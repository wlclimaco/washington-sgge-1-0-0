{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Fabio Farias                           }
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
|* 04/10/2005: Daniel Simoes de Almeida
|*  - Primeira Versao ACBrBAL
******************************************************************************}

{$I ACBr.inc}

unit ACBrBALClass;

interface
uses ACBrDevice,      {Units da ACBr}
     Classes,
     {$IFDEF COMPILER6_UP} Types {$ELSE} Windows {$ENDIF} ;

type

{ Classe generica de BALANCA, nao implementa nenhum modelo especifico, apenas
  declara a Classe. NAO DEVE SER INSTANCIADA. Usada apenas como base para
  as demais Classes de BALANCA como por exemplo a classe TACBrBALFilizola }

TACBrBALClass = class
  private
    procedure SetAtivo(const Value: Boolean);
  protected
    fpDevice  : TACBrDevice ;
    fpAtivo   : Boolean ;
    fpModeloStr: String;
    fpUltimoPesoLido: Double;
    fpUltimaResposta: AnsiString;
  public
    constructor Create(AOwner: TComponent);
    destructor Destroy  ; override ;

    property Ativo  : Boolean read fpAtivo write SetAtivo ;
    procedure Ativar ; virtual ;
    procedure Desativar ; virtual ;

    function LePeso( MillisecTimeOut : Integer = 3000) :Double ; virtual;
    procedure LeSerial( MillisecTimeOut : Integer = 500) ; virtual ;
    property ModeloStr: String  read fpModeloStr ;
    property UltimoPesoLido : Double read fpUltimoPesoLido ;
    property UltimaResposta : AnsiString read fpUltimaResposta ;
end;

implementation

Uses
  ACBrBAL, ACBrUtil, 
  SysUtils;

{ TACBrBALClass }

constructor TACBrBALClass.Create(AOwner: TComponent);
begin
  if not (AOwner is TACBrBAL) then
     raise Exception.create(ACBrStr('Essa Classe deve ser instanciada por TACBrBAL'));

  { Criando ponteiro interno para as Propriedade SERIAL de ACBrBAL,
    para permitir as Classes Filhas o acesso a essas propriedades do Componente}

  fpDevice    := (AOwner as TACBrBAL).Device ;
  fpDevice.SetDefaultValues ;

  fpAtivo     := false ;
  fpModeloStr := 'N�o Definida' ;
end;

destructor TACBrBALClass.Destroy;
begin
  fpDevice := nil ; { Apenas remove referencia (ponteiros internos) }

  inherited Destroy;
end;

procedure TACBrBALClass.SetAtivo(const Value: Boolean);
begin
  if Value then
     Ativar
  else
     Desativar ;
end;

procedure TACBrBALClass.Ativar;
begin
  if fpAtivo then exit ;

  if fpDevice.Porta <> '' then
     fpDevice.Ativar ;

  fpAtivo          := true ;
  fpUltimaResposta := '' ;
  fpUltimoPesoLido := 0 ;
end;

procedure TACBrBALClass.Desativar;
begin
  if not fpAtivo then exit ;

  if fpDevice.Porta <> '' then
     fpDevice.Desativar ;

  fpAtivo := false ;
end;

function TACBrBALClass.LePeso( MillisecTimeOut : Integer): Double;
begin
  { Deve ser implementada na Classe Filha }
  raise Exception.Create(ACBrStr('Fun��o LePeso n�o implementada em: ')+ModeloStr);
end;

procedure TACBrBALClass.LeSerial( MillisecTimeOut : Integer) ;
begin
  { Deve ser implementada na Classe Filha }
  raise Exception.Create(ACBrStr('Procedure LeSerial n�o implementada em: ')+ModeloStr);
end;

end.
