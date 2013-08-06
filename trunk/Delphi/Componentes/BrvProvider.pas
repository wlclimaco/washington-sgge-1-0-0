unit BrvProvider;

interface

uses
  SysUtils, Classes, Provider, DB, Dialogs;

type
  TBrvProvider = class(TDataSetProvider)
  private
    { Private declarations }
  protected
    function HandleUpdateError(Tree: TUpdateTree; E: EUpdateError;
      var MaxErrors, ErrorCount: Integer): Boolean; virtual;
    { Protected declarations }
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    { Public declarations }
  published
    { Published declarations }
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Banco', [TBrvProvider]);
end;

constructor TBrvProvider.Create(AOwner: TComponent);
begin
      inherited;
end;

destructor TBrvProvider.Destroy;
begin
      inherited Destroy;
end;

function TBrvProvider.HandleUpdateError(Tree: TUpdateTree;
  E: EUpdateError; var MaxErrors, ErrorCount: Integer): Boolean;
begin
      inherited;
      ShowMessage(E.Message);
end;

end.
