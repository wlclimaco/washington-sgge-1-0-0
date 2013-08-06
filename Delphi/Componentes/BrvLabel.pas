unit BrvLabel;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls;

type
  TBrvLabel = class(TLabel)
  private
    { Private declarations }
    FOnMouseEnter   : TNotifyEvent;
    FOnMouseExit    : TNotifyEvent;
    procedure CMMouseEnter (var Message: TMessage); message CM_MOUSEENTER;
    procedure CMMouseLeave (var Message: TMessage); message CM_MOUSELEAVE;
  protected
    { Protected declarations }
  public
    { Public declarations }
  published
    { Published declarations }
    property BrOnMouseEnter : TNotifyEvent read FOnMouseEnter write FOnMouseEnter;
    property BrOnMouseExit  : TNotifyEvent read FOnMouseExit  write FOnMouseExit;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Standard', [TBrvLabel]);
end;

procedure TBrvLabel.CMMouseEnter (var Message: TMessage);
begin
      inherited;

      if Enabled then
      begin
            if Assigned(FOnMouseEnter) then
            begin
                  FOnMouseEnter (Self);
            end;
      end;
end;

procedure TBrvLabel.CMMouseLeave (var Message: TMessage);
begin
      inherited;
      if Enabled then
      begin
            if Assigned(FOnMouseExit) then
            begin
                  FOnMouseExit (Self);
            end;
      end;
end;

end.
