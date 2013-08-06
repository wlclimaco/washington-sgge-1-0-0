unit UImpEmail;

interface

uses Forms, SysUtils, ComCtrls, Classes, IdComponent, IdTCPConnection, IdTCPClient,
     IdExplicitTLSClientServerBase, IdMessageClient, IdPOP3, IdBaseComponent, IdMessage,
     IdMessageParts, IdAttachment, DB, DBClient, BrvClientDataSet, FMTBcd, SqlExpr, BrvDataSet,
     Provider, BrvProvider, BrvString, IdText;


function  CaminhoAnexo :  String;
function  NomeArquivo(pNmArquiv : String) : String;
procedure ImportarEmails(pDsConta : String;     pDsSenha : String;
                         pCdEmail : AnsiString; pCpXML   : TBrvClientDataSet;
                         pGauge   : TProgressBar);

procedure VerificaSeNFeTransporte(pTxXml : String; var pSnTransp : String; var pCdEmpres : Integer);
function GravarEmails(pCpXML   : TBrvClientDataSet;lStlAnexo:TStrings;lIdMessag:TIdMessage;
                                                    pCdEmail : AnsiString;NrSeqXML:Integer):boolean;

implementation

uses UDmDicion;

var gBrvString : TBrvString;
    gCPEmpres  : TClientDataSet;

function GravarEmails(pCpXML : TBrvClientDataSet;lStlAnexo:TStrings;lIdMessag:TIdMessage;
                                                    pCdEmail : AnsiString;NrSeqXML:Integer):boolean;
var  lDsChaDoc : String;
     lSnTransp  : String;
     lCdEmpres  : Integer;
     lDtEmissa  : String;

begin
      if (Pos('<CHNFE>', UpperCase(lStlAnexo.Text)) > 0)  then
      begin
            lDsChaDoc := Copy(lStlAnexo.Text, pos('<chNFe>',lStlAnexo.Text) + 7, 44);
            VerificaSeNFeTransporte(lStlAnexo.Text, lSnTransp, lCdEmpres);
            if Pos('<XCORRECAO>',
                           UpperCase(lStlAnexo.Text)) > 0 then
            begin
                  pCpXML.FieldByName('TpXML').AsString := 'CCE';
                  lDtEmissa := Copy(lStlAnexo.Text,pos('<dhEvento>',lStlAnexo.Text) + 10, 10);
            end else
            begin
                  if (Pos('<INFCANC',UpperCase(lStlAnexo.Text)) > 0) or
                   (Pos('<DESCEVENTO>CANCELAMENTO</DESCEVENTO>',UpperCase(lStlAnexo.Text)) > 0) then
                  begin
                        pCpXML.FieldByName('TpXML').AsString :='NFC';
                        lDtEmissa := Copy(lStlAnexo.Text,pos('<dhEvento>',lStlAnexo.Text) + 10, 10);
                  end else
                  begin
                        pCpXML.FieldByName('TpXML').AsString :='NFE';
                        lDtEmissa := Copy(lStlAnexo.Text,pos('<dEmi>', lStlAnexo.Text) + 6, 10);
                  end;
            end;
      end else
      begin
            lDsChaDoc := Copy(lStlAnexo.Text, pos('<refCTE>',lStlAnexo.Text) + 8, 44);
            lDtEmissa := Copy(lStlAnexo.Text, pos('<dhEmi>',lStlAnexo.Text) + 7, 10);
            pCpXML.FieldByName('TpXML').AsString  := 'CTE';
      end;

      pCpXML.FieldByName('NrSeqXML').AsInteger  := NrSeqXML;
      gBrvString.Split(lDtEmissa, '-');
      pCpXML.FieldByName('DtEmissa').AsDateTime := EncodeDate(
          StrToInt(gBrvString.BrSplitLista.Strings[0]),StrToInt(gBrvString.BrSplitLista.Strings[1]),
          StrToInt(gBrvString.BrSplitLista.Strings[2]));
      pCpXML.FieldByName('NrChaDoc').AsString   := lDsChaDoc;
      pCpXML.FieldByName('TxXml').AsString := lStlAnexo.Text;
      pCpXML.FieldByName('CdEmaDes').AsString   := pCdEmail;

      if lIdMessag.Recipients.Count > 0 then
      begin
            pCpXML.FieldByName('DsRemete').AsString  := lIdMessag.Recipients.Items[0].Text;
      end;
      pCpXML.FieldByName('TxCorpo').AsString   := lIdMessag.Body.Text;
      pCpXML.FieldByName('DtEmail').AsDateTime := lIdMessag.Date;
      pCpXML.FieldByName('DsAssunt').AsString  := lIdMessag.Subject;
      pCpXML.FieldByName('SnTransp').AsString  := lSnTransp;
      if lCdEmpres > 0 then
      begin
            pCpXML.FieldByName('CdEmpres').AsInteger := lCdEmpres;
      end;

      pCpXML.Post;

      pCpXML.BrApplyUpdates;

end;

function CaminhoAnexo : String;
begin
      Result := Application.ExeName;
      Result := ExtractFileDir(Result) + '\Xml';

      if not DirectoryExists(Result) then
      begin
            CreateDir(Result);
      end;

      Result := Result + '\';
end;

procedure ImportarEmails(pDsConta : String;     pDsSenha : String;
                         pCdEmail : AnsiString; pCpXML   : TBrvClientDataSet;
                         pGauge   : TProgressBar);
const
    cHosPop   = 'pop.terra.com.br';

var lNrMensag  : Integer;
    lNrParte   : Integer;
    lNrSeqXML  : Integer;
    lPtAnexo   : String;
    lStlAnexo  : TStrings;
    lNmArqXml  : String;
    lDsChaDoc  : String;
    lSnImport  : Boolean;
    lDsParte   : TIdMessagePart;
    lDsAnexo   : TObject;
    lIdPop3    : TIdPop3;
    lIdMessag  : TIdMessage;
