unit Unit82;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DBTables, DB, ADODB, ComCtrls, StdCtrls, Buttons, Mask, IBQuery,
  IBCustomDataSet, IBTable, IBUpdateSQL;

type
  TForm82 = class(TForm)
    Button2: TButton;
    ProgressBar1: TProgressBar;
    Button3: TButton;
    OpenDialog1: TOpenDialog;
    Label1: TLabel;
    BitBtn1: TBitBtn;
    Table1: TIBTable;
    Table2: TIBTable;
    Table3: TIBTable;
    Table4: TIBTable;
    Table5: TIBTable;
    Table6: TIBTable;
    Query1: TIBQuery;
    Query2: TIBQuery;
    Query3: TIBQuery;
    Query4: TIBQuery;
    Table2TIPO: TIBStringField;
    Table2CAR: TIBStringField;
    Table2NOSERIE: TIBStringField;
    Table2MODECF: TIBStringField;
    Table2COOINICIAL: TFloatField;
    Table2COOFINAL: TFloatField;
    Table2CRZ: TFloatField;
    Table2CRO: TFloatField;
    Table2VENDABRUTA: TFloatField;
    Table2TOTALGERAL: TFloatField;
    Table3DTMOVIMENTO: TDateField;
    Table3NOSERIE: TIBStringField;
    Table3MODELO: TIBStringField;
    Table3COO: TIntegerField;
    Table3NOITEM: TFloatField;
    Table3CDPRODUTO: TIBStringField;
    Table3QUANTIDADE: TFloatField;
    Table3BASEICMS: TFloatField;
    Table3ALIQUOTA: TFloatField;
    Table3VLICMS: TFloatField;
    Table3ID: TIntegerField;
    Table3STATUS: TIBStringField;
    Table3CODPRO: TFloatField;
    Table4ID: TIntegerField;
    Table4PRODUTO: TIBStringField;
    Table4CODSIN: TIBStringField;
    Table4CODPRO: TFloatField;
    Table5ID: TIntegerField;
    Table5MESANO: TIBStringField;
    Table5CDPRODUTO: TIBStringField;
    Table5QUANTIDADE: TFloatField;
    Table5VALOR: TFloatField;
    Table5BASEICMS: TFloatField;
    Table5ALIQUOTA: TFloatField;
    Table6DTEMISSAO: TDateField;
    Table6NOSERIE: TIBStringField;
    Table6ALIGUOTA: TIBStringField;
    Table6VALOR: TFloatField;
    IBUpdateSQL1: TIBUpdateSQL;
    IBTable1: TIBTable;
    IBTable1COO: TIntegerField;
    IBTable1NOITEM: TIntegerField;
    IBTable1QUANTIDADE: TFloatField;
    IBTable1VLPRODUTO: TFloatField;
    IBTable1BASEICMS: TFloatField;
    IBTable1ALIQUOTA: TFloatField;
    IBTable1VLICMS: TFloatField;
    IBTable1CODPRO: TIntegerField;
    Button1: TButton;
    IBTable1CDPRODUTO: TIBStringField;
    Table3VLNOTA: TFloatField;
    Table2DTMOVIMENTO: TDateField;
    IBQuery1: TIBQuery;
    Query5: TIBQuery;
    Query6: TIBQuery;
    Label2: TLabel;
    Label3: TLabel;
    Table3STFISCAL: TIBStringField;
    IBTable1INTEGRADO: TIBStringField;
    IBQuery2: TIBQuery;
    IBQuery3: TIBQuery;
    IBQuery4: TIBQuery;
    IBQuery5: TIBQuery;
    IBQuery6: TIBQuery;
    IBQuery7: TIBQuery;
    DataSource1: TDataSource;
    Table1CAR: TIBStringField;
    Table1DTMOVIMENTO: TDateField;
    Table1NOSERIE: TIBStringField;
    Table1QUANTIDADE: TFloatField;
    Table1VLTOTAL: TFloatField;
    Table1BASEICMS: TFloatField;
    Table1ALIQUOTA: TFloatField;
    Table1VLTOTALICMS: TFloatField;
    Table1CDPRODUTO: TIBStringField;
    Table2NOECF: TIntegerField;
    QryInsert60M: TIBQuery;
    IBUpdateSQL2: TIBUpdateSQL;
    QryReg60A: TIBQuery;
    IBUpdateSQL3: TIBUpdateSQL;
    query8: TIBQuery;
    IBQuery8: TIBQuery;
    IBQuery9: TIBQuery;
  //  Table1CDPRODUTO: TFloatField;
    procedure Button2Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    function somarItens():Double;
    function verifica_coo (coo :Integer) :boolean;
    function verifica_cooItens (coo :Integer;noitens:integer) :boolean;
    function InserirCodPro (ref : String) :Integer;
    function VerificaString (STR :String):String;
    function InseriCodSint(): boolean;
    function ProcuraCodPro(ref : String): Integer;
    function SomarVlIcms(coo,noitem :Integer):Double;
    function somarIcmsNf():boolean;
    function verifica60A(const dtemissao:Tdatetime;noserie:String;aliquota:String):boolean;
    function verifica60D(const dtemissao:Tdatetime;noserie:String;codpro:String):boolean;
    function verifica60R(const coo :integer):boolean;
    function verifica60M(const dtemissao:Tdatetime;noecf:Integer):boolean;
    function verificaCupons(const coo:integer;noitem:integer):boolean;
    function Inserir60m(const tipo,aux,noserie,ModEcf :String;dtmovimento:TdateTime;noecf,CooInicial,CooFinal,Crz,Cro: Integer;VENDABRUTA,TOTALGERAL:double ):boolean;
    function Inserir60A(const tipo,reg,aliquota,noserie :String;dtmovimento:TdateTime;valor :Double):Boolean;
    function InserirReg75(Const PRODUTO, CODSIN : String ;CODPRO:Integer ):boolean;
    function InserirReg60D(const CAR,NOSERIE, CDPRODUTO : String ; DTMOVIMENTO :Tdatetime; QUANTIDADE, VLTOTAL, BASEICMS, ALIQUOTA, VLTOTALICMS :Double):boolean;
    function InserirReg60I(const CAR,NOSERIE,MODELO,STATUS,STFISCAL : String ; DTMOVIMENTO :Tdatetime; QUANTIDADE, VLNOTA, BASEICMS, ALIQUOTA, VLTOTALICMS :Double;COO, NOITEM, CDPRODUTO,CODPRO:Integer):boolean;

    function InserirReg60II(const CAR,NOSERIE,MODELO,STATUS,STFISCAL, CDPRODUTO,CODPRO : String ; DTMOVIMENTO :Tdatetime; QUANTIDADE, VLNOTA, BASEICMS, ALIQUOTA, VLTOTALICMS :Double;COO, NOITEM:Integer):boolean;

   //INSERT INTO NFSAIDASITENS (COO, NOITEM, QUANTIDADE, VLPRODUTO, BASEICMS, ALIQUOTA, VLICMS, CODPRO, CDPRODUTO, INTEGRADO) VALUES (3039, 1, 1, 19.9, 19.9, 18, 3039, 0, '8860201716110 ', 'N');
  end;

