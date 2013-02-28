unit UAltPrec;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Buttons, ActButton, ActNumberEdit, ActResultEdit,
  ExtCtrls, ActCustomEdit, ActCurrencyEdit, DB, IBCustomDataSet, IBQuery;

type
  TFrmAltPreco = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    GroupBox1: TGroupBox;
    GroupBox2: TGroupBox;
    GroupBox3: TGroupBox;
    GroupBox4: TGroupBox;
    GroupBox5: TGroupBox;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    RadioButton3: TRadioButton;
    TXTMARGEM: TActCurrencyEdit;
    ActResultEdit1: TActResultEdit;
    ActResultEdit2: TActResultEdit;
    ActResultEdit3: TActResultEdit;
    ActResultEdit4: TActResultEdit;
    ActButton1: TActButton;
    IBQuery1: TIBQuery;
    RadioButton4: TRadioButton;
    RadioButton5: TRadioButton;
    RadioButton6: TRadioButton;
    RadioButton7: TRadioButton;
    IBQuery2: TIBQuery;
    IBQuery3: TIBQuery;
    procedure ActButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmAltPreco: TFrmAltPreco;

implementation

uses Unit1;

{$R *.dfm}

procedure TFrmAltPreco.ActButton1Click(Sender: TObject);
var NovaMargem,NovoPreco :Double;
begin
  if (RadioButton4.Checked = True) then
  begin
    IBQuery2.Active := false;
    IBQuery2.SQL.Clear;
    IBQuery2.SQL.Add('select *from Prod_Serv p where p.grupo = '+ ActResultEdit1.Text );
    IBQuery2.Active := True;
   if(RadioButton1.Checked = True) then
     begin
      while (not IBQuery2.eof)do
      begin
        IBQuery3.SQL.Clear;
        IBQuery3.SQL.Add('update Prod_Serv p set p.margempr = p.margempr + :margempr,p.precovenda =  ((( p.margempr + :margempr)* p.vlaquis )/100)  where Cod_produto = '+ IntToStr(IBQuery2.FieldByName('COD_PRODUTO').asinteger));
        IBQuery3.ParamByName(':margempr').AsFloat := StrToFloat(TXTMARGEM.Text);
        IBQuery3.ExecSQL;
        IBQuery3.Transaction.Commit;
        IBQuery2.Next;
      end;
     end;


if(RadioButton2.Checked = True) then
begin

while (not IBQuery2.eof)do
begin
 IBQuery3.SQL.Clear;
 IBQuery3.SQL.Add('update Prod_Serv p set p.margempr = p.margempr - :margempr,p.precovenda =  ((( p.margempr - :margempr)* p.vlaquis )/100)  where Cod_produto = '+ IntToStr(IBQuery2.FieldByName('COD_PRODUTO').asinteger));
 IBQuery3.ParamByName(':margempr').AsFloat := StrToFloat(TXTMARGEM.Text);
 IBQuery3.ExecSQL;
 IBQuery3.Transaction.Commit;
IBQuery2.Next;
end;
end;

if(RadioButton3.Checked = True) then
begin
while (not IBQuery2.eof)do
begin
 IBQuery3.SQL.Clear;
 IBQuery3.SQL.Add('update Prod_Serv p set p.margempr = :margempr,p.precovenda =  ((( :margempr)* p.vlaquis )/100)  where Cod_produto = '+ IntToStr(IBQuery2.FieldByName('COD_PRODUTO').asinteger));
 IBQuery3.ParamByName(':margempr').AsFloat := StrToFloat(TXTMARGEM.Text);
 IBQuery3.ExecSQL;
 IBQuery3.Transaction.Commit;
IBQuery2.Next;
end;
end;
end;
if (RadioButton5.Checked = True) then
begin
IBQuery2.Active := false;
IBQuery2.SQL.Clear;
IBQuery2.SQL.Add('select *from Prod_Serv p where p.SUB_grupo = '+ ActResultEdit2.Text );
IBQuery2.Active := True;
   if(RadioButton1.Checked = True) then
     begin
      while (not IBQuery2.eof)do
      begin
        IBQuery3.SQL.Clear;
        IBQuery3.SQL.Add('update Prod_Serv p set p.margempr = p.margempr + :margempr,p.precovenda =  ((( p.margempr + :margempr)* p.vlaquis )/100)  where Cod_produto = '+ IntToStr(IBQuery2.FieldByName('COD_PRODUTO').asinteger));
        IBQuery3.ParamByName(':margempr').AsFloat := StrToFloat(TXTMARGEM.Text);
        IBQuery3.ExecSQL;
        IBQuery3.Transaction.Commit;
        IBQuery2.Next;
      end;
     end;


if(RadioButton2.Checked = True) then
begin

while (not IBQuery2.eof)do
begin
 IBQuery3.SQL.Clear;
 IBQuery3.SQL.Add('update Prod_Serv p set p.margempr = p.margempr - :margempr,p.precovenda =  ((( p.margempr - :margempr)* p.vlaquis )/100)  where Cod_produto = '+ IntToStr(IBQuery2.FieldByName('COD_PRODUTO').asinteger));
 IBQuery3.ParamByName(':margempr').AsFloat := StrToFloat(TXTMARGEM.Text);
 IBQuery3.ExecSQL;
 IBQuery3.Transaction.Commit;
