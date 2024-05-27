package co.edu.uniquindio.model;

import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;


@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address implements Serializable {
//direccion de donde vive
    private String city; //ciudad
    private String stateProvince; //Departamento de la ciudad
    private String suburb; //barrio donde vive
    private String streetAdress; //direccion de la casa

}
