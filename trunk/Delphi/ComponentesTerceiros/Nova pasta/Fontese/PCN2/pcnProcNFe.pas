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

unit pcnProcNFe;

interface uses
  SysUtils, Classes, pcnAuxiliar, pcnConversao, pcnGerador;

type

  TPcnPadraoNomeProcNFe = (tpnPublico, tpnPrivado);

  TProcNFe = class(TPersistent)
  private
    FGerador: TGerador;
    FSchema: TpcnSchema;
    FPathNFe: string;
    FPathRetConsReciNFe: string;
    FPathRetConsSitNFe: string;
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: string;
    FchNFe: string;
    FdhRecbto: TDateTime;
    FnProt: string;
    FdigVal: string;
    FcStat: integer;
    FxMotivo: string;
    FVersao: String;
  public
    constructor Create;
    destructor Destroy; override;
    function GerarXML: boolean;
    function ObterNomeArquivo(const PadraoNome: TPcnPadraoNomeProcNFe = tpnPrivado): string;
  published
    property Gerador: TGerador read FGerador write FGerador;
    property Schema: TpcnSchema read Fschema write Fschema;
    property PathNFe: string read FPathNFe write FPathNFe;
    property PathRetConsReciNFe: string read FPathRetConsReciNFe write FPathRetConsReciNFe;
    property PathRetConsSitNFe: string read FPathRetConsSitNFe write FPathRetConsSitNFe;
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property verAplic: string read FverAplic write FverAplic;
    property chNFe: string read FchNFe write FchNFe;
    property dhRecbto: TDateTime read FdhRecbto write FdhRecbto;
    property nProt: string read FnProt write FnProt;
    property digVal: string read FdigVal write FdigVal;
    property cStat: integer read FcStat write FcStat;
    property xMotivo: string read FxMotivo write FxMotivo;
    property Versao: String read FVersao write FVersao;
  end;

implementation

uses pcnLeitor;

{ TProcNFe }

constructor TProcNFe.Create;
begin
  FGerador := TGerador.Create;
end;

destructor TProcNFe.Destroy;
begin
  FGerador.Free;
  inherited;
end;

function TProcNFe.ObterNomeArquivo(const PadraoNome: TPcnPadraoNomeProcNFe = tpnPrivado): string;
var
  s: string;
begin
  Result := FchNFe + '-procNFe.xml';
  if PadraoNome = tpnPublico then
  begin
    s := '00' + retornarVersaoLayout(FSchema, tlProcNFe);
    Result := FnProt + '_v' + copy(s, length(s) - 4, 5) + '-procNFe.xml';
  end;
end;

function TProcNFe.GerarXML: boolean;
  function PreencherTAG(const TAG: string; Texto: string): string;
  begin
    result := '<' + TAG + '>' + RetornarConteudoEntre(Texto, '<' + TAG + '>', '</' + TAG + '>') + '</' + TAG + '>';
  end;
var
  XMLNFe: TstringList;
  XMLinfProt: TstringList;
  XMLinfProt2: TstringList;
  wCstat: string;
  xProtNFe: string;
  LocLeitor: TLeitor;
  i : Integer;
  ProtLido : Boolean; //Protocolo lido do arquivo
begin
  ProtLido := False;
  // Alterado por Italo em 28/09/2012

