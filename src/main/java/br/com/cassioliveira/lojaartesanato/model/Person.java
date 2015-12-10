package br.com.cassioliveira.lojaartesanato.model;

import br.com.cassioliveira.lojaartesanato.enumerations.States;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author cassio
 */
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 0, max = 100)
    @Column(name = "client_name", nullable = false, length = 100)
    private String name;

    @Column(name = "client_nick_name", length = 100)
    private String nickName;

    @Pattern(regexp = "^$|^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$",
            message = "E-mail com formato incorreto")
    @Size(min = 0, max = 100)
    @Column(name = "client_email", length = 100)
    private String email;

    @Size(min = 0, max = 150)
    @Column(name = "client_address_street", length = 150)
    private String street;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_address_uf")
    private States uf;

    @Size(min = 0, max = 70)
    @Column(name = "client_address_neighborhood", length = 70)
    private String neighborhood;

    @Size(min = 0, max = 7)
    @Column(name = "client_address_number", length = 7)
    private String number;

    @Size(min = 0, max = 100)
    @Column(name = "client_address_city", length = 100)
    private String city;

    @Size(min = 0, max = 50)
    @Column(name = "client_address_country", length = 50)
    private String country;

    @Size(min = 0, max = 10)
    @Column(name = "client_address_postal_code", length = 10)
    private String postalCode;

    @NotNull
    @Column(name = "client_phone1", nullable = false)
    private String phone1;

    @Column(name = "client_phone2")
    private String phone2;

    @Column(name = "client_nationality")
    private String nationality;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "client_date")
    private Date date;
}
