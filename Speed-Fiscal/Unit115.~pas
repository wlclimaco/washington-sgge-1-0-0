unit Unit115;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, IBDatabase, ScrollView,
  CustomGridViewControl, CustomGridView, GridView, GridPrint,
  CustomCellSource, DataCellSource, StdCtrls, Buttons, ExtCtrls;

type
  TFrmTitulosP = class(TForm)
    DataCellSource1: TDataCellSource;
    GridPrint1: TGridPrint;
    GridView1: TGridView;
    IBQuery1: TIBQuery;
    Button1: TButton;
    Button2: TButton;
    Button3: TButton;
    IBQuery2: TIBQuery;
    Panel1: TPanel;
    BitBtn1: TBitBtn;
    BitBtn2: TBitBtn;
    BitBtn3: TBitBtn;
    BitBtn4: TBitBtn;
    Label1: TLabel;
    Label2: TLabel;
    procedure Button1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure GridView1SelectCell(Sender: TObject; ACol, ARow: Integer);
    procedure FormActivate(Sender: TObject);
  private
    { Private declarations }
  public
    function teste():boolean;
  end;

var
  FrmTitulosP: TFrmTitulosP;
  Soma :Double;
  conta :Integer;
implementation

uses Unit315, Unit1;

{$R *.dfm}
function TFrmTitulosP.teste():boolean;
begin
  result := True;
end;
procedure TFrmTitulosP.Button1Click(Sender: TObject);
var
  x :Integer;
begin
 IBQuery1.Active := False;
IBQuery1.Active := True;
IBQuery1.FetchAll;
x:= GridView1.RowCount -1  ;
while (x >= 0) do
begin
  if(GridView1.Cell[29,x].AsBoolean = True )then
  begin
  IBQuery2.SQL.Clear;
  IBQuery2.SQL.Add('update titulospagar2  set status = ''A'' , data_aprov_pag =  ''01/01/2011'' ,resp_aprov_pag = :resp_aprov_pag ');
  IBQuery2.SQL.Add('                           where cod_empresa = :cod_empresa  and  ');
  IBQuery2.SQL.Add('                           cod_filial  = :Cod_filial   and          ');
  IBQuery2.SQL.Add('                           cod_titulo  = :cod_titulo                ');
  IBQuery2.ParamByName('resp_aprov_pag').AsInteger := 1;
  IBQuery2.ParamByName('cod_empresa').AsInteger    := GridView1.Cell[14,x].AsInteger ;
  IBQuery2.ParamByName('Cod_filial').AsInteger     := GridView1.Cell[15,x].AsInteger ;
  IBQuery2.ParamByName('cod_titulo').AsInteger     := GridView1.Cell[17,x].AsInteger ;
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
 x:= x-1;
end;
IBQuery1.Active := False;
IBQuery1.Active := True;
while (not IBQuery1.Eof) do
begin
  DataCellSource1.Active := True;


  IBQuery1.Next;
end;
end;

procedure TFrmTitulosP.Button2Click(Sender: TObject);
var x :Integer;
begin
 x:= GridView1.RowCount -1  ;
while (x >= 0) do
begin
  GridView1.Cell[29,x].AsBoolean := True ;

  x:= x-1;

end;
end;

procedure TFrmTitulosP.Button3Click(Sender: TObject);
var x :Integer;
begin
 x:= GridView1.RowCount -1  ;
while (x >= 0) do
begin
  GridView1.Cell[29,x].AsBoolean := False ;

  x:= x-1;

end;
end;

procedure TFrmTitulosP.GridView1SelectCell(Sender: TObject; ACol, ARow: Integer);
begin
        if(GridView1.Cell[29,ARow].AsBoolean = false )then
         soma := soma + GridView1.Cell[09,ARow].AsFloat;
        if(GridView1.Cell[29,ARow].AsBoolean = True )then
         soma := soma - GridView1.Cell[09,ARow].AsFloat;

         Label1.Caption := FloatToStr(soma);
         Label2.Caption := IntToStr(conta);
end;

procedure TFrmTitulosP.FormActivate(Sender: TObject);
begin
IBQuery1.Active := False;
IBQuery1.Active := True;
while (not IBQuery1.Eof) do
begin
  DataCellSource1.Active := True;

  conta := conta +1;
  IBQuery1.Next;
end;
end;

end.
