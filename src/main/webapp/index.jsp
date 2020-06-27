<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2017/11/5
  Time: 3:44
  登录界面代码
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh" ng-app="indexApp">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <script src="jquery/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css"/>
    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="js/index.js"></script>

</head>
<body>
<div class="panel">
    <h2 class="panel-heading" align="center">学生信息管理系统</h2>
</div>
<div class="well">
    <form action="/login" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input type="text" class="form-control" placeholder="Username" id="username" name="username"
                       aria-describedby="basic-addon1" required>
            </div>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input type="password" class="form-control" id="password" placeholder="Password" name="password"
                       aria-describedby="basic-addon1" required/>
            </div>
        </div>
        <label style="color:red;">${errorMessage}</label>
        <button type="submit" class="btn btn-block btn-success">登录</button>
    </form>
</div>
</body>
</html>
