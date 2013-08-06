unit UDmTar003;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,ComCtrls,
  Dialogs,  FMTBcd,ACBrNFe, pcnConversao, ACBrNFeDANFEClass, ACBrNFeDANFERave, ACBrUtil,
  pcnNFeW, pcnNFeRTXT, pcnAuxiliar, ACBrDFeUtil,ACBrNFeDANFERaveCB, IdIMAP4,
  IdSMTPBase, IdSMTP,  DB, SqlExpr,Provider,  DBClient,
  IdComponent, IdTCPConnection,StdCtrls,IdTCPClient,IdExplicitTLSClientServerBase,
  IdMessageClient, IdPOP3, IdBaseComponent,IdMessage,SHDocVw,IniFiles,
  BrvString, BrvClientDataSet, UDmTarefa, BrvDataSet, BrvProvider;

type
  TDmTar003 = class(TDmTarefa)
    ACBrNFe: TACBrNFe;
    BrvString: TBrvString;
    pCpXML: TBrvClientDataSet;
    DcXML: TBrvProvider;
    QcXML: TBrvDataSet;
    CpConChv: TBrvClientDataSet;
    CPEmpresa: TBrvClientDataSet;
    CpXML: TBrvClientDataSet;
  private
    { Private declarations }
  public
    { Public declarations }
     procedure Executar(pGauge: TProgressBar); override;

  end;

var
  DmTar003: TDmTar003;
  ok:boolean;
  WBResposta: TWebBrowser;
  MemoResp: TMemo;
  memoRespWS: TMemo;
  gBrvString : TBrvString;


implementation
  uses pcnNFe, ACBrNFeNotasFiscais, DateUtils, ACBrNFeUtil,UDmDicion,UImpEmail,
  UImpManif;

{$R *.dfm}


procedure TDmTar003.Executar(pGauge: TProgressBar);
var
ok:boolean;
begin
      CPEmpresa.Close;
      CPEmpresa.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(223, '', 'XML');
      while not CPEmpresa.Eof do
      begin
            if (CPEmpresa.FieldByName('NrCertif').AsString <> '') and
                                            (CPEmpresa.FieldByName('NrSenCer').AsString<>'') then
            begin
                  ConnectaReceita(CPEmpresa.FieldByName('CjEmpres').AsString,
                                    CPEmpresa.FieldByName('NrCertif').AsString,
                                    CPEmpresa.FieldByName('NrSenCer').AsString,
                                    CPEmpresa.FieldByName('CdEstado').AsString,ACBrNFe);

                  BuscarNFeManifesto(CPEmpresa.FieldByName('CjEmpres').AsString,
                                                CPEmpresa.FieldByName('CdEmpres').AsString,
                                                                                        ACBrNFe,
                                                                                        pCpXML,
                                                                                        CPEmpresa);
            end;
            CPEmpresa.Next;
      end;
end;


initialization
      RegisterClass(TDmTar003);
finalization
      UnRegisterClass(TDmTar003);
end.
