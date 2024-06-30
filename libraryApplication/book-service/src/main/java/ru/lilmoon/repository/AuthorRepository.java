package ru.lilmoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lilmoon.Entities.Author;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
