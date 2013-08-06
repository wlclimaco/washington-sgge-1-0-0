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

unit ACBrFContBloco_J;

interface

uses
  SysUtils, Classes, Contnrs, DateUtils, ACBrFContBlocos;

type
  /// Registro J001 - ABERTURA DO BLOCO J

  TRegistroJ001 = class(TOpenBlocos)
  private
  public
  end;

  /// Registro J930 � IDENTIFICA��O DOS SIGNAT�RIOS DA ESCRITURA��O

  TRegistroJ930 = class
  private
    fIDENT_NOM: String;      /// Nome do signat�rio.
    fIDENT_CPF_CNPJ: String;      /// CPF/CNPJ.
    fIDENT_QUALIF: String;   /// Qualifica��o do assinante, conforme tabela do Departamento Nacional de Registro do Com�rcio - DNRC.
    fCOD_ASSIN: String;      /// C�digo de qualifica��o do assinante, conforme tabela do Departamento Nacional de Registro do Com�rcio - DNRC.
    fIND_CRC: String;        /// N�mero de inscri��o do contabilista no Conselho Regional de Contabilidade.
  public
    property IDENT_NOM: String read fIDENT_NOM write fIDENT_NOM;
    property IDENT_CPF_CNPJ: String read fIDENT_CPF_CNPJ write fIDENT_CPF_CNPJ;
    property IDENT_QUALIF: String read fIDENT_QUALIF write fIDENT_QUALIF;
    property COD_ASSIN: String read fCOD_ASSIN write fCOD_ASSIN;
    property IND_CRC: String read fIND_CRC write fIND_CRC;
  end;

  /// Registro J930 - Lista

  TRegistroJ930List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistroJ930;
    procedure SetItem(Index: Integer; const Value: TRegistroJ930);
  public
    function New: TRegistroJ930;
    property Items[Index: Integer]: TRegistroJ930 read GetItem write SetItem;
  end;

  /// Registro J990 - ENCERRAMENTO DO BLOCO J

  TRegistroJ990 = class
  private
    fQTD_LIN_J: Integer;    /// Quantidade total de linhas do Bloco J
  public
    property QTD_LIN_J: Integer read FQTD_LIN_J write FQTD_LIN_J;
  end;

implementation

{ TRegistroJ930List }

function TRegistroJ930List.GetItem(Index: Integer): TRegistroJ930;
begin
  Result := TRegistroJ930(Inherited Items[Index]);
end;

function TRegistroJ930List.New: TRegistroJ930;
begin
  Result := TRegistroJ930.Create;
  Add(Result);
end;

procedure TRegistroJ930List.SetItem(Index: Integer; const Value: TRegistroJ930);
begin
  Put(Index, Value);
end;

end.
