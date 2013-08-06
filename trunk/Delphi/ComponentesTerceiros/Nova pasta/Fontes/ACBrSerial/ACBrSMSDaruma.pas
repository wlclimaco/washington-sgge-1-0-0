{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo: Alexandre Rocha Lima e Marcondes                }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
{                                                                              }
{ Esse arquivo usa a classe  SynaSer   Copyright (c)2001-2003, Lukas Gebauer   }
{  Project : Ararat Synapse     (Found at URL: http://www.ararat.cz/synapse/)  }
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

{$I ACBr.inc}

unit ACBrSMSDaruma;

interface

uses
  ACBrSMSClass, Classes;

type
  TACBrSMSDaruma = class(TACBrSMSClass)
  private

  public
    constructor Create(AOwner: TComponent);
    procedure TrocarBandeja(const ASimCard: TACBrSMSSimCard); override;
  end;

implementation

uses
  ACBrUtil, SysUtils;

{ TACBrSMSDaruma }

constructor TACBrSMSDaruma.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);

  fpBandejasSimCard := 2;
end;

procedure TACBrSMSDaruma.TrocarBandeja(const ASimCard: TACBrSMSSimCard);
var
  cmd: String;
  Tentativas: Integer;
  Str: String;
  ListaParametro: TStringList;
begin
  if ASimCard = SimCard then
    Exit;

  case ASimCard of
    simCard1: cmd := 'ATL1';
    simCard2: cmd := 'ATL2';
  end;

  fpDevice.Serial.Purge;
  Self.EnviarComando(cmd);

  if Self.ATResult then
  begin
    Self.SimCard := ASimCard;

    ListaParametro := TStringList.Create;
    try
      Tentativas := 0;
      repeat

        Sleep(500);
        Self.EnviarComando('AT+CIND?');
        //exemplo de retorno:   +CIND: 5,99,1,0,0,0,0,1,4

        Str := Self.UltimaResposta;
        Str := Trim(Copy(Str, 7, Length(Str) - 9));
        Str := StringReplace(Str, ',', sLineBreak, [rfReplaceAll]);

        ListaParametro.Text := Str;
        Str := ListaParametro[2];

        Inc(Tentativas);

      until (Str = '1') or (Tentativas >= 30);

      if Tentativas > 30 then
        raise EACBrSMSException.Create('N�o foi possivel sincronizar o SinCard com a operadora de telefonia.');
    finally
      ListaParametro.Free;
    end;
  end
  else
    raise EACBrSMSException.Create('N�o foi poss�vel efetuar a troca da bandeja.')
end;

end.

