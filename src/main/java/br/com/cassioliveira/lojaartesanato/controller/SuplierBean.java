package br.com.cassioliveira.lojaartesanato.controller;

import br.com.cassioliveira.lojaartesanato.enumerations.States;
import br.com.cassioliveira.lojaartesanato.enumerations.Unit;
import br.com.cassioliveira.lojaartesanato.services.SuplierServices;
import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import br.com.cassioliveira.lojaartesanato.model.Suplier;
import br.com.cassioliveira.lojaartesanato.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;

/**
 *
 * @author cassio
 */
@Model
public class SuplierBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @Getter
    @Setter
    private SuplierServices suplierServices;

    @Inject
    @Getter
    @Setter
    private Suplier suplier;

    @Inject
    @Getter
    @Setter
    private Suplier selectedSuplier;

    @Getter
    private List<Suplier> supliers;
    
    @Getter
    private List<Suplier> supliersByUsers;

    @Getter
    private final List<Unit> units;

    @Getter
    private transient List<States> uf = new ArrayList<>();

    @Getter
    private final transient List<String> cities = new ArrayList<>();
    
    private final String LOGGED_USER = (String) SecurityUtils.getSubject().getPrincipal();

    public SuplierBean() {
        units = Arrays.asList(Unit.values());
        uf = Arrays.asList(States.values());
    }

    @PostConstruct
    public void init() {
        this.supliersByUsers = suplierServices.findByUser(LOGGED_USER);
        this.supliers = suplierServices.findAll();
    }

    public void save() throws GenericException {
        this.suplierServices.save(suplier);
        System.out.println("################ BEAN");
        if (getEditing()) {
            FacesUtil.sucessMessage("Cadastro do fornecedor '" + suplier.getName() + "' atualizado com sucesso!");
        } else {
            FacesUtil.sucessMessage("Cadastro do fornecedor '" + suplier.getName() + "' realizada com sucesso!");
        }
        FacesUtil.redirectTo("PesquisaFornecedor.xhtml");
        suplier = new Suplier();
    }

    public void remove() throws GenericException {
        this.suplierServices.delete(selectedSuplier);
        FacesUtil.sucessMessage("Exclusão efetuada com sucesso!");
    }

    /**
     * Metodo que verifica se o objeto esta nulo quando for recuperado. Se sim,
     * refere-se a um novo cadastro, senao refere-se a um fornecedor a ser
     * editado
     *
     * @return
     */
    public boolean getEditing() {
        return this.suplier.getId() != null;
    }

    /**
     * Método responsável por carregar uma lista com todas as cidades
     * cadastradas. Esta lista será usada para preencher o respectivo campo de
     * cidade na view.
     */
    public void loadCities() {
        this.cities.clear();
        if (suplier.getUf() != null) {
            for (String cidadesFiltradas : suplierServices.returnCities(suplier.getUf().getCode())) {
                this.cities.add(cidadesFiltradas);
            }
        }
    }
}
