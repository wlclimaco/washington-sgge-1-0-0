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

unit pcteEnvEventoCTe;

interface

uses
  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao, pcnGerador, pcnLeitor, pcteEventoCTe;

type
  TInfEventoCollection     = class;
  TInfEventoCollectionItem = class;
  TEventoCTe               = class;
  EventoCTeException       = class(Exception);

  TInfEventoCollection = class(TCollection)
  private
    function GetItem(Index: Integer): TInfEventoCollectionItem;
    procedure SetItem(Index: Integer; Value: TInfEventoCollectionItem);
  public
    constructor Create(AOwner: TPersistent);
    function Add: TInfEventoCollectionItem;
    property Items[Index: Integer]: TInfEventoCollectionItem read GetItem write SetItem; default;
  end;

  TInfEventoCollectionItem = class(TCollectionItem)
  private
    FInfEvento: TInfEvento;
    FRetInfEvento: TRetInfEvento;
  public
    constructor Create; reintroduce;
    destructor Destroy; override;
  published
    property InfEvento: TInfEvento       read FInfEvento    write FInfEvento;
    property RetInfEvento: TRetInfEvento read FRetInfEvento write FRetInfEvento;
  end;

  TEventoCTe = class(TPersistent)
  private
    FGerador: TGerador;
    FidLote: Integer;
    FEvento: TInfEventoCollection;
    procedure SetEvento(const Value: TInfEventoCollection);
  public
    constructor Create;
    destructor Destroy; override;
    function GerarXML: boolean;
    function LerXML(CaminhoArquivo: string): boolean;
    function ObterNomeArquivo(tpEvento: TpcnTpEvento): string;
  published
    property Gerador: TGerador             read FGerador write FGerador;
    property idLote: Integer               read FidLote  write FidLote;
    property Evento: TInfEventoCollection  read FEvento  write SetEvento;
  end;

implementation

uses
 pcteRetEnvEventoCTe;

{ TEventoCTe }

constructor TEventoCTe.Create;
begin
  FGerador := TGerador.Create;
  FEvento  := TInfEventoCollection.Create(Self);
end;

destructor TEventoCTe.Destroy;
begin
  FGerador.Free;
  FEvento.Free;
  inherited;
end;

function TEventoCTe.ObterNomeArquivo(tpEvento: TpcnTpEvento): string;
begin
 case tpEvento of
    teCCe         : Result := IntToStr(Self.idLote) + '-cce.xml';
    teCancelamento: Result := Evento.Items[0].InfEvento.chCTe + '-can-eve.xml';
    teEPEC        : Result := Evento.Items[0].InfEvento.chCTe + '-ped-epec.xml';
  else
    raise EventoCTeException.Create('Obter nome do arquivo de Evento n�o Implementado!');
 end;
end;

function TEventoCTe.GerarXML: boolean;
var
  sDoc : String;
