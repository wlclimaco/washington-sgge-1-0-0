<%@ page session="true"%>
<%
	response.setContentType("text/html; charset=utf-8");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>C&D Contabilidade</title>
<meta charset="utf-8">
<script type="text/javascript" src="js/jquery-1.6.js" ></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script>
<script type="text/javascript" src="js/Didact_Gothic_400.font.js"></script>
<script type="text/javascript" src="js/Shanti_400.font.js"></script>
<script src="js/roundabout.js" type="text/javascript"></script>
<script src="js/roundabout_shapes.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.2.js" type="text/javascript"></script>
<script type="text/javascript" src="js/script.js"></script>
<style type="text/css">
a, abbr, acronym, address, applet, article, aside, audio, b, blockquote, big, body, center, canvas, caption, cite, code, command, datalist, dd, del, details, dfn, dl, div, dt, em, embed, fieldset, figcaption, figure, font, footer, form, h1, h2, h3, h4, h5, h6, header, hgroup, html, i, iframe, img, ins, kbd, keygen, label, legend, li, meter, nav, object, ol, output, p, pre, progress, q, s, samp, section, small, span, source, strike, strong, sub, sup, table, tbody, tfoot, thead, th, tr, tdvideo, tt, u, ul, var {
	background:transparent;
	border:0 none;
	font-size:100%;
	margin:0;
	padding:0;
	border:0;
	outline:0;
	vertical-align:top
}
ol, ul {
	list-style:none
}
blockquote, q {
	quotes:none
}
table, table td {
	padding:0;
	border:none;
	border-collapse:collapse
}
img {
	vertical-align:top
}
embed {
	vertical-align:top
}
* {
	border:none
}
#page4 .body1 .main #content {
	font-family: Verdana, Geneva, sans-serif;
	color: #000000;
	font-size: 36px;
}

