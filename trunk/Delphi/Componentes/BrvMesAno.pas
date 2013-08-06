unit BrvMesAno;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, BrvCustomMaskEdit,
  StdCtrls, Mask, Buttons, ExtCtrls, Spin, BrvBitBtn, BrvImgBot, BrvRecordacao,
  BrvDicionario;

type
  TTpButton = (TVMAData);
  TBrvMesAno = class(TBrvCustomMaskEdit)
  private
    { Private declarations }
    FDtValida       : Boolean;
    FDsVazia        : String;
    FDtVazia        : Boolean;
    FDtMes          : Byte;
    FDtAno          : Integer;
    FDtDiaFim       : Byte;
    FTpButton       : TTpButton;
    FButton         : TSpeedButton;
    FAlignment      : TAlignment;
    FSnRecord       : Boolean;
    gBrvDic         : TBrvDicionario;
    gSnRecord       : Boolean;

    procedure SetMes(InMes : Byte);
    procedure SetAno(InAno : Integer);
    procedure EncontrarDiaFinal;
    procedure CriarBotao;
    procedure SetTipoBotao(InTpBotao : TTpButton);
    procedure WMSize(var Message: TWMSize); message WM_SIZE;
    procedure SetEditRect;
    procedure CreateParams(var DsParams: TCreateParams); override;
    procedure SetAlignment(VrAlinha: TAlignment);
  protected
    { Protected declarations }
    procedure  VerificarData;
    procedure  DoExit; override;
    procedure  Change; override;
    procedure  ChamarCalendario;
    procedure  CreateWnd; override;
    procedure  ClickBotao (Sender: TObject); virtual;
    procedure  TeclaPressionada(key : Word);
    procedure  KeyUp(var Key: Word; Shift: TShiftState); override;
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
    property    Button : TSpeedButton read FButton;
  published
    { Published declarations }
    property  DataValida     : Boolean        read FDtValida  write FDtValida;
    property  DataVazia      : Boolean        read FDtVazia   write FDtVazia;
    property  AsMes          : Byte           read FDtMes     write SetMes;
    property  AsAno          : Integer        read FDtAno     write SetAno;
    property  AsDiaFinal     : Byte           read FDtDiaFim  write FDtDiaFim;
    property  FunctionButton : TTpButton      read FTpButton  write SetTipoBotao;
    property  Alignment      : TAlignment     read FAlignment write SetAlignment;
    property  BrDicionario   : TBrvDicionario read gBrvDic    write gBrvDic;
    property  BrRecordar     : Boolean        read FSnRecord  write FSnRecord;
  end;

const TcConsul = 120; // Tecla de consulta F9

procedure Register;

implementation

constructor TBrvMesAno.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
      EditMask  := '99/9999;1; ';
      Width     := 70;
      FDtValida := False;
      FDtVazia  := True;
      FDsVazia  := Text;

      CriarBotao;
end;

procedure  TBrvMesAno.DoExit;
begin
      VerificarData;
      inherited DoExit;
end;

procedure  TBrvMesAno.Change;
begin
      if  pos(' ', Text) = 0 then
      begin
            VerificarData;
      end;

      inherited Change;
end;

procedure TBrvMesAno.SetMes(InMes: Byte);
begin
      FDtMes := InMes;

      if  (FDtMes <> 0) and (FDtAno <> 0) then
      begin
            Text := FormatFloat('00', FDtMes) + FormatSettings.DateSeparator +
                    FormatFloat('0000', FDtAno);
      end;
end;

procedure TBrvMesAno.SetAno(InAno: Integer);
begin
      FDtAno := InAno;

      if  (FDtMes <> 0) and (FDtAno <> 0) then
      begin
          Text   := FormatFloat('00', FDtMes) + FormatSettings.DateSeparator +
                    FormatFloat('0000', FDtAno);
      end;
end;

destructor TBrvMesAno.Destroy;
var lBrvRecord : TBrvRecordacao;
begin
      if FSnRecord then
      begin
            try
                lBrvRecord := TBrvRecordacao.Create(Self);
                lBrvRecord.BrDicionario := gBrvDic;
                lBrvRecord.GravarRecordacao(Text, Owner.Name, Name);
            finally
                FreeAndNil(lBrvRecord);
            end;
      end;

      inherited  destroy;
end;

procedure TBrvMesAno.VerificarData;
var  DtAuxili  :  TDate;
     DtDia     :  Word;
     DtMes     :  Word;
     DtAno     :  Word;
begin
      FDtValida := True;
      FDtVazia  := False;
      FDtAno    := 0;
      FDtMes    := 0;
      FDtDiaFim := 0;

      if  Text = FDsVazia then
      begin
            FDtVazia  := True;
            FDtValida := False;
      end else
      begin
            try
                DtAuxili  := StrToDate('01' + FormatSettings.DateSeparator + Text);
                DecodeDate(DtAuxili, DtAno, DtMes, DtDia);
                FDtAno    :=  DtAno;
                FDtMes    :=  DtMes;

                EncontrarDiaFinal;
            except
                on  EConvertError do begin
                    FDtValida := False;
                end;
            end;
      end;
