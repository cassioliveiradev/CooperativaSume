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
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author cassio
 */
@Entity
public class Client extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column(name = "client_cpf_client", length = 11, unique = true)
    private String cpfClient;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "client_gender")
    private Gender gender;

    @Getter
    @Setter
    @Pattern(regexp = "^$|[a-zA-Z\\d/.-]{1,}",
            message = "Apenas letras números ou os caracteres a seguir são aceitos para o RG: / . -")
    @Column(name = "client_doc_travel_number", length = 15)
    private String docTravelNumber;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "client_travel_doc_type", length = 50)
    private Documentation travelDocType;

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


    @Override
    public int hashCode() {
        int hashClient = 7;
        hashClient = 23 * hashClient + Objects.hashCode(this.cpfClient);
        return hashClient;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        return Objects.equals(this.cpfClient, other.cpfClient);
    }
}
