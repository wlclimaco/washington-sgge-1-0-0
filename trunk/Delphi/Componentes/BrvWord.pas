unit BrvWord;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, Db, ComObj, ComCtrls, ExtCtrls, Word2000, Variants;
type
  TBrvWord = class(TComponent)
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

const
     wdLineStyleSingle  = $00000001;
     wdBorderTop        = $FFFFFFFF;
     wdBorderLeft       = $FFFFFFFE;
     wdBorderBottom     = $FFFFFFFD;
     wdBorderRight      = $FFFFFFFC;
     wdBorderHorizontal = $FFFFFFFB;
     wdBorderVertical   = $FFFFFFFA;


procedure Register;
begin
  RegisterComponents('Bravo Exporta', [TBrvWord]);
end;

constructor TBrvWord.Create(AOwner: TComponent);
begin
      inherited;
end;

procedure TBrvWord.Execute(pStlColuna : TStrings; pNmFile : String;
                           pFrmProgre : TForm);
var lNrField  : Integer;
    lAplWord  : OleVariant;
    lTable    : OleVariant;
    lNrLinha  : Integer;
begin
      if gDsDatSet = nil then
      begin
            raise Exception.Create(Name + ': DataSet não definido');
      end;

      try
          try
              lAplWord := GetActiveOleObject('Word.Application');
          except
              try
                  lAplWord := CreateOleObject('Word.Application');
              except
                  ShowMessage('Não foi possível abrir o Word.');
                  raise;
              end;
          end;

          try
              if (gDsDatSet.FieldCount - 1) > 0 then
              begin
                    // Novo documento
                    lAplWord.Documents.Add;
                    // Adiciona tabela
                    lTable := lAplWord.ActiveDocument.Tables.Add(lAplWord.Selection.Range,
                                             gDsDatSet.RecordCount + 1, pStlColuna.Count);

                    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                    // Criando bordas na tabela
                    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                    lTable.Borders.Item(wdBorderLeft).LineStyle       := wdLineStyleSingle;
                    lTable.Borders.Item(wdBorderRight).LineStyle      := wdLineStyleSingle;
                    lTable.Borders.Item(wdBorderVertical).LineStyle   := wdLineStyleSingle;

                    lTable.Borders.Item(wdBorderTop).LineStyle        := wdLineStyleSingle;
                    lTable.Borders.Item(wdBorderBottom).LineStyle     := wdLineStyleSingle;
                    lTable.Borders.Item(wdBorderHorizontal).LineStyle := wdLineStyleSingle;
                    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                    // Cabeçalho
                    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                    for lNrField := 0 to gDsDatSet.FieldCount - 1 do
                    begin
                          if (Pos(gDsDatSet.Fields[lNrField].FieldName,
                                                                 pStlColuna.Text) > 0) and
                             (gDsDatSet.Fields[lNrField].AsString <> '') then
                          begin
                                lAplWord.Selection.TypeText(UpperCase(
                                          gDsDatSet.Fields.Fields[lNrField].DisplayName));
                                lAplWord.Selection.MoveRight(12);
                          end;
                    end;

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

                          for lNrField := 0 to gDsDatSet.FieldCount - 1 do
                          begin
                                if (Pos(gDsDatSet.Fields[lNrField].FieldName,
                                                                 pStlColuna.Text) > 0) and
                                   (gDsDatSet.Fields[lNrField].AsString <> '') then
                                begin
                                      lAplWord.Selection.TypeText(
                                              gDsDatSet.Fields.Fields[lNrField].AsString);
                                      lAplWord.Selection.MoveRight(12);
                                end;
                          end;

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

                    lAplWord.ActiveDocument.SaveAs(pNmFile);
              end;
          finally
              lAplWord.ActiveDocument.Close(0);
          end;
      finally
          lAplWord.Quit;
     end;
end;

end.

