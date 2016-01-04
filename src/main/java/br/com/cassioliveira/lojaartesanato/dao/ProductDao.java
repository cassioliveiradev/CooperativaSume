package br.com.cassioliveira.lojaartesanato.dao;

import br.com.cassioliveira.lojaartesanato.model.Product;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author cassio
 */
public class ProductDao extends AbstractDao<Product> implements Serializable {

    public ProductDao() {
        super(Product.class);
    }

    public List<String> getCategories() {
        Query createQuery;

        createQuery = getEntityManager().createQuery("SELECT DISTINCT p.category FROM Product p");
        return createQuery.getResultList();
    }
}
