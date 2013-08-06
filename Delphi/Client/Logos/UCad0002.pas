unit UCad0002;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, UClaSrv,
  Dialogs, UCad0000, Buttons, BrvSpeedButton, ExtCtrls, BrvDbNavCop, StdCtrls, DB,
  DBClient, BrvClientDataSet, BrvString, ComCtrls, BrvEditNum, BrvEditDate, BrvEdit,
  BrvComboBox, DBCtrls, BrvRadioGroup, Menus, Mask, BrvDbEdit, BrvRetCon, BrvImage, ImgList,
  BrvImgBot, BrvListParam;

type
  TColuna  = record
       NmAtribu : String;
       DsAtribu : String;
       TpAtribu : String;
       SnChaPri : Boolean;
       DsDomini : String;
       VrDomini : String;
       TpMascar : String;
       DsMascar : String;
       VrSize   : Integer;
  end;

  TCompon = record
       NrCompon : Integer;
       NmAtribu : String;
       NrQueCon : Integer; // número da query de consulta
       NmChaCon : String;  // nome da chave de consulta
       DsRetCon : String;  // Valores que retornarão da consulta (campos)
       DsAtrCon : String;  // atributos que retornarão da comsulta (label de retorno)
       DsSepCon : String;  // separador de campos de retorno da consulta
       DsCaptio : String;
       DsCapGri : String;
       NrTabOrd : Integer;
       NrTabGri : Integer;
       SnEnable : Boolean;
       SnVisibl : Boolean;
       SnAncAci : Boolean; // ancora top
       SnAncAba : Boolean; // ancora botton
       SnAncEsq : Boolean; // ancora esquerda
       SnAncDir : Boolean; // ancora direita
       TpCompon : Char; // (L)abel,          (P)anel, Pa(G)eControl, (T)abSheet,
                        // La(B)el de campo, (E)dit,  (M)emo,
                        // L(A)bel de consulta
                        // Edit(N)um,        (C)ombobox, (R)adioGroup
                        // Chec(K) box       (I)magem
       TpDefaul  : String;
       VrDefaul  : String;
       NrComPai : Integer;
       SnNulo   : Boolean;
       SnAutInc : Boolean;

       DsDomini : String;
       SnDelete : Boolean; // Componente deletado;
       LblRotul : TStaticText;
       LblConsu : TStaticText;
       NrAtribu : Integer;
  end;

  TCad0002 = class(TCad0000)
    PnlForDin: TPanel;
    SbxFundo: TScrollBox;
    Image1: TImage;
    PnlFunPro: TPanel;
    PnlAtribu: TPanel;
    PnlColuna: TPanel;
    LbxAtribu: TListBox;
    QpAtrDis: TBrvClientDataSet;
    Label1: TLabel;
    LblPosX: TLabel;
    LblPosY: TLabel;
    PnlBarra: TPanel;
    ShpVulto: TStaticText;
    SbtSeta: TSpeedButton;
    SbtLabel: TSpeedButton;
    SbtSalvar: TBrvSpeedButton;
    BrvString: TBrvString;
    PgcInspec: TPanel;
    Label2: TLabel;
    SbtConsul: TSpeedButton;
    Label3: TLabel;
    EdtDsCaptio: TEdit;
    EdtDsCapGri: TEdit;
    Label4: TLabel;
    Label5: TLabel;
    EdtVlLeft: TBrvEditNum;
    EdtVlTop: TBrvEditNum;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    EdtVlHeigth: TBrvEditNum;
    EdtVlWidth: TBrvEditNum;
    LblNrConsul: TLabel;
    Label9: TLabel;
    CbxTpObjeto: TBrvComboBox;
    SbtTabOrd: TSpeedButton;
    SbtTabGri: TSpeedButton;
    QpObjDin: TBrvClientDataSet;
    SbtPagCon: TSpeedButton;
    PopPagCtr: TPopupMenu;
    AdicionarPgina1: TMenuItem;
    SbtPanel: TSpeedButton;
    PopupMenu1: TPopupMenu;
    SbtOrdem: TBrvSpeedButton;
    CdsOrdIni: TBrvClientDataSet;
    PgcOpcao: TPageControl;
    TbsOpcVis: TTabSheet;
    TbsOpcCon: TTabSheet;
    CbxVisibl: TCheckBox;
    ChxEnabled: TCheckBox;
    CbxSnNulo: TCheckBox;
    CbxAutInc: TCheckBox;
    TbsOpcAnc: TTabSheet;
    Label10: TLabel;
    SbtDefaul: TSpeedButton;
    LblDsDefaul: TLabel;
    CbxAncAci: TCheckBox;
    CbxAncEsq: TCheckBox;
    CbxAncDir: TCheckBox;
    CbxAncAba: TCheckBox;
    BrvString2: TBrvString;
    BrvString3: TBrvString;
    BrvSpeedButton2: TBrvSpeedButton;
    procedure LbxAtribuMouseDown(Sender: TObject; Button: TMouseButton;
      Shift: TShiftState; X, Y: Integer);
    procedure SbxFundoMouseMove(Sender: TObject; Shift: TShiftState; X, Y: Integer);
    procedure FormMouseMove(Sender: TObject; Shift: TShiftState; X, Y: Integer);
    procedure SbxFundoDragOver(Sender, Source: TObject; X, Y: Integer; State: TDragState;
      var Accept: Boolean);
    procedure SbxFundoDragDrop(Sender, Source: TObject; X, Y: Integer);
    procedure FormCreate(Sender: TObject);
    procedure ShpVultoEndDrag(Sender, Target: TObject; X, Y: Integer);
    procedure ShpVultoDragOver(Sender, Source: TObject; X, Y: Integer; State: TDragState;
      var Accept: Boolean);
    procedure EdtDsCaptioKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
    procedure EdtDsCapGriKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
    procedure EdtVlLeftChange(Sender: TObject);
    procedure EdtVlTopChange(Sender: TObject);
    procedure EdtVlWidthChange(Sender: TObject);
    procedure EdtVlHeigthChange(Sender: TObject);
    procedure ChxEnabledClick(Sender: TObject);
    procedure CbxVisiblClick(Sender: TObject);
    procedure ComponenteFundoClick(Sender: TObject);
    procedure FormKeyDown(Sender: TObject; var Key: Word; Shift: TShiftState);
    procedure CbxAutIncClick(Sender: TObject);
    procedure CbxSnNuloClick(Sender: TObject);
    procedure CbxTpObjetoChange(Sender: TObject);
    procedure SbtTabOrdClick(Sender: TObject);
    procedure SbtTabGriClick(Sender: TObject);
    procedure SbtSalvarClick(Sender: TObject);
    procedure FormCloseQuery(Sender: TObject; var CanClose: Boolean);
    procedure AdicionarPgina1Click(Sender: TObject);
    procedure SbtForDinClick(Sender: TObject);
    procedure SbtDefaulClick(Sender: TObject);
    procedure SbtOrdemClick(Sender: TObject);
    procedure SbtConsulClick(Sender: TObject);
    procedure CbxAncAciClick(Sender: TObject);
    procedure CbxAncEsqClick(Sender: TObject);
    procedure CbxAncDirClick(Sender: TObject);
    procedure CbxAncAbaClick(Sender: TObject);
  private
    { Private declarations }
    gNmTabela : String;
    gNrForDin : Integer;
    gDsComCli : TWinControl; // componente clicado
    gNoCompon : Integer;     // Número sequencial de componente
    gDsCompon  : array [0..900] of TCompon;
    gNmColuna  : array          of TColuna;  // Colunas
    gVlClickX  : Integer;
    gVlClickY  : Integer;
    PnlMarcad  : array [0..7]   of TPanel;
    gNoTabOrd  : Integer;
    gDsEdtOrd  : Array of TEdit;
    procedure CarregarAtributosDisponiveis;
    procedure RedimencionarVulto(X, Y: Integer);
    procedure EncontrarComponenteClicado(pNoTag: Integer);
    procedure CriarPageControl(pDsParent : TWinControl; pVlLeft   : Integer;
                               pVlTop    : Integer;     pVlWidth  : Integer;
                               pVlHeight : Integer;     pNrCompon : Integer;
                               pSnAncAci : Boolean;     pSnAncAba : Boolean;
                               pSnAncEsq : Boolean;     pSnAncDir : Boolean);
    function CriarLabel(pDsCaptio : String;  pTpCompon : Char;
                        pSnNulo   : Boolean; pSnChaPri : Boolean;
                        pNmColuna : String;  pVlLeft   : Integer;
                        pVlTop    : Integer; pVlHeight : Integer;
                        pVlWidth  : Integer; pDsParent : TWinControl;
                        pNoTabGri : Integer; pDsCapGri : String;
                        pNrAtribu : Integer; pNrCompon : Integer;
                        pTpDefaul : String;  pVrDefaul : String;
                        pNrConsul : Integer; pNmChaCon : String;
                        pSnAncAci : Boolean; pSnAncAba : Boolean;
                        pSnAncEsq : Boolean; pSnAncDir : Boolean) : TStaticText;
    procedure CriarEdit(pNmColuna : String;      pDsParent : TWinControl;
                        pVlLeft   : Integer;     pVlTop    : Integer;
                        pVlWidth  : Integer;     pVlHeight : Integer;
                        pNoTabOrd : Integer;     pNoTabGri : Integer;
                        pSnEnable : Boolean;     pDsCapGri : String;
                        pSnVisibl : Boolean;     pNoConsul : Integer;
                        pDsLabel  : TStaticText; pSnAutInc : Boolean;
                        pSnNulo   : Boolean;     pNrAtribu : Integer;
                        pNrCompon : Integer;     pTpDefaul : String;
                        pVrDefaul : String;      pNmChaCon : String;
                        pSnAncAci : Boolean;     pSnAncAba : Boolean;
                        pSnAncEsq : Boolean;     pSnAncDir : Boolean);

    procedure CriarEditNumerico(pNmColuna : String;       pDsParent : TWinControl;
                                pVlLeft   : Integer;      pVlTop    : Integer;
                                pVlWidth  : Integer;      pVlHeight : Integer;
                                pNoTabOrd : Integer;      pNoTabGri : Integer;
                                pSnEnable : Boolean;      pDsCapGri : String;
                                pSnVisibl : Boolean;      pNoConsul : Integer;
                                pDsLabel  : TStaticText;  pSnAutInc : Boolean;
                                pSnNulo   : Boolean;      pNrAtribu : Integer;
                                pNrCompon : Integer;      pTpDefaul : String;
                                pVrDefaul : String;       pNmChaCon : String;
                                pSnAncAci : Boolean;      pSnAncAba : Boolean;
                                pSnAncEsq : Boolean;      pSnAncDir : Boolean);

    procedure ConfigurarComponente(pNrCompon: Integer; pTpCompon : Char;
                    pNmColuna : String;       pDsCaptio : String;  pNoComPai : Integer;
                    pSnEnable : Boolean;      pSnVisibl : Boolean; pSnAutInc : Boolean;
                    pSnNulo   : Boolean;      pDsDomini : String;
                    pNoTabGri : Integer;      pNoTabOrd : Integer; pNoConsul : Integer;
                    pDsLabel  : TStaticText;  pNrAtribu : Integer; pDsCapGri : String;
                    pTpDefaul : String;       pVrDefaul : String;  pNmChaCon : String;
                    pSnAncAci : Boolean;      pSnAncAba : Boolean; pSnAncEsq : Boolean;
                    pSnAncDir : Boolean);
    procedure ComponenteFrenteClick(Sender: TObject);
    procedure ComponenteDragOver(Sender, Source: TObject; X, Y: Integer;
      State: TDragState; var Accept: Boolean);
    procedure ComponenteMouseDown(Sender: TObject; Button: TMouseButton;
      Shift: TShiftState; X, Y: Integer);
    procedure ArrastarComponente(pVlClickX, pVlClickY: Integer);
    procedure ContornoComponente;
    procedure PnlMarcadorMouseDown(Sender: TObject; Button: TMouseButton;
      Shift: TShiftState; X, Y: Integer);
    procedure ExcluirComponente;
    procedure ExcluirComponentesFilhos(pNoComPai: Integer);
    procedure ColorirObjeto;
    procedure ColorirLabelObjeto;
    procedure LiberarColunaInclusao;
    procedure PosicionarEdit(
                         pDsEdit   : TEdit;   pNrCompon : Integer; pDsParent : TWinControl;
                         pVlLeft   : Integer; pVlTop    : Integer; pVlWidth  : Integer;
                         pVlHeight : Integer; pSnEnable : Boolean;
                         pSnVisibl : Boolean;
                         pSnAncAci : Boolean; pSnAncAba : Boolean;
                         pSnAncEsq : Boolean; pSnAncDir : Boolean);
    procedure CriarComboBox(pNmColuna : String;  pDsParent : TWinControl;
                            pVlLeft   : Integer; pVlTop    : Integer;
                            pVlWidth  : Integer; pVlHeight : Integer;
                            pDsDomini : String;  pNoTabOrd : Integer;
                            pNoTabGri : Integer; pSnEnable : Boolean;
                            pDsCapGri : String;  pSnVisibl : Boolean;
                            pSnNulo   : Boolean;
                            pNrAtribu : Integer; pDsLabel  : TStaticText;
                            pNrCompon : Integer; pTpDefaul : String;
                            pVrDefaul : String;
                            pSnAncAci : Boolean; pSnAncAba : Boolean;
                            pSnAncEsq : Boolean; pSnAncDir : Boolean);
    procedure CriarRadioGroup(pNmColuna : String;  pDsParent : TWinControl;
                            pVlLeft   : Integer; pVlTop    : Integer;
                            pVlWidth  : Integer; pVlHeight : Integer;
                            pDsDomini : String;  pNoTabOrd : Integer;
                            pNoTabGri : Integer; pSnEnable : Boolean;
                            pDsCapGri : String;  pSnVisibl : Boolean;
                            pSnNulo   : Boolean; pDsCaptio : String;
                            pNrAtribu : Integer; pDsLabel  : TStaticText;
                            pNrCompon : Integer; pTpDefaul : String;
                            pVrDefaul : String;
                            pSnAncAci : Boolean; pSnAncAba : Boolean;
                            pSnAncEsq : Boolean; pSnAncDir : Boolean);
    procedure CriarCheckBox(pNmColuna : String;  pDsParent : TWinControl;
                            pVlLeft   : Integer; pVlTop    : Integer;
                            pVlWidth  : Integer; pVlHeight : Integer;
                            pDsDomini : String;  pNoTabOrd : Integer;
                            pNoTabGri : Integer; pSnEnable : Boolean;
                            pDsCapGri : String;  pSnVisibl : Boolean;
                            pSnNulo   : Boolean; pDsCaptio : String;
                            pNrAtribu : Integer; pDsLabel  : TStaticText;
                            pNrCompon : Integer; pTpDefaul : String;
                            pVrDefaul : String;  pNmChaCon : String;
                            pSnAncAci : Boolean; pSnAncAba : Boolean;
                            pSnAncEsq : Boolean; pSnAncDir : Boolean);
    procedure CriarMemo(pNmColuna : String;       pDsParent : TWinControl;
                        pVlLeft   : Integer;      pVlTop    : Integer;
                        pVlWidth  : Integer;      pVlHeight : Integer;
                        pNoTabOrd : Integer;      pNoTabGri : Integer;
                        pSnEnable : Boolean;      pDsCapGri : String;
                        pSnVisibl : Boolean;      pNoConsul : Integer;
                        pDsLabel  : TStaticText;  pSnAutInc : Boolean;
                        pSnNulo   : Boolean;      pNrAtribu : Integer;
                        pNrCompon : Integer;      pTpDefaul : String;
                        pVrDefaul : String;       pNmChaCon : String;
                        pSnAncAci : Boolean;      pSnAncAba : Boolean;
                        pSnAncEsq : Boolean;      pSnAncDir : Boolean);
    procedure CriarEditData(pNmColuna : String;       pDsParent : TWinControl;
                            pVlLeft   : Integer;      pVlTop    : Integer;
                            pVlWidth  : Integer;      pVlHeight : Integer;
                            pNoTabOrd : Integer;      pNoTabGri : Integer;
                            pSnEnable : Boolean;      pDsCapGri : String;
                            pSnVisibl : Boolean;      pNoConsul : Integer;
                            pDsLabel  : TStaticText;  pSnAutInc : Boolean;
                            pSnNulo   : Boolean;      pNrAtribu : Integer;
                            pNrCompon : Integer;      pTpDefaul : String;
                            pVrDefaul : String;       pNmChaCon : String;
                            pSnAncAci : Boolean;      pSnAncAba : Boolean;
                            pSnAncEsq : Boolean;      pSnAncDir : Boolean);
    procedure CriarImagem(pNmColuna : String;  pDsParent : TWinControl;
                          pVlLeft   : Integer; pVlTop    : Integer;
                          pVlWidth  : Integer; pVlHeight : Integer;
                          pNoTabOrd : Integer; pNoTabGri : Integer;
                          pSnEnable : Boolean; pDsCapGri : String;
                          pSnVisibl : Boolean; pNoConsul : Integer;
                          pDsLabel  : TStaticText;  pSnAutInc : Boolean;
                          pSnNulo   : Boolean; pNrAtribu : Integer;
                          pNrCompon : Integer; pTpDefaul : String;
                          pVrDefaul : String;  pNmChaCon : String;
                          pSnAncAci : Boolean; pSnAncAba : Boolean;
                          pSnAncEsq : Boolean; pSnAncDir : Boolean);
    procedure CriarPanel(pDsParent : TWinControl; pVlLeft  : Integer;
                         pVlTop    : Integer;     pVlWidth : Integer;
                         pVlHeight : Integer;     pNrCompon : Integer;
                         pSnAncAci : Boolean;     pSnAncAba : Boolean;
                         pSnAncEsq : Boolean;     pSnAncDir : Boolean);
    procedure TrocarParaCheckBox;
    procedure TrocarParaRadioGroup;
    procedure TrocarParaComboBox;
    procedure CarregarTabOrderComponentes;
    procedure CliqueTabOrder(Sender: TObject);
    procedure CarregarTabOrderGrid(lSnShow: Boolean);
    procedure CarregarDadosDataSetSalvar(pCdsSalva: TClientDataSet;
                                         pCdsSalCon : TClientDataSet;
                                         pCdsSalRet : TClientDataSet);
    procedure CriarTabelaSalvar(pCdsSalva: TClientDataSet);
    function BooleanToStr(pVrBoolea: Boolean): String;
    procedure CarregarObjetosDinamicos;
    function EncontraAtributoLista(pNmAtribu: String): Integer;
    function EncontrarLabelDoObjeto(pNmAtribu: String; pTpLabel : Char): TStaticText;
    procedure AtribuirLabelsDosObjetos;
    procedure TabSheetOnMouseDown(Sender: TObject; Button: TMouseButton;
      Shift: TShiftState; X, Y: Integer);
    procedure CriarTabSheet(pDsParent : TWinControl; pDsCaptio: String;
                            pNrCompon : Integer);
    procedure CarregarOrdenacaoInicial;
    procedure CriarTabelaSalvarConsulta(pCdsSalCon: TClientDataSet);
    procedure SalvarAtributosDisplayConsulta(pCdsSalCon: TClientDataset;
      pNrCompon: Integer);
    function Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir: Boolean): TAnchors;
    procedure CriarTabelaSalvarRetornoConsulta(pCdsSalRet: TClientDataSet);
    procedure SalvarAtributosRestornoConsulta(pCdsSalRet: TClientDataSet;
                                              pNrCompon  : Integer);
  public
    { Public declarations }
    procedure CarregarFormulario(pNmTabela : String; pNrForDin : Integer);
  end;

