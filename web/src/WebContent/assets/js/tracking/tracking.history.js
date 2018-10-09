var TrackingHistory = {
	companies : [],
	vehicleData :[],
	
	/**
	 * This function init variable, value and events for tracking history.
	 * Updated by TuanVA
	 */
	init: function() {
		//Register triggers
		TrackingHistory.triggerRegister();
	},
	
	/*
	 * 
	 */
	triggerRegister: function(){
		//On province selector change
		$("#inputProvince").on("change", function(e){
			$("#inputCompany").children().remove();
			$("#inputVehicle").children().remove();
			
			var provinceId = $("#inputProvince").val();

			$("#inputCompany").append('<option value="-1">---[Chọn doanh nghiệp]---</option>');
			$("#inputVehicle").append('<option value="-1">---[Chọn xe]---</option>');
			
			//Companies in filter
			for(var i=0; i<TrackingHistory.companies.length; i++){
				var company = TrackingHistory.companies[i];
				if(provinceId == company.provinceId || provinceId == -1){
					var content = "<option value='" + company.id + "' >" + company.name + "</option>";
					$("#inputCompany").append(content);
				}
			}
			
			//Vehicles in filter
			for(var i=0; i<TrackingHistory.vehicleData.length; i++){
				var vehicle = TrackingHistory.vehicleData[i];
				if( provinceId == vehicle.provinceId){
					var content = "<option value='" + vehicle.vehicle + "' >" + vehicle.vehicle + "</option>";
					$("#inputVehicle").append(content);
				}
			}
		});
		
		//On company selector change
		$("#inputCompany").on("change", function(e){
			$("#inputVehicle").children().remove();

			$("#inputVehicle").append('<option value="-1">---[Chọn xe]---</option>');

			var companyId = $("#inputCompany").val();
			var searchContent = $("#findVehicleOnline").val();
			
			if(companyId != -1)
				//Vehicles in filter
				for(var i=0; i<TrackingHistory.vehicleData.length; i++){
					var vehicle = TrackingHistory.vehicleData[i];
					if( companyId == vehicle.companyId){
						var content = "<option value='" + vehicle.vehicle + "' >" + vehicle.vehicle + "</option>";
						$("#inputVehicle").append(content);
					}
				}
			else{
				var provinceId = $("#inputProvince").val();
				
				//Vehicles in filter
				for(var i=0; i<TrackingHistory.vehicleData.length; i++){
					var vehicle = TrackingHistory.vehicleData[i];
					if( provinceId == vehicle.provinceId ){
						var content = "<option value='" + vehicle.vehicle + "' >" + vehicle.vehicle + "</option>";
						$("#inputVehicle").append(content);
					}
				}
			}
		});
		
		//Listen events with "load data" button
	    $("#showTrackingHistory").click(function (evt) {
	    	//get value
	    	var fromTime = new Date($('#showHistoryFrom').val());
	    	var toTime = new Date($('#showHistoryTo').val());
	    	var vehicle = $("#inputVehicle").val();
	    	
	    	//Validate
	    	var STARTDATE_LESS_ENDATE = "Thời gian kết thúc phải lớn hơn thời gian bắt đầu";
	    	if(fromTime.getTime() >= toTime.getTime()) {
	    		alert(STARTDATE_LESS_ENDATE);
	    		return false;
	    	}
	    	if(vehicle < 1){
	    		alert("Vui lòng chọn xe cần xem hành trình");
	    		return false;
	    	}
	    	
	    	//Clear map layers
	    	Map.clearMapLayers();
	    	
	    	//Draw history Tracks
	    	TrackingHistory.historyDraw(vehicle, $('#showHistoryFrom').val(), $('#showHistoryTo').val());
	    	evt.preventDefault();
	    });
	},
	/**
	 * This function is get url parameter
	 * 
	 * @author TuanVA
	 *
	 */
	GetUrlValue: function(VarSearch){
	    var getURL = window.location.search.substring(1);
	    var VariableArray = getURL.split('&');
	    for(var i = 0; i < VariableArray.length; i++){
	        var KeyValuePair = VariableArray[i].split('=');
	        if(KeyValuePair[0] == VarSearch){
	            return KeyValuePair[1];
	        }
	    }
	},
	tripHistoryDraw: function(){
		$("#inputVehicle").children().remove();
		var vehicleid = TrackingHistory.GetUrlValue('vehicleid');
		var f = new Date(TrackingHistory.GetUrlValue('strFrom')*1);
		var t = new Date(TrackingHistory.GetUrlValue('strTo')*1);
		//Clear map layers
    	Map.clearMapLayers();
    	
    	var fYear = f.getFullYear();
    	var tYear = t.getFullYear();
    	
    	var fDD = f.getDate();
    	var tDD = t.getDate();
    	
    	var fMM = (f.getMonth()+1);
    	var tMM = (t.getMonth()+1);
    	
    	var fHH = f.getHours();
    	var tHH = t.getHours();
    	
    	var fMN = f.getMinutes();
    	var tMN = t.getMinutes();
    	
    	var fSS = f.getSeconds();
    	var tSS = t.getSeconds();
    	
    	if(fDD<10){fDD='0'+fDD} if(tDD<10){tDD='0'+tDD}
    	
    	if(fMM<10){fMM='0'+fMM} if(tMM<10){tMM='0'+tMM}
    	
    	if(fHH<10){fHH='0'+fHH} if(tHH<10){tHH='0'+tHH}
    	
    	if(fMN<10){fMN='0'+fMN} if(tMN<10){tMN='0'+tMN}
    	
    	if(fSS<10){fSS='0'+fSS} if(tSS<10){tSS='0'+tSS}
    	
    	var strFrom = fYear +'/'+ fMM +'/'+  fDD +" "+ fHH+':'+fMN +':'+fSS;
    	var strTo = tYear+'/'+ tMM+'/'+ fDD +" "+ tHH+':'+tMN+':'+tSS;
    	var content = "<option value='" + vehicleid + "' >" + vehicleid + "</option>";
		if(vehicleid!=undefined || f !=null){
	    	$("#inputVehicle").append(content);
	    	$("#showHistoryFrom").val(strFrom);
	    	$("#showHistoryTo").val(strTo);
	    	//Draw history Tracks
	    	TrackingHistory.historyDraw(vehicleid, strFrom, strTo);
		}
	},
	stopHistoryDraw: function(){
		Map.clearMapLayers();
		var vehicleid = TrackingHistory.GetUrlValue('vehicleid');
		var x = TrackingHistory.GetUrlValue('x');
		var y = TrackingHistory.GetUrlValue('y');
		var onStopTime = TrackingHistory.GetUrlValue('onStopTime');
		var address = TrackingHistory.GetUrlValue('address');
		var stopIcon = L.icon({
			iconUrl: '/ufms/assets/images/icons/tracking-history/icon_stop.png',
		    iconSize: [16, 16],
		    iconAnchor: [8, 8],
		    popupAnchor: [0, -16],
		});
		var stopContent = "<span><h2>Điểm dừng/đỗ</h2></span><br /><span>Biển số xe:  <span class='label label-info'>" + vehicleid + "</span></span><br /><span>Thời gian dừng/đỗ: <span class='label label-warning'>" + onStopTime + "</span></span><br /><span>Tọa độ: {" + x + "," + y +"}</span><br /><span>Địa chỉ: "+address+"</span>";
		marker = L.marker([y, x], {icon: stopIcon}).addTo(map)
	    	.bindPopup(stopContent)
	    	.openPopup();
	},
	doorHistoryDraw: function(){
		Map.clearMapLayers();
		var vehicleid = TrackingHistory.GetUrlValue('vehicleid');
		var x = TrackingHistory.GetUrlValue('x');
		var y = TrackingHistory.GetUrlValue('y');
		var status = TrackingHistory.GetUrlValue('status');
		var address = TrackingHistory.GetUrlValue('address');
		var doorContent = "<span><h2>Điểm đóng/mở cửa</h2></span><br /><span>Biển số xe:  <span class='label label-info'>" + vehicleid + "</span></span><br /><span>Tọa độ: {" + x + "," + y +"}</span><br /><span>Địa chỉ: "+address+"</span><br /><span>Trạng thái: "+status+"</span>";
		marker = L.marker([y, x]).addTo(map)
    	.bindPopup(doorContent)
    	.openPopup();
	},
	/**
	 * This function request geoJSON data, parse and add them to map
	 * 
	 * @author cuonght at eposi dot vn
	 * Edited by TuanVA
	 */
	historyDraw: function(vehicleId, fromDateTime, toDateTime) {
		//Khai báo url request geoJSON
		var urlRequest = "/ufms/tracking/trackingReportMap.action";
		urlRequest += "?vehicleId=" + vehicleId;
		urlRequest += "&strFrom=" + fromDateTime;
		urlRequest += "&strTo=" + toDateTime;
		//console.log(urlRequest);
		//Request hành trình
		$.getJSON(urlRequest, function(data){
			if(typeof data != "undefined"){
				//Hoàn thiện các tuyến đường và thêm vào map
				
				L.geoJson(data, {
					onEachFeature: TrackingHistory.onEachFeature,
					style: function (feature) { },
				}).addTo(map);
				
				Map.showHistory = true;
				Map.showArrow();
				
				TrackingHistory.showListTrackPoint(vehicleId, data);
			}else{
				alert("Không có dữ liệu");
			}
		});
	},
	
	/**
	 * This function for load list of track point
	 * @param feature
	 * @return none
	 * @author tuanva
	 */
	showListTrackPoint: function(vehicleId, data){
		$("#lstTrackPoint").children().remove();
		var trackList = data.features[0].geometry.coordinates;
		var lasttime = 0;
		var x, y, s, t;
		if(typeof trackList != "undefined"){
			//Add begin point and finish point
			
			var beginIcon = L.icon({
				iconUrl: '/ufms/assets/images/icons/tracking-history/begin.png',
			    iconSize: [24, 37],
			    iconAnchor: [12,37],
			    popupAnchor: [0, -37],
			});
			
			var finishRouteIcon = L.icon({
				iconUrl: '/ufms/assets/images/icons/tracking-history/flag.png',
			    iconSize: [24, 31],
			    iconAnchor: [12, 31],
			    popupAnchor: [0, -31],
			});
			
			var stopIcon = L.icon({
				iconUrl: '/ufms/assets/images/icons/tracking-history/icon_stop.png',
			    iconSize: [16, 16],
			    iconAnchor: [8, 8],
			    popupAnchor: [0, -16],
			});
			
			//begin point
			var begin = trackList[0];
			var latlng = new L.LatLng(begin[1], begin[0]);
			currentTime = begin[3];
			currentTime = currentTime.substr(0,2) + ":" + currentTime.substr(2,2) + ":" + currentTime.substr(4,2);
			var popupContent = "<span><h3>Điểm bắt đầu hành trình</h3></span><br /><span>Biển số xe: <span class='label label-info'>" + vehicleId + "</span></span><br /><span>Thời gian bắt đầu: " + currentTime + "</span><br /><span>Tọa độ: {" + begin[0] + "," + begin[1] +"}</span>";
			var beginRoute = L.marker(latlng, {icon: beginIcon}) .bindPopup(popupContent).addTo(map);
			
			//end point
			var end = trackList[trackList.length - 1];
			latlng = new L.LatLng(end[1], end[0]);
			currentTime = end[3];
			currentTime = currentTime.substr(0,2) + ":" + currentTime.substr(2,2) + ":" + currentTime.substr(4,2);
			popupContent = "<span><h3>Điểm kết thúc hành trình</h3></span><br /><span>Biển số xe:  <span class='label label-info'>" + vehicleId + "</span></span><br /><span>Thời gian kết thúc: " + currentTime + "</span><br /><span>Tọa độ: {" + begin[0] + "," + begin[1] +"}</span>";
			var endRoute = L.marker(latlng, {icon: finishRouteIcon}) .bindPopup(popupContent).addTo(map);
			
			//
			for(var i=0; i<trackList.length; i++){
				var track = trackList[i];
				x = track[0];
				y = track[1];
				s = track[2];
				t = track[3];
				if(typeof track[4] != "undefined" && track[4] != "" && track[4] > 5 ){
					var stopedTime = track[4];
					var hours = (stopedTime - stopedTime%3600)/3600;
					stopedTime = stopedTime%3600;
					var minute = (stopedTime - stopedTime%60)/60;
					var second = stopedTime%60;
					if(hours>0)
						hours = (hours<10) ? ("0" + hours) : hours;
					if(minute > 0)
						minute = (minute<10) ? ("0" + minute) : minute;
					if(second > 0)
						second = (second<10) ? ("0" + second) : second;
					stopedTime = "";
					if(hours >0 )
						stopedTime += hours + "giờ ";
					if(minute >0 )
						stopedTime += minute + "phút ";
					if(second >0 )
						stopedTime += second + "giây ";
					var stopContent = "<span><h3>Điểm dừng/đỗ</h3></span><br /><span>Biển số xe:  <span class='label label-info'>" + vehicleId + "</span></span><br /><span>Thời gian dừng/đỗ: " + stopedTime + "</span><br /><span>Tọa độ: {" + x + "," + y +"}</span>";
					marker = L.marker([y, x], {icon: stopIcon}).addTo(map)
				    	.bindPopup(stopContent)
				    	.openPopup();
				}
				if((t - lasttime) >= 15 ){
					lasttime = t;
					s = (s<10) ? ("0" + s) : s ;
					var label;
					if(s>100)
						label = "important";
					else if(s>80)
						label = "warning";
					else
						label = "success";
					t = t.substr(0,2) + ":" + t.substr(2,2) + ":" + t.substr(4,2);
					var content = '<tr class="trackPoint"> <td class="time" x=' + x + ' y=' + y + ' s=' + s + ' t=' + t + '>' + t + '</td><td class="speed"><span class="label label-' + label + '">' + s + '</td><td class="address"> {' + x + ", " + y + '} </td></tr>';
					$("#lstTrackPoint").append(content);
				}
			}
		}
		
		//
		var marker = L.marker();
		$(".trackPoint").on("click", function(e){
			if(marker!=null){
				map.removeLayer(marker);
			}
			var x = $(this).find('.time').attr('x');
			var y = $(this).find('.time').attr('y');
			var s = $(this).find('.time').attr('s');
			var t = $(this).find('.time').attr('t');
			var content = "<span><h3>Thông tin vị trí</h3></span><br /><span>Biển số xe:  <span class='label label-info'>" + vehicleId + "</span></span><br /><span>Thời gian: " + t + "</span><br /><span>Vận tốc hiện tại: " + s + " (km/h)</span><br /><span>Tọa độ: {" + x + "," + y +"}</span>";
			marker = L.marker([y, x]).addTo(map)
		    	.bindPopup(content)
		    	.openPopup();
		});
	},
	
};