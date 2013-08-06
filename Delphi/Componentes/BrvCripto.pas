unit BrvCripto;

interface

uses Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, math;

type
  TBrvCripto = class(TComponent)
  private
    { Private declarations }
    FDsString : String;
    FDsCripto : String;
    procedure SetCriptografia(Value : String);
    procedure SetTexto(Value : String);
    function Inverter(DsString : String) : String;
    function HexToInt(DsString : String) : Integer;
    function Expoente(VrNumero : Integer; NrExpoen : Integer) : Integer;
    function IntToBin(VrNumero : LongInt; VrTamanh : Integer): String;
    function DecToBase(VrDecima: LongInt; const NrBase: Byte): String;
    function BaseToDec(DsValor: String; const NrBase: Byte): String;

  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
  published
    { Published declarations }
    property Text          : String  read FDsString write SetCriptografia;
    property Criptografia  : String  read FDsCripto write SetTexto;
  end;

procedure Register;

implementation

const DsSimbol = '0123456789ABCDEFGHIàâêóÃÇÜëËïÏöÖ´JKÎîµLMNOP±£©ÆØQRST{}¹³²' +
                 '[]¶§÷·¾æUVWôûãõáéíXYZÕÁÉÍÓÚabcdefghúçüÀÂÊÔÛijkl`ªº¿®òùÙÿmno=+<,>' +
                 '.:;?pqrstÑñ¬½¼¢¥¤uvwxyz!@#$%¨&()-_/|\¦»«ø';

procedure Register;
begin
      RegisterComponents('Bravo Utils', [TBrvCripto]);
end;

constructor TBrvCripto.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
end;

destructor TBrvCripto.Destroy;
begin
      inherited  destroy;
end;

procedure TBrvCripto.SetCriptografia(Value : String);
var NrCaract : Integer;
    NrDecima : Integer;
    DsString : String;
    DsStrAux : String;
    QtZero   : Byte;
    DsStrTot : String;
    DsCripto : String;
begin
      DsStrTot  := Inverter(Value);
      FDsString := Value;
      FDsCripto := '';

      While Length(DsStrTot) > 0 do
      begin
            DsString := Copy(DsStrTot, 1, 3000); //criptografia a cada 3000 caracteres
            Delete(DsStrTot, 1, 3000);

            // primeira parte da criptopgrafia

            DsCripto  := '';

            for NrCaract := 1 to Length(DsString) do
            begin
                  NrDecima  := Ord(DsString[NrCaract]) + NrCaract;
                  DsCripto  := DsCripto + IntToHex(NrDecima, 3);
            end;
            //----------------------------------------------------------------------------
            // Segunda parte da criptopgrafia
            DsString := DsCripto;
            DsCripto := '';

            for NrCaract := 1 to Length(DsString) do
            begin
                  NrDecima   := HexToInt(DsString[NrCaract]);

                  if NrDecima < 10 then
                  begin
                        DsCripto := DsCripto + '0';
                  end;

                  DsCripto  := DsCripto + IntToStr(NrDecima);
            end;
            //----------------------------------------------------------------------------
            // Terceira parte da criptopgrafia

            DsString := DsCripto;
            DsCripto := '';
            NrCaract := 1;

            while NrCaract < Length(DsString) do
            begin
                  DsStrAux := Copy(DsString, NrCaract, 9);
                  Inc(NrCaract, 9);

                  if NrCaract >= Length(DsString) then
                  begin
                        QtZero := 0;
                        while Copy(DsStrAux, 1, 1) = '0' do
                        begin
                              Inc(QtZero);
                              Delete(DsStrAux, 1, 1);
                        end;

                        if (DsStrAux = '') and (QtZero > 0) then
                        begin
                              QtZero   := QtZero -1;
                              DsStrAux := '0';
                        end;

                        DsCripto := DsCripto + IntToStr(QtZero) +
                                                       DecToBase(StrToInt(DsStrAux), 162);
                  end else
                  begin
                        DsCripto := DsCripto + DecToBase(StrToInt(DsStrAux), 162);
                  end;
            end;

            FDsCripto := FDsCripto + DsCripto + 'å';
      end;