//  if retornarVersaoLayout(FSchema, tlProcNFe) = '1.10' then
//  begin

    XMLNFe := TStringList.Create;
    XMLinfProt := TStringList.Create;
    XMLinfProt2 := TStringList.Create;
    xProtNFe := '';
    FnProt := '';

    // Arquivo NFe
    if not FileExists(FPathNFe) then
      Gerador.wAlerta('XR04', 'NFE', 'NFE', ERR_MSG_ARQUIVO_NAO_ENCONTRADO)
    else
      XMLNFE.LoadFromFile(FPathNFe);
    FchNFe := RetornarConteudoEntre(XMLNFE.Text, 'Id="NFe', '"');
    if trim(FchNFe) = '' then
      Gerador.wAlerta('XR01', 'ID/NFE', 'Numero da chave da NFe', ERR_MSG_VAZIO);

    if (FPathRetConsReciNFe = '') and (FPathRetConsSitNFe = '') then
     begin
      if (FchNFe = '') and (FnProt = '') then
         Gerador.wAlerta('XR06', 'RECIBO/SITUA��O', 'RECIBO/SITUA��O', ERR_MSG_ARQUIVO_NAO_ENCONTRADO)
      else
         ProtLido := True;
     end;

    // Gerar arquivo pelo Recibo da NFe                                       //
    if (FPathRetConsReciNFe <> '') and (FPathRetConsSitNFe = '') and (not ProtLido) then
    begin
      if not FileExists(FPathRetConsReciNFe) then
        Gerador.wAlerta('XR06', 'PROTOCOLO', 'PROTOCOLO', ERR_MSG_ARQUIVO_NAO_ENCONTRADO)
      else
      begin
        I := 0;
        LocLeitor := TLeitor.Create;
        LocLeitor.CarregarArquivo(FPathRetConsReciNFe);
        while LocLeitor.rExtrai(1, 'protNFe', '', i + 1) <> '' do
         begin
           if LocLeitor.rCampo(tcStr, 'chNFe') = FchNFe then
             FnProt := LocLeitor.rCampo(tcStr, 'nProt');
           if trim(FnProt) = '' then
             Gerador.wAlerta('XR01', 'PROTOCOLO/NFe', 'Numero do protocolo', ERR_MSG_VAZIO)
           else
            begin
             xProtNFe := LocLeitor.rExtrai(1, 'protNFe', '', i + 1)+'</protNFe>';
             Gerador.ListaDeAlertas.Clear;
             break;
            end;
            I := I + 1;
         end;
         LocLeitor.Free;
      end;
    end;

    // Gerar arquivo pelo arquivo de consulta da situa��o da NFe              //
    if (FPathRetConsReciNFe = '') and (FPathRetConsSitNFe <> '') and (not ProtLido) then
    begin
      if not FileExists(FPathRetConsSitNFe) then
        Gerador.wAlerta('XR06', 'SITUA��O', 'SITUA��O', ERR_MSG_ARQUIVO_NAO_ENCONTRADO)
      else
      begin
        XMLinfProt.LoadFromFile(FPathRetConsSitNFe);

        wCstat:=RetornarConteudoEntre(XMLinfProt.text, '<cStat>', '</cStat>');
        if ((trim(wCstat) = '101') or
            (trim(wCstat) = '151') or
            (trim(wCstat) = '155')) then //esta cancelada
           XMLinfProt2.Text:=RetornarConteudoEntre(XMLinfProt.text, '<infCanc', '</infCanc>')
        else
           XMLinfProt2.Text:=RetornarConteudoEntre(XMLinfProt.text, '<infProt', '</infProt>');

        // Alterado por Italo em 28/09/2012
        xProtNFe :=
          '<protNFe versao="' + Versao + '">' +
        (******)'<infProt>'+// + RetornarConteudoEntre(XMLinfProt.text, '<infProt', '<tpAmb>') +
        (*********)PreencherTAG('tpAmb', XMLinfProt.text) +
        (*********)PreencherTAG('verAplic', XMLinfProt.text) +
        (*********)PreencherTAG('chNFe', XMLinfProt.text) +
        (*********)PreencherTAG('dhRecbto', XMLinfProt2.text) +
        (*********)PreencherTAG('nProt', XMLinfProt2.text) +
        (*********)PreencherTAG('digVal', XMLinfProt.text) +
        (*********)PreencherTAG('cStat', XMLinfProt.text) +
        (*********)PreencherTAG('xMotivo', XMLinfProt.text) +
        (******)'</infProt>' +
        {****}'</protNFe>';
      end;
    end;

    if ProtLido then
     begin
        xProtNFe :=
          '<protNFe versao="' + Versao + '">' +
        (******)'<infProt>'+
        (*********)'<tpAmb>'+TpAmbToStr(FtpAmb)+'</tpAmb>'+
        (*********)'<verAplic>'+FverAplic+'</verAplic>'+
        (*********)'<chNFe>'+FchNFe+'</chNFe>'+
        (*********)'<dhRecbto>'+FormatDateTime('yyyy-mm-dd"T"hh:nn:ss',FdhRecbto)+'</dhRecbto>'+
        (*********)'<nProt>'+FnProt+'</nProt>'+
        (*********)'<digVal>'+FdigVal+'</digVal>'+
        (*********)'<cStat>'+IntToStr(FcStat)+'</cStat>'+
        (*********)'<xMotivo>'+FxMotivo+'</xMotivo>'+
        (******)'</infProt>'+
        {****}'</protNFe>';

     end;

    // Gerar arquivo
    if Gerador.ListaDeAlertas.Count = 0 then
    begin
      Gerador.ArquivoFormatoXML := '';
      Gerador.wGrupo(ENCODING_UTF8, '', False);
      Gerador.wGrupo('nfeProc versao="' + Versao + '" ' + NAME_SPACE, '');
      Gerador.wTexto('<NFe xmlns' + RetornarConteudoEntre(XMLNFE.Text, '<NFe xmlns', '</NFe>') + '</NFe>');
      Gerador.wTexto(xProtNFe);
      Gerador.wGrupo('/nfeProc');
    end;

    XMLNFE.Free;
    XMLinfProt.Free;
    XMLinfProt2.Free;
    Result := (Gerador.ListaDeAlertas.Count = 0);

//  end;
end;

end.

