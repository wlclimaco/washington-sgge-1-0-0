unit Unit54;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Unit1, StdCtrls, Mask, DBCtrls, Buttons, U_DBBButton, ExtCtrls;

type
  TFRMPW_GRUPO = class(TForm1)
    Label1: TLabel;
    DBEdit1: TDBEdit;
    Label2: TLabel;
    DBEdit2: TDBEdit;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FRMPW_GRUPO: TFRMPW_GRUPO;

implementation

uses Unit2;

{$R *.dfm}

end.
