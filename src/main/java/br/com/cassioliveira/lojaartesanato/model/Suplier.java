package br.com.cassioliveira.lojaartesanato.model;

import br.com.cassioliveira.lojaartesanato.enumerations.States;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Suplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 0, max = 100)
    @Column(name = "suplier_name", nullable = false, length = 200)
    private String name;

    @Column(name = "suplier_cnpj", length = 14)
    private String cnpj;

    @Pattern(regexp = "^$|^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$",
            message = "E-mail com formato incorreto")
    @Size(min = 0, max = 100)
    @Column(name = "suplier_email", length = 100)
    private String email;

    @Size(min = 0, max = 150)
    @Column(name = "suplier_address_street", length = 150)
    private String street;

    @Enumerated(EnumType.STRING)
    @Column(name = "suplier_address_uf")
    private States uf;

    @Size
    @Column(name = "suplier_address_neighborhood", length = 70)
    private String neighborhood;

    @Size
    @Column(name = "suplier_address_number", length = 7)
    private String number;

    @Size
    @Column(name = "suplier_address_city", length = 100)
    private String city;

    @Size
    @Column(name = "suplier_address_country", length = 50)
    private String country;

    @Size
    @Column(name = "suplier_address_postal_code", length = 10)
    private String postalCode;

    @Column(name = "suplier_phone1")
    private String phone1;

    @Column(name = "suplier_phone2")
    private String phone2;

    @PostConstruct
    public void init() {
        setCountry("Brasil");
    }

}
