package com.Lost.Found.Lost.Found.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor @Data
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String location;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUser owner;
}
