<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2017/11/5
  Time: 9:36
  用户管理界面
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

        #userManagerLeft {
            float: left;
            width: 20%;
            height: 100%;
        }

        #userManagerRight {
            float: left;
            margin-left: 1%;
            width: 79%;
        }

        #userManagerLeft ul li {
            margin-top: 4%;
        }
    </style>
</head>
<body>
<div>
    <ul class="layui-nav layui-bg-cyan">
        <li class="layui-nav-item">
            <a href="main.jsp">首页</a>
        </li>
        <li class="layui-nav-item layui-this">
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
<div>
    <div id="userManagerLeft">
        <ul class="nav nav-pills nav-stacked">
            <li id="addButton" role="presentation" class="active"><a href="#" onclick="changeShowView(this)">添加用户</a>
            </li>
            <li id="modifyButton" role="presentation"><a href="#" onclick="changeShowView(this)">密码修改</a></li>
        </ul>
    </div>
    <div id="userManagerRight">
        <div id="addUser" class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    添加用户
                </h3>
            </div>
            <div class="panel-body well well-lg">
                <form id="addUserForm" action="/addUser" method="post">
                    <div class="form-group">
                        <label for="addUsername">用户名：</label>
                        <input type="text" class="form-control" id="addUsername" name="addUsername" placeholder="用户名"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="addPassword">输入密码：</label>
                        <input type="password" class="form-control" id="addPassword" name="addPassword"
                               placeholder="密码" required>
                    </div>
                    <div class="form-group">
                        <label for="addPasswordConfirm">确认密码：</label>
                        <input type="password" class="form-control" id="addPasswordConfirm" name="addPasswordConfirm"
                               placeholder="确认密码" required>
                    </div>
                    <button type="button" class="btn btn-default btn-lg" onclick="submitNewUser()">提交</button>
                </form>
            </div>
        </div>


        <div id="changePassword" class="panel panel-default" style="display: none">
            <div class="panel-heading">
                <h3 class="panel-title">
                    修改用户密码
                </h3>
            </div>
            <div class="panel panel-body well well-lg">
                <form id="modifyForm" method="post" action="/modifyUserPassword">
                    <div class="form-group">
                        <label for="inputUsername">用户名：</label>
                        <input type="text" class="form-control" id="inputUsername" name="inputUsername"
                               placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">输入密码：</label>
                        <input type="password" class="form-control" id="inputPassword" name="inputPassword"
                               placeholder="密码">
                    </div>
                    <div class="form-group">
                        <label for="newPassword">输入新密码：</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword"
                               placeholder="新密码">
                    </div>
                    <div class="form-group">
                        <label for="newPasswordConfirm">确认新密码：</label>
                        <input type="password" class="form-control" id="newPasswordConfirm" name="newPasswordConfirm"
                               placeholder="确认新密码">
                    </div>
                    <button type="button" class="btn btn-default btn-lg" onclick="submitNewPassword()">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    var msg = null;

    layui.use(['layer', 'form'], function () {
        var layer = layui.layer, form = layui.form;
        msg = function (str) {
            layer.msg(str);
        }
    });
    layui.use('element', function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        //监听导航点击
        element.on('nav(demo)', function (elem) {
            layer.msg(elem.text());
        });
    });

    var submitNewPassword = function () {
        var inputUsername = document.getElementById("inputUsername");
        if (inputUsername.value == "") {
            inputUsername.focus();
            msg("用户名不能为空");
            return;
        }
        var inputPassword = document.getElementById("inputPassword");
        if (inputPassword.value == "") {
            inputPassword.focus();
            msg("密码不能为空");
            return;
        }
        var newPassword = document.getElementById("newPassword");
        if (newPassword.value == "") {
            newPassword.focus();
            msg("密码不能为空");
            return;
        }
        var newPasswordConfirm = document.getElementById("newPasswordConfirm");
        if (newPasswordConfirm.value == "") {
            newPasswordConfirm.focus();
            msg("密码不能为空");
            return;
        }
        if (newPasswordConfirm.value != newPassword.value) {
            msg("两次输入的密码不一致！");
            newPassword.focus();
            return;
        } else {
            var modifyForm = document.getElementById("modifyForm");
            modifyForm.submit();
        }
    }

    var changeShowView = function (obj) {
        var clickButton = obj.innerHTML;
        var addView = document.getElementById("addUser");
        var addButton = document.getElementById("addButton");
        var modifyButton = document.getElementById("modifyButton");
        var modifyView = document.getElementById("changePassword");
        if (clickButton == "密码修改") {
            //密码修改按钮高亮
            modifyButton.setAttribute("class", "active");
            //添加用户按钮灭
            addButton.setAttribute("class", "");
            //显示密码修改界面
            modifyView.style.display = "inherit";
            //隐藏添加用户界面
            addView.style.display = "none";
        } else if (clickButton == "添加用户") {
            //添加用户按钮高亮
            addButton.setAttribute("class", "active");
            //修改密码按钮灭
            modifyButton.setAttribute("class", "");
            //显示添加用户界面
            addView.style.display = "inherit";
            //隐藏修改秘密界面
            modifyView.style.display = "none";
        } else {
            return;
        }
    }

    var submitNewUser = function () {
        var username = document.getElementById("addUsername");
        if (username.value == "") {
            username.focus();
            msg("用户名不能为空");
            return;
        }
        var addPassword = document.getElementById("addPassword");
        if (addPassword.value == "") {
            addPassword.focus();
            msg("密码不能为空");
            return;
        }
        var addPasswordConfirm = document.getElementById("addPasswordConfirm");
        if (addPasswordConfirm.value == "") {
            addPasswordConfirm.focus();
            msg("密码不能为空");
            return;
        }
        if (addPassword.value != addPasswordConfirm.value) {
            msg("两次输入的密码不一致！");
            addPassword.focus();
            return;
        } else {
            var addForm = document.getElementById("addUserForm");
            addForm.submit();
        }
    }
</script>