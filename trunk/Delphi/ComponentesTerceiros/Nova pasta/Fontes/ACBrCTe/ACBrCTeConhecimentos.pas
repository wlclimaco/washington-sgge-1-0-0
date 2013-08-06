{******************************************************************************}
{ Projeto: Componente ACBrCTe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - CTe - http://www.CTe.fazenda.gov.br                             }
{                                                                              }
{ Direitos Autorais Reservados (c) 2008 Wemerson Souto                         }
{                                       Daniel Simoes de Almeida               }
{                                       Andr� Ferreira de Moraes               }
{                                                                              }
{ Desenvolvimento                                                              }
{         de Cte: Wiliam Zacarias da Silva Rosa                                }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do Projeto ACBr     }
{ Componentes localizado em http://www.sourceforge.net/projects/acbr           }
{                                                                              }
{                                                                              }
{  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la }
{ sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) }
{ qualquer vers�o posterior.                                                   }
{                                                                              }
{  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      }
{ ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto}
{ com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  }
{ no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Voc� tamb�m pode obter uma copia da licen�a em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 28/07/2009: Andre F. Moraes
|*  - Inicio do componente ACBrCTe baseado no componente ACBrCTePCN
******************************************************************************}
{$I ACBr.inc}

unit ACBrCTeConhecimentos;

interface

uses
  Classes, Sysutils, Dialogs, Forms,
  ACBrCTeUtil, ACBrCTeConfiguracoes,
  ACBrCTeDACTEClass,
  smtpsend, ssl_openssl, mimemess, mimepart, // units para enviar email
  pcteCTe, pcteCTeR, pcteCTeW, pcnConversao, pcnAuxiliar, pcnLeitor;

type

  Conhecimento = class(TCollectionItem)
  private
    FCTe: TCTe;
    FXML: AnsiString;
    FConfirmada : Boolean;
    FMsg : AnsiString ;
    FAlertas: AnsiString;
    FNomeArq: String;
    function GetCTeXML: AnsiString;
  public
    constructor Create(Collection2: TCollection); override;
    destructor Destroy; override;
    procedure Imprimir;
    procedure ImprimirPDF;
    function SaveToFile(CaminhoArquivo: string = ''): boolean;
    function SaveToStream(Stream: TStringStream): boolean;
    procedure EnviarEmail(const sSmtpHost,
                                sSmtpPort,
                                sSmtpUser,
                                sSmtpPasswd,
                                sFrom,
                                sTo,
                                sAssunto: String;
                                sMensagem : TStrings;
                                SSL : Boolean;
                                EnviaPDF: Boolean = true;
                                sCC: TStrings = nil;
                                Anexos:TStrings=nil;
                                PedeConfirma: Boolean = False;
                                AguardarEnvio: Boolean = False;
                                NomeRemetente: String = '';
                                TLS : Boolean = True;
                                UsarThread: Boolean = True);
    property CTe: TCTe  read FCTe write FCTe;
    property XML: AnsiString  read GetCTeXML write FXML;
    property Confirmada: Boolean  read FConfirmada write FConfirmada;
    property Msg: AnsiString  read FMsg write FMsg;
    property Alertas: AnsiString read FAlertas write FAlertas;
    property NomeArq: String read FNomeArq write FNomeArq;
  end;

  TConhecimentos = class(TOwnedCollection)
  private
    FConfiguracoes : TConfiguracoes;
    FACBrCTe : TComponent ;

    function GetItem(Index: Integer): Conhecimento;
    procedure SetItem(Index: Integer; const Value: Conhecimento);
  public
    constructor Create(AOwner: TPersistent; ItemClass: TCollectionItemClass);

    procedure GerarCTe;
    procedure Assinar;
    procedure Valida;
    function ValidaAssinatura(out Msg : String) : Boolean;
    procedure Imprimir;
    procedure ImprimirPDF;
    function  Add: Conhecimento;
    function Insert(Index: Integer): Conhecimento;
    property Items[Index: Integer]: Conhecimento read GetItem  write SetItem;
    property Configuracoes: TConfiguracoes read FConfiguracoes  write FConfiguracoes;

    function GetNamePath: string; override ;
    // Incluido o Parametro AGerarCTe que determina se ap�s carregar os dados do CTe
    // para o componente, ser� gerado ou n�o novamente o XML do CTe.
    function LoadFromFile(CaminhoArquivo: string; AGerarCTe: Boolean = True): boolean;
    function LoadFromStream(Stream: TStringStream; AGerarCTe: Boolean = True): boolean;
    function LoadFromString(AString: String; AGerarCTe: Boolean = True): boolean;
    function SaveToFile(PathArquivo: string = ''): boolean;

    property ACBrCTe : TComponent read FACBrCTe ;
  end;

  TSendMailThread = class(TThread)
  private
    FException : Exception;
    // FOwner: Conhecimento;
    procedure DoHandleException;
  public
    OcorreramErros: Boolean;
    Terminado: Boolean;
    smtp : TSMTPSend;
    sFrom : String;
    sTo : String;
    sCC : TStrings;
    slmsg_Lines : TStrings;
    constructor Create;
    destructor Destroy; override;
  protected
    procedure Execute; override;
    procedure HandleException;
  end;


