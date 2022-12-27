<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:choose>
	<c:when test="${emailConfigId==null}">
		<title>Add Email Configuration</title>
	</c:when>
	<c:otherwise>
		<title>Edit Email Configuration</title>
	</c:otherwise>
</c:choose>


<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">

<!-- DataTables -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">

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


<script>
	function showhide() {

		var div = document.getElementById("smtpcheckbox");

		if (div.checked == true) {
			document.getElementById("usernameid").style.display = 'block';
			document.getElementById("passwordid").style.display = 'block';
		} else {
			document.getElementById("usernameid").style.display = 'none';
			document.getElementById("passwordid").style.display = 'none';
		}
	}
</script>


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
							<c:choose>
								<c:when test="${emailConfigId==null}">
									<h1>Add Email Configuration</h1>
								</c:when>
								<c:otherwise>
									<h1>Edit Email Configuration</h1>
								</c:otherwise>
							</c:choose>

						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<c:choose>
									<c:when test="${emailConfigId==null}">
										<li class="breadcrumb-item active">Add Email
											Configuration</li>
									</c:when>
									<c:otherwise>
										<li class="breadcrumb-item active">Edit Email
											Configuration</li>
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
									<c:choose>
										<c:when test="${emailConfigId==null}">
											<h3 class="card-title">Add Email Configuration</h3>
										</c:when>
										<c:otherwise>
											<h3 class="card-title">Edit Email Configuration</h3>
										</c:otherwise>
									</c:choose>

								</div>
								<!-- /.card-header -->
								<!-- form start -->
								<!--<form id="quickForm">-->
								<form:form method="post" id="addEmailConfigId"
									modelAttribute="fetchEmailConfigInfo">
									<div class="card-body">

										<div class="row">



											<div class="col-md-6">

												<div class="form-group">
													<label for="exampleInputEmail1">From email address</label>
													<form:input type="text" path="emailId" class="form-control"
														placeholder="Enter email address" />
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Port</label>
													<form:input type="text" path="port" class="form-control"
														placeholder="Enter port" />
												</div>
												<br>
											</div>


											<div class="col-md-6">
												<div class="form-group">
													<label for="exampleInputPassword1">SMTP server</label>

													<form:input type="text" path="smtpServer"
														class="form-control" placeholder="Enter SMTP server" />
												</div>


												<div class="form-group">
													<label for="exampleInputPassword1">Timeout (sec)</label>

													<form:input type="text" path="timeout" class="form-control"
														placeholder="Enter timeout" />
												</div>
												<br>

											</div>

											<div class="col-md-6">

												<div class="form-check">
													<!-- <input type="checkbox" class="form-check-input"
														id="sslcheckbox" path="isSslTls"> <label
														class="form-check-label" style="font-weight: bold;"
														for="sslcheckbox">Use an encrypted connection
														(SSL/TLS)</label> -->

													<c:choose>
														<c:when test="${emailsslcheckbox==true}">
															<input type="checkbox" class="form-check-input"
																id="sslcheckbox" path="isSslTls" checked>
															<label class="form-check-label"
																style="font-weight: bold;" for="sslcheckbox">Use
																an encrypted connection (SSL/TLS)</label>
														</c:when>
														<c:otherwise>
															<input type="checkbox" class="form-check-input"
																id="sslcheckbox" path="isSslTls">
															<label class="form-check-label"
																style="font-weight: bold;" for="sslcheckbox">Use
																an encrypted connection (SSL/TLS)</label>
														</c:otherwise>
													</c:choose>

												</div>
												<br>
												<div class="form-group" id="usernameid">
													<label for="exampleInputEmail1">Username</label>
													<form:input type="text" path="username"
														class="form-control" placeholder="Enter username" />
												</div>

											</div>

											<div class="col-md-6">

												<div class="form-check">



													<c:choose>
														<c:when test="${emailsmtpcheckbox==true}">
															<input type="checkbox" class="form-check-input"
																id="smtpcheckbox" onclick="showhide()" path="isSmtpAuth"
																checked>
															<label class="form-check-label"
																style="font-weight: bold;" for="smtpcheckbox">Use
																SMTP Authentication</label>
														</c:when>
														<c:otherwise>
															<input type="checkbox" class="form-check-input"
																id="smtpcheckbox" onclick="showhide()" path="isSmtpAuth">
															<label class="form-check-label"
																style="font-weight: bold;" for="smtpcheckbox">Use
																SMTP Authentication</label>
														</c:otherwise>
													</c:choose>

												</div>
												<br>
												<div class="form-group" id="passwordid">
													<label for="exampleInputEmail1">Password</label>
													<form:input type="password" path="password"
														class="form-control" placeholder="Enter password" />
												</div>

											</div>

											<form:input type="hidden" path="id" />

										</div>

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

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">


							<div class="card">
								<div class="card-header">
									<h3 class="card-title">Email Configuration Details</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">


									<table id="example1" class="table table-bordered table-striped">
										<thead>
											<tr>
												<th>Sr. No</th>
												<th>From Email ID</th>
												<th>SMTP Server</th>
												<th>Port</th>
												<th>Timeout (sec)</th>
												<th>SSL/TLS</th>
												<th>SMTP Authentication</th>
												<th>Username</th>
												<!--  	<th>Edit</th> -->
												<th>Delete</th>

											</tr>
										</thead>
										<tbody>

										</tbody>

									</table>


								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->


		</div>
		<!-- /.content-wrapper -->
		<!-- Footer  -->
		<jsp:include page="footer.jsp" />
		<!-- /. Footer -->

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

	<!-- DataTables  & Plugins -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jszip/jszip.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/pdfmake/pdfmake.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/pdfmake/vfs_fonts.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/buttons.print.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>

	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<!-- Sweet Alert -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/sweetalert2/sweetalert2.min.js"></script>
	<!-- Page specific script -->
	<script src="<%=request.getContextPath()%>/custom_js/addEmailConfig.js"></script>

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
