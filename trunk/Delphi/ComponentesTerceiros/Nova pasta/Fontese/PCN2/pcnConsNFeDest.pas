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

unit pcnConsNFeDest;

interface uses
  SysUtils, Classes, pcnAuxiliar, pcnConversao, pcnGerador;

type

  //////////////////////////////////////////////////////////////////////////////
  //                                                                          //
  //    E M   D E S E N V O L V I M E N T O   -   N � O   T E S T A D O       //
  //                                                                          //
  //////////////////////////////////////////////////////////////////////////////

  TConsNFeDest = class(TPersistent)
  private
    FGerador: TGerador;
    FSchema: TpcnSchema;
    FtpAmb: TpcnTipoAmbiente;
    FCNPJ: String;
    FindNFe: TpcnIndicadorNFe;
    FindEmi: TpcnIndicadorEmissor;
    FultNSU: String;
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
    property CNPJ: String read FCNPJ write FCNPJ;
    property indNFe: TpcnIndicadorNFe read FindNFe write FindNFe;
    property indEmi: TpcnIndicadorEmissor read FindEmi write FindEmi;
    property ultNSU: String read FultNSU write FultNSU;
    property Versao: String read FVersao write FVersao;
  end;

implementation

{ TConsNFeDest }

constructor TConsNFeDest.Create;
begin
  FGerador := TGerador.Create;
end;

destructor TConsNFeDest.Destroy;
begin
  FGerador.Free;
  inherited;
end;

function TConsNFeDest.ObterNomeArquivo: string;
var
  DataHora: TDateTime;
  Year, Month, Day, Hour, Min, Sec, Milli: Word;
  AAAAMMDDTHHMMSS: string;
begin
  Datahora := now;
  DecodeTime(DataHora, Hour, Min, Sec, Milli);
  DecodeDate(DataHora, Year, Month, Day);
  AAAAMMDDTHHMMSS := IntToStrZero(Year, 4) + IntToStrZero(Month, 2) + IntToStrZero(Day, 2) +
    IntToStrZero(Hour, 2) + IntToStrZero(Min, 2) + IntToStrZero(Sec, 2);
  Result := AAAAMMDDTHHMMSS + '-con-nfe-dest.xml';
end;

function TConsNFeDest.GerarXML: boolean;
var
 sDoc, sNSU: String;
begin
  Result := False;

//  if retornarVersaoLayout(Fschema, tlConsNFeDest) = '2.00' then
//  begin

    Gerador.ArquivoFormatoXML := '';
//    Gerador.wGrupo('consNFeDest ' + NAME_SPACE + ' ' + V1_01);
    Gerador.wGrupo('consNFeDest ' + NAME_SPACE + ' versao="' + Versao + '"');
    Gerador.wCampo(tcStr, 'IP03', 'tpAmb', 001, 001, 1, tpAmbToStr(FtpAmb), DSC_TPAMB);
    Gerador.wCampo(tcStr, 'IP04', 'xServ', 018, 018, 1, 'CONSULTAR NFE DEST', DSC_XSERV);

    sDoc := SomenteNumeros( FCNPJ );
    Gerador.wCampo(tcStr, 'IP05', 'CNPJ', 014, 014, 1, sDoc , DSC_CNPJ);
    if not ValidarCNPJ( sDoc ) then Gerador.wAlerta('IP05', 'CNPJ', DSC_CNPJ, ERR_MSG_INVALIDO);

    Gerador.wCampo(tcStr, 'IP06', 'indNFe', 001, 001, 1, IndicadorNFeToStr(FindNFe), DSC_INDNFE);
    Gerador.wCampo(tcStr, 'IP07', 'indEmi', 001, 001, 1, IndicadorEmissorToStr(FindEmi), DSC_INDEMI);

    sNSU := FultNSU;
    if sNSU = '' then sNSU := '0';
    Gerador.wCampo(tcStr, 'IP08', 'ultNSU', 001, 015, 1, sNSU, DSC_ULTNSU);
    Gerador.wGrupo('/consNFeDest');
    Result := (Gerador.ListaDeAlertas.Count = 0);

//  end;

end;

end.

