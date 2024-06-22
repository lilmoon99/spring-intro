package ru.lilmoon.seminar3.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lilmoon.seminar3.entity.ReaderEntity;
import ru.lilmoon.seminar3.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface ReaderRepository extends JpaRepository<ReaderEntity,Long> {

    public ReaderEntity findByName(String name);

//  private final List<Reader> readers;
//
//  public ReaderRepository() {
//    this.readers = new ArrayList<>();
//  }
//
//  @PostConstruct
//  public void generateData() {
//    String[] randomNames = {"Игорь","Айнур","Елизавета","Николай","Михаил"};
//    for (String randomName : randomNames) {
//      readers.add(new Reader(randomName));
//    }
//  }
//
//  public List<Reader> getAllReaders(){
//    return List.copyOf(readers);
//  }
//
//  public Reader getReaderById(long id) {
//    return readers.stream().filter(it -> Objects.equals(it.getId(), id))
//      .findFirst()
//      .orElse(null);
//  }
//
//  public Reader addReader(Reader reader){
//    readers.add(reader);
//    return reader;
//  }
//
//  public void deleteReaderById(long id){
//    readers.removeIf(it -> Objects.equals(it.getId(),id));
//  }

}
