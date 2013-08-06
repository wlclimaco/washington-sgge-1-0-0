unit BrvEditNum;

interface

uses Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
     StdCtrls, Buttons, Db, BrvDicionario, BrvCalculadora, BrvRecordacao, BrvCustomEdit;

type
  TTpButton = (TpConsulta, TpCalculadora);
  TBrvEditNum = class(TBrvCustomEdit)
  private
    { Private declarations }
    FDsConfig  :  TStrings;
    FButton    :  TSpeedButton;
    FQtDecima  :  Byte;
    FSnMilhar  :  Boolean;
    FAlignment :  TAlignment;
    FDsCalcul  :  TBrvCalculadora;
    FVrValFlo  :  Real;
    FVrFloSql  :  String;
    FVrValInt  :  Integer;
    FSnChange  :  Boolean;
    FSnBotVis  :  Boolean;
    FTpButton  :  TTpButton;
    FOnBtnCli  :  TNotifyEvent;
    FOnBefCon  :  TNotifyEvent;
    FOnAftCon  :  TNotifyEvent;
    FDsDicion  :  TBrvDicionario;
    FCdQuery   :  Integer;
    FDsParams  :  TStrings;
    FDsRetOld  :  TStrings;
    FSnRecord  :  Boolean;
    gSnRecord  :  Boolean;
    procedure CreateParams(var DsParams: TCreateParams); override;
    procedure SetAlignment(VrAlinha: TAlignment);
    procedure SetCasasDecimais(InDecimal : Byte);
    procedure SetValorFloat(InValor : Real);
    procedure SetValorFloatSql(InValor : String);
    procedure SetValorInteger(InValor : Integer);
    function  RetirarSeparadorMilhar(DsText : String) : String;
    procedure WMSize(var Message: TWMSize); message WM_SIZE;
    procedure SetEditRect;
    procedure SetBotalVisivel(InBotVis : Boolean);
    procedure CriarBotao;
    procedure SetTipoBotao(InTpBotao : TTpButton);
    procedure SetConfigurar(Value: TStrings);
    procedure ChamarEventoBotao;
    procedure SetParametros(Value: TStrings);
    procedure PesquisarConteudo;
  protected
    { Protected declarations }
    procedure  FormatarSaida;
    procedure  TeclaPressionada(var key : char);
    procedure  ChamarCalculadora;
    procedure  SelecionarTudo;
    function   AtualizarMudanca : Real;
    procedure  KeyUp(var Key: Word; Shift: TShiftState); override;
    procedure  KeyPress(var key : char); override;
    procedure  DoExit; override;
    procedure  DoEnter; override;
    procedure  Change; override;
    procedure  CreateWnd; override;
    procedure  ClickBotao (Sender: TObject); virtual;
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    property    Button : TSpeedButton read FButton;
    property    BrOnBtnClick : TNotifyEvent read FOnBtnCli write FOnBtnCli;
    procedure   BrValidarEntrada;
    procedure   BrChamarConsulta;
  published
    { Published declarations }
    property BrAlignment: TAlignment             read FAlignment
                                                           write SetAlignment default taLeftJustify;
    property BrCasasDecimais    : Byte           read FQtDecima write SetCasasDecimais;
    property BrSepararMilhar    : Boolean        read FSnMilhar write FSnMilhar;
    property BrAsFloat          : Real           read FVrValFlo write SetValorFloat;
    property BrAsInteger        : Integer        read FVrValInt write SetValorInteger;
    property BrAsFloatSQL       : String         read FVrFloSQL write SetValorFloatSql;
    property BrVisibleButton    : Boolean        read FSnBotVis write SetBotalVisivel;
    property BrFunctionButton   : TTpButton      read FTpButton write SetTipoBotao;
    property BrOnBeforeConsulta : TNotifyEvent   read FOnBefCon write FOnBefCon;
    property BrOnAfterConsulta  : TNotifyEvent   read FOnAftCon write FOnAftCon;
    property BrConfigurar       : TStrings       read FDsConfig write SetConfigurar;
    property BrDicionario       : TBrvDicionario read FDsDicion   write FDsDicion;
    property BrQueryCode        : Integer        read FCdQuery    write FCdQuery;
    property BrParams           : TStrings       read FDsParams   write SetParametros;
    property BrRecordar         : Boolean        read FSnRecord   write FSnRecord;
  end;

  const TcConsul = 120; // Tecla de consulta F9

procedure Register;

implementation

uses BrvConsulta;

constructor TBrvEditNum.Create(AOwner: TComponent);
begin
      FAlignment:=  taRightJustify;
      inherited create(AOwner);

      // =-=-=-= Configurando propriedades do edit
      FSnChange    := True;
      Text         := '0' + DecimalSeparator + '00';
      FQtDecima    :=  2;
      FSnMilhar    :=  False;

      // =-=-=-=-=
      FSnBotVis    := True;
      FDsConfig    := TStringList.Create;
      FDsParams    := TStringList.Create;
      FDsRetOld    := TStringList.Create;

      FSnRecord    := False;
      gSnRecord    := False;

      CriarBotao;
