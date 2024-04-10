package com.learn.springfoundation.errsave;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "TGN", name = "TASK")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class JobTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String description;
    private String result;
    private Integer attempts;
}
