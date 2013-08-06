unit URel0000;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, ExtCtrls, Buttons,
  BrvGeraRelatorio, BrvSpeedButton, BrvDbNavCop, BrvListParam, ImgList, Menus, StdCtrls, BrvBitBtn;

type
  TRel0000 = class(TCad0000)
    BrvGerRel: TBrvGeraRelatorio;
    PnlRodape: TPanel;
    BtnGeraRel: TBrvBitBtn;
    procedure FormCreate(Sender: TObject);
    procedure FormKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
    procedure SbtImprimClick(Sender: TObject);
    procedure BtnGeraRelClick(Sender: TObject);
  protected
    { Protected declarations }
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Rel0000: TRel0000;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TRel0000.BtnGeraRelClick(Sender: TObject);
begin
      inherited;
      //
end;

procedure TRel0000.FormCreate(Sender: TObject);
begin
      inherited;
     //  if FrmLogos.PnlSrvImp.Tag <> 1 then
     // begin
     //       raise Exception.Create('Servidor de impressão não conectado.' + #13#13 +
     //                              'Aguarde conexão e tente novamente.');
     // end;
end;

procedure TRel0000.FormKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
begin
      if Shift = [ssCtrl] then
      begin
            case key of
                 80: if BtnGeraRel.Enabled  then BtnGeraRelClick(Self);  {ctrl+p}
            end;
      end;
end;

procedure TRel0000.SbtImprimClick(Sender: TObject);
begin
      inherited;
      //
end;

end.
