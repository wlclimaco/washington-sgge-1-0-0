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

unit ACBrLFDBloco_9;

interface

uses
  SysUtils, Classes, DateUtils, ACBrLFDBlocos;

type

  TRegistro9030List = class;
  TRegistro9040List = class;

  /// Registro 9001 - Abertura do Bloco 9

  { TRegistro9001 }

  TRegistro9001 = class(TOpenBlocos)
  private
  public
  end;

  /// Registro 9020 - Informa��es de arquivos associados

  { TRegistro9020 }

  TRegistro9020 = class
  private
    FLEIAUTE: String; /// Texto fixo contendo �LFPD� ou �LECD�
    FARQ_DT_INI: TDateTime; /// Data inicial das informa��es do arquivo associado
    FARQ_DT_FIN: TDateTime; /// Data final das informa��es do arquivo associado
    FARQ_NOME_EMPR: String; /// Nome empresarial do contribuinte do arquivo associado
    FARQ_CNPJ: String; /// CNPJ do contribuinte do arquivo associado
    FARQ_UF: String; /// Sigla da unidade da Federa��o do arquivo associado
    FARQ_IE: String; /// Inscri��o Estadual do contribuinte do arquivo associado
    FARQ_COD_MUN: Integer; /// C�digo do munic�pio do arquivo associado
    FARQ_IM: String; /// Inscri��o Municipal do contribuinte do arquivo associado
    //VAZIO -> Texto fixo contendo ��
    FARQ_SUFRAMA: String; /// Inscri��o do contribuinte na Suframa do arquivo associado
    FARQ_COD_VER: String; /// C�digo da vers�o do leiaute do arquivo associado
    FARQ_COD_FIN: TACBrLCodFinalidade; /// C�digo da finalidade do arquivo associado
    FARQ_COD_CTD: String; /// C�digo do conte�do do arquivo associado
    //ARQ_PAIS -> Texto fixo contendo �Brasil�
    FARQ_CPF: String; /// CPF do contribuinte do arquivo associado
    FARQ_FANTASIA: String; /// Nome de fantasia associado ao nome empresarial do arquivo associado
    FARQ_QTD_LIN: String; /// Quantidade total de linhas do arquivo associado
    FARQ_NOME: String; /// Nome atribu�do pelo informante ao arquivo associado
    FASS_HASH: String; /// Hash do arquivo assinado

    FRegistro9030: TRegistro9030List;
    FRegistro9040: TRegistro9040List;
  public
    constructor Create(AOwner: TRegistro9001); virtual; /// Create
    destructor Destroy; override; /// Create

    property LEIAUTE: String read FLEIAUTE write FLEIAUTE;
    property ARQ_DT_INI: TDateTime read FARQ_DT_INI write FARQ_DT_INI;
    property ARQ_DT_FIN: TDateTime read FARQ_DT_FIN write FARQ_DT_FIN;
    property ARQ_NOME_EMPR: String read FARQ_NOME_EMPR write FARQ_NOME_EMPR;
    property ARQ_CNPJ: String read FARQ_CNPJ write FARQ_CNPJ;
    property ARQ_UF: String read FARQ_UF write FARQ_UF;
    property ARQ_IE: String read FARQ_IE write FARQ_IE;
    property ARQ_COD_MUN: Integer read FARQ_COD_MUN write FARQ_COD_MUN;
    property ARQ_IM: String read FARQ_IM write FARQ_IM;
    property ARQ_SUFRAMA: String read FARQ_SUFRAMA write FARQ_SUFRAMA;
    property ARQ_COD_VER: String read FARQ_COD_VER write FARQ_COD_VER;
    property ARQ_COD_FIN: TACBrLCodFinalidade read FARQ_COD_FIN write FARQ_COD_FIN;
    property ARQ_COD_CTD: String read FARQ_COD_CTD write FARQ_COD_CTD;
    property ARQ_CPF: String read FARQ_CPF write FARQ_CPF;
    property ARQ_FANTASIA: String read FARQ_FANTASIA write FARQ_FANTASIA;
    property ARQ_QTD_LIN: String read FARQ_QTD_LIN write FARQ_QTD_LIN;
    property ARQ_NOME: String read FARQ_NOME write FARQ_NOME;
    property ASS_HASH: String read FASS_HASH write FASS_HASH;

    property Registro9030: TRegistro9030List read FRegistro9030 write FRegistro9030;
    property Registro9040: TRegistro9040List read FRegistro9040 write FRegistro9040;
  end;

  /// Registro 9020 - Lista

  { TRegistro9020List }

  TRegistro9020List = class(TACBrLFDRegistros)
  private
    function GetItem(Index: Integer): TRegistro9020;
    procedure SetItem(Index: Integer; const Value: TRegistro9020);
  public
    function New(AOwner: TRegistro9001): TRegistro9020; overload;
    property Items[Index: Integer]: TRegistro9020 read GetItem write SetItem;
  end;

  /// Registro 9030 - Linhas do arquivo associado

  { TRegistro9030 }

  TRegistro9030 = class
  private
    FARQ_LIN_BLC: String; /// Sigla da linha que ser� totalizada no pr�ximo campo
    FARQ_QTD_LIN_BLC: String; /// Tipos de linhas (tipos de registro)
  public
    constructor Create(AOwner: TRegistro9020); virtual; /// Create

    property ARQ_LIN_BLC: String read FARQ_LIN_BLC write FARQ_LIN_BLC;
    property ARQ_QTD_LIN_BLC: String read FARQ_QTD_LIN_BLC write FARQ_QTD_LIN_BLC;
  end;

  /// Registro 9030 - Lista

  { TRegistro9030List }

  TRegistro9030List = class(TACBrLFDRegistros)
  private
    function GetItem(Index: Integer): TRegistro9030;
    procedure SetItem(Index: Integer; const Value: TRegistro9030);
  public
    function New(AOwner: TRegistro9020): TRegistro9030; overload;
    property Items[Index: Integer]: TRegistro9030 read GetItem write SetItem;
  end;

  /// Registro 9040 - Identifica��o dos signat�rios

  { TRegistro9040 }

  TRegistro9040 = class
  private
    FARQ_CERT_AC: String; /// Nome da Autoridade Certificadora emissora do certificado digital
    FARQ_CERT_SER: String; /// N�mero de s�rie do certificado
    FARQ_CERT_EXP_DT: TDateTime; /// Data de expira��o do certificado
    FARQ_CERT_EXP_HR: TdateTime; /// Hora de expira��o do certificado
    FARQ_ALG: String; /// OId do algoritmo de assinatura
    FARQ_DT: TDatetime; /// Data em que o arquivo foi assinado
    FARQ_HR: Tdatetime; /// Hora em que o arquivo foi assinado
    FARQ_SIGN: String; /// Nome do assinante
    FCOD_ASS: String; /// C�digo de qualifica��o do assinante
    FARQ_CERT: String; /// Certificado do assinante (codifica��o DER no formato Base64, retirados os caracteres de fim de linha)
    FARQ_ASS: String; /// Assinatura digital do arquivo (no formato Base64, retirados os caracteres de fim de linha)
  public
    constructor Create(AOwner: TRegistro9020); virtual; /// Create

    property ARQ_CERT_AC: String read FARQ_CERT_AC write FARQ_CERT_AC;
    property ARQ_CERT_SER: String read FARQ_CERT_SER write FARQ_CERT_SER;
    property ARQ_CERT_EXP_DT: TDatetime read FARQ_CERT_EXP_DT write FARQ_CERT_EXP_DT;
    property ARQ_CERT_EXP_HR: TdateTime read FARQ_CERT_EXP_HR write FARQ_CERT_EXP_HR;
    property ARQ_ALG: String read FARQ_ALG write FARQ_ALG;
    property ARQ_DT: TDatetime read FARQ_DT write FARQ_DT;
    property ARQ_HR: TdateTime read FARQ_HR write FARQ_HR;
    property ARQ_SIGN: String read FARQ_SIGN write FARQ_SIGN;
    property COD_ASS: String read FCOD_ASS write FCOD_ASS;
    property ARQ_CERT: String read FARQ_CERT write FARQ_CERT;
    property ARQ_ASS: String read FARQ_ASS write FARQ_ASS;
  end;

  /// Registro 9040 - Lista

  { TRegistro9040List }

  TRegistro9040List = class(TACBrLFDRegistros)
  private
    function GetItem(Index: Integer): TRegistro9040;
    procedure SetItem(Index: Integer; const Value: TRegistro9040);
  public
    function New(AOwner: TRegistro9020): TRegistro9040; overload;
    property Items[Index: Integer]: TRegistro9040 read GetItem write SetItem;
  end;

  /// Registro 9900 - Registros do Arquivo Digital

  { TRegistro9900 }

  TRegistro9900 = class
  private
    FREG_BLC: String; /// Registro que ser� totalizado no pr�ximo campo
    FQTD_REG_BLC: Integer; /// Total de registros do tipo informado no campo anterior
  public
    property REG_BLC: String read FREG_BLC write FREG_BLC;
    property QTD_REG_BLC: Integer read FQTD_REG_BLC write FQTD_REG_BLC;
  end;

  /// Registro 9900 - Lista

  { TRegistro9900List }

  TRegistro9900List = class(TACBrLFDRegistros)
  private
    function GetItem(Index: Integer): TRegistro9900;
    procedure SetItem(Index: Integer; const Value: TRegistro9900);
  public
    function New: TRegistro9900;// overload;
    //function New(ALIN_BLC: String; AQTD_LIN_BLC: Integer): TRegistro9900; overload;
    property Items[Index: Integer]: TRegistro9900 read GetItem write SetItem;
  end;

  /// Registro 9990 - Encerramento do Bloco 9

  { TRegistro9990 }

  TRegistro9990 = class
  private
    fQTD_LIN_9: Integer;
  public
    property QTD_LIN_9: Integer read fQTD_LIN_9 write fQTD_LIN_9;
  end;

  /// Registro 9999 - Encerramento, Controle e Assinaturas do Arquivo Digital

  { TRegistro9999 }

  TRegistro9999 = class
  private
    fQTD_LIN: Integer;
  public
    property QTD_LIN: Integer read fQTD_LIN write fQTD_LIN;
  end;

