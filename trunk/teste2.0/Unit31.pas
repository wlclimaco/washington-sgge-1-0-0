unit Unit31;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids;

type
  TCONSXMATERIA = class(TForm)
    DBGrid1: TDBGrid;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  CONSXMATERIA: TCONSXMATERIA;

implementation

uses Unit2;

{$R *.dfm}

end.
