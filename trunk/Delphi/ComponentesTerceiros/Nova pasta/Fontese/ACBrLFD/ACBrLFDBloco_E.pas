{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009  Juliana Tamizou                       }
{                                                                              }
{ Colaboradores nesse arquivo: Isaque Pinheiro                                 }
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
|* 26/01/2013: Nilson Sergio
|*  - Cria��o e distribui��o da Primeira Versao
*******************************************************************************}

unit ACBrLFDBloco_E;

interface

uses
  SysUtils, Classes, DateUtils, ACBrLFDBlocos;

type
  //TRegistroE005List = class;
  TRegistroE020List = class;
  TRegistroE025List = class;
  TRegistroE300 = class;
  TRegistroE310List = class;
  TRegistroE360 = class;
  TRegistroE365List = class;
  TRegistroE500 = class;
  TRegistroE530 = class;

  /// Registro E001 - Abertura do Bloco E

  { TRegistroE001 }

  TRegistroE001 = class(TOpenBlocos)
  private
    FRegistroE020: TRegistroE020List;
   // FRegistroE005: TRegistroE005List;
    FRegistroE300: TRegistroE300;
    FRegistroE500: TRegistroE500;
  public
    constructor Create; virtual; /// Create
    destructor Destroy; override; /// Destroy
   // property RegistroE005: TRegistroE005List read FRegistroE005 write FRegistroE005;
    property RegistroE020: TRegistroE020List read FRegistroE020 write FRegistroE020;
    property RegistroE300: TRegistroE300 read FRegistroE300 write FRegistroE300;
    property RegistroE500: TRegistroE500 read FRegistroE500 write FRegistroE500;
  end;


  /// Registro E005 - Beneficio Fiscal

  { TRegistroE005 }

  {TRegistroE005 = class
  private
    FCAMPO_INI: String;
    FQTD_CAMPO: Integer;
    FREG_NOM: String;
    FUF: String;
  public
    constructor Create(AOwner: TRegistroE001); virtual; /// Create
    property UF: String read FUF write FUF;
    property REG_NOM: String read FREG_NOM write FREG_NOM;
    property CAMPO_INI: String read FCAMPO_INI write FCAMPO_INI;
    property QTD_CAMPO: Integer read FQTD_CAMPO write FQTD_CAMPO;
  end; }

  /// Registro E005 - Lista

  { TRegistroE005List }

  {TRegistroE005List = class(TACBrLFDRegistros)
  private
    function GetItem(Index: Integer): TRegistroE005;
    procedure SetItem(Index: Integer; const Value: TRegistroE005);
  public
    function New(AOwner: TRegistroE001): TRegistroE005;
    property Items[Index: Integer]: TRegistroE005 read GetItem write SetItem;
  end;    }


  /// Registro E020 - LAN�AMENTO - NOTA FISCAL (C�DIGO 01) E NOTA FISCAL DE PRODUTOR (C�DIGO 04)

  { TRegistroE020 }

  TRegistroE020 = class
  private
    FCHV_NFE: String;
    FCOD_INF_OBS: String;
    FCOD_MOD: String;
    FCOD_NAT: String;
    FCOD_PART: String;
    FCOD_SIT: TACBrlSituacaoDocto;
    FCOP: String;
    FDT_EMISSAO: TDateTime;
    FDT_ES: TDateTime;
    FIND_COMPL: TACBrIndCompICMS;
    FIND_EMIT: TACBrlEmitente;
    FIND_OPER: TACBrLTipoOperacao;
    FIND_PGTO: TACBrlTipoPagamento;
    FNUMDOCTO: Integer;
    FNUM_LCTO: String;
    FRegistroE025: TRegistroE025List;
    FSERIE: String;
    FVALOR_AT: Double;
    FVALOR_BC_ICMS: Double;
    FVALOR_BC_IPI: Double;
    FVALOR_DOC: Double;
    FVALOR_ICMS: Double;
    FVALOR_IN_ICMS: Double;
    FVALOR_IN_IPI: Double;
    FVALOR_IPI: Double;
    FVALOR_OP_ISS: Double;
    FVALOR_O_ICMS: Double;
    FVALOR_O_IPI: Double;
    FVALOR_ST: Double;
    FVALOR_STE: Double;
    FVALOR_STS: Double;
    FVL_ICMS_COMPL: Double;
  public
    constructor Create; virtual; /// Create
    destructor Destroy; override; /// Destroy

    property IND_OPER: TACBrLTipoOperacao read FIND_OPER write FIND_OPER;
    property IND_EMIT: TACBrlEmitente read FIND_EMIT write FIND_EMIT;
    property COD_PART: String read FCOD_PART write FCOD_PART;
    property COD_MOD: String read FCOD_MOD write FCOD_MOD;
    property COD_SIT: TACBrlSituacaoDocto read FCOD_SIT write FCOD_SIT;
    property SERIE: String read FSERIE write FSERIE;
    property NUMDOCTO: Integer read FNUMDOCTO write FNUMDOCTO;
    property DT_EMISSAO: TDateTime read FDT_EMISSAO write FDT_EMISSAO;
    property NUM_LCTO: String read FNUM_LCTO write FNUM_LCTO;
    property DT_ES: TDateTime read FDT_ES write FDT_ES;
    property VALOR_DOC: Double read FVALOR_DOC write FVALOR_DOC;
    property VALOR_BC_ICMS: Double read FVALOR_BC_ICMS write FVALOR_BC_ICMS;
    property VALOR_ICMS: Double read FVALOR_ICMS write FVALOR_ICMS;
    property VALOR_ST: Double read FVALOR_ST write FVALOR_ST;
    property VALOR_STE: Double read FVALOR_STE write FVALOR_STE;
    property VALOR_STS: Double read FVALOR_STS write FVALOR_STS;
    property VALOR_AT: Double read FVALOR_AT write FVALOR_AT;
    property VL_ICMS_COMPL: Double read FVL_ICMS_COMPL write FVL_ICMS_COMPL;
    property IND_COMPL: TACBrIndCompICMS read FIND_COMPL write FIND_COMPL;
    property VALOR_IN_ICMS: Double read FVALOR_IN_ICMS write FVALOR_IN_ICMS;
    property VALOR_O_ICMS: Double read FVALOR_O_ICMS write FVALOR_O_ICMS;
    property VALOR_BC_IPI: Double read FVALOR_BC_IPI write FVALOR_BC_IPI;
    property VALOR_IPI: Double read FVALOR_IPI write FVALOR_IPI;
    property VALOR_IN_IPI: Double read FVALOR_IN_IPI write FVALOR_IN_IPI;
    property VALOR_O_IPI: Double read FVALOR_O_IPI write FVALOR_O_IPI;
    property VALOR_OP_ISS: Double read FVALOR_OP_ISS write FVALOR_OP_ISS;
    property COD_INF_OBS: String read FCOD_INF_OBS write FCOD_INF_OBS;
    property CHV_NFE: String read FCHV_NFE write FCHV_NFE;
    property COD_NAT: String read FCOD_NAT write FCOD_NAT;
    property COP: String read FCOP write FCOP;
    property IND_PGTO: TACBrlTipoPagamento read FIND_PGTO write FIND_PGTO;

    property RegistroE025: TRegistroE025List read FRegistroE025 write FRegistroE025;
  end;

  /// Registro E020 - Lista

  { TRegistroE020List }

  TRegistroE020List = class(TACBrLFDRegistros)
  private
     function GetItem(Index: Integer): TRegistroE020;
     procedure SetItem(Index: Integer; const Value: TRegistroE020);
  public
     function New(AOwner: TRegistroE001): TRegistroE020;
     property Items[Index: Integer]: TRegistroE020 read GetItem write SetItem;
  end;

  { TRegistroE025 }

  TRegistroE025 = class
  private
    FALIQ_ICMS: Double;
    FCFOP: String;
    FVALOR_BC_ICMS: Double;
    FVALOR_BC_IPI: Double;
    FVALOR_CONT: Double;
    FVALOR_ICMS: Double;
    FVALOR_IN_ICMS: Double;
    FVALOR_IN_IPI: Double;
    FVALOR_IPI: Double;
    FVALOR_O_ICMS: Double;
    FVALOR_O_IPI: Double;
    FVALOR_ST: Double;
    FVL_ICMS_COMPL: Double;
  public
    property CFOP: String read FCFOP write FCFOP;
    property VALOR_CONT: Double read FVALOR_CONT write FVALOR_CONT;
    property VALOR_BC_ICMS: Double read FVALOR_BC_ICMS write FVALOR_BC_ICMS;
    property ALIQ_ICMS: Double read FALIQ_ICMS write FALIQ_ICMS;
    property VALOR_ICMS: Double read FVALOR_ICMS write FVALOR_ICMS;
    property VALOR_ST: Double read FVALOR_ST write FVALOR_ST;
    property VL_ICMS_COMPL: Double read FVL_ICMS_COMPL write FVL_ICMS_COMPL;
    property VALOR_IN_ICMS: Double read FVALOR_IN_ICMS write FVALOR_IN_ICMS;
    property VALOR_O_ICMS: Double read FVALOR_O_ICMS write FVALOR_O_ICMS;
    property VALOR_BC_IPI: Double read FVALOR_BC_IPI write FVALOR_BC_IPI;
    property VALOR_IPI: Double read FVALOR_IPI write FVALOR_IPI;
    property VALOR_IN_IPI: Double read FVALOR_IN_IPI write FVALOR_IN_IPI;
    property VALOR_O_IPI: Double read FVALOR_O_IPI write FVALOR_O_IPI;
  end;

  /// Registro E025 - Lista

  { TRegistroE025List }

  TRegistroE025List = class(TACBrLFDRegistros)
  private
     function GetItem(Index: Integer): TRegistroE025;
     procedure SetItem(Index: Integer; const Value: TRegistroE025);
  public
     function New(AOwner: TRegistroE020): TRegistroE025;
     property Items[Index: Integer]: TRegistroE025 read GetItem write SetItem;
  end;

  { TRegistroE300 }

  TRegistroE300 = class
  private
    FDT_FIM: TDateTime;
    FDT_INI: TDatetime;
    FRegistroE310: TRegistroE310List;
    FRegistroE360: TRegistroE360;
  public
    constructor Create(AOwner: TRegistroE001); virtual; /// Create
    destructor Destroy; override; /// Destroy

    property DT_INI: TDateTime read FDT_INI write FDT_INI;
    property DT_FIM: TDateTime read FDT_FIM write FDT_FIM;

    property RegistroE310: TRegistroE310List read FRegistroE310 write FRegistroE310;
    property RegistroE360: TRegistroE360 read FRegistroE360 write FRegistroE360;
  end;

  { TRegistroE310 }

  TRegistroE310 = class
  private
    FCFOP: String;
    FVALOR_BC_ICMS: Double;
    FVALOR_CONT: Double;
    FVALOR_ICMS: Double;
    FVALOR_IN_ICMS: Double;
    FVALOR_O_ICMS: Double;
    FVALOR_ST: Double;
    FVL_ICMS_COMPL: Double;
  public
    property CFOP: String read FCFOP write FCFOP;
    property VALOR_CONT: Double read FVALOR_CONT write FVALOR_CONT;
    property VALOR_BC_ICMS: Double read FVALOR_BC_ICMS write FVALOR_BC_ICMS;
    property VALOR_ICMS: Double read FVALOR_ICMS write FVALOR_ICMS;
    property VALOR_ST: Double read FVALOR_ST write FVALOR_ST;
    property VL_ICMS_COMPL: Double read FVL_ICMS_COMPL write FVL_ICMS_COMPL;
    property VALOR_IN_ICMS: Double read FVALOR_IN_ICMS write FVALOR_IN_ICMS;
    property VALOR_O_ICMS: Double read FVALOR_O_ICMS write FVALOR_O_ICMS;
  end;

  /// Registro E310 - Lista

  { TRegistroE310List }

  TRegistroE310List = class(TACBrLFDRegistros)
  private
     function GetItem(Index: Integer): TRegistroE310;
     procedure SetItem(Index: Integer; const Value: TRegistroE310);
  public
     function New(AOwner: TRegistroE300): TRegistroE310;
     property Items[Index: Integer]: TRegistroE310 read GetItem write SetItem;
  end;

  { TRegistroE360 }

  TRegistroE360 = class
  private
    FRegistroE365: TRegistroE365List;
    FVL_CRED_ENT: Currency;
    FVL_DEB_SAIDA: Currency;
    FVL_DEDUCOES: Currency;
    FVL_DIF_ICMS: Currency;
    FVL_EST_CRED: Currency;
    FVL_EST_DEB: Currency;
    FVL_ICMS_COMPL: Currency;
    FVL_ICMS_IMP: Currency;
    FVL_ICMS_OO: Currency;
    FVL_ICMS_OREC: Currency;
    FVL_ICMS_ST_ENT: Currency;
    FVL_ICMS_ST_FORAUF: Currency;
    FVL_ICMS_ST_SAI: Currency;
    FVL_OCRED: Currency;
    FVL_ODEB: Currency;
    FVL_SALDO_CREDANT: Currency;
    FVL_SALDO_CRED_TRANSP: Currency;
    FVL_TOT_CRED: Currency;
  public
    constructor Create(AOwner: TRegistroE300); virtual; /// Create
    destructor Destroy; override; /// Destroy

    property VL_DEB_SAIDA: Currency read FVL_DEB_SAIDA write FVL_DEB_SAIDA;
    property VL_ODEB: Currency read FVL_ODEB write FVL_ODEB;
    property VL_EST_CRED: Currency read FVL_EST_CRED write FVL_EST_CRED;
    property VL_CRED_ENT: Currency read FVL_CRED_ENT write FVL_CRED_ENT;
    property VL_ICMS_COMPL: Currency read FVL_ICMS_COMPL write FVL_ICMS_COMPL;
    property VL_OCRED: Currency read FVL_OCRED write FVL_OCRED;
    property VL_EST_DEB: Currency read FVL_EST_DEB write FVL_EST_DEB;
    property VL_SALDO_CREDANT: Currency read FVL_SALDO_CREDANT write FVL_SALDO_CREDANT;
    property VL_TOT_CRED: Currency read FVL_TOT_CRED write FVL_TOT_CRED;
    property VL_SALDO_CRED_TRANSP: Currency read FVL_SALDO_CRED_TRANSP write FVL_SALDO_CRED_TRANSP;
    property VL_DEDUCOES: Currency read FVL_DEDUCOES write FVL_DEDUCOES;
    property VL_ICMS_ST_ENT: Currency read FVL_ICMS_ST_ENT write FVL_ICMS_ST_ENT;
    property VL_ICMS_ST_SAI: Currency read FVL_ICMS_ST_SAI write FVL_ICMS_ST_SAI;
    property VL_DIF_ICMS: Currency read FVL_DIF_ICMS write FVL_DIF_ICMS;
    property VL_ICMS_IMP: Currency read FVL_ICMS_IMP write FVL_ICMS_IMP;
    property VL_ICMS_OO: Currency read FVL_ICMS_OO write FVL_ICMS_OO;
    property VL_ICMS_OREC: Currency read FVL_ICMS_OREC write FVL_ICMS_OREC;
    property VL_ICMS_ST_FORAUF: Currency read FVL_ICMS_ST_FORAUF write FVL_ICMS_ST_FORAUF;

    property RegistroE365: TRegistroE365List read FRegistroE365 write FRegistroE365;
  end;

  { TRegistroE300 }

  { TRegistroE365 }

  TRegistroE365 = class
  private
    FUF: String;
    FVL_ICMS_ST_SAI: Currency;
  public
    constructor Create(AOwner: TRegistroE360); virtual; /// Create
    property VL_ICMS_ST_SAI: Currency read FVL_ICMS_ST_SAI write FVL_ICMS_ST_SAI;
    property UF: String read FUF write FUF;
  end;

  //Registro E365 - Lista

  { TRegistroE020List }

  { TRegistroE365List }

  TRegistroE365List = class(TACBrLFDRegistros)
  private
     function GetItem(Index: Integer): TRegistroE365;
     procedure SetItem(Index: Integer; const Value: TRegistroE365);
  public
     function New(AOwner: TRegistroE360): TRegistroE365;
     property Items[Index: Integer]: TRegistroE365 read GetItem write SetItem;
  end;

  { TRegistroE500 }

  TRegistroE500 = class
  private
    FDT_FIM: TDatetime;
    FDT_INI: TDateTime;
    FRegistroE530: TRegistroE530;
  public
    constructor Create(AOwner: TRegistroE001); virtual; /// Create
    destructor Destroy; override; /// Destroy
    property DT_INI: TDatetime read FDT_INI write FDT_INI;
    property DT_FIM: TDateTime read FDT_FIM write FDT_FIM;

    property RegistroE530: TRegistroE530 read FRegistroE530 write FRegistroE530;
  end;


  { TRegistroE530 }

  TRegistroE530 = class
  private
    FVL_CRED_IPI: Currency;
    FVL_DEB_IPI: Currency;
    FVL_OC_IPI: Currency;
    FVL_SC_IPI: Currency;
    FVL_SD_ANT_IPI: Currency;
    FVL_SD_IPI: Currency;
    FVL_OD_IPI: Currency;
  public
    constructor Create(AOwner: TRegistroE500); virtual; /// Create
    destructor Destroy; override; /// Destroy

    property VL_SD_ANT_IPI: Currency read FVL_SD_ANT_IPI write FVL_SD_ANT_IPI;
    property VL_DEB_IPI: Currency read FVL_DEB_IPI write FVL_DEB_IPI;
    property VL_CRED_IPI: Currency read FVL_CRED_IPI write FVL_CRED_IPI;
    property VL_OD_IPI: Currency read FVL_OD_IPI write FVL_OD_IPI;
    property VL_OC_IPI: Currency read FVL_OC_IPI write FVL_OC_IPI;
    property VL_SC_IPI: Currency read FVL_SC_IPI write FVL_SC_IPI;
    property VL_SD_IPI: Currency read FVL_SD_IPI write FVL_SD_IPI;
  end;


  /// Registro E990 - Encerramento do Bloco E

  { TRegistroE990 }

  TRegistroE990 = class
  private
    fQTD_LIN_E: Integer;
  public
    property QTD_LIN_E: Integer read fQTD_LIN_E write fQTD_LIN_E;
  end;

