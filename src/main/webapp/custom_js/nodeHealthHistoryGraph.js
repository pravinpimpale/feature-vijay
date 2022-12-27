
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
	//alert("generate Graph")
	

    $.ajax({
        type: 'POST',
        url: 'nodeHealthHistoryGraph',
        dataType: 'json',
        data: 'ip_address=' + $('#ip_address').val()
                + '&from_date=' + $('#from_date').val()
                + '&to_date=' + $('#to_date').val(),
        success: function (jsondata) {
        //    alert("Bandwidth Utilization");
// alert("cpuUtilization:"+jsondata.cpuUtilization);
 // alert("memoryUtilization:"+jsondata.memoryUtilization);
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
                                text: 'Health Graph#' + (new Date() - start) + 'ms'
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
                        text: 'Health'
                    }
                },
//                            title: {
//                                text: 'Bandwidth Utilization'
//                            },

                subtitle: {
                    text: 'Health' // dummy text to reserve space for dynamic subtitle
                },
                series: [
                    {
                        name: 'CPU Utilization',
                       data: jsondata.cpuUtilization,


                      ///  data:[[1147651200000, 67.79], [1147737600000, 64.98], [1147824000000, 65.26], [1147910400000, 63.18], [1147996800000, 64.51], [1148256000000, 63.38]],

                                showInNavigator: true,
                        //  pointStart: data.pointStart,
                        //    pointInterval: data.pointInterval,
                        tooltip: {
                            valueDecimals: 1,
                            valueSuffix: '%'
                        }




                    },
                    {
                        name: 'Memory Utilization',
                       data: jsondata.memoryUtilization,

                     //  data:[[1147651200000, 56], [1147737600000, 87], [1147824000000, 65.26], [1147910400000,98], [1147996800000, 12], [1148256000000, 63.38]],

                        showInNavigator: true,
                        tooltip: {
                            valueDecimals: 1,
                            valueSuffix: '%'
                        }
                    }
                ]

            });


        }

    });
	
}
