
function titleize(text) {

    // Convertendo primeira letra em maiuscula.
    text = text.charAt(0).toUpperCase() + text.slice(1);

    for (var i = 0; i < text.length; i++) {
        if (text.charAt(i) ===" ") {

            // Convertendo letra ap�s o ESPA�O em maiuscula
            var charToUper = text.charAt(i+1).toUpperCase();

            // Colocando texto de antes do ESPA�O na vari�vel
            var sliceBegin = text.slice(0, (i+1));

            // colocando o texto de depois do ESPA�O na vari�vel
            var sliceEnd = text.slice(i + 2);

            // Juntando tudo
            text = sliceBegin + charToUper + sliceEnd;

        } else {

            // NAO CONSIGO PENSAR EM COMO TRANSFORMAR O RESTANTE DAS LETRAS EM MINUSCULA
        }
    }
    return text;
}

model = function (oField, name){

var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';
text = text + "package com.qat.samples.sysmgmt."+titleize(name)+".model;\n";
text = text + "\n";
text = text + "\n";
text = text + "import java.util.List;\n";
text = text + "\n";
text = text + "import com.qat.samples.sysmgmt.cfop.model.Cfop;\n";
text = text + "import com.qat.samples.sysmgmt.condpag.model.FormaPg;\n";
text = text + "import com.qat.samples.sysmgmt.condpag.model.FormaPgPessoa;\n";
text = text + "import com.qat.samples.sysmgmt.conta.model.Conta;\n";
text = text + "import com.qat.samples.sysmgmt.entidade.model.Entidade;\n";
text = text + "import com.qat.samples.sysmgmt.pessoa.model.Pessoa;\n";
text = text + "import com.qat.samples.sysmgmt.pessoa.model.Transportador;\n";
text = text + "import com.qat.samples.sysmgmt.produto.model.ItensEspeciais;\n";
text = text + "import com.qat.samples.sysmgmt.produto.model.Tributacao;\n";
text = text + "import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;\n";
text = text + "import com.qat.samples.sysmgmt.util.model.Note;\n";
text = text + "\n";
text = text + "/**\n";
text = text + " * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer\n";
text = text + " * setting.\n";
text = text + " */\n";
text = text + "\n";
text = text + '@SuppressWarnings("serial")\n';
text = text + "public class "+titleize(name)+" extends ModelCosmeDamiao\n";
text = text + "{\n";
text = text + "\n";


for (i = 0; i < oField.length; i++) {
        if (oField[i].field.xml == true) {
            if (oField[i].field.tipo !== undefined) {
                text = text + '    /** The econtabil ' + oField[i].field.campo + ' for the ' + name + '. */\n';
                if ((oField[i].field.tipo.indexOf('List') == -1)) {
                    text = text + '    private ' + oField[i].field.tipo + ' ' + oField[i].field.campo.toLowerCase() + ';\n';
                    text = text + "\n";
                } else {
                    text = text + '    private List<' + oField[i].field.tipo + '> ' + oField[i].field.campo.toLowerCase() + ';\n';
                    text = text + "\n";
                }
            }
        }

    }

text = text + "\n";
text = text + "\n";
text = text + "    /**\n";
text = text + "     * Default constructor.\n";
text = text + "     */\n";
text = text + "    public " + name + "()\n";
text = text + "    {\n";
text = text + "        super();\n";
text = text + "    }\n";
text = text + "\n";
text = text + "\n";

for (i = 0; i < oField.length; i++) {
        if (oField[i].field.xml == true) {
            if (oField[i].field.tipo !== undefined) {
                text = text + "    /**\n";
                text = text + "     * Gets the " + oField[i].field.campo.toLowerCase() + ".\n";
                text = text + "     *\n";
                text = text + "     * @return the " + oField[i].field.campo.toLowerCase() + "\n";
                text = text + "     */\n";
                if ((oField[i].field.tipo.indexOf('List') == -1)) {
                        text = text + "    /**\n";
                        text = text + "     * Gets the " + oField[i].field.campo.toLowerCase() + ".\n";
                        text = text + "     *\n";
                        text = text + "     * @return the " + oField[i].field.campo.toLowerCase() + "\n";
                        text = text + "     */\n";
                        text = text + "    public "+titleize(oField[i].field.tipo)+" get"+titleize(oField[i].field.campo)+"()\n";
                        text = text + "    {\n";
                        text = text + "        return " + oField[i].field.campo.toLowerCase() + ";\n";
                        text = text + "    }\n";
                        text = text + "\n";
                        text = text + "    /**\n";
                        text = text + "     * Sets the " + oField[i].field.campo.toLowerCase() + ".\n";
                        text = text + "     *\n";
                        text = text + "* @param id the " + oField[i].field.campo.toLowerCase() + " to set\n";
                        text = text + " */\n";
                        text = text + "public void set"+titleize(oField[i].field.campo)+"("+titleize(oField[i].field.tipo)+" " + oField[i].field.campo.toLowerCase() + ")\n";
                        text = text + "{\n";
                        text = text + "        this." + oField[i].field.campo.toLowerCase() + " = " + oField[i].field.campo.toLowerCase() + ";\n";
                        text = text + "    }\n";
                        text = text + "\n";
                    } else {
                        text = text + "    /**\n";
                        text = text + "     * Gets the " + oField[i].field.campo.toLowerCase() + ".\n";
                        text = text + "     *\n";
                        text = text + "     * @return the " + oField[i].field.campo.toLowerCase() + "\n";
                        text = text + "     */\n";
                        text = text + "    public List<"+titleize(oField[i].field.tipo)+"> get"+titleize(oField[i].field.campo)+"()\n";
                        text = text + "    {\n";
                        text = text + "        return " + oField[i].field.campo.toLowerCase() + ";\n";
                        text = text + "    }\n";
                        text = text + "\n";
                        text = text + "    /**\n";
                        text = text + "     * Sets the " + oField[i].field.campo.toLowerCase() + ".\n";
                        text = text + "     *\n";
                        text = text + "* @param id the " + oField[i].field.campo.toLowerCase() + " to set\n";
                        text = text + " */\n";
                        text = text + "public void set"+titleize(oField[i].field.campo)+"(List<"+titleize(oField[i].field.tipo)+"> " + oField[i].field.campo.toLowerCase() + ")\n";
                        text = text + "{\n";
                        text = text + "        this." + oField[i].field.campo.toLowerCase() + " = " + oField[i].field.campo.toLowerCase() + ";\n";
                        text = text + "    }\n";
                        text = text + "\n";

                }
            }
        }

    }

text = text + "\n";
text = text + "\n";
text = text + "\n";
text = text + "    @Override\n";
text = text + "    public String toString() {\n";
text = text + '        return "'+titleize(name)+' [\n';

var  x = 0
for (i = 0; i < oField.length; i++) {
        if (oField[i].field.xml == true) {
            if (oField[i].field.tipo !== undefined) {
                if(x < 5)
                {
                    text = text + ' get' + titleize(oField[i].field.campo) + '()=" + get' + titleize(oField[i].field.campo) + '() + ",';
                    x++;
                }else{
                    text = text + ' get' + titleize(oField[i].field.campo) + '()=" + get' + titleize(oField[i].field.campo) + '() + ",\n';
                    x = 0

                }
            }
        }

    }

text = text + '                 + toString()=super.toString() + "]";\n';
text = text + '     }\n';
text = text + ' \n';
text = text + ' \n';
text = text + ' }\n';


return text;
}
