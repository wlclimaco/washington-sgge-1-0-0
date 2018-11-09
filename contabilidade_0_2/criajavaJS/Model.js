function titleize(text) {

    // Convertendo primeira letra em maiuscula.
    text = text.charAt(0).toUpperCase() + text.slice(1);

    for (var i = 0; i < text.length; i++) {
        if (text.charAt(i) === " ") {

            // Convertendo letra ap�s o ESPA�O em maiuscula
            var charToUper = text.charAt(i + 1).toUpperCase();

            // Colocando texto de antes do ESPA�O na vari�vel
            var sliceBegin = text.slice(0, (i + 1));

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


function titleize2(text) {

    // Convertendo primeira letra em maiuscula.
    text = text.charAt(0).toLowerCase() + text.slice(1);

    for (var i = 0; i < text.length; i++) {
        if (text.charAt(i) === " ") {

            // Convertendo letra ap�s o ESPA�O em maiuscula
            var charToUper = text.charAt(i + 1).toLowerCase();

            // Colocando texto de antes do ESPA�O na vari�vel
            var sliceBegin = text.slice(0, (i + 1));

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


model = function(oField, name) {

    var text = '/** create by system gera-java version 1.0.0 ' + dataAtualFormatada() + '*/\n';
    text = text + "package com.nouhoun.springboot.jwt.integration.domain;\n";
    text = text + "\n";
    text = text + "\n";
    text = text + "import javax.persistence.Column;\n";
    text = text + "import javax.persistence.Entity;\n";
    text = text + "import javax.persistence.GeneratedValue;\n";
    text = text + "import javax.persistence.GenerationType;\n";
    text = text + "import javax.persistence.Id;\n";
    text = text + "import javax.persistence.SequenceGenerator;\n";
    text = text + "import javax.persistence.Table;\n";
    text = text + "\n";
    text = text + "/**\n";
    text = text + " * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer\n";
    text = text + " * setting.\n";
    text = text + " */\n";
    text = text + "\n";
    text = text + "@Entity\n";
    text = text + '@Table(name = "' + name.toUpperCase() + '")\n';
    text = text + "public class " + titleize(name) + " \n";
    text = text + "{\n";
    text = text + "\n";

    // requerid: true,
    // primaryKey: true,
    // forenkey: false,
    // model: true,
    // xml: true
    for (i = 0; i < oField.length; i++) {
        if (oField[i].field.xml == true) {
            if (oField[i].field.tipo !== undefined) {
                text = text + '    /** The econtabil ' + titleize2(oField[i].field.campo) + ' for the ' + name + '. */\n';
                if ((oField[i].field.tipo.indexOf('List') == -1)) {
                    if (oField[i].field.primaryKey) {
                        text = text + "    @Id\n";
                        text = text + '    @Column(name = "' + name.toUpperCase() + '_' + oField[i].field.campo.toUpperCase() + '")\n';
                    } else {
                        text = text + '    @Column(name = "' + oField[i].field.campo.toUpperCase() + '")\n';
                    }
                    if (oField[i].field.createSeq) {
                        text = text + '    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "' + name.toLowerCase() + '_seq")\n';
                        text = text + '    @SequenceGenerator(name = "' + name.toLowerCase() + '_seq", sequenceName = "' + name.toLowerCase() + '_seq", allocationSize = 1)\n';
                    }
                    if (oField[i].field.tipoLigacao && oField[i].field.tipoLigacao.tipo.toLowerCase() == "onetoone") {
                        text = text + '    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)\n'
                        text = text + '    @JoinColumn(name = "' + oField[i].field.tipoLigacao.ligacao + '",insertable = true, unique = false, nullable = false, updatable = true)\n'

                    } else if (oField[i].field.tipoLigacao && oField[i].field.tipoLigacao.tipo.toLowerCase() == "onetomany") {
                        text = text + '    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)\n'
                        text = text + '    @JoinColumn(name="' + oField[i].field.tipoLigacao.ligacao + '", referencedColumnName="' + oField[i].field.tipoLigacao.ligacao + '", nullable = false, insertable = true, updatable = true)\n'
                        text = text + '    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})\n'
                            //todo buxa
                    } else if (oField[i].field.tipoLigacao && oField[i].field.tipoLigacao.tipo.toLowerCase() == "manytomany") {
                        text = text + '    @ManyToMany(cascade = CascadeType.ALL)\n'
                        text = text + '    @JoinTable(name = "empresa_quadra", joinColumns = @JoinColumn(name="empresa_id"), inverseJoinColumns = @JoinColumn(name = "quadra_id"))\n'
                    }
                    text = text + '    private ' + oField[i].field.tipo + ' ' + titleize2(oField[i].field.campo) + ';\n';
                    text = text + "\n";
                } else {
                    if (oField[i].field.tipoLigacao && oField[i].field.tipoLigacao.tipo.toLowerCase() == "onetomany") {
                        text = text + '    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)\n'
                        text = text + '    @JoinColumn(name="chat_id", referencedColumnName="chat_id", nullable = false, insertable = true, updatable = true)\n'
                        text = text + '    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})\n'

                    } else {
                        text = text + '    @ManyToMany(cascade = CascadeType.ALL)\n'
                        text = text + '    @JoinTable(name = "empresa_quadra", joinColumns = @JoinColumn(name="empresa_id"), inverseJoinColumns = @JoinColumn(name = "quadra_id"))\n'
                    }
                    text = text + '    private ' + oField[i].field.tipo + ' ' + titleize2(oField[i].field.campo) + ';\n';
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
                if ((oField[i].field.tipo.indexOf('List') == -1)) {
                    text = text + "    /**\n";
                    text = text + "     * Gets the " + titleize2(oField[i].field.campo) + ".\n";
                    text = text + "     *\n";
                    text = text + "     * @return the " + titleize2(oField[i].field.campo) + "\n";
                    text = text + "     */\n";
                    text = text + "    public " + titleize(oField[i].field.tipo) + " get" + titleize(oField[i].field.campo) + "()\n";
                    text = text + "    {\n";
                    text = text + "        return " + titleize2(oField[i].field.campo) + ";\n";
                    text = text + "    }\n";
                    text = text + "\n";
                    text = text + "    /**\n";
                    text = text + "     * Sets the " + oField[i].field.campo.toLowerCase() + ".\n";
                    text = text + "     *\n";
                    text = text + "* @param id the " + oField[i].field.campo.toLowerCase() + " to set\n";
                    text = text + " */\n";
                    text = text + "    public void set" + titleize(oField[i].field.campo) + "(" + titleize(oField[i].field.tipo) + " " + oField[i].field.campo.toLowerCase() + ")\n";
                    text = text + "    {\n";
                    text = text + "        this." + titleize2(oField[i].field.campo) + " = " + oField[i].field.campo.toLowerCase() + ";\n";
                    text = text + "    }\n";
                    text = text + "\n";
                } else {
                    text = text + "    /**\n";
                    text = text + "     * Gets the " + oField[i].field.campo.toLowerCase() + ".\n";
                    text = text + "     *\n";
                    text = text + "     * @return the " + oField[i].field.campo.toLowerCase() + "\n";
                    text = text + "     */\n";
                    text = text + "    public " + titleize(oField[i].field.tipo) + " get" + titleize(oField[i].field.campo) + "()\n";
                    text = text + "    {\n";
                    text = text + "        return " + titleize2(oField[i].field.campo) + ";\n";
                    text = text + "    }\n";
                    text = text + "\n";
                    text = text + "    /**\n";
                    text = text + "     * Sets the " + oField[i].field.campo.toLowerCase() + ".\n";
                    text = text + "     *\n";
                    text = text + "* @param id the " + oField[i].field.campo.toLowerCase() + " to set\n";
                    text = text + " */\n";
                    text = text + "public void set" + titleize(oField[i].field.campo) + "(" + titleize(oField[i].field.tipo) + " " + oField[i].field.campo.toLowerCase() + ")\n";
                    text = text + "{\n";
                    text = text + "        this." + titleize2(oField[i].field.campo) + " = " + oField[i].field.campo.toLowerCase() + ";\n";
                    text = text + "    }\n";
                    text = text + "\n";

                }
            }
        }

    }

    // text = text + "\n";
    // text = text + "\n";
    // text = text + "\n";
    // text = text + "    @Override\n";
    // text = text + "    public String toString() {\n";
    // text = text + '        return "' + titleize(name) + ' [\n';

    // var x = 0
    // for (i = 0; i < oField.length; i++) {
    //     if (oField[i].field.xml == true) {
    //         if (oField[i].field.tipo !== undefined) {
    //             if (x < 5) {
    //                 text = text + ' get' + titleize(oField[i].field.campo) + '()=" + get' + titleize(oField[i].field.campo) + '() + ",';
    //                 x++;
    //             } else {
    //                 text = text + ' get' + titleize(oField[i].field.campo) + '()=" + get' + titleize(oField[i].field.campo) + '() + ",\n';
    //                 x = 0

    //             }
    //         }
    //     }

    // }

    // text = text + '                 + toString()=super.toString() + "]";\n';
    // text = text + '     }\n';
    // text = text + ' \n';
    // text = text + ' \n';
    text = text + ' }\n';


    return text;
}