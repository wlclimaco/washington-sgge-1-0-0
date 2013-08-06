unit BrvCalculadoraForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Buttons, StdCtrls, ExtCtrls, Menus, BrvEditNum, BrvEditDate;

type
  TFrmCalculadora = class(TForm)
    PnlFundo: TPanel;
    SbtSete: TSpeedButton;
    SbtOito: TSpeedButton;
    SbtQuatro: TSpeedButton;
    SbtNove: TSpeedButton;
    SbtSeis: TSpeedButton;
    SbtCinco: TSpeedButton;
    SbtUm: TSpeedButton;
    SbtDois: TSpeedButton;
    SbtTres: TSpeedButton;
    SbtZero: TSpeedButton;
    SbtDecima: TSpeedButton;
    SbtDivide: TSpeedButton;
    SbtMultip: TSpeedButton;
    SbtSubtra: TSpeedButton;
    SbtAdicio: TSpeedButton;
    SbtC: TSpeedButton;
    SbtCE: TSpeedButton;
    SbtIgual: TSpeedButton;
    SbtPorcen: TSpeedButton;
    MemVisor: TMemo;
    EdtVrTotal: TBrvEditNum;
    SbtFita: TSpeedButton;
    BbtTransf: TSpeedButton;
    EdtVrMemori: TBrvEditNum;
    SbtMemMai: TSpeedButton;
    SbtMemMen: TSpeedButton;
    SbtMemRec: TSpeedButton;
    SbtMemCle: TSpeedButton;
    SbtBack: TSpeedButton;
    SbtMaiMen: TSpeedButton;
    PumCalcula: TPopupMenu;
    Configurar1: TMenuItem;
    Funo2: TMenuItem;
    Precisodecimal1: TMenuItem;
    MenSemDec: TMenuItem;
    MenUmaCas: TMenuItem;
    MenDuaCas: TMenuItem;
    MenTreCas: TMenuItem;
    MenQuaCas: TMenuItem;
    MenCinCas: TMenuItem;
    MenSnSepMil: TMenuItem;
    MenSubDat: TMenuItem;
    Sair1: TMenuItem;
    Exibir1: TMenuItem;
    MenExiFit: TMenuItem;
    MenRetVal: TMenuItem;
    procedure FormCreate(Sender: TObject);
    procedure SbtCClick(Sender: TObject);
    procedure SbtCEClick(Sender: TObject);
    procedure SbtUmClick(Sender: TObject);
    procedure SbtDivideClick(Sender: TObject);
    procedure FormKeyPress(Sender: TObject; var Key: Char);
    procedure SbtIgualClick(Sender: TObject);
    procedure SbtFitaClick(Sender: TObject);
    procedure FormKeyUp(Sender: TObject; var Key: Word;
      Shift: TShiftState);
    procedure BbtTransfClick(Sender: TObject);
    procedure SbtMemMaiClick(Sender: TObject);
    procedure SbtMemMenClick(Sender: TObject);
    procedure SbtMemRecClick(Sender: TObject);
    procedure SbtMemCleClick(Sender: TObject);
    procedure SbtBackClick(Sender: TObject);
    procedure SbtMaiMenClick(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure MenSemDecClick(Sender: TObject);
    procedure MenUmaCasClick(Sender: TObject);
    procedure MenDuaCasClick(Sender: TObject);
    procedure MenTreCasClick(Sender: TObject);
    procedure MenQuaCasClick(Sender: TObject);
    procedure MenCinCasClick(Sender: TObject);
    procedure MenSnSepMilClick(Sender: TObject);
    procedure MenSubDatClick(Sender: TObject);
    procedure Sair1Click(Sender: TObject);
    procedure MenExiFitClick(Sender: TObject);
    procedure MenRetValClick(Sender: TObject);
  private
    { Private declarations }
    VrTotal  : Real;
    NrLinha  : Integer;
    DsOpera  : Char;
    DsOpeAnt : Char;
    procedure DigitoPressionado(NrNumero : String);
    procedure MostrarOperador(DsOperad : String);
    procedure CasasDecimais(QtCasas : Byte);
  public
    { Public declarations }
  end;

var
  FrmCalculadora: TFrmCalculadora;

implementation

{$R *.DFM}

procedure TFrmCalculadora.FormCreate(Sender: TObject);
begin
      Width             := 212;
      SbtDecima.Caption := DecimalSeparator;
      SbtCClick(nil);
end;

procedure TFrmCalculadora.SbtCClick(Sender: TObject);
begin
      MenSubDat.Enabled    := True;
      VrTotal              := 0;
      NrLinha              := 0;
      DsOpera              := ' ';
      DsOpeAnt             := ' ';
      EdtVrTotal.BrAsFloat := 0;
      MemVisor.Lines.Clear;
      MemVisor.Lines.Add('');
end;

procedure TFrmCalculadora.SbtCEClick(Sender: TObject);
begin
      MenSubDat.Enabled               := True;
      MemVisor.Lines.Strings[NrLinha] := '';
      EdtVrTotal.BrAsFloat            := 0;
end;

procedure TFrmCalculadora.SbtUmClick(Sender: TObject);
begin
      DigitoPressionado(TSpeedButton(Sender).Caption);
end;

procedure TFrmCalculadora.DigitoPressionado(NrNumero : String);
begin
      MenSubDat.Enabled := False;
      
      if (NrLinha <> 0) and (MemVisor.Lines.Strings[NrLinha -1] <> '') and
         ((DsOpeAnt = '=') or (DsOpeAnt = '%')) then
      begin
            MemVisor.Lines.Add('');
            Inc(NrLinha);
            DsOpeAnt := ' ';
      end;

      if  (NrNumero = '.') or (NrNumero = ',') then
      begin
            if  (Pos('.', MemVisor.Lines.Strings[NrLinha]) = 0) and
                (Pos(',', MemVisor.Lines.Strings[NrLinha]) = 0) then
            begin
                  if MemVisor.Lines.Strings[NrLinha] = '' then
                  begin
                        MemVisor.Lines.Strings[NrLinha] := '0' +
                                        MemVisor.Lines.Strings[NrLinha] +
                                        DecimalSeparator;
                  end else
                  begin
                        MemVisor.Lines.Strings[NrLinha] :=
                                        MemVisor.Lines.Strings[NrLinha] +
                                        DecimalSeparator;
                  end;
            end;
      end else
      begin
            MemVisor.Lines.Strings[NrLinha] :=
                                     MemVisor.Lines.Strings[NrLinha] + NrNumero;
      end;

      EdtVrTotal.Text                 := MemVisor.Lines.Strings[NrLinha];
end;

procedure TFrmCalculadora.SbtDivideClick(Sender: TObject);
begin
      MostrarOperador(TSpeedButton(Sender).Caption);
end;

procedure TFrmCalculadora.SbtIgualClick(Sender: TObject);
begin
      MostrarOperador('=');
end;

procedure TFrmCalculadora.MostrarOperador(DsOperad : String);
var VrAtual : Real;
begin
      MenSubDat.Enabled := True;

      VrAtual := EdtVrTotal.BrAsFloat;

      if  DsOpeAnt = ' ' then
      begin
            EdtVrTotal.BrAsFloat := VrAtual;
            MemVisor.Lines.Strings[NrLinha] := EdtVrTotal.Text + DsOperad;

            VrTotal   := VrAtual;
            MemVisor.Lines.Add('');
            inc(NrLinha);

            DsOpeAnt  := DsOperad[1];
      end else
      begin
            if  DsOpeAnt = '=' then
            begin
                  if   DsOperad = '=' then
                  begin
                        MemVisor.Lines.Add(EdtVrTotal.Text);
                  end else begin
                        MemVisor.Lines.Add(EdtVrTotal.Text + DsOperad);
                  end;

                  MemVisor.Lines.Add('');
                  NrLinha := NrLinha + 2;

                  DsOpeAnt := DsOperad[1];
            end else
            begin
                  if MemVisor.Lines.Strings[NrLinha] <> '' then
                  begin
                        if  DsOperad = '%' then
                        begin
                              MemVisor.Lines.Strings[NrLinha] :=
                                          MemVisor.Lines.Strings[NrLinha] + '%';
                        end;

                        EdtVrTotal.BrAsFloat := VrAtual;
                        MemVisor.Lines.Strings[NrLinha] := EdtVrTotal.Text + '=';

                        case DsOpeAnt of
                             '/': begin
                                        if  VrAtual = 0 then
                                        begin
                                              MemVisor.Lines.Add('Divisão por' +
                                                                       ' Zero');
                                              Inc(NrLinha);
                                        end else
                                        begin
                                              if  DsOperad = '%' then
                                              begin
                                                    VrTotal :=
                                                    ((100 / VrAtual) * VrTotal);
                                              end else
                                              begin
                                                   VrTotal := VrTotal / VrAtual;
                                              end;
                                        end;
                                  end;
                             '+': begin
                                        if  DsOperad = '%' then
                                        begin
                                              VrTotal := VrTotal +
                                                     ((VrAtual /100) * VrTotal);
                                        end else begin
                                              VrTotal := VrTotal + VrAtual;
                                        end;
                                  end;
                             '-': begin
                                        if  DsOperad = '%' then
                                        begin
                                              VrTotal := VrTotal -
                                                     ((VrAtual /100) * VrTotal);
                                        end else
                                        begin
                                              VrTotal := VrTotal - VrAtual;
                                        end;
                                  end;
                             '*': begin
                                        if  DsOperad = '%' then
                                        begin
                                              VrTotal :=
                                                     ((VrAtual /100) * VrTotal);
                                        end else
                                        begin
                                              VrTotal := VrTotal * VrAtual;
                                        end;
                                  end;
                             else DsOpera := DsOperad[1];
                        end;

                        MemVisor.Lines.Add('------------------');


                        EdtVrTotal.BrAsFloat := VrTotal;
                        MemVisor.Lines.Add(EdtVrTotal.Text);

                        NrLinha  := NrLinha + 2;

                        if  (DsOperad <> '=') and (DsOperad <> '%')  then
                        begin
                              MemVisor.Lines.Strings[NrLinha] :=
                                     MemVisor.Lines.Strings[NrLinha] + DsOperad;
                        end;
                  end else
                  begin
                        EdtVrTotal.BrAsFloat := VrAtual;
                        MemVisor.Lines.Add(EdtVrTotal.Text + DsOperad);
                        NrLinha  := NrLinha + 1;
                  end;

                  MemVisor.Lines.Add('');
                  NrLinha  := NrLinha + 1;
                  DsOpeAnt := DsOperad[1];
            end;
      end;

      EdtVrTotal.BrAsFloat := VrTotal;
end;

procedure TFrmCalculadora.FormKeyPress(Sender: TObject; var Key: Char);
begin
      case key of
         '0'..'9', '.', ','      : DigitoPressionado(key);
         '+', '-', '*', '/', '%' : MostrarOperador(Key);
         #8                      : SbtBackClick(nil);
         #13                     : MostrarOperador('=');
         #27                     : Close;
      end;
end;

procedure TFrmCalculadora.FormKeyUp(Sender: TObject; var Key: Word;
  Shift: TShiftState);
begin
      if ssCtrl in Shift then
      begin
            case key of
                67 :  SbtMemCleClick(nil); // Ctrl C
                82 :  SbtMemRecClick(nil); // Ctrl R
               107 :  SbtMemMaiClick(nil); // Ctrl +
               109 :  SbtMemMenClick(nil); // Ctrl -
            end;
      end else
      begin
            case key of
               120 :  BbtTransfClick(nil); // F9
            end;
      end;
end;

procedure TFrmCalculadora.SbtFitaClick(Sender: TObject);
begin
      if  not MemVisor.Visible then
      begin
            Width             :=  380;
            MemVisor.Visible  :=  True;
      end else
      begin
            MemVisor.Visible  :=  False;
            Width             :=  212;
      end;

      MenExiFit.Checked  := not MenExiFit.Checked;
end;

procedure TFrmCalculadora.BbtTransfClick(Sender: TObject);
begin
      ModalResult := MrOk;
end;

procedure TFrmCalculadora.SbtMemMaiClick(Sender: TObject);
begin
      EdtVrMemori.BrAsFloat := EdtVrMemori.BrAsFloat + EdtVrTotal.BrAsFloat;
end;

procedure TFrmCalculadora.SbtMemMenClick(Sender: TObject);
begin
      EdtVrMemori.BrAsFloat := EdtVrMemori.BrAsFloat - EdtVrTotal.BrAsFloat;
end;

procedure TFrmCalculadora.SbtMemRecClick(Sender: TObject);
begin
      MenSubDat.Enabled  := False;
      EdtVrTotal.BrAsFloat := EdtVrMemori.BrAsFloat;

      MemVisor.Lines.Strings[NrLinha] := EdtVrMemori.Text;
end;

procedure TFrmCalculadora.SbtMemCleClick(Sender: TObject);
begin
      EdtVrMemori.BrAsFloat := 0;
end;

procedure TFrmCalculadora.SbtBackClick(Sender: TObject);
var DsString : String;
begin
      if  MemVisor.Lines.Strings[NrLinha] <> '' then
      begin
            DsString                        := EdtVrTotal.Text;
            Delete(DsString, Length(DsString), 1);
            EdtVrTotal.Text                 := DsString;
            MemVisor.Lines.Strings[NrLinha] := DsString;

            if  EdtVrTotal.BrAsFloat = 0 then
            begin
                  MenSubDat.Enabled := True;
            end;
      end;
end;

procedure TFrmCalculadora.SbtMaiMenClick(Sender: TObject);
var VrVisor : Real;
begin
      VrVisor                         := EdtVrTotal.BrAsFloat * (-1);
      EdtVrTotal.BrAsFloat              := VrVisor;

      if  DsOpeAnt = '=' then
      begin
            VrTotal := VrTotal * (-1);
      end;

      MemVisor.Lines.Strings[NrLinha] := EdtVrTotal.Text;
end;

procedure TFrmCalculadora.CasasDecimais(QtCasas : Byte);
var  VrAnteri : Real;
begin
      VrAnteri                   := EdtVrTotal.BrAsFloat;
      EdtVrTotal.BrCasasDecimais := QtCasas;
      EdtVrTotal.BrAsFloat       := VrAnteri;
end;

procedure TFrmCalculadora.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin
      Action := CaFree;
end;

procedure TFrmCalculadora.MenSemDecClick(Sender: TObject);
begin
      MenSemDec.Checked        := not MenSemDec.Checked;
      CasasDecimais(0);
end;

procedure TFrmCalculadora.MenUmaCasClick(Sender: TObject);
begin
      MenUmaCas.Checked        := not MenUmaCas.Checked;
      CasasDecimais(1);
end;

procedure TFrmCalculadora.MenDuaCasClick(Sender: TObject);
begin
      MenDuaCas.Checked        := not MenDuaCas.Checked;
      CasasDecimais(2);
end;

procedure TFrmCalculadora.MenTreCasClick(Sender: TObject);
begin
      MenTreCas.Checked        := not MenTreCas.Checked;
      CasasDecimais(3);
end;

procedure TFrmCalculadora.MenQuaCasClick(Sender: TObject);
begin
      MenQuaCas.Checked        := not MenQuaCas.Checked;
      CasasDecimais(4);
end;

procedure TFrmCalculadora.MenCinCasClick(Sender: TObject);
begin
      MenCinCas.Checked        := not MenCinCas.Checked;
      CasasDecimais(5);
end;

procedure TFrmCalculadora.MenSnSepMilClick(Sender: TObject);
var  VrAnteri : Real;
begin
      MenSnSepMil.Checked        := not MenSnSepMil.Checked;
      VrAnteri                   := EdtVrTotal.BrAsFloat;
      EdtVrTotal.BrSepararMilhar := MenSnSepMil.Checked;
      EdtVrTotal.BrAsFloat       := VrAnteri;
end;

procedure TFrmCalculadora.MenSubDatClick(Sender: TObject);
var FrmData     : TForm;
    PnlFundo    : TPanel;
    LblDtInicio : TLabel;
    LblDtFinal  : TLabel;
    EdtDtInicio : TBrvEditDate;
    EdtDtFinal  : TBrvEditDate;
    BbtOk       : TBitBtn;
    BbtCancel   : TBitBtn;
    VrData      : Real;
begin
      FrmData                 := TForm.CreateNew(Self);
      FrmData.Position        := poScreenCenter;
      FrmData.Width           := 190;
      FrmData.Height          := 130;
      FrmData.BorderStyle     := bsSingle;
      FrmData.BorderIcons     := [biSystemMenu];

      PnlFundo                := TPanel.Create(Self);
      PnlFundo.Parent         := FrmData;
      PnlFundo.Align          := alClient;
      PnlFundo.BorderStyle    := bsSingle;
      PnlFundo.Caption        := '';

      LblDtInicio             := TLabel.Create(Self);
      LblDtInicio.Parent      := PnlFundo;
      LblDtInicio.Caption     := 'Data inicial';
      LblDtInicio.Font.Style  := [fsBold];
      LblDtInicio.Top         := 14;
      LblDtInicio.Left        := 8;

      LblDtFinal              := TLabel.Create(Self);
      LblDtFinal.Parent       := PnlFundo;
      LblDtFinal.Caption      := 'Data final';
      LblDtFinal.Font.Style   := [fsBold];
      LblDtFinal.Top          := 38;
      LblDtFinal.Left         := 8;

      EdtDtInicio             := TBrvEditDate.Create(Self);
      EdtDtInicio.Parent      := PnlFundo;
      EdtDtInicio.Top         := 6;
      EdtDtInicio.Left        := 80;

      EdtDtFinal              := TBrvEditDate.Create(Self);
      EdtDtFinal.Parent       := PnlFundo;
      EdtDtFinal.Top          := 30;
      EdtDtFinal.Left         := 80;

      BbtOk                   := TBitBtn.Create(Self);
      BbtOk.Parent            := PnlFundo;
      BbtOk.Top               := 65;
      BbtOk.Left              := 10;
      BbtOk.Kind              := bkOK;
      BbtOk.Caption           := '&Ok';

      BbtCancel               := TBitBtn.Create(Self);
      BbtCancel.Parent        := PnlFundo;
      BbtCancel.Top           := 65;
      BbtCancel.Left          := 90;
      BbtCancel.Kind          := bkCancel;
      BbtCancel.Caption       := '&Cancelar';

      VrData := -2;

      repeat
             if  FrmData.ShowModal = MrOk then
             begin
                   if  not EdtDtInicio.BrDataValida then
                   begin
                         MessageDlg('Data inicial inválida', MtError, [MbOk], 0);
                   end else
                   begin
                         if  not EdtDtFinal.BrDataValida then
                         begin
                               MessageDlg('Data final inválida', MtError,
                                                                     [MbOk], 0);
                         end else
                         begin
                               if  EdtDtInicio.BrAsDate > EdtDtFinal.BrAsDate then
                               begin
                                     MessageDlg('Data inicial não pode ser ' +
                                         'maior que final', MtError, [MbOk], 0);
                               end else
                               begin
                                     VrData := EdtDtFinal.BrAsDate - EdtDtInicio.BrAsDate;
                               end;
                         end;
                   end;
             end else
             begin
                   VrData := -1;
             end;
      until VrData <> -2;

      if  VrData <> -1 then
      begin
            EdtVrTotal.BrAsFloat := VrData;
            MemVisor.Lines.Strings[NrLinha] := EdtVrTotal.Text;
      end;

      MenSubDat.Enabled := False;
end;

procedure TFrmCalculadora.Sair1Click(Sender: TObject);
begin
      Close;
end;

procedure TFrmCalculadora.MenExiFitClick(Sender: TObject);
begin
      SbtFitaClick(nil);
end;

procedure TFrmCalculadora.MenRetValClick(Sender: TObject);
begin
      BbtTransfClick(nil);
end;

end.
