////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//              PCN - Projeto Cooperar CTe                                    //
//                                                                            //
//   Descri��o: Classes para gera��o/leitura dos arquivos xml da CTe          //
//                                                                            //
//        site: www.projetocooperar.org/nfe                                   //
//       email: projetocooperar@zipmail.com.br                                //
//       forum: http://br.groups.yahoo.com/group/projeto_cooperar_nfe/        //
//     projeto: http://code.google.com/p/projetocooperar/                     //
//         svn: http://projetocooperar.googlecode.com/svn/trunk/              //
//                                                                            //
// Coordena��o: (c) 2009 - Paulo Casagrande                                   //
//                                                                            //
// Desenvolvimento                                                            //
//         de Cte: Wiliam Zacarias da Silva Rosa                              //
//                                                                            //
//      Equipe: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
//      Vers�o: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
//     Licen�a: GNU Lesser General Public License (GNU LGPL)                  //
//                                                                            //
//              - Este programa � software livre; voc� pode redistribu�-lo    //
//              e/ou modific�-lo sob os termos da Licen�a P�blica Geral GNU,  //
//              conforme publicada pela Free Software Foundation; tanto a     //
//              vers�o 2 da Licen�a como (a seu crit�rio) qualquer vers�o     //
//              mais nova.                                                    //
//                                                                            //
//              - Este programa � distribu�do na expectativa de ser �til,     //
//              mas SEM QUALQUER GARANTIA; sem mesmo a garantia impl�cita de  //
//              COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM       //
//              PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter   //
//              mais detalhes. Voc� deve ter recebido uma c�pia da Licen�a    //
//              P�blica Geral GNU junto com este programa; se n�o, escreva    //
//              para a Free Software Foundation, Inc., 59 Temple Place,       //
//              Suite 330, Boston, MA - 02111-1307, USA ou consulte a         //
//              licen�a oficial em http://www.gnu.org/licenses/gpl.txt        //
//                                                                            //
//    Nota (1): - Esta  licen�a  n�o  concede  o  direito  de  uso  do nome   //
//              "PCN  -  Projeto  Cooperar  NFe", n�o  podendo o mesmo ser    //
//              utilizado sem previa autoriza��o.                             //
//                                                                            //
//    Nota (2): - O uso integral (ou parcial) das units do projeto esta       //
//              condicionado a manuten��o deste cabe�alho junto ao c�digo     //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////

unit pcteModeloCTe;

interface uses

  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao, pcteCTe, pcteCTeW;

procedure ModeloCTe;

implementation

procedure ModeloCTe;
var
//  CTe: TCTe;
//  CTeW: TCTeW;
  i, j, k: integer;
  s: string;
  ReferenciadaTipoCTe: boolean;
  Opcao1: boolean;
begin

  // IMPORTANTE: Os la�os For - Next codificados nesses modelo s�o meramente descritivos.
  //             Esse arquivo � apenas um modelo e deve ser adaptado conforme as suas necessidades.

  Opcao1 := True;      // Esta variavel esta sendo usada nesse modelo para indicar os locais onde
                       // devem ser tomadas deciss�es por parte do programador conforme a regras
                       // de negocio de cada cliente.

//  CTe := TCTe.create;

//  s := CTe.infCTe.ID;  // ATEN��O: Esse campo representa a chave da CTe
                       //          N�o utilize esse campo para escrita (apenas para leitura)
                       //          pois a chave � gerada automaticamente no momento da gera��o do arquivo

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG de grupo das informa��es de identifica��o da NF-e  - <ide> - Ocorr�ncia 1-1                                   *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

