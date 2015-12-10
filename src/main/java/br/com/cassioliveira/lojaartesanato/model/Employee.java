package br.com.cassioliveira.lojaartesanato.model;

import br.com.cassioliveira.lojaartesanato.enumerations.Documentation;
import br.com.cassioliveira.lojaartesanato.enumerations.Gender;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author cassio
 */
@Entity
public class Employee extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column(name = "client_cpf_client", length = 11, unique = true)
    private String cpfEmployee;

    @Getter
    @Setter
    @NotNull
    @Size(min = 0, max = 20, message = "O codigo deve ter no maximo 20 caracteres")
    @Column(name = "emp_code", nullable = false, length = 20)
    private String code;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "client_gender")
    private Gender gender;

    @Getter
    @Setter
    @Column(name = "client_doc_number", length = 15)
    private String docNumber;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "client_doc_type", length = 50)
    private Documentation docType;

    @Getter
    @Setter
    @Column(name = "client_issuing_body", length = 15)
    private String issuingBody;

    @Getter
    @Setter
    @Column(name = "client_profession", length = 100)
    private String profession;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "client_birthdate")
    private Date birthDate;

    /* Attibutes to relationship of Entities */
//    @ManyToOne
//    @JoinColumn(name = "hosting_fk", referencedColumnName = "id")
//    private Hosting hostingFK;
//    @OneToMany(mappedBy = "employeeFK", targetEntity = Room.class)
//    private List<Room> rooms;
//    @OneToMany(mappedBy = "employeeFK", targetEntity = Hosting.class)
//    private List<Hosting> hostings;
//    @OneToMany(mappedBy = "employeeFK", targetEntity = Reservation.class)
//    private List<Reservation> reservations;
//    @OneToMany(mappedBy = "employeeFK", targetEntity = Service.class)
//    private List<Service> services;
//    @OneToMany(mappedBy = "employeeFK", targetEntity = Person.class)
//    private List<Person> clients;
//    @OneToMany(mappedBy = "employeeFK", targetEntity = SystemUser.class)
//    private List<SystemUser> users;

    @Override
    public int hashCode() {
        int hashEmployee = 3;
        hashEmployee = 59 * hashEmployee + Objects.hashCode(this.cpfEmployee);
        return hashEmployee;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        return Objects.equals(this.cpfEmployee, other.cpfEmployee);
    }

}
