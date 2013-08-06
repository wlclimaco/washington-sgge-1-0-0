unit uFrmClient;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DBXDataSnap, DBXCommon, DB, SqlExpr, Buttons, StdCtrls, DBClient, DSConnect,
  Grids, DBGrids;

type
  TFrmClient = class(TForm)
    SRVSERVER: TSQLConnection;
    BbtConecta: TBitBtn;
    BbtDados: TBitBtn;
    ClientDataSet1: TClientDataSet;
    DBGrid1: TDBGrid;
    DataSource1: TDataSource;
    BbtTransac: TBitBtn;
    LblTransac: TLabel;
    BitBtn1: TBitBtn;
    ClientDataSet2: TClientDataSet;
    DBGrid2: TDBGrid;
    DataSource2: TDataSource;
    Button1: TButton;
    PvcTabela: TDSProviderConnection;
    procedure BbtConectaClick(Sender: TObject);
    procedure BbtDadosClick(Sender: TObject);
    procedure BbtTransacClick(Sender: TObject);
    procedure BitBtn1Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure ClientDataSet2AfterPost(DataSet: TDataSet);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmClient: TFrmClient;

implementation

{$R *.dfm}

uses  UServerClasses;

procedure TFrmClient.BbtConectaClick(Sender: TObject);
begin
      if BbtConecta.Caption = 'Desconectado' then
      begin
            SRVSERVER.Connected := True;
            BbtConecta.Caption  := 'Conectado';
      end else
      begin
            SRVSERVER.Connected := False;
            BbtConecta.Caption  :=  'Desconectado';
      end;
end;

procedure TFrmClient.BbtDadosClick(Sender: TObject);
var Conexao : TServerMethodsClient;
begin
      Conexao := TServerMethodsClient.Create(SRVSERVER.DBXConnection);
      ClientDataSet1.Data := Conexao.PegaDados;
      FreeAndNil(Conexao);
end;

procedure TFrmClient.BbtTransacClick(Sender: TObject);
var Conexao : TServerMethodsClient;
begin
      Conexao := TServerMethodsClient.Create(SRVSERVER.DBXConnection);
      LblTransac.Caption := Conexao.Transacao;
      FreeAndNil(Conexao);
end;

procedure TFrmClient.BitBtn1Click(Sender: TObject);
begin
      ClientDataSet1.Close;
end;

procedure TFrmClient.Button1Click(Sender: TObject);
begin
      ClientDataSet2.ProviderName := 'DataSetProvider2';
      ClientDataSet2.Params.clear;
//      ClientDataSet2.FetchParams;
      ClientDataSet2.Open;
end;

procedure TFrmClient.ClientDataSet2AfterPost(DataSet: TDataSet);
begin
      ClientDataSet2.ApplyUpdates(0);
end;

end.
