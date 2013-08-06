{******************************************************************************}
{ Projeto: Componente ACBrNFe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - NFe - http://www.nfe.fazenda.gov.br                          }
{                                                                              }
{ Direitos Autorais Reservados (c) 2008 Wemerson Souto                         }
{                                       Daniel Simoes de Almeida               }
{                                       Andr� Ferreira de Moraes               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
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
|* 16/12/2008: Wemerson Souto
|*  - Doa��o do componente para o Projeto ACBr
|* 10/02/2009: Andr� Ferreira de Moraes
|*  - Adicionado URL de todos os estados
|* 18/02/2009: Andr� Ferreira de Moraes
|*  - Criado Assinatura baseado em c�digo passado por Daniel Sim�es
|*  - Criado Valida��o do XML da NFE baseado em c�digo passado por Daniel Sim�es
|* 24/09/2012: Italo Jurisato Junior
|*  - Altera��es para funcionamento com NFC-e
******************************************************************************}
{$I ACBr.inc}

unit ACBrNFeUtil;

interface

uses {$IFNDEF ACBrNFeOpenSSL}ACBrCAPICOM_TLB, ACBrMSXML2_TLB, JwaWinCrypt, {$ENDIF}
  Classes, Forms, TypInfo,
  {$IFDEF FPC}
     LResources, Controls, Graphics, Dialogs,
  {$ELSE}
     StrUtils,
  {$ENDIF}
  ACBrNFeConfiguracoes, pcnConversao, pcnNFe, ACBrDFeUtil;


{$IFDEF ACBrNFeOpenSSL}
const
 cDTD     = '<!DOCTYPE test [<!ATTLIST infNFe Id ID #IMPLIED>]>' ;
 cDTDCanc = '<!DOCTYPE test [<!ATTLIST infCanc Id ID #IMPLIED>]>' ;
 cDTDInut = '<!DOCTYPE test [<!ATTLIST infInut Id ID #IMPLIED>]>' ;
 cDTDDpec = '<!DOCTYPE test [<!ATTLIST infDPEC Id ID #IMPLIED>]>' ;
 cDTDCCe  = '<!DOCTYPE test [<!ATTLIST infEvento Id ID #IMPLIED>]>' ;
 cDTDEven = '<!DOCTYPE test [<!ATTLIST infEvento Id ID #IMPLIED>]>' ;
{$ELSE}
const
  DSIGNS = 'xmlns:ds="http://www.w3.org/2000/09/xmldsig#"';
{$ENDIF}
{$IFNDEF ACBrNFeOpenSSL}
var
  CertStore     : IStore3;
  CertStoreMem  : IStore3;
  PrivateKey    : IPrivateKey;
  Certs         : ICertificates2;
  Cert          : ICertificate2;
  NumCertCarregado : String;
{$ENDIF}

type
  NotaUtil = class
  private
//(AC,AL,AP,AM,BA,CE,DF,ES,GO,MA,MT,MS,MG,PA,PB,PR,PE,PI,RJ,RN,RS,RO,RR,SC,SP,SE,TO);
//AC,AL,AP,MA,PA,PB,PI,RJ,RN,RR,SC,SE,TO - Estados sem WebServices pr�prios
//Estados Emissores pela Sefaz Virtual RS (Rio Grande do Sul): AC, AL, AM, AP, MS, PB, RJ, RR, SC, SE e TO.
//Estados Emissores pela Sefaz Virtual AN (Ambiente Nacional): ES, MA, PA, PI e RN.

    class function GetURLSVRS(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString; //atualizado 2.0 Homologa��o e Produ��o
    class function GetURLSVAN(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString; //atualizado 2.0 Homologa��o e Produ��o
    class function GetURLAM(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;   //atualizado 2.0 Homologa��o e Produ��o
    class function GetURLBA(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;   //atualizado 2.0 Homologa��o e Produ��o
    class function GetURLCE(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;   //atualizado 2.0 Homologa��o e Produ��o
    class function GetURLGO(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;   //atualizado 2.0 Homologa��o e Produ��o
    class function GetURLMT(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;   //atualizado 2.0 Homologa��o e Produ��o
    class function GetURLMS(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;   //atualizado 2.0 Homologa��o e Produ��o
    class function GetURLMG(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;   //atualizado 2.0 Homologa��o e Produ��o
    class function GetURLPR(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;   //atualizado 2.0 Homologa��o e Produ��o
    class function GetURLPE(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;   //atualizado 2.0 Homologa��o e Produ��o
    class function GetURLRS(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;   //atualizado 2.0 Homologa��o e Produ��o
    class function GetURLSP(AAmbiente: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;   //atualizado 2.0 Homologa��o e Produ��o
  protected

  public
    {$IFDEF ACBrNFeOpenSSL}
       class function sign_file(const Axml: PAnsiChar; const key_file: PAnsiChar; const senha: PAnsiChar): AnsiString;
       class function sign_memory(const Axml: PAnsiChar; const key_file: PAnsichar; const senha: PAnsiChar; Size: Cardinal; Ponteiro: Pointer): AnsiString;
       class Procedure InitXmlSec ;
       class Procedure ShutDownXmlSec ;
    {$ENDIF}
//    class function PosEx(const SubStr, S: AnsiString; Offset: Cardinal = 1): Integer;
//    class function PosLast(const SubStr, S: AnsiString ): Integer;
//    class function PadE(const AString : string; const nLen : Integer; const Caracter : Char = ' ') : String;
//    class function PadD(const AString : string; const nLen : Integer; const Caracter : Char = ' ') : String;
//    class function padC(const AString : string; const nLen : Integer; const Caracter : Char = ' ') : String;
//    class function SeSenao(ACondicao: Boolean; ATrue, AFalse: Variant) : Variant;
//    class function FormatFloat(AValue: Extended; const AFormat: string = ',0.00'): String;
//    class function Poem_Zeros(const Texto : String; const Tamanho : Integer) : String;overload;
//    class function Poem_Zeros(const Valor : Integer; const Tamanho : Integer) : String;overload;
    class function Modulo11(Valor: string): String;
    class function ChaveAcesso(AUF:Integer; ADataEmissao:TDateTime; ACNPJ:String; ASerie:Integer;
                               ANumero,ACodigo: Integer; AModelo:Integer=55): String;
//    class function LasString(AString: String): String;
//    class function EstaVazio(const AValue: String): Boolean;overload;
//    class procedure EstaVazio(const AValue, AMensagem: String);overload;
//    class function NaoEstaVazio(AValue: String): Boolean;
//    class function EstaZerado(AValue: Double): Boolean;overload;
//    class function EstaZerado(AValue: Integer): Boolean;overload;
//    class procedure EstaZerado(AValue: Integer; AMensagem: String);overload;
//    class function NaoEstaZerado(AValue: Double): Boolean;overload;
//    class function NaoEstaZerado(AValue: Integer): Boolean;overload;
//    class function LimpaNumero(AValue: String): String;
//    class function TrataString(const AValue: String): String;overload;
//    class function TrataString(const AValue: String; const ATamanho: Integer): String;overload;
//    class function CortaD(const AString: string; const ATamanho: Integer): String;
//    class function CortaE(const AString: string; const ATamanho: Integer): String;
//    class function FormatDate(const AString: string): String;
//    class function StringToDate(const AString: string): TDateTime;
//    class function StringToTime(const AString: string): TDateTime;
    class function StringToDateTime(const AString: string): TDateTime;
//    class function TamanhoIgual(const AValue: String; const ATamanho: Integer): Boolean;overload;
//    class procedure TamanhoIgual(const AValue: String; const ATamanho: Integer; AMensagem: String);overload;
//    class function TamanhoIgual(const AValue: Integer; const ATamanho: Integer): Boolean;overload;
//    class procedure TamanhoIgual(const AValue: Integer; const ATamanho: Integer; AMensagem: String);overload;
//    class function TamanhoMenor(const AValue: String; const ATamanho: Integer): Boolean;
    class function ValidaUFCidade(const UF, Cidade: Integer): Boolean;overload;
    class procedure ValidaUFCidade(const UF, Cidade: Integer; Const AMensagem: String);overload;
//    class function FormatarCPF(AValue : String ): String;
//    class function FormatarCNPJ(AValue : String ): String;
    class function FormatarCEP(AValue : String ): String;
    class function FormatarFone(AValue : String ): String;
//    class function FormatarNumeroDocumentoFiscal(AValue : String ): String;
    class function FormatarChaveAcesso(AValue : String ): String;
    class function GetURL(Const AUF, AAmbiente, FormaEmissao: Integer; ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
    class function IdentificaTipoSchema(Const AXML: AnsiString; var I: Integer): integer; {eventos_juaumkiko}
    class function Valida(Const AXML: AnsiString; var AMsg: AnsiString; const APathSchemas: string = ''; AModeloDF: TpcnModeloDF = moNFe): Boolean;
    class function ValidaAssinatura(const AXML: AnsiString;  var AMsg: AnsiString): Boolean;
{$IFDEF ACBrNFeOpenSSL}
    class function Assinar(const AXML, ArqPFX, PFXSenha: AnsiString; out AXMLAssinado, FMensagem: AnsiString): Boolean;
{$ELSE}
    class function Assinar(const AXML: AnsiString; Certificado : ICertificate2; out AXMLAssinado, FMensagem: AnsiString): Boolean;
{$ENDIF}
//    class function StringToFloat(AValue : String ) : Double ;
//    class function StringToFloatDef(const AValue: String; const DefaultValue: Double): Double;
    class procedure ConfAmbiente;
    class function PathAplication: String;
    class function GerarChaveContingencia(FNFe:TNFe): String;
    class function FormatarChaveContigencia(AValue: String): String;
    class function PreparaCasasDecimais(AValue: Integer): String;
    class function CollateBr(Str: String): String;
    class function UpperCase2(Str: String): String;
    class function UFtoCUF(UF : String): Integer;
  end;

implementation

uses {$IFDEF ACBrNFeOpenSSL}libxml2, libxmlsec, libxslt, {$ELSE} ComObj, {$ENDIF} Sysutils,
  Variants, ACBrUtil, ACBrConsts, ACBrNFe, pcnAuxiliar;

{ NotaUtil }

{$IFDEF ACBrNFeOpenSSL}
class function NotaUtil.sign_file(const Axml: PAnsiChar; const key_file: PAnsiChar; const senha: PAnsiChar): AnsiString;
var
  doc: xmlDocPtr;
  node: xmlNodePtr;
  dsigCtx: xmlSecDSigCtxPtr;
  buffer: PAnsiChar;
  bufSize: integer;
label done;
begin
    doc := nil;
    //node := nil;
    dsigCtx := nil;
    result := '';

    if (Axml = nil) or (key_file = nil) then Exit;

    try
       { load template }
       doc := xmlParseDoc(Axml);
       if ((doc = nil) or (xmlDocGetRootElement(doc) = nil)) then
         raise Exception.Create('Error: unable to parse');

       { find start node }
       node := xmlSecFindNode(xmlDocGetRootElement(doc), PAnsiChar(xmlSecNodeSignature), PAnsiChar(xmlSecDSigNs));
       if (node = nil) then
         raise Exception.Create('Error: start node not found');

       { create signature context, we don't need keys manager in this example }
       dsigCtx := xmlSecDSigCtxCreate(nil);
       if (dsigCtx = nil) then
         raise Exception.Create('Error :failed to create signature context');

       // { load private key}
       dsigCtx^.signKey := xmlSecCryptoAppKeyLoad(key_file, xmlSecKeyDataFormatPkcs12, senha, nil, nil);
       if (dsigCtx^.signKey = nil) then
          raise Exception.Create('Error: failed to load private pem key from "' + key_file + '"');

       { set key name to the file name, this is just an example! }
       if (xmlSecKeySetName(dsigCtx^.signKey, PAnsiChar(key_file)) < 0) then
         raise Exception.Create('Error: failed to set key name for key from "' + key_file + '"');

       { sign the template }
       if (xmlSecDSigCtxSign(dsigCtx, node) < 0) then
         raise Exception.Create('Error: signature failed');

       { print signed document to stdout }
       // xmlDocDump(stdout, doc);
       // Can't use "stdout" from Delphi, so we'll use xmlDocDumpMemory instead...
       buffer := nil;
       xmlDocDumpMemory(doc, @buffer, @bufSize);
       if (buffer <> nil) then
          { success }
          result := buffer ;
   finally
       { cleanup }
       if (dsigCtx <> nil) then
         xmlSecDSigCtxDestroy(dsigCtx);

       if (doc <> nil) then
         xmlFreeDoc(doc);
   end ;
end;

class function NotaUtil.sign_memory(const Axml: PAnsiChar; const key_file: PAnsichar; const senha: PAnsiChar; Size: Cardinal; Ponteiro: Pointer): AnsiString;
var
  doc: xmlDocPtr;
  node: xmlNodePtr;
  dsigCtx: xmlSecDSigCtxPtr;
  buffer: PAnsiChar;
  bufSize: integer;
label done;
begin
    doc := nil;
    //node := nil;
    dsigCtx := nil;
    result := '';

    if (Axml = nil) or (key_file = nil) then Exit;
    try
       { load template }
       doc := xmlParseDoc(Axml);
       if ((doc = nil) or (xmlDocGetRootElement(doc) = nil)) then
         raise Exception.Create('Error: unable to parse');

       { find start node }
       node := xmlSecFindNode(xmlDocGetRootElement(doc), PAnsiChar(xmlSecNodeSignature), PAnsiChar(xmlSecDSigNs));
       if (node = nil) then
         raise Exception.Create('Error: start node not found');

       { create signature context, we don't need keys manager in this example }
       dsigCtx := xmlSecDSigCtxCreate(nil);
       if (dsigCtx = nil) then
         raise Exception.Create('Error :failed to create signature context');

       // { load private key, assuming that there is not password }
       dsigCtx^.signKey := xmlSecCryptoAppKeyLoadMemory(Ponteiro, size, xmlSecKeyDataFormatPkcs12, senha, nil, nil);

       if (dsigCtx^.signKey = nil) then
          raise Exception.Create('Error: failed to load private pem key from "' + key_file + '"');

       { set key name to the file name, this is just an example! }
       if (xmlSecKeySetName(dsigCtx^.signKey, key_file) < 0) then
         raise Exception.Create('Error: failed to set key name for key from "' + key_file + '"');

       { sign the template }
       if (xmlSecDSigCtxSign(dsigCtx, node) < 0) then
         raise Exception.Create('Error: signature failed');

       { print signed document to stdout }
       // xmlDocDump(stdout, doc);
       // Can't use "stdout" from Delphi, so we'll use xmlDocDumpMemory instead...
       buffer := nil;
       xmlDocDumpMemory(doc, @buffer, @bufSize);
       if (buffer <> nil) then
          { success }
          result := buffer ;
   finally
       { cleanup }
       if (dsigCtx <> nil) then
         xmlSecDSigCtxDestroy(dsigCtx);

       if (doc <> nil) then
         xmlFreeDoc(doc);
   end ;
end;

class Procedure NotaUtil.InitXmlSec ;
begin
    { Init libxml and libxslt libraries }
    xmlInitParser();
    __xmlLoadExtDtdDefaultValue^ := XML_DETECT_IDS or XML_COMPLETE_ATTRS;
    xmlSubstituteEntitiesDefault(1);
    __xmlIndentTreeOutput^ := 1;


    { Init xmlsec library }
    if (xmlSecInit() < 0) then
       raise Exception.Create('Error: xmlsec initialization failed.');

    { Check loaded library version }
    if (xmlSecCheckVersionExt(1, 2, 8, xmlSecCheckVersionABICompatible) <> 1) then
       raise Exception.Create('Error: loaded xmlsec library version is not compatible.');

    (* Load default crypto engine if we are supporting dynamic
     * loading for xmlsec-crypto libraries. Use the crypto library
     * name ("openssl", "nss", etc.) to load corresponding
     * xmlsec-crypto library.
     *)
    if (xmlSecCryptoDLLoadLibrary('openssl') < 0) then
       raise Exception.Create( 'Error: unable to load default xmlsec-crypto library. Make sure'#10 +
                          			'that you have it installed and check shared libraries path'#10 +
                          			'(LD_LIBRARY_PATH) environment variable.');

    { Init crypto library }
    if (xmlSecCryptoAppInit(nil) < 0) then
       raise Exception.Create('Error: crypto initialization failed.');

    { Init xmlsec-crypto library }
    if (xmlSecCryptoInit() < 0) then
       raise Exception.Create('Error: xmlsec-crypto initialization failed.');
end ;

class Procedure NotaUtil.ShutDownXmlSec ;
begin
    { Shutdown xmlsec-crypto library }
    xmlSecCryptoShutdown();

    { Shutdown crypto library }
    xmlSecCryptoAppShutdown();

    { Shutdown xmlsec library }
    xmlSecShutdown();

    { Shutdown libxslt/libxml }
    xsltCleanupGlobals();
    xmlCleanupParser();
end ;
{$ENDIF}

class function NotaUtil.ChaveAcesso(AUF: Integer; ADataEmissao: TDateTime;
  ACNPJ: String; ASerie, ANumero, ACodigo: Integer; AModelo: Integer): String;
var
  vUF, vDataEmissao, vSerie, vNumero,
  vCodigo, vModelo: String;
begin
  vUF          := DFeUtil.Poem_Zeros(AUF, 2);
  vDataEmissao := FormatDateTime('YYMM', ADataEmissao);
  vModelo      := DFeUtil.Poem_Zeros(AModelo, 2);
  vSerie       := DFeUtil.Poem_Zeros(ASerie, 3);
  vNumero      := DFeUtil.Poem_Zeros(ANumero, 9);
  vCodigo      := DFeUtil.Poem_Zeros(ACodigo, 9);

  Result := vUF+vDataEmissao+ACNPJ+vModelo+vSerie+vNumero+vCodigo;
  Result := Result+NotaUtil.Modulo11(Result);
end;

(*
class function NotaUtil.PosEx(const SubStr, S: AnsiString; Offset: Cardinal = 1): Integer;
var
  I,X: Integer;
  Len, LenSubStr: Integer;
begin
  if Offset = 1 then
    Result := Pos(SubStr, S)
  else
  begin
    I := Offset;
    LenSubStr := Length(SubStr);
    Len := Length(S) - LenSubStr + 1;
    while I <= Len do
    begin
      if S[I] = SubStr[1] then
      begin
        X := 1;
        while (X < LenSubStr) and (S[I + X] = SubStr[X + 1]) do
          Inc(X);
        if (X = LenSubStr) then
        begin
          Result := I;
          exit;
        end;
      end;
      Inc(I);
    end;
    Result := 0;
  end;
end;
*)

(*
class function NotaUtil.PosLast(const SubStr, S: AnsiString ): Integer;
Var P : Integer ;
begin
  Result := 0 ;
  P := Pos( SubStr, S) ;
  while P <> 0 do
  begin
     Result := P ;
     P := PosEx( SubStr, S, P+1) ;
  end ;
end ;
*)

(*
class function NotaUtil.CortaD(const AString: string;
  const ATamanho: Integer): String;
begin
  Result := copy(AString,1,ATamanho);
end;
*)

(*
class function NotaUtil.CortaE(const AString: string;
  const ATamanho: Integer): String;
begin
  Result := AString;
  if Length(AString) > ATamanho then
    Result := copy(AString, Length(AString)-ATamanho+1, length(AString));
end;
*)

(*
class function NotaUtil.EstaVazio(const AValue: String): Boolean;
begin
  Result := (Trim(AValue)='');
end;
*)

(*
class function NotaUtil.EstaZerado(AValue: Double): Boolean;
begin
  Result := (AValue = 0);
end;
*)

(*
class procedure NotaUtil.EstaVazio(const AValue, AMensagem: String);
begin
  if NotaUtil.EstaVazio(AValue) then
    raise EACBrNFeException.Create(AMensagem);
end;
*)

(*
class function NotaUtil.EstaZerado(AValue: Integer): Boolean;
begin
  Result := (AValue = 0);
end;
*)

(*
class function NotaUtil.FormatDate(const AString: string): String;
var
  vTemp: TDateTime;
{$IFDEF VER140} //delphi6
{$ELSE}
  FFormato : TFormatSettings;
{$ENDIF}
begin
  try
{$IFDEF VER140} //delphi6
    DateSeparator := '/';
    ShortDateFormat := 'dd/mm/yyyy';
{$ELSE}
    FFormato.DateSeparator   := '-';
    FFormato.ShortDateFormat := 'yyyy-mm-dd';
//    vTemp := StrToDate(AString, FFormato);
{$ENDIF}
    vTemp := StrToDate(AString);
    if vTemp = 0 then
      Result := ''
    else
      Result := DateToStr(vTemp);
  except
    Result := '';
  end;
end;
*)

(*
class function NotaUtil.StringToDate(const AString: string): TDateTime;
begin
  if (AString = '0') or (AString = '') then
     Result := 0
  else
     Result := StrToDate(AString);
end;
*)

(*
class function NotaUtil.StringToTime(const AString: string): TDateTime;
begin
  if (AString = '0') or (AString = '') then
     Result := 0
  else
     Result := StrToTime(AString);
end;
*)

class function NotaUtil.StringToDateTime(const AString: string): TDateTime;
begin
  if (AString = '0') or (AString = '') then
     Result := 0
  else
     Result := StrToDateTime(AString);
end;

(*
class function NotaUtil.FormatFloat(AValue: Extended; const AFormat: string): string;
{$IFDEF VER140} //delphi6
{$ELSE}
var
  vFormato: TFormatSettings;
{$ENDIF}
begin
{$IFDEF VER140} //delphi6
  DecimalSeparator  := ',';
  ThousandSeparator := '.';
  Result := SysUtils.FormatFloat(AFormat, AValue);
{$ELSE}
  vFormato.DecimalSeparator  := ',';
  vFormato.ThousandSeparator := '.';
  Result := SysUtils.FormatFloat(AFormat, AValue, vFormato);
{$ENDIF}
end;
*)

(*
class function NotaUtil.LasString(AString: String): String;
begin
  Result := Copy(AString, Length(AString), Length(AString));
end;
*)

(*
class function NotaUtil.LimpaNumero(AValue: String): String;
var
  A : Integer ;
begin
  Result := '' ;
  For A := 1 to length(AValue) do
  begin
    {$IFDEF DELPHI12_UP}
    if CharInSet(AValue[A], ['0'..'9']) then
    {$ELSE}
    if (AValue[A] in ['0'..'9']) then
    {$ENDIF}
       Result := Result + AValue[A];
  end ;
end;
*)

class function NotaUtil.Modulo11(Valor: string): string;
var
  Soma: integer;
  Contador, Peso, Digito: integer;
begin
  Soma := 0;
  Peso := 2;
  for Contador := Length(Valor) downto 1 do
  begin
    Soma := Soma + (StrToInt(Valor[Contador]) * Peso);
    if Peso < 9 then
      Peso := Peso + 1
    else
      Peso := 2;
  end;

  Digito := 11 - (Soma mod 11);
  if (Digito > 9) then
    Digito := 0;

  Result := IntToStr(Digito);
end;

(*
class function NotaUtil.NaoEstaVazio(AValue: String): Boolean;
begin
  Result := not(EstaVazio(AValue));
end;
*)

(*
class function NotaUtil.NaoEstaZerado(AValue: Double): Boolean;
begin
  Result := not(EstaZerado(AValue));
end;

class function NotaUtil.NaoEstaZerado(AValue: Integer): Boolean;
begin
  Result := not(EstaZerado(AValue));
end;
*)

(*
class function NotaUtil.padC(const AString: string; const nLen: Integer;
  const Caracter: Char): String;
Var nCharLeft : Integer ;
    D : Double ;
begin
  Result    := copy(AString,1,nLen) ;
  D         := (nLen - Length( Result )) / 2 ;
  nCharLeft := Trunc( D ) ;
  Result    := PadE( StringOfChar(Caracter, nCharLeft)+Result, nLen, Caracter) ;
end ;
*)

(*
class function NotaUtil.PadD(const AString: string; const nLen: Integer;
  const Caracter: Char): String;
begin
  Result := copy(AString,1,nLen) ;
  Result := StringOfChar(Caracter, (nLen - Length(Result))) + Result ;
end;
*)

(*
class function NotaUtil.PadE(const AString: string; const nLen: Integer;
  const Caracter: Char): String;
begin
  Result := copy(AString,1,nLen) ;
  Result := Result + StringOfChar(Caracter, (nLen - Length(Result))) ;
end;
*)

(*
class function NotaUtil.Poem_Zeros(const Texto: String;
  const Tamanho: Integer): String;
begin
  Result := PadD(Trim(Texto),Tamanho,'0') ;
end;

class function NotaUtil.Poem_Zeros(const Valor, Tamanho: Integer): String;
begin
  Result := PadD(IntToStr(Valor), Tamanho, '0') ;
end;
*)

(*
class function NotaUtil.SeSenao(ACondicao: Boolean; ATrue,
  AFalse: Variant): Variant;
begin
  Result := AFalse;
  if ACondicao then
    Result := ATrue;
end;
*)

(*
class function NotaUtil.TrataString(const AValue: String): String;
var
  A : Integer ;
begin
  Result := '' ;
  For A := 1 to length(AValue) do
  begin
    case Ord(AValue[A]) of
      60  : Result := Result + '&lt;';  //<
      62  : Result := Result + '&gt;';  //>
      38  : Result := Result + '&amp;'; //&
      34  : Result := Result + '&quot;';//"
      39  : Result := Result + '&#39;'; //'
      32  : begin          // Retira espa�os duplos
              if ( Ord(AValue[Pred(A)]) <> 32 ) then
                 Result := Result + ' ';
            end;
      193 : Result := Result + 'A';//�
      224 : Result := Result + 'a';//�
      226 : Result := Result + 'a';//�
      234 : Result := Result + 'e';//�
      244 : Result := Result + 'o';//�
      251 : Result := Result + 'u';//�
      227 : Result := Result + 'a';//�
      245 : Result := Result + 'o';//�
      225 : Result := Result + 'a';//�
      233 : Result := Result + 'e';//�
      237 : Result := Result + 'i';//�
      243 : Result := Result + 'o';//�
      250 : Result := Result + 'u';//�
      231 : Result := Result + 'c';//�
      252 : Result := Result + 'u';//�
      192 : Result := Result + 'A';//�
      194 : Result := Result + 'A';//�
      202 : Result := Result + 'E';//�
      212 : Result := Result + 'O';//�
      219 : Result := Result + 'U';//�
      195 : Result := Result + 'A';//�
      213 : Result := Result + 'O';//�
      201 : Result := Result + 'E';//�
      205 : Result := Result + 'I';//�
      211 : Result := Result + 'O';//�
      218 : Result := Result + 'U';//�
      199 : Result := Result + 'C';//�
      220 : Result := Result + 'U';//�
    else
      Result := Result + AValue[A];
    end;
  end;
  Result := Trim(Result);
end;

class function NotaUtil.TrataString(const AValue: String;
  const ATamanho: Integer): String;
begin
  Result := NotaUtil.TrataString(NotaUtil.CortaD(AValue, ATamanho));
end;
*)

(*
class function NotaUtil.TamanhoIgual(const AValue: String;
  const ATamanho: Integer): Boolean;
begin
  Result := (Length(AValue)= ATamanho);
end;

class procedure NotaUtil.TamanhoIgual(const AValue: String;
  const ATamanho: Integer; AMensagem: String);
begin
  if not(NotaUtil.TamanhoIgual(AValue, ATamanho)) then
    raise EACBrNFeException.Create(AMensagem);
end;

class function NotaUtil.TamanhoIgual(const AValue,
  ATamanho: Integer): Boolean;
begin
  Result := (Length(IntToStr(AValue))= ATamanho);
end;

class procedure NotaUtil.TamanhoIgual(const AValue,
  ATamanho: Integer; AMensagem: String);
begin
  if not(NotaUtil.TamanhoIgual(AValue, ATamanho)) then
    raise EACBrNFeException.Create(AMensagem);
end;
*)

(*
class procedure NotaUtil.EstaZerado(AValue: Integer;
  AMensagem: String);
begin
  if NotaUtil.EstaZerado(AValue) then
    raise EACBrNFeException.Create(AMensagem);
end;
*)

(*
class function NotaUtil.FormatarCPF(AValue: String): String;
begin
  if Length(AValue) = 0 then
     Result := AValue
  else
   begin
      AValue := NotaUtil.LimpaNumero(AValue);
     Result := copy(AValue,1,3) + '.' + copy(AValue,4 ,3) + '.' +
               copy(AValue,7,3) + '-' + copy(AValue,10,2) ;
   end;
end;
*)

(*
class function NotaUtil.FormatarCNPJ(AValue: String): String;
begin
  if Length(AValue) = 0 then
     Result := AValue
  else
   begin
     AValue := NotaUtil.LimpaNumero(AValue);
     Result := copy(AValue,1,2) + '.' + copy(AValue,3,3) + '.' +
               copy(AValue,6,3) + '/' + copy(AValue,9,4) + '-' + copy(AValue,13,2) ;
   end;
end;
*)

class function NotaUtil.FormatarCEP(AValue: String): String;
begin
   Result := DFeUtil.FormatarCEP(AValue);
//  Result := copy(AValue,1,5) + '-' + copy(AValue,6,3);
end;

(*
class function NotaUtil.TamanhoMenor(const AValue: String;
  const ATamanho: Integer): Boolean;
begin
  Result := (Length(AValue) < ATamanho);
end;
*)

class function NotaUtil.FormatarFone(AValue: String): String;
//var
//  lTemp: string;
  //i: integer;
begin
   Result := DFeUtil.FormatarFone(AValue);
(*
  AValue := IntToStr(StrToInt64Def(DFeUtil.LimpaNumero(AValue),0));
  Result := AValue;
  lTemp := '';

  if DFeUtil.NaoEstaVazio(AValue) then
  begin
    if Length(AValue) < 10 then
      AValue := DFeUtil.Poem_Zeros(DFeUtil.LimpaNumero(AValue), 10);

    Result := copy(AValue,Length(AValue)-7,4)+'-'+copy(AValue,Length(AValue)-3,4);

    lTemp:=copy(AValue,1,Length(AValue)-8);
    case length(AValue) of
      10: Result := '('+lTemp+')'+Result;
      else
      begin
        lTemp:=copy(AValue,Length(AValue)-8,1);
        Result := lTemp+Result;
        lTemp:=copy(AValue,1,Length(AValue)-9);
        Result := '('+lTemp+')'+Result;
      end;
    end;
  end;
*)
  {Result := AValue;
  if NotaUtil.NaoEstaVazio(AValue) then
  begin
    AValue := NotaUtil.Poem_Zeros(NotaUtil.LimpaNumero(AValue), 10);
    Result := '('+copy(AValue,1,2) + ')' + copy(AValue,3,8);
  end;}
end;

(*
class function NotaUtil.FormatarNumeroDocumentoFiscal(
  AValue: String): String;
begin
  AValue := NotaUtil.Poem_Zeros(AValue, 9);
  Result := copy(AValue,1,3) + '.' + copy(AValue,4,3)+ '.'+
            copy(AValue,7,3);
end;
*)

class function NotaUtil.FormatarChaveAcesso(AValue: String): String;
begin
  AValue := DFeUtil.LimpaNumero(AValue);

  Result := copy(AValue,1,4)  + ' ' + copy(AValue,5,4)  + ' ' +
            copy(AValue,9,4)  + ' ' + copy(AValue,13,4) + ' ' +
            copy(AValue,17,4) + ' ' + copy(AValue,21,4) + ' ' +
            copy(AValue,25,4) + ' ' + copy(AValue,29,4) + ' ' +
            copy(AValue,33,4) + ' ' + copy(AValue,37,4) + ' ' +
            copy(AValue,41,4) ;
end;       

class function NotaUtil.GetURL(const AUF, AAmbiente, FormaEmissao : Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
//  (AC,AL,AP,AM,BA,CE,DF,ES,GO,MA,MT,MS,MG,PA,PB,PR,PE,PI,RJ,RN,RS,RO,RR,SC,SP,SE,TO);
//  (12,27,16,13,29,23,53,32,52,21,51,50,31,15,25,41,26,22,33,24,43,11,14,42,35,28,17);

case FormaEmissao of
  1,2,4,5 : begin
       case ALayOut of
         LayNfeEnvDPEC      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.nfe.fazenda.gov.br/SCERecepcaoRFB/SCERecepcaoRFB.asmx','https://hom.nfe.fazenda.gov.br/SCERecepcaoRFB/SCERecepcaoRFB.asmx');
         LayNfeConsultaDPEC : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.nfe.fazenda.gov.br/SCEConsultaRFB/SCEConsultaRFB.asmx','https://hom.nfe.fazenda.gov.br/SCEConsultaRFB/SCEConsultaRFB.asmx');
         LayNFeEventoAN     : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx', 'https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx ');
         LayNfeConsNFeDest  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.nfe.fazenda.gov.br/NFeConsultaDest/NFeConsultaDest.asmx', 'https://hom.nfe.fazenda.gov.br/NFeConsultaDest/NFeConsultaDest.asmx');
         LayNfeDownloadNFe  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.nfe.fazenda.gov.br/NfeDownloadNF/NfeDownloadNF.asmx', 'https://hom.nfe.fazenda.gov.br/NfeDownloadNF/NfeDownloadNF.asmx');
       end;
       case AUF of
         12: Result := NotaUtil.GetURLSVRS(AAmbiente,ALayOut, AModeloDF); //AC
         27: Result := NotaUtil.GetURLSVRS(AAmbiente,ALayOut, AModeloDF); //AL
         16: Result := NotaUtil.GetURLSVRS(AAmbiente,ALayOut, AModeloDF); //AP
         13: Result := NotaUtil.GetURLAM(AAmbiente,ALayOut, AModeloDF); //AM
         29: Result := NotaUtil.GetURLBA(AAmbiente,ALayOut, AModeloDF); //BA
         23: Result := NotaUtil.GetURLCE(AAmbiente,ALayOut, AModeloDF); //CE
//         53: Result := NotaUtil.GetURLDF(AAmbiente,ALayOut, AModeloDF); //DF  A partir do dia 04/10/2009 todos os contribuintes do DF dever�o modificar seus sistemas para utilizarem os servi�os da SEFAZ Virtual do Rio Grande do Sul (SVRS).
         53: Result := NotaUtil.GetURLSVRS(AAmbiente,ALayOut, AModeloDF); //DF
         32: Result := NotaUtil.GetURLSVAN(AAmbiente,ALayOut, AModeloDF); //ES
         52: Result := NotaUtil.GetURLGO(AAmbiente,ALayOut, AModeloDF); //GO
         21: Result := NotaUtil.GetURLSVAN(AAmbiente,ALayOut, AModeloDF); //MA
         51: Result := NotaUtil.GetURLMT(AAmbiente,ALayOut, AModeloDF); //MT
         50: Result := NotaUtil.GetURLMS(AAmbiente,ALayOut, AModeloDF); //MS
         31: Result := NotaUtil.GetURLMG(AAmbiente,ALayOut, AModeloDF); //MG
         15: Result := NotaUtil.GetURLSVAN(AAmbiente,ALayOut, AModeloDF); //PA
         25: Result := NotaUtil.GetURLSVRS(AAmbiente,ALayOut, AModeloDF); //PB
         41: Result := NotaUtil.GetURLPR(AAmbiente,ALayOut, AModeloDF); //PR
         26: Result := NotaUtil.GetURLPE(AAmbiente,ALayOut, AModeloDF); //PE
         22: Result := NotaUtil.GetURLSVAN(AAmbiente,ALayOut, AModeloDF); //PI
         33: Result := NotaUtil.GetURLSVRS(AAmbiente,ALayOut, AModeloDF); //RJ
         24: Result := NotaUtil.GetURLSVAN(AAmbiente,ALayOut, AModeloDF); //RN
         43: Result := NotaUtil.GetURLRS(AAmbiente,ALayOut, AModeloDF); //RS
//         11: Result := NotaUtil.GetURLRO(AAmbiente,ALayOut, AModeloDF); //RO
         11: Result := NotaUtil.GetURLSVRS(AAmbiente,ALayOut, AModeloDF); //RO  A partir do dia 03/09/2009 todos os contribuintes de ROND�NIA dever�o modificar seus sistemas para utilizarem os servi�os da SEFAZ Virtual do Rio Grande do Sul (SVRS).
         14: Result := NotaUtil.GetURLSVRS(AAmbiente,ALayOut, AModeloDF); //RR
         42: Result := NotaUtil.GetURLSVRS(AAmbiente,ALayOut, AModeloDF); //SC
         35: Result := NotaUtil.GetURLSP(AAmbiente,ALayOut, AModeloDF); //SP
         28: Result := NotaUtil.GetURLSVRS(AAmbiente,ALayOut, AModeloDF); //SE
         17: Result := NotaUtil.GetURLSVRS(AAmbiente,ALayOut, AModeloDF); //TO
      end;
     end;
  3 : begin
       case ALayOut of
         LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx','https://hom.nfe.fazenda.gov.br/SCAN/NfeRecepcao2/NfeRecepcao2.asmx');
         LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx','https://hom.nfe.fazenda.gov.br/SCAN/NfeRetRecepcao2/NfeRetRecepcao2.asmx');
         LayNfeCancelamento  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/NfeCancelamento2/NfeCancelamento2.asmx','https://hom.nfe.fazenda.gov.br/SCAN/NfeCancelamento2/NfeCancelamento2.asmx');
         LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx','https://hom.nfe.fazenda.gov.br/SCAN/NfeInutilizacao2/NfeInutilizacao2.asmx');
         LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx','https://hom.nfe.fazenda.gov.br/SCAN/NfeConsulta2/NfeConsulta2.asmx');
         LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/NFeStatusServico2/NFeStatusServico2.asmx','https://hom.nfe.fazenda.gov.br/SCAN/NfeStatusServico2/NfeStatusServico2.asmx');
         LayNFeCCe,LayNFeEvento : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx', 'https://hom.nfe.fazenda.gov.br/SCAN/RecepcaoEvento/RecepcaoEvento.asmx');
       end;
     end;
  end;
  if Result = '' then
     raise EACBrNFeException.Create('URL n�o dispon�vel para o estado solicitado.');
end;

//AC,AL,AP,MA,PA,PB,PI,RJ,RN,RR,SC,SE,TO - Estados sem WebServices pr�prios
//Estados Emissores pela Sefaz Virtual RS (Rio Grande do Sul): AC, AL, AM, AP, MS, PB, RJ, RR, SC, SE e TO.
//Estados Emissores pela Sefaz Virtual AN (Ambiente Nacional): ES, MA, PA, PI, PR e RN.

class function NotaUtil.GetURLSVRS(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao         : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefazvirtual.rs.gov.br/ws/Nferecepcao/NFeRecepcao2.asmx', 'https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/Nferecepcao/NFeRecepcao2.asmx');
      LayNfeRetRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx', 'https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx');
      LayNfeCancelamento     : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx', 'https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx');
      LayNfeInutilizacao     : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefazvirtual.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx', 'https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx');
      LayNfeConsulta         : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx', 'https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx');
      LayNfeStatusServico    : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx', 'https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx');
//    LayNfeCadastro         : Result := NotaUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento           : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefazvirtual.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx', 'https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefazvirtual.rs.gov.br/ws/Nfeautorizacao/NFeautorizacao.asmx', 'https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/Nfeautorizacao/NFeautorizacao.asmx');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefazvirtual.rs.gov.br/ws/NferetAutorizacao/NFeretAutorizacao.asmx', 'https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NferetAutorizacao/NFeretAutorizacao.asmx');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
    end;
   end;
end;

class function NotaUtil.GetURLSVAN(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao         : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx', 'https://hom.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx');
      LayNfeRetRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.sefazvirtual.fazenda.gov.br/NFeRetRecepcao2/NFeRetRecepcao2.asmx', 'https://hom.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx');
      LayNfeCancelamento     : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.sefazvirtual.fazenda.gov.br/NFeCancelamento2/NFeCancelamento2.asmx', 'https://hom.sefazvirtual.fazenda.gov.br/NfeCancelamento2/NfeCancelamento2.asmx');
      LayNfeInutilizacao     : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.sefazvirtual.fazenda.gov.br/NFeInutilizacao2/NFeInutilizacao2.asmx', 'https://hom.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx');
      LayNfeConsulta         : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.sefazvirtual.fazenda.gov.br/nfeconsulta2/nfeconsulta2.asmx', 'https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx');
      LayNfeStatusServico    : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.sefazvirtual.fazenda.gov.br/NFeStatusServico2/NFeStatusServico2.asmx', 'https://hom.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx');
//    LayNfeCadastro         : Result := NotaUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento           : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://www.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx', 'https://hom.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
    end;
   end;
end;

class function NotaUtil.GetURLAM(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/services2/services/NfeRecepcao2', 'https://homnfe.sefaz.am.gov.br/services2/services/NfeRecepcao2');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/services2/services/NfeRetRecepcao2', 'https://homnfe.sefaz.am.gov.br/services2/services/NfeRetRecepcao2');
      LayNfeCancelamento  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/services2/services/NfeCancelamento2', 'https://homnfe.sefaz.am.gov.br/services2/services/NfeCancelamento2');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/services2/services/NfeInutilizacao2', 'https://homnfe.sefaz.am.gov.br/services2/services/NfeInutilizacao2');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/services2/services/NfeConsulta2', 'https://homnfe.sefaz.am.gov.br/services2/services/NfeConsulta2');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/services2/services/NfeStatusServico2', 'https://homnfe.sefaz.am.gov.br/services2/services/NfeStatusServico2');
      LayNfeCadastro      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/services2/services/cadconsultacadastro2', 'https://homnfe.sefaz.am.gov.br/services2/services/cadconsultacadastro2');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/services2/services/RecepcaoEvento', 'https://homnfe.sefaz.am.gov.br/services2/services/RecepcaoEvento');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/nfce-services/services/NfeRecepcao2', 'https://homnfe.sefaz.am.gov.br/nfce-services/services/NfeRecepcao2');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/nfce-services/services/NfeRetRecepcao2', 'https://homnfe.sefaz.am.gov.br/nfce-services/services/NfeRetRecepcao2');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/nfce-services/services/NfeInutilizacao2', 'https://homnfe.sefaz.am.gov.br/nfce-services/services/NfeInutilizacao2');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/nfce-services/services/NfeConsulta2', 'https://homnfe.sefaz.am.gov.br/nfce-services/services/NfeConsulta2');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/nfce-services/services/NfeStatusServico2', 'https://homnfe.sefaz.am.gov.br/nfce-services/services/NfeStatusServico2');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.am.gov.br/nfce-services/services/RecepcaoEvento', 'https://homnfe.sefaz.am.gov.br/nfce-services/services/RecepcaoEvento');
    end;
   end;
end;

class function NotaUtil.GetURLBA(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeRecepcao2.asmx', 'https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeRecepcao2.asmx');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeRetRecepcao2.asmx', 'https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeRetRecepcao2.asmx');
      LayNfeCancelamento  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeCancelamento2.asmx', 'https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeCancelamento2.asmx');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeInutilizacao2.asmx', 'https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeInutilizacao2.asmx');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeConsulta2.asmx', 'https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeConsulta2.asmx');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeStatusServico2.asmx', 'https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeStatusServico2.asmx');
      LayNfeCadastro      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ba.gov.br/webservices/nfenw/CadConsultaCadastro2.asmx', 'https://hnfe.sefaz.ba.gov.br/webservices/nfenw/CadConsultaCadastro2.asmx');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ba.gov.br/webservices/sre/RecepcaoEvento.asmx', 'https://hnfe.sefaz.ba.gov.br/webservices/sre/RecepcaoEvento.asmx');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
    end;
   end;
end;

class function NotaUtil.GetURLCE(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ce.gov.br/nfe2/services/NfeRecepcao2', 'https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeRecepcao2');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ce.gov.br/nfe2/services/NfeRetRecepcao2', 'https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeRetRecepcao2');
      LayNfeCancelamento  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ce.gov.br/nfe2/services/NfeCancelamento2', 'https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeCancelamento2');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ce.gov.br/nfe2/services/NfeInutilizacao2', 'https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeInutilizacao2');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ce.gov.br/nfe2/services/NfeConsulta2', 'https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeConsulta2');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ce.gov.br/nfe2/services/NfeStatusServico2', 'https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeStatusServico2');
      LayNfeCadastro      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ce.gov.br/nfe2/services/CadConsultaCadastro2', 'https://nfeh.sefaz.ce.gov.br/nfe2/services/CadConsultaCadastro2');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.ce.gov.br/nfe2/services/RecepcaoEvento',       'https://nfeh.sefaz.ce.gov.br/nfe2/services/RecepcaoEvento');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
    end;
   end;
end;

class function NotaUtil.GetURLGO(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeRecepcao2', 'https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeRecepcao2');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeRetRecepcao2', 'https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeRetRecepcao2');
      LayNfeCancelamento  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeCancelamento2', 'https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeCancelamento2');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeInutilizacao2', 'https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeInutilizacao2');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeConsulta2', 'https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeConsulta2');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeStatusServico2', 'https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeStatusServico2');
      LayNfeCadastro      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.go.gov.br/nfe/services/v2/CadConsultaCadastro2', 'https://homolog.sefaz.go.gov.br/nfe/services/v2/CadConsultaCadastro2');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeRecepcaoEvento', 'https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeRecepcaoEvento');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
    end;
   end;
end;

class function NotaUtil.GetURLMT(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeRecepcao2', 'https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeRecepcao2');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeRetRecepcao2', 'https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeRetRecepcao2');
      LayNfeCancelamento  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeCancelamento2', 'https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeCancelamento2');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeInutilizacao2', 'https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeInutilizacao2');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeConsulta2', 'https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeConsulta2');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeStatusServico2', 'https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeStatusServico2');
      LayNfeCadastro      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.mt.gov.br/nfews/v2/services/CadConsultaCadastro2', 'https://homologacao.sefaz.mt.gov.br/nfews/v2/services/CadConsultaCadastro2');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.mt.gov.br/nfews/v2/services/RecepcaoEvento', 'https://homologacao.sefaz.mt.gov.br/nfews/v2/services/RecepcaoEvento');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
    end;
   end;
end;

class function NotaUtil.GetURLMS(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.ms.gov.br/producao/services2/NfeRecepcao2', 'https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeRecepcao2');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.ms.gov.br/producao/services2/NfeRetRecepcao2', 'https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeRetRecepcao2');
      LayNfeCancelamento  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.ms.gov.br/producao/services2/NfeCancelamento2', 'https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeCancelamento2');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.ms.gov.br/producao/services2/NfeInutilizacao2', 'https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeInutilizacao2');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.ms.gov.br/producao/services2/NfeConsulta2', 'https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeConsulta2');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.ms.gov.br/producao/services2/NfeStatusServico2', 'https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeStatusServico2');
      LayNfeCadastro      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.ms.gov.br/producao/services2/CadConsultaCadastro2', 'https://homologacao.nfe.ms.gov.br/homologacao/services2/CadConsultaCadastro2');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.ms.gov.br/producao/services2/RecepcaoEvento', 'https://homologacao.nfe.ms.gov.br/homologacao/services2/RecepcaoEvento');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
    end;
   end;
end;

class function NotaUtil.GetURLMG(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.mg.gov.br/nfe2/services/NfeRecepcao2', 'https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeRecepcao2');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.mg.gov.br/nfe2/services/NfeRetRecepcao2', 'https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeRetRecepcao2');
      LayNfeCancelamento  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.mg.gov.br/nfe2/services/NfeCancelamento2', 'https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeCancelamento2');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.mg.gov.br/nfe2/services/NfeInutilizacao2', 'https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeInutilizacao2');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.mg.gov.br/nfe2/services/NfeConsulta2', 'https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeConsulta2');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.mg.gov.br/nfe2/services/NfeStatus2', 'https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeStatus2');
      LayNfeCadastro      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.mg.gov.br/nfe2/services/cadconsultacadastro2', 'https://hnfe.fazenda.mg.gov.br/nfe2/services/cadconsultacadastro2');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.mg.gov.br/nfe2/services/RecepcaoEvento', 'https://hnfe.fazenda.mg.gov.br/nfe2/services/RecepcaoEvento');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
    end;
   end;
end;

class function NotaUtil.GetURLPR(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe2.fazenda.pr.gov.br/nfe/NFeRecepcao2', 'https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NFeRecepcao2');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe2.fazenda.pr.gov.br/nfe/NFeRetRecepcao2', 'https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NFeRetRecepcao2');
      LayNfeCancelamento  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe2.fazenda.pr.gov.br/nfe/NFeCancelamento2', 'https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NFeCancelamento2');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe2.fazenda.pr.gov.br/nfe/NFeInutilizacao2', 'https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NFeInutilizacao2');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe2.fazenda.pr.gov.br/nfe/NFeConsulta2', 'https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NFeConsulta2');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe2.fazenda.pr.gov.br/nfe/NFeStatusServico2', 'https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NFeStatusServico2');
      LayNfeCadastro      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe2.fazenda.pr.gov.br/nfe/CadConsultaCadastro2', 'https://homologacao.nfe2.fazenda.pr.gov.br/nfe/CadConsultaCadastro2');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe2.fazenda.pr.gov.br/nfe-evento/NFeRecepcaoEvento', 'https://homologacao.nfe2.fazenda.pr.gov.br/nfe-evento/NFeRecepcaoEvento');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
    end;
   end;
end;

class function NotaUtil.GetURLPE(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeRecepcao2', 'https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeRecepcao2');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeRetRecepcao2', 'https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeRetRecepcao2');
      LayNfeCancelamento  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeCancelamento2', 'https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeCancelamento2');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeInutilizacao2', 'https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeInutilizacao2');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeConsulta2', 'https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeConsulta2');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeStatusServico2', 'https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeStatusServico2');
      LayNfeCadastro      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.pe.gov.br/nfe-service/services/CadConsultaCadastro2', 'https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/CadConsultaCadastro2');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.pe.gov.br/nfe-service/services/RecepcaoEvento?wsdl', 'https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/RecepcaoEvento?wsdl');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
    end;
   end;
end;

class function NotaUtil.GetURLRS(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.rs.gov.br/ws/Nferecepcao/NFeRecepcao2.asmx', 'https://homologacao.nfe.sefaz.rs.gov.br/ws/Nferecepcao/NFeRecepcao2.asmx');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx', 'https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx');
      LayNfeCancelamento  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx', 'https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeCancelamento/NfeCancelamento2.asmx');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx', 'https://homologacao.nfe.sefaz.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx', 'https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx', 'https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx');
      LayNfeCadastro      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://sef.sefaz.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro2.asmx', 'https://sef.sefaz.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro2.asmx');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx', 'https://homologacao.nfe.sefaz.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.rs.gov.br/ws/Nfeautorizacao/NFeautorizacao.asmx', 'https://homologacao.nfe.sefaz.rs.gov.br/ws/Nfeautorizacao/NFeautorizacao.asmx');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.sefaz.rs.gov.br/ws/NferetAutorizacao/NFeretAutorizacao.asmx', 'https://homologacao.nfe.sefaz.rs.gov.br/ws/NferetAutorizacao/NFeretAutorizacao.asmx');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
    end;
   end;
end;

class function NotaUtil.GetURLSP(AAmbiente: Integer;
  ALayOut: TLayOut; AModeloDF: TpcnModeloDF = moNFe): WideString;
begin
  if AModeloDF = moNFe then 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.sp.gov.br/nfeweb/services/nferecepcao2.asmx', 'https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeRecepcao2.asmx');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.sp.gov.br/nfeweb/services/nferetrecepcao2.asmx', 'https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeRetRecepcao2.asmx');
      LayNfeCancelamento  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.sp.gov.br/nfeweb/services/nfecancelamento2.asmx', 'https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeCancelamento2.asmx');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.sp.gov.br/nfeweb/services/nfeinutilizacao2.asmx', 'https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeInutilizacao2.asmx');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.sp.gov.br/nfeweb/services/nfeconsulta2.asmx', 'https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeConsulta2.asmx');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.sp.gov.br/nfeweb/services/nfestatusservico2.asmx', 'https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeStatusServico2.asmx');
      LayNfeCadastro      : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.sp.gov.br/nfeweb/services/cadconsultacadastro2.asmx', 'https://homologacao.nfe.fazenda.sp.gov.br/nfeWEB/services/cadconsultacadastro2.asmx');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.sp.gov.br/eventosWEB/services/RecepcaoEvento.asmx', 'https://homologacao.nfe.fazenda.sp.gov.br/eventosWEB/services/RecepcaoEvento.asmx');
    end;
   end
  else 
   begin
    case ALayOut of
      LayNfeRecepcao      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeRetRecepcao   : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeInutilizacao  : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeConsulta      : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNfeStatusServico : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
      LayNFeCCe,
      LayNFeEvento        : Result := DFeUtil.SeSenao(AAmbiente=1, '', '');
    end;
   end;
end;

{$IFDEF ACBrNFeOpenSSL}
function ValidaLibXML(const AXML: AnsiString;
  var AMsg: AnsiString; const APathSchemas: string = ''; AModeloDF: TpcnModeloDF = moNFe): Boolean;
var
 doc, schema_doc : xmlDocPtr;
 parser_ctxt : xmlSchemaParserCtxtPtr;
 schema : xmlSchemaPtr;
 valid_ctxt : xmlSchemaValidCtxtPtr;
 schemError : xmlErrorPtr;
 schema_filename : AnsiString;

 Tipo,I: Integer;
begin
  Tipo := NotaUtil.IdentificaTipoSchema(AXML,I) ;

 if not DirectoryExists(DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+'Schemas',PathWithDelim(APathSchemas))) then
    raise EACBrNFeException.Create('Diret�rio de Schemas n�o encontrado'+sLineBreak+
                           DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+'Schemas',PathWithDelim(APathSchemas)));

  if AModeloDF = moNFe then 
   begin
    case Tipo of
      1: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'nfe_v' + NFenviNFe + '.xsd';
      2: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'cancNFe_v' + NFecancNFe + '.xsd';
      3: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'inutNFe_v' + NFeinutNFe + '.xsd';
      4: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'envDPEC_v' + NFeEnvDPEC + '.xsd';
      5: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'envCCe_v' + NFeCCeNFe + '.xsd';
      6: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'envEventoCancNFe_v' + NFeEventoNFe + '.xsd';
      7..10: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                                 'Schemas\',PathWithDelim(APathSchemas))+'envConfRecebto_v' + NFeEventoNFe + '.xsd';
      else schema_filename := '';
    end;
   end
  else 
   begin
    case Tipo of
      1: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'nfe_v' + NFCeEnvi + '.xsd';
      2: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'cancNFe_v' + NFCeCanc + '.xsd';
      3: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'inutNFe_v' + NFCeInut + '.xsd';
      4: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'envDPEC_v' + NFCeEnvDPEC + '.xsd';
      5: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'envCCe_v' + NFCeCCe + '.xsd';
      6: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'envEventoCancNFe_v' + NFCeEvento + '.xsd';
      7..10: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(DFeUtil.PathAplication))+
                                                 'Schemas\',PathWithDelim(APathSchemas))+'envConfRecebto_v' + NFCeEvento + '.xsd';
      else schema_filename := '';
    end;
   end;
  (*
  case Tipo of
    1:
    begin
      if DFeUtil.EstaVazio(APathSchemas) then
         schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\nfe_v2.00.xsd')
      else
         schema_filename := pchar(PathWithDelim(APathSchemas)+'nfe_v2.00.xsd');
    end;
    2:
    begin
      if DFeUtil.EstaVazio(APathSchemas) then
         schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\cancNFe_v2.00.xsd')
      else
         schema_filename := pchar(PathWithDelim(APathSchemas)+'cancNFe_v2.00.xsd');
    end;
    3:
    begin
      if DFeUtil.EstaVazio(APathSchemas) then
         schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\inutNFe_v2.00.xsd')
      else
         schema_filename := pchar(PathWithDelim(APathSchemas)+'inutNFe_v2.00.xsd');
    end;
    4:
    begin
      if DFeUtil.EstaVazio(APathSchemas) then
         schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\envDPEC_v1.01.xsd')
      else
         schema_filename := pchar(PathWithDelim(APathSchemas)+'envDPEC_v1.01.xsd');
    end;
    5:
    begin
      if DFeUtil.EstaVazio(APathSchemas) then
         schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\envCCe_v1.00.xsd')
      else
         schema_filename := pchar(PathWithDelim(APathSchemas)+'envCCe_v1.00.xsd');
    end;
    6:
    begin
      if DFeUtil.EstaVazio(APathSchemas) then
         schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\envEventoCancNFe_v1.00.xsd')
      else
         schema_filename := pchar(PathWithDelim(APathSchemas)+'envEventoCancNFe_v1.00.xsd');
    end;
    7..10:
    begin
      if DFeUtil.EstaVazio(APathSchemas) then
         schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\envConfRecebto_v1.00.xsd')
      else
         schema_filename := pchar(PathWithDelim(APathSchemas)+'envConfRecebto_v1.00.xsd');
    end;
    else
      schema_filename := '';
  end;
  *)
  
  if not FilesExists(schema_filename) then
    raise EACBrNFeException.Create('Arquivo '+schema_filename+' n�o encontrado');

 //doc         := nil;
 //schema_doc  := nil;
 //parser_ctxt := nil;
 //schema      := nil;
 //valid_ctxt  := nil;

 doc := xmlParseDoc(PAnsiChar(Axml));
 if ((doc = nil) or (xmlDocGetRootElement(doc) = nil)) then
  begin
    AMsg := 'Erro: unable to parse';
    Result := False;
    exit;
  end;

 schema_doc  := xmlReadFile(PAnsiChar(schema_filename), nil, XML_DETECT_IDS);
