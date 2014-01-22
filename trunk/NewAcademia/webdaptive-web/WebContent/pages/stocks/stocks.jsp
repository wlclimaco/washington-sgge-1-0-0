<%
	response.setContentType("text/html; charset=iso-8859-1");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" href="../styles/common_grid.css" rel="stylesheet" />
<script type="text/javascript" src="../scripts/pages/stocks/stocks_main.js"></script>
<script type="text/javascript" src="../scripts/pages/stocks/stocks_init.js"></script>
</head>
<body>
	<table cellpadding="0" cellspacing="0" border="0" class="display" id="dt_container" width="100%">
	</table>
</body>
</html>