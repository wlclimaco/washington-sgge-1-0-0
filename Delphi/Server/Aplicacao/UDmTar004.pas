unit UDmTar004;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UDmTarefa, FMTBcd, DB, SqlExpr, BrvDataSet, Provider, BrvProvider,
  ACBrNFe, BrvString, DBClient, BrvClientDataSet,ComCtrls;

type
  TDmTar004 = class(TDmTarefa)
    pCpXML: TBrvClientDataSet;
    BrvString: TBrvString;
    ACBrNFe: TACBrNFe;
    DcXML: TBrvProvider;
    QcXML: TBrvDataSet;
    CpConChv: TBrvClientDataSet;
    CPEmpresa: TBrvClientDataSet;
    CPChvAce: TBrvClientDataSet;
    CpXML: TBrvClientDataSet;
  private
    { Private declarations }
  public
    procedure Executar(pGauge: TProgressBar); override;
  end;

var
  DmTar004: TDmTar004;

implementation

  uses pcnNFe, ACBrNFeNotasFiscais, DateUtils, ACBrNFeUtil,UDmDicion,UImpEmail,
  UImpManif;

{$R *.dfm}

procedure TDmTar004.Executar(pGauge: TProgressBar);
begin
      CPChvAce.Close;
      CPChvAce.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(225, '', 'XML');
      CPChvAce.Open;
      while not CPChvAce.Eof do
      begin
             ConnectaReceita(CPChvAce.FieldByName('CjEmpres').AsString,
                              CPChvAce.FieldByName('NrCertif').AsString,
                              CPChvAce.FieldByName('NrSenCer').AsString,
                              CPChvAce.FieldByName('CdEstado').AsString,ACBrNFe);

             GerarStatusManifesto(CPChvAce.FieldByName('NrChadoc').AsString,
                                      CPChvAce.FieldByName('CjEmpres').AsString,
                                      CPChvAce.FieldByName('CdEmpres').AsString,
                                      210200,ACBrNFe,pCpXML);
             CPChvAce.Next;
      end;



end;

initialization
      RegisterClass(TDmTar004);
finalization
      UnRegisterClass(TDmTar004);

end.
