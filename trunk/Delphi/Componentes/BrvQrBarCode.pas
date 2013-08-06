{By Francisco Reis Oliveira}
unit BrvQrBarCode;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  ExtCtrls, ClipBrd, Printers, QuickRpt;

type
  TOrientacao  = (RHorizontal, RVertical);
  TBarcodeType = (btInterleaved2of5, btCode39, btEAN13);

  TBrvQrBarCode = class(TQRPrintable) {Quick Report 2.x}
  private
    { Private declarations }
    BarStr         : string;                   {String de B's e E's que formarao as Barras}
    FPicture       : TPicture;
    FBarcodeType   : TBarcodeType;             {Padrao do Código de Barras}
    FDigits        : string;
    FMagnification : integer;                  {Espessura em Pontos do Módulo}
    FVersion       : string;
    FBarCaption    : Boolean;
    FRescaleEAN    : Boolean;
    FBarColor      : TColor;
    FBackGColor    : TColor;
    FOrientacao    : TOrientacao;
    procedure SetPicture(Value: TPicture);
    function  GetCanvas: TCanvas;
    procedure SetBarStr(Value : string);
    procedure SetBarcodeType(BarcodeType : TBarcodeType);
    procedure SetDigits(Value : string);
    procedure DrawBars;
    function  MontaPar(A,B : Integer) : string;
    function  EAN13DV(Value : AnsiString) : AnsiChar;
    procedure SetMagnification(Magnification : Integer);
    procedure SetBarCaption(Value : Boolean);
    procedure SetRescaleEAN(Value : Boolean);
    procedure SetBarColor(Value : TColor);
    procedure SetBackgroundColor(Value : TColor);
    procedure SetOrientacao(Value : TOrientacao);
    procedure ReDraw;
    property  Picture: TPicture read FPicture write SetPicture;
  protected
    { Protected declarations }
    procedure Paint; override;
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor Destroy; override;
    function SaveBarcode(FileName : string) : Boolean;   {Salva as Barras para FileName}
    property Canvas: TCanvas read GetCanvas;
    procedure BarcodePrint(X, Y : Integer); {Imprime as Barras}
    procedure Print(X,Y : Integer); override;
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
    property BackgroundColor : TColor read FBackGColor write SetBackgroundColor;
    property BarcodeType : TBarcodeType read FBarcodeType write SetBarcodeType;
    property Digits : string read FDigits write SetDigits;
    property Magnification : integer read FMagnification write SetMagnification;
    property BarCaption : Boolean read FBarCaption write SetBarCaption;
    property RescaleEAN : Boolean read FRescaleEAN write SetRescaleEAN;
    property Orientacao : TOrientacao read FOrientacao write SetOrientacao;
  end;

procedure Register;

const
      MargemV = 6;
      MargemH = 14;

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

uses Math;

procedure Register;
begin
     RegisterComponents('Bravo Relatorio', [TBrvQrBarCode]);
end;

constructor TBrvQrBarCode.Create(AOwner : TComponent);
begin
     inherited Create(AOwner);
     ControlStyle   := ControlStyle + [csReplicatable];
     FPicture       := TPicture.Create;
     FDigits        := '000000000000';
     FRescaleEAN    := True;
     Height         := 70;
     FMagnification := 1;
     FBarColor      := clBlack;
     FBackGColor    := clWhite;
     Orientacao     := RHorizontal;
     SetDigits(FDigits);
end;

destructor TBrvQrBarCode.Destroy;
begin
     FPicture.Destroy;
     inherited Destroy;
end;

procedure TBrvQrBarCode.SetBarcodeType(BarcodeType : TBarcodeType);
begin
     FBarcodeType := BarcodeType;

     if BarcodeType = btEAN13 then
     begin
           FBarCaption := True;
     end;

     SetDigits(Digits);
end;

procedure TBrvQrBarCode.SetBarStr(Value : string);
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

procedure TBrvQrBarCode.SetDigits(Value : string);
var F : Integer;
    HaveAlpha : Boolean;
