unit BrvHtml;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, Db, ComObj, ComCtrls, ExtCtrls, Word2000, Variants;
type
  TBrvHtml = class(TComponent)
  private
    { Private declarations }
    gDsDatSet  : TDataSet;
    gDsProBar  : TProgressBar;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    procedure   Execute(pStlColuna : TStrings; pNmFile : String;
                        pFrmProgre : TForm);
  published
    { Published declarations }
    property BrProgressBar : TProgressBar  read gDsProBar  write gDsProBar;
    property BrDataSet     : TDataSet      read gDsDatSet  write gDsDatSet;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Exporta', [TBrvHtml]);
end;

constructor TBrvHtml.Create(AOwner: TComponent);
begin
      inherited;
end;

procedure TBrvHtml.Execute(pStlColuna : TStrings; pNmFile : String;
                           pFrmProgre : TForm);
var lNrField  : Integer;
    lDsHtml   : TStrings;
    lNrLinha  : Integer;
begin
      if gDsDatSet = nil then
      begin
            raise Exception.Create(Name + ': DataSet não definido');
      end;

      try
          lDsHtml := TStringList.Create;

          lDsHtml.Add('<html>');
          lDsHtml.Add(#9 + '<body>');
          lDsHtml.Add(#9#9 + '<table width=100% border="1" cellpadding="3" ' +
                                                  'cellspacing="0" bordercolor="#0000">');

          if (gDsDatSet.FieldCount - 1) > 0 then
          begin
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                // Cabeçalho
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                lDsHtml.Add(#9#9#9 + '<tr>');
                for lNrField := 0 to gDsDatSet.FieldCount - 1 do
                begin
                      if (Pos(gDsDatSet.Fields[lNrField].FieldName,
                                                             pStlColuna.Text) > 0) and
                         (gDsDatSet.Fields[lNrField].AsString <> '') then
                      begin
                            lDsHtml.Add(#9#9#9#9 + '<td>' +
                                UpperCase(gDsDatSet.Fields.Fields[lNrField].DisplayName) +
                                '</td>');
                      end;
                end;
                lDsHtml.Add(#9#9#9 + '</tr>');

                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                // Imprimindo Linhas
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                if gDsProBar <> nil then
                begin
                      gDsProBar.Position := 0;
                      gDsProBar.Max := gDsDatSet.RecordCount;
                end;

                lNrLinha  := 1;
                while not gDsDatSet.Eof do
                begin
                      inc(lNrLinha);
                      lDsHtml.Add(#9#9#9 + '<tr>');

                      for lNrField := 0 to gDsDatSet.FieldCount - 1 do
                      begin
                            if (Pos(gDsDatSet.Fields[lNrField].FieldName,
                                                                 pStlColuna.Text) > 0) and
                               (gDsDatSet.Fields[lNrField].AsString <> '') then
                            begin
                                  lDsHtml.Add(#9#9#9#9 + '<td>' +
                                              gDsDatSet.Fields.Fields[lNrField].AsString +
                                              '</td>');
                            end;
                      end;
                      lDsHtml.Add(#9#9#9 + '</tr>');

                      if gDsProBar <> nil then
                      begin
                            gDsProBar.StepIt;
                            Application.ProcessMessages;
                      end;

                      gDsDatSet.Next;
                end;

                if pFrmProgre <> nil then
                begin
                      pFrmProgre.Caption := 'Salvando arquivo. Aguarde ...';
                      pFrmProgre.Refresh;
                end;

                lDsHtml.Add(#9#9 + '</table>');
                lDsHtml.Add(#9 + '</body>');
                lDsHtml.Add('</html>');
                lDsHtml.SaveToFile(pNmFile);
          end;
      finally
          FreeAndNil(lDsHtml);
     end;
end;

end.

