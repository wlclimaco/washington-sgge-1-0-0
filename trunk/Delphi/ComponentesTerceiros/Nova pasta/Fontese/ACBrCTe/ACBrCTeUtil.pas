{******************************************************************************}
{ Projeto: Componente ACBrCTe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - CTe - http://www.CTe.fazenda.gov.br                             }
{                                                                              }
{ Direitos Autorais Reservados (c) 2008 Wiliam Zacarias da Silva Rosa          }
{                                       Wemerson Souto                         }
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
|*  - Criado Valida��o do XML da CTe baseado em c�digo passado por Daniel Sim�es
|* 07/08/2009 : Wiliam Zacarias da Silva Rosa
|*  - Adicionado URL do estado de MT
|* 08/03/2010 : Bruno - Rhythmus Informatica
|* - Function GetURL:
|*  Incluida instru��o 33: Result := CTeUtil.GetURLRS(AAmbiente, ALayOut); //RJ
|*  RJ usa os WebServices do RS
******************************************************************************}
{$I ACBr.inc}

unit ACBrCTeUtil;

interface

uses
{$IFNDEF ACBrCTeOpenSSL}
  ACBrCAPICOM_TLB, ACBrMSXML2_TLB, JwaWinCrypt,
{$ENDIF}
  Classes, Forms,
{$IFDEF FPC}
  LResources, Controls, Graphics, Dialogs,
{$ELSE}
  StrUtils,
{$ENDIF}
  ACBrCTeConfiguracoes, pcnConversao, pcteCTe, ACBrDFeUtil;

{$IFDEF ACBrCTeOpenSSL}
const
  cDTD     = '<!DOCTYPE test [<!ATTLIST infCte Id ID #IMPLIED>]>';
  cDTDCanc = '<!DOCTYPE test [<!ATTLIST infCanc Id ID #IMPLIED>]>';
  cDTDInut = '<!DOCTYPE test [<!ATTLIST infInut Id ID #IMPLIED>]>';
  cDTDEven = '<!DOCTYPE test [<!ATTLIST infEvento Id ID #IMPLIED>]>';
{$ELSE}
const
  DSIGNS = 'xmlns:ds="http://www.w3.org/2000/09/xmldsig#"';
{$ENDIF}
{$IFNDEF ACBrCTeOpenSSL}
var
  CertStore    : IStore3;
  CertStoreMem : IStore3;
  PrivateKey   : IPrivateKey;
  Certs        : ICertificates2;
  Cert         : ICertificate2;
  NumCertCarregado : String;
{$ENDIF}

