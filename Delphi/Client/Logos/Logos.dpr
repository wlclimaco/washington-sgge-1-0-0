program Logos;

uses
  Forms,
  Dialogs,
  Classes,
  Windows,
  SysUtils,
  ULogos in 'ULogos.pas' {FrmLogos},
  UFrmConCli in 'UFrmConCli.pas' {FrmConCli},
  UDmSrvApl in 'UDmSrvApl.pas' {DmSrvApl: TDataModule},
  UClaSrv in 'UClaSrv.pas',
  UFrmErrCon in 'UFrmErrCon.pas' {FrmErrCon},
  UCai0000 in '..\..\padrao\UCai0000.pas' {Cai0000},
  UCai0001 in 'UCai0001.pas' {Cai0001},
  UCai0002 in 'UCai0002.pas' {Cai0002},
  UCad0001 in 'UCad0001.pas' {Cad0001},
  UDmSis in 'UDmSis.pas' {DmSis: TDataModule},
  UCad0000 in '..\..\padrao\UCad0000.pas' {Cad0000},
  UCad0002 in 'UCad0002.pas' {Cad0002},
  UCai0003 in 'UCai0003.pas' {Cai0003},
  UCad0003 in 'UCad0003.pas' {Cad0003},
  BrvDb in '..\..\Componentes\BrvDb.pas',
  UCai0004 in 'UCai0004.pas' {Cai0004},
  UCai0005 in 'UCai0005.pas' {Cai0005},
  UCai0006 in 'UCai0006.pas' {Cai0006},
  UCai0007 in 'UCai0007.pas' {Cai0007},
  UCad0004 in 'UCad0004.pas' {Cad0004},
  UCai0008 in 'UCai0008.pas' {Cai0008},
  UCai0009 in 'UCai0009.pas' {Cai0009},
  UCai0010 in 'UCai0010.pas' {Cai0010},
  UMov0000 in '..\..\padrao\UMov0000.pas' {Mov0000},
  UMov0001 in 'UMov0001.pas' {Mov0001},
  UMov0002 in 'UMov0002.pas' {Mov0002},
  UCon0001 in 'UCon0001.pas' {Con0001},
  UMov0003 in 'UMov0003.pas' {Mov0003},
  UClaImp in 'UClaImp.pas',
  URel0000 in '..\..\padrao\URel0000.pas' {REL0000},
  URel0001 in 'URel0001.pas' {REL0001},
  UCon0002 in 'UCon0002.pas' {Con0002},
  UCon0003 in 'UCon0003.pas' {Con0003},
  UCai0011 in 'UCai0011.pas' {Cai0011},
  UCai0012 in 'UCai0012.pas' {Cai0012},
  UCon0005 in 'UCon0005.pas' {Con0005},
  UCon0004 in 'UCon0004.pas' {Con0004},
  UCon0006 in 'UCon0006.pas' {Con0006},
  BrvDominiosXE in 'BrvDominiosXE.pas',
  BrvFuncoesXE in 'BrvFuncoesXE.pas',
  UCon0007 in 'UCon0007.pas' {Con0007},
  UCai0013 in 'UCai0013.pas' {Cai0013},
  UCon0008 in 'UCon0008.pas' {Con0008},
  uCon0009 in 'uCon0009.pas' {Con0009},
  UCad0007 in 'UCad0007.pas' {Cad0007},
  UCad0008 in 'UCad0008.pas' {Cad0008},
  UDmCtb in 'UDmCtb.pas' {DmCtb: TDataModule},
  UMov0004 in 'UMov0004.pas' {Mov0004},
  UCon0010 in 'UCon0010.pas' {Con0010},
  UMov0005 in 'UMov0005.pas' {Mov0005},
  URel0002 in 'URel0002.pas' {Rel0002},
  URel0004 in 'URel0004.pas' {Rel0004},
  URel0005 in 'URel0005.pas' {Rel0005},
  UCon0011 in 'UCon0011.pas' {Con0011},
  UMov0006 in 'UMov0006.pas' {Mov0006},
  UMov0007 in 'UMov0007.pas' {Mov0007},
  UMov0008 in 'UMov0008.pas' {Mov0008},
  UMov0009 in 'UMov0009.pas' {Mov0009},
  UMov0011 in 'UMov0011.pas' {Mov0011},
  UMov0010 in 'UMov0010.pas' {Mov0010},
  UMov0012 in 'UMov0012.pas' {Mov0012},
  UMov0013 in 'UMov0013.pas' {Mov0013},
  UMov0014 in 'UMov0014.pas' {Mov0014},
  UMov0015 in 'UMov0015.pas' {Mov0015},
  URel0003 in 'URel0003.pas' {Rel0003},
  URel0006 in 'URel0006.pas' {Rel0006},
  UQrl0000 in '..\..\padrao\UQrl0000.pas' {Qrl0000: TQuickRep},
  UQrl0005 in '..\..\Server\Impressao\UQrl0005.pas' {Qrl0005: TQuickRep},
  UCon0012 in 'UCon0012.pas' {Con0012},
  UMov0016 in 'UMov0016.pas' {Mov0016},
  UMov0017 in 'UMov0017.pas' {Mov0017},
  WSStatusPedidos in 'WSStatusPedidos.pas',
  UCon0013 in 'UCon0013.pas' {Con0013},
  UMov0018 in 'UMov0018.pas' {Mov0018},
  UCon0014 in 'UCon0014.pas' {Con0014},
  UCon0015 in 'UCon0015.pas' {Con0015},
  URel0007 in 'URel0007.pas' {Rel0007},
  UMov0019 in 'UMov0019.pas' {Mov0019},
  UGravaLogScreen in 'UGravaLogScreen.pas',
  UCad0010 in 'UCad0010.pas' {Cad0010},
  UCad0009 in 'UCad0009.pas' {Cad0009},
  UCad0011 in 'UCad0011.pas' {Cad0011},
  UMov0020 in 'UMov0020.pas' {Mov0020},
  UMov0021 in 'UMov0021.pas' {MOV0021},
  UMov0022 in 'UMov0022.pas' {MOV0022},
  UMov0023 in 'UMov0023.pas' {MOV0023},
  UMov0024 in 'UMov0024.pas' {MOV0024},
  UMov0025 in 'UMov0025.pas' {MOV0025},
  UMov0026 in 'UMov0026.pas' {Mov0026},
  UDmTra in 'UDmTra.pas' {DmTra: TDataModule},
  UMov0027 in 'UMov0027.pas' {Mov0027},
  UMov0028 in 'UMov0028.pas' {Mov0028},
  UMov0029 in 'UMov0029.pas' {Mov0029},
  UCon0016 in 'UCon0016.pas' {CON0016},
  UCon0017 in 'UCon0017.pas' {Con0017},
  UCon0018 in 'UCon0018.pas' {CON0018},
  UCad0012 in 'UCad0012.pas' {Cad0012},
  UMov0033 in 'UMov0033.pas' {Mov0033},
  UMov0030 in 'UMov0030.pas' {Mov0030},
  UMov0031 in 'UMov0031.pas' {Mov0031},
  UMov0032 in 'UMov0032.pas' {Mov0032},
  UMov0034 in 'UMov0034.pas' {Mov0034},
  URel0008 in 'URel0008.pas' {Rel0008},
  UCon0020 in 'UCon0020.pas' {Con0020},
  UCon0021 in 'UCon0021.pas' {CON0021},
  UCon0019 in 'UCon0019.pas' {Con0019},
  UMov0035 in 'UMov0035.pas' {MOV0035},
  UMov0036 in 'UMov0036.pas' {Mov0036},
  UCai0014 in 'UCai0014.pas' {Cai0014},
  UCon0022 in 'UCon0022.pas' {Con0022},
  UCon0023 in 'UCon0023.pas' {Con0023},
  UCon0024 in 'UCon0024.pas' {Con0024},
  UMov0037 in 'UMov0037.pas' {Mov0037},
  UMov0038 in 'UMov0038.pas' {Mov0038},
  UMov0039 in 'UMov0039.pas' {Mov0039},
  UDmFis in 'UDmFis.pas' {DmFis: TDataModule},
  UCad0006 in 'UCad0006.pas' {Cad0006},
  UBrvISO in 'UBrvISO.pas',
  UCon0025 in 'UCon0025.pas' {Con0025};

