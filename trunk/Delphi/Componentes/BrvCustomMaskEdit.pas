unit BrvCustomMaskEdit;

interface

uses StdCtrls, Classes, Mask, Graphics;

type
  TBrvCustomMaskEdit = class(TMaskEdit)
  private
    { Private declarations }
    FCorReaOnl : TColor;
  protected
    { Protected declarations }
  public
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure   BrSetFocus;
    procedure   BrvReadOnly(pSnReadOnly : boolean);
    { Public declarations }
  published
    { Published declarations }
    property BrvReadOnlyColor : TColor read FCorReaOnl write FCorReaOnl;
  end;

implementation

constructor TBrvCustomMaskEdit.Create(AOwner : TComponent);
begin
      BrvReadOnlyColor := $00DDE2E3;
      inherited Create(AOwner);
end;

destructor TBrvCustomMaskEdit.Destroy;
begin
      inherited  destroy;
end;

procedure TBrvCustomMaskEdit.BrSetFocus;
begin
      if CanFocus then
      begin
            SetFocus;
      end;
end;

procedure TBrvCustomMaskEdit.BrvReadOnly;
begin
      self.ReadOnly := pSnReadOnly;

      if (pSnReadOnly) then
      begin
            self.Color := BrvReadOnlyColor;
      end else
      begin
            self.Color := clWhite;
      end;
end;

end.
