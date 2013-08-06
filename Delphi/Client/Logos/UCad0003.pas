unit UCad0003;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, Buttons, BrvSpeedButton, ExtCtrls, BrvDbNavCop, ComCtrls, StdCtrls,
  DB, DBClient, BrvClientDataSet, UClaSrv, Grids, DBGrids, BrvDbGrid, BrvDb,
  BrvDbEdit, Mask, DBCtrls, BrvDBComboListBox, BrvString, BrvRelatorio, BrvOrdenar,
  BrvDbGrids, ImgList, ToolWin, Menus, BrvLocalizar, BrvFiltrar, BrvExport, BrvImport,
  BrvDbRetCon, RegularExpressions, BrvDigito, BrvXml, BrvListParam;

type
   TValida = record
       TpValida : String;
       DsValida : String;
       DsMensag : String;
       DsExpres : String;
   end;

  TCompon = record
       TpCompon : Char;
       DsAlias  : Char;
       NmColuna : String;
       DsColuna : String;
       TpColuna : String;
       TmColuna : Integer;
       SnNulo   : Boolean;
       SnAutInc : Boolean;
       DsDomini : String;
       VrDomini : String;
       NoConsul : Integer;
       NmChaCon : String;  // nome da chave de consulta
       DsKeyCon : String;
       VrDefaul : String;
       TpDefaul : String;
       DsCaptio : String;
       TpMascar : String;
       DsMascar : String;
       DsHelp   : String;
       DsHint   : String;
       CdPermis : Integer;
       SnKey    : Boolean;
       DsDisFor : String;
       SnEnable : Boolean;
       SnSubFor : Boolean;
       ArValida : array of TValida;
  end;

  TCad0003 = class(TCad0000)
    SbtLimpar: TBrvSpeedButton;
    QpForDin: TBrvClientDataSet;
    QpComDin: TBrvClientDataSet;
    QpComPai: TBrvClientDataSet;
    QCadastro: TBrvClientDataSet;
    DCadastro: TDataSource;
    BrvString: TBrvString;
    SbtLocali: TBrvSpeedButton;
    SbtLocAva: TBrvSpeedButton;
    SbtPosici: TBrvSpeedButton;
    SbtInsert: TBrvSpeedButton;
    SbtCancel: TBrvSpeedButton;
    SbtGravar: TBrvSpeedButton;
    SbtDelete: TBrvSpeedButton;
    SbtPrimei: TBrvSpeedButton;
    SbtAnteri: TBrvSpeedButton;
    SbtProxim: TBrvSpeedButton;
    SbtUltimo: TBrvSpeedButton;
    SbtGrade: TBrvSpeedButton;
    SbtOrdem: TBrvSpeedButton;
    SbtImprim: TBrvSpeedButton;
    SbtExport: TBrvSpeedButton;
    SbtImport: TBrvSpeedButton;
    Panel1: TPanel;
    Panel2: TPanel;
    Panel3: TPanel;
    Panel4: TPanel;
    SbtDuplic: TBrvSpeedButton;
    PgcPrinci: TPageControl;
    TbsPrinci: TTabSheet;
    SbxFundo: TScrollBox;
    PnlProgre: TPanel;
    LblProgre: TLabel;
    PgbProgre: TProgressBar;
    TbsGrade: TTabSheet;
    DbgColuna: TBrvDbGrid;
    DlgRelato: TRelatorio;
    DlgOrdenar: TBrvOrdenar;
    QpConsult: TBrvClientDataSet;
    ImageList1: TImageList;
    QpSubFor: TBrvClientDataSet;
    PopSubFor: TPopupMenu;
    N11: TMenuItem;
    N21: TMenuItem;
    BrvLocalizar: TBrvLocalizar;
    BrvFiltrar: TBrvFiltrar;
    BrvExport: TBrvExport;
    BrvImport: TBrvImport;
    PopLog: TPopupMenu;
    VerLog1: TMenuItem;
    QpValida: TBrvClientDataSet;
    BrvDigito: TBrvDigito;
    SbtSubCad: TBrvSpeedButton;
    procedure FormCreate(Sender: TObject);
    procedure SbtLimparClick(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure SbtLocaliClick(Sender: TObject);
    procedure QCadastroAfterScroll(DataSet: TDataSet);
    procedure DbgColunaKeyPress(Sender: TObject; var Key: Char);
    procedure SbtInsertClick(Sender: TObject);
    procedure SbtCancelClick(Sender: TObject);
    procedure SbtGravarClick(Sender: TObject);
    procedure SbtDeleteClick(Sender: TObject);
    procedure QCadastroAfterCancel(DataSet: TDataSet);
    procedure QCadastroAfterDelete(DataSet: TDataSet);
    procedure QCadastroAfterEdit(DataSet: TDataSet);
    procedure QCadastroAfterInsert(DataSet: TDataSet);
    procedure QCadastroAfterOpen(DataSet: TDataSet);
    procedure QCadastroAfterPost(DataSet: TDataSet);
    procedure QCadastroBeforeDelete(DataSet: TDataSet);
    procedure QCadastroBeforeInsert(DataSet: TDataSet);
    procedure QCadastroBeforePost(DataSet: TDataSet);
    procedure SbtPrimeiClick(Sender: TObject);
    procedure SbtAnteriClick(Sender: TObject);
    procedure SbtProximClick(Sender: TObject);
    procedure SbtUltimoClick(Sender: TObject);
    procedure SbtDuplicClick(Sender: TObject);
    procedure SbtGradeClick(Sender: TObject);
    procedure SbtImprimClick(Sender: TObject);
    procedure SbtOrdemClick(Sender: TObject);
    procedure FormKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
    procedure PopSubForPopup(Sender: TObject);
    procedure SbtAjudaClick(Sender: TObject);
    procedure SbtPosiciClick(Sender: TObject);
    procedure SbtLocAvaClick(Sender: TObject);
    procedure SbtExportClick(Sender: TObject);
    procedure SbtImportClick(Sender: TObject);
    procedure QCadastroReconcileError(DataSet: TCustomClientDataSet; E: EReconcileError;
      UpdateKind: TUpdateKind; var Action: TReconcileAction);
    procedure PopLogPopup(Sender: TObject);
    procedure VerLog1Click(Sender: TObject);
    procedure SbtSubCadClick(Sender: TObject);
  private
    { Private declarations }
    gSnInclui : String;
    gSnDelete : String;
    gNmTabela : String;
    gDsSelect : String;
    gDsOrdIni : String;
    gDsCompon : array of TCompon;
    gDsWheNul : String;
    gStlWheSub: TStringList;
    gDsHelp   : String;
    gSnLer    : Char;
    gNrCompon : Integer;
    gDsWheAva : String; // where da consulta avançada
    gDsWhere  : String; // where da consulta do formulário
    gDsOrder  : String; // campos de ordenação
    procedure CriarField(pNrCompon: Integer);
    procedure CriarFieldQuery(pTpColuna : String;   pTmColuna : Integer;
                              pTpCaract : String;   pDsMascar : String;
                              pNmColuna : String;   pDataSet  : TClientDataSet;
                              pDsColuna : String;   pNrTag    : Integer;
                              pSnProvid : Boolean);
    procedure SetarTextoCampo(Sender: TField; const Text: String);
    procedure CriarLabel(pNrCompon : Integer; pDsCaptio : String;  pSnChaPri : Boolean;
                         pSnNulo   : Boolean; pNrComPai : Integer; pVrLeft   : Integer;
                         pVrTop    : Integer; pVrHeight : Integer; pVrWidth  : Integer;
                         pSnAutInc : Boolean;
                         pSnAncAci : Boolean; pSnAncAba : Boolean;
                         pSnAncEsq : Boolean; pSnAncDir : Boolean);

    function EncontrarComponentePai(pNrTag: Integer; pDsCompon: String): TWinControl;
    procedure CriarPanel(pNrCompon : Integer; pNrComPai : Integer;
                         pVrLeft   : Integer; pVrTop    : Integer;
                         pVrHeight : Integer; pVrWidth  : Integer;
                         pSnAncAci : Boolean; pSnAncAba : Boolean;
                         pSnAncEsq : Boolean; pSnAncDir : Boolean);
    procedure CriarPageControl(pNrCompon : Integer; pNrComPai : Integer;
                               pVrLeft   : Integer; pVrTop    : Integer;
                               pVrHeight : Integer; pVrWidth  : Integer;
                               pSnAncAci : Boolean; pSnAncAba : Boolean;
                               pSnAncEsq : Boolean; pSnAncDir : Boolean);
    procedure CriarTabSheet(pNrCompon : Integer; pNrComPai : Integer;
                            pDsCaptio : String);
    procedure CriarEdit(pNrCompon : Integer;     pDsAlias  : Char;
                        pNmColuna : String;      pNrComPai : Integer;
                        pVrLeft   : Integer;     pVrTop    : Integer;
                        pVrHeight : Integer;     pVrWidth  : Integer;
                        pSnEnable : Boolean;     pSnVisibl : Boolean;
                        pTpCompon : String;      pNrQueCon : Integer;
                        pSnAncAci : Boolean;     pSnAncAba : Boolean;
                        pSnAncEsq : Boolean;     pSnAncDir : Boolean;
                        pSnAutInc : Boolean);
    procedure CriarMemo(pNrCompon : Integer;     pDsAlias  : Char;
                        pNmColuna : String;      pNrComPai : Integer;
                        pVrLeft   : Integer;     pVrTop    : Integer;
                        pVrHeight : Integer;     pVrWidth  : Integer;
                        pSnEnable : Boolean;     pSnVisibl : Boolean;
                        pTpCompon : String;      pNrQueCon : Integer;
                        pSnAncAci : Boolean;     pSnAncAba : Boolean;
                        pSnAncEsq : Boolean;     pSnAncDir : Boolean);
    procedure CriarComboBox(pNrCompon : Integer;     pDsAlias  : Char;
                            pNmColuna : String;      pNrComPai : Integer;
                            pVrLeft   : Integer;     pVrTop    : Integer;
                            pVrHeight : Integer;     pVrWidth  : Integer;
                            pSnEnable : Boolean;     pSnVisibl : Boolean;
                            pTpCompon : String;      pNrQueCon : Integer;
                            pDsDomini : String;      pVrDomini : String;
                            pSnAncAci : Boolean;     pSnAncAba : Boolean;
                            pSnAncEsq : Boolean;     pSnAncDir : Boolean);
    procedure CriarCheckBox(pNrCompon : Integer;     pDsAlias  : Char;
                            pNmColuna : String;      pNrComPai : Integer;
                            pVrLeft   : Integer;     pVrTop    : Integer;
                            pVrHeight : Integer;     pVrWidth  : Integer;
                            pSnEnable : Boolean;     pSnVisibl : Boolean;
                            pTpCompon : String;      pNrQueCon : Integer;
                            pVrDomini : String;      pDsCaptio : String;
                            pSnAncAci : Boolean;     pSnAncAba : Boolean;
                            pSnAncEsq : Boolean;     pSnAncDir : Boolean);
    procedure CriarRadioGroup(pNrCompon : Integer;     pDsAlias  : Char;
                              pNmColuna : String;      pNrComPai : Integer;
                              pVrLeft   : Integer;     pVrTop    : Integer;
                              pVrHeight : Integer;     pVrWidth  : Integer;
                              pSnEnable : Boolean;     pSnVisibl : Boolean;
                              pTpCompon : String;      pNrQueCon : Integer;
                              pDsDomini : String;      pVrDomini : String;
                              pDsCaptio : String;
                              pSnAncAci : Boolean;     pSnAncAba : Boolean;
                              pSnAncEsq : Boolean;     pSnAncDir : Boolean);
   procedure CriarImagem(pNrCompon : Integer;     pDsAlias  : Char;
                         pNmColuna : String;      pNrComPai : Integer;
                         pVrLeft   : Integer;     pVrTop    : Integer;
                         pVrHeight : Integer;     pVrWidth  : Integer;
                         pSnEnable : Boolean;     pSnVisibl : Boolean;
                         pTpCompon : String;      pNrQueCon : Integer;
                         pSnAncAci : Boolean;     pSnAncAba : Boolean;
                         pSnAncEsq : Boolean;     pSnAncDir : Boolean);
    procedure MontarWhereNulo;
    procedure AtribuirProviderDeCadastro(pNrForDin : Integer);
    procedure LiberarProviderFormDinamico;
    procedure SetarFocoPrimeiroComponente;
    function MontarWhereConsulta(pDsDatSet: TClientDataSet; pDsDbGrid: TBrvDbGrid;
                                 pDsAlias: String): String;
    procedure KeyPress(Sender: TObject; var Key: Char);
    function ValorDefault(pVrDefaul: String): String;
    function ObjetoDeCampoEditavel(pDsCompon: TWinControl): Boolean;
    procedure CarregarOrdenacaoInicial;
    procedure SetarOrdenacaoInicial;
    procedure CriarDbLabel(pNrCompon : Integer;     pDsAlias  : Char;
                           pNmColuna : String;      pNrComPai : Integer;
                           pVrLeft   : Integer;     pVrTop    : Integer;
                           pVrHeight : Integer;     pVrWidth  : Integer;
                           pNrForDin : Integer;
                           pSnAncAci : Boolean;     pSnAncAba : Boolean;
                           pSnAncEsq : Boolean;     pSnAncDir : Boolean);
    function ApelidoColunaConsulta: Char;
    function RetornoDaConsulta(pNrForDin : Integer; pNrCompon : Integer;
                               pDsAlias  : Char): String;
    function ColunasPesquisaChave(pNmChave: String): String;
    function CamposRetornoConsulta(pNrForDin: Integer; pNrCompon: String): String;
    procedure AtualizarEditConsulta(pNrCompon: Integer; pDsConfig: String);
    procedure AtualizarUtilizacaoFormDinamico(pNrForDin: Integer);
    procedure MontarSubFormularios(pNrForDin: Integer);
    procedure SubFormularioClick(Sender: TObject);
    procedure ChamarSubFormulario(Sender: TObject);
    procedure AtivarMenuSubFormularios(pSnEnable: Boolean);
    procedure DesabilitarCamposIgualdadeSubFormulario;
    procedure HabilitarColunaEdicaoGrade(pNmColuna: String; pSnEnable: Boolean);
    procedure AbrirCadastroPrincipal(pDsWhere: String);
    function CampoDeIgualdadeSubFormulario(pNmCampo: String): Boolean;
    function PermiteModificarCampo(pNmColuna: String): Boolean;
    function Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir: Boolean): TAnchors;
    procedure CarregarRetornosConsulta(pNrForDin: Integer; pNrCompon: String;
      pNrTabGri: Integer);
    procedure CarregarValidacoes(pNrCompon: Integer; pNmAtribu : String);
    procedure VerificarRegraExpressao(pNrCompon: Integer; pDsValor : String;
                                      pDsColuna: String);
  public
    { Public declarations }
    procedure MontarFormularioCadastro(pNrForDin: Integer; pDsWheSub : String);
  end;

var
  Cad0003: TCad0003;

implementation

uses UDmSrvApl, UCai0007;

{$R *.dfm}

procedure TCad0003.FormClose(Sender: TObject; var Action: TCloseAction);
begin
      FreeAndNil(gStlWheSub);
      LiberarProviderFormDinamico;
      inherited;
end;

