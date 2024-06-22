package ru.lilmoon.seminar3.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lilmoon.seminar3.entity.IssueEntity;
import ru.lilmoon.seminar3.entity.ReaderEntity;
import ru.lilmoon.seminar3.model.Issue;
import ru.lilmoon.seminar3.model.Reader;
import ru.lilmoon.seminar3.repository.IssueRepository;
import ru.lilmoon.seminar3.repository.ReaderRepository;
import ru.lilmoon.seminar3.service.IssueServiceImpl;
import ru.lilmoon.seminar3.service.ReaderServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/reader")
@Tag(name = "Readers API", description = "API to manage readers.")
public class ReaderController {
    @Autowired
    private final ReaderServiceImpl readerService;


    public ReaderController(ReaderServiceImpl readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    @Operation(
            summary = "Retrieves an all readers.",
            description = "Retrieves a list of readers from Data Base."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ReaderEntity.class)), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    public List<ReaderEntity> getAllReaders() {
        return readerService.getAllReaders();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieves thr reader by :id",
            description = "The response is the reader object with id,name"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ReaderEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",description = "Reader not found.", content = {@Content(schema = @Schema())})
    })
    public ReaderEntity getReaderById(@PathVariable long id) {
        return readerService.getById(id);
    }

    @Operation(
            summary = "Creates a reader",
            description = "Creates a reader and adds it to the Data Base."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = ReaderEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @PostMapping
    public ReaderEntity addReader(@RequestBody ReaderEntity reader) {
        return readerService.createReader(reader);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletes the reader by :id",
            description ="Deletes the reader by :id from the Data Base.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404",description = "Reader not found.",content = {@Content(schema = @Schema())})
    })
    public void deleteReaderById(@PathVariable long id) {
        readerService.deleteReaderById(id);
    }

    @Operation(
            summary = "Retrieves all issues by :readerId",
            description = "Retrieves a list of issues from Data Base related to concrete reader using :readerId"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(array = @ArraySchema(schema = @Schema(implementation = IssueEntity.class)),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",description = "Reader not found.",content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{id}/issue")
    public List<IssueEntity> getIssuesByReaderId(@PathVariable long id) {
        return readerService.getIssuesByReaderId(id);
    }
}
