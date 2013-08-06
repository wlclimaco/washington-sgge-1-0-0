unit UQrl0006;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UQrl0000, QuickRpt, QRCtrls,BrvDicionario, qrpctrls, jpeg, ExtCtrls, DB, DBClient,
  grimgctrl, qrFramelines;

type
  TQrl0006 = class(TQrl0000)
    CpRelato: TClientDataSet;
    CpProdut: TClientDataSet;
    QRSubDetail1: TQRSubDetail;
    QRDBText3: TQRDBText;
    QRPLabel4: TQRPLabel;
    QRPLabel3: TQRPLabel;
    QRDBText4: TQRDBText;
    QRDBText1: TQRDBText;
    QRPLabel1: TQRPLabel;
    QRPLabel11: TQRPLabel;
    QRPLabel12: TQRPLabel;
    QRPLabel22: TQRPLabel;
    QRFrameline1: TQRFrameline;
    QRLabel7: TQRLabel;
    QRDBText6: TQRDBText;
    QRFrameline6: TQRFrameline;
    CpUsuari: TClientDataSet;
    CpAnexos: TClientDataSet;
    QRSysData1: TQRSysData;
    QRFrameline2: TQRFrameline;
    QRFrameline3: TQRFrameline;
    QRSubDetail12: TQRSubDetail;
    QRSubDetail13: TQRSubDetail;
    QRPLabel2: TQRPLabel;
    QRSubDetail14: TQRSubDetail;
    QRDBText2: TQRDBText;
    QRPLabel9: TQRPLabel;
    QRSubDetail16: TQRSubDetail;
    QRFrameline4: TQRFrameline;
    QRFrameline5: TQRFrameline;
    QRPLabel6: TQRPLabel;
    QRDBText9: TQRDBText;
    QRPLabel10: TQRPLabel;
    QRDBText10: TQRDBText;
    QRDBText16: TQRDBText;
    QRPLabel13: TQRPLabel;
    QRSubDetail17: TQRSubDetail;
    QRSubDetail18: TQRSubDetail;
    QRPLabel14: TQRPLabel;
    QRSubDetail19: TQRSubDetail;
    QRFrameline23: TQRFrameline;
    QRPLabel15: TQRPLabel;
    QRPLabel16: TQRPLabel;
    QRSubDetail20: TQRSubDetail;
    QRFrameline25: TQRFrameline;
    QRFrameline26: TQRFrameline;
    QRPLabel18: TQRPLabel;
    QRPLabel19: TQRPLabel;
    LblVrHorPar: TQRDBText;
    LblVrMatTer: TQRDBText;
    QRSubDetail21: TQRSubDetail;
    QRFrameline24: TQRFrameline;
    QRPLabel17: TQRPLabel;
    QRPLabel20: TQRPLabel;
    LblVrMatBra: TQRDBText;
    LblQtHorPar: TQRDBText;
    QRPLabel21: TQRPLabel;
    LblQtHorCor: TQRDBText;
    QRPLabel23: TQRPLabel;
    LblVrHorCor: TQRDBText;
    QRFrameline27: TQRFrameline;
    QRSubDetail22: TQRSubDetail;
    QRFrameline28: TQRFrameline;
    QRPLabel24: TQRPLabel;
    QRPLabel25: TQRPLabel;
    LblVrMulta: TQRDBText;
    LblVrCusInc: TQRDBText;
    QRSubDetail23: TQRSubDetail;
    QRFrameline29: TQRFrameline;
    QRPLabel26: TQRPLabel;
    QRPLabel27: TQRPLabel;
    LblVrOutDir: TQRDBText;
    LblVrMatSup: TQRDBText;
    QRSubDetail24: TQRSubDetail;
    QRFrameline30: TQRFrameline;
    QRPLabel29: TQRPLabel;
    LblVrKmRoda: TQRDBText;
    QRSubDetail25: TQRSubDetail;
    QRFrameline31: TQRFrameline;
    QRPLabel28: TQRPLabel;
    LblVrOutInd: TQRDBText;
    QRSubDetail26: TQRSubDetail;
    QRFrameline32: TQRFrameline;
    QRPLabel30: TQRPLabel;
    QRPLabel31: TQRPLabel;
    LblTtCusDir: TQRDBText;
    LblTtCusInd: TQRDBText;
    QRSubDetail27: TQRSubDetail;
    QRPLabel32: TQRPLabel;
    LblVrCusto: TQRDBText;
    QRPLabel33: TQRPLabel;
    QRPLabel34: TQRPLabel;
    QRPLabel35: TQRPLabel;
    QRPLabel36: TQRPLabel;
    QRPLabel37: TQRPLabel;
    QRPLabel38: TQRPLabel;
    QRPLabel39: TQRPLabel;
    QRPLabel40: TQRPLabel;
    QRPLabel41: TQRPLabel;
    QRPLabel42: TQRPLabel;
    QRPLabel43: TQRPLabel;
    QRPLabel44: TQRPLabel;
    QRPLabel45: TQRPLabel;
    QRSubDetail10: TQRSubDetail;
    QRSubDetail2: TQRSubDetail;
    QRPLabel7: TQRPLabel;
    GroupHeaderBand1: TQRBand;
    QRLabel1: TQRLabel;
    QRLabel3: TQRLabel;
    QRLabel4: TQRLabel;
    QRLabel5: TQRLabel;
    QRLabel6: TQRLabel;
    QRLabel10: TQRLabel;
    QRLabel11: TQRLabel;
    QRLabel13: TQRLabel;
    QRFrameline7: TQRFrameline;
    QRFrameline9: TQRFrameline;
    QRFrameline10: TQRFrameline;
    QRFrameline13: TQRFrameline;
    QRFrameline14: TQRFrameline;
    QRFrameline15: TQRFrameline;
    QRFrameline16: TQRFrameline;
    QRSubDetail3: TQRSubDetail;
    QRDBText8: TQRDBText;
    QRDBText12: TQRDBText;
    QRDBText13: TQRDBText;
    QRDBText14: TQRDBText;
    QRDBText11: TQRDBText;
    QRDBText19: TQRDBText;
    QRDBText20: TQRDBText;
    QRDBText17: TQRDBText;
    QRFrameline8: TQRFrameline;
    QRFrameline11: TQRFrameline;
    QRFrameline12: TQRFrameline;
    QRFrameline17: TQRFrameline;
    QRFrameline18: TQRFrameline;
    QRFrameline19: TQRFrameline;
    QRFrameline20: TQRFrameline;
    QRSubDetail6: TQRSubDetail;
    QRSubDetail5: TQRSubDetail;
    QRPLabel8: TQRPLabel;
    QRBand1: TQRBand;
    QRLabel8: TQRLabel;
    QRLabel12: TQRLabel;
    QRFrameline21: TQRFrameline;
    QRSubDetail4: TQRSubDetail;
    QRDBText5: TQRDBText;
    QRDBText15: TQRDBText;
    QRFrameline22: TQRFrameline;
    QRSubDetail15: TQRSubDetail;
    QRSubDetail8: TQRSubDetail;
    QRPLabel5: TQRPLabel;
    QRBand2: TQRBand;
    QRLabel9: TQRLabel;
    QRSubDetail9: TQRSubDetail;
    QRDBText7: TQRDBText;
    QRSubDetail11: TQRSubDetail;
    QRSubDetail7: TQRSubDetail;
    QRLabel24: TQRLabel;
    QRDBText24: TQRDBText;
    QRLabel25: TQRLabel;
    QRHRule4: TQRHRule;
    procedure LblVrMatTerPrint(sender: TObject; var Value: string);
    procedure LblVrMatBraPrint(sender: TObject; var Value: string);
    procedure LblVrMultaPrint(sender: TObject; var Value: string);
    procedure LblVrOutDirPrint(sender: TObject; var Value: string);
    procedure LblTtCusDirPrint(sender: TObject; var Value: string);
    procedure LblVrCustoPrint(sender: TObject; var Value: string);
    procedure LblVrHorParPrint(sender: TObject; var Value: string);
    procedure LblVrHorCorPrint(sender: TObject; var Value: string);
    procedure LblVrCusIncPrint(sender: TObject; var Value: string);
    procedure LblVrMatSupPrint(sender: TObject; var Value: string);
    procedure LblVrKmRodaPrint(sender: TObject; var Value: string);
    procedure LblVrOutIndPrint(sender: TObject; var Value: string);
    procedure LblTtCusIndPrint(sender: TObject; var Value: string);
    procedure QuickRepBeforePrint(Sender: TCustomQuickRep; var PrintReport: Boolean);
  private
    var gTxMask : String;
  public
    function GerarRelatorio(pCdsParams : TClientDataSet;
                            pBrvDicion : TBrvDicionario;
                            pNmEmpres  : String; pNmFrmOri : String;
                            pData      : OleVariant) : String; Override;
  end;

