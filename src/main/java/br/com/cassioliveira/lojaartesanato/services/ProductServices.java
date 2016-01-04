package br.com.cassioliveira.lojaartesanato.services;

import br.com.cassioliveira.lojaartesanato.dao.ProductDao;
import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import br.com.cassioliveira.lojaartesanato.model.Product;
import br.com.cassioliveira.lojaartesanato.util.jpa.Transactional;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class ProductServices {

    private static final Log LOGGER = LogFactory.getLog(ProductServices.class);

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

    public List<String> getCategories() {
        return productDao.getCategories();
    }

    /**
     * Recebe o valor passado pelo método buscarPorCampo() para determinar a
     * duplicidade do cadastro e lança uma exceção informando ao usuário qual
     * campo não pode ser inserido por já existir no banco de dados.
     *
     * @param campo
     * @param valor
     * @param id
     * @return
     */
    public boolean verificaCampoUnique(String campo, Object valor, Long id) throws GenericException {

        try {

            Product product;

            if (id == null) {
                product = productDao.buscarPorCampo(campo, valor);
                if (product != null) {
                    throw new GenericException("Já existe um cadastro com esse(a) " + campo.toUpperCase());
                }
            } else {
                product = productDao.buscarPorCampo(campo, valor);
                if (product != null && id.equals(product.getId())) {
                    throw new GenericException("Já existe um cadastro com esse(a) " + campo.toUpperCase());
                }
                return true;
            }
        } catch (NoResultException ex) {
            LOGGER.info("Infomação não encontrada");
        }
        return true;
    }
}
