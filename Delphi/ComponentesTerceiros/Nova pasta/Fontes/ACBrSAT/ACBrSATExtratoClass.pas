{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para interação com equipa- }
{ mentos de Automação Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Você pode obter a última versão desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
{                                                                              }
{  Esta biblioteca é software livre; você pode redistribuí-la e/ou modificá-la }
{ sob os termos da Licença Pública Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a versão 2.1 da Licença, ou (a seu critério) }
{ qualquer versão posterior.                                                   }
{                                                                              }
{  Esta biblioteca é distribuída na expectativa de que seja útil, porém, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia implícita de COMERCIABILIDADE OU      }
{ ADEQUAÇÃO A UMA FINALIDADE ESPECÍFICA. Consulte a Licença Pública Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICENÇA.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Você deve ter recebido uma cópia da Licença Pública Geral Menor do GNU junto}
{ com esta biblioteca; se não, escreva para a Free Software Foundation, Inc.,  }
{ no endereço 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Você também pode obter uma copia da licença em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Simões de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Praça Anita Costa, 34 - Tatuí - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 04/04/2013:  André Ferreira de Moraes
|*   Inicio do desenvolvimento
******************************************************************************}

unit ACBrSATExtratoClass;

interface

uses SysUtils,
     Classes,
     ACBrDevice,
     pcnCFe, pcnCFeCanc;

type

  TCasasDecimais = class(TComponent)
  private
    FqCom: integer;
    FvUnCom: integer;
    FMask_qCom:String;
    FMask_vUnCom:String;

    procedure Set_qCom(AValue: integer);
    procedure Set_vUnCom(AValue: integer);
  public
    constructor Create(AOwner: TComponent); override ;
    destructor Destroy; override;
  published
    property _qCom: Integer read FQCom write Set_qCom;
    property _vUnCom: Integer read FvUnCom write Set_vUnCom;
    property _Mask_qCom: String read FMask_qCom write FMask_qCom;
    property _Mask_vUnCom: String read FMask_vUnCom write FMask_vUnCom;
  end;

  { TACBrSATExtratoClass }

  TACBrSATExtratoClass = class( TComponent )
  private
    FACBrSAT : TComponent;
    FCasasDecimais: TCasasDecimais;

    procedure ErroAbstract(NomeProcedure : String) ;
    procedure SetSAT(const Value: TComponent);
  protected
    FpImprimeQRCode: Boolean;
    FpCFe: TCFe;
    FpCFeCanc: TCFeCanc;

    procedure Notification(AComponent: TComponent; Operation: TOperation); override;
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure ImprimirExtrato(CFe : TCFe = nil); virtual;
    procedure ImprimirExtratoResumido(CFe : TCFe = nil); virtual;
    procedure ImprimirExtratoCancelamento(CFe : TCFe = nil; CFeCanc: TCFeCanc = nil); virtual;
  published
    property ACBrSAT : TComponent  read FACBrSAT write SetSAT ;
    property CasasDecimais: TCasasDecimais read FCasasDecimais ;
    property ImprimeQRCode: Boolean read FpImprimeQRCode write FpImprimeQRCode default True ;
  end ;

implementation

uses ACBrSAT, ACBrSATClass;

{ TCasasDecimais }

constructor TCasasDecimais.Create(AOwner: TComponent);
begin
  inherited create( AOwner );

  FQCom := 2;
  FvUnCom := 2;
end;

destructor TCasasDecimais.Destroy;
begin

  inherited Destroy ;
end;

procedure TCasasDecimais.Set_qCom(AValue: integer);
begin
  if ((AValue >= 0) and
      (AValue <= 4))  then
    FqCom := AValue
  else
    FqCom := 2;
end;

procedure TCasasDecimais.Set_vUnCom(AValue: integer);
begin
  if ((AValue >= 0) and
      (AValue <= 3))  then
    FvUnCom := AValue
  else
    FvUnCom := 2;
end;

{ TACBrSATExtratoClass }

constructor TACBrSATExtratoClass.Create(AOwner: TComponent);
begin
  inherited create( AOwner );

  FACBrSAT      := nil ;
  FCasasDecimais := TCasasDecimais.Create(self);
  FCasasDecimais.Name:= 'CasasDecimais' ;
  {$IFDEF COMPILER6_UP}
      FCasasDecimais.SetSubComponent( true );{ para gravar no DFM/XFM }
  {$ENDIF}
end;

destructor TACBrSATExtratoClass.Destroy;
begin

  inherited Destroy ;
end;

procedure TACBrSATExtratoClass.ImprimirExtrato(CFe: TCFe);
begin
  ErroAbstract('ImprimirExtrato' ) ;
end;

procedure TACBrSATExtratoClass.ImprimirExtratoCancelamento(CFe: TCFe; CFeCanc: TCFeCanc);
begin
  ErroAbstract('ImprimirExtratoCancelamento' ) ;
end;

procedure TACBrSATExtratoClass.ImprimirExtratoResumido(CFe: TCFe);
begin
  ErroAbstract('ImprimirExtratoResumido' ) ;
end;

procedure TACBrSATExtratoClass.Notification(AComponent: TComponent;
  Operation: TOperation);
begin
  inherited Notification(AComponent, Operation);

  if (Operation = opRemove) and (FACBrSAT <> nil) and (AComponent is TACBrSAT) then
     FACBrSAT := nil ;
end;

procedure TACBrSATExtratoClass.ErroAbstract(NomeProcedure : String) ;
begin
  raise EACBrSATErro.create( Format( 'Procedure: %s '+ sLineBreak +
                                     ' não implementada para o Extrato: %s' ,
                                     [NomeProcedure, ClassName] )) ;
end ;

procedure TACBrSATExtratoClass.SetSAT(const Value: TComponent);
var
  OldValue : TACBrSAT ;
begin
  if Value <> FACBrSAT then
  begin
     if Value <> nil then
        if not (Value is TACBrSAT) then
           raise Exception.Create('ACBrSATExtrato.SAT deve ser do tipo TACBrSAT') ;

     if Assigned(FACBrSAT) then
        FACBrSAT.RemoveFreeNotification(Self);

     OldValue := TACBrSAT(FACBrSAT) ;   // Usa outra variavel para evitar Loop Infinito
     FACBrSAT := Value;                 // na remoção da associação dos componentes

     if Assigned(OldValue) then
        if Assigned(OldValue.Extrato) then
           OldValue.Extrato := nil ;

     if Value <> nil then
     begin
        Value.FreeNotification(self);
        TACBrSAT(Value).Extrato := self ;
     end ;
  end ;
end;

end.
