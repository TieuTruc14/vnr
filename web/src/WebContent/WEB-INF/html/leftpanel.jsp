<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<aside class="bg-black dk aside hidden-print" id="nav">
    <section class="vbox">
        <section class="scrollable">
            <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="10px" data-railOpacity="0.2">
                <!-- nav -->
                <nav class="nav-primary hidden-xs">
                    <ul class="nav bg" data-ride="collapse">
                        <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted"> Kế hoạch </li>
                        <li > <a href="/vnr/scheduling/daily.action" class="auto"> <i class="icon-calendar icon text-success"></i> <span class="font-bold">Kế hoạch ngày</span> </a> </li>
                        <li > <a href="/vnr/scheduling/trainReference.listing.action" class="auto"> <i class="icon-folder-alt icon"></i> <span class="font-bold">Đoàn tàu mẫu</span> </a> </li>
                    </ul>
                    <ul class="nav" data-ride="collapse">
                        <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted"> Hạ tầng </li>
                        <li > <a href="/vnr/infrastructure/stations.action" class="auto"> <i class="fa fa-circle-o"></i> <span class="font-bold">Nhà Ga</span> </a> </li>
                        <li > <a href="/vnr/infrastructure/stages.action" class="auto"> <i class="fa fa-code-fork"></i> <span class="font-bold">Khu Đoạn</span> </a> </li>
                    </ul>
                </nav>
                <!-- / nav -->
            </div>
        </section>
        <%--<footer class="footer hidden-xs no-padder text-center-nav-xs">
        <div class="bg hidden-xs ">
        <div class="dropdown dropup wrapper-sm clearfix"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="thumb-sm avatar pull-left m-l-xs"> <img src="/vnr/assets/musik/images/a3.png" class="dker" alt="..."> <i class="on b-light"></i> </span> <span class="hidden-nav-xs clear"> <span class="block m-l"> <strong class="font-bold text-lt">John.Smith</strong> <b class="caret"></b> </span> <span class="text-muted text-xs block m-l">Art Director</span> </span> </a>
        <ul class="dropdown-menu animated fadeInRight aside text-left">
        <li> <span class="arrow bottom hidden-nav-xs"></span> <a href="#">Settings</a> </li>
        <li> <a href="profile.html">Profile</a> </li>
        <li> <a href="#"> <span class="badge bg-danger pull-right">3</span> Notifications </a> </li>
        <li> <a href="docs.html">Help</a> </li>
        <li class="divider"></li>
        <li> <a href="modal.lockme.html" data-toggle="ajaxModal" >Logout</a> </li>
        </ul>
        </div>
        </div>
        </footer>--%>
    </section>
</aside>