program Project15;

uses
  Forms,
  Unit100 in 'Unit100.pas' {FrmCadMovD},
  Unit200 in 'Unit200.pas' {Form2},
  Unit300 in 'Unit300.pas' {Form3},
  Unit400 in 'Unit400.pas' {FrmBaixaTitulo},
  Unit500 in 'Unit500.pas' {FrmCadTitulos},
  Unit600 in 'Unit600.pas' {FrmAprovPag},
  Unit700 in 'Unit700.pas' {FrmCadTitPa},
  Unit800 in 'Unit800.pas' {frmlancVendasVend};

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TFrmCadMovD, FrmCadMovD);
  Application.CreateForm(TForm2, Form2);
  Application.CreateForm(TForm3, Form3);
  Application.CreateForm(TFrmBaixaTitulo, FrmBaixaTitulo);
  Application.CreateForm(TFrmCadTitulos, FrmCadTitulos);
  Application.CreateForm(TFrmAprovPag, FrmAprovPag);
  Application.CreateForm(TFrmCadTitPa, FrmCadTitPa);
  Application.CreateForm(TfrmlancVendasVend, frmlancVendasVend);
  Application.Run;
end.
