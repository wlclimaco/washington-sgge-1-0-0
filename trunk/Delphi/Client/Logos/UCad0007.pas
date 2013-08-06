unit UCad0007;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0003, BrvDigito, BrvImport, BrvExport, BrvFiltrar, BrvLocalizar, Menus,
  ImgList, BrvOrdenar, BrvRelatorio, BrvString, DB, DBClient, BrvClientDataSet, Grids,
  BrvDbGrids, BrvDbGrid, ComCtrls, StdCtrls, ToolWin, ExtCtrls, Buttons, BrvSpeedButton,
  BrvDbNavCop, BrvListParam;

type
  TCad0007 = class(TCad0003)
    procedure FormCreate(Sender: TObject);
  private
    procedure InsereOpcaoContasSubCadastro;
    procedure SubFormularioContasClick(Sender: TObject);
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Cad0007: TCad0007;

implementation

uses UCad0008, UDmCtb;

{$R *.dfm}

procedure TCad0007.FormCreate(Sender: TObject);
begin
      inherited;
      MontarFormularioCadastro(26, '');

      InsereOpcaoContasSubCadastro;
end;

procedure TCad0007.InsereOpcaoContasSubCadastro;
var  lMnuItem : TMenuItem;
begin
      lMnuItem         := TMenuItem.Create(Self);
      lMnuItem.Caption := 'Contas';
      lMnuItem.Name    := 'SubFor_Contas';
      lMnuItem.Tag     := 999999;
      lMnuItem.OnClick := SubFormularioContasClick;
      PopSubFor.Items.Add(lMnuItem);

      SbtSubCad.Visible := True;
end;

procedure TCad0007.SubFormularioContasClick(Sender: TObject);
var lDsPlano : WideString;
begin
      try
          Cad0008 := TCad0008.Create(Self);
          Cad0008.FormStyle := fsNormal;
          Cad0008.Visible   := False;
          Cad0008.CbxNumAut.Checked := QCadastro.FieldByName('SnGerNum').AsString = 'S';
          Cad0008.gNrPlano  := QCadastro.FieldByName('NrPlano').AsInteger;
          Cad0008.gEditMask := QCadastro.FieldByName('DsMasCla').AsString;
          Cad0008.CarregarPlanoDeContas;
          Cad0008.ShowModal;
      finally
          FreeAndNil(Cad0008);
      end;
end;

initialization
      RegisterClass(TCad0007);

finalization
      UnRegisterClass(TCad0007);

end.
