package com.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by SChen on 2017/10/26.
 * 退票记录表
 */
@Entity
@Table(name="refund_record")
public class refund_record implements Serializable{

    private int rid;            //退票记录id
    private int refund_price;  //退还金额

    private sales_record sales_no;

    public refund_record() {
    }

    public refund_record(int rid, int refund_price) {
        this.rid = rid;
        this.refund_price = refund_price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getRefund_price() {
        return refund_price;
    }

    public void setRefund_price(int refund_price) {
        this.refund_price = refund_price;
    }

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="sid")
    public sales_record getSales_no() {
        return sales_no;
    }

    public void setSales_no(sales_record sales_no) {
        this.sales_no = sales_no;
    }
}
