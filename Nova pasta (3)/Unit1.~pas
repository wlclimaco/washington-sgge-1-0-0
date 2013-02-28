unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ActNumberEdit, ActResultEdit, ActCurrencyEdit, StdCtrls,
  ExtCtrls, ActCustomEdit, ActMask, ActDateEdit, Grids, DBGrids, Buttons,
  DB, IBCustomDataSet, IBQuery, IBDatabase, IBUpdateSQL, XPInformationPanel;

type
  TForm1 = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    SpeedButton3: TSpeedButton;
    SpeedButton4: TSpeedButton;
    SpeedButton5: TSpeedButton;
    SpeedButton6: TSpeedButton;
    SpeedButton7: TSpeedButton;
    SpeedButton8: TSpeedButton;
    ScrollBox1: TScrollBox;
    TXTDATA: TActDateEdit;
    TXTDINHEIRO: TActCurrencyEdit;
    TXTCHEQUE: TActResultEdit;
    TXTPROMISSORIA: TActResultEdit;
    TXTDEBITO: TActCurrencyEdit;
    TXTCREDITO: TActCurrencyEdit;
    TXTTOTAL: TActCurrencyEdit;
    IBDatabase1: TIBDatabase;
    IBTransaction1: TIBTransaction;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    Button1: TButton;
    txtcontadin: TActResultEdit;
    txtcontadeb: TActResultEdit;
    txtcontacre: TActResultEdit;
    txtdtprovpag: TActDateEdit;
    QryMovDia: TIBQuery;
    IBUpdateSQL1: TIBUpdateSQL;
    QryTiTUlos: TIBQuery;
    IBUpdateSQL2: TIBUpdateSQL;
    TXTEMPRESA: TActResultEdit;
    TXTFILIAL: TActResultEdit;
    QryTiTUlosDCNUMERO: TIntegerField;
    QryTiTUlosDCSERIE: TIBStringField;
    QryTiTUlosDCORDEM: TIBStringField;
    QryTiTUlosDCTIPO: TIBStringField;
    QryTiTUlosPARCELA: TFloatField;
    QryTiTUlosDTVENCIMENTO: TIBStringField;
    QryTiTUlosDTLANCAMENTO: TIBStringField;
    QryTiTUlosSTATUS: TIBStringField;
    QryTiTUlosTPSITUACAO: TIBStringField;
    QryTiTUlosVLPARCELA: TFloatField;
    QryTiTUlosFORNECEDOR: TFloatField;
    QryTiTUlosOBS: TIBStringField;
    QryTiTUlosDATAPAGAMENTO: TDateField;
    QryTiTUlosTIPO_TITULO: TIntegerField;
    QryTiTUlosCOD_EMPRESA: TIntegerField;
    QryTiTUlosCOD_FILIAL: TIntegerField;
    QryTiTUlosCOD_CONTA: TIntegerField;
    QryTiTUlosCOD_TITULO: TIntegerField;
    QryTiTUlosVALORPAGO: TFloatField;
    QryTiTUlosOPER_TITULO: TIBStringField;
    ActResultEdit1: TActResultEdit;
    SpeedButton2: TSpeedButton;
    IBQuery2: TIBQuery;
    Button2: TButton;
    IBQuery3: TIBQuery;
    IntegerField1: TIntegerField;
    IBStringField1: TIBStringField;
    IBStringField2: TIBStringField;
    IBStringField3: TIBStringField;
    FloatField1: TFloatField;
    IBStringField4: TIBStringField;
    IBStringField5: TIBStringField;
    IBStringField6: TIBStringField;
    IBStringField7: TIBStringField;
    FloatField2: TFloatField;
    FloatField3: TFloatField;
    IBStringField8: TIBStringField;
    DateField1: TDateField;
    IntegerField2: TIntegerField;
    IntegerField3: TIntegerField;
    IntegerField4: TIntegerField;
    IntegerField5: TIntegerField;
    IntegerField6: TIntegerField;
    FloatField4: TFloatField;
    IBStringField9: TIBStringField;
    IBQuery4: TIBQuery;
    IntegerField7: TIntegerField;
    IBStringField10: TIBStringField;
    IBStringField11: TIBStringField;
    IBStringField12: TIBStringField;
    FloatField5: TFloatField;
    IBStringField13: TIBStringField;
    IBStringField14: TIBStringField;
    IBStringField15: TIBStringField;
    IBStringField16: TIBStringField;
    FloatField6: TFloatField;
    FloatField7: TFloatField;
    IBStringField17: TIBStringField;
    DateField2: TDateField;
    IntegerField8: TIntegerField;
    IntegerField9: TIntegerField;
    IntegerField10: TIntegerField;
    IntegerField11: TIntegerField;
    IntegerField12: TIntegerField;
    FloatField8: TFloatField;
    IBStringField18: TIBStringField;
    IBQuery5: TIBQuery;
    IntegerField13: TIntegerField;
    IBStringField19: TIBStringField;
    IBStringField20: TIBStringField;
    IBStringField21: TIBStringField;
    FloatField9: TFloatField;
    IBStringField22: TIBStringField;
    IBStringField23: TIBStringField;
    IBStringField24: TIBStringField;
    IBStringField25: TIBStringField;
    FloatField10: TFloatField;
    FloatField11: TFloatField;
    IBStringField26: TIBStringField;
    DateField3: TDateField;
    IntegerField14: TIntegerField;
    IntegerField15: TIntegerField;
    IntegerField16: TIntegerField;
    IntegerField17: TIntegerField;
    IntegerField18: TIntegerField;
    FloatField12: TFloatField;
    IBStringField27: TIBStringField;
    Button3: TButton;
    Button4: TButton;
    procedure TXTDATAExit(Sender: TObject);
    procedure TXTCHEQUEButtonClick(Sender: TObject);
    procedure TXTPROMISSORIAButtonClick(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure TXTDINHEIROExit(Sender: TObject);
    procedure TXTCHEQUEExit(Sender: TObject);
    procedure TXTPROMISSORIAExit(Sender: TObject);
    procedure TXTDEBITOExit(Sender: TObject);
    procedure TXTCREDITOExit(Sender: TObject);
    procedure SpeedButton4Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure SpeedButton7Click(Sender: TObject);
    procedure SpeedButton8Click(Sender: TObject);
    procedure SpeedButton5Click(Sender: TObject);
    procedure SpeedButton6Click(Sender: TObject);
    procedure FormActivate(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure Button4Click(Sender: TObject);
  private
    { Private declarations }
  public
    function  ajustaVirgula(const frase : String): String;
    function GravarALMENTOConta(CONST VALOR :DOUBLE;CONTA :INTEGER ):Boolean;
    FUNCTION POPULATXT():BOOLEAN;
//    FUNCTION POPULABANCO():BOOLEAN;
    FUNCTION SELECTINICIAL():BOOLEAN;
    function Primeiro():Boolean;
    function Proximo():Boolean;
    function Anterior():Boolean;
    function Ultimo():Boolean;
  end;

var
  Form1: TForm1;
  SOMA : double;

implementation

uses Unit2, Unit3, Unit4, Unit5, Unit6, Unit7, Unit8;

{$R *.dfm}

function TForm1.Primeiro():Boolean;
    begin
      QryMovDia.First;
    end;
    function TForm1.Proximo():Boolean;
    begin
       QryMovDia.Next;
    end;
    function TForm1.Anterior():Boolean;
    begin
       QryMovDia.Prior;
    end;
    function TForm1.Ultimo():Boolean;
    begin
       QryMovDia.Last;
    end;


FUNCTION TForm1.SELECTINICIAL():BOOLEAN;
BEGIN
QryMovDia.SQL.Clear;
QryMovDia.Active := fALSE;
QryMovDia.SQL.Add('SELECT *FROM movimentodiario where status = ''A'' ORDER BY DATAmovimento');
QryMovDia.Active := TRUE;
end;

FUNCTION TForm1.POPULATXT():BOOLEAN;
BEGIN
ActResultEdit1.Text :=  IntToStr(QryMovDia.FieldByName('ID').AsInteger) ;
TXTEMPRESA.Text :=      IntToStr(QryMovDia.FieldByName('COD_EMPRESA').AsInteger) ;
TXTFILIAL.Text  :=      IntToStr(QryMovDia.FieldByName('COD_FILIAL').AsInteger);
TXTDATA.Text    :=      DateToStr(QryMovDia.FieldByName('DATAMOVIMENTO').AsDateTime );
TXTTOTAL.Text   :=      FloatToStr(QryMovDia.FieldByName('TOTAL').AsFloat);
TXTDINHEIRO.Text:=      FloatToStr(QryMovDia.FieldByName('DINHEIRO').AsFloat);
TXTCHEQUE.Text  :=      FloatToStr(QryMovDia.FieldByName('CHEQUE').AsFloat) ;
TXTCREDITO.Text :=      FloatToStr(QryMovDia.FieldByName('CARTAO_CREDITO').AsFloat);
TXTDEBITO.Text  :=      FloatToStr(QryMovDia.FieldByName('CARTAO_DEBITO').AsFloat) ;
TXTPROMISSORIA.Text  :=      FloatToStr(QryMovDia.FieldByName('PROMISSORIA').AsFloat);

end;

 function TForm1.GravarALMENTOConta(CONST VALOR :DOUBLE;CONTA :INTEGER ):Boolean;
 begin
   fORM5.QryContaCorrente.Active := False;
   fORM5.QryContaCorrente.SQL.Clear;
   fORM5.QryContaCorrente.SQL.Add('update contacorrente set vlsaldo = vlsaldo +'+FloatToStr(VALOR)+' where cdcontacorrente = '+IntToStr(CONTA)+'' );
   fORM5.QryContaCorrente.ExecSQL;
   fORM5.QryContaCorrente.Transaction.Commit;
 end;
function  TForm1.ajustaVirgula(const frase : String): String;
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
    {x:= 0;
    teste := '';
    teste := frase;
    while (x < 10  )do
    begin
      if(teste[x] = ',') then
        teste[x] := '.';
        x := x + 1;
    end;
    Result := teste; }

  end;

procedure TForm1.TXTDATAExit(Sender: TObject);
begin
 IBQuery1.Active := False;
 IBQuery1.SQL.Clear;
 IBQuery1.SQL.Add('Select *from movimentodiario where datamovimento = '''+TXTDATA.Text+'''');
 IBQuery1.Active := true;
 Form2.IBQuery1.Active := False;
 Form2.IBQuery1.SQL.Clear;
 Form2.IBQuery1.SQL.Add('select *from cheques where dtmovimento = '''+TXTDATA.Text+'''');
 Form2.IBQuery1.Active := True;
end;

procedure TForm1.TXTCHEQUEButtonClick(Sender: TObject);
begin
Form5.Show;
end;

procedure TForm1.TXTPROMISSORIAButtonClick(Sender: TObject);
begin
Form5.Show;
end;

procedure TForm1.Button1Click(Sender: TObject);
begin
Form4.Show;
end;

procedure TForm1.SpeedButton3Click(Sender: TObject);
begin
QryMovDia.SQL.Clear;
QryMovDia.SQL.Add('insert into MOVIMENTODIARIO ');
QryMovDia.SQL.Add('  (COD_EMPRESA,COD_FILIAL, ID, DATAMOVIMENTO, TOTAL, DINHEIRO, CHEQUE, CARTAO_CREDITO, CARTAO_DEBITO, ');
QryMovDia.SQL.Add('   PROMISSORIA)');
QryMovDia.SQL.Add('values ');
QryMovDia.SQL.Add('  (:COD_EMPRESA,:COD_FILIAL,:ID, :DATAMOVIMENTO, :TOTAL, :DINHEIRO, :CHEQUE, :CARTAO_CREDITO, :CARTAO_DEBITO, ');
QryMovDia.SQL.Add('   :PROMISSORIA )');
QryMovDia.ParamByName('ID').AsInteger            :=  1;
QryMovDia.ParamByName('COD_EMPRESA').AsInteger   :=  1;
QryMovDia.ParamByName('COD_FILIAL').AsInteger     :=  1;
QryMovDia.ParamByName('DATAMOVIMENTO').AsDate    :=  StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(TXTDATA.Text)));
QryMovDia.ParamByName('TOTAL').AsFloat           :=  StrToFloat(TXTTOTAL.Text);
QryMovDia.ParamByName('DINHEIRO').AsFloat        :=  StrToFloat(TXTDINHEIRO.Text);
QryMovDia.ParamByName('CHEQUE').AsFloat          :=  StrToFloat(TXTCHEQUE.Text);
QryMovDia.ParamByName('CARTAO_CREDITO').AsFloat  :=  StrToFloat(TXTCREDITO.Text);
QryMovDia.ParamByName('CARTAO_DEBITO').AsFloat   :=  StrToFloat(TXTDEBITO.Text);
QryMovDia.ParamByName('PROMISSORIA').AsFloat     :=  StrToFloat(TXTPROMISSORIA.Text);
 TRY
  QryMovDia.ExecSQL;
  QryMovDia.Transaction.Commit;


  if (StrToFloat(TXTDINHEIRO.Text) > 0 )THEN
    BEGIN
      QryTiTUlos.SQL.Clear;
      QryTiTUlos.SQL.Add('  insert into titulospagar2 ');
      QryTiTUlos.SQL.Add('    (PARCELA, DTVENCIMENTO, DTLANCAMENTO, ');
      QryTiTUlos.SQL.Add('     STATUS, TPSITUACAO, VLPARCELA,TIPO_TITULO, ');
      QryTiTUlos.SQL.Add('     COD_EMPRESA, COD_FILIAL,COD_TITULO,COD_CONTA,OPER_TITULO,FORNECEDOR,ID_MOV_DIARIO) ');
      QryTiTUlos.SQL.Add('  values ');
      QryTiTUlos.SQL.Add('    (:PARCELA, :DTVENCIMENTO, :DTLANCAMENTO, ');
      QryTiTUlos.SQL.Add('     :STATUS, :TPSITUACAO, :VLPARCELA, ');
      QryTiTUlos.SQL.Add('     :TIPO_TITULO, :COD_EMPRESA, :COD_FILIAL, :COD_TITULO,:COD_CONTA,:OPER_TITULO,:FORNECEDOR,:ID_MOV_DIARIO) ');                                             //  FormatDateTime('DD/MM/YYYY',)
      QryTiTUlos.ParamByName('PARCELA').AsInteger := 1 ;
      QryTiTUlos.ParamByName('DTVENCIMENTO').AsDate  :=  StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(TXTDATA.Text)));
      QryTiTUlos.ParamByName('DTLANCAMENTO').AsDate :=   StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(TXTDATA.Text)));
      QryTiTUlos.ParamByName('STATUS').AsString := 'B';
      QryTiTUlos.ParamByName('TPSITUACAO').AsString := 'F';
      QryTiTUlos.ParamByName('VLPARCELA').AsFloat := StrToFloat(TXTDINHEIRO.Text);
      QryTiTUlos.ParamByName('TIPO_TITULO').AsInteger := 4 ;
      QryTiTUlos.ParamByName('COD_FILIAL').AsInteger := StrToInt(TXTFILIAL.Text);
      QryTiTUlos.ParamByName('COD_EMPRESA').AsInteger := StrToInt(TXTEMPRESA.Text);
      QryTiTUlos.ParamByName('COD_TITULO').AsInteger := 1;
      QryTiTUlos.ParamByName('COD_CONTA').AsInteger := StrToInt(txtcontadin.Text);
      QryTiTUlos.ParamByName('OPER_TITULO').AsString := 'R';
      QryTiTUlos.ParamByName('FORNECEDOR').AsInteger := 102;
      QryTiTUlos.ParamByName('ID_MOV_DIARIO').AsInteger :=  StrToINT(ActResultEdit1.Text);
      QryTiTUlos.ExecSQL;
      QryTiTUlos.Transaction.Commit;
     GravarALMENTOConta(StrToFloat(TXTDINHEIRO.Text),StrToInt(txtcontadin.Text));

    end;



    if (StrToFloat(TXTDEBITO.Text) > 0 )THEN
      BEGIN
        IBQuery3.SQL.Clear;
        IBQuery3.SQL.Add('  insert into titulospagar2 ');
        IBQuery3.SQL.Add('    (PARCELA, DTVENCIMENTO, DTLANCAMENTO, ');
        IBQuery3.SQL.Add('     STATUS, TPSITUACAO, VLPARCELA,TIPO_TITULO, ');
        IBQuery3.SQL.Add('     COD_EMPRESA, COD_FILIAL,COD_TITULO,COD_CONTA,OPER_TITULO,FORNECEDOR,ID_MOV_DIARIO) ');
        IBQuery3.SQL.Add('  values ');
        IBQuery3.SQL.Add('    (:PARCELA, :DTVENCIMENTO, :DTLANCAMENTO, ');
        IBQuery3.SQL.Add('     :STATUS, :TPSITUACAO, :VLPARCELA, ');
        IBQuery3.SQL.Add('     :TIPO_TITULO, :COD_EMPRESA, :COD_FILIAL, :COD_TITULO,:COD_CONTA,:OPER_TITULO,:FORNECEDOR,:ID_MOV_DIARIO) ');
        IBQuery3.ParamByName('PARCELA').AsInteger := 1 ;
        IBQuery3.ParamByName('DTVENCIMENTO').AsDate  := StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(TXTDATA.Text)));
        IBQuery3.ParamByName('DTLANCAMENTO').AsDate :=  StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(TXTDATA.Text)));
        IBQuery3.ParamByName('STATUS').AsString := 'B';
        IBQuery3.ParamByName('TPSITUACAO').AsString := 'F';
        IBQuery3.ParamByName('VLPARCELA').AsFloat := StrToFloat(TXTDEBITO.Text);
        IBQuery3.ParamByName('TIPO_TITULO').AsInteger := 5 ;
        IBQuery3.ParamByName('COD_FILIAL').AsInteger := StrToInt(TXTFILIAL.Text);
        IBQuery3.ParamByName('COD_EMPRESA').AsInteger := StrToInt(TXTEMPRESA.Text);
        IBQuery3.ParamByName('COD_TITULO').AsInteger := 1;
        IBQuery3.ParamByName('COD_CONTA').AsInteger := StrToInt(txtcontacre.Text);
        IBQuery3.ParamByName('OPER_TITULO').AsString := 'R';
        IBQuery3.ParamByName('FORNECEDOR').AsInteger := 102;
        IBQuery3.ParamByName('ID_MOV_DIARIO').AsInteger:=  StrToINT(ActResultEdit1.Text);
        IBQuery3.ExecSQL;
        IBQuery3.Transaction.Commit;
          GravarALMENTOConta(StrToFloat(TXTDEBITO.Text),StrToInt(txtcontadeb.Text));

      end;
      if (StrToFloat(TXTCREDITO.Text) > 0 )THEN
        BEGIN
          IBQuery4.SQL.Clear;
          IBQuery4.SQL.Add('  insert into titulospagar2 ');
          IBQuery4.SQL.Add('    (PARCELA, DTVENCIMENTO, DTLANCAMENTO, ');
          IBQuery4.SQL.Add('     STATUS, TPSITUACAO, VLPARCELA,TIPO_TITULO, ');
          IBQuery4.SQL.Add('     COD_EMPRESA, COD_FILIAL,COD_TITULO,COD_CONTA,OPER_TITULO,FORNECEDOR,ID_MOV_DIARIO) ');
          IBQuery4.SQL.Add('  values ');
          IBQuery4.SQL.Add('    (:PARCELA, :DTVENCIMENTO, :DTLANCAMENTO, ');
          IBQuery4.SQL.Add('     :STATUS, :TPSITUACAO, :VLPARCELA, ');
          IBQuery4.SQL.Add('     :TIPO_TITULO, :COD_EMPRESA, :COD_FILIAL, :COD_TITULO,:COD_CONTA,:OPER_TITULO,:FORNECEDOR,:ID_MOV_DIARIO) ');
          IBQuery4.ParamByName('PARCELA').AsInteger := 1 ;
          IBQuery4.ParamByName('DTVENCIMENTO').AsDate  := StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(txtdtprovpag.Text)));
          IBQuery4.ParamByName('DTLANCAMENTO').AsDate :=  StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(TXTDATA.Text)));
          IBQuery4.ParamByName('STATUS').AsString := 'A';
          IBQuery4.ParamByName('TPSITUACAO').AsString := 'F';
          IBQuery4.ParamByName('VLPARCELA').AsFloat := StrToFloat(TXTCREDITO.Text);
          IBQuery4.ParamByName('TIPO_TITULO').AsInteger := 6 ;
          IBQuery4.ParamByName('COD_FILIAL').AsInteger := StrToInt(TXTFILIAL.Text);
          IBQuery4.ParamByName('COD_EMPRESA').AsInteger := StrToInt(TXTEMPRESA.Text);
          IBQuery4.ParamByName('COD_TITULO').AsInteger := 1;
          IBQuery4.ParamByName('COD_CONTA').AsInteger := StrToInt(txtcontadeb.Text);
          IBQuery4.ParamByName('OPER_TITULO').AsString := 'R';
          IBQuery4.ParamByName('FORNECEDOR').AsInteger := 102;
          IBQuery4.ParamByName('ID_MOV_DIARIO').AsInteger:=  StrToINT(ActResultEdit1.Text);
          IBQuery4.ExecSQL;
          IBQuery4.Transaction.Commit;

        end;
   showmessage('MOVIMENTO GRAVADO COM SUCESSO');
Except
  QryMovDia.Transaction.Rollback;
  QryTiTUlos.Transaction.Rollback;
  IBQuery3.Transaction.Rollback;
  IBQuery4.Transaction.Rollback;
  showmessage('ERRO NA GRAVAÇÃO DO MOVIMENTO');
end;
    SELECTINICIAL();
    POPULATXT();


END;

procedure TForm1.TXTDINHEIROExit(Sender: TObject);
begin
SOMA := SOMA + StrTofLOAT(TXTDINHEIRO.Text) ;
TXTTOTAL.Text := FloatToStr(SOMA);
end;

procedure TForm1.TXTCHEQUEExit(Sender: TObject);
begin
SOMA := SOMA + StrTofLOAT(TXTCHEQUE.Text) ;
TXTTOTAL.Text := FloatToStr(SOMA);
end;

procedure TForm1.TXTPROMISSORIAExit(Sender: TObject);
begin
SOMA := SOMA + StrTofLOAT(TXTPROMISSORIA.Text) ;
TXTTOTAL.Text := FloatToStr(SOMA);
end;

procedure TForm1.TXTDEBITOExit(Sender: TObject);
begin
SOMA := SOMA + StrTofLOAT(TXTDEBITO.Text) ;
TXTTOTAL.Text := FloatToStr(SOMA);
end;

procedure TForm1.TXTCREDITOExit(Sender: TObject);
begin
SOMA:= SOMA + StrTofLOAT(TXTCREDITO.Text) ;
TXTTOTAL.Text := FloatToStr(SOMA);
end;

procedure TForm1.SpeedButton4Click(Sender: TObject);
begin
if MessageDlg('DESEJA REALMENTE EXCLUIR TITULO ', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
  begin
    QryMovDia.SQL.Clear;
    QryMovDia.SQL.Add('UPDATE MOVIMENTODIARIO set STATUS = ''C''  WHERE ID = '+ActResultEdit1.Text );
    QryTiTUlos.SQL.Clear;
    QryTiTUlos.SQL.Add('update titulospagar2 set STATUS = ''E'' where ID_MOV_DIARIO = '+ActResultEdit1.Text );
 try
  QryMovDia.ExecSQL;
  QryMovDia.Transaction.Commit;
  QryTiTUlos.ExecSQL;
  QryTiTUlos.Transaction.Commit;
 Except
  QryMovDia.Transaction.Rollback;
  QryTiTUlos.Transaction.Rollback;
 end
end;
    SELECTINICIAL();
    POPULATXT();
end;

procedure TForm1.SpeedButton2Click(Sender: TObject);
var max :Integer;
begin
IBQuery2.Active := false;
IBQuery2.SQL.Text := 'select max(id)+ 1  from movimentodiario';
IBQuery2.Active := true;
max := IBQuery2.fieldbyname('F_1').AsInteger;
ActResultEdit1.Text := IntToStr(max);
ActResultEdit1.Enabled := true ;
TXTEMPRESA.Enabled := true ;
TXTFILIAL.Enabled := true ;
TXTDATA.Enabled := true ;
TXTTOTAL.Enabled := true ;
TXTDINHEIRO.Enabled := true ;
TXTCHEQUE.Enabled := true ;
TXTCREDITO.Enabled := true ;
TXTDEBITO.Enabled := true ;
TXTPROMISSORIA.Enabled := true ;



TXTEMPRESA.Text := '' ;
TXTFILIAL.Text := '' ;
TXTDATA.Text := '' ;
TXTTOTAL.Text := '' ;
TXTDINHEIRO.Text := '' ;
TXTCHEQUE.Text := '0' ;
TXTCREDITO.Text := '' ;
TXTDEBITO.Text := '' ;
TXTPROMISSORIA.Text := '0' ;

end;

procedure TForm1.SpeedButton7Click(Sender: TObject);
begin
Primeiro;
POPULATXT;
end;

procedure TForm1.SpeedButton8Click(Sender: TObject);
begin
ultimo;
POPULATXT;
end;

procedure TForm1.SpeedButton5Click(Sender: TObject);
begin
proximo;
POPULATXT;
end;

procedure TForm1.SpeedButton6Click(Sender: TObject);
begin
anterior;
POPULATXT;
end;

procedure TForm1.FormActivate(Sender: TObject);
begin
SELECTINICIAL();
POPULATXT();

ActResultEdit1.Enabled := False ;
TXTEMPRESA.Enabled := False ;
TXTFILIAL.Enabled := False ;
TXTDATA.Enabled := False ;
TXTTOTAL.Enabled := False ;
TXTDINHEIRO.Enabled := False ;
TXTCHEQUE.Enabled := False ;
TXTCREDITO.Enabled := False ;
TXTDEBITO.Enabled := False ;
TXTPROMISSORIA.Enabled := False ;


end;

procedure TForm1.Button2Click(Sender: TObject);
begin
form6.Show;
end;

procedure TForm1.Button3Click(Sender: TObject);
begin
form7.Show;
end;

procedure TForm1.Button4Click(Sender: TObject);
begin
Form8.Show;
end;

end.
