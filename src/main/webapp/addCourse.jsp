<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2017/11/6
  Time: 18:20
  添加课程界面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加课程</title>
    <script src="jquery/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css"/>
    <script src="bootstrap/js/bootstrap.js"></script>

    <!-- bootstrap date start -->
    <link href="./bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="./bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="./bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <!-- bootstrap date end -->

    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <style>
        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<div>
    <ul class="layui-nav layui-bg-cyan">
        <li class="layui-nav-item">
            <a href="main.jsp">首页</a>
        </li>
        <li class="layui-nav-item">
            <a href="userManager.jsp">用户管理</a>
        </li>
        <li class="layui-nav-item">
            <a href="studentManager.jsp">学生基本信息管理</a>
        </li>
        <li class="layui-nav-item  layui-this">
            <a href="courseManager.jsp">课程信息管理</a>
        </li>
        <li class="layui-nav-item">
            <a href="studentCourseManager.jsp">学生成绩信息管理</a>
        </li>
        <li class="layui-nav-item">
            <a href="dataStatistics.jsp">数据统计</a>
        </li>
    </ul>
</div>
<div class="well">
    <label>添加课程</label>
    <form action="/courseServlet?type=add" method="post">
        <div class="form-group">
            <label for="courseNum">课程号：</label>
            <input type="text" class="form-control" id="courseNum" name="courseNum" placeholder="课程号">
        </div>
        <div class="form-group">
            <label for="courseName">课程名：</label>
            <input type="text" class="form-control" id="courseName" name="courseName" placeholder="课程名">
        </div>
        <div class="form-group">
            <label for="grade">学分：</label>
            <input type="text" class="form-control" id="grade" name="grade" placeholder="学分">
        </div>
        <button class="btn btn-success btn-block" type="submit">提交</button>
    </form>
</div>
</body>
</html>

<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 0,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: "month",//选择日期后，不会再跳转去选择时分秒
        forceParse: 0,
        showMeridian: 1
    });
</script>