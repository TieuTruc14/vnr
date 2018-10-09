<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<section id="content">
    <ul class="breadcrumb m-b-none">
        <li><a href="#"><i class="fa fa-home"></i> Trang chủ</a></li>
        <li><a href="#"> Lịch trình</a></li>
        <li><a href="#"> Kế hoạch ngày</a></li>
    </ul>

    <section class="vbox">
        <section class="scrollable wrapper w-f bg-white-only">
            <section class="panel panel-default bg-light lt">
                <div class="panel-body">
                    <s:form id="filter" label="Tìm kiếm" style="width:100%!important;" action="daily.action"
                            method="get" theme="simple" enctype="multipart/form-data" cssClass="form-inline pull-left"
                            cssStyle="width:100%;" validate="false">
                        <div class="form-group" Style="width:100%">
                            <div class="col-xs-7" style="text-align:left">
                                <%--<button id="btnUnZoom" type="button" class="btn btn-primary btn-sm">UnZoom</button>--%>
                                <a href="/vnr/scheduling/daily.addTrains.action" class="btn btn-success btn-sm">Thêm
                                    Tàu</a>
                                <a href="/vnr/scheduling/daily.deleteTrains.action" class="btn btn-danger btn-sm">Xóa
                                    lịch trình tàu</a>
                                <a href="/vnr/scheduling/detect.conflicts.action" class="btn btn-primary btn-sm">Tìm
                                    xung đột !</a>
                            </div>
                            <%--<div class="pull-right col-xs-5">
                                Mốc thời gian khóa biểu kế hoạch:<s:property value="timelock"/>
                                    <button id="editTimeLock" type="button" class="btn btn-success btn-sm">Sửa</button>
                            </div>--%>
                        </div>
                        <div class="line-dashed" style="margin:20px 0px;"></div>
                        <div class="form-group">
                            <label class="control-label">Chọn thời gian </label>
                            <s:textfield key="start" placeholder="Ngày" theme="simple"
                                         cssClass="input-small input-sm input-s-sm form-control inline"/>
                            <span class="add-on">-</span>
                            <s:textfield key="end" placeholder="Ngày" theme="simple"
                                         cssClass="input-small input-sm input-s-sm form-control inline"/>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Bàn làm việc </label>
                            <s:select theme="simple"
                                      cssClass="selectpicker span2 input-s-lg form-control inline"
                                      label="Chọn bàn làm việc"
                                      list="ListWorkbenchInterceptor_Workbenchs"
                                      listKey="id"
                                      listValue="name"
                                      name="workbenchId"
                                      headerKey=""
                                      headerValue="---Chọn---"
                                      data-width="auto"
                                      cssStyle="width:250px"
                                      require="true"
                                    />
                        </div>
                        <button type="submit" class="btn btn-primary btn-sm">Tải lịch trình</button>
                        <a href="/vnr/scheduling/daily.print.action?start=<s:property value="start"/>&end=<s:property value="end"/>&workbenchId=<s:property value="workbenchId"/> " class="btn btn-success btn-sm">In lịch trình</a>
                    </s:form>

                </div>
            </section>

            <div id="multipleCharts"></div>

            <div id="container" style="height: auto; min-width: 310px; width: auto"></div>

            <div id="dlgEditStop" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 id="myModalLabel" class="modal-title">Modal title</h4>
                        </div>
                        <div class="modal-body">

                            <p>Mác Tàu: <span id="dlgEditStop_TrainName"></span></p>

                            <p>Điểm dừng: <span id="dlgEditStop_Stop"></span></p>

                            <p>TG Đến: <span id="dlgEditStop_TimeIn"></span></p>

                            <p>TG Rời: <span id="dlgEditStop_TimeOut"></span></p>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>

        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>

