unit UCad0005;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, Spin, Grids, BrvDbGrids,
  BrvDbGrid, SynEdit, SynDBEdit, ComCtrls, DB, DBClient, BrvClientDataSet, ImgList,
  SynEditHighlighter, SynHighlighterSQL, ToolWin, DBCtrls, BrvOracle,
  SynEditTypes;

type
  TCad0005 = class(TCad0000)
    TrvDicion: TTreeView;
    Splitter1: TSplitter;
    Panel1: TPanel;
    DbgSqlUsr: TBrvDbGrid;
    Splitter3: TSplitter;
    DbgSQL: TBrvDbGrid;
    Splitter2: TSplitter;
    QcSqlUsr: TBrvClientDataSet;
    DsSqlUsr: TDataSource;
    MemSQL: TDBSynEdit;
    ImageList1: TImageList;
    DsView: TDataSource;
    QcView: TBrvClientDataSet;
    QcExcess: TBrvClientDataSet;
    DsExcess: TDataSource;
    DsProced: TDataSource;
    QcProced: TBrvClientDataSet;
    QcColInd: TBrvClientDataSet;
    DsColInd: TDataSource;
    DsIndice: TDataSource;
    QcIndice: TBrvClientDataSet;
    DsTrigger: TDataSource;
    QcTrigger: TBrvClientDataSet;
    QcTabela: TBrvClientDataSet;
    DsTabela: TDataSource;
    SynSQLSyn1: TSynSQLSyn;
    QcTabAtr: TBrvClientDataSet;
    DsTabAtr: TDataSource;
    QcDomAtr: TBrvClientDataSet;
    DsDomAtr: TDataSource;
    QcChaPri: TBrvClientDataSet;
    DsChaEst: TDataSource;
    DsChaPri: TDataSource;
    QcChaEst: TBrvClientDataSet;
    QcColEst: TBrvClientDataSet;
    DsColEst: TDataSource;
    DSql: TDataSource;
    QSql: TBrvClientDataSet;
    QpTabela: TBrvClientDataSet;
    QpTabAtr: TBrvClientDataSet;
    QpChaPri: TBrvClientDataSet;
    QpChaEst: TBrvClientDataSet;
    QpColEst: TBrvClientDataSet;
    QpTrigger: TBrvClientDataSet;
    QpIndice: TBrvClientDataSet;
    QpAtrInd: TBrvClientDataSet;
    QpProced: TBrvClientDataSet;
    QpView: TBrvClientDataSet;
    QcPerGru: TBrvClientDataSet;
    QcPerUsu: TBrvClientDataSet;
    CdsTmpGru: TClientDataSet;
    CdsTmpUsu: TClientDataSet;
    QpExcess: TBrvClientDataSet;
    QpFormul: TBrvClientDataSet;
    QcForDin: TBrvClientDataSet;
    DsForDin: TDataSource;
    QpSubFor: TBrvClientDataSet;
    DsSubFor: TDataSource;
    ToolBar1: TToolBar;
    BrvSpeedButton3: TBrvSpeedButton;
    BrvSpeedButton2: TBrvSpeedButton;
    BrvSpeedButton4: TBrvSpeedButton;
    DBNavigator1: TDBNavigator;
    Bevel1: TBevel;
    Bevel2: TBevel;
    BrvOracle: TBrvOracle;
    StatusBar: TStatusBar;
    BrvSpeedButton5: TBrvSpeedButton;
    procedure FormCreate(Sender: TObject);
    procedure SpinButton1DownClick(Sender: TObject);
    procedure SpinButton1UpClick(Sender: TObject);
    procedure TrvDicionChange(Sender: TObject; Node: TTreeNode);
    procedure TrvDicionDblClick(Sender: TObject);
    procedure BrvSpeedButton2Click(Sender: TObject);
    procedure BrvSpeedButton3Click(Sender: TObject);
    procedure BrvSpeedButton4Click(Sender: TObject);
    procedure FormKeyDown(Sender: TObject; var Key: Word; Shift: TShiftState);
    procedure MemSQLStatusChange(Sender: TObject; Changes: TSynStatusChanges);
    procedure QcSqlUsrNewRecord(DataSet: TDataSet);
  private
    gNdNode   : PString;

    procedure TabelasDoBanco;
    procedure MontarNivel(pNoImagem: Integer);
    procedure CarregarTabelas;
    procedure MontarArvoreAtributosTabela;
    procedure CarregarAtributosDaTabela(pNmTabela: String);
    procedure CarregarAtributosChaveEstrangeira(pNmChaEst: String);
    procedure CarregarAtributosDaChavePrimaria(pNmTabela: String);
    procedure CarregarChavesEstrangeiras(pNmTabela: String);
    procedure CarregarTrigger(pNmTabela: String);
    procedure CdsTmpGruAfterPost(DataSet: TDataSet);
    procedure CarregarAtributosIndice(pNmTabela, pNmIndice: String);
    procedure CarregarIndices(pNmTabela: String);
    procedure CarregarExcessoes;
    procedure CarregarFormularios(pNmTabela: String);
    procedure CarregarProcedimentos;
    procedure CarregarViews;
    procedure CarregarSubFormulariosDinamico(pNrForDin: String);
    function NumeroDoFormularioDinamico(pDsForDin: String): String;
    procedure ExecutarSQL;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Cad0005: TCad0005;