procedure TCad0003.LiberarProviderFormDinamico;
var lConexao     : TSDmSisClient;
    lDsResult    : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-= Criarndo Provider no servidor de aplicação
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          lConexao.LiberarProviderFormDinamico(
                                   DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                   QCadastro.ProviderName);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCad0003.FormCreate(Sender: TObject);
begin
      inherited;
      TbsGrade.TabVisible  := False;
      TbsPrinci.TabVisible := False;
      PgcPrinci.ActivePage := TbsPrinci;
      gDsOrder             := '';
      gStlWheSub           := TStringList.Create;
end;

procedure TCad0003.FormKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
begin
      inherited;
      if Shift = [ssCtrl] then
      begin
            case key of
                 77: if SbtPrimei.Enabled  then SbtPrimeiClick(Self);  {ctrl+m}
                 65: if SbtAnteri.Enabled  then SbtAnteriClick(Self);  {ctrl+a}
                 85: if SbtUltimo.Enabled  then SbtUltimoClick(Self);  {ctrl+u}
                 88: if SbtProxim.Enabled  then SbtProximClick(Self);  {ctrl+x}
                 79: if SbtOrdem.Enabled   then SbtOrdemClick(Self);   {ctrl+o}
                 70: if SbtLocAva.Enabled  then SbtLocAvaClick(Self);  {ctrl+f}
                 76: if SbtLocali.Enabled  then SbtLocaliClick(Self);  {ctrl+l}
                 80: if SbtImprim.Enabled  then SbtImprimClick(Self);  {ctrl+p}
            end
      end else
      begin
            case key of
                 112: SbtAjudaClick(nil);                                   {F1}
                 113: if SbtLimpar.Enabled   then SbtLimparClick(Self);     {F2}
                 114: if (SbtInsert.Enabled) and (SbtInsert.Visible)
                                             then SbtInsertClick(Self);     {F3}
                 115: if (SbtCancel.Enabled) and (SbtCancel.Visible)
                                             then SbtCancelClick(Self);     {F4}
                 116: if SbtGravar.Enabled   then SbtGravarClick(Self);     {F5}
                 117: if SbtDelete.Enabled   then SbtDeleteClick(Self);     {F6}
                 118: if SbtLocali.Enabled   then SbtLocaliClick(Self);     {F7}
                 119: if SbtGrade.Enabled    then SbtGradeClick(Self);      {F8}
            end;
      end;
end;

procedure TCad0003.AtualizarUtilizacaoFormDinamico(pNrForDin : Integer);
var lConexao     : TSDmSisClient;
    lDsResult    : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-= Criarndo Provider no servidor de aplicação
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

          lConexao.AtualizarUtilizacaoFormulario(
                                   DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                   DmSrvApl.BrvDicionario.UserCode,
                                   pNrFordin, '', 0);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCad0003.SbtSubCadClick(Sender: TObject);
var lPoint : TPoint;
begin
      inherited;
      GetCursorPos(lPoint);
      PopSubFor.Popup(lPoint.X, lPoint.Y);
end;

procedure TCad0003.MontarFormularioCadastro(pNrForDin : Integer; pDsWheSub : String);
var lNoCompon    : Integer;
    lDsAlias     : Char;
    lDsJoin      : String;
    lStlDsChave  : TStrings;
    lStlDsChaEst : TStrings;
    lNoTabGri    : Integer;
    lNoChave     : Integer;
    lDsCabHel    : String;
    lDsHelp      : String;
    lSnHelp      : Boolean;
    lNrColGri    : Integer;
    lStForm      : TWindowState;
