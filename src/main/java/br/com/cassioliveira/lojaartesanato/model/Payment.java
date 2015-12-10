//package br.com.cassioliveira.lojaartesanato.model;
//
//import java.io.Serializable;
//import java.util.Date;
//import javax.annotation.PostConstruct;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import lombok.Data;
//
///**
// * Class to modelling the Payment.
// *
// * @author Cássio Oliveira
// * @author Wilde Arruda
// */
//@Entity
//@Data
//public class Payment implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "payment_value", length = 10)
//    private Double value;
//
//    @Column(name = "payment_way")
//    @Enumerated(EnumType.STRING)
//    private PaymentWay paymentWay;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "payment_date")
//    private Date payment_date;
//
//    @Column(name = "payment_increase", length = 10)
//    private Double increase;
//
//    @Column(name = "payment_discount", length = 10)
//    private Double discount;
//
//    /**
//     * This attibute register the date wich change on this class, in TIMESTAMP
//     * format
//     */
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "payment_date_time")
//    private Date dateTime;
//
//}