implementation

{ TRegistroE310List }

function TRegistroE310List.GetItem(Index: Integer): TRegistroE310;
begin
   Result := TRegistroE310(Get(Index));
end;

procedure TRegistroE310List.SetItem(Index: Integer; const Value: TRegistroE310);
begin
   Put(Index, Value);
end;

function TRegistroE310List.New(AOwner: TRegistroE300): TRegistroE310;
begin
   Result := TRegistroE310.Create;
   Add(Result);
end;

{ TRegistroE020 }

constructor TRegistroE020.Create;
begin
  FRegistroE025 := TRegistroE025List.Create;
end;

destructor TRegistroE020.Destroy;
begin
   FRegistroE025.Free;
   inherited Destroy;
end;

{ TRegistroE025List }

function TRegistroE025List.GetItem(Index: Integer): TRegistroE025;
begin
   Result := TRegistroE025(Get(Index));
end;

procedure TRegistroE025List.SetItem(Index: Integer; const Value: TRegistroE025);
begin
   Put(Index, Value);
end;

function TRegistroE025List.New(AOwner: TRegistroE020): TRegistroE025;
begin
   Result := TRegistroE025.Create;
   Add(Result);
end;

{ TRegistroE020List }

function TRegistroE020List.GetItem(Index: Integer): TRegistroE020;
begin
   Result := TRegistroE020(Get(Index));
