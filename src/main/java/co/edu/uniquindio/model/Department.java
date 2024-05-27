package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
//@Audited
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departments")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "department_name")
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private Set<EmployeeEntity> listEmployees;

    @OneToOne(mappedBy = "department")
    private UserEntity userEntity;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Department(String departmentName, Set<EmployeeEntity> listEmployees, UserEntity userEntity, Status status) {

        this.departmentName = departmentName;
        this.listEmployees = listEmployees;
        this.userEntity = userEntity;
        this.status = status;
    }
}


