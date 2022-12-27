<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Device Details</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
<!-- IonIcons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">
<!-- date range picker -->


<!-- <script type="text/javascript" -->
<!-- 	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script> -->
<!-- <script type="text/javascript" -->
<!-- 	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script> -->
<!-- <script type="text/javascript" -->
<!-- 	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" /> -->


<!-- DataTables -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/css/dataTables.bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/css/fixedHeader.dataTables.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/css/jquery.dataTables.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/css/select.dataTables.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/css/buttons.dataTables.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/css/colReorder.dataTables.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/plugins/daterangepicker/daterangepicker.css">

<style>
.dropbtn {
	/* 	background-color: #3498DB; */
	color: #3498DB;
	padding: 16px;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

.dropbtn:hover, .dropbtn:focus {
	/* 	background-color: #2980B9; */
	color: red;
}

.dropdown {
	float: right !important;
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 160px;
	overflow: auto;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown a:hover {
	background-color: #ddd;
	background-color: #3498DB;
}

.show {
	display: block;
}
</style>
</head>
<!--
`body` tag options:

  Apply one or more of the following classes to to the body tag
  to get the desired effect

  * sidebar-collapse
  * sidebar-mini
-->
<body class="hold-transition layout-top-nav">

	<input type="hidden" name="deviceIP" id="deviceIP"
		value='<%=request.getParameter("nodeIP")%>'>

	<div class="wrapper">

		<jsp:include page="header.jsp" />
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">
								Node Details
								<%=request.getParameter("nodeIP")%></h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Node Details</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<div class="content">
				<div class="container-fluid">




					<div class="row">
						<div class="col-md-6">
							<!-- Line chart -->

							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Basic Information</h3>
									<div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a> <a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-bars"></i>
										</a>
									</div>
								</div>
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle">
										<thead>
											<tr>
												<th>Label</th>
												<th>Information</th>

											</tr>
										</thead>

										<tbody>
											<tr>
												<td>Node IP</td>
												<!-- 												<td>192.168.0.1</td> -->
												<td><span id="Node_ip"></span></td>
											</tr>
											<tr>
												<td>Node Name</td>
												<!-- 												<td>VSHRouter</td> -->
												<td><span id="NodeNAme"></span></td>
											</tr>
											<tr>
												<td>Location</td>
												<!-- 												<td>Vashi</td> -->
												<td><span id="Location"></span></td>
											</tr>
											<tr>
												<td>Company Name</td>
												<!-- 												<td>Cisco IOS Software</td> -->
												<td><span id="Company"></span></td>
											</tr>

											<tr>
												<td>Version</td>
												<!-- 												<td>Version Version 15.4(3)M2</td> -->
												<td><span id="Version"></span></td>
											</tr>

											<tr>
												<td>Model</td>
												<!-- 												<td>C3900 Software (C3900-UNIVERSALK9-M)</td> -->
												<td><span id="Model"></span></td>
											</tr>

											<tr>
												<td>Uptime</td>
												<!-- 												<td>Uptime 487 days, 11:18:41.46</td> -->
												<td><span id="Uptime"></span></td>
											</tr>



											<!-- 											<tr> -->
											<!-- 												<td>Customer Name</td> -->
											<!-- 																								<td>HDFC</td> -->
											<!-- 												<td><span id="Customer"></span></td> -->
											<!-- 											</tr> -->

											<!-- 											<tr> -->
											<!-- 												<td>State</td> -->
											<!-- 												<td>Maharashtra</td> -->
											<!-- 												<td><span id="State"></span></td> -->
											<!-- 											</tr> -->
											<tr>
												<td>District</td>
												<!-- 												<td>Thane</td> -->
												<td><span id="District"></span></td>
											</tr>


										</tbody>
									</table>
								</div>
							</div>



						</div>
						<!-- /.col -->
						<div class="col-md-6">
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Node Latency</h3>

									</div>
									<div id="dropdown" style="float: right; margin-top: -5%;">
										<p onclick="myFunction()" class="dropbtn"
											style="float: right; margin-top: -6%;">View Report</p>
										<!-- 				<div id="myDropdown" class="dropdown-content" style="margin-left: 71%;margin-top: 47px;">-->
										<div id="myDropdown" class="dropdown-content"
											style="margin-top: 6%; margin-left: -5%;">
											<a href="#" data-toggle="modal" data-target="#dateModal">Export</a>
											<a href="#graph">Graph</a>

										</div>
									</div>

								</div>
								<div class="card-body">
									<div class="d-flex">
										<!-- 										<p class="d-flex flex-column"> -->
										<!-- 											<span class="text-bold text-lg">125</span> <span>AVG -->
										<!-- 												Latency</span> -->
										<!-- 										</p> -->
										<!-- 										<p class="ml-auto d-flex flex-column text-right"> -->
										<!-- 											<span class="text-success"> 72% </span> <span -->
										<!-- 												class="text-muted">AVG Packet Drop</span> -->
										<!-- 										</p> -->
									</div>
									<!-- /.d-flex -->

									<div class="position-relative mb-4">
										<div id="container_latency"
											style="height: 400px; min-width: 310px"></div>
										<%-- 										<canvas id="visitors-chart" height="200"></canvas> --%>
									</div>

									<div class="d-flex flex-row justify-content-end">
										<span class="mr-2"> <i
											class="fas fa-square text-primary"></i> Latency
										</span> <span> <i class="fas fa-square text-gray"></i> Packet
											Drop
										</span>
									</div>
								</div>
							</div>
							<!-- /.card -->

							<!-- /.card -->
						</div>

						<!-- /.col -->
					</div>



					<!-- First Row Complete -->

					<div class="row">
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Node Availablity</h3>
										<!-- 										<a href="javascript:void(0);">View Report</a> -->
									</div>
									<div id="dropdown" style="float: right; margin-top: -5%;">
										<!-- <p onclick="NodeAvailabilityDropdown()" class="dropbtn"
											style="float: right; margin-top: -6%;">View Report</p>-->
										<!-- 											<div id="myDropdown" class="dropdown-content" style="margin-left: 71%;margin-top: 47px;"> -->
										<div id="NodeAvailability" class="dropdown-content"
											style="margin-top: 6%; margin-left: -5%;">
											<a href="#" data-toggle="modal" data-target="#dateNodeModal">Export</a>
											<a href="#graph">Graph</a>

										</div>
									</div>
								</div>
								<div class="card-body">
									<div class="d-flex">
										<p class="d-flex flex-column">
											<span class="text-bold text-lg"></span> <span>Uptime
												Over Time</span>
										</p>
										<p class="ml-auto d-flex flex-column text-right">


											<span class="text-muted">Date Wise (Avg %)</span>
										</p>
									</div>


									<div class="position-relative mb-4">
										<canvas id="sales-chart" height="200"></canvas>
									</div>

									<div class="d-flex flex-row justify-content-end">
										<span class="mr-2"> <i
											class="fas fa-square text-primary"></i> Uptime
										</span> <span> <i class="fas fa-square text-gray"></i>
											Downtime
										</span>
									</div>
								</div>
							</div>
						</div>

						<div class="card">
							<div class="card-header border-0">
								<h3 class="card-title">Interface Summary</h3>
								<div class="card-tools">
									<a href="#" class="btn btn-tool btn-sm"> <i
										class="fas fa-download"></i>
									</a> <a href="#" class="btn btn-tool btn-sm"> <i
										class="fas fa-bars"></i>
									</a>
								</div>
							</div>
							<div class="card-body table-responsive p-0">
								<table class="table table-striped table-valign-middle"
									name="interfaceStatusSummary" id="interfaceStatusSummary">
									<thead>
										<tr>
											<th>Interface Name</th>
											<th>Interface IP</th>
											<th>Status</th>
											<th>Alias Name</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>

					</div>

					<!-- Second Row Complete -->

					<div class="row">

						<div class="col-md-6">

							<!-- Donut chart -->
							<div class="card card-primary card-outline">
								<div class="card-header">
									<h3 class="card-title">
										<i class="far fa-chart-bar"></i> Memory Utilization
									</h3>

									<div class="card-tools">
										<button type="button" class="btn btn-tool"
											data-card-widget="collapse">
											<i class="fas fa-minus"></i>
										</button>
										<button type="button" class="btn btn-tool"
											data-card-widget="remove">
											<i class="fas fa-times"></i>
										</button>
									</div>
								</div>
								<div class="card-body">
									<div id="donut-chart" style="height: 300px;"></div>
								</div>
								<!-- /.card-body-->
							</div>
							<!-- /.card -->
						</div>




						<!-- /.col-md-6 -->
						<div class="col-lg-6">




							<!-- /.card -->

							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">RAM</h3>
									<div class="card-tools">
										<a href="#" class="btn btn-sm btn-tool"> <i
											class="fas fa-download"></i>
										</a> <a href="#" class="btn btn-sm btn-tool"> <i
											class="fas fa-bars"></i>
										</a>
									</div>
								</div>
								<div class="card-body">
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-success text-xl">
											<i class="ion ion-ios-refresh-empty"></i>
										</p>
										<p class="d-flex flex-column text-right">
											<span class="font-weight-bold"> <i
												class="ion ion-android-arrow-up text-success"></i><span
												id="Total_RAM"></span>
											</span> <span class="text-muted">Total RAM(MB)</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-warning text-xl">
											<i class="ion ion-ios-cart-outline"></i>
										</p>
										<p class="d-flex flex-column text-right">
											<span class="font-weight-bold"> <i
												class="ion ion-android-arrow-up text-warning"></i><span
												id="Used_RAM"></span>
											</span> <span class="text-muted">Used RAM(MB)</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center mb-0">
										<p class="text-danger text-xl">
											<i class="ion ion-ios-people-outline"></i>
										</p>
										<p class="d-flex flex-column text-right">
											<span class="font-weight-bold"> <i
												class="ion ion-android-arrow-down text-danger"></i><span
												id="Free_RAM"></span>
											</span> <span class="text-muted">Free RAM (MB)</span>
										</p>
									</div>
									<!-- /.d-flex -->
								</div>
							</div>
						</div>
						<!-- /.col-md-6 -->
					</div>



					<div class="row">
						<div class="col-6">
							<!-- interactive chart -->
							<div class="card card-primary card-outline">
								<div class="card-header">
									<h3 class="card-title">
										<i class="far fa-chart-bar"></i> CPU Utilization
									</h3>

									<div class="card-tools">
										Real time
										<div class="btn-group" id="realtime" data-toggle="btn-toggle">
											<button type="button" class="btn btn-default btn-sm active"
												data-toggle="on">On</button>
											<button type="button" class="btn btn-default btn-sm"
												data-toggle="off">Off</button>
										</div>
									</div>
								</div>
								<div class="card-body">
									<div id="interactive" style="height: 300px;"></div>
								</div>
								<!-- /.card-body-->
							</div>
							<!-- /.card -->

						</div>


						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Node Status Events</h3>
									<div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a> <a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-bars"></i>
										</a>
									</div>
								</div>
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle"
										name="nodeStatusSummary" id="nodeStatusSummary">
										<thead>
											<tr>
												<th>Node IP</th>
												<th>Status</th>
												<th>Date</th>

											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
							</div>
						</div>




						<!-- /.col -->
					</div>



					<!-- /.row -->



					<div class="card-body" style="display: none">
						<div id="area-chart" style="height: 338px;"
							class="full-width-chart"></div>
						<div id="line-chart" style="height: 300px;"></div>
					</div>

				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->



		<!-- Modal -->
		<!-- Modal -->
		<div class="modal fade bd-example-modal-lg" id="dateModal"
			tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Latency Report</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form method="post" action="exportNodeLatency">
						<div class="modal-body">

							<div class="form-group">
								<label>Report Date range:</label>
								<div class="input-group">
									<button type="button" class="btn btn-default float-right"
										id="daterange-btn1" name="daterange-btn1">
										<i class="far fa-calendar-alt"></i> Date range picker <i
											class="fas fa-caret-down"></i>
									</button>
									<input type="text" id='from_date' name='from_date'
										class="form-control" readonly /> <input type="text"
										id='to_date' name='to_date' class="form-control" readonly />
								</div>

							</div>
						</div>
						<div class="modal-footer">
							<!--         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
							<center>
								<button type="submit" class="btn btn-primary" id="exportLatency">Submit</button>
							</center>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="modal fade bd-example-modal-lg" id="dateNodeModal"
			tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Node
							Availability Report</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form method="post" action="nodeAvailabilityReport">
						<div class="modal-body">

							<div class="form-group" id="date_div">
								<label>Report Date range:</label>
								<!-- 								<div class="input-group"> -->
								<!-- 									<button type="button" class="btn btn-default float-right" -->
								<!-- 										id="daterange-btnNode" name="daterange-btnNode"> -->
								<!-- 										<i class="far fa-calendar-alt"></i> Date range picker <i -->
								<!-- 											class="fas fa-caret-down"></i> -->
								<!-- 									</button> -->
								<!-- 									<input type="text" id='from_dateNode' name='from_dateNode' -->
								<!-- 										class="form-control" readonly /> <input type="text" -->
								<!-- 										id='to_dateNode' name='to_dateNode' class="form-control" readonly /> -->
								<!-- 								</div> -->

							</div>
						</div>
						<div class="modal-footer">
							<!--         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
							<center>
								<button type="submit" class="btn btn-primary">Submit</button>
							</center>
						</div>
					</form>
				</div>
			</div>
		</div>

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

	<!-- REQUIRED SCRIPTS -->

	<!-- jQuery -->

	<!-- Bootstrap -->



	<!-- start -->
	<!-- jQuery -->

	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/moment/moment.min.js"></script>
	<!-- jquery-validation -->

	<!-- end -->


	<script
		src="<%=request.getContextPath()%>/webtemplate/datatablesJS/jquery-3.5.1.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.js"></script>

	<!-- OPTIONAL SCRIPTS -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/chart.js/Chart.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/pages/dashboard3.js"></script>



	<!-- DataTables -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net/js/jquery.dataTables.min.js"></script>
	<%-- <script src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/dataTables.bootstrap.min.js"></script> --%>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net/js/dataTables.fixedHeader.js"></script>

	<%-- <script src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/dataTables.editor.min.js"></script> --%>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/dataTables.select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/dataTables.colReorder.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/dataTables.buttons.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/jszip.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/vfs_fonts.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/buttons.html5.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/datatables.net-bs/js/buttons.print.min.js"></script>






	<!-- jQuery -->

	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.min.js"></script>
	<!-- FLOT CHARTS -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/flot/jquery.flot.js"></script>
	<!-- FLOT RESIZE PLUGIN - allows the chart to redraw when the window is resized -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/flot/plugins/jquery.flot.resize.js"></script>
	<!-- FLOT PIE PLUGIN - also used to draw donut charts -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/flot/plugins/jquery.flot.pie.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/webtemplate/dist/js/demo.js"></script>
	<script src="<%=request.getContextPath()%>/webtemplate/js/highstock.js"></script>
	<script src="<%=request.getContextPath()%>/custom_js/nodeDetails.js"></script>

	<%-- 	<script src="<%=request.getContextPath()%>/custom_js/exportReports.js"></script> --%>

	<!-- date-range-picker -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/moment/moment.min.js"></script>



	<%-- <script src="<%=request.getContextPath()%>/custom_js/nodeReport.js"></script> --%>

	<!-- <script type="text/javascript">
		$(function() {

 			var start = moment().subtract(29, 'days');
 			var end = moment();

 			function cb(start, end) {
 				$('#daterange-btn span').html(
 						start.format('MMMM D, YYYY') + ' , '
 								+ end.format('MMMM D, YYYY'));
 				 							start.format('MMMM D, YYYY') + ' - '
 				 									+ end.format('MMMM D, YYYY'));
 			}

 			$('#daterange-btn').daterangepicker(
 					{

 						startDate : start,
 						locale : {
 							format : 'YYYY/MM/DD' // --------Here
 						},
 						endDate : end,

 						ranges : {
 							'Today' : [ moment(), moment() ],
 							'Yesterday' : [ moment().subtract(1, 'days'),
 									moment().subtract(1, 'days') ],
 							'Last 7 Days' : [ moment().subtract(6, 'days'),
 									moment() ],
 							'Last 30 Days' : [ moment().subtract(29, 'days'),
 									moment() ],
 							'This Month' : [ moment().startOf('month'),
 									moment().endOf('month') ],
 							'Last Month' : [
 									moment().subtract(1, 'month').startOf(
 											'month'),
 									moment().subtract(1, 'month')
 											.endOf('month') ]
 						}
					}, cb);

 			cb(start, end);

 		});
	</script> -->
	<!-- Page specific script -->
	<script>
		/*
		 * Custom Label formatter
		 * ----------------------
		 */
		function labelFormatter(label, series) {
			return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
					+ label + '<br>' + Math.round(series.percent) + '%</div>'
		}
	</script>

	<script>
		/* When the user clicks on the button, 
		 toggle between hiding and showing the dropdown content */
		function myFunction() {

			document.getElementById("myDropdown").classList.toggle("show");
		}
		function NodeAvailabilityDropdown() {
			document.getElementById("NodeAvailability").classList
					.toggle("show");
		}

		// Close the dropdown if the user clicks outside of it
		window.onclick = function(event) {
			if (!event.target.matches('.dropbtn')) {
				var dropdowns = document
						.getElementsByClassName("dropdown-content");
				var i;
				for (i = 0; i < dropdowns.length; i++) {
					var openDropdown = dropdowns[i];
					if (openDropdown.classList.contains('show')) {
						openDropdown.classList.remove('show');
					}
				}
			}
		}
	</script>

	<script>
		$('#daterange-btn1').daterangepicker(
				{
					timePicker : true,
					timePickerIncrement : 10,
					ranges : {
						'Today' : [ moment(), moment() ],
						'Yesterday' : [ moment().subtract(1, 'days'),
								moment().subtract(1, 'days') ],
						'Last 7 Days' : [ moment().subtract(6, 'days'),
								moment() ],
						'Last 30 Days' : [ moment().subtract(29, 'days'),
								moment() ],
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

				});

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
		/* 
		function ShowDateDiv() {
			$("#datetime_div").hide();	
			$("#date_div").show();	
		}

		function ShowTimeDiv() {
			$("#datetime_div").show();	
			$("#date_div").hide();	
		}
		 */
		$(function() {

			$('[data-mask]').inputmask()
			// Bootstrap Duallistbox

		})
	</script>


</body>
</html>
