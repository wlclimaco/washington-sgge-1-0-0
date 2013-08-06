unit BrvCalculadora;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs;

type
  TBrvCalculadora = class(TComponent)
  private
    { Private declarations }
    function CriarCalculadora(const FrmCalcul: TForm; FrmPrinci: TForm): Boolean;
  protected
    { Protected declarations }
  public
    { Public declarations }
    function    Execute(VrAtual : Real) : Real;
    procedure   Executar;
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
  published
    { Published declarations }
  end;

procedure Register;

implementation

Uses BrvCalculadoraForm;

procedure Register;
begin
      RegisterComponents('Bravo Utils', [TBrvCalculadora]);
end;

constructor TBrvCalculadora.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);
end;

function TBrvCalculadora.Execute(VrAtual : Real) : Real;
begin
      FrmCalculadora := TFrmCalculadora.Create(Self);

      FrmCalculadora.EdtVrTotal.BrAsFloat := VrAtual;

      if  FrmCalculadora.ShowModal = MrOk then
      begin
            Result := FrmCalculadora.EdtVrTotal.BrAsFloat;
      end else
      begin
            Result := VrAtual;
      end;

      FrmCalculadora.Destroy;
end;

procedure TBrvCalculadora.Executar;
begin
      if  CriarCalculadora(FrmCalculadora, (Owner as TForm)) then
      begin
            FrmCalculadora := TFrmCalculadora.Create(Self);
            FrmCalculadora.FormStyle            := fsMDIChild;
            FrmCalculadora.EdtVrTotal.BrAsFloat := 0;
      end;

      FrmCalculadora.Show;
end;

function TBrvCalculadora.CriarCalculadora
                                      (const FrmCalcul: TForm; FrmPrinci: TForm): Boolean;
var QtForm : Integer;
begin
      Result := True;
      QtForm := 0;

      while (QtForm < FrmPrinci.MDIChildCount) and (Result) do
      begin
            if FrmPrinci.MDIChildren[QtForm] = FrmCalcul then
            begin
                  Result := False
            end else
            begin
                  Inc(QtForm);
            end;
      end;
end;

destructor TBrvCalculadora.Destroy;
begin
      inherited  destroy;
end;

end.
