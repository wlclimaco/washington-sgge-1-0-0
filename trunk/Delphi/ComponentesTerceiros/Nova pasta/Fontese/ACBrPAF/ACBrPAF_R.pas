{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009   Isaque Pinheiro                      }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
{                                                                              }
{  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la }
{ sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) }
{ qualquer vers�o posterior.                                                   }
{                                                                              }
{  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      }
{ ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto}
{ com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  }
{ no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Voc� tamb�m pode obter uma copia da licen�a em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 10/04/2009: Isaque Pinheiro
|*  - Cria��o e distribui��o da Primeira Versao
*******************************************************************************}

unit ACBrPAF_R;

interface

uses
  SysUtils, Classes, Contnrs, DateUtils, ACBrPAFRegistros;

type
  TRegistroR03List = class;
  TRegistroR05List = class;
  TRegistroR07List = class;
  TRegistroR07xList = class;

  /// REGISTRO TIPO R01 - IDENTIFICA��O DO ECF, DO USU�RIO, DO PAF-ECF E DA EMPRESA DESENVOLVEDORA E DADOS DO ARQUIVO

  TRegistroR01 = class
  private
    fRegistroValido: boolean;
    fNUM_FAB: string;        /// N�mero de fabrica��o do ECF
    fMF_ADICIONAL: string;   /// Letra indicativa de MF adicional
    fTIPO_ECF: string;       /// Tipo de ECF
    fMARCA_ECF: string;      /// Marca do ECF
    fMODELO_ECF: string;     /// Modelo do ECF
    fVERSAO_SB: string;      /// Vers�o atual do Software B�sico do ECF gravada na MF
    fDT_INST_SB: TDateTime;  /// Data de instala��o da vers�o atual do Software B�sico gravada naMem�ria Fiscal do ECF
    fHR_INST_SB: TDateTime;  /// Hor�rio de instala��o da vers�o atual do Software B�sico gravada na Mem�ria Fiscal do ECF
    fNUM_SEQ_ECF: integer;   /// N� de ordem seq�encial do ECF no estabelecimento usu�rio
    fCNPJ: string;           /// CNPJ do estabelecimento usu�rio do ECF
    fIE: string;             /// Inscri��o Estadual do estabelecimento usu�rio
    fCNPJ_SH: string;        /// CNPJ da empresa desenvolvedora do PAF-ECF
    fIE_SH: string;          /// Inscri��o Estadual da empresa desenvolvedora do PAF-ECF, se houver
    fIM_SH: string;          /// Inscri��o Municipal da empresa desenvolvedora do PAF-ECF, se houver
    fNOME_SH: string;        /// Denomina��o da empresa desenvolvedora do PAF-ECF
    fNOME_PAF: string;       /// Nome Comercial do PAF-ECF
    fVER_PAF: string;        /// Vers�o atual do PAF-ECF
    fCOD_MD5: string;        /// C�digo MD-5 da Lista de Arquivos Autenticados do PAF-ECF
    fDT_INI: TDateTime;      /// Data do in�cio do per�odo informado no arquivo
    fDT_FIN: TDateTime;      /// Data do fim do per�odo informado no arquivo
    fER_PAF_ECF: string;     /// Vers�o da Especifica��o de Requisitos do PAF-ECF
    fInclusaoExclusao: Boolean; /// ER 1.08 inclus�o/exclus�o de registros
  public
    constructor Create; virtual; /// Create

    property InclusaoExclusao: Boolean read fInclusaoExclusao write fInclusaoExclusao default False;
    property RegistroValido: Boolean read fRegistroValido write fRegistroValido default True;
    property NUM_FAB: string read FNUM_FAB write FNUM_FAB;
    property MF_ADICIONAL: string read fMF_ADICIONAL write fMF_ADICIONAL;
    property TIPO_ECF: string read FTIPO_ECF write FTIPO_ECF;
    property MARCA_ECF: string read FMARCA_ECF write FMARCA_ECF;
    property MODELO_ECF: string read FMODELO_ECF write FMODELO_ECF;
    property VERSAO_SB: string read FVERSAO_SB write FVERSAO_SB;
    property DT_INST_SB: TDateTime read FDT_INST_SB write FDT_INST_SB;
    property HR_INST_SB: TDateTime read FHR_INST_SB write FHR_INST_SB;
    property NUM_SEQ_ECF: integer read FNUM_SEQ_ECF write FNUM_SEQ_ECF;
    property CNPJ: string read FCNPJ write FCNPJ;
    property IE: string read FIE write FIE;
    property CNPJ_SH: string read FCNPJ_SH write FCNPJ_SH;
    property IE_SH: string read FIE_SH write FIE_SH;
    property IM_SH: string read FIM_SH write FIM_SH;
    property NOME_SH: string read FNOME_SH write FNOME_SH;
    property NOME_PAF: string read FNOME_PAF write FNOME_PAF;
    property VER_PAF: string read FVER_PAF write FVER_PAF;
    property COD_MD5: string read FCOD_MD5 write FCOD_MD5;
    property DT_INI: TDateTime read FDT_INI write FDT_INI;
    property DT_FIN: TDateTime read FDT_FIN write FDT_FIN;
    property ER_PAF_ECF: string read FER_PAF_ECF write FER_PAF_ECF;
  end;

  /// REGISTRO TIPO R02 - RELA��O DE REDU��ES Z

  TRegistroR02 = class
  private
    fRegistroValido: boolean;
    fNUM_USU: integer;       /// N� de ordem do usu�rio do ECF relativo � respectiva Redu��o Z
    fCRZ: integer;           /// N� do Contador de Redu��o Z relativo � respectiva redu��o
    fCOO: integer;           /// N� do Contador de Ordem de Opera��o relativo � respectiva Redu��o Z
    fCRO: integer;           /// N� do Contador de Rein�cio de Opera��o relativo � respectiva Redu��o Z
    fDT_MOV: TDateTime;      /// Data das opera��es relativas � respectiva Redu��o Z
    fDT_EMI: TDateTime;      /// Data de emiss�o da Redu��o Z
    fHR_EMI: TDateTime;      /// Hora de emiss�o da Redu��o Z
    fVL_VBD: currency;       /// Valor acumulado neste totalizador relativo � respectiva Redu��o Z, com duas casas decimais.
    fPAR_ECF: string;        /// Par�metro do ECF para incid�ncia de desconto sobre itens sujeitos ao ISSQN conforme item 7.2.1.4

    fRegistroR03: TRegistroR03List; /// Registro FILHO
  public
    constructor Create; virtual; /// Create
    destructor Destroy; override; /// Destroy

    property RegistroValido: Boolean read fRegistroValido write fRegistroValido default True;
    property NUM_USU: integer read FNUM_USU write FNUM_USU;
    property CRZ: integer read FCRZ write FCRZ;
    property COO: integer read FCOO write FCOO;
    property CRO: integer read FCRO write FCRO;
    property DT_MOV: TDateTime read FDT_MOV write FDT_MOV;
    property DT_EMI: TDateTime read FDT_EMI write FDT_EMI;
    property HR_EMI: TDateTime read FHR_EMI write FHR_EMI;
    property VL_VBD: currency read FVL_VBD write FVL_VBD;
    property PAR_ECF: string read FPAR_ECF write FPAR_ECF;

    property RegistroR03: TRegistroR03List read FRegistroR03 write FRegistroR03;
  end;

  /// REGISTRO R02 - Lista

  TRegistroR02List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroR02;
    procedure SetItem(Index: Integer; const Value: TRegistroR02);
  public
    function New: TRegistroR02;
    property Items[Index: Integer]: TRegistroR02 read GetItem write SetItem;
  end;

  /// REGISTRO TIPO R03 - DETALHE DA REDU��O Z

  TRegistroR03 = class
  private
    fRegistroValido: boolean;
    fTOT_PARCIAL: string;       /// C�digo do totalizador conforme tabela abaixo
    fVL_ACUM: currency;         /// Valor acumulado no totalizador, relativo � respectiva Redu��o Z, com duas casas decimais
  public
    constructor Create; virtual; /// Create

    property RegistroValido: Boolean read fRegistroValido write fRegistroValido default True;
    property TOT_PARCIAL: string read FTOT_PARCIAL write FTOT_PARCIAL;
    property VL_ACUM: currency read FVL_ACUM write FVL_ACUM;
  end;

  /// REGISTRO R03 - Lista

  TRegistroR03List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroR03;
    procedure SetItem(Index: Integer; const Value: TRegistroR03);
  public
    function New: TRegistroR03;
    property Items[Index: Integer]: TRegistroR03 read GetItem write SetItem;
  end;

  /// REGISTRO TIPO R04 - CUPOM FISCAL, NOTA FISCAL DE VENDA A CONSUMIDOR E BILHETE DE PASSAGEM

  TRegistroR04 = class
  private
    fRegistroValido: boolean;
    fNUM_USU: integer;          /// N� de ordem do usu�rio do ECF
    fNUM_CONT: integer;         /// N� do contador do respectivo documento emitido
    fCOO: integer;              /// N� do COO relativo ao respectivo documento
    fDT_INI: TDateTime;         /// Data de in�cio da emiss�o do documento impressa no cabe�alho do documento
    fSUB_DOCTO: currency;       /// Valor total do documento, com  duas casas decimais.
    fSUB_DESCTO: currency;      /// Valor do desconto ou Percentual aplicado sobre o valor do subtotal do documento, com duas casas decimais.
    fTP_DESCTO: string;         /// Informar �V� para valor monet�rio ou �P� para percentual
    fSUB_ACRES: currency;       /// Valor do acr�scimo ou Percentual aplicado sobre o valor do subtotal do documento, com duas casas decimais
    fTP_ACRES: string;          /// Informar �V�  para valor monet�rio ou �P� para percentual
    fVL_TOT: currency;          /// Valor total do Cupom Fiscal ap�s desconto/acr�scimo, com duas casas decimais.
    fCANC: string;              /// Informar "S" ou "N", conforme tenha ocorrido ou n�o, o cancelamento do documento.
    fVL_CA: currency;           /// Valor do cancelamento de acr�scimo no subtotal
    fORDEM_DA: string;          /// Indicador de ordem de aplica��o de desconto/acr�scimo em Subtotal. �D� ou �A� caso tenha ocorrido primeiro desconto ou acr�scimo, respectivamente
    fNOME_CLI: string;          /// Nome do Cliente
    fCNPJ_CPF: string;          /// CPF ou CNPJ do adquirente

    fRegistroR05: TRegistroR05List; /// Registro FILHO
    fRegistroR07: TRegistroR07List; /// Registro FILHO
  public
    constructor Create; virtual; /// Create
    destructor Destroy; override; /// Destroy

    property RegistroValido: Boolean read fRegistroValido write fRegistroValido default True;
    property NUM_USU: integer read FNUM_USU write FNUM_USU;
    property NUM_CONT: integer read FNUM_CONT write FNUM_CONT;
    property COO: integer read FCOO write FCOO;
    property DT_INI: TDateTime read FDT_INI write FDT_INI;
    property SUB_DOCTO: currency read FSUB_DOCTO write FSUB_DOCTO;
    property SUB_DESCTO: currency read FSUB_DESCTO write FSUB_DESCTO;
    property TP_DESCTO: string read FTP_DESCTO write FTP_DESCTO;
    property SUB_ACRES: currency read FSUB_ACRES write FSUB_ACRES;
    property TP_ACRES: string read FTP_ACRES write FTP_ACRES;
    property VL_TOT: currency read FVL_TOT write FVL_TOT;
    property CANC: string read FCANC write FCANC;
    property VL_CA: currency read FVL_CA write FVL_CA;
    property ORDEM_DA: string read FORDEM_DA write FORDEM_DA;
    property NOME_CLI: string read FNOME_CLI write FNOME_CLI;
    property CNPJ_CPF: string read FCNPJ_CPF write FCNPJ_CPF;

    property RegistroR05: TRegistroR05List read FRegistroR05 write FRegistroR05;
    property RegistroR07: TRegistroR07List read FRegistroR07 write FRegistroR07;
  end;

  /// REGISTRO R04 - Lista

  TRegistroR04List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroR04;
    procedure SetItem(Index: Integer; const Value: TRegistroR04);
  public
    function New: TRegistroR04;
    property Items[Index: Integer]: TRegistroR04 read GetItem write SetItem;
  end;

  /// REGISTRO TIPO R05 - DETALHE DO CUPOM FISCAL, DA NOTA FISCAL DE VENDA A CONSUMIDOR OU DO BILHETE DE PASSAGEM

  { TRegistroR05 }

  TRegistroR05 = class
  private
    FNUM_CONT: integer;
    fRegistroValido: boolean;
    fNUM_ITEM: integer;         /// N�mero do item registrado no documento
    fCOD_ITEM: string;          /// C�digo do produto ou servi�o registrado no documento
    fDESC_ITEM: string;         /// Descri��o do produto ou servi�o constante no Cupom Fiscal
    fQTDE_ITEM: currency;       /// Quantidade comercializada, sem a separa��o das casas decimais
    fUN_MED: string;            /// Unidade de medida
    fVL_UNIT: currency;         /// Valor unit�rio do produto ou servi�o, sem a separa��o das casas decimais.
    fDESCTO_ITEM: currency;     /// Valor  do desconto  incidente sobre o valor do item, com duas casas decimais.
    fACRES_ITEM: currency;      /// Valor do acr�scimo incidente sobre o valor do item, com duas casas decimais.
    fVL_TOT_ITEM: currency;     /// Valor total l�quido do item, com duas casas decimais.
    fCOD_TOT_PARC: string;      /// C�digo do totalizador relativo ao produto ou servi�o conforme tabela abaixo.
    fIND_CANC: string;          /// Informar "S" ou "N", conforme tenha ocorrido ou n�o, o cancelamento total do item  no documento. Informar "P" quando ocorrer o cancelamento parcial do item.
    fQTDE_CANC: currency;       /// Quantidade cancelada, no caso de cancelamento parcial de item, sem a separa��o das casas decimais.
    fVL_CANC: currency;         /// Valor cancelado, no caso de cancelamento parcial de item
    fVL_CANC_ACRES: currency;   /// Valor do cancelamento de acr�scimo no item
    fIAT: string;               /// Indicador de Arredondamento ou Truncamento relativo � regra de c�lculo do valor total l�quido do item, sendo �T�  para truncamento ou �A� para arredondamento
    fIPPT: string;              /// Indicador de Produ��o Pr�pria ou de Terceiro relativo � mercadoria, sendo �P� para mercadoria de produ��o pr�pria ou �T� para mercadoria produzida por terceiros
    fQTDE_DECIMAL: integer;     /// Par�metro de n�mero de casas decimais da quantidade
    fVL_DECIMAL: integer;       /// Par�metro de n�mero de casas decimais de valor unit�rio
  public
    constructor Create; virtual; /// Create

    property RegistroValido: Boolean read fRegistroValido write fRegistroValido default True;
    property NUM_ITEM: integer read FNUM_ITEM write FNUM_ITEM;
    property COD_ITEM: string read FCOD_ITEM write FCOD_ITEM;
    property DESC_ITEM: string read FDESC_ITEM write FDESC_ITEM;
    property QTDE_ITEM: currency read FQTDE_ITEM write FQTDE_ITEM;
    property UN_MED: string read FUN_MED write FUN_MED;
    property VL_UNIT: currency read FVL_UNIT write FVL_UNIT;
    property DESCTO_ITEM: currency read FDESCTO_ITEM write FDESCTO_ITEM;
    property ACRES_ITEM: currency read FACRES_ITEM write FACRES_ITEM;
    property VL_TOT_ITEM: currency read FVL_TOT_ITEM write FVL_TOT_ITEM;
    property COD_TOT_PARC: string read FCOD_TOT_PARC write FCOD_TOT_PARC;
    property IND_CANC: string read FIND_CANC write FIND_CANC;
    property QTDE_CANC: currency read FQTDE_CANC write FQTDE_CANC;
    property VL_CANC: currency read FVL_CANC write FVL_CANC;
    property VL_CANC_ACRES: currency read FVL_CANC_ACRES write FVL_CANC_ACRES;
    property IAT: string read FIAT write FIAT;
    property IPPT: string read FIPPT write FIPPT;
    property QTDE_DECIMAL: integer read FQTDE_DECIMAL write FQTDE_DECIMAL;
    property VL_DECIMAL: integer read FVL_DECIMAL write FVL_DECIMAL;
    property NUM_CONT: integer read FNUM_CONT write FNUM_CONT;
  end;

  /// REGISTRO R05 - Lista

  TRegistroR05List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroR05;
    procedure SetItem(Index: Integer; const Value: TRegistroR05);
  public
    function New: TRegistroR05;
    property Items[Index: Integer]: TRegistroR05 read GetItem write SetItem;
  end;

  /// REGISTRO TIPO R06 - DEMAIS DOCUMENTOS EMITIDOS PELO ECF

  TRegistroR06 = class
  private
    fRegistroValido: boolean;
    fNUM_USU: integer;          /// N� de ordem do usu�rio do ECF
    fCOO: integer;              /// N� do COO relativo ao respectivo documento
    fGNF: integer;              /// N�mero do GNF relativo ao respectivo documento, quando houver
    fGRG: integer;              /// N�mero do GRG relativo ao respectivo documento (vide item 7.6.1.2)
    fCDC: integer;              /// N�mero do CDC relativo ao respectivo documento (vide item 7.6.1.3)
    fDENOM: string;             /// S�mbolo  referente � denomina��o do documento fiscal, conforme tabela abaixo
    fDT_FIN: TDateTime;         /// Data final de emiss�o (impressa no rodap� do documento)
    fHR_FIN: TDateTime;         /// Hora final de emiss�o (impressa no rodap� do documento)

    fRegistroR07: TRegistroR07List;
    procedure setNUM_USU(const Value: integer);
    procedure setCOO(const Value: integer); /// Registro FILHO
  public
    constructor Create; virtual; /// Create
    destructor Destroy; override; /// Destroy

    property RegistroValido: Boolean read fRegistroValido write fRegistroValido default True;
    property NUM_USU: integer read FNUM_USU write setNUM_USU;
    property COO: integer read FCOO write setCOO;
    property GNF: integer read FGNF write FGNF;
    property GRG: integer read FGRG write FGRG;
    property CDC: integer read FCDC write FCDC;
    property DENOM: string read FDENOM write FDENOM;
    property DT_FIN: TDateTime read FDT_FIN write FDT_FIN;
    property HR_FIN: TDateTime read FHR_FIN write FHR_FIN;

    property RegistroR07: TRegistroR07List read FRegistroR07 write FRegistroR07;
  end;

  /// REGISTRO R06 - Lista

  TRegistroR06List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroR06;
    procedure SetItem(Index: Integer; const Value: TRegistroR06);
  public
    function New: TRegistroR06;
    property Items[Index: Integer]: TRegistroR06 read GetItem write SetItem;
  end;

  /// REGISTRO TIPO R07 - DETALHE DO CUPOM FISCAL E DO DOCUMENTO N�O FISCAL  - MEIO DE PAGAMENTO

  TRegistroR07 = class
  private
    fRegistroValido: boolean;
    fCCF: integer;              /// N�mero do Contador de Cupom Fiscal relativo ao respectivo Cupom Fiscal emitido
    fGNF: integer;              /// N�mero do GNF relativo ao respectivo documento, quando houver
    fMP: string;                /// Descri��o do totalizador parcial de meio de pagamento
    fVL_PAGTO: currency;        /// Valor do pagamento efetuado, com duas casas decimais
    fIND_EST: string;           /// Informar "S" ou "N", conforme tenha ocorrido ou n�o, o estorno do pagamento, ou �P� para estorno parcial do pagamento
    fVL_EST: currency;          /// Valor do estorno efetuado, com duas casas decimais
    fTipoRegistroPai: string;
    fRegistroPai: Pointer;
  public
    constructor Create(const cTipoRegistroPai:string; cRegistroPai:Pointer); virtual; /// Create

    property RegistroValido: Boolean read fRegistroValido write fRegistroValido default True;
    property CCF: integer read FCCF write FCCF;
    property GNF: integer read FGNF write FGNF;
    property MP: string read FMP write FMP;
    property VL_PAGTO: currency read FVL_PAGTO write FVL_PAGTO;
    property IND_EST: string read FIND_EST write FIND_EST;
    property VL_EST: currency read FVL_EST write FVL_EST;
    property TipoRegistroPai: string read fTipoRegistroPai write fTipoRegistroPai;
    property RegistroPai: Pointer read fRegistroPai write fRegistroPai;
  end;

  /// REGISTRO R07 - Lista

  TRegistroR07List = class(TObjectList)
  private
    fTipoRegistroPai: string;
    fRegistroPai: Pointer;
    function GetItem(Index: Integer): TRegistroR07;
    procedure SetItem(Index: Integer; const Value: TRegistroR07);
  public
    function New: TRegistroR07;
    property Items[Index: Integer]: TRegistroR07 read GetItem write SetItem;
    property TipoRegistroPai: string read fTipoRegistroPai write fTipoRegistroPai;
    property RegistroPai: Pointer read fRegistroPai write fRegistroPai;
  end;

  TRegistroR07xList = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroR07;
    procedure SetItem(Index: Integer; const Value: TRegistroR07);
  public
    function New: TRegistroR07;
    property Items[Index: Integer]: TRegistroR07 read GetItem write SetItem;
  end;

