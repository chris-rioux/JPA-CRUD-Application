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
    <section id="advisorInfo" class="container content-section text-center" style="margin-top:-175px;">
        <div class="row">
            <div class="col-md-6">
            	<div class="well" style="margin-top:100px;">
            		<div class="row clearfix">
			        	<div class="col-xs-8">
			            	<div class="panel panel-primary">	
			            		<div class="panel-heading" style="background-color:#797c80;color:000000">
			                        <h3 class="panel-title">Advisor Information</h3>
			                    </div>			
			                    <div class="panel-body">
									<ul style="text-align:left; color:#797c80;">
										<li>Advisor ID: <a href="GoToUpdateAdvisor.do?id=${advisor.id}">${advisor.id}</a></li>
										<li>Name: ${advisor.name}</li>
										<li>Salary: <fmt:formatNumber value="${advisor.salary}" type="currency"></fmt:formatNumber></li>
										<li>Role: ${advisor.position.positionName}</li>
										<li>Location: ${advisor.location.city}, ${advisor.location.country}</li>					
									</ul>            		
			            		</div>
			            	</div>
			            </div>
			            <div class="col-xs-4">
			            	<div class="panel panel-primary">	
			            		<div class="panel-heading" style="background-color:#797c80;color:000000">
			                        <h3 class="panel-title">Options</h3>
			                    </div>
			                    <div class="panel-body">
									<form action="GoToUpdateAdvisor.do?id=${advisor.id}" method="GET">
										<input type="hidden" name="id" value="${advisor.id}"/>
							        	<button class="btn btn-md text-normal btn-primary-outline" type="submit" name="id" value="Update">Update</button>
									</form>
									</br>
							        <form action="DeleteAdvisor.do?id=${advisor.id}" method="POST">
										<input type="hidden" name="id" value="${advisor.id}"/>
										<button class="btn btn-md text-normal btn-danger" type="submit" name="id" value="Delete">Delete</button>
									</form>
								</div>            			
			            	</div>
			            </div>
			        </div><!-- /.row -->
            	</div>
			</div>
			
			<div class="col-md-3" id="charts" style="margin-top:55px;">
				<!-- placeholderCharts.js <canvas id="DoughnutChart" width="200" height="300"></canvas> -->
				<canvas id="advisorDoughnutChart" width="200" height="300"></canvas>
			</div>
			<div class="col-md-3" id="charts" style="margin-top:55px;">
				<canvas id="advisorBarChart" width="200" height="300"></canvas>
				<!-- placeholderCharts.js <canvas id="advisorBarChart" width="200" height="300"></canvas> -->
			</div>
        </div><!-- /.row -->
        
        <div class="row">
        	<div class="col-md-8 col-md-offset-2" style="margin-top:25px;">
        		<canvas id="advisorLineChart" width="600" height="300"></canvas>
        		<!-- placeholderCharts.js <canvas id="advisorLineChart" width="600" height="300"></canvas> -->
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
    
    <!-- jQuery DataTable -->
	<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>

	<script>
    	$(document).ready(function() {
        	$('#table').DataTable();
    	} );
    </script>
    
    <!-- ChartJS -->
	<script src="js/Chart.js-master/Chart.js"></script>
	<!-- <script src="js/placeholderCharts.js"></script> -->
	    
	<script>
	// universal color code randomizer
	function randomColor() {return "#000000".replace(/0/g,function(){return (~~(Math.random()*16)).toString(16);});}
	
	// advisor sales by fund doughnut chart
	var ctx = document.getElementById("advisorDoughnutChart").getContext("2d");
		
	var advisorFundSalesData = [ 
	            		<c:forEach var="value" items="${advisorFundSales}"> 
	            			<c:if test="${!empty value[0]}">
	            				{
	            					value : ${value[1]},
	            					color : randomColor(),
	            					highlight : randomColor(),
	            					label : "${value[0]}"
	            				}, 
	            			</c:if>
	            		</c:forEach>
	            		];
	var options = {
		tooltipTemplate: 
			function(label){return  '$' + label.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");},
				animation: false,
		scaleLabel:
			function(label){return  '$' + label.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");}
	};
	
	var advisorDoughnutChart = new Chart(ctx).Doughnut(advisorFundSalesData, options);

	// advisor sales by year bar chart
	var ctx = document.getElementById("advisorBarChart").getContext("2d");
	
	var advisorYearSalesData = {
			labels: [<c:forEach var="value" items="${advisorYearSales}">"${value[0]}", </c:forEach>],
			datasets: [
				{
					label: "Year Sales Dataset",
					fillColor: randomColor(),
            		strokeColor: randomColor(),
            		highlightFill: randomColor(),
            		highlightStroke: randomColor(),
					data: [<c:forEach var="value" items="${advisorYearSales}">"${value[1]}", </c:forEach>]					
				}
			]		
	};
	
	var options = {
			tooltipTemplate: 
				function(label){return  '$' + label.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");},
			animation: false,
			scaleLabel:
				function(label){return  '$' + label.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");}
	};
	
	var advisorBarChart = new Chart(ctx).Bar(advisorYearSalesData, options);
	
	// advisor sales trend line chart
	var ctx = document.getElementById("advisorLineChart").getContext("2d");
	
	var advisorTrendSalesData = {
			labels: [<c:forEach var="value" items="${advisorTrendSales}">"${value[0]}", </c:forEach>],
			datasets: [
				{
					label: "Trend Sales Dataset",
					fillColor : "rgba(151,187,205,0.2)",
					strokeColor : "rgba(151,187,205,1)",
					pointColor : "rgba(151,187,205,1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(151,187,205,1)",
					data : [<c:forEach var="value" items="${advisorTrendSales}">"${value[1]}", </c:forEach>]
				}	           
			]
	};
	
	var options = {
			tooltipTemplate: 
				function(label){return  '$' + label.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");},
			animation: false,
			scaleLabel:
				function(label){return  '$' + label.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");}
	};
	
	var advisorLineChart = new Chart(ctx).Line(advisorTrendSalesData, options);
	</script>
	 
</body>

</html>