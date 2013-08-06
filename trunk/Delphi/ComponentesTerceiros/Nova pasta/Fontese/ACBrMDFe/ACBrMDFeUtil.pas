{******************************************************************************}
{ Projeto: Componente ACBrMDFe                                                 }
{  Biblioteca multiplataforma de componentes Delphi                            }
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
|* 01/08/2012: Italo Jurisato Junior
|*  - Doa��o do componente para o Projeto ACBr
******************************************************************************}

{$I ACBr.inc}

unit ACBrMDFeUtil;

interface

uses
{$IFNDEF ACBrMDFeOpenSSL}
  ACBrCAPICOM_TLB, ACBrMSXML2_TLB, JwaWinCrypt,
{$ENDIF}
  Classes, Forms,
{$IFDEF FPC}
  LResources, Controls, Graphics, Dialogs,
{$ELSE}
  StrUtils,
{$ENDIF}
  ACBrMDFeConfiguracoes, pcnConversao, pmdfeMDFe;

{$IFDEF ACBrMDFeOpenSSL}
const
  cDTD     = '<!DOCTYPE test [<!ATTLIST infMDFe Id ID #IMPLIED>]>';
  cDTDEven = '<!DOCTYPE test [<!ATTLIST infEvento Id ID #IMPLIED>]>';
{$ELSE}
const
  DSIGNS = 'xmlns:ds="http://www.w3.org/2000/09/xmldsig#"';
{$ENDIF}
{$IFNDEF ACBrMDFeOpenSSL}
var
  CertStore    : IStore3;
  CertStoreMem : IStore3;
  PrivateKey   : IPrivateKey;
  Certs        : ICertificates2;
  Cert         : ICertificate2;
  NumCertCarregado : String;
{$ENDIF}

type
  MDFeUtil = class
  private
    // Estados Emissores pela Sefaz Virtual RS (Rio Grande do Sul):
    // AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, PA, PB, PR, PE, PI, RJ, RN,
    // RO, RR, SC, SE, TO.
    class function GetURLSVRS(AAmbiente: Integer; ALayOut: TLayOut): WideString;

    class function GetURLMG(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLRS(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLSP(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLMT(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLMS(AAmbiente: Integer; ALayOut: TLayOut): WideString;

  protected

  public
{$IFDEF ACBrMDFeOpenSSL}
    class function sign_file(const Axml: PAnsiChar; const key_file: PChar; const senha: PChar): AnsiString;
    class procedure InitXmlSec;
    class procedure ShutDownXmlSec;
{$ENDIF}
    class function GetURL(const AUF, AAmbiente, FormaEmissao: Integer; ALayOut: TLayOut): WideString;
    class function Valida(const AXML: AnsiString; var AMsg: AnsiString; const APathSchemas: string = ''): Boolean;

//    class function ValidaUFCidade(const UF, Cidade: Integer): Boolean; overload;
//    class procedure ValidaUFCidade(const UF, Cidade: Integer; const AMensagem: string); overload;
    // Alterado por Italo em 17/05/2011
    class function FormatarChaveAcesso(AValue : String; Mascara: Boolean = False ): String;
    class function FormatarNumMDFe(const AValue: Integer): string;
    class function FormatarValor(mask: TpcteMask; const AValue: real): string;
    // Incluido por Italo em 28/01/2011
    class function GerarChaveContingencia(FMDFe:TMDFe): String;
    class function FormatarChaveContingencia(AValue: String): String;
    // Incluido por Italo em 10/02/2012
    class function ValidaAssinatura(const AXML: AnsiString;  var AMsg: AnsiString): Boolean;

{$IFDEF ACBrMDFeOpenSSL}
    class function Assinar(const AXML, ArqPFX, PFXSenha: AnsiString; out AXMLAssinado, FMensagem: AnsiString): Boolean;
{$ELSE}
    class function Assinar(const AXML: AnsiString; Certificado: ICertificate2; out AXMLAssinado, FMensagem: AnsiString): Boolean;
{$ENDIF}

//    class function PathAplication: String;
//    class procedure ConfAmbiente;
    class function UFtoCUF(UF : String): Integer;
    class function IdentificaTipoSchema(Const AXML: AnsiString; var I: Integer): integer;
//    class function FormatarPlaca(AValue: string): string;
  end;

implementation

uses
 {$IFDEF ACBrMDFeOpenSSL}
  libxml2, libxmlsec, libxslt,
 {$ELSE}
  ComObj,
 {$ENDIF}
  Sysutils, Variants, ACBrUtil, ACBrDFeUtil, pcnAuxiliar;

{ MDFeUtil }
{$IFDEF ACBrMDFeOpenSSL}

class procedure MDFeUtil.InitXmlSec;
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
    raise Exception.Create('Error: unable to load default xmlsec-crypto library. Make sure'#10 +
      'that you have it installed and check shared libraries path'#10 +
      '(LD_LIBRARY_PATH) environment variable.');

  { Init crypto library }
  if (xmlSecCryptoAppInit(nil) < 0) then
    raise Exception.Create('Error: crypto initialization failed.');

  { Init xmlsec-crypto library }
  if (xmlSecCryptoInit() < 0) then
    raise Exception.Create('Error: xmlsec-crypto initialization failed.');
end;

class procedure MDFeUtil.ShutDownXmlSec;
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
end;
{$ENDIF}

class function MDFeUtil.GetURL(const AUF, AAmbiente, FormaEmissao: Integer;
  ALayOut: TLayOut): WideString;
begin
  //  (AC,AL,AP,AM,BA,CE,DF,ES,GO,MA,MT,MS,MG,PA,PB,PR,PE,PI,RJ,RN,RS,RO,RR,SC,SP,SE,TO);
  //  (12,27,16,13,29,23,53,32,52,21,51,50,31,15,25,41,26,22,33,24,43,11,14,42,35,28,17);

 case FormaEmissao of
  1,2,4,5 : begin
             case AUF of
              12: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //AC - Acre
              27: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //AL - Alagoas
              16: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //AP - Amap�
              13: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //AM - Amazonas
              29: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //BA - Bahia
              23: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //CE - Cear�
              53: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //DF - Distrito Federal
              32: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //ES - Espirito Santo
              52: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //GO - Goi�s
              21: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //MA - Maranh�o

              51: Result := MDFeUtil.GetURLMT(AAmbiente, ALayOut);               //MT - Mato Grosso
              50: Result := MDFeUtil.GetURLMS(AAmbiente, ALayOut);               //MS - Mato Grosso do Sul
              31: Result := MDFeUtil.GetURLMG(AAmbiente, ALayOut);               //MG - Minas Gerais

              15: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //PA - Par�
              25: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //PB - Paraib�
              41: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //PR - Paran�

              26: Result := MDFeUtil.GetURLSP(AAmbiente, ALayOut);               //PE - Pernambuco

              22: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //PI - Piau�
              33: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //RJ - Rio de Janeiro
              24: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //RN - Rio Grande do Norte

              43: Result := MDFeUtil.GetURLRS(AAmbiente, ALayOut);               //RS - Rio Grande do Sul

              11: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //RO - Rond�nia
              14: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //RR - Roraima
              42: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //SC - Santa Catarina

              35: Result := MDFeUtil.GetURLSP(AAmbiente, ALayOut);               //SP - S�o Paulo

              28: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //SE - Sergipe
              17: Result := MDFeUtil.GetURLSVRS(AAmbiente, ALayOut);             //TO - Tocantins
             end;
            end;
 end;
 if Result = '' then
     raise Exception.Create('URL n�o dispon�vel para o Estado solicitado.');
end;

class function MDFeUtil.GetURLSVRS(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayMDFeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx'            , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx');
    LayMDFeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx'      , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx');
    LayMDFeEvento:        Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx', 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx');
    LayMDFeConsulta:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx'            , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx');
    LayMDFeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx'  , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx');
  end;
