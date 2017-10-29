<%--
  Created by IntelliJ IDEA.
  User: SChen
  Date: 2017/10/26
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>mian</title>
</head>
<script src="js/jquery-3.2.1.min.js"></script>
<style>
    #salediv,#refunddiv{
        background-color: aqua;
        width:300px;
        height:220px;
        position: absolute;
        left: 40%;
        top: 40%;
        display: none;
    }
</style>
<body>
    <div id="refunddiv">
        <h2 style="text-align:center;">退票</h2>
        <div style="color:black;background-color:pink;width:40px;height: 30px;position: relative;left: 259px;top: -70px;" onclick="colseRefundDiv()">关闭</div>
        <form id="refundFrm">
            <input name="refundinfo.sales_no.sid"><br/>
            <input type="button" onclick="doRefundMovie()" value="确认退票"/>
        </form>
        <h4 style="text-align:center;">注意：退票只能退还原实收金额的80%</h4>
    </div>

    <div id="salediv">
        <h2 style="text-align:center;">售票</h2>
        <div style="color:black;background-color:pink;width:40px;height: 30px;position: relative;left: 259px;top: -70px;" onclick="closeSaleDiv()">关闭</div>
        <h2 id="salemovie_name"></h2>
        <h2 id="salemovie_price"></h2>
        <form id="saleFrm">
            <input type="hidden" name="salesinfo.movie.mid"/>
            <input name="salesinfo.sprice"/><br/>
            <input type="button" onclick="doSaleMovie()" value="确定"/>
        </form>
    </div>
    <input name="movie.name_cn"/><input onclick="selectMovieByName()" type="button" value="查询"/>
    <table id="mytab" border="1">
        <tr>
            <td>上映日期</td>
            <td>中文名</td>
            <td>英文名</td>
            <td>类型</td>
            <td>国家</td>
            <td>票数</td>
            <td>已卖</td>
            <td>已退</td>
            <td>剩余</td>
            <td>操作</td>
        </tr>
    </table>
<br/>
<a href="index.jsp">添加新电影</a><a href="javascript:openRefundDiv()">退票</a>
</body>
</html>
<script>
    function pageonload() {
        $("#mytab tr:not(:first)").remove();
        var moviename=$("input[name='movie.name_cn']").val();
        $.post("movieAction_findMovieList","res="+moviename,function (data) {
            $.each(data.movieList,function (i,v) {
                var tr="<tr>";
                    tr+="<td>"+v[0].pub_date+"</td>";
                    tr+="<td>"+v[0].name_cn+"</td>";
                    tr+="<td>"+v[0].name_en+"</td>";
                    tr+="<td>"+v[0].movie_type+"</td>";
                    tr+="<td>"+v[0].country+"</td>";
                    tr+="<td>"+v[0].pub_count+"</td>";
                    tr+="<td>"+v[1]+"</td>";
                    tr+="<td>"+v[2]+"</td>";
                    tr+="<td>"+v[3]+"</td>";
                    tr+="<td>" +
                        "<button onclick='javascript:openSaleDiv(\""+v[0].mid+"\")'>售票</button>" +
                        "<a href='movieAction_deleteMovie?movie.mid="+v[0].mid+"'>删除</a>" +
                        "</td>";
                    tr+="</tr>";
                    $("#mytab").append(tr);
            });
        },"json");
    }
    //查询数据并打开售票div
    function openSaleDiv(mid){
        //打开前先查询数据
        $.post("movieAction_findMovieById","movie.mid="+mid,function (data) {
            $("#salediv").show(300);
            //绑定值
            $("#salemovie_name").text("电影:"+data.movie.name_cn);
            $("#salemovie_price").text("价格"+data.movie.price);
            $("input[name='salesinfo.movie.mid']").val(data.movie.mid);
        });
    }

    //关闭售票窗口
    function closeSaleDiv(){
        $("#salediv").hide(300);
    }

    //模糊查询电影
    function selectMovieByName(){
        var moviename=$("input[name='movie.name_cn']").val();
        $("#mytab tr:not(:first)").remove();
        $.post("movieAction_findMovieList","res="+moviename,function (data) {
            $.each(data.movieList,function (i,v) {
                var tr="<tr>";
                tr+="<td>"+v[0].pub_date+"</td>";
                tr+="<td>"+v[0].name_cn+"</td>";
                tr+="<td>"+v[0].name_en+"</td>";
                tr+="<td>"+v[0].movie_type+"</td>";
                tr+="<td>"+v[0].country+"</td>";
                tr+="<td>"+v[0].pub_count+"</td>";
                tr+="<td>"+v[1]+"</td>";
                tr+="<td>"+v[2]+"</td>";
                tr+="<td>"+v[3]+"</td>";
                tr+="<td>" +
                    "<button onclick='javascript:openSaleDiv(\""+v[0].mid+"\")'>售票</button>" +
                    "<a href='movieAction_deleteMovie?movie.mid="+v[0].mid+"'>删除</a>" +
                    "</td>";
                tr+="</tr>";
                $("#mytab").append(tr);
            });
        },"json");
    }

    //售票
    function doSaleMovie(){
        var param=$("#saleFrm").serialize();
        $.post("movieAction_saleMovie",param,function (data) {
            $("#salediv").html("<h2 style='text-align:center;'>成功</h2>" +
            "<div style='color:black;background-color:pink;width:40px;height: 30px;position: relative;left: 259px;top: -70px;' onclick='closeSaleDiv()'>关闭</div>" +
            "<h2>电影："+data.salesinfo.movie.name_cn+"</h2>" +
            "<h2>票号："+data.salesinfo.sid+"</h2>");
        },"json");
    }

    //退票
    function doRefundMovie(){
        var param=$("#refundFrm").serialize();
        $.post("movieAction_refundMovie",param,function (data) {
            if(data.msg==1){
                $("#refunddiv").html("<h2 style='text-align:center;'>" +
                    "退票</h2><div style='color:black;background-color:pink;" +
                    "width:40px;height: 30px;position: " +
                    "relative;left: 259px;top: -70px;' onclick='colseRefundDiv()'>" +
                    "关闭</div>" +
                    "<h2 style='text-align:center;'>退票成功</h2>");
            }
        },"json");
    }

    //打开退票窗口
    function openRefundDiv(){
        $("#refunddiv").show(300);
    }
    //关闭退票窗口

    function colseRefundDiv(){
        $("#refunddiv").hide(300);
    }

    window.onload=pageonload();
</script>
