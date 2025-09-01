package com.Lost.Found.Lost.Found.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor @Data @Getter @Setter
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String department;

    @OneToMany(mappedBy = "owner" , cascade = CascadeType.ALL , orphanRemoval = true)
    List<Item> items = new ArrayList<>();
}
