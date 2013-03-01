unit XPInformationPanel;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  ExtCtrls, StdCtrls;

type
  TXPInformationPanel = class(TPanel)
  private
    { Private declarations }
    FTimeAnimation: Integer;
    FOldHeight: Integer;
    FOnButtonClick: TNotifyEvent;
    FHeader: TPanel;
    FHeaderCaption: TLabel;
    FIcon, FGradient, FCurveLeft, FCurveRight, FBackgroundImage: TImage;
    FHeaderEndColor, FBackground, FColorBorder: TColor;
    FExpanded: Boolean;
    FBorder: Boolean;
    FLabel: TLabel;
    FText: String;
    function GetIcon: TBitmap;
    procedure SetIcon(const Value:TBitmap);
    function GetHeaderVisibility: Boolean;
    procedure SetHeaderVisibility(const Value: Boolean);
    function GetHeaderCaption: String;
    procedure SetHeaderCaption(const Value: String);
    function GetHeaderColor: TColor;
    procedure SetHeaderColor(const Value: TColor);
    function GetHeaderEndColor: TColor;
    procedure SetHeaderEndColor(const Value: TColor);
    function GetHeaderFont: TFont;
    procedure SetHeaderFont(const Value: TFont);
    function GetHeaderHeight: Integer;
    procedure SetHeaderHeight(const Value: Integer);
    function GetBackgroundImage: TBitmap;
    procedure SetBackgroundImage(const Value: TBitmap);
    function GetBackTransparent: Boolean;
    procedure SetBackTransparent(const Value: Boolean);
    function GetBackCenter: Boolean;
    procedure SetBackCenter(const Value: Boolean);
    function GetBackStretch: Boolean;
    procedure SetBackStretch(const Value: Boolean);
    function GiveColor(BeginColor, EndColor: TColor; Count, Position: Integer): TColor;
    procedure SetExpanded(const Value: Boolean);
    procedure Expanding;
    procedure UnExpanding;
    procedure SetBorder(const Value: Boolean);
    procedure SetColorBorder(const Value: TColor);
    procedure SetText(const Value: String);
  protected
    { Protected declarations }
    procedure ButtonClick (Sender: TObject); virtual;
    procedure FHExpanding(FH: Integer);
    procedure FHUnExpanding(FH: Integer);
  public
    { Public declarations }
    procedure Paint; override;
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
  published
    { Published declarations }
    property OnButtonClick: TNotifyEvent read FOnButtonClick write FOnButtonClick;
    property HeaderBitmap: TBitmap read GetIcon write SetIcon;
    property HeaderVisible: Boolean read GetHeaderVisibility write SetHeaderVisibility;
    property HeaderCaption: String read GetHeaderCaption write SetHeaderCaption;
    property HeaderStartColor: TColor read GetHeaderColor write SetHeaderColor;
    property HeaderEndColor: TColor read GetHeaderEndColor write SetHeaderEndColor;
    property HeaderFont: TFont read GetHeaderFont write SetHeaderFont;
    property HeaderHeight: Integer read GetHeaderHeight write SetHeaderHeight;
    property HeaderBackgroundColor: TColor read FBackground write FBackground;
    property BackgroundBitmap: TBitmap read GetBackgroundImage write SetBackgroundImage;
    property BackgroundBitmapTransparent: Boolean read GetBackTransparent write SetBackTransparent;
    property BackgroundBitmapCenter: Boolean read GetBackCenter write SetBackCenter;
    property BackgroundBitmapStretch: Boolean read GetBackStretch write SetBackStretch;
    property Expanded: Boolean read FExpanded write SetExpanded;
    property ColorBorder: TColor read FColorBorder write SetColorBorder;
    property Border: Boolean read FBorder write SetBorder;
    property SetHeight: Integer read FOldHeight write FOldHeight;
    property Text: String read FText write SetText;
  end;

procedure Register;

implementation

{$R XPInformationPanel.res}

procedure Register;
begin
  RegisterComponents('Active Controls', [TXPInformationPanel]);
end;

