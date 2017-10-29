package com.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by SChen on 2017/10/26.
 * 电影票
 */
@Entity
@Table(name="tb_movie")
public class Movie implements Serializable{

    private String mid;                //电影ID
    private String name_cn;         //中文名
    private String name_en;         //英文名
    private double price;           //票价
    private Date pub_date;          //发布日期
    private String movie_type;      //电影类型
    private String country;         //国家
    private int pub_count;          //发行票数


    public Movie() {
    }

    public Movie(String mid, String name_cn, String name_en, double price, Date pub_date, String movie_type, String country, int pub_count) {
        this.mid = mid;
        this.name_cn = name_cn;
        this.name_en = name_en;
        this.price = price;
        this.pub_date = pub_date;
        this.movie_type = movie_type;
        this.country = country;
        this.pub_count = pub_count;
    }

    @Id
    @GenericGenerator(name="systemUUID",strategy="uuid")
    @GeneratedValue(generator="systemUUID")
    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getName_cn() {
        return name_cn;
    }

    public void setName_cn(String name_cn) {
        this.name_cn = name_cn;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(columnDefinition = "date")
    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }

    public String getMovie_type() {
        return movie_type;
    }

    public void setMovie_type(String movie_type) {
        this.movie_type = movie_type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPub_count() {
        return pub_count;
    }

    public void setPub_count(int pub_count) {
        this.pub_count = pub_count;
    }

}
