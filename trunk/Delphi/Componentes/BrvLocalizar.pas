unit BrvLocalizar;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, DB,
  Variants;

type
  TBrvLocalizar = class(TComponent)
  private
    procedure PLocalizar(DtSet: TDataSet; Params: TLocateOptions);
  public
    procedure Execute(const DataSetAtual: TDataSet);
  end;

procedure Register;

implementation

uses BrvLocalizarForm;

var
  FVariavel: String;
  FValores: Variant;
  QtTermo: Integer;

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvLocalizar]);
end;

procedure TBrvLocalizar.Execute(const DataSetAtual: TDataSet);
var FrmLocalizar: TFrmLocalizar;
    Idx: Integer;
begin
      FrmLocalizar        := TFrmLocalizar.Create(self);
      FrmLocalizar.Campos := TStringList.Create;
      FrmLocalizar.CarregarStringGrid(DataSetAtual);
      FVariavel := '';
      FValores := '';
      QtTermo := 0;
      if FrmLocalizar.ShowModal = mrOK then
      begin
            for Idx := 1 to FrmLocalizar.StringGrid1.RowCount do
            begin
                  if FrmLocalizar.StringGrid1.Cells[1, Idx] <> '' then
                  begin
                        inc(QtTermo);
                  end;
            end;

            if QtTermo > 0 then
            begin
                  FValores := VarArrayCreate([0, QtTermo-1], varVariant);
                  QtTermo  := 0;
                  for Idx  := 1 to FrmLocalizar.StringGrid1.RowCount do
                  begin
                        if (FrmLocalizar.StringGrid1.Cells[0, Idx] <> '') and
                          (FrmLocalizar.StringGrid1.Cells[1, Idx] <> '') then
                        begin
                              if FVariavel = '' then
                              begin
                                    FVariavel := FrmLocalizar.Campos[Idx]
                              end else
                              begin
                                    FVariavel := FVariavel + ';' + FrmLocalizar.Campos[Idx];
                              end;

                              FValores[QtTermo] := FrmLocalizar.StringGrid1.Cells[1, Idx];
                              inc(QtTermo);
                        end;
                  end;

                  if (FrmLocalizar.CheckBox1.Checked) and
                     (FrmLocalizar.CheckBox2.Checked) then
                  begin
                         PLocalizar(DataSetAtual, [loPartialKey, loCaseInsensitive])
                  end
                  else if FrmLocalizar.CheckBox1.Checked then
                       begin
                             PLocalizar(DataSetAtual, [loPartialKey])
                       end
                  else if FrmLocalizar.CheckBox1.Checked then
                       begin
                             PLocalizar(DataSetAtual, [loCaseInsensitive])
                       end
                  else begin
                             PLocalizar(DataSetAtual, []);
                       end
                  ;
            end;
      end;
      FrmLocalizar.Free;
end;

procedure TBrvLocalizar.PLocalizar(DtSet: TDataSet; Params: TLocateOptions);
begin
      if QtTermo > 1 then
      begin
            if not DtSet.Locate(FVariavel, FValores, Params) then
            begin
                  MessageDlg('Registro não existe.', mtInformation, [mbOk], 0);
            end;
      end else
      begin
            if not DtSet.Locate(FVariavel, FValores[0], Params) then
            begin
                  MessageDlg('Registro não existe.', mtInformation, [mbOk], 0);
            end;
      end;
end;

end.