{$R *.res}
{$R LogosResource.RES}

const gQtMaxTen = 2;

var lDsLisSer : String;
    lSnConect : Integer;
    lNrTentat : Integer;
begin
      FormatSettings.ThousandSeparator := '.';
      FormatSettings.DecimalSeparator  := ',';

      if (not FileExists('LogosProgress.exe')) then
      begin
            TResourceStream.Create(HInstance, 'Progress',RT_RCDATA).SaveToFile('LogosProgress.exe');
      end;

      if (not FileExists('Upgrade.exe')) then
      begin
            TResourceStream.Create(HInstance, 'Upgrade',RT_RCDATA).SaveToFile('Upgrade.exe');
      end;

      if (not FileExists('7za.dll')) then
      begin
            TResourceStream.Create(HInstance, '7za', RT_RCDATA).SaveToFile('7za.dll');
      end;

      Application.Initialize;
      Application.MainFormOnTaskbar := True;
      Application.CreateForm(TDmSrvApl, DmSrvApl);
  Application.CreateForm(TDmSis, DmSis);
  Application.CreateForm(TDmCtb, DmCtb);
  Application.CreateForm(TFrmLogos, FrmLogos);
  Application.CreateForm(TDmTra, DmTra);
  Application.CreateForm(TDmFis, DmFis);
  FrmConCli := TFrmConCli.Create(Application);
      FrmConCli.Show;

      lNrTentat := 0;

      repeat
             inc(lNrTentat);

             if lNrTentat > gQtMaxTen then
             begin
                   lSnConect := DmSrvApl.ConectaServidorAplicacao('ER;30');
             end else
             begin
                   lDsLisSer := FrmConCli.ConectaServidorConexao(True);
                   lSnConect := DmSrvApl.ConectaServidorAplicacao(lDsLisSer);
             end;

             if  lSnConect = 1 then
             begin
                   FrmConCli.Visible := False;

                   if ParamStr(1) <> 'N' then
                   begin
                         if DmSrvApl.AtualizarAplicacaoCliente then
                         begin
                               FrmConCli.gTcpAplCli.Disconnect; //colocar depois.
                               lSnConect := 2;
                               Application.Terminate;
                         end;
                   end;

                   if lSnConect <> 2 then
                   begin
                         if not FrmLogos.AutenticaUsuario then
                         begin
                               FrmConCli.gTcpAplCli.Disconnect;
                               lSnConect := 2;
                               Application.Terminate;
                         end;
                   end;
             end else
             begin
                   if lNrTentat > gQtMaxTen then
                   begin
                         MessageDlg('Não foi possível conectar ao servidor de conexões.' +
                                    #13#13 +
                                    'Ultrapassado o número máximo de ' +
                                    IntToStr(gQtMaxTen) + ' tentativa(s)',
                                    mtError, [mbOk], 0);
                         lSnConect := 2;
                         Application.Terminate;
                   end else
                   begin
                         sleep(1000);
                   end;
             end;
      until (lSnConect = 1) or (lSnConect = 2);

      if lSnConect <> 2 then
      begin
            FrmConCli.SolicitarServidorImpressao(1);
      end;

      Application.Run;
end.