//  the schema cannot be loaded or is not well-formed
 if (schema_doc = nil) then
  begin
    AMsg := 'Erro: Schema n�o pode ser carregado ou est� corrompido';
    Result := False;
    exit;
  end;

  parser_ctxt  := xmlSchemaNewDocParserCtxt(schema_doc);
// unable to create a parser context for the schema */
    if (parser_ctxt = nil) then
     begin
        xmlFreeDoc(schema_doc);
        AMsg := 'Erro: unable to create a parser context for the schema';
        Result := False;
        exit;
     end;

   schema := xmlSchemaParse(parser_ctxt);
// the schema itself is not valid
    if (schema = nil) then
     begin
        xmlSchemaFreeParserCtxt(parser_ctxt);
        xmlFreeDoc(schema_doc);
        AMsg := 'Error: the schema itself is not valid';
        Result := False;
        exit;
     end;

    valid_ctxt := xmlSchemaNewValidCtxt(schema);
//   unable to create a validation context for the schema */
    if (valid_ctxt = nil) then
     begin
        xmlSchemaFree(schema);
        xmlSchemaFreeParserCtxt(parser_ctxt);
        xmlFreeDoc(schema_doc);
        AMsg := 'Error: unable to create a validation context for the schema';
        Result := False;
        exit;
     end;

    if (xmlSchemaValidateDoc(valid_ctxt, doc) <> 0) then
     begin
       schemError := xmlGetLastError();
       AMsg := IntToStr(schemError^.code)+' - '+schemError^.message;
       Result := False;
       exit;
     end;

    xmlSchemaFreeValidCtxt(valid_ctxt);
    xmlSchemaFree(schema);
    xmlSchemaFreeParserCtxt(parser_ctxt);
    xmlFreeDoc(schema_doc);
    Result := True;
