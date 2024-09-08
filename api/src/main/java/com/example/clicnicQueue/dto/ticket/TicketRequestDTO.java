package com.example.clicnicQueue.dto.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TicketRequestDTO {
    @JsonProperty("service_type_id")
    private Long serviceTypeId;
}
