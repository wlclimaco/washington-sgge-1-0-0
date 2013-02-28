unit UCadVendDiario;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DBCtrls, StdCtrls, Mask, DB, IBCustomDataSet, IBUpdateSQL,
  IBQuery, Buttons, U_DBBButton, ExtCtrls;

type
  TCadVendDiario = class(TForm)
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
    IBQuery1: TIBQuery;
    IBUpdateSQL1: TIBUpdateSQL;
    DataSource1: TDataSource;
    IBQuery2: TIBQuery;
    DataSource2: TDataSource;
    IBQuery3: TIBQuery;
    DataSource3: TDataSource;
    IBQuery1CODVEND: TIntegerField;
    IBQuery1ID: TIntegerField;
    IBQuery1DATA: TDateField;
    IBQuery1VALORVENDA: TFloatField;
    Label2: TLabel;
    DBEdit2: TDBEdit;
    Label3: TLabel;
    DBEdit3: TDBEdit;
    DBLookupComboBox1: TDBLookupComboBox;
    Label4: TLabel;
    procedure FormActivate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  CadVendDiario: TCadVendDiario;

implementation

{$R *.dfm}

procedure TCadVendDiario.FormActivate(Sender: TObject);
begin
IBQuery1.Active := True;
IBQuery2.Active := True;
IBQuery3.Active := True;
end;

end.
