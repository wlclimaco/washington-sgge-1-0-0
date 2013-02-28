unit Unit68;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ExtCtrls, Grids, DBGrids;

type
  TForm68 = class(TForm)
    DBGrid1: TDBGrid;
    Panel1: TPanel;
    Panel2: TPanel;
    procedure DBGrid1DblClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form68: TForm68;

implementation

uses Unit2, Unit60;

{$R *.dfm}

procedure TForm68.DBGrid1DblClick(Sender: TObject);
begin
FRMNFENTRADAS.DbEdit9.Text := IntToStr(DBGrid1.Fields[0].asinteger);
close();
end;

end.
