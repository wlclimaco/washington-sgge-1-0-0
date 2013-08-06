////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//              PCN - Projeto Cooperar NFe                                    //
//                                                                            //
//   Descri��o: Classes para gera��o/leitura dos arquivos xml da NFe          //
//                                                                            //
//        site: www.projetocooperar.org/nfe                                   //
//       email: projetocooperar@zipmail.com.br                                //
//       forum: http://br.groups.yahoo.com/group/projeto_cooperar_nfe/        //
//     projeto: http://code.google.com/p/projetocooperar/                     //
//         svn: http://projetocooperar.googlecode.com/svn/trunk/              //
//                                                                            //
// Coordena��o: (c) 2009 - Paulo Casagrande                                   //
//                                                                            //
//      Equipe: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
// Desenvolvimento                                                            //
//         de Cte: Wiliam Zacarias da Silva Rosa                              //
//                                                                            //
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

{$I ACBr.inc}

unit pcnGerador;

interface uses

  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao;

type

  TGeradorOpcoes = class;

  TGerador = class(TPersistent)
  private
    FArquivoFormatoXML: AnsiString;
    FArquivoFormatoTXT: AnsiString;
    FLayoutArquivoTXT: TstringList;
    FListaDeAlertas: TStringList;
    FTagNivel: string;
    FIDNivel: string;
    FOpcoes: TGeradorOpcoes;
    FPrefixo : string;
  public
    FIgnorarTagNivel: string;
    FIgnorarTagIdentacao: string;
    constructor Create;
    destructor Destroy; override;
    function SalvarArquivo(const CaminhoArquivo: string; const FormatoGravacao: TpcnFormatoGravacao = fgXML): Boolean;
    procedure wGrupo(const TAG: string; ID: string = ''; const Identar: Boolean = True);
    procedure wCampo(const Tipo: TpcnTipoCampo; ID, TAG: string; const min, max, ocorrencias: smallint; const valor: variant; const Descricao: string = '');
    procedure wGrupoNFSe(const TAG: string; ID: string = ''; const Identar: Boolean = True);
    procedure wCampoNFSe(const Tipo: TpcnTipoCampo; ID, TAG: string; const min, max, ocorrencias: smallint; const valor: variant; const Descricao: string = '');
    procedure wCampoCNPJCPF(const ID1, ID2: string; CNPJCPF: string; const cPais: Integer);
    procedure wCampoCNPJ(const ID: string; CNPJ: string; const cPais: Integer; obrigatorio: Boolean);
    procedure wCampoCPF(const ID: string; CPF: string; const cPais: Integer; obrigatorio: Boolean);
    procedure wAlerta(const ID, TAG, Descricao, Alerta: string);
    procedure wTexto(const Texto: string);
    procedure gtNivel(ID: string);
    procedure gtCampo(const Tag, ConteudoProcessado: string);
    procedure gtAjustarRegistros(const ID: string);
  published
    property ArquivoFormatoXML: AnsiString read FArquivoFormatoXML write FArquivoFormatoXML;
    property ArquivoFormatoTXT: AnsiString read FArquivoFormatoTXT write FArquivoFormatoTXT;
    property IDNivel: string read FIDNivel write FIDNivel;
    property ListaDeAlertas: TStringList read FListaDeAlertas write FListaDeAlertas;
    property LayoutArquivoTXT: TStringList read FLayoutArquivoTXT write FLayoutArquivoTXT;
    property Opcoes: TGeradorOpcoes read FOpcoes write FOpcoes;
    property Prefixo: string read FPrefixo write FPrefixo;
  end;

  TGeradorOpcoes = class(TPersistent)
  private
    FSomenteValidar: boolean;
    FIdentarXML: boolean;
    FRetirarEspacos: boolean;
    FRetirarAcentos: boolean;
    FNivelIdentacao: integer;
    FTamanhoIdentacao: integer;
    FSuprimirDecimais: boolean;
    FTagVaziaNoFormatoResumido: boolean;
    FFormatoAlerta: string;
  published
    property SomenteValidar: boolean read FSomenteValidar write FSomenteValidar;
    property RetirarEspacos: boolean read FRetirarEspacos write FRetirarEspacos;
    property RetirarAcentos: boolean read FRetirarAcentos write FRetirarAcentos;
    property IdentarXML: boolean read FIdentarXML write FIdentarXML;
    property TamanhoIdentacao: integer read FTamanhoIdentacao write FTamanhoIdentacao;
    property SuprimirDecimais: boolean read FSuprimirDecimais write FSuprimirDecimais;
    property TagVaziaNoFormatoResumido: boolean read FTagVaziaNoFormatoResumido write FTagVaziaNoFormatoResumido;
    property FormatoAlerta: string read FFormatoAlerta write FFormatoAlerta;
  end;

