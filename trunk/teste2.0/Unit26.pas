unit Unit26;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Grids, DBGrids;

type
  TCONSPWGRUPOS = class(TForm)
    DBGrid1: TDBGrid;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  CONSPWGRUPOS: TCONSPWGRUPOS;

implementation

uses Unit2;

{$R *.dfm}

end.
