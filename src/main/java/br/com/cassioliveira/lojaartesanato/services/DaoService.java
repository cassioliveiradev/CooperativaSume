package br.com.cassioliveira.lojaartesanato.services;

import br.com.cassioliveira.lojaartesanato.dao.AbstractDao;
import br.com.cassioliveira.lojaartesanato.util.jpa.Transactional;

/**
 * Esta classe representa o DaoService. 
 * 
 * @author cassio <cassio@cassioliveira.com.br>
 * @param <T>
 */
public class DaoService<T> extends AbstractDao<T> {

    public AbstractDao getDao;

    public DaoService(Class<T> entityClass) {
        super(entityClass);
    }

    /**
     * Faz uma consulta no banco de dados baseado em um valor passado como
     * parâmetro e retorna o resultado da consulta.
     * 
     * @param campo
     * @param valor
     * @return 
     */
    @Transactional
    @Override
    public T buscarPorCampo(String campo, Object valor) {
        return (T) getDao.buscarPorCampo(campo, valor);
    }

}
