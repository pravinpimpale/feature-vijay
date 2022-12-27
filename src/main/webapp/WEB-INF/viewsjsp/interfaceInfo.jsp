<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NPM | Interface Info</title>

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
		<input type="hidden" name="intName" id="intName"
		value='<%=request.getParameter("intName")%>'>

	<div class="wrapper">

		<!-- Navbar -->
		<jsp:include page="header.jsp" />
		<!-- /.navbar -->



		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h4 class="m-0">Interface Details of <%=request.getParameter("nodeIP")%> ~ <%=request.getParameter("intName")%></h4>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Interface Details</li>
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
						<!-- .col-md-12 -->

						<div class="col-lg-6">


							<div class="card"">
								<div class="card-header border-0">
									<h3 class="card-title">Interface Statistics</h3>
								</div>
								<div class="card-body" style="height: 742px;">
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">IP Address</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="nodeIPID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Name</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="intNameID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Alias Name</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="aliasID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Type</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="intTypeID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Specific</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="intSpcID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3"
										id="admindivid">
										<p class="text-normal text-medium">Admin Status</p>
										<p class="d-flex flex-column text-right">

											<span class="text-success mr-1" id="adminStatusID"> <i
												class="fas fa-arrow-up"></i> NA
											</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3"
										id="operdivid">
										<p class="text-normal text-medium">Operational Status</p>
										<p class="d-flex flex-column text-right">
											<span class="text-success mr-1" id="operStatusID"> <i
												class="fas fa-arrow-up"></i> NA
											</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface IP Address</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="intIPID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface MAC Address</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="intMACID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">MTU</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="mtuID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">CRC Count</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="crcID">NA</span>
										</p>
									</div>
									<!-- /.d-flex -->
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Interface Bandwidth</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="procBwID">NA</span>
										</p>
									</div>
									<div
										class="d-flex justify-content-between align-items-center border-bottom mb-3">
										<p class="text-normal text-medium">Current Throughput</p>
										<p class="d-flex flex-column text-right">
											<span class="text-muted" id="throughputID">NA</span>
										</p>
									</div>

								</div>
							</div>
						</div>

						<!-- /.col-md-12 -->
						<div class="col-lg-6">
							<!-- interactive chart -->
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Real Time Bandwidth (Kbps)</h3>
										<div class="card-tools">
											<a href="#" class="btn btn-tool btn-sm"> <i
												class="fas fa-download"></i>
											</a>
										</div>
									</div>


								</div>
								<div class="card-body">


									<div class="position-relative mb-4">
										<div id="container_bandwidth"
											style="height: 400px; min-width: 310px"></div>
									</div>

									<div class="d-flex flex-row justify-content-end">
										<span class="mr-2"> <i
											class="fas fa-square text-primary"></i> In
										</span> <span> <i class="fas fa-square text-gray"></i> Out
										</span>
									</div>
								</div>
							</div>
							<!-- /.card -->
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Interface Uptime</h3>
										<div class="card-tools">
											<a href="#" class="btn btn-tool btn-sm"> <i
												class="fas fa-download"></i>
											</a>
										</div>
									</div>
								</div>
								<div class="card-body">
									<div class="d-flex">
										<p class="d-flex flex-column">
											<span>Uptime Over Time</span>
										</p>
										<p class="ml-auto d-flex flex-column text-right">
											<span class="text-muted">Date Wise (Avg %)</span>
										</p>
									</div>
									<!-- /.d-flex -->

									<div class="position-relative mb-4">
										<canvas id="sales-chart" height="200"></canvas>
									</div>

									<div class="d-flex flex-row justify-content-end">
										<span class="mr-2"> <i class="fas fa-square text-gray"></i>
											Uptime
										</span> <span class="mr-2"> <i
											class="fas fa-square text-primary"></i> Downtime
										</span>
									</div>
								</div>
							</div>
							<!-- /.card -->

						</div>
						<!-- /.col-md-6 -->
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Last 5 Events of Interface</h3>
									<div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a>
									</div>
								</div>
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle"
										id="interfaceSummary">
										<thead>
											<tr>
												<th>Date & Time</th>
												<th>Interface Name</th>
												<th>Activity</th>
												<th>Interface Status</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col-md-6 -->
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header border-0">
									<h3 class="card-title">Last 5 Events of CRC Log</h3>
									<div class="card-tools">
										<a href="#" class="btn btn-tool btn-sm"> <i
											class="fas fa-download"></i>
										</a>
									</div>
								</div>
								<div class="card-body table-responsive p-0">
									<table class="table table-striped table-valign-middle"
										id="crcLogID">
										<thead>
											<tr>
												<th>Date & Time</th>
												<th>Interface Name</th>
												<th>CRC Before</th>
												<th>CRC Current</th>

											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- /.col-md-6 -->


					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
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

	<!-- REQUIRED SCRIPTS -->

	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap -->
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
	<script src="<%=request.getContextPath()%>/webtemplate/js/highstock.js"></script>
	<!-- Page specific script -->
	<script src="<%=request.getContextPath()%>/custom_js/interfaceInfo.js"></script>
</body>
</html>
