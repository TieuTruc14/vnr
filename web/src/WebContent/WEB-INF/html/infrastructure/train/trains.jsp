<%--
  Created by IntelliJ IDEA.
  User: TieuTruc
  Date: 11/13/2014
  Time: 3:01 PM
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
                    <li><a href="#">Đoàn tàu mẫu</a></li>
                </ul>
                <h3 class="m-b-none">Quản lý đoàn tàu mẫu</h3>
                <label class="btn btn-sm btn-success pull-right" style="position: relative;top:-25px;"><i class="fa fa-check text-active"></i><a href="train.addnew.action" style="color:#ffffff;"> Thêm đoàn tàu mẫu </a></label>
            </div>
            <section class="panel panel-default">
                <header class="panel-heading"> Bảng dữ liệu <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i>

                </header>
                <div class="table-responsive">
                    <table id="train" class="display" data-ride="datatables">
                        <thead>
                        <tr>
                            <th style="width:45px">ID</th>
                            <th style="width:15%">Mác tàu</th>
                            <th style="width:25%">Thời gian rời ga</th>
                            <th style="width:20%">Loại tàu</th>
                            <th style="width:20%">Độ ưu tiên</th>
                            <th style="width:auto">Chức năng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator var="item" value="items" status="stat">
                            <tr>
                                <td><s:property value="#item.id"/></td>
                                <td><s:property value="#item.label.name"/></td>

                                <td><s:property value="@com.eposi.common.util.FormatUtil@formatDate(#item.beginTime,'yyyy/MM/dd')"/></td>
                                <s:if test="#item.label.classification">
                                    <td>Chở hàng</td>
                                </s:if>
                                <s:else>
                                    <td>Chở khách</td>
                                </s:else>
                                <td><s:property value="#item.label.priority" /></td>
                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"><i class="fa fa-random"></i></button>
                                        <ul class="dropdown-menu pull-right">
                                            <li><a href="/vnr/infrastructure/train.edit.action?trainId=<s:property value="#item.id" />"><i class="fa fa-pencil-square-o"></i>  Sửa</a></li>
                                            <li><a href="/vnr/infrastructure/trains.action?trainId=<s:property value="#item.id" />"><i class="fa fa-pencil-square-o"></i>Chi tiết đoàn đầu</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </div>
            </section>

            <section class="panel panel-default">
                <header class="panel-heading"> Chi tiết đoàn tầu <s:property value="item.name"/><i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i>
                </header>
                <div class="table-responsive">
                    <table  id="stops" class="display" data-ride="datatables">
                        <thead>
                        <tr>
                            <th style="width:45px">STT</th>
                            <th style="width:15%">Mác tàu</th>
                            <th style="width:15%">Khu đoạn</th>
                            <th style="width:15%">Ga </th>
                            <th style="width:10%">Thứ tự</th>
                            <th style="width:auto">Thời gian tới ga</th>
                            <th style="width:auto">Thời gian rời ga</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator var="item" value="stops" status="stat">
                            <tr>
                                <td><s:property value="#stat.count"/></td>
                                <td><s:property value="#item.train.label.name"/></td>
                                <td><s:property value="#item.stage.name"/></td>
                                <td><s:property value="#item.station.name"/></td>
                                <td><s:property value="#item.orderStation"/></td>
                                <td><s:property value="@com.eposi.common.util.FormatUtil@formatDate(#item.arrivalTime,'yyyy/MM/dd-HH:mmss')"/></td>
                                <td><s:property value="@com.eposi.common.util.FormatUtil@formatDate(#item.departureTime, 'yyyy/MM/dd-HH:mmss')"/></td>
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

        var tableTrain = $('#train').DataTable();
        $('#train tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                tableTrain.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        } );

        var tableStop = $('#stops').DataTable();
        $('#stops tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                tableStop.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        } );

    } );
</script>