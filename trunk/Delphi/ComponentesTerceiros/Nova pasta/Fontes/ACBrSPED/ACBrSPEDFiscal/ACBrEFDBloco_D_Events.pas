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

unit ACBrEFDBloco_D_Events;

interface

uses
  SysUtils, Math, Classes, ACBrSped;

type
  { TEventsBloco_D }
  TEventsBloco_D = class(TComponent)
  private
    FOwner: TComponent;

    FOnBeforeWriteRegistroD100: TWriteRegistroEvent;
    FOnBeforeWriteRegistroD110: TWriteRegistroEvent;
    FOnBeforeWriteRegistroD510: TWriteRegistroEvent;

    FOnWriteRegistroD100: TWriteRegistroEvent;
    FOnWriteRegistroD110: TWriteRegistroEvent;
    FOnWriteRegistroD510: TWriteRegistroEvent;

    FOnAfterWriteRegistroD100: TWriteRegistroEvent;
    FOnAfterWriteRegistroD110: TWriteRegistroEvent;
    FOnAfterWriteRegistroD510: TWriteRegistroEvent;

    function GetOnAfterWriteRegistroD100: TWriteRegistroEvent;
    function GetOnAfterWriteRegistroD110: TWriteRegistroEvent;
    function GetOnAfterWriteRegistroD510: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistroD100: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistroD110: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistroD510: TWriteRegistroEvent;
    function GetOnWriteRegistroD100: TWriteRegistroEvent;
    function GetOnWriteRegistroD110: TWriteRegistroEvent;
    function GetOnWriteRegistroD510: TWriteRegistroEvent;
    procedure SetOnAfterWriteRegistroD100(const Value: TWriteRegistroEvent);
    procedure SetOnAfterWriteRegistroD110(const Value: TWriteRegistroEvent);
    procedure SetOnAfterWriteRegistroD510(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistroD100(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistroD110(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistroD510(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistroD100(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistroD110(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistroD510(const Value: TWriteRegistroEvent);

  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
  published
    property OnBeforeWriteRegistroD100: TWriteRegistroEvent read GetOnBeforeWriteRegistroD100 write SetOnBeforeWriteRegistroD100;
    property OnBeforeWriteRegistroD110: TWriteRegistroEvent read GetOnBeforeWriteRegistroD110 write SetOnBeforeWriteRegistroD110;
    property OnBeforeWriteRegistroD510: TWriteRegistroEvent read GetOnBeforeWriteRegistroD510 write SetOnBeforeWriteRegistroD510;

    property OnWriteRegistroD100: TWriteRegistroEvent read GetOnWriteRegistroD100 write SetOnWriteRegistroD100;
    property OnWriteRegistroD110: TWriteRegistroEvent read GetOnWriteRegistroD110 write SetOnWriteRegistroD110;
    property OnWriteRegistroD510: TWriteRegistroEvent read GetOnWriteRegistroD510 write SetOnWriteRegistroD510;

    property OnAfterWriteRegistroD100: TWriteRegistroEvent read GetOnAfterWriteRegistroD100 write SetOnAfterWriteRegistroD100;
    property OnAfterWriteRegistroD110: TWriteRegistroEvent read GetOnAfterWriteRegistroD110 write SetOnAfterWriteRegistroD110;
    property OnAfterWriteRegistroD510: TWriteRegistroEvent read GetOnAfterWriteRegistroD510 write SetOnAfterWriteRegistroD510;
  end;

implementation

uses ACBrSpedFiscal;

{ TEventsBloco_0 }

constructor TEventsBloco_D.Create(AOwner: TComponent);
begin
   inherited Create(AOwner);
   FOwner := AOwner;
end;

destructor TEventsBloco_D.Destroy;
begin
   FOwner := nil;
   inherited Destroy;
end;

function TEventsBloco_D.GetOnAfterWriteRegistroD100: TWriteRegistroEvent;
begin
   Result := FOnAfterWriteRegistroD100;
end;

function TEventsBloco_D.GetOnAfterWriteRegistroD110: TWriteRegistroEvent;
begin
   Result := FOnAfterWriteRegistroD110;
end;

function TEventsBloco_D.GetOnAfterWriteRegistroD510: TWriteRegistroEvent;
begin
   Result := FOnAfterWriteRegistroD510;
end;

function TEventsBloco_D.GetOnBeforeWriteRegistroD100: TWriteRegistroEvent;
begin
   Result := FOnBeforeWriteRegistroD100;
end;

function TEventsBloco_D.GetOnBeforeWriteRegistroD110: TWriteRegistroEvent;
begin
   Result := FOnBeforeWriteRegistroD110;
end;

function TEventsBloco_D.GetOnBeforeWriteRegistroD510: TWriteRegistroEvent;
begin
   Result := FOnBeforeWriteRegistroD510;
end;

function TEventsBloco_D.GetOnWriteRegistroD100: TWriteRegistroEvent;
begin
   Result := FOnWriteRegistroD100;
end;

function TEventsBloco_D.GetOnWriteRegistroD110: TWriteRegistroEvent;
begin
   Result := FOnWriteRegistroD110;
end;

function TEventsBloco_D.GetOnWriteRegistroD510: TWriteRegistroEvent;
begin
   Result := FOnWriteRegistroD510;
end;

procedure TEventsBloco_D.SetOnAfterWriteRegistroD100(
  const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistroD100 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_D.OnAfterWriteRegistroD100 := Value;
end;

procedure TEventsBloco_D.SetOnAfterWriteRegistroD110(
  const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistroD110 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_D.OnAfterWriteRegistroD110 := Value;
end;

procedure TEventsBloco_D.SetOnAfterWriteRegistroD510(
  const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistroD510 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_D.OnAfterWriteRegistroD510 := Value;
end;

procedure TEventsBloco_D.SetOnBeforeWriteRegistroD100(
  const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistroD100 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_D.OnBeforeWriteRegistroD100 := Value;
end;

procedure TEventsBloco_D.SetOnBeforeWriteRegistroD110(
  const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistroD110 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_D.OnBeforeWriteRegistroD110 := Value;
end;

procedure TEventsBloco_D.SetOnBeforeWriteRegistroD510(
  const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistroD510 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_D.OnBeforeWriteRegistroD510 := Value;
end;

procedure TEventsBloco_D.SetOnWriteRegistroD100(
  const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistroD100 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_D.OnWriteRegistroD100 := Value;
end;

procedure TEventsBloco_D.SetOnWriteRegistroD110(
  const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistroD110 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_D.OnWriteRegistroD110 := Value;
end;

procedure TEventsBloco_D.SetOnWriteRegistroD510(
  const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistroD510 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_D.OnWriteRegistroD510 := Value;
end;

end.
