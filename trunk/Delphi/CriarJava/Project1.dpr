program Project1;

uses
  Forms,
  Unit1 in 'Unit1.pas' {Form1},
  BrvFuncoesXE in 'BrvFuncoesXE.pas',
  UIClasseBCF in 'UIClasseBCF.pas',
  UBE in 'UBE.pas',
  UFEpas in 'UFEpas.pas',
  UBanco in 'UBanco.pas',
  UConf in 'UConf.pas';

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(TForm1, Form1);
  Application.Run;
end.
