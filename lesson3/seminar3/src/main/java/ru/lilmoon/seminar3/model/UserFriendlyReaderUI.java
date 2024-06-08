package ru.lilmoon.seminar3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserFriendlyReaderUI {
    private long id;
    private String name;
    private String bookName;
}
