<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>NMS | Log in</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/webtemplate/plugins/fontawesome-free/css/all.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/webtemplate/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/webtemplate/dist/css/adminlte.min.css">
  
   <link rel="stylesheet"
	href="<%=request.getContextPath()%>/webtemplate/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <!-- /.login-logo -->
  <div class="card card-outline card-primary">
    <div class="card-header text-center">
    <!--   <a href="../../index2.html" class="h1"><b>NMS</b></a>-->
    <img class="animation__wobble"   height="110" width="150"
				src="<%=request.getContextPath()%>/webtemplate/dist/img/canaris.jpeg"> 
    </div>
    <div class="card-body">
      <p class="login-box-msg">Sign in to start your session</p>

      <form class="pressEnter">
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="User Name" id="username">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" class="form-control" placeholder="Password" id="password">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="row">
         <!--   <div class="col-8">
            <div class="icheck-primary">
              <input type="checkbox" id="remember">
              <label for="remember">
                Remember Me
              </label>
            </div>
          </div> -->
          <!-- /.col -->
          <div class="col-4">
            <button type="button" onclick="checkDBChreds()" class="btn btn-primary btn-block" id="login-button">Sign In</button>
          </div>
          <!-- /.col -->
        </div>
       </form> 

<!--       <div class="social-auth-links text-center mt-2 mb-3"> -->
<!--         <a href="#" class="btn btn-block btn-primary"> -->
<!--           <i class="fab fa-facebook mr-2"></i> Sign in using Facebook -->
<!--         </a> -->
<!--         <a href="#" class="btn btn-block btn-danger"> -->
<!--           <i class="fab fa-google-plus mr-2"></i> Sign in using Google+ -->
<!--         </a> -->
<!--       </div> -->
      <!-- /.social-auth-links -->

      <!-- <p class="mb-1">
        <a href="forgot-password.html">I forgot my password</a>
      </p> -->
<!--       <p class="mb-0"> -->
<!--         <a href="register.html" class="text-center">Register a new membership</a> -->
<!--       </p> -->
    </div>
    <!-- /.card-body -->
  </div>
  <!-- /.card -->
</div>
<!-- /.login-box -->

<!-- jQuery -->
<script src="<%=request.getContextPath()%>/webtemplate/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath()%>/webtemplate/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath()%>/webtemplate/dist/js/adminlte.min.js"></script>
<script
		src="<%=request.getContextPath()%>/webtemplate/sweetalert2/sweetalert2.min.js"></script>
<script src="<%=request.getContextPath()%>/custom_js/login.js"></script> 
</body>
</html>
