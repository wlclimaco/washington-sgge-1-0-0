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
unit pcnEnvEventoNFe;

interface

uses SysUtils, Classes,
     {$IFNDEF VER130}
     Variants,
     {$ENDIF}
     pcnAuxiliar, pcnConversao, pcnGerador, pcnLeitor, pcnEventoNFe;

type
  TInfEventoCollection  = class ;
  TInfEventoCollectionItem = class ;
  TEventoNFe = class ;
  EventoException = class(Exception);

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
    property InfEvento: TInfEvento read FInfEvento write FInfEvento;
    property RetInfEvento: TRetInfEvento read FRetInfEvento write FRetInfEvento;
  end;

  TEventoNFe = class(TPersistent)
  private
    FGerador: TGerador;
    FSchema: TpcnSchema;
    FidLote: Integer;
    FEvento: TInfEventoCollection;
    FVersao: String;
    procedure SetEvento(const Value: TInfEventoCollection);
  public
    constructor Create;
    destructor Destroy; override;
    function GerarXML: boolean;
    function LerXML(const CaminhoArquivo: string): boolean;
    function LerXMLFromString(const AXML: String): boolean;
    function ObterNomeArquivo(tpEvento: TpcnTpEvento): string;
  published
    property Gerador: TGerador  read FGerador write FGerador;
    property schema: TpcnSchema read Fschema write Fschema;
    property idLote: Integer               read FidLote      write FidLote;
    property Evento: TInfEventoCollection  read FEvento      write SetEvento;
    property Versao: String                read FVersao      write FVersao;
  end;

implementation

uses pcnRetEnvEventoNFe ;

{ TEventoNFe }

constructor TEventoNFe.Create;
begin
  FGerador   := TGerador.Create;
  FEvento    := TInfEventoCollection.Create(Self);
end;

destructor TEventoNFe.Destroy;
begin
  FGerador.Free;
  FEvento.Free;
  inherited;
end;

function TEventoNFe.ObterNomeArquivo(tpEvento: TpcnTpEvento): string;
begin
 case tpEvento of
    teCCe                       : Result := IntToStr(Self.idLote) + '-cce.xml';     // Carta de Corre��o Eletr�nica
    teCancelamento              : Result := IntToStr(Self.idLote) + '-can-eve.xml'; // Cancelamento da NFe como Evento
    teManifDestCiencia,
    teManifDestConfirmacao,
    teManifDestDesconhecimento,
    teManifDestOperNaoRealizada : Result := IntToStr(Self.idLote) + '-man-des.xml'; // Manifesta��o do Destinat�rio
  else
    raise EventoException.Create('Obter nome do arquivo de Evento n�o Implementado!');
 end;
end;

function TEventoNFe.GerarXML: boolean;
var
  i     : integer;
  sDoc  : String;
begin
  Result := False;

//  if RetornarVersaoLayout(FSchema, tlCCeNFe) = '2.00' then
//  begin

    Gerador.ArquivoFormatoXML := '';
//    Gerador.wGrupo('envEvento ' + NAME_SPACE + ' ' + V1_00);
    Gerador.wGrupo('envEvento ' + NAME_SPACE + ' versao="' + Versao + '"');
    Gerador.wCampo(tcInt, 'HP03', 'idLote', 001, 015, 1, FidLote, DSC_IDLOTE);
    for i:= 0 to Evento.Count - 1 do
    begin
      Evento.Items[i].InfEvento.id := 'ID'+
                                        Evento.Items[i].InfEvento.TipoEvento +
                                        SomenteNumeros(Evento.Items[i].InfEvento.chNFe) +
                                        Format('%.2d', [Evento.Items[i].InfEvento.nSeqEvento]);

