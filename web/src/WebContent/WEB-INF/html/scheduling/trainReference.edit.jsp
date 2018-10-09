<%--
  Created by IntelliJ IDEA.
  User: TieuTruc
  Date: 12/15/2014
  Time: 10:34 AM
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
            <div class="m-b-md"> <h3 class="m-b-none">Thêm tàu mẫu</h3> </div>

            <section class="panel panel-default">
                <header class="panel-heading"><i class="fa fa-caret-square-o-right"></i> Thông tin
                    <ul class="nav nav-pills pull-right"><li><a href="#" class="panel-toggle text-muted"><i class="fa fa-caret-down text-active"></i><i class="fa fa-caret-up text"></i></a></li></ul>
                </header>
                <section class="panel-body">
                    <s:form id="filter" label="Tìm kiếm " action="/vnr/scheduling/trainReference.edit.action?format=edit" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" cssStyle="" validate="false">
                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Tên mác tàu, ex: SE1,SE2,...</i>
                            </div>
                        </div>
                        <div class="form-group"  >
                            <label class="col-sm-2 control-label">Tên mác</label>
                            <div class="col-sm-10">
                                <s:textfield name="item.name" cssClass="span2 input-s-lg input-md form-control inline" disabled="true" required="true"></s:textfield>
                            </div>
                        </div>
                        <div class="form-group" hidden="true" >
                            <label class="col-sm-2 control-label">Tên mác</label>
                            <div class="col-sm-10">
                                <s:textfield name="item.id" cssClass="span2 input-s-lg input-md form-control inline"  required="true"></s:textfield>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Chọn loại tàu: khách hoặc hàng</i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Loại tàu</label>
                            <div class="col-sm-10">
                                <s:select label="loại"
                                          name="item.classification"
                                          cssClass="span2 input-s-lg form-control inline"
                                          headerKey="false" headerValue="Chọn"
                                          list="#{'0':'Tàu khách', '1':'Tàu hàng'}"
                                          value="item.classification"
                                          required="true"
                                        />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Chọn độ ưu tiên mác tàu.Độ ưu tiên càng cao thì giá trị càng lớn.Cao nhất là 500 tương ứng SE3/SE4,
                                    400 tương ứng SE1/SE2, tàu khách nhanh,..., 300- tàu khách thường, 200-tàu GS,HGĐ,HSĐ... , 100- tàu hàng</i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Độ ưu tiên</label>
                            <div class="col-sm-10">
                                <s:select label="loại"
                                          name="item.priority"
                                          cssClass="span2 input-s-lg form-control inline"
                                          headerKey="false" headerValue="Chọn"
                                          list="{100,200,300,400,500}"
                                          value="item.priority"
                                          required="true"
                                        />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-10">
                                <i>Thời gian xuất phát trong ngày nhập vào dạng HH:mm, ví dụ 6h30' sáng thì nhập 6:30, 2h20 chiều thì nhập 14:20</i>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Thời gian xuất phát</label>
                            <div class="col-sm-10">
                                <s:textfield type="text" name="departureTime" Class=" span2 input-s-lg form-control inline" pattern="(([0-1][0-9])|([2][0-3])|([0-9])):([0-5][0-9])" required="true" ></s:textfield>
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
                                <button  class="btn btn-info"><a href="/vnr/scheduling/trainReference.listing.action" class="text-white">Quay lại</a></button>
                            </div> </div>
                        </div>
                    </s:form>
                </section>
            </section>
        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>