package com.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by SChen on 2017/10/26.
 * 售票记录表
 */
@Entity
@Table(name="sales_record")
public class sales_record implements Serializable{

    private int sid;        //售票id
    private int sprice;     //实收价格
    private Date sales_date;//销售日期

    private Movie movie;    //电影

    public sales_record() {
    }

    public sales_record(int sid, int sprice, Date sales_date) {
        this.sid = sid;
        this.sprice = sprice;
        this.sales_date = sales_date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSprice() {
        return sprice;
    }

    public void setSprice(int sprice) {
        this.sprice = sprice;
    }

    public Date getSales_date() {
        return sales_date;
    }

    public void setSales_date(Date sales_date) {
        this.sales_date = sales_date;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "smid")
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


}