end;

class function MDFeUtil.GetURLMG(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayMDFeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx'            , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx');
    LayMDFeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx'      , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx');
    LayMDFeEvento:        Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx', 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx');
    LayMDFeConsulta:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx'            , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx');
    LayMDFeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx'  , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx');
  end;
end;

class function MDFeUtil.GetURLRS(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayMDFeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx'            , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx');
    LayMDFeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx'      , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx');
    LayMDFeEvento:        Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx', 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx');
    LayMDFeConsulta:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx'            , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx');
    LayMDFeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx'  , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx');
  end;
end;

class function MDFeUtil.GetURLSP(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayMDFeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx'            , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx');
    LayMDFeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx'      , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx');
    LayMDFeEvento:        Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx', 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx');
    LayMDFeConsulta:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx'            , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx');
    LayMDFeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx'  , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx');
  end;
end;

class function MDFeUtil.GetURLMS(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayMDFeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx'            , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx');
    LayMDFeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx'      , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx');
    LayMDFeEvento:        Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx', 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx');
    LayMDFeConsulta:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx'            , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx');
    LayMDFeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx'  , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx');
  end;
end;

class function MDFeUtil.GetURLMT(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayMDFeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx'            , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx');
    LayMDFeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx'      , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx');
    LayMDFeEvento:        Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx', 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx');
    LayMDFeConsulta:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx'            , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx');
    LayMDFeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://mdfe.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx'  , 'https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx');
  end;
end;

class function MDFeUtil.FormatarNumMDFe(const AValue: Integer): string;
begin
  result := FormatFloat('000000000', AValue);
end;

class function MDFeUtil.FormatarValor(mask: TpcteMask; const AValue: real): string;
begin
  result := FormatFloat(TpMaskToStrText(mask), AValue);
end;

(*
class procedure MDFeUtil.ConfAmbiente;
begin
  DecimalSeparator := ',';
end;
*)

