unit UDmTar001;

interface

uses Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, Dialogs,
     UDmTarefa, IdComponent, IdTCPConnection, IdTCPClient, IdExplicitTLSClientServerBase,
     IdMessageClient, IdPOP3, IdBaseComponent, IdMessage, IdMessageParts, IdAttachment,
     ComCtrls, DB, DBClient, BrvClientDataSet, FMTBcd, SqlExpr, BrvDataSet, Provider,
     BrvProvider, BrvString, IdSMTPBase, IdSMTP, IdIMAP4, IdText;

type
  TDmTar001 = class(TDmTarefa)
    IdMessage: TIdMessage;
    IdPOP3: TIdPOP3;
    BrvString: TBrvString;
    IdSMTP: TIdSMTP;
    IdIMAP: TIdIMAP4;
    CpXML: TBrvClientDataSet;
    DcXML: TBrvProvider;
    QcXML: TBrvDataSet;
    CPEmpresa: TBrvClientDataSet;
  private

    { Private declarations }
  public
    { Public declarations }
    procedure Executar(pGauge: TProgressBar); override;
  end;

var
  DmTar001: TDmTar001;


implementation

uses UDmDicion,UImpEmail;

const
      cHosPop   = 'pop.terra.com.br';
      cHosSmt   = 'smtp.terra.com.br';
      cSenha    = '12bra34';
      cDsConBkp = 'sped.bkp@bravolog.com.br';

{$R *.dfm}

{ TDmTar001 }

procedure TDmTar001.Executar(pGauge: TProgressBar);
begin

          IdPOP3.Host     := cHosPop;
          IdPOP3.Password := cSenha;

          pGauge.Position := 0;

          ImportarEmails('sped.cli@bravolog.com.br', cSenha, '01', CpXML, pGauge);


end;



initialization
      RegisterClass(TDmTar001);

finalization
      UnRegisterClass(TDmTar001);

end.
