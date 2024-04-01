package co.edu.uniquindio.model;


import co.edu.uniquindio.model.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Data
@Entity
@Builder
@ToString
//@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ERole name;
}