end;

function ValidaAssinaturaLibXML(const Axml: PAnsiChar; out Msg: AnsiString): Boolean;
{var
  doc : xmlDocPtr;
  node : xmlNodePtr;
  dsigCtx : xmlSecDSigCtxPtr;
  mngr : xmlSecKeysMngrPtr;

  Publico : String;
  Cert: TMemoryStream;
  Cert2: TStringStream;}
begin
  Result := False;
{  Publico := copy(Axml,pos('<X509Certificate>',Axml)+17,pos('</X509Certificate>',Axml)-(pos('<X509Certificate>',Axml)+17));

  Cert := TMemoryStream.Create;
  Cert2 := TStringStream.Create(Publico);
  Cert.LoadFromStream(Cert2);
       xmlSecCryptoAppKeyCertLoadMemory
  if (xmlSecCryptoAppKeysMngrCertLoadMemory(mngr,
                                        Cert.Memory,
                                        Cert.Size,
                                        xmlSecKeyDataFormatUnknown,
                                        1) < 0) then
    raise Exception.Create('Error: failed to load certificate');
  xmlSecOpenSSLAppKeyCertLoadMemory

  doc := xmlParseDoc(Axml);
  if ((doc = nil) or (xmlDocGetRootElement(doc) = nil)) then
    raise Exception.Create('Error: unable to parse');

  node := xmlSecFindNode(xmlDocGetRootElement(doc), PAnsiChar(xmlSecNodeSignature), PAnsiChar(xmlSecDSigNs));
  if (node = nil) then
    raise Exception.Create('Error: start node not found');

  dsigCtx := xmlSecDSigCtxCreate(nil);
  if (dsigCtx = nil) then
    raise Exception.Create('Error :failed to create signature context');



  dsigCtx^.signKey := xmlSecCryptoAppKeyLoadMemory(Cert.Memory, Cert.Size, xmlSecKeyDataFormatPem, '', nil, nil);
  if (dsigCtx^.signKey = nil) then
    raise Exception.Create('Error: failed to load public pem key from "' + Axml + '"');

  { Verify signature }
 { if (xmlSecDSigCtxVerify(dsigCtx, node) < 0) then
      raise Exception.Create('Error: signature verify');

  if dsigCtx.status = xmlSecDSigStatusSucceeded then
    Result := True
  else
    Result := False;

  xmlSecDSigCtxDestroy(dsigCtx);
  xmlFreeDoc(doc);}
end;
{$ELSE}
function ValidaMSXML(XML: AnsiString; out Msg: AnsiString; const APathSchemas: string = ''; AModeloDF: TpcnModeloDF = moNFe): Boolean;
var
  DOMDocument: IXMLDOMDocument2;
  ParseError: IXMLDOMParseError;
  Schema: XMLSchemaCache;
  Tipo,I: Integer;
  schema_filename : String;
begin
  Tipo := NotaUtil.IdentificaTipoSchema(XML,I) ;

  DOMDocument := CoDOMDocument50.Create;
  DOMDocument.async := False;
  DOMDocument.resolveExternals := False;
  DOMDocument.validateOnParse := True;
  DOMDocument.loadXML(XML);

  Schema := CoXMLSchemaCache50.Create;

  if not DirectoryExists(DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas',PathWithDelim(APathSchemas))) then
     raise EACBrNFeException.Create('Diret�rio de Schemas n�o encontrado'+sLineBreak+
                           DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas',PathWithDelim(APathSchemas)));

  if AModeloDF = moNFe then 
   begin
    case Tipo of
      1: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'nfe_v' + NFenviNFe + '.xsd';
      2: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'cancNFe_v' + NFecancNFe + '.xsd';
      3: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'inutNFe_v' + NFeinutNFe + '.xsd';
      4: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'envDPEC_v' + NFeEnvDPEC + '.xsd';
      5: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'envCCe_v' + NFeCCeNFe + '.xsd';
      6: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'envEventoCancNFe_v' + NFeEventoNFe + '.xsd';
      7..10: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                                 'Schemas\',PathWithDelim(APathSchemas))+'envConfRecebto_v' + NFeEventoNFe + '.xsd';
      else schema_filename := '';
    end;
   end
  else 
   begin
    case Tipo of
      1: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'nfe_v' + NFCeEnvi + '.xsd';
      2: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'cancNFe_v' + NFCeCanc + '.xsd';
      3: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'inutNFe_v' + NFCeInut + '.xsd';
      4: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'envDPEC_v' + NFCeEnvDPEC + '.xsd';
      5: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'envCCe_v' + NFCeCCe + '.xsd';
      6: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                             'Schemas\',PathWithDelim(APathSchemas))+'envEventoCancNFe_v' + NFCeEvento + '.xsd';
      7..10: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+
                                                 'Schemas\',PathWithDelim(APathSchemas))+'envConfRecebto_v' + NFCeEvento + '.xsd';
      else schema_filename := '';
    end;
   end;

 (*
  case Tipo of
    1: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',PathWithDelim(APathSchemas))+'nfe_v2.00.xsd';
    2: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',PathWithDelim(APathSchemas))+'cancNFe_v2.00.xsd';
    3: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',PathWithDelim(APathSchemas))+'inutNFe_v2.00.xsd';
    4: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',PathWithDelim(APathSchemas))+'envDPEC_v1.01.xsd';
    5: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',PathWithDelim(APathSchemas))+'envCCe_v1.00.xsd';
    6: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',PathWithDelim(APathSchemas))+'envEventoCancNFe_v1.00.xsd';
    7..10: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',PathWithDelim(APathSchemas))+'envConfRecebto_v1.00.xsd';
    else schema_filename := '';
  end;
 *)
  if not FilesExists(schema_filename) then
     raise EACBrNFeException.Create('Arquivo '+schema_filename+' n�o encontrado');

  Schema.add( 'http://www.portalfiscal.inf.br/nfe', schema_filename );

  DOMDocument.schemas := Schema;
  ParseError := DOMDocument.validate;
  Result := (ParseError.errorCode = 0);
  Msg   := ParseError.reason;

  DOMDocument := nil;
  ParseError := nil;
  Schema := nil;
end;

function ValidaAssinaturaMSXML(XML: AnsiString; out Msg: AnsiString): Boolean;
var
  xmldoc  : IXMLDOMDocument3;
  xmldsig : IXMLDigitalSignature;

  pKeyInfo : IXMLDOMNode;
  pKey, pKeyOut : IXMLDSigKey;

begin
  xmldoc := CoDOMDocument50.Create;
  xmldsig := CoMXDigitalSignature50.Create;

  xmldoc.async              := False;
  xmldoc.validateOnParse    := False;
  xmldoc.preserveWhiteSpace := True;

   if (not xmldoc.loadXML(XML) ) then
      raise EACBrNFeException.Create('N�o foi poss�vel carregar o arquivo: '+XML);
  try
    xmldoc.setProperty('SelectionNamespaces', DSIGNS);
    xmldsig.signature := xmldoc.selectSingleNode('.//ds:Signature');

   if (xmldsig.signature = nil ) then
      raise EACBrNFeException.Create('N�o foi poss�vel carregar o ler a assinatura: '+XML);

    pKeyInfo := xmldoc.selectSingleNode('.//ds:KeyInfo/ds:X509Data');

    pKey := xmldsig.createKeyFromNode(pKeyInfo);

    try
      pKeyOut := xmldsig.verify(pKey);
    except
       on E: Exception do
          Msg := 'Erro ao verificar assinatura do arquivo: '+ E.Message;
    end;
  finally
    Result := (pKeyOut <> nil );

    pKeyOut := nil;
    pKey := nil;
    pKeyInfo := nil;
    xmldsig := nil;
    xmldoc := nil;
  end;
end;
{$ENDIF}

class function NotaUtil.IdentificaTipoSchema(const AXML: AnsiString; var I: integer): integer;
var
 lTipoEvento: String;
begin
  I := pos('<infNFe',AXML) ;
  Result := 1;
  if I = 0  then
   begin
     I := pos('<infCanc',AXML) ;
     if I > 0 then
        Result := 2
     else
      begin
        I := pos('<infInut',AXML) ;
        if I > 0 then
           Result := 3
        else
         begin
          I := Pos('<infEvento', AXML);
          if I > 0 then
          begin
            lTipoEvento := Trim(RetornarConteudoEntre(AXML,'<tpEvento>','</tpEvento>'));
            if lTipoEvento = '110111' then
              Result := 6 // Cancelamento
            else if lTipoEvento = '210200' then
              Result := 7 //Manif. Destinatario: Confirma��o da Opera��o
            else if lTipoEvento = '210210' then
              Result := 8 //Manif. Destinatario: Ci�ncia da Opera��o Realizada
            else if lTipoEvento = '210220' then
              Result := 9 //Manif. Destinatario: Desconhecimento da Opera��o
            else if lTipoEvento = '210240' then
              Result := 10 //Manif. Destinatario: Opera��o n�o Realizada
            else
              Result := 5; //Carta de Corre��o Eletr�nica
          end
          else
            Result := 4; //DPEC
         end;
     end;
   end;
end;

class function NotaUtil.Valida(const AXML: AnsiString;
  var AMsg: AnsiString; const APathSchemas: string = ''; AModeloDF: TpcnModeloDF = moNFe): Boolean;
begin
{$IFDEF ACBrNFeOpenSSL}
  Result := ValidaLibXML(AXML,AMsg,APathSchemas, AModeloDF);
{$ELSE}
  Result := ValidaMSXML(AXML,AMsg,APathSchemas, AModeloDF);
{$ENDIF}
end;

class function NotaUtil.ValidaAssinatura(const AXML: AnsiString;
  var AMsg: AnsiString): Boolean;
begin
{$IFDEF ACBrNFeOpenSSL}
  Result := ValidaAssinaturaLibXML(PAnsiChar(AXML),AMsg);
{$ELSE}
  Result := ValidaAssinaturaMSXML(AXML,AMsg);
{$ENDIF}
end;

{$IFDEF ACBrNFeOpenSSL}
function AssinarLibXML(const AXML, ArqPFX, PFXSenha : AnsiString;
  out AXMLAssinado, FMensagem: AnsiString): Boolean;
 Var I, J, PosIni, PosFim : Integer ;
     URI, AStr, XmlAss : AnsiString ;
     Tipo : Integer; // 1 - NFE 2 - Cancelamento 3 - Inutilizacao
     Cert: TMemoryStream;
     Cert2: TStringStream;
begin
  AStr := AXML ;

  //// Encontrando o URI ////
  Tipo := NotaUtil.IdentificaTipoSchema(AStr,I);

  I := DFeUtil.PosEx('Id=',AStr,I+6) ;
  if I = 0 then
     raise EACBrNFeException.Create('N�o encontrei inicio do URI: Id=') ;
  I := DFeUtil.PosEx('"',AStr,I+2) ;
  if I = 0 then
     raise EACBrNFeException.Create('N�o encontrei inicio do URI: aspas inicial') ;
  J := DFeUtil.PosEx('"',AStr,I+1) ;
  if J = 0 then
     raise EACBrNFeException.Create('N�o encontrei inicio do URI: aspas final') ;

  URI := copy(AStr,I+1,J-I-1) ;

  //// Adicionando Cabe�alho DTD, necess�rio para xmlsec encontrar o ID ////
  I := pos('?>',AStr) ;

  case Tipo of
    1: AStr := copy(AStr,1,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+1,I)))) + cDTD     + Copy(AStr,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+2,I))),Length(AStr));
    2: AStr := copy(AStr,1,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+1,I)))) + cDTDCanc + Copy(AStr,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+2,I))),Length(AStr));
    3: AStr := copy(AStr,1,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+1,I)))) + cDTDInut + Copy(AStr,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+2,I))),Length(AStr));
    4: AStr := copy(AStr,1,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+1,I)))) + cDTDDpec + Copy(AStr,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+2,I))),Length(AStr));
    5: AStr := copy(AStr,1,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+1,I)))) + cDTDCCe  + Copy(AStr,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+2,I))),Length(AStr));
    6..10: AStr := copy(AStr,1,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+1,I)))) + cDTDEven  + Copy(AStr,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+2,I))),Length(AStr));
    else AStr := '';
  end;

  //// Inserindo Template da Assinatura digital ////
  case Tipo of
    1:
    begin
      I := pos('</NFe>',AStr) ;
      if I = 0 then
        raise EACBrNFeException.Create('N�o encontrei final do XML: </NFe>') ;
    end;
    2:
    begin
      I := pos('</cancNFe>',AStr) ;
      if I = 0 then
        raise EACBrNFeException.Create('N�o encontrei final do XML: </cancNFe>') ;
    end;
    3:
    begin
      I := pos('</inutNFe>',AStr) ;
      if I = 0 then
        raise EACBrNFeException.Create('N�o encontrei final do XML: </inutNFe>') ;
    end;
    4:
    begin
      I := pos('</envDPEC>',AStr) ;
      if I = 0 then
        raise EACBrNFeException.Create('N�o encontrei final do XML: </envDPEC>') ;
    end;
    5..10:
    begin
      I := pos('</evento>',AStr) ;
      if I = 0 then
        raise EACBrNFeException.Create('N�o encontrei final do XML: </evento>') ;
    end;
    else
      raise EACBrNFeException.Create('Template de Tipo n�o implementado.') ;
  end;

  if pos('<Signature',AStr) > 0 then
     I := pos('<Signature',AStr);
     AStr := copy(AStr,1,I-1) +
            '<Signature xmlns="http://www.w3.org/2000/09/xmldsig#">'+
              '<SignedInfo>'+
                '<CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/>'+
                '<SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1" />'+
                '<Reference URI="#'+URI+'">'+
                  '<Transforms>'+
                    '<Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature" />'+
                    '<Transform Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" />'+
                  '</Transforms>'+
                  '<DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1" />'+
                  '<DigestValue></DigestValue>'+
                '</Reference>'+
              '</SignedInfo>'+
              '<SignatureValue></SignatureValue>'+
              '<KeyInfo>'+
                '<X509Data>'+
                  '<X509Certificate></X509Certificate>'+
                '</X509Data>'+
              '</KeyInfo>'+
            '</Signature>';

  // Alterado por Italo em 14/09/2012
  case Tipo of
    1: AStr := AStr + '</NFe>';
    2: AStr := AStr + '</cancNFe>';
    3: AStr := AStr + '</inutNFe>';
    4: AStr := AStr + '</envDPEC>';
    5..10: AStr := AStr + '</evento>';
