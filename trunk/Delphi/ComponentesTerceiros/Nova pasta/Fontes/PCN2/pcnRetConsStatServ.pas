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

unit pcnRetConsStatServ;

interface uses
  SysUtils, Classes, pcnAuxiliar, pcnConversao, pcnLeitor;

type

  //////////////////////////////////////////////////////////////////////////////
  //                                                                          //
  //    E M   D E S E N V O L V I M E N T O   -   N � O   T E S T A D O       //
  //                                                                          //
  //////////////////////////////////////////////////////////////////////////////

  TRetConsStatServ = class(TPersistent)
  private
    FtpAmb: TpcnTipoAmbiente;
    FdhRecbto: TDateTime;
    FcStat: integer;
    FLeitor: TLeitor;
    FxMotivo: string;
    FcUF: integer;
    FverAplic: string;
    FtMed: integer;
    FdhRetorno: TDateTime;
    FxObs: string;
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
    property dhRecbto: TDateTime read FdhRecbto write FdhRecbto;
    property tMed: integer read FtMed write FtMed;
    property dhRetorno: TDateTime read FdhRetorno write FdhRetorno;
    property xObs: string read FxObs write FxObs;
  end;

implementation

{ TRetConsStatServ }

constructor TRetConsStatServ.Create;
begin
  FLeitor := TLeitor.Create;
end;

destructor TRetConsStatServ.Destroy;
begin
  FLeitor.Free;
  inherited;
end;

function TRetConsStatServ.LerXml: boolean;
var
  ok: boolean;
begin
  Result := False;
  try
    Leitor.Grupo := Leitor.Arquivo;
    if leitor.rExtrai(1, 'retConsStatServ') <> '' then
    begin
      (*FR03*)FtpAmb := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
      (*FR04*)FverAplic := Leitor.rCampo(tcStr, 'verAplic');
      (*FR05*)FcStat := Leitor.rCampo(tcInt, 'cStat');
      (*FR06*)FxMotivo := Leitor.rCampo(tcStr, 'xMotivo');
      (*FR07*)FcUF := Leitor.rCampo(tcInt, 'cUF');
      (*FR08*)FdhRecbto := Leitor.rCampo(tcDatHor, 'dhRecbto');
      (*FR09*)FtMed := Leitor.rCampo(tcInt, 'tMed');
      (*FR10*)FdhRetorno := Leitor.rCampo(tcDatHor, 'dhRetorno');
      (*FR11*)FxObs := Leitor.rCampo(tcStr, 'xObs');
      Result := True;
    end;
  except
    Result := False;
  end;
end;

end.

