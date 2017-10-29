<%--
  Created by IntelliJ IDEA.
  User: SChen
  Date: 2017/10/26
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>电影添加</h2>
    <s:if test="#parameters.msg[0]==1">
        <div style="color:green;">添加成功</div>
    </s:if>
    <s:if test="#parameters.msg[0]==2">
        <div style="color:red;">添加失败</div>
    </s:if>

    <s:if test="hasFieldErrors()">
        <div style="color:blue;">
            <h2>错误信息</h2>
            <s:fielderror />
        </div>
    </s:if>

    <s:form method="post" action="movieAction_addMovie">
        中文名:<s:textfield name="movie.name_cn"/><br/>
        英文名:<s:textfield name="movie.name_en"/><br/>
        票&nbsp;价:<s:textfield name="movie.price"/><br/>
        日&nbsp;期:<s:textfield name="movie.pub_date"/><br/>
        类&nbsp;型:<s:textfield name="movie.movie_type"/><br/>
        国&nbsp;家:<s:textfield name="movie.country"/><br/>
        票&nbsp;数:<s:textfield name="movie.pub_count"/><br/>
        <s:submit value="添加"/>
    </s:form>
    <a href="main.jsp">主页</a>
</body>
</html>
