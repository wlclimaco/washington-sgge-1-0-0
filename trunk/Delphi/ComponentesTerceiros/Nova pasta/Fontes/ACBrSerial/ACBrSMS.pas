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

unit ACBrSMS;

interface

uses
  ACBrBase, ACBrConsts, ACBrDevice, ACBrSMSClass,
  SysUtils , Classes{$IFNDEF FRAMEWORK}, Forms{$ENDIF};

type
  TACBrSMS = class(TACBrComponent)
  private
    fsAtivo: Boolean;
    fsDevice: TACBrDevice;
    fsSMS: TACBrSMSClass;
    fsModelo: TACBrSMSModelo;
    fsOnProgresso: TACBrSMSProgresso;

    procedure SetAtivo(const Value: Boolean);
    procedure SetModelo(const Value: TACBrSMSModelo);
    procedure SetRecebeConfirmacao(const Value: Boolean);
    function GetRecebeConfirmacao: Boolean;
    function GetSimCard: TACBrSMSSimCard;
    function GetUltimaReposta: String;
    procedure TestaAtivo;
    procedure TestaEmLinha;
    function GetQuebraMensagens: Boolean;
    procedure SetQuebraMensagens(const Value: Boolean);
    procedure SetATTimeOut(const Value: Integer);
    function GetATTimeOut: Integer;
    function GetATResult: Boolean;
    procedure SetATResult(const Value: Boolean);
    function GetBandejasSimCard: Integer;
    function GetUltimoComando: String;
    function GetIntervaloEntreMensagens: Integer;
    procedure SetIntervaloEntreMensagens(const Value: Integer);
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;

    procedure Ativar;
    procedure Desativar;

    function EmLinha: Boolean;
    function IMEI: String;
    function IMSI: String;
    function NivelSinal: Double;
    function Operadora: String;
    function Fabricante: String;
    function ModeloModem: String;
    function Firmware: String;
    function EstadoSincronismo: TACBrSMSSincronismo;

    procedure TrocarBandeja(const ASimCard: TACBrSMSSimCard);
    procedure EnviarSMS(const ATelefone, AMensagem: String;
      var AIndice: String);
    procedure EnviarSMSLote(const ALote: TACBrSMSMensagens;
      var AIndice: String);
    procedure ListarMensagens(const AFiltro: TACBrSMSFiltro;
      const APath: String);

    procedure EnviarComando(ACmd: String; ATimeOut: Integer = 0);
  published
    property Ativo: Boolean read fsAtivo write SetAtivo;
    property Device: TACBrDevice read fsDevice;
    property SMS: TACBrSMSClass read fsSMS;
    property Modelo: TACBrSMSModelo read fsModelo write SetModelo;
    property SimCard: TACBrSMSSimCard read GetSimCard;
    property ATTimeOut: Integer read GetATTimeOut write SetATTimeOut;
    property ATResult: Boolean read GetATResult write SetATResult;
    property IntervaloEntreMensagens: Integer read GetIntervaloEntreMensagens write SetIntervaloEntreMensagens;
    property RecebeConfirmacao: Boolean read GetRecebeConfirmacao write SetRecebeConfirmacao;
    property BandejasSimCard: Integer read GetBandejasSimCard;
    property QuebraMensagens: Boolean read GetQuebraMensagens write SetQuebraMensagens;
    property UltimaResposta: String read GetUltimaReposta;
    property UltimoComando: String read GetUltimoComando;
    property OnProgresso: TACBrSMSProgresso read fsOnProgresso write fsOnProgresso;
  end;

implementation

uses
  ACBrUtil, ACBrSMSDaruma, ACBrSMSZTE;

{ TACBrSMS }

constructor TACBrSMS.Create(AOwner: TComponent);
begin
  inherited create(AOwner);

  fsDevice := TACBrDevice.Create(Self);
  fsDevice.Name := 'ACBrDevice' ;
  {$IFDEF COMPILER6_UP}
  fsDevice.SetSubComponent( true );
  {$ENDIF}
  fsDevice.Porta := 'COM1';

  fsModelo := modNenhum;
  fsSMS  := TACBrSMSClass.Create(Self);
