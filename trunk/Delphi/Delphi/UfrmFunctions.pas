unit UfrmFunctions;

interface
uses  SysUtils, Forms, Classes, Dialogs, Windows, ComCtrls, DBClient, DB,
      Variants, BrvExcel, BrvClientDataSet, Graphics, CheckLst,ACBrNFe, pcnConversao,
      ACBrNFeDANFEClass, ACBrNFeDANFERave, ACBrUtil,pcnNFeW, pcnNFeRTXT, pcnAuxiliar, ACBrDFeUtil,
      XMLIntf, XMLDoc, BrvXml, pnfsConversao,ACBrNFSe, ACBrNFSeDANFSeClass, ACBrNFSeDANFSeQRClass, pnfsNFSe,
      ACBrCTeDACTEClass,ACBrCTe,Controls,SHDocVw,IniFiles, ShellAPI;

//================================= Util======================================================

procedure IniciarVariaveisGlobal();

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

procedure Func_CDS_Duplica(Cds: TClientDataSet;CamposExcecoes:String = '');


procedure inicializetion(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean);


//================================= NFe=======================================================
function ListarXmlDiretorio(diretorioOrigem,DiretorioMove:String):TClientDataSet;

function ACBrNFe_ListarNotasManifesto(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;XmlRetorno: AnsiString;CjEmpres:String;Operacao:Integer): TClientDataSet;

procedure ACBrNFe_BuscarNFePelaChave(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;pCpXML:TClientDataSet);

function ACBrNFe_gravarNFe(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;CcXml:TClientDataSet):Boolean;

function ACBrNFe_Mudar_e_GravarStatusManifesto(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;Chave,CjEmpres:String;CdEventOp:Integer):Boolean;

function ACBrNFe_DownloadNFe(NrSenha,NrCertificado,Chave,CjEmpres:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;CdEventOp :Integer):Boolean;

