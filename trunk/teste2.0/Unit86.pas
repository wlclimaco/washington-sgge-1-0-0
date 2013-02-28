unit Unit86;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, DBTables, DBCtrls, Buttons, Mask, StdCtrls, Grids, DBGrids,
  ExtCtrls;

type
  TForm842 = class(TForm)
    Panel1: TPanel;
    DBGrid1: TDBGrid;
    Panel2: TPanel;
    Edit1: TEdit;
    Label1: TLabel;
    Edit2: TEdit;
    Label2: TLabel;
    MaskEdit1: TMaskEdit;
    MaskEdit2: TMaskEdit;
    Label3: TLabel;
    Label4: TLabel;
    SpeedButton1: TSpeedButton;
    DataSource1: TDataSource;
    DataSource2: TDataSource;
    DBText1: TDBText;
    DBText2: TDBText;
    Query1: TQuery;
    Query2: TQuery;
    SpeedButton2: TSpeedButton;
    RadioGroup1: TRadioGroup;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    procedure Edit1Exit(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form842: TForm842;

implementation

uses Unit2, Unit84, Unit93, Unit114, Unit115;

{$R *.dfm}

procedure TForm842.Edit1Exit(Sender: TObject);
begin
if(Edit1.Text <> '')then
BEGIN
  Query1.Active := False;
  Query1.SQL.Text := 'SELECT *FROM PRODUTOS WHERE CODPRO ='+EDIT1.Text;
  Query1.Active := True;
end
 else
   if(Edit2.Text <> '')then
   BEGIN
     Query1.Active := False;
     Query1.SQL.Text := 'SELECT *FROM PRODUTOS WHERE REF LIKE ''%'+EDIT2.Text+'%''';
     Query1.Active := True;
   end;

end;

procedure TForm842.SpeedButton1Click(Sender: TObject);
begin
 if(RadioButton2.Checked = True)then
 begin
  Query1.Active := False;
  Query1.SQL.Clear;
  Query1.SQL.Text := 'select N.CODPRO,p.ref,P.PRODUTO,SUM(N.QUANTIDADE) as total  from nfsaidas2 n,produtos p where n.codpro = p.codpro and n.DTMOVIMENTO BETWEEN '''+formatDateTime('mm/dd/yyyy', StrToDate(MaskEdit1.Text))+''' AND '''+formatDateTime('mm/dd/yyyy', StrToDate(MaskEdit2.Text))+'''';
  if(Edit1.Text <> '')then
   BEGIN
     Query1.SQL.Add('AND CODPRO = '+eDIT1.Text+'  ');
   end;
   if(Edit2.Text <> '')then
   BEGIN
     Query1.SQL.Add('AND REF = '''+eDIT2.Text+'''  ');
   end;
  Query1.SQL.Add(' GROUP BY N.CODPRO,p.ref,P.PRODUTO  order by total');
  Query1.Active := True;
 end;
 if (RadioButton1.Checked = True) then
 begin
     Query1.Active := False;
     Query1.SQL.Clear;
     Query1.SQL.Text := 'select n.dtmovimento, N.CODPRO,p.ref,P.PRODUTO,SUM(N.QUANTIDADE) as total  from nfsaidas2 n,produtos p where n.codpro = p.codpro and n.DTMOVIMENTO BETWEEN '''+formatDateTime('mm/dd/yyyy', StrToDate(MaskEdit1.Text))+''' AND '''+formatDateTime('mm/dd/yyyy', StrToDate(MaskEdit2.Text))+'''  ';
     if(Edit1.Text <> '')then
       BEGIN
         Query1.SQL.Add('AND CODPRO = '+eDIT1.Text+'  ');
       end;
     if(Edit2.Text <> '')then
       BEGIN
        Query1.SQL.Add('AND REF = '''+eDIT2.Text+'''  ');
       end;
     Query1.SQL.Add(' GROUP BY n.dtmovimento, N.CODPRO,p.ref,P.PRODUTO  order by n.dtmovimento');
     Query1.Active := True;
 end;
end;

procedure TForm842.SpeedButton2Click(Sender: TObject);
begin
//Form93.RLReport1.Preview();
 if(RadioButton2.Checked = True)then
 begin
   Form114.RLReport1.Preview();
 end;
  if(RadioButton1.Checked = True)then
 begin
   Form115.RLReport1.Preview();
 end;



end;

end.
