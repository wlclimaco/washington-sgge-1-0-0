unit BrvEditDate;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, BrvCustomMaskEdit,
  StdCtrls, Mask, Buttons, BrvDicionario, BrvCalendario, BrvRecordacao, BrvCustomEdit;

type
  TTpButton = (TVdData, TVdDataHora);
  TBrvEditDate = class(TBrvCustomMaskEdit)
  private
    { Private declarations }

    FDtValida       : Boolean;
    FDsVazia        : String;
    FDtVazia        : Boolean;
    FDtData         : TDate;
    FDtDataTime     : TDateTime;
    FDtSQL          : String;
    FButton         : TSpeedButton;
    FTpButton       : TTpButton;
    FAlignment      : TAlignment;
    FDsCalend       : TBrvCalendario;
    gBrvDic         : TBrvDicionario;
    FSnRecord       : Boolean;
    gSnRecord       : Boolean;

    procedure SetData(InData : TDate);
    procedure SetDataTime(InData: TDateTime);
    procedure CriarBotao;
    procedure SetTipoBotao(InTpBotao : TTpButton);
    procedure WMSize(var Message: TWMSize); message WM_SIZE;

    procedure SetEditRect;
    procedure CreateParams(var DsParams: TCreateParams); override;
    procedure SetAlignment(VrAlinha: TAlignment);
  protected
    { Protected declarations }
    procedure  VerificarData(TpVerifi : String);
    procedure  DoExit; override;
    procedure  Change; override;
    procedure  ChamarCalendario;
    procedure  CreateWnd; override;
    procedure  ClickBotao (Sender: TObject); virtual;
    procedure  DblClickEdit(Sender: TObject); virtual;
    procedure  TeclaPressionada(key : Word);
    procedure  KeyUp(var Key: Word; Shift: TShiftState); override;

  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
    property    Button : TSpeedButton read FButton;
  published
    { Published declarations }
    Property  BrDataValida      : Boolean         read  FDtValida   write FDtValida;
    Property  BrDataVazia       : Boolean         read  FDtVazia    write FDtVazia;
    Property  BrAsDataSQL       : String          read  FDtSQL      write FDtSQL;
    Property  BrAsDate          : TDate           read  FDtData     write SetData;
    Property  BrAsDateTime      : TDateTime       read  FDtDataTime write SetDataTime;
    property  BrFunctionButton  : TTpButton       read  FTpButton   write SetTipoBotao;
    property  BrAlignment       : TAlignment      read  FAlignment  write SetAlignment;
    property  BrDicionario      : TBrvDicionario  read  gBrvDic     write gBrvDic;
    property  BrRecordar        : Boolean         read  FSnRecord   write FSnRecord;
  end;

const TcConsul = 120; // Tecla de consulta F9

procedure Register;

implementation

constructor TBrvEditDate.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
      CriarBotao;

      FDtValida  := False;
      FDtVazia   := True;
      FAlignment := taLeftJustify;

      OnDblClick := DblClickEdit;
end;

procedure TBrvEditDate.SetTipoBotao(InTpBotao : TTpButton);
begin
      FTpButton := InTpBotao;

      case FTpButton of
           TVdData     :
           begin
                 FButton.Glyph.LoadFromResourceName(HInstance, 'BtnCalend');
                 FButton.Hint  := 'Calendário';
                 EditMask      := '99/99/9999;1; ';
//                 Width         := 90;
           end;

           TVdDataHora :
           begin
                 FButton.Glyph.LoadFromResourceName(HInstance, 'BtnDatHor');
                 FButton.Hint := 'Calendário';
                 EditMask     := '99/99/9999 99:99:99;1; ';
//                 Width        := 170;
           end;
      end;

      FDsVazia   := Text;
end;

procedure  TBrvEditDate.DoExit;
begin
      VerificarData('E');
      inherited DoExit;
end;

procedure  TBrvEditDate.Change;
begin
      if (FDsVazia <> '') and (gBrvDic <> nil) then
      begin
            VerificarData('C');
      end else
      begin
            FDtVazia  := True;
            FDtValida := False;
      end;

      inherited Change;
end;

procedure TBrvEditDate.SetData(InData: TDate);
begin
      if  InData > StrToDate('01' + DateSeparator + '01' + DateSeparator +
                             '1900') then
      begin
            Text := DateToStr(inData);
      end else
      begin
            Text := '';
      end;
end;

procedure TBrvEditDate.SetDataTime(InData: TDateTime);
begin
      if  InData > StrToDateTime('01' + DateSeparator + '01' + DateSeparator +
                             '1900 00:00:00') then
      begin
            Text := DateTimeToStr(InData);
      end else
      begin
            Text := '';
      end;
end;

destructor TBrvEditDate.Destroy;
var lBrvRecord : TBrvRecordacao;
begin
      FButton.Destroy;

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

procedure TBrvEditDate.VerificarData(TpVerifi : String);
var  DtAuxDat :  TDate;
     DtAuxTim :  TDateTime;
     TextAux  :  String;
