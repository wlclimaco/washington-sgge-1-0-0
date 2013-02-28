unit Unit70;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Unit1, DB, StdCtrls, Mask, DBCtrls, Buttons, U_DBBButton,
  ExtCtrls;

type
  TForm70 = class(TForm1)
    Label1: TLabel;
    DBEdit1: TDBEdit;
    DataSource1: TDataSource;
    Label2: TLabel;
    DBEdit2: TDBEdit;
    Label3: TLabel;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form70: TForm70;

implementation

uses Unit2;

{$R *.dfm}

end.
