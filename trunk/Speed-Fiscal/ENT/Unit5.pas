unit unit5;

interface

type
 TProduto = Class
    private
    FCODPRO,FCNB : Integer;
    FPRODUTO,FREF,FCLASS,FINDICE,FUNIDADE,FLOCAL,FRAzao,FSTATUS,FMSG,FAPELIDO :String;
    FVALOR,FCODIND,fcodcli,FAQUIS, FICMS,FFINAN,FFRETE,FCUSTO,FPRECOVENDA,FESTOQUE,FESTOQUEANT :Double;
    FPRAZO:TDateTime;
    protected
      function  GetCODPRO    : Integer;
      function  GetCNB    : Integer;
      function  GetPRODUTO : string;
      function  GetREF : string;
      function  GetCLASS : string;
      function  GetUNIDADE : string;
      function  GetLOCAL : string;
      function  GetCODIND : DOUBLE;
      function  GetINDICE : string;
      function  GetVALOR : DOUBLE;
      function  GetCODCLI : DOUBLE;
      function  GetRAzao : string;
      function  GetApelido : string;
      function  GetAQUIS : DOUBLE;
      function  GetICMS : DOUBLE;
      function  GetFINAN : DOUBLE;
      function  GetFRETE : DOUBLE;
      function  GetCUSTO : DOUBLE;
      function  GetPRAZO : TDateTime;
      function  GetPRECOVENDA : DOUBLE;
      function  GetSTATUS : string;
      function  GetESTOQUE : DOUBLE;
      function  GetESTOQUEANT : DOUBLE;
      function  GetMSG :String;

      Procedure  SetCODPRO    (Value : Integer);
      Procedure  SetCNB    (Value : Integer);
      Procedure  SetPRODUTO (Value : string);
      Procedure  SetREF (Value : string);
      Procedure  SetCLASS (Value : string);
      Procedure  SetUNIDADE (Value : string);
      Procedure  SetLOCAL (Value : string);
      Procedure  SetCODIND (Value : DOUBLE);
      Procedure  SetINDICE (Value : string);
      Procedure  SetVALOR (Value : DOUBLE);
      Procedure  SetCODCLI (Value : DOUBLE);
      Procedure  SetRAZao(Value : string);
      Procedure  SetApelido(Value : string);
      Procedure  SetAQUIS (Value : DOUBLE);
      Procedure  SetICMS (Value : DOUBLE);
      Procedure  SetFINAN (Value : DOUBLE);
      Procedure  SetFRETE (Value : DOUBLE);
      Procedure  SetCUSTO (Value : DOUBLE);
      Procedure  SetPRAZO (Value : TDateTime);
      Procedure  SetPRECOVENDA (Value : DOUBLE);
      Procedure  SetSTATUS (Value : string);
      Procedure  SetESTOQUE (Value : DOUBLE);
      Procedure  SetESTOQUEANT (Value : DOUBLE);
      Procedure  SetMSG (Value : String);

    public
      property  CODPRO      : Integer read GetCODPRO     write SetCODPRO;
      property  CNB         : Integer read GetCNB     write SetCNB;
      property  PRODUTO     : string read GetPRODUTO    write SetPRODUTO;
      property  REF         : string read GetREF        write SetREF;
      property  CLASSs       : string read GetCLASS      write SetCLASS;
      property  UNIDADE     : string read GetUNIDADE    write SetUNIDADE;
      property  LOCAL       : String read GetLOCAL      write SetLOCAL;
      property  CODIND      : DOUBLE read GetCODIND     write SetCODIND;
      property  INDICE      : String read GetINDICE     write SetINDICE;
      property  VALOR       : DOUBLE read GetVALOR      write SetVALOR;
      property  CODCLI      : DOUBLE read GetCODCLI     write SetCODCLI;
      property  RAZAO       : string read GetRAZAO      write SetRAZAO;
      property  Apelido     : string read GetApelido    write SetApelido;
      property  AQUIS       : DOUBLE read GetAQUIS      write SetAQUIS;
      property  ICMS        : DOUBLE read GetICMS       write SetICMS;
      property  FINAN       : DOUBLE read GetFINAN      write SetFINAN;
      property  FRETE       : DOUBLE read GetFRETE      write SetFRETE;
      property  CUSTO       : DOUBLE read GetCUSTO      write SetCUSTO;
      property  PRAZO       : TDatetime read GetPRAZO      write SetPRAZO;
      property  PRECOVENDA  : DOUBLE read GetPRECOVENDA write SetPRECOVENDA;
      property  STATUS      : string read GetSTATUS     write SetSTATUS;
      property  ESTOQUE     : DOUBLE read GetESTOQUE    write SetESTOQUE;
      property  ESTOQUEANT  : DOUBLE read GetESTOQUEANT write SetESTOQUEANT;
      property  MSG         : String read Getmsg        write SetMsg;

  End;
implementation

uses SysUtils;

function    TProduto.GetCODPRO    : Integer;
Begin
      Result := Self.FCODPRO;
End;
function    TProduto.GetCNB   : Integer;
Begin
      Result := Self.FCODPRO;
End;
function    TProduto.GetPRODUTO    : string;
Begin
  Result := Self.FPRODUTO;