var
  Form82: TForm82;
  total :double;
implementation

uses Unit1, Unit2, URelCriticaSintegra;

{$R *.dfm}

function TForm82.InserirReg60II(const CAR,NOSERIE,MODELO,STATUS,STFISCAL, CDPRODUTO,CODPRO : String ; DTMOVIMENTO :Tdatetime; QUANTIDADE, VLNOTA, BASEICMS,
 ALIQUOTA, VLTOTALICMS :Double;COO, NOITEM:Integer):boolean;
begin
try
      if(verificaCupons(coo,noitem) = False )then
      begin
        query4.SQL.clear;
        query4.SQL.add('INSERT INTO NFSAIDASITENS (COO, NOITEM, QUANTIDADE, VLPRODUTO, BASEICMS, ALIQUOTA, VLICMS, CODPRO, CDPRODUTO, INTEGRADO) VALUES ');
        query4.SQL.add('(:COO, :NOITEM, :QUANTIDADE, :VLPRODUTO, :BASEICMS, :ALIQUOTA, :VLICMS, :CODPRO, :CDPRODUTO, :INTEGRADO)');
      end
   else
      begin
        query4.SQL.clear;
        query4.SQL.add('UPDATE NFSAIDASITENS SET ');
        query4.SQL.add('QUANTIDADE = :QUANTIDADE, ');
        query4.SQL.add('VLPRODUTO = :VLPRODUTO,   ');
        query4.SQL.add('BASEICMS = :BASEICMS,     ');
        query4.SQL.add('ALIQUOTA = :ALIQUOTA,     ');
        query4.SQL.add('VLICMS = :VLICMS,         ');
        query4.SQL.add('CODPRO = :CODPRO,         ');
        query4.SQL.add('CDPRODUTO = :CDPRODUTO    ');
        query4.SQL.add('WHERE (COO = :COO) AND    ');
        query4.SQL.add('(NOITEM = :NOITEM);       ');
    end;



     try
            Query4.ParamByName('COO').AsInteger := COO;
            Query4.ParamByName('NOITEM').AsInteger := NOITEM;
            Query4.ParamByName('CDPRODUTO').AsString := CDPRODUTO;
            Query4.ParamByName('QUANTIDADE').AsFloat:= QUANTIDADE;
            Query4.ParamByName('VLPRODUTO').AsFloat := VLNOTA;
            Query4.ParamByName('BASEICMS').AsFloat := BASEICMS;
            Query4.ParamByName('ALIQUOTA').AsFloat:= ALIQUOTA;
            Query4.ParamByName('VLICMS').AsFloat := VLTOTALICMS;
            Query4.ParamByName('CODPRO').AsString := CODPRO;
      except
               ShowMEssage('nserirReg60II');
       end;

           Try
               Query4.ExecSQL;
               Query4.Transaction.Commit;
               Result := True;
            except
                Result := False;
            end;
except

end;

end;

function TForm82.verificaCupons(const coo:integer;noitem:integer):boolean;
    begin
try
      IBQuery5.Active := False;
      IBQuery5.ParamByName('coo').AsInteger := coo;
      IBQuery5.ParamByName('noitem').AsInteger   := noitem;

      IBQuery5.Active := True;
      if (not IBQuery5.Eof)then
          result := True
      else
         result  := False;

       IBQuery5.Active := False;
except

end;
end;

function TForm82.InserirReg60I(const CAR, NOSERIE, MODELO, STATUS,
  STFISCAL: String; DTMOVIMENTO: Tdatetime; QUANTIDADE, VLNOTA, BASEICMS,
  ALIQUOTA, VLTOTALICMS: Double; COO, NOITEM, CDPRODUTO,
  CODPRO: Integer): boolean;
