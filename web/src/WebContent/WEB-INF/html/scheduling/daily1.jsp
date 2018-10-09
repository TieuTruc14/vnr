<%@ page isELIgnored="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<section id="content">
    <ul class="breadcrumb m-b-none">
        <li><a href="#"><i class="fa fa-home"></i> Trang chủ</a></li>
        <li><a href="#"> Điều hành</a></li>
        <li><a href="#"> Lịch trình</a></li>
        <li><a href="#"> Kế hoạch ngày</a></li>
        <li class="active">khu đoạn HÀ NỘI - THANH HÓA</li>
    </ul>

    <section class="vbox">
        <section class="scrollable wrapper w-f bg-white-only">
            <%--<p class="h4">Kế hoạch ngày khu đoạn HÀ NỘI - THANH HÓA</p>--%>

            <section class="panel panel-default m-b-none bg-light lt">
                <div class="panel-body">
                    <s:form id="filter" label="Tìm kiếm " action="daily.action" method="get" theme="simple" enctype="multipart/form-data" cssClass="form-inline pull-left" cssStyle="" validate="false">
                        <div class="form-group">
                            <label class="control-label">Chọn thời gian </label>
                            <s:textfield key="start" placeholder="Ngày" theme="simple" cssClass="input-small input-sm input-s-sm form-control inline"/>
                            <span class="add-on">-</span>
                            <s:textfield key="end" placeholder="Ngày" theme="simple" cssClass="input-small input-sm input-s-sm form-control inline"/>
                        </div>
                        <button type="submit" class="btn btn-primary btn-sm">Tải lịch trình</button>
                    </s:form>
                    <div class="pull-right">
                        <a href="/vnr/scheduling/detect.conflicts.action" class="btn btn-success btn-sm">Tìm xung đột !</a>
                    </div>
                </div>
            </section>


            <div id="container" style="height: 95%; min-width: 310px; width: auto"></div>

            <%--<div id="dlgEditStop" title="Basic dialog">--%>
              <%--<p>Mác Tàu: <span id="dlgEditStop_TrainName"></span></p>--%>
              <%--<p>Điểm dừng: <span id="dlgEditStop_Stop"></span></p>--%>
              <%--<p>TG Đến: <span id="dlgEditStop_TimeIn"></span></p>--%>
              <%--<p>TG Rời: <span id="dlgEditStop_TimeOut"></span></p>--%>
            <%--</div>--%>

            <%--<div id="dlgEditStop" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">--%>
                <%--<div class="modal-dialog">--%>
                    <%--<div class="modal-content">--%>
                        <%--<div class="modal-header">--%>
                            <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                            <%--<h4 id="myModalLabel" class="modal-title">Modal title</h4>--%>
                        <%--</div>--%>
                        <%--<div class="modal-body">--%>
                            <%--...--%>
                        <%--</div>--%>
                        <%--<div class="modal-footer">--%>
                            <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                            <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
			
        </section>

    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>

