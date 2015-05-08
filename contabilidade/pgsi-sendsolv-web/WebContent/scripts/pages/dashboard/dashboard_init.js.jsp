<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

// Set main menu active class on click
$(".primary-dash .menu").click(function(e) {
	$(".col").hide();
	$(".menu").removeClass("menu-active");
	$("." + $(this).addClass("menu-active").attr("id")).show();
});

$("select").selectmenu();
$("#search-options-button").attr("placeholder", "Search");
$.pgsi.progressBar.stopGlobal();

// Set Correct links for reporting
pgsi.util.page.reports.sar.fnInit($(".dashboard>.content>.compliance>ul>li.sar-reports>.submenu"));
pgsi.util.page.reports.operation.fnInit($(".dashboard>.content>.operation-reports>ul"));


</script>

</sec:authorize>