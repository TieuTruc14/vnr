<%--
  Created by IntelliJ IDEA.
  User: TieuTruc
  Date: 11/15/2014
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<section id="content">
    <section class="vbox">
        <section class="scrollable padder">
            <ul class="breadcrumb no-border no-radius b-b b-light pull-in"> <li><a href="/mdms/index.action"><i class="fa fa-home"></i> Trang chủ</a></li> <li><a href="/mdms/report/index.action">Hạ tầng</a></li> <li><a href="#">Quản lý ga</a></li></ul>
            <div class="m-b-md"> <h3 class="m-b-none">Thêm kế hoạch lịch trình mác tàu <s:property value="train.name"/> </h3> </div>

            <section class="panel panel-default">
                <header class="panel-heading"><i class="fa fa-caret-square-o-right"></i> Thông tin
                    <ul class="nav nav-pills pull-right"><li><a href="#" class="panel-toggle text-muted"><i class="fa fa-caret-down text-active"></i><i class="fa fa-caret-up text"></i></a></li></ul>
                </header>
                <section class="panel-body">
                    <s:form id="filter" label="Tìm kiếm " action="plan.edit.action" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" cssStyle="" validate="false">
                        <div class="form-group" hidden="true">
                            <label class="col-sm-2 control-label">id</label>
                            <div class="col-sm-10">
                                <s:textfield name="trainId" cssClass="span2 input-s-lg input-md form-control inline"></s:textfield>
                            </div>
                        </div>
                        <div class="form-group" hidden="true">
                            <label class="col-sm-2 control-label">id</label>
                            <div class="col-sm-10">
                                <s:textfield name="item.id" cssClass="span2 input-s-lg input-md form-control inline"></s:textfield>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Ga dừng/đỗ</label>
                            <div class="col-sm-10">
                                <s:select theme="simple"
                                          cssClass="selectpicker span2 input-small input-sm input-s-sm form-control inline"
                                          label="Chọn ga dừng/đỗ"
                                          list="stations"
                                          listKey="id"
                                          listValue="name"
                                          name="item.station.id"
                                          headerKey=""
                                          headerValue="Chọn ga dừng/đỗ"
                                          data-width="auto"
                                          cssStyle="width:300px"
                                          require="true"
                                        />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Thời gian tới ga</label>
                            <div class="col-sm-10">
                                <s:textfield name="item.arrivalTime" cssClass=" span2 input-s-lg form-control inline" ></s:textfield>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Thời gian rời ga</label>
                            <div class="col-sm-10">
                                <s:textfield name="item.departureTime" cssClass="span2 input-s-lg form-control inline" ></s:textfield>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Ga tác nghiệp</label>
                            <div class="col-sm-10">
                                <s:select label="loại"
                                          name="item.wait"
                                          cssClass="span2 input-s-lg form-control inline"
                                          headerKey="0" headerValue="Chọn"
                                          list="#{'0':'Có', '1':'Không'}"
                                          value="item.wait"
                                          required="true"
                                        />
                            </div>
                        </div>
                        <div class="form-group" >
                            <div class="line line-dashed line-lg pull-in"></div>
                            <div class="form-group col-md-12"> <div class="col-sm-12 col-sm-offset-2">
                                <button type="submit" class="btn btn-primary">Lưu</button>
                                <button  class="btn btn-info"><a href="plans.action?trainId=<s:property value="trainId" /> " class="text-white">Quay lại</a></button>
                            </div> </div>
                        </div>
                    </s:form>
                </section>
            </section>
        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>