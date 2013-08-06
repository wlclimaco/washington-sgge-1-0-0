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

unit pmdfeRetEnvMDFe;

interface
 uses
  SysUtils, Classes,
  pcnAuxiliar, pcnConversao, pcnLeitor;

type

  TInfREC = class
  private
    FnRec: string;
    FdhRecbto: TDateTime;
    FtMed: integer;
  public
    property nRec: string read FnRec write FnRec;
    property dhRecbto: TDateTime read FdhRecbto write FdhRecbto;
    property tMed: integer read FtMed write FtMed;
  end;

  TretEnvMDFe = class(TPersistent)
  private
    FtpAmb: TpcnTipoAmbiente;
    FcStat: integer;
    FLeitor: TLeitor;
    FcUF: integer;
    FverAplic: string;
    FxMotivo: string;
    FinfRec: TInfREC;
  public
    constructor Create;
    destructor Destroy; override;
    function LerXml: boolean;
  published
    property Leitor: TLeitor read FLeitor write FLeitor;
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb; //default taHomologacao;
    property verAplic: string read FverAplic write FverAplic;
    property cStat: integer read FcStat write FcStat;
    property xMotivo: string read FxMotivo write FxMotivo;
    property cUF: integer read FcUF write FcUF;
    property infRec: TInfREC read FinfRec write FinfRec;
  end;

implementation

{ TretEnvMDFe }

constructor TretEnvMDFe.Create;
begin
  FLeitor := TLeitor.Create;
  FinfRec := TInfREC.Create
end;

destructor TretEnvMDFe.Destroy;
begin
  FLeitor.Free;
  FinfRec.Free;
  inherited;
end;

function TretEnvMDFe.LerXml: boolean;
var
  ok: boolean;
begin
  result := False;
  try
    Leitor.Grupo := Leitor.Arquivo;
    if leitor.rExtrai(1, 'retEnviMDFe') <> '' then
    begin
      FtpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
      FcUF      := Leitor.rCampo(tcInt, 'cUF');
      FverAplic := Leitor.rCampo(tcStr, 'verAplic');
      FcStat    := Leitor.rCampo(tcInt, 'cStat');
      FxMotivo  := Leitor.rCampo(tcStr, 'xMotivo');

      // Grupo infRec - Dados do Recibo do Lote (S� � gerado se o Lote for aceito)
      infRec.nRec      := Leitor.rCampo(tcStr, 'nRec');
      infRec.FdhRecbto := Leitor.rCampo(tcDatHor, 'dhRecbto');
      infRec.FtMed     := Leitor.rCampo(tcInt, 'tMed');

      Result := True;
    end;

  except
    result := false;
  end;
end;

end.