begin
try
    if(verifica_coo(coo) = False )then
      begin
            query4.SQL.clear;
            query4.SQL.add('INSERT INTO NFSAIDAS2 (DTMOVIMENTO, NOSERIE, MODELO, COO, NOITEM, CDPRODUTO, QUANTIDADE, VLNOTA, BASEICMS, ALIQUOTA, VLICMS, ID, STATUS, CODPRO, STFISCAL) VALUES (');
            query4.SQL.add(' :DTMOVIMENTO, :NOSERIE, :MODELO, :COO, :NOITEM, :CDPRODUTO, :QUANTIDADE, :VLNOTA, :BASEICMS, :ALIQUOTA, :VLICMS, :ID, :STATUS, :CODPRO, :STFISCAL);               ');
      end
    else
      begin
            query4.SQL.clear;
            query4.SQL.add('UPDATE NFSAIDAS2 SET ');
            query4.SQL.add('DTMOVIMENTO = :DTMOVIMENTO , ');
            query4.SQL.add('NOSERIE = :NOSERIE, ');
            query4.SQL.add('MODELO = :MODELO,  ');
            query4.SQL.add('NOITEM = :NOITEM,      ');
            query4.SQL.add('CDPRODUTO = :CDPRODUTO, ');
            query4.SQL.add('QUANTIDADE = :QUANTIDADE, ');
            query4.SQL.add('VLNOTA = :VLNOTA,     ');
            query4.SQL.add('BASEICMS = :BASEICMS,   ');
            query4.SQL.add('ALIQUOTA = :ALIQUOTA,      ');
            query4.SQL.add('VLICMS =   :VLICMS,    ');
            query4.SQL.add('ID = :ID,          ');
            query4.SQL.add('STATUS = :STATUS,       ');
            query4.SQL.add('CODPRO = :COdPRO,      ');
            query4.SQL.add('STFISCAL = :STFISCAL     ');
            query4.SQL.add('WHERE (COO = :COO)');

    end;
    Try
            query4.ParamByName('DTMOVIMENTO').AsDateTime := DTMOVIMENTO;
            query4.ParamByName('NOSERIE').AsString := NOSERIE;
            query4.ParamByName('MODELO').AsString := 'D2';
            query4.ParamByName('COO').AsInteger := COO;
            query4.ParamByName('NOITEM').AsInteger := NOITEM;
            Query4.ParamByName('CDPRODUTO').AsString := IntToStr(CDPRODUTO);
            Query4.ParamByName('QUANTIDADE').AsFloat:= QUANTIDADE;
            Query4.ParamByName('VLNOTA').AsFloat := VLNOTA;
            Query4.ParamByName('BASEICMS').AsFloat := BASEICMS;
            Query4.ParamByName('ALIQUOTA').AsFloat:= ALIQUOTA;
            Query4.ParamByName('VLICMS').AsFloat := VLTOTALICMS;
            Query4.ParamByName('ID').AsInteger := 1;
            Query4.ParamByName('STATUS').AsString := 'A';
            Query4.ParamByName('CODPRO').AsInteger := CODPRO;
            Query4.ParamByName('STFISCAL').AsString := 'S';
    except
               ShowMEssage('Reg60I');
       end;
           Try
               Query4.ExecSQL;
               Query4.Transaction.Commit;
               Result := True;
            except
                Result := False;
            end;

except

end;

end;










