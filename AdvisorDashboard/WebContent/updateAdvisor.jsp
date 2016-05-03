<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Advisor Dashboard</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/grayscale.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    
    <!-- jQuery DataTable -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

    <!-- Navigation -->
    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="GetAllAdvisors.do">
                    <i class="fa fa-play-circle"></i>  <span class="light">Back</span> to All Advisors
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
                <ul class="nav navbar-nav">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a href="#"></a>
                    </li>
                   
                    <c:choose>
                    	<c:when test="${empty user}">
		                    <li>
		                        <li class="nav-item"><a class="nav-link page-scroll" href="index.jsp">Login</a></li>
		                    </li>
                    	</c:when>  
                    	<c:otherwise>
                    		<li>
		                        <li class="nav-item"><a class="nav-link page-scroll" href="#">Logged in as ${user.name}</a></li>
		                    </li>
		                    <li>
		                        <li class="nav-item"><a class="nav-link page-scroll" href="Logout.do">Log Out</a></li>
		                    </li>
                    	</c:otherwise>                  
                    </c:choose>
                    
                    
                    <li>
                        <li class="nav-item"><a class="nav-link page-scroll" href="#contact">Connect</a></li>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav>

    <!-- Advisor Section -->
    <section id="advisorInfo" class="container content-section text-center" style="margin-top:-200px;">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
            	<div class="well" style="margin-top:100px;">
            		<div class="row clearfix">
			        	<div class="col-xs-8">
			            	<div class="panel panel-primary">	
			            		<div class="panel-heading" style="background-color:#797c80;color:000000">
			                        <h3 class="panel-title">Advisor Information</h3>
			                    </div>			
			                    <div class="panel-body">
									<form action="UpdateAdvisor.do" method="POST">
	                    			<fieldset>	
	                    				<div class="input-group">
											<input type="text" class="form-control" name="name" value="${advisor.name}" aria-describedby="basic-addon1">
										</div>
										</br>
										<div class="input-group">
											<input type="int" class="form-control" name="salary" value="${advisor.salary}" type="currency" aria-describedby="basic-addon1">
										</div>
										</br>
										<div class="input-group">
											<input type="text" class="form-control" name="password" value="${advisor.password}" aria-describedby="basic-addon1">
										</div>
										</br>
										
										<div class="input-group">
											<label for="sel1">Role</label>
											<select class="form-control" id="sel1" name="position">
											    <c:forEach var="position" items="${positions}">
													<option value="${position.id}">${position.positionName}</option>
												</c:forEach>
											</select>
										</div><!-- /input-group -->
										</br>
										
										<div class="input-group">
											<label for="sel1">Office</label>
											<select class="form-control" id="sel1" name="location">
											    <c:forEach var="location" items="${locations}">
													<option value="${location.id}">${location.address}</option>
												</c:forEach>
											</select>
										</div><!-- /input-group -->
										</br>
	 								</fieldset>
	 								</br>
	 								<input type="hidden" name="id" value="${advisor.id}"/>
 							    	<button class="btn btn-md text-normal btn-primary-outline" type="submit" name="id" value="Update">Update Advisor</button>
									</form>  		
			            		</div>
			            	</div>
			            </div>
			            <div class="col-xs-4">
			            	<div class="panel panel-primary">	
			            		<div class="panel-heading" style="background-color:#797c80;color:000000">
			                        <h3 class="panel-title">${advisor.name}</h3>
			                    </div>
			                    <div class="panel-body">
									<img class="img-responsive" src="${url}"/>
								</div>            			
			            	</div>
			            </div>
			        </div><!-- /.row -->
            	</div>
			</div>
		</div><!-- /.row -->
    </section>

    <!-- Contact Section -->
    <section id="contact" class="container content-section text-center" style="margin-top:-100px;">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
            	<p>Copyright &copy; <a href="http://www.chrisriouxdevelopment.com" target="_blank">Chris Rioux Development</a> 2016</p>                
            </div>
        </div>
    </section>
    

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="js/jquery.easing.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/grayscale.js"></script>
    
</body>

</html>