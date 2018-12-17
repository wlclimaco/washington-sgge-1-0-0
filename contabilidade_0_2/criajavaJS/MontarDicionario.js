
montDicionario = function (oField) {
    console.log(oField());
    console.log("----------------------------")

    let aModel = [];
    let idEntidade = 1;
    let idTab = 0;
    var text = "";
    aModel = oField();
    for (let x = 0; x < aModel.length; x++) {
        let oClasses = aModel[x];
        // if (oClasses.interfaces.toLowerCase() == 'site') {
        for (let i = 0; i < oClasses.classes.length; i++) {
            let model = oClasses.classes[i];
            text = text + 'INSERT INTO ENTIDADE (ENTIDADE_ID,NOME,DESCRICAO) VALUE ';
            text = text + "(" + x + ",'" + model.classe + "','" + model.classe + "_descrição');\n";
            text = text + "\n";

            text = text + 'INSERT INTO PAGINA (PAGINA_ID,PAGINA,ENTIDADE_ID,STATUS) VALUE ';
            text = text + "(" + x + ",'" + model.classe + "'," + x + ",0);\n";
            text = text + "\n";
            for (let y = 0; y < model.model.length; y++) {
                oCampo = model.model[y].field;
                if (y == 0) {
                    idTab++;
                    text = text + 'INSERT INTO TAB (TAB_ID,NOME,LABEL,ORDERS,pagina_id) VALUE ';
                    text = text + "(" + idTab + ",'" + model.classe + "','" + model.classe + "'," + y + "," + x + ");\n";
                    text = text + "\n";

                } else if ((y == 10) || (y == 20) || (y == 30) || (y == 40) || (y == 50) || (y == 60)) {
                    idTab++;
                    text = text + 'INSERT INTO TAB (TAB_ID,NOME,LABEL,ORDERS,pagina_id) VALUE ';
                    text = text + "(" + idTab + ",'" + model.classe + "','" + model.classe + "'," + y + "," + x + ");\n";
                    text = text + "\n";

                }
                text = text + 'INSERT INTO FIELD (FIELD_ID,TAMANHO,TIPO,REQUERID,PRIMARYKEY,FORENKEY,MODEL,XML,NOME,LABEL,entidade_id,tab_id,oculto,ORDERS) VALUE ';
                text = text + "(" + y + ",100,'" + oCampo.tipo + "'," + oCampo.requerid + "," + oCampo.primaryKey + "," + oCampo.forenkey + "," + oCampo.model + ",'" + oCampo.campo + "'," + x + "," + idTab + ",false," + y + ");\n";
            }
        }
        //   }

    }
    return text;
}

