unit Unit8;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DBCtrls, StdCtrls, ActRadioButton, ExtCtrls, ActMask,
  ActDateEdit, ActCurrencyEdit, ActEdit, ActCustomEdit, ActNumberEdit, DB,
  IBCustomDataSet, IBQuery, IBDatabase, Grids, DBGrids, Buttons,UNFENTRADASDDL,
  IBUpdateSQL, ACBrNFe, ACBrNFeDANFEClass, ACBrNFeDANFERave, ComCtrls,
  OleCtrls, SHDocVw, ActResultEdit,
  IniFiles, ShellAPI,
  pcnConversao, ACBrUtil,Unit5,UBDLFornecedores,
  IBTable,pcnNFeW, pcnNFeRTXT, UDLLNFFISCAL,UDLLNFFISCALITENS, Menus;


type
  TForm7 = class(TForm)
    IBQuery1: TIBQuery;
    IBQuery1DCNUMERO: TIntegerField;
    IBQuery1DCSERIE: TIBStringField;
    IBQuery1DCORDEM: TIBStringField;
    IBQuery1DCTIPO: TIBStringField;
    IBQuery1NATUREZA: TIntegerField;
    IBQuery1VLNOTA: TFloatField;
    IBQuery1VLICMS: TFloatField;
    IBQuery1VLIPI: TFloatField;
    IBQuery1VLFRETE: TFloatField;
    IBQuery1CFOP: TFloatField;
    IBQuery1DTENTRADA: TDateField;
    IBQuery1DTEMISSAO: TDateField;
    IBQuery1TRANSPORTADO: TFloatField;
    IBQuery1CDPEDIDO: TFloatField;
    IBQuery1IDNFENTRADAS: TIntegerField;
    IBQuery1VLST: TFloatField;
    IBQuery1TPSITUACAO: TIBStringField;
    IBQuery1CDFORNECEDOR: TIntegerField;
    DataSource1: TDataSource;
    Panel1: TPanel;
    TXTDCNUMERO: TActNumberEdit;
    TXTDCSERIE: TActEdit;
    TXTDCORDEM: TActEdit;
    TXTICMS: TActCurrencyEdit;
    TXTIPI: TActCurrencyEdit;
    TXTVLNOTA: TActCurrencyEdit;
    TXTDTENTRADA: TActDateEdit;
    TXTDTEMISSAO: TActDateEdit;
    TXTBASEST: TActCurrencyEdit;
    RadioGroup1: TRadioGroup;
    ActRadioButton1: TActRadioButton;
    ActRadioButton2: TActRadioButton;
    IBQuery2: TIBQuery;
    IBQuery3: TIBQuery;
    DataSource2: TDataSource;
    DataSource3: TDataSource;
    Panel2: TPanel;
    GroupBox1: TGroupBox;
    txtnoparcela: TActNumberEdit;
    txtvalor: TActCurrencyEdit;
    SpeedButton1: TSpeedButton;
    Panel3: TPanel;
    DBGrid1: TDBGrid;
    DBGrid2: TDBGrid;
    Panel4: TPanel;
    IBQuery4: TIBQuery;
    IBQuery5: TIBQuery;
    DataSource4: TDataSource;
    IBQuery6: TIBQuery;
    DataSource5: TDataSource;
    SpeedButton7: TSpeedButton;
    SpeedButton8: TSpeedButton;
    SpeedButton5: TSpeedButton;
    SpeedButton6: TSpeedButton;
    SpeedButton2: TSpeedButton;
    SpeedButton3: TSpeedButton;
    SpeedButton4: TSpeedButton;
    SpeedButton9: TSpeedButton;
    SpeedButton10: TSpeedButton;
    SpeedButton11: TSpeedButton;
    Panel5: TPanel;
    DBText1: TDBText;
    DBText2: TDBText;
    Label27: TLabel;
    Label28: TLabel;
    SpeedButton13: TSpeedButton;
    txtquantidade: TActNumberEdit;
    txtvlunitario: TActCurrencyEdit;
    TXTICMSI: TActCurrencyEdit;
    TXTICMSBASEI: TActCurrencyEdit;
    txtdtvencimento: TActDateEdit;
    IBQgravartitulospagar: TIBQuery;
    IBQgravarnfentitens: TIBQuery;
    IBQgravnfentrad: TIBQuery;
    IBQuery7: TIBQuery;
    IBQuery8: TIBQuery;
    qryprodutos1: TIBQuery;
    qryMovEstoque: TIBQuery;
    IBQuery9: TIBQuery;
    ACBrNFeDANFERave1: TACBrNFeDANFERave;
    ACBrNFe1: TACBrNFe;
    OpenDialog1: TOpenDialog;
    SpeedButton14: TSpeedButton;
    SpeedButton15: TSpeedButton;
    SpeedButton16: TSpeedButton;
    IBQuery10: TIBQuery;
    TXTDCTIPO: TActEdit;
    IBUpdateSQL1: TIBUpdateSQL;
    IBQuery11: TIBQuery;
    TXTICMSBASE: TActCurrencyEdit;
    IBQuery12: TIBQuery;
    Label25: TLabel;
    Label26: TLabel;
    Label31: TLabel;
    Label35: TLabel;
    Label36: TLabel;
    Label37: TLabel;
    TXTCFOP: TActResultEdit;
    TXTST: TActCurrencyEdit;
    TXTPIS: TActCurrencyEdit;
    TXTCONFINS: TActCurrencyEdit;
    TXTVL_DESC: TActCurrencyEdit;
    TXTCDPEDIDO: TActResultEdit;
    TXTFRETE: TActResultEdit;
    txtnatureza: TActResultEdit;
    TXTCHACESSO: TActEdit;
    TXTPROT: TActEdit;
    TXTSTATUSNFE: TActEdit;
    QryNFFISCAL: TIBQuery;
    QryNFFISCALIND_OPER: TIntegerField;
    QryNFFISCALIND_EMIT: TIntegerField;
    QryNFFISCALCOD_PART: TIBStringField;
    QryNFFISCALCOD_SIT: TIBStringField;
    QryNFFISCALDT_DOC: TDateField;
    QryNFFISCALDT_E_S: TDateField;
    QryNFFISCALVL_DOC: TIntegerField;
    QryNFFISCALVL_DESC: TIntegerField;
    QryNFFISCALVL_FORN: TIntegerField;
    QryNFFISCALVL_SERV_NT: TIntegerField;
    QryNFFISCALVL_TERC: TIntegerField;
    QryNFFISCALVL_DA: TIntegerField;
    QryNFFISCALVL_BC_ICMS: TIntegerField;
    QryNFFISCALVL_ICMS: TIntegerField;
    QryNFFISCALVL_BC_ICMS_ST: TIntegerField;
    QryNFFISCALVL_ICMS_ST: TIntegerField;
    QryNFFISCALCOD_INF: TIntegerField;
    QryNFFISCALVL_COFINS: TIntegerField;
    QryNFFISCALTP_LIGACAO: TIntegerField;
    QryNFFISCALCOD_GRUPO_TENSAO: TIBStringField;
    QryNFFISCALCHV_CTE: TIBStringField;
    QryNFFISCALTP_CT: TIBStringField;
    QryNFFISCALCHV_CTE_REF: TIBStringField;
    QryNFFISCALIND_FRT: TIBStringField;
    QryNFFISCALCODNFE: TIntegerField;
    QryNFFISCALCOD_EMPRESA: TIntegerField;
    GroupBox2: TGroupBox;
    TXTEMPRESA: TActResultEdit;
    TXTFILIAL: TActResultEdit;
    TXTCODPRO: TActResultEdit;
    TXTTIPO_TIT: TActResultEdit;
    TXTDESCONTO: TActCurrencyEdit;
    TXTACRESCIMO: TActCurrencyEdit;
    txtDesc_itens: TActNumberEdit;
    IBQuery13: TIBQuery;
    IBUpdateSQL2: TIBUpdateSQL;
    TXTBASE_ST: TActCurrencyEdit;
    ActRadioButton3: TActRadioButton;
    ActRadioButton4: TActRadioButton;
    TXTVL_ST: TActCurrencyEdit;
    TXTCFOP_I: TActResultEdit;
    IBUpdateSQL3: TIBUpdateSQL;
    IBQuery14: TIBQuery;
    DataSource6: TDataSource;
    PopupMenu1: TPopupMenu;
    EXCLUIR1: TMenuItem;
    ATUALIZAR1: TMenuItem;
    IBQuery15: TIBQuery;
    SpeedButton12: TSpeedButton;
    Panel6: TPanel;
    TXTNUMNF: TActResultEdit;
    ActResultEdit1: TActResultEdit;
    IBQuery16: TIBQuery;
    procedure SpeedButton7Click(Sender: TObject);
    procedure SpeedButton8Click(Sender: TObject);
    procedure SpeedButton5Click(Sender: TObject);
    procedure SpeedButton6Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure SpeedButton4Click(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton13Click(Sender: TObject);
    procedure DBGrid1DblClick(Sender: TObject);
    procedure txtcodproExit(Sender: TObject);
    procedure SpeedButton14Click(Sender: TObject);
    procedure SpeedButton15Click(Sender: TObject);
    procedure SpeedButton16Click(Sender: TObject);
    procedure SpeedButton17Click(Sender: TObject);
    procedure SpeedButton18Click(Sender: TObject);
    procedure DBGrid2DblClick(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure TXTCODPROChange(Sender: TObject);
    procedure EXCLUIR1Click(Sender: TObject);
    procedure SpeedButton10Click(Sender: TObject);
    procedure TXTEMPRESAButtonClick(Sender: TObject);
    procedure txtnaturezaButtonClick(Sender: TObject);
    procedure txtfornecedorButtonClick(Sender: TObject);
    procedure ActResultEdit1ButtonClick(Sender: TObject);
    procedure TXTCODPROButtonClick(Sender: TObject);
    procedure TXTTIPO_TITButtonClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure ATUALIZAR1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    function Atualizagrid(const dcnumero :Integer):Boolean;
    function populaTXT():Boolean;
    function populacampos():Boolean;
    function GRAVARTITULO():Boolean;
    function GRAVARNFENTRADASITENS():Boolean;
    function GRAVARNFENTRADAS():Boolean;
    function POPULANFENTRADASITENS():Boolean;
    function POPULANFENTRADAS():Boolean;
    function ApuraValores():boolean;
    function AumentaSaldoEstoque(const cdproduto : integer;quantidade: real):boolean;
    function DiminuiSaldoEstoque(const cdproduto : integer;quantidade: real):boolean;
    function apagatitulospagar(const dcnumero,noparcela:Integer;dcserie,dcordem,dctipo:String):boolean;
    function apagaitens(const dcnumero,codpro,quantidade:Integer;  dcserie,dcordem,dctipo:String):boolean;
    function apaganf(const dcnumero:Integer; dcserie,dcordem,dctipo:String ):boolean;
    function proximo():boolean;
    function anterior():boolean;
    function primeiro():boolean;
    function ultimo():boolean;
    function auteranf():boolean;
    function selectprinc():boolean;
    Function LimpaCampos():Boolean;
    Function Alteraritens():Boolean;
    Function Alterar():Boolean;
    Function deletar():Boolean;
    Function ValidaFornecedor (const cd : Integer ):Boolean;
    Function ValidaProdutos (const cdpro : Integer;NCM:String):Boolean;
    Function VerificaProduto(cdpro :integer):Boolean;
    function ajustaVirgula(const frase : String): String;
    function ajustaData(const frase : String): String;
    function ajustaSpaco(const frase : String): String;
   // function auterarItens(quantidade,ipi,st,icms,vlunitario,vldesconto:Double; cdfornecedor,DCNUMERO,CDPRODUTO,CODPRO:Integer;DCSERIE,DCORDEM,DCTIPO:String):Boolean;
    procedure GravarConfiguracao ;
    procedure LerConfiguracao ;
    procedure LoadXML(MyMemo: TMemo; MyWebBrowser: TWebBrowser);
    Function GravarProdutos ():Boolean;
    function POPULARNFFFISCAL():BOOLEAN;
    function ajustaPONTO(const frase : String): String;
    function Desabilita():Boolean;
  end;

var
  Form7: TForm7;
  nfentradas :TNFENTRADAS;
  Produtos   :TProduto;
  fornecedores   :TFornecedores;
  NFFISCAL   :TNFISCAL;
  NFISCALitens :TNFISCALitens;
  Gravar :Boolean;
  somaItens,SomaTitulos,SomaNF :Double;
implementation

uses FileCtrl, pcnNFe,  ACBrNFeNotasFiscais, DateUtils, ACBrNFeUtil,
  Unit4, Unit10, Unit7, Unit1, UFormCadProServ, UFrmRateio, UConsEmpr,
  UConsTransacao, UConsForn, UConsProd, UConsTipoTitulo;

const
  SELDIRHELP = 1000;

{$R *.dfm}

function TForm7.Desabilita():Boolean;
begin
   RadioGroup1.Enabled := False;
   ActRadioButton1.Checked := True;
   TXTEMPRESA.Enabled := False;
   TXTFILIAL.Enabled := False;
   TXTDCNUMERO.Enabled := False;
   TXTDCSERIE.Enabled := False;
   TXTDCORDEM.Enabled := False;
   TXTDCTIPO.Enabled := False;
   TXTICMS.Enabled := False;
   TXTICMSBASE.Enabled := False;
   TXTBASEST.Enabled := False;
   TXTST.Enabled := False;
   TXTPIS.Enabled := False;
   TXTCONFINS.Enabled := False;
   TXTVLNOTA.Enabled := False;
   TXTVL_DESC.Enabled := False;
   TXTDTEMISSAO.Enabled := False;
   TXTDTENTRADA.Enabled :=  False;
   TXTCFOP.Enabled := False;
   TXTIPI.Enabled := False;
   TXTCDPEDIDO.Enabled := False;
   TXTFRETE.Enabled := False;
   txtnatureza.Enabled := False;
 //  txtfornecedor.Enabled := False;
   TXTCHACESSO.Enabled := False;
   TXTPROT.Enabled := False;
   TXTSTATUSNFE.Enabled := False;


end;


Function TForm7.Alterar():Boolean;
begin
try
IBQgravnfentrad.Active := False;
IBQgravnfentrad.SQL.Clear;
IBQgravnfentrad.SQL.Add('update nffiscal ');
IBQgravnfentrad.SQL.Add('set ');
IBQgravnfentrad.SQL.Add('  IND_OPER = :IND_OPER, ');
IBQgravnfentrad.SQL.Add('  IND_EMIT = :IND_EMIT, ');
IBQgravnfentrad.SQL.Add('  COD_PART = :COD_PART, ');
IBQgravnfentrad.SQL.Add('  COD_SIT = :COD_SIT,   ');
IBQgravnfentrad.SQL.Add('  DT_DOC = :DT_DOC,     ');
IBQgravnfentrad.SQL.Add('  DT_E_S = :DT_E_S,     ');
IBQgravnfentrad.SQL.Add('  VL_DOC = :VL_DOC,     ');
IBQgravnfentrad.SQL.Add('  VL_DESC = :VL_DESC,   ');
IBQgravnfentrad.SQL.Add('  VL_FORN = :VL_FORN,   ');
IBQgravnfentrad.SQL.Add('  VL_SERV_NT = :VL_SERV_NT, ');
IBQgravnfentrad.SQL.Add('  VL_TERC = :VL_TERC,       ');
IBQgravnfentrad.SQL.Add('  VL_DA = :VL_DA,           ');
IBQgravnfentrad.SQL.Add('  VL_BC_ICMS = :VL_BC_ICMS, ');
IBQgravnfentrad.SQL.Add('  VL_ICMS = :VL_ICMS,       ');
IBQgravnfentrad.SQL.Add('  VL_BC_ICMS_ST = :VL_BC_ICMS_ST, ');
IBQgravnfentrad.SQL.Add('  VL_ICMS_ST = :VL_ICMS_ST, ');
IBQgravnfentrad.SQL.Add('  COD_INF = :COD_INF,       ');
IBQgravnfentrad.SQL.Add('  VL_COFINS = :VL_COFINS,   ');
IBQgravnfentrad.SQL.Add('  TP_LIGACAO = :TP_LIGACAO, ');
IBQgravnfentrad.SQL.Add('  COD_GRUPO_TENSAO = :COD_GRUPO_TENSAO, ');
IBQgravnfentrad.SQL.Add('  CHV_CTE = :CHV_CTE, ');
IBQgravnfentrad.SQL.Add('  TP_CT = :TP_CT,     ');
IBQgravnfentrad.SQL.Add('  CHV_CTE_REF = :CHV_CTE_REF,');
IBQgravnfentrad.SQL.Add('  IND_FRT = :IND_FRT, ');
IBQgravnfentrad.SQL.Add('  CODNFE = :CODNFE,   ');
IBQgravnfentrad.SQL.Add('  COD_EMPRESA = :COD_EMPRESA, ');
IBQgravnfentrad.SQL.Add('  COD_FILIAL = :COD_FILIAL,   ');
IBQgravnfentrad.SQL.Add('  DCNUMERO = :DCNUMERO,       ');
IBQgravnfentrad.SQL.Add('  DCSERIE = :DCSERIE,        ');
IBQgravnfentrad.SQL.Add('  DCORDEM = :DCORDEM,        ');
IBQgravnfentrad.SQL.Add('  DCTIPO = :DCTIPO,          ');
IBQgravnfentrad.SQL.Add('  CFOP = :CFOP,              ');
IBQgravnfentrad.SQL.Add('  CDPEDIDO = :CDPEDIDO,      ');
IBQgravnfentrad.SQL.Add('  NATUREZA = :NATUREZA      ');
IBQgravnfentrad.SQL.Add('where                       ');
IBQgravnfentrad.SQL.Add('  COD_EMPRESA = :OLD_COD_EMPRESA and  ');
IBQgravnfentrad.SQL.Add('  COD_FILIAL = :OLD_COD_FILIAL and    ');
IBQgravnfentrad.SQL.Add('  DCNUMERO = :OLD_DCNUMERO        ');

  IBQgravnfentrad.ParamByName('OLD_COD_EMPRESA').AsInteger := NFFISCAL.COD_EMPRESA;
  IBQgravnfentrad.ParamByName('OLD_COD_FILIAL').AsInteger := NFFISCAL.Cod_Filial;
  IBQgravnfentrad.ParamByName('OLD_DCNUMERO').AsInteger := NFFISCAL.DCNUMERO;


  IBQgravnfentrad.ParamByName('IND_OPER').AsInteger := NFFISCAL.IND_OPER;
  IBQgravnfentrad.ParamByName('IND_EMIT').AsInteger := NFFISCAL.IND_EMIT;
  IBQgravnfentrad.ParamByName('COD_PART').AsString := IntToStr(NFFISCAL.COD_PART);
  IBQgravnfentrad.ParamByName('COD_SIT').AsString := NFFISCAL.COD_SIT;
  IBQgravnfentrad.ParamByName('DT_DOC').AsDateTime:= NFFISCAL.DTEMISSAO;
  IBQgravnfentrad.ParamByName('DT_E_S').AsDateTime := NFFISCAL.DTENTRADA;
  IBQgravnfentrad.ParamByName('VL_DOC').AsFloat := NFFISCAL.VL_DOC;
  IBQgravnfentrad.ParamByName('VL_DESC').AsFloat  := NFFISCAL.VL_DESC;
  IBQgravnfentrad.ParamByName('VL_FORN').AsFloat  := NFFISCAL.VL_FORN;
  IBQgravnfentrad.ParamByName('VL_SERV_NT').AsFloat  := NFFISCAL.VL_SERV_NT;
  IBQgravnfentrad.ParamByName('VL_TERC').AsFloat  := NFFISCAL.VL_TERC;
  IBQgravnfentrad.ParamByName('VL_DA').AsFloat  := NFFISCAL.VL_DA;
  IBQgravnfentrad.ParamByName('VL_BC_ICMS').AsFloat  := NFFISCAL.VL_BC_ICMS;
  IBQgravnfentrad.ParamByName('VL_ICMS').AsFloat  := NFFISCAL.VL_ICMS;
  IBQgravnfentrad.ParamByName('VL_BC_ICMS_ST').AsFloat  := NFFISCAL.VL_BC_ICMS_ST;
  IBQgravnfentrad.ParamByName('VL_ICMS_ST').AsFloat  := NFFISCAL.VL_ICMS_ST;
  IBQgravnfentrad.ParamByName('COD_INF').AsFloat  := NFFISCAL.COD_INF;
  IBQgravnfentrad.ParamByName('VL_COFINS').AsFloat := NFFISCAL.VL_COFINS;
  IBQgravnfentrad.ParamByName('TP_LIGACAO').AsFloat := NFFISCAL.TP_LIGACAO ;
  IBQgravnfentrad.ParamByName('COD_GRUPO_TENSAO').Asstring := NFFISCAL.COD_GRUPO_TENSAO;
  IBQgravnfentrad.ParamByName('CHV_CTE').AsString := NFFISCAL.CHV_CTE;
  IBQgravnfentrad.ParamByName('TP_CT').AsString := NFFISCAL.TP_CT;
  IBQgravnfentrad.ParamByName('CHV_CTE_REF').AsString := NFFISCAL.CHV_CTE_REF;
  IBQgravnfentrad.ParamByName('IND_FRT').AsString := NFFISCAL.IND_FRT;
  IBQgravnfentrad.ParamByName('COD_EMPRESA').AsInteger := NFFISCAL.COD_EMPRESA;
  IBQgravnfentrad.ParamByName('CODNFE').AsInteger := NFFISCAL.DCNUMERO;
  IBQgravnfentrad.ParamByName('DCNUMERO').AsInteger := NFFISCAL.DCNUMERO;
  IBQgravnfentrad.ParamByName('DCSERIE').AsString:= NFFISCAL.DCSERIE;
  IBQgravnfentrad.ParamByName('DCORDEM').AsString := NFFISCAL.DCORDEM;
  IBQgravnfentrad.ParamByName('DCTIPO').AsString := NFFISCAL.DCTIPO;
  IBQgravnfentrad.ParamByName('cdPEDIDO').AsInteger := NFFISCAL.CDPEDIDO;
  IBQgravnfentrad.ParamByName('NATUREZA').AsInteger := NFFISCAL.NATUREZA;
  IBQgravnfentrad.ParamByName('CFOP').AsString := NFFISCAL.CFOP;
  IBQgravnfentrad.ParamByName('COD_FILIAL').AsInteger := NFFISCAL.Cod_Filial;
  IBQgravnfentrad.ExecSQL;
  IBQgravnfentrad.Transaction.Commit;
  result := True;
 Except
   result := False;
 end;
end;




Function TForm7.deletar():Boolean;
begin

end;


Function TForm7.Alteraritens():Boolean;
begin
try
IBQgravarnfentitens.sql.Clear;
IBQgravarnfentitens.sql.Add('');
IBQgravarnfentitens.sql.Add(' update NFFISCALITENS ');
IBQgravarnfentitens.sql.Add('set ');
IBQgravarnfentitens.sql.Add('  CODNFE = :CODNFE,');
IBQgravarnfentitens.sql.Add('  COD_EMPRESA = :COD_EMPRESA, ');
IBQgravarnfentitens.sql.Add('  NUM_ITEM = :NUM_ITEM, ');
IBQgravarnfentitens.sql.Add('  COD_ITEM = :COD_ITEM, ');
IBQgravarnfentitens.sql.Add('  DESCR_COMPL = :DESCR_COMPL,');
IBQgravarnfentitens.sql.Add('  QTD = :QTD, ');
IBQgravarnfentitens.sql.Add('  UNID = :UNID, ');
IBQgravarnfentitens.sql.Add('  VL_ITEM = :VL_ITEM,  ');
IBQgravarnfentitens.sql.Add('  VL_DESC = :VL_DESC, ');
IBQgravarnfentitens.sql.Add('  IND_MOV = :IND_MOV,  ');
IBQgravarnfentitens.sql.Add('  CST_ICMS = :CST_ICMS,');
IBQgravarnfentitens.sql.Add('  CFOP = :CFOP,');
IBQgravarnfentitens.sql.Add('  ALIQ_ICMS = :ALIQ_ICMS,');
IBQgravarnfentitens.sql.Add('  VL_ICMS = :VL_ICMS,');
IBQgravarnfentitens.sql.Add('  VL_BC_ICMS_ST = :VL_BC_ICMS_ST,');
IBQgravarnfentitens.sql.Add('  ALIQ_ST = :ALIQ_ST, ');
IBQgravarnfentitens.sql.Add('  VL_ICMS_ST = :VL_ICMS_ST, ');
IBQgravarnfentitens.sql.Add('  IND_APUR = :IND_APUR, ');
IBQgravarnfentitens.sql.Add('  CST_IPI = :CST_IPI,   ');
IBQgravarnfentitens.sql.Add('  COD_ENQ = :COD_ENQ,   ');
IBQgravarnfentitens.sql.Add('  VL_BC_IPI = :VL_BC_IPI, ');
IBQgravarnfentitens.sql.Add('  ALIQ_IPI = :ALIQ_IPI,   ');
IBQgravarnfentitens.sql.Add('  VL_IPI = :VL_IPI,       ');
IBQgravarnfentitens.sql.Add('  CST_PIS = :CST_PIS,     ');
IBQgravarnfentitens.sql.Add('  VL_BC_PIS = :VL_BC_PIS, ');
IBQgravarnfentitens.sql.Add('  ALIQ_PIS = :ALIQ_PIS,   ');
IBQgravarnfentitens.sql.Add('  QUANT_BC_PIS = :QUANT_BC_PIS, ');
IBQgravarnfentitens.sql.Add('  VL_PIS = :VL_PIS,              ');
IBQgravarnfentitens.sql.Add('  CST_COFINS = :CST_COFINS,     ');
IBQgravarnfentitens.sql.Add('  VL_BC_COFINS = :VL_BC_COFINS, ');
IBQgravarnfentitens.sql.Add('  ALIQ_COFINS = :ALIQ_COFINS,   ');
IBQgravarnfentitens.sql.Add('  QUANT_BC_COFINS = :QUANT_BC_COFINS, ');
IBQgravarnfentitens.sql.Add('  VL_COFINS = :VL_COFINS,');
IBQgravarnfentitens.sql.Add('  COD_CTA = :COD_CTA,    ');
IBQgravarnfentitens.sql.Add('  DCNUMERO = :DCNUMERO,  ');
IBQgravarnfentitens.sql.Add('  DCORDEM = :DCORDEM,    ');
IBQgravarnfentitens.sql.Add('  DCSERIE = :DCSERIE,    ');
IBQgravarnfentitens.sql.Add('  DCTIPO = :DCTIPO       ');
IBQgravarnfentitens.sql.Add('where                    ');
IBQgravarnfentitens.sql.Add('  COD_ITEM = :OLD_COD_ITEM and  ');
IBQgravarnfentitens.sql.Add('  DCNUMERO = :OLD_DCNUMERO and  ');
IBQgravarnfentitens.sql.Add('  DCORDEM = :OLD_DCORDEM and ');
IBQgravarnfentitens.sql.Add('  DCSERIE = :OLD_DCSERIE and ');
IBQgravarnfentitens.sql.Add('  DCTIPO = :OLD_DCTIPO       ');
IBQgravarnfentitens.ParamByName('OLD_COD_ITEM').AsInteger := NFISCALitens.COD_ITEM;
IBQgravarnfentitens.ParamByName('OLD_DCNUMERO').AsInteger := NFISCALitens.DCNUMERO;
IBQgravarnfentitens.ParamByName('OLD_DCORDEM').AsString := NFISCALitens.DCORDEM;
IBQgravarnfentitens.ParamByName('OLD_DCSERIE').AsString := NFISCALitens.DCSERIE;
IBQgravarnfentitens.ParamByName('OLD_DCTIPO').AsString := NFISCALitens.DCTIPO;

IBQgravarnfentitens.ParamByName('CODNFE').AsInteger :=         NFISCALitens.DCNUMERO;
  IBQgravarnfentitens.ParamByName('COD_EMPRESA').AsInteger :=    NFISCALitens.COD_EMPRESA;
  IBQgravarnfentitens.ParamByName('NUM_ITEM').AsInteger :=       1;
  IBQgravarnfentitens.ParamByName('COD_ITEM').AsInteger :=       NFISCALitens.COD_ITEM;
  IBQgravarnfentitens.ParamByName('DESCR_COMPL').AsString :=     NFISCALitens.CDESCR_COMPL ;
  IBQgravarnfentitens.ParamByName('QTD').AsFloat :=              NFISCALitens.QTD ;
  IBQgravarnfentitens.ParamByName('UNID').AsFloat :=             NFISCALitens.UNID;
  IBQgravarnfentitens.ParamByName('VL_ITEM').AsFloat :=          NFISCALitens.VL_ITEM;
  IBQgravarnfentitens.ParamByName('VL_DESC').AsFloat :=          NFISCALitens.VL_DESC;
  IBQgravarnfentitens.ParamByName('IND_MOV').AsFloat :=          NFISCALitens.IND_MOV;
  IBQgravarnfentitens.ParamByName('CST_ICMS').AsFloat :=         NFISCALitens.CST_ICMS;
  IBQgravarnfentitens.ParamByName('CFOP').AsString :=            NFISCALitens.CFOP;
  IBQgravarnfentitens.ParamByName('ALIQ_ICMS').AsFloat :=        NFISCALitens.ALIQ_ICMS;
  IBQgravarnfentitens.ParamByName('VL_ICMS').AsFloat :=          NFISCALitens.VL_ICMS;
  IBQgravarnfentitens.ParamByName('VL_BC_ICMS_ST').AsFloat :=    NFISCALitens.VL_BC_ICMS_ST;
  IBQgravarnfentitens.ParamByName('ALIQ_ST').AsFloat :=          NFISCALitens.ALIQ_ST;
  IBQgravarnfentitens.ParamByName('VL_ICMS_ST').AsFloat :=       NFISCALitens.VL_ICMS_ST;
  IBQgravarnfentitens.ParamByName('IND_APUR').AsFloat  :=        NFISCALitens.IND_APUR;
  IBQgravarnfentitens.ParamByName('CST_IPI').AsFloat :=          NFISCALitens.CST_IPI;
  IBQgravarnfentitens.ParamByName('COD_ENQ').AsFloat :=          NFISCALitens.COD_ENQ;
  IBQgravarnfentitens.ParamByName('VL_BC_IPI').AsFloat :=        NFISCALitens.VL_BC_IPI;
  IBQgravarnfentitens.ParamByName('ALIQ_IPI').AsFloat :=         NFISCALitens.ALIQ_IPI;
  IBQgravarnfentitens.ParamByName('VL_IPI').AsFloat :=           NFISCALitens.VL_IPI;
  IBQgravarnfentitens.ParamByName('VL_BC_PIS').AsFloat :=        NFISCALitens.VL_BC_PIS;
  IBQgravarnfentitens.ParamByName('ALIQ_PIS').AsFloat :=         NFISCALitens.ALIQ_PIS;
  IBQgravarnfentitens.ParamByName('QUANT_BC_PIS').AsFloat:=      NFISCALitens.QUANT_BC_PIS;
  IBQgravarnfentitens.ParamByName('VL_PIS').AsFloat :=           NFISCALitens.VL_PI ;
  IBQgravarnfentitens.ParamByName('CST_COFINS').AsFloat :=       NFISCALitens.CST_COFINS;
  IBQgravarnfentitens.ParamByName('VL_BC_COFINS').AsFloat :=     NFISCALitens.VL_BC_COFINS;
  IBQgravarnfentitens.ParamByName('ALIQ_COFINS').AsFloat :=      NFISCALitens.ALIQ_COFINS;
  IBQgravarnfentitens.ParamByName('QUANT_BC_COFINS').AsFloat :=  NFISCALitens.QUANT_BC_COFINS;
  IBQgravarnfentitens.ParamByName('VL_COFINS').AsFloat :=        NFISCALitens.VL_COFINS;
  IBQgravarnfentitens.ParamByName('COD_CTA').AsFloat :=          NFISCALitens.COD_CTA;
  IBQgravarnfentitens.ParamByName('DCNUMERO').AsInteger :=       NFISCALitens.DCNUMERO ;
  IBQgravarnfentitens.ParamByName('DCSERIE').AsString :=         NFISCALitens.DCSERIE;
  IBQgravarnfentitens.ParamByName('DCORDEM').AsString :=         NFISCALitens.DCORDEM;
  IBQgravarnfentitens.ParamByName('DCTIPO').AsString :=          NFISCALitens.DCTIPO;
  IBQgravarnfentitens.ExecSQL;
  IBQgravarnfentitens.Transaction.Commit;
 //AumentaSaldoEstoque(NFENTRADAS.CODPRO,NFENTRADAS.QUANTIDADE);
  result := True;
 Except
   result := False;
 end;
 IBQuery5.Active := False;
 IBQuery5.Active := True;


end;


function TForm7.POPULARNFFFISCAL():BOOLEAN;
BEGIN
   if (NFFISCAL.COD_SIT =  'I') then
        ActRadioButton1.Checked := True;
   if (NFFISCAL.COD_SIT =  'P') then
        ActRadioButton2.Checked := True;
   if (NFFISCAL.COD_SIT =  'T') then
        ActRadioButton3.Checked := True;
   if (NFFISCAL.COD_SIT =  'C') then
        ActRadioButton4.Checked := True;

   TXTEMPRESA.Text :=   IntToStr(NFFISCAL.COD_EMPRESA  );
   TXTFILIAL.Text  :=   IntToStr(NFFISCAL.Cod_Filial  );
   TXTDCNUMERO.Text     :=  IntToStr(NFFISCAL.DCNUMERO  );
   TXTDCSERIE.Text     :=    NFFISCAL.DCSERIE;
   TXTDCORDEM.Text     :=    NFFISCAL.DCORDEM;
   TXTDCTIPO.Text     :=     NFFISCAL.DCTIPO;
   TXTICMS.Text     :=   FloatToStr(NFFISCAL.VL_ICMS ) ;
   TXTICMSBASE.Text      :=  FloatToStr(NFFISCAL.VL_BC_ICMS ) ;
   TXTBASEST.Text      :=  FloatToStr(NFFISCAL.VL_BC_ICMS_ST ) ;
   TXTST.Text      :=     FloatToStr(NFFISCAL.VL_ICMS_ST ) ;
   TXTPIS.Text      :=    FLOATToStr(NFFISCAL.VL_pis ) ;
   TXTCONFINS.Text      :=  FloatToStr(NFFISCAL.VL_COFINS ) ;
   TXTVLNOTA.Text     :=    FloatToStr(NFFISCAL.VL_DOC ) ;
   TXTVL_DESC.Text     :=   FloatToStr(NFFISCAL.VL_DESC ) ;
   TXTDTEMISSAO.Text     :=  DateToStr(NFFISCAL.DTEMISSAO);
   TXTDTENTRADA.Text    :=   DateToStr(NFFISCAL.DTENTRADA);
   TXTCFOP.Text    :=       NFFISCAL.CFOP;
   TXTIPI.Text    :=        FloatToStr(NFFISCAL.IPI ) ;
   TXTCDPEDIDO.Text    :=   IntToStr(NFFISCAL.CDPEDIDO  );
   TXTFRETE.Text     :=     NFFISCAL.IND_FRT;
   txtnatureza.Text    :=   IntToStr(NFFISCAL.NATUREZA  );
   ActResultEdit1.Text    := IntToStr(NFFISCAL.FORNECEDOR  );
   TXTCHACESSO.Text    :=   NFFISCAL.CHV_CTE ;
   TXTPROT.Text    :=       NFFISCAL.TP_CT ;
   TXTSTATUSNFE.Text     :=  NFFISCAL.COD_SIT;
   TXTNUMNF.Text         :=  IntToStr(NFFISCAL.CODNFE);
   IBQUERY5.Active := FALSE;
   IBQUERY5.SQL.Text := 'SELECT i.dcnumero,i.dcserie,i.dcordem,i.dctipo,i.num_item, i.cod_item,p.ref,p.descr_produto,p.cod_ncm,i.qtd,i.vl_item,i.vl_icms,p.vlaquis ,p.margempr ,p.precovenda  FROM nffiscalitens i ,prod_serv p where p.cod_produto = i.cod_item  and DCNUMERO = '+txtDCNUMERO.Text+' AND DCSERIE = '''+txtDCSERIE.Text+''' AND DCORDEM = '''+txtDCORDEM.Text+''' AND DCTIPO = '''+txtDCTIPO.Text+'''';
   IBQUERY5.Active := TRUE;
   IBQUERY6.Active := FALSE;
   IBQUERY6.SQL.Text := 'SELECT *FROM TITULOSPAGAR2 WHERE DCNUMERO = '+txtDCNUMERO.Text+' AND DCSERIE = '''+txtDCSERIE.Text+''' AND DCORDEM = '''+txtDCORDEM.Text+''' AND DCTIPO = '''+txtDCTIPO.Text+'''';
   IBQUERY6.Active := TRUE;
   IBQUERY7.Active := FALSE;
   IBQUERY7.SQL.Text := 'SELECT *FROM nffiscal WHERE DCNUMERO = '+txtDCNUMERO.Text+' AND DCSERIE = '''+txtDCSERIE.Text+''' AND DCORDEM = '''+txtDCORDEM.Text+''' AND DCTIPO = '''+txtDCTIPO.Text+'''';
   IBQUERY7.Active := TRUE;
   if(IBQUERY7.FieldByName('COD_SIT').AsString = 'I' )then
      Desabilita;
   IBQuery8.Active := False ;
   IBQuery8.SQL.Clear;
   IBQuery8.SQL.Add('SELECT MAX(CODNFE)+1 FROM NFFISCAL'  ) ;
   IBQuery8.Active := True;
     TXTNUMNF.Text := IntToStr(IBQuery8.fieldByName('F_1').AsInteger);
   ApuraValores();


end;


function TForm7.Atualizagrid(const dcnumero :Integer):Boolean;
begin
  IBQuery5.Active := False;
  IBQuery5.SQL.Clear;
  IBQuery5.SQL.Text := 'Select *from nfentradasItens where dcnumero ='+ IntToStr(dcnumero);
  SHOWMESSAGE(IBQuery5.SQL.Text);
  IBQuery5.Active := true;

end;

Function TForm7.GravarProdutos ():Boolean;
begin
   IBQuery12.SQL.Text := 'INSERT INTO PRODUTOS (CODPRO, PRODUTO, REF, UNIDADE, AQUIS, ICMS, FRETE, STATUS, ESTOQUE, APELIDO, CNB) VALUES ('+IntToStr(produtos.codpro)+', '''+produtos.PRODUTO+''', '''+produtos.REF+''', '''+produtos.UNIDADE+''','+ajustaVirgula(FloatToStr(produtos.AQUIS))+','+FloatToStr(produtos.ICMS)+','+FloatToStr(produtos.FRETE)+','''+produtos.STATUS+''','+FloattoStr(produtos.estoque)+','''+produtos.PRODUTO+''', '+intToStr(produtos.CNB)+')';
  // ShowMessage(IBQuery12.SQL.Text);
   IBQuery12.ExecSQL;
   IBQuery12.Transaction.Commit;
   IBQuery5.Active := False;
   IBQuery5.Active := True;
   end;
{
function TForm7.auterarItens(quantidade,ipi,st,icms,vlunitario,vldesconto:Double; cdfornecedor,DCNUMERO,CDPRODUTO,CODPRO:Integer;DCSERIE,DCORDEM,DCTIPO:String):Boolean;
begin
try
 IBQuery11.SQL.Clear;
 IBQuery11.SQL.Add('UPDATE NFENTRADASITENS SET');
 IBQuery11.SQL.Add('   QUANTIDADE = '+ajustaVirgula(FloatToStr(quantidade))+' , ');
 IBQuery11.SQL.Add('   IPI = '+ajustaVirgula(FloatToStr(IPI))+' , ');
 IBQuery11.SQL.Add('   ST = '+ajustaVirgula(FloatToStr(ST))+' , ');
 IBQuery11.SQL.Add('   ICMS = '+ajustaVirgula(FloatToStr(ICMS))+',');
 IBQuery11.SQL.Add('   VLUNITARIO = '+ajustaVirgula(FloatToStr(vlunitario))+' , ');
 IBQuery11.SQL.Add('   VLDESCONTO = '+ajustaVirgula(FloatToStr(vldesconto))+' , ');
 IBQuery11.SQL.Add('   CDFORNECEDOR = '+intToStr(cdfornecedor)+' ');
 IBQuery11.SQL.Add('WHERE (DCNUMERO = '+intToStr(dcnumero)+') AND ');
 IBQuery11.SQL.Add('     (DCSERIE = '''+dcserie+''') AND ');
 IBQuery11.SQL.Add('     (DCORDEM = '''+dcordem+''') AND ');
 IBQuery11.SQL.Add('     (DCTIPO = '''+dctipo+''') AND ');
 IBQuery11.SQL.Add('     (CODPRO = '+intToStr(codpro)+')');
 IBQuery11.ExecSQL;
 IBQuery11.Transaction.Commit;
 result := True;
 Except
   result := False;
 end;
    IBQUERY5.Active := FALSE;
   IBQUERY5.SQL.Text := 'SELECT i.dcnumero,i.dcserie,i.dcordem,i.dctipo, i.codpro,p.ref,p.produto,p.cnb,i.quantidade,i.vlunitario,i.icmsbsi,i.icms,p.valor as PORC,p.precovenda  FROM nfentradasitens i ,produtos p where p.codpro = i.codpro  and DCNUMERO = '+IntToStr(NFENTRADAS.DCNUMERO)+' AND DCSERIE = '''+NFENTRADAS.DCSERIE+''' AND DCORDEM = '''+NFENTRADAS.DCORDEM+''' AND DCTIPO = '''+NFENTRADAS.DCTIPO+'''';
   IBQUERY5.Active := TRUE;
Gravar := True;


end;      }

Function TForm7.ValidaFornecedor (const cd : Integer ):Boolean;
begin

end;


Function TForm7.ValidaProdutos (const cdpro : Integer;NCM :String ):Boolean;
begin
  IBQuery9.Active := False;
  IBQuery9.SQL.Text := 'select *from prod_serv where cod_produto = '+IntToStr(cdpro);
  IBQuery9.Active := True;
  if (not IBQuery9.Eof ) then
  begin
    NFISCALitens.COD_ITEM := IBQuery9.FieldByNAme('cod_produto').AsInteger;
    Result := True;
  end
  else
   begin
      IBQuery9.Active := False;
      IBQuery9.SQL.Text := 'select *from prod_serv where REF like '''+IntToStr(cdpro)+'''';
      IBQuery9.Active := True;
        if (not IBQuery9.Eof ) then
         begin
           NFISCALitens.COD_ITEM := IBQuery9.FieldByNAme('cod_produto').AsInteger;
           Result := True;
         end
         else
         begin
            IBQuery9.Active := False;
            IBQuery9.SQL.Text := 'select *from prod_serv where cod_ncm like '''+IntToStr(cdpro)+'''';
            IBQuery9.Active := True;
            if (not IBQuery9.Eof ) then
             begin
               NFISCALitens.COD_ITEM := IBQuery9.FieldByNAme('cod_produto').AsInteger;
                Result := True;
             end
            else
             begin
               IBQuery9.Active := False;
               IBQuery9.SQL.Text := 'select *from prod_serv where cod_ncm like '''+NCM+'''';
               IBQuery9.Active := True;
               if (not IBQuery9.Eof ) then
                  begin
                   NFISCALitens.COD_ITEM := IBQuery9.FieldByNAme('cod_produto').AsInteger;
                   Result := True;
                  end
               else
                Result := False;
             end;

          end;


end;
 end;
function TForm7.ajustaData(const frase : String): String;
  var x: integer;
     teste : string;
  begin
    x:= 0;
    teste := frase;
    while (x < 11  )do
    begin
      if(teste[x] = '/') then
        teste[x] := '.';
        x := x + 1;
    end;
    Result := teste;

  end;
function TForm7.ajustaSpaco(const frase : String): String;
  var x,y: integer;
     teste : string;
  begin
    x:= 0;
    y:= 0;
   //tringReplace(frase, ' ', '', [rfReplaceAll]);
   //este := frase;
    while (x < 20  )do
    begin
     if ((frase[x] = ' ')or (frase[x] = '0')  or (frase[x] = '1') or (frase[x] = '2') or (frase[x] = '3') or (frase[x] = '4') or (frase[x] = '5') or (frase[x] = '6') or (frase[x] = '7')or (frase[x] = '8') or (frase[x] = '9')) then
     begin
      if(frase[x] = ' ') then
        x := x + 1
      else
      begin
         teste := teste + frase[x];
         x := x + 1;
         y := y + 1;
      end;
    end
    else
      x := x + 1;
   end;
    Result := teste;

  end;

 function TForm7.ajustaPONTO(const frase : String): String;
var
  valor,valornovo:String;
begin
  valornovo := StringReplace(frase,'.','',[rfReplaceAll]);
  valornovo := StringReplace(valornovo,'\','',[rfReplaceAll]);
  result := valornovo;
end;








procedure TForm7.GravarConfiguracao;
Var IniFile : String ;
    Ini     : TIniFile ;
    StreamMemo : TMemoryStream;
begin
  IniFile := ChangeFileExt( Application.ExeName, '.ini') ;

  Ini := TIniFile.Create( IniFile );
  try

  finally
     Ini.Free ;
  end;

end;

procedure TForm7.LerConfiguracao;
Var IniFile  : String ;
    Ini     : TIniFile ;
    Ok : Boolean;
    StreamMemo : TMemoryStream;
begin
  IniFile := ChangeFileExt( Application.ExeName, '.ini') ;

  Ini := TIniFile.Create( IniFile );
  try

  finally
     Ini.Free ;
  end;

end;

procedure TForm7.LoadXML(MyMemo: TMemo; MyWebBrowser: TWebBrowser);
begin
  MyMemo.Lines.SaveToFile(ExtractFileDir(application.ExeName)+'\temp.xml');
  MyWebBrowser.Navigate(ExtractFileDir(application.ExeName)+'\temp.xml');
end;




Function TForm7.VerificaProduto(cdpro :integer):Boolean;
begin
  IBQuery9.Active := False;
  IBQuery9.SQL.Text := 'select *from Prod_serv where CoD_PRODUTO = '+IntToStr(cdpro);
  IBQuery9.Active := True;
  if (not IBQuery9.Eof ) then
  begin
      if(IBQuery9.FieldByName('STATUS').AsString = 'A'  )then
         Result := true
  end



end;
function TForm7.proximo():boolean;
begin
   IBQuery4.Next;
    populaTXT();
   POPULARNFFFISCAL ;

end;
function TForm7.anterior():boolean;
begin
   IBQuery4.Prior;
    populaTXT();
   POPULARNFFFISCAL ;

end;
function TForm7.primeiro():boolean;
begin
  IBQuery4.First;
    populaTXT();
   POPULARNFFFISCAL ;

end;
function TForm7.ultimo():boolean;
begin
 IBQuery4.Last;
    populaTXT();
   POPULARNFFFISCAL ;

end;
function TForm7.selectprinc():boolean;
begin
  IBQuery4.Active := False;
  IBQuery4.SQL.Text := 'select *from nffiscal';
  IBQuery4.Active := True;
end;
Function TForm7.LimpaCampos():Boolean;
begin

  TXTDCNUMERO.Text := '';
  TXTDCSERIE.Text := '';
  TXTDCORDEM.Text := '';
  TXTDCTIPO.Text := '';
  txtnatureza.Text:= '';
  TXTVLNOTA.Text:= '';
  TXTICMS.Text:= '';
  TXTIPI.Text:= '';
  TXTFRETE.Text:= '';
  TXTCFOP.Text:= '';
  TXTDTENTRADA.Text := DateTimeToStr(Now);
  TXTDTEMISSAO.Text := '';
  ActResultEdit1.Text:= '';
  TXTCDPEDIDO.Text:= '';
  TXTST.Text:= '';
  TXTDCNUMERO.Text:= '';
  ActResultEdit1.Text:= '';
  IBQUERY5.Active := FALSE;
  IBQUERY6.Active := FALSE;

end;


function TForm7.auteranf():boolean;
begin

end;

function TForm7.apagatitulospagar(const dcnumero,noparcela:Integer;dcserie,dcordem,dctipo:String):boolean;
begin
  try
    IBQuery8.Active := False;
    IBQuery8.Sql.Text := 'delete from titulospagar2 where dcnumero = '+IntToStr(dcnumero)+' and dcserie = '''+dcserie+''' and dcordem = '''+dcordem+''' and dctipo =  '''+dctipo+''' and parcela = '+intToStr(noparcela)+' ';
    IBQuery8.ExecSQL;
    IBQuery8.Transaction.Commit;
    ApuraValores();
    result := True;
  Except
    Result := False;

end;
end;

function TForm7.apagaitens(const dcnumero,codpro,QUANTIDADE:Integer;dcserie,dcordem,dctipo:String):boolean;
begin
     try
    IBQuery8.Active := False;
    IBQuery8.Sql.Text := 'delete from nffiscalitens where dcnumero = '+IntToStr(dcnumero)+' and dcserie = '''+dcserie+''' and dcordem = '''+dcordem+''' and dctipo =  '''+dctipo+''' and codpro = '+IntToStr(codpro)+' ';
    IBQuery8.ExecSQL;
    IBQuery8.Transaction.Commit;
    DiminuiSaldoEstoque(codpro,QUANTIDADE);
    ApuraValores();
    result := True;
    Except
    Result := False;
   end;
end;
function TForm7.apaganf(const dcnumero:Integer; dcserie,dcordem,dctipo:String ):boolean;
begin
       try
    IBQuery8.Active := False;
    IBQuery8.Sql.Text := 'delete from nffiscal where dcnumero = '+IntToStr(dcnumero)+' and dcserie = '''+dcserie+''' and dcordem = '''+dcordem+''' and dctipo =  '''+dctipo+'''  ';
    IBQuery8.ExecSQL;
    IBQuery8.Transaction.Commit;
    result := True;
    Except
    Result := False;
   end;
end;


  function  TForm7.ajustaVirgula(const frase : String): String;
  var x: integer;
     teste : string;

        const
   cP='.';
   cVa='';
   cVi=',';

   begin
   result:=Frase;
   result:=StringReplace(StringReplace(result,cP,cVa,[rfReplaceAll]),
   cVi,cP,[rfReplaceAll]);

  end;

function TForm7.AumentaSaldoEstoque(const cdproduto : integer;
                                           quantidade: real):boolean;
var
  estqatual,estoque : double;
 begin

Try
    qryprodutos1.Active := false;
    qryMovEstoque.SQL.Text := 'select *from produtos where codpro = '+IntToStr(cdproduto);
    qryMovEstoque.Active := true;
    estqatual := qryMovEstoque.FieldbyName('estoque').AsInteger;
    estoque := estqatual + quantidade;
    qryMovEstoque.Active := false;
    qryMovEstoque.SQL.Text := 'update produtos set estoque = '+FloatToStr(estoque)+',estoqueant = '+FloatToStr(estqatual)+', where codpro = '+IntToStr(cdproduto) ;
    qryMovEstoque.ExecSQL;
    //DataModule2.qryMovEstoque.Active := True;
    Except
   result := False;
 end;
end;
function TForm7.DiminuiSaldoEstoque(const cdproduto : integer;
                                  quantidade: real):boolean;
 var
  estqatual,estoque : double;
 begin
 try
    qryprodutos1.Active := false;
    qryMovEstoque.SQL.Text := 'select *from produtos where codpro = '+IntToStr(cdproduto);
    qryMovEstoque.Active := true;
    estqatual := qryMovEstoque.FieldbyName('estoque').AsInteger;
    estoque := estqatual - quantidade;
    qryMovEstoque.Active := false;
    qryMovEstoque.SQL.Text := 'update produtos set estoque = '+FloatToStr(estoque)+',estoqueant = '+FloatToStr(estqatual)+',  where codpro = '+IntToStr(cdproduto) ;
    qryMovEstoque.ExecSQL;
   // DataModule2.qryMovEstoque.Active := false;
   // DataModule2.qryMovEstoque.SQL.Text := 'update produtos set estoque = '+FloatToStr(estoque)+',estoqueant = '+FloatToStr(estqatual)+', custo = '+custo+' where codpro = '+IntToStr(cdproduto) ;
   // DataModule2.qryMovEstoque.ExecSQL;
 Except
   result := False;
 end;

end;


function TForm7.ApuraValores():boolean;

  begin
   ibquery7.Active := False;
   ibquery7.sql.text := 'SELECT Sum(qtd*vl_item)  FROM nffiscalitens WHERE DCNUMERO = '+txtDCNUMERO.Text+' and dcserie = '''+txtDCSERIE.Text+''' and dcordem = '''+txtDCORDEM.Text+''' and dctipo = '''+txtDCTIPO.Text+'''';
  // ShowMEssage(ibquery7.sql.text);
   ibquery7.Active := True;
   somaItens := ibquery7.FieldByName('Sum').AsFloat;
   ibquery7.Active := False;
   ibquery7.sql.text := 'SELECT Sum(vlparcela)  FROM titulospagar2 WHERE DCNUMERO = '+txtDCNUMERO.Text+' and dcserie = '''+txtDCSERIE.Text+''' and dcordem = '''+txtDCORDEM.Text+''' and dctipo =  '''+txtdctipo.text+'''';
   ibquery7.Active := True;
   somaTitulos := ibquery7.FieldByName('Sum').AsFloat;
   ibquery7.Active := False;
   ibquery7.sql.text := 'SELECT Sum(vl_doc - vl_desc)  FROM nffiscal WHERE DCNUMERO = '+txtdcnumero.text+' and dcserie = '''+txtDCSERIE.Text+''' and dcordem = '''+txtDCORDEM.text+''' and dctipo =  '''+txtDCTIPO.Text+'''';
   ibquery7.Active := True;
   somaNF := ibquery7.FieldByName('Sum').AsFloat;



    if ((somaNF = SomaTitulos) and (SomaNf = SomaItens) )then
       Result := True
    Else
       Result := False;
     // somaItens,SomaTitulos,SomaNF :Double;
   Label35.Caption := FloatToStr(SomaTitulos);
   Label36.Caption := FloatToStr(somaItens);
   Label37.Caption := FloatToStr(SomaNF);
  end;


function TForm7.POPULANFENTRADASITENS():Boolean;
 BEGIN
              while(TXTCODPRO.Text = '')do
              begin
                if(TXTCODPRO.Text = '') then
                ShowMessage('CODIGO PRODUTO EM BRANCO OU INVALIDO')
                else
                 NFISCALitens.COD_ITEM := StrToInt(TXTCODPRO.Text);
              end;
              NFISCALitens.COD_ITEM := StrToInt(TXTCODPRO.Text);
              if(TXTEMPRESA.text = '')then
                NFISCALitens.COD_EMPRESA := 1
              else
              NFISCALitens.COD_EMPRESA      := StrToInt(TXTEMPRESA.text);
              if(TXTFILIAL.Text = '')then
                NFISCALitens.COD_FILIAL := 1
              else
              NFISCALitens.COD_FILIAL       := StrToInt(TXTFILIAL.Text);
              if(TXTDCNUMERO.Text = '')then
                NFISCALitens.DCNUMERO := 1
              else
              NFISCALitens.DCNUMERO         := StrToInt(TXTDCNUMERO.Text);
              if(TXTDCSERIE.Text = '')then
                NFISCALitens.DCSERIE := ' '
              else
              NFISCALitens.DCSERIE          := TXTDCSERIE.Text;
              if(TXTDCORDEM.Text = '')then
                NFISCALitens.DCORDEM := ' '
              else
              NFISCALitens.DCORDEM          := TXTDCORDEM.Text;
              if(TXTDCTIPO.Text = '')then
                NFISCALitens.DCTIPO := ' '
              else
              NFISCALitens.dctipo           := TXTDCTIPO.Text;
              if(ActResultEdit1.text = '')then
                NFISCALitens.CDFORNECEDOR := 0
              else
              NFISCALitens.CDFORNECEDOR     := StrToInt(ActResultEdit1.text);
              if(txtquantidade.Text = '')then
                NFISCALitens.QTD := 0
              else
              NFISCALitens.QTD              := StrToFloat(txtquantidade.Text);
              if(txtDesc_Itens.Text = '')then
                NFISCALitens.VL_DESC := 0
              else
              NFISCALitens.VL_DESC          := StrToFloat(txtDesc_Itens.Text);
              if(txtvlunitario.Text = '')then
                NFISCALitens.VL_ITEM := 0
              else
              NFISCALitens.VL_ITEM          := StrToFloat(txtvlunitario.Text);

              NFISCALitens.IND_MOV          :=  1.00 ;
              NFISCALitens.CST_ICMS         :=  0;

              if(TXTCFOP_I.Text = '')then
                NFISCALitens.CFOP := ' '
              else
              NFISCALitens.CFOP             :=  TXTCFOP_I.Text;




                NFISCALitens.ALIQ_ICMS := 0 ;
                IF (TXTICMSBASEI.Text = '' )THEN
                 NFISCALitens.VL_BC_ICMS := 0
                else
                 NFISCALitens.VL_BC_ICMS :=    StrToFloat(TXTICMSBASEI.Text);
                if (TXTICMSI.Text = '')then
                NFISCALitens.VL_ICMS          :=  0
                else
                NFISCALitens.VL_ICMS          :=  StrToFloat(TXTICMSI.Text) ;

                if (TXTBASE_ST.Text = '')then
                NFISCALitens.VL_BC_ICMS_ST    :=  0
                else
                NFISCALitens.VL_BC_ICMS_ST    := StrToFloat(TXTBASE_ST.Text);
                NFISCALitens.ALIQ_ST          :=  0;
                if(TXTVL_ST.Text = '' )THEN
                 NFISCALitens.VL_ICMS_ST         :=  0
                ELSE
                 NFISCALitens.VL_ICMS_ST            := StrToFloat(TXTVL_ST.Text) ;
                NFISCALitens.IND_APUR         :=  1;
                NFISCALitens.CST_IPI          :=  0;
                NFISCALitens.COD_ENQ          :=  0;
                NFISCALitens.VL_BC_IPI        :=  0;
                NFISCALitens.ALIQ_IPI         :=  0;
                NFISCALitens.VL_IPI           :=  0;
                NFISCALitens.CST_PIS          :=  0;
                NFISCALitens.VL_BC_PIS        :=  0;
                NFISCALitens.ALIQ_PIS         :=  0;
                NFISCALitens.QUANT_BC_PIS     :=  0;
                NFISCALitens.VL_PI            :=  0;
                NFISCALitens.CST_COFINS       :=  0;
                NFISCALitens.VL_BC_COFINS     :=  0;
                NFISCALitens.ALIQ_COFINS      :=  0;
                NFISCALitens.QUANT_BC_COFINS  :=  0;
                NFISCALitens.VL_COFINS        :=  0;
                NFISCALitens.COD_CTA          :=  0;
                NFISCALitens.CODNFE           := StrToInt(TXTNUMNF.Text);
 end;
function TForm7.POPULANFENTRADAS():Boolean;
 BEGIN

         NFFISCAL.IND_OPER := 01;
         NFFISCAL.IND_EMIT := 01;
         NFFISCAL.COD_PART := StrToInt(ActResultEdit1.text);
         NFFISCAL.COD_SIT   :=  'D';
         NFFISCAL.DTEMISSAO    := StrToDate(TXTDTEMISSAO.Text);
         NFFISCAL.DTENTRADA    := StrToDate(TXTDTENTRADA.Text);
         if (TXTVLNOTA.Text = '')then
         begin
           NFFISCAL.VL_DOC    := 0;
           NFFISCAL.VL_FORN   := 0;
         end
         else
         begin
           NFFISCAL.VL_DOC    := StrToFloat(TXTVLNOTA.Text);
           NFFISCAL.VL_FORN   := StrToFloat(TXTVLNOTA.Text);
         end;
           NFFISCAL.VL_SERV_NT  :=  0;
           NFFISCAL.VL_TERC     :=  0;
           NFFISCAL.VL_DA       :=  0;
         if (TXTVL_DESC.Text = '')then
          NFFISCAL.VL_DESC   := 0
         else
          NFFISCAL.VL_DESC   := StrToFloat(TXTVL_DESC.Text);
         NFFISCAL.VL_FORN   := 0; //(Valor total fornecido/consumido)
         NFFISCAL.VL_SERV_NT := 0; //(Valor total dos servi�os n�o-tributados pelo ICMS)
         NFFISCAL.VL_TERC    := 0; // (Valor total cobrado em nome de terceiros)
         NFFISCAL.VL_DA      := 0; //(Valor total de despesas acess�rias indicadas no documento fiscal)
         if (TXTICMSBASE.Text = '')then
           NFFISCAL.VL_BC_ICMS := 0
         else
           NFFISCAL.VL_BC_ICMS := StrToFloat(TXTICMSBASE.Text);
         if (TXTICMS.Text = '')then
           NFFISCAL.VL_ICMS    := 0
         else
           NFFISCAL.VL_ICMS    := StrToFloat(TXTICMS.Text);
         if (TXTBASEST.Text = '')then
           NFFISCAL.VL_BC_ICMS_ST  := 0
         else
           NFFISCAL.VL_BC_ICMS_ST  :=  StrToFloat(TXTBASEST.Text);
         if (TXTST.Text = '')then
           NFFISCAL.VL_ICMS_ST     := 0
         else
           NFFISCAL.VL_ICMS_ST     :=  StrToFloat(TXTST.Text);
         NFFISCAL.COD_INF        := 0;
         if (TXTCONFINS.Text = '')then
           NFFISCAL.VL_COFINS      := 0
         else
           NFFISCAL.VL_COFINS      := StrToFloat(TXTCONFINS.Text);
         NFFISCAL.TP_LIGACAO     := 2 ; //(C�digo da informa��o complementar do documento fiscal (campo 02 do Registro 0450))
         NFFISCAL.COD_GRUPO_TENSAO := '1';
         NFFISCAL.TP_CT    :=   '0';
         NFFISCAL.CHV_CTE_REF := TXTCHACESSO.Text;
         NFFISCAL.IND_FRT     := TXTFRETE.Text;   // '2'; // (Indicador do tipo do frete:){0- Por conta de terceiros;1- Por conta do emitente;2- Por conta do destinat�rio;9- Sem cobran�a de frete.}
         NFFISCAL.CODNFE      := 0;
         if (TXTEMPRESA.Text = '')then
           NFFISCAL.COD_EMPRESA  := 1
         else
           NFFISCAL.COD_EMPRESA  := StrToInt(TXTEMPRESA.Text);
         if (TXTFILIAL.Text = '')then
          NFFISCAL.COD_FILIAL   := 1
         else
          NFFISCAL.COD_FILIAL   := StrToInt(TXTFILIAL.Text);
         if (TXTDCNUMERO.Text = '')then
         NFFISCAL.DCNUMERO   := 0
         else
         NFFISCAL.DCNUMERO   :=  StrToInt(TXTDCNUMERO.Text);
         NFFISCAL.DCSERIE    := TXTDCSERIE.Text;
         NFFISCAL.DCORDEM    := TXTDCORDEM.Text ;
         NFFISCAL.DCTIPO     := TXTDCTIPO.Text;
         if (TXTCDPEDIDO.text = '')then
         TXTCDPEDIDO.text := '0'
         else
         NFFISCAL.CDPEDIDO   := StrToInt(TXTCDPEDIDO.text);
         if(txtnatureza.text = '')then
         txtnatureza.text := '0'
         else
         NFFISCAL.NATUREZA   := StrToInt(txtnatureza.text);
         NFFISCAL.CFOP       := TXTCFOP.text;
         if(TXTCHACESSO.Text = '')then
          NFFISCAL.CHV_CTE := '0'
         else
         NFFISCAL.CHV_CTE    := TXTCHACESSO.Text;
         NFFISCAL.TP_CT      := '0';
         NFFISCAL.CHV_CTE_REF:= '0';

         IF(ActRadioButton1.Checked = True )THEN
             NFFISCAL.COD_SIT := 'I';
         IF(ActRadioButton1.Checked = True )THEN
             NFFISCAL.COD_SIT := 'P';
         IF(ActRadioButton1.Checked = True )THEN
             NFFISCAL.COD_SIT := 'T';
         IF(ActRadioButton1.Checked = True )THEN
             NFFISCAL.COD_SIT := 'C';


 end;



function TForm7.GRAVARTITULO():Boolean;
begin
     try
      IBQgravartitulospagar.Active := False;
      IBQgravartitulospagar.SQL.Clear;
      IBQgravartitulospagar.SQL.Add('      insert into titulospagar2 ');
      IBQgravartitulospagar.SQL.Add('  (DCNUMERO, DCSERIE, DCORDEM, DCTIPO, PARCELA, DTVENCIMENTO, DTLANCAMENTO, ');
      IBQgravartitulospagar.SQL.Add('   STATUS, TPSITUACAO, VLPARCELA, FORNECEDOR,TIPO_TITULO, ');
      IBQgravartitulospagar.SQL.Add('   COD_EMPRESA, COD_FILIAL, COD_TITULO, OPER_TITULO,DESCONTO)');
      IBQgravartitulospagar.SQL.Add('values');
      IBQgravartitulospagar.SQL.Add('  (:DCNUMERO, :DCSERIE, :DCORDEM, :DCTIPO, :PARCELA, :DTVENCIMENTO, :DTLANCAMENTO, ');
      IBQgravartitulospagar.SQL.Add('   :STATUS, :TPSITUACAO, :VLPARCELA, :FORNECEDOR, ');
      IBQgravartitulospagar.SQL.Add('   :TIPO_TITULO, :COD_EMPRESA, :COD_FILIAL, :COD_TITULO,');
      IBQgravartitulospagar.SQL.Add('   :OPER_TITULO,:DESCONTO)');
      IBQgravartitulospagar.ParamByName('STATUS').AsString       := 'D';
      IBQgravartitulospagar.ParamByName('TPSITUACAO').AsString   := 'F';
      IBQgravartitulospagar.ParamByName('Cod_empresa').asInteger :=  StrToInt(TXTEMPRESA.Text);
      IBQgravartitulospagar.ParamByName('Cod_filial').asInteger  :=  StrToInt(TXTFILIAL.Text);
      IBQgravartitulospagar.ParamByName('dcnumero').asInteger    :=  StrToInt(TXTDCNUMERO.Text);
      IBQgravartitulospagar.ParamByName('dcserie').AsString      :=   TXTDCSERIE.Text;
      IBQgravartitulospagar.ParamByName('dctipo').AsString       :=   TXTDCTIPO.Text;
      IBQgravartitulospagar.ParamByName('dcordem').AsString      :=   TXTDCORDEM.Text;
      IBQgravartitulospagar.ParamByName('parcela').asInteger     :=  StrToInt(txtnoparcela.Text);
      IBQgravartitulospagar.ParamByName('fornecedor').asInteger  :=  StrToInt(ActResultEdit1.Text);
      IBQgravartitulospagar.ParamByName('dtlancamento').asdATETIME := Now;
      IBQgravartitulospagar.ParamByName('vlparcela').asfLOAT       := StrToFloat(TXTVALOR.Text);
      IBQgravartitulospagar.ParamByName('dtvencimento').asdATETIME :=   StrToDate(txtdtvencimento.Text);
      IBQgravartitulospagar.ParamByName('tipo_titulo').asInteger   :=   StrToInt(TXTTIPO_TIT.Text);
      IBQgravartitulospagar.ParamByName('COD_TITULO').asInteger    :=   StrToInt(TXTDCNUMERO.TEXT);
      if(TXTDESCONTO.TEXT = '')then
         TXTDESCONTO.TEXT := '0';
      IBQgravartitulospagar.ParamByName('DESCONTO').AsFloat    :=   StrToFloat(TXTDESCONTO.TEXT);
      IBQgravartitulospagar.ParamByName('OPER_TITULO').AsString := 'P';

      IBQgravartitulospagar.ExecSQL;
      IBQgravartitulospagar.Transaction.Commit;
     Result := True;
     except
       Result := False;
    END;




end;
function TForm7.GRAVARNFENTRADASITENS():Boolean;
begin
 try
 IBQgravarnfentitens.SQL.Clear;
 IBQgravarnfentitens.SQL.Add(' insert into NFFISCALITENS ');
 IBQgravarnfentitens.SQL.Add(' (CODNFE, COD_EMPRESA, NUM_ITEM, COD_ITEM, DESCR_COMPL, QTD, UNID, VL_ITEM, ');
 IBQgravarnfentitens.SQL.Add('  VL_DESC, IND_MOV, CST_ICMS, CFOP, ALIQ_ICMS, VL_ICMS, VL_BC_ICMS_ST,   ');
 IBQgravarnfentitens.SQL.Add('  ALIQ_ST, VL_ICMS_ST, IND_APUR, CST_IPI, COD_ENQ, VL_BC_IPI, ALIQ_IPI, ');
 IBQgravarnfentitens.SQL.Add('  VL_IPI, CST_PIS, VL_BC_PIS, ALIQ_PIS, QUANT_BC_PIS, VL_PIS, CST_COFINS, ');
 IBQgravarnfentitens.SQL.Add('  VL_BC_COFINS, ALIQ_COFINS, QUANT_BC_COFINS, VL_COFINS, COD_CTA, DCNUMERO, ');
 IBQgravarnfentitens.SQL.Add('  DCSERIE, DCORDEM, DCTIPO,COD_FILIAL,rateio) ');
 IBQgravarnfentitens.SQL.Add(' values  ');
 IBQgravarnfentitens.SQL.Add(' (:CODNFE, :COD_EMPRESA, :NUM_ITEM, :COD_ITEM, :DESCR_COMPL, :QTD, :UNID,');
 IBQgravarnfentitens.SQL.Add('  :VL_ITEM, :VL_DESC, :IND_MOV, :CST_ICMS, :CFOP, :ALIQ_ICMS, :VL_ICMS, ');
 IBQgravarnfentitens.SQL.Add('  :VL_BC_ICMS_ST, :ALIQ_ST, :VL_ICMS_ST, :IND_APUR, :CST_IPI, :COD_ENQ,');
 IBQgravarnfentitens.SQL.Add('  :VL_BC_IPI, :ALIQ_IPI, :VL_IPI, :CST_PIS, :VL_BC_PIS, :ALIQ_PIS, :QUANT_BC_PIS, ');
 IBQgravarnfentitens.SQL.Add('  :VL_PIS, :CST_COFINS, :VL_BC_COFINS, :ALIQ_COFINS, :QUANT_BC_COFINS,');
 IBQgravarnfentitens.SQL.Add('  :VL_COFINS, :COD_CTA, :DCNUMERO, :DCSERIE, :DCORDEM, :DCTIPO,:COD_FILIAL,:rateio) ');
  IBQgravarnfentitens.ParamByName('CODNFE').AsInteger :=         NFISCALitens.CODNFe;
  IBQgravarnfentitens.ParamByName('COD_EMPRESA').AsInteger :=    NFISCALitens.COD_EMPRESA;
  IBQgravarnfentitens.ParamByName('NUM_ITEM').AsInteger :=       1;
  IBQgravarnfentitens.ParamByName('COD_ITEM').AsInteger :=       NFISCALitens.COD_ITEM;
  IBQgravarnfentitens.ParamByName('DESCR_COMPL').AsString :=     NFISCALitens.CDESCR_COMPL ;
  IBQgravarnfentitens.ParamByName('QTD').AsFloat :=              NFISCALitens.QTD ;
  IBQgravarnfentitens.ParamByName('UNID').AsFloat :=             NFISCALitens.UNID;
  IBQgravarnfentitens.ParamByName('VL_ITEM').AsFloat :=          NFISCALitens.VL_ITEM;
  IBQgravarnfentitens.ParamByName('VL_DESC').AsFloat :=          NFISCALitens.VL_DESC;
  IBQgravarnfentitens.ParamByName('IND_MOV').AsFloat :=          NFISCALitens.IND_MOV;
  IBQgravarnfentitens.ParamByName('CST_ICMS').AsFloat :=         NFISCALitens.CST_ICMS;
  IBQgravarnfentitens.ParamByName('CFOP').AsString :=            NFISCALitens.CFOP;
  IBQgravarnfentitens.ParamByName('ALIQ_ICMS').AsFloat :=        NFISCALitens.ALIQ_ICMS;
  IBQgravarnfentitens.ParamByName('VL_ICMS').AsFloat :=          NFISCALitens.VL_ICMS;
  IBQgravarnfentitens.ParamByName('VL_BC_ICMS_ST').AsFloat :=    NFISCALitens.VL_BC_ICMS_ST;
  IBQgravarnfentitens.ParamByName('ALIQ_ST').AsFloat :=          NFISCALitens.ALIQ_ST;
  IBQgravarnfentitens.ParamByName('VL_ICMS_ST').AsFloat :=       NFISCALitens.VL_ICMS_ST;
  IBQgravarnfentitens.ParamByName('IND_APUR').AsFloat  :=        NFISCALitens.IND_APUR;
  IBQgravarnfentitens.ParamByName('CST_IPI').AsFloat :=          NFISCALitens.CST_IPI;
  IBQgravarnfentitens.ParamByName('COD_ENQ').AsFloat :=          NFISCALitens.COD_ENQ;
  IBQgravarnfentitens.ParamByName('VL_BC_IPI').AsFloat :=        NFISCALitens.VL_BC_IPI;
  IBQgravarnfentitens.ParamByName('ALIQ_IPI').AsFloat :=         NFISCALitens.ALIQ_IPI;
  IBQgravarnfentitens.ParamByName('VL_IPI').AsFloat :=           NFISCALitens.VL_IPI;
  IBQgravarnfentitens.ParamByName('VL_BC_PIS').AsFloat :=        NFISCALitens.VL_BC_PIS;
  IBQgravarnfentitens.ParamByName('ALIQ_PIS').AsFloat :=         NFISCALitens.ALIQ_PIS;
  IBQgravarnfentitens.ParamByName('QUANT_BC_PIS').AsFloat:=      NFISCALitens.QUANT_BC_PIS;
  IBQgravarnfentitens.ParamByName('VL_PIS').AsFloat :=           NFISCALitens.VL_PI ;
  IBQgravarnfentitens.ParamByName('CST_COFINS').AsFloat :=       NFISCALitens.CST_COFINS;
  IBQgravarnfentitens.ParamByName('VL_BC_COFINS').AsFloat :=     NFISCALitens.VL_BC_COFINS;
  IBQgravarnfentitens.ParamByName('ALIQ_COFINS').AsFloat :=      NFISCALitens.ALIQ_COFINS;
  IBQgravarnfentitens.ParamByName('QUANT_BC_COFINS').AsFloat :=  NFISCALitens.QUANT_BC_COFINS;
  IBQgravarnfentitens.ParamByName('VL_COFINS').AsFloat :=        NFISCALitens.VL_COFINS;
  IBQgravarnfentitens.ParamByName('COD_CTA').AsFloat :=          NFISCALitens.COD_CTA;
  IBQgravarnfentitens.ParamByName('DCNUMERO').AsInteger :=       NFISCALitens.DCNUMERO ;
  IBQgravarnfentitens.ParamByName('DCSERIE').AsString :=         NFISCALitens.DCSERIE;
  IBQgravarnfentitens.ParamByName('DCORDEM').AsString :=         NFISCALitens.DCORDEM;
  IBQgravarnfentitens.ParamByName('DCTIPO').AsString :=          NFISCALitens.DCTIPO;
  IBQgravarnfentitens.ParamByName('COD_filial').AsiNTEGER :=     NFISCALitens.COD_FILIAL;
  IBQgravarnfentitens.ParamByName('RATEIO').AsString      :=     NFISCALitens.rateio;
  IBQgravarnfentitens.ExecSQL;
  IBQgravarnfentitens.Transaction.Commit;
 //AumentaSaldoEstoque(NFENTRADAS.CODPRO,NFENTRADAS.QUANTIDADE);
  result := True;
 Except
   result := False;
 end;
   IBQUERY5.Active := FALSE;
   IBQUERY5.SQL.Text := 'SELECT i.dcnumero,i.dcserie,i.dcordem,i.dctipo,i.num_item, i.cod_item,p.ref,p.descr_produto,p.cod_ncm,i.qtd,i.vl_item,i.vl_icms,p.vlaquis ,p.margempr ,p.precovenda  FROM nffiscalitens i ,prod_serv p where p.cod_produto = i.cod_item  and DCNUMERO = '+txtDCNUMERO.Text+' AND DCSERIE = '''+txtDCSERIE.Text+''' AND DCORDEM = '''+txtDCORDEM.Text+''' AND DCTIPO = '''+txtDCTIPO.Text+'''';
   IBQUERY5.Active := TRUE;
end;
function TForm7.GRAVARNFENTRADAS():Boolean;
begin

 try
  IBQgravnfentrad.Active := False;
  IBQgravnfentrad.SQL.Clear;
  IBQgravnfentrad.SQL.Add('insert into nffiscal');
  IBQgravnfentrad.SQL.Add('(IND_OPER, IND_EMIT, COD_PART, COD_SIT, DT_DOC, DT_E_S, VL_DOC, VL_DESC,');
  IBQgravnfentrad.SQL.Add(' VL_FORN, VL_SERV_NT, VL_TERC, VL_DA, VL_BC_ICMS, VL_ICMS, VL_BC_ICMS_ST,');
  IBQgravnfentrad.SQL.Add(' VL_ICMS_ST, COD_INF, VL_COFINS, TP_LIGACAO, COD_GRUPO_TENSAO, CHV_CTE,');
  IBQgravnfentrad.SQL.Add(' TP_CT, CHV_CTE_REF, IND_FRT, COD_EMPRESA, DCNUMERO, DCSERIE, DCORDEM,');
  IBQgravnfentrad.SQL.Add(' DCTIPO, cdPEDIDO, NATUREZA, CFOP, COD_FILIAL,CODNFE)');
  IBQgravnfentrad.SQL.Add('values ');
  IBQgravnfentrad.SQL.Add('(:IND_OPER, :IND_EMIT, :COD_PART, :COD_SIT, :DT_DOC, :DT_E_S, :VL_DOC, ');
  IBQgravnfentrad.SQL.Add(' :VL_DESC, :VL_FORN, :VL_SERV_NT, :VL_TERC, :VL_DA, :VL_BC_ICMS, :VL_ICMS,');
  IBQgravnfentrad.SQL.Add(' :VL_BC_ICMS_ST, :VL_ICMS_ST, :COD_INF, :VL_COFINS, :TP_LIGACAO, :COD_GRUPO_TENSAO,');
  IBQgravnfentrad.SQL.Add(' :CHV_CTE, :TP_CT, :CHV_CTE_REF, :IND_FRT, :COD_EMPRESA, :DCNUMERO, :DCSERIE, ');
  IBQgravnfentrad.SQL.Add(' :DCORDEM, :DCTIPO, :cdPEDIDO, :NATUREZA, :CFOP, :COD_FILIAL,:CODNFE) ');
  IBQgravnfentrad.ParamByName('IND_OPER').AsInteger := NFFISCAL.IND_OPER;
  IBQgravnfentrad.ParamByName('IND_EMIT').AsInteger := NFFISCAL.IND_EMIT;
  IBQgravnfentrad.ParamByName('COD_PART').AsString := IntToStr(NFFISCAL.COD_PART);
  IBQgravnfentrad.ParamByName('COD_SIT').AsString := NFFISCAL.COD_SIT;
  IBQgravnfentrad.ParamByName('DT_DOC').AsDateTime:= NFFISCAL.DTEMISSAO;
  IBQgravnfentrad.ParamByName('DT_E_S').AsDateTime := NFFISCAL.DTENTRADA;
  IBQgravnfentrad.ParamByName('VL_DOC').AsFloat := NFFISCAL.VL_DOC;
  IBQgravnfentrad.ParamByName('VL_DESC').AsFloat  := NFFISCAL.VL_DESC;
  IBQgravnfentrad.ParamByName('VL_FORN').AsFloat  := NFFISCAL.VL_FORN;
  IBQgravnfentrad.ParamByName('VL_SERV_NT').AsFloat  := NFFISCAL.VL_SERV_NT;
  IBQgravnfentrad.ParamByName('VL_TERC').AsFloat  := NFFISCAL.VL_TERC;
  IBQgravnfentrad.ParamByName('VL_DA').AsFloat  := NFFISCAL.VL_DA;
  IBQgravnfentrad.ParamByName('VL_BC_ICMS').AsFloat  := NFFISCAL.VL_BC_ICMS;
  IBQgravnfentrad.ParamByName('VL_ICMS').AsFloat  := NFFISCAL.VL_ICMS;
  IBQgravnfentrad.ParamByName('VL_BC_ICMS_ST').AsFloat  := NFFISCAL.VL_BC_ICMS_ST;
  IBQgravnfentrad.ParamByName('VL_ICMS_ST').AsFloat  := NFFISCAL.VL_ICMS_ST;
  IBQgravnfentrad.ParamByName('COD_INF').AsFloat  := NFFISCAL.COD_INF;
  IBQgravnfentrad.ParamByName('VL_COFINS').AsFloat := NFFISCAL.VL_COFINS;
  IBQgravnfentrad.ParamByName('TP_LIGACAO').AsFloat := NFFISCAL.TP_LIGACAO ;
  IBQgravnfentrad.ParamByName('COD_GRUPO_TENSAO').Asstring := NFFISCAL.COD_GRUPO_TENSAO;
  IBQgravnfentrad.ParamByName('CHV_CTE').AsString := NFFISCAL.CHV_CTE;
  IBQgravnfentrad.ParamByName('TP_CT').AsString := NFFISCAL.TP_CT;
  IBQgravnfentrad.ParamByName('CHV_CTE_REF').AsString := NFFISCAL.CHV_CTE_REF;
  IBQgravnfentrad.ParamByName('IND_FRT').AsString := NFFISCAL.IND_FRT;
  IBQgravnfentrad.ParamByName('COD_EMPRESA').AsInteger := NFFISCAL.COD_EMPRESA;
  IBQgravnfentrad.ParamByName('CODNFE').AsInteger := NFFISCAL.DCNUMERO;
  IBQgravnfentrad.ParamByName('DCNUMERO').AsInteger := NFFISCAL.DCNUMERO;
  IBQgravnfentrad.ParamByName('DCSERIE').AsString:= NFFISCAL.DCSERIE;
  IBQgravnfentrad.ParamByName('DCORDEM').AsString := NFFISCAL.DCORDEM;
  IBQgravnfentrad.ParamByName('DCTIPO').AsString := NFFISCAL.DCTIPO;
  IBQgravnfentrad.ParamByName('cdPEDIDO').AsInteger := NFFISCAL.CDPEDIDO;
  IBQgravnfentrad.ParamByName('NATUREZA').AsInteger := NFFISCAL.NATUREZA;
  IBQgravnfentrad.ParamByName('CFOP').AsString := NFFISCAL.CFOP;
  IBQgravnfentrad.ParamByName('COD_FILIAL').AsInteger := NFFISCAL.Cod_Filial;
  IBQgravnfentrad.ExecSQL;
  IBQgravnfentrad.Transaction.Commit;
  result := True;
 Except
   result := False;
 end;
end;



function TForm7.populacampos():Boolean;
begin

  NFENTRADAS := TNFENTRADAS.Create;
         NFFISCAL.IND_OPER := 01;
         NFFISCAL.IND_EMIT := 01;
         NFFISCAL.COD_PART := StrToInt(ActResultEdit1.text);
         NFFISCAL.COD_SIT   :=  '01' ; //CODIGO DO DOCUMENTO FISCAL
         NFFISCAL.DTEMISSAO    := StrToDate(TXTDTEMISSAO.Text);
         NFFISCAL.DTENTRADA    := StrToDate(TXTDTENTRADA.Text);
         NFFISCAL.VL_DOC    := 0;
         NFFISCAL.VL_FORN   := 0;
         NFFISCAL.VL_DOC    := StrToFloat(TXTVLNOTA.Text);
         NFFISCAL.VL_FORN   := StrToFloat(TXTVLNOTA.Text);
         NFFISCAL.VL_SERV_NT  :=  0;
         NFFISCAL.VL_TERC     :=  0;
         NFFISCAL.VL_DA       :=  0;
         NFFISCAL.VL_DESC   := 0 ;
         NFFISCAL.VL_DESC   := StrToFloat(TXTVL_DESC.Text);
         NFFISCAL.VL_FORN   := 0; //(Valor total fornecido/consumido)
         NFFISCAL.VL_SERV_NT := 0; //(Valor total dos servi�os n�o-tributados pelo ICMS)
         NFFISCAL.VL_TERC    := 0; // (Valor total cobrado em nome de terceiros)
         NFFISCAL.VL_DA      := 0; //(Valor total de despesas acess�rias indicadas no documento fiscal)
         NFFISCAL.VL_BC_ICMS := StrToFloat(TXTICMSBASE.Text);
         NFFISCAL.VL_ICMS    := StrToFloat(TXTICMS.Text);
         NFFISCAL.VL_BC_ICMS_ST  :=  StrToFloat(TXTBASEST.Text);
         NFFISCAL.VL_ICMS_ST     :=  StrToFloat(TXTST.Text);
         NFFISCAL.COD_INF        := 0;
         NFFISCAL.VL_COFINS      := StrToFloat(TXTCONFINS.Text);
         NFFISCAL.TP_LIGACAO     := 2 ; //(C�digo da informa��o complementar do documento fiscal (campo 02 do Registro 0450))
         NFFISCAL.COD_GRUPO_TENSAO := '1';
         NFFISCAL.TP_CT    :=   '0';
         NFFISCAL.CHV_CTE_REF := TXTCHACESSO.Text;
         NFFISCAL.IND_FRT     := TXTFRETE.Text;   // '2'; // (Indicador do tipo do frete:){0- Por conta de terceiros;1- Por conta do emitente;2- Por conta do destinat�rio;9- Sem cobran�a de frete.}
         NFFISCAL.CODNFE      := 0;
         NFFISCAL.COD_EMPRESA  := StrToInt(TXTEMPRESA.Text);
         NFFISCAL.COD_FILIAL   := StrToInt(TXTFILIAL.Text);
         NFFISCAL.DCNUMERO   :=  StrToInt(TXTDCNUMERO.Text);
         NFFISCAL.DCSERIE    := TXTDCSERIE.Text;
         NFFISCAL.DCORDEM    := TXTDCORDEM.Text ;
         NFFISCAL.DCTIPO     := TXTDCTIPO.Text;
         NFFISCAL.CDPEDIDO   := StrToInt(TXTCDPEDIDO.text);
         NFFISCAL.NATUREZA   := StrToInt(txtnatureza.text);
         NFFISCAL.CFOP       := TXTCFOP.text;
         NFFISCAL.CHV_CTE    := TXTCHACESSO.Text;
         NFFISCAL.TP_CT      := '0';
         NFFISCAL.CHV_CTE_REF:= '0';
         
end;

function TForm7.populaTXT():Boolean;
begin
 NFFISCAL.IND_OPER  := IBQuery4.FieldByName('IND_OPER').AsInteger;
 NFFISCAL.IND_EMIT  := IBQuery4.FieldByName('IND_EMIT').AsInteger;
 NFFISCAL.COD_PART  :=  IBQuery4.FieldByName('COD_PART').AsInteger;
 NFFISCAL.COD_SIT   :=  IBQuery4.FieldByName('COD_SIT').AsString;
 NFFISCAL.DTEMISSAO  :=  IBQuery4.FieldByName('DT_DOC').AsDateTime;
 NFFISCAL.DTENTRADA  :=  IBQuery4.FieldByName('DT_E_S').AsDateTime;
 NFFISCAL.VL_DOC  :=  IBQuery4.FieldByName('VL_DOC').AsFloat;
 NFFISCAL.VL_DESC  :=  IBQuery4.FieldByName('VL_DESC').AsFloat;
 NFFISCAL.VL_FORN  :=   IBQuery4.FieldByName('VL_FORN').AsFloat;
 NFFISCAL.VL_SERV_NT  :=   IBQuery4.FieldByName('VL_SERV_NT').AsFloat;
 NFFISCAL.VL_TERC  :=  IBQuery4.FieldByName('VL_TERC').AsFloat;
 NFFISCAL.VL_DA  :=   IBQuery4.FieldByName('VL_DA').AsFloat;
 NFFISCAL.VL_BC_ICMS   := IBQuery4.FieldByName('VL_BC_ICMS').AsFloat;
 NFFISCAL.VL_ICMS  :=  IBQuery4.FieldByName('VL_ICMS').AsFloat;
 NFFISCAL.VL_BC_ICMS_ST  :=  IBQuery4.FieldByName('VL_BC_ICMS_ST').AsFloat;
 NFFISCAL.VL_ICMS_ST  :=  IBQuery4.FieldByName('VL_ICMS_ST').AsFloat;
 NFFISCAL.COD_INF  :=  IBQuery4.FieldByName('COD_INF').AsFloat;
 NFFISCAL.VL_COFINS  :=  IBQuery4.FieldByName('VL_COFINS').AsFloat;
 NFFISCAL.TP_LIGACAO  :=  IBQuery4.FieldByName('TP_LIGACAO').AsFloat;
 NFFISCAL.COD_GRUPO_TENSAO  :=  IBQuery4.FieldByName('COD_GRUPO_TENSAO').Asstring;
 NFFISCAL.CHV_CTE  :=  IBQuery4.FieldByName('CHV_CTE').AsString ;
 NFFISCAL.TP_CT  :=  IBQuery4.FieldByName('TP_CT').AsString ;
 NFFISCAL.CHV_CTE_REF  :=  IBQuery4.FieldByName('CHV_CTE_REF').AsString ;
 NFFISCAL.IND_FRT  :=  IBQuery4.FieldByName('IND_FRT').AsString;
 NFFISCAL.COD_EMPRESA  :=  IBQuery4.FieldByName('COD_EMPRESA').AsInteger;
 NFFISCAL.DCNUMERO  :=  IBQuery4.FieldByName('CODNFE').AsInteger;
 NFFISCAL.DCNUMERO  :=  IBQuery4.FieldByName('DCNUMERO').AsInteger;
 NFFISCAL.DCSERIE  :=  IBQuery4.FieldByName('DCSERIE').AsString;
 NFFISCAL.DCORDEM   :=  IBQuery4.FieldByName('DCORDEM').AsString;
 NFFISCAL.DCTIPO  :=  IBQuery4.FieldByName('DCTIPO').AsString;
 NFFISCAL.CDPEDIDO   := IBQuery4.FieldByName('cdPEDIDO').AsInteger;
 NFFISCAL.NATUREZA   := IBQuery4.FieldByName('NATUREZA').AsInteger;
 NFFISCAL.CFOP   := IBQuery4.FieldByName('CFOP').AsString;
 NFFISCAL.Cod_Filial  :=  IBQuery4.FieldByName('COD_FILIAL').AsInteger;
 NFFISCAL.CODNFE       := IBQuery4.FieldByName('CODNFE').AsInteger;
end;

procedure TForm7.SpeedButton7Click(Sender: TObject);
begin
primeiro();
end;

procedure TForm7.SpeedButton8Click(Sender: TObject);
begin
ultimo();
end;

procedure TForm7.SpeedButton5Click(Sender: TObject);
begin
proximo();
end;

procedure TForm7.SpeedButton6Click(Sender: TObject);
begin
Anterior();
end;

procedure TForm7.SpeedButton2Click(Sender: TObject);
begin
LimpaCampos();
end;

procedure TForm7.SpeedButton4Click(Sender: TObject);
begin

POPULANFENTRADAS();
//  if (ApuraValores = True  )then
  //  begin
if( MessageDlg(' Deseja fechar a NOTA', mtConfirmation, [mbYes, mbNo], 0) = mrYes) then
 NFFISCAL.COD_SIT := 'I'
else
 NFFISCAL.COD_SIT := 'A';
      if (GRAVARNFENTRADAS = True ) then
          ShowMessage('Nota Gravada com SUCESSO')

      else
          ShowMessage('ERRO ao gravar a nota verifique as informa��es e tente novamente');

 // else
 // ShowMEssage('Valor do TITULO e ITENS n�o bate com o valor da nota');
end;
procedure TForm7.SpeedButton1Click(Sender: TObject);
begin

  if (GRAVARTITULO = True) then
  begin
      ShowMEssage('TITULO GRAVADO COM SUCESSO');
   IBQUERY6.Active := FALSE;
   IBQUERY6.SQL.Text := 'SELECT *FROM TITULOSPAGAR2 WHERE DCNUMERO = '+TXTDCNUMERO.Text+' AND DCSERIE = '''+txtDCSERIE.Text+''' AND DCORDEM = '''+txtDCORDEM.Text+''' AND DCTIPO = '''+txtDCTIPO.Text+'''';
   IBQUERY6.Active := TRUE;
  end
  ELSE
      ShowMEssage('ERRO ao gravar o TITULO verifique as informa��es e tente novamente');

end;

procedure TForm7.SpeedButton13Click(Sender: TObject);
var nfe,numitem:Integer;
begin
if (Gravar = False ) then
begin
POPULANFENTRADASITENS();
     IBQuery15.Active := False;
     IBQuery15.SQL.Text := 'select rateio  from nffiscalitens where rateio = ''N'' and cod_Item =  '+TXTCODPRO.Text ;
     IBQuery15.Active := True;
if( IBQuery15.eof)then
begin


if MessageDlg('Deseja fazer RATEIO', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
    begin
     IBQuery15.Active := False;
     IBQuery15.SQL.Text := 'select MAX(codnfe) from nffiscal ' ;
     IBQuery15.Active := True;
     nfe := IBQuery15.FieldByName('MAX').AsInteger;

     IBQuery15.Active := False;
     IBQuery15.SQL.Text := 'select max(Num_Item) from nffiscalitens ' ;
     IBQuery15.Active := True;
     numitem := IBQuery15.FieldByName('MAX').AsInteger;

      FrmRateio.inserir_dados(nfe,numitem,StrToFloat(txtquantidade.Text));
      FrmRateio.ShowModal;
      NFISCALitens.rateio := 'S';
    end
    else
       NFISCALitens.rateio := 'N';
end;

  if (GRAVARNFENTRADASITENS = True) then
      ShowMEssage('ITENS  GRAVADO COM SUCESSO')
  ELSE
      ShowMEssage('ERRO ao gravar o ITENS verifique as informa��es e tente novamente');


end
else
begin
   POPULANFENTRADASITENS();
   if (Alteraritens  = True) then
      ShowMEssage('ITEN  ALTERADO COM SUCESSO')
  ELSE
      ShowMEssage('ERRO ao ALTERAR ITEM verifique as informa��es e tente novamente');

end;
end;
procedure TForm7.DBGrid1DblClick(Sender: TObject);
begin
  if (DBGrid1.Fields[1].asString = 'A')then
     apagatitulospagar(DBGrid1.Fields[0].asInteger,DBGrid1.Fields[4].asInteger,DBGrid1.Fields[1].asString,DBGrid1.Fields[2].asString,DBGrid1.Fields[3].asString)
  else
     ShowMessage('Titulo encontra -se pago e n�o pode ser excluso !!');
end;

procedure TForm7.txtcodproExit(Sender: TObject);
begin
VerificaProduto(StrToInt(txtcodpro.Text) );
end;

procedure TForm7.SpeedButton14Click(Sender: TObject);
var
  i, j, k, n  : integer;
  Nota, Node, NodePai, NodeItem: TTreeNode;
  NFeRTXT: TNFeRTXT;
  a :String;
  sai :Boolean;
begin
  OpenDialog1.FileName  :=  '';
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Arquivos TXT (*.TXT)|*.TXT|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    //tenta TXT
    ACBrNFe1.NotasFiscais.Add;
    NFeRTXT := TNFeRTXT.Create(ACBrNFe1.NotasFiscais.Items[0].NFe);
    NFeRTXT.CarregarArquivo(OpenDialog1.FileName);
    if NFeRTXT.LerTxt then
       NFeRTXT.Free
    else
    begin
       NFeRTXT.Free;
       //tenta XML
       ACBrNFe1.NotasFiscais.Clear;
       try
          ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
       except
          ShowMessage('Arquivo NFe Inv�lido');
          exit;
       end;
    end;

   // trvwNFe.Items.Clear;

    for n:=0 to ACBrNFe1.NotasFiscais.Count-1 do
    begin
    with ACBrNFe1.NotasFiscais.Items[n].NFe do
     begin
        try
        NFFISCAL := TNFISCAL.Create;
         sai := False;
         NFFISCAL.IND_EMIT  := 1;
         NFFISCAL.COD_PART  := 2; //(Indicador do emitente do documento fiscal:0- Emiss�o pr�pria;1- 2Terceiros)
         repeat
         ibquery10.Active := False;
         ibquery10.SQL.Text := 'Select *from CLIENTES_FORNECEDOR where CNPJ = '''+ajustaPONTO(Emit.CNPJCPF)+''' and status ''A''';
         ShowMessage(ibquery10.SQL.Text);
         ibquery10.Active := True;
         if (not ibquery10.Eof )then
         begin
         NFFISCAL.COD_PART  := Ibquery10.fieldbyname('COD_PART').AsInteger;
         sai := True;
         end
          ELSE
           BEGIN
              ibquery10.Active := False;
              ibquery10.SQL.Text := 'Select *from CLIENTES_FORNECEDOR where CPF = '+Emit.CNPJCPF;
              ibquery10.Active := True;
                if (not ibquery10.Eof )then
                begin
                  NFFISCAL.COD_PART  := Ibquery10.fieldbyname('COD_PART').AsInteger;
                  sai := True;
                end

           ELSE
             if MessageDlg('Fornecedor n�o encontrado deseja cadastrar !!', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
             begin
              Form10.limpar;
              Form10.POPULARTXT();
              Form10.TXTEMPRESA.Text := '1' ;
              Form10.TXTFILIAL.Text := '1' ;
              Form10.TXTRAZAO.Text     := Emit.xNome ;
              Form10.TXTCOD_PAIS.Text := '055' ;
              Form10.TXTIE.Text       := Emit.IE ;
              Form10.TXTLOGRADOURO.Text     := Emit.EnderEmit.xLgr;
              Form10.TXTCep.Text      :=IntToStr(Emit.EnderEmit.CEP) ;
              Form10.TXTFantasia.Text  := Emit.xFant ;
              Form10.TXTcnpj.Text := Emit.CNPJCPF;
              Form10.txtnum.Text := Emit.EnderEmit.nro;
              Form10.ShowModal;

             end
             else
               begin
                 ShowMessage('Nota n�o importada favor lan�ar o fornecedor');
                 sai := True;
                 Form7.Close;

               end;

           end



         until (sai <> false);
         NFFISCAL.COD_SIT   :=  '01' ; //CODIGO DO DOCUMENTO FISCAL
         NFFISCAL.DTEMISSAO    := Ide.dEmi;
         NFFISCAL.DTENTRADA    := Now();
         NFFISCAL.VL_DOC    := Total.ICMSTot.vNF;
         NFFISCAL.VL_DESC   := Total.ICMSTot.vDesc;
         NFFISCAL.VL_FORN   := 0; //(Valor total fornecido/consumido)
         NFFISCAL.VL_SERV_NT := 0; //(Valor total dos servi�os n�o-tributados pelo ICMS)
         NFFISCAL.VL_TERC    := 0; // (Valor total cobrado em nome de terceiros)
         NFFISCAL.VL_DA      := 0; //(Valor total de despesas acess�rias indicadas no documento fiscal)
         NFFISCAL.VL_BC_ICMS := Total.ICMSTot.vBC;
         NFFISCAL.VL_ICMS    := Total.ICMSTot.vICMS;
         NFFISCAL.VL_BC_ICMS_ST  :=  Total.ICMSTot.vBCST;
         NFFISCAL.VL_ICMS_ST     :=  Total.ICMSTot.vST;
         NFFISCAL.COD_INF        := 0;
         NFFISCAL.VL_COFINS      := Total.ICMSTot.vCOFINS ;
         NFFISCAL.TP_LIGACAO     := 2 ; //(C�digo da informa��o complementar do documento fiscal (campo 02 do Registro 0450))
         NFFISCAL.COD_GRUPO_TENSAO := '1';
         NFFISCAL.TP_CT    :=   procNFe.chNFe;
         NFFISCAL.CHV_CTE_REF := procNFe.chNFe ;
         NFFISCAL.IND_FRT     := '2'; // (Indicador do tipo do frete:){0- Por conta de terceiros;1- Por conta do emitente;2- Por conta do destinat�rio;9- Sem cobran�a de frete.}
         NFFISCAL.CODNFE      := procNFe.cStat;
         NFFISCAL.COD_EMPRESA  := 1;
         NFFISCAL.COD_FILIAL   := 1;
         NFFISCAL.DCNUMERO   :=  Ide.nNF;
         NFFISCAL.DCSERIE    := IntToStr(Ide.serie);
         NFFISCAL.DCORDEM    := 'A' ;
         NFFISCAL.DCTIPO     := 'NF';
         POPULARNFFFISCAL();


        ShowMessage ('Nota Importada com sucesso');

     Except
        ShowMEssage('problema na grava��o da nota');
     end;
     Produtos := TProduto.Create;
       for I := 0 to Det.Count-1 do
       begin
          with Det.Items[I] do
           begin
            sai := false;
           repeat
             if (ValidaProdutos(StrToInt(ajustaSpaco(Prod.cProd)),ajustaSpaco(Prod.NCM)))then
              begin
                nfentradas.CODPRO := NFISCALitens.COD_ITEM;
                sai := True;
              end
             else
                if MessageDlg('PRODUTO n�o encontrado deseja cadastrar !!', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
                    begin
                      FRMCADPROD.limpar();
                      FRMCADPROD.txtDescricao.Text    :=  Prod.xProd;
                      FRMCADPROD.txtCompNCM.Text      :=  ajustaSpaco(Prod.cProd);
                      FRMCADPROD.TXTAQUIS.Text        :=  CurrToStr(Prod.qCom);
                      FRMCADPROD.TXtICMS.Text         :=  '0';
                      FRMCADPROD.TXtESTOQUE.Text      :=   '0';
                      FRMCADPROD.txtncm.Text          :=  ajustaSpaco(Prod.NCM);
                      FRMCADPROD.ShowModal;
                    end;
            until (sai <> False);
              try
              NFISCALitens.COD_EMPRESA      := StrToInt(TXTEMPRESA.text);
              NFISCALitens.COD_FILIAL       := StrToInt(TXTFILIAL.Text);
              NFISCALitens.DCNUMERO         := StrToInt(TXTDCNUMERO.Text);
              NFISCALitens.DCSERIE          := TXTDCSERIE.Text;
              NFISCALitens.DCORDEM          := TXTDCORDEM.Text;
              NFISCALitens.dctipo           := TXTDCTIPO.Text;
              NFISCALitens.CDFORNECEDOR     := StrToInt(ActResultEdit1.text);
              a := CurrToStr(Prod.qCom);
              NFISCALitens.QTD              :=  StrToFloat(a)  ;
              NFISCALitens.VL_DESC          :=  Prod.vDesc;
              NFISCALitens.VL_ITEM          :=  Prod.vProd ;
              NFISCALitens.IND_MOV          :=  1.00 ;
              NFISCALitens.CST_ICMS         :=  0;
              NFISCALitens.CFOP             :=  Prod.CFOP;
              NFISCALitens.Cod_NCM          :=  ajustaSpaco(Prod.NCM);
              NFISCALitens.Ref              :=  ajustaSpaco(Prod.cProd);
              NFISCALitens.ALIQ_ICMS        :=  Imposto.ICMS.pICMS;
              NFISCALitens.VL_ICMS          :=  Imposto.ICMS.vICMS;
              NFISCALitens.VL_BC_ICMS_ST    :=  Imposto.ICMS.vBCST;
              NFISCALitens.ALIQ_ST          :=  Imposto.ICMS.pICMSST;
              NFISCALitens.VL_ICMS_ST       :=  Imposto.ICMS.vICMSST;
              NFISCALitens.IND_APUR         :=  1;
              NFISCALitens.CST_IPI          :=  0;
              NFISCALitens.COD_ENQ          :=  0;
              NFISCALitens.VL_BC_IPI        :=  Imposto.ipi.vBC;
              NFISCALitens.ALIQ_IPI         :=  Imposto.ipi.pIPI;
              NFISCALitens.VL_IPI           :=  Imposto.ipi.vIPI;
              NFISCALitens.CST_PIS          :=  0;
              NFISCALitens.VL_BC_PIS        :=  Imposto.PIS.vBC;
              NFISCALitens.ALIQ_PIS         :=  Imposto.PIS.pPIS;
              NFISCALitens.QUANT_BC_PIS     :=  Imposto.PIS.qBCProd;
              NFISCALitens.VL_PI            :=  Imposto.PIS.vPIS;
              NFISCALitens.CST_COFINS       :=  0;
              NFISCALitens.VL_BC_COFINS     :=  Imposto.COFINS.vBC;
              NFISCALitens.ALIQ_COFINS      :=  Imposto.COFINS.pCOFINS;
              NFISCALitens.QUANT_BC_COFINS  :=  Imposto.COFINS.qBCProd;
              NFISCALitens.VL_COFINS        :=  Imposto.COFINS.vCOFINS;
              NFISCALitens.COD_CTA          :=  0;






              GRAVARNFENTRADASITENS;
              ShowMEssage('grava��o dos Itens');
              Except
                ShowMEssage('Erro na grava��o dos Itens');
               end ;
         end;

       end;


     end;

     end;
     Atualizagrid(StrToInt(TXTDCNUMERO.Text));
     LimpaCampos();
     populaTXT;
  end;
end;




procedure TForm7.SpeedButton15Click(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
    if ACBrNFe1.NotasFiscais.Items[0].NFe.Ide.tpEmis = teDPEC then
     begin
       ACBrNFe1.WebServices.ConsultaDPEC.NFeChave := ACBrNFe1.NotasFiscais.Items[0].NFe.infNFe.ID;
       ACBrNFe1.WebServices.ConsultaDPEC.Executar;
       ACBrNFe1.DANFE.ProtocoloNFe := ACBrNFe1.WebServices.ConsultaDPEC.nRegDPEC +' '+ DateTimeToStr(ACBrNFe1.WebServices.ConsultaDPEC.retDPEC.dhRegDPEC);
     end;
    ACBrNFe1.NotasFiscais.Imprimir;
  end;
end;

procedure TForm7.SpeedButton16Click(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
    ACBrNFe1.NotasFiscais.Valida;
    showmessage('Nota Fiscal Eletr�nica Valida');
  end;
end;

procedure TForm7.SpeedButton17Click(Sender: TObject);
begin
 IBQuery5.Open;
 IBQuery5.Edit;
end;

procedure TForm7.SpeedButton18Click(Sender: TObject);
var
  x :Integer;
begin
  X := DBGrid2.FieldCount;
  IBQuery5.Post;
  IBQuery5.Transaction.CommitRetaining;
end;

procedure TForm7.DBGrid2DblClick(Sender: TObject);
begin
IBQuery14.SQL.Clear;
IBQuery14.Active:= False;
IBQuery14.SQL.Add('select *from nffiscalitens i where i.cod_empresa = :cdempresa and');
IBQuery14.SQL.Add('i.cod_filial  = :cdfilial  and ');
IBQuery14.SQL.Add('i.dcnumero    = :dcnumero and cod_item = :coditem ');
IBQuery14.ParamByName('cdempresa').AsInteger := 1;
IBQuery14.ParamByName('coditem').AsInteger := dbGrid2.Fields[4].AsInteger;
IBQuery14.ParamByName('cdfilial').AsInteger := 1;
IBQuery14.ParamByName('dcnumero').AsInteger := dbGrid2.Fields[0].AsInteger;
IBQuery14.Active := True;
if ( not IBQuery14.Eof)then
  gravar := True
else
  gravar := False;


txtcodPro.text := dbGrid2.Fields[4].AsString;
txtQuantidade.text := dbGrid2.Fields[8].AsString;
//txtIpiI.text := dbGrid2.Fields[6].AsString;
//txtStI.text := dbGrid2.Fields[7].AsString;
txtIcmsI.text := dbGrid2.Fields[11].AsString;
txtVlunitario.text := dbGrid2.Fields[9].AsString;

//auterarItens(quantidade,            ipi,st,                  icms,   vlunitario,         vldesconto   cdfornecedor,                DCNUMERO,CDPRODUTO,CODPRO:Integer;DCSERIE,DCORDEM,DCTIPO:String):Boolean;
//auterarItens(dbGrid2.Fields[8].AsFloat,0,0,StrToFloat(TXTICMSI.Text),StrToFloat(txtvlunitario.Text),0,StrToInt(txtfornecedor.text),StrToInt(txtDCNUMERO.text),dbGrid2.Fields[4].AsInteger,dbGrid2.Fields[4].AsInteger,txtDCSERIE.text,txtDCORDEM.text,txtDCTIPO.text);

end;

procedure TForm7.SpeedButton3Click(Sender: TObject);
begin
POPULANFENTRADAS();
      if (Alterar = True ) then
          ShowMessage('Nota ALTERADA com SUCESSO')
      else
          ShowMessage('ERRO ao na ALTERA��O da nota verifique as informa��es e tente novamente');

end;

procedure TForm7.TXTCODPROChange(Sender: TObject);
begin
    if (TXTCODPRO.Text <> '')then
  begin
     VerificaProduto(StrToInt(txtcodpro.Text) );
  end;
end;

procedure TForm7.EXCLUIR1Click(Sender: TObject);
begin
IBQuery14.SQL.Clear;
IBQuery14.SQL.Add('delete From nffiscalitens i where i.num_item = :NumItem') ;
IBQuery14.ParamByName('NumItem').AsInteger:= DBGrid2.Fields[4].AsInteger;
IBQuery14.ExecSQL;
IBQuery14.Transaction.Commit;
 IBQuery5.Active := False;
 IBQuery5.Active := True;
end;

procedure TForm7.SpeedButton10Click(Sender: TObject);
begin
IBQuery7.Active := fALSE;
IBQuery7.SQL.Text := 'SELECT MAX(CODNFE)+1 FROM NFFISCAL';
IBQuery7.Active := tRUE;
TXTNUMNF.Text := IntToStr(IBQuery7.FieldByName('F_1').AsInteger);
   TXTEMPRESA.Text := '';
   TXTFILIAL.Text := '';
   TXTDCNUMERO.Text := '';
   TXTDCSERIE.Text := '';
   TXTDCORDEM.Text := '';
   TXTDCTIPO.Text := '';
   TXTICMS.Text := '';
   TXTICMSBASE.Text := '';
   TXTBASEST.Text := '';
   TXTST.Text := '';
   TXTPIS.Text := '';
   TXTCONFINS.Text := '';
   TXTVLNOTA.Text := '';
   TXTVL_DESC.Text := '';
   TXTDTEMISSAO.Text := '';
   TXTDTENTRADA.Text := '';
   TXTCFOP.Text := '';
   TXTIPI.Text := '';
   TXTCDPEDIDO.Text := '';
   TXTFRETE.Text := '';
   txtnatureza.Text := '';
   ActResultEdit1.Text := '';
   TXTCHACESSO.Text := '';
   TXTPROT.Text := '';
   TXTSTATUSNFE.Text := '';


   RadioGroup1.Enabled := True;
   ActRadioButton1.Checked := False;
   TXTEMPRESA.Enabled := True;
   TXTFILIAL.Enabled := True;
   TXTDCNUMERO.Enabled := True;
   TXTDCSERIE.Enabled := True;
   TXTDCORDEM.Enabled := True;
   TXTDCTIPO.Enabled := True;
   TXTICMS.Enabled := True;
   TXTICMSBASE.Enabled := True;
   TXTBASEST.Enabled := True;
   TXTST.Enabled := True;
   TXTPIS.Enabled := True;
   TXTCONFINS.Enabled := True;
   TXTVLNOTA.Enabled := True;
   TXTVL_DESC.Enabled := True;
   TXTDTEMISSAO.Enabled := True;
   TXTDTENTRADA.Enabled :=  True;
   TXTCFOP.Enabled := True;
   TXTIPI.Enabled := True;
   TXTCDPEDIDO.Enabled := True;
   TXTFRETE.Enabled := True;
   txtnatureza.Enabled := True;
   ActResultEdit1.Enabled := True;
   TXTCHACESSO.Enabled := True;
   TXTPROT.Enabled := True;
   TXTSTATUSNFE.Enabled := True;

   IBQuery5.Active := False;
   IBQuery6.Active := False;
end;

procedure TForm7.TXTEMPRESAButtonClick(Sender: TObject);
begin
Form11.InsertCodRet(2);
Form11.Show;
end;

procedure TForm7.txtnaturezaButtonClick(Sender: TObject);
begin
ConsTransacao.Show;
end;

procedure TForm7.txtfornecedorButtonClick(Sender: TObject);
begin
ConsForn.InsertCodRet(2);
ConsForn.show;
end;

procedure TForm7.ActResultEdit1ButtonClick(Sender: TObject);
begin
ConsForn.InsertCodRet(2);
ConsForn.Show;
end;

procedure TForm7.TXTCODPROButtonClick(Sender: TObject);
begin
Form15.InsertCodRet(2);
Form15.Show;
end;

procedure TForm7.TXTTIPO_TITButtonClick(Sender: TObject);
begin
ConsTipoTitulo.InsertCodRet(1);
ConsTipoTitulo.Show;
end;

procedure TForm7.FormCreate(Sender: TObject);
begin
   NFFISCAL := TNFISCAL.Create;
   NFISCALitens := TNFISCALitens.Create;
   selectprinc();
   populaTXT();
   POPULARNFFFISCAL ;

   ApuraValores ;
end;

procedure TForm7.ATUALIZAR1Click(Sender: TObject);
begin
 IBQuery5.Active := False;
 IBQuery5.Active := True;
end;

end.