program LogosApl;

uses
  Forms,
  dialogs,
  ActiveX,
  Windows,
  USDmLogos in 'USDmLogos.pas' {SDmLogos: TDSServerModule},
  USDmRW in 'USDmRW.pas' {SDmRW: TDSServerModule},
  UFrmAtuEnd in '..\Conexao\UFrmAtuEnd.pas' {FrmAtuEnd},
  UDmContai in 'UDmContai.pas' {DmContai: TDataModule},
  UDmDicion in 'UDmDicion.pas' {DmDicion: TDataModule},
  USDmSis in 'USDmSis.pas' {SDmSis: TDSServerModule},
  USDmTra in 'USDmTra.pas' {SDmTra: TDSServerModule},
  UFrmLogos in 'UFrmLogos.pas' {FrmLogos},
  UCad0000 in '..\..\padrao\UCad0000.pas' {Cad0000},
  USRA0000 in '..\..\padrao\USRA0000.pas' {SRA0000},
  USRA0001 in 'USRA0001.pas' {SRA0001},
  UFrmConSrv in 'UFrmConSrv.pas' {FrmConSrv},
  USDmCon in 'USDmCon.pas' {SDmCon: TDSServerModule},
  UDmTarefa in 'UDmTarefa.pas' {DmTarefa: TDataModule},
  UDmTar001 in 'UDmTar001.pas' {DmTar001: TDataModule},
  USDmWms in 'USDmWms.pas' {SDmWms: TDSServerModule},
  UDmTar002 in 'UDmTar002.pas' {DmTar002: TDataModule},
  UImpEmail in 'UImpEmail.pas',
  UXmlPlanoContas in '..\..\padrao\UXmlPlanoContas.pas',
  USDmAdm in 'USDmAdm.pas' {SDmAdm: TDSServerModule},
  UDmAdm in 'UDmAdm.pas' {DmAdm: TDataModule},
  UDmTar003 in 'UDmTar003.pas' {DmTar003: TDataModule},
  UDmTar004 in 'UDmTar004.pas' {DmTar004: TDataModule},
  UImpManif in 'UImpManif.pas';

{$R *.res}

begin
      Application.Initialize;
      Application.MainFormOnTaskbar := True;
      FrmConSrv := TFrmConSrv.Create(Application);
      FrmConSrv.Show;

  if FrmConSrv.ConectaServidorConexao(True) then
  begin
//      FrmConSrv.Visible := False;
        Application.CreateForm(TDmDicion, DmDicion);
  Application.CreateForm(TFrmLogos, FrmLogos);
  Application.CreateForm(TDmContai, DmContai);
  Application.CreateForm(TDmAdm, DmAdm);
  Application.CreateForm(TDmTar004, DmTar004);
  FrmLogos.gNrProCli                   := 0;
        DmContai.DSTCPServerTransport.Port   := FrmConSrv.gNoPorta;
        DmConTai.DSServer.Start;
        Application.Run;
  end;
end.