const

  ERR_MSG_MAIOR = 'Tamanho maior que o m�ximo permitido';
  ERR_MSG_MENOR = 'Tamanho menor que o m�nimo permitido';
  ERR_MSG_VAZIO = 'Nenhum valor informado';
  ERR_MSG_INVALIDO = 'Conte�do inv�lido';
  ERR_MSG_MAXIMO_DECIMAIS = 'Numero m�ximo de casas decimais permitidas';
  ERR_MSG_MAIOR_MAXIMO = 'N�mero de ocorr�ncias maior que o m�ximo permitido - M�ximo ';
  ERR_MSG_GERAR_CHAVE = 'Erro ao gerar a chave da NFe!';
  ERR_MSG_FINAL_MENOR_INICIAL = 'O numero final n�o pode ser menor que o inicial';
  ERR_MSG_ARQUIVO_NAO_ENCONTRADO = 'Arquivo n�o encontrado';
  ERR_MSG_SOMENTE_UM = 'Somente um campo deve ser preenchido';
 // Incluido por Italo em 18/07/2012
  ERR_MSG_MENOR_MINIMO = 'N�mero de ocorr�ncias menor que o m�nimo permitido - M�nimo ';

  CODIGO_BRASIL = 1058;

  ENCODING_UTF8 = '?xml version="1.0" encoding="UTF-8"?';
  ENCODING_UTF8_STD = '?xml version="1.0" encoding="UTF-8" standalone="no"?';
  NAME_SPACE = 'xmlns="http://www.portalfiscal.inf.br/nfe"';
  NAME_SPACE_CTE = 'xmlns="http://www.portalfiscal.inf.br/cte"';
  NAME_SPACE_CFE = 'xmlns="http://www.fazenda.sp.gov.br/sat"';
  NAME_SPACE_MDFE = 'xmlns="http://www.portalfiscal.inf.br/mdfe"';

  V0_02 = 'versao="0.02"';
  V1_00 = 'versao="1.00"';
  V1_01 = 'versao="1.01"';
  V1_02 = 'versao="1.02"';
  V1_03 = 'versao="1.03"';
  V1_04 = 'versao="1.04"';
  V1_07 = 'versao="1.07"';
  V1_10 = 'versao="1.10"';
  V2_00 = 'versao="2.00"';
  V2_01 = 'versao="2.01"';

  VM_Rodo_1_04  = 'versaoModal="1.04"';
  VM_Aereo_1_04 = 'versaoModal="1.04"';
  VM_Aqua_1_04  = 'versaoModal="1.04"';
  VM_Ferro_1_04 = 'versaoModal="1.04"';
  VM_Duto_1_04  = 'versaoModal="1.04"';

  // NFE //

  DSC_AAMM = 'Ano e M�s';
  DSC_ANOFAB = 'Ano de Fabrica��o';
  DSC_ANOMOD = 'Ano do Modelo de Fabrica��o';
  DSC_CCOR = 'Cor do Ve�culo';
  DSC_CDV = 'Digito Verificador';
  DSC_CEAN = 'C�digo de Barra do Item';
  DSC_CEANTRIB = 'C�digo de Barra do Item Tributa��o';
  DSC_CENQ = 'C�digo de Enquadramento Legal do IPI';
  DSC_CEP = 'CEP';
  DSC_CEXPORTADOR = 'C�digo do exportador';
  DSC_CFABRICANTE = 'Fabricante';
  DSC_CFOP = 'CFOP';
  DSC_CHASSI = 'N�mero do chassi';
  DSC_CHAVE = 'Chave da NFe';
  DSC_CLENQ = 'Classe de enquadramento do IPI para Cigarros e Bebidas';
  DSC_CLISTSERV = 'Lista Presta��o de Servi�os';
  DSC_CSITTRIB = 'C�digo de Tributa��o do ISSQN';
  DSC_CILIN = 'Cilindradas';
  DSC_CMT = 'Capacidade M�xima de Tra��o';
  DSC_CMOD = 'Modelo do Ve�culo';
  DSC_CCORDEN = 'C�digo da Cor DENATRAN';
  DSC_TPREST = 'Restri��o';
  DSC_CMUN = 'C�digo do Munic�pio';
  DSC_CMUNFG = 'C�digo do Munic�pio FG';
  DSC_CNAE = 'Classifica��o Nacional de Atividades Econ�micas';
  DSC_CRT = 'C�digo de Regime Tribut�rio';
  DSC_CNF = 'N�mero da Nota Fiscal Eletr�nica';
  DSC_CODIF = 'C�digo de autoriza��o / registro do CODIF';
  DSC_CONDVEIC = 'Condi��es do Ve�culo';
  DSC_CPAIS = 'C�digo do Pa�s';
  DSC_CNPJPROD = 'CNPJ do produtor da mercadoria, quando diferente do emitente';
  DSC_CPROD = 'C�digo do produto ou servi�o';
  DSC_CPRODANP = 'C�digo do produto ANP';
  DSC_CSELO = 'C�digo do selo';
  DSC_CST = 'C�digo da situa��o tribut�ria ';
  DSC_CSOSN = 'C�digo de Situa��o da Opera��o � Simples Nacional';
  DSC_CUF = 'C�digo do UF (Unidade da Federa��o)';
  DSC_DDESEMB = 'Data do Desembara�o Aduaneiro';
  DSC_DDI = 'Data de registro da DI/DSI/DA';
  DSC_DEMI = 'Data de emiss�o';
  DSC_DESCR = 'Descri��o completa';
  DSC_DFAB = 'Data de fabrica��o';
  DSC_DIST = 'Dist�ncia entre os eixos';
  DSC_DPAG = 'Data de pagamento do Documento de Arrecada��o';
  DSC_DSAIENT = 'Data de sa�da ou entrada da mercadoria/produto';
  DSC_HSAIENT = 'Hora de sa�da ou entrada da mercadoria/produto';
  DSC_DVAL = 'Data de validade';
  DSC_DVENC = 'Data de vencimento';
  DSC_ESP = 'Esp�cie dos volumes transportados';
  DSC_ESPVEIC = 'Esp�cie de ve�culo';
  DSC_EXTIPI = 'EX_TIPI';
  DSC_FINNFE = 'Finalidade de emiss�o da NFe';
  DSC_FONE = 'Telefone';
  DSC_GENERO = 'G�nero do produto ou servi�o';
  DSC_IE = 'Inscri��o Estadual';
  DSC_IEST = 'Inscri��o Estadual do Substituto tribut�rio';
  DSC_IM = 'Inscri��o Municipal';
  DSC_INDPAG = 'Indicador da forma de pagamento';
  DSC_INDPROC = 'Indicador da origem do processo';
  DSC_INFADFISCO = 'Informa��es adicionais de interesse do Fisco';
  DSC_INFADPROD = 'Informa��es adicionais do Produto';
  DSC_INFCPL = 'Informa��es complementares de interesse do contribuinte';
  DSC_ISUF = 'Inscri��o na SUFRAMA';
  DSC_EMAIL = 'Endere�o de Email';
  DSC_IPITrib = 'IPI Tribut�vel';
  DSC_MARCA = 'Marca dos volumes transportados';
  DSC_MATR = 'Matr�cula do agente';
  DSC_MOD = 'Modelo';
  DSC_NECF = 'N�mero de ordem seq�encial do ECF';
  DSC_NCOO = 'N�mero do Contador de Ordem de Opera��o - COO';
  DSC_MODBC = 'Modalidade de determina��o da BC do ICMS';
  DSC_MODBCST = 'Modalidade de determina��o da BC do ICMS ST';
  DSC_MOTDESICMS = 'Motivo da desonera��o do ICMS';
  DSC_MODFRETE = 'Modalidade do Frete';
  DSC_NADICAO = 'Numero da Adi��o';
  DSC_NATOP = 'Descri��o da Natureza da Opera��o';
  DSC_DHCONT = 'Data e Hora de entrada em contingencia';
  DSC_XJUSTCONT = 'Justificativa de entrada em contingencia';
  DSC_NCANO = 'Numero de s�rie do cano';
  DSC_NCM = 'C�digo NCM';
  DSC_NDI = 'Numero do Documento de Importa��o DI/DSI/DA';
  DSC_nDAR = 'N�mero do Documento Arrecada��o de Receita';
  DSC_NDUP = 'N�mero da duplicata';
  DSC_NFAT = 'N�mero da fatura';
  DSC_NITEM = 'Numero do item';
  DSC_NLACRE = 'N�mero dos Lacres';
  DSC_NLOTE = 'N�mero do Lote do medicamento';
  DSC_NMOTOR = 'N�mero de Motor';
  DSC_NNF = 'N�mero do Documento Fiscal';
  DSC_NPROC = 'Identificador do processo ou ato concess�rio';
  DSC_NRO = 'N�mero';
  DSC_NSEQADIC = 'Numero seq�encial do item dentro da adi��o';
  DSC_NSERIE = 'N�mero de s�rie';
  DSC_NVOL = 'Numera��o dos volumes transportados';
  DSC_ORIG = 'Origem da mercadoria';
  DSC_OBSCONT = 'Observa��es de interesse do contribuite';
  DSC_OBSFISCO = 'Observa��es de interesse do fisco';
  DSC_PCOFINS = 'Al�quota da COFINS (em percentual)';
  DSC_PESOB = 'Peso Bruto (em kg)';
  DSC_PESOL = 'Peso L�quido (em kg)';
  DSC_PICMS = 'Al�quota do imposto';
  DSC_PICMSRET = 'Al�quota da Reten��o';
  DSC_PICMSST = 'Al�quota do imposto do ICMS ST';
  DSC_PIPI = 'Al�quota do IPI';
  DSC_PISOUTR = 'Grupo PIS outras opera��es';
  DSC_PLACA = 'Placa do Ve�culo';
  DSC_PMVAST = 'Percentual da margem de valor Adicionado do ICMS ST';
  DSC_POT = 'Pot�ncia Motor';
  DSC_PPIS = 'Al�quota do PIS (em percentual)';
  DSC_PREDBC = 'Percentual da Redu��o de BC';
  DSC_PCREDSN = 'Al�quota aplic�vel de c�lculo do cr�dito (Simples Nacional).';
  DSC_VCREDICMSSN = 'Valor cr�dito do ICMS que pode ser aproveitado nos termos do art. 23 da LC 123 (Simples Nacional)';
  DSC_PREDBCST = 'Percentual da Redu��o de BC do ICMS ST';
  DSC_UFST = 'UF para qual � devido o ICMS ST';
  DSC_PBCOP = 'Percentual da BC opera��o pr�pria';
  DSC_PROCEMI = 'Processo de emiss�o da NF-e';
  DSC_QBCPROD = 'BC da CIDE';
  DSC_QCOM = 'Quantidade Comercial';
  DSC_QLOTE = 'Quantidade de produto no Lote do medicamento';
  DSC_QSELO = 'Quantidade de selo de controle';
  DSC_QTEMP = 'Quantidade de combust�vel faturada � temperatura ambiente.';
  DSC_QTRIB = 'Quantidade Tribut�vel';
  DSC_QUNID = 'Quantidade total na unidade padr�o para tributa��o (somente para os produtos tributados por unidade)';
  DSC_QVOL = 'Quantidade de volumes transportados';
  DSC_REBOQUE = 'Reboque';
  DSC_REFNFE = 'Chave de acesso das NF-e referenciadas';
  DSC_RENAVAM = 'RENAVAM';
  DSC_REPEMI = 'Reparti��o Fiscal emitente';
  DSC_RNTC = 'Registro Nacional de Transportador de Carga (ANTT)';
  DSC_VAGAO = 'Identifica��o do vag�o';
  DSC_BALSA = 'Identifica��o da balsa';
  DSC_SERIE = 'S�rie do Documento Fiscal';
  DSC_TPAMB = 'Identifica��o do Ambiente';
  DSC_TPARMA = 'Indicador do tipo de arma de fogo';
  DSC_TPCOMB = 'Tipo de combust�vel';
  DSC_TPEMIS = 'Forma de Emiss�o da NF-e';
  DSC_TPIMP = 'Formato de Impress�o do DANFE';
  DSC_TPNF = 'Tipo do Documento Fiscal';
  DSC_TPOP = 'Tipo da opera��o';
  DSC_TPPINT = 'Tipo de Pintura';
  DSC_TPVEIC = 'Tipo de Ve�culo';
  DSC_UCOM = 'Unidade Comercial';
  DSC_UF = 'Sigla da UF';
  DSC_UFCONS = 'Sigla da UF de consumo';
  DSC_UFDESEMB = 'Sigla da UF onde ocorreu o Desembara�o Aduaneiro';
  DSC_UFEMBARQ = 'Sigla da UF onde ocorrer� o embarque dos produtos';
  DSC_UTRIB = 'Unidade Tribut�vel';
  DSC_VALIQ = 'Al�quota';
  DSC_VALIQPROD = 'Valor da al�quota (em reais)';
  DSC_VBC = 'Valor da BC do ICMS';
  DSC_VBCICMS = 'BC do ICMS';
  DSC_VBCICMSST = 'BC do ICMS ST retido';
  DSC_VBCICMSSTCONS = 'Valor do ICMS ST da UF de consumo';
  DSC_VBCICMSSTDEST = 'BC do ICMS ST da UF de destino';
  DSC_VBCIRRF = 'Base de C�lculo do IRRF';
  DSC_VBCRET = 'BC da Reten��o do ICMS';
  DSC_VBCRETPREV = 'Base de C�lculo da Reten��o da Previd�ncia Social';
  DSC_VBCST = 'Valor da BC do ICMS ST';
  DSC_VBCSTRET = 'Valor da BC do ICMS ST Retido';
  DSC_VCIDE = 'Valor da CIDE';
  DSC_VCOFINS = 'Valor do COFINS';
  DSC_VDAR = 'Valor total constante no Documento de arrecada��o de Receita';
  DSC_VDESC = 'Valor do desconto';
  DSC_INDTOT = 'Indicador de soma no total da NFe';
  DSC_VDESCDI = 'Valor do desconto do item da DI � adi��o';
  DSC_NITEMPED = 'Item do Pedido de Compra da DI � adi��o';
  DSC_VDESPADU = 'Valor das despesas aduaneiras';
  DSC_VDUP = 'Valor da duplicata';
  DSC_VERPROC = 'Vers�o do Processo de emiss�o da NF-e';
  DSC_VFRETE = 'Valor Total do Frete';
  DSC_VICMS = 'Valor do ICMS';
  DSC_VICMSRET = 'Valor do ICMS Retido';
  DSC_VICMSST = 'Valor do ICMS Substitui��o Tributaria';
  DSC_VICMSSTRET = 'Valor do ICMS Substitui��o Tributaria Retido';
  DSC_VICMSSTCONS = 'Valor do ICMS Substitui��o Tributaria da UF de Consumo';
  DSC_VICMSSTDEST = 'Valor do ICMS Substitui��o Tributaria da UF de Destino';
  DSC_VII = 'Valor do Imposto de Importa��o';
  DSC_VIN = 'Condi��o do VIN';
  DSC_VIOF = 'Valor do Imposto sobre opera��es Financeiras';
  DSC_vIPI = 'Valor do Imposto sobre Produtos Industrializados';
  DSC_VIRRF = 'Valor do Imposto de Renda Retido na Fonte';
  DSC_VBCISS = 'Valor da Base de C�lculo do ISSQN';
  DSC_VISS = 'Valor do Imposto sobre Servi�o';
  DSC_VISSQN = 'Valor do Imposto sobre Servi�o de Qualquer Natureza';
  DSC_VLIQ = 'Valor L�quido da Fatura';
  DSC_VNF = 'Valor Total da NF-e';
  DSC_VORIG = 'Valor Original da Fatura';
  DSC_VOUTRO = 'Outras Despesas Acess�rias';
  DSC_VPIS = 'Valor do PIS';
  DSC_VPMC = 'Pre�o M�ximo ao Consumidor';
  DSC_VPROD = 'Valor Total Bruto dos Produtos ou Servi�os';
  DSC_VRETCOFINS = 'Valor Retido do COFINS';
  DSC_VRETCSLL = 'Valor Retido da CONTRIBUI��O SOCIAL SOBRE O LUCRO L�QUIDO';
  DSC_VRETPIS = 'Valor Retido do PIS';
  DSC_VRETPREV = 'Valor da Reten��o da Previd�ncia Social';
  DSC_VSEG = 'Valor Total do Seguro';
  DSC_VSERV = 'Valor total dos Servi�os sob n�o incid�ncia ou n�o Tributados pelo ICMS  / Valor do Servi�o';
  DSC_VST = 'Valor TOTAL Icms substitui��o Tribut�ria';
  DSC_VUNCOM = 'Valor Unit�rio de Comercializa��o';
  DSC_VUNID = 'Valor por Unidade Tribut�vel';
  DSC_VUNTRIB = 'Valor unit�rio de Tributa��o';
  DSC_XAGENTE = 'Nome do agente';
  DSC_XBAIRRO = 'Bairro';
  DSC_XCAMPO = 'Identifica��o do Campo';
  DSC_XCONT = 'Contrato';
  DSC_XCOR = 'Descri��o da Cor';
  DSC_XCPL = 'Complemento (Endere�o)';
  DSC_XENDER = 'Endere�o Completo';
  DSC_XFANT = 'Nome de Fantasia';
  DSC_XLGR = 'Logradouro';
  DSC_XLOCDESEMB = 'Local de Desembara�o';
  DSC_XLOCEMBARQ = 'Local onde ocorrer� o embarque dos produtos';
  DSC_XMUN = 'Nome do Munic�pio';
  DSC_XNEMP = 'Nota de Empenho';
  DSC_XNOME = 'Raz�o Social ou Nome';
  DSC_XPAIS = 'Nome do Pa�s';
  DSC_XPED = 'Pedido';
  DSC_XPROD = 'Descri��o do Produto ou Servi�o';
  DSC_XTEXTO = 'Conte�do do Campo';
  DSC_XORGAO = 'Org�o emitente';
  DSC_DigestValue = 'Digest Value';
  DSC_SignatureValue = 'Signature Value';
  DSC_X509Certificate = 'X509 Certificate';
  DSC_XSERV = 'Descri��o do servi�o';
  DSC_ANO = 'Ano';
  DSC_CNPJ = 'CNPJ(MF)';
  DSC_CPF = 'CPF';
  DSC_NNFINI = 'Numero inicial';
  DSC_NNFFIN = 'Numero final';
  DSC_XJUST = 'Justificativa';
  DSC_CHNFE = 'Chave da NFe';
  DSC_NPROT = 'Numero do protocolo';
  DSC_NREC = 'Numero do recibo';
  DSC_IDLOTE = 'Numero do Lote';
  DSC_VERAPLIC = 'Vers�o do aplicativo';
  DSC_NREGDPEC = 'N�mero de registro do DPEC';
  DSC_DPEC_ID = 'Grupo de Identifica��o da TAG a ser assinada. DPEC + CNPJ do emissor.';
  DSC_SAFRA = 'Identifica��o da safra';
  DSC_REF = 'M�s e ano de refer�ncia';
  DSC_FORDIA = 'Grupo de Fornecimento di�rio de cana';
  DSC_DIA = 'Dia';
  DSC_QTDE = 'Quantidade';
  DSC_QTOTMES = 'Quantidade Total do M�s';
  DSC_QTOTANT = 'Quantidade Total Anterior';
  DSC_TOTGER = 'Quantidade Total Geral';
  DSC_DEDUC = 'Grupo de Dedu��es � Taxas e Contribui��es';
  DSC_XDED = 'Descri��o da Dedu��o';
  DSC_VDED = 'Valor da Dedu��o';
  DSC_VFOR = 'Valor dos Fornecimentos';
  DSC_VTOTDED = 'Valor Total da Dedu��o';
  DSC_VLIQFOR = 'Valor L�quido dos Fornecimentos';
  DSC_INDNFE = 'Indicador de NF-e consultada';
  DSC_INDEMI = 'Indicador do Emissor da NF-e';
  DSC_ULTNSU = '�ltimo NSU recebido pela Empresa';

  // CTE //
  DSC_CHCTE    = 'Chave do CTe';
  DSC_TPCTe    = 'Tipo do Conhecimento';
  DSC_REFCTE   = 'Chave de acesso do CT-e referenciado';
  DSC_CMUNEMI  = 'C�digo do Munic�pio onde o CT-e est� sendo emitido';
  DSC_ORIGCALC = 'Munic�pio origem para efeito de c�lculo do frete';
  DSC_DESTCALC = 'Munic�pio destino para efeito de c�lculo do frete';
  DSC_XOBS     = 'Observa��es Gerais';
  DSC_TOMA     = 'Tomador do Servi�o';
  DSC_INFNF    = 'Informa��es da Nota Fiscal';
  DSC_INFNFE   = 'Informa��es da Nota Fiscal Eletr�nica';
  DSC_NDOC     = 'N�mero da Nota Fiscal';
  DSC_PESO     = 'Peso';
  DSC_TPDOC    = 'Tipo de documento origin�rio';
  DSC_OUTROS   = 'Descri��o';
  DSC_VTPREST  = 'Valor total da presta��o do servi�o';
  DSC_VREC     = 'Valor a Receber';
  DSC_XNOMEC   = 'Nome do Componente';
  DSC_VCOMP    = 'Valor do Componente';
  DSC_VCRED    = 'Valor do Cr�dito outorgado/presumido';
  DSC_RESPSEG  = 'Respons�vel pelo Seguro';
  DSC_XSEG     = 'Nome da Seguradora';
  DSC_NAPOL    = 'N�mero da Ap�lice';
  DSC_NAVER    = 'N�mero da Averba��o';
  DSC_VMERC    = 'Valor da mercadoria para efeito de averba��o';

  DSC_INFSEG   = 'Informa��es de seguro da carga';
  DSC_RNTRC    = 'Registro Nacional de Transportadores Rodovi�rios de Carga';
  DSC_DPREV    = 'Data prevista de entrega';
  DSC_LOTA     = 'Indicador de lota��o';
  DSC_CINT     = 'C�digo interno do emitente';
  DSC_LACR     = 'Grupo de lacres';
  DSC_TPPROP   = 'Tipo de Propriet�rio';
  DSC_INFOUTRO = 'informa��es dos demais documentos';
  DSC_VDOC     = 'Valor do documento';
  DSC_MODAL    = 'Tipo de Modal';
  DSC_TPSERV   = 'Tipo do Servi�o';
  DSC_RETIRA   = 'Recebedor retira na Filial?';
  DSC_PRED     = 'Produto predominante';
  DSC_OUTCAT   = 'Outras caracter�sticas da carga';
  DSC_VTMERC   = 'Valor total da mercadoria';
  DSC_INFQ     = 'Informa��es de quantidades da Carga';
  DSC_CUNID    = 'C�digo da unidade de medida';
  DSC_TPMED    = 'Tipo da Medida';
  DSC_QTD      = 'Quantidade';
  DSC_DRET     = 'Detalhes da Retirada';

  //CFe - Cupom Fiscal Eletr�nico - SAT
  DSC_VDESCSUBTOT = 'Valor de Desconto sobre Subtotal';
  DSC_VACRESSUBTOT = 'Valor de Acr�scimo sobre Subtotal';
  DSC_VPISST = 'Valor do PIS ST';
  DSC_VCOFINSST = 'Valor do COFINS ST';
  DSC_VCFE = 'Valor Total do CF-e';
  DSC_VCFELEI12741 = 'Valor aproximado dos tributos do CFe-SAT � Lei 12741/12.';
  DSC_VDEDUCISS = 'Valor das dedu��es para ISSQN';
  DSC_CSERVTRIBMUN = 'Codigo de tributa��o pelo ISSQN do municipio';
  DSC_CNATOP = 'Natureza da Opera��o de ISSQN';
  DSC_INDINCFISC = 'Indicador de Incentivo Fiscal do ISSQN';
  DSC_COFINSST = 'Grupo de COFINS Substitui��o Tribut�ria';
  DSC_REGTRIB = 'C�digo de Regime Tribut�rio';
  DSC_REGISSQN = 'Regime Especial de Tributa��o do ISSQN';
  DSC_RATISSQN = 'Indicador de rateio do Desconto sobre subtotal entre itens sujeitos � tributa��o pelo ISSQN.';
  DSC_NCFE = 'N�mero do Cupom Fiscal Eletronico';
  DSC_HEMI = 'Hora de emiss�o';
  DSC_SIGNAC = 'Assinatura do Aplicativo Comercial';
  DSC_QRCODE = 'Assinatura Digital para uso em QRCODE';
  DSC_MP = 'Grupo de informa��es sobre Pagamento do CFe';
  DSC_CMP = 'C�digo do Meio de Pagamento';
  DSC_VMP = 'Valor do Meio de Pagamento';
  DSC_CADMC = 'Credenciadora de cart�o de d�bito ou cr�dito';
  DSC_VTROCO = 'Valor do troco';
  DSC_VITEM = 'Valor l�quido do Item';
  DSC_VRATDESC = 'Rateio do desconto sobre subtotal';
  DSC_VRATACR = 'Rateio do acr�scimo sobre subtotal';
  DSC_NUMEROCAIXA = 'N�mero do Caixa ao qual o SAT est� conectado';
  DSC_VITEM12741 = 'Valor aproximado dos tributos do Produto ou servi�o � Lei 12741/12';

