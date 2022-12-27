$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
   // alert( "Form successful submitted!" );
    	addConfigBackup();
      
    }
  });
  $('#configBackup').validate({
    rules: {
    	
    	backuppath:{
    		
    		required: true
    		
    	},
    	timepicker:{
    		required: true
    	},
      

    },
    messages: {
      backuppath: {
        required: "Please enter a Backup Path",
        
      },
      timepicker: {
        required: "Time is Required",
        
      },
      
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

function addConfigBackup()
{
	 var val = [];
     $('.checkers:checked').each(function(i){
       val[i] = $(this).val();
     });
     
     var ipAddress = val.join(",")
	var runConfig = $('#runConfig').is(":checked")
	var startupConfig =  $('#startupConfig').is(":checked")
	var backup = $("input:radio[name=radio1]:checked").val()
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/node/addConfigBackup";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'backuppath='+$('#backuppath').val()
	        + '&runConfig='+runConfig
	        +'&startupConfig='+startupConfig
	        +'&backup='+backup
	        +'&timepicker='+$('#timepicker').val()
	        +'&group_name='+$('#group_name').val()
	        +'&ipAddressCheck='+ipAddress,

	    }).done(function (data) {
	    	

		    if(data=== "success"){

				Swal.fire({
				position: 'top',
				icon: 'success',
				title: 'Data inserted Successfully',
				showConfirmButton: true,
				timer: 3000
				}).then(() => {
					 var l = window.location;
					   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
					  window.location.href =base_url+"/node/configBackup";
				
					})
						      
						    }else{
						    	 
						    	Swal.fire({
						    		  position: 'top',
						    		  icon: 'warning',
						    		  title: 'Failed',
						    		  showConfirmButton: false,
						    		  timer: 3000
						    		})
								     
							    }
					    });	
}

function myFunction() {
	var change = document.getElementById("toggle");
	if (change.innerHTML == "Select All") {
		$('.checkers').each(function() {
			this.checked = true;
		});
		change.innerHTML = "Un-Select All";
	} else {
		$('.checkers').each(function() {
			this.checked = false;
		});
		change.innerHTML = "Select All";
	}
}

function deleteConfigBackupdata(id) {

	Swal.fire({
		position : 'top',
		  title: 'Are you sure?',
		  text: "You want to delete the record:"+id,
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes, delete it!'
		})
	.then((result) => {
		  if (result.value) {
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/node/deleteConfigBackup";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : 'id=' + id,
	}).done(function(data) {

		if (data === "success") {

			Swal.fire({
				position : 'top',
				icon : 'success',
				title : 'Delete Record Successfully',
				showConfirmButton : false,
				timer : 3000
			}).then(() => {
				 var l = window.location;
				   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
				  window.location.href =base_url+"/node/viewconfigBackupdata";

				})

		} else {

			Swal.fire({
				position : 'top',
				icon : 'warning',
				title : 'Record Not Deleted',
				showConfirmButton : false,
				timer : 3000
			})

		}
	});
		  }
	});
}


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

