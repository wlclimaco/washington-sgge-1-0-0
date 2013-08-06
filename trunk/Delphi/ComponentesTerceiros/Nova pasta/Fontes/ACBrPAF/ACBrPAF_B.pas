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
|* 01/03/2012: Gutierres Santana da Costa
|*  - Implementado Registro Tipo B "Substitui��o da placa eletr�nica de
|     gerenciamento de bomba de combustivel
*******************************************************************************}

unit ACBrPAF_B;

interface

uses
  SysUtils, Classes, Contnrs, DateUtils, ACBrPAFRegistros;

type
  /// REGISTRO TIPO B1 - IDENTIFICA��O DO ESTABELECIMENTO USU�RIO DO PAF-ECF

  TRegistroB1 = class(TRegistroX1)
  end;

  /// REGISTRO TIPO B2 - REGISTRO DE SUBSTITUI��O DA PLACA ELETR�NICA DE GERENCIAMENTO DE BOMBA DE COMBUSTIVEL

  TRegistroB2 = class
  private
    fRegistroValido: boolean;
//    fTANQUE: string;
    fBOMBA: string;
    fBICO: string;
    fDATA: TDateTime;
    fHORA: TDateTime;
    fMOTIVO: string;
    fCNPJ_EMPRESA: string;
    fCPF_TECNICO: string;
    fNRO_LACRE_ANTES: string;
    fNRO_LACRE_APOS: string;
    fENCERRANTE_ANTES: currency;
    fENCERRANTE_APOS: currency;
  public
    constructor Create; virtual; /// Create

    property RegistroValido: Boolean read fRegistroValido write fRegistroValido default True;
    property BOMBA: string read fBOMBA write fBOMBA;
    property BICO: string read fBICO write fBICO;
    property DATA: TDateTime read fDATA write fDATA;
    property HORA: TDateTime read fHORA write fHORA;
    property MOTIVO: string read fMOTIVO write fMOTIVO;
    property CNPJ_EMPRESA: string read fCNPJ_EMPRESA write fCNPJ_EMPRESA;
    property CPF_TECNICO: string read fCPF_TECNICO write fCPF_TECNICO;
    property NRO_LACRE_ANTES: string read fNRO_LACRE_ANTES write fNRO_LACRE_ANTES;
    property NRO_LACRE_APOS: string read fNRO_LACRE_APOS write fNRO_LACRE_APOS;
    property ENCERRANTE_ANTES: currency read fENCERRANTE_ANTES write fENCERRANTE_ANTES;
    property ENCERRANTE_APOS: currency read fENCERRANTE_APOS write fENCERRANTE_APOS;
  end;

  /// REGISTRO B2 - Lista

  TRegistroB2List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroB2;
    procedure SetItem(Index: Integer; const Value: TRegistroB2);
  public
    function New: TRegistroB2;
    property Items[Index: Integer]: TRegistroB2 read GetItem write SetItem;
  end;

  /// REGISTRO TIPO B9 - TOTALIZA��O DO ARQUIVO

  TRegistroB9 = class(TRegistroX9)
  end;

implementation

(* TRegistroB2List *)

function TRegistroB2List.GetItem(Index: Integer): TRegistroB2;
begin
  Result := TRegistroB2(inherited Items[Index]);
end;

function TRegistroB2List.New: TRegistroB2;
begin
  Result := TRegistroB2.Create;
  Add(Result);
end;

procedure TRegistroB2List.SetItem(Index: Integer; const Value: TRegistroB2);
begin
  Put(Index, Value);
end;

{ TRegistroB2 }

constructor TRegistroB2.Create;
begin
  fRegistroValido := True;
end;

end.