implementation

uses DateUtils, ACBrConsts;

{ TGerador }

constructor TGerador.Create;
begin
  FOpcoes := TGeradorOpcoes.Create;
  FOpcoes.FIdentarXML := False;
  FOpcoes.FTamanhoIdentacao := 3;
  FOpcoes.FFormatoAlerta := 'TAG:%TAGNIVEL% ID:%ID%/%TAG%(%DESCRICAO%) - %MSG%.'; // Vide coment�rio em wAlerta
  FOpcoes.FRetirarEspacos := True;
  FOpcoes.FRetirarAcentos := True;
  FOpcoes.FSuprimirDecimais := False;
  FOpcoes.FSomenteValidar := False;
  FOpcoes.FTagVaziaNoFormatoResumido := True;
  FListaDeAlertas := TStringList.Create;
  FLayoutArquivoTXT := TStringList.Create;
end;

destructor TGerador.Destroy;
begin
  FOpcoes.Free;
  FListaDeAlertas.Free;
  FLayoutArquivoTXT.Free;
  FIgnorarTagNivel := '!@#';
  FIgnorarTagIdentacao := '!@#';
  inherited Destroy;
end;

function TGerador.SalvarArquivo(const CaminhoArquivo: string; const FormatoGravacao: TpcnFormatoGravacao = fgXML): Boolean;
var
  ArquivoGerado: TStringList;
