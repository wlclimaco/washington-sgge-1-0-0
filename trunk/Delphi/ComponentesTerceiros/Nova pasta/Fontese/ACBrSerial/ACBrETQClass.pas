{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2007 Andrews Ricardo Bejatto                }
{                                       Anderson Rogerio Bejatto               }
{                                                                              }
{ Colaboradores nesse arquivo:          Daniel Simoes de Almeida               }
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
|* 27/03/2007: Andrews R Bejatto/ Anderson R Bejatto/ Daniel Sim�es de Almeida
|*  - Primeira Versao ACBrETQClass
|* 17/04/2009: Alexsander da Rosa
|*  - Parametro "SubFonte" na procedure ImprimirTexto
|* 29/05/2010: Alexsander da Rosa
|*  - Propriedade "Unidade" para indicar milimetros/polegadas
******************************************************************************}

{$I ACBr.inc}

unit ACBrETQClass;

interface
uses ACBrDevice, Classes;

type

{ Classe generica de ETIQUETA, nao implementa nenhum modelo especifico, apenas
  declara a Classe. NAO DEVE SER INSTANCIADA. Usada apenas como base para
  as demais Classes de Impressora como por exemplo a classe TACBrETQPpla }

{ TACBrETQClass }

TACBrETQClass = class
  private
    FTemperatura: Integer;
    FAvanco: Integer;
    FUnidade: TACBrETQUnidade;
    FDPI: TACBrETQDPI;
    procedure SetAtivo(const Value: Boolean);
    procedure SetTemperatura(const Value: Integer);
    procedure SetAvanco(const Value: Integer);
  protected
    fpDevice  : TACBrDevice ;
    fpAtivo   : Boolean ;
    fpModeloStr: String;
    fpListaCmd: TStringList;
    fpCmd: AnsiString;
    fpLimparMemoria : Boolean;

    procedure SetUnidade(const AValue: TACBrETQUnidade); virtual;
    procedure SetDPI(const AValue : TACBrETQDPI) ; virtual;

  public
    property Ativo  : Boolean read fpAtivo write SetAtivo;
    property ModeloStr: String read fpModeloStr;
    property ListaCmd: TStringList read fpListaCmd write fpListaCmd;
    property Cmd: AnsiString read fpCmd write fpCmd;
    property Temperatura: Integer read FTemperatura write SetTemperatura;
    property Avanco: Integer read FAvanco write SetAvanco;
    property Unidade: TACBrETQUnidade read FUnidade write SetUnidade;
    property DPI: TACBrETQDPI read FDPI write SetDPI;
    property LimparMemoria: Boolean read fpLimparMemoria write fpLimparMemoria ;

    constructor Create(AOwner: TComponent);
    destructor Destroy  ; override;

    procedure Ativar ; virtual;
    procedure Desativar ; virtual;

    function ConverterUnidade( UnidadeSaida: TACBrETQUnidade;
       AValue : Integer) : Integer ; virtual;

    procedure ImprimirTexto(Orientacao: TACBrETQOrientacao; Fonte, MultiplicadorH,
      MultiplicadorV, Vertical, Horizontal: Integer;
      Texto: String; SubFonte: Integer = 0; ImprimirReverso : Boolean = False); virtual;
    procedure ImprimirBarras(Orientacao: TACBrETQOrientacao; TipoBarras,
      LarguraBarraLarga, LarguraBarraFina: String; Vertical, Horizontal: Integer;
      Texto: String; AlturaCodBarras: Integer;
      ExibeCodigo: TACBrETQBarraExibeCodigo = becPadrao); virtual;
    procedure ImprimirLinha(Vertical, Horizontal, Largura, Altura: Integer); virtual;
    procedure ImprimirCaixa(Vertical, Horizontal, Largura, Altura,
      EspessuraVertical, EspessuraHorizontal: Integer); virtual;
    procedure ImprimirImagem(MultiplicadorImagem, Vertical, Horizontal: Integer;
       NomeImagem: String); virtual;
    procedure CarregarImagem(AStream : TStream; NomeImagem: String;
       Flipped : Boolean = True; Tipo: String = 'BMP' ); virtual; 
    procedure Imprimir(Copias: Integer = 1; AvancoEtq: Integer = 0); virtual;
end;

implementation

Uses
  ACBrETQ, ACBrUtil, 
  SysUtils, math;

{ TACBrBAETQClass }

constructor TACBrETQClass.Create(AOwner: TComponent);
begin
  if not (AOwner is TACBrETQ) then
     raise Exception.create(ACBrStr('Essa Classe deve ser instanciada por TACBrETQ'));

  { Criando ponteiro interno para as Propriedade SERIAL de ACBrETQ,
    para permitir as Classes Filhas o acesso a essas propriedades do Componente}

  fpDevice    := (AOwner as TACBrETQ).Device ;
  fpDevice.SetDefaultValues ;

  fpAtivo     := false ;
  fpModeloStr := 'N�o Definida' ;
  fpListaCmd:= TStringList.Create;
  fpLimparMemoria := True ;
  
  FAvanco      := 0;
  FTemperatura := 10 ;
  FUnidade     := etqMilimetros;
  FDPI         := dpi203;
end;