begin
      lStForm := WindowState;
      WindowState := wsNormal;

      AtualizarUtilizacaoFormDinamico(pNrForDin);

      if Trim(pDsWheSub) <> '' then
      begin
            // =-=-=-=-=-=
            BrvString.Split(pDsWheSub, '@');
            gStlWheSub.Text := BrvString.BrSplitLista.Text;
            // =-=-=-=-=-=
      end else
      begin
            gStlWheSub.Clear
      end;

      PnlProgre.Visible := True;
      LblProgre.Caption := 'Carregando o formulário principal. Aguarde...';
      PnlProgre.Refresh;

      lDsAlias  := 'A';
      gNrCompon := 0;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-= Obtendo informações do form dinamico
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      QpForDin.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
               ' Select S014.*, ' +
                       '(Select Max(S015.NrCompon) ' +
                       ' From S015 ' +
                       ' Where S015.NrForDin = S014.NrForDin) as QtCompon' +
               ' From S014 ' +
               ' Where NrForDin =   '  + IntToStr(pNrForDin));

      gSnInclui := QpForDin.FieldByName('SnInclui').AsString;
      gNmTabela := QpForDin.FieldByName('NmTabela').AsString;
      gSnDelete := QpForDin.FieldByName('SnDelete').AsString;
      Caption   := QpForDin.FieldByName('NmTabela').AsString + ' - ' +
                   '(' + IntToStr(pNrForDin) + ') - ' +
                   QpForDin.FieldByName('DsForDin').AsString;
      lDsCabHel := QpForDin.FieldByName('DsHelp').AsString + '<p>';

      PgbProgre.Max := QpForDin.FieldByName('QtCompon').AsInteger * 2;
      SetLength(gDsCompon, PgbProgre.Max + 10);

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      gDsSelect := 'Select ';
      lDsJoin   := '';

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-= Encontrando Componentes Pai (Criar um pai por vez por causa do taborder
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      QpComPai.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                       ' Select Distinct(NrComPai) ' +
                                       ' From S015 ' +
                                       ' Where NrForDin = ' + IntToStr(pNrForDin) +
                                       ' Order by NrComPai');
      gDsHelp   := '';

      while not QpComPai.Eof do
      begin
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Encontrando Componentes do pai
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            QpComDin.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                   ' Select S015.*, S016.NmTabCon ' +
                   ' From S015 S015' +
                   ' Left join S016 S016 on (S016.NrQueCon = S015.NrQueCon) ' +
                   ' Where NrForDin = ' + IntToStr(pNrForDin) +
                   ' and   NrComPai = ' + QpComPai.FieldByName('NrComPai').AsString +
                   ' Order by NrTabOrd');

            while not QpComDin.Eof do
            begin
                  lNoCompon := QpComDin.FieldByName('NrCompon').AsInteger;
                  if lNoCompon > gNrCompon then
                  begin
                        gNrCompon  := lNoCompon;
                  end;

                  CarregarValidacoes(lNoCompon, QpComDin.FieldByName('NmAtribu').AsString);

                  gDsCompon[lNoCompon].TpCompon :=
                                       QpComDin.FieldByName('TpCompon').AsString[1];
                  gDsCompon[lNoCompon].DsCaptio :=
                                       QpComDin.FieldByName('DsCaptio').AsString;
                  gDsCompon[lNoCompon].SnNulo   :=
                                       QpComDin.FieldByName('SnNulo').AsString = 'S';
                  gDsCompon[lNoCompon].SnAutInc :=
                                       QpComDin.FieldByName('SnAutInc').AsString = 'S';
                  gDsCompon[lNoCompon].SnEnable :=
                                       QpComDin.FieldByName('SnEnable').AsString = 'S';
                  gDsCompon[lNoCompon].NmColuna :=
                                       QpComDin.FieldByName('NmAtribu').AsString;
                  gDsCompon[lNoCompon].TpDefaul :=
                                       QpComDin.FieldByName('TpDefaul').AsString;
                  gDsCompon[lNoCompon].VrDefaul :=
                                       QpComDin.FieldByName('VrDefaul').AsString;
                  gDsCompon[lNoCompon].NoConsul :=
                                       QpComDin.FieldByName('NrQueCon').AsInteger;
                  gDsCompon[lNoCompon].SnSubFor := False;


                  if (gDsCompon[lNoCompon].TpCompon <> 'A') then
                  begin
                        gDsCompon[lNoCompon].DsAlias := 'A';

                        DmSrvApl.BrvDicionario.AtributoConfiguracao(
                                 gNmTabela,
                                 gDsCompon[lNoCompon].NmColuna,
                                 gDsCompon[lNoCompon].DsColuna,
                                 gDsCompon[lNoCompon].TpColuna,
                                 gDsCompon[lNoCompon].TpMascar,
                                 gDsCompon[lNoCompon].DsMascar,
                                 gDsCompon[lNoCompon].DsHelp,
                                 gDsCompon[lNoCompon].DsHint,
                                 gDsCompon[lNoCompon].TmColuna,
                                 gDsCompon[lNoCompon].CdPermis,
                                 gDsCompon[lNoCompon].VrDomini,
                                 gDsCompon[lNoCompon].DsDomini,
                                 gDsCompon[lNoCompon].SnKey,
                                 gDsCompon[lNoCompon].DsDisFor);
                  end else
                  begin // L(A)bel de consulta
                        lDsAlias := Chr(Ord(lDsAlias) + 1);

                        gDsCompon[lNoCompon].NmChaCon :=
                                             QpComDin.FieldByName('NmChaCon').AsString;
                        gDsCompon[lNoCompon].DsAlias  := lDsAlias;

                        gDsCompon[lNoCompon].NmColuna := lDsAlias + '_' +
                                                         gDsCompon[lNoCompon].NmColuna;
                        gDsCompon[lNoCompon].DsColuna := 'Retorno ' +
                                                         gDsCompon[lNoCompon].NmColuna;
                        gDsCompon[lNoCompon].TpColuna := 'V'; // Varchar
                        gDsCompon[lNoCompon].TmColuna := 100;
                        gDsCompon[lNoCompon].SnNulo   := True;
                        gDsCompon[lNoCompon].SnKey    := False;
                        gDsCompon[lNoCompon].TpMascar := 'M'; // maiusculo
                        gDsCompon[lNoCompon].SnNulo   := True;

                        lDsJoin := lDsJoin + ' left join ' +
                                   QpComDin.FieldByName('NmTabCon').AsString +
                                   ' ' + lDsAlias + ' on (';

                        try

                            lStlDsChave  := TStringList.Create;
                            lStlDsChaEst := TStringList.Create;

                            DmSrvApl.BrvDicionario.ChavePrimaria(
                                     QpComDin.FieldByName('NmTabCon').AsString,
                                     lStlDsChave);

                            DmSrvApl.BrvDicionario.AtributoEstrangeiro(
                                     QpComDin.FieldByName('NmChaCon').AsString,
                                     lStlDsChaEst);

                            for lNoChave := 0 to lStlDsChave.Count -1 do
                            begin
                                  lDsJoin := lDsJoin  + lDsAlias + '.' +
                                             lStlDsChave.Strings[lNoChave] + ' = A.' +
                                             lStlDsChaEst.Strings[lNoChave];

                                  if lNoChave < lStlDsChave.Count -1 then
                                  begin
                                        lDsJoin := lDsJoin + ' and ';
                                  end;
                            end;
                        finally
                            lStlDsChave.Destroy;
                            lStlDsChaEst.Destroy;
                        end;

                        lDsJoin := lDsJoin + ')';
                  end;

                  lSnHelp := True;

                  if (QpComDin.FieldByName('TpCompon').AsString = 'L') or // Label
                     (QpComDin.FieldByName('TpCompon').AsString = 'B') then // Label de campo
                  begin
                        CriarLabel(lNoCompon,
                                   gDsCompon[lNoCompon].DsCaptio,
                                   gDsCompon[lNoCompon].SnKey,
                                   gDsCompon[lNoCompon].SnNulo,
                                   QpComDin.FieldByName('NrComPai').AsInteger,
                                   QpComDin.FieldByName('VrLeft').AsInteger,
                                   QpComDin.FieldByName('VrTop').AsInteger,
                                   QpComDin.FieldByName('VrHeight').AsInteger,
                                   QpComDin.FieldByName('VrWidth').AsInteger,
                                   gDsCompon[lNoCompon].SnAutInc,
                                   QpComDin.FieldByName('SnAncAci').AsString = 'S',
                                   QpComDin.FieldByName('SnAncAba').AsString = 'S',
                                   QpComDin.FieldByName('SnAncEsq').AsString = 'S',
                                   QpComDin.FieldByName('SnAncdir').AsString = 'S');

                        lSnHelp := False;
                  end
                  else if (QpComDin.FieldByName('TpCompon').AsString = 'A') then // Label de consulta
                       begin
                             CriarDbLabel(lNoCompon,
                                      gDsCompon[lNoCompon].DsAlias,
                                      gDsCompon[lNoCompon].NmColuna,
                                      QpComDin.FieldByName('NrComPai').AsInteger,
                                      QpComDin.FieldByName('VrLeft').AsInteger,
                                      QpComDin.FieldByName('VrTop').AsInteger,
                                      QpComDin.FieldByName('VrHeight').AsInteger,
                                      QpComDin.FieldByName('VrWidth').AsInteger,
                                      pNrForDin,
                                      QpComDin.FieldByName('SnAncAci').AsString = 'S',
                                      QpComDin.FieldByName('SnAncAba').AsString = 'S',
                                      QpComDin.FieldByName('SnAncEsq').AsString = 'S',
                                      QpComDin.FieldByName('SnAncdir').AsString = 'S');
                             CriarField(lNoCompon);
                       end
                  else if Pos(QpComDin.FieldByName('TpCompon').AsString, 'EDN')
                                                                      > 0 then // Edit
                       begin
                             gDsCompon[lNoCompon].DsCaptio :=
                                            QpComDin.FieldByName('DsCapGri').AsString;
                             CriarEdit(lNoCompon,
                                      gDsCompon[lNoCompon].DsAlias,
                                      gDsCompon[lNoCompon].NmColuna,
                                      QpComDin.FieldByName('NrComPai').AsInteger,
                                      QpComDin.FieldByName('VrLeft').AsInteger,
                                      QpComDin.FieldByName('VrTop').AsInteger,
                                      QpComDin.FieldByName('VrHeight').AsInteger,
                                      QpComDin.FieldByName('VrWidth').AsInteger,
                                      QpComDin.FieldByName('SnEnable').AsString = 'S',
                                      QpComDin.FieldByName('SnVisibl').AsString = 'S',
                                      QpComDin.FieldByName('TpCompon').AsString,
                                      QpComDin.FieldByName('NrQueCon').AsInteger,
                                      QpComDin.FieldByName('SnAncAci').AsString = 'S',
                                      QpComDin.FieldByName('SnAncAba').AsString = 'S',
                                      QpComDin.FieldByName('SnAncEsq').AsString = 'S',
                                      QpComDin.FieldByName('SnAncdir').AsString = 'S',
                                      QpComDin.FieldByName('SnAutInc').AsString = 'S');

                             CriarField(lNoCompon);
                       end
                  else if QpComDin.FieldByName('TpCompon').AsString = 'P' then
                       begin
                             CriarPanel(lNoCompon,
                                        QpComDin.FieldByName('NrComPai').AsInteger,
                                        QpComDin.FieldByName('VrLeft').AsInteger,
                                        QpComDin.FieldByName('VrTop').AsInteger,
                                        QpComDin.FieldByName('VrHeight').AsInteger,
                                        QpComDin.FieldByName('VrWidth').AsInteger,
                                        QpComDin.FieldByName('SnAncAci').AsString = 'S',
                                        QpComDin.FieldByName('SnAncAba').AsString = 'S',
                                        QpComDin.FieldByName('SnAncEsq').AsString = 'S',
                                        QpComDin.FieldByName('SnAncdir').AsString = 'S');

                             lSnHelp := False;
                       end
                  else if QpComDin.FieldByName('TpCompon').AsString = 'G' then
                       begin
                             CriarPageControl(lNoCompon,
                                        QpComDin.FieldByName('NrComPai').AsInteger,
                                        QpComDin.FieldByName('VrLeft').AsInteger,
                                        QpComDin.FieldByName('VrTop').AsInteger,
                                        QpComDin.FieldByName('VrHeight').AsInteger,
                                        QpComDin.FieldByName('VrWidth').AsInteger,
                                        QpComDin.FieldByName('SnAncAci').AsString = 'S',
                                        QpComDin.FieldByName('SnAncAba').AsString = 'S',
                                        QpComDin.FieldByName('SnAncEsq').AsString = 'S',
                                        QpComDin.FieldByName('SnAncdir').AsString = 'S');

                             lSnHelp := False;
                       end
                  else if QpComDin.FieldByName('TpCompon').AsString = 'T' then
                       begin
                             CriarTabSheet(lNoCompon,
                                           QpComDin.FieldByName('NrComPai').AsInteger,
                                           gDsCompon[lNoCompon].DsCaptio);
                             lSnHelp := False;
                       end
                  else if QpComDin.FieldByName('TpCompon').AsString = 'M' then
                       begin
                             gDsCompon[lNoCompon].DsCaptio :=
                                            QpComDin.FieldByName('DsCapGri').AsString;
                             CriarMemo(lNoCompon,
                                      gDsCompon[lNoCompon].DsAlias,
                                      gDsCompon[lNoCompon].NmColuna,
                                      QpComDin.FieldByName('NrComPai').AsInteger,
                                      QpComDin.FieldByName('VrLeft').AsInteger,
                                      QpComDin.FieldByName('VrTop').AsInteger,
                                      QpComDin.FieldByName('VrHeight').AsInteger,
                                      QpComDin.FieldByName('VrWidth').AsInteger,
                                      QpComDin.FieldByName('SnEnable').AsString = 'S',
                                      QpComDin.FieldByName('SnVisibl').AsString = 'S',
                                      QpComDin.FieldByName('TpCompon').AsString,
                                      QpComDin.FieldByName('NrQueCon').AsInteger,
                                      QpComDin.FieldByName('SnAncAci').AsString = 'S',
                                      QpComDin.FieldByName('SnAncAba').AsString = 'S',
                                      QpComDin.FieldByName('SnAncEsq').AsString = 'S',
                                      QpComDin.FieldByName('SnAncdir').AsString = 'S');
                             CriarField(lNoCompon);
                       end
                  else if QpComDin.FieldByName('TpCompon').AsString = 'C' then
                       begin
                             gDsCompon[lNoCompon].DsCaptio :=
                                            QpComDin.FieldByName('DsCapGri').AsString;
                             CriarComboBox(lNoCompon,
                                      gDsCompon[lNoCompon].DsAlias,
                                      gDsCompon[lNoCompon].NmColuna,
                                      QpComDin.FieldByName('NrComPai').AsInteger,
                                      QpComDin.FieldByName('VrLeft').AsInteger,
                                      QpComDin.FieldByName('VrTop').AsInteger,
                                      QpComDin.FieldByName('VrHeight').AsInteger,
                                      QpComDin.FieldByName('VrWidth').AsInteger,
                                      QpComDin.FieldByName('SnEnable').AsString = 'S',
                                      QpComDin.FieldByName('SnVisibl').AsString = 'S',
                                      QpComDin.FieldByName('TpCompon').AsString,
                                      QpComDin.FieldByName('NrQueCon').AsInteger,
                                      gDsCompon[lNoCompon].DsDomini,
                                      gDsCompon[lNoCompon].VrDomini,
                                      QpComDin.FieldByName('SnAncAci').AsString = 'S',
                                      QpComDin.FieldByName('SnAncAba').AsString = 'S',
                                      QpComDin.FieldByName('SnAncEsq').AsString = 'S',
                                      QpComDin.FieldByName('SnAncdir').AsString = 'S');
                             CriarField(lNoCompon);
                       end
                  else if QpComDin.FieldByName('TpCompon').AsString = 'R' then
                       begin
                             CriarRadioGroup(lNoCompon,
                                      gDsCompon[lNoCompon].DsAlias,
                                      gDsCompon[lNoCompon].NmColuna,
                                      QpComDin.FieldByName('NrComPai').AsInteger,
                                      QpComDin.FieldByName('VrLeft').AsInteger,
                                      QpComDin.FieldByName('VrTop').AsInteger,
                                      QpComDin.FieldByName('VrHeight').AsInteger,
                                      QpComDin.FieldByName('VrWidth').AsInteger,
                                      QpComDin.FieldByName('SnEnable').AsString = 'S',
                                      QpComDin.FieldByName('SnVisibl').AsString = 'S',
                                      QpComDin.FieldByName('TpCompon').AsString,
                                      QpComDin.FieldByName('NrQueCon').AsInteger,
                                      gDsCompon[lNoCompon].DsDomini,
                                      gDsCompon[lNoCompon].VrDomini,
                                      gDsCompon[lNoCompon].DsCaptio,
                                      QpComDin.FieldByName('SnAncAci').AsString = 'S',
                                      QpComDin.FieldByName('SnAncAba').AsString = 'S',
                                      QpComDin.FieldByName('SnAncEsq').AsString = 'S',
                                      QpComDin.FieldByName('SnAncdir').AsString = 'S');
                             CriarField(lNoCompon);
                       end
                  else if QpComDin.FieldByName('TpCompon').AsString = 'I' then
                       begin
                             gDsCompon[lNoCompon].DsCaptio :=
                                            QpComDin.FieldByName('DsCapGri').AsString;
                             CriarImagem(lNoCompon,
                                      gDsCompon[lNoCompon].DsAlias,
                                      gDsCompon[lNoCompon].NmColuna,
                                      QpComDin.FieldByName('NrComPai').AsInteger,
                                      QpComDin.FieldByName('VrLeft').AsInteger,
                                      QpComDin.FieldByName('VrTop').AsInteger,
                                      QpComDin.FieldByName('VrHeight').AsInteger,
                                      QpComDin.FieldByName('VrWidth').AsInteger,
                                      QpComDin.FieldByName('SnEnable').AsString = 'S',
                                      QpComDin.FieldByName('SnVisibl').AsString = 'S',
                                      QpComDin.FieldByName('TpCompon').AsString,
                                      QpComDin.FieldByName('NrQueCon').AsInteger,
                                      QpComDin.FieldByName('SnAncAci').AsString = 'S',
                                      QpComDin.FieldByName('SnAncAba').AsString = 'S',
                                      QpComDin.FieldByName('SnAncEsq').AsString = 'S',
                                      QpComDin.FieldByName('SnAncdir').AsString = 'S');
                             CriarField(lNoCompon);
                       end
                  else if QpComDin.FieldByName('TpCompon').AsString = 'K' then
                       begin
                             CriarCheckBox(lNoCompon,
                                      gDsCompon[lNoCompon].DsAlias,
                                      gDsCompon[lNoCompon].NmColuna,
                                      QpComDin.FieldByName('NrComPai').AsInteger,
                                      QpComDin.FieldByName('VrLeft').AsInteger,
                                      QpComDin.FieldByName('VrTop').AsInteger,
                                      QpComDin.FieldByName('VrHeight').AsInteger,
                                      QpComDin.FieldByName('VrWidth').AsInteger,
                                      QpComDin.FieldByName('SnEnable').AsString = 'S',
                                      QpComDin.FieldByName('SnVisibl').AsString = 'S',
                                      QpComDin.FieldByName('TpCompon').AsString,
                                      QpComDin.FieldByName('NrQueCon').AsInteger,
                                      gDsCompon[lNoCompon].VrDomini,
                                      gDsCompon[lNoCompon].DsCaptio,
                                      QpComDin.FieldByName('SnAncAci').AsString = 'S',
                                      QpComDin.FieldByName('SnAncAba').AsString = 'S',
                                      QpComDin.FieldByName('SnAncEsq').AsString = 'S',
                                      QpComDin.FieldByName('SnAncdir').AsString = 'S');
                             CriarField(lNoCompon);
                       end;

                  if lSnHelp then
                  begin
                        lDsCabHel := lDsCabHel + '<a href="#'           +
                                     gDsCompon[lNoCompon].NmColuna      +
                                     '"><strong>' +
                                     gDsCompon[lNoCompon].DsColuna      +
                                     ':</strong></a><br>';

                        lDsHelp := gDsCompon[lNoCompon].DsHelp;
                        lDsHelp :=
                               StringReplace(lDsHelp, #13#13, '<p> ', [rfReplaceAll]);
                        lDsHelp :=
                               StringReplace(lDshelp, #13, '<br> ', [rfReplaceAll]);

                        gDsHelp := gDsHelp + '<a name="'              +
                                   gDsCompon[lNoCompon].NmColuna      +
                                   '"><font color="#0000FF"><strong>' +
                                   gDsCompon[lNoCompon].DsColuna      +
                                   ':</strong></font></a><br>'        +
                                   lDsHelp + '<p>';
                  end;

                  QpComDin.Next;
                  PgbProgre.StepIt;
            end;

            QpComPai.Next;
      end;

      gDsHelp := lDsCabHel + '<p>' + gDsHelp;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=  Criando campos na grid =-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      QpComDin.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                              ' Select * ' +
                                              ' From S015 ' +
                                              ' Where NrForDin = ' + IntToStr(pNrForDin) +
                                              ' Order by NrTabGri');

      while not QpComDin.Eof do
      begin
            if (QpComDin.FieldByName('TpCompon').AsString <> 'L') and  // label
               (QpComDin.FieldByName('TpCompon').AsString <> 'P') and  // Panel
               (QpComDin.FieldByName('TpCompon').AsString <> 'G') and  // Page Control
               (QpComDin.FieldByName('TpCompon').AsString <> 'T') and  // TabSheet
               (QpComDin.FieldByName('TpCompon').AsString <> 'I') and  // Imagem
               (QpComDin.FieldByName('TpCompon').AsString <> 'B') then // Label Campo
            begin
                  lNoTabGri := QpComDin.FieldByName('NrTabGri').AsInteger;

                  if QpComDin.FieldByName('TpCompon').AsString = 'A' then //Label consulta
                  begin
                        DbgColuna.Columns.Items[lNoTabGri].FieldName :=
                                           ApelidoColunaConsulta + '_' +
                                           QpComDin.FieldByName('NmAtribu').AsString;

                        DbgColuna.Columns.Items[lNoTabGri].ReadOnly := True;
                        DbgColuna.Columns.Items[lNoTabGri].Width    := 100;
                        DbgColuna.Columns.Items[lNoTabGri].BrPermissao := [BrLer];
                  end else
                  begin
                        lNoCompon := QpComDin.FieldByName('NrCompon').AsInteger;

                        case gDsCompon[lNoCompon].CdPermis of
                             1: DbgColuna.Columns.Items[lNoTabGri].BrPermissao :=
                                          [BrLer,BrAlterar, BrIncluir, BrExcluir];
                             2: DbgColuna.Columns.Items[lNoTabGri].BrPermissao :=
                                          [BrLer,BrAlterar, BrIncluir];
                             3: DbgColuna.Columns.Items[lNoTabGri].BrPermissao :=
                                          [BrLer,BrAlterar];
                             4: DbgColuna.Columns.Items[lNoTabGri].BrPermissao := [BrLer];
                        end;

                        DbgColuna.Columns.Items[lNoTabGri].FieldName :=
                                          QpComDin.FieldByName('NmAtribu').AsString;
                        DbgColuna.Columns.Items[lNoTabGri].BrConsulta :=
                                          QpComDin.FieldByName('NrQueCon').AsInteger;

                        DbgColuna.Columns.Items[lNoTabGri].BrPickValue.CommaText :=
                                                           gDsCompon[lNoCompon].VrDomini;
                        DbgColuna.Columns.Items[lNoTabGri].PickList.CommaText :=
                                                           gDsCompon[lNoCompon].DsDomini;

                        if DbgColuna.Columns.Items[lNoTabGri].BrConsulta = 0 then
                        begin
                              if (gDsCompon[lNoCompon].TpColuna = 'I') and
                                 (not gDsCompon[lNoCompon].SnKey) then
                              begin
                                    DbgColuna.Columns.Items[lNoTabGri].ButtonStyle :=
                                                                       cbsCalculadora;
                              end
                              else if gDsCompon[lNoCompon].TpColuna = 'D' then
                                   begin
                                         DbgColuna.Columns.Items[lNoTabGri].
                                                               ButtonStyle := cbsData;
                                   end;
                        end else
                        begin
                              DbgColuna.Columns.Items[lNoTabGri].BrConfigurar.Text :=
                                  ColunasPesquisaChave(
                                           QpComDin.FieldByName('NmChaCon').AsString);

                              DbgColuna.Columns.Items[lNoTabGri].BrConfigurar.Add(
                                      ApelidoColunaConsulta + '_' +
                                      QpComDin.FieldByName('NmAtribu').AsString + ';' +
                                      CamposRetornoConsulta(pNrForDin,
                                           QpComDin.FieldByName('NrCompon').AsString) +
                                      ';' + 'S;N;');


                              CarregarRetornosConsulta(pNrForDin,
                                           QpComDin.FieldByName('NrCompon').AsString,
                                           lNoTabGri);

                              AtualizarEditConsulta(
                                    QpComDin.FieldByName('NrCompon').AsInteger,
                                    DbgColuna.Columns.Items[lNoTabGri].BrConfigurar.Text);
                        end;
                  end;

                   DbgColuna.Columns.Items[lNoTabGri].Title.Caption :=
                                            QpComDin.FieldByName('DsCapGri').AsString;

                  DbgColuna.Columns.Items[lNoTabGri].Title.Font.Style := [fsBold];

                  if (QpComDin.FieldByName('SnNulo').AsString <> 'S') and
                     (QpComDin.FieldByName('TpCompon').AsString <> 'A') then
                  begin
                        DbgColuna.Columns.Items[lNoTabGri].Title.Font.Color := clBlue;
                  end;

                  if (QpComDin.FieldByName('SnAutInc').AsString = 'S') and
                     (QpComDin.FieldByName('TpCompon').AsString <> 'A') then
                  begin
                        DbgColuna.Columns.Items[lNoTabGri].Title.Font.Color := clGreen;
                  end;

                  if gDsCompon[QpComDin.FieldByName('NrCompon').AsInteger].SnKey then
                  begin
                        DbgColuna.Columns.Items[lNoTabGri].Title.Font.Style :=
                                 DbgColuna.Columns.Items[lNoTabGri].Title.Font.Style +
                                                                        [fsUnderline];
                  end;
            end;

            QpComDin.Next;
            PgbProgre.StepIt;
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-= Limpando colunas não utilizadas na grade
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      lNrColGri := 0;

      while lNrColGri < DbgColuna.Columns.Count do
      begin
            if DbgColuna.Columns[lNrColGri].FieldName = '' then
            begin
                  DbgColuna.Columns.Delete(lNrColGri);
            end else
            begin
                  inc(lNrColGri);
            end;
      end;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      MontarWhereNulo;

      Delete(gDsSelect, Length(gDsSelect) - 1, 2);
      gDsSelect := gDsSelect + ' From ' + gNmTabela + ' A ' + lDsJoin;
      CarregarOrdenacaoInicial;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      MontarSubFormularios(pNrForDin);
      DesabilitarCamposIgualdadeSubFormulario;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-= Solicitar Provider de acesso ao servidor de aplicação
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      AtribuirProviderDeCadastro(pNrForDin);

      SbtLimpar.Enabled := True;
      SbtLocali.Enabled := False;
      SbtLocAva.Enabled := True;
      SbtPosici.Enabled := False;
      SbtPrimei.Enabled := False;
      SbtAnteri.Enabled := False;
      SbtProxim.Enabled := False;
      SbtUltimo.Enabled := False;
      SbtInsert.Enabled := True;
      SbtDelete.Enabled := False;
      SbtGravar.Enabled := False;
      SbtDuplic.Enabled := false;
      SbtCancel.Enabled := False;
      SbtGrade.Enabled  := True;
      SbtOrdem.Enabled  := False;
      SbtImprim.Enabled := False;
      SbtExport.Enabled := False;
      SbtImport.Enabled := False;

      WindowState := lStForm;

      PnlProgre.Visible := False;
end;

procedure TCad0003.CarregarValidacoes(pNrCompon : Integer; pNmAtribu : String);
var lNrValida : Integer;
    lDsSql    : String;
begin
      lDsSql := ' Select S048.NrOrdem, ' +
                '        S047.TpValida, S047.DsValida, S047.DsMensag, S047.DsExpres ' +
                ' From S048 S048' +
                ' Left join S047 S047 on (S047.CdValida = S048.CdValida) ' +
                ' Where S048.NmTabela = ' + #39 + gNmTabela + #39 +
                ' and   S048.NmAtribu = ' + #39 + pNmAtribu + #39 +
                ' Order by S048.NrOrdem';

      QpValida.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(lDsSql);

      SetLength(gDsCompon[pNrCompon].ArValida, QpValida.RecordCount + 1);
      lNrValida := 0;

      while not QpValida.Eof do
      begin
            gDsCompon[pNrCompon].ArValida[lNrValida].TpValida :=
                                 QpValida.FieldByName('TpValida').AsString;
            gDsCompon[pNrCompon].ArValida[lNrValida].DsValida :=
                                 QpValida.FieldByName('DsValida').AsString;
            gDsCompon[pNrCompon].ArValida[lNrValida].DsMensag :=
                                 QpValida.FieldByName('DsMensag').AsString;
            gDsCompon[pNrCompon].ArValida[lNrValida].DsExpres :=
                                 QpValida.FieldByName('DsExpres').AsString;

            QpValida.Next;
            inc(lNrValida);
      end;
end;

procedure TCad0003.CarregarRetornosConsulta(pNrForDin : Integer; pNrCompon : String;
                                            pNrTabGri : Integer);
var lCdsAtrCon   : TClientDataSet;
begin
      lCdsAtrCon := TClientDataSet.Create(Self);
      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-= Obtendo informações do form dinamico
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          lCdsAtrCon.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                      ' Select S045.NmAtrOri, S045.NmAtrDes ' +
                                      ' From   S045 S045 ' +
                                      ' Where  S045.NrForDin = ' + IntToStr(pNrForDin) +
                                      ' and    S045.NrCompon = ' + pNrCompon +
                                      ' Order by S045.NrSequen');

          while not lCdsAtrCon.Eof do
          begin
                DbgColuna.Columns.Items[pNrTabGri].BrConfigurar.Add(
                          lCdsAtrCon.FieldByName('NmAtrDes').AsString + ';' +
                          lCdsAtrCon.FieldByName('NmAtrOri').AsString + ';S;N;');
                lCdsAtrCon.Next;
          end;
      finally
          FreeAndNil(lCdsAtrCon);
      end;
