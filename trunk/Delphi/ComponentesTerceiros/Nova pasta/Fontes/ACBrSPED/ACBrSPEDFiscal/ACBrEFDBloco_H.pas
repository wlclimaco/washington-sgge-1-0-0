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

unit ACBrEFDBloco_H;

interface

uses
  SysUtils, Classes, Contnrs, DateUtils, ACBrEFDBlocos;

type
  TRegistroH005List = class;
  TRegistroH010List = class;
  TRegistroH020List = class;

  /// Registro H001 - ABERTURA DO BLOCO H

  TRegistroH001 = class(TOpenBlocos)
  private
    FRegistroH005: TRegistroH005List;
  public
    constructor Create; virtual; /// Create
    destructor Destroy; override; /// Destroy

    property RegistroH005: TRegistroH005List read FRegistroH005 write FRegistroH005;
  end;

  /// Registro H005 - TOTAIS DO INVENT�RIO

  TRegistroH005 = class
  private
    fDT_INV: TDateTime;    /// Data do invent�rio:
    fVL_INV: currency;     /// Valor total do estoque:
    fMOT_INV: TACBrMotivoInventario;      ///01 � No final no per�odo;
                                         ///02 � Na mudan�a de forma de tributa��o da mercadoria (ICMS);
                                         ///03 � Na solicita��o da baixa cadastral, paralisa��o tempor�ria e outras situa��es;
                                         ///04 � Na altera��o de regime de pagamento � condi��o do contribuinte;
                                         ///05 � Por determina��o dos fiscos.

    FRegistroH010: TRegistroH010List;  /// BLOCO H - Lista de RegistroH010 (FILHO)
  public
    constructor Create(AOwner: TRegistroH001); virtual; /// Create
    destructor Destroy; override; /// Destroy

    property DT_INV: TDateTime read FDT_INV write FDT_INV;
    property VL_INV: currency read FVL_INV write FVL_INV;
    property MOT_INV: TACBrMotivoInventario read fMOT_INV write fMOT_INV;

    /// Registros FILHOS
    property RegistroH010: TRegistroH010List read FRegistroH010 write FRegistroH010;
  end;

  /// Registro H005 - Lista

  TRegistroH005List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroH005; /// GetItem
    procedure SetItem(Index: Integer; const Value: TRegistroH005); /// SetItem
  public
    function New(AOwner: TRegistroH001): TRegistroH005;
    property Items[Index: Integer]: TRegistroH005 read GetItem write SetItem;
  end;

  /// Registro H010 - INVENT�RIO

  TRegistroH010 = class
  private
    fCOD_ITEM: String;         /// C�digo do item (campo 02 do Registro 0200)
    fUNID: String;             /// Unidade do item
    fQTD: Double;              /// Quantidade do item
    fVL_UNIT: Double;          /// Valor unit�rio do item
    fVL_ITEM: currency;        /// Valor do item
    fIND_PROP: TACBrPosseItem; /// Indicador de propriedade/posse do item: 0- Item de propriedade do informante e em seu poder, 1- Item de propriedade do informante em posse de terceiros, 2- Item de propriedade de terceiros em posse do informante
    fCOD_PART: String;         /// C�digo do participante (campo 02 do Registro 0150): propriet�rio/possuidor que n�o seja o informante do arquivo
    fTXT_COMPL: String;        /// Descri��o complementar
    fCOD_CTA: String;          /// C�digo da conta anal�tica cont�bil debitada/creditada

    FRegistroH020: TRegistroH020List;  /// BLOCO H - Lista de RegistroH020 (FILHO)
  public
    constructor Create(AOwner: TRegistroH005); virtual; /// Create
    destructor Destroy; override; /// Destroy

    property COD_ITEM: String read FCOD_ITEM write FCOD_ITEM;
    property UNID: String read FUNID write FUNID;
    property QTD: Double read FQTD write FQTD;
    property VL_UNIT: Double read FVL_UNIT write FVL_UNIT;
    property VL_ITEM: currency read FVL_ITEM write FVL_ITEM;
    property IND_PROP: TACBrPosseItem read FIND_PROP write FIND_PROP;
    property COD_PART: String read FCOD_PART write FCOD_PART;
    property TXT_COMPL: String read FTXT_COMPL write FTXT_COMPL;
    property COD_CTA: String read FCOD_CTA write FCOD_CTA;
    /// Registros FILHOS
    property RegistroH020: TRegistroH020List read FRegistroH020 write FRegistroH020;
  end;

  /// Registro H010 - Lista

  TRegistroH010List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroH010; /// GetItem
    procedure SetItem(Index: Integer; const Value: TRegistroH010); /// SetItem
  public
    function LocalizaRegistro(pCOD_ITEM: String): boolean;
    function New(AOwner: TRegistroH005): TRegistroH010;
    property Items[Index: Integer]: TRegistroH010 read GetItem write SetItem;
  end;

  /// Registro H020 - INFORMA��O COMPLEMENTAR DO INVENT�RIO

  TRegistroH020 = class
  private
    fCST_ICMS: String;          /// C�digo da Situa��o Tribut�ria, conforme a Tabela indicada no item 4.3.1
    fBC_ICMS: currency;         /// Informe a base de c�lculo do ICMS
    fVL_ICMS: currency;         /// Informe o valor do ICMS a ser debitado ou creditado
  public
    constructor Create(AOwner: TRegistroH010); virtual; /// Create

    property CST_ICMS: String read FCST_ICMS write FCST_ICMS;
    property BC_ICMS: currency read FBC_ICMS write FBC_ICMS;
    property VL_ICMS: currency read FVL_ICMS write FVL_ICMS;
  end;

  /// Registro H020 - Lista

  TRegistroH020List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroH020; /// GetItem
    procedure SetItem(Index: Integer; const Value: TRegistroH020); /// SetItem
  public
    function New(AOwner: TRegistroH010): TRegistroH020;
    property Items[Index: Integer]: TRegistroH020 read GetItem write SetItem;
  end;

  /// Registro H990 - ENCERRAMENTO DO BLOCO H

  TRegistroH990 = class
  private
    fQTD_LIN_H: Integer;    /// Quantidade total de linhas do Bloco H
  public
    property QTD_LIN_H: Integer read FQTD_LIN_H write FQTD_LIN_H;
  end;