var
  Cad0002: TCad0002;

implementation

uses UDmSrvApl, UCai0003, UCad0003, UCai0004, UCai0005, UCai0006;

const cCrInvisi = clBlue;
      cCrAuto   = clGreen;
      cCrHabili = clWindow;
      cCrDesabi = clBtnFace;

{$R *.dfm}

{ TCad0002 }

procedure TCad0002.CarregarFormulario(pNmTabela: String; pNrForDin: Integer);
begin
      gNmTabela := pNmTabela;
      gNrForDin := pNrForDin;
      gNoCompon := 0;

      PnlColuna.Caption := gNmTabela;

      CarregarAtributosDisponiveis;
      CarregarObjetosDinamicos;
      CarregarOrdenacaoInicial;

      SbtSalvar.Enabled := False;
end;

procedure TCad0002.CarregarOrdenacaoInicial;
var lConexao  : TSDmSisClient;
    lDsResult : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          CdsOrdIni.Data := lConexao.OrdemInicialFormDinamico(
                                 DmSrvApl.BrvDicionario.Credencial, lDsResult, gNmTabela);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          CdsOrdIni.IndexDefs.Add('Key1', 'NmAtribu', [ixCaseInsensitive]);
          CdsOrdIni.IndexDefs.Add('Key2', 'NrOrdem', [ixCaseInsensitive]);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCad0002.CarregarObjetosDinamicos;
var lNrColuna : Integer;
    lTpCompon : String;
    lDsLabel  : TLabel;
begin
      QpObjDin.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(14,
                                                   '<%NrForDin%>;' + IntToStr(gNrForDin),
                                                   Name);

      try
          while not QpObjDin.Eof do
          begin
                if QpObjDin.FieldByName('NrCompon').AsInteger > gNoCompon then
                begin
                      gNoCompon  := QpObjDin.FieldByName('NrCompon').AsInteger;
                end;

                EncontrarComponenteClicado(QpObjDin.FieldByName('NrComPai').AsInteger);
                lNrColuna := EncontraAtributoLista(
                                               QpObjDin.FieldByName('NmAtribu').AsString);
                lTpCompon := QpObjDin.FieldByName('TpCompon').AsString;

                case lTpCompon[1] of
                     'L', 'B', 'A' :
                          begin
                                CriarLabel(QpObjDin.FieldByName('DsCaptio').AsString,
                                          lTpCompon[1],
                                          False,
                                          gNmColuna[lNrColuna].SnChaPri,
                                          gNmColuna[lNrColuna].NmAtribu,
                                          QpObjDin.FieldByName('VrLeft').AsInteger,
                                          QpObjDin.FieldByName('VrTop').AsInteger,
                                          QpObjDin.FieldByName('VrHeight').AsInteger,
                                          QpObjDin.FieldByName('VrWidth').AsInteger,
                                          gDsComCli,
                                          QpObjDin.FieldByName('NrTabGri').AsInteger,
                                          gNmColuna[lNrColuna].DsAtribu,
                                          lNrColuna,
                                          QpObjDin.FieldByName('NrCompon').AsInteger,
                                          QpObjDin.FieldByName('TpDefaul').AsString,
                                          QpObjDin.FieldByName('VrDefaul').AsString,
                                          QpObjDin.FieldByName('NrQueCon').AsInteger,
                                          QpObjDin.FieldByName('NmChaCon').AsString,
                                          QpObjDin.FieldByName('SnAncAci').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncAba').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncEsq').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncDir').AsString = 'S');
                          end;
                     'M' :
                          begin
                                CriarMemo(gNmColuna[lNrColuna].NmAtribu,
                                          gDsComCli,
                                          QpObjDin.FieldByName('VrLeft').AsInteger,
                                          QpObjDin.FieldByName('VrTop').AsInteger,
                                          QpObjDin.FieldByName('VrWidth').AsInteger,
                                          QpObjDin.FieldByName('VrHeight').AsInteger,
                                          QpObjDin.FieldByName('NrTabOrd').AsInteger,
                                          QpObjDin.FieldByName('NrTabGri').AsInteger,
                                          QpObjDin.FieldByName('SnEnable').AsString = 'S',
                                          QpObjDin.FieldByName('DsCapGri').AsString,
                                          QpObjDin.FieldByName('SnVisibl').AsString = 'S',
                                          QpObjDin.FieldByName('NrQueCon').AsInteger,
                                          nil,
                                          QpObjDin.FieldByName('SnAutInc').AsString = 'S',
                                          QpObjDin.FieldByName('SnNulo').AsString = 'S',
                                          lNrColuna,
                                          QpObjDin.FieldByName('NrCompon').AsInteger,
                                          QpObjDin.FieldByName('TpDefaul').AsString,
                                          QpObjDin.FieldByName('VrDefaul').AsString,
                                          QpObjDin.FieldByName('NmChaCon').AsString,
                                          QpObjDin.FieldByName('SnAncAci').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncAba').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncEsq').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncDir').AsString = 'S');
                          end;
                     'E' :
                          begin
                                CriarEdit(gNmColuna[lNrColuna].NmAtribu,
                                          gDsComCli,
                                          QpObjDin.FieldByName('VrLeft').AsInteger,
                                          QpObjDin.FieldByName('VrTop').AsInteger,
                                          QpObjDin.FieldByName('VrWidth').AsInteger,
                                          QpObjDin.FieldByName('VrHeight').AsInteger,
                                          QpObjDin.FieldByName('NrTabOrd').AsInteger,
                                          QpObjDin.FieldByName('NrTabGri').AsInteger,
                                          QpObjDin.FieldByName('SnEnable').AsString = 'S',
                                          QpObjDin.FieldByName('DsCapGri').AsString,
                                          QpObjDin.FieldByName('SnVisibl').AsString = 'S',
                                          QpObjDin.FieldByName('NrQueCon').AsInteger,
                                          nil,
                                          QpObjDin.FieldByName('SnAutInc').AsString = 'S',
                                          QpObjDin.FieldByName('SnNulo').AsString = 'S',
                                          lNrColuna,
                                          QpObjDin.FieldByName('NrCompon').AsInteger,
                                          QpObjDin.FieldByName('TpDefaul').AsString,
                                          QpObjDin.FieldByName('VrDefaul').AsString,
                                          QpObjDin.FieldByName('NmChaCon').AsString,
                                          QpObjDin.FieldByName('SnAncAci').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncAba').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncEsq').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncDir').AsString = 'S');
                          end;
                     'N' :
                          begin
                                CriarEditNumerico(gNmColuna[lNrColuna].NmAtribu,
                                          gDsComCli,
                                          QpObjDin.FieldByName('VrLeft').AsInteger,
                                          QpObjDin.FieldByName('VrTop').AsInteger,
                                          QpObjDin.FieldByName('VrWidth').AsInteger,
                                          QpObjDin.FieldByName('VrHeight').AsInteger,
                                          QpObjDin.FieldByName('NrTabOrd').AsInteger,
                                          QpObjDin.FieldByName('NrTabGri').AsInteger,
                                          QpObjDin.FieldByName('SnEnable').AsString = 'S',
                                          QpObjDin.FieldByName('DsCapGri').AsString,
                                          QpObjDin.FieldByName('SnVisibl').AsString = 'S',
                                          QpObjDin.FieldByName('NrQueCon').AsInteger,
                                          nil,
                                          QpObjDin.FieldByName('SnAutInc').AsString = 'S',
                                          QpObjDin.FieldByName('SnNulo').AsString = 'S',
                                          lNrColuna,
                                          QpObjDin.FieldByName('NrCompon').AsInteger,
                                          QpObjDin.FieldByName('TpDefaul').AsString,
                                          QpObjDin.FieldByName('VrDefaul').AsString,
                                          QpObjDin.FieldByName('NmChaCon').AsString,
                                          QpObjDin.FieldByName('SnAncAci').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncAba').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncEsq').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncDir').AsString = 'S');
                          end;
                     'D' :
                          begin
                                CriarEditData(gNmColuna[lNrColuna].NmAtribu,
                                          gDsComCli,
                                          QpObjDin.FieldByName('VrLeft').AsInteger,
                                          QpObjDin.FieldByName('VrTop').AsInteger,
                                          QpObjDin.FieldByName('VrWidth').AsInteger,
                                          QpObjDin.FieldByName('VrHeight').AsInteger,
                                          QpObjDin.FieldByName('NrTabOrd').AsInteger,
                                          QpObjDin.FieldByName('NrTabGri').AsInteger,
                                          QpObjDin.FieldByName('SnEnable').AsString = 'S',
                                          QpObjDin.FieldByName('DsCapGri').AsString,
                                          QpObjDin.FieldByName('SnVisibl').AsString = 'S',
                                          QpObjDin.FieldByName('NrQueCon').AsInteger,
                                          nil,
                                          QpObjDin.FieldByName('SnAutInc').AsString = 'S',
                                          QpObjDin.FieldByName('SnNulo').AsString = 'S',
                                          lNrColuna,
                                          QpObjDin.FieldByName('NrCompon').AsInteger,
                                          QpObjDin.FieldByName('TpDefaul').AsString,
                                          QpObjDin.FieldByName('VrDefaul').AsString,
                                          QpObjDin.FieldByName('NmChaCon').AsString,
                                          QpObjDin.FieldByName('SnAncAci').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncAba').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncEsq').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncDir').AsString = 'S');
                          end;
                     'I' :
                          begin
                                CriarImagem(gNmColuna[lNrColuna].NmAtribu,
                                          gDsComCli,
                                          QpObjDin.FieldByName('VrLeft').AsInteger,
                                          QpObjDin.FieldByName('VrTop').AsInteger,
                                          QpObjDin.FieldByName('VrWidth').AsInteger,
                                          QpObjDin.FieldByName('VrHeight').AsInteger,
                                          QpObjDin.FieldByName('NrTabOrd').AsInteger,
                                          QpObjDin.FieldByName('NrTabGri').AsInteger,
                                          QpObjDin.FieldByName('SnEnable').AsString = 'S',
                                          QpObjDin.FieldByName('DsCapGri').AsString,
                                          QpObjDin.FieldByName('SnVisibl').AsString = 'S',
                                          QpObjDin.FieldByName('NrQueCon').AsInteger,
                                          nil,
                                          QpObjDin.FieldByName('SnAutInc').AsString = 'S',
                                          QpObjDin.FieldByName('SnNulo').AsString = 'S',
                                          lNrColuna,
                                          QpObjDin.FieldByName('NrCompon').AsInteger,
                                          QpObjDin.FieldByName('TpDefaul').AsString,
                                          QpObjDin.FieldByName('VrDefaul').AsString,
                                          QpObjDin.FieldByName('NmChaCon').AsString,
                                          QpObjDin.FieldByName('SnAbaAci').AsString = 'S',
                                          QpObjDin.FieldByName('SnAbaAba').AsString = 'S',
                                          QpObjDin.FieldByName('SnAbaEsq').AsString = 'S',
                                          QpObjDin.FieldByName('SnAbaDir').AsString = 'S');
                          end;
                     'C' :
                          begin
                                CriarComboBox(gNmColuna[lNrColuna].NmAtribu,
                                          gDsComCli,
                                          QpObjDin.FieldByName('VrLeft').AsInteger,
                                          QpObjDin.FieldByName('VrTop').AsInteger,
                                          QpObjDin.FieldByName('VrWidth').AsInteger,
                                          QpObjDin.FieldByName('VrHeight').AsInteger,
                                          gNmColuna[lNrColuna].DsDomini,
                                          QpObjDin.FieldByName('NrTabOrd').AsInteger,
                                          QpObjDin.FieldByName('NrTabGri').AsInteger,
                                          QpObjDin.FieldByName('SnEnable').AsString = 'S',
                                          QpObjDin.FieldByName('DsCapGri').AsString,
                                          QpObjDin.FieldByName('SnVisibl').AsString = 'S',
                                          QpObjDin.FieldByName('SnNulo').AsString = 'S',
                                          lNrColuna, nil,
                                          QpObjDin.FieldByName('NrCompon').AsInteger,
                                          QpObjDin.FieldByName('TpDefaul').AsString,
                                          QpObjDin.FieldByName('VrDefaul').AsString,
                                          QpObjDin.FieldByName('SnAncAci').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncAba').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncEsq').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncDir').AsString = 'S');
                          end;
                     'K' :
                          begin
                                CriarCheckBox(gNmColuna[lNrColuna].NmAtribu,
                                          gDsComCli,
                                          QpObjDin.FieldByName('VrLeft').AsInteger,
                                          QpObjDin.FieldByName('VrTop').AsInteger,
                                          QpObjDin.FieldByName('VrWidth').AsInteger,
                                          QpObjDin.FieldByName('VrHeight').AsInteger,
                                          gNmColuna[lNrColuna].DsDomini,
                                          QpObjDin.FieldByName('NrTabOrd').AsInteger,
                                          QpObjDin.FieldByName('NrTabGri').AsInteger,
                                          QpObjDin.FieldByName('SnEnable').AsString = 'S',
                                          QpObjDin.FieldByName('DsCapGri').AsString,
                                          QpObjDin.FieldByName('SnVisibl').AsString = 'S',
                                          QpObjDin.FieldByName('SnNulo').AsString = 'S',
                                          QpObjDin.FieldByName('DsCaptio').AsString,
                                          lNrColuna, nil,
                                          QpObjDin.FieldByName('NrCompon').AsInteger,
                                          QpObjDin.FieldByName('TpDefaul').AsString,
                                          QpObjDin.FieldByName('VrDefaul').AsString,
                                          QpObjDin.FieldByName('NmChaCon').AsString,
                                          QpObjDin.FieldByName('SnAncAci').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncAba').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncEsq').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncDir').AsString = 'S');
                          end;
                     'R' :
                          begin
                                CriarRadioGroup(gNmColuna[lNrColuna].NmAtribu,
                                          gDsComCli,
                                          QpObjDin.FieldByName('VrLeft').AsInteger,
                                          QpObjDin.FieldByName('VrTop').AsInteger,
                                          QpObjDin.FieldByName('VrWidth').AsInteger,
                                          QpObjDin.FieldByName('VrHeight').AsInteger,
                                          gNmColuna[lNrColuna].DsDomini,
                                          QpObjDin.FieldByName('NrTabOrd').AsInteger,
                                          QpObjDin.FieldByName('NrTabGri').AsInteger,
                                          QpObjDin.FieldByName('SnEnable').AsString = 'S',
                                          QpObjDin.FieldByName('DsCapGri').AsString,
                                          QpObjDin.FieldByName('SnVisibl').AsString = 'S',
                                          QpObjDin.FieldByName('SnNulo').AsString = 'S',
                                          QpObjDin.FieldByName('DsCaptio').AsString,
                                          lNrColuna, nil,
                                          QpObjDin.FieldByName('NrCompon').AsInteger,
                                          QpObjDin.FieldByName('TpDefaul').AsString,
                                          QpObjDin.FieldByName('VrDefaul').AsString,
                                          QpObjDin.FieldByName('SnAncAci').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncAba').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncEsq').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncDir').AsString = 'S');
                          end;
                     'G' :
                          begin
                                CriarPageControl(gDsComCli,
                                          QpObjDin.FieldByName('VrLeft').AsInteger,
                                          QpObjDin.FieldByName('VrTop').AsInteger,
                                          QpObjDin.FieldByName('VrWidth').AsInteger,
                                          QpObjDin.FieldByName('VrHeight').AsInteger,
                                          QpObjDin.FieldByName('NrCompon').AsInteger,
                                          QpObjDin.FieldByName('SnAncAci').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncAba').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncEsq').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncDir').AsString = 'S');
                          end;
                     'T' :
                          begin
                                CriarTabSheet(gDsComCli,
                                               QpObjDin.FieldByName('DsCaptio').AsString,
                                               QpObjDin.FieldByName('NrCompon').AsInteger);
                          end;
                     'P' :
                          begin
                                CriarPanel(gDsComCli,
                                          QpObjDin.FieldByName('VrLeft').AsInteger,
                                          QpObjDin.FieldByName('VrTop').AsInteger,
                                          QpObjDin.FieldByName('VrWidth').AsInteger,
                                          QpObjDin.FieldByName('VrHeight').AsInteger,
                                          QpObjDin.FieldByName('NrCompon').AsInteger,
                                          QpObjDin.FieldByName('SnAncAci').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncAba').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncEsq').AsString = 'S',
                                          QpObjDin.FieldByName('SnAncDir').AsString = 'S');

                          end;
                end;
                QpObjDin.Next;
          end;

          AtribuirLabelsDosObjetos;
          ComponenteFundoClick(SbxFundo);
      finally
          QpObjDin.Close;
      end;
end;