end;


procedure TCad0003.DesabilitarCamposIgualdadeSubFormulario;
var lNrIguala : Integer;
    lNrColuna : Integer;
begin
      for lNrIguala := 0 to gStlWheSub.Count -1 do
      begin
            BrvString.Split(gStlWheSub.Strings[lNrIguala], '=');

            for lNrColuna := 0 to gNrCompon do
            begin
                  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  // =-=-=-=-=-=-=  Movendo valor default -=-=-=-=-=-=-=-=-=-=-=
                  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  if (Trim(gDsCompon[lNrColuna].NmColuna) <> '') and
                     (Trim(gDsCompon[lNrColuna].NmColuna) = BrvString.BrSplitLista[0]) then
                  begin
                        gDsCompon[lNrColuna].SnSubFor := True;
                  end;
            end;
      end;
end;

procedure TCad0003.MontarSubFormularios(pNrForDin : Integer);
var lDsSql   : String;
    lMnuItem : TMenuItem;
begin
      PopSubFor.Items.Clear;

      lDsSql := 'Select S031.*, S014F.NmTabela as NmTabDin, ' +
                'S014S.NmTabela as NmTabSub, S014S.DsForDin ' +
                'From   S031 S031 '     +
                'Left Join S014 S014F on (S014F.NrForDin = S031.NrForDin) ' +
                'Left Join S014 S014S on (S014S.NrForDin = S031.NrSubFor) ' +
                'Where  S031.NrForDin = ' + IntToStr(pNrForDin);

      QpSubFor.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(lDsSql);

      if not QpSubFor.Eof then
      begin
            QpSubFor.IndexDefs.Add('Key1', 'NrSubFor', []);
            QpSubFor.IndexName := 'Key1';

            SbtSubCad.Visible := True;

            while not QpSubFor.Eof do
            begin
                 lMnuItem         := TMenuItem.Create(Self);
                 lMnuItem.Caption := QpSubFor.FieldByName('DsForDin').AsString;
                 lMnuItem.Name    := 'SubFor_' + QpSubFor.FieldByName('NrSubFor').AsString;
                 lMnuItem.Tag     := QpSubFor.FieldByName('NrSubFor').AsInteger;
                 lMnuItem.OnClick := SubFormularioClick;
                 PopSubFor.Items.Add(lMnuItem);

                 QpSubFor.Next;
            end;
      end;
end;

procedure TCad0003.SubFormularioClick(Sender: TObject);
begin
      if SbtGravar.Enabled then
      begin
            if MessageDlg('Os dados da tabela principal devem ser salvos ' +
                          'antes desta operação.' + #13#13 +
                          'Deseja salvá-los agora?', mtConfirmation,
                          [MbYes, MbNo], 0) = IdYes then
            begin
                  SbtGravarClick(SbtGravar);
                  ChamarSubFormulario(Sender);
            end;
      end else
      begin
            ChamarSubFormulario(Sender);
      end;
end;

procedure TCad0003.ChamarSubFormulario(Sender : TObject);
var lCad0003     : TCad0003;
    lCdsChaEst   : TClientDataSet;
    lStlChaEst   : TStrings;
    lStlChaPri   : TStrings;
    lDsIguala    : String;
    lNrChave     : Integer;
begin
      if not QpSubFor.FindKey([(Sender as TMenuItem).Tag]) then
      begin
            raise Exception.Create('Não foi possível carregar o sub-formulario.' +#13#13 +
                                   'Chave de refência não encontrada.');
      end;

      try
          lStlChaEst := TStringList.Create;
          lStlChaPri := TStringList.Create;
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-=-=-=-=-=- Encontrando atributos estrangeiros para referencia
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          try
              lCdsChaEst := TClientDataset.Create(Self);
              lCdsChaEst.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                  'Select S012.NmChaEst ' +
                  'From S012 S012 ' +
                  'Where S012.NmTabela = ' + #39 +
                              QpSubFor.FieldByName('NmTabSub').AsString + #39 +
                  ' and  S012.NmTabEst = ' + #39 +
                              QpSubFor.FieldByName('NmTabDin').AsString + #39);

              if lCdsChaEst.Eof then
              begin
                    raise Exception.Create('Não foi possível carregar o sub-formulario.' +
                                           #13#13 +
                                          'Chave de refência não encontrada.');

              end;

              DmSrvApl.BrvDicionario.AtributoEstrangeiro(
                       lCdsChaEst.FieldByName('NmChaEst').AsString, lStlChaEst);

          finally
              FreeAndNil(lCdsChaEst);
          end;
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-=-=-=-=-=- Encontrando atributos chave primária para referência
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          DmSrvApl.BrvDicionario.ChavePrimaria(
                                 QpSubFor.FieldByName('NmTabDin').AsString, lStlChaPri);
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          try
              lDsIguala := '';
              for lNrChave := 0 to lStlChaPri.Count -1 do
              begin
                    lDsIguala := lDsIguala +
                            lStlChaEst.Strings[lNrChave] + '=' +
                            QCadastro.FieldByName(lStlChaPri.Strings[lNrChave]).AsString +
                            '@';
              end;
          except
              on E: Exception do
                    begin
                          raise Exception.create(
                                'Não foi possível carrar o sub-formulário.' +
                                #13#13 +
                                'Quantidade de chaves primárias não é igual a ' +
                                                        'quantidade chaves estrangeiras' +
                                #13#13 + E.Message);
                    end;
          end;

          try
              lCad0003  := TCad0003.Create(Self);
              lCad0003.FormStyle := fsNormal;
              lCad0003.Visible   := False;
              lCad0003.MontarFormularioCadastro((Sender as TMenuItem).Tag, lDsIguala);
              lCad0003.ShowModal;
          finally
              FreeAndNil(lCad0003);
          end;
      finally
          FreeAndNil(lStlChaEst);
          FreeAndNil(lStlChaPri);
      end;
end;

procedure TCad0003.AtualizarEditConsulta(pNrCompon : Integer; pDsConfig : String);
var lDsEdit : TBrvDbEdit;
    lNmEdit : String;
begin
      lNmEdit := '_' + FormatFloat('0000', pNrCompon);
      lDsEdit := TBrvDbEdit(FindComponent(lNmEdit));

      if lDsEdit <> nil then
      begin
            lDsEdit.BrConfigurar.Text := pDsConfig;
      end;
end;

function TCad0003.CamposRetornoConsulta(pNrForDin : Integer; pNrCompon : String) : String;
var lCdsAtrCon   : TClientDataSet;
begin
      lCdsAtrCon := TClientDataSet.Create(Self);
      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-= Obtendo informações do form dinamico
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          lCdsAtrCon.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                      ' Select S026.NmAtribu, S026.DsSepAnt ' +
                                      ' From   S026 S026 ' +
                                      ' Where  S026.NrForDin = ' + IntToStr(pNrForDin) +
                                      ' and    S026.NrCompon = ' + pNrCompon +
                                      ' Order by S026.NrOrdCon');
          Result := '';

          while not lCdsAtrCon.Eof do
          begin
                Result := Result + lCdsAtrCon.FieldByName('NmAtribu').AsString +
                                   '@' +
                                   lCdsAtrCon.FieldByName('DsSepAnt').AsString +
                                   '#';
                lCdsAtrCon.Next;
          end;
      finally
          FreeAndNil(lCdsAtrCon);
      end;
end;

function TCad0003.ColunasPesquisaChave(pNmChave : String) : String;
var lCdsChaEst   : TClientDataSet;
begin
      lCdsChaEst := TClientDataSet.Create(Self);
      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-= Obtendo informações do form dinamico
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          lCdsChaEst.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                     ' Select S013.NmAtribu, S011.NmAtribu as NmAtrEst ' +
                     ' From   S013 S013, S011 S011 ' +
                     ' Where  S013.NmChaEst = ' + #39 + pNmChave + #39 + ' and ' +
                     '      (S011.NmTabela = (Select S012.NmTabEst ' +
                     '                        From S012 S012 ' +
                     '                        Where S012.NmChaEst = S013.NmChaEst) and ' +
                     '       S011.NrSeqKey = S013.NrSeqKey)' +
                     ' Order By S013.NrSeqKey');
          Result := '';

          while not lCdsChaEst.Eof do
          begin
                Result := Result + lCdsChaEst.FieldByName('NmAtribu').AsString +
                                   ';' +
                                   lCdsChaEst.FieldByName('NmAtrEst').AsString +
                                   ';S;S;' + #13;
                lCdsChaEst.Next;
          end;
      finally
          FreeAndNil(lCdsChaEst);
      end;
end;

function TCad0003.ApelidoColunaConsulta : Char;
var lNoColuna : Integer;
begin
      lNoColuna := 0;
      Result    := ' ';

      while (Result = ' ') and (lNoColuna < Length(gDsCompon)) do
      begin
            if (gDsCompon[lNoColuna].NmChaCon =
                                           QpComDin.FieldByName('NmChaCon').AsString) then
            begin
                  Result := gDsCompon[lNoColuna].DsAlias;
            end;

            inc(lNoColuna);
      end;
end;

procedure TCad0003.CarregarOrdenacaoInicial;
var lConexao   : TSDmSisClient;
    lDsResult  : String;
    lCdsOrdIni : TClientDataSet;
begin
      lConexao   := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);
      lCdsOrdIni := TClientDataSet.Create(Self);

      try
          lCdsOrdIni.Data := lConexao.OrdemInicialFormDinamico(
                                      DmSrvApl.BrvDicionario.Credencial,
                                      lDsResult, gNmTabela);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          gDsOrdIni := '';

          while not lCdsOrdIni.Eof do
          begin
                if lCdsOrdIni.FieldByName('NmTabela').AsString <> '' then
                begin
                      if gDsOrdIni <> '' then
                      begin
                            gDsOrdIni := gDsOrdIni + ';';
                      end;

                      gDsOrdIni := gDsOrdIni + lCdsOrdIni.FieldByName('NmAtribu').AsString;
                end;
                lCdsOrdIni.Next;
          end;
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCad0003.AtribuirProviderDeCadastro(pNrForDin : Integer);
var lConexao     : TSDmSisClient;
    lDsResult    : String;
    lNmProvid    : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-= Criarndo Provider no servidor de aplicação
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          lConexao.AtribuirProviderFormDinamico(
                                   DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                   lNmProvid, gNmTabela, pNrForDin);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
          QCadastro.ProviderName := lNmProvid;
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCad0003.SbtAjudaClick(Sender: TObject);
var lDsCaptio : String;
    lDsHelp   : String;
begin
      Cai0007 := TCai0007.Create(Self);

      try
          lDsCaptio := Caption;
          Delete(lDsCaptio, Pos('&', lDsCaptio), 1);
          Delete(lDsCaptio, 1, Pos('-', lDsCaptio));
          Delete(lDsCaptio, 1, Pos('-', lDsCaptio));
          lDsCaptio :=
                      '<div align="center"><h1><font color="#CC0000"><strong>' +
                      lDsCaptio + '</strong></font></h1></div>';

          lDsHelp :=
                     '<body>' + lDsCaptio + '<p>' + gDsHelp + '<p>' +
                     '<div align="center"><a href="JavaScript:print()">' +
                     'Imprmir</a></div></body>';

          Cai0007.MostrarPagina(lDsHelp);
          Cai0007.ShowModal;
      finally
          FreeAndNil(Cai0007);
      end;
end;

procedure TCad0003.SbtAnteriClick(Sender: TObject);
begin
      QCadastro.Prior
end;

procedure TCad0003.SbtCancelClick(Sender: TObject);
begin
      QCadastro.Cancel;
end;

procedure TCad0003.SbtDeleteClick(Sender: TObject);
begin
      QCadastro.Delete;
end;

