{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Fabio Farias                           }
{                                       Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
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

{******************************************************************************
|* Historico
|*
|* 04/10/2005: Fabio Farias  / Daniel Sim�es de Almeida
|*  - Primeira Versao ACBrBAL
******************************************************************************}

{$I ACBr.inc}

unit ACBrBAL;

interface
uses ACBrDevice, ACBrBase, ACBrBALClass,  {Units da ACBr}
     SysUtils
     {$IFNDEF NOGUI}
       {$IFDEF VisualCLX}, QExtCtrls {$ELSE}, ExtCtrls {$ENDIF}
     {$ENDIF}
     {$IFDEF COMPILER6_UP}, Types {$ELSE}, Windows {$ENDIF}
     , Classes;

type

TACBrBALModelo = (balNenhum, balFilizola, balToledo, balToledo2180, balUrano, balLucasTec, balMagna, balDigitron,balMagellan ) ;
TACBrBALLePeso = procedure(Peso : Double; Resposta : AnsiString) of object ;

{ Componente ACBrBAL }
TACBrBAL = class( TACBrComponent )
  private
    fsDevice  : TACBrDevice ;   { SubComponente ACBrDevice }
    {$IFNDEF NOGUI}
      fsTimer: TTimer;
    {$ELSE}
      fsTimer: TACBrThreadTimer;
    {$ENDIF}

    { Propriedades do Componente ACBrBAL }
    fsAtivo  : Boolean;
    fsModelo : TACBrBALModelo;
    fsBAL    : TACBrBALClass ;
    fsOnLePeso: TACBrBALLePeso;
    fsMonitorarBalanca: Boolean;
    fsIntervalo: Integer;

    procedure SetModelo(const Value: TACBrBALModelo);
    procedure SetPorta(const Value: String);
    procedure SetAtivo(const Value: Boolean);
    procedure LeSerial(Sender: TObject); virtual ;

    function GetPorta: String;
    function GetModeloStrClass: String;
    function GetUltimoPesoLido: Double;
    procedure SetMonitorarBalanca(const Value: Boolean);
    procedure SetIntervalo(const Value: Integer);
    function GetUltimaResposta: AnsiString;
  protected

  public
    constructor Create(AOwner: TComponent); override;
    Destructor Destroy  ; override ;

    procedure Ativar ;
    procedure Desativar ;

    function LePeso( MillisecTimeOut : Integer = 3000) :Double;

    property UltimoPesoLido : Double read GetUltimoPesoLido ;
    property UltimaResposta : AnsiString read GetUltimaResposta ;
    property Ativo : Boolean read fsAtivo write SetAtivo ;
    property BAL : TACBrBALClass read fsBAL;
    property ModeloStr : String read GetModeloStrClass;
  published
     property Modelo : TACBrBALModelo read fsModelo write SetModelo
                 default balNenhum ;
     property Porta : String read GetPorta write SetPorta ;
     property Intervalo  : Integer      read fsIntervalo
        write SetIntervalo default 200 ;
     property MonitorarBalanca : Boolean read fsMonitorarBalanca
        write SetMonitorarBalanca default False ;
     { Instancia do Componente ACBrDevice, ser� passada para fsBAL.create }
     property Device : TACBrDevice read fsDevice ;
     property OnLePeso : TACBrBALLePeso read fsOnLePeso write fsOnLePeso;
  end ;

implementation
Uses ACBrUtil, ACBrBALFilizola, ACBrBALToledo, ACBrBALUrano,
     ACBrBALLucasTec,  ACBrBALToledo2180, ACBrBALMagna, ACBrBALDigitron,ACBrBALMagellan,
     {$IFDEF COMPILER6_UP} StrUtils {$ELSE} ACBrD5{$ENDIF},
     Math;

{ TACBrBAL }
constructor TACBrBAL.Create(AOwner: TComponent);
begin
  inherited create( AOwner );

  fsAtivo       := false ;
  fsModelo      := balNenhum ;
  fsIntervalo   := 200 ;

  { Instanciando SubComponente TACBrDevice }
  fsDevice := TACBrDevice.Create( self ) ;  { O dono � o proprio componente }
  fsDevice.Name := 'ACBrDevice' ;      { Apenas para aparecer no Object Inspector}
  {$IFDEF COMPILER6_UP}
  fsDevice.SetSubComponent( true );{ para gravar no DFM/XFM }
  {$ENDIF}
  fsDevice.Porta := 'COM1';
  fsDevice.Serial.DeadlockTimeout := 1000 ;

  { Timer para monitorar o envio de dados pela Balan�a }
  {$IFNDEF NOGUI}
    fsTimer := TTimer.Create(self) ;
  {$ELSE}
    fsTimer := TACBrThreadTimer.Create ;
  {$ENDIF}
  fsTimer.Enabled := False ;
  fsTimer.OnTimer := LeSerial ;

  { Instanciando fsBAL com modelo Generico (TACBrBALClass) }
  fsBAL := TACBrBALClass.create( self ) ;
end;

destructor TACBrBAL.Destroy;
begin
  Desativar ;

  fsTimer.Enabled := False ;
  fsTimer.Free ;

  if Assigned( fsBAL ) then
     FreeAndNil( fsBAL ) ;

  FreeAndNil( fsDevice ) ;

  inherited Destroy;
end;

procedure TACBrBAL.SetModelo(const Value: TACBrBALModelo);
begin
  if fsModelo = Value then exit ;

  if fsAtivo then
     raise Exception.Create(ACBrStr('N�o � poss�vel mudar o Modelo com ACBrBAL Ativo') );

  FreeAndNil( fsBAL ) ;

  { Instanciando uma nova classe de acordo com fsModelo }
  case Value of
     balFilizola    : fsBAL := TACBrBALFilizola.create( Self ) ;
     balToledo      : fsBAL := TACBrBALToledo.Create( Self );
     balToledo2180  : fsBAL := TACBrBALToledo2180.Create( Self );
     balUrano       : fsBAL := TACBrBALUrano.Create( Self );
     balLucasTec    : fsBAL := TACBrBALLucasTec.Create( Self );
     balMagna       : fsBAL := TACBrBALMagna.Create(Self);
     balDigitron    : fsBAL := TACBrBALDigitron.Create(Self);
     balMagellan    : fsBAL := TACBrBALMagellan.Create(Self);
  else
     fsBAL := TACBrBALClass.create( Self ) ;
  end;

  fsModelo := Value;
end;

procedure TACBrBAL.SetAtivo(const Value: Boolean);
begin
  if Value then
     Ativar
  else
     Desativar ;
end;

procedure TACBrBAL.Ativar;
begin
  if fsAtivo then exit ;

  fsBAL.Ativar ;
  fsAtivo   := true ;
  Intervalo := fsIntervalo ; { isso apenas verifica se precisa ligar o timer }
end;

procedure TACBrBAL.Desativar;
begin
  if not fsAtivo then exit ;

  fsTimer.Enabled := False ;
  fsBAL.Desativar ;
  fsAtivo := false;
end;


function TACBrBAL.GetModeloStrClass: String;
begin
  Result := ACBrStr(fsBAL.ModeloStr) ;
end;

function TACBrBAL.GetPorta: String;
begin
  result := fsDevice.Porta ;
end;

procedure TACBrBAL.SetPorta(const Value: String);
begin
  fsDevice.Porta := Value ;
end;

function TACBrBAL.LePeso( MillisecTimeOut : Integer) : Double;
Var Ativado, Monitorando : Boolean ;
begin
  Ativado     := Ativo ;
  Monitorando := MonitorarBalanca ;

  try
     Monitorando := False ;
     
     if not Ativado then   { Ativa caso n�o tenha sido ativado antes }
        Ativar ;

     Result := fsBAL.LePeso( MillisecTimeOut ) ;

     if Assigned( fsOnLePeso ) then
        fsOnLePeso( UltimoPesoLido, UltimaResposta ) ;
  finally
     Ativo            := Ativado ;
     MonitorarBalanca := Monitorando ; 
  end ;
end;

procedure TACBrBAL.LeSerial(Sender: TObject);  { Chamado pelo Timer interno }
begin
  fsTimer.Enabled := False ;  { Desliga o Timer para evitar chamadas Recursivas }

  { Est� ativo ? Tem dados esperando na porta Serial ? }
  if fsDevice.Serial.InstanceActive then
  begin
     if (fsDevice.Serial.WaitingDataEx > 0) then
     begin
        fsBAL.LeSerial( 500 ) ;
        if Assigned( fsOnLePeso ) then
           fsOnLePeso( UltimoPesoLido, UltimaResposta ) ;
     end ;
  end ;
  
  fsTimer.Enabled := True ;
end;

function TACBrBAL.GetUltimoPesoLido: Double;
begin
  Result := fsBAL.UltimoPesoLido ;
end;

function TACBrBAL.GetUltimaResposta: AnsiString;
begin
  Result := fsBAL.UltimaResposta ;
end;

procedure TACBrBAL.SetMonitorarBalanca(const Value: Boolean);
begin
  fsMonitorarBalanca := Value;
  Intervalo := fsIntervalo ; { isso apenas verifica se precisa ligar o timer }
end;

procedure TACBrBAL.SetIntervalo(const Value: Integer);
begin
  fsTimer.Interval := Value ;
  fsIntervalo      := fsTimer.Interval ;
  fsTimer.Enabled  := fsMonitorarBalanca and fsAtivo and (fsIntervalo > 0) ;
end;

end.


