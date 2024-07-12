package ru.lilmoon.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.lilmoon.Entities.Author;
@Data
@AllArgsConstructor
public class TitleAndAuthorResponse {

    private String title;
    private Author author;
}
