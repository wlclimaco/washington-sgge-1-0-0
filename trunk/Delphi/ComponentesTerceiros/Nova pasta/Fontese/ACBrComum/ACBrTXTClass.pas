{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009   Isaque Pinheiro                      }
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
|* 10/04/2009: Isaque Pinheiro
|*  - Cria��o e distribui��o da Primeira Versao
*******************************************************************************}

{$I ACBr.inc}

unit ACBrTXTClass;

interface

uses {$IFDEF MSWINDOWS}
      Windows,
    {$ENDIF}
      SysUtils, Classes, DateUtils, Math;

type
  TErrorEvent = procedure(const MsnError: AnsiString) of object;

  { TACBrTXTClass }

  TACBrTXTClass = class
  private
    FLinhasBuffer: Integer;
    FNomeArquivo: String;
    FOnError: TErrorEvent;
    FDelimitador: String;     /// Caracter delimitador de campos
    FTrimString: boolean;     /// Retorna a string sem espa�os em branco iniciais e finais
    FCurMascara: String;      /// Mascara para valores tipo currency

    FConteudo : TStringList;

    procedure AssignError(MsnError: String);
    procedure SetLinhasBuffer(const AValue: Integer);
    procedure SetNomeArquivo(const AValue: String);
  public
    constructor create ;
    destructor Destroy ; override ;

    procedure WriteBuffer ;
    procedure SaveToFile ;
    procedure LoadFromFile ;
    procedure Reset ;
    function Add( const AString : AnsiString; AddDelimiter : Boolean = True ) : Integer;
    function RFill(Value: String;
                   Size: Integer = 0;
                   Caracter: Char = ' '): String; overload;
    function LFill(Value: String;
                   Size: Integer = 0;
                   Nulo: Boolean = false;
                   Caracter: Char = '0'): String; overload;
    function DFill(Value: Double;
                   Decimal: Integer = 2;
                   Nulo: Boolean = false): String;
    function LFill(Value: Currency;
                   Size: Integer;
                   Decimal: Integer = 2;
                   Nulo: Boolean = false;
                   Caracter: Char = '0';
                   Mascara: String = ''): String; overload;
    function LFill(Value: Integer; Size: Integer; Nulo: Boolean = false; Caracter: Char = '0'): String; overload;
    function LFill(Value: TDateTime; Mask: String = 'ddmmyyyy'): String; overload;
    ///
    procedure Check(Condicao: Boolean; const Msg: String); overload;
    procedure Check(Condicao: Boolean; Msg: String; Fmt: array of const); overload;
    ///
    property NomeArquivo : String read FNomeArquivo write SetNomeArquivo ;
    property LinhasBuffer : Integer read FLinhasBuffer write SetLinhasBuffer ;
    property Delimitador: String read FDelimitador write FDelimitador;
    property TrimString: boolean read FTrimString write FTrimString;
    property CurMascara: String read FCurMascara write FCurMascara;
    property OnError: TErrorEvent read FOnError write FOnError;

    property Conteudo : TStringList read FConteudo ;
  end;

implementation

Uses ACBrUtil ;

(* TACBrTXTClass *)

constructor TACBrTXTClass.create;
begin
   FConteudo     := TStringList.Create ;
   FLinhasBuffer := 0 ; // 0 = Sem tratamento de buffer
end;

destructor TACBrTXTClass.destroy;
begin
  FConteudo.Free;

  inherited destroy;
end;

procedure TACBrTXTClass.WriteBuffer;
var
  FS : TFileStream ;
begin
  if NomeArquivo = '' then
     raise Exception.Create( ACBrStr('"NomeArquivo" n�o especificado') ) ;

  if (not FileExists( NomeArquivo )) then
     {$IFDEF UNICODE}
      WriteToTXT( NomeArquivo, FConteudo.Text, False, False )
     {$ELSE}
      FConteudo.SaveToFile( NomeArquivo ) // SaveToFile nativo deixa arquivo como UTF-8
     {$ENDIF}
  else
   begin
      FS := TFileStream.Create( NomeArquivo, fmOpenReadWrite or fmShareExclusive );
      try
         FS.Seek(0, soFromEnd);  // vai para EOF
         FConteudo.SaveToStream( FS );
      finally
         FS.Free ;
      end;
   end;

  if (FLinhasBuffer > 0) then
     FConteudo.Clear;
end;

procedure TACBrTXTClass.SaveToFile ;
begin
  WriteBuffer;
end;

procedure TACBrTXTClass.LoadFromFile ;
begin
   if NomeArquivo = '' then
      raise Exception.Create( ACBrStr('"Nome do Arquivo" n�o especificado') ) ;

   FConteudo.LoadFromFile( NomeArquivo );
end;

procedure TACBrTXTClass.Reset;
begin
   FConteudo.Clear;

   if FNomeArquivo <> '' then
      if FileExists( FNomeArquivo ) then
         DeleteFile( FNomeArquivo );
end;