{come�o do coment
  CTe.Ide.cUF := 0;                     // B02 - C�digo da UF do emitente do Documento Fiscal - Tabela do IBGE
                                        //         Voc� pode utilizar a fun��o UFparaCodigo caso n�o s�iba o c�digo da UF
                                        //         ex: CTe.Ide.cUF := UFparaCodigo(MinhaUF);
  CTe.Ide.cNF := -1;                    // B03 - C�digo Num�rico que comp�e a Chave de Acesso
                                        //         Se nenhum valor for informado ser� atribuido um valor aleat�rio
                                        //         Se for informado o valor -1; ser� gerado um valor baseado no numero da CTe
  CTe.Ide.natOp := '';                  // B04 - Descri��o da Natureza da Opera��o
  CTe.Ide.indPag := ipVista;            // B05 - Indicador da forma de pagamento (*)
                                        //         (0)=ipVista
                                        //         (1)=ipPrazo
                                        //         (2)=ipOutras
  CTe.Ide.modelo := 55;                 // B06 - C�digo do Modelo do Documento Fiscal Utilizar o c�digo 55 para identifica��o da NF-e, emitida em substitui��o ao modelo 1 ou 1A.
  CTe.Ide.serie := 0;                   // B07 - S�rie do Documento Fiscal, informar 0 (zero) para s�rie �nica.
  CTe.Ide.nNF := 0;                     // B08 - N�mero do Documento Fiscal
  CTe.Ide.dEmi := null;                 // B09 - Data de emiss�o do Documento Fiscal
  CTe.Ide.dSaiEnt := null;              // B10 - Data de Sa�da ou da Entrada da Mercadoria/Produto
  CTe.Ide.tpNF := tnSaida;              // B11 - Tipo do Documento Fiscal (*)
                                        //         (0)=tnEntrada
                                        //         (1)=tnSaida
  CTe.Ide.cMunFG := 0;                  // B12 - C�digo do Munic�pio de Ocorr�ncia do Fato Gerador do ICMS ( Tab. IBGE )
  CTe.Ide.tpImp := tiRetrato;           // B21 - Formato de Impress�o do DACTe (*)
                                        //         (1)=tiRetrato
                                        //         (2)=tiPaisagem
  CTe.Ide.tpEmis := teNormal;           // B22 - Forma de Emiss�o da NF-e (*)
                                        //         (1)=teNormal
                                        //         (2)=teContingencia
                                        //         (3)=teSCAN
                                        //         (4)=teDPEC
                                        //         (5)=teFSDA
  // Ex: i := CTe.Ide.cDv;              // B23 - D�gito Verificador da Chave de  Acesso da NF-e
                                        //         N�o utilize esse campo para escrita (apenas para leitura)
                                        //         pois o digito � gerado automaticamente no momento da gera��o do arquivo
  CTe.Ide.tpAmb := taProducao;          // B24 - Identifica��o do Ambiente (*)
                                        //         (1)=Produ��o
                                        //         (2)=Homologa��o
  CTe.Ide.finCTe := fnNormal;           // B25 - Finalidade de emiss�o da NF-e (*)
                                        //         (1)=fnNormal
                                        //         (2)=fnComplementar
                                        //         (3)=fnAjuste
  CTe.Ide.procEmi :=                    // B26 - Processo de emiss�o da NF-e (*)
    peAplicativoContribuinte;           //         (0)=peAplicativoContribuinte      - emiss�o de NF-e com aplicativo do contribuinte;
                                        //         (1)=peAvulsaFisco                 - emiss�o de NF-e avulsa pelo Fisco;
                                        //         (2)=peAvulsaContribuinte          - emiss�o de NF-e avulsa, pelo contribuinte com seu certificado digital, atrav�s do site do Fisco;
                                        //         (3)=peContribuinteAplicativoFisco - emiss�o NF-e pelo contribuinte com aplicativo fornecido pelo Fisco.
  CTe.Ide.verProc := '';                // B27 - Vers�o do Processo de emiss�o da NF-e

  ReferenciadaTipoCTe := True;          // TAG - Informa��o das NF/NF-e referenciadas - <NFref> - Ocorr�ncia 0-N ********
  if ReferenciadaTipoCTe then
  begin                                 // Se a nota referenciada for um CTe preencher o campo abaixo:
    CTe.Ide.NFref.Add;
    CTe.Ide.NFref[0].refCTe := '';      // B13 - Chave de acesso das NF-e referenciadas
  end;
  if not ReferenciadaTipoCTe then
  begin                                 // Se a nota referenciada n�o for uma CTe preencher o campos abaixo:
    CTe.Ide.NFref.Add;
    CTe.Ide.NFref[1].RefNF.cUF := 0;    // B15 - C�digo da UF do emitente do Documento Fiscal - Tabela do IBGE
    CTe.Ide.NFref[1].RefNF.AAMM := '';  // B16 - Ano e M�s de emiss�o da NF-e
    CTe.Ide.NFref[1].RefNF.CNPJ := '';  // B17 - CNPJ do emitente
    CTe.Ide.NFref[1].RefNF.modelo := 0; // B18 - Modelo do Documento Fiscal - Ex. 01
    CTe.Ide.NFref[1].RefNF.serie := 0;  // B19 - S�rie do Documento Fiscal - informar zero se inexistente
    CTe.Ide.NFref[1].RefNF.nNF := 0;    // B20 - N�mero do Documento Fiscal
  end;

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG de grupo de identifica��o do emitente da NF-e  - <emit> - Ocorr�ncia 1-1                                      *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

  CTe.Emit.CNPJCPF := '';           // C02 - CNPJ do emitente / CPF do remetente
  CTe.Emit.xNome := '';             // C03 - Raz�o Social ou Nome do emitente
  CTe.Emit.xFant := '';             // C04 - Nome fantasia
  CTe.Emit.IE := '';                // C17 - Inscri��o Estadual do emitente
  CTe.Emit.IEST := '';              // C18 - Inscri��o Estadual do Substituto Tribut�rio da UF de destino da mercadoria, quando houver a reten��o do ICMS ST para a UF de destino.
  CTe.Emit.IM := '';                // C19 - Inscri��o Municipal (NF-e conjugada, com presta��o de servi�os sujeitos ao ISSQN e fornecimento de pe�as sujeitos ao ICMS.)
  CTe.Emit.CNAE := '';              // C20 - CNAE fiscal Este campo deve ser informado quando o campo CTe.Emit.IM for informado.
                                    //       TAG de grupo do Endere�o do emitente - <enderEmit> - Ocorr�ncia 1-1 ********
  CTe.Emit.enderEmit.xLgr := '';    // C06 - Logradouro
  CTe.Emit.enderEmit.nro := '';     // C07 - N�mero
  CTe.Emit.enderEmit.xCpl := '';    // C08 - Complemento
  CTe.Emit.enderEmit.xBairro := ''; // C09 - Bairro
  CTe.Emit.enderEmit.cMun := 0;     // C10 - C�digo do munic�pio (Tabela do IBGE - '9999999' para opera��es com o exterior))
  CTe.Emit.enderEmit.xMun := '';    // C11 - Nome do munic�pio   ('EXTERIOR' para opera��es com o exterior)
  CTe.Emit.enderEmit.UF := '';      // C12 - Sigla da UF         ('EX' para opera��es com o exterior.)
  CTe.Emit.enderEmit.CEP := 0;      // C13 - C�digo do CEP
  CTe.Emit.enderEmit.cPais := 0;    // C14 - C�digo do Pa�s      (Tabela do BACEN )
  CTe.Emit.enderEmit.xPais := '';   // C15 - Nome do Pa�s
  CTe.Emit.enderEmit.fone := '';    // C16 - Telefone            ( C�digo DDD + n�mero do telefone. )


  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG de grupo de Identifica��o do Fisco Emitente da NF-e - <avulsa> - Ocorr�ncia 0-1                               *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

                                    // D01 - Grupo para uso exclusivo do fisco
  CTe.Avulsa.CNPJ := '';
  CTe.Avulsa.xOrgao := '';
  CTe.Avulsa.matr := '';
  CTe.Avulsa.xAgente := '';
  CTe.Avulsa.fone := '';
  CTe.Avulsa.UF := '';
  CTe.Avulsa.nDAR := '';
  CTe.Avulsa.dEmi := null;
  CTe.Avulsa.vDAR := 0;
  CTe.Avulsa.repEmi := '';
  CTe.Avulsa.dPag := null;

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG de grupo de identifica��o do Destinat�rio da NF-e  - <dest> - Ocorr�ncia 1-1                                  *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

  CTe.Dest.CNPJCPF := '';           // E02 - CNPJ do destinat�rio / CPF do destinat�rio
  CTe.Dest.xNome := '';             // E04 - Raz�o Social ou nome do destinat�rio
  CTe.Dest.IE := '';                // E17 - Inscri��o Estadual do destinat�rio
  CTe.Dest.ISUF := '';              // E18 - Inscri��o na SUFRAMA
                                    //       TAG de grupo de endere�o do Destinat�rio da NF-e - <enderDest> - Ocorr�ncia 1-1 *
  CTe.Dest.enderDest.xLgr := '';    // E06 - Logradouro
  CTe.Dest.enderDest.nro := '';     // E07 - N�mero
  CTe.Dest.enderDest.xCpl := '';    // E08 - Complemento
  CTe.Dest.enderDest.xBairro := ''; // E09 - Bairro
  CTe.Dest.enderDest.cMun := 0;     // E10 - C�digo do munic�pio (Tabela do IBGE - '9999999' para opera��es com o exterior))
  CTe.Dest.enderDest.xMun := '';    // E11 - Nome do munic�pio   ('EXTERIOR' para opera��es com o exterior)
  CTe.Dest.enderDest.UF := '';      // E12 - Sigla da UF         ('EX' para opera��es com o exterior.)
  CTe.Dest.enderDest.CEP := 0;      // E13 - C�digo do CEP
  CTe.Dest.enderDest.cPais := 0;    // E14 - C�digo do Pa�s      (Tabela do BACEN )
  CTe.Dest.enderDest.xPais := '';   // E15 - Nome do Pa�s
  CTe.Dest.enderDest.fone := '';    // E16 - Telefone            ( C�digo DDD + n�mero do telefone. )

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG de grupo de identifica��o do Local de retirada - <retirada> - Ocorr�ncia 0-N                                  *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

                              // Informar os valores desse grupo somente se o  endere�o de
                              // retirada for diferente do endere�o do remetente.
                              // Assim se retirada.xLgr <> '' o gerador grava o grupo no XML

  CTe.Retirada.CNPJ := '';    // F02 - CNPJ
  CTe.Retirada.xLgr := '';    // F03 - Logradouro
  CTe.Retirada.nro := '';     // F04 - N�mero
  CTe.Retirada.xCpl := '';    // F05 - Complemento
  CTe.Retirada.xBairro := ''; // F06 - Bairro
  CTe.Retirada.cMun := 0;     // F07 - C�digo do munic�pio (Tabela do IBGE - '9999999' para opera��es com o exterior))
  CTe.Retirada.xMun := '';    // F08 - Nome do munic�pio   ('EXTERIOR' para opera��es com o exterior)
  CTe.Retirada.UF := '';      // F09 - Sigla da UF         ('EX' para opera��es com o exterior.)

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG de grupo de identifica��o do Local de entrega - <entrega> - Ocorr�ncia 0-N                                    *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

                              // Informar os valores desse grupo somente se o
                              // endere�o de entrega for diferente do endere�o do destinatario.
                              // Assim se entrega.xLgr <> '' o gerador grava o grupo no XML

  CTe.Entrega.CNPJ := '';     // G02 - CNPJ
  CTe.Entrega.xLgr := '';     // G03 - Logradouro
  CTe.Entrega.nro := '';      // G04 - N�mero
  CTe.Entrega.xCpl := '';     // G05 - Complemento
  CTe.Entrega.xBairro := '';  // G06 - Bairro
  CTe.Entrega.cMun := 0;      // G07 - C�digo do munic�pio (Tabela do IBGE - '9999999' para opera��es com o exterior))
  CTe.Entrega.xMun := '';     // G08 - Nome do munic�pio   ('EXTERIOR' para opera��es com o exterior)
  CTe.Entrega.UF := '';       // G09 - Sigla da UF         ('EX' para opera��es com o exterior.)

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG de grupo do detalhamento de Produtos e Servi�os da NF-e - <det> - Ocorr�ncia 1-990                            *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

  for i := 0 to 1 do
  begin

    CTe.Det.Add;

    CTe.Det[i].infAdProd := '';     // - Informa��es Adicionais do Produto

    CTe.Det[i].Prod.nItem := 1 + i; // H02 - N�mero do item DE 1 a 990
    CTe.Det[i].Prod.cProd := '';    // I02 - C�digo do produto ou servi�o
    CTe.Det[i].Prod.cEAN := '';     // I03 - GTIN (Global Trade Item Number) do produto, antigo c�digo EAN ou c�digo de barra
    CTe.Det[i].Prod.xProd := '';    // I04 - Descri��o do produto ou servi�o
    CTe.Det[i].Prod.NCM := '';      // I05 - C�digo NCM         (Em caso de servi�o, n�o incluir a TAG.)
    CTe.Det[i].Prod.EXTIPI := '';   // I06 - C�digo EX da TIPI  (Em caso de servi�o, n�o incluir a TAG.)
    CTe.Det[i].Prod.genero := 0;    // I07 - G�nero do Produto ou Servi�o (Tabela de Cap�tulos da NCM)(Em caso de servi�o, n�o incluir a TAG.)
    CTe.Det[i].Prod.CFOP := '';     // I08 - C�digo Fiscal de Opera��es e Presta��es

    CTe.Det[i].Prod.uCom := '';     // I09 - Unidade Comercial
    CTe.Det[i].Prod.qCom := 0;      // I10 - Quantidade Comercial
    CTe.Det[i].Prod.vUnCom := 0;    // I10a  Valor Unit�rio de comercializa��o
    CTe.Det[i].Prod.vProd := 0;     // I11 - Valor Total Bruto dos Produtos ou Servi�os
    CTe.Det[i].Prod.uTrib := '';    // I13 - Unidade Tribut�vel
    CTe.Det[i].Prod.qTrib := 0;     // I14 - Quantidade Tribut�vel
    CTe.Det[i].Prod.vUnTrib := 0;   // I14a  Valor Unit�rio de tributa��o

    (* EXEMPLO *)                   // CTe.Det[i].Prod.uCom    := 'CX';
                                    // CTe.Det[i].Prod.qCom    :=    2;   Vendidas 2 caixas ( com 10 unidades cada )
                                    // CTe.Det[i].Prod.vUnCom  :=   50;   R$ 50,00 cada caixa
                                    // CTe.Det[i].Prod.vProd   :=  100;   R$ 100,00 Valor dos produtos
                                    // CTe.Det[i].Prod.uTrib   := 'UN';
                                    // CTe.Det[i].Prod.qTrib   :=   20;   2 caixas X 10 unidades por caixa = 20 unidades
                                    // CTe.Det[i].Prod.vUnTrib :=    5;   R$ 100,00 / 20 unidades = R$ 5,00 cada unidade

    CTe.Det[i].Prod.cEANTrib := ''; // I12 - GTIN (Global Trade Item Number) da unidade tribut�vel,  antigo c�digo EAN ou c�digo de  barras ??
    CTe.Det[i].Prod.vFrete := 0;    // I15 - Valor Total do Frete
    CTe.Det[i].Prod.vSeg := 0;      // I16 - Valor Total do Seguro
    CTe.Det[i].Prod.vDesc := 0;     // I17 - Valor do Desconto

    //                                                           Tag da Declara��o de Importa��o - <DI> - Ocorr�ncia 0-N

    for j := 0 to 1 do
    begin
      CTe.Det[i].Prod.DI.Add;
      CTe.Det[i].Prod.DI[j].nDi := '';                  // I19 - N�mero do Documento de Importa��o DI/DSI/DA (DI/DSI/DA)
      CTe.Det[i].Prod.DI[j].dDi := null;                // I20 - Data de Registro da DI/DSI/DA
      CTe.Det[i].Prod.DI[j].xLocDesemb := '';           // I21 - Local de desembara�o
      CTe.Det[i].Prod.DI[j].UFDesemb := '';             // I22 - Sigla da UF onde ocorreu o Desembara�o Aduaneiro
      CTe.Det[i].Prod.DI[j].dDesemb := null;            // I23 - Data do Desembara�o Aduaneiro
      CTe.Det[i].Prod.DI[j].cExportador := '';          // I24 - C�digo do exportador
      for k := 0 to 1 do
      begin
        CTe.Det[i].Prod.DI[j].adi.Add;                  //       Tag de Adi��es - <adi> - Ocorr�ncia 1-N
        CTe.Det[i].Prod.DI[j].adi[k].nAdicao := 0;      // I26 - Numero da adi��o
        CTe.Det[i].Prod.DI[j].adi[k].nSeqAdi := 0;      // I27 - Numero seq�encial do item dentro da adi��o
        CTe.Det[i].Prod.DI[j].adi[k].cFabricante := ''; // I28 - C�digo do fabricante estrangeiro
        CTe.Det[i].Prod.DI[j].adi[k].vDescDI := 0;      // I29 - Valor do desconto do item da DI � adi��o
      end;
    end;

    //                                                      TAG de grupo do detalhamento de Ve�culos novos - <veicProd> - Ocorr�ncia 0-1

                                                    //       Este grupo ser� criado no XML somente se o campo <chassi> for informado.
    CTe.Det[i].prod.veicProd.tpOP := toVendaDireta; // J02 - Tipo da opera��o
                                                    //         (1)=toVendaConcessionaria
                                                    //         (2)=toFaturamentoDireto
                                                    //         (3)=toVendaDireta
                                                    //         (0)=toOutros
    CTe.Det[i].prod.veicProd.chassi := '';          // J03 - Chassi do ve�culo
    CTe.Det[i].prod.veicProd.cCor := '';            // J04 - Cor
    CTe.Det[i].prod.veicProd.xCor := '';            // J05 - Descri��o da Cor
    CTe.Det[i].prod.veicProd.pot := '';             // J06 - Pot�ncia Motor
    CTe.Det[i].prod.veicProd.CM3 := '';             // J07 - CM3 (Pot�ncia)
    CTe.Det[i].prod.veicProd.pesoL := '';           // J08 - Peso L�quido
    CTe.Det[i].prod.veicProd.pesoB := '';           // J09 - Peso Bruto
    CTe.Det[i].prod.veicProd.nSerie := '';          // J10 - Serial (s�rie)
    CTe.Det[i].prod.veicProd.tpComb := '';          // J11 - Tipo de combust�vel
    CTe.Det[i].prod.veicProd.nMotor := '';          // J12 - N�mero de Motor
    CTe.Det[i].prod.veicProd.CMKG := '';            // J13 - CMKG
    CTe.Det[i].prod.veicProd.dist := '';            // J14 - Dist�ncia entre eixos
    CTe.Det[i].prod.veicProd.RENAVAM := '';         // J15 - RENAVAM            (N�o informar a TAG na exporta��o)
    CTe.Det[i].prod.veicProd.anoMod := 0;           // J16 - Ano Modelo de Fabrica��o
    CTe.Det[i].prod.veicProd.anoFab := 0;           // J17 - Ano de Fabrica��o
    CTe.Det[i].prod.veicProd.tpPint := '';          // J18 - Tipo de Pintura
    CTe.Det[i].prod.veicProd.tpVeic := 0;           // J19 - Tipo de Ve�culo    (Utilizar Tabela RENAVAM)
    CTe.Det[i].prod.veicProd.espVeic := 0;          // J20 - Esp�cie de Ve�culo (Utilizar Tabela RENAVAM)
    CTe.Det[i].prod.veicProd.VIN := '';             // J21 - Condi��o do VIN
    CTe.Det[i].prod.veicProd.condVeic := cvAcabado; // J22 - Condi��o do Ve�culo (1-Acabado; 2-Inacabado; 3-Semi-acabado)
    CTe.Det[i].prod.veicProd.cMod := '';             // J23 - C�digo Marca Modelo (Utilizar Tabela RENAVAM)

    //                                              TAG de grupo do detalhamento de Medicamentos - <med> - Ocorr�ncia 0-N
    for j := 0 to 1 do
    begin
      CTe.Det[i].prod.med.add;
      CTe.Det[i].prod.med[j].nLote := '';  // K02 - N�mero do Lote do medicamento
      CTe.Det[i].prod.med[j].qLote := 0;   // K03 - Quantidade de produto no Lote do medicamento
      CTe.Det[i].prod.med[j].dFab := null; // K04 - Data de fabrica��o
      CTe.Det[i].prod.med[j].dVal := null; // K05 - Data de validade
      CTe.Det[i].prod.med[j].vPMC := 0;    // K06 - Pre�o m�ximo consumidor
    end;

    //                                                           TAG de grupo do detalhamento de Armamento - <arma> - Ocorr�ncia 0-N
    for j := 0 to 1 do
    begin
      CTe.Det[i].prod.arma.add;
      CTe.Det[i].prod.arma[j].tpArma := taUsoPermitido; // L02 - Indicador do tipo de arma de fogo
                                                        //         (0)=taUsoPermitido
                                                        //         (1)=taUsoRestrito
      CTe.Det[i].prod.arma[j].nSerie := 0;              // L03 - N�mero de s�rie da arma
      CTe.Det[i].prod.arma[j].nCano := 0;               // L04 - N�mero de s�rie do cano
      CTe.Det[i].prod.arma[j].descr := '';              // L05 - Descri��o completa da arma, compreendendo: calibre, marca, capacidade, etc)
    end;

    //                                                            TAG de grupo de informa��es espec�ficas para combust�veis

                                                        //        l�quidos - <comb> - Ocorr�ncia 0-1
                                                        //        Se for informado algum dos valores abaixo:
                                                        //        a TAG grava o grupo no XML ********************************************
    CTe.Det[i].prod.comb.cProdANP := 0;                 // L102 - C�digo de produto da ANP - codifica��o de produtos do SIMP
    CTe.Det[i].prod.comb.CODIF := '';                   // L103 - C�digo de autoriza��o / registro do CODIF
    CTe.Det[i].prod.comb.qTemp := 0;                    // L104 - Quantidade de combust�vel faturada � temperatura ambiente.
                                                        //        TAG de grupo da CIDE - <CIDE> - Ocorr�ncia 0-1
    CTe.Det[i].prod.comb.CIDE.qBCprod := 0;             // L106 - BC da CIDE em quantidad
    CTe.Det[i].prod.comb.CIDE.vAliqProd := 0;           // L107 - Valor da al�quota da CIDE
    CTe.Det[i].prod.comb.CIDE.vCIDE := 0;               // L108 - Valor da CIDE
                                                        //        TAG de grupo do ICMS - <ICMS> - Ocorr�ncia 1-1
    CTe.Det[i].prod.comb.ICMS.vBCICMS := 0;             // L110 - BC do ICMS
    CTe.Det[i].prod.comb.ICMS.vICMS := 0;               // L111 - Valor do ICMS
    CTe.Det[i].prod.comb.ICMS.vBCICMSST := 0;           // L112 - BC do ICMS ST retido
    CTe.Det[i].prod.comb.ICMS.vICMSST := 0;             // L113 - Valor do ICMS ST retido
                                                        //        TAG de grupo do ICMSST de opera��o interestadual - <ICMSInter> - Ocorr�ncia 0-1
    CTe.Det[i].prod.comb.ICMSInter.vBCICMSSTDest := 0;  // L115 - BC do ICMS ST da UF de destino
    CTe.Det[i].prod.comb.ICMSInter.vICMSSTDest := 0;    // L116 - Valor do ICMS ST da UF de destino
                                                        //        TAG de ICMS para consumo em UF diversa da UF de localiza��o do destinat�rio do produto - <ICMSCons> - Ocorr�ncia 0-1
    CTe.Det[i].prod.comb.ICMSCons.vBCICMSSTCons := 0;   // L118 - BC do ICMS ST da UF de consumo
    CTe.Det[i].prod.comb.ICMSCons.vICMSSTCons := 0;     // L119 - Valor do ICMS ST da UF de consumo
    CTe.Det[i].prod.comb.ICMSCons.UFcons := '';         // L120 - Sigla da UF de consumo

    //                                                                      TAG de grupo do ICMS da Opera��o pr�pria e ST - <ICMS> - Ocorr�ncia 1-1

    CTe.Det[i].Imposto.ICMS.orig := oeNacional;                    // N11 - Origem da mercadoria
                                                                   //         (0)=oeNacional
                                                                   //         (1)=oeEstrangeiraImportacaoDireta
                                                                   //         (2)=oeEstrangeiraAdquiridaBrasil
    CTe.Det[i].Imposto.ICMS.CST  := cst00;                         // N12 - Tributa��o do ICMS
                                                                   //         (00)=cst00
                                                                   //         (10)=cst10
                                                                   //         (20)=cst20
                                                                   //         (30)=cst30
                                                                   //         (40)=cst40
                                                                   //         (41)=cst41
                                                                   //         (50)=cst50
                                                                   //         (51)=cst51
                                                                   //         (60)=cst60
                                                                   //         (70)=cst70
                                                                   //         (90)=cst90
    if CTe.Det[i].Imposto.ICMS.CST = cst00 then
    begin
      CTe.Det[i].Imposto.ICMS.modBC := dbiMargemValorAgregado;     // N13 - Modalidade de determina��o da BC do ICMS
      CTe.Det[i].Imposto.ICMS.vBC := 0;                            // N15 - Valor da BC do ICMS
      CTe.Det[i].Imposto.ICMS.pICMS := 0;                          // N16 - Al�quota do imposto
      CTe.Det[i].Imposto.ICMS.vICMS := 0;                          // N17 - Valor do ICMS
    end;
    if CTe.Det[i].Imposto.ICMS.CST = cst10 then
    begin
      CTe.Det[i].Imposto.ICMS.modBC := dbiMargemValorAgregado;     // N13 - Modalidade de determina��o da BC do ICMS
      CTe.Det[i].Imposto.ICMS.vBC := 0;                            // N15 - Valor da BC do ICMS
      CTe.Det[i].Imposto.ICMS.pICMS := 0;                          // N16 - Al�quota do imposto
      CTe.Det[i].Imposto.ICMS.vICMS := 0;                          // N17 - Valor do ICMS
      CTe.Det[i].Imposto.ICMS.modBCST := dbisMargemValorAgregado;  // N18 - Modalidade de determina��o da BC do ICMS ST
      CTe.Det[i].Imposto.ICMS.pMVAST := 0;                         // N19 - Percentual da margem de valor Adicionado do ICMS ST
      CTe.Det[i].Imposto.ICMS.pRedBCST := 0;                       // N20 - Percentual da Redu��o de BC do ICMS ST
      CTe.Det[i].Imposto.ICMS.vBCST := 0;                          // N21 - Valor da BC do ICMS ST
      CTe.Det[i].Imposto.ICMS.pICMSST := 0;                        // N22 - Al�quota do imposto do ICMS ST
      CTe.Det[i].Imposto.ICMS.vICMSST := 0;                        // N23 - Valor do ICMS ST
    end;
    if CTe.Det[i].Imposto.ICMS.CST = cst20 then
    begin
      CTe.Det[i].Imposto.ICMS.modBC := dbiMargemValorAgregado;     // N13 - Modalidade de determina��o da BC do ICMS
      CTe.Det[i].Imposto.ICMS.pRedBC := 0;                         // N14 - Percentual da Redu��o de BC do ICMS
      CTe.Det[i].Imposto.ICMS.vBC := 0;                            // N15 - Valor da BC do ICMS
      CTe.Det[i].Imposto.ICMS.pICMS := 0;                          // N16 - Al�quota do imposto
      CTe.Det[i].Imposto.ICMS.vICMS := 0;                          // N17 - Valor do ICMS
    end;
    if CTe.Det[i].Imposto.ICMS.CST = cst30 then
    begin
      CTe.Det[i].Imposto.ICMS.vICMS := 0;                          // N17 - Valor do ICMS
      CTe.Det[i].Imposto.ICMS.pMVAST := 0;                         // N19 - Percentual da margem de valor Adicionado do ICMS ST
      CTe.Det[i].Imposto.ICMS.pRedBCST := 0;                       // N20 - Percentual da Redu��o de BC do ICMS ST
      CTe.Det[i].Imposto.ICMS.vBCST := 0;                          // N21 - Valor da BC do ICMS ST
      CTe.Det[i].Imposto.ICMS.pICMSST := 0;                        // N22 - Al�quota do imposto do ICMS ST
      CTe.Det[i].Imposto.ICMS.vICMSST := 0;                        // N23 - Valor do ICMS ST
    end;
    if CTe.Det[i].Imposto.ICMS.CST = cst51 then
    begin
      CTe.Det[i].Imposto.ICMS.modBC := dbiMargemValorAgregado;     // N13 - Modalidade de determina��o da BC do ICMS
      CTe.Det[i].Imposto.ICMS.pRedBC := 0;                         // N14 - Percentual da Redu��o de BC do ICMS
      CTe.Det[i].Imposto.ICMS.vBC := 0;                            // N15 - Valor da BC do ICMS
      CTe.Det[i].Imposto.ICMS.pICMS := 0;                          // N16 - Al�quota do imposto
      CTe.Det[i].Imposto.ICMS.vICMS := 0;                          // N17 - Valor do ICMS
    end;
    if CTe.Det[i].Imposto.ICMS.CST = cst60 then
    begin
      CTe.Det[i].Imposto.ICMS.vBCST := 0;                          // N21 - Valor da BC do ICMS ST
      CTe.Det[i].Imposto.ICMS.vICMSST := 0;                        // N23 - Valor do ICMS ST
    end;
    if CTe.Det[i].Imposto.ICMS.CST = cst70 then
    begin
      CTe.Det[i].Imposto.ICMS.modBC := dbiMargemValorAgregado;     // N13 - Modalidade de determina��o da BC do ICMS
      CTe.Det[i].Imposto.ICMS.pRedBC := 0;                         // N14 - Percentual da Redu��o de BC do ICMS
      CTe.Det[i].Imposto.ICMS.vBC := 0;                            // N15 - Valor da BC do ICMS
      CTe.Det[i].Imposto.ICMS.pICMS := 0;                          // N16 - Al�quota do imposto
      CTe.Det[i].Imposto.ICMS.vICMS := 0;                          // N17 - Valor do ICMS
      CTe.Det[i].Imposto.ICMS.modBCST := dbisMargemValorAgregado;  // N18 - Modalidade de determina��o da BC do ICMS ST
      CTe.Det[i].Imposto.ICMS.pMVAST := 0;                         // N19 - Percentual da margem de valor Adicionado do ICMS ST
      CTe.Det[i].Imposto.ICMS.pRedBCST := 0;                       // N20 - Percentual da Redu��o de BC do ICMS ST
      CTe.Det[i].Imposto.ICMS.vBCST := 0;                          // N21 - Valor da BC do ICMS ST
      CTe.Det[i].Imposto.ICMS.pICMSST := 0;                        // N22 - Al�quota do imposto do ICMS ST
      CTe.Det[i].Imposto.ICMS.vICMSST := 0;                        // N23 - Valor do ICMS ST
    end;
    if CTe.Det[i].Imposto.ICMS.CST = cst90 then
    begin
      CTe.Det[i].Imposto.ICMS.modBC := dbiMargemValorAgregado;     // N13 - Modalidade de determina��o da BC do ICMS
      CTe.Det[i].Imposto.ICMS.pRedBC := 0;                         // N14 - Percentual da Redu��o de BC do ICMS
      CTe.Det[i].Imposto.ICMS.vBC := 0;                            // N15 - Valor da BC do ICMS
      CTe.Det[i].Imposto.ICMS.pICMS := 0;                          // N16 - Al�quota do imposto
      CTe.Det[i].Imposto.ICMS.vICMS := 0;                          // N17 - Valor do ICMS
      CTe.Det[i].Imposto.ICMS.modBCST := dbisMargemValorAgregado;  // N18 - Modalidade de determina��o da BC do ICMS ST
      CTe.Det[i].Imposto.ICMS.pMVAST := 0;                         // N19 - Percentual da margem de valor Adicionado do ICMS ST
      CTe.Det[i].Imposto.ICMS.pRedBCST := 0;                       // N20 - Percentual da Redu��o de BC do ICMS ST
      CTe.Det[i].Imposto.ICMS.vBCST := 0;                          // N21 - Valor da BC do ICMS ST
      CTe.Det[i].Imposto.ICMS.pICMSST := 0;                        // N22 - Al�quota do imposto do ICMS ST
      CTe.Det[i].Imposto.ICMS.vICMSST := 0;                        // N23 - Valor do ICMS ST
    end;
                                                                   // N13 - Modalidade de determina��o da BC do ICMS
                                                                   //         (0)=dbiMargemValorAgregado
                                                                   //         (1)=dbiPauta
                                                                   //         (2)=dbiPrecoTabelado
                                                                   //         (3)=dbiValorOperacao

                                                                   // N18 - Modalidade de determina��o da BC do ICMS ST
                                                                   //         (0)=dbisPrecoTabelado       � Pre�o tabelado ou m�ximo sugerido;
                                                                   //         (1)=dbisListaNegativa       - Lista Negativa (valor);
                                                                   //         (2)=dbisListaPositiva       - Lista Positiva (valor);
                                                                   //         (3)=dbisListaNeutra         - Lista Neutra (valor);
                                                                   //         (4)=dbisMargemValorAgregado - Margem Valor Agregado (%);
                                                                   //         (5)=dbisPauta               - Pauta (valor);)

    //                                                   TAG de grupo do IPI - <IPI> - Ocorr�ncia 0-1

    CTe.Det[i].Imposto.IPI.clEnq := '';         // O02 - Classe de enquadramento do IPI para Cigarros e Bebidas
    CTe.Det[i].Imposto.IPI.CNPJProd := '';      // O03 - CNPJ do produtor da mercadoria, quando diferente do emitente. Somente para os casos de exporta��o direta ou indireta.
    CTe.Det[i].Imposto.IPI.cSelo := '';         // O04 - C�digo do selo de controle IPI
    CTe.Det[i].Imposto.IPI.qSelo := 0;          // O05 - Quantidade de selo de controle
    CTe.Det[i].Imposto.IPI.cEnq := '';          // O06 - C�digo de Enquadramento Legal do IPI (Tabela a ser criada pela RFB, informar 999 enquanto a tabela n�o for criada)
    CTe.Det[i].Imposto.IPI.CST := ipi00;        // O09 - C�digo da situa��o tribut�ria do IPI
                                                //       (00)=ipi00 - Entrada com recupera��o de cr�dito
                                                //       (49)=ipi49 - Outras entradas
                                                //       (50)=ipi50 - Sa�da tributada
                                                //       (99)=ipi99 - Outras sa�das
                                                //       (01)=ipi01 - Entrada tributada com al�quota zero
                                                //       (02)=ipi02 - Entrada isenta
                                                //       (03)=ipi03 - Entrada n�o-tributada
                                                //       (04)=ipi04 - Entrada imune
                                                //       (05)=ipi05 - Entrada com suspens�o
                                                //       (51)=ipi51 - Sa�da tributada com al�quota zero
                                                //       (52)=ipi52 - Sa�da isenta
                                                //       (53)=ipi53 - Sa�da n�o-tributada
                                                //       (54)=ipi54 - Sa�da imune
                                                //       (55)=ipi55 - Sa�da com suspens�o

    if (CTe.Det[i].Imposto.IPI.CST = ipi00) or
    (CTe.Det[i].Imposto.IPI.CST = ipi49) or
    (CTe.Det[i].Imposto.IPI.CST = ipi50) or
    (CTe.Det[i].Imposto.IPI.CST = ipi99) then
    begin
      if opcao1 then
      begin
        CTe.Det[ i].Imposto.IPI.vBC := 0;       // O10 - Valor da BC do IPI
        CTe.Det[ i].Imposto.IPI.pIPI := 0;      // 013 - Al�quota do IPI
        CTe.Det[i].Imposto.IPI.vIPI := 0;       // O14 - Valor do IPI
      end;
      if not opcao1 then
      begin
        CTe.Det[ i].Imposto.IPI.qUnid := 0;     // O11 - Qtde total na unidade padr�o para tributa��o (somente para os produtos tributados por unidade)
        CTe.Det[ i].Imposto.IPI.vUnid := 0;     // O12 - Valor por Unidade Tribut�vel
        CTe.Det[i].Imposto.IPI.vIPI := 0;       // O14 - Valor do IPI
      end;
    end;

    //                                                   TAG de grupo do Imposto de Importa��o - <II> - Ocorr�ncia 0-1

                                                //       Informar apenas quando o item for sujeito ao II
    CTe.Det[i].Imposto.II.vBc := 0;             // P02 - Valor da BC do Imposto de Importa��o
    CTe.Det[i].Imposto.II.vDespAdu := 0;        // P03 - Valor das despesas aduaneiras
    CTe.Det[i].Imposto.II.vII := 0;             // P04 - Valor do Imposto de Importa��o
    CTe.Det[i].Imposto.II.vIOF := 0;            // P05 - Valor do Imposto sobre Opera��es Financeiras

    //                                                   TAG de grupo do PIS - <PIS> - Ocorr�ncia 1-1

    CTe.Det[i].Imposto.PIS.CST := pis01;        // Q06 - C�digo de Situa��o Tribut�ria do PIS
                                                //         (01)=pis01 � Opera��o Tribut�vel (base de c�lculo = valor da opera��o al�quota normal (cumulativo/n�o cumulativo));
                                                //         (02)=pis02 - Opera��o Tribut�vel (base de c�lculo = valor da opera��o (al�quota diferenciada));
                                                //         (03)=pis03 - Opera��o Tribut�vel (base de c�lculo = quantidade vendida x al�quota por unidade de produto )
                                                //         (04)=pis04 - Opera��o Tribut�vel (tributa��o monof�sica (al�quota zero));
                                                //         (06)=pis06 - Opera��o Tribut�vel (al�quota zero);
                                                //         (07)=pis07 - Opera��o Isenta da Contribui��o;
                                                //         (08)=pis08 - Opera��o Sem Incid�ncia da Contribui��o;
                                                //         (09)=pis09 - Opera��o com Suspens�o da Contribui��o;
                                                //         (99)=pis99 - Outras Opera��es;

    if (CTe.Det[i].Imposto.PIS.CST = pis01) or
    (CTe.Det[i].Imposto.PIS.CST = pis02) then
    begin
      CTe.Det[i].Imposto.PIS.vBC := 0;          // Q07 - Valor da Base de C�lculo do PIS
      CTe.Det[i].Imposto.PIS.pPIS := 0;         // Q08 - Al�quota do PIS (em percentual)
      CTe.Det[i].Imposto.PIS.qBCProd := 0;      // Q10 - Quantidade Vendida
      CTe.Det[i].Imposto.PIS.vAliqProd := 0;    // Q11 - Al�quota do PIS (em reais)
      CTe.Det[i].Imposto.PIS.vPIS := 0;         // Q09 - Valor do PIS
    end;
    if (CTe.Det[i].Imposto.PIS.CST = pis03) then
    begin
      CTe.Det[i].Imposto.PIS.qBCProd := 0;      // Q10 - Quantidade Vendida
      CTe.Det[i].Imposto.PIS.vAliqProd := 0;    // Q11 - Al�quota do PIS (em reais)
      CTe.Det[i].Imposto.PIS.vPIS := 0;         // Q09 - Valor do PIS
    end;
    if (CTe.Det[i].Imposto.PIS.CST = pis03) and (opcao1)then
    begin
      CTe.Det[i].Imposto.PIS.vBC := 0;          // Q07 - Valor da Base de C�lculo do PIS
      CTe.Det[i].Imposto.PIS.pPIS := 0;         // Q08 - Al�quota do PIS (em percentual)
      CTe.Det[i].Imposto.PIS.vPIS := 0;         // Q09 - Valor do PIS
    end;
    if (CTe.Det[i].Imposto.PIS.CST = pis03) and (not opcao1)then
    begin
      CTe.Det[i].Imposto.PIS.qBCProd := 0;      // Q10 - Quantidade Vendida
      CTe.Det[i].Imposto.PIS.vAliqProd := 0;    // Q11 - Al�quota do PIS (em reais)
      CTe.Det[i].Imposto.PIS.vPIS := 0;         // Q09 - Valor do PIS
    end;

  //          TAG do grupo de PIS Substitui��o Tribut�ria - <PISST> - Ocorr�ncia 0-1

    if opcao1 then
    begin
      CTe.Det[i].Imposto.PISST.vBc := 0;        // R02 - Valor da Base de C�lculo do PIS
      CTe.Det[i].Imposto.PISST.pPis := 0;       // R03 - Al�quota do PIS (em percentual)
      CTe.Det[i].Imposto.PISST.vPIS := 0;       // R06 - Valor do PIS
    end;
    if not opcao1 then
    begin
      CTe.Det[i].Imposto.PISST.qBCProd := 0;    // R04 - Quantidade Vendida
      CTe.Det[i].Imposto.PISST.vAliqProd := 0;  // R05 - Al�quota do PIS (em reais)
      CTe.Det[i].Imposto.PISST.vPIS := 0;       // R06 - Valor do PIS
    end;

  //          TAG de grupo do COFINS - <COFINS> - Ocorr�ncia 1-1

    CTe.Det[i].Imposto.COFINS.CST := cof01;          // S06 - C�digo de Situa��o Tribut�ria do COFINS
                                                     //         (01) � Opera��o Tribut�vel (base de c�lculo = valor da opera��o al�quota normal (cumulativo/n�o cumulativo));
                                                     //         (02) - Opera��o Tribut�vel (base de c�lculo = valor da opera��o
                                                     //         (03) - Opera��o Tribut�vel (base de c�lculo = quantidade vendida x al�quota por unidade de produto);
                                                     //         (04) - Opera��o Tribut�vel (tributa��o monof�sica (al�quota zero));
                                                     //         (06) - Opera��o Tribut�vel (al�quota zero);
                                                     //         (07) - Opera��o Isenta da Contribui��o;
                                                     //         (08) - Opera��o Sem Incid�ncia da Contribui��o;
                                                     //         (09) - Opera��o com Suspens�o da Contribui��o;
                                                     //         (99) - Outras Opera��es;

    if (CTe.Det[i].Imposto.COFINS.CST = cof01) or (CTe.Det[i].Imposto.COFINS.CST = cof02) then
    begin
      CTe.Det[i].Imposto.COFINS.vBC := 0;            // S07 - Valor da Base de C�lculo da COFINS
      CTe.Det[i].Imposto.COFINS.pCOFINS := 0;        // S08 - Al�quota da COFINS (em percentual)
      CTe.Det[i].Imposto.COFINS.vCOFINS := 0;        // S11 - Valor do COFINS
    end;
    if CTe.Det[i].Imposto.COFINS.CST = cof03 then
    begin
      CTe.Det[i].Imposto.COFINS.vBCProd := 0;        // S09 - Valor do COFINS
      CTe.Det[i].Imposto.COFINS.vAliqProd := 0;      // S10 - Al�quota do COFINS (em reais)
      CTe.Det[i].Imposto.COFINS.vCOFINS := 0;        // S11 - Valor do COFINS
    end;
    if ( CTe.Det[i].Imposto.COFINS.CST = cof99) and (opcao1) then
    begin
      CTe.Det[i].Imposto.COFINS.vBC := 0;            // S07 - Valor da Base de C�lculo da COFINS
      CTe.Det[i].Imposto.COFINS.pCOFINS := 0;        // S08 - Al�quota da COFINS (em percentual)
      CTe.Det[i].Imposto.COFINS.vCOFINS := 0;        // S11 - Valor do COFINS
    end;
    if ( CTe.Det[i].Imposto.COFINS.CST = cof99) and (not opcao1) then
    begin
      CTe.Det[i].Imposto.COFINS.qBCProd := 0;        // S09 - Quantidade Vendida
      CTe.Det[i].Imposto.COFINS.vAliqProd := 0;      // S10 - Al�quota do COFINS (em reais)
      CTe.Det[i].Imposto.COFINS.vCOFINS := 0;        // S11 - Valor do COFINS
    end;

  //          TAG do grupo de COFINS Substitui��o Tribut�ria - <COFINSST> - Ocorr�ncia 0-1

    if opcao1 then
    begin
      CTe.Det[i].Imposto.COFINSST.vBC := 0;          // T02 - Valor da Base de C�lculo da COFINS
      CTe.Det[i].Imposto.COFINSST.pCOFINS := 0;      // T03 - Al�quota da COFINS (em percentual)
      CTe.Det[i].Imposto.COFINSST.vCOFINS := 0;      // T06 - Valor do COFINS
    end;
    if not opcao1 then
    begin
      CTe.Det[i].Imposto.COFINSST.qBCProd := 0;      // T04 - Quantidade Vendida
      CTe.Det[i].Imposto.COFINSST.vAliqProd := 0;    // T05 - Al�quota do COFINS (em reais)
      CTe.Det[i].Imposto.COFINSST.vCOFINS := 0;      // T06 - Valor do COFINS
    end;

  //          TAG do grupo do ISSQN - <ISSQN> - Ocorr�ncia 0-1

    CTe.Det[i].Imposto.ISSQN.vBC := 0;               // U02 - Valor da Base de C�lculo do ISSQN
    CTe.Det[i].Imposto.ISSQN.vAliq := 0;             // U03 - Al�quota do ISSQN
    CTe.Det[i].Imposto.ISSQN.vISSQN := 0;            // U04 - Valor do ISSQN
    CTe.Det[i].Imposto.ISSQN.cMunFG := 0;            // U05 - C�digo do munic�pio de ocorr�ncia do fato gerador do ISSQN
    CTe.Det[i].Imposto.ISSQN.cListServ := 0;         // U06 - C�digo da Lista de Servi�os

  end;

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG de grupo de Valores Totais da NF-e - <total> - Ocorr�ncia 1-1                                                 *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

                                     //       TAG de grupo de Valores Totais referentes ao ICMS - <ICMSTot> - Ocorr�ncia 1-1
  CTe.Total.ICMSTot.vBC := 0;        // W03 - Base de C�lculo do ICMS
  CTe.Total.ICMSTot.vICMS := 0;      // W04 - Valor Total do ICMS
  CTe.Total.ICMSTot.vBCST := 0;      // W05 - Base de C�lculo do ICMS ST
  CTe.Total.ICMSTot.vST := 0;        // W06 - Valor Total do ICMS ST
  CTe.Total.ICMSTot.vProd := 0;      // W07 - Valor Total dos produtos e servi�os
  CTe.Total.ICMSTot.vFrete := 0;     // W08 - Valor Total do Frete
  CTe.Total.ICMSTot.vSeg := 0;       // W09 - Valor Total do Seguro
  CTe.Total.ICMSTot.vDesc := 0;      // W10 - Valor Total do Desconto
  CTe.Total.ICMSTot.vII := 0;        // W11 - Valor Total do II
  CTe.Total.ICMSTot.vIPI := 0;       // W12 - Valor Total do IPI
  CTe.Total.ICMSTot.vPIS := 0;       // W13 - Valor do PIS
  CTe.Total.ICMSTot.vCOFINS := 0;    // W14 - Valor do COFINS
  CTe.Total.ICMSTot.vOutro := 0;     // W15 - Outras Despesas acess�rias
  CTe.Total.ICMSTot.vNF := 0;        // W16 - Valor Total da NF-e

                                     //       TAG de grupo de Valores Totais referentes ao ISSQN - <ISSQNtot> - Ocorr�ncia 0-1
  CTe.Total.ISSQNtot.vServ := 0;     // W18 - Valor Total dos Servi�os sob n�oincid�ncia ou n�o tributados pelo ICMS
  CTe.Total.ISSQNtot.vBC := 0;       // W19 - Base de C�lculo do ISS
  CTe.Total.ISSQNtot.vISS := 0;      // W20 - Valor Total do ISS
  CTe.Total.ISSQNtot.vPIS := 0;      // W21 - Valor do PIS sobre servi�os
  CTe.Total.ISSQNtot.vCOFINS := 0;   // W22 - Valor do COFINS sobre servi�os

                                     //       TAG de grupo de Reten��es de Tributos - <retTrib> - Ocorr�ncia 0-1
  CTe.Total.retTrib.vRetPIS := 0;    // W24 - Valor Retido de PIS
  CTe.Total.retTrib.vRetCOFINS := 0; // W25 - Valor Retido de COFINS
  CTe.Total.retTrib.vRetCSLL := 0;   // W26 - Valor Retido de CSLL
  CTe.Total.retTrib.vBCIRRF := 0;    // W27 - Base de C�lculo do IRRF
  CTe.Total.retTrib.vIRRF := 0;      // W28 - Valor Retido do IRRF
  CTe.Total.retTrib.vBCRetPrev := 0; // W29 - Base de C�lculo da Reten��o da Previd�ncia Social
  CTe.Total.retTrib.vRetPrev := 0;   // W30  - Valor da Reten��o da Previd�ncia Social

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG de grupo de Informa��es do Transporte da NF-e - <transp> - Ocorr�ncia 1-1                                     *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

  CTe.Transp.modFrete := mfContaEmitente;       // X02 - Modalidade do frete
                                                //         (0)=mfContaEmitente     � por conta do emitente;
                                                //         (1)=mfContaDestinatario � por conta do destinat�rio)
                                                //       TAG de grupo Transportador - <transporta> - Ocorr�ncia 0-1
  CTe.Transp.Transporta.CNPJCPF := '';          // X05 - Informar o CNPJ ou o CPF do Transportador, preenchendo os zeros n�o significativos.
  CTe.Transp.Transporta.xNome := '';            // X06 - Raz�o Social ou nome
  CTe.Transp.Transporta.IE := '';               // X07 - Inscri��o Estadual
  CTe.Transp.Transporta.xEnder := '';           // X08 - Endere�o Completo
  CTe.Transp.Transporta.xMun := '';             // X09 - Nome do munic�pio
  CTe.Transp.Transporta.UF := '';               // X10 - Sigla da UF
                                                //       TAG de grupo de Reten��o do ICMS do transporte - <retTransp> - Ocorr�ncia 0-1
  CTe.Transp.retTransp.vServ := 0;              // X12 - Valor do Servi�o
  CTe.Transp.retTransp.vBCRet := 0;             // X13 - BC da Reten��o do ICMS
  CTe.Transp.retTransp.pICMSRet := 0;           // X14 - Al�quota da Reten��o
  CTe.Transp.retTransp.vICMSRet := 0;           // X15 - Valor do ICMS Retido
  CTe.Transp.retTransp.CFOP := '';              // X16 - CFOP (Utilizar Tabela de CFOP)
  CTe.Transp.retTransp.cMunFG := 0;             // X17 - C�digo do munic�pio de ocorr�ncia do fato gerador do ICMS do transporte (Tabela do IBGE)
                                                //       TAG de grupo Ve�culo - <veicTransp> - Ocorr�ncia 0-1
  CTe.Transp.veicTransp.placa := '';            // X19 - Placa do Ve�culo
  CTe.Transp.veicTransp.UF := '';               // X20 - Sigla da UF
  CTe.Transp.veicTransp.RNTC := '';             // X21 - Registro Nacional de Transportador de Carga (ANTT)

  for i := 0 to 1 do
  begin
    CTe.Transp.Reboque.Add;                     //       TAG de grupo Reboque - <reboque> - Ocorr�ncia 0-2
    CTe.Transp.Reboque[i].placa := '';          // X23 - Placa do Ve�culo
    CTe.Transp.Reboque[i].UF := '';             // X24 - Sigla da UF
    CTe.Transp.Reboque[i].RNTC := '';           // X25 - Registro Nacional de Transportador de Carga (ANTT)
  end;

  for i := 0 to 1 do
  begin
    CTe.Transp.Vol.add;                         //       TAG de grupo Volumes - <vol> - Ocorr�ncia 0-N
    CTe.Transp.Vol[i].qVol := 0;                // X27 - Quantidade de volumes transportados
    CTe.Transp.vol[i].esp := '';                // X28 - Esp�cie dos volumes transportados
    CTe.Transp.Vol[i].marca := '';              // X29 - Marca dos volumes transportados
    CTe.Transp.Vol[i].nVol := '';               // X30 - Numera��o dos volumes transportados
    CTe.Transp.Vol[i].pesoL := 0;               // X31 - Peso L�quido (em kg)
    CTe.Transp.Vol[i].pesoB := 0;               // X32 - Peso Bruto (em kg)
    for j := 0 to 1 do
    begin
      CTe.transp.Vol[i].lacres.add;             //       TAG de grupo de Lacres - <lacres> - Ocorr�ncia 0-N
      CTe.transp.Vol[i].lacres[j].nLacre := ''; // X34 - N�mero dos Lacres
                                                //       Para Combust�veis, um Caminh�o (Toco, Truck, Carreta, Bi-Tren) pode ter at� 32 bocas.
                                                //       Ou seja at� 32 lacres. Cada lacre pode ser um n�mero de at� 8 digitos.
    end;
  end;

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG de grupo de Cobran�a - <cobr> - Ocorr�ncia 0-1                                                                *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

                                  // >>>   TAG de grupo da Fatura - <fat> - Ocorr�ncia 0-1
  CTe.Cobr.Fat.nFat := '';        // Y03 - N�mero da Fatura
  CTe.Cobr.Fat.vOrig := 0;        // Y04 - Valor Original da Fatura
  CTe.Cobr.Fat.vDesc := 0;        // Y05 - Valor do desconto
  CTe.Cobr.Fat.vLiq := 0;         // Y06 - Valor L�quido da Fatura
  for i := 0 to 1 do
  begin
    CTe.Cobr.Dup.Add;             // >>>   TAG de grupo da Duplicata - <dup> - Ocorr�ncia 0-N
    CTe.Cobr.Dup[i].nDup := '';   // Y08 - N�mero da Duplicata
    CTe.Cobr.Dup[i].dVenc := now; // Y09 - Data de vencimento
    CTe.Cobr.Dup[i].vDup := 0;    // Y10 - Valor da duplicata
  end;

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG de grupo de Informa��es Adicionais - <infAdic> - Ocorr�ncia 0-1                                               *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

  CTe.InfAdic.infAdFisco := '';                // Z02 - Informa��es Adicionais de Interesse do Fisco
  CTe.InfAdic.infCpl := '';                    // Z03 - Informa��es Complementares de interesse do Contribuinte
  for i := 0 to 9 do
  begin
    CTe.InfAdic.obsCont.Add;                   // >>>   TAG de grupo do campo de uso livre do contribuinte - <obsCont> - Ocorr�ncia 0-10
    CTe.InfAdic.obsCont[i].xCampo := '';       // Z05 - Identifica��o do campo (TAM : 1-20)
    CTe.InfAdic.obsCont[i].xTexto := '';       // Z06 - Conte�do do campo (TAM : 1-60)
  end;
  for i := 0 to 9 do
  begin
    CTe.InfAdic.obsFisco.Add;                  // >>>   TAG de grupo do campo de uso livre do Fisco - <obsFisco> - Ocorr�ncia 0-10
    CTe.InfAdic.obsFisco[i].xCampo := '';      // Z08 - Identifica��o do campo (TAM : 1-20)
    CTe.InfAdic.obsFisco[i].xTexto := '';      // Z09 - Conte�do do campo (TAM : 1-60)
  end;
  for i := 0 to 1 do
  begin
    CTe.InfAdic.procRef.Add;                   // >>>   Tag de grupo do processo referenciado - <procRef> - Ocorr�ncia 0-N
    CTe.InfAdic.procRef[i].nProc := '';        // Z08 - Indentificador do processo ou ato concess�rio
    CTe.InfAdic.procRef[i].indProc := ipSEFAZ; // Z09 - Indicador da origem do processo  (0 - SEFAZ; 1 - Justi�a Federal; 2 - Justi�a Estadual; 3 - Secex/RFB; 9 - Outros)
  end;

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG do Grupo de Exporta��o - <exporta> - Ocorr�ncia 0-1                                                           *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

                                //        O grupo sera gravado se um dos campos abaixo for preenchido
  CTe.exporta.UFembarq := '';   // ZA02 - Sigla da UF onde ocorrer� o Embarque dos produtos
  CTe.exporta.xLocEmbarq := ''; // ZA03 - Local onde ocorrer� o Embarque dos produtos

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG do Grupo de Compra - <compra> - Ocorr�ncia 0-1                                                                *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

                          //        O grupo sera gravado se um dos campos abaixo for preenchido
  CTe.compra.xNEmp := ''; // ZB02 - Nota de Empenho quando se tratar de compras p�blicas
  CTe.compra.xPed := '';  // ZB03 - Pedido
  CTe.compra.xCont := ''; // ZB04 - Contrato de Compra

  (* ----------------------------------------------------------------------------------------------------------------- *)
  (* TAG do Assinatura - <signature>                                                                                   *)
  (* ----------------------------------------------------------------------------------------------------------------- *)

                          //        Opcionalmente pode gerar o template da assinatura - Isso n�o sginifica assinar o arquivo!

  CTe.signature.URI := CTe.infCTe.Id;

  (****************************************************************************)
  (*                                                                          *)
  (*                G E R A R   O   A R Q U I V O   X M L                     *)
  (*                                                                          *)
  (****************************************************************************)

  // Criar a class TCTeW para a gera��o do arquivo conforme os dados inseridos
  // em CTe passar o objeto que cont�m os dados para a gera��o do arquivo xml

  CTeW := TCTeW.Create(CTe);

  // Informa as op��es especificas de TCTeW

  CTeW.schema := tsPL005C;

  CTeW.Opcoes.AjustarTagNro := True;                             // Ajusta o tamanho do campo para 3 posi��es com zero a esquerda
  CTew.Opcoes.GerarTagIPIparaNaoTributado := True;               // No caso de produto n�o tributado gera a TAG especifica
  CTeW.Opcoes.GerarTXTSimultaneamente := False;                  // Possibilita gerar um arquivo TXT do formato do programa SEFAZ-SP
  CTeW.Opcoes.NormatizarMunicipios := False;                     // Realiza a normatiza��o do nome do municipio conforme a tabela do IBGE
  CTeW.Opcoes.PathArquivoMunicipios := 'C:\meuCaminho\MunIBGE\'; // Indicar para aonde est�o os arquivo com os nomes dos municipios
  CTeW.Opcoes.GerarTagAssinatura := taNunca;                     // Op��o de gerar o template da assinature em branco
  CTeW.Opcoes.ValidarInscricoes := False;                        // Valida as Inscri��es. (� melhor quando essa valida��o � feita no ERP)

  // Informar as op��es comuns ao gerador ( Abaixo opc�es Default)

  CTeW.Gerador.Opcoes.IdentarXML := False;                                                   // Os arquivos que ser�o enviados para o SEFAZ n�o devem estar identados
  CTeW.Gerador.Opcoes.TamanhoIdentacao := 3;                                                 // Tamanho da identa��o do arquivo
  CTeW.Gerador.Opcoes.FormatoAlerta := 'TAG:%TAGNIVEL% ID:%ID%/%TAG%(%DESCRICAO%) - %MSG%.'; // Formato em que a mensagem vai ser gravada a ListaDeAlertas
  CTeW.Gerador.Opcoes.RetirarEspacos := True;                                                // Retira os espa�os em branco duplos nas tag do xml
  CTeW.Gerador.Opcoes.SuprimirDecimais := False;                                             // Ignora valores n�o significativos nas casa decimais
  CTeW.Gerador.Opcoes.SomenteValidar := False;                                               // N�o gera o arquivo apenas valida as informa��es

  // Gerar o arquivo XML

  CTeW.GerarXml;

  // Carrega erros

  // if CTeW.Gerador.ListaDeAlertas.Count > 0 then
  //  memo1.Lines.Add(CTeW.gerador.ListaDeAlertas.Text);

  // Gravar o arquivo no HD

  if CTeW.Gerador.ListaDeAlertas.Count = 0 then // Se n�o contiver nenhum erro, grava
  begin
    CTeW.gerador.SalvarArquivo('C:\Meu-Caminho\' + CTeW.ObterNomeArquivo);                  // N�o � necess�rio informar o parametro fpXML pois ele � default
    if CTeW.Opcoes.GerarTXTSimultaneamente then
      CTeW.gerador.SalvarArquivo('C:\Meu-Caminho\' + CTeW.ObterNomeArquivo + '.txt',fgTXT); // Informar o parametro fgTXT para gerar o arquivo no formato TEXTO
  end;

  CTeW.Free;
  CTe.Free;
fim do coment}

end;

end.

