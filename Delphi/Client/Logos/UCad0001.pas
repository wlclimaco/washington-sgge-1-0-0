unit UCad0001;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, Buttons, BrvSpeedButton, ExtCtrls, BrvDbNavCop, ComCtrls, ImgList,
  DB, DBTables, BrvQuery, UClaSrv, DBClient, BrvClientDataSet, Menus, StdCtrls, Mask,
  DBCtrls, BrvDBComboListBox, Grids, DBGrids, Spin, BrvDbGrid, BrvExport,
  BrvRelatorio, BrvOracle, BrvDbGrids, BrvDbEdit, BrvConsulta, BrvDbRetCon, BrvListParam, BrvBitBtn;

type
  TCad0001 = class(TCad0000)
    ImageList1: TImageList;
    Splitter1: TSplitter;
    PgcBanco: TPageControl;
    TbsNada: TTabSheet;
    PgcGeral: TPageControl;
    TbsBanco: TTabSheet;
    TbsGerSql: TTabSheet;
    TrvDicion: TTreeView;
    QpTabela: TBrvClientDataSet;
    PopDic: TPopupMenu;
    PopInclui: TMenuItem;
    PopExclui: TMenuItem;
    N1: TMenuItem;
    PopScript: TMenuItem;
    QcTabela: TBrvClientDataSet;
    TbsTabela: TTabSheet;
    DsTabela: TDataSource;
    Label1: TLabel;
    Label2: TLabel;
    DbEdtDsTabela: TDBEdit;
    DbEdtNmTabela: TDBEdit;
    SbtGravar: TBrvSpeedButton;
    SbtCancel: TBrvSpeedButton;
    QcTabAtr: TBrvClientDataSet;
    DsTabAtr: TDataSource;
    QpTabAtr: TBrvClientDataSet;
    TbsTabAtr: TTabSheet;
    PgcAtribu: TPageControl;
    TbsAtribu: TTabSheet;
    TbsHelp: TTabSheet;
    DBMemo1: TDBMemo;
    Panel1: TPanel;
    Label3: TLabel;
    DBEdit1: TDBEdit;
    Label4: TLabel;
    DBEdit2: TDBEdit;
    Label5: TLabel;
    DBEdit3: TDBEdit;
    BrvDBComboListBox1: TBrvDBComboListBox;
    Label6: TLabel;
    LblTmAtribu: TLabel;
    DBEdit4: TDBEdit;
    Label8: TLabel;
    BrvDBComboListBox2: TBrvDBComboListBox;
    LblTpMascar: TLabel;
    DBEdit5: TDBEdit;
    Label10: TLabel;
    DBEdit6: TDBEdit;
    GroupBox1: TGroupBox;
    Label11: TLabel;
    Label12: TLabel;
    DBEdit7: TDBEdit;
    DBEdit8: TDBEdit;
    Splitter3: TSplitter;
    QcDomAtr: TBrvClientDataSet;
    DsDomAtr: TDataSource;
    QpChaPri: TBrvClientDataSet;
    QcChaPri: TBrvClientDataSet;
    DsChaPri: TDataSource;
    TbsChaPri: TTabSheet;
    Label7: TLabel;
    DBEdit9: TDBEdit;
    Label9: TLabel;
    DBEdit10: TDBEdit;
    Label13: TLabel;
    DBEdit11: TDBEdit;
    QcChaEst: TBrvClientDataSet;
    TbsChaEst: TTabSheet;
    Label14: TLabel;
    DBEdit12: TDBEdit;
    Label15: TLabel;
    DBEdit13: TDBEdit;
    Label16: TLabel;
    DBEdit14: TDBEdit;
    DsChaEst: TDataSource;
    QpChaEst: TBrvClientDataSet;
    QcColEst: TBrvClientDataSet;
    DsColEst: TDataSource;
    TbsColEst: TTabSheet;
    Label17: TLabel;
    DBEdit15: TDBEdit;
    Label18: TLabel;
    DBEdit16: TDBEdit;
    Label20: TLabel;
    DBEdit18: TDBEdit;
    QpColEst: TBrvClientDataSet;
    Label22: TLabel;
    DBEdit20: TDBEdit;
    TbsSql: TTabSheet;
    MemSql: TRichEdit;
    Panel2: TPanel;
    PnlDados: TPanel;
    GbxNrPosKey: TGroupBox;
    EdtNrPosKey: TSpinEdit;
    DbgSQL: TBrvDbGrid;
    QSql: TBrvClientDataSet;
    DSql: TDataSource;
    Panel4: TPanel;
    SbtCommit: TBrvSpeedButton;
    SbtRollba: TBrvSpeedButton;
    SbtEdit: TSpeedButton;
    PnlResult: TPanel;
    SbtExport: TSpeedButton;
    BrvExport: TBrvExport;
    BrvSpeedButton3: TBrvSpeedButton;
    BrvRelato: TRelatorio;
    BbtSalSql: TBrvSpeedButton;
    DbgSqlUsr: TBrvDbGrid;
    BbtNewSql: TBrvSpeedButton;
    QcSqlUsr: TBrvClientDataSet;
    DsSqlUsr: TDataSource;
    BrvOracle: TBrvOracle;
    PopSqlUsr: TPopupMenu;
    MenuItem2: TMenuItem;
    QpTrigger: TBrvClientDataSet;
    TbsTrigge: TTabSheet;
    QcTrigger: TBrvClientDataSet;
    DsTrigger: TDataSource;
    DBRichEdit1: TDBRichEdit;
    Panel3: TPanel;
    Label19: TLabel;
    DBEdit17: TDBEdit;
    QpIndice: TBrvClientDataSet;
    QpAtrInd: TBrvClientDataSet;
    QcIndice: TBrvClientDataSet;
    DsIndice: TDataSource;
    TbsIndice: TTabSheet;
    Label21: TLabel;
    DBEdit19: TDBEdit;
    Label23: TLabel;
    DBEdit21: TDBEdit;
    DBCheckBox1: TDBCheckBox;
    QcColInd: TBrvClientDataSet;
    DsColInd: TDataSource;
    TbsColInd: TTabSheet;
    Label24: TLabel;
    Label25: TLabel;
    Label26: TLabel;
    Label27: TLabel;
    DBEdit22: TBrvDBEdit;
    DBEdit23: TDBEdit;
    DBEdit24: TBrvDBEdit;
    DBEdit25: TDBEdit;
    PnlTmSql: TPanel;
    TbsProced: TTabSheet;
    Panel5: TPanel;
    Label28: TLabel;
    DBEdit26: TDBEdit;
    DBRichEdit2: TDBRichEdit;
    QcProced: TBrvClientDataSet;
    DsProced: TDataSource;
    QpProced: TBrvClientDataSet;
    QcExcess: TBrvClientDataSet;
    DsExcess: TDataSource;
    QpExcess: TBrvClientDataSet;
    TbsExcess: TTabSheet;
    Panel6: TPanel;
    Label29: TLabel;
    DBEdit27: TDBEdit;
    DBRichEdit3: TDBRichEdit;
    QpFormul: TBrvClientDataSet;
    QcForDin: TBrvClientDataSet;
    DsForDin: TDataSource;
    TbsForDin: TTabSheet;
    Panel7: TPanel;
    DBCheckBox2: TDBCheckBox;
    DBCheckBox3: TDBCheckBox;
    Panel8: TPanel;
    DBRichEdit4: TDBRichEdit;
    Panel9: TPanel;
    Label30: TLabel;
    DBEdit28: TDBEdit;
    SbtForDin: TSpeedButton;
    Splitter2: TSplitter;
    DBText1: TDBText;
    GbxSubFor: TGroupBox;
    Panel10: TPanel;
    BrvDbGrid1: TBrvDbGrid;
    QpSubFor: TBrvClientDataSet;
    DsSubFor: TDataSource;
    BrvSpeedButton2: TBrvSpeedButton;
    BrvSpeedButton5: TBrvSpeedButton;
    BrvConSub: TBrvConsulta;
    LblNrSubFor: TLabel;
    PgcTabAtr: TPageControl;
    TbsDomAtr: TTabSheet;
    GbxDomAtr: TGroupBox;
    DBGrid1: TDBGrid;
    TbsPerAtr: TTabSheet;
    Splitter4: TSplitter;
    GroupBox2: TGroupBox;
    DbgPerGru: TBrvDbGrid;
    GbxPerUsu: TGroupBox;
    DbgPerUsu: TBrvDbGrid;
    CdsTmpGru: TClientDataSet;
    DtsTmpGru: TDataSource;
    CdsTmpUsu: TClientDataSet;
    DtsTmpUsu: TDataSource;
    QcPerGru: TBrvClientDataSet;
    QcPerUsu: TBrvClientDataSet;
    Panel11: TPanel;
    SbtImprim: TBrvSpeedButton;
    BrnAtuCli: TBrvSpeedButton;
    QcView: TBrvClientDataSet;
    DsView: TDataSource;
    TbsView: TTabSheet;
    Panel12: TPanel;
    Label31: TLabel;
    DBEdit29: TDBEdit;
    QpView: TBrvClientDataSet;
    Label32: TLabel;
    Label33: TLabel;
    BrvDBComboListBox3: TBrvDBComboListBox;
    TbsValAtr: TTabSheet;
    DBGrid2: TBrvDBGrid;
    QcValAtr: TBrvClientDataSet;
    DsValAtr: TDataSource;
    BrvDBRetCon1: TBrvDBRetCon;
    BrvDBRetCon2: TBrvDBRetCon;
    BrvDBRetCon3: TBrvDBRetCon;
    BrvDBRetCon4: TBrvDBRetCon;
    DBMemo2: TDBMemo;
    DBRichEdit5: TDBMemo;
    QpModulo: TClientDataSet;
    QpTabAux: TClientDataSet;
    SbtExeSql: TBrvSpeedButton;
    BtnTraduzir: TBrvSpeedButton;
    EdtAtuDicSrv: TBrvSpeedButton;
    GbxNrPosAtr: TGroupBox;
    EdtNrPosAtr: TSpinEdit;
    Label34: TLabel;
    ReiniciarVisualizao1: TMenuItem;
    procedure FormCreate(Sender: TObject);
    procedure TrvDicionDblClick(Sender: TObject);
    procedure PopIncluiClick(Sender: TObject);
    procedure DsTabelaStateChange(Sender: TObject);
    procedure SbtCancelClick(Sender: TObject);
    procedure SbtGravarClick(Sender: TObject);
    procedure QcTabelaAfterPost(DataSet: TDataSet);
    procedure QcTabelaBeforePost(DataSet: TDataSet);
    procedure FormKeyDown(Sender: TObject; var Key: Word; Shift: TShiftState);
    procedure TrvDicionChanging(Sender: TObject; Node: TTreeNode;
      var AllowChange: Boolean);
    procedure TrvDicionChange(Sender: TObject; Node: TTreeNode);
    procedure QcTabAtrAfterPost(DataSet: TDataSet);
    procedure QcTabAtrBeforePost(DataSet: TDataSet);
    procedure BrvDBComboListBox1Change(Sender: TObject);
    procedure BrvDBComboListBox2Change(Sender: TObject);
    procedure QcDomAtrBeforePost(DataSet: TDataSet);
    procedure QcDomAtrAfterInsert(DataSet: TDataSet);
    procedure QcDomAtrAfterPost(DataSet: TDataSet);
    procedure QcChaPriAfterPost(DataSet: TDataSet);
    procedure QcChaPriBeforePost(DataSet: TDataSet);
    procedure EdtNrPosKeyChange(Sender: TObject);
    procedure QcChaEstAfterPost(DataSet: TDataSet);
    procedure QcChaEstBeforePost(DataSet: TDataSet);
    procedure QcColEstAfterPost(DataSet: TDataSet);
    procedure QcColEstBeforePost(DataSet: TDataSet);
    procedure PopScriptClick(Sender: TObject);
    procedure PgcGeralChange(Sender: TObject);
    procedure PopDicPopup(Sender: TObject);
    procedure PopExcluiClick(Sender: TObject);
    procedure SbtExeSqlClick(Sender: TObject);
    procedure MemSqlKeyDown(Sender: TObject; var Key: Word; Shift: TShiftState);
    procedure SbtEditClick(Sender: TObject);
    procedure QSqlAfterPost(DataSet: TDataSet);
    procedure SbtCommitClick(Sender: TObject);
    procedure SbtExportClick(Sender: TObject);
    procedure BrvSpeedButton3Click(Sender: TObject);
    procedure MemSqlChange(Sender: TObject);
    procedure BbtNewSqlClick(Sender: TObject);
    procedure BbtSalSqlClick(Sender: TObject);
    procedure DbgSqlUsrDblClick(Sender: TObject);
    procedure MenuItem2Click(Sender: TObject);
    procedure QcTriggerBeforePost(DataSet: TDataSet);
    procedure QcTriggerAfterPost(DataSet: TDataSet);
    procedure QcIndiceBeforePost(DataSet: TDataSet);
    procedure QcIndiceAfterPost(DataSet: TDataSet);
    procedure QcColIndAfterPost(DataSet: TDataSet);
    procedure QcColIndBeforePost(DataSet: TDataSet);
    procedure QcProcedAfterPost(DataSet: TDataSet);
    procedure QcProcedBeforePost(DataSet: TDataSet);
    procedure QcExcessAfterPost(DataSet: TDataSet);
    procedure QcExcessBeforePost(DataSet: TDataSet);
    procedure QcForDinAfterPost(DataSet: TDataSet);
    procedure QcForDinBeforePost(DataSet: TDataSet);
    procedure SbtForDinClick(Sender: TObject);
    procedure DBEdit24BrOnBeforeConsulta(Sender: TObject);
    procedure BrvSpeedButton2Click(Sender: TObject);
    procedure BrvSpeedButton5Click(Sender: TObject);
    procedure PgcTabAtrChange(Sender: TObject);
    procedure CdsTmpGruBeforeInsert(DataSet: TDataSet);
    procedure CdsTmpGruBeforePost(DataSet: TDataSet);
    procedure CdsTmpGruAfterPost(DataSet: TDataSet);
    procedure CdsTmpGruAfterScroll(DataSet: TDataSet);
    procedure CdsTmpUsuAfterPost(DataSet: TDataSet);
    procedure SbtImprimClick(Sender: TObject);
    procedure BrnAtuCliClick(Sender: TObject);
    procedure QcViewAfterPost(DataSet: TDataSet);
    procedure QcViewBeforePost(DataSet: TDataSet);
    procedure QcValAtrAfterPost(DataSet: TDataSet);
    procedure QcValAtrBeforePost(DataSet: TDataSet);
    procedure QcTabAtrAfterCancel(DataSet: TDataSet);
    procedure BtnTraduzirClick(Sender: TObject);
    procedure EdtAtuDicSrvClick(Sender: TObject);
    procedure Atualizar1Click(Sender: TObject);
  private
    { Private declarations }
    gNdNode   : PString;
    gTpOperac : Integer; // 0 = nada, 1 = incluindo, 2 = editando
    procedure MontarNivel(pNoImagem: Integer);
    procedure CarregarTabelas;
    procedure MontarArvoreAtributosTabela;
    procedure CarregarAtributosDaTabela(pNmTabela: String);
    procedure CarregarAtributosDaChavePrimaria(pNmTabela : String);
    procedure AtualizarPosicaoChave(pTpChave: String; pNmChaEst, pNmTabela : String;
                                    pNrNewPos : Integer; pNrOldPos : Integer);
    procedure CarregarChavesEstrangeiras(pNmTabela: String);
    procedure CarregarAtributosChaveEstrangeira(pNmChaEst: String);
    procedure ExcluirTabela(pNmTabela: String);
    procedure ExcluirAtributo(pNmTabela: String; pNmAtribu: String);
    procedure ExcluirAtributoPrimario(pNmTabela, pNmAtribu: String);
    procedure ExcluirChaveEstrangeira(pNmChaEst: String);
    procedure ExcluirAtributoChaveEstrangeira(pNmChaEst: String; pNmAtribu : String);
    function  ExecutarSql(pDsSql: String) : Integer;
    function NomeDaTabelaDaInstrucaoSql(pDsSql: String): String;
    procedure SalvarInstrucaoSqlUsuario;
    procedure SalvarComoInstrucaoSqlUsuario;
    procedure CarregarTrigger(pNmTabela: String);
    procedure CarregarIndices(pNmTabela: String);
    procedure CarregarAtributosIndice(pNmTabela : String; pNmIndice : String);
    procedure ExcluirIndiceSecundario(pNmIndice: String);
    procedure ExcluirAtributoIndiceSecundario(pNmIndice, pNmAtribu: String);
    procedure CarregarProcedimentos;
    procedure CarregarViews;
    procedure CarregarExcessoes;
    procedure CarregarFormularios(pNmTabela: String);
    procedure ExcluirFormularioDinamico(pNrForDin: String);
    procedure CarregarSubFormulariosDinamico(pNrForDin: String);
    procedure AtualizarPosicaoAtributo(pNmTabela : String; pNrNewPos : Integer; pNrOldPos: Integer);
    procedure IniciarTreeView;
  public
    { Public declarations }
  end;

