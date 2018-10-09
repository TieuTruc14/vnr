<%--
  Created by IntelliJ IDEA.
  User: TieuTruc
  Date: 11/13/2014
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<section id="content">
    <section class="vbox">
        <section class="scrollable padder">
            <ul class="breadcrumb no-border no-radius b-b b-light pull-in"> <li><a href="/mdms/index.action"><i class="fa fa-home"></i> Trang chủ</a></li> <li><a href="/mdms/report/index.action">Báo cáo</a></li> <li><a href="#">Tổng hợp</a></li> <li class="active">cấp Sở</li> </ul>
            <div class="m-b-md"> <h3 class="m-b-none">Sửa thông tin ga</h3> </div>

                <section class="panel panel-default">
                    <header class="panel-heading"><i class="fa fa-caret-square-o-right"></i> Thông tin
                        <ul class="nav nav-pills pull-right"><li><a href="#" class="panel-toggle text-muted"><i class="fa fa-caret-down text-active"></i><i class="fa fa-caret-up text"></i></a></li></ul>
                    </header>
                    <section class="panel-body">
                        <s:form id="filter" label="Tìm kiếm " action="station.edit.action" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" cssStyle="" validate="false">
                            <div class="form-group">
                                <div class="form-group" hidden="true">
                                    <label class="col-sm-2 control-label">Id</label>
                                    <div class="col-sm-10">
                                        <s:textfield name="item.id" cssClass="span2 input-s-lg input-md form-control inline"></s:textfield>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label"></label>
                                <div class="col-sm-10">
                                    <i>Nhập thông tin tên Ga (ex: Hà Nội)</i>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Tên ga <code>(*)</code></label>
                                <div class="col-sm-10">
                                    <s:textfield name="item.name" cssClass="span2 input-s-lg input-md form-control inline"></s:textfield>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"></label>
                                <div class="col-sm-10">
                                    <i>Nhập thông tin lý trình của Ga (ex: Km16+500)</i>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Lý trình <code>(*)</code></label>
                                <div class="col-sm-10">
                                    <s:textfield name="item.km" cssClass="span2 input-s-lg input-md form-control inline"></s:textfield>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"></label>
                                <div class="col-sm-10">
                                    <i>Nhập thông tin vị trí của Ga theo kinh độ (nhập số ex: 21.678568)</i>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Kinh độ</label>
                                <div class="col-sm-10">
                                    <s:textfield name="item.y" cssClass="span2 input-s-lg input-md form-control inline"></s:textfield>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"></label>
                                <div class="col-sm-10">
                                    <i>Nhập thông tin vị trí của Ga theo vĩ độ (nhập số ex: 105.678568)</i>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Vĩ độ</label>
                                <div class="col-sm-10">
                                    <s:textfield name="item.x" cssClass="span2 input-s-lg input-md form-control inline"></s:textfield>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"></label>
                                <div class="col-sm-10">
                                    <i>Nhập thông tin khoảng cách so với Ga đầu tiên tính theo mét ( nhập số ex: 95600)</i>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Khoảng cách(km)</label>
                                <div class="col-sm-9">
                                    <s:textfield name="item.distance" cssClass="span2 input-mini form-control inline"></s:textfield>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label"></label>
                                <div class="col-sm-9">
                                    <label  Class="span2 text-success control-label inline"><s:property value="Status"/> </label>
                                </div>
                            </div>

                            <div class="form-group" >
                                <div class="line line-dashed line-lg pull-in"></div>
                                <div class="form-group col-md-12"> <div class="col-sm-12 col-sm-offset-2"> <button type="submit" class="btn btn-primary">Lưu</button>  <button  class="btn btn-info"><a href="stations.action" class="text-white">Quay lại</a></button>  </div> </div>
                            </div>
                        </s:form>
                    </section>
                </section>
        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>