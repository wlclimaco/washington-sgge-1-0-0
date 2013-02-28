unit Unit12;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Unit2, DB, IBCustomDataSet, IBQuery, Grids, DBGrids,
  ActCurrencyEdit, StdCtrls, ActLabel, ActMask, ActDateEdit, ExtCtrls,
  ActCustomEdit, ActNumberEdit, Buttons;

type
  TModeloForm12 = class(TModeloForm)
    TXTCOO: TActNumberEdit;
    TXTDATA: TActDateEdit;
    Panel4: TPanel;
    TXTCODPRO: TActNumberEdit;
    ActLabel1: TActLabel;
    TXTQUANTIDADE: TActCurrencyEdit;
    TXTVALOR: TActCurrencyEdit;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    DBGrid1: TDBGrid;
    SpeedButton11: TSpeedButton;
    IBQuery1: TIBQuery;
    IBQuery2: TIBQuery;
    IBQuery3: TIBQuery;
    IBQuery4: TIBQuery;
    IBQuery5: TIBQuery;
    IBQuery6: TIBQuery;
    GroupBox1: TGroupBox;
    GroupBox2: TGroupBox;
    GroupBox3: TGroupBox;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    GroupBox4: TGroupBox;
    Label8: TLabel;
    GroupBox5: TGroupBox;
    Label9: TLabel;
    IBQuery7: TIBQuery;
    IBQuery8: TIBQuery;
    DataSource1: TDataSource;
    GroupBox6: TGroupBox;
    Label10: TLabel;
    IBQuery9: TIBQuery;
    IBQuery10: TIBQuery;
    procedure TXTCOOExit(Sender: TObject);
    procedure TXTCODPROExit(Sender: TObject);
    procedure SpeedButton11Click(Sender: TObject);
    procedure TXTDATAExit(Sender: TObject);
    procedure DBGrid1DblClick(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
     Function SomaItens(const DtMovimento :String):Double;
     Function SomaMovimentoDiario(const DtMovimento :String):Double;
     Function SomaReducao(const DtMovimento :String):Double;
     Function SomaCupomAtual(const noCOO :integer):Double;
     Function PopulaGrid(const noCOO :integer):Boolean;
  end;

var
  ModeloForm12: TModeloForm12;
  MaxCOO      : Integer;
  MaxItens    : Integer;
implementation

uses Unit3, Unit11, Unit7, Unit8;

{$R *.dfm}
Function TModeloForm12.PopulaGrid(const noCOO :integer):Boolean;
begin
  IBQuery8.Active := False;
  IBQuery8.SQL.Text := 'select *from nfsaidasitens i where  i.coo = '+TXTCOO.Text+'  ';
  IBQuery8.Active := True;
  Result := True;

end;
Function TModeloForm12.SomaCupomAtual(const noCOO :integer):Double;
begin
  IBQuery4.Active := False;
  IBQuery4.SQL.Text := 'select sum (i.quantidade * i.vlproduto) from nfsaidasitens i where  i.coo = '+TXTCOO.Text+'  ';
  IBQuery4.Active := True;
  result := IBQuery4.FieldByName('Sum').AsFloat;
end;
Function TModeloForm12.SomaItens(Const DtMovimento :String):Double;
begin
  IBQuery4.Active := False;
  IBQuery4.SQL.Text := 'select sum (i.quantidade * i.vlproduto) from nfsaidas2 n,nfsaidasitens i where i.coo = n.coo and n.dtmovimento = '''+Form7.ajustaData(TXTDATa.Text)+'''  ';
  IBQuery4.Active := True;
  result := IBQuery4.FieldByName('Sum').AsFloat;
end;
Function TModeloForm12.SomaMovimentoDiario(const DtMovimento :String):Double;
begin
  IBQuery5.Active := False;
  IBQuery5.SQL.Text := 'select *from movimentodiario m where m.datamovimento = '''+Form7.ajustaData(TXTDATa.Text)+'''';
  IBQuery5.Active := True;
  result := IBQuery5.FieldByName('TOTAL').AsFloat;
end;
Function TModeloForm12.SomaReducao(const DtMovimento :String):Double;
begin
  IBQuery6.Active := False;
  IBQuery6.SQL.Text := 'select *from redleitura r where r.dtmovimento = '''+Form7.ajustaData(TXTDATa.Text)+'''';
  IBQuery6.Active := True;
  result := IBQuery6.FieldByName('VENDABRUTA').AsFloat;
end;

procedure TModeloForm12.TXTCOOExit(Sender: TObject);
begin
  inherited;
  IBQuery1.Active := False;
  IBQuery1.SQL.Text := 'Select MAX(COO) from nfsaidas2 where STFISCAL = ''N''';
  IBQuery1.Active := True;
   if ( IBQuery1.FieldByName('MAX').IsNull )then
       MaxCOO := 99998
   else
       MaxCOO := IBQuery1.FieldByName('MAX').AsInteger;

  TXTCOO.Text := IntToStr(MaxCOO+1);
  Label9.Caption := FormatFloat('0.00',SomaCupomAtual(StrToInt(TXTCOO.Text)));
  PopulaGrid(StrToInt(TXTCOO.Text));

end;

procedure TModeloForm12.TXTCODPROExit(Sender: TObject);
var
   Produto :String;
begin
  Produto := '';
  IBQuery2.Active := False;
  IBQuery2.SQL.Text := 'Select *from Produtos where codPro = '+TXTCODPRO.text+'';
  IBQuery2.Active := True;
   if (IBQuery2.Eof)then
       begin
           IBQuery2.Active := False;
           IBQuery2.SQL.Text := 'Select *from Produtos where REF = ''%'+TXTCODPRO.text+'%''';
           IBQuery2.Active := True;
           if (IBQuery2.Eof)then
            ShowMessage ('Produto NAO ENCONTRADO')
           else
            Produto := IBQuery2.FieldByName('Produto').AsString;
       end
   else
       Produto := IBQuery2.FieldByName('Produto').AsString;
       ActLabel1.Caption := Produto;
end;

procedure TModeloForm12.SpeedButton11Click(Sender: TObject);
begin
  IBQuery3.Active := False;
  IBQuery3.SQL.Text := 'Select MAX(NOITEM) from nfsaidasitens where COO = '+TXTCOO.Text;
  IBQuery3.Active := True;
  if ( IBQuery3.FieldByName('MAX').IsNull )then
        MaxItens := 1
  else
     MaxItens := IBQuery3.FieldbyName('MAX').asinteger + 1;

  IBQuery3.SQL.Text := 'Insert Into NFSAIDASITENS (COO,NOITEM,QUANTIDADE,VLPRODUTO,CODPRO) values ('+TXTCOO.Text+','+IntToStr(MaxItens)+','+Form7.ajustaVirgula(TXTQUANTIDADE.Text)+','+Form7.ajustaVirgula(TXTVALOR.Text)+','+Form7.ajustaVirgula(TXTCODPRO.Text)+' )';
  IBQuery3.ExecSQL;
  IBQuery3.Transaction.Commit;
  Label9.Caption := FormatFloat('0.00',SomaCupomAtual(StrToInt(TXTCOO.Text)));
  end;

procedure TModeloForm12.TXTDATAExit(Sender: TObject);
begin
  inherited;

Label5.Caption := FloatToStr(somaItens(TXTDATA.Text));
Label7.Caption := FloatToStr(SomaMovimentoDiario(TXTDATA.Text));
Label6.Caption := FloatToStr(SomaReducao(TXTDATA.Text));
//Label8.Caption := FloatToStr(SomaMovimentoDiario(TXTDATA.Text) - SomaReducao(TXTDATA.Text));
If (SomaMovimentoDiario(TXTDATA.Text) - SomaReducao(TXTDATA.Text) < 0 )then
   Label8.Font.Color :=  Clred
else
   Label8.Font.Color := clWindowText;
Label8.Caption := FormatFloat('0.00',SomaMovimentoDiario(TXTDATA.Text) - SomaReducao(TXTDATA.Text));
  Label10.Caption := FormatFloat('0.00',(SomaMovimentoDiario(TXTDATA.Text) - SomaReducao(TXTDATA.Text)) - SomaCupomAtual(StrToInt(TXTCOO.Text)));
end ;



procedure TModeloForm12.DBGrid1DblClick(Sender: TObject);
begin
  IBQuery9.SQL.Text := 'delete from nfsaidasitens where coo = '+IntToStr(DBGrid1.Fields[0].asInteger)+' and noitem = '+IntToStr(DBGrid1.Fields[1].asInteger)+' ';
  IBQuery9.ExecSQL;
  IBQuery9.Transaction.Commit;

end;

procedure TModeloForm12.SpeedButton3Click(Sender: TObject);
begin
  IBQuery9.SQL.Text := 'insert into nfsaidas2 (COO,DTMOVIMENTO,VLNOTA,STATUS,STFISCAL)values('+TXTCOO.Text+','''+FORM7.ajustaData(TXTDATA.Text)+''','+FORM7.ajustaVirgula(FloatToStr(SomaCupomAtual(StrToInt(TXTCOO.Text))))+',''A'',''N'')';
  ShowMEssage(IBQuery9.SQL.Text);
  IBQuery9.ExecSQL;
  IBQuery9.Transaction.Commit;

end;

end.
