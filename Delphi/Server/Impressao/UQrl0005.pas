unit UQrl0005;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, BrvDicionario,
  Dialogs, UQrl0000, jpeg, QRCtrls, QuickRpt, ExtCtrls, BrvQrBarCode, DB, DBClient;

type
  TQrl0005 = class(TQrl0000)
    QrbCabecalho: TQRBand;
    QRLabel1: TQRLabel;
    QRLabel3: TQRLabel;
    QRLabel4: TQRLabel;
    QRLabel6: TQRLabel;
    QRLabel7: TQRLabel;
    QRLabel8: TQRLabel;
    QRLabel9: TQRLabel;
    QRLabel27: TQRLabel;
    QRLabel28: TQRLabel;
    QRLabel29: TQRLabel;
    QRLabel35: TQRLabel;
    QRLabel33: TQRLabel;
    QRDBText3: TQRDBText;
    QRDBText11: TQRDBText;
    QRDBText12: TQRDBText;
    QRDBText13: TQRDBText;
    QRDBText14: TQRDBText;
    QRDBText15: TQRDBText;
    QRDBText16: TQRDBText;
    QRDBText18: TQRDBText;
    QRDBText19: TQRDBText;
    QRDBText20: TQRDBText;
    QRDBText21: TQRDBText;
    QRDBText22: TQRDBText;
    QRShape1: TQRShape;
    QrShpMotor2: TQRShape;
    QrLblCdBarCar: TBrvQrBarCode;
    QRLabel5: TQRLabel;
    QrbColunas: TQRBand;
    QRLabel14: TQRLabel;
    QRLabel15: TQRLabel;
    QRLabel16: TQRLabel;
    QRLabel17: TQRLabel;
    QRLabel18: TQRLabel;
    QRLabel19: TQRLabel;
    QRLabel20: TQRLabel;
    QRLabel21: TQRLabel;
    QRLabel22: TQRLabel;
    QRLabel23: TQRLabel;
    QRShape6: TQRShape;
    QrbDetalhes: TQRBand;
    QrlCount: TQRDBText;
    QRDBText4: TQRDBText;
    QRDBText1: TQRDBText;
    QRDBText2: TQRDBText;
    QRDBText5: TQRDBText;
    QRDBText6: TQRDBText;
    QRDBText7: TQRDBText;
    QRDBText8: TQRDBText;
    QRDBText9: TQRDBText;
    QRDBText10: TQRDBText;
    QRBand1: TQRBand;
    QRShape3: TQRShape;
    QRShape5: TQRShape;
    QrLblTtPeso: TQRLabel;
    QrLblTtVolume: TQRLabel;
    QrLblTtMercad: TQRLabel;
    QRLabel11: TQRLabel;
    QRLabel12: TQRLabel;
    QRLabel13: TQRLabel;
    CdsCarga: TClientDataSet;
    QRShape4: TQRShape;
    QRShape9: TQRShape;
    QRShape10: TQRShape;
    QrLblQtPeso2: TQRDBText;
    QrLblPeso2: TQRLabel;
    QrLblQtVolum2: TQRDBText;
    QrLblVolum2: TQRLabel;
    QrLblNmMotor2: TQRDBText;
    QrLblMotor2: TQRLabel;
    QrlblDsVeicu2: TQRDBText;
    QrlblVeicu2: TQRLabel;
    QrLblSnImpress: TQRLabel;
    QRLabel10: TQRLabel;
    QRDBText17: TQRDBText;
    DbCdCarga: TQRDBText;
    procedure QuickRepBeforePrint(Sender: TCustomQuickRep; var PrintReport: Boolean);
    procedure QrbDetalhesBeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
    procedure QRBand1BeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
    procedure QrbCabecalhoBeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
  private
    { Private declarations }
  public
    { Public declarations }
    function GerarRelatorio(pCdsParams : TClientDataSet;
                            pBrvDicion : TBrvDicionario;
                            pNmEmpres  : String; pNmFrmOri : String;
                            pData      : OleVariant) : String; Override;

  end;

var
  Qrl0005   : TQrl0005;
  gTtPeso   : Real;
  gTtVolume : Real;
  gTtMercad : Real;

implementation

{$R *.dfm}

function TQrl0005.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String; pNmFrmOri : String;
                                 pData      : OleVariant) : String;
begin
      inherited;
      CdsCarga.Close;
      CdsCarga.Data := pBrvDicion.RetornaDadosSqlCadastro(144,
                                                   ProcessarParametros(pCdsParams), 'QRL0005');
end;

procedure TQrl0005.QRBand1BeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
begin
      inherited;
      QrLblTtPeso.Caption   := FormatFloat('#,###,##0.00', gTtPeso);
      QrLblTtVolume.Caption := FormatFloat('#,###,##0.00', gTtVolume);
      QrLblTtMercad.Caption := FormatFloat('#,###,##0.00', gTtMercad);


      QrLblSnImpress.Caption := '';

      if CdsCarga.FieldByName('SnImpres').AsString = 'S' then
      begin
            QrLblSnImpress.Caption := 'REIMPRESSÃO';
      end;

end;

procedure TQrl0005.QrbCabecalhoBeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
begin
      inherited;
      if Trim(CdsCarga.FieldByName('CdCarga').AsString) <> '' then
      begin
            QrLblCdBarCar.Digits :=
                                FormatFloat('000000000000',CdsCarga.FieldByName('CdCarga').AsFloat);
      end;
      if Trim(CdsCarga.FieldByName('DsVeicu1').AsString) = '' then
      begin
            QrShpMotor2.Visible   := False;
            QrLblQtPeso2.Visible  := False;
            QrLblPeso2.Visible    := False;
            QrLblQtVolum2.Visible := False;
            QrLblVolum2.Visible   := False;
            QrLblNmMotor2.Visible := False;
            QrLblMotor2.Visible   := False;
            QrlblDsVeicu2.Visible := False;
            QrlblVeicu2.Visible   := False;

            QrbCabecalho.Height   := 58;
      end else
      begin
            QrShpMotor2.Visible   := True;
            QrLblQtPeso2.Visible  := True;
            QrLblPeso2.Visible    := True;
            QrLblQtVolum2.Visible := True;
            QrLblVolum2.Visible   := True;
            QrLblNmMotor2.Visible := True;
            QrLblMotor2.Visible   := True;
            QrlblDsVeicu2.Visible := True;
            QrlblVeicu2.Visible   := True;

            QrbCabecalho.Height   := 75;
      end;
end;

procedure TQrl0005.QrbDetalhesBeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
begin
      inherited;

      gTtPeso   := gTtPeso   + CdsCarga.FieldByName('NrPeso').AsFloat;
      gTtVolume := gTtVolume + CdsCarga.FieldByName('QtVolume').AsFloat;
      gTtMercad := gTtMercad + CdsCarga.FieldByName('VrCtb').AsFloat;
end;

procedure TQrl0005.QuickRepBeforePrint(Sender: TCustomQuickRep; var PrintReport: Boolean);
begin
      inherited;

      gTtPeso   := 0;
      gTtVolume := 0;
      gTtMercad := 0;


end;

initialization
      RegisterClass(TQrl0005);

finalization
      UnRegisterClass(TQrl0005);

end.
