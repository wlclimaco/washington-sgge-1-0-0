unit BrvTCPClient;

interface

uses
  SysUtils, Classes, IdBaseComponent, IdComponent, IdTCPConnection, IdTCPClient,
  IdIOHandlerSocket;

type
  TExecuteEvent = procedure(Sender: TObject; pDsMensag : String) of object;

  TBrvTCPClient = class(TIdTCPClient)
  private
    { Private declarations }
    gOnExecute : TExecuteEvent;
  protected
    { Protected declarations }
  public
    { Public declarations }
    procedure Connect; override;
    procedure Executar(pDsMensag : String);
  published
    { Published declarations }
    property OnExecute       : TExecuteEvent read gOnExecute write gOnExecute;
  end;

  TClientThread = class(TThread)
  protected
    procedure Execute; override;
  public
    gTcpClient : TBrvTCPClient;
    constructor Create(CreateSuspended: Boolean);
  end;

  procedure Register;

implementation

procedure Register;
begin
      RegisterComponents('Bravo Rede', [TBrvTCPClient]);
end;

{ TBrvTCPClient }

procedure TBrvTCPClient.Connect;
var lCltThread : TClientThread;
begin
      inherited;
      lCltThread := TClientThread.Create(false);
      lCltThread.gTcpClient := Self;
end;

procedure TBrvTCPClient.Executar(pDsMensag : String);
begin
      if Assigned(gOnExecute) then
      begin
            gOnExecute(Self, pDsMensag);
      end;
end;

{ TClientThread }

constructor TClientThread.Create(CreateSuspended: Boolean);
begin
      inherited Create(CreateSuspended);
      Priority        := tpIdle;
      FreeOnTerminate := True;
end;

procedure TClientThread.Execute;
var lDsMensag : String;
begin
      inherited;
      repeat
             lDsMensag := Trim(gTcpClient.Socket.ReadLn());

             if lDsMensag <> '' then
             begin
                   gTcpClient.Executar(lDsMensag);
             end;
      until not gTcpClient.Connected;
end;

end.
