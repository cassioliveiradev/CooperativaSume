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
    private final List<Unit> units;
    
    @Getter
    private static transient List<States> uf = new ArrayList<>();

    public SuplierBean() {
        units = Arrays.asList(Unit.values());
        uf = Arrays.asList(States.values());
    }

    @PostConstruct
    public void init() {
        this.supliers = suplierServices.findAll();
    }

    public void save() throws GenericException {
        this.suplierServices.save(suplier);
        if (getEditing()) {
            FacesUtil.sucessMessage("Cadastro do fornecedor '" + suplier.getName() + "' atualizado com sucesso!");
            FacesUtil.redirectTo("PesquisaFornecedor.xhtml");
        } else {
            FacesUtil.sucessMessage("Cadastro do fornecedor " + suplier.getName() + " realizada com sucesso!");
            FacesUtil.redirectTo("/LojaArtesanato/Home.xhtml");
        }
        suplier = new Suplier();
    }

    public void remove() throws GenericException {
        this.suplierServices.delete(selectedSuplier);
        FacesUtil.sucessMessage("Exclusão efetuada com sucesso!");
    }

    /**
     * Metodo que verifica se o objeto esta nulo quando for recuperado. Se sim,
     * refere-se a um novo cadastro, senao refere-se a um fornecedor a ser editado
     *
     * @return
     */
    public boolean getEditing() {
        return this.suplier.getId() != null;
    }

}
