
domain = function (oField,name){


    var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';

    text = text + 'qat.model.fn'+name+' =function(_'+name+',modelAction)\n';
    text = text + '{\n';    
    text = text + '\n';
    text = text + '     var _id = null;\n';
    text = text + '     if(_'+name+'.id == "" || _'+name+'.id == " "){\n';
    text = text + '         _id = null;\n';
    text = text + '     }\n';
    text = text + '     var _emprId = null;\n';
    text = text + "     if(localStorage.getItem('empresa') == null || localStorage.getItem('empresa') == ''){\n";
    text = text + '         _emprId = null;\n';
    text = text + '     }else{\n';
    text = text + "         _emprId = JSON.parse(localStorage.getItem('empresa')).id;\n";
    text = text + '     }\n';
    text = text + '     '+name+'  = {\n';
    text = text + '             id : _id,\n';

    for(i=0;i < oField.length;i++){
        if(oField[i].field.campo != 'id')
        text = text + '             '+oField[i].field.campo+' : _'+name+'.'+oField[i].field.campo+',\n';
               
    }

    text = text + '             parentId       : 0,\n';
    text = text + '             emprId         : _emprId,\n';
    text = text + '             processId      : 0,\n';
    text = text + '             tableEnumValue : 0,\n';
    text = text + '             modelAction    : modelAction,\n';
    text = text + '             createUser     : "System",\n';
    text = text + '             createDateUTC  : (new Date()).getTime(),\n';
    text = text + '             modifyUser     : "System",\n';
    text = text + '             modifyDateUTC  : (new Date()).getTime()\n';
    text = text + '\n';
    text = text + '     }\n';
    text = text + '     return '+name+';\n';
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

