package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "client_books", schema = "libraryJPA")
public class Book implements Serializable {
    private static final long serialVersionUID = 102L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "book_isbn",
            nullable = false,
            length = 13)
    private String ISBN;
    @Column(name = "book_title",
            nullable = false)
    private String title;
    @Column(name = "book_author",
            nullable = false)
    private String author;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "book_client_id")
    private Client client;

    public Book() {}

    public Book(String ISBN, String title, String author) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", client=" + client +
                '}';
    }
}
