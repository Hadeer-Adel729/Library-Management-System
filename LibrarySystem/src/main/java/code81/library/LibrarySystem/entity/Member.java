package code81.library.LibrarySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Members")
@PrimaryKeyJoinColumn(name = "id")
public class Member extends User {

    @Column(name = "membership_number", nullable = false, unique = true)
    private String membershipNumber;

}