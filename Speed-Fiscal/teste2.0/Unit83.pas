unit Unit83;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DBCtrls, Grids, DBGrids, Mask, StdCtrls, DB, DBTables, Buttons,
  IBCustomDataSet, IBQuery, ExtCtrls;

type
  TForm83 = class(TForm)
    Edit1: TEdit;
    Edit2: TEdit;
    Edit3: TEdit;
    Edit4: TEdit;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    MaskEdit1: TMaskEdit;
    DBGrid1: TDBGrid;
    DataSource1: TDataSource;
    SpeedButton1: TSpeedButton;
    DataSource2: TDataSource;
    Query1: TIBQuery;
    Query2: TIBQuery;
    Query3: TIBQuery;
    Panel1: TPanel;
    GroupBox1: TGroupBox;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    IBQuery1: TIBQuery;
    IBQuery2: TIBQuery;
    IBQuery3: TIBQuery;
    IBQuery4: TIBQuery;
    IBQuery5: TIBQuery;
    Label12: TLabel;
    Query4: TIBQuery;
    Query5: TIBQuery;
    IBQuery6: TIBQuery;
    Query7: TIBQuery;
    IBQuery8: TIBQuery;
    IBQuery9: TIBQuery;
    procedure Edit1Exit(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure MaskEdit1Exit(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form83: TForm83;

implementation

uses Unit2, Unit1;

{$R *.dfm}

procedure TForm83.Edit1Exit(Sender: TObject);
begin
If(Edit1.Text <> '') or (Edit1.Text <> ' ')then
begin
  query1.Active := false;
  query1.SQL.Text := 'select *from prod_serv where cod_produto = '+edit1.Text;
  query1.Active := true;
  Label12.Caption :=  query1.Fieldbyname('DESCR_PRODUTO').AsString;
  Edit3.Text := FloatToStr(query1.Fieldbyname('precovenda').asFloat);
end;

end;

procedure TForm83.SpeedButton1Click(Sender: TObject);
var
  times1 :Tdatetime;
  nome :string ;
  max  :integer;
begin

  times1 := STRTODATE(MaskEdit1.Text);
  Query3.Active := False;
  QUERY3.sql.Clear;
  query3.SQL.Add('select *from nfsaidas2 n where n.stfiscal = ''N'' and n.dtmovimento = '''+formatDateTime('mm/dd/yyyy',times1)+'''');
  query3.Active := true;
  IBquery6.SQL.Clear;
  IBquery6.SQL.Add('select *from Prod_Serv p  where p.cod_produto =  '+ Edit1.Text);
  IBquery6.Active := True;
  if(query3.eof  )then
  begin
      query1.Active := false;
      query1.SQL.Clear;
      query1.SQL.Add('select max(coo) from nfsaidas2 ');
      query1.Active := true;
      max :=  query1.FieldByName('MAX').AsInteger + 1;
      query1.Active := false;
      try
          QUERY2.sql.Clear;
          QUERY2.SQL.Add('INSERT INTO NFSAIDAS2 (COO,        CDPRODUTO,                              QUANTIDADE,  VLNota,     CODPRO,STATUS,DTMOVIMENTO,STFISCAL)VALUES  ');
          QUERY2.SQL.Add('('+IntToStr(max)+','''+IBquery6.FIELDBYNAME('REF').AsString+''','+EDIT2.Text+','+DataModule1.ajustaVirgula(EDIT3.Text)+','+EDIT1.Text+',''A'','''+formatDateTime('mm/dd/yyyy',times1)+''', ''N'' )');
          QUERY4.sql.Clear;
          QUERY4.SQL.Add('INSERT INTO NFSAIDASitens (COO,             NOITEM,    QUANTIDADE,     VLPRODUTO, BASEICMS, ALIQUOTA, VLICMS, CODPRO       ,                                 CDPRODUTO, INTEGRADO)VALUES  ');
          QUERY4.SQL.Add('                          ('+IntToStr(max)+','+IntToStr(1)+','+EDIT2.Text+','+DataModule1.ajustaVirgula(EDIT3.Text)+',''0''    ,''18''   ,''0''  ,'+EDIT1.Text+','''+IBquery6.FIELDBYNAME('REF').AsString+''',''S'')');
          ShowMEssage(QUERY4.SQL.Text);
          QUERY2.ExecSQL;
          QUERY2.Transaction.Commit;
          QUERY4.ExecSQL;
          QUERY4.Transaction.Commit;

      Except
          QUERY2.Transaction.Rollback;
          QUERY4.Transaction.Rollback;
         // RaiseException('Erro ao inserir os dados');
      end;
  end
  else
  begin
     query1.Active := false;
     query1.SQL.Clear;
     query1.SQL.Add('select n.coo from nfsaidas2 n where n.dtmovimento = '''+formatDateTime('mm/dd/yyyy',times1)+'''  and n.stfiscal = ''N''');
     query1.Active := true;
     max :=  query1.FieldByName('COO').AsInteger;
     query1.Active := false;
     QUERY5.SQL.Clear;
     QUERY5.Active := False;
     QUERY5.SQL.Add('select max(n.noitem) from nfsaidasitens n where n.coo = :coo ');
     QUERY5.ParamByName('coo').AsInteger := max;
     QUERY5.Active := True;
     QUERY4.sql.Clear;
     QUERY4.SQL.Add('INSERT INTO NFSAIDASitens (COO, NOITEM, QUANTIDADE, VLPRODUTO, BASEICMS, ALIQUOTA, VLICMS, CODPRO, CDPRODUTO, INTEGRADO)VALUES  ');
     QUERY4.SQL.Add('('+IntToStr(max)+','''+IntToStr(QUERY5.FieldByName('MAX').AsInteger + 1 )+''','+EDIT2.Text+','+DataModule1.ajustaVirgula(EDIT3.Text)+',''0'',''18'',''0'','+EDIT1.Text+','''+IBquery6.FIELDBYNAME('REF').AsString+''',''S'')');
     QUERY4.ExecSQL;
     QUERY4.Transaction.Commit;

  end;




  query3.Active := false;
  query3.SQL.Text := 'select *from nfsaidas2 where dtmovimento =  '''+formatDateTime('mm/dd/yyyy', StrToDate(MaskEdit1.Text))+'''';
  query3.Active := true;
end;

procedure TForm83.MaskEdit1Exit(Sender: TObject);
begin
If(Edit1.Text <> '') or (Edit1.Text <> ' ')then
begin
  query7.Active := false;
  query7.SQL.Clear;
  query7.SQL.Add('select n2.dtmovimento,n.coo,n.quantidade,n.vlproduto,n2.vlnota,p.produto  from nfsaidasitens n ,prodsinte p,nfsaidas2 n2 where n2.coo = n.coo and p.codsin = n.cdproduto  and n2.dtmovimento =  '''+FormatDateTime('DD.MM.YYYY',StrToDate(MaskEdit1.Text))+'''');
  query7.SQL.Add('group by  n2.dtmovimento,n.coo,n.quantidade,n.vlproduto,n2.vlnota,p.produto');
  query7.Active := true;
  IBquery1.Active := False;
  IBquery1.ParamByName('data').AsDate := StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(MaskEdit1.Text)));
  IBquery1.Active := True;
  Label9.Caption  := IBquery1.FieldByNAme('total').AsString ;
  IBquery2.Active := False;
  IBquery2.ParamByName('dtmovimento').AsDate := StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(MaskEdit1.Text)));
  IBquery2.Active := True;
  Label10.Caption  := IBquery2.FieldByNAme('vendabruta').AsString ;
  IBquery3.Active := False;
  IBquery3.ParamByName('dtmovimento').AsDate := StrToDate(FormatDateTime('DD/MM/YYYY',StrToDate(MaskEdit1.Text)));
  IBquery3.Active := True;
  Label11.Caption  := IBquery3.FieldByNAme('total').AsString ;

end;
end;

end.
