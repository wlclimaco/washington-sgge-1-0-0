{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:    Andreas Schmidt and friends                  }
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
|* 04/10/2008: Primeira Versao
|*    Daniel Simoes de Almeida
******************************************************************************}

{$I ACBr.inc}

unit ACBrBarCode;

interface

uses
 {ACBrBase, }Classes, SysUtils,
 {$IFDEF VisualCLX}
  QGraphics, QControls, QExtCtrls, QDialogs,
 {$ELSE}
  Graphics, Controls, ExtCtrls, Dialogs, 
 {$ENDIF}
  AJBarcode ;

type
  TACBrBarCode = class ( TGraphicControl )
  private
//  fsAbout: TACBrAboutInfo;
    fsBarCode  : TAsBarcode ;
    fsTransparent: Boolean;

    procedure SetTransparent(const Value: Boolean);
    function GetAngle: Double;
    function GetCheckSum: Boolean;
    function GetCheckSumMethod: TCheckSumMethod;
    function GetColor: TColor;
    function GetColorBar: TColor;
    function GetFShowTextPosition: TShowTextPosition;
    function GetModul: Integer;
    function GetRatio: Double;
    function GetShowText: TBarcodeOption;
    function GetShowTextFont: TFont;
    function GetBarCodeText: String;
    function GetTyp: TBarcodeType;
    procedure SetAngle(const Value: Double);
    procedure SetCheckSum(const Value: Boolean);
    procedure SetCheckSumMethod(const Value: TCheckSumMethod);
    procedure SetColor(const Value: TColor);
    procedure SetColorBar(const Value: TColor);
    procedure SetModul(const Value: Integer);
    procedure SetRatio(const Value: Double);
    procedure SetShowText(const Value: TBarcodeOption);
    procedure SetShowTextFont(const Value: TFont);
    procedure SetShowTextPosition(const Value: TShowTextPosition);
    procedure SetBarCodeText(const Value: String);
    procedure SetTyp(const Value: TBarcodeType);
    procedure BarcodeChange(Sender: TObject);

  protected
    procedure Paint; override;
    procedure Resize; override;

  public
    constructor Create(AOwner: TComponent ); override ;
    destructor Destroy ; override ;

    property BarCode : TAsBarcode read fsBarCode ;
    property Canvas ;

    procedure DrawBarcode(Canvas:TCanvas);
    procedure DrawText(Canvas:TCanvas);

  published
    property Text : String  read GetBarCodeText write SetBarCodeText stored False;

   { Width of the smallest line in a Barcode }
    property Modul  : Integer      read GetModul write SetModul;
    property Ratio  : Double       read GetRatio write SetRatio;
    property Typ    : TBarcodeType read GetTyp   write SetTyp
      default bcCode_2_5_interleaved;
   { build CheckSum ? }
    property Checksum       : Boolean read GetCheckSum write SetCheckSum
       default FALSE;
    property CheckSumMethod : TCheckSumMethod read GetCheckSumMethod
       write SetCheckSumMethod default csmModulo10;

   { 0 - 360 degree }
    property Angle : Double read GetAngle write SetAngle;

    property ShowText : TBarcodeOption read GetShowText write SetShowText
       default bcoNone;
    property ShowTextFont: TFont read GetShowTextFont write SetShowTextFont;
    property ShowTextPosition: TShowTextPosition read GetFShowTextPosition
       write SetShowTextPosition default stpTopLeft;
    property Color    : TColor read GetColor    write SetColor    default clWhite;
    property ColorBar : TColor read GetColorBar write SetColorBar default clBlack;

    property Align ;
    property Anchors;
    property Transparent: Boolean read fsTransparent write SetTransparent default False;
    property Constraints;
    property DragMode;
    property Enabled;
    property ParentShowHint;
    property PopupMenu;
    property ShowHint;
    property Visible;
    property OnClick;
    property OnContextPopup;
    property OnDblClick;
    property OnDragDrop;
    property OnDragOver;
    property OnEndDrag;
    property OnMouseDown;
    {$IFDEF VisualCLX}
     property OnMouseEnter;
     property OnMouseLeave;
    {$ENDIF}
    property OnMouseMove;
    property OnMouseUp;
    property OnStartDrag;

//  property AboutACBr : TACBrAboutInfo read fsAbout write fsAbout
//                         stored false ;
  end;


implementation

{ TACBrBarCode }
constructor TACBrBarCode.Create( AOwner: TComponent );
begin
  inherited Create( AOwner );

  fsTransparent:= False;

  // Criando Objeto de Cod.Barras //
  fsBarCode := TAsBarcode.Create( self ) ;

  ControlStyle := ControlStyle - [csAcceptsControls] + [csOpaque];
  Height := 50 ;
  Width  := 200 ;

  fsBarCode.Top    := 0 ;
  fsBarCode.Left   := 0 ;
  fsBarCode.Width  := Self.Width ;
  fsBarCode.Height := Self.Height ;
