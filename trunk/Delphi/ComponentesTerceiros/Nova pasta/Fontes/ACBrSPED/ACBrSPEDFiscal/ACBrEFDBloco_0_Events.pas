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

unit ACBrEFDBloco_0_Events;

interface

uses
  SysUtils, Math, Classes, ACBrSped;

type
  { TEventsBloco_0 }
  TEventsBloco_0 = class(TComponent)
  private
    FOwner: TComponent;

    FOnBeforeWriteRegistro0200: TWriteRegistroEvent;
    FOnBeforeWriteRegistro0206: TWriteRegistroEvent;
    FOnBeforeWriteRegistro0500: TWriteRegistroEvent;
    FOnBeforeWriteRegistro0600: TWriteRegistroEvent;
    FOnBeforeWriteRegistro0990: TWriteRegistroEvent;

    FOnWriteRegistro0200: TWriteRegistroEvent;
    FOnWriteRegistro0206: TWriteRegistroEvent;
    FOnWriteRegistro0500: TWriteRegistroEvent;
    FOnWriteRegistro0600: TWriteRegistroEvent;
    FOnWriteRegistro0990: TWriteRegistroEvent;

    FOnAfterWriteRegistro0200: TWriteRegistroEvent;
    FOnAfterWriteRegistro0206: TWriteRegistroEvent;
    FOnAfterWriteRegistro0500: TWriteRegistroEvent;
    FOnAfterWriteRegistro0600: TWriteRegistroEvent;
    FOnAfterWriteRegistro0990: TWriteRegistroEvent;

    function GetOnAfterWriteRegistro0200: TWriteRegistroEvent;
    function GetOnAfterWriteRegistro0206: TWriteRegistroEvent;
    function GetOnAfterWriteRegistro0500: TWriteRegistroEvent;
    function GetOnAfterWriteRegistro0600: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistro0200: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistro0206: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistro0500: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistro0600: TWriteRegistroEvent;
    procedure SetOnAfterWriteRegistro0200(const Value: TWriteRegistroEvent);
    procedure SetOnAfterWriteRegistro0206(const Value: TWriteRegistroEvent);
    procedure SetOnAfterWriteRegistro0500(const Value: TWriteRegistroEvent);
    procedure SetOnAfterWriteRegistro0600(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistro0200(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistro0206(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistro0500(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistro0600(const Value: TWriteRegistroEvent);
    function GetOnWriteRegistro0200: TWriteRegistroEvent;
    function GetOnWriteRegistro0206: TWriteRegistroEvent;
    function GetOnWriteRegistro0500: TWriteRegistroEvent;
    function GetOnWriteRegistro0600: TWriteRegistroEvent;
    procedure SetOnWriteRegistro0200(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistro0206(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistro0500(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistro0600(const Value: TWriteRegistroEvent);
    function GetOnAfterWriteRegistro0990: TWriteRegistroEvent;
    function GetOnBeforeWriteRegistro0990: TWriteRegistroEvent;
    function GetOnWriteRegistro0990: TWriteRegistroEvent;
    procedure SetOnAfterWriteRegistro0990(const Value: TWriteRegistroEvent);
    procedure SetOnBeforeWriteRegistro0990(const Value: TWriteRegistroEvent);
    procedure SetOnWriteRegistro0990(const Value: TWriteRegistroEvent);
  public
    constructor Create(AOwner: TComponent); override; 
    destructor Destroy; override;
  published
    property OnBeforeWriteRegistro0200: TWriteRegistroEvent read GetOnBeforeWriteRegistro0200 write SetOnBeforeWriteRegistro0200;
    property OnBeforeWriteRegistro0206: TWriteRegistroEvent read GetOnBeforeWriteRegistro0206 write SetOnBeforeWriteRegistro0206;
    property OnBeforeWriteRegistro0500: TWriteRegistroEvent read GetOnBeforeWriteRegistro0500 write SetOnBeforeWriteRegistro0500;
    property OnBeforeWriteRegistro0600: TWriteRegistroEvent read GetOnBeforeWriteRegistro0600 write SetOnBeforeWriteRegistro0600;
    property OnBeforeWriteRegistro0990: TWriteRegistroEvent read GetOnBeforeWriteRegistro0990 write SetOnBeforeWriteRegistro0990;

    property OnWriteRegistro0200      : TWriteRegistroEvent read GetOnWriteRegistro0200       write SetOnWriteRegistro0200;
    property OnWriteRegistro0206      : TWriteRegistroEvent read GetOnWriteRegistro0206       write SetOnWriteRegistro0206;
    property OnWriteRegistro0500      : TWriteRegistroEvent read GetOnWriteRegistro0500       write SetOnWriteRegistro0500;
    property OnWriteRegistro0600      : TWriteRegistroEvent read GetOnWriteRegistro0600       write SetOnWriteRegistro0600;
    property OnWriteRegistro0990      : TWriteRegistroEvent read GetOnWriteRegistro0990       write SetOnWriteRegistro0990;

    property OnAfterWriteRegistro0200 : TWriteRegistroEvent read GetOnAfterWriteRegistro0200  write SetOnAfterWriteRegistro0200;
    property OnAfterWriteRegistro0206 : TWriteRegistroEvent read GetOnAfterWriteRegistro0206  write SetOnAfterWriteRegistro0206;
    property OnAfterWriteRegistro0500 : TWriteRegistroEvent read GetOnAfterWriteRegistro0500  write SetOnAfterWriteRegistro0500;
    property OnAfterWriteRegistro0600 : TWriteRegistroEvent read GetOnAfterWriteRegistro0600  write SetOnAfterWriteRegistro0600;
    property OnAfterWriteRegistro0990 : TWriteRegistroEvent read GetOnAfterWriteRegistro0990  write SetOnAfterWriteRegistro0990;
  end;

implementation

uses ACBrSpedFiscal;


{ TEventsBloco_0 }

constructor TEventsBloco_0.Create(AOwner: TComponent);
begin
   inherited Create(AOwner);
   FOwner := AOwner;
end;

destructor TEventsBloco_0.Destroy;
begin
   FOwner := nil;
   inherited Destroy;
end;

function TEventsBloco_0.GetOnAfterWriteRegistro0200: TWriteRegistroEvent;
begin
   Result := FOnAfterWriteRegistro0200;
end;

function TEventsBloco_0.GetOnAfterWriteRegistro0206: TWriteRegistroEvent;
begin
   Result := FOnAfterWriteRegistro0206;
end;

function TEventsBloco_0.GetOnAfterWriteRegistro0500: TWriteRegistroEvent;
begin
   Result := FOnAfterWriteRegistro0500;
end;

function TEventsBloco_0.GetOnAfterWriteRegistro0600: TWriteRegistroEvent;
begin
   Result := FOnAfterWriteRegistro0600;
end;

function TEventsBloco_0.GetOnAfterWriteRegistro0990: TWriteRegistroEvent;
begin
  Result := FOnAfterWriteRegistro0990;
end;

function TEventsBloco_0.GetOnBeforeWriteRegistro0200: TWriteRegistroEvent;
begin
   Result := FOnBeforeWriteRegistro0200;
end;

function TEventsBloco_0.GetOnBeforeWriteRegistro0206: TWriteRegistroEvent;
begin
   Result := FOnBeforeWriteRegistro0206;
end;

function TEventsBloco_0.GetOnBeforeWriteRegistro0500: TWriteRegistroEvent;
begin
   Result := FOnBeforeWriteRegistro0500;
end;

function TEventsBloco_0.GetOnBeforeWriteRegistro0600: TWriteRegistroEvent;
begin
   Result := FOnBeforeWriteRegistro0600;
end;

function TEventsBloco_0.GetOnBeforeWriteRegistro0990: TWriteRegistroEvent;
begin
  Result := FOnBeforeWriteRegistro0990;
end;

function TEventsBloco_0.GetOnWriteRegistro0200: TWriteRegistroEvent;
begin
    Result := FOnWriteRegistro0200;
end;

function TEventsBloco_0.GetOnWriteRegistro0206: TWriteRegistroEvent;
begin
   Result := FOnWriteRegistro0206;
end;

function TEventsBloco_0.GetOnWriteRegistro0500: TWriteRegistroEvent;
begin
   Result := FOnWriteRegistro0500;
end;

function TEventsBloco_0.GetOnWriteRegistro0600: TWriteRegistroEvent;
begin
   Result := FOnWriteRegistro0600;
end;

function TEventsBloco_0.GetOnWriteRegistro0990: TWriteRegistroEvent;
begin
  Result := FOnWriteRegistro0990;
end;

procedure TEventsBloco_0.SetOnAfterWriteRegistro0200(const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistro0200 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_0.OnAfterWriteRegistro0200 := Value;
end;

procedure TEventsBloco_0.SetOnAfterWriteRegistro0206(const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistro0206 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_0.OnAfterWriteRegistro0206 := Value;
end;

procedure TEventsBloco_0.SetOnAfterWriteRegistro0500(const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistro0500 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_0.OnAfterWriteRegistro0500 := Value;
end;

procedure TEventsBloco_0.SetOnAfterWriteRegistro0600(const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistro0600 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_0.OnAfterWriteRegistro0600 := Value;
end;

procedure TEventsBloco_0.SetOnAfterWriteRegistro0990( const Value: TWriteRegistroEvent);
begin
  FOnAfterWriteRegistro0990 := Value;
  TACBrSPEDFiscal(FOwner).Bloco_0.OnAfterWriteRegistro0990 := Value;
end;

procedure TEventsBloco_0.SetOnBeforeWriteRegistro0200(const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistro0200 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_0.OnBeforeWriteRegistro0200 := Value;
end;

procedure TEventsBloco_0.SetOnBeforeWriteRegistro0206(const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistro0206 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_0.OnBeforeWriteRegistro0206 := Value;
end;

procedure TEventsBloco_0.SetOnBeforeWriteRegistro0500(const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistro0500 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_0.OnBeforeWriteRegistro0500 := Value;
end;

procedure TEventsBloco_0.SetOnBeforeWriteRegistro0600(const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistro0600 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_0.OnBeforeWriteRegistro0600 := Value;
end;

procedure TEventsBloco_0.SetOnBeforeWriteRegistro0990( const Value: TWriteRegistroEvent);
begin
  FOnBeforeWriteRegistro0990 := Value;
  TACBrSPEDFiscal(FOwner).Bloco_0.OnBeforeWriteRegistro0990 := Value;
end;

procedure TEventsBloco_0.SetOnWriteRegistro0200(const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistro0200 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_0.OnWriteRegistro0200 := Value;
end;

procedure TEventsBloco_0.SetOnWriteRegistro0206(const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistro0206 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_0.OnAfterWriteRegistro0206 := Value;
end;

procedure TEventsBloco_0.SetOnWriteRegistro0500(const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistro0500 := Value;

  TACBrSPEDFiscal(FOwner).Bloco_0.OnAfterWriteRegistro0500 := Value;
end;

procedure TEventsBloco_0.SetOnWriteRegistro0600(const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistro0600 := Value;
  TACBrSPEDFiscal(FOwner).Bloco_0.OnAfterWriteRegistro0600 := Value;
end;

procedure TEventsBloco_0.SetOnWriteRegistro0990( const Value: TWriteRegistroEvent);
begin
  FOnWriteRegistro0990 := Value;
  TACBrSPEDFiscal(FOwner).Bloco_0.OnAfterWriteRegistro0990 := Value;
end;

end.
