unit UFrmCadGrupo;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Mask, DBCtrls, DB, IBCustomDataSet, IBUpdateSQL,
  IBQuery, Buttons, U_DBBButton, ExtCtrls;

type
  TFrmCadGrupo = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    DBButton1: TDBButton;
    DBButton2: TDBButton;
    DBButton3: TDBButton;
    DBButton4: TDBButton;
    DBButton5: TDBButton;
    DBButton6: TDBButton;
    DBButton7: TDBButton;
    DBButton8: TDBButton;
    DBButton9: TDBButton;
    DBButton10: TDBButton;
    Panel3: TPanel;
    ScrollBox1: TScrollBox;
    GroupBox1: TGroupBox;
    IBQuery1: TIBQuery;
    IBUpdateSQL1: TIBUpdateSQL;
    DataSource1: TDataSource;
    IBQuery1GRUPO: TIntegerField;
    IBQuery1DSGRUPO: TIBStringField;
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
  FrmCadGrupo: TFrmCadGrupo;

implementation

{$R *.dfm}

end.
