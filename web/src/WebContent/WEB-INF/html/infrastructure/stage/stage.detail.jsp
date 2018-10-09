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
                    <li><a href="#">Khu đoạn</a></li>
                    <li><a href="#">Chi tiết</a></li>
                </ul>
                <h3 class="m-b-none">Chi tiết Khu đoạn <s:property value="stageItem.name"/> <small><code><s:property value="stageItem.id"/></code></small></h3>
            </div>

            <section class="panel panel-default">
                <header class="panel-heading"> Danh sách Ga trong khu đoạn </header>
                <div class="wrapper">
                    <p>Liệt kê Danh sách ga trong khu đoạn theo chiều xuôi. Lý trinh thể hiện thứ tự (trước sau cho từng ga). Lý trình sử dụng đơn vị mét (m), ví dụ 8km+300 tương đương 8300m. </p>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped b-t b-light">
                        <thead>
                        <tr>
                            <th style="width:120px;">Lý trình (m)</th>
                            <th style="width:200px;">Id Ga</th>
                            <th>Tên Ga</th>
                            <th style="width:30px;"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator var="item" value="stationStageList" status="stat">
                        <tr>
                            <td class="text-right"><s:property value="#item.distance"/></td>
                            <td><s:property value="#item.station.id"/></td>
                            <td><s:property value="#item.station.name"/></td>
                            <td></td>
                        </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </div>
                <footer class="panel-footer">

                </footer>
            </section>



        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>