var
  Cad0001: TCad0001;

implementation

uses UDmSrvApl, UDmSis, UCad0002, URel0001, UCai0011;

const gImgBanco  = 0;  // Banco
      gImgGruTab = 1;  // Grupo de Tabelas
      gImgGruPro = 2;  // Grupo de Procedures
      gImgTabela = 3;  // Tabela
      gImgGruCol = 4;  // Grupo de Colunas
      gImgGruKey = 5;  // Grupo de Chave Primária
      gImgGruEst = 6;  // Grupo de chave estrangeira
      gImgColuna = 7;  // Atributos
      gImgColKey = 8;  // Atributo da chave primária
      gImgEstKey = 9;  // Chave estrangeira
      gImgColEst = 10; // Atributo estrangeiro
      gImgEditar = 11; // Edição
      gImgGruTri = 12; // Grupo de Triggers
      gImgTrigge = 13; // trigger
      gImgGruInd = 14; // Grupo de Índices
      gImgIndice = 15; // índice
      gImgColInd = 16; // atributo do índice
      gImgProced = 17; // imagem do procedimento
      gImgGruExc = 18; // Grupo de Excessões
      gImgExcess = 19; // Excessão
      gImgGruFor = 20; // Grupo de formulários
      gImgFormul = 21; // Formulários
      gImgGruVie = 22; // Grupo de View
      gImgView   = 23; // View
      gImgEstCon = 24; // Chave estrangeira somente para consulta
      gImgModTab = 25; // Modulos

{$R *.dfm}

procedure TCad0001.DBEdit24BrOnBeforeConsulta(Sender: TObject);
begin
      (Sender as TBrvDbEdit).BrParams.Clear;
      (Sender as TBrvDbEdit).BrParams.Add('<%NmTabela%>;' +
                                               QcColInd.FieldByName('NmTabela').AsString);
end;

procedure TCad0001.DbgSqlUsrDblClick(Sender: TObject);
begin
      if BbtSalSql.Enabled then
      begin
            if MessageDlg('Deseja salvar instrução em edição?', mtConfirmation,
                                                            [mbYes, mbNo], 0) = idYes then
            begin
                  SalvarInstrucaoSqlUsuario;
            end;
      end;

      MemSql.Lines.Text := QcSqlUsr.FieldByName('TxSqlUsr').AsString;
      MemSql.Tag        := QcSqlUsr.FieldByName('NrSqlUsr').AsInteger;
      BbtSalSql.Enabled := False;
      BbtNewSql.Enabled := True;
end;

procedure TCad0001.DsTabelaStateChange(Sender: TObject);
begin
      inherited;

      if (Sender as TDataSource).DataSet.State in [dsInsert, dsEdit] then
      begin
            if (Sender as TDataSource).DataSet.State in [dsInsert] then
            begin
                  gTpOperac := 1;
            end else
            begin
                  gTpOperac := 2;
            end;

            SbtGravar.Enabled := True;
            SbtCancel.Enabled := True;
            SbtForDin.Enabled := False;
      end else
      begin
            SbtGravar.Enabled := False;
            SbtCancel.Enabled := False;
            SbtForDin.Enabled := True;
      end;
end;

procedure TCad0001.EdtNrPosKeyChange(Sender: TObject);
begin
      if PgcBanco.ActivePage = TbsChaPri then
      begin
            DsTabelaStateChange(DsChaPri);
      end else
      begin
           if PgcBanco.ActivePage = TbsColEst then
           begin
                 DsTabelaStateChange(DsColEst);
           end else
           begin
                 if PgcBanco.ActivePage = TbsColInd then
                 begin
                       DsTabelaStateChange(DsColInd);
                 end else
                 begin
                       if PgcBanco.ActivePage = TbsTabAtr then
                       begin
                             QcTabATr.Edit;
                             DsTabelaStateChange(DsTabAtr);
                       end;
                 end;
           end;
      end;
end;

procedure TCad0001.IniciarTreeView;
var   lNdNovo   : TTreeNode;
begin
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

      lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected, 'Excessões',
                                                gNdNode);
      PString(lNdNovo.Data)^ := 'EXCESSOES';
      lNdNovo.ImageIndex    := gImgGruExc;
      lNdNovo.SelectedIndex := gImgGruExc;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      TrvDicion.Selected.Expand(True);
end;

procedure TCad0001.FormCreate(Sender: TObject);
var lNoPagina : Integer;
begin
      inherited;

      gTpOperac := 0;

      PgcGeral.ActivePage := TbsBanco;

      for lNoPagina := 0 to PgcBanco.PageCount -1 do
      begin
            PgcBanco.Pages[lNoPagina].TabVisible := False;
      end;

      PgcBanco.ActivePage := TbsNada;

      IniciarTreeView;

      // =-=-=-=-= carregando sql do usuário
      QcSqlUsr.Close;
      QcSqlUsr.BrParams.Clear;
      QcSqlUsr.BrParams.Add('<%CdUsuari%>;' +  IntToStr(DmSrvApl.BrvDicionario.UserCode));
      QcSqlUsr.Open;
end;

procedure TCad0001.FormKeyDown(Sender: TObject; var Key: Word; Shift: TShiftState);
var lTpOperac : Integer;
begin
      if (ssCtrl in Shift) then
      begin
            if (key = 83) and (SbtGravar.Enabled) then // Ctrl S
            begin
                  lTpOperac := gTpOperac;

                  SbtGravarClick(SbtGravar);

                  if lTpOperac = 1 then // incluindo
                  begin
                        TrvDicion.Selected := TrvDicion.Selected.Parent;
                        TrvDicion.SetFocus;

                        if TrvDicion.Selected.ImageIndex in [gImgGruCol, gImgGruKey,
                                                             gImgEstKey, gImgEstCon,
                                                             gImgIndice] then
                        begin
                              PopIncluiClick(nil);
                        end;
                  end;

            end
            else if (key = 78) and (SbtGravar.Enabled) then // Ctrl N
                 begin
                       SbtGravarClick(SbtGravar);
                 end
            ;
      end;
end;

procedure TCad0001.TrvDicionChange(Sender: TObject; Node: TTreeNode);
var lNrForDin : String;
    lDsComWhe : String;
