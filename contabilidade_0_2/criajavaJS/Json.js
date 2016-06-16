
json = function (oTeste,quant){

debugger
	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';
text = text + "[\n"

text = text + "\n";
for(x=0;x < quant;x++){
	text = text + "{\n"
	for(i=0;i < oTeste.length;i++){
		text = text + '    "'+oTeste[i].field.campo+'": "'+oTeste[i].field.campo+'_'+x+'",\n';
	}
	text = text + "},\n"
}
text = text + "]\n";
return text;
}


otable = function (oTeste){

var text = '';
	for(i=0;i < oTeste.length;i++){
		text = text + "DTColumnBuilder.newColumn('"+oTeste[i].field.campo+"').withTitle('"+oTeste[i].field.campo+"'),\n";
	}

return text;
}