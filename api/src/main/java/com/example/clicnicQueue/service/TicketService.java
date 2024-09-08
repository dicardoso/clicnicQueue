package com.example.clicnicQueue.service;

import com.example.clicnicQueue.dto.ticket.TicketResponseDTO;
import com.example.clicnicQueue.model.ServiceType;
import com.example.clicnicQueue.model.Status;
import com.example.clicnicQueue.model.Ticket;
import com.example.clicnicQueue.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService extends AbstractService<Ticket, Long> {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ServiceTypeService serviceTypeService;
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
        String nextNumber = generateNextTicketNumber(lastTicket, serviceType.getCode());

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
    private String generateNextTicketNumber(Ticket lastTicket, String serviceCode) {
        int nextNumber = 1;
        if (lastTicket != null) {
            String lastNumber = lastTicket.getNumber().replace(serviceCode, "");
            nextNumber = Integer.parseInt(lastNumber) + 1;
        }
        return serviceCode + String.format("%03d", nextNumber);
    }
}