begin
      if (TrvDicion.Tag = 0) then
      begin
            case TrvDicion.Selected.ImageIndex of
                 gImgBanco,  gImgGruTab, gImgGruPro, gImgGruCol, gImgGruEst,
                 gImgGruKey, gImgGruTri, gImgGruExc, gImgGruVie:
                              begin
                                    PgcBanco.ActivePage := TbsNada;
                              end;
                 gImgTabela :
                              begin
                                    QcTabela.Close;
                                    lDsComWhe := '= ' + #39 +  PString(TrvDicion.Selected.Data)^
                                                                                              + #39;
                                    QcTabela.BrParams.Clear;
                                    QcTabela.BrParams.Add('<%NmTabela%>;' + lDsComWhe);
                                    QcTabela.Open;
                                    PgcBanco.ActivePage   := TbsTabela;
                              end;
                 gImgColuna : begin
                                    lDsComWhe := 'S009.NmTabela = ' + #39 +
                                             PString(TrvDicion.Selected.Parent.Parent.Data)^ + #39 +
                                             ' and S009.NmAtribu = ' + #39 +
                                             PString(TrvDicion.Selected.Data)^ + #39;
                                    QcTabAtr.Close;
                                    QcTabAtr.BrParams.Clear;
                                    QcTabAtr.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcTabATr.Open;

                                    // =-=-=-=-=-=-=-=-=
                                    QcDomAtr.Close;
                                    QcDomAtr.BrParams.Clear;
                                    QcDomAtr.BrParams.Add('<%NmTabela%>;' +
                                                   PString(TrvDicion.Selected.Parent.Parent.Data)^);
                                    QcDomAtr.BrParams.Add('<%NmAtribu%>;' +
                                                      PString(TrvDicion.Selected.Data)^);
                                    QcDomAtr.Open;
                                    // =-=-=-=-=-=-=-=-=

                                    QcValAtr.Close;
                                    QcValAtr.BrParams.Clear;
                                    QcValAtr.BrParams.Add('<%NmTabela%>;' +
                                                   PString(TrvDicion.Selected.Parent.Parent.Data)^);
                                    QcValAtr.BrParams.Add('<%NmAtribu%>;' +
                                                      PString(TrvDicion.Selected.Data)^);
                                    QcValAtr.Open;

                                    // =-=-=-=-=-=-=-=-=
                                    BrvDBComboListBox1Change(BrvDBComboListBox1);
                                    BrvDBComboListBox2Change(BrvDBComboListBox2);
                                    PgcTabAtr.Visible    := True;
                                    PgcTabAtr.Tag        := 0;
                                    PgcTabAtr.ActivePage := TbsDomAtr;
                                    PgcBanco.ActivePage  := TbsTabAtr;
                                    PgcAtribu.ActivePage := TbsAtribu;

                                    EdtNrPosAtr.Value    :=
                                                         QcTabAtr.FieldByName('NrSeqAtr').AsInteger;
                                    EdtNrPosAtr.Tag      :=
                                                         QcTabAtr.FieldByName('NrSeqAtr').AsInteger;
                                    EdtNrPosAtr.MaxValue :=
                                                         TrvDicion.Selected.Parent.Count -1;
                              end;
                 gImgColKey : begin
                                    lDsComWhe := 'S011.NmTabela = ' + #39 +
                                            PString(TrvDicion.Selected.Parent.Parent.Data)^ + #39 +
                                            ' and S011.NmAtribu = ' + #39 +
                                                            PString(TrvDicion.Selected.Data)^ + #39;
                                    QcChaPri.Close;
                                    QcChaPri.BrParams.Clear;
                                    QcChaPri.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcChaPri.Open;
                                    QcChaPri.Edit;

                                    GbxNrPosKey.Visible  := True;
                                    GbxNrPosKey.Parent   := TbsChaPri;
                                    EdtNrPosKey.Value    := TrvDicion.Selected.Index;
                                    EdtNrPosKey.Tag      := TrvDicion.Selected.Index;
                                    EdtNrPosKey.MaxValue :=
                                                       TrvDicion.Selected.Parent.Count -1;
                                    PgcBanco.ActivePage  := TbsChaPri;
                              end;
                 gImgEstKey,
                 gImgEstCon : begin
                                    lDsComWhe := 'S012.NmChaEst = ' + #39 +
                                                            PString(TrvDicion.Selected.Data)^ + #39;
                                    QcChaEst.Close;
                                    QcChaEst.BrParams.Clear;
                                    QcChaEst.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcChaEst.Open;

                                    PgcBanco.ActivePage := TbsChaEst;
                              end;
                 gImgColEst : begin
                                    lDsComWhe := 'S013.NmChaEst = ' + #39 +
                                                    PString(TrvDicion.Selected.Parent.Data)^ + #39 +
                                                 ' and S013.NmAtribu = ' + #39 +
                                                            PString(TrvDicion.Selected.Data)^ + #39;
                                    QcColEst.Close;
                                    QcColEst.BrParams.Clear;
                                    QcColEst.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcColEst.Open;
                                    QcColEst.Edit;

                                    GbxNrPosKey.Visible  := True;
                                    GbxNrPosKey.Parent   := TbsColEst;
                                    EdtNrPosKey.Value    := TrvDicion.Selected.Index;
                                    EdtNrPosKey.Tag      := TrvDicion.Selected.Index;
                                    EdtNrPosKey.MaxValue :=
                                                       TrvDicion.Selected.Parent.Count -1;

                                    PgcBanco.ActivePage := TbsColEst;
                              end;
                 gImgTrigge : begin
                                    lDsComWhe := 'S020.NmTrigge = ' + #39 +
                                                            PString(TrvDicion.Selected.Data)^ + #39;
                                    QcTrigger.Close;
                                    QcTrigger.BrParams.Clear;
                                    QcTrigger.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcTrigger.Open;
                                    PgcBanco.ActivePage := TbsTrigge;
                                    DBEdit17.Enabled    := False;
                              end;
                 gImgIndice : begin
                                    lDsComWhe := 'S018.NmIndice = ' + #39 +
                                                            PString(TrvDicion.Selected.Data)^ + #39;
                                    QcIndice.Close;
                                    QcIndice.BrParams.Clear;
                                    QcIndice.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcIndice.Open;
                                    PgcBanco.ActivePage := TbsIndice;
                                    DBEdit21.Enabled    := False;
                              end;
                 gImgColInd : begin
                                    lDsComWhe := 'S019.NmIndice = ' + #39 +
                                                    PString(TrvDicion.Selected.Parent.Data)^ + #39 +
                                                 ' and S019.NmAtribu = ' + #39 +
                                                    PString(TrvDicion.Selected.Data)^ + #39;
                                    QcColInd.Close;
                                    QcColInd.BrParams.Clear;
                                    QcColInd.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcColInd.Open;
                                    QcColInd.Edit;

                                    GbxNrPosKey.Visible  := True;
                                    GbxNrPosKey.Parent   := TbsColInd;
                                    EdtNrPosKey.Value    := TrvDicion.Selected.Index;
                                    EdtNrPosKey.Tag      := TrvDicion.Selected.Index;
                                    EdtNrPosKey.MaxValue :=
                                                       TrvDicion.Selected.Parent.Count -1;
                                    PgcBanco.ActivePage  := TbsColInd;
                              end;
                 gImgProced : begin
                                    lDsComWhe := 'S021.NmProced = ' + #39 +
                                                            PString(TrvDicion.Selected.Data)^ + #39;
                                    QcProced.Close;
                                    QcProced.BrParams.Clear;
                                    QcProced.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcProced.Open;
                                    PgcBanco.ActivePage := TbsProced;
                                    DBEdit21.Enabled    := False;
                              end;
                 gImgView   : begin
                                    lDsComWhe := 'S040.NmView = ' + #39 +
                                                            PString(TrvDicion.Selected.Data)^ + #39;
                                    QcView.Close;
                                    QcView.BrParams.Clear;
                                    QcView.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcView.Open;
                                    PgcBanco.ActivePage := TbsView;
                                    DBEdit29.Enabled    := False;
                              end;
                 gImgExcess : begin
                                    lDsComWhe := 'S025.NmExcess = ' + #39 +
                                                            PString(TrvDicion.Selected.Data)^ + #39;
                                    QcExcess.Close;
                                    QcExcess.BrParams.Clear;
                                    QcExcess.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcExcess.Open;
                                    PgcBanco.ActivePage := TbsExcess;
                                    DBEdit27.Enabled    := False;
                              end;
                 gImgFormul : begin
                                    lNrForDin := PString(TrvDicion.Selected.Data)^;
                                    lDsComWhe := 'S014.NrForDin = ' + #39 + lNrForDin + #39;
                                    QcForDin.Close;
                                    QcForDin.BrParams.Clear;
                                    QcForDin.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                                    QcForDin.Open;
                                    PgcBanco.ActivePage := TbsForDin;

                                    CarregarSubFormulariosDinamico(lNrForDin);
                              end;
            end;
      end;
end;

procedure TCad0001.CarregarSubFormulariosDinamico(pNrForDin : String);
begin
      QpSubFor.Close;
      QpSubFor.BrParams.Clear;
      QpSubFor.BrParams.Add('<%NrForDin%>;' + pNrForDin);
      QpSubFor.Open;
end;

procedure TCad0001.TrvDicionChanging(Sender: TObject; Node: TTreeNode;
  var AllowChange: Boolean);
begin
      if (TrvDicion.Tag = 0) and (TrvDicion.Selected <> nil) then
      begin
            if SbtGravar.Enabled then
            begin
                  SbtCancelClick(nil);
            end;
      end;
end;

procedure TCad0001.TrvDicionDblClick(Sender: TObject);
begin
      if not TrvDicion.Selected.HasChildren then
      begin
            MontarNivel(TrvDicion.Selected.ImageIndex);
      end;
end;

procedure TCad0001.CarregarTabelas;
var lNdNovo   : TTreeNode;
    lNdNovTab : TTreeNode;
begin
     QpTabAux.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                                            'Select * From S008 Order by NmTabela');

     QpModulo.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                                            'Select * From S050 Order by DsModulo');

     while not QpModulo.Eof do
     begin
           New(gNdNode);
           lNdNovo := TrvDicion.Items.AddChildObject(
                                      TrvDicion.Selected,
                                      QpModulo.FieldByName('DsModulo').AsString,
                                      gNdNode);
           PString(lNdNovo.Data)^ :=  QpModulo.FieldByName('CdModulo').AsString;

           lNdNovo.ImageIndex    := gImgModTab;
           lNdNovo.SelectedIndex := gImgModTab;

           QpTabela.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                     'Select * From S008 ' +
                                     'Where NmTabela like "' +
                                       Copy(QpModulo.FieldByName('DsMasMod').AsString,1,1) + '%" ' +
                                     'Order by NmTabela');

           while not QpTabela.Eof do
           begin
                 New(gNdNode);
                 lNdNovTab := TrvDicion.Items.AddChildObject(lNdNovo,
                                            QpTabela.FieldByName('NmTabela').AsString + ' - ' +
                                            UpperCase(QpTabela.FieldByName('DsTabela').AsString),
                                            gNdNode);
                 PString(lNdNovTab.Data)^ :=  QpTabela.FieldByName('NmTabela').AsString;

                 lNdNovTab.ImageIndex    := gImgTabela;
                 lNdNovTab.SelectedIndex := gImgTabela;

                 if (QpTabAux.Locate('NmTabela', QpTabela.FieldByName('NmTabela').AsString,
                                                                          [loCaseInsensitive])) then
                 begin
                       QpTabAux.Delete;
                 end;

                 QpTabela.Next;
           end;

           lNdNovo.Collapse(True);

           QpTabela.Close;
           QpModulo.Next;
     end;

     //Tabelas órfãs

     if (QpTabAux.RecordCount > 0) then
     begin
           New(gNdNode);
           lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected, 'SEM MÓDULO', gNdNode);
           PString(lNdNovo.Data)^ :=  '0';

           lNdNovo.ImageIndex    := gImgModTab;
           lNdNovo.SelectedIndex := gImgModTab;

           QpTabAux.First;

           while not QpTabAux.Eof do
           begin
                 New(gNdNode);
                 lNdNovTab := TrvDicion.Items.AddChildObject(lNdNovo,
                                            QpTabAux.FieldByName('NmTabela').AsString + ' - ' +
                                            UpperCase(QpTabAux.FieldByName('DsTabela').AsString),
                                            gNdNode);
                 PString(lNdNovTab.Data)^ :=  QpTabAux.FieldByName('NmTabela').AsString;

                 lNdNovTab.ImageIndex    := gImgTabela;
                 lNdNovTab.SelectedIndex := gImgTabela;
                 QpTabAux.Next;
           end;

           lNdNovo.Collapse(True);

     end;

     TrvDicion.Selected.Expand(False);
     QpTabAux.Close;
end;

procedure TCad0001.MemSqlChange(Sender: TObject);
begin
      BbtSalSql.Enabled := True;
      BbtNewSql.Enabled := True;
end;

procedure TCad0001.MemSqlKeyDown(Sender: TObject; var Key: Word; Shift: TShiftState);
begin
      if ssCtrl in Shift then
      begin
            case key of
                 69 : begin
                            SbtExeSqlClick(SbtExeSql);
                            Abort;
                      end;
            end;
      end else
      begin
            if key = 120 then
            begin
                  SbtExeSqlClick(SbtExeSql);
                  Abort;
            end;
      end;
end;

procedure TCad0001.MenuItem2Click(Sender: TObject);
begin
      if QcSqlUsr.FieldByName('NrSqlUsr').AsInteger <> 0 then
      begin
            if MessageDlg('Confirma a exclusão desta instrução SQL?', mtConfirmation,
                                                            [mbYes, mbNo], 0) = IdYes then
            begin
                  QcSqlUsr.Delete;
                  QcSqlUsr.ApplyUpdates(0);
            end;
      end;
end;

procedure TCad0001.MontarArvoreAtributosTabela;
var lNdNovo   : TTreeNode;
begin
      // Colunas
         New(gNdNode);
         lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                                  'Atributos', gNdNode);
         PString(lNdNovo.Data)^ := 'Atributos';
         lNdNovo.ImageIndex     := gImgGruCol;
         lNdNovo.SelectedIndex  := gImgGruCol;

      // Chave Primária
         New(gNdNode);
         lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                                   'Chave Primária', gNdNode);
         PString(lNdNovo.Data)^ := 'Chave Primária';
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

      // Índices
         New(gNdNode);
         lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                                   'Índices', gNdNode);
         PString(lNdNovo.Data)^ := 'Índices';
         lNdNovo.ImageIndex     := gImgGruInd;
         lNdNovo.SelectedIndex  := gImgGruInd;

      // Formulários dinâmicos
         New(gNdNode);
         lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                                   'Formulários Dinâmicos', gNdNode);
         PString(lNdNovo.Data)^ := 'Formulários';
         lNdNovo.ImageIndex     := gImgGruFor;
         lNdNovo.SelectedIndex  := gImgGruFor;
