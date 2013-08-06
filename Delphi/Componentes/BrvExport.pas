unit BrvExport;

interface

uses Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, BrvQuery,
     BrvDicionario, BrvString, Db, DBCtrls, StdCtrls, ComCtrls, BrvClientDataSet,
     BrvExcel, BrvWord, BrvHtml, xmldom, msxmldom, XMLDoc, XMLIntf;

type
  TBrvExport = class(TComponent)
  private
    { Private declarations }
    DlgSave   : TSaveDialog;
    FrmProgre : TForm;
    BarProgre : TProgressBar;
    BrvString : TBrvString;
    DsMemo    : TDbMemo;
    FDsDicion : TBrvDicionario;

    procedure  CriarFormProgresso(QtRegist : Integer);

    function   FormatarLinhaBravo(NrRegist : Integer; StlChave : TStrings;
                                     SnMemo   : String;  DsField  : String;
                                     NmField  : String) : String;
    function ReceberColunasExportacao(var StlColExp: TStrings;
                                          StlColuna: TStrings; var TpExport: Byte;
                                      var TpCarSep : String): Boolean;
    procedure ExportarFormatoBravo(NmTabela: string;
                                   StlColExp: TStrings; DtsDados : TDataSource);
    procedure ExportarFormatoColunar(StlColuna: TStrings; TpCarSep: string;
                                     DtsDados : TDataSource);
    procedure ExportarFormatoLinear(DtsDados: TDataSource; StlColuna: TStrings);
    function FormatarLinhaLinear(NrRegist : Integer; DsField : string;
                                 NmField  : String): String;
    procedure ExportarOutroFormato(CdsDados : TBrvClientDataSet;
                                   StlColuna: TStrings; TpExport : Byte);
    procedure ExportarFormatoExcel(CdsDados: TDataSource; StlColuna: TStrings;
                                   pNmFile : String; pFrmProgre : TForm);
    procedure ExportarFormatoWord(CdsDados: TDataSource; StlColuna: TStrings;
                                  pNmFile : String; pFrmProgre : TForm);
    procedure ExportarFormatoHTML(CdsDados: TDataSource; StlColuna: TStrings;
      pNmFile: String; pFrmProgre: TForm);
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure   Execute(NmTabela : string; Dados : Variant);
    procedure   MontarTreeView(pDsTexto: WideString; pTreView: TTreeView);
    function    ExportarTreeView(pTreView: TTreeView): WideString;
    procedure   XMLToTreeView(pDsTexto: WideString; pTreView: TTreeView);
  published
    { Published declarations }
    property BrDicionario : TBrvDicionario read FDsDicion write FDsDicion;
  end;

procedure Register;

implementation

uses BrvImpExpForm, BrvExportForm;

procedure Register;
begin
      RegisterComponents('Bravo Exporta', [TBrvExport]);
end;

constructor TBrvExport.Create(AOwner: TComponent);
begin
      inherited create(AOwner);
end;

destructor TBrvExport.Destroy;
begin
      inherited  destroy;
end;

procedure TBrvExport.Execute(NmTabela : string; Dados : Variant);
var StlColExp : TStrings;
    StlColuna : TStrings;
    NrField   : Integer;
    TpExport  : Byte;
    TpCarSep  : String;

    CdsDados  : TBrvClientDataSet;
    DtsDados : TDataSource;