end;


destructor TACBrBarCode.Destroy;
begin
  fsBarCode.Free ;

  inherited Destroy ;
end;

procedure TACBrBarCode.BarcodeChange(Sender: TObject);
begin
  Paint;
end;

procedure TACBrBarCode.Paint;
begin
  if [csLoading,csDestroying]*ComponentState<>[] then exit;

  fsBarCode.OnChange := nil ;
  try
     if not Transparent then
     begin
        // Apaga o interior do Retangulo //
        Canvas.Brush.Color := Color;
        Canvas.Brush.Style := bsSolid;
        Canvas.FillRect(ClientRect);
     end ;

     fsBarCode.Top    := 0 ;
     fsBarCode.Left   := 0 ;
     fsBarCode.Width  := Self.Width ;
     fsBarCode.Height := Self.Height ;

     fsBarCode.DrawBarcode(Canvas);
  finally
     fsBarCode.OnChange := BarcodeChange ;
  end ;
end;

procedure TACBrBarCode.Resize;
begin
  inherited;
end;

procedure TACBrBarCode.DrawBarcode(Canvas: TCanvas);
begin
  fsBarCode.DrawBarcode(Canvas);
end;

procedure TACBrBarCode.DrawText(Canvas: TCanvas);
begin
  fsBarCode.DrawText(Canvas);
end;

function TACBrBarCode.GetAngle: Double;
begin
  Result := fsBarCode.Angle ;
end;

function TACBrBarCode.GetCheckSum: Boolean;
begin
  Result := fsBarCode.Checksum ;
end;

function TACBrBarCode.GetCheckSumMethod: TCheckSumMethod;
begin
  Result := fsBarCode.CheckSumMethod ;
end;

function TACBrBarCode.GetColor: TColor;
begin
  Result := fsBarCode.Color ;
end;

function TACBrBarCode.GetColorBar: TColor;
begin
  Result := fsBarCode.ColorBar ;
end;

function TACBrBarCode.GetFShowTextPosition: TShowTextPosition;
begin
  Result := fsBarCode.ShowTextPosition ;
end;

function TACBrBarCode.GetModul: Integer;
begin
  Result := fsBarCode.Modul ;
end;

function TACBrBarCode.GetRatio: Double;
begin
  Result := fsBarCode.Ratio ;
end;

function TACBrBarCode.GetShowText: TBarcodeOption;
begin
  Result := fsBarCode.ShowText ;
end;

function TACBrBarCode.GetShowTextFont: TFont;
begin
  Result := fsBarCode.ShowTextFont ;
end;

function TACBrBarCode.GetBarCodeText: String;
begin
  Result := fsBarCode.Text ;
end;

function TACBrBarCode.GetTyp: TBarcodeType;
begin
  Result := fsBarCode.Typ ;
end;

procedure TACBrBarCode.SetAngle(const Value: Double);
begin
  fsBarCode.Angle := Value ;
end;

procedure TACBrBarCode.SetCheckSum(const Value: Boolean);
begin
  fsBarCode.Checksum := Value ;
end;

procedure TACBrBarCode.SetCheckSumMethod(const Value: TCheckSumMethod);
begin
  fsBarCode.CheckSumMethod := Value ;
end;

procedure TACBrBarCode.SetColor(const Value: TColor);
begin
  fsBarCode.Color := Value ;
end;

procedure TACBrBarCode.SetColorBar(const Value: TColor);
begin
  fsBarCode.ColorBar := Value ;
end;

procedure TACBrBarCode.SetModul(const Value: Integer);
begin
  fsBarCode.Modul := Value ;
end;

procedure TACBrBarCode.SetRatio(const Value: Double);
begin
  fsBarCode.Ratio := Value ;
end;

procedure TACBrBarCode.SetShowText(const Value: TBarcodeOption);
begin
  fsBarCode.ShowText := Value ;
end;

procedure TACBrBarCode.SetShowTextFont(const Value: TFont);
begin
  fsBarCode.ShowTextFont := Value ;
end;

procedure TACBrBarCode.SetShowTextPosition(const Value: TShowTextPosition);
begin
  fsBarCode.ShowTextPosition := Value ;
end;

procedure TACBrBarCode.SetBarCodeText(const Value: String);
begin
  fsBarCode.Text := Value ;
end;

procedure TACBrBarCode.SetTransparent(const Value: Boolean);
begin
  if not Value then
     ControlStyle := ControlStyle + [csOpaque]
  else
     ControlStyle := ControlStyle - [csOpaque];

  fsTransparent := Value;
  Paint ;
end;

procedure TACBrBarCode.SetTyp(const Value: TBarcodeType);
begin
  fsBarCode.Typ := Value ;
end;


end.

