package br.com.cassioliveira.lojaartesanato.converters;

import br.com.cassioliveira.lojaartesanato.services.SuplierServices;
import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import br.com.cassioliveira.lojaartesanato.model.Suplier;
import br.com.cassioliveira.lojaartesanato.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author cassio
 */
@FacesConverter(forClass = Suplier.class)
public class SuplierConverter implements Converter {

    private final SuplierServices suplierServices;

    public SuplierConverter() throws GenericException {
        this.suplierServices = CDIServiceLocator.getBean(SuplierServices.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Suplier objectToReturn = null;

        if (value != null) {
            objectToReturn = this.suplierServices.findById(new Long(value));
        }
        return objectToReturn;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {
            Long code = ((Suplier) value).getId();
            return code == null ? null : code.toString();
        }
        return "";
    }
}
