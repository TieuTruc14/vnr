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
                    <li><a href="#">Lịch trình</a></li>
                    <li><a href="#">Đoàn tàu Mẫu</a></li>
                    <li><a href="#">Chi tiết</a></li>
                </ul>
                <h3 class="m-b-none">Chi tiết Đoàn tàu mẫu - Mác tàu <code><s:property value="id"/></code></h3>
                <input name="idTrainR" value="<s:property value="id"/>" hidden="true"/>
            </div>
            <section class="panel panel-default text-sm doc-buttons">
                <div class="panel-body">
                    Thời gian Khởi hành: <span class="badge bg-info h4"><s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(item.departureTime, 'HH:mm')"/></span>
                </div>
            </section>
            <section class="panel panel-default">
                <header class="panel-heading"> Bảng Lịch trình mẫu  </header>
                <div class="wrapper ">
                    <p class="col-xs-10">Danh sách các Ga Thuộc Khu đoạn đoàn tàu chạy qua, sắp xếp theo thời gian.</p>
                    <div class=" col-xs-2">
                        <a href="/vnr/scheduling/stopReference.add.action?trainReferenceId=<s:property value="id"/> " class="btn btn-success btn-sm " style="margin-top:-8px;">Thêm lịch trình</a>
                    </div>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped b-t b-light">
                        <thead>
                        <tr>
                            <th>Tên Ga</th>
                            <th style="width:100px;">Thời gian Tới</th>
                            <th style="width:100px;">Thời gian Rời</th>
                            <th style="width:20px;">Tác nghiệp</th>
                            <th style="width:30px;"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator var="item" value="stopReferenceList" status="stat">
                            <tr>
                                <td><s:property value="#item.stationStage.station.name"/></td>
                                <td class="text-right"><s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(#item.arrivalTime + trainReference.departureTime, 'HH:mm')"/></td>
                                <td class="text-right"><s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(#item.departureTime + trainReference.departureTime, 'HH:mm')"/></td>
                                <td class="text-center">
                                    <s:if test="%{#item.wait != 0}">
                                        <i class="fa fa-check text-success"></i>
                                    </s:if>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"><i class="fa fa-random"></i></button>
                                        <ul class="dropdown-menu pull-right">
                                            <li><a href="/vnr/scheduling/stopReference.edit.action?stopReferenceId=<s:property value="#item.id"/>"><i class="fa fa-pencil-square-o"></i>Sửa</a></li>
                                            <li ><a class="delete_stopReference"
                                                    data-toggle="modal" data-target="#deleteStopReference"
                                                    data-item.id="<s:property value="#item.id"/> "><i class="fa fa-pencil-square-o"></i>Xóa</a>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
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
<%--<div id="modal-dialog" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="close">×</a>
                <h3>Are you sure</h3>
            </div>
            <div class="modal-body">
                <p>Do you want to submit the form?</p>
            </div>
            <div class="modal-footer">
                <a href="#" id="btnYes" class="btn confirm">Yes</a>
                <a href="#" data-dismiss="modal" aria-hidden="true" class="btn secondary">No</a>
            </div>
        </div>
    </div>
</div>--%>
<div class="modal fade"  id="deleteStopReference" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Xóa điểm dừng tàu!</h4>
            </div>
            <s:form id="filter" method="POST" label="Tìm kiếm " action="stopReference.delete.action" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" cssStyle="" validate="false">
                <div class="modal-body">

                    <h3>Thực sự muốn xóa trường này?</h3>
                    <div class="form-group">
                        <div class="col-sm-9">
                            <input class="form-control skill_id" type="hidden" name="stopReferenceId">
                            <input class="form-control" type="hidden" name="id" value="<s:property value="id"/>"/>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-danger">Xóa</button>
                </div>
            </s:form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(".delete_stopReference").click(function(e){
        var id = $(this).data('item.id');
        $(".skill_id").val(id);
    });
</script>