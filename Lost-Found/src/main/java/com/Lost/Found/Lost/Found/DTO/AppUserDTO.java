package com.Lost.Found.Lost.Found.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class AppUserDTO {
    private String name;
    private String department;
    List<ItemDTO> items;
}