implementation

uses BrvFuncoesXE, UDmSrvApl;


const gImgBanco  = 0;
      gImgGruTab = 1; // Grupo de Tabelas
      gImgGruPro = 2; // Grupo de Procedures
      gImgTabela = 3; // Tabela
      gImgGruCol = 4; // Grupo de Colunas
      gImgGruKey = 5; // Grupo de Chave Primria
      gImgGruEst = 6; // Grupo de chave estrangeira
      gImgColuna = 7; // Atributos
      gImgColKey = 8; // Atributo da chave primria
      gImgEstKey = 9;  //chave estrangeira
      gImgColEst = 10; // Atributo estrangeiro
      gImgEditar = 11; // Edio
      gImgGruTri = 12; // Grupo de Triggers
      gImgTrigge = 13; // trigger
      gImgGruInd = 14; // Grupo de ndices
      gImgIndice = 15; // ndice
      gImgColInd = 16; // atributo do ndice
      gImgProced = 17; // imagem do procedimento
      gImgGruExc = 18; // Grupo de Excesses
      gImgExcess = 19; // Excesso
      gImgGruFor = 20; // Grupo de formulrios
      gImgFormul = 21; // Formulrios
      gImgGruVie = 22; // Grupo de View
      gImgView   = 23; // View

{$R *.dfm}



//***********************************************************************************************//
procedure TCad0005.TabelasDoBanco();
begin
      { Refaz TreeView com tabelas do banco }

end;
procedure TCad0005.TrvDicionChange(Sender: TObject; Node: TTreeNode);
var lNrForDin : String;
    lDsComWhe : String;