var
  Qrl0006: TQrl0006;

implementation

{$R *.dfm}
function TQrl0006.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String; pNmFrmOri : String;
                                 pData      : OleVariant) : String;
begin
      inherited;

      Result := '';
      try
          CpRelato.Data := pBrvDicion.RetornaDadosSqlCadastro(250,
                                                        ProcessarParametros(pCdsParams), 'QRL0006');
          CpProdut.Data := pBrvDicion.RetornaDadosSqlCadastro(247,
                                                        ProcessarParametros(pCdsParams), 'QRL0006');
          CpUsuari.Data := pBrvDicion.RetornaDadosSqlCadastro(248,
                                                        ProcessarParametros(pCdsParams), 'QRL0006');
          CpAnexos.Data := pBrvDicion.RetornaDadosSqlCadastro(249,
                                                        ProcessarParametros(pCdsParams), 'QRL0006');
      except
          on E : Exception do
          begin
                 Result := 'Erro ao gerar relatório: ' + #13 + E.Message;
          end;
      end;
end;


procedure TQrl0006.LblTtCusDirPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('TtCusDir').AsFloat);
end;

procedure TQrl0006.LblTtCusIndPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('TtCusInd').AsFloat);
end;

procedure TQrl0006.LblVrCusIncPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('VrCusInc').AsFloat);
end;

