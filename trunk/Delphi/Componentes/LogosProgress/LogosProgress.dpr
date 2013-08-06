program LogosProgress;

uses
  Forms,
  UBrvAlertProgress in 'UBrvAlertProgress.pas' {BrvProgressSvr};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(TBrvProgressSvr, BrvProgressSvr);
  Application.Run;
end.