end;

procedure TCad0001.BbtNewSqlClick(Sender: TObject);
begin
      if BbtSalSql.Enabled then
      begin
            if MessageDlg('Deseja salvar instrução em edição?', mtConfirmation,
                                                            [mbYes, mbNo], 0) = idYes then
            begin
                  SalvarInstrucaoSqlUsuario;
            end;
      end;

      MemSql.Lines.Clear;
      MemSql.Tag        := 0;
      BbtSalSql.Enabled := False;
      BbtNewSql.Enabled := False;
end;

procedure TCad0001.BbtSalSqlClick(Sender: TObject);
begin
      SalvarInstrucaoSqlUsuario;
      BbtSalSql.Enabled := False;
end;

procedure TCad0001.SalvarInstrucaoSqlUsuario;
begin
      if MemSql.Tag = 0 then
      begin
            SalvarComoInstrucaoSqlUsuario;
      end else
      begin
            QcSqlUsr.Edit;
            QcSqlUsr.FieldByName('TxSqlUsr').AsString := MemSql.Lines.Text;
            QcSqlUsr.Post;
            QcSqlUsr.ApplyUpdates(0);
      end;
end;

procedure TCad0001.SalvarComoInstrucaoSqlUsuario;
var lDsSqlUsr : String;
begin
      QcSqlUsr.Last;
      MemSql.Tag := QcSqlUsr.FieldByName('NrSqlUsr').AsInteger + 1;

      lDsSqlUsr := InputBox('Descrição da Sql', 'Descrição', 'Sql Nr. ' +
                                                                   IntToStr(MemSql.Tag));

      QcSqlUsr.Append;
      QcSqlUsr.FieldByName('NrSqlUsr').AsInteger := MemSql.Tag;
      QcSqlUsr.FieldByName('CdUsuari').AsInteger := DmSrvApl.BrvDicionario.UserCode;
      QcSqlUsr.FieldByName('TxSqlUsr').AsString  := MemSql.Lines.Text;
      QcSqlUsr.FieldByName('DsSqlUsr').AsString  := lDsSqlUsr;
      QcSqlUsr.Post;
      QcSqlUsr.ApplyUpdates(0);
end;

procedure TCad0001.BrvDBComboListBox1Change(Sender: TObject);
begin
      if (Sender as TBrvDbComboListBox).ItemIndex  in [0, 1, 2, 4] then
      begin
            LblTmAtribu.Font.Color := clBlack;
      end else
      begin
            LblTmAtribu.Font.Color := clBlue;
      end;
end;

procedure TCad0001.BrvDBComboListBox2Change(Sender: TObject);
begin
      if (Sender as TBrvDbComboListBox).ItemIndex  = 3 then
      begin
            LblTpMascar.Font.Color := clBlue;
      end else
      begin
            LblTpMascar.Font.Color := clBlack;
      end;
end;

procedure TCad0001.BrvSpeedButton2Click(Sender: TObject);
begin
      if QpSubFor.FieldByName('NrSubFor').AsInteger <> 0 then
      begin
            if MessageDlg('Confirma a exclusão deste sub-formulário?', mtConfirmation,
                                                            [mbYes, mbNo], 0) = Idyes then
            begin
                  QpSubFor.Delete;
                  QpSubFor.ApplyUpdates(0);
            end;
      end;
end;

procedure TCad0001.BrvSpeedButton3Click(Sender: TObject);
var lNmTabela : String;
begin
      lNmTabela := NomeDaTabelaDaInstrucaoSql(QSql.CommandText);
      BrvRelato.Execute(QSql, DmSrvApl.BrvDicionario, '', '', '', Cad0001, lNmTabela);
end;

procedure TCad0001.EdtAtuDicSrvClick(Sender: TObject);
begin
      inherited;
      if MessageDlg('Confirma a atualização do dicionário de dados do servidor?',
                                            mtConfirmation, [mbYes, mbNo], 0) = idYes then
      begin
            DmSrvApl.BrvDicionario.AtualizarDicionarioServer;
      end;
end;

procedure TCad0001.BrvSpeedButton5Click(Sender: TObject);
begin
      LblNrSubFor.Caption := 'NADA';
      BrvConSub.BrParams.Clear;
      BrvConSub.BrParams.Add('<%NmTabela%>;' + PString(TrvDicion.Selected.Parent.Parent.Data)^);
      BrvConSub.BrExecute('');

      if (LblNrSubFor.Caption <> '') and (LblNrSubFor.Caption <> 'NADA') then
      begin
            try
                QpSubFor.Append;
                QpSubFor.FieldByName('NrForDin').AsString :=
                         QcForDin.FieldByName('NrForDin').AsString;
                QpSubFor.FieldByName('NrSubFor').AsString := LblNrSubFor.Caption;
                QpSubFor.Post;
                QpSubFor.ApplyUpdates(0);
            finally
                QpSubFor.Close;
                QpSubFor.Open;
            end;
      end;
end;

procedure TCad0001.BrnAtuCliClick(Sender: TObject);
begin
      Cai0011 := TCai0011.Create(Self);
      Cai0011.ModoDesenvolvedor;
      FreeAndNil(Cai0011);
end;

procedure TCad0001.BtnTraduzirClick(Sender: TObject);
var lDsSql : String;
begin
      inherited;
      if MemSql.SelLength > 0 then
      begin
            lDsSql := UpperCase(Trim(MemSql.SelText));
            lDsSql := BrvOracle.SintaxeOracle(lDsSql);
            MemSql.SelText := lDsSql;
      end else
      begin
            lDsSql := UpperCase(Trim(MemSql.Text));
            lDsSql := BrvOracle.SintaxeOracle(lDsSql);
            MemSql.Text := lDsSql;
      end;
end;

procedure TCad0001.CarregarAtributosDaTabela(pNmTabela : String);
var lNdNovo   : TTreeNode;
begin
     QpTabAtr.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
          'Select * From S009 Where NmTabela = ' + #39 +
                                                   pNmTabela +#39 + ' Order by NrSeqAtr');

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

procedure TCad0001.CarregarAtributosDaChavePrimaria(pNmTabela : String);
var lNdNovo   : TTreeNode;
begin
      QpChaPri.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                      'Select * From S011 Where NmTabela = ' + #39 +
                                                   pNmTabela +#39 + ' Order by NrSeqKey');

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

procedure TCad0001.CarregarChavesEstrangeiras(pNmTabela : String);
var lNdNovo   : TTreeNode;
begin
      QpChaEst.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                       'Select * From S012 Where NmTabela = ' + #39 +
                                                   pNmTabela +#39 + ' Order by NmChaEst');

      while not QpChaEst.Eof do
      begin
            New(gNdNode);
            lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected,
                                       QpChaEst.FieldByName('NmChaEst').AsString,
                                       gNdNode);
            PString(lNdNovo.Data)^ :=  QpChaEst.FieldByName('NmChaEst').AsString;

            if QpChaEst.FieldByName('TpChaEst').AsString = 'R' then
            begin
                  lNdNovo.ImageIndex    := gImgEstKey;
                  lNdNovo.SelectedIndex := gImgEstKey;
            end else
            begin
                  lNdNovo.ImageIndex    := gImgEstCon;
                  lNdNovo.SelectedIndex := gImgEstCon;
            end;

            QpChaEst.Next;
      end;

      QpChaEst.Close;
end;

procedure TCad0001.CarregarAtributosChaveEstrangeira(pNmChaEst : String);
var lNdNovo   : TTreeNode;
begin
      QpColEst.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                       'Select * From S013 Where NmChaEst = ' + #39 +
                                                   pNmChaEst +#39 + ' Order by NrSeqKey');

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

procedure TCad0001.CarregarTrigger(pNmTabela : String);
var lNdNovo   : TTreeNode;
begin
      QpTrigger.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                        'Select * From S020 Where NmTabela = ' + #39 +
                                                  pNmTabela + #39 + ' Order by NmTrigge');

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

procedure TCad0001.CdsTmpGruAfterPost(DataSet: TDataSet);
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

procedure TCad0001.CdsTmpGruAfterScroll(DataSet: TDataSet);
begin
      if DataSet.Tag = 0 then
      begin
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-=-=-=-= Abrindo a Query que efetivará as alterações no banco
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            QcPerUsu.Close;
            QcPerUsu.BrParams.Clear;
            QcPerUsu.BrParams.Add('<%NmTabela%>;' +
                                   QcTabAtr.FieldByName('NmTabela').AsString);
            QcPerUsu.BrParams.Add('<%NmAtribu%>;' +
                                   QcTabAtr.FieldByName('nmAtribu').AsString);
            QcPerUsu.BrParams.Add('<%CdGrupo%>;'  +
                                   DataSet.FieldByName('CdGrupo').AsString);
            QcPerUsu.Open;
            if QcPerUsu.Tag = 0 then
            begin
                  QcPerUsu.Tag := 1;
                  QcPerUsu.IndexDefs.Add('Key_Usu', 'CdUsuari',
                                                    [ixCaseInsensitive,ixDescending]);
                  QcPerUsu.IndexName := 'Key_Usu';
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-=-=-=-= Abrindo a Query para controle visual
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            CdsTmpUsu.Tag := 1;
            CdsTmpUsu.DisableControls;

            CdsTmpUsu.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(
                      30,
                      '<%NmTabela%>;' + QcTabAtr.FieldByName('NmTabela').AsString + #13 +
                      '<%NmAtribu%>;' + QcTabAtr.FieldByName('NmAtribu').AsString + #13 +
                      '<%CdGrupo%>;'  + DataSet.FieldByName('CdGrupo').AsString,
                      Name);

            while not CdsTmpUsu.Eof do
            begin
                  if CdsTmpUsu.FieldByName('TpPermis').AsString = '' then
                  begin
                        CdsTmpUsu.Edit;
                        CdsTmpUsu.FieldByName('TpPermis').AsString := '0';
                        CdsTmpUsu.Post;
                  end;

                  CdsTmpUsu.Next;
            end;

            CdsTmpUsu.First;
            CdsTmpUsu.EnableControls;
            CdsTmpUsu.Tag := 0;
      end;
end;

procedure TCad0001.CdsTmpGruBeforeInsert(DataSet: TDataSet);
begin
     Abort;
end;

procedure TCad0001.CdsTmpGruBeforePost(DataSet: TDataSet);
begin
      if DataSet.FieldByName('TpPermis').AsString = '5' then
      begin
            DataSet.FieldByName('TpPermis').AsString := '0';
      end;
end;

procedure TCad0001.CdsTmpUsuAfterPost(DataSet: TDataSet);
begin
      if DataSet.Tag = 0 then
      begin
            if DataSet.FieldByName('TpPermis').AsInteger > 0 then
            begin
                  if QcPerUsu.FindKey([DataSet.FieldByName('CdUsuari').AsInteger]) then
                  begin
                        QcPerUsu.Edit;
                  end else
                  begin
                        QcPerUsu.Insert;
                        QcPerUsu.FieldByName('NmTabela').AsString :=
                                 QcTabAtr.FieldByName('NmTabela').AsString;
                        QcPerUsu.FieldByName('NmAtribu').AsString :=
                                 QcTabAtr.FieldByName('NmAtribu').AsString;
                        QcPerUsu.FieldByName('CdGrupo').AsInteger :=
                                 DataSet.FieldByName('CdGrupo').AsInteger;
                        QcPerUsu.FieldByName('CdUsuari').AsInteger :=
                                 DataSet.FieldByName('CdUsuari').AsInteger;
                  end;
                  QcPerUsu.FieldByName('TpPermis').AsString :=
                                                 DataSet.FieldByName('TpPermis').AsString;
                  QcPerUsu.Post;
                  QcPerUsu.ApplyUpdates(0);
            end else
            begin
                  if QcPerUsu.FindKey([DataSet.FieldByName('CdUsuari').AsInteger]) then
                  begin
                        QcPerUsu.Delete;
                        QcPerUsu.ApplyUpdates(0);
                  end;
            end;
      end;
end;

procedure TCad0001.CarregarIndices(pNmTabela : String);
var lNdNovo   : TTreeNode;
begin
      QpIndice.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                          'Select * From S018 Where NmTabela = ' + #39 + pNmTabela + #39 +
                          ' Order by NmIndice');

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

