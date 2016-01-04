package br.com.cassioliveira.lojaartesanato.dao;

import br.com.cassioliveira.lojaartesanato.model.Suplier;
import java.io.Serializable;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class SuplierDao extends AbstractDao<Suplier> implements Serializable {

    public SuplierDao() {
        super(Suplier.class);
    }
}
