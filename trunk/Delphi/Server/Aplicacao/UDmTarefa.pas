unit UDmTarefa;

interface

uses
  SysUtils, Classes, ComCtrls;

type
  TDmTarefa = class(TDataModule)
  private
    { Private declarations }
  public
    { Public declarations }
    procedure Executar(pGauge: TProgressBar); virtual;
  end;

var
  DmTarefa: TDmTarefa;

implementation

{$R *.dfm}

{ TDmTarefa }

procedure TDmTarefa.Executar(pGauge: TProgressBar);
begin
// n�o faz nada mesmo
end;

end.
