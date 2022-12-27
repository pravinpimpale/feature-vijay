<!DOCTYPE html>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%> --%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Schedule Scan</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/static/resources/icon/favicon.ico">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/Ionicons/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/dist3/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/dist3/css/skins/_all-skins.min.css">
<!-- Morris chart -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/morris.js/morris.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/jvectormap/jquery-jvectormap.css">
<!-- Date Picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/bootstrap-daterangepicker/daterangepicker.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/plugins3/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<!-- DataTables -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/css/dataTables.bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/css/fixedHeader.dataTables.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/css/jquery.dataTables.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/css/select.dataTables.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/css/buttons.dataTables.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/css/colReorder.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/bower_components3/bootstrap-duallistbox/src/bootstrap-duallistbox.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/plugins/toastr/toastr.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/resources1/bowercomponents/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">


<style>
.form-control-feedback {
	right: 25px !important;
}

#container {
	border: 1px solid black;
	position: relative;
}
.help-block{
color: #dd4b39;
}
.form-control-feedback{
color: #dd4b39;
}

#label {
	position: absolute;
	top: -10px;
	left: 20px;
	height: 20px;
	color: white;
	background-color: #1e282c;
	text-align: center;
	background-color: #1e282c;
}
</style>

</head>

<body class="hold-transition fixed skin-blue sidebar-mini">
	<div class="wrapper">
		<%-- <jsp:include page="/WEB-INF/views/dashboardheaderAdmin.jsp"></jsp:include>

		<jsp:include page="/WEB-INF/views/dashboardsidebar.jsp"></jsp:include> --%>


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<section class="content-header">

				
				<div style="display:flex; justify-content:space-between; align-items:baseline;">
					<h2 class="heading" style="width: 193px"> Schedule Scan </h2>
