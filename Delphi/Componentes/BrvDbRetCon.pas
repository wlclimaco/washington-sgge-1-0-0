unit BrvDbRetCon;

interface

uses
  SysUtils, Classes, Controls, StdCtrls, DBCtrls, Forms;

type
  TBrvDBRetCon = class(TDBEdit)
  private
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
  published
    { Published declarations }
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Consulta', [TBrvDBRetCon]);
end;

{ TBrvDBRetCon }

constructor TBrvDBRetCon.Create(AOwner: TComponent);
begin
  inherited;
      TabStop     := False;
      ReadOnly    := True;
      ParentColor := True;
      BevelInner  := bvLowered;
      BevelKind   := bkFlat;
      BevelOuter  := bvLowered;
      BorderStyle := bsNone;
      BevelWidth  := 2;
end;

destructor TBrvDBRetCon.Destroy;
begin

  inherited;
end;

end.