procedure TCad0002.AtribuirLabelsDosObjetos;
var lNrCompon : Integer;
begin
      for lNrCompon := 0 to gNoCompon do
      begin
            if (gDsCompon[lNrCompon].TpCompon <> #0) and
               (Pos(gDsCompon[lNrCompon].TpCompon, 'LAB') = 0) then
            begin
                  gDsCompon[lNrCompon].LblRotul :=
                           EncontrarLabelDoObjeto(gDsCompon[lNrCompon].NmAtribu, 'B');
                  gDsCompon[lNrCompon].LblConsu :=
                           EncontrarLabelDoObjeto(gDsCompon[lNrCompon].NmAtribu, 'A');

                  EncontrarComponenteClicado(lNrCompon);
                  ColorirLabelObjeto;
            end;
      end;
end;

function TCad0002.EncontrarLabelDoObjeto(pNmAtribu : String; pTpLabel : Char) : TStaticText;
var lNrCompon : Integer;
begin
      Result    := nil;
      lNrCompon := 0;

      while (Result = nil) and (lNrCompon <= gNoCompon) do
      begin
            if (gDsCompon[lNrCompon].NmAtribu = pNmAtribu) and
               (gDsCompon[lNrCompon].TpCompon = pTpLabel) then
            begin
                  EncontrarComponenteClicado(lNrCompon);
                  Result := TStaticText(FindComponent(gDsComCli.Name));
            end;

            inc(lNrCompon);
      end;
end;

function TCad0002.EncontraAtributoLista(pNmAtribu : String) : Integer;
var lNrLinha : Integer;
begin
      lNrLinha := 0;
      while lNrLinha < LbxAtribu.Count do
      begin
            BrvString.Split(LbxAtribu.Items.Strings[lNrLinha], ' ');

            if (BrvString.BrSplitLista.Strings[0] = pNmAtribu) or
               (BrvString.BrSplitLista.Strings[1] = pNmAtribu) then
            begin
                  if (BrvString.BrSplitLista.Strings[0] = pNmAtribu) then
                  begin
                        LbxAtribu.Items.Strings[lNrLinha] := '-> ' +
                                                        LbxAtribu.Items.Strings[lNrLinha];
                  end;

                  Result   := lNrLinha;
                  lNrLinha := LbxAtribu.Count + 10;
            end;

            inc(lNrLinha);
      end;
end;

procedure TCad0002.CbxAncEsqClick(Sender: TObject);
begin
      gDsCompon[gDsComCli.Tag].SnAncEsq := CbxAncEsq.Checked;
      SbtSalvar.Enabled := True;
end;

procedure TCad0002.CbxAncAbaClick(Sender: TObject);
begin
      gDsCompon[gDsComCli.Tag].SnAncAba := CbxAncAba.Checked;
      SbtSalvar.Enabled := True;
end;

procedure TCad0002.CbxAncAciClick(Sender: TObject);
begin
      gDsCompon[gDsComCli.Tag].SnAncAci := CbxAncAci.Checked;
      SbtSalvar.Enabled := True;
end;

procedure TCad0002.CbxAncDirClick(Sender: TObject);
begin
      gDsCompon[gDsComCli.Tag].SnAncDir := CbxAncDir.Checked;
      SbtSalvar.Enabled := True;
end;

procedure TCad0002.CbxAutIncClick(Sender: TObject);
begin
      if  ChxEnabled.Tag = 0 then
      begin
            SbtSalvar.Enabled := True;
            gDsCompon[gDsComCli.Tag].SnAutInc := CbxAutInc.Checked;
            ColorirLabelObjeto;
      end;
end;

procedure TCad0002.CbxSnNuloClick(Sender: TObject);
begin
      if  ChxEnabled.Tag = 0 then
      begin
            SbtSalvar.Enabled := True;
            gDsCompon[gDsComCli.Tag].SnNulo := CbxSnNulo.Checked;
            ColorirLabelObjeto;
      end;
end;

procedure TCad0002.CbxTpObjetoChange(Sender: TObject);
begin
      if CbxTpObjeto.Tag = 1 then
      begin
            SbtSalvar.Enabled := True;
            case gDsCompon[gDsComCli.Tag].TpCompon of
                 // ComboBox
                 'C' : case CbxTpObjeto.ItemIndex of
                            1 : TrocarParaCheckBox;
                            2 : TrocarParaRadioGroup;
                       end;
                 //CheckBox
                 'K' : case CbxTpObjeto.ItemIndex of
                            0 : TrocarParaComboBox;
                            2 : TrocarParaRadioGroup;
                       end;
                 //RadioGroup
                 'R' : case CbxTpObjeto.ItemIndex of
                            0 : TrocarParaComboBox;
                            1 : TrocarParaCheckBox;
                       end;
            end;
      end;
end;

procedure TCad0002.TrocarParaComboBox;
var lDsComBox : TBrvComboBox;
    lNoComCli : Integer;
    lDsParent : TWinControl;
begin
      lNoComCli := gDsComCli.Tag;
      lDsParent := gDsComCli.Parent;
      gDsCompon[lNoComCli].TpCompon := 'C';
      gDsCompon[lNoComCli].DsCaptio := gNmColuna[gDsCompon[lNoComCli].NrAtribu].NmAtribu;

      lDsComBox                 := TBrvComboBox.Create(Self);
      lDsComBox.Tag             := lNoComCli;
      lDsComBox.Parent          := lDsParent;
      lDsComBox.Left            := gDsComCli.Left;
      lDsComBox.Top             := gDsComCli.Top;
      lDsCombox.Style           := csDropDownList;
      lDsComBox.TabStop         := False;
      lDsComBox.OnClick         := ComponenteFrenteClick;
      lDsComBox.OnDragOver      := ComponenteDragOver;
      lDsComBox.OnMouseDown     := ComponenteMouseDown;
      lDsCombox.Items.CommaText := gNmColuna[gDsCompon[lNoComCli].NrAtribu].DsDomini;

      gDsComCli.destroy;
      lDsComBox.Name            := '_' + FormatFloat('0000', lNoComCli);

      ComponenteFrenteClick(lDsComBox);

      ColorirLabelObjeto;
      ColorirObjeto;
end;

procedure TCad0002.TrocarParaRadioGroup;
var lDsRadGro : TBrvRadioGroup;
    lNoComCli : Integer;
    lDsParent : TWinControl;
    lDsCaptio : String;
begin
      lNoComCli := gDsComCli.Tag;
      lDsParent := gDsComCli.Parent;

      if gDsCompon[lNoComCli].TpCompon = 'C' then
      begin
            if gDsCompon[lNoComCli].LblRotul <> nil then
            begin
                  lDsCaptio := gDsCompon[lNoComCli].LblRotul.Caption;
            end else
            begin
                  lDsCaptio := gDsCompon[lNoComCli].DsCaptio;
            end;
      end else
      begin
            lDsCaptio := gDsCompon[lNoComCli].DsCaptio;
      end;

      gDsCompon[lNoComCli].TpCompon := 'R';
      gDsCompon[lNoComCli].DsCaptio := lDsCaptio;

      lDsRadGro                 := TBrvRadioGroup.Create(Self);
      lDsRadGro.Tag             := lNoComCli;
      lDsRadGro.Parent          := lDsParent;
      lDsRadGro.Left            := gDsComCli.Left;
      lDsRadGro.Top             := gDsComCli.Top;
      lDsRadGro.Caption         := lDsCaptio;
      lDsRadGro.Font.Style      := [fsBold];
      lDsRadGro.TabStop         := False;
      lDsRadGro.OnClick         := ComponenteFrenteClick;
      lDsRadGro.OnDragOver      := ComponenteDragOver;
      lDsRadGro.OnMouseDown     := ComponenteMouseDown;
      lDsRadGro.Items.CommaText := gNmColuna[gDsCompon[lNoComCli].NrAtribu].DsDomini;

      gDsComCli.destroy;
      lDsRadGro.Name            := '_' + FormatFloat('0000', lNoComCli);

      ComponenteFrenteClick(lDsRadGro);

      ColorirLabelObjeto;
      ColorirObjeto;
end;

procedure TCad0002.TrocarParaCheckBox;
var lDsCheBox : TCheckBox;
    lNoComCli : Integer;
    lDsParent : TWinControl;
    lDsCaptio : String;
begin
      lNoComCli := gDsComCli.Tag;
      lDsParent := gDsComCli.Parent;

      if gDsCompon[lNoComCli].TpCompon = 'C' then
      begin
            if gDsCompon[lNoComCli].LblRotul <> nil then
            begin
                  lDsCaptio := gDsCompon[lNoComCli].LblRotul.Caption;
            end else
            begin
                  lDsCaptio := gDsCompon[lNoComCli].DsCaptio;
            end;
      end else
      begin
            lDsCaptio := gDsCompon[lNoComCli].DsCaptio;
      end;

      gDsCompon[lNoComCli].TpCompon := 'K';
      gDsCompon[lNoComCli].DsCaptio := lDsCaptio;

      lDsCheBox             := TCheckBox.Create(Self);
      lDsCheBox.Tag         := lNoComCli;
      lDsCheBox.Parent      := lDsParent;
      lDsCheBox.Left        := gDsComCli.Left;
      lDsCheBox.Top         := gDsComCli.Top;
      lDsCheBox.Caption     := lDsCaptio;
      lDsCheBox.Font.Style  := [fsBold];
      lDsCheBox.TabStop     := False;
      lDsCheBox.OnClick     := ComponenteFrenteClick;
      lDsCheBox.OnDragOver  := ComponenteDragOver;
      lDsCheBox.OnMouseDown := ComponenteMouseDown;
      lDsCheBox.Caption     := lDsCaptio;

      gDsComCli.destroy;
      lDsCheBox.Name        := '_' + FormatFloat('0000', lNoComCli);

      ComponenteFrenteClick(lDsCheBox);

      ColorirLabelObjeto;
      ColorirObjeto;
end;

procedure TCad0002.ColorirLabelObjeto;
var lCrCor    : TColor;
    lSnAutInc : Boolean;
    lSnNulo   : Boolean;
begin
      lSnAutInc := False;
      lSnNulo   := False;

      if gDsCompon[gDsComCli.Tag].SnAutInc then
      begin
            lCrCor    := cCrAuto;
            lSnAutInc := True;
      end else
      begin
            if gDsCompon[gDsComCli.Tag].SnNulo then
            begin
                   lCrCor  := clBlack;
                   lSnNulo := True;
            end else
            begin
                   lCrCor := clBlue;
            end;
      end;

      if (gDsComCli is TCheckBox) then
      begin
            (gDsComCli as TCheckBox).Font.Color := lCrCor;
      end
      else if (gDsComCli is TRadioGroup) then
           begin
                 (gDsComCli as TRadioGroup).Font.Color := lCrCor;
           end
      ;

      if gDsCompon[gDsComCli.Tag].LblRotul <> nil then
      begin
            gDsCompon[gDsComCli.Tag].LblRotul.Font.Color := lCrCor;
            gDsCompon[gDsCompon[gDsComCli.Tag].LblRotul.Tag].SnAutInc := lSnAutInc;
            gDsCompon[gDsCompon[gDsComCli.Tag].LblRotul.Tag].SnNulo   := lSnNulo;
      end;
end;

procedure TCad0002.CbxVisiblClick(Sender: TObject);
var lCrCor : TColor;
begin
      if CbxVisibl.Tag = 0 then
      begin
            gDsCompon[gDsComCli.Tag].SnVisibl := CbxVisibl.Checked;
            SbtSalvar.Enabled := True;
            ColorirObjeto;
      end;
end;

procedure TCad0002.ChxEnabledClick(Sender: TObject);
begin
      if  ChxEnabled.Tag = 0 then
      begin
            SbtSalvar.Enabled := True;
            gDsCompon[gDsComCli.Tag].SnEnable := ChxEnabled.Checked;
            ColorirObjeto;
      end;
end;

procedure TCad0002.ColorirObjeto;
var lCrCor : TColor;
begin
      if gDsCompon[gDsComCli.Tag].SnVisibl then // visivel
      begin
            if gDsCompon[gDsComCli.Tag].SnEnable then
            begin
                  lCrCor := cCrHabili; // habilitado
            end else
            begin
                  lCrCor := cCrDesabi; // desabilitado
            end;
      end else
      begin // invisível
            lCrCor := clBlue;
      end;

      if gDsCompon[gDsComCli.Tag].TpCompon = 'E' then
      begin
            (gDsComCli as TEdit).Color := lCrCor;
      end
      else if gDsCompon[gDsComCli.Tag].TpCompon = 'D' then
           begin
                 (gDsComCli as TBrvEditDate).Color := lCrCor;
           end
      else if gDsCompon[gDsComCli.Tag].TpCompon = 'M' then
           begin
                (gDsComCli as TMemo).Color := lCrCor;
           end
      else if gDsCompon[gDsComCli.Tag].TpCompon = 'N' then
           begin
                (gDsComCli as TEdit).Color := lCrCor;
           end
      else if gDsCompon[gDsComCli.Tag].TpCompon = 'C' then
           begin
                (gDsComCli as TComboBox).Color := lCrCor;
           end
      else if gDsCompon[gDsComCli.Tag].TpCompon = 'R' then
           begin
                 (gDsComCli as TRadioGroup).Color := lCrCor;
           end
      else if gDsCompon[gDsComCli.Tag].TpCompon = 'K' then
           begin
                 (gDsComCli as TCheckBox).Color := lCrCor;
           end
      ;
end;

procedure TCad0002.CarregarAtributosDisponiveis;
var lNrColuna : Integer;
    lDsChaPri : TStrings;
begin
      QpAtrDis.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
          'Select * From S009 Where NmTabela = ' + #39 + gNmTabela + #39 +
                                                                   ' Order by NmAtribu');
      LbxAtribu.Items.Clear;
      SetLength(gNmColuna, QpAtrDis.RecordCount + 2);
      lNrColuna := 0;

      try
          lDsChaPri := TStringList.Create;
          DmSrvApl.BrvDicionario.ChavePrimaria(gNmTabela, lDsChaPri);

          while not QpAtrDis.Eof do
          begin
                LbxAtribu.Items.Add(QpAtrDis.FieldByName('NmAtribu').AsString + ' - ' +
                                    QpAtrDis.FieldByName('DsAtribu').AsString);

                gNmColuna[lNrColuna].NmAtribu := QpAtrDis.FieldByName('NmAtribu').AsString;
                gNmColuna[lNrColuna].DsAtribu := QpAtrDis.FieldByName('DsAtribu').AsString;
                gNmColuna[lNrColuna].TpAtribu := QpAtrDis.FieldByName('TpAtribu').AsString;
                gNmColuna[lNrColuna].TpMascar := QpAtrDis.FieldByName('TpMascar').AsString;
                gNmColuna[lNrColuna].DsMascar := QpAtrDis.FieldByName('DsMascar').AsString;
                gNmColuna[lNrColuna].VrSize   := QpAtrDis.FieldByName('TmAtribu').AsInteger;

                gNmColuna[lNrColuna].SnChaPri :=
                          Pos(QpAtrDis.FieldByName('NmAtribu').AsString,
                              lDsChaPri.Text) > 0;

                DmSrvApl.BrvDicionario.CarregarDominiosDoAtributo(
                                               gNmTabela,
                                               QpAtrDis.FieldByName('NmAtribu').AsString,
                                               gNmColuna[lNrColuna].VrDomini,
                                               gNmColuna[lNrColuna].DsDomini);

                QpAtrDis.Next;
                inc(lNrColuna);
          end;
      finally
          QpAtrDis.Close;
          FreeAndNil(lDsChaPri);
      end;
end;

procedure TCad0002.FormCloseQuery(Sender: TObject; var CanClose: Boolean);
var lTpOpcao : Integer;
begin
      inherited;

      if SbtSalvar.Enabled then
      begin
            lTpOpcao :=  MessageDlg('Salvar modificações feitas?', mtConfirmation,
                                                              [mbYes, mbNo, mbCancel], 0);

            case lTpOpcao of
                 idYes : SbtSalvarClick(SbtSalvar);
                 idCancel : CanClose := False;
            end;
      end;
end;

procedure TCad0002.FormCreate(Sender: TObject);
var lNrMarcad : Integer;
begin
  inherited;
      PgcOpcao.ActivePage := TbsOpcVis;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-= definindo marcadores de click sobre o componente -=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      for lNrMarcad := 0 to 7 do
      begin
            PnlMarcad[lNrMarcad]              := TPanel.Create(Self);
            PnlMarcad[lNrMarcad].Brush.Color  := clBlack;
            PnlMarcad[lNrMarcad].Height       := 5;
            PnlMarcad[lNrMarcad].Width        := 5;
            PnlMarcad[lNrMarcad].Color        := clBlack;
            PnlMarcad[lNrMarcad].BevelOuter   := bvNone;
            PnlMarcad[lNrMarcad].OnMouseDown  := PnlMarcadorMouseDown;
            PnlMarcad[lNrMarcad].OnEndDrag    := ShpVultoEndDrag;
      end;

      PnlMarcad[0].Cursor       := crSizeNWSE;
      PnlMarcad[0].DragCursor   := crSizeNWSE;

      PnlMarcad[1].Cursor       := crSizeNS;
      PnlMarcad[1].DragCursor   := crSizeNS;

      PnlMarcad[2].Cursor       := crSizeNESW;
      PnlMarcad[2].DragCursor   := crSizeNESW;

      PnlMarcad[3].Cursor       := crSizeWE;
      PnlMarcad[3].DragCursor   := crSizeWE;

      PnlMarcad[4].Cursor       := crSizeWE;
      PnlMarcad[4].DragCursor   := crSizeWE;

      PnlMarcad[5].Cursor       := crSizeNESW;
      PnlMarcad[5].DragCursor   := crSizeNESW;

      PnlMarcad[6].Cursor       := crSizeNS;
      PnlMarcad[6].DragCursor   := crSizeNS;

      PnlMarcad[7].Cursor       := crSizeNWSE;
      PnlMarcad[7].DragCursor   := crSizeNWSE;

      gDsComCli  := SbxFundo;
      ContornoComponente;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
end;

procedure TCad0002.FormKeyDown(Sender: TObject; var Key: Word; Shift: TShiftState);
var lSnAtuCon : Boolean;
    lNoEdit   : Integer;
begin
      if gDsComCli.Tag <> 0 then
      begin
            lSnAtuCon := False;

            if (ssShift in Shift) and (ssCtrl in Shift) then
            begin
                  case key of
                       38 : gDsComCli.Top  := gDsComCli.Top  - 4; // seta cima
                       40 : gDsComCli.Top  := gDsComCli.Top  + 4; // seta baixo
                       39 : gDsComCli.Left := gDsComCli.Left + 4; // seta direita
                       37 : gDsComCli.Left := gDsComCli.Left - 4; // seta esquerda
                  end;
                  lSnAtuCon := True;
            end
            else if ssCtrl in Shift then
                 begin
                       case key of
                            38 : gDsComCli.Top  := gDsComCli.Top  - 1; // seta cima
                            40 : gDsComCli.Top  := gDsComCli.Top  + 1; // seta baixo
                            39 : gDsComCli.Left := gDsComCli.Left + 1; // seta direita
                            37 : gDsComCli.Left := gDsComCli.Left - 1; // seta esquerda
                       end;
                       lSnAtuCon := True;
                 end
            else if ssShift in Shift then
                 begin
                       case key of
                            38 : gDsComCli.Height := gDsComCli.Height - 1; // seta cima
                            40 : gDsComCli.Height := gDsComCli.Height + 1; // seta baixo
                            39 : gDsComCli.Width  := gDsComCli.Width  + 1; // seta direita
                            37 : gDsComCli.Width  := gDsComCli.Width  - 1; // seta esquerda
                       end;
                       lSnAtuCon := True;
                 end
            else if key = 46 then //Delete
                 begin
                       if (not EdtDsCaptio.Focused) and (not EdtDsCapGri.Focused) then
                       begin
                             if MessageDlg('Confirma a exclusão deste objeto?',
                                            mtConfirmation, [mbYes, mbNo], 0) = Idyes then
                             begin
                                   gDsCompon[gDsComCli.Tag].SnDelete := True;
                                   ExcluirComponente;
                                   EncontrarComponenteClicado(0);
                                   ContornoComponente;
                                   ComponenteFundoClick(SbxFundo);
                                   key := 0; // cancelando evento para não causar violação
                             end;
                       end;
                 end;

            if (lSnAtuCon) and (key >= 37) and (key <= 40)  then
            begin
                  EdtVlLeft.BrAsInteger   := gDsComCli.Left;
                  EdtVlTop.BrAsInteger    := gDsComCli.Top;
                  EdtVlWidth.BrAsInteger  := gDsComCli.Width;
                  EdtVlHeigth.BrAsInteger := gDsComCli.Height;

                  if Pos(gDsCompon[gDsComCli.Tag].TpCompon, 'L') <> 0 then
                  begin
                        ComponenteFrenteClick(gDsComCli);
                  end else
                  begin
                        ContornoComponente;
                  end;
            end;
      end;

      if key = 27 then
      begin
            if (PnlBarra.Enabled = False) and (High(gDsEdtOrd)  > 0) then
            begin
                  for lNoEdit := 0 to High(gDsEdtOrd) do
                  begin
                        FreeAndNil(gDsEdtOrd[lNoEdit]);
                  end;

                  PnlBarra.Enabled  := True;

                  gNoTabOrd := -100;
                  LbxAtribu.Enabled := True;

                  EncontrarComponenteClicado(0);
            end;
      end;
end;

procedure TCad0002.ExcluirComponente;
var lNoMarcad : Byte;
    lNoCompon : Integer;
begin
      for lNoMarcad := 0 to 7 do
      begin
            PnlMarcad[lNoMarcad].Visible := False;
            PnlMarcad[lNoMarcad].Parent  := SbxFundo;
      end;

      lNoCompon := gDsComCli.Tag;

      if (gDsComCli is TPanel) or (gDsComCli is TTabSheet) or
         (gDsComCli is TScrollBox) then
      begin
            ExcluirComponentesFilhos(lNoCompon);
      end;

      EncontrarComponenteClicado(lNoCompon);

      if gDsComCli.Tag <> 0 then
      begin
            if (gDsComCli is TTabSheet) and
               (((gDsComCli as TTabSheet).PageControl).PageCount = 1) then
            begin
                  gDsCompon[(gDsComCli as TTabSheet).
                                              PageControl.Tag].SnDelete := True;
                  gDsCompon[gDsComCli.Tag].SnDelete := True;
                  (gDsComCli as TTabSheet).PageControl.Destroy;
            end else
            begin
                  LiberarColunaInclusao;
            end;
      end;