<%--<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.2/themes/smoothness/jquery-ui.css">--%>
<link rel="stylesheet" type="text/css" href="/vnr/assets/js/bootstrap3-dialog/bootstrap-dialog.min.css" />
<script type="text/javascript" src="/vnr/assets/js/bootstrap3-dialog/bootstrap-dialog.min.js"></script>
<%--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>--%>
<script type="text/javascript" src="/vnr/assets/js/highstock/js/highstock.js"></script>
<script type="text/javascript" src="/vnr/assets/js/moment.min.js"></script>
<script type="text/javascript">
var stations = [
    <s:iterator var="item" value="stations" status="stat">
    { id: <s:property value="#item.id" />, name: "<s:property value="#item.name" escapeHtml="false" />", feature: <s:property value="#item.feature" />, distance: <s:property value="#item.distance" /> }<s:if test="!#stat.last">,</s:if>
    </s:iterator>
];

$( document ).ready(function() {
    Highcharts.setOptions({ global: { useUTC: false } });
	
	// Create the chart
	var chart = new Highcharts.StockChart({
		chart: { renderTo: 'container', type: 'line' , zoomType: "xy" },
		rangeSelector: {enabled:false}, exporting: { enabled: false },
		plotOptions: {
			series: {
				cursor: 'pointer',
				marker : { enabled : true, radius : 4 }, 
				shadow : false, /* allowPointSelect: true,*/ animation: false
			}
		},
		tooltip:{ crosshairs:true, shared:false, snap:1 },    
		yAxis: {
            lineWidth: 4, offset: 30, opposite: false, /* minTickInterval: 1, tickInterval: 1, min: -1000, max: 5000, */
			reversed: true, showFirstLabel: true, showLastLabel: true,
			/* gridLineColor: '#e0e0e0', alternateGridColor: '#F7F7F7', gridZIndex: 1, */
			tickPositioner: function(min, max) {
				var i, tickPositions = [];
				tickPositions.push(min - max/stations.length/8);

				for (i = 0; i < stations.length; i++) {
                    if ((stations[i].distance >= min) && (stations[i].distance <= max)) {
                        tickPositions.push(stations[i].distance);
                    }
            }

                tickPositions.push(max + max/stations.length/8);

				return tickPositions;
			},
			labels: {
				align: 'right', y: 3,
				formatter: function(){
					var value = this.value;
                    var foundTick = false;
					$.each(stations, function(index, item) {
						if (item.distance == value) {
                            foundTick = true;
							value = item.name + ' [' + item.distance/1000 + ']';
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
		series : [
            <s:iterator var="item" value="trains" status="stat">
            <s:set var="lineColor" value="'red'" />
            <s:set var="lineWidth" value="1" />
            <s:if test="%{#item.classification==1}">
                <s:set var="lineColor" value="'blue'" />
            </s:if>
            <s:if test="%{#item.name.startsWith('SE')}">
                <s:set var="lineWidth" value="2" />
            </s:if>

            { id: <s:property value="#item.id" />, name: "<s:property value="#item.name" />", marker: { symbol: 'circle', radius: 2 }, dashStyle: 'Solid', color: '<s:property value="#lineColor" />', lineWidth: <s:property value="#lineWidth" />, departureTime: <s:property value="#item.departureTime" />,
                data: [
                    <s:iterator var="itemStop" value="#item.stops" status="statStop">
                    { x: <s:property value="#itemStop.arrivalTime" />, y: <s:property value="#itemStop.station.distance" />  },
                    { x: <s:property value="#itemStop.departureTime" />, y: <s:property value="#itemStop.station.distance" />  }<s:if test="!#statStop.last">,</s:if>
                    </s:iterator>
                ],
                events: {
                    click: function (e) {
//                      console.log("x: " + e.point.x + ", y: " + e.point.y);
//                      console.log("e.point.series.name: " + e.point.series.name);
//                        $("#dlgEditStop").modal('show');

                        var stopInfo = {
                            trainId: e.point.series.options.id,
                            trainName: e.point.series.name
                        };

                        $.each(stations, function(index, item) {
                            if (item.distance == e.point.y) {
                                stopInfo.station = item;
                                return false;
                            }
                        });

//                        console.log(e);
//                        console.log(e.point);
//                        console.log(e.point.series.data);

                        $.each(e.point.series.data, function(index, item) {
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
                            message: '<form id="frmStopUpdate" name="filter" action="/vnr/scheduling/daily.stop.update.action" method="get" enctype="multipart/form-data" class="form-inline" style="">' +
                                     '  <div class="form-group">' +
                                     '      <label class="control-label">Thời gian ( Tới - Rời ) Ga </label>' +
                                     '      <input id="frmStopUpdate_arrivalTime" type="text" name="arrivalTime" value="' + moment(stopInfo.arrivalTime).format("YYYY/MM/DD-HH:mm:ss") + '" class="input-small input-sm input-s-sm form-control inline" placeholder="Ngày">' +
                                     '      <span class="add-on">-</span>' +
                                     '      <input id="frmStopUpdate_departureTime" type="text" name="departureTime" value="' + moment(stopInfo.departureTime).format("YYYY/MM/DD-HH:mm:ss") + '" class="input-small input-sm input-s-sm form-control inline" placeholder="Ngày">' +
                                     '      <input id="frmStopUpdate_trainId" name="trainId" type="hidden" value="' + stopInfo.trainId + '">' +
                                     '      <input id="frmStopUpdate_stationId" name="stationId" type="hidden" value="' + stopInfo.station.id + '">' +
                                     '      <input id="frmStopUpdate_start" name="start" type="hidden" value="' + $("#filter_start").val() + '">' +
                                     '      <input id="frmStopUpdate_end" name="end" type="hidden" value="' + $("#filter_end").val() + '">' +
                                     '  </div>' +
                                     '</form>',
                            buttons: [{
                                    label: 'Close', cssClass: 'btn-sm',
                                    action: function(dialogRef){
                                        dialogRef.close();
                                    }
                                }, {
                                    label: 'OK', cssClass: 'btn-primary btn-sm',
                                    action: function(dialogRef){
                                        $( "#frmStopUpdate" ).submit();
                                    }
                                }]
                        });
                    }
                }
            }<s:if test="!#stat.last">,</s:if>
            </s:iterator>
		],
		navigator: { enabled : true, height: 18, margin: 20, series : { data: [] }, xAxis: { min: <s:property value="dateStart.time" />, max: <s:property value="dateEnd.time" /> } },
		scrollbar : { enabled : false },
		credits: { enabled: false }
	});
});
</script>