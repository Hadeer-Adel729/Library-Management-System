package code81.library.LibrarySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "language")
    private String language;

    @Column(name = "publication_year", columnDefinition = "YEAR")
    private Integer publicationYear;

    @Column(name = "ISBN", nullable = false, unique = true, length = 10)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "edition")
    private String edition;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "total_copies")
    private Integer totalCopies;

    @Column(name = "available_copies")
    private Integer availableCopies;

    @ManyToMany
    @JoinTable(
            name = "BookAuthors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "BookPublishers",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    private Set<Publisher> publishers = new HashSet<>();


    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this); // Maintain bidirectional relationship
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getBooks().remove(this); // Maintain bidirectional relationship
    }

    public void addPublisher(Publisher publisher) {
        publishers.add(publisher);
        publisher.getBooks().add(this); // Maintain bidirectional relationship
    }

    public void removePublisher(Publisher publisher) {
        publishers.remove(publisher);
        publisher.getBooks().remove(this); // Maintain bidirectional relationship
    }

    // Helper method for Category
    public void setCategory(Category category) {
        // Remove from old category
        if (this.category != null) {
            this.category.getBooks().remove(this);
        }

        // Set new category
        this.category = category;

        // Add to new category
        if (category != null) {
            category.getBooks().add(this);
        }
    }
}