function TForm82.InserirReg60D(const CAR,NOSERIE, CDPRODUTO : String ; DTMOVIMENTO :Tdatetime; QUANTIDADE, VLTOTAL, BASEICMS, ALIQUOTA, VLTOTALICMS :Double):boolean;
begin
try
query4.Active := False;
 query8.SQL.clear;
 query8.sql.add('Select *from R60D where dtmovimento =     '''+FormatDateTime('DD/MM/YYYY',DTMOVIMENTO)+''' and noserie like '''+NOSERIE+''' and CDPRODUTO like '''+CDPRODUTO+''' ');
 query8.active := True;
 if (query8.Eof) then
 begin
      IBQuery8.SQL.clear;
      IBQuery8.SQL.add('INSERT INTO R60D (ID,CAR, DTMOVIMENTO, NOSERIE, CDPRODUTO, QUANTIDADE, VLTOTAL, BASEICMS, ALIQUOTA, VLTOTALICMS) VALUES (:ID,:CAR, :DTMOVIMENTO, :NOSERIE, :CDPRODUTO, :QUANTIDADE, :VLTOTAL, :BASEICMS, :ALIQUOTA, :VLTOTALICMS);');
 end
 else
 begin
    IBQuery8.SQL.clear;
    IBQuery8.SQL.add('UPDATE R60D SET ');
    IBQuery8.SQL.add('ID = :ID,       ');
    IBQuery8.SQL.add('CAR = :CAR,     ');
    IBQuery8.SQL.add('QUANTIDADE = :QUANTIDADE,  ');
    IBQuery8.SQL.add('VLTOTAL = :VLTOTAL,        ');
    IBQuery8.SQL.add('BASEICMS = :BASEICMS,      ');
    IBQuery8.SQL.add('ALIQUOTA = :ALIQUOTA,      ');
    IBQuery8.SQL.add('VLTOTALICMS = :VLTOTALICMS ');
    IBQuery8.SQL.add('WHERE (DTMOVIMENTO = :DTMOVIMENTO) AND ');
    IBQuery8.SQL.add('  (NOSERIE = :NOSERIE) AND ');
    IBQuery8.SQL.add('  (CDPRODUTO = :CDPRODUTO)');
  end;
  Try
    IBQuery8.ParamByName('ID').AsInteger := 2;
    IBQuery8.ParamByName('CAR').AsString := 'D';
    IBQuery8.ParamByName('QUANTIDADE').AsFloat:= QUANTIDADE;
    IBQuery8.ParamByName('VLTOTAL').AsFloat := VLTOTAL;
    IBQuery8.ParamByName('BASEICMS').AsFloat := BASEICMS;
    IBQuery8.ParamByName('ALIQUOTA').AsFloat :=   ALIQUOTA/100;
    IBQuery8.ParamByName('VLTOTALICMS').AsFloat := VLTOTALICMS;
    IBQuery8.ParamByName('DTMOVIMENTO').AsDate := DTMOVIMENTO;
    IBQuery8.ParamByName('NOSERIE').AsString := NOSERIE;
    IBQuery8.ParamByName('CDPRODUTO').AsString := CDPRODUTO;
    IBQuery8.ParamByName('CAR').AsString := 'D';
  except
               ShowMEssage('nserirReg60D');
       end;
 Try
      IBQuery8.ExecSQL;
      IBQuery8.Transaction.Commit;
      Result := True;
 except
      Result := False;
 end;
      query8.active := False;

except

end;

end;

function TForm82.InserirReg75(const PRODUTO, CODSIN: String;
  CODPRO: Integer): boolean;
begin
try
 query4.SQL.clear;
 query4.sql.add('Select *from PRODSINTE where CODSIN like    '''+CODSIN+''' ');
 query4.active := True;
 if (query4.IsEmpty) then
 begin
      query4.SQL.clear;
      query4.SQL.add('INSERT INTO PRODSINTE (PRODUTO, CODSIN, CODPRO) VALUES (:PRODUTO, :CODSIN, :CODPRO)');
 end
 else
 begin
 query4.SQL.clear;
      query4.SQL.add('UPDATE PRODSINTE SET ');
      query4.SQL.add('PRODUTO = :Produto , ');
      query4.SQL.add('CODPRO =  :CODPRO    ');
      query4.SQL.add('WHERE (CODSIN = :CODSIN )');
 end;
 Try
 Query4.ParamByName('PRODUTO').AsString := PRODUTO;
 Query4.ParamByName('CODSIN').AsString  := CODSIN ;
 Query4.ParamByName('CODPRO').AsInteger := CODPRO ;
       except
               ShowMEssage('nserirReg60D');
       end;
 Try
      Query4.ExecSQL;
      Query4.Transaction.Commit;
      Result := True;
 except
      Result := False;
 end;
except

end;
end;



    function TForm82.verifica60A(const dtemissao:Tdatetime;noserie:String;aliquota:String):boolean;
    begin
    try
      IBQuery2.Active := False;
      IBQuery2.ParamByName('dtemissao').AsDate := dtemissao;
      IBQuery2.ParamByName('noserie').AsString :=   noserie;
      IBQuery2.ParamByName('aliquota').AsString  :=  aliquota;
      IBQuery2.Active := True;
      if (not IBQuery2.Eof)then
          result := True
      else
         result  := False;

       IBQuery2.Active := False;
              except

end;
    end;
    function TForm82.verifica60D(const dtemissao:Tdatetime;noserie:String;codpro:String):boolean;
    begin
    try
      IBQuery3.Active := False;
      IBQuery3.ParamByName('dtemissao').AsDate := dtemissao;
      IBQuery3.ParamByName('noserie').AsString :=   noserie;
      IBQuery3.ParamByName('cdproduto').AsString  :=  codpro;
      IBQuery3.Active := True;
      if (not IBQuery3.Eof)then
          result := True
      else
         result  := False;

       IBQuery3.Active := False;
       except

end;
    end;
    function TForm82.verifica60R(const coo :integer):boolean;
    begin
    try
      IBQuery6.Active := False;
      IBQuery6.ParamByName('coo').AsInteger := coo;
      IBQuery6.Active := True;
      if (not IBQuery6.Eof)then
          result := True
      else
         result  := False;

       IBQuery6.Active := False;
       except

end;
    end;
    function TForm82.verifica60M(const dtemissao:Tdatetime;noecf:Integer):boolean;
    begin
    try
    IBQuery4.SQL.Clear;
    IBQuery4.SQL.Add('select *from redleitura r where r.dtmovimento = :date and ');
    IBQuery4.SQL.Add('          r.noecf       = :ecd                            ');

      IBQuery4.Active := False;
      IBQuery4.ParamByName('date').AsDate := dtemissao;
      IBQuery4.ParamByName('ecd').AsInteger:=   noecf;
      IBQuery4.Active := True;
      if (not IBQuery4.Eof)then
          result := True
      else
         result  := False;

       IBQuery4.Active := False;
       except

end;
    end;


function TForm82.Inserir60m(const tipo,aux,noserie,ModEcf :String;dtmovimento:TdateTime;noecf,CooInicial,CooFinal,Crz,Cro: Integer;VENDABRUTA,TOTALGERAL:double ):boolean;
begin
try
   if (verifica60M(dtmovimento,noecf))then
   begin
        QryInsert60M.SQL.Clear;
        QryInsert60M.SQL.Add('UPDATE REDLEITURA SET ');
        QryInsert60M.SQL.Add('TIPO = :TIPO, ');
        QryInsert60M.SQL.Add('CAR =  :CAR, ');
        QryInsert60M.SQL.Add('NOSERIE = :NOSERIE, ');
        QryInsert60M.SQL.Add('MODECF =  :MODECF, ');
        QryInsert60M.SQL.Add('COOINICIAL = :COOINICIAL,  ');
        QryInsert60M.SQL.Add('COOFINAL = :COOFINAL,  ');
        QryInsert60M.SQL.Add('CRZ = :CRZ,  ');
        QryInsert60M.SQL.Add('CRO = :CRO, ');
        QryInsert60M.SQL.Add('VENDABRUTA = :VENDABRUTA, ');
        QryInsert60M.SQL.Add('TOTALGERAL = :TOTALGERAL  ');
        QryInsert60M.SQL.Add('WHERE DTMOVIMENTO = :DTMOVIMENTO AND  ');
        QryInsert60M.SQL.Add('NOECF = :NOECF');
   end
   else
   begin
        QryInsert60M.SQL.Clear;
        QryInsert60M.SQL.Add('INSERT INTO REDLEITURA (TIPO, CAR, DTMOVIMENTO, NOSERIE, NOECF, MODECF, COOINICIAL, COOFINAL, CRZ, CRO, VENDABRUTA, TOTALGERAL) VALUES ');
        QryInsert60M.SQL.Add('(:TIPO, :CAR, :DTMOVIMENTO, :NOSERIE, :NOECF, :MODECF, :COOINICIAL, :COOFINAL, :CRZ, :CRO, :VENDABRUTA, :TOTALGERAL)');
   end;


   try
    QryInsert60M.Close;
    QryInsert60M.ParamByName('tipo').Value := tipo;
    QryInsert60M.ParamByName('Car').Value := aux;
    QryInsert60M.ParamByName('noserie').Value := noserie ;
    QryInsert60M.ParamByName('ModEcf').Value := ModEcf;
    QryInsert60M.ParamByName('dtmovimento').Value := dtmovimento;
    QryInsert60M.ParamByName('noecf').Value := noecf;
    QryInsert60M.ParamByName('CooInicial').Value := CooInicial;
    QryInsert60M.ParamByName('CooFinal').Value := CooFinal;
    QryInsert60M.ParamByName('Crz').Value := Crz;
    QryInsert60M.ParamByName('Cro').Value := Cro;
    QryInsert60M.ParamByName('VENDABRUTA').Value := VENDABRUTA;
    QryInsert60M.ParamByName('TOTALGERAL').Value := TOTALGERAL;
      except
               ShowMEssage('nserirReg60M');
       end;
    QryInsert60M.ExecSQL;
    QryInsert60M.Transaction.Commit;
except

end;
end;
function TForm82.Inserir60A(const tipo,reg,aliquota,noserie :String;dtmovimento:TdateTime;valor :Double):Boolean;
begin
try
QryReg60A.Close;
QryReg60A.Open;
if (verifica60A(dtmovimento,noserie,aliquota) )then
begin
  QryReg60A.SQL.Clear;
  QryReg60A.SQL.Add(' update R60A ');
  QryReg60A.SQL.Add('set          ');
  QryReg60A.SQL.Add('  DTEMISSAO = :DTEMISSAO, ');
  QryReg60A.SQL.Add('  NOSERIE = :NOSERIE,     ');
  QryReg60A.SQL.Add('  ALIGUOTA = :ALIGUOTA,   ');
  QryReg60A.SQL.Add('  VALOR = :VALOR          ');
  QryReg60A.SQL.Add('where                     ');
  QryReg60A.SQL.Add('  DTEMISSAO = :OLD_DTEMISSAO and ');
  QryReg60A.SQL.Add('  NOSERIE = :OLD_NOSERIE and    ');
  QryReg60A.SQL.Add('  ALIGUOTA = :OLD_ALIGUOTA       ');
    try
      QryReg60A.ParamByName('DTEMISSAO').Value :=  dtmovimento;
      QryReg60A.ParamByName('NOSERIE').Value :=  noserie;
      QryReg60A.ParamByName('ALIGUOTA').Value :=  aliquota;
      QryReg60A.ParamByName('VALOR').Value :=  VALOR;
      QryReg60A.ParamByName('OLD_DTEMISSAO').Value :=  dtmovimento;
      QryReg60A.ParamByName('OLD_NOSERIE').Value :=  noserie;
      QryReg60A.ParamByName('OLD_ALIGUOTA').Value :=  aliquota;
      QryReg60A.ExecSQL;
      QryReg60A.Transaction.Commit;
  Except
     ShowMEssage('nserirReg60A');
  end;
  end
else
begin
  QryReg60A.SQL.Clear;
  QryReg60A.SQL.Add('insert into R60A ');
  QryReg60A.SQL.Add('  (DTEMISSAO, NOSERIE, ALIGUOTA, VALOR) ');
  QryReg60A.SQL.Add('values   ');
  QryReg60A.SQL.Add('  (:DTEMISSAO, :NOSERIE, :ALIGUOTA, :VALOR) ');
    try
      QryReg60A.ParamByName('DTEMISSAO').Value :=  dtmovimento;
      QryReg60A.ParamByName('NOSERIE').Value :=  dtmovimento;
      QryReg60A.ParamByName('ALIGUOTA').Value :=  aliquota;
      QryReg60A.ParamByName('VALOR').Value :=  VALOR;
      QryReg60A.ExecSQL;
      QryReg60A.Transaction.Commit;
  Except
  end;
end;
except

end;
end;
function TForm82.somarIcmsNf():boolean;
var soma :Double;
    cont : Integer;
    icms : String;
begin
try
  query5.Active := False;
  query5.SQL.Text := 'select *from nfsaidas2  ';
  query5.Active := True;
  while( not query5.eof ) do
  begin
  query4.Active := False;
  query4.SQL.Text := 'select *from nfsaidasitens where coo = '+IntToStr(query5.FieldByName('COO').AsInteger )+'  ';
  query4.Active := True;
  soma := 0;
  while( not query4.Eof ) do
  begin
   soma := soma + query4.fieldbyname('vlicms').AsFloat;

   query4.Next;
  end;
    icms := FloatToStr(soma);
     cont := 0;
     while(cont <= 10)do
     begin
      if (icms[cont] = ',' )then
       icms[cont] := '.';
       cont := cont + 1;
      end;
      Ibquery1.Active := False;
      Ibquery1.SQL.Text := 'update nfsaidas2 set vlicms = '+icms+'  where coo  = '+IntToStr(query5.FieldByName('COO').AsInteger )+' ';
      Ibquery1.ExecSQL;
      Ibquery1.Transaction.CommitRetaining;
   query5.Next;
  end;

except

end;
end;


function TForm82.SomarVlIcms(coo,noitem :Integer):Double;
var soma :Double;
    cont : Integer;
    icms : String;
begin
try
  query6.Active := False;
  query6.SQL.Text := 'select *from nfsaidasitens where coo  = '+IntToStr(coo)+' and noitem = '+IntToStr(noitem)+' ';
  query6.Active := True;
  soma := (query6.FieldByName('BaseIcms').AsFloat * query6.FieldByName('aliquota').AsFloat)/100;
  icms := FloatToStr(soma);
  cont := 0;
  while(cont <= 10)do
  begin
   if (icms[cont] = ',' )then
       icms[cont] := '.';
   cont := cont + 1;
  end;
  Ibquery1.Active := false;
  Ibquery1.SQL.Text := 'update nfsaidasitens set vlicms = '+icms+'  where coo  = '+IntToStr(coo)+' and noitem = '+IntToStr(noitem);
  Ibquery1.ExecSQL;

except

end;
end;

function TForm82.ProcuraCodPro(ref : String): Integer;
begin
try
  query1.Active := False;
  query1.SQL.Text := 'select *from prodsinte where codsin = '+ ref;
  query1.Active := True;
 if(not query1.Eof )then
    result := query1.FieldByName('CODPRO').AsInteger
 else
    result := 0;
except

end;
end;

function TForm82.InseriCodSint(): boolean;
var aux,aux2,aux3,aux4 : String;
begin
try
  query1.Active := False;
  query1.SQL.Text := 'select *from prodsinte where codpro = 0';
  query1.Active := True;
//  query3.Close;
//  query3.Open;
  while (not query1.Eof) do
  begin
    aux := query1.FieldByName('CODSIN').AsString;
    aux2 := Copy(aux,10,4);
    aux4 := query1.FieldByName('PRODUTO').AsString;
    aux3 := Copy(aux4,0,8);
    query2.Active := False;
    query2.SQL.Text := 'select *from produtos where produto like ''%'+aux3+'%'' and ref like ''%'+AUX2+'%'' ';
    query2.Active := True;
    query3.SQL.Text := 'UPDATE PRODSINTE SET CODPRO = '+IntToStr(query2.FieldByName('CODPRO').AsINTEGER) +' WHERE CODSIN LIKE ''%'+query1.FieldByName('CODSIN').AsString+'%''' ;
    query3.ExecSQL;

   query1.Next;
       query3.Transaction.CommitRetaining;
  end;

except

end;
end;


function TForm82.VerificaString (str :String):String;

var
i: Integer;
begin
  for i := 1 to Length ( str ) do
  case str[i] of
  'á': str[i] := 'a';
  'é': str[i] := 'e';
  'í': str[i] := 'i';
  'ó': str[i] := 'o';
  'ú': str[i] := 'u';
  'à': str[i] := 'a';
  'è': str[i] := 'e';
  'ì': str[i] := 'i';
  'ò': str[i] := 'o';
  'ù': str[i] := 'u';
  'â': str[i] := 'a';
  'ê': str[i] := 'e';
  'î': str[i] := 'i';
  'ô': str[i] := 'o';
  'û': str[i] := 'u';
  'ä': str[i] := 'a';
  'ë': str[i] := 'e';
  'ï': str[i] := 'i';
  'ö': str[i] := 'o';
  'ü': str[i] := 'u';
  'ã': str[i] := 'a';
  'õ': str[i] := 'o';
  'ñ': str[i] := 'n';
  'ç': str[i] := 'c';
  'Á': str[i] := 'A';
  'É': str[i] := 'E';
  'Í': str[i] := 'I';
  'Ó': str[i] := 'O';
  'Ú': str[i] := 'U';
  'À': str[i] := 'A';
  'È': str[i] := 'E';
  'Ì': str[i] := 'I';
  'Ò': str[i] := 'O';
  'Ù': str[i] := 'U';
  'Â': str[i] := 'A';
  'Ê': str[i] := 'E';
  'Î': str[i] := 'I';
  'Ô': str[i] := 'O';
  'Û': str[i] := 'U';
  'Ä': str[i] := 'A';
  'Ë': str[i] := 'E';
  'Ï': str[i] := 'I';
  'Ö': str[i] := 'O';
  'Ü': str[i] := 'U';
  'Ã': str[i] := 'A';
  'Õ': str[i] := 'O';
  'Ñ': str[i] := 'N';
  'Ç': str[i] := 'C';
end;
Result := str;
end;


function TForm82.InserirCodPro (ref:String) :Integer;
var nome :string;
    CodPRo3 : Integer;
begin
try
  query1.Active := False;
  query1.SQL.Text := 'select *from prodsinte  ps where ps.codsin like  ''%'+ref+'%''';
  query1.Active := True;
  nome := trim(query1.fieldByName('Produto').AsString);

  query1.Active := False;
  query1.SQL.Text := 'select *from prod_serv where ref like ''%'+ref+'%''';
  query1.Active := True;

  if (not query1.Eof)then
   result := query1.fieldbyName('cod_produto').asinteger
  else
  begin
    IBQuery9.SQL.Clear;
    IBQuery9.SQL.Add('select *from prod_serv pp where pp.descr_produto like ''%'+nome+'%''');
    IBQuery9.Active := True;
    CodPRo3 := IBQuery9.fieldbyName('cod_produto').asinteger;
    result := CodPRo3;
  end;
except

end;
end;
function TForm82.somarItens():Double;
var soma,baseicms,vlicmss :double;
teste,teste1 :String;
cont :integer;
begin
try
  soma := 0;
  baseicms := 0;
  vlicmss :=0;
  query1.Active := False;
  query1.SQL.Text := 'select *from nfsaidas2';
  query1.Active := True;
  while ( not query1.Eof) do
  begin
  query2.Active := False;
  query2.SQL.Text := 'select *from nfsaidasitens where coo = '+IntToStr(query1.FieldByname('coo').asinteger) ;
  query2.Active := True;
  while ( not query2.Eof) do
  begin
     soma := soma + (query2.FieldbyName('VLPRODUTO').AsFloat * query2.FieldbyName('QUANTIDADE').AsFloat);
     SomarVlIcms(query2.FieldbyName('COO').AsInteger ,query2.FieldbyName('NOITEM').AsInteger );
  query2.Next;
  end;
  teste := FloatToStr(soma);
  cont := 0;
  while(cont <= 10)do
  begin
   if (teste[cont] = ',' )then
       teste[cont] := '.';
   cont := cont + 1;
  end;
  cont := 0;
  teste1 := FloatToStr(vlicmss);
  while(cont <= 10)do
  begin
   if (teste1[cont] = ',' )then
       teste1[cont] := '.';
   cont := cont + 1;
  end;
  query3.SQL.Text := 'update  nfsaidas2 set vlnota = '+teste+',baseicms = '+teste+',aliquota = 18  where coo = '+IntToStr(query1.FieldByname('coo').asinteger) ;
 // ShowMEssage(query3.SQL.Text);
  query3.ExecSQL;
  query3.Transaction.CommitRetaining;
  soma := 0;
  vlicmss := 0;
  query1.Next;
  end;


except

end;
end;
function TForm82.verifica_coo (coo :Integer) :boolean;
begin
try
  query1.Active := False;
  query1.SQL.Text := 'select *from nfsaidas2 where coo = '+ IntToStr(coo);
  query1.Active := True;
  if (not query1.Eof)then
   result := true
  else
   result := false;
except

end;
end;
function TForm82.verifica_cooItens (coo :Integer;noitens:integer) :boolean;
begin
try
  query1.Active := False;
  query1.SQL.Text := 'select *from nfsaidas2 where coo = '+ IntToStr(coo)+' and noitem = '+IntToStr(noitens);
  query1.Active := True;
  if (not query1.Eof)then
   result := true
  else
   result := false;
except

end;
end;
procedure TForm82.Button2Click(Sender: TObject);
var
ano,mes,dia :string;
  datas : string;
  data,dataRed,data60A : Tdatetime;

txt: TStrings;
a,c, linha, str1, str2,aux,aux2,status,aliguota,serie,codpro: String;
b :TextFile;
cont,medidor,x,y,noecf,coo,NOITEM,aliquota,CodPro2: integer;
ref,Produto,Codsim,Cdproduto :string;
TIPO, CAR, NOSERIE, MODECF:String;
DTMOVIMENTO : TDateTime;
COOINICIAL, COOFINAL,CRZ, CRO  :Integer;
VENDABRUTA, TOTALGERAL,valor,Quantidade,Vltotal,Baseicms,Vltotalicms,Vlicms,VLPRODUTO :Double;

begin
try
OpenDialog1.Execute;
AssignFile(b,OpenDialog1.FileName);
cont := 0;
x:= 0;
Reset(b);
txt := TStringList.Create;
txt.LoadFromFile(OpenDialog1.FileName);
medidor := txt.Count;
ProgressBar1.Min := 0;
ProgressBar1.Max := txt.Count;
cont := 0;
Status := 'A';
while not  Eof(b)do
 begin
a:=txt[cont];
aux := Copy(a,0, 2);
            
              if (aux = '75')then

                begin
                try
                  Label1.Caption := 'gerando  Registro 75...';
                  Label2.Caption := IntToStr(cont);
                  Label3.Caption := IntToStr(medidor);
                  Application.ProcessMessages;
                  //aux2 := Copy(a,19,14) ;
                 // CodSin := aux2;
                  aux2 := Copy(a,41,53);
                  Produto := VerificaString(aux2);
                  aux2 := Copy(a,26,6);
                  CodPro2 := InserirCodPro(aux2);
                Except
                   ShowMessage('Erro no Registro 75');
                end;
                  InserirReg75(PRODUTO, Copy(a,19,14),CODPRO2);
                end;
                if(aux = '60')then
                begin
                 Label1.Caption := 'gerando  Registro 60M...';
                  Label2.Caption := IntToStr(cont);
                  Label3.Caption := IntToStr(medidor);
                  Application.ProcessMessages;
                 aux := Copy(a,3, 1);
                  if(aux = 'M' )then
                  begin
                  Try
                    TIPO := '60';
                    aux2 := Copy(a,4,8);
                    DTMOVIMENTO := StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(DataModule1.ajustadata(aux2))));
                    aux2 := Copy(a,12,20);
                    NOSERIE := aux2;
                    aux2 := Copy(a,32,3);
                    NOECF := StrToInt(aux2);
                    aux2 := Copy(a,35,2);
                    MODECF := aux2;
                    aux2 := Copy(a,37,6);
                    COOINICIAL := StrToInt(aux2);
                    aux2 := Copy(a,43,6);
                    COOFINAL := StrToInt(aux2);
                    aux2 := Copy(a,49,6);
                    CRZ := StrToInt(aux2) ;
                    aux2 := Copy(a,55,3);
                    CRO := StrToInt(aux2);
                    aux2 := Copy(a,58,16);
                    VENDABRUTA := StrToFloat(aux2)/100;
                    aux2 := Copy(a,74,16);
                    TOTALGERAL := StrToFloat(aux2)/100;
                  except
                   ShowMessage('Erro no Registro M');
                  end;
                    Inserir60m(tipo,aux,noserie,ModEcf,dtmovimento,noecf,CooInicial,CooFinal,Crz,Cro,VENDABRUTA,TOTALGERAL);

                  end;
                  if(aux = 'A' )then
                  begin
                    Label1.Caption := 'gerando  Registro 60A...';
                    Label2.Caption := IntToStr(cont);
                    Label3.Caption := IntToStr(medidor);
                    Application.ProcessMessages;
                    try
                    aux2 := Copy(a,4,8);
                    dtmovimento := StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(DataModule1.ajustadata(aux2))));
                    serie   :=  Copy(a,12,20);
                    aliguota := Copy(a,32,4);
                    aux2 := Copy(a,38,12);
                    valor :=  StrToFloat(aux2)/100;
                   except
                   ShowMessage('Erro no Registro A');
                  end;
                    Inserir60A('60','A',aliguota,serie,dtmovimento,valor);
                  end;
                  if(aux = 'D' )then
                  begin

                    Label1.Caption := 'gerando  Registro 60D...';
                    Label2.Caption := IntToStr(cont);
                    Label3.Caption := IntToStr(medidor);
                    Application.ProcessMessages;
                   try


                    aux2 := Copy(a,4,8);
                    data60A := StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(DataModule1.ajustadata(aux2))));
                    serie   := Copy(a,12,20);
                    codpro  := Copy(a,36,10);
                    aux2 := Copy(a,12,20);
                    Noserie := aux2;
                    aux2 := Copy(a,36,10);
                    Cdproduto := aux2;
                    aux2 := Copy(a,46,13);
                    Quantidade := StrToFloat(aux2)/1000;
                    aux2 := Copy(a,59,16);
                    Vltotal := StrToCurr(aux2)/100;
                    aux2 := Copy(a,75,16);
                    Baseicms:= StrToCurr(aux2)/100;
                    aux2 := Copy(a,91,4);
                    Aliquota := StrToInt(aux2);
                    aux2 := Copy(a,95,13);
                    Vltotalicms := StrToCurr(aux2)/100;
                                      except
                   ShowMessage('Erro no Registro D');
                  end;

                                        InserirReg60D('D',Noserie,Cdproduto,data60A,Quantidade,Vltotal,Baseicms,Aliquota,Vltotalicms)

                  end;
                  if(aux = 'I' )then
                  begin
                  try
                    Label1.Caption := 'gerando  Registro 60I...';
                    Label2.Caption := IntToStr(cont);
                    Label3.Caption := IntToStr(medidor);
                    Application.ProcessMessages;
                    aux2 := Copy(a,34,6);
                    aux2 := Copy(a,4,8);
                    DTMOVIMENTO := StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(DataModule1.ajustadata(aux2))));
                    aux2 := Copy(a,12,20);
                    NOSERIE := aux2;

                    aux2 := Copy(a,34,6);
                    Coo := StrToInt(aux2);
                    aux2 := Copy(a,40,3);
                    Noitem := StrToInt(aux2);
                    aux2 := Copy(a,83,12);
                    BASEICMS := (StrTofloat(aux2)/100);
                    aux2 := Copy(a,95,2);
                    Aliquota := StrToInt(aux2);
                    aux2 := Copy(a,99,11);
                    Vlicms := (StrTofloat(aux2)/100);
                  except
                   ShowMessage('Erro no Registro I - Nota');
                  end;
                    //function InserirReg60I('60',NOSERIE,MODELO,STATUS,STFISCAL : String ; DTMOVIMENTO :Tdatetime; QUANTIDADE, VLNOTA, BASEICMS, ALIQUOTA, VLTOTALICMS :Double;COO, NOITEM, CDPRODUTO,CODPRO:Integer):boolean;
                    InserirReg60I('60',NOSERIE,'2D','A','S',DTMOVIMENTO,0,0, BASEICMS, ALIQUOTA,Vlicms,COO, NOITEM,0,0);
                    aux2 := Copy(a,34,6);
                    aux := Copy(a,40,3);
                    try
                    IBTable1.Open;
                    coo :=    StrToInt(Copy(a,34,6));
                    NOITEM := StrToInt(Copy(a,40,3));
                    aux2 := Copy(a,57,13);
                    QUANTIDADE := (StrTofloat(aux2)/1000);
                    aux2 := Copy(a,70,13);
                    VLPRODUTO  := (StrTofloat(aux2)/100);
                    aux2 := Copy(a,83,12);
                    BASEICMS   := (StrTofloat(aux2)/100);
                     aux2 := Copy(a,95,2);
                    ALIQUOTA   := StrToInt(aux2);
                    aux2 := Copy(a,34,6);
                    aux := Copy(a,40,3);
                    VLICMS     :=StrToInt(aux2);
                    aux2 := Copy(a,50,6);
                    Cdproduto := aux2;
                    aux2 := Copy(a,50,6);
                    CODPRO  := IntToStr(InserirCodPro(aux2));
                   except
                   ShowMessage('Erro no Registro I - Itens');
                  end;
                   InserirReg60II('60I',NOSERIE,'2D','A','S', CDPRODUTO,CODPRO,DTMOVIMENTO,QUANTIDADE,VLPRODUTO,BASEICMS, ALIQUOTA,VLICMS,COO, NOITEM);

                  end;
                   if(aux = 'R' )then
                  begin
                  Label1.Caption := 'gerando  Registro 60R...';
                  Label2.Caption := IntToStr(cont);
                  Label3.Caption := IntToStr(medidor);
                  Application.ProcessMessages;
                  try
                  Table5.open;
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
                  Table5.Transaction.Commit;
                  except
                  ShowMessage('Erro no registro R');

                    end;

                 end

                 end;
      cont := cont + 1;
      ProgressBar1.Position := cont;
      next;
     end;




  Readln(b, linha);