end;

destructor TACBrSMS.Destroy;
begin
  if Assigned(fsDevice) then
    FreeAndNil(fsSMS);

  if Assigned(fsDevice) then
    FreeAndNil(fsDevice);

  inherited Destroy;
end;

procedure TACBrSMS.TestaAtivo;
begin
  if not Ativo then
    raise EACBrSMSException.Create('Comunica��o ainda n�o foi ativada.');
end;

procedure TACBrSMS.TestaEmLinha;
begin
  if not EmLinha then
    raise EACBrSMSException.Create('SMS n�o est� em linha.');
end;

procedure TACBrSMS.TrocarBandeja(const ASimCard: TACBrSMSSimCard);
begin
  TestaAtivo;
  if fsSMS.SimCard <> ASimCard then
  begin
    fsSMS.TrocarBandeja(ASimCard);
    fsSMS.SimCard := ASimCard;
  end;
end;

function TACBrSMS.EmLinha: Boolean;
begin
  TestaAtivo;
  Result := fsSMS.EmLinha;
end;

procedure TACBrSMS.EnviarComando(ACmd: String; ATimeOut: Integer);
begin
  TestaAtivo;
  fsSMS.EnviarComando(ACmd, ATimeOut);
end;

procedure TACBrSMS.EnviarSMS(const ATelefone, AMensagem: String;
  var AIndice: String);
var
  F: TStringList;
  I: Integer;
  IndiceMsg: String;
begin
  TestaAtivo;
  TestaEmLinha;

  // se o parametro estiver true, ent�o quebrar em mensagens menores e enviar.
  if not QuebraMensagens then
  begin
    if Length(AMensagem) > 160 then
      raise EACBrSMSException.Create('A quantidade m�xima permitida de caracteres por mensagem de texto � de 160 caractes.');

    fsSMS.EnviarSMS(ATelefone, AMensagem, AIndice);
  end
  else
  begin
    AIndice := EmptyStr;

    F := TStringList.Create;
    try
      F.Text := QuebraLinhas(AMensagem, 160);
      for I := 0 to F.Count - 1 do
      begin
        IndiceMsg := '';
        fsSMS.EnviarSMS(ATelefone, F.Strings[I], IndiceMsg);
        AIndice := AIndice + ',' + IndiceMsg;
      end;

      // limpar a virgula inicial
      if AIndice <> EmptyStr then
        AIndice := Copy(AIndice, 2, Length(AIndice));
    finally
      F.Free;
    end;
  end;
end;

procedure TACBrSMS.EnviarSMSLote(const ALote: TACBrSMSMensagens;
  var AIndice: String);
var
  I: Integer;
  IndMsgAtual: String;
  TotalMensagensLote: Integer;
begin
  AIndice := EmptyStr;
  TotalMensagensLote := ALote.Count;

  for I := 0 to TotalMensagensLote - 1 do
  begin
    IndMsgAtual := '' ;
    fsSMS.EnviarSMS(ALote[I].Telefone, ALote[I].Mensagem, IndMsgAtual);
    AIndice := AIndice + ',' + IndMsgAtual;

    {$IFNDEF FRAMEWORK}
    Application.ProcessMessages;
    {$ENDIF}

    // chamar o evento para a aplica��o
    if Assigned(fsOnProgresso) then
      fsOnProgresso(I+1, TotalMensagensLote);
  end;

  // limpar a virgula inicial
  if AIndice <> EmptyStr then
    AIndice := Copy(AIndice, 2, Length(AIndice));
end;

function TACBrSMS.EstadoSincronismo: TACBrSMSSincronismo;
begin
  TestaAtivo;
  Result := fsSMS.EstadoSincronismo;
end;

function TACBrSMS.Fabricante: String;
begin
  TestaAtivo;
  Result := fsSMS.Fabricante;
end;

function TACBrSMS.GetATResult: Boolean;
begin
  Result := fsSMS.ATResult;
