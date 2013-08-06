{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Andr� Ferreira de Moraes               }
{                                       Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
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
|* 02/05/2011 Isaque Pinheiro
|*  - Primeira Versao ACBrInStore
******************************************************************************}

unit ACBrInStore;

interface

uses
  SysUtils, Classes, ACBrBase, ACBrUtil;

type
  TACBrPrecoUnitario = procedure(const Codigo: string;
                                 var PrecoUnitario: Double) of object;

  TACBrInStore = class(TACBrComponent)
  private
    fPrefixo: String;
    fPeso: Double;
    fTotal: Double;
    fCodigo: String;
    fDV: String;
    FCodificacao: String;
    fsOnGetPrecoUnitario: TACBrPrecoUnitario;

    procedure SetCodificacao(Value: string);
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure ZerarDados;
    procedure Desmembrar(pCodigoEtiqueta: string);

    property Codificacao: String read FCodificacao write SetCodificacao;
    property Prefixo: String read fPrefixo;
    property Codigo: String read fCodigo;
    property Peso: Double read fPeso;
    property Total: Double read fTotal;
    property DV: String read fDV;
  published
    property OnGetPrecoUnitario: TACBrPrecoUnitario read fsOnGetPrecoUnitario
                                                   write fsOnGetPrecoUnitario;
  end;

implementation

{ TACBrInStore }

constructor TACBrInStore.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );
  fPrefixo := '';
end;

destructor TACBrInStore.Destroy;
begin

  inherited;
end;

procedure TACBrInStore.SetCodificacao(Value: string);
var
pCodigo: Integer;
begin
   FCodificacao := Value;
   // Vari�veis de posi��o
   pCodigo  := Pos('C', FCodificacao);
   // Desmembrar os campos
   // Profixo
   fPrefixo := Copy(FCodificacao, 1, pCodigo -1);
end;

procedure TACBrInStore.ZerarDados;
begin
  fCodigo  := '';
  fDV      := '';
  fPeso    := 0;
  fTotal   := 0;
end;

procedure TACBrInStore.Desmembrar(pCodigoEtiqueta: string);
var
  // Vari�veis de posi��o
  pCodigo: Integer;
  pTotal: Integer;
  pPeso: Integer;
  // Vari�veis de tamanho
  tCodigo: Integer;
  tTotal: Integer;
  tPeso: Integer;
  // Digito verificador
  iFor: Integer;
  fPrecoUnitario: Double;
begin
  if Length(FCodificacao) < 13 then
     raise Exception.Create('Codifica��o inv�lida!');

  if Length(pCodigoEtiqueta) < 13 then
     raise Exception.Create('C�digo EAN13 inv�lido!');

  if not ACBrUtil.EAN13Valido(pCodigoEtiqueta) then
     raise Exception.Create('Digito verificador do c�digo EAN13 inv�lido!');

  // Limpa fields
  ZerarDados;
  //
  fPrecoUnitario := 0;

  // Vari�veis de posi��o
  pCodigo := Pos('C', FCodificacao);
  pPeso   := Pos('P', FCodificacao);
  pTotal  := Pos('T', FCodificacao);

  // Vari�veis de tamanho
  tCodigo := 0;
  tTotal  := 0;
  tPeso   := 0;

  for iFor := 1 to Length(FCodificacao) do
  begin
    if FCodificacao[iFor] = 'C' then
      Inc(tCodigo)
    else
    if FCodificacao[iFor] = 'P' then
      Inc(tPeso)
    else
    if FCodificacao[iFor] = 'T' then
      Inc(tTotal);
  end;

  // C�digo
  if pCodigo > 0 then
     fCodigo := Copy(pCodigoEtiqueta, pCodigo, tCodigo);

  // Peso
  if pPeso > 0 then
  begin
    fPeso := StrToCurrDef( Copy(pCodigoEtiqueta, pPeso, tPeso), 0);
    fPeso := fPeso / 1000;
  end;

  // Total
  if pTotal > 0 then
  begin
    fTotal := StrToCurrDef( Copy(pCodigoEtiqueta, pTotal, tTotal), 0);
    fTotal := fTotal / 100;
  end;

  // Caso use somente o peso, poder� ser buscado o pre�o unit�rio para achar
  // o valor total
  if Assigned( fsOnGetPrecoUnitario ) then
  begin
     fsOnGetPrecoUnitario( fCodigo, fPrecoUnitario );

     // Se:
     // Valor unit�rio maior que zero
     // Peso maior que zero
     // Ser� calculado o pre�o total
     if (fPrecoUnitario > 0) and (fPeso > 0) then
        fTotal := fPrecoUnitario * fPeso;

     // Se:
     // Valor unit�rio maior que zero
     // Valor total maior que zero
     // Peso igual a zero
     // Ser� calculado o peso do produto
     if (fPrecoUnitario > 0) and (fTotal > 0) and (fPeso = 0) then
        fPeso := fTotal / fPrecoUnitario;
  end;
  // Captura digito verificador
  fDV := Copy(pCodigoEtiqueta, Length(pCodigoEtiqueta), 1);
end;

end.