implementation

{ TRegistro9020 }

constructor TRegistro9020.Create(AOwner: TRegistro9001);
begin
  FRegistro9030 := TRegistro9030List.Create;
  FRegistro9040 := TRegistro9040List.Create;
end;

destructor TRegistro9020.Destroy;
begin
  FRegistro9030.Free;
  FRegistro9040.Free;
  inherited;
end;

{ TRegistro9020List }

function TRegistro9020List.GetItem(Index: Integer): TRegistro9020;
begin
  Result := TRegistro9020(Get(Index));
end;

function TRegistro9020List.New(AOwner: TRegistro9001): TRegistro9020;
begin
  Result := TRegistro9020.Create(AOwner);
  Add(Result);
end;

procedure TRegistro9020List.SetItem(Index: Integer; const Value: TRegistro9020);
begin
  Put(Index, Value);
end;

{ TRegistro9030 }

constructor TRegistro9030.Create(AOwner: TRegistro9020);
begin
end;

{ TRegistro9030List }

function TRegistro9030List.GetItem(Index: Integer): TRegistro9030;
begin
  Result := TRegistro9030(Get(Index));
end;

function TRegistro9030List.New(AOwner: TRegistro9020): TRegistro9030;
begin
  Result := TRegistro9030.Create(AOwner);
  Add(AOwner);
