unit BrvParamExe;

interface

uses
  SysUtils, Classes, Forms, ComCtrls, ExtCtrls, StdCtrls, Graphics, Windows, Controls, DB,
  DBClient, SqlExpr;

type
  TBrvParamExe = class(TComponent)
  private
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    function GetURL: String; {\U}
  published
    { Published declarations }

  end;

{$R BrvAlertProgress.res}

procedure Register;

implementation

procedure Register;
begin
      RegisterComponents('Bravo Utils', [TBrvParamExe]);
end;

{ TBrvParamExe }

function TBrvParamExe.GetURL: String;
var lnrparam : Integer;
begin
      Result := '';

      if (ParamCount > 0)  then
      begin
            lnrparam := 0;

            while (lnrparam  <= ParamCount) do
            begin
                  if (UpperCase(Copy(ParamStr(lnrparam), 1, 2)) = '\U')  then
                  begin
                        Result := Copy(ParamStr(lnrparam), 3, 
                                                    Length(ParamStr(lnrparam)));
                  end;
                  
                  inc(lnrparam);
            end;

      end;
end;

end.
