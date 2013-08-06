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

unit pmdfeSignature;

interface

uses
  SysUtils, Classes,
  pcnConversao, pcnGerador;

type

  TSignature = class(TPersistent)
  private
    FGerador: TGerador;
    FURI: string;
    FDigestValue: string;
    FSignatureValue: string;
    FX509Certificate: string;
  public
    constructor Create;
    destructor Destroy; override;
    function GerarXML: boolean;
  published
    property Gerador: TGerador read FGerador write FGerador;
    property URI: string read FURI write FURI;
    property DigestValue: string read FDigestValue write FDigestValue;
    property SignatureValue: string read FSignatureValue write FSignatureValue;
    property X509Certificate: string read FX509Certificate write FX509Certificate;
  end;

implementation

{ TSignature }

constructor TSignature.Create;
begin
  FGerador := TGerador.Create;
end;

destructor TSignature.Destroy;
begin
  FGerador.Free;
  inherited;
end;

function TSignature.GerarXML: boolean;
begin
  FGerador.ArquivoFormatoXML := '';
  FGerador.Opcoes.TagVaziaNoFormatoResumido := false;
  FGerador.FIgnorarTagIdentacao := '|Reference URI|SignatureMethod|Transform Algorithm="http://www.w3.org/TR|/Transforms|/Reference|';

  Gerador.wGrupo('Signature xmlns="http://www.w3.org/2000/09/xmldsig#"', 'XS01');
  Gerador.wGrupo('SignedInfo', 'XS02');
  Gerador.wGrupo('CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/', 'XS03');
  Gerador.wGrupo('SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/', 'XS05');
  Gerador.wGrupo('Reference URI="#MDFe' + FURI + '"', 'XS07');
  Gerador.wGrupo('Transforms', 'XS10');
  Gerador.wGrupo('Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature"/', 'SX12');
  Gerador.wGrupo('Transform Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/', 'SX12');
  Gerador.wGrupo('/Transforms');
  Gerador.wGrupo('DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/', 'XS15');
  Gerador.wCampo(tcStr, 'XS17', 'DigestValue', 000, 999, 1, FDigestValue, DSC_DigestValue);
  Gerador.wGrupo('/Reference');
  Gerador.wGrupo('/SignedInfo');
  Gerador.wCampo(tcStr, 'XS18', 'SignatureValue', 000, 999, 1, FSignatureValue, DSC_SignatureValue);
  Gerador.wGrupo('KeyInfo', 'XS19');
  Gerador.wGrupo('X509Data', 'XS20');
  Gerador.wCampo(tcStr, 'XS21', 'X509Certificate', 000, 999, 1, FX509Certificate, DSC_X509Certificate);
  Gerador.wGrupo('/X509Data');
  Gerador.wGrupo('/KeyInfo');
  Gerador.wGrupo('/Signature');

  Result := (Gerador.ListaDeAlertas.Count = 0);
end;

end.

