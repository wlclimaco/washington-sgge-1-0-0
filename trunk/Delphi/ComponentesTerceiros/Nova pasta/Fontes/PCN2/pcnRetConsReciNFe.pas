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


unit pcnRetConsReciNFe;

interface uses
  SysUtils, Classes, pcnAuxiliar, pcnConversao, pcnLeitor;

type

  //////////////////////////////////////////////////////////////////////////////
  //                                                                          //
  //    E M   D E S E N V O L V I M E N T O   -   N � O   T E S T A D O       //
  //                                                                          //
  //////////////////////////////////////////////////////////////////////////////

  TRetConsReciNFe = class;
  TProtNFeCollection = class;
  TProtNFeCollectionItem = class;

  TRetConsReciNFe = class(TPersistent)
  private
    FLeitor: TLeitor;
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: string;
    FnRec: string;
    FcStat: integer;
    FxMotivo: string;
    FcUF: integer;
    FcMsg: integer;
    FxMsg: string;
    FProtNFe: TProtNFeCollection;
    procedure SetProtNFe(const Value: TProtNFeCollection);
  public
    constructor Create;
    destructor Destroy; override;
    function LerXML: boolean;
  published
    property Leitor: TLeitor read FLeitor write FLeitor;
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property verAplic: string read FverAplic write FverAplic;
    property nRec: string read FnRec write FnRec;
    property cStat: integer read FcStat write FcStat;
    property xMotivo: string read FxMotivo write FxMotivo;
    property cUF: integer read FcUF write FcUF;
    property cMsg: integer read FcMsg write FcMsg;
    property xMsg: string read FxMsg write FxMsg;
    property ProtNFe: TProtNFeCollection read FProtNFe write SetProtNFe;
  end;

  TProtNFeCollection = class(TCollection)
  private
    function GetItem(Index: Integer): TProtNFeCollectionItem;
    procedure SetItem(Index: Integer; Value: TProtNFeCollectionItem);
  public
    constructor Create(AOwner: TRetConsReciNFe); reintroduce;
    function Add: TProtNFeCollectionItem;
    property Items[Index: Integer]: TProtNFeCollectionItem read GetItem write SetItem; default;
  end;

  TProtNFeCollectionItem = class(TCollectionItem)
  private
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: string;
    FchNFe: string;
    FdhRecbto: TDateTime;
    FnProt: string;
    FdigVal: string;
    FcStat: integer;
    FxMotivo: string;
  published
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property verAplic: string read FverAplic write FverAplic;
    property chNFe: string read FchNFe write FchNFe;
    property dhRecbto: TDateTime read FdhRecbto write FdhRecbto;
    property nProt: string read FnProt write FnProt;
    property digVal: string read FdigVal write FdigVal;
    property cStat: integer read FcStat write FcStat;
    property xMotivo: string read FxMotivo write FxMotivo;
  end;

implementation

{ TRetConsReciNFe }

constructor TRetConsReciNFe.Create;
begin
  FLeitor := TLeitor.Create;
  FProtNFe := TProtNFeCollection.Create(self);
end;

destructor TRetConsReciNFe.Destroy;
begin
  FLeitor.Free;
  FProtNFe.Free;
  inherited;
end;

procedure TRetConsReciNFe.SetProtNFe(const Value: TProtNFeCollection);
begin
  FProtNFe.Assign(Value);
end;

{ TProtNFeCollection }

constructor TProtNFeCollection.Create(AOwner: TRetConsReciNFe);
begin
  inherited Create(TProtNFeCollectionItem);
end;

function TProtNFeCollection.Add: TProtNFeCollectionItem;
begin
  Result := TProtNFeCollectionItem(inherited Add);
end;

function TProtNFeCollection.GetItem(Index: Integer): TProtNFeCollectionItem;
begin
  Result := TProtNFeCollectionItem(inherited GetItem(Index));
end;

procedure TProtNFeCollection.SetItem(Index: Integer; Value: TProtNFeCollectionItem);
begin
  inherited SetItem(Index, Value);
end;

////////////////////////////////////////////////////////////////////////////////

function TRetConsReciNFe.LerXML: boolean;
var
  ok: boolean;
  i: integer;
begin
  Result := False;
  try
    if Leitor.rExtrai(1, 'retConsReciNFe') <> '' then
    begin
      Leitor.Grupo := Leitor.Arquivo;
      (*BR03 *)FtpAmb := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
      (*BR04 *)FverAplic := Leitor.rCampo(tcStr, 'verAplic');
      (*BR04a*)FnRec := Leitor.rCampo(tcStr, 'nRec');
      (*BR05 *)FcStat := Leitor.rCampo(tcInt, 'cStat');
      (*BR06 *)FxMotivo := Leitor.rCampo(tcStr, 'xMotivo');
      (*BR06a*)FcUF := Leitor.rCampo(tcInt, 'cUF');
      (*BR06b*)FcMsg := Leitor.rCampo(tcInt, 'cMsg');
      (*BR06c*)FxMsg := Leitor.rCampo(tcStr, 'xMsg');
      i := 0;
      while (FcStat = 104) and (Leitor.rExtrai(1, 'infProt', '', i + 1) <> '') do
      begin
        ProtNFe.Add;
        (*PR05*)ProtNFe[i].FtpAmb := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
        (*PR06*)ProtNFe[i].FverAplic := Leitor.rCampo(tcStr, 'verAplic');
        (*PR07*)ProtNFe[i].FchNFe := Leitor.rCampo(tcStr, 'chNFe');
        (*PR08*)ProtNFe[i].FdhRecbto := Leitor.rCampo(tcDatHor, 'dhRecbto');
        (*PR09*)ProtNFe[i].FnProt := Leitor.rCampo(tcStr, 'nProt');
        (*PR10*)ProtNFe[i].FdigVal := Leitor.rCampo(tcStr, 'digVal');
        (*PR11*)ProtNFe[i].FcStat := Leitor.rCampo(tcInt, 'cStat');
        (*PR12*)ProtNFe[i].FxMotivo := Leitor.rCampo(tcStr, 'xMotivo');
        inc(i);
      end;
      if i = 0 then
        ProtNFe.Add;
      Result := True;
    end;
  except
    result := False;
  end;
end;

end.

