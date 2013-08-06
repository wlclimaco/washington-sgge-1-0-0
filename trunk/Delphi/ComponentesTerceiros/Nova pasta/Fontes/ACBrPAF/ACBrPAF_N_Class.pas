{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }

{ Direitos Autorais Reservados (c) 2009   Isaque Pinheiro                      }

{ Colaboradores nesse arquivo:                                                 }

{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }

{  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la }
{ sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) }
{ qualquer vers�o posterior.                                                   }

{  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      }
{ ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              }

{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto}
{ com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  }
{ no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Voc� tamb�m pode obter uma copia da licen�a em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }

{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }

{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 10/04/2009: Isaque Pinheiro
|*  - Cria��o e distribui��o da Primeira Versao
*******************************************************************************}

{$I ACBr.inc}

unit ACBrPAF_N_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrTXTClass, ACBrPAFRegistros,
  ACBrPAF_N;

type
  /// TPAF_N -

  { TPAF_N }

  TPAF_N = class(TACBrTXTClass)
  private
    FRegistroN1: TRegistroN1;       /// FRegistroN1
    FRegistroN2: TRegistroN2;       /// FRegistroN2
    FRegistroN3: TRegistroN3List;   /// FRegistroN3
    FRegistroN9: TRegistroN9;       /// FRegistroN9
    FOwner     : TObject;

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create( AOwner : TObject); /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    procedure LerDadosArquivo(const APathArquivo: String);

    function WriteRegistroN1: string;
    function WriteRegistroN2: string;
    function WriteRegistroN3: string;
    function WriteRegistroN9: string;

    property RegistroN1: TRegistroN1 read FRegistroN1 write FRegistroN1;
    property RegistroN2: TRegistroN2 read FRegistroN2 write FRegistroN2;
    property RegistroN3: TRegistroN3List read FRegistroN3 write FRegistroN3;
    property RegistroN9: TRegistroN9 read FRegistroN9 write FRegistroN9;
  end;

implementation

uses ACBrSPEDUtils, ACBrUtil, ACBrPAF;

{ TPAF_N }

constructor TPAF_N.Create( AOwner : TObject);
begin
  if not (AOwner is TACBrPAF) then
     raise Exception.Create( 'Dono de TPAF_N deve ser do tipo TACBrPAF' );

  FOwner      := AOwner;

  CriaRegistros;
end;

procedure TPAF_N.CriaRegistros;
begin
  FRegistroN1 := TRegistroN1.Create;
  FRegistroN2 := TRegistroN2.Create;
  FRegistroN3 := TRegistroN3List.Create;
  FRegistroN9 := TRegistroN9.Create;

  FRegistroN9.TOT_REG := 0;
end;

destructor TPAF_N.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TPAF_N.LerDadosArquivo(const APathArquivo: String);
var
  Arquivo: TStringList;
  I: Integer;
  Linha: String;
  IdLinha: String;
begin
  if not FileExists(APathArquivo) then
    raise Exception.Create( ACBrStr( AnsiString( Format(
       'Arquivo "%s" informado n�o existe.', [APathArquivo])) ) );

  // ler os dados de um arquivo j� gravado
  Arquivo := TStringList.Create;
  try
    Arquivo.Clear;
    Arquivo.LoadFromFile(APathArquivo);

    Self.RegistroN3.Clear;
    for I := 0 to Arquivo.Count - 1 do
    begin
      Linha   := Arquivo.Strings[I];
      IdLinha := Copy(Linha, 1, 2);

      if IdLinha = 'N1' then
      begin
        Self.RegistroN1.CNPJ        := Trim(Copy(Linha, 03, 14));
        Self.RegistroN1.IE          := Trim(Copy(Linha, 17, 14));
        Self.RegistroN1.IM          := Trim(Copy(Linha, 31, 14));
        Self.RegistroN1.RAZAOSOCIAL := Trim(Copy(Linha, 45, 14));
      end
      else
      if IdLinha = 'N2' then
      begin
        Self.RegistroN2.LAUDO  := Trim(Copy(Linha, 03, 10));
        Self.RegistroN2.NOME   := Trim(Copy(Linha, 13, 50));
        Self.RegistroN2.VERSAO := Trim(Copy(Linha, 63, 10));
      end
      else
      if IdLinha = 'N3' then
      begin
        with Self.RegistroN3.New do
        begin
          NOME_ARQUIVO := Trim(Copy(Linha, 03, 50));
          MD5          := Trim(Copy(Linha, 53, 32));
        end;
      end;
    end;
  finally
    Arquivo.Free;
  end;