begin
      if FDsDicion = nil then
      begin
            raise Exception.Create(
                                'Dicionário de dados não definido. Por favor verifique!');
      end;

      CdsDados := TBrvClientDataSet.Create(Self);
      CdsDados.BrDicionario := BrDicionario;

      StlColExp := TStringList.Create;
      StlColuna := TStringList.Create;

      try
          CdsDados.Data := Dados;

          if  CdsDados.Eof then
          begin
                raise Exception.Create('Não foi encontrado registros para exportação');
          end;

          for NrField := 0 To CdsDados.FieldCount -1 do
          begin
                StlColuna.Add(CdsDados.Fields[NrField].FieldName);
          end;

          if ReceberColunasExportacao(StlColExp, StlColuna, TpExport, TpCarSep) then
          begin
                try
                    DlgSave := TSaveDialog.Create(Self);

                    case TpExport of
                         0..2 : begin
                                      DlgSave.DefaultExt := 'txt';
                                      DlgSave.Filter := 'Arquivo texto|*.txt';
                                end;
                         3    : begin
                                      DlgSave.DefaultExt := 'xls';
                                      DlgSave.Filter := 'Arquivo Excel|*.xls';
                                end;
                         4    : begin
                                      DlgSave.DefaultExt := 'doc';
                                      DlgSave.Filter := 'Arquivo Word|*.doc';
                                end;
                         5    : begin
                                      DlgSave.DefaultExt := 'html';
                                      DlgSave.Filter := 'Arquivo HTML|*.html';
                                end;
                    end;

                    if  DlgSave.Execute then
                    begin
                          try
                              if TpExport < 6 then
                              begin
                                    CriarFormProgresso(CdsDados.RecordCount);

                                    DtsDados          := TDataSource.Create(Self);
                                    DtsDados.DataSet  := CdsDados;
                                    DsMemo.DataSource := DtsDados;
                              end;

                              case TpExport of
                                   0 : ExportarFormatoBravo(NmTabela,
                                                            StlColExp, DtsDados);
                                   1 : ExportarFormatoColunar(StlColExp, TpCarSep,
                                                              DtsDados);
                                   2 : ExportarFormatoLinear(DtsDados,   StlColExp);
                                   3 : ExportarFormatoExcel(DtsDados, StlColExp,
                                                            DlgSave.FileName,
                                                            FrmProgre);
                                   4 : ExportarFormatoWord(DtsDados, StlColExp,
                                                           DlgSave.FileName,
                                                           FrmProgre);
                                   5 : ExportarFormatoHTML(DtsDados, StlColExp,
                                                           DlgSave.FileName,
                                                           FrmProgre);
                                   else ExportarOutroFormato(CdsDados,   StlColExp,
                                                                         TpExport);
                              end;
                          finally
                              if TpExport < 6 then
                              begin
                                    FreeAndNil(DtsDados);
                                    FreeAndNil(FrmProgre);
                              end;
                              MessageDlg('Registros exportados com sucesso!',
                                                                MtInformation, [MbOk], 0);
                          end;
                    end;
                finally
                     FreeAndNil(DlgSave);
                end;
          end;
      finally
          FreeAndNil(CdsDados);
          FreeAndNil(StlColExp);
          FreeAndNil(StlColuna);
      end;
end;

procedure  TBrvExport.ExportarFormatoHTML(CdsDados : TDataSource; StlColuna : TStrings;
                                          pNmFile  : String; pFrmProgre : TForm);
var lHtml : TBrvHTML;
begin
      lHtml := TBrvHtml.Create(Self);
      try
          lHtml.BrProgressBar := BarProgre;
          lHtml.BrDataSet := CdsDados.DataSet;
          lHtml.Execute(StlColuna, pNmFile, pFrmProgre);
      finally
          FreeAndNil(lHtml);
      end;
end;

procedure  TBrvExport.ExportarFormatoWord(CdsDados : TDataSource; StlColuna : TStrings;
                                          pNmFile  : String; pFrmProgre : TForm);
var lWord : TBrvWord;
begin
      lWord := TBrvWord.Create(Self);
      try
          lWord.BrProgressBar := BarProgre;
          lWord.BrDataSet := CdsDados.DataSet;
          lWord.Execute(StlColuna, pNmFile, pFrmProgre);
      finally
          FreeAndNil(lWord);
      end;
end;

procedure  TBrvExport.ExportarFormatoExcel(CdsDados  : TDataSource; StlColuna : TStrings;
                                           pNmFile : String; pFrmProgre : TForm);