function groupDevices(option) {
	var grpname = option.value;
	// alert(grpname);
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/nodeReport/getGroupDeviceDetails";
	// var serviceUrl = base_url + "/master/viewCompanyMaster";

	$
			.ajax({
				type : 'get',
				dataType : 'json',
				url : serviceUrl,
				data : 'groupName=' + grpname

			})
			.done(
					function(data5) {
						// alert(data5);
						var table = $('#example').DataTable();
						table.destroy();
						$('#example thead tr').clone(true).appendTo(
								'#example thead').addClass('searchRow');
						// $('#example thead tr').clone(true).appendTo('#example
						// thead');
						$('.searchRow').css('display', 'none');

						$('#example thead tr:eq(1) th')
								.each(
										function(i) {
											var title = $(this).text();

											if (title === "Select"
													|| title === "Action") {
												$(this)
														.html(
																'<div><label>&nbsp;</label></div>');

											} else {

												$(this)
														.html(
																'<input type="text" class="customSearch" placeholder="Search" />');

												$('input', this)
														.on(
																'keyup change',
																function() {
																	if (table
																			.column(
																					i)
																			.search() !== this.value) {
																		table
																				.column(
																						i)
																				.search(
																						this.value)
																				.draw();
																	}
																});

											}
										});

						var table = $('#example')
								.DataTable(
										{
											dom : '<"pull-left"Bl><"pull-right"f>rtip',
											orderCellsTop : true,
											// fixedHeader: true,
											scrollX : true,
											data : data5,
											paging : false,
											scrollY : '350px',
											orderCellsTop : true,
											fixedHeader : true,
											buttons : [
// {
//
// text : 'Submit',
// className : 'btn btn-primary newButton',
// action : function(e,
// dt, node, conf) {
// generateReport();
// },
// init : function(api,
// node, config) {
// $(node)
// .removeClass(
// 'dt-button')
// // $(node).addClass('btn-danger')
// }
// },

													{
														text : '<div id="toggle" onclick="myFunction()">Select All</div>',
														className : 'newButton',
														action : function(e,
																dt, node,
																config) {

														},

													},
													{
														text : 'Toggle Search',

														action : function() {
															var element = document
																	.querySelector('.searchRow');
															var style = getComputedStyle(element);
															var displaystyle = style.display;

															if (displaystyle === "none") {
																$('.searchRow')
																		.css(
																				'display',
																				'contents');
															} else {
																$('.searchRow')
																		.css(
																				'display',
																				'none');
															}

														},

													},

											],

										});

						if ($("#utype").val() == "User") {
							$(".newButton").hide();
							// table.columns([0,1]).visible(false);
						}

					});
}

// $(function() {
// // Initialize Select2 Elements
// $('.select2').select2()
//
// // Initialize Select2 Elements
// $('.select2bs4').select2({
// theme : 'bootstrap4'
// })
//
// });




function generateReport() {
// alert("generate report")
	// var daterange = document.getElementById("reportrange").value;
	// alert(daterange)

	// var from_date = document.getElementById("from_date").value;
	// alert(from_date)
	//
	// var to_date = document.getElementById("to_date").value;
	// alert(to_date)
	// var grpname = document.getElementById("group_name").value;
	// alert(grpname)

	var checkboxes = document.querySelectorAll('input[name="check"]:checked'), values = [];
	Array.prototype.forEach.call(checkboxes, function(el) {
		values.push(el.value);

	});

	if (!values.length > 0) {

		Swal.fire({
			position : 'top',
			icon : 'info',
			title : 'Please Select atleast one checkbox',
			showConfirmButton : false,
			timer : 3000
		})
	} else {

		var temp = new Array();
		temp = values.toString().split(",");
		alert(temp)

		var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/nodeReport/scanStatus";
		$.ajax({
			type : 'POST',
			url : serviceUrl,
			data : 'ip_address=' + ip_address

		}).done(
				function(data) {
					// alert("DATA "+data)
					sessionStorage.setItem("Device_IP", data);
					var l = window.location;
					var base_url = l.protocol + "//" + l.host + "/"
							+ l.pathname.split('/')[1];
					var serviceUrl = base_url + "/Discover/viewIPScan";
					window.location.href = serviceUrl;

					var ip_address = [];
				});

		// + '&from_date='+$('#from_date').val()
		// +'&to_date='+$('#to_date').val()
		// +'&group_name='+$('#group_name').val(),

		// sessionStorage.setItem("from_date",$('#from_date').val());
		// sessionStorage.setItem("to_date",$('#to_date').val());
		// sessionStorage.setItem("group_name",$('#group_name').val());
		// sessionStorage.setItem("ip_list",temp);
		// var l = window.location; var base_url =l.protocol + "//" + l.host +
		// "/" +l.pathname.split('/')[1];
		// var serviceUrl =base_url + "/nodeReport/nodeStatusReport";
		// window.location.href=serviceUrl;

		//		
		// var l = window.location;
		// var base_url = l.protocol + "//" + l.host + "/"
		// + l.pathname.split('/')[1];
		// var serviceUrl = base_url + "/nodeReport/nodeStatusReport";
		//			
		//			
		//		
		// $.ajax({
		// url : serviceUrl,
		// data : 'iplist=' + temp,
		//			
		// data:'iplist='+temp
		// + '&from_date='+$('#from_date').val()
		// +'&to_date='+$('#to_date').val()
		// +'&group_name='+$('#group_name').val(),
		//		
		//			
		// type : 'post',
		// success : function(data) {
		//				
		// alert(data)
		//
		// }
		// })
		// alert(temp);
	}

}
