package com.example.server.userpage;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userResource")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResourceEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "smth")
    private String smth;
}
