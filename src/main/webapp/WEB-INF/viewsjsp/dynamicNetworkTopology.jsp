<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Dashboard 3</title>

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


<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />


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

    <style type="text/css">
        .footer_logo_right pull-right{
            margin-top: -33px;
        }
        body {
            font: 10pt arial;
            padding: 0;
        }

        ul {
            list-style-type: none;
        }



        #mynetwork {
            width:  1330px;
            height: 720px;
            border: 2px solid cyan;
            background-color:#333333;

            margin-left: -10px;
            margin-top: 10px;
        }#items{
            list-style:none;
            margin:0px;
            margin-top:4px;
            padding-left:10px;
            padding-right:10px;
            padding-bottom:3px;
            font-size:17px;
            color: #333333;

        }
        hr { width: 85%;
             background-color:#E4E4E4;
             border-color:#E4E4E4;
             color:#E4E4E4;
        }
        #cntnr{
            display:none;
            position:fixed;
            border:1px solid #B2B2B2;
            /*                width:150px;   */
            background:#F9F9F9;
            box-shadow: 3px 3px 2px #E9E9E9;
            border-radius:4px;
        }




        li{
            padding: 3px;
            padding-left:10px;
            cursor: default;
        }

        li:hover{
            background: #0DB0E7;
        }

        select{
            cursor: pointer;

        }
        input [button] {
            cursor: pointer;
        }

        .slidebar {
            width: 188px;
            min-height: 00px;
            float: left;

            margin: 0 0 0 -180px;
            border-right: 1px solid rgb(235,235,235);
            /*                background-color: rgb(247,247,247);*/
            background: none repeat scroll 0 0 #eee;
        }
        .floating-box {
            float: left;
            width: 68%;
            height: 100%;
            margin: 00%;
            /*border: 3px solid #73AD21;*/
        }
        .floating-box1 {
            float: left;
            width: 16%;
            height: 100%;
            margin: 00%;
            /*border: 3px solid #73AD21;*/
        }
        .after-box1 {
            clear: left;
            border-left: 2px solid #00CCFF;
            border-right: 2px solid #00CCFF;
            border-top: 2px solid #00CCFF;
            /*border-bottom: 2px solid brown;*/
            /*border: 3px solid red;*/
        }

        .after-box {
            clear: left;

            /*border-bottom: 2px solid brown;*/
            /*border: 3px solid red;*/
        }

        .after-box2 {
            clear: left;

            /*border-bottom: 2px solid brown;*/
            /*border: 3px solid red;*/
        }

        #dashboard>div {
            border: 1px solid rgb(235,235,235);
            margin-left: 30px;
            float: left;
            border-radius: 5px;
            min-width: 345px;
            height: 262px;
            display: inline-block;
            /*               background:  url("images/02.gif") 0 0 repeat;*/
        }
        .swal-wide{
            width:300px !important;
            height: 100px !important;
        }





    </style>
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
<body class="hold-transition layout-top-nav" onload="auto_refresh1">
	<div class="wrapper">

		<jsp:include page="header.jsp" />
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Node Details 192.168.0.230</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Node Details
									192.168.0.230</li>
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
						<div class="col-lg-12">
							<div class="card">
								<div class="card-header border-0">
									<div class="d-flex justify-content-between">
										<h3 class="card-title">Node Latency</h3>

									</div>


								</div>
								<div class="card-body">


									<div id="templatemo_container">



										<br> <input type="box" readonly
											style="width: 10px; height: 10px; background-color: red">
										<span class="tooltiptext"
											style="overflow: auto; height: 350px; width: inherit;">Down</span>
										<input type="box" readonly
											style="width: 10px; height: 10px; background-color: #5FBF00">
										<span class="tooltiptext"
											style="overflow: auto; height: 350px; width: inherit;">Up</span>
										<input type="box" readonly
											style="width: 10px; height: 10px; background-color: #FFFF00">
										<span class="tooltiptext"
											style="overflow: auto; height: 350px; width: inherit;">Software
											Alert</span> <input type="box" readonly
											style="width: 10px; height: 10px; background-color: #FFA500">
										<span class="tooltiptext"
											style="overflow: auto; height: 350px; width: inherit;">Hardware
											Alert</span> <span> 
											
<!-- 											<input type="button" value="Edit View" onclick="location.href = 'DynamicNetworkTopologyEditTest.jsp'"> -->
										<input type="button" value="Edit View" onclick="editTopologyPageCall()">
									
										
										</span> <br>




										<div class="floating-box">
											<div class="admin-panel clearfix">




												<div id="mynetwork"></div>
												<div id="forRighClick">
													<span id="op"></span>

													<div id="cntnr"></div>

												</div>
											</div>
										</div>


										<br> <br> <br>
										<div class="after-box">
											<div
												style="background-color: whitesmoke; margin-left: 10px; height: 20% !important">

												<!--<div align="right">-->
												<footer class="footer">
													<div class="container-fluid">
														<div class="row">
															<div class="col-md-6">
																<div class="footer_logo_left">
																	<img
																		src="<%=request.getContextPath()%>/img/veloxlogo.png"
																		alt="" height="50px" width="152px">
																</div>
															</div>
															

														</div>
													</div>
												</footer>
												<%--<jsp:include page="footer.jsp"></jsp:include>--%>

												<!--</div>-->
											</div>

										</div>


									</div>
								</div>
							</div>
							<!-- /.card -->


						</div>

						<!-- /.col-md-6 -->
					</div>






				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->





		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->

		<!-- Main Footer -->
		<footer class="main-footer">
			<strong>Copyright &copy; 2014-2021 <a
				href="https://adminlte.io">AdminLTE.io</a>.
			</strong> All rights reserved.
			<div class="float-right d-none d-sm-inline-block">
				<b>Version</b> 3.1.0
			</div>
		</footer>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->

	<!-- jQuery -->

	<!-- Bootstrap -->
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

	<!-- date-range-picker -->
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="<%=request.getContextPath()%>/webtemplate/plugins/moment/moment.min.js"></script>


	<script
		src="<%=request.getContextPath()%>/custom_js/dynamicNetworkTopology.js"></script>
</body>
</html>
