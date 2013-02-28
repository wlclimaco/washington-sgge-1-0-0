unit Umod;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBUpdateSQL, IBQuery, StdCtrls, Buttons,
  U_DBBButton, ExtCtrls;

type
  TFrmmod = class(TForm)
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
    GroupBox2: TGroupBox;
    GroupBox3: TGroupBox;
    IBQuery1: TIBQuery;
    IBQuery1NOME: TIBStringField;
    IBQuery1CPF: TIBStringField;
    IBQuery1CRC: TIBStringField;
    IBQuery1CNPJ: TIBStringField;
    IBQuery1CEP: TIBStringField;
    IBQuery1ENDE: TIBStringField;
    IBQuery1NUM: TIntegerField;
    IBQuery1COMPL: TIBStringField;
    IBQuery1BAIRRO: TIBStringField;
    IBQuery1FONE: TIBStringField;
    IBQuery1FAX: TIBStringField;
    IBQuery1EMAIL: TIBStringField;
    IBQuery1COD_MUN: TIBStringField;
    IBQuery1COD_CONTADOR: TIntegerField;
    IBUpdateSQL1: TIBUpdateSQL;
    DataSource1: TDataSource;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Frmmod: TFrmmod;

implementation

{$R *.dfm}

end.
