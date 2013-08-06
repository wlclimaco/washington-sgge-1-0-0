unit BrvRelatorioForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Db, DBTables, Qrctrls, QuickRpt, ExtCtrls;

type
  TFrmRelatorio = class(TForm)
    QRRelatorio: TQuickRep;
    Detail: TQRBand;
    ColumnHeader: TQRBand;
    QRBand1: TQRBand;
    QRLabel1: TQRLabel;
    QRSysData4: TQRSysData;
    PageHeaderBand1: TQRBand;
    QRSysDataPagina: TQRSysData;
    QRSysData1: TQRSysData;
    QRSysDataDateTime: TQRSysData;
  private
    { Private declarations }
  public
    { Public declarations }
  end;
         
var
  FrmRelatorio: TFrmRelatorio;

implementation

{$R *.DFM}

end.