end;

procedure TBrvMesAno.EncontrarDiaFinal;
var DtAuxili : TDate;
    DtMes    : Word;
    DtAno    : Word;
    DtDia    : Word;
begin
      if FDtMes = 12 then
      begin
            DtMes := 1;
            DtAno := FDtAno + 1;
      end else
      begin
            DtMes := FDtMes + 1;
            DtAno := FDtAno;
      end;

      DtAuxili := EncodeDate(DtAno, DtMes, 1);
      DtAuxili := DtAuxili - 1;

      DecodeDate(DtAuxili, DtAno, DtMes, DtDia);
      FDtDiaFim := DtDia;
end;

procedure  TBrvMesAno.ChamarCalendario;
var FrmData     : TForm;
    PnlFundo    : TPanel;
    LblDtMes    : TLabel;
    LblDtAno    : TLabel;
    CbxDtMes    : TComboBox;
    SpeDtAno    : TSpinEdit;
    BbtOk       : TBrvBitBtn;
    BbtCancel   : TBrvBitBtn;

    DtDia       : Word;
    DtMes       : Word;
    DtAno       : Word;
begin
    if  not ReadOnly then
    begin
          FrmData                 := TForm.CreateNew(Self);
          FrmData.Position        := poScreenCenter;
          FrmData.Width           := 290;
          FrmData.Height          := 105;
          FrmData.BorderStyle     := bsSingle;
          FrmData.BorderIcons     := [biSystemMenu];
          FrmData.Caption         := 'Escolha Mês\Ano';

          PnlFundo                := TPanel.Create(Self);
          PnlFundo.Parent         := FrmData;
          PnlFundo.Align          := alClient;
          PnlFundo.BorderStyle    := bsSingle;
          PnlFundo.Caption        := '';

          LblDtMes                := TLabel.Create(Self);
          LblDtMes.Parent         := PnlFundo;
          LblDtMes.Caption        := 'Mês';
          LblDtMes.Font.Style     := [fsBold];
          LblDtMes.Top            := 8;
          LblDtMes.Left           := 8;

          LblDtAno                := TLabel.Create(Self);
          LblDtAno.Parent         := PnlFundo;
          LblDtAno.Caption        := 'Ano';
          LblDtAno.Font.Style     := [fsBold];
          LblDtAno.Top            := 8;
          LblDtAno.Left           := 192;

          CbxDtMes                := TComboBox.Create(Self);
          CbxDtMes.Parent         := PnlFundo;
          CbxDtMes.Top            := 5;
          CbxDtMes.Left           := 36;
          CbxDtMes.Style          := csDropDownList;
          CbxDtMes.Items.Add('Janeiro');
          CbxDtMes.Items.Add('Fevereiro');
          CbxDtMes.Items.Add('Março');
          CbxDtMes.Items.Add('Abril');
          CbxDtMes.Items.Add('Maio');
          CbxDtMes.Items.Add('Junho');
          CbxDtMes.Items.Add('Julho');
          CbxDtMes.Items.Add('Agosto');
          CbxDtMes.Items.Add('Setembro');
          CbxDtMes.Items.Add('Outubro');
          CbxDtMes.Items.Add('Novembro');
          CbxDtMes.Items.Add('Dezembro');

          SpeDtAno              := TSpinEdit.Create(Self);
          SpeDtAno.Parent       := PnlFundo;
          SpeDtAno.Top          := 5;
          SpeDtAno.Left         := 224;
          SpeDtAno.MaxLength    := 4;
          SpeDtAno.MaxValue     := 2100;
          SpeDtAno.MinValue     := 1900;
          SpeDtAno.Width        := 50;

          BbtOk                 := TBrvBitBtn.Create(Self);
          BbtOk.Parent          := PnlFundo;
          BbtOk.Top             := 40;
          BbtOk.Left            := 64;
          BbtOk.BrTipoBotao     := BrBtnOk;
          BbtOk.Caption         := 'Confirmar';
          BbtOk.ModalResult     := mrOk;

          BbtCancel             := TBrvBitBtn.Create(Self);
          BbtCancel.Parent      := PnlFundo;
          BbtCancel.Top         := 40;
          BbtCancel.Left        := 160;
          BbtCancel.BrTipoBotao := BrBtnCancel;
          BbtCancel.Caption     := 'Cancelar';
          BbtCancel.ModalResult := mrCancel;

          if  (FDtAno > 0) and (FDtMes > 0) then
          begin
                SpeDtAno.Value          := FDtAno;
                CbxDtMes.ItemIndex      := FDtMes -1;
          end else
          begin
                DecodeDate(Date, DtAno, DtMes, DtDia);
                SpeDtAno.Value          := DtAno;
                CbxDtMes.ItemIndex      := DtMes -1;
          end;

          if  FrmData.ShowModal = MrOk then
          begin
                SetMes(CbxDtMes.ItemIndex + 1);
                SetAno(SpeDtAno.Value);
          end;

          FrmData.Destroy;
      end;
