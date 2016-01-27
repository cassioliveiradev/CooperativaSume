package br.com.cassioliveira.lojaartesanato.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author cassio
 */
@FacesConverter(value = "cnpjConverter")
public class CNPJConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        String cpf = value;
        if (value != null && !"".equals(value)) {
            cpf = value.replaceAll("\\.", "").replaceAll("\\/", "").replaceAll("\\-", "");
        }
        return cpf;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {

        String cpf = (String) value;
        if (cpf != null && cpf.length() == 11) {
            cpf = cpf.substring(0, 2) + "." + cpf.substring(2, 5) + "." + cpf.substring(5, 8) + "/" + cpf.substring(8, 12) + "-" + cpf.substring(12, 14);
        }

        return cpf;
    }
}
