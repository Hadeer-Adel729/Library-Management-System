package code81.library.LibrarySystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email" , unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "country" , nullable = false)
    private String country;

    @Column(name = "city")
    private String city;

    @ManyToMany(mappedBy = "publishers" , fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        books.add(book);
        book.getPublishers().add(this); // Maintain bidirectional relationship
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.getPublishers().remove(this); // Maintain bidirectional relationship
    }

}