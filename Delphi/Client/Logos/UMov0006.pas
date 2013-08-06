unit UMov0006;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCon0011, DB, DBClient, BrvClientDataSet, Grids, BrvDbGrids, BrvDbGrid,
  StdCtrls, BrvEditDate, BrvEditNum, BrvRetCon, Mask, ExtCtrls, Buttons, BrvSpeedButton,
  BrvDbNavCop, BrvListParam, ImgList, Menus;

type
  TMov0006 = class(TCon0011)
    procedure DBGLanctoDblClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0006: TMov0006;

implementation

uses UMov0007;

{$R *.dfm}

procedure TMov0006.DBGLanctoDblClick(Sender: TObject);
begin
      inherited;

      try
          Mov0007 := TMov0007.Create(Self);
          Mov0007.LblNrPlano.Caption   := LblNrPlano.Caption;
          Mov0007.DbEdtMasConDeb.Width := EdtMasConDeb.Width;
          Mov0007.DbEdtMasConCre.Width := EdtMasConDeb.Width;
          Mov0007.gDsMasLim            := EdtMasConCre.EditMask;

          Mov0007.AbrirLancamentos(gDsFiltro);

          Mov0007.ShowModal;
      finally
          FreeAndNil(Mov0007);
      end;

      AbrirPesquisa;
end;

initialization
      RegisterClass(TMov0006);

finalization
      UnRegisterClass(TMov0006);

end.
