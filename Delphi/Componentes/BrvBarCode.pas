unit BrvBarCode;    {By Francisco Reis Oliveira}

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  ExtCtrls, ClipBrd, Printers;

type
  TAuthor = class(TPersistent)
  private
    FName  : string;
    FPhone  : string;
    FEmail : string;
    procedure SetName(Nome : string);
    procedure SetPhone(Fone : string);
    procedure SetEmail(Mail : string);
  protected
  public
    constructor Create;
  published
    property Name : string read FName write SetName;
    property Phone : string read FPhone write SetPhone;
    property Email : string read FEmail write SetEmail;
  end;

  TBrvBarCodeType = (btInterleaved2of5, btCode39, btEAN13);

  TBrvBarCode = class(TGraphicControl)
  private
    { Private declarations }
    BarStr           : string;                   {String de B's e E's que formarao as Barras}
    FPicture         : TPicture;
    FBarcodeType     : TBrvBarCodeType;             {Padrao do Código de Barras}
    FDigits          : string;
    FMagnification   : integer;                  {Espessura em Pontos do Módulo}
    FVersion         : string;
    FAuthor          : TAuthor;
    FBarCaption      : Boolean;
    FRescaleEAN      : Boolean;
    FBarColor,
    FBackgroundColor : TColor;
    function DelphiIsRunning: Boolean;
    procedure SetPicture(Value: TPicture);
    function GetCanvas: TCanvas;
    procedure SetBarStr(Value : string);
    procedure SeTBrvBarCodeType(BarcodeType : TBrvBarCodeType);
    procedure SetDigits(Value : string);
    procedure DrawBars;
    function MontaPar(A,B : Integer) : string;
    function EAN13DV(Value : AnsiString) : AnsiChar;
    procedure SetMagnification(Magnification : Integer);
    procedure SetVersion(Version : string);
    procedure SetBarCaption(Value : Boolean);
    procedure SetRescaleEAN(Value : Boolean);
    procedure SetBarColor(Value : TColor);
    procedure SetBackgroundColor(Value : TColor);
    procedure ReDraw;
    property Picture: TPicture read FPicture write SetPicture;
  protected
    { Protected declarations }
    procedure Paint; override;
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor Destroy; override;
    procedure BarcodeCopy;                               {Copia as Barras para o Clipboard}
    function SaveBarcode(FileName : string) : Boolean;   {Salva as Barras para FileName}
    property Canvas: TCanvas read GetCanvas;
    procedure BarcodePrint(X, Y : Integer; P : TPrinter); {Imprime as Barras}
  published
    { Published declarations }
    property Enabled;
    property ParentShowHint;
    property PopupMenu;
    property ShowHint;
    property Visible;
    property OnClick;
    property OnDblClick;
    property OnDragDrop;
    property OnDragOver;
    property OnEndDrag;
    property OnMouseDown;
    property OnMouseMove;
    property OnMouseUp;
    property OnStartDrag;
    property BarColor : TColor read FBarColor write SetBarColor;
    property BackgroundColor : TColor read FBackgroundColor write SetBackgroundColor;
    property BarcodeType : TBrvBarCodeType read FBarcodeType write SeTBrvBarCodeType;
    property Digits : string read FDigits write SetDigits;
    property Magnification : integer read FMagnification write SetMagnification;
    property Version : string read FVersion write SetVersion;
    property Author : TAuthor read FAuthor write FAuthor;
    property BarCaption : Boolean read FBarCaption write SetBarCaption;
    property RescaleEAN : Boolean read FRescaleEAN write SetRescaleEAN;
  end;

procedure Register;

