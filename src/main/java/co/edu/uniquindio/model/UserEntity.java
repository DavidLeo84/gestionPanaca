package co.edu.uniquindio.model;


import co.edu.uniquindio.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
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
    private String username; // nombre del usuario para iniciar sesion

    @NotBlank
    private String password; // contraseña para iniciar sesion

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles; // roles que cumple el usuario

//    @OneToMany(mappedBy = "userEntity")
//    private Set<EmployeeEntity> employeeSet; // lista de empleados a cargo

    @OneToOne
    private Department department; // departamento al que pertenece el usuario

    @OneToMany(mappedBy = "userEntity")
    private Set<Document> documentSet; // lista de documentos que puede crear

    @OneToMany(mappedBy = "userEntity")
    private Set<PayRollNews> payRollNewsSet; // lista de novedades de nomina que realiza

    @Builder
    public UserEntity(String firstName, String lastname, String dni, String numberPhone,
                      String localNumberPhone, Address address, String birthDate, Status status,
                      String username,
                      String password, Set<Role> roles, Department department) {
        super(firstName, lastname, dni, numberPhone, localNumberPhone, address, birthDate, status);
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.department = department;
    }
}
