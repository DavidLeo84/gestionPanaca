package co.edu.uniquindio.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
public class NewEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Double price;

    @OneToOne
    private EmployeeEntity employeeEntity;

    @ManyToOne
    private UserEntity userEntity;

}
