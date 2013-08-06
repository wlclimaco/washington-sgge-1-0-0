unit UQrl0001;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UQrl0000, QRCtrls, QuickRpt, ExtCtrls, BrvDicionario, DbClient, DB, jpeg;

type
  TQrl0001 = class(TQrl0000)
    CdsRelato: TClientDataSet;
    QRBand1: TQRBand;
    QRLabel1: TQRLabel;
    QRDBText1: TQRDBText;
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
  Qrl0001: TQrl0001;

implementation

{$R *.dfm}

function TQrl0001.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String; pNmFrmOri : String;
                                 pData      : OleVariant) : String;
begin
      inherited;

      Result := '';
      try
          CdsRelato.Data := pBrvDicion.RetornaDadosSqlCadastro(49,
                                          ProcessarParametros(pCdsParams), 'QRL0001');
      except
          on E : Exception do
          begin
                 Result := 'Erro ao gerar relatório: ' + #13 + E.Message;
          end;
      end;
end;


initialization
      RegisterClass(TQrl0001);

finalization
      UnRegisterClass(TQrl0001);

end.