.col1, .col2, .col3, .cols {
	float:left
}
.col1 {
	padding-left:50px;
	width:279px
}
.col2 {
	padding-left:50px;
	width:553px
}
.col_1 {
	width:161px;
	padding-left:50px;
	float:left
}
.col_2 {
	width:131px;
	padding-left:50px;
	float:left
}
.cols {
	width:256px
}
/* index.html */
#page1 header {
	height:544px;
	background:url(../images/header_bg.png) center 131px no-repeat
}
/* index-1.html */
#page2 .col2 figure {
	float:left;
	width:53px
}
#page2 .col2 .left {
	width:195px
}
/* index-2.html */
#page4 h2.pad_top1 {
	padding-top:0;
	margin-top:-10px
}
#page4 #content {
	padding-bottom:29px
}
/* index-3.html */
/* index-4.html */
/* index-5.html */
/* index-6.html */
/* Getting the new tags to behave */
article, aside, audio, canvas, command, datalist, details, embed, figcaption, figure, footer, header, hgroup, keygen, meter, nav, output, progress, section, source, video {
	display:block
}
mark, rp, rt, ruby, summary, time {
	display:inline
}
/* Left & Right alignment */
.left {
	float:left
}
.right {
	float:right
}
.wrapper {
	width:100%;
	overflow:hidden
}
/* Global properties */
body {
	background: #454545;
	border: 0;
	color: #818181;
	line-height: 20px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
}
.ic, .ic a {
	border:0;
	float:right;
	background:#fff;
	color:#f00;
	width:50%;
	line-height:10px;
	font-size:10px;
	margin:-220% 0 0 0;
	overflow:hidden;
	padding:0
}
.main {
	margin:0 auto;
	width:980px
}
.body1 {
	background:url(../images/bg_top.gif) top repeat-x #f2f2f2
}
.body2 {
	background:#e6e6e6;
	border-bottom:1px solid #4e4e4e
}
/* main layout */
a {
	color:#000;
	text-decoration:underline;
	outline:none
}
a:hover {
	text-decoration: none;
	text-align: center;
}
h1 {
	float:left;
	padding:23px 0 0 30px
}
h2 {
	font-size:36px;
	color:#000;
	line-height:1.2em;
	padding:36px 0 21px 0;
	letter-spacing:-1px
}
h2.pad_bot1 {
	padding-bottom:11px
}
h2.pad_top1 {
	padding-top:10px
}
h3 {
	font-size:24px;
	line-height:1.2em;
	color:#000;
	padding:37px 0 13px 0;
	letter-spacing:-1px
}
p {
	padding-bottom:18px
}
/* header */
header {
	height:181px;
	width:100%;
	overflow:hidden;
	background:url(../images/bg_header2.jpg) center 131px no-repeat
}
#top_nav {
	float:right;
	padding:5px 32px 0 0
}
#top_nav li {
	float:left;
	padding-right:8px;
	margin-right:7px;
	background:url(../images/line_top.gif) right 5px no-repeat
}
#top_nav li a {
	color:#000;
	text-decoration:none;
	font-size:12px
}
#top_nav li a:hover {
	text-decoration:underline
}
#top_nav .end {
	padding-right:0;
	margin-right:0;
	background:none
}
.date {
	float:left;
	font-size:12px;
	color:#000;
	padding:5px 0 0 32px
}
#logo {
	display:block;
	background:url(../images/logo.png) 0 0 no-repeat;
	width:209px;
	height:68px;
	text-indent:-9999px
}
#menu {
	float:right;
	padding:52px 0 0 0
}
#menu li {
	float:left;
	padding-left:1px
}
#menu li a {
	display:block;
	font-size:15px;
	color:#000;
	text-decoration:none;
	line-height:60px;
	background:url(../images/menu_bg.gif) top repeat-x;
	height:56px;
	overflow:hidden;
	cursor:pointer
}
#menu li a span {
	display:block;
	background:url(../images/menu_bg_left.gif) top left no-repeat
}
#menu li a span span {
	background:url(../images/menu_bg_right.gif) top right no-repeat;
	padding:0 32px;
	height:56px
}
#menu li a:hover, #menu #menu_active a {
	color:#fff;
	background:url(../images/menu_bg_active.gif) top repeat-x
}
#menu li a:hover span, #menu #menu_active a span {
	background:url(../images/menu_bg_left_active.gif) top left no-repeat
}
#menu li a:hover span span, #menu #menu_active a span span {
	background:url(../images/menu_bg_right_active.gif) top right no-repeat
}
#menu .nav3 a:hover, #menu .nav3#menu_active a {
	background:url(../images/menu_nav3_bg.gif) top repeat-x
}
#menu .nav3 a:hover span, #menu .nav3#menu_active a span {
	background:url(../images/menu_nav3_left.gif) top left no-repeat
}
#menu .nav3 a:hover span span, #menu .nav3#menu_active a span span {
	background:url(../images/menu_nav3_right.gif) top right no-repeat
}
#menu .nav4 a:hover, #menu .nav4#menu_active a {
	background:url(../images/menu_nav4_bg.gif) top repeat-x
}
#menu .nav4 a:hover span, #menu .nav4#menu_active a span {
	background:url(../images/menu_nav4_left.gif) top left no-repeat
}
#menu .nav4 a:hover span span, #menu .nav4#menu_active a span span {
	background:url(../images/menu_nav4_right.gif) top right no-repeat
}
#menu .nav5 a:hover, #menu .nav5#menu_active a {
	background:url(../images/menu_nav5_bg.gif) top repeat-x
}
#menu .nav5 a:hover span, #menu .nav5#menu_active a span {
	background:url(../images/menu_nav5_left.gif) top left no-repeat
}
#menu .nav5 a:hover span span, #menu .nav5#menu_active a span span {
	background:url(../images/menu_nav5_right.gif) top right no-repeat
}
.text {
	font-size:20px;
	line-height:1.2em;
	color:#fff;
	padding:43px 0 0 45px;
	letter-spacing:-1px;
	float:left;
	width:340px;
	position:relative
}
.text p span {
	display:block;
	margin-top:-4px
}
.text p {
	padding-left:5px;
	padding-bottom:0px
}
.text .tittle {
	font-size:75px;
	line-height:1.2em;
	display:block;
	letter-spacing:-2px;
	margin-bottom:-10px
}
.text .tittle span {
	font-size:46px;
	line-height:1.2em;
	color:#000;
	display:block;
	margin-top:-22px;
	letter-spacing:-1px
}
.text .button1 {
	display:block;
	position:absolute;
	top:295px;
	left:49px;
	background:url(../images/button_1.png) 0 0 no-repeat;
	width:158px;
	height:56px;
	text-align:center;
	font-size:24px;
	line-height:46px;
	color:#3d6c00;
	text-transform:uppercase;
	text-decoration:none
}
.text .button1:hover {
	color:#000
}
.roundabout-holder {
	height:5em
}
.roundabout-moveable-item {
	cursor:pointer;
	width:394px;
	height:307px;
	margin-top:178px;
	padding-bottom:20px
}
.roundabout-in-focus {
	cursor:auto
}
#gallery {
	position:relative;
	height:413px;
	width:530px;
	float:right;
	padding-right:30px
}
#gallery img {
	display:block;
	width:100%;
	height:100%
}
#gallery li {
	background:url(../images/img_bg.png) center 2px no-repeat;
	overflow:hidden
}
/* content */
#content {
	padding-bottom:48px;
	width:100%;
	overflow:hidden
}
#content2 {
	padding-bottom:49px;
	width:100%;
	overflow:hidden
}
.marg_right1 {
	margin-right:19px
}
.pad_bot1 {
	padding-bottom:6px
}
.pad_bot2 {
	padding-bottom:18px
}
.pad_bot3 {
	padding-bottom:12px
}
.pad_top1 {
	padding-top:6px
}
.pad_left1 {
	padding-left:40px
}
.color1 {
	color:#000
}
.link1 {
	text-decoration:none
}
.link1:hover {
	color:#76a300
}
.button {
	display:inline-block;
	background:url(../images/button_2.png) 0 0 no-repeat;
	width:129px;
	height:44px;
	font-weight:bold;
	text-align:center;
	text-decoration:none;
	line-height:36px
}
.button:hover {
	color:#76a300
}
.list1 li {
	font-size:12px;
	line-height:24px
}
.list1 li a {
	padding-left:8px;
	background:url(../images/marker_1.gif) 0 5px no-repeat
}
.list2 {
	margin-top:-6px
}
.list2 li {
	line-height:24px
}
.list2 li a {
	padding-left:8px;
	background:url(../images/marker_1.gif) 0 5px no-repeat
}
#newsletter {
}
#newsletter .bg {
	background:url(../images/newsletter_input.png) 0 0 no-repeat;
	min-height:43px
}
#newsletter .input {
	background:none;
	padding:7px 10px;
	width:231px;
	font-size:12px;
	font-family:Arial, Helvetica, sans-serif;
	color:#c0c0c0;
	height:14px
}
#icons {
}
#icons li {
	line-height:24px;
	font-size:12px
}
#icons img {
	float:left;
	margin-right:10px;
	margin-top:4px
}
.address {
	line-height:24px;
	font-size:12px
}
.address span {
	width:57px;
	float:left;
	color:#000
}
.address a {
	color:#76a300
}
/* footer */
footer {
	color:#aaa;
	font-size:12px;
	padding:27px 0 36px 0;
	text-align:center;
	width:100%;
	overflow:hidden
}
footer a {
	color:#fff
}
footer a:hover {
}
/* forms ======= */
#ContactForm {
	padding-top:1px
}
#ContactForm span {
	float: left;
	width: 94px;
	line-height: 19px;
	color: #000
}
#ContactForm .wrapper {
	min-height:34px
}
#ContactForm .bg {
	float:left;
	background:#fff;
	border-left:1px solid #bfbfbf;
	border-top:1px solid #bfbfbf;
	border-radius:10px;
	-moz-border-radius:10px;
	-webkit-border-radius:10px;
	position:relative
}
#ContactForm .textarea_box {
	height:230px;
	width:100%;
	overflow:hidden
}
#ContactForm a {
	margin-left:2px;
	float:right
}
#ContactForm .input {
	width:230px;
	background:none;
	padding:6px 10px;
	font-size:12px;
	font-family:Arial, Helvetica, sans-serif;
	color:#c0c0c0;
	height:15px;
	margin:0
}
#ContactForm textarea {
	overflow:auto;
	width:434px;
	background:none;
	padding:6px 10px;
	font-size:12px;
	font-family:Arial, Helvetica, sans-serif;
	color:#c0c0c0;
	height:207px;
	margin:0
}