constructor TXPInformationPanel.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  Align := alNone;
  BorderWidth := 0;
  Font.Name := 'Tahoma';
  ParentColor := False;
  Color := $00F8E4D8;
  BevelOuter := bvNone;
  Width := 223;
  Height := 151;
  Font.Color := clWhite;
  FExpanded := True;
  FOldHeight := Height;
  FTimeAnimation := 1;
  FColorBorder := clWhite;
  FBorder := True;
  Screen.Cursors[crHandPoint] := LoadCursor(HInstance, 'XPHAND');
  DoubleBuffered := True;

  FHeader := TPanel.Create(Self);
  with FHeader do
  begin
    Parent := Self;
    Visible := True;
    BorderWidth := 0;
    BevelOuter := BvNone;
    ParentColor := True;
    Color := ClWhite;
    ParentColor := False;
    Alignment := TaLeftJustify;
    Font.Color := ClWhite;
    Font.Style := [FsBold];
    Caption := '';
    Height := 24;
    SetBounds(0, 0, Width, Height);
    Align := alTop;
    Cursor := crHandPoint;
    OnClick := ButtonClick;
  end;

  FCurveLeft := TImage.Create(Self);
  with FCurveLeft do
  begin
    Parent := FHeader;
    Width := 3;
    Visible := True;
    Transparent := False;
    SetBounds(0, 0, 3, Height);
    Align := alLeft;
  end;

  FCurveRight := TImage.Create(Self);
  with FCurveRight do
  begin
    Parent := FHeader;
    Width := 3;
    Visible := True;
    Transparent:= False;
    SetBounds(FHeader.Width, 0, 3, Height);
    Align := alRight;
  end;

  FIcon := TImage.Create(Self);
  with FIcon do
  begin
    Parent := FHeader;
    Autosize := True;
    Visible := True;
    Center := True;
    Transparent := True;
    SetBounds(0, 0, 16, Height);
    Align := alLeft;
    Cursor := crHandPoint;
    FIcon.Picture.Bitmap.Handle := LoadBitmap(HInstance, 'XPFOLD');
    FIcon.OnClick := ButtonClick;
  end;

  FGradient := TImage.Create(Self);
  with FGradient do
  begin
    Parent := FHeader;
    Width := 110;
    Visible := True;
    Transparent := False;
    SetBounds(0, 0, Width, Height);
    Align := alRight;
    OnClick := ButtonClick;
  end;

  FHeaderCaption := TLabel.Create(Self);
  with FHeaderCaption do
  begin
    Parent := FHeader;
    Visible := True;
    Caption := 'Título';
    Transparent := True;
    SetBounds(FIcon.Width + 5, 5, Width, Height);
    OnClick := ButtonClick;
  end;

  FBackgroundImage := TImage.Create(Self);
  with FBackgroundImage do
  begin
    Parent := Self;
    Visible := True;
    Transparent := True;
    SetBounds(FHeader.Width, FHeader.Height, Width, Height);
    Align := alClient;
  end;

  FLabel := TLabel.Create(Self);
  with FLabel do
  begin
    Parent := Self;
    Transparent := True;
    AutoSize := False;
    WordWrap := True;
    Align := alNone;
    Top := 30;
    Left := 10;
    Width := Self.Width - 20;
    Height := Self.Height - 40;
  end;

  Font.Color := $00C05C20;
  FHeaderCaption.Font.Color := $00C05C20;
  HeaderEndColor := $00F1CCB4;
  HeaderBackgroundColor := clBtnFace; // $00EFA27B;
  FBackgroundImage.Transparent := False;
end;

destructor TXPInformationPanel.Destroy;
begin
  FCurveRight.Free;
  FCurveLeft.Free;
  FIcon.Free;
  FGradient.Free;
  FHeaderCaption.Free;
  FBackgroundImage.Free;
  FHeader.Free;
  FLabel.Free;
  inherited;
end;

function TXPInformationPanel.GiveColor(BeginColor, EndColor: TColor; Count, Position: Integer): TColor;
var
  BeginRGBValue: array[0..2] of Byte; { Begin RGB values }
  RGBDifference: array[0..2] of Integer; { Difference between begin and end RGB values }
  I: Integer;
  R, G, B: Byte; { Color band Red, Green, Blue values }