begin
      try
          pGauge.Position := 0;

          pCpXML.Close;
          pCpXML.Open;

          lStlAnexo := TStringList.Create;

          lIdPop3    := TIdPop3.Create(nil);
          lIdMessag  := TIdMessage.Create(nil);
          gBrvString := TBrvString.Create(nil);

          lIdPop3.Host     := cHosPop;
          lIdPop3.Password := pDsSenha;

          lNrMensag        := 0;
          lIdPop3.Username := pDsConta;
          lIdPop3.Connect;

          lPtAnexo := CaminhoAnexo;

          while (lNrMensag < lIdPop3.CheckMessages) do
          begin
                lSnImport := False;
                Inc(lNrMensag);
                lIdMessag.Clear;
                lIdPop3.Retrieve(lNrMensag, lIdMessag);

                for lNrParte := 0 to lIdMessag.MessageParts.Count - 1 do
                begin
                      lDsParte := lIdMessag.MessageParts.Items[lNrParte];

                      //ShowMessage(lDsParte.ClassName);
                      if (lDsParte is TIdAttachment) or (lDsParte is TIdText)  then
                      begin
                            try
                                if (lDsParte is TIdAttachment) then
                                begin
                                      lDsAnexo  := lDsParte as TIdAttachment;
                                      lNmArqXml := Trim(NomeArquivo(lPtAnexo +
                                                            TIdAttachment(lDsAnexo).FileName));

                                      if Trim(TIdAttachment(lDsAnexo).FileName) <> '' then
                                      begin
                                            TIdAttachment(lDsAnexo).SaveToFile(lNmArqXml);
                                            lStlAnexo.LoadFromFile(lNmArqXml);
                                      end else
                                      begin
                                            lStlAnexo.Clear;
                                      end;
                                end else
                                begin
                                      lDsAnexo  := lDsParte as TIdText;

                                      if Trim(TIdText(lDsAnexo).FileName) <> '' then
                                      begin
                                            lStlAnexo.Text := TIdText(lDsAnexo).Body.Text;

                                      end else
                                      begin
                                            lStlAnexo.Clear;
                                            lIdPop3.Delete(lNrMensag);
                                      end;
                                end;

                                if ((Pos('<CHNFE>',  UpperCase(lStlAnexo.Text)) > 0) or
                                    (Pos('<REFCTE>', UpperCase(lStlAnexo.Text)) > 0) or
                                    (Pos('<CHCTE>',  UpperCase(lStlAnexo.Text)) > 0)) then
                                begin
                                      if Pos('<RETCONSRECINFE', UpperCase(lStlAnexo.Text)) = 0 then
                                      begin
                                            lDsChaDoc := Copy(lStlAnexo.Text, pos('<chNFe>',
                                                                           lStlAnexo.Text) + 7, 44);
                                            pCpXML.Close;
                                            pCpXML.BrParams.Clear;
                                            pCpXML.BrParams.Add('<%NrChadoc%>;' + lDsChaDoc);
                                            pCpXML.Open;
                                            if(pCpXML.RecordCount = 0) then
                                            begin
                                                  pCpXML.Append;
                                                  lNrSeqXML := DmDicion.BrvDicionario.
                                                           ProxNumeroSequencial('F003', 'NRSEQXML');
                                            end else
                                            begin
                                                  pCpXML.Edit;
                                                  lNrSeqXML :=
                                                           pCpXML.FieldByName('NrSeqXML').AsInteger;
                                            end;

                                            GravarEmails(pCpXML,lStlAnexo,lIdMessag,pCdEmail,
                                                                                         lNrSeqXML);
                                            lSnImport := True;
                                      end else
                                      begin
                                            lSnImport := True;
                                      end;
                                end;

                                if (lDsParte is TIdAttachment) then
                                begin
                                      DeleteFile(lNmArqXml);
                                end;
                            except
                                  lSnImport := False
                            end;
                      end;
                end;

                if lSnImport then
                begin
                      lIdPop3.Delete(lNrMensag);
                end;
                pGauge.Stepit;
          end;
      finally
          lIdPop3.Disconnect;
          FreeAndNil(lStlAnexo);
          FreeAndNil(lIdPop3);
          FreeAndNil(lIdMessag);
          FreeAndNil(gBrvString);
      end;
end;

function  NomeArquivo(pNmArquiv : String) : String;
begin
      Result := StringReplace(pNmArquiv, '@', '_', [rfReplaceAll]);
      Result := gBrvString.RetirarAcentos(Result);
end;

procedure VerificaSeNFeTransporte(pTxXml : String; var pSnTransp : String; var pCdEmpres : Integer);

var lCjTransp : String;
begin
      if gCPEmpres = nil then
      begin
            gCPEmpres      := TClientDataSet.Create(Nil);
            gCPEmpres.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(166, '', 'XML');
      end;

      pSnTransp := 'N';
      pCdEmpres := 0;

      pTxXml := UpperCase(pTxXml);

      Delete(pTxXml, 1, Pos('<TRANSPORTA>', pTxXml) + 11);
      Delete(pTxXml, 1, Pos('<CNPJ>',       pTxXml) + 5);


      lCjTransp := gBrvString.SomenteNumero(Copy(pTxXml, 1, 14));

      gCPEmpres.Filtered := True;
      gCPEmpres.Filter   := ' CjEmpres = ' + QuotedStr(lCjTransp);

      if not gCPEmpres.Eof then
      begin
            pSnTransp := 'S';
            pCdEmpres := gCPEmpres.FieldByName('CdEmpres').AsInteger;
      end;
end;


end.
