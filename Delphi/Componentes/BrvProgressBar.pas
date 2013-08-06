unit BrvProgressBar;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, ComCtrls, DB,
  BrvQuery, DBClient, SqlExpr;

type
  TBrvProgressBar = class(TProgressBar)
  private
    { Private declarations }
    FQuery: TDataSet;
    procedure SetQuery(Value : TDataSet);
  protected
    { Protected declarations }
  public
    { Public declarations }
    SnProMes : Boolean;
    procedure StepIt; virtual;
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
  published
    { Published declarations }
    property BrQuery: TDataSet read FQuery write SetQuery;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvProgressBar]);
end;

constructor TBrvProgressBar.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
end;

destructor TBrvProgressBar.Destroy;
begin
      inherited destroy;
end;

procedure TBrvProgressBar.StepIt;
begin
      inherited StepIt;

      if SnProMes then
      begin
            Application.ProcessMessages;
            Refresh;
      end;
end;

procedure TBrvProgressBar.SetQuery(Value : TDataSet);
begin
      FQuery := Value;

      if FQuery.Active then
      begin
            try
                if (FQuery is TBrvQuery) and
                   (TBrvQuery(FQuery).Database.DriverName = 'ORACLE') then
                begin
                      FQuery.Last;
                      FQuery.First;
                end;
                Max := FQuery.RecordCount;
            except
                FQuery.Close;
                FQuery.Open;
            end;
      end else
      begin
            Max := 0;
      end;
end;

end.