begin
      if (TrvDicion.Tag = 0) then
      begin
            case TrvDicion.Selected.ImageIndex of
                 gImgBanco,  gImgGruTab, gImgGruPro, gImgGruCol, gImgGruEst,
                 gImgGruKey, gImgGruTri, gImgGruExc, gImgGruVie:
                              begin
                                    // PgcBanco.ActivePage := TbsNada;
                              end;
                 gImgTabela :
                              begin
                                    QcTabela.Close;
                                    lDsComWhe := '= ' + #39 +
                                                            TrvDicion.Selected.Text + #39;
                                    QcTabela.BrParams.Clear;
                                    QcTabela.BrParams.Add('<%NmTabela%>;' + lDsComWhe);
                                    QcTabela.Open;
                              end;
                 gImgColuna : begin
                                    lDsComWhe := 'S009.NmTabela = ' + #39 +
                                             TrvDicion.Selected.Parent.Parent.Text + #39 +
                                             ' and S009.NmAtribu = ' + #39 +
                                             TrvDicion.Selected.Text + #39;
                                    QcTabAtr.Close;
                                    QcTabAtr.BrParams.Clear;
                                    QcTabAtr.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcTabATr.Open;

                                    QcDomAtr.Close;
                                    QcDomAtr.BrParams.Clear;
                                    QcDomAtr.BrParams.Add('<%NmTabela%>;' +
                                                   TrvDicion.Selected.Parent.Parent.Text);
                                    QcDomAtr.BrParams.Add('<%NmAtribu%>;' +
                                                      TrvDicion.Selected.Text);
                                    QcDomAtr.Open;

                              end;
                 gImgColKey : begin
                                    lDsComWhe := 'S011.NmTabela = ' + #39 +
                                            TrvDicion.Selected.Parent.Parent.Text + #39 +
                                            ' and S011.NmAtribu = ' + #39 +
                                                            TrvDicion.Selected.Text + #39;
                                    QcChaPri.Close;
                                    QcChaPri.BrParams.Clear;
                                    QcChaPri.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcChaPri.Open;
                                    QcChaPri.Edit;

                              end;
                 gImgEstKey : begin
                                    lDsComWhe := 'S012.NmChaEst = ' + #39 +
                                                            TrvDicion.Selected.Text + #39;
                                    QcChaEst.Close;
                                    QcChaEst.BrParams.Clear;
                                    QcChaEst.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcChaEst.Open;

                              end;
                 gImgColEst : begin
                                    lDsComWhe := 'S013.NmChaEst = ' + #39 +
                                                    TrvDicion.Selected.Parent.Text + #39 +
                                                 ' and S013.NmAtribu = ' + #39 +
                                                            TrvDicion.Selected.Text + #39;
                                    QcColEst.Close;
                                    QcColEst.BrParams.Clear;
                                    QcColEst.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcColEst.Open;
                                    QcColEst.Edit;

                              end;
                 gImgTrigge : begin
                                    lDsComWhe := 'S020.NmTrigge = ' + #39 +
                                                            TrvDicion.Selected.Text + #39;
                                    QcTrigger.Close;
                                    QcTrigger.BrParams.Clear;
                                    QcTrigger.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcTrigger.Open;
                              end;
                 gImgIndice : begin
                                    lDsComWhe := 'S018.NmIndice = ' + #39 +
                                                            TrvDicion.Selected.Text + #39;
                                    QcIndice.Close;
                                    QcIndice.BrParams.Clear;
                                    QcIndice.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcIndice.Open;
                              end;
                 gImgColInd : begin
                                    lDsComWhe := 'S019.NmIndice = ' + #39 +
                                                    TrvDicion.Selected.Parent.Text + #39 +
                                                 ' and S019.NmAtribu = ' + #39 +
                                                    TrvDicion.Selected.Text + #39;
                                    QcColInd.Close;
                                    QcColInd.BrParams.Clear;
                                    QcColInd.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcColInd.Open;
                                    QcColInd.Edit;

                              end;
                 gImgProced : begin
                                    lDsComWhe := 'S021.NmProced = ' + #39 +
                                                            TrvDicion.Selected.Text + #39;
                                    QcProced.Close;
                                    QcProced.BrParams.Clear;
                                    QcProced.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcProced.Open;
                              end;
                 gImgView   : begin
                                    lDsComWhe := 'S040.NmView = ' + #39 +
                                                            TrvDicion.Selected.Text + #39;
                                    QcView.Close;
                                    QcView.BrParams.Clear;
                                    QcView.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcView.Open;
                              end;
                 gImgExcess : begin
                                    lDsComWhe := 'S025.NmExcess = ' + #39 +
                                                            TrvDicion.Selected.Text + #39;
                                    QcExcess.Close;
                                    QcExcess.BrParams.Clear;
                                    QcExcess.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcExcess.Open;
                              end;
                 gImgFormul : begin
                                    lNrForDin := NumeroDoFormularioDinamico(
                                                                 TrvDicion.Selected.Text);
                                    lDsComWhe := 'S014.NrForDin = ' +
                                                                    #39 + lNrForDin + #39;
                                    QcForDin.Close;
                                    QcForDin.BrParams.Clear;
                                    QcForDin.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcForDin.Open;
                                    CarregarSubFormulariosDinamico(lNrForDin);
                              end;
            end;
      end;
end;


