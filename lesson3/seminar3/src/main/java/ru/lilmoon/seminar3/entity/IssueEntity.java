package ru.lilmoon.seminar3.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "issues")
public class IssueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_id")
    private long bookId;

    @Column(name = "reader_id")
    private long readerId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "issued_at")
    private LocalDateTime issued_at;

    @Column(name = "returned_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime returned_at;
}
