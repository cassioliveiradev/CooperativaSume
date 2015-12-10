//package br.com.cassioliveira.lojaartesanato.controller;
//
//import br.com.cassioliveira.lojaartesanato.enumerations.UserLevel;
//import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
//import br.com.cassioliveira.lojaartesanato.model.Login;
//import br.com.cassioliveira.lojaartesanato.services.LoginServices;
//import br.com.cassioliveira.lojaartesanato.util.jsf.FacesUtil;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.Arrays;
//import java.util.List;
//import javax.enterprise.inject.Model;
//import javax.inject.Inject;
//import lombok.Getter;
//import lombok.Setter;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.web.util.SavedRequest;
//import org.apache.shiro.web.util.WebUtils;
//import org.omnifaces.util.Faces;
//
///**
// *
// * @author cassio
// */
//@Model
//public class LoginBean implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Inject
//    @Getter
//    @Setter
//    private LoginServices loginServices;
//
//    @Inject
//    @Getter
//    @Setter
//    private Login login;
//
//    @Inject
//    @Getter
//    @Setter
//    private Login selectedLogin;
//
//    @Getter
//    private final String loggedUser;
//
//    private List<Login> logins;
//
//    @Getter
//    private final List<UserLevel> userLevels;
//
//    public LoginBean() {
//        this.userLevels = Arrays.asList(UserLevel.values());
//        this.loggedUser = (String) SecurityUtils.getSubject().getPrincipal();
//    }
//
//    /**
//     * Chama um metodo do Service que faz a comunicaçao com a camada de
//     * persistencia para salvar as alteraçoes feitas em um cadastro existente ou
//     * salvar os dados de um novo cadastro.
//     *
//     * @throws GenericException
//     */
//    public void save() throws GenericException {
//        this.loginServices.save(login);
//        if (getEditing()) {
//            FacesUtil.sucessMessage("Cadastro de '" + login.getUserName() + "' atualizado com sucesso!");
//            FacesUtil.redirectTo("SearchLogin.xhtml");
//        } else {
//            FacesUtil.sucessMessage("Cadastro efetuado com sucesso!");
//            FacesUtil.redirectTo("CadastreLogin.xhtml");
//        }
//        login = new Login();
//    }
//
//    /**
//     * Chama um metodo do Service que faz a comunicaçao com a camada de
//     * persistencia para excluir um cadastro existente.
//     *
//     * @throws GenericException
//     */
//    public void remove() throws GenericException {
//        this.loginServices.delete(selectedLogin);
//        FacesUtil.sucessMessage("Exclusão efetuada com sucesso!");
//    }
//
//    /**
//     * Metodo que verifica se o objeto esta nulo quando for recuperado. Se sim,
//     * refere-se a um novo cadastro, senao refere-se a um produto a ser editado
//     * @return 
//     */
//    public boolean getEditing() {
//        return this.login.getId() != null;
//    }
//
//    /**
//     * @return the logins
//     */
//    public List<Login> getLogins() {
//        this.logins = loginServices.findAll();
//        return logins;
//    }
//
//    /**
//     * Metodo que pega o usuário do arquivo de configurações do Shiro,
//     * atualmente, e compara os valores passados pelo usuario na tela de login
//     * com os armazenados. Em caso de falha, o usuario eh notificado. Em caso de
//     * sucesso, o usuario e direcionado para a tela inicial do sistema.
//     *
//     * @throws IOException
//     */
//    public void login() throws IOException {
//        try {
//            SecurityUtils.getSubject().login(new UsernamePasswordToken(login.getUserName(), login.getPassword()));
//            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
//            Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : "/HotelSys/Home.xhtml");
//        } catch (AuthenticationException e) {
//            FacesUtil.errorMessage("Falha no login!");
//            e.printStackTrace(); // TODO: logger.
//        }
//    }
//
//    /**
//     * Captura o usuário logado e finaliza a sessão do mesmo.
//     *
//     * @return
//     */
//    public String logout() {
//        SecurityUtils.getSubject().logout();
//        return "/Login.xhtml?faces-redirect=true";
//    }
//}
