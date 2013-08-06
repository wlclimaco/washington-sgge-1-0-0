{******************************************************************************}
{ Projeto: Componente ACBrMDFe                                                 }
{  Biblioteca multiplataforma de componentes Delphi                            }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do Projeto ACBr     }
{ Componentes localizado em http://www.sourceforge.net/projects/acbr           }
{                                                                              }
{                                                                              }
{  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la }
{ sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) }
{ qualquer vers�o posterior.                                                   }
{                                                                              }
{  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      }
{ ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto}
{ com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  }
{ no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Voc� tamb�m pode obter uma copia da licen�a em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 01/08/2012: Italo Jurisato Junior
|*  - Doa��o do componente para o Projeto ACBr
******************************************************************************}

{$I ACBr.inc}

unit pmdfeEnvEventoMDFe;

interface

uses
  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao, pcnGerador, pcnLeitor, pmdfeEventoMDFe;

type
  TInfEventoCollection     = class;
  TInfEventoCollectionItem = class;
  TEventoMDFe              = class;
  EventoException          = class(Exception);

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

  TEventoMDFe = class(TPersistent)
  private
    FGerador: TGerador;
    FidLote: Integer;
    FEvento: TInfEventoCollection;
    procedure SetEvento(const Value: TInfEventoCollection);
  public
    constructor Create;
    destructor Destroy; override;
    function GerarXML: boolean;
    function LerXML(const CaminhoArquivo: string): boolean;
    function LerXMLFromString(const AXML: String): boolean;
    function ObterNomeArquivo(tpEvento: TpcnTpEvento): string;
  published
    property Gerador: TGerador             read FGerador write FGerador;
    property idLote: Integer               read FidLote  write FidLote;
    property Evento: TInfEventoCollection  read FEvento  write SetEvento;
  end;

implementation

uses
 pmdfeRetEnvEventoMDFe;

{ TEventoMDFe }

constructor TEventoMDFe.Create;
begin
  FGerador   := TGerador.Create;
  FEvento    := TInfEventoCollection.Create(Self);
end;

destructor TEventoMDFe.Destroy;
begin
  FGerador.Free;
  FEvento.Free;
  inherited;
end;

function TEventoMDFe.ObterNomeArquivo(tpEvento: TpcnTpEvento): string;
begin
 case tpEvento of
    teCancelamento : Result := Evento.Items[0].InfEvento.chMDFe + '-can-eve.xml'; // Cancelamento da MDFe como Evento
    teEncerramento : Result := Evento.Items[0].InfEvento.chMDFe + '-ped-eve.xml'; // Encerramento
  else
    raise EventoException.Create('Obter nome do arquivo de Evento n�o Implementado!');
 end;
end;

function TEventoMDFe.GerarXML: boolean;
var
  sDoc : String;
begin
  Gerador.ArquivoFormatoXML := '';
  Gerador.wGrupo('eventoMDFe ' + NAME_SPACE_MDFE + ' versao="' + MDFeEventoMDFe + '"');

  Evento.Items[0].InfEvento.id := 'ID'+ Evento.Items[0].InfEvento.TipoEvento +
                                        SomenteNumeros(Evento.Items[0].InfEvento.chMDFe) +
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

  Gerador.wCampo(tcStr, 'EP08', 'chMDFe', 44, 44, 1, Evento.Items[0].InfEvento.chMDFe, DSC_CHAVE);

  if not ValidarChave('NFe' + SomenteNumeros(Evento.Items[0].InfEvento.chMDFe))
   then Gerador.wAlerta('EP08', 'chMDFe', '', 'Chave de MDFe inv�lida');

  // Segundo o manual a data deve conter o UTC mas no schema n�o contem
  Gerador.wCampo(tcStr, 'EP09', 'dhEvento', 01, 27,   1, FormatDateTime('yyyy-mm-dd"T"hh:nn:ss',Evento.Items[0].InfEvento.dhEvento)
                                                           {+ GetUTC( CodigoParaUF(Evento.Items[0].InfEvento.cOrgao),
                                                                     Evento.Items[0].InfEvento.dhEvento)} );

  Gerador.wCampo(tcInt, 'EP10', 'tpEvento  ', 6, 6, 1, Evento.Items[0].InfEvento.TipoEvento);
  Gerador.wCampo(tcInt, 'EP11', 'nSeqEvento', 1, 2, 1, Evento.Items[0].InfEvento.nSeqEvento);

  Gerador.wGrupo('detEvento versaoEvento="' + MDFeEventoMDFe + '"');
  case Evento.Items[0].InfEvento.tpEvento of
   teCancelamento:
     begin
       Gerador.wGrupo('evCancMDFe');
       Gerador.wCampo(tcStr, 'EP02', 'descEvento', 005, 012, 1, Evento.Items[0].InfEvento.DescEvento);
       Gerador.wCampo(tcStr, 'EP03', 'nProt     ', 015, 015, 1, Evento.Items[0].InfEvento.detEvento.nProt);
       Gerador.wCampo(tcStr, 'EP04', 'xJust     ', 015, 255, 1, Evento.Items[0].InfEvento.detEvento.xJust);
       Gerador.wGrupo('/evCancMDFe');
     end;
   teEncerramento:
     begin
       Gerador.wGrupo('evEncMDFe');
       Gerador.wCampo(tcStr, 'EP02', 'descEvento', 05, 12, 1, Evento.Items[0].InfEvento.DescEvento);
       Gerador.wCampo(tcStr, 'EP03', 'nProt     ', 15, 15, 1, Evento.Items[0].InfEvento.detEvento.nProt);
       Gerador.wCampo(tcDat, 'EP04', 'dtEnc     ', 10, 10, 1, Evento.Items[0].InfEvento.detEvento.dtEnc);
       Gerador.wCampo(tcInt, 'EP05', 'cUF       ', 02, 02, 1, Evento.Items[0].InfEvento.detEvento.cUF);
       Gerador.wCampo(tcInt, 'EP06', 'cMun      ', 07, 07, 1, Evento.Items[0].InfEvento.detEvento.cMun);
       Gerador.wGrupo('/evEncMDFe');
     end;
  end;
  Gerador.wGrupo('/detEvento');
  Gerador.wGrupo('/infEvento');
  Gerador.wGrupo('/eventoMDFe');

  Result := (Gerador.ListaDeAlertas.Count = 0);
