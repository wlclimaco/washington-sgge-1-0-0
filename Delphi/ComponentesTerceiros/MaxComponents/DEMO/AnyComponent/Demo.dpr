program Demo;

uses
  Forms,
  MainWindow in 'MainWindow.pas' {frm_MainWindow};

{$R *.RES}

begin
  Application.Initialize;
  Application.CreateForm(Tfrm_MainWindow, frm_MainWindow);
  Application.Run;
end.
