unit BrvDigito;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs;

type
  TTpDigito = (DvModulo10, DvModulo11);
  TBrvDigito = class(TComponent)
  private
    { Private declarations }
    FTpDigito : TTpDigito; // Tipo de cálculo do dígito
    FCdCodigo : String;    // Código que será submetido o código
    FDgDigito : String;    // Dígito de retorno do cálculo
    FDgMaiNov : Char;      // Caracter a ser retornado quando dígito maior que 9
    FDgIguZer : Char;      // Caracter a ser retornado quando dígito igual a zero
    FQtDigito : Byte;      // Quantidade de dígitos a calcular
    procedure SetCodigo(Value : String);
    procedure SetModulo(Value : TTpDigito);
    procedure SetQtdeDigito(Value : Byte);
    procedure SetDigitoZero(Value : Char);
    procedure SetDigitoMaiorNove(Value : Char);
    procedure CalcularDigito;
    procedure CalcularDigitoModulo10;
    procedure CalcularDigitoModulo11;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
    function    ValidaCPFCNPJ(pNrCPFCNP : String) : Boolean;
  published
    { Published declarations }
    property Modulo            : TTpDigito read FTpDigito write SetModulo;
    property Codigo            : String    read FCdCodigo write SetCodigo;
    property Digito            : String    read FDgDigito write FDgDigito;
    property DigitoIgualZero   : Char      read FDgIguZer write SetDigitoZero;
    property DigitoMaiorNove   : Char      read FDgMaiNov write SetDigitoMaiorNove;
    property QuantidadeDigitos : Byte      read FQtDigito write SetQtdeDigito;
  end;

procedure Register;

implementation

procedure Register;
begin
      RegisterComponents('Bravo Utils', [TBrvDigito]);
end;

constructor TBrvDigito.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
      FQtDigito := 1;
      FDgMaiNov := '0';
      FDgIguZer := '0';
end;

destructor TBrvDigito.Destroy;
begin
      inherited  destroy;
end;

procedure TBrvDigito.SetCodigo(Value : String);
begin
      FCdCodigo := Value;
      CalcularDigito;
end;

procedure TBrvDigito.SetModulo(Value : TTpDigito);
begin
       FTpDigito := Value;
       CalcularDigito;
end;

procedure TBrvDigito.SetQtdeDigito(Value : Byte);
begin
       FQtDigito := Value;
       CalcularDigito;
end;

function TBrvDigito.ValidaCPFCNPJ(pNrCPFCNP: String): Boolean;
var lDsDigito : String;
begin
      lDsDigito := Copy(pNrCPFCNP, length(pNrCPFCNP), 1);
      Delete(pNrCPFCNP, length(pNrCPFCNP), 1);

      lDsDigito := Copy(pNrCPFCNP, length(pNrCPFCNP), 1) + lDsDigito;
      Delete(pNrCPFCNP, length(pNrCPFCNP), 1);

      DigitoIgualZero   := '0';
      DigitoMaiorNove   := '0';
      Modulo            := DvModulo11;
      QuantidadeDigitos := 2;
      Digito            := '';
      Codigo            := '';
      Codigo            := pNrCPFCNP;

      Result := lDsDigito = Digito;
end;

procedure TBrvDigito.SetDigitoZero(Value : Char);
begin
     FDgIguZer := Value;
     CalcularDigito;
end;

procedure TBrvDigito.SetDigitoMaiorNove(Value : Char);
begin
      FDgMaiNov := Value;
      CalcularDigito;
end;

procedure TBrvDigito.CalcularDigito;
begin
      if FCdCodigo <> '' then
      begin
            case FTpDigito of
                 DvModulo10 : CalcularDigitoModulo10;
                 DvModulo11 : CalcularDigitoModulo11;
                 else raise Exception.Create('Tipo de módulo a ser ' +
                                                  'calculado não foi definido');
            end;
      end;
end;

procedure TBrvDigito.CalcularDigitoModulo10;
var CdCodigo : String;
    NrDigito : Byte;
    QtDigito : Byte;
    VrFator  : Byte;
    VrMultip : Integer;
    VrMulAux : String;
    VrSoma   : Integer;
    VrResto  : Byte;
    VrDigito : Byte;
begin
      CdCodigo  := FCdCodigo;
      FDgDigito := '';

      for QtDigito := 1 to FQtDigito do
      begin
            VrFator  := 2;
            VrSoma   := 0;

            for NrDigito := Length(CdCodigo) downto 0 do
            begin
                  if CdCodigo[NrDigito] in ['0' .. '9'] then
                  begin
                        VrMultip := StrToInt(CdCodigo[NrDigito]) * VrFator;

                        if Length(IntToStr(VrMultip)) > 1 then
                        begin
                              VrMulAux := IntToStr(VrMultip);
                              VrSoma := VrSoma + StrToInt(VrMulAux[1]) +
                                                 StrToInt(VrMulAux[2]);
                        end else
                        begin
                              VrSoma := VrSoma + VrMultip;
                        end;
                        
                        inc(VrFator);

                        if VrFator > 2 then
                        begin
                              VrFator := 1;
                        end;
                  end;
            end;

            VrResto  := VrSoma mod 10;
            VrDigito := 10 - VrResto;

            if VrDigito > 9 then
            begin
                  FDgDigito := FDgDigito + FDgMaiNov;
                  CdCodigo  := CdCodigo  + FDgMaiNov;
            end else
            begin
                  if VrDigito = 0 then
                  begin
                        FDgDigito := FDgDigito + FDgIguZer;
                        CdCodigo  := CdCodigo  + FDgIguZer;
                  end else
                  begin
                        FDgDigito := FDgDigito + IntToStr(VrDigito);
                        CdCodigo  := CdCodigo  + IntToStr(VrDigito);
                  end;
            end;
      end;
end;

procedure TBrvDigito.CalcularDigitoModulo11;
var CdCodigo : String;
    NrDigito : Byte;
    QtDigito : Byte;
    VrFator  : Byte;
    VrSoma   : Integer;
    VrResto  : Byte;
    VrDigito : Byte;
begin
      CdCodigo  := FCdCodigo;
      FDgDigito := '';

      for QtDigito := 1 to FQtDigito do
      begin
            VrFator  := 2;
            VrSoma   := 0;

            for NrDigito := Length(CdCodigo) downto 0 do
            begin
                  if CdCodigo[NrDigito] in ['0' .. '9'] then
                  begin
                        VrSoma := VrSoma +
                                 (StrToInt(CdCodigo[NrDigito]) * VrFator);
                        inc(VrFator);

                        if VrFator > 9 then
                        begin
                              VrFator := 2;
                        end;
                  end;
            end;

            VrResto  := VrSoma mod 11;
            VrDigito := 11 - VrResto;

            if VrDigito > 9 then
            begin
                  FDgDigito := FDgDigito + FDgMaiNov;
                  CdCodigo  := CdCodigo  + FDgMaiNov;
            end else
            begin
                  if VrDigito = 0 then
                  begin
                        FDgDigito := FDgDigito + FDgIguZer;
                        CdCodigo  := CdCodigo  + FDgIguZer;
                  end else
                  begin
                        FDgDigito := FDgDigito + IntToStr(VrDigito);
                        CdCodigo  := CdCodigo  + IntToStr(VrDigito);
                  end;
            end;
      end;
end;

end.
