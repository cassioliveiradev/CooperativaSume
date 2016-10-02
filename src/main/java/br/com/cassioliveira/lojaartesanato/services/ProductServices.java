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
        if("".equals(product.getCategory()) || product.getCategory() == null){
            product.setCategory("Sem categoria");
        }
        this.productDao.save(product);
    }
    
    /**
     * Método responsável por debitar a quantidade de produtos vendida
     * da quantidade total de produtos e salvar o novo valor no banco.
     * @param product
     * @param quantitySell
     * @throws GenericException 
     */
    @Transactional
    public void sell(Product product, int quantitySell) throws GenericException {
        product.setQuantity(product.getQuantity() - quantitySell);
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
        
//        List<String> categories;
//        categories = productDao.getCategories();
//        if(categories.contains(null)){
//            categories.remove(null);
//        }
//        return categories;
    }
    
    public List<Product> findByUser(String user) {
        return productDao.findByUser(user);
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