begin
  Gerador.ArquivoFormatoXML := '';
  Gerador.wGrupo('eventoCTe ' + NAME_SPACE_CTE + ' versao="' + CTeEventoCTe + '"');

  Evento.Items[0].InfEvento.id := 'ID'+ Evento.Items[0].InfEvento.TipoEvento +
                                        SomenteNumeros(Evento.Items[0].InfEvento.chCTe) +
                                        Format('%.2d', [Evento.Items[0].InfEvento.nSeqEvento]);

  Gerador.wGrupo('infEvento Id="' + Evento.Items[0].InfEvento.id + '"');
  if Length(Evento.Items[0].InfEvento.id) < 54
   then Gerador.wAlerta('EP04', 'ID', '', 'ID de Evento inv�lido');

  Gerador.wCampo(tcInt, 'EP05', 'cOrgao', 1, 2, 1, Evento.Items[0].InfEvento.cOrgao);
  Gerador.wCampo(tcStr, 'EP06', 'tpAmb ', 1, 1, 1, TpAmbToStr(Evento.Items[0].InfEvento.tpAmb), DSC_TPAMB);

  sDoc := SomenteNumeros( Evento.Items[0].InfEvento.CNPJ );

  case Length( sDoc ) of
    14 : begin
          Gerador.wCampo(tcStr, 'EP07', 'CNPJ', 14, 14, 1, sDoc , DSC_CNPJ);
          if not ValidarCNPJ( sDoc ) then Gerador.wAlerta('HP10', 'CNPJ', DSC_CNPJ, ERR_MSG_INVALIDO);
         end;
    11 : begin
          Gerador.wCampo(tcStr, 'EP07', 'CPF ', 11, 11, 1, sDoc, DSC_CPF);
          if not ValidarCPF( sDoc ) then Gerador.wAlerta('HP11', 'CPF', DSC_CPF, ERR_MSG_INVALIDO);
         end;
  end;

  Gerador.wCampo(tcStr, 'EP08', 'chCTe', 44, 44, 1, Evento.Items[0].InfEvento.chCTe, DSC_CHAVE);

  if not ValidarChave('NFe' + SomenteNumeros(Evento.Items[0].InfEvento.chCTe))
   then Gerador.wAlerta('EP08', 'chCTe', '', 'Chave de CTe inv�lida');
  {
  Gerador.wCampo(tcStr, 'EP09', 'dhEvento', 01, 27,   1, FormatDateTime('yyyy-mm-dd"T"hh:nn:ss',Evento.Items[0].InfEvento.dhEvento)
                                                           + GetUTC( CodigoParaUF(Evento.Items[0].InfEvento.cOrgao),
                                                                     Evento.Items[0].InfEvento.dhEvento) );
  }
  // Alterado por Italo em 23/04/2013 conforme NT 2013/003
  Gerador.wCampo(tcStr, 'EP09', 'dhEvento  ', 01, 27, 1, FormatDateTime('yyyy-mm-dd"T"hh:nn:ss',Evento.Items[0].InfEvento.dhEvento));
  Gerador.wCampo(tcInt, 'EP10', 'tpEvento  ', 06, 06, 1, Evento.Items[0].InfEvento.TipoEvento);
  Gerador.wCampo(tcInt, 'EP11', 'nSeqEvento', 01, 02, 1, Evento.Items[0].InfEvento.nSeqEvento);

  Gerador.wGrupo('detEvento versaoEvento="' + CTeEventoCTe + '"');
  case Evento.Items[0].InfEvento.tpEvento of
   teCancelamento:
     begin
       Gerador.wGrupo('evCancCTe');
       Gerador.wCampo(tcStr, 'EP02', 'descEvento', 005, 012, 1, Evento.Items[0].InfEvento.DescEvento);
       Gerador.wCampo(tcStr, 'EP03', 'nProt     ', 015, 015, 1, Evento.Items[0].InfEvento.detEvento.nProt);
       Gerador.wCampo(tcStr, 'EP04', 'xJust     ', 015, 255, 1, Evento.Items[0].InfEvento.detEvento.xJust);
       Gerador.wGrupo('/evCancCTe');
     end;
   teEPEC:
     begin
       Gerador.wGrupo('evEPECCTe');
       Gerador.wCampo(tcStr, 'EP02', 'descEvento', 05, 12, 1, Evento.Items[0].InfEvento.DescEvento);
       Gerador.wCampo(tcStr, 'EP04', 'xJust     ', 015, 255, 1, Evento.Items[0].InfEvento.detEvento.xJust);
       Gerador.wCampo(tcDe2, 'EP05', 'vICMS     ', 01, 15, 1, Evento.Items[0].InfEvento.detEvento.vICMS, DSC_VICMS);
       Gerador.wCampo(tcDe2, 'EP06', 'vTPrest   ', 01, 15, 1, Evento.Items[0].InfEvento.detEvento.vTPrest, DSC_VTPREST);
       Gerador.wCampo(tcDe2, 'EP07', 'vCarga    ', 01, 15, 1, Evento.Items[0].InfEvento.detEvento.vCarga, DSC_VTMERC);

       Gerador.wGrupo('toma04');
       Gerador.wCampo(tcStr, 'EP09', 'toma', 01, 01, 1, TpTomadorToStr(Evento.Items[0].InfEvento.detEvento.toma), DSC_TOMA);
       Gerador.wCampo(tcStr, 'EP10', 'UF  ', 02, 02, 1, Evento.Items[0].InfEvento.detEvento.UF, DSC_UF);
       Gerador.wCampoCNPJCPF('EP11', 'EP12', Evento.Items[0].InfEvento.detEvento.CNPJCPF, CODIGO_BRASIL);
       Gerador.wCampo(tcStr, 'EP13', 'IE  ', 02, 14, 1, SomenteNumeros(Evento.Items[0].InfEvento.detEvento.IE), DSC_IE);
       Gerador.wGrupo('/toma04');

       Gerador.wCampo(tcStr, 'EP14', 'modal   ', 02, 02, 1, TpModalToStr(Evento.Items[0].InfEvento.detEvento.modal), DSC_MODAL);
       Gerador.wCampo(tcStr, 'EP15', 'UFIni   ', 02, 02, 1, Evento.Items[0].InfEvento.detEvento.UFIni, DSC_UF);
       if not ValidarUF(Evento.Items[0].InfEvento.detEvento.UFIni) then
        Gerador.wAlerta('EP15', 'UFIni', DSC_UF, ERR_MSG_INVALIDO);
       Gerador.wCampo(tcStr, 'EP16', 'UFFim   ', 02, 02, 1, Evento.Items[0].InfEvento.detEvento.UFFim, DSC_UF);
       if not ValidarUF(Evento.Items[0].InfEvento.detEvento.UFFim) then
        Gerador.wAlerta('EP16', 'UFFim', DSC_UF, ERR_MSG_INVALIDO);
       Gerador.wGrupo('/evEPECCTe');
     end;
  end;
  Gerador.wGrupo('/detEvento');
  Gerador.wGrupo('/infEvento');
  Gerador.wGrupo('/eventoCTe');

  Result := (Gerador.ListaDeAlertas.Count = 0);
