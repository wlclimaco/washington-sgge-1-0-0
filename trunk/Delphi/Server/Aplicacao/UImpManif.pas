unit UImpManif;

interface

uses
    Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,ComCtrls,
    Dialogs,  FMTBcd,ACBrNFe, pcnConversao, ACBrNFeDANFEClass, ACBrNFeDANFERave, ACBrUtil,
    pcnNFeW, pcnNFeRTXT, pcnAuxiliar, ACBrDFeUtil,XMLIntf, XMLDoc, ACBrNFeDANFERaveCB, IdIMAP4,
    IdSMTPBase, IdSMTP,  DB, SqlExpr,Provider, DBClient,
    IdComponent, IdTCPConnection,StdCtrls,IdTCPClient,IdExplicitTLSClientServerBase,
    IdMessageClient, IdPOP3, IdBaseComponent,IdMessage,SHDocVw,IniFiles,
    BrvString, BrvClientDataSet, UDmTarefa, BrvDataSet, BrvProvider, BrvSql;

procedure ConnectaReceita(CjEmpres:String;NrCertif:String;NrSenCer:String;CdEstado:
                                              String;ACBrNFe:TACBrNFe);
procedure GerarLog(menssage:String;NrChaDoc:String;CjEmpres:String;CdEventOp:String;msgLivre:String);
function  GerarStatusManifesto(NrChaDoc:String;CjEmpres:String;CdEmpres:String;CdEventOp:Integer;
                                          ACBrNFe:TACBrNFe;CpXML: TBrvClientDataSet): boolean;
function  DownloadNFe(NrChaDoc:String;CjEmpres:String;ACBrNFe:TACBrNFe;
                                          pCpXML: TBrvClientDataSet):AnsiString ;
function  GravarNFe(TxXml:AnsiString;CjEmpres:String;CdEmpres:String;pCpXML: TBrvClientDataSet):boolean;
function  GravarManifestoNFe(NrChaDoc:String;CdEventOp:Integer;pCpXML: TBrvClientDataSet;
                                          CdEmpres:String):boolean;
function  BuscarNFeManifesto(CjEmpres:String;CdEmpres:String;ACBrNFe:TACBrNFe;
                                      pCpXML: TBrvClientDataSet;CPEmpresa:TBrvClientDataSet):boolean;
function  CarregaConsulta(TxXml: String):boolean;
function  BuscarNFeManifestoWS(opStatus:integer;NrChaDoc:String;CjEmpres:String;NrCertif:String;
                                          NrSenCer:String;CdEstado:String;CdEmpres:String):boolean;
implementation

uses UDmDicion,pcnNFe, ACBrNFeNotasFiscais, DateUtils, ACBrNFeUtil,UImpEmail,UFrmLogos;


function  BuscarNFeManifestoWS(opStatus:integer;NrChaDoc:String;CjEmpres:String;NrCertif:String;
                                          NrSenCer:String;CdEstado:String;CdEmpres:String):boolean;
var
    pCpXML : TBrvClientDataSet ;
    ACBrNFe: TACBrNFe;
begin

      try
          pCpXML  := TBrvClientDataSet.Create(nil);
          ACBrNFe := TACBrNFe.Create(nil);
          ConnectaReceita(CjEmpres,NrCertif,NrSenCer,CdEstado,ACBrNFe);
          GerarStatusManifesto(NrChaDoc,CjEmpres,CdEmpres,opStatus,ACBrNFe,pCpXML);
      finally
          FreeAndNil(pCpXML);
          FreeAndNil(ACBrNFe);
      end;
end;


function  GravarManifestoNFe(NrChaDoc:String;CdEventOp:Integer;pCpXML: TBrvClientDataSet;
                                                                          CdEmpres:String):boolean;
var iCdEventOp :integer;
    count      :Integer;
    lDsTransa  : TTransactionDesc;
    lQCF003    : TBrvDataSet;
    lQCF002    : TBrvDataSet;
    lQCF001    : TBrvDataSet;
