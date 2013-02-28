program Project1;

uses
  Forms,
  Unit1 in 'Unit1.pas' {Form1},
  Unit2 in 'Unit2.pas' {ModeloForm},
  Unit3 in 'Unit3.pas' {DataModule3: TDataModule},
  Unit4 in 'Unit4.pas' {ModeloForm4},
  Unit5 in 'Unit5.pas',
  Unit6 in 'Unit6.pas' {Form6},
  Unit82 in 'Unit82.pas' {Form82},
  Unit8 in 'unit8.pas' {Form7},
  Unit9 in 'Unit9.pas' {Form8},
  UNFENTRADASDDL in 'UNFENTRADASDDL.pas',
  Unit10 in 'Unit10.pas' {Form10},
  UBDLFornecedores in 'UBDLFornecedores.pas',
  Unit7 in 'Unit7.pas' {ModeloForm7},
  Unit11 in 'Unit11.pas' {ModeloForm11};

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TForm1, Form1);
  Application.CreateForm(TModeloForm4, ModeloForm4);
  Application.CreateForm(TModeloForm, ModeloForm);
  Application.CreateForm(TDataModule3, DataModule3);
  Application.CreateForm(TForm6, Form6);
  Application.CreateForm(TForm82, Form82);
  Application.CreateForm(TForm7, Form7);
  Application.CreateForm(TForm8, Form8);
  Application.CreateForm(TForm10, Form10);
  Application.CreateForm(TModeloForm7, ModeloForm7);
  Application.CreateForm(TModeloForm11, ModeloForm11);
  Application.Run;
end.