procedure TCad0003.SbtDuplicClick(Sender: TObject);
type TValor = record
       NmAtribu : WideString;
       VrAtribu : WideString;
     end;
var  lVrAnteri : array of TValor;
     lNrField  : Integer;
begin
      SetLength(lVrAnteri, gNrCompon + 1);

      for lNrField := 0 to gNrCompon do
      begin
            if (gDsCompon[lNrField].NmColuna <> '') and
               (not gDsCompon[lNrField].SnKey) then
            begin
                  lVrAnteri[lNrField].NmAtribu := gDsCompon[lNrField].NmColuna;
                  lVrAnteri[lNrField].VrAtribu :=
                             QCadastro.FieldByName(gDsCompon[lNrField].NmColuna).AsString;
            end else
            begin
                  lVrAnteri[lNrField].NmAtribu := 'VAZIO';
            end;
      end;

      SbtInsertClick(SbtInsert);

      for lNrField := 0 to gNrCompon do
      begin
            if  lVrAnteri[lNrField].NmAtribu <> 'VAZIO' then
            begin
                  QCadastro.FieldByName(lVrAnteri[lNrField].NmAtribu).AsString :=
                                                             lVrAnteri[lNrField].VrAtribu;
            end;
      end;
end;

procedure TCad0003.SbtGradeClick(Sender: TObject);
begin
      if PgcPrinci.ActivePage = TbsPrinci then
      begin
            PgcPrinci.ActivePage := TbsGrade;
      end else
      begin
            PgcPrinci.ActivePage := TbsPrinci;
      end;
end;

procedure TCad0003.SbtGravarClick(Sender: TObject);
begin
      QCadastro.Post;
end;

procedure TCad0003.SbtImportClick(Sender: TObject);
begin
      BrvImport.ImportTable(gNmTabela);
end;

procedure TCad0003.SbtImprimClick(Sender: TObject);
begin
      DlgRelato.Execute(QCadastro,
                        DmSrvApl.BrvDicionario,
                        Caption,
                        QCadastro.CommandText,
                        gDsOrder,
                        Self,
                        gNmTabela);
end;

procedure TCad0003.SbtInsertClick(Sender: TObject);
begin
      if not QCadastro.Active then
      begin
            QCadastro.Close;
            QCadastro.CommandText := gDsSelect + gDsWheNul;
            QCadastro.Open;
      end;

      QCadastro.Append;

      SetarFocoPrimeiroComponente;
end;

procedure TCad0003.SbtLimparClick(Sender: TObject);
begin
      if gSnLer = 'N' then
      begin
            raise Exception.Create('Este formulário não permite visualização.');
      end;

      QCadastro.Tag := 1;
      QCadastro.Close;
      QCadastro.CommandText := gDsSelect + gDsWheNul;
      QCadastro.Open;
      QCadastro.Append;

      SetarFocoPrimeiroComponente;

      SbtLimpar.Enabled := False;
      SbtLocali.Enabled := True;
      SbtLocAva.Enabled := False;
      SbtPosici.Enabled := False;

      SbtDuplic.Enabled := False;
      SbtInsert.Enabled := False;
      SbtCancel.Enabled := True;
      SbtGravar.Enabled := False;
      SbtDelete.Enabled := False;

      SbtPrimei.Enabled := False;
      SbtAnteri.Enabled := False;
      SbtProxim.Enabled := False;
      SbtUltimo.Enabled := False;

      SbtGrade.Enabled  := True;
      SbtOrdem.Enabled  := False;
      SbtImprim.Enabled := False;

      SbtExport.Enabled := False;
      SbtImport.Enabled := False;
end;

procedure TCad0003.SbtLocaliClick(Sender: TObject);
begin
      if NavBarra.CanFocus then
      begin
            NavBarra.SetFocus;
      end;

      gDsWheAva  := '';

      gDsWhere := MontarWhereConsulta(QCadastro, DbgColuna, 'A');

      AbrirCadastroPrincipal(gDsWhere);
end;

procedure TCad0003.AbrirCadastroPrincipal(pDsWhere : String);
begin
      QCadastro.Tag := 0;
      QCadastro.Close;

      if pDsWhere <> '' then
      begin
            QCadastro.CommandText := gDsSelect + ' where ' + pDsWhere;
      end else
      begin
            QCadastro.CommandText := gDsSelect;
      end;

      QCadastro.Open;

      SbtLimpar.Enabled := True;
      SbtLocali.Enabled := False;
      SbtLocAva.Enabled := True;
      SbtPosici.Enabled := True;

      SbtDuplic.Enabled := True;
      SbtInsert.Enabled := True;
      SbtCancel.Enabled := False;
      SbtGravar.Enabled := False;

      SbtPrimei.Enabled := False;
      SbtAnteri.Enabled := False;
      SbtProxim.Enabled := False;
      SbtUltimo.Enabled := False;

      SbtGrade.Enabled  := True;
      SbtOrdem.Enabled  := True;
      SbtImprim.Enabled := True;

      SbtExport.Enabled := True;
      SbtImport.Enabled := True;

      QCadastroAfterScroll(nil);
end;

procedure TCad0003.SbtLocAvaClick(Sender: TObject);
begin
      if BrvFiltrar.Execute(TClientDataSet(QCadastro)) then
      begin
            gDsWhere := BrvFiltrar.WhereFieldName;
            AbrirCadastroPrincipal(gDsWhere);
      end;
end;

procedure TCad0003.SbtOrdemClick(Sender: TObject);
var lDsOrder  : String;
begin
       if DlgOrdenar.Execute(TClientDataSet(DCadastro.DataSet), gDsOrdIni) then
       begin
             lDsOrder  := DlgOrdenar.OrderFieldName;
             gDsOrder  := DlgOrdenar.OrderDisplayName;
             gDsOrdIni := StringReplace(lDsOrder, ',', ';', [rfReplaceAll]);

             SetarOrdenacaoInicial;
       end;
end;

procedure TCad0003.SetarOrdenacaoInicial;
var lNrIndex  : Integer;
begin
      if gDsOrdIni <> '' then
      begin
            lNrIndex := TClientDataSet(DCadastro.DataSet).IndexDefs.IndexOf('IndexOrdGri');

            if lNrIndex >= 0 then
            begin
                  TClientDataSet(DCadastro.DataSet).DeleteIndex('IndexOrdGri');
                  TClientDataSet(DCadastro.DataSet).IndexDefs.Delete(lNrIndex);
            end;

            TClientDataSet(DCadastro.DataSet).IndexDefs.Add(
                                           'IndexOrdGri', gDsOrdIni, [ixCaseInsensitive]);
            TClientDataSet(DCadastro.DataSet).IndexName := 'IndexOrdGri';
            TClientDataSet(DCadastro.DataSet).First;
      end;
end;

procedure TCad0003.SbtPosiciClick(Sender: TObject);
begin
      BrvLocalizar.Execute(TClientDataSet(QCadastro));
end;

procedure TCad0003.SbtPrimeiClick(Sender: TObject);
begin
      QCadastro.First;
end;

procedure TCad0003.SbtProximClick(Sender: TObject);
begin
      QCadastro.Next;
end;

procedure TCad0003.SbtUltimoClick(Sender: TObject);
begin
      QCadastro.Last;
end;

function TCad0003.MontarWhereConsulta(pDsDatSet : TClientDataSet;
                                      pDsDbGrid : TBrvDbGrid; pDsAlias  : String) : String;
var lNoField  : Integer;
    lVlAuxili : String;
    lNmField  : AnsiString;
begin
      DbgColuna.DataSource := DCadastro;

      Result := '';
      for lNoField := 0 to pDsDbGrid.Columns.Count -1 do
      begin
            lNmField := pDsDbGrid.Columns[lNoField].FieldName;

            if (Pos('_', lNmField) = 0)                               and
               (not pDsDatSet.FieldByName(lNmField).IsNull)           and
               (Trim(pDsDatSet.FieldByName(lNmField).AsString) <> '') and
               ((not  pDsDbGrid.Columns[lNoField].ReadOnly))  or
                (CampoDeIgualdadeSubFormulario(lNmField))             then
            begin
                  if Result <> '' then
                  begin
                        case pDsDatSet.FieldByName(
                                        pDsDbGrid.Columns[lNoField].FieldName).DataType of
                             ftString, ftWideString :
                                      Result := Result + ' and ' +
                                        pDsAlias + '.' +
                                        pDsDbGrid.Columns[lNoField].FieldName +
                                        ' like "%' +
                                        pDsDatSet.FieldByName(
                                         pDsDbGrid.Columns[lNoField].FieldName).AsString +
                                        '%"';
                             ftDateTime : Result := Result + ' and ' +
                                      pDsAlias + '.' +
                                      pDsDbGrid.Columns[lNoField].FieldName +
                                      ' = "' +
                                      FormatDateTime('mm/dd/yyyy',
                                      pDsDatSet.FieldByName(
                                      pDsDbGrid.Columns[lNoField].FieldName).AsDateTime) +
                                      '"';
                             FtFloat : begin
                                             lVlAuxili := StringReplace(
                                             pDsDatSet.FieldByName(
                                                   pDsDbGrid.Columns[lNoField].
                                                                      FieldName).AsString,
                                                       '.', '', [rfReplaceAll]);

                                             lVlAuxili := StringReplace(
                                                          lVlAuxili, ',', '.',
                                                          [rfReplaceAll]);

                                             Result := Result + ' and ' +
                                                         pDsAlias + '.' +
                                                         pDsDbGrid.Columns
                                                                    [lNoField].FieldName +
                                                          ' = ' + lVlAuxili;
                                       end;
                             else Result := Result + ' and ' +
                                         pDsAlias + '.' +
                                         pDsDbGrid.Columns[lNoField].FieldName +
                                         ' = "' +
                                         pDsDatSet.FieldByName(
                                         pDsDbGrid.Columns[lNoField].FieldName).AsString +
                                         '"';
                        end;
                  end else
                  begin
                        case pDsDatSet.FieldByName(
                                        pDsDbGrid.Columns[lNoField].FieldName).DataType of
                             ftString, ftWideString :
                                     Result := pDsAlias + '.' +
                                        pDsDbGrid.Columns[lNoField].FieldName +
                                        ' like "%' +
                                        pDsDatSet.FieldByName(
                                        pDsDbGrid.Columns[lNoField].FieldName).AsString  +
                                        '%"';
                             ftDateTime : Result := pDsAlias + '.' +
                                        pDsDbGrid.Columns[lNoField].FieldName +
                                        ' = "' +
                                        FormatDateTime('mm/dd/yyyy',
                                        pDsDatSet.FieldByName(
                                        pDsDbGrid.Columns[lNoField].FieldName).AsDateTime)+
                                        '"';
                             FtFloat : begin
                                             lVlAuxili := StringReplace(
                                             pDsDatSet.FieldByName(
                                             pDsDbGrid.Columns[lNoField].FieldName).
                                                                                AsString,
                                             '.', '', [rfReplaceAll]);

                                             lVlAuxili := StringReplace(
                                                          lVlAuxili, ',', '.',
                                                          [rfReplaceAll]);

                                             Result := pDsAlias + '.' +
                                                         pDsDbGrid.
                                                         Columns[lNoField].FieldName +
                                                         ' = ' + lVlAuxili;
                                       end;
                             else Result := pDsAlias + '.' +
                                        pDsDbGrid.Columns[lNoField].FieldName +
                                        ' = "' +
                                        pDsDatSet.FieldByName(
                                         pDsDbGrid.Columns[lNoField].FieldName).AsString +
                                         '"';
                        end;
                  end;
            end;
      end;
end;

function TCad0003.CampoDeIgualdadeSubFormulario(pNmCampo : String) : Boolean;
var lNrIguala : Integer;
    lNrColuna : Integer;
begin
      Result := False;

      for lNrIguala := 0 to gStlWheSub.Count -1 do
      begin
            BrvString.Split(gStlWheSub.Strings[lNrIguala], '=');

            if pNmCampo = BrvString.BrSplitLista[0] then
            begin
                  Result := True;
            end;
      end;
end;

procedure TCad0003.SetarFocoPrimeiroComponente;
begin
      if PgcPrinci.ActivePage = TbsPrinci then
      begin
            SbxFundo.SetFocus;
            Perform(Wm_NextDlgCtl,0,0);
      end else
      begin
            DbgColuna.SetFocus;
      end;
end;

procedure TCad0003.MontarWhereNulo;
var lStlChave : TStrings;
    lNrChave  : Integer;
begin
      try
          lStlChave := TStringList.Create;

          DmSrvApl.BrvDicionario.ChavePrimaria(gNmTabela, lStlChave);

          for lNrChave := 0 to lStlChave.Count -1 do
          begin
                if lNrChave = 0 then
                begin
                      gDsWheNul := ' Where A.' +
                                   lStlChave.Strings[lNrChave] + ' is null ';
                end else
                begin
                      gDsWheNul := gDsWheNul + ' and A.' +
                                   lStlChave.Strings[lNrChave] + ' is null ';
                end;
          end;
      finally
          lStlChave.Destroy;
      end;
end;

procedure TCad0003.QCadastroAfterCancel(DataSet: TDataSet);
begin
      QCadastro.Tag := 0;
      if SbtLocali.Enabled then
      begin
            QCadastro.Close;
      end;

      SbtLimpar.Enabled := True;
      SbtLocali.Enabled := False;
      SbtLocAva.Enabled := True;
      SbtPosici.Enabled := False;
      SbtInsert.Enabled := True;
      SbtDelete.Enabled := not QCadastro.IsEmpty;
      SbtGravar.Enabled := False;
      SbtDuplic.Enabled := not QCadastro.IsEmpty;
      SbtCancel.Enabled := False;
      SbtGrade.Enabled  := True;
      SbtOrdem.Enabled  := not QCadastro.IsEmpty;
      SbtImprim.Enabled := not QCadastro.IsEmpty;
      SbtExport.Enabled := not QCadastro.IsEmpty;
      SbtImport.Enabled := not QCadastro.IsEmpty;

      QCadastroAfterScroll(nil);
end;

procedure TCad0003.QCadastroAfterDelete(DataSet: TDataSet);
begin
      try
          QCadastro.BrApplyUpdates;

          SbtLimpar.Enabled := True;
          SbtLocali.Enabled := False;
          SbtLocAva.Enabled := True;
          SbtPosici.Enabled := False;
          SbtInsert.Enabled := True;
          SbtDelete.Enabled := not QCadastro.IsEmpty;
          SbtGravar.Enabled := False;
          SbtDuplic.Enabled := not QCadastro.IsEmpty;
          SbtCancel.Enabled := False;
          SbtGrade.Enabled  := True;
          SbtOrdem.Enabled  := not QCadastro.IsEmpty;
          SbtImprim.Enabled := not QCadastro.IsEmpty;
          SbtExport.Enabled := not QCadastro.IsEmpty;
          SbtImport.Enabled := not QCadastro.IsEmpty;
      except
          raise;
      end;
end;

procedure TCad0003.QCadastroAfterEdit(DataSet: TDataSet);
var lNoColuna : Integer;
    lDsComAux : TWinControl;
