unit BrvConColForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, CheckLst, ExtCtrls, Buttons, Menus, BrvBitBtn;

type
  TFrmConCol = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    CbxColuna: TCheckListBox;
    BitBtn1: TBrvBitBtn;
    BitBtn2: TBrvBitBtn;
    PopupMenu: TPopupMenu;
    Marcartodos1: TMenuItem;
    Desmarcartodos1: TMenuItem;
    procedure BitBtn1Click(Sender: TObject);
    procedure FormKeyPress(Sender: TObject; var Key: Char);
    procedure Marcartodos1Click(Sender: TObject);
    procedure Desmarcartodos1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FrmConCol: TFrmConCol;

implementation

{$R *.DFM}

procedure TFrmConCol.BitBtn1Click(Sender: TObject);
begin
      ModalResult := MrOk;
end;

procedure TFrmConCol.FormKeyPress(Sender: TObject; var Key: Char);
begin
      if Key = #27 then
      begin
            Close;
      end;
end;

procedure TFrmConCol.Marcartodos1Click(Sender: TObject);
var NrColuna : Integer;
begin
      for NrColuna := 0 to CbxColuna.Items.Count -1 do
      begin
            CbxColuna.Checked[NrColuna] := True;
      end;
end;

procedure TFrmConCol.Desmarcartodos1Click(Sender: TObject);
var NrColuna : Integer;
begin
      for NrColuna := 0 to CbxColuna.Items.Count -1 do
      begin
            CbxColuna.Checked[NrColuna] := False;
      end;
end;

end.
