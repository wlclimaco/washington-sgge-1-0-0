<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%
	response.setContentType("text/html; charset=iso-8859-1");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" media="all" href="../thirdparty/jquery/Niceforms-master/niceforms-default.css" />
<link type="text/css" href="../styles/common_grid.css" rel="stylesheet" />
<link type="text/css" href="../styles/slick.pager.css" rel="stylesheet" />
</head>
<body><div id="container">
<form action="vars.php" method="post" class="niceform">
	<fieldset>
    	<legend>Produto Info</legend>
        <dl>
        	<dt><label for="codbarra">Codigo de Barra:</label></dt>
            <dd><input type="text" name="codbarra" id="codbarra" size="32" maxlength="60" /></dd>
        </dl>
        <dl>
        	<dt><label for="nomeProd">Nome Produro:</label></dt>
            <dd><input type="text" name="nomeProd" id="nomeProd" size="32" maxlength="200" /></dd>
        </dl>
        <dl>
        	<dt><label for="descr">Descrição:</label></dt>
            <dd><input type="text" name="descr" id="descr" size="32" maxlength="200" /></dd>
        </dl>
        <dl>
        <dt><label for="unimed">Unidade de Medida:</label></dt>
            <dd>
            	<select size="1" name="unimed" id="unimed">
                    <option value="Guy">Guy</option>
                    <option value="Girl">Girl</option>
                    <option value="Dude">Dude</option>
                    <option value="Chic">Chic</option>
                    <option value="Gentleman">Gentleman</option>
                    <option value="Lady">Lady</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Don't Ask">Don't Ask</option>
            	</select>
            </dd>
            </dl>
    </fieldset>
    <fieldset>
    	<legend>Configuração Sistema</legend>
    	<dl>
           <dt><label for="marca">Marca:</label></dt>
            <dd>
            	<select size="1" name="marca" id="marca">
                    <option value="Guy">Guy</option>
                    <option value="Girl">Girl</option>
                    <option value="Dude">Dude</option>
                    <option value="Chic">Chic</option>
                    <option value="Gentleman">Gentleman</option>
                    <option value="Lady">Lady</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Don't Ask">Don't Ask</option>
            	</select>
            </dd>
           </dl>
           <dl>
           <dt><label for="menu">Menu:</label></dt>
            <dd>
            	<select size="1" name="menu" id="menu">
                    <option value="Guy">Guy</option>
                    <option value="Girl">Girl</option>
                    <option value="Dude">Dude</option>
                    <option value="Chic">Chic</option>
                    <option value="Gentleman">Gentleman</option>
                    <option value="Lady">Lady</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Don't Ask">Don't Ask</option>
            	</select>
            </dd>
            </dl>
            <dl>
            <dt><label for="submenu">Sub Menu:</label></dt>
            <dd>
            	<select size="1" name="submenu" id="submenu">
                    <option value="Guy">Guy</option>
                    <option value="Girl">Girl</option>
                    <option value="Dude">Dude</option>
                    <option value="Chic">Chic</option>
                    <option value="Gentleman">Gentleman</option>
                    <option value="Lady">Lady</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Don't Ask">Don't Ask</option>
            	</select>
            </dd>
            </dl>
            <dl>
            <dt><label for="trimenu">Tri Menu:</label></dt>
            <dd>
            	<select size="1" name="trimenu" id="trimenu">
                    <option value="Guy">Guy</option>
                    <option value="Girl">Girl</option>
                    <option value="Dude">Dude</option>
                    <option value="Chic">Chic</option>
                    <option value="Gentleman">Gentleman</option>
                    <option value="Lady">Lady</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Don't Ask">Don't Ask</option>
            	</select>
            </dd>
            </dl>
    </fieldset>
     <fieldset>
    	<legend>Imagens</legend>
        <dl>
        	<dt><label for="upload">Upload a File:</label></dt>
            <dd><input type="file" name="upload" id="upload" /></dd>
        </dl>
    </fieldset>
     <fieldset>
     <legend>Cadastro Preços</legend>
	    <div style="width:100%;height:100%;float:left;">
			<div class="grid-header" style="width:100%">
			    <span id="listpreco" style="float:left; width: 16px; height: 16px; background-image:url('../images/text_list.png')" title="List Preços"></span>
				<sec:authorize access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
			    <span id="refreshpreco" style="float:right" class="ui-icon ui-icon-refresh" title="Rebuild Procedures"></span>
				</sec:authorize>
			</div>
			<div id="precoGrid" style="width:100%; height:95%;" class="wdgrid" ></div>
			<div id="pager" style="width:100%; height:3%;"></div>
		</div>
	 </fieldset>
    <fieldset class="action">
    	<input type="submit" name="submit" id="submit" value="Submit" />
    	<input type="submit" name="submit" id="submit" value="Submit" />
    </fieldset>
</form>

</div></body>
<script type="text/javascript" src="../thirdparty/jquery/Niceforms-master/niceforms.js"></script>
<script type="text/javascript" src="../commons/scripts/model/user_context.js"></script>
<script type="text/javascript" src="../commons/scripts/model/request_objects.js"></script>
<script type="text/javascript" src="../commons/scripts/model/domain_objects.js"></script>
<script type="text/javascript" src="../commons/scripts/widget/slick.pager.js"></script>
<jsp:include page="../../commons/scripts/util/app_reuse_functions.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/produto/preco_mvc_bas_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/produto/preco_mvc_bas_init.js.jsp" flush="true"/>
</html>
