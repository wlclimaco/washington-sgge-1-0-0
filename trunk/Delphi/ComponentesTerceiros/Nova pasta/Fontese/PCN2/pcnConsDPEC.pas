////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//              PCN - Projeto Cooperar NFe                                    //
//                                                                            //
//   Descri��o: Classes para gera��o/leitura dos arquivos xml da NFe          //
//                                                                            //
//        site: www.projetocooperar.org                                       //
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

unit pcnConsDPEC;

interface uses
  SysUtils, Classes, pcnAuxiliar, pcnConversao, pcnGerador;

type

  //////////////////////////////////////////////////////////////////////////////
  //                                                                          //
  //    E M   D E S E N V O L V I M E N T O   -   N � O   T E S T A D O       //
  //                                                                          //
  //////////////////////////////////////////////////////////////////////////////

  TConsDPEC = class(TPersistent)
  private
    FGerador: TGerador;
    FSchema: TpcnSchema;
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: string;
    FchNFe: string;
    FnRegDPEC: string;
    FVersao: String;
  public
    constructor Create;
    destructor Destroy; override;
    function GerarXML: boolean;
    function ObterNomeArquivo: string;
  published
    property Gerador: TGerador read FGerador write FGerador;
    property schema: TpcnSchema read Fschema write Fschema;
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property verAplic: string read FverAplic write FverAplic;
    property chNFe: string read FchNFe write FchNFe;
    property nRegDPEC: string read FnRegDPEC write FnRegDPEC;
    property Versao: String read FVersao write FVersao;
  end;

implementation

{ TConsDPEC }

constructor TConsDPEC.Create;
begin
  FGerador := TGerador.Create;
end;

destructor TConsDPEC.Destroy;
begin
  FGerador.Free;
  inherited;
end;

function TConsDPEC.ObterNomeArquivo: string;
begin
  Result := SomenteNumeros(FchNFe) + '-ped-sit.xml'; ///////alterar
end;

function TConsDPEC.GerarXML: boolean;
begin
  Result := False;

//  if retornarVersaoLayout(Fschema, tlConsDPEC) = '1.01' then
//  begin

    Gerador.ArquivoFormatoXML := '';
    Gerador.wGrupo(ENCODING_UTF8, '', False);
//    Gerador.wGrupo('consDPEC '+  V1_01 + ' ' + NAME_SPACE );
    Gerador.wGrupo('consDPEC versao="'+  Versao + '" ' + NAME_SPACE );
    Gerador.wCampo(tcStr, 'BP03', 'tpAmb', 001, 001, 1, tpAmbToStr(FtpAmb), DSC_TPAMB);
    Gerador.wCampo(tcStr, 'BP04', 'verAplic', 001, 020, 1, FverAplic, DSC_VERAPLIC);
    if trim(FchNFe) <> '' then
      Gerador.wCampo(tcEsp, 'BP05', 'chNFe', 044, 044, 1, FchNFe, DSC_CHNFe);
    if trim(FnRegDPEC) <> '' then
      Gerador.wCampo(tcEsp, 'BP06', 'nRegDPEC', 015, 015, 1, FnRegDPEC, DSC_nRegDPEC);
    Gerador.wGrupo('/consDPEC');
    if (trim(FchNFe) <> '') and (trim(FnRegDPEC) <> '') then
      Gerador.wAlerta('BP05/BP06', 'chNFe/nRegDPEC', DSC_CHNFe + '/' + DSC_nRegDPEC, ERR_MSG_SOMENTE_UM);
    Result := (Gerador.ListaDeAlertas.Count = 0);

//  end;
end;

end.

