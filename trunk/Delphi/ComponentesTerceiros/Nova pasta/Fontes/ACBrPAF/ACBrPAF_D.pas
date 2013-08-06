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

unit ACBrPAF_D;

interface

uses
  SysUtils, Classes, Contnrs, DateUtils, ACBrPAFRegistros;

type
  TRegistroD3List = class;

  /// REGISTRO TIPO D1 - IDENTIFICA��O DO ESTABELECIMENTO USU�RIO DO PAF-ECF

  TRegistroD1 = class(TRegistroX1)
  end;

  /// REGISTRO TIPO D2 - RELA��O DOS DAV EMITIDOS:

  TRegistroD2 = class
  private
    fNUM_FAB: string;      /// N� de fabrica��o do ECF
    fMF_ADICIONAL: string; /// Letra indicativa de MF adicional
    fTIPO_ECF: string;     /// Tipo do ECF
    fMARCA_ECF: string;    /// Marca do ECF
    fMODELO_ECF: string;   /// Modelo do ECF
    fCOO: string;          /// Contador de Ordem de Opera��o do documento onde o DAV foi impresso pelo ECF
    fNUM_DAV: string;      /// N�mero do DAV emitido
    fDT_DAV: TDateTime;    /// Data de emiss�o do DAV
    fTIT_DAV: string;      /// T�tulo atribu�do ao DAV de acordo com sua fun��o. Ex: Or�amento, Pedido, etc.
    fVLT_DAV: currency;    /// Valor total do DAV emitido, com duas casas decimais
    fCOO_DFV: string;      /// Contador de Ordem de Opera��o do Documento Fiscal Vinculado
    /// Vers�o 01.07
    fNUMERO_ECF: string;   /// N�mero sequencial do ECF emissor do documento fiscal vinculado
    fNOME_CLIENTE: string; /// Nome do Cliente
    fCPF_CNPJ: string;     /// CPF ou CNPJ do adquirente

    fRegistroValido: boolean;

    fRegistroD3: TRegistroD3List; /// Registro FILHO
  public
    constructor Create; virtual; /// Create
    destructor Destroy; override; /// Destroy

    property RegistroValido: Boolean read fRegistroValido write fRegistroValido default True;
    property NUM_FAB: string read FNUM_FAB write FNUM_FAB;
    property MF_ADICIONAL: string read FMF_ADICIONAL write FMF_ADICIONAL;
    property TIPO_ECF: string read FTIPO_ECF write FTIPO_ECF;
    property MARCA_ECF: string read FMARCA_ECF write FMARCA_ECF;
    property MODELO_ECF: string read FMODELO_ECF write FMODELO_ECF;
    property COO: string read FCOO write FCOO;
    property NUM_DAV: string read FNUM_DAV write FNUM_DAV;
    property DT_DAV: TDateTime read FDT_DAV write FDT_DAV;
    property TIT_DAV: string read FTIT_DAV write FTIT_DAV;
    property VLT_DAV: currency read FVLT_DAV write FVLT_DAV;
    property COO_DFV: string read FCOO_DFV write FCOO_DFV;
    property NUMERO_ECF: string read FNUMERO_ECF write FNUMERO_ECF;
    property NOME_CLIENTE: string read FNOME_CLIENTE write FNOME_CLIENTE;
    property CPF_CNPJ: string read FCPF_CNPJ write FCPF_CNPJ;

    property RegistroD3: TRegistroD3List read FRegistroD3 write FRegistroD3;
  end;

  /// REGISTRO D2 - Lista

  TRegistroD2List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroD2;
    procedure SetItem(Index: Integer; const Value: TRegistroD2);
  public
    function New: TRegistroD2;
    property Items[Index: Integer]: TRegistroD2 read GetItem write SetItem;
  end;

  TRegistroD3 = class
  private
    fCOD_ITEM: string;
    fRegistroValido: Boolean;
    fVL_TOTAL: Currency;
    fDESC_ITEM: string;
    fIND_CANC: string;
    fVL_ACRES: Currency;
    fUNI_ITEM: string;
    fNUM_ITEM: Integer;
    fVL_DESCTO: Currency;
    fVL_UNIT: Currency;
    fDT_INCLUSAO: TDateTime;
    fDEC_VL_UNIT: Integer;
    fDEC_QTDE_ITEM: Integer;
    fQTDE_ITEM: Currency;
    //Vers�o 01.09
    fALIQ: Currency;
    fSIT_TRIB: string;
  public
    constructor Create; virtual;

    property RegistroValido: Boolean read fRegistroValido write fRegistroValido default True;
    property DT_INCLUSAO: TDateTime read fDT_INCLUSAO write fDT_INCLUSAO;
    property NUM_ITEM: Integer read fNUM_ITEM write fNUM_ITEM;
    property COD_ITEM: string read fCOD_ITEM write fCOD_ITEM;
    property DESC_ITEM: string read fDESC_ITEM write fDESC_ITEM;
    property QTDE_ITEM: Currency read fQTDE_ITEM write fQTDE_ITEM;
    property UNI_ITEM: string read fUNI_ITEM write fUNI_ITEM;
    property VL_UNIT: Currency read fVL_UNIT write fVL_UNIT;
    property VL_DESCTO: Currency read fVL_DESCTO write fVL_DESCTO;
    property VL_ACRES: Currency read fVL_ACRES write fVL_ACRES;
    property VL_TOTAL: Currency read fVL_TOTAL write fVL_TOTAL;
    property SIT_TRIB : string read fSIT_TRIB write fSIT_TRIB;
    property ALIQ : Currency read fALIQ write fALIQ;
    property IND_CANC: string read fIND_CANC write fIND_CANC;
    property DEC_QTDE_ITEM: Integer read fDEC_QTDE_ITEM write fDEC_QTDE_ITEM;
    property DEC_VL_UNIT: Integer read fDEC_VL_UNIT write fDEC_VL_UNIT;
  end;

  /// REGISTRO D3 - Lista

  TRegistroD3List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroD3;
    procedure SetItem(Index: Integer; const Value: TRegistroD3);
  public
    function New: TRegistroD3;
    property Items[Index: Integer]: TRegistroD3 read GetItem write SetItem;
  end;

  /// REGISTRO TIPO D9 - TOTALIZA��O DO ARQUIVO

  TRegistroD9 = class(TRegistroX9)
  private
    FTOT_REG_D2: integer;  /// Total de registros
    FTOT_REG_D3: integer;  /// Total de registros
  public
    property TOT_REG_D2: integer read FTOT_REG_D2 write FTOT_REG_D2;
    property TOT_REG_D3: integer read FTOT_REG_D3 write FTOT_REG_D3;
  end;

