package pl.camp.it.library.model;

import javax.persistence.*;

@Entity(name = "tbook") // zostanie utworzona nowa tabelka - entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //jak dociagam ksiazke to od razu dociagam autora w agresywnym
    private Author author;
    @Column(nullable = false, unique = true, length = 20)
    private String isbn;
    @Enumerated(EnumType.STRING)
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) { // set nie sprawdza po equals, jest nie potrzebna
        if(o instanceof Book) {
            Book b = (Book) o;
            return b.id == this.id;

            /*if(b.id == this.id) {
                return true;
            } else {
                return false;
            }*/

        }
        return false;
    }

    @Override
    public int hashCode() { // sety nie porownuja po equals a po hashCode
        return this.id;
    }

    public enum Category { // istenienie kategorii nie ma sensu bez istnienia książki
        COOKING,
        BAKING
    }
}