CloseFile(b);
IBQuery7.ParamByName('dtinicial').AsDate := StrToDate('01/01/2010') ;
IBQuery7.ParamByName('dtfinal').AsDate :=   StrToDate('01/04/2011') ;
IBQuery7.Active := True;
 RelCriticaSintegra.RLReport1.Preview;
 except
   ShowMEssage('000020');
end;
end;

procedure TForm82.Button3Click(Sender: TObject);

var
 cont :Integer;
begin
  // try
   query2.Active := False;
   Label1.Caption := 'Baixando Estoque...';
   query2.SQL.Text :=' select *from nfsaidasitens n where n.integrado = ''N'' ';
   query2.Active := true;
   ProgressBar1.Min := 0;
   query2.FetchAll;
   ProgressBar1.Max :=  query2.RecordCount;
   Label2.Caption := '0';
   Label3.Caption := IntToStr(query2.RecordCount);
   cont := 0;
   Application.ProcessMessages;

   while (not query2.eof) do
   begin

     DataModule1.baixarestoque(query2.fieldbyname('codpro').AsInteger,'0',query2.fieldbyname('quantidade').Asfloat);
     query4.SQL.Text := 'update nfsaidasitens set integrado = ''S'',codpro = '+IntToStr(query2.fieldbyname('codpro').AsInteger)+' where cdproduto = '''+query2.fieldbyname('cdproduto').AsString+'''';
     query4.ExecSQL;
     ProgressBar1.Position := cont;
     inc(cont);
     Label2.Caption := IntToStr(cont);
     Application.ProcessMessages;
     query4.Close;

     query2.Next;
   end;
    query4.Transaction.CommitRetaining;
end;

procedure TForm82.Button1Click(Sender: TObject);
begin
{ query3.Active := false;
 query3.SQL.Text := 'select *from produtos';
 query3.Active := true;
 while (not query3.Eof)do
 begin
  query1.Active := False;
  query1.SQL.Text := 'select *from nfsaidas2 where cdproduto like ''%'+query3.fieldbyname('REF').AsString +'%''';
  query1.Active := True;
  while (not query1.Eof)do
  begin
  query2.SQL.Text := 'update nfsaidas2 set codpro = '+IntToStr(query3.fieldbyName('codpro').asinteger)+'where cdproduto like ''%'+Query1.fieldbyName('Cdproduto').AsString +'%'' ';
  query2.ExecSQL;
  query1.Next;
  end;
  query3.Next;
  end;}
  InseriCodSint;
  query2.Active := False;
  query2.SQL.Text := 'select *from nfsaidasitens where codpro = 0';
  query2.Active := True;
  while (not query2.Eof )do
  begin
     query3.SQL.Text := 'update nfsaidasitens set codpro = '+IntToStr(ProcuraCodPro(query2.FieldByName('CDPRODUTO').AsString))+' where cdproduto = '+query2.FieldByName('CDPRODUTO').AsString ;
   query2.Next;
  end;
 somarItens();
 somarIcmsNf();
end;

end.