End;
function    TProduto.GetLOCAL    : string;
Begin
  Result := Self.FLOCAL;
End;
function    TProduto.GetApelido    : string;
Begin
  Result := Self.FAPELIDO;
End;
function    TProduto.GetCODIND    : DOUBLE;
Begin
  Result := Self.FCODIND;
End;
function    TProduto.GetINDICE    : string;
Begin
  Result := Self.FINDICE;
End;
function    TProduto.GetREF    : string;
Begin
  Result := Self.FREF;
End;
function    TProduto.GetCLASS   : string;
Begin
  Result := Self.FCLASS;
End;
function    TProduto.GetUNIDADE    : string;
Begin
  Result := Self.FUNIDADE;
End;
function    TProduto.GetVALOR   : DOUBLE;
Begin
  Result := Self.FVALOR;
End;
function    TProduto.GetCODCLI    : DOUBLE;
Begin
  Result := Self.FCODCLI;
End;
function    TProduto.GetRAzao    : string;
Begin
  Result := Self.FRAZAO;
End;
function    TProduto.GetAQUIS   : DOUBLE;
Begin
  Result := Self.FAQUIS;
End;
function    TProduto.GetICMS    : DOUBLE;
Begin
  Result := Self.FICMS;
End;
function    TProduto.GetFINAN    : DOUBLE;
Begin
  Result := Self.FFINAN;
End;
function    TProduto.GetFRETE    : DOUBLE;
Begin
  Result := Self.FFRETE;
End;
function    TProduto.GetCUSTO    : DOUBLE;
Begin
  Result := Self.FCUSTO;
End;
function    TProduto.GetPRAZO    : TDateTime;
Begin
  Result := Self.FPRAZO;
End;
function    TProduto.GetPRECOVENDA    : DOUBLE;
Begin
  Result := Self.FPRECOVENDA;
End;
function    TProduto.GetSTATUS    : string;
Begin
  Result := Self.FSTATUS;
End;
function    TProduto.GetESTOQUE    : DOUBLE;
Begin
  Result := Self.FESTOQUE;
End;
function    TProduto.GetESTOQUEANT    : DOUBLE;
Begin
  Result := Self.FESTOQUEANT;
End;
function    TProduto.Getmsg   : String;
Begin
  Result := Self.Fmsg;
End;


procedure   TProduto.SetCNB    (Value : Integer);
Begin
  Self.FCNB := Value;
End;
procedure   TProduto.SetCODPRO    (Value : Integer);
Begin
  Self.FCODPRO := Value;
End;
procedure   TProduto.SetPRODUTO    (Value : string);
Begin
  Self.FPRODUTO := Value;
End;
procedure   TProduto.SetREF   (Value : string);
Begin
  Self.FREF := Value;
End;
procedure   TProduto.SetCLASS    (Value : string);
Begin
  Self.FCLASS := Value;
End;
procedure   TProduto.SetAPELIDO    (Value : string);
Begin
  Self.FAPELIDO := Value;
End;
procedure   TProduto.SetUNIDADE    (Value : string);
Begin
  Self.FUNIDADE := Value;
End;
procedure   TProduto.SetLOCAL    (Value : String);
Begin
  Self.FLOCAL:= Value;
End;
procedure   TProduto.SetCODIND   (Value : Double);
Begin
  Self.FCODIND := Value;
End;
procedure   TProduto.SetINDICE    (Value : String);
Begin
  Self.FINDICE := Value;
End;
procedure   TProduto.SetVALOR    (Value : Double);
Begin
  Self.FVALOR := Value;
End;
procedure   TProduto.SetCODCLI    (Value : Double);
Begin
  Self.FCODCLI := Value;
End;
procedure   TProduto.SetRAzao    (Value : String);
Begin
  Self.FRAzao := Value;
End;
procedure   TProduto.SetAQUIS    (Value : Double);
Begin
  Self.FAQUIS := Value;
End;
procedure   TProduto.SetICMS    (Value : Double);
Begin
 // if (value <>StrToFloat('')) then
    Self.FICMS := Value;
  //else
  //  Self.FICMS := 0;
End;
procedure   TProduto.SetFINAN    (Value : Double);
Begin
  Self.FFINAN := Value;
End;
procedure   TProduto.SetFRETE    (Value : Double);
Begin
  Self.FFRETE := Value;
End;
procedure   TProduto.SetCUSTO    (Value : Double);
Begin
  Self.FCUSTO := Value;
End;
procedure   TProduto.SetPRAZO    (Value : TdateTime);
Begin
  Self.FPRAZO := Value;
End;
procedure   TProduto.SetPRECOVENDA    (Value : Double);
Begin
  Self.FPRECOVENDA := Value;
End;
procedure   TProduto.SetSTATUS    (Value : String);
Begin
  Self.FSTATUS := Value;
End;
procedure   TProduto.SetESTOQUE    (Value : Double);
Begin
  Self.FESTOQUE := Value;
End;
procedure   TProduto.SetESTOQUEANT    (Value : Double);
Begin
  Self.FESTOQUEANT := Value;
End;
procedure   TProduto.SetMsg    (Value : String);
Begin
  Self.Fmsg := Value;
End;
end.
