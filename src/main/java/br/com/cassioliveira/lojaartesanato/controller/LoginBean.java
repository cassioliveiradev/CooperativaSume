package br.com.cassioliveira.lojaartesanato.controller;

import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import br.com.cassioliveira.lojaartesanato.model.Login;
import br.com.cassioliveira.lojaartesanato.services.LoginServices;
import br.com.cassioliveira.lojaartesanato.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author cassio
 */
@Model
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @Getter
    @Setter
    private LoginServices loginServices;

    @Inject
    @Getter
    @Setter
    private Login login;

    @Inject
    @Getter
    @Setter
    private Login selectedLogin;

//    @Getter
//    private final String loggedUser;

    private List<Login> logins;

    public LoginBean() {
//        this.loggedUser = (String) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * Chama um metodo do Service que faz a comunicaçao com a camada de
     * persistencia para salvar as alteraçoes feitas em um cadastro existente ou
     * salvar os dados de um novo cadastro.
     *
     * @throws GenericException
     */
    public void save() throws GenericException {
        this.loginServices.save(login);
        if (getEditing()) {
            FacesUtil.sucessMessage("Cadastro de '" + login.getUserName() + "' atualizado com sucesso!");
            FacesUtil.redirectTo("SearchLogin.xhtml");
        } else {
            FacesUtil.sucessMessage("Cadastro efetuado com sucesso!");
            FacesUtil.redirectTo("CadastreLogin.xhtml");
        }
        login = new Login();
    }

    /**
     * Chama um metodo do Service que faz a comunicaçao com a camada de
     * persistencia para excluir um cadastro existente.
     *
     * @throws GenericException
     */
    public void remove() throws GenericException {
        this.loginServices.delete(selectedLogin);
        FacesUtil.sucessMessage("Exclusão efetuada com sucesso!");
    }

    /**
     * Metodo que verifica se o objeto esta nulo quando for recuperado. Se sim,
     * refere-se a um novo cadastro, senao refere-se a um produto a ser editado
     *
     * @return
     */
    public boolean getEditing() {
        return this.login.getId() != null;
    }

    /**
     * @return the logins
     */
    public List<Login> getLogins() {
        this.logins = loginServices.findAll();
        return logins;
    }

    /**
     * Captura o usuário logado e finaliza a sessão do mesmo.
     *
     * @return
     */
    public String getLogout() {
        SecurityUtils.getSubject().logout();
        return "/Login.xhtml?faces-redirect=true";
    }

    public void loginUser() {

        try {

            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(login.getUserName(), new Sha256Hash(login.getPassword()).toHex());

            currentUser.login(token);

        } catch (UnknownAccountException uae) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", "Usuário incorreto"));

        } catch (IncorrectCredentialsException ice) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", "Senha incorreta"));

        } catch (LockedAccountException lae) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", "Usuário está bloqueado"));

        } catch (AuthenticationException aex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", aex.toString()));
        }

    }

    public void authorizedUserControl() {

        if (null != SecurityUtils.getSubject().getPrincipal()) {

            NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
            nh.handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml?faces-redirect=true");

        }
    }
}
