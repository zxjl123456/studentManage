<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2017/11/5
  Time: 9:36
  修改课程界面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改课程信息</title>
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
        <li class="layui-nav-item ">
            <a href="studentManager.jsp">学生基本信息管理</a>
        </li>
        <li class="layui-nav-item layui-this">
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
<H1>修改课程</H1>
<div class="well">

    <form action="/courseServlet?type=update" method="post">
        <div class="form-group">
            <label for="courseNum">课程号：</label>
            <input type="text" class="form-control" id="courseNum" name="courseNum" value="${course.courseNum}"
                   placeholder="学号" disabled>
            <input type="hidden" class="form-control"  name="courseNum" value="${course.courseNum}">
        </div>
        <div class="form-group">
            <label for="courseName">课程名：</label>
            <input type="text" class="form-control" id="courseName" name="courseName"
                   value="${course.courseName}" placeholder="学生姓名">
        </div>
        <div class="form-group">
            <label for="grade">学分：</label>
            <input type="text" class="form-control" id="grade" name="grade" value="${course.grade}"
                   placeholder="性别">
        </div>
        <button type="submit" class="btn btn-block btn-success">提交修改</button>
    </form>
</div>
</body>
</html>