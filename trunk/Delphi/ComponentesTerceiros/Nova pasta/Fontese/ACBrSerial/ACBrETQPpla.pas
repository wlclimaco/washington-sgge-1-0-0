{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2007 Andrews Ricardo Bejatto                }
{                                       Anderson Rogerio Bejatto               }
{                                                                              }
{ Colaboradores nesse arquivo:          Daniel Simooes de Almeida              }
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
|*  - Primeira versao ACBrETQPpla
|* 17/04/2009: Alexsander da Rosa
|*  - Parametro "SubFonte" na procedure ImprimirTexto
|* 29/05/2010: Alexsander da Rosa
|*  - Propriedade "Unidade" para indicar milimetros/polegadas
******************************************************************************}

{$I ACBr.inc}

unit ACBrETQPpla;

interface
uses ACBrETQClass, ACBrUtil, ACBrDevice, Classes ;

type

  { TACBrETQPpla }

  TACBrETQPpla = class( TACBrETQClass )
  private
    function MultiplicadorToStr( Multiplicador : Integer) : String ;
    function UnidadeToStr( Unidade : TACBrETQUnidade ) : Char ;

  protected
    function ConverterUnidade( AValue : Integer) : Integer ; reintroduce;

  public
    constructor Create(AOwner: TComponent);

    procedure ImprimirTexto(Orientacao: TACBrETQOrientacao; Fonte, MultiplicadorH,
      MultiplicadorV, Vertical, Horizontal: Integer; Texto: String;
      SubFonte: Integer = 0; ImprimirReverso : Boolean = False); override;
    procedure ImprimirBarras(Orientacao: TACBrETQOrientacao; TipoBarras,
      LarguraBarraLarga, LarguraBarraFina: String; Vertical, Horizontal: Integer;
      Texto: String; AlturaCodBarras: Integer = 0;
      ExibeCodigo: TACBrETQBarraExibeCodigo = becPadrao); override;
    procedure ImprimirLinha(Vertical, Horizontal, Largura, Altura: Integer); override;
    procedure ImprimirCaixa(Vertical, Horizontal, Largura, Altura,
      EspessuraVertical, EspessuraHorizontal: Integer); override;
    procedure ImprimirImagem(MultiplicadorImagem, Vertical, Horizontal: Integer;
       NomeImagem: String); override;
    procedure CarregarImagem(AStream : TStream; NomeImagem: String;
       Flipped : Boolean = True; Tipo: String = 'BMP' ); override;
    procedure Imprimir(Copias: Integer = 1; AvancoEtq: Integer = 0); override;

  end ;

implementation
Uses  ACBrConsts,
     {$IFNDEF COMPILER6_UP} ACBrD5, Windows, {$ENDIF}
     SysUtils, StrUtils ;

{ TACBrETQPpla }

constructor TACBrETQPpla.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpModeloStr := 'PPLA';
  Temperatura := 10;
  Unidade     := etqMilimetros;
end;

function TACBrETQPpla.MultiplicadorToStr(Multiplicador : Integer) : String ;
begin
  if (Multiplicador >= 0) and (Multiplicador < 10) then
     Result := IntToStr(Multiplicador)
  else if Multiplicador < 24 then
     Result := chr(Multiplicador+55)   //Ex: 10 + 55 = 65 = A
  else
     Raise Exception.Create(ACBrStr('Informe um valor entre 0 e 24 para Multiplicador'));
end ;

function TACBrETQPpla.UnidadeToStr( Unidade : TACBrETQUnidade ) : Char ;
begin
  if Unidade = etqPolegadas then
     Result := 'n'
  else
     Result := 'm' ;
end ;

function TACBrETQPpla.ConverterUnidade(AValue : Integer) : Integer ;
begin
  Result := AValue;
  if Unidade <> etqDots then
     exit ;

  Result := inherited ConverterUnidade( etqMilimetros, AValue ) ;
end ;

procedure TACBrETQPpla.ImprimirTexto(Orientacao: TACBrETQOrientacao; Fonte, MultiplicadorH,
  MultiplicadorV, Vertical, Horizontal: Integer; Texto: String;
  SubFonte: Integer = 0; ImprimirReverso : Boolean = False);
var
   eixoY, eixoX, Smooth: String;
begin

  Cmd := '';

  if (Fonte < 0) or (Fonte > 10) then
     Raise Exception.Create(ACBrStr('Informe um valor entre 0 e 10 para Fonte'));

  if (SubFonte < 0) or (SubFonte > 7) then
     Raise Exception.Create(ACBrStr('Informe um valor entre 0 e 7 para SubFonte'));

