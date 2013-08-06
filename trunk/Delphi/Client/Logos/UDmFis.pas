unit UDmFis;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, Grids, BrvDbGrids, BrvDbGrid, StdCtrls, ComCtrls, Buttons, BrvBitBtn,
  BrvRetCon, BrvEditNum, BrvComboBox, BrvCustomEdit, BrvEdit, Mask, BrvCustomMaskEdit, BrvEditDate,
  ACBrNFe, BrvListParam, ImgList, Menus, ExtCtrls, BrvSpeedButton, BrvDbNavCop, DB, DBClient,
  BrvClientDataSet,pcnConversao, BrvXml, BrvString;

type
  TDmFis = class(TDataModule)
    ACBrNFe: TACBrNFe;
    BrvXML: TBrvXML;
    QpXML: TBrvClientDataSet;
    CcNFeProd: TBrvClientDataSet;
    CcNfeFat: TBrvClientDataSet;
    gBrvString: TBrvString;
  private
    { Private declarations }
  public
    { Public declarations }
  function DownloadNFe(pCpXML: TClientDataSet):AnsiString ;
  function CancelarNotaFiscal(CdNFCan:TClientDataSet;NrAmbiente:Integer;StrJustif:String;
                                                                        StrTpNFe:String):String;
  function GerarStatusManifesto(pCpXML: TClientDataSet): boolean;
  function GravarDownloadNFe(Xml : AnsiString;cdEmpres:Integer;stmanife:Integer;nrchadoc:String;
                                                                        dtemissa:TDateTime): String;
  end;

var
  DmFis: TDmFis;

implementation

uses UDmSrvApl,UClaSrv;

{$R *.dfm}



function  TDmFis.GravarDownloadNFe(Xml : AnsiString;cdEmpres:Integer;stmanife:Integer;
                                                        nrchadoc:String;dtemissa:TDateTime): String;
var
    lCpT002      : TClientDataSet;
    lDsChaDoc    : String;
    lSrvAdm      : TSDmAdmClient;
    lDsResultSrv : String;
begin
      lCpT002 := TClientDataSet.Create(nil);
      lCpT002.FieldDefs.Clear;
      lCpT002.FieldDefs.Add('NrChaDoc', ftString,   50);
      lCpT002.FieldDefs.Add('txxml',    ftString,   10000);
      lCpT002.FieldDefs.Add('dtemissa', ftDateTime,   0);
      lCpT002.FieldDefs.Add('tpxml',    ftString,   30);
      lCpT002.FieldDefs.Add('sntransp', ftString,   3);
      lCpT002.FieldDefs.Add('cdempres', ftInteger,   0);
      lCpT002.FieldDefs.Add('stmanife', ftInteger,   0);
      lCpT002.CreateDataSet;
      lCpT002.Open;
      lCpT002.Append;
      lCpT002.FieldByName('sntransp').AsString := 'N';
      lCpT002.FieldByName('NrChaDoc').AsString := nrchadoc;
      lCpT002.FieldByName('txxml').AsString := Xml;
      lCpT002.FieldByName('dtemissa').AsDateTime := dtemissa;
      lCpT002.FieldByName('tpxml').AsString := 'NF';
      lCpT002.FieldByName('cdempres').AsInteger := cdEmpres;
      lCpT002.FieldByName('stmanife').AsInteger := stmanife;

      lCpT002.Post;

      if (lCpT002.RecordCount > 0)  then
      begin
            lSrvAdm   := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
            lDsResultSrv := lSrvAdm.GravarXMLNotaFiscal
                                         (DmSrvApl.BrvDicionario.Credencial,lCpT002.Data);
            DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResultSrv);
            result := lDsResultSrv;
      end else
      begin
            result := 'Favor informar uma NOTA FISCAL';
      end;



end;

