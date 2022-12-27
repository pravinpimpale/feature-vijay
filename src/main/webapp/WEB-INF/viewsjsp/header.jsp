<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NPM | Header</title>


</head>
<body class="hold-transition layout-top-nav">
	<div class="wrapper">

		<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand-md navbar-light navbar-white">
			<div class="container">
				<a href="<%=request.getContextPath()%>/dashboard/summaryPage"
					class="navbar-brand"> <!--  <img
					src="<%=request.getContextPath()%>/webtemplate/dist/img/AdminLTELogo.png"
					alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
					style="opacity: .8"> --> <img height="128" width="128"
					src="<%=request.getContextPath()%>/webtemplate/dist/img/canaris.jpeg"
					class="brand-image""> <!-- <span class="brand-text font-weight-light">NPM</span> -->
					<span class="brand-text font-weight-bold">NPM</span>
				</a>

				<button class="navbar-toggler order-1" type="button"
					data-toggle="collapse" data-target="#navbarCollapse"
					aria-controls="navbarCollapse" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse order-3" id="navbarCollapse">
					<!-- Left navbar links -->
					<ul class="navbar-nav">
						<li class="nav-item"><a
							href="<%=request.getContextPath()%>/dashboard/summaryPage"
							class="nav-link">Home</a></li>
						<li class="nav-item dropdown"><a
							id="dashboardDropdownSubMenu" href="#" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"
							class="nav-link dropdown-toggle">Dashboard</a>
							<ul aria-labelledby="dashboardDropdownSubMenu"
								class="dropdown-menu border-0 shadow">
								<li><a
									href="<%=request.getContextPath()%>/dashboard/summaryPage"
									class="dropdown-item">Home Dashboard</a></li>
								<li class="dropdown-divider"></li>
								<li><a
									href="<%=request.getContextPath()%>/dashboard/topUtilization"
									class="dropdown-item">Top 10</a></li>
								<li class="dropdown-divider"></li>

								<li><a
									href="<%=request.getContextPath()%>/nodeDashboard/dynamicNetworkTopology"
									class="dropdown-item">Network Topology</a></li>
								<li class="dropdown-divider"></li>
								<li><a
									href="<%=request.getContextPath()%>/dashboard/toptalker"
									class="dropdown-item">Top Talker</a></li>

							</ul></li>
						<li class="nav-item dropdown"><a id="adminDropdownSubMenu"
							href="#" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" class="nav-link dropdown-toggle">Admin</a>
							<ul aria-labelledby="adminDropdownSubMenu"
								class="dropdown-menu border-0 shadow">
								<!-- Level two dropdown-->
								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Master</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addUserForm"
											class="dropdown-item">Register User</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/viewUsers"
											class="dropdown-item">View Users</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addIcmpform"
											class="dropdown-item">ICMP Configuration</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/viewIcmp"
											class="dropdown-item">View ICMP Configuration</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/companyMasterform"
											class="dropdown-item">Company Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/viewCompanyMaster"
											class="dropdown-item">View Company</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/locationMasterform"
											class="dropdown-item">Location Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/viewLocationMaster"
											class="dropdown-item">View Location</a></li>


										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addGroupForm"
											class="dropdown-item">Group Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addServiceProviderForm"
											class="dropdown-item">Service Provider Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addMakeModelForm"
											class="dropdown-item">Make & Model Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addDeviceTypeForm"
											class="dropdown-item">Device Type Master</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addSNMPConfigForm"
											class="dropdown-item">SNMP Configuration</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addEmailConfigForm"
											class="dropdown-item">Email Configuration</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/addADConfigForm"
											class="dropdown-item">AD Configuration</a></li>



									</ul></li>

								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Node</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/addNodeForm"
											class="dropdown-item">Add Node</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/viewAddNode"
											class="dropdown-item">View Node</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/addNodeParameterForm"
											class="dropdown-item">Add Node Parameter</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/viewNodeParameter"
											class="dropdown-item">View Node Parameter</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/configBackup"
											class="dropdown-item">Configuration Backup</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/viewconfigBackupdata"
											class="dropdown-item">View Configuration Backup</a></li>
									</ul></li>

								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Network
										Discovery</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/nwIpScanForm"
											class="dropdown-item">IP Scan</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/viewIpScan"
											class="dropdown-item">View IP Scan</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/node/addNodeParameterForm"
											class="dropdown-item">Subnet Scan</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/ScheduleScan"
											class="dropdown-item">Schedule Scan</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/master/discoveredNetwork"
											class="dropdown-item">View Discovered Network</a></li>

									</ul></li>



								<!-- End Level two -->
							</ul></li>



						<li class="nav-item dropdown"><a id="reportDropdownSubMenu"
							href="#" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" class="nav-link dropdown-toggle">Report</a>
							<ul aria-labelledby="adminDropdownSubMenu"
								class="dropdown-menu border-0 shadow">
								<!-- Level two dropdown-->
								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Node
										Report</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/deviceStatus"
											class="dropdown-item">Node Status Report</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/latencyHistoryReportForm"
											class="dropdown-item">Latency History Report</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/latencyThresholdReportForm"
											class="dropdown-item">Latency Threshold Report</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeAvailabilityForm"
											class="dropdown-item">Node Availability</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeHealthHistoryForm"
											class="dropdown-item">Node Health History</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/cpuThresholdHistoryForm"
											class="dropdown-item">CPU Threshold History</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/memoryThresholdHistoryForm"
											class="dropdown-item">Memory Threshold History</a></li>


										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeHealthMonitoringView"
											class="dropdown-item">Node Health Monitoring</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeMonitoringView"
											class="dropdown-item">Node Monitoring</a></li>
											
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/deviceAlertLog"
											class="dropdown-item">Device Alert Log</a></li>
										



									</ul></li>

								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Interface
										Report</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/interfaceStatusReportForm"
											class="dropdown-item">Interface Status Report</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/interfaceBandwidthHistoryForm"
											class="dropdown-item">Interface Bandwidth History</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/bandwidthThresholdHistoryForm"
											class="dropdown-item">Bandwidth Threshold History</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/interfaceCrcHistoryForm"
											class="dropdown-item">Interface CRC History</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/interfaceReport/interfaceAvailabilityForm"
											class="dropdown-item">Interface Availability</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/interfaceMonitoringView"
											class="dropdown-item">Interface Monitoring</a></li>

									</ul></li>

								<!-- End Level two -->
							</ul></li>



						<li class="nav-item dropdown"><a id="reportDropdownSubMenu"
							href="#" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false" class="nav-link dropdown-toggle">Graph</a>
							<ul aria-labelledby="adminDropdownSubMenu"
								class="dropdown-menu border-0 shadow">
								<!-- Level two dropdown-->
								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Node
										Graph</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeHealthHistoryGraphForm"
											class="dropdown-item">Node Health History Graph</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/nodeAvailabilityGraphForm"
											class="dropdown-item">Node Availability Graph</a></li>
										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/latencyHisotryGraphForm"
											class="dropdown-item">Latency Graph</a></li>


									</ul></li>

								<li class="dropdown-submenu dropdown-hover"><a
									id="adminDropdownSubMenu1" href="#" role="button"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="dropdown-item dropdown-toggle">Interface
										Graph</a>
									<ul aria-labelledby="adminDropdownSubMenu1"
										class="dropdown-menu border-0 shadow">

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/interfaceBandwidthHistoryGraphForm"
											class="dropdown-item">Interface Bandwidth History Graph</a></li>

										<li><a tabindex="-1"
											href="<%=request.getContextPath()%>/nodeReport/interfaceAvailabilityGraphForm"
											class="dropdown-item">Interface Availability Graph</a></li>



									</ul></li>

								<!-- End Level two -->
							</ul></li>
					</ul>

					<!-- SEARCH FORM -->
					<!-- <form class="form-inline ml-0 ml-md-3">
						<div class="input-group input-group-sm">
							<input class="form-control form-control-navbar" type="search"
								placeholder="Search" aria-label="Search">
							<div class="input-group-append">
								<button class="btn btn-navbar" type="submit">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</div>
					</form> -->
				</div>

				<!-- Right navbar links -->
				<ul class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
					<!-- Messages Dropdown Menu -->


					<!-- Notifications Dropdown Menu -->
					<!--  	<li class="nav-item dropdown"><a class="nav-link"
						data-toggle="dropdown" href="#"> <i class="far fa-bell"></i> <span
							class="badge badge-warning navbar-badge">15</span>
					</a>
						<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
							<span class="dropdown-header">15 Notifications</span>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item"> <i
								class="fas fa-envelope mr-2"></i> 4 new messages <span
								class="float-right text-muted text-sm">3 mins</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item"> <i
								class="fas fa-users mr-2"></i> 8 friend requests <span
								class="float-right text-muted text-sm">12 hours</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item"> <i
								class="fas fa-file mr-2"></i> 3 new reports <span
								class="float-right text-muted text-sm">2 days</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="#" class="dropdown-item dropdown-footer">See All
								Notifications</a>
						</div></li>
						-->


					<li class="nav-item"><a class="nav-link"
						data-widget="control-sidebar" data-slide="true" href="#"
						role="button"> <i class="fas fa-th-large"></i>
					</a></li>
					<li class="nav-item dropdown"><a id="reportDropdownSubMenu"
						href="#" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false" class="nav-link dropdown-toggle"><i
							class="fa fa-user"></i>&nbsp;&nbsp;NPM</a>
						<ul aria-labelledby="dashboardDropdownSubMenu"
							class="dropdown-menu border-0 shadow">
							<li><a href="<%=request.getContextPath()%>"
								class="dropdown-item">Logout</a></li>

						</ul></li>
				</ul>
			</div>
		</nav>
		<!-- /.navbar -->

	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->


</body>
</html>