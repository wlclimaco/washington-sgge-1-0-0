unit Unit2;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, Grids, DBGrids, IBCustomDataSet, IBQuery, ExtCtrls,
  IBUpdateSQL, Buttons;

type
  TForm2 = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    ScrollBox1: TScrollBox;
    IBQuery1: TIBQuery;
    DBGrid1: TDBGrid;
    DataSource1: TDataSource;
    IBUpdateSQL1: TIBUpdateSQL;
    SpeedButton1: TSpeedButton;
    IBQuery1NOBANCO: TIntegerField;
    IBQuery1CDAGENCIA: TIBStringField;
    IBQuery1NOCONTA: TIBStringField;
    IBQuery1NOCHEQUE: TIBStringField;
    IBQuery1COD_PART: TIntegerField;
    IBQuery1VLCHEQUE: TFloatField;
    IBQuery1DTMOVIMENTO: TDateTimeField;
    IBQuery1DTPREDATADO: TDateTimeField;
    IBQuery1NOCNPJCPF: TIBStringField;
    IBQuery1COD_EMPRESA: TIntegerField;
    IBQuery1COD_FILIAL: TIntegerField;
    IBQuery1TPSITUACAO: TIBStringField;
    IBQuery1NMEMITENTE: TIBStringField;
    IBQuery1DTBAIXA: TDateTimeField;
    IBQuery1DTDEVOLUCAO: TDateTimeField;
    IBQuery1DSOBSERVACAO: TIBStringField;
    IBQuery1DTEMISSAO: TDateTimeField;
    IBQuery1DTBAIXADEVOL: TDateTimeField;
    IBQuery1DTSEGDEV: TDateTimeField;
    IBQuery1DTSEGBAI: TDateTimeField;
    IBQuery1DSSERIE: TIBStringField;
    procedure FormCreate(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form2: TForm2;

implementation

uses Unit1;

{$R *.dfm}

procedure TForm2.FormCreate(Sender: TObject);
begin
IbQuery1.Close;
IbQuery1.Open;
IBQuery1.Insert;

end;

procedure TForm2.SpeedButton1Click(Sender: TObject);
begin
 IBQuery1.Post;
 IBQuery1.Transaction.Commit;
 IbQuery1.Close;
end;

end.
