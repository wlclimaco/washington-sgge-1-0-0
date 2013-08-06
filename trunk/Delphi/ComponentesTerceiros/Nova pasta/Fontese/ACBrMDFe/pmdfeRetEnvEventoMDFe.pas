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

unit pmdfeRetEnvEventoMDFe;

interface

uses
  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao, pcnLeitor, pmdfeEventoMDFe;

type
  TRetInfEventoCollection     = class;
  TRetInfEventoCollectionItem = class;
  TRetEventoMDFe              = class;

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

  TRetEventoMDFe = class(TPersistent)
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

function TRetInfEventoCollection.Add: TRetInfEventoCollectionItem;
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

{ TRetEventoMDFe }
constructor TRetEventoMDFe.Create;
begin
  FLeitor := TLeitor.Create;
  FretEvento := TRetInfEventoCollection.Create(Self);
  FInfEvento := TInfEvento.Create;
end;

destructor TRetEventoMDFe.Destroy;
begin
  FLeitor.Free;
  FretEvento.Free;
  FInfEvento.Free;
  inherited;
end;

function TRetEventoMDFe.LerXml: boolean;
var
  ok: boolean;
  i : integer;
begin
  Result := False;
  i := 0;
  
  try
    if (Leitor.rExtrai(1, 'eventoMDFe') <> '') then
    begin
      if Leitor.rExtrai(2, 'infEvento', '', i + 1) <> '' then
       begin
         infEvento.ID         := Leitor.rCampo(tcStr, 'Id');
         infEvento.cOrgao     := Leitor.rCampo(tcInt, 'cOrgao');
         infEvento.tpAmb      := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
         infEvento.CNPJ       := Leitor.rCampo(tcStr, 'CNPJ');
         infEvento.chMDFe     := Leitor.rCampo(tcStr, 'chMDFe');
         infEvento.dhEvento   := Leitor.rCampo(tcDatHor, 'dhEvento');
         infEvento.tpEvento   := StrToTpEvento(ok,Leitor.rCampo(tcStr, 'tpEvento'));
         infEvento.nSeqEvento := Leitor.rCampo(tcInt, 'nSeqEvento');

         if Leitor.rExtrai(3, 'detEvento', '', i + 1) <> '' then
         begin
           infEvento.VersaoEvento         := Leitor.rAtributo('versaoEvento');
           infEvento.detEvento.descEvento := Leitor.rCampo(tcStr, 'descEvento');
           infEvento.detEvento.nProt      := Leitor.rCampo(tcStr, 'nProt');
           infEvento.detEvento.dtEnc      := Leitor.rCampo(tcDat, 'dtEnc');
           infEvento.detEvento.cUF        := Leitor.rCampo(tcInt, 'cUF');
           infEvento.detEvento.cMun       := Leitor.rCampo(tcInt, 'cMun');
           infEvento.detEvento.xJust      := Leitor.rCampo(tcStr, 'xJust');
         end;
      end;
    end;

    if (Leitor.rExtrai(1, 'retEnvEvento') <> '') or
       (Leitor.rExtrai(1, 'retEventoMDFe') <> '') then
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

         // Os campos abaixos seram retornados caso o cStat = 135 ou 136
         FretEvento.Items[i].FRetInfEvento.chMDFe      := Leitor.rCampo(tcStr, 'chMDFe');
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
