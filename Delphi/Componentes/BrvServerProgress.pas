unit BrvServerProgress;

interface

uses
  SysUtils, Classes, Forms, BrvAlertProgress, Dialogs, Windows;

type
  TBrvServerProgress = class(TComponent)
  private
    { Private declarations }
    FProcInfo: TProcessInformation;
    FProcessok : Boolean;
  protected
    { Protected declarations }
  public
    { Public declarations }
    procedure Start(pDsCaption, pDsProcess: String; pNrMaxPos, pNrTime: Integer);
    procedure Stop;
  published
    { Published declarations }
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvServerProgress]);
end;

{ TBrvServerProgress }

procedure TBrvServerProgress.Start(pDsCaption, pDsProcess: String;
                                   pNrMaxPos, pNrTime: Integer);

function ProcessoAtivo: Integer;
  var h: hwnd;
      hpr, hth: longint;
  begin
        h:= getforegroundwindow;
        GetWindowThreadProcessID(h, @hpr);
        hth:= OpenProcess(STANDARD_RIGHTS_REQUIRED OR PROCESS_TERMINATE, false, hpr);
        Result := hpr;
  end;

var SUInfo: TStartupInfo;
    cmd : String;
begin
      cmd := 'LogosProgress.exe ' + StringReplace(pDsCaption, ' ' , '|', [rfReplaceAll]) + ' ' +
                                    StringReplace(pDsProcess, ' ' , '|', [rfReplaceAll]) + ' ' +
                                    FormatFloat('0', pNrMaxPos) + ' ' +
                                    FormatFloat('0', pNrTime) + ' ' +
                                    FormatFloat('0', ProcessoAtivo);

      FillChar(SUInfo, SizeOf(SUInfo), #0);
      SUInfo.cb      := SizeOf(SUInfo);
      SUInfo.dwFlags := STARTF_USESHOWWINDOW;
      SUInfo.wShowWindow := SW_HIDE;

      FProcessok := CreateProcess(nil, PChar(cmd), nil, nil, false,
                              CREATE_NEW_PROCESS_GROUP+NORMAL_PRIORITY_CLASS,
                              nil, nil, SUInfo, FProcInfo);

      if (not FProcessok) then
      begin
            MessageDlg('Erro durante apresentação do processo! Aguarde...', mtWarning, [mbok], 0);


      end;
end;

procedure TBrvServerProgress.Stop;
begin
      if (FProcessok) then
      begin
            TerminateProcess(OpenProcess(PROCESS_TERMINATE or PROCESS_QUERY_INFORMATION,False,
                                                                         FProcInfo.dwProcessId), 0);
      end;
end;

end.
