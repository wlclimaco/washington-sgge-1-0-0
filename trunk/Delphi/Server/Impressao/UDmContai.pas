unit UDmContai;

interface

uses
  SysUtils, Classes,
  DSTCPServerTransport,
  DSHTTPCommon, DSHTTP,
  DSServer, DSCommonServer, DSAuth, DB, DBTables, DBXOracle, SqlExpr, dialogs;

type
  TDmContai = class(TDataModule)
    DSServer: TDSServer;
    DSTCPServerTransport: TDSTCPServerTransport;
    DSHTTPService: TDSHTTPService;
    DSAuthenticationManager: TDSAuthenticationManager;
    DscImp: TDSServerClass;
    DscRW: TDSServerClass;
    procedure DscLogosGetClass(DSServerClass: TDSServerClass;
      var PersistentClass: TPersistentClass);
    procedure DSAuthenticationManagerUserAuthorize(Sender: TObject;
      EventObject: TDSAuthorizeEventObject; var valid: Boolean);
    procedure DSAuthenticationManagerUserAuthenticate(Sender: TObject;
      const Protocol, Context, User, Password: string; var valid: Boolean;
      UserRoles: TStrings);
    procedure DataModuleCreate(Sender: TObject);
    procedure DscImpGetClass(DSServerClass: TDSServerClass;
      var PersistentClass: TPersistentClass);
    procedure DscRWGetClass(DSServerClass: TDSServerClass;
      var PersistentClass: TPersistentClass);
  private
    { Private declarations }
  public
  end;

var
  DmContai: TDmContai;

implementation

uses Windows, USDmLogos,  USDmImp, UFrmLogos, USDmRW;

{$R *.dfm}

procedure TDmContai.DscLogosGetClass(
  DSServerClass: TDSServerClass; var PersistentClass: TPersistentClass);
begin
      PersistentClass := USDmLogos.TSDmLogos;
end;

procedure TDmContai.DscRWGetClass(DSServerClass: TDSServerClass;
  var PersistentClass: TPersistentClass);
begin
      PersistentClass := USDmRW.TSDmRw;
end;

procedure TDmContai.DscImpGetClass(DSServerClass: TDSServerClass;
  var PersistentClass: TPersistentClass);
begin
      PersistentClass := USDmImp.TSDmImp;
end;

procedure TDmContai.DataModuleCreate(Sender: TObject);
begin
      DSTCPServerTransport.Port := FrmLogos.EdtNoPorta.BrAsInteger;
end;

procedure TDmContai.DSAuthenticationManagerUserAuthenticate(
  Sender: TObject; const Protocol, Context, User, Password: string;
  var valid: Boolean; UserRoles: TStrings);
begin
  valid := True;
end;

procedure TDmContai.DSAuthenticationManagerUserAuthorize(
  Sender: TObject; EventObject: TDSAuthorizeEventObject;
  var valid: Boolean);
begin
  valid := True;
end;

end.