{ Multiplicador Horizontal, Multiplicador Vertical:
 De 0 a 9 e de A at� O representa as escalas de multiplica��o (A=10, B=11,..., O=24)}

  Vertical := ConverterUnidade(Vertical);
  if (Vertical > 9999) then
     Raise Exception.Create(ACBrStr('Vertical deve ter no m�ximo 4 d�gitos'));
  eixoY := IntToStrZero(Vertical, 4);

  Horizontal := ConverterUnidade(Horizontal);
  if (Horizontal > 9999) then
     Raise Exception.Create(ACBrStr('Horizontal deve ter no m�ximo 4 d�gitos'));
  eixoX := IntToStrZero(Horizontal, 4);

  if Length(Texto) > 255 then
     Raise Exception.Create(ACBrStr('Tamanho maximo para o texto 255 caracteres'));

  if Fonte < 9 then
    Smooth := '000'
  else
    Smooth := IntToStrZero(SubFonte, 3);

  Cmd := IntToStr(Integer(Orientacao) + 1)    +
         Chr(48+Fonte)                        +
         MultiplicadorToStr( MultiplicadorH ) +
         MultiplicadorToStr( MultiplicadorV ) +
         Smooth + eixoY + eixoX + Texto;

  ListaCmd.Add(Cmd);
end;

procedure TACBrETQPpla.ImprimirBarras(Orientacao: TACBrETQOrientacao; TipoBarras,
  LarguraBarraLarga, LarguraBarraFina: String; Vertical, Horizontal: Integer;
  Texto: String; AlturaCodBarras: Integer;
  ExibeCodigo: TACBrETQBarraExibeCodigo);
var
   eixoY, eixoX, AltCodBarras: String;
begin
  Cmd := '';

{Tipo de C�digo de Barras - vai de 'a' at� 't' e de 'A' at� 'T'
 Largura da Barra Larga, Largura da Barra Fina - De 0 a 9 e de 'A' at� 'O'}

  Vertical := ConverterUnidade(Vertical);
  if (Vertical > 9999) then
     Raise Exception.Create(ACBrStr('Vertical deve ter no m�ximo 4 d�gitos'));
  eixoY := IntToStrZero(Vertical, 4);

  Horizontal := ConverterUnidade(Horizontal);
  if (Horizontal > 9999) then
     Raise Exception.Create(ACBrStr('Horizontal deve ter no m�ximo 4 d�gitos'));
  eixoX := IntToStrZero(Horizontal, 4);

  AlturaCodBarras := ConverterUnidade(AlturaCodBarras);
  if (AlturaCodBarras < 0) or (AlturaCodBarras > 999) then
     Raise Exception.Create(ACBrStr('AlturaCodBarras deve ter no m�ximo 3 d�gitos'));
  AltCodBarras := IntToStrZero(AlturaCodBarras,3);

  case ExibeCodigo of
     becNAO : TipoBarras := LowerCase(TipoBarras);
     becSIM : TipoBarras := UpperCase(TipoBarras);
  end ;

  Cmd := IntToStr(Integer(Orientacao) + 1) + TipoBarras + LarguraBarraLarga +
         LarguraBarraFina + AltCodBarras + eixoY + eixoX + Texto;

  ListaCmd.Add(Cmd);
end;

procedure TACBrETQPpla.ImprimirLinha(Vertical, Horizontal, Largura,
  Altura: Integer);
var
   eixoY, eixoX, Larg, Alt: String;
begin
  Cmd := '';

  if (Vertical < 0) or (Vertical > 9999) then
     Raise Exception.Create(ACBrStr('Informe um valor entre 0 e 9999 para Vertical'));
  eixoY := IntToStrZero(Vertical, 4);

  if (Horizontal < 0) or (Horizontal > 9999) then
     Raise Exception.Create(ACBrStr('Informe um valor entre 0 e 9999 para Horizontal'));
  eixoX := IntToStrZero(Horizontal, 4);

  if (Largura < 0) or (Largura > 999) then
     Raise Exception.Create(ACBrStr('Informe um valor entre 0 e 999 para Largura'));
  Larg := IntToStrZero(Largura, 3);

  if (Altura < 0) or (Altura > 999) then
     Raise Exception.Create(ACBrStr('Informe um valor entre 0 e 999 para Altura'));
  Alt := IntToStrZero(Altura, 3);

  Cmd := '1X11000' + eixoY + eixoX + 'L' + Larg + Alt;

  ListaCmd.Add(Cmd);
end;

procedure TACBrETQPpla.ImprimirCaixa(Vertical, Horizontal, Largura, Altura,
  EspessuraVertical, EspessuraHorizontal: Integer);
var
   eixoY, eixoX, Larg, Alt, EspH, EspV: String;
begin
  Cmd := '';

  if (Vertical < 0) or (Vertical > 9999) then
     Raise Exception.Create(ACBrStr('Tamanho m�ximo para Vertical 4 caracteres'));
  eixoY := IntToStrZero(Vertical, 4);

  if (Horizontal < 0) or (Horizontal > 9999) then
     Raise Exception.Create(ACBrStr('Tamanho m�ximo para Horizontal 4 caracteres'));
  eixoX := IntToStrZero(Horizontal, 4);

  if (Largura < 0) or (Largura > 999) then
     Raise Exception.Create(ACBrStr('Tamanho m�ximo para a Largura da Linha 3 caracteres'));
  Larg := IntToStrZero(Largura, 3);

  if (Altura < 0) or (Altura > 999) then
     Raise Exception.Create(ACBrStr('Tamanho m�ximo para a Altura da Linha 3 caracteres'));
  Alt := IntToStrZero(Altura, 3);

  if (EspessuraHorizontal < 0) or (EspessuraHorizontal > 999) then
     Raise Exception.Create(ACBrStr('Tamanho m�ximo para a Espessura das Linhas Horizontais 3 caracteres'));
  EspH := IntToStrZero(EspessuraHorizontal, 3);

  if (EspessuraVertical < 0) or (EspessuraVertical > 999) then
     Raise Exception.Create(ACBrStr('Tamanho m�ximo para a Espessura das Linhas Verticais 3 caracteres'));
  EspV := IntToStrZero(EspessuraVertical, 3);

  Cmd := '1X11000' + eixoY + eixoX + 'B' + Larg + Alt + EspH + EspV;

  ListaCmd.Add(Cmd);