function ACBrNFe_StatusServico(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

function ACBrNFe_ConsultaNFeChave(NrSenha,NrCertificado,uf,Chave:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

function ACBrNFe_CancelarNFePelaChave(NrSenha,NrCertificado,uf,Chave,idLote,CNPJ,Protocolo,Justificativa:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

function ACBrNFe_ConsultarReciboLoteNFe(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;NrLote :Integer):AnsiString;

function ACBrNFe_ConsCadDestinatario(NrSenha,NrCertificado,CdUF,CjEmpres:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

function ACBrNFe_GerarPDFNFe(NrSenha,NrCertificado,CdUF:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;XMLNFE:String):AnsiString;

function ACBrNFe_ImprimirDanfe(NrSenha,NrCertificado,CdUF,XMLNFE:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

function ACBrNFe_CartaDeCorrecao(NrSenha,NrCertificado,CdUF,Chave, idLote, CNPJ, nSeqEvento, Correcao:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;

function ACBrNFe_NfeDestinadas(NrSenha,NrCertificado,uf,CNPJ, IndNFe, IndEmi, ultNSU:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean): OleVariant;

procedure CreateTableInfoNFe();

procedure ACBrNFe_LerConfiguracao();

//======================== NFSe=====================================================================

procedure ACBrNFSe_ConfiguraComponente(edtCaminho,edtSenha,edtNumSerie,edtPathLogs,edtSchemas,edtLogoMarca,edtPrestLogo,edtPrefeitura,
            edtCodCidade,edtSenhaWeb,edtUserWeb,edtProxyHost,edtProxyPorta,edtProxyUser,edtProxySenha
            :String;ckSalvar,ckVisualizar:Boolean;rgTipoAmb:Integer);

 function ACBrNFSe_GerarRPS():AnsiString;

 function ACBrNFSe_ImprimirNFSe(Xml : AnsiString):AnsiString;

 function ACBrNFSe_ConsultarSitLote(Protocolo,EmitCNPJ,EmitIM : String):AnsiString;

 function ACBrNFSe_ConsultarNFSePeriodo(DataInicial,DataFinal,EmitCNPJ,EmitIM:String):AnsiString;

 function ACBrNFSe_CancNFSe(XML,Codigo:AnsiString):AnsiString;

 function ACBrNFSe_ConsultarNFSeRPS(XML:AnsiString):AnsiString;

 //======================================CTe========================================================

 function ACBrCTe_ValidarXML(FileName:String):Boolean;

 function ACBrCTe_ImportarXML(FileName:String):AnsiString;

 function ACBrCTe_Imprimir(Xmls:AnsiString):AnsiString;

 function ACBrCTe_GerarPDF(Xmls:AnsiString):AnsiString;

 function ACBrCTe_ConsultarRecibo(NrLote:String):AnsiString;

 function ACBrCTe_CancCTeXml(XmlS,Justificativa:AnsiString):AnsiString;

 function ACBrCTe_ConsultarChave(vChave : String):AnsiString;

 function ACBrCTe_Consultar(XMLS:AnsiString):AnsiString;

 function ACBrCTe_Status(Sender: TObject):AnsiString;

 //====================================== Boleto ===================================================


implementation

uses pcnNFe, ACBrNFeNotasFiscais, DateUtils, ACBrNFeUtil, FileCtrl,ACBrCTeConhecimentos,ACBrCTeUtil;

var CcxmlSet    :TClientDataSet;
    CpNfeDet    : TClientDataSet;
    CpNFeFat    : TClientDataSet;
    CpNFePro    : TClientDataSet;
    XML         : TBrvXML;
    ACBrNFe     : TACBrNFe;
    ACBrNFSe    : TACBrNFSe;
    ACBrCTe     : TACBrCTe;
    edtEmitCNPJ : String;
    edtEmitIE   : String;
    edtEmitIM   : String;
    edtEmitCEP  : String;
    edtEmitUF   : String;


procedure ACBrNFSe_LerConfiguracao;
var
 IniFile    : String;
 Ini        : TIniFile;
 StreamMemo : TMemoryStream;
 PathMensal : String;
 Ok         : Boolean;
begin
     IniFile := ChangeFileExt( Application.ExeName, '.ini');

     Ini := TIniFile.Create( IniFile );
     try
       ACBrNFSe.Configuracoes.Certificados.NumeroSerie  := Ini.ReadString( 'Certificado','NumSerie' ,'');
       ACBrNFSe.Configuracoes.Certificados.Senha        := Ini.ReadString( 'Certificado','Senha'   ,'');
       edtEmitCNPJ                                      := Ini.ReadString( 'Emitente', 'CNPJ'       , '');
       edtEmitIM                                        := Ini.ReadString( 'Emitente', 'IM'         , '');
       edtEmitUF                                        := Ini.ReadString( 'Emitente', 'UF'         , '');
       ACBrNFSe.Configuracoes.Arquivos.AdicionarLiteral :=True;
       ACBrNFSe.Configuracoes.Arquivos.EmissaoPathNFSe  :=True;
       ACBrNFSe.Configuracoes.Arquivos.PastaMensal      :=True;
       ACBrNFSe.Configuracoes.Arquivos.PathCan:=Ini.ReadString( 'Geral','PathSalvar'  ,'');
       ACBrNFSe.Configuracoes.Arquivos.PathNFSe:=Ini.ReadString( 'Geral','PathSalvar'  ,'');
       ACBrNFSe.Configuracoes.Arquivos.Salvar:=True;

       PathMensal:=ACBrNFSe.Configuracoes.Arquivos.GetPathNFSe(0);

       ACBrNFSe.Configuracoes.Geral.PathSchemas := Ini.ReadString( 'Proxy','Host'   ,'');
       ACBrNFSe.Configuracoes.Geral.Salvar      := Ini.ReadBool(   'Geral','Salvar'      ,True);
       ACBrNFSe.Configuracoes.Geral.PathSalvar  := Ini.ReadString( 'Geral','PathSalvar'  ,'');

       ACBrNFSe.Configuracoes.WebServices.CodigoMunicipio := StrToIntDef(Ini.ReadString( 'Geral','PathSalvar'  ,''), 0);
       ACBrNFSe.Configuracoes.WebServices.Ambiente        := StrToTpAmb(Ok,IntToStr(Ini.ReadInteger('WebService','Ambiente'  ,0)+1));
       ACBrNFSe.Configuracoes.WebServices.Visualizar      := Ini.ReadBool(    'WebService','Visualizar',False);
       ACBrNFSe.Configuracoes.WebServices.SenhaWeb        := Ini.ReadString( 'Proxy','SenhaWeb'   ,'');
       ACBrNFSe.Configuracoes.WebServices.UserWeb         := Ini.ReadString( 'Proxy','UserWeb'   ,'');
       ACBrNFSe.Configuracoes.WebServices.Ambiente   := StrToTpAmb(Ok,IntToStr(Ini.ReadInteger('WebService','Ambiente'  ,0)+1));
       ACBrNFSe.Configuracoes.WebServices.Visualizar := Ini.ReadBool(  'WebService','Visualizar',False);
       ACBrNFSe.Configuracoes.WebServices.ProxyHost := Ini.ReadString( 'Proxy','Host'   ,'');
       ACBrNFSe.Configuracoes.WebServices.ProxyPort := Ini.ReadString( 'Proxy','Porta'  ,'');
       ACBrNFSe.Configuracoes.WebServices.ProxyUser := Ini.ReadString( 'Proxy','User'   ,'');
       ACBrNFSe.Configuracoes.WebServices.ProxyPass := Ini.ReadString( 'Proxy','Pass'   ,'');

       ACBrNFSe.Configuracoes.WebServices.SetConfigMunicipio(ACBrNFSe.Configuracoes.Geral.PathSchemas);

       if ACBrNFSe.DANFSe <> nil then
        begin
         ACBrNFSe.DANFSe.Logo       := Ini.ReadString( 'Geral','LogoMarcaNFSe'   ,'');
         ACBrNFSe.DANFSe.PrestLogo  := Ini.ReadString( 'Geral','PrestLogo'   ,'');
         ACBrNFSe.DANFSe.Prefeitura := Ini.ReadString( 'Geral','Prefeitura'   ,'');
        end;


      StreamMemo := TMemoryStream.Create;
      StreamMemo.Free;
     finally
      Ini.Free;
     end;
end;

procedure ACBrNFe_LerConfiguracao;
Var IniFile  : String ;
    Ini     : TIniFile ;
    Ok : Boolean;
begin
  IniFile := ChangeFileExt( Application.ExeName, '.ini') ;

  Ini := TIniFile.Create( IniFile );
  try
        ACBrNFe.Configuracoes.Certificados.NumeroSerie := Ini.ReadString( 'Certificado','NumSerie' ,'');
        ACBrNFe.Configuracoes.Certificados.Senha        := Ini.ReadString( 'Certificado','Senha'   ,'') ;
        ACBrNFe.Configuracoes.Geral.FormaEmissao        := StrToTpEmis(OK,IntToStr(Ini.ReadInteger('Geral','FormaEmissao',0)+1));
        ACBrNFe.Configuracoes.Geral.Salvar              := Ini.ReadBool(   'Geral','Salvar'      ,True);
        ACBrNFe.Configuracoes.Geral.PathSalvar          := Ini.ReadString( 'Geral','PathSalvar'  ,'');
        ACBrNFe.Configuracoes.WebServices.UF            := Ini.ReadString( 'WebService','UF','SP');
        ACBrNFe.Configuracoes.WebServices.Ambiente      := StrToTpAmb(Ok,IntToStr(Ini.ReadInteger('WebService','Ambiente'  ,0)+1));
        ACBrNFe.Configuracoes.WebServices.Visualizar    := Ini.ReadBool(    'WebService','Visualizar',False) ;

        ACBrNFe.Configuracoes.WebServices.ProxyHost := Ini.ReadString( 'Proxy','Host'   ,'') ;
        ACBrNFe.Configuracoes.WebServices.ProxyPort := Ini.ReadString( 'Proxy','Porta'  ,'') ;
        ACBrNFe.Configuracoes.WebServices.ProxyUser := Ini.ReadString( 'Proxy','User'   ,'') ;
        ACBrNFe.Configuracoes.WebServices.ProxyPass := Ini.ReadString( 'Proxy','Pass'   ,'') ;

        if ACBrNFe.DANFE <> nil then
         begin
           ACBrNFe.DANFE.TipoDANFE  := StrToTpImp(OK,IntToStr(Ini.ReadInteger( 'Geral','DANFE'       ,0)+1));
           ACBrNFe.DANFE.Logo       := Ini.ReadString( 'Geral','LogoMarca'   ,'') ;
         end;

        edtEmitCNPJ      := Ini.ReadString( 'Emitente','CNPJ'       ,'') ;
        edtEmitIE        := Ini.ReadString( 'Emitente','IE'         ,'') ;
        edtEmitCEP       := Ini.ReadString( 'Emitente','CEP'        ,'') ;
        edtEmitUF        := Ini.ReadString( 'Emitente','UF'         ,'') ;
  finally
     Ini.Free ;
  end;

end;

procedure ACBrCTe_LerConfiguracao;
var
 IniFile    : String;
 Ini        : TIniFile;
 Ok         : Boolean;
 StreamMemo : TMemoryStream;
begin
      IniFile := ChangeFileExt( Application.ExeName, '.ini');
      IniciarVariaveisGlobal;
      Ini := TIniFile.Create( IniFile );
      try
          IniciarVariaveisGlobal;
          ACBrCTe.Configuracoes.Certificados.NumeroSerie  := Ini.ReadString( 'Certificado','NumSerie' ,'');
          ACBrCTe.Configuracoes.Certificados.Senha        := Ini.ReadString( 'Certificado','Senha'   ,'');
          ACBrCTe.Configuracoes.Geral.FormaEmissao := StrToTpEmis(OK,IntToStr(Ini.ReadInteger('Geral','FormaEmissao',0)+1));
          ACBrCTe.Configuracoes.Geral.Salvar       := Ini.ReadBool(   'Geral','Salvar'      ,True);
          ACBrCTe.Configuracoes.Geral.PathSalvar   := Ini.ReadString( 'Geral','PathSalvar'  ,'');
          ACBrCTe.Configuracoes.WebServices.UF         := Ini.ReadString('WebService','UF','SP');
          ACBrCTe.Configuracoes.WebServices.Ambiente   := StrToTpAmb(Ok,IntToStr(Ini.ReadInteger('WebService','Ambiente'  ,0)+1));
          ACBrCTe.Configuracoes.WebServices.Visualizar := Ini.ReadBool(    'WebService','Visualizar',False);
          ACBrCTe.Configuracoes.WebServices.ProxyHost := Ini.ReadString( 'Proxy','Host'   ,'');
          ACBrCTe.Configuracoes.WebServices.ProxyPort := Ini.ReadString( 'Proxy','Porta'  ,'');
          ACBrCTe.Configuracoes.WebServices.ProxyUser := Ini.ReadString( 'Proxy','User'   ,'');
          ACBrCTe.Configuracoes.WebServices.ProxyPass := Ini.ReadString( 'Proxy','Pass'   ,'');
          if ACBrCTe.DACTe <> nil then
          begin
                ACBrCTe.DACTe.TipoDACTe := StrToTpImp(OK,IntToStr(Ini.ReadInteger( 'Geral','DACTE'       ,0)+1));
                ACBrCTe.DACTe.Logo      := Ini.ReadString( 'Geral','LogoMarca'   ,'');
                ACBrCTe.DACTe.PathPDF   := Ini.ReadString( 'Geral','PathSalvar'  ,'');
          end;

          edtEmitCNPJ       := Ini.ReadString( 'Emitente','CNPJ'       ,'');
          edtEmitIE         := Ini.ReadString( 'Emitente','IE'         ,'');
          edtEmitUF         := Ini.ReadString( 'Emitente','UF'         ,'');
          StreamMemo.Free;
 finally
  Ini.Free;
 end;
end;

function ACBrCTe_ValidarXML(FileName:String):Boolean;
begin
      ACBrCTe.Conhecimentos.Clear;
      ACBrCTe.Conhecimentos.LoadFromFile(FileName);
      ACBrCTe.Conhecimentos.Valida;
      result :=  true;
end;

function ACBrCTe_ImportarXML(FileName:String):AnsiString;

begin

   ACBrCTe.Conhecimentos.Clear;
   ACBrCTe.Conhecimentos.LoadFromFile(FileName);

   result := ACBrCTe.Conhecimentos.Items[0].XML;
end;

function ACBrCTe_Imprimir(Xmls:AnsiString):AnsiString;
begin
   ACBrCTe.Conhecimentos.Clear;
   ACBrCTe.Conhecimentos.LoadFromFile(Xmls);
//   if ACBrCTe.Conhecimentos.Items[0].CTe.Ide.tpEmis = teDPEC then
//    begin
//     ACBrCTe.WebServices.ConsultaDPEC.CTeChave := ACBrCTe.Conhecimentos.Items[0].CTe.infCTe.ID;
//     ACBrCTe.WebServices.ConsultaDPEC.Executar;
//     ACBrCTe.DACTe.ProtocoloCTe := ACBrCTe.WebServices.ConsultaDPEC.nRegDPEC +
//       ' '+ DateTimeToStr(ACBrCTe1.WebServices.ConsultaDPEC.retDPEC.dhRegDPEC);
//    end;
   ACBrCTe.Conhecimentos.Imprimir;
end;

function ACBrCTe_GerarPDF(Xmls:AnsiString):AnsiString;
begin
      ACBrCTe.Conhecimentos.Clear;
      ACBrCTe.Conhecimentos.LoadFromFile(Xmls);
      ACBrCTe.Conhecimentos.ImprimirPDF;
end;

function ACBrCTe_ConsultarRecibo(NrLote:String):AnsiString;
begin
      ACBrCTe.WebServices.Recibo.Recibo := NrLote;
      ACBrCTe.WebServices.Recibo.Executar;
      Result := UTF8Encode(ACBrCTe.WebServices.Recibo.RetWS);
end;

function ACBrCTe_CancCTeXml(XmlS,Justificativa:AnsiString):AnsiString;
begin
      ACBrCTe.Conhecimentos.Clear;
      ACBrCTe.Conhecimentos.LoadFromFile(XmlS);
      ACBrCTe.Cancelamento(Justificativa);
      Result := UTF8Encode(ACBrCTe.WebServices.Cancelamento.RetWS);
end;

function ACBrCTe_ConsultarChave(vChave : String):AnsiString;
begin
      ACBrCTe.WebServices.Consulta.CTeChave := vChave;
      ACBrCTe.WebServices.Consulta.Executar;
      Result := ACBrCTe.WebServices.Consulta.RetWS;
end;

function ACBrCTe_Consultar(XMLS:AnsiString):AnsiString;
begin
     ACBrCTe.Conhecimentos.Clear;
     ACBrCTe.Conhecimentos.LoadFromFile(XMLS);
     ACBrCTe.Consultar;
     Result := ACBrCTe.WebServices.Consulta.RetWS;
end;

function ACBrCTe_Status(Sender: TObject):AnsiString;
begin
      ACBrCTe.WebServices.StatusServico.Executar;
      Result := UTF8Encode(ACBrCTe.WebServices.StatusServico.RetWS);
end;

function ACBrNFSe_ConsultarNFSeRPS(XML:AnsiString):AnsiString;
begin
       ACBrNFSe.NotasFiscais.Clear;
       ACBrNFSe.NotasFiscais.LoadFromFile(XML);

       ACBrNFSe.ConsultarNFSeporRps(ACBrNFSe.NotasFiscais.Items[0].NFSe.IdentificacaoRps.Numero,
                                    ACBrNFSe.NotasFiscais.Items[0].NFSe.IdentificacaoRps.Serie,
                                    TipoRPSToStr(ACBrNFSe.NotasFiscais.Items[0].NFSe.IdentificacaoRps.Tipo),
                                    ACBrNFSe.NotasFiscais.Items[0].NFSe.Prestador.Cnpj,
                                    ACBrNFSe.NotasFiscais.Items[0].NFSe.Prestador.InscricaoMunicipal);
       Result := ACBrNFSe.WebServices.ConsNfseRps.RetWS
end;

function ACBrNFSe_CancNFSe(XML,Codigo:AnsiString):AnsiString;
begin
      ACBrNFSe.NotasFiscais.Clear;
      ACBrNFSe.NotasFiscais.LoadFromFile(XML);

   // Codigo de Cancelamento
   // 1 - Erro de emissão
   // 2 - Serviço não concluido
   // 3 - RPS Cancelado na Emissão
//   ACBrNFSe1.WebServices.CancelaNFSe(Codigo, '1', '03310700000170', '0306223', '0');
     ACBrNFSe.CancelarNFSe(Codigo);
     Result := ACBrNFSe.WebServices.CancNfse.CodigoCancelamento;
end;

function ACBrNFSe_ConsultarLote(Lote,Protocolo:String):AnsiString;
begin
      ACBrNFSe.ConsultarLoteRps(Lote, Protocolo);
      Result := UTF8Encode(ACBrNFSe.WebServices.ConsLote.RetWS);
end;

function ACBrNFSe_ConsultarNFSePeriodo(DataInicial,DataFinal,EmitCNPJ,EmitIM:String):AnsiString;
begin
      ACBrNFSe.ConsultarNFSe(EmitCNPJ, EmitIM, StrToDate(DataInicial), StrToDate(DataFinal));
      Result :=  UTF8Encode(ACBrNFSe.WebServices.ConsNfse.RetWS);

end;

function ACBrNFSe_ConsultarSitLote(Protocolo,EmitCNPJ,EmitIM : String):AnsiString;
begin
      ACBrNFSe.ConsultarSituacao(EmitCNPJ,EmitIM, Protocolo);
      Result := UTF8Encode(ACBrNFSe.WebServices.ConsSitLote.RetWS);
end;

function ACBrNFSe_GerarRPS():AnsiString;
var
 vAux : String;
begin
      if not(InputQuery('Gerar RPS', 'Numero do RPS', vAux))
      then exit;
      ACBrNFSe.NotasFiscais.Clear;
      //GerarNFSe(vAux);
      ACBrNFSe.NotasFiscais.Items[0].SaveToFile;
      Result := ACBrNFSe.NotasFiscais.Items[0].NomeArq;
end;

function ACBrNFSe_ImprimirNFSe(Xml : AnsiString):AnsiString;
begin
      ACBrNFSe.NotasFiscais.Clear;
      ACBrNFSe.NotasFiscais.LoadFromFile(Xml);
      ACBrNFSe.NotasFiscais.Imprimir;
      Result := ACBrNFSe.NotasFiscais.Items[0].NFSe.CodigoVerificacao
end;

procedure ACBrNFSe_ConfiguraComponente(edtCaminho,edtSenha,edtNumSerie,edtPathLogs,edtSchemas,edtLogoMarca,edtPrestLogo,edtPrefeitura,
            edtCodCidade,edtSenhaWeb,edtUserWeb,edtProxyHost,edtProxyPorta,edtProxyUser,edtProxySenha
            :String;ckSalvar,ckVisualizar:Boolean;rgTipoAmb:Integer);
var
 PathMensal: String;
 Ok : Boolean;
begin
     {$IFDEF ACBrNFSeOpenSSL}
       ACBrNFSe.Configuracoes.Certificados.Certificado := edtCaminho;
       ACBrNFSe.Configuracoes.Certificados.Senha       := edtSenha;
     {$ELSE}
       ACBrNFSe.Configuracoes.Certificados.NumeroSerie := edtNumSerie;
     {$ENDIF}

     ACBrNFSe.Configuracoes.Arquivos.AdicionarLiteral:=True;
     ACBrNFSe.Configuracoes.Arquivos.EmissaoPathNFSe:=True;
     ACBrNFSe.Configuracoes.Arquivos.PastaMensal:=True;
     ACBrNFSe.Configuracoes.Arquivos.PathCan:=edtPathLogs;
     ACBrNFSe.Configuracoes.Arquivos.PathNFSe:=edtPathLogs;
     ACBrNFSe.Configuracoes.Arquivos.Salvar:=True;

     PathMensal:=ACBrNFSe.Configuracoes.Arquivos.GetPathNFSe(0);

     ACBrNFSe.Configuracoes.Geral.PathSchemas := edtSchemas;
     ACBrNFSe.Configuracoes.Geral.Salvar      := ckSalvar;
     ACBrNFSe.Configuracoes.Geral.PathSalvar  := edtPathLogs;

     ACBrNFSe.Configuracoes.WebServices.CodigoMunicipio := StrToIntDef(edtCodCidade, 0);
     ACBrNFSe.Configuracoes.WebServices.Ambiente        := StrToTpAmb(Ok, IntToStr(rgTipoAmb+1));
     ACBrNFSe.Configuracoes.WebServices.Visualizar      := ckVisualizar;
     ACBrNFSe.Configuracoes.WebServices.SenhaWeb        := edtSenhaWeb;
     ACBrNFSe.Configuracoes.WebServices.UserWeb         := edtUserWeb;

     ACBrNFSe.Configuracoes.WebServices.ProxyHost := edtProxyHost;
     ACBrNFSe.Configuracoes.WebServices.ProxyPort := edtProxyPorta;
     ACBrNFSe.Configuracoes.WebServices.ProxyUser := edtProxyUser;
     ACBrNFSe.Configuracoes.WebServices.ProxyPass := edtProxySenha;

     ACBrNFSe.Configuracoes.WebServices.SetConfigMunicipio(ACBrNFSe.Configuracoes.Geral.PathSchemas);

     if ACBrNFSe.DANFSe <> nil then
      begin
       ACBrNFSe.DANFSe.Logo       := edtLogoMarca;
       ACBrNFSe.DANFSe.PrestLogo  := edtPrestLogo;
       ACBrNFSe.DANFSe.Prefeitura := edtPrefeitura;
      end;

 //lblSchemas.Caption := ACBrNFSe.Configuracoes.WebServices.xProvedor;
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
          ACBrNFSe  := TACBrNFSe.Create(nil);
          ACBrCTe   := TACBrCTe.Create(nil);
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
      CcxmlSet.FieldDefs.Add('Xml',                ftString, 4000 );
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


function ACBrNFe_NfeDestinadas(NrSenha,NrCertificado,uf,CNPJ, IndNFe, IndEmi, ultNSU:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean): OleVariant;
var
    ok: boolean;
    lStlAnexo :AnsiString;
begin
      IniciarVariaveisGlobal;
      ACBrNFe_LerConfiguracao();
      ACBrNFe.ConsultaNFeDest(CNPJ,
                               StrToIndicadorNFe(ok,indNFe),
                               StrToIndicadorEmissor(ok,IndEmi),
                               UltNSu);
      lStlAnexo := AcbrNFe.WebServices.ConsNFeDest.retConsNFeDest.XML;

      ACBrNFe_ListarNotasManifesto(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar,AcbrNFe.WebServices.ConsNFeDest.retConsNFeDest.XML,CNPJ,1);

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

function ACBrNFe_StatusServico(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;
begin
      try
          inicializetion(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar);
          ACBrNFe.WebServices.StatusServico.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally

      end;
end;

function ACBrNFe_ConsultaNFeChave(NrSenha,NrCertificado,uf,Chave:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;
begin
      try
          inicializetion(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar);
          ACBrNFe.WebServices.Consulta.NFeChave := Chave;
          ACBrNFe.WebServices.Consulta.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
      end;

end;

function ACBrNFe_CancelarNFePelaChave(NrSenha,NrCertificado,uf,Chave,idLote,CNPJ,Protocolo,Justificativa:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;
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

function ACBrNFe_ConsultarReciboLoteNFe(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;NrLote :Integer):AnsiString;
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

function ACBrNFe_ConsCadDestinatario(NrSenha,NrCertificado,CdUF,CjEmpres:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;
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

function ACBrNFe_GerarPDFNFe(NrSenha,NrCertificado,CdUF:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;XMLNFE:String):AnsiString;
begin
      try
           IniciarVariaveisGlobal;
           inicializetion(NrSenha,NrCertificado,CdUF,TpAmbiente,BoVisualizar);
           ACBrNFe.NotasFiscais.Items[0].XML := XMLNFE;
           ACBrNFe.NotasFiscais.ImprimirPDF;
      finally
      end;

end;

function ACBrNFe_ImprimirDanfe(NrSenha,NrCertificado,CdUF,XMLNFE:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;
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
begin
      try
          ACBrNFe.WebServices.StatusServico.Executar;
          Result := UTF8Encode(ACBrNFe.WebServices.StatusServico.RetWS);
      finally
          FreeAndNil(ACBrNFe);
      end;

end;
function ACBrNFe_CartaDeCorrecao(NrSenha,NrCertificado,CdUF,Chave, idLote, CNPJ, nSeqEvento, Correcao:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean):AnsiString;
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

function ACBrNFe_ListarNotasManifesto(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;XmlRetorno: AnsiString;CjEmpres:String;Operacao:Integer): TClientDataSet;
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
          ACBrNFe_BuscarNFePelaChave(NrSenha,NrCertificado,uf,TpAmbiente,BoVisualizar,pCpXML);
          Result := CpNfeDet;
      finally
         FreeAndNil(pCpXML);
      end;

end;

procedure ACBrNFe_BuscarNFePelaChave(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;pCpXML:TClientDataSet);
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

function ACBrNFe_gravarNFe(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;CcXml:TClientDataSet):Boolean;begin
      CcXml.Post;
      CcXml.ApplyUpdates(1);
end;

function ACBrNFe_Mudar_e_GravarStatusManifesto(NrSenha,NrCertificado,uf:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;Chave,CjEmpres:String;CdEventOp:Integer):Boolean;var
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

function ACBrNFe_DownloadNFe(NrSenha,NrCertificado,Chave,CjEmpres:String;TpAmbiente:TpcnTipoEmissao;BoVisualizar:boolean;CdEventOp :Integer):Boolean;
begin


end;

function ListarXmlDiretorio(diretorioOrigem,DiretorioMove:String):TClientDataSet;
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
