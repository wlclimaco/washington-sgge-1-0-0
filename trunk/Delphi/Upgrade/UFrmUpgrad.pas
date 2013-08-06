unit UFrmUpgrad;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ComCtrls, ExtCtrls, StdCtrls, Tlhelp32;

type
  TFrmUpgrad = class(TForm)
    Panel1: TPanel;
    TmrAtuali: TTimer;
    LblProces: TLabel;
    PgbAtuali: TProgressBar;
    Label1: TLabel;
    procedure TmrAtualiTimer(Sender: TObject);
  private
    procedure IniciarAplicacao(pNmArqDes: String);
    procedure FinalizarProcessoPrincipal(pNmAplica: String);
    function MatarProcesso(pNmProces: String): Integer;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmUpgrad: TFrmUpgrad;

implementation

{$R *.dfm}

procedure TFrmUpgrad.TmrAtualiTimer(Sender: TObject);
begin
      PgbAtuali.StepIt;

      case PgbAtuali.Position of
           01 : begin
                      if (Trim(ParamStr(1)) = '') or (Trim(ParamStr(2)) = '') then
                      begin
                            TmrAtuali.Enabled := False;
                            MessageDlg('Parâmetros insuficientes para ' +
                                       'proceder a atualização', mtError, [mbOk], 0);

                            Close;
                      end;
                end;
           05 : begin
                      LblProces.Caption := 'Encerrando processos pendentes.';
                      LblProces.Refresh;
                      FinalizarProcessoPrincipal(ParamStr(2));
                end;
           10 : begin
                      LblProces.Caption := 'Atualizando e reinicializando aplicação.';
                      LblProces.Refresh;
                      DeleteFile(ParamStr(2));
                      MoveFile(PChar(ParamStr(1)), PChar(ParamStr(2)));
                end;
           15 : begin
                      TmrAtuali.Enabled := False;
                      IniciarAplicacao(ParamStr(2));
                end;
      end;
end;

procedure TFrmUpgrad.FinalizarProcessoPrincipal(pNmAplica : String);
var lNmAplica : String;
begin
      lNmAplica := ExtractFileName(pNmAplica);
      MatarProcesso(lNmAplica);
end;

function TFrmUpgrad.MatarProcesso(pNmProces : String) : Integer;
const PROCESS_TERMINATE = $0001;
var   ContinueLoop    : BOOL;
      FSnapshotHandle : THandle;
      FProcessEntry32 : TProcessEntry32;
begin
      Result := 0;
      FSnapshotHandle := CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, 0);
      FProcessEntry32.dwSize := SizeOf(FProcessEntry32);
      ContinueLoop := Process32First(FSnapshotHandle, FProcessEntry32);

      while Integer(ContinueLoop) <> 0 do
      begin
            if ((UpperCase(ExtractFileName(FProcessEntry32.szExeFile)) =
                                                                UpperCase(pNmProces)) or
                (UpperCase(FProcessEntry32.szExeFile) = UpperCase(pNmProces))) then
            begin
                Result := Integer(TerminateProcess(OpenProcess(PROCESS_TERMINATE, BOOL(0),
                                                           FProcessEntry32.th32ProcessID),
                                                           0));
            end;
            ContinueLoop := Process32Next(FSnapshotHandle, FProcessEntry32);
      end;

      CloseHandle(FSnapshotHandle);
end;

procedure TFrmUpgrad.IniciarAplicacao(pNmArqDes : String);
var lSuExecuca : STARTUPINFO;
    lDsComand  : String;
    lproceexec : PROCESS_INFORMATION;
begin
      lSuExecuca.cb              := 4000;
      lSuExecuca.lpReserved      := pointer(#0);
      lSuExecuca.lpDesktop       := pointer(#0);
      lSuExecuca.lpTitle         := pointer(#0);
      lSuExecuca.dwX             := 0;
      lSuExecuca.dwY             := 0;
      lSuExecuca.dwXSize         := 0;
      lSuExecuca.dwYSize         := 0;
      lSuExecuca.dwXCountChars   := 0;
      lSuExecuca.dwYCountChars   := 0;
      lSuExecuca.dwFillAttribute := 0;
      lSuExecuca.dwFlags         := STARTF_USESHOWWINDOW;
      lSuExecuca.wShowWindow     := SW_SHOWNORMAL;
      lSuExecuca.cbReserved2     := 0;
      lSuExecuca.lpReserved2     := pointer(#0);
      lSuExecuca.hStdInput       := 0;
      lSuExecuca.hStdOutput      := 0;
      lSuExecuca.hStdError       := 0;
      lDsComand := pNmArqDes + ' N';

      CreateProcess(Pointer(#0), PChar(lDsComand), Pointer(#0), Pointer(#0),
                    True, High_Priority_Class,     Pointer(#0), Pointer(#0),
                    lSuExecuca,  lProceexec);

      Close;
end;

end.
