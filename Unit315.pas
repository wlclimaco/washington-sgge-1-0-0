unit Unit315;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, CustomCellSource, DataCellSource,
  GridPrint, ScrollView, CustomGridViewControl, CustomGridView, GridView,
  ExtCtrls;

type
  TFrmConsNfe = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    Panel3: TPanel;
    GridView1: TGridView;
    GridPrint1: TGridPrint;
    DataCellSource1: TDataCellSource;
    IBQuery1: TIBQuery;
    IBQuery1IND_OPER: TIntegerField;
    IBQuery1IND_EMIT: TIntegerField;
    IBQuery1COD_PART: TIBStringField;
    IBQuery1COD_SIT: TIBStringField;
    IBQuery1DT_DOC: TDateField;
    IBQuery1DT_E_S: TDateField;
    IBQuery1VL_DOC: TIntegerField;
    IBQuery1VL_DESC: TIntegerField;
    IBQuery1VL_FORN: TIntegerField;
    IBQuery1VL_SERV_NT: TIntegerField;
    IBQuery1VL_TERC: TIntegerField;
    IBQuery1VL_DA: TIntegerField;
    IBQuery1VL_BC_ICMS: TIntegerField;
    IBQuery1VL_ICMS: TIntegerField;
    IBQuery1VL_BC_ICMS_ST: TIntegerField;
    IBQuery1VL_ICMS_ST: TIntegerField;
    IBQuery1COD_INF: TIntegerField;
    IBQuery1VL_COFINS: TIntegerField;
    IBQuery1TP_LIGACAO: TIntegerField;
    IBQuery1COD_GRUPO_TENSAO: TIBStringField;
    IBQuery1CHV_CTE: TIBStringField;
    IBQuery1TP_CT: TIBStringField;
    IBQuery1CHV_CTE_REF: TIBStringField;
    IBQuery1IND_FRT: TIBStringField;
    IBQuery1CODNFE: TIntegerField;
    IBQuery1COD_EMPRESA: TIntegerField;
    IBQuery1DCNUMERO: TIntegerField;
    IBQuery1DCSERIE: TIBStringField;
    IBQuery1DCORDEM: TIBStringField;
    IBQuery1DCTIPO: TIBStringField;
    IBQuery1CDPEDIDO: TIntegerField;
    IBQuery1NATUREZA: TIntegerField;
    IBQuery1CFOP: TIBStringField;
    IBQuery1COD_FILIAL: TIntegerField;
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
     function CriaSql(const numero:Integer;nfe :Integer):String;
  end;

var
  FrmConsNfe: TFrmConsNfe;
  sql  : String;
  Cons : Integer;
implementation

uses Unit1;

{$R *.dfm}
function TFrmConsNfe.CriaSql(const numero :Integer;nfe :Integer):String;
begin
  if(numero = 1)then
     sql := 'select *from nffiscal';
  if(numero = 2)then
     sql := 'select *from nffiscal where codnfe ='+IntToStr(nfe);
end;
procedure TFrmConsNfe.FormCreate(Sender: TObject);
begin
{IBQuery1.SQL.Clear;
CriaSql(1,1);
IBQuery1.SQL.Add(sql);
IBQuery1.Active := True ;

while (not IBQuery1.Eof) do
begin
  DataCellSource1.Active := True;
  IBQuery1.Next;
end;  }
end;

end.
