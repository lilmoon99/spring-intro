package ru.lilmoon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "readers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "readerId")
    private UUID readerId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
}