//      Gerador.wGrupo('evento ' + NAME_SPACE + ' ' + V1_00);
      Gerador.wGrupo('evento ' + NAME_SPACE + ' versao="' + Versao + '"');
      Gerador.wGrupo('infEvento Id="' + Evento.Items[i].InfEvento.id + '"');
      if Length(Evento.Items[i].InfEvento.id) < 54 then
          Gerador.wAlerta('HP07', 'ID', '', 'ID de Evento inv�lido');
      Gerador.wCampo(tcInt, 'HP08', 'cOrgao', 001, 002, 1, FEvento.Items[i].FInfEvento.cOrgao);
      Gerador.wCampo(tcStr, 'HP09', 'tpAmb', 001, 001,  1, TpAmbToStr(Evento.Items[i].InfEvento.tpAmb), DSC_TPAMB);

      // SomenteNumeros ..estava executando 5 vezes na versao anterior
      // no techo de verificar se era cnpj ou cpf.
      sDoc := SomenteNumeros( Evento.Items[i].InfEvento.CNPJ );
      case Length( sDoc ) of
        14 : begin
              Gerador.wCampo(tcStr, 'HP10', 'CNPJ', 014, 014, 1, sDoc , DSC_CNPJ);
              if not ValidarCNPJ( sDoc ) then Gerador.wAlerta('HP10', 'CNPJ', DSC_CNPJ, ERR_MSG_INVALIDO);
             end;
        11 : begin
              Gerador.wCampo(tcStr, 'HP11', 'CPF', 011, 011, 1, sDoc, DSC_CPF);
              if not ValidarCPF( sDoc ) then Gerador.wAlerta('HP11', 'CPF', DSC_CPF, ERR_MSG_INVALIDO);
             end;
      end;
      Gerador.wCampo(tcStr,    'HP12', 'chNFe', 044, 044,      1, Evento.Items[i].InfEvento.chNFe, DSC_CHAVE);
      if not ValidarChave('NFe' + SomenteNumeros(Evento.Items[i].InfEvento.chNFe)) then
        Gerador.wAlerta('HP12', 'chNFe', '', 'Chave de NFe inv�lida');
        Gerador.wCampo(tcStr,    'HP13', 'dhEvento', 001, 050,   1, FormatDateTime('yyyy-mm-dd"T"hh:nn:ss',Evento.Items[i].InfEvento.dhEvento)+
                                                                  GetUTC(CodigoParaUF(Evento.Items[i].InfEvento.cOrgao), Evento.Items[i].InfEvento.dhEvento));
      Gerador.wCampo(tcInt,    'HP14', 'tpEvento', 006, 006,   1, Evento.Items[i].InfEvento.TipoEvento);
      Gerador.wCampo(tcInt,    'HP15', 'nSeqEvento', 001, 002, 1, Evento.Items[i].InfEvento.nSeqEvento);
      Gerador.wCampo(tcStr,    'HP16', 'verEvento', 001, 004,  1, Evento.Items[i].InfEvento.versaoEvento);
      Gerador.wGrupo('detEvento versao="' +  Versao + '"');
      Gerador.wCampo(tcStr,    'HP19', 'descEvento', 005, 060, 1,  Evento.Items[i].InfEvento.DescEvento);
      case Evento.Items[i].InfEvento.tpEvento of
        teCCe:
          begin
            Gerador.wCampo(tcStr,    'HP20', 'xCorrecao', 015, 1000, 1,  Evento.Items[i].InfEvento.detEvento.xCorrecao);
            Gerador.wCampo(tcStr,    'HP20a', 'xCondUso', 001, 5000, 1,  Evento.Items[i].InfEvento.detEvento.xCondUso);
          end;
        teCancelamento:
          begin
            Gerador.wCampo(tcStr,    'HP20', 'nProt', 015, 015, 1,  Evento.Items[i].InfEvento.detEvento.nProt);
            Gerador.wCampo(tcStr,    'HP21', 'xJust', 015, 255, 1,  Evento.Items[i].InfEvento.detEvento.xJust);
          end;
        teManifDestOperNaoRealizada:
          begin
            Gerador.wCampo(tcStr,    'HP21', 'xJust', 015, 255, 1,  Evento.Items[i].InfEvento.detEvento.xJust);
          end;
      end;
      Gerador.wGrupo('/detEvento');
      Gerador.wGrupo('/infEvento');
      Gerador.wGrupo('/evento');
    end;
    Gerador.wGrupo('/envEvento');

    Result := (Gerador.ListaDeAlertas.Count = 0);

