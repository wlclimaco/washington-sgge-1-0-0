unit UfrmFunctions;

interface
uses  SysUtils, Forms, Classes, Dialogs, Windows, ComCtrls, DBClient, DB,
      Variants, BrvExcel, BrvClientDataSet, Graphics, CheckLst,ACBrNFe, pcnConversao,
      ACBrNFeDANFEClass, ACBrNFeDANFERave, ACBrUtil,pcnNFeW, pcnNFeRTXT, pcnAuxiliar, ACBrDFeUtil,
      XMLIntf, XMLDoc, ACBrNFeDANFERaveCB,BrvXml;


function ListarNotasManifesto(NrSenha,NrCertificado,uf:String;XmlRetorno: AnsiString;CjEmpres:String;Operacao:Integer): OleVariant;

procedure BuscarNFePelaChave(NrSenha,NrCertificado,uf:String;pCpXML:TClientDataSet);

procedure IniciarVariaveisGlobal();

function gravarNFe(NrSenha,NrCertificado:String;CcXml:TClientDataSet):Boolean;

function Mudar_e_GravarStatusManifesto(NrSenha,NrCertificado:String;Chave,CjEmpres:String;CdEventOp:Integer):Boolean;

function DownloadNFe(NrSenha,NrCertificado,Chave,CjEmpres:String;CdEventOp :Integer):Boolean;

function StatusServico(NrSenha,NrCertificado:String):AnsiString;

function ConsultaNFeChave(NrSenha,NrCertificado,Chave:String):AnsiString;

function CancelarNFePelaChave(NrSenha,NrCertificado,Chave,idLote,CNPJ,Protocolo,Justificativa:String):AnsiString;

function ConsultarReciboLoteNFe(NrSenha,NrCertificado:String;NrLote :Integer):AnsiString;

function ConsCadDestinatario(NrSenha,NrCertificado,CdUF,CjEmpres:String):AnsiString;

function GerarPDFNFe(NrSenha,NrCertificado,Diretorio:String):AnsiString;

function ImprimirDanfe(NrSenha,NrCertificado,Diretorio:String):AnsiString;

function EnviarEmail(const NrSenha,NrCertificado:String;sSmtpHost,
                                      sSmtpPort,
                                      sSmtpUser,
                                      sSmtpPasswd,
                                      sFrom,
                                      sTo,
                                      sAssunto: String;
                                      sMensagem : TStrings;
                                      SSL : Boolean;
                                      EnviaPDF: Boolean = true;
                                      sCC: TStrings=nil;
                                      Anexos:TStrings=nil;
                                      PedeConfirma: Boolean = False;
                                      AguardarEnvio: Boolean = False;
                                      NomeRemetente: String = '';
                                      TLS : Boolean = True;
                                      UsarThread: Boolean = True):AnsiString;

function CartaDeCorrecao(NrSenha,NrCertificado,Chave, idLote, CNPJ, nSeqEvento, Correcao:String):AnsiString;

procedure Func_CDS_Duplica(Cds: TClientDataSet;CamposExcecoes:String = '');

function NfeDestinadas(NrSenha,NrCertificado,uf,CNPJ, IndNFe, IndEmi, ultNSU:String): OleVariant;


implementation

uses pcnNFe, ACBrNFeNotasFiscais, DateUtils, ACBrNFeUtil;

var CcxmlSet  :TClientDataSet;
    CpNfeDet  : TClientDataSet;
    CpNFeFat  : TClientDataSet;
    CpNFePro  : TClientDataSet;
    XML       : TBrvXML;
    ACBrNFe   : TACBrNFe;

function NfeDestinadas(NrSenha,NrCertificado,uf,CNPJ, IndNFe, IndEmi, ultNSU:String): OleVariant;
var
    ok: boolean;
    lStlAnexo :AnsiString;
