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

unit pcnRetInutNFe;

interface uses
  SysUtils, Classes, pcnAuxiliar, pcnConversao, pcnLeitor;

type

  TRetInutNFe = class(TPersistent)
  private
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: string;
    FLeitor: TLeitor;
    FcStat: integer;
    FxMotivo: string;
    FcUF: integer;
    Fano: integer;
    FCNPJ: string;
    FModelo: integer;
    FSerie: integer;
    FnNFIni: integer;
    FnNFFin: integer;
    FdhRecbto: TDateTime;
    FnProt: string;
  public
    constructor Create;
    destructor Destroy; override;
    function LerXml: boolean;
  published
    property Leitor: TLeitor read FLeitor write FLeitor;
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property verAplic: string read FverAplic write FverAplic;
    property cStat: integer read FcStat write FcStat;
    property xMotivo: string read FxMotivo write FxMotivo;
    property cUF: integer read FcUF write FcUF;
    property ano: integer read Fano write Fano;
    property CNPJ: string read FCNPJ write FCNPJ;
    property Modelo: integer read FModelo write FModelo;
    property Serie: integer read FSerie write FSerie;
    property nNFIni: integer read FnNFIni write FnNFIni;
    property nNFFin: integer read FnNFFin write FnNFFin;
    property dhRecbto: TDateTime read FdhRecbto write FdhRecbto;
    property nProt: string read FnProt write FnProt;
  end;

implementation

{ TretAtuCadEmiDFe }

constructor TRetInutNFe.Create;
begin
  FLeitor := TLeitor.Create;
end;

destructor TRetInutNFe.Destroy;
begin
  FLeitor.Free;
  inherited;
end;

function TRetInutNFe.LerXml: boolean;
var
  ok: boolean;
begin
  Result := False;
  try
    if leitor.rExtrai(1, 'infInut') <> '' then
    begin
      (*DR05 *)FtpAmb := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
      (*DR06 *)FverAplic := Leitor.rCampo(tcStr, 'verAplic');
      (*DR07 *)FcStat := Leitor.rCampo(tcInt, 'cStat');
      (*DR08 *)FxMotivo := Leitor.rCampo(tcStr, 'xMotivo');
      (*DR09 *)FcUF := Leitor.rCampo(tcInt, 'cUF');
      (*DR10 *)Fano := Leitor.rCampo(tcInt, 'ano');
      (*DR11 *)FCNPJ := Leitor.rCampo(tcStr, 'CNPJ');
      (*DR12 *)FModelo := Leitor.rCampo(tcInt, 'mod');
      (*DR13 *)FSerie := Leitor.rCampo(tcInt, 'serie');
      (*DR14 *)FnNFIni := Leitor.rCampo(tcInt, 'nNFIni');
      (*DR15 *)FnNFFin := Leitor.rCampo(tcInt, 'nNFFin');
      (*DR16 *)FdhRecbto := Leitor.rCampo(tcDatHor, 'dhRecbto');
      (*DR17 *)FnProt := Leitor.rCampo(tcStr, 'nProt');
      Result := True;
    end;
  except
    result := False;
  end;
end;

end.

