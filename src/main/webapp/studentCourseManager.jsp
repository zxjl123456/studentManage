<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2017/11/6
  Time: 18:16
  学生成绩管理界面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="zh">
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

        #userManagerLeft ul li {
            margin-top: 4%;
        }

        .table th, .table td {
            text-align: center;
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
        <li class="layui-nav-item">
            <a href="courseManager.jsp">课程信息管理</a>
        </li>
        <li class="layui-nav-item  layui-this">
            <a href="studentCourseManager.jsp">学生成绩信息管理</a>
        </li>
        <li class="layui-nav-item">
            <a href="dataStatistics.jsp">数据统计</a>
        </li>
    </ul>
</div>
<div>
    <div class="well">
        <form action="/studentGrade?type=search" method="post" id="searchStudentGrade">
            <div class="form-inline">
                <!-- Single button -->
                <div class="btn-group">
                    <div class="form-group">
                        <label class="control-label">查询方式：</label>
                        <select class="form-control" id="searchWay" name="searchWay">
                            <option value="studentId">学号</option>
                            <option value="studentName">姓名</option>
                            <option value="courseId">课程号</option>
                            <option value="courseName">课程名</option>
                        </select>
                    </div>
                </div>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <div class="form-group">
                    <label for="searchGradeMessage" id="searchMessageLabel">学号：</label>
                    <input type="text" class="form-control" id="searchGradeMessage" name="searchGradeMessage"
                           value="${searchGradeMessage}" placeholder="请输入学号">
                </div>
                <button type="button" class="btn btn-default" onclick="submitMessage()">查询</button>

                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a class="btn btn-primary" href="addStudentCourseGrade.jsp">添加</a>
            </div>
        </form>
    </div>
    <c:if test="${studentAndCourseList!= null || fn:length(studentAndCourseList) != 0}">


        <div class="well">
            <table class="table table-striped table-bordered">
                <thead>
                <tr class="messageTitleShow">
                    <td>序号</td>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>课程号</th>
                    <th>课程名</th>
                    <th>成绩</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <% int i = 1; %>
                <c:forEach var="studentGrade" items="${studentAndCourseList}">
                    <tr>
                        <td><%=i++ %>
                        </td>
                        <td><c:out value="${studentGrade.studentId}"/></td>
                        <td><c:out value="${studentGrade.studentName}"/></td>
                        <td><c:out value="${studentGrade.courseNum}"/></td>
                        <td><c:out value="${studentGrade.courseName}"/></td>
                        <td><c:out value="${studentGrade.grade}"/></td>
                        <td>

                            <a class="btn btn-success"
                               href="studentGrade?type=modify&id=${studentGrade.studentId}&courseNum=${studentGrade.courseNum}">修改</a>
                            <a class="btn btn-danger"
                               href="studentGrade?type=delete&id=${studentGrade.studentId}&courseNum=${studentGrade.courseNum}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</div>
</body>
</html>

<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>

    var submitMessage = function () {
        var message = document.getElementById("searchGradeMessage");
        message.value = $.trim(message.value);
        if (message.value == "") {
            msg("查询信息不能为空");
            $("#searchGradeMessage").val("");
            message.focus();
            return;
        }
        var formMessage = document.getElementById("searchStudentGrade");
        formMessage.submit();
    }
    var way = "id";
    var msg = null;
    layui.use('element', function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function (elem) {
            //console.log(elem)
            layer.msg(elem.text());
        });
    });

    layui.use(['layer', 'form'], function () {
        var layer = layui.layer
            , form = layui.form;
        msg = function (str) {
            layer.msg(str);
        }

    });

    $("#searchWay").change(function () {
        way = $(this).val();
        var wayTitle = document.getElementById("searchMessageLabel");
        var wayInput = document.getElementById("searchGradeMessage");
        if (way == "studentId") {
            wayTitle.innerHTML = "学号：";
            wayInput.setAttribute("placeholder", "请输入学号");
        } else if (way == "studentName") {
            wayTitle.innerHTML = "姓名：";
            wayInput.setAttribute("placeholder", "请输入姓名");
        } else if (way == "courseId") {
            wayTitle.innerHTML = "课程号：";
            wayInput.setAttribute("placeholder", "请输入课程号");
        } else if (way == "courseName") {
            wayTitle.innerHTML = "课程名：";
            wayInput.setAttribute("placeholder", "请输入课程名");
        }
    });
</script>