procedure TCad0001.CarregarAtributosIndice(pNmTabela : String; pNmIndice : String);
var lNdNovo   : TTreeNode;
begin
      QpAtrInd.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
          'Select * From S019 Where NmTabela = ' + #39 + pNmTabela + #39 +
                       ' and NmIndice = ' + #39 + pNmIndice + #39 + ' Order by NrSeqInd');

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

procedure TCad0001.CarregarProcedimentos;
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

procedure TCad0001.CarregarViews;
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

procedure TCad0001.CarregarExcessoes;
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

procedure TCad0001.CarregarFormularios(pNmTabela : String);
var lNdNovo   : TTreeNode;
begin
      QpFormul.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                       'Select * From S014 Where NmTabela = ' + #39 +
                                                 pNmTabela + #39 + ' Order by NrForDin');

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

procedure TCad0001.MontarNivel(pNoImagem : Integer);
begin
      case pNoImagem of
           gImgGruTab: begin // mostrando tabelas
                             CarregarTabelas;
                       end;
           gImgTabela:
                       begin // Criando árvore de propriedades da tabela
                             MontarArvoreAtributosTabela;
                       end;
           gImgGruCol: begin // mostrando colunas da tabela
                             CarregarAtributosDaTabela(PString(TrvDicion.Selected.Parent.Data)^);
                       end;
           gImgGruKey: begin // mostrando colunas da chave primária
                             CarregarAtributosDaChavePrimaria
                                     (PString(TrvDicion.Selected.Parent.Data)^);
                       end;
           gImgGruEst: begin // mostrando chaves estrangeiras
                             CarregarChavesEstrangeiras(PString(TrvDicion.Selected.Parent.Data)^);
                       end;
           gImgEstKey,
           gImgEstCon: begin
                             CarregarAtributosChaveEstrangeira(PString(TrvDicion.Selected.Data)^);
                       end;
           gImgGruTri: begin // mostrando Triggers
                             CarregarTrigger(PString(TrvDicion.Selected.Parent.Data)^);
                       end;
           gImgGruInd: begin // mostrando índices
                             CarregarIndices(PString(TrvDicion.Selected.Parent.Data)^);
                       end;
           gImgIndice: begin
                             CarregarAtributosIndice(PString(TrvDicion.Selected.Parent.Parent.Data)^,
                                                     PString(TrvDicion.Selected.Data)^);
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
           gImgGruFor: begin // mostrando os formulários
                             CarregarFormularios(PString(TrvDicion.Selected.Parent.Data)^);
                       end;
           gImgFormul: begin
                             Cad0002 := TCad0002.Create(Self);

                             try
                                 Cad0002.CarregarFormulario(
                                                        QcForDin.FieldByName('NmTabela').AsString,
                                                        QcForDin.FieldByName('NrForDin').AsInteger);
                                 Cad0002.ShowModal;
                             finally
                                 FreeAndNil(Cad0002);
                             end;
                       end;
      end;

      if (pNoImagem <> gImgGruTab) then
      begin
            TrvDicion.Selected.Expand(True);
      end;
end;

procedure TCad0001.PgcGeralChange(Sender: TObject);
begin
      SbtCancelClick(nil);

      if PgcGeral.ActivePage = TbsGerSql then
      begin
            PgcBanco.ActivePage := TbsSql;
      end else
      begin
            PgcBanco.ActivePage := TbsNada;
      end;
end;

procedure TCad0001.PgcTabAtrChange(Sender: TObject);
begin
      if (PgcTabAtr.ActivePage = TbsPerAtr) and (PgcTabAtr.Tag = 0) then
      begin
            PgcTabAtr.Tag := 1;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-=-=-=-= Abrindo a Query que efetivará as alterações no banco
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            QcPerGru.Close;
            QcPerGru.BrParams.Clear;
            QcPerGru.BrParams.Add('<%NmTabela%>;' +
                                               QcTabAtr.FieldByName('NmTabela').AsString);
            QcPerGru.BrParams.Add('<%NmAtribu%>;' +
                                               QcTabAtr.FieldByName('NmAtribu').AsString);
            QcPerGru.Open;
            if QcPerGru.Tag = 0 then
            begin
                  QcPerGru.Tag := 1;
                  QcPerGru.IndexDefs.Add('Key_Grupo', 'CDGRUPO',
                                                        [ixCaseInsensitive,ixDescending]);
                  QcPerGru.IndexName := 'Key_Grupo';
            end;
        
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-=-=-= Pegando permissões do grupo no atributo
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

            CdsTmpGru.Tag := 1;
            CdsTmpGru.DisableControls;
            CdsTmpGru.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(
                      27,
                      '<%NmTabela%>;' + QcTabAtr.FieldByName('NmTabela').AsString + #13 +
                      '<%NmAtribu%>;' + QcTabAtr.FieldByName('NmAtribu').AsString,
                      Name);

            while not CdsTmpGru.Eof do
            begin
                  if CdsTmpGru.FieldByName('TpPermis').AsString = '' then
                  begin
                        CdsTmpGru.Edit;
                        CdsTmpGru.FieldByName('TpPermis').AsString := '0';
                        CdsTmpGru.Post;
                  end;

                  CdsTmpGru.Next;
            end;

            CdsTmpGru.EnableControls;
            CdsTmpGru.Tag := 0;
            CdsTmpGru.First;
      end;
end;

procedure TCAD0001.ExcluirTabela(pNmTabela : String);
var lConexao  : TSDmSisClient;
    lDsResult : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.ExcluirTabela(DmSrvApl.BrvDicionario.Credencial, lDsResult, pNmTabela);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCAD0001.ExcluirAtributo(pNmTabela : String; pNmAtribu : String);
var lConexao  : TSDmSisClient;
    lDsResult : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.ExcluirAtributo(DmSrvApl.BrvDicionario.Credencial,
                                                         lDsResult, pNmTabela, pNmAtribu);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCAD0001.ExcluirAtributoPrimario(pNmTabela : String; pNmAtribu : String);
var lConexao  : TSDmSisClient;
    lDsResult : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.ExcluirAtributoPrimario(DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                           pNmTabela, pNmAtribu);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCAD0001.ExcluirChaveEstrangeira(pNmChaEst : String);
var lConexao  : TSDmSisClient;
    lDsResult : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.ExcluirChaveEstrangeira(DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                           pNmChaEst);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCAD0001.ExcluirIndiceSecundario(pNmIndice : String);
var lConexao  : TSDmSisClient;
    lDsResult : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.ExcluirIndiceSecundario(DmSrvApl.BrvDicionario.Credencial,
                                                                    lDsResult, pNmIndice);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCAD0001.ExcluirAtributoIndiceSecundario(pNmIndice : String; pNmAtribu : String);
var lConexao  : TSDmSisClient;
    lDsResult : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.ExcluirAtributoIndiceSecundario(DmSrvApl.BrvDicionario.Credencial,
                                                   lDsResult, pNmIndice, pNmAtribu);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCAD0001.ExcluirFormularioDinamico(pNrForDin : String);
var lConexao  : TSDmSisClient;
    lDsResult : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.ExcluirFormularioDinamico(DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                             pNrForDin);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCAD0001.ExcluirAtributoChaveEstrangeira(pNmChaEst : String; pNmAtribu : String);
var lConexao  : TSDmSisClient;
    lDsResult : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.ExcluirAtributoChaveEstrangeira(DmSrvApl.BrvDicionario.Credencial,
                                                   lDsResult, pNmChaEst, pNmAtribu);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCad0001.PopExcluiClick(Sender: TObject);
begin
      if MessageDlg('Confirma a exclusão do ítem selecionado?', MtConfirmation,
                                                  [MbYes, MbNo], 0) = IdYes then
      begin
            case TrvDicion.Selected.ImageIndex of
                 gImgTabela: begin
                                   ExcluirTabela(PString(TrvDicion.Selected.Data)^);
                             end;
                 gImgColuna: begin // =-=-=-=-=-=-=-=-=-=- Coluna da tabela
                                   ExcluirAtributo(PString(TrvDicion.Selected.Parent.Parent.Data)^,
                                                   PString(TrvDicion.Selected.Data)^);
                             end;
                 gImgColKey: begin // =-=-=-=-=-=-=-=-=-=- Chave primaria
                                   ExcluirAtributoPrimario(
                                                   PString(TrvDicion.Selected.Parent.Parent.Data)^,
                                                   PString(TrvDicion.Selected.Data)^);
                             end;
                 gImgEstKey,
                 gImgEstCon: begin
                                   // =-=-=-=-=-=-=-=-=-=- Chave estrangeira
                                   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                                   // =-= Excluindo Colunas da Chave Est.-=
                                   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                                   ExcluirChaveEstrangeira(PString(TrvDicion.Selected.Data)^);
                             end;
                 gImgColEst: begin // =-=-=-=-=- coluna da chave estrangeira
                                   ExcluirAtributoChaveEstrangeira(
                                          PString(TrvDicion.Selected.Parent.Data)^,
                                          PString(TrvDicion.Selected.Data)^);
                             end;
                 gImgTrigge: begin // =-=-=-=-=- Trigger
                                   QcTrigger.Delete;
                                   QcTrigger.ApplyUpdates(0);
                             end;
                 gImgIndice: begin // =-=-=-=-=- Índice
                                   ExcluirIndiceSecundario(PString(TrvDicion.Selected.Data)^);
                             end;
                 gImgColInd: begin // =-=-=-=-=- atributo do índice
                                   ExcluirAtributoIndiceSecundario(
                                                          PString(TrvDicion.Selected.Parent.Data)^,
                                                          PString(TrvDicion.Selected.Data)^);
                             end;
                 gImgProced: begin // =-=-=-=-=- Procedimento
                                   QcProced.Delete;
                                   QcProced.ApplyUpdates(0);
                             end;
                 gImgView  : begin // =-=-=-=-=- Procedimento
                                   QcView.Delete;
                                   QcView.ApplyUpdates(0);
                             end;
                 gImgExcess: begin // =-=-=-=-=- Excessões
                                   QcExcess.Delete;
                                   QcExcess.ApplyUpdates(0);
                             end;
                 gImgFormul: begin // =-=-=-=-=- Formulário dinâmico
                                   ExcluirFormularioDinamico(PString(TrvDicion.Selected.Data)^);
                             end;
            end;

            TrvDicion.Selected.Delete;
            SbtCancelClick(nil);
      end;
end;

procedure TCad0001.PopIncluiClick(Sender: TObject);
var lNdAnteri : TTreeNode;
    lNdNovo   : TTreeNode;
    lDsNovo   : String;
    lNmTabEst : String;
    lDsComWhe : String;
