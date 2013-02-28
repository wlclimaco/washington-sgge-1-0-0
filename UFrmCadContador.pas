unit UFrmCadContador;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Mask, DBCtrls, DB, IBCustomDataSet, IBUpdateSQL,
  IBQuery, Buttons, U_DBBButton, ExtCtrls;

type
  TFrmCadContador = class(TForm)
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
    IBUpdateSQL1: TIBUpdateSQL;
    DataSource1: TDataSource;
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
    Label1: TLabel;
    DBEdit1: TDBEdit;
    Label2: TLabel;
    DBEdit2: TDBEdit;
    Label3: TLabel;
    DBEdit3: TDBEdit;
    Label4: TLabel;
    DBEdit4: TDBEdit;
    Label5: TLabel;
    DBEdit5: TDBEdit;
    Label6: TLabel;
    DBEdit6: TDBEdit;
    Label7: TLabel;
    DBEdit7: TDBEdit;
    Label8: TLabel;
    DBEdit8: TDBEdit;
    Label9: TLabel;
    DBEdit9: TDBEdit;
    Label10: TLabel;
    DBEdit10: TDBEdit;
    Label11: TLabel;
    DBEdit11: TDBEdit;
    Label12: TLabel;
    DBEdit12: TDBEdit;
    Label13: TLabel;
    DBEdit13: TDBEdit;
    Label14: TLabel;
    DBEdit14: TDBEdit;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmCadContador: TFrmCadContador;

implementation

{$R *.dfm}

end.
