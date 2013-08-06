program LogosCnx;

uses
  Forms,
  messages,
  dialogs,
  UFrmSerCon in 'UFrmSerCon.pas' {FrmSerCon},
  UFrmAtuEnd in 'UFrmAtuEnd.pas' {FrmAtuEnd},
  UClaSrv in '..\..\Client\Logos\UClaSrv.pas';

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(TFrmSerCon, FrmSerCon);
  if FrmSerCon.AtualizarEnderecoConexao then
  begin
        FrmSerCon.InicializarTreeVeiew;
        FrmSerCon.ConectarServer;
        Application.Run;
  end;
end.