end;

procedure TRegistroE020List.SetItem(Index: Integer; const Value: TRegistroE020);
begin
   Put(Index, Value);
end;

function TRegistroE020List.New(AOwner: TRegistroE001): TRegistroE020;
begin
   Result := TRegistroE020.Create;
   Add(Result);
end;

{ TRegistroE530 }

constructor TRegistroE530.Create(AOwner: TRegistroE500);
begin

end;

destructor TRegistroE530.Destroy;
begin
  inherited Destroy;
end;

{ TRegistroE500 }

constructor TRegistroE500.Create(AOwner: TRegistroE001);
begin
   FRegistroE530 := TRegistroE530.Create(Self);
end;

destructor TRegistroE500.Destroy;
begin
   FRegistroE530.Free;
   inherited Destroy;
end;

{ TRegistroE365List }

function TRegistroE365List.GetItem(Index: Integer): TRegistroE365;
begin
   Result := TRegistroE365(Get(Index));
end;

procedure TRegistroE365List.SetItem(Index: Integer; const Value: TRegistroE365);
begin
   Put(Index, Value);
end;

function TRegistroE365List.New(AOwner: TRegistroE360): TRegistroE365;
begin
   Result := TRegistroE365.Create(AOwner);
   Add(Result);
end;

{ TRegistroE365 }

