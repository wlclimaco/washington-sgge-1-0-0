program BrvBarCode;

uses
  Forms,
  UBarC in 'UBarC.pas' {FrmBarCode},
  QuricolCode in 'QuricolCode.pas';

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TFrmBarCode, FrmBarCode);
  Application.Title := 'Bar Code';

  Application.Run;
end.
