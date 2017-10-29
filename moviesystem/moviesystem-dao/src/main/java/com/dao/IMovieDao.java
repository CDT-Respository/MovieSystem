package com.dao;

import com.entity.Movie;
import com.entity.refund_record;
import com.entity.sales_record;

import java.util.List;

/**
 * Created by SChen on 2017/10/26.
 */
public interface IMovieDao {
    //添加电影
    public boolean addMovie(Movie movie);
    //删除电影
    public boolean deleteMovie(Movie movie);
    //查询电影列表
    public List findMovieList(String hql);
    //根据id查询电影信息
    public Movie findMovieById(String mid);
    //添加售票信息
    public sales_record addSalesInfo(sales_record salesinfo);
    //添加退票信息
    public boolean addRefundInfo(refund_record refundinfo);
    //根据id查询售票信息
    public sales_record findSalesInfoById(int sid);
    //根据电影的id查询出对应的销售记录
    public  List findSalesByMid(String sql);
    //根据售票记录的id查询退票记录
    public void deleteRefundInfoBySaleId(String sql);
}
