unit Unit82;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DBTables, DB, ADODB, ComCtrls, StdCtrls, Buttons, Mask;

type
  TForm82 = class(TForm)
    Button2: TButton;
    ProgressBar1: TProgressBar;
    Button3: TButton;
    OpenDialog1: TOpenDialog;
    Database1: TDatabase;
    Table1: TTable;
    Table2: TTable;
    Table3: TTable;
    Table3Tipo: TStringField;
    Table3Car: TStringField;
    Table3NOSERIE: TStringField;
    Table3Modelo: TStringField;
    Table3Coo: TFloatField;
    Table3Noitem: TFloatField;
    Table3Quantidade: TFloatField;
    Table3Vlproduto: TFloatField;
    Table3BaseICMS: TFloatField;
    Table3Aliquota: TFloatField;
    Table3Vlicms: TFloatField;
    Table3Id: TAutoIncField;
    Table3Cdproduto: TStringField;
    Table3Status: TStringField;
    Table4: TTable;
    Table4Id: TAutoIncField;
    Table4Produto: TStringField;
    Table4Codsin: TStringField;
    Table4Codpro: TFloatField;
    Query1: TQuery;
    Query2: TQuery;
    Query3: TQuery;
    Query4: TQuery;
    Table3DTMOVIMENTO: TDateField;
    Table3Codpro: TFloatField;
    Table5: TTable;
    Table5Id: TAutoIncField;
    Table5Mesano: TStringField;
    Table5Cdproduto: TStringField;
    Table5Quantidade: TFloatField;
    Table5Valor: TFloatField;
    Table5Baseicms: TFloatField;
    Table5Aliquota: TFloatField;
    Table6: TTable;
    Table6DTEMISSAO: TDateField;
    Table6NOSERIE: TStringField;
    Table6Valor: TFloatField;
    Table6Aliguota: TStringField;
    Table2Tipo: TStringField;
    Table2Car: TStringField;
    Table2Dtmovimento: TDateField;
    Table2Noserie: TStringField;
    Table2Noecf: TFloatField;
    Table2Modecf: TStringField;
    Table2Cooinicial: TFloatField;
    Table2Coofinal: TFloatField;
    Table2Crz: TFloatField;
    Table2Cro: TFloatField;
    Table2Vendabruta: TFloatField;
    Table2Totalgeral: TFloatField;
    Table1Car: TStringField;
    Table1Dtmovimento: TDateField;
    Table1Noserie: TStringField;
    Table1Cdproduto: TFloatField;
    Table1Quantidade: TFloatField;
    Table1Vltotal: TCurrencyField;
    Table1Baseicms: TCurrencyField;
    Table1Aliquota: TFloatField;
    Table1Vltotalicms: TCurrencyField;
    Label1: TLabel;
    MaskEdit1: TMaskEdit;
    MaskEdit2: TMaskEdit;
    BitBtn1: TBitBtn;
    procedure Button2Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure BitBtn1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form82: TForm82;

implementation

uses Unit1, Unit2;

{$R *.dfm}

procedure TForm82.Button2Click(Sender: TObject);
var
ano,mes,dia :string;
  datas : string;
  data : Tdatetime;
txt: TStrings;
a,c, linha, str1, str2,aux,aux2,status: String;
b :TextFile;
cont,medidor,x,y : integer;
ref :string;
begin
OpenDialog1.Execute;
AssignFile(b,OpenDialog1.FileName);
cont := 0;
x:= 0;
Reset(b);
txt := TStringList.Create;
txt.LoadFromFile(OpenDialog1.FileName);
medidor := txt.Count;
ProgressBar1.Min := 0;
ProgressBar1.Max := medidor+10;
cont := 0;
Status := 'A';
while not  Eof(b)do
 begin