begin
      SbtLimpar.Enabled := False;
      SbtLocali.Enabled := False;
      SbtLocAva.Enabled := False;
      SbtPosici.Enabled := False;
      SbtInsert.Enabled := False;
      SbtDelete.Enabled := False;
      SbtGravar.Enabled := True;
      SbtDuplic.Enabled := False;
      SbtCancel.Enabled := True;
      SbtGrade.Enabled  := True;
      SbtOrdem.Enabled  := False;
      SbtImprim.Enabled := False;
      SbtExport.Enabled := False;
      SbtImport.Enabled := False;

      if QCadastro.Tag = 0 then
      begin
            for lNoColuna := 0 to  gNrCompon do
            begin
                  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  // =-=-=-=-=-=-=  Movendo valor default -=-=-=-=-=-=-=-=-=-=-=
                  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  if (Trim(gDsCompon[lNoColuna].NmColuna) <> '') and
                     (Trim(gDsCompon[lNoColuna].VrDefaul) <> '') and
                     (  (gDsCompon[lNoColuna].TpDefaul = 'AE') or
                        (gDsCompon[lNoColuna].TpDefaul = 'AIE')) then
                  begin
                        QCadastro.FieldByName(
                                  gDsCompon[lNoColuna].NmColuna).AsString :=
                                    ValorDefault(gDsCompon[lNoColuna].VrDefaul);

                        lDsComAux := TWinControl(FindComponent('_' +
                                               FormatFloat('0000', lNoColuna)));

                        if (lDsComAux <> nil) and (lDsComAux is TBrvDbEdit) then
                        begin
                              (lDsComAux as TBrvDbEdit).BrValidarEntrada;
                        end;
                  end;
            end;
      end;
end;

procedure TCad0003.QCadastroAfterInsert(DataSet: TDataSet);
var lNoColuna : Integer;
    lDsComAux : TWinControl;

    lNmColuna : String;
    lVrColuna : String;
begin
      for lNoColuna := 0 to gStlWheSub.Count -1 do
      begin
            BrvString.Split(gStlWheSub.Strings[lNoColuna], '=');

            QCadastro.FieldByName(BrvString.BrSplitLista[0]).AsString :=
                                                                BrvString.BrSplitLista[1];
      end;

      if QCadastro.Tag = 0 then
      begin
            SbtLimpar.Enabled := False;
            SbtLocali.Enabled := False;
            SbtLocAva.Enabled := False;
            SbtPosici.Enabled := False;
            SbtInsert.Enabled := False;
            SbtDelete.Enabled := False;
            SbtGravar.Enabled := True;
            SbtDuplic.Enabled := False;
            SbtCancel.Enabled := True;
            SbtGrade.Enabled  := True;
            SbtOrdem.Enabled  := False;
            SbtImprim.Enabled := False;
            SbtExport.Enabled := False;
            SbtImport.Enabled := False;

            for lNoColuna := 0 to gNrCompon do
            begin
                  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  // =-=-=-=-=-=-=  Movendo valor default -=-=-=-=-=-=-=-=-=-=-=
                  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  if (Trim(gDsCompon[lNoColuna].NmColuna) <> '') and
                     (Trim(gDsCompon[lNoColuna].VrDefaul) <> '') and
                     ( (gDsCompon[lNoColuna].TpDefaul = 'AI') or
                       (gDsCompon[lNoColuna].TpDefaul = 'AIE')) then
                  begin
                        QCadastro.FieldByName(
                                  gDsCompon[lNoColuna].NmColuna).AsString :=
                                    ValorDefault(gDsCompon[lNoColuna].VrDefaul);

                        lDsComAux := TWinControl(FindComponent('_' +
                                               FormatFloat('0000', lNoColuna)));

                        if (lDsComAux <> nil) and (lDsComAux is TBrvDbEdit) then
                        begin
                              (lDsComAux as TBrvDbEdit).BrValidarEntrada;
                        end;
                  end;
            end;
      end else
      begin
            if  SbtLocali.Enabled then
            begin
                  abort;
            end;
      end;
end;

procedure TCad0003.QCadastroAfterOpen(DataSet: TDataSet);
var lNoColuna : Integer;
    lDsComAux : TWinControl;
begin
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-= Habilitando objetos em modo de pesquisa
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      for lNoColuna := 0 to gNrCompon do
      begin
            if Trim(gDsCompon[lNoColuna].NmColuna) <> '' then
            begin
                  lDsComAux := TWinControl(FindComponent('_' +
                                                         FormatFloat('0000', lNoColuna)));

                  if ObjetoDeCampoEditavel(lDsComAux) then
                  begin
                        if gDsCompon[lNoColuna].SnSubFor then
                        begin
                              lDsComAux.Enabled := False;
                        end else
                        begin
                              if QCadastro.Tag = 1 then // em modo pesquisa
                              begin
                                    lDsComAux.Enabled := True;
                              end else
                              begin
                                    lDsComAux.Enabled := gDsCompon[lNoColuna].SnEnable;
                              end;
                        end;

                        HabilitarColunaEdicaoGrade(gDsCompon[lNoColuna].NmColuna,
                                                   not lDsComAux.Enabled);
                  end;
            end;
      end;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      SetarOrdenacaoInicial;
end;

procedure TCad0003.HabilitarColunaEdicaoGrade(pNmColuna : String; pSnEnable : Boolean);
var lNrColuna : Integer;
begin
      for lNrColuna := 0 to DbgColuna.Columns.Count -1 do
      begin
            if DbgColuna.Columns[lNrColuna].FieldName = pNmColuna then
            begin
                  DbgColuna.Columns[lNrColuna].ReadOnly := pSnEnable;
            end;
      end;
end;

procedure TCad0003.QCadastroAfterPost(DataSet: TDataSet);
begin
      if SbtGravar.Enabled then
      begin
            try
                QCadastro.ApplyUpdates(0);

                SbtLimpar.Enabled := True;
                SbtLocali.Enabled := False;
                SbtLocAva.Enabled := True;
                SbtPosici.Enabled := False;
                SbtInsert.Enabled := True;
                SbtDelete.Enabled := True;
                SbtGravar.Enabled := False;
                SbtDuplic.Enabled := not QCadastro.IsEmpty;
                SbtCancel.Enabled := False;
                SbtGrade.Enabled  := True;
                SbtOrdem.Enabled  := not QCadastro.IsEmpty;
                SbtImprim.Enabled := not QCadastro.IsEmpty;
                SbtExport.Enabled := not QCadastro.IsEmpty;
                SbtImport.Enabled := not QCadastro.IsEmpty;

                QCadastroAfterScroll(nil);
            except
                raise;
            end;
      end;
end;

function TCad0003.ObjetoDeCampoEditavel(pDsCompon : TWinControl) : Boolean;
begin
      if (pDsCompon is TDbEdit)             or
         (pDsCompon is TDbMemo)             or
         (pDsCompon is TDbComboBox)         or
         (pDsCompon is TDbCheckBox)         or
         (pDsCompon is TDbRadioGroup)       then
      begin
            Result := True;
      end else
      begin
            Result := False;
      end;
end;

procedure TCad0003.PopLogPopup(Sender: TObject);
begin
      if (not DmSrvApl.BrvDicionario.UserGroupAdm) or
         (QCadastro.Tag <> 0) and
         (not QCadastro.Eof)  then
      begin
            Abort;
      end;
end;

procedure TCad0003.PopSubForPopup(Sender: TObject);
begin
      if QCadastro.Active then
      begin
            AtivarMenuSubFormularios(QCadastro.RecordCount > 0);
      end else
      begin
            AtivarMenuSubFormularios(False);
      end;
end;

procedure TCad0003.AtivarMenuSubFormularios(pSnEnable : Boolean);
var lNrMenu : Integer;
begin
      for lNrMenu := 0 to PopSubFor.Items.Count -1 do
      begin
            PopSubFor.Items[lNrMenu].Enabled := pSnEnable;
      end;
end;

function TCad0003.ValorDefault(pVrDefaul : String) : String;
begin
      Result := DmSrvApl.BrvDicionario.ValorDefault(pVrDefaul);
end;

procedure TCad0003.VerLog1Click(Sender: TObject);
begin
      VerLog(QCadastro, gNmTabela);
end;

procedure TCad0003.QCadastroAfterScroll(DataSet: TDataSet);
begin
      SbtPrimei.Enabled  := (not QCadastro.Bof)   and
                            (not (QCadastro.State in  [dsEdit, dsInsert]));
      SbtAnteri.Enabled  := SbtPrimei.Enabled;

      SbtProxim.Enabled  := (not QCadastro.Eof)   and
                            (not (QCadastro.State in  [dsEdit, dsInsert]));
      SbtUltimo.Enabled  := SbtProxim.Enabled;


      SbtDelete.Enabled  := (not QCadastro.IsEmpty) and
                            (not (QCadastro.State in  [dsEdit, dsInsert]));
      SbtDuplic.Enabled  := SbtDelete.Enabled;
      SbtExport.Enabled  := SbtDelete.Enabled;
      SbtImport.Enabled  := SbtDelete.Enabled;
end;

procedure TCad0003.QCadastroBeforeDelete(DataSet: TDataSet);
begin
      if gSnDelete = 'N' then
      begin
            raise Exception.Create('Este formulário não permite exclusão.');
      end;

      if MessageDlg('Confirma exclusão do registro?', MtConfirmation,
                                                            [MbYes, MbNo], 0) = IdNo then
      begin
            Abort;
      end;
end;

procedure TCad0003.QCadastroBeforeInsert(DataSet: TDataSet);
begin
      if (QCadastro.Tag = 0) and (gSnInclui = 'N') then
      begin
            raise Exception.Create('Este formulário não permite inclusão.');
      end;
end;

procedure TCad0003.QCadastroBeforePost(DataSet: TDataSet);
var lNoField  : Integer;
    lNmValida : String;
    lDsValida : String;
    lDsMensag : String;
    lNoColuna : Integer;
    lDsComAux : TWinControl;
    lSnValDef : Boolean;
begin
      if QCadastro.Tag = 0 then
      begin
            try
                if SbxFundo.Visible then
                begin
                      PnlFundo.SetFocus;
                end;
            except

            end;

         // =-=-=-=-=-=-=-=-=-=-=-=
         // retirando espaços da direita e esquerda
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            for lNoField := 0 to QCadastro.FieldCount -1 do
            begin
                  if (QCadastro.Fields[lNoField].DataType = FtString) and
                     (QCadastro.Fields[lNoField].ReadOnly = False   ) then
                  begin
                        QCadastro.Fields[lNoField].AsString :=
                                                Trim(QCadastro.Fields[lNoField].AsString);
                  end;
            end;
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=  Movendo valor default -=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

            for lNoColuna := 0 to gNrCompon do
            begin
                  if  (Trim(gDsCompon[lNoColuna].NmColuna) <> '') and
                      (Trim(gDsCompon[lNoColuna].VrDefaul) <> '') then
                  begin
                        lSnValDef := False;

                        if gDsCompon[lNoColuna].TpDefaul = 'BPIE' then
                        begin
                              lSnValDef := True;
                        end
                        else if (gDsCompon[lNoColuna].TpDefaul = 'BPI') and
                                 (QCadastro.State = dsInsert        ) then
                             begin
                                   lSnValDef := True;
                             end
                        else if (gDsCompon[lNoColuna].TpDefaul = 'BPE') and
                                (QCadastro.State = dsEdit           ) then
                             begin
                                   lSnValDef := True;
                             end;

                        if lSnValDef then
                        begin
                              QCadastro.FieldByName(
                                        gDsCompon[lNoColuna].NmColuna).AsString :=
                                              ValorDefault(gDsCompon[lNoColuna].VrDefaul);

                              lDsComAux := TWinControl(FindComponent('_' +
                                               FormatFloat('0000', lNoColuna)));

                              if (lDsComAux <> nil) and (lDsComAux is TBrvDbEdit) then
                              begin
                                    (lDsComAux as TBrvDbEdit).BrValidarEntrada;
                              end;
                        end;
                  end;
            end;

         // =-=-=-=-=-=-=-=-=-=-=-=
         // Verificando validações de preenchimento do campo
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            for lNoField := 0 to QCadastro.FieldCount -1 do
            begin
                  QCadastro.Fields[lNoField].AsString :=
                                                Trim(QCadastro.Fields[lNoField].AsString);

                  if QCadastro.Fields[lNoField].tag <> 0 then
                  begin
                        if ((not gDsCompon[QCadastro.Fields[lNoField].tag].SnNulo) and
                            (    QCadastro.Fields[lNoField].AsString = ''        ) and
                            (not gDsCompon[QCadastro.Fields[lNoField].tag].SnAutInc) )

                             or

                           ((not gDsCompon[QCadastro.Fields[lNoField].tag].SnNulo)   and
                            (    QCadastro.Fields[lNoField].AsString = ''        )   and
                            (    gDsCompon[QCadastro.Fields[lNoField].tag].SnKey) and
                            (not gDsCompon[QCadastro.Fields[lNoField].tag].SnAutInc)) then
                        begin
                              raise Exception.Create('Campo "' +
                                      gDsCompon[QCadastro.Fields[lNoField].tag].DsColuna +
                                                                  '" deve ser informado');
                        end;

                        if Length(gDsCompon[lNoField].ArValida) > 0 then
                        begin
                              VerificarRegraExpressao(QCadastro.Fields[lNoField].tag,
                                     QCadastro.Fields[lNoField].AsString,
                                     gDsCompon[QCadastro.Fields[lNoField].tag].DsColuna);
                        end;
                  end;
            end;
         // =-=-=-=-=-=-=-=-=-=-=-=
         // inserindo valor de auto-incremento
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if QCadastro.State in [DsInsert] then
            begin
                  for lNoField := 0 to QCadastro.FieldCount -1 do
                  begin
                        if QCadastro.Fields[lNoField].tag <> 0 then
                        begin
                              if ((QCadastro.Fields[lNoField].DataType = FtInteger) or
                                  (QCadastro.Fields[lNoField].DataType = ftFMTBcd)) and
                                 (gDsCompon[QCadastro.Fields[lNoField].tag].SnAutInc) then
                              begin
                                    QCadastro.Fields[lNoField].AsInteger :=
                                        DmSrvApl.BrvDicionario.ProxNumeroSequencial(
                                              gNmTabela,
                                              gDsCompon[
                                                QCadastro.Fields[lNoField].tag].NmColuna);
                              end;
                        end;
                  end;
            end;
      end else
      begin
            Abort;
      end;
end;

procedure TCad0003.VerificarRegraExpressao(pNrCompon : Integer; pDsValor : String;
                                           pDsColuna  : String);
var lNrValida : Integer;
    lDsMensag : String;
begin
      for lNrValida := 0 to Length(gDsCompon[pNrCompon].ArValida) -1 do
      begin
            lDsMensag := '';

            if gDsCompon[pNrCompon].ArValida[lNrValida].TpValida = 'E' then // Expressão Regular
            begin
                  if not TRegEx.IsMatch(pDsValor,
                                   gDsCompon[pNrCompon].ArValida[lNrValida].DsExpres) then
                  begin
                        lDsMensag := 'Campo "' + pDsColuna +
                                '" não satisfaz expressão regular ' +
                                gDsCompon[pNrCompon].ArValida[lNrValida].DsValida +
                                ' determinada para ele.';

                        if gDsCompon[pNrCompon].ArValida[lNrValida].DsMensag <> '' then
                        begin
                              lDsMensag := lDsMensag + #13#13 +
                                        gDsCompon[pNrCompon].ArValida[lNrValida].DsMensag;
                        end;
                  end;
            end
            else if gDsCompon[pNrCompon].ArValida[lNrValida].TpValida = 'C' then // CPF CNPJ
                 begin
                       if not BrvDigito.ValidaCPFCNPJ(pDsValor) then
                       begin
                             lDsMensag := 'Campo "' + pDsColuna +
                                     '" não satisfaz validação ' +
                                     gDsCompon[pNrCompon].ArValida[lNrValida].DsValida +
                                     ' determinada para ele.';

                             if gDsCompon[pNrCompon].ArValida[lNrValida].DsMensag <>
                                                                                   '' then
                             begin
                                   lDsMensag := lDsMensag + #13#13 +
                                        gDsCompon[pNrCompon].ArValida[lNrValida].DsMensag;
                             end;

                       end;
                 end
            ;

            if lDsMensag <> '' then
            begin
                  raise Exception.Create(lDsMensag);
            end;
      end;