implementation

{ TRegistroR02List }

function TRegistroR02List.GetItem(Index: Integer): TRegistroR02;
begin
  Result := TRegistroR02(inherited Items[Index]);
end;

function TRegistroR02List.New: TRegistroR02;
begin
  Result := TRegistroR02.Create;
  Add(Result);
end;

procedure TRegistroR02List.SetItem(Index: Integer; const Value: TRegistroR02);
begin
  Put(Index, Value);
end;

{ TRegistroR03List }

function TRegistroR03List.GetItem(Index: Integer): TRegistroR03;
begin
  Result := TRegistroR03(inherited Items[Index]);
end;

function TRegistroR03List.New: TRegistroR03;
begin
  Result := TRegistroR03.Create;
  Add(Result);
end;

procedure TRegistroR03List.SetItem(Index: Integer; const Value: TRegistroR03);
begin
  Put(Index, Value);
end;

{ TRegistroR04List }

function TRegistroR04List.GetItem(Index: Integer): TRegistroR04;
begin
  Result := TRegistroR04(inherited Items[Index]);
end;

function TRegistroR04List.New: TRegistroR04;
begin
  Result := TRegistroR04.Create;
  Add(Result);
end;

procedure TRegistroR04List.SetItem(Index: Integer; const Value: TRegistroR04);
begin
  Put(Index, Value);