implementation

uses ACBrCTe, ACBrUtil, ACBrDFeUtil, pcnGerador;

{ Conhecimento }

constructor Conhecimento.Create(Collection2: TCollection);
begin
  inherited Create(Collection2);
  FCTe := TCTe.Create;

  FCTe.Ide.tpCTe  := tcNormal;
  FCTe.Ide.modelo := '57';

  FCTe.Ide.verProc := 'ACBrCTe';
  FCTe.Ide.tpAmb   := TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).Configuracoes.WebServices.Ambiente;
  FCTe.Ide.tpEmis  := TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).Configuracoes.Geral.FormaEmissao;
  if Assigned(TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).DACTe) then
     FCTe.Ide.tpImp   := TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).DACTe.TipoDACTE;
end;

destructor Conhecimento.Destroy;
begin
  FCTe.Free;
  inherited Destroy;
end;

procedure Conhecimento.Imprimir;
begin
  if not Assigned( TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).DACTE ) then
     raise Exception.Create('Componente DACTE n�o associado.')
  else
     TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).DACTE.ImprimirDACTE(CTe);
end;

procedure Conhecimento.ImprimirPDF;
begin
  if not Assigned( TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).DACTE ) then
     raise Exception.Create('Componente DACTE n�o associado.')
  else
     TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).DACTE.ImprimirDACTEPDF(CTe);
end;

function Conhecimento.SaveToFile(CaminhoArquivo: string = ''): boolean;
var
  LocCTeW : TCTeW;
begin
  try
     Result  := True;
     LocCTeW := TCTeW.Create(CTe);
     try
        LocCTeW.GerarXml;
        if DFeUtil.EstaVazio(CaminhoArquivo) then
           CaminhoArquivo := PathWithDelim(TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).Configuracoes.Geral.PathSalvar)+copy(CTe.inFCTe.ID, (length(CTe.inFCTe.ID)-44)+1, 44)+'-cte.xml';
        if DFeUtil.EstaVazio(CaminhoArquivo) or not DirectoryExists(ExtractFilePath(CaminhoArquivo)) then
           raise Exception.Create('Caminho Inv�lido: ' + CaminhoArquivo);
        LocCTeW.Gerador.SalvarArquivo(CaminhoArquivo);
        NomeArq := CaminhoArquivo;
     finally
        LocCTeW.Free;
     end;
  except
     raise;
     Result := False;
  end;
end;

function Conhecimento.SaveToStream(Stream: TStringStream): boolean;
var
  LocCTeW : TCTeW;
begin
  try
     Result  := True;
     LocCTeW := TCTeW.Create(CTe);
     try
        LocCTeW.GerarXml;
        Stream.WriteString(LocCTeW.Gerador.ArquivoFormatoXML);
     finally
        LocCTeW.Free;
     end;
  except
     Result := False;
  end;
end;

procedure Conhecimento.EnviarEmail(const sSmtpHost,
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
                                      UsarThread: Boolean = True);
var
 NomeArq : String;
 AnexosEmail:TStrings ;
 StreamCTe : TStringStream;
