window.onload = function() {

	// Device Up Down Count

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/deviceSummaryCount";
	$
			.ajax({
				type : 'GET',
				url : serviceUrl,
				dataType : 'json',
				success : function(data) {
					
					var upcount=0;
					var downcount=0;
					var warningcount=0;

					for (var i = 0; i < data.length; i++) {

						if (data[i].status == "Up") {
							document.getElementById("upspanid").textContent = data[i].count;
							upcount = data[i].count;
						}
						if (data[i].status == "Down") {
							document.getElementById("downspanid").textContent = data[i].count;
							downcount = data[i].count;
						}
						if (data[i].status == "Warning") {
							document.getElementById("warningspanid").textContent = data[i].count;
							warningcount = data[i].count;
						}
					}

					var result = upcount + downcount + warningcount;
					document.getElementById("totalspanid").textContent = result;

					localStorage.setItem("upCountVar", upcount);
					localStorage.setItem("downCountVar", downcount);
					localStorage.setItem("warningCountVar", warningcount);
				}
			});

	// Device Down Summary Listing

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/deviceSummaryList";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			// alert(data);

			var table = $('#deviceSummary').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true
			});

		}
	});

	// Link Up Down Count

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/linkSummaryCount";
	$
			.ajax({
				type : 'GET',
				url : serviceUrl,
				dataType : 'json',
				success : function(data) {

					var linkupcount = 0;
					var linkdowncount = 0;

					for (var i = 0; i < data.length; i++) {

						if (data[i].status == "up") {
							document.getElementById("uplinkspanid").textContent = data[i].count;
							linkupcount = data[i].count;
						}
						if (data[i].status == "down") {
							document.getElementById("downlinkspanid").textContent = data[i].count;
							linkdowncount = data[i].count;
						}

					}

					var result = linkupcount + linkdowncount;
					document.getElementById("totallinkspanid").textContent = result;

					localStorage.setItem("upLinkCountVar", linkupcount);
					localStorage.setItem("downLinkCountVar", linkdowncount);
				}
			});

	// Link Down Summary Listing

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/linkSummaryList";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		dataType : 'json',
		success : function(data) {
			// alert(data);

			var table = $('#linkSummary').DataTable({
				lengthChange : false,
				autoWidth : false,
				data : data,
				"pageLength" : 5,
				scrollX : true,
				scrollY : true
			});

		}
	});

}

// Get Node Details on Model
function getNodeInfo(NodeStatus) {
	$('#deviceInfoTable td').remove();
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getDeviceInfo";
	var nodeDetailUrl = base_url + "/dashboard/nodeDetailsPage?nodeIP=";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : {
			deviceInfo : NodeStatus
		},
		dataType : 'json',
		success : function(data) {
		
			var infoModal = $('#device-info-modal');
			infoModal.modal('show');
			var htmldata;
			$.each(data, function(i, item) {
				
				htmldata=	$('<tr>').html(
						"<td><a href="+nodeDetailUrl+data[i].deviceip+">"+data[i].deviceip+"</a></td><td>"
								+ data[i].devicename + "</td><td>"
								+ data[i].location + "</td><td>"
								+ data[i].company + "</td>")
								
				infoModal.find($('#deviceInfoTable')).append(htmldata); 
				
				 
			});
			
		}
		
	});
	
}

//Get Link Details on Model
function getLinkInfo(LinkStatus) {

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/getLinkInfo";
	var linkDetailUrl = base_url + "/dashboard/interfaceInfoPage?nodeIP=";
	$.ajax({
		type : 'GET',
		url : serviceUrl,
		data : {
			linkInfo : LinkStatus
		},
		dataType : 'json',
		success : function(data) {
			var infoModal = $('#link-info-modal');
			infoModal.modal('show');
			var htmldata;
			$.each(data, function(i, item) {
				htmldata=	$('<tr>').html(
						"<td>" + data[i].nodeip + "</td>"+
						"<td><a href="+linkDetailUrl+data[i].nodeip+"&intName="+encodeURIComponent(data[i].interfacename)+">"+ data[i].interfacename + "</a></td>"+
						"<td>"	+ data[i].location + "</td>"+
							"<td>"	+ data[i].company + "</td>")
				infoModal.find($('#linkInfoTable')).append(htmldata);
				
				
			});
			
			
		}
	});
	
}

