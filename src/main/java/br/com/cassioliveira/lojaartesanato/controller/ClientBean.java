package br.com.cassioliveira.lojaartesanato.controller;

import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import br.com.cassioliveira.lojaartesanato.model.Client;
import br.com.cassioliveira.lojaartesanato.services.ClientServices;
import br.com.cassioliveira.lojaartesanato.services.PersonServices;
import br.com.cassioliveira.lojaartesanato.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author cassio
 */
@Model
public class ClientBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @Getter
    @Setter
    private ClientServices clientServices;

    @Inject
    private PersonServices personServices;

    @Inject
    @Getter
    @Setter
    private Client client;

    @Inject
    @Getter
    @Setter
    private Client selectedClient;

    private List<Client> clients;

    public void save() throws GenericException {
        this.clientServices.save(client);
        if (getEditing()) {
            FacesUtil.sucessMessage("Cadastro de '" + client.getName() + "' atualizado com sucesso!");
            FacesUtil.redirectTo("SearchClient.xhtml");
        } else {
            FacesUtil.sucessMessage("Cadastro efetuado com sucesso!");
            FacesUtil.redirectTo("CadastreClient.xhtml");
        }
        client = new Client();
    }

    public void remove() throws GenericException {
        this.clientServices.delete(selectedClient);
        FacesUtil.sucessMessage("Exclusão efetuada com sucesso!");
    }

    public void loadCities() {
        PersonBean.cities.clear();
        if (client.getUf() != null) {
            for (String filteredCities : personServices.returnCities(client.getUf().getCode())) {
                PersonBean.cities.add(filteredCities);
            }
        }
    }

    /*
     * Metodo que verifica se o objeto esta nulo quando for recuperado.
     * Se sim, refere-se a um novo cadastro, senao refere-se a um produto a ser editado
     */
    public boolean getEditing() {
        return this.client.getId() != null;
    }

    /**
     * @return the clients
     */
    public List<Client> getClients() {
        this.clients = clientServices.findAll();
        return clients;
    }
}
