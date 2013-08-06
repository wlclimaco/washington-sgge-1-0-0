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

unit ACBrFContBloco_J_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrSped, ACBrFContBloco_J;

type
  /// TBloco_J -
  TBloco_J = class(TACBrSPED)
  private
    FRegistroJ001: TRegistroJ001;      /// BLOCO J - RegistroJ001
    FRegistroJ930: TRegistroJ930List;  /// BLOCO J - Lista de RegistroJ930
    FRegistroJ990: TRegistroJ990;      /// BLOCO J - FRegistroJ990

  public
    constructor Create; /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    function WriteRegistroJ001: String;
    function WriteRegistroJ930: String;
    function WriteRegistroJ990: String;

    property RegistroJ001: TRegistroJ001     read fRegistroJ001 write fRegistroJ001;
    property RegistroJ930: TRegistroJ930List read fRegistroJ930 write fRegistroJ930;
    property RegistroJ990: TRegistroJ990     read fRegistroJ990 write fRegistroJ990;
  end;

implementation

{ TBloco_J }

constructor TBloco_J.Create;
begin
  FRegistroJ001 := TRegistroJ001.Create;
  FRegistroJ930 := TRegistroJ930List.Create;
  FRegistroJ990 := TRegistroJ990.Create;

  FRegistroJ990.QTD_LIN_J := 0;
end;

destructor TBloco_J.Destroy;
begin
  FRegistroJ001.Free;
  FRegistroJ930.Free;
  FRegistroJ990.Free;
  inherited;
end;

procedure TBloco_J.LimpaRegistros;
begin
  FRegistroJ930.Clear;

  FRegistroJ990.QTD_LIN_J := 0;
end;

function TBloco_J.WriteRegistroJ001: String;
begin
  Result := '';

  if Assigned(FRegistroJ001) then
  begin
     with FRegistroJ001 do
     begin
       Check(((IND_DAD = 0) or (IND_DAD = 1)), '(J-J001) Na abertura do bloco, deve ser informado o n�mero 0 ou 1!');
       ///
       Result := LFill('J001') +
                 LFill(IND_DAD, 1) +
                 Delimitador +
                 #13#10;
       ///
       FRegistroJ990.QTD_LIN_J := FRegistroJ990.QTD_LIN_J + 1;
     end;
  end;
end;

function TBloco_J.WriteRegistroJ930: String;
var
intFor: integer;
strRegistroJ930: String;
begin
  strRegistroJ930 := '';

  if Assigned(FRegistroJ930) then
  begin
     for intFor := 0 to FRegistroJ930.Count - 1 do
     begin
        with FRegistroJ930.Items[intFor] do
        begin
           ///
           strRegistroJ930 :=  strRegistroJ930 + LFill('J930') +
                                                 LFill(IDENT_NOM) +
                                                 LFill(IDENT_CPF_CNPJ) +
                                                 LFill(IDENT_QUALIF) +
                                                 LFill(COD_ASSIN, 3) +
                                                 LFill(IND_CRC, 11) +
                                                 Delimitador +
                                                 #13#10;
        end;
        FRegistroJ990.QTD_LIN_J := FRegistroJ990.QTD_LIN_J + 1;
     end;
  end;
  Result := strRegistroJ930;
end;

function TBloco_J.WriteRegistroJ990: String;
begin
  Result := '';

  if Assigned(FRegistroJ990) then
  begin
     with FRegistroJ990 do
     begin
       QTD_LIN_J := QTD_LIN_J + 1;
       ///
       Result := LFill('J990') +
                 LFill(QTD_LIN_J, 0) +
                 Delimitador +
                 #13#10;
     end;
  end;
end;

end.