begin
  // Formato de grava��o somente � v�lido para NFe
  ArquivoGerado := TStringList.Create;
  try
    try
      if FormatoGravacao = fgXML then
        ArquivoGerado.Add(FArquivoFormatoXML)
      else
        ArquivoGerado.Add(FArquivoFormatoTXT);
      ArquivoGerado.SaveToFile(CaminhoArquivo);
      Result := True;
    except
      Result := False;
      raise;
    end;
  finally
    ArquivoGerado.Free;
  end;
end;

procedure TGerador.wAlerta(const ID, TAG, Descricao, Alerta: string);
var
  s: string;
begin
  // O Formato da mensagem de erro pode ser alterado pelo usuario alterando-se a property FFormatoAlerta: onde;
  // %TAGNIVEL%  : Representa o Nivel da TAG; ex: <transp><vol><lacres>
  // %TAG%       : Representa a TAG; ex: <nLacre>
  // %ID%        : Representa a ID da TAG; ex X34
  // %MSG%       : Representa a mensagem de alerta
  // %DESCRICAO% : Representa a Descri��o da TAG
  s := FOpcoes.FFormatoAlerta;
  s := stringReplace(s, '%TAGNIVEL%', FTagNivel, [rfReplaceAll]);
  s := stringReplace(s, '%TAG%', TAG, [rfReplaceAll]);
  s := stringReplace(s, '%ID%', ID, [rfReplaceAll]);
  s := stringReplace(s, '%MSG%', Alerta, [rfReplaceAll]);
  s := stringReplace(s, '%DESCRICAO%', Trim(Descricao), [rfReplaceAll]);
  if Trim(Alerta) <> '' then
    FListaDeAlertas.Add(s);
