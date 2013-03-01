unit ActOptical;
{
  Este componente detecta se num campo de edição foi utilizado um leitor
  óptico. Para utilizar, coloque no evento onChange do componente de edição.
  Por exemplo:
    ActOptical.InputFromOnChange := Edit1.text;
  se foi utilizado um leitor, então ctrlROpt.IsReadOptical é True
}

interface

uses

  Windows, SysUtils, Classes;
//  SysUtils, Types;

type
  TActOptical = class(TComponent)
  private
    FIsReadOpt: Boolean;
    FInput: ShortString;
    FSpeed: Word;
    aMSec: array[1..255] of Integer;
    procedure CalcReadOptical;
    function GetIsReadOpt: Boolean;
    procedure SetEmpty(const Value: Boolean);
    procedure ClearMatrix;
    procedure SetInput(const Value: ShortString);
    function ChkReadOptical: Boolean;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
  published
    property Speed: Word read FSpeed write FSpeed;
    property InputFromOnChange: ShortString read FInput write SetInput;
    property IsReadOptical: Boolean read GetIsReadOpt write SetEmpty;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Controls', [TActOptical]);
end;

{ TActOptical }

procedure TActOptical.CalcReadOptical;
var
  Hour, Min, Sec, MSec: Word;
  MSecT: Integer;
begin
  DecodeTime(Now, Hour, Min, Sec, MSec);
  MSecT := (Hour * 3600 + Min * 60 + Sec) * 1000 + MSec;
  aMSec[Length(FInput)] := MSecT;
end;

function TActOptical.ChkReadOptical: Boolean;
var
  nI: Smallint;
  nT,nJ: Integer;
begin
  nT := 0;
  nJ := 0;

  for nI := 2 to High(aMSec) do
  if aMSec[nI] = 0 then
    Break
  else
  begin
    nT := nT + aMSec[nI] - aMSec[nI - 1];
    nJ := nJ + 1;
  end;

  FIsReadOpt := False;
  if nJ > 0 then
    FIsReadOpt := Trunc(nT/nJ) <= FSpeed;

  Result := FIsReadOpt;
end;

procedure TActOptical.ClearMatrix;
var
  nI: Word;
begin
  for nI := Low(aMSec) to High(aMSec) do
    aMSec[nI] := 0;
end;

constructor TActOptical.Create(AOwner: TComponent);
begin
  inherited;
  FIsReadOpt := False;
  FSpeed := 20;  // milliseconds
  ClearMatrix;
end;

destructor TActOptical.Destroy;
begin
  inherited;

end;

function TActOptical.GetIsReadOpt: Boolean;
begin
  Result := chkReadOptical;
  ClearMatrix;
end;

procedure TActOptical.SetEmpty(const Value: Boolean);
begin
  //empty;
end;

procedure TActOptical.SetInput(const Value: ShortString);
begin
  FInput := Value;
  CalcReadOptical;
end;

end.
