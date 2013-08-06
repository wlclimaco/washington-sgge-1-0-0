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

unit pcteRetConsSitCTe;

interface

uses
  SysUtils, Classes,
  pcnAuxiliar, pcnConversao, pcnLeitor, pcteProcCTe,
  pcteRetCancCTe, pcteRetEnvEventoCTe;

type

  TRetEventoCTeCollection = class;
  TRetEventoCTeCollectionItem = class;
  TRetConsSitCTe = class;

  TRetEventoCTeCollection = class(TCollection)
  private
    function GetItem(Index: Integer): TRetEventoCTeCollectionItem;
    procedure SetItem(Index: Integer; Value: TRetEventoCTeCollectionItem);
  public
    constructor Create(AOwner: TPersistent);
    function Add: TRetEventoCTeCollectionItem;
    property Items[Index: Integer]: TRetEventoCTeCollectionItem read GetItem write SetItem; default;
  end;

  TRetEventoCTeCollectionItem = class(TCollectionItem)
  private
    FRetEventoCTe: TRetEventoCTe;
  public
    constructor Create; reintroduce;
    destructor Destroy; override;
  published
    property RetEventoCTe: TRetEventoCTe read FRetEventoCTe write FRetEventoCTe;
  end;

  TRetConsSitCTe = class(TPersistent)
  private
    FLeitor: TLeitor;
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: string;
    FcStat: Integer;
    FxMotivo: string;
    FcUF: integer;
    FchCTe: string;
    FprotCTe: TProcCTe;
    FretCancCTe: TRetCancCTe;
    FprocEventoCTe: TRetEventoCTeCollection;
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
    property chCTe: string read FchCTe write FchCTe;
    property protCTe: TProcCTe read FprotCTe write FprotCTe;
    property retCancCTe: TRetCancCTe read FretCancCTe write FretCancCTe;
    property procEventoCTe: TRetEventoCTeCollection read FprocEventoCTe write FprocEventoCTe;
  end;

implementation

{ TRetConsSitCTe }

constructor TRetConsSitCTe.Create;
begin
  FLeitor := TLeitor.Create;
  FprotCTe := TProcCTe.create;
  FretCancCTe := TRetCancCTe.create;
end;

destructor TRetConsSitCTe.Destroy;
begin
  FLeitor.Free;
  FprotCTe.Free;
  FretCancCTe.Free;
  if Assigned(procEventoCTe) then
    procEventoCTe.Free;
  inherited;
end;

function TRetConsSitCTe.LerXml: boolean;
var
  ok: boolean;
  i: Integer;
begin
  Result := False;
  try
    if leitor.rExtrai(1, 'retConsSitCTe') <> '' then
    begin
      (*ER03 *)FtpAmb    := StrToTpAmb(ok, leitor.rCampo(tcStr, 'tpAmb'));
      (*ER04 *)FverAplic := leitor.rCampo(tcStr, 'verAplic');
      (*ER05 *)FcStat    := leitor.rCampo(tcInt, 'cStat');
      (*ER06 *)FxMotivo  := leitor.rCampo(tcStr, 'xMotivo');
      (*ER07 *)FcUF      := leitor.rCampo(tcInt, 'cUF');

      if FcStat in  [100, 101] then
       begin
         if (Leitor.rExtrai(1, 'protCTe') <> '') or (Leitor.rExtrai(1, 'infProt') <> '') then
          begin
            protCTe.tpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
            protCTe.verAplic := Leitor.rCampo(tcStr, 'verAplic');
            protCTe.chCTe    := Leitor.rCampo(tcStr, 'chCTe');
            protCTe.dhRecbto := Leitor.rCampo(tcDatHor, 'dhRecbto');
            protCTe.nProt    := Leitor.rCampo(tcStr, 'nProt');
            protCTe.digVal   := Leitor.rCampo(tcStr, 'digVal');
            protCTe.cStat    := Leitor.rCampo(tcInt, 'cStat');
            protCTe.xMotivo  := Leitor.rCampo(tcStr, 'xMotivo');
            FchCTe           := protCTe.chCTe;
         end;
       end;

      if FcStat = 101 then
       begin
         if Leitor.rExtrai(1, 'infCanc') <> '' then
          begin
            retCancCTe.tpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
            retCancCTe.verAplic := Leitor.rCampo(tcStr, 'verAplic');
            retCancCTe.cStat    := Leitor.rCampo(tcInt, 'cStat');
            retCancCTe.xMotivo  := Leitor.rCampo(tcStr, 'xMotivo');
            retCancCTe.cUF      := Leitor.rCampo(tcInt, 'cUF');
            retCancCTe.chCTe    := Leitor.rCampo(tcStr, 'chCTe');
            retCancCTe.dhRecbto := Leitor.rCampo(tcDatHor, 'dhRecbto');
            retCancCTe.nProt    := Leitor.rCampo(tcStr, 'nProt');
            FchCTe              := retCancCTe.chCTe;
         end;
       end;

      if Assigned(procEventoCTe) then
        procEventoCTe.Free;

      procEventoCTe := TRetEventoCTeCollection.Create(Self);
      i := 0;
      while Leitor.rExtrai(1, 'procEventoCTe', '', i + 1) <> '' do
      begin
        procEventoCTe.Add;
        procEventoCTe.Items[i].RetEventoCTe.Leitor.Arquivo := Leitor.Grupo;
        procEventoCTe.Items[i].RetEventoCTe.LerXml;
        inc(i);
      end;
      if i = 0
       then procEventoCTe.Add;

      Result := True;
    end;
  except
    Result := False;
  end;
end;

{ TRetEventoCTeCollection }

function TRetEventoCTeCollection.Add: TRetEventoCTeCollectionItem;
begin
  Result := TRetEventoCTeCollectionItem(inherited Add);
  Result.create;
end;

constructor TRetEventoCTeCollection.Create(AOwner: TPersistent);
begin
  inherited Create(TRetEventoCTeCollectionItem);
end;

function TRetEventoCTeCollection.GetItem(Index: Integer): TRetEventoCTeCollectionItem;
begin
  Result := TRetEventoCTeCollectionItem(inherited GetItem(Index));
end;

procedure TRetEventoCTeCollection.SetItem(Index: Integer; Value: TRetEventoCTeCollectionItem);
begin
  inherited SetItem(Index, Value);
end;

{ TRetEventoCTeCollectionItem }

constructor TRetEventoCTeCollectionItem.Create;
begin
  FRetEventoCTe := TRetEventoCTe.Create;
end;

destructor TRetEventoCTeCollectionItem.Destroy;
begin
  FRetEventoCTe.Free;
  inherited;
end;

end.

