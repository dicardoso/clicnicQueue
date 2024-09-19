package com.example.clicnicQueue.service;

import com.example.clicnicQueue.dto.ticket.TicketResponseDTO;
import com.example.clicnicQueue.model.Counter;
import com.example.clicnicQueue.model.ServiceType;
import com.example.clicnicQueue.model.Status;
import com.example.clicnicQueue.model.Ticket;
import com.example.clicnicQueue.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService extends AbstractService<Ticket, Long> {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ServiceTypeService serviceTypeService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private StatusService statusService;
    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        super(ticketRepository);
    }

    public TicketResponseDTO generateTicket(Long serviceTypeId) {
        ServiceType serviceType = serviceTypeService.findById(serviceTypeId)
                .orElseThrow(() -> new RuntimeException("Service type not found"));
        Status defaultStatus = statusService.findById(1L)
                .orElseThrow(() -> new RuntimeException("Default status not found"));

        Ticket lastTicket = ticketRepository.findTopByServiceTypeIdOrderByIssuedAtDesc(serviceTypeId);
        String nextNumber;
        if (lastTicket == null || isNewDay(lastTicket.getIssuedAt())) {
            nextNumber = generateNextTicketNumber(null, serviceType.getCode());
        } else {
            nextNumber = generateNextTicketNumber(lastTicket, serviceType.getCode());
        }

        Ticket newTicket = new Ticket();
        newTicket.setNumber(nextNumber);
        newTicket.setServiceType(serviceType);
        newTicket.setIssuedAt(LocalDateTime.now());
        newTicket.setStatus(defaultStatus);

        ticketRepository.save(newTicket);
        return new TicketResponseDTO(
                newTicket.getId(),
                newTicket.getNumber(),
                newTicket.getIssuedAt(),
                newTicket.getCalledAt()
        );
    }
    private boolean isNewDay(LocalDateTime lastIssuedAt) {
        return !lastIssuedAt.toLocalDate().equals(LocalDate.now());
    }
    private String generateNextTicketNumber(Ticket lastTicket, String serviceCode) {
        int nextNumber = 1;
        if (lastTicket != null) {
            String lastNumber = lastTicket.getNumber().replace(serviceCode, "");
            nextNumber = Integer.parseInt(lastNumber) + 1;
        }
        return serviceCode + String.format("%03d", nextNumber);
    }

    public TicketResponseDTO callNextTicket(Long counterId) {
        Counter counter = counterService.findById(counterId)
                .orElseThrow(() -> new RuntimeException("Counter not found"));


        Status waitingStatus = statusService.findById(1L)
                .orElseThrow(() -> new RuntimeException("Waiting status not found"));

        List<Ticket> pendingTickets = ticketRepository.findTicketsByStatusOrderByPriorityAndIssuedAt(waitingStatus.getId());


        Optional<Ticket> nextTicket = pendingTickets.stream()
                .min(Comparator.comparingDouble(this::calculatePriorityWeight));


        Status calledStatus = statusService.findById(2L) // Supondo que o ID 2 seja 'chamado'
                .orElseThrow(() -> new RuntimeException("Called status not found"));
        Ticket ticket = nextTicket.get();

        ticket.setStatus(calledStatus);
        ticket.setCalledAt(LocalDateTime.now());
        ticket.setCounter(counter);

        ticketRepository.save(ticket);

        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getNumber(),
                ticket.getIssuedAt(),
                ticket.getCalledAt()
        );
    }

    private double calculatePriorityWeight(Ticket ticket) {

        int priority = ticket.getServiceType().getPriority();
        long waitingTime = java.time.Duration.between(ticket.getIssuedAt(), LocalDateTime.now()).toMinutes();

        return priority * Math.log(waitingTime + 1);
    }
}
