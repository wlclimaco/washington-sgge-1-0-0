{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }

{ Direitos Autorais Reservados (c) 2009   Isaque Pinheiro                      }

{ Colaboradores nesse arquivo:                                                 }

{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }

{  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la }
{ sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) }
{ qualquer vers�o posterior.                                                   }

{  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      }
{ ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              }

{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto}
{ com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  }
{ no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Voc� tamb�m pode obter uma copia da licen�a em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }

{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }

{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 10/04/2009: Isaque Pinheiro
|*  - Cria��o e distribui��o da Primeira Versao
*******************************************************************************}

unit ACBrPAF_N;

interface

uses
  SysUtils, Classes, Contnrs, DateUtils, ACBrPAFRegistros;

type
  /// REGISTRO TIPO N1 - IDENTIFICA��O DO DESENVOLVEDOR DO PAF-ECF:

  TRegistroN1 = class(TRegistroX1)
  end;

  /// REGISTRO TIPO N2 - IDENTIFICA��O DO PAF-ECF

  { TRegistroN2 }

  TRegistroN2 = class
  private
    fLAUDO:  string;
    fNOME:   string;
    fVERSAO: string;
  public
    property LAUDO: string read fLAUDO write fLAUDO;
 //N�mero do Laudo de An�lise Funcional
    property NOME: string read fNOME write fNOME;
//Nome do aplicativo indicado no Laudo de An�lise T�cnica
    property VERSAO: string read fVERSAO write fVERSAO;
//Vers�o atual do aplicativo indicado no Laudo de An�lise T�cnica
  end;

  /// REGISTRO TIPO N3 - RELA��O DOS EXECUT�VEIS E SEUS C�DIGOS DE AUTENTICA��O (MD5):

  { TRegistroN3 }

  TRegistroN3 = class
  private
    fMD5: string;
    fNOME_ARQUIVO: string;
  public
    property NOME_ARQUIVO : string read fNOME_ARQUIVO write fNOME_ARQUIVO;
    property MD5 : string read fMD5 write fMD5;
  end;

  /// REGISTRO TIPO N3 - Lista

  TRegistroN3List = class(TObjectList)
  private
    function GetItem(Index: integer): TRegistroN3;
    procedure SetItem(Index: integer; const Value: TRegistroN3);
  public
    function New: TRegistroN3;
    property Items[Index: integer]: TRegistroN3 read GetItem write SetItem; default;
  end;

  /// REGISTRO TIPO N9 - TOTALIZA��O DO ARQUIVO

  TRegistroN9 = class(TRegistroX9)
  end;

implementation

(* TRegistroN3List *)

function TRegistroN3List.GetItem(Index: integer): TRegistroN3;
begin
  Result := TRegistroN3(inherited Items[Index]);
end;

function TRegistroN3List.New: TRegistroN3;
begin
  Result := TRegistroN3.Create;
  Add(Result);
end;

procedure TRegistroN3List.SetItem(Index: integer; const Value: TRegistroN3);
begin
  Put(Index, Value);
end;

end.

