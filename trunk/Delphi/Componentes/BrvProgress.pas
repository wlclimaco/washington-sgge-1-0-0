unit BrvProgress;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  ComCtrls, ExtCtrls, Buttons, BrvGauge, BrvSpeedButton, StdCtrls, BrvBitBtn;

type
  TFrmProgress = class(TForm)
    BtnCancelar: TBrvBitBtn;
    Gauge: TBrvGauge;
    procedure FormCreate(Sender: TObject);
    procedure BtnCancelarClick(Sender: TObject);
  public
    procedure AbilitarCancel;
  end;

var
  FrmProgress: TFrmProgress;

implementation

{$R *.DFM}

procedure TFrmProgress.FormCreate(Sender: TObject);
begin
      Gauge.Width             := 415;
      BtnCancelar.Visible     := False;
      Gauge.BrProcessMessages := False;
end;

procedure TFrmProgress.AbilitarCancel;
begin
      Gauge.Width             := 340;
      BtnCancelar.Visible     := True;
      Gauge.BrProcessMessages := True;
      Refresh;
end;

procedure TFrmProgress.BtnCancelarClick(Sender: TObject);
begin
      Gauge.BrDataSet.Last;
      Close;
end;

end.
