unit Unit62;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, DB, DBTables, Grids, DBGrids, Buttons;

type
  TForm62 = class(TForm)
    DBGrid1: TDBGrid;
    Query1: TQuery;
    DataSource1: TDataSource;
    Button1: TButton;
    memo1: TMemo;
    BitBtn1: TBitBtn;
    procedure Button1Click(Sender: TObject);
    procedure BitBtn1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form62: TForm62;

implementation

uses Unit2;

{$R *.dfm}

procedure TForm62.Button1Click(Sender: TObject);
begin
    query1.Active := false;
    query1.SQL.Text := memo1.Text;
    query1.Active := true;
end;

procedure TForm62.BitBtn1Click(Sender: TObject);
begin
    query1.Active := false;
    query1.SQL.Text := memo1.Text;
    query1.ExecSQL;
end;

end.
