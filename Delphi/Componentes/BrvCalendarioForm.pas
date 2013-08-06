unit BrvCalendarioForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, ExtCtrls, ComCtrls, BrvEditNum, Menus, BrvBitBtn, BrvImgBot;

type
  TFrmCalendar = class(TForm)
    MthCalend: TMonthCalendar;
    PnlRodape: TPanel;
    BbtDtRetorn: TBitBtn;
    PopFuncao: TPopupMenu;
    Adicionardias: TMenuItem;
    Subtrairdias: TMenuItem;
    Dthoje: TMenuItem;
    procedure BbtDtRetornClick(Sender: TObject);
    procedure FormKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
    procedure FormKeyPress(Sender: TObject; var Key: Char);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure AdicionardiasClick(Sender: TObject);
    procedure SubtrairdiasClick(Sender: TObject);
    procedure DthojeClick(Sender: TObject);
  private
    { Private declarations }
    FrmDias       : TForm;
    function Dias : Integer;
    procedure EditKeyPress(Sender: TObject; var Key: Char);
  public
    { Public declarations }
  end;

var
  FrmCalendar: TFrmCalendar;

implementation

{$R *.DFM}

procedure TFrmCalendar.BbtDtRetornClick(Sender: TObject);
begin
      ModalResult := MrOk;
end;

procedure TFrmCalendar.FormKeyPress(Sender: TObject; var Key: Char);
begin
      case key of
           #27 : Close;
      end;
end;

procedure TFrmCalendar.FormKeyUp(Sender: TObject; var Key: Word;
  Shift: TShiftState);
begin
      case key of
//                  27 :  Close;
           120 : BbtDtRetornClick(nil); // F9
           37  : MthCalend.Date := MthCalend.Date - 1;
           39  : MthCalend.Date := MthCalend.Date + 1;
           38  : MthCalend.Date := IncMonth(MthCalend.Date, 1);
           40  : MthCalend.Date := IncMonth(MthCalend.Date, -1);
      end;
end;

function TFrmCalendar.Dias : Integer;
var PnlFundo    : TPanel;
    LblDsDias   : TLabel;
    EdtQtDias   : TBrvEditNum;
    BbtOk       : TBrvBitBtn;
    BbtCancel   : TBrvBitBtn;
begin
      FrmDias                 := TForm.CreateNew(Self);
      FrmDias.Position        := poScreenCenter;
      FrmDias.Width           := 190;
      FrmDias.Height          := 130;
      FrmDias.BorderStyle     := bsSingle;
      FrmDias.BorderIcons     := [biSystemMenu];

      PnlFundo                := TPanel.Create(Self);
      PnlFundo.Parent         := FrmDias;
      PnlFundo.Align          := alClient;
      PnlFundo.BorderStyle    := bsSingle;
      PnlFundo.Caption        := '';

      LblDsDias               := TLabel.Create(Self);
      LblDsDias.Parent        := PnlFundo;
      LblDsDias.Caption       := 'Qtde de dias';
      LblDsDias.Font.Style    := [fsBold];
      LblDsDias.Top           := 14;
      LblDsDias.Left          := 8;

      EdtQtDias                 := TBrvEditNum.Create(Self);
      EdtQtDias.Parent          := PnlFundo;
      EdtQtDias.Top             := 6;
      EdtQtDias.Left            := 85;
      EdtQtDias.BrVisibleButton := False;
      EdtQtDias.BrCasasDecimais := 0;
      EdtQtDias.Width           := 75;
      EdtQtDias.OnKeyPress      := EditKeyPress;

      BbtOk                   := TBrvBitBtn.Create(Self);
      BbtOk.Parent            := PnlFundo;
      BbtOk.Top               := 65;
      BbtOk.Left              := 10;
      BbtOk.Kind              := bkOK;
      BbtOk.Caption           := '&Ok';
      BbtOk.BrTipoBotao       := BrBtnOk;

      BbtCancel               := TBrvBitBtn.Create(Self);
      BbtCancel.Parent        := PnlFundo;
      BbtCancel.Top           := 65;
      BbtCancel.Left          := 90;
      BbtCancel.Kind          := bkCancel;
      BbtCancel.Caption       := '&Cancelar';
      BbtOk.BrTipoBotao       := BrBtnCancel;

      if  FrmDias.ShowModal = MrOk then
      begin
            Result := EdtQtDias.BrAsInteger;
      end else
      begin
            Result := 0;
      end;
end;

procedure TFrmCalendar.EditKeyPress(Sender: TObject; var Key: Char);
begin
      case key of
           #13 : FrmDias.ModalResult := MrOk;
           #27 : FrmDias.Close;
      end;
end;

procedure TFrmCalendar.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin
      Action := CaFree;
end;

procedure TFrmCalendar.AdicionardiasClick(Sender: TObject);
begin
      MthCalend.Date := MthCalend.Date + Dias;
end;

procedure TFrmCalendar.SubtrairdiasClick(Sender: TObject);
begin
      MthCalend.Date := MthCalend.Date - Dias;
end;

procedure TFrmCalendar.DthojeClick(Sender: TObject);
begin
      MthCalend.Date := Date;
end;

end.
