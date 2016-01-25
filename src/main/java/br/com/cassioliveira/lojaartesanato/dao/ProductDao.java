package br.com.cassioliveira.lojaartesanato.dao;

import br.com.cassioliveira.lojaartesanato.model.Product;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.inject.spi.Producer;
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
    
    public List<Product> findByUser(String user) {
        Query createQuery;
        createQuery = getEntityManager().createQuery("FROM Product p WHERE p.userName = :userName", Product.class);
        createQuery.setParameter("userName", user);
        return createQuery.getResultList();
    }
}