begin
      IniciarVariaveisGlobal;
      ACBrNFe.Configuracoes.WebServices.UF             := uf;
      ACBrNFe.Configuracoes.Certificados.NumeroSerie   := NrCertificado;
      ACBrNFe.Configuracoes.Certificados.Senha         := NrSenha;
      ACBrNFe.Configuracoes.WebServices.Visualizar     := true;
      ACBrNFe.Configuracoes.WebServices.Ambiente       := taProducao;

      ACBrNFe.ConsultaNFeDest(CNPJ,
                               StrToIndicadorNFe(ok,indNFe),
                               StrToIndicadorEmissor(ok,IndEmi),
                               UltNSu);
      lStlAnexo := AcbrNFe.WebServices.ConsNFeDest.retConsNFeDest.XML;

      ListarNotasManifesto(NrSenha,NrCertificado,uf,AcbrNFe.WebServices.ConsNFeDest.retConsNFeDest.XML,CNPJ,1);

end;

procedure IniciarVariaveisGlobal();
begin
      try
          CcxmlSet  := TClientDataSet.Create(nil);
          XML       := TBrvXML.Create(nil);
          CpNfeDet  := TClientDataSet.Create(nil);
          CpNFeFat  := TClientDataSet.Create(nil);
          CpNFePro  := TClientDataSet.Create(nil);
          ACBrNFe   := TACBrNFe.Create(nil);
          XML.BrCdsNfeDet := CpNfeDet;
          XML.BrCdsNfeFat := CpNFeFat;
          XML.BrCdsNfePro := CpNFePro;
      finally

      end;
end;

procedure Func_CDS_Duplica(Cds: TClientDataSet;CamposExcecoes:String = '');
var
    CdsClone: TClientDataSet;
    i: integer;
    FieldName : string;
begin
     CdsClone := TClientDataSet.Create(Application);
     try
        CdsClone.CloneCursor(Cds, True);
          Cds.Append;
          for i := 0 to Cds.FieldCount-1 do
          begin
          FieldName :=  Cds.Fields[i].FieldName;
          if (Cds.FieldByName(FieldName).FieldKind<>fkData)or(FieldName = CamposExcecoes) then
              Cds.FieldByName(FieldName).Value := CdsClone.FieldByName(FieldName).Value;
          end;
          CdsClone.Close;
     finally
        CdsClone.Free;
     end;

end ;

function StatusServico(NrSenha,NrCertificado:String):AnsiString;
var
    ACBrNFe   : TACBrNFe;
begin
      try
          ACBrNFe   := TACBrNFe.Create(nil);
          ACBrNFe.Configuracoes.Certificados.NumeroSerie := NrCertificado;
          ACBrNFe.Configuracoes.Certificados.Senha       := NrSenha;

          ACBrNFe.WebServices.StatusServico.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;
end;

function ConsultaNFeChave(NrSenha,NrCertificado,Chave:String):AnsiString;
var
    ACBrNFe   : TACBrNFe;
begin
      try
          ACBrNFe   := TACBrNFe.Create(nil);
          ACBrNFe.Configuracoes.Certificados.NumeroSerie := NrCertificado;
          ACBrNFe.Configuracoes.Certificados.Senha       := NrSenha;
          ACBrNFe.WebServices.Consulta.NFeChave := Chave;
          ACBrNFe.WebServices.Consulta.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;

end;

function CancelarNFePelaChave(NrSenha,NrCertificado,Chave,idLote,CNPJ,Protocolo,Justificativa:String):AnsiString;
var
    ACBrNFe   : TACBrNFe;
begin
      try
          ACBrNFe.EventoNFe.Evento.Clear;
          with ACBrNFe.EventoNFe.Evento.Add do
           begin
                 infEvento.chNFe           := Chave;
                 infEvento.CNPJ            := CNPJ;
                 infEvento.dhEvento        := now;
                 infEvento.tpEvento        := teCancelamento;
                 infEvento.detEvento.xJust := Justificativa;
                 infEvento.detEvento.nProt := Protocolo;
           end;
          ACBrNFe.EnviarEventoNFe(StrToInt('idLote'));
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;

end;

function ConsultarReciboLoteNFe(NrSenha,NrCertificado:String;NrLote :Integer):AnsiString;
var
    ACBrNFe   : TACBrNFe;
