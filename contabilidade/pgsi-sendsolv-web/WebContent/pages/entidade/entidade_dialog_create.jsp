<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
<div id="wb_Form1" style="position:absolute;left:0px;top:0px;width:782px;height:365px;z-index:0;">
   <form name="Form1" method="post" action="" enctype="text/plain" id="Form1">

	  <div class="form-group">
		  <div id="wb_Text1" style="position:absolute;left:30px;top:26px;width:55px;z-index:0;text-align:left;">
			 <span style="color:#000000;font-family:Arial;font-size:13px;">Codigo :</span>
		  </div>
		  <input type="text" id="codigo" style="position:absolute;left:99px;top:21px;width:94px;height:27px;line-height:27px;z-index:1;" name="codigo" value="">
		  <input type="text" id="nome" style="position:absolute;left:334px;top:21px;width:427px;height:27px;line-height:27px;z-index:2;" name="nome" value="">
	  </div>

	  <div id="cnae" style="z-index:400;">
		  <div id="wb_Text3" class="test" style="position:absolute;left:30px;top:58px;width:55px;height:16px;z-index:3;text-align:left;">
			 <span style="color:#000000;font-family:Arial;font-size:13px;">CNAE :</span>
		  </div>
		  <input type="text" id="Editbox1" style="position:absolute;left:99px;top:53px;width:94px;height:27px;line-height:27px;z-index:4;" name="cnae" value="">
		  <input type="text" id="Editbox2" style="position:absolute;left:334px;top:53px;width:381px;height:27px;line-height:27px;z-index:5;" name="descricao" value="">
		  <div id="wb_Text4" style="position:absolute;left:261px;top:58px;width:130px;height:16px;z-index:6;text-align:left;">
			 <span style="color:#000000;font-family:Arial;font-size:13px;">Descrição :</span>
		  </div>
		  <button class="btn btn-default" type="button" style="position:absolute;left:195px;top:53px;width:29px;height:23px;z-index:40;"><i class="fa fa-search"></i></button>
		  <div id="wb_Text2" style="position:absolute;left:204px;top:26px;width:130px;height:16px;z-index:28;text-align:left;">
			 <span style="color:#000000;font-family:Arial;font-size:13px;">Nome/Razão Social :</span>
		  </div>

		<a title="Adicionar Cnae" id="Button1" href="javascript:;" style="position:absolute;left:204px;top:81px;width:130px;height:16px;z-index:9999;text-align:left;"><span class="icon-small-button icon-nav icon-plus"></span>Adicionar Cnae</a>
	    </div>
		<div id="cnae"></div>
	 </div>


	 <div id="wb_Form2" style="position:absolute;left:0px;top:110px;width:782px;height:365px;z-index:42;">

	  <jsp:include page="../email/email_main.jsp" flush="true" />

	  <div id="emails" style="position:absolute;left:0px;top:-58px;width:782px;height:365px;z-index:0;"></div>

	  <div id="wb_Form3" style="position:absolute;left:0px;top:0px;">
	  <input type="text" id="enderecoId" class="hide">
      <input type="text" id="logradouro" style="position:absolute;left:99px;top:88px;width:286px;height:27px;line-height:27px;z-index:8;" name="logradouro" value="">
      <div id="wb_Text5" style="position:absolute;left:11px;top:93px;width:88px;height:16px;z-index:9;text-align:left;">
         <span style="color:#000000;font-family:Arial;font-size:13px;">Logradouro :</span>
      </div>
      <div id="wb_Text6" style="position:absolute;left:399px;top:93px;width:58px;height:16px;z-index:10;text-align:left;">
         <span style="color:#000000;font-family:Arial;font-size:13px;">Cidade :</span>
      </div>
      <input type="text" id="cidade" style="position:absolute;left:453px;top:88px;width:173px;height:27px;line-height:27px;z-index:11;" name="cidade" value="">
      <div id="wb_Text7" style="position:absolute;left:637px;top:93px;width:58px;height:16px;z-index:12;text-align:left;">
         <span style="color:#000000;font-family:Arial;font-size:13px;">Estado :</span>
      </div>
      <select name="estado" size="1" id="estado" style="position:absolute;left:695px;top:88px;width:68px;height:21px;z-index:13;">
      </select>
      <input type="text" id="cep" style="position:absolute;left:99px;top:116px;width:103px;height:27px;line-height:27px;z-index:14;" name="cep" value="">
      <div id="wb_Text8" style="position:absolute;left:45px;top:121px;width:40px;height:16px;z-index:15;text-align:left;">
         <span style="color:#000000;font-family:Arial;font-size:13px;">CEP :</span>
      </div>
      <div id="wb_Text9" style="position:absolute;left:224px;top:121px;width:51px;height:16px;z-index:16;text-align:left;">
         <span style="color:#000000;font-family:Arial;font-size:13px;">CNPJ :</span>
      </div>
      <input type="text" id="Editbox6" style="position:absolute;left:275px;top:116px;width:176px;height:27px;line-height:27px;z-index:17;" name="cnpj" value="">
      <div id="wb_Text10" style="position:absolute;left:457px;top:121px;width:129px;height:16px;z-index:18;text-align:left;">
         <span style="color:#000000;font-family:Arial;font-size:13px;">Inscrição Estadual :</span>
      </div>
      <input type="text" id="Editbox7" style="position:absolute;left:586px;top:116px;width:175px;height:27px;line-height:27px;z-index:19;" name="IE" value="">
	  <div style="position:absolute;left:0px;top:148px;width:129px;height:16px;z-index:38;text-align:left;">
			<div id="wb_Text19" style="position:absolute;text-align:left; left:10px;width:180px;height:200px;">
				 <span style="color:#000000;font-family:Arial;font-size:13px;">Regime Tributario :</span>
			  </div>
			  <select name="regime" size="1" id="regime" style="position:absolute;z-index:39;left:125px;width:235px;height:27px;">
				<option value="1">Simples Nacional</option>
				<option value="2">Lucro Real</option>
				<option value="3">MEI</option>
				<option value="4">Lucro Presumido</option>
			</select>
		</div>
      <div id="wb_Text12"  style="position:absolute;left:45px;top:192px;width:40px;height:16px;z-index:23;text-align:left;">
         <span style="color:#000000;font-family:Arial;font-size:13px;">Type :</span>
      </div>
	  <select name="emailType" class="Editbox9" maxlength="254" style="position:absolute;left:99px;top:190px;width:74px;height:27px;line-height:27px;z-index:24;" name="nome" value="">
					<option>Comercial</option>
					<option>Compras</option>
					<option>SAC</option>
					<option>NFE</option>
				</select>

     <div id="wb_Text12" style="position:absolute;left:322px;top:192px;width:40px;height:16px;z-index:23;text-align:left;">
         <span style="color:#000000;font-family:Arial;font-size:13px;">DDD :</span>
      </div>
      <input type="text" id="Editboxd9" style="position:absolute;left:374px;top:190px;width:74px;height:27px;line-height:27px;z-index:24;" name="nome" value="">

	 <input type="button" id="Button3" name="" value="+" style="position:absolute;left:702px;top:190px;width:34px;height:23px;z-index:25;">

	  <div id="wb_Text13" style="position:absolute;left:457px;top:192px;width:80px;height:16px;z-index:26;text-align:left;">
         <span style="color:#000000;font-family:Arial;font-size:13px;">Telefone :</span>
      </div>

      <input type="text" id="Editbox10" style="position:absolute;left:523px;top:190px;width:157px;height:27px;line-height:27px;z-index:27;" name="nome" value="">
	  <div id="wb_Text18" style="position:absolute;left:457px;top:159px;width:129px;height:16px;z-index:36;text-align:left;">
			 <span style="color:#000000;font-family:Arial;font-size:13px;">Inscrição Municipal :</span>
		  </div>
		  <input type="text" id="Editbox15" style="position:absolute;left:586px;top:154px;width:175px;height:27px;line-height:27px;z-index:37;" name="IM" value="">
		<div class="test001" style="position:absolute;left:0px;top:51px;"></div>
		<div id="telefones"  style="position:absolute;left:0px;top:133px;"></div>
		<div id="wb_Text24"  style="position:absolute;left:0px;top:0px;">
		  <div id="wb_Text14" style="position:absolute;left:30px;top:232px;width:55px;height:16px;z-index:29;text-align:left;">
			 <span style="color:#000000;font-family:Arial;font-size:13px;">Socio :</span>
		  </div>
		  <input type="text" id="Editbox11" style="position:absolute;left:99px;top:227px;width:270px;height:27px;line-height:27px;z-index:30;" name="nome" value="">
		   <button class="btn btn-default" type="button" style="position:absolute;left:369px;top:227px;width:29px;height:27px;z-index:40;"><i class="fa fa-search"></i></button>
		  <div id="wb_Text15" style="position:absolute;left:411px;top:232px;width:80px;height:16px;z-index:31;text-align:left;">
			 <span style="color:#000000;font-family:Arial;font-size:13px;">Cota :</span>
		  </div>
		  <input type="text" id="Editbox12" style="position:absolute;left:453px;top:227px;width:57px;height:27px;line-height:27px;z-index:32;" name="nome" value="">
		  <input type="button" id="Button4" name="" value="+" style="position:absolute;left:695px;top:223px;width:34px;height:25px;z-index:33;">
		  <div id="wb_Text16" style="position:absolute;left:528px;top:232px;width:80px;height:32px;z-index:34;text-align:left;">
			 <span style="color:#000000;font-family:Arial;font-size:13px;">Porcentagem :</span>
		  </div>
		  <input type="text" id="Editbox13" style="position:absolute;left:619px;top:227px;width:57px;height:27px;line-height:27px;z-index:35;" name="nome" value="">

		  <div class="test002" style="position:absolute;left:0px;top:51px;"></div>
		  <div id="socios"  style="position:absolute;left:0px;top:172px;"></div>
		  <div id="wb_Text25"  style="position:absolute;left:0px;top:0px;">	</div>

	   </div>
	  </div>
   </form>
		</div>
	</div>
</div>

<jsp:include page="../../scripts/pages/empresa/empresa_create_init.js.jsp" flush="true" />