end;

procedure TACBrETQPpla.ImprimirImagem(MultiplicadorImagem, Vertical, Horizontal:
  Integer; NomeImagem: String);
var
  Mul, Lin, Col: String;
begin
  if (MultiplicadorImagem < 0) or (MultiplicadorImagem > 99) then
    Raise Exception.Create(ACBrStr('Informe um valor entre 0 e 99 para MultiplicadorImagem'));
  Mul := IntToStrZero(MultiplicadorImagem, 2);

  if (Vertical < 0) or (Vertical > 9999) then
    Raise Exception.Create(ACBrStr('Informe um valor entre 0 e 9999 para Vertical'));
  Lin := IntToStrZero(Vertical, 4);

  if (Horizontal < 0) or (Horizontal > 9999) then
    Raise Exception.Create(ACBrStr('Informe um valor entre 0 e 9999 para Horizontal'));
  Col := IntToStrZero(Horizontal, 4);

  NomeImagem := OnlyAlphaNum(UpperCase(LeftStr(Trim(NomeImagem),16))) ;

  Cmd := '1Y' + Mul + '000' + Lin + Col + NomeImagem;

  ListaCmd.Add(Cmd);
end;

procedure TACBrETQPpla.CarregarImagem(AStream : TStream; NomeImagem: String;
  Flipped : Boolean; Tipo: String);
Var
  TipoImagem : Char ;
  S  : AnsiString ;
begin
  if Tipo = 'PCX' then
     TipoImagem := 'p'
  else if Tipo = 'IMG' then
     TipoImagem := 'i'
  else if Tipo = 'HEX' then
     TipoImagem := 'f'
  else if Tipo = 'BMP' then
     TipoImagem := 'b'
  else
     raise Exception.Create( ACBrStr('Formato de Imagem deve ser MonoCrom�tico e '+
                                     ' do tipo: BMP, PCX, IMG ou HEX') );

  if Flipped then
     TipoImagem := UpCase( TipoImagem ) ;

  NomeImagem := OnlyAlphaNum(UpperCase(LeftStr(Trim(NomeImagem),16))) ;

  Cmd := STX + 'IA' + TipoImagem + NomeImagem + CRLF ;
  S   := '' ;

  AStream.Position := 0 ;
  SetLength(S,AStream.Size);
  AStream.ReadBuffer(pchar(S)^,AStream.Size);

  Cmd := Cmd + S ;

  fpDevice.EnviaString( Cmd );
end;

procedure TACBrETQPpla.Imprimir(Copias: Integer = 1; AvancoEtq: Integer = 0);
var
   Temp, NCop : String;
begin
  Cmd := '';

  if (Temperatura < 0) or (Temperatura > 20) then
     Raise Exception.Create(ACBrStr('Informe um valor entre 0 e 20 para Temperatura'));
  Temp := IntToStrZero(Temperatura, 2);

  if (Copias < 0) or (Copias > 9999) then
     Raise Exception.Create(ACBrStr('Tamanho m�ximo para o N�mero de C�pias 4 caracteres'));
  NCop := IntToStrZero(Copias, 4);

  Cmd := STX + 'L'                     + CRLF +  // Enters label formatting state
         STX + UnidadeToStr( Unidade ) + CRLF +  // Informa a Unidade utilizada
         'H' + Temp                    + CRLF +  // Ajusta a Temperatura
         'D11'                         + CRLF +  // Ajusta a resolu��o
         'Q' + NCop;                             // Ajusta o n�mero de c�pias

  {Inserindo comando iniciais na posicao Zero}
  ListaCmd.Insert(0, Cmd);

  Cmd := '';
  if AvancoEtq = 0 then
     AvancoEtq := Avanco;
  if (AvancoEtq < 0) or (AvancoEtq > 779) then
     Raise Exception.Create(ACBrStr('O Valor m�ximo para o Avan�o de Etiquetas � 779'));

  AvancoEtq := AvancoEtq + 220;

  Cmd := 'E'                                   + CRLF +   // Ends the job and exit from label formatting mode
         STX + 'f' + IntToStrZero(AvancoEtq,3) + CRLF ;   // Ajusta o avan�o para corte da etiqueta

  if LimparMemoria then
    Cmd := Cmd + STX + 'Q' ;

  ListaCmd.Add(Cmd);

  fpDevice.EnviaString(ListaCmd.Text);
  ListaCmd.Clear;
end;

end.