(*
 ThreadSMTP : TSendMailThread;
 m          : TMimemess;
 p          : TMimepart;
 StreamCTe  : TStringStream;
 NomeArq    : String;
 i          : Integer;
*)
begin
 AnexosEmail := TStringList.Create;
 StreamCTe  := TStringStream.Create('');
 try
    AnexosEmail.Clear;
    if Anexos <> nil then
      AnexosEmail.Text := Anexos.Text;
    if NomeArq <> '' then
     begin
       SaveToFile(NomeArq);
       AnexosEmail.Add(NomeArq);
     end
    else
     begin
       SaveToStream(StreamCTe) ;
     end;
    if (EnviaPDF) then
    begin
       if TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).DACTE <> nil then
       begin
          TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).DACTE.ImprimirDACTEPDF(CTe);
          NomeArq :=  StringReplace(CTe.infCTe.ID,'CTe', '', [rfIgnoreCase]);
          NomeArq := PathWithDelim(TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).DACTE.PathPDF)+NomeArq+'.pdf';
          AnexosEmail.Add(NomeArq);
       end;
    end;
    TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).EnviaEmail(sSmtpHost,
                sSmtpPort,
                sSmtpUser,
                sSmtpPasswd,
                sFrom,
                sTo,
                sAssunto,
                sMensagem,
                SSL,
                sCC,
                AnexosEmail,
                PedeConfirma,
                AguardarEnvio,
                NomeRemetente,
                TLS,
                StreamCTe,
                copy(CTe.infCTe.ID, (length(CTe.infCTe.ID)-44)+1, 44)+'-CTe.xml',
                UsarThread);
 finally
    AnexosEmail.Free ;
    StreamCTe.Free ;
 end;
(*
 m:=TMimemess.create;
 ThreadSMTP := TSendMailThread.Create(Self);  // N�o Libera, pois usa FreeOnTerminate := True ;
 StreamCTe  := TStringStream.Create('');
 try
    p := m.AddPartMultipart('mixed', nil);
    if sMensagem <> nil then
       m.AddPartText(sMensagem, p);
    SaveToStream(StreamCTe) ;
    m.AddPartBinary(StreamCTe,copy(CTe.infCTe.ID, (length(CTe.infCTe.ID)-44)+1, 44)+'-cte.xml', p);
    if (EnviaPDF) then
    begin
       if TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).DACTE <> nil then
       begin
          TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).DACTE.ImprimirDACTEPDF(CTe);
          NomeArq :=  StringReplace(CTe.infCTe.ID,'CTe', '', [rfIgnoreCase]);
          NomeArq := PathWithDelim(TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).DACTE.PathPDF)+NomeArq+'.pdf';
          m.AddPartBinaryFromFile(NomeArq, p);
       end;
    end;

    if assigned(Anexos) then
      for i := 0 to Anexos.Count - 1 do
      begin
        m.AddPartBinaryFromFile(Anexos[i], p);
      end;

    m.header.tolist.add(sTo);

    if Trim(NomeRemetente) <> ''
     then m.header.From := Format('%s<%s>', [NomeRemetente, sFrom])
     else m.header.From := sFrom;

    m.header.subject:= sAssunto;
    m.Header.ReplyTo := sFrom;
    if PedeConfirma then
       m.Header.CustomHeaders.Add('Disposition-Notification-To: '+sFrom);
    m.EncodeMessage;

    ThreadSMTP.sFrom := sFrom;
    ThreadSMTP.sTo   := sTo;
    if sCC <> nil then
       ThreadSMTP.sCC.AddStrings(sCC);
    ThreadSMTP.slmsg_Lines.AddStrings(m.Lines);

    ThreadSMTP.smtp.UserName := sSmtpUser;
    ThreadSMTP.smtp.Password := sSmtpPasswd;

    ThreadSMTP.smtp.TargetHost := sSmtpHost;
    if not CTeUtil.EstaVazio( sSmtpPort ) then     // Usa default
       ThreadSMTP.smtp.TargetPort := sSmtpPort;

    ThreadSMTP.smtp.FullSSL := SSL;
    ThreadSMTP.smtp.AutoTLS := TLS;
    TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).SetStatus( stCTeEmail );

    ThreadSMTP.Resume; // inicia a thread
    if AguardarEnvio then
     begin
      repeat
       Sleep(1000);
       Application.ProcessMessages;
      until ThreadSMTP.Terminado;
     end;
    TACBrCTe( TConhecimentos( Collection ).ACBrCTe ).SetStatus( stCTeIdle );

 finally
    m.free;
    StreamCTe.Free ;
 end;
*)
end;

function Conhecimento.GetCTeXML: AnsiString;
var
 LocCTeW : TCTeW;
