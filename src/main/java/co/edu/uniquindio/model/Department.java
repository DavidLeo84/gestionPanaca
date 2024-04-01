package co.edu.uniquindio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
//@Audited
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
    private Set<EmployeeEntity> employees;

    @OneToOne(mappedBy = "department")
    private UserEntity userEntity;
}