begin
      try
          ACBrNFe.WebServices.StatusServico.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;

end;

function ConsCadDestinatario(NrSenha,NrCertificado,CdUF,CjEmpres:String):AnsiString;
var
    ACBrNFe   : TACBrNFe;
begin
      try
          ACBrNFe.WebServices.StatusServico.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;

end;

function GerarPDFNFe(NrSenha,NrCertificado,Diretorio:String):AnsiString;
var
    ACBrNFe   : TACBrNFe;
begin
      try
          ACBrNFe.WebServices.StatusServico.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;

end;

function ImprimirDanfe(NrSenha,NrCertificado,Diretorio:String):AnsiString;
var
    ACBrNFe   : TACBrNFe;
begin
      try
          ACBrNFe.WebServices.StatusServico.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;

end;

function EnviarEmail(const NrSenha,NrCertificado:String;sSmtpHost,
                                      sSmtpPort,
                                      sSmtpUser,
                                      sSmtpPasswd,
                                      sFrom,
                                      sTo,
                                      sAssunto: String;
                                      sMensagem : TStrings;
                                      SSL : Boolean;
                                      EnviaPDF: Boolean = true;
                                      sCC: TStrings=nil;
                                      Anexos:TStrings=nil;
                                      PedeConfirma: Boolean = False;
                                      AguardarEnvio: Boolean = False;
                                      NomeRemetente: String = '';
                                      TLS : Boolean = True;
                                      UsarThread: Boolean = True):AnsiString;
var
    ACBrNFe   : TACBrNFe;
begin
      try
          ACBrNFe.WebServices.StatusServico.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;

end;
function CartaDeCorrecao(NrSenha,NrCertificado,Chave, idLote, CNPJ, nSeqEvento, Correcao:String):AnsiString;
var
    ACBrNFe   : TACBrNFe;
begin
      try
          ACBrNFe.WebServices.StatusServico.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;

end;



function ListarNotasManifesto(NrSenha,NrCertificado,uf:String;XmlRetorno: AnsiString;CjEmpres:String;Operacao:Integer): OleVariant;
var lStlAnexo : AnsiString;
    lDsChaDoc : AnsiString;
    pCpXML    : TClientDataSet;
begin
      lStlAnexo := XmlRetorno;
      try
          pCpXML    := TClientDataSet.Create(nil);
          IniciarVariaveisGlobal;
          pCpXML.FieldDefs.Clear;
          pCpXML.FieldDefs.Add('NrChaDoc', ftString , 255);
          pCpXML.FieldDefs.Add('CjEmpres', ftString , 255);
          pCpXML.CreateDataSet;
          if Operacao = 1 then
          begin
                if Pos('<CHNFE>', UpperCase(lStlAnexo)) > 0 then
                begin
                      while Pos('<CHNFE>', UpperCase(lStlAnexo)) > 0 do
                      begin
                            lDsChaDoc := Copy(lStlAnexo, pos('<chNFe>',lStlAnexo) + 7, 44);
                            pCpXML.Append;
                            pCpXML.FieldByName('NrChaDoc').AsString := lDsChaDoc;
                            pCpXML.FieldByName('CjEmpres').AsString := CjEmpres;
                            pCpXML.Post;
                            delete(lStlAnexo,1, pos('<chNFe>',lStlAnexo) + 7);
                      end;
                end;
          end else
          begin
                pCpXML.Append;
                pCpXML.FieldByName('NrChaDoc').AsString := XmlRetorno;
                pCpXML.FieldByName('CjEmpres').AsString := CjEmpres;
                pCpXML.Post;
          end;
          BuscarNFePelaChave(NrSenha,NrCertificado,uf,pCpXML);
          Result := CpNfeDet.Data;
      finally
         FreeAndNil(pCpXML);
      end;

end;

procedure BuscarNFePelaChave(NrSenha,NrCertificado,uf:String;pCpXML:TClientDataSet);
var
    lCpXML    : TClientDataSet;
    lNrIndice,i : Integer;