begin
 LocCTeW := TCTeW.Create(Self.CTe);
 try
    LocCTeW.GerarXml;
    Result := LocCTeW.Gerador.ArquivoFormatoXML;
 finally
    LocCTeW.Free;
 end;
// Result := FXML;
end;

{ TConhecimentos }
constructor TConhecimentos.Create(AOwner: TPersistent;
  ItemClass: TCollectionItemClass);
begin
  if not (AOwner is TACBrCTe ) then
     raise Exception.Create( 'AOwner deve ser do tipo TACBrCTe') ;

  inherited;

  FACBrCTe := TACBrCTe( AOwner ) ;
end;


function TConhecimentos.Add: Conhecimento;
begin
  Result := Conhecimento(inherited Add);

  Result.CTe.Ide.tpAmb := Configuracoes.WebServices.Ambiente ;
end;

procedure TConhecimentos.Assinar;
var
  i: Integer;
  vAssinada : AnsiString;
  LocCTeW : TCTeW;
  Leitor: TLeitor;
  FMsg : AnsiString;
begin
  for i:= 0 to Self.Count-1 do
   begin
     LocCTeW := TCTeW.Create(Self.Items[i].CTe);
     try
        LocCTeW.GerarXml;
        Self.Items[i].Alertas := LocCTeW.Gerador.ListaDeAlertas.Text;
{$IFDEF ACBrCTeOpenSSL}
        if not(CTeUtil.Assinar(LocCTeW.Gerador.ArquivoFormatoXML, FConfiguracoes.Certificados.Certificado , FConfiguracoes.Certificados.Senha, vAssinada, FMsg)) then
           raise Exception.Create('Falha ao assinar Conhecimento de Transporte Eletr�nico '+
                                   IntToStr(Self.Items[i].CTe.Ide.cCT)+FMsg);
{$ELSE}
        if not(CTeUtil.Assinar(LocCTeW.Gerador.ArquivoFormatoXML, FConfiguracoes.Certificados.GetCertificado , vAssinada, FMsg)) then
           raise Exception.Create('Falha ao assinar Conhecimento de Transporte Eletr�nico '+
                                   IntToStr(Self.Items[i].CTe.Ide.cCT)+FMsg);
{$ENDIF}
        vAssinada := StringReplace( vAssinada, '<'+ENCODING_UTF8_STD+'>', '', [rfReplaceAll] ) ;
        vAssinada := StringReplace( vAssinada, '<?xml version="1.0"?>', '', [rfReplaceAll] ) ;
        Self.Items[i].XML := vAssinada;

        Leitor := TLeitor.Create;
        leitor.Grupo := vAssinada;
        Self.Items[i].CTe.signature.URI := Leitor.rAtributo('Reference URI=');
        Self.Items[i].CTe.signature.DigestValue := Leitor.rCampo(tcStr, 'DigestValue');
        Self.Items[i].CTe.signature.SignatureValue := Leitor.rCampo(tcStr, 'SignatureValue');
        Self.Items[i].CTe.signature.X509Certificate := Leitor.rCampo(tcStr, 'X509Certificate');
        Leitor.Free;

        if FConfiguracoes.Geral.Salvar then
           FConfiguracoes.Geral.Save(StringReplace(Self.Items[i].CTe.infCTe.ID, 'CTe', '', [rfIgnoreCase])+'-cte.xml', vAssinada);

        if DFeUtil.NaoEstaVazio(Self.Items[i].NomeArq) then
           FConfiguracoes.Geral.Save(ExtractFileName(Self.Items[i].NomeArq), vAssinada, ExtractFilePath(Self.Items[i].NomeArq));
     finally
        LocCTeW.Free;
     end;
   end;

end;

procedure TConhecimentos.GerarCTe;
var
 i: Integer;
 LocCTeW : TCTeW;
begin
 for i:= 0 to Self.Count-1 do
  begin
    LocCTeW := TCTeW.Create(Self.Items[i].CTe);
    try
       LocCTeW.GerarXml;
       Self.Items[i].XML     := LocCTeW.Gerador.ArquivoFormatoXML;
       Self.Items[i].Alertas := LocCTeW.Gerador.ListaDeAlertas.Text;
    finally
       LocCTeW.Free;
    end;
  end;
end;

function TConhecimentos.GetItem(Index: Integer): Conhecimento;
begin
  Result := Conhecimento(inherited Items[Index]);
end;

function TConhecimentos.GetNamePath: string;
begin
  Result := 'Conhecimento';
