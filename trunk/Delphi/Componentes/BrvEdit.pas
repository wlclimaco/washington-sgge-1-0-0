unit BrvEdit;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, Db, BrvDicionario, BrvConsulta, SqlExpr, FileCtrl, BrvRecordacao, BrvCustomEdit;

type
  TTpButton = (VeConsulta, VeArquivo, VeDiretorio);

  TBrvEdit = class(TBrvCustomEdit)
  private
    { Private declarations }
    FDsConfig : TStrings;
    FButton   : TSpeedButton;
    FSnBotVis : Boolean;
    FTpButton : TTpButton;
    FOnBtnCli : TNotifyEvent;
    FOnBefCon : TNotifyEvent;
    FOnAftCon : TNotifyEvent;
    FAlignment: TAlignment;
    FDsDicion : TBrvDicionario;
    FCdQuery  : Integer;
    FDsParams : TStrings;
    FNmArquiv : AnsiString;
    FSnRecord : Boolean;
    gSnRecord : Boolean;
    procedure WMSize(var Message: TWMSize); message WM_SIZE;
    procedure SetEditRect;
    procedure SetBotaoVisivel(InBotVis : Boolean);
    procedure CriarBotao;
    procedure SetTipoBotao(InTpBotao : TTpButton);
    procedure SetConfigurar(Value: TStrings);
    procedure ChamarEventoBotao;
    procedure SetAlignment(Value : TAlignment);
    procedure CreateParams(var DsParams: TCreateParams); override;
    procedure ChamarConsulta;
    procedure SetParametros(Value: TStrings);
    procedure TeclaPressionada(var key : char);
    procedure ExecutarBuscaDiretorio;
  protected
    { Protected declarations }
    procedure  EncontrarArquivo;
    procedure  SelecionarTudo;
    procedure  KeyUp(var Key: Word; Shift: TShiftState); override;
    procedure  KeyPress(var key : char); override;
    procedure  DoExit; override;
    procedure  DoEnter; override;
    procedure  CreateWnd; override;
    procedure  ClickBotao (Sender: TObject); virtual;
  public
    { Public declarations }
    DlgOpen : TOpenDialog;
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    property    Button : TSpeedButton read FButton;
    property    OnBtnClick : TNotifyEvent read FOnBtnCli write FOnBtnCli;
    procedure   ValidarEntrada;
  published
    { Published declarations }
    property BrVisibleButton    : Boolean        read FSnBotVis  write SetBotaoVisivel;
    property BrFunctionButton   : TTpButton      read FTpButton  write SetTipoBotao;
    property BrOnBeforeConsulta : TNotifyEvent   read FOnBefCon  write FOnBefCon;
    property BrOnAfterConsulta  : TNotifyEvent   read FOnAftCon  write FOnAftCon;
    property BrConfigurar       : TStrings       read FDsConfig  write SetConfigurar;
    property BrAlignment        : TAlignment     read FAlignment write SetAlignment;
    property BrDicionario       : TBrvDicionario read FDsDicion  write FDsDicion;
    property BrvQueryCode       : Integer        read FCdQuery   write FCdQuery;
    property BrParams           : TStrings       read FDsParams  write SetParametros;
    property BrRecordar         : Boolean        read FSnRecord  write FSnRecord;
  end;

procedure Register;

  const TcConsul  = 120; // Tecla de consulta F9

implementation

constructor TBrvEdit.Create(AOwner: TComponent);
begin
      inherited create(AOwner);
      FAlignment := taLeftJustify;
      FSnBotVis  := True;
      FDsConfig  := TStringList.Create;
      FDsParams  := TStringList.Create;
      DlgOpen    := TOpenDialog.Create(Self);

      CriarBotao;

      Hint := Name;
end;

destructor TBrvEdit.Destroy;
var lBrvRecord : TBrvRecordacao;
begin
      if FSnBotVis then
      begin
            FButton.Destroy;
      end;

      if FSnRecord then
      begin
            try
                lBrvRecord := TBrvRecordacao.Create(Self);
                lBrvRecord.BrDicionario := FDsDicion;
                lBrvRecord.GravarRecordacao(Text, Owner.Name, Name);
            finally
                FreeAndNil(lBrvRecord);
            end;
      end;

      FDsConfig.Destroy;
      FDsParams.Destroy;
      DlgOpen.Destroy;

      inherited  destroy;
end;

