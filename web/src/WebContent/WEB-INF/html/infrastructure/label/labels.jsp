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
                    <li><a href="#">Mác tàu</a></li>
                </ul>
                <h3 class="m-b-none">Quản lý đoàn tàu mẫu</h3>
                <label class="btn btn-sm btn-success pull-right" style="position: relative;top:-25px;"><i class="fa fa-check text-active"></i><a data-toggle="modal" data-target="#addLabel"  style="color:#ffffff;"> Thêm đoàn tàu mẫu</a></label>
            </div>
            <section class="panel panel-default">
            <header class="panel-heading"> Danh sách mác tàu <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i>

            </header>
            <div class="table-responsive">
                <table id="label" class="display" data-ride="datatables">
                    <thead>
                    <tr>
                        <th style="width:45px">STT</th>
                        <th style="width:40%">Tên mác</th>
                        <th style="width:15%">Loại tàu</th>
                        <th style="width:20%">Độ ưu tiên</th>
                        <th style="width:20%">Giờ xuất phát</th>
                        <th style="width:auto">Chức năng</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator var="item" value="items" status="stat">
                        <tr>
                            <td><a href="/vnr/infrastructure/labels.action?labelId=<s:property value="#item.id" />"><s:property value="#stat.count"/></a></td>
                            <td><a href="/vnr/infrastructure/labels.action?labelId=<s:property value="#item.id" />"><s:property value="#item.name"/></a></td>
                            <s:if test="item.classification==1">
                                <td><a href="/vnr/infrastructure/labels.action?labelId=<s:property value="#item.id" />">Chở khách</a></td>
                            </s:if>
                            <s:else>
                                <td><a href="/vnr/infrastructure/labels.action?labelId=<s:property value="#item.id" />">Chở hàng</a></td>
                            </s:else>
                            <td><a href="/vnr/infrastructure/labels.action?labelId=<s:property value="#item.id" />"><s:property value="#item.priority"/></a></td>
                            <td><a href="/vnr/infrastructure/labels.action?labelId=<s:property value="#item.id" />"><s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(#item.departureTime, 'HH:mm')"/></a></td>
                            <td>
                                <div class="btn-group">
                                    <button class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"><i class="fa fa-random"></i></button>
                                    <ul class="dropdown-menu pull-right">
                                        <li ><a  ><i class="fa fa-pencil-square-o"></i>
                                            <button type="button" class="btn btn-primary btn-xs edit_button"
                                                    data-toggle="modal" data-target="#editLabel"
                                                    data-item.name="<s:property value="#item.name"/> "
                                                    data-item.id="<s:property value="#item.id"/> "
                                                    data-item.classification="<s:property value="#item.classification"/> "
                                                    data-item.departure="<s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(#item.departureTime, 'HH:mm')"/> "
                                                    data-item.priority="<s:property value="#item.priority"/> ">
                                               Sửa
                                            </button>
                                        </a></li>
                                        <li><a href="/vnr/infrastructure/labels.action?labelId=<s:property value="#item.id" />"><i class="fa fa-pencil-square-o"></i>Kế hoạch mác tàu</a></li>
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
                <header class="panel-heading"> Kế hoạch mác tàu <s:property value="labelName"/><i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i>
                   <%-- <button type="button" class="btn btn-success btn-xs add_plan"
                            data-toggle="modal" data-target="#addPlan" data-labelId="<s:property value="labelId"/> ">
                        Thêm kế hoạch <s:property value="labelId" />
                    </button>--%>
                    <label class="btn btn-sm btn-success pull-right add_plan" style="position: relative;top:-8px;"><i class="fa fa-check text-active"></i><a data-toggle="modal" data-target="#addPlan" data-labelId="<s:property value="labelId"/> "  style="color:#ffffff;"> Thêm Kế hoạch </a></label>
                </header>
                <div class="table-responsive">
                    <table  id="plans" class="display" data-ride="datatables">
                        <thead>
                        <tr>
                            <th style="width:45px">STT</th>
                            <th style="width:30%">Tên mác</th>
                            <th style="width:auto">Ga dừng/đỗ</th>
                            <th style="width:auto">Thứ tự điểm dừng</th>
                            <th style="width:auto">Thời gian tới ga</th>
                            <th style="width:auto">Thời gian rời ga</th>
                            <th style="width:auto">Chức năng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator var="item" value="stopReferences" status="stat">
                            <tr>
                                <td><s:property value="#stat.count"/></td>
                                <td><s:property value="#item.trainReference.name"/></td>
                                <td><s:property value="#item.station.name"/></td>
                                <td><s:property value="#item.orderStation"/></td>
                                <td><s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(#item.arrivalTime, 'HH:mm:ss')"/></td>
                                <td><s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(#item.departureTime, 'HH:mm:ss')"/></td>
                               <%-- <s:if test="item.station.name">
                                    <td>Chở khách</td>
                                </s:if>
                                <s:else>
                                    <td>Chở hàng</td>
                                </s:else>--%>

                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"><i class="fa fa-random"></i></button>
                                        <ul class="dropdown-menu pull-right">
                                            <li><a><i class="fa fa-pencil-square-o"></i>
                                                <button type="button" class="btn btn-primary btn-xs edit_plan"
                                                        data-toggle="modal" data-target="#editPlan"
                                                        data-plan.id="<s:property value="#item.id"/>"
                                                        data-plan.station.id="<s:property value="#item.station.id"/>"
                                                        data-plan.stage.id="<s:property value="#item.stage.id"/>"
                                                        data-plan.wait="<s:property value="#item.wait"/>"
                                                        data-plan.arrival="<s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(#item.arrivalTime, 'HH:mm')"/>"
                                                        data-plan.departure="<s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(#item.departureTime, 'HH:mm')"/>"
                                                        data-plan.ordersta="<s:property value="#item.orderStation"/>">
                                                    Sửa
                                                </button>
                                            </a></li>

                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </div>
            </section>
<%--o day--%>


            <!--  Edit Label -->
            <div class="modal fade"  id="editLabel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Sửa mác tàu</h4>
                        </div>
                        <s:form id="filter" method="POST" label="Tìm kiếm " action="labels.action" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" cssStyle="" validate="false">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Mã mác tàu ví dụ: SE1,SE2</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class=" col-sm-3 control-label">Mã mác tàu<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control " type="hidden" name="format" value="edit">
                                        <input class="form-control business_skill_id" type="hidden" name="item.id">
                                        <input class="form-control span2 input-s-lg inline business_skill_name" name="item.name" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Dữ liệu dạng số 0,1 .Bằng "1" tức tàu chở khách, "0" là tàu hàng</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Loại tàu<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control span2 input-s-lg inline business_skill_classification"  name="item.classification">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Độ ưu tiên là giá trị hàng trăm 100,200,300,400,...Càng lớn độ ưu tiên càng cao.</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Độ ưu tiên<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control span2 input-s-lg inline business_skill_priority" name="item.priority"  required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Thời gian rời ga theo giờ-phút từ lúc xuất phát</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Thời gian rời ga<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control  span2 input-s-lg inline business_skill_departure" name="departure" pattern="(([0-9][0-9])|([0-9][0-9][0-9])|([0-9])):([0-9][0-9])" title="Dạng HH:mm, Ví dụ: 07:07" >
                                            <%--<input class="form-control  span2 input-s-lg inline" style="width:60px;" type="number" name="departure1" required>&nbsp; giờ
                                            <input class="form-control  span2 input-s-lg inline" style="width:60px;" type="number" name="departure2" required> &nbsp;phút--%>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                                <button type="submit" class="btn btn-primary">Cập nhâp</button>
                            </div>
                        </s:form>
                    </div>
                </div>
            </div>
            <%--add label--%>
            <div class="modal fade" id="addLabel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" >Sửa mác tàu</h4>
                        </div>
                        <s:form id="filter"  label="Tìm kiếm " action="labels.action" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" cssStyle="" validate="false">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Mã mác tàu ví dụ: SE1,SE2</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Mã mác tàu<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control " type="hidden" name="format" value="add">
                                        <input class="form-control business_skill_id" type="hidden" name="item.id">
                                        <input class="form-control span2 input-s-lg inline business_skill_name" name="item.name" required>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Dữ liệu dạng số 0,1 .Bằng "1" tức tàu chở khách, "0" là tàu hàng</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Loại tàu<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control span2 input-s-lg inline business_skill_classification"  name="item.classification">
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Độ ưu tiên là giá trị hàng trăm 100,200,300,400,...Càng lớn độ ưu tiên càng cao.</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Độ ưu tiên<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control span2 input-s-lg inline business_skill_priority" name="item.priority"  required>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Thời gian rời ga theo giờ-phút từ lúc xuất phát</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Thời gian rời ga<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control  span2 input-s-lg inline business_skill_departure" name="departure" pattern="(([0-9][0-9])|([0-9][0-9][0-9])|([0-9])):([0-9][0-9])" title="Dạng HH:mm, Ví dụ: 07:07" >
                                            <%--<input class="form-control  span2 input-s-lg inline" style="width:60px;" type="number" name="departure1" required>&nbsp; giờ
                                            <input class="form-control  span2 input-s-lg inline" style="width:60px;" type="number" name="departure2" required> &nbsp;phút--%>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                                <button type="submit" class="btn btn-primary">Cập nhâp</button>
                            </div>
                        </s:form>
                    </div>
                </div>
            </div>
            <%--Add plan--%>
            <div class="modal fade"  id="addPlan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" >Thêm kế hoạch mác tàu</h4>
                        </div>
                        <s:form  method="post" id="filter"  label="Tìm kiếm " action="labels.action" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" cssStyle="" validate="false">
                            <div class="modal-body">
                                <input class="form-control " type="hidden" name="format" value="addPlan">
                                <input class="form-control " type="hidden" name="plan.id">
                                <input class="form-control " type="hidden" name="labelId" value="<s:property value="labelId"/>" >
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Ga tàu dừng trên đường đi</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Ga dừng/đỗ<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <s:select theme="simple"
                                                  cssClass="selectpicker span2 input-s-lg form-control inline"
                                                  label="Chọn ga dừng/đỗ"
                                                  list="stations"
                                                  listKey="id"
                                                  listValue="name"
                                                  name="stationId"
                                                  headerKey=""
                                                  headerValue="---Chọn---"
                                                  data-width="auto"
                                                  cssStyle="width:250px"
                                                  require="true"
                                                />

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Trực thuộc khu đoạn nào</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Khu đoạn<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <s:select theme="simple"
                                                  cssClass="selectpicker span2 input-s-lg form-control inline"
                                                  label="Thuộc khu đoạn"
                                                  list="stages"
                                                  listKey="id"
                                                  listValue="name"
                                                  name="stageId"
                                                  headerKey=""
                                                  headerValue="---Chọn---"
                                                  data-width="auto"
                                                  cssStyle="width:250px"
                                                  require="true"
                                                />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Ga này là điểm dừng thứ mấy trong hành trình, nhập dạng số 1,2,3,...</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Thứ tự điểm dừng đỗ<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control span2 input-s-lg inline" name="plan.orderStation"  required>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Có phải là ga tác nghiệp của tàu không.Chọn "Có" hoặc "Không"</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Ga tác nghiệp<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <s:select label="loại"
                                                  name="plan.wait"
                                                  cssClass="span2 input-s-lg form-control inline"
                                                  headerKey="0" headerValue="Chọn"
                                                  list="#{'0':'Có', '1':'Không'}"
                                                  value="plan.wait"
                                                  required="true"
                                                />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Thời gian tới ga theo giờ-phút từ lúc xuất phát</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Thời gian tới ga<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control  span2 input-s-lg inline business_skill_arrivalTime" name="arrival" pattern="(([0-9][0-9])|([0-9][0-9][0-9])|([0-9])):([0-9][0-9])" title="Dạng HH:mm, Ví dụ: 07:07" >
                                        <%--<input class="form-control  span2 input-s-lg inline" style="width:60px;" type="number" name="arrivalTime1" required>&nbsp; giờ
                                        <input class="form-control  span2 input-s-lg inline" style="width:60px;" type="number"  name="arrivalTime2" required> &nbsp;phút--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Thời gian rời ga theo giờ-phút từ lúc xuất phát</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Thời gian rời ga<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control  span2 input-s-lg inline business_skill_departureTime" name="departure" pattern="(([0-9][0-9])|([0-9][0-9][0-9])|([0-9])):([0-9][0-9])" title="Dạng HH:mm, Ví dụ: 07:07" >
                                        <%--<input class="form-control  span2 input-s-lg inline" style="width:60px;" type="number" name="departure1" required>&nbsp; giờ
                                        <input class="form-control  span2 input-s-lg inline" style="width:60px;" type="number" name="departure2" required> &nbsp;phút--%>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                                <button type="submit" class="btn btn-primary">Cập nhâp</button>
                            </div>
                        </s:form>
                    </div>
                </div>
            </div>
            <%--Edit plan--%>

            <div class="modal fade" id="editPlan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" >Thêm kế hoạch mác tàu</h4>
                        </div>
                        <s:form id="filter" method="POST" label="Tìm kiếm " action="labels.action" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" cssStyle="" validate="false">
                            <div class="modal-body">
                                <input class="form-control " type="hidden" name="format" value="editPlan">
                                <input class="form-control business_skill_id" type="hidden" name="plan.id">
                                <input class="form-control " type="hidden" name="labelId" value="<s:property value="labelId"/>" >
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Ga tàu dừng trên đường đi</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Ga dừng/đỗ<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <s:select theme="simple"
                                                  cssClass="selectpicker span2 input-s-lg form-control inline business_skill_station"
                                                  label="Chọn ga dừng/đỗ"
                                                  list="stations"
                                                  listKey="id"
                                                  listValue="name"
                                                  name="plan.station.id"
                                                  headerKey=""
                                                  headerValue="---Chọn---"
                                                  data-width="auto"
                                                  cssStyle="width:250px"
                                                  require="true"
                                                />

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Trực thuộc khu đoạn nào</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Khu đoạn<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <s:select theme="simple"
                                                  cssClass="selectpicker span2 input-s-lg form-control inline business_skill_stage"
                                                  label="Thuộc khu đoạn"
                                                  list="stages"
                                                  listKey="id"
                                                  listValue="name"
                                                  name="plan.stage.id"
                                                  headerKey=""
                                                  headerValue="---Chọn---"
                                                  data-width="auto"
                                                  cssStyle="width:250px"
                                                  value=""
                                                  require="true"
                                                />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Ga này là điểm dừng thứ mấy trong hành trình, nhập dạng số 1,2,3,...</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Thứ tự điểm dừng đỗ<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control span2 input-s-lg inline business_skill_orderStation " name="plan.orderStation"   required>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Có phải là ga tác nghiệp của tàu không.Chọn "Có" hoặc "Không"</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Ga tác nghiệp<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <s:select label="loại"
                                                  name="plan.wait"
                                                  cssClass="span2 input-s-lg form-control inline business_skill_wait"
                                                  headerKey="0" headerValue="Chọn"
                                                  list="#{'true':'Có', 'false':'Không'}"
                                                  value="plan.wait"
                                                  required="true"
                                                />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Thời gian tới ga theo giờ-phút tính từ lúc xuất phát, định dạng HH:mm</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Thời gian tới ga<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control  span2 input-s-lg inline business_skill_arrivalTime"  name="arrival" pattern="(([0-9][0-9])|([0-9][0-9][0-9])|([0-9])):([0-9][0-9])" title="Dạng HH:mm, Ví dụ: 07:07"  >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                        <i>Thời gian rời ga theo giờ-phút tính từ lúc xuất phát, định dạng HH:mm</i>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Thời gian rời ga<code>(*)</code></label>
                                    <div class="col-sm-9">
                                        <input class="form-control  span2 input-s-lg inline business_skill_departureTime" name="departure" pattern="(([0-9][0-9])|([0-9][0-9][0-9])|([0-9])):([0-9][0-9])" title="Dạng HH:mm, Ví dụ: 07:07"  >
                                       <%-- <input class="form-control  span2 input-s-lg inline business_skill_departureTime"  name="plan.departureTime" required>--%>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                                <button type="submit" class="btn btn-primary">Cập nhâp</button>
                            </div>
                        </s:form>
                    </div>
                </div>
            </div>
    <%--end--%>
        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>
<script src="/vnr/assets/js/jquery-1.11.1.min.js"></script>
<script>

    $(document).ready(function() {
        /*
          View format table train
         */
        var tablelabel = $('#label').DataTable();
        $('#label tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                tablelabel.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        } );

        /*
            View format table plan
         */

        var tablePlan = $('#plans').DataTable();
        $('#plans tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                tablePlan.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        } );

        /*
           Logic show
         */
        $(".edit_button").click(function(e){
            var name = $(this).data('item.name');
            var id = $(this).data('item.id');
            var classification = $(this).data('item.classification');
            var departure=$(this).data('item.departure');
            var priority = $(this).data('item.priority');

            $(".business_skill_id").val(id);
            $(".business_skill_name").val(name);
            $(".business_skill_priority").val(priority);
            $(".business_skill_departure").val(departure);
            $(".business_skill_classification").val(classification);

        });
        $(".edit_plan").click(function(){
            var id=$(this).data('plan.id');
            var wait=$(this).data('plan.wait');
            var stage=$(this).data('plan.stage.id');
            var station=$(this).data('plan.station.id');
            var arrivalTime=$(this).data('plan.arrival');
            var departureTime=$(this).data('plan.departure');
            var orderStation=$(this).data('plan.ordersta');

            $(".business_skill_id").val(id);
            $(".business_skill_wait").val(wait);
            $(".business_skill_stage").val(stage);
            $(".business_skill_station").val(station);
            $(".business_skill_arrivalTime").val(arrivalTime);
            $(".business_skill_departureTime").val(departureTime);
            $(".business_skill_orderStation").val(orderStation);
        });

    });

</script>


