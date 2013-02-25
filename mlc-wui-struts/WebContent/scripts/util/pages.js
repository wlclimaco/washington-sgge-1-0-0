sensus.util.pages = {

		fnConfiguracao : function(DialogWidth,DialogHeight,tabWidth,tabHeight,title){
			$('.ui-dialog').css({ width:" "+DialogWidth+"px"});
			$('.ui-dialog').css({ height:" "+DialogHeight+"px"});
			$('#action-dialog').css({ width:" "+tabWidth+"px"});
			$('#action-dialog').css({ height:" "+tabHeight+"px"});
			$('#ui-dialog-title-action-dialog').text(title);
			$('#tabs').css({ width:" "+parseInt(tabWidth)-10+"px"});
			$('#tabs').css({ height:" "+parseInt(tabHeight)-10+"px"});
		},

		fnTypeInput : function(requerid,positionY,positionX,label,name,tamanho){
			var sTableLines="";
				if(requerid){
					sTableLines +='<span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(positionY)+'px;top:'+positionX+'px;width:250px;height:16px;""><strong>'+label+'</strong></span>  <input type="text" id="id'+name+'"style="position:absolute;left:'+positionY+'px;top:'+(positionX + 20)+'px;width:'+(tamanho)+'px;height:16px;border-style:solid;border-color:#ff0000 #ff0000;" name="'+name+'">';
				}else{
					sTableLines +='<span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(positionY)+'px;top:'+positionX+'px;width:250px;height:16px;""><strong>'+label+'</strong></span>  <input type="text" id="id'+name+'"style="position:absolute;left:'+positionY+'px;top:'+(positionX + 20)+'px;width:'+(tamanho)+'px;height:16px;" name="'+name+'">';
				}
			return sTableLines;
			},

			fnTypeRadio : function(oData){},

			fnTypeCheckbox : function(id,positionY,positionX,label,name,tamanho){
				var sTableLines="";
				sTableLines +='<input type="checkbox" id="Checkbox'+id+'" name="" value="on" style="position:absolute;left:'+positionY+'px;top:'+positionX+'px;"><span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(positionY+30)+'px;top:'+positionX+'px;width:250px;height:16px;""><strong>'+label+'</strong></span>';
			return sTableLines;
			},

			fnTypeCombo : function(id,positionY,positionX,label,name,tamanho,dominio){
			var sTableLines="";
				sTableLines +='<span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(positionY)+'px;top:'+positionX+'px;width:250px;height:16px;""><strong>'+label+'</strong></span><select name="Combobox'+id+'" size="1" id="Combobox'+id+'" style="position:absolute;left:'+positionY+'px;top:'+(positionX+20)+'px;width:'+tamanho+'px;height:21px;">';
					var dominios = dominio.split(',');
					for(var i= 0;dominios.length > i ;i++){
						var values = dominios[i].split('|');
							sTableLines +=	'<option value="'+values[0]+'">'+values[1]+'</option>';
						}
					sTableLines +='</select>';
			return sTableLines;
			},

			fnTypeTextArea : function(id,positionY,positionX,label,name){},

			fnTypeLabel : function(id,positionY,positionX,label,name){
				var sTableLines="";
				sTableLines += '<span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(positionY)+'px;top:'+positionX+'px;width:250px;height:16px;"><strong>'+label+'</strong></span>"'
				return sTableLines;
			},

			fnTypeDataPicker : function(id,positionY,positionX,label,name,tamanho){
				var sTableLines="";
				sTableLines +='<span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(positionY)+'px;top:'+positionX+'px;width:250px;height:16px;""><strong>'+label+'</strong></span>  <input type="text" class="datepicker"id="id'+name+'"style="position:absolute;left:'+positionY+'px;top:'+(positionX + 20)+'px;width:'+(tamanho)+'px;height:16px;" name="Editbox'+name+'">';
				return sTableLines;
			},

			fnTypeDataTime : function(id,positionY,positionX,label,name,tamanho){
				var sTableLines="";
					sTableLines +='<span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(positionY)+'px;top:'+positionX+'px;width:250px;height:16px;""><strong>'+label+'</strong></span>  <input type="text" class="time"id="id'+name+'"style="position:absolute;left:'+positionY+'px;top:'+(positionX + 20)+'px;width:'+(tamanho)+'px;height:16px;" name="Editbox'+name+'">';
				return sTableLines;
			},
			fnBuscar : function (requerid,positionY,positionX,label,name,tamanho){
				var sTableLines="";
					sTableLines +='<span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(positionY)+'px;top:'+positionX+'px;width:250px;height:16px;""><strong>'+label+'</strong></span>  <input type="text" id="id'+name+'"style="position:absolute;left:'+positionY+'px;top:'+(positionX + 20)+'px;width:'+(tamanho)+'px;height:16px;" name="'+name+'"><button class="buttonBusca" style="position:absolute;left:'+(positionY  + tamanho + 5)+'px;top:'+(positionX+20)+'px;width:'+(18)+'px;height:18px;">Button with icon only</button>';
				return sTableLines;
			},



			fnTabAbs : function(id,width,height,positionY,positionX,title){

			var sTableLines='';
			sTableLines += '<div id="tabs" >';
			sTableLines += '	<ul>';
			sTableLines += '		<li class="hide"><a href="#tabs-1">Nunc tincidunt</a></li>';
			sTableLines += '	</ul>';
			sTableLines += '	<div id="tabs-1" class="hide">';
			sTableLines += '		<p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.</p>';
			sTableLines += '	</div>';
			sTableLines += '</div>';
			return sTableLines;

			},
		fnCreateAbs : function(title,conteudo,id){
		var test = '<div id="'+id+'" class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">';
			test+=conteudo;
			test+='</div>';
				$('#tabs ul').find('li:last').before('<li class="ui-state-default ui-corner-top"><a href="#'+id+'">'+title+'</a></li>');
				$("#tabs div:last").before(test)//'conteudo<div id="'+id+'" class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide"></div>');
				//$('#'+id).append(conteudo);

		},

		createPage : function(codpag,form,action) {
			if(codpag == 1){
			sensus.util.pages.fnConfiguracao(500,500,450,450,"Empresa");
				var sTableLines="";
					sTableLines += sensus.util.pages.fnTabAbs('teste',500,500,10,20,'teste');
				$('.summary').empty();
				$('.summary').append(sTableLines);
				sTableLines = "";
				sTableLines +=sensus.util.pages.fnTypeInput(true,10,50,'Cod Emp','name1',80);
				sTableLines +=sensus.util.pages.fnTypeInput(true,100,50,'Razão Social da Empresa','name2',290);
				sTableLines +=sensus.util.pages.fnTypeInput(false,400,50,'Nome Fantasia','name3',255);
				sTableLines +=sensus.util.pages.fnTypeInput(true,10,90,'Cnpj','name4',118);
				sTableLines +=sensus.util.pages.fnTypeInput(false,136,90,'Inscrição Estadual','name5',255);
				sTableLines +=sensus.util.pages.fnTypeInput(false,400,90,'Inscrição Municipal','name8',255);
				sTableLines +=sensus.util.pages.fnBuscar(false,10,130,'Cep','name12',80);
				sTableLines +=sensus.util.pages.fnTypeInput(false,120,130,'Logradouro','name9',250);
				sTableLines +=sensus.util.pages.fnTypeInput(false,377,130,'Numero','name9',90);
				sTableLines +=sensus.util.pages.fnTypeInput(false,476,130,'Complemento','name9',178);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,170,'Bairro','name9',150);
				sTableLines +=sensus.util.pages.fnTypeInput(false,170,170,'Cidade','name9',200);
				sTableLines +=sensus.util.pages.fnTypeInput(false,379,170,'UF','name9',50);
				sTableLines +=sensus.util.pages.fnTypeInput(false,437,170,'Pagina WEB','name9',218);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,210,'Telefone','name9',115);
				sTableLines +=sensus.util.pages.fnTypeInput(false,140,210,'Fax','name9',115);
				sTableLines +=sensus.util.pages.fnTypeInput(false,264,210,'Email','name9',190);
				sTableLines +=sensus.util.pages.fnTypeInput(false,464,210,'Email NFe','name9',190);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,250,'Cod. EAN','name9',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,137,250,'C. Pais','name9',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,264,250,'%ISS','name9',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,393,250,'Contato','name9',262);
				sTableLines +=sensus.util.pages.fnTypeCheckbox('name10',10,290,'Intercambio de almox entre filiais ?','Checkbox',50);
