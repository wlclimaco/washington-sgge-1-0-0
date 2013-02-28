unit Unit102;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Mask, DBCtrls, DB, DBTables, ExtCtrls, Buttons,
  U_DBBButton;

type
  TForm102 = class(TForm)
    Panel2: TPanel;
    Panel3: TPanel;
    Query1: TQuery;
    UpdateSQL1: TUpdateSQL;
    Label1: TLabel;
    DataSource1: TDataSource;
    Label2: TLabel;
    DBEdit2: TDBEdit;
    Label3: TLabel;
    DBEdit3: TDBEdit;
    Label4: TLabel;
    DBEdit4: TDBEdit;
    Label5: TLabel;
    DBEdit5: TDBEdit;
    Label6: TLabel;
    DBEdit6: TDBEdit;
    Label7: TLabel;
    DBEdit7: TDBEdit;
    Label8: TLabel;
    DBEdit8: TDBEdit;
    Label9: TLabel;
    DBEdit9: TDBEdit;
    Edit1: TEdit;
    Bevel1: TBevel;
    Label28: TLabel;
    Panel1: TPanel;
    SpeedButton1: TSpeedButton;
    DBButton1: TDBButton;
    DBButton2: TDBButton;
    DBButton3: TDBButton;
    DBButton4: TDBButton;
    DBButton5: TDBButton;
    DBButton6: TDBButton;
    DBNavigator1: TDBNavigator;
    procedure Edit1Exit(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form102: TForm102;

implementation

uses Unit2;

{$R *.dfm}

procedure TForm102.Edit1Exit(Sender: TObject);
begin
   query1.SQL.Text := 'select *from produtos where codpro = '+Edit1.Text;
   query1.Active := true;
end;

procedure TForm102.SpeedButton1Click(Sender: TObject);
begin
Close();
end;

end.