end;

{ TRegistroR05List }

function TRegistroR05List.GetItem(Index: Integer): TRegistroR05;
begin
  Result := TRegistroR05(inherited Items[Index]);
end;

function TRegistroR05List.New: TRegistroR05;
begin
  Result := TRegistroR05.Create;
  Add(Result);
end;

procedure TRegistroR05List.SetItem(Index: Integer; const Value: TRegistroR05);
begin
  Put(Index, Value);
end;

{ TRegistroR06List }

function TRegistroR06List.GetItem(Index: Integer): TRegistroR06;
begin
  Result := TRegistroR06(inherited Items[Index]);
end;

function TRegistroR06List.New: TRegistroR06;
begin
  Result := TRegistroR06.Create;
  Add(Result);
end;

procedure TRegistroR06List.SetItem(Index: Integer; const Value: TRegistroR06);
begin
  Put(Index, Value);
end;

{ TRegistroR07List }

function TRegistroR07List.GetItem(Index: Integer): TRegistroR07;
begin
  Result := TRegistroR07(inherited Items[Index]);
end;

function TRegistroR07List.New: TRegistroR07;
begin
  Result := TRegistroR07.Create(TipoRegistroPai, RegistroPai);
  Add(Result);
end;

procedure TRegistroR07List.SetItem(Index: Integer; const Value: TRegistroR07);
begin
  Put(Index, Value);