begin
      lNdAnteri := TrvDicion.Selected;
      TrvDicionDblClick(nil);
      TrvDicion.Selected := lNdAnteri;
      lDsNovo := '';

      case TrvDicion.Selected.ImageIndex of
           gImgModTab : begin
                              QpModulo.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                                            'Select * From S050 Where CdModulo = ' +
                                                            PString(TrvDicion.Selected.Data)^);

                              QcTabela.Close;
                              lDsComWhe := ' is null ';
                              QcTabela.BrParams.Clear;
                              QcTabela.BrParams.Add('<%NmTabela%>;' + lDsComWhe);
                              QcTabela.Open;
                              QcTabela.Append;
                              QcTabela.FieldByName('NmTabela').AsString :=
                                                Copy(QpModulo.FieldByName('DsMasMod').AsString,1,1);

                              lDsNovo := 'Nova Tabela';
                              PgcBanco.ActivePage := TbsTabela;
                              DbEdtNmTabela.Enabled := True;
                              DbEdtNmTabela.SetFocus;
                        end;
           gImgGruTab : begin
                              QcTabela.Close;
                              lDsComWhe := ' is null ';
                              QcTabela.BrParams.Clear;
                              QcTabela.BrParams.Add('<%NmTabela%>;' + lDsComWhe);
                              QcTabela.Open;
                              QcTabela.Append;
                              lDsNovo := 'Nova Tabela';
                              PgcBanco.ActivePage := TbsTabela;
                              DbEdtNmTabela.Enabled := True;
                              DbEdtNmTabela.SetFocus;
                        end;
           gImgGruCol : begin
                              lDsComWhe := 'S009.NmTabela is null';
                              QcTabAtr.Close;
                              QcTabAtr.BrParams.Clear;
                              QcTabAtr.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                              QcTabATr.Open;
                              QcTabATr.Append;
                              QcTabAtr.FieldByName('NmTabela').AsString :=
                                                           PString(TrvDicion.Selected.Parent.Data)^;
                              QcTabAtr.FieldByName('VrInterv').AsInteger := 1;
                              QcTabAtr.FieldByName('NrSeqAtr').AsInteger := TrvDicion.Selected.Count;

                              lDsNovo := 'Novo Atributo';
                              PgcBanco.ActivePage  := TbsTabAtr;
                              PgcAtribu.ActivePage := TbsAtribu;
                              PgcTabAtr.Visible    := False;
                              DBEdit2.ReadOnly     := False;

                              EdtNrPosAtr.Tag      := TrvDicion.Selected.Count+1;
                              EdtNrPosAtr.Value    := TrvDicion.Selected.Count+1;
                              EdtNrPosAtr.Enabled  := False;

                              DBEdit2.SetFocus;
                        end;
           gImgGruKey : begin
                              lDsComWhe := 'S011.NmTabela is null';
                              QcChaPri.Close;
                              QcChaPri.BrParams.Clear;
                              QcChaPri.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                              QcChaPri.Open;
                              QcChaPri.Append;
                              QcChaPri.FieldByName('NmTabela').AsString :=
                                                           PString(TrvDicion.Selected.Parent.Data)^;

                              lDsNovo := 'Nova Chave';
                              PgcBanco.ActivePage := TbsChaPri;
                              DBEdit11.ReadOnly   := False;
                              DBEdit11.SetFocus;
                        end;
           gImgGruEst : begin
                              lDsComWhe := 'S012.NmTabela is null';
                              QcChaEst.Close;
                              QcChaEst.BrParams.Clear;
                              QcChaEst.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                              QcChaEst.Open;
                              QcChaEst.Append;

                              QcChaEst.FieldByName('NmTabela').AsString :=
                                       PString(TrvDicion.Selected.Parent.Data)^;
                              QcChaEst.FieldByName('NmChaEst').AsString :=
                                       'FK_' + QcChaEst.FieldByName('NmTabela').AsString +
                                       '_';
                              QcChaEst.FieldByName('TpChaEst').AsString := 'R';

                              lDsNovo := 'Nova Chave Estrangeira';

                              DBEdit13.Enabled    := True;
                              DBEdit14.Enabled    := True;
                              PgcBanco.ActivePage := TbsChaEst;
                              DBEdit13.SetFocus;
                        end;
           gImgEstKey,
           gImgEstCon : begin
                              lDsComWhe := 'S013.NmChaEst = ' + #39 +
                                                            PString(TrvDicion.Selected.Data)^ + #39;
                              QcColEst.Close;
                              QcColEst.BrParams.Clear;
                              QcColEst.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                              QcColEst.Open;
                              QcColEst.Append;
                              QcColEst.FieldByName('NmTabela').AsString :=
                                                   PString(TrvDicion.Selected.Parent.Parent.Data)^;
                              QcColEst.FieldByName('NmChaEst').AsString :=
                                                   PString(TrvDicion.Selected.Data)^;

                              lDsNovo := 'Nova Coluna Estrangeira';

                              DBEdit18.Enabled    := True;
                              PgcBanco.ActivePage := TbsColEst;

                              DBEdit18.SetFocus;
                        end;
           gImgGruTri : begin
                              lDsComWhe := 'S020.NmTabela is null';
                              QcTrigger.Close;
                              QcTrigger.BrParams.Clear;
                              QcTrigger.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                              QcTrigger.Open;
                              QcTrigger.Append;
                              QcTrigger.FieldByName('NmTabela').AsString :=
                                                    PString(TrvDicion.Selected.Parent.Data)^;

                              lDsNovo := 'Nova Trigger';
                              PgcBanco.ActivePage := TbsTrigge;

                              DBEdit17.Enabled := True;
                              DBEdit17.SetFocus;
                        end;
           gImgGruInd : begin
                              lDsComWhe := 'S018.NmTabela is null';
                              QcIndice.Close;
                              QcIndice.BrParams.Clear;
                              QcIndice.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                              QcIndice.Open;
                              QcIndice.Append;
                              QcIndice.FieldByName('NmTabela').AsString :=
                                                   PString(TrvDicion.Selected.Parent.Data)^;
                              QcIndice.FieldByName('SnUnico').AsString  := 'N';

                              lDsNovo := 'Novo Índice';
                              PgcBanco.ActivePage := TbsIndice;

                              DBEdit21.Enabled := True;
                              DBEdit21.SetFocus;
                        end;
           gImgIndice : begin
                              lDsComWhe := 'S019.NmTabela is null';
                              QcColInd.Close;
                              QcColInd.BrParams.Clear;
                              QcColInd.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                              QcColInd.Open;
                              QcColInd.Append;
                              QcColInd.FieldByName('NmIndice').AsString :=
                                                                  PString(TrvDicion.Selected.Data)^;
                              QcColInd.FieldByName('NmTabela').AsString :=
                                                   PString(TrvDicion.Selected.Parent.Parent.Data)^;

                              lDsNovo := 'Novo atributo do índice';
                              PgcBanco.ActivePage := TbsColInd;
                              DBEdit24.Enabled    := True;
                              DBEdit24.SetFocus;
                        end;
           gImgGruPro : begin
                              lDsComWhe := 'S021.NmProced is null';
                              QcProced.Close;
                              QcProced.BrParams.Clear;
                              QcProced.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                              QcProced.Open;
                              QcProced.Append;

                              lDsNovo := 'Novo Procedimento';
                              PgcBanco.ActivePage := TbsProced;

                              DBEdit26.Enabled := True;
                              DBEdit26.SetFocus;
                        end;
           gImgGruVie : begin
                              lDsComWhe := 'S040.NmView is null';
                              QcView.Close;
                              QcView.BrParams.Clear;
                              QcView.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                              QcView.Open;
                              QcView.Append;

                              lDsNovo := 'Nova View';
                              PgcBanco.ActivePage := TbsView;

                              DBEdit29.Enabled := True;
                              DBEdit29.SetFocus;
                        end;
           gImgGruExc : begin
                              lDsComWhe := 'S025.NmExcess is null';
                              QcExcess.Close;
                              QcExcess.BrParams.Clear;
                              QcExcess.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                              QcExcess.Open;
                              QcExcess.Append;

                              lDsNovo := 'Nova Excessão';
                              PgcBanco.ActivePage := TbsExcess;

                              DBEdit27.Enabled := True;
                              DBEdit27.SetFocus;
                        end;
           gImgGruFor : begin
                              lDsComWhe := 'S014.NmTabela is null';
                              QcForDin.Close;
                              QcForDin.BrParams.Clear;
                              QcForDin.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
                              QcForDin.Open;
                              QcForDin.Append;
                              QcForDin.FieldByName('NmTabela').AsString :=
                                                           PString(TrvDicion.Selected.Parent.Data)^;
                              QcForDin.FieldByName('SnDelete').AsString := 'S';
                              QcForDin.FieldByName('SnInclui').AsString := 'S';

                              lDsNovo := 'Novo Formulário';
                              PgcBanco.ActivePage := TbsForDin;
                              GbxSubFor.Visible   := False;
                              DBEdit28.SetFocus;
                        end;
      end;

      if lDsNovo <> '' then
      begin
            lNdNovo := TrvDicion.Items.AddChildObject(TrvDicion.Selected, lDsNovo,
                                                                                 gNdNode);
            PString(lNdNovo.Data)^ := lDsNovo;
            lNdNovo.ImageIndex     := gImgEditar;
            lNdNovo.SelectedIndex  := gImgEditar;

            case TrvDicion.Selected.ImageIndex of
                 gImgGruKey : QcChaPri.FieldByName('NrSeqKey').AsInteger := lNdNovo.Index;
                 gImgEstKey,
                 gImgEstCon : QcColEst.FieldByName('NrSeqKey').AsInteger := lNdNovo.Index;
                 gImgIndice : QcColInd.FieldByName('NrSeqInd').AsInteger := lNdNovo.Index;
            end;

            TrvDicion.Tag          := 1;
            TrvDicion.Selected     := lNdNovo;
            TrvDicion.Tag          := 0;
      end;
end;

procedure TCad0001.PopScriptClick(Sender: TObject);
var lDsLinha  : String;
    lDsComWhe : String;
begin
      SbtCancelClick(nil);

      MemSql.Lines.Add('=-=-=-=-=-=-=-=-=-=');
      MemSql.Lines.Add('=-=-= Script para a tabela ' +
                                                 PString(TrvDicion.Selected.Data)^ + ' (inicio) ');
      MemSql.Lines.Add('=-=-=-=-=-=-=-=-=-=');
      // =-=-=-=-=-=-=-=-=-=-=-= Carregando as colunas
      lDsComWhe := 'S009.NmTabela = ' + #39 + PString(TrvDicion.Selected.Data)^ + #39;
      QcTabAtr.Close;
      QcTabAtr.BrParams.Clear;
      QcTabAtr.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
      QcTabATr.Open;

      MemSql.Lines.Add('CREATE TABLE ' + PString(TrvDicion.Selected.Data)^ + '(');

      while not QcTabATr.Eof do
      begin
            lDsLinha := '   ' + QcTabATr.FieldByName('NmAtribu').AsString +
               DmSis.TipoColuna(QcTabATr.FieldByName('TpAtribu').AsString,
                                QcTabATr.FieldByName('TmAtribu').AsString);

            QcTabATr.Next;

            if not QcTabATr.Eof then
            begin
                  lDsLinha := lDsLinha + ',';
            end;

            MemSql.Lines.Add(lDsLinha);
      end;

      QcTabATr.Close;

      MemSql.Lines.Add(')');

      // =-=-=-=-=-=-=-=-=-=-=-= Carregando chave primaria
      MemSql.Lines.Add(' ');
      MemSql.Lines.Add('ALTER TABLE ' + PString(TrvDicion.Selected.Data)^);
      MemSql.Lines.Add('  ADD CONSTRAINT PK_' + PString(TrvDicion.Selected.Data)^);
      lDsLinha := '  PRIMARY KEY (';

      lDsComWhe := 'S011.NmTabela = ' + #39 + PString(TrvDicion.Selected.Data)^ + #39;
      QcChaPri.Close;
      QcChaPri.BrParams.Clear;
      QcChaPri.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
      QcChaPri.Open;

      while not QcChaPri.Eof do
      begin
            lDsLinha := lDsLinha + QcChaPri.FieldByName('NmAtribu').AsString;
            QcChaPri.Next;

            if not QcChaPri.Eof then
            begin
                  lDsLinha := lDsLinha + ', ';
            end;
      end;

      QcChaPri.Close;

      MemSql.Lines.Add(lDsLinha + ')');

      // =-=-=-=-=-=-=-=-=-=-=-= Carregando chave estrangeira
      lDsComWhe := 'S012.NmTabela = ' + #39 + PString(TrvDicion.Selected.Data)^ + #39 +
              ' and S012.TpChaEst = ' + #39 + 'R'                     + #39;
      QcChaEst.Close;
      QcChaEst.BrParams.Clear;
      QcChaEst.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
      QcChaEst.Open;

      while not QcChaEst.Eof do
      begin
            MemSql.Lines.Add(' ');
            MemSql.Lines.Add('ALTER TABLE ' + PString(TrvDicion.Selected.Data)^);
            MemSql.Lines.Add('  ADD CONSTRAINT ' +
                                               QcChaEst.FieldByName('NmChaEst').AsString);
            lDsLinha := '  FOREIGN KEY (';

            lDsComWhe := 'S013.NmChaEst = ' + #39 +
                                          QcChaEst.FieldByName('NmChaEst').AsString + #39;
            QcColEst.Close;
            QcColEst.BrParams.Clear;
            QcColEst.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
            QcColEst.Open;

            while not QcColEst.Eof do
            begin
                  lDsLinha := lDsLinha + QcColEst.FieldByName('NmAtribu').AsString;

                  QcColEst.Next;

                  if not QcColEst.Eof then
                  begin
                        lDsLinha := lDsLinha + ', ';
                  end;
            end;

            QcColEst.Close;

            MemSql.Lines.Add(lDsLinha + ')');
            MemSql.Lines.Add('  REFERENCES ' + QcChaEst.FieldByName('NmTabEst').AsString);
            QcChaEst.Next;
      end;

      QcChaEst.Close;

      // =-=-=-=-=-=-=-=-=-=-=-= Carregando índices
      lDsComWhe := 'S018.NmTabela = ' + #39 + PString(TrvDicion.Selected.Data)^ + #39;
      QcIndice.Close;
      QcIndice.BrParams.Clear;
      QcIndice.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
      QcIndice.Open;

      while not QcIndice.Eof do
      begin
            MemSql.Lines.Add(' ');

            if QcIndice.FieldByName('SnUnico').AsString = 'S' then
            begin
                  MemSql.Lines.Add('CREATE  UNIQUE INDEX ');
            end else
            begin
                  MemSql.Lines.Add('CREATE  INDEX ');
            end;

            MemSql.Lines.Add(QcIndice.FieldByName('NmIndice').AsString +
                             ' ON ' + QcIndice.FieldByName('NmTabela').AsString);
            lDsLinha := '  (';

            lDsComWhe := 'S019.NmIndice = ' + #39 +
                                          QcIndice.FieldByName('NmIndice').AsString + #39;
            QcColInd.Close;
            QcColInd.BrParams.Clear;
            QcColInd.BrParams.Add('<%DsComWhe%>;' + lDsComWhe);
            QcColInd.Open;

            while not QcColInd.Eof do
            begin
                  lDsLinha := lDsLinha + QcColInd.FieldByName('NmAtribu').AsString;

                  QcColInd.Next;

                  if not QcColInd.Eof then
                  begin
                        lDsLinha := lDsLinha + ', ';
                  end;
            end;

            QcColInd.Close;

            MemSql.Lines.Add(lDsLinha + ')');
            QcIndice.Next;
      end;

      QcIndice.Close;


      MemSql.Lines.Add('=-=-= Script para a tabela ' +
                                                     PString(TrvDicion.Selected.Data)^ + ' (FIM) ');
      PnlDados.Visible    := False;
      SbtCommit.Enabled   := False;
      SbtRollba.Enabled   := False;
      PgcBanco.ActivePage := TbsSQL;