procedure TCad0005.MontarNivel(pNoImagem : Integer);
begin
      case pNoImagem of
           gImgGruTab: begin // mostrando tabelas
                             CarregarTabelas;
                       end;
           gImgTabela:
                       begin // Criando rvore de propriedades da tabela
                             MontarArvoreAtributosTabela;
                       end;
           gImgGruCol: begin // mostrando colunas da tabela
                             CarregarAtributosDaTabela(TrvDicion.Selected.Parent.Text);
                       end;
           gImgGruKey: begin // mostrando colunas da chave primria
                             CarregarAtributosDaChavePrimaria
                                     (TrvDicion.Selected.Parent.Text);
                       end;
           gImgGruEst: begin // mostrando chaves estrangeiras
                             CarregarChavesEstrangeiras(TrvDicion.Selected.Parent.Text);
                       end;
           gImgEstKey: begin
                             CarregarAtributosChaveEstrangeira(TrvDicion.Selected.Text);
                       end;
           gImgGruTri: begin // mostrando Triggers
                             CarregarTrigger(TrvDicion.Selected.Parent.Text);
                       end;
           gImgGruInd: begin // mostrando ndices
                             CarregarIndices(TrvDicion.Selected.Parent.Text);
                       end;
           gImgIndice: begin
                             CarregarAtributosIndice(TrvDicion.Selected.Parent.Parent.Text,
                                                     TrvDicion.Selected.Text);
                       end;
           gImgGruPro: begin
                             CarregarProcedimentos;
                       end;
           gImgGruVie: begin
                             CarregarViews;
                       end;
           gImgGruExc: begin
                             CarregarExcessoes;
                       end;
           gImgGruFor: begin // mostrando os formulrios
                             CarregarFormularios(TrvDicion.Selected.Parent.Text);
                       end;
      end;

      TrvDicion.Selected.Expand(True);
end;

//***********************************************************************************************//
procedure TCad0005.CarregarTabelas;
var lNdNovo   : TTreeNode;
begin
     QpTabela.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                            'Select * From S008 Order by NmTabela');

     while not QpTabela.Eof do
     begin
           New(gNdNode);
           lNdNovo := TrvDicion.Items.AddChildObject(
                                      TrvDicion.Selected,
                                      QpTabela.FieldByName('NmTabela').AsString,
                                      gNdNode);
           PString(lNdNovo.Data)^ :=  QpTabela.FieldByName('NmTabela').AsString;

           lNdNovo.ImageIndex    := gImgTabela;
           lNdNovo.SelectedIndex := gImgTabela;

           QpTabela.Next;
     end;

     QpTabela.Close;
end;

procedure TCad0005.MemSQLStatusChange(Sender: TObject; Changes: TSynStatusChanges);
const
  ModifiedStrs: array[boolean] of string = ('', 'Modified');
  InsertModeStrs: array[boolean] of string = ('Overwrite', 'Insert');
var
  p: TBufferCoord;
  Token: WideString;
  Attri: TSynHighlighterAttributes;
begin
  if Changes * [scAll, scCaretX, scCaretY] <> [] then begin
    p := MemSQL.CaretXY;
    Statusbar.Panels[0].Text := Format('%6d:%3d', [p.Line, p.Char]);
  end;

  if Changes * [scAll, scInsertMode, scReadOnly] <> [] then begin
    if MemSQL.ReadOnly then
      Statusbar.Panels[2].Text := 'ReadOnly'
    else
      Statusbar.Panels[2].Text := InsertModeStrs[MemSQL.InsertMode];
  end;
  // Modified property has changed
  if Changes * [scAll, scModified] <> [] then
    Statusbar.Panels[1].Text := ModifiedStrs[MemSQL.Modified];
  // selection has changed
  // select highlighter attribute at caret
  if (MemSQL.Highlighter <> nil) and (Changes * [scAll, scCaretX, scCaretY] <> [])
  then begin
    if not MemSQL.GetHighlighterAttriAtRowCol(MemSQL.CaretXY, Token,
      Attri)
    then
      Attri := MemSQL.Highlighter.WhitespaceAttribute;
  end;

end;