begin
      try
          lCpXML    := TClientDataSet.Create(nil);
          CcxmlSet.Close;
          CcxmlSet.FieldDefs.Clear;
          CcxmlSet.FieldDefs.Add('SnMarcad',           ftString,   10 );
          CcxmlSet.FieldDefs.Add('xMotivo',            ftString,   99 );
          CcxmlSet.FieldDefs.Add('chNFe',              ftString,   50 );
          CcxmlSet.FieldDefs.Add('dhRecbto',           ftString,   50 );
          CcxmlSet.FieldDefs.Add('nProt',              ftString,   50 );
          CcxmlSet.FieldDefs.Add('ide_mod',            ftString,   05 );
          CcxmlSet.FieldDefs.Add('ide_natOp',          ftString,   99 );
          CcxmlSet.FieldDefs.Add('ide_nNF',            ftString,   20 );
          CcxmlSet.FieldDefs.Add('ide_serie',          ftString,   20 );
          CcxmlSet.FieldDefs.Add('ide_tpImp',          ftString,   20 );
          CcxmlSet.FieldDefs.Add('ide_tpEmis',         ftString,   20 );
          CcxmlSet.FieldDefs.Add('ide_cDV',            ftString,   20 );
          CcxmlSet.FieldDefs.Add('ide_tpAmb',          ftString,   20 );
          CcxmlSet.FieldDefs.Add('ide_dEmi',           ftString,   20 );
          CcxmlSet.FieldDefs.Add('ide_dSaiEnt',        ftString,   20 );
          CcxmlSet.FieldDefs.Add('ide_hSaiEnt',        ftString,   20 );
          CcxmlSet.FieldDefs.Add('emit_xNome',         ftString,   99 );
          CcxmlSet.FieldDefs.Add('emit_CNPJ',          ftString,   30 );
          CcxmlSet.FieldDefs.Add('emit_IE',            ftString,   30 );
          CcxmlSet.FieldDefs.Add('emit_CRT',           ftString,   05 );


          CcxmlSet.FieldDefs.Add('enderEmit_xLgr',     ftString,   99 );
          CcxmlSet.FieldDefs.Add('enderEmit_nro',      ftString,   20 );
          CcxmlSet.FieldDefs.Add('enderEmit_xBairro',  ftString,   99 );
          CcxmlSet.FieldDefs.Add('enderEmit_xMun',     ftString,   99 );
          CcxmlSet.FieldDefs.Add('enderEmit_CEP',      ftString,   20 );
          CcxmlSet.FieldDefs.Add('enderEmit_fone',     ftString,   20 );
          CcxmlSet.FieldDefs.Add('enderEmit_UF',       ftString,   05 );

          CcxmlSet.FieldDefs.Add('dest_xNome',         ftString,   99 );
          CcxmlSet.FieldDefs.Add('dest_CNPJ',          ftString,   30 );
          CcxmlSet.FieldDefs.Add('dest_CPF',           ftString,   30 );
          CcxmlSet.FieldDefs.Add('dest_IE',            ftString,   30 );
          CcxmlSet.FieldDefs.Add('dest_email',         ftString,   99 );

          CcxmlSet.FieldDefs.Add('enderDest_xLgr',     ftString,   99 );
          CcxmlSet.FieldDefs.Add('enderDest_nro',      ftString,   20 );
          CcxmlSet.FieldDefs.Add('enderDest_xBairro',  ftString,   99 );
          CcxmlSet.FieldDefs.Add('enderDest_xMun',     ftString,   99 );
          CcxmlSet.FieldDefs.Add('enderDest_CEP',      ftString,   20 );
          CcxmlSet.FieldDefs.Add('enderDest_fone',     ftString,   30 );
          CcxmlSet.FieldDefs.Add('enderDest_UF',       ftString,   05 );
          CcxmlSet.FieldDefs.Add('Xml',                ftString, 1000 );
          CcxmlSet.CreateDataSet;
          ACBrNFe.Configuracoes.WebServices.UF := uf;
          ACBrNFe.Configuracoes.Certificados.NumeroSerie   :=  NrCertificado;
          ACBrNFe.Configuracoes.Certificados.Senha         :=  NrSenha;
          ACBrNFe.Configuracoes.WebServices.Visualizar := true;
          ACBrNFe.Configuracoes.WebServices.Ambiente := taProducao;
          if pCpXML.RecordCount > 0 then
          begin
                while not pCpXML.Eof do
                begin
                      ACBrNFe.DownloadNFe.Download.Chaves.Clear;
                      ACBrNFe.DownloadNFe.Download.Chaves.Add.chNFe := pCpXML.FieldByName('NrChaDoc').AsString;
                      ACBrNFe.DownloadNFe.Download.CNPJ             := pCpXML.FieldByName('CjEmpres').AsString;
                      ACBrNFe.Configuracoes.WebServices.ProxyHost := '192.10.10.1';
                      ACBrNFe.Configuracoes.WebServices.ProxyPort := '80';
                      ACBrNFe.Configuracoes.WebServices.ProxyUser := 'administrador';
                      ACBrNFe.Configuracoes.WebServices.ProxyPass := 'brvu3025';
                      ACBrNFe.Download;
                      XML.BrXMLOriginal.Text := ACBrNFe.WebServices.DownloadNFe.retDownloadNFe.XML;
                      XML.ProcessarXml;
                      CcxmlSet.Append;
                      CcxmlSet.FieldByName('SnMarcad').Value := 'N';
                      for i := 1 to CcxmlSet.FieldCount-1 do
                      begin
                            CcxmlSet.FieldByName(CcxmlSet.Fields[i].FieldName).Value := CpNfeDet.FieldByName(CcxmlSet.Fields[i].FieldName).Value;
                      end;
                      CcxmlSet.Post;
                      pCpXML.Next;
                end;
          end;
      finally
          FreeAndNil(lCpXML);
      end;
