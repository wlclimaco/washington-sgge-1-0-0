unit UDmDicion;

interface

uses
  SysUtils, Classes, BrvDicionario, DBXOracle, DB, SqlExpr, BrvConnection, DbClient;

type
  TDmDicion = class(TDataModule)
    BrvDicionario: TBrvDicionario;
    SqlDicion: TBrvConnection;
    procedure DataModuleCreate(Sender: TObject);
    procedure SqlDicionAfterConnect(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    function NumeroTransactionID: TTransactionDesc;
  end;

var
  DmDicion: TDmDicion;

implementation

{$R *.dfm}

procedure TDmDicion.DataModuleCreate(Sender: TObject);
begin
      BrvDicionario.CarregarDicionario(nil, nil, '');
end;

function TDmDicion.NumeroTransactionID : TTransactionDesc;
Var  NrSeqTra : Cardinal;
begin
      NrSeqTra              := BrvDicionario.ProximoValorSequencial(0, 4);
      Result.GlobalID       := NrSeqTra * 10;
      Result.IsolationLevel := xilREADCOMMITTED;
end;

procedure TDmDicion.SqlDicionAfterConnect(Sender: TObject);
begin
      SqlDicion.ExecuteDirect('alter session set nls_numeric_characters = ''.,''');
end;

end.
