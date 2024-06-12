package ru.lilmoon.seminar3.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lilmoon.seminar3.entity.IssueEntity;
import ru.lilmoon.seminar3.model.BookLimitExceededException;
import ru.lilmoon.seminar3.model.Issue;
import ru.lilmoon.seminar3.repository.IssueRepository;
import ru.lilmoon.seminar3.service.IssueServiceImpl;
import ru.lilmoon.seminar3.service.IssuerServiceImpl;


import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
@Tag(name = "Issue Service API",description = "API to manage issues.")
public class IssuerController {

    @Autowired
    private final IssuerServiceImpl service;
    @Autowired
    private final IssueServiceImpl issueService;

    public IssuerController(IssuerServiceImpl service,IssueServiceImpl issueService) {
        this.service = service;
        this.issueService = issueService;
    }

    @PostMapping
    @Operation(
            summary = "Creates an issues",
            description = "Creates an issue using :readerId and :bookId from request."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201",content = {@Content(schema = @Schema(implementation = IssueEntity.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "409",content = {@Content(schema = @Schema())})
    })
    public ResponseEntity<IssueEntity> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final IssueEntity issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (BookLimitExceededException ex) {
            log.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieves the issue by :id",
            description = "The response is the issue object with id,readerId,bookId,issued_at,returned_at."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema = @Schema(implementation = IssueEntity.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",content = {@Content(schema = @Schema())})
    })
    public IssueEntity getIssueById(@PathVariable long id) {
        return issueService.getIssueById(id);
    }

    @GetMapping
    @Operation(
            summary = "Retrieves an all issues.",
            description = "Retrieves a list of issues from Data Base."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(array = @ArraySchema(schema = @Schema(implementation = IssueEntity.class)),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",content = {@Content(schema = @Schema())})
    })
    public List<IssueEntity> getAllIssues() {
        return issueService.getAll();
    }

//    @GetMapping("/history")
//    public List<IssueEntity> getAllIssues() {
//        return repository.getAllIssues();
//    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Return book by :id",
            description = "Updates issue's returned_at field, book considered as \"RETURNED\""
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(array = @ArraySchema(schema = @Schema(implementation = IssueEntity.class)),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",content = {@Content(schema = @Schema())})
    })
    public IssueEntity returnBookById(@PathVariable long id) {
        return service.closeIssue(id);
    }
}
