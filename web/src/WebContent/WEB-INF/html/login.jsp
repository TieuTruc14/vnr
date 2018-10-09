<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Fullscreen Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="/vnr/assets/login/css/reset.css">
        <link rel="stylesheet" href="/vnr/assets/login/css/supersized.css">
        <link rel="stylesheet" href="/vnr/assets/login/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>

        <div class="page-container">
            <h1>Đăng nhập</h1>
            <form action="<s:url value='/j_spring_security_check'/>" method="post">
                <input type="text" id="j_username" name="j_username" class="username" placeholder="Tên truy cập">
                <input type="password" id="j_password" name="j_password" class="password" placeholder="Mật khẩu">
                <button type="submit">Đăng nhập</button>
                <div class="error"><span>+</span></div>
            </form>
			<!--
            <div class="connect">
                <p>Or connect with:</p>
                <p>
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p>
            </div>
			-->
        </div>

        <!-- Javascript -->
        <script src="/vnr/assets/login/js/jquery-1.8.2.min.js"></script>
        <script src="/vnr/assets/login/js/supersized.3.2.7.min.js"></script>
        <script src="/vnr/assets/login/js/supersized-init.js"></script>
        <script src="/vnr/assets/login/js/scripts.js"></script>

    </body>

</html>

