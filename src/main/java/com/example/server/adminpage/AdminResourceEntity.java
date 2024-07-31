package com.example.server.adminpage;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "adminResource")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResourceEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "smth")
    private String smth;
}
