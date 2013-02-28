unit Unit600;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Buttons, U_DBBButton, ExtCtrls, XPInformationPanel,
  DB, IBCustomDataSet, IBQuery, Grids, DBGrids, ActCustomEdit,
  ActNumberEdit, ActResultEdit;

type
  TFrmAprovPag = class(TForm)
    Panel2: TPanel;
    SpeedButton6: TSpeedButton;
    Panel1: TPanel;
    ActResultEdit1: TActResultEdit;
    Panel3: TPanel;
    Panel4: TPanel;
    Panel5: TPanel;
    DBGrid1: TDBGrid;
    Label1: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label2: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    Label14: TLabel;
    Label15: TLabel;
    Label16: TLabel;
    Label17: TLabel;
    Label18: TLabel;
    Label19: TLabel;
    Label20: TLabel;
    Label21: TLabel;
    Label22: TLabel;
    Label23: TLabel;
    Label24: TLabel;
    Label25: TLabel;
    Label26: TLabel;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    IBQuery1DCNUMERO: TIntegerField;
    IBQuery1DCSERIE: TIBStringField;
    IBQuery1DCORDEM: TIBStringField;
    IBQuery1DCTIPO: TIBStringField;
    IBQuery1PARCELA: TFloatField;
    IBQuery1DTVENCIMENTO: TDateField;
    IBQuery1DTLANCAMENTO: TDateField;
    IBQuery1STATUS: TIBStringField;
    IBQuery1TPSITUACAO: TIBStringField;
    IBQuery1VLPARCELA: TFloatField;
    IBQuery1FORNECEDOR: TFloatField;
    IBQuery1OBS: TIBStringField;
    IBQuery1DATAPAGAMENTO: TDateField;
    IBQuery1TIPO_TITULO: TIntegerField;
    IBQuery1COD_EMPRESA: TIntegerField;
    IBQuery1COD_FILIAL: TIntegerField;
    IBQuery1COD_CONTA: TIntegerField;
    IBQuery1COD_TITULO: TIntegerField;
    IBQuery1VALORPAGO: TFloatField;
    IBQuery1OPER_TITULO: TIBStringField;
    IBQuery1ID_MOV_DIARIO: TIntegerField;
    QryEmp: TIBQuery;
    QryFil: TIBQuery;
    QryFor: TIBQuery;
    QryTipo: TIBQuery;
    ibstrngfldIBQuery1NOCHEQUE: TIBStringField;
    fltfldIBQuery1DESCONTO: TFloatField;
    fltfldIBQuery1JUROS: TFloatField;
    IBQuery2: TIBQuery;
    procedure DBGrid1CellClick(Column: TColumn);
    procedure SpeedButton6Click(Sender: TObject);
  private
    { Private declarations }
  public
    
  end;

var
  FrmAprovPag: TFrmAprovPag;

implementation

uses Unit1;

{$R *.dfm}



procedure TFrmAprovPag.DBGrid1CellClick(Column: TColumn);
begin
 QryEmp.Active := False;
 QryEmp.ParamByName('cod_empresa').AsInteger := DBGrid1.Fields[14].Asinteger;
 QryEmp.Active := true;
 LABEL14.Caption := QryEmp.FieldbyName('nome').asString;

 QryFil.Active := false;
 QryFil.ParamByName('cdfilial').AsInteger := DBGrid1.Fields[15].Asinteger;
 QryFil.Active := True;
 LABEL15.Caption := QryFil.FieldbyName('nome').asString;

 Qryfor.Active := false;
 QryFor.ParamByName('cod_Part').AsInteger := DBGrid1.Fields[10].Asinteger;
 Qryfor.Active := True;
 LABEL16.Caption := QryFor.FieldbyName('nome').asString;

 QryTipo.Active := false;
 QryTipo.ParamByName('COD_TIPO_TITULO').AsInteger := DBGrid1.Fields[13].Asinteger;
 QryTipo.Active := True;
 LABEL17.Caption := QryTipo.FieldbyName('DSTIPOTITULO').asString;

 LABEL18.Caption :=  DBGrid1.Fields[6].AsString;
 LABEL19.Caption :=  DBGrid1.Fields[0].AsString;
 LABEL20.Caption :=  DBGrid1.Fields[1].AsString;
 LABEL21.Caption :=  DBGrid1.Fields[2].AsString;
 LABEL22.Caption :=  DBGrid1.Fields[3].AsString;
 LABEL23.Caption :=  DBGrid1.Fields[4].AsString;
 LABEL24.Caption :=  DBGrid1.Fields[9].AsString;
 LABEL25.Caption :=  DBGrid1.Fields[22].AsString;
 LABEL26.Caption :=  DBGrid1.Fields[5].AsString;
 ActResultEdit1.Text :=  DBGrid1.Fields[17].AsString;




end;

procedure TFrmAprovPag.SpeedButton6Click(Sender: TObject);
begin

  IBQuery2.SQL.Clear;
  IBQuery2.SQL.Add('update titulospagar2  set status = ''A'' , data_aprov_pag =  ''01/01/2011'' ,resp_aprov_pag = :resp_aprov_pag ');
  IBQuery2.SQL.Add('                           where cod_empresa = :cod_empresa  and  ');
  IBQuery2.SQL.Add('                           cod_filial  = :Cod_filial   and          ');
  IBQuery2.SQL.Add('                           cod_titulo  = :cod_titulo                ');
  IBQuery2.ParamByName('resp_aprov_pag').AsInteger := 1;
  IBQuery2.ParamByName('cod_empresa').AsInteger    := DBGrid1.Fields[14].Asinteger ;
  IBQuery2.ParamByName('Cod_filial').AsInteger     := DBGrid1.Fields[15].Asinteger ;
  IBQuery2.ParamByName('cod_titulo').AsInteger     := DBGrid1.Fields[17].Asinteger ;
  Try
  IBQuery2.ExecSQL;
  IBQuery2.Transaction.Commit;
   ShowMEssage('PAGAMENTO APROVADO COM SUCESSO !!!');
  IBQuery1.Active := False;
  IBQuery1.Active := True;

  Except
    ShowMEssage('Erro ao aprovar PAGAMENTO');
  end;

end;

end.