implementation

{ TRegistroH010List }

function TRegistroH010List.GetItem(Index: Integer): TRegistroH010;
begin
  Result := TRegistroH010(Inherited Items[Index]);
end;

function TRegistroH010List.LocalizaRegistro(pCOD_ITEM: String): boolean;
var
intFor: integer;
begin
   Result := false;
   for intFor := 0 to Self.Count - 1 do
   begin
      if Self.Items[intFor].COD_ITEM = pCOD_ITEM then
      begin
         Result := true;
         Break;
      end;
   end;
end;

function TRegistroH010List.New(AOwner: TRegistroH005): TRegistroH010;
begin
  Result := TRegistroH010.Create(AOwner);
  Add(Result);
end;

procedure TRegistroH010List.SetItem(Index: Integer; const Value: TRegistroH010);
begin
  Put(Index, Value);
end;

{ TRegistroH005List }

function TRegistroH005List.GetItem(Index: Integer): TRegistroH005;
begin
  Result := TRegistroH005(Inherited Items[Index]);
end;

function TRegistroH005List.New(AOwner: TRegistroH001): TRegistroH005;
begin
  Result := TRegistroH005.Create(AOwner);
  Add(Result);
end;

procedure TRegistroH005List.SetItem(Index: Integer; const Value: TRegistroH005);
begin
  Put(Index, Value);
end;

{ TRegistroH005 }

constructor TRegistroH005.Create(AOwner: TRegistroH001);
begin
  FRegistroH010 := TRegistroH010List.Create;
end;

destructor TRegistroH005.Destroy;
begin
  FRegistroH010.Free;
  inherited;
end;

{ TRegistroH001 }

constructor TRegistroH001.Create;
begin
   FRegistroH005 := TRegistroH005List.Create;
   //
   IND_MOV := imSemDados;
end;

destructor TRegistroH001.Destroy;
begin
  FRegistroH005.Free;
  inherited;
end;

{ TRegistroH020List }

function TRegistroH020List.GetItem(Index: Integer): TRegistroH020;
begin
  Result := TRegistroH020(Inherited Items[Index]);
end;

function TRegistroH020List.New(AOwner: TRegistroH010): TRegistroH020;
begin
  Result := TRegistroH020.Create(AOwner);
  Add(Result);
end;

procedure TRegistroH020List.SetItem(Index: Integer;
  const Value: TRegistroH020);
begin
  Put(Index, Value);
end;

{ TRegistroH010 }

constructor TRegistroH010.Create(AOwner: TRegistroH005);
begin
  FRegistroH020 := TRegistroH020List.Create;
end;

destructor TRegistroH010.Destroy;
begin
  FRegistroH020.Free;
  inherited;
end;

{ TRegistroH020 }

constructor TRegistroH020.Create(AOwner: TRegistroH010);
begin
end;

end.