end;

function gravarNFe(NrSenha,NrCertificado:String;CcXml:TClientDataSet):Boolean;
begin
      CcXml.Post;
      CcXml.ApplyUpdates(1);
end;

function Mudar_e_GravarStatusManifesto(NrSenha,NrCertificado:String;Chave,CjEmpres:String;CdEventOp:Integer):Boolean;
var
    ACBrNFe     : TACBrNFe;
    DescEventOp : TpcnTpEvento ;
begin
      try
          ACBrNFe := TACBrNFe.Create(nil);
          case CdEventOp of
            210200 : DescEventOp := teManifDestConfirmacao;
            210210 : DescEventOp := teManifDestCiencia;
            210220 : DescEventOp := teManifDestDesconhecimento;
            210240 : DescEventOp := teManifDestOperNaoRealizada;
          end;
          ACBrNFe.EventoNFe.Evento.Clear;
          with ACBrNFe.EventoNFe.Evento.Add do
          begin
                infEvento.chNFe    := Chave;
                infEvento.CNPJ     := CjEmpres;
                infEvento.dhEvento := now;
                infEvento.tpEvento := DescEventOp;
          end;
          ACBrNFe.EnviarEventoNFe(StrToInt('1'));
          if Pos('REJEICAO: CODIGO DO ORGAO DIVERGE DO ORGAO AUTORIZADOR', UpperCase(
              AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].RetInfEvento.xMotivo)
                                                                                       ) > 0 then
          begin
                ACBrNFe.EventoNFe.Evento.Clear;
                with ACBrNFe.EventoNFe.Evento.Add do
                begin
                      infEvento.chNFe    := Chave;
                      infEvento.CNPJ     := CjEmpres;
                      infEvento.dhEvento := now;
                      infEvento.tpEvento := DescEventOp;
                      infEvento.cOrgao   := 91;
                end;
                ACBrNFe.EnviarEventoNFe(StrToInt('1'));
                if Pos('REJEICAO', UpperCase(AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].RetInfEvento.xMotivo)) > 0 then
                begin
                      showMessage(UpperCase(AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].RetInfEvento.xMotivo));
                end;
          end;
      finally
         FreeAndNil(ACBrNFe);
      end;
