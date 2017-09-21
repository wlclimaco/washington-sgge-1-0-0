
domain = function (oField,name){

    var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';

    text = text + 'qat.model.'+name+' = function(_oObjet, _modelAction, _user, $log)\n';
    text = text + '{\n';    
    text = text + '     if (_oObjet != undefined)\n';   
	text = text + '     {\n';   
    text = text + '\n';
    text = text + '                  this.id = _oObjet.id;\n';
    for(i=0;i < oField.length;i++){
        if(oField[i].field.campo != 'id')
        text = text + '                  this.'+oField[i].field.campo+' = _oObjet.'+oField[i].field.campo+',\n';
               
    }

    text = text + '                  this.parentId = _oObjet.parentId;\n';   
    text = text + "                  this.emprId = JSON.parse(localStorage.getItem('empresa')) ? JSON.parse(localStorage.getItem('empresa')).id : null;\n";   
    text = text + '                  this.processId = _oObjet.processId;\n';   
    text = text + '                  this.tableEnumValue = _oObjet.tableEnumValue;\n';   
    text = text + '                  this.modelAction = _modelAction;\n';   
    text = text + '                  this.createUser = _user;\n';   
    text = text + '                  this.createDateUTC = (new Date()).getTime();\n';   
    text = text + '                  this.modifyUser = _user;\n';   
    text = text + '                  this.modifyDateUTC = (new Date()).getTime();\n';     
    text = text + '\n';   
    text = text + '                  if($log)\n';   
    text = text + '                           $log.info("add '+name+' --> " +  _oObjet.id,"Teste");\n';   
    text = text + '     }\n';  
    text = text + '}\n';
    text = text + '\n';

return text;
}

domainTest = function (oField,name){
    var a ="";
    var b = new Date();
    var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';
    text = text + '\n';
    text = text + '//'+name+' Object\n';
    text = text + 'var _'+name+' = []\n';
    text = text + '\n';
    for(i=0;i < oField.length;i++){
        if(oField[i].field.tipo.indexOf('List') > -1){

                text = text + '     _'+name+'.'+oField[i].field.campo+' = '+name+'\n';
            }else{
                    var a ="";
                    if((oField[i].field.campo.toLowerCase().indexOf('data') > -1)||((oField[i].field.campo.toLowerCase().indexOf('date') > -1))){
                    a = a + " "+b.getTime()+",";
                    }else if((oField[i].field.tipo.toLowerCase() !== 'integer')&&(oField[i].field.tipo.toLowerCase() !== 'double')&&(oField[i].field.tipo.toLowerCase() !== 'long')&&(oField[i].field.tipo.toLowerCase() !== 'boolean')){
                        a = a + " '"+oField[i].field.campo+"_"+i+"',";
                    }else if(oField[i].field.tipo.toLowerCase() === 'boolean'){
                        a = a + 'true,'
                    }else{
                        a = a + ' '+(i+1)+',';
                    }
                text = text + '     _'+name+'.'+oField[i].field.campo+' = '+a+'\n';
            }               
    }
    text = text + '     _'+name+'.parentId       = 1;\n';
    text = text + '     _'+name+'.emprId         = 1;\n';
    text = text + '     _'+name+'.processId      = 1;\n';
    text = text + '     _'+name+'.tableEnumValue = 1;\n';
    text = text + '     _'+name+'.modelAction    = "INSERT";\n';
    text = text + '     _'+name+'.createUser     = "rod";\n';
    text = text + '     _'+name+'.createDateUTC  = '+(new Date()).getTime()+';\n';
    text = text + '     _'+name+'.modifyUser     = "rod"\n';
    text = text + '     _'+name+'.modifyDateUTC  = '+(new Date()).getTime()+';\n';
    text = text + '\n';
return text;
}

