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

unit ACBrPAF_T;

interface

uses
  SysUtils, Classes, Contnrs, DateUtils, ACBrPAFRegistros;

type
  /// REGISTRO TIPO T1 - IDENTIFICA��O DO ESTABELECIMENTO USU�RIO DO PAF-ECF

  TRegistroT1 = class(TRegistroX1)
  end;

  /// REGISTRO TIPO T2 - MOVIMENTO DI�RIO - TRANSPORTE DE PASSAGEIROS

  TRegistroT2 = class
  private
    fRegistroValido: boolean;
    fDT_MOV: TDateTime;       /// Data a que se refere o movimento informado
    fTP_DOCTO: string;        /// Tipo do documento a que se refere o movimento informado, conforme item 7.2.1.3
    fSERIE: string;           /// S�rie do bilhete de passagem, no caso deste tipo de documento
    fNUM_BILH_I: integer;     /// N� do primeiro bilhete de passagem emitido no dia informado no campo 03, no caso deste tipo de documento
    fNUM_BILH_F: integer;     /// N� do �ltimo bilhete de passagem emitido no dia informado no campo 03, no caso deste tipo de documento
    fNUM_ECF: string;         /// N�mero de fabrica��o do ECF, no caso de documento emitido por este equipamento
    fCRZ: integer;            /// N� do Contador de Redu��o Z relativo ao documento Redu��o Z emitido pelo ECF informado no campo 08 no dia informado no campo 03
    fCFOP: string;            /// CFOP relativo ao movimento informado
    fVL_CONT: currency;       /// Valor cont�bil do movimento informado, com duas casas decimais
    fVL_BASECALC: currency;   /// Base de C�lculo relativa ao movimento informado, com duas casas decimais
    fALIQ: currency;          /// Al�quota do ICMS incidente sobre o movimento informado
    fVL_IMPOSTO: currency;    /// Valor do ICMS incidente sobre o movimento informado, com duas casas decimais
    fVL_ISENTAS: currency;    /// Valor das presta��es isentas do ICMS relativas ao movimento informado, com duas casas decimais
    fVL_OUTRAS: currency;     /// Valor de outras situa��es tribut�rias relativas ao movimento informado, com duas casas decimais
  public
    constructor Create; virtual; /// Create

    property RegistroValido: Boolean read fRegistroValido write fRegistroValido default True;
    property DT_MOV: TDateTime read FDT_MOV write FDT_MOV;
    property TP_DOCTO: string read FTP_DOCTO write FTP_DOCTO;
    property SERIE: string read FSERIE write FSERIE;
    property NUM_BILH_I: integer read FNUM_BILH_I write FNUM_BILH_I;
    property NUM_BILH_F: integer read FNUM_BILH_F write FNUM_BILH_F;
    property NUM_ECF: string read FNUM_ECF write FNUM_ECF;
    property CRZ: integer read FCRZ write FCRZ;
    property CFOP: string read FCFOP write FCFOP;
    property VL_CONT: currency read FVL_CONT write FVL_CONT;
    property VL_BASECALC: currency read FVL_BASECALC write FVL_BASECALC;
    property ALIQ: currency read FALIQ write FALIQ;
    property VL_IMPOSTO: currency read FVL_IMPOSTO write FVL_IMPOSTO;
    property VL_ISENTAS: currency read FVL_ISENTAS write FVL_ISENTAS;
    property VL_OUTRAS: currency read FVL_OUTRAS write FVL_OUTRAS;
  end;

  /// REGISTRO T2 - Lista

  TRegistroT2List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroT2;
    procedure SetItem(Index: Integer; const Value: TRegistroT2);
  public
    function New: TRegistroT2;
    property Items[Index: Integer]: TRegistroT2 read GetItem write SetItem;
  end;

  /// REGISTRO TIPO T9 - TOTALIZA��O DO ARQUIVO

  TRegistroT9 = class(TRegistroX9)
  end;

implementation

(* TRegistroT2List *)

function TRegistroT2List.GetItem(Index: Integer): TRegistroT2;
begin
  Result := TRegistroT2(inherited Items[Index]);
end;

function TRegistroT2List.New: TRegistroT2;
begin
  Result := TRegistroT2.Create;
  Add(Result);
end;

procedure TRegistroT2List.SetItem(Index: Integer; const Value: TRegistroT2);
begin
  Put(Index, Value);
end;

{ TRegistroT2 }

constructor TRegistroT2.Create;
begin
   fRegistroValido := True;
end;

end.
