unit BrvExtenso;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs;

type
  TBrvExtenso = class(TComponent)
  private
    { Private declarations }
    Unidade  : array[0..9] of string[7];
    Dezena   : array[0..9] of string[10];
    Centena  : array[0..9] of string[13];
    DsSinMil : String;
    DsPluMil : String;
    FMoeda,
    FPluralMoeda,
    FFracao,
    FPluralFracao,
    FNumero,
    FExtenso : string;
    FAcento  : Boolean;
    procedure SetNumero(Value : string);
    procedure SetMoeda(Value : string);
    procedure SetPluralMoeda(Value : string);
    procedure SetFracao(Value : string);
    procedure SetPluralFracao(Value : string);
    function  ExtUnidade(S : string) : string;
    function  ExtDezena(S : string) : string;
    function  PassarParaExtenso(S : string) : string;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
  published
    { Published declarations }
    property Moeda        : string  read FMoeda        write SetMoeda;
    property PluralMoeda  : string  read FPluralMoeda  write SetPluralMoeda;
    property Fracao       : string  read FFracao       write SetFracao;
    property PluralFracao : string  read FPluralFracao write SetPluralFracao;
    property Numero       : string  read FNumero       write SetNumero;
    property Extenso      : string  read FExtenso      write FExtenso;
    property Acentuar     : Boolean read FAcento       write FAcento;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvExtenso]);
end;

constructor TBrvExtenso.Create(AOwner : TComponent);
begin
  inherited Create(AOwner);

  {=-=-=-= Unidades =-=-=-=}
  Unidade[0]    := '';
  Unidade[1]    := 'um ';
  Unidade[2]    := 'dois ';
  Unidade[3]    := 'tres ';
  Unidade[4]    := 'quatro ';
  Unidade[5]    := 'cinco ';
  Unidade[6]    := 'seis ';
  Unidade[7]    := 'sete ';
  Unidade[8]    := 'oito ';
  Unidade[9]    := 'nove ';

  {=-=-=-= Dezenas =-=-=-=}
  Dezena[0]     := '';
  Dezena[1]     := 'dez ';
  Dezena[2]     := 'vinte ';
  Dezena[3]     := 'trinta ';
  Dezena[4]     := 'quarenta ';
  Dezena[5]     := 'cinquenta ';
  Dezena[6]     := 'sessenta ';
  Dezena[7]     := 'setenta ';
  Dezena[8]     := 'oitenta ';
  Dezena[9]     := 'noventa ';

  {=-=-=-=- Centenas =-=-=-=-=}
  Centena[0]    := '';
  Centena[1]    := 'cem ';
  Centena[2]    := 'duzentos ';
  Centena[3]    := 'trezentos ';
  Centena[4]    := 'quatrocentos ';
  Centena[5]    := 'quinhentos ';
  Centena[6]    := 'seiscentos ';
  Centena[7]    := 'setecentos ';
  Centena[8]    := 'oitocentos ';
  Centena[9]    := 'novecentos ';

  DsSinMil      := 'milhao ';
  DsPluMil      := 'milhoes ';

  FNumero       := '0' + DecimalSeparator + '00';
  FMoeda        := 'Real';
  FPluralMoeda  := 'Reais';
  FFracao       := 'centavo';
  FPluralFracao := 'centavos';
  FAcento       := False;
end;

