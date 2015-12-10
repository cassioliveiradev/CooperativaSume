package br.com.cassioliveira.lojaartesanato.services;

import br.com.cassioliveira.lojaartesanato.dao.ClientDao;
import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import br.com.cassioliveira.lojaartesanato.model.Client;
import br.com.cassioliveira.lojaartesanato.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author cassio
 */
public class ClientServices implements Serializable {

    @Inject
    private ClientDao clientDao;

    @Transactional
    public void save(Client client) throws GenericException {
        this.clientDao.save(client);
    }
    
    @Transactional
    public void delete(Client client) throws GenericException {
        clientDao.delete(findById(client.getId()));
    }

    public Client findById(Long id) {
        return clientDao.findById(id);
    }

    public List<Client> findAll() {
        return clientDao.findAll();
    }

    public List<Client> completeMethod(String query) {
        return clientDao.findByNameLike(query);
    }

    public List<Client> hostedNames(){
        return clientDao.hostedNames();
    }
}
