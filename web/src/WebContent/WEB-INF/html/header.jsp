<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header class="bg-info header header-xs navbar navbar-fixed-top-xs">
    <div class="navbar-header aside bg-info">
        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html"> <i class="icon-list"></i> </a>
        <a href="/vnr/index.action" class="navbar-brand text-lt">Vnr.Schedule</a>
        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".user"> <i class="icon-settings"></i> </a>
    </div>
    <ul class="nav navbar-nav hidden-xs">
        <li> <a href="#nav,.navbar-header" data-toggle="class:nav-xs,nav-xs" class="text-muted"> <i class="fa fa-indent text"></i> <i class="fa fa-dedent text-active"></i> </a> </li>
    </ul>
    <div class="navbar-left">
        <p class="text-center text-uc text-xs text-mute font-bold m-sm">Tổng công ty Đường sắt Việt nam<br>
            <small>Trung tâm Điều hành Vận tải Đường sắt</small>
        </p>
    </div>
    <%--<form class="navbar-form navbar-left input-s-lg m-t m-l-n-xs hidden-xs" role="search">--%>
    <%--<div class="form-group">--%>
    <%--<div class="input-group"> <span class="input-group-btn">--%>
    <%--<button type="submit" class="btn btn-sm bg-white btn-icon rounded"><i class="fa fa-search"></i></button>--%>
    <%--</span>--%>
    <%--<input type="text" class="form-control input-sm no-border rounded" placeholder="Search songs, albums...">--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</form>--%>
    <div class="navbar-right ">
        <ul class="nav navbar-nav m-n hidden-xs nav-user user">

            <li class="dropdown"> <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown"> <span class="thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm"> <img src="/vnr/assets/img/user.png" alt="..."> </span> admin <b class="caret"></b> </a>
                <ul class="dropdown-menu animated fadeInRight">
                    <li> <span class="arrow top"></span> <a href="#">Settings</a> </li>
                    <li> <a href="profile.html">Profile</a> </li>
                    <li> <a href="#"> <span class="badge bg-danger pull-right">3</span> Notifications </a> </li>
                    <li> <a href="docs.html">Help</a> </li>
                    <li class="divider"></li>
                    <li> <a href="modal.lockme.html" data-toggle="ajaxModal" >Logout</a> </li>
                </ul>
            </li>
        </ul>
    </div>

</header>