unit BrvExportForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, CheckLst, Buttons, ExtCtrls, Menus, BrvBitBtn;

type
  TFrmExpRes = class(TForm)
    PopupMenu1: TPopupMenu;
    Marcartodos1: TMenuItem;
    Desmarcartodos1: TMenuItem;
    Panel1: TPanel;
    Panel2: TPanel;
    BitBtn1: TBrvBitBtn;
    BitBtn2: TBrvBitBtn;
    LcbColunas: TCheckListBox;
    Panel3: TPanel;
    Panel4: TPanel;
    RgpTpExport: TRadioGroup;
    Memo1: TMemo;
    PnlSepara: TPanel;
    Label1: TLabel;
    EdtTpCarSep: TEdit;
    procedure Marcartodos1Click(Sender: TObject);
    procedure Desmarcartodos1Click(Sender: TObject);
    procedure BitBtn1Click(Sender: TObject);
    procedure RgpTpExportClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
  private
    procedure MarcarTodos(SnMarcar: Boolean);
    { Private declarations }
  public
    { Public declarations }
    StlColImp : TStrings;
  end;

var
  FrmExpRes: TFrmExpRes;

implementation

{$R *.DFM}

procedure TFrmExpRes.Marcartodos1Click(Sender: TObject);
begin
      MarcarTodos(True);
end;

procedure TFrmExpRes.Desmarcartodos1Click(Sender: TObject);
begin
      MarcarTodos(False);
end;

procedure TFrmExpRes.MarcarTodos(SnMarcar : Boolean);
var NrColuna : Integer;
begin
      for NrColuna := 0 to LcbColunas.Items.Count -1 do
      begin
            LcbColunas.Checked[NrColuna] := SnMarcar;
      end;
end;

procedure TFrmExpRes.RgpTpExportClick(Sender: TObject);
begin
      PnlSepara.Visible := RgpTpExport.ItemIndex = 1;

      if PnlSepara.Visible then
      begin
            EdtTpCarSep.SetFocus;
      end;
end;

procedure TFrmExpRes.BitBtn1Click(Sender: TObject);
var NrColuna : Integer;
begin
      if PnlSepara.Visible then
      begin
            if EdtTpCarSep.Text = '' then
            begin
                  raise Exception.Create('Informe o caracter separador de coluna.');
            end;
      end;

      // Carregando Colunas que serão exportadas;

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
            raise Exception.Create('Nenhuma coluna foi selecionada para exportação');
      end;

      ModalResult := MrOk;
end;

procedure TFrmExpRes.FormCreate(Sender: TObject);
begin
      StlColImp := TStringList.Create;
end;

end.
