unit Unit24;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids;

type
  TCONSPRINTERS = class(TForm)
    DBGrid1: TDBGrid;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  CONSPRINTERS: TCONSPRINTERS;

implementation

uses Unit2;

{$R *.dfm}

end.