const Ver = 'Registered 1.0';
      ITF : array[0..9] of string[5] = ('00110'{0},'10001'{1},'01001'{2},'11000'{3},'00101'{4},
                                        '10100'{5},'01100'{6},'00011'{7},'10010'{8},'01010'{9});
      C39 : array[0..43] of string[15] = ('BEBEEEBBBEBBBEB', {'0'}
                                          'BBBEBEEEBEBEBBB', {'1'}
                                          'BEBBBEEEBEBEBBB', {'2'}
                                          'BBBEBBBEEEBEBEB', {'3'}
                                          'BEBEEEBBBEBEBBB', {'4'}
                                          'BBBEBEEEBBBEBEB', {'5'}
                                          'BEBBBEEEBBBEBEB', {'6'}
                                          'BEBEEEBEBBBEBBB', {'7'}
                                          'BBBEBEEEBEBBBEB', {'8'}
                                          'BEBBBEEEBEBBBEB', {'9'}
                                          'BBBEBEBEEEBEBBB', {'A'}
                                          'BEBBBEBEEEBEBBB', {'B'}
                                          'BBBEBBBEBEEEBEB', {'C'}
                                          'BEBEBBBEEEBEBBB', {'D'}
                                          'BBBEBEBBBEEEBEB', {'E'}
                                          'BEBBBEBBBEEEBEB', {'F'}
                                          'BEBEBEEEBBBEBBB', {'G'}
                                          'BBBEBEBEEEBBBEB', {'H'}
                                          'BEBBBEBEEEBBBEB', {'I'}
                                          'BEBEBBBEEEBBBEB', {'J'}
                                          'BBBEBEBEBEEEBBB', {'K'}
                                          'BEBBBEBEBEEEBBB', {'L'}
                                          'BBBEBBBEBEBEEEB', {'M'}
                                          'BEBEBBBEBEEEBBB', {'N'}
                                          'BBBEBEBBBEBEEEB', {'O'}
                                          'BEBBBEBBBEBEEEB', {'P'}
                                          'BEBEBEBBBEEEBBB', {'Q'}
                                          'BBBEBEBEBBBEEEB', {'R'}
                                          'BEBBBEBEBBBEEEB', {'S'}
                                          'BEBEBBBEBBBEEEB', {'T'}
                                          'BBBEEEBEBEBEBBB', {'U'}
                                          'BEEEBBBEBEBEBBB', {'V'}
                                          'BBBEEEBBBEBEBEB', {'W'}
                                          'BEEEBEBBBEBEBBB', {'X'}
                                          'BBBEEEBEBBBEBEB', {'Y'}
                                          'BEEEBBBEBBBEBEB', {'Z'}
                                          'BEEEBEBEBBBEBBB', {'-'}
                                          'BBBEEEBEBEBBBEB', {'.'}
                                          'BEEEBBBEBEBBBEB', {'#'}
                                          'BEEEBEBBBEBBBEB', {'*'}
                                          'BEEEBEEEBEEEBEB', {'$'}
                                          'BEEEBEEEBEBEEEB', {'/'}
                                          'BEEEBEBEEEBEEEB', {'+'}
                                          'BEBEEEBEEEBEEEB'  {'%'});
      EANA : array[0..9] of string[7] = ('EEEBBEB'{0},'EEBBEEB'{1},
                                         'EEBEEBB'{2},'EBBBBEB'{3},
                                         'EBEEEBB'{4},'EBBEEEB'{5},
                                         'EBEBBBB'{6},'EBBBEBB'{7},
                                         'EBBEBBB'{8},'EEEBEBB'{9});
      EANB : array[0..9] of string[7] = ('EBEEBBB'{0},'EBBEEBB'{1},
                                         'EEBBEBB'{2},'EBEEEEB'{3},
                                         'EEBBBEB'{4},'EBBBEEB'{5},
                                         'EEEEBEB'{6},'EEBEEEB'{7},
                                         'EEEBEEB'{8},'EEBEBBB'{9});
      EANC : array[0..9] of string[7] = ('BBBEEBE'{0},'BBEEBBE'{1},
                                         'BBEBBEE'{2},'BEEEEBE'{3},
                                         'BEBBBEE'{4},'BEEBBBE'{5},
                                         'BEBEEEE'{6},'BEEEBEE'{7},
                                         'BEEBEEE'{8},'BBBEBEE'{9});

implementation

procedure Register;
begin
     RegisterComponents('Bravo Utils', [TBrvBarCode]);
end;
               
{%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%}
{%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% TAuthor %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%}
{%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%}

constructor TAuthor.Create;      
begin
     inherited Create;
     FName := 'Francisco Reis Oliveira';
     FPhone := '(034) 314-3592';
     FEmail := 'linkd@linkd.com.br';
end;

procedure TAuthor.SetName(Nome : string);
begin
     FName := 'Francisco Reis Oliveira';
end;

