package com.Lost.Found.Lost.Found.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class ItemDTO {
    private String name;
    private String type;
    private String location;
    private LocalDate date;
    private Long ownerId;
}
