
$('#importNodeIP').click(function(){
  
    	var nodeIP = [];
        $.each($("input[name='selectNodeIP']:checked"), function(){            
        	nodeIP.push($(this).val());
         });
        
        var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/node/importNodeIP";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'nodeIP='+nodeIP,
	  }).done(function(data) {
			if (data === "success") {

				Swal.fire({
					position : 'top',
					icon : 'success',
					title : 'Node IP Imported Successfully',
					showConfirmButton : false,
					timer : 3000
				}).then(() => {
					 var l = window.location;
					   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
					  window.location.href =base_url+"/master/discoveredNetwork";

					})

			}
			else{
		    	 
		    	Swal.fire({
		    		  position: 'top',
		    		  icon: 'warning',
		    		  title: 'Failed to Import Node IP',
		    		  showConfirmButton: false,
		    		  timer: 3000
		    		})
				     
			    }
	});
});

$(function () {
  $.validator.setDefaults({
    submitHandler: function () {
   alert( "Form successful submitted!" );
    	addUpdate()	
      
    }
  });
  $('#addNodeId').validate({
    rules: {
    	
    	deviceIp:{
    		
    		required: true
    		
    	},
    	deviceName:{
    		required: true
    	},
    	

    	
    	company: {
    		required: true,
    		
    	},
    	
    	zone: {
    		required: true
    	},
    	
    	
    	
      district: {
        required: true,
        
      },
      state: {
        required: true,
       
      },
      
      location: {
          required: true,
         
        },
      

  	
      

    },
    messages: {
    	deviceIp: {
        required: "Please provide Device IP",
        
      },
  	deviceName: {
        required: "Please provide Device Name",
      },
      
      company: {
            required: "Enter Provide Company Name",
            
        },
      
        zone: {
              required: "Please enter ther Zone",
              
        },
      
        state: {
                required: "Please enter the State",
                
              },
              location: {
                  required: "Please enter Location",
                  
                },
                district: {
                    required: "Please enter the District",
                    
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

function editAddNode(id) {
	alert("edit called")
	var l = window.location;
	var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	var serviceUrl = base_url + "/node/editAddNode";
	$.ajax({
		type : 'POST',
		url : serviceUrl,
		data : 'id=' + id,
	}).done(function(data) {
		alert(data)
	});
}

function deleteAddNode(id) {

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
	var serviceUrl = base_url + "/node/deleteAddNode";
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
				  window.location.href =base_url+"/node/viewAddNode";

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
function addUpdate(){
	
	var dbid=$('#id').val()
	 // alert("DB ID"+dbid)
	
	if(dbid==0)
		{
		alert("Add action call"+dbid)
		addData();
		
		}
	else
		{
		alert("Update action call"+dbid)
		updateData();
		}
	
}





function updateData()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/node/updateAddNode";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'deviceIp='+$('#deviceIp').val()
	        + '&deviceName='+$('#deviceName').val()
	        +'&deviceType='+$('#deviceType').val()
	        +'&groupName='+$('#groupName').val()
	        +'&snmp='+$('#snmp').val()
	        +'&serviceProvider='+$('#serviceProvider').val()
	        +'&company='+$('#company').val()
	         +'&zone='+$('#zone').val()
	          +'&state='+$('#state').val()
	           +'&district='+$('#district').val()
	            +'&id='+$('#id').val()
	        +'&location='+$('#location').val(),
	        
	    }).done(function (data) {
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Update add node Data Successfully',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/node/viewAddNode";

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

// ICMP config
function addData()
{
	 var l = window.location;
		var base_url = l.protocol + "//" + l.host + "/"
				+ l.pathname.split('/')[1];
		var serviceUrl = base_url + "/node/addNode";
	  $.ajax({
	        type: 'POST',
	        url: serviceUrl,
	        data:'deviceIp='+$('#deviceIp').val()
	        + '&deviceName='+$('#deviceName').val()
	        +'&deviceType='+$('#deviceType').val()
	        +'&groupName='+$('#groupName').val()
	        +'&snmp='+$('#snmp').val()
	        +'&serviceProvider='+$('#serviceProvider').val()
	        +'&company='+$('#company').val()
	         +'&zone='+$('#zone').val()
	          +'&state='+$('#state').val()
	           +'&district='+$('#district').val()
	        +'&location='+$('#location').val(),
	     

	        
		     

	    }).done(function (data) {
	    	
	    	
	    // alert(data)
	    	console.log(data);

		    if(data=== "success"){

Swal.fire({
position: 'top',
icon: 'success',
title: 'Add node Successfull',
showConfirmButton: true,
timer: 3000
}).then(() => {
	 var l = window.location;
	   var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
	  window.location.href =base_url+"/node/addNodeForm";

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