begin
      FDtValida := True;
      FDtVazia  := False;
      FDtSQL    := '';

      if  Text = FDsVazia then
      begin
            FDtVazia  := True;
            FDtValida := False;
      end else
      begin
            if not (csDesigning in ComponentState) then
            begin
                  if gBrvDic = nil then
                  begin
                        raise Exception.Create(
                               'Dicionário de dados não definido para o objeto: ' + Name);
                  end;

                  try
                      case FTpButton of
                           TVdData     :
                           begin
                                 TextAux := Trim(Text);

                                 if (Length(TextAux) = Length(Text)) then
                                 begin
                                       DtAuxDat    := StrToDate(Text);
                                       FDtData     := DtAuxDat;
                                       FDtSQL      :=
                                                FormatDateTime(gBrvDic.DateSql,FDtData);
                                 end else
                                 begin
                                       FDtValida := False;
                                 end;
                           end;

                           TVdDataHora :
                           begin
                                 DtAuxTim    := StrToDateTime(Text);
                                 FDtDataTime := DtAuxTim;
                                 FDtSQL      := FormatDateTime(gBrvDic.DateTimeSql,
                                                                       FDtDataTime);
                           end;
                      end;
                  except
                      on  EConvertError do
                      begin
                            FDtValida := False;
                            
                            if TpVerifi = 'E' then
                            begin
                                  Raise Exception.Create(Text + ' não é uma data válida');
                            end;
                      end;
                  end;
            end;
      end;
end;

{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
 =-=-=-=- Rotinas do Botão =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
 =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
procedure  TBrvEditDate.CriarBotao;
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

procedure TBrvEditDate.WMSize(var Message: TWMSize);
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
end;

procedure TBrvEditDate.SetEditRect;
var Loc: TRect;
begin
      {=-=-=-=-= demarca o campo de edição do edit =-=-=-=-==--=-=-=-=-=-=}
      SendMessage(Handle, EM_GETRECT, 0, LongInt(@Loc));
      Loc.Bottom := ClientHeight + 1;  {+1 is workaround for windows paint bug}
      Loc.Top := 0;
      Loc.Left := 2;
      Loc.Right := ClientWidth - FButton.Width - 1;

      SendMessage(Handle, EM_SETRECTNP, 0, LongInt(@Loc));
      SendMessage(Handle, EM_GETRECT, 0, LongInt(@Loc));  {debug}
end;

procedure TBrvEditDate.CreateWnd;
begin
      inherited CreateWnd;
      SetEditRect;
end;

procedure  TBrvEditDate.ChamarCalendario;
var DtAtual  : TDate;
    SnExecut : Boolean;
begin
      if  not ReadOnly then
      begin
            if (Text <> FDsVazia) and (FDtValida) then
            begin
                  DtAtual := FDtData;
            end else
            begin
                  DtAtual := Date;
            end;

             FDsCalend.Execute(DtAtual, SnExecut);

            if  SnExecut then
            begin
                  case FTpButton of
                       TVdData     : SetData(DtAtual);
                       TVdDataHora : SetDataTime(StrToDateTime(
                                             DateToStr(DtAtual) + ' ' + TimeToStr(Time)));
                  end;

                  SetFocus;
           end;
      end;
end;

procedure TBrvEditDate.KeyUp(var Key: Word; Shift: TShiftState);
begin
      inherited KeyUp(key, Shift);
      TeclaPressionada(Key);
end;

procedure TBrvEditDate.ClickBotao (Sender: TObject);
begin
      TeclaPressionada(TcConsul);
end;

procedure TBrvEditDate.DblClickEdit(Sender: TObject);
Var DtAtual : TDate;
begin
      if (not ReadOnly) and (FDtVazia) then
      begin
            if (Text <> FDsVazia) then
            begin
                  DtAtual := FDtData;
            end else
            begin
                  DtAtual := Date;
            end;

            case FTpButton of
                 TVdData     : SetData(DtAtual);
                 TVdDataHora : SetDataTime(StrToDateTime(
                                             DateToStr(DtAtual) + ' ' + TimeToStr(Time)));
            end;
      end;
end;

procedure TBrvEditDate.TeclaPressionada(key : Word);
begin
      if  key = TcConsul then
      begin
            case FTpButton of
                 TVdData     : ChamarCalendario;
                 TVdDataHora : ChamarCalendario;
            end;
      end;
end;
{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-= Rotinas para alinhamento do edit -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
procedure TBrvEditDate.SetAlignment(VrAlinha: TAlignment);
begin
      if FAlignment <> VrAlinha then
      begin
            FAlignment := VrAlinha;
            RecreateWnd;
      end;
end;

procedure TBrvEditDate.CreateParams(var DsParams: TCreateParams);
const
  Alignments: array[TAlignment] of Longint = (ES_LEFT, ES_RIGHT, ES_CENTER);
begin
      inherited CreateParams(DsParams);
      CreateSubClass(DsParams, 'DBEDIT');
      DsParams.Style := DsParams.Style and
                                       not (ES_AUTOVSCROLL or ES_AUTOHSCROLL) or
                                        ES_MULTILINE or Alignments[FAlignment];
end;
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}

procedure Register;
begin
      RegisterComponents('Bravo Standard', [TBrvEditDate]);
end;

end.
