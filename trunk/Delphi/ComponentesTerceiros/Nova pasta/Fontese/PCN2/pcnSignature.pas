////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//              PCN - Projeto Cooperar NFe                                    //
//                                                                            //
//   Descri��o: Classes para gera��o/leitura dos arquivos xml da NFe          //
//                                                                            //
//        site: www.projetocooperar.org/nfe                                   //
//       email: projetocooperar@zipmail.com.br                                //
//       forum: http://br.groups.yahoo.com/group/projeto_cooperar_nfe/        //
//     projeto: http://code.google.com/p/projetocooperar/                     //
//         svn: http://projetocooperar.googlecode.com/svn/trunk/              //
//                                                                            //
// Coordena��o: (c) 2009 - Paulo Casagrande                                   //
//                                                                            //
//      Equipe: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
//      Vers�o: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
//     Licen�a: GNU Lesser General Public License (GNU LGPL)                  //
//                                                                            //
//              - Este programa � software livre; voc� pode redistribu�-lo    //
//              e/ou modific�-lo sob os termos da Licen�a P�blica Geral GNU,  //
//              conforme publicada pela Free Software Foundation; tanto a     //
//              vers�o 2 da Licen�a como (a seu crit�rio) qualquer vers�o     //
//              mais nova.                                                    //
//                                                                            //
//              - Este programa � distribu�do na expectativa de ser �til,     //
//              mas SEM QUALQUER GARANTIA; sem mesmo a garantia impl�cita de  //
//              COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM       //
//              PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter   //
//              mais detalhes. Voc� deve ter recebido uma c�pia da Licen�a    //
//              P�blica Geral GNU junto com este programa; se n�o, escreva    //
//              para a Free Software Foundation, Inc., 59 Temple Place,       //
//              Suite 330, Boston, MA - 02111-1307, USA ou consulte a         //
//              licen�a oficial em http://www.gnu.org/licenses/gpl.txt        //
//                                                                            //
//    Nota (1): - Esta  licen�a  n�o  concede  o  direito  de  uso  do nome   //
//              "PCN  -  Projeto  Cooperar  NFe", n�o  podendo o mesmo ser    //
//              utilizado sem previa autoriza��o.                             //
//                                                                            //
//    Nota (2): - O uso integral (ou parcial) das units do projeto esta       //
//              condicionado a manuten��o deste cabe�alho junto ao c�digo     //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////

unit pcnSignature;

interface uses
  SysUtils, Classes,  pcnConversao, pcnGerador;

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
    function GerarXMLCTe: boolean;
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
  (**)Gerador.wGrupo('Signature xmlns="http://www.w3.org/2000/09/xmldsig#"', 'XS01');
  (****)Gerador.wGrupo('SignedInfo', 'XS02');
  (******)Gerador.wGrupo('CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/', 'XS03');
  (******)Gerador.wGrupo('SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/', 'XS05');
  (******)Gerador.wGrupo('Reference URI="#NFe' + FURI + '"', 'XS07');
  (********)Gerador.wGrupo('Transforms', 'XS10');
  (**********)Gerador.wGrupo('Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature"/', 'SX12');
  (**********)Gerador.wGrupo('Transform Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/', 'SX12');
  (********)Gerador.wGrupo('/Transforms');
  (********)Gerador.wGrupo('DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/', 'XS15');
  (********)Gerador.wCampo(tcStr, 'XS17', 'DigestValue', 000, 999, 1, FDigestValue, DSC_DigestValue);
  (******)Gerador.wGrupo('/Reference');
  (****)Gerador.wGrupo('/SignedInfo');
  (****)Gerador.wCampo(tcStr, 'XS18', 'SignatureValue', 000, 999, 1, FSignatureValue, DSC_SignatureValue);
  (****)Gerador.wGrupo('KeyInfo', 'XS19');
  (******)Gerador.wGrupo('X509Data', 'XS20');
  (********)Gerador.wCampo(tcStr, 'XS21', 'X509Certificate', 000, 999, 1, FX509Certificate, DSC_X509Certificate);
  (******)Gerador.wGrupo('/X509Data');
  (****)Gerador.wGrupo('/KeyInfo');
  (**)Gerador.wGrupo('/Signature');
  Result := (Gerador.ListaDeAlertas.Count = 0);
end;

// Fun��o criada especialmente para gerar os campos da assinatura do CT-e
// 15/06/2010 - por: Italo Jurisato Junior
function TSignature.GerarXMLCTe: boolean;
begin
  FGerador.ArquivoFormatoXML := '';
  FGerador.Opcoes.TagVaziaNoFormatoResumido := false;
  FGerador.FIgnorarTagIdentacao := '|Reference URI|SignatureMethod|Transform Algorithm="http://www.w3.org/TR|/Transforms|/Reference|';
  (**)Gerador.wGrupo('Signature xmlns="http://www.w3.org/2000/09/xmldsig#"', 'XS01');
  (****)Gerador.wGrupo('SignedInfo', 'XS02');
  (******)Gerador.wGrupo('CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/', 'XS03');
  (******)Gerador.wGrupo('SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/', 'XS05');
  (******)Gerador.wGrupo('Reference URI="#CTe' + FURI + '"', 'XS07');
  (********)Gerador.wGrupo('Transforms', 'XS10');
  (**********)Gerador.wGrupo('Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature"/', 'SX12');
  (**********)Gerador.wGrupo('Transform Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/', 'SX12');
  (********)Gerador.wGrupo('/Transforms');
  (********)Gerador.wGrupo('DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/', 'XS15');
  (********)Gerador.wCampo(tcStr, 'XS17', 'DigestValue', 000, 999, 1, FDigestValue, DSC_DigestValue);
  (******)Gerador.wGrupo('/Reference');
  (****)Gerador.wGrupo('/SignedInfo');
  (****)Gerador.wCampo(tcStr, 'XS18', 'SignatureValue', 000, 999, 1, FSignatureValue, DSC_SignatureValue);
  (****)Gerador.wGrupo('KeyInfo', 'XS19');
  (******)Gerador.wGrupo('X509Data', 'XS20');
  (********)Gerador.wCampo(tcStr, 'XS21', 'X509Certificate', 000, 999, 1, FX509Certificate, DSC_X509Certificate);
  (******)Gerador.wGrupo('/X509Data');
  (****)Gerador.wGrupo('/KeyInfo');
  (**)Gerador.wGrupo('/Signature');
  Result := (Gerador.ListaDeAlertas.Count = 0);
end;

end.

