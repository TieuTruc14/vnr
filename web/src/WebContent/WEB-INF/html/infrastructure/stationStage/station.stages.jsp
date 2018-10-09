<%--
  Created by IntelliJ IDEA.
  User: TieuTruc
  Date: 11/25/2014
  Time: 11:57 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<section id="content">
    <section class="vbox">
        <section class="scrollable padder">
            <div class="m-b-md">
                <ul class="breadcrumb no-border no-radius b-b b-light pull-in">
                    <li><a href="/mdms/index.action"><i class="fa fa-home"></i> Trang chủ</a></li>
                    <li><a href="#">Hạ tầng</a></li>
                    <li><a href="#">Nhà ga</a></li>
                </ul>
                <h3 class="m-b-none">Quản lý ga trong khu đoạn</h3>
                <label class="btn btn-sm btn-success pull-right" style="position: relative;top:-25px;"><i class="fa fa-check text-active"></i><a href="station.stage.add.action" style="color:#ffffff;"> Thêm  </a></label>
            </div>
            <section class="panel panel-default">
                <header class="panel-heading"> Bảng dữ liệu <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i>

                </header>
                <div class="table-responsive">
                    <table id="example" class="display" data-ride="datatables">
                        <thead>
                        <tr>
                            <th style="width:45px">STT</th>
                            <th style="width:auto">Khu đoạn</th>
                            <th style="width:auto">Tên ga</th>
                            <th style="width:auto">Lý trình</th>
                            <th style="width:auto">Khoảng cách(km)</th>
                            <th style="width:auto">Chức năng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator var="item" value="items" status="stat">
                            <tr>
                                <td><s:property value="#stat.count"/></td>
                                <td><s:property value="#item.Stage.name"/></td>
                                <td><s:property value="#item.Station.name"/></td>
                                <td><s:property value="#item.km"/> </td>
                                <td><s:property value="#item.distance"/></td>
                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"><i class="fa fa-random"></i></button>
                                        <ul class="dropdown-menu pull-right">
                                            <li><a href="/vnr/infrastructure/station.stage.edit.action?stationId=<s:property value="#item.id" />"><i class="fa fa-pencil-square-o"></i>  Sửa</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </div>
            </section>
        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>

<script src="/vnr/assets/js/jquery-1.11.1.min.js"></script>
<script>
    $(document).ready(function() {
        var table = $('#example').DataTable();
        $('#example tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        } );
    } );
</script>
