unit Unit53;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Unit1, StdCtrls, Mask, DBCtrls, Buttons, U_DBBButton, ExtCtrls,
  DB, DBTables;

type
  TFRMPRODUTOS = class(TForm1)
    Label1: TLabel;
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
    Label20: TLabel;
    DBEdit20: TDBEdit;
    Label21: TLabel;
    DBEdit21: TDBEdit;
    Label22: TLabel;
    DBEdit22: TDBEdit;
    qry1: TQuery;
    DataSource1: TDataSource;
    UpdateSQL1: TUpdateSQL;
    qry1CODPRO: TFloatField;
    qry1PRODUTO: TStringField;
    qry1REF: TStringField;
    qry1CLASS: TStringField;
    qry1UNIDADE: TStringField;
    qry1LOCAL: TStringField;
    qry1CODIND: TFloatField;
    qry1INDICE: TStringField;
    qry1VALOR: TFloatField;
    qry1CODCLI: TFloatField;
    qry1RAZAO: TStringField;
    qry1AQUIS: TFloatField;
    qry1ICMS: TFloatField;
    qry1IPI: TFloatField;
    qry1FINAN: TFloatField;
    qry1FRETE: TFloatField;
    qry1CUSTO: TFloatField;
    qry1PRAZO: TSmallintField;
    qry1PRECOVENDA: TFloatField;
    qry1STATUS: TStringField;
    qry1ESTOQUE: TFloatField;
    qry1ESTOQUEANT: TFloatField;
    Edit1: TEdit;
    Label23: TLabel;
    procedure DBEdit1KeyDown(Sender: TObject; var Key: Word;
      Shift: TShiftState);
    procedure FormActivate(Sender: TObject);
    procedure DBEdit1Exit(Sender: TObject);
    procedure Edit1Exit(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FRMPRODUTOS: TFRMPRODUTOS;

implementation

uses Unit2, Unit67;

{$R *.dfm}

procedure TFRMPRODUTOS.DBEdit1KeyDown(Sender: TObject; var Key: Word;
  Shift: TShiftState);
begin
  inherited;
If Key = 112 then
 form67.Show;


end;

procedure TFRMPRODUTOS.FormActivate(Sender: TObject);
begin
  inherited;
//DataModule2.tblprodutos.Active := true;
// qry1.Active := true;
end;

procedure TFRMPRODUTOS.DBEdit1Exit(Sender: TObject);
begin
 { inherited;

 qry1.Active := false;
 qry1.SQL.Text := 'select *from produtos where codpro = '+DBEdit1.Text;
 qry1.Active := true;
 {DBEdit2.Text := qry1.fieldbyname('PRODUTO').AsString;
 DBEdit3.Text := qry1.fieldbyname('REF').AsString;
 DBEdit4.Text := qry1.fieldbyname('CLASS').AsString;
 DBEdit5.Text := qry1.fieldbyname('UNIDADE').AsString;
 DBEdit6.Text := qry1.fieldbyname('LOCAL').AsString;
 DBEdit7.Text := IntToStr(qry1.fieldbyname('CODIND').AsInteger);
 DBEdit8.Text := qry1.fieldbyname('INDICE').AsString;
 DBEdit9.Text := FloatToStr(qry1.fieldbyname('VALOR').Asfloat);
 DBEdit10.Text := IntToStr(qry1.fieldbyname('CODCLI').Asinteger);
 DBEdit11.Text := qry1.fieldbyname('RAZAO').AsString;
 DBEdit12.Text := qry1.fieldbyname('AQUIS').AsString;
 DBEdit13.Text := FloatToStr(qry1.fieldbyname('ICMS').Asfloat);
 DBEdit14.Text := FloatToStr(qry1.fieldbyname('IPI').AsFloat);
 DBEdit15.Text := qry1.fieldbyname('FINAN').AsString;
 DBEdit16.Text := qry1.fieldbyname('FRETE').AsString;
 DBEdit17.Text := qry1.fieldbyname('CUSTO').AsString;
 DBEdit18.Text := qry1.fieldbyname('PRAZO').AsString;
 DBEdit19.Text := qry1.fieldbyname('PRECOVENDA').AsString;
 DBEdit20.Text := qry1.fieldbyname('STATUS').AsString;
 DBEdit21.Text := qry1.fieldbyname('ESTOQUE').AsString;
 DBEdit22.Text := qry1.fieldbyname('ESTOQUEANT').AsString;
end; }

end;

procedure TFRMPRODUTOS.Edit1Exit(Sender: TObject);
begin
  inherited;
 qry1.Active := false;
 qry1.SQL.Text := 'select *from produtos where codpro = '+Edit1.Text;
 qry1.Active := true;
end;

end.