begin
      HaveAlpha := False;
      Value := UpperCase(Value);

      for F := 1 to Length(Value) do
      begin
            HaveAlpha := (HaveAlpha or not (Value[F] in ['0'..'9']));
      end;

      if HaveAlpha and (FBarcodeType <> btCode39) then
      begin
            FBarcodeType := btCode39;
      end;

      if (FBarcodeType = btEAN13) and (Length(Value) <> 12) then
      begin
            if HaveAlpha then
            begin
                  FBarcodeType := btCode39
            end else
            begin
                  FBarcodeType := btInterleaved2of5;
            end;
      end;

      FDigits := Value;
      SetBarStr(Value);

      if (Value = '') then
      begin
            BarStr := '';
      end;

      DrawBars;
      ReDraw;
end;

function TBrvQrBarCode.EAN13DV(Value : AnsiString) : AnsiChar;
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

function TBrvQrBarCode.MontaPar(A,B : integer) : string;
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

procedure TBrvQrBarCode.DrawBars;
var   F,H,W           : integer;
      BC              : string;   {BarCaption}
      FWidth, FHeight : Integer;
      X,Y             : Integer;
      Resultado       : TBitmap;
begin
     {Definindo o Comprimento do Componente}
      if FBarCaption then
      begin
            for F := 1 to MargemH do
            begin
                  BarStr := 'E' + BarStr + 'E';
            end;
      end;

      if (Orientacao = RHorizontal) then
      begin
            Self.Width := FMagnification * Length(BarStr);

           {Definindo a Altura do Componente}
            if (FRescaleEAN and (FBarcodeType = btEAN13)) then
            begin
                  Self.Height := Round(Self.Width * 0.75);
            end;
      end else
      begin
            Self.Height := FMagnification * Length(BarStr);

           {Definindo a Altura do Componente}
            if (FRescaleEAN and (FBarcodeType = btEAN13)) then
            begin
                  Self.Width := Round(Self.Height * 0.75);
            end;
      end;

     {Definindo o Comprimento do Gráfico}
      Picture.Bitmap.Width := Self.Width;

     {Definindo a Altura do Gráfico}
      Picture.Bitmap.Height := Self.Height;

     {Definindo o Fundo do Componente}
      Picture.Bitmap.Canvas.Brush.Style := bsSolid;
      Picture.Bitmap.Canvas.Brush.Color := FBackGColor;

     {Desenhando o Retangulo do Fundo}
      Picture.Bitmap.Canvas.FillRect(Self.BoundsRect);

     {Desenhando as Barras}
      for F := 1 to Length(BarStr) do
      begin
            if BarStr[F] = 'B' then
            begin
                  Picture.Bitmap.Canvas.Brush.Color := FBarColor
            end else
            begin
                  Picture.Bitmap.Canvas.Brush.Color := FBackGColor;
            end;

            if Orientacao = RVertical then
            begin
                  Picture.Bitmap.Canvas.FillRect(Rect(0, F * FMagnification - FMagnification,
                                                 Self.Height, F * FMagnification));
            end else
            begin
                  Picture.Bitmap.Canvas.FillRect(Rect(F * FMagnification - FMagnification, 0,
                                                 F * FMagnification, Self.Height));
            end;
      end;

     {Imprimindo o BarCaption}
     if (FBarCaption) then
     begin
           Picture.Bitmap.Canvas.Brush.Color := FBackGColor;
           Picture.Bitmap.Canvas.FillRect(Rect(0, 0, Self.Width, MargemV * FMagnification));
           Picture.Bitmap.Canvas.FillRect(Rect(0, Self.Height - MargemV * FMagnification,
                                               Self.Width, Self.Height));
           if FBarcodeType = btEAN13 then
           begin
                 BC := FDigits + EAN13DV(FDigits)
           end else
           begin
                  BC := FDigits;
           end;

           try
               Picture.Bitmap.Canvas.Font.Name := 'Arial';
           except
               // Não Faz Nada //
           end;

           Picture.Bitmap.Canvas.Font.Height := Round(Self.Width / 9);
           Picture.Bitmap.Canvas.Font.Color := FBarColor;
           W := Picture.Bitmap.Canvas.TextWidth(BC);
           H := Picture.Bitmap.Canvas.TextHeight(BC);
           Picture.Bitmap.Canvas.Brush.Color := FBackGColor;

           if (FBarcodeType <> btEAN13) then
           begin
                 Picture.Bitmap.Canvas.FillRect(Rect((Picture.Bitmap.Width - W) div 2 - 2,
                                                Picture.Bitmap.Height - H,
                                               (Picture.Bitmap.Width - W) div 2 + W + 2,
                                                Picture.Bitmap.Height));
                 Picture.Bitmap.Canvas.TextOut((Picture.Bitmap.Width - W) div 2,
                                                Picture.Bitmap.Height - H, BC);
           end else
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

     if FBarCaption then
     begin
           Delete(BarStr, 1, MargemH);
           Delete(BarStr, Length(BarStr) - MargemH + 1, MargemH);
     end;