<link rel="stylesheet" type="text/css" href="/vnr/assets/js/bootstrap3-dialog/bootstrap-dialog.min.css"/>
<script type="text/javascript" src="/vnr/assets/js/bootstrap3-dialog/bootstrap-dialog.min.js"></script>
<script type="text/javascript" src="/vnr/assets/js/Highstock-2.0.4/js/highstock.js"></script>
<%--<script type="text/javascript" src="/vnr/assets/js/Highstock-2.0.4/js/modules/exporting.js"></script>--%>
<script type="text/javascript" src="/vnr/assets/js/moment.min.js"></script>
<%--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.2/themes/smoothness/jquery-ui.css">--%>

<script type="text/javascript">


var charts = [], stageStations = [], stations = [];

$(document).ready(function () {
    Highcharts.setOptions({ global: { useUTC: false } });

    <s:iterator var="itemStage" value="stages" status="stat">
    <s:set var="listStationStages" value="getStationStages(#itemStage)" />
    <s:if test="#listStationStages.size > 0">
    <s:if test="#listStationStages.size > 3">
    <s:set var="paneHeight" value="#listStationStages.size * 40"  />
    </s:if><s:else>
    <s:set var="paneHeight" value="230"  />
    </s:else>
    stations['<s:property value="#itemStage" />'] = [
        <s:iterator var="itemStation" value="#listStationStages" status="statStation">
        {
            id: '<s:property value="#itemStation.station.id" />',
            name: "<s:property value="#itemStation.station.name" escapeHtml="false" />",
            feature: false,
            distance: <s:property value="#itemStation.distance" />,
            km:<s:property value="#itemStation.km"/>,
            stationStageId: '<s:property value="#itemStation.id" /> '
        }<s:if test="!#statStation.last">,
        </s:if>

        </s:iterator>
    ];
    stageStations['<s:property value="#itemStage.id" />'] = stations['<s:property value="#itemStage" />'];

    divSectionChart = $('<section class="panel panel-default"></section>').appendTo($("#multipleCharts"))[0];
    divSectionChartHeader = $('<header class="panel-heading"><s:property value="#itemStage.name" /> <i class="fa fa-info-sign text-muted"></i></header>').appendTo(divSectionChart)[0];
    divSectionChartBody = $('<div class="panel-body no-padder" style="height: auto; min-width: 310px; width: auto"></div>').appendTo(divSectionChart)[0];

    // Create the chart
    chart = new Highcharts.StockChart({
        chart: { renderTo: divSectionChartBody, type: 'line', zoomType: "xy", height: <s:property value="#paneHeight" /> },
        rangeSelector: {enabled: false}, exporting: { enabled: false },
        plotOptions: {
            series: {
                cursor: 'pointer',
                marker: { enabled: true, radius: 4 },
                shadow: false, /* allowPointSelect: true,*/
                animation: false

            }
        },
        tooltip: { crosshairs: true, shared: false, snap: 1,
            formatter: function () {
                var distanceGaHientai = this.y;
                var tenga = "";
                $.each(stageStations['<s:property value="#itemStage.id" />'], function (index, item) {
                    if (item.distance == distanceGaHientai) {
                        tenga = item.name;
                        return false;
                    }
                });
                return 'Tàu ' + this.series.name + '--<b>' + tenga  +'--'+ moment(this.x).format('DD/MM/YYYY : HH:mm')+ '</b>'  ;
            } },
        yAxis: {
            lineWidth: 4, offset: 30, opposite: false, /* minTickInterval: 1, tickInterval: 1, min: -1000, max: 500000, */
            showFirstLabel: true, showLastLabel: true,
            <s:if test="#itemStage.arrangement">
            reversed: true,
            </s:if><s:else>
            reversed: false,
            </s:else>
            /* gridLineColor: '#e0e0e0', alternateGridColor: '#F7F7F7', gridZIndex: 1, */
            tickPositioner: function (min, max) {
                var i, tickPositions = [];
                tickPositions.push(min - max/<s:property value="#listStationStages.size" />/8);

                        for (i = 0; i < stageStations['<s:property value="#itemStage.id" />'].length; i++)
                {
                    if ((stageStations['<s:property value="#itemStage.id" />'][i].distance >= min) && (stageStations['<s:property value="#itemStage.id" />'][i].distance <= max)) {
                        tickPositions.push(stageStations['<s:property value="#itemStage.id" />'][i].distance);
                    }
                }

                tickPositions.push(max + max/<s:property value="#listStationStages.size" />/8);

                return tickPositions;
            },
            labels: {
                align: 'right', y: 3,
                formatter: function () {
                    var value = this.value;
                    var foundTick = false;
                    $.each(stageStations['<s:property value="#itemStage.id" />'], function (index, item) {
                        if (item.distance == value) {
                            foundTick = true;
                            value = item.name/* + ' [' + item.distance/1000 + ']'*/;
                            if (item.feature) {
                                value = '<span style="text-transform:uppercase; color: black; font-weight: bold;">' + value + '</span>';
                            }
                            return value;
                        }
                    });

                    if (!foundTick) {
                        value = "";
                    }

                    return value;
                }
            }
        },
        xAxis: {
            ordinal: false, opposite: true, min: <s:property value="dateStart.time" />, max: <s:property value="dateEnd.time" />,
            /** minTickInterval: 60 * 60 * 1000,  */ minorTickInterval: 10 * 60 * 1000,
            gridLineWidth: 2
            /** ,
             plotLines: [{ value: new Date(2012, 5, 5, 20, 00).getTime(), color: 'black', width: 2, dashStyle: 'Solid', zIndex: 5 }] */
        },
        series: [
            <s:set var="listStageTrainSchedule" value="getStageTrainScheduleListByStage(#itemStage)" />
            <s:iterator var="itemStageTrainSchedule" value="#listStageTrainSchedule" status="statStageTrainSchedule">
            <s:set var="lineColor" value="'red'" />
            <s:set var="lineWidth" value="1" />
            <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=100}">
            <s:set var="lineColor" value="'#666666'" />
            </s:if><s:else>
            <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=200}">
            <s:set var="lineColor" value="'green'" />
            </s:if><s:else>
            <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=300}">
            <s:set var="lineColor" value="'blue'" />
            </s:if><s:else>
            <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=400}">
            <s:set var="lineColor" value="'#FF1493'" />
            </s:if>
            </s:else>
            </s:else>
            </s:else>

            { id: <s:property value="#itemStageTrainSchedule.train.id" />, name: "<s:property value="#itemStageTrainSchedule.train.trainReference.name" />", marker: { symbol: 'circle', radius: 2 }, dashStyle: 'Solid', color: '<s:property value="#lineColor" />', lineWidth: <s:property value="#lineWidth" />, departureTime: <s:property value="#itemStageTrainSchedule.train.beginTime.time" />,
                data: [
                    <s:set var="fullStopStage" value="%{#itemStageTrainSchedule.getStops()}"/>
                    <s:set var="firstStop" value="%{#itemStageTrainSchedule.getStops().get(0)}"/>
                    <s:set var="numberLocation" value="getRandomNumber(#fullStopStage.size())"/>
                    <s:set var="stopNumberLocation" value="#fullStopStage.get((#fullStopStage.size()-1))" />
                    <s:iterator var="itemStop" value="#itemStageTrainSchedule.stops" status="statStop">
                    { x: <s:property value="#itemStop.arrivalTime" />, y: <s:property value="#itemStop.stationStage.distance" />},
                    { x: <s:property value="#itemStop.departureTime" />, y: <s:property value="#itemStop.stationStage.distance" />
                       <s:if test="(#statStop.count-1)==0">
                        ,
                        dataLabels: {
                            enabled: true,
                            borderRadius: 1,
                            borderColor: 'black',
                            zIndex:-6,
                            borderWidth: 1,
                            padding: 3,
                            format:"<s:property value="#itemStop.train.trainReference.id" />",
                            <s:if test="#stopNumberLocation.stationStage.distance > #firstStop.stationStage.distance">
                                 rotation:70,
                                 x:5
                            </s:if><s:else>
                                 rotation:-70,
                                 x:-5
                            </s:else>
                        }
                       </s:if>
                        <s:if test="(#itemStop.vehicle)!=null">
                        ,
                        dataLabels: {
                            enabled: true,
                            borderRadius: 1,
                            borderColor: 'red',
                            borderWidth: 1,
                            zIndex:-6,
                            padding: 3,
                            format:"<s:property value="#itemStop.vehicle" />",
                            <s:if test="#stopNumberLocation.stationStage.distance > #firstStop.stationStage.distance">
                            rotation:70,
                            x:-11
                            </s:if><s:else>
                            rotation:-70,
                            x:11
                            </s:else>
                        }
                        </s:if>
                    }
                    <s:if test="!#statStop.last">,
                    </s:if>
                    </s:iterator>
                ],
                rotation:60,

                events: {
                    click: function (e) {
//                      console.log("x: " + e.point.x + ", y: " + e.point.y);
//                      console.log("e.point.series.name: " + e.point.series.name + " " + e.point.series.options.id);
                        /* $("#dlgEditStop").modal('show');*/

                        var stopInfo = {
                            trainId: e.point.series.options.id,
                            trainName: e.point.series.name
                        };

                        $.each(stations['<s:property value="#itemStage" />'], function (index, item) {
                            if (item.distance == e.point.y) {
                                stopInfo.station = item;
                                return false;
                            }
                        });

//                        console.log(e);
//                        console.log(e.point);
//                        console.log(e.point.series.data);

                        $.each(e.point.series.data, function (index, item) {
                            if (item && (item.y == e.point.y)) { // to avoid undefined also
                                stopInfo.index = index;
                                return false;
                            }
                        });

                        if (stopInfo.index != undefined) {
                            stopInfo.arrivalTime = e.point.series.data[stopInfo.index].x;
                            stopInfo.departureTime = e.point.series.data[stopInfo.index + 1].x;
                        }

                        var dialogTitle = "<span class='label bg-light'>" + stopInfo.trainName + "</span>" + " <i class='fa fa-arrow-right'></i> " + "<span class='label bg-light'>" + stopInfo.station.name + "</span>";

                        BootstrapDialog.show({ title: dialogTitle,
                            message: '<form id="frmStopUpdate" name="filter" action="/vnr/scheduling/daily.stop.update.action"  enctype="multipart/form-data" class="form-inline" style="">' +
                                    '  <div class="form-group">' +
                                    '      <label class="control-label">Thời gian ( Tới - Rời ) Ga </label>' +
                                    '      <input id="frmStopUpdate_arrivalTime" type="text" name="arrivalTime" value="' + moment(stopInfo.arrivalTime).format("DD/MM/YYYY-HH:mm:ss") + '" class="input-small input-sm input-s-sm form-control inline" placeholder="Ngày">' +
                                    '      <span class="add-on">-</span>' +
                                    '      <input id="frmStopUpdate_departureTime" type="text" name="departureTime" value="' + moment(stopInfo.departureTime).format("DD/MM/YYYY-HH:mm:ss") + '" class="input-small input-sm input-s-sm form-control inline" placeholder="Ngày">' +
                                    '      <label class="control-label">->>Sửa thời gian </label>' +
                                    '      <input id="frmStopUpdate_check" name="checkEdit"  type="checkbox" class="checkbox m-n i-checks" />' +
                                    '      <input id="frmStopUpdate_trainId" name="trainId" type="hidden" value="' + stopInfo.trainId + '">' +
                                    '      <input id="frmStopUpdate_stationStageId" name="stationStageId" type="hidden" value="' + stopInfo.station.stationStageId + '">' +
                                    '      <input id="frmStopUpdate_stationId" name="stationId" type="hidden" value="' + stopInfo.station.id + '">' +
                                    '      <input id="frmStopUpdate_start" name="start" type="hidden" value="' + $("#filter_start").val() + '">' +
                                    '      <input id="frmStopUpdate_end" name="end" type="hidden" value="' + $("#filter_end").val() + '">' +
                                    '      <input id="frmStopUpdate_workbenchId" name="workbenchId" type="hidden" value="' + $("#filter_workbenchId").val() + '">' +
                                    '       </br>' +
                                    '      <label class="control-label">Gán là ga xuất phát </label>' +
                                    '      <input id="frmStopUpdate_check" name="check"  type="checkbox" class="checkbox m-n i-checks" />' +
                                    '       </br>' +
                                    '      <label class="control-label">Bổ sung đầu máy </label>' +
                                    '      <input id="frmStopUpdate_checkVehicle" name="checkVehicle"  type="checkbox" class="checkbox m-n i-checks" />' +
                                    <s:if test="stopInfo.vehicle!=null">
                                    '      <input id="frmStopUpdate_vehicle" type="text" name="vehicle" value="' + stopInfo.vehicle + '" class="input-small input-sm input-s-sm form-control inline" placeholder="Đầu máy">' +
                                    </s:if><s:else>
                                    '      <input id="frmStopUpdate_vehicle" type="text" name="vehicle" value="" class="input-small input-sm input-s-sm form-control inline" placeholder="Đầu máy">' +
                                    </s:else>

                                    '  </div>' +
                                    '</form>',
                            buttons: [
                                {
                                    label: 'Close', cssClass: 'btn-sm',
                                    action: function (dialogRef) {
                                        dialogRef.close();
                                    }
                                },
                                {
                                    label: 'OK', cssClass: 'btn-primary btn-sm',
                                    action: function (dialogRef) {
                                        $("#frmStopUpdate").submit();
                                    }
                                }
                            ]
                        });
                    }
                }

            },
            <s:set var="listStopByTrain" value="getStopsByTrain(#itemStageTrainSchedule.train)" />
            <s:set var="sizetest" value="#listStopByTrain.size()"/>

            <s:set var="stopStart" value="#listStopByTrain.get(0)"/>

            <s:set var="stopEnd" value="#listStopByTrain.get(#listStopByTrain.size()-1)"/>
            <s:set var="fullStopStage" value="%{#itemStageTrainSchedule.getStops()}"/>
             <s:set var="firstStop" value="%{#itemStageTrainSchedule.getStops().get(0)}"/>
            <s:set var="numberLocation" value="getRandomNumber(#fullStopStage.size())"/>
             <s:set var="titleFlagLocation" value="#fullStopStage.get(#numberLocation)" />

            {
                type : 'flags',
                data : [{
                    x : <s:property value="#itemStop.departureTime" />,
                   /* title :'<s:property value="#itemStageTrainSchedule.train.trainReference.id" />'*//*+moment(this.x).format("HH:mm")*//*,*/
                    title:' '
                }],
                onSeries:<s:property value="#itemStageTrainSchedule.train.id" />,

                <s:if test="#itemStop.stationStage.distance > #firstStop.stationStage.distance">
                     y:-3,
                    shape : "url(/vnr/assets/img/imageChart/goDownRight.png)",

                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=100}">
                        shape : "url(/vnr/assets/img/imageChart/goDownRight100.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=200}">
                        shape : "url(/vnr/assets/img/imageChart/goDownRight200.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=300}">
                        shape : "url(/vnr/assets/img/imageChart/goDownRight300.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=400}">
                        shape : "url(/vnr/assets/img/imageChart/goDownRight400.png)",
                        </s:if>
                        </s:else>
                        </s:else>
                        </s:else>
                     <s:if test="#stopEnd.departureTime==#itemStop.departureTime">
                    color : Highcharts.getOptions().colors[0],
                    fillColor : "#000000",
                    style : {// text style
                        color : '#000000',
                        top:-50
                    },
                    y:-4,
                    shape : "url(/vnr/assets/img/imageChart/end.png)",
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=100}">
                        shape : "url(/vnr/assets/img/imageChart/end100.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=200}">
                        shape : "url(/vnr/assets/img/imageChart/end200.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=300}">
                        shape : "url(/vnr/assets/img/imageChart/end300.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=400}">
                        shape : "url(/vnr/assets/img/imageChart/end400.png)",
                        </s:if>
                        </s:else>
                        </s:else>
                        </s:else>
                    </s:if>
                </s:if><s:else>
                  y:-25,
                    shape : "url(/vnr/assets/img/imageChart/goUpRight.png)",
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=100}">
                        shape : "url(/vnr/assets/img/imageChart/goUpRight100.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=200}">
                        shape : "url(/vnr/assets/img/imageChart/goUpRight200.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=300}">
                        shape : "url(/vnr/assets/img/imageChart/goUpRight300.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=400}">
                        shape : "url(/vnr/assets/img/imageChart/goUpRight400.png)",
                        </s:if>
                        </s:else>
                        </s:else>
                        </s:else>

                     <s:if test="#stopEnd.departureTime==#itemStop.departureTime">
                    color : Highcharts.getOptions().colors[0],
                    fillColor : "#000000",
                    style : {// text style
                        color : '#000000'
                    },
                    y:-23,
                    shape : "url(/vnr/assets/img/imageChart/end.png)",
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=100}">
                         shape : "url(/vnr/assets/img/imageChart/end100.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=200}">
                         shape : "url(/vnr/assets/img/imageChart/end200.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=300}">
                         shape : "url(/vnr/assets/img/imageChart/end300.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=400}">
                        shape : "url(/vnr/assets/img/imageChart/end400.png)",
                        </s:if>
                        </s:else>
                        </s:else>
                        </s:else>
                    </s:if>
                </s:else>
                states : {
                    hover : {
                        fillColor : '#6badf6' // darker
                    }
                },

                width : 11,
                height:20
            },{
                type : 'flags',
                data : [{
                    x : <s:property value="#firstStop.arrivalTime" />,
                   /* title :'<s:property value="#itemStageTrainSchedule.train.trainReference.id" /> ' ,*/
                        title:' ',
                    text : 'Shape: "circlepin" '
                }],
                onSeries: <s:property value="#itemStageTrainSchedule.train.id" />,
                <s:if test="#itemStop.stationStage.distance > #firstStop.stationStage.distance">
                     y:-12,
                    shape : "url(/vnr/assets/img/imageChart/goUpLeft.png)",
                    <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=100}">
                    shape : "url(/vnr/assets/img/imageChart/goUpLeft100.png)",
                    </s:if><s:else>
                    <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=200}">
                    shape : "url(/vnr/assets/img/imageChart/goUpLeft200.png)",
                    </s:if><s:else>
                    <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=300}">
                    shape : "url(/vnr/assets/img/imageChart/goUpLeft300.png)",
                    </s:if><s:else>
                    <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=400}">
                    shape : "url(/vnr/assets/img/imageChart/goUpLeft400.png)",
                    </s:if>
                    </s:else>
                    </s:else>
                    </s:else>
                    <s:if test="#stopStart.arrivalTime==#firstStop.arrivalTime">
                        color : Highcharts.getOptions().colors[0],
                        fillColor : "#000000",
                        style : {// text style
                            color : '#000000'
                        },
                        shape : "url(/vnr/assets/img/imageChart/startDown.png)",
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=100}">
                        shape : "url(/vnr/assets/img/imageChart/startDown100.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=200}">
                        shape : "url(/vnr/assets/img/imageChart/startDown200.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=300}">
                        shape : "url(/vnr/assets/img/imageChart/startDown300.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=400}">
                        shape : "url(/vnr/assets/img/imageChart/startDown400.png)",
                        </s:if>
                        </s:else>
                        </s:else>
                        </s:else>
                    </s:if>
                </s:if><s:else>
                     y:5,

                     shape : "url(/vnr/assets/img/imageChart/goDownLeft.png)",
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=100}">
                        shape : "url(/vnr/assets/img/imageChart/goDownLeft100.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=200}">
                        shape : "url(/vnr/assets/img/imageChart/goDownLeft200.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=300}">
                        shape : "url(/vnr/assets/img/imageChart/goDownLeft300.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=400}">
                        shape : "url(/vnr/assets/img/imageChart/goDownLeft400.png)",
                        </s:if>
                        </s:else>
                        </s:else>
                        </s:else>
                    <s:if test="#stopStart.arrivalTime==#firstStop.arrivalTime">
                    color : Highcharts.getOptions().colors[0],
                    fillColor : "#000000",
                    style : {// text style
                        color : '#000000'
                    },
                    shape : "url(/vnr/assets/img/imageChart/startUp.png)",
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=100}">
                        shape : "url(/vnr/assets/img/imageChart/startUp100.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=200}">
                        shape : "url(/vnr/assets/img/imageChart/startUp200.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=300}">
                        shape : "url(/vnr/assets/img/imageChart/startUp300.png)",
                        </s:if><s:else>
                        <s:if test="%{#itemStageTrainSchedule.train.trainReference.priority<=400}">
                        shape : "url(/vnr/assets/img/imageChart/startUp400.png)",
                        </s:if>
                        </s:else>
                        </s:else>
                        </s:else>
                    </s:if>
                </s:else>


                states : {
                    hover : {
                        fillColor : '#6badf6' // darker
                    }
                },

                width : 27
            }
            <s:if test="!#statStageTrainSchedule.last">,
            </s:if>
            </s:iterator>
        ],
        navigator: { enabled: true, height: 18, margin: 20, series: { data: [] }, xAxis: { min: <s:property value="dateStart.time" />, max: <s:property value="dateEnd.time" /> } },
        scrollbar: { enabled: false },
        credits: { enabled: false }


    });

    charts.push(chart);
    </s:if>
    </s:iterator>

    $('#btnUnZoom').click(function () {
        fntUnZoom();
    });
