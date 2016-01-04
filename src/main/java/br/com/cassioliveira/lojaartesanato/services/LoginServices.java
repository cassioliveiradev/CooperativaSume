//package br.com.cassioliveira.lojaartesanato.services;
//
//import br.com.cassioliveira.lojaartesanato.dao.LoginDao;
//import br.com.cassioliveira.lojaartesanato.exceptions.GenericException;
//import br.com.cassioliveira.lojaartesanato.model.Login;
//import br.com.cassioliveira.lojaartesanato.util.jpa.Transactional;
//import java.io.Serializable;
//import java.util.List;
//import javax.inject.Inject;
//
///**
// *
// * @author cassio
// */
//public class LoginServices implements Serializable {
//
//    @Inject
//    private LoginDao loginDao;
//
//    @Transactional
//    public void save(Login product) throws GenericException {
//        this.loginDao.save(product);
//    }
//
//    @Transactional
//    public void delete(Login product) throws GenericException {
//        loginDao.delete(findById(product.getId()));
//    }
//
//    public Login findById(Long id) {
//        return loginDao.findById(id);
//    }
//
//    public List<Login> findAll() {
//        return loginDao.findAll();
//    }
//
//}
