program ACBrNFe_demo;

uses
  Forms,
  Unit1 in 'Unit1.pas' {Form1},
  ufrmStatus in 'ufrmStatus.pas' {frmStatus},
  Unit82 in 'Unit82.pas' {Form82},
  UBDLFornecedores in 'UBDLFornecedores.pas',
  UNFENTRADASDDL in 'UNFENTRADASDDL.pas',
  Unit2 in 'Unit2.pas' {ModeloForm},
  Unit3 in 'Unit3.pas' {DataModule3: TDataModule},
  Unit4 in 'Unit4.pas' {ModeloForm4},
  unit5 in 'Unit5.pas',
  Unit6 in 'Unit6.pas' {Form6},
  Unit7 in 'Unit7.pas' {ModeloForm7},
  Unit8 in 'unit8.pas' {Form7},
  Unit9 in 'Unit9.pas' {Form8},
  Unit10 in 'Unit10.pas' {Form10},
  Unit11 in 'Unit11.pas' {ModeloForm11},
  Unit12 in 'Unit12.pas' {ModeloForm12},
  Unit13 in 'Unit13.pas' {ModeloForm13},
  Unit14 in 'Unit14.pas' {Form14},
  Unit15 in 'Unit15.pas';

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TForm1, Form1);
  Application.CreateForm(TfrmStatus, frmStatus);
  Application.CreateForm(TForm82, Form82);
  Application.CreateForm(TModeloForm, ModeloForm);
  Application.CreateForm(TDataModule3, DataModule3);
  Application.CreateForm(TModeloForm4, ModeloForm4);
  Application.CreateForm(TForm6, Form6);
  Application.CreateForm(TModeloForm7, ModeloForm7);
  Application.CreateForm(TForm7, Form7);
  Application.CreateForm(TForm8, Form8);
  Application.CreateForm(TForm10, Form10);
  Application.CreateForm(TModeloForm11, ModeloForm11);
  Application.CreateForm(TModeloForm12, ModeloForm12);
  Application.CreateForm(TModeloForm13, ModeloForm13);
  Application.CreateForm(TForm14, Form14);
  Application.Run;
end.