/*

    $('#editTimeLock').click(function(){
        BootstrapDialog.show({ title: 'Khóa biểu kế hoạch',
            message:
                    '<form id="frmTimeLockUpdate" name="filter" action="/vnr/scheduling/daily.action"  enctype="multipart/form-data" class="form-inline" style="">' +
                    '  <div class="form-group">' +
                    '      <label class="control-label">Thời gian khóa(dd/MM/yyyy): </label>' +
                    '      <input id="frmTimeLock_timelock" type="text" name="timelock" value="<s:property value="timelock"/>" class="input-small input-sm input-s-sm form-control inline" placeholder="Ngày">' +

                    '      <input id="frmTimeLock_start" name="start" type="hidden" value="' + $("#filter_start").val() + '">' +
                    '      <input id="frmTimeLock_end" name="end" type="hidden" value="' + $("#filter_end").val() + '">' +
                    '      <input id="frmTimeLock_workbenchId" name="workbenchId" type="hidden" value="' + $("#filter_workbenchId").val() + '">' +
                    '  </div>' +
                    '</form>',
            buttons: [
                {
                    label: 'Close', cssClass: 'btn-sm',
                    action: function (dialogRef) {
                        dialogRef.close();
                    }
                },
                {
                    label: 'OK', cssClass: 'btn-primary btn-sm',
                    action: function (dialogRef) {
                        $("#frmTimeLockUpdate").submit();
                    }
                }
            ]
        });
    })
*/

});

function fntUnZoom() {
    $.each(charts, function (index, item) {
        item.xAxis[0].setExtremes(null, null);
    });
}


</script>


