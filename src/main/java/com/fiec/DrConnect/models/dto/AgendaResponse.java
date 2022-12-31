package com.fiec.DrConnect.models.dto;

import com.fiec.DrConnect.models.enums.AgendaStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgendaResponse {
    Integer id;
    String startTime;
    String endTime;
    String title;
    AgendaStatus agendaStatus;

}