end;

function DownloadNFe(NrSenha,NrCertificado,Chave,CjEmpres:String;CdEventOp :Integer):Boolean;
begin
//var
//    ACBrNFe     : TACBrNFe;
//    DescEventOp : TpcnTpEvento ;
//begin
//      try
//          case CdEventOp of
//            210200 : DescEventOp := teManifDestConfirmacao;
//            210210 : DescEventOp := teManifDestCiencia;
//            210220 : DescEventOp := teManifDestDesconhecimento;
//            210240 : DescEventOp := teManifDestOperNaoRealizada;
//        end;
//          ACBrNFe.EventoNFe.Evento.Clear;
//            with ACBrNFe.EventoNFe.Evento.Add do
//            begin
//                  infEvento.chNFe    := Chave;
//                  infEvento.CNPJ     := CjEmpres;
//                  infEvento.dhEvento := now;
//                  infEvento.tpEvento := DescEventOp;
//            end;
//            ACBrNFe.EnviarEventoNFe(StrToInt('1'));
//            if Pos('REJEICAO: CODIGO DO ORGAO DIVERGE DO ORGAO AUTORIZADOR', UpperCase(
//                AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].RetInfEvento.xMotivo)
//                                                                                         ) > 0 then
//            begin
//                  ACBrNFe.EventoNFe.Evento.Clear;
//                  with ACBrNFe.EventoNFe.Evento.Add do
//                  begin
//                        infEvento.chNFe    := Chave;
//                        infEvento.CNPJ     := CjEmpres;
//                        infEvento.dhEvento := now;
//                        infEvento.tpEvento := DescEventOp;
//                        infEvento.cOrgao   := 91;
//                  end;
//                  ACBrNFe.EnviarEventoNFe(StrToInt('1'));
//                  if Pos('REJEICAO', UpperCase(AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento
//                                                           .Items[0].RetInfEvento.xMotivo)) > 0 then
//                  begin
//                        ACBrNFe.DownloadNFe.Download.Chaves.Clear;
//                        ACBrNFe.DownloadNFe.Download.Chaves.Add.chNFe := Chave;
//                        ACBrNFe.DownloadNFe.Download.CNPJ             := CjEmpres;
//                        ACBrNFe.Download;
//              if Pos('REJEICAO',
//                         UpperCase(ACBrNFe.WebServices.DownloadNFe.retDownloadNFe.xMotivo)) = 0 then
//              begin
//                    pCpXML  := TClientDataSet.Create(nil);
//                    pCpXML.FieldDefs.Clear;
//                    pCpXML.FieldDefs.Add('NrChaDoc', ftString , 255);
//                    pCpXML.CreateDataSet;
//                    pCpXML.Append;
//                    pCpXML.FieldByName('NrChaDoc').AsString := lDsChaDoc;
//                    pCpXML.post;
//                    BuscarNFePelaChave(pCpXML);
//              end else
//              begin
//                    ShowMessage(ACBrNFe.WebServices.DownloadNFe.retDownloadNFe.xMotivo);
//              end;
//                  end else
//                  begin
//                        GravarDownloadNFe('',pCpXML.FieldByName('CdEmpres').AsInteger,2,
//                                                       pCpXML.FieldByName('NrChaDoc').AsString,now);
//                        DownloadNFe(pCpXML);
//                  end;
//            end else
//            begin
//                  if Pos('REJEICAO', UpperCase(AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento
//                                                           .Items[0].RetInfEvento.xMotivo)) > 0 then
//                  begin
//                        DownloadNFe(pCpXML);
//                  end else
//                  begin
//                        GravarDownloadNFe('',pCpXML.FieldByName('CdEmpres').AsInteger,2,
//                                                       pCpXML.FieldByName('NrChaDoc').AsString,now);
//                        DownloadNFe(pCpXML);
//                  end;
//            end;
//      finally
//
//      end;

end;

end.
