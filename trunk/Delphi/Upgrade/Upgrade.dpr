program Upgrade;

uses
  Forms,
  UFrmUpgrad in 'UFrmUpgrad.pas' {FrmUpgrad};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(TFrmUpgrad, FrmUpgrad);
  Application.Run;
end.