end;

procedure TCad0002.LiberarColunaInclusao;
var lDsColuna : String;
    lNoComLab : Integer;
    lNrCompon : Integer;
begin
      if Pos(gDsCompon[gDsComCli.Tag].TpCompon, 'LBATGP') = 0 then
      begin
            lDsColuna := LbxAtribu.Items.Strings[gDsCompon[gDsComCli.Tag].NrAtribu];
            Delete(lDsColuna, 1, 3);
            LbxAtribu.Items.Strings[gDsCompon[gDsComCli.Tag].NrAtribu] := lDsColuna;

            if gDsCompon[gDsComCli.Tag].LblRotul <> nil then
            begin
                  lNoComLab := (gDsCompon[gDsComCli.Tag].LblRotul as TStaticText).Tag;
                  (gDsCompon[gDsComCli.Tag].LblRotul as TStaticText).Font.Color := clRed;
                  (gDsCompon[gDsComCli.Tag].LblRotul as TStaticText).BorderStyle := sbsNone;
                  (gDsCompon[gDsComCli.Tag].LblRotul as TStaticText).Refresh;
                  gDsCompon[lNoComlab].TpCompon := 'L';
                  gDsCompon[lNoComlab].NmAtribu := '';
                  gDsCompon[lNoComlab].NmAtribu := '';
                  gDsCompon[gDsComCli.Tag].LblRotul := nil;
            end;

            if gDsCompon[gDsComCli.Tag].LblConsu <> nil then
            begin
                  lNoComLab := (gDsCompon[gDsComCli.Tag].LblConsu as TStaticText).Tag;
                  (gDsCompon[gDsComCli.Tag].LblConsu as TStaticText).Font.Color := clRed;
                  gDsCompon[lNoComlab].TpCompon     := 'L';
                  gDsCompon[lNoComlab].NmAtribu     := '';
                  gDsCompon[gDsComCli.Tag].LblConsu := nil;
            end;
      end
      else if gDsCompon[gDsComCli.Tag].TpCompon = 'B' then // excluiu label de rótulo
           begin
                 for lNrCompon := 0 to gNoCompon do
                 begin
                       if gDsCompon[lNrCompon].NrAtribu =
                                                    gDsCompon[gDsComCli.Tag].NrAtribu then
                       begin
                             gDsCompon[lNrCompon].LblRotul := nil;
                       end;
                 end;
           end
      else if gDsCompon[gDsComCli.Tag].TpCompon = 'A' then // excluiu label de consulta
           begin
                 for lNrCompon := 0 to gNoCompon do
                 begin
                       if gDsCompon[lNrCompon].NrAtribu =
                                                    gDsCompon[gDsComCli.Tag].NrAtribu then
                       begin
                             gDsCompon[lNrCompon].LblConsu := nil;
                       end;
                 end;
           end
      ;

      gDsCompon[gDsComCli.Tag].SnDelete := True;
      gDsComCli.Destroy;
end;

procedure TCad0002.ExcluirComponentesFilhos(pNoComPai : Integer);
var lNoCompon : Integer;
    lDsColuna : String;
begin
      for lNoCompon := 1 to gNoCompon do
      begin
            if (gDsCompon[lNoCompon].NrComPai = pNoComPai) and
               (not gDsCompon[lNoCompon].SnDelete) then
            begin
                  EncontrarComponenteClicado(lNoCompon);

                  if (gDsComCli is TPanel) or (gDsComCli is TTabSheet) then
                  begin
                        ExcluirComponentesFilhos(lNoCompon);
                        EncontrarComponenteClicado(lNoCompon);
                  end;

                  LiberarColunaInclusao;
            end;
      end;
end;

procedure TCad0002.PnlMarcadorMouseDown(Sender: TObject; Button: TMouseButton;
                                      Shift: TShiftState; X, Y: Integer);
begin
      if gDsComCli.Tag <> 0 then
      begin
            ShpVulto.Parent := gDsComCli.Parent;
            ShpVulto.Top    := gDsComCli.Top;
            ShpVulto.Left   := gDsComCli.Left;
            ShpVulto.Width  := gDsComCli.Width;
            ShpVulto.Height := gDsComCli.Height;
            ShpVulto.BringToFront;
            ShpVulto.Visible := True;

            (Sender as TPanel).BeginDrag(False);
            (Sender as TPanel).Tag := 1;
      end;
end;

procedure TCad0002.FormMouseMove(Sender: TObject; Shift: TShiftState; X, Y: Integer);
var lNoEdit : Integer;
begin
      if PnlBarra.Enabled = False then
      begin
            if gNoTabOrd > High(gDsEdtOrd) then
            begin
                  for lNoEdit := 0 to High(gDsEdtOrd) do
                  begin
                        gDsCompon[gDsEdtOrd[lNoEdit].Tag].NrTabOrd :=
                                                        StrToInt(gDsEdtOrd[lNoEdit].Text);
                        FreeAndNil(gDsEdtOrd[lNoEdit]);
                  end;

                  PnlBarra.Enabled  := True;
                  PgcInspec.Enabled := True;
                  LbxAtribu.Enabled := True;

                  gNoTabOrd := -100;
                  EncontrarComponenteClicado(0);
            end;
      end;
end;

procedure TCad0002.LbxAtribuMouseDown(Sender: TObject; Button: TMouseButton;
  Shift: TShiftState; X, Y: Integer);
begin
      LbxAtribu.BeginDrag(False);
end;

procedure TCad0002.EdtDsCapGriKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
begin
      SbtSalvar.Enabled := True;
      gDsCompon[gDsComCli.Tag].DsCapGri := (Sender as TEdit).Text;
end;

procedure TCad0002.EdtDsCaptioKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
var lDsLabel  : TLabel;
    lDsTabShe : TTabSheet;
    lDsCheBox : TCheckBox;
    lDsRadGro : TRadioGroup;
begin
      SbtSalvar.Enabled := True;
      gDsCompon[gDsComCli.Tag].DsCaptio := (Sender as TEdit).Text;

      case gDsCompon[gDsComCli.Tag].TpCompon of
           'L', 'B' :
                 begin
                       lDsLabel := TLabel(FindComponent(gDsComCli.Name));
                       lDsLabel.Caption := (Sender as TEdit).Text;
                       ComponenteFrenteClick(TWinControl(
                                          FindComponent(gDsComCli.Name)));
                 end;
           'T' : begin
                       lDsTabShe := TTabSheet(FindComponent(gDsComCli.Name));
                       lDsTabShe.Caption := (Sender as TEdit).Text;
                 end;
           'K' : begin
                       lDsCheBox := TCheckBox(FindComponent(gDsComCli.Name));
                       lDsCheBox.Caption := (Sender as TEdit).Text;
                 end;
           'R' : begin
                       lDsRadGro := TRadioGroup(FindComponent(gDsComCli.Name));
                       lDsRadGro.Caption := (Sender as TEdit).Text;
                 end;
      end;
end;

procedure TCad0002.EdtVlHeigthChange(Sender: TObject);
begin
      gDsComCli.Height  := EdtVlHeigth.BrAsInteger;
      SbtSalvar.Enabled := True;
      ContornoComponente;
end;

procedure TCad0002.EdtVlLeftChange(Sender: TObject);
begin
      gDsComCli.Left    := EdtVlLeft.BrAsInteger;
      SbtSalvar.Enabled := True;
      ContornoComponente;
end;

procedure TCad0002.EdtVlTopChange(Sender: TObject);
begin
      gDsComCli.Top     := EdtVlTop.BrAsInteger;
      SbtSalvar.Enabled := True;
      ContornoComponente;
end;

procedure TCad0002.EdtVlWidthChange(Sender: TObject);
begin
      gDsComCli.Width   := EdtVlWidth.BrAsInteger;
      SbtSalvar.Enabled := True;
      ContornoComponente;
end;

procedure TCad0002.EncontrarComponenteClicado(pNoTag : Integer);
begin
      if pNoTag = 0 then
      begin
            gDsComCli :=  SbxFundo;
      end else
      begin
            gDsComCli := TWinControl(FindComponent('_' + FormatFloat('0000', pNoTag)));

            if gDsComCli = nil then
            begin
                  showmessage('error');
            end;
      end;
end;

procedure TCad0002.SbtConsulClick(Sender: TObject);
var lNrComCli : Integer;
begin
      Cai0006 := TCai0006.Create(Self);

      try
          Cai0006.CarregarFormConfiguracao(gNmTabela, gDsCompon[gDsComCli.Tag].NmAtribu,
                                           gDsCompon[gDsComCli.Tag].NrQueCon,
                                           gDsCompon[gDsComCli.Tag].DsSepCon,
                                           gDsCompon[gDsComCli.Tag].DsAtrCon,
                                           gDsCompon[gDsComCli.Tag].DsRetCon);

          if Cai0006.ShowModal = MrOk then
          begin
                SbtSalvar.Enabled := True;
                lNrComCli := gDsComCli.Tag;
                if Cai0006.gDsAtrDis = 'Excluir' then
                begin
                      gDsCompon[lNrComCli].DsRetCon := '';
                      gDsCompon[lNrComCli].DsAtrCon := '';
                      gDsCompon[lNrComCli].DsSepCon := '';
                      gDsCompon[lNrComCli].NrQueCon := 0;

                      if gDsCompon[lNrComCli].TpCompon = 'N' then
                      begin
                           (gDsComCli as TBrvEditNum).BrFunctionButton := TpCalculadora;
                      end else
                      begin
                           (gDsComCli as TBrvEdit).BrVisibleButton := False;
                      end;

                      gDsCompon[gDsCompon[lNrComCli].LblConsu.Tag].SnDelete := True;
                      FreeAndNil(gDsCompon[lNrComCli].LblConsu);
                      gDsCompon[lNrComCli].LblConsu := nil;
                end else
                begin
                      if (gDsComCli is TBrvEditNum) then
                      begin
                            (gDsComCli as TBrvEditNum).BrVisibleButton  := True;
                            (gDsComCli as TBrvEditNum).BrFunctionButton := TpConsulta;
                      end else
                      begin
                            if (gDsComCli is TBrvEdit) then
                            begin
                                 (gDsComCli as TBrvEdit).BrVisibleButton  := True;
                                 (gDsComCli as TBrvEdit).BrFunctionButton := VeConsulta;
                            end;
                      end;

                      gDsCompon[lNrComCli].DsRetCon := Cai0006.gDsRetCon;
                      gDsCompon[lNrComCli].DsAtrCon := Cai0006.gDsAtrDis;
                      gDsCompon[lNrComCli].DsSepCon :=
                            Cai0006.CbxSepAnt.Values.Strings[Cai0006.CbxSepAnt.ItemIndex];
                      gDsCompon[lNrComCli].NrQueCon := StrToInt(
                            Cai0006.CbxConDis.Values.Strings[Cai0006.CbxConDis.ItemIndex]);
                      gDsCompon[lNrComCli].NmChaCon := Cai0006.gNmChaCon;

                      if gDsCompon[lNrComCli].LblConsu = nil then
                      begin
                            inc(gNoCompon);

                            gDsCompon[lNrComCli].LblConsu :=
                                 CriarLabel('Retorno ' + gDsCompon[lNrComCli].NmAtribu,
                                            'A',
                                            False, // nulo
                                            False, // chave primária
                                            gDsCompon[lNrComCli].NmAtribu,
                                            gDsComCli.Left + gDsComCli.Width + 5,
                                            gDsComCli.Top,
                                            21, // height
                                            40, // width
                                            gDsComCli.Parent,
                                            0, // TabOrder Grade
                                            'Retorno ' + gDsCompon[lNrComCli].NmAtribu,
                                            gDsCompon[lNrComCli].NrAtribu,
                                            gNoCompon,
                                            '', // tipo default
                                            '',  // valor default
                                            gDsCompon[lNrComCli].NrQueCon,
                                            gDsCompon[lNrComCli].NmChaCon,
                                            True, False, True, False);
                      end else
                      begin
                            EncontrarComponenteClicado(gDsCompon[lNrComCli].LblConsu.Tag);
                            gDsCompon[gDsComCli.Tag].NrQueCon :=
                                                     gDsCompon[lNrComCli].NrQueCon;
                            gDsCompon[gDsComCli.Tag].NmChaCon :=
                                                     gDsCompon[lNrComCli].NmChaCon;
                      end;
                      gDsCompon[gDsComCli.Tag].DsAtrCon := gDsCompon[lNrComCli].DsAtrCon;
                      gDsCompon[gDsComCli.Tag].DsSepCon := gDsCompon[lNrComCli].DsSepCon;
                      EncontrarComponenteClicado(lNrComCli);
                      ContornoComponente;
                end;

                LblNrConsul.Caption           := IntToStr(gDsCompon[lNrComCli].NrQueCon);
          end;
      finally
          FreeAndNil(Cai0006);
      end;
end;

procedure TCad0002.SbtDefaulClick(Sender: TObject);
begin
      Cai0004 := TCai0004.Create(Self);

      try
          Cai0004.CbxTpOperac.BrvPosicionarValor(gDsCompon[gDsComCli.Tag].TpDefaul);

          if not Cai0004.CbxVrDefaul.BrvPosicionarValor(gDsCompon[gDsComCli.Tag].VrDefaul)
                                                                                      then
          begin
               Cai0004.CbxVrDefaul.BrvPosicionarValor('<%FIXO%>');
               Cai0004.EdtVrDefaul.Text := gDsCompon[gDsComCli.Tag].VrDefaul;
               Cai0004.LblVrFixo.Font.Color := clBlue;
          end;

          if Cai0004.ShowModal = MrOk then
          begin
                SbtSalvar.Enabled := True;
                gDsCompon[gDsComCli.Tag].TpDefaul := Cai0004.gTpDefaul;
                gDsCompon[gDsComCli.Tag].VrDefaul := Cai0004.gVrDefaul;
                LblDsDefaul.Caption               :=
                                 gDsCompon[gDsComCli.Tag].TpDefaul + '-' +
                                 gDsCompon[gDsComCli.Tag].VrDefaul;
          end;
      finally
          FreeAndNil(Cai0004);
      end;
end;

procedure TCad0002.SbtForDinClick(Sender: TObject);
begin
      if SbtSalvar.Enabled then
      begin
            if MessageDlg('O projeto ainda não foi salvo. É necessário salvar antes de ' +
                          'executar.' + #13#13 + 'Deseja salvar agora?',
                          mtConfirmation, [mbYes, mbNo], 0) = IdYes then
            begin
                  SbtSalvarClick(SbtSalvar);
            end;
      end;

      if not SbtSalvar.Enabled then
      begin
            Cad0003:= TCad0003.Create(Self);

            try
                Cad0003.FormStyle := fsNormal;
                Cad0003.Visible   := False;
                Cad0003.MontarFormularioCadastro(gNrForDin, '');
                Cad0003.ShowModal;
            finally
                FreeAndNil(Cad0003);
            end;
      end;
end;

procedure TCad0002.SbtOrdemClick(Sender: TObject);
var lNrLinha : Integer;
begin
      Cai0005 := TCai0005.Create(Self);

      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-=-= Carregando a lista de atributos para ordenção
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          Cai0005.LbxAtribu.Items.Clear;
          Cai0005.LbxAtribu.Values.Clear;

          CdsOrdIni.IndexName := 'Key2';
          CdsOrdIni.SetKey;
          CdsOrdIni.First;

          while not CdsOrdIni.Eof do
          begin
                Cai0005.LbxAtribu.Items.Add(CdsOrdIni.FieldByName('NmAtribu').AsString +
                                            ' - ' +
                                            CdsOrdIni.FieldByName('DsAtribu').AsString);
                Cai0005.LbxAtribu.Values.Add(CdsOrdIni.FieldByName('NmAtribu').AsString);
                if CdsOrdIni.FieldByName('NmTabela').AsString <> '' then
                begin
                      Cai0005.LbxAtribu.Checked[Cai0005.LbxAtribu.Count -1] := True;
                end;

                CdsOrdIni.Next;
          end;

          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-=-= Salvando a lista de atributos para ordenção
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          if Cai0005.ShowModal = MrOk then
          begin
                SbtSalvar.Enabled := True;

                CdsOrdIni.IndexName := 'Key1';
                CdsOrdIni.SetKey;

                for lNrLinha := 0 to Cai0005.LbxAtribu.Count -1 do
                begin
                      if CdsOrdIni.FindKey(
                                       [Cai0005.LbxAtribu.Values.Strings[lNrLinha]]) then
                      begin
                            CdsOrdIni.Edit;
                            if Cai0005.LbxAtribu.Checked[lNrLinha] then
                            begin
                                  CdsOrdIni.FieldByName('NmTabela').AsString := gNmTabela;
                            end else
                            begin
                                  CdsOrdIni.FieldByName('NmTabela').AsString := '';
                            end;
                            CdsOrdIni.FieldByName('NrOrdem').AsInteger := lNrLinha;
                            CdsOrdIni.Post;
                      end;
                end;
          end;
      finally
          FreeAndNil(Cai0005);
      end;
end;

procedure TCad0002.SbtSalvarClick(Sender: TObject);
var lConexao   : TSDmSisClient;
    lDsResult  : String;
    lCdsSalva  : TClientDataSet;
    lCdsSalCon : TClientDataSet;
    lCdsSalRet : TClientDataSet;
    lDsOrdIni  : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      lCdsSalva  := TClientDataSet.Create(Self);
      lCdsSalCon := TClientDataSet.Create(Self);
      lCdsSalRet := TClientDataSet.Create(Self);

      try
          //=-=-=-=-= monstando string da ordenação inicial
            CdsOrdIni.IndexName := 'Key2';
            CdsOrdIni.SetKey;
            CdsOrdIni.First;
            lDsOrdIni := '';
            while not CdsOrdIni.Eof do
            begin
                  if CdsOrdIni.FieldByName('NmTabela').AsString <> '' then
                  begin
                        lDsOrdIni := lDsOrdIni +
                                     CdsOrdIni.FieldByName('NmAtribu').AsString +
                                     '@';
                  end;

                  CdsOrdIni.Next;
            end;
          //=-=-=-=-=
          CarregarDadosDataSetSalvar(lCdsSalva, lCdsSalCon, lCdsSalRet);

          lConexao.SalvarObjetosFormDinamico(DmSrvApl.BrvDicionario.Credencial,  lDsResult,
                                             IntToStr(gNrForDin), lCdsSalva.Data,
                                             lDsOrdIni, gNmTabela, lCdsSalCon.Data, Name,
                                             lCdsSalRet.Data);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          SbtSalvar.Enabled := False;
      finally
          FreeAndNil(lConexao);
          FreeAndNil(lCdsSalva);
      end;

      inherited;
end;

procedure TCad0002.CarregarDadosDataSetSalvar(pCdsSalva  : TClientDataSet;
                                              pCdsSalCon : TClientDataSet;
                                              pCdsSalRet : TClientDataSet);
