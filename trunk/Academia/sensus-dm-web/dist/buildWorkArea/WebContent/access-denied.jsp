<%@page contentType="text/html; charset=UTF-8" language="java" session="true" %>
<html>
<body>
	<center><h1>You are not authorized to access the requested resource.</h1></center>
	<center><h2>You will be redirected back to the login page in <span id="countdown">6</span> seconds.</h2></center>
	<center><h2><span id="error-message"></span></h2></center>
	<script>
		var count=6;

		var counter=setInterval(timer, 1000); //1000 will  run it every 1 second
		
		if (window.location.search.substring(0, 1) == '?')
		{
			document.getElementById("error-message").innerHTML = unescape(window.location.search.substring(1));
		}

		function timer()
		{
		  count=count-1;
		  if (count <= 0)
		  {
			 clearInterval(counter);
			 window.location.href = "<%= request.getContextPath() + "/j_spring_security_logout" %>";
			 return;
		  }

		  document.getElementById("countdown").innerHTML=count;
		}
	
	</script>
</body>
</html>