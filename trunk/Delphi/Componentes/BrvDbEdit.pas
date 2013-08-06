unit BrvDbEdit;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, StdCtrls,
  Mask, DBCtrls, Buttons, Db, BrvCalculadora, FileCtrl, ShellApi, BrvCalendario,
  ExtDlgs, BrvImage, BrvDicionario, BrvConsulta;

type
  TTpButton = (TpDbConsulta, TpDbCalculadora, TpDbDiretorio, TpDbEmail,   TpDbHomePage,
               TpDbDataHora, TpDbImagem,      TpDbData,      TpDbArquivo, TpDbDias);

  TBrvDbEdit = class(TDBEdit)
  private
    { Private declarations }
    FDsConfig     : TStrings;
    FButton       : TSpeedButton;
    FButton02     : TSpeedButton;
    FTpButton     : TTpButton;
    FSnBotVis     : Boolean;
    FWcButton     : TWinControl;
    FWcButto2     : TWinControl;
    FAlignment    : TAlignment;
    FOnBefCon     : TNotifyEvent;
    FOnAftCon     : TNotifyEvent;
    FDsCalcul     : TBrvCalculadora;
    FDsCalend     : TBrvCalendario;
    FDsImagem     : TBrvImage;
    FSnVieImg     : Boolean;
    FDsDicion     : TBrvDicionario;
    FCdQuery      : Integer;
    FDsParams     : TStrings;
    FCorReaOnl    : TColor;

    procedure SetEditRect;   //
    procedure WMSetFocus(var Message: TWMSetFocus); message WM_SETFOCUS;
    procedure CMTextChanged(var Message: TMessage); message CM_TEXTCHANGED;
    procedure WMSize(var Message: TWMSize); message WM_SIZE;      //
    procedure SetBotaoVisivel(InBotVis : Boolean);     //
    procedure CriarBotao;     //
    procedure SetTipoBotao(InTpBotao : TTpButton);  //
    procedure SetAlignment(VrAlinha: TAlignment);  //
    procedure CreateParams(var DsParams: TCreateParams); override;    //
    procedure SetConfigurar(Value: TStrings);     //
    procedure SetParametros(Value: TStrings);    //
    procedure DestruirVisualizadorImagem;   //
    procedure CriarVisualizadorImagem;     //
    Procedure CalcularDias;         //
  protected
    { Protected declarations }
    procedure  KeyUp(var Key: Word; Shift: TShiftState); override;
    procedure  KeyPress(var key : char); override;
    procedure  TeclaPressionada(key : Word);
    procedure  ChamarCalculadora;
    procedure  ChamarCalendario;
    procedure  ExecutarBuscaDiretorio;
    procedure  ExecutarBuscaImagem;
    procedure  ExecutarBuscaArquivo;
    procedure  VisualizarWeb(DsParam : String);
    procedure  CreateWnd; override;
    procedure  ClickBotao (Sender: TObject); virtual;
    procedure  ClickViewImagem (Sender: TObject); virtual;
    procedure  DoExit; override;
    procedure  DoEnter; override;
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    property    Button : TSpeedButton read FButton;
    procedure   BrChamarConsulta;
    procedure   BrValidarEntrada;
    procedure   BrvReadOnly(pSnReadOnly : boolean);
  published
    { Published declarations }
    property BrAlignment        : TAlignment     read FAlignment  write SetAlignment;
    property BrVisibleButton    : Boolean        read FSnBotVis   write SetBotaoVisivel;
    property BrFunctionButton   : TTpButton      read FTpButton   write SetTipoBotao;
    property BrOnBeforeConsulta : TNotifyEvent   read FOnBefCon   write FOnBefCon;
    property BrOnAfterConsulta  : TNotifyEvent   read FOnAftCon   write FOnAftCon;
    property BrConfigurar       : TStrings       read FDsConfig   write SetConfigurar;
    property BrDicionario       : TBrvDicionario read FDsDicion   write FDsDicion;
    property BrQueryConsulta    : Integer        read FCdQuery    write FCdQuery;
    property BrParams           : TStrings       read FDsParams   write SetParametros;
    property BrvReadOnlyColor   : TColor         read FCorReaOnl  write FCorReaOnl;
  end;

