package br.com.cassioliveira.lojaartesanato.services;

import br.com.cassioliveira.lojaartesanato.dao.ProductDao;
import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import br.com.cassioliveira.lojaartesanato.model.Product;
import br.com.cassioliveira.lojaartesanato.util.jpa.Transactional;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class ProductServices {

    @Inject
    private ProductDao productDao;

    @Transactional
    public void save(Product product) throws GenericException {
        this.productDao.save(product);
    }

    @Transactional
    public void delete(Product product) throws GenericException {
        product = findById(product.getId());
        productDao.delete(product);
    }

    public Product findById(Long id) {
        return productDao.findById(id);
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }
}
