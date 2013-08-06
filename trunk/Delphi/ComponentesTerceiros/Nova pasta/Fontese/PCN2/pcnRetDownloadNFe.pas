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
///
unit pcnRetDownloadNFe;

interface uses

  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao, pcnLeitor, ACBrUtil;

type
  TRetNFeCollection  = class ;
  TRetNFeCollectionItem = class ;
  TRetDownloadNFe = class ;

  TRetNFeCollection = class(TCollection)
  private
    function GetItem(Index: Integer): TRetNFeCollectionItem;
    procedure SetItem(Index: Integer; Value: TRetNFeCollectionItem);
  public
    constructor Create(AOwner: TPersistent);
    function Add: TRetNFeCollectionItem;
    property Items[Index: Integer]: TRetNFeCollectionItem read GetItem write SetItem; default;
  end;

  TRetNFeCollectionItem = class(TCollectionItem)
  private
    FchNFe: String;
    FcStat: Integer;
    FxMotivo: String;
    FprocNFe: AnsiString;
  public
    constructor Create; reintroduce;
    destructor Destroy; override;
  published
    property chNFe: String       read FchNFe   write FchNFe;
    property cStat: Integer      read FcStat   write FcStat;
    property xMotivo: String     read FxMotivo write FxMotivo;
    property procNFe: AnsiString read FprocNFe write FprocNFe;
  end;

  TRetDownloadNFe = class(TPersistent)
  private
    FLeitor: TLeitor;
    Fversao : String;
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: String;
    FcStat: Integer;
    FxMotivo: String;
    FdhResp: TDateTime;
    FretNFe: TRetNFeCollection;
    FXML: AnsiString;

    procedure SetretNFe(const Value: TRetNFeCollection);
  public
    constructor Create;
    destructor Destroy; override;
    function LerXml: boolean;
  published
    property Leitor: TLeitor           read FLeitor   write FLeitor;
    property versao: String            read Fversao   write Fversao;
    property tpAmb: TpcnTipoAmbiente   read FtpAmb    write FtpAmb;
    property verAplic: String          read FverAplic write FverAplic;
    property cStat: Integer            read FcStat    write FcStat;
    property xMotivo: String           read FxMotivo  write FxMotivo;
    property dhResp: TDateTime         read FdhResp   write FdhResp;
    property retNFe: TRetNFeCollection read FretNFe   write SetretNFe;
    property XML: AnsiString           read FXML      write FXML;
  end;


implementation

{ TRetNFeCollection }

function TRetNFeCollection. Add: TRetNFeCollectionItem;
begin
  Result := TRetNFeCollectionItem(inherited Add);
  Result.create;
end;

constructor TRetNFeCollection.Create(AOwner: TPersistent);
begin
  inherited Create(TRetNFeCollectionItem);
end;

function TRetNFeCollection.GetItem(
  Index: Integer): TRetNFeCollectionItem;
begin
  Result := TRetNFeCollectionItem(inherited GetItem(Index));
end;

procedure TRetNFeCollection.SetItem(Index: Integer;
  Value: TRetNFeCollectionItem);
begin
  inherited SetItem(Index, Value);
end;

{ TRetNFeCollectionItem }

constructor TRetNFeCollectionItem.Create;
begin

end;

destructor TRetNFeCollectionItem.Destroy;
begin

  inherited;
end;

{ TRetDownloadNFe }
procedure TRetDownloadNFe.SetretNFe(const Value: TRetNFeCollection);
begin
  FretNFe.Assign(Value);
end;

constructor TRetDownloadNFe.Create;
begin
  FLeitor := TLeitor.Create;
  FretNFe := TRetNFeCollection.Create(Self);
end;

destructor TRetDownloadNFe.Destroy;
begin
  FLeitor.Free;
  FretNFe.Free;
  inherited;
end;

function TRetDownloadNFe.LerXml: boolean;
var
  ok: Boolean;
  i : Integer;
begin
  Result := False;
  try
    i := -1;

    FXML := Self.Leitor.Arquivo;
    if (Leitor.rExtrai(1, 'retDownloadNFe') <> '') then
    begin
      (*JR02 *)Fversao   := Leitor.rCampo(tcStr, 'versao');
      (*JR03 *)FtpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
      (*JR04 *)FverAplic := Leitor.rCampo(tcStr, 'verAplic');
      (*JR05 *)FcStat    := Leitor.rCampo(tcInt, 'cStat');
      (*JR06 *)FxMotivo  := Leitor.rCampo(tcStr, 'xMotivo');
      (*JR07 *)FdhResp   := Leitor.rCampo(tcDatHor, 'dhResp');
      i := 0;
      while Leitor.rExtrai(2, 'retNFe', '', i + 1) <> '' do
      begin
        FretNFe.Add;
        (*JR09 *)FretNFe.Items[i].FchNFe   := Leitor.rCampo(tcStr, 'chNFe');
        (*JR10 *)FretNFe.Items[i].FcStat   := Leitor.rCampo(tcInt, 'cStat');
        (*JR11 *)FretNFe.Items[i].FxMotivo := Leitor.rCampo(tcStr, 'xMotivo');

        (*JR12 *)FretNFe.Items[i].FprocNFe := '<?xml version="1.0" encoding="utf-8"?>' +
                                              SeparaDados(Leitor.Grupo, 'procNFe');
        inc(i);
      end;
      Result := True;
    end;

    if i = 0 then
      FretNFe.Add;
  except
    result := False;
  end;
end;

end.