//    5..10: AStr := AStr + '</evento></envEvento>';
    else AStr := '';
  end;

  if FileExists(ArqPFX) then
    XmlAss := NotaUtil.sign_file(PAnsiChar(AStr), PAnsiChar(ArqPFX), PAnsiChar(PFXSenha))
  else
   begin
    Cert := TMemoryStream.Create;
    Cert2 := TStringStream.Create(ArqPFX);
    try
      Cert.LoadFromStream(Cert2);
      XmlAss := NotaUtil.sign_memory(PAnsiChar(AStr), PAnsiChar(ArqPFX), PAnsiChar(PFXSenha), Cert.Size, Cert.Memory) ;
    finally
      Cert2.Free;
      Cert.Free;
    end;
  end;

  // Removendo quebras de linha //
  XmlAss := StringReplace( XmlAss, #10, '', [rfReplaceAll] ) ;
  XmlAss := StringReplace( XmlAss, #13, '', [rfReplaceAll] ) ;

  // Removendo DTD //
  case Tipo of
    1: XmlAss := StringReplace( XmlAss, cDTD, '', [] );
    2: XmlAss := StringReplace( XmlAss, cDTDCanc, '', [] );
    3: XmlAss := StringReplace( XmlAss, cDTDInut, '', [] );
    4: XmlAss := StringReplace( XmlAss, cDTDDpec, '', [] );
    5: XmlAss := StringReplace( XmlAss, cDTDCCe, '', [] );
    6..10: XmlAss := StringReplace( XmlAss, cDTDEven, '', [] );
    else XmlAss := '';
  end;

  PosIni := Pos('<X509Certificate>',XmlAss)-1;
  PosFim := DFeUtil.PosLast('<X509Certificate>',XmlAss);

  XmlAss := copy(XmlAss,1,PosIni)+copy(XmlAss,PosFim,length(XmlAss));

  AXMLAssinado := XmlAss ;

  Result := True;
end;
{$ELSE}
function AssinarMSXML(XML : AnsiString; Certificado : ICertificate2; out XMLAssinado : AnsiString): Boolean;
var
 I, J, PosIni, PosFim : Integer;
 URI           : String ;
 Tipo : Integer;

 xmlHeaderAntes, xmlHeaderDepois : AnsiString ;
 xmldoc  : IXMLDOMDocument3;
 xmldsig : IXMLDigitalSignature;
 dsigKey   : IXMLDSigKey;
 signedKey : IXMLDSigKey;
begin
   if Pos('<Signature',XML) <= 0 then
   begin
      Tipo := NotaUtil.IdentificaTipoSchema(XML,I);

      I := DFeUtil.PosEx('Id=',XML,6) ;
      if I = 0 then
         raise EACBrNFeException.Create('N�o encontrei inicio do URI: Id=') ;
      I := DFeUtil.PosEx('"',XML,I+2) ;
      if I = 0 then
         raise EACBrNFeException.Create('N�o encontrei inicio do URI: aspas inicial') ;
      J := DFeUtil.PosEx('"',XML,I+1) ;
      if J = 0 then
         raise EACBrNFeException.Create('N�o encontrei inicio do URI: aspas final') ;

      URI := copy(XML,I+1,J-I-1) ;

      case Tipo of
        1: XML := copy(XML,1,pos('</NFe>',XML)-1);
        2: XML := copy(XML,1,pos('</cancNFe>',XML)-1);
        3: XML := copy(XML,1,pos('</inutNFe>',XML)-1);
        4: XML := copy(XML,1,pos('</envDPEC>',XML)-1);
        5..10: XML := copy(XML,1,pos('</evento>',XML)-1);
        else XML := '';
      end;

      XML := XML + '<Signature xmlns="http://www.w3.org/2000/09/xmldsig#"><SignedInfo><CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/><SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1" />';
      XML := XML + '<Reference URI="#'+URI+'">';
      XML := XML + '<Transforms><Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature" /><Transform Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" /></Transforms><DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1" />';
      XML := XML + '<DigestValue></DigestValue></Reference></SignedInfo><SignatureValue></SignatureValue><KeyInfo></KeyInfo></Signature>';

      // Alterado por Italo em 14/09/2012
      case Tipo of
        1: XML := XML + '</NFe>';
        2: XML := XML + '</cancNFe>';
        3: XML := XML + '</inutNFe>';
        4: XML := XML + '</envDPEC>';
        5..10: XML := XML + '</evento>';
//        5..10: XML := XML + '</evento></envEvento>';
        else XML := '';
      end;
   end;

   // Lendo Header antes de assinar //
   xmlHeaderAntes := '' ;
   I := pos('?>',XML) ;
   if I > 0 then
      xmlHeaderAntes := copy(XML,1,I+1) ;

   xmldoc := CoDOMDocument50.Create;

   xmldoc.async              := False;
   xmldoc.validateOnParse    := False;
   xmldoc.preserveWhiteSpace := True;

   xmldsig := CoMXDigitalSignature50.Create;

   if (not xmldoc.loadXML(XML) ) then
      raise EACBrNFeException.Create('N�o foi poss�vel carregar o arquivo: '+XML);

   xmldoc.setProperty('SelectionNamespaces', DSIGNS);

   xmldsig.signature := xmldoc.selectSingleNode('.//ds:Signature');

   if (xmldsig.signature = nil) then
      raise EACBrNFeException.Create('� preciso carregar o template antes de assinar.');

   if NumCertCarregado <> Certificado.SerialNumber then
      CertStoreMem := nil;

   if  CertStoreMem = nil then
    begin
      CertStore := CoStore.Create;
      CertStore.Open(CAPICOM_CURRENT_USER_STORE, 'My', CAPICOM_STORE_OPEN_MAXIMUM_ALLOWED);

      CertStoreMem := CoStore.Create;
      CertStoreMem.Open(CAPICOM_MEMORY_STORE, 'Memoria', CAPICOM_STORE_OPEN_MAXIMUM_ALLOWED);

      Certs := CertStore.Certificates as ICertificates2;
      for i:= 1 to Certs.Count do
      begin
        Cert := IInterface(Certs.Item[i]) as ICertificate2;
        if Cert.SerialNumber = Certificado.SerialNumber then
         begin
           CertStoreMem.Add(Cert);
           NumCertCarregado := Certificado.SerialNumber;
         end;
      end;
   end;

   OleCheck(IDispatch(Certificado.PrivateKey).QueryInterface(IPrivateKey,PrivateKey));
   xmldsig.store := CertStoreMem;

   dsigKey := xmldsig.createKeyFromCSP(PrivateKey.ProviderType, PrivateKey.ProviderName, PrivateKey.ContainerName, 0);
   if (dsigKey = nil) then
      raise EACBrNFeException.Create('Erro ao criar a chave do CSP.');

   signedKey := xmldsig.sign(dsigKey, $00000002);
   if (signedKey <> nil) then
    begin
      XMLAssinado := xmldoc.xml;
      XMLAssinado := StringReplace( XMLAssinado, #10, '', [rfReplaceAll] ) ;
      XMLAssinado := StringReplace( XMLAssinado, #13, '', [rfReplaceAll] ) ;
      PosIni := Pos('<SignatureValue>',XMLAssinado)+length('<SignatureValue>');
      XMLAssinado := copy(XMLAssinado,1,PosIni-1)+StringReplace( copy(XMLAssinado,PosIni,length(XMLAssinado)), ' ', '', [rfReplaceAll] ) ;
      PosIni := Pos('<X509Certificate>',XMLAssinado)-1;
      PosFim := DFeUtil.PosLast('<X509Certificate>',XMLAssinado);

      XMLAssinado := copy(XMLAssinado,1,PosIni)+copy(XMLAssinado,PosFim,length(XMLAssinado));
    end
   else
      raise EACBrNFeException.Create('Assinatura Falhou.');

   if xmlHeaderAntes <> '' then
   begin
      I := pos('?>',XMLAssinado) ;
      if I > 0 then
       begin
         xmlHeaderDepois := copy(XMLAssinado,1,I+1) ;
         if xmlHeaderAntes <> xmlHeaderDepois then
            XMLAssinado := StuffString(XMLAssinado,1,length(xmlHeaderDepois),xmlHeaderAntes) ;
       end
      else
         XMLAssinado := xmlHeaderAntes + XMLAssinado ;
   end ;

   dsigKey   := nil;
   signedKey := nil;
   xmldoc    := nil;
   xmldsig   := nil;

   Result := True;
end;
{$ENDIF}

{$IFDEF ACBrNFeOpenSSL}
class function NotaUtil.Assinar(const AXML, ArqPFX, PFXSenha: AnsiString; out AXMLAssinado, FMensagem: AnsiString): Boolean;
{$ELSE}
class function NotaUtil.Assinar(const AXML: AnsiString; Certificado : ICertificate2; out AXMLAssinado, FMensagem: AnsiString): Boolean;
{$ENDIF}
begin
{$IFDEF ACBrNFeOpenSSL}
  Result := AssinarLibXML(AXML, ArqPFX, PFXSenha, AXMLAssinado, FMensagem);
{$ELSE}
  Result := AssinarMSXML(AXML,Certificado,AXMLAssinado);
{$ENDIF}
end;

class function NotaUtil.ValidaUFCidade(const UF, Cidade: Integer): Boolean;
begin
   Result := DFeUtil.ValidaUFCidade(UF, Cidade);
//  Result := (Copy(IntToStr(UF), 1, 2) = Copy(IntToStr(Cidade), 1, 2));
end;

class procedure NotaUtil.ValidaUFCidade(const UF, Cidade: Integer;
  const AMensagem: String);
begin
   DFeUtil.ValidaUFCidade(UF, Cidade, AMensagem);
//  if not(ValidaUFCidade(UF,Cidade)) then
//    raise EACBrNFeException.Create(AMensagem);
end;

(*
class function NotaUtil.StringToFloat(AValue: String): Double;
begin
  AValue := Trim( AValue ) ;

  if DecimalSeparator <> '.' then
     AValue := StringReplace(AValue,'.',DecimalSeparator,[rfReplaceAll]) ;

  if DecimalSeparator <> ',' then
     AValue := StringReplace(AValue,',',DecimalSeparator,[rfReplaceAll]) ;

  Result := StrToFloat(AValue)
end ;
*)

(*
class function NotaUtil.StringToFloatDef(const AValue: String;
  const DefaultValue: Double): Double;
begin
  try
     Result := StringToFloat( AValue ) ;
  except
     Result := DefaultValue ;
  end ;
end ;
*)

class procedure NotaUtil.ConfAmbiente;
begin
 DecimalSeparator := ',';
end;

class function NotaUtil.PathAplication: String;
begin
   Result := DFeUtil.PathAplication;
//  Result := ExtractFilePath(Application.ExeName);
end;

class function NotaUtil.GerarChaveContingencia(FNFe:TNFe): string;
   function GerarDigito_Contigencia(var Digito: integer; chave: string): boolean;
   var
     i, j: integer;
   const
     PESO = '43298765432987654329876543298765432';
   begin
     // Manual Integracao Contribuinte v2.02a - P�gina: 70 //
     chave := DFeUtil.LimpaNumero(chave);
     j := 0;
     Digito := 0;
     result := True;
     try
       for i := 1 to 35 do
         j := j + StrToInt(copy(chave, i, 1)) * StrToInt(copy(PESO, i, 1));
       Digito := 11 - (j mod 11);
       if (j mod 11) < 2 then
         Digito := 0;
     except
       result := False;
     end;
     if length(chave) <> 35 then
       result := False;
   end;
var
   wchave: string;
   wicms_s, wicms_p: string;
   wd,wm,wa: word;
   Digito: integer;
begin
   //ajustado de acordo com nota tecnica 2009.003

   //UF
   if FNFe.Dest.EnderDest.UF='EX' then
      wchave:='99' //exterior
   else
   begin
      if FNFe.Ide.tpNF=tnSaida then
         wchave:=copy(inttostr(FNFe.Dest.EnderDest.cMun),1,2) //saida
      else
         wchave:=copy(inttostr(FNFe.Emit.EnderEmit.cMun),1,2); //entrada
   end;

   //TIPO DE EMISSAO
   if FNFe.Ide.tpEmis=teContingencia then
      wchave:=wchave+'2'
   else if FNFe.Ide.tpEmis=teFSDA then
      wchave:=wchave+'5'
   else
      wchave:=wchave+'0'; //esta valor caracteriza ERRO, valor tem q ser  2 ou 5

   //CNPJ OU CPF
   if (FNFe.Dest.EnderDest.UF='EX') then
      wchave:=wchave+DFeUtil.Poem_Zeros('0',14)
   else
      wchave:=wchave+DFeUtil.Poem_Zeros(FNFe.Dest.CNPJCPF,14);

   //VALOR DA NF
   wchave:=wchave+DFeUtil.Poem_Zeros(DFeUtil.LimpaNumero(Floattostrf(FNFe.Total.ICMSTot.vNF,ffFixed,18,2)),14);

   //DESTAQUE ICMS PROPRIO E ST
   wicms_p:='2';
   wicms_s:='2';
   if (DFeUtil.NaoEstaZerado(FNFe.Total.ICMSTot.vICMS)) then
      wicms_p:='1';
   if (DFeUtil.NaoEstaZerado(FNFe.Total.ICMSTot.vST)) then
      wicms_s:='1';
   wchave:=wchave+wicms_p+wicms_s;

   //DIA DA EMISSAO
   decodedate(FNFe.Ide.dEmi,wa,wm,wd);
   wchave:=wchave+DFeUtil.Poem_Zeros(inttostr(wd),2);

   //DIGITO VERIFICADOR
   GerarDigito_Contigencia(Digito,wchave);
   wchave:=wchave+inttostr(digito);

   //RETORNA A CHAVE DE CONTINGENCIA
   result:=wchave;
end;

class function NotaUtil.FormatarChaveContigencia(AValue: String): String;
begin
  AValue := DFeUtil.LimpaNumero(AValue);
  Result := copy(AValue,1,4)  + ' ' + copy(AValue,5,4)  + ' ' +
            copy(AValue,9,4)  + ' ' + copy(AValue,13,4) + ' ' +
            copy(AValue,17,4) + ' ' + copy(AValue,21,4) + ' ' +
            copy(AValue,25,4) + ' ' + copy(AValue,29,4) + ' ' +
            copy(AValue,33,4) ;
end;

class function NotaUtil.PreparaCasasDecimais(AValue: Integer): String;
var
   i: integer;
begin
   Result:='0';
   if AValue > 0 then
      Result:=Result+'.';
   for I := 0 to AValue-1 do
      Result:=Result+'0';
end;

class function NotaUtil.CollateBr(Str: String): String;
//var
//   i, wTamanho: integer;
//   wChar, wResultado: Char;
begin
   Result := DFeUtil.CollateBr(Str);
(*
   result:='';
   wtamanho:=Length(Str);
   i:=1;
   while (i <= wtamanho) do
   begin
      wChar:=Str[i];
      case wChar of
         '�', '�', '�', '�', '�', '�',
         '�', '�', '�', '�', '�', '�': wResultado := 'A';
         '�', '�', '�', '�',
         '�', '�', '�', '�': wResultado := 'E';
         '�', '�', '�', '�',
         '�', '�', '�', '�': wResultado := 'I';
         '�', '�', '�', '�', '�',
         '�', '�', '�', '�', '�': wResultado := 'O';
         '�', '�', '�', '�',
         '�', '�', '�', '�': wResultado := 'U';
         '�', '�': wResultado := 'C';
         '�', '�': wResultado := 'N';
         '�', '�', '�', 'Y': wResultado := 'Y';
      else
         wResultado:=wChar;
      end;
      i:=i+1;
      Result:=Result+wResultado;
   end;
   Result:=UpperCase(Result);
*)
end;

class function NotaUtil.UpperCase2(Str: String): String;
//var
//   i, wTamanho: integer;
//   wChar, wResultado: Char;
begin
   Result := DFeUtil.UpperCase2(Str);
(*
   result:='';
   wtamanho:=Length(Str);
   i:=1;
   while (i <= wtamanho) do
   begin
      wChar:=Str[i];
      case wChar of
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�','�': wResultado := '�';
         '�', '�': wResultado := '�';
         '�', '�': wResultado := '�';
         '�', '�', '�', 'Y': wResultado := 'Y';
      else
         wResultado:=wChar;
      end;
      i:=i+1;
      Result:=Result+wResultado;
   end;
   Result:=UpperCase(Result);
*)
end;

class function NotaUtil.UFtoCUF(UF : String): Integer;
var
  Codigo, i: Integer;
begin
  Codigo := -1 ;
  for i:= 0 to High(NFeUF) do
  begin
    if NFeUF[I] = UF then
      Codigo := NFeUFCodigo[I];
  end;

  if Codigo < 0 then
     Result := -1
  else
     Result := Codigo;
end;

end.