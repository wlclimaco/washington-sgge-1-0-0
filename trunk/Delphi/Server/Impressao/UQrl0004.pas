unit UQrl0004;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UQrl0000, QRCtrls, QuickRpt, ExtCtrls, DbClient, BrvDicionario, DB, jpeg;

type
  TQrl0004 = class(TQrl0000)
    CdsLancto: TClientDataSet;
    QRBand1: TQRBand;
    QRDBText1: TQRDBText;
    QRDBText2: TQRDBText;
    QRDBText3: TQRDBText;
    QRDBText4: TQRDBText;
    QRDBText5: TQRDBText;
    QRDBText6: TQRDBText;
    QRDBText7: TQRDBText;
    QRDBText8: TQRDBText;
    QRDBText9: TQRDBText;
    QRDBText10: TQRDBText;
    QRLabel1: TQRLabel;
    QRLabel3: TQRLabel;
    QRLabel6: TQRLabel;
    QRLabel4: TQRLabel;
    QRLabel7: TQRLabel;
    QRLabel5: TQRLabel;
    QRLabel8: TQRLabel;
    QrsLinha: TQRShape;
    procedure QRDBText7Print(sender: TObject; var Value: string);
  private
    { Private declarations }
  public
    { Public declarations }
    function GerarRelatorio(pCdsParams : TClientDataSet; pBrvDicion : TBrvDicionario;
                            pNmEmpres  : String;         pNmFrmOri : String;
                            pData      : OleVariant) : String; Override;
  end;

var
  Qrl0004: TQrl0004;

implementation

{$R *.dfm}

{ TQrl0004 }

function TQrl0004.GerarRelatorio(pCdsParams : TClientDataSet; pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String;         pNmFrmOri : String;
                                 pData      : OleVariant) : String;
begin
      inherited;
      Result := '';
      try
          CdsLancto.Data := pBrvDicion.RetornaDadosSqlCadastro(88,
                                              ProcessarParametros(pCdsParams), 'QRL0004');
      except
          on E : Exception do
          begin
                 Result := 'Erro ao gerar relatório: ' + #13 + E.Message;
          end;
      end;
end;

procedure TQrl0004.QRDBText7Print(sender: TObject; var Value: string);
begin
      Value := FormatFloat('#,###,###,##0.00', CdsLancto.FieldByName('VrLancto').AsFloat);
end;

initialization
      RegisterClass(TQrl0004);

finalization
      UnRegisterClass(TQrl0004);

end.
