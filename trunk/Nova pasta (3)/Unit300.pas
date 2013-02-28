unit Unit3;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBUpdateSQL, IBQuery, Grids, DBGrids,
  Buttons, ExtCtrls;

type
  TForm3 = class(TForm)
    Panel1: TPanel;
    SpeedButton1: TSpeedButton;
    Panel2: TPanel;
    ScrollBox1: TScrollBox;
    DBGrid1: TDBGrid;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    IBUpdateSQL1: TIBUpdateSQL;
    IBQuery1COD_EMPRESA: TIntegerField;
    IBQuery1COD_FILIAL: TIntegerField;
    IBQuery1COD_TITULO: TIntegerField;
    IBQuery1NOPARCELA: TIntegerField;
    IBQuery1CDTITULAR: TIntegerField;
    IBQuery1DSESPECIE: TIBStringField;
    IBQuery1VLTITULO: TFloatField;
    IBQuery1DTLANCAMENTO: TDateTimeField;
    IBQuery1HOLANCAMENTO: TIBStringField;
    IBQuery1DTVENCIMENTO: TDateTimeField;
    IBQuery1DTLIMITE: TDateTimeField;
    IBQuery1DTDESCONTO: TDateTimeField;
    IBQuery1VLDESCONTO: TFloatField;
    IBQuery1VLJURODIA: TFloatField;
    IBQuery1VLMULTA: TFloatField;
    IBQuery1NOBANCO: TIntegerField;
    IBQuery1CDAGENCIA: TIBStringField;
    IBQuery1NOCONTA: TIntegerField;
    IBQuery1NOSSONUMERO: TFloatField;
    IBQuery1DGNOSSONUMERO: TIBStringField;
    IBQuery1CDREPRESENTANTE: TIntegerField;
    IBQuery1OBSTITULO: TIBStringField;
    IBQuery1STTITULO: TIBStringField;
    IBQuery1STATUS: TIBStringField;
    IBQuery1CARTEIRA: TIBStringField;
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form3: TForm3;

implementation

{$R *.dfm}

procedure TForm3.FormCreate(Sender: TObject);
begin
IbQuery1.Close;
IbQuery1.Open;
IBQuery1.Insert;
end;

end.
