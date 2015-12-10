package br.com.cassioliveira.lojaartesanato.controller;

import java.io.Serializable;
import javax.enterprise.inject.Model;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author cassio
 */
@Model
public class GenericBean implements Serializable {

    @Getter
    @Setter
    private String text;

    /**
     * Este método é um pequeno utilitário para gerar um hash do tipo SHA-256 de
     * um texto qualquer, porém é mais usado em senhas.
     *
     * @param string
     */
//    public void getHashText(String string) {
//        Sha256Hash sha256Hash = new Sha256Hash(this.text);
//        this.text = sha256Hash.toHex();
//    }

}