procedure TCad0005.MontarArvoreAtributosTabela;
var lNdNovo   : TTreeNode;
begin
      // Colunas
         New(gNdNode);
         lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                                  'Atributos', gNdNode);
         PString(lNdNovo.Data)^ := 'Atributos';
         lNdNovo.ImageIndex     := gImgGruCol;
         lNdNovo.SelectedIndex  := gImgGruCol;

      // Chave Primria
         New(gNdNode);
         lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                                   'Chave Primria', gNdNode);
         PString(lNdNovo.Data)^ := 'Chave Primria';
         lNdNovo.ImageIndex     := gImgGruKey;
         lNdNovo.SelectedIndex  := gImgGruKey;

      // Chave Estrangeira
         New(gNdNode);
         lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                                  'Chave Estrangeira', gNdNode);
         PString(lNdNovo.Data)^ := 'Chave Estrangeira';
         lNdNovo.ImageIndex     := gImgGruEst;
         lNdNovo.SelectedIndex  := gImgGruEst;

      // Triggers
         New(gNdNode);
         lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                                   'Triggers', gNdNode);
         PString(lNdNovo.Data)^ := 'Triggers';
         lNdNovo.ImageIndex     := gImgGruTri;
         lNdNovo.SelectedIndex  := gImgGruTri;

      // ndices
         New(gNdNode);
         lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                                   'ndices', gNdNode);
         PString(lNdNovo.Data)^ := 'ndices';
         lNdNovo.ImageIndex     := gImgGruInd;
         lNdNovo.SelectedIndex  := gImgGruInd;

      // Formulrios dinmicos
         New(gNdNode);
         lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                                   'Formulrios Dinmicos', gNdNode);
         PString(lNdNovo.Data)^ := 'Formulrios';
         lNdNovo.ImageIndex     := gImgGruFor;
         lNdNovo.SelectedIndex  := gImgGruFor;
end;


procedure TCad0005.CarregarAtributosDaTabela(pNmTabela : String);
var lNdNovo   : TTreeNode;
begin
     QpTabAtr.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
          'Select * From S009 Where NmTabela = ' + #39 +
                                                   pNmTabela +#39 + ' Order by NmAtribu');

     while not QpTabAtr.Eof do
     begin
           New(gNdNode);
           lNdNovo := TrvDicion.Items.AddChildObject(
                                      TrvDicion.Selected,
                                      QpTabAtr.FieldByName('NmAtribu').AsString,
                                      gNdNode);
           PString(lNdNovo.Data)^ :=  QpTabAtr.FieldByName('NmAtribu').AsString;

           lNdNovo.ImageIndex    := gImgColuna;
           lNdNovo.SelectedIndex := gImgColuna;

           QpTabAtr.Next;
     end;

     QpTabAtr.Close;
end;

procedure TCad0005.CarregarAtributosDaChavePrimaria(pNmTabela : String);
var lNdNovo   : TTreeNode;
begin
      QpChaPri.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
          'Select * From S011 Where NmTabela = ' + #39 + pNmTabela +#39 + ' Order by NrSeqKey');

      while not QpChaPri.Eof do
      begin
            New(gNdNode);
            lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                       QpChaPri.FieldByName('NmAtribu').AsString,
                                       gNdNode);
            PString(lNdNovo.Data)^ :=  QpChaPri.FieldByName('NmAtribu').AsString;
            lNdNovo.ImageIndex    := gImgColKey;
            lNdNovo.SelectedIndex := gImgColKey;
            QpChaPri.Next;
      end;

      QpChaPri.Close;
end;

procedure TCad0005.CarregarChavesEstrangeiras(pNmTabela : String);
var lNdNovo   : TTreeNode;
begin
      QpChaEst.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
          'Select * From S012 Where NmTabela = ' + #39 + pNmTabela +#39 + ' Order by NmChaEst');

      while not QpChaEst.Eof do
      begin
            New(gNdNode);
            lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                       QpChaEst.FieldByName('NmChaEst').AsString,
                                       gNdNode);
            PString(lNdNovo.Data)^ :=  QpChaEst.FieldByName('NmChaEst').AsString;

            lNdNovo.ImageIndex    := gImgEstKey;
            lNdNovo.SelectedIndex := gImgEstKey;

            QpChaEst.Next;
      end;

      QpChaEst.Close;
end;

procedure TCad0005.BrvSpeedButton2Click(Sender: TObject);
begin
      inherited;
      if QcSqlUsr.State in [dsEdit, dsInsert] then
      begin
            QcSqlUsr.Post;
      end;
      if QcSqlUsr.ChangeCount > 0 then
      begin
            QcSqlUsr.ApplyUpdates(0);
      end;
end;

procedure TCad0005.BrvSpeedButton3Click(Sender: TObject);
begin
      inherited;
      BrvSpeedButton2Click(Sender);
      QcSqlUsr.Append;
end;

