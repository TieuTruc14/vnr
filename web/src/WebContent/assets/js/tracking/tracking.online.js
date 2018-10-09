/*
 * Tracking Online
 * 
 * @author:	cuonght at eposi dot vn
 * @version: 1.0.1	2013-07-02
 */

var TrackingOnline= {
	numRows : 0,
	companies : [],
	vehicleData :[],
	
	//Tam
	icons : {
		'iconOverspeed': {
			icon : L.AwesomeMarkers.icon({
				icon : 'spinner',
				color : 'orange',
				spin : true
			}),
			opacity: 1.0
			},
		'iconOverspeed100': {
			icon : L.AwesomeMarkers.icon({
				icon : 'spinner',
				color : 'red',
				spin : true
			}),
			opacity: 1.0
			},
		'iconRunning': {
			icon : L.AwesomeMarkers.icon({
				icon : 'spinner',
				color : 'green',
				spin : true
			}),
			opacity: 1.0
			},
		'iconStop': {
			icon : L.AwesomeMarkers.icon({
				icon : 'spinner',
				color : 'darkpurple',
				spin : true
			}),
			opacity: 1.0
			},
	},
	
	/*
	 * init function
	 */
	init: function(){
		TrackingOnline.triggerRegister();
		TrackingOnline.update();
	},
	
	/*
	 * 
	 */
	triggerRegister: function(){
		//On province selector change
		$("#inputProvince").on("change", function(e){
			$("#inputCompany").children().remove();
			$("#OnlineTracking-vehicleList").children().remove();
			
			var provinceId = $("#inputProvince").val();
			var searchContent = $("#findVehicleOnline").val();
			
			$("#inputCompany").append('<option value="-1">---[Chọn doanh nghiệp]---</option>');
			//Companies in filter
			for(var i=0; i<TrackingOnline.companies.length; i++){
				var company = TrackingOnline.companies[i];
				if(provinceId == company.provinceId || provinceId == -1){
					var content = "<option value='" + company.id + "' >" + company.name + "</option>";
					$("#inputCompany").append(content);
				}
			}
			
			//Vehicles in filter
			/*for(var i=0; i<TrackingOnline.vehicleData.length; i++){
				var vehicle = TrackingOnline.vehicleData[i];
				if( vehicle.vehicle.indexOf(searchContent) !== -1 && provinceId == vehicle.provinceId){
					var content = '<tr id="vehicle_' + vehicle.vehicle.toUpperCase() + '" index="' + i + '"> <td class="vehicle">' + vehicle.vehicle + '</td><td class="speed"><span class="label label-success">' + vehicle.speed + '</td><td class="address">' + vehicle.address + '</td></tr>';
					$("#OnlineTracking-vehicleList").append(content);
				}
			}*/
		});
		
		//On company selector change
		$("#inputCompany").on("change", function(e){
			$("#OnlineTracking-vehicleList").children().remove();

			var companyId = $("#inputCompany").val();
			var searchContent = $("#findVehicleOnline").val();
			
			if(companyId != -1)
				//Vehicles in filter
				for(var i=0; i<TrackingOnline.vehicleData.length; i++){
					var vehicle = TrackingOnline.vehicleData[i];
					if( vehicle.vehicle.indexOf(searchContent) !== -1 && companyId == vehicle.companyId){
						var content = '<tr id="vehicle_' + vehicle.vehicle.toUpperCase() + '" index="' + i + '"> <td class="vehicle">' + vehicle.vehicle + '</td><td class="speed"><span class="label label-success">' + vehicle.speed + '</td><td class="address">' + vehicle.address + '</td></tr>';
						$("#OnlineTracking-vehicleList").append(content);
					}
				}
			else{
				var provinceId = $("#inputProvince").val();
				
				//Vehicles in filter
				for(var i=0; i<TrackingOnline.vehicleData.length; i++){
					var vehicle = TrackingOnline.vehicleData[i];
					if( vehicle.vehicle.indexOf(searchContent) !== -1 && provinceId == vehicle.provinceId ){
						var content = '<tr id="vehicle_' + vehicle.vehicle.toUpperCase() + '" index="' + i + '"> <td class="vehicle">' + vehicle.vehicle + '</td><td class="speed"><span class="label label-success">' + vehicle.speed + '</td><td class="address">' + vehicle.address + '</td></tr>';
						$("#OnlineTracking-vehicleList").append(content);
					}
				}
			}
		});
		
		//findVehicleDisplayed
		$("#findVehicleDisplayed").on("click", function(e){
			$("#OnlineTracking-vehicleList").children().remove();
			
			var searchContent = $("#findVehicleOnline").val();
			var provinceId = $("#inputProvince").val();
			var companyId = $("#inputCompany").val();
			
			//Vehicles in filter
			for(var i=0; i<TrackingOnline.vehicleData.length; i++){
				var vehicle = TrackingOnline.vehicleData[i];
				if(vehicle.vehicle.indexOf(searchContent) !== -1 && provinceId == vehicle.provinceId && (companyId == vehicle.companyId | companyId == -1) ){
					var content = '<tr id="vehicle_' + vehicle.vehicle.toUpperCase() + '" index="' + i + '"> <td class="vehicle">' + vehicle.vehicle + '</td><td class="speed"><span class="label label-success">' + vehicle.speed + '</td><td class="address">' + vehicle.address + '</td></tr>';
					$("#OnlineTracking-vehicleList").append(content);
				}
			}
		});
		
		//Onclick into vehicle list
		$("#OnlineTracking-vehicleList").on("click", function(e){
			/*console.log(e);
			console.log(e.target);
			console.log(e.target.parent());*/
		});
		
		//
		$("#searchByCompany").on("click", function(evt){
			evt.preventDefault();
			
			$("#OnlineTracking-vehicleList").children().remove();
			
			var searchContent = $("#findVehicleOnline").val();
			var provinceId = $("#inputProvince").val();
			var companyId = $("#inputCompany").val();
			
			//Vehicles in filter
			for(var i=0; i<TrackingOnline.vehicleData.length; i++){
				var vehicle = TrackingOnline.vehicleData[i];
				if(vehicle.vehicle.indexOf(searchContent) !== -1 && provinceId == vehicle.provinceId && (companyId == vehicle.companyId | companyId == -1) ){
					var content = '<tr id="vehicle_' + vehicle.vehicle.toUpperCase() + '" index="' + i + '"> <td class="vehicle">' + vehicle.vehicle + '</td><td class="speed"><span class="label label-success">' + vehicle.speed + '</td><td class="address">' + vehicle.address + '</td></tr>';
					$("#OnlineTracking-vehicleList").append(content);
				}
			}
		});
	},
	
	/*
	 * Update vehicles status
	 */
	update: function() {
		var provinceId = $("#inputProvince").val();
		var companyId = $("#inputCompany").val();
		var searchContent = $("#findVehicleOnline").val();

		if(provinceId > 0 && companyId != -1 ){
			//Validate list vehicle need update
			var vehicleLits = [];
			var referenceObjectList = [];
			
			//Vehicles in filter
			for(var i=0; i<TrackingOnline.vehicleData.length; i++){
				var vehicle = TrackingOnline.vehicleData[i];
				if( vehicle.vehicle.indexOf(searchContent) !== -1 && provinceId == vehicle.provinceId && (companyId == vehicle.companyId | companyId == -1) ){
					vehicleLits += vehicle.vehicle + "_";
					var referenceObject = {
						index : i,
						vehicle : vehicle.vehicle,
					};
					referenceObjectList.push(referenceObject);
				}
			}
			
			TrackingOnline.requestData(vehicleLits, referenceObjectList);
		}
		setTimeout(TrackingOnline.update, 1000);
	},

	/*
	 * request real-time data for vehicle online
	 */
	requestData: function(vehicleLits, referenceObjectList){
		var urlRequest = "/ufms/tracking/online.json.action?lstUpdate=" + vehicleLits
		$.getJSON(urlRequest, function(data) {
			if(data){
				vehicleOverSpeed.clearLayers();
				vehicleOnline.clearLayers();
				$.each(data, function(key, val) {
					for(var i=0; i<referenceObjectList.length; i++){
						if(val[0].toUpperCase() == referenceObjectList[i].vehicle.toUpperCase()){
							
							//Update status for vehicle list
							TrackingOnline.vehicleData[referenceObjectList[i].index].x = val[2];
							TrackingOnline.vehicleData[referenceObjectList[i].index].y = val[3];
							TrackingOnline.vehicleData[referenceObjectList[i].index].time = val[1];
							TrackingOnline.vehicleData[referenceObjectList[i].index].speed = val[4];
							
							//Update new info for data row in vehicle list tab
							var speedDom = "#vehicle_" + val[0].toUpperCase() + " .speed>span";
							if(val[4] > 100){
								$(speedDom).text(val[4]);
								$(speedDom).removeClass("label-success");
								$(speedDom).addClass("label-important");
							}
							else if(val[4] > 80){
								$(speedDom).text(val[4]);
								$(speedDom).removeClass("label-success");
								$(speedDom).addClass("label-warning");
							}
							else{
								//Makeup data
								if(val[4] < 10)
									$(speedDom).text("0" + val[4]);
								else
									$(speedDom).text(val[4]);
								$(speedDom).removeClass("label-success");
								$(speedDom).removeClass("label-warning");
								$(speedDom).removeClass("label-important");
								$(speedDom).addClass("label-success");
							}
							
							var addressDom = "#vehicle_" + val[0].toUpperCase() + " .address";
							$(addressDom).text("{" + val[2] + "," + val[3] + "}");
							
							//apply into map
							var iconType = (val[4] >= 100) ? 'iconOverspeed100' : ( (val[4] >= 80) ? 'iconOverspeed' : "iconRunning");	//((val[4] == 0) ? 'iconStop' : 'iconRunning') );
							var point = {
									x: val[2],
									y: val[3],
							};
							var time = new Date(val[1]);
							description = "<span><b>Biển số xe</b>:   <span class=\"label label-info\">" + val[0] + "</span></span><br />" +
											"<span>Vận tốc hiện tại:   " + val[4] + " (km/h)" + "</span><br />" +
											"<span>Thời gian :   " + time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds() + "</span><br />"+
											"<span>Địa chỉ:   " + "{" + val[2] + ", " + val[3] + "}" + "</span>" ;
							
							var marker = Map.marker(point, iconType, description);
							marker["index"] = referenceObjectList[i].index;
							
							marker.on("click", function(e){
								map.setView( this._latlng, map._zoom);
								var popup =  L.popup({offset: new L.Point(0, -30)})
											    .setLatLng(this._latlng)
											    .setContent(this._popup._content)
											    .openOn(map);
							});
							
							//open popup on map when this row clicked
							var rowId = "#vehicle_" + val[0].toUpperCase();
							$(rowId).click(function() {
								marker.fire('click');
							});
							
							//Control
							TrackingOnline.vehicleData[referenceObjectList[i].index].layer = marker;
							
							if(val[4] > 80)
								vehicleOverSpeed.addLayer(marker);
							else
								vehicleOnline.addLayer(marker);
						}
					}
				});
			}
		});
	},
}