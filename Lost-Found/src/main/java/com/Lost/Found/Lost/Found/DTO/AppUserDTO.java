package com.Lost.Found.Lost.Found.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class AppUserDTO {
    private Long id;
    private String name;
    private String department;
    private String email;
    List<ItemDTO> items = new ArrayList<>();
}
