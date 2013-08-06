unit BrvDbGrid;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  BrvDbGrids, Grids, Db;

type
  TBrvDbGrid = class(TBrDbGrids)
  private
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    procedure SetarTodasColunas(pNmColuna : String; pVrColuna : String);
  published
    { Published declarations }
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Banco', [TBrvDbGrid]);
end;

{ TBrvDbGrid }


{ TBrvDbGrid }

procedure TBrvDbGrid.SetarTodasColunas(pNmColuna : String; pVrColuna : String);
begin
      DataSource.DataSet.DisableControls;
      DataSource.DataSet.First;
      while not DataSource.DataSet.Eof do
      begin
            DataSource.DataSet.Edit;
            DataSource.DataSet.FieldByName(pNmColuna).AsString := pVrColuna;
            DataSource.DataSet.Post;
            DataSource.DataSet.Next;
      end;
      DataSource.DataSet.First;
      DataSource.DataSet.EnableControls;
end;

end.
