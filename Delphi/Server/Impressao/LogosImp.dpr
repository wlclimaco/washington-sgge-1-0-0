program LogosImp;

uses
  Forms,
  dialogs,
  USDmLogos in '..\Aplicacao\USDmLogos.pas' {SDmLogos: TDSServerModule},
  UFrmAtuEnd in '..\Conexao\UFrmAtuEnd.pas' {FrmAtuEnd},
  UFrmLogos in 'UFrmLogos.pas' {FrmLogos},
  UDmContai in 'UDmContai.pas' {DmContai: TDataModule},
  UDmDicion in '..\Aplicacao\UDmDicion.pas' {DmDicion: TDataModule},
  USDmImp in 'USDmImp.pas' {SDmImp: TDSServerModule},
  USDmRW in '..\Aplicacao\USDmRW.pas' {SDmRW: TDSServerModule},
  URlc0000 in 'URlc0000.pas',
  URlc0001 in 'URlc0001.pas',
  UFrmConSrv in '..\Aplicacao\UFrmConSrv.pas' {FrmConSrv},
  URlc0003 in 'URlc0003.pas',
  UQrl0000 in '..\..\padrao\UQrl0000.pas' {Qrl0000: TQuickRep},
  UQrl0001 in 'UQrl0001.pas' {Qrl0001: TQuickRep},
  UQrl0004 in 'UQrl0004.pas' {Qrl0004: TQuickRep},
  URlc0005 in 'URlc0005.pas',
  URlc0006 in 'URlc0006.pas',
  URlc0007 in 'URlc0007.pas',
  URlc0008 in 'URlc0008.pas',
  UQrl0005 in 'UQrl0005.pas' {Qrl0005: TQuickRep},
  URlc0009 in 'URlc0009.pas',
  URlc0010 in 'URlc0010.pas',
  UQrl0007 in 'UQrl0007.pas' {Qrl0007: TQuickRep},
  UQrl0006 in 'UQrl0006.pas' {Qrl0006: TQuickRep};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  FrmConSrv := TFrmConSrv.Create(Application);
  FrmConSrv.Show;
  if FrmConSrv.ConectaServidorConexao(True) then
  begin
//        FrmConexa.Visible := False;
        Application.CreateForm(TDmDicion, DmDicion);
  Application.CreateForm(TFrmLogos, FrmLogos);
  Application.CreateForm(TDmContai, DmContai);
  FrmLogos.gNrProCli                   := 0;

        try
            DmConTai.DSServer.Stop;

            if uFrmLogos.cTpServer = 'I' then
            begin
                  DmContai.DSHTTPService.HttpPort := 8081;
                  DmContai.DSHTTPService.DSPort   := 8082;
            end;
            DmContai.DSTCPServerTransport.Port   := FrmConSrv.gNoPorta;
            DmConTai.DSServer.Start;

            Application.Title := 'Servidor de Impressão';

            Application.Run;
        except
            raise;
        end;
  end;
end.

