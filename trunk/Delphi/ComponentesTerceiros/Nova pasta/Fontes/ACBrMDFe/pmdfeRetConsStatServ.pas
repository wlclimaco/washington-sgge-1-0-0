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

unit pmdfeRetConsStatServ;

interface

uses
  SysUtils, Classes,
  pcnAuxiliar, pcnConversao, pcnLeitor;

type

  TRetConsStatServ = class(TPersistent)
  private
    FLeitor: TLeitor;
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: string;
    FcStat: integer;
    FxMotivo: string;
    FcUF: integer;
    FdhRecbto: TDateTime;
    FtMed: integer;
    FdhRetorno: TDateTime;
    FxObs: string;
  public
    constructor Create;
    destructor Destroy; override;
    function LerXml: boolean;
  published
    property Leitor: TLeitor read FLeitor write FLeitor;
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property verAplic: string read FverAplic write FverAplic;
    property cStat: integer read FcStat write FcStat;
    property xMotivo: string read FxMotivo write FxMotivo;
    property cUF: integer read FcUF write FcUF;
    property dhRecbto: TDateTime read FdhRecbto write FdhRecbto;
    property tMed: integer read FtMed write FtMed;
    property dhRetorno: TDateTime read FdhRetorno write FdhRetorno;
    property xObs: string read FxObs write FxObs;
  end;

implementation

{ TRetConsStatServ }

constructor TRetConsStatServ.Create;
begin
  FLeitor := TLeitor.Create;
end;

destructor TRetConsStatServ.Destroy;
begin
  FLeitor.Free;
  inherited;
end;

function TRetConsStatServ.LerXml: boolean;
var
  ok: boolean;
begin
  Result := False;
  try
    Leitor.Grupo := Leitor.Arquivo;

    if leitor.rExtrai(1, 'retConsStatServMDFe') <> '' then
    begin
      FtpAmb     := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
      FverAplic  := Leitor.rCampo(tcStr, 'verAplic');
      FcStat     := Leitor.rCampo(tcInt, 'cStat');
      FxMotivo   := Leitor.rCampo(tcStr, 'xMotivo');
      FcUF       := Leitor.rCampo(tcInt, 'cUF');
      FdhRecbto  := Leitor.rCampo(tcDatHor, 'dhRecbto');
      FtMed      := Leitor.rCampo(tcInt, 'tMed');
      FdhRetorno := Leitor.rCampo(tcDatHor, 'dhRetorno');
      FxObs      := Leitor.rCampo(tcStr, 'xObs');

      Result := True;
    end;

  except
    Result := False;
  end;
end;

end.

