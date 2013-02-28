unit Unit4;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Unit2, IBCustomDataSet, IBUpdateSQL, Buttons, DBCtrls, DB,
  StdCtrls, Mask, IBQuery, ExtCtrls, IWControl, IWCompEdit,
  Unit5, DBTables, ActMask, ActDateEdit, ActCurrencyEdit, ActEdit,
  ActCustomEdit, ActNumberEdit;

type
  TModeloForm4 = class(TModeloForm)
    QryProdutos: TIBQuery;
    Label1: TLabel;
    DataSource1: TDataSource;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    Label14: TLabel;
    Label15: TLabel;
    Label16: TLabel;
    Label17: TLabel;
    Label18: TLabel;
    Label19: TLabel;
    Label21: TLabel;
    Label22: TLabel;
    IBQuery1: TIBQuery;
    GroupBox1: TGroupBox;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    BitBtn1: TBitBtn;
    QryProdutosCODPRO: TIntegerField;
    QryProdutosPRODUTO: TIBStringField;
    QryProdutosREF: TIBStringField;
    QryProdutosCLASS: TIBStringField;
    QryProdutosUNIDADE: TIBStringField;
    QryProdutosLOCAL: TIBStringField;
    QryProdutosCODIND: TFloatField;
    QryProdutosINDICE: TIBStringField;
    QryProdutosVALOR: TFloatField;
    QryProdutosCODCLI: TFloatField;
    QryProdutosRAZAO: TIBStringField;
    QryProdutosAQUIS: TFloatField;
    QryProdutosICMS: TFloatField;
    QryProdutosIPI: TFloatField;
    QryProdutosFINAN: TFloatField;
    QryProdutosFRETE: TFloatField;
    QryProdutosCUSTO: TFloatField;
    QryProdutosPRAZO: TSmallintField;
    QryProdutosPRECOVENDA: TFloatField;
    QryProdutosSTATUS: TIBStringField;
    QryProdutosESTOQUE: TFloatField;
    QryProdutosESTOQUEANT: TFloatField;
    QryProdutosAPELIDO: TIBStringField;
    QryProdutosCDBARRA: TIBStringField;
    QryProdutosCNB: TIntegerField;
    TXTCODPRO: TActNumberEdit;
    TXTPRODUTO: TActEdit;
    TXTREF: TActEdit;
    TXTCLASS: TActEdit;
    TXTUNIDADE: TActEdit;
    TXTLOCAL: TActEdit;
    TXTCODIND: TActNumberEdit;
    TXTINDICE: TActNumberEdit;
    TXTVALOR: TActCurrencyEdit;
    TXTCODCLI: TActNumberEdit;
    TXTRAZAO: TActEdit;
    TXTAQUIS: TActCurrencyEdit;
    TXTICMS: TActCurrencyEdit;
    TXTIPI: TActCurrencyEdit;
    TXTFINAN: TActCurrencyEdit;
    TXTFRETE: TActCurrencyEdit;
    TXTCUSTO: TActCurrencyEdit;
    TXTPRAZO: TActDateEdit;
    TXTPRECOVENDA: TActCurrencyEdit;
    TXTESTOQUE: TActCurrencyEdit;
    TXTESTOQUEOLD: TActCurrencyEdit;
    procedure SpeedButton2Click(Sender: TObject);
    procedure SpeedButton5Click(Sender: TObject);
    procedure SpeedButton6Click(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure FormActivate(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure SpeedButton4Click(Sender: TObject);
    procedure SpeedButton7Click(Sender: TObject);
    procedure SpeedButton8Click(Sender: TObject);
    procedure SpeedButton9Click(Sender: TObject);
    procedure BitBtn1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
   Procedure InserirPopular(Const teste : Tproduto);
   Procedure InserirProdutos(Const teste : Tproduto);
   function GravaProdutos():Boolean;
   Function  Proximo(): Boolean;
   Function  Anterior(): Boolean;
   Function  primeiro(): Boolean;
   Function  ultimo(): Boolean;
   function Auterar(): Boolean;
   Function  Deletar(): Boolean;
   function  limpar(teste : Tproduto):boolean;
   Function  buscar(): Boolean;
  end;

var
  ModeloForm4: TModeloForm4;
  produtos1  : TProduto;
implementation

uses Unit1;
{$R *.dfm}

Procedure TModeloForm4.InserirPopular(Const teste : Tproduto);
begin
  // QryProdutos.Active := False;
   QryProdutos.Active := True;
   QryProdutos.Open;
   txtcodpro.Text     := IntToStr(QryProdutosCODPRO.AsInteger);
   txtPRODUTO.Text    := QryProdutosPRODUTO.asstring;
   txtREF.Text        := QryProdutosREF.AsString;
   txtCLASS.Text      := QryProdutosCLASS.AsString;
   txtUNIDADE.Text    := QryProdutosUNIDADE.AsString;
   txtLOCAL.Text      := QryProdutosLOCAL.AsString;
   txtCODIND.Text     := FloatToStr(QryProdutosCODIND.AsFloat);
   txtINDICE.Text     := QryProdutosINDICE.AsString;
   txtVALOR.Text      := FloatToStr(QryProdutosVALOR.AsFloat);
   txtCODCLI.Text     := FloatToStr(QryProdutosCODCLI.AsFloat);
   txtRAZAO.Text      := QryProdutosRAZAO.AsString;
   txtAQUIS.Text      := FloatToStr(QryProdutosAQUIS.AsFloat);
   TXTICMS.Text       := FloatToStr(QryProdutosICMS.AsFloat);
   txtFINAN.Text      := FloatToStr(QryProdutosFINAN.AsFloat);
   txtFRETE.Text      := FloatToStr(QryProdutosFRETE.AsFloat);
   txtCUSTO.Text      := FloatToStr(QryProdutosCUSTO.AsFloat);
   txtPRAZO.Text      := QryProdutosPRAZO.AsString;
   txtPRECOVENDA.Text := FloatToStr(QryProdutosPRECOVENDA.AsFloat);
   txtESTOQUE.Text    := FloatToStr(QryProdutosESTOQUE.AsFloat);
   TXTESTOQUEOLD.Text := FloatToStr(QryProdutosESTOQUEANT.AsFloat);
   if (QryProdutosSTATUS.AsString = 'A')then
     RadioButton1.Checked := True
   else
     RadioButton2.Checked := True;
end;
   Procedure TModeloForm4.InserirProdutos(Const teste : Tproduto);
   begin
    produtos1 := TProduto.Create;
   produtos1.CODPRO     := StrToInt(TXTCODPRO.Text);
   produtos1.PRODUTO    := txtPRODUTO.Text;
   produtos1.REF        := txtREF.Text;
   produtos1.CLASSs     := txtCLASS.Text;
   produtos1.UNIDADE    := txtUNIDADE.Text;
   produtos1.LOCAL      := txtLOCAL.Text;
   if (txtCODIND.Text = '')then
       txtCODIND.Text := '0';
      if (txtVALOR.Text = '')then
       produtos1.VALOR  := 0
      else
       produtos1.VALOR      := StrToFLOAT(txtVALOR.Text);

      if (txtCODCLI.Text = '')then
       produtos1.CODCLI := 0
      else
       produtos1.CODCLI     := StrToFLOAT(txtCODCLI.Text);
      if (txtAQUIS.Text = '')then
        produtos1.AQUIS := 0
      else
       produtos1.AQUIS      := StrToFLOAT(txtAQUIS.Text);
      if (TXTICMS.Text = '')then
       produtos1.ICMS := 0
      else
       produtos1.ICMS       := StrToFLOAT(TXTICMS.Text);
      if (txtFINAN.Text = '')then
       produtos1.FINAN := 0
      else
       produtos1.FINAN      := StrToFLOAT(txtFINAN.Text);
      if (txtFRETE.Text = '')then
        produtos1.FRETE := 0
      else
       produtos1.FRETE      := StrToFLOAT(txtFRETE.Text);
      if (txtCUSTO.Text = '')then
        produtos1.CUSTO := 0
      else
       produtos1.CUSTO      := StrToFLOAT(txtCUSTO.Text);
      if (txtPRECOVENDA.Text = '')then
         produtos1.PRECOVENDA := 0
      else
          produtos1.PRECOVENDA := StrToFLOAT(txtPRECOVENDA.Text);
      if (txtESTOQUE.Text = '')then
      produtos1.ESTOQUE    := 0
      else
        produtos1.ESTOQUE    := StrToFLOAT(txtESTOQUE.Text);
      if (TXTESTOQUEOLD.Text = '')then
         produtos1.ESTOQUEANT := 0
      else
         produtos1.ESTOQUEANT := StrToFLOAT(TXTESTOQUEOLD.Text);
   produtos1.CODIND     := StrToFLOAT(txtCODIND.Text);
   produtos1.INDICE     := txtINDICE.Text;
   produtos1.RAZAO      := txtRAZAO.Text;
   IF (RadioButton1.Checked = TRUE)THEN
      produtos1.STATUS     := 'A'
   ELSE
      produtos1.STATUS     := 'I';
//   produtos1.ESTOQUE    := StrToFLOAT(txtESTOQUE.Text);
//   produtos1.ESTOQUEANT := StrToFLOAT(TXTESTOQUEOLD.Text);

   end;
   function  TModeloForm4.limpar(teste : Tproduto):boolean;
   begin
   txtcodpro.Text     := '';
   txtPRODUTO.Text    := '';
   txtREF.Text        := '';
   txtCLASS.Text      := '';
   txtUNIDADE.Text    := '';
   txtLOCAL.Text      := '';
   txtCODIND.Text     := '';
   txtINDICE.Text     := '';
   txtVALOR.Text      := '';
   txtCODCLI.Text     := '';
   txtRAZAO.Text      := '';
   txtAQUIS.Text      := '';
   TXTICMS.Text       := '';
   txtFINAN.Text      := '';
   txtFRETE.Text      := '';
   txtCUSTO.Text      := '';
   txtPRAZO.Text      := '';
   txtPRECOVENDA.Text := '';
   txtESTOQUE.Text    := '';
   TXTESTOQUEOLD.Text := '';


   end;
function TModeloForm4.Buscar():Boolean;
begin
  QryProdutos.SQL.Text := 'select *from produtos where codpro > 0 ';
  if ( TXTCODPRO.Text <> '') then
        QryProdutos.SQL.Add(' and codpro = '+TXTCODPRO.Text+' '  );
  if ( TXTPRODUTO.Text <> '') then
        QryProdutos.SQL.Add(' and produto = '+TXTPRODUTO.Text  );
  if ( TXTREF.Text <> '') then
        QryProdutos.SQL.Add(' and REF = '+TXTREF.Text  );
  if ( TXTCLASS.Text <> '') then
        QryProdutos.SQL.Add(' and class = '''+TXTclass.Text+''''  );
  if ( TXTUNIDADE.Text <> '' ) then
        QryProdutos.SQL.Add(' and unidade = '''+TXTUNIDADE.Text+''''  );
  if ( TXTLOCAL.Text <> '') then
        QryProdutos.SQL.Add(' and local = '''+TXTlocal.Text+''''  );
  if ( TXTCODIND.Text <> '') then
        QryProdutos.SQL.Add(' and codind = '''+TXTcodind.Text+''''  );
  if ( TXTINDICE.Text <> '') then
        QryProdutos.SQL.Add(' and indice = '''+TXTINDICE.Text+''''  );
  if ( TXTVALOR.Text <> '') then
        QryProdutos.SQL.Add(' and valor = '''+TXTvalor.Text+''''  );
  if ( TXTCODCLI.Text <> '' ) then
        QryProdutos.SQL.Add(' and codcli = '''+TXTCODCLI.Text+''''  );
  if ( TXTESTOQUE.Text <> '' ) then
        QryProdutos.SQL.Add(' and estoque = '''+TXTESTOQUE.Text+''''  );
  if (RadioButton1.Checked = True) then
        QryProdutos.SQL.Add(' and status = ''A'''  );
  if (RadioButton2.Checked = True)then
        QryProdutos.SQL.Add(' and status <> ''A'''  );
  try
   QryProdutos.Active := True;
   if (not QryProdutos.Eof) then
      result := True
   else
      result := False;

  Except
     Result := False;
  end;
end;
function TModeloForm4.GravaProdutos():Boolean;
begin
try
   //  InserirProdutos(produtos1);
     IBQuery1.SQL.Clear;
     IBQuery1.SQL.add ('insert into Produtos(CODPRO, PRODUTO, REF, CLASS, UNIDADE, LOCAL, CODIND, INDICE, VALOR, CODCLI, RAZAO, AQUIS, ICMS, IPI, FINAN, FRETE, CUSTO,  PRECOVENDA, STATUS, ESTOQUE, ESTOQUEANT');
     IBQuery1.SQL.add (') values (:CODPRO, :PRODUTO, :REF, :CLASS, :UNIDADE, :LOCAL, :CODIND, :INDICE, :VALOR, :CODCLI, :RAZAO, :AQUIS, :ICMS, :IPI, :FINAN, :FRETE, :CUSTO,  :PRECOVENDA, :STATUS, :ESTOQUE, :ESTOQUEANT)');
     IBQuery1.ParamByName('CODPRO').AsInteger :=  produtos1.CODPRO ;
     IBQuery1.ParamByName('PRODUTO').AsString :=  produtos1.PRODUTO ;
     IBQuery1.ParamByName('REF').AsString :=      produtos1.REF ;
     IBQuery1.ParamByName('CLASS').AsString :=    produtos1.CLASSs ;
     IBQuery1.ParamByName('UNIDADE').AsString :=  produtos1.UNIDADE;
     IBQuery1.ParamByName('LOCAL').AsString :=    produtos1.LOCAL;
     IBQuery1.ParamByName('CODIND').AsFloat :=  produtos1.CODIND;
     IBQuery1.ParamByName('INDICE').AsString:=    produtos1.INDICE;
     IBQuery1.ParamByName('VALOR').AsFloat :=     produtos1.VALOR;
     IBQuery1.ParamByName('CODCLI').AsFloat :=    produtos1.CODCLI;
     IBQuery1.ParamByName('RAZAO').AsString :=    produtos1.RAZAO;
     IBQuery1.ParamByName('AQUIS').AsFloat :=     produtos1.AQUIS;
     IBQuery1.ParamByName('ICMS').AsFLoat :=      produtos1.ICMS;
     IBQuery1.ParamByName('IPI').AsFloat :=       produtos1.ICMS;
     IBQuery1.ParamByName('FINAN').AsFloat :=     produtos1.FINAN;
     IBQuery1.ParamByName('FRETE').AsFloat :=     produtos1.FRETE;
     IBQuery1.ParamByName('CUSTO').AsFloat :=     produtos1.CUSTO;
     IBQuery1.ParamByName('PRECOVENDA').Asfloat :=produtos1.PRECOVENDA;
     IBQuery1.ParamByName('STATUS').AsString :=    produtos1.STATUS;
     IBQuery1.ParamByName('ESTOQUE').Asfloat :=   produtos1.ESTOQUE;
     IBQuery1.ParamByName('ESTOQUEANT').Asfloat :=produtos1.ESTOQUEANT;
     IBQuery1.ExecSQL;
     IBQuery1.Transaction.Commit;
     result := True;
  Except
     Result := False;
  End;
end;
   Function TModeloForm4.Proximo(): Boolean;
   begin
      QryProdutos.Next;

   end;
      Function TModeloForm4.Anterior(): Boolean;
   begin
     // QryProdutos.First; primeiro
     // QryProdutos.Last;  ultimo
        QryProdutos.Prior;
   end;
         Function TModeloForm4.primeiro(): Boolean;
   begin
     QryProdutos.First;

   end;
         Function TModeloForm4.ultimo(): Boolean;
   begin
     QryProdutos.Last;
   end;
   function TModeloForm4.Auterar():boolean;
   begin
   InserirProdutos(produtos1);
   IBQuery1.SQL.Clear;
   IBQuery1.SQL.add(' update Produtos');
   IBQuery1.SQL.add('set');
   IBQuery1.SQL.add('PRODUTO = :PRODUTO,');
   IBQuery1.SQL.add('REF = :REF,');
   IBQuery1.SQL.add('CLASS = :CLASS,');
   IBQuery1.SQL.add('UNIDADE = :UNIDADE,');
   IBQuery1.SQL.add('LOCAL = :LOCAL,');
   IBQuery1.SQL.add('CODIND = :CODIND,');
   IBQuery1.SQL.add('INDICE = :INDICE,');
   IBQuery1.SQL.add('VALOR = :VALOR,');
   IBQuery1.SQL.add('CODCLI = :CODCLI,');
   IBQuery1.SQL.add('RAZAO = :RAZAO,');
   IBQuery1.SQL.add('AQUIS = :AQUIS,');
   IBQuery1.SQL.add('ICMS = :ICMS,');
   IBQuery1.SQL.add('IPI = :IPI,');
   IBQuery1.SQL.add('FINAN = :FINAN,');
   IBQuery1.SQL.add('FRETE = :FRETE,');
   IBQuery1.SQL.add('CUSTO = :CUSTO,');
   IBQuery1.SQL.add('PRECOVENDA = :PRECOVENDA,');
   IBQuery1.SQL.add('STATUS = :STATUS,');
   IBQuery1.SQL.add('ESTOQUE = :ESTOQUE,');
   IBQuery1.SQL.add('ESTOQUEANT = :ESTOQUEANT');
   IBQuery1.SQL.add('where');
   IBQuery1.SQL.add('CODPRO = :OLD_CODPRO');
   IBQuery1.ParamByName('OLD_CODPRO').AsInteger :=  produtos1.CODPRO ;
   IBQuery1.ParamByName('PRODUTO').AsString :=  produtos1.PRODUTO ;
   IBQuery1.ParamByName('REF').AsString :=      produtos1.REF ;
   IBQuery1.ParamByName('CLASS').AsString :=    produtos1.CLASSs ;
   IBQuery1.ParamByName('UNIDADE').AsString :=  produtos1.UNIDADE;
   IBQuery1.ParamByName('LOCAL').AsString :=    produtos1.LOCAL;
   IBQuery1.ParamByName('CODIND').AsFloat :=  produtos1.CODIND;
   IBQuery1.ParamByName('INDICE').AsString:=    produtos1.INDICE;
   IBQuery1.ParamByName('VALOR').AsFloat :=     produtos1.VALOR;
   IBQuery1.ParamByName('CODCLI').AsFloat :=    produtos1.CODCLI;
   IBQuery1.ParamByName('RAZAO').AsString :=    produtos1.RAZAO;
   IBQuery1.ParamByName('AQUIS').AsFloat :=     produtos1.AQUIS;
   IBQuery1.ParamByName('ICMS').AsFLoat :=      produtos1.ICMS;
   IBQuery1.ParamByName('IPI').AsFloat :=       produtos1.ICMS;
   IBQuery1.ParamByName('FINAN').AsFloat :=     produtos1.FINAN;
   IBQuery1.ParamByName('FRETE').AsFloat :=     produtos1.FRETE;
   IBQuery1.ParamByName('CUSTO').AsFloat :=     produtos1.CUSTO;
   IBQuery1.ParamByName('PRECOVENDA').Asfloat :=produtos1.PRECOVENDA;
   IBQuery1.ParamByName('STATUS').AsString :=    produtos1.STATUS;
   IBQuery1.ParamByName('ESTOQUE').Asfloat :=   produtos1.ESTOQUE;
   IBQuery1.ParamByName('ESTOQUEANT').Asfloat :=produtos1.ESTOQUEANT;
TRY
IBQuery1.ExecSQL;
IBQuery1.Transaction.Commit;
result := True;
Except
result := False;
end;



   end;
   Function  TModeloForm4.Deletar(): Boolean;
   begin
      IBQuery1.SQL.Clear;
      IBQuery1.SQL.Text := 'delete from produtos where codpro ='+Txtcodpro.Text;
      IBQuery1.ExecSQL;
      IBQuery1.Transaction.Commit;
   end;
procedure TModeloForm4.SpeedButton2Click(Sender: TObject);
begin
  inherited;
 if (not  Auterar())then
   ShowMessage('Erro ao alterar o produto')
 else
   ShowMessage('Produto alterado com sucesso');
end;

procedure TModeloForm4.SpeedButton5Click(Sender: TObject);
begin
  inherited;
Proximo;
InserirPopular(produtos1);
end;

procedure TModeloForm4.SpeedButton6Click(Sender: TObject);
begin
  inherited;
Anterior;
InserirPopular(produtos1);
end;

procedure TModeloForm4.SpeedButton1Click(Sender: TObject);
begin
  inherited;
limpar(produtos1);
end;

procedure TModeloForm4.FormActivate(Sender: TObject);
begin
  inherited;
  QryProdutos.Active := False;
  QryProdutos.Active := True;
 InserirPopular(produtos1);
end;

procedure TModeloForm4.SpeedButton3Click(Sender: TObject);
begin
  inherited;
if (not GravaProdutos()) then
   ShowMessage('ERRO AO INSERIR PRODUTO')
else
   ShowMessage('PRODUTO CADASTRADO COM SUCESSO');
end;

procedure TModeloForm4.SpeedButton4Click(Sender: TObject);
begin
  inherited;
Deletar();
InserirPopular(Produtos1);
end;

procedure TModeloForm4.SpeedButton7Click(Sender: TObject);
begin
  inherited;
Primeiro();
InserirPopular(produtos1);
end;

procedure TModeloForm4.SpeedButton8Click(Sender: TObject);
begin
  inherited;
ultimo();
InserirPopular(produtos1);
end;

procedure TModeloForm4.SpeedButton9Click(Sender: TObject);
begin
  inherited;
if (not buscar()) then
    Showmessage('PRODUTO NAO ENCONTRADO')
ELSE
BEGIN
BUSCAR();
QryProdutos.Active := False;
QryProdutos.Active := True;
InserirPopular(produtos1);
END;
end;

procedure TModeloForm4.BitBtn1Click(Sender: TObject);
begin
  inherited;
{produtos1 := TProduto.Create;
  while (not Table1.eof) do
  begin
   if Table1CODPRO.AsInteger = 132 then
     showmessage('0');

   produtos1.CODPRO     := Table1CODPRO.AsInteger;
   produtos1.PRODUTO    := Table1PRODUTO.AsString;
   produtos1.REF        := Table1REF.AsString;
   produtos1.CLASSs     := Table1CLASS.AsString;
   produtos1.UNIDADE    := Table1UNIDADE.AsString;
   produtos1.LOCAL      := Table1LOCAL.AsString;
   if (Table1CODIND.IsNull )then
       produtos1.codind := 0
   else
       produtos1.codind :=  Table1CODIND.AsFloat;
      if (Table1VALOR.IsNull)then
          produtos1.VALOR  := 0
      else
        produtos1.VALOR      := Table1VALOR.AsFloat;

      if (Table1CODCLI.IsNull)then
       produtos1.CODCLI := 0
      else
       produtos1.CODCLI     := Table1CODCLI.AsFloat;
      if (Table1AQUIS.IsNull)then
        produtos1.AQUIS := 0
      else
       produtos1.AQUIS      := Table1AQUIS.AsFloat;
      if (Table1ICMS.IsNull)then
       produtos1.ICMS := 0
      else
       produtos1.ICMS       := Table1ICMS.AsFloat;
      if (Table1FINAN.IsNull)then
       produtos1.FINAN := 0
      else
       produtos1.FINAN      := Table1FINAN.AsFloat;
      if (Table1FRETE.IsNull)then
        produtos1.FRETE := 0
      else
       produtos1.FRETE      := Table1FRETE.AsFloat;
      if (Table1CUSTO.IsNull)then
        produtos1.CUSTO := 0
      else
       produtos1.CUSTO      := Table1CUSTO.AsFloat;
      if (Table1PRECOVENDA.IsNull)then
         produtos1.PRECOVENDA := 0
      else
          produtos1.PRECOVENDA := Table1PRECOVENDA.AsFloat;
      if (Table1ESTOQUE.IsNull)then
      produtos1.ESTOQUE    := 0
      else
        produtos1.ESTOQUE    := Table1ESTOQUE.AsFloat;
      if (Table1ESTOQUEANT.IsNull)then
         produtos1.ESTOQUEANT := 0
      else
         produtos1.ESTOQUEANT :=Table1ESTOQUEANT.AsFloat;
   produtos1.INDICE     := Table1INDICE.AsString;
   produtos1.RAZAO      := Table1RAZAO.AsString;
   produtos1.STATUS := 'A';
   GravaProdutos();

  table1.Next;
end; }

end;

end.
