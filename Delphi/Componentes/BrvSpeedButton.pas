unit BrvSpeedButton;

interface

uses
  SysUtils, Classes, Controls, Buttons, Graphics, BrvImgBot;

type

  TBrvSpeedButton = class(TSpeedButton)
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
  RegisterComponents('Bravo Standard', [TBrvSpeedButton]);
end;

{ TBrvSpeedButton }

constructor TBrvSpeedButton.Create(AOwner: TComponent);
begin
      inherited;

      gBrImagem := TBrvImagem.Create(Self);
      SetTipoBotao(BrBtnNone);
end;

destructor TBrvSpeedButton.Destroy;
begin
      FreeAndNil(gBrImagem);
      inherited;
end;

procedure TBrvSpeedButton.SetTipoBotao(pTpBitBtn: TBrTpBotao);
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

      NumGlyphs := 2;
end;


end.