end;

procedure TRegistro9030List.SetItem(Index: Integer; const Value: TRegistro9030);
begin
  Put(Index, Value);
end;

{ TRegistro9040 }

constructor TRegistro9040.Create(AOwner: TRegistro9020);
begin
end;

{ TRegistro9040List }

function TRegistro9040List.GetItem(Index: Integer): TRegistro9040;
begin
  Result := TRegistro9040(Get(Index));
end;

function TRegistro9040List.New(AOwner: TRegistro9020): TRegistro9040;
begin
  Result := TRegistro9040.Create(AOwner);
  Add(Result);
end;

procedure TRegistro9040List.SetItem(Index: Integer; const Value: TRegistro9040);
begin
  Put(Index, Value);
end;

{ TRegistro9900List }

function TRegistro9900List.GetItem(Index: Integer): TRegistro9900;
begin
  Result := TRegistro9900(Get(Index));
end;

function TRegistro9900List.New: TRegistro9900;
begin
  Result := TRegistro9900.Create;
  Add(Result);
end;

{function TRegistro9900List.New(ALIN_BLC: String; AQTD_LIN_BLC: Integer): TRegistro9900;
begin
  if AQTD_LIN_BLC > 0 then
    with New do
    begin
      LIN_BLC := ALIN_BLC;
      QTD_LIN_BLC := AQTD_LIN_BLC;
    end;
end; }

procedure TRegistro9900List.SetItem(Index: Integer; const Value: TRegistro9900);
begin
  Put(Index, Value);
end;

end.
