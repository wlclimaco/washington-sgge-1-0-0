unit UFrmCentroCusto;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Buttons, U_DBBButton, DBCtrls, DB, Mask,
  IBCustomDataSet, IBUpdateSQL, IBQuery, ExtCtrls;

type
  TFrmCentroCusto = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    Panel3: TPanel;
    ScrollBox1: TScrollBox;
    IBQuery1: TIBQuery;
    IBUpdateSQL1: TIBUpdateSQL;
    IBQuery1DT_ALT: TDateField;
    IBQuery1COD_CCUS: TIntegerField;
    IBQuery1CCUS: TIBStringField;
    IBQuery1ABREV: TIBStringField;
    IBQuery1TIPO_CC: TIntegerField;
    IBQuery1RESPONSAVEL: TIBStringField;
    IBQuery1CC_PAI: TIntegerField;
    IBQuery1CLASSIFICACAO: TIntegerField;
    IBQuery1NIVEL: TIntegerField;
    IBQuery1POSICAO_NIVEL: TIntegerField;
    IBQuery1TIPO: TIBStringField;
    IBQuery1RATEIO: TIBStringField;
    IBQuery1CONTA_CONT_01: TIntegerField;
    IBQuery1CONTA_CONT_04: TIntegerField;
    IBQuery1CONTA_CONT_03: TIntegerField;
    IBQuery1CONTA_CONT_02: TIntegerField;
    IBQuery1CLASS_DESPESAS: TIntegerField;
    IBQuery1PLANTA_SEGURO: TIntegerField;
    IBQuery1TURNO_DEPRECIACAO: TIntegerField;
    Label1: TLabel;
    DBEdit1: TDBEdit;
    DataSource1: TDataSource;
    Label2: TLabel;
    DBEdit2: TDBEdit;
    Label3: TLabel;
    DBEdit3: TDBEdit;
    Label4: TLabel;
    DBEdit4: TDBEdit;
    Label5: TLabel;
    Label6: TLabel;
    DBEdit6: TDBEdit;
    Label7: TLabel;
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
    Label15: TLabel;
    DBEdit15: TDBEdit;
    Label16: TLabel;
    DBEdit16: TDBEdit;
    Label17: TLabel;
    DBEdit17: TDBEdit;
    Label18: TLabel;
    DBEdit18: TDBEdit;
    Label19: TLabel;
    DBEdit19: TDBEdit;
    DBLookupComboBox1: TDBLookupComboBox;
    DBLookupComboBox2: TDBLookupComboBox;
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
    IBQuery2: TIBQuery;
    DataSource2: TDataSource;
    IBQuery3: TIBQuery;
    DataSource3: TDataSource;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmCentroCusto: TFrmCentroCusto;

implementation

uses Unit1;

{$R *.dfm}

end.
