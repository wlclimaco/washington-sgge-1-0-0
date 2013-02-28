unit UConsSub_Grupo;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, Grids, DBGrids, ExtCtrls;

type
  TFrmConsSub_Grupo = class(TForm)
    Panel1: TPanel;
    DBGrid1: TDBGrid;
    IBQuery1: TIBQuery;
    DataSource1: TDataSource;
    procedure DBGrid1DblClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmConsSub_Grupo: TFrmConsSub_Grupo;

implementation

uses UProd_Serv, Unit1, UFormCadProServ;

{$R *.dfm}

procedure TFrmConsSub_Grupo.DBGrid1DblClick(Sender: TObject);
begin
FRMCADPROD.txtSub_Grupo.Text := IntToStr(dbGrid1.Fields[0].AsInteger);
FrmConsSub_Grupo.Close();
end;

end.
