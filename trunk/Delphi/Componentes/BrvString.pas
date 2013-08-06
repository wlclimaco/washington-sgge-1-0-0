unit BrvString;

interface

uses
  SysUtils, Classes, StdCtrls;

type
  TBrvString = class(TComponent)
  private
    { Private declarations }
    gLstSplit : TStrings;
    procedure   SetResultSplit(Value: TStrings);
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    function    RetirarAcentos(pDsString : String) : String;
    function    PreparaXML(pDsString : String) : String;
    function    FormatarStringComAcento(pDsString : String; pQtCaract : Integer) : String;
    function    FormatarStringSemAcento(pDsString : String; pQtCaract : Integer) : String;
    function    FormatarNumero(pVrNumero : String;  pQtCaract : Integer;
                               pSnZerEsq : Boolean) : String;
    function    FormatarValor(pVrValor : String; pQtCaract : integer) : string;
    function    AlinharDireita(DsString: String; QtCaract: Byte): String;
    procedure   Split(pDsOrigem : String; pDsSepara : String);
    function    CharToStr(pNmChar : array of char; pQtCaract : Integer): String;
    procedure   StrToChar(pNmString : String; var pNmChar: array of char;
                          pQtCaract : Integer);
    function  BrLowerCase(pDsCaract: string): string;
    function  BrLwCase(pDsCaract: Char): Char;
    function  BrUpCase(pDsCaract: Char): Char;
    function  BrUpperCase(pDsCaract: string): string;
    function  JustificarTexto(pTxOrigem : AnsiString; pTmLinha : Byte) : AnsiString;
    function  Justificar(pDsLinha: AnsiString; pTmLinha: Integer) : AnsiString;
    function  ProximaPalavra(var pDsLinha : AnsiString) : AnsiString;
    function  RemoverZerosEsquerda(pVrNumero: string): string;
    function  SomenteNumero(pNrNumero : String) : String;
    function  SomenteLetrasNumeros(DsString: String): String;

  published
    { Published declarations }
    property    BrSplitLista    : TStrings read gLstSplit write SetResultSplit;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvString]);
end;

{ TBrvString }

constructor TBrvString.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);
      gLstSplit := TStringList.Create;
end;

destructor TBrvString.Destroy;
begin
      inherited Destroy;

      FreeAndNil(gLstSplit);
end;

function TBrvString.RemoverZerosEsquerda(pVrNumero: string) : string;
var lVrNumero: String;
begin
      lVrNumero := pVrNumero;

      while lVrNumero[1] = '0' do
      begin
            lVrNumero := copy(lVrNumero, 2, Length(lVrNumero));
            if (Trim(lVrNumero) = '') then
            begin
                  lVrNumero := '.';
            end;

      end;

      if (lVrNumero = '.') then
      begin
            lVrNumero := '0';
      end;

      result := lVrNumero;
end;

function TBrvString.FormatarNumero(pVrNumero: String; pQtCaract: Integer;
  pSnZerEsq: Boolean): String;
begin
      Result  := pVrNumero;

      if Length(pVrNumero) > pQtCaract then
      begin
            Delete(Result, 1, (Length(pVrNumero) - pQtCaract));
      end;

      if Length(Result) < pQtCaract then
      begin
            if  pSnZerEsq then
            begin
                  Result := StringOfChar('0', pQtCaract - Length(Result)) + Result;
            end else
            begin
                  Result := StringOfChar(' ', pQtCaract - Length(Result)) + Result;
            end;
      end;
end;

function TBrvString.FormatarStringComAcento(pDsString: String; pQtCaract: Integer): String;
begin
      Result := pDsString;

      if Length(pDsString) > pQtCaract then
      begin
            Delete(Result, pQtCaract + 1, (Length(pDsString) - pQtCaract));
      end;

      if Length(Result) < pQtCaract then
      begin
            Result := Result + StringOfChar(' ', pQtCaract - Length(Result));
      end;
end;

function TBrvString.FormatarStringSemAcento(pDsString: String; pQtCaract: Integer): String;
begin
      Result := RetirarAcentos(pDsString);
      Result := FormatarStringComAcento(Result, pQtCaract);
end;

function TBrvString.FormatarValor(pVrValor: String; pQtCaract: integer): string;
var lNrCaract : integer;
    lDsFormat : string;
begin
      lNrCaract := 1;

      if pVrValor <> '' then
      begin
            pVrvalor := CurrToStrF(StrToFloat(pVrValor), FFCurrency, CurrencyDecimals);
      end;

      while (lNrCaract <= pQtCaract) and (lNrCaract <= Length(pVrValor)) do
      begin
            lDsFormat := lDsFormat + pVrValor[lNrCaract];
            inc(lNrCaract);
      end;

      if lNrCaract <= pQtCaract then
      begin
            while lNrCaract <= pQtCaract do
            begin
                  lDsFormat := ' ' + lDsFormat;
                  inc(lNrCaract);
            end;
      end;

      Result := lDsFormat;
end;

