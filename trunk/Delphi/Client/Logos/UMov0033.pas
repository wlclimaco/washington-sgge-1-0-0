unit UMov0033;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, Grids, BrvDbGrids, BrvDbGrid, BrvRetCon, StdCtrls, BrvCustomEdit, BrvEditNum,
  BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, DBClient, DB;

type
  TMov0033 = class(TMov0000)
    BrvDbGrid1: TBrvDbGrid;
    PopupMenu: TPopupMenu;
    Detalhar: TMenuItem;
    LblQtReg: TLabel;
    procedure DetalharClick(Sender: TObject);
    procedure BrvDbGrid1DblClick(Sender: TObject);
  private
    procedure DetalharCTRC;
  public
    var gCpParams : TClientDataSet;
    procedure CarregarDados(pCpParams : TClientDataSet; pSpParams: TDataSource);
  end;

implementation

uses UDmTra;

{$R *.dfm}

procedure TMov0033.BrvDbGrid1DblClick(Sender: TObject);
begin
      inherited;
      DetalharCTRC;
end;

procedure TMov0033.DetalharClick(Sender: TObject);
begin
      inherited;
      DetalharCTRC;
end;

procedure TMov0033.DetalharCTRC;
begin
      inherited;
      DmTra.VisualizarConhecimento(gCpParams);
end;

procedure TMov0033.CarregarDados(pCpParams : TClientDataSet; pSpParams: TDataSource);
begin
      FormStyle             := fsNormal;
      Visible               := False;
      Position              := poMainFormCenter;
      BorderIcons           := [biSystemMenu];
      gCpParams             := pCpParams;
      gCpParams.ReadOnly    := True;
      pSpParams.DataSet     := pCpParams;
      BrvDbGrid1.DataSource := pSpParams;
      LblQtReg.Caption     := '  ' + FormatFloat('0', pCpParams.RecordCount) + ' Registro(s)';
      ShowModal;
end;

end.