var lExcel : TBrvExcel;
begin
      lExcel := TBrvExcel.Create(Self);
      try
          lExcel.BrProgressBar := BarProgre;
          lExcel.BrDataSet := CdsDados.DataSet;
          lExcel.Execute(StlColuna, pNmFile, pFrmProgre);
      finally
          FreeAndNil(lExcel);
      end;
end;

procedure  TBrvExport.ExportarOutroFormato(CdsDados  : TBrvClientDataSet;
                                           StlColuna : TStrings;
                                           TpExport  : Byte);
//var ExpExport: TmxDataSetExport;
//    NrField  : Integer;
begin
{
      ExpExport := TmxDataSetExport.Create(Self);

      try
          for NrField := 0 to CdsDados.FieldCount -1 do
          begin
                if Pos(CdsDados.Fields[NrField].FieldName, StlColuna.Text) = 0 then
                begin
                      CdsDados.FieldList.Fields[NrField].Visible := False;
                end;
          end;

          case TpExport of
               3 : ExpExport.ExportType := xtExcel;
               4 : ExpExport.ExportType := xtWord;
               5 : ExpExport.ExportType := xtHTML;
          end;

          ExpExport.DataSet     := CdsDados;
          ExpExport.FileName    := DlgSave.FileName;
          ExpExport.ExportStyle := xsFile;
          ExpExport.Execute;
      finally
          FreeAndNil(ExpExport);
      end;
}
end;

procedure  TBrvExport.ExportarFormatoLinear(DtsDados  : TDataSource;
                                              StlColuna : TStrings);
var NrField  : Integer;
    NrRegist : Integer;
    DsExport : TextFile;
    NrLinha  : Integer;
begin
      AssignFile(DsExport, DlgSave.FileName);
      Rewrite(DsExport);

      try
          DtsDados.Dataset.First;
          NrRegist := 1;

          while not DtsDados.Dataset.Eof do
          begin
                for NrField := 0 to DtsDados.Dataset.FieldCount -1 do
                begin
                      if (Pos(DtsDados.Dataset.Fields[NrField].FieldName,
                                                                  StlColuna.Text) > 0) and
                           (DtsDados.Dataset.Fields[NrField].AsString <> '') then
                      begin
                            if  DtsDados.Dataset.Fields[NrField].DataType <> FtMemo then
                            begin
                                  WriteLn(DsExport,
                                          FormatarLinhaLinear(
                                             NrRegist,
                                             DtsDados.Dataset.Fields[NrField].AsString,
                                             DtsDados.Dataset.Fields[NrField].FieldName));
                            end else
                            begin
                                  DsMemo.DataField  :=
                                               DtsDados.DataSet.Fields[NrField].FieldName;

                                  for NrLinha := 0 to DsMemo.Lines.Count -1 do
                                  begin
                                        WriteLn(
                                          DsExport,
                                          FormatarLinhaLinear(
                                             NrRegist,
                                             DsMemo.Lines.Strings[NrLinha],
                                             DtsDados.Dataset.Fields[NrField].FieldName));
                                  end;
                            end;
                      end;
                end;

                DtsDados.Dataset.Next;
                BarProgre.StepIt;
                Inc(NrRegist);
          end;
      finally
          CloseFile(DsExport);
      end;
end;

function   TBrvExport.FormatarLinhaLinear(NrRegist : Integer; DsField : String;
                                            NmField  : String) : String;
begin
      Result := BrvString.FormatarNumero(IntToStr(NrRegist), 6, True)        + ' ' +
                BrvString.FormatarStringSemAcento(NmField, 8)                + ' ' +
                BrvString.FormatarNumero(IntToStr(Length(DsField)), 6, True) + ' ' +
                BrvString.FormatarStringSemAcento(DsField, Length(Dsfield));
end;


procedure  TBrvExport.ExportarFormatoColunar(StlColuna : TStrings;
                                               TpCarSep  : string;
                                               DtsDados  : TDataSource);
var NrField  : Integer;
    DsExport : TextFile;
    DsLinha  : String;
    NrLinha  : Integer;
