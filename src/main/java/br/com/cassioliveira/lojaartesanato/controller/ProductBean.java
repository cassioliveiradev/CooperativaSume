package br.com.cassioliveira.lojaartesanato.controller;

import br.com.cassioliveira.lojaartesanato.enumerations.Unit;
import br.com.cassioliveira.lojaartesanato.services.ProductServices;
import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import br.com.cassioliveira.lojaartesanato.model.Product;
import br.com.cassioliveira.lojaartesanato.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;

/**
 *
 * @author cassio
 */
@Model
public class ProductBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @Getter
    @Setter
    private ProductServices productServices;

    @Inject
    @Getter
    @Setter
    private Product product;

    @Inject
    @Getter
    @Setter
    private Product selectedProduct;

    @Getter
    private List<Product> products;

    @Getter
    private List<Product> productsByUsers;

    @Getter
    private final List<Unit> units;

    @Getter
    private List<String> categories;

    private final String LOGGED_USER = (String) SecurityUtils.getSubject().getPrincipal();

    public ProductBean() {
        units = Arrays.asList(Unit.values());
    }

    @PostConstruct
    public void init() {
        this.productsByUsers = productServices.findByUser(this.LOGGED_USER);
        this.categories = productServices.getCategories(); //Consulta o banco e retorna as categorias cadastradas
        this.products = productServices.findAll();

    }

    public void save() throws GenericException {
        this.productServices.save(product);
        if (getEditing()) {
            FacesUtil.sucessMessage("Cadastro do produto '" + product.getDescription() + "' atualizado com sucesso!");
        } else {
            FacesUtil.sucessMessage("Cadastro do produto " + product.getDescription() + " realizada com sucesso!");
        }
//        FacesUtil.redirectTo("PesquisaProduto.xhtml");
        product = new Product();
    }

    public void remove() throws GenericException {
        this.productServices.delete(selectedProduct);
        FacesUtil.sucessMessage("Exclusão efetuada com sucesso!");
        product = new Product();
    }

    /**
     * Metodo que verifica se o objeto esta nulo quando for recuperado. Se sim,
     * refere-se a um novo cadastro, senao refere-se a um produto a ser editado
     *
     * @return
     */
    public boolean getEditing() {
        return this.product.getId() != null;
    }
}
