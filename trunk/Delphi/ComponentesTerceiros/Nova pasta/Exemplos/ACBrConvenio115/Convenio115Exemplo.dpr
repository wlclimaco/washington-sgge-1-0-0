program Convenio115Exemplo;

uses
  Vcl.Forms,
  uFrmMain in 'uFrmMain.pas' {Form3},
  uFrmACBrConvenio115_PRN in 'uFrmACBrConvenio115_PRN.pas' {FrmACBrConvenio115_PRN};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(TForm3, Form3);
  Application.Run;
end.