begin
      AssignFile(DsExport, DlgSave.FileName);
      Rewrite(DsExport);

      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // escrevendo o cabeçalho
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          DsLinha := '';
          for NrField := 0 to DtsDados.DataSet.FieldCount -1 do
          begin
                if Pos(DtsDados.DataSet.Fields[NrField].FieldName,
                                                                  StlColuna.Text) > 0 then
                begin
                      if DsLinha <> '' then
                      begin
                            DsLinha := DsLinha + TpCarSep;
                      end;

                      DsLinha := DsLinha + DtsDados.DataSet.Fields[NrField].FieldName;
                end;
          end;

          WriteLn(DsExport, DsLinha);

          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // exportando os dados
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          DtsDados.DataSet.First;

          while not DtsDados.DataSet.Eof do
          begin
                DsLinha := '';
                for NrField := 0 to DtsDados.DataSet.FieldCount -1 do
                begin
                      if Pos(DtsDados.DataSet.Fields[NrField].FieldName,
                                                                  StlColuna.Text) > 0 then
                      begin
                            if DsLinha <> '' then
                            begin
                                  DsLinha := DsLinha + TpCarSep;
                            end;

                            if  DtsDados.DataSet.Fields[NrField].DataType <> FtMemo then
                            begin
                                  DsLinha := DsLinha +
                                             DtsDados.DataSet.Fields[NrField].AsString;
                            end else
                            begin
                                  DsMemo.DataField  :=
                                               DtsDados.DataSet.Fields[NrField].FieldName;

                                  for NrLinha := 0 to DsMemo.Lines.Count -1 do
                                  begin
                                        DsLinha := DsLinha + DsMemo.Lines.Strings[NrLinha];
                                  end;
                            end;
                      end;
                end;

                WriteLn(DsExport, DsLinha);

                DtsDados.DataSet.Next;
                BarProgre.StepIt;
          end;
      finally
          CloseFile(DsExport);
      end;
end;

procedure TBrvExport.ExportarFormatoBravo(NmTabela : String;
                                          StlColExp   : TStrings; DtsDados : TDataSource);
var StlChave : TStrings;
    NrField  : Integer;
    NrRegist : Integer;
    DsExport : TextFile;
    NrLinha  : Integer;
begin
      BrvString := TBrvString.Create(Self);

      try
          NmTabela  := Trim(NmTabela);
          StlChave  := TStringList.Create;

          FDsDicion.ChavePrimaria(NmTabela,   StlChave);

          AssignFile(DsExport, DlgSave.FileName);
          Rewrite(DsExport);

          WriteLn(DsExport, BrvString.FormatarStringSemAcento(NmTabela, 15) + ' ' +
                            BrvString.FormatarNumero(IntToStr(BarProgre.Max), 6, True));

          DtsDados.DataSet.First;
          NrRegist := 1;

          while not DtsDados.DataSet.Eof do
          begin
                for NrField := 0 to DtsDados.DataSet.FieldCount -1 do
                begin
                      if (Pos(DtsDados.DataSet.Fields[NrField].FieldName,
                                                                  StlColExp.Text) > 0) and
                         (DtsDados.DataSet.Fields[NrField].AsString <> '') then
                      begin
                            if  DtsDados.DataSet.Fields[NrField].DataType <> FtMemo then
                            begin
                                  WriteLn(DsExport, FormatarLinhaBravo(NrRegist,
                                          StlChave, 'N',
                                          DtsDados.DataSet.Fields[NrField].AsString,
                                          DtsDados.DataSet.Fields[NrField].FieldName));
                            end else
                            begin
                                  DsMemo.DataField  :=
                                               DtsDados.DataSet.Fields[NrField].FieldName;

                                  for NrLinha := 0 to DsMemo.Lines.Count -1 do
                                  begin
                                        WriteLn(
                                             DsExport,
                                             FormatarLinhaBravo(NrRegist,
                                             StlChave, 'S',
                                             DsMemo.Lines.Strings[NrLinha],
                                             DtsDados.DataSet.Fields[NrField].FieldName));
                                  end;
                            end;
                      end;
                end;

                DtsDados.DataSet.Next;
                BarProgre.StepIt;
                Inc(NrRegist);
          end;
      finally
           BrvString.Destroy;
           StlChave.Destroy;
           CloseFile(DsExport);
      end;
