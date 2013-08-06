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

unit pmdfeRetConsSitMDFe;

interface

uses
  SysUtils, Classes,
  pcnAuxiliar, pcnConversao, pcnLeitor,
  pmdfeProcMDFe, pmdfeRetEnvEventoMDFe;
//  , pmdfeRetCancMDFe;

type

  TRetEventoMDFeCollection = class;
  TRetEventoMDFeCollectionItem = class;
  TRetConsSitMDFe = class;

  TRetEventoMDFeCollection = class(TCollection)
  private
    function GetItem(Index: Integer): TRetEventoMDFeCollectionItem;
    procedure SetItem(Index: Integer; Value: TRetEventoMDFeCollectionItem);
  public
    constructor Create(AOwner: TPersistent);
    function Add: TRetEventoMDFeCollectionItem;
    property Items[Index: Integer]: TRetEventoMDFeCollectionItem read GetItem write SetItem; default;
  end;

  TRetEventoMDFeCollectionItem = class(TCollectionItem)
  private
    FRetEventoMDFe: TRetEventoMDFe;
  public
    constructor Create; reintroduce;
    destructor Destroy; override;
  published
    property RetEventoMDFe: TRetEventoMDFe read FRetEventoMDFe write FRetEventoMDFe;
  end;

  TRetConsSitMDFe = class(TPersistent)
  private
    FLeitor: TLeitor;
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: string;
    FcStat: Integer;
    FxMotivo: string;
    FcUF: integer;
    FchMDFe: string;
    FprotMDFe: TProcMDFe;
//    FretCancMDFe: TRetCancMDFe;
    FprocEventoMDFe: TRetEventoMDFeCollection;
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
    property chMDFe: string read FchMDFe write FchMDFe;
    property protMDFe: TProcMDFe read FprotMDFe write FprotMDFe;
//    property retCancMDFe: TRetCancMDFe read FretCancMDFe write FretCancMDFe;
    property procEventoMDFe: TRetEventoMDFeCollection read FprocEventoMDFe write FprocEventoMDFe;
  end;

implementation

{ TRetConsSitMDFe }

constructor TRetConsSitMDFe.Create;
begin
  FLeitor := TLeitor.Create;
  FprotMDFe := TProcMDFe.create;
//  FretCancMDFe := TRetCancMDFe.create;
end;

destructor TRetConsSitMDFe.Destroy;
begin
  FLeitor.Free;
  FprotMDFe.Free;
//  FretCancMDFe.Free;
  if Assigned(procEventoMDFe) then
    procEventoMDFe.Free;
  inherited;
end;

function TRetConsSitMDFe.LerXml: boolean;
var
  ok: boolean;
  i: integer;
begin
  Result := False;
  try
    if leitor.rExtrai(1, 'retConsSitMDFe') <> '' then
    begin
      FtpAmb    := StrToTpAmb(ok, leitor.rCampo(tcStr, 'tpAmb'));
      FverAplic := leitor.rCampo(tcStr, 'verAplic');
      FcStat    := leitor.rCampo(tcInt, 'cStat');
      FxMotivo  := leitor.rCampo(tcStr, 'xMotivo');
      FcUF      := leitor.rCampo(tcInt, 'cUF');

      if FcStat in [100, 132] then
       begin
         if (Leitor.rExtrai(1, 'protMDFe') <> '') or (Leitor.rExtrai(1, 'infProt') <> '') then
          begin
            protMDFe.tpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
            protMDFe.verAplic := Leitor.rCampo(tcStr, 'verAplic');
            protMDFe.chMDFe   := Leitor.rCampo(tcStr, 'chMDFe');
            protMDFe.dhRecbto := Leitor.rCampo(tcDatHor, 'dhRecbto');
            protMDFe.nProt    := Leitor.rCampo(tcStr, 'nProt');
            protMDFe.digVal   := Leitor.rCampo(tcStr, 'digVal');
            protMDFe.cStat    := Leitor.rCampo(tcInt, 'cStat');
            protMDFe.xMotivo  := Leitor.rCampo(tcStr, 'xMotivo');
            FchMDFe           := protMDFe.chMDFe;
         end;
       end;

      if Assigned(procEventoMDFe) then
        procEventoMDFe.Free;
      procEventoMDFe := TRetEventoMDFeCollection.Create(Self);
      i := 0;
      while Leitor.rExtrai(1, 'procEventoMDFe', '', i + 1) <> '' do
      begin
        procEventoMDFe.Add;
        procEventoMDFe.Items[i].RetEventoMDFe.Leitor.Arquivo := Leitor.Grupo;
        procEventoMDFe.Items[i].RetEventoMDFe.LerXml;
        inc(i);
      end;
      if i = 0
       then procEventoMDFe.Add;
      (*
      if FcStat = 101 then
       begin
         if Leitor.rExtrai(1, 'infCanc') <> '' then
          begin
            retCancMDFe.tpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
            retCancMDFe.verAplic := Leitor.rCampo(tcStr, 'verAplic');
            retCancMDFe.cStat    := Leitor.rCampo(tcInt, 'cStat');
            retCancMDFe.xMotivo  := Leitor.rCampo(tcStr, 'xMotivo');
            retCancMDFe.cUF      := Leitor.rCampo(tcInt, 'cUF');
            retCancMDFe.chMDFe   := Leitor.rCampo(tcStr, 'chMDFe');
            retCancMDFe.dhRecbto := Leitor.rCampo(tcDatHor, 'dhRecbto');
            retCancMDFe.nProt    := Leitor.rCampo(tcStr, 'nProt');
            FchMDFe              := retCancMDFe.chMDFe;
         end;
       end;
      *)
      Result := True;
    end;

  except
    Result := False;
  end;
end;

{ TRetEventoCollection }

function TRetEventoMDFeCollection.Add: TRetEventoMDFeCollectionItem;
begin
  Result := TRetEventoMDFeCollectionItem(inherited Add);
  Result.create;
end;

constructor TRetEventoMDFeCollection.Create(AOwner: TPersistent);
begin
  inherited Create(TRetEventoMDFeCollectionItem);
end;

function TRetEventoMDFeCollection.GetItem(Index: Integer): TRetEventoMDFeCollectionItem;
begin
  Result := TRetEventoMDFeCollectionItem(inherited GetItem(Index));
end;

procedure TRetEventoMDFeCollection.SetItem(Index: Integer;
  Value: TRetEventoMDFeCollectionItem);
begin
  inherited SetItem(Index, Value);
end;

{ TRetEventoCollectionItem }

constructor TRetEventoMDFeCollectionItem.Create;
begin
  FRetEventoMDFe := TRetEventoMDFe.Create;
end;

destructor TRetEventoMDFeCollectionItem.Destroy;
begin
  FRetEventoMDFe.Free;
  inherited;
end;

end.

