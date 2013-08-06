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

{$I ACBr.inc}

unit pcteRetEnvEventoCTe;

interface

uses
  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao, pcnLeitor, pcteEventoCTe;

type
  TRetInfEventoCollection     = class;
  TRetInfEventoCollectionItem = class;
  TRetEventoCTe               = class;

  TRetInfEventoCollection = class(TCollection)
  private
    function GetItem(Index: Integer): TRetInfEventoCollectionItem;
    procedure SetItem(Index: Integer; Value: TRetInfEventoCollectionItem);
  public
    constructor Create(AOwner: TPersistent);
    function Add: TRetInfEventoCollectionItem;
    property Items[Index: Integer]: TRetInfEventoCollectionItem read GetItem write SetItem; default;
  end;

  TRetInfEventoCollectionItem = class(TCollectionItem)
  private
    FRetInfEvento: TRetInfEvento;
  public
    constructor Create; reintroduce;
    destructor Destroy; override;
  published
    property RetInfEvento: TRetInfEvento read FRetInfEvento write FRetInfEvento;
  end;

  TRetEventoCTe = class(TPersistent)
  private
    FidLote : integer;
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: string;
    FLeitor: TLeitor;
    FcStat: integer;
    FcOrgao: Integer;
    FxMotivo: string;
    FretEvento: TRetInfEventoCollection;
    FInfEvento: TInfEvento;
  public
    constructor Create;
    destructor Destroy; override;
    function LerXml: boolean;
  published
    property idLote: integer                    read FidLote    write FidLote;
    property Leitor: TLeitor                    read FLeitor    write FLeitor;
    property tpAmb: TpcnTipoAmbiente            read FtpAmb     write FtpAmb;
    property verAplic: string                   read FverAplic  write FverAplic;
    property cOrgao: integer                    read FcOrgao    write FcOrgao;
    property cStat: integer                     read FcStat     write FcStat;
    property xMotivo: string                    read FxMotivo   write FxMotivo;
    property InfEvento: TInfEvento              read FInfEvento write FInfEvento;
    property retEvento: TRetInfEventoCollection read FretEvento write FretEvento;
  end;

implementation

{ TRetInfEventoCollection }

function TRetInfEventoCollection. Add: TRetInfEventoCollectionItem;
begin
  Result := TRetInfEventoCollectionItem(inherited Add);
  Result.create;
end;

constructor TRetInfEventoCollection.Create(AOwner: TPersistent);
begin
  inherited Create(TRetInfEventoCollectionItem);
end;

function TRetInfEventoCollection.GetItem(
  Index: Integer): TRetInfEventoCollectionItem;
begin
  Result := TRetInfEventoCollectionItem(inherited GetItem(Index));
end;

procedure TRetInfEventoCollection.SetItem(Index: Integer;
  Value: TRetInfEventoCollectionItem);
begin
  inherited SetItem(Index, Value);
end;

{ TRetInfEventoCollectionItem }

constructor TRetInfEventoCollectionItem.Create;
begin
  FRetInfEvento := TRetInfEvento.Create;
end;

destructor TRetInfEventoCollectionItem.Destroy;
begin
  FRetInfEvento.Free;
  inherited;
end;

{ TRetEventoCTe }
constructor TRetEventoCTe.Create;
begin
  FLeitor    := TLeitor.Create;
  FretEvento := TRetInfEventoCollection.Create(Self);
  FInfEvento := TInfEvento.Create;
end;

destructor TRetEventoCTe.Destroy;
begin
  FLeitor.Free;
  FretEvento.Free;
  FInfEvento.Free;
  inherited;
end;

function TRetEventoCTe.LerXml: boolean;
var
  ok: boolean;
  i : integer;
