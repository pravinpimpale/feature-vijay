function getInterface(ip) {
	
	
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/interfaceReport/getInterfaceNameByIP";
	
	$
	.ajax({
		url : serviceUrl,
		type : 'GET',
		data : 'ip_address=' +ip

	})
	.done(
			function(data) {
	//			alert(data)
				var pollingHost = data;
				var d = pollingHost.split("~");

				var length = d.length;
				// alert(length)

				var html;
				// html += '<option value="">---Please
				// Select---</option>';
				for (var i = 0; i < length; i++) {

					// alert(d1);
					if(d[i]=='NA'){
						
					}else{
					html += '<option value="&apos;' + d[i] + '&apos;">'
							+ d[i] + '</option>';
					}
				}

				html += "</select>";
//alert(html)
				document.getElementById("interface_name").innerHTML = html;

			});
			
	
}


$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
   // alert( "Form successful submitted!" +$('#ip_address').val());
    	$("#graphFormDiv").hide();
    	$("#graphView").show();
    	 document.getElementById("graph_ip").innerHTML = $('#ip_address').val();
    	generateGraph();
      
    }
  });
  $('#reportForm').validate({
    rules: {
    	
    	ip_address:{
    		
    		required: true
    		
    	},
    	from_date:{
    		required: true
    	},
    	
    	to_date:{
    		required: true
    		
    	},
    
      

    },
    messages: {
    	ip_address: {
        required: "Please enter a IP address",
       
      },
      from_date: {
        required: "Please select ",
     
      },
      to_date:{
    	  required: "date range picker",
      },
    
      
      terms: "Please accept our terms"
    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
      error.addClass('invalid-feedback');
      element.closest('.form-group').append(error);
    },
    highlight: function (element, errorClass, validClass) {
      $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
      $(element).removeClass('is-invalid');
    }
  });
});


function printReport() {
	alert("Print");
    $("#print").hide()
    window.print();
 //   window.location.reload();
    $("#print").show()
}



$(function () {
	  $("#backForm").click(function () {
		  $("#graphFormDiv").show();
	    	$("#graphView").hide();
	  });
	
	
    $("#SavePngButton").click(function () {

        html2canvas($("#graphView"), {
            onrendered: function (canvas) {
                //alert("onrendered emthod: ")
                canvas.toBlob(function (blob) {
                    //you can changename
                    var name = "BandwidthGraph.pdf";
                    var imgData = canvas.toDataURL(blob);
                    var doc = new jsPDF('p', 'mm');
                    doc.addImage(imgData, 'PNG', 8, 10, 198, 105);
                    doc.save(name);
                });
            }
        });
    });
});

$(function () {

    $("#SaveJPGButton").click(function () {

        html2canvas($("#graphView"), {
            onrendered: function (canvas) {
                //alert("onrendered emthod: ")
                canvas.toBlob(function (blob) {
                    //you can changename
                    saveAs(blob, "BandwidthGraph.jpeg");
                });
            }
        });
    });
});


// Date range as a button
$('#daterange-btn').daterangepicker(
		{
			timePicker : true,
			timePickerIncrement : 10,
			ranges : {
				'Today' : [ moment(), moment() ],
				'Yesterday' : [ moment().subtract(1, 'days'),
						moment().subtract(1, 'days') ],
				'Last 7 Days' : [ moment().subtract(6, 'days'), moment() ],
				'Last 30 Days' : [ moment().subtract(29, 'days'), moment() ],
				'This Month' : [ moment().startOf('month'),
						moment().endOf('month') ],
				'Last Month' : [
						moment().subtract(1, 'month').startOf('month'),
						moment().subtract(1, 'month').endOf('month') ]
			},
			startDate : moment().subtract(29, 'days'),
			endDate : moment()
		}, function(start, end) {
			var from_date = document.getElementById("from_date");
			from_date.value = start.format('YYYY-MM-DD HH:mm:ss');
			var to_date = document.getElementById("to_date");
			to_date.value = end.format('YYYY-MM-DD HH:mm:ss');

		})

$('#reservationtime').daterangepicker({
	timePicker : true,
	timePickerIncrement : 10,
	locale : {
		format : 'MM/DD/YYYY hh:mm:ss'
	}

}, function(start, end) {

	var from_date = document.getElementById("from_date");
	from_date.value = start.format('YYYY-MM-DD HH:mm:ss');
	var to_date = document.getElementById("to_date");
	to_date.value = end.format('YYYY-MM-DD HH:mm:ss');
});



$(function() {

	$('[data-mask]').inputmask()
	// Bootstrap Duallistbox

})



function generateGraph()
{
	alert("generate Graph")
	alert( $('#interface_name').val())

    $.ajax({
        type: 'POST',
        url: 'interfaceBandwidthHistoryGraph',
        dataType: 'json',
        data: 'ip_address=' + $('#ip_address').val()
                + '&from_date=' + $('#from_date').val()
                + '&to_date=' + $('#to_date').val()
                + '&interface_name=' + $('#interface_name').val(),
        success: function (jsondata) {
            alert("Bandwidth Utilization");
 alert("inTraffic:"+jsondata.inTraffic);
  alert("outTraffic:"+jsondata.outTraffic);
            var start = +new Date();
            // Create the chart

            Highcharts.setOptions({
                global: {
                    useUTC: false
                }
            });

            Highcharts.stockChart('containervlx', {
                chart: {
                    events: {
                        load: function () {
                            this.setTitle(null, {
                                text: 'IN OUT Traffic#' + (new Date() - start) + 'ms'
                                        // text: 'IN OUT Traffic ' + (new Date() - start) + 'ms'
                            });
                        }
                    },
                    zoomType: 'x'
                },
                rangeSelector: {
                    buttons: [{
                            type: 'day',
                            count: 3,
                            text: '3d'
                        }, {
                            type: 'week',
                            count: 1,
                            text: '1w'
                        }, {
                            type: 'month',
                            count: 1,
                            text: '1m'
                        }, {
                            type: 'month',
                            count: 6,
                            text: '6m'
                        }, {
                            type: 'year',
                            count: 1,
                            text: '1y'
                        }, {
                            type: 'all',
                            text: 'All'
                        }],
                    selected: 3
                },
                yAxis: {
                    title: {
                        text: 'Bandwidth Utilization'
                    }
                },
//                            title: {
//                                text: 'Bandwidth Utilization'
//                            },

                subtitle: {
                    text: 'IN OUT Traffic' // dummy text to reserve space for dynamic subtitle
                },
                series: [
                    {
                        name: 'In Traffic',
                       data: jsondata.inTraffic,


                      ///  data:[[1147651200000, 67.79], [1147737600000, 64.98], [1147824000000, 65.26], [1147910400000, 63.18], [1147996800000, 64.51], [1148256000000, 63.38]],

                                showInNavigator: true,
                        //  pointStart: data.pointStart,
                        //    pointInterval: data.pointInterval,
                        tooltip: {
                            valueDecimals: 1,
                            valueSuffix: 'kbps'
                        }




                    },
                    {
                        name: 'Out Traffic',
                       data: jsondata.outTraffic,

                     //  data:[[1147651200000, 56], [1147737600000, 87], [1147824000000, 65.26], [1147910400000,98], [1147996800000, 12], [1148256000000, 63.38]],

                        showInNavigator: true,
                        tooltip: {
                            valueDecimals: 1,
                            valueSuffix: 'kbps'
                        }
                    }
                ]

            });


        }

    });
	
}