function  TDmFis.GerarStatusManifesto(pCpXML: TClientDataSet): boolean;
begin
      try
      begin
            ACBrNFe.Configuracoes.WebServices.Visualizar := false;
            ACBrNFe.Configuracoes.WebServices.Ambiente := taProducao;
            ACBrNFe.Configuracoes.WebServices.UF := pCpXML.FieldByName('CdEstado').AsString;
            if trim(pCpXML.FieldByName('NrCertif').AsString) = '' then
            begin
                  Raise Exception.Create('Nenhum certificado cadastrado para esta empresa');
            end else
            begin
            ACBrNFe.Configuracoes.Certificados.NumeroSerie   :=
                                                            pCpXML.FieldByName('NrCertif').AsString;
            end;
            if trim(pCpXML.FieldByName('NrSenCer').AsString) = '' then
            begin
                  Raise Exception.Create('Senha do certificado não cadastrada');
            end else
            begin
            ACBrNFe.Configuracoes.Certificados.Senha         :=
                                                            pCpXML.FieldByName('NrSenCer').AsString;
            end;

            ACBrNFe.EventoNFe.Evento.Clear;
            with ACBrNFe.EventoNFe.Evento.Add do
            begin
                  infEvento.chNFe := pCpXML.FieldByName('NrChaDoc').AsString;
                  infEvento.CNPJ   := pCpXML.FieldByName('CjEmpres').AsString;
                  infEvento.dhEvento := now;
                  infEvento.tpEvento := teManifDestCiencia;
            end;
            ACBrNFe.EnviarEventoNFe(StrToInt('1'));
            if Pos('REJEICAO: CODIGO DO ORGAO DIVERGE DO ORGAO AUTORIZADOR', UpperCase(
                AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].RetInfEvento.xMotivo)
                                                                                         ) > 0 then
            begin
                  ACBrNFe.EventoNFe.Evento.Clear;
                  with ACBrNFe.EventoNFe.Evento.Add do
                  begin
                        infEvento.chNFe := pCpXML.FieldByName('NrChaDoc').AsString;
                        infEvento.CNPJ   := pCpXML.FieldByName('CjEmpres').AsString;
                        infEvento.dhEvento := now;
                        infEvento.tpEvento := teManifDestCiencia;
                        infEvento.cOrgao := 91;
                  end;
                  ACBrNFe.EnviarEventoNFe(StrToInt('1'));
                  if Pos('REJEICAO', UpperCase(AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento
                                                           .Items[0].RetInfEvento.xMotivo)) > 0 then
                  begin
                        DownloadNFe(pCpXML);
                  end else
                  begin
                        GravarDownloadNFe('',pCpXML.FieldByName('CdEmpres').AsInteger,2,
                                                       pCpXML.FieldByName('NrChaDoc').AsString,now);
                        DownloadNFe(pCpXML);
                  end;
            end else
            begin
                  if Pos('REJEICAO', UpperCase(AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento
                                                           .Items[0].RetInfEvento.xMotivo)) > 0 then
                  begin
                        DownloadNFe(pCpXML);
                  end else
                  begin
                        GravarDownloadNFe('',pCpXML.FieldByName('CdEmpres').AsInteger,2,
                                                       pCpXML.FieldByName('NrChaDoc').AsString,now);
                        DownloadNFe(pCpXML);
                  end;
            end;
      end;


      finally

      end;
end;


function TDmFis.DownloadNFe(pCpXML: TClientDataSet):AnsiString ;

begin
        while not pCpXML.Eof do
        begin
              ACBrNFe.DownloadNFe.Download.Chaves.Clear;
              ACBrNFe.DownloadNFe.Download.Chaves.Add.chNFe :=
                                                            pCpXML.FieldByName('NrChaDoc').AsString;
              ACBrNFe.DownloadNFe.Download.CNPJ             :=
                                                            pCpXML.FieldByName('CjEmpres').AsString;
              ACBrNFe.Download;
              if Pos('REJEICAO',
                         UpperCase(ACBrNFe.WebServices.DownloadNFe.retDownloadNFe.xMotivo)) = 0 then
              begin
                    GravarDownloadNFe(ACBrNFe.WebServices.DownloadNFe.retDownloadNFe.XML,
                                                       pCpXML.FieldByName('CdEmpres').AsInteger,2,
                                                       pCpXML.FieldByName('NrChaDoc').AsString,now);
              end else
              begin
                    ShowMessage(ACBrNFe.WebServices.DownloadNFe.retDownloadNFe.xMotivo);
              end;
              pCpXML.Next;
        end;
end;

function TDmFis.CancelarNotaFiscal(CdNFCan:TClientDataSet;NrAmbiente:Integer;StrJustif:String;
                                                                            StrTpNFe:String):String;
var
    lSrvAdm      : TSDmAdmClient;
    NrSerCert    : String;
    DsArquiv     : TStrings;
    lDsResultSrv : String;
    lCpT002      : TClientDataSet;
    lDsResult    : String;