procedure TAuthor.SetPhone(Fone : string);
begin
     FPhone := '(034) 314-3592';
end;

procedure TAuthor.SetEmail(Mail : string);
begin
     FEmail := 'linkd@linkd.com.br';
end;

{%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%}
{%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% TBrvBarCode %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%}
{%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%}

constructor TBrvBarCode.Create(AOwner : TComponent);
begin
     inherited Create(AOwner);
     ControlStyle := ControlStyle + [csReplicatable];
     FPicture := TPicture.Create;
     FAuthor := TAuthor.Create;
     FDigits := '000000000000';
     FRescaleEAN := True;
     FVersion := Ver;
     Height := 70;
     FMagnification := 1;
     FBarColor := clBlack;
     FBackgroundColor := clWhite;
     SetDigits(FDigits);
end;

destructor TBrvBarCode.Destroy;
begin
     FAuthor.Destroy;
     FPicture.Destroy;
     inherited Destroy;
end;

function TBrvBarCode.DelphiIsRunning: Boolean;
var H1, H2, H3, H4 :HWND;
const A1 : array[0..12] of char = 'TApplication'#0;
      A2 : array[0..15] of char = 'TAlignPalette'#0;
      A3 : array[0..18] of char = 'TPropertyInspector'#0;
      A4 : array[0..11] of char = 'TAppBuilder'#0;
begin
     H1 := FindWindow(A1, nil);
     H2 := FindWindow(A2, nil);
     H3 := FindWindow(A3, nil);
     H4 := FindWindow(A4, nil);
     Result := (H1<>0) and (H2<>0) and (H3<>0) and (H4<>0);
end;

procedure TBrvBarCode.SeTBrvBarCodeType(BarcodeType : TBrvBarCodeType);
begin
     FBarcodeType := BarcodeType;
     if BarcodeType = btEAN13
       then FBarCaption := True;
     SetDigits(Digits);
end;

procedure TBrvBarCode.SetBarStr(Value : string);
var F : integer;
    DV : AnsiChar;
