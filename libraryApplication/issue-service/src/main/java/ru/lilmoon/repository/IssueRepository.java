package ru.lilmoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.lilmoon.entity.Issue;

import java.util.UUID;

public interface IssueRepository extends JpaRepository<Issue, UUID> {
    @Query(value = "SELECT LAST_INSERT_ID()",nativeQuery = true)
    UUID getLastInsertedId();
}
