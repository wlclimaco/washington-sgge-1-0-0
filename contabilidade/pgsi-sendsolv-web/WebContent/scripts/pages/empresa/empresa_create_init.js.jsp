<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

	<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The init namespace for the Location Create Page.
 */
	//Receives preloaded data
	<c:choose>
		<c:when test="${empty response}">
	    	var oPreLoadResponse = null;
	    </c:when>
	    <c:otherwise>
	    	var oPreLoadResponse = ${response};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty number_of_employees}">
	    	var oEmployees = null;
	    </c:when>
	    <c:otherwise>
	    	var oEmployees = ${number_of_employees};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty number_of_migrant_workers}">
	    	var oMigrantWorkers = null;
	    </c:when>
	    <c:otherwise>
	    	var oMigrantWorkers= ${number_of_migrant_workers};
	    </c:otherwise>
	</c:choose>

$(document).ready(function()
{

	$("#Button1").on("click", function() {

		var t = $(".test").length;
		var sTop = $(".test:eq("+(t-1)+")").css('top')
		if(sTop == "auto"){
			sTop ="53px"
		}
		sTop = (parseInt(sTop.replace("px",""),10)+30);

		s = '<div class="test" style="position:absolute;left:30px;top:'+sTop+'px;width:55px;height:16px;z-index:3;text-align:left;">'
			+'<span style="color:#000000;font-family:Arial;font-size:13px;">CNAE :</span>'
			+'</div>'
			+'<input type="text" id="Editbox1" style="position:absolute;left:99px;top:'+sTop+'px;width:94px;height:27px;line-height:19px;z-index:4;" name="cnae" value="">'
			+'<input type="text" id="Editbox2" style="position:absolute;left:334px;top:'+sTop+'px;width:381px;height:27px;line-height:19px;z-index:5;" name="descricao" value="">'
			+' <div id="wb_Text4" style="position:absolute;left:261px;top:'+sTop+'px;width:130px;height:16px;z-index:6;text-align:left;">'
			+'<span style="color:#000000;font-family:Arial;font-size:13px;">Descrição :</span>'
			+'</div>'
			+'<button class="btn btn-default" type="button" style="position:absolute;left:195px;top:'+sTop+'px;width:29px;height:27px;z-index:40;"><i class="fa fa-search"></i></button>'
			+'<input type="button" id="bRemove" name="" value=" - " style="position:absolute;left:729px;top:'+sTop+'px;width:34px;height:25px;z-index:50;">'
		$('#cnae').append(s);
		var ii = parseInt($('#wb_Form2').css('top').replace("px",""),10)+40;
		$('#wb_Form2').attr({'style':'position:absolute;left:0px;  top:'+(ii)+'px;width:782px;height:365px;z-index:42;'})
		$('#Button1').attr({'style':'position:absolute;left:204px;top:'+(ii-5)+'px;width:130px;height:16px;z-index:28;'})

	});

	//=====
	$("#Button3").on("click", function() {

		var t = $(".test001").length;
		var sTop = $(".test001:eq("+(t-1)+")").css('top')
		if(sTop == "auto"){
			sTop ="53px"
		}
		sTop = (parseInt(sTop.replace("px",""),10)+35);



		s = '<div id="wb_Text12" class="test001" style="position:absolute;left:45px;top:'+sTop+'px;width:40px;height:16px;z-index:23;text-align:left;">'
			+'<span style="color:#000000;font-family:Arial;font-size:13px;">Type :</span>'
			+'</div>'
			+'<select name="emailType" class="Editbox9" maxlength="254" style="position:absolute;left:99px;top:'+sTop+'px;width:74px;height:27px;line-height:27px;z-index:24;" name="nome" value="">'
					+'<option>Comercial</option>'
					+'<option>Compras</option>'
					+'<option>SAC</option>'
					+'<option>NFE</option>'
				+'</select>'
			+'<div id="wb_Text12" style="position:absolute;left:322px;top:'+sTop+'px;width:40px;height:16px;z-index:23;text-align:left;">'
				+'<span style="color:#000000;font-family:Arial;font-size:13px;">DDD :</span>'
			  +'</div>'
			  +'<input type="text" id="Editboxd9" style="position:absolute;left:374px;top:'+sTop+'px;width:74px;height:27px;line-height:27px;z-index:24;" name="nome" value="">'
			+'<input type="button" id="Button3" name="" value="+" style="position:absolute;left:702px;top:'+sTop+'px;width:34px;height:27px;z-index:25;">'
			+'<div id="wb_Text13" style="position:absolute;left:457px;top:'+sTop+'px;width:80px;height:16px;z-index:26;text-align:left;">'
			+'<span style="color:#000000;font-family:Arial;font-size:13px;">Telefone :</span>'
			+'</div>'
			+'<input type="text" id="Editbox10" style="position:absolute;left:527px;top:'+sTop+'px;width:157px;height:27px;line-height:27px;z-index:27;" name="nome" value="">'
		$('#telefones').append(s);
		var ii = parseInt($('#wb_Text24').css('top').replace("px",""),10)+40;
		$('#wb_Text24').attr({'style':'position:absolute;left:0px;top:'+ii+'px;'})
	});

	//==================

	$("#Button4").on("click", function() {

		var t = $(".test002").length;
		var sTop = $(".test002:eq("+(t-1)+")").css('top')
		if(sTop == "auto"){
			sTop ="53px"
		}
		sTop = (parseInt(sTop.replace("px",""),10)+30);

		s = '<div id="wb_Text14" class="test002" style="position:absolute;left:30px;top:'+sTop+'px;width:55px;height:16px;z-index:29;text-align:left;">'
			+'<span style="color:#000000;font-family:Arial;font-size:13px;">Socio :</span>'
			+'</div>'
			+'<input type="text" id="Editbox11" style="position:absolute;left:99px;top:'+sTop+'px;width:270px;height:27px;line-height:27px;z-index:30;" name="nome" value="">'
		    +'<button class="btn btn-default" type="button" style="position:absolute;left:369px;top:'+sTop+'px;width:29px;height:27px;z-index:40;"><i class="fa fa-search"></i></button>'
			+'<div id="wb_Text15" style="position:absolute;left:411px;top:'+sTop+'px;width:80px;height:16px;z-index:31;text-align:left;">'
			+'<span style="color:#000000;font-family:Arial;font-size:13px;">Cota :</span>'
			+'</div>'
			+'<input type="text" id="Editbox12" style="position:absolute;left:453px;top:'+sTop+'px;width:57px;height:27px;line-height:27px;z-index:32;" name="nome" value="">'
			+'<input type="button" id="Button4" name="" value="+" style="position:absolute;left:695px;top:'+sTop+'px;width:34px;height:25px;z-index:33;">'
			+'<div id="wb_Text16" style="position:absolute;left:528px;top:'+sTop+'px;width:80px;height:32px;z-index:34;text-align:left;">'
			+'<span style="color:#000000;font-family:Arial;font-size:13px;">Porcentagem :</span>'
			+'</div>'
			+'<input type="text" id="Editbox13" style="position:absolute;left:627px;top:'+sTop+'px;width:57px;height:27px;line-height:27px;z-index:35;" name="nome" value="">'

		$('#socios').append(s);
		var ii = parseInt($('#wb_Text25').css('top').replace("px",""),10)+40;
		$('#wb_Text25').attr({'style':'position:absolute;left:0px;top:'+ii+'px;'})
	});

	$.pgsi.progressBar.stopGlobal();
});
</script>

</sec:authorize>