end;

procedure TGerador.wGrupo(const TAG: string; ID: string = ''; const Identar: Boolean = True);
begin
  // A propriedade FIgnorarTagNivel � utilizada para Ignorar TAG
  // na constru��o dos n�veis para apresenta��o na mensagem de erro.
  gtNivel(ID);
  // Caso a tag seja um Grupo com Atributo
  if (pos('="', TAG) > 0) or (pos('= "', TAG) > 0) then
    gtCampo(RetornarConteudoEntre(TAG, ' ', '='), RetornarConteudoEntre(TAG, '"', '"'));
  //
  if not SubStrEmSubStr(TAG, FIgnorarTagNivel) then
  begin
    if TAG[1] <> '/' then
      FTagNivel := FTagNivel + '<' + TAG + '>';
    if (TAG[1] = '/') and (Copy(TAG, 2, 3) = 'det') then
      FTagNivel := copy(FTagNivel, 1, pos('<det', FTagNivel) - 1)
    else
      FTagNivel := StringReplace(FTagNivel, '<' + Copy(TAG, 2, MaxInt) + '>', '', []);
  end;
  //
  if (Identar) and (TAG[1] = '/') then
    Dec(FOpcoes.FNivelIdentacao);
  if SubStrEmSubStr(TAG, FIgnorarTagIdentacao) then
    Dec(FOpcoes.FNivelIdentacao);
  //
  if FOpcoes.IdentarXML then
    FArquivoFormatoXML := FArquivoFormatoXML + StringOfChar(' ', FOpcoes.FTamanhoIdentacao * FOpcoes.FNivelIdentacao) + '<' + tag + '>' + #13#10
  else
    FArquivoFormatoXML := FArquivoFormatoXML + '<' + tag + '>';
  if (Identar) and (TAG[1] <> '/') then
    Inc(FOpcoes.FNivelIdentacao);
