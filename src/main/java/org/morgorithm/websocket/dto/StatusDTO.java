package org.morgorithm.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.morgorithm.websocket.entity.Facility;
import org.morgorithm.websocket.entity.Member;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO {
    private Long statusnum;
    private Member member;
    private Facility facility;
    private double temperature;
    private Boolean state;
    private LocalDateTime regDate;
}
