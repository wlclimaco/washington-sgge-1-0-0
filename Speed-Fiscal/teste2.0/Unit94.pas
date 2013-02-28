unit Unit94;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, TeEngine, Series, DB, DBTables, ExtCtrls, TeeProcs, Chart,
  DbChart;

type
  TForm94 = class(TForm)
    Query1: TQuery;
    procedure FormActivate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form94: TForm94;

implementation

uses Unit2;

{$R *.dfm}

procedure TForm94.FormActivate(Sender: TObject);
begin
//Query1.Active := True;
end;

end.