begin
      try
                lCpT002 := TClientDataSet.Create(nil);
                lCpT002.FieldDefs.Clear;
                lCpT002.FieldDefs.Add('CdEmpres', ftInteger,  0);
                lCpT002.FieldDefs.Add('DsModeNF', ftString,   3);
                lCpT002.FieldDefs.Add('NrSeriNF', ftString,   6);
                lCpT002.FieldDefs.Add('NrNota',   ftInteger,  0);
                lCpT002.FieldDefs.Add('StNota',   ftString,   3);
                lCpT002.FieldDefs.Add('CdTitula', ftString,   8);
                lCpT002.FieldDefs.Add('TxRetCan', ftString, 1000);
                lCpT002.FieldDefs.Add('TpNfe', ftString, 1);
                lCpT002.CreateDataSet;

                CdNFCan.First;
                lDsResult := '';
                // 24/06/2011
                NrSerCert:= ACBrNFe.Configuracoes.Certificados.SelecionarCertificado;

                // Visualizar Mensagem
                ACBrNFe.Configuracoes.WebServices.Visualizar := true;

                // tpAmb  Identificação do Ambiente
                Case NrAmbiente of
                     0: ACBrNFe.Configuracoes.WebServices.Ambiente := taProducao;
                     1: ACBrNFe.Configuracoes.WebServices.Ambiente := taHomologacao;
                end;
                ACBrNFe.Configuracoes.WebServices.UF :=
                                                        CdNFCan.FieldByName('CdEstado').AsString;
                ACBrNFe.EventoNFe.Evento.Clear;
                while not CdNFCan.Eof do
                begin
                      if CdNFCan.FieldByName('SnMarcad').AsString = 'S' then
                      begin
                            if CdNFCan.FieldByName('NrSeriNF').AsString = '900' then
                            begin
                                  ACBrNFe.Configuracoes.Geral.FormaEmissao := teSCAN;
                            end else
                            begin
                                  ACBrNFe.Configuracoes.Geral.FormaEmissao := teNormal;
                            end;
                            if CdNFCan.FieldByName('DsProNFe').AsString <> '' then
                            begin
                                  ACBrNFe.NotasFiscais.Clear;
                                  BrvXML.BrXMLOriginal.Text   :=
                                                        CdNFCan.FieldByName('DsXmlNFe').AsString;
                                  BrvXML.ProcessarXml;
                                  ACBrNFe.EventoNFe.Evento.Clear;

                                  ACBrNFe.EventoNFe.Evento.Clear;
                                  with ACBrNFe.EventoNFe.Evento.Add do
                                  begin
                                        infEvento.chNFe := QpXML.FieldByName('chNFe').AsString;
                                        infEvento.CNPJ   := QpXML.FieldByName('emit_CNPJ').AsString;
                                        infEvento.dhEvento := now;
                                        infEvento.tpEvento := teCancelamento;
                                        infEvento.detEvento.xJust := StrJustif;
                                        infEvento.detEvento.nProt :=
                                                                QpXML.FieldByName('nProt').AsString;
                                  end;
                                  ACBrNFe.EnviarEventoNFe(StrToInt('0'));
                                  if (Pos('REJEICAO', UpperCase
                                               (ACBrNFe.WebServices.EnvEvento.RetornoWS)) = 0) then
                                  begin
                                        lCpT002.Append;
                                        lCpT002.FieldByName('CdEmpres').AsString :=
                                                           CdNFCan.FieldByName('CdEmpres').AsString;
                                        lCpT002.FieldByName('DsModeNF').AsString :=
                                                           CdNFCan.FieldByName('DsModeNF').AsString;
                                        lCpT002.FieldByName('NrSeriNF').AsString :=
                                                           CdNFCan.FieldByName('NrSeriNF').AsString;
                                        lCpT002.FieldByName('NrNota').AsString   :=
                                                             CdNFCan.FieldByName('NrNota').AsString;
                                        lCpT002.FieldByName('StNota').AsString   := 'C';
                                        lCpT002.FieldByName('TxRetCan').AsString :=
                                                                ACBrNFe.WebServices.EnvEvento.RetWS;
                                        lCpT002.FieldByName('TpNFe').AsString    := StrTpNFe;
                                        if StrTpNFe = 'E' then
                                        begin
                                              lCpT002.FieldByName('CdTitula').AsString :=
                                                           CdNFCan.FieldByName('CdTitula').AsString;
                                        end else
                                        begin
                                             lCpT002.FieldByName('CdTitula').AsString :=
                                                           CdNFCan.FieldByName('CdClient').AsString;
                                        end;
                                        lCpT002.Post;
                                  end;
                                  lDsResult := lDsResult +'chNFe:     '+
                                                          QpXML.FieldByName('chNFe').AsString + #13;
                                  lDsResult := lDsResult +'Nota:      '+
                                                         QpXML.FieldByName('ide_nNF').AsString+ #13;
                                  lDsResult := lDsResult +'Status:    '+
                                                 IntToStr(ACBrNFe.WebServices.EnvEvento.cStat)+ #13;
                                  lDsResult := lDsResult +'Mensagem:  '+
                                                             ACBrNFe.WebServices.EnvEvento.Msg+ #13;
                                  lDsResult := lDsResult +'Motivo:    '+
                                                         ACBrNFe.WebServices.EnvEvento.xMotivo+ #13;
                                  lDsResult := lDsResult +'Ret WS     '+
                                                           ACBrNFe.WebServices.EnvEvento.RetWS+ #13;
                                  lDsResult := lDsResult +'Retorno WS '+
                                                       ACBrNFe.WebServices.EnvEvento.RetornoWS+ #13;
                                  lDsResult := lDsResult +' '+ #13;
                            end;
                      end;
                      CdNFCan.Next;
                end;
                if (lCpT002.RecordCount > 0)  then
                begin
                      lSrvAdm   := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                      lDsResultSrv := lSrvAdm.CancelarNotaFiscal
                                                   (DmSrvApl.BrvDicionario.Credencial,lCpT002.Data);
                      DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResultSrv);
                end;
                ShowMessage('Notas Processada pela receita! Verifique o Retorno!');
                result := lDsResult;
      except
          FreeAndNil(lCpT002);
      end;

end;

end.