procedure  TBrvEdit.CriarBotao;
begin
      if FSnBotVis then
      begin
            FButton          := TSpeedButton.Create(Self);
            FButton.Width    := Height+2;
            FButton.Height   := Height-1;
            FButton.Visible  := True;
            FButton.Parent   := Self;
            FButton.OnClick  := ClickBotao;
            FButton.Cursor   := crArrow;
            FButton.ShowHint := True;

            SetTipoBotao(FTpButton);
      end;
end;

procedure TBrvEdit.ClickBotao(Sender: TObject);
begin
      ChamarEventoBotao;
end;

procedure  TBrvEdit.ChamarEventoBotao;
begin
      case FTpButton of
           VeConsulta  : begin
                               ChamarConsulta;
                               SetFocus;
                         end;
           VeArquivo   : EncontrarArquivo;
           VeDiretorio : ExecutarBuscaDiretorio;
      end;
end;

procedure TBrvEdit.SetTipoBotao(InTpBotao : TTpButton);
begin
      if FSnBotVis then
      begin
            FTpButton := InTpBotao;

            case FTpButton of
                 VeConsulta  : begin
                                     FButton.Glyph.LoadFromResourceName(HInstance, 'BtnConsul');
                                     FButton.Hint  := 'Pesquisar';
                               end;
                 VeArquivo   : begin
                                     FButton.Glyph.LoadFromResourceName(HInstance, 'BtnOpeArq');
                                     FButton.Hint  := 'Abrir';
                               end;
                 VeDiretorio : begin
                                     FButton.Glyph.LoadFromResourceName(HInstance, 'BtnDireto');
                                     FButton.Hint := 'Pesquisar diretório';
                               end;
            end;
      end;
end;

procedure TBrvEdit.WMSize(var Message: TWMSize);
var lBrvRecord : TBrvRecordacao;
    lDsRecord  : String;
begin
      inherited;

      if (FSnBotVis) and (FButton <> nil) then
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

      if (FSnBotVis) and (FButton <> nil) then
      begin
            SetEditRect;
      end;

      if FSnRecord and (not gSnRecord) then
      begin
            try
                lBrvRecord := TBrvRecordacao.Create(Self);
                lBrvRecord.BrDicionario := FDsDicion;
                lDsRecord  := lBrvRecord.CarregarRecordacao(Owner.Name, Name);

                if lDsRecord <> '' then
                begin
                      Text := lDsRecord;
                      ValidarEntrada;
                end;
            finally
                FreeAndNil(lBrvRecord);
            end;

            gSnRecord := True;
      end;
end;

procedure TBrvEdit.CreateWnd;
begin
      inherited CreateWnd;
      SetEditRect;
end;

procedure TBrvEdit.SetEditRect;
var Loc: TRect;
begin
      {=-=-=-=-= demarca o campo de edição do edit =-=-=-=-==--=-=-=-=-=-=}
      SendMessage(Handle, EM_GETRECT, 0, LongInt(@Loc));
      Loc.Bottom := ClientHeight + 1;  {+1 is workaround for windows paint bug}
      Loc.Top := 0;
      Loc.Left := 2;

      if FSnBotVis then
      begin
            Loc.Right := ClientWidth - FButton.Width - 1;
      end else
      begin
            Loc.Right := ClientWidth - 1;
      end;

      SendMessage(Handle, EM_SETRECTNP, 0, LongInt(@Loc));
      SendMessage(Handle, EM_GETRECT, 0, LongInt(@Loc));  {debug}
end;

procedure TBrvEdit.SetBotaoVisivel(InBotVis : Boolean);
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
                  except
                      // -- Não Faz Nada -- //
                  end;
            end;

            RecreateWnd;
      end;
end;

procedure  TBrvEdit.ExecutarBuscaDiretorio;
var DsDireto : String;
begin
      DsDireto := '';

      if SelectDirectory('Selecionar diretório', '', DsDireto) then
      begin
            Text := DsDireto;
            SetFocus;
      end;
end;

procedure  TBrvEdit.KeyPress(var key : char);
begin
      TeclaPressionada(key);
end;

procedure TBrvEdit.EncontrarArquivo;
begin
      if  DlgOpen.Execute then
      begin
          // =-=-=-= Executando OnBeforeConsulta
            if Assigned(FOnBefCon) then
            begin
                  FOnBefCon(Self);
            end;

         // =-=-=-= Movendo o arquivo
            Text := DlgOpen.FileName;

         // =-=-=-= Executando OnAfterConsulta
           if Assigned(FOnAftCon) then
           begin
                 FOnAftCon(Self);
           end;
      end;
