{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
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
|* 28/06/2004: Daniel Simoes de Almeida
|*  - Primeira Versao ACBrGAV
|* 23/01/2007: Daniel Simoes de Almeida
|*  - Corrigido Problemas de FreeNotification com a propriedade "ECF"
|* 12/07/2007: Andr� Ferreira de Moraes
|*  - Corrigido Problemas de perda do comando de abertura quando
|*    modelo = gavImpressoraComum e usado ACBrMonitorConsole
******************************************************************************}

{$I ACBr.inc}

unit ACBrGAV;

interface
uses ACBrBase, ACBrDevice, ACBrGAVClass, ACBrECF, {Units da ACBr}
     SysUtils , Classes;

type

TACBrGAVModelo = (gavNenhuma, gavSerialMenno, gavSerialGerbo, gavImpressoraECF,
                 gavImpressoraComum ) ;

{ Componente ACBrGAV }
TACBrGAV = class( TACBrComponent )
  private
    fsDevice  : TACBrDevice ;   { SubComponente ACBrDevice }

    { Propriedades do Componente ACBrGAV }
    fsAtivo  : Boolean;
    fsModelo : TACBrGAVModelo;
    fsGAV    : TACBrGAVClass ;   { Classe com instancia da Gaveta de fsModelo }
    fsECF    : TACBrECF;

    procedure SetModelo(const Value: TACBrGAVModelo);
    procedure SetPorta(const Value: String);
    procedure SetAtivo(const Value: Boolean);

    function GetPorta: String;
    function GetGavetaAbertaClass: Boolean;
    function GetModeloStrClass: String;
    function GetComandoClass: String;
    procedure SetComandoClass(const Value: String);
    function GetAberturaIntervaloClass: Integer;
    procedure SetAberturaIntervaloClass(const Value: Integer);
    function GetAberturaAntecipadaClass: TACBrGAVAberturaAntecipada;
    procedure SetAberturaAntecipadaClass(
      const Value: TACBrGAVAberturaAntecipada);
    procedure SetECF(const Value: TACBrECF);

    procedure SetCmdGavetaECF ;
  protected
    procedure Notification(AComponent: TComponent; Operation: TOperation); override;

  public
    constructor Create(AOwner: TComponent); override;
    Destructor Destroy  ; override ;

    procedure Ativar ;
    procedure Desativar ;
    property Ativo : Boolean read fsAtivo write SetAtivo ;
    
    property GAV : TACBrGAVClass read fsGAV ;

    Property ModeloStr : String read GetModeloStrClass;

    Procedure AbreGaveta ;
    Property GavetaAberta : Boolean read GetGavetaAbertaClass ;

  published
     property Modelo : TACBrGAVModelo read fsModelo write SetModelo
                 default gavNenhuma ;
     property Porta : String read GetPorta write SetPorta ;

     { Instancia do Componente ACBrDevice, ser� passada para fsGAV.create }
     property Device : TACBrDevice read fsDevice ;

     property ECF    : TACBrECF    read fsECF write SetECF ;
     property StrComando : String  read GetComandoClass write SetComandoClass ;
     property AberturaIntervalo : Integer read GetAberturaIntervaloClass
        write SetAberturaIntervaloClass default cAberturaIntervalo ; 
     Property AberturaAntecipada : TACBrGAVAberturaAntecipada
        read GetAberturaAntecipadaClass  write SetAberturaAntecipadaClass
        default aaAguardar ;
end ;

implementation
Uses ACBrUtil, ACBrGAVSerialMenno, ACBrGAVSerialGerbo, ACBrGAVImpressoraECF,
     ACBrGAVImpressoraComum, ACBrECFNaoFiscal ;

{ TACBrGAV }

constructor TACBrGAV.Create(AOwner: TComponent);
begin
  inherited create( AOwner );

  fsAtivo  := false ;
  fsModelo := gavNenhuma ;
  
  { Instanciando SubComponente TACBrDevice }
  fsDevice := TACBrDevice.Create( self ) ;  { O dono � o proprio componente }
  fsDevice.Name := 'ACBrDevice' ;      { Apenas para aparecer no Object Inspector}
  {$IFDEF COMPILER6_UP}
  fsDevice.SetSubComponent( true );{ para gravar no DFM/XFM }
  {$ENDIF}
  fsDevice.Porta := 'COM1';
  fsDevice.Serial.DeadlockTimeout := 1000 ;

  { Instanciando fsGAV com modelo Generico (GAVClass) }
  fsGAV := TACBrGAVClass.create( self ) ;
end;

destructor TACBrGAV.Destroy;
begin
  Ativo := false ;
  
  if Assigned( fsGAV ) then
     FreeAndNil( fsGAV ) ;

  FreeAndNil( fsDevice ) ;

  inherited Destroy;
end;

procedure TACBrGAV.SetModelo(const Value: TACBrGAVModelo);
Var wStrComando : String ;
    wAberturaAntecipada : TACBrGAVAberturaAntecipada ;
