package co.edu.uniquindio.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.io.Serializable;


@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documents")
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String remitente;
    private String destinatario;

    @ManyToOne
    private UserEntity userEntity;

    @OneToOne(mappedBy = "document")
    private EmployeeEntity employeeEntity;

}
