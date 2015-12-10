package br.com.cassioliveira.lojaartesanato.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

    @Column(name = "product_code")
    private String code;

    @Column(name = "product_bar_code", length = 255)
    private String barCode;

    @Column(name = "product_description", length = 255)
    private String description;

    @Column(name = "product_category", length = 100)
    private String category;

    @Column(name = "product_price")
    private Double price;

    /**
     * Atributo referente ao armazenamento da data em que o registro foi
     * criado/alterado.
     */
    @Column(name = "product_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
}
