unit Unit60;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Unit1, DB, DBTables, Buttons, DBCtrls, StdCtrls, Grids, DBGrids,
  Mask, ComCtrls, U_DBBButton, ExtCtrls, IBCustomDataSet, IBUpdateSQL,
  IBQuery;

type
  TFRMNFENTRADAS = class(TForm1)
    PageControl1: TPageControl;
    TabSheet1: TTabSheet;
    TabSheet2: TTabSheet;
    Label1: TLabel;
    DBEdit1: TDBEdit;
    DataSource1: TDataSource;
    Label2: TLabel;
    DBEdit2: TDBEdit;
    Label3: TLabel;
    DBEdit3: TDBEdit;
    Label4: TLabel;
    Label5: TLabel;
    DBEdit5: TDBEdit;
    Label6: TLabel;
    DBEdit6: TDBEdit;
    Label7: TLabel;
    DBEdit7: TDBEdit;
    Label8: TLabel;
    DBEdit8: TDBEdit;
    Label9: TLabel;
    DBEdit9: TDBEdit;
    Label10: TLabel;
    DBEdit10: TDBEdit;
    Label11: TLabel;
    DBEdit11: TDBEdit;
    Label12: TLabel;
    DBEdit12: TDBEdit;
    Label14: TLabel;
    DBEdit14: TDBEdit;
    Label15: TLabel;
    DBEdit15: TDBEdit;
    Label16: TLabel;
    DBEdit16: TDBEdit;
    GroupBox1: TGroupBox;
    Panel4: TPanel;
    DBGrid1: TDBGrid;
    Edit1: TEdit;
    DBText1: TDBText;
    Edit2: TEdit;
    Edit3: TEdit;
    DBText2: TDBText;
    Panel5: TPanel;
    Label17: TLabel;
    Label18: TLabel;
    Label19: TLabel;
    Label20: TLabel;
    Label21: TLabel;
    Label22: TLabel;
    Label24: TLabel;
    Label25: TLabel;
    Label26: TLabel;
    DBGrid2: TDBGrid;
    Query1: TQuery;
    Query2: TQuery;
    DataSource2: TDataSource;
    DataSource3: TDataSource;
    Query3: TQuery;
    BitBtn1: TBitBtn;
    Query4: TQuery;
    Edit5: TEdit;
    Edit6: TEdit;
    Edit7: TEdit;
    Edit8: TEdit;
    Edit9: TEdit;
    Edit10: TEdit;
    Edit11: TEdit;
    Edit12: TEdit;
    Edit13: TEdit;
    Query5: TQuery;
    DataSource4: TDataSource;
    Query6: TQuery;
    DBEdit4: TDBEdit;
    Label23: TLabel;
    Label27: TLabel;
    Label28: TLabel;
    Label29: TLabel;
    Label30: TLabel;
    DBText3: TDBText;
    DBText4: TDBText;
    DataSource5: TDataSource;
    DataSource6: TDataSource;
    Query7: TQuery;
    Query8: TQuery;
    Edit14: TEdit;
    Label31: TLabel;
    Label32: TLabel;
    Edit15: TEdit;
    Label33: TLabel;
    Edit16: TEdit;
    Label34: TLabel;
    Query9: TQuery;
    SpeedButton2: TSpeedButton;
    SpeedButton3: TSpeedButton;
    DBButton7: TDBButton;
    Label13: TLabel;
    Button1: TButton;
    IBQuery1: TIBQuery;
    IBUpdateSQL1: TIBUpdateSQL;
    IBQuery2: TIBQuery;
    IBUpdateSQL2: TIBUpdateSQL;
    Table1: TTable;
    Table2: TTable;
    Table1Dcnumero: TFloatField;
    Table1Dcserie: TStringField;
    Table1Dcordem: TStringField;
    Table1Dctipo: TStringField;
    Table1Vlnota: TCurrencyField;
    Table1Vlicms: TCurrencyField;
    Table1Vlipi: TCurrencyField;
    Table1Vlfrete: TCurrencyField;
    Table1Cdfornecedor: TFloatField;
    Table1Cfop: TFloatField;
    Table1Dtentrada: TDateField;
    Table1Dtemissao: TDateField;
    Table1Transportado: TFloatField;
    Table1Cdpedido: TFloatField;
    Table1Natureza: TFloatField;
    Table1Idnfentradas: TIntegerField;
    Table1Vlst: TCurrencyField;
    Table1Tpsituacao: TStringField;
    Table2Dcnumero: TFloatField;
    Table2Dcserie: TStringField;
    Table2Dcordem: TStringField;
    Table2Dctipo: TStringField;
    Table2Cdfornecedor: TFloatField;
    Table2Cdproduto: TFloatField;
    Table2Quantidade: TFloatField;
    Table2Ipi: TCurrencyField;
    Table2St: TCurrencyField;
    Table2Icms: TCurrencyField;
    Table2Vlunitario: TCurrencyField;
    Table2Vldesconto: TCurrencyField;
    IBQuery3: TIBQuery;
    IBUpdateSQL3: TIBUpdateSQL;
    Table3: TTable;
    Table3Dcnumero: TFloatField;
    Table3Dcserie: TStringField;
    Table3Dcordem: TStringField;
    Table3Dctipo: TStringField;
    Table3Parcela: TFloatField;
    Table3Dtvencimento: TStringField;
    Table3Dtlancamento: TStringField;
    Table3Status: TStringField;
    Table3Tpsituacao: TStringField;
    Table3Vlparcela: TFloatField;
    Table3Fornecedor: TFloatField;
    Table3Obs: TStringField;
    Table3DATAPAGAMENTO: TDateField;
    procedure Edit1Exit(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure DBEdit1Exit(Sender: TObject);
    procedure DBEdit2Exit(Sender: TObject);
    procedure DBEdit3Exit(Sender: TObject);
    procedure DBEdit9Exit(Sender: TObject);
    procedure BitBtn1Click(Sender: TObject);
    procedure Query4AfterPost(DataSet: TDataSet);
    procedure Query4BeforePost(DataSet: TDataSet);
    procedure DBGrid1DblClick(Sender: TObject);
    procedure DBEdit4Change(Sender: TObject);
    procedure DBGrid2DblClick(Sender: TObject);
    procedure DBEdit15Exit(Sender: TObject);
    procedure Edit1KeyDown(Sender: TObject; var Key: Word;
      Shift: TShiftState);
    procedure DBEdit9KeyDown(Sender: TObject; var Key: Word;
      Shift: TShiftState);
    procedure DBEdit15KeyDown(Sender: TObject; var Key: Word;
      Shift: TShiftState);
    procedure Edit14Exit(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure Edit3Exit(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FRMNFENTRADAS: TFRMNFENTRADAS;

implementation

uses Unit2, Unit67, Unit68, Unit72, Unit53;

{$R *.dfm}

procedure TFRMNFENTRADAS.Edit1Exit(Sender: TObject);
begin
  inherited;
Query1.Active := False;
Query1.SQL.Clear;

Query1.SQL.Text :=  'select *from PRODUTOS where codpro = '+Edit1.Text;
Query1.Active := true;
If Query1.IsEmpty Then
  begin
   ShowMessage('produto inexistente');
   Edit1.Clear;
   Edit1.SetFocus;
  end
 else
 begin
   Query1.FieldByName('AQUIS').AsFloat;
   Edit3.Text := FloatToStr(Query1.FieldByName('AQUIS').AsFloat);
   Edit14.Text := FloatToStr(Query1.FieldByName('valor').AsFloat);
   Edit15.Text := FloatToStr(Query1.FieldByName('CUSTO').AsFloat);
   Edit16.Text := FloatToStr(Query1.FieldByName('precovenda').AsFloat);
 end;
end;

procedure TFRMNFENTRADAS.SpeedButton1Click(Sender: TObject);
begin
close();
end;

procedure TFRMNFENTRADAS.DBEdit1Exit(Sender: TObject);
begin
  inherited;
edit5.Text := DBEdit1.Text;
end;

procedure TFRMNFENTRADAS.DBEdit2Exit(Sender: TObject);
begin
  inherited;
edit6.Text := DBEdit2.Text;
end;

procedure TFRMNFENTRADAS.DBEdit3Exit(Sender: TObject);
begin
  inherited;
edit7.Text := DBEdit3.Text;
end;

procedure TFRMNFENTRADAS.DBEdit9Exit(Sender: TObject);
begin
  inherited;
  Edit12.Text := DBEdit9.Text;
  Query7.Active := false;
    if(DBEdit9.Text <> '' )THEN
    BEGIN
      Query7.SQL.Text := 'select *from cliente where codcli = '+DBEdit9.Text;
      Query7.Active := True;
      If Query7.IsEmpty Then
       begin
         ShowMessage('FORNECEDOR NÃO CADASTRADO');
         DBEdit9.Clear;
         DBEdit9.SetFocus;
       end;
    end;

end;

procedure TFRMNFENTRADAS.BitBtn1Click(Sender: TObject);
begin
  inherited;
Query4.SQL.Text := 'insert into titulospagar2 (dcnumero,dcserie,dcordem,dctipo,parcela,dtvencimento,status,dtlancamento,tpsituacao,vlparcela,fornecedor,obs)values( '+edit5.text+' ,'''+edit6.text+''','''+edit7.text+''' ,''' +edit8.text+''','''+edit9.Text+ ''','''+edit10.Text+''',''A'',''' +edit5.Text+''',''A'','+DataModule2.ajustaVirgula(Edit11.text)+ ','''+Edit12.text+''','''+Edit13.text+''' )';
//ShowMessage(Query4.SQL.Text);
Query4.ExecSQL;
Query4.Active := False;
Query5.Active := False;
Query5.SQL.Text := 'select *from titulospagar2 where dcnumero = '+edit5.Text+' and dcserie = '''+edit6.Text+''' and dcordem = '''+edit7.Text+''' and dctipo = '''+edit8.Text+'''';
//showmessage(Query5.SQL.Text);
Query5.Active := True;

end;

procedure TFRMNFENTRADAS.Query4AfterPost(DataSet: TDataSet);
begin
  inherited;
ShowMessage('Titulo gravado com sucesso !!!!');
end;

procedure TFRMNFENTRADAS.Query4BeforePost(DataSet: TDataSet);
begin
  inherited;
ShowMessage('Titulo gravado com sucesso !!!!');
end;

procedure TFRMNFENTRADAS.DBGrid1DblClick(Sender: TObject);
var
  dcnumero,cdproduto,quantidade : Integer;
  dcserie,dctipo,dcordem : string;
    datamodule : TDataModule2;
begin
  inherited;
//ShowMessage(IntToStr(DBGrid1.SelectedField.AsInteger));
dcnumero  :=  DBGrid1.Fields[0].AsInteger;
dcserie   := DBGrid1.Fields[1].AsString;
dcordem   := DBGrid1.Fields[2].AsString;
dctipo    := DBGrid1.Fields[3].AsString;
cdproduto := DBGrid1.Fields[4].AsInteger;
quantidade := DBGrid1.Fields[7].AsInteger;
Query6.SQL.Text := 'delete from nfentradasitens where dcnumero = '+IntToStr(dcnumero)+' and dcserie = '''+dcserie+''' and dcordem = '''+dcordem+''' and dctipo = '''+dctipo+''' and cdproduto = '+ IntToStr(cdproduto)+'   ';
Query6.ExecSQL;
 datamodule.baixarestoque(cdproduto,'03',quantidade);
Query3.Active := false;
Query3.Active := True;
end;

procedure TFRMNFENTRADAS.DBEdit4Change(Sender: TObject);
begin
  inherited;
   edit8.Text := DBEdit4.Text;
   edit5.Text := DBEdit1.Text;
   if(DBEdit1.Text <> '')then
     begin
       if(DBEdit2.Field.asString <> '' )then
       begin
         if(DBEdit3.Field.asString <> '')then
          begin
            if(DBEdit4.Field.asString <> '')then
            begin
                // Query3.SQL.Text := 'select *from nfentradasitens n where dcnumero = '+ IntToStr(DBEdit1.Field.asInteger)+' and  dcserie = '''+DBEdit2.Field.AsString +''' and dcordem = '''+DBEdit3.Field.AsString+''' and dctipo = ''' +DBEdit4.Field.AsString+ ''' ';
                Query3.SQL.Text := 'select n.dcnumero,n.dcserie,n.dcordem,n.dctipo,n.cdproduto,p.produto,n.vlunitario,n.quantidade,sum(n.vlunitario*n.quantidade) as valor_total from produtos p, nfentradasitens n where n.cdproduto = p.codpro and dcnumero = '+ IntToStr(DBEdit1.Field.asInteger)+' and  dcserie = '''+DBEdit2.Field.AsString +''' and dcordem = '''+DBEdit3.Field.AsString+''' and dctipo LIKE '''+DBEdit4.Field.AsString+'''group by n.dcnumero,n.dcserie,n.dcordem,n.dctipo,n.cdproduto,p.produto,n.vlunitario,n.quantidade ' ;

                 Query3.Active := True;
                 Query5.SQL.Text := 'select *from titulospagar2 where     dcnumero = '+ IntToStr(DBEdit1.Field.asInteger)+' and  dcserie = '''+DBEdit2.Field.AsString +''' and dcordem = '''+DBEdit3.Field.AsString+''' and dctipo LIKE '''+DBEdit4.Field.AsString+''' ';
             //    showmessage(Query5.SQL.Text);
                 Query5.Active := True;
            end;
          end;
       end;
     end;



end;

procedure TFRMNFENTRADAS.DBGrid2DblClick(Sender: TObject);

  var
  dcnumero,cdproduto,parcela : Integer;
  dcserie,dctipo,dcordem : string;
    datamodule : TDataModule2;
begin
  inherited;
//ShowMessage(IntToStr(DBGrid1.SelectedField.AsInteger));
dcnumero  :=  DBGrid2.Fields[0].AsInteger;
dcserie   := DBGrid2.Fields[1].AsString;
dcordem   := DBGrid2.Fields[2].AsString;
dctipo    := DBGrid2.Fields[3].AsString;
parcela  :=  DBGrid2.Fields[4].AsInteger;
Query6.SQL.Text := 'delete from titulospagar2 where dcnumero = '+IntToStr(dcnumero)+' and dcserie = '''+dcserie+''' and dcordem = '''+dcordem+''' and dctipo = '''+dctipo+'''and parcela = '+IntToStr(parcela)+' ';
Query6.ExecSQL;
Query5.Active := false;
Query5.Active := True;
end;

procedure TFRMNFENTRADAS.DBEdit15Exit(Sender: TObject);
begin
  inherited;
  Query8.Active := false;
  if(DBEdit15.Text <> '' )THEN
  BEGIN
  Query8.SQL.Text := 'select *from natureza where natureza = '+DBEdit15.Text;
  Query8.Active := True;
  If Query8.IsEmpty Then
  begin
   ShowMessage('NATUREZA NÃO CADASTRADA');
   DBEdit15.Clear;
   DBEdit15.SetFocus;
  end;
  end;
end;

procedure TFRMNFENTRADAS.Edit1KeyDown(Sender: TObject; var Key: Word;
  Shift: TShiftState);
begin
  inherited;
If Key = 112 then
 form67.Show;
end;

procedure TFRMNFENTRADAS.DBEdit9KeyDown(Sender: TObject; var Key: Word;
  Shift: TShiftState);
begin
  inherited;
If Key = 112 then
 form68.Show;
end;

procedure TFRMNFENTRADAS.DBEdit15KeyDown(Sender: TObject; var Key: Word;
  Shift: TShiftState);
begin
  inherited;
  If Key = 112 then
 form72.Show;
end;

procedure TFRMNFENTRADAS.Edit14Exit(Sender: TObject);

var
   por,vlcusto,valorSuj : Double;

begin
vlcusto := StrToFloat( Edit3.Text);
por := StrToFloat( Edit14.Text);


Edit15.text := FloatToStr(vlcusto + ((vlcusto * por)/100));
end;

procedure TFRMNFENTRADAS.SpeedButton2Click(Sender: TObject);
begin
  inherited;
 FRMPRODUTOS.Show;
end;

procedure TFRMNFENTRADAS.Edit3Exit(Sender: TObject);
begin
  inherited;
Edit14.SetFocus;
end;

procedure TFRMNFENTRADAS.SpeedButton3Click(Sender: TObject);
var
  datamodule : TDataModule2;
  precovenda,aquis : String;
  a:Integer;
begin
  inherited;
//insert into nfentradasitens (dcnumero,dcserie,dcordem,dctipo,cdfornecedor,cdproduto,quantidade,vlunitario)values(:dcnumero,:dcserie,:dcordem,:dctipo,:cdfornecedor,:cdproduto,:quantidade,:vlunitario)
try
  Query2.SQL.Text :=   'insert into nfentradasitens (dcnumero,dcserie,dcordem,dctipo,cdfornecedor,cdproduto,quantidade,vlunitario)values('+DBEdit1.text+','''+DBEdit2.Text+''','''+DBEdit3.Text+''','''+DBEdit4.Text+''','''+DBEdit9.text+''','+Edit1.text+','+DataModule2.ajustaVirgula(Edit2.text)+','+DataModule2.ajustaVirgula(Edit3.text)+')';
  datamodule.aumentarestoque(StrToInt(Edit1.text),Edit3.text,StrToInt(DataModule2.ajustaVirgula(Edit2.text)));
  Query2.ExecSQL;
  Query2.SQL.Text :=  'update produtos set aquis = '+DataModule2.ajustaVirgula(Edit3.Text)+', valor = '+DataModule2.ajustaVirgula(Edit14.Text)+',Custo = '+DataModule2.ajustaVirgula(Edit15.Text)+' ,precovenda = '+DataModule2.ajustaVirgula(Edit16.Text)+', Status = ''A'' where codpro = '+Edit1.text;
  //showmessage(Query2.SQL.Text);
  Query2.ExecSQL;
  ShowMessage('Produto Cadastrado com Sucesso !!');
  except
    ShowMessage('Erro ao lançar ITEM !!');
end;
Query3.SQL.Text := 'select *from nfentradasitens n where dcnumero = '+ IntToStr(DBEdit1.Field.asInteger)+' and  dcserie = '''+DBEdit2.Field.AsString +''' and dcordem = '''+DBEdit3.Field.AsString+''' and dctipo LIKE '''+DBEdit4.Field.AsString+''' ';
//showmessage(Query3.SQL.Text);
precovenda := Edit16.Text;
aquis := Edit3.Text;


Query3.Active := True;

{QUERY9.Active := False;
QUERY9.SQL.Text := 'update produtos set precovenda = '+precovenda+'  where codpro = '+Edit1.text;
QUERY9.ExecSQL;
QUERY9.Active := False;
QUERY9.SQL.Text := 'update produtos set aquis = '+aquis+'  where codpro = '+Edit1.text;
QUERY9.ExecSQL; }
  Edit1.text := '' ;
  Edit2.Text := '' ;
  Edit3.Text := '' ;
  Edit14.Text := '' ;
  Edit15.Text := '' ;
  Edit16.Text := '' ;
  Edit1.SetFocus;

end;

procedure TFRMNFENTRADAS.Button1Click(Sender: TObject);
begin
while( not table1.Eof )do
begin
IBQuery1.SQL.Clear;
IBQuery1.SQL.Add(' insert into nffiscal ');
IBQuery1.SQL.Add('  (IND_OPER, IND_EMIT, COD_PART, COD_SIT, DT_DOC, DT_E_S, VL_DOC, VL_DESC,');
IBQuery1.SQL.Add('   VL_FORN, VL_SERV_NT, VL_TERC, VL_DA, VL_BC_ICMS, VL_ICMS, VL_BC_ICMS_ST,');
IBQuery1.SQL.Add('   VL_ICMS_ST, COD_INF, VL_COFINS, TP_LIGACAO, COD_GRUPO_TENSAO, CHV_CTE, ');
IBQuery1.SQL.Add('   TP_CT, CHV_CTE_REF, IND_FRT, CODNFE, COD_EMPRESA, COD_FILIAL, DCNUMERO,');
IBQuery1.SQL.Add('   DCSERIE, DCORDEM, DCTIPO, CFOP, CDPEDIDO, NATUREZA) ');
IBQuery1.SQL.Add('values ');
IBQuery1.SQL.Add('  (:IND_OPER, :IND_EMIT, :COD_PART, :COD_SIT, :DT_DOC, :DT_E_S, :VL_DOC,');
IBQuery1.SQL.Add('   :VL_DESC, :VL_FORN, :VL_SERV_NT, :VL_TERC, :VL_DA, :VL_BC_ICMS, :VL_ICMS, ');
IBQuery1.SQL.Add('   :VL_BC_ICMS_ST, :VL_ICMS_ST, :COD_INF, :VL_COFINS, :TP_LIGACAO, :COD_GRUPO_TENSAO,');
IBQuery1.SQL.Add('   :CHV_CTE, :TP_CT, :CHV_CTE_REF, :IND_FRT, :CODNFE, :COD_EMPRESA, :COD_FILIAL, ');
IBQuery1.SQL.Add('   :DCNUMERO, :DCSERIE, :DCORDEM, :DCTIPO, :CFOP, :CDPEDIDO, :NATUREZA) ');
IBQuery1.ParamByName('IND_OPER').AsInteger := 01;
IBQuery1.ParamByName('IND_EMIT').AsInteger := 01;
IBQuery1.ParamByName('COD_PART').AsString := IntToStr(Table1Cdfornecedor.AsInteger);
IBQuery1.ParamByName('COD_SIT').AsString := Table1Tpsituacao.AsString;
IBQuery1.ParamByName('DT_DOC').AsDateTime:= Table1Dtemissao.AsDateTime;
IBQuery1.ParamByName('DT_E_S').AsDateTime := Table1Dtentrada.AsDateTime;
IBQuery1.ParamByName('VL_DOC').AsFloat := Table1Vlnota.AsFloat;
IBQuery1.ParamByName('VL_DESC').AsFloat  := 0;
IBQuery1.ParamByName('VL_FORN').AsFloat  := 0;
IBQuery1.ParamByName('VL_SERV_NT').AsFloat  := 0;
IBQuery1.ParamByName('VL_TERC').AsFloat  := 0;
IBQuery1.ParamByName('VL_DA').AsFloat  := 0;
IBQuery1.ParamByName('VL_BC_ICMS').AsFloat  := Table1Vlnota.AsFloat;
IBQuery1.ParamByName('VL_ICMS').AsFloat  := Table1Vlicms.AsFloat;
IBQuery1.ParamByName('VL_BC_ICMS_ST').AsFloat  := 0;
IBQuery1.ParamByName('VL_ICMS_ST').AsFloat  := 0;
IBQuery1.ParamByName('COD_INF').AsFloat  := 0;
IBQuery1.ParamByName('VL_COFINS').AsFloat := 0;
IBQuery1.ParamByName('TP_LIGACAO').AsFloat := 0 ;
IBQuery1.ParamByName('COD_GRUPO_TENSAO').Asstring := '00';
IBQuery1.ParamByName('CHV_CTE').AsString := '00';
IBQuery1.ParamByName('TP_CT').AsString := '00';
IBQuery1.ParamByName('CHV_CTE_REF').AsString := '00';
IBQuery1.ParamByName('IND_FRT').AsString := '00';
IBQuery1.ParamByName('COD_EMPRESA').AsInteger := 1;
IBQuery1.ParamByName('CODNFE').AsInteger := 1;
IBQuery1.ParamByName('DCNUMERO').AsInteger := Table1Dcnumero.AsInteger;
IBQuery1.ParamByName('DCSERIE').AsString:= Table1Dcserie.AsString;
IBQuery1.ParamByName('DCORDEM').AsString := Table1Dcordem.AsString;
IBQuery1.ParamByName('DCTIPO').AsString := Table1Dctipo.AsString;
IBQuery1.ParamByName('cdPEDIDO').AsInteger := Table1Cdpedido.AsInteger;
IBQuery1.ParamByName('NATUREZA').AsInteger := Table1Natureza.AsInteger;
IBQuery1.ParamByName('CFOP').AsString := Table1Cfop.AsString;
IBQuery1.ParamByName('COD_FILIAL').AsInteger := 1;
IBQuery1.ExecSQL;
IBQuery1.Transaction.Commit;
Table1.Next;
end; {
while (not Table2.Eof)do
begin
IBQuery2.SQL.Clear;
IBQuery2.SQL.Add('insert into nffiscalitens ');
IBQuery2.SQL.Add('  (CODNFE, COD_EMPRESA, NUM_ITEM, COD_ITEM, DESCR_COMPL, QTD, UNID, VL_ITEM,');
IBQuery2.SQL.Add('   VL_DESC, IND_MOV, CST_ICMS, CFOP, ALIQ_ICMS, VL_ICMS, VL_BC_ICMS_ST, ');
IBQuery2.SQL.Add('   ALIQ_ST, VL_ICMS_ST, IND_APUR, CST_IPI, COD_ENQ, VL_BC_IPI, ALIQ_IPI,');
IBQuery2.SQL.Add('   VL_IPI, CST_PIS, VL_BC_PIS, ALIQ_PIS, QUANT_BC_PIS, VL_PIS, CST_COFINS,');
IBQuery2.SQL.Add('   VL_BC_COFINS, ALIQ_COFINS, QUANT_BC_COFINS, VL_COFINS, COD_CTA, COD_FILIAL,');
IBQuery2.SQL.Add('   DCNUMERO, DCORDEM, DCSERIE, DCTIPO) ');
IBQuery2.SQL.Add('values ');
IBQuery2.SQL.Add('  (:CODNFE, :COD_EMPRESA, :NUM_ITEM, :COD_ITEM, :DESCR_COMPL, :QTD, :UNID,');
IBQuery2.SQL.Add('   :VL_ITEM, :VL_DESC, :IND_MOV, :CST_ICMS, :CFOP, :ALIQ_ICMS, :VL_ICMS,');
IBQuery2.SQL.Add('   :VL_BC_ICMS_ST, :ALIQ_ST, :VL_ICMS_ST, :IND_APUR, :CST_IPI, :COD_ENQ,');
IBQuery2.SQL.Add('   :VL_BC_IPI, :ALIQ_IPI, :VL_IPI, :CST_PIS, :VL_BC_PIS, :ALIQ_PIS, :QUANT_BC_PIS,');
IBQuery2.SQL.Add('   :VL_PIS, :CST_COFINS, :VL_BC_COFINS, :ALIQ_COFINS, :QUANT_BC_COFINS, ');
IBQuery2.SQL.Add('   :VL_COFINS, :COD_CTA, :COD_FILIAL, :DCNUMERO, :DCORDEM, :DCSERIE, :DCTIPO) ');

  IBQuery2.ParamByName('CODNFE').AsInteger :=         0;
  IBQuery2.ParamByName('COD_EMPRESA').AsInteger :=    1;
  IBQuery2.ParamByName('NUM_ITEM').AsInteger :=       1;
  IBQuery2.ParamByName('COD_ITEM').AsInteger :=       Table2Cdproduto.AsInteger;
  IBQuery2.ParamByName('DESCR_COMPL').AsString :=      ' '    ;
  IBQuery2.ParamByName('QTD').AsFloat :=              Table2Quantidade.AsFloat ;
  IBQuery2.ParamByName('UNID').AsFloat :=             1;
  IBQuery2.ParamByName('VL_ITEM').AsFloat :=          Table2Vlunitario.AsFloat;
  IBQuery2.ParamByName('VL_DESC').AsFloat :=          Table2Vldesconto.AsFloat;
  IBQuery2.ParamByName('IND_MOV').AsFloat :=           0;
  IBQuery2.ParamByName('CST_ICMS').AsFloat :=         0;
  IBQuery2.ParamByName('CFOP').AsString :=            '5104';
  IBQuery2.ParamByName('ALIQ_ICMS').AsFloat :=        Table2Icms.AsFloat;
  IBQuery2.ParamByName('VL_ICMS').AsFloat :=          Table2Icms.AsFloat;
  IBQuery2.ParamByName('VL_BC_ICMS_ST').AsFloat :=     0;
  IBQuery2.ParamByName('ALIQ_ST').AsFloat :=          0;
  IBQuery2.ParamByName('VL_ICMS_ST').AsFloat :=       0;
  IBQuery2.ParamByName('IND_APUR').AsFloat  :=        0;
  IBQuery2.ParamByName('CST_IPI').AsFloat :=          0;
  IBQuery2.ParamByName('COD_ENQ').AsFloat :=          0;
  IBQuery2.ParamByName('VL_BC_IPI').AsFloat :=        0;
  IBQuery2.ParamByName('ALIQ_IPI').AsFloat :=         0;
  IBQuery2.ParamByName('VL_IPI').AsFloat :=           0;
  IBQuery2.ParamByName('VL_BC_PIS').AsFloat :=        0;
  IBQuery2.ParamByName('ALIQ_PIS').AsFloat :=         0;
  IBQuery2.ParamByName('QUANT_BC_PIS').AsFloat:=      0;
  IBQuery2.ParamByName('VL_PIS').AsFloat :=           0;
  IBQuery2.ParamByName('CST_COFINS').AsFloat :=       0;
  IBQuery2.ParamByName('VL_BC_COFINS').AsFloat :=     0;
  IBQuery2.ParamByName('ALIQ_COFINS').AsFloat :=      0;
  IBQuery2.ParamByName('QUANT_BC_COFINS').AsFloat :=  0;
  IBQuery2.ParamByName('VL_COFINS').AsFloat :=        0;
  IBQuery2.ParamByName('COD_CTA').AsFloat :=          0;
  IBQuery2.ParamByName('DCNUMERO').AsInteger :=       Table2Dcnumero.AsInteger ;
  IBQuery2.ParamByName('DCSERIE').AsString :=         Table2Dcserie.AsString;
  IBQuery2.ParamByName('DCORDEM').AsString :=         Table2Dcordem.AsString;
  IBQuery2.ParamByName('DCTIPO').AsString :=          Table2Dctipo.AsString;
  IBQuery2.ParamByName('COD_filial').AsiNTEGER :=     1;
  IBQuery2.ExecSQL;
  IBQuery2.Transaction.Commit;


Table2.Next;
end;  }
while (not Table3.Eof) do
begin
      IBQuery3.Active := False;
      IBQuery3.SQL.Clear;
      IBQuery3.SQL.Add('      insert into titulospagar2 ');
      IBQuery3.SQL.Add('  (DCNUMERO, DCSERIE, DCORDEM, DCTIPO, PARCELA, DTVENCIMENTO, DTLANCAMENTO, ');
      IBQuery3.SQL.Add('   STATUS, TPSITUACAO, VLPARCELA, FORNECEDOR,TIPO_TITULO, ');
      IBQuery3.SQL.Add('   COD_EMPRESA, COD_FILIAL, COD_TITULO, OPER_TITULO,DESCONTO)');
      IBQuery3.SQL.Add('values');
      IBQuery3.SQL.Add('  (:DCNUMERO, :DCSERIE, :DCORDEM, :DCTIPO, :PARCELA, :DTVENCIMENTO, :DTLANCAMENTO, ');
      IBQuery3.SQL.Add('   :STATUS, :TPSITUACAO, :VLPARCELA, :FORNECEDOR, ');
      IBQuery3.SQL.Add('   :TIPO_TITULO, :COD_EMPRESA, :COD_FILIAL, :COD_TITULO,');
      IBQuery3.SQL.Add('   :OPER_TITULO,:DESCONTO)');
      IBQuery3.ParamByName('STATUS').AsString       := 'D';
      IBQuery3.ParamByName('TPSITUACAO').AsString   := 'F';
      IBQuery3.ParamByName('Cod_empresa').asInteger :=  1;
      IBQuery3.ParamByName('Cod_filial').asInteger  :=  1;
      IBQuery3.ParamByName('dcnumero').asInteger    :=  Table3Dcnumero.AsInteger;
      IBQuery3.ParamByName('dcserie').AsString      :=  Table3Dcserie.AsString;
      IBQuery3.ParamByName('dctipo').AsString       :=  Table3Dctipo.AsString;
      IBQuery3.ParamByName('dcordem').AsString      :=  Table3Dcordem.AsString;
      IBQuery3.ParamByName('parcela').asInteger     :=  Table3Parcela.AsInteger;
      IBQuery3.ParamByName('fornecedor').asInteger  :=  Table3Fornecedor.AsInteger;
      IBQuery3.ParamByName('dtlancamento').asdATETIME := Now ;
      IBQuery3.ParamByName('vlparcela').asfLOAT       := Table3Vlparcela.AsFloat;
      IBQuery3.ParamByName('dtvencimento').asdATETIME := Now;
      IBQuery3.ParamByName('tipo_titulo').asInteger   :=   503;
      IBQuery3.ParamByName('COD_TITULO').asInteger    :=   1;
      IBQuery3.ParamByName('DESCONTO').AsFloat    :=   0;
      IBQuery3.ParamByName('OPER_TITULO').AsString := 'P';

      IBQuery3.ExecSQL;
      IBQuery3.Transaction.Commit;
 table3.Next;
end;
end;

END.
