package br.com.cassioliveira.lojaartesanato.model;

import br.com.cassioliveira.lojaartesanato.enumerations.Unit;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Class to modelling the Product entity.
 *
 * @author Cássio Oliveira
 * @author Wilde Arruda
 */
@Entity
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 25)
    @Column(name = "product_code", length = 25)
    private String code;

    @Size(max = 50)
    @Column(name = "product_bar_code", length = 50)
    private String barCode;

    @NotNull(message = "Um nome deve ser informado")
    @Column(name = "product_description", length = 70)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "product_unit")
    private Unit unit;

    @Size(max = 100)
    @Column(name = "product_category", length = 100)
    private String category;

    @Column(name = "product_price")
    private Double price;
    
    @Column(name = "product_is_perishable")
    private boolean perishable;
    
    @Column(name = "product_expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    /**
     * Atributo referente ao armazenamento da data em que o registro foi
     * criado/alterado.
     */
    @Column(name = "product_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    
    /**
     * Este método anotado com @PostConstruct é executado na inicialização da
     * entidade e seta um valor padrão para o(s) atributo(s) especificado(s).
     */
    @PostConstruct
    public void init() {
        setUnit(Unit.UNIDADE);
    }
}
