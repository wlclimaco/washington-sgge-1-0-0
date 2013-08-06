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

unit pcnRetConsSitNFe;

interface uses
  SysUtils, Classes, pcnAuxiliar, pcnConversao, pcnLeitor, pcnProcNFe,
  pcnRetCancNFe, pcnRetEnvEventoNFe;

type

  //////////////////////////////////////////////////////////////////////////////
  //                                                                          //
  //    E M   D E S E N V O L V I M E N T O   -   N � O   T E S T A D O       //
  //                                                                          //
  //////////////////////////////////////////////////////////////////////////////

  {eventos_juaumkiko}
  TRetEventoNFeCollection = class;
  TRetEventoNFeCollectionItem = class;
  TRetConsSitNFe = class;

  {eventos_juaumkiko}
  TRetEventoNFeCollection = class(TCollection)
  private
    function GetItem(Index: Integer): TRetEventoNFeCollectionItem;
    procedure SetItem(Index: Integer; Value: TRetEventoNFeCollectionItem);
  public
    constructor Create(AOwner: TPersistent);
    function Add: TRetEventoNFeCollectionItem;
    property Items[Index: Integer]: TRetEventoNFeCollectionItem read GetItem write SetItem; default;
  end;

  {eventos_juaumkiko}
  TRetEventoNFeCollectionItem = class(TCollectionItem)
  private
    FRetEventoNFe: TRetEventoNFe;
  public
    constructor Create; reintroduce;
    destructor Destroy; override;
  published
    property RetEventoNFe: TRetEventoNFe read FRetEventoNFe write FRetEventoNFe;
  end;

  TRetConsSitNFe = class(TPersistent)
  private
    FLeitor: TLeitor;
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: string;
    FcStat: Integer;
    FxMotivo: string;
    FcUF: integer;
    FdhRecbto: TDateTime;
    FchNFe: string;
    FprotNFe: TProcNFe;
    FretCancNFe: TRetCancNFe;
    FprocEventoNFe: TRetEventoNFeCollection;
  public
    constructor Create;
    destructor Destroy; override;
    function LerXml: boolean;
  published
    property Leitor: TLeitor read FLeitor write FLeitor;
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property verAplic: string read FverAplic write FverAplic;
    property cStat: Integer read FcStat write FcStat;
    property xMotivo: string read FxMotivo write FxMotivo;
    property cUF: integer read FcUF write FcUF;
    property dhRecbto: TDateTime read FdhRecbto write FdhRecbto;
    property chNfe: string read FchNfe write FchNfe;
    property protNFe: TProcNFe read FprotNFe write FprotNFe;
    property retCancNFe: TRetCancNFe read FretCancNFe write FretCancNFe;
    property procEventoNFe: TRetEventoNFeCollection read FprocEventoNFe write FprocEventoNFe;
  end;

implementation

{ TRetConsSitNFe }

constructor TRetConsSitNFe.Create;
begin
  FLeitor := TLeitor.Create;
  FprotNFe := TProcNFe.create;
  FretCancNFe := TRetCancNFe.create;
end;

destructor TRetConsSitNFe.Destroy;
begin
  FLeitor.Free;
  FprotNFe.Free;
  FretCancNFe.Free;
  if Assigned(procEventoNFe) then
    procEventoNFe.Free;
  inherited;
end;

function TRetConsSitNFe.LerXml: boolean;
var
  ok: boolean;
  i: integer;
begin
  Result := False;
  try
    if leitor.rExtrai(1, 'retConsSitNFe') <> '' then
    begin
      (*ER03 *)FtpAmb     := StrToTpAmb(ok, leitor.rCampo(tcStr, 'tpAmb'));
      (*ER04 *)FverAplic  := leitor.rCampo(tcStr, 'verAplic');
      (*ER05 *)FcStat     := leitor.rCampo(tcInt, 'cStat');
      (*ER06 *)FxMotivo   := leitor.rCampo(tcStr, 'xMotivo');
      (*ER07 *)FcUF       := leitor.rCampo(tcInt, 'cUF');
      (*ER07a*)FdhRecbto  := leitor.rCampo(tcDatHor, 'dhRecbto');
      (*ER07b*)FchNFe     := leitor.rCampo(tcStr, 'chNFe');
      case FcStat of 100,101,104,110,150,151,155,301,302:
        begin
          if ((Leitor.rExtrai(1, 'protNFe') <> '') or (Leitor.rExtrai(1, 'infProt') <> '')) then
          begin
            protNFe.tpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
            protNFe.verAplic := Leitor.rCampo(tcStr, 'verAplic');
            protNFe.chNFe    := Leitor.rCampo(tcStr, 'chNFe');
            protNFe.dhRecbto := Leitor.rCampo(tcDatHor, 'dhRecbto');
            protNFe.nProt    := Leitor.rCampo(tcStr, 'nProt');
            protNFe.digVal   := Leitor.rCampo(tcStr, 'digVal');
            protNFe.cStat    := Leitor.rCampo(tcInt, 'cStat');
            protNFe.xMotivo  := Leitor.rCampo(tcStr, 'xMotivo');
          end;
        end;
      end;
      if FcStat in [101,151,155] then
      begin
        if Leitor.rExtrai(1, 'infCanc') <> '' then
        begin
          retCancNFe.tpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
          retCancNFe.verAplic := Leitor.rCampo(tcStr, 'verAplic');
          retCancNFe.cStat    := Leitor.rCampo(tcInt, 'cStat');
          retCancNFe.xMotivo  := Leitor.rCampo(tcStr, 'xMotivo');
          retCancNFe.cUF      := Leitor.rCampo(tcInt, 'cUF');
          retCancNFe.chNFe    := Leitor.rCampo(tcStr, 'chNFe');
          retCancNFe.dhRecbto := Leitor.rCampo(tcDatHor, 'dhRecbto');
          retCancNFe.nProt    := Leitor.rCampo(tcStr, 'nProt');
        end;
      end;
      {eventos_juaumkiko}
      if Assigned(procEventoNFe) then
        procEventoNFe.Free;
      procEventoNFe := TRetEventoNFeCollection.Create(Self);
      i:=0;
      while Leitor.rExtrai(1, 'procEventoNFe', '', i + 1) <> '' do
      begin
        procEventoNFe.Add;
        procEventoNFe.Items[i].RetEventoNFe.Leitor.Arquivo := Leitor.Grupo;
        procEventoNFe.Items[i].RetEventoNFe.LerXml;
        inc(i);
      end;
      Result := True;
    end;
  except
    Result := False;
  end;
end;

{ TRetEventoCollection }

function TRetEventoNFeCollection.Add: TRetEventoNFeCollectionItem;
begin
  Result := TRetEventoNFeCollectionItem(inherited Add);
  Result.create;
end;

constructor TRetEventoNFeCollection.Create(AOwner: TPersistent);
begin
  inherited Create(TRetEventoNFeCollectionItem);
end;

function TRetEventoNFeCollection.GetItem(Index: Integer): TRetEventoNFeCollectionItem;
begin
  Result := TRetEventoNFeCollectionItem(inherited GetItem(Index));
end;

procedure TRetEventoNFeCollection.SetItem(Index: Integer;
  Value: TRetEventoNFeCollectionItem);
begin
  inherited SetItem(Index, Value);
end;

{ TRetEventoCollectionItem }

constructor TRetEventoNFeCollectionItem.Create;
begin
  FRetEventoNFe := TRetEventoNFe.Create;
end;

destructor TRetEventoNFeCollectionItem.Destroy;
begin
  FRetEventoNFe.Free;
  inherited;
end;

end.

