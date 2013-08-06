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
unit pcnRetConsNFeDest;

interface uses

  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao, pcnLeitor{, pcnEnvEventoNFe};

type
  TresNFe = class ;
  TresCanc = class ;
  TresCCe = class ;
  TRetCollection  = class ;
  TRetCollectionItem = class ;
  TRetConsNFeDest = class ;

  TresNFe = class
  private
    FdhRecbto: TDateTime;
    FdEmi: TDateTime;
    FtpNF: TpcnTipoNFe;
    FdigVal: string;
    FchNFe: string;
    FvNF: double;
    FxNome: string;
    FCNPJCPF: string;
    FIE: string;
    FcSitConf: TpcnSituacaoManifDest;
    FNSU: string;
    FcSitNFe: TpcnSituacaoNFe;
  public
    property NSU: string read FNSU write FNSU;
    property chNFe: string read FchNFe write FchNFe;
    property CNPJCPF: string read FCNPJCPF write FCNPJCPF;
    property xNome: string read FxNome write FxNome;
    property IE: string read FIE write FIE;
    property dEmi: TDateTime read FdEmi write FdEmi;
    property tpNF: TpcnTipoNFe read FtpNF write FtpNF;
    property vNF: double read FvNF write FvNF;
    property digVal: string read FdigVal write FdigVal;
    property dhRecbto: TDateTime read FdhRecbto write FdhRecbto;
    property cSitNFe: TpcnSituacaoNFe read FcSitNFe write FcSitNFe;
    property cSitConf: TpcnSituacaoManifDest read FcSitConf write FcSitConf;
  end;

  TresCanc = class
  private
    FdhRecbto: TDateTime;
    FdEmi: TDateTime;
    FtpNF: TpcnTipoNFe;
    FdigVal: string;
    FchNFe: string;
    FvNF: double;
    FxNome: string;
    FCNPJCPF: string;
    FIE: string;
    FcSitConf: TpcnSituacaoManifDest;
    FNSU: string;
    FcSitNFe: TpcnSituacaoNFe;
  public
    property NSU: string read FNSU write FNSU;
    property chNFe: string read FchNFe write FchNFe;
    property CNPJCPF: string read FCNPJCPF write FCNPJCPF;
    property xNome: string read FxNome write FxNome;
    property IE: string read FIE write FIE;
    property dEmi: TDateTime read FdEmi write FdEmi;
    property tpNF: TpcnTipoNFe read FtpNF write FtpNF;
    property vNF: double read FvNF write FvNF;
    property digVal: string read FdigVal write FdigVal;
    property dhRecbto: TDateTime read FdhRecbto write FdhRecbto;
    property cSitNFe: TpcnSituacaoNFe read FcSitNFe write FcSitNFe;
    property cSitConf: TpcnSituacaoManifDest read FcSitConf write FcSitConf;
  end;

  TresCCe = class
  private
    FdhRecbto: TDateTime;
    FtpNF: TpcnTipoNFe;
    FchNFe: string;
    FdhEvento: TDateTime;
    FxCorrecao: string;
    FtpEvento: TpcnTpEvento;
    FdescEvento: string;
    FNSU: string;
    FnSeqEvento: ShortInt;
  public
    property NSU: string read FNSU write FNSU;
    property chNFe: string read FchNFe write FchNFe;
    property dhEvento: TDateTime read FdhEvento write FdhEvento;
    property tpEvento: TpcnTpEvento read FtpEvento write FtpEvento;
    property nSeqEvento: ShortInt read FnSeqEvento write FnSeqEvento;
    property descEvento: string read FdescEvento write FdescEvento;
    property xCorrecao: string read FxCorrecao write FxCorrecao;
    property tpNF: TpcnTipoNFe read FtpNF write FtpNF;
    property dhRecbto: TDateTime read FdhRecbto write FdhRecbto;
  end;

  TRetCollection = class(TCollection)
  private
    function GetItem(Index: Integer): TRetCollectionItem;
    procedure SetItem(Index: Integer; Value: TRetCollectionItem);
  public
    constructor Create(AOwner: TPersistent);
    function Add: TRetCollectionItem;
    property Items[Index: Integer]: TRetCollectionItem read GetItem write SetItem; default;
  end;

  TRetCollectionItem = class(TCollectionItem)
  private
    FresNFe: TresNFe;
    FresCanc: TresCanc;
    FresCCe: TresCCe;
  public
    constructor Create; reintroduce;
    destructor Destroy; override;
  published
    property resNFe: TresNFe read FresNFe write FresNFe;
    property resCanc: TresCanc read FresCanc write FresCanc;
    property resCCe: TresCCe read FresCCe write FresCCe;
  end;

  TRetConsNFeDest = class(TPersistent)
  private
    Fversao : string;
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: string;
    FLeitor: TLeitor;
    FcStat: integer;
    FdhResp: TDateTime;
    FxMotivo: string;
    FindCont: TpcnIndicadorContinuacao;
    FultNSU: string;
    Fret: TRetCollection;
    FXML: AnsiString;
    procedure Setret(const Value: TRetCollection);
  public
    constructor Create;
    destructor Destroy; override;
    function LerXml: boolean;
  published
    property versao: string read Fversao write Fversao;
    property Leitor: TLeitor read FLeitor write FLeitor;
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property verAplic: string read FverAplic write FverAplic;
    property cStat: integer read FcStat write FcStat;
    property xMotivo: string read FxMotivo write FxMotivo;
    property dhResp: TDateTime read FdhResp write FdhResp;
    property indCont: TpcnIndicadorContinuacao read FindCont write FindCont;
    property ultNSU: string read FultNSU write FultNSU;
    property ret: TRetCollection read Fret write Setret;
    property XML: AnsiString read FXML write FXML;
  end;


