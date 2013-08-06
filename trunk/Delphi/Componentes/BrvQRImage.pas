unit BrvQRImage;

interface

uses
  SysUtils, Classes, Controls, QuickRpt, QRCtrls, Graphics;

type
  TBrvQRImage = class(TQRImage)
  private
    FFont : TFont;
    FText : String;
    procedure SetFont(const Value: TFont);
    procedure SetText(const Value: String);
    procedure GeraTexto;
    procedure Limpa;
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
  published
    { Published declarations }
    property Font : TFont   read FFont write SetFont;
    property Text : String  read FText write SetText;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Relatorio', [TBrvQRImage]);
end;

{ TBrvQRImage }

procedure TBrvQRImage.SetFont(const Value: TFont);
begin
      FFont.Assign(Value);
      if (Trim(FText) <> '') then
      begin
            GeraTexto;
      end else
      begin
            Limpa;
      end;
end;

procedure TBrvQRImage.SetText(const Value: String);
begin
      FText := Value;
      if (Trim(FText) <> '') then
      begin
            GeraTexto;
      end else
      begin
            Limpa;
      end;
end;

constructor TBrvQRImage.Create(AOwner: TComponent);
begin
      inherited;
      FFont := TFont.Create;
end;

procedure TBrvQRImage.GeraTexto;
var lBtmText : TBitmap;
    lVrEixoX, lVrEixoY : Integer;
begin
      try
          lBtmText := TBitmap.Create;
          lBtmText.Canvas.Font.Assign(FFont);
          lBtmText.Width  := lBtmText.Canvas.TextWidth(FText)  + 1;
          lBtmText.Height := lBtmText.Canvas.TextHeight(FText) + 1;

          lBtmText.Canvas.TextOut(0,0, FText);

          Width  := lBtmText.Height;
          Height := lBtmText.Width;

          Picture.Bitmap.Width  := lBtmText.Height;
          Picture.Bitmap.Height := lBtmText.Width;

          for lVrEixoX := 0 to lBtmText.Width do
          begin
                for lVrEixoY := 0 to lBtmText.Height do
                begin
                      Picture.Bitmap.Canvas.Pixels[lVrEixoX, lVrEixoY] := clWhite;
                end;
          end;

          for lVrEixoX := 0 to lBtmText.Width-1 do
          begin
                for lVrEixoY := 0 to lBtmText.Height do
                begin
                      Picture.Bitmap.Canvas.Pixels[lVrEixoY, lBtmText.Width - lVrEixoX] :=
                                                          lBtmText.Canvas.Pixels[lVrEixoX,lVrEixoY];
                end;
          end;
      finally
          FreeAndNil(lBtmText);
      end;
end;

procedure TBrvQRImage.Limpa;
var lVrEixoX, lVrEixoY : Integer;
begin
      Width  := 100;
      Height := 13;

      Picture.Bitmap.Width  := 100;
      Picture.Bitmap.Height := 13;

      for lVrEixoX := 0 to 100 do
      begin
            for lVrEixoY := 0 to 13 do
            begin
                  Picture.Bitmap.Canvas.Pixels[lVrEixoX, lVrEixoY] := clWhite;;
            end;
      end;
end;

end.
