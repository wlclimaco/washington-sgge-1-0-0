<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">

	<link rel="stylesheet" href="styles/jquery-ui.min.css" />
	<link href="styles/bootstrap.css" rel="stylesheet">
	<link href="styles/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="styles/dataTables.bootstrap.css" rel="stylesheet">
    <link href="styles/dataTables.responsive.css" rel="stylesheet">

</head>
<body>
<div id="preload">
	<div>
		<span></span>
	</div>
</div>

<div id="idletimeout" style="display:none;">
	<a href="#"></a>.
</div>


<div class="fullscreen"></div>
<div id="loading" style="display: none;">
	<h5></h5>
	<div id="progress-bar"></div>
</div>
<div id="action-dialog" style="display: none"></div>
<div id="action-dialog-Error" style="display: none"></div>
<script type="text/javascript" src="thirdparty/head.min.js"></script>
<script>



   head.js(
			"/qat-webdaptive/thirdparty/components/jquery/dist/jquery.min.js",
		   "/qat-webdaptive/thirdparty/jquery/styles/jnotify/jquery.jnotify.css",
		   "/qat-webdaptive/thirdparty/components/morrisjs/morris.css",
		   "/qat-webdaptive/thirdparty/components/font-awesome/css/font-awesome.min.css",
			"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/bootstrap/css/bootstrap.min.css",
			"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/plugins/jvectormap/jquery-jvectormap-1.2.2.css",
			"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/dist/css/AdminLTE.min.css",
			"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/dist/css/skins/_all-skins.min.css",
			"/qat-webdaptive/thirdparty/components/morrisjs/morris.css",
			"/qat-webdaptive/styles/ng-table.min.css",



		   "/qat-webdaptive/commons/scripts/util/namespace_init.js",
		   "/qat-webdaptive/commons/scripts/util/wd_log.js",
		   "/qat-webdaptive/scripts/config/wd_config.js",

"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/plugins/jQuery/jQuery-2.1.4.min.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/bootstrap/js/bootstrap.min.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/plugins/morris/morris.min.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/plugins/sparkline/jquery.sparkline.min.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/plugins/jvectormap/jquery-jvectormap-world-mill-en.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/plugins/knob/jquery.knob.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/plugins/daterangepicker/daterangepicker.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/plugins/datepicker/bootstrap-datepicker.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/plugins/slimScroll/jquery.slimscroll.min.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/plugins/fastclick/fastclick.min.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/dist/js/app.min.js",
"/qat-webdaptive/thirdparty/AdminLTE-2.3.0/dist/js/demo.js",
			"/qat-webdaptive/thirdparty/jquery/jquery.address-1.6.min.js",
		   "/qat-webdaptive/thirdparty/jquery/custom/jquery.i18n.properties.js",
		   "/qat-webdaptive/thirdparty/jquery/custom/jquery.format-1.2.js",
		   "/qat-webdaptive/thirdparty/jquery/jquery.cookie.js",
		   "/qat-webdaptive/thirdparty/jquery/jquery.equalheights.js",
		   "/qat-webdaptive/thirdparty/jquery/jquery.jnotify.js",
		   "/qat-webdaptive/thirdparty/jquery/jquery.blockUI.js",
		   "/qat-webdaptive/thirdparty/jquery/jquery.validate.min.js",
		   "/qat-webdaptive/thirdparty/jquery/jquery.idle-timer.js" ,
		   "/qat-webdaptive/thirdparty/jquery/jquery.idletimeout.js",
		   "/qat-webdaptive/thirdparty/jquery/jquery.json-2.4.min.js",
		   "/qat-webdaptive/thirdparty/jquery/jquery.event.drag-2.2.js",

	  		"/qat-webdaptive/commons/scripts/util/qat_commons_1.3.js",
	  		"/qat-webdaptive/commons/scripts/util/qat_commons_table_1.0.js",
		    "/qat-webdaptive/thirdparty/formvalidation-master/dist/js/select2.min.js",
			"/qat-webdaptive/thirdparty/formvalidation-master/dist/js/formValidation.js",
			"/qat-webdaptive/thirdparty/jquery/jquery.inputmask.js",
			"/qat-webdaptive/thirdparty/jquery/jquery.inputmask.date.extensions.js",
			"/qat-webdaptive/thirdparty/jquery/jquery.inputmask.extensions.js",
			"/qat-webdaptive/thirdparty/jquery/jquery.inputmask.numeric.extensions.js",
			"/qat-webdaptive/thirdparty/jquery/jquery.inputmask.regex.extensions.js",
			"/qat-webdaptive/thirdparty/jquery/jstorage.min.js",

			"/qat-webdaptive/thirdparty/timezone-js/date.custom.js",
			"/qat-webdaptive/thirdparty/jquery/jquery.ThreeDots.min.js",
			"/qat-webdaptive/thirdparty/jquery/custom/jquery.dataTables.custom.js",
			"/qat-webdaptive/thirdparty/jquery/jquery.datatable.fnReloadAjax.js",
			"/qat-webdaptive/thirdparty/jquery/jquery.datatable.fnStartingRedraw.js",
			"/qat-webdaptive/thirdparty/jquery/custom/tableExport.js",
			"/qat-webdaptive/thirdparty/bootstrap-table/src/bootstrap-table.js",
			"/qat-webdaptive/thirdparty/knockout/knockout-3.3.0.js",
		   "/qat-webdaptive/commons/scripts/model/user_context.js",
           "/qat-webdaptive/commons/scripts/model/request_objects.js",


		   "/qat-webdaptive/commons/scripts/model/domain_objects.js",
           "/qat-webdaptive/commons/scripts/widget/slick.pager.js",
"/qat-webdaptive/scripts/util/actiondialog.js",
"/qat-webdaptive/scripts/pages/empresa/empresa.js",
"/qat-webdaptive/scripts/pages/regime/regime.js",
"/qat-webdaptive/scripts/pages/documento/documento.js",
"/qat-webdaptive/scripts/pages/endereco/endereco.js",
"/qat-webdaptive/scripts/pages/email/email.js",
"/qat-webdaptive/scripts/pages/phone/telefone.js",
"/qat-webdaptive/scripts/pages/cnae/cnae.js",
"/qat-webdaptive/scripts/pages/status/status.js",
"/qat-webdaptive/scripts/pages/note/note.js",
"/qat-webdaptive/scripts/pages/socios/socios.js",
"/qat-webdaptive/scripts/pages/plano/plano.js",
"/qat-webdaptive/scripts/pages/filial/filial.js",
"/qat-webdaptive/scripts/pages/cidade/cidade.js",
"/qat-webdaptive/scripts/pages/usuario/usuario.js",
"/qat-webdaptive/scripts/pages/estado/estado.js",
"/qat-webdaptive/scripts/pages/deposito/deposito.js",

"/qat-webdaptive/scripts/pages/agencia/agencia.js",
"/qat-webdaptive/scripts/pages/banco/banco.js",
"/qat-webdaptive/scripts/pages/beneficios/beneficio.js",
"/qat-webdaptive/scripts/pages/condpg/condPg.js",
"/qat-webdaptive/scripts/pages/contato/contato.js",
"/qat-webdaptive/scripts/pages/eventos/evento.js",
"/qat-webdaptive/scripts/pages/formaPg/formaPg.js",
"/qat-webdaptive/scripts/pages/salario/salario.js",
"/qat-webdaptive/scripts/pages/horaFunc/horario.js",
"/qat-webdaptive/scripts/pages/funcionario/funcionario.js",
"/qat-webdaptive/scripts/pages/pgsi_main.js",
"//cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.2/angular.js",
"/qat-webdaptive/thirdparty/js/ng-table.js"
	);
</script>

<jsp:include page="../scripts/pages/wd_main_init.js.jsp" flush="true" />

</body>
</html>