end;

function TBrvExport.FormatarLinhaBravo(NrRegist : Integer;
                                       StlChave : TStrings; SnMemo   : String;
                                       DsField  : String;   NmField  : String) : String;
var SnKey : String;
begin
      if pos(NmField, StlChave.Text) > 0 then
      begin
            SnKey := 'S';
      end else
      begin
            SnKey := 'N';
      end;

      Result := BrvString.FormatarNumero(IntToStr(NrRegist), 6, True)        + ' ' +
                BrvString.FormatarStringSemAcento(NmField, 8)                + ' ' +
                BrvString.FormatarStringSemAcento(SnKey, 1)                  + ' ' +
                BrvString.FormatarStringSemAcento(SnMemo, 1)                 + ' ' +
                BrvString.FormatarNumero(IntToStr(Length(DsField)), 6, True) + ' ' +
                BrvString.FormatarStringSemAcento(DsField, Length(Dsfield));
end;

procedure TBrvExport.CriarFormProgresso(QtRegist : Integer);
begin
      FrmProgre               := TForm.CreateNew(Self);
      FrmProgre.Visible       := False;
      FrmProgre.FormStyle     := fsMDIChild;
      FrmProgre.BorderStyle   := bsSingle;
      FrmProgre.BorderIcons   := [];
      FrmProgre.Caption       := 'Exportando dados';

      BarProgre               := TProgressBar.Create(FrmProgre);
      BarProgre.Parent        := FrmProgre;
      BarProgre.Top           := 5;
      BarProgre.Left          := 8;
      BarProgre.Width         := 417;
      BarProgre.Height        := 16;
      BarProgre.Step          := 1;
      BarProgre.Max           := QtRegist;

      DsMemo                  := TDbMemo.Create(FrmProgre);
      DsMemo.Visible          := False;
      DsMemo.Parent           := FrmProgre;
      DsMemo.ScrollBars       := ssBoth;

      FrmProgre.Width         := 443;
      FrmProgre.Height        := 55;
      FrmProgre.Position      := poScreenCenter;
      FrmProgre.Show;
end;

function TBrvExport.ReceberColunasExportacao(
                      var StlColExp : TStrings;     StlColuna : TStrings;
                      var TpExport  : Byte;     var TpCarSep  : String):Boolean;
begin
      FrmExpRes  := TFrmExpRes.Create(Self);

      try
          FrmExpRes.LcbColunas.Items.Text := StlColuna.Text;
          FrmExpRes.Marcartodos1Click(nil);

          if  FrmExpRes.ShowModal = MrOk then
          begin
                StlColExp.Text := FrmExpRes.StlColImp.Text;
                TpExport       := FrmExpRes.RgpTpExport.ItemIndex;
                TpCarSep       := FrmExpRes.EdtTpCarSep.Text;
                Result := True;
          end else
          begin
                Result := False;
          end;
      finally
          FrmExpRes.Destroy;
      end;
end;

procedure TBrvExport.XMLToTreeView(pDsTexto: WideString; pTreView: TTreeView);
var lXMLDoc: TXMLDocument;
    lVarNode   : PString;

    procedure GenereteTree(XMLNode: IXMLNode; TreeNode: TTreeNode);
    var  NodeText : string;
         NewTreeNode: TTreeNode;
         I : Integer;
         lVrNode : String;
    begin
          if XMLNode.NodeType <> ntElement then
          begin
                Exit;
          end;

          NodeText := XMLNode.Attributes['VR1'];

          if (XMLNode.AttributeNodes.Count = 2) then
          begin
                lVrNode := XMLNode.Attributes['VR2'];
          end else
          begin
                lVrNode := '';
          end;

          if XMLNode.IsTextElement then
          begin
                NodeText := XMLNode.NodeValue;
          end;

          new(lVarNode);
          NewTreeNode := pTreView.Items.AddChildObject(TreeNode, NodeText, lVarNode);

          PString(NewTreeNode.Data)^ := lVrNode;

          if XMLNode.HasChildNodes then
          begin
                for I := 0 to XMLNode.ChildNodes.Count - 1 do
                begin
                      GenereteTree(XMLNode.ChildNodes[I], NewTreeNode);
                end;
          end;
    end;