begin
      try
          case CdEventOp of
            210200 : iCdEventOp := 1;
            210210 : iCdEventOp := 2;
            210220 : iCdEventOp := 3;
            210240 : iCdEventOp := 4;
          end;


          pCpXML.Close;
          pCpXML.BrQueryCode := 224;
          pCpXML.BrParams.Add('<%NrChaDoc%>;' + NrChaDoc);
          pCpXML.Open;

          if pCpXML.RecordCount = 0 then
          begin
                pCpXML.Append;
                pCpXML.FieldByName('NrChaDoc').AsString  := NrChaDoc;
                pCpXML.FieldByName('StManife').AsInteger := iCdEventOp;
                pCpXML.FieldByName('CdEmpres').AsInteger := StrToInt(CdEmpres);
                pCpXML.Post;
          end else
          begin
                lQCF003 := TBrvDataSet.Create(nil);

                try
                    lQCF003.BrDicionario   := DmDicion.BrvDicionario;
                    lQCF003.SQLConnection  := DmDicion.BrvDicionario.SQLConnection;
                    lQCF003.BrQueryCode    := 229;
                    lQCF003.GetMetadata    := False;
                    lQCF003.NumericMapping := True;
                    lQCF003.BrParams.Add('<%NrChaDoc%>;' + NrChaDoc);
                    lQCF003.BrExecuteSQL(False);

                finally
                     FreeAndNil(lQCF003);
                     FreeAndNil(lQCF002);
                     FreeAndNil(lQCF001);
                end;
          end;

          Result := True;
          except on E: Exception do
          begin
                GerarLog (E.Message,NrChaDoc,'','Gravar Manifesto','Status OPERAÇAO : '
                                                                              +IntToStr(CdEventOp));
                Result := False;
          end;
      end;

end;

procedure GerarLog(menssage : String;NrChaDoc:String;CjEmpres:String;
                                                                  CdEventOp:String;msgLivre:String);
var
  Handle: TextFile;
begin
      try
          AssignFile(Handle,  Application.ExeName+ '.log');
          if not FileExists(Application.ExeName+ '.log') then
            Rewrite(Handle);
          Append(Handle);
          Writeln(Handle,'Description.................: '+CdEventOp);
          Writeln(Handle,'DATE/TIME...................: '+DateTimeToStr(now));
          Writeln(Handle,'Description.................: '+menssage);
          Writeln(Handle,'Chave ACESSO................: '+NrChaDoc);
          Writeln(Handle,'CjEmpres....................: '+CjEmpres);
          Writeln(Handle,'ERROR.......................: '+msgLivre);
          Writeln(Handle,'===============================================');
  finally
    CloseFile(Handle);
  end;
end;

function DownloadNFe(NrChaDoc:String;CjEmpres:String;ACBrNFe:TACBrNFe;
                                                            pCpXML: TBrvClientDataSet):AnsiString ;
begin
    try
        ACBrNFe.DownloadNFe.Download.Chaves.Clear;
        ACBrNFe.DownloadNFe.Download.Chaves.Add.chNFe:= NrChaDoc;
        ACBrNFe.DownloadNFe.Download.CNPJ:= CjEmpres;
        ACBrNFe.Download;
        Result := ACBrNFe.WebServices.DownloadNFe.retDownloadNFe.XML;
    except on E: Exception do
           GerarLog (E.Message,NrChaDoc,CjEmpres,'Download NFe',
                                            ACBrNFe.WebServices.DownloadNFe.retDownloadNFe.xMotivo);
    end;
end;
function GravarNFe(TxXml:AnsiString;CjEmpres:String;CdEmpres:String;
                                                                pCpXML: TBrvClientDataSet):boolean;
var
    lDsChaDoc  : String;
    lDtEmissa  : String;
    lSnImport  : Boolean;
    lSnTransp  : String;
    lCdEmpres  : Integer;
    gBrvString : TBrvString;