procedure TQrl0006.LblVrCustoPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('VrCusto').AsFloat);
end;

procedure TQrl0006.LblVrHorCorPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('VrHorCor').AsFloat);
end;

procedure TQrl0006.LblVrHorParPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('VrHorPar').AsFloat);
end;

procedure TQrl0006.LblVrKmRodaPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('VrKmRoda').AsFloat);
end;

procedure TQrl0006.LblVrMatBraPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('VrMatBra').AsFloat);
end;

procedure TQrl0006.LblVrMatSupPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('VrMatSup').AsFloat);
end;

procedure TQrl0006.LblVrMatTerPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('VrMatTer').AsFloat);
end;

procedure TQrl0006.LblVrMultaPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('VrMulta').AsFloat);
end;

procedure TQrl0006.LblVrOutDirPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('VrOutDir').AsFloat);
end;

procedure TQrl0006.LblVrOutIndPrint(sender: TObject; var Value: string);
begin
      inherited;
      Value := FormatFloat(gTxMask, CpRelato.FieldByName('VrOutInd').AsFloat);
end;

procedure TQrl0006.QuickRepBeforePrint(Sender: TCustomQuickRep; var PrintReport: Boolean);
begin
      inherited;
      gTxMask := '#,###,###,##0.00';
end;

initialization
      RegisterClass(TQrl0006);

finalization
      UnRegisterClass(TQrl0006);
end.
