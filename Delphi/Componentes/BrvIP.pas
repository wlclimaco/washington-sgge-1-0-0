unit BrvIP;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Winsock;

type
  TBrvlIP = class(TComponent)
  private
    { Private declarations }
    FSnLocal  : Boolean;
    FNmComput : AnsiString;
    FNrEndeIP : String;
    procedure SetComputadorLocal(Value : Boolean);
    procedure SetNomeComputador(Value : AnsiString);
    procedure EncontrarNumeroIP;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
  published
    { Published declarations }
    property BrComputadorLocal : Boolean     read FSnLocal   write SetComputadorLocal;
    property BrNomeComputador  : AnsiString  read FNmComput  write SetNomeComputador;
    property BrEnderecoIP      : String      read FNrEndeIP  write FNrEndeIP;
  end;

procedure Register;

implementation

constructor TBrvlIP.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
end;

destructor TBrvlIP.Destroy;
begin
      inherited  destroy;
end;

procedure TBrvlIP.SetComputadorLocal(Value : Boolean);
begin
      FSnLocal := Value;
      EncontrarNumeroIP;
end;

procedure TBrvlIP.SetNomeComputador(Value : AnsiString);
begin
      FNmComput := Value;
      EncontrarNumeroIP;
end;

procedure TBrvlIP.EncontrarNumeroIP;
type
    TaPInAddr = array [0..10] of PInAddr;
    PaPInAddr = ^TaPInAddr;
var
    phe       : PHostEnt;
    pptr      : PaPInAddr;
    Buffer    : array [0..255] of AnsiChar;
    I         : Integer;
    GInitData : TWSADATA;
begin
      WSAStartup($101, GInitData);
      FNrEndeIp  := '';

      if  FSnLocal then
      begin
            GetHostName(Buffer, SizeOf(Buffer));
            FNmComput := Buffer;
      end;

      if  FNmComput <> '' then
      begin
            phe := GetHostByName(PAnsiChar(FNmComput));

            if phe <> nil then
            begin
                  pptr := PaPInAddr(Phe^.h_addr_list);
                  I := 0;

                  //Pegar somente o primeiro IP da lista.

                  if (pptr^[I] <> nil) then
                  begin
                        FNrEndeIp := StrPas(inet_ntoa(pptr^[I]^));
                  end;

                 {while pptr^[I] <> nil do
                  begin
                        FNrEndeIp := StrPas(inet_ntoa(pptr^[I]^));
                        Inc(I);
                  end;}
            end;
      end;

      WSACleanup;
end;

procedure Register;
begin
  RegisterComponents('Bravo Rede', [TBrvlIP]);
end;

end.
