package br.com.cassioliveira.lojaartesanato.dao;

import br.com.cassioliveira.lojaartesanato.model.Login;
import java.io.Serializable;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class LoginDao extends AbstractDao<Login> implements Serializable {

    public LoginDao() {
        super(Login.class);
    }
}
