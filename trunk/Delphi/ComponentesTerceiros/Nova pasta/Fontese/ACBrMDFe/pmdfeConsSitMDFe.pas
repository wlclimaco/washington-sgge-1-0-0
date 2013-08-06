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

unit pmdfeConsSitMDFe;

interface

uses
  SysUtils, Classes,
  pcnAuxiliar, pcnConversao, pcnGerador;

type

  TConsSitMDFe = class(TPersistent)
  private
    FGerador: TGerador;
    FtpAmb: TpcnTipoAmbiente;
    FchMDFe: string;
  public
    constructor Create;
    destructor Destroy; override;
    function GerarXML: boolean;
    function ObterNomeArquivo: string;
  published
    property Gerador: TGerador read FGerador write FGerador;
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property chMDFe: string read FchMDFe write FchMDFe;
  end;

implementation

{ TConsSitMDFe }

constructor TConsSitMDFe.Create;
begin
  FGerador := TGerador.Create;
end;

destructor TConsSitMDFe.Destroy;
begin
  FGerador.Free;
  inherited;
end;

function TConsSitMDFe.ObterNomeArquivo: string;
begin
  Result := SomenteNumeros(FchMDFe) + '-ped-sit.xml';
end;

function TConsSitMDFe.GerarXML: boolean;
begin
 Gerador.ArquivoFormatoXML := '';

 Gerador.wGrupo('consSitMDFe ' + NAME_SPACE_MDFE + ' versao="' + MDFeconsSitMDFe + '"');
 Gerador.wCampo(tcStr, 'CP03', 'tpAmb ', 01, 01, 1, tpAmbToStr(FtpAmb), DSC_TPAMB);
 Gerador.wCampo(tcStr, 'CP04', 'xServ ', 09, 09, 1, 'CONSULTAR', DSC_XSERV);
 Gerador.wCampo(tcEsp, 'CP05', 'chMDFe', 44, 44, 1, SomenteNumeros(FchMDFe), DSC_CHCTe);
 if not ValidarChave('NFe' + SomenteNumeros(FchMDFe))
  then Gerador.wAlerta('CP05', 'chMDFe', '', 'Chave do MDFe inv�lida');
 Gerador.wGrupo('/consSitMDFe');

 Result := (Gerador.ListaDeAlertas.Count = 0);
end;

end.

