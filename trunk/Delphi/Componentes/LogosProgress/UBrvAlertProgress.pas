unit UBrvAlertProgress;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, ComCtrls, TLHelp32, PsAPI, BrvAlertProgress;

type
  TBrvProgressSvr = class(TForm)
    Panel1: TPanel;
    BrvAlertProgress: TBrvAlertProgress;
    TmrClose: TTimer;
    procedure FormCreate(Sender: TObject);
    procedure TmrCloseTimer(Sender: TObject);
  private
    function LocalizaProcesso(NrProgress: Cardinal): Boolean;
    { Private declarations }
  public
    { Public declarations }
    IdProcess : Cardinal;
  end;

var
  BrvProgressSvr: TBrvProgressSvr;

implementation

{$R *.dfm}

procedure TBrvProgressSvr.FormCreate(Sender: TObject);
var lDsCaption : String;
    lDsProcess : String;
    lNrMaxPos  : Integer;
    lNrTime    : Integer;
begin
      if (ParamCount <> 5) then
      begin
            MessageDlg('BrvProgressSvr:' + Chr(13) +
                       'Parâmetros insuficientes para esta função!', mtError, [mbok], 0);
            Halt;
      end;

      IdProcess := StrToInt(ParamStr(5));

      BrvAlertProgress.BrCaption  := StringReplace(ParamStr(1), '|', ' ', [rfReplaceAll]);
      BrvAlertProgress.BrProcesso := StringReplace(ParamStr(2), '|', ' ', [rfReplaceAll]);
      BrvAlertProgress.BrProgressInfinity(StrToIntDef(ParamStr(3), 100),
                                                                     StrToIntDef(ParamStr(4), 100));
end;

function TBrvProgressSvr.LocalizaProcesso(NrProgress: Cardinal): Boolean;
var ProcEntry: TProcessEntry32;
    Hnd: THandle;
    Fnd: Boolean;
begin
      Result := False;
      Hnd := CreateToolhelp32Snapshot(TH32CS_SNAPALL, 0);
      if Hnd > -1 then
      begin
            ProcEntry.dwSize := SizeOf(TProcessEntry32);
            Fnd := Process32First(Hnd, ProcEntry);

            while Fnd do
            begin
                  if (ProcEntry.th32ProcessID = NrProgress) then
                  begin
                        Fnd := False;
                        Result := True;
                  end else
                  begin
                        Fnd := Process32Next(Hnd, ProcEntry);
                  end;
            end;

            CloseHandle(Hnd);
      end;
end;

procedure TBrvProgressSvr.TmrCloseTimer(Sender: TObject);
begin
      if (not LocalizaProcesso(IdProcess)) then
      begin
            halt;
      end;
end;

end.
