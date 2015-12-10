package br.com.cassioliveira.lojaartesanato.dao;

import br.com.cassioliveira.lojaartesanato.model.Client;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class ClientDao extends AbstractDao<Client> implements Serializable {

    public ClientDao() {
        super(Client.class);
    }
    
    public List<Client> hostedNames(){
        Query createQuery;

        createQuery = getEntityManager().createQuery("SELECT i.name FROM Client AS i, Hosting AS h WHERE h.clientFK=i.id");
        return createQuery.getResultList();
    }

    public List<Client> findByNameLike(String query) {

        Query createQuery;

//        if (StringUtils.isNotBlank(query)) {
        createQuery = getEntityManager().createQuery("from Client c where c.name like :name");
        createQuery.setParameter("name", "%" + query + "%");
//        }
        return createQuery.getResultList();
    }
}
