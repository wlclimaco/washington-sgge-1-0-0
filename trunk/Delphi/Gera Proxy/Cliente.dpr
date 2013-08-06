program Cliente;

uses
  Forms,
  uFrmClient in 'uFrmClient.pas' {FrmClient},
  UClaSrv in '..\Client\Logos\UClaSrv.pas';

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(TFrmClient, FrmClient);
  Application.Run;
end.