type
  CTeUtil = class
  private
    // Estados Emissores pela Sefaz Virtual RS (Rio Grande do Sul):
    // AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, PA, PB, PE, PI, RJ, RN,
    // RO, RR, SC, SE, TO.
    class function GetURLSVRS(AAmbiente: Integer; ALayOut: TLayOut): WideString;

    // Incluido por Italo em 03/10/2012
    class function GetURLAC(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLAL(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLAP(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLAM(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLBA(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLCE(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLDF(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLES(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLGO(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLMA(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLPA(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLPB(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLPE(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLPI(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLRJ(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLRN(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLRO(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLRR(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLSC(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLSE(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLTO(AAmbiente: Integer; ALayOut: TLayOut): WideString;

    class function GetURLMG(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLRS(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLSP(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLMT(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLMS(AAmbiente: Integer; ALayOut: TLayOut): WideString;
    class function GetURLPR(AAmbiente: Integer; ALayOut: TLayOut): WideString;

  protected

  public
{$IFDEF ACBrCTeOpenSSL}
//    class function sign_file(const Axml: PAnsiChar; const key_file: PChar; const senha: PChar): AnsiString;
    class function sign_file(const Axml: PAnsiChar; const key_file: PAnsiChar; const senha: PAnsiChar): AnsiString;
    class function sign_memory(const Axml: PAnsiChar; const key_file: PAnsichar; const senha: PAnsiChar; Size: Cardinal; Ponteiro: Pointer): AnsiString;
    class procedure InitXmlSec;
    class procedure ShutDownXmlSec;
{$ENDIF}
    class function GetURL(const AUF, AAmbiente, FormaEmissao: Integer; ALayOut: TLayOut): WideString;
    class function Valida(const AXML: AnsiString; var AMsg: AnsiString; const APathSchemas: string = ''): Boolean;

    class function FormatarChaveAcesso(AValue : String; Mascara: Boolean = False ): String;
    class function FormatarNumCTe(const AValue: Integer): string;
    class function FormatarValor(mask: TpcteMask; const AValue: real): string;
    class function GerarChaveContingencia(FCTe:TCTe): String;
    class function FormatarChaveContingencia(AValue: String): String;
    // Incluido por Italo em 10/02/2012
    class function ValidaAssinatura(const AXML: AnsiString;  var AMsg: AnsiString): Boolean;

{$IFDEF ACBrCTeOpenSSL}
    class function Assinar(const AXML, ArqPFX, PFXSenha: AnsiString; out AXMLAssinado, FMensagem: AnsiString): Boolean;
{$ELSE}
    class function Assinar(const AXML: AnsiString; Certificado: ICertificate2; out AXMLAssinado, FMensagem: AnsiString): Boolean;
{$ENDIF}

    class function UFtoCUF(UF : String): Integer;
    // Incluido por Italo em 13/09/2012
    class function IdentificaTipoSchema(Const AXML: AnsiString; var I: Integer): integer;
  end;

implementation

uses
 {$IFDEF ACBrCTeOpenSSL}
  libxml2, libxmlsec, libxslt,
 {$ELSE}
  ComObj,
 {$ENDIF}
  Sysutils, Variants, ACBrUtil, ACBrConsts, pcnAuxiliar;

{ CTeUtil }
{$IFDEF ACBrCTeOpenSSL}

class procedure CTeUtil.InitXmlSec;
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

class procedure CTeUtil.ShutDownXmlSec;
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

class function CTeUtil.GetURL(const AUF, AAmbiente, FormaEmissao: Integer;
  ALayOut: TLayOut): WideString;
begin
  //  (AC,AL,AP,AM,BA,CE,DF,ES,GO,MA,MT,MS,MG,PA,PB,PR,PE,PI,RJ,RN,RS,RO,RR,SC,SP,SE,TO);
  //  (12,27,16,13,29,23,53,32,52,21,51,50,31,15,25,41,26,22,33,24,43,11,14,42,35,28,17);

 case FormaEmissao of
  1,2,5   : begin
             {
             case ALayOut of
              LayCTeEnvDPEC:      Result := CTeUtil.SeSenao(AAmbiente=1, 'https://www.nfe.fazenda.gov.br/SCERecepcaoRFB/SCERecepcaoRFB.asmx', 'https://hom.nfe.fazenda.gov.br/SCERecepcaoRFB/SCERecepcaoRFB.asmx');
              LayCTeConsultaDPEC: Result := CTeUtil.SeSenao(AAmbiente=1, 'https://www.nfe.fazenda.gov.br/SCEConsultaRFB/SCEConsultaRFB.asmx', 'https://hom.nfe.fazenda.gov.br/SCEConsultaRFB/SCEConsultaRFB.asmx');
             end;
             }
             case AUF of
              // Alterados por Italo em 03/10/2012
              12: Result := CTeUtil.GetURLAC(AAmbiente, ALayOut);               //AC - Acre
              27: Result := CTeUtil.GetURLAL(AAmbiente, ALayOut);               //AL - Alagoas
              16: Result := CTeUtil.GetURLAP(AAmbiente, ALayOut);               //AP - Amap�
              13: Result := CTeUtil.GetURLAM(AAmbiente, ALayOut);               //AM - Amazonas
              29: Result := CTeUtil.GetURLBA(AAmbiente, ALayOut);               //BA - Bahia
              23: Result := CTeUtil.GetURLCE(AAmbiente, ALayOut);               //CE - Cear�
              53: Result := CTeUtil.GetURLDF(AAmbiente, ALayOut);               //DF - Distrito Federal
              32: Result := CTeUtil.GetURLES(AAmbiente, ALayOut);               //ES - Espirito Santo
              52: Result := CTeUtil.GetURLGO(AAmbiente, ALayOut);               //GO - Goi�s
              21: Result := CTeUtil.GetURLMA(AAmbiente, ALayOut);               //MA - Maranh�o

              51: Result := CTeUtil.GetURLMT(AAmbiente, ALayOut);               //MT - Mato Grosso
                  // S� Homologacao
              50: Result := CTeUtil.GetURLMS(AAmbiente, ALayOut);               //MS - Mato Grosso do Sul
              31: Result := CTeUtil.GetURLMG(AAmbiente, ALayOut);               //MG - Minas Gerais
              // Alterados por Italo em 03/10/2012
              15: Result := CTeUtil.GetURLPA(AAmbiente, ALayOut);               //PA - Par�
              25: Result := CTeUtil.GetURLPB(AAmbiente, ALayOut);               //PB - Paraib�
              41: Result := CTeUtil.GetURLPR(AAmbiente, ALayOut);               //PR - Paran�
//              41: Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);             //PR - Paran�

              // Alterado por Italo em 07/03/2012
              // conforme informa��es postadas no f�rum por
              // Eliton.net e jek
              26: Result := CTeUtil.GetURLPE(AAmbiente, ALayOut);               //PE - Pernambuco
//              26: Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);             //PE - Pernambuco

              // Alterados por Italo em 03/10/2012
              22: Result := CTeUtil.GetURLPI(AAmbiente, ALayOut);               //PI - Piau�
              33: Result := CTeUtil.GetURLRJ(AAmbiente, ALayOut);               //RJ - Rio de Janeiro
              24: Result := CTeUtil.GetURLRN(AAmbiente, ALayOut);               //RN - Rio Grande do Norte

              43: Result := CTeUtil.GetURLRS(AAmbiente, ALayOut);               //RS - Rio Grande do Sul
              // Alterados por Italo em 03/10/2012
              11: Result := CTeUtil.GetURLRO(AAmbiente, ALayOut);               //RO - Rond�nia
              14: Result := CTeUtil.GetURLRR(AAmbiente, ALayOut);               //RR - Roraima
              42: Result := CTeUtil.GetURLSC(AAmbiente, ALayOut);               //SC - Santa Catarina

              35: Result := CTeUtil.GetURLSP(AAmbiente, ALayOut);               //SP - S�o Paulo
              // Alterados por Italo em 03/10/2012
              28: Result := CTeUtil.GetURLSE(AAmbiente, ALayOut);               //SE - Sergipe
              17: Result := CTeUtil.GetURLTO(AAmbiente, ALayOut);               //TO - Tocantins
             end;
            end;
        3 : begin
             {
             case ALayOut of
              LayCTeRecepcao      : Result := CTeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/NfeRecepcao/NfeRecepcao.asmx'          , 'https://hom.nfe.fazenda.gov.br/SCAN/nferecepcao/NfeRecepcao.asmx');
              LayCTeRetRecepcao   : Result := CTeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/NfeRetRecepcao/NfeRetRecepcao.asmx'    , 'https://hom.nfe.fazenda.gov.br/SCAN/nferetrecepcao/NfeRetRecepcao.asmx');
              LayCTeCancelamento  : Result := CTeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/NfeCancelamento/NfeCancelamento.asmx'  , 'https://hom.nfe.fazenda.gov.br/SCAN/nfecancelamento/NfeCancelamento.asmx');
              LayCTeInutilizacao  : Result := CTeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/NfeInutilizacao/NfeInutilizacao.asmx'  , 'https://hom.nfe.fazenda.gov.br/SCAN/nfeinutilizacao/NfeInutilizacao.asmx');
              LayCTeConsultaCT    : Result := CTeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/NfeConsulta/NfeConsulta.asmx'          , 'https://hom.nfe.fazenda.gov.br/SCAN/nfeconsulta/NfeConsulta.asmx');
              LayCTeStatusServico : Result := CTeUtil.SeSenao(AAmbiente=1, 'https://www.scan.fazenda.gov.br/NfeStatusServico/NfeStatusServico.asmx', 'https://hom.nfe.fazenda.gov.br/SCAN/nfestatusservico/NfeStatusServico.asmx');
             end;
             }
            end;
        4 : begin
             case ALayOut of
              LayCTeEvento : begin
                               case AUF of
                                11, // Rond�nia
                                12, // Acre
                                13, // Amazonas
                                14, // Roraima
                                15, // Par�
                                16, // Amap�
                                17, // Tocantins
                                21, // Maranh�o
                                22, // Piau�
                                23, // Cear�
                                24, // Rio Grande do Norte
                                25, // Paraib�
                                27, // Alagoas
                                28, // Sergipe
                                29, // Bahia
                                31, // Minas Gerais
                                32, // Espirito Santo
                                33, // Rio de Janeiro
                                41, // Paran�
                                42, // Santa Catarina
                                43, // Rio Grande do Sul
                                52, // Goi�s
                                53: // Distrito Federal
                                    Result := DFeUtil.SeSenao(AAmbiente=1, 'https://nfe.fazenda.sp.gov.br/cteWEB/services/CteRecepcaoEvento.asmx', 'https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/CteRecepcaoEvento.asmx');
                                26, // Pernanbuco
                                35, // S�o Paulo
                                50, // Mato Grosso do Sul
                                51: // Mato Grosso
                                    // Alterado por Italo em 23/04/2013 conforme NT2013/003
                                    Result := DFeUtil.SeSenao(AAmbiente=1, 'https://cte.sefaz.rs.gov.br/ws/CteRecepcaoEvento/CteRecepcaoEvento.asmx', 'https://homologacao.cte.sefaz.rs.gov.br/ws/CteRecepcaoEvento/CteRecepcaoEvento.asmx');
                               end;
                             end;
             end;
            end;
        7 : begin // SVC-RS
             case ALayOut of
               LayCTeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/CTeRecepcao/CTeRecepcao.asmx'          , 'https://homologacao.cte.sefazvirtual.rs.gov.br/ws/CTeRecepcao/CTeRecepcao.asmx');
               LayCTeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/CTeRetRecepcao/CTeRetRecepcao.asmx'    , 'https://homologacao.cte.sefazvirtual.rs.gov.br/ws/CTeRetRecepcao/CTeRetRecepcao.asmx');
               LayCTeCancelamento:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/CTeCancelamento/CTeCancelamento.asmx'  , 'https://homologacao.cte.sefazvirtual.rs.gov.br/ws/CTeCancelamento/CTeCancelamento.asmx');
               LayCTeConsultaCT:    Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/CTeConsulta/CTeConsulta.asmx'          , 'https://homologacao.cte.sefazvirtual.rs.gov.br/ws/CTeConsulta/CTeConsulta.asmx');
               LayCTeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/CTeStatusServico/CTeStatusServico.asmx', 'https://homologacao.cte.sefazvirtual.rs.gov.br/ws/CTeStatusServico/CTeStatusServico.asmx');
             end;
            end;
        8 : begin // SVC-SP
             case ALayOut of
               LayCTeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.sp.gov.br/cteWEB/services/CteRecepcao.asmx'     , 'https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/CteRecepcao.asmx');
               LayCTeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.sp.gov.br/cteWEB/services/CteRetRecepcao.asmx'  , 'https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/CteRetRecepcao.asmx');
               LayCTeCancelamento:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.sp.gov.br/cteWEB/services/CteCancelamento.asmx' , 'https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/CteCancelamento.asmx');
               LayCTeConsultaCT:    Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.sp.gov.br/cteWEB/services/CteConsulta.asmx'     , 'https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/CteConsulta.asmx');
               LayCTeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.sp.gov.br/cteWEB/services/CteStatusServico.asmx', 'https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/CteStatusServico.asmx');
             end;
            end;

 end;
 if Result = '' then
     raise Exception.Create('URL n�o dispon�vel para o Estado solicitado.');
end;

class function CTeUtil.GetURLSVRS(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayCTeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/cterecepcao/CteRecepcao.asmx'          , 'https://homologacao.cte.sefaz.rs.gov.br/ws/cterecepcao/CteRecepcao.asmx');
    LayCTeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/cteretrecepcao/CteRetRecepcao.asmx'    , 'https://homologacao.cte.sefaz.rs.gov.br/ws/cteretrecepcao/CteRetRecepcao.asmx');
    LayCTeCancelamento:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/ctecancelamento/ctecancelamento.asmx'  , 'https://homologacao.cte.sefaz.rs.gov.br/ws/ctecancelamento/ctecancelamento.asmx');
    LayCTeInutilizacao:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/cteinutilizacao/cteinutilizacao.asmx'  , 'https://homologacao.cte.sefaz.rs.gov.br/ws/cteinutilizacao/cteinutilizacao.asmx');
    LayCTeConsultaCT:    Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/cteconsulta/cteconsulta.asmx'          , 'https://homologacao.cte.sefaz.rs.gov.br/ws/cteconsulta/cteconsulta.asmx');
    LayCTeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/ctestatusservico/ctestatusservico.asmx', 'https://homologacao.cte.sefaz.rs.gov.br/ws/ctestatusservico/ctestatusservico.asmx');
    LayCTeCadastro:      Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLAC(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLAL(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLAP(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLAM(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.sefaz.am.gov.br/services2/services/cadconsultacadastro2', 'https://homnfe.sefaz.am.gov.br/services2/services/cadconsultacadastro2');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLBA(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.sefaz.ba.gov.br/webservices/nfenw/CadConsultaCadastro2.asmx', 'https://hnfe.sefaz.ba.gov.br/webservices/nfenw/CadConsultaCadastro2.asmx');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLCE(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.sefaz.ce.gov.br/nfe2/services/CadConsultaCadastro2', 'https://nfeh.sefaz.ce.gov.br/nfe2/services/CadConsultaCadastro2');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLDF(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLES(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLGO(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.sefaz.go.gov.br/nfe/services/v2/CadConsultaCadastro2', 'https://homolog.sefaz.go.gov.br/nfe/services/v2/CadConsultaCadastro2');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLMA(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLPA(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLPB(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLPE(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
//  Result := DFeUtil.GetURLSP(AAmbiente, ALayOut);

  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.sefaz.pe.gov.br/nfe-service/services/CadConsultaCadastro2', '');
   else Result := CTeUtil.GetURLSP(AAmbiente, ALayOut);
  end;

end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLPI(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLRJ(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLRN(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLRO(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLRR(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLSC(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLSE(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;

// Incluido por Italo em 03/10/2012
class function CTeUtil.GetURLTO(AAmbiente: Integer; ALayOut: TLayOut): WideString;
begin
  case ALayOut of
   LayCTeCadastro: Result := DFeUtil.SeSenao(AAmbiente = 1, '', '');
   else Result := CTeUtil.GetURLSVRS(AAmbiente, ALayOut);
  end;
end;


class function CTeUtil.GetURLMG(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayCTeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.mg.gov.br/cte/services/CteRecepcao'     , 'https://hcte.fazenda.mg.gov.br/cte/services/CteRecepcao'); //?WSDL
    LayCTeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.mg.gov.br/cte/services/CteRetRecepcao'  , 'https://hcte.fazenda.mg.gov.br/cte/services/CteRetRecepcao'); //?WSDL
    LayCTeCancelamento:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.mg.gov.br/cte/services/CteCancelamento' , 'https://hcte.fazenda.mg.gov.br/cte/services/CteCancelamento');
    LayCTeInutilizacao:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.mg.gov.br/cte/services/CteInutilizacao' , 'https://hcte.fazenda.mg.gov.br/cte/services/CteInutilizacao');
    LayCTeConsultaCT:    Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.mg.gov.br/cte/services/CteConsulta'     , 'https://hcte.fazenda.mg.gov.br/cte/services/CteConsulta');
    LayCTeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.mg.gov.br/cte/services/CteStatusServico', 'https://hcte.fazenda.mg.gov.br/cte/services/CteStatusServico');
    // Incluido por Italo em 03/10/2012 conforme sugest�o de Moacir
    LayCTeCadastro:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.mg.gov.br/nfe2/services/cadconsultacadastro2', 'https://hnfe.fazenda.mg.gov.br/nfe2/services/cadconsultacadastro2');
//    LayCTeCadastro: Result      := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.mg.gov.br/cte/services/cadConsultaCadastro', 'https://hcte.fazenda.mg.gov.br/cte/services/cadConsultaCadastro');
  end;
end;

class function CTeUtil.GetURLRS(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayCTeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/cterecepcao/CteRecepcao.asmx'          , 'https://homologacao.cte.sefaz.rs.gov.br/ws/cterecepcao/CteRecepcao.asmx');
    LayCTeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/cteretrecepcao/cteRetRecepcao.asmx'    , 'https://homologacao.cte.sefaz.rs.gov.br/ws/cteretrecepcao/cteRetRecepcao.asmx'); //CteRetRecepcao.asmx
    LayCTeCancelamento:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/ctecancelamento/ctecancelamento.asmx'  , 'https://homologacao.cte.sefaz.rs.gov.br/ws/ctecancelamento/ctecancelamento.asmx');
    LayCTeInutilizacao:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/cteinutilizacao/cteinutilizacao.asmx'  , 'https://homologacao.cte.sefaz.rs.gov.br/ws/cteinutilizacao/cteinutilizacao.asmx');
    LayCTeConsultaCT:    Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/cteconsulta/CteConsulta.asmx'          , 'https://homologacao.cte.sefaz.rs.gov.br/ws/cteconsulta/CteConsulta.asmx');
    LayCTeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.rs.gov.br/ws/ctestatusservico/CteStatusServico.asmx', 'https://homologacao.cte.sefaz.rs.gov.br/ws/ctestatusservico/CteStatusServico.asmx');
    // Alterado por Italo em 14/03/2012 conforme sugest�o de Moacir
    LayCTeCadastro:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://sef.sefaz.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro2.asmx', 'https://sef.sefaz.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro2.asmx');
  end;
end;

class function CTeUtil.GetURLSP(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayCTeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.sp.gov.br/cteWEB/services/cteRecepcao.asmx'     , 'https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteRecepcao.asmx');
    LayCTeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.sp.gov.br/cteWEB/services/cteRetRecepcao.asmx'  , 'https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteRetRecepcao.asmx');
    LayCTeCancelamento:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.sp.gov.br/cteWEB/services/cteCancelamento.asmx' , 'https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteCancelamento.asmx');
    LayCTeInutilizacao:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.sp.gov.br/cteWEB/services/cteInutilizacao.asmx' , 'https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteInutilizacao.asmx');
    LayCTeConsultaCT:    Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.sp.gov.br/cteWEB/services/cteConsulta.asmx'     , 'https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteConsulta.asmx');
    LayCTeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.sp.gov.br/cteWEB/services/cteStatusServico.asmx', 'https://homologacao.nfe.fazenda.sp.gov.br/cteWEB/services/cteStatusServico.asmx');
    LayCTeCadastro:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.fazenda.sp.gov.br/nfeweb/services/cadconsultacadastro2.asmx', 'https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/cadconsultacadastro2.asmx');
  end;
end;

class function CTeUtil.GetURLMS(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayCTeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://producao.cte.ms.gov.br/cteWEB/CteRecepcao.asmx'        , 'https://homologacao.cte.ms.gov.br/cteWEB/CteRecepcao.asmx');
    LayCTeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://producao.cte.ms.gov.br/cteWEB/CteRetRecepcao.asmx'     , 'https://homologacao.cte.ms.gov.br/cteWEB/CteRetRecepcao.asmx');
    LayCTeCancelamento:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://producao.cte.ms.gov.br/cteWEB/CteCancelamento.asmx'    , 'https://homologacao.cte.ms.gov.br/cteWEB/CteCancelamento.asmx');
    LayCTeInutilizacao:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://producao.cte.ms.gov.br/cteWEB/CteInutilizacao.asmx'    , 'https://homologacao.cte.ms.gov.br/cteWEB/CteInutilizacao.asmx');
    LayCTeConsultaCT:    Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://producao.cte.ms.gov.br/cteWEB/CteConsulta.asmx'        , 'https://homologacao.cte.ms.gov.br/cteWEB/CteConsulta.asmx');
    LayCTeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://producao.cte.ms.gov.br/cteWEB/CteStatusServico.asmx'   , 'https://homologacao.cte.ms.gov.br/cteWEB/CteStatusServico.asmx');
    LayCTeCadastro:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://producao.cte.ms.gov.br/cteWEB/CadConsultaCadastro.asmx', 'https://homologacao.cte.ms.gov.br/cteWEB/CadConsultaCadastro.asmx');
  end;
end;

class function CTeUtil.GetURLMT(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayCTeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.mt.gov.br/ctews/services/CteRecepcao'     , 'https://homologacao.sefaz.mt.gov.br/ctews/services/CteRecepcao'); //?WSDL
    LayCTeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.mt.gov.br/ctews/services/CteRetRecepcao'  , 'https://homologacao.sefaz.mt.gov.br/ctews/services/CteRetRecepcao'); //?WSDL
    LayCTeCancelamento:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.mt.gov.br/ctews/services/CteCancelamento' , 'https://homologacao.sefaz.mt.gov.br/ctews/services/CteCancelamento');
    LayCTeInutilizacao:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.mt.gov.br/ctews/services/CteInutilizacao' , 'https://homologacao.sefaz.mt.gov.br/ctews/services/CteInutilizacao');
    LayCTeConsultaCT:    Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.mt.gov.br/ctews/services/CteConsulta'     , 'https://homologacao.sefaz.mt.gov.br/ctews/services/CteConsulta');
    LayCTeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.mt.gov.br/ctews/services/CteStatusServico', 'https://homologacao.sefaz.mt.gov.br/ctews/services/CteStatusServico'); //?WSDL
    // Incluido por Italo em 03/10/2012 conforme sugest�o de Moacir
    LayCTeCadastro:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe.sefaz.mt.gov.br/nfews/v2/services/CadConsultaCadastro2', 'https://homologacao.sefaz.mt.gov.br/nfews/v2/services/CadConsultaCadastro2');
//    LayCTeCadastro: Result      := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.mt.gov.br/ctews/services/cadConsultaCadastro', 'https://homologacao.sefaz.mt.gov.br/ctews/services/cadConsultaCadastro');
  end;
end;

// Adicionado por NCC - http://www.sped.fazenda.pr.gov.br/modules/conteudo/conteudo.php?conteudo=10
class function CTeUtil.GetURLPR(AAmbiente: Integer;
  ALayOut: TLayOut): WideString;
begin
  case ALayOut of
    LayCTeRecepcao:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.pr.gov.br/cte/CteRecepcao?wsdl'     , 'https://homologacao.cte.fazenda.pr.gov.br/cte/CteRecepcao?wsdl'); //?WSDL
    LayCTeRetRecepcao:   Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.pr.gov.br/cte/CteRetRecepcao?wsdl'  , 'https://homologacao.cte.fazenda.pr.gov.br/cte/CteRetRecepcao?wsdl'); //?WSDL
    LayCTeCancelamento:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.pr.gov.br/cte/CteCancelamento?wsdl' , 'https://homologacao.cte.fazenda.pr.gov.br/cte/CteCancelamento?wsdl');
    LayCTeInutilizacao:  Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.pr.gov.br/cte/CteInutilizacao?wsdl' , 'https://homologacao.cte.fazenda.pr.gov.br/cte/CteInutilizacao?wsdl');
    LayCTeConsultaCT:    Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.pr.gov.br/cte/CteConsulta?wsdl'     , 'https://homologacao.cte.fazenda.pr.gov.br/cte/CteConsulta?wsdl');
    LayCTeStatusServico: Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.fazenda.pr.gov.br/cte/CteStatusServico?wsdl', 'https://homologacao.cte.fazenda.pr.gov.br/cte/CteStatusServico?wsdl'); //?WSDL
    // Incluido por Italo em 03/10/2012 conforme sugest�o de Moacir
    LayCTeCadastro:      Result := DFeUtil.SeSenao(AAmbiente = 1, 'https://nfe2.fazenda.pr.gov.br/nfe/CadConsultaCadastro2', 'https://homologacao.nfe2.fazenda.pr.gov.br/nfe/CadConsultaCadastro2');
//    LayCTeCadastro: Result      := DFeUtil.SeSenao(AAmbiente = 1, 'https://cte.sefaz.mt.gov.br/ctews/services/cadConsultaCadastro', 'https://homologacao.sefaz.mt.gov.br/ctews/services/cadConsultaCadastro');
  end;
end;

class function CTeUtil.FormatarNumCTe(const AValue: Integer): string;
begin
  result := FormatFloat('000000000', AValue);
end;

class function CTeUtil.FormatarValor(mask: TpcteMask; const AValue: real): string;
begin
  result := FormatFloat(TpMaskToStrText(mask), AValue);
end;

class function CTeUtil.FormatarChaveAcesso(AValue: String; Mascara: Boolean = False ): String;
begin
  AValue := DFeUtil.LimpaNumero(AValue);
  // Alterado por Italo em 06/06/2012
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

{$IFDEF ACBrCTeOpenSSL}

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
  Tipo := CTeUtil.IdentificaTipoSchema(AXML, I);

  if not DirectoryExists(DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
                 PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas',
                 PathWithDelim(APathSchemas))) then
    raise Exception.Create('Diret�rio de Schemas n�o encontrado'+sLineBreak+
                           DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
                           PathWithDelim(ExtractFileDir(application.ExeName))+
                           'Schemas',PathWithDelim(APathSchemas)));

{$IFDEF PL_103}
 case Tipo of
  1: begin
      if DFeUtil.EstaVazio(APathSchemas) then
        schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\cte_v1.03.xsd')
       else
        schema_filename := pchar(PathWithDelim(APathSchemas)+'cte_v1.03.xsd');
     end;
  2: begin
      if DFeUtil.EstaVazio(APathSchemas) then
        schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\canccte_v1.03.xsd')
       else
        schema_filename := pchar(PathWithDelim(APathSchemas)+'canccte_v1.03.xsd');
     end;
  3: begin
      if DFeUtil.EstaVazio(APathSchemas) then
        schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\inutcte_v1.03.xsd')
       else
        schema_filename := pchar(PathWithDelim(APathSchemas)+'inutcte_v1.03.xsd');
     end;
  4: begin
      {
      if DFeUtil.EstaVazio(APathSchemas) then
        schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\envDPEC_v1.03.xsd')
       else
        schema_filename := pchar(PathWithDelim(APathSchemas)+'envDPEC_v1.03.xsd');
      }
     end;
 end;
{$ENDIF}
{$IFDEF PL_104}
 case Tipo of
  1: begin
      if DFeUtil.EstaVazio(APathSchemas) then
        schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\cte_v1.04.xsd')
       else
        schema_filename := pchar(PathWithDelim(APathSchemas)+'cte_v1.04.xsd');
     end;
  2: begin
      if DFeUtil.EstaVazio(APathSchemas) then
        schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\canccte_v1.04.xsd')
       else
        schema_filename := pchar(PathWithDelim(APathSchemas)+'canccte_v1.04.xsd');
     end;
  3: begin
      if DFeUtil.EstaVazio(APathSchemas) then
        schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\inutcte_v1.04.xsd')
       else
        schema_filename := pchar(PathWithDelim(APathSchemas)+'inutcte_v1.04.xsd');
     end;
  4: begin
      {
      if DFeUtil.EstaVazio(APathSchemas) then
        schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\envDPEC_v1.04.xsd')
       else
        schema_filename := pchar(PathWithDelim(APathSchemas)+'envDPEC_v1.04.xsd');
      }
     end;
  5..11:
     begin
      if DFeUtil.EstaVazio(APathSchemas) then
        schema_filename := pchar(PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\eventoCTe_v' + CTeEventoCTe + '.xsd')
       else
        schema_filename := pchar(PathWithDelim(APathSchemas)+'eventoCTe_v' + CTeEventoCTe + '.xsd');
     end;
 end;
{$ENDIF}

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

  schema_doc := xmlReadFile(Pansichar(AnsiToUtf8(schema_filename)), nil, XML_DETECT_IDS);

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
begin
  Tipo := CTeUtil.IdentificaTipoSchema(XML, I);

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

{$IFDEF PL_103}
  case Tipo of
   1: begin
       Schema.remove('http://www.portalfiscal.inf.br/cte');
       Schema.add('http://www.portalfiscal.inf.br/cte',
        DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
        PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
        PathWithDelim(APathSchemas))+'cte_v1.03.xsd')
      end;
   2: begin
       Schema.remove('http://www.portalfiscal.inf.br/cte');
       Schema.add('http://www.portalfiscal.inf.br/cte',
        DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
        PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
        PathWithDelim(APathSchemas))+'cancCte_v1.03.xsd')
      end;
   3: begin
       Schema.remove('http://www.portalfiscal.inf.br/cte');
       Schema.add('http://www.portalfiscal.inf.br/cte',
        DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
        PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
        PathWithDelim(APathSchemas))+'inutCte_v1.03.xsd')
      end;
   4: begin
       Schema.remove('http://www.portalfiscal.inf.br/cte');
       Schema.add( 'http://www.portalfiscal.inf.br/cte',
        DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
        PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
        PathWithDelim(APathSchemas))+'envDPEC_v1.03.xsd')
      end;
  end;
{$ENDIF}
{$IFDEF PL_104}
  case Tipo of
   1: begin
       Schema.remove('http://www.portalfiscal.inf.br/cte');

       Schema.add('http://www.portalfiscal.inf.br/cte',
        DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
        PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
        PathWithDelim(APathSchemas))+'cte_v1.04.xsd');
      end;
   2: begin
       Schema.remove('http://www.portalfiscal.inf.br/cte');
       Schema.add('http://www.portalfiscal.inf.br/cte',
        DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
        PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
        PathWithDelim(APathSchemas))+'cancCte_v1.04.xsd');
      end;
   3: begin
       Schema.remove('http://www.portalfiscal.inf.br/cte');
       Schema.add('http://www.portalfiscal.inf.br/cte',
        DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
        PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
        PathWithDelim(APathSchemas))+'inutCte_v1.04.xsd');
      end;
   4: begin
       Schema.remove('http://www.portalfiscal.inf.br/cte');
       Schema.add( 'http://www.portalfiscal.inf.br/cte',
        DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
        PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
        PathWithDelim(APathSchemas))+'envDPEC_v1.04.xsd');
      end;
    5..11:
      begin
       Schema.remove('http://www.portalfiscal.inf.br/cte');
       Schema.add( 'http://www.portalfiscal.inf.br/cte',
        DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
        PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
        PathWithDelim(APathSchemas))+'eventoCTe_v' + CTeEventoCTe + '.xsd');
      end;
  end;
{$ENDIF}

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
{$IFDEF PL_104}
var
  DOMDocument : IXMLDOMDocument2;
  ParseError  : IXMLDOMParseError;
  Schema      : XMLSchemaCache;
  Tipo        : Integer;
  AXML        : AnsiString;
{$ENDIF}
begin
{$IFDEF PL_103}
  Result := True;
{$ENDIF}

{$IFDEF PL_104}
  Tipo := 0;
  AXML := XML;

  // Incluido por Italo em 19/10/2012
  XML := SeparaDados( XML, 'infModal' );

  if pos( '<aereo>', XML ) <> 0
   then begin
    Tipo := 1;
    XML := SeparaDados( XML, 'aereo' );
    XML := '<aereo xmlns="http://www.portalfiscal.inf.br/cte">' +
            XML +
           '</aereo>';
   end;
  if pos( '<aquav>', XML) <> 0
   then begin
    Tipo := 2;
    XML := SeparaDados( XML, 'aquav' );
    XML := '<aquav xmlns="http://www.portalfiscal.inf.br/cte">' +
            XML +
           '</aquav>';
   end;
  if pos( '<duto>', XML) <> 0
   then begin
    Tipo := 3;
    XML := SeparaDados( XML, 'duto' );
    XML := '<duto xmlns="http://www.portalfiscal.inf.br/cte">' +
            XML +
           '</duto>';
   end;
  if pos( '<ferrov>', XML) <> 0
   then begin
    Tipo := 4;
    XML := SeparaDados( XML, 'ferrov' );
    XML := '<ferrov xmlns="http://www.portalfiscal.inf.br/cte">' +
            XML +
           '</ferrov>';
   end;
  if pos( '<rodo>', XML) <> 0
   then begin
    Tipo := 5;
    XML := SeparaDados( XML, 'rodo' );
    XML := '<rodo xmlns="http://www.portalfiscal.inf.br/cte">' +
            XML +
           '</rodo>';
   end;

  // Incluido por Italo em 27/11/2012
  // Eventos
  if Tipo = 0
   then begin
    XML := AXML;
    if pos( '<evEPECCTe>', XML) <> 0
     then begin
      Tipo := 6;
      XML := SeparaDados( XML, 'evEPECCTe' );
      XML := '<evEPECCTe xmlns="http://www.portalfiscal.inf.br/cte">' +
              XML +
             '</evEPECCTe>';
     end;
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

  Schema.remove('http://www.portalfiscal.inf.br/cte');

  case Tipo of
   1: begin
       Schema.add('http://www.portalfiscal.inf.br/cte',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'cteModalAereo_v1.04.xsd');
      end;
   2: begin
       Schema.add('http://www.portalfiscal.inf.br/cte',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'cteModalAquaviario_v1.04.xsd');
      end;
   3: begin
       Schema.add('http://www.portalfiscal.inf.br/cte',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'cteModalDutoviario_v1.04.xsd');
      end;
   4: begin
       Schema.add('http://www.portalfiscal.inf.br/cte',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'cteModalFerroviario_v1.04.xsd');
      end;
   5: begin
       Schema.add('http://www.portalfiscal.inf.br/cte',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'cteModalRodoviario_v1.04.xsd');
      end;
   6: begin
       Schema.add('http://www.portalfiscal.inf.br/cte',
          DFeUtil.SeSenao(DFeUtil.EstaVazio(APathSchemas),
          PathWithDelim(ExtractFileDir(application.ExeName))+'Schemas\',
          PathWithDelim(APathSchemas))+'evEPECCTe_v' + CTeEventoCTe + '.xsd');
      end;
  end;

  DOMDocument.schemas := Schema;
  ParseError          := DOMDocument.validate;
  Result              := (ParseError.errorCode = 0);
  Msg                 := ParseError.reason;
  DOMDocument         := nil;
  ParseError          := nil;
  Schema              := nil;
{$ENDIF}
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

class function CTeUtil.Valida(const AXML: AnsiString;
  var AMsg: AnsiString; const APathSchemas: string = ''): Boolean;
begin
{$IFDEF ACBrCTeOpenSSL}
  Result := ValidaLibXML(AXML, AMsg, APathSchemas);
{$ELSE}
  // Alterado por Italo em 27/11/2012
  if (pos('<infCTeNorm>', AXML) <> 0) or (pos('<infEvento', AXML) <> 0)
   then Result := ValidaMSXML(AXML, AMsg, APathSchemas) and
                  ValidaModalMSXML(AXML, AMsg, APathSchemas)
   else Result := ValidaMSXML(AXML, AMsg, APathSchemas);
{$ENDIF}
end;

class function CTeUtil.ValidaAssinatura(const AXML: AnsiString;
  var AMsg: AnsiString): Boolean;
begin
{$IFDEF ACBrCTeOpenSSL}
  Result := False;
{$ELSE}
  Result := ValidaAssinaturaMSXML(AXML,AMsg);
{$ENDIF}
end;

{$IFDEF ACBrCTeOpenSSL}

function AssinarLibXML(const AXML, ArqPFX, PFXSenha: AnsiString;
  out AXMLAssinado, FMensagem: AnsiString): Boolean;
var
  I, J, PosIni, PosFim : Integer;
  URI, AStr, XmlAss    : AnsiString;
  Tipo                 : Integer;  // 1 - CTe 2 - Cancelamento 3 - Inutilizacao
  Cert: TMemoryStream;
  Cert2: TStringStream;
begin
  AStr := AXML;

  //// Encontrando o URI ////
  // Alterado por Italo em 13/09/2012
  Tipo := CTeUtil.IdentificaTipoSchema(AStr, I);

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
   1: AStr := copy(AStr, 1, StrToInt(VarToStr(DFeUtil.SeSenao(I > 0, I + 1, I))))
         + cDTD + Copy(AStr, StrToInt(VarToStr(DFeUtil.SeSenao(I > 0, I + 2, I))),
          Length(AStr));
   2: AStr := copy(AStr, 1, StrToInt(VarToStr(DFeUtil.SeSenao(I > 0, I + 1, I))))
         + cDTDCanc + Copy(AStr, StrToInt(VarToStr(DFeUtil.SeSenao(I > 0, I + 2, I))),
          Length(AStr));
   3: AStr := copy(AStr, 1, StrToInt(VarToStr(DFeUtil.SeSenao(I > 0, I + 1, I))))
         + cDTDInut + Copy(AStr, StrToInt(VarToStr(DFeUtil.SeSenao(I > 0, I + 2, I))),
          Length(AStr));
   {
   4: AStr := copy(AStr,1,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+1,I))))
         + cDTDDpec + Copy(AStr,StrToInt(VarToStr(DFeUtil.SeSenao(I>0,I+2,I))),
          Length(AStr));
   }
   5..11: AStr := Copy(AStr, 1, StrToInt(VarToStr(DFeUtil.SeSenao(I > 0, I + 1, I))))
             + cDTDEven + Copy(AStr, StrToInt(VarToStr(DFeUtil.SeSenao(I > 0, I + 2, I))),
              Length(AStr));
   else AStr := '';
  end;

  //// Inserindo Template da Assinatura digital ////
  // Alterado por Italo em 13/09/2012
  case Tipo of
   1: begin
       I := pos('</CTe>', AStr);
       if I = 0 then
        raise Exception.Create('N�o encontrei final do XML: </CTe>');
      end;
   2: begin
       I := pos('</cancCTe>', AStr);
       if I = 0 then
        raise Exception.Create('N�o encontrei final do XML: </cancCTe>');
      end;
   3: begin
       I := pos('</inutCTe>', AStr);
       if I = 0 then
        raise Exception.Create('N�o encontrei final do XML: </inutCTe>');
      end;
   5..11:
      begin
       // Incluido por Italo em 27/11/2012
       I := pos('</eventoCTe>', AStr) ;
       if I = 0 then
        raise Exception.Create('N�o encontrei final do XML: </eventoCTe>') ;
      end;
   else
      raise Exception.Create('Template de Tipo n�o implementado.') ;
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

  // Alterado por Italo em 13/09/2012
  case Tipo of
    1: AStr := AStr + '</CTe>';
    2: AStr := AStr + '</cancCTe>';
    3: AStr := AStr + '</inutCTe>';
    5..11: AStr := AStr + '</eventoCTe>';
    else AStr := '';
  end;

//  XmlAss := CTeUtil.sign_file(PAnsiChar(AStr), PChar(ArqPFX), PChar(PFXSenha));

  // Alterado por Italo em 13/09/2012
  if FileExists(ArqPFX) then
    XmlAss := CTeUtil.sign_file(PAnsiChar(AStr), PAnsiChar(ArqPFX), PAnsiChar(PFXSenha))
  else
   begin
    Cert := TMemoryStream.Create;
    Cert2 := TStringStream.Create(ArqPFX);
    try
      Cert.LoadFromStream(Cert2);
      XmlAss := CTeUtil.sign_memory(PAnsiChar(AStr), PAnsiChar(ArqPFX), PAnsiChar(PFXSenha), Cert.Size, Cert.Memory) ;
    finally
      Cert2.Free;
      Cert.Free;
    end;
  end;

  // Removendo quebras de linha //
  XmlAss := StringReplace(XmlAss, #10, '', [rfReplaceAll]);
  XmlAss := StringReplace(XmlAss, #13, '', [rfReplaceAll]);

  // Removendo DTD //
  // Alterado por Italo em 13/09/2012
  case Tipo of
    1: XmlAss := StringReplace( XmlAss, cDTD, '', [] );
    2: XmlAss := StringReplace( XmlAss, cDTDCanc, '', [] );
    3: XmlAss := StringReplace( XmlAss, cDTDInut, '', [] );
    5..11: XmlAss := StringReplace( XmlAss, cDTDEven, '', [] );
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
    // Alterado por Italo em 13/09/2012
    Tipo := CTeUtil.IdentificaTipoSchema(XML,I);

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

    case Tipo of
      1: XML := copy(XML, 1, pos('</CTe>', XML) - 1);
      2: XML := copy(XML, 1, pos('</cancCTe>', XML) - 1);
      3: XML := copy(XML, 1, pos('</inutCTe>', XML) - 1);
      5..11: XML := copy(XML, 1, pos('</eventoCTe>', XML) - 1);
      else XML := '';
    end;

    XML := XML + '<Signature xmlns="http://www.w3.org/2000/09/xmldsig#">' +
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
                   '<KeyInfo></KeyInfo>' +
                 '</Signature>';

    case Tipo of
      1: XML := XML + '</CTe>';
      2: XML := XML + '</cancCTe>';
      3: XML := XML + '</inutCTe>';
      5..11: XML := XML + '</eventoCTe>';
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

{$IFDEF ACBrCTeOpenSSL}
class function CTeUtil.sign_file(const Axml: PAnsiChar; const key_file: PAnsiChar; const senha: PAnsiChar): AnsiString;
var
  doc     : xmlDocPtr;
  node    : xmlNodePtr;
  dsigCtx : xmlSecDSigCtxPtr;
  buffer  : PAnsiChar;
  bufSize : integer;
label
  done;
begin
  doc := nil;
  // node := nil;
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

//    dsigCtx^.signKey := xmlSecCryptoAppKeyLoad(PAnsiChar(key_file), xmlSecKeyDataFormatPkcs12, PAnsiChar(senha), nil, nil);
//    if (dsigCtx^.signKey = nil) then
//      raise Exception.Create('Error: failed to load private pem key from "' + key_file + '"');

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

class function CTeUtil.sign_memory(const Axml: PAnsiChar; const key_file: PAnsichar; const senha: PAnsiChar; Size: Cardinal; Ponteiro: Pointer): AnsiString;
var
  doc     : xmlDocPtr;
  node    : xmlNodePtr;
  dsigCtx : xmlSecDSigCtxPtr;
  buffer  : PAnsiChar;
  bufSize : integer;
label
 done;
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
{$ENDIF}

{$IFDEF ACBrCTeOpenSSL}
class function CTeUtil.Assinar(const AXML, ArqPFX, PFXSenha: AnsiString; out AXMLAssinado, FMensagem: AnsiString): Boolean;
{$ELSE}
class function CTeUtil.Assinar(const AXML: AnsiString; Certificado: ICertificate2; out AXMLAssinado, FMensagem: AnsiString): Boolean;
{$ENDIF}
begin
{$IFDEF ACBrCTeOpenSSL}
  Result := AssinarLibXML(AXML, ArqPFX, PFXSenha, AXMLAssinado, FMensagem);
{$ELSE}
  Result := AssinarMSXML(AXML, Certificado, AXMLAssinado);
{$ENDIF}
end;

class function CTeUtil.UFtoCUF(UF : String): Integer;
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

class function CTeUtil.GerarChaveContingencia(FCTe:TCTe): string;

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
   // Alterado por Italo em 29/10/2012
   // Conforme NT 2012/007
   //UF
   // TpcteTomador = ( tmRemetente, tmExpedidor, tmRecebedor, tmDestinatario, tmOutros);
   if FCTe.Ide.toma4.CNPJCPF<>''
    then begin
     if FCTe.Ide.toma4.enderToma.UF = 'EX'
      then wchave := '99' //exterior
      else wchave := copy(inttostr(FCTe.Ide.toma4.enderToma.cMun),1,2);
    end
    else begin
     case FCTe.Ide.toma03.Toma of
      tmRemetente: if FCTe.Rem.enderReme.UF = 'EX'
                    then wchave := '99' //exterior
                    else wchave := copy(inttostr(FCTe.Rem.enderReme.cMun),1,2);
      tmExpedidor: if FCTe.Exped.enderExped.UF = 'EX'
                    then wchave := '99' //exterior
                    else wchave := copy(inttostr(FCTe.Exped.enderExped.cMun),1,2);
      tmRecebedor: if FCTe.Receb.enderReceb.UF = 'EX'
                    then wchave := '99' //exterior
                    else wchave := copy(inttostr(FCTe.Receb.enderReceb.cMun),1,2);
      tmDestinatario: if FCTe.Dest.EnderDest.UF = 'EX'
                       then wchave := '99' //exterior
                       else wchave := copy(inttostr(FCTe.Dest.EnderDest.cMun),1,2);
     end;
    end;

   //TIPO DE EMISSAO
   if FCTe.Ide.tpEmis = teContingencia
    then wchave := wchave + '2'
    else if FCTe.Ide.tpEmis = teFSDA
          then wchave := wchave + '5'
          else wchave := wchave + '0'; //esta valor caracteriza ERRO, valor tem q ser  2 ou 5

   //CNPJ OU CPF
   if FCTe.Ide.toma4.CNPJCPF<>''
    then begin
     if FCTe.Ide.toma4.enderToma.UF = 'EX'
      then wchave:=wchave+DFeUtil.Poem_Zeros('0',14)
      else wchave:=wchave+DFeUtil.Poem_Zeros(FCTe.Ide.toma4.CNPJCPF,14);
    end
    else begin
     case FCTe.Ide.toma03.Toma of
      tmRemetente: if (FCTe.Rem.enderReme.UF='EX')
                    then wchave:=wchave+DFeUtil.Poem_Zeros('0',14)
                    else wchave:=wchave+DFeUtil.Poem_Zeros(FCTe.Rem.CNPJCPF,14);
      tmExpedidor: if (FCTe.Exped.enderExped.UF='EX')
                    then wchave:=wchave+DFeUtil.Poem_Zeros('0',14)
                    else wchave:=wchave+DFeUtil.Poem_Zeros(FCTe.Exped.CNPJCPF,14);
      tmRecebedor: if (FCTe.Receb.enderReceb.UF='EX')
                    then wchave:=wchave+DFeUtil.Poem_Zeros('0',14)
                    else wchave:=wchave+DFeUtil.Poem_Zeros(FCTe.Receb.CNPJCPF,14);
      tmDestinatario: if (FCTe.Dest.EnderDest.UF='EX')
                       then wchave:=wchave+DFeUtil.Poem_Zeros('0',14)
                       else wchave:=wchave+DFeUtil.Poem_Zeros(FCTe.Dest.CNPJCPF,14);
     end;
    end;

   //VALOR DA CT-e
   wchave := wchave + DFeUtil.Poem_Zeros(DFeUtil.LimpaNumero(FloatToStrf(FCTe.vPrest.vTPrest, ffFixed,18,2)),14);

   //DESTAQUE ICMS PROPRIO E ST
   wicms_p := '2';
   wicms_s := '2';

   // Checar esse trecho

{$IFDEF PL_103}
   if (DFeUtil.NaoEstaZerado(FCTe.Imp.ICMS.CST00.vICMS))
    then wicms_p := '1';
   if (DFeUtil.NaoEstaZerado(FCTe.Imp.ICMS.CST80.vICMS))
    then wicms_s := '1';
{$ENDIF}
{$IFDEF PL_104}
   if (DFeUtil.NaoEstaZerado(FCTe.Imp.ICMS.ICMS00.vICMS))
    then wicms_p := '1';
   if (DFeUtil.NaoEstaZerado(FCTe.Imp.ICMS.ICMSOutraUF.vICMSOutraUF))
    then wicms_s := '1';
{$ENDIF}

   wchave := wchave + wicms_p + wicms_s;

   //DIA DA EMISSAO
   decodedate(FCTe.Ide.dhEmi, wa, wm, wd);
   wchave := wchave + DFeUtil.Poem_Zeros(inttostr(wd), 2);

   //DIGITO VERIFICADOR
   GerarDigito_Contingencia(Digito, wchave);
   wchave := wchave + inttostr(digito);

   //RETORNA A CHAVE DE CONTINGENCIA
   result:=wchave;
end;

class function CTeUtil.FormatarChaveContingencia(AValue: String): String;
begin
  AValue := DFeUtil.LimpaNumero(AValue);
  Result := copy(AValue,1,4)  + ' ' + copy(AValue,5,4)  + ' ' +
            copy(AValue,9,4)  + ' ' + copy(AValue,13,4) + ' ' +
            copy(AValue,17,4) + ' ' + copy(AValue,21,4) + ' ' +
            copy(AValue,25,4) + ' ' + copy(AValue,29,4) + ' ' +
            copy(AValue,33,4) ;
end;

// Incluido por Italo em 13/09/2012
class function CTeUtil.IdentificaTipoSchema(const AXML: AnsiString; var I: integer): integer;
var
 lTipoEvento: String;
begin
  // Corrigido por Italo em 05/10/2012
  I := pos('<infCte',AXML) ;
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
            lTipoEvento := Trim(RetornarConteudoEntre(AXML, '<tpEvento>', '</tpEvento>'));
            if lTipoEvento = '110111' then // Cancelamento
              Result := 6
            else if lTipoEvento = '110113' then // EPEC
              Result := 7
            else if lTipoEvento = '210200' then // Manif. Destinatario: Confirma��o da Opera��o
              Result := 8
            else if lTipoEvento = '210210' then // Manif. Destinatario: Ci�ncia da Opera��o Realizada
              Result := 9
            else if lTipoEvento = '210220' then // Manif. Destinatario: Desconhecimento da Opera��o
              Result := 10
            else if lTipoEvento = '210240' then // Manif. Destinatario: Opera��o n�o Realizada
              Result := 11
            else
              Result := 5; // Carta de Corre��o Eletr�nica
          end
          else
            Result := 4; //DPEC
         end;
     end;
   end;
end;

end.