end;

procedure  TBrvEditNum.KeyPress(var key : char);
begin
      inherited KeyPress(key);
      TeclaPressionada(key);
end;

procedure TBrvEditNum.TeclaPressionada(var key : char);
begin
      if  (key = '.') or (key = ',') then
      begin
            key := DecimalSeparator;
      end;

      if  (not (key in ['0'..'9'])) and (key <> #8) and
          (key <> DecimalSeparator)  then
      begin
            key  := #0;
      end else
      begin
            if  key = DecimalSeparator then
            begin
                  if (FQtDecima = 0) or (pos(DecimalSeparator, text) <> 0) then
                  begin
                        key := #0;
                  end;
            end;
      end;
end;

procedure TBrvEditNum.ClickBotao (Sender: TObject);
begin
      ChamarEventoBotao;
end;

procedure TBrvEditNum.KeyUp(var Key: Word; Shift: TShiftState);
begin
      inherited KeyUp(key, Shift);

      if  (key = TcConsul) and (FSnBotVis) then
      begin
            ChamarEventoBotao;
      end;
end;

procedure  TBrvEditNum.ChamarEventoBotao;
begin
      case FTpButton of
           TpConsulta    : begin
                                 BrChamarConsulta;
                                 SetFocus;
                           end;
           TpCalculadora : ChamarCalculadora;
      end;
end;

procedure  TBrvEditNum.BrChamarConsulta;
var DsConsul : TBrvConsulta;
begin
      if  not ReadOnly then
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
                  DsConsul.BrConfigurar.Text := FDsConfig.Text;
                  DsConsul.BrQueryCode       := FCdQuery;
                  DsConsul.BrDicionario      := FDsDicion;
                  DsConsul.BrParams.Text     := FDsParams.Text;

                  DsConsul.BrExecute('');

                  FDsRetOld.Text             := DsConsul.FDsRetOld.Text;

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

procedure TBrvEditNum.ChamarCalculadora;
var VrCalcul : Real;
begin
      if  not ReadOnly then
      begin
            VrCalcul := FDsCalcul.Execute(FVrValFlo);

            SetValorFloat(VrCalcul);
            SetFocus;
      end;
end;

procedure  TBrvEditNum.Change;
begin
      try
          if  FSnChange then
          begin
                AtualizarMudanca;
          end;

          inherited Change;
      except
          showmessage(self.name);
      end;
end;

function  TBrvEditNum.AtualizarMudanca : Real;
var VrIntege : Integer;
    DsValor  : String;
    VrContad : Byte;
begin
      Result   := 0;
      VrIntege := 0;

      if  Text <> '' then
      begin
            Text := RetirarSeparadorMilhar(Text);
            Result   := StrToFloat(Text);
            VrIntege := StrToIntDef(FormatFloat('0', Result), 0);
      end;

      DsValor  := '0.';

      for VrContad := 1 to FQtDecima do
      begin
            DsValor := DsValor + '0';
      end;

      FVrValFlo := StrToFloat(FormatFloat(DsValor, Result));
      FVrValInt := VrIntege;
      FVrFloSQL := FormatFloat(DsValor, FVrValFlo);
      FVrFloSQL := StringReplace(FVrFloSql, '.', #0,  [rfReplaceAll]);
      FVrFloSQL := StringReplace(FVrFloSql, ',', '.', [rfReplaceAll]);
end;

function TBrvEditNum.RetirarSeparadorMilhar(DsText : String) : String;
var NrPosica : byte;
begin
      NrPosica := pos(ThousandSeparator, DsText);

      while  NrPosica <> 0 do
      begin
            Delete(DsText, NrPosica, 1);
            NrPosica := pos(ThousandSeparator, DsText)
      end;

      Result := DsText;
end;

procedure  TBrvEditNum.DoExit;
begin
      BrValidarEntrada;
      inherited DoExit;
end;

procedure TBrvEditNum.BrValidarEntrada;
begin
      FormatarSaida;
      PesquisarConteudo;
      FormatarSaida;
end;

procedure TBrvEditNum.PesquisarConteudo;
var DsConsul : TBrvConsulta;
begin
      if  (FCdQuery > 0) and (not ReadOnly)  then
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
            DsConsul.BrConfigurar.Text := FDsConfig.Text;
            DsConsul.BrQueryCode       := FCdQuery;
            DsConsul.BrDicionario      := FDsDicion;
            DsConsul.BrParams.Text     := FDsParams.Text;

            DsConsul.FDsRetOld.Text   := FDsRetOld.Text;

            DsConsul.BrValidarEntrada;

            FDsRetOld.Text            := DsConsul.FDsRetOld.Text;

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

procedure TBrvEditNum.FormatarSaida;
var DsValor  : String;
    VrContad : Byte;
begin
      if not FSnMilhar then
      begin
            if  FQtDecima > 0 then
            begin
                  DsValor  := '0.';
            end else
            begin
                  DsValor  := '0';
            end;
      end else
      begin
            if  FQtDecima > 0 then
            begin
                  DsValor  := '###,###,###,###,##0.';
            end else
            begin
                  DsValor  := '###,###,###,###,##0';
            end;
      end;

      for VrContad := 1 to FQtDecima do
      begin
            DsValor := DsValor + '0';
      end;

      FSnChange  := False;
      Text       := FormatFloat(DsValor, AtualizarMudanca);
      FSnChange  := True;
end;

procedure  TBrvEditNum.DoEnter;
begin
      inherited DoEnter;
      SelecionarTudo;
end;

procedure TBrvEditNum.SelecionarTudo;
begin
      SelectAll;
end;

destructor TBrvEditNum.Destroy;
var lBrvRecord : TBrvRecordacao;
begin
      if  FSnBotVis then
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
      FreeAndNil(FDsRetOld);


      inherited  destroy;
end;

procedure TBrvEditNum.SetAlignment(VrAlinha: TAlignment);
begin
      if FAlignment <> VrAlinha then
      begin
            FAlignment := VrAlinha;
            RecreateWnd;
      end;
end;

procedure TBrvEditNum.CreateParams(var DsParams: TCreateParams);
const
  Alignments: array[TAlignment] of Longint = (ES_LEFT, ES_RIGHT, ES_CENTER);
begin
      inherited CreateParams(DsParams);
      CreateSubClass(DsParams, 'EDIT');
      DsParams.Style := DsParams.Style and
                                       not (ES_AUTOVSCROLL or ES_AUTOHSCROLL) or
                                        ES_MULTILINE or Alignments[FAlignment];
end;

procedure TBrvEditNum.SetValorFloatSQL(InValor : String);
begin
      InValor := StringReplace(InValor, ',', #0,  [rfReplaceAll]);
      InValor := StringReplace(InValor, '.', ',', [rfReplaceAll]);
      Text    := InValor;
      FormatarSaida;
end;

procedure TBrvEditNum.SetValorFloat(InValor : Real);
begin
      Text := FloatToStr(inValor);
      FormatarSaida;
end;

procedure TBrvEditNum.SetValorInteger(InValor : Integer);
begin
      Text := IntToStr(inValor);
      FormatarSaida;
end;

procedure TBrvEditNum.SetCasasDecimais(InDecimal : Byte);
var DsDecima : String;
    VrContad : byte;
begin
      FQtDecima  :=  InDecimal;

      DsDecima := '0';

      if  FQtDecima > 0 then
      begin
            DsDecima := DsDecima + DecimalSeparator;
      end;

      for VrContad := 1 to FQtDecima do
      begin
            DsDecima := DsDecima + '0';
      end;

      Text := DsDecima;
end;


{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
 =-=-=-=- Rotinas do Botão =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
 =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
procedure  TBrvEditNum.CriarBotao;
begin
      if  FSnBotVis then
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
end;

procedure TBrvEditNum.WMSize(var Message: TWMSize);
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
                end;
            finally
                FreeAndNil(lBrvRecord);
            end;

            gSnRecord := True;
      end;
end;

procedure TBrvEditNum.SetEditRect;
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

procedure TBrvEditNum.CreateWnd;
begin
  inherited CreateWnd;
  SetEditRect;

  if (FCdQuery > 0) and (FButton.Hint = 'Pesquisar') then
  begin
        FButton.Hint := FButton.Hint + ' - Query : ' + FormatFloat('0000', FCdQuery);
  end;
end;

procedure TBrvEditNum.SetBotalVisivel(InBotVis : Boolean);
begin
      if  InBotVis <> FSnBotVis then
      begin
            FSnBotVis       := InBotVis;

            if  FSnBotVis then
            begin
                  CriarBotao;
            end else
            begin
                  try
                      FButton.Destroy;
                  Except
                  end;
            end;

            RecreateWnd;
      end;
end;

procedure TBrvEditNum.SetTipoBotao(InTpBotao : TTpButton);
begin
      if  FSnBotVis then
      begin
            FTpButton := InTpBotao;

            if FTpButton = TpConsulta then
            begin
                  FButton.Glyph.LoadFromResourceName(HInstance, 'BtnConsul');
                  FButton.Hint  := 'Pesquisar';
            end else
            begin
                  FButton.Glyph.LoadFromResourceName(HInstance, 'BtnCalcul');
                  FButton.Hint  := 'Calculadora';
            end;
      end;
end;

{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-= Rotinas de configuração do "Configurar" =-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
procedure TBrvEditNum.SetConfigurar(Value: TStrings);
begin
      FDsConfig.Assign(Value);
end;

{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-= Rotinas de configuração do "BrParams" =-=-=-=-=-=-=-=-=-=-=-=-=
 =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
procedure TBrvEditNum.SetParametros(Value: TStrings);
begin
      FDsParams.Assign(Value);
end;

{=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-}
procedure Register;
begin
      RegisterComponents('Bravo Standard', [TBrvEditNum]);
end;

end.
