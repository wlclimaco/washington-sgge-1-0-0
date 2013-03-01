unit ActException;

interface

uses
  Windows, Forms, Messages, SysUtils, Classes, DB, DBClient;

type
  TActOnException = procedure(Mesg, ClassName: String) of object;

type
  TActException = class(TComponent)
  private
    { Private declarations }
    FActOnException: TActOnException;
    procedure GlobalException(Sender: TObject; E: Exception);
    procedure DBException(E: EDatabaseError);
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
  published
    { Published declarations }
    property OnException: TActOnException read FActOnException write FActOnException;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Active Controls', [TActException]);
end;

{ TActException }

constructor TActException.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  if not (csDesigning in ComponentState) then
    Application.OnException := GlobalException;
end;

procedure TActException.DBException(E: EDatabaseError);
begin
  FActOnException(E.Message, E.ClassName)
end;

procedure TActException.GlobalException(Sender: TObject; E: Exception);
begin
  if E is Exception then
  begin
    if E is EDatabaseError then
      DBException(E as EDatabaseError)
    else
      FActOnException(E.Message, E.ClassName);
  end;
end;

end.
