unit BrvDbNavCop;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  ExtCtrls{, DB, DBTables};

type
  TBrvDbNavCop = class(TCustomPanel)
//    FDataSource: TDataSource;
  private
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
  published
    { Published declarations }
//    property DataSource: TDataSource read FDataSource write FDataSource;
    property Caption;
    property Align;
  end;

procedure Register;

implementation

constructor TBrvDbNavCop.Create(AOwner: TComponent);
begin
   inherited;
   Width:=450;
   Height:=30;
end;

destructor TBrvDbNavCop.Destroy;
begin
   inherited;
end;

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvDbNavCop]);
end;

end.








