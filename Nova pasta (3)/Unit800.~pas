unit Unit800;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBUpdateSQL, IBQuery, StdCtrls, Buttons,
  U_DBBButton, Grids, DBGrids, ExtCtrls, ActCustomEdit, ActMask,
  ActDateEdit;

type
  TfrmlancVendasVend = class(TForm)
    Panel1: TPanel;
    TXTDATA: TActDateEdit;
    Panel2: TPanel;
    Panel3: TPanel;
    Panel4: TPanel;
    DBGrid1: TDBGrid;
    IBQuery1: TIBQuery;
    IBUpdateSQL1: TIBUpdateSQL;
    DataSource1: TDataSource;
    Label1: TLabel;
    Label2: TLabel;
    IBQuery2: TIBQuery;
    DBButton4: TDBButton;
    SpeedButton1: TSpeedButton;
    DBButton1: TDBButton;
    DBGrid2: TDBGrid;
    IBQuery3: TIBQuery;
    DataSource2: TDataSource;
    Label3: TLabel;
    procedure DBGrid1KeyPress(Sender: TObject; var Key: Char);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure TXTDATAExit(Sender: TObject);
    procedure DBGrid1CellClick(Column: TColumn);
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frmlancVendasVend: TfrmlancVendasVend;
  CONT : Integer;
implementation

uses Unit1;

{$R *.dfm}

procedure TfrmlancVendasVend.DBGrid1KeyPress(Sender: TObject; var Key: Char);
var

soma :Double;

begin

 if(dbGrid1.Fields[0].IsNull )THEN
  begin
    dbGrid1.Fields[0].AsInteger := CONT;
    dbGrid1.Fields[3].AsDateTime := StrToDateTime(TXTDATA.Text);
    CONT := CONT +1 ;

  end;

end;
procedure TfrmlancVendasVend.FormClose(Sender: TObject; var Action: TCloseAction);
begin
IBQuery1.Transaction.CommitRetaining;
end;

procedure TfrmlancVendasVend.TXTDATAExit(Sender: TObject);
begin
IBQuery1.Active := False;

IBQuery1.ParamByName('DATA').AsDate := StrToDate(TXTDATA.Text);
IBQuery1.Active := TRUE;
IBQuery1.Edit;



end;

procedure TfrmlancVendasVend.DBGrid1CellClick(Column: TColumn);
begin
 if(NOT dbGrid1.Fields[1].IsNull )THEN
   BEGIN
    IBQuery2.Active := False;
    IBQuery2.ParamByName('ID').AsInteger := dbGrid1.Fields[1].AsInteger;
    IBQuery2.Active := TRUE;
    Label2.Caption :=  IBQuery2.FIELDBYNAME('NOME').AsString;
   end;
end;

procedure TfrmlancVendasVend.SpeedButton1Click(Sender: TObject);
begin
IBQuery1.Transaction.CommitRetaining;
end;

end.
