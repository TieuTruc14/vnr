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
                    <li><a href="#">Lịch trình</a></li>
                </ul>
                <h3 class="m-b-none">Quản lý lịch trình</h3>
                <label class="btn btn-sm btn-success pull-right" style="position: relative;top:-25px;"><i class="fa fa-check text-active"></i><a href="plan.addnew.action" style="color:#ffffff;"> Thêm lịch trình </a></label>
            </div>

            <section class="panel panel-default">
                <header class="panel-heading"> Bảng dữ liệu <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i>
                </header>
                <div class="table-responsive">
                    <table class="table table-striped m-b-none" data-ride="datatables">
                        <thead>
                        <tr>
                            <th style="width:45px" rowspan="2">STT</th>
                            <th style="width:40%" rowspan="2">Mác tàu</th>
                            <th style="width:15%" rowspan="2">Ga dừng/đỗ</th>
                            <th style="width:15%" colspan="2">Thời gian</th>
                            <th style="width:15%" rowspan="2">Ga tác nghiệp</th>
                            <th style="width:auto" rowspan="2">Chức năng</th>
                        </tr>
                        <tr>
                            <th >Tới ga</th>
                            <th >Rời đi</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator var="item" value="items" status="stat">
                            <tr>
                                <td><s:property value="#stat.count"/></td>
                                <td><s:property value="#item.train.name"/></td>
                                <td><s:property value="#item.station.name"/></td>
                                <td><s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(#item.departureTime, 'HH:mm:ss')"/></td>
                                <td><s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(#item.arrivalTime, 'HH:mm:ss')"/></td>
                                <s:if test="#item.wait">
                                    <td>Không</td>
                                </s:if>
                                <s:else>
                                    <td>Có</td>
                                </s:else>
                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"><i class="fa fa-random"></i></button>
                                        <ul class="dropdown-menu pull-right">
                                            <li><a href="/vnr/infrastructure/plan.edit.action?planId=<s:property value="#item.id" />"><i class="fa fa-pencil-square-o"></i>  Sửa</a></li>
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