function TACBrTXTClass.Add(const AString: AnsiString; AddDelimiter: Boolean
   ): Integer;
Var
  S : AnsiString ;
begin
   S      := Trim( AString ) ;
   Result := -1 ;
   if S = '' then exit ;

   if AddDelimiter then
      S := S + Delimitador;

   Result := FConteudo.Add( S );

   if FLinhasBuffer > 0 then
      if FConteudo.Count >= FLinhasBuffer then
         WriteBuffer ;
end;

procedure TACBrTXTClass.Check(Condicao: Boolean; const Msg: String);
begin
  if not Condicao then AssignError(Msg);
end;

procedure TACBrTXTClass.Check(Condicao: Boolean; Msg: String; Fmt: array of const);
begin
  Check(Condicao, Format(Msg, Fmt));
end;

function TACBrTXTClass.RFill(Value: String;
                             Size: Integer = 0;
                             Caracter: Char = ' '): String;
begin
  /// Se a propriedade TrimString = true, Result retorna sem espa�os em branco
  /// iniciais e finais.
  if FTrimString then
     Result := Trim(Value);

  if (Size > 0) and (Length(Value) > Size) then
     Result := Copy(Value, 1, Size)
  else
     Result := Value + StringOfChar(Caracter, Size - Length(Value));

  if Caracter = '?' then
     Result := FDelimitador + StringReplace(Result, ' ', Caracter, [rfReplaceAll])
  else
     Result := FDelimitador + Result;
end;

function TACBrTXTClass.LFill(Value: String;
                             Size: Integer = 0;
                             Nulo: Boolean = false;
                             Caracter: Char = '0'): String;
begin
  if (Nulo) and (Length(Value) = 0) then
  begin
     Result := FDelimitador;
     Exit;
  end;
  /// Se a propriedade TrimString = true, Result retorna sem espa�os em branco
  /// iniciais e finais.
  if FTrimString then
     Result := Trim(Value);

  if (Size > 0) and (Length(Value) > Size) then
     Result := Copy(Value, 1, Size)
  else
     Result := StringOfChar(Caracter, Size - length(Value)) + Value;

  Result := FDelimitador + Result;
end;

function TACBrTXTClass.LFill(Value: Currency;
                        Size: Integer;
                        Decimal: Integer = 2;
                        Nulo: Boolean = false;
                        Caracter: Char = '0';
                        Mascara: String = ''): String;
var
intFor, intP: Integer;
strCurMascara: string;
begin
  strCurMascara := FCurMascara;
  // Se recebeu uma mascara como parametro substitue a principal
  if Mascara <> '' then
     strCurMascara := Mascara;

  /// Se o parametro Nulo = true e Value = 0, ser� retornado '|'
  if (Nulo) and (Value = 0) then
  begin
     Result := FDelimitador;
     Exit;
  end;
  intP := 1;
  for intFor := 1 to Decimal do
  begin
     intP := intP * 10;
  end;

  if (strCurMascara <> '#') and (strCurMascara <> '') then
     Result := FDelimitador + FormatCurr(strCurMascara, Value)
  else
     Result := LFill(Trunc(Value * intP), Size, Nulo, Caracter);
end;

function TACBrTXTClass.DFill(Value: Double;
                        Decimal: Integer = 2;
                        Nulo: Boolean = false): String;
begin
  /// Se o parametro Nulo = true e Value = 0, ser� retornado '|'
  if (Nulo) and (Value = 0) then
  begin
     Result := FDelimitador;
     Exit;
  end;
  Result := FDelimitador + FormatFloat('#0.' + StringOfChar('0', Decimal), Value); //FormatCurr n�o permite precis�o acima de 4 casas decimais
end;

function TACBrTXTClass.LFill(Value: Integer; Size: Integer; Nulo: Boolean = false; Caracter: Char = '0'): String;
begin
  /// Se o parametro Nulo = true e Value = 0, ser� retornado '|'
  if (Nulo) and (Value = 0) then
  begin
     Result := FDelimitador;
     Exit;
  end;
  Result := LFill(IntToStr(Value), Size, False, Caracter);
end;

function TACBrTXTClass.LFill(Value: TDateTime; Mask: String = 'ddmmyyyy'): String;
begin
  /// Se o parametro Value = 0, ser� retornado '|'
  if (Value = 0) then
  begin
     Result := FDelimitador;
     Exit;
  end;
  Result := FDelimitador + FormatDateTime(Mask, Value);
end;

procedure TACBrTXTClass.AssignError(MsnError: String);
begin
  if Assigned(FOnError) then FOnError( ACBrStr(MsnError) );
end;

procedure TACBrTXTClass.SetLinhasBuffer(const AValue: Integer);
begin
   if FLinhasBuffer = AValue then exit;
   FLinhasBuffer := max(AValue,0);   // Sem valores negativos
end;

procedure TACBrTXTClass.SetNomeArquivo(const AValue: String);
begin
   if FNomeArquivo = AValue then exit;
   FNomeArquivo := AValue;
end;

end.
