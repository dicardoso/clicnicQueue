package com.example.clicnicQueue.dto.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TicketResponseDTO {
    private final Long id;
    private final String number;
    @JsonProperty("issued_at")
    private LocalDateTime issuedAt;
    @JsonProperty("called_at")
    private LocalDateTime calledAt;
}