end;

procedure TCad0001.PopDicPopup(Sender: TObject);
begin
  inherited;
      PopInclui.Enabled := True;
      PopExclui.Enabled := True;
      PopScript.Enabled := False;

      if TrvDicion.Selected.ImageIndex in  [gImgBanco,  gImgTabela, gImgColuna,
                                            gImgColEst, gImgColKey, gImgEditar,
                                            gImgTrigge] then
      begin
            PopInclui.Enabled := False;
      end;

      if TrvDicion.Selected.ImageIndex in [gImgBanco,  gImgGruTab, gImgGruPro,
                                           gImgGruCol, gImgGruKey, gImgGruEst,
                                           gImgEditar, gImgGruTri, gImgGruExc,
                                           gImgGruVie] then
      begin
            PopExclui.Enabled := False;
      end;

      if TrvDicion.Selected.ImageIndex in [gImgTabela] then
      begin
            PopScript.Enabled := True;
      end;
end;

procedure TCad0001.QcChaEstAfterPost(DataSet: TDataSet);
begin
  inherited;
      PString(TrvDicion.Selected.Data)^ := QcChaEst.FieldByName('NmChaEst').AsString;
      TrvDicion.Selected.Text           := QcChaEst.FieldByName('NmChaEst').AsString;

      if QcChaEst.FieldByName('TpChaEst').AsString = 'R' then
      begin
            TrvDicion.Selected.ImageIndex      := gImgEstKey;
            TrvDicion.Selected.SelectedIndex   := gImgEstKey;
      end else
      begin
            TrvDicion.Selected.ImageIndex      := gImgEstCon;
            TrvDicion.Selected.SelectedIndex   := gImgEstCon;
      end;

      DBEdit13.Enabled                   := False;
      DBEdit14.Enabled                   := False;
end;

procedure TCad0001.QcChaEstBeforePost(DataSet: TDataSet);
begin
      inherited;

      if DataSet.FieldByName('NmChaEst').AsString = ''  then
      begin
            raise Exception.Create('Informe o nome da chave estrangeira');
      end;

      if DataSet.FieldByName('NmTabEst').AsString = ''  then
      begin
            raise Exception.Create('Informe o nome da tabela estrangeira');
      end;

      if DataSet.FieldByName('TpChaEst').AsString = ''  then
      begin
            raise Exception.Create('Informe o tipo da chave estrangeira');
      end;
end;

procedure TCad0001.QcChaPriAfterPost(DataSet: TDataSet);
begin
      PString(TrvDicion.Selected.Data)^ := QcChaPri.FieldByName('NmAtribu').AsString;
      TrvDicion.Selected.Text           := QcChaPri.FieldByName('NmAtribu').AsString;
      TrvDicion.Selected.ImageIndex     := gImgColKey;
      TrvDicion.Selected.SelectedIndex  := gImgColKey;
      DBEdit11.ReadOnly                 := True;
end;

procedure TCad0001.QcChaPriBeforePost(DataSet: TDataSet);
begin
      if DataSet.FieldByName('NmAtribu').AsString = ''  then
      begin
            raise Exception.Create('Informe o nome do atributo');
      end;
end;

procedure TCad0001.QcColEstAfterPost(DataSet: TDataSet);
begin
      PString(TrvDicion.Selected.Data)^ := QcColEst.FieldByName('NmAtribu').AsString;
      TrvDicion.Selected.Text           := QcColEst.FieldByName('NmAtribu').AsString;

      TrvDicion.Selected.ImageIndex      := gImgColEst;
      TrvDicion.Selected.SelectedIndex   := gImgColEst;
      DBEdit18.Enabled                   := False;
end;

procedure TCad0001.QcColEstBeforePost(DataSet: TDataSet);
begin
  inherited;
      if DataSet.FieldByName('NmAtribu').AsString = ''  then
      begin
            raise Exception.Create('Informe o nome do atributo');
      end;
end;

procedure TCad0001.QcColIndAfterPost(DataSet: TDataSet);
begin
      PString(TrvDicion.Selected.Data)^ := QcColInd.FieldByName('NmAtribu').AsString;
      TrvDicion.Selected.Text           := QcColInd.FieldByName('NmAtribu').AsString;
      TrvDicion.Selected.ImageIndex     := gImgColInd;
      TrvDicion.Selected.SelectedIndex  := gImgColInd;
      DBEdit24.Enabled                  := False;
end;

procedure TCad0001.QcColIndBeforePost(DataSet: TDataSet);
begin
      if QcColInd.FieldByName('NmAtribu').AsString = '' then
      begin
            raise Exception.Create('Informe o nome do atributo');
      end;
end;

procedure TCad0001.QcDomAtrAfterInsert(DataSet: TDataSet);
begin
      DataSet.FieldByName('NmTabela').AsString :=
                                      QcTabATr.FieldByName('NmTabela').AsString;
      DataSet.FieldByName('NmAtribu').AsString :=
                                      QcTabATr.FieldByName('NmAtribu').AsString;
end;

procedure TCad0001.QcDomAtrAfterPost(DataSet: TDataSet);
begin
      QcDomAtr.ApplyUpdates(0);
end;

procedure TCad0001.QcDomAtrBeforePost(DataSet: TDataSet);
begin
      if DataSet.FieldByName('DsDomini').AsString = ''  then
      begin
            raise Exception.Create('Informe a descrição do domínio');
      end;

      if DataSet.FieldByName('VrDomini').AsString = ''  then
      begin
            raise Exception.Create('Informe o valor do domínio');
      end;
end;

procedure TCad0001.QcExcessAfterPost(DataSet: TDataSet);
begin
      inherited;

      PString(TrvDicion.Selected.Data)^ := QcExcess.FieldByName('NmExcess').AsString;
      TrvDicion.Selected.Text           := QcExcess.FieldByName('NmExcess').AsString;
      TrvDicion.Selected.ImageIndex     := gImgExcess;
      TrvDicion.Selected.SelectedIndex  := gImgExcess;
      DBEdit27.Enabled                  := False;
end;

procedure TCad0001.QcExcessBeforePost(DataSet: TDataSet);
begin
      if QcExcess.FieldByName('NmExcess').AsString = '' then
      begin
            raise Exception.Create('Informe o nome da excessão');
      end;

      if QcExcess.FieldByName('TxExcess').AsString = '' then
      begin
            raise Exception.Create('Informe o texto da excessão');
      end;
end;

procedure TCad0001.QcForDinAfterPost(DataSet: TDataSet);
begin
      PString(TrvDicion.Selected.Data)^ := QcForDin.FieldByName('NrForDin').AsString;

      TrvDicion.Selected.Text           := QcForDin.FieldByName('DsForDin').AsString +
                                   ' (' + QcForDin.FieldByName('NrForDin').AsString + ')';

      TrvDicion.Selected.ImageIndex     := gImgFormul;
      TrvDicion.Selected.SelectedIndex  := gImgFormul;
end;

procedure TCad0001.QcForDinBeforePost(DataSet: TDataSet);
begin
      if QcForDin.FieldByName('DsForDin').AsString = '' then
      begin
            raise Exception.Create('Informe a descrição do formulário');
      end;

      if QcForDin.State = dsInsert then
      begin
            QcForDin.FieldByName('NrForDin').AsInteger :=
                          DmSrvApl.BrvDicionario.ProxNumeroSequencial('S014', 'NRFORDIN');
      end;
end;

procedure TCad0001.QcIndiceAfterPost(DataSet: TDataSet);
begin
      PString(TrvDicion.Selected.Data)^ := QcIndice.FieldByName('NmIndice').AsString;
      TrvDicion.Selected.Text           := QcIndice.FieldByName('NmIndice').AsString;
      TrvDicion.Selected.ImageIndex     := gImgIndice;
      TrvDicion.Selected.SelectedIndex  := gImgIndice;
      DBEdit21.Enabled                  := False;
end;

procedure TCad0001.QcIndiceBeforePost(DataSet: TDataSet);
begin
      if QcIndice.FieldByName('NmIndice').AsString = '' then
      begin
            raise Exception.Create('Informe o nome do índice');
      end;
end;

procedure TCad0001.QcProcedAfterPost(DataSet: TDataSet);
begin
      PString(TrvDicion.Selected.Data)^ := QcProced.FieldByName('NmProced').AsString;
      TrvDicion.Selected.Text           := QcProced.FieldByName('NmProced').AsString;
      TrvDicion.Selected.ImageIndex     := gImgProced;
      TrvDicion.Selected.SelectedIndex  := gImgProced;
      DBEdit26.Enabled                  := False;
end;

procedure TCad0001.QcProcedBeforePost(DataSet: TDataSet);
begin
      if QcProced.FieldByName('NmProced').AsString = '' then
      begin
            raise Exception.Create('Informe o nome do procedimento');
      end;

      if QcProced.FieldByName('TxProced').AsString = '' then
      begin
            raise Exception.Create('Informe o texto do procedimento');
      end;
end;

procedure TCad0001.QcTabAtrAfterCancel(DataSet: TDataSet);
begin
      inherited;
      DBEdit2.ReadOnly := True;
end;

procedure TCad0001.QcTabAtrAfterPost(DataSet: TDataSet);
begin
      PString(TrvDicion.Selected.Data)^ := QcTabAtr.FieldByName('NmAtribu').AsString;
      TrvDicion.Selected.Text           := QcTabAtr.FieldByName('NmAtribu').AsString;
      TrvDicion.Selected.ImageIndex     := gImgColuna;
      TrvDicion.Selected.SelectedIndex  := gImgColuna;
      DBEdit2.ReadOnly                  := True;
end;

procedure TCad0001.QcTabAtrBeforePost(DataSet: TDataSet);
begin
      inherited;

      if DataSet.FieldByName('NmAtribu').AsString = '' then
      begin
            raise Exception.Create('Informe o nome do atributo');
      end;

      if DataSet.FieldByName('DsAtribu').AsString = '' then
      begin
            raise Exception.Create('Informe a descrição do atributo');
      end;

      if DataSet.FieldByName('TpAtribu').AsString = '' then
      begin
            raise Exception.Create('Informe o tipo do atributo');
      end;

      if (LblTmAtribu.Font.Color = clBlue) and
         (DataSet.FieldByName('TmAtribu').AsInteger = 0) then
      begin
            raise Exception.Create('Informe o tamanho do atributo');
      end;

      if DataSet.FieldByName('TpMascar').AsString = '' then
      begin
            raise Exception.Create('Informe o tipo da máscara do atributo');
      end;

      if (LblTpMascar.Font.color = clBlue) and
         (DataSet.FieldByName('DsMascar').AsString  = '') then
      begin
            raise Exception.Create('Defina a máscara para este atributo');
      end;

      if DataSet.FieldByName('VrInterv').AsInteger = 0 then
      begin
            raise Exception.Create('Intervalo do contator não pode ser zero');
      end;
end;

procedure TCad0001.QcTabelaAfterPost(DataSet: TDataSet);
begin
      inherited;
      PString(TrvDicion.Selected.Data)^ := QcTabela.FieldByName('NmTabela').AsString;
      TrvDicion.Selected.Text           := QcTabela.FieldByName('NmTabela').AsString + ' - ' +
                                           QcTabela.FieldByName('DsTabela').AsString;
      TrvDicion.Selected.ImageIndex     := gImgTabela;
      TrvDicion.Selected.SelectedIndex  := gImgTabela;
      DbEdtNmTabela.Enabled             := False;
end;

