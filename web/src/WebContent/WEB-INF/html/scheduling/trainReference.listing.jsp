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
                </ul>
                <h3 class="m-b-none">Danh sách Đoàn tàu Mẫu</h3><a href="/vnr/scheduling/trainReference.add.action" class="btn btn-success btn-sm pull-right" style="margin-top:25px;">Thêm tàu mẫu</a>
                <%--<label class="btn btn-sm btn-success pull-right" style="position: relative;top:-25px;"><i class="fa fa-check text-active"></i><a href="stage.addnew.action" style="color:#ffffff;"> Thêm khu đoạn </a></label>--%>
            </div>

            <section class="panel panel-default">
                <header class="panel-heading"> Danh sách Đoàn tàu Mẫu </header>
                <div class="wrapper col-xs-10">
                    <p>Đoàn tàu Mẫu là các đoàn tàu được lập trước, sử dụng để lập Kế hoạch chạy tàu.<br>
                       Đoàn tàu Mẫu chỉ có Giờ khởi hành mà không có Ngày khởi hành, để khi lập kế hoạch sẽ đặt cụ thể lại Ngày giờ.
                    </p>
                </div>

                <div class="table-responsive">
                    <table id="example" class="table table-striped b-t b-light" data-ride="datatables">
                        <thead>
                        <tr>
                            <th style="width:200px;">Mác Tàu</th>
                            <th style="width:120px;">Tàu Khách</th>
                            <th style="width:120px;">Giờ Khởi hành</th>
                            <th>Ghi chú</th>
                            <th style="width:30px;"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator var="item" value="trainReferenceList" status="stat">
                            <tr>
                                <td><span class="label bg-primary"><s:property value="#item.id"/></span></td>
                                <td class="text-center">
                                    <s:if test="%{#item.classification == 0}">
                                        <i class="fa fa-check text-success"></i>
                                    </s:if>
                                </td>
                                <td class="text-right"><s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(#item.departureTime, 'HH:mm')"/></td>
                                <td></td>
                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"><i class="fa fa-random"></i></button>
                                        <ul class="dropdown-menu pull-right">
                                            <li><a href="/vnr/scheduling/trainReference.detail.action?id=<s:property value="#item.id" />"><i class="fa fa-pencil-square-o"></i>Chi tiết</a></li>
                                            <li><a href="/vnr/scheduling/trainReference.edit.action?id=<s:property value="#item.id" />"><i class="fa fa-pencil-square-o"></i>Sửa mác</a></li>
                                            <li ><a class="delete_trainReference"
                                                    data-toggle="modal" data-target="#deleteTrainReference"
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
<script>
    $(document).ready(function() {
        var table = $('#example').DataTable(
                {
                    "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
                    "iDisplayLength": 50,
                    "sPaginationType": "full_numbers"
                }
        );

        $('#example tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        } );
    });
</script>
<div class="modal fade"  id="deleteTrainReference" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Xóa mác tàu!</h4>
            </div>
            <s:form id="filter" method="POST"  action="trainReference.delete.action" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" cssStyle="" validate="false">
                <div class="modal-body">
                    <h3>Thực sự muốn xóa mác tàu này?</h3>
                    <div class="form-group">
                        <div class="col-sm-9">
                            <input class="form-control skill_id" type="hidden" name="id">
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
    $(".delete_trainReference").click(function(e){
        var id = $(this).data('item.id');
        $(".skill_id").val(id);
    });
</script>