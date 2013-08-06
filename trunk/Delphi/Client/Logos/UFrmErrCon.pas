unit UFrmErrCon;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Buttons, ComCtrls, ExtCtrls;

type
  TFrmErrCon = class(TForm)
    PnlFundo: TPanel;
    MenDsMsgErr: TMemo;
    Panel1: TPanel;
    LblDsTempo: TLabel;
    PgbTempo: TProgressBar;
    PnlBaixo: TPanel;
    BbtOk: TBitBtn;
    Label2: TLabel;
    Timer: TTimer;
    procedure TimerTimer(Sender: TObject);
    procedure BbtOkClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmErrCon: TFrmErrCon;

implementation

{$R *.dfm}

procedure TFrmErrCon.BbtOkClick(Sender: TObject);
begin
      Close;
end;

procedure TFrmErrCon.TimerTimer(Sender: TObject);
begin
      PgbTempo.Position := PgbTempo.Position -1;

      if PgbTempo.Position = 0 then
      begin
            Close;
      end else
      begin
            LblDsTempo.Caption := 'A aplicação encerrará em: ' +
                                  IntToStr(PgbTempo.Position) + ' segundo(s)';
            LblDsTempo.Refresh;
      end;
end;

end.