end;

procedure TGerador.wCampoCNPJCPF(const ID1, ID2: string; CNPJCPF: string; const cPais: Integer);
var
  Tamanho: integer;
begin
  if cPais <> 1058 then
  begin
    wCampo(tcStr, ID1, 'CNPJ', 00, 00, 1, '');
    exit;
  end;
  CNPJCPF := SomenteNumeros(trim(CNPJCPF));
  Tamanho := length(CNPJCPF);
  if Tamanho = 11 then
  begin
    wCampo(tcStr, ID2, 'CPF  ', 11, 11, 1, CNPJCPF);
    if not ValidarCPF(CNPJCPF) then
      wAlerta(ID2, 'CPF', 'CPF', ERR_MSG_INVALIDO);
  end
  else if Tamanho = 14 then
  begin
    wCampo(tcStr, ID1, 'CNPJ', 14, 14, 1, CNPJCPF);
    if not ValidarCNPJ(CNPJCPF) then
      wAlerta(ID1, 'CNPJ', 'CNPJ', ERR_MSG_INVALIDO);
  end;
  if ((Tamanho <> 11) and (Tamanho <> 14)) then
    wAlerta(ID1 + '-' + ID2, 'CNPJ-CPF', 'CNPJ/CPF', ERR_MSG_VAZIO);
end;

procedure TGerador.wCampoCNPJ(const ID: string; CNPJ: string; const cPais: Integer; obrigatorio: Boolean);
begin
  if cPais <> 1058 then
  begin
    wCampo(tcStr, ID, 'CNPJ', 00, 00, 1, '');
    exit;
  end;
  CNPJ := SomenteNumeros(trim(CNPJ));
  if obrigatorio then
    wCampo(tcEsp, ID, 'CNPJ', 14, 14, 1, CNPJ, DSC_CNPJ)
  else
    wCampo(tcEsp, ID, 'CNPJ', 14, 14, 0, CNPJ, DSC_CNPJ);
  if not ValidarCNPJ(CNPJ) then
    wAlerta(ID, 'CNPJ', DSC_CNPJ, ERR_MSG_INVALIDO);
end;

procedure TGerador.wCampoCPF(const ID: string; CPF: string; const cPais: Integer; obrigatorio: Boolean);
begin
  if cPais <> 1058 then
  begin
    wCampo(tcStr, ID, 'CPF', 00, 00, 1, '');
    exit;
  end;
  CPF := SomenteNumeros(trim(CPF));
  if obrigatorio then
    wCampo(tcEsp, ID, 'CPF', 11, 11, 1, CPF, DSC_CPF)
  else
    wCampo(tcEsp, ID, 'CPF', 11, 11, 0, CPF, DSC_CPF);
  if not ValidarCPF(CPF) then
    wAlerta(ID, 'CPF', DSC_CPF, ERR_MSG_INVALIDO);