<!-- 					<span class="glyphicon glyphicon-backward" onclick="toggleinverse()"></span> -->
					</div>
					<div style="width: 100%">
					
						<br>
						<div class="row">
							<div class="col-md-1"></div>
							<div class="col-md-11">
								<input type="checkbox" id="scheduler" name="scheduler" value="false">
								<label>&nbsp;&nbsp;<b>Enable Scheduled Scan</b></label><br>
								<br>
							</div>
						</div>
						<div class="row">
							<div class="col-md-1"></div>
							<div class="col-md-10">
								<div class="col-md-3">
									<input type="radio" id="once" name="type" value="once"
										checked="true" onclick="togglediv(this.value)"><label>&nbsp;&nbsp;Scan
										Once</label><br> <input type="radio" id="daily" name="type"
										value="daily" onclick="togglediv(this.value)"><label>&nbsp;&nbsp;Daily
										Scan</label><br> <input type="radio" id="weekly" name="type"
										value="weekly" onclick="togglediv(this.value)"><label>&nbsp;&nbsp;Weekly
										Scan</label><br> <input type="radio" id="monthly" name="type"
										value="monthly" onclick="togglediv(this.value)"><label>&nbsp;&nbsp;Monthly
										Scan</label><br> <input type="radio" id="periodic" name="type"
										value="periodic" onclick="togglediv(this.value)"><label>&nbsp;&nbsp;Periodic
										Scan</label><br>
								</div>
								<div class="col-md-9">
									<div class="row" id="once_div">
										<div class="row">
											<label><b>Scan for workstation</b></label>
										</div>
										<br>
										<form id="scan_once_form">
										<div id="container">
											<div id="label">&nbsp;&nbsp;On&nbsp;&nbsp;</div>
											<div class="row">
												<div class="col-md-1"></div>
												<div class="col-md-12">
													<br>
													<div class="col-md-2">
														<label>Date & Time</label>
													</div>
													<div class="col-md-8">
														<div class="input-group date">
															<div class="input-group-addon">
																<i class="fa fa-calendar"></i>
															</div>
															<input type="text" class="form-control pull-right"
																id="Once_Date" name="Once_Date" readonly required>
														</div>
													</div>
													<div class="col-md-1"></div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-1"></div>
												<div class="col-md-12">
													<br>
													<div class="col-md-2">
														<label>Time</label>
													</div>
													<div class="col-md-8">
														<div class="input-group date">

															<input type="time" id="once_time" name="once_time"
																min="00:00" max="23:59" required>
														</div>
													</div>
													<div class="col-md-1"></div>
												</div>
											</div>
											<br> <br>

										</div>
										</form>
									</div>
									<form id="daily_form">
									<div class="row" id="daily_div" style="display: none">
										<div class="row">
											<label><b>Daily Scan for workstations :</b></label>
										</div>
										<br>
										<div id="container">
											<div id="label">&nbsp;&nbsp;Daily At&nbsp;&nbsp;</div>
											<div class="row">
												<div class="col-md-1"></div>
												<div class="col-md-12">
													<br>
													<div class="col-md-2">
														<label>From</label>
													</div>
													<div class="col-md-8">
														<div class="input-group date">
															<div class="input-group-addon">
																<i class="fa fa-calendar"></i>
															</div>
															<input type="text" class="form-control pull-right"
																id="Daily_Date" name="Daily_Date" readonly required>
														</div>
													</div>
													<div class="col-md-1"></div>
												</div>
											</div>

											<div class="row">
												<div class="col-md-1"></div>
												<div class="col-md-12">
													<br>
													<div class="col-md-2">
														<label>Time</label>
													</div>
													<div class="col-md-8">
														<div class="input-group date">

															<input type="time" id="daily_time" name="daily_time" min="00:00"
																max="23:59" required>
														</div>
													</div>
													<div class="col-md-1"></div>
												</div>
											</div>
											<br>
										</div>
									</div>
									</form>
									
									<form id="week_form">
									<div class="row" id="weekly_div" style="display: none">
										<div class="row">
											<label><b>Weekly Scan for workstations :</b></label>
										</div>
										<br>
										<div id="container">
											<div id="label">
												&nbsp;&nbsp;<input type="checkbox" id="everyday"
													name="everyday"  class="everyday" onclick="CheckAll()">&nbsp;&nbsp;EveryDay&nbsp;&nbsp;
											</div>
											<div class="row">
												<div class="col-md-1"></div>
												<div class="col-md-11">
													<br>
													<div class="col-md-12">
														<input type="checkbox" id="sunday"
															class="weekdays_checkbox" name="weekdays_checkbox" value="sunday">&nbsp;&nbsp;<label>Sunday</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="monday"
															class="weekdays_checkbox" name="weekdays_checkbox" value="monday">&nbsp;&nbsp;<label>Monday</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="tuesday"
															class="weekdays_checkbox" name="weekdays_checkbox" value="Tuesday">&nbsp;&nbsp;<label>Tuesday</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="wednesday"
															class="weekdays_checkbox" name="weekdays_checkbox" value="Wednesday">&nbsp;&nbsp;<label>Wednesday</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<br> <input type="checkbox" id="thursday"
															class="weekdays_checkbox" name="weekdays_checkbox" value="Thursday">&nbsp;&nbsp;<label>Thursday</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="friday"
															class="weekdays_checkbox" name="weekdays_checkbox" value="Friday">&nbsp;&nbsp;<label>Friday</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="saturday"
															class="weekdays_checkbox" name="weekdays_checkbox" value="Saturday">&nbsp;&nbsp;<label>Saturday</label>&nbsp;&nbsp;&nbsp;&nbsp;

													</div>

												</div>
											</div>

											<div class="row">
												<div class="col-md-1"></div>
												<div class="col-md-11">
													<br>
													<div class="col-md-2">
														<label>Time</label>
													</div>
													<div class="col-md-8">
														<div class="input-group date">

															<input type="time" id="week_time" name="week_time" min="00:00"
																max="23:59" required>
														</div>
													</div>
													<div class="col-md-1"></div>
												</div>
											</div>
											<br>
										</div>
									</div>
									</form>
									
									
									<form id="monthly_form">
									<div class="row" id="monthly_div" style="display: none">
										<div class="row">
											<label><b>Monthly Scan for workstations :</b></label>
										</div>
										<br>
										<div id="container">
											<div id="label">
												&nbsp;&nbsp;<input type="checkbox" id="everymonth"
													name="everymonth" class="everymonth" onclick="CheckAllMonth()"">&nbsp;&nbsp;Every
												Month&nbsp;&nbsp;
											</div>
											<div class="row">
												<div class="col-md-1"></div>
												<div class="col-md-11">
													<br>
													<div class="col-md-12">
														<input type="checkbox" id="January"
															class="month_checkbox" name="month_checkbox" value="January" onclick="CheckAllMonth()">&nbsp;&nbsp;<label>January</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="February"
															class="month_checkbox" name="month_checkbox" value="February" onclick="CheckAllMonth()">&nbsp;&nbsp;<label>February</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="March"
															class="month_checkbox" name="month_checkbox" value="March" onclick="CheckAllMonth()">&nbsp;&nbsp;<label>March</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="April"
															class="month_checkbox" name="month_checkbox" value="April" onclick="CheckAllMonth()">&nbsp;&nbsp;<label>April</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<br> <input type="checkbox" id="May"
															class="month_checkbox" name="month_checkbox" value="May" onclick="CheckAllMonth()">&nbsp;&nbsp;<label>May</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="June"
															class="month_checkbox" name="month_checkbox" value="June" onclick="CheckAllMonth()">&nbsp;&nbsp;<label>June</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="July"
															class="month_checkbox" name="month_checkbox" value="July" onclick="CheckAllMonth()">&nbsp;&nbsp;<label>July</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="August"
															class="month_checkbox" name="month_checkbox" value="August" onclick="CheckAllMonth()">&nbsp;&nbsp;<label>August</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<br> <input type="checkbox" id="September"
															class="month_checkbox" name="month_checkbox" value="September" onclick="CheckAllMonth()">&nbsp;&nbsp;<label>September</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="October"
															class="month_checkbox" name="month_checkbox" value="October"  onclick="CheckAllMonth()">&nbsp;&nbsp;<label>October</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="November"
															class="month_checkbox" name="month_checkbox" value="November" onclick="CheckAllMonth()">&nbsp;&nbsp;<label>November</label>&nbsp;&nbsp;&nbsp;&nbsp;
														<input type="checkbox" id="December"
															class="month_checkbox" name="month_checkbox" value="December" onclick="CheckAllMonth()">&nbsp;&nbsp;<label>December</label>&nbsp;&nbsp;&nbsp;&nbsp;

													</div>

												</div>
											</div>

											<br>
										</div>
										<br>
										<div id="container">
											<div id="label">&nbsp;&nbsp;On&nbsp;&nbsp;</div>
											<div class="row">
												<div class="col-md-1"></div>
												<div class="col-md-11">
													<br>
													<div class="col-md-12">
														<input type="radio" class="monththly_scan_on" name="radiotype"><label>&nbsp;&nbsp;Day&nbsp;&nbsp;</label>&nbsp;&nbsp;
														<select id="monthly_scan_day">
															<option value="Sunday">Sunday</option>
															<option value="Monday">Monday</option>
															<option value="Tuesday">Tuesday</option>
															<option value="Wednesday">Wednesday</option>
															<option value="Thursday">Thursday</option>
															<option value="Friday">Friday</option>
															<option value="Saturday">Saturday</option>
														</select> <br> <input type="radio" class="monththly_scan_on" name="radiotype"><label>&nbsp;&nbsp;Date&nbsp;&nbsp;</label>&nbsp;&nbsp;
														<select id="monthly_scan_date">
															<option value="1">1</option>
															<option value="2">2</option>
															<option value="3">3</option>
															<option value="4">4</option>
															<option value="5">5</option>
															<option value="6">6</option>
															<option value="7">7</option>
															<option value="8">8</option>
															<option value="9">9</option>
															<option value="10">10</option>
															<option value="11">11</option>
															<option value="12">12</option>
															<option value="13">13</option>
															<option value="14">14</option>
															<option value="15">15</option>
															<option value="16">16</option>
															<option value="17">17</option>
															<option value="18">18</option>
															<option value="19">19</option>
															<option value="20">20</option>
															<option value="21">21</option>
															<option value="22">22</option>
															<option value="23">23</option>
															<option value="24">24</option>
															<option value="25">25</option>
															<option value="26">26</option>
															<option value="27">27</option>
															<option value="28">28</option>
															<option value="29">29</option>
															<option value="30">30</option>
															<option value="31">31</option>

														</select>
													</div>

												</div>
											</div>

											<div class="row">
												<div class="col-md-1"></div>
												<div class="col-md-11">
													<br>
													<div class="col-md-2">
														<label>Time</label>
													</div>
													<div class="col-md-8">
														<div class="input-group date">

															<input type="time" id="month_time" name="month_time" min="00:00"
 																max="23:59" required>
														</div>
													</div>
													<div class="col-md-1"></div>
												</div>
											</div>
											<br>
										</div>
									</div>
									</form>
									
									<form id="periodic_form">
									<div class="row" id="periodic_div" style="display: none">
										<div class="row">
											<label><b>Periodic Scan for workstations :</b></label>
										</div>
										<br>
										<div id="container">
											<div class="row">
												<div class="col-md-1"></div>
												<div class="col-md-11">
													<br> <span>After every&nbsp;&nbsp;<input
														type="text" name="text" size="1" id="periodic_days" maxlength="3"
														value="7" />&nbsp;&nbsp;Days
													</span>
												</div>
											</div>
											<br>
										</div>


									</div>
									</form>

								</div>
							</div>
							<div class="col-md-1"></div>
						</div>

						<br>
						<div class="row">
							<div class="col-md-1"></div>
							<div class="col-md-11">
								<input type="checkbox" id="scan_history" name="scan_history" value="false">
								<label><b>&nbsp;&nbsp;Enable Scan History Clean Up</b></label><br>
								<br> <span>Clean up Scan History older
									than&nbsp;&nbsp;<input type="text" size="1"
									id="scan_history_days" maxlength="3" value="30" />&nbsp;&nbsp;Days
								</span>


							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-1"></div>
							<div class="col-md-11">
								<input type="checkbox" id="scan_history_work" name="scan_history_work" value="false">
								<label><b>&nbsp;&nbsp;Check for newly added
										workstations</b></label><br> <br> <span>Every&nbsp;&nbsp;<input
									type="text" size="1" id="scan_history_work_days" maxlength="3"
									value="7" />&nbsp;&nbsp;Days
								</span>


							</div>
						</div>
						<br>
						<div class="row" id="range_network">
						<div class="col-md-1"></div>
						<div class="col-md-10">
							<div class="row">

								<div class="col-md-2">
									<label>From IP Address</label>
								</div>
								<div class="col-md-9">
									<input type="text" size="1" id="class5" class="inputIP"
										maxlength="3" /><span class="ipspan">&nbsp;&nbsp;&#x2022;&nbsp;</span>

									<input type="text" size="1" id="class6" class="inputIP"
										maxlength="3"><span class="ipspan">&nbsp;&nbsp;&#x2022;&nbsp;</span>

									<input type="text" size="1" id="class7" class="inputIP"
										maxlength="3"><span class="ipspan">&nbsp;&nbsp;&#x2022;&nbsp;</span>

									<input type="text" size="1" id="class8" class="inputIP"
										maxlength="3"> &nbsp;&nbsp;<label>To IP Address</label>&nbsp;&nbsp;
									<input type="text" size="1" id="class9" class="subnetmaskscan"
										maxlength="3" /><span class="subnetmaskspan">&nbsp;&nbsp;&#x2022;&nbsp;</span>

									<input type="text" size="1" id="class10" class="subnetmaskscan"
										maxlength="3"><span class="subnetmaskspan">&nbsp;&nbsp;&#x2022;&nbsp;</span>

									<input type="text" size="1" id="class11" class="subnetmaskscan"
										maxlength="3"><span class="subnetmaskspan">&nbsp;&nbsp;&#x2022;&nbsp;</span>

									<input type="text" size="1" id="class12" class="subnetmaskscan"
										maxlength="3">
								</div>
							</div>



						</div>

					</div>
					<br>
						<div class="row">
							<center>
								<button tabindex="8" class="btn btn-primary" id="submit-btn"
									name="submit-btn" data-style="expand-left">
									Submit <span class="spinner-border"
										style="width: 1rem; height: 1rem; display: none;" id="spin"></span>
								</button>
								&nbsp;&nbsp;
								<button tabindex="8" class="btn btn-default" id="reset-btn"
									name="submit-btn" data-style="expand-left">
									Reset 
								</button>
							</center><br><br>
						</div>
					</div>

				</form>

			</section>


		</div>

		<!-- Main Footer -->
		<%-- <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include> --%>
	</div>
	<!-- ./wrapper -->

	<!-- jQuery 3 -->
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/jquery/dist/jquery-3.5.1.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/jquery-ui/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- daterangepicker -->
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/moment/min/moment.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/bootstrap-daterangepicker/daterangepicker.js"></script>
	<!-- datepicker -->
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
	<!-- Bootstrap WYSIHTML5 -->
	<script
		src="<%=request.getContextPath()%>/static/plugins3/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<!-- Slimscroll -->
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/fastclick/lib/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/static/dist3/js/adminlte.min.js"></script>

	<!-- DataTables -->
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/datatables.net/js/jquery.dataTables.min.js"></script>
	<%-- <script src="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/js/dataTables.bootstrap.min.js"></script> --%>
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/datatables.net/js/dataTables.fixedHeader.js"></script>

	<%-- <script src="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/js/dataTables.editor.min.js"></script> --%>
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/js/dataTables.select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/js/dataTables.colReorder.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/js/dataTables.buttons.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/js/jszip.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/js/pdfmake.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/js/vfs_fonts.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/js/buttons.html5.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/datatables.net-bs/js/buttons.print.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/static/bower_components3/bootstrap-duallistbox/src/jquery.bootstrap-duallistbox.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/bower_components3/bootstrap-validator/js/bootstrapValidator.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/plugins/sweetalert2/sweetalert2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/plugins/toastr/toastr.min.js"></script>

	<script
		src="<%=request.getContextPath()%>/static/resources1/bowercomponents/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
	
	<script
		src="<%=request.getContextPath()%>/static/custom/ViewSchedule.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#entire').checked = true;
		});

		
	</script>

</body>
</html>