a:=txt[cont];
aux := Copy(a,0, 2);
            if(aux = '50') then
            begin
               {ADOQuery1.Parameters.ParamByName('tipo').Value := aux;
               aux2 := Copy(a,2,14);
               ADOQuery1.Parameters.ParamByName('cnpj').Value := aux2;
               aux2 := Copy(a,16,14);
               ADOQuery1.Parameters.ParamByName('IE').Value := aux2;
               aux2 := Copy(a,31,8);
               ADOQuery1.Parameters.ParamByName('DATA').Value := aux2;
               aux2 := Copy(a,39,2);
               ADOQuery1.Parameters.ParamByName('UF').Value := aux2;
               aux2 := Copy(a,41,2);
               ADOQuery1.Parameters.ParamByName('modelo').Value := aux2;
               aux2 := Copy(a,43,3);
               ADOQuery1.Parameters.ParamByName('serie').Value := aux2;
               aux2 := Copy(a,46,6);
               ADOQuery1.Parameters.ParamByName('numero').Value := aux2;
               aux2 := Copy(a,52,4);
               ADOQuery1.Parameters.ParamByName('cfop').Value := aux2;
               aux2 := Copy(a,56,1);
               ADOQuery1.Parameters.ParamByName('Emitente').Value := aux2;
               aux2 := Copy(a,57,13);
               ADOQuery1.Parameters.ParamByName('valortotal').Value := aux2;
               aux2 := Copy(a,70,13);
               ADOQuery1.Parameters.ParamByName('basecalculoicms').Value := aux2;
               aux2 := Copy(a,83,13);
               ADOQuery1.Parameters.ParamByName('valordoicms').Value := aux2;
               aux2 := Copy(a,96,13);
               ADOQuery1.Parameters.ParamByName('isentas').Value := aux2;
               aux2 := Copy(a,109,13);
               ADOQuery1.Parameters.ParamByName('outras').Value := aux2;
               aux2 := Copy(a,122,4);
               ADOQuery1.Parameters.ParamByName('aliquota').Value := aux2;
               aux2 := Copy(a,126,1);
               ADOQuery1.Parameters.ParamByName('situacao').Value := aux2;
               ADOQuery1.ExecSQL; }                                   //       ShowMessage('situação '+aux2);
            end;
              if(aux = '54') then
              begin

              // ADOQuery2.Parameters.ParamByName('tipo').Value := aux;
               aux2 := Copy(a,1,14);
             //  ADOQuery2.Parameters.ParamByName('cnpj').Value := aux2;
               aux2 := Copy(a,15,2);
             //  ADOQuery2.Parameters.ParamByName('MODELO').Value := aux2;
               aux2 := Copy(a,17,3);
             //  ADOQuery2.Parameters.ParamByName('serie').Value := aux2;
               aux2 := Copy(a,20,6);
             //  ADOQuery2.Parameters.ParamByName('numero').Value := aux2;
               aux2 := Copy(a,26,4);
            //   ADOQuery2.Parameters.ParamByName('cfop').Value := aux2;
               aux2 := Copy(a,30,3);
           //    ADOQuery2.Parameters.ParamByName('cst').Value := aux2;
               aux2 := Copy(a,33,3);
           //    ADOQuery2.Parameters.ParamByName('NumeroItem').Value := aux2;
               aux2 := Copy(a,36,14);
           //    ADOQuery2.Parameters.ParamByName('cdproduto').Value := aux2;
               aux2 := Copy(a,50,11);
          //    ADOQuery2.Parameters.ParamByName('quantidade').Value := aux2;
               aux2 := Copy(a,61,12);
          //     ADOQuery2.Parameters.ParamByName('valorProduto').Value := aux2;
               aux2 := Copy(a,73,12);
          //     ADOQuery2.Parameters.ParamByName('ValorDesconto').Value := aux2;
               aux2 := Copy(a,85,12);
          //     ADOQuery2.Parameters.ParamByName('valorBaseCalcICMS').Value := aux2;
               aux2 := Copy(a,97,12);
          //     ADOQuery2.Parameters.ParamByName('valorBaseCalcSt').Value := aux2;
               aux2 := Copy(a,109,12);
         //      ADOQuery2.Parameters.ParamByName('valorIPI').Value := aux2;
               aux2 := Copy(a,121,4);
         //      ADOQuery2.Parameters.ParamByName('aliquota').Value := aux2;
         //      ADOQuery2.ExecSQL;
              end;
              if(aux = '53') then
              begin
               //  ShowMessage('Linha 53');
              end;
              if (aux = '75')then

                begin
                  Label1.Caption := 'gerando  Registro 75...';
                  table4.Insert;
                  aux2 := Copy(a,3,8);
                  aux2 := Copy(a,11,8);
                  aux2 := Copy(a,19,14);
                //  Table4Codsin.AsString := aux2;
                  //===========================
                  query1.Active := false;
                  query1.SQL.Text := 'select * from prodsinte where Codsin like ''%'+aux2+'%''';
                  query1.Active := true;
                  If (query1.IsEmpty) Then
                  begin
                   Table4Codsin.AsString := aux2;
                  //===========================
                    ref  := aux2;
                    aux2 := Copy(a,33,8);
                    aux2 := Copy(a,41,53);
                    Table4Produto.AsString := aux2;
                    aux2 := Copy(a,41,2);
                    query1.Active := false;
                    query1.SQL.Text := 'select * from produtos where ref like ''%'+ref+'%'' and produto like '''+aux2+'%''';
                    query1.Active := true;
                    Table4Codpro.AsInteger := query1.fieldbyname('codpro').AsInteger;
                    table4.Post;
                  end;
                  //==========================


                  aux2 := Copy(a,94,6);
            //    ADOQuery3.Parameters.ParamByName('UniMed').Value := aux2;
                  aux2 := Copy(a,100,5);
            //    ADOQuery3.Parameters.ParamByName('IPI').Value := aux2;
                  aux2 := Copy(a,105,4);
           //     ADOQuery3.Parameters.ParamByName('ICMS').Value := aux2;
                  aux2 := Copy(a,109,5);
           //     ADOQuery3.Parameters.ParamByName('baseicms').Value := aux2;
                  aux2 := Copy(a,114,13);
           //     ADOQuery3.Parameters.ParamByName('basest').Value := aux2;

           //     ADOQuery3.ExecSQL;
                end;
                if(aux = '60')then
                begin
                 Label1.Caption := 'gerando  Registro 60M...';
                 aux := Copy(a,3, 1);
                  if(aux = 'M' )then
                  begin
                    table2.Insert;
                    Table2TIPO.AsString := '60';
                    Table2CAR.AsString := aux;
                    aux2 := Copy(a,4,8);
                    Table2DTMOVIMENTO.AsDateTime := StrToDate(datamodule2.ajustadata(aux2));
                    aux2 := Copy(a,12,20);
                    Table2NOSERIE.AsString := aux2;
                    aux2 := Copy(a,32,3);
                    Table2NOECF.AsFloat := StrToFloat(aux2);
                    aux2 := Copy(a,35,2);
                    Table2MODECF.AsString := aux2;
                    aux2 := Copy(a,37,6);
                    Table2COOINICIAL.AsFloat := StrToFloat(aux2)/100;
                    aux2 := Copy(a,43,6);
                    Table2COOFINAL.AsFloat := StrToFloat(aux2)/100;
                    aux2 := Copy(a,49,6);
                    Table2CRZ.AsFloat := StrToFloat(aux2)/100 ;
                    aux2 := Copy(a,55,3);
                    Table2CRO.AsFloat := StrToFloat(aux2)/100;
                    aux2 := Copy(a,58,16);
                    Table2VENDABRUTA.AsFloat := StrToFloat(aux2)/100;
                    aux2 := Copy(a,74,16);
                    Table2TOTALGERAL.AsFloat := StrToFloat(aux2)/100;
                    table2.Post;
                  end;
                  if(aux = 'A' )then
                  begin
                    table6.Insert;
                    Label1.Caption := 'gerando  Registro 60A...';
                    aux2 := Copy(a,4,8);
                    Table6Dtemissao.AsDateTime := StrToDate(datamodule2.ajustadata(aux2));
                    aux2 := Copy(a,12,20);
                    Table6Noserie.AsString := aux2;
                    aux2 := Copy(a,32,4);
                    Table6Aliguota.AsString := aux2;
                    aux2 := Copy(a,36,6);
                    Table6Valor.AsFloat :=  StrToFloat(aux2)/100;
                    table6.Post;
                  end;
                  if(aux = 'D' )then
                  begin
                    Label1.Caption := 'gerando  Registro 60D...';
                    table1.Insert;
                    Table1Car.AsString := aux;
                    aux2 := Copy(a,4,8);
                    Table1Dtmovimento.AsDateTime := StrToDate(datamodule2.ajustadata(aux2));
                    aux2 := Copy(a,12,20);
                    Table1Noserie.AsString := aux2;
                    aux2 := Copy(a,36,10);
                    Table1Cdproduto.AsString:= aux2;
                    aux2 := Copy(a,46,13);
                    Table1Quantidade.AsFloat := StrToFloat(aux2)/1000;
                    aux2 := Copy(a,59,16);
                    Table1Vltotal.AsCurrency := StrToCurr(aux2)/100;
                    aux2 := Copy(a,75,16);
                    Table1Baseicms.AsCurrency := StrToCurr(aux2)/100;
                    aux2 := Copy(a,91,4);
                    Table1Aliquota.Asinteger := StrToInt(aux2);
                    aux2 := Copy(a,95,13);
                    Table1Vltotalicms.AsCurrency := StrToCurr(aux2)/100;

                  end;
                  if(aux = 'I' )then
                  begin
                    Label1.Caption := 'gerando  Registro 60I...';
                    table3.Insert;
                    Table3Tipo.AsString := '60';
                    Table3Car.AsString := aux;
                    aux2 := Copy(a,4,8);
                    Table3DTMOVIMENTO.AsDateTime := StrToDate(datamodule2.ajustadata(aux2));
                    aux2 := Copy(a,12,20);
                    Table3NOSERIE.AsString := aux2;
                    aux2 := Copy(a,32,2);
                    Table3Modelo.AsString := aux2;
                    aux2 := Copy(a,34,6);
                    Table3Coo.AsInteger := StrToInt(aux2);
                    aux2 := Copy(a,40,3);
                    Table3Noitem.AsInteger := StrToInt(aux2);
                    aux2 := Copy(a,43,14);
                    Table3Cdproduto.AsString := aux2;
                    aux2 := Copy(a,57,13);
                    Table3Quantidade.Asfloat := (StrTofloat(aux2)/1000);
                    aux2 := Copy(a,70,13);
                    Table3Vlproduto.Asfloat := (StrTofloat(aux2)/100);
                    aux2 := Copy(a,83,12);
                    Table3BaseICMS.Asfloat := (StrTofloat(aux2)/100);
                    aux2 := Copy(a,95,4);
                    Table3Aliquota.AsInteger := StrToInt(aux2);
                    aux2 := Copy(a,99,11);
                    Table3Vlicms.Asfloat := (StrTofloat(aux2)/100);
                    Table3Status.AsString := Status;
                    table3.Post;
                  end;
                   if(aux = 'R' )then
                  begin
                  Label1.Caption := 'gerando  Registro 60R...';
                  Table5.Insert;
                   aux2 := Copy(a,4,6);
                  Table5Mesano.AsString    := aux2;
                   aux2 := Copy(a,10,14);
                  Table5Cdproduto.AsString := aux2;
                   aux2 := Copy(a,24,13);
                  Table5Quantidade.asfloat := StrToFloat(aux2)/1000;
                   aux2 := Copy(a,37,16);
                  Table5Valor.AsFloat      := StrToFloat(aux2)/100;
                   aux2 := Copy(a,53,16);
                  Table5Baseicms.AsFloat   := StrToFloat(aux2)/100;
                   aux2 := Copy(a,69,4);
                  Table5Aliquota.Asfloat := StrToFloat(aux2)/100;
                  Table5.post;



                 end

                 end;
      cont := cont + 1;
      ProgressBar1.Position := cont;
      next;
     end;

     Readln(b, linha);