begin
     if FBarcodeType = btInterleaved2of5
       then
         begin
           BarStr := 'BEBE';
           if (Length(Value) / 2) <> (Length(Value) div 2)
             then
               begin
                 {O Numero de Digitos é Ímpar}
                 if Value[1] = '0'
                   then Delete(Value,1,1)
                   else Value := '0' + Value;
               end;
           for F := 1 to Length(Value) div 2 do
             BarStr := BarStr + MontaPar(StrToInt(Value[F*2-1]), StrToInt(Value[F*2]));
           BarStr := BarStr + 'BBBEB';
         end else
     if FBarcodeType = btCode39
       then
         begin
           BarStr := C39[39] + 'E';
           for F := 1 to Length(Value) do
             begin
               if Value[F] in ['A'..'Z']
                 then BarStr := BarStr + C39[Ord(UpperCase(Value[F])[1]) - 55] + 'E';
               if Value[F] in ['0'..'9']
                 then BarStr := BarStr + C39[Ord(UpperCase(Value[F])[1]) - 48] + 'E';
               if Value[F] = '-' then BarStr := BarStr + C39[36] + 'E';
               if Value[F] = '.' then BarStr := BarStr + C39[37] + 'E';
               if Value[F] = '#' then BarStr := BarStr + C39[38] + 'E';
               if Value[F] = '$' then BarStr := BarStr + C39[40] + 'E';
               if Value[F] = '/' then BarStr := BarStr + C39[41] + 'E';
               if Value[F] = '+' then BarStr := BarStr + C39[42] + 'E';
               if Value[F] = '%' then BarStr := BarStr + C39[43] + 'E';
             end;
           BarStr := BarStr + C39[39];
         end else
     if FBarcodeType = btEAN13
       then
         begin
           DV := EAN13DV(Value);
           Value := Value + DV;
           BarStr := 'BEB';            {Guarda Inicial}
           case Value[1] of
             '0' : BarStr := BarStr + EANA[StrToInt(Value[2])] + EANA[StrToInt(Value[3])] +
                                      EANA[StrToInt(Value[4])] + EANA[StrToInt(Value[5])] +
                                      EANA[StrToInt(Value[6])] + EANA[StrToInt(Value[7])];
             '1' : BarStr := BarStr + EANA[StrToInt(Value[2])] + EANA[StrToInt(Value[3])] +
                                      EANB[StrToInt(Value[4])] + EANA[StrToInt(Value[5])] +
                                      EANB[StrToInt(Value[6])] + EANB[StrToInt(Value[7])];
             '2' : BarStr := BarStr + EANA[StrToInt(Value[2])] + EANA[StrToInt(Value[3])] +
                                      EANB[StrToInt(Value[4])] + EANB[StrToInt(Value[5])] +
                                      EANA[StrToInt(Value[6])] + EANB[StrToInt(Value[7])];
             '3' : BarStr := BarStr + EANA[StrToInt(Value[2])] + EANA[StrToInt(Value[3])] +
                                      EANB[StrToInt(Value[4])] + EANB[StrToInt(Value[5])] +
                                      EANB[StrToInt(Value[6])] + EANA[StrToInt(Value[7])];
             '4' : BarStr := BarStr + EANA[StrToInt(Value[2])] + EANB[StrToInt(Value[3])] +
                                      EANA[StrToInt(Value[4])] + EANA[StrToInt(Value[5])] +
                                      EANB[StrToInt(Value[6])] + EANB[StrToInt(Value[7])];
             '5' : BarStr := BarStr + EANA[StrToInt(Value[2])] + EANB[StrToInt(Value[3])] +
                                      EANB[StrToInt(Value[4])] + EANA[StrToInt(Value[5])] +
                                      EANA[StrToInt(Value[6])] + EANB[StrToInt(Value[7])];
             '6' : BarStr := BarStr + EANA[StrToInt(Value[2])] + EANB[StrToInt(Value[3])] +
                                      EANB[StrToInt(Value[4])] + EANB[StrToInt(Value[5])] +
                                      EANA[StrToInt(Value[6])] + EANA[StrToInt(Value[7])];
             '7' : BarStr := BarStr + EANA[StrToInt(Value[2])] + EANB[StrToInt(Value[3])] +
                                      EANA[StrToInt(Value[4])] + EANB[StrToInt(Value[5])] +
                                      EANA[StrToInt(Value[6])] + EANB[StrToInt(Value[7])];
             '8' : BarStr := BarStr + EANA[StrToInt(Value[2])] + EANB[StrToInt(Value[3])] +
                                      EANA[StrToInt(Value[4])] + EANB[StrToInt(Value[5])] +
                                      EANB[StrToInt(Value[6])] + EANA[StrToInt(Value[7])];
             '9' : BarStr := BarStr + EANA[StrToInt(Value[2])] + EANB[StrToInt(Value[3])] +
                                      EANB[StrToInt(Value[4])] + EANA[StrToInt(Value[5])] +
                                      EANB[StrToInt(Value[6])] + EANA[StrToInt(Value[7])];
           end;
           BarStr := BarStr + 'EBEBE'; {Guarda Central}
           BarStr := BarStr + EANC[StrToInt(Value[8])] + EANC[StrToInt(Value[9])] +
                              EANC[StrToInt(Value[10])] + EANC[StrToInt(Value[11])] +
                              EANC[StrToInt(Value[12])] + EANC[StrToInt(Value[13])];
           BarStr := BarStr + 'BEB';   {Guarda Final}
         end;
end;

procedure TBrvBarCode.SetDigits(Value : string);
var F : Integer;
    HaveAlpha : Boolean;
begin
     if (FVersion[1] = 'U') and (not DelphiIsRunning)
       then
         Exit; {O Componente não é Registrado e o Delphi não está Ativando}
     HaveAlpha := False;
     Value := UpperCase(Value);
     for F := 1 to Length(Value) do
       HaveAlpha := (HaveAlpha or not (Value[F] in ['0'..'9']));
     if HaveAlpha and (FBarcodeType <> btCode39)
       then FBarcodeType := btCode39;
     if (FBarcodeType = btEAN13) and (Length(Value) <> 12)
       then
         if HaveAlpha
           then FBarcodeType := btCode39
           else FBarcodeType := btInterleaved2of5;
     FDigits := Value;
     SetBarStr(Value);
     if Value = '' then BarStr := '';
     DrawBars;
     ReDraw;
end;

function TBrvBarCode.EAN13DV(Value : AnsiString) : AnsiChar;
var C : array[2..13] of AnsiChar;
    N : array[2..13] of Byte;
    F : Byte;
    D : string[2];
