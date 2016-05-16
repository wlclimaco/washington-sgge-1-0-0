package com.qat.samples.sysmgmt.nf.model.classes.nota;

import com.qat.samples.sysmgmt.nf.model.classes.NFBase;

public class NFPessoaAutorizadaDownloadNFe extends NFBase {
    //@Element(name = "CNPJ", required = false)
    private String cnpj;

    //@Element(name = "CPF", required = false)
    private String cpf;

//    public void setCnpj(final String cnpj) {
//        if (this.cpf != null) {
//            throw new IllegalStateException("Nao deve setar CNPJ se CPF esteja setado");
//        }
//        StringValidador.cnpj(cnpj);
//        this.cnpj = cnpj;
//    }
//
//    public void setCpf(final String cpf) {
//        if (this.cnpj != null) {
//            throw new IllegalStateException("Nao deve setar CPF se CNPJ esteja setado");
//        }
//        StringValidador.cpf(cpf);
//        this.cpf = cpf;
//    }
}