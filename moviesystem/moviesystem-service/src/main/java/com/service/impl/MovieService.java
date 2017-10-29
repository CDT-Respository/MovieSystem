package com.service.impl;

import com.dao.IMovieDao;
import com.entity.Movie;
import com.entity.refund_record;
import com.entity.sales_record;
import com.service.IMovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SChen on 2017/10/26.
 */
@Service("movieService")
@Transactional(propagation = Propagation.REQUIRED)
public class MovieService implements IMovieService{

    @Resource(name="movieDao")
    private IMovieDao movieDao;

    //添加电影
    @Override
    public boolean addMovie(Movie movie) {
        return movieDao.addMovie(movie);
    }

    //删除电影(同时删掉对应的记录)
    @Override
    public boolean deleteMovie(String mid) {
        //查出这部电影的信息
        Movie movie=movieDao.findMovieById(mid);
        //查出这部电影相关的售票信息
        List saleList=movieDao.findSalesByMid("select s.sid from tb_movie m,sales_record s where m.mid='"+movie.getMid()+"'");

        //删除退票信息
        for (int i=0;i<saleList.size();i++){
            movieDao.deleteRefundInfoBySaleId("delete from refund_record where sid="+saleList.get(i));
        }
        System.out.print(1);
        //删除售票信息
        for (int i=0;i<saleList.size();i++){
            movieDao.deleteRefundInfoBySaleId("delete from sales_record where sid="+saleList.get(i));
        }
        System.out.print(2);
        //删除电影
        return movieDao.deleteMovie(movie);
    }

    //查询电影列表
    @Override
    public List findMovieList(String res) {
        String hql="select m,count(s.sid),count(r.rid),m.pub_count-count(s.sid)+count(r.rid) " +
                "from Movie m left join sales_record s on m.mid=s.movie.mid " +
                "left join refund_record r on s.sid= r.sales_no.sid " +
                "group by m.mid";
        if(res==null){
            return movieDao.findMovieList(hql);
        }else{
            hql="select m,count(s.sid),count(r.rid),m.pub_count-count(s.sid)+count(r.rid) " +
                "from Movie m left join sales_record s on m.mid=s.movie.mid " +
                "left join refund_record r on s.sid= r.sales_no.sid " +
                "where (1=1 and m.name_cn like '%"+res+"%') or " +
                "(1=1 and m.name_en like '%"+res+"%') " +
                "group by m.mid";
        }
        return movieDao.findMovieList(hql);
    }

    //根据id查询电影
    @Override
    public Movie findMovieById(String mid) {
        return movieDao.findMovieById(mid);
    }

    //添加售票信息
    @Override
    public sales_record addSalesInfo(sales_record salesinfo, String mid) {
        //查出电影信息
        Movie movie=movieDao.findMovieById(mid);
        //把电影放入售票信息中
        salesinfo.setMovie(movie);
        //添加售票信息并返回售票信息
        return movieDao.addSalesInfo(salesinfo);
    }

    //添加退票记录
    @Override
    public boolean addRefundInfo(refund_record refundinfo,int sid) {
        //查出售票信息
        sales_record salesinfo=movieDao.findSalesInfoById(sid);
        //根据售票信息查询电影票价
        int price= (int) salesinfo.getMovie().getPrice();
        price= (int) (price*0.8);
        //把售票信息放入退票记录里
        refundinfo.setSales_no(salesinfo);
        refundinfo.setRefund_price(price);
        //添加退票记录
        return movieDao.addRefundInfo(refundinfo);
    }

    public void setMovieDao(IMovieDao movieDao) {
        this.movieDao = movieDao;
    }
}