end;

procedure TEventoMDFe.SetEvento(const Value: TInfEventoCollection);
begin
  FEvento.Assign(Value);
end;

function TEventoMDFe.LerXML(const CaminhoArquivo: string): boolean;
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

function TEventoMDFe.LerXMLFromString(const AXML: String): boolean;
var
  RetEventoMDFe : TRetEventoMDFe;
begin
  RetEventoMDFe := TRetEventoMDFe.Create;
  try
     RetEventoMDFe.Leitor.Arquivo := AXML;
     Result := RetEventoMDFe.LerXml;
     with FEvento.Add do
      begin
         infEvento.ID         := RetEventoMDFe.InfEvento.id;
         InfEvento.cOrgao     := RetEventoMDFe.InfEvento.cOrgao;
         infEvento.tpAmb      := RetEventoMDFe.InfEvento.tpAmb;
         infEvento.CNPJ       := RetEventoMDFe.InfEvento.CNPJ;
         infEvento.chMDFe     := RetEventoMDFe.InfEvento.chMDFe;
         infEvento.dhEvento   := RetEventoMDFe.InfEvento.dhEvento;
         infEvento.tpEvento   := RetEventoMDFe.InfEvento.tpEvento;
         infEvento.nSeqEvento := RetEventoMDFe.InfEvento.nSeqEvento;

         infEvento.VersaoEvento         := RetEventoMDFe.InfEvento.VersaoEvento;
         infEvento.detEvento.descEvento := RetEventoMDFe.InfEvento.detEvento.descEvento;
         infEvento.detEvento.nProt      := RetEventoMDFe.InfEvento.detEvento.nProt;
         infEvento.detEvento.dtEnc      := RetEventoMDFe.InfEvento.detEvento.dtEnc;
         infEvento.detEvento.cUF        := RetEventoMDFe.InfEvento.detEvento.cUF;
         infEvento.detEvento.cMun       := RetEventoMDFe.InfEvento.detEvento.cMun;
         infEvento.detEvento.xJust      := RetEventoMDFe.InfEvento.DetEvento.xJust;

        if RetEventoMDFe.retEvento.Count > 0 then
         begin
           FRetInfEvento.Id          := RetEventoMDFe.retEvento.Items[0].RetInfEvento.Id;
           FRetInfEvento.tpAmb       := RetEventoMDFe.retEvento.Items[0].RetInfEvento.tpAmb;
           FRetInfEvento.verAplic    := RetEventoMDFe.retEvento.Items[0].RetInfEvento.verAplic;
           FRetInfEvento.cOrgao      := RetEventoMDFe.retEvento.Items[0].RetInfEvento.cOrgao;
           FRetInfEvento.cStat       := RetEventoMDFe.retEvento.Items[0].RetInfEvento.cStat;
           FRetInfEvento.xMotivo     := RetEventoMDFe.retEvento.Items[0].RetInfEvento.xMotivo;
           FRetInfEvento.chMDFe      := RetEventoMDFe.retEvento.Items[0].RetInfEvento.chMDFe;
           FRetInfEvento.tpEvento    := RetEventoMDFe.retEvento.Items[0].RetInfEvento.tpEvento;
           FRetInfEvento.xEvento     := RetEventoMDFe.retEvento.Items[0].RetInfEvento.xEvento;
           FRetInfEvento.nSeqEvento  := RetEventoMDFe.retEvento.Items[0].RetInfEvento.nSeqEvento;
           FRetInfEvento.CNPJDest    := RetEventoMDFe.retEvento.Items[0].RetInfEvento.CNPJDest;
           FRetInfEvento.emailDest   := RetEventoMDFe.retEvento.Items[0].RetInfEvento.emailDest;
           FRetInfEvento.dhRegEvento := RetEventoMDFe.retEvento.Items[0].RetInfEvento.dhRegEvento;
           FRetInfEvento.nProt       := RetEventoMDFe.retEvento.Items[0].RetInfEvento.nProt;
         end;
      end;
  finally
     RetEventoMDFe.Free;
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