implementation

(* TRegistroD2List *)

function TRegistroD2List.GetItem(Index: Integer): TRegistroD2;
begin
  Result := TRegistroD2(inherited Items[Index]);
end;

function TRegistroD2List.New: TRegistroD2;
begin
  Result := TRegistroD2.Create;
  Add(Result);
end;

procedure TRegistroD2List.SetItem(Index: Integer; const Value: TRegistroD2);
begin
  Put(Index, Value);
end;

{ TRegistroD2 }

constructor TRegistroD2.Create;
begin
  fRegistroD3 := TRegistroD3List.Create;
  fRegistroValido := True;
end;

destructor TRegistroD2.Destroy;
begin
  fRegistroD3.Free;
  inherited;
end;

{ TRegistroD3 }

constructor TRegistroD3.Create;
begin
   fRegistroValido := True;
end;

{ TRegistroD3List }

function TRegistroD3List.GetItem(Index: Integer): TRegistroD3;
begin
  Result := TRegistroD3(inherited Items[Index]);
end;

function TRegistroD3List.New: TRegistroD3;
begin
  Result := TRegistroD3.Create;
  Add(Result);
end;

procedure TRegistroD3List.SetItem(Index: Integer; const Value: TRegistroD3);
begin
  Put(Index, Value);
end;

end.
