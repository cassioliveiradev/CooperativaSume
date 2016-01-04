package br.com.cassioliveira.lojaartesanato.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author cassio
 */
@Entity
public class Corporate extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column(name = "product_fantasy_name", length = 150)
    private String fantasyName;

    @Getter
    @Setter
    @Column(name = "product_cnpj", length = 14, unique = true)
    private String cnpj;

    @Getter
    @Setter
    @Column(name = "product_state_register", length = 15)
    private String stateRegister;

    @Getter
    @Setter
    @Column(name = "product_municipal_register", length = 15)
    private String municipalRegister;

//    @ManyToOne
//    @JoinColumn(name = "hosting_fk", referencedColumnName = "id")
//    private Hosting hostingFK;
    @Override
    public int hashCode() {
        int hashCorporate = 5;
        hashCorporate = 29 * hashCorporate + Objects.hashCode(this.cnpj);
        return hashCorporate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Corporate other = (Corporate) obj;
        return Objects.equals(this.cnpj, other.cnpj);
    }

}
