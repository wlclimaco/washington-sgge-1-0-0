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
<script language="javascript" type="text/javascript" src="niceforms.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="../thirdparty/Niceforms-master/niceforms-default.css" />
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
    <fieldset class="action">
    	<input type="submit" name="submit" id="submit" value="Submit" />
    	<input type="submit" name="submit" id="submit" value="Submit" />
    </fieldset>
</form>

</div></body>
<script type="text/javascript" src="../thirdparty/jquery/Niceforms-master/niceforms.js"></script>
</html>