end;

function TACBrSMS.GetATTimeOut: Integer;
begin
  Result := fsSMS.ATTimeOut;
end;

function TACBrSMS.GetBandejasSimCard: Integer;
begin
  Result := fsSMS.BandejasSimCard;
end;

function TACBrSMS.GetIntervaloEntreMensagens: Integer;
begin
  Result := fsSMS.IntervaloEntreMensagens;
end;

function TACBrSMS.GetQuebraMensagens: Boolean;
begin
  Result := fsSMS.QuebraMensagens;
end;

function TACBrSMS.GetRecebeConfirmacao: Boolean;
begin
  Result := fsSMS.RecebeConfirmacao;
end;

function TACBrSMS.GetSimCard: TACBrSMSSimCard;
begin
  Result := fsSMS.SimCard;
end;

function TACBrSMS.GetUltimaReposta: String;
begin
  Result := fsSMS.UltimaResposta;
end;

function TACBrSMS.GetUltimoComando: String;
begin
  Result := fsSMS.UltimoComando;
end;

function TACBrSMS.IMEI: String;
begin
  TestaAtivo;
  Result := fsSMS.IMEI;
end;

function TACBrSMS.IMSI: String;
begin
  TestaAtivo;
  Result := fsSMS.IMSI;
end;

procedure TACBrSMS.ListarMensagens(const AFiltro: TACBrSMSFiltro;
  const APath: String);
begin
  TestaAtivo;
  fsSMS.ListarMensagens(AFiltro, APath);
end;

function TACBrSMS.ModeloModem: String;
begin
  TestaAtivo;
  Result := fsSMS.ModeloModem;
end;

function TACBrSMS.NivelSinal: Double;
begin
  TestaAtivo;
  Result := fsSMS.NivelSinal;
end;

function TACBrSMS.Operadora: String;
begin
  TestaAtivo;
  Result := fsSMS.Operadora;
end;

function TACBrSMS.Firmware: String;
begin
  TestaAtivo;
  Result := fsSMS.Firmware;
end;

procedure TACBrSMS.SetModelo(const Value: TACBrSMSModelo);
begin
  if fsModelo = Value then
    Exit;

  if fsAtivo then
    raise EACBrSMSException.Create(ACBrStr('N�o � poss�vel mudar o Modelo com ACBrSMS Ativo'));

  FreeAndNil(fsSMS);
  case Value of
    modDaruma: fsSMS := TACBrSMSDaruma.Create(Self);
    modZTE: fsSMS := TACBrSMSZTE.Create(Self);
  else
    fsSMS := TACBrSMSClass.create(Self);
  end;

  fsModelo := Value;
end;

procedure TACBrSMS.SetQuebraMensagens(const Value: Boolean);
begin
  fsSMS.QuebraMensagens := Value;
end;

procedure TACBrSMS.SetRecebeConfirmacao(const Value: Boolean);
begin
  fsSMS.RecebeConfirmacao := Value;
end;

procedure TACBrSMS.SetAtivo(const Value: Boolean);
begin
  if Value then
    Ativar
  else
    Desativar;
end;

procedure TACBrSMS.SetATResult(const Value: Boolean);
begin
  fsSMS.ATResult := Value;
end;

procedure TACBrSMS.SetATTimeOut(const Value: Integer);
begin
  fsSMS.ATTimeOut := Value;
end;

procedure TACBrSMS.SetIntervaloEntreMensagens(const Value: Integer);
begin
  fsSMS.IntervaloEntreMensagens := Value;
end;

procedure TACBrSMS.Ativar;
begin
  if fsAtivo then
    Exit;

  if fsModelo = modNenhum then
    raise EACBrSMSException.Create(ACBrStr('Modelo n�o definido'));

  fsSMS.Ativar;
  fsAtivo := True;
end;

procedure TACBrSMS.Desativar;
begin
  if not fsAtivo then
    Exit;

  fsSMS.Desativar;
  fsAtivo := False;
end;

end.