end;

procedure TConhecimentos.Imprimir;
begin
  if not Assigned( TACBrCTe( FACBrCTe ).DACTE ) then
     raise Exception.Create('Componente DACTE n�o associado.')
  else
     TACBrCTe( FACBrCTe ).DACTe.ImprimirDACTe(nil);
end;

procedure TConhecimentos.ImprimirPDF;
begin
  if not Assigned( TACBrCTe( FACBrCTe ).DACTE ) then
     raise Exception.Create('Componente DACTE n�o associado.')
  else
     TACBrCTe( FACBrCTe ).DACTe.ImprimirDACTePDF(nil);
end;

function TConhecimentos.Insert(Index: Integer): Conhecimento;
begin
  Result := Conhecimento(inherited Insert(Index));
end;

procedure TConhecimentos.SetItem(Index: Integer; const Value: Conhecimento);
begin
  Items[Index].Assign(Value);
end;

procedure TConhecimentos.Valida;
var
 i: Integer;
 FMsg : AnsiString;
begin
  for i:= 0 to Self.Count-1 do
   begin
     if pos('<Signature',Self.Items[i].XML) = 0 then
        Assinar;
     if not(CTeUtil.Valida(('<CTe xmlns' +
        RetornarConteudoEntre(Self.Items[i].XML, '<CTe xmlns', '</CTe>')+ '</CTe>'),
         FMsg, Self.FConfiguracoes.Geral.PathSchemas)) then
       raise Exception.Create('Falha na valida��o dos dados do Conhecimento '+
                    IntToStr(Self.Items[i].CTe.Ide.nCT) +
                    sLineBreak + Self.Items[i].Alertas + FMsg);
  end;
end;

function TConhecimentos.ValidaAssinatura(out Msg : String) : Boolean;
var
 i: Integer;
 FMsg : AnsiString;
begin
  Result := True;
  for i:= 0 to Self.Count-1 do
   begin
     if not(CTeUtil.ValidaAssinatura(('<CTe xmlns' + RetornarConteudoEntre(Self.Items[i].XML, '<CTe xmlns', '</CTe>')+ '</CTe>'), FMsg)) then
      begin
        Result := False;
        Msg := 'Falha na valida��o da assinatura do conhecimento '+
                               IntToStr(Self.Items[i].CTe.Ide.nCT)+sLineBreak+FMsg
      end
     else
       Result := True;
  end;
end;

function TConhecimentos.LoadFromFile(CaminhoArquivo: string; AGerarCTe: Boolean = True): boolean;
var
 LocCTeR : TCTeR;
 ArquivoXML: TStringList;
 XML : AnsiString;
begin
 try
    ArquivoXML := TStringList.Create;
    try
      ArquivoXML.LoadFromFile(CaminhoArquivo);
      Result := True;
      while pos('</CTe>',ArquivoXML.Text) > 0 do
       begin
         if pos('</cteProc>',ArquivoXML.Text) > 0  then
          begin
            XML := copy(ArquivoXML.Text,1,pos('</cteProc>',ArquivoXML.Text)+5);
            ArquivoXML.Text := Trim(copy(ArquivoXML.Text,pos('</cteProc>',ArquivoXML.Text)+10,length(ArquivoXML.Text)));
          end
         else
          begin
            XML := copy(ArquivoXML.Text,1,pos('</CTe>',ArquivoXML.Text)+5);
            ArquivoXML.Text := Trim(copy(ArquivoXML.Text,pos('</CTe>',ArquivoXML.Text)+6,length(ArquivoXML.Text)));
          end;
         LocCTeR := TCTeR.Create(Self.Add.CTe);
         try
            LocCTeR.Leitor.Arquivo := XML;
            LocCTeR.LerXml;
            Items[Self.Count-1].XML := LocCTeR.Leitor.Arquivo;
            Items[Self.Count-1].NomeArq := CaminhoArquivo;
            if AGerarCTe then GerarCTe;
         finally
            LocCTeR.Free;
         end;
       end;
    finally
      ArquivoXML.Free;
    end;
 except
    raise;
    Result := False;
 end;
end;

function TConhecimentos.LoadFromStream(Stream: TStringStream; AGerarCTe: Boolean = True): boolean;
var
 LocCTeR : TCTeR;
