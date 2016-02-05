package br.com.cassioliveira.lojaartesanato.dao;

import br.com.cassioliveira.lojaartesanato.model.Suplier;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class SuplierDao extends AbstractDao<Suplier> implements Serializable {

    public SuplierDao() {
        super(Suplier.class);
    }
    
    public List<Suplier> findByUser(String user) {
        Query createQuery;
        createQuery = getEntityManager().createQuery("FROM Suplier s WHERE s.userName = :userName", Suplier.class);
        createQuery.setParameter("userName", user);
        return createQuery.getResultList();
    }
    
//    public List<Suplier> findByUser(String user) {
//        return getEntityManager().createNamedQuery("Suplier.registeredByUser", Suplier.class).setParameter("userName", user).getResultList();
//    }

    /**
     * Método responsável por retornar a lista de cidades brasileiras. Carrega a
     * lista de cidades através de uma consulta personalizada.
     *
     * @param ufCode
     * @return
     */
    public List<String> returnCities(int ufCode) {
        Query createQuery;
        createQuery = getEntityManager().createNativeQuery("SELECT c.nome FROM cidades c where c.estado = " + ufCode);
        return createQuery.getResultList();
    }
}