</style>
<script type="text/javascript">

</script>
</head>
<body id="page1">
<div class="body1">
  <div class="main">
    <!-- header -->
    <header>
      <div class="wrapper">
        <nav>
          <ul id="top_nav">
            <li><a href="support.html">Support</a></li>
            <li><a href="#">FAQs</a></li>
            <li class="end"><a href="#">Sitemap</a></li>
          </ul>
        </nav>
        <span class="date">
        <!-- -->
        </span> </div>
      <div class="wrapper">
        <h1><a href="index.html" id="logo">Pro Soft</a></h1>
        <nav>
          <ul id="menu">
            <li id="menu_active"><a href="index.html"><span><span>Empresa</span></span></a></li>
            <li><a href="features.html"><span><span>Serviços</span></span></a></li>
            <li class="nav3"><a href="support.html"><span><span>Planos/Valores</span></span></a></li>
            <li class="nav5"><a href="contacts.html"><span><span>Contato</span></span></a></li>
          </ul>
        </nav>
      </div>
      <div class="wrapper">
        <div class="text"> <span class="tittle">Cosme & Damião<span>Escritório Contábil</span></span></div>
        <div id="gallery">
          <ul id="myRoundabout">
            <li><img src="images/noticias_contabilidade_linkconstrucao1jpeg.jpg" alt=""></li>
            <li><img src="images/relatório-da-contabilidade-7558164.jpg" alt=""></li>
            <li><img src="images/Photoxpress_5555674.jpg" alt=""></li>
          </ul>
        </div>
      </div>
    </header>
    <!-- content -->
    <article id="content">
      <section class="col1">
        <div class="wrapper pad_bot2">
          <figure class="left marg_right1"></figure>
          <p class="pad_bot1 pad_top1"><span class="color1"><img src="images/dep_contabil.gif" width="226" height="47" alt=""></span>  </p>
          <p align="left"><em><img src="images/_318-8504.jpg" width="29" height="19">Classificação da Contabilidade;</em></p>
          <p align="left"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19">Declaração de imposto de renda;</em></p>
          <p align="left"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19">Elaboração de balancetes;</em></p>
          <p align="left"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19">Conciliação de balanço</em></p>
        </div>
        <div class="wrapper pad_bot2">
          <figure class="left marg_right1"></figure>
          <p class="pad_bot1 pad_top1"><img src="images/dep_fiscal.gif" width="226" height="47"></p>
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em>Apuração de impostos;</p>
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em>Assessoria fiscal e tributaria;</p>
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em>Atendimento nas fiscalizações;</p>
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em>Declaração de operação tributarias;</p>
        </div>
        <div class="wrapper">
          <figure class="left marg_right1"></figure>
          <img src="images/dep_pessoal (1).gif" width="226" height="47">
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em>Admissão e rescisão de contratos;</p>
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em>Assessoria trabalhista;</p>
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em>Elaboração de folha de pagamento;</p>
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em>Levantamento para aposentadoria;</p>
          <p class="pad_bot1 pad_top1"><img src="images/dep_pessoal.fw.png" width="226" height="47"></p>
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em>Sistema de gestão para pousadas e hotéis</p>
          <p align="center" class="pad_bot1 pad_top1">			   Segurança</p>
          <p align="center" class="pad_bot1 pad_top1">Reserva online</p>
          <p align="center" class="pad_bot1 pad_top1">Automação Total</p>
          <p align="center" class="pad_bot1 pad_top1">Gestão Inteligente</p>
          <p class="pad_bot1 pad_top1"><img src="images/dep_pessoal.gif" width="226" height="47"></p>
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em> Abertura/Legalização de empresa e filiais</p>
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em> Baixas e regularização de empresas</p>
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em> Cadastro de empresas;</p>
          <p class="pad_bot1 pad_top1"><em><img src="images/_318-8504.jpg" alt="" width="29" height="19"></em> Orientações nas áreas administrativas, contábil, financeira, fiscal,tributária,gestão da tecnologia da informção e pessoal;</p>
        </div>
      </section>
      <p>&nbsp;</p>
      <section class="col2">
        <div class="wrapper">
          <figure class="left marg_right1"></figure>
          <table width="580" border="0" cellspacing="0" cellpadding="0">
            <tbody>
              <tr>
                <td width="565" align="left"><p>Conheça melhor o Escritório Contábil COSME &amp; DAMIÃO <br>
                  Somos uma empresa de serviços de contabilidade constituída  por profissionais com larga experiência nas áreas de gestão empresarial. Em  nosso contexto, o cliente é a figura mais importante. Assim, nos colocamos à  sua disposição para ajudá-lo a ter sucesso neste mundo altamente competitivo.<br>
                  Contamos  com um sistema próprio de gestão para facilitar a integração cliente empresa.<br>
                  MISSÃO <br>
                  O Escritório Contábil  COSME &amp; DAMIÃO se destaca pela capacidade de inovar perante a necessidade  de constantes mudanças do universo globalizado, buscando novos horizontes para  permanecer competindo no mercado de serviços contábeis em geral com foco para o  futuro, buscando sempre se manter atualizado do mercado sempre atento as novas tendências  e tecnologias do ramo Contábil</p>
                  <p>VISÂO<br>
                Praticar a  ética, a verdade, a honestidade e o respeitando sempre nossos clientes  fornecedores e colaboradores.</p></td>
              </tr>
              <tr>
                <td colspan="2"><p>.</p></td>
              </tr>
            </tbody>
          </table>
          <p class="pad_top1 pad_bot1">.</p>

        <h2 class="pad_bot1 pad_top1">Modulos do Nosso Software de gestão</h2>
        <div class="wrapper">
          <p class="pad_top1 pad_bot1"><strong class="color1">Estoque</strong></p>
          <p class="pad_bot3">
				Estoque mínimo/máximo, data de validade, Multi depósitos com transferências</p>

          <p class="pad_top1 pad_bot1"><strong class="color1">Fechadura Eletrônica</strong></p>
          <p class="pad_bot3">
				Gravação da chave, Validade, Segurança...
           </p>
           <p class="pad_top1 pad_bot1"><strong class="color1">Tarifador Telefônico</strong></p>
          <p class="pad_bot3">
				Tarifação atualizada imediata, Rota de menor cust, Sugestão de planos/operadoras
           </p>
           <p class="pad_top1 pad_bot1"><strong class="color1">Comanda Eletrônica</strong></p>
          <p class="pad_bot3">
				Mesa, cartão de consumo ou Apto, Impressão nos pontos de preparo, Rapidez e segurança...
           </p>

           <p class="pad_top1 pad_bot1"><strong class="color1">Integração Contábil</strong></p>
          <p class="pad_bot3">
				Controle: por Empresa/ Filial, por depósitos, em múltiplas unidades de medidas. Recursos: baixas...
           </p>
           <p class="pad_top1 pad_bot1"><strong class="color1"></strong></p>
          <a href="features.html" class="button right">Saiba mais</a> </div>
      </section>
    </article>
  </div>
</div>
<div class="body2">
  <div class="main">
    <article id="content2">
      <section class="col1">
        <form id="newsletter" action="#">
        </form>
      </section>
      <section class="col_1"></section>
      <section class="col_2"></section>
      <section class="col_1"></section>
    </article>
    <!-- / content -->
  </div>
</div>
<div class="main">
  <!-- footer -->
  <footer>Copyright &copy; <a href="#">Cosme & Damião Escritório Contabil</a> All Rights Reserved<br>
       <!-- {%FOOTER_LINK} -->
  </footer>
  <!-- / footer -->
</div>
<script type="text/javascript">Cufon.now();</script>
<script type="text/javascript">
$(document).ready(function () {
    $('#myRoundabout').roundabout({
        shape: 'square',
        minScale: 0.89, // tiny!
        maxScale: 1, // tiny!
        easing: 'easeOutExpo',
        clickToFocus: 'true',
        focusBearing: '0',
        duration: 800,
        reflect: true
    });
});
</script>

</html>