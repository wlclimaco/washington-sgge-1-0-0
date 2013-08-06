unit BrvRegEdit;

interface

uses Windows, Classes;

type
  TBrvRegEdit = class(TComponent)
  private
    { Private declarations }
    FDsAppSer : String;
    procedure SetApplicationServer(Value : String);

  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
  published
    { Published declarations }
    property AppServer    : String read FDsAppSer write SetApplicationServer;
    property BancosOracle : String read FDsAppSer write SetApplicationServer;
  end;

procedure Register;

implementation

constructor TBrvRegEdit.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
end;

destructor TBrvRegEdit.Destroy;
begin
      inherited  destroy;
end;

procedure TBrvRegEdit.SetApplicationServer(Value : String);
begin
      //---
end;

procedure Register;
begin
  RegisterComponents('Bravo Banco', [TBrvRegEdit]);
end;



end.

