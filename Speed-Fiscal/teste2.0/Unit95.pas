unit Unit95;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ExtCtrls, ComCtrls;

type
  TForm95 = class(TForm)
    Timer1: TTimer;
    ProgressBar1: TProgressBar;
    procedure Timer1Timer(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form95: TForm95;

implementation

uses Unit58, Unit2;

{$R *.dfm}

procedure TForm95.Timer1Timer(Sender: TObject);
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
DataModule2.QryProdutos.Active := true;
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
DataModule2.Query2.Active := true;
ProgressBar1.Position := 35;
DataModule2.Query3.Active := true;
ProgressBar1.Position := 36;
DataModule2.tblnatureza.Active := true;
ProgressBar1.Position := 37;
fORM95.Destroy;
FRMMENU.ShowModal;}
end;

end.
