program ACBrNFe_demo;

uses
  Forms,
  Unit1 in 'Unit1.pas' {Form1},
  ufrmStatus in 'ufrmStatus.pas' {frmStatus},
  UfrmFunctions in 'UfrmFunctions.pas',
  Unit2 in 'Unit2.pas' {DmNfe: TDataModule};

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TForm1, Form1);
  Application.CreateForm(TfrmStatus, frmStatus);
  Application.CreateForm(TDmNfe, DmNfe);
  Application.Run;
end.