constructor TRegistroE365.Create(AOwner: TRegistroE360);
begin
end;

{ TRegistroE360 }

constructor TRegistroE360.Create(AOwner: TRegistroE300);
begin
  FRegistroE365 := TRegistroE365List.create;
end;

destructor TRegistroE360.Destroy;
begin
   FRegistroE365.Free;
   inherited Destroy;
end;

{ TRegistroE300 }

constructor TRegistroE300.Create(AOwner: TRegistroE001);
begin
  FRegistroE360 := TRegistroE360.Create(Self);
  FRegistroE310 := TRegistroE310List.Create;
end;

destructor TRegistroE300.Destroy;
begin
   FRegistroE310.Free;
   FRegistroE360.Free;
   inherited Destroy;
end;

{ TRegistroE020List }

{function TRegistroE020List.GetItem(Index: Integer): TRegistroE020;
begin
   Result := TRegistroE020(Get(Index));
end;

procedure TRegistroE020List.SetItem(Index: Integer; const Value: TRegistroE020);
begin
   Put(Index, Value);
end;

function TRegistroE020List.New(AOwner: TRegistroE001): TRegistroE020;
begin
   Result := TRegistroE020.Create;
  Add(Result);
end;      }


{ TRegistroE005List }

{function TRegistroE005List.SetItem(Index: Integer): TRegistroE005;
begin
   Result := TRegistroE005(Get(Index));
end;

procedure TRegistroE005List.SetItem(Index: Integer; const Value: TRegistroE005);
begin
   Put(Index, Value);
end;

function TRegistroE005List.New(AOwner: TRegistroE001): TRegistroE005;
begin
  Result := TRegistroE005.Create(AOwner);
end;      }

{ TRegistroE005 }

{Constructor TRegistroE005.Create(AOwner: TRegistroE001);
begin

end;}

{ TRegistroE001 }

constructor TRegistroE001.Create;
begin
   //FRegistroE005 := TRegistroE005List.Create;
   FRegistroE300 := TRegistroE300.Create(Self);
   FRegistroE020 := TRegistroE020List.create;
   FRegistroE500 := TRegistroE500.Create(Self);
   IND_MOV := imlSemDados;
end;

destructor TRegistroE001.Destroy;
begin
   FRegistroE020.Free;
   FRegistroE300.Free;
   FRegistroE500.Free;
   inherited Destroy;
end;

end.