end;

procedure TBrvCripto.SetTexto(Value : String);
Var DsString : String;
    NrCaract : Integer;
    NrAuxili : Integer;
    DsAuxili : String;
    DsStrAu1 : String;
    QtZero   : Byte;
    DsCripto : String;
    DsTexAux : String;
    DsStrAnt : String;
begin
      FDsCripto := Value;

      try
          if Pos('*', FDsCripto) = 0 then
          begin //se não tem asterisco é porque tá criptpgrafado da forma antiga
                DsString   := FDsCripto;
                FDsString  := '';
                NrCaract   := 1;

                while Length(DsString) > 0 do
                begin
                      DsAuxili  := Copy(DsString, 1, 3);
                      NrAuxili  := HexToInt(DsAuxili);
                      FDsString := FDsString  + Chr(NrAuxili  -  NrCaract);
                      Delete(DsString, 1, 3);

                      inc(NrCaract);
                end;

                FDsString := Inverter(FDsString);
          end else
          begin
                DsCripto  := FDsCripto;
                FDsString := '';

                While Length(DsCripto) > 0 do
                begin
                      NrAuxili := Pos('å', DsCripto);

                      if NrAuxili = 0 then
                      begin
                            NrAuxili := Length(DsCripto) + 1;
                      end;

                      DsString := Copy(DsCripto, 1, NrAuxili -1);
                      Delete(DsCripto, 1, NrAuxili);

                      DsTexAux := '';
                      DsStrAnt := '';

                      // primeira parte
                      While (Length(DsString) > 0) and (DsString <> DsStrAnt) do
                      begin
                            DsStrAnt := DsString;

                            DsStrAu1 := Copy(DsString, 1, Pos('*', DsString) -1);
                            Delete(DsString, 1, Pos('*', DsString));

                            if Length(DsString) > 0 then
                            begin
                                  DsStrAu1 := BaseToDec(DsStrAu1, 162);

                                  for NrCaract := Length(DsStrAu1) +1 to 9 do
                                  begin
                                        DsStrAu1 := '0' + DsStrAu1;
                                  end;
                            end else
                            begin
                                  QtZero := StrToInt(Copy(DsStrAu1, 1, 1));
                                  Delete(DsStrAu1, 1, 1);

                                  DsStrAu1  := BaseToDec(DsStrAu1, 162);

                                  for NrCaract := 1 to QtZero do
                                  begin
                                        DsStrAu1 := '0' + DsStrAu1;
                                  end;
                            end;

                           DsTexAux := DsTexAux + DsStrAu1;
                      end;

                     // Segunda parte da criptopgrafia
                     //----------------------------------------------------------------------------
                      DsString  := DsTexAux;
                      DsTexAux := '';
                      NrCaract  := 1;

                      While NrCaract <= Length(DsString) do
                      begin
                            DsTexAux := DsTexAux +
                                                 IntToHex(StrToInt(Copy(DsString, NrCaract, 2)), 1);

                            Inc(NrCaract, 2);
                      end;

                      // Quarta parte da criptopgrafia
                      DsString := DsTexAux;
                      DsTexAux := '';
                      NrCaract := 1;

                      while Length(DsString) > 0 do
                      begin
                            DsAuxili := Copy(DsString, 1, 3);
                            NrAuxili := HexToInt(DsAuxili);
                            DsTexAux := DsTexAux  + Chr(NrAuxili  -  NrCaract);
                            Delete(DsString, 1, 3);

                            inc(NrCaract);
                      end;

                      DsTexAux  := Inverter(DsTexAux);
                      FDsString := DsTexAux + FDsString;
                end;
          end;
      except
          FDsString := DsString;
      end;
end;