procedure TBrvExtenso.SetNumero(Value : string);
var VrPriCen: String;
var VrSegCen: String;
var VrTerCen: String;
var Inteiro : String;
var Centavo : String;
var Len     : Integer;
var n       : Integer;  {Nº de Digitos }
var TpExtens: Byte;
begin
   if  FAcento
   then begin
       Unidade[3]    := 'três ';
       Dezena[5]     := 'cinqüenta ';
       DsSinMil      := 'milhão ';
       DsPluMil      := 'milhões ';
   end else begin
       Unidade[3]    := 'tres ';
       Dezena[5]     := 'cinquenta ';
       DsSinMil      := 'milhao ';
       DsPluMil      := 'milhoes ';
   end;
   
   TpExtens := 0;
   
   if Value = '0' + DecimalSeparator + '00' then
   begin
         FExtenso := '';
   end else
   begin
    //      Len := Length(Value);
    //      n   := Pos(DecimalSeparator, Value);

          {Formatando a Vírgula e os Centavos}

          Value := FormatFloat('########0.00', StrToFloat(Value));

          n        := Pos(DecimalSeparator, Value);
          Inteiro  := Copy(Value, 1, n - 1);
          Centavo  := Copy(Value, n + 1, 2);
          Len      := Length(Inteiro);
          VrPriCen := '   ';
          VrSegCen := '   ';
          VrTerCen := '   ';

          case Len of
               1: begin
                        VrPriCen := Value[1];
                        TpExtens := 1;
                  end;
               2: begin
                        VrPriCen := Value[1] + Value[2];
                        TpExtens := 1;
                  end;
               3: begin
                        VrPriCen := Value[1] + Value[2] + Value[3];
                        TpExtens := 1;
                  end;
               4: begin
                        VrPriCen := Value[1];
                        VrSegCen := Value[2] + Value[3] + Value[4];
                        TpExtens := 2;
                  end;
               5: begin
                        VrPriCen := Value[1] + Value[2];
                        VrSegCen := Value[3] + Value[4] + Value[5];
                        TpExtens := 2;
                  end;
               6: begin
                        VrPriCen := Value[1] + Value[2] + Value[3];
                        VrSegCen := Value[4] + Value[5] + Value[6];
                        TpExtens := 2;
                  end;
               7: begin
                        VrPriCen := Value[1];
                        VrSegCen := Value[2] + Value[3] + Value[4];
                        VrTerCen := Value[5] + Value[6] + Value[7];
                        TpExtens := 3;
                  end;
               8: begin
                        VrPriCen := Value[1] + Value[2];
                        VrSegCen := Value[3] + Value[4] + Value[5];
                        VrTerCen := Value[6] + Value[7] + Value[8];
                        TpExtens := 3;
                  end;
               9: begin
                        VrPriCen := Value[1] + Value[2] + Value[3];
                        VrSegCen := Value[4] + Value[5] + Value[6];
                        VrTerCen := Value[7] + Value[8] + Value[9];
                        TpExtens := 3;
                  end;
               else
                  FExtenso   := Value;
          end;

          case TpExtens of
               1 : FExtenso := PassarParaExtenso(VrPriCen);
               2 : begin
                          if  StrToInt(VrSegCen) <> 0 then
                          begin
                                 if VrSegCen[2] + VrSegCen[3] = '00' then
                                 begin
                                       FExtenso := PassarParaExtenso(VrPriCen) + 'mil e ' +
                                                   PassarParaExtenso(VrSegCen);
                                 end else
                                 begin
                                       FExtenso := PassarParaExtenso(VrPriCen) + 'mil, ' +
                                                   PassarParaExtenso(VrSegCen);
                                 end;
                          end else
                          begin
                                FExtenso := PassarParaExtenso(VrPriCen) + 'mil '   +
                                            PassarParaExtenso(VrSegCen);
                          end;
                   end;
               3 : begin
                          if  StrToInt(VrPriCen) = 1 then
                          begin
                                FExtenso := PassarParaExtenso(VrPriCen) + DsSinMil;
                          end else
                          begin
                                FExtenso := PassarParaExtenso(VrPriCen) + DsPluMil;
                          end;

                          if  StrToInt(VrSegCen) <> 0 then
                          begin
                                 if  (VrSegCen[2] + VrSegCen[3] = '00') and
                                     (StrToInt(VrTerCen) = 0) then
                                 begin
                                       FExtenso := FExtenso + 'e ';
                                 end else
                                 begin
                                       FExtenso[Length(FExtenso)] := DecimalSeparator;
                                       FExtenso := FExtenso + ' ';
                                 end;

                                 if  StrToInt(VrTerCen) <> 0 then
                                 begin
                                        if VrTerCen[2] + VrTerCen[3] = '00' then
                                        begin
                                              FExtenso := FExtenso +
                                                          PassarParaExtenso(VrSegCen) +
                                                          'mil e ' +
                                                          PassarParaExtenso(VrTerCen);
                                        end else
                                        begin
                                              FExtenso := FExtenso +
                                                          PassarParaExtenso(VrSegCen) +
                                                          'mil, ' +
                                                          PassarParaExtenso(VrTerCen);
                                        end;
                                 end else begin
                                    FExtenso := FExtenso + PassarParaExtenso(VrSegCen) +
                                                           'mil '   +
                                                           PassarParaExtenso(VrTerCen);
                                 end;
                          end else
                          begin
                             if  StrToInt(VrTerCen) <> 0 then
                             begin
                                   if  (VrTerCen[2] + VrTerCen[3] = '00') or
                                       (VrTerCen[1] = '0') then
                                   begin
                                         FExtenso := FExtenso + 'e ';
                                   end else
                                   begin
                                         FExtenso[Length(FExtenso)] := DecimalSeparator;
                                         FExtenso := FExtenso + ' ';
                                   end;
                             end else
                             begin
                                   FExtenso := FExtenso + 'de ';
                             end;

                             FExtenso := FExtenso + PassarParaExtenso(VrTerCen);
                          end;
                   end;
          end;

          if  StrToInt(Inteiro) <> 0 then
          begin
                 if  StrToInt(Inteiro) <> 1 then
                 begin
                       FExtenso := FExtenso + FPluralMoeda + ' ';
                 end else
                 begin
                       FExtenso := FExtenso + FMoeda + ' ';
                 end;
          end;

          if Centavo <> '00' then
          begin
                if StrToInt(Inteiro) <> 0 then
                begin
                      FExtenso := FExtenso + 'e ';
                end;

                if Centavo <> '01' then
                begin
                      FExtenso := FExtenso + PassarParaExtenso(Centavo) + FPluralFracao;
                end else
                begin
                      FExtenso := FExtenso + 'um ' + FFracao;
                end;
          end;
   end;

   FNumero := Value;