/* 				sTableLines +=sensus.util.pages.fnTypeCombo('nome11',10,210,'Combo','Combo',100,'1|Teste1,2|Teste2,3|Teste3');
				sTableLines +=sensus.util.pages.fnTypeDataPicker(1,10,240,"Date",'Date',100);
				sTableLines +=sensus.util.pages.fnTypeDataTime(1,10,270,"Date",'Date',100);
				sTableLines +=sensus.util.pages.fnBuscar(false,10,310,'name12','name12',100);
				sTableLines +=sensus.util.pages.fnBuscar(false,120,310,'name12','name12',100); */
				sensus.util.pages.fnCreateAbs('Geral',sTableLines,'Geral');
				sTableLines = "";
				sTableLines +=sensus.util.pages.fnTypeInput(true,10,50,'Cod Filial','name1',80);
				sTableLines +=sensus.util.pages.fnTypeInput(true,100,50,'Razão Social da Filial','name2',290);
				sTableLines +=sensus.util.pages.fnTypeInput(false,400,50,'Nome Fantasia da filial','name3',255);
				sTableLines +=sensus.util.pages.fnTypeInput(true,10,90,'Cnpj','name4',118);
				sTableLines +=sensus.util.pages.fnTypeInput(false,136,90,'Inscrição Estadual','name5',255);
				sTableLines +=sensus.util.pages.fnTypeInput(false,400,90,'Inscrição Municipal','name8',255);
				sTableLines +=sensus.util.pages.fnBuscar(false,10,130,'Cep','name12',80);
				sTableLines +=sensus.util.pages.fnTypeInput(false,120,130,'Logradouro','name9',250);
				sTableLines +=sensus.util.pages.fnTypeInput(false,377,130,'Numero','name9',90);
				sTableLines +=sensus.util.pages.fnTypeInput(false,476,130,'Complemento','name9',178);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,170,'Bairro','name9',150);
				sTableLines +=sensus.util.pages.fnTypeInput(false,170,170,'Cidade','name9',200);
				sTableLines +=sensus.util.pages.fnTypeInput(false,379,170,'UF','name9',50);
				sTableLines +=sensus.util.pages.fnTypeInput(false,437,170,'Pagina WEB','name9',218);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,210,'Telefone','name9',115);
				sTableLines +=sensus.util.pages.fnTypeInput(false,140,210,'Fax','name9',115);
				sTableLines +=sensus.util.pages.fnTypeInput(false,264,210,'Email','name9',190);
				sTableLines +=sensus.util.pages.fnTypeInput(false,464,210,'Email NFe','name9',190);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,250,'C. dist Fil','name9',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,137,250,'PIS','name9',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,264,250,'CONFINS','name9',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,290,'IR','name9',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,137,290,'Cont.Social','name9',120);
				sTableLines +=sensus.util.pages.fnTypeLabel('sede',400,250,'Sede','Sede');
				sTableLines +=sensus.util.pages.fnTypeCheckbox('name10',400,270,'Matriz','Checkbox',50);
				sTableLines +=sensus.util.pages.fnTypeLabel('Fiscal',400,290,'Fiscal','Fiscal');
				sTableLines +=sensus.util.pages.fnTypeCheckbox('name10',400,310,'Simples','Checkbox',50);

				sensus.util.pages.fnCreateAbs('Filial',sTableLines,'Filial');
				$("#tabs").tabs();
				$("#tabs").tabs( "refresh" );
				sensus.util.pages.fnConfiguracao(700,450,680,350,"Teste");

			}else if(codpag == 2) {
				$("#action-dialog").summary(1, 3,"update");
				sensus.util.pages.fnConfiguracao(500,500,450,450,"Empresa");
				var sTableLines="";

					sTableLines += sensus.util.pages.fnTabAbs('Empresa',500,500,10,20,'Empresa');
				$('.summary').empty();
				$('.summary').append(sTableLines);
				sTableLines = "";
				sTableLines += '<form id="'+form+'" name="'+form+'" method="post" action="'+action+'" enctype="multipart/form-data">';
				sTableLines +=sensus.util.pages.fnTypeInput(true,10,50,'Cod Emp','codEmp',80);
				sTableLines +=sensus.util.pages.fnTypeInput(true,100,50,'Razão Social da Empresa','razSocEmp',290);
				sTableLines +=sensus.util.pages.fnTypeInput(false,400,50,'Nome Fantasia','nomFant',255);
				sTableLines +=sensus.util.pages.fnTypeInput(true,10,90,'Cnpj','cnpj',118);
				sTableLines +=sensus.util.pages.fnTypeInput(false,136,90,'Inscrição Estadual','insEst',255);
				sTableLines +=sensus.util.pages.fnTypeInput(false,400,90,'Inscrição Municipal','insMun',255);
				sTableLines +=sensus.util.pages.fnBuscar(false,10,130,'Cep','cep',80);
				sTableLines +=sensus.util.pages.fnTypeInput(false,120,130,'Logradouro','logradouro',250);
				sTableLines +=sensus.util.pages.fnTypeInput(false,377,130,'Numero','numero',90);
				sTableLines +=sensus.util.pages.fnTypeInput(false,476,130,'Complemento','complemento',178);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,170,'Bairro','bairro',150);
				sTableLines +=sensus.util.pages.fnTypeInput(false,170,170,'Cidade','cidade',200);
				sTableLines +=sensus.util.pages.fnTypeInput(false,379,170,'UF','uf',50);
				sTableLines +=sensus.util.pages.fnTypeInput(false,437,170,'Pagina WEB','paginaWEB',218);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,210,'Telefone','telefone',115);
				sTableLines +=sensus.util.pages.fnTypeInput(false,140,210,'Fax','fax',115);
				sTableLines +=sensus.util.pages.fnTypeInput(false,264,210,'Email','email',190);
				sTableLines +=sensus.util.pages.fnTypeInput(false,464,210,'Email NFe','emailNFe',190);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,250,'Cod. EAN','cEAN',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,137,250,'C. Pais','cPais',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,264,250,'%ISS','iSS',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,393,250,'Contato','contato',262);
				sTableLines +=sensus.util.pages.fnTypeCheckbox('intalmfil',10,290,'Intercambio de almox entre filiais ?','Checkbox',50);
