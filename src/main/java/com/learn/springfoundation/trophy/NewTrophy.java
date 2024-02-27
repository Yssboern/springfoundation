package com.learn.springfoundation.trophy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NewTrophy {
    private Long id;
    private String name;
    private Integer year;
    private Long discipline;
}
