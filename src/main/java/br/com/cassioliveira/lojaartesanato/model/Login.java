//package br.com.cassioliveira.lojaartesanato.model;
//
//import br.com.cassioliveira.lojaartesanato.enumerations.UserLevel;
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import lombok.Data;
//
///**
// *
// * @author cassio
// */
//@Entity
////@NamedQueries({
////    @NamedQuery(
////        name = "Login.find",
////        query = "SELECT l FROM Login l WHERE l.userName = :username AND l.password = :password"),
////    @NamedQuery(
////        name = "Login.list",
////        query = "SELECT l FROM Login l")
////})
//@Data
//public class Login implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    
//    @Column(name = "username", nullable = false, length = 20, unique = true)
//    private String userName;
//
//    @Column(name = "password", nullable = false, length = 30)
//    private String password;
//    
//    @Column(name = "login_level", nullable = false, length = 50)
//    @Enumerated(EnumType.STRING)
//    private UserLevel level;
//
//    @Column(name = "login_date_time_register")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateTimeRegister;
//}
