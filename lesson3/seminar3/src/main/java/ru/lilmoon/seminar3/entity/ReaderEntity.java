package ru.lilmoon.seminar3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lilmoon.seminar3.model.Role;

import java.util.Set;

@Entity
@Table(name = "readers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReaderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reader_id")
    private long id;
    @Column(name = "name",unique = true)
    private String name;

//    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "reader_id"))
//    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    private String password;
}
