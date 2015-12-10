package br.com.cassioliveira.lojaartesanato.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.enterprise.inject.Model;

/**
 *
 * @author cassio
 */
@Model
public class DateTimeUtilBean implements Serializable {

    private Date date = new Date();
    Calendar calendar = new GregorianCalendar();

    public DateTimeUtilBean() {

    }

    public Date getDateToday() {
        return new Date();
    }

    public String getCurrentMonthAsString() {
        int monthAsInt;
        String monthAsString = "";

        //Returns the month in numbers by range 0-11
        monthAsInt = this.calendar.get(Calendar.MONTH);

        switch (monthAsInt) {
            case 0:
                monthAsString = "Janeiro";
                break;
            case 1:
                monthAsString = "Fevereiro";
                break;
            case 2:
                monthAsString = "Março";
                break;
            case 3:
                monthAsString = "Abril";
                break;
            case 4:
                monthAsString = "Maio";
                break;
            case 5:
                monthAsString = "Junho";
                break;
            case 6:
                monthAsString = "Julho";
                break;
            case 7:
                monthAsString = "Agosto";
                break;
            case 8:
                monthAsString = "Setembro";
                break;
            case 9:
                monthAsString = "Outubro";
                break;
            case 10:
                monthAsString = "Novembro";
                break;
            case 11:
                monthAsString = "Dezembro";
                break;
            default:
                break;
        }
        return monthAsString;
    }

    public Date dateHour() {
        this.date = new Timestamp(date.getTime());
        return date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
