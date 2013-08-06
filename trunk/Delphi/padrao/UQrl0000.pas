unit UQrl0000;

interface

uses Windows, SysUtils, Messages, Classes, Graphics, Controls,
  StdCtrls, ExtCtrls, Forms, Quickrpt, QRCtrls, jpeg, BrvDicionario, DbClient;

type
  TQrl0000 = class(TQuickRep)
    PageHeaderBand1: TQRBand;
    QRSysDataPagina: TQRSysData;
    QRSysDataDateTime: TQRSysData;
    QRLabel2: TQRLabel;
    QrLblTitle: TQRLabel;
    QrLblNmRelato: TQRLabel;
    QrImage1: TQRImage;
    QrLblSubTit: TQRLabel;
    procedure QuickRepBeforePrint(Sender: TCustomQuickRep;
      var PrintReport: Boolean);
  protected
    { Protected declarations }
    function ProcessarParametros(pCdsParams : TClientDataSet): String;
  private
    { Private declarations }
  public
    { Public declarations }
    function GerarRelatorio(pCdsParams   : TClientDataSet;
                            pBrvDicion : TBrvDicionario;
                            pNmEmpres  : String; pNmFrmOri : String;
                            pData      : OleVariant ) : String; virtual;
  end;

var
  Qrl0000: TQrl0000;

implementation


{$R *.DFM}

procedure TQrl0000.QuickRepBeforePrint(Sender: TCustomQuickRep;
                                                      var PrintReport: Boolean);
begin
//      QrImage1.Picture.Bitmap :=
end;

function TQrl0000.ProcessarParametros(pCdsParams: TClientDataSet) : String;
begin
      Result := '';
      pCdsParams.First;
      pCdsParams.Next;
      pCdsParams.Next;
      pCdsParams.Next;
      pCdsParams.Next;

      while not pCdsParams.Eof do
      begin
            Result := Result + '<%' + pCdsParams.FieldByName('NmParam').AsString + '%>;' +
                                      pCdsParams.FieldByName('DsParam').AsString + #13;
            pCdsParams.Next;
      end;
end;

function TQrl0000.GerarRelatorio(pCdsParams  : TClientDataSet;
                                  pBrvDicion : TBrvDicionario;
                                  pNmEmpres  : String; pNmFrmOri : String;
                                  pData      : OleVariant) : String;
begin
      Result := '';

      QrLblNmRelato.Caption := pNmFrmOri + ' - ' + Name;
end;


end.
