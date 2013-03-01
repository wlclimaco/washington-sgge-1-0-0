unit ActStopWatch;

interface

uses
  WinProcs, SysUtils, Classes;

type
  TActStopWatch = class(TComponent)
  private
    FRunning: Boolean;
    FRunningTotal: TDateTime;
    FStartTime: TDateTime;
    FElapsedTime: TDateTime;
    function GetElapsedTime: TDateTime;
    procedure Update;
  public
    procedure Reset;
    procedure Start;
    procedure Stop;
    property ElapsedTime: TDateTime read GetElapsedTime;
  published
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Controls', [TActStopWatch]);
end;

{ TActStopWatch }

function TActStopWatch.GetElapsedTime: TDateTime;
begin
  if FRunning then
    Update;
  Result := FElapsedTime;
end;

procedure TActStopWatch.Update;
begin
  FElapsedTime := Now - FStartTime + FRunningTotal;
end;

procedure TActStopWatch.Reset;
begin
  FRunningTotal := 0.0;
  FElapsedTime := 0.0;
  FStartTime := Now;
end;

procedure TActStopWatch.Start;
begin
  if not FRunning then
  begin
    FRunning := True;
    FStartTime := Now;
  end;
end;

procedure TActStopWatch.Stop;
begin
  if FRunning then
  begin
    Update;
    FRunningTotal := FElapsedTime;
    FRunning := False;
  end;
end;

end.

