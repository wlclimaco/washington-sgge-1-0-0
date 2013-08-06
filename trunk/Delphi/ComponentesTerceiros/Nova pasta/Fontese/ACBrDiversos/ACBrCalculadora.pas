{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:   Alexandre Rocha Lima e Marcondes              }
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
|* 19/05/2004: Primeira Versao
|*    Daniel Simoes de Almeida
|*    Cria�ao e Distribui�ao da Primeira Versao
|* 20/05/2004: Primeira Versao Multi-plataforma
|*    Alexandre Rocha Lima e Marcondes
|*    Compatibilidade entre VCL e VisualCLX
|* 27/05/2004: Revisao e novas Propriedades
|*    Daniel Simoes de Almeida
|*    Adcionada heran�a a TACBrComponente , adcionada prop. CorDisplay,
|*    Adtionado Editor de Componente que exibe a calculdora
|* 03/01/2007:  Nei Jos� Van Lare Junior
|*   - Adicionada a propriedade CorForm : TColor
******************************************************************************}

{$I ACBr.inc}

unit ACBrCalculadora;

interface

uses
 ACBrBase,
 Classes, SysUtils,
 {$IFDEF VisualCLX}
   QControls, QGraphics, QForms
 {$ELSE}
   Controls,  Graphics,  Forms
 {$ENDIF};

type
  TACBrCalculadoraDisplayChange = procedure(Sender: TObject; Valor : Double) of object;

  { TACBrCalculadora }

  TACBrCalculadora = class ( TACBrComponent )
  private
    FBorderStyle : TFormBorderStyle;
    FTitulo: String ;
    FValor : Double ;
    FTexto : String ;
    FPrecisao: Integer;
    FSaiComEsc: Boolean;
    FCalcLeft: Integer;
    FCalcTop: Integer;
    FCor: TColor;
    FCorForm: TColor;
    FCentraliza: Boolean;
    FOnCalcKey: TKeyPressEvent;
    FOnDisplayChange: TACBrCalculadoraDisplayChange;
  public
    constructor Create(AOwner: TComponent); override;
    function Execute: Boolean;
    property Texto  : String read FTexto ;
  published
  { TODO : Adicionar evento OnMudaValor }

     property Valor  : Double read FValor  write FValor stored false ;
     property Titulo : String read FTitulo write FTitulo ;
     property Precisao : Integer read FPrecisao write FPrecisao default 4 ;
     property SaiComEsc : Boolean read FSaiComEsc write FSaiComEsc
                       default true ;
     property Centraliza : Boolean read FCentraliza write FCentraliza
                       default false ;
     property CalcTop  : Integer read FCalcTop write FCalcTop default 0;
     property CalcLeft : Integer read FCalcLeft write FCalcLeft default 0;
     property CorDisplay : TColor read FCor write FCor default clLime ;
     property CorForm : TColor read FCorForm write FCorForm
        default clBtnFace ;
     property OnCalcKey : TKeyPressEvent read FOnCalcKey write FOnCalcKey;
     property OnDisplayChange: TACBrCalculadoraDisplayChange
                      read FOnDisplayChange write FOnDisplayChange;
     property BorderStyle : TFormBorderStyle read FBorderStyle write FBorderStyle ;
  end;

implementation
Uses {$IFDEF VisualCLX}
       QCalculadora
     {$ELSE}
       {$IFDEF FPC}
         LCalculadora
       {$ELSE}
         Calculadora
       {$ENDIF}
     {$ENDIF};

{ TACBrCalculadora }

constructor TACBrCalculadora.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );
  FTitulo     := 'ACBr Calculadora' ;
  FPrecisao   := 4 ;
  FSaiComEsc  := true ;
  FCor        := clLime ;
  FCentraliza := false ;
  FCorForm    := clBtnFace ;
  {$IFDEF VisualCLX}
   FBorderStyle:= fbsDialog;
  {$ELSE}
   FBorderStyle:= bsDialog;
  {$ENDIF}
end;

function TACBrCalculadora.Execute: Boolean;
var
  FrCalculadora : TFrCalculadora ;
begin
  //Result := False;
  FrCalculadora := TFrCalculadora.Create( Application ) ;
  try
     if FCentraliza then
        FrCalculadora.Position := poMainFormCenter
     else
        if (FCalcTop = 0) and (FCalcLeft = 0) then
           FrCalculadora.Position := poDefault
        else
         begin
           FrCalculadora.Top  := FCalcTop  ;
           FrCalculadora.Left := FCalcLeft ;
         end ;

     FrCalculadora.BorderStyle := FBorderStyle ;
     FrCalculadora.Caption := FTitulo ;
     FrCalculadora.Color := FCorForm;
     FrCalculadora.pValor.Font.Color := FCor ;
     FrCalculadora.ValorDisplay := FloatToStr( FValor ) ;
     FrCalculadora.pSaiComEsc := FSaiComEsc ;
     FrCalculadora.pPrecisao := FPrecisao ;
     FrCalculadora.pOnCalKey := FOnCalcKey ;
     FrCalculadora.pOnDisplayChange := FOnDisplayChange ;

     Result := ( FrCalculadora.ShowModal = mrOk ) ;

     FTexto := FrCalculadora.ValorDisplay ;
     FValor := StrToFloat( FTexto ) ;
  finally
     FCalcTop  := FrCalculadora.Top  ;
     FCalcLeft := FrCalculadora.Left ;
     FrCalculadora.Free;
  end;
end;

end.