begin
  try
    Result  := True;
    LocCTeR := TCTeR.Create(Self.Add.CTe);
    try
       LocCTeR.Leitor.CarregarArquivo(Stream);
       LocCTeR.LerXml;
       Items[Self.Count-1].XML := LocCTeR.Leitor.Arquivo;
       if AGerarCTe then GerarCTe;
    finally
       LocCTeR.Free
    end;
  except
    Result := False;
  end;
end;

function TConhecimentos.SaveToFile(PathArquivo: string = ''): boolean;
var
 i : integer;
 CaminhoArquivo : String;
begin
 Result := True;
 try
    for i:= 0 to TACBrCTe( FACBrCTe ).Conhecimentos.Count-1 do
     begin
        if DFeUtil.EstaVazio(PathArquivo) then
           PathArquivo := TACBrCTe( FACBrCTe ).Configuracoes.Geral.PathSalvar
        else
           PathArquivo := ExtractFilePath(PathArquivo);
        CaminhoArquivo := PathWithDelim(PathArquivo)+copy(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.inFCTe.ID, (length(TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].CTe.inFCTe.ID)-44)+1, 44)+'-cte.xml';
        TACBrCTe( FACBrCTe ).Conhecimentos.Items[i].SaveToFile(CaminhoArquivo)
     end;
 except
    Result := False;
 end;
end;

function TConhecimentos.LoadFromString(AString: String; AGerarCTe: Boolean = True): boolean;
var
  XMLCTe: TStringStream;
begin
  try
    XMLCTe := TStringStream.Create('');
    try
      XMLCTe.WriteString(AString);
      Result := LoadFromStream(XMLCTe, AGerarCTe);
    finally
      XMLCTe.Free;
    end;
  except
    Result := False;
  end;
end;

{ TSendMailThread }

procedure TSendMailThread.DoHandleException;
begin
  // TACBrCTe(TConhecimentos(FOwner.GetOwner).ACBrCTe).SetStatus( stCTeIdle );

  // FOwner.Alertas := FException.Message;

  if FException is Exception then
    Application.ShowException(FException)
  else
    SysUtils.ShowException(FException, nil);
end;

constructor TSendMailThread.Create;
begin
  smtp        := TSMTPSend.Create;
  slmsg_Lines := TStringList.Create;
  sCC         := TStringList.Create;
  sFrom       := '';
  sTo         := '';

  FreeOnTerminate := True;

  inherited Create(True);
end;

destructor TSendMailThread.Destroy;
begin
  slmsg_Lines.Free;
  sCC.Free;
  smtp.Free;

  inherited;
end;

procedure TSendMailThread.Execute;
var
 i: integer;
begin
  inherited;

  try
    Terminado := False;
    try
      if not smtp.Login() then
        raise Exception.Create('SMTP ERROR: Login:' + smtp.EnhCodeString+sLineBreak+smtp.FullResult.Text);

      if not smtp.MailFrom( sFrom, Length(sFrom)) then
        raise Exception.Create('SMTP ERROR: MailFrom:' + smtp.EnhCodeString+sLineBreak+smtp.FullResult.Text);

      if not smtp.MailTo(sTo) then
        raise Exception.Create('SMTP ERROR: MailTo:' + smtp.EnhCodeString+sLineBreak+smtp.FullResult.Text);

      if (sCC <> nil) then
      begin
        for I := 0 to sCC.Count - 1 do
        begin
          if not smtp.MailTo(sCC.Strings[i]) then
            raise Exception.Create('SMTP ERROR: MailTo:' + smtp.EnhCodeString+sLineBreak+smtp.FullResult.Text);
        end;
      end;

      if not smtp.MailData(slmsg_Lines) then
        raise Exception.Create('SMTP ERROR: MailData:' + smtp.EnhCodeString+sLineBreak+smtp.FullResult.Text);

      if not smtp.Logout() then
        raise Exception.Create('SMTP ERROR: Logout:' + smtp.EnhCodeString+sLineBreak+smtp.FullResult.Text);
    finally
      try
        smtp.Sock.CloseSocket;
      except
      end ;
      Terminado := True;
    end;
  except
    Terminado := True;
    HandleException;
  end;
end;

procedure TSendMailThread.HandleException;
begin
  FException := Exception(ExceptObject);
  try
    // N�o mostra mensagens de EAbort
    if not (FException is EAbort) then
      Synchronize(DoHandleException);
  finally
    FException := nil;
  end;
end;

end.
