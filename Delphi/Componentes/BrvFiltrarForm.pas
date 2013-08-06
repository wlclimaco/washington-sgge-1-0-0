unit BrvFiltrarForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, Grids, ToolWin, ComCtrls, DB, BrvBitBtn;

type
  TFrmFiltrar = class(TForm)
    CoolBar1: TCoolBar;
    StringGrid1: TStringGrid;
    CheckBox1: TCheckBox;
    CheckBox2: TCheckBox;
    BitBtn1: TBrvBitBtn;
    BitBtn2: TBrvBitBtn;
  public
    Campos  : TStrings;
    TpCampo : TStrings;
    procedure CarregarStringGrid(const DataSet1: TDataSet);
  end;

var
  FrmFiltrar : TFrmFiltrar;
  DataSet_Aux: TDataSet;

implementation

{$R *.DFM}

procedure TFrmFiltrar.CarregarStringGrid(const DataSet1: TDataSet);
var
  Idx: integer;
begin
   DataSet_Aux := DataSet1;
  Campos      := TStringList.Create;
  TpCampo     := TStringList.Create;

  StringGrid1.Cells[0, 0] := 'Campos:';
  StringGrid1.Cells[1, 0] := 'De:';
  StringGrid1.Cells[2, 0] := 'Até:';
  Campos.Add('');
  TpCampo.Add('');

  for Idx := 0 to DataSet1.FieldCount - 1 do
  begin
        if (DataSet1.Fields[Idx].Visible) and
           (DataSet1.Fields[Idx].FieldKind = fkData) and
           (Pos('_', DataSet1.Fields[idx].FieldName) = 0) then
        begin
              StringGrid1.Cells[0, StringGrid1.RowCount - 1] :=
                                                         DataSet1.Fields[Idx].DisplayName;
              Campos.Add(DataSet1.Fields[Idx].FieldName);

              case DataSet1.Fields[Idx].DataType of
                   FtInteger  : TpCampo.Add('Integer');
                   ftDate     : TpCampo.Add('Date');
                   ftSmallint : TpCampo.Add('SmallInt');
                   ftFloat    : TpCampo.Add('Float');
                   ftCurrency : TpCampo.Add('Currency');
                   ftTime     : TpCampo.Add('Time');
                   ftDateTime : TpCampo.Add('DateTime');
                   ftString   : TpCampo.Add('String');
                   ftBlob     : TpCampo.Add('Blob');
                   ftMemo     : TpCampo.Add('Memo');
                   ftGraphic  : TpCampo.Add('Grafic');
                   else         TpCampo.Add('Desconhecido');
              end;

              StringGrid1.RowCount := StringGrid1.RowCount + 1;
        end;
  end;

  StringGrid1.RowCount := StringGrid1.RowCount - 1;
end;

end.