end;

procedure TCad0003.QCadastroReconcileError(DataSet: TCustomClientDataSet;
  E: EReconcileError; UpdateKind: TUpdateKind; var Action: TReconcileAction);
begin
  inherited;
      Raise Exception.Create(E.Message);
end;

procedure TCad0003.KeyPress(Sender : TObject; var Key : Char);
begin
      if (Sender is TBrvDbEdit) then
      begin
            if ((Sender as TBrvDbEdit).Field is TBrWideStringField) then
            begin
                  case ((Sender as TBrvDbEdit).Field as TBrWideStringField).BrCharCase of
                       BrSfUpperCase : key := BrvString.BrUpCase(key);
                       BrSfLowerCase : key := BrvString.BrLwCase(key);
                  end;
            end;
      end else
      begin
            if (Sender is TDbMemo) then
            begin
                  case ((Sender as TDbMemo).Field as TBrMemoField).BrCharCase of
                       BrSfUpperCase : key := BrvString.BrUpCase(key);
                       BrSfLowerCase : key := BrvString.BrLwCase(key);
                  end;
            end;
      end;
end;

procedure TCad0003.CriarImagem(pNrCompon : Integer;     pDsAlias  : Char;
                               pNmColuna : String;      pNrComPai : Integer;
                               pVrLeft   : Integer;     pVrTop    : Integer;
                               pVrHeight : Integer;     pVrWidth  : Integer;
                               pSnEnable : Boolean;     pSnVisibl : Boolean;
                               pTpCompon : String;      pNrQueCon : Integer;
                               pSnAncAci : Boolean;     pSnAncAba : Boolean;
                               pSnAncEsq : Boolean;     pSnAncDir : Boolean);
var lDsImage : TDbImage;
begin
      gDsSelect := gDsSelect + pDsAlias + '.' + pNmColuna + ', ';

      lDsImage             := TDbImage.Create(Self);
      lDsImage.Tag         := pNrCompon;
      lDsImage.Name        := '_' + FormatFloat('0000', pNrCompon);
      lDsImage.Parent      := EncontrarComponentePai(pNrComPai, pNmColuna);
      lDsImage.Left        := pVrLeft;
      lDsImage.Top         := pVrTop;
      lDsImage.Height      := pVrheight;
      lDsImage.Width       := pVrWidth;
      lDsImage.DataField   := pNmColuna;
      lDsImage.DataSource  := DCadastro;
      lDsImage.Enabled     := pSnEnable;
      lDsImage.Visible     := pSnVisibl;
      lDsImage.Anchors     := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);
end;

procedure TCad0003.CriarCheckBox(pNrCompon : Integer;     pDsAlias  : Char;
                                 pNmColuna : String;      pNrComPai : Integer;
                                 pVrLeft   : Integer;     pVrTop    : Integer;
                                 pVrHeight : Integer;     pVrWidth  : Integer;
                                 pSnEnable : Boolean;     pSnVisibl : Boolean;
                                 pTpCompon : String;      pNrQueCon : Integer;
                                 pVrDomini : String;      pDsCaptio : String;
                                 pSnAncAci : Boolean;     pSnAncAba : Boolean;
                                 pSnAncEsq : Boolean;     pSnAncDir : Boolean);
var lDsCheBox : TDbCheckBox;
begin
      gDsSelect := gDsSelect + pDsAlias + '.' + pNmColuna  + ', ';

      lDsCheBox                  := TDbCheckBox.Create(Self);
      lDsCheBox.Tag              := pNrCompon;
      lDsCheBox.Name             := '_' + FormatFloat('0000', pNrCompon);
      lDsCheBox.Parent           := EncontrarComponentePai(pNrComPai, pNmColuna);
      lDsCheBox.Left             := pVrLeft;
      lDsCheBox.Top              := pVrTop;
      lDsCheBox.Height           := pVrheight;
      lDsCheBox.Width            := pVrWidth;
      lDsCheBox.DataField        := pNmColuna;
      lDsCheBox.DataSource       := DCadastro;
      lDsCheBox.Enabled          := pSnEnable;
      lDsCheBox.Visible          := pSnVisibl;
      lDsCheBox.Caption          := pDsCaptio;
      lDsCheBox.Anchors          := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      BrvString.Split(pVrDomini, ',');

      try
          lDsCheBox.ValueUnchecked := BrvString.BrSplitLista.Strings[0];
          lDsCheBox.ValueChecked   := BrvString.BrSplitLista.Strings[1];
      except
          raise Exception.Create('Domínios para o atributo não ' + pNmColuna +
                                                                       ' está definido.');
      end;
end;

procedure TCad0003.CriarRadioGroup(pNrCompon : Integer;     pDsAlias  : Char;
                                   pNmColuna : String;      pNrComPai : Integer;
                                   pVrLeft   : Integer;     pVrTop    : Integer;
                                   pVrHeight : Integer;     pVrWidth  : Integer;
                                   pSnEnable : Boolean;     pSnVisibl : Boolean;
                                   pTpCompon : String;      pNrQueCon : Integer;
                                   pDsDomini : String;      pVrDomini : String;
                                   pDsCaptio : String;
                                   pSnAncAci : Boolean;     pSnAncAba : Boolean;
                                   pSnAncEsq : Boolean;     pSnAncDir : Boolean);
var lDsRadGro : TDbRadioGroup;
begin
      gDsSelect := gDsSelect + pDsAlias + '.' + pNmColuna + ', ';

      lDsRadGro                  := TDbRadioGroup.Create(Self);
      lDsRadGro.Tag              := pNrCompon;
      lDsRadGro.Name             := '_' + FormatFloat('0000', pNrCompon);
      lDsRadGro.Parent           := EncontrarComponentePai(pNrComPai, pNmColuna);
      lDsRadGro.Left             := pVrLeft;
      lDsRadGro.Top              := pVrTop;
      lDsRadGro.Height           := pVrheight;
      lDsRadGro.Width            := pVrWidth;
      lDsRadGro.DataField        := pNmColuna;
      lDsRadGro.DataSource       := DCadastro;
      lDsRadGro.Enabled          := pSnEnable;
      lDsRadGro.Visible          := pSnVisibl;
      lDsRadGro.Caption          := pDsCaptio;

      lDsRadGro.Items.CommaText  := pDsDomini;
      lDsRadGro.Values.CommaText := pVrDomini;
      lDsRadGro.Anchors          := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);
end;

procedure TCad0003.CriarComboBox(pNrCompon : Integer;     pDsAlias  : Char;
                                 pNmColuna : String;      pNrComPai : Integer;
                                 pVrLeft   : Integer;     pVrTop    : Integer;
                                 pVrHeight : Integer;     pVrWidth  : Integer;
                                 pSnEnable : Boolean;     pSnVisibl : Boolean;
                                 pTpCompon : String;      pNrQueCon : Integer;
                                 pDsDomini : String;      pVrDomini : String;
                                 pSnAncAci : Boolean;     pSnAncAba : Boolean;
                                 pSnAncEsq : Boolean;     pSnAncDir : Boolean);
var lDsCombo : TBrvDBComboListBox;
begin
      gDsSelect := gDsSelect + pDsAlias + '.' + pNmColuna + ', ';

      lDsCombo                  := TBrvDBComboListBox.Create(Self);
      lDsCombo.Tag              := pNrCompon;
      lDsCombo.Name             := '_' + FormatFloat('0000', pNrCompon);
      lDsCombo.Parent           := EncontrarComponentePai(pNrComPai, pNmColuna);
      lDsCombo.Left             := pVrLeft;
      lDsCombo.Top              := pVrTop;
      lDsCombo.Height           := pVrheight;
      lDsCombo.Width            := pVrWidth;
      lDsCombo.DataField        := pNmColuna;
      lDsCombo.DataSource       := DCadastro;
      lDsCombo.Enabled          := pSnEnable;
      lDsCombo.Visible          := pSnVisibl;
      lDsCombo.Text             := '';
      lDsCombo.Items.CommaText  := pDsDomini;
      lDsCombo.Values.CommaText := pVrDomini;
      lDsCombo.Anchors          := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);
end;

procedure TCad0003.CriarMemo(pNrCompon : Integer;     pDsAlias  : Char;
                             pNmColuna : String;      pNrComPai : Integer;
                             pVrLeft   : Integer;     pVrTop    : Integer;
                             pVrHeight : Integer;     pVrWidth  : Integer;
                             pSnEnable : Boolean;     pSnVisibl : Boolean;
                             pTpCompon : String;      pNrQueCon : Integer;
                             pSnAncAci : Boolean;     pSnAncAba : Boolean;
                             pSnAncEsq : Boolean;     pSnAncDir : Boolean);
var lDsMemo : TDbMemo;
begin
      gDsSelect := gDsSelect + pDsAlias + '.' + pNmColuna + ', ';

      lDsMemo             := TDbMemo.Create(Self);
      lDsMemo.Tag         := pNrCompon;
      lDsMemo.Name        := '_' + FormatFloat('0000', pNrCompon);
      lDsMemo.Parent      := EncontrarComponentePai(pNrComPai, pNmColuna);
      lDsMemo.Left        := pVrLeft;
      lDsMemo.Top         := pVrTop;
      lDsMemo.Height      := pVrheight;
      lDsMemo.Width       := pVrWidth;
      lDsMemo.DataField   := pNmColuna;
      lDsMemo.DataSource  := DCadastro;
      lDsMemo.Enabled     := pSnEnable;
      lDsMemo.Visible     := pSnVisibl;
      lDsMemo.Lines.Clear;
      lDsMemo.Font.Name   := 'Courier New';
      lDsMemo.Font.Size   := 10;
      lDsMemo.OnKeyPress  := KeyPress;
      lDsMemo.Anchors     := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);
      lDsMemo.ScrollBars  := ssBoth;
end;

procedure TCad0003.CriarDbLabel(pNrCompon : Integer;     pDsAlias  : Char;
                                pNmColuna : String;      pNrComPai : Integer;
                                pVrLeft   : Integer;     pVrTop    : Integer;
                                pVrHeight : Integer;     pVrWidth  : Integer;
                                pNrForDin : Integer;
                                pSnAncAci : Boolean;     pSnAncAba : Boolean;
                                pSnAncEsq : Boolean;     pSnAncDir : Boolean);
var lDsText : TBrvDbRetCon;
begin
      gDsSelect := gDsSelect +
                   RetornoDaConsulta(pNrForDin, pNrCompon, pDsAlias) +
                   ' as ' + pNmColuna + ', ';

      lDsText             := TBrvDbRetCon.Create(Self);
      lDsText.Tag         := pNrCompon;
      lDsText.Name        := '_' + FormatFloat('0000', pNrCompon);
      lDsText.Text        := '';
      lDsText.Parent      := EncontrarComponentePai(pNrComPai, pNmColuna);
      lDsText.Left        := pVrLeft;
      lDsText.Top         := pVrTop;
      lDsText.Height      := pVrheight;
      lDsText.Width       := pVrWidth;
      lDsText.DataField   := pNmColuna;
      lDsText.DataSource  := DCadastro;
      lDsText.Anchors     := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);
//      lDsText.Font.Style  := [fsBold];
//      lDsText.Font.color  := clMaroon;
end;

function TCad0003.RetornoDaConsulta(pNrForDin : Integer; pNrCompon : Integer;
                                    pDsAlias  : Char) : String;
var lNrAtribu    : Integer;
    lDsSepCol    : String;
begin
      QpConsult.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                   ' Select S026.DsSepAnt, S026.NmAtribu ' +
                   ' From   S026 S026 ' +
                   ' Where  S026.NrForDin = ' + IntToStr(pNrForDin) +
                   ' and    S026.NrCompon = ' + IntToStr(pNrCompon) +
                   ' Order by S026.NrOrdCon');

      lNrAtribu := 0;
      Result    := '';

      while not QpConsult.Eof do
      begin
            inc(lNrAtribu);

            if lNrAtribu > 1 then
            begin
                  lDsSepCol := QpConsult.FieldByName('DsSepAnt').AsString;
                  lDsSepCol := StringReplace(lDsSepCol, '{', '', [rfReplaceAll]);
                  lDsSepCol := StringReplace(lDsSepCol, '}', '', [rfReplaceAll]);

                  Result := Result + ' || ' + #39 + lDsSepCol + #39;
            end;

            if Result <> '' then
            begin
                  Result := Result + ' || ';
            end;

            Result := Result + pDsAlias + '.' +
                                              QpConsult.FieldByName('NmAtribu').AsString;


            QpConsult.Next;
      end;
end;

procedure TCad0003.CriarEdit(pNrCompon : Integer;     pDsAlias  : Char;
                             pNmColuna : String;      pNrComPai : Integer;
                             pVrLeft   : Integer;     pVrTop    : Integer;
                             pVrHeight : Integer;     pVrWidth  : Integer;
                             pSnEnable : Boolean;     pSnVisibl : Boolean;
                             pTpCompon : String;      pNrQueCon : Integer;
                             pSnAncAci : Boolean;     pSnAncAba : Boolean;
                             pSnAncEsq : Boolean;     pSnAncDir : Boolean;
                             pSnAutInc : Boolean);
var lDsEdit : TBrvDbEdit;
begin
      gDsSelect := gDsSelect + pDsAlias + '.' + pNmColuna + ', ';

      lDsEdit                 := TBrvDbEdit.Create(Self);
      lDsEdit.Tag             := pNrCompon;
      lDsEdit.Name            := '_' + FormatFloat('0000', pNrCompon);
      lDsEdit.Text            := '';
      lDsEdit.Parent          := EncontrarComponentePai(pNrComPai, pNmColuna);
      lDsEdit.Left            := pVrLeft;
      lDsEdit.Top             := pVrTop;
      lDsEdit.Height          := pVrheight;
      lDsEdit.Width           := pVrWidth;
      lDsEdit.DataField       := pNmColuna;
      lDsEdit.DataSource      := DCadastro;
      lDsEdit.Enabled         := pSnEnable;
      lDsEdit.Visible         := pSnVisibl;
      lDsEdit.OnKeyPress      := KeyPress;
      lDsEdit.BrDicionario    := DmSrvApl.BrvDicionario;
      lDsEdit.BrQueryConsulta := pNrQueCon;
      lDsEdit.Anchors         := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);
      case pTpCompon[1] of
           'N': begin
                      lDsEdit.BrVisibleButton  := True;
                      if pNrQueCon = 0 then
                      begin
                            if not pSnAutInc then
                            begin
                                  lDsEdit.BrFunctionButton := TpDbCalculadora;
                            end else
                            begin
                                  lDsEdit.BrVisibleButton  := False;
                            end;
                      end else
                      begin
                            lDsEdit.BrFunctionButton := TpDbConsulta;
                      end;
                end;
           'D': begin
                      lDsEdit.BrVisibleButton  := True;
                      lDsEdit.BrFunctionButton := TpDbData;
                end;
           else begin
                      if pNrQueCon <> 0 then
                      begin
                            lDsEdit.BrVisibleButton := True;
                            lDsEdit.BrFunctionButton := TpDbConsulta;
                      end else
                      begin
                            lDsEdit.BrVisibleButton := False;
                      end;
                      lDsEdit.BrAlignment     := taLeftJustify;
                end;
      end;
