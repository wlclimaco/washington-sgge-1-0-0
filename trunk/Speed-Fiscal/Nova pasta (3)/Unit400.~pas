unit Unit400;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids, DB, IBCustomDataSet, IBQuery, StdCtrls,
  ActHintBalloon, Buttons, ExtCtrls;

type
  TFrmBaixaTitulo = class(TForm)
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    DBGrid1: TDBGrid;
    Panel1: TPanel;
    GroupBox1: TGroupBox;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    GroupBox2: TGroupBox;
    RadioButton3: TRadioButton;
    RadioButton4: TRadioButton;
    RadioButton5: TRadioButton;
    RadioButton6: TRadioButton;
    SpeedButton1: TSpeedButton;
    procedure DBGrid1DblClick(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure RadioButton2Click(Sender: TObject);
    procedure RadioButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmBaixaTitulo: TFrmBaixaTitulo;

implementation

uses Unit1, Unit5, Unit500;

{$R *.dfm}

procedure TFrmBaixaTitulo.DBGrid1DblClick(Sender: TObject);
var
   numero,fornecedor,parcela :Integer;
   dctipo,dcserie,dcordem :String;
begin
 FrmCadTitulos.TXTEMPRESA.Text    := IntToStr(DBGrid1.Fields[14].AsInteger);
  FrmCadTitulos.TXTFILIAL.Text     := IntToStr(DBGrid1.Fields[15].AsInteger);
  FrmCadTitulos.TXTNUMERO.Text     := IntToStr(DBGrid1.Fields[0].AsInteger);
  FrmCadTitulos.TXTSERIE.Text      := DBGrid1.Fields[1].AsString;
  FrmCadTitulos.TXTORDEM.Text      := DBGrid1.Fields[2].AsString ;
  FrmCadTitulos.TXTTIPO.Text       := DBGrid1.Fields[3].AsString ;
  FrmCadTitulos.TXTPARCELA.Text    := IntToStr(DBGrid1.Fields[4].AsInteger) ;
  FrmCadTitulos.TXTFORNECEDOR.Text := IntToStr(DBGrid1.Fields[10].AsInteger) ;
  FrmCadTitulos.TXTDATATITULO.Text := FormatDatetime('DD/MM/YYYY',DBGrid1.Fields[6].AsDateTime) ;
  FrmCadTitulos.TXTVALOR.Text      := FloatToStr(DBGrid1.Fields[9].AsFloat) ;
  FrmCadTitulos.TXTDATVENCI.Text   := FormatDatetime('DD/MM/YYYY',DBGrid1.Fields[5].AsDateTime);
  IF (DBGrid1.Fields[19].AsString = 'R') THEN
     FrmCadTitulos.RadioButton2.Checked := TruE
  ELSE
     FrmCadTitulos.RadioButton1.Checked := TruE ;




     IF (not DBGrid1.Fields[5].IsNull) then
          FrmCadTitulos.TXTDATBAIXA.Text := DateToStr(Now)
     else
          FrmCadTitulos.TXTDATBAIXA.Text   := DateTimeToStr(DBGrid1.Fields[5].AsDatetime) ;

  FrmCadTitulos.TXTVALORPAGO.Text  := FloatToStr(DBGrid1.Fields[18].AsFloat) ;
  FrmCadTitulos.TXTCONTA.Text      := IntToStr(DBGrid1.Fields[16].AsInteger) ;
  FrmCadTitulos.ActResultEdit1.Text:= IntToStr(DBGrid1.Fields[17].AsInteger) ;
  FrmCadTitulos.TXTTIPOTITULO.Text := IntToStr(DBGrid1.Fields[13].AsInteger) ;
  FrmCadTitulos.Show;
end;

procedure TFrmBaixaTitulo.Button1Click(Sender: TObject);
begin
  ShowMEssage(' ');
end;

procedure TFrmBaixaTitulo.SpeedButton1Click(Sender: TObject);
begin
IBQuery1.ACTIVE := fALSE;
IBQuery1.SQL.Clear;
IBQuery1.SQL.Add( 'SELECT *FROM TITULOSPAGAR2 WHERE STATUS <> ''E''' );
if(RadioButton1.Checked = True)then
   IBQuery1.SQL.Add( ' and  OPER_TITULO = ''P''' );

if(RadioButton2.Checked = True)then
   IBQuery1.SQL.Add( ' and  OPER_TITULO = ''R''' );
if(RadioButton6.Checked = True)then
   IBQuery1.SQL.Add( ' and  OPER_TITULO in ( ''R'',''P'')');

if(RadioButton3.Checked = True)then
   IBQuery1.SQL.Add( ' and  STATUS =  ''A''');

if(RadioButton4.Checked = True)then
   IBQuery1.SQL.Add( ' and  STATUS = ''B''');
if(RadioButton5.Checked = True)then
   IBQuery1.SQL.Add( ' and  STATUS in ( ''A'',''B'')');

IBQuery1.SQL.Add( 'ORDER BY DTLANCAMENTO  ');
IBQuery1.ACTIVE := True;

end;

procedure TFrmBaixaTitulo.RadioButton2Click(Sender: TObject);
begin
  RadioButton3.Caption := 'Á RECEBER';
  RadioButton4.Caption := 'RECEBIDOS' ;
end;

procedure TFrmBaixaTitulo.RadioButton1Click(Sender: TObject);
begin
  RadioButton3.Caption := 'Á PAGAR';
  RadioButton4.Caption := 'PAGOS' ;
end;

end.
