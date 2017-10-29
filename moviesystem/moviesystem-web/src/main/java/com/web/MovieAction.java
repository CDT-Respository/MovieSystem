package com.web;

import com.entity.Movie;
import com.entity.refund_record;
import com.entity.sales_record;
import com.opensymphony.xwork2.ActionSupport;
import com.service.IMovieService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by SChen on 2017/10/26.
 */
@Controller("movieAction")
@Scope("prototype")
public class MovieAction extends ActionSupport{
    private Movie movie;
    private String res;         //查询条件
    private int msg;            //返回结果
    private sales_record salesinfo;
    private refund_record refundinfo;
    private List movieList;


    @Resource(name="movieService")
    private IMovieService movieService;

    //添加售票记录
    public String saleMovie(){
        salesinfo.setSales_date(new Date());
        salesinfo=movieService.addSalesInfo(salesinfo,salesinfo.getMovie().getMid());
        return "endSaleMovie";
    }

    //添加退票记录
    public String refundMovie(){
        if(movieService.addRefundInfo(refundinfo,refundinfo.getSales_no().getSid())){
            msg=1;
        }else{
            msg=2;
        }
        return "endRefundMovie";
    }

    //删除电影
    public String deleteMovie(){
        movieService.deleteMovie(movie.getMid());
        return "endDeleteMovie";
    }

    public String addMovie(){
        if(this.hasErrors()){
            return INPUT;
        }
        if(movieService.addMovie(movie)){
            msg=1;
        }else{
            msg=2;
        }
        return "endAddMovie";
    }

    //查询所有电影(包括模糊查询）
    public String findMovieList(){
        movieList=movieService.findMovieList(res);
        return "movieList";
    }

    //根据id查询电影
    public String findMovieById(){
        movie=movieService.findMovieById(movie.getMid());
        return "onemovie";
    }

    public void validateAddMovie() {
        //电影名字不能为空
        if(null==movie.getName_cn() || "".equals(movie.getName_cn())){
            this.addFieldError("movie.name_cn", "请输入电影的中文名字!");
        }
        //正确的日期格式
        String res_data="^\\d{4}-\\d{1,2}-\\d{1,2}$";
        if(movie.getPub_date().toString().matches(res_data)){
            this.addFieldError("movie.data", "请输入正确的日期格式!（yyyy-MM-dd）");
        }
        //正确的价格格式
        String res_price="^\\d$";
        if((movie.getPrice()+"").matches(res_price)){
            this.addFieldError("movie.price", "请输入正确的价格格式!");
        }
        //正确的整数
        String res_count="^\\d$";
        if((movie.getPub_count()+"").matches(res_count)){
            this.addFieldError("movie.count", "请输入正确的整数!");
        }

    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public List getMovieList() {
        return movieList;
    }

    public void setMovieList(List movieList) {
        this.movieList = movieList;
    }

    public void setMovieService(IMovieService movieService) {
        this.movieService = movieService;
    }

    public sales_record getSalesinfo() {
        return salesinfo;
    }

    public void setSalesinfo(sales_record salesinfo) {
        this.salesinfo = salesinfo;
    }

    public refund_record getRefundinfo() {
        return refundinfo;
    }

    public void setRefundinfo(refund_record refundinfo) {
        this.refundinfo = refundinfo;
    }
}
