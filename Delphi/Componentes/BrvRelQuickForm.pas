unit BrvRelQuickForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Db, DBTables, Qrctrls, QuickRpt, ExtCtrls;

type
  TRelQuick = class(TForm)
    QRRelatorio: TQuickRep;
    Detail: TQRBand;
    QRBand1: TQRBand;
    QRLabel1: TQRLabel;
    QRSysData4: TQRSysData;
    PhbCabeca: TQRBand;
    QrsNrPagina: TQRSysData;
    QrsDtSistem: TQRSysData;
    QrLblDsEmpres: TQRLabel;
    QrLblVitalSis: TQRLabel;
    QrLblNmFormul: TQRLabel;
    QrLblDsTitulo: TQRLabel;
    QrLblDsPagina: TQRLabel;
    PhbComCab: TQRBand;
    QrsLinha: TQRShape;
  private
    { Private declarations }
  public
    { Public declarations }
  end;
         
var
  RelQuick: TRelQuick;

implementation

{$R *.DFM}

end.
