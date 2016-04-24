
function titleize(text) {

    // Convertendo primeira letra em maiuscula.
    text = text.charAt(0).toUpperCase() + text.slice(1);

    for (var i = 0; i < text.length; i++) {
        if (text.charAt(i) ===" ") {

            // Convertendo letra após o ESPAÇO em maiuscula
            var charToUper = text.charAt(i+1).toUpperCase();

            // Colocando texto de antes do ESPAÇO na variável
            var sliceBegin = text.slice(0, (i+1));

            // colocando o texto de depois do ESPAÇO na variável
            var sliceEnd = text.slice(i + 2);

            // Juntando tudo
            text = sliceBegin + charToUper + sliceEnd;

        } else {

            // NAO CONSIGO PENSAR EM COMO TRANSFORMAR O RESTANTE DAS LETRAS EM MINUSCULA
        }   
    }
    return text;
}

b_Insert = function (table,oField,total){
	var text = "";
    var a ="";
	if((total > 0 )&&(total !== undefined)){
		for(var x=0;x < total;x++){
			for(i=0;i < oField.length;i++){
				if(oField[i].field.xml == true){
					if(oField[i].field.tipo.indexOf('List') == -1){
						a = a + ' '+oField[i].field.campo+',';
					}
				}
				
			}
			//debugger
			text = text + "\n";	
			text = text + 'INSERT INTO '+table+'('+a+'create_user,create_date,modify_user,modify_date)values\n';
			a ="";
			b = new Date();
			for(i=0;i < oField.length;i++){
				if(oField[i].field.xml == true){
					if(oField[i].field.tipo.indexOf('List') == -1){
	
						if((oField[i].field.campo.toLowerCase().indexOf('data') > -1)||((oField[i].field.campo.toLowerCase().indexOf('date') > -1))){
							
							a = a + " "+b.getTime()+",";
						}else if((oField[i].field.tipo.toLowerCase() !== 'integer')&&(oField[i].field.tipo.toLowerCase() !== 'double')&&(oField[i].field.tipo.toLowerCase() !== 'long')){
							a = a + " '"+oField[i].field.campo+"_"+i+"',";
						}else{
							a = a + ' '+(x+1)+',';
						}
					}
				}
				
			}

			text = text + "("+a+"'system',"+b.getTime()+",'rod',"+b.getTime()+");\n";
			a ="";
		}
	}
	//if(text[text.length-1] == ','){
//		text = (text.substr(0, text.length - 1));
//	}
return text;
}
b_Table = function (table,oField){


	var text = "";
var a = "";
for(i=0;i < oField.length;i++){
	if(oField[i].field.xml == true){
		if(oField[i].field.tipo.indexOf('List') == -1){
			if(oField[i].field.campo.toLowerCase() !== 'id'){
				if(oField[i].field.requerid == true){
					a = a + oField[i].field.campo+' '+convertBanco(oField[i].field.tipo,100)+' NOT NULL,\n'
				}else{
					a = a + oField[i].field.campo+' '+convertBanco(oField[i].field.tipo,100)+' ,\n';
				}
			}
		}
	}
}	
	
text = text + "DROP SEQUENCE "+table.toLowerCase()+"_id_seq;\n"
text = text + "\n"
text = text + "CREATE SEQUENCE "+table.toLowerCase()+"_id_seq\n"
text = text + "INCREMENT 1\n"
text = text + "MINVALUE 1\n"
text = text + "MAXVALUE 9223372036854775807\n"
text = text + "START 1\n"
text = text + "CACHE 1;\n"
text = text + "ALTER TABLE cnae_id_seq\n"
text = text + "OWNER TO qat;\n"
text = text + "\n"
text = text + "\n"
text = text + "DROP TABLE  "+table.toLowerCase()+";\n"
text = text + "\n"
text = text + "CREATE TABLE "+table.toLowerCase()+"(\n"
text = text + "    id           integer NOT NULL DEFAULT nextval('"+table.toLowerCase()+"_id_seq'::regclass),\n"
text = text + " "+a+" \n"
text = text + "create_date  bigint,\n"
text = text + "create_user  character varying(50) NULL,\n"
text = text + "modify_date  bigint,\n"
text = text + "modify_user  character varying(50) NULL,\n"
text = text + "CONSTRAINT "+table.toLowerCase()+"_pkey PRIMARY KEY (id)\n"
text = text + ")\n"
text = text + "WITH (\n"
text = text + "OIDS=FALSE\n"
text = text + ");\n"
text = text + "ALTER TABLE "+table.toLowerCase()+"\n"
text = text + "  OWNER TO qat;\n"

return text;

}
b_Delete = function (teste,bar,local){

}
b_Select = function (teste,bar,local){

}
