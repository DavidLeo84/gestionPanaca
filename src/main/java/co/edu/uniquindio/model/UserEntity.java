package co.edu.uniquindio.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@ToString
//@Audited
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 15)
    private String username;

    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "userEntity")
    private Set<EmployeeEntity> employeeSet;

    @OneToOne
    private Department department;

    @OneToMany(mappedBy = "userEntity")
    private Set<Document> documentSet;

    @OneToMany(mappedBy = "userEntity")
    private Set<NewEntity> newEntitySet;

    @Builder
    public UserEntity(String firstName, String lastname, String dni, String numberPhone,
                      String localNumberPhone, Address address, LocalDate birthDate, String username,
                      String password, Set<Role> roles) {
        super(firstName, lastname, dni, numberPhone, localNumberPhone, address, birthDate);
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