begin
  R := 0;
  G := 0;
  B := 0;

  if Position = Count then
  begin
    Result := EndColor; // protects last block of progress bar from being draw in wrong color
    Exit;
  end;

  BeginColor := ColorToRGB(BeginColor);
  EndColor := ColorToRGB(EndColor);

  { Set the Red, Green and Blue colors }
  BeginRGBValue[0] := GetRValue(BeginColor);
  BeginRGBValue[1] := GetGValue(BeginColor);
  BeginRGBValue[2] := GetBValue(BeginColor);

  { Calculate the difference between begin and end RGB values }
  RGBDifference[0] := GetRValue(EndColor) - BeginRGBValue[0];
  RGBDifference[1] := GetGValue(EndColor) - BeginRGBValue[1];
  RGBDifference[2] := GetBValue(EndColor) - BeginRGBValue[2];

  for I := 0 to Position do
  begin
    { Calculate the color band's color }
    R := BeginRGBValue[0] + MulDiv(I, RGBDifference[0], Count - 1);
    G := BeginRGBValue[1] + MulDiv(I, RGBDifference[1], Count - 1);
    B := BeginRGBValue[2] + MulDiv(I, RGBDifference[2], Count - 1);
  end;
  Result := RGB(R, G, B);
end;

procedure TXPInformationPanel.Paint;
var
   i, j: Integer;
begin
  inherited;
  Parent.Repaint;

  FLabel.Width := Self.Width - 20;
  FLabel.Height := Self.Height - 40;

  if FBorder then
  begin
    Canvas.Pen.Color := FColorBorder;
    Canvas.Rectangle(ClientRect);
  end;

  for i := 0 to FGradient.Width do
  begin
  with FGradient.Canvas do
  begin
   Pen.Color := GiveColor(FHeader.color, FHeaderEndColor, FGradient.width, I);
   Pen.Width := 1;
   MoveTo(I, 0);
   LineTo(I, FGradient.Height);
  end;
  end;

  with FCurveLeft.Canvas do
  begin
    for i:=0 to FCurveLeft.Width do
      for j:=0 to FCurveLeft.Height do
        Pixels[i, j] := FHeader.Color;

    Pixels[0,0] := FBackground;
    Pixels[1,0] := FBackground;
    Pixels[2,0] := FBackground;
    Pixels[0,1] := FBackground;
    Pixels[0,2] := FBackground;
  end;

  with FCurveRight.Canvas do
  begin
    for i := 0 to FCurveLeft.Width do
      for j := 0 to FCurveLeft.Height do
        Pixels[i, j] := FHeaderEndColor;

    Pixels[0,0] := FBackground;
    Pixels[1,0] := FBackground;
    Pixels[2,0] := FBackground;
    Pixels[2,1] := FBackground;
    Pixels[2,2] := FBackground;
  end;
end;

function TXPInformationPanel.GetIcon: TBitmap;
begin
  Result := FIcon.Picture.Bitmap;
end;

procedure TXPInformationPanel.SetIcon(const Value:TBitmap);
begin
  FIcon.Picture.Bitmap := Value;
  FHeaderCaption.Left := FIcon.Width + 5;
  Repaint;
end;

function TXPInformationPanel.GetHeaderVisibility: Boolean;
begin
  Result := FHeader.Visible;
end;

procedure TXPInformationPanel.SetHeaderVisibility(const Value: Boolean);
begin
  FHeader.Visible := Value;
end;

function TXPInformationPanel.GetHeaderCaption: String;
begin
  Result := FHeaderCaption.Caption;
end;

procedure TXPInformationPanel.SetHeaderCaption(const Value: String);
begin
  FHeaderCaption.Caption := Value;
  FHeaderCaption.Left := FIcon.Width + 5;
end;

function TXPInformationPanel.GetHeaderColor: TColor;
begin
  Result := FHeader.Color;
end;

procedure TXPInformationPanel.SetHeaderColor(const Value: TColor);
begin
  FHeader.Color := Value;
  Repaint;
end;

function TXPInformationPanel.GetHeaderEndColor: TColor;
begin
  Result := FHeaderEndColor;
end;

procedure TXPInformationPanel.SetHeaderEndColor(const Value: TColor);
begin
  FHeaderEndcolor := Value;
  Repaint;
end;

function TXPInformationPanel.GetHeaderFont: TFont;
begin
  Result := FHeaderCaption.Font;
end;

procedure TXPInformationPanel.SetHeaderFont(const Value: TFont);
begin
  FHeaderCaption.Font := Value;
end;

function TXPInformationPanel.GetHeaderHeight: Integer;
begin
  Result := FHeader.Height;
end;

procedure TXPInformationPanel.SetHeaderHeight(const Value: Integer);
begin
  FHeader.Height := Value;
  FHeaderCaption.Top := Value div 4;
end;

function TXPInformationPanel.GetBackgroundImage: TBitmap;
begin
  Result := FBackgroundImage.Picture.Bitmap;
end;

procedure TXPInformationPanel.SetBackgroundImage(const Value: TBitmap);
begin
  FBackgroundImage.Picture.Bitmap := Value;
  Repaint;
end;

function TXPInformationPanel.GetBackTransparent: Boolean;
begin
  Result := FBackgroundImage.Transparent;
end;

procedure TXPInformationPanel.SetBackTransparent(const Value: Boolean);
begin
  FBackgroundImage.Transparent := Value;
end;

function TXPInformationPanel.GetBackCenter: Boolean;
begin
  Result := FBackgroundImage.Center;
end;

procedure TXPInformationPanel.SetBackCenter(const Value: Boolean);
begin
  FBackgroundImage.Center := Value;
  Repaint;
end;

function TXPInformationPanel.GetBackStretch: Boolean;
begin
  Result := FBackgroundImage.Stretch;
end;

procedure TXPInformationPanel.SetBackStretch(const Value: Boolean);
begin
  FBackgroundImage.Stretch := Value;
  Repaint;
end;

procedure TXPInformationPanel.ButtonClick(Sender: TObject);
begin
  if Assigned(FOnButtonClick) then
    FOnButtonClick(Self);
end;

procedure TXPInformationPanel.SetExpanded(const Value: Boolean);
begin
  if Value <> FExpanded then
  begin
    FExpanded := Value;
    if Expanded then
      Expanding
    else
      UnExpanding;
  end;
end;

procedure TXPInformationPanel.FHExpanding(FH: Integer);
begin
  if FH < FOldHeight then
  begin
    if FH mod 12 = 0 then
    begin
      Sleep(10);
      Height := FH;
      Repaint;
    end;
    FHExpanding(FH + 1);
  end;
  Height := FOldHeight;
end;

procedure TXPInformationPanel.Expanding;
begin
  FIcon.Picture.Bitmap.Handle := LoadBitmap(HInstance, 'XPFOLD');
  FHExpanding(24);
end;

procedure TXPInformationPanel.FHUnExpanding(FH: Integer);
begin
  if FH > 24 then
  begin
    if FH mod 12 = 0 then
    begin
      Sleep(10);
      Height := FH;
      Repaint;
    end;
    FHUnExpanding(FH - 1);
  end;
  Height := 24;
end;

procedure TXPInformationPanel.UnExpanding;
begin
  FIcon.Picture.Bitmap.Handle := LoadBitmap(HInstance, 'XPUNFOLD');
  FOldHeight := Height;
  FHUnExpanding(Height);
end;

procedure TXPInformationPanel.SetBorder(const Value: Boolean);
begin
  if Value <> FBorder then
  begin
    FBorder := Value;
    Repaint;
  end;
end;

procedure TXPInformationPanel.SetColorBorder(const Value: TColor);
begin
  if Value <> FColorBorder then
  begin
    FColorBorder := Value;
    Repaint;
  end;
end;

procedure TXPInformationPanel.SetText(const Value: String);
begin
  if FText <> Value then
  begin
    FText := Value;
    FLabel.Caption := FText;
  end;
end;

end.

