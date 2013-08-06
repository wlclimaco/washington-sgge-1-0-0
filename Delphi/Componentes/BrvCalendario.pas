unit BrvCalendario;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs;

type
  TBrvCalendario = class(TComponent)
  private
    { Private declarations }
    function CriarCalendario(const FrmCalcul: TForm; FrmPrinci: TForm): Boolean;
  protected
    { Protected declarations }
  public
    { Public declarations }
    procedure   Execute(var DtAtual : TDate; var SnExecut : Boolean);
    procedure   Executar;
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
  published
    { Published declarations }
  end;

procedure Register;

implementation

Uses BrvCalendarioForm;

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvCalendario]);
end;

constructor TBrvCalendario.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);
end;

procedure TBrvCalendario.Execute(var DtAtual : TDate; var SnExecut : Boolean);
begin
      SnExecut := False;

      FrmCalendar                := TFrmCalendar.Create(Self);
      FrmCalendar.MthCalend.Date := DtAtual;

      if  FrmCalendar.ShowModal = MrOk then
      begin
            DtAtual  := FrmCalendar.MthCalend.Date;
            SnExecut := True;
      end;

      FrmCalendar.Destroy;
end;

procedure TBrvCalendario.Executar;
begin
      if  CriarCalendario(FrmCalendar, (Owner as TForm)) then
      begin
            FrmCalendar                := TFrmCalendar.Create(Self);
            FrmCalendar.FormStyle      := fsMDIChild;
            FrmCalendar.MthCalend.Date := Date;
      end;

      FrmCalendar.Show;
end;

function TBrvCalendario.CriarCalendario
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

destructor TBrvCalendario.Destroy;
begin
      inherited  destroy;
end;

end.