end;

procedure TCad0003.CriarTabSheet(pNrCompon : Integer; pNrComPai : Integer;
                                 pDsCaptio : String);
var lDsTabShe : TTabSheet;
begin
      lDsTabShe             := TTabSheet.Create(Self);
      lDsTabShe.Tag         := pNrCompon;
      lDsTabShe.Name        := '_' + FormatFloat('0000', pNrCompon);
      lDsTabShe.Parent      := EncontrarComponentePai(pNrComPai, pDsCaptio);
      lDsTabShe.PageControl := TPageControl(FindComponent(lDsTabShe.Parent.Name));
      lDsTabShe.Caption     := pDsCaptio;
end;

procedure TCad0003.DbgColunaKeyPress(Sender: TObject; var Key: Char);
begin
      if PermiteModificarCampo(((Sender as TBrvDbGrid).SelectedField).FieldName) then
      begin
            if ((Sender as TBrvDbGrid).SelectedField is TBrWideStringField) then
            begin
                  case ((Sender as TBrvDbGrid).SelectedField as
                                                         TBrWideStringField).BrCharCase of
                       BrSfUpperCase : key := BrvString.BrUpCase(key);
                       BrSfLowerCase : key := BrvString.BrLwCase(key);
                  end;
            end else
            begin
                  if ((Sender as TBrvDbGrid).SelectedField is TBrMemoField) then
                  begin
                        case ((Sender as TBrvDbGrid).SelectedField as
                                                               TBrMemoField).BrCharCase of
                             BrSfUpperCase : key := BrvString.BrUpCase(key);
                             BrSfLowerCase : key := BrvString.BrLwCase(key);
                        end;
                  end;
            end;
      end else
      begin
            Key := #0;
      end;
end;

function TCad0003.PermiteModificarCampo(pNmColuna : String) : Boolean;
var lNrColuna : Integer;
begin
      Result := False;

      if QCadastro.Tag = 1 then // em modo pesquisa
      begin
            Result := True;
      end else
      begin
            lNrColuna := 0;

            while lNrColuna < DbgColuna.Columns.Count do
            begin
                  if DbgColuna.Columns[lNrColuna].FieldName = pNmColuna then
                  begin
                        if BrExcluir in DbgColuna.Columns[lNrColuna].BrPermissao then
                        begin
                              Result := True;
                        end
                        else if BrIncluir in DbgColuna.Columns[lNrColuna].BrPermissao then
                             begin
                                   if (QCadastro.FieldByName(pNmColuna).OldValue = '') or
                                      (QCadastro.FieldByName(pNmColuna).OldValue = null)
                                                                                      then
                                   begin
                                         Result := True;
                                   end
                                   else if (QCadastro.FieldByName(pNmColuna).OldValue <>
                                                                                   '') and
                                           (QCadastro.FieldByName(pNmColuna).OldValue <>
                                                                                null) then
                                        begin
                                              if Trim(
                                                QCadastro.FieldByName(pNmColuna).NewValue)
                                                                                 = '' then
                                              begin
                                                    Result := False;
                                              end else
                                              begin
                                                    Result := True;
                                              end;
                                        end
                                   ;
                             end
                        else if BrAlterar in DbgColuna.Columns[lNrColuna].BrPermissao then
                             begin
                                   if (QCadastro.FieldByName(pNmColuna).OldValue <> '')
                                                                                       and
                                      (QCadastro.FieldByName(pNmColuna).OldValue <> null)
                                                                                      then
                                   begin
                                         Result := True;
                                   end;
                             end
                        else if BrLer in DbgColuna.Columns[lNrColuna].BrPermissao then
                             begin
                                   Result := False;
                             end
                        ;

                        lNrColuna := DbgColuna.Columns.Count + 10;
                  end;

                  inc(lNrColuna);
            end;
      end;
end;

procedure TCad0003.CriarPageControl(pNrCompon : Integer; pNrComPai : Integer;
                                    pVrLeft   : Integer; pVrTop    : Integer;
                                    pVrHeight : Integer; pVrWidth  : Integer;
                                    pSnAncAci : Boolean; pSnAncAba : Boolean;
                                    pSnAncEsq : Boolean; pSnAncDir : Boolean);
var lDsPagCon : TPageControl;
begin
      lDsPagCon             := TPageControl.Create(Self);
      lDsPagCon.Tag         := pNrCompon;
      lDsPagCon.Name        := '_' + FormatFloat('0000', pNrCompon);
      lDsPagCon.Parent      := EncontrarComponentePai(pNrComPai, lDsPagCon.Name);
      lDsPagCon.Left        := pVrLeft;
      lDsPagCon.Top         := pVrTop;
      lDsPagCon.Height      := pVrHeight;
      lDsPagCon.Width       := pVrWidth;
      lDsPagCon.Anchors     := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);
end;

procedure TCad0003.CriarPanel(pNrCompon : Integer; pNrComPai : Integer;
                              pVrLeft   : Integer; pVrTop    : Integer;
                              pVrHeight : Integer; pVrWidth  : Integer;
                              pSnAncAci : Boolean; pSnAncAba : Boolean;
                              pSnAncEsq : Boolean; pSnAncDir : Boolean);
var lDsPanel : TPanel;
begin
      lDsPanel             := TPanel.Create(Self);
      lDsPanel.Tag         := pNrCompon;
      lDsPanel.Name        := '_' + FormatFloat('0000', pNrCompon);
      lDsPanel.Parent      := EncontrarComponentePai(pNrComPai, lDsPanel.Name);
      lDsPanel.Left        := pVrLeft;
      lDsPanel.Top         := pVrTop;
      lDsPanel.Height      := pVrHeight;
      lDsPanel.Width       := pVrWidth;
      lDsPanel.Caption     := ' ';
      lDsPanel.Anchors     := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);
end;

procedure TCad0003.CriarLabel(pNrCompon : Integer; pDsCaptio : String;
                              pSnChaPri : Boolean; pSnNulo   : Boolean;
                              pNrComPai : Integer; pVrLeft   : Integer;
                              pVrTop    : Integer; pVrHeight : Integer;
                              pVrWidth  : Integer; pSnAutInc : Boolean;
                              pSnAncAci : Boolean; pSnAncAba : Boolean;
                              pSnAncEsq : Boolean; pSnAncDir : Boolean);
var lDsLabel : TLabel;
begin
      lDsLabel            := TLabel.Create(Self);
      lDsLabel.Tag        := pNrCompon;
      lDsLabel.Parent     := EncontrarComponentePai(pNrComPai, pDsCaptio);
      lDsLabel.AutoSize   := False;
      lDsLabel.Left       := pVrLeft;
      lDsLabel.Top        := pVrTop;
      lDsLabel.Height     := pVrHeight;
      lDsLabel.Width      := pVrWidth;
      lDsLabel.Caption    := pDsCaptio;
      lDsLabel.Name       := '_' + FormatFloat('0000', pNrCompon);
      lDsLabel.Font.Style := [fsBold];
      lDsLabel.Anchors    := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      case gDsCompon[pNrCompon].TpCompon of
           'B' : begin // La(B)el de campo {{ os demais labels são pretos mesmo }}
                       if pSnChaPri then
                       begin
                             lDsLabel.Font.Style := lDsLabel.Font.Style + [fsUnderline];
                       end;

                       if not pSnNulo then
                       begin
                             lDsLabel.Font.color := clBlue;
                       end;

                       if pSnAutInc then // auto-incremento
                       begin
                             lDsLabel.Font.color := clGreen;
                       end;
                 end;
      end;
end;

function TCad0003.EncontrarComponentePai(pNrTag : Integer;
                                         pDsCompon : String) : TWinControl;
begin
      if pNrTag = 0 then
      begin
            Result :=  SbxFundo;
      end else
      begin
            Result := TWinControl(FindComponent('_' + FormatFloat('0000', pNrTag)));

            if Result = nil then
            begin
                  MessageDlg('Componente pai do objeto ' + pDsCompon +
                                               ' não foi encontrado', MtError, [MbOk], 0);
            end;
      end;
end;

procedure TCad0003.CriarField(pNrCompon : Integer);
var lNmColuna : String;
begin
      if pNrCompon > 0 then
      begin
            lNmColuna := gDsCompon[pNrCompon].NmColuna;

            CriarFieldQuery(gDsCompon[pNrCompon].TpColuna,
                            gDsCompon[pNrCompon].TmColuna,
                            gDsCompon[pNrCompon].TpMascar,
                            gDsCompon[pNrCompon].DsMascar,
                            lNmColuna, QCadastro,
                            gDsCompon[pNrCompon].DsCaptio,
                            pNrCompon,
                            gDsCompon[pNrCompon].DsAlias = 'A');

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-=-=-=-=-=-= Criando campo na Grid
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            DbgColuna.Columns.Add;
      end;
end;

procedure TCad0003.CriarFieldQuery(pTpColuna : String;   pTmColuna : Integer;
                                   pTpCaract : String;   pDsMascar : String;
                                   pNmColuna : String;   pDataSet  : TClientDataSet;
                                   pDsColuna : String;   pNrTag    : Integer;
                                   pSnProvid : Boolean);
var lDsField : TField;
begin
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-=-=-=-= Criando campo na query
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      case pTpColuna[1] of
           'V' : begin // Varchar
                       ///if pTmColuna <= 500 then
                      // begin
                             lDsField      := TBrWideStringField.Create(Self);
                             lDsField.Size := pTmColuna;

                             case pTpCaract[1] of
                                  //maiúsculo
                                  'M': (lDsField as TBrWideStringField).BrCharCase :=
                                                                           BrSfUpperCase;

                                  //minúsculo
                                  'm': (lDsField as TBrWideStringField).BrCharCase :=
                                                                           BrSfLowerCase;

                                  // máscara cadastrada
                                  'D': if Trim(pDsMascar) <> '' then
                                       begin
                                             (lDsField as TBrWideStringField).EditMask :=
                                                                      pDsMascar + ';0; ';
                                       end;
                             end;
                       {end else
                       begin
                             lDsField      := TBrMemoField.Create(Self);
                             lDsField.Size := 0;

                             case pTpCaract[1] of
                                  //maiúsculo
                                  'M': (lDsField as TBrMemoField).BrCharCase :=
                                                                        BrSfUpperCase;
                                  //minúsculo
                                  'm': (lDsField as TBrMemoField).BrCharCase :=
                                                                        BrSfLowerCase;
                             end;
                       end;   }
                 end;
           'B' : begin // Blob Sub_Type 1
                       lDsField      := TBrMemoField.Create(Self);
                       lDsField.Size := 0;

                       case pTpCaract[1] of
                            //maiúsculo
                            'M': (lDsField as TBrMemoField).BrCharCase := BrSfUpperCase;
                            //minúsculo
                            'm': (lDsField as TBrMemoField).BrCharCase := BrSfLowerCase;
                       end;
                 end;
           'I' : begin // Integer
                       lDsField           := TFmTBcdField.Create(Self);
                       lDsField.Alignment := taLeftJustify;
                       lDsField.Size      := 0;
                 end;
           'N' : begin // Numeric (Float)
                       if DmSrvApl.BrvDicionario.TipoBancoDados = BcoOracle then
                       begin
                             lDsField           := TFmTBcdField.Create(Self);
                             lDsField.Alignment := taLeftJustify;
                             lDsField.Size      := 0;

                             if pTmColuna > 0 then
                             begin
                                   (lDsField as TFmTBcdField).DisplayFormat :=
                                   '###' + DecimalSeparator + '###' + DecimalSeparator  +
                                   '###' + DecimalSeparator + '##0' + ThousandSeparator +
                                   StringOfChar('0', pTmColuna);
                             end;
                       end else
                       begin
                             lDsField           := TFloatField.Create(Self);
                             lDsField.Alignment := taLeftJustify;
                             lDsField.Size      := 0;

                             if pTmColuna > 0 then
                             begin
                                   (lDsField as TFloatField).DisplayFormat :=
                                   '###' + DecimalSeparator + '###' + DecimalSeparator  +
                                   '###' + DecimalSeparator + '##0' + ThousandSeparator +
                                   StringOfChar('0', pTmColuna);
                             end;
                       end;
                 end;
           'D' : begin // Date
                       lDsField := TSQLTimeStampField.Create(Self);
                       lDsField.Alignment := taLeftJustify;
                       lDsField.EditMask  := '99/99/9999;1; ';
                       lDsField.Size      := 0;
                 end;
           'L' : begin // Blob
                       lDsField := TGraphicField.Create(Self);
                       lDsField.Size      := 0;
                 end;
           else  begin
                       raise Exception.Create('Tipo de campo para coluna "' +
                                              pNmColuna + '" não foi definido');
                 end;
      end;

      lDsField.FieldName     := pNmColuna;
      lDsField.DataSet       := pDataSet;
      lDsField.DisplayLabel  := pDsColuna;
      lDsField.DisplayWidth  := pTmColuna;
      lDsField.Required      := False;
      lDsField.Tag           := pNrTag;
      lDsField.OnSetText     := SetarTextoCampo;

      if not pSnProvid then
      begin
            lDsField.ProviderFlags := [];
      end;
end;

procedure TCad0003.SetarTextoCampo(Sender: TField; const Text: String);
begin
      if (Text = '') or (Text = '  /  /    ') or (Text = '  /  /       :  :  ')then
      begin
            Sender.Value := null;
      end else
      begin
            if Copy(Text, 12, 8) = '  :  :  ' then
            begin
                  Sender.AsString := Copy(Text, 1, 10) + ' 00:00:00';
            end else
            begin
                 if (Sender.DataType = ftFMTBcd) then
                 begin
                       Sender.AsFloat := StrToFloat(StringReplace(Text,'.', '', [rfReplaceAll]));
                 end else
                 begin
                       Sender.AsString := Text;
                 end;
            end;
      end;
end;

procedure TCad0003.SbtExportClick(Sender: TObject);
begin
      BrvExport.Execute(gNmTabela, QCadastro.Data);
end;

function TCad0003.Ancoras(pSnAncAci : Boolean; pSnAncAba : Boolean;
                          pSnAncEsq : Boolean; pSnAncDir : Boolean) : TAnchors;
begin
      Result := [];

      if pSnAncAci then
      begin
            Result := Result + [akTop];
      end;

      if pSnAncAba then
      begin
            Result := Result + [akBottom];
      end;

      if pSnAncEsq then
      begin
            Result := Result + [akLeft];
      end;

      if pSnAncDir then
      begin
            Result := Result + [akRight];
      end;
end;

initialization
      RegisterClass(TCad0003);

finalization
      UnRegisterClass(TCad0003);

end.
