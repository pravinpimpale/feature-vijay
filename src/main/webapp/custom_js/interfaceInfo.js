window.onload = function() {

	
var deviceIPP = document.getElementById("deviceIP");
var intNamee=document.getElementById("intName");
	
var deviceIP = deviceIPP.value;
var intName=intNamee.value;
	
	// Interface Info

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/interfaceInfo";
	$
			.ajax({
				type : 'POST',
				url : serviceUrl,
				data : {
					deviceIP : deviceIP,
					intName : intName
				},
				dataType : 'json',
				success : function(data) {

					for (var i = 0; i < data.length; i++) {
						document.getElementById("nodeIPID").textContent = data[i].node_ip;
						document.getElementById("intNameID").textContent = data[i].int_name;
						document.getElementById("aliasID").textContent = data[i].alias_name;
						document.getElementById("intTypeID").textContent = data[i].int_type;
						document.getElementById("intSpcID").textContent = data[i].int_id;
						document.getElementById("adminStatusID").textContent = data[i].admin_status;
						document.getElementById("operStatusID").textContent = data[i].oper_status;
						document.getElementById("intIPID").textContent = data[i].int_ip;
						document.getElementById("intMACID").textContent = data[i].int_mac;
						document.getElementById("mtuID").textContent = data[i].mtu;
						if(data[i].crc=="noSuchObject"){
							document.getElementById("crcID").textContent = "NA";
						}else{
							document.getElementById("crcID").textContent = data[i].crc;
						}
						
						document.getElementById("procBwID").textContent = Math.round(data[i].procure_bw/1024 * 100)/100
								+ " Kbps";
						document.getElementById("throughputID").textContent = Math.round(data[i].out_bw/1024 * 100)/100
								+ " Kbps(Transmit) "
								+ Math.round(data[i].in_bw/1024 * 100)/100
								+ " Kbps(Receive)";

					}

				}
			});

	// Interface Status History

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/interfaceStatusHistory";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : {
			deviceIP : deviceIP,
			intName : intName
		},
		dataType : 'json',
		success : function(data) {
			// alert(data);

			$.each(data, function(i, item) {
				$('<tr>').html(
						"<td>" + data[i].event_timestamp + "</td><td>"
								+ data[i].int_name + "</td><td>"
								+ data[i].int_status + "</td><td>"
								+ data[i].int_status_type + "</td>").appendTo(
						'#interfaceSummary');
			});
		}
	});

	// Interface CRC Log

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/interfaceCRCLog";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : {
			deviceIP : deviceIP,
			intName : intName
		},
		dataType : 'json',
		success : function(data) {
			// alert(data);

			$.each(data, function(i, item) {
				$('<tr>').html(
						"<td>" + data[i].event_timestamp + "</td><td>"
								+ data[i].int_name + "</td><td>"
								+ data[i].old_crc + "</td><td>"
								+ data[i].new_crc + "</td>").appendTo(
						'#crcLogID');
			});
		}
	});

	// -------------
	// - UPTIME BAR CHART -
	// -------------

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/interfaceUptimeLog";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : {
			deviceIP : deviceIP,
			intName : intName
		},
		dataType : 'json',
		success : function(data) {

		
				var areaChartData = {
					labels : data[0].datetime,
					datasets : [ {
						label : 'Downtime',
						backgroundColor : 'rgba(60,141,188,0.9)',
						borderColor : 'rgba(60,141,188,0.8)',
						pointRadius : false,
						pointColor : '#3b8bba',
						pointStrokeColor : 'rgba(60,141,188,1)',
						pointHighlightFill : '#fff',
						pointHighlightStroke : 'rgba(60,141,188,1)',
						data : data[0].downtime,
					}, {
						label : 'Uptime',
						backgroundColor : 'rgba(210, 214, 222, 1)',
						borderColor : 'rgba(210, 214, 222, 1)',
						pointRadius : false,
						pointColor : 'rgba(210, 214, 222, 1)',
						pointStrokeColor : '#c1c7d1',
						pointHighlightFill : '#fff',
						pointHighlightStroke : 'rgba(220,220,220,1)',
						data : data[0].uptime,
					}, ]
				}

				var barChartCanvas = $('#sales-chart').get(0).getContext('2d')
				var barChartData = $.extend(true, {}, areaChartData)
//				var temp0 = areaChartData.datasets[0]
//				var temp1 = areaChartData.datasets[1]

//				barChartData.datasets[0] = temp1
//				barChartData.datasets[1] = temp0

				var barChartOptions = {
					responsive : true,
					maintainAspectRatio : false,
					datasetFill : false
				}

				new Chart(barChartCanvas, {
					type : 'bar',
					data : areaChartData,
					options : barChartOptions
				})

			

		}

	});

	/* Real time Bandwidth Utilization */

	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/dashboard/interfaceBandwidthUtil";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : {
			deviceIP : deviceIP,
			intName : intName
		},
		dataType : 'json',
		success : function(jsondata) {
			// alert(jsondata);

			
			Highcharts.stockChart('container_bandwidth', {
			    chart: {
			        events: {
			            load: function () {
			            	
			                // set up the updating of the chart each second
			                var series = this.series[0];
			                var series2 = this.series[1];
			                
			                
			                setInterval(function () {
			          
			                	var l = window.location;
			                	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
			                	var serviceUrl = base_url + "/dashboard/interfaceBandwidthUtil";
			                	$.ajax({
			                		type : 'POST',
			                		url : serviceUrl,
			                		data : {
			                			deviceIP : deviceIP,
			                			intName : intName
			                		},
			                		dataType : 'json',
			                		success : function(jsondata1) {
			                	
			                			var x = (new Date()).getTime(), // current
																		// time
				                    		y=Math.round(jsondata1[0].in_traffic/1024),
				                    		z=Math.round(jsondata1[0].out_traffic/1024);
				                    
				                    series.addPoint([x, y], true, true);
				                    series2.addPoint([x, z], true, true);
			                			
			                		}
			                		
			                	});
			              
			                }, 2000);
			               
			            }
			        }
			    },

			    accessibility: {
			        enabled: false
			    },

			    time: {
			        useUTC: false
			    },

			    rangeSelector: {
			        buttons: [{
			            count: 1,
			            type: 'minute',
			            text: '1M'
			        }, {
			            count: 5,
			            type: 'minute',
			            text: '5M'
			        }, {
			            type: 'all',
			            text: 'All'
			        }],
			        inputEnabled: false,
			        selected: 0
			    },

			    
			    exporting: {
			        enabled: false
			    },

			    
			    series: [{
			        name: 'In Traffic',
			        data: (function () {
			            // generate an array of random data
			            var data = [],
			                time = (new Date()).getTime(),
			                i;

			            for (i = -999; i <= 0; i += 1) {
			                data.push([
			                    time + i * 1000,
			                    Math.round(jsondata[0].in_traffic/1024)
			                ]);
			            }
			            return data;
			        }())
			    },
			    {
			        name: 'Out Traffic',
			        data: (function () {
			            // generate an array of random data
			            var data = [],
			                time = (new Date()).getTime(),
			                i;

			            for (i = -999; i <= 0; i += 1) {
			                data.push([
			                    time + i * 1000,
			                    Math.round(jsondata[0].out_traffic/1024)
			                ]);
			            }
			            return data;
			        }())
			    }]
			});
			

		}
	});


}
