package com.service;

import com.entity.Movie;
import com.entity.refund_record;
import com.entity.sales_record;

import java.util.List;

/**
 * Created by SChen on 2017/10/26.
 */
public interface IMovieService {
    //添加电影的方法
    public boolean addMovie(Movie movie);

    //删除电影的方法
    public boolean deleteMovie(String mid);

    //查询电影列表（包括模糊查询
    public List findMovieList(String res);

    //根据id查询电影
    public Movie findMovieById(String mid);

    //添加售票信息
    public sales_record addSalesInfo(sales_record salesinfo,String mid);

    //添加退票信息
    public boolean addRefundInfo(refund_record refundinfo,int sid);
}
