unit USdmLogos;

interface

uses
  SysUtils, Classes, DSServer, Provider, DBXOracle, SqlExpr, FMTBcd, dialogs, DB,
  DBClient;

type
  TSDmLogos = class(TDSServerModule)
    CdsTabela: TClientDataSet;
    DataSetProvider1: TDataSetProvider;
    QpHistor: TSQLDataSet;
    QpTransac: TSQLDataSet;
    DataSetProvider2: TDataSetProvider;
    CdsTransac: TClientDataSet;
    ProDireto: TDataSetProvider;
    SqlConnec: TSQLConnection;
  private
    { Private declarations }
     ObjTransacao: TTransactionDesc;
  public
    { Public declarations }
    function PegaDados : OleVariant;
    function Transacao : String;
    function AlteraHistorico(pDsHistor : String) : String;
    function RoolBack: String;
  end;

implementation

{$R *.dfm}

uses StrUtils, UDmContai;

function TSDmLogos.Transacao: String;
begin
     try
         ObjTransacao.TransactionID := 1;
         ObjTransacao.IsolationLevel:=  xilReadCommitted;
         SqlConnec.StartTransaction(ObjTransacao);

         CdsTransac.Close;
         CdsTransac.Open;
         CdsTransac.Edit;
         Result := Result + ' Transação aberta ' +
                                               CdsTransac.FieldByName('DsHistor').AsString;
     Except
          on E: Exception do
                begin
                      Result := ' Transação Erro: ' + E.Message;
                end;
     end;
end;

function TSDmLogos.AlteraHistorico(pDsHistor : String): String;
begin
     try
         ObjTransacao.TransactionID := 1;
         ObjTransacao.IsolationLevel:=  xilReadCommitted;
         ObjTransacao.GlobalID      := 1;
//         Bravo_Oracle.StartTransaction(ObjTransacao);

         CdsTransac.Close;
         CdsTransac.Open;
         CdsTransac.Edit;
         CdsTransac.FieldByName('DsHistor').AsString := pDsHistor;
         CdsTransac.Post;
         CdsTransac.ApplyUpdates(0);
         CdsTransac.Close;
         SqlConnec.Commit(ObjTransacao);
         Result := ' Alterado ';
     except
          on E: Exception do
                begin
                   SqlConnec.Rollback(ObjTransacao);
                   Result := ' Erro alteração: ' + E.Message;
                end;
     end;
end;

function TSDmLogos.RoolBack : String;
begin
     try
         ObjTransacao.TransactionID := 1;
         ObjTransacao.IsolationLevel:=  xilReadCommitted;
         ObjTransacao.GlobalID      := 1;

         SqlConnec.Rollback(ObjTransacao);
         Result := ' Transação desfeita ';
     except
          on E: Exception do
                begin
                   Result := ' Erro rollback: ' + E.Message;
                end;
     end;
end;

function TSDmLogos.PegaDados : OleVariant;
var DataSet : TSqlDataSet;
    Provider : TDataSetProvider;
begin
     try
         CdsTabela.Close;
         CdsTabela.Open;
         Result := DataSetProvider1.Data;
{
         DataSet := TSqlDataSet.Create(Self);
         DataSet.CommandText   := 'select * from HISTORICO';
         DataSet.SQLConnection := Bravo_Oracle;
         DataSet.GetMetadata   := False;

         Provider  := TDataSetProvider.Create(Self);
         Provider.DataSet := DataSet;
         Result := Provider.Data;
 }
     Except
     end;
end;

end.

