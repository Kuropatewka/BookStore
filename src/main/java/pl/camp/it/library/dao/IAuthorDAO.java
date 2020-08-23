package pl.camp.it.library.dao;

import pl.camp.it.library.model.Author;

import java.util.List;

public interface IAuthorDAO {

    public List<Author> findAuthors(String pattern);
}
