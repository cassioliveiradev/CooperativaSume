package br.com.cassioliveira.lojaartesanato.services;

import br.com.cassioliveira.lojaartesanato.dao.SuplierDao;
import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import br.com.cassioliveira.lojaartesanato.model.Suplier;
import br.com.cassioliveira.lojaartesanato.util.jpa.Transactional;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class SuplierServices {

    private static final Log LOGGER = LogFactory.getLog(SuplierServices.class);

    @Inject
    private SuplierDao suplierDao;

    @Transactional
    public void save(Suplier suplier) throws GenericException {
        this.suplierDao.save(suplier);
    }

    @Transactional
    public void delete(Suplier suplier) throws GenericException {
        suplier = findById(suplier.getId());
        suplierDao.delete(suplier);
    }

    public Suplier findById(Long id) {
        return suplierDao.findById(id);
    }

    public List<Suplier> findAll() {
        return suplierDao.findAll();
    }
    
    /**
     * Método responsável por carregar a lista de todas as cidades da federação 
     * através de uma consulta ao banco de dados.
     * 
     * @param codigoUF
     * @return
     */
    public List<String> returnCities(int ufCode) {
        return suplierDao.returnCities(ufCode);
    }

    /**
     * Recebe o valor passado pelo método buscarPorCampo() para determinar a
     * duplicidade do cadastro e lança uma exceção informando ao usuário qual
     * campo não pode ser inserido por já existir no banco de dados.
     *
     * @param campo
     * @param valor
     * @param id
     * @return
     */
    public boolean verificaCampoUnique(String campo, Object valor, Long id) throws GenericException {

        try {

            Suplier suplier;

            if (id == null) {
                suplier = suplierDao.buscarPorCampo(campo, valor);
                if (suplier != null) {
                    throw new GenericException("Já existe um cadastro com esse(a) " + campo.toUpperCase());
                }
            } else {
                suplier = suplierDao.buscarPorCampo(campo, valor);
                if (suplier != null && id.equals(suplier.getId())) {
                    throw new GenericException("Já existe um cadastro com esse(a) " + campo.toUpperCase());
                }
                return true;
            }
        } catch (NoResultException ex) {
            LOGGER.info("Infomação não encontrada");
        }
        return true;
    }
}