destructor TACBrETQClass.Destroy;
begin
  fpDevice := nil; { Apenas remove referencia (ponteiros internos) }
  FreeAndNil(fpListaCmd);

  inherited Destroy;
end;

procedure TACBrETQClass.SetAtivo(const Value: Boolean);
begin
  if Value then
     Ativar
  else
     Desativar ;
end;

procedure TACBrETQClass.Ativar;
begin
  if fpAtivo then exit ;

  if fpDevice.Porta <> '' then
     fpDevice.Ativar ;

  fpAtivo := true ;
end;

procedure TACBrETQClass.Desativar;
begin
  if not fpAtivo then exit ;

  if fpDevice.Porta <> '' then
     fpDevice.Desativar ;

  fpAtivo := false ;
end;

function TACBrETQClass.ConverterUnidade( UnidadeSaida : TACBrETQUnidade;
   AValue : Integer) : Integer ;
Var
  ValorReal, ValorFinal, DotsMM, DotsPI : Double ;
begin
  Result     := AValue;
  ValorFinal := AValue ;  // evita Warnings
  ValorReal  := AValue ;

  if UnidadeSaida = Unidade then
     exit ;

  case DPI of
    dpi300 :
      begin
        DotsMM := 12;
        DotsPI := 300;
      end ;

    dpi600 :
      begin
        DotsMM := 23.5;
        DotsPI := 600;
      end ;
  else
   begin
     DotsMM := 8;
     DotsPI := 203;
   end ;
  end ;

  case Unidade of
    etqMilimetros : ValorReal := AValue / 10 ;
    etqPolegadas  : ValorReal := AValue / 100;
  end ;

  case UnidadeSaida of
    etqMilimetros :
       begin
         case Unidade of
            etqPolegadas : ValorFinal := ValorReal * 25.4;
            etqDots      : ValorFinal := ValorReal / DotsMM ;
         end ;

         ValorFinal := ValorFinal * 10;
       end ;

    etqPolegadas :
       begin
         case Unidade of
            etqMilimetros : ValorFinal := ValorReal / 25.4;
            etqDots       : ValorFinal := ValorReal / DotsPI ;
         end ;

         ValorFinal := ValorFinal * 100;
       end ;

    etqDots :
       begin
         case Unidade of
            etqMilimetros : ValorFinal := ValorReal * DotsMM;
            etqPolegadas  : ValorFinal := ValorReal * DotsPI ;
         end ;
       end ;
  end ;

  Result := trunc( RoundTo( ValorFinal, 0 ) );
end ;

procedure TACBrETQClass.ImprimirBarras(Orientacao: TACBrETQOrientacao;
  TipoBarras, LarguraBarraLarga, LarguraBarraFina: String;
  Vertical, Horizontal: Integer; Texto: String; AlturaCodBarras: Integer;
  ExibeCodigo: TACBrETQBarraExibeCodigo = becPadrao);
begin
  raise Exception.Create(ACBrStr('Fun��o ImprimirBarras n�o implementada em: ') + ModeloStr);
end;

procedure TACBrETQClass.ImprimirCaixa(Vertical, Horizontal, Largura,
  Altura, EspessuraVertical, EspessuraHorizontal: Integer);
begin
  raise Exception.Create(ACBrStr('Fun��o ImprimirCaixa n�o implementada em: ') + ModeloStr);
end;

procedure TACBrETQClass.ImprimirLinha(Vertical, Horizontal, Largura,
  Altura: Integer);
begin
  raise Exception.Create(ACBrStr('Fun��o ImprimirLinha n�o implementada em: ') + ModeloStr);
end;

procedure TACBrETQClass.ImprimirTexto(Orientacao: TACBrETQOrientacao; Fonte, MultiplicadorH,
  MultiplicadorV, Vertical, Horizontal: Integer; Texto: String;
  SubFonte: Integer = 0; ImprimirReverso : Boolean = False);
begin
  raise Exception.Create(ACBrStr('Fun��o ImprimirTexto n�o implementada em: ') + ModeloStr);
end;

procedure TACBrETQClass.SetTemperatura(const Value: Integer);
begin
  FTemperatura := Value;
end;

procedure TACBrETQClass.SetAvanco(const Value: Integer);
begin
  FAvanco := Value;
end;

procedure TACBrETQClass.SetUnidade(const AValue: TACBrETQUnidade);
begin
  FUnidade := AValue;
end;

procedure TACBrETQClass.SetDPI(const AValue : TACBrETQDPI) ;
begin
   FDPI := AValue ;
end ;

procedure TACBrETQClass.Imprimir(Copias : Integer ; AvancoEtq : Integer) ;
begin
  raise Exception.Create(ACBrStr('Fun��o Imprimir n�o implementada em: ') + ModeloStr);
end;

procedure TACBrETQClass.ImprimirImagem(MultiplicadorImagem, Vertical, Horizontal
  : Integer; NomeImagem : String);
begin
  raise Exception.Create(ACBrStr('Fun��o ImprimirImagem n�o implementada em: ') + ModeloStr);
end;

procedure TACBrETQClass.CarregarImagem(AStream : TStream; NomeImagem: String;
   Flipped : Boolean; Tipo: String);
begin
  raise Exception.Create(ACBrStr('Fun��o CarregarImagem n�o implementada em: ') + ModeloStr);
end;

end.
