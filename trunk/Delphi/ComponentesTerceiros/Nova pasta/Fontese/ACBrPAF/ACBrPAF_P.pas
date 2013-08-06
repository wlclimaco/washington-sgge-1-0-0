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

unit ACBrPAF_P;

interface

uses
  SysUtils, Classes, Contnrs, DateUtils, ACBrPAFRegistros;

type
  /// REGISTRO TIPO P1 - IDENTIFICA��O DO ESTABELECIMENTO USU�RIO DO PAF-ECF

  TRegistroP1 = class(TRegistroX1)
  end;

  /// REGISTRO TIPO P2 - RELA��O DE MERCADORIAS E SERVI�OS

  TRegistroP2 = class
  private
    fRegistroValido: boolean;
    fCOD_MERC_SERV: string;     /// C�digo da mercadoria ou servi�o
    fDESC_MERC_SERV: string;    /// Descri��o da mercadoria ou servi�o
    fUN_MED: string;            /// Unidade de medida
    fIAT: string;               /// Indicador de Arredondamento ou Truncamento, conforme item 7.2.1.3
    fIPPT: string;              /// Indicador de Produ��o Pr�pria ou de Terceiro, conforme item 7.2.1.4
    fST: string;                /// C�digo da Situa��o Tributaria conforme tabela constante no item 7.2.1.5
    fALIQ: currency;            /// Al�quota, conforme item 7.2.1.6
    fVL_UNIT: currency;         /// Valor unit�rio com duas casas decimais
  public
    constructor Create; virtual; /// Create

    property RegistroValido: Boolean read fRegistroValido write fRegistroValido default True;
    property COD_MERC_SERV: string read FCOD_MERC_SERV write FCOD_MERC_SERV;
    property DESC_MERC_SERV: string read FDESC_MERC_SERV write FDESC_MERC_SERV;
    property UN_MED: string read FUN_MED write FUN_MED;
    property IAT: string read FIAT write FIAT;
    property IPPT: string read FIPPT write FIPPT;
    property ST: string read FST write FST;
    property ALIQ: currency read FALIQ write FALIQ;
    property VL_UNIT: currency read FVL_UNIT write FVL_UNIT;
  end;

  /// REGISTRO P2 - Lista

  TRegistroP2List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroP2;
    procedure SetItem(Index: Integer; const Value: TRegistroP2);
  public
    function New: TRegistroP2;
    property Items[Index: Integer]: TRegistroP2 read GetItem write SetItem;
  end;

  /// REGISTRO TIPO E9 - TOTALIZA��O DO ARQUIVO

  TRegistroP9 = class(TRegistroX9)
  end;

implementation

(* TRegistroP2List *)

function TRegistroP2List.GetItem(Index: Integer): TRegistroP2;
begin
  Result := TRegistroP2(inherited Items[Index]);
end;

function TRegistroP2List.New: TRegistroP2;
begin
  Result := TRegistroP2.Create;
  Add(Result);
end;

procedure TRegistroP2List.SetItem(Index: Integer; const Value: TRegistroP2);
begin
  Put(Index, Value);
end;

{ TRegistroP2 }

constructor TRegistroP2.Create;
begin
   fRegistroValido := True;
end;

end.
