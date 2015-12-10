package br.com.cassioliveira.lojaartesanato.dao;

import br.com.cassioliveira.lojaartesanato.model.Product;
import java.io.Serializable;

/**
 *
 * @author cassio
 */
public class ProductDao extends AbstractDao<Product> implements Serializable {

    public ProductDao() {
        super(Product.class);
    }
}
