program LogosWS;

{$APPTYPE CONSOLE}

uses
  WebBroker,
  CGIApp,
  ULogosWS in 'ULogosWS.pas' {WebModule1: TWebModule},
  LogosWSImpl in 'LogosWSImpl.pas',
  LogosWSIntf in 'LogosWSIntf.pas',
  UClaSrv in '..\..\Client\Logos\UClaSrv.pas';

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TWebModule1, WebModule1);
  Application.Run;
end.
