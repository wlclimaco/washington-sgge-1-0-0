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
</head>
<body>
  <select size="1" name="estado" id="estado">
	  <option value="">Selecione</option>
	  <option reference="AC" value="AC">Acre</option>
	  <option reference="AL" value="AL">Alagoas</option>
	  <option reference="AP" value="AP">Amapá</option>
	  <option reference="AM" value="AM">Amazonas</option>
	  <option reference="BA" value="BA">Bahia</option>
	  <option reference="CE" value="CE">Ceará</option>
	  <option reference="DF" value="DF">Distrito Federal</option>
	  <option reference="ES" value="ES">Espírito Santo</option>
	  <option reference="GO" value="GO">Goias</option>
	  <option reference="MA" value="MA">Maranhao</option>
	  <option reference="MT" value="MT">Mato Grosso</option>
	  <option reference="MS" value="MS">Mato Grosso do Sul</option>
	  <option reference="MG" value="MG">Minas Gerais</option>
	  <option reference="PA" value="PA">Pará</option>
	  <option reference="PB" value="PB">Paraíba</option>
	  <option reference="PR" value="PR">Paraná</option>
	  <option reference="PE" value="PE">Pernambuco</option>
	  <option reference="PI" value="PI">Piauí</option>
	  <option reference="RJ" value="RJ">Rio de Janeiro</option>
	  <option reference="RN" value="RN">Rio Grande do Norte</option>
	  <option reference="RS" value="RS">Rio Grande do Sul</option>
	  <option reference="RO" value="RO">Rondônia</option>
	  <option reference="RR" value="RR">Roraima</option>
	  <option reference="SC" value="SC">Santa Catarina</option>
	  <option reference="SP" value="SP">Sao Paulo</option>
	  <option reference="SE" value="SE">Sergipe</option>
	  <option reference="TO" value="TO">Tocantins</option>
	</select>

<jsp:include page="../../scripts/pages/util/util_mvc_bas_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/util/util_mvc_bas_init.js.jsp" flush="true"/>
</body>
</html>