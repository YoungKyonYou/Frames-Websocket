package org.morgorithm.websocket.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String building;
}