begin
      try
          gBrvString := TBrvString.Create(nil);

          if Pos('<CHNFE>', UpperCase(TxXml)) > 0 then
          begin
                lDsChaDoc := Copy(TxXml, pos('<chNFe>',
                                  TxXml) + 7, 44);
          end;
          pCpXML.BrParams.Clear;
          pCpXML.Close;
          pCpXML.BrParams.Add('<%NrChaDoc%>;' + lDsChaDoc);
          pCpXML.Open;
          if pCpXML.RecordCount > 0 then
          begin
                pCpXML.Edit;
          end else
          begin
                pCpXML.Append;
          end;
          lCdEmpres := StrToInt(CdEmpres);
          if (Pos('<CHNFE>',  UpperCase(TxXml)) > 0) or (Pos('<REFCTE>', UpperCase(TxXml)) > 0) then
          begin
                pCpXML.FieldByName('NrSeqXML').AsInteger :=
                                                     DmDicion.BrvDicionario.ProxNumeroSequencial(
                                                                                'F003', 'NRSEQXML');
                pCpXML.FieldByName('CdEmpres').AsInteger := lCdEmpres ;
                pCpXML.FieldByName('NrChaDoc').AsString := lDsChaDoc   ;
                if Pos('<CHNFE>', UpperCase(TxXml)) > 0 then
                begin
                      lDsChaDoc := Copy(TxXml, pos('<chNFe>',TxXml) + 7, 44);
                      VerificaSeNFeTransporte(TxXml, lSnTransp, lCdEmpres);
                      if Pos('<XCORRECAO>',UpperCase(TxXml)) > 0 then
                      begin
                            pCpXML.FieldByName('TpXML').AsString := 'CCE';
                            lDtEmissa := Copy(TxXml,pos('<dhEvento>',TxXml) + 10, 10);
                      end else
                      begin
                            if Pos('<INFCANC',UpperCase(TxXml)) > 0 then
                            begin
                                  pCpXML.FieldByName('TpXML').AsString :='NFC';
                                  lDtEmissa := Copy(TxXml,pos('<dhRecbto>',TxXml) + 10, 10);
                            end else
                            begin
                                  pCpXML.FieldByName('TpXML').AsString :='NFE';
                                  lDtEmissa := Copy(TxXml,pos('<dEmi>', TxXml) + 6, 10);
                            end;
                      end;
                end else
                begin
                      lDsChaDoc := Copy(TxXml, pos('<refCTE>',TxXml) + 8, 44);
                      lDtEmissa := Copy(TxXml, pos('<dhEmi>',TxXml) + 7, 10);
                      pCpXML.FieldByName('TpXML').AsString  := 'CTE';
                end;
                gBrvString.Split(lDtEmissa, '-');
                pCpXML.FieldByName('DtEmissa').AsDateTime :=
                                          EncodeDate(StrToInt(gBrvString.BrSplitLista.Strings[0]),
                                                     StrToInt(gBrvString.BrSplitLista.Strings[1]),
                                                     StrToInt(gBrvString.BrSplitLista.Strings[2]));
                pCpXML.FieldByName('TxXml').AsString := TxXml;
                pCpXML.FieldByName('SnTransp').AsString  := lSnTransp;
                if lCdEmpres > 0 then
                begin
                      pCpXML.FieldByName('CdEmpres').AsInteger := lCdEmpres;
                end;
                pCpXML.BrDicionario   := DmDicion.BrvDicionario;
                pCpXML.Post;
                pCpXML.BrApplyUpdates;
                lSnImport := True;
          end;
          Result := True;
       except on E: Exception do
       begin
           GerarLog (E.Message,lDsChaDoc,CjEmpres,'Gravar NFe','');
            Result := False
      end;
 end;
end;

procedure ConnectaReceita(CjEmpres:String;NrCertif:String;NrSenCer:String;CdEstado:String;
                                                                                ACBrNFe:TACBrNFe);
Var IniFile  : String ;
    Ini     : TIniFile ;
    Ok : Boolean;
    StreamMemo : TMemoryStream;
