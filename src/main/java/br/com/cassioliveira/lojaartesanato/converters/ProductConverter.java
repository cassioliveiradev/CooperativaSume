package br.com.cassioliveira.lojaartesanato.converters;

import br.com.cassioliveira.lojaartesanato.services.ProductServices;
import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import br.com.cassioliveira.lojaartesanato.model.Product;
import br.com.cassioliveira.lojaartesanato.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author cassio
 */
@FacesConverter(forClass = Product.class)
public class ProductConverter implements Converter {

    private final ProductServices productServices;

    public ProductConverter() throws GenericException {
        this.productServices = CDIServiceLocator.getBean(ProductServices.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Product objectToReturn = null;

        if (value != null) {
            objectToReturn = this.productServices.findById(new Long(value));
        }
        return objectToReturn;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {
            Long code = ((Product) value).getId();
            return code == null ? null : code.toString();
        }
        return "";
    }
}
