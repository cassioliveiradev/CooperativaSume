package br.com.cassioliveira.lojaartesanato.exceptions;

import java.io.Serializable;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class GenericException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public GenericException(String message) {
        super(message);
    }

}
