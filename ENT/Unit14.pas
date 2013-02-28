unit Unit14;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, IBCustomDataSet, IBUpdateSQL, ACBrNFe, ACBrNFeDANFEClass,
  ACBrNFeDANFERave, DB, IBQuery, IBDatabase, ComCtrls, OleCtrls, SHDocVw,
  StdCtrls, DBCtrls, Grids, DBGrids, Buttons, ActRadioButton, ExtCtrls,
  ActMask, ActDateEdit, ActCurrencyEdit, ActEdit, ActCustomEdit,
  ActNumberEdit;

type
  TForm14 = class(TForm)
    Panel1: TPanel;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    Label14: TLabel;
    Label16: TLabel;
    Label18: TLabel;
    Label24: TLabel;
    TXTDCNUMERO: TActNumberEdit;
    TXTDCSERIE: TActEdit;
    TXTDCORDEM: TActEdit;
    TXTICMS: TActCurrencyEdit;
    TXTIPI: TActCurrencyEdit;
    TXTFRETE: TActCurrencyEdit;
    TXTVLNOTA: TActCurrencyEdit;
    TXTDTENTRADA: TActDateEdit;
    TXTDTEMISSAO: TActDateEdit;
    TXTTRASNPORTADO: TActNumberEdit;
    TXTCFOP: TActNumberEdit;
    TXTST: TActCurrencyEdit;
    TXTCDPEDIDO: TActNumberEdit;
    RadioGroup1: TRadioGroup;
    ActRadioButton1: TActRadioButton;
    ActRadioButton2: TActRadioButton;
    GroupBox1: TGroupBox;
    Label15: TLabel;
    Label17: TLabel;
    SpeedButton1: TSpeedButton;
    Label19: TLabel;
    txtnoparcela: TActNumberEdit;
    txtvalor: TActCurrencyEdit;
    Panel3: TPanel;
    DBGrid1: TDBGrid;
    txtdtvencimento: TActDateEdit;
    txtfornecedor: TActNumberEdit;
    txtnatureza: TActNumberEdit;
    TXTDCTIPO: TActEdit;
    ActCurrencyEdit2: TActCurrencyEdit;
    Panel2: TPanel;
    DBGrid2: TDBGrid;
    Panel4: TPanel;
    SpeedButton7: TSpeedButton;
    SpeedButton8: TSpeedButton;
    SpeedButton5: TSpeedButton;
    SpeedButton6: TSpeedButton;
    SpeedButton2: TSpeedButton;
    SpeedButton3: TSpeedButton;
    SpeedButton4: TSpeedButton;
    SpeedButton9: TSpeedButton;
    SpeedButton10: TSpeedButton;
    SpeedButton11: TSpeedButton;
    Label25: TLabel;
    Label26: TLabel;
    Label31: TLabel;
    Label35: TLabel;
    Label36: TLabel;
    Label37: TLabel;
    Panel5: TPanel;
    DBText1: TDBText;
    DBText2: TDBText;
    Label23: TLabel;
    Label27: TLabel;
    Label28: TLabel;
    Label29: TLabel;
    Label30: TLabel;
    SpeedButton12: TSpeedButton;
    SpeedButton13: TSpeedButton;
    txtcodpro: TActNumberEdit;
    txtquantidade: TActNumberEdit;
    txtvlunitario: TActCurrencyEdit;
    TXTICMSI: TActCurrencyEdit;
    TXTICMSBASEI: TActCurrencyEdit;
    txtporc: TActCurrencyEdit;
    TXTVlSuj: TActCurrencyEdit;
    TXTVLVENDA: TActCurrencyEdit;
    IBDatabase1: TIBDatabase;
    IBTransaction1: TIBTransaction;
    IBQuery1: TIBQuery;
    IBQuery1DCNUMERO: TIntegerField;
    IBQuery1DCSERIE: TIBStringField;
    IBQuery1DCORDEM: TIBStringField;
    IBQuery1DCTIPO: TIBStringField;
    IBQuery1NATUREZA: TIntegerField;
    IBQuery1VLNOTA: TFloatField;
    IBQuery1VLICMS: TFloatField;
    IBQuery1VLIPI: TFloatField;
    IBQuery1VLFRETE: TFloatField;
    IBQuery1CFOP: TFloatField;
    IBQuery1DTENTRADA: TDateField;
    IBQuery1DTEMISSAO: TDateField;
    IBQuery1TRANSPORTADO: TFloatField;
    IBQuery1CDPEDIDO: TFloatField;
    IBQuery1IDNFENTRADAS: TIntegerField;
    IBQuery1VLST: TFloatField;
    IBQuery1TPSITUACAO: TIBStringField;
    IBQuery1CDFORNECEDOR: TIntegerField;
    DataSource1: TDataSource;
    IBQuery2: TIBQuery;
    IBQuery3: TIBQuery;
    DataSource2: TDataSource;
    DataSource3: TDataSource;
    IBQuery4: TIBQuery;
    IBQuery5: TIBQuery;
    IBQuery5DCNUMERO: TIntegerField;
    IBQuery5DCSERIE: TIBStringField;
    IBQuery5DCORDEM: TIBStringField;
    IBQuery5DCTIPO: TIBStringField;
    IBQuery5CODPRO: TIntegerField;
    IBQuery5REF: TIBStringField;
    IBQuery5PRODUTO: TIBStringField;
    IBQuery5CNB: TIntegerField;
    IBQuery5QUANTIDADE: TFloatField;
    IBQuery5VLUNITARIO: TFloatField;
    IBQuery5ICMSBSI: TFloatField;
    IBQuery5ICMS: TFloatField;
    IBQuery5PORC: TFloatField;
    IBQuery5PRECOVENDA: TFloatField;
    DataSource4: TDataSource;
    IBQuery6: TIBQuery;
    DataSource5: TDataSource;
    IBQgravartitulospagar: TIBQuery;
    IBQgravarnfentitens: TIBQuery;
    IBQgravnfentrad: TIBQuery;
    IBQuery7: TIBQuery;
    IBQuery8: TIBQuery;
    qryprodutos1: TIBQuery;
    qryMovEstoque: TIBQuery;
    IBQuery9: TIBQuery;
    ACBrNFeDANFERave1: TACBrNFeDANFERave;
    ACBrNFe1: TACBrNFe;
    OpenDialog1: TOpenDialog;
    IBQuery10: TIBQuery;
    IBUpdateSQL1: TIBUpdateSQL;
    IBQuery11: TIBQuery;
    IBQuery12: TIBQuery;
    Label20: TLabel;
    Label21: TLabel;
    Label22: TLabel;
    Label32: TLabel;
    Label33: TLabel;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form14: TForm14;

implementation

{$R *.dfm}

end.