begin
     for F := 2 to 13 do
       C[F] := Value[14-F];
     for F := 2 to 13 do
       N[F] := StrToInt(C[F]);
     D := IntToStr(10 - (((N[13]+N[11]+N[9]+N[7]+N[5]+N[3]) +
                         ((N[12]+N[10]+N[8]+N[6]+N[4]+N[2])*3)) mod 10));
     if Length(D) = 2
       then Result := '0'
       else Result := D[1];
end;

function TBrvBarCode.MontaPar(A,B : integer) : string;
var Par : string;
F : integer;
begin
     Par := '';
     for F := 1 to 5 do
       begin
         if ITF[A][F] = '0'
           then Par := Par + 'B'    {Barra Estreita}
           else Par := Par + 'BBB'; {Barra Larga}
         if ITF[B][F] = '0'
           then Par := Par + 'E'    {Barra Estreita}
           else Par := Par + 'EEE'; {Barra Larga}
       end;
     Result := Par;
end;

procedure TBrvBarCode.DrawBars;
var F,H,W  : integer;
    BC     : string;   {BarCaption}
const
     MargemV = 6;
     MargemH = 14;
begin
     {Definindo o Comprimento do Componente}
     if FBarCaption
       then
         for F := 1 to MargemH do BarStr := 'E' + BarStr + 'E';
     Self.Width := FMagnification * Length(BarStr);
     {Definindo a Altura do Componente}
     if FRescaleEAN and (FBarcodeType = btEAN13)
       then Self.Height := Round(Self.Width * 0.75);
     {Definindo o Comprimento do Gráfico}
     Picture.Bitmap.Width := Self.Width;
     {Definindo o Fundo do Componente}
     Picture.Bitmap.Canvas.Brush.Style := bsSolid;
     Picture.Bitmap.Canvas.Brush.Color := FBackgroundColor;
     {Definindo a Altura do Gráfico}
     Picture.Bitmap.Height := Self.Height;
     {Desenhando o Retangulo do Fundo}
     Picture.Bitmap.Canvas.FillRect(Self.BoundsRect);
     {Desenhando as Barras}
     for F := 1 to Length(BarStr) do
       begin
         if BarStr[F] = 'B'
           then Picture.Bitmap.Canvas.Brush.Color := FBarColor
           else Picture.Bitmap.Canvas.Brush.Color := FBackgroundColor;
         Picture.Bitmap.Canvas.FillRect(Rect(F * FMagnification - FMagnification, 0,
                                             F * FMagnification, Self.Height));
       end;
     {Imprimindo o BarCaption}
     if FBarCaption
       then
         begin
           Picture.Bitmap.Canvas.Brush.Color := FBackgroundColor;
           Picture.Bitmap.Canvas.FillRect(Rect(0, 0, Self.Width, MargemV * FMagnification));
           Picture.Bitmap.Canvas.FillRect(Rect(0, Self.Height - MargemV * FMagnification,
                                               Self.Width, Self.Height));
           if FBarcodeType = btEAN13 then BC := FDigits + EAN13DV(FDigits)
                                     else BC := FDigits;
           try
             Picture.Bitmap.Canvas.Font.Name := 'Arial';
           except
           end;
           Picture.Bitmap.Canvas.Font.Height := Round(Self.Width / 9);
           Picture.Bitmap.Canvas.Font.Color := FBarColor;
           W := Picture.Bitmap.Canvas.TextWidth(BC);
           H := Picture.Bitmap.Canvas.TextHeight(BC);
           Picture.Bitmap.Canvas.Brush.Color := FBackgroundColor;
           if FBarcodeType <> btEAN13
             then
               begin
                 Picture.Bitmap.Canvas.FillRect(Rect((Picture.Bitmap.Width - W) div 2 - 2,
                                                Picture.Bitmap.Height - H,
                                                (Picture.Bitmap.Width - W) div 2 + W + 2,
                                                Picture.Bitmap.Height));
                 Picture.Bitmap.Canvas.TextOut((Picture.Bitmap.Width - W) div 2,
                                               Picture.Bitmap.Height - H, BC);
               end
             else
               begin
                 Picture.Bitmap.Canvas.FillRect(Rect((MargemH + 3) * FMagnification,
                                                Picture.Bitmap.Height - H,
                                                (MargemH + 3 + 42) * FMagnification,
                                                Picture.Bitmap.Height));
                 Picture.Bitmap.Canvas.FillRect(Rect((MargemH + 3 + 42 + 5) * FMagnification,
                                                Picture.Bitmap.Height - H,
                                                (MargemH + 3 + 42 + 5 + 42) * FMagnification,
                                                Picture.Bitmap.Height));
                 Picture.Bitmap.Canvas.TextOut((MargemH + 3) * FMagnification +
                                               ((42 * FMagnification) -
                                               Picture.Bitmap.Canvas.TextWidth(Copy(BC,2,6))) div 2,
                                                Picture.Bitmap.Height - H, Copy(BC,2,6));
                 Picture.Bitmap.Canvas.TextOut((MargemH + 3 + 42 + 5) * FMagnification +
                                               ((42 * FMagnification) -
                                               Picture.Bitmap.Canvas.TextWidth(Copy(BC,2,6))) div 2,
                                                Picture.Bitmap.Height - H, Copy(BC,8,6));
                 Picture.Bitmap.Canvas.TextOut((MargemH * FMagnification -
                                               Picture.Bitmap.Canvas.TextWidth(BC[1])) div 2,
                                               Picture.Bitmap.Height - H, BC[1]);
               end;
         end;
     if FBarCaption
       then
         begin
           Delete(BarStr, 1, MargemH);
           Delete(BarStr, Length(BarStr) - MargemH + 1, MargemH);
         end;