(*
class function MDFeUtil.ValidaUFCidade(const UF, Cidade: Integer): Boolean;
begin
  Result := (Copy(IntToStr(UF), 1, 2) = Copy(IntToStr(Cidade), 1, 2));
end;
*)

(*
class procedure MDFeUtil.ValidaUFCidade(const UF, Cidade: Integer; const AMensagem: string);
begin
  if not (ValidaUFCidade(UF, Cidade)) then
    raise Exception.Create(AMensagem);
end;
*)

class function MDFeUtil.FormatarChaveAcesso(AValue: String; Mascara: Boolean = False ): String;
begin
  AValue := DFeUtil.LimpaNumero(AValue);
  if Mascara
   then Result := copy(AValue,1,2)  + '-' + copy(AValue,3,2)  + '/' +
                  copy(AValue,5,2)  + '-' + copy(AValue,7,2)  + '.' +
                  copy(AValue,9,3)  + '.' + copy(AValue,12,3) + '/' +
                  copy(AValue,15,4) + '-' + copy(AValue,19,2) + '-' +
                  copy(AValue,21,2) + '-' + copy(AValue,23,3) + '-' +
                  copy(AValue,26,3) + '.' + copy(AValue,29,3) + '.' +
                  copy(AValue,32,3) + '-' + copy(AValue,35,1) + '-' +
                  copy(AValue,36,2) + '.' + copy(AValue,38,3) + '.' +
                  copy(AValue,41,3) + '-' + copy(AValue,44,1)
   else Result := copy(AValue,1,4)  + ' ' + copy(AValue,5,4)  + ' ' +
                  copy(AValue,9,4)  + ' ' + copy(AValue,13,4) + ' ' +
                  copy(AValue,17,4) + ' ' + copy(AValue,21,4) + ' ' +
                  copy(AValue,25,4) + ' ' + copy(AValue,29,4) + ' ' +
                  copy(AValue,33,4) + ' ' + copy(AValue,37,4) + ' ' +
                  copy(AValue,41,4);
end;

(*
class function MDFeUtil.PathAplication: String;
begin
  Result := ExtractFilePath(Application.ExeName);
end;
*)

{$IFDEF ACBrMDFeOpenSSL}

function ValidaLibXML(const AXML: AnsiString;
  var AMsg: AnsiString; const APathSchemas: string = ''): Boolean;
var
  doc, schema_doc   : xmlDocPtr;
  parser_ctxt       : xmlSchemaParserCtxtPtr;
  schema            : xmlSchemaPtr;
  valid_ctxt        : xmlSchemaValidCtxtPtr;
  schemError        : xmlErrorPtr;
  schema_filename   : PChar;
  Tipo, I           : Integer;
begin
 Tipo := MDFeUtil.IdentificaTipoSchema(AXML, I);

 if not DirectoryExists(DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
                 PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas',
                 PathWithDelim(APathSchemas))) then
    raise Exception.Create('Diret�rio de Schemas n�o encontrado'+sLineBreak+
                           DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
                           PathWithDelim(ExtractFileDir(application.ExeName))+
                           'Schemas',PathWithDelim(APathSchemas)));

 case Tipo of
  1: begin
      if DFeUtil.EstaVazio(APathSchemas)
       then schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\MDFe_v' + MDFeEnviMDFe + '.xsd')
       else schema_filename := pchar(PathWithDelim(APathSchemas)+'MDFe_v' + MDFeEnviMDFe + '.xsd');
     end;
  2,3:
     begin
      if DFeUtil.EstaVazio(APathSchemas) then
        schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\eventoMDFe_v' + MDFeEventoMDFe + '.xsd')
       else
        schema_filename := pchar(PathWithDelim(APathSchemas)+'eventoMDFe_v' + MDFeEventoMDFe + '.xsd');
     end;
 end;

  doc         := nil;
  schema_doc  := nil;
  parser_ctxt := nil;
  schema      := nil;
  valid_ctxt  := nil;
  doc         := xmlParseDoc(PAnsiChar(Axml));

  if ((doc = nil) or (xmlDocGetRootElement(doc) = nil)) then
  begin
    AMsg := 'Erro: unable to parse';
    Result := False;
    exit;
  end;

  schema_doc := xmlReadFile(PAnsiChar(schema_filename), nil, XML_DETECT_IDS);
  //  the schema cannot be loaded or is not well-formed
  if (schema_doc = nil) then
  begin
    AMsg := 'Erro: Schema n�o pode ser carregado ou est� corrompido';
    Result := False;
    exit;
  end;

  parser_ctxt := xmlSchemaNewDocParserCtxt(schema_doc);
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
    AMsg := IntToStr(schemError^.code) + ' - ' + schemError^.message;
    Result := False;
    exit;
  end;

  xmlSchemaFreeValidCtxt(valid_ctxt);
  xmlSchemaFree(schema);
  xmlSchemaFreeParserCtxt(parser_ctxt);
  xmlFreeDoc(schema_doc);
  Result := True;
