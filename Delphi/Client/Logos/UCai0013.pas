unit UCai0013;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCai0000, StdCtrls, Buttons, BrvBitBtn, ExtCtrls;

type
  TCai0013 = class(TCai0000)
    Panel3: TPanel;
    ImgCarro6: TImage;
    ImgCarro5: TImage;
    ImgCarro4: TImage;
    ImgCarro1: TImage;
    ImgCaixa1: TImage;
    ImgCarro2: TImage;
    ImgCarro3: TImage;
    ImgCaixa2: TImage;
    ImgCarro7: TImage;
    Panel4: TPanel;
    TmrAnima: TTimer;
    procedure FormCreate(Sender: TObject);
    procedure TmrAnimaTimer(Sender: TObject);
  private
    { Private declarations }
    procedure IniciarAnimacao;
  public
    { Public declarations }
  end;

var
  Cai0013: TCai0013;

implementation

{$R *.dfm}

procedure TCai0013.FormCreate(Sender: TObject);
begin
      inherited;
      IniciarAnimacao;
end;

procedure TCai0013.IniciarAnimacao;
begin
      TmrAnima.Enabled  := False;
      TmrAnima.Interval := 50;

      ImgCarro1.Visible := True;
      ImgCarro1.Left    := 730;
      ImgCarro1.Top     := 36;

      ImgCarro2.Visible := False;
      ImgCarro2.Left    := 620;
      ImgCarro2.Top     := 36;

      ImgCarro3.Visible := False;
      ImgCarro3.Left    := 66;
      ImgCarro3.Top     := 23;

      ImgCarro4.Visible := False;
      ImgCarro4.Left    := 66;
      ImgCarro4.Top     := 10;

      ImgCarro5.Visible := False;
      ImgCarro6.Visible := False;
      ImgCarro7.Visible := False;
      ImgCarro7.Top     := 36;

      ImgCaixa1.Visible := True;
      ImgCaixa1.Left    := 620;
      ImgCaixa1.Top     := 50;

      ImgCaixa2.Left    := 24;
      ImgCaixa2.Top     := 50;

      TmrAnima.Enabled := True;
end;

procedure TCai0013.TmrAnimaTimer(Sender: TObject);
begin
      TmrAnima.Enabled := False;

      if ImgCarro1.Visible then
      begin
            ImgCarro1.Left := ImgCarro1.Left - 5;

            if ImgCarro1.Left = 620 then
            begin
                  ImgCarro2.Visible := True;

                  ImgCarro1.Visible := False;
                  ImgCaixa1.Visible := False;
            end;
      end
      else if ImgCarro2.Visible then
           begin
                 ImgCarro2.Left := ImgCarro2.Left - 5;

                 if ImgCarro2.Left < 65 then
                 begin
                       ImgCarro3.Visible := True;
                       ImgCarro2.Visible := False;

                       TmrAnima.Interval := 100;
                 end;
           end
      else if ImgCarro3.Visible then
           begin
                 ImgCarro4.Visible := True;
                 ImgCarro3.Visible := False;
           end
      else if ImgCarro4.Visible then
           begin
                 ImgCarro4.Left := ImgCarro4.Left - 5;

                 if ImgCarro4.Left <= 25 then
                 begin
                       ImgCaixa1.Top     := 24;
                       ImgCaixa1.Left    := 24;
                       ImgCaixa1.Visible := True;

                       ImgCarro5.Top     := ImgCarro4.Top;
                       ImgCarro5.Left    := ImgCarro4.Left;
                       ImgCarro5.Visible := True;

                       ImgCarro4.Visible := False;
                 end;
           end
      else if ImgCarro5.Visible then
           begin
                 ImgCarro5.Left := ImgCarro5.Left + 5;

                 if ImgCarro5.Left >= 65 then
                 begin
                       ImgCarro6.Left    := ImgCarro5.Left;
                       ImgCarro6.Top     := ImgCarro5.Top;
                       ImgCarro6.Visible := True;
                       ImgCarro5.Visible := False;
                 end;
           end
      else if ImgCarro6.Visible then
           begin
                 ImgCarro7.Left    := ImgCarro6.Left;
                 ImgCarro7.Visible := True;
                 ImgCarro6.Visible := False;
           end
      else if ImgCarro7.Visible then
           begin
                 ImgCarro7.Left    := ImgCarro7.Left + 5;

                 if ImgCarro7.Left >= 220 then
                 begin
                       ImgCarro7.Visible := False;
                 end;
           end
      else begin
                 IniciarAnimacao;
           end
      ;

      TmrAnima.Enabled := True;
end;

end.
