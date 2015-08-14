<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>


<style>

</style>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-12">
				<jsp:include page="../produto/produto_inf.jsp" flush="true" />
			</div>
			 <div class="col-xs-3"> <!-- required for floating -->
          <!-- Nav tabs -->
          <ul class="nav nav-tabs tabs-left">
            <li class="active"><a href="#home" data-toggle="tab">Cadastro</a></li>
            <li><a href="#profile" data-toggle="tab">Calculo de preco</a></li>
            <li><a href="#messages" data-toggle="tab">Messages</a></li>
            <li><a href="#settings" data-toggle="tab">Settings</a></li>
          </ul>
        </div>

        <div class="col-xs-9">
          <!-- Tab panes -->
          <div class="tab-content">
            <div class="tab-pane active"  style="background:#F3EEEE;" id="home"><jsp:include page="../produto/produto_cadastro.jsp" flush="true" /></div>
            <div class="tab-pane" id="profile"><jsp:include page="../produto/produto_preco.jsp" flush="true" /></div>
            <div class="tab-pane" id="messages">Messages Tab.</div>
            <div class="tab-pane" id="settings">Settings Tab.</div>
          </div>
        </div>

        <div class="clearfix"></div>


		</div>
	</div>
</div>

<script type="text/javascript">

$(document).ready(function() {
	console.log('ff')
	$.pgsi.progressBar.stopGlobal()
});
</script>