unit Unit118;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, DB, DBTables;

type
  TForm118 = class(TForm)
    Panel1: TPanel;
    Button1: TButton;
    Button2: TButton;
    Query1: TQuery;
    Query2: TQuery;
    procedure Button2Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form118: TForm118;

implementation

uses Unit2;

{$R *.dfm}

procedure TForm118.Button2Click(Sender: TObject);
begin
    QUERY1.Active := False;
    QUERY1.SQL.Text := 'SELECT *FROM NFentradas ';
    QUERY1.Active := tRUE;
    while(not QUERY1.Eof)do
    begin
       QUERY2.SQL.Text := 'update  titulospagar2 set dtlancamento = '''+FormatDateTime('mm/DD/yyyy',QUERY1.fieldbyname('dtentrada').AsDateTime)+'''  where dcnumero = '+QUERY1.fieldbyname('dcnumero').AsString+' and dcserie = '''+IntToStr(QUERY1.fieldbyname('dcnumero').AsInteger)+''' and dcordem = '''+QUERY1.fieldbyname('dcordem').AsString+''' and dctipo = '''+QUERY1.fieldbyname('dctipo').AsString+'''';
      // showMessage(QUERY2.SQL.Text);
       QUERY2.ExecSQL;
       QUERY1.Next;
    end;
end;

end.