end;

procedure TGerador.wCampo(const Tipo: TpcnTipoCampo; ID, TAG: string; const min, max, ocorrencias: smallint; const valor: variant; const Descricao: string = '');
var
  NumeroDecimais: smallint;
  Limite: Integer;
  alerta, ConteudoProcessado: string;
  wAno, wMes, wDia, wHor, wMin, wSeg, wMse: Word;
  EstaVazio: boolean;
begin
  ID                  := Trim(ID);
  Tag                 := Trim(TAG);
  EstaVazio           := False;
  NumeroDecimais      := 0;
  ConteudoProcessado  := '';
  Limite              := max;
  case Tipo of
    tcStr   : begin
                ConteudoProcessado := trim(valor);
                EstaVazio := ConteudoProcessado = '';

              end;
    tcDat,
    tcDatCFe: begin
                DecodeDate(valor, wAno, wMes, wDia);
                ConteudoProcessado := FormatFloat('0000', wAno) + '-' + FormatFloat('00', wMes) + '-' + FormatFloat('00', wDia);
                if Tipo = tcDatCFe then
                  ConteudoProcessado := SomenteNumeros(ConteudoProcessado);
                EstaVazio := ((wAno = 1899) and (wMes = 12) and (wDia = 30));
              end;
    tcHor,
    tcHorCFe: begin
                DecodeTime(valor, wHor, wMin, wSeg, wMse);
                ConteudoProcessado := FormatFloat('00', wHor) + ':' + FormatFloat('00', wMin) + ':' + FormatFloat('00', wSeg);
                if Tipo = tcHorCFe then
                   ConteudoProcessado := SomenteNumeros(ConteudoProcessado);
                EstaVazio := (wHor = 0) and (wMin = 0) and (wSeg = 0);
              end;
    tcDatHor : begin
                DecodeDateTime(valor, wAno, wMes, wDia, wHor, wMin, wSeg, wMse);
                ConteudoProcessado := FormatFloat('0000', wAno) + '-' +
                FormatFloat('00', wMes) + '-' +
                FormatFloat('00', wDia) + 'T' +
                FormatFloat('00', wHor) + ':' +
                FormatFloat('00', wMin) + ':' +
                FormatFloat('00', wSeg);
                EstaVazio := ((wAno = 1899) and (wMes = 12) and (wDia = 30));
              end;
      tcDe2,
      tcDe3,
      tcDe4,
      tcDe6,  // Incluido por Italo em 30/09/2010
      tcDe10 : begin
                // adicionar um para que o m�ximo n�o considere a virgula
                Limite := Limite + 1;

                // Tipo numerico com decimais
                  case Tipo of
                    tcDe2 : NumeroDecimais :=  2;
                    tcDe3 : NumeroDecimais :=  3;
                    tcDe4 : NumeroDecimais :=  4;
                    tcDe6 : NumeroDecimais :=  6; // Incluido por Italo em 30/09/2010
                    tcDe10: NumeroDecimais := 10;
                  end;
                  ConteudoProcessado  := FormatFloat('0.0000000000', valor);
                  EstaVazio           := (valor = 0) and (ocorrencias = 0);
                  if StrToIntDef(Copy(ConteudoProcessado, pos(DecimalSeparator, ConteudoProcessado) + NumeroDecimais + 1, 10),0) > 0 then
                    walerta(ID, Tag, Descricao, ERR_MSG_MAXIMO_DECIMAIS + ' ' + IntToStr(NumeroDecimais));

                  ConteudoProcessado := FormatFloat('0.' + StringOfChar('0', NumeroDecimais), valor);
                  ConteudoProcessado := StringReplace(ConteudoProcessado, ',', '.', [rfReplaceAll]);
                  // Caso n�o seja um valor fracion�rio; retira os decimais.
                  if FOpcoes.FSuprimirDecimais then
                    if int(Valor) = Valor then
                     ConteudoProcessado := IntToStr(Round(Integer(valor)));

              end;
      tcEsp : begin
                  // Tipo String - somente numeros
                  ConteudoProcessado  := trim(valor);
                  EstaVazio           := valor = '';
                  if not ValidarNumeros(ConteudoProcessado) then walerta(ID, Tag, Descricao, ERR_MSG_INVALIDO);
              end;
      tcInt : begin
                  // Tipo Inteiro
                  ConteudoProcessado := IntToStr(valor);
                  EstaVazio := (valor = 0) and (ocorrencias = 0);
                  if min = Limite then
                  begin
                    ConteudoProcessado := StringOfChar('0', 60) + ConteudoProcessado;
                    ConteudoProcessado := copy(ConteudoProcessado, length(ConteudoProcessado) - Limite + 1, Limite);
                  end;
              end;
    end;
    alerta := '';
    //(Existem tags obrigat�rias que podem ser nulas ex. cEAN)  if (ocorrencias = 1) and (EstaVazio) then
    if (ocorrencias = 1) and (EstaVazio) and (min > 0)                                            then alerta := ERR_MSG_VAZIO;
    if (length(ConteudoProcessado) < min) and (alerta = '') and (length(ConteudoProcessado) > 1)  then alerta := ERR_MSG_MENOR;
    if length(ConteudoProcessado) > Limite                                                        then alerta := ERR_MSG_MAIOR;
      // Grava alerta //
    if (alerta <> '') and (pos(ERR_MSG_VAZIO, alerta) = 0) and (not EstaVazio)                    then alerta := alerta + ' [' + VarToStr(valor) + ']';
    walerta(ID, TAG, Descricao, alerta);
    // Sai se for apenas para validar //
    if FOpcoes.FSomenteValidar  then exit;
    // Grava no Formato Texto
    if not EstaVazio            then
      gtCampo(tag, ConteudoProcessado)
    else
      gtCampo(tag, '');

    // Grava a tag no arquivo - Quando n�o existir algum conte�do
    if ((ocorrencias = 1) and (EstaVazio)) then
    begin
      if FOpcoes.FIdentarXML then
      begin
        if FOpcoes.FTagVaziaNoFormatoResumido then
          FArquivoFormatoXML := FArquivoFormatoXML + StringOfChar(' ', FOpcoes.FTamanhoIdentacao * FOpcoes.FNivelIdentacao) + '<' + tag + '/>' + #13#10
        else
          FArquivoFormatoXML := FArquivoFormatoXML + StringOfChar(' ', FOpcoes.FTamanhoIdentacao * FOpcoes.FNivelIdentacao) + '<' + tag + '></' + tag + '>' + #13#10
      end
      else
      begin
        if FOpcoes.FTagVaziaNoFormatoResumido then
          FArquivoFormatoXML := FArquivoFormatoXML + '<' + tag + '/>'
        else
          FArquivoFormatoXML := FArquivoFormatoXML + '<' + tag + '></' + tag + '>';
      end;
      exit;
    end;
    // Grava a tag no arquivo - Quando existir algum conte�do
    if ((ocorrencias = 1) or (not EstaVazio)) then
      if FOpcoes.FIdentarXML then
        FArquivoFormatoXML := FArquivoFormatoXML + StringOfChar(' ', FOpcoes.FTamanhoIdentacao * FOpcoes.FNivelIdentacao) + '<' + tag + '>' + FiltrarTextoXML(FOpcoes.FRetirarEspacos, ConteudoProcessado, FOpcoes.FRetirarAcentos) + '</' + tag + '>' + #13#10
    else
      FArquivoFormatoXML := FArquivoFormatoXML + '<' + tag + '>' + FiltrarTextoXML(FOpcoes.FRetirarEspacos, ConteudoProcessado, FOpcoes.FRetirarAcentos) + '</' + tag + '>';