end;

procedure TEventoCTe.SetEvento(const Value: TInfEventoCollection);
begin
  FEvento.Assign(Value);
end;

function TEventoCTe.LerXML(CaminhoArquivo: string): boolean;
var
  ArqEvento    : TStringList;
  RetEventoCTe : TRetEventoCTe;
begin
  ArqEvento := TStringList.Create;
  RetEventoCTe := TRetEventoCTe.Create;
  Result := False;
  try
     ArqEvento.LoadFromFile(CaminhoArquivo);
     RetEventoCTe.Leitor.Arquivo := ArqEvento.Text;
     RetEventoCTe.LerXml;
     with FEvento.Add do
      begin
         infEvento.ID         := RetEventoCTe.InfEvento.id;
         InfEvento.cOrgao     := RetEventoCTe.InfEvento.cOrgao;
         infEvento.tpAmb      := RetEventoCTe.InfEvento.tpAmb;
         infEvento.CNPJ       := RetEventoCTe.InfEvento.CNPJ;
         infEvento.chCTe      := RetEventoCTe.InfEvento.chCTe;
         infEvento.dhEvento   := RetEventoCTe.InfEvento.dhEvento;
         infEvento.tpEvento   := RetEventoCTe.InfEvento.tpEvento;
         infEvento.nSeqEvento := RetEventoCTe.InfEvento.nSeqEvento;

         infEvento.VersaoEvento         := RetEventoCTe.InfEvento.VersaoEvento;
         infEvento.detEvento.descEvento := RetEventoCTe.InfEvento.detEvento.descEvento;
         infEvento.detEvento.nProt      := RetEventoCTe.InfEvento.detEvento.nProt;
         infEvento.detEvento.xJust      := RetEventoCTe.InfEvento.DetEvento.xJust;
         infEvento.detEvento.vICMS      := RetEventoCTe.InfEvento.DetEvento.vICMS;
         infEvento.detEvento.vTPrest    := RetEventoCTe.InfEvento.DetEvento.vTPrest;
         infEvento.detEvento.vCarga     := RetEventoCTe.InfEvento.DetEvento.vCarga;
         infEvento.detEvento.toma       := RetEventoCTe.InfEvento.DetEvento.toma;
         infEvento.detEvento.UF         := RetEventoCTe.InfEvento.DetEvento.UF;
         infEvento.detEvento.CNPJCPF    := RetEventoCTe.InfEvento.DetEvento.CNPJCPF;
         infEvento.detEvento.IE         := RetEventoCTe.InfEvento.DetEvento.IE;
         infEvento.detEvento.modal      := RetEventoCTe.InfEvento.DetEvento.modal;
         infEvento.detEvento.UFIni      := RetEventoCTe.InfEvento.DetEvento.UFIni;
         infEvento.detEvento.UFFim      := RetEventoCTe.InfEvento.DetEvento.UFFim;

        if RetEventoCTe.retEvento.Count > 0 then
         begin
           FRetInfEvento.Id          := RetEventoCTe.retEvento.Items[0].RetInfEvento.Id;
           FRetInfEvento.tpAmb       := RetEventoCTe.retEvento.Items[0].RetInfEvento.tpAmb;
           FRetInfEvento.verAplic    := RetEventoCTe.retEvento.Items[0].RetInfEvento.verAplic;
           FRetInfEvento.cOrgao      := RetEventoCTe.retEvento.Items[0].RetInfEvento.cOrgao;
           FRetInfEvento.cStat       := RetEventoCTe.retEvento.Items[0].RetInfEvento.cStat;
           FRetInfEvento.xMotivo     := RetEventoCTe.retEvento.Items[0].RetInfEvento.xMotivo;
           FRetInfEvento.chCTe       := RetEventoCTe.retEvento.Items[0].RetInfEvento.chCTe;
           FRetInfEvento.tpEvento    := RetEventoCTe.retEvento.Items[0].RetInfEvento.tpEvento;
           FRetInfEvento.xEvento     := RetEventoCTe.retEvento.Items[0].RetInfEvento.xEvento;
           FRetInfEvento.nSeqEvento  := RetEventoCTe.retEvento.Items[0].RetInfEvento.nSeqEvento;
           FRetInfEvento.CNPJDest    := RetEventoCTe.retEvento.Items[0].RetInfEvento.CNPJDest;
           FRetInfEvento.emailDest   := RetEventoCTe.retEvento.Items[0].RetInfEvento.emailDest;
           FRetInfEvento.dhRegEvento := RetEventoCTe.retEvento.Items[0].RetInfEvento.dhRegEvento;
           FRetInfEvento.nProt       := RetEventoCTe.retEvento.Items[0].RetInfEvento.nProt;
         end;
      end;
     Result := True;
  finally
     ArqEvento.Free;
     RetEventoCTe.Free;
  end;
end;

{ TInfEventoCollection }

function TInfEventoCollection.Add: TInfEventoCollectionItem;
begin
  Result := TInfEventoCollectionItem(inherited Add);
  Result.create;
end;

constructor TInfEventoCollection.Create(AOwner: TPersistent);
begin
  inherited Create(TInfEventoCollectionItem);
end;

function TInfEventoCollection.GetItem(
  Index: Integer): TInfEventoCollectionItem;
begin
  Result := TInfEventoCollectionItem(inherited GetItem(Index));
end;

procedure TInfEventoCollection.SetItem(Index: Integer;
  Value: TInfEventoCollectionItem);
begin
  inherited SetItem(Index, Value);
end;

{ TInfEventoCollectionItem }

constructor TInfEventoCollectionItem.Create;
begin
  FInfEvento := TInfEvento.Create;
  FRetInfEvento := TRetInfEvento.Create;
end;

destructor TInfEventoCollectionItem.Destroy;
begin
  FInfEvento.Free;
  FRetInfEvento.Free;
  inherited;
end;

end.
