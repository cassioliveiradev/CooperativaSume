package br.com.cassioliveira.lojaartesanato.dao;

import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Esta classe representa um DAO genérico e contém todos os métodos básicos para
 * efetuar um CRUD.
 *
 * @author cassio
 * @param <T>
 */
public abstract class AbstractDao<T> implements Serializable {
    
    private static final Log LOGGER = LogFactory.getLog(AbstractDao.class);

    @Inject
    private EntityManager entityManager;

    private final Class<T> entity;

    /**
     * Construtor da classe que captura a entidade que chamar esta classe.
     *
     * @param entityClass
     */
    public AbstractDao(Class<T> entityClass) {
        this.entity = entityClass;
    }

    /**
     * Método get para a instância do EntityManager
     *
     * @return
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Metodo utilizado para salvar um novo cadastro no banco de dados ou editar
     * um cadastro existente.
     *
     * @param entity
     */
    public void save(T entity) {
        entityManager.merge(entity);
        
        System.out.println("################ BEAN");
    }

    /**
     * Método utilizado para remover um cadastro do banco de dados
     *
     * @param entity
     */
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    /**
     * Método utilizado para retornar uma lista com todos os resultados
     * encontrados no banco de dados para a esntidade que a chamar. A consulta é
     * feita através de Criteria
     *
     * @return
     */
    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entity));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Método utilizado para buscar um registro no banco de dados para
     * determinada entidade através da passagem do seu ID como parâmetro.
     *
     * @param id
     * @return
     */
    public T findById(Long id) {
        return entityManager.find(entity, id);
    }
    
    /**
     * Faz uma consulta no banco de dados baseado em um valor passado como
     * parâmetro e retorna o resultado da consulta.
     * 
     * @param campo
     * @param valor
     * @return 
     */
    public T buscarPorCampo(String campo, Object valor) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> createQuery = criteriaBuilder.createQuery(entity);
            Root<T> root = createQuery.from(entity);
            Predicate predicate = criteriaBuilder.conjunction();
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.<T>get(campo), valor));
            createQuery.where(predicate);

            return entityManager.createQuery(createQuery).getSingleResult();

        } catch (NoResultException ex) {
            LOGGER.info("Infomação não encontrada" + ex.getMessage());
        }
        return null;
    }

    /**
     * Recebe o valor passado pelo método buscarPorCampo() para determinar a
     * duplicidade do cadastro e lança uma exceção informando ao usuário qual
     * campo não pode ser inserido por já existir no banco de dados.
     *
     * 
     * @param campo
     * @param valor
     * @param id
     * @param entidade
     * @return 
     */
    public boolean checaCampoDuplicado(String campo, Object valor, Long id, T entidade) throws GenericException {
        try {
            if (id == null) {
                entidade = buscarPorCampo(campo, valor);
                if (entidade != null) {
                    throw new GenericException("Já existe um cadastro com esse(a) " + campo.toUpperCase());
                }
            }
        } catch (NoResultException ex) {
            LOGGER.info("Infomação não encontrada" + ex.getMessage());
        }
        return true;
    }
}
