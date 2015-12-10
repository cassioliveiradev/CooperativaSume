package br.com.cassioliveira.lojaartesanato.controller;

import br.com.cassioliveira.lojaartesanato.services.ProductServices;
import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import br.com.cassioliveira.lojaartesanato.model.Product;
import br.com.cassioliveira.lojaartesanato.model.Client;
import br.com.cassioliveira.lojaartesanato.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

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
    private List<Client> clients;

    @Getter
    private List<Product> products;

    public ProductBean() {

    }

    @PostConstruct
    public void init() {
        this.products = productServices.findAll();
    }

    public void save() throws GenericException {
        if (getEditing()) {
            this.productServices.save(product);
            FacesUtil.sucessMessage("Cadastro do produto '" + product.getCode() + "' atualizado com sucesso!");
            FacesUtil.redirectTo("SearchProduct.xhtml");
        } else {
            this.productServices.save(product);
            FacesUtil.sucessMessage("Cadastro do produto " + product.getDescription() + " realizada com sucesso!");
            FacesUtil.redirectTo("/LojaArtesanato/Home.xhtml");
        }
        product = new Product();
    }

    public void remove() throws GenericException {
        this.productServices.delete(selectedProduct);
        FacesUtil.sucessMessage("Exclusão efetuada com sucesso!");
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
