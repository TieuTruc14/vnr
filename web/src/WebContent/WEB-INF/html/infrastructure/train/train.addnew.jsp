<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<section id="content">
    <section class="vbox">
        <section class="scrollable padder">
            <ul class="breadcrumb no-border no-radius b-b b-light pull-in"> <li><a href="/mdms/index.action"><i class="fa fa-home"></i> Trang chủ</a></li> <li><a href="/mdms/report/index.action">Hạ tầng</a></li> <li><a href="#">Quản lý đoàn tàu</a></li></ul>
            <div class="m-b-md"> <h3 class="m-b-none">Thêm đoàn tàu</h3> </div>

            <section class="panel panel-default">
                <header class="panel-heading"><i class="fa fa-caret-square-o-right"></i> Thông tin
                    <ul class="nav nav-pills pull-right"><li><a href="#" class="panel-toggle text-muted"><i class="fa fa-caret-down text-active"></i><i class="fa fa-caret-up text"></i></a></li></ul>
                </header>
                <section class="panel-body">
                    <s:form id="filter" label="Tìm kiếm " action="train.addnew.action" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" cssStyle="" validate="false">
                        <div class="form-group">
                        </div>
                        <div class="form-group" hidden="true">
                            <label class="col-sm-2 control-label">Mã đoàn tàu</label>
                            <div class="col-sm-8">
                                <s:textfield name="item.id" cssClass="span2 input-s-lg input-md form-control inline"></s:textfield>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Chọn mác tầu để thêm đoàn tầu mẫu, Hệ thống sẽ lấy kế hoạch của mác tàu làm cơ sở xây dựng kế hoạch cho đoàn tầu mẫu</i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Mác tàu <code>(*)</code></label>
                            <div class="col-sm-9">
                                <s:select theme="simple"
                                          cssClass="selectpicker span2 input-small input-sm input-s-sm form-control inline"
                                          label="Chọn mác tàu"
                                          list="ListTrainReferenceInterceptor_trainReferences"
                                          listKey="id"
                                          listValue="name"
                                          name="item.label.id"
                                          headerKey=""
                                          headerValue="Chọn mác tàu"
                                          data-width="auto"
                                          cssStyle="width:300px"
                                        />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Chọn thông tin về ngày giờ xuất phát, Hệ thống sẽ so sánh với thời gian xuất phát của mác tầu tạo ra kế hoạch cho đoàn tầu mẫu  </i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Thời gian rời ga <code>(*)</code></label>
                            <div class="col-sm-8">
                                <s:textfield name="strBeginTime" cssClass="span2 input-s-lg form-control inline" ></s:textfield>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i> Ghi chú các thông tin cần chú ý khi vận hành</i>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Ghi chú</label>
                            <div class="col-sm-8">
                                <s:textfield name="item.description" cssClass="span2 input-s-lg form-control inline" ></s:textfield>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-9">
                                <label  Class="span2 text-success control-label inline"><s:property value="messageOk"/> </label>
                                <label  Class="span2 text-danger control-label inline"><s:property value="messageErr"/> </label>
                            </div>
                        </div>

                        <div class="form-group" >
                            <div class="line line-dashed line-lg pull-in"></div>
                            <div class="form-group col-md-12"> <div class="col-sm-12 col-sm-offset-2"> <button type="submit" class="btn btn-primary">Lưu</button>  <button  class="btn btn-info"><a href="trains.action" class="text-white">Quay lại</a></button>  </div> </div>
                        </div>
                    </s:form>
                </section>
            </section>
        </section>

    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>