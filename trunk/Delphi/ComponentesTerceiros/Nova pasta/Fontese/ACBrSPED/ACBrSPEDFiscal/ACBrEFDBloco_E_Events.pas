{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2012   Isaque Pinheiro                      }
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
|* 15/04/2012: Isaque Pinheiro
|*  - Cria��o e distribui��o da Primeira Versao
*******************************************************************************}

unit ACBrEFDBloco_E_Events;

interface

uses
  SysUtils, Math, Classes, ACBrSped;

type
  { TEventsBloco_E }
  TEventsBloco_E = class(TComponent)
  private
    FOwner: TComponent;

    FOnBeforeWriteRegistroE990: TWriteRegistroEvent;

    FOnWriteRegistroE990: TWriteRegistroEvent;

    FOnAfterWriteRegistroE990: TWriteRegistroEvent;

    function GetOnAfterWriteRegistroE990: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistroE990: TWriteRegistroEvent;
    function GetOnWriteRegistroE990: TWriteRegistroEvent;

    procedure SetOnAfterWriteRegistroE990(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistroE990(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistroE990(const Value: TWriteRegistroEvent);
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
  published
    property OnBeforeWriteRegistroE990: TWriteRegistroEvent read GetOnBeforeWriteRegistroE990 write SetOnBeforeWriteRegistroE990;

    property OnWriteRegistroE990: TWriteRegistroEvent read GetOnWriteRegistroE990 write SetOnWriteRegistroE990;

    property OnAfterWriteRegistroE990: TWriteRegistroEvent read GetOnAfterWriteRegistroE990 write SetOnAfterWriteRegistroE990;
  end;

implementation

uses ACBrSpedFiscal;

{ TEventsBloco_0 }

constructor TEventsBloco_E.Create(AOwner: TComponent);
begin
   inherited Create(AOwner);
   FOwner := AOwner;
end;

destructor TEventsBloco_E.Destroy;
begin
   FOwner := nil;
   inherited Destroy;
end;

function TEventsBloco_E.GetOnAfterWriteRegistroE990: TWriteRegistroEvent;
begin
  Result := FOnAfterWriteRegistroE990;
end;

function TEventsBloco_E.GetOnBeforeWriteRegistroE990: TWriteRegistroEvent;
begin
  Result := FOnBeforeWriteRegistroE990;
end;

function TEventsBloco_E.GetOnWriteRegistroE990: TWriteRegistroEvent;
begin
  Result := FOnWriteRegistroE990;
end;

procedure TEventsBloco_E.SetOnAfterWriteRegistroE990( const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistroE990 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_E.OnAfterWriteRegistroE990 := Value;
end;


procedure TEventsBloco_E.SetOnBeforeWriteRegistroE990( const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistroE990:= Value;

  TACBrSPEDFiscal(FOwner).Bloco_E.OnBeforeWriteRegistroE990 := Value;
end;

procedure TEventsBloco_E.SetOnWriteRegistroE990( const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistroE990 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_E.OnWriteRegistroE990 := Value;
end;

end.