begin
  if fsModelo = Value then exit ;

  if fsAtivo then
     raise Exception.Create(ACBrStr('N�o � poss�vel mudar o Modelo com ACBrGAV Ativo'));

  wStrComando         := StrComando ;
  wAberturaAntecipada := AberturaAntecipada ;

  FreeAndNil( fsGAV ) ;

  { Instanciando uma nova classe de acordo com fsModelo }
  case Value of
     gavSerialMenno     : fsGAV := TACBrGAVSerialMenno.create( Self ) ;
     gavSerialGerbo     : fsGAV := TACBrGAVSerialGerbo.create( Self ) ;
     gavImpressoraECF   : fsGAV := TACBrGAVImpressoraECF.create( Self ) ;
     gavImpressoraComum : fsGAV := TACBrGAVImpressoraComum.create( Self ) ;
  else
     fsGAV := TACBrGAVClass.create( Self ) ;
  end;

  StrComando         := wStrComando ;
  AberturaAntecipada := wAberturaAntecipada ;

  fsModelo := Value;

  if Value <> gavImpressoraECF then
     ECF := nil ;

  if Value in [gavImpressoraECF] then
     Porta := '' ;

  if Value <> gavImpressoraComum then
     StrComando := '' ;

  SetCmdGavetaECF ;
end;

procedure TACBrGAV.SetAtivo(const Value: Boolean);
begin
  if Value then
     Ativar
  else
     Desativar ;
end;

procedure TACBrGAV.Ativar;
begin
  if fsAtivo then exit ;

  if fsModelo = gavNenhuma then
     raise Exception.Create(ACBrStr('Modelo n�o definido'));

  fsGAV.Ativar ;

  SetCmdGavetaECF ;
  fsAtivo := true ;
end;

procedure TACBrGAV.Desativar;
begin
  if not fsAtivo then exit ;

  fsGAV.Desativar ;
  fsAtivo := false;
end;

procedure TACBrGAV.AbreGaveta;
Var wAtivo : Boolean ;
begin
  wAtivo := Ativo ;
  try
     Ativo  := true ;
     fsGAV.AbreGaveta ;
  finally
     Ativo := wAtivo ;
  end ;
end;

function TACBrGAV.GetGavetaAbertaClass: Boolean;
Var wAtivo : Boolean ;
begin
  wAtivo := Ativo ;
  try
     Ativo  := true ;
     Result := fsGAV.GavetaAberta ;
  finally
     Ativo := wAtivo ;
  end ;
end;

function TACBrGAV.GetModeloStrClass: String;
begin
  Result := ACBrStr(fsGAV.ModeloStr);
end;

function TACBrGAV.GetPorta: String;
begin
  result := fsDevice.Porta ;
end;

procedure TACBrGAV.SetPorta(const Value: String);
begin
  if Modelo in [gavImpressoraECF] then
     fsDevice.Porta := ''
  else
     fsDevice.Porta := Value ;
end;

function TACBrGAV.GetComandoClass: String;
begin
  result := fsGAV.StrComando ;
end;

procedure TACBrGAV.SetComandoClass(const Value: String);
begin
  if fsAtivo then
     raise Exception.Create(ACBrStr('N�o � poss�vel mudar StrComando com ACBrGAV Ativo'));

  fsGAV.StrComando := Value ;

  SetCmdGavetaECF ;
end;

function TACBrGAV.GetAberturaIntervaloClass: Integer;
begin
  result := fsGAV.AberturaIntervalo ;
end;

procedure TACBrGAV.SetAberturaIntervaloClass(const Value: Integer);
begin
  fsGAV.AberturaIntervalo := Value ;
end;

function TACBrGAV.GetAberturaAntecipadaClass: TACBrGAVAberturaAntecipada;
begin
  result := fsGAV.AberturaAntecipada ;
end;

procedure TACBrGAV.SetAberturaAntecipadaClass(
  const Value: TACBrGAVAberturaAntecipada);
begin
  fsGAV.AberturaAntecipada := Value ;
end;

procedure TACBrGAV.SetCmdGavetaECF;
begin
  if Assigned( fsECF ) then
     if fsECF.ECF is TACBrECFNaoFiscal then
        TACBrECFNaoFiscal(fsECF.ECF).GavetaCmd := StrComando ;
end;

procedure TACBrGAV.SetECF(const Value: TACBrECF);
begin
  if Value <> fsECF then
  begin
     if Assigned(fsECF) then
        fsECF.RemoveFreeNotification(Self);

     fsECF     := Value;
     fsGAV.ECF := Value ;

     if fsECF is TACBrECF then
        Modelo := gavImpressoraECF ;

     if Value <> nil then
        Value.FreeNotification(self);
  end ;
end;

procedure TACBrGAV.Notification(AComponent: TComponent;
  Operation: TOperation);
begin
  inherited Notification(AComponent, Operation);

  if (Operation = opRemove) and (fsGAV <> nil) and (AComponent is TACBrECF) then
  begin
     fsECF := nil ;

     if (fsGAV.ECF <> nil) then
        fsGAV.ECF := nil ;
  end ;
end;

end.
