<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2017/11/5
  Time: 9:34
  信息提示界面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生成绩信息管理系统</title>
    <script src="jquery/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css"/>
    <script src="bootstrap/js/bootstrap.js"></script>

    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <style>
        a {
            text-decoration: none;
        }

        .jumbotron {
            padding: 2%;
        }
    </style>
</head>
<body>
<div>
    <ul class="layui-nav layui-bg-cyan">
        <li class="layui-nav-item layui-this">
            <a href="main.jsp">首页</a>
        </li>
        <li class="layui-nav-item">
            <a href="userManager.jsp">用户管理</a>
        </li>
        <li class="layui-nav-item">
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
<!--<div class="well well-lg">-->
<!--<span style="font-size: 3rem;text-align: center">欢迎进入学生成绩信息管理系统</span>-->
<!--</div>-->
<div class="jumbotron" style="text-align: center">
    <%-- 信息提示标题 --%>
    <h1>${PromptMessage}</h1>
        <%-- 跳转链接 --%>
    <p>${SkipLinks}</p>
</div>
</body>
</html>

<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('element', function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function (elem) {
            //console.log(elem)
            layer.msg(elem.text());
        });
    });
</script>