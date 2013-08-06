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


unit pcteRetConsReciCTe;

interface

uses
  SysUtils, Classes, pcnAuxiliar, pcnConversao, pcnLeitor;

type

  TRetConsReciCTe = class;
  TProtCTeCollection = class;
  TProtCTeCollectionItem = class;

  TRetConsReciCTe = class(TPersistent)
  private
    FLeitor: TLeitor;
    FtpAmb: TpcnTipoAmbiente;
    Fversao : string;
    FverAplic: string;
    FnRec: string;
    FcStat: integer;
    FxMotivo: string;
    FcUF: integer;
    FProtCTe: TProtCTeCollection;
    procedure SetProtCTe(const Value: TProtCTeCollection);
  public
    constructor Create;
    destructor Destroy; override;
    function LerXML: boolean;
  published
    property Leitor: TLeitor read FLeitor write FLeitor;
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property versao: string read Fversao write Fversao;
    property verAplic: string read FverAplic write FverAplic;
    property nRec: string read FnRec write FnRec;
    property cStat: integer read FcStat write FcStat;
    property xMotivo: string read FxMotivo write FxMotivo;
    property cUF: integer read FcUF write FcUF;
    property ProtCTe: TProtCTeCollection read FProtCTe write SetProtCTe;
  end;

  TProtCTeCollection = class(TCollection)
  private
    function GetItem(Index: Integer): TProtCTeCollectionItem;
    procedure SetItem(Index: Integer; Value: TProtCTeCollectionItem);
  public
    constructor Create(AOwner: TRetConsReciCTe); reintroduce;
    function Add: TProtCTeCollectionItem;
    property Items[Index: Integer]: TProtCTeCollectionItem read GetItem write SetItem; default;
  end;

  TProtCTeCollectionItem = class(TCollectionItem)
  private
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: string;
    FchCTe: string;
    FdhRecbto: TDateTime;
    FnProt: string;
    FdigVal: string;
    FcStat: integer;
    FxMotivo: string;
  published
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property verAplic: string read FverAplic write FverAplic;
    property chCTe: string read FchCTe write FchCTe;
    property dhRecbto: TDateTime read FdhRecbto write FdhRecbto;
    property nProt: string read FnProt write FnProt;
    property digVal: string read FdigVal write FdigVal;
    property cStat: integer read FcStat write FcStat;
    property xMotivo: string read FxMotivo write FxMotivo;
  end;

implementation

{ TRetConsReciCTe }

constructor TRetConsReciCTe.Create;
begin
  FLeitor := TLeitor.Create;
  FProtCTe := TProtCTeCollection.Create(self);
end;

destructor TRetConsReciCTe.Destroy;
begin
  FLeitor.Free;
  FProtCTe.Free;
  inherited;
end;

procedure TRetConsReciCTe.SetProtCTe(const Value: TProtCTeCollection);
begin
  FProtCTe.Assign(Value);
end;

{ TProtCTeCollection }

constructor TProtCTeCollection.Create(AOwner: TRetConsReciCTe);
begin
  inherited Create(TProtCTeCollectionItem);
end;

function TProtCTeCollection.Add: TProtCTeCollectionItem;
begin
  Result := TProtCTeCollectionItem(inherited Add);
end;

function TProtCTeCollection.GetItem(Index: Integer): TProtCTeCollectionItem;
begin
  Result := TProtCTeCollectionItem(inherited GetItem(Index));
end;

procedure TProtCTeCollection.SetItem(Index: Integer; Value: TProtCTeCollectionItem);
begin
  inherited SetItem(Index, Value);
end;

////////////////////////////////////////////////////////////////////////////////

function TRetConsReciCTe.LerXML: boolean;
var
  ok: boolean;
  i: integer;
begin
  Result := False;
  try
    if Leitor.rExtrai(1, 'retConsReciCTe') <> '' then
    begin
      Leitor.Grupo := Leitor.Arquivo;

      (*BR03 *)FtpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
      (*BR04 *)FverAplic := Leitor.rCampo(tcStr, 'verAplic');
      (*BR05 *)FnRec     := Leitor.rCampo(tcStr, 'nRec');
      (*BR06 *)FcStat    := Leitor.rCampo(tcInt, 'cStat');
      (*BR07 *)FxMotivo  := Leitor.rCampo(tcStr, 'xMotivo');
      (*BR08 *)FcUF      := Leitor.rCampo(tcInt, 'cUF');

      i := 0;
      while (FcStat = 104) and (Leitor.rExtrai(1, 'infProt', '', i + 1) <> '') do
      begin
        ProtCTe.Add;

        (*PR05*)ProtCTe[i].FtpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
        (*PR06*)ProtCTe[i].FverAplic := Leitor.rCampo(tcStr, 'verAplic');
        (*PR07*)ProtCTe[i].FchCTe    := Leitor.rCampo(tcStr, 'chCTe');
        (*PR08*)ProtCTe[i].FdhRecbto := Leitor.rCampo(tcDatHor, 'dhRecbto');
        (*PR09*)ProtCTe[i].FnProt    := Leitor.rCampo(tcStr, 'nProt');
        (*PR10*)ProtCTe[i].FdigVal   := Leitor.rCampo(tcStr, 'digVal');
        (*PR11*)ProtCTe[i].FcStat    := Leitor.rCampo(tcInt, 'cStat');
        (*PR12*)ProtCTe[i].FxMotivo  := Leitor.rCampo(tcStr, 'xMotivo');
        
        inc(i);
      end;
      
      if i = 0 then ProtCTe.Add;

      Result := True;
    end;
  except
    result := False;
  end;
end;

end.

