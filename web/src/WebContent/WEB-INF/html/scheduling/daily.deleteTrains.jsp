<%--
  Created by IntelliJ IDEA.
  User: TieuTruc
  Date: 12/24/2014
  Time: 10:16 AM
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
                    <li><a href="#"><i class="fa fa-home"></i> Trang chủ</a></li>
                    <li><a href="#"> Lịch trình</a></li>
                    <li><a href="#"> Kế hoạch ngày</a></li>
                    <li><a href="#">Xóa kế hoạch tàu</a></li>
                </ul>
                <h3 class="m-b-none">Xóa lịch trình Tàu từ Đoàn tàu Mẫu trong Kế hoạch chạy tàu</h3>
                <%--<label class="btn btn-sm btn-success pull-right" style="position: relative;top:-25px;"><i class="fa fa-check text-active"></i><a href="stage.addnew.action" style="color:#ffffff;"> Thêm khu đoạn </a></label>--%>
            </div>

            <div class="line line-dashed line-lg pull-in"></div>
            <form class="form-horizontal" method="get" action="daily.deleteTrains.save.action">
                <section class="panel panel-default">
                    <header class="panel-heading"> Danh sách Đoàn tàu Mẫu
                        <div class=" col-xs-6 pull-right">
                            <label class="col-sm-5 control-label text-danger-dker">CHỌN NGÀY XÓA</label>
                            <div class="col-sm-7">
                                <div class="input-daterange">
                                    <s:textfield key="datePlan" placeholder="Ngày" theme="simple" cssClass="input-small input-sm input-s-sm form-control inline" required="true"/>
                                </div>
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                    </header>
                    <div class="wrapper">
                        <p>Lựa chọn Đoàn tàu Mẫu và Khu đoạn cần thêm ở bên dưới.
                        </p>
                    </div>
                    <div class="table-responsive">
                        <table  class="table table-striped b-t b-light"  data-ride="datatables">
                            <thead>
                            <tr>
                                <th style="width:60px!important;">
                                    <input style="width:20px!important;z-index: 9999;"  type="checkbox"  />
                                </th>
                                <th style="width:200px;">Mác Tàu</th>
                                <th style="width:120px;">Tàu Khách</th>
                                <th style="width:120px;">Giờ Khởi hành</th>
                                <th>Ghi chú</th>
                                <th style="width:30px;"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator var="item" value="trainReferenceList" status="stat">
                                <s:set var="idx" value="#stat.count - 1" />
                                <input hidden="true" name="trainReferences[<s:property value="#idx"/>].id" value="<s:property value="#item.id"/>">
                                <tr>
                                    <td><label style="width:40px!important;" class="checkbox m-n i-checks">
                                        <input style="width:20px!important;" type="checkbox" name="trainReferences[<s:property value="#idx"/>].checkbox">
                                        <i></i></label>
                                    </td>
                                    <td><span class="label bg-primary"><s:property value="#item.id"/></span></td>
                                    <td class="text-center">
                                        <s:if test="%{#item.classification == 0}">
                                            <i class="fa fa-check text-success"></i>
                                        </s:if>
                                    </td>
                                    <td class="text-right"><s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(#item.departureTime, 'HH:mm')"/></td>
                                    <td></td>
                                    <td><a href="/vnr/scheduling/trainReference.detail.action?id=<s:property value="#item.id"/>"><i class="fa fa-chevron-circle-right"></i></a></td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <hr />
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-2">
                            <button type="submit" class="btn btn-primary">Xóa tàu khỏi kế hoạch</button>
                        </div>
                    </div>
                </form>
                <footer class="panel-footer">
                </footer>
            </section>

        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>

<link rel="stylesheet" href="/vnr/assets/js/eternicode-bootstrap-datepicker/css/datepicker.css" type="text/css" />
<script src="/vnr/assets/js/eternicode-bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script>
    $(document).ready(function() {
        var table = $('#example').DataTable(
                {
                    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
                    "iDisplayLength": 50,
                    "sPaginationType": "full_numbers"
                }
        );

        // datatable
        $('[data-ride="datatables"]').each(function() {
            var oTable = $(this).dataTable( {
                "bProcessing": true,
                /* "sAjaxSource": "js/datatables/datatable.json",*/
                "iDisplayLength": 100,
                "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
                "sPaginationType": "full_numbers"
            } );
        });
        $('.input-daterange').datepicker({
            format: "dd/mm/yyyy",
           /* startDate: "new Date()",*//*01-01-2014*/
            /*endDate: "new Date()",*/
            todayBtn: "linked",
            calendarWeeks: false,
            autoclose: true,
            todayHighlight: true
        });

    } );

</script>