end;

procedure  TBrvEdit.ChamarConsulta;
var DsConsul : TBrvConsulta;
begin
      if not ReadOnly then
      begin
        // =-=-=-=-=-  Executando OnBeforeConsulta =-=-=-=-=-=-=-=-=-=-=-=-=-=
           if Assigned(FOnBefCon) then
           begin
                 FOnBefCon(Self);
           end;

        // =-=-=-=-=-  Executando a consulta  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
           if  FCdQuery > 0 then
           begin
                 DsConsul := TBrvConsulta.Create(Owner);
                 DsConsul.BrConfigurar.Text  := FDsConfig.Text;
                 DsConsul.BrQueryCode        := FCdQuery;
                 DsConsul.BrDicionario       := FDsDicion;
                 DsConsul.BrParams.Text      := FDsParams.Text;

                 DsConsul.BrExecute('');

                 DsConsul.Destroy;
           end;

        // =-=-=-=-=-  Executando OnAfterConsulta  =-=-=-=-=-=-=-=-=-=-=-=-=-=
           if Assigned(FOnAftCon) then
           begin
                 FOnAftCon(Self);
           end;
      end;
end;

procedure TBrvEdit.SetConfigurar(Value: TStrings);
begin
      FDsConfig.Assign(Value);
end;

procedure TBrvEdit.KeyUp(var Key: Word; Shift: TShiftState);
begin
      inherited KeyUp(key, Shift);

      if  (key = TcConsul) and (FSnBotVis) then
      begin
            ChamarEventoBotao;
      end;
end;

procedure  TBrvEdit.DoExit;
begin
      ValidarEntrada;
      inherited DoExit;
end;

procedure  TBrvEdit.DoEnter;
begin
      SelecionarTudo;
      inherited DoEnter;
end;

procedure TBrvEdit.SelecionarTudo;
begin
      SelectAll;
end;

procedure TBrvEdit.SetAlignment(Value : TAlignment);
begin
      if FAlignment <> Value then
      begin
            FAlignment := Value;
            RecreateWnd;
      end;
end;

procedure TBrvEdit.CreateParams(var DsParams: TCreateParams);
const
  Alignments: array[TAlignment] of Longint = (ES_LEFT, ES_RIGHT, ES_CENTER);
begin
      inherited CreateParams(DsParams);
      CreateSubClass(DsParams, 'EDIT');
      DsParams.Style := DsParams.Style and
                                       not (ES_AUTOVSCROLL or ES_AUTOHSCROLL) or
                                        ES_MULTILINE or Alignments[FAlignment];
end;

procedure TBrvEdit.ValidarEntrada;
var DsConsul : TBrvConsulta;
begin
      if  (FCdQuery > 0) and (not ReadOnly) then
      begin
         // =-=-=-=-=-  Executando OnBeforeConsulta =-=-=-=-=-=-=-=-=-=-=-=-=-=
            if Assigned(FOnBefCon) then
            begin
                  FOnBefCon(Self);
            end;

         // =-=-=-=-=-  Executando a consulta  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            DsConsul := TBrvConsulta.Create(Owner);
            DsConsul.BrConfigurar.Text := FDsConfig.Text;
            DsConsul.BrQueryCode       := FCdQuery;
            DsConsul.BrDicionario      := FDsDicion;
            DsConsul.BrParams.Text  := FDsParams.Text;

            DsConsul.BrValidarEntrada;

            DsConsul.Destroy;

         // =-=-=-=-=-  Executando OnAfterConsulta  =-=-=-=-=-=-=-=-=-=-=-=-=-=
            if Assigned(FOnAftCon) then
            begin
                  FOnAftCon(Self);
            end;
      end;
end;

procedure TBrvEdit.TeclaPressionada(var key : char);
begin
//      if  (key = '.') or (key = ',') then
//      begin
//            key := DecimalSeparator;
//      end;
end;

//=-=-=-=-=-=-=-= Rotinas de configuração dos Parametros -=-=-=-=-=-=-=-=-=-=-=-=
procedure TBrvEdit.SetParametros(Value: TStrings);
begin
      FDsParams.Assign(Value);
end;

procedure Register;
begin
      RegisterComponents('Bravo Standard', [TBrvEdit]);
end;

end.
