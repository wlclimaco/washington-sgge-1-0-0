unit UCad0006;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0003, BrvImport, BrvExport, BrvFiltrar, BrvLocalizar, Menus, ImgList,
  BrvOrdenar, BrvRelatorio, BrvString, DB, DBClient, BrvClientDataSet, Grids, BrvDbGrids,
  BrvDbGrid, ComCtrls, StdCtrls, ToolWin, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  UClaSrv, BrvDigito, BrvListParam;

type
  TCad0006 = class(TCad0003)
    procedure FormCreate(Sender: TObject);
    procedure QCadastroBeforePost(DataSet: TDataSet);
    procedure QCadastroAfterPost(DataSet: TDataSet);
  private
    { Private declarations }
    gSnLancar : Boolean;
  public
    { Public declarations }
  end;

var
  Cad0006: TCad0006;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCad0006.FormCreate(Sender: TObject);
begin
      inherited;
      MontarFormularioCadastro(19, '');
end;

procedure TCad0006.QCadastroAfterPost(DataSet: TDataSet);
var lConexao     : TSDmConClient;
    lDsResult    : String;
begin
      inherited;

      if gSnLancar then
      begin
            lConexao := TSDmConClient.Create(DmSrvApl.SrvAplica.DBXConnection);

            try
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                // =-=-=-= Criarndo Provider no servidor de aplicação
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                lConexao.LancamentoPatrimonial(
                                         DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                         QCadastro.FieldByName('CdBem').AsInteger);

                DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
            finally
                FreeAndNil(lConexao);
            end;

      end;
end;

procedure TCad0006.QCadastroBeforePost(DataSet: TDataSet);
begin
      inherited;
      gSnLancar := (QCadastro.State = dsInsert) and (SbtGravar.Enabled);
end;

initialization
      RegisterClass(TCad0006);

finalization
      UnRegisterClass(TCad0006);

end.
