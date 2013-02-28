unit Unit58;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Menus, ExtCtrls, Buttons, jpeg, ComCtrls, StdCtrls;

type
  TFRMMENU = class(TForm)
    MainMenu1: TMainMenu;
    CADASTROS1: TMenuItem;
    LOJAS1: TMenuItem;
    CLIFORM1: TMenuItem;
    EMPREGADO1: TMenuItem;
    ESTOQUE1: TMenuItem;
    PRODUTOS1: TMenuItem;
    INVENTARIO1: TMenuItem;
    PEDIDOS1: TMenuItem;
    FINANAS1: TMenuItem;
    CAIXA1: TMenuItem;
    CAP1: TMenuItem;
    IMPRBOLETOS1: TMenuItem;
    UTIL1: TMenuItem;
    SAIR1: TMenuItem;
    MANUTENO2: TMenuItem;
    CONSULTA2: TMenuItem;
    MANUTENO3: TMenuItem;
    MANUTENO8: TMenuItem;
    CONSULTA3: TMenuItem;
    CONSULTA8: TMenuItem;
    MANUTENO10: TMenuItem;
    CONSULTA10: TMenuItem;
    CONSULTA11: TMenuItem;
    MANUTENO13: TMenuItem;
    CONSULTAR1: TMenuItem;
    MANUTENO15: TMenuItem;
    CONSULTA15: TMenuItem;
    NATUREZA1: TMenuItem;
    MANUTENO20: TMenuItem;
    CONSULTA21: TMenuItem;
    FINANCEIRO1: TMenuItem;
    BAIXARTITULOS1: TMenuItem;
    MANUTENO21: TMenuItem;
    RELATORIO1: TMenuItem;
    RELATORIO2: TMenuItem;
    ENTRADAS1: TMenuItem;
    ENTRADANOTAFISCAL1: TMenuItem;
    ETIQUETA1: TMenuItem;
    IMPRIMIRETIQUETA1: TMenuItem;
    form771: TMenuItem;
    Form651: TMenuItem;
    form661: TMenuItem;
    form621: TMenuItem;
    form611: TMenuItem;
    form751: TMenuItem;
    Sintegra1: TMenuItem;
    LESINTEGRA1: TMenuItem;
    SAIDAS1: TMenuItem;
    BAIXAS2: TMenuItem;
    ENTRADA1: TMenuItem;
    CONSULTAR2: TMenuItem;
    SAIDASDEPRODUTOS1: TMenuItem;
    REDUOZ1: TMenuItem;
    RELA1: TMenuItem;
    RELATORIOSINTEGRA1: TMenuItem;
    SAIDASDEPRODUTO1: TMenuItem;
    GRAFICOSAIDAS1: TMenuItem;
    ProgressBar1: TProgressBar;
    RELATORIODEENTRADADEPRODUTOS1: TMenuItem;
    teste1: TMenuItem;
    GRAFICOSAIDAPRODUTO1: TMenuItem;
    inventario2: TMenuItem;
    este1: TMenuItem;
    acertardatatitulos1: TMenuItem;
    Panel1: TPanel;
    Panel2: TPanel;
    GroupBox1: TGroupBox;
    GroupBox2: TGroupBox;
    GroupBox3: TGroupBox;
    Memo1: TMemo;
    Memo2: TMemo;
    Memo3: TMemo;
    procedure MANUTENO1Click(Sender: TObject);
    procedure MANUTENO2Click(Sender: TObject);
    procedure CONSULTA2Click(Sender: TObject);
    procedure MANUTENO3Click(Sender: TObject);
    procedure MANUTENO5Click(Sender: TObject);
    procedure MANUTENO6Click(Sender: TObject);
    procedure MANUTENO7Click(Sender: TObject);
    procedure MANUTENO8Click(Sender: TObject);
    procedure MANUTENO9Click(Sender: TObject);
    procedure CONSULTA5Click(Sender: TObject);
    procedure CONSULTA6Click(Sender: TObject);
    procedure CONSULTA7Click(Sender: TObject);
    procedure CONSULTA8Click(Sender: TObject);
    procedure CONSULTA9Click(Sender: TObject);
    procedure MANUTENO10Click(Sender: TObject);
    procedure CONSULTA10Click(Sender: TObject);
    procedure CONSULTA11Click(Sender: TObject);
    procedure MANUTENO11Click(Sender: TObject);
    procedure CONSULTA12Click(Sender: TObject);
    procedure MANUTENO12Click(Sender: TObject);
    procedure CONSULTA13Click(Sender: TObject);
    procedure MANUTENO14Click(Sender: TObject);
    procedure CONSULTA14Click(Sender: TObject);
    procedure MANUTENO15Click(Sender: TObject);
    procedure CONSULTA15Click(Sender: TObject);
    procedure MANUTENAO1Click(Sender: TObject);
    procedure CONSULTA16Click(Sender: TObject);
    procedure MANUTENO16Click(Sender: TObject);
    procedure CONSULTA17Click(Sender: TObject);
    procedure MANUTENO17Click(Sender: TObject);
    procedure CONSULTA18Click(Sender: TObject);
    procedure MANUTENO18Click(Sender: TObject);
    procedure CONSULTA19Click(Sender: TObject);
    procedure MANUTENO19Click(Sender: TObject);
    procedure CONSULTA20Click(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure SpeedButton4Click(Sender: TObject);
    procedure CONSULTA3Click(Sender: TObject);
    procedure SpeedButton8Click(Sender: TObject);
    procedure SpeedButton7Click(Sender: TObject);
    procedure SpeedButton6Click(Sender: TObject);
    procedure MANUTENO20Click(Sender: TObject);
    procedure SpeedButton5Click(Sender: TObject);
    procedure SAIR1Click(Sender: TObject);
    procedure RELATORIO1Click(Sender: TObject);
    procedure form771Click(Sender: TObject);
    procedure Form651Click(Sender: TObject);
    procedure form661Click(Sender: TObject);
    procedure IMPRIMIRETIQUETA1Click(Sender: TObject);
    procedure form621Click(Sender: TObject);
    procedure form611Click(Sender: TObject);
    procedure form751Click(Sender: TObject);
    procedure LESINTEGRA1Click(Sender: TObject);
    procedure BAIXAS2Click(Sender: TObject);
    procedure ENTRADA1Click(Sender: TObject);
    procedure CONSULTAR2Click(Sender: TObject);
    procedure RELATORIO2Click(Sender: TObject);
    procedure MANUTENO21Click(Sender: TObject);
    procedure SAIDASDEPRODUTO1Click(Sender: TObject);
    procedure GRAFICOSAIDAS1Click(Sender: TObject);
    procedure FormActivate(Sender: TObject);
    procedure RELA1Click(Sender: TObject);
    procedure RELATORIODEENTRADADEPRODUTOS1Click(Sender: TObject);
    procedure teste1Click(Sender: TObject);
    procedure GRAFICOSAIDAPRODUTO1Click(Sender: TObject);
    procedure inventario2Click(Sender: TObject);
    procedure este1Click(Sender: TObject);
    procedure CAP1Click(Sender: TObject);
    procedure IMPRBOLETOS1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure acertardatatitulos1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FRMMENU: TFRMMENU;

implementation

uses Unit10, Unit11, Unit12, Unit13, Unit14, Unit15, Unit16, Unit17,
  Unit18, Unit19, Unit2, Unit20, Unit21, Unit22, Unit23, Unit24, Unit25,
  Unit26, Unit27, Unit28, Unit29, Unit3, Unit30, Unit31, Unit32, Unit33,
  Unit34, Unit35, Unit36, Unit37, Unit38, Unit39, Unit4, Unit40, Unit41,
  Unit42, Unit43, Unit44, Unit45, Unit46, Unit47, Unit48, Unit49, Unit5,
  Unit50, Unit51, Unit52, Unit53, Unit54, Unit55, Unit56, Unit57, Unit6,
  Unit7, Unit8, Unit9, Unit59, Unit60, Unit61, Unit62, Unit64, Unit65,
  Unit66, Unit70, Unit73, Unit75, Unit77, Unit80, Unit82, Unit83, Unit90,
  Unit86, Unit84, Unit100, Unit92, Unit94, Unit96, Unit98, Unit102,
  Unit103, Unit104, Unit106, Unit108, Unit111, Unit118;

{$R *.dfm}

procedure TFRMMENU.MANUTENO1Click(Sender: TObject);
begin
FRMENDERECO.ShowModal;
end;

procedure TFRMMENU.MANUTENO2Click(Sender: TObject);
begin
FRMLOJAS.ShowModal;
end;

procedure TFRMMENU.CONSULTA2Click(Sender: TObject);
begin
ConsLoja.ShowModal;
end;

procedure TFRMMENU.MANUTENO3Click(Sender: TObject);
begin
FRMCLIENTES.ShowModal;
end;

procedure TFRMMENU.MANUTENO5Click(Sender: TObject);
begin
FRMINDICE.ShowModal;
end;

procedure TFRMMENU.MANUTENO6Click(Sender: TObject);
begin
FRMMATERIAL.ShowModal;
end;

procedure TFRMMENU.MANUTENO7Click(Sender: TObject);
begin
FRMPLCONTA.ShowModal;
end;

procedure TFRMMENU.MANUTENO8Click(Sender: TObject);
begin
FRMEMPREGAD.ShowModal;
end;

procedure TFRMMENU.MANUTENO9Click(Sender: TObject);
begin
FRMBANCOS.ShowModal;
end;

procedure TFRMMENU.CONSULTA5Click(Sender: TObject);
begin
ConsIndice.ShowModal;
end;

procedure TFRMMENU.CONSULTA6Click(Sender: TObject);
begin
ConsMaterial.ShowModal;
end;

procedure TFRMMENU.CONSULTA7Click(Sender: TObject);
begin
CONSPLCONTA.ShowModal;
end;

procedure TFRMMENU.CONSULTA8Click(Sender: TObject);
begin
consEMPREGAD.ShowModal;
end;

procedure TFRMMENU.CONSULTA9Click(Sender: TObject);
begin
CONSCXBANCOS.ShowModal;
end;

procedure TFRMMENU.MANUTENO10Click(Sender: TObject);
begin
FRMPRODUTOS.ShowModal;
end;

procedure TFRMMENU.CONSULTA10Click(Sender: TObject);
begin
CONSPRODUTOS.ShowModal;
end;

procedure TFRMMENU.CONSULTA11Click(Sender: TObject);
begin
CONSPRODUTOS.ShowModal;
end;

procedure TFRMMENU.MANUTENO11Click(Sender: TObject);
begin
FRMTRASNF.ShowModal;
end;

procedure TFRMMENU.CONSULTA12Click(Sender: TObject);
begin
CONSTRASFER.ShowModal;
end;

procedure TFRMMENU.MANUTENO12Click(Sender: TObject);
begin
FRMBAIXAS.ShowModal;
end;

procedure TFRMMENU.CONSULTA13Click(Sender: TObject);
begin
   CONSBAIXAS.ShowModal;
end;

procedure TFRMMENU.MANUTENO14Click(Sender: TObject);
begin
FRMETIQUETA.ShowModal;
end;

procedure TFRMMENU.CONSULTA14Click(Sender: TObject);
begin
ConsEtiqueta.ShowModal;
end;

procedure TFRMMENU.MANUTENO15Click(Sender: TObject);
begin
form85.ShowModal;
end;

procedure TFRMMENU.CONSULTA15Click(Sender: TObject);
begin
consMOVCAIXA.ShowModal;
end;

procedure TFRMMENU.MANUTENAO1Click(Sender: TObject);
begin
FRMBANCOS.ShowModal;
end;

procedure TFRMMENU.CONSULTA16Click(Sender: TObject);
begin
CONSBANCOS.ShowModal;
end;

procedure TFRMMENU.MANUTENO16Click(Sender: TObject);
begin
FRMCAR.ShowModal;
end;

procedure TFRMMENU.CONSULTA17Click(Sender: TObject);
begin
CONSCAR.ShowModal;
end;

procedure TFRMMENU.MANUTENO17Click(Sender: TObject);
begin
FRMCAP.ShowModal;
end;

procedure TFRMMENU.CONSULTA18Click(Sender: TObject);
begin
CONSCAP.ShowModal;
end;

procedure TFRMMENU.MANUTENO18Click(Sender: TObject);
begin
FRMBOLETAS.ShowModal;
end;

procedure TFRMMENU.CONSULTA19Click(Sender: TObject);
begin
CONSBOLETAS.ShowModal;
end;

procedure TFRMMENU.MANUTENO19Click(Sender: TObject);
begin
FRMCOC.ShowModal;
end;

procedure TFRMMENU.CONSULTA20Click(Sender: TObject);
begin
ConsCOC.ShowModal;
end;

procedure TFRMMENU.SpeedButton1Click(Sender: TObject);
begin
Form75.Show;
end;

procedure TFRMMENU.SpeedButton2Click(Sender: TObject);
begin
FRMNFENTRADAS.ShowModal;
end;

procedure TFRMMENU.SpeedButton3Click(Sender: TObject);
begin
Form61.ShowModal;
end;

procedure TFRMMENU.SpeedButton4Click(Sender: TObject);
begin
form62.ShowModal;
end;

procedure TFRMMENU.CONSULTA3Click(Sender: TObject);
begin
CONSCLIENTES.Show;
end;

procedure TFRMMENU.SpeedButton8Click(Sender: TObject);
begin
Form77.Show;
end;

procedure TFRMMENU.SpeedButton7Click(Sender: TObject);
begin
Form65.Show;
end;

procedure TFRMMENU.SpeedButton6Click(Sender: TObject);
begin
form66.Show;
end;

procedure TFRMMENU.MANUTENO20Click(Sender: TObject);
begin
FORM70.Show;
end;

procedure TFRMMENU.SpeedButton5Click(Sender: TObject);
begin
form73.Show;
end;

procedure TFRMMENU.SAIR1Click(Sender: TObject);
begin
close();
end;

procedure TFRMMENU.RELATORIO1Click(Sender: TObject);
begin
Form80.Show;
end;

procedure TFRMMENU.form771Click(Sender: TObject);
begin
Form77.Show;
end;

procedure TFRMMENU.Form651Click(Sender: TObject);
begin
Form65.Show;
end;

procedure TFRMMENU.form661Click(Sender: TObject);
begin
form66.show;
end;

procedure TFRMMENU.IMPRIMIRETIQUETA1Click(Sender: TObject);
begin
form73.show;
end;

procedure TFRMMENU.form621Click(Sender: TObject);
begin
form62.show;
end;

procedure TFRMMENU.form611Click(Sender: TObject);
begin
form61.show;
end;

procedure TFRMMENU.form751Click(Sender: TObject);
begin
Form75.Show;
end;

procedure TFRMMENU.LESINTEGRA1Click(Sender: TObject);
begin
FORM82.Show;
end;

procedure TFRMMENU.BAIXAS2Click(Sender: TObject);
begin
FORM83.Show;
end;

procedure TFRMMENU.ENTRADA1Click(Sender: TObject);
begin
FRMNFENTRADAS.show;
end;

procedure TFRMMENU.CONSULTAR2Click(Sender: TObject);
begin
ConsEntradas.Show;
end;

procedure TFRMMENU.RELATORIO2Click(Sender: TObject);
begin
  form88.Show;
end;

procedure TFRMMENU.MANUTENO21Click(Sender: TObject);
begin
Form92.Show;
end;

procedure TFRMMENU.SAIDASDEPRODUTO1Click(Sender: TObject);
begin
Form842.Show;
end;

procedure TFRMMENU.GRAFICOSAIDAS1Click(Sender: TObject);
begin
fORM94.Show;
end;

procedure TFRMMENU.FormActivate(Sender: TObject);
begin
{ProgressBar1.Min := 1 ;
ProgressBar1.Max := 37;
DataModule2.TBLBAIXAS.Active := true;
ProgressBar1.Position := 1;
DataModule2.TBLBANCOS.Active := true;
ProgressBar1.Position := 2;
DataModule2.TBLBOLETAS.Active := true;
ProgressBar1.Position := 3;
DataModule2.TBLCAP.Active := true;
ProgressBar1.Position := 4;
DataModule2.TBLCLIENTES.Active := true;
ProgressBar1.Position := 5;
DataModule2.TBLCOC.Active := true;
ProgressBar1.Position := 6;
DataModule2.TBLCXBANCOS.Active := true;
ProgressBar1.Position := 7;
DataModule2.TBLCXLANCAM.Active := true;
ProgressBar1.Position := 8;
DataModule2.TBLEMPREGAD.Active := true;
ProgressBar1.Position := 9;
DataModule2.qryMovEstoque.Active := true;
ProgressBar1.Position := 10;
DataModule2.tblENDERECO.Active := true;
ProgressBar1.Position := 11;
DataModule2.tblENTRADAS.Active := true;
ProgressBar1.Position := 12;
DataModule2.tblETIQUETA.Active := true;
ProgressBar1.Position := 13;
DataModule2.tblINDICE.Active := true;
ProgressBar1.Position := 14;
DataModule2.tblLOJAS.Active := true;
ProgressBar1.Position := 15;
DataModule2.tblMATERIAL.Active := true;
ProgressBar1.Position := 16;
DataModule2.tblMERCAD.Active := true;
ProgressBar1.Position := 17;
DataModule2.tblMOVCAIXA.Active := true;
ProgressBar1.Position := 18;
DataModule2.tblPLCONTA.Active := true;
ProgressBar1.Position := 19;
DataModule2.tblXMATERIA.Active := true;
DataModule2.tblPRECOS.Active := true;
ProgressBar1.Position := 20;
DataModule2.tblprodutos.Active := true;
ProgressBar1.Position := 21;
DataModule2.tblPRINTERS.Active := true;
ProgressBar1.Position := 22;
DataModule2.tblPWGRUPOS.Active := true;
ProgressBar1.Position := 23;
DataModule2.tblPWTABELA.Active := true;
ProgressBar1.Position := 24;
DataModule2.tblPWUSUA.Active := true;
ProgressBar1.Position := 25;
DataModule2.tblTRANSFER.Active := true;
ProgressBar1.Position := 26;
DataModule2.tblVENDAS.Active := true;
ProgressBar1.Position := 27;
DataModule2.tbletiques.Active := true;
ProgressBar1.Position := 28;
DataModule2.QryProdutos.Active := False;
ProgressBar1.Position := 29;
DataModule2.qryclientes.Active := true;
ProgressBar1.Position := 30;
DataModule2.tblnfentradas.Active := true;
ProgressBar1.Position := 31;
DataModule2.Tbtitulospagar.Active := true;
ProgressBar1.Position := 32;
DataModule2.qrytitulospagar.Active := true;
ProgressBar1.Position := 33;
DataModule2.QYBAIXAS.Active := true;
ProgressBar1.Position := 34;
//DataModule2.Query2.Active := true;
ProgressBar1.Position := 35;
DataModule2.Query3.Active := true;
ProgressBar1.Position := 36;
//DataModule2.tblnatureza.Active := true;
ProgressBar1.Position := 37;  }
end;

procedure TFRMMENU.RELA1Click(Sender: TObject);
begin
form96.Show;
end;

procedure TFRMMENU.RELATORIODEENTRADADEPRODUTOS1Click(Sender: TObject);
begin
Form98.Show;
end;

procedure TFRMMENU.teste1Click(Sender: TObject);
begin
Form102.show;
end;

procedure TFRMMENU.GRAFICOSAIDAPRODUTO1Click(Sender: TObject);
begin
FORM103.Show;
end;

procedure TFRMMENU.inventario2Click(Sender: TObject);
begin
 form104.Show;
end;

procedure TFRMMENU.este1Click(Sender: TObject);
begin
Form106.Show;
end;

procedure TFRMMENU.CAP1Click(Sender: TObject);
begin
fORM108.Show;
end;

procedure TFRMMENU.IMPRBOLETOS1Click(Sender: TObject);
begin
fORM111.Show;
end;

procedure TFRMMENU.FormCreate(Sender: TObject);
begin
{ProgressBar1.Min := 1 ;
ProgressBar1.Max := 37;
//DataModule2.TBLBAIXAS.Active := true;
ProgressBar1.Position := 1;
//DataModule2.TBLBANCOS.Active := true;
ProgressBar1.Position := 2;
//DataModule2.TBLBOLETAS.Active := true;
ProgressBar1.Position := 3;
//DataModule2.TBLCAP.Active := true;
ProgressBar1.Position := 4;
DataModule2.TBLCLIENTES.Active := true;
ProgressBar1.Position := 5;
//DataModule2.TBLCOC.Active := true;
ProgressBar1.Position := 6;
//DataModule2.TBLCXBANCOS.Active := true;
ProgressBar1.Position := 7;
//DataModule2.TBLCXLANCAM.Active := true;
ProgressBar1.Position := 8;
DataModule2.TBLEMPREGAD.Active := true;
ProgressBar1.Position := 9;
DataModule2.qryMovEstoque.Active := true;
ProgressBar1.Position := 10;
DataModule2.tblENDERECO.Active := true;
ProgressBar1.Position := 11;
DataModule2.tblENTRADAS.Active := true;
ProgressBar1.Position := 12;
DataModule2.tblETIQUETA.Active := true;
ProgressBar1.Position := 13;
DataModule2.tblINDICE.Active := true;
ProgressBar1.Position := 14;
DataModule2.tblLOJAS.Active := true;
ProgressBar1.Position := 15;
//DataModule2.tblMATERIAL.Active := true;
ProgressBar1.Position := 16;
DataModule2.tblMERCAD.Active := true;
ProgressBar1.Position := 17;
DataModule2.tblMOVCAIXA.Active := true;
ProgressBar1.Position := 18;
DataModule2.tblPLCONTA.Active := true;
ProgressBar1.Position := 19;
DataModule2.tblXMATERIA.Active := true;
DataModule2.tblPRECOS.Active := true;
ProgressBar1.Position := 20;
DataModule2.tblprodutos.Active := true;
ProgressBar1.Position := 21;
DataModule2.tblPRINTERS.Active := true;
ProgressBar1.Position := 22;
DataModule2.tblPWGRUPOS.Active := true;
ProgressBar1.Position := 23;
DataModule2.tblPWTABELA.Active := true;
ProgressBar1.Position := 24;
DataModule2.tblPWUSUA.Active := true;
ProgressBar1.Position := 25;
DataModule2.tblTRANSFER.Active := true;
ProgressBar1.Position := 26;
DataModule2.tblVENDAS.Active := true;
ProgressBar1.Position := 27;
DataModule2.tbletiques.Active := true;
ProgressBar1.Position := 28;
DataModule2.QryProdutos.Active := False;
ProgressBar1.Position := 29;
DataModule2.qryclientes.Active := true;
ProgressBar1.Position := 30;
DataModule2.tblnfentradas.Active := true;
ProgressBar1.Position := 31;
DataModule2.Tbtitulospagar.Active := true;
ProgressBar1.Position := 32;
DataModule2.qrytitulospagar.Active := true;
ProgressBar1.Position := 33;
DataModule2.QYBAIXAS.Active := true;
ProgressBar1.Position := 34;
//DataModule2.Query2.Active := true;
ProgressBar1.Position := 35;
DataModule2.Query3.Active := true;
ProgressBar1.Position := 36;
//DataModule2.tblnatureza.Active := true;
ProgressBar1.Position := 37;  }
end;

procedure TFRMMENU.acertardatatitulos1Click(Sender: TObject);
begin
  form118.Show;
end;

end.
