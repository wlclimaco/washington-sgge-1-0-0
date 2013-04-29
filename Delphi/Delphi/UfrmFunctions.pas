unit UfrmFunctions;

interface
uses  SysUtils, Forms, Classes, Dialogs, Windows, ComCtrls, DBClient, DB,
      Variants, BrvExcel, BrvClientDataSet, Graphics, CheckLst,ACBrNFe, pcnConversao,
      ACBrNFeDANFEClass, ACBrNFeDANFERave, ACBrUtil,pcnNFeW, pcnNFeRTXT, pcnAuxiliar, ACBrDFeUtil,
      XMLIntf, XMLDoc, BrvXml;

function ListarXmlDiretorio(NrSenha,NrCertificado,uf,diretorioOrigem,DiretorioMove:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):TClientDataSet;

function ListarNotasManifesto(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;XmlRetorno: AnsiString;CjEmpres:String;Operacao:Integer): OleVariant;

procedure BuscarNFePelaChave(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;pCpXML:TClientDataSet);

procedure IniciarVariaveisGlobal();

function gravarNFe(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;CcXml:TClientDataSet):Boolean;

function Mudar_e_GravarStatusManifesto(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;Chave,CjEmpres:String;CdEventOp:Integer):Boolean;

function DownloadNFe(NrSenha,NrCertificado,Chave,CjEmpres:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;CdEventOp :Integer):Boolean;