procedure TCad0001.QcTabelaBeforePost(DataSet: TDataSet);
begin
      if QcTabela.FieldByName('NmTabela').AsString = ''  then
      begin
            raise Exception.Create('Nome da tabela deve ser informado');
      end;

      if QcTabela.FieldByName('DsTabela').AsString = ''  then
      begin
            raise Exception.Create('Descrição da tabela deve ser informado');
      end;
end;

procedure TCad0001.QcTriggerAfterPost(DataSet: TDataSet);
begin
      PString(TrvDicion.Selected.Data)^ := QcTrigger.FieldByName('NmTrigge').AsString;
      TrvDicion.Selected.Text           := QcTrigger.FieldByName('NmTrigge').AsString;

      TrvDicion.Selected.ImageIndex     := gImgTrigge;
      TrvDicion.Selected.SelectedIndex  := gImgTrigge;

      DBEdit17.Enabled                  := False;
end;

procedure TCad0001.QcTriggerBeforePost(DataSet: TDataSet);
begin
      if QcTrigger.FieldByName('NmTrigge').AsString = '' then
      begin
            raise Exception.Create('Informe o nome da Trigger');
      end;

      if QcTrigger.FieldByName('TxTrigge').AsString = '' then
      begin
            raise Exception.Create('Informe o Texto da Trigger');
      end;
end;

procedure TCad0001.QcValAtrAfterPost(DataSet: TDataSet);
begin
      QcValAtr.ApplyUpdates(0);
end;

procedure TCad0001.QcValAtrBeforePost(DataSet: TDataSet);
begin
      if DataSet.FieldByName('NrOrdem').AsInteger = 0 then
      begin
            DataSet.FieldByName('NrOrdem').AsInteger := QcValAtr.RecordCount + 1;
      end;

      if DataSet.FieldByName('CdValida').AsInteger = 0 then
      begin
            raise Exception.Create('Código de validação deve ser informado');
      end;
end;

procedure TCad0001.QcViewAfterPost(DataSet: TDataSet);
begin
      PString(TrvDicion.Selected.Data)^ := QcView.FieldByName('NmView').AsString;
      TrvDicion.Selected.Text           := QcView.FieldByName('NmView').AsString;
      TrvDicion.Selected.ImageIndex     := gImgView;
      TrvDicion.Selected.SelectedIndex  := gImgView;
      DBEdit29.Enabled                  := False;
end;

procedure TCad0001.QcViewBeforePost(DataSet: TDataSet);
begin
      if QcView.FieldByName('NmView').AsString = '' then
      begin
            raise Exception.Create('Informe o nome ds view');
      end;

      if QcView.FieldByName('TxView').AsString = '' then
      begin
            raise Exception.Create('Informe o texto da view');
      end;
end;

procedure TCad0001.QSqlAfterPost(DataSet: TDataSet);
begin
     SbtCommit.Enabled := True;
     SbtRollba.Enabled := True;
end;

procedure TCad0001.SbtCancelClick(Sender: TObject);
begin
      EdtNrPosAtr.Enabled  := True;

      PgcBanco.ActivePage := TbsNada;

      GbxNrPosKey.Visible := False;

      QcTabela.Cancel;
      QcTabATr.Cancel;
      QcChaPri.Cancel;
      QcChaEst.Cancel;
      QcColEst.Cancel;
      QcTrigger.Cancel;
      QcIndice.Cancel;
      QcColInd.Cancel;
      QcProced.Cancel;
      QcView.Cancel;
      QcExcess.Cancel;
      QcForDin.Cancel;

      if TrvDicion.Selected.ImageIndex = gImgEditar then
      begin
            TrvDicion.Selected.Delete;
      end;

      gTpOperac := 0;
      GbxSubFor.Visible   := True;
end;

procedure TCad0001.SbtCommitClick(Sender: TObject);
begin
      QSql.ApplyUpdates(0);
      SbtCommit.Enabled := False;
      SbtRollba.Enabled := False;
      SbtExeSqlClick(SbtExeSql);
end;

procedure TCad0001.SbtEditClick(Sender: TObject);
begin
      SbtExeSqlClick(SbtExeSql);
end;

procedure TCad0001.SbtExeSqlClick(Sender: TObject);
var lDsSql    : String;
    lSnExecut : Boolean;
    lHoInicio : TTime;
begin
      lSnExecut := True;

      if SbtCommit.Enabled then
      begin
            if MessageDlg('Confirma o cancelamento das alterações feitas?',
                                             MtConfirmation, [mbYes, mbNo], 0) = IdNo then
            begin
                  lSnExecut := False;
            end;
      end;

      if lSnExecut then
      begin
            SbtCommit.Enabled := False;
            SbtRollba.Enabled := False;
            PnlDados.Visible  := False;
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
                            DbgSQL.ReadOnly := not SbtEdit.Down;
                            QSql.Close;
                            QSql.CommandText := lDsSql;
                            QSql.Open;
                            PnlResult.Caption := 'Resultado: ' +
                                                 IntToStr(QSql.RecordCount) + ' linha(s)';
                        except
                            raise;
                        end;

                        PnlDados.Visible := True;
                  end else
                  begin
                        PnlResult.Caption := IntToStr(ExecutarSql(lDsSql)) +
                          ' linha(s) afetada(s)';
                  end;

                  PnlTmSql.Caption := TimeToStr(Time - lHoInicio);
             end;
      end;
end;

procedure TCad0001.SbtExportClick(Sender: TObject);
var lNmTabela : String;
begin
      lNmTabela := NomeDaTabelaDaInstrucaoSql(QSql.CommandText);
      BrvExport.Execute(lNmTabela, QSql.Data);
end;

procedure TCad0001.SbtForDinClick(Sender: TObject);
begin
      Cad0002 := TCad0002.Create(Self);

      try
          Cad0002.CarregarFormulario(QcForDin.FieldByName('NmTabela').AsString,
                                     QcForDin.FieldByName('NrForDin').AsInteger);
          Cad0002.ShowModal;
      finally
          FreeAndNil(Cad0002);
      end;
end;

function TCad0001.NomeDaTabelaDaInstrucaoSql(pDsSql : String) : String;
begin
      Result := UpperCase(pDsSql);
      Delete(Result, 1, Pos('FROM', Result) + 4);
      Result := Trim(Result);

      if Pos(' ', Result) > 0  then
      begin
            Result := Copy(Result, 1, Pos(' ', Result));
            Result := Trim(Result);
      end;
end;

function TCad0001.ExecutarSql(pDsSql : String) : Integer;
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      lConexao := TSDmRWClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          Result := lConexao.ExecutarInstrucaoSql(DmSrvApl.BrvDicionario.Credencial,
                                                  lDsResult, pDsSql);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          MessageDlg('Instrução executada com sucesso!', MtInformation, [mbOk], 0);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCad0001.Atualizar1Click(Sender: TObject);
begin
      inherited;
      IniciarTreeView;
end;

procedure TCad0001.AtualizarPosicaoAtributo(pNmTabela : String;
                                            pNrNewPos : Integer; pNrOldPos : Integer);
var lConexao  : TSDmSisClient;
    lDsResult : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lDsResult := lConexao.AtualizarPosicaoAtributo(DmSrvApl.BrvDicionario.Credencial,
                                                         pNmTabela, pNrNewPos, pNrOldPos);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCad0001.AtualizarPosicaoChave(pTpChave  : String; pNmChaEst : String;
                                         pNmTabela : String; pNrNewPos : Integer;
                                         pNrOldPos : Integer);
var lConexao  : TSDmSisClient;
    lDsResult : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.AtualizarPosicaoChave(DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                         pTpChave, pNmChaEst, pNmTabela, pNrNewPos,
                                         pNrOldPos);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCad0001.SbtGravarClick(Sender: TObject);
begin
      try
          if QcTabela.State in [dsInsert, dsEdit] then
          begin
                QcTabela.Post;
                QcTabela.ApplyUpdates(0);
          end;

          if QcTabATr.State in [dsInsert, dsEdit] then
          begin
                QcTabATr.Post;
                QcTabATr.ApplyUpdates(0);

                AtualizarPosicaoAtributo(QcTabAtr.FieldByName('NmTabela').AsString,
                                         EdtNrPosAtr.Value, EdtNrPosAtr.Tag);

                TrvDicion.Selected := TrvDicion.Selected.Parent;
                TrvDicion.Selected.DeleteChildren;
                TrvDicionDblClick(TrvDicion);
          end;

          if QcChaEst.State in [dsInsert, dsEdit] then
          begin
                QcChaEst.Post;
                QcChaEst.ApplyUpdates(0);
          end;

          if QcColEst.State in [dsInsert, dsEdit] then
          begin
                QcColEst.Post;
                QcColEst.ApplyUpdates(0);
          end;

          if QcTrigger.State in [dsInsert, dsEdit] then
          begin
                QcTrigger.Post;
                QcTrigger.ApplyUpdates(0);
          end;

          if QcIndice.State in [dsInsert, dsEdit] then
          begin
                QcIndice.Post;
                QcIndice.ApplyUpdates(0);
          end;

          if QcColInd.State in [dsInsert, dsEdit] then
          begin
                QcColInd.Post;
                QcColInd.ApplyUpdates(0);
          end;

          if QcProced.State in [dsInsert, dsEdit] then
          begin
                QcProced.Post;
                QcProced.ApplyUpdates(0);
          end;

          if QcView.State in [dsInsert, dsEdit] then
          begin
                QcView.Post;
                QcView.ApplyUpdates(0);
          end;

          if QcExcess.State in [dsInsert, dsEdit] then
          begin
                QcExcess.Post;
                QcExcess.ApplyUpdates(0);
          end;

          if QcForDin.State in [dsInsert, dsEdit] then
          begin
                QcForDin.Post;
                QcForDin.ApplyUpdates(0);
                GbxSubFor.Visible   := True;
          end;

          // Atualizando posição das chaves primária e extrangeira
          if GbxNrPosKey.Visible then
          begin
                if EdtNrPosKey.Value <> EdtNrPosKey.Tag then
                begin
                      if PgcBanco.ActivePage = TbsChaPri then
                      begin
                            try
                                AtualizarPosicaoChave('P',
                                                '', // Nome da chave estrangeira
                                                QcChaPri.FieldByName('NmTabela').AsString,
                                                EdtNrPosKey.Value, EdtNrPosKey.Tag);

                                TrvDicion.Selected := TrvDicion.Selected.Parent;
                                TrvDicion.Selected.DeleteChildren;
                                TrvDicionDblClick(TrvDicion);
                            finally
                                QcChaPri.Cancel;
                                GbxNrPosKey.Visible  := False;
                            end;
                      end
                      else if PgcBanco.ActivePage = TbsColEst then
                           begin
                                 try
                                     AtualizarPosicaoChave('E',
                                                QcColEst.FieldByName('NmChaEst').AsString,
                                                QcColEst.FieldByName('NmTabela').AsString,
                                                EdtNrPosKey.Value, EdtNrPosKey.Tag);

                                     TrvDicion.Selected := TrvDicion.Selected.Parent;
                                     TrvDicion.Selected.DeleteChildren;
                                     TrvDicionDblClick(TrvDicion);
                                 finally
                                     QcColEst.Cancel;
                                     GbxNrPosKey.Visible  := False;
                                 end;
                           end
                      else if PgcBanco.ActivePage = TbsColInd then
                           begin
                                 try
                                     AtualizarPosicaoChave('I',
                                                QcColInd.FieldByName('NmIndice').AsString,
                                                QcColInd.FieldByName('NmTabela').AsString,
                                                EdtNrPosKey.Value, EdtNrPosKey.Tag);

                                     TrvDicion.Selected := TrvDicion.Selected.Parent;
                                     TrvDicion.Selected.DeleteChildren;
                                     TrvDicionDblClick(TrvDicion);
                                 finally
                                     QcColInd.Cancel;
                                     GbxNrPosKey.Visible  := False;
                                 end;
                           end
                      ;
                end else
                begin
                      SbtCancelClick(SbtCancel);
                end;
          end else
          begin
                // Inserindo chave primária
                if QcChaPri.State in [dsInsert] then
                begin
                      QcChaPri.Post;
                      QcChaPri.ApplyUpdates(0);
                end;
          end;

          gTpOperac := 0;
          TrvDicion.SetFocus;
      except
          raise;
      end;
end;

procedure TCad0001.SbtImprimClick(Sender: TObject);
begin
      try
          Rel0001 := TRel0001.Create(Self);

          Rel0001.FormStyle := fsNormal;
          Rel0001.Visible := False;
          Rel0001.ShowModal;

      finally
          FreeAndNil(Rel0001);
      end;
end;

initialization
      RegisterClass(TCad0001);

finalization
      UnRegisterClass(TCad0001);


end.