procedure TCad0005.BrvSpeedButton4Click(Sender: TObject);
begin
      if MessageDlg('Confirma a excluso desta QUERY ?', mtConfirmation,
                                                      [mbYes, mbNo], 0) = Idyes then
      begin
            QcSqlUsr.Delete;
            QcSqlUsr.ApplyUpdates(0);
      end;

end;

procedure TCad0005.CarregarAtributosChaveEstrangeira(pNmChaEst : String);
var lNdNovo   : TTreeNode;
begin
      QpColEst.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
          'Select * From S013 Where NmChaEst = ' + #39 + pNmChaEst +#39 + ' Order by NrSeqKey');

      while not QpColEst.Eof do
      begin
            New(gNdNode);
            lNdNovo := TrvDicion.Items.AddChildObject(
                                               TrvDicion.Selected,
                                               QpColEst.FieldByName('NmAtribu').AsString,
                                               gNdNode);
            PString(lNdNovo.Data)^ := QpColEst.FieldByName('NmAtribu').AsString;
            lNdNovo.ImageIndex    := gImgColEst;
            lNdNovo.SelectedIndex := gImgColEst;
            QpColEst.Next;
      end;
end;

procedure TCad0005.CarregarTrigger(pNmTabela : String);
var lNdNovo   : TTreeNode;
begin
      QpTrigger.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
          'Select * From S020 Where NmTabela = ' + #39 + pNmTabela + #39 + ' Order by NmTrigge');

      while not QpTrigger.Eof do
      begin
            New(gNdNode);
            lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                             QpTrigger.FieldByName('NmTrigge').AsString,
                                             gNdNode);

            PString(lNdNovo.Data)^ := QpTrigger.FieldByName('NmTrigge').AsString;
            lNdNovo.ImageIndex    := gImgTrigge;
            lNdNovo.SelectedIndex := gImgTrigge;
            QpTrigger.Next;
      end;

      QpTrigger.Close;
end;

procedure TCad0005.CdsTmpGruAfterPost(DataSet: TDataSet);
begin
      if DataSet.Tag = 0 then
      begin
            if DataSet.FieldByName('TpPermis').AsInteger > 0 then
            begin
                  if QcPerGru.FindKey([DataSet.FieldByName('CdGrupo').AsInteger]) then
                  begin
                        QcPerGru.Edit;
                  end else
                  begin
                        QcPerGru.Insert;
                        QcPerGru.FieldByName('NmTabela').AsString :=
                                 QcTabAtr.FieldByName('NmTabela').AsString;
                        QcPerGru.FieldByName('NmAtribu').AsString :=
                                 QcTabAtr.FieldByName('NmAtribu').AsString;
                        QcPerGru.FieldByName('CdGrupo').AsInteger :=
                                 DataSet.FieldByName('CdGrupo').AsInteger;
                  end;
                  QcPerGru.FieldByName('TpPermis').AsString :=
                                                 DataSet.FieldByName('TpPermis').AsString;
                  QcPerGru.Post;
                  QcPerGru.ApplyUpdates(0);
            end else
            begin
                  if QcPerGru.FindKey([CdsTmpGru.FieldByName('CdGrupo').AsInteger]) then
                  begin
                        QcPerGru.Delete;
                        QcPerGru.ApplyUpdates(0);
                  end;
            end;
      end;
end;

//***********************************************************************************************//
procedure TCad0005.CarregarIndices(pNmTabela : String);
var lNdNovo   : TTreeNode;
begin
      QpIndice.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
          'Select * From S018 Where NmTabela = ' + #39 + pNmTabela + #39 + ' Order by NmIndice');

      while not QpIndice.Eof do
      begin
            New(gNdNode);
            lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                             QpIndice.FieldByName('NmIndice').AsString,
                                             gNdNode);

            PString(lNdNovo.Data)^ := QpIndice.FieldByName('NmIndice').AsString;

            lNdNovo.ImageIndex    := gImgIndice;
            lNdNovo.SelectedIndex := gImgIndice;
            QpIndice.Next;
      end;

      QpIndice.Close;
end;

