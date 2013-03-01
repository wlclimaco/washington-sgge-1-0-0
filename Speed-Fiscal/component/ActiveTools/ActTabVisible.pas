unit ActTabVisible;

interface

uses
  SysUtils, Classes, ComCtrls;

type
  TActTabVisible = class(TComponent)
  private
    FPageControl: TCustomTabControl;
    FVisible: Boolean;
    FPageIndex: Byte;
    procedure SetVisible(const Value: Boolean);
    procedure SetPageIndex(const Value: Byte);
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure NextPage;
    procedure PriorPage;
  published
    { Published declarations }
    property PageControl: TCustomTabControl read FPageControl write FPageControl;
    property Visible: Boolean read FVisible write SetVisible;
    property PageIndex: Byte read FPageIndex write SetPageIndex;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Controls', [TActTabVisible]);
end;

{ TActTabVisible }

constructor TActTabVisible.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FPageIndex := 0;
end;

destructor TActTabVisible.Destroy;
begin
  inherited Destroy;
end;

procedure TActTabVisible.NextPage;
begin
  TPageControl(FPageControl).SelectNextPage(True, False);
end;

procedure TActTabVisible.PriorPage;
begin
  TPageControl(FPageControl).SelectNextPage(False, False);
end;

procedure TActTabVisible.SetPageIndex(const Value: Byte);
begin
  if Value <> FPageIndex then
  begin
    if Assigned(FPageControl) then
    begin
      FPageIndex := Value;
      if FPageIndex > TPageControl(FPageControl).PageCount - 1 then
        FPageIndex := TPageControl(FPageControl).PageCount - 1;
      TPageControl(FPageControl).ActivePageIndex := FPageIndex;
    end
    else
      FPageIndex := 0;
  end;
end;

procedure TActTabVisible.SetVisible(const Value: Boolean);
var
  iPage: Integer;
begin
  if Value <> FVisible then
  begin
    FVisible := Value;
    if Assigned(FPageControl) then
    begin
      for iPage := 0 to TPageControl(FPageControl).PageCount - 1 do
        TPageControl(FPageControl).Pages[iPage].TabVisible := FVisible;
      TPageControl(FPageControl).ActivePageIndex := FPageIndex;
    end;
  end;
end;

end.
