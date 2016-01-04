package br.com.cassioliveira.lojaartesanato.services;

import br.com.cassioliveira.lojaartesanato.dao.PersonDao;
import br.com.cassioliveira.lojaartesanato.model.Product;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author cassio
 */
public class PersonServices implements Serializable {

    @Inject
    private PersonDao personDao;

    public List<String> returnCities(int ufCode) {
        return personDao.returnCities(ufCode);
    }

    public List<String> returnCountries() {
        return personDao.returnCountries();
    }
    
    public List<Product> completeMethod(String query) {
        return personDao.findByNameLike(query);
    }

}
