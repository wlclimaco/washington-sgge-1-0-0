unit USrvContainer2;

interface

uses
  SysUtils, Classes, 
  SvcMgr, 
  DSTCPServerTransport,
  DSHTTPCommon, DSHTTP,
  DSServer, DSCommonServer, DSAuth; 

type
  TServerContainer2 = class(TService)
    DSServer: TDSServer;
    DSTCPServerTransport: TDSTCPServerTransport;
    DSHTTPService: TDSHTTPService;
    DSAuthenticationManager: TDSAuthenticationManager;
    DSServerClass: TDSServerClass;
    procedure DSServerClassGetClass(DSServerClass: TDSServerClass;
      var PersistentClass: TPersistentClass);
    procedure DSAuthenticationManagerUserAuthorize(Sender: TObject;
      EventObject: TDSAuthorizeEventObject; var valid: Boolean);
    procedure DSAuthenticationManagerUserAuthenticate(Sender: TObject;
      const Protocol, Context, User, Password: string; var valid: Boolean;
      UserRoles: TStrings);
    procedure ServiceStart(Sender: TService; var Started: Boolean);
  private
    { Private declarations }
  protected
    function DoStop: Boolean; override;
    function DoPause: Boolean; override;
    function DoContinue: Boolean; override;
    procedure DoInterrogate; override;
  public
    function GetServiceController: TServiceController; override;
  end;

var
  ServerContainer2: TServerContainer2;

implementation

uses Windows, USrvMethods;

{$R *.dfm}

procedure TServerContainer2.DSServerClassGetClass(
  DSServerClass: TDSServerClass; var PersistentClass: TPersistentClass);
begin
  PersistentClass := USrvMethods.TServerMethods;
end;

procedure TServerContainer2.DSAuthenticationManagerUserAuthenticate(
  Sender: TObject; const Protocol, Context, User, Password: string;
  var valid: Boolean; UserRoles: TStrings);
begin
                                                 
                                                                                       
  valid := True;
end;

procedure TServerContainer2.DSAuthenticationManagerUserAuthorize(
  Sender: TObject; EventObject: TDSAuthorizeEventObject;
  var valid: Boolean);
begin
                                                
                                                                                             
                                                                            
                                    
  valid := True;
end;

procedure ServiceController(CtrlCode: DWord); stdcall;
begin
  ServerContainer2.Controller(CtrlCode);
end;

function TServerContainer2.GetServiceController: TServiceController;
begin
  Result := ServiceController;
end;

function TServerContainer2.DoContinue: Boolean;
begin
  Result := inherited;
  DSServer.Start;
end;

procedure TServerContainer2.DoInterrogate;
begin
  inherited;
end;

function TServerContainer2.DoPause: Boolean;
begin
  DSServer.Stop;
  Result := inherited;
end;

function TServerContainer2.DoStop: Boolean;
begin
  DSServer.Stop;
  Result := inherited;
end;

procedure TServerContainer2.ServiceStart(Sender: TService; var Started: Boolean);
begin
  DSServer.Start;
end;
end.

