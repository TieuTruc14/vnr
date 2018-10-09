<%--
  Created by IntelliJ IDEA.
  User: TieuTruc
  Date: 12/17/2014
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<section id="content">
    <section class="vbox">
        <section class="scrollable padder">
            <ul class="breadcrumb no-border no-radius b-b b-light pull-in"> <li><a href="/mdms/index.action"><i class="fa fa-home"></i> Trang chủ</a></li> <li><a href="/mdms/report/index.action">Kế hoạch</a></li> <li><a href="#">Quản lý đoàn tàu mẫu</a></li></ul>
            <section class="panel panel-default">
                <header class="panel-heading"><i class="fa fa-caret-square-o-right"></i> Sửa lịch trình tàu mác <s:property value="trainReferenceId" /> - Khởi hành lúc  <span class="badge bg-info h4"><s:property value="@org.apache.commons.lang3.time.DurationFormatUtils@formatDuration(trainReference.departureTime, 'HH:mm')"/></span>
                    <ul class="nav nav-pills pull-right"><li><a href="#" class="panel-toggle text-muted"><i class="fa fa-caret-down text-active"></i><i class="fa fa-caret-up text"></i></a></li></ul>
                </header>
                <section class="panel-body">
                    <s:form id="filter" label="Tìm kiếm " action="/vnr/scheduling/stopReference.edit.action?format=edit" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" cssStyle="" validate="false">

                        <div class="form-group" hidden="true" >
                            <label class="col-sm-2 control-label">Tên mác</label>
                            <div class="col-sm-10">
                                <s:textfield name="trainReferenceId" cssClass="span2 input-s-lg input-md form-control inline" required="true"></s:textfield>
                            </div>
                        </div>
                        <div class="form-group" hidden="true" >
                            <label class="col-sm-2 control-label">Tên mác</label>
                            <div class="col-sm-10">
                                <s:textfield name="stopReferenceId" cssClass="span2 input-s-lg input-md form-control inline" required="true"></s:textfield>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Chọn ga lịch trình tàu đi qua</i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2 ">Ga tàu</label>
                            <div class="col-sm-10">
                                <s:select theme="simple"
                                          cssClass="selectpicker span2 inline "
                                          label="Chọn ga"
                                          list="ListStationInterceptor_stations"
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
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Thời gian tới ga, được tính bằng thời gian từ ga khởi hành tới ga hiện tại(Giả sử SE1 xuất phát ga HN lúc 19h, tới Thường tín lúc 20h30 thì thời gian đến ga thường tín điền là 1:30)
                                    Từ HN vào Sài Gòn hết 2 ngày thì ghi 48:00</i>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Thời gian tới ga</label>
                            <div class="col-sm-10">
                                <s:textfield name="arrivalTime" pattern="(([0-9][0-9])|([0-9][0-9][0-9])|([0-9])):([0-5][0-9])" Class=" span2 input-s-lg form-control inline" title="Định dạng HH:mm, ex:05:46" required="true" ></s:textfield>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Thời gian rời ga, được tính bằng thời gian từ ga khởi hành tới ga hiện tại(Giả sử SE1 xuất phát ga HN lúc 19h, rời Thường tín lúc 21h30 thì thời gian đến ga thường tín điền là 2:30)</i>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Thời gian rời ga</label>
                            <div class="col-sm-10">
                                <s:textfield name="departureTime" pattern="(([0-9][0-9])|([0-9][0-9][0-9])|([0-9])):([0-5][0-9])" Class=" span2 input-s-lg form-control inline" title="Định dạng HH:mm, ex:05:46" required="true" ></s:textfield>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Nếu là ga tác nghiệp thì check còn không thì bỏ trống.</i>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Ga tác nghiệp</label>
                            <div class="col-sm-10">
                                <s:textfield name="wait" cssClass="span2 input-s-lg input-md form-control inline" ></s:textfield>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <label class="text-success control-label"><s:property value="status"/> </label>
                            </div>
                        </div>
                        <div class="form-group" >
                            <div class="line line-dashed line-lg pull-in"></div>
                            <div class="form-group col-md-12"> <div class="col-sm-12 col-sm-offset-2">
                                <button type="submit" class="btn btn-primary">Lưu</button>
                                <button  class="btn btn-info"><a href="/vnr/scheduling/trainReference.detail.action?id=<s:property value="trainReferenceId"/> " class="text-white">Quay lại</a></button>
                            </div> </div>
                        </div>
                    </s:form>
                </section>
            </section>
        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>

<link rel="stylesheet" href="/vnr/assets/js/select2/select2.css" type="text/css" />
<script src="/vnr/assets/js/select2/select2.min.js"></script>
<script>
    $(document).ready(function() {
        $("#filter_stationId").select2();
    });
</script>