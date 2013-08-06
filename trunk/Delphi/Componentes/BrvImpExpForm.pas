unit BrvImpExpForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  ExtCtrls, StdCtrls, Buttons, CheckLst, Menus, BrvBitBtn;

type
  TFrmImpExp = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    BitBtn1: TBrvBitBtn;
    BitBtn2: TBrvBitBtn;
    LcbColunas: TCheckListBox;
    PopupMenu1: TPopupMenu;
    Marcartodos1: TMenuItem;
    Desmarcartodos1: TMenuItem;
    procedure BitBtn1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure FormDestroy(Sender: TObject);
    procedure Marcartodos1Click(Sender: TObject);
    procedure Desmarcartodos1Click(Sender: TObject);
  private
    { Private declarations }
    procedure ExcluirCamposChave;
  public
    { Public declarations }
    StlColImp : TStrings;
    StlChave  : Tstrings;
  end;

var
  FrmImpExp: TFrmImpExp;

implementation

{$R *.DFM}

procedure TFrmImpExp.FormCreate(Sender: TObject);
begin
      StlColImp := TStringList.Create;
      StlChave  := TStringList.Create;
end;

procedure TFrmImpExp.BitBtn1Click(Sender: TObject);
var NrColuna : Integer;
begin
      StlColImp.Clear;

      for NrColuna := 0 to LcbColunas.Items.Count -1 do
      begin
            if LcbColunas.Checked[NrColuna] then
            begin
                  StlColImp.Add(LcbColunas.Items.Strings[NrColuna]);
            end;
      end;

      if  StlColImp.Text = '' then
      begin
            raise Exception.Create('Nenhuma coluna foi selecionada');
      end;

      ExcluirCamposChave;

      StlColImp.Text := StlChave.Text + StlColImp.Text;

      ModalResult := MrOk;
end;

procedure TFrmImpExp.ExcluirCamposChave;
var NrCampo : Integer;
    NrChave : Integer;
    SnAchou : Boolean;
begin
      for NrChave := 0 to StlChave.Count -1 do
      begin
            NrCampo := 0;
            SnAchou := False;

            while (NrCampo < StlColImp.Count) and (not SnAchou) do
            begin
                  if  StlColImp.Strings[NrCampo] = StlChave.Strings[NrChave] then
                  begin
                        StlColImp.Delete(NrCampo);
                        SnAchou := True;
                  end;

                  Inc(NrCampo);
            end;
      end;
end;

procedure TFrmImpExp.FormDestroy(Sender: TObject);
begin
      StlColImp.Destroy;
      StlChave.Destroy;
end;

procedure TFrmImpExp.Marcartodos1Click(Sender: TObject);
var NrColuna : Integer;
begin
      for NrColuna := 0 to LcbColunas.Items.Count -1 do
      begin
            LcbColunas.Checked[NrColuna] := True;
      end;
end;

procedure TFrmImpExp.Desmarcartodos1Click(Sender: TObject);
var NrColuna : Integer;
begin
      for NrColuna := 0 to LcbColunas.Items.Count -1 do
      begin
            LcbColunas.Checked[NrColuna] := False;
      end;
end;

end.
