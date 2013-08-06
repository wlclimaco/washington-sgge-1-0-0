unit BrvExcel;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, Db, ComObj, ComCtrls, ExtCtrls, BrvAlertProgress;
type
  TBrvExcel = class(TComponent)
  private
    { Private declarations }
    gDsDatSet : TDataSet;
    gDsProBar : TProgressBar;
    gPbProBar : TBrvAlertProgress;
    gSDArquiv : TSaveDialog;
    gNmPadrao : String;
    gSnSalvCm : Boolean;
    gSnAlert  : Boolean;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    procedure   Execute(lStlColuna : TStrings; pNmFile : String; pFrmProgre : TForm);
  published
    { Published declarations }
    property BrProgressBar   : TProgressBar  read gDsProBar  write gDsProBar;
    property BrDataSet       : TDataSet      read gDsDatSet  write gDsDatSet;
    property BrSalvarComo    : Boolean       read gSnSalvCm  write gSnSalvCm;
    property BrNomePadrao    : String        read gNmPadrao  write gNmPadrao;
    property BrAlertProgress : Boolean       read gSnAlert   write gSnAlert;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Exporta', [TBrvExcel]);
end;

constructor TBrvExcel.Create(AOwner: TComponent);
begin
      inherited;
      gSnAlert  := false
end;

procedure TBrvExcel.Execute(lStlColuna : TStrings; pNmFile : String;
                            pFrmProgre : TForm);
var lNrField  : Integer;
    lAplExcel  : OleVariant;
    lNoColExc  : Integer;
    lNoLinExc  : Integer;
begin
      if gDsDatSet = nil then
      begin
            raise Exception.Create(Name + ': DataSet não definido');
      end;

      try
          try
              lAplExcel := GetActiveOleObject('Excel.Application');
          except
              try
                  lAplExcel := CreateOleObject('Excel.Application');
              except
                  ShowMessage('Não foi possível abrir o Excel.');
                  raise;
              end;
          end;

          if (gDsDatSet.FieldCount - 1) > 0 then
          begin
                lAplExcel.WorkBooks.Add(1);
                lNoColExc := 1;
                lNoLinExc := 1;

                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                // Imprimindo Cabeçalho
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                for lNrField := 0 to gDsDatSet.FieldCount - 1 do
                begin
                      if (Pos(gDsDatSet.Fields[lNrField].FieldName, lStlColuna.Text) > 0) then
                      begin
                            lAplExcel.WorkSheets[1].Cells[01, lNoColExc]:=
                                 UpperCase(gDsDatSet.Fields.Fields[lNrField].DisplayLabel);
                      end;
                      if Pos(gDsDatSet.Fields[lNrField].FieldName,lStlColuna.Text) > 0 then
                               inc(lNoColExc);

                end;

                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                // Imprimindo Linhas
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
               {if gDsProBar <> nil then
                begin
                      gDsProBar.Position := 0;
                      gDsProBar.Max := gDsDatSet.RecordCount;
                end;}

                if BrAlertProgress then
                begin
                      gPbProBar := TBrvAlertProgress.Create(Self);
                      gPbProBar.BrCaption  := (Owner as TForm).Caption;
                      gPbProBar.BrProcesso := 'Gerando planilha! Aguarde...';
                      gPbProBar.ShowAlert;
                      gPbProBar.BrQuery    := gDsDatSet;
                end;

                while not gDsDatSet.Eof do
                begin
                      lNoColExc := 1;
                      inc(lNoLinExc);

                      for lNrField := 0 to gDsDatSet.FieldCount - 1 do
                      begin
                            if (Pos(gDsDatSet.Fields[lNrField].FieldName,
                                                                 lStlColuna.Text) > 0) and
                               (gDsDatSet.Fields[lNrField].AsString <> '') then
                            begin
                                  lAplExcel.WorkSheets[1].Cells[lNoLinExc, lNoColExc] :=
                                               gDsDatSet.Fields.Fields[lNrField].AsString;
                            end;
                            if Pos(gDsDatSet.Fields[lNrField].FieldName,lStlColuna.Text) > 0 then
                               inc(lNoColExc);
                      end;

                     {if gDsProBar <> nil then
                      begin
                            gDsProBar.StepIt;
                            Application.ProcessMessages;
                      end;}
                      if BrAlertProgress then
                      begin
                            gPbProBar.BrStepIt;
                      end;

                      gDsDatSet.Next;
                end;

               {if pFrmProgre <> nil then
                begin
                      pFrmProgre.Caption := 'Salvando arquivo. Aguarde ...';
                      pFrmProgre.Refresh;
                end;}

                if BrSalvarComo then
                begin
                      try
                          gSDArquiv := TSaveDialog.Create(Self);
                          gSDArquiv.DefaultExt  := 'xls';
                          gSDArquiv.Filter      := 'Arquivo Excel|*.xls|Todos os Arquivos|*.*';
                          gSDArquiv.FilterIndex := 0;
                          gSDArquiv.FileName    := BrNomePadrao;

                          if (gSDArquiv.Execute()) then
                          begin
                                lAplExcel.WorkSheets[1].SaveAs(gSDArquiv.FileName);
                          end;
                      finally
                          FreeAndNil(gSDArquiv);
                      end;
                end else
                begin
                     if (Trim(pNmFile) = '') then
                     begin
                           lAplExcel.WorkSheets[1].SaveAs('c:\plan.xls');
                     end else
                     begin
                           lAplExcel.WorkSheets[1].SaveAs(pNmFile);
                     end;
                end;
          end;
     finally
          lAplExcel.Quit;
     end;
end;

end.

