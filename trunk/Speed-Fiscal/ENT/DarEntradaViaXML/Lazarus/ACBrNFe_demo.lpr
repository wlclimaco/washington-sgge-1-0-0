program ACBrNFe_demo;

{$mode objfpc}{$H+}

uses
  {$IFDEF UNIX}{$IFDEF UseCThreads}
  cthreads,
  {$ENDIF}{$ENDIF}
  LResources, Interfaces, // this includes the LCL widgetset
  Forms, ACBr_LCL, TurboPowerIPro, Unit1, ufrmStatus,
  acbrnfepcn_lcl;

{$IFDEF WINDOWS}{$R ACBrNFe_demo.rc}{$ENDIF}

begin
  {$I ACBrNFe_demo.lrs}
  Application.Initialize;
  Application.CreateForm(TForm1, Form1);
  Application.Run;
end.

