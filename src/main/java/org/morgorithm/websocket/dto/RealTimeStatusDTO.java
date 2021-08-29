package org.morgorithm.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.morgorithm.websocket.entity.Status;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RealTimeStatusDTO {
    int[] in;
    int[] out;
    int[] bno;
    int total;
    String[] bName;
    List<Status> statusList;
}
