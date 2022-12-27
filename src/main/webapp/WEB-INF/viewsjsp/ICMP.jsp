<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>ICMP Configuration | DataTables</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%-- <c:choose>
	<c:when test="${userId==null}">
		<title>Add User</title>
	</c:when>
	<c:otherwise>
		<title>Edit User</title>
	</c:otherwise>
</c:choose>

 --%>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/daterangepicker/daterangepicker.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- Select2 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/select2/css/select2.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
<!-- BS Stepper -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/bs-stepper/css/bs-stepper.min.css">
<!-- dropzonejs -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/dropzone/min/dropzone.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">



<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">



</head>
<body class="hold-transition layout-top-nav">


	<div class="wrapper">

		<!-- Navbar -->

		<jsp:include page="header.jsp" />

		<!-- /.navbar -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">

							<h1>ICMP Configuration</h1>


						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<c:choose>
									<c:when test="${userId==null}">
										<li class="breadcrumb-item active">Add ICMP</li>
									</c:when>
									<c:otherwise>
										<li class="breadcrumb-item active">Edit ICMP</li>
									</c:otherwise>
								</c:choose>

							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<!-- left column -->
						<div class="col-md-12">
							<!-- jquery validation -->
							<div class="card card-primary">
								<div class="card-header">

									<h3 class="card-title">ICMP Configuration</h3>

								</div>
								<!-- /.card-header -->
								<!-- form start -->
								<!--<form id="quickForm">-->
								<form:form method="post" id="addIcmpId" modelAttribute="icmp">
									<div class="card-body">

										<div class="row">

											<div class="col-md-6">

												<div class="form-group">
													<label for="exampleInputEmail1">Packet-Size</label>
													<!--  <input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">-->
													<form:input type="text" path="packetSize"
														class="form-control" placeholder="Packet-Size" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Packet-Count</label>
													<!--  <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">-->
													<form:input type="text" path="packetCount"
														class="form-control" placeholder="Packet-Count" />
												</div>
											</div>


											<div class="col-md-6">
												<div class="form-group">
													<label for="exampleInputPassword1">Timeout</label>

													<form:input type="text" path="timeout" class="form-control"
														placeholder="Timeout" />
												</div>


												<div class="form-group">
													<label for="exampleInputPassword1">TTL</label>

													<form:input type="text" path="ttl" class="form-control"
														placeholder="TTL" />
												</div>

											</div>

											<div class="col-md-6">

												<div class="form-group">
													<label for="exampleInputEmail1">Pooling-Time</label>
													<!--  <input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">-->
													<form:input type="text" path="poolingTime"
														class="form-control" placeholder="Pooling-Time" />
												</div>

												<div class="form-group">
													<label for="exampleInputEmail1">Customer Name</label>
													<!--  <input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">-->
													<form:input type="text" path="customerName"
														class="form-control" placeholder="Customer Name" />
												</div>


											</div>

											<div class="col-md-6">

												<div class="form-group">
													<label for="exampleInputEmail1">Location</label>
													<!--  <input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">-->
													<form:input type="text" path="location"
														class="form-control" placeholder="Location" />
												</div>

												<%-- 												<div class="form-group">
													<label for="exampleInputEmail1">Department</label>
													<!--  <input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">-->
													<form:input type="text" path="department"
														class="form-control" placeholder="Enter Department" />
												</div>

 --%>


												<form:input type="hidden" path="id" />



											</div>
											<!-- /.card-body -->
											<div class="card-footer">
												<button type="submit" class="btn btn-primary">Submit</button>
											</div>
								</form:form>
							</div>
							<!-- /.card -->
						</div>
						<!--/.col (left) -->
						<!-- right column -->
						<div class="col-md-6"></div>
						<!--/.col (right) -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<footer class="main-footer">
			<div class="float-right d-none d-sm-block">
				<b>Version</b> 3.1.0
			</div>
			<strong>Copyright &copy; 2014-2021 <a
				href="https://adminlte.io">AdminLTE.io</a>.
			</strong> All rights reserved.
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>
	<!-- ./wrapper -->

	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- jquery-validation -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery-validation/additional-methods.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<!-- Sweet Alert -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2/sweetalert2.min.js"></script>
	<!-- Page specific script -->
	<script src="<%=request.getContextPath()%>/custom_js/icmp.js"></script>

	<!-- Select2 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/select2/js/select2.full.min.js"></script>

	<script>
		$(function() {
			//Initialize Select2 Elements
			$('.select2').select2()

			//Initialize Select2 Elements
			$('.select2bs4').select2({
				theme : 'bootstrap4'
			})

		});
	</script>

</body>
</html>
