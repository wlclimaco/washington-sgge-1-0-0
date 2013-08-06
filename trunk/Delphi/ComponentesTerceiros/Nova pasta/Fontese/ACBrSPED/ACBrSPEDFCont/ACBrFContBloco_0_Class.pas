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

unit ACBrFContBloco_0_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrSped, ACBrFContBloco_0;

type
  /// TBLOCO_0 - Abertura, Identifica��o e Refer�ncias
  TBloco_0 = class(TACBrSPED)
  private
    FRegistro0000: TRegistro0000;      /// BLOCO 0 - Registro0000
  protected
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    function WriteRegistro0000: String;

    property Registro0000: TRegistro0000     read FRegistro0000 write FRegistro0000;
  end;

implementation

uses ACBrSpedUtils;

{ TBloco_0 }

constructor TBloco_0.Create;
begin
  FRegistro0000 := TRegistro0000.Create;
end;

destructor TBloco_0.Destroy;
begin
  FRegistro0000.Free;

  inherited;
end;

procedure TBloco_0.LimpaRegistros;
begin

end;

function TBloco_0.WriteRegistro0000: String;
begin
  Result := '';

  if Assigned(FRegistro0000) then
  begin
     with FRegistro0000 do
     begin
       Check(NOME <> '', '(0-0000) O nome empresarial � obrigat�rio!');
       Check(funChecaCNPJ(CNPJ), '(0-0000) O CNPJ "%s" digitado � inv�lido!', [CNPJ]);
       Check(funChecaUF(UF), '(0-0000) A UF "%s" digitada � inv�lido!', [UF]);
       Check(funChecaIE(IE, UF), '(0-0000) A inscri��o estadual "%s" digitada � inv�lida!', [IE]);
       Check(funChecaMUN(StrToInt(COD_MUN)), '(0-0000) O c�digo do munic�pio "%s" digitado � inv�lido!', [COD_MUN]);
       Check((((IND_SIT_ESP >= '0') and (IND_SIT_ESP <= '4')) or (IND_SIT_ESP = '')), '(0-0000) O indicador "%s" de situa��o especial, deve ser vazio ou informado o n�mero 0 ou 1 ou 2 ou 3 ou 4!', [IND_SIT_ESP]);
       Check(((IND_SIT_INI_PER >= '0') and (IND_SIT_INI_PER <= '3')), '(0-0000) O indicador "%s" de in�cio do per�odo, deve ser informado o n�mero 0 ou 1 ou 2 ou 3!', [IND_SIT_INI_PER]);
       ///
       Result := LFill('0000') +
                 LFill('LALU') +
                 LFill(DT_INI) +
                 LFill(DT_FIN) +
                 LFill(NOME) +
                 LFill(CNPJ) +
                 LFill(UF) +
                 LFill(IE) +
                 LFill(COD_MUN, 7) +
                 LFill(IM) +
                 LFill(IND_SIT_ESP) +
                 LFill(IND_SIT_INI_PER, 1) +
                 Delimitador +
                 #13#10;
       ///
     end;
  end;
end;

end.
