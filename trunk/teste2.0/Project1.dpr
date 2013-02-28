program Project1;

uses
  Forms,
  Unit1 in 'Unit1.pas' {Form1},
  Unit2 in 'Unit2.pas' {DataModule2: TDataModule},
  Unit3 in 'Unit3.pas' {Form3},
  Unit9 in 'Unit9.pas' {CONSCLIENTES},
  Unit14 in 'Unit14.pas' {consEMPREGAD},
  Unit15 in 'Unit15.pas' {ConsFornecedor},
  Unit16 in 'Unit16.pas' {ConsEntradas},
  Unit19 in 'Unit19.pas' {ConsLoja},
  Unit25 in 'Unit25.pas' {CONSPRODUTOS},
  Unit37 in 'Unit37.pas' {FRMCLIENTES},
  Unit41 in 'Unit41.pas' {FRMEMPREGAD},
  Unit42 in 'Unit42.pas' {FRMENDERECO},
  Unit46 in 'Unit46.pas' {FRMLOJAS},
  Unit53 in 'Unit53.pas' {FRMPRODUTOS},
  Unit58 in 'Unit58.pas' {FRMMENU},
  Unit59 in 'Unit59.pas' {Form59},
  Unit60 in 'Unit60.pas' {FRMNFENTRADAS},
  Unit61 in 'Unit61.pas' {Form61},
  Unit62 in 'Unit62.pas' {Form62},
  Unit63 in 'Unit63.pas' {Form63},
  Urelbancos in 'Urelbancos.pas' {RelBancos},
  URELBOLETAS in 'URELBOLETAS.pas' {RelBoletas},
  URelCap in 'URelCap.pas' {RelCap},
  Unit64 in 'Unit64.pas' {FrmExcluirtrasnf},
  Unit65 in 'Unit65.pas' {Form65},
  Unit66 in 'Unit66.pas' {Form66},
  Unit67 in 'Unit67.pas' {Form67},
  Unit68 in 'Unit68.pas' {Form68},
  Unit69 in 'Unit69.pas' {Form69},
  Unit70 in 'Unit70.pas' {Form70},
  Unit71 in 'Unit71.pas' {Form71},
  Unit72 in 'Unit72.pas' {Form72},
  Unit73 in 'Unit73.pas' {Form73},
  Unit74 in 'Unit74.pas' {Form74},
  Unit75 in 'Unit75.pas' {Form75},
  Unit76 in 'Unit76.pas' {Form76},
  Unit77 in 'Unit77.pas' {Form77},
  Unit78 in 'Unit78.pas' {Form78},
  Unit79 in 'Unit79.pas' {Form79},
  Unit80 in 'Unit80.pas' {Form80},
  Unit81 in 'Unit81.pas' {Form81},
  Unit82 in 'Unit82.pas' {Form82},
  Unit83 in 'Unit83.pas' {Form83},
  Unit84 in 'Unit84.pas' {Form84},
  Unit90 in 'Unit90.pas' {Form85},
  Unit86 in 'Unit86.pas' {Form842},
  Unit100 in 'Unit100.pas' {Form88},
  Unit101 in 'Unit101.pas' {Form91},
  Unit92 in 'Unit92.pas' {Form92},
  Unit93 in 'Unit93.pas' {Form93},
  Unit96 in 'Unit96.pas' {Form96},
  Unit97 in 'Unit97.pas' {Form97},
  Unit98 in 'Unit98.pas' {Form98},
  Unit99 in 'Unit99.pas' {Form99},
  Unit102 in 'Unit102.pas' {Form102},
  Unit103 in 'Unit103.pas' {Form103},
  Unit104 in 'Unit104.pas' {Form104},
  Unit105 in 'Unit105.pas' {Form105},
  Unit106 in 'Unit106.pas' {Form106},
  Unit107 in 'Unit107.pas' {Form107},
  Unit108 in 'Unit108.pas' {Form108},
  Unit109 in 'Unit109.pas' {Form109},
  Unit110 in 'Unit110.pas' {Form110},
  Unit111 in 'Unit111.pas' {Form111},
  Unit112 in 'Unit112.pas' {Form112},
  Unit113 in 'Unit113.pas' {Form113},
  Unit114 in 'Unit114.pas' {Form114},
  Unit115 in 'Unit115.pas' {Form115},
  Unit116 in 'Unit116.pas' {Form116},
  Unit117 in 'Unit117.pas' {Form117},
  Unit118 in 'Unit118.pas' {Form118},
  Unit119 in 'Unit119.pas' {Form119};

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TFRMMENU, FRMMENU);
  Application.CreateForm(TForm1, Form1);
  Application.CreateForm(TDataModule2, DataModule2);
  Application.CreateForm(TForm3, Form3);
  Application.CreateForm(TCONSCLIENTES, CONSCLIENTES);
  Application.CreateForm(TconsEMPREGAD, consEMPREGAD);
  Application.CreateForm(TConsFornecedor, ConsFornecedor);
  Application.CreateForm(TConsEntradas, ConsEntradas);
  Application.CreateForm(TConsLoja, ConsLoja);
  Application.CreateForm(TCONSPRODUTOS, CONSPRODUTOS);
  Application.CreateForm(TFRMCLIENTES, FRMCLIENTES);
  Application.CreateForm(TFRMEMPREGAD, FRMEMPREGAD);
  Application.CreateForm(TFRMENDERECO, FRMENDERECO);
  Application.CreateForm(TFRMLOJAS, FRMLOJAS);
  Application.CreateForm(TFRMPRODUTOS, FRMPRODUTOS);
  Application.CreateForm(TForm59, Form59);
  Application.CreateForm(TFRMNFENTRADAS, FRMNFENTRADAS);
  Application.CreateForm(TForm61, Form61);
  Application.CreateForm(TForm62, Form62);
  Application.CreateForm(TForm63, Form63);
  Application.CreateForm(TRelBancos, RelBancos);
  Application.CreateForm(TRelBoletas, RelBoletas);
  Application.CreateForm(TRelCap, RelCap);
  Application.CreateForm(TFrmExcluirtrasnf, FrmExcluirtrasnf);
  Application.CreateForm(TForm65, Form65);
  Application.CreateForm(TForm66, Form66);
  Application.CreateForm(TForm67, Form67);
  Application.CreateForm(TForm68, Form68);
  Application.CreateForm(TForm69, Form69);
  Application.CreateForm(TForm70, Form70);
  Application.CreateForm(TForm71, Form71);
  Application.CreateForm(TForm72, Form72);
  Application.CreateForm(TForm73, Form73);
  Application.CreateForm(TForm74, Form74);
  Application.CreateForm(TForm75, Form75);
  Application.CreateForm(TForm76, Form76);
  Application.CreateForm(TForm77, Form77);
  Application.CreateForm(TForm78, Form78);
  Application.CreateForm(TForm79, Form79);
  Application.CreateForm(TForm80, Form80);
  Application.CreateForm(TForm81, Form81);
  Application.CreateForm(TForm82, Form82);
  Application.CreateForm(TForm83, Form83);
  Application.CreateForm(TForm84, Form84);
  Application.CreateForm(TForm85, Form85);
  Application.CreateForm(TForm842, Form842);
  Application.CreateForm(TForm88, Form88);
  Application.CreateForm(TForm91, Form91);
  Application.CreateForm(TForm92, Form92);
  Application.CreateForm(TForm93, Form93);
  Application.CreateForm(TForm96, Form96);
  Application.CreateForm(TForm97, Form97);
  Application.CreateForm(TForm98, Form98);
  Application.CreateForm(TForm99, Form99);
  Application.CreateForm(TForm102, Form102);
  Application.CreateForm(TForm103, Form103);
  Application.CreateForm(TForm104, Form104);
  Application.CreateForm(TForm105, Form105);
  Application.CreateForm(TForm106, Form106);
  Application.CreateForm(TForm107, Form107);
  Application.CreateForm(TForm108, Form108);
  Application.CreateForm(TForm109, Form109);
  Application.CreateForm(TForm110, Form110);
  Application.CreateForm(TForm111, Form111);
  Application.CreateForm(TForm112, Form112);
  Application.CreateForm(TForm113, Form113);
  Application.CreateForm(TForm114, Form114);
  Application.CreateForm(TForm115, Form115);
  Application.CreateForm(TForm116, Form116);
  Application.CreateForm(TForm117, Form117);
  Application.CreateForm(TForm118, Form118);
  Application.CreateForm(TForm119, Form119);
  Application.Run;
end.
