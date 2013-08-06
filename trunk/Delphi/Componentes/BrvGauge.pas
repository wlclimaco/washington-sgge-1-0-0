unit BrvGauge;

interface

uses SysUtils, Classes, Controls, ComCtrls, DbTables, Db, Forms;

type
  TBrvGauge = class(TProgressBar)
  private
    { Private declarations }

    gDataSet  : TDataSet;
    gSnProMes : Boolean;
    procedure SetDataSet(Value : TDataSet);

  protected
    { Protected declarations }
  public
    { Public declarations }

     procedure   BrStepIt(Value : LongInt = 1);
     constructor Create(AOwner : TComponent); override;
     destructor  Destroy;
  published
    { Published declarations }
    property BrDataSet         : TDataSet read gDataSet  write SetDataSet;
    property BrProcessMessages : Boolean  read gSnProMes write gSnProMes;

  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvGauge]);
end;

constructor TBrvGauge.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
end;

destructor TBrvGauge.Destroy;
begin
      inherited destroy;
end;

procedure TBrvGauge.BrStepIt(Value : LongInt = 1);
begin
      StepIt;

      if gSnProMes then
      begin
            Application.ProcessMessages;
            Refresh;
      end;
end;

procedure TBrvGauge.SetDataSet(Value : TDataSet);
begin
      Min      := 0;
      gDataSet := Value;

      if gDataSet.Active then
      begin
            try
                if (gDataSet is TQuery) and
                   (TQuery(gDataSet).Database.DriverName = 'ORACLE') then
                begin
                      gDataSet.Last;
                      gDataSet.First;
                end;
                Max := gDataSet.RecordCount;
            except
                gDataSet.Close;
                gDataSet.Open;
            end;
      end else
      begin
            Max := 0;
      end;
end;

end.
