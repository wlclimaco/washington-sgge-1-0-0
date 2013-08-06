////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//              PCN - Projeto Cooperar CTe                                    //
//                                                                            //
//   Descri��o: Classes para gera��o/leitura dos arquivos xml da CTe          //
//                                                                            //
//        site: www.projetocooperar.org/CTe                                   //
//       email: projetocooperar@zipmail.com.br                                //
//       forum: http://br.groups.yahoo.com/group/projeto_cooperar_CTe/        //
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
//              "PCN  -  Projeto  Cooperar  CTe", n�o  podendo o mesmo ser    //
//              utilizado sem previa autoriza��o.                             //
//                                                                            //
//    Nota (2): - O uso integral (ou parcial) das units do projeto esta       //
//              condicionado a manuten��o deste cabe�alho junto ao c�digo     //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////

{$I ACBr.inc}

unit pcteInutCTe;

interface

uses
  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao, pcnGerador;

type

  TinutCTe = class(TPersistent)
  private
    FGerador: TGerador;
    FtpAmb: TpcnTipoAmbiente;
    FcUF: integer;
    Fano: integer;
    FCNPJ: string;
    Fmodelo: integer;
    Fserie: integer;
    FnCTIni: integer;
    FnCTFin: integer;
    FxJust: string;
    FIDInutilizacao: string;
  public
    constructor Create;
    destructor Destroy; override;
    function GerarXML: boolean;
    function ObterNomeArquivo: string;
  published
    property Gerador: TGerador read FGerador write FGerador;
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property cUF: integer read FcUF write FcUF;
    property ano: integer read Fano write Fano;
    property CNPJ: string read FCNPJ write FCNPJ;
    property modelo: integer read Fmodelo write Fmodelo;
    property serie: integer read Fserie write Fserie;
    property nCTIni: integer read FnCTIni write FnCTIni;
    property nCTFin: integer read FnCTFin write FnCTFin;
    property xJust: string read FxJust write FxJust;
    property ID: string read FIDInutilizacao;
  end;

implementation

{ TinutCTe }

constructor TinutCTe.Create;
begin
  FGerador := TGerador.Create;
end;

destructor TinutCTe.Destroy;
begin
  FGerador.Free;
  inherited;
end;

function TinutCTe.ObterNomeArquivo: string;
begin
  Result := SomenteNumeros(FIDInutilizacao) + '-ped-inu.xml';
end;

function TinutCTe.GerarXML: boolean;
begin
  FIDInutilizacao := 'ID' + IntToStrZero(FcUF, 2) +
    SomenteNumeros(FCNPJ) + IntToStrZero(Fmodelo, 2) + IntToStrZero(Fserie, 3) +
    IntToStrZero(FnCTIni, 9) + IntToStrZero(FnCTFin, 9);

  Gerador.ArquivoFormatoXML := '';

  Gerador.wGrupo('inutCTe ' + NAME_SPACE_CTE + ' versao="' + CTeinutCTe + '"');
  Gerador.wGrupo('infInut Id="' + FIDInutilizacao + '"');
  if length(FIDInutilizacao) < 39 then
    Gerador.wAlerta('DP04', 'ID', '', 'ID de inutiliza��o inv�lido');
  Gerador.wCampo(tcStr, 'DP05', 'tpAmb ', 001, 001, 1, tpAmbToStr(FtpAmb), DSC_TPAMB);
  Gerador.wCampo(tcStr, 'DP06', 'xServ ', 010, 010, 1, 'INUTILIZAR', DSC_XSERV);
  Gerador.wCampo(tcInt, 'DP07', 'cUF   ', 002, 002, 1, FcUF, DSC_CUF);
  if not ValidarCodigoUF(FcUF) then
    Gerador.wAlerta('DP07', 'cUF', DSC_CUF, ERR_MSG_INVALIDO);
  Gerador.wCampo(tcInt, 'DP08', 'ano   ', 002, 002, 1, Fano, DSC_ANO);
  Gerador.wCampo(tcStr, 'DP09', 'CNPJ  ', 014, 014, 1, SomenteNumeros(FCNPJ), DSC_CNPJ);
  if not ValidarCNPJ(FCNPJ) then
    Gerador.wAlerta('DP09', 'CNPJ', DSC_CNPJ, ERR_MSG_INVALIDO);
  Gerador.wCampo(tcInt, 'DP10', 'mod   ', 002, 002, 1, Fmodelo, DSC_MOD);
  Gerador.wCampo(tcInt, 'DP11', 'serie ', 001, 003, 1, Fserie, DSC_SERIE);
  Gerador.wCampo(tcInt, 'DP12', 'nCTIni', 001, 009, 1, FnCTIni, DSC_NNFINI);
  Gerador.wCampo(tcInt, 'DP13', 'nCTFin', 001, 009, 1, FnCTFin, DSC_NNFFIN);
  if FnCTIni > FnCTFin then
    Gerador.wAlerta('DP13', 'nCTFin', DSC_NNFFIN, ERR_MSG_FINAL_MENOR_INICIAL);
  Gerador.wCampo(tcStr, 'CP14', 'xJust ', 015, 255, 1, FiltrarTextoXML(true, FxJust), DSC_XJUST);
  Gerador.wGrupo('/infInut');
  Gerador.wGrupo('/inutCTe');

  Result := (Gerador.ListaDeAlertas.Count = 0);
end;

end.

