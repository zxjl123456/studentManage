<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2017/11/6
  Time: 18:20
  添加学生界面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加学生</title>
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
        <li class="layui-nav-item layui-this">
            <a href="studentManager.jsp">学生基本信息管理</a>
        </li>
        <li class="layui-nav-item">
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
    <label>添加学生</label>
    <form action="/addStudent" method="post">
        <div class="form-group">
            <label for="addStudentId">学号：</label>
            <input type="text" class="form-control" id="addStudentId" name="addStudentId" placeholder="学号">
        </div>
        <div class="form-group">
            <label for="addStudentName">学生姓名：</label>
            <input type="text" class="form-control" id="addStudentName" name="addStudentName" placeholder="学生姓名">
        </div>
        <div class="form-group">
            <label for="StudentSex">性别：</label>
            <input type="text" class="form-control" id="StudentSex" name="StudentSex" placeholder="性别">
        </div>
        <div class="form-group">
            <label for="StudentDepartment">系名：</label>
            <input type="text" class="form-control" id="StudentDepartment" name="StudentDepartment" placeholder="系名">
        </div>
        <div class="form-group">
            <label for="noToLayTime">生日：</label>
            <div class="input-group date form_datetime col-md-5" data-date-format="yyyy-MM-dd"
                 data-link-field="dtp_input1">
                <input class="form-control" size="12" type="text" value="" name="noToLayTime" readonly
                       id="noToLayTime" ng-model="notToLay.time" required>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
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