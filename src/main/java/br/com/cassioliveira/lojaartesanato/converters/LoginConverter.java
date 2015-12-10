//package br.com.cassioliveira.lojaartesanato.converters;
//
//import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
//import br.com.cassioliveira.lojaartesanato.model.Login;
//import br.com.cassioliveira.lojaartesanato.services.LoginServices;
//import br.com.cassioliveira.lojaartesanato.util.cdi.CDIServiceLocator;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//
///**
// *
// * @author cassio
// */
//@FacesConverter(forClass = Login.class)
//public class LoginConverter implements Converter {
//
//    private final LoginServices loginServices;
//
//    public LoginConverter() throws GenericException {
//        this.loginServices = CDIServiceLocator.getBean(LoginServices.class);
//    }
//
//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//
//        Login objectToReturn = null;
//
//        if (value != null) {
//            objectToReturn = this.loginServices.findById(new Long(value));
//        }
//        return objectToReturn;
//    }
//
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//
//        if (value != null) {
//            Long code = ((Login) value).getId();
//            return code == null ? null : code.toString();
//        }
//        return "";
//    }
//}
