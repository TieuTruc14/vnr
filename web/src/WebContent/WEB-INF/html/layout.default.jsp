<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8" />
    <title>VNR VOC</title>
    <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="/vnr/assets/musik/css/app.v1.css" type="text/css" />
    <link rel="stylesheet" href="/vnr/assets/css/style.css" type="text/css" />
    <link rel="stylesheet" href="/vnr/assets/musik/js/datatables/datatables.css" type="text/css">

   <%-- <!--[if lt IE 9]> <script src="/vnr/assets/musik/js/ie/html5shiv.js"></script> <script src="/vnr/assets/musik/js/ie/respond.min.js"></script> <script src="/vnr/assets/musik/js/ie/excanvas.js"></script> <![endif]-->--%>

    <!-- Bootstrap -->
    <!-- App -->
    <script src="/vnr/assets/musik/js/app.v1.js"></script>
    <script src="/vnr/assets/musik/js/datatables/jquery.dataTables.min.js"></script>
    <script src="/vnr/assets/js/moment.min.js"></script>
    <script src="/vnr/assets/musik/js/app.plugin.js"></script>

    <%--<script src="/vnr/assets/js/jquery.dataTables.min.js"></script>--%>

</head>
<body class="">
<section class="vbox">
    <tiles:insertAttribute name="header" />

    <section>
        <section class="hbox stretch">
            <!-- .aside -->
            <tiles:insertAttribute name="leftpanel" />
            <!-- /.aside -->

            <tiles:insertAttribute name="page" />
        </section>
    </section>
</section>

</body>
</html>

