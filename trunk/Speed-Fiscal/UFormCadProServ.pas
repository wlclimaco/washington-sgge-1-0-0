unit UFormCadProServ;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UFormModelo, ActCurrencyEdit, StdCtrls, Buttons, ActButton,
  ActEdit,UProd_Serv, ActMask, ActMaskEdit, DBCtrls, ExtCtrls, ActCustomEdit,
  ActNumberEdit, ActResultEdit, DB, IBCustomDataSet, IBQuery, IBDatabase,
  IBUpdateSQL, Grids, DBGrids;

type
  TFRMCADPROD = class(TForm1)
    GroupBox1: TGroupBox;
    GroupBox2: TGroupBox;
    GroupBox3: TGroupBox;
    GroupBox4: TGroupBox;
    GroupBox5: TGroupBox;
    txtncm: TActMaskEdit;
    txtCompNCM: TActNumberEdit;
    txtDescricao: TActEdit;
    txtUnidMed: TActResultEdit;
    TxtInteiro: TActEdit;
    Txt_Tipo_Item: TActResultEdit;
    TxtProd_Esp: TActResultEdit;
    TxtCodServ: TActResultEdit;
    ActButton1: TActButton;
    Txt_CST_origem: TActResultEdit;
    Txt_CST_Tributacao: TActResultEdit;
    TXT_OPERACAO: TActResultEdit;
    TXTICMS: TActCurrencyEdit;
    TXTIPI: TActCurrencyEdit;
    TXTREDBASECALC: TActCurrencyEdit;
    TXTCONfins: TActCurrencyEdit;
    TXTPIS: TActCurrencyEdit;
    TXTMVA: TActCurrencyEdit;
    IBQuery1: TIBQuery;
    IBUpdateSQL1: TIBUpdateSQL;
    GroupBox6: TGroupBox;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    GroupBox7: TGroupBox;
    txtempresa: TActResultEdit;
    txtfilial: TActResultEdit;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    QryEmpresa: TIBQuery;
    Label4: TLabel;
    QryFilial: TIBQuery;
    Label5: TLabel;
    Qryunimed: TIBQuery;
    QryTipoItem: TIBQuery;
    QryProdSpe: TIBQuery;
    Label6: TLabel;
    SpeedButton12: TSpeedButton;
    Label7: TLabel;
    QryProdutos: TIBQuery;
    GroupBox8: TGroupBox;
    TXTAQUIS: TActCurrencyEdit;
    TXTMARGEM: TActCurrencyEdit;
    TXTPRECOVENDA: TActCurrencyEdit;
    GroupBox9: TGroupBox;
    TXTESTOQUE: TActCurrencyEdit;
    IBDatabase1: TIBDatabase;
    IBTransaction1: TIBTransaction;
    TXTGRUPO: TActResultEdit;
    txtSub_Grupo: TActResultEdit;
    TxtDerivacao: TActResultEdit;
    Label8: TLabel;
    GroupBox10: TGroupBox;
    DBGrid1: TDBGrid;
    Label9: TLabel;
    Label10: TLabel;
    IBQuery2: TIBQuery;
    IBQuery3: TIBQuery;
    SpeedButton13: TSpeedButton;
    SpeedButton14: TSpeedButton;
    SpeedButton15: TSpeedButton;
    SpeedButton16: TSpeedButton;
    IBQuery4: TIBQuery;
    DataSource1: TDataSource;
    IBUpdateSQL2: TIBUpdateSQL;
    Label11: TLabel;
    TXTTAMANHO: TActEdit;
    TXTREF: TActResultEdit;
    IBQuery5: TIBQuery;
    procedure FormCreate(Sender: TObject);
    procedure SpeedButton11Click(Sender: TObject);
    procedure SpeedButton10Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure SpeedButton4Click(Sender: TObject);
    procedure SpeedButton5Click(Sender: TObject);
    procedure txtempresaChange(Sender: TObject);
    procedure txtfilialChange(Sender: TObject);
    procedure txtUnidMedChange(Sender: TObject);
    procedure Txt_Tipo_ItemChange(Sender: TObject);
    procedure TxtProd_EspChange(Sender: TObject);
    procedure TxtInteiroChange(Sender: TObject);
    procedure txtUnidMedButtonClick(Sender: TObject);
    procedure txtempresaButtonClick(Sender: TObject);
    procedure txtfilialButtonClick(Sender: TObject);
    procedure Txt_Tipo_ItemButtonClick(Sender: TObject);
    procedure TxtProd_EspButtonClick(Sender: TObject);
    procedure ActResultEdit1ButtonClick(Sender: TObject);
    procedure cod(Sender: TObject);
    procedure SpeedButton12Click(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure SpeedButton9Click(Sender: TObject);
    procedure TxtDerivacaoButtonClick(Sender: TObject);
    procedure TXTGRUPOExit(Sender: TObject);
    procedure TXTGRUPOChange(Sender: TObject);
    procedure txtSub_GrupoChange(Sender: TObject);
    procedure TXTGRUPOButtonClick(Sender: TObject);
    procedure txtSub_GrupoButtonClick(Sender: TObject);
    procedure DBGrid1KeyPress(Sender: TObject; var Key: Char);
    procedure SpeedButton15Click(Sender: TObject);
    procedure SpeedButton16Click(Sender: TObject);
    procedure SpeedButton14Click(Sender: TObject);
    procedure SpeedButton13Click(Sender: TObject);
    procedure ActResultEdit1Change(Sender: TObject);
    procedure SpeedButton6Click(Sender: TObject);
    procedure TXTMARGEMExit(Sender: TObject);
  private
    { Private declarations }
  public
   function GravaProdutosCLasse():Boolean;
   function GravaProdutosEdit():Boolean;
   function GravaProdutosSql():Boolean;
   function GravaProdutos():Boolean;
   Function  Proximo(): Boolean;
   Function  Anterior(): Boolean;
   Function  primeiro(): Boolean;
   Function  ultimo(): Boolean;
   function Auterar(): Boolean;
   Function  Deletar(): Boolean;
   function  limpar():boolean;
   function  SelectInicial():boolean;
   function Atualiza():boolean;
  end;

var
  FRMCADPROD: TFRMCADPROD;
  produtos1  : TProduto;
implementation

uses UConsUniMed, UConsEmpr, UConsFilial, UConstipoItem,
  UConsTipoProd, UConsProd, Unit1, UconsMarca, UConsSub_Grupo, UConsGrupo;

{$R *.dfm}
function  TFRMCADPROD.SelectInicial():boolean;
begin
      IBQuery1.Active := False;
     IBQuery1.SQL.Clear;
     IBQuery1.SQL.Text := 'select *from Prod_serv order by cod_produto';
     IBQuery1.Active := True;
end;
   function TFRMCADPROD.Primeiro ():boolean;
   begin
     IBQuery1.First;
   end;
   function TFRMCADPROD.Ultimo ():boolean;
      begin
     IBQuery1.Last;
   end;
   function TFRMCADPROD.Proximo ():boolean;
      begin
     IBQuery1.Next;
   end;
   function TFRMCADPROD.Anterior ():boolean;
      begin
     IBQuery1.Prior;
   end;
   function TFRMCADPROD.Atualiza():boolean;
      begin
      IBQuery1.Active := True;
      IBQuery1.SQL.Text := 'Select *from Prod_Serv';
      IBQuery1.Active := False;
   end;
   function TFRMCADPROD.GravaProdutosEdit():Boolean;
   begin
     ActResultEdit1.Text     :=  IntToStr(produtos1.COD_PRODUTO);
     txtgrupo.Text           :=  IntToStr(produtos1.GRUPO);
     txtEMPRESA.Text         :=  IntToStr(produtos1.EMPRESA);
     txtFILIAL.Text          :=  IntToStr(produtos1.FILIAL);
     txtSub_Grupo.Text       :=  IntToStr(produtos1.SUBGRUPO);
     txtncm.Text             :=  produtos1.COD_NCM;
     txtCompNCM.Text         :=  produtos1.CompNCM;
     txttamanho.Text        :=  produtos1.TAMANHO;
     txtDescricao.Text       :=  produtos1.DESCR_PRODUTO ;
     txtUnidMed.Text         :=  IntToStr(produtos1.UNID);
     TxtInteiro.Text         :=  produtos1.INTEIRO;
     Txt_Tipo_Item.Text      :=  IntToStr(produtos1.COD_TIPO_ITEM) ;
     TxtProd_Esp.Text        :=  IntToStr(produtos1.COD_PROD_ESP);
     TxtCodServ.Text         :=  IntToStr(produtos1.CODSERV);
     Txt_CST_origem.Text     :=  IntToStr(produtos1.ORIGEM);
     Txt_CST_Tributacao.Text :=  IntToStr(produtos1.CD_TRIBUTACAO);
     TXT_OPERACAO.Text       :=  produtos1.INTEIRO;
     TXTICMS.Text            :=  FloatToStr(produtos1.ALIQ_ICMS);
     TXTIPI.Text             :=  FloatToStr(produtos1.IPI);
     TXTREF.Text             :=  Produtos1.REF;
     TXTREDBASECALC.Text     :=  FloatToStr(produtos1.RED_BASE_CALC);
     TXTCONfins.Text         :=  FloatToStr(produtos1.ALIQ_CONFINS);
     TXTPIS.Text             :=  FloatToStr(produtos1.ALIQ_PIS);
     TXTMVA.Text             :=  FloatToStr(produtos1.MVA);
     TxtDerivacao.Text       :=  IntToStr(produtos1.DERIVACAO) ;
     TXTAQUIS.Text           :=  FloatToStr(produtos1.VLAQUIS) ;
     TXTMARGEM.Text          :=  FloatToStr(produtos1.MARGEMPR) ;
     TXTPRECOVENDA.Text      :=  FloatToStr(produtos1.PRECOVENDA) ;
     TXTESTOQUE.Text         :=  FloatToStr(produtos1.ESTOQUE) ;
     IF(PRODUTOS1.STATUS = 'A'  )THEN
       RadioButton1.Checked := TRUE
     ELSE
       RadioButton2.Checked := TRUE;

    // TXTSTATUS.Text


   end;
   function TFRMCADPROD.GravaProdutosCLasse():Boolean;
   begin

     produtos1.COD_PRODUTO     := StrToInt(ActResultEdit1.Text);
     produtos1.EMPRESA          := StrToInt(txtEMPRESA.Text);
     produtos1.FILIAL           := StrToInt(txtFILIAL.Text);
     produtos1.GRUPO           := StrToInt(txtgrupo.Text);
     produtos1.SUBGRUPO        := StrToInt(txtSub_Grupo.Text);
     produtos1.COD_NCM         := txtncm.Text;
     produtos1.CompNCM         := txtCompNCM.Text;
     produtos1.TAMANHO         := txtTAMANHO.Text;
     produtos1.DESCR_PRODUTO   := txtDescricao.Text;
     produtos1.UNID            := StrToInt(txtUnidMed.Text);
     produtos1.INTEIRO         := TxtInteiro.Text;
     produtos1.COD_TIPO_ITEM   := StrToInt(Txt_Tipo_Item.Text);
     produtos1.TIPO_ITEM       := Txt_Tipo_Item.Text;
     produtos1.COD_PROD_ESP    := StrToInt(TxtProd_Esp.Text);
     produtos1.CODSERV         := StrToInt(TxtCodServ.Text);
     produtos1.ORIGEM          := StrToInt(Txt_CST_origem.Text);
     produtos1.CD_TRIBUTACAO   := StrToInt(Txt_CST_Tributacao.Text);
     produtos1.INTEIRO         := TXT_OPERACAO.Text;
     produtos1.ALIQ_ICMS       := StrToFloat(TXTICMS.Text);
     produtos1.IPI             := StrToFloat(TXTIPI.Text);
     produtos1.REF             := TXTREF.Text;
     produtos1.RED_BASE_CALC   := StrToFloat(TXTREDBASECALC.Text);
     produtos1.ALIQ_CONFINS    := StrToFloat(TXTCONfins.Text);
     produtos1.ALIQ_PIS        := StrToFloat(TXTPIS.Text);
     produtos1.MVA             := StrToFloat(TXTMVA.Text);
     produtos1.DERIVACAO       := StrToInt(TxtDerivacao.Text);
     produtos1.VLAQUIS         := StrToFloat(TXTAQUIS.Text);
     produtos1.MARGEMPR        := StrToFloat(TXTMARGEM.Text);
     produtos1.PRECOVENDA      := StrToFloat(TXTPRECOVENDA.Text);
     produtos1.ESTOQUE         := StrToFloat(TXTESTOQUE.Text);
     IF(RadioButton1.Checked = TRUE )THEN
        produtos1.STATUS := 'A'
     ELSE
        produtos1.STATUS := 'I';
   end;
   function TFRMCADPROD.GravaProdutosSql():Boolean;
   begin

     produtos1 := TProduto.Create;
     produtos1.COD_PRODUTO     := IBQuery1.FieldByName('COD_PRODUTO').AsInteger;
     produtos1.EMPRESA         := IBQuery1.FieldByName('EMPRESA').AsInteger;
     produtos1.FILIAL          := IBQuery1.FieldByName('FILIAL').AsInteger;
     produtos1.GRUPO           := IBQuery1.FieldByName('GRUPO').AsInteger;
     produtos1.COD_TIPO_ITEM   := IBQuery1.FieldByName('Cod_tipo_item').AsInteger;
     produtos1.SUBGRUPO        := IBQuery1.FieldByName('SUBGRUPO').AsInteger;
     produtos1.COD_NCM         := IBQuery1.FieldByName('COD_NCM').AsString;
     produtos1.CompNCM         := IBQuery1.FieldByName('CompNCM').AsString;
     produtos1.TAMANHO         := IBQuery1.FieldByName('TAMANHO').AsString;
     produtos1.DESCR_PRODUTO   := IBQuery1.FieldByName('DESCR_PRODUTO').AsString;
     produtos1.UNID            := IBQuery1.FieldByName('UNID').AsInteger;
     produtos1.INTEIRO         := IBQuery1.FieldByName('INTEIRO').AsString;
     produtos1.TIPO_ITEM       := IBQuery1.FieldByName('TIPO_ITEM').AsString;
     produtos1.COD_PROD_ESP    := IBQuery1.FieldByName('COD_PROD_ESP').AsInteger;
     produtos1.CODSERV         := IBQuery1.FieldByName('CODSERV').AsInteger;
     produtos1.ORIGEM          := IBQuery1.FieldByName('ORIGEM').AsInteger;
     produtos1.CD_TRIBUTACAO   := IBQuery1.FieldByName('CD_TRIBUTACAO').AsInteger;
     produtos1.INTEIRO         := IBQuery1.FieldByName('INTEIRO').AsString;
     produtos1.ALIQ_ICMS       := IBQuery1.FieldByName('ALIQ_ICMS').AsFloat;
     produtos1.IPI             := IBQuery1.FieldByName('IPI').AsFloat;
     produtos1.REF             := IBQuery1.FieldByName('REF').AsString;
     produtos1.RED_BASE_CALC   := IBQuery1.FieldByName('RED_BASE_CALC').AsFloat;
     produtos1.ALIQ_CONFINS    := IBQuery1.FieldByName('ALIQ_CONFINS').AsFloat;
     produtos1.ALIQ_PIS        := IBQuery1.FieldByName('ALIQ_PIS').AsFloat;
     produtos1.MVA             := IBQuery1.FieldByName('MVA').AsFloat;
     produtos1.DERIVACAO       := IBQuery1.FieldByName('DERIVACAO').AsInteger;
     produtos1.STATUS          := IBQuery1.FieldByName('STATUS').AsString;
     produtos1.VLAQUIS         := IBQuery1.FieldByName('VLAQUIS').AsFloat;
     produtos1.MARGEMPR        := IBQuery1.FieldByName('MARGEMPR').AsFloat;
     produtos1.PRECOVENDA      := IBQuery1.FieldByName('PRECOVENDA').AsFloat;
     produtos1.ESTOQUE         := IBQuery1.FieldByName('ESTOQUE').AsFloat;
   end;
   function TFRMCADPROD.GravaProdutos():Boolean;
   begin
         {
     CREATE TABLE PROD_SERV (
    COD_PRODUTO    INTEGER NOT NULL,
    UNID           INTEGER,
    COD_TIPO_ITEM  INTEGER,
    COD_PROD_ESP   INTEGER,
    DESCR_PRODUTO  VARCHAR(120),
    COD_BARRA      VARCHAR(120),
    COD_ANT_ITEM   VARCHAR(120),
    TIPO_ITEM      VARCHAR(120),
    COD_NCM        VARCHAR(120),
    EX_IPI         VARCHAR(120),
    COD_GEN        VARCHAR(120),
    COD_LST        VARCHAR(120),
    ALIQ_ICMS      VARCHAR(120),
    MVA            INTEGER,
    GRUPO          INTEGER,
    SUBGRUPO       INTEGER,
    INTEIRO        INTEGER,
    CD_TRIBUTACAO  INTEGER,
    ORIGEM         INTEGER,
    OPERACAO       INTEGER,
    RED_BASE_CALC  INTEGER,
    ALIQ_CONFINS   INTEGER,
    ALIQ_PIS       INTEGER,
    VLAQUIS        DOUBLE PRECISION,
    MARGEMPR       DOUBLE PRECISION,
    ESTOQUE        INTEGER,
    PRECOVENDA     DOUBLE PRECISION,
    REF            VARCHAR(15),
    STATUS         VARCHAR(1),
    DERIVACAO      VARCHAR(100)
     }
   IBQuery1.Active := False;
   IBQuery1.SQL.Clear;
   IBQuery1.SQL.Add('insert into PROD_SERV(EMPRESA,FILIAL,UNID, COD_TIPO_ITEM, COD_PROD_ESP, DESCR_PRODUTO, TAMANHO, COD_ANT_ITEM,COD_TIPO_ITEM, TIPO_ITEM, COD_NCM, EX_IPI, COD_GEN, COD_LST, ALIQ_ICMS, MVA, GRUPO, SUBGRUPO, INTEIRO, CD_TRIBUTACAO, ORIGEM, OPERACAO, ');
   IBQuery1.SQL.Add(' RED_BASE_CALC,ALIQ_CONFINS,ALIQ_PIS, VLAQUIS, MARGEMPR, ESTOQUE, PRECOVENDA, REF, STATUS, DERIVACAO,COMPNCM, CODSERV, IPI)values(:EMPRESA, :FILIAL, :UNID, :COD_TIPO_ITEM, :COD_PROD_ESP, :DESCR_PRODUTO,:TAMANHO,:COD_ANT_ITEM,:COD_TIPO_ITEM,');
   IBQuery1.SQL.Add(' :TIPO_ITEM,:COD_NCM,:EX_IPI,:COD_GEN,:COD_LST,:ALIQ_ICMS,:MVA, :GRUPO, :SUBGRUPO, :INTEIRO, :CD_TRIBUTACAO, :ORIGEM, :OPERACAO, :RED_BASE_CALC, :ALIQ_CONFINS, :ALIQ_PIS, :VLAQUIS, :MARGEMPR, :ESTOQUE, :PRECOVENDA, :REF, :STATUS, :DERIVACAO, :COMPNCM');
   IBQuery1.SQL.Add(' , :CODSERV, :IPI)');
   //ShowMessage(IBQuery1.SQL.Text);
  // IBQuery1.ParamByName('COD_PRODUTO').AsInteger       := Produtos1.COD_PRODUTO ;
  IBQuery1.ParamByName('EMPRESA').AsInteger            := Produtos1.EMPRESA;
  IBQuery1.ParamByName('FILIAL').AsInteger             := Produtos1.FILIAL;
   IBQuery1.ParamByName('UNID').AsInteger              := Produtos1.UNID;
   IBQuery1.ParamByName('COD_TIPO_ITEM').AsInteger     := Produtos1.COD_TIPO_ITEM;
   IBQuery1.ParamByName('COD_PROD_ESP').AsInteger      := Produtos1.COD_PROD_ESP;
   IBQuery1.ParamByName('DESCR_PRODUTO').AsString      := Produtos1.DESCR_PRODUTO ;
   IBQuery1.ParamByName('TAMANHO').AsString          := Produtos1.TAMANHO ;
   IBQuery1.ParamByName('COD_ANT_ITEM').AsString       := Produtos1.COD_ANT_ITEM ;
   IBQuery1.ParamByName('COD_TIPO_ITEM').AsInteger     := Produtos1.COD_TIPO_ITEM ;
   IBQuery1.ParamByName('TIPO_ITEM').AsString          := Produtos1.TIPO_ITEM ;
   IBQuery1.ParamByName('COD_NCM').AsString            := Produtos1.COD_NCM ;
   IBQuery1.ParamByName('EX_IPI').AsString             := Produtos1.EX_IPI ;
   IBQuery1.ParamByName('COD_GEN').AsString            := Produtos1.COD_GEN ;
   IBQuery1.ParamByName('COD_LST').AsString            := Produtos1.COD_LST ;
   IBQuery1.ParamByName('ALIQ_ICMS').AsFloat           := Produtos1.ALIQ_ICMS ;
   IBQuery1.ParamByName('MVA').AsFloat                 := Produtos1.MVA ;
   IBQuery1.ParamByName('GRUPO').AsInteger             := Produtos1.GRUPO ;
   IBQuery1.ParamByName('SUBGRUPO').AsInteger          := Produtos1.SUBGRUPO ;
   IBQuery1.ParamByName('INTEIRO').AsString            := Produtos1.INTEIRO ;
   IBQuery1.ParamByName('CD_TRIBUTACAO').AsInteger     := Produtos1.CD_TRIBUTACAO ;
   IBQuery1.ParamByName('ORIGEM').AsInteger            := Produtos1.ORIGEM ;
   IBQuery1.ParamByName('OPERACAO').AsInteger          := Produtos1.OPERACAO ;
   IBQuery1.ParamByName('RED_BASE_CALC').AsFloat       := Produtos1.RED_BASE_CALC ;
   IBQuery1.ParamByName('ALIQ_CONFINS').AsFloat        := Produtos1.ALIQ_CONFINS ;
   IBQuery1.ParamByName('ALIQ_PIS').AsFloat            := Produtos1.ALIQ_PIS ;
   IBQuery1.ParamByName('VLAQUIS').AsFloat             := Produtos1.VLAQUIS ;
   IBQuery1.ParamByName('MARGEMPR').AsFloat            := Produtos1.MARGEMPR ;
   IBQuery1.ParamByName('ESTOQUE').AsFloat             := Produtos1.ESTOQUE ;
   IBQuery1.ParamByName('PRECOVENDA').AsFloat          := Produtos1.PRECOVENDA ;
   IBQuery1.ParamByName('REF').AsString                := Produtos1.REF ;
   IBQuery1.ParamByName('STATUS').AsString             := 'A' ;
   IBQuery1.ParamByName('DERIVACAO').AsInteger          := Produtos1.DERIVACAO ;
   IBQuery1.ParamByName('COMPNCM').AsString            := Produtos1.CompNCM ;
   IBQuery1.ParamByName('CODSERV').AsInteger           := Produtos1.CODSERV ;
  // IBQuery1.FieldByName('REF').AsString                := Produtos1.REF;
   IBQuery1.ParamByName('IPI').AsFloat                 := Produtos1.IPI ;
   IBQuery1.ParamByName('ref').AsString                := Produtos1.REF;
 //  IBQuery1.Close();
 //  IBQuery1.Open;
   IBQuery1.ExecSQL;
   IBQuery1.Transaction.Commit;
 //  IBQuery1.Close();
   end;

   function TFRMCADPROD.Auterar(): Boolean;
   begin
   IBQuery1.Active := False;
   IBQuery1.SQL.Clear;
    IBQuery1.SQL.Add('update PROD_SERV');
    IBQuery1.SQL.Add(' set ');
    IBQuery1.SQL.Add('EMPRESA = :EMPRESA,');
    IBQuery1.SQL.Add('FILIAL = :FILIAL,');
    IBQuery1.SQL.Add('UNID = :UNID,');
    IBQuery1.SQL.Add('COD_TIPO_ITEM = :COD_TIPO_ITEM,');
    IBQuery1.SQL.Add('COD_PROD_ESP = :COD_PROD_ESP,');
    IBQuery1.SQL.Add('DESCR_PRODUTO = :DESCR_PRODUTO,');
    IBQuery1.SQL.Add('TAMANHO = :TAMANHO,');
    IBQuery1.SQL.Add('COD_ANT_ITEM = :COD_ANT_ITEM,');
    IBQuery1.SQL.Add('COD_TIPO_ITEM = :COD_TIPO_ITEM,');
    IBQuery1.SQL.Add('TIPO_ITEM = :TIPO_ITEM,');
    IBQuery1.SQL.Add('COD_NCM = :COD_NCM,');
    IBQuery1.SQL.Add('EX_IPI = :EX_IPI,');
    IBQuery1.SQL.Add('COD_GEN = :COD_GEN,');
    IBQuery1.SQL.Add('COD_LST = :COD_LST,');
    IBQuery1.SQL.Add('ALIQ_ICMS = :ALIQ_ICMS,');
    IBQuery1.SQL.Add('MVA = :MVA,');
    IBQuery1.SQL.Add('GRUPO = :GRUPO,');
    IBQuery1.SQL.Add('SUBGRUPO = :SUBGRUPO,');
    IBQuery1.SQL.Add('INTEIRO = :INTEIRO,');
    IBQuery1.SQL.Add('CD_TRIBUTACAO = :CD_TRIBUTACAO, ');
    IBQuery1.SQL.Add('ORIGEM = :ORIGEM,');
    IBQuery1.SQL.Add('OPERACAO = :OPERACAO,');
    IBQuery1.SQL.Add('RED_BASE_CALC = :RED_BASE_CALC,');
    IBQuery1.SQL.Add('ALIQ_CONFINS = :ALIQ_CONFINS,');
    IBQuery1.SQL.Add('ALIQ_PIS = :ALIQ_PIS,');
    IBQuery1.SQL.Add('VLAQUIS = :VLAQUIS,');
    IBQuery1.SQL.Add('MARGEMPR = :MARGEMPR,');
    IBQuery1.SQL.Add('ESTOQUE = :ESTOQUE,');
    IBQuery1.SQL.Add(' PRECOVENDA = :PRECOVENDA,');
    IBQuery1.SQL.Add('REF = :REF,');
    IBQuery1.SQL.Add('STATUS = :STATUS,');
    IBQuery1.SQL.Add('DERIVACAO = :DERIVACAO,');
    IBQuery1.SQL.Add('COMPNCM = :COMPNCM,');
    IBQuery1.SQL.Add('CODSERV = :CODSERV,');
    IBQuery1.SQL.Add('IPI = :IPI');
    IBQuery1.SQL.Add('where ');
    IBQuery1.SQL.Add('COD_PRODUTO = :OLD_COD_PRODUTO');
   IBQuery1.ParamByName('OLD_COD_PRODUTO').AsInteger       := Produtos1.COD_PRODUTO;
   IBQuery1.ParamByName('EMPRESA').AsInteger              := Produtos1.EMPRESA;
   IBQuery1.ParamByName('FILIAL').AsInteger              := Produtos1.FILIAL;
   IBQuery1.ParamByName('UNID').AsInteger              := Produtos1.UNID;
   IBQuery1.ParamByName('COD_TIPO_ITEM').AsInteger     := Produtos1.COD_TIPO_ITEM;
   IBQuery1.ParamByName('COD_PROD_ESP').AsInteger      := Produtos1.COD_PROD_ESP;
   IBQuery1.ParamByName('DESCR_PRODUTO').AsString      := Produtos1.DESCR_PRODUTO ;
   IBQuery1.ParamByName('TAMANHO').AsString          := Produtos1.TAMANHO ;
   IBQuery1.ParamByName('COD_ANT_ITEM').AsString       := Produtos1.COD_ANT_ITEM ;
   IBQuery1.ParamByName('COD_TIPO_ITEM').AsInteger     := Produtos1.COD_TIPO_ITEM ;
   IBQuery1.ParamByName('TIPO_ITEM').AsString          := Produtos1.TIPO_ITEM ;
   IBQuery1.ParamByName('COD_NCM').AsString            := Produtos1.COD_NCM ;
   IBQuery1.ParamByName('EX_IPI').AsString             := Produtos1.EX_IPI ;
   IBQuery1.ParamByName('COD_GEN').AsString            := Produtos1.COD_GEN ;
   IBQuery1.ParamByName('COD_LST').AsString            := Produtos1.COD_LST ;
   IBQuery1.ParamByName('ALIQ_ICMS').AsFloat           := Produtos1.ALIQ_ICMS ;
   IBQuery1.ParamByName('MVA').AsFloat                 := Produtos1.MVA ;
   IBQuery1.ParamByName('GRUPO').AsInteger             := Produtos1.GRUPO ;
   IBQuery1.ParamByName('SUBGRUPO').AsInteger          := Produtos1.SUBGRUPO ;
   IBQuery1.ParamByName('INTEIRO').AsString            := Produtos1.INTEIRO ;
   IBQuery1.ParamByName('CD_TRIBUTACAO').AsInteger     := Produtos1.CD_TRIBUTACAO ;
   IBQuery1.ParamByName('ORIGEM').AsInteger            := Produtos1.ORIGEM ;
   IBQuery1.ParamByName('OPERACAO').AsInteger          := Produtos1.OPERACAO ;
   IBQuery1.ParamByName('RED_BASE_CALC').AsFloat       := Produtos1.RED_BASE_CALC ;
   IBQuery1.ParamByName('ALIQ_CONFINS').AsFloat        := Produtos1.ALIQ_CONFINS ;
   IBQuery1.ParamByName('ALIQ_PIS').AsFloat            := Produtos1.ALIQ_PIS ;
   IBQuery1.ParamByName('VLAQUIS').AsFloat             := Produtos1.VLAQUIS ;
   IBQuery1.ParamByName('MARGEMPR').AsFloat            := Produtos1.MARGEMPR ;
   IBQuery1.ParamByName('ESTOQUE').AsFloat             := Produtos1.ESTOQUE ;
   IBQuery1.ParamByName('PRECOVENDA').AsFloat          := Produtos1.PRECOVENDA ;
   IBQuery1.ParamByName('REF').AsString                := Produtos1.REF ;
   IBQuery1.ParamByName('STATUS').AsString             := Produtos1.STATUS ;
   IBQuery1.ParamByName('DERIVACAO').AsInteger          := Produtos1.DERIVACAO ;
   IBQuery1.ParamByName('COMPNCM').AsString            := Produtos1.CompNCM ;
   IBQuery1.ParamByName('CODSERV').AsInteger           := Produtos1.CODSERV ;
   IBQuery1.ParamByName('ref').AssTRING                := Produtos1.REF ;
   IBQuery1.ParamByName('IPI').AsFloat                 := Produtos1.IPI ;
   IBQuery1.ExecSQL;
   IBQuery1.Close();
   IBQuery1.Open;

   IBQuery1.Transaction.Commit;
   IBQuery1.Close();
   end;
   Function TFRMCADPROD.Deletar(): Boolean;
   begin
   IBQuery1.Active := False;
   IBQuery1.SQL.Clear;
   IBQuery1.SQL.Add('Delete from PROD_SERV where cod_produto = :Cod_Produto ' );
   IBQuery1.ParamByName('COD_PRODUTO').AsInteger := StrToInt(ActResultEdit1.Text);
   IBQuery1.ExecSQL;
   IBQuery1.Close();
   IBQuery1.Open;
   IBQuery1.Transaction.Commit;
   IBQuery1.Close();
   end;
   function TFRMCADPROD.limpar():boolean;
   begin
   IBQuery5.SQL.Clear;
   IBQuery5.Active := False;
   IBQuery5.SQL.Add('select max (p.cod_produto) +1 as soma from prod_serv p');
   IBQuery5.Active := True;

     ActResultEdit1.Text     :=  IntToStr(IBQuery5.fieldByname('Soma').AsInteger);
     txtgrupo.Text           :=  '';
     txtEMPRESA.Text         :=  '1';
     txtFILIAL.Text          :=  '1';
     txtSub_Grupo.Text       :=  '';
     txtncm.Text             :=  '';
     txtCompNCM.Text         :=  '';
     txttamanho.Text         :=   '';
     txtDescricao.Text       :=  '';
     txtUnidMed.Text         :=  '1';
     TxtInteiro.Text         :=  '1';
     Txt_Tipo_Item.Text      :=  '1';
     TxtProd_Esp.Text        :=  '1';
     TxtCodServ.Text         :=  '1';
     Txt_CST_origem.Text     :=  '';
     Txt_CST_Tributacao.Text :=  '';
     TXT_OPERACAO.Text       :=  '';
     TXTICMS.Text            :=  '';
     TXTIPI.Text             :=  '';
     TXTREF.Text             :=  '';
     TXTREDBASECALC.Text     :=  '';
     TXTCONfins.Text         :=  '';
     TXTPIS.Text             :=  '';
     TXTMVA.Text             :=  '';
     TxtDerivacao.Text       :=  '';
     TXTAQUIS.Text           :=  '';
     TXTMARGEM.Text          :=  '';
     TXTPRECOVENDA.Text      :=  '';
     TXTESTOQUE.Text         :=  '0';
     RadioButton1.Checked := TRUE
   end;


procedure TFRMCADPROD.FormCreate(Sender: TObject);
begin
  SelectInicial();
  GravaProdutosSql();
  GravaProdutosEdit()

end;

procedure TFRMCADPROD.SpeedButton11Click(Sender: TObject);
begin

  Primeiro();
  GravaProdutosSql();
  GravaProdutosEdit()

end;

procedure TFRMCADPROD.SpeedButton10Click(Sender: TObject);
begin
  Proximo();
  GravaProdutosSql();
  GravaProdutosEdit()

end;

procedure TFRMCADPROD.SpeedButton3Click(Sender: TObject);
begin


  produtos1 := TProduto.Create;
  GravaProdutosCLasse();
  if (GravaProdutos())then
  ShowMessage('Produto INSERIDO com SUCESSO !!')
  else
  ShowMessage('ERRO ao inserir Produto!!');
  SelectInicial();
  GravaProdutosSql();
  GravaProdutosEdit();
  ultimo();
end;

procedure TFRMCADPROD.SpeedButton4Click(Sender: TObject);
begin
  inherited;
    produtos1 := TProduto.Create;
  GravaProdutosCLasse();
 if(Auterar) then
   ShowMEssage('Produto alterado com sucesso !!')
 else
   ShowMEssage(' ERRO ao alterar Produto');
  SelectInicial();
  GravaProdutosSql();
  GravaProdutosEdit()
end;

procedure TFRMCADPROD.SpeedButton5Click(Sender: TObject);
begin
  inherited;
  if MessageDlg('Tem Certeza????', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
      Deletar;
  SelectInicial();
  GravaProdutosSql();
  GravaProdutosEdit()
end;

procedure TFRMCADPROD.txtempresaChange(Sender: TObject);
begin
  if (txtempresa.Text <> '')then
  begin
  QryEmpresa.Active := False;
  QryEmpresa.ParamByName('Cod_Empresa').AsInteger := StrToInt(txtempresa.Text);
  QryEmpresa.Active := True;
  if(not QryEmpresa.Eof)then
     Label4.Caption := QryEmpresa.fieldbyName('NOME').AsString;
  end;
end;

procedure TFRMCADPROD.txtfilialChange(Sender: TObject);
begin
  if (txtfilial.Text <> '')then
  begin
  Qryfilial.Active := False;
  Qryfilial.ParamByName('CdFilial').AsInteger := StrToInt(txtFilial.Text);
  Qryfilial.ParamByName('Cod_Empresa').AsInteger := StrToInt(txtempresa.Text);
  Qryfilial.Active := True;
  if(not Qryfilial.Eof)then
     Label5.Caption := Qryfilial.fieldbyName('NOME').AsString;
  end;


end;

procedure TFRMCADPROD.txtUnidMedChange(Sender: TObject);
begin
    if (txtUnidMed.Text <> '')then
  begin
  Qryunimed.Active := False;
  Qryunimed.ParamByName('unid').AsInteger := StrToInt(txtUnidMed.Text);
  Qryunimed.Active := True;
  if(not Qryunimed.Eof)then
     Label1.Caption := Qryunimed.fieldbyName('SIGLA').AsString;
  end;

end;

procedure TFRMCADPROD.Txt_Tipo_ItemChange(Sender: TObject);
begin
    if (Txt_Tipo_Item.Text <> '')then
  begin
  QryTipoItem.Active := False;
  QryTipoItem.ParamByName('COD_TIPO_ITEM').AsString := Txt_Tipo_Item.Text;
  QryTipoItem.Active := True;
  if(not QryTipoItem.Eof)then
     Label2.Caption := QryTipoItem.fieldbyName('DESCRICAO').AsString
  else
     Label2.Caption := 'TIPO PRODUTO INEXISTENTE'
  end;
end;

procedure TFRMCADPROD.TxtProd_EspChange(Sender: TObject);
begin  // select * from PROD_ESPESIFICO where COD_PROD_ESP = :COD_PROD_ESP
  if (TxtProd_Esp.Text <> '')then
  begin
  QryProdSpe.Active := False;
  QryProdSpe.ParamByName('COD_PROD_ESP').AsInteger := StrToInt(txtProd_Esp.Text);
  QryProdSpe.Active := True;
  if(not QryProdSpe.Eof)then
     Label3.Caption := QryProdSpe.fieldbyName('DESCRICAO').AsString
  else
     Label3.Caption := 'PRODUTO ESPECIFICO INEXISTENTE'
  end;

end;

procedure TFRMCADPROD.TxtInteiroChange(Sender: TObject);
begin
   if (TxtInteiro.Text <> '')then
  begin

  if(TxtInteiro.Text = '1' )then
     Label6.Caption := 'SIM'
  else
     Label3.Caption := 'NÃO';
  end;

end;

procedure TFRMCADPROD.txtUnidMedButtonClick(Sender: TObject);
begin
  Form9.Show;

end;

procedure TFRMCADPROD.txtempresaButtonClick(Sender: TObject);
begin
  inherited;
Form11.InsertCodRet(1);
Form11.Show;
end;

procedure TFRMCADPROD.txtfilialButtonClick(Sender: TObject);
begin
  inherited;
Form12.InsertCodRet(1);
Form12.Show;
end;

procedure TFRMCADPROD.Txt_Tipo_ItemButtonClick(Sender: TObject);
begin
  inherited;
 Form13.Show;
end;

procedure TFRMCADPROD.TxtProd_EspButtonClick(Sender: TObject);
begin
  inherited;
  Form14.Show;
end;

procedure TFRMCADPROD.ActResultEdit1ButtonClick(Sender: TObject);
begin
  inherited;
Form15.InsertCodRet(1);
Form15.Show;
end;

procedure TFRMCADPROD.cod(Sender: TObject);
begin
  inherited;
    if (ActResultEdit1.Text <> '')then
  begin
  QryProdutos.Active := False;
  QryProdutos.ParamByName('Cod_Produto').AsInteger := StrToInt(ActResultEdit1.Text);
  QryProdutos.Active := True;
  if(not QryProdutos.Eof)then
     Label7.Caption := ''+IntToStr(QryProdutos.fieldbyName('derivacao').AsInteger)+'  -  '+QryProdutos.fieldbyName('DESCR_PRODUTO').AsString
  else
     Label7.Caption := ' ';
     IBQuery4.Active := False;
     IBQuery4.SQL.Text := 'select *from cod_barra where cod_produto ='+ActResultEdit1.Text;
     IBQuery4.Active := true;
  end;
end;

procedure TFRMCADPROD.SpeedButton12Click(Sender: TObject);
begin
  inherited;
IBQuery1.Active := False;
IBQuery1.SQL.Clear;
IBQuery1.SQL.Text := 'select *  from PROD_SERV   where Cod_PRoduto = :Cod_PRoduto';
IBQuery1.ParamByName('Cod_PRoduto').AsInteger := StrToInt(ActResultEdit1.Text);
IBQuery1.Active := True;
  GravaProdutosSql();
  GravaProdutosEdit()
end;

procedure TFRMCADPROD.SpeedButton1Click(Sender: TObject);
begin
  inherited;
  Anterior();
  GravaProdutosSql();
  GravaProdutosEdit()
end;

procedure TFRMCADPROD.SpeedButton2Click(Sender: TObject);
begin
  inherited;
   Ultimo();
  GravaProdutosSql();
  GravaProdutosEdit()
end;

procedure TFRMCADPROD.SpeedButton9Click(Sender: TObject);
begin
  inherited;
Close;
end;

procedure TFRMCADPROD.TxtDerivacaoButtonClick(Sender: TObject);
begin
  inherited;
FConsMarca.Show;
end;

procedure TFRMCADPROD.TXTGRUPOExit(Sender: TObject);
begin
  inherited;
FrmConsSub_Grupo.IBQuery1.Active := False;
FrmConsSub_Grupo.IBQuery1.ParamByName('grupo').AsInteger := StrToInt(TXTGRUPO.Text);
FrmConsSub_Grupo.IBQuery1.Active := True;
end;

procedure TFRMCADPROD.TXTGRUPOChange(Sender: TObject);
begin
  if (TXTGRUPO.Text <> '')then
  begin
  IBQuery2.Active := False;
  IBQuery2.ParamByName('grupo').AsInteger := StrToInt(TXTGRUPO.Text);
  IBQuery2.Active := True;
  if(not IBQuery2.Eof)then
     Label9.Caption := IBQuery2.fieldbyName('dsgrupo').AsString;
  end;

end;

procedure TFRMCADPROD.txtSub_GrupoChange(Sender: TObject);
begin     
    if (txtSub_Grupo.Text <> '')then
  begin
  IBQuery3.Active := False;
  IBQuery3.ParamByName('Sub_grupo').AsInteger := StrToInt(txtSub_Grupo.Text);
  IBQuery3.Active := True;
  if(not IBQuery3.Eof)then
     Label10.Caption := IBQuery3.fieldbyName('dsSub_grupo').AsString;
  end;

end;

procedure TFRMCADPROD.TXTGRUPOButtonClick(Sender: TObject);
begin
 FrmConsGrupo.Show;

end;

procedure TFRMCADPROD.txtSub_GrupoButtonClick(Sender: TObject);
begin
 FrmConsSub_Grupo.Show;

end;

procedure TFRMCADPROD.DBGrid1KeyPress(Sender: TObject; var Key: Char);
begin
    if(dbGrid1.Fields[0].IsNull )THEN
  begin
   // IBQuery6.Active := False;
   // IBQuery6.SQL.Text := 'SELECT MAX(cod_MARCA) FROM MARCA ';
  //  IBQuery6.Active := True;
  //  dbGrid1.Fields[0].AsInteger := IBQuery6.FieldByName('max').asInteger + 1;
    dbGrid1.Fields[1].AsInteger := StrToInt(ActResultEdit1.Text);

end;
 end;
procedure TFRMCADPROD.SpeedButton15Click(Sender: TObject);
begin
 IBQuery4.Post;
 IBQuery4.Transaction.Commit;
      IBQuery4.Active := False;
     IBQuery4.SQL.Text := 'select *from cod_barra where cod_produto ='+ActResultEdit1.Text;
     IBQuery4.Active := true;
end;

procedure TFRMCADPROD.SpeedButton16Click(Sender: TObject);
begin
   //   if MessageDlg('Tem Certeza????', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
   //               FDataSource.DataSet.Delete;
end;

procedure TFRMCADPROD.SpeedButton14Click(Sender: TObject);
begin
  inherited;
IBQuery4.Close;
IBQuery4.Open;
IBQuery4.Edit;
end;

procedure TFRMCADPROD.SpeedButton13Click(Sender: TObject);
begin
IBQuery4.Close;
IBQuery4.Open;
IBQuery4.Insert;

end;

procedure TFRMCADPROD.ActResultEdit1Change(Sender: TObject);
begin
  inherited;
    if (ActResultEdit1.Text <> '')then
  begin
  QryProdutos.Active := False;
  QryProdutos.ParamByName('Cod_Produto').AsInteger := StrToInt(ActResultEdit1.Text);
  QryProdutos.Active := True;
  if(not QryProdutos.Eof)then
     Label7.Caption := ''+IntToStr(QryProdutos.fieldbyName('derivacao').AsInteger)+'  -  '+QryProdutos.fieldbyName('DESCR_PRODUTO').AsString
  else
     Label7.Caption := ' ';
     IBQuery4.Active := False;
     IBQuery4.SQL.Text := 'select *from cod_barra where cod_produto ='+ActResultEdit1.Text;
     IBQuery4.Active := true;

end;
  end;
procedure TFRMCADPROD.SpeedButton6Click(Sender: TObject);
begin
  inherited;
LIMPAR();
end;

procedure TFRMCADPROD.TXTMARGEMExit(Sender: TObject);
var aquis,margem,precovenda :Double;
begin
  inherited;

aquis := StrToFloat(TXTAQUIS.Text);
margem := StrToFloat(TXTMARGEM.Text);
precovenda := ((aquis*margem)/100)+ aquis;
TXTPRECOVENDA.Text  := FloatToStr(precovenda);
end;

end.