end;
{$ELSE}

function ValidaMSXML(XML: AnsiString; out Msg: AnsiString;
 const APathSchemas: string = ''): Boolean;
var
  DOMDocument : IXMLDOMDocument2;
  ParseError  : IXMLDOMParseError;
  Schema      : XMLSchemaCache;
  Tipo, I     : Integer;
  schema_filename : String;
begin
  Tipo := MDFeUtil.IdentificaTipoSchema(XML, I);

  DOMDocument                  := CoDOMDocument50.Create;
  DOMDocument.async            := False;
  DOMDocument.resolveExternals := False;
  DOMDocument.validateOnParse  := True;
  DOMDocument.loadXML(XML);

  Schema := CoXMLSchemaCache50.Create;

 if not DirectoryExists(DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
                  PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas',
                  PathWithDelim(APathSchemas))) then
    raise Exception.Create('Diret�rio de Schemas n�o encontrado'+sLineBreak+
                            DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
                            PathWithDelim(ExtractFileDir(application.ExeName))+
                            'Schemas',PathWithDelim(APathSchemas)));

  case Tipo of
    1: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
                                           PathWithDelim(ExtractFileDir(application.ExeName)) + 'Schemas\',
                                           PathWithDelim(APathSchemas)) + 'MDFe_v' + MDFeEnviMDFe + '.xsd';
    2,3: schema_filename := DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
                                             PathWithDelim(ExtractFileDir(application.ExeName)) + 'Schemas\',
                                             PathWithDelim(APathSchemas)) + 'eventoMDFe_v' + MDFeEventoMDFe + '.xsd';
    else schema_filename := '';
  end;

 if not FilesExists(schema_filename) then
    raise Exception.Create('Arquivo '+schema_filename+' n�o encontrado');

  Schema.add( 'http://www.portalfiscal.inf.br/mdfe', schema_filename );

  DOMDocument.schemas := Schema;
  ParseError          := DOMDocument.validate;
  Result              := (ParseError.errorCode = 0);
  Msg                 := ParseError.reason;
  DOMDocument         := nil;
  ParseError          := nil;
  Schema              := nil;
end;

// Incluido por Italo em 16/03/2012
function ValidaModalMSXML(XML: AnsiString; out Msg: AnsiString;
 const APathSchemas: string = ''): Boolean;
var
  DOMDocument : IXMLDOMDocument2;
  ParseError  : IXMLDOMParseError;
  Schema      : XMLSchemaCache;
  Tipo        : Integer;
