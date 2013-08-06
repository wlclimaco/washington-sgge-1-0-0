unit UQrl0007;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UQrl0000, QRCtrls, qrpctrls,BrvDicionario, grimgctrl, qrFramelines, jpeg, QuickRpt, ExtCtrls, DB,
  DBClient;

type
  TQrl0007 = class(TQrl0000)
    CpRelato: TClientDataSet;
    QRSubDetail1: TQRSubDetail;
    QRSubDetail2: TQRSubDetail;
    QRSubDetail3: TQRSubDetail;
    QRFrameline2: TQRFrameline;
    QRDBText1: TQRDBText;
    QRFrameline1: TQRFrameline;
    QRLabel1: TQRLabel;
    QRDBText2: TQRDBText;
    QRLabel3: TQRLabel;
    QRLabel4: TQRLabel;
    QRFrameline4: TQRFrameline;
    QRFrameline3: TQRFrameline;
    QRFrameline5: TQRFrameline;
    QRLabel5: TQRLabel;
    QRDBText3: TQRDBText;
    QRLabel6: TQRLabel;
    QRLabel7: TQRLabel;
    QRDBText4: TQRDBText;
    QRSubDetail4: TQRSubDetail;
    QRSubDetail5: TQRSubDetail;
    QRSubDetail6: TQRSubDetail;
    QRSubDetail7: TQRSubDetail;
    QRDBText10: TQRDBText;
    QRDBText11: TQRDBText;
    QRDBText12: TQRDBText;
    QRFrameline6: TQRFrameline;
    QRSubDetail9: TQRSubDetail;
    QRSubDetail12: TQRSubDetail;
    QRSubDetail13: TQRSubDetail;
    QRLabel8: TQRLabel;
    QRDBText5: TQRDBText;
    QRLabel9: TQRLabel;
    QRPLabel11: TQRPLabel;
    QRPLabel1: TQRPLabel;
    QRPLabel2: TQRPLabel;
    QRFrameline7: TQRFrameline;
    QRFrameline8: TQRFrameline;
    QRDBText7: TQRDBText;
    QRLabel10: TQRLabel;
    QRLabel11: TQRLabel;
    QRDBText8: TQRDBText;
    QRDBText13: TQRDBText;
    QRLabel13: TQRLabel;
    QRSysData1: TQRSysData;
    LblVrCusto: TQRLabel;
    procedure QRSubDetail7BeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
  private
    { Private declarations }
  public
    function GerarRelatorio(pCdsParams : TClientDataSet;
                            pBrvDicion : TBrvDicionario;
                            pNmEmpres  : String; pNmFrmOri : String;
                            pData      : OleVariant) : String; Override;
  end;

var
  Qrl0007   : TQrl0007;
  gVrCusto  : Real;

implementation

{$R *.dfm}

function TQrl0007.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String; pNmFrmOri : String;
                                 pData      : OleVariant) : String;
begin
      inherited;

      Result := '';
      try
          CpRelato.Data := pBrvDicion.RetornaDadosSqlCadastro(246,
                                                        ProcessarParametros(pCdsParams), 'Qrl0007');
          if (CpRelato.RecordCount > 0) then
          begin
                gVrCusto := CpRelato.FieldByName('VrCusto').AsFloat;
          end;
      except
          on E : Exception do
          begin
                 Result := 'Erro ao gerar relatório: ' + #13 + E.Message;
          end;
      end;
end;


procedure TQrl0007.QRSubDetail7BeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
begin
      inherited;
      LblVrCusto.Caption  := 'R$ ' + FormatFloat('#,###,##0.00', gVrCusto);
end;

initialization
      RegisterClass(TQrl0007);

finalization
      UnRegisterClass(TQrl0007);

end.
