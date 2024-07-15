package ru.lilmoon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.lilmoon.Entities.Book;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "issueId")
    private UUID issueId;

    @Column(name = "reader")
    @JdbcTypeCode(SqlTypes.JSON)
    private Reader reader;

    @Column(name = "book")
    @JdbcTypeCode(SqlTypes.JSON)
    private Book book;

    @Column(name = "issuedDate")
    private LocalDate issuedDate;
}