//  end;
end;

procedure TEventoNFe.SetEvento(const Value: TInfEventoCollection);
begin
  FEvento.Assign(Value);
end;

function TEventoNFe.LerXML(const CaminhoArquivo: string): boolean;
var
  ArqEvento    : TStringList;
begin
  ArqEvento := TStringList.Create;
  try
     ArqEvento.LoadFromFile(CaminhoArquivo);
     Result := LerXMLFromString(ArqEvento.Text);
  finally
     ArqEvento.Free;
  end;
end;

function TEventoNFe.LerXMLFromString(const AXML: String): boolean;
var
  RetEventoNFe : TRetEventoNFe;
begin
  RetEventoNFe := TRetEventoNFe.Create;
  try
     RetEventoNFe.Leitor.Arquivo := AXML;
     Result := RetEventoNFe.LerXml;
     with FEvento.Add do
      begin
         infEvento.ID            := RetEventoNFe.InfEvento.id;
         InfEvento.cOrgao        := RetEventoNFe.InfEvento.cOrgao;
         infEvento.tpAmb         := RetEventoNFe.InfEvento.tpAmb;
         infEvento.CNPJ          := RetEventoNFe.InfEvento.CNPJ;
         infEvento.chNFe         := RetEventoNFe.InfEvento.chNFe;
         infEvento.dhEvento      := RetEventoNFe.InfEvento.dhEvento;
         infEvento.tpEvento      := RetEventoNFe.InfEvento.tpEvento;
         infEvento.nSeqEvento    := RetEventoNFe.InfEvento.nSeqEvento;
         infEvento.VersaoEvento  := RetEventoNFe.InfEvento.VersaoEvento;

         infEvento.DetEvento.xCorrecao := RetEventoNFe.InfEvento.DetEvento.xCorrecao;
         infEvento.DetEvento.xCondUso  := RetEventoNFe.InfEvento.DetEvento.xCondUso;
         infEvento.DetEvento.nProt     := RetEventoNFe.InfEvento.DetEvento.nProt;
         infEvento.DetEvento.xJust     := RetEventoNFe.InfEvento.DetEvento.xJust;


        if RetEventoNFe.retEvento.Count > 0 then
         begin
           FRetInfEvento.Id := RetEventoNFe.retEvento.Items[0].RetInfEvento.Id;
           FRetInfEvento.tpAmb := RetEventoNFe.retEvento.Items[0].RetInfEvento.tpAmb;
           FRetInfEvento.verAplic := RetEventoNFe.retEvento.Items[0].RetInfEvento.verAplic;
           FRetInfEvento.cOrgao := RetEventoNFe.retEvento.Items[0].RetInfEvento.cOrgao;
           FRetInfEvento.cStat := RetEventoNFe.retEvento.Items[0].RetInfEvento.cStat;
           FRetInfEvento.xMotivo := RetEventoNFe.retEvento.Items[0].RetInfEvento.xMotivo;
           FRetInfEvento.chNFe := RetEventoNFe.retEvento.Items[0].RetInfEvento.chNFe;
           FRetInfEvento.tpEvento := RetEventoNFe.retEvento.Items[0].RetInfEvento.tpEvento;
           FRetInfEvento.xEvento := RetEventoNFe.retEvento.Items[0].RetInfEvento.xEvento;
           FRetInfEvento.nSeqEvento := RetEventoNFe.retEvento.Items[0].RetInfEvento.nSeqEvento;
           FRetInfEvento.CNPJDest := RetEventoNFe.retEvento.Items[0].RetInfEvento.CNPJDest;
           FRetInfEvento.emailDest := RetEventoNFe.retEvento.Items[0].RetInfEvento.emailDest;
           FRetInfEvento.dhRegEvento := RetEventoNFe.retEvento.Items[0].RetInfEvento.dhRegEvento;
           FRetInfEvento.nProt := RetEventoNFe.retEvento.Items[0].RetInfEvento.nProt;
         end;
      end;
  finally
     RetEventoNFe.Free;
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
