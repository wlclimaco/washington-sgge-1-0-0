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
    DSAuthenticationManager: TDSAuthenticationManager;
    DscLogos: TDSServerClass;
    DscRW: TDSServerClass;
    DscSis: TDSServerClass;
    DscTra: TDSServerClass;
    DscCon: TDSServerClass;
    DscWms: TDSServerClass;
    DscAdm: TDSServerClass;
    procedure DscLogosGetClass(DSServerClass: TDSServerClass;
      var PersistentClass: TPersistentClass);
    procedure DSAuthenticationManagerUserAuthorize(Sender: TObject;
      EventObject: TDSAuthorizeEventObject; var valid: Boolean);
    procedure DSAuthenticationManagerUserAuthenticate(Sender: TObject;
      const Protocol, Context, User, Password: string; var valid: Boolean;
      UserRoles: TStrings);
    procedure DscRWGetClass(DSServerClass: TDSServerClass;
      var PersistentClass: TPersistentClass);
    procedure DataModuleCreate(Sender: TObject);
    procedure DscSisGetClass(DSServerClass: TDSServerClass;
      var PersistentClass: TPersistentClass);
    procedure DscTraGetClass(DSServerClass: TDSServerClass;
      var PersistentClass: TPersistentClass);
    procedure DscConGetClass(DSServerClass: TDSServerClass;
      var PersistentClass: TPersistentClass);
    procedure DscWmsGetClass(DSServerClass: TDSServerClass;
      var PersistentClass: TPersistentClass);
    procedure DscAdmGetClass(DSServerClass: TDSServerClass; var PersistentClass: TPersistentClass);
  private
    { Private declarations }
  public
  end;

var
  DmContai: TDmContai;

implementation

uses Windows, USDmLogos, USDmRW, UFrmLogos, USDmSis, USDmTra, USDmCon, USDmWms, USDmAdm;

{$R *.dfm}

procedure TDmContai.DscAdmGetClass(DSServerClass: TDSServerClass;
  var PersistentClass: TPersistentClass);
begin
      PersistentClass := USDmAdm.TSDmAdm;
end;

procedure TDmContai.DscConGetClass(DSServerClass: TDSServerClass;
  var PersistentClass: TPersistentClass);
begin
      PersistentClass := USDmCon.TSDmCon;
end;

procedure TDmContai.DscLogosGetClass(
  DSServerClass: TDSServerClass; var PersistentClass: TPersistentClass);
begin
  PersistentClass := USDmLogos.TSDmLogos;
end;

procedure TDmContai.DscRWGetClass(DSServerClass: TDSServerClass;
  var PersistentClass: TPersistentClass);
begin
  PersistentClass := USDmRW.TSDmRW;
end;

procedure TDmContai.DscSisGetClass(DSServerClass: TDSServerClass;
  var PersistentClass: TPersistentClass);
begin
      PersistentClass := USDmSis.TSDmSis;
end;

procedure TDmContai.DscTraGetClass(DSServerClass: TDSServerClass;
  var PersistentClass: TPersistentClass);
begin
      PersistentClass := USDmTra.TSDmTra;
end;

procedure TDmContai.DscWmsGetClass(DSServerClass: TDSServerClass;
  var PersistentClass: TPersistentClass);
begin
      PersistentClass := USDmWms.TSDmWms;
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