/* 				sTableLines +=sensus.util.pages.fnTypeCombo('nome11',10,210,'Combo','Combo',100,'1|Teste1,2|Teste2,3|Teste3');
				sTableLines +=sensus.util.pages.fnTypeDataPicker(1,10,240,"Date",'Date',100);
				sTableLines +=sensus.util.pages.fnTypeDataTime(1,10,270,"Date",'Date',100);
				sTableLines +=sensus.util.pages.fnBuscar(false,10,310,'name12','name12',100);
				sTableLines +=sensus.util.pages.fnBuscar(false,120,310,'name12','name12',100); */
				sTableLines += '</form>';
				sensus.util.pages.fnCreateAbs('Geral',sTableLines,'Geral');
				$("#tabs").tabs();
				$("#tabs").tabs( "refresh" );
				sensus.util.pages.fnConfiguracao(700,450,680,350,"Empresa");

			} else if(codpag == 3){
				$("#action-dialog").summary(1, 3,"update");
				sensus.util.pages.fnConfiguracao(500,500,450,450,"Filial");
				var sTableLines="";
					sTableLines += sensus.util.pages.fnTabAbs('Filial',500,500,10,20,'Filial');
				$('.summary').empty();
				$('.summary').append(sTableLines);
				sTableLines = "";
				sTableLines += '<form id="'+form+'" name="'+form+'" method="post" action="'+action+'" enctype="multipart/form-data">';
				sTableLines +=sensus.util.pages.fnTypeInput(true,10,50,'Cod Filial','CodFilial',40);
				sTableLines +=sensus.util.pages.fnBuscar(false,60,50,'Cod Empresa','codemp',40);
				sTableLines +=sensus.util.pages.fnTypeInput(true,140,50,'Razão Social da Filial','razfilial',250);
				sTableLines +=sensus.util.pages.fnTypeInput(false,400,50,'Nome Fantasia da filial','nomefilial',255);
				sTableLines +=sensus.util.pages.fnTypeInput(true,10,90,'Cnpj','cnpjfilial',118);
				sTableLines +=sensus.util.pages.fnTypeInput(false,136,90,'Inscrição Estadual','inscfilial',255);
				sTableLines +=sensus.util.pages.fnTypeInput(false,400,90,'Inscrição Municipal','name8',255);
				sTableLines +=sensus.util.pages.fnBuscar(false,10,130,'Cep','cep',80);
				sTableLines +=sensus.util.pages.fnTypeInput(false,120,130,'Logradouro','endereco',250);
				sTableLines +=sensus.util.pages.fnTypeInput(false,377,130,'Numero','numero',90);
				sTableLines +=sensus.util.pages.fnTypeInput(false,476,130,'Complemento','complemento',178);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,170,'Bairro','bairro',150);
				sTableLines +=sensus.util.pages.fnTypeInput(false,170,170,'Cidade','cidade',200);
				sTableLines +=sensus.util.pages.fnTypeInput(false,379,170,'UF','uf',50);
				sTableLines +=sensus.util.pages.fnTypeInput(false,437,170,'Pagina WEB','site',218);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,210,'Telefone','fone1',115);
				sTableLines +=sensus.util.pages.fnTypeInput(false,140,210,'Fax','fax',115);
				sTableLines +=sensus.util.pages.fnTypeInput(false,264,210,'Email','email',190);
				sTableLines +=sensus.util.pages.fnTypeInput(false,464,210,'Email NFe','name9',190);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,250,'C. dist Fil','coddistfilial',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,137,250,'PIS','percpisfilial',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,264,250,'CONFINS','perccofinsfilial',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,10,290,'IR','percirfilial',120);
				sTableLines +=sensus.util.pages.fnTypeInput(false,137,290,'Cont.Social','perccsocialfilial',120);
				sTableLines +=sensus.util.pages.fnTypeLabel('sede',400,250,'Sede','Sede');
				sTableLines +=sensus.util.pages.fnTypeCheckbox('sedeMatriz',400,270,'Matriz','Checkbox',50);
				sTableLines +=sensus.util.pages.fnTypeLabel('Fiscal',400,290,'Fiscal','Fiscal');
				sTableLines +=sensus.util.pages.fnTypeCheckbox('simplesfilial',400,310,'Simples','Checkbox',50);
				sTableLines += '</form>';
				sensus.util.pages.fnCreateAbs('Filial',sTableLines,'Filial');
				$("#tabs").tabs();
				$("#tabs").tabs( "refresh" );
				sensus.util.pages.fnConfiguracao(700,450,680,350,"Filial");

			}

			$(function() {
				var tabs = $( "#tabs" ).tabs();
				tabs.find( ".ui-tabs-nav" ).sortable({
					axis: "x",
					stop: function() {
						tabs.tabs( "refresh" );
					}
				});
			});

		 $( ".datepicker" ).datepicker();
		 $('.time').calendricalTime();
		 $( ".buttonBusca" ).button({
            icons: {
                primary: "ui-icon-search"
            },
            text: false
        });
		}


}