begin
      IniFile := ChangeFileExt( Application.ExeName, '.ini') ;
      Ini := TIniFile.Create( IniFile );
      try

          ACBrNFe.Configuracoes.Certificados.NumeroSerie   := NrCertif;
          ACBrNFe.Configuracoes.Certificados.Senha         := NrSenCer;
          ACBrNFe.Configuracoes.Geral.FormaEmissao         :=
                            StrToTpEmis(OK,IntToStr(Ini.ReadInteger( 'Geral','FormaEmissao',0)+1));
          ACBrNFe.Configuracoes.Geral.Salvar               := Ini.ReadBool('Geral','Salvar',True);
          ACBrNFe.Configuracoes.Geral.PathSalvar           := Ini.ReadString('Geral','PathSalvar','');
          ACBrNFe.Configuracoes.WebServices.UF             := CdEstado;
          ACBrNFe.Configuracoes.WebServices.Ambiente       :=
                            StrToTpAmb(Ok,IntToStr(Ini.ReadInteger( 'WebService','Ambiente'  ,0)));
          ACBrNFe.Configuracoes.WebServices.Visualizar     :=
                                                    Ini.ReadBool('WebService','Visualizar',False) ;
          ACBrNFe.Configuracoes.WebServices.ProxyHost      := Ini.ReadString( 'Proxy','Host'   ,'');
          ACBrNFe.Configuracoes.WebServices.ProxyPort      := Ini.ReadString( 'Proxy','Porta'  ,'');
          ACBrNFe.Configuracoes.WebServices.ProxyUser      := Ini.ReadString( 'Proxy','User'   ,'');
          ACBrNFe.Configuracoes.WebServices.ProxyPass      := Ini.ReadString( 'Proxy','Pass'   ,'');
      except
      on E: Exception do
          GerarLog (E.Message,'','','Inseri Configuração ','');
      end;

     Ini.Free ;


end;

function CarregaConsulta(TxXml: String):Boolean;
var
  DOM: IXMLDocument;
  lXML: String;
  begin
        if Pos('<XMOTIVO>', UpperCase(TxXml)) > 0 then
        begin
              lXML := Copy(TxXml, pos('<xMotivo>',
                                            TxXml) + 1,240);
              if pos('<Rejeicao>',lXML) > 0 then
              begin
                Result := false;
              end else
              begin
                Result := true;
              end;
        end else
        begin
          Result := true;
        end;
  end;
function  GerarStatusManifesto(NrChaDoc:String;CjEmpres:String;CdEmpres:String;CdEventOp:Integer;
                                          ACBrNFe:TACBrNFe;CpXML: TBrvClientDataSet): boolean;
 var
    Ok                : Boolean;
    DescEventOp       : TpcnTpEvento ;
    Chave             : String ;
    lCdUsuari         : Integer;
begin
      try
      begin

        case CdEventOp of
            210200 : DescEventOp := teManifDestConfirmacao;
            210210 : DescEventOp := teManifDestCiencia;
            210220 : DescEventOp := teManifDestDesconhecimento;
            210240 : DescEventOp := teManifDestOperNaoRealizada;
        end;
          Chave := IntToStr(CdEventOp);
          ACBrNFe.EventoNFe.Evento.Clear;
          ACBrNFe.EventoNFe.Evento.Add.infEvento.chNFe    := NrChaDoc;
          ACBrNFe.EventoNFe.Evento.Add.infEvento.CNPJ     := CjEmpres;
          ACBrNFe.EventoNFe.Evento.Add.infEvento.dhEvento := now;
          ACBrNFe.EventoNFe.Evento.Add.infEvento.tpEvento := DescEventOp;
          ACBrNFe.EnviarEventoNFe(1);
          if Pos('REJEICAO: CODIGO DO ORGAO DIVERGE DO ORGAO AUTORIZADOR', UpperCase(
                AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].RetInfEvento.xMotivo)
                                                                                         ) > 0 then
          begin
                ACBrNFe.EventoNFe.Evento.Clear;
                ACBrNFe.EventoNFe.Evento.Add.infEvento.chNFe    := NrChaDoc;
                ACBrNFe.EventoNFe.Evento.Add.infEvento.CNPJ     := CjEmpres;
                ACBrNFe.EventoNFe.Evento.Add.infEvento.dhEvento := now;
                ACBrNFe.EventoNFe.Evento.Add.infEvento.tpEvento := DescEventOp;
                ACBrNFe.EventoNFe.Evento.Add.infEvento.cOrgao   := 91;
                ACBrNFe.EnviarEventoNFe(1);
          end;
          CarregaConsulta(ACBrNFe.WebServices.Consulta.RetWS);
          if(AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].RetInfEvento.chNFe <>'')
            and (Pos('REJEICAO',UpperCase(AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.
                                                            Items[0].RetInfEvento.xMotivo)) > 0) then
          begin
                Result := True;
                GravarManifestoNFe(AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].
                                                      RetInfEvento.chNFe,CdEventOp,CpXML, cdempres);
                GerarLog (AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].
                                                      RetInfEvento.xMotivo,
                                AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].
                                                      RetInfEvento.chNFe,
                                AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].
                                                      RetInfEvento.CNPJDest,
                                'Consultar Manifesto',AcbrNFe.WebServices.EnvEvento.EventoRetorno.
                                                      retEvento.Items[0].RetInfEvento.xMotivo);
          end else
          begin
                Result := False;
                GerarLog (AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].
                                                                               RetInfEvento.xMotivo,
                                AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].
                                                                                 RetInfEvento.chNFe,
                                AcbrNFe.WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].
                                                                              RetInfEvento.CNPJDest,
                                'Consultar Manifesto',AcbrNFe.WebServices.EnvEvento.EventoRetorno.
                                                           retEvento.Items[0].RetInfEvento.xMotivo);
          end;
      end;
      except
        on E: Exception do
            GerarLog (E.Message,NrChaDoc,CjEmpres,'Altera Status Manifesto Receita',AcbrNFe.
                       WebServices.EnvEvento.EventoRetorno.retEvento.Items[0].RetInfEvento.xMotivo);
        end;
end;

function BuscarNFeManifesto(CjEmpres:String;CdEmpres:String;ACBrNFe:TACBrNFe;
                                     pCpXML: TBrvClientDataSet;CPEmpresa:TBrvClientDataSet):boolean;
var
     ok        : boolean;
     lStlAnexo :AnsiString;
     lDsChaDoc :AnsiString;
begin
      try
          ACBrNFe.ConsultaNFeDest(CjEmpres,StrToIndicadorNFe(ok,'0'),
                                                                 StrToIndicadorEmissor(ok,'0'),'0');
          lStlAnexo := AcbrNFe.WebServices.ConsNFeDest.retConsNFeDest.XML;
          if Pos('<CHNFE>', UpperCase(lStlAnexo)) > 0 then
          begin
                while Pos('<CHNFE>', UpperCase(lStlAnexo)) > 0 do
                begin
                      lDsChaDoc := Copy(lStlAnexo, pos('<chNFe>',lStlAnexo) + 7, 44);
                      Result := True;
                      GravarNFe(DownloadNFe(lDsChaDoc,CjEmpres,ACBrNFe,pCpXML)
                                                                        ,CjEmpres,CdEmpres,pCpXML);
                      GerarStatusManifesto(lDsChaDoc,CjEmpres,CdEmpres,210210,ACBrNFe,pCpXML);
                      delete(lStlAnexo,1, pos('<chNFe>',lStlAnexo) + 7);
                end;
          end else
          begin
                GerarLog ('Error',lDsChaDoc,CjEmpres,'Manifesto Verificar Notas Receita',AcbrNFe.
                                                    WebServices.ConsNFeDest.retConsNFeDest.xMotivo);
          end;
      except on E: Exception do
           GerarLog (E.Message,lDsChaDoc,CjEmpres,'','');
      end;
end;

end.
