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

unit ACBrEFDBloco_C_Events;

interface

uses
  SysUtils, Math, Classes, ACBrSped;

type
  { TEventsBloco_0 }
  TEventsBloco_C = class(TComponent)
  private
    FOwner: TComponent;

    FOnBeforeWriteRegistroC111: TWriteRegistroEvent;
    FOnBeforeWriteRegistroC120: TWriteRegistroEvent;
    FOnBeforeWriteRegistroC170: TWriteRegistroEvent;
    FOnBeforeWriteRegistroC470: TWriteRegistroEvent;
    FOnBeforeWriteRegistroC510: TWriteRegistroEvent;

    FOnWriteRegistroC111: TWriteRegistroEvent;
    FOnWriteRegistroC120: TWriteRegistroEvent;
    FOnWriteRegistroC170: TWriteRegistroEvent;
    FOnWriteRegistroC470: TWriteRegistroEvent;
    FOnWriteRegistroC510: TWriteRegistroEvent;

    FOnAfterWriteRegistroC111: TWriteRegistroEvent;
    FOnAfterWriteRegistroC120: TWriteRegistroEvent;
    FOnAfterWriteRegistroC170: TWriteRegistroEvent;
    FOnAfterWriteRegistroC470: TWriteRegistroEvent;
    FOnAfterWriteRegistroC510: TWriteRegistroEvent;

    function GetOnAfterWriteRegistroC111: TWriteRegistroEvent;
    function GetOnAfterWriteRegistroC120: TWriteRegistroEvent;
    function GetOnAfterWriteRegistroC170: TWriteRegistroEvent;
    function GetOnAfterWriteRegistroC470: TWriteRegistroEvent;
    function GetOnAfterWriteRegistroC510: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistroC111: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistroC120: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistroC170: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistroC470: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistroC510: TWriteRegistroEvent;
    function GetOnWriteRegistroC111: TWriteRegistroEvent;
    function GetOnWriteRegistroC120: TWriteRegistroEvent;
    function GetOnWriteRegistroC170: TWriteRegistroEvent;
    function GetOnWriteRegistroC470: TWriteRegistroEvent;
    function GetOnWriteRegistroC510: TWriteRegistroEvent;
    procedure SetOnAfterWriteRegistroC111(const Value: TWriteRegistroEvent);
    procedure SetOnAfterWriteRegistroC120(const Value: TWriteRegistroEvent);
    procedure SetOnAfterWriteRegistroC170(const Value: TWriteRegistroEvent);
    procedure SetOnAfterWriteRegistroC470(const Value: TWriteRegistroEvent);
    procedure SetOnAfterWriteRegistroC510(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistroC111(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistroC120(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistroC170(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistroC470(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistroC510(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistroC111(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistroC120(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistroC170(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistroC470(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistroC510(const Value: TWriteRegistroEvent);

  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
  published
    property OnBeforeWriteRegistroC111: TWriteRegistroEvent read GetOnBeforeWriteRegistroC111 write SetOnBeforeWriteRegistroC111;
    property OnBeforeWriteRegistroC120: TWriteRegistroEvent read GetOnBeforeWriteRegistroC120 write SetOnBeforeWriteRegistroC120;
    property OnBeforeWriteRegistroC170: TWriteRegistroEvent read GetOnBeforeWriteRegistroC170 write SetOnBeforeWriteRegistroC170;
    property OnBeforeWriteRegistroC470: TWriteRegistroEvent read GetOnBeforeWriteRegistroC470 write SetOnBeforeWriteRegistroC470;
    property OnBeforeWriteRegistroC510: TWriteRegistroEvent read GetOnBeforeWriteRegistroC510 write SetOnBeforeWriteRegistroC510;

    property OnWriteRegistroC111: TWriteRegistroEvent read GetOnWriteRegistroC111 write SetOnWriteRegistroC111;
    property OnWriteRegistroC120: TWriteRegistroEvent read GetOnWriteRegistroC120 write SetOnWriteRegistroC120;
    property OnWriteRegistroC170: TWriteRegistroEvent read GetOnWriteRegistroC170 write SetOnWriteRegistroC170;
    property OnWriteRegistroC470: TWriteRegistroEvent read GetOnWriteRegistroC470 write SetOnWriteRegistroC470;
    property OnWriteRegistroC510: TWriteRegistroEvent read GetOnWriteRegistroC510 write SetOnWriteRegistroC510;

    property OnAfterWriteRegistroC111: TWriteRegistroEvent read GetOnAfterWriteRegistroC111 write SetOnAfterWriteRegistroC111;
    property OnAfterWriteRegistroC120: TWriteRegistroEvent read GetOnAfterWriteRegistroC120 write SetOnAfterWriteRegistroC120;
    property OnAfterWriteRegistroC170: TWriteRegistroEvent read GetOnAfterWriteRegistroC170 write SetOnAfterWriteRegistroC170;
    property OnAfterWriteRegistroC470: TWriteRegistroEvent read GetOnAfterWriteRegistroC470 write SetOnAfterWriteRegistroC470;
    property OnAfterWriteRegistroC510: TWriteRegistroEvent read GetOnAfterWriteRegistroC510 write SetOnAfterWriteRegistroC510;
  end;

implementation

uses ACBrSpedFiscal;

{ TEventsBloco_0 }

constructor TEventsBloco_C.Create(AOwner: TComponent);
begin
   inherited Create(AOwner);
   FOwner := AOwner;
end;

destructor TEventsBloco_C.Destroy;
begin
   FOwner := nil;
   inherited Destroy;
end;

function TEventsBloco_C.GetOnAfterWriteRegistroC111: TWriteRegistroEvent;
begin
   Result := FOnAfterWriteRegistroC111;
end;

function TEventsBloco_C.GetOnAfterWriteRegistroC120: TWriteRegistroEvent;
begin
   Result := FOnAfterWriteRegistroC120;
end;

function TEventsBloco_C.GetOnAfterWriteRegistroC170: TWriteRegistroEvent;
begin
   Result := FOnAfterWriteRegistroC170;
end;

function TEventsBloco_C.GetOnAfterWriteRegistroC470: TWriteRegistroEvent;
begin
   Result := FOnAfterWriteRegistroC470;
end;

function TEventsBloco_C.GetOnAfterWriteRegistroC510: TWriteRegistroEvent;
begin
   Result := FOnAfterWriteRegistroC510;
end;

function TEventsBloco_C.GetOnBeforeWriteRegistroC111: TWriteRegistroEvent;
begin
   Result := FOnBeforeWriteRegistroC111;
end;

function TEventsBloco_C.GetOnBeforeWriteRegistroC120: TWriteRegistroEvent;
begin
   Result := FOnBeforeWriteRegistroC120;
end;

function TEventsBloco_C.GetOnBeforeWriteRegistroC170: TWriteRegistroEvent;
begin
   Result := FOnBeforeWriteRegistroC170;
end;

function TEventsBloco_C.GetOnBeforeWriteRegistroC470: TWriteRegistroEvent;
begin
   Result := FOnBeforeWriteRegistroC470;
end;

function TEventsBloco_C.GetOnBeforeWriteRegistroC510: TWriteRegistroEvent;
begin
   Result := FOnBeforeWriteRegistroC510;
end;

function TEventsBloco_C.GetOnWriteRegistroC111: TWriteRegistroEvent;
begin
   Result := FOnWriteRegistroC111;
end;

function TEventsBloco_C.GetOnWriteRegistroC120: TWriteRegistroEvent;
begin
   Result := FOnWriteRegistroC120;
end;

function TEventsBloco_C.GetOnWriteRegistroC170: TWriteRegistroEvent;
begin
   Result := FOnWriteRegistroC170;
end;

function TEventsBloco_C.GetOnWriteRegistroC470: TWriteRegistroEvent;
begin
   Result := FOnWriteRegistroC470;
end;

function TEventsBloco_C.GetOnWriteRegistroC510: TWriteRegistroEvent;
begin
   Result := FOnWriteRegistroC510;
end;

procedure TEventsBloco_C.SetOnAfterWriteRegistroC111(
  const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistroC111 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnAfterWriteRegistroC111 := Value;
end;

procedure TEventsBloco_C.SetOnAfterWriteRegistroC120(
  const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistroC120 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnAfterWriteRegistroC120 := Value;
end;

procedure TEventsBloco_C.SetOnAfterWriteRegistroC170(
  const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistroC170 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnAfterWriteRegistroC170 := Value;
end;

procedure TEventsBloco_C.SetOnAfterWriteRegistroC470(
  const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistroC470 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnAfterWriteRegistroC470 := Value;
end;

procedure TEventsBloco_C.SetOnAfterWriteRegistroC510(
  const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistroC510 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnAfterWriteRegistroC510 := Value;
end;

procedure TEventsBloco_C.SetOnBeforeWriteRegistroC111(
  const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistroC111 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnBeforeWriteRegistroC111 := Value;
end;

procedure TEventsBloco_C.SetOnBeforeWriteRegistroC120(
  const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistroC120 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnBeforeWriteRegistroC120 := Value;
end;

procedure TEventsBloco_C.SetOnBeforeWriteRegistroC170(
  const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistroC170 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnBeforeWriteRegistroC170 := Value;
end;

procedure TEventsBloco_C.SetOnBeforeWriteRegistroC470(
  const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistroC470 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnBeforeWriteRegistroC470 := Value;
end;

procedure TEventsBloco_C.SetOnBeforeWriteRegistroC510(
  const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistroC510 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnBeforeWriteRegistroC510 := Value;
end;

procedure TEventsBloco_C.SetOnWriteRegistroC111(
  const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistroC111 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnWriteRegistroC111 := Value;
end;

procedure TEventsBloco_C.SetOnWriteRegistroC120(
  const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistroC120 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnWriteRegistroC120 := Value;
end;

procedure TEventsBloco_C.SetOnWriteRegistroC170(
  const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistroC170 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnWriteRegistroC170 := Value;
end;

procedure TEventsBloco_C.SetOnWriteRegistroC470(
  const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistroC470 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnWriteRegistroC470 := Value;
end;

procedure TEventsBloco_C.SetOnWriteRegistroC510(
  const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistroC510 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_C.OnWriteRegistroC510 := Value;
end;

end.