end;

function TBrvExtenso.PassarParaExtenso(S : string) : string;
begin
     case Length(S) of
          1 : Result := ExtUnidade(S[1]);
          2 : Result := ExtDezena(S[1] + S[2]);
          3 : if S[2] + S[3] = '00' then
              begin
                    Result := Centena[StrToInt(S[1])];
              end else
              begin
                    if S[1] = '1' then
                    begin
                          Result := 'cento e ' + ExtDezena(S[2] + S[3]);
                    end else
                    begin
                          if  S[1] <> '0' then
                          begin
                                Result := Centena[StrToInt(S[1])] + 'e ' +
                                          ExtDezena(S[2] + S[3]);
                          end else
                          begin
                                 Result := Centena[StrToInt(S[1])] +
                                           ExtDezena(S[2] + S[3]);
                          end;
                    end;
              end;
     end;
end;

function TBrvExtenso.ExtUnidade(S : string) : string;
begin
      Result := Unidade[StrToInt(S[1])];
end;

function TBrvExtenso.ExtDezena(S : string) : string;
begin
     if S[1] <> '1' then
     begin
         {Dezena <> 1}
         if S[2] <> '0' then
         begin
               if S[1] <> '0' then
               begin
                      Result := Dezena[StrToInt(S[1])] + 'e ' + Unidade[StrToInt(S[2])];
               end else
               begin
                      Result := Unidade[StrToInt(S[2])]
               end;
           end else
           begin
                  Result := Dezena[StrToInt(S[1])];
           end;
     end else
     begin
           {Dezena = 1}
           case S[2] of
                '0' : Result := 'dez ';
                '1' : Result := 'onze ';
                '2' : Result := 'doze ';
                '3' : Result := 'treze ';
                '4' : Result := 'quatorze ';
                '5' : Result := 'quinze ';
                '6' : Result := 'dezesseis ';
                '7' : Result := 'dezessete ';
                '8' : Result := 'dezoito ';
                '9' : Result := 'dezenove ';
           end;
     end;
end;

procedure TBrvExtenso.SetMoeda(Value : string);
begin
     FMoeda := Value;
     SetNumero(FNumero);
end;

procedure TBrvExtenso.SetPluralMoeda(Value : string);
begin
     FPluralMoeda := Value;
     SetNumero(FNumero);
end;

procedure TBrvExtenso.SetFracao(Value : string);
begin
     FFracao := Value;
     SetNumero(FNumero);
end;

procedure TBrvExtenso.SetPluralFracao(Value : string);
begin
     FPluralFracao := Value;
     SetNumero(FNumero);
end;
end.
