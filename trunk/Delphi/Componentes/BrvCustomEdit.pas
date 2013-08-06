unit BrvCustomEdit;

interface

uses StdCtrls, Classes, Graphics;

type
  TBrvCustomEdit = class(TEdit)
  private
    { Private declarations }
    FCorReaOnl : TColor;
  protected
    { Protected declarations }
  public
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure   BrvSetFocus;
    procedure   BrvReadOnly(pSnReadOnly : boolean);
    { Public declarations }
  published
    { Published declarations }
    property BrvReadOnlyColor: TColor read FCorReaOnl write FCorReaOnl;
  end;

implementation

constructor TBrvCustomEdit.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
      BrvReadOnlyColor := $00DDE2E3;
end;

destructor TBrvCustomEdit.Destroy;
begin
      inherited  destroy;
end;

procedure TBrvCustomEdit.BrvReadOnly(pSnReadOnly : boolean);
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

procedure TBrvCustomEdit.BrvSetFocus;
begin
      if CanFocus then
      begin
            SetFocus;
      end;
end;

end.