CloseFile(b);

end;

procedure TForm82.Button3Click(Sender: TObject);

var
 cont :Integer;
begin
  // try
   query2.SQL.Text :=' select *from nfsaidas2 n, prodsinte p where n.cdproduto = p.codsin and n.status = ''A'' ';
   query2.Active := true;
   ProgressBar1.Min := 0;
   ProgressBar1.Max :=  query2.RecordCount;
   cont := 0;
   while (not query2.eof) do
   begin
     DataModule2.baixarestoque(query2.fieldbyname('codpro_1').AsInteger,'0',query2.fieldbyname('quantidade').Asfloat);
     query4.SQL.Text := 'update nfsaidas2 set status = ''P'',codpro = '+IntToStr(query2.fieldbyname('codpro_1').AsInteger)+' where cdproduto = '''+query2.fieldbyname('cdproduto').AsString+'''';
     query4.ExecSQL;
     Label1.Caption := 'Total/registro '+IntToStr(query2.RecordCount)+' / '+IntToStr(cont)+' ';
     ProgressBar1.Position := cont;
     inc(cont);
     query2.Next;
   end;
end;

procedure TForm82.Button1Click(Sender: TObject);
begin
query4.SQL.Text := 'delete from nfsaidas2';
query4.ExecSQL;
query4.SQL.Text := 'delete from prodsinte';
query4.ExecSQL;
query4.SQL.Text := 'delete from R60D';
query4.ExecSQL;
query4.SQL.Text := 'delete from REDLEITURA';
query4.ExecSQL;
query4.ExecSQL;
query4.SQL.Text := 'delete from r60A';
query4.ExecSQL;
query4.ExecSQL;
query4.SQL.Text := 'delete from r60R';
query4.ExecSQL;
//query4.ExecSQL;
//query4.SQL.Text := 'delete from r60I';
//query4.ExecSQL;
end;

procedure TForm82.BitBtn1Click(Sender: TObject);
begin
query4.SQL.Text := 'delete from nfsaidas2 where dtmovimento between '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ '''';
query4.ExecSQL;
query4.SQL.Text := 'delete from prodsinte';
query4.ExecSQL;
query4.SQL.Text := 'delete from R60D where dtmovimento between '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ '''';
query4.ExecSQL;
query4.SQL.Text := 'delete from REDLEITURA where dtmovimento between '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ '''';
query4.ExecSQL;
query4.ExecSQL;
query4.SQL.Text := 'delete from r60A where dtemissao between '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ '''';
query4.ExecSQL;
query4.ExecSQL;
query4.SQL.Text := 'delete from r60R ';
query4.ExecSQL;
{query4.ExecSQL;
query4.SQL.Text := 'delete from r60I where dtmovimento between '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit1.text)) +''' AND '''+FormatDateTime('mm/DD/yyyy',StrToDate(MaskEdit2.text))+ '''';
query4.ExecSQL; }
end;

end.
