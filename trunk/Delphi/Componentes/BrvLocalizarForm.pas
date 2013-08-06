unit BrvLocalizarForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, Grids, ToolWin, ComCtrls, DB, BrvBitBtn;

type
  TFrmLocalizar = class(TForm)
    CoolBar1: TCoolBar;
    StringGrid1: TStringGrid;
    CheckBox1: TCheckBox;
    CheckBox2: TCheckBox;
    BitBtn1: TBrvBitBtn;
    BitBtn2: TBrvBitBtn;
  private
    { Private declarations }
  public
    Campos: TStrings;
    procedure CarregarStringGrid(const DataSet1: TDataSet);
    { Public declarations }
  end;

var
  FrmLocalizar: TFrmLocalizar;

implementation

{$R *.DFM}

procedure TFrmLocalizar.CarregarStringGrid(const DataSet1: TDataSet);
var
  j: integer;
begin
   StringGrid1.Cells[0, 0] := 'Campos:';
   StringGrid1.Cells[1, 0] := 'Valores:';
   Campos.Add('');

   For j:=0 to DataSet1.FieldCount-1
   do begin
      if (DataSet1.Fields[j].Visible) and (DataSet1.Fields[j].FieldKind = fkData)
      then begin
         StringGrid1.Cells[0, StringGrid1.RowCount - 1] := DataSet1.Fields[j].DisplayName;
         Campos.Add(DataSet1.Fields[j].FieldName);
         StringGrid1.RowCount := StringGrid1.RowCount + 1;
      end;
   end;

   StringGrid1.RowCount := StringGrid1.RowCount - 1;
end;

end.
