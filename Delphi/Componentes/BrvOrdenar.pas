unit BrvOrdenar;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, DB,
  BrvOrdenarForm;

type
  TBrvOrdenar = class(TComponent)
  private
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    OrderFieldName: string;
    OrderDisplayName: string;
    function Execute(const DataSetAtual: TDataSet; pDsOrdAtu : String): boolean;
  published
    { Published declarations }
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvOrdenar]);
end;

function TBrvOrdenar.Execute(const DataSetAtual: TDataSet; pDsOrdAtu : String): boolean;
var FrmOrdenar: TFrmOrdenar;
    i: Integer;
    NmField : String;
begin
      FrmOrdenar := TFrmOrdenar.Create(self);
      FrmOrdenar.CarregarListBox(DataSetAtual, pDsOrdAtu);

      if FrmOrdenar.ShowModal = mrOK then
      begin
            OrderFieldName   := '';
            OrderDisplayName := '';
            Result           := True;

            for i := 0 to FrmOrdenar.LbxOrdenar2.Count - 1 do
            begin
                  NmField := FrmOrdenar.LbxOrdenar2[i];

                  if OrderFieldName = '' then
                  begin
                        OrderFieldName   := NmField;
                        OrderDisplayName := FrmOrdenar.LbxOrdenar1.Items[i];
                  end else
                  begin
                        OrderFieldName   := OrderFieldName   + ','  + NmField;
                        OrderDisplayName := OrderDisplayName + ', ' +
                                                          FrmOrdenar.LbxOrdenar1.Items[i];
                  end;
            end;
      end else
      begin
            Result := False;
      end;

      FrmOrdenar.LbxDisponiveis2.Free;
      FrmOrdenar.LbxOrdenar2.Free;
      FrmOrdenar.Free;
end;

end.
