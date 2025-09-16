package code81.library.LibrarySystem.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Staff")
@PrimaryKeyJoinColumn(name = "id")
public class Staff extends User {

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private StaffRole role;

    @Column(name = "employee_id", nullable = false, unique = true)
    private String employeeId;

    @Column(name = "is_active")
    private Boolean isActive = true;

}

