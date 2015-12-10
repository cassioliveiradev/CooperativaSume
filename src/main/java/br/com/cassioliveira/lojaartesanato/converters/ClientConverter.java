package br.com.cassioliveira.lojaartesanato.converters;

import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import br.com.cassioliveira.lojaartesanato.model.Client;
import br.com.cassioliveira.lojaartesanato.services.ClientServices;
import br.com.cassioliveira.lojaartesanato.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author cassio
 */
@FacesConverter(forClass = Client.class)
public class ClientConverter implements Converter {

    private final ClientServices clientServices;

    public ClientConverter() throws GenericException {
        this.clientServices = CDIServiceLocator.getBean(ClientServices.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Client objectToReturn = null;

        if (value != null) {
            objectToReturn = this.clientServices.findById(new Long(value));
        }
        return objectToReturn;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {
            Long code = ((Client) value).getId();
            return code == null ? null : code.toString();
        }
        return "";
    }
}