end;

procedure TBrvQrBarCode.Paint;
var Dest : TRect;
begin
      if csDesigning in ComponentState then
      begin
            with inherited Canvas do
            begin
                  Pen.Style := psDash;
                  Brush.Style := bsClear;
                  Rectangle(0, 0, Width, Height);
                  DrawBars;
            end;
      end;

      Dest := Rect(0, 0, Picture.Width, Picture.Height);

      inherited Canvas.StretchDraw(Dest, Picture.Graphic);
      DrawBars;
end;

procedure TBrvQrBarCode.Print(X,Y : Integer);
var aCanvas : TCanvas;
    lPtInit : TPoint;
    lPtFini : TPoint;
    aRect   : TRect;
begin
      aCanvas   := ParentReport.QRPrinter.Canvas;
      lPtInit.X := ParentReport.QrPrinter.XPos(X + Size.Left);
      lPtInit.Y := ParentReport.QrPrinter.YPos(Y + Size.Top);
      lPtFini.X := (lPtInit.X + ParentReport.QrPrinter.XPos(Size.Width));
      lPtFini.Y := (lPtInit.Y + ParentReport.QrPrinter.YPos(Size.Height));
      aRect     := Rect(lPtInit, lPtFini);

      aCanvas.StretchDraw(aRect, Picture.Graphic);
end;

procedure TBrvQrBarCode.SetMagnification(Magnification : Integer);
begin
      if Magnification > 9 then
      begin
            FMagnification := 9;
      end else
      begin
            if Magnification < 1 then
            begin
                  FMagnification := 1;
            end else
            begin
                  FMagnification := Magnification;
            end;
      end;

      SetDigits(Digits);
end;

procedure TBrvQrBarCode.SetBarCaption(Value : Boolean);
begin
     FBarCaption := Value;
     SetDigits(FDigits);
end;

procedure TBrvQrBarCode.SetOrientacao(Value: TOrientacao);
begin
      FOrientacao := Value;
      SetDigits(FDigits);
end;

procedure TBrvQrBarCode.SetRescaleEAN(Value : Boolean);
begin
     FRescaleEAN := Value;
     SetDigits(FDigits);
end;

procedure TBrvQrBarCode.SetPicture(Value: TPicture);
begin
     FPicture.Assign(Value);
end;

procedure TBrvQrBarCode.SetBarColor(Value : TColor);
begin
     FBarColor := Value;
     SetDigits(FDigits);
end;

procedure TBrvQrBarCode.SetBackgroundColor(Value : TColor);
begin
     FBackGColor := Value;
     SetDigits(FDigits);
end;

function TBrvQrBarCode.GetCanvas: TCanvas;
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

function TBrvQrBarCode.SaveBarcode(FileName : string) : Boolean;
begin
     try
       FPicture.SaveToFile(FileName);
       Result := True;
     except
       Result := False;
     end;
end;

procedure TBrvQrBarCode.ReDraw;
begin
     Repaint;
end;

procedure TBrvQrBarCode.BarcodePrint(X, Y : Integer);
begin
      Printer.Canvas.Draw(X, Y, FPicture.Graphic);
end;

end.