function StatusServico(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

function ConsultaNFeChave(NrSenha,NrCertificado,uf,Chave:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

function CancelarNFePelaChave(NrSenha,NrCertificado,uf,Chave,idLote,CNPJ,Protocolo,Justificativa:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

function ConsultarReciboLoteNFe(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;NrLote :Integer):AnsiString;

function ConsCadDestinatario(NrSenha,NrCertificado,CdUF,CjEmpres:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

function GerarPDFNFe(NrSenha,NrCertificado,CdUF:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;XMLNFE:String):AnsiString;

function ImprimirDanfe(NrSenha,NrCertificado,CdUF,XMLNFE:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

function EnviarEmail(const NrSenha,NrCertificado,CdUF:String;sSmtpHost,
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

function CartaDeCorrecao(NrSenha,NrCertificado,CdUF,Chave, idLote, CNPJ, nSeqEvento, Correcao:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

procedure Func_CDS_Duplica(Cds: TClientDataSet;CamposExcecoes:String = '');

function NfeDestinadas(NrSenha,NrCertificado,uf,CNPJ, IndNFe, IndEmi, ultNSU:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean): OleVariant;

procedure inicializetion(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean);

procedure CreateTableInfoNFe();

implementation

uses pcnNFe, ACBrNFeNotasFiscais, DateUtils, ACBrNFeUtil;

var CcxmlSet  :TClientDataSet;
    CpNfeDet  : TClientDataSet;
    CpNFeFat  : TClientDataSet;
    CpNFePro  : TClientDataSet;
    XML       : TBrvXML;
    ACBrNFe   : TACBrNFe;

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

procedure CreateTableInfoNFe();
begin
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
end;
procedure inicializetion(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean);
begin
          ACBrNFe.Configuracoes.WebServices.UF             := uf;
          ACBrNFe.Configuracoes.Certificados.NumeroSerie   := NrCertificado;
          ACBrNFe.Configuracoes.Certificados.Senha         := NrSenha;
          ACBrNFe.Configuracoes.WebServices.Visualizar     := BoVisualizar;
        //  ACBrNFe.Configuracoes.WebServices.Ambiente       := TpAmbiente;
end;


function NfeDestinadas(NrSenha,NrCertificado,uf,CNPJ, IndNFe, IndEmi, ultNSU:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean): OleVariant;
var
    ok: boolean;
    lStlAnexo :AnsiString;
begin
      IniciarVariaveisGlobal;
      inicializetion(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar);
      ACBrNFe.ConsultaNFeDest(CNPJ,
                               StrToIndicadorNFe(ok,indNFe),
                               StrToIndicadorEmissor(ok,IndEmi),
                               UltNSu);
      lStlAnexo := AcbrNFe.WebServices.ConsNFeDest.retConsNFeDest.XML;

      ListarNotasManifesto(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar,AcbrNFe.WebServices.ConsNFeDest.retConsNFeDest.XML,CNPJ,1);

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

function StatusServico(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;
begin
      try
          inicializetion(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar);
          ACBrNFe.WebServices.StatusServico.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally

      end;
end;

function ConsultaNFeChave(NrSenha,NrCertificado,uf,Chave:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;
begin
      try
          inicializetion(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar);
          ACBrNFe.WebServices.Consulta.NFeChave := Chave;
          ACBrNFe.WebServices.Consulta.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
      end;

end;

function CancelarNFePelaChave(NrSenha,NrCertificado,uf,Chave,idLote,CNPJ,Protocolo,Justificativa:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

var
    ACBrNFe   : TACBrNFe;
begin
      try
          IniciarVariaveisGlobal;
          inicializetion(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar);
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
          ACBrNFe.EnviarEventoNFe(StrToInt(idLote));
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;

end;

function ConsultarReciboLoteNFe(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;NrLote :Integer):AnsiString;
begin
      try
          IniciarVariaveisGlobal;
          inicializetion(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar);
          ACBrNFe.WebServices.Recibo.Recibo := IntToStr(NrLote);
          ACBrNFe.WebServices.StatusServico.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;

end;

function ConsCadDestinatario(NrSenha,NrCertificado,CdUF,CjEmpres:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;
var
    ACBrNFe   : TACBrNFe;
begin
      try
          IniciarVariaveisGlobal;
          inicializetion(NrSenha,NrCertificado,CdUF,TpAmbiente,BoVisualizar);
          ACBrNFe.WebServices.ConsultaCadastro.UF  := CdUF;
          if Length(CjEmpres) > 11 then
             ACBrNFe.WebServices.ConsultaCadastro.CNPJ := CjEmpres
          else
             ACBrNFe.WebServices.ConsultaCadastro.CPF := CjEmpres;
          ACBrNFe.WebServices.ConsultaCadastro.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;

end;

function GerarPDFNFe(NrSenha,NrCertificado,CdUF:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;XMLNFE:String):AnsiString;
begin
      try
           IniciarVariaveisGlobal;
           inicializetion(NrSenha,NrCertificado,CdUF,TpAmbiente,BoVisualizar);
           ACBrNFe.NotasFiscais.Items[0].XML := XMLNFE;
           ACBrNFe.NotasFiscais.ImprimirPDF;
      finally
      end;

end;

function ImprimirDanfe(NrSenha,NrCertificado,CdUF,XMLNFE:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;
begin
      try
          IniciarVariaveisGlobal;
          inicializetion(NrSenha,NrCertificado,CdUF,TpAmbiente,BoVisualizar);
          ACBrNFe.NotasFiscais.Clear;
          ACBrNFe.NotasFiscais.Items[0].XML := XMLNFE ;
          if ACBrNFe.NotasFiscais.Items[0].NFe.Ide.tpEmis = teDPEC then
           begin
             ACBrNFe.WebServices.ConsultaDPEC.NFeChave := ACBrNFe.NotasFiscais.Items[0].NFe.infNFe.ID;
             ACBrNFe.WebServices.ConsultaDPEC.Executar;
             ACBrNFe.DANFE.ProtocoloNFe := ACBrNFe.WebServices.ConsultaDPEC.nRegDPEC +' '+ DateTimeToStr(ACBrNFe.WebServices.ConsultaDPEC.dhRegDPEC);
           end;
          ACBrNFe.NotasFiscais.Imprimir;

      finally

      end;

end;

function EnviarEmail(const NrSenha,NrCertificado,CdUF:String;sSmtpHost,
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
function CartaDeCorrecao(NrSenha,NrCertificado,CdUF,Chave, idLote, CNPJ, nSeqEvento, Correcao:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;
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

function ListarNotasManifesto(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;XmlRetorno: AnsiString;CjEmpres:String;Operacao:Integer): OleVariant;
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
          BuscarNFePelaChave(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar,pCpXML);
          Result := CpNfeDet.Data;
      finally
         FreeAndNil(pCpXML);
      end;

end;

procedure BuscarNFePelaChave(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;pCpXML:TClientDataSet);
var
    lCpXML    : TClientDataSet;
    lNrIndice,i : Integer;
begin
      try
          lCpXML    := TClientDataSet.Create(nil);
          CreateTableInfoNFe;
          inicializetion(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar);
          if pCpXML.RecordCount > 0 then
          begin
                while not pCpXML.Eof do
                begin
                      ACBrNFe.DownloadNFe.Download.Chaves.Clear;
                      ACBrNFe.DownloadNFe.Download.Chaves.Add.chNFe := pCpXML.FieldByName('NrChaDoc').AsString;
                      ACBrNFe.DownloadNFe.Download.CNPJ             := pCpXML.FieldByName('CjEmpres').AsString;
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

function gravarNFe(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;CcXml:TClientDataSet):Boolean;begin
      CcXml.Post;
      CcXml.ApplyUpdates(1);
end;

function Mudar_e_GravarStatusManifesto(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;Chave,CjEmpres:String;CdEventOp:Integer):Boolean;var
    ACBrNFe     : TACBrNFe;
    DescEventOp : TpcnTpEvento ;
begin
      try
          XML.BrXMLOriginal.Text := '';
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

function DownloadNFe(NrSenha,NrCertificado,Chave,CjEmpres:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;CdEventOp :Integer):Boolean;
begin


end;

function ListarXmlDiretorio(NrSenha,NrCertificado,uf,diretorioOrigem,DiretorioMove:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):TClientDataSet;
var i : Integer;
    ArquivoXML: TStringList;
    SR: TSearchRec;
    destino,origem:String;
begin
      IniciarVariaveisGlobal;
//      inicializetion(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar);

      if diretorioOrigem = '' then
      begin
            ACBrNFe.NotasFiscais.Clear;
            ACBrNFe.NotasFiscais.LoadFromFile(diretorioOrigem);
            ACBrNFe.NotasFiscais.GerarNFe;
            ACBrNFe.Enviar(1,True);
            if Pos('REJEICAO', UpperCase(ACBrNFe.WebServices.Retorno.RetWS)) > 0 then
            begin
            end else
            begin
                  try

                    XML.BrXMLOriginal.Text := ACBrNFe.WebServices.Retorno.RetWS;
                    XML.ProcessarXml;
                    CcxmlSet.Append;
                    CcxmlSet.FieldByName('SnMarcad').Value := 'N';
                    for i := 1 to CcxmlSet.FieldCount-1 do
                    begin
                         CcxmlSet.FieldByName(CcxmlSet.Fields[i].FieldName).Value := CpNfeDet.FieldByName(CcxmlSet.Fields[i].FieldName).Value;
                    end;
                    CcxmlSet.Post;
                    finally

                    end;
            end;
        end else
        begin
              try

              i := FindFirst(diretorioOrigem+'\*.xml', faAnyFile, SR);
              if i = 0 then
              begin
                CreateTableInfoNFe;
              while I = 0 do
              begin
                  ArquivoXML := TStringList.Create;
                  ArquivoXML.LoadFromFile(diretorioOrigem+'\'+SR.Name);
                  XML.BrXMLOriginal.Text := ArquivoXML.Text;
                  XML.ProcessarXml;

                  CcxmlSet.insert;
                  CcxmlSet.FieldByName('SnMarcad').Value := 'N';
                  for i := 1 to CcxmlSet.FieldCount-1 do
                  begin
                       CcxmlSet.FieldByName(CcxmlSet.Fields[i].FieldName).Value := CpNfeDet.FieldByName(CcxmlSet.Fields[i].FieldName).Value;
                  end;
                  CcxmlSet.Post;
                  Windows.MoveFile(PChar(diretorioOrigem+'\'+SR.Name),PChar(DiretorioMove+'\'+SR.Name));
                  Windows.DeleteFile(PChar(diretorioOrigem+'\'+SR.Name));
                  i := FindNext(SR);
              end;
                  result :=  CcxmlSet;
              end;

              finally

              end;
        end;
end;

end.