function  TBrvString.AlinharDireita(DsString : String; QtCaract : Byte) : String;
begin
      Result := Trim(DsString);

      while Length(Result) < QtCaract do
      begin
            Result := ' ' + Result;
      end;
end;

function TBrvString.RetirarAcentos(pDsString: String): String;
begin
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-  Minusculas  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      pDsString := StringReplace(pDsString, 'á', 'a', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'é', 'e', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'í', 'i', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ó', 'o', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ú', 'u', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ä', 'a', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ë', 'e', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ï', 'i', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ö', 'o', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ü', 'u', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'à', 'a', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'è', 'e', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ì', 'i', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ò', 'o', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ù', 'u', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ã', 'a', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'õ', 'o', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'â', 'a', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ê', 'e', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'î', 'i', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ô', 'o', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'û', 'u', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ç', 'c', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'ñ', 'n', [rfReplaceAll]);

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-  Maiúsculas  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      pDsString := StringReplace(pDsString, 'Á', 'A', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'É', 'E', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Í', 'I', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ó', 'O', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ú', 'U', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ä', 'A', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ë', 'E', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ï', 'I', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ö', 'O', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ü', 'U', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'À', 'A', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'È', 'E', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ì', 'I', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ò', 'O', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ù', 'U', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ã', 'A', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Õ', 'O', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Â', 'A', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ê', 'E', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Î', 'I', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ô', 'O', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Û', 'U', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ç', 'C', [rfReplaceAll]);
      pDsString := StringReplace(pDsString, 'Ñ', 'N', [rfReplaceAll]);

      Result := pDsString;
end;

procedure TBrvString.SetResultSplit(Value: TStrings);
begin
      gLstSplit.Assign(Value);
end;

procedure TBrvString.Split(pDsOrigem: String; pDsSepara: String);
var lDsOcorre : String;
begin
      pDsOrigem := Trim(pDsOrigem);
      gLstSplit.Clear;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-= Carregando itens de retorno
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      while pDsOrigem <> '' do
      begin
            if Pos(pDsSepara, pDsOrigem) > 0 then
            begin
                  lDsOcorre := Copy(pDsOrigem, 1, Pos(pDsSepara, pDsOrigem) - 1);
                  Delete(pDsOrigem, 1, Pos(pDsSepara, pDsOrigem)- 1);
                  Delete(pDsOrigem, 1, Length(pDsSepara));
            end else
            begin
                   lDsOcorre := pDsOrigem;
                   pDsOrigem := '';
            end;

            gLstSplit.Add(lDsOcorre);
      end;
end;

procedure TBrvString.StrToChar(pNmString : String; var pNmChar : array of char;
                               pQtCaract : Integer);
var lNrCaract : Integer;
begin
      while Length(pNmString) <= pQtCaract do
      begin
            pNmString := pNmString + ' ';
      end;

      for lNrCaract := 1 to Length(pNmString) do
      begin
            pNmChar[lNrCaract] := pNmString[lNrCaract];
      end;
end;

function  TBrvString.CharToStr(pNmChar: array of char; pQtCaract : Integer) : String;
var lNrCaract : Integer;
begin
      Result := StringOfChar(' ', pQtCaract + 10);

      for lNrCaract := 1 to pQtCaract do
      begin
            Result[lNrCaract] := pNmChar[lNrCaract];
      end;

      Result := Trim(Result);
end;

function TBrvString.BrLwCase(pDsCaract : Char) : Char;
begin
      case pDsCaract of
           'Á' : Result := 'á';
           'É' : Result := 'é';
           'Í' : Result := 'í';
           'Ó' : Result := 'ó';
           'Ú' : Result := 'ú';
           'Ã' : Result := 'ã';
           'Õ' : Result := 'õ';
           'Â' : Result := 'â';
           'Ê' : Result := 'ê';
           'Î' : Result := 'î';
           'Ô' : Result := 'ô';
           'Û' : Result := 'û';
           'Ä' : Result := 'ä';
           'Ë' : Result := 'ë';
           'Ï' : Result := 'ï';
           'Ö' : Result := 'ö';
           'Ü' : Result := 'ü';
           'À' : Result := 'à';
           'È' : Result := 'è';
           'Ì' : Result := 'ì';
           'Ò' : Result := 'ò';
           'Ù' : Result := 'ù';
           'Ç' : Result := 'ç';
           else  Result := LowerCase(pDsCaract + ' ')[1];
      end;
end;

function TBrvString.BrUpCase(pDsCaract : Char) : Char;
begin
      case pDsCaract of
           'á' : Result := 'Á';
           'é' : Result := 'É';
           'í' : Result := 'Í';
           'ó' : Result := 'Ó';
           'ú' : Result := 'Ú';
           'ã' : Result := 'Ã';
           'õ' : Result := 'Õ';
           'â' : Result := 'Â';
           'ê' : Result := 'Ê';
           'î' : Result := 'Î';
           'ô' : Result := 'Ô';
           'û' : Result := 'Û';
           'ä' : Result := 'Ä';
           'ë' : Result := 'Ë';
           'ï' : Result := 'Ï';
           'ö' : Result := 'Ö';
           'ü' : Result := 'Ü';
           'à' : Result := 'À';
           'è' : Result := 'È';
           'ì' : Result := 'Ì';
           'ò' : Result := 'Ò';
           'ù' : Result := 'Ù';
           'ç' : Result := 'Ç';
           else  Result := UpperCase(pDsCaract + ' ')[1];
      end;
end;

function TBrvString.BrUpperCase(pDsCaract : string): string;
var
    lNoCaract : Integer;
begin
      for lNoCaract := 1 to Length(pDsCaract) do
      begin
            pDsCaract[lNoCaract] := BrUpCase(pDsCaract[lNoCaract]);
      end;

      Result := pDsCaract;
end;

function TBrvString.BrLowerCase(pDsCaract : string): string;
var
    lNoCaract : Integer;
begin
      for lNoCaract := 1 to Length(pDsCaract) do
      begin
            pDsCaract[lNoCaract] := BrLwCase(pDsCaract[lNoCaract]);
      end;

      Result := pDsCaract;
end;

function TBrvString.JustificarTexto(pTxOrigem : AnsiString; pTmLinha : Byte) : AnsiString;
var lDslinha : AnsiString;
    lTxJusti : TStrings;
    lDsPalav : AnsiString;
begin
      try
          lTxJusti := TStringList.Create;
          lTxJusti.Clear;

          lDslinha := ProximaPalavra(pTxOrigem);

          while Length(pTxOrigem) > 0 do
          begin
                lDsPalav := ProximaPalavra(pTxOrigem);

                if Length(lDslinha + lDsPalav) > pTmLinha then
                begin
                      lTxJusti.Add(Justificar(lDslinha, pTmLinha));
                      lDslinha := lDsPalav;
                end else
                begin
                      lDslinha := lDslinha + ' ' + lDsPalav;
                end;
          end;

          lTxJusti.Add(lDslinha);

          Result := lTxJusti.Text;
      finally
          FreeAndNil(lTxJusti);
      end;
end;

function TBrvString.Justificar(pDsLinha: AnsiString; pTmLinha: Integer) : AnsiString;
var lNrIndice   : Integer;
begin
      Result    := pDsLinha;
      lNrIndice := 1;

      if Pos(' ', pDsLinha) > 0  then
      begin
            while Length(Result) < pTmLinha do
            begin
                  if lNrIndice >= Length(Result) then
                  begin
                        lNrIndice := 1;
                  end;

                  while (Result[lNrIndice] <> ' ') and
                        (Length(Result) > lNrIndice) do
                  begin
                        Inc(lNrIndice);
                  end;

                  if Result[lNrIndice] = ' ' then
                  begin
                        Insert(' ', Result, lNrIndice);
                  end;

                  while (Result[lNrIndice] = ' ') and (Length(Result) > lNrIndice) do
                  begin
                        Inc(lNrIndice);
                  end;

                  Inc(lNrIndice);
            end;
      end;
end;

function TBrvString.PreparaXML(pDsString: String): String;
begin
      Result := RetirarAcentos(pDsString);
      Result := StringReplace(Result, '/', '', [rfReplaceAll]);
      Result := StringReplace(Result, 'º', '', [rfReplaceAll]);
      Result := StringReplace(Result, '<', '', [rfReplaceAll]);
      Result := StringReplace(Result, '>', '', [rfReplaceAll]);
      Result := StringReplace(Result, '"', '', [rfReplaceAll]);
end;

function TBrvString.ProximaPalavra(var pDsLinha : AnsiString) : AnsiString;
begin
      Result  := '';

      pDsLinha := Trim(pDsLinha);

      while (pDsLinha <> '') and (pDsLinha[1] <> ' ') do
      begin
            if (pDsLinha[1] <> #13) and (pDsLinha[1] <> #10) then
            begin
                  Result := Result + pDsLinha[1];
            end;

            Delete(pDsLinha, 1, 1);
      end;
end;

function TBrvString.SomenteNumero(pNrNumero : String) : String;
var lNrIndice : Integer;
begin
      lNrIndice  := 1;

      while lNrIndice <= Length(pNrNumero) do
      begin
            if  not (pNrNumero[lNrIndice] in ['0'..'9']) then
            begin
                  Delete(pNrNumero, lNrIndice, 1);
            end else
            begin
                  Inc(lNrIndice);
            end;
      end;

      Result  := pNrNumero;
end;

function TBrvString.SomenteLetrasNumeros(DsString: String): String;
var NrPosStr : integer;
begin
      NrPosStr := 1;

      while NrPosStr <= Length(DsString) do
      begin
            if not (DsString[NrPosStr] in ['0'..'9', 'A'..'Z', 'a'..'z']) then
            begin
                  Delete(DsString, NrPosStr, 1);
            end else
            begin
                  NrPosStr := NrPosStr + 1;
            end;
      end;
      Result := DsString;
end;



end.