begin
  Tipo := 0;

  if pos( '<aereo>', XML ) <> 0
   then begin
    Tipo := 1;
    XML := SeparaDados( XML, 'aereo' );
    XML := '<aereo xmlns="http://www.portalfiscal.inf.br/mdfe">' +
            XML +
           '</aereo>';
   end;
  if pos( '<aquav>', XML) <> 0
   then begin
    Tipo := 2;
    XML := SeparaDados( XML, 'aquav' );
    XML := '<aquav xmlns="http://www.portalfiscal.inf.br/mdfe">' +
            XML +
           '</aquav>';
   end;
  if pos( '<duto>', XML) <> 0
   then begin
    Tipo := 3;
    XML := SeparaDados( XML, 'duto' );
    XML := '<duto xmlns="http://www.portalfiscal.inf.br/mdfe">' +
            XML +
           '</duto>';
   end;
  if pos( '<ferrov>', XML) <> 0
   then begin
    Tipo := 4;
    XML := SeparaDados( XML, 'ferrov' );
    XML := '<ferrov xmlns="http://www.portalfiscal.inf.br/mdfe">' +
            XML +
           '</ferrov>';
   end;
  if pos( '<rodo>', XML) <> 0
   then begin
    Tipo := 5;
    XML := SeparaDados( XML, 'rodo' );
    XML := '<rodo xmlns="http://www.portalfiscal.inf.br/mdfe">' +
            XML +
           '</rodo>';
   end;

  // Incluido por Italo em 02/10/2012
  // Eventos
  if pos( '<evEncMDFe>', XML) <> 0
   then begin
    Tipo := 6;
    XML := SeparaDados( XML, 'evEncMDFe' );
    XML := '<evEncMDFe xmlns="http://www.portalfiscal.inf.br/mdfe">' +
            XML +
           '</evEncMDFe>';
   end;
  if pos( '<evCancMDFe>', XML) <> 0
   then begin
    Tipo := 7;
    XML := SeparaDados( XML, 'evCancMDFe' );
    XML := '<evCancMDFe xmlns="http://www.portalfiscal.inf.br/mdfe">' +
            XML +
           '</evCancMDFe>';
   end;

  XML := '<?xml version="1.0" encoding="UTF-8" ?>' + XML;

  if Tipo = 0 then
    raise Exception.Create('Modal n�o encontrado no XML.');

  DOMDocument                  := CoDOMDocument50.Create;
  DOMDocument.async            := False;
  DOMDocument.resolveExternals := False;
  DOMDocument.validateOnParse  := True;
  DOMDocument.loadXML(XML);

  Schema := CoXMLSchemaCache50.Create;

  if not DirectoryExists(DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
                  PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas',
                  PathWithDelim(APathSchemas))) then
    raise Exception.Create('Diret�rio de Schemas n�o encontrado'+sLineBreak+
                            DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
                            PathWithDelim(ExtractFileDir(application.ExeName))+
                            'Schemas',PathWithDelim(APathSchemas)));

  Schema.remove('http://www.portalfiscal.inf.br/mdfe');

  case Tipo of
   1: begin
       Schema.add('http://www.portalfiscal.inf.br/mdfe',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'MDFeModalAereo_v' + MDFeModalAereo + '.xsd');
      end;
   2: begin
       Schema.add('http://www.portalfiscal.inf.br/mdfe',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'MDFeModalAquaviario_v' + MDFeModalAqua + '.xsd');
      end;
   3: begin
       Schema.add('http://www.portalfiscal.inf.br/mdfe',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'MDFeModalDutoviario_v' + MDFeModalDuto + '.xsd');
      end;
   4: begin
       Schema.add('http://www.portalfiscal.inf.br/mdfe',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'MDFeModalFerroviario_v' + MDFeModalFerro + '.xsd');
      end;
   5: begin
       Schema.add('http://www.portalfiscal.inf.br/mdfe',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'MDFeModalRodoviario_v' + MDFeModalRodo + '.xsd');
      end;
   // Incluido por Italo em 02/10/2012
   6: begin
       Schema.add('http://www.portalfiscal.inf.br/mdfe',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'evEncMDFe_v' + MDFeModalRodo + '.xsd');
      end;
   7: begin
       Schema.add('http://www.portalfiscal.inf.br/mdfe',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'evCancMDFe_v' + MDFeModalRodo + '.xsd');
      end;
  end;

  DOMDocument.schemas := Schema;
  ParseError          := DOMDocument.validate;
  Result              := (ParseError.errorCode = 0);
  Msg                 := ParseError.reason;
  DOMDocument         := nil;
  ParseError          := nil;
  Schema              := nil;
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
      raise Exception.Create('N�o foi poss�vel carregar o arquivo: '+XML);
  try
    xmldoc.setProperty('SelectionNamespaces', DSIGNS);
    xmldsig.signature := xmldoc.selectSingleNode('.//ds:Signature');

   if (xmldsig.signature = nil ) then
      raise Exception.Create('N�o foi poss�vel carregar o ler a assinatura: '+XML);

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

class function MDFeUtil.Valida(const AXML: AnsiString;
  var AMsg: AnsiString; const APathSchemas: string = ''): Boolean;
begin
{$IFDEF ACBrMDFeOpenSSL}
  Result := ValidaLibXML(AXML, AMsg, APathSchemas);
{$ELSE}
  Result := ValidaMSXML(AXML, AMsg, APathSchemas) and
            ValidaModalMSXML(AXML, AMsg, APathSchemas)
{$ENDIF}
end;

class function MDFeUtil.ValidaAssinatura(const AXML: AnsiString;
  var AMsg: AnsiString): Boolean;
begin
{$IFDEF ACBrMDFeOpenSSL}
  Result := False;
{$ELSE}
  Result := ValidaAssinaturaMSXML(AXML,AMsg);
{$ENDIF}
end;

{$IFDEF ACBrMDFeOpenSSL}

function AssinarLibXML(const AXML, ArqPFX, PFXSenha: AnsiString;
  out AXMLAssinado, FMensagem: AnsiString): Boolean;
var
  I, J, PosIni, PosFim : Integer;
  URI, AStr, XmlAss    : AnsiString;
  Tipo                 : Integer;  // 1 - MDFe 2 - Cancelamento 3 - Inutilizacao