function TBrvCripto.Inverter(DsString : String) : String;
var NrAuxili : Integer;
    NrPosica : Integer;
begin
      NrAuxili := 1;
      NrPosica := Length(DsString);
      Result   := DsString;

      while NrPosica >= 1 do
      begin
            Result[NrAuxili] :=  DsString[NrPosica];
            Inc(NrAuxili);
            Dec(NrPosica);
      end;
end;

function TBrvCripto.HexToInt(DsString : String) : Integer;
var NrPosica : Integer;
    VrNumero : Integer;
begin
      DsString := Inverter(DsString);

   // =-=-=-=-=
      Result   := 0;
      NrPosica := Length(DsString);

      while NrPosica > 0 do
      begin
            case DsString[NrPosica] of
                 'A' : VrNumero := 10;
                 'B' : VrNumero := 11;
                 'C' : VrNumero := 12;
                 'D' : VrNumero := 13;
                 'E' : VrNumero := 14;
                 'F' : VrNumero := 15;
                 else  VrNumero := StrToInt(DsString[NrPosica]);
            end;

            Result := Result + (VrNumero * Expoente(16, NrPosica -1));

            Dec(NrPosica);
      end;
end;

function TBrvCripto.Expoente(VrNumero : Integer; NrExpoen : Integer) : Integer;
var NrAuxili : Integer;
begin
      if  NrExpoen = 0 then
      begin
            Result := 1;
      end else
      begin
            Result   := VrNumero;
            NrAuxili := 2;

            while NrAuxili <= NrExpoen do
            begin
                  Result := Result * VrNumero;

                  inc(NrAuxili);
            end;
      end;
end;

function TBrvCripto.IntToBin(VrNumero : LongInt; VrTamanh : Integer): String;
Var NrIndice : Integer;
begin
      Result := '';

      for NrIndice := VrTamanh downto 0 do
      begin
            if VrNumero and (1 shl NrIndice) <> 0 then
            begin
                  Result := Result + '1';
            end else
            begin
                  Result := Result + '0';
            end;
      end;
end;

function TBrvCripto.DecToBase(VrDecima: LongInt; const NrBase: Byte): String;
var VrRestan : Byte;
begin
      Result := '';

      While VrDecima >= NrBase do
      begin
            VrRestan  := VrDecima mod NrBase;
            Result    := DsSimbol[VrRestan + 1] + Result;
            VrDecima  := VrDecima div NrBase;
      end;

     if VrDecima > 0 then
     begin
           Result := DsSimbol[VrDecima + 1] + Result + '*';
     end;
end;

function TBrvCripto.BaseToDec(DsValor: String; const NrBase: Byte): String;
var NrIndice : Byte;
    NrPosStr : Real; ///posição para calculo exponencial
    VrResDec : Real;
    NrPosCar : Byte; ///posição do caracter na string
begin
      Result   := '';
      VrResDec := 0;

      For NrIndice := 1 to Length(DsValor) do
      begin
            NrPosCar := Pos(DsValor[NrIndice], DsValor);
            NrPosStr := Length(DsValor) - NrPosCar;

            VrResDec := VrResDec + (Pos(DsValor[NrIndice], DsSimbol) - 1) *
                                          Power(StrToFloat(IntToStr(NrBase)), NrPosStr);


            Delete(DsValor, NrPosCar, 1);
            Insert('*', DsValor, NrPosCar);


{            if DsValor[NrIndice] in ['0' .. '9'] then
            begin
                  VrResDec := VrResDec + (StrToInt(DsValor[NrIndice]) *
                                          Power(StrToFloat(IntToStr(NrBase)), NrPosStr));
            end else
            begin
                  VrResDec := VrResDec + (Pos(DsValor[NrIndice], DsSimbol) - 1) *
                                          Power(StrToFloat(IntToStr(NrBase)), NrPosStr);
            end;   }
      end;

      Result := FloatToStr(VrResDec);
end;



end.