end;

procedure TBrvBarCode.Paint;
var
  Dest : TRect;
begin
     if csDesigning in ComponentState
       then
         with inherited Canvas do
           begin
             Pen.Style := psDash;
             Brush.Style := bsClear;
             Rectangle(0, 0, Width, Height);
             DrawBars;
           end;
     Dest := Rect(0, 0, Picture.Width, Picture.Height);
     inherited Canvas.StretchDraw(Dest, Picture.Graphic);
     DrawBars;
end;

procedure TBrvBarCode.SetMagnification(Magnification : Integer);
begin
     if Magnification > 9
       then FMagnification := 9 else
     if Magnification < 1
       then FMagnification := 1 else
     FMagnification := Magnification;
     SetDigits(Digits);
end;

procedure TBrvBarCode.SetVersion(Version : string);
begin
     FVersion := Ver;
end;

procedure TBrvBarCode.SetBarCaption(Value : Boolean);
begin
     FBarCaption := Value;
     SetDigits(FDigits);
end;

procedure TBrvBarCode.SetRescaleEAN(Value : Boolean);
begin
     FRescaleEAN := Value;
     SetDigits(FDigits);
end;

procedure TBrvBarCode.SetPicture(Value: TPicture);
begin
     FPicture.Assign(Value);
end;

procedure TBrvBarCode.SetBarColor(Value : TColor);
begin
     FBarColor := Value;
     SetDigits(FDigits);
end;

procedure TBrvBarCode.SetBackgroundColor(Value : TColor);
begin
     FBackgroundColor := Value;
     SetDigits(FDigits);
end;

function TBrvBarCode.GetCanvas: TCanvas;
var
  Bitmap: TBitmap;
begin
     if Picture.Graphic = nil then
       begin
         Bitmap := TBitmap.Create;
         try
           Bitmap.Width := Width;
           Bitmap.Height := Height;
           Picture.Graphic := Bitmap;
         finally
           Bitmap.Free;
         end;
       end;
     if Picture.Graphic is TBitmap
       then Result := TBitmap(Picture.Graphic).Canvas;
end;

procedure TBrvBarCode.BarcodeCopy;
begin
     ClipBoard.Assign(Picture.Graphic);
end;

function TBrvBarCode.SaveBarcode(FileName : string) : Boolean;
begin
     try
       FPicture.SaveToFile(FileName);
       Result := True;
     except
       Result := False;
     end;
end;

procedure TBrvBarCode.ReDraw;
begin
     Repaint;
end;

procedure TBrvBarCode.BarcodePrint(X, Y : Integer; P : TPrinter);
begin
     P.Canvas.Draw(X, Y, FPicture.Graphic);
end;

end.