end;

procedure TGerador.wTexto(const Texto: string);
begin
  FArquivoFormatoXML := FArquivoFormatoXML + Texto;
end;

// Gerador TXT

procedure TGerador.gtNivel(ID: string);
var
  i: integer;
begin
  ID := UpperCase(ID);
  FIDNivel := ID;
  if (FLayoutArquivoTXT.Count = 0) or (ID = '') then
    exit;
  for i := 0 to FLayoutArquivoTXT.Count - 1 do
    if pos('<' + ID + '>', UpperCase(FLayoutArquivoTXT.Strings[i])) > 0 then
      FArquivoFormatoTXT := FArquivoFormatoTXT + FLayoutArquivoTXT.Strings[i] + #13;
end;

procedure TGerador.gtCampo(const Tag, ConteudoProcessado: string);
var
  i: integer;
  List: TstringList;
begin
  if FLayoutArquivoTXT.Count = 0 then
    exit;
  List := TStringList.Create;
  List.Text := FArquivoFormatoTXT;
  //
  for i := 0 to List.count - 1 do
    if pos('<' + FIDNivel + '>', List.Strings[i]) > 0 then
      if pos('|' + UpperCase(Tag) + '�', UpperCase(List.Strings[i])) > 0 then
        List[i] := StringReplace(List[i], '|' + UpperCase(Trim(TAG)) + '�', '|' + conteudoProcessado, []);
  //
  FArquivoFormatoTXT := List.Text;
  List.Free;
end;

procedure TGerador.gtAjustarRegistros(const ID: string);
var
  i, j, k: integer;
  s, idLocal: string;
  ListArquivo: TstringList;
  ListCorrigido: TstringList;
  ListTAGs: TstringList;
begin
  if FLayoutArquivoTXT.Count = 0 then
    exit;
  ListTAGs := TStringList.Create;
  ListArquivo := TStringList.Create;
  ListCorrigido := TStringList.Create;
  // Elimina registros n�o utilizados
  ListArquivo.Text := FArquivoFormatoTXT;
  for i := 0 to ListArquivo.count - 1 do
  begin
    k := 0;
    for j := 0 to FLayoutArquivoTXT.count - 1 do
      if listArquivo[i] = FLayoutArquivoTXT[j] then
        if pos('�', listArquivo[i]) > 0 then
          k := 1;
    if k = 0 then
      ListCorrigido.add(ListArquivo[i]);
  end;
  // Insere dados da chave da Nfe
  for i := 0 to ListCorrigido.count - 1 do
    if pos('^ID^', ListCorrigido[i]) > 1 then
      ListCorrigido[i] := StringReplace(ListCorrigido[i], '^ID^', ID, []);
  // Elimina Nome de TAG sem informa��o
  for j := 0 to FLayoutArquivoTXT.count - 1 do
  begin
    s := FLayoutArquivoTXT[j];
    while (pos('|', s) > 0) and (pos('�', s) > 0) do
    begin
      s := copy(s, pos('|', s), maxInt);
      ListTAGs.add(copy(s, 1, pos('�', s)));
      s := copy(s, pos('�', s) + 1, maxInt);
    end;
  end;
  for i := 0 to ListCorrigido.count - 1 do
    for j := 0 to ListTAGs.count - 1 do
      ListCorrigido[i] := StringReplace(ListCorrigido[i], ListTAGs[j], '|', []);
  // Elimina Bloco <ID>
  for i := 0 to ListCorrigido.count - 1 do
    if pos('>', ListCorrigido[i]) > 0 then
     begin
      ListCorrigido[i] := Trim(copy(ListCorrigido[i], pos('>', ListCorrigido[i]) + 1, maxInt));
      idLocal := copy(ListCorrigido[i],1,pos('|',ListCorrigido[i])-1);

      if (length(idLocal) > 2) and (UpperCase(idLocal) <> 'NOTA FISCAL') and
         (copy(idLocal,length(idLocal),1) <> SomenteNumeros(copy(idLocal,length(idLocal),1))) then
       begin
         idLocal := copy(idLocal,1,length(idLocal)-1)+LowerCase(copy(idLocal,length(idLocal),1));
         ListCorrigido[i] := StringReplace(ListCorrigido[i],idLocal,idLocal,[rfIgnoreCase]);
       end;
     end;
  FArquivoFormatoTXT := ListCorrigido.Text;
  //
  ListTAGs.Free;
  ListArquivo.Free;
  ListCorrigido.Free;
end;

procedure TGerador.wCampoNFSe(const Tipo: TpcnTipoCampo; ID, TAG: string;
  const min, max, ocorrencias: smallint; const valor: variant;
  const Descricao: string);
begin
  Self.wCampo(Tipo, ID, Self.Prefixo + TAG, min, max, ocorrencias, valor, Descricao);
end;

procedure TGerador.wGrupoNFSe(const TAG: string; ID: string;
  const Identar: Boolean);
begin
  if copy(TAG,1,1) = '/' then
     Self.wGrupo('/'+Self.Prefixo + copy(TAG,2,length(TAG)), ID, Identar)
  else
     Self.wGrupo(Self.Prefixo + TAG, ID, Identar);
end;

end.