implementation

{ TRetCollection }

function TRetCollection.Add: TRetCollectionItem;
begin
  Result := TRetCollectionItem(inherited Add);
  Result.create;
end;

constructor TRetCollection.Create(AOwner: TPersistent);
begin
  inherited Create(TRetCollectionItem);
end;

function TRetCollection.GetItem(
  Index: Integer): TRetCollectionItem;
begin
  Result := TRetCollectionItem(inherited GetItem(Index));
end;

procedure TRetCollection.SetItem(Index: Integer;
  Value: TRetCollectionItem);
begin
  inherited SetItem(Index, Value);
end;

{ TRetCollectionItem }

constructor TRetCollectionItem.Create;
begin
  FresNFe := TresNFe.Create;
  FresCanc := TresCanc.Create;
  FresCCe := TresCCe.Create;
end;

destructor TRetCollectionItem.Destroy;
begin
  FresNFe.Free;
  FresCanc.Free;
  FresCCe.Free;
  inherited;
end;

{ TRetConsNFeDest }
procedure TRetConsNFeDest.Setret(const Value: TRetCollection);
begin
  Fret.Assign(Value);
end;

constructor TRetConsNFeDest.Create;
begin
  FLeitor := TLeitor.Create;
  Fret := TRetCollection.Create(Self);
end;

destructor TRetConsNFeDest.Destroy;
begin
  FLeitor.Free;
  Fret.Free;
  inherited;
end;

function TRetConsNFeDest.LerXml: boolean;
var
  ok: boolean;
  i : integer;
begin
  Result := False;
  try
    FXML := Self.Leitor.Arquivo;
    if (Leitor.rExtrai(1, 'retConsNFeDest') <> '') then
    begin
      (*IR02 *)Fversao   := Leitor.rAtributo('versao');
//      (*IR02 *)Fversao   := Leitor.rCampo(tcStr, 'versao');
      (*IR03 *)FtpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
      (*IR04 *)FverAplic := Leitor.rCampo(tcStr, 'verAplic');
      (*IR05 *)FcStat    := Leitor.rCampo(tcInt, 'cStat');
      (*IR06 *)FxMotivo  := Leitor.rCampo(tcStr, 'xMotivo');
      (*IR07 *)FdhResp   := Leitor.rCampo(tcDatHor, 'dhResp');
      (*IR08 *)FindCont  := StrToIndicadorContinuacao(ok, Leitor.rCampo(tcStr, 'indCont'));
      (*IR09 *)FultNSU   := Leitor.rCampo(tcStr, 'ultNSU');
      i := 0;
      while Leitor.rExtrai(2, 'ret', '', i + 1) <> '' do
      begin
        Fret.Add;

        if (Leitor.rExtrai(3, 'resNFe') <> '') then
        begin
          (*IR12 *)Fret.Items[i].FresNFe.FNSU       := Leitor.rAtributo('NSU');
