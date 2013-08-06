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
|* 27/01/2011: Jos� Lu�s Schiavo
|*  - Primeira versao ACBrETQEpl2
******************************************************************************}

{$I ACBr.inc}

unit ACBrETQEpl2;

interface
uses ACBrETQClass, ACBrUtil, ACBrDevice, Classes ;

const
   LF  : String = chr(10);

type

  { TACBrETQEPL2 }

  TACBrETQEpl2 = class( TACBrETQClass )
  private
     function OrientacaoToStr(Orientacao: TACBrETQOrientacao) : Char ;
     Function FormatarTexto( const ATexto : String) : String ;

  public
    constructor Create(AOwner: TComponent);

    procedure ImprimirTexto(Orientacao: TACBrETQOrientacao; Fonte, MultiplicadorH,
      MultiplicadorV, Vertical, Horizontal: Integer; Texto: String;
      SubFonte: Integer = 0; ImprimirReverso : Boolean = False); override;
    procedure ImprimirBarras(Orientacao: TACBrETQOrientacao; TipoBarras,
      LarguraBarraLarga, LarguraBarraFina: String; Vertical, Horizontal: Integer;
      Texto: String; AlturaCodBarras: Integer;
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
Uses math,
     {$IFDEF COMPILER6_UP} StrUtils {$ELSE} ACBrD5, Windows{$ENDIF},
     SysUtils ;

{ TACBrETQEpl2 }

function TACBrETQEpl2.OrientacaoToStr(Orientacao: TACBrETQOrientacao) : Char ;
begin
   case Orientacao of
     or270    : Result := '3' ; // 270
     or180    : Result := '2' ; // 180
     or90     : Result := '1' ; // 90
   else
     Result := '0' ; // normal
   end ;
end ;

function TACBrETQEpl2.FormatarTexto( const ATexto : String) : String ;
begin
  Result := ATexto;

  if LeftStr(ATexto, 1 ) = '"' then  // Usu�rio j� faz os ajustes ?
     exit ;

  // Ajustando o TEXTO com caraceteres especiais //
  Result := StringReplace( Result, '\', '\\"', [rfReplaceAll] ) ;
  Result := StringReplace( Result, '"', '\"', [rfReplaceAll] ) ;
  Result := '"' + Result + '"' ;
end ;

constructor TACBrETQEpl2.Create(AOwner: TComponent);
begin
  inherited Create( AOwner );

  fpModeloStr := 'EPL2';
  Unidade     := etqDots;
end;

procedure TACBrETQEpl2.ImprimirTexto(Orientacao: TACBrETQOrientacao; Fonte,
  MultiplicadorH, MultiplicadorV, Vertical, Horizontal: Integer; Texto: String;
  SubFonte: Integer = 0; ImprimirReverso : Boolean = False);
var
   TipoVideo : Char;
begin
  Cmd := '';

  TipoVideo := ifthen( ImprimirReverso, 'R', 'N' )[1] ; // Somente normal, R seria para Reverso

  if (Fonte < 1) or (Fonte > 5) then
     Raise Exception.Create(ACBrStr('Informe um valor entre 1 e 5 para Fonte'));

  if (MultiplicadorH < 1) or (MultiplicadorH > 8) then
     Raise Exception.Create(ACBrStr('Informe um valor entre 1 e 8 para Multiplicador Horizontal'));

  if (MultiplicadorV < 1) or (MultiplicadorV > 9) then
     Raise Exception.Create(ACBrStr('Informe um valor entre 1 e 9 para Multiplicador Vertical'));

  if Fonte = 0 then
     Fonte := 3
  else if Fonte = 5 then
    Texto := UpperCase(Texto); // Fonte 5 s� funciona com caracteres mai�sculos

  Texto := FormatarTexto( Texto ) ;

  Cmd := 'A'                                                 +
         IntToStr( ConverterUnidade( etqDots, Horizontal ) ) + ',' +
         IntToStr( ConverterUnidade( etqDots, Vertical ) )   + ',' +
         OrientacaoToStr( Orientacao )                       + ',' +
         IntToStr( Fonte )                                   + ',' +
         IntToStr( MultiplicadorH )                          + ',' +
         IntToStr( MultiplicadorV )                          + ',' +
         TipoVideo                                           + ',' +
         Texto ;

  ListaCmd.Add(Cmd);
end;

procedure TACBrETQEpl2.ImprimirBarras(Orientacao: TACBrETQOrientacao; TipoBarras,
   LarguraBarraLarga, LarguraBarraFina: String; Vertical, Horizontal: Integer;
   Texto: String; AlturaCodBarras: Integer;
   ExibeCodigo: TACBrETQBarraExibeCodigo = becPadrao);
var
  iLarguraBarraFina, iLarguraBarraLarga : Integer ;
  FlagExibeCodigo : Char ;
begin
  Cmd := '';

  (*  O tratamento referente � largura da barra fina para cada tipo de c�digo
      fica "a implementar" ;-)
   Largura da Barra Larga - De 2 a 30
   Descri��o                                          Tipo   Largura da Barra Fina
   -------------------------------------------------------------------------------
   Code 39 std. or extended                              3                    1-10
   Code 39 with check digit                             3C                    1-10
   Code 93                                               9                    1-10
   Code 128 UCC Serial Shipping Container Code           0                    1-10
   Code 128 auto A, B, C modes                           1                    1-10
   Code 128 mode A                                      1A                    1-10
   Code 128 mode B                                      1B                    1-10
   Code 128 mode C                                      1C                    1-10
   Codabar                                               K                    1-10
   EAN8                                                E80                    2-4
   EAN8 2 digit add-on                                 E82                    2-4
   EAN8 5 digit add-on                                 E85                    2-4
   EAN13                                               E30                    2-4
   EAN13 2 digit add-on                                E32                    2-4
   EAN13 5 digit add-on                                E35                    2-4
   German Post Code                                     2G                    3-4
   Interleaved 2 of 5                                    2                    1-10
   Interleaved 2 of 5 with mod 10 check digit           2C                    1-10
   Interleaved 2 of 5 with human readable check digit   2D                    1-10
   Postnet 5, 9, 11 & 13 digit1                          P                     �
   Planet 11 & 13 digit1                                PL
   Japanese Postnet                                      J                     �
   UCC/EAN 1282                                         1E                    1-10
   UPC A                                               UA0                    2-4
   UPC A 2 digit add-on                                UA2                    2-4
   UPC A 5 digit add-on                                UA5                    2-4
   UPC E                                               UE0                    2-4
   UPC E 2 digit add-on                                UE2                    2-4
   UPC E 5 digit add-on                                UE5                    2-4
   UPC Interleaved 2 of 5                               2U                    1-10
   Plessey (MSI-1) with mod. 10 check digit              L                     �
   MSI-3 with mod. 10 check digit                        M                     �
   *)

  iLarguraBarraFina := StrToIntDef(LarguraBarraFina,0) ;
  if (iLarguraBarraFina < 1) or (iLarguraBarraFina > 10) then
     Raise Exception.Create(ACBrStr('LarguraBarraFina deve ser de 1 a 10'));

  iLarguraBarraLarga := StrToIntDef(LarguraBarraLarga,0) ;
  if (iLarguraBarraLarga < 2) or (iLarguraBarraLarga > 30) then
     Raise Exception.Create(ACBrStr('LarguraBarraLarga deve ser de 2 a 30'));

  if ExibeCodigo = becSIM then
     FlagExibeCodigo := 'B'
  else
     FlagExibeCodigo := 'N';

  Texto := FormatarTexto( Texto ) ;

  Cmd := 'B'                                                      +
         IntToStr( ConverterUnidade( etqDots, Horizontal ) )      + ',' +
         IntToStr( ConverterUnidade( etqDots, Vertical ) )        + ',' +
         OrientacaoToStr( Orientacao )                            + ',' +
         TipoBarras                                               + ',' +
         LarguraBarraFina                                         + ',' +
         LarguraBarraLarga                                        + ',' +
         IntToStr( ConverterUnidade( etqDots, AlturaCodBarras ) ) + ',' +
         FlagExibeCodigo                                          + ',' +
         Texto ;

  ListaCmd.Add(Cmd);
end;

procedure TACBrETQEpl2.ImprimirLinha(Vertical, Horizontal, Largura,
  Altura: Integer);
begin
  Cmd := '';

  if (Vertical < 0) then
     Raise Exception.Create(ACBrStr('Informe um valor positivo para Vertical'));

  if (Horizontal < 0) then
     Raise Exception.Create(ACBrStr('Informe um valor positivo para Horizontal'));

  if (Largura < 0) then
     Raise Exception.Create(ACBrStr('Informe um valor positivo para Largura'));

  if (Altura < 0) then
     Raise Exception.Create(ACBrStr('Informe um valor positivo para Altura'));

  Cmd := 'LO'                                               +
         IntToStr( ConverterUnidade( etqDots, Horizontal) ) + ',' +
         IntToStr( ConverterUnidade( etqDots, Vertical) )   + ',' +
         IntToStr( ConverterUnidade( etqDots, Largura) )    + ',' +
         IntToStr( ConverterUnidade( etqDots, Altura) ) ;

  ListaCmd.Add(Cmd);
end;

procedure TACBrETQEpl2.ImprimirCaixa(Vertical, Horizontal, Largura, Altura,
  EspessuraVertical, EspessuraHorizontal: Integer);
begin
  Cmd := '';

  if (Vertical < 0) then
     Raise Exception.Create(ACBrStr('Informe um valor positivo para Vertical'));

  if (Horizontal < 0) then
     Raise Exception.Create(ACBrStr('Informe um valor positivo para Horizontal'));

  if (Largura < 1) then
     Raise Exception.Create(ACBrStr('Informe um valor positivo para Largura'));

  if (Altura < 1) then
     Raise Exception.Create(ACBrStr('Informe um valor positivo para Altura'));

  EspessuraHorizontal := max(EspessuraHorizontal, EspessuraVertical);
  if (EspessuraHorizontal < 1) then
     Raise Exception.Create(ACBrStr('Informe um valor positivo para Espessura'));

  Cmd := 'X' +
         IntToStr( ConverterUnidade( etqDots, Horizontal) )           + ',' +
         IntToStr( ConverterUnidade( etqDots, Vertical) )             + ',' +
         IntToStr( ConverterUnidade( etqDots, EspessuraHorizontal) )  + ',' +
         IntToStr( ConverterUnidade( etqDots, Horizontal + Largura) ) + ',' +
         IntToStr( ConverterUnidade( etqDots, Vertical   + Altura) ) ;

  ListaCmd.Add(Cmd);
end;

procedure TACBrETQEpl2.ImprimirImagem(MultiplicadorImagem, Vertical, Horizontal:
  Integer ; NomeImagem : String) ;
begin
  NomeImagem := '"' + OnlyAlphaNum(LeftStr(Trim(NomeImagem),8)) + '"' ;

  if (Vertical < 0) then
     Raise Exception.Create(ACBrStr('Informe um valor positivo para Vertical'));

  if (Horizontal < 0) then
     Raise Exception.Create(ACBrStr('Informe um valor positivo para Horizontal'));

  Cmd := 'GG'                 +
         IntToStr( ConverterUnidade( etqDots, Horizontal) ) + ',' +
         IntToStr( ConverterUnidade( etqDots, Vertical) )   + ',' +
         NomeImagem ;

  ListaCmd.Add(Cmd);
end ;

procedure TACBrETQEpl2.CarregarImagem(AStream : TStream; NomeImagem: String;
  Flipped : Boolean; Tipo: String);
Var
  Data : AnsiString ;
  Size : Integer ;
begin
  if Tipo <> 'PCX' then
     raise Exception.Create(ACBrStr('Este modelo de Impressora s� suporta Imagens no formato PCX'));

  NomeImagem := '"' + OnlyAlphaNum(LeftStr(Trim(NomeImagem),8)) + '"' ;
  Data       := '' ;   // Graphic data in 1-bit (black & white) PCX

  AStream.Position := 0 ;
  Size := AStream.Size;
  SetLength(Data,Size);
  AStream.ReadBuffer(pchar(Data)^,Size);

  Cmd := 'GK' + NomeImagem                  + LF +  // deletes graphic "NomeImagem" - Required
         'GK' + NomeImagem                  + LF +  // second delete graphic - Required
         'GM' + NomeImagem + IntToStr(Size) + LF +  // Prepares printer to receive graphic "NomeImagem";
         Data ;                                     // Data string in PCX format

  fpDevice.EnviaString( Cmd );
end;

procedure TACBrETQEpl2.Imprimir(Copias : Integer ; AvancoEtq : Integer) ;
Var
  Buffer : AnsiString ;
begin
  Cmd := '';

  if (Temperatura < 0) or (Temperatura > 15) then
     Raise Exception.Create(ACBrStr('Informe um valor entre 0 e 15 para Temperatura'));

  if (Copias < 0) or (Copias > 65535) then
     Raise Exception.Create(ACBrStr('N�mero de C�pias deve estar entre 0 e 65535'));

  Cmd := 'D' + IntToStr(Temperatura) + LF ;  // Densidade / temperatura
  if LimparMemoria then
    Cmd := Cmd + LF + 'N' + LF ;             // Limpa "Canvas" da Etiqueta

  Cmd := Cmd + 'R0,0' + LF +  // Anula as margens Horizontal e Vertical
               'ZB' ;         // ZT = Printing from top of image buffer. (PADR�O)
                              // ZB = Printing from bottom of image buffer.

  ListaCmd.Insert(0, Cmd);  // Inserindo comandos iniciais na posicao Zero

  Cmd := 'P' + IntToStr(Copias) ;
  ListaCmd.Add(Cmd);

  Buffer := ListaCmd.Text;
  Buffer := StringReplace( Buffer, sLineBreak, LF, [rfReplaceAll] );
  ListaCmd.Clear;

  fpDevice.EnviaString( Buffer );

// Verificar se precisa:
// '^@'   // Reset na impressora
// 'xa'   // For�a a detec�ao da Medida da Etiqueta e Gap
// 'Q' + IntToStr(AvancoEtq) + ',' +  // Label length
//       IntToStr(EspacoEtq) +        // Gap length
end;

end.
