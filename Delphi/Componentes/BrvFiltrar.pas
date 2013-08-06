unit BrvFiltrar;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, DB;

type
  TBrvFiltrar = class(TComponent)
  private
    function VerificarTipoCampo(TpCampo : String; VrCampo : String) : String;
  public
    WhereFieldName: string;
    WhereDisplayName: string;
    function Execute(const DataSetAtual: TDataSet): boolean;
  end;

procedure Register;

implementation

uses BrvFiltrarForm;

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvFiltrar]);
end;

function TBrvFiltrar.Execute(const DataSetAtual: TDataSet): boolean;
var
  frmFiltrar: TfrmFiltrar;
  i, j: Integer;
  NmField : String;
begin
  frmFiltrar := TfrmFiltrar.Create(self);
  frmFiltrar.CarregarStringGrid(DataSetAtual);

  if frmFiltrar.ShowModal = mrOK then
  begin
        WhereFieldName := '';
        WhereDisplayName := '';
        Result := True;

        for i := 1 to frmFiltrar.StringGrid1.RowCount - 1 do
        begin
              j := 0;

              NmField := frmFiltrar.Campos[i];

              if  Pos('_', NmField) > 0 then
              begin
                    NmField := StringReplace(NmField , '_', '.', [rfReplaceAll]);
              end else
              begin
                    NmField := 'A.' + NmField;
              end;

              if WhereFieldName = '' then
              begin
                    if (frmFiltrar.StringGrid1.Cells[j, i] <> '') and
                       (frmFiltrar.StringGrid1.Cells[j + 1, i] <> '') then
                    begin
                          WhereFieldName := NmField + ' >= ' +
                               '"' +
                               VerificarTipoCampo(FrmFiltrar.TpCampo[i],
                                       frmFiltrar.StringGrid1.Cells[j + 1, i]) +
                               '"';

                          WhereDisplayName :=
                                   frmFiltrar.StringGrid1.Cells[j, i] + ' >= ' +
                                   frmFiltrar.StringGrid1.Cells[j + 1, i];

                          if frmFiltrar.StringGrid1.Cells[j + 2, i] <> '' then
                          begin
                                WhereFieldName := WhereFieldName + ' and '  +
                                     NmField + ' <= ' + '"'    +
                                     VerificarTipoCampo(FrmFiltrar.TpCampo[i],
                                       frmFiltrar.StringGrid1.Cells[j + 2, i]) +
                                     '"';

                                WhereDisplayName := WhereDisplayName + ', ' +
                                     frmFiltrar.StringGrid1.Cells[j, i] +
                                     ' <= ' +
                                     frmFiltrar.StringGrid1.Cells[j + 2, i];
                          end;
                    end;
              end else
              begin
                    if (frmFiltrar.StringGrid1.Cells[j, i] <> '')     and
                       (frmFiltrar.StringGrid1.Cells[j + 1, i] <> '') then
                    begin
                          WhereFieldName := WhereFieldName       + ' and ' +
                                   NmField + ' >= ' + '"' +
                                   VerificarTipoCampo(FrmFiltrar.TpCampo[i],
                                       frmFiltrar.StringGrid1.Cells[j + 1, i]) +
                                   '"';

                          WhereDisplayName := WhereDisplayName + ', ' +
                                   frmFiltrar.StringGrid1.Cells[j, i] + ' >= ' +
                                   frmFiltrar.StringGrid1.Cells[j + 1, i];

                          if frmFiltrar.StringGrid1.Cells[j + 2, i] <> '' then
                          begin
                                WhereFieldName := WhereFieldName + ' and ' +
                                     NmField + ' <= ' + '"' +
                                     VerificarTipoCampo(FrmFiltrar.TpCampo[i],
                                       frmFiltrar.StringGrid1.Cells[j + 2, i]) +
                                     '"';

                                WhereDisplayName := WhereDisplayName + ', ' +
                                     frmFiltrar.StringGrid1.Cells[j, i] +
                                     ' <= ' +
                                     frmFiltrar.StringGrid1.Cells[j + 2, i];
                          end;
                    end;
              end;
        end;
  end else
  begin
        Result := False;
  end;

  FrmFiltrar.Campos.Free;
  FrmFiltrar.TpCampo.Free;
  FrmFiltrar.Free;
end;

function TBrvFiltrar.VerificarTipoCampo(TpCampo : String; VrCampo : String) : String;
var DtDia : String;
    DtMes : String;
begin
      if  (TpCampo = 'DateTime') or (TpCampo = 'Date') then
      begin
            DtDia := VrCampo[1] + VrCampo[2];
            DtMes := VrCampo[4] + VrCampo[5];
            
            VrCampo[1] := DtMes[1];
            VrCampo[2] := DtMes[2];

            VrCampo[4] := DtDia[1];
            VrCampo[5] := DtDia[2];
      end;

      Result  := VrCampo;
end;

end.