end;

{ TRegistroR01 }

constructor TRegistroR01.Create;
begin
   fRegistroValido := True;
   fInclusaoExclusao := False;
end;

{ TRegistroR02 }

constructor TRegistroR02.Create;
begin
  fRegistroValido := True;
  FRegistroR03 := TRegistroR03List.Create;
end;

destructor TRegistroR02.Destroy;
begin
  FRegistroR03.Free;
  inherited;
end;

{ TRegistroR03 }

constructor TRegistroR03.Create;
begin
  fRegistroValido := True;
end;

{ TRegistroR04 }

constructor TRegistroR04.Create;
begin
  fRegistroValido := True;
  FRegistroR05 := TRegistroR05List.Create;
  FRegistroR07 := TRegistroR07List.Create;
  FRegistroR07.fTipoRegistroPai := 'R04';
  FRegistroR07.fRegistroPai := Self;
end;

destructor TRegistroR04.Destroy;
begin
  FRegistroR05.Free;
  FRegistroR07.Free;
  inherited;
end;

{ TRegistroR05 }

constructor TRegistroR05.Create;
begin
  fRegistroValido := True;
  FNUM_CONT       := -1;
end;

{ TRegistroR06 }

constructor TRegistroR06.Create;
begin
  fRegistroValido := True;
  FRegistroR07 := TRegistroR07List.Create;
  FRegistroR07.fTipoRegistroPai := 'R06';
  FRegistroR07.fRegistroPai := Self;
end;

destructor TRegistroR06.Destroy;
begin
  FRegistroR07.Free;
  inherited;
end;

procedure TRegistroR06.setCOO(const Value: integer);
begin
  FCOO := Value;
end;

procedure TRegistroR06.setNUM_USU(const Value: integer);
begin
  FNUM_USU := Value;
end;

{ TRegistroR07 }

constructor TRegistroR07.Create(const cTipoRegistroPai:string; cRegistroPai:Pointer);
begin
  fRegistroValido := True;
  fTipoRegistroPai := cTipoRegistroPai;
  fRegistroPai := cRegistroPai;
end;

{ TRegistroR07xList }


function TRegistroR07xList.GetItem(Index: Integer): TRegistroR07;
begin
  Result := TRegistroR07(inherited Items[Index]);
end;

function TRegistroR07xList.New: TRegistroR07;
begin
  Result := TRegistroR07.Create('x',self);
  Add(Result);
end;

procedure TRegistroR07xList.SetItem(Index: Integer; const Value: TRegistroR07);
begin
  Put(Index, Value);
end;

end.
