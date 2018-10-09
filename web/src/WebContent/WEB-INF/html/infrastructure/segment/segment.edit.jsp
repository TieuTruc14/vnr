<%--
  Created by IntelliJ IDEA.
  User: TieuTruc
  Date: 11/18/2014
  Time: 5:25 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<section id="content">
    <section class="vbox">
        <section class="scrollable padder">
            <ul class="breadcrumb no-border no-radius b-b b-light pull-in"> <li><a href="/vnr/index.action"><i class="fa fa-home"></i> Trang chủ</a></li> <li><a href="/vnr/report/index.action">Hạ tầng</a></li> <li><a href="#">Quản lý khu gian</a></li></ul>
            <div class="m-b-md"> <h3 class="m-b-none">Sửa khu gian</h3> </div>

            <section class="panel panel-default">
                <header class="panel-heading"><i class="fa fa-caret-square-o-right"></i> Thông tin
                    <ul class="nav nav-pills pull-right"><li><a href="#" class="panel-toggle text-muted"><i class="fa fa-caret-down text-active"></i><i class="fa fa-caret-up text"></i></a></li></ul>
                </header>
                <section class="panel-body">
                    <s:form id="filter" label="Tìm kiếm " action="segment.edit.action" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" cssStyle="" validate="false">
                        <div class="form-group" hidden="true">
                            <label class="col-sm-2 control-label">Id</label>
                            <div class="col-sm-10">
                                <s:textfield name="item.id" cssClass="span2 input-s-lg input-md form-control inline"></s:textfield>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Đặt tên khu gian theo cấu trúc GaA-GaB (ex: Hà Nội-Giáp Bát)</i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Tên</label>
                            <div class="col-sm-10">
                                <s:textfield name="item.name" cssClass="span2 input-s-lg input-md form-control inline"></s:textfield>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Chọn Ga Bắt đầu cho khu gian(Ex : Hà Nội)</i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Ga A <code>(*)</code></label>
                            <div class="col-sm-10">
                                <s:select theme="simple"
                                          cssClass="selectpicker span2 input-small input-sm input-s-sm form-control inline"
                                          label="Chọn ga"
                                          list="ListStationInterceptor_stations"
                                          listKey="id"
                                          listValue="name"
                                          name="item.stationA.id"
                                          headerKey=""
                                          headerValue="Chọn ga"
                                          data-width="auto"
                                          cssStyle="width:300px"
                                        />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Chọn Ga kết thúc cho khu gian(Ex : Giáp Bát)</i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Ga B <code>(*)</code></label>
                            <div class="col-sm-10">
                                <s:select theme="simple"
                                          cssClass="selectpicker span2 input-small input-sm input-s-sm form-control inline"
                                          label="Chọn ga"
                                          list="ListStationInterceptor_stations"
                                          listKey="id"
                                          listValue="name"
                                          name="item.stationB.id"
                                          headerKey=""
                                          headerValue="Chọn ga"
                                          data-width="auto"
                                          cssStyle="width:300px"
                                        />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Nhập đọ dài khu gian tính theo km (Khoảng cách tính từ tâm Ga A đến tâm Ga B) (Ex : 12.5)</i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Độ dài(km)</label>
                            <div class="col-sm-10">
                                <s:textfield name="item.distance" cssClass="span2 input-s-lg input-md form-control inline"></s:textfield>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-9">
                                <label  Class="span2 text-success control-label inline"><s:property value="Status"/> </label>
                            </div>
                        </div>

                        </div>
                        <div class="form-group" >
                            <div class="line line-dashed line-lg pull-in"></div>
                            <div class="form-group col-md-12"> <div class="col-sm-12 col-sm-offset-2"> <button type="submit" class="btn btn-primary">Lưu</button>  <button  class="btn btn-info"><a href="segments.action" class="text-white">Quay lại</a></button>  </div> </div>
                        </div>
                    </s:form>
                </section>
            </section>

        </section>

    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>