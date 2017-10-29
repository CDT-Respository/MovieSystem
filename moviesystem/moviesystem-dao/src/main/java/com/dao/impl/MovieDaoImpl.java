package com.dao.impl;

import com.dao.IMovieDao;
import com.entity.Movie;
import com.entity.refund_record;
import com.entity.sales_record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SChen on 2017/10/26.
 */
@Repository("movieDao")
public class MovieDaoImpl extends BaseDao implements IMovieDao {

    //添加电影
    @Override
    public boolean addMovie(Movie movie) {
        try {
            getSession().save(movie);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //删除电影
    @Override
    public boolean deleteMovie(Movie movie) {
        try {
            getSession().delete(movie);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //查询电影列表
    @Override
    public List findMovieList(String hql) {
        return getSession().createQuery(hql).list();
    }

    //根据id查询电影信息
    @Override
    public Movie findMovieById(String mid) {
        return getSession().get(Movie.class,mid);
    }

    //添加售票信息
    @Override
    public sales_record addSalesInfo(sales_record salesinfo) {
        salesinfo.setSid((Integer) getSession().save(salesinfo));
        return salesinfo;
    }

    @Override
    public boolean addRefundInfo(refund_record refundinfo) {
        try {
            getSession().save(refundinfo);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public sales_record findSalesInfoById(int sid) {
        return getSession().get(sales_record.class,sid);
    }

    //根据mid查询售票记录
    @Override
    public List findSalesByMid(String sql) {
        return getSession().createSQLQuery(sql).list();
    }

    @Override
    public void deleteRefundInfoBySaleId(String sql) {
        try {
            getSession().createSQLQuery(sql).executeUpdate();
        }catch (Exception e){

        }
    }
}
