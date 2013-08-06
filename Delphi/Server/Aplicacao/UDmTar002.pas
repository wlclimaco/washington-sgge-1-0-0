unit UDmTar002;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, ComCtrls,
  Dialogs, UDmTarefa, FMTBcd, IdSMTPBase, IdSMTP, BrvString, DB, SqlExpr, BrvDataSet, Provider,
  BrvProvider, DBClient, BrvClientDataSet, IdComponent, IdTCPConnection, IdTCPClient,
  IdExplicitTLSClientServerBase, IdMessageClient, IdPOP3, IdBaseComponent, IdMessage;

type
  TDmTar002 = class(TDmTarefa)
    IdMessage: TIdMessage;
    IdPOP3: TIdPOP3;
    CpXML: TBrvClientDataSet;
    DcXML: TBrvProvider;
    QcXML: TBrvDataSet;
    BrvString: TBrvString;
    IdSMTP: TIdSMTP;
  private
    { Private declarations }
  public
    { Public declarations }
    procedure Executar(pGauge: TProgressBar); override;
  end;

var
  DmTar002: TDmTar002;

implementation

uses UImpEmail;

const
   cDsSenha = '12bra34';


{$R *.dfm}

procedure TDmTar002.Executar(pGauge: TProgressBar);
begin
      ImportarEmails('sped.for@bravolog.com.br', cDsSenha, '01', CpXML, pGauge);
end;

initialization
      RegisterClass(TDmTar002);

finalization
      UnRegisterClass(TDmTar002);

end.
