unit BrvBitBtn;

interface

uses
  SysUtils, Classes, Controls, StdCtrls, Buttons, Graphics, BrvImgBot;

type

  TBrvBitBtn = class(TBitBtn)
  private
    { Private declarations }
    gTpBitBtn : TBrTpBotao;
    gBrImagem : TBrvImagem;
    procedure SetTipoBotao(pTpBitBtn: TBrTpBotao);
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;
  published
    { Published declarations }
    property BrTipoBotao : TBrTpBotao        read gTpBitBtn  write SetTipoBotao;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Standard', [TBrvBitBtn]);
end;

constructor TBrvBitBtn.Create(AOwner: TComponent);
begin
      inherited;

      gBrImagem := TBrvImagem.Create(Self);
      SetTipoBotao(BrBtnNone);
      Width := 90;
      Font.Style := [fsBold];
end;

destructor TBrvBitBtn.Destroy;
begin
      FreeAndNil(gBrImagem);
      inherited;
end;

procedure TBrvBitBtn.SetTipoBotao(pTpBitBtn : TBrTpBotao);
var lDsHint  : String;
    lImGlyph : TBitMap;
begin
      gTpBitBtn := pTpBitBtn;

      lImGlyph := TBitMap.Create;

      try
          gBrImagem.BrImagem(gTpBitBtn, lDsHint, lImGlyph);

          if Hint = '' then
          begin
                Hint     := lDsHint;
                ShowHint := True;
          end;

          Glyph := lImGlyph;
      finally
          FreeAndNil(lImGlyph);
      end;

      NumGlyphs := StrToInt(FormatFloat('0', Glyph.Width / 20));
end;

end.