procedure TCad0005.CarregarAtributosIndice(pNmTabela : String; pNmIndice : String);
var lNdNovo   : TTreeNode;
begin
      QpAtrInd.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
          'Select * From S019 Where NmTabela = ' + #39 + pNmTabela + #39 + ' and NmIndice = ' + #39 + pNmIndice + #39 + ' Order by NrSeqInd');

      while not QpAtrInd.Eof do
      begin
            New(gNdNode);
            lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                             QpAtrInd.FieldByName('NmAtribu').AsString,
                                             gNdNode);

            PString(lNdNovo.Data)^ := QpAtrInd.FieldByName('NmAtribu').AsString;

            lNdNovo.ImageIndex    := gImgColInd;
            lNdNovo.SelectedIndex := gImgColInd;
            QpAtrInd.Next;
      end;

      QpAtrInd.Close;
end;


//***********************************************************************************************//
procedure TCad0005.CarregarProcedimentos;
var lNdNovo   : TTreeNode;
begin
      QpProced.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                                  'Select * From S021 Order by NmProced');
      while not QpProced.Eof do
      begin
            New(gNdNode);
            lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                             QpProced.FieldByName('NmProced').AsString,
                                             gNdNode);

            PString(lNdNovo.Data)^ := QpProced.FieldByName('NmProced').AsString;

            lNdNovo.ImageIndex    := gImgProced;
            lNdNovo.SelectedIndex := gImgProced;
            QpProced.Next;
      end;

      QpProced.Close;
end;

procedure TCad0005.CarregarViews;
var lNdNovo   : TTreeNode;
begin
      QpView.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                                    'Select * From S040 Order by NmView');
      while not QpView.Eof do
      begin
            New(gNdNode);
            lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                             QpView.FieldByName('NmView').AsString,
                                             gNdNode);

            PString(lNdNovo.Data)^ := QpView.FieldByName('NmView').AsString;

            lNdNovo.ImageIndex    := gImgView;
            lNdNovo.SelectedIndex := gImgView;
            QpView.Next;
      end;

      QpView.Close;
end;

procedure TCad0005.CarregarExcessoes;
var lNdNovo   : TTreeNode;
begin
      QpExcess.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                                 'Select * From S025 Order by NmExcess');

      while not QpExcess.Eof do
      begin
            New(gNdNode);
            lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                             QpExcess.FieldByName('NmExcess').AsString,
                                             gNdNode);

            PString(lNdNovo.Data)^ := QpExcess.FieldByName('NmExcess').AsString;

            lNdNovo.ImageIndex    := gImgExcess;
            lNdNovo.SelectedIndex := gImgExcess;
            QpExcess.Next;
      end;

      QpExcess.Close;
end;

procedure TCad0005.CarregarFormularios(pNmTabela : String);
var lNdNovo   : TTreeNode;
begin
      QpFormul.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
          'Select * From S014 Where NmTabela = ' + #39 + pNmTabela + #39 + ' Order by NrForDin');

      while not QpFormul.Eof do
      begin
            New(gNdNode);
            lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                       QpFormul.FieldByName('DsForDin').AsString +
                                       ' (' +
                                       QpFormul.FieldByName('NrForDin').AsString + ')',
                                       gNdNode);

            PString(lNdNovo.Data)^ := QpFormul.FieldByName('NrForDin').AsString;

            lNdNovo.ImageIndex    := gImgFormul;
            lNdNovo.SelectedIndex := gImgFormul;
            QpFormul.Next;
      end;

      QpFormul.Close;
end;

//***********************************************************************************************//

procedure TCad0005.CarregarSubFormulariosDinamico(pNrForDin : String);
begin
      QpSubFor.Close;
      QpSubFor.BrParams.Clear;
      QpSubFor.BrParams.Add('<%NrForDin%>;' + pNrForDin);
      QpSubFor.Open;
end;

function TCad0005.NumeroDoFormularioDinamico(pDsForDin : String) : String;
begin
      Delete(pDsForDin, 1, Pos('(', pDsForDin));
      Result := Copy(pDsForDin, 1, Pos(')', pDsForDin) -1);
end;

procedure TCad0005.QcSqlUsrNewRecord(DataSet: TDataSet);
begin
      inherited;
      QcSqlUsr.FieldByName('CdUsuari').AsInteger := DmSrvApl.BrvDicionario.UserCode;
end;

//***********************************************************************************************//

procedure TCad0005.TrvDicionDblClick(Sender: TObject);
begin
  inherited;
      if not TrvDicion.Selected.HasChildren then
      begin
            MontarNivel(TrvDicion.Selected.ImageIndex);
      end;