IBQuery2.Next;
end;
end;

if(RadioButton3.Checked = True) then
begin
while (not IBQuery2.eof)do
begin
 IBQuery3.SQL.Clear;
 IBQuery3.SQL.Add('update Prod_Serv p set p.margempr = :margempr,p.precovenda =  ((( :margempr)* p.vlaquis )/100)  where Cod_produto = '+ IntToStr(IBQuery2.FieldByName('COD_PRODUTO').asinteger));
 IBQuery3.ParamByName(':margempr').AsFloat := StrToFloat(TXTMARGEM.Text);
 IBQuery3.ExecSQL;
 IBQuery3.Transaction.Commit;
IBQuery2.Next;
end;
end;



end;
if (RadioButton6.Checked = True) then
begin
IBQuery2.Active := false;
IBQuery2.SQL.Clear;
IBQuery2.SQL.Add('select *from Prod_Serv p where p.derivacao = '+ ActResultEdit3.Text );
IBQuery2.Active := True;
end;
if (RadioButton7.Checked = True) then
begin
   if(RadioButton1.Checked = True) then
     begin
      while (not IBQuery2.eof)do
      begin
        IBQuery3.SQL.Clear;
        IBQuery3.SQL.Add('update Prod_Serv p set p.margempr = p.margempr + :margempr,p.precovenda =  ((( p.margempr + :margempr)* p.vlaquis )/100)  where Cod_produto = '+ IntToStr(IBQuery2.FieldByName('COD_PRODUTO').asinteger));
        IBQuery3.ParamByName(':margempr').AsFloat := StrToFloat(TXTMARGEM.Text);
        IBQuery3.ExecSQL;
        IBQuery3.Transaction.Commit;
        IBQuery2.Next;
      end;
     end;


if(RadioButton2.Checked = True) then
begin

while (not IBQuery2.eof)do
begin
 IBQuery3.SQL.Clear;
 IBQuery3.SQL.Add('update Prod_Serv p set p.margempr = p.margempr - :margempr,p.precovenda =  ((( p.margempr - :margempr)* p.vlaquis )/100)  where Cod_produto = '+ IntToStr(IBQuery2.FieldByName('COD_PRODUTO').asinteger));
 IBQuery3.ParamByName(':margempr').AsFloat := StrToFloat(TXTMARGEM.Text);
 IBQuery3.ExecSQL;
 IBQuery3.Transaction.Commit;
IBQuery2.Next;
end;
end;

if(RadioButton3.Checked = True) then
begin
while (not IBQuery2.eof)do
begin
 IBQuery3.SQL.Clear;
 IBQuery3.SQL.Add('update Prod_Serv p set p.margempr = :margempr,p.precovenda =  ((( :margempr)* p.vlaquis )/100)  where Cod_produto = '+ IntToStr(IBQuery2.FieldByName('COD_PRODUTO').asinteger));
 IBQuery3.ParamByName(':margempr').AsFloat := StrToFloat(TXTMARGEM.Text);
 IBQuery3.ExecSQL;
 IBQuery3.Transaction.Commit;
IBQuery2.Next;
end;
end;
end;
if(RadioButton7.Checked = True)then
if(RadioButton1.Checked = True)then
begin
 IBQuery3.SQL.Clear;
 IBQuery3.SQL.Add('update Prod_Serv p set p.margempr = p.margempr + :margempr,p.precovenda =  ((( p.margempr + :margempr)* p.vlaquis )/100)  where Cod_produto = '+ ActResultEdit4.Text);
 IBQuery3.ParamByName(':margempr').AsFloat := StrToFloat(TXTMARGEM.Text);
 IBQuery3.ExecSQL;
 IBQuery3.Transaction.Commit;
end;
if(RadioButton2.Checked = True)then
begin
 IBQuery3.SQL.Clear;
 IBQuery3.SQL.Add('update Prod_Serv p set p.margempr = p.margempr - :margempr,p.precovenda =  ((( p.margempr - :margempr)* p.vlaquis )/100)  where Cod_produto = '+ ActResultEdit4.Text);
 IBQuery3.ParamByName(':margempr').AsFloat := StrToFloat(TXTMARGEM.Text);
 IBQuery3.ExecSQL;
 IBQuery3.Transaction.Commit;
end;
if(RadioButton3.Checked = True)then
begin
 IBQuery3.SQL.Clear;
 IBQuery3.SQL.Add('update Prod_Serv p set p.margempr = :margempr,p.precovenda =  ((( :margempr)* p.vlaquis )/100)  where Cod_produto = '+ ActResultEdit4.Text);
 IBQuery3.ParamByName(':margempr').AsFloat := StrToFloat(TXTMARGEM.Text);
 IBQuery3.ExecSQL;
 IBQuery3.Transaction.Commit;
end;


end;

end.
