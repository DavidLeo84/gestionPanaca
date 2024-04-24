package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Data
@ToString
@MappedSuperclass
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastname;

    private String dni;

    @Column(name = "number_phone")
    private String numberPhone;

    @Column(name = "local_number_phone")
    private String localNumberPhone;

    private Address address;

    @Column(name = "birth_date")
    private String birthDate;

    private Status status;

    public Person(String firstName, String lastname, String dni, String numberPhone,
                  String localNumberPhone, Address address, String birthDate, Status status) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.dni = dni;
        this.numberPhone = numberPhone;
        this.localNumberPhone = localNumberPhone;
        this.address = address;
        this.birthDate = birthDate;
        this.status = status;
    }

}