end;

procedure TPAF_N.LiberaRegistros;
begin
  FRegistroN1.Free;
  FRegistroN2.Free;
  FRegistroN3.Free;
  FRegistroN9.Free;
end;

procedure TPAF_N.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  /// Recriar os Registros Limpos
  CriaRegistros;
end;

function TPAF_N.WriteRegistroN1: string;
begin
  if Assigned(FRegistroN1) then
    begin
    with FRegistroN1 do
      begin
      Check(funChecaCNPJ(CNPJ),
        '(N1) ESTABELECIMENTO: O CNPJ "%s" digitado � inv�lido!', [CNPJ]);
      Check(funChecaIE(IE, UF),
        '(N1) ESTABELECIMENTO: A Inscri��o Estadual "%s" digitada � inv�lida!', [IE]);
      ///
      Result :=
             LFill('N1') +
             LFill(CNPJ, 14) +
             RFill(IE, 14) +
             RFill(IM, 14) +
             RFill(RAZAOSOCIAL, 50) +
             #13#10;
      end;
    end;
end;

function TPAF_N.WriteRegistroN2: string;
begin
  if Assigned(FRegistroN2) then
      begin
      with FRegistroN2 do
        begin
        Result :=
               LFill('N2') +
               RFill(LAUDO, 10) +
               RFill(NOME, 50) +
               RFill(VERSAO, 10) +
               #13#10;
        end;
      end;
end;

// fun��o para compara��o dos nomes de arquivo que ser�o utilizados para
// ordenar os registros N3
function CompararRegistroN3(ARegN3_1, ARegN3_2: Pointer): Integer;
begin
  Result := AnsiCompareText(
    AnsiUpperCase(TRegistroN3(ARegN3_1).NOME_ARQUIVO),
    AnsiUpperCase(TRegistroN3(ARegN3_2).NOME_ARQUIVO)
  );
end;

function TPAF_N.WriteRegistroN3: string;
var
  intFor: integer;
  strRegistroN3, NomeArquivoCompleto, ApplicationDir : String ;
begin
  strRegistroN3 := '';
  ApplicationDir := ExtractFilePath( ParamStr(0) );

  if Assigned(FRegistroN3) then
  begin
    FRegistroN3.Sort(@CompararRegistroN3);

    for intFor := 0 to FRegistroN3.Count - 1 do
    begin
      with FRegistroN3.Items[intFor] do
      begin
        // N�o informou o MD5 ? Vamos calcula-lo...
        if Trim(MD5) = '' then
        begin
          NomeArquivoCompleto := NOME_ARQUIVO ;
          if pos( PathDelim, NomeArquivoCompleto ) = 0 then  // Nao informou Path ?
             NomeArquivoCompleto := ApplicationDir + NOME_ARQUIVO;

          try
             MD5 := TACBrPAF(FOwner).GetACBrEAD.MD5FromFile( NomeArquivoCompleto );
          except
             { Ignora Provavel erro de arquivo n�o encontrado }
          end ;
        end ;

        strRegistroN3 := strRegistroN3 +
          LFill('N3') +
          RFill( UpperCase( ExtractFileName( NOME_ARQUIVO ) ), 50) +
          LFill( UpperCase( MD5 ), 32) +
          sLineBreak;
      end;

      FRegistroN9.TOT_REG := FRegistroN9.TOT_REG +  1;
    end;

    Result := strRegistroN3;
  end;
end;


function TPAF_N.WriteRegistroN9: string;
begin
  if Assigned(FRegistroN9) then
    begin
    with FRegistroN9 do
      begin
           Result :=
           LFill('N9') +
           LFill(FRegistroN1.CNPJ, 14) +
           RFill(FRegistroN1.IE, 14) +
           LFill(TOT_REG, 6, 0) +
           #13#10;
      end;
    end;
end;

end.

