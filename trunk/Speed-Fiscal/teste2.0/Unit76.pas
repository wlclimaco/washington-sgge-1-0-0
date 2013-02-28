unit Unit76;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, DBCtrls, DB, DBTables;

type
  TForm76 = class(TForm)
    Button1: TButton;
    DataSource1: TDataSource;
    Query1: TQuery;
    Edit1: TEdit;
    Edit2: TEdit;
    DBText1: TDBText;
    procedure Edit1Exit(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form76: TForm76;

implementation

uses Unit2;

{$R *.dfm}

procedure TForm76.Edit1Exit(Sender: TObject);
begin
query1.SQL.Text := 'select *from produtos p where codpro = '+Edit1.Text;
query1.Active := true;
end;

end.