end;

procedure TCad0005.FormCreate(Sender: TObject);
var lNdNovo   : TTreeNode;
begin
      inherited;
      Height  :=  650;
      Width   :=  1000;
      AjustaForm(Self);

      TrvDicion.Items.Clear;
      new(gNdNode);
      lNdNovo := TrvDicion.Items.AddObject(nil, 'Banco', gNdNode);
      PString(lNdNovo.Data)^ := 'BANCO';
      lNdNovo.ImageIndex    := gImgBanco;
      lNdNovo.SelectedIndex := gImgBanco;
      TrvDicion.Selected := lNdNovo;


      lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected, 'Tabelas',
                                                gNdNode);
      PString(lNdNovo.Data)^ := 'TABELAS';
      lNdNovo.ImageIndex    := gImgGruTab;
      lNdNovo.SelectedIndex := gImgGruTab;


      lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected, 'Procedures',
                                                gNdNode);
      PString(lNdNovo.Data)^ := 'PROCEDURES';
      lNdNovo.ImageIndex    := gImgGruPro;
      lNdNovo.SelectedIndex := gImgGruPro;

      lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected, 'Views',
                                                gNdNode);
      PString(lNdNovo.Data)^ := 'VIEWS';
      lNdNovo.ImageIndex    := gImgGruVie;
      lNdNovo.SelectedIndex := gImgGruVie;

      lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected, 'Excesses',
                                                gNdNode);
      PString(lNdNovo.Data)^ := 'EXCESSOES';
      lNdNovo.ImageIndex    := gImgGruExc;
      lNdNovo.SelectedIndex := gImgGruExc;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      TrvDicion.Selected.Expand(True);



      // =-=-=-=-= carregando sql do usurio
      QcSqlUsr.Close;
      QcSqlUsr.BrParams.Clear;
      QcSqlUsr.BrParams.Add('<%CdUsuari%>;' +  IntToStr(DmSrvApl.BrvDicionario.UserCode));
      QcSqlUsr.Open;

      // =-=-=-=-= ajusta colunas do grid, withs ;-)
      with DbgSqlUsr.Columns do
      begin
            Items[0].Width  :=  26;
            Items[1].Width  :=  220;

      end;
      DbgSqlUsr.Width       :=  282;
      TrvDicion.Width       :=  282;
end;

procedure TCad0005.FormKeyDown(Sender: TObject; var Key: Word; Shift: TShiftState);
begin
      if ssCtrl in Shift then
      begin
            case key of
                 VK_F9 : begin
                            ExecutarSQL;
                      end;
            end;
      end;
end;

procedure TCad0005.SpinButton1DownClick(Sender: TObject);
begin
      inherited;
      Height  :=  Height  - 1;
end;

procedure TCad0005.SpinButton1UpClick(Sender: TObject);
begin
      inherited;
      Height  :=  Height  + 1;

end;


//*************************************************************************************************
procedure TCad0005.ExecutarSQL();
var lDsSql    : String;
    lSnExecut : Boolean;
    lHoInicio : TTime;
begin
      lSnExecut := True;

      if lSnExecut then
      begin
            if MemSql.Text <> '' then
            begin
                  if MemSql.SelLength > 0 then
                  begin
                        lDsSql := UpperCase(Trim(MemSql.SelText));
                  end else
                  begin
                        lDsSql := UpperCase(Trim(MemSql.Text));
                  end;

                  lDsSql := BrvOracle.SintaxeOracle(lDsSql);

                  lHoInicio := Time;

                  if Copy(lDsSql, 1, 6) = 'SELECT' then
                  begin
                        try
                            QSql.Close;
                            QSql.CommandText := lDsSql;
                            QSql.Open;
//                            PnlResult.Caption := 'Resultado: ' +
//                                                 IntToStr(QSql.RecordCount) + ' linha(s)';
                        except
                            raise;
                        end;
                end else
                  begin
//                        PnlResult.Caption := IntToStr(ExecutarSql(lDsSql)) +
//                          ' linha(s) afetada(s)';
                  end;

//                  PnlTmSql.Caption := TimeToStr(Time - lHoInicio);
             end;
      end;end;




initialization
      RegisterClass(TCad0005);

finalization
      UnRegisterClass(TCad0005);


end.
Class(TCad0005);


end.
