package ru.lilmoon.seminar3.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "readers")
@Data
public class ReaderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
}