var lNrCompon : Integer;
begin
      CriarTabelaSalvar(pCdsSalva);
      CriarTabelaSalvarConsulta(pCdsSalCon);
      CriarTabelaSalvarRetornoConsulta(pCdsSalRet);

      for lNrCompon := 1 to gNoCompon do
      begin
            if (gDsCompon[lNrCompon].TpCompon <> #0) and
               (not gDsCompon[lNrCompon].SnDelete) then
            begin
                  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  // =-=-= Salvando dados principais
                  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  pCdsSalva.Append;
                  pCdsSalva.FieldByName('NrForDin').AsInteger := gNrForDin;
                  pCdsSalva.FieldByName('NrCompon').AsInteger := lNrCompon;
                  pCdsSalva.FieldByName('NmAtribu').AsString  :=
                                                    gDsCompon[lNrCompon].NmAtribu;
                  pCdsSalva.FieldByName('NrQueCon').AsInteger :=
                                                    gDsCompon[lNrCompon].NrQueCon;
                  pCdsSalva.FieldByName('NmChaCon').AsString  :=
                                                    gDsCompon[lNrCompon].NmChaCon;
                  pCdsSalva.FieldByName('DsCaptio').AsString  :=
                                                    gDsCompon[lNrCompon].DsCaptio;
                  pCdsSalva.FieldByName('DsCapGri').AsString  :=
                                                    gDsCompon[lNrCompon].DsCapGri;
                  pCdsSalva.FieldByName('NrTabOrd').AsInteger :=
                                                    gDsCompon[lNrCompon].NrTabOrd;
                  pCdsSalva.FieldByName('NrTabGri').AsInteger :=
                                                    gDsCompon[lNrCompon].NrTabGri;
                  pCdsSalva.FieldByName('TpCompon').AsString  :=
                                                    gDsCompon[lNrCompon].TpCompon;
                  pCdsSalva.FieldByName('TpDefaul').AsString  :=
                                                    gDsCompon[lNrCompon].TpDefaul;
                  pCdsSalva.FieldByName('VrDefaul').AsString  :=
                                                    gDsCompon[lNrCompon].VrDefaul;
                  pCdsSalva.FieldByName('NrComPai').AsInteger :=
                                                    gDsCompon[lNrCompon].NrComPai;
                  pCdsSalva.FieldByName('NmTabela').AsString  := gNmTabela;

                  pCdsSalva.FieldByName('SnAutinc').AsString  :=
                                        BooleanToStr(gDsCompon[lNrCompon].SnAutInc);
                  pCdsSalva.FieldByName('SnNulo').AsString    :=
                                        BooleanToStr(gDsCompon[lNrCompon].SnNulo);
                  pCdsSalva.FieldByName('SnEnable').AsString  :=
                                        BooleanToStr(gDsCompon[lNrCompon].SnEnable);
                  pCdsSalva.FieldByName('SnVisibl').AsString  :=
                                        BooleanToStr(gDsCompon[lNrCompon].SnVisibl);
                  pCdsSalva.FieldByName('SnAncAci').AsString  :=
                                        BooleanToStr(gDsCompon[lNrCompon].SnAncAci);
                  pCdsSalva.FieldByName('SnAncAba').AsString  :=
                                        BooleanToStr(gDsCompon[lNrCompon].SnAncAba);
                  pCdsSalva.FieldByName('SnAncEsq').AsString  :=
                                        BooleanToStr(gDsCompon[lNrCompon].SnAncEsq);
                  pCdsSalva.FieldByName('SnAncDir').AsString  :=
                                        BooleanToStr(gDsCompon[lNrCompon].SnAncDir);

                  EncontrarComponenteClicado(lNrCompon);

                  pCdsSalva.FieldByName('VrHeight').AsInteger := gDsComCli.Height;
                  pCdsSalva.FieldByName('VrLeft').AsInteger   := gDsComCli.Left;
                  pCdsSalva.FieldByName('VrTop').AsInteger    := gDsComCli.Top;
                  pCdsSalva.FieldByName('VrWidth').AsInteger  := gDsComCli.Width;

                  pCdsSalva.Post;
                  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  // =-=-= Salvando dados da consulta
                  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  if gDsCompon[lNrCompon].NrQueCon <> 0 then
                  begin
                        SalvarAtributosDisplayConsulta(pCdsSalCon, lNrCompon);
                        SalvarAtributosRestornoConsulta(pCdsSalRet, lNrCompon);
                  end;
            end;
      end;

      ComponenteFundoClick(SbxFundo);
end;

procedure TCad0002.SalvarAtributosDisplayConsulta(pCdsSalCon : TClientDataset;
                                                  pNrCompon  : Integer);
var lNrAtribu : Integer;
    lDsAtribu : String;
begin
      BrvString.Split(gDsCompon[pNrCompon].DsAtrCon, '@');

      for lNrAtribu := 0 to BrvString.BrSplitLista.Count -1 do
      begin
            lDsAtribu := BrvString.BrSplitLista.Strings[lNrAtribu];
            if lDsAtribu <> '' then
            begin
                  pCdsSalCon.Append;
                  pCdsSalCon.FieldByName('NrForDin').AsInteger := gNrForDin;
                  pCdsSalCon.FieldByName('NrCompon').AsInteger := pNrCompon;
                  pCdsSalCon.FieldByName('NrOrdCon').AsInteger  := lNrAtribu;
                  pCdsSalCon.FieldByName('DsSepAnt').AsString  :=
                                                     gDsCompon[pNrCompon].DsSepCon;

                  pCdsSalCon.FieldByName('NmTabela').AsString  :=
                             Trim(Copy(lDsAtribu, 1, Pos('.', lDsAtribu) -1));
                  pCdsSalCon.FieldByName('NmAtribu').AsString  :=
                             Trim(Copy(lDsAtribu, Pos('.', lDsAtribu) +1, 200));
                  pCdsSalCon.Post;
            end;
      end;
end;

procedure TCad0002.SalvarAtributosRestornoConsulta(pCdsSalRet : TClientDataSet;
                                                   pNrCompon  : Integer);
var lNrRetorn : Integer;
    lDsLinha  : String;
begin
      BrvString.Split(gDsCompon[pNrCompon].DsRetCon, '@');

      for lNrRetorn := 0 to BrvString.BrSplitLista.Count -1 do
      begin
            lDsLinha := BrvString.BrSplitLista.Strings[lNrRetorn];
            if lDsLinha <> '' then
            begin
                  BrvString2.Split(lDsLinha, ' = ');

                  pCdsSalRet.Append;
                  pCdsSalRet.FieldByName('NrForDin').AsInteger := gNrForDin;
                  pCdsSalRet.FieldByName('NrCompon').AsInteger := pNrCompon;
                  pCdsSalRet.FieldByName('NrSequen').AsInteger := lNrRetorn;

                  BrvString3.Split(BrvString2.BrSplitLista.Strings[0], '.');
                  pCdsSalRet.FieldByName('NmTabDes').AsString  :=
                                                       BrvString3.BrSplitLista.Strings[0];
                  pCdsSalRet.FieldByName('NmAtrDes').AsString  :=
                                                       BrvString3.BrSplitLista.Strings[1];

                  BrvString3.Split(BrvString2.BrSplitLista.Strings[1], '.');
                  pCdsSalRet.FieldByName('NmTabOri').AsString  :=
                                                       BrvString3.BrSplitLista.Strings[0];
                  pCdsSalRet.FieldByName('NmAtrOri').AsString  :=
                                                       BrvString3.BrSplitLista.Strings[1];

                  pCdsSalRet.Post;
            end;
      end;
end;

function TCad0002.BooleanToStr(pVrBoolea : Boolean) : String;
begin
      if pVrBoolea then
      begin
            Result := 'S';
      end else
      begin
            Result := 'N';
      end;
end;

procedure TCad0002.CriarTabelaSalvarConsulta(pCdsSalCon : TClientDataSet);
begin
      pCdsSalCon.Close;
      pCdsSalCon.FieldDefs.Clear;

      pCdsSalCon.FieldDefs.Add('NrForDin',   ftInteger, 0, False);
      pCdsSalCon.FieldDefs.Add('NrCompon',   FtInteger, 0, False);
      pCdsSalCon.FieldDefs.Add('NrOrdCon',   FtInteger, 0, False);
      pCdsSalCon.FieldDefs.Add('NmTabela',   ftString, 15, False);
      pCdsSalCon.FieldDefs.Add('NmAtribu',   ftString, 08, False);
      pCdsSalCon.FieldDefs.Add('DsSepAnt',   ftString, 05, False);

      pCdsSalCon.IndexDefs.Clear;
      pCdsSalCon.IndexDefs.Add('Key1', 'NrForDin;NrCompon;NrOrdCon', [ixPrimary,ixUnique]);

      pCdsSalCon.CreateDataSet;

      pCdsSalCon.IndexName := 'Key1';
      pCdsSalCon.SetKey;
end;

procedure TCad0002.CriarTabelaSalvarRetornoConsulta(pCdsSalRet : TClientDataSet);
begin
      pCdsSalRet.Close;
      pCdsSalRet.FieldDefs.Clear;

      pCdsSalRet.FieldDefs.Add('NrForDin',   ftInteger, 0, False);
      pCdsSalRet.FieldDefs.Add('NrCompon',   FtInteger, 0, False);
      pCdsSalRet.FieldDefs.Add('NrSequen',   FtInteger, 0, False);
      pCdsSalRet.FieldDefs.Add('NmTabOri',   ftString, 15, False);
      pCdsSalRet.FieldDefs.Add('NmAtrOri',   ftString, 08, False);
      pCdsSalRet.FieldDefs.Add('NmTabDes',   ftString, 15, False);
      pCdsSalRet.FieldDefs.Add('NmAtrDes',   ftString, 08, False);

      pCdsSalRet.IndexDefs.Clear;
      pCdsSalRet.IndexDefs.Add('Key1', 'NrForDin;NrCompon;NrSequen', [ixPrimary,ixUnique]);

      pCdsSalRet.CreateDataSet;

      pCdsSalRet.IndexName := 'Key1';
      pCdsSalRet.SetKey;
end;

procedure TCad0002.CriarTabelaSalvar(pCdsSalva : TClientDataSet);
begin
      pCdsSalva.Close;
      pCdsSalva.FieldDefs.Clear;

      pCdsSalva.FieldDefs.Add('NrForDin',   ftInteger, 0, False);
      pCdsSalva.FieldDefs.Add('NrCompon',   FtInteger, 0, False);
      pCdsSalva.FieldDefs.Add('NmAtribu',   FtString,  8, False);
      pCdsSalva.FieldDefs.Add('NrQueCon',   ftInteger, 0, False);
      pCdsSalva.FieldDefs.Add('NmChaCon',   FtString, 30, False);
      pCdsSalva.FieldDefs.Add('DsCaptio',   ftString, 99, False);
      pCdsSalva.FieldDefs.Add('DsCapGri',   ftString, 99, False);
      pCdsSalva.FieldDefs.Add('NrTabOrd',   ftInteger, 0, False);
      pCdsSalva.FieldDefs.Add('NrTabGri',   ftInteger, 0, False);
      pCdsSalva.FieldDefs.Add('SnEnable',   ftString,  1, False);
      pCdsSalva.FieldDefs.Add('SnVisibl',   ftString,  1, False);
      pCdsSalva.FieldDefs.Add('SnAncAci',   ftString,  1, False);
      pCdsSalva.FieldDefs.Add('SnAncAba',   ftString,  1, False);
      pCdsSalva.FieldDefs.Add('SnAncEsq',   ftString,  1, False);
      pCdsSalva.FieldDefs.Add('SnAncDir',   ftString,  1, False);
      pCdsSalva.FieldDefs.Add('TpCompon',   ftString,  1, False);
      pCdsSalva.FieldDefs.Add('VrHeight',   ftInteger, 0, False);
      pCdsSalva.FieldDefs.Add('VrLeft',     ftInteger, 0, False);
      pCdsSalva.FieldDefs.Add('VrTop',      ftInteger, 0, False);
      pCdsSalva.FieldDefs.Add('VrWidth',    ftInteger, 0, False);
      pCdsSalva.FieldDefs.Add('TpDefaul',   ftString,  5, False);
      pCdsSalva.FieldDefs.Add('VrDefaul',   ftString, 99, False);
      pCdsSalva.FieldDefs.Add('NrComPai',   ftInteger, 0, False);
      pCdsSalva.FieldDefs.Add('SnNulo',     ftString,  1, False);
      pCdsSalva.FieldDefs.Add('NmTabela',   ftString, 15, False);
      pCdsSalva.FieldDefs.Add('SnAutInc',   ftString,  1, False);

      pCdsSalva.IndexDefs.Clear;
      pCdsSalva.IndexDefs.Add('Key1', 'NrForDin;NrCompon', [ixPrimary,ixUnique]);

      pCdsSalva.CreateDataSet;

      pCdsSalva.IndexName := 'Key1';
      pCdsSalva.SetKey;
end;

procedure TCad0002.SbtTabGriClick(Sender: TObject);
begin
      CarregarTabOrderGrid(True);
      SbtSalvar.Enabled := True;
end;

procedure TCad0002.CarregarTabOrderGrid(lSnShow : Boolean);
var lNoCompon  : Integer;
    lDsLinha   : String;
    lNoTabOrd  : Integer;
    lLbxTabOrd : TListBox;
    lSnAtuali  : Boolean;
begin
      lLbxTabOrd := TListBox.Create(Self);

      try
          lLbxTabOrd.Visible := False;
          lLbxTabOrd.Parent  := Self;

          lLbxTabOrd.Items.Clear;
          lLbxTabOrd.Sorted  := True;

          for lNoCompon := 1 to gNoCompon do
          begin
                if (Trim(gDsCompon[lNoCompon].TpCompon) <> '')  and
                   (Pos(gDsCompon[lNoCompon].TpCompon, 'LBTGPUOSF') = 0)  and
                   (not gDsCompon[lNoCompon].SnDelete) then
                begin
                      if gDsCompon[lNoCompon].TpCompon = 'A' then
                      begin
                            if gDsCompon[lNoCompon].DsCaptio = '' then
                            begin
                                  lLbxTabOrd.Items.Add(FormatFloat('000',
                                       gDsCompon[lNoCompon].NrTabGri) + ' ' +
                                       gDsCompon[lNoCompon].DsCapGri  + ';' +
                                       FormatFloat('000', lNoCompon));
                            end else
                            begin
                                  lLbxTabOrd.Items.Add(FormatFloat('000',
                                       gDsCompon[lNoCompon].NrTabGri) + ' ' +
                                       gDsCompon[lNoCompon].DsCaptio + ';' +
                                       FormatFloat('000', lNoCompon));
                            end;
                      end else
                      begin
                            if gDsCompon[lNoCompon].DsCapGri = '' then
                            begin
                                  lLbxTabOrd.Items.Add(FormatFloat('000',
                                       gDsCompon[lNoCompon].NrTabGri)   + ' ' +
                                       gDsCompon[lNoCompon].NmAtribu    + ';' +
                                       FormatFloat('000', lNoCompon));
                            end else
                            begin
                                  lLbxTabOrd.Items.Add(FormatFloat('000',
                                       gDsCompon[lNoCompon].NrTabGri)   + ' ' +
                                       gDsCompon[lNoCompon].DsCapGri    + ';' +
                                       FormatFloat('000', lNoCompon));
                            end;
                      end;
                end;
          end;

          Cai0003 := TCai0003.Create(Self);

          try
              for lNoCompon := 0 to lLbxTabOrd.Items.Count -1 do
              begin
                    lDsLinha := lLbxTabOrd.Items.Strings[lNoCompon];
                    Delete(lDsLinha, 1, Pos(' ', lDsLinha));

                    Cai0003.DbgTabOrd.Columns.Add;
                    lNoTabOrd := Cai0003.DbgTabOrd.Columns.Count -1;

                    Cai0003.DbgTabOrd.Columns.Items[lNoTabOrd].Title.Font.Style := [fsBold];

                    Cai0003.DbgTabOrd.Columns.Items[lNoTabOrd].Title.Caption :=
                                                Copy(lDsLinha, 1, Pos(';', lDsLinha) - 1);

                    Delete(lDsLinha, 1, Pos(';', lDsLinha));
                    Cai0003.DbgTabOrd.Columns.Items[lNoTabOrd].FieldName := lDsLinha;
              end;

              if lSnShow then
              begin
                    if Cai0003.ShowModal = MrOk then
                    begin
                          lSnAtuali := True;
                    end else
                    begin
                          lSnAtuali := False;
                    end;
              end else
              begin
                    lSnAtuali := True;
              end;

              if lSnAtuali then
              begin
                    for lNoCompon := 0 to Cai0003.DbgTabOrd.Columns.Count -1 do
                    begin
                          lNoTabOrd := StrToInt(
                                    Cai0003.DbgTabOrd.Columns.Items[lNoCompon].FieldName);

                          gDsCompon[lNoTabOrd].NrTabGri := lNoCompon;
                    end;
              end;
          finally
              FreeAndNil(Cai0003);
          end;
      finally
          lLbxTabOrd.Destroy
      end;
end;

procedure TCad0002.SbtTabOrdClick(Sender: TObject);
begin
      if (gDsComCli is TPanel) or (gDsComCli is TTabSheet) or
         (gDsComCli is TScrollBox) then
      begin
            PnlBarra.Enabled  := False;
            PgcInspec.Enabled := False;
            LbxAtribu.Enabled := False;
            CarregarTabOrderComponentes;
            SbtSalvar.Enabled := True;
      end;
end;

procedure TCad0002.CarregarTabOrderComponentes;
var lNoCompon  : Integer;
    lLbxTabOrd : TListBox;
    lDsLinha   : String;
    lNoColuna  : Integer;
    lNmColuna  : String;
    lNoTabOrd  : String;
    lDsComPai  : TWinControl;
begin
      lLbxTabOrd := TListBox.Create(Self);

      try
          lLbxTabOrd.Visible := False;
          lLbxTabOrd.Parent  := Self;
          lLbxTabOrd.Items.Clear;
          lLbxTabOrd.Sorted := True;

          for lNoCompon := 1 to gNoCompon do
          begin
                if Trim(gDsCompon[lNoCompon].TpCompon) <> '' then
                begin
                      if (gDsCompon[lNoCompon].NrComPai = gDsComCli.Tag) and
                         (Pos(gDsCompon[lNoCompon].TpCompon, 'LBA') = 0)  and
                         (not gDsCompon[lNoCompon].SnDelete) then
                      begin
                            lLbxTabOrd.Items.Add(FormatFloat('000',
                                                 gDsCompon[lNoCompon].NrTabOrd) + ';' +
                                                 gDsCompon[lNoCompon].NmAtribu  + ';' +
                                                 FormatFloat('000', lNoCompon));
                      end;
                end;
          end;

          SetLength(gDsEdtOrd, lLbxTabOrd.Items.Count);

          lDsComPai := gDsComCli;
          gNoTabOrd := 0;

          for lNoCompon := 0 to lLbxTabOrd.Items.Count -1 do
          begin
                gDsEdtOrd[lNoCompon] := TEdit.Create(Self);
                gDsEdtOrd[lNoCompon].Parent := lDsComPai;

                lDsLinha   := lLbxTabOrd.Items.Strings[lNoCompon];

                lNoTabOrd  := Copy(lDsLinha, 1, 3);
                Delete(lDsLinha, 1, 4);

                lNmColuna  := Copy(lDsLinha, 1, Pos(';', lDsLinha) - 1);

                Delete(lDsLinha, 1, Pos(';', lDsLinha));
                lNoColuna  := StrToInt(lDsLinha);

                EncontrarComponenteClicado(lNoColuna);

                gDsEdtOrd[lNoCompon].Top        := gDsComCli.Top;
                gDsEdtOrd[lNoCompon].Left       := gDsComCli.Left;
                gDsEdtOrd[lNoCompon].Width      := gDsComCli.Width;
                gDsEdtOrd[lNoCompon].Height     := gDsComCli.Height;
                gDsEdtOrd[lNoCompon].Color      := clAqua;
                gDsEdtOrd[lNoCompon].TabStop    := False;
                gDsEdtOrd[lNoCompon].ReadOnly   := True;
                gDsEdtOrd[lNoCompon].Tag        := lNoColuna;
                gDsEdtOrd[lNoCompon].OnClick    := CliqueTabOrder;
                gDsEdtOrd[lNoCompon].Cursor     := crHandPoint;
                gDsEdtOrd[lNoCompon].Text       := lNoTabOrd;
                gDsEdtOrd[lNoCompon].Font.Style := [fsBold];
          end;
      finally
          lLbxTabOrd.Destroy;
      end;
end;

procedure TCad0002.CliqueTabOrder(Sender : TObject);
begin
      if (Sender as TEdit).Color = clAqua then
      begin
            (Sender as TEdit).Text  := FormatFloat('000', gNoTabOrd);
            (Sender as TEdit).Color := clFuchsia;
            inc(gNoTabOrd);
      end;
end;

procedure TCad0002.SbxFundoDragDrop(Sender, Source: TObject; X, Y: Integer);
var lNrColuna : Integer;
    lDsLabel  : TStaticText;
begin
      if Source = LbxAtribu then
      begin
            EncontrarComponenteClicado((Sender as TComponent).Tag);

            LblPosX.Caption := IntToStr(x);
            LblPosY.Caption := IntToStr(y);
            lNrColuna := LbxAtribu.ItemIndex;
            LbxAtribu.Items.Strings[lNrColuna] := '-> ' +
                                                  LbxAtribu.Items.Strings[lNrColuna];

            inc(gNoCompon);
            lDsLabel := CriarLabel(gNmColuna[lNrColuna].DsAtribu, 'B', False,
                                   gNmColuna[lNrColuna].SnChaPri,
                                   gNmColuna[lNrColuna].NmAtribu,
                                   StrToInt(LblPosX.Caption),
                                   StrToInt(LblPosY.Caption), 13, 38, gDsComCli, 0,
                                   gNmColuna[lNrColuna].DsAtribu, lNrColuna,
                                   gNoCompon, '', '', 0, '',
                                   True, False, True, False);
            inc(gNoCompon);

            if gNmColuna[lNrColuna].DsDomini <> '' then
            begin
                  CriarComboBox(
                           gNmColuna[lNrColuna].NmAtribu,
                           gDsComCli,
                           StrToInt(LblPosX.Caption),
                           StrToInt(LblPosY.Caption),
                           121, 21,
                           gNmColuna[lNrColuna].DsDomini,
                           lNrColuna, lNrColuna, True,
                           gNmColuna[lNrColuna].DsAtribu, True, False ,lNrColuna,
                           lDsLabel, gNoCompon, '', '',
                           True, False, True, False);
            end else
            begin
                  case gNmColuna[lNrColuna].TpAtribu[1] of
                       'V' : begin // Varchar
                                  // if gNmColuna[lNrColuna].VrSize <= 500 then
                                  // begin
                                         CriarEdit(gNmColuna[lNrColuna].NmAtribu,
                                                   gDsComCli,
                                                   StrToInt(LblPosX.Caption),
                                                   StrToInt(LblPosY.Caption),
                                                   121, 21,
                                                   lNrColuna, lNrColuna, True,
                                                   gNmColuna[lNrColuna].DsAtribu,
                                                   True,
                                                   0,
                                                   lDsLabel, False, False,
                                                   lNrColuna, gNoCompon, '', '', '',
                                                   True, False, True, False);
                                {   end else
                                   begin
                                         CriarMemo(gNmColuna[lNrColuna].NmAtribu,
                                                   gDsComCli,
                                                   StrToInt(LblPosX.Caption),
                                                   StrToInt(LblPosY.Caption),
                                                   185, 89,
                                                   lNrColuna, lNrColuna, True,
                                                   gNmColuna[lNrColuna].DsAtribu,
                                                   True, 0, lDsLabel, False, False, lNrColuna,
                                                   gNoCompon, '', '', '',
                                                   True, False, True, False);
                                   end; }
                             end;
                       'B' : begin // blob sub_type 1
                                   CriarMemo(gNmColuna[lNrColuna].NmAtribu,
                                             gDsComCli,
                                             StrToInt(LblPosX.Caption),
                                             StrToInt(LblPosY.Caption),
                                             185, 89,
                                             lNrColuna, lNrColuna, True,
                                             gNmColuna[lNrColuna].DsAtribu,
                                             True, 0, lDsLabel, False, False, lNrColuna,
                                             gNoCompon, '', '', '',
                                             True, False, True, False);
                             end;
                       'I', 'N' : // integer numeric
                             begin
                                   CriarEditNumerico(gNmColuna[lNrColuna].NmAtribu,
                                                  gDsComCli,
                                                  StrToInt(LblPosX.Caption),
                                                  StrToInt(LblPosY.Caption),
                                                  70, 21,
                                                  lNrColuna, lNrColuna, True,
                                                  gNmColuna[lNrColuna].DsAtribu,
                                                  True,
                                                  0,
                                                  lDsLabel, False, False,
                                                  lNrColuna, gNoCompon, '', '', '',
                                                  True, False, True, False);
                             end;
                       'D':  begin // Date
                                   CriarEditData(gNmColuna[lNrColuna].NmAtribu,
                                                  gDsComCli,
                                                  StrToInt(LblPosX.Caption),
                                                  StrToInt(LblPosY.Caption),
                                                  90, 21,
                                                  lNrColuna, lNrColuna, True,
                                                  gNmColuna[lNrColuna].DsAtribu,
                                                  True,
                                                  0,
                                                  lDsLabel, False, False,
                                                  lNrColuna, gNoCompon, '', '', '',
                                                  True, False, True, False);
                             end;
                       'L' : begin // Blob
                                   CriarImagem(gNmColuna[lNrColuna].NmAtribu,
                                               gDsComCli,
                                               StrToInt(LblPosX.Caption),
                                               StrToInt(LblPosY.Caption),
                                               105, 105,
                                               lNrColuna, lNrColuna, True,
                                               gNmColuna[lNrColuna].DsAtribu,
                                               True,
                                               0,
                                               lDsLabel, False, False,
                                               lNrColuna, gNoCompon, '', '', '',
                                               True, False, True, False);
                            end;
                  else begin
                             Dec(gNoCompon);
                       end;
                  end;

            end;
      end;
end;

function TCad0002.Ancoras(pSnAncAci : Boolean; pSnAncAba : Boolean;
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

procedure TCad0002.CriarImagem(pNmColuna : String;       pDsParent : TWinControl;
                               pVlLeft   : Integer;      pVlTop    : Integer;
                               pVlWidth  : Integer;      pVlHeight : Integer;
                               pNoTabOrd : Integer;      pNoTabGri : Integer;
                               pSnEnable : Boolean;      pDsCapGri : String;
                               pSnVisibl : Boolean;      pNoConsul : Integer;
                               pDsLabel  : TStaticText;  pSnAutInc : Boolean;
                               pSnNulo   : Boolean;      pNrAtribu : Integer;
                               pNrCompon : Integer;      pTpDefaul : String;
                               pVrDefaul : String;       pNmChaCon : String;
                               pSnAncAci : Boolean;      pSnAncAba : Boolean;
                               pSnAncEsq : Boolean;      pSnAncDir : Boolean);

var lDsImage : TDbImage;
begin
      ConfigurarComponente(pNrCompon, 'I', pNmColuna, pNmColuna, pDsParent.Tag,
                           pSnEnable, pSnVisibl, pSnAutInc, pSnNulo, '', pNoTabGri,
                           pNoTabOrd, pNoConsul, pDsLabel, pNrAtribu, pDsCapGri,
                           pTpDefaul, pVrDefaul, pNmChaCon,
                           pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsImage                 := TDbImage.Create(Self);
      lDsImage.Tag             := pNrCompon;
      lDsImage.Parent          := pDsParent;
      lDsImage.Left            := pVlLeft;
      lDsImage.Top             := pVlTop;
      lDsImage.Width           := pVlWidth;
      lDsImage.Height          := pVlHeight;
      lDsImage.ReadOnly        := True;
      lDsImage.TabStop         := False;
      lDsImage.OnClick         := ComponenteFrenteClick;
      lDsImage.OnDragOver      := ComponenteDragOver;
      lDsImage.OnMouseDown     := ComponenteMouseDown;
      lDsImage.Name            := '_' + FormatFloat('0000', pNrCompon);
      lDsImage.Anchors         := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      if not pSnEnable then
      begin
            lDsImage.Color := cCrDesabi;
      end;

      if not pSnVisibl then
      begin
            lDsImage.Color := cCrInvisi;
      end;

      ComponenteFrenteClick(lDsImage);
end;

procedure TCad0002.CriarCheckBox(pNmColuna : String;  pDsParent : TWinControl;
                                 pVlLeft   : Integer; pVlTop    : Integer;
                                 pVlWidth  : Integer; pVlHeight : Integer;
                                 pDsDomini : String;  pNoTabOrd : Integer;
                                 pNoTabGri : Integer; pSnEnable : Boolean;
                                 pDsCapGri : String;  pSnVisibl : Boolean;
                                 pSnNulo   : Boolean; pDsCaptio : String;
                                 pNrAtribu : Integer; pDsLabel  : TStaticText;
                                 pNrCompon : Integer; pTpDefaul : String;
                                 pVrDefaul : String;  pNmChaCon : String;
                                 pSnAncAci : Boolean; pSnAncAba : Boolean;
                                 pSnAncEsq : Boolean; pSnAncDir : Boolean);

var lDsCheck : TCheckBox;
begin
      ConfigurarComponente(pNrCompon, 'K', pNmColuna, pDsCaptio, pDsParent.Tag,
                           pSnEnable, pSnVisibl, False, pSnNulo, '', pNoTabGri,
                           pNoTabOrd, 0, pDsLabel,  pNrAtribu, pDsCapGri,
                           pTpDefaul, pVrDefaul, pNmChaCon,
                           pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsCheck                  := TCheckBox.Create(Self);
      lDsCheck.Tag              := pNrCompon;
      lDsCheck.Parent           := pDsParent;
      lDsCheck.Left             := pVlLeft;
      lDsCheck.Top              := pVlTop;
      lDsCheck.Width            := pVlWidth;
      lDsCheck.Height           := pVlHeight;
      lDsCheck.TabStop          := False;
      lDsCheck.Font.Style       := [fsBold];
      lDsCheck.Anchors          := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsCheck.Caption          := pDsCaptio;
      lDsCheck.OnClick          := ComponenteFrenteClick;
      lDsCheck.OnDragOver       := ComponenteDragOver;
      lDsCheck.OnMouseDown      := ComponenteMouseDown;
      lDsCheck.Name             := '_' + FormatFloat('0000', pNrCompon);

      if pSnEnable then
      begin
            lDsCheck.Color := cCrHabili;
      end;

      if not pSnVisibl then
      begin
            lDsCheck.Color := cCrInvisi;
      end;
      ComponenteFrenteClick(lDsCheck);
end;

procedure TCad0002.CriarComboBox(pNmColuna : String;  pDsParent : TWinControl;
                                 pVlLeft   : Integer; pVlTop    : Integer;
                                 pVlWidth  : Integer; pVlHeight : Integer;
                                 pDsDomini : String;  pNoTabOrd : Integer;
                                 pNoTabGri : Integer; pSnEnable : Boolean;
                                 pDsCapGri : String;  pSnVisibl : Boolean;
                                 pSnNulo   : Boolean;
                                 pNrAtribu : Integer; pDsLabel  : TStaticText;
                                 pNrCompon : Integer; pTpDefaul : String;
                                 pVrDefaul : String;
                                 pSnAncAci : Boolean; pSnAncAba : Boolean;
                                 pSnAncEsq : Boolean; pSnAncDir : Boolean);
var lDsCombo : TBrvComboBox;
begin
      ConfigurarComponente(pNrCompon, 'C', pNmColuna, pNmColuna, pDsParent.Tag,
                           pSnEnable, pSnVisibl, False, pSnNulo, '', pNoTabGri,
                           pNoTabOrd, 0, pDsLabel,  pNrAtribu, pDsCapGri,
                           pTpDefaul, pVrDefaul, '',
                           pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsCombo                  := TBrvComboBox.Create(Self);
      lDsCombo.Tag              := pNrCompon;
      lDsCombo.Parent           := pDsParent;
      lDsCombo.Left             := pVlLeft;
      lDsCombo.Top              := pVlTop;
      lDsCombo.Width            := pVlWidth;
      lDsCombo.Height           := pVlHeight;
      lDsCombo.TabStop          := False;
      lDsCombo.Style            := csDropDownList;
      lDsCombo.Anchors          := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsCombo.Text             := pNmColuna;
      lDsCombo.OnClick          := ComponenteFrenteClick;
      lDsCombo.OnDragOver       := ComponenteDragOver;
      lDsCombo.OnMouseDown      := ComponenteMouseDown;
      lDsCombo.Items.CommaText  := pDsDomini;
      lDsCombo.Name             := '_' + FormatFloat('0000', pNrCompon);

      if not pSnEnable then
      begin
            lDsCombo.Color := cCrDesabi;
      end;

      if not pSnVisibl then
      begin
            lDsCombo.Color := cCrInvisi;
      end;
      ComponenteFrenteClick(lDsCombo);
end;

procedure TCad0002.CriarRadioGroup(pNmColuna : String;  pDsParent : TWinControl;
                                 pVlLeft   : Integer; pVlTop    : Integer;
                                 pVlWidth  : Integer; pVlHeight : Integer;
                                 pDsDomini : String;  pNoTabOrd : Integer;
                                 pNoTabGri : Integer; pSnEnable : Boolean;
                                 pDsCapGri : String;  pSnVisibl : Boolean;
                                 pSnNulo   : Boolean; pDsCaptio : String;
                                 pNrAtribu : Integer; pDsLabel  : TStaticText;
                                 pNrCompon : Integer; pTpDefaul : String;
                                 pVrDefaul : String;
                                 pSnAncAci : Boolean; pSnAncAba : Boolean;
                                 pSnAncEsq : Boolean; pSnAncDir : Boolean);
var lDsRadGro : TBrvRadioGroup;
begin
      ConfigurarComponente(pNrCompon, 'R', pNmColuna, pDsCaptio, pDsParent.Tag,
                           pSnEnable, pSnVisibl, False, pSnNulo, '', pNoTabGri,
                           pNoTabOrd, 0, pDsLabel,  pNrAtribu, pDsCapGri,
                           pTpDefaul, pVrDefaul, '',
                           pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsRadGro                  := TBrvRadioGroup.Create(Self);
      lDsRadGro.Tag              := pNrCompon;
      lDsRadGro.Parent           := pDsParent;
      lDsRadGro.Left             := pVlLeft;
      lDsRadGro.Top              := pVlTop;
      lDsRadGro.Width            := pVlWidth;
      lDsRadGro.Height           := pVlHeight;
      lDsRadGro.TabStop          := False;
      lDsRadGro.Items.CommaText  := pDsDomini;
      lDsRadGro.Font.Style       := [fsBold];
      lDsRadGro.Anchors          := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsRadGro.Caption          := pDsCaptio;
      lDsRadGro.OnClick          := ComponenteFrenteClick;
      lDsRadGro.OnDragOver       := ComponenteDragOver;
      lDsRadGro.OnMouseDown      := ComponenteMouseDown;
      lDsRadGro.Items.CommaText  := pDsDomini;
      lDsRadGro.Name             := '_' + FormatFloat('0000', pNrCompon);

      if pSnEnable then
      begin
            lDsRadGro.Color := cCrHabili;
      end;

      if not pSnVisibl then
      begin
            lDsRadGro.Color := cCrInvisi;
      end;
      ComponenteFrenteClick(lDsRadGro);
end;

procedure TCad0002.CriarMemo(pNmColuna : String;       pDsParent : TWinControl;
                             pVlLeft   : Integer;      pVlTop    : Integer;
                             pVlWidth  : Integer;      pVlHeight : Integer;
                             pNoTabOrd : Integer;      pNoTabGri : Integer;
                             pSnEnable : Boolean;      pDsCapGri : String;
                             pSnVisibl : Boolean;      pNoConsul : Integer;
                             pDsLabel  : TStaticText;  pSnAutInc : Boolean;
                             pSnNulo   : Boolean;      pNrAtribu : Integer;
                             pNrCompon : Integer;      pTpDefaul : String;
                             pVrDefaul : String;       pNmChaCon : String;
                             pSnAncAci : Boolean;      pSnAncAba : Boolean;
                             pSnAncEsq : Boolean;      pSnAncDir : Boolean);
var lDsMemo : TMemo;
begin
      ConfigurarComponente(pNrCompon, 'M', pNmColuna, pNmColuna, pDsParent.Tag,
                           pSnEnable, pSnVisibl, pSnAutInc, pSnNulo, '', pNoTabGri,
                           pNoTabOrd, pNoConsul, pDsLabel,  pNrAtribu, pDsCapGri,
                           pTpDefaul, pVrDefaul, pNmChaCon,
                           pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsMemo                  := TMemo.Create(Self);
      lDsMemo.Tag              := pNrCompon;
      lDsMemo.Parent           := pDsParent;
      lDsMemo.Left             := pVlLeft;
      lDsMemo.Top              := pVlTop;
      lDsMemo.Width            := pVlWidth;
      lDsMemo.Height           := pVlHeight;
      lDsMemo.TabStop          := False;
      lDsMemo.ReadOnly         := True;
      lDsMemo.Lines.Text       := pNmColuna;
      lDsMemo.OnClick          := ComponenteFrenteClick;
      lDsMemo.OnDragOver       := ComponenteDragOver;
      lDsMemo.OnMouseDown      := ComponenteMouseDown;
      lDsMemo.Name             := '_' + FormatFloat('0000', pNrCompon);
      lDsMemo.Anchors          := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      if not pSnEnable then
      begin
            lDsMemo.Color := cCrDesabi;
      end;

      if not pSnVisibl then
      begin
            lDsMemo.Color := cCrInvisi;
      end;

      ComponenteFrenteClick(lDsMemo);
end;

function TCad0002.CriarLabel(pDsCaptio : String;  pTpCompon : Char;
                              pSnNulo   : Boolean; pSnChaPri : Boolean;
                              pNmColuna : String;  pVlLeft   : Integer;
                              pVlTop    : Integer; pVlHeight : Integer;
                              pVlWidth  : Integer; pDsParent : TWinControl;
                              pNoTabGri : Integer; pDsCapGri : String;
                              pNrAtribu : Integer; pNrCompon : Integer;
                              pTpDefaul : String;  pVrDefaul : String;
                              pNrConsul : Integer; pNmChaCon : String;
                              pSnAncAci : Boolean; pSnAncAba : Boolean;
                              pSnAncEsq : Boolean; pSnAncDir : Boolean) : TStaticText;
begin
      ConfigurarComponente(pNrCompon, pTpCompon, pNmColuna, pDsCaptio, pDsParent.Tag,
                           True, True, False, pSnNulo, '', pNoTabGri,  0, pNrConsul, nil,
                           pNrAtribu, pDsCapGri, pTpDefaul, pVrDefaul, pNmChaCon,
                           pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      Result             := TStaticText.Create(Self);
      Result.Tag         := pNrCompon;
      Result.Parent      := pDsParent;
      Result.AutoSize    := False;
      Result.Left        := pVlLeft;
      Result.Top         := pVlTop;
      Result.Height      := pVlHeight;
      Result.Width       := pVlWidth;
      Result.Name        := '_' + FormatFloat('0000', pNrCompon);
      Result.Caption     := pDsCaptio;
      Result.OnClick     := ComponenteFrenteClick;
      Result.OnDragOver  := ComponenteDragOver;
      Result.OnMouseDown := ComponenteMouseDown;
      Result.Font.Style  := [fsBold];
      Result.Anchors     := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      case pTpCompon of
           'L' : begin // Label normal
                       Result.Font.Color := clRed;
                       ComponenteFrenteClick(Result);
                 end;
           'B' : begin // label de campo (que vai ao lado esquerdo)
                       if pSnChaPri then
                       begin
                             Result.Font.Style := Result.Font.Style + [fsUnderline];
                       end;

                       if not pSnNulo then
                       begin
                             Result.Font.color := clBlue;
                       end;

                       LblPosX.Caption := IntToStr(Result.Left + Result.Width + 5);
                 end;
           'A' : begin // retorno  da consulta
//                       Result.Font.Color := clMaroon;
                       Result.Font.Style  := [];
                       ComponenteFrenteClick(Result);
                       Result.BorderStyle := sbsSingle;
                 end;
      end;
end;

procedure TCad0002.CriarEdit(pNmColuna : String;       pDsParent : TWinControl;
                             pVlLeft   : Integer;      pVlTop    : Integer;
                             pVlWidth  : Integer;      pVlHeight : Integer;
                             pNoTabOrd : Integer;      pNoTabGri : Integer;
                             pSnEnable : Boolean;      pDsCapGri : String;
                             pSnVisibl : Boolean;      pNoConsul : Integer;
                             pDsLabel  : TStaticText;  pSnAutInc : Boolean;
                             pSnNulo   : Boolean;      pNrAtribu : Integer;
                             pNrCompon : Integer;      pTpDefaul : String;
                             pVrDefaul : String;       pNmChaCon : String;
                             pSnAncAci : Boolean;      pSnAncAba : Boolean;
                             pSnAncEsq : Boolean;      pSnAncDir : Boolean);
var lDsEdit : TBrvEdit;
begin
      ConfigurarComponente(pNrCompon, 'E', pNmColuna, pNmColuna, pDsParent.Tag,
                           pSnEnable, pSnVisibl, pSnAutInc, pSnNulo, '', pNoTabGri,
                           pNoTabOrd, pNoConsul, pDsLabel, pNrAtribu, pDsCapGri,
                           pTpDefaul, pVrDefaul, pNmChaCon,
                           pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsEdit                 := TBrvEdit.Create(Self);

      PosicionarEdit(lDsEdit, pNrCompon, pDsParent, pVlLeft, pVlTop, pVlWidth, pVlHeight,
                     pSnEnable, pSnVisibl,
                     pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsEdit.Text            := pNmColuna;
      lDsEdit.BrVisibleButton := pNoConsul > 0;

end;

procedure TCad0002.CriarEditNumerico(pNmColuna : String;       pDsParent : TWinControl;
                                     pVlLeft   : Integer;      pVlTop    : Integer;
                                     pVlWidth  : Integer;      pVlHeight : Integer;
                                     pNoTabOrd : Integer;      pNoTabGri : Integer;
                                     pSnEnable : Boolean;      pDsCapGri : String;
                                     pSnVisibl : Boolean;      pNoConsul : Integer;
                                     pDsLabel  : TStaticText;  pSnAutInc : Boolean;
                                     pSnNulo   : Boolean;      pNrAtribu : Integer;
                                     pNrCompon : Integer;      pTpDefaul : String;
                                     pVrDefaul : String;       pNmChaCon : String;
                                     pSnAncAci : Boolean;      pSnAncAba : Boolean;
                                     pSnAncEsq : Boolean;      pSnAncDir : Boolean);

var lDsEdit : TBrvEditNum;
begin
      ConfigurarComponente(pNrCompon, 'N', pNmColuna, pNmColuna, pDsParent.Tag,
                           pSnEnable, pSnVisibl, pSnAutInc, pSnNulo, '', pNoTabGri,
                           pNoTabOrd, pNoConsul, pDsLabel, pNrAtribu, pDsCapGri,
                           pTpDefaul, pVrDefaul, pNmChaCon,
                           pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);


      lDsEdit                 := TBrvEditNum.Create(Self);

      PosicionarEdit(lDsEdit, pNrCompon, pDsParent, pVlLeft, pVlTop, pVlWidth, pVlHeight,
                     pSnEnable, pSnVisibl,
                     pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsEdit.BrCasasDecimais := 0;

      if pNoConsul <> 0 then
      begin
            lDsEdit.BrFunctionButton := TpConsulta;
      end else
      begin
            lDsEdit.BrFunctionButton := TpCalculadora;
      end;
end;

procedure TCad0002.CriarEditData(pNmColuna : String;       pDsParent : TWinControl;
                                 pVlLeft   : Integer;      pVlTop    : Integer;
                                 pVlWidth  : Integer;      pVlHeight : Integer;
                                 pNoTabOrd : Integer;      pNoTabGri : Integer;
                                 pSnEnable : Boolean;      pDsCapGri : String;
                                 pSnVisibl : Boolean;      pNoConsul : Integer;
                                 pDsLabel  : TStaticText;  pSnAutInc : Boolean;
                                 pSnNulo   : Boolean;      pNrAtribu : Integer;
                                 pNrCompon : Integer;      pTpDefaul : String;
                                 pVrDefaul : String;       pNmChaCon : String;
                                 pSnAncAci : Boolean;      pSnAncAba : Boolean;
                                 pSnAncEsq : Boolean;      pSnAncDir : Boolean);
var lDsEdit : TBrvEditDate;
begin
      ConfigurarComponente(pNrCompon, 'D', pNmColuna, pNmColuna, pDsParent.Tag,
                           pSnEnable, pSnVisibl, pSnAutInc, pSnNulo, '', pNoTabGri,
                           pNoTabOrd, pNoConsul, pDsLabel, pNrAtribu, pDsCapGri,
                           pTpDefaul, pVrDefaul, pNmChaCon,
                           pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);


      lDsEdit                 := TBrvEditDate.Create(Self);
      lDsEdit.Tag             := pNrCompon;
      lDsEdit.Parent          := pDsParent;
      lDsEdit.Left            := pVlLeft;
      lDsEdit.Top             := pVlTop;
      lDsEdit.Width           := pVlWidth;
      lDsEdit.Height          := pVlHeight;
      lDsEdit.ReadOnly        := True;
      lDsEdit.TabStop         := False;
      lDsEdit.OnClick         := ComponenteFrenteClick;
      lDsEdit.OnDragOver      := ComponenteDragOver;
      lDsEdit.OnMouseDown     := ComponenteMouseDown;
      lDsEdit.Name            := '_' + FormatFloat('0000', pNrCompon);
      lDsEdit.Anchors         := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      if not pSnEnable then
      begin
            lDsEdit.Color := cCrDesabi;
      end;

      if not pSnVisibl then
      begin
            lDsEdit.Color := cCrInvisi;
      end;

      ComponenteFrenteClick(lDsEdit);
end;

procedure TCad0002.PosicionarEdit(
                         pDsEdit   : TEdit;   pNrCompon : Integer; pDsParent : TWinControl;
                         pVlLeft   : Integer; pVlTop    : Integer; pVlWidth  : Integer;
                         pVlHeight : Integer; pSnEnable : Boolean; pSnVisibl : Boolean;
                         pSnAncAci : Boolean; pSnAncAba : Boolean;
                         pSnAncEsq : Boolean; pSnAncDir : Boolean);
begin
      pDsEdit.Tag             := pNrCompon;
      pDsEdit.Parent          := pDsParent;
      pDsEdit.Left            := pVlLeft;
      pDsEdit.Top             := pVlTop;
      pDsEdit.Width           := pVlWidth;
      pDsEdit.Height          := pVlHeight;
      pDsEdit.ReadOnly        := True;
      pDsEdit.TabStop         := False;
      pDsEdit.OnClick         := ComponenteFrenteClick;
      pDsEdit.OnDragOver      := ComponenteDragOver;
      pDsEdit.OnMouseDown     := ComponenteMouseDown;
      pDsEdit.Name            := '_' + FormatFloat('0000', pNrCompon);
      pDsEdit.Anchors         := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      if not pSnEnable then
      begin
            pDsEdit.Color := cCrDesabi;
      end;

      if not pSnVisibl then
      begin
            pDsEdit.Color := cCrInvisi;
      end;

      ComponenteFrenteClick(pDsEdit);
end;

procedure TCad0002.ConfigurarComponente(pNrCompon : Integer;   pTpCompon : Char;
                    pNmColuna : String;      pDsCaptio : String;  pNoComPai : Integer;
                    pSnEnable : Boolean;     pSnVisibl : Boolean; pSnAutInc : Boolean;
                    pSnNulo   : Boolean;     pDsDomini : String;
                    pNoTabGri : Integer;     pNoTabOrd : Integer; pNoConsul : Integer;
                    pDsLabel  : TStaticText; pNrAtribu : Integer; pDsCapGri : String;
                    pTpDefaul : String;      pVrDefaul : String;  pNmChaCon : String;
                    pSnAncAci : Boolean;     pSnAncAba : Boolean; pSnAncEsq : Boolean;
                    pSnAncDir : Boolean);
var lCdsAtrib : TClientDataSet;
begin
      gDsCompon[pNrCompon].NrCompon := pNrCompon;
      gDsCompon[pNrCompon].TpCompon := pTpCompon;
      gDsCompon[pNrCompon].NmAtribu := pNmColuna;
      gDsCompon[pNrCompon].DsCaptio := pDsCaptio;
      gDsCompon[pNrCompon].DsCapGri := pDsCapGri;
      gDsCompon[pNrCompon].NrComPai := pNoComPai;
      gDsCompon[pNrCompon].SnEnable := pSnEnable;
      gDsCompon[pNrCompon].SnVisibl := pSnVisibl;
      gDsCompon[pNrCompon].SnAutInc := pSnAutInc;
      gDsCompon[pNrCompon].SnNulo   := pSnNulo;
      gDsCompon[pNrCompon].DsDomini := pDsDomini;
      gDsCompon[pNrCompon].SnDelete := False;
      gDsCompon[pNrCompon].NrTabGri := pNoTabGri;
      gDsCompon[pNrCompon].NrTabOrd := pNoTabOrd;
      gDsCompon[pNrCompon].LblRotul := pDsLabel;
      gDsCompon[pNrCompon].NrAtribu := pNrAtribu;
      gDsCompon[pNrCompon].TpDefaul := pTpDefaul;
      gDsCompon[pNrCompon].VrDefaul := pVrDefaul;
      gDsCompon[pNrCompon].NrQueCon := pNoConsul;
      gDsCompon[pNrCompon].NmChaCon := pNmChaCon;
      gDsCompon[pNrCompon].SnAncAci := pSnAncAci;
      gDsCompon[pNrCompon].SnAncAba := pSnAncAba;
      gDsCompon[pNrCompon].SnAncEsq := pSnAncEsq;
      gDsCompon[pNrCompon].SnAncDir := pSnAncDir;

      if pNoConsul > 0 then
      begin
            lCdsAtrib := TClientDataSet.Create(Self);

            try
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                // =-=-=-= Carregando atributos de display
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                lCdsAtrib.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                          ' Select * From S026 '  +
                                          ' Where NrForDin =   '  + IntToStr(gNrForDin) +
                                          ' and   NrCompon =   '  + IntToStr(pNrCompon) +
                                          ' Order by NrOrdCon');
                gDsCompon[pNrCompon].DsAtrCon := '';
                gDsCompon[pNrCompon].DsSepCon :=
                                              lCdsAtrib.FieldByName('DsSepAnt').AsString;

                while not lCdsAtrib.Eof do
                begin
                      gDsCompon[pNrCompon].DsAtrCon :=
                                       gDsCompon[pNrCompon].DsAtrCon +
                                       lCdsAtrib.FieldByName('NmTabela').AsString + '.' +
                                       lCdsAtrib.FieldByName('NmAtribu').AsString + '@';

                      lCdsAtrib.Next;
                end;
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                // =-=-=-= Carregando atributos de retorno
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                lCdsAtrib.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlFixa(
                                          ' Select * From S045 '  +
                                          ' Where NrForDin =   '  + IntToStr(gNrForDin) +
                                          ' and   NrCompon =   '  + IntToStr(pNrCompon) +
                                          ' Order by NrSequen');
                gDsCompon[pNrCompon].DsRetCon := '';

                while not lCdsAtrib.Eof do
                begin
                      gDsCompon[pNrCompon].DsRetCon :=
                                       gDsCompon[pNrCompon].DsRetCon +
                                       lCdsAtrib.FieldByName('NmTabDes').AsString + '.' +
                                       lCdsAtrib.FieldByName('NmAtrDes').AsString + ' = '+
                                       lCdsAtrib.FieldByName('NmTabOri').AsString + '.' +
                                       lCdsAtrib.FieldByName('NmAtrOri').AsString + '@';

                      lCdsAtrib.Next;
                end;
            finally
                FreeAndNil(lCdsAtrib);
            end;
      end;

      SbtSalvar.Enabled := True;
end;

procedure TCad0002.ComponenteFrenteClick(Sender : TObject);
begin
      EncontrarComponenteClicado((Sender as TComponent).Tag);
      ContornoComponente;

      EdtVlLeft.Enabled   := True;
      EdtVlTop.Enabled    := True;
      EdtVlWidth.Enabled  := True;
      EdtVlHeigth.Enabled := True;

      CbxTpObjeto.Items.Clear;
      CbxTpObjeto.Values.Clear;
      CbxTpObjeto.Enabled := False;
      CbxTpObjeto.Tag     := 0;


      case gDsCompon[(Sender as TComponent).Tag].TpCompon of
           'L', 'B': begin // Label
                      CbxTpObjeto.Items.Add('Label');
                      CbxTpObjeto.ItemIndex := 0;
                      ChxEnabled.Enabled    := False;
                      CbxVisibl.Enabled     := False;
                      CbxAutInc.Enabled     := False;
                      CbxSnNulo.Enabled     := False;
                      LblNrConsul.Caption   := '';
                      LblDsDefaul.Caption   := '';
                      SbtConsul.Enabled     := False;
                      SbtDefaul.Enabled     := False;
                      EdtDsCaptio.Enabled   := True;
                      EdtDsCapGri.Enabled   := False;
                end;
           'D': begin
                      CbxTpObjeto.Items.Add('Edit');
                      CbxTpObjeto.ItemIndex := 0;

                      ChxEnabled.Enabled    := True;
                      CbxVisibl.Enabled     := True;
                      CbxAutInc.Enabled     := False;
                      CbxSnNulo.Enabled     := True;
                      SbtConsul.Enabled     := False;
                      SbtDefaul.Enabled     := True;
                      EdtDsCaptio.Enabled   := False;
                      EdtDsCapGri.Enabled   := True;
                end;
           'E', 'N':
                begin
                      CbxTpObjeto.Items.Add('Edit');
                      CbxTpObjeto.ItemIndex := 0;

                      ChxEnabled.Enabled    := True;
                      CbxVisibl.Enabled     := True;
                      CbxAutInc.Enabled     := True;
                      CbxSnNulo.Enabled     := True;
                      SbtConsul.Enabled     := True;
                      SbtDefaul.Enabled     := True;
                      EdtDsCaptio.Enabled   := False;
                      EdtDsCapGri.Enabled   := True;
                end;
           'C', 'R', 'K':
                begin
                      ChxEnabled.Enabled    := True;
                      CbxVisibl.Enabled     := True;
                      CbxAutInc.Enabled     := False;
                      CbxSnNulo.Enabled     := True;
                      SbtConsul.Enabled     := False;
                      SbtDefaul.Enabled     := True;
                      EdtDsCapGri.Enabled   := True;

                      CbxTpObjeto.Items.Add('ComboBox');
                      CbxTpObjeto.Items.Add('CheckBox');
                      CbxTpObjeto.Items.Add('RadioGroup');

                      CbxTpObjeto.Values.Add('C');
                      CbxTpObjeto.Values.Add('K');
                      CbxTpObjeto.Values.Add('R');

                      case gDsCompon[(Sender as TComponent).Tag].TpCompon of
                           'C' : begin
                                       CbxTpObjeto.ItemIndex := 0;
                                       EdtDsCaptio.Enabled   := False;
                                 end;
                           'K' : begin
                                       CbxTpObjeto.ItemIndex := 1;
                                       EdtDsCaptio.Enabled   := True;
                                 end;
                           'R' : begin
                                       CbxTpObjeto.ItemIndex := 2;
                                       EdtDsCaptio.Enabled   := True;
                                 end;
                      end;

                      CbxTpObjeto.Enabled := True;
                end;
           'M': begin
                      CbxTpObjeto.Items.Add('Memo');
                      CbxTpObjeto.ItemIndex := 0;
                      ChxEnabled.Enabled   := True;
                      CbxVisibl.Enabled    := True;
                      CbxAutInc.Enabled    := False;
                      CbxSnNulo.Enabled    := True;
                      ChxEnabled.Enabled   := True;
                      SbtConsul.Enabled    := False;
                      SbtDefaul.Enabled    := True;
                      EdtDsCaptio.Enabled  := False;
                      EdtDsCapGri.Enabled  := True;
                end;
           'T': begin
                      CbxTpObjeto.Items.Add('TabSheet');
                      CbxTpObjeto.ItemIndex := 0;
                      ChxEnabled.Enabled   := False;
                      CbxVisibl.Enabled    := False;
                      CbxAutInc.Enabled    := False;
                      CbxSnNulo.Enabled    := False;
                      ChxEnabled.Enabled   := False;
                      SbtConsul.Enabled    := False;
                      SbtDefaul.Enabled    := False;
                      EdtDsCaptio.Enabled  := True;
                      EdtDsCapGri.Enabled  := False;
                end;
           else begin
                      ChxEnabled.Enabled   := False;
                      CbxVisibl.Enabled    := False;
                      CbxAutInc.Enabled    := False;
                      CbxSnNulo.Enabled    := False;
                      EdtDsCaptio.Enabled  := False;
                      EdtDsCapGri.Enabled  := False;
                      SbtConsul.Enabled    := False;
                      SbtDefaul.Enabled    := False;
                      LblNrConsul.Caption  := '';
                      LblDsDefaul.Caption  := '';
                end;
      end;

      EdtVlLeft.BrAsInteger   := gDsComCli.Left;
      EdtVlTop.BrAsInteger    := gDsComCli.Top;
      EdtVlWidth.BrAsInteger  := gDsComCli.Width;
      EdtVlHeigth.BrAsInteger := gDsComCli.Height;
      EdtDsCaptio.Text        := gDsCompon[(Sender as TComponent).Tag].DsCaptio;
      LblNrConsul.Caption     := IntToStr(gDsCompon[(Sender as TComponent).Tag].NrQueCon);
      LblDsDefaul.Caption     := gDsCompon[(Sender as TComponent).Tag].TpDefaul + '-' +
                                 gDsCompon[(Sender as TComponent).Tag].VrDefaul;

      if gDsCompon[(Sender as TComponent).Tag].DsCapGri = '' then
      begin
            EdtDsCapGri.Text := gDsCompon[(Sender as TComponent).Tag].DsCaptio;
      end else
      begin
            EdtDsCapGri.Text := gDsCompon[(Sender as TComponent).Tag].DsCapGri;
      end;

      ChxEnabled.Checked    := gDsCompon[(Sender as TComponent).Tag].SnEnable;
      CbxVisibl.Checked     := gDsCompon[(Sender as TComponent).Tag].SnVisibl;
      CbxAutInc.Checked     := gDsCompon[(Sender as TComponent).Tag].SnAutInc;
      CbxSnNulo.Checked     := gDsCompon[(Sender as TComponent).Tag].SnNulo;
      CbxAncAci.Checked     := gDsCompon[(Sender as TComponent).Tag].SnAncAci;
      CbxAncAba.Checked     := gDsCompon[(Sender as TComponent).Tag].SnAncAba;
      CbxAncEsq.Checked     := gDsCompon[(Sender as TComponent).Tag].SnAncEsq;
      CbxAncDir.Checked     := gDsCompon[(Sender as TComponent).Tag].SnAncDir;
      CbxTpObjeto.Tag       := 1;
end;

procedure TCad0002.ContornoComponente;
var lNrMarcad : Byte;
begin
      for lNrMarcad := 0 to 7 do
      begin
            PnlMarcad[lNrMarcad].Visible := False;
      end;

      PnlMarcad[0].Parent := gDsComCli.Parent;
      PnlMarcad[0].Left   := gDsComCli.Left - 2;
      PnlMarcad[0].Top    := gDsComCli.Top  - 2;
      PnlMarcad[0].BringToFront;

      PnlMarcad[1].Parent := gDsComCli.Parent;
      PnlMarcad[1].Left   := (gDsComCli.Left - 2) + ((gDsComCli.Width div 2));
      PnlMarcad[1].Top    := (gDsComCli.Top  - 2);

      PnlMarcad[2].Parent := gDsComCli.Parent;
      PnlMarcad[2].Left   := (gDsComCli.Left - 2) + (gDsComCli.Width - 2);
      PnlMarcad[2].Top    := (gDsComCli.Top  - 2);

      PnlMarcad[3].Parent := gDsComCli.Parent;
      PnlMarcad[3].Left   := gDsComCli.Left - 2;
      PnlMarcad[3].Top    := (gDsComCli.Top) + ((gDsComCli.Height div 2) - 2);

      PnlMarcad[4].Parent := gDsComCli.Parent;
      PnlMarcad[4].Left   := (gDsComCli.Left - 2) + (gDsComCli.Width - 2);
      PnlMarcad[4].Top    := (gDsComCli.Top) + ((gDsComCli.Height div 2) - 2);

      PnlMarcad[5].Parent := gDsComCli.Parent;
      PnlMarcad[5].Left   := gDsComCli.Left - 2;
      PnlMarcad[5].Top    := (gDsComCli.Top - 3) + (gDsComCli.Height);

      PnlMarcad[6].Parent := gDsComCli.Parent;
      PnlMarcad[6].Left   := (gDsComCli.Left - 2) + ((gDsComCli.Width div 2));
      PnlMarcad[6].Top    := (gDsComCli.Top - 3)  + (gDsComCli.Height);

      PnlMarcad[7].Parent := gDsComCli.Parent;
      PnlMarcad[7].Left   := (gDsComCli.Left - 2) + (gDsComCli.Width - 2);
      PnlMarcad[7].Top    := (gDsComCli.Top - 3)  + (gDsComCli.Height);

      for lNrMarcad := 0 to 7 do
      begin
            PnlMarcad[lNrMarcad].Visible := True;
            PnlMarcad[lNrMarcad].BringToFront;
      end;

      ShpVulto.Caption := gDsCompon[gDsComCli.Tag].DsCaptio;
      shpVulto.Parent  := SbxFundo;
      ShpVulto.BringToFront;
end;

procedure TCad0002.ComponenteMouseDown(Sender: TObject; Button: TMouseButton;
                                             Shift: TShiftState; X, Y: Integer);
begin
      if ssCtrl in Shift then
      begin
             EncontrarComponenteClicado((Sender as TComponent).Tag);
             ArrastarComponente(x, y);
      end;
end;

procedure TCad0002.ArrastarComponente(pVlClickX, pVlClickY : Integer);
begin
      gVlClickX := pVlClickX;
      gVlClickY := pVlClickY;

      ShpVulto.Caption := gDsCompon[gDsComCli.Tag].DsCaptio;
      ShpVulto.Parent  := gDsComCli.Parent;
      ShpVulto.Top     := gDsComCli.Top;
      ShpVulto.Left    := gDsComCli.Left;
      ShpVulto.Width   := gDsComCli.Width;
      ShpVulto.Height  := gDsComCli.Height;
      ShpVulto.BringToFront;
      ShpVulto.Visible := True;
      ShpVulto.BeginDrag(False);
end;

procedure TCad0002.ComponenteDragOver(Sender, Source: TObject; X, Y: Integer;
                                        State: TDragState; var Accept: Boolean);
begin
      LblPosX.Caption := IntToStr(x);
      LblPosY.Caption := IntToStr(y);

      case gDsCompon[(Sender as TComponent).Tag].TpCompon of
           'L', 'B', 'A':
                SbxFundoDragOver(Sender, Source, x + TLabel(Sender).Left,
                            y + TLabel(Sender).Top, dsDragEnter, Accept);
           'E': SbxFundoDragOver(Sender, Source, x + TEdit(Sender).Left,
                            y + TEdit(Sender).Top, dsDragEnter, Accept);
           'P': SbxFundoDragOver(Sender, Source, x + TPanel(Sender).Left,
                            y + TPanel(Sender).Top, dsDragEnter, Accept);
           'G': SbxFundoDragOver(Sender, Source, x + TPageControl(Sender).Left,
                            y + TPageControl(Sender).Top, dsDragEnter, Accept);
           'T': SbxFundoDragOver(Sender, Source, x + TTabSheet(Sender).Left,
                            y + TTabSheet(Sender).Top, dsDragEnter, Accept);
           'M': SbxFundoDragOver(Sender, Source, x + TMemo(Sender).Left,
                            y + TMemo(Sender).Top, dsDragEnter, Accept);
           'I': SbxFundoDragOver(Sender, Source, x + TImage(Sender).Left,
                            y + TImage(Sender).Top, dsDragEnter, Accept);
           'N': SbxFundoDragOver(Sender, Source, x + TBrvEditNum(Sender).Left,
                            y + TBrvEditNum(Sender).Top, dsDragEnter, Accept);
           'C': SbxFundoDragOver(Sender, Source, x + TComboBox(Sender).Left,
                            y + TComboBox(Sender).Top, dsDragEnter, Accept);
           'R': SbxFundoDragOver(Sender, Source, x + TRadioGroup(Sender).Left,
                            y + TRadioGroup(Sender).Top, dsDragEnter, Accept);
      end;
end;

procedure TCad0002.SbxFundoDragOver(Sender, Source: TObject; X, Y: Integer;
  State: TDragState; var Accept: Boolean);
begin
      LblPosX.Caption := IntToStr(x);
      LblPosY.Caption := IntToStr(y);

      Accept := (PnlColuna.Caption = gNmTabela);

      if Accept then
      begin
            if Source = LbxAtribu then
            begin
                  Accept := LbxAtribu.ItemIndex >= 0;

                  if Accept then
                  begin
                        Accept := Copy(LbxAtribu.Items.Strings[LbxAtribu.ItemIndex], 1, 2)
                                                                                  <> '->';
                  end;
            end else
            begin
                  Accept := (Sender = gDsComCli.Parent) or
                            (Sender = ShpVulto)         or
                            (Sender = gDsComCli);

                  if Accept then
                  begin
                        RedimencionarVulto(x, y);
                  end;
            end;
      end;
end;

procedure TCad0002.RedimencionarVulto(X, Y : Integer);
var VlDifere : Integer;
begin
      if PnlMarcad[0].Tag = 1 then
      begin
            VlDifere        := ShpVulto.Top    - Y;
            ShpVulto.top    := Y;
            ShpVulto.Height := ShpVulto.Height + VlDifere;

            VlDifere        := ShpVulto.Left  - X;
            ShpVulto.Left   := X;
            ShpVulto.Width  := ShpVulto.Width + VlDifere;
      end
      else if PnlMarcad[1].Tag = 1 then
           begin
                 VlDifere        := ShpVulto.top - Y;
                 ShpVulto.Top    := Y;
                 ShpVulto.Height := ShpVulto.Height + VlDifere;
           end
      else if PnlMarcad[2].Tag = 1 then
           begin
                 VlDifere        := ShpVulto.top - Y;
                 ShpVulto.Top    := Y;
                 ShpVulto.Height := ShpVulto.Height + VlDifere;
                 ShpVulto.Width  := X - ShpVulto.Left;
           end
      else if PnlMarcad[3].Tag = 1 then
           begin
                 VlDifere        := ShpVulto.Left - X;
                 ShpVulto.Left   := X;
                 ShpVulto.Width  := ShpVulto.Width + VlDifere;
           end
      else if PnlMarcad[4].Tag = 1 then
           begin
                 ShpVulto.Width  := X - ShpVulto.Left;
           end
      else if PnlMarcad[5].Tag = 1 then
           begin
                 ShpVulto.Height := Y - ShpVulto.Top;
                 VlDifere        := ShpVulto.Left - X;
                 ShpVulto.Left   := X;
                 ShpVulto.Width  := ShpVulto.Width + VlDifere;
           end
      else if PnlMarcad[6].Tag = 1 then
           begin
                 ShpVulto.Height := Y - ShpVulto.Top;
           end
      else if PnlMarcad[7].Tag = 1 then
           begin
                 ShpVulto.Height := Y - ShpVulto.Top;
                 ShpVulto.Width  := X - ShpVulto.Left;
           end
      else begin // arrastando
                 ShpVulto.Top  := Y - gVlClickY;
                 ShpVulto.Left := X - gVlClickX;
           end;
end;

procedure TCad0002.SbxFundoMouseMove(Sender: TObject; Shift: TShiftState; X, Y: Integer);
begin
{
      if (Sender as TComponent).Tag = 0 then
      begin
            LblPosX.Caption := IntToStr(x);
            LblPosY.Caption := IntToStr(y);
      end else
      begin
            LblPosX.Caption := IntToStr(x + (Sender as TWincontrol).Left);
            LblPosY.Caption := IntToStr(y + (Sender as TWinControl).Top);
      end;
}
      LblPosX.Caption := IntToStr(x);
      LblPosY.Caption := IntToStr(y);

      FormMouseMove(Sender, Shift, X, Y);
end;

procedure TCad0002.ShpVultoDragOver(Sender, Source: TObject; X, Y: Integer;
  State: TDragState; var Accept: Boolean);
begin
  inherited;
      SbxFundoDragOver(Sender, Source, x + ShpVulto.Left, y + ShpVulto.Top,
                                                                     dsDragEnter, Accept);
end;

procedure TCad0002.ShpVultoEndDrag(Sender, Target: TObject; X, Y: Integer);
begin
  inherited;
      ShpVulto.Visible := False;

      if Sender is TPanel then
      begin
            (Sender as TPanel).Tag := 0;
      end;

      gDsComCli.Left   := ShpVulto.Left;
      gDsComCli.Top    := ShpVulto.Top;
      gDsComCli.Width  := ShpVulto.Width;
      gDsComCli.Height := ShpVulto.Height;

      EdtVlLeft.BrAsInteger   := ShpVulto.Left;
      EdtVlTop.BrAsInteger    := ShpVulto.Top;
      EdtVlWidth.BrAsInteger  := ShpVulto.Width;
      EdtVlHeigth.BrAsInteger := ShpVulto.Height;

      if Pos(gDsCompon[gDsComCli.Tag].TpCompon, 'L') <> 0 then
      begin
            ComponenteFrenteClick(gDsComCli);
      end else
      begin
            ContornoComponente;
      end;
end;

procedure TCad0002.ComponenteFundoClick(Sender : TObject);
begin
      EncontrarComponenteClicado((Sender as TComponent).Tag);
      ContornoComponente;

      CbxVisibl.Enabled   := False;
      ChxEnabled.Enabled  := False;
      CbxAutInc.Enabled   := False;
      CbxSnNulo.Enabled   := False;
      SbtConsul.Enabled   := False;
      SbtDefaul.Enabled   := False;
      EdtVlLeft.Enabled   := False;
      EdtVlTop.Enabled    := False;
      EdtVlWidth.Enabled  := False;
      EdtVlHeigth.Enabled := False;
      EdtDsCaptio.Enabled := False;
      EdtDsCapGri.Enabled := False;
      CbxTpObjeto.Enabled := False;

      CbxAncAci.Enabled   := SbxFundo <> (Sender as TComponent);
      CbxAncAba.Enabled   := CbxAncAci.Enabled;
      CbxAncEsq.Enabled   := CbxAncAci.Enabled;
      CbxAncDir.Enabled   := CbxAncAci.Enabled;

      CbxAncAci.Checked       := gDsCompon[(Sender as TComponent).Tag].SnAncAci;
      CbxAncAba.Checked       := gDsCompon[(Sender as TComponent).Tag].SnAncAba;
      CbxAncEsq.Checked       := gDsCompon[(Sender as TComponent).Tag].SnAncEsq;
      CbxAncDir.Checked       := gDsCompon[(Sender as TComponent).Tag].SnAncDir;
      EdtVlLeft.BrAsInteger   := gDsComCli.Left;
      EdtVlTop.BrAsInteger    := gDsComCli.Top;
      EdtVlWidth.BrAsInteger  := gDsComCli.Width;
      EdtVlHeigth.BrAsInteger := gDsComCli.Height;
      EdtDsCaptio.Text        := gDsCompon[(Sender as TComponent).Tag].DsCaptio;
      LblNrConsul.Caption     := IntToStr(gDsCompon[(Sender as TComponent).Tag].NrQueCon);
      LblDsDefaul.Caption     := gDsCompon[(Sender as TComponent).Tag].TpDefaul + '-' +
                                 gDsCompon[(Sender as TComponent).Tag].VrDefaul;

      if SbtSeta.Down then
      begin
           if gDsCompon[(Sender as TComponent).Tag].TpCompon = 'T' then
           begin
                 ComponenteFrenteClick(Sender);
           end
      end
      else if SbtLabel.Down then
           begin
                 SbtSeta.Down := True;
                 inc(gNoCompon);
                 CriarLabel('Label', 'L', True, False, '',
                            StrToInt(LblPosX.Caption),
                            StrToInt(LblPosY.Caption), 13, 38, gDsComCli,
                            0, '', -1, gNoCompon, '', '', 0, '',
                            True, False, True, False);
           end
           else if SbtPanel.Down then
                begin
                      SbtSeta.Down := True;
                      inc(gNoCompon);
                      CriarPanel(gDsComCli, StrToInt(LblPosX.Caption),
                                 StrToInt(LblPosY.Caption), 50, 50, gNoCompon,
                                 True, False, True, False);
                end
           else if SbtPagCon.Down then
                begin
                      SbtSeta.Down := True;
                      inc(gNoCompon);
                      CriarPageControl(gDsComCli, StrToInt(LblPosX.Caption),
                                       StrToInt(LblPosY.Caption), 50, 50, gNoCompon,
                                       True, False, True, False);

                      AdicionarPgina1Click(nil);
                end
     ;
end;

procedure TCad0002.CriarPanel(pDsParent : TWinControl; pVlLeft  : Integer;
                              pVlTop    : Integer;     pVlWidth : Integer;
                              pVlHeight : Integer;     pNrCompon : Integer;
                              pSnAncAci : Boolean;     pSnAncAba : Boolean;
                              pSnAncEsq : Boolean;     pSnAncDir : Boolean);
var lDsPanel : TPanel;
begin
      ConfigurarComponente(pNrCompon, 'P', '', 'Panel', pDsParent.Tag,
                           True, True, False, True, '', 0,
                           0, 0, nil,  -1, '', '', '', '',
                           pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsPanel             := TPanel.Create(Self);
      lDsPanel.Tag         := pNrCompon;
      lDsPanel.Parent      := pDsParent;
      lDsPanel.Left        := pVlLeft;
      lDsPanel.Top         := pVlTop;
      lDsPanel.Width       := pVlWidth;
      lDsPanel.Height      := pVlHeight;
      lDsPanel.OnClick     := ComponenteFundoClick;
      lDsPanel.OnMouseMove := SbxFundoMouseMove;
      lDsPanel.OnDragDrop  := SbxFundoDragDrop;
      lDsPanel.OnDragOver  := ComponenteDragOver;
      lDsPanel.OnMouseDown := ComponenteMouseDown;
      lDsPanel.Name        := '_' + FormatFloat('0000', pNrCompon);
      lDsPanel.Caption     := '';
      lDsPanel.Anchors         := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      ComponenteFundoClick(lDsPanel);
end;

procedure TCad0002.CriarPageControl(pDsParent : TWinControl; pVlLeft   : Integer;
                                    pVlTop    : Integer;     pVlWidth  : Integer;
                                    pVlHeight : Integer;     pNrCompon : Integer;
                                    pSnAncAci : Boolean;     pSnAncAba : Boolean;
                                    pSnAncEsq : Boolean;     pSnAncDir : Boolean);

var lDsPagCtr : TPageControl;
begin
      ConfigurarComponente(pNrCompon, 'G', '', 'PageControl', pDsParent.Tag,
                           True, True, False, True, '', 0,
                           0, 0, nil,  -1, '', '', '', '',
                           pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      lDsPagCtr             := TPageControl.Create(Self);
      lDsPagCtr.Tag         := pNrCompon;
      lDsPagCtr.Parent      := pDsParent;
      lDsPagCtr.Left        := pVlLeft;
      lDsPagCtr.Top         := pVlTop;
      lDsPagCtr.Width       := pVlWidth;
      lDsPagCtr.Height      := pVlHeight;
      lDsPagCtr.Name        := '_' + FormatFloat('0000', pNrCompon);
      lDsPagCtr.PopupMenu   := PopPagCtr;
      lDsPagCtr.OnMouseDown := TabSheetOnMouseDown;
      lDsPagCtr.Anchors     := Ancoras(pSnAncAci, pSnAncAba, pSnAncEsq, pSnAncDir);

      gDsComCli           := lDsPagCtr;
end;

procedure TCad0002.TabSheetOnMouseDown(Sender: TObject; Button: TMouseButton;
                                       Shift: TShiftState; X, Y: Integer);
begin
      if (Button in [mbLeft]) or
         (Sender is TPageControl) then
      begin
            ComponenteFundoClick(Sender);

            if ssCtrl in Shift then
            begin
                  ArrastarComponente(x, y);
            end;
      end else
      begin
            ComponenteFundoClick((Sender as TTabSheet).PageControl);
      end;
end;

procedure TCad0002.AdicionarPgina1Click(Sender: TObject);
begin
      inc(gNoCompon);
      CriarTabSheet(gDsComCli, 'TabSheet', gNoCompon);
end;

procedure TCad0002.CriarTabSheet(pDsParent : TWinControl; pDsCaptio : String;
                                 pNrCompon : Integer);
var lDsTabShe : TTabSheet;
begin
      ConfigurarComponente(pNrCompon, 'T', '', pDsCaptio, pDsParent.Tag,
                           True, True, True, True, '', 0,
                           0, 0, nil,  -1, '', '', '', '',
                           True, False, True, False);

      lDsTabShe             := TTabSheet.Create(Self);
      lDsTabShe.Tag         := pNrCompon;
      lDsTabShe.Parent      := pDsParent;
      lDsTabShe.PageControl := TPageControl(FindComponent(pDsParent.Name));
      lDsTabShe.OnMouseMove := SbxFundoMouseMove;
      lDsTAbShe.OnDragOver  := ComponenteDragOver;
      lDsTabShe.OnDragDrop  := SbxFundoDragDrop;
      lDsTabShe.Name        := '_' + FormatFloat('0000', pNrCompon);
      lDsTabShe.Caption     := pDsCaptio;
      lDsTabShe.OnMouseDown := TabSheetOnMouseDown;

      ComponenteFundoClick(lDsTabShe);
end;

initialization
      RegisterClass(TCad0002);

finalization
      UnRegisterClass(TCad0002);

end.
