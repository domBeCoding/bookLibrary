package uk.codingplayroom.bls.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.codingplayroom.bls.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    Optional<Book> findById(int id);
    Optional<Book> findByTitle(String title);
    Optional<List<Book>> findByAuthor(String author);
    Optional<Book> findByPages(int pages);
    Optional<List<Book>> findByGenre(String genre);
}
