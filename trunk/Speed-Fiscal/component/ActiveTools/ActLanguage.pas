unit ActLanguage;

interface

uses
  SysUtils, Classes, Windows, Dialogs;

type
  TLanguage = (Portuguese_br, English, Spanish);

type
  TActLanguage = class(TComponent)
  private
    FLanguage: TLanguage;
    FLibraryName: string;
    FOnError: TGetStrProc;
    procedure SetLanguage(const Value: TLanguage);
    procedure SetLibraryName(const Value: string);
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    HandleLib: THandle;
    procedure LoadLanguage;
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
  published
    { Published declarations }
    property Language: TLanguage read FLanguage write SetLanguage;
    property LibraryName: string read FLibraryName write SetLibraryName;
    property OnError: TGetStrProc read FOnError write FOnError;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Controls', [TActLanguage]);
end;

{ TActLanguage }

procedure TActLanguage.SetLibraryName(const Value: string);
begin
  if Value <> FLibraryName then
    FLibraryName := Value;
end;

procedure TActLanguage.SetLanguage(const Value: TLanguage);
begin
  if Value <> FLanguage then
    FLanguage := Value;
end;

constructor TActLanguage.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
end;

destructor TActLanguage.Destroy;
begin
  inherited Destroy;
  if not (csDesigning in ComponentState) then
  begin
    if HandleLib <> 0 then
      FreeLibrary(HandleLib);
  end;
end;

procedure TActLanguage.LoadLanguage;
begin
  case FLanguage of
    Portuguese_br: FLibraryName := 'portuguese_br.dll';
    English: FLibraryName := 'english.dll';
    Spanish: FLibraryName := 'spanish.dll';
  end;
  if not (csDesigning in ComponentState) then
  begin
    if FileExists(GetCurrentDir + '\' + LibraryName) then
    begin
      HandleLib := LoadLibrary(PChar(FLibraryName));
      if HandleLib = 0 then
        FreeLibrary(HandleLib);
    end
    else
      ShowMessage('File ' + LibraryName + ' not found!');
  end;
end;

end.
