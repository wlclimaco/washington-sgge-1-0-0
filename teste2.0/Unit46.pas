unit Unit46;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Unit1, StdCtrls, Mask, DBCtrls, Buttons, U_DBBButton, ExtCtrls;

type
  TFRMLOJAS = class(TForm1)
    Label1: TLabel;
    DBEdit1: TDBEdit;
    Label2: TLabel;
    DBEdit2: TDBEdit;
    Label3: TLabel;
    DBEdit3: TDBEdit;
    Label4: TLabel;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FRMLOJAS: TFRMLOJAS;

implementation

uses Unit2;

{$R *.dfm}

end.