begin
  AStr := AXML;

  //// Encontrando o URI ////
  Tipo := MDFeUtil.IdentificaTipoSchema(AStr,I);

  if I = 0 then
    raise Exception.Create('N�o encontrei inicio do URI: <infMDFe');
  I := DFeUtil.PosEx('Id=', AStr, I + 6);
  if I = 0 then
    raise Exception.Create('N�o encontrei inicio do URI: Id=');
  I := DFeUtil.PosEx('"', AStr, I + 2);
  if I = 0 then
    raise Exception.Create('N�o encontrei inicio do URI: aspas inicial');
  J := DFeUtil.PosEx('"', AStr, I + 1);
  if J = 0 then
    raise Exception.Create('N�o encontrei inicio do URI: aspas final');

  URI := copy(AStr, I + 1, J - I - 1);

  //// Adicionando Cabe�alho DTD, necess�rio para xmlsec encontrar o ID ////
  I := pos('?>', AStr);

  case Tipo of
    1: AStr := Copy(AStr,1,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+1,I)))) + cDTD +
               Copy(AStr,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+2,I))),Length(AStr));
    2,3: AStr := Copy(AStr,1,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+1,I)))) + cDTDEven +
                 Copy(AStr,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+2,I))),Length(AStr));
    else AStr := '';
  end;

  case Tipo of
    1:
    begin
      I := pos('</MDFe>',AStr);
      if I = 0 then
        raise Exception.Create('N�o encontrei final do XML: </MDFe>');
    end;
    2,3:
    begin
      // Alterado por Italo em 02/10/2012
      I := pos('</eventoMDFe>',AStr);
      if I = 0 then
        raise Exception.Create('N�o encontrei final do XML: </eventoMDFe>');
    end;
    else
      raise Exception.Create('Template de Tipo n�o implementado.');
  end;

  if pos('<Signature', AStr) > 0 then
    I := pos('<Signature', AStr);
  AStr := copy(AStr, 1, I - 1) +
    '<Signature xmlns="http://www.w3.org/2000/09/xmldsig#">' +
      '<SignedInfo>' +
        '<CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" />' +
        '<SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1" />' +
        '<Reference URI="#' + URI + '">' +
          '<Transforms>' +
            '<Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature" />' +
            '<Transform Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" />' +
          '</Transforms>' +
          '<DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1" />' +
          '<DigestValue></DigestValue>' +
        '</Reference>' +
      '</SignedInfo>' +
      '<SignatureValue></SignatureValue>' +
      '<KeyInfo>' +
        '<X509Data>' +
          '<X509Certificate></X509Certificate>' +
        '</X509Data>' +
      '</KeyInfo>' +
    '</Signature>';

  // Alterado por Italo em 02/10/2012
  case Tipo of
    1: AStr := AStr + '</MDFe>';
    2,3: AStr := AStr + '</eventoMDFe>';
    else AStr := '';
  end;

  XmlAss := MDFeUtil.sign_file(PAnsiChar(AStr), PChar(ArqPFX), PChar(PFXSenha));

  // Removendo quebras de linha //
  XmlAss := StringReplace(XmlAss, #10, '', [rfReplaceAll]);
  XmlAss := StringReplace(XmlAss, #13, '', [rfReplaceAll]);

  // Removendo DTD //
  case Tipo of
    1: XmlAss := StringReplace( XmlAss, cDTD, '', [] );
    2,3: XmlAss := StringReplace( XmlAss, cDTDEven, '', [] );
    else XmlAss := '';
  end;

  PosIni := Pos('<X509Certificate>', XmlAss) - 1;
  PosFim := DFeUtil.PosLast('<X509Certificate>', XmlAss);

  XmlAss := copy(XmlAss, 1, PosIni) + copy(XmlAss, PosFim, length(XmlAss));

  AXMLAssinado := XmlAss;

  Result := True;
end;
{$ELSE}

function AssinarMSXML(XML: AnsiString; Certificado: ICertificate2; out XMLAssinado: AnsiString): Boolean;
var
  I, J, PosIni, PosFim: Integer;
  URI               : string;
  Tipo              : Integer;

  xmlHeaderAntes, xmlHeaderDepois: AnsiString;
  xmldoc            : IXMLDOMDocument3;
  xmldsig           : IXMLDigitalSignature;
  dsigKey           : IXMLDSigKey;
  signedKey         : IXMLDSigKey;
begin
  if Pos('<Signature', XML) <= 0 then
  begin
    Tipo := MDFeUtil.IdentificaTipoSchema(XML, I);

    I := DFeUtil.PosEx('Id=', XML, 6);
    if I = 0 then
      raise Exception.Create('N�o encontrei inicio do URI: Id=');
    I := DFeUtil.PosEx('"', XML, I + 2);
    if I = 0 then
      raise Exception.Create('N�o encontrei inicio do URI: aspas inicial');
    J := DFeUtil.PosEx('"', XML, I + 1);
    if J = 0 then
      raise Exception.Create('N�o encontrei inicio do URI: aspas final');

    URI := copy(XML, I + 1, J - I - 1);

    // Alterado por Italo em 02/10/2012
    case Tipo of
      1: XML := copy(XML,1,pos('</MDFe>',XML)-1);
      2,3: XML := copy(XML,1,pos('</eventoMDFe>',XML)-1);
      else XML := '';
    end;

    XML := XML + '<Signature xmlns="http://www.w3.org/2000/09/xmldsig#"><SignedInfo><CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" /><SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1" />';
    XML := XML + '<Reference URI="#' + URI + '">';
    XML := XML + '<Transforms><Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature" /><Transform Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315" /></Transforms><DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1" />';
    XML := XML + '<DigestValue></DigestValue></Reference></SignedInfo><SignatureValue></SignatureValue><KeyInfo></KeyInfo></Signature>';

    // Alterado por Italo em 02/10/2012
    case Tipo of
      1: XML := XML + '</MDFe>';
      2,3: XML := XML + '</eventoMDFe>';
      else XML := '';
    end;
  end;

  // Lendo Header antes de assinar //
  xmlHeaderAntes := '';
  I := pos('?>', XML);
  if I > 0 then
    xmlHeaderAntes := copy(XML, 1, I + 1);

  xmldoc := CoDOMDocument50.Create;

  xmldoc.async := False;
  xmldoc.validateOnParse := False;
  xmldoc.preserveWhiteSpace := True;

  xmldsig := CoMXDigitalSignature50.Create;

  if (not xmldoc.loadXML(XML)) then
    raise Exception.Create('N�o foi poss�vel carregar o arquivo: ' + XML);

  xmldoc.setProperty('SelectionNamespaces', DSIGNS);

  xmldsig.signature := xmldoc.selectSingleNode('.//ds:Signature');

  if (xmldsig.signature = nil) then
    raise Exception.Create('Falha ao setar assinatura.');

  if (xmldsig.signature = nil) then
    raise Exception.Create('� preciso carregar o template antes de assinar.');

   if NumCertCarregado <> Certificado.SerialNumber then
      CertStoreMem := nil;

  if CertStoreMem = nil then
  begin
    CertStore := CoStore.Create;
    CertStore.Open(CAPICOM_CURRENT_USER_STORE, 'My', CAPICOM_STORE_OPEN_MAXIMUM_ALLOWED);

    CertStoreMem := CoStore.Create;
    CertStoreMem.Open(CAPICOM_MEMORY_STORE, 'Memoria', CAPICOM_STORE_OPEN_MAXIMUM_ALLOWED);

    Certs := CertStore.Certificates as ICertificates2;
    for i := 1 to Certs.Count do
    begin
      Cert := IInterface(Certs.Item[i]) as ICertificate2;
      if Cert.SerialNumber = Certificado.SerialNumber then
       begin
         CertStoreMem.Add(Cert);
         NumCertCarregado := Certificado.SerialNumber;
       end;
    end;
  end;

  OleCheck(IDispatch(Certificado.PrivateKey).QueryInterface(IPrivateKey, PrivateKey));
  xmldsig.store := CertStoreMem;

  dsigKey := xmldsig.createKeyFromCSP(PrivateKey.ProviderType, PrivateKey.ProviderName, PrivateKey.ContainerName, 0);
  if (dsigKey = nil) then
    raise Exception.Create('Erro ao criar a chave do CSP.');

  signedKey := xmldsig.sign(dsigKey, $00000002);
  if (signedKey <> nil) then
  begin
    XMLAssinado := xmldoc.xml;
    XMLAssinado := StringReplace(XMLAssinado, #10, '', [rfReplaceAll]);
    XMLAssinado := StringReplace(XMLAssinado, #13, '', [rfReplaceAll]);
    PosIni := Pos('<SignatureValue>', XMLAssinado) + length('<SignatureValue>');
    XMLAssinado := copy(XMLAssinado, 1, PosIni - 1) + StringReplace(copy(XMLAssinado, PosIni, length(XMLAssinado)), ' ', '', [rfReplaceAll]);
    PosIni := Pos('<X509Certificate>', XMLAssinado) - 1;
    PosFim := DFeUtil.PosLast('<X509Certificate>', XMLAssinado);

    XMLAssinado := copy(XMLAssinado, 1, PosIni) + copy(XMLAssinado, PosFim, length(XMLAssinado));
  end
  else
    raise Exception.Create('Assinatura Falhou.');

  if xmlHeaderAntes <> '' then
  begin
    I := pos('?>', XMLAssinado);
    if I > 0 then
    begin
      xmlHeaderDepois := copy(XMLAssinado, 1, I + 1);
      if xmlHeaderAntes <> xmlHeaderDepois then
        XMLAssinado := StuffString(XMLAssinado, 1, length(xmlHeaderDepois), xmlHeaderAntes);
    end
    else
      XMLAssinado := xmlHeaderAntes + XMLAssinado;
  end;

  dsigKey := nil;
  signedKey := nil;
  xmldoc := nil;
  xmldsig := nil;

  Result := True;
end;
{$ENDIF}

{$IFDEF ACBrMDFeOpenSSL}

class function MDFeUtil.sign_file(const Axml: PAnsiChar; const key_file: PChar; const senha: PChar): AnsiString;
var
  doc               : xmlDocPtr;
  node              : xmlNodePtr;
  dsigCtx           : xmlSecDSigCtxPtr;
  buffer            : PChar;
  bufSize           : integer;
label done;
begin
  doc := nil;
  node := nil;
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
    dsigCtx^.signKey := xmlSecCryptoAppKeyLoad(PAnsiChar(key_file), xmlSecKeyDataFormatPkcs12, PAnsiChar(senha), nil, nil);
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
      result := buffer;
  finally
    { cleanup }
    if (dsigCtx <> nil) then
      xmlSecDSigCtxDestroy(dsigCtx);

    if (doc <> nil) then
      xmlFreeDoc(doc);
  end;
end;
{$ENDIF}

{$IFDEF ACBrMDFeOpenSSL}

class function MDFeUtil.Assinar(const AXML, ArqPFX, PFXSenha: AnsiString; out AXMLAssinado, FMensagem: AnsiString): Boolean;
{$ELSE}

class function MDFeUtil.Assinar(const AXML: AnsiString; Certificado: ICertificate2; out AXMLAssinado, FMensagem: AnsiString): Boolean;
{$ENDIF}
begin
{$IFDEF ACBrMDFeOpenSSL}
  Result := AssinarLibXML(AXML, ArqPFX, PFXSenha, AXMLAssinado, FMensagem);
{$ELSE}
  Result := AssinarMSXML(AXML, Certificado, AXMLAssinado);
{$ENDIF}
end;

class function MDFeUtil.UFtoCUF(UF : String): Integer;
var
  Codigo, i: Integer;
begin
  Codigo := -1;
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

class function MDFeUtil.GerarChaveContingencia(FMDFe:TMDFe): string;

   function GerarDigito_Contingencia(var Digito: integer; chave: string): boolean;
   var
     i, j: integer;
   const
     PESO = '43298765432987654329876543298765432';
   begin
     chave  := DFeUtil.LimpaNumero(chave);
     j      := 0;
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
   //UF
   (*
   if FMDFe.Dest.EnderDest.UF = 'EX'
    then wchave := '99' //exterior
    else wchave := copy(inttostr(FMDFe.Dest.EnderDest.cMun),1,2);

   //TIPO DE EMISSAO
   if FMDFe.Ide.tpEmis = teContingencia
    then wchave := wchave + '2'
    else if FMDFe.Ide.tpEmis = teFSDA
          then wchave := wchave + '5'
          else wchave := wchave + '0'; //esta valor caracteriza ERRO, valor tem q ser  2 ou 5

   //CNPJ OU CPF
   if (FMDFe.Dest.EnderDest.UF='EX')
    then wchave:=wchave+MDFeUtil.Poem_Zeros('0',14)
    else wchave:=wchave+MDFeUtil.Poem_Zeros(FMDFe.Dest.CNPJCPF,14);

   //VALOR DA CT-e
   wchave := wchave + MDFeUtil.Poem_Zeros(MDFeUtil.LimpaNumero(FloatToStrf(FMDFe.vPrest.vTPrest, ffFixed,18,2)),14);

   //DESTAQUE ICMS PROPRIO E ST
   wicms_p := '2';
   wicms_s := '2';

   // Checar esse trecho

   if (MDFeUtil.NaoEstaZerado(FMDFe.Imp.ICMS.ICMS00.vICMS))
    then wicms_p := '1';
   if (MDFeUtil.NaoEstaZerado(FMDFe.Imp.ICMS.ICMSOutraUF.vICMSOutraUF))
    then wicms_s := '1';

   wchave := wchave + wicms_p + wicms_s;

   //DIA DA EMISSAO
   decodedate(FMDFe.Ide.dhEmi, wa, wm, wd);
   wchave := wchave + MDFeUtil.Poem_Zeros(inttostr(wd), 2);

   //DIGITO VERIFICADOR
   GerarDigito_Contingencia(Digito, wchave);
   wchave := wchave + inttostr(digito);
*)
   //RETORNA A CHAVE DE CONTINGENCIA
   result:=wchave;
end;

class function MDFeUtil.FormatarChaveContingencia(AValue: String): String;
begin
  AValue := DFeUtil.LimpaNumero(AValue);
  Result := copy(AValue,1,4)  + ' ' + copy(AValue,5,4)  + ' ' +
            copy(AValue,9,4)  + ' ' + copy(AValue,13,4) + ' ' +
            copy(AValue,17,4) + ' ' + copy(AValue,21,4) + ' ' +
            copy(AValue,25,4) + ' ' + copy(AValue,29,4) + ' ' +
            copy(AValue,33,4);
end;

class function MDFeUtil.IdentificaTipoSchema(const AXML: AnsiString; var I: integer): integer;
var
 lTipoEvento: String;
begin
  I := pos('<infMDFe',AXML);
  Result := 1;
  if I = 0  then
   begin
     I := pos('<infEvento',AXML);
     if I > 0 then
      begin
       lTipoEvento := Trim(RetornarConteudoEntre(AXML,'<tpEvento>','</tpEvento>'));
       if lTipoEvento = '110111'
        then Result := 2 // Cancelamento
        else begin
         if lTipoEvento = '110112'
          then Result := 3 //Encerramento
          else Result := 4;
        end;
      end;
   end;
end;

(*
class function MDFeUtil.FormatarPlaca(AValue: string): string;
begin
 Result := Copy(AValue, 1, 3) + '-' + Copy(AValue, 4, 4);
end;
*)
end.

