<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%
	response.setContentType("text/html; charset=utf-8");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" media="all" href="../dthirdparty/jquery/Niceforms-master/niceforms-default.css" />
<style type="text/css">

<!---->
.gugu {

	width:			800px!important;
	height:			400px!important;
    }
.hide{display:none;}

</style>
</head>
<body><div id="container">
<form action="vars.php" method="post" class="niceform">
	<fieldset>
    	<legend>Supermercado Info</legend>
        <dl>
        	<dt><label for="nome">Nome:</label></dt>
            <dd><input type="text" name="nome" id="nome" size="32" maxlength="100" /></dd>
			<dd><input type="text" name="codId" id="codId" size="32" maxlength="60" class="hide" /></dd>
			<dd><input type="text" name="codEnd" id="codEnd" size="32" maxlength="60" class="hide" /></dd>
			<dd><input type="text" name="endId" id="endId" size="32" maxlength="60" class="hide" /></dd>
			<dd><input type="text" name="codDoc" id="codDoc" size="32" maxlength="60" class="hide" /></dd>
			<dd><input type="text" name="idDoc" id="idDoc" size="32" maxlength="60" class="hide" /></dd>
        </dl>

        <dl>
        	<dt><label for="site">Site:</label></dt>
            <dd><input type="text" name="site" id="site" size="32" maxlength="100" /></dd>
        </dl>
         <dl>
        	<dt><label for="email">Email:</label></dt>
            <dd><input type="text" name=email id="email" size="32" maxlength="100" /></dd>
        </dl>
        <dl>
        	<dt><label for="usuario">Usuario:</label></dt>
            <dd><input type="text" name="usuario" id="usuario" size="32" maxlength="100" /></dd>
        </dl>
        <dl>
        	<dt><label for="password">Senha:</label></dt>
            <dd><input type="password" name="password" id="password" size="32" maxlength="32" /></dd>
        </dl>
    </fieldset>
    <fieldset>
    	<legend>Documentos</legend>
    	<dl>
        	<dt><label for="rg">Incri��o Municipal:</label></dt>
            <dd><input type="text" name="rg" id="rg" size="32" maxlength="100" /></dd>
        </dl>
        <dl>
        	<dt><label for="cpf">CNPJ:</label></dt>
            <dd><input type="text" name="cpf" id="cpf" size="32" maxlength="100" /></dd>
        </dl>
          <dl>
        	<dt><label for="dtnasci">Data Abertura:</label></dt>
            <dd><input type="text" name="dtnasci" id="dtnasci" size="32" maxlength="100" /></dd>
        </dl>
    </fieldset>
     <fieldset>
    	<legend>Endere�o</legend>

    	<dl>
        	<dt><label for="cep">Cep:</label></dt>
            <dd><input type="text" name="cep" id="cep" size="32" maxlength="100" /></dd>
        </dl>
         <dl>
        	<dt><label for="logradouro">Logradouro:</label></dt>
            <dd><input type="text" name="logradouro" id="logradouro" size="32" maxlength="100" /></dd>
        </dl>
         <dl>
        	<dt><label for="bairro">Bairro:</label></dt>
            <dd><input type="text" name="bairro" id="bairro" size="32" maxlength="100" /></dd>
        </dl>
         <dl>
        	<dt><label for="cidade">Cidade:</label></dt>
            <dd><input type="text" name="cidade" id="cidade" size="32" maxlength="100" /></dd>
        </dl>
        <dl>
  <dt><label for="estado">Estado:</label></dt>
            <dd>
  <select size="1" name="estado" id="estado">
	  <option value="">Selecione</option>
	  <option reference="AC" value="AC">Acre</option>
	  <option reference="AL" value="AL">Alagoas</option>
	  <option reference="AP" value="AP">Amap�</option>
	  <option reference="AM" value="AM">Amazonas</option>
	  <option reference="BA" value="BA">Bahia</option>
	  <option reference="CE" value="CE">Cear�</option>
	  <option reference="DF" value="DF">Distrito Federal</option>
	  <option reference="ES" value="ES">Esp�rito Santo</option>
	  <option reference="GO" value="GO">Goias</option>
	  <option reference="MA" value="MA">Maranhao</option>
	  <option reference="MT" value="MT">Mato Grosso</option>
	  <option reference="MS" value="MS">Mato Grosso do Sul</option>
	  <option reference="MG" value="MG">Minas Gerais</option>
	  <option reference="PA" value="PA">Par�</option>
	  <option reference="PB" value="PB">Para�ba</option>
	  <option reference="PR" value="PR">Paran�</option>
	  <option reference="PE" value="PE">Pernambuco</option>
	  <option reference="PI" value="PI">Piau�</option>
	  <option reference="RJ" value="RJ">Rio de Janeiro</option>
	  <option reference="RN" value="RN">Rio Grande do Norte</option>
	  <option reference="RS" value="RS">Rio Grande do Sul</option>
	  <option reference="RO" value="RO">Rond�nia</option>
	  <option reference="RR" value="RR">Roraima</option>
	  <option reference="SC" value="SC">Santa Catarina</option>
	  <option reference="SP" value="SP">Sao Paulo</option>
	  <option reference="SE" value="SE">Sergipe</option>
	  <option reference="TO" value="TO">Tocantins</option>
	</select>
	 </dl>
         <dl>
        	<dt><label for="numero">Numero:</label></dt>
            <dd><input type="text" name="numero" id="numero" size="32" maxlength="100" /></dd>
        </dl>
         <dl>
        	<dt><label for="complemento">Complemento:</label></dt>
            <dd><input type="text" name="complemento" id="complemento" size="32" maxlength="100" /></dd>
        </dl>
    </fieldset>
</form>

</div></body>
<script type="text/javascript" src="../thirdparty/jquery/Niceforms-master/niceforms.js"></script>
<script type="text/javascript" src="../commons/scripts/model/user_context.js"></script>
<script type="text/javascript" src="../commons/scripts/model/request_objects.js"></script>
<script type="text/javascript" src="../commons/scripts/model/domain_objects.js"></script>
<jsp:include page="../../scripts/pages/supermercado/supermercado_create_mvc_bas_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/supermercado/supermercado_create_mvc_bas_init.js.jsp" flush="true"/>
</html>