end;

procedure TBrvMesAno.CreateWnd;
begin
  inherited CreateWnd;
  SetEditRect;
end;

procedure TBrvMesAno.ClickBotao (Sender: TObject);
begin
      TeclaPressionada(TcConsul);
end;

procedure TBrvMesAno.TeclaPressionada(key : Word);
begin
      if  key = TcConsul then
      begin
            case FTpButton of
                 TVMAData    : ChamarCalendario;
            end;
      end;
end;

procedure TBrvMesAno.KeyUp(var Key: Word; Shift: TShiftState);
begin
      inherited KeyUp(key, Shift);
      TeclaPressionada(Key);
end;

procedure  TBrvMesAno.CriarBotao;
begin
      FButton          := TSpeedButton.Create (Self);
      FButton.Width    := Height+2;
      FButton.Height   := Height-1;
      FButton.Visible  := True;
      FButton.Parent   := Self;
      FButton.OnClick  := ClickBotao;
      FButton.Cursor   := crArrow;
      FButton.ShowHint := True;

      SetTipoBotao(FTpButton);
end;

procedure TBrvMesAno.SetTipoBotao(InTpBotao : TTpButton);
begin
      FTpButton := InTpBotao;

      if FTpButton = TVMAData then
      begin
            FButton.Glyph.LoadFromResourceName(HInstance, 'BtnCalend');
            FButton.Hint  := 'Calendário';
      end;
end;

procedure TBrvMesAno.WMSize(var Message: TWMSize);
var lBrvRecord : TBrvRecordacao;
    lDsRecord  : String;
begin
      inherited;

      if (FButton <> nil) then
      begin
            if NewStyleControls and Ctl3D then
            begin
                  FButton.SetBounds(Width - FButton.Width,  // Left
                                    0,                      // Top
                                    FButton.Width,          // Width
                                    Height);                // Height
                  FButton.Left   := Width - FButton.Width-3;
                  FButton.Top    := FButton.Top - 1;
                  FButton.Height := FButton.Height - 2;
            end else
            begin
                  FButton.SetBounds(Width - FButton.Width-5, // Left
                                    0,                       // Top
                                    FButton.Width,           // Width
                                    Height);                 // Height

                  FButton.Left := (Width - FButton.Width);
            end;
      end;

      if (FButton <> nil) then
      begin
            SetEditRect;
      end;

      if FSnRecord and (not gSnRecord) then
      begin
            try
                lBrvRecord := TBrvRecordacao.Create(Self);
                lDsRecord  := lBrvRecord.CarregarRecordacao(Owner.Name, Name);

                if lDsRecord <> '' then
                begin
                      Text := lDsRecord;
                end;
            finally
                FreeAndNil(lBrvRecord);
            end;

            gSnRecord := True;
      end;
end;

procedure TBrvMesAno.SetEditRect;
var Loc: TRect;
begin
      {=-=-=-=-= demarca o campo de edição do edit =-=-=-=-==--=-=-=-=-=-=}
      SendMessage(Handle, EM_GETRECT, 0, LongInt(@Loc));
      Loc.Bottom := ClientHeight + 1;  {+1 is workaround for windows paint bug}
      Loc.Top    := 0;
      Loc.Left   := 2;
      Loc.Right  := ClientWidth - FButton.Width - 1;

      SendMessage(Handle, EM_SETRECTNP, 0, LongInt(@Loc));
      SendMessage(Handle, EM_GETRECT, 0, LongInt(@Loc));  {debug}
end;

procedure TBrvMesAno.CreateParams(var DsParams: TCreateParams);
const
  Alignments: array[TAlignment] of Longint = (ES_LEFT, ES_RIGHT, ES_CENTER);
begin
      inherited CreateParams(DsParams);
      CreateSubClass(DsParams, 'DBEDIT');
      DsParams.Style := DsParams.Style and
                                       not (ES_AUTOVSCROLL or ES_AUTOHSCROLL) or
                                        ES_MULTILINE or Alignments[FAlignment] ;
end;

procedure TBrvMesAno.SetAlignment(VrAlinha: TAlignment);
begin
      if FAlignment <> VrAlinha then
      begin
            FAlignment := VrAlinha;
            RecreateWnd;
      end;
end;

procedure Register;
begin
      RegisterComponents('Bravo Standard', [TBrvMesAno]);
end;

end.