//          (*IR12 *)Fret.Items[i].FresNFe.FNSU       := Leitor.rCampo(tcStr, 'NSU');
          (*IR13 *)Fret.Items[i].FresNFe.chNFe      := Leitor.rCampo(tcStr, 'chNFe');
          (*IR14 *)Fret.Items[i].FresNFe.FCNPJCPF   := Leitor.rCampo(tcStr, 'CNPJ');
          if Fret.Items[i].FresNFe.FCNPJCPF = '' then
            (*IR15 *)Fret.Items[i].FresNFe.FCNPJCPF := Leitor.rCampo(tcStr, 'CPF');
          (*IR16 *)Fret.Items[i].FresNFe.FxNome     := Leitor.rCampo(tcStr, 'xNome');
          (*IR17 *)Fret.Items[i].FresNFe.FIE        := Leitor.rCampo(tcStr, 'IE');
          (*IR18 *)Fret.Items[i].FresNFe.FdEmi      := Leitor.rCampo(tcDat, 'dEmi');
          (*IR19 *)Fret.Items[i].FresNFe.FtpNF      := StrToTpNF(ok, Leitor.rCampo(tcStr, 'tpNF'));
          (*IR20 *)Fret.Items[i].FresNFe.FvNF       := Leitor.rCampo(tcDe2, 'vNF');
          (*IR21 *)Fret.Items[i].FresNFe.FdigVal    := Leitor.rCampo(tcStr, 'digVal');
          (*IR22 *)Fret.Items[i].FresNFe.FdhRecbto  := Leitor.rCampo(tcDatHor, 'dhRecbto');
          (*IR23 *)Fret.Items[i].FresNFe.FcSitNFe   := StrToSituacaoNFe(ok, Leitor.rCampo(tcStr, 'cSitNFe'));
          (*IR24 *)Fret.Items[i].FresNFe.FcSitConf  := StrToSituacaoManifDest(ok, Leitor.rCampo(tcStr, 'cSitConf'));
        end;

        if (Leitor.rExtrai(3, 'resCanc') <> '') then
        begin
          (*IR26 *)Fret.Items[i].FresCanc.FNSU       := Leitor.rAtributo('NSU');
          (*IR27 *)Fret.Items[i].FresCanc.chNFe      := Leitor.rCampo(tcStr, 'chNFe');
          (*IR28 *)Fret.Items[i].FresCanc.FCNPJCPF   := Leitor.rCampo(tcStr, 'CNPJ');
          if Fret.Items[i].FresCanc.FCNPJCPF = '' then
            (*IR29 *)Fret.Items[i].FresCanc.FCNPJCPF := Leitor.rCampo(tcStr, 'CPF');
          (*IR30 *)Fret.Items[i].FresCanc.FxNome     := Leitor.rCampo(tcStr, 'xNome');
          (*IR31 *)Fret.Items[i].FresCanc.FIE        := Leitor.rCampo(tcStr, 'IE');
          (*IR32 *)Fret.Items[i].FresCanc.FdEmi      := Leitor.rCampo(tcDat, 'dEmi');
          (*IR33 *)Fret.Items[i].FresCanc.FtpNF      := StrToTpNF(ok, Leitor.rCampo(tcStr, 'tpNF'));
          (*IR34 *)Fret.Items[i].FresCanc.FvNF       := Leitor.rCampo(tcDe2, 'vNF');
          (*IR35 *)Fret.Items[i].FresCanc.FdigVal    := Leitor.rCampo(tcStr, 'digVal');
          (*IR36 *)Fret.Items[i].FresCanc.FdhRecbto  := Leitor.rCampo(tcDatHor, 'dhRecbto');
          (*IR37 *)Fret.Items[i].FresCanc.FcSitNFe   := StrToSituacaoNFe(ok, Leitor.rCampo(tcStr, 'cSitNFe'));
          (*IR38 *)Fret.Items[i].FresCanc.FcSitConf  := StrToSituacaoManifDest(ok, Leitor.rCampo(tcStr, 'cSitConf'));
        end;

        if (Leitor.rExtrai(3, 'resCCe') <> '') then
        begin
          (*IR40 *)Fret.Items[i].FresCCe.FNSU        := Leitor.rAtributo('NSU');
//          (*IR40 *)Fret.Items[i].FresCCe.FNSU        := Leitor.rCampo(tcStr, 'NSU');
          (*IR41 *)Fret.Items[i].FresCCe.chNFe       := Leitor.rCampo(tcStr, 'chNFe');
          (*IR42 *)Fret.Items[i].FresCCe.FdhEvento   := Leitor.rCampo(tcDatHor, 'dhEvento');
          (*IR43 *)Fret.Items[i].FresCCe.FtpEvento   := StrToTpEvento(ok, Leitor.rCampo(tcStr, 'tpEvento'));
          (*IR44 *)Fret.Items[i].FresCCe.FnSeqEvento := Leitor.rCampo(tcInt, 'nSeqEvento');
          (*IR45 *)Fret.Items[i].FresCCe.FdescEvento := Leitor.rCampo(tcStr, 'descEvento');
          (*IR46 *)Fret.Items[i].FresCCe.FxCorrecao  := Leitor.rCampo(tcStr, 'xCorrecao');
          (*IR47 *)Fret.Items[i].FresCCe.FtpNF       := StrToTpNF(ok, Leitor.rCampo(tcStr, 'tpNF'));
          (*IR48 *)Fret.Items[i].FresCCe.FdhRecbto   := Leitor.rCampo(tcDatHor, 'dhRecbto');
        end;

        inc(i);
      end;

      if i = 0
       then Fret.Add;

      Result := True;
    end;
  except
    result := False;
  end;
end;

end.
