unit UBuscarMovDia;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Mask, DB, IBCustomDataSet, IBQuery, Grids, DBGrids,
  Buttons, ExtCtrls;

type
  TBusMovDiario = class(TForm)
    Panel1: TPanel;
    GroupBox3: TGroupBox;
    SpeedButton1: TSpeedButton;
    Panel2: TPanel;
    DBGrid1: TDBGrid;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    MaskEdit1: TMaskEdit;
    MaskEdit2: TMaskEdit;
    Label1: TLabel;
    Label2: TLabel;
    CheckBox1: TCheckBox;
    procedure SpeedButton1Click(Sender: TObject);
    procedure DBGrid1DblClick(Sender: TObject);
    procedure DBGrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
      DataCol: Integer; Column: TColumn; State: TGridDrawState);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  BusMovDiario: TBusMovDiario;

implementation

uses Unit100;

{$R *.dfm}

procedure TBusMovDiario.SpeedButton1Click(Sender: TObject);
begin
  if (CheckBox1.Checked = false) then
  begin
    IBQuery1.Active := False;
    IBQuery1.SQL.Clear;
    IBQuery1.SQL.Text := 'select * from movimentodiario m where datamovimento between :dtinicial and :dtfinal';
    IBQuery1.ParamByName('dtinicial').AsDate := StrToDate(MaskEdit1.Text);
    IBQuery1.ParamByName('dtfinal').AsDate := StrToDate(MaskEdit2.Text);
    IBQuery1.Active := True;
  end
  else
  begin
    IBQuery1.Active := False;
    IBQuery1.SQL.Clear;
    IBQuery1.SQL.Text := ' select * from movimentodiario';
    IBQuery1.Active := True;
  end;
end;

procedure TBusMovDiario.DBGrid1DblClick(Sender: TObject);
begin
FrmCadMovD.ActResultEdit1.Text := intToStr(dbGrid1.Fields[0].AsInteger);
BusMovDiario.Close;
end;

procedure TBusMovDiario.DBGrid1DrawColumnCell(Sender: TObject;
  const Rect: TRect; DataCol: Integer; Column: TColumn;
  State: TGridDrawState);
begin
 if State = [] then
  begin
    if IBQuery1.RecNo mod 2 = 1 then
      DBGrid1.Canvas.Brush.Color := clAqua
    else
      DBGrid1.Canvas.Brush.Color := clWhite;
  end;
  DBGrid1.DefaultDrawColumnCell(Rect, DataCol, Column, State);
end;

end.