begin
      lXMLDoc := TXMLDocument.Create(Self);
      lXMLDoc.Active:= False;
      lXMLDoc.XML.Text := pDsTexto;
      lXMLDoc.Active:= True;

      pTreView.Items.Clear;

      GenereteTree(lXMLDoc.DocumentElement, nil);
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=-=-= Rotinas para tratamento do TreeView
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TBrvExport.MontarTreeView(pDsTexto: WideString; pTreView : TTreeView);
var lNrLinha  : Integer;
    lDsLinha  : String;

    lNrNivAnt  : Integer;
    lNrNivel   : Integer;

    lVarNode   : PString;
    lNode      : TTreeNode;

    lArrNode   : array of TTreeNode;
    lNdPai     : TTreeNode;

    lBrvStrLin : TBrvString;
    lBrvStrCol : TBrvString;
begin
      lBrvStrLin := TBrvString.Create(Self);
      lBrvStrCol := TBrvString.Create(Self);
      pTreView.Items.Clear;

      try
          lBrvStrLin.Split(pDsTexto, '@@');
          lNrNivAnt := -1;
          SetLength(lArrNode, StrToInt(lBrvStrLin.BrSplitLista.Strings[0]));

          for lNrLinha := 1 to lBrvStrLin.BrSplitLista.Count -1 do
          begin
                lDsLinha := lBrvStrLin.BrSplitLista.Strings[lNrLinha];

                if lDsLinha <> '' then
                begin
                      lBrvStrCol.Split(lDsLinha, '#');
                      new(lVarNode);

                      lNrNivel := StrToInt(lBrvStrCol.BrSplitLista.Strings[0]);

                      if lNrNivel = 0 then
                      begin
                            lNode := pTreView.Items.AddObject(nil,
                                               lBrvStrCol.BrSplitLista.Strings[1],
                                               lVarNode);
                      end else
                      begin
                            if lNrNivel > lNrNivAnt then
                            begin
                                  lArrNode[lNrNivel] := lNode;
                            end;

                            lNdPai := lArrNode[lNrNivel];

                            lNode := pTreView.Items.AddChildObject(
                                        lNdPai,
                                        lBrvStrCol.BrSplitLista.Strings[1],
                                        lVarNode);
                      end;

                      lNrNivAnt := lNrNivel;

                      if lBrvStrCol.BrSplitLista.Strings[1] = '' then
                      begin
                            PString(lNode.Data)^ := '';
                      end else
                      begin
                            PString(lNode.Data)^ := lBrvStrCol.BrSplitLista.Strings[2];
                      end;
                end;
          end;
      finally
          FreeAndNil(lBrvStrLin);
      end;
end;

function TBrvExport.ExportarTreeView(pTreView : TTreeView) : WideString;
var lNrNode  : Integer;
    lNrIndex : Integer;
begin
      Result := IntToStr(pTreView.Items.Count) + '@@';

      pTreView.Visible := False;

      for lNrNode := 0 to pTreView.Items.Count -1 do
      begin
            if pTreView.Items[lNrNode].Parent = nil then
            begin
                  lNrIndex := 0;
            end else
            begin
                  lNrIndex := pTreView.Items[lNrNode].Parent.AbsoluteIndex + 1;
            end;

            Result := Result +
                      IntToStr(lNrIndex) + '#' +
                      pTreView.Items[lNrNode].Text  + '#' +
                      PString(pTreView.Items[lNrNode].Data)^ + '@@';
      end;
end;
end.