begin
  Result := False;
  i := 0;
  
  try
    if (Leitor.rExtrai(1, 'eventoCTe') <> '') then
    begin
      if Leitor.rExtrai(2, 'infEvento', '', i + 1) <> '' then
       begin
         infEvento.ID         := Leitor.rCampo(tcStr, 'Id');
         infEvento.cOrgao     := Leitor.rCampo(tcInt, 'cOrgao');
         infEvento.tpAmb      := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
         infEvento.CNPJ       := Leitor.rCampo(tcStr, 'CNPJ');
         infEvento.chCTe      := Leitor.rCampo(tcStr, 'chCTe');
         infEvento.dhEvento   := Leitor.rCampo(tcDatHor, 'dhEvento');
         infEvento.tpEvento   := StrToTpEvento(ok,Leitor.rCampo(tcStr, 'tpEvento'));
         infEvento.nSeqEvento := Leitor.rCampo(tcInt, 'nSeqEvento');

         if Leitor.rExtrai(3, 'detEvento', '', i + 1) <> '' then
         begin
           infEvento.VersaoEvento         := Leitor.rAtributo('versaoEvento');
           infEvento.detEvento.descEvento := Leitor.rCampo(tcStr, 'descEvento');
           infEvento.detEvento.nProt      := Leitor.rCampo(tcStr, 'nProt');
           infEvento.detEvento.xJust      := Leitor.rCampo(tcStr, 'xJust');
           infEvento.detEvento.vICMS      := Leitor.rCampo(tcDe2, 'vICMS');
           infEvento.detEvento.vTPrest    := Leitor.rCampo(tcDe2, 'vTPrest');
           infEvento.detEvento.vCarga     := Leitor.rCampo(tcDe2, 'vCarga');
           infEvento.detEvento.toma       := StrToTpTomador(ok, Leitor.rCampo(tcStr, 'toma'));
           infEvento.detEvento.UF         := Leitor.rCampo(tcStr, 'UF');
           infEvento.detEvento.CNPJCPF    := Leitor.rCampoCNPJCPF;
           infEvento.detEvento.IE         := Leitor.rCampo(tcStr, 'IE');
           infEvento.detEvento.modal      := StrToTpModal(ok, Leitor.rCampo(tcStr, 'modal'));
           infEvento.detEvento.UFIni      := Leitor.rCampo(tcStr, 'UFIni');
           infEvento.detEvento.UFFim      := Leitor.rCampo(tcStr, 'UFFim');
         end;
      end;
    end;

    if (Leitor.rExtrai(1, 'retEnvEvento') <> '') or
       (Leitor.rExtrai(1, 'retEventoCTe') <> '') then
    begin
      (*
      FidLote   := Leitor.rCampo(tcInt, 'idLote');
      FtpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
      FverAplic := Leitor.rCampo(tcStr, 'verAplic');
      FcOrgao   := Leitor.rCampo(tcInt, 'cOrgao');
      FcStat    := Leitor.rCampo(tcInt, 'cStat');
      FxMotivo  := Leitor.rCampo(tcStr, 'xMotivo');
      *)
      i := 0;
      while Leitor.rExtrai(2, 'infEvento', '', i + 1) <> '' do
       begin
         FretEvento.Add;
         FretEvento.Items[i].FRetInfEvento.Id       := Leitor.rAtributo('Id');
         FretEvento.Items[i].FRetInfEvento.tpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
         FretEvento.Items[i].FRetInfEvento.verAplic := Leitor.rCampo(tcStr, 'verAplic');
         FretEvento.Items[i].FRetInfEvento.cOrgao   := Leitor.rCampo(tcInt, 'cOrgao');
         FretEvento.Items[i].FRetInfEvento.cStat    := Leitor.rCampo(tcInt, 'cStat');
         FretEvento.Items[i].FRetInfEvento.xMotivo  := Leitor.rCampo(tcStr, 'xMotivo');

         // Os campos abaixos seram retornados caso o cStat = 134 ou 135 ou 136
         FretEvento.Items[i].FRetInfEvento.chCTe       := Leitor.rCampo(tcStr, 'chCTe');
         FretEvento.Items[i].FRetInfEvento.tpEvento    := StrToTpEvento(ok,Leitor.rCampo(tcStr, 'tpEvento'));
         FretEvento.Items[i].FRetInfEvento.xEvento     := Leitor.rCampo(tcStr, 'xEvento');
         FretEvento.Items[i].FRetInfEvento.nSeqEvento  := Leitor.rCampo(tcInt, 'nSeqEvento');
         FretEvento.Items[i].FRetInfEvento.dhRegEvento := Leitor.rCampo(tcDatHor, 'dhRegEvento');
         FretEvento.Items[i].FRetInfEvento.nProt       := Leitor.rCampo(tcStr, 'nProt');
         inc(i);
       end;

      if i = 0 then
        FretEvento.Add;

      Result := True;
    end;
  except
    result := False;
  end;
end;

end.