const TcConsul = 120; // Tecla de consulta F9

procedure Register;

implementation

uses BrvCalculaDiasForm;

{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-= Criando o componente -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
constructor TBrvDbEdit.Create(AOwner: TComponent);
begin
//    FSnBotVis        := True;
      inherited create(AOwner);
      FCorReaOnl       := $00DDE2E3;
      FAlignment       := taRightJustify;
      FDsConfig        := TStringList.Create;
      FDsParams        := TStringList.Create;
      FSnVieImg        := False;
      CriarBotao;
      CriarVisualizadorImagem;    
end;

{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-= Destruindo o componente =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
destructor TBrvDbEdit.Destroy;
begin
      DestruirVisualizadorImagem;

      if  FSnBotVis then
      begin
            FButton.Destroy;
            FWcButton.Destroy;
      end;

      FDsParams.Destroy;

      inherited  destroy;
end;

{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-= Rotinas para chamada das funções -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
procedure  TBrvDbEdit.KeyPress(var key : char);
begin
      if (DataSource.DataSet.FieldByName(DataField) is TNumericField) and
                                              ((key = '.') or (key = ',')) then
      begin
            key := DecimalSeparator;
      end;

      inherited KeyPress(key);
end;

procedure  TBrvDbEdit.KeyUp(var Key: Word; Shift: TShiftState);
begin
      inherited KeyUp(key, Shift);
      TeclaPressionada(Key);
end;

procedure TBrvDbEdit.TeclaPressionada(key : Word);
begin
      if  (key = TcConsul) and (FSnBotVis) then
      begin
            case FTpButton of
                 TpDbConsulta    : begin
                                         BrChamarConsulta;
                                         SetFocus;
                                   end;
                 TpDbCalculadora : ChamarCalculadora;
                 TpDbDiretorio   : ExecutarBuscaDiretorio;
                 TpDbEmail       : VisualizarWeb('mailto:');
                 TpDbHomePage    : VisualizarWeb('http://');
                 TpDbDataHora    : ChamarCalendario;
                 TpDbData        : ChamarCalendario;
                 TpDbImagem      : ExecutarBuscaImagem;
                 TpDbArquivo     : ExecutarBuscaArquivo;
                 TpDbDias        : CalcularDias;
            end;
      end;
end;

procedure  TBrvDbEdit.BrChamarConsulta;
var DsConsul : TBrvConsulta;
begin
      if (not ReadOnly) and (DataSource.DataSet.Active) and
         (not DataSource.DataSet.FieldByName(DataField).ReadOnly) then
      begin
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-  Executando OnBeforeConsulta =-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if Assigned(FOnBefCon) then
            begin
                  FOnBefCon(Self);
            end;

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-  Executando a consulta  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if  (FCdQuery > 0) then
            begin

                  DsConsul := TBrvConsulta.Create(Owner);
                  DsConsul.BrConfigurar.Text  := FDsConfig.Text;
                  DsConsul.BrDataSource       := DataSource;
                  DsConsul.BrQueryCode        := FCdQuery;
                  DsConsul.BrDicionario       := FDsDicion;
                  DsConsul.BrParams.Text      := FDsParams.Text;

                  DsConsul.BrExecute('');

                  DsConsul.Destroy;
            end;

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-  Executando OnAfterConsulta  =-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if Assigned(FOnAftCon) then
            begin
                  FOnAftCon(Self);
            end;
      end;
end;

procedure TBrvDbEdit.ChamarCalculadora;
var VrValFlo : Real;
begin
      if  (DataSource <> nil)         and
          (DataSource.DataSet <> nil) and
          (DataSource.DataSet.Active) then
      begin
            VrValFlo := DataSource.DataSet.FieldByName(DataField).AsFloat;
            VrValFlo := FDsCalcul.Execute(VrValFlo);

            if (not DataSource.DataSet.FieldByName(DataField).ReadOnly) and
               (DataSource.DataSet.FieldByName(DataField).AsFloat <> VrValFlo) then
            begin
                  if  not (DataSource.DataSet.State in [DsInsert, DsEdit]) then
                  begin
                        DataSource.DataSet.Edit;
                  end;

                  DataSource.DataSet.FieldByName(DataField).AsFloat := VrValFlo;
                  SetFocus;
            end;
      end;
end;

procedure  TBrvDbEdit.ExecutarBuscaDiretorio;
var DsDireto : String;
begin
      if  (DataSource <> nil)         and
          (DataSource.DataSet <> nil) and
          (DataSource.DataSet.Active) then
      begin
            DsDireto := '';

            if SelectDirectory('Selecionar diretório', '', DsDireto) then
            begin
                  if (not DataSource.DataSet.FieldByName(DataField).ReadOnly) and
                     (DataSource.DataSet.FieldByName(DataField).AsString <> DsDireto) then
                  begin
                        if  not (DataSource.DataSet.State in [DsInsert, DsEdit]) then
                        begin
                              DataSource.DataSet.Edit;
                        end;

                        DataSource.DataSet.FieldByName(DataField).AsString := DsDireto;
                        SetFocus;
                  end;
            end;
      end;
end;

procedure  TBrvDbEdit.ExecutarBuscaImagem;
var DlgImagem : TOpenPictureDialog;
begin
      if  (DataSource <> nil)         and
          (DataSource.DataSet <> nil) and
          (DataSource.DataSet.Active) then
      begin
            DlgImagem := TOpenPictureDialog.Create(Self);
            DlgImagem.Options := [ofHideReadOnly,ofFileMustExist,ofEnableSizing];
            DlgImagem.InitialDir := '\';
            DlgImagem.Filter     := 'Todos (*.jpg;*.jpeg;*.bmp;*.ico)|*.jpg;'     +
                                    '*.jpeg;*.bmp;*.ico|JPG (*.jpg)|*.jpg|'       +
                                    'JPEG (*.jpeg)|*.jpeg|Bitmaps (*.bmp)|*.bmp|' +
                                    'Icones (*.ico)|*.ico';

            if  (DlgImagem.Execute) and
                (not DataSource.DataSet.FieldByName(DataField).ReadOnly) and
                (DataSource.DataSet.FieldByName(DataField).AsString <>
                                                       DlgImagem.FileName) then
            begin
                  if  not (DataSource.DataSet.State in [DsInsert, DsEdit]) then
                  begin
                        DataSource.DataSet.Edit;
                  end;

                  DataSource.DataSet.FieldByName(DataField).AsString := DlgImagem.FileName;
                  SetFocus;
            end;

            DlgImagem.Destroy;
      end;
end;

procedure TBrvDbEdit.ExecutarBuscaArquivo;
var DlgArquiv : TOpenDialog;
begin
      if  (DataSource <> nil)         and
          (DataSource.DataSet <> nil) and
          (DataSource.DataSet.Active) then
      begin
            DlgArquiv         := TOpenDialog.Create(Self);
            DlgArquiv.Options := [ofHideReadOnly,ofFileMustExist,ofEnableSizing];
            DlgArquiv.InitialDir := '\';
            DlgArquiv.Filter     := 'Todos (*.*)|*.*|Txt (*.txt)|*.txt|'      +
                                    'Word (*.doc)|*.doc|Excel (*.xls)|*.xls|' +
                                    'PDF (*.pdf)|*.pdf';

            if  (DlgArquiv.Execute) and
                (not DataSource.DataSet.FieldByName(DataField).ReadOnly) and
                (DataSource.DataSet.FieldByName(DataField).AsString <>
                                                                  DlgArquiv.FileName) then
            begin
                  if  not (DataSource.DataSet.State in [DsInsert, DsEdit]) then
                  begin
                        DataSource.DataSet.Edit;
                  end;

                  DataSource.DataSet.FieldByName(DataField).AsString :=DlgArquiv.FileName;
                  SetFocus;
            end;

            DlgArquiv.Destroy;
      end;
end;

procedure  TBrvDbEdit.VisualizarWeb(DsParam : String);
begin
      if  DataSource.DataSet.FieldByName(DataField).AsString <> '' then
      begin
            if DsParam = 'mailto:' then
            begin
{
                  FrmMail := TFrmMail.Create(Application);
                  FrmMail.AssociaTo(DataSource.DataSet.FieldByName(DataField).AsString);
                  FrmMail.Showmodal;
                  FrmMail.Close;
}
            end else
            begin
                  ShellExecute(Application.Handle, PChar('open'), PChar(DsParam +
                               DataSource.DataSet.FieldByName(DataField).AsString),
                               PChar(''), nil, Sw_Normal);
            end;
            SetFocus;
      end;
end;

procedure  TBrvDbEdit.ChamarCalendario;
var DtAtual  : TDate;
    DtNova   : String;
    SnExecut : Boolean;
begin
      if  (DataSource <> nil)         and
          (DataSource.DataSet <> nil) and
          (DataSource.DataSet.Active) then
      begin
            if  DataSource.DataSet.FieldByName(DataField).AsString = '' then
            begin
                  DtAtual := Date;
            end else
            begin
                  DtAtual := DataSource.DataSet.FieldByName(DataField).AsDateTime;
            end;

            FDsCalend.Execute(DtAtual, SnExecut);
            DtNova := DateToStr(DtAtual) + ' ' + TimeToStr(Time);

            if  (SnExecut) and
                (not DataSource.DataSet.FieldByName(DataField).ReadOnly) and
                (DataSource.DataSet.FieldByName(DataField).AsString <> DtNova) then
            begin
                  if  not (DataSource.DataSet.State in [DsInsert, DsEdit]) then
                  begin
                        DataSource.DataSet.Edit;
                  end;

                  case FTpButton of
                       TpDbDataHora : DtNova := DateToStr(DtAtual) + ' ' +TimeToStr(Time);
                       TpDbData     : DtNova := DateToStr(DtAtual) + ' 00:00:00';
                  end;

                  DataSource.DataSet.FieldByName(DataField).AsString := DtNova;
                  SetFocus;
            end;
      end;
end;

procedure TBrvDbEdit.CalcularDias;
  procedure CarregaLabel;
  var aux : String;
      AParent, Origem : TComponent;
  begin
        aux := Copy(BrConfigurar.Text, 1, (Pos(#13, BrConfigurar.Text) - 1));

        if aux <> '' then
        begin
              if FrmCalDias.Msg <> '' then
              begin
                    AParent                := Owner;
                    Origem                 := AParent.FindComponent(aux);
                    Tlabel(Origem).Caption := FrmCalDias.msg;
              end;
        end;
  end;
begin
      if  (DataSource <> nil)         and
          (DataSource.DataSet <> nil) and
          (DataSource.DataSet.Active) and
         (not DataSource.DataSet.FieldByName(DataField).ReadOnly) then
      begin
            FrmCalDias := TFrmCalDias.Create(Application);
            FrmCalDias.ShowModal;

            CarregaLabel;

            if FrmCalDias.QtDias > 0 then
            begin
                  DataSource.DataSet.FieldByName(DataField).AsInteger := FrmCalDias.QtDias;
            end;

            FrmCalDias.Close;
      end;
end;

procedure  TBrvDbEdit.DoExit;
var DtAuxili : String;
begin
      BrValidarEntrada;

      if (FTpButton = TpDbData) and
         (DataSource.DataSet.Active) and
         (DataSource.DataSet.FieldByName(DataField).AsString <> '') and
         (DataSource.DataSet.State in [DsInsert, DsEdit]) then
      begin
            DtAuxili := FormatDateTime('dd/mm/yyyy',
                                    DataSource.DataSet.FieldByName(DataField).AsDateTime);
            DataSource.DataSet.FieldByName(DataField).AsDateTime := StrToDate(DtAuxili);
      end;

      inherited DoExit;
end;

procedure TBrvDbEdit.BrvReadOnly;
begin
      self.ReadOnly := pSnReadOnly;

      if (pSnReadOnly) then
      begin
            self.Color := FCorReaOnl;
      end else
      begin
            self.Color := clWhite;
      end;

end;

procedure TBrvDbEdit.BrValidarEntrada;
var DsConsul : TBrvConsulta;
begin
      if  (FCdQuery > 0) and (DataSource.DataSet.Active)  and
          (DataSource.DataSet.State in [DsInsert, DsEdit])  and
          (not DataSource.DataSet.FieldByName(DataField).ReadOnly) then
      begin
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-  Executando OnBeforeConsulta =-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if Assigned(FOnBefCon) then
            begin
                  FOnBefCon(Self);
            end;

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-  Executando a consulta  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            DsConsul := TBrvConsulta.Create(Owner);
            DsConsul.BrConfigurar.Text  := FDsConfig.Text;
            DsConsul.BrDataSource       := DataSource;
            DsConsul.BrQueryCode        := FCdQuery;
            DsConsul.BrDicionario       := FDsDicion;
            DsConsul.BrParams.Text      := FDsParams.Text;

            DsConsul.BrValidarEntrada;

            DsConsul.Destroy;

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-  Executando OnAfterConsulta  =-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if Assigned(FOnAftCon) then
            begin
                  FOnAftCon(Self);
            end;
      end;
end;

procedure  TBrvDbEdit.DoEnter;
begin
      inherited DoEnter;
      SelectAll;
end;
{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-= Rotinas de tratamento do botão -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
procedure  TBrvDbEdit.CriarBotao;
begin
      if  FSnBotVis then
      begin
            FWcButton         := TWinControl.Create(Self);
            FWcButton.Parent  := self;
            FWcButton.Width   := 20;
            FWcButton.Height  := 20;

            FButton           := TSpeedButton.Create (Self);
            FButton.Parent    := FWcButton;
            FButton.Width     := 15;
            FButton.Height    := 15;
            FButton.Visible   := True;
            FButton.OnClick   := ClickBotao;
            FButton.Cursor    := crArrow;
            FButton.ShowHint  := True;

            SetTipoBotao(FTpButton);
      end;
end;

procedure TBrvDbEdit.CriarVisualizadorImagem;
begin
      if FTpButton = TpDbImagem then
      begin
            FWcButto2           := TWinControl.Create(Self);
            FWcButto2.Parent    := self;
            FWcButto2.Width     := 20;
            FWcButto2.Height    := 20;

            FButton02           := TSpeedButton.Create (Self);
            FButton02.Parent    := FWcButto2;
            FButton02.Width     := 15;
            FButton02.Height    := 15;
            FButton02.Visible   := True;
            FButton02.OnClick   := ClickViewImagem;
            FButton02.Cursor    := crArrow;
            FButton02.ShowHint  := True;

            FButton02.Glyph.LoadFromResourceName(HInstance, 'VsVerImagem');
            FButton02.Hint      := 'Visualizar Imagem';

            FSnVieImg           := True;

            RecreateWnd;
      end;
end;

procedure  TBrvDbEdit.ClickViewImagem (Sender: TObject);
begin
      if  (DataSource <> nil)         and
          (DataSource.DataSet <> nil) and
          (DataSource.DataSet.FieldByName(DataField).AsString <> '') then
      begin
            FDsImagem     :=  TBrvImage.Create(Self);
            FDsImagem.VisualizarImagem(
                                      DataSource.DataSet.FieldByName(DataField).AsString);
            FDsImagem.Destroy;
      end;
end;

procedure TBrvDbEdit.DestruirVisualizadorImagem;
begin
      if FSnVieImg then
      begin
            FButton02.Destroy;
            FWcButto2.Destroy;
            FSnVieImg  :=  False;

            RecreateWnd;
      end;
end;

procedure TBrvDbEdit.WMSize(var Message: TWMSize);
var MinHeight: Integer;
    VrLeft   : Integer;
begin
      inherited;

      VrLeft := 0;

      MinHeight := 5;
        { text edit bug: if size to less than minheight, then edit ctrl does
          not display the text }
      if Height < MinHeight then
      begin
            Height := MinHeight;
      end else
      begin
            if (FSnBotVis) and (FButton <> nil) then
            begin
                  FWcButton.Width  := Height;

                  if NewStyleControls and Ctl3D then
                  begin
                        VrLeft := Width - FWcButton.Width - 5;
                        FWcButton.SetBounds(VrLeft,                    // Left
                                            0,                         // Top
                                            FWcButton.Width,           // Width
                                            Height - 3);               // Height
                  end else
                  begin
                        VrLeft := Width - FWcButton.Width;
                        FWcButton.SetBounds (VrLeft-1,                 // Left
                                             1,                        // Top
                                             FWcButton.Width,          // Width
                                             Height - 2);              // Height
                  end;

                  FButton.Width  :=  FWcButton.Width;
                  FButton.Height :=  FWcButton.Height;
                  VrLeft         :=  VrLeft - FWcButton.Width - 1;
            end;

            if  FSnVieImg then
            begin
                  FWcButto2.Width  := Height;

                  if NewStyleControls and Ctl3D then
                  begin
                        if  VrLeft = 0 then
                        begin
                              VrLeft := Width - FWcButto2.Width - 5;
                        end;

                        FWcButto2.SetBounds(VrLeft,                    // Left
                                            0,                         // Top
                                            FWcButto2.Width,           // Widht
                                            Height - 3);               // Height
                  end else
                  begin
                        if  VrLeft = 0 then
                        begin
                              VrLeft := Width - FWcButto2.Width;
                        end;

                        FWcButto2.SetBounds (VrLeft,                   // Left
                                             1,                        // Top
                                             FWcButto2.Width,          // Widht
                                             Height - 0);              // Height
                  end;

                  FButton02.Width  :=  FWcButto2.Width;
                  FButton02.Height :=  FWcButto2.Height;
            end;

            if ((FSnBotVis) and (FButton <> nil)) or (FSnVieImg) then
            begin
                  SetEditRect;
            end;
      end;
end;

{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==--=-=-=-=-=-=}
{=-=-=-=-= demarca o campo de edição do edit =-=-=-=-==--=-=-=-=-=-=}
{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==--=-=-=-=-=-=}
procedure TBrvDbEdit.SetEditRect;
var Loc: TRect;
begin
      if (DataSource <> nil) and  (DataSource.DataSet <> nil)               and
         (DataSource.DataSet.Active)                                        and
         (DataSource.DataSet.FieldByName(DataField).DataType  <> FtString)  and
         (DataSource.DataSet.FieldByName(DataField).DataType  <> FtBoolean) and
         (DataSource.DataSet.FieldByName(DataField).Alignment <> taLeftJustify) then
      begin
            DataSource.DataSet.FieldByName(DataField).Alignment := taLeftJustify;
            FAlignment  := taRightJustify;
      end;

      SendMessage(Handle, EM_GETRECT, 0, LongInt(@Loc));
      Loc.Bottom := ClientHeight + 1;  {+1 is workaround for windows paint bug}
      Loc.Top    := 0;
      Loc.Left   := 0;

      if  FSnBotVis then
      begin
            Loc.Right := ClientWidth - FWcButton.Width - 1;
      end else
      begin
            Loc.Right := ClientWidth - 1;
      end;

      if  (FTpButton = TpDbImagem) and (FSnVieImg) then
      begin
            Loc.Right := Loc.Right - FWcButto2.Width - 1;
      end;

      SendMessage(Handle, EM_SETRECTNP, 0, LongInt(@Loc));
      SendMessage(Handle, EM_GETRECT, 0, LongInt(@Loc));
end;

procedure TBrvDbEdit.WMSetFocus(var Message: TWMSetFocus);
begin
      inherited;

      if ((FSnBotVis) and (FButton <> nil)) or (FSnVieImg) then
      begin
            SetEditRect;
      end;
end;

procedure TBrvDbEdit.CMTextChanged(var Message: TMessage);
begin
      inherited;

      if ((FSnBotVis) and (FButton <> nil)) or (FSnVieImg) then
      begin
            SetEditRect;
      end;
end;
{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==--=-=-=-=-=-=}

procedure TBrvDbEdit.CreateWnd;
begin
      inherited CreateWnd;
      SetEditRect;

      if (FCdQuery > 0) and (FButton <> nil) and (FButton.Hint = 'Pesquisar') then
      begin
            FButton.Hint := FButton.Hint + ' - Query : ' + FormatFloat('0000', FCdQuery);
      end;
end;

procedure TBrvDbEdit.SetBotaoVisivel(InBotVis : Boolean);
begin
      if  InBotVis <> FSnBotVis then
      begin
            FSnBotVis := InBotVis;

            if  FSnBotVis then
            begin
                  CriarBotao;
            end else
            begin
                  try
                      FButton.Destroy;
                      FWcButton.Destroy;
                  Except
                  end;
            end;

            RecreateWnd;
      end;
end;

procedure TBrvDbEdit.ClickBotao (Sender: TObject);
begin
      TeclaPressionada(TcConsul);
end;

procedure TBrvDbEdit.SetTipoBotao(InTpBotao : TTpButton);
begin
      FTpButton := InTpBotao;

      DestruirVisualizadorImagem();

      if  FSnBotVis then
      begin
            case FTpButton of
                 TpDbConsulta    :
                 begin
                       FButton.Glyph.LoadFromResourceName(HInstance, 'BtnConsul');
                       FButton.Hint := 'Pesquisar';
                 end;
                 TpDbCalculadora :
                 begin
                       FButton.Glyph.LoadFromResourceName(HInstance, 'BtnCalcul');
                       FButton.Hint := 'Calculadora';
                 end;
                 TpDbDiretorio   :
                 begin
                       FButton.Glyph.LoadFromResourceName(HInstance, 'BtnDireto');
                       FButton.Hint := 'Pesquisar diretório';
                 end;
                 TpDbEMail       :
                 begin
                       FButton.Glyph.LoadFromResourceName(HInstance, 'BtnEmail');
                       FButton.Hint := 'Enviar e-mail';
                 end;
                 TpDbHomePage    :
                 begin
                       FButton.Glyph.LoadFromResourceName(HInstance, 'BtnHomPag');
                       FButton.Hint := 'Exibir Home Page';
                 end;
                 TpDbDataHora    :
                 begin
                       FButton.Glyph.LoadFromResourceName(HInstance, 'BtnDatHor');
                       FButton.Hint := 'Calendário';
                 end;
                 TpDbData        :
                 begin
                       FButton.Glyph.LoadFromResourceName(HInstance, 'BtnCalend');
                       FButton.Hint := 'Calendário';
                 end;
                 TpDbImagem      :
                 begin
                       FButton.Glyph.LoadFromResourceName(HInstance, 'BtnOpeImg');
                       FButton.Hint := 'Localizar Imagem';
                       CriarVisualizadorImagem;
                 end;
                 TpDbArquivo     :
                 begin
                       FButton.Glyph.LoadFromResourceName(HInstance, 'BtnOpeArq');
                       FButton.Hint := 'Localizar Arquivo';
                 end;
                 TpDbDias        :
                 begin
                       FButton.Glyph.LoadFromResourceName(HInstance, 'BtnDias');
                       FButton.Hint := 'Calcular Dias/Meses/Anos';
                 end;
            end;
      end else
      begin
            case FTpButton of
                 TpDbImagem : CriarVisualizadorImagem;
            end;
      end;
end;

{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-= Rotinas para alinhamento do edit -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
procedure TBrvDbEdit.SetAlignment(VrAlinha: TAlignment);
begin
      if FAlignment <> VrAlinha then
      begin
            FAlignment := VrAlinha;
            RecreateWnd;
      end;
end;

procedure TBrvDbEdit.CreateParams(var DsParams: TCreateParams);
const
  Alignments: array[TAlignment] of Longint = (ES_LEFT, ES_RIGHT, ES_CENTER);
begin
      inherited CreateParams(DsParams);
      CreateSubClass(DsParams, 'DBEDIT');
      DsParams.Style := DsParams.Style and not (ES_AUTOVSCROLL or ES_AUTOHSCROLL) or
                                                ES_MULTILINE or Alignments[FAlignment];
end;

{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-= Rotinas de configuração do "Configurar" =-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
procedure TBrvDbEdit.SetConfigurar(Value: TStrings);
begin
      FDsConfig.Assign(Value);
end;

{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-= Rotinas de configuração do "VitalParams" -=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
procedure TBrvDbEdit.SetParametros(Value: TStrings);
begin
      FDsParams.Assign(Value);
end;

// =-=-=-=-=-=-=-= Registrando o componente -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

procedure Register;
begin
      RegisterComponents('Bravo Banco', [TBrvDbEdit]);
end;

end.
