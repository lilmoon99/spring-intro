package ru.lilmoon.seminar3.service;

import ru.lilmoon.seminar3.entity.ReaderEntity;
import ru.lilmoon.seminar3.repository.ReaderRepository;

import java.util.List;

public interface ReaderService {

    List<ReaderEntity> getAllReaders();

    ReaderEntity getById(long id);

    ReaderEntity createReader(ReaderEntity reader);

    ReaderEntity updateReaderById(long id,ReaderEntity reader);

    void deleteReaderById(long id);

}
