unit Unit80;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids, StdCtrls, ExtCtrls, Buttons;

type
  TForm80 = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    Panel3: TPanel;
    Label1: TLabel;
    DBGrid1: TDBGrid;
    SpeedButton1: TSpeedButton;
    procedure DBGrid1DblClick(Sender: TObject);
    procedure FormActivate(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form80: TForm80;

implementation

uses Unit2;

{$R *.dfm}

procedure TForm80.DBGrid1DblClick(Sender: TObject);
var
  dcnumero,parcela : Integer;
  dcserie,dcordem,dctipo : string;
begin

dcnumero  :=  DBGrid1.Fields[0].AsInteger;
dcserie   := DBGrid1.Fields[1].AsString;
dcordem   := DBGrid1.Fields[2].AsString;
dctipo    := DBGrid1.Fields[3].AsString;
parcela := DBGrid1.Fields[4].AsInteger;

if MessageDlg('Tem Certeza que deseja baixar titulo ????', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
begin
  DataModule2.Query9.SQL.Text := 'update titulospagar2 set tpsituacao = ''P'' where dcnumero = '+IntToStr(dcnumero)+' and dcserie = '''+dcserie+''' and dcordem = '''+dcordem+''' and dctipo = '''+dctipo+''' and parcela = '+IntToStr(parcela);
  DataModule2.Query9.ExecSQL;
end;
  DataModule2.qrytitulospagar.Active := False;
  DataModule2.qrytitulospagar.Active := TRUE;
end;

procedure TForm80.FormActivate(Sender: TObject);
begin
DataModule2.Tbtitulospagar.Active := True;
end;

procedure TForm80.SpeedButton1Click(Sender: TObject);
begin
DataModule2.Tbtitulospagar.Active := True;
end;

end.
