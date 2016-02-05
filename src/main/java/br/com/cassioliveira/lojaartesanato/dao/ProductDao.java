package br.com.cassioliveira.lojaartesanato.dao;

import br.com.cassioliveira.lojaartesanato.model.Product;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author cassio
 */
public class ProductDao extends AbstractDao<Product> implements Serializable {

    public ProductDao() {
        super(Product.class);
    }

    public List<String> getCategories() {
        return getEntityManager().createNamedQuery("Product.categories").getResultList();
    }

    public List<Product> findByUser(String user) {
        return getEntityManager().createNamedQuery("Product.registeredByUser", Product.class).setParameter("userName", user).getResultList();
    }
}
