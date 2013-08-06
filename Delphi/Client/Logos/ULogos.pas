unit ULogos;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, BrvString, StdCtrls, ExtCtrls, ComCtrls, Menus, DB, DBClient, BrvClientDataSet,
  BrvCalculadora, BrvCalendario, Gauges, MidasLib, UClaSrv, ImgList;

type
  TFrmLogos = class(TForm)
    TrmClose: TTimer;
    TmrConexa: TTimer;
    MainMenu: TMainMenu;
    Sistema1: TMenuItem;
    DicionriodeDados1: TMenuItem;
    Menu1: TMenuItem;
    QpMenu: TBrvClientDataSet;
    CdsOpcao: TBrvClientDataSet;
    BrvCalculadora: TBrvCalculadora;
    BrvCalendario: TBrvCalendario;
    QpPerMen: TBrvClientDataSet;
    StbRodape: TStatusBar;
    CdsTelAbe: TBrvClientDataSet;
    CdsTelAbeNmFormul: TStringField;
    CdsTelAbeNrFormul: TSmallintField;
    BrvString: TBrvString;
    CdsForAbe: TBrvClientDataSet;
    TmrRefresh: TTimer;
    ImgListStatusBar: TImageList;
    procedure TrmCloseTimer(Sender: TObject);
    procedure TmrConexaTimer(Sender: TObject);
    procedure DicionriodeDados1Click(Sender: TObject);
    procedure Menu1Click(Sender: TObject);
    procedure FormCloseQuery(Sender: TObject; var CanClose: Boolean);
    procedure FormShow(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure StbRodapeDrawPanel(StatusBar: TStatusBar; Panel: TStatusPanel; const Rect: TRect);
    procedure TmrRefreshTimer(Sender: TObject);
  private
    { Private declarations }
    procedure CriarTabelaTemporariaOpcoes;
    procedure ClickItemMenu(Sender: TObject);
    procedure ChamarFormularioEstatico(pNmFormul : String; pDsMenu : String; pVrParMen: String);
    procedure ChamarFormularioDinamico(pNrFormul : Integer);
    function  UsuarioTemPermissaoMenu(pNrMenu : String): Boolean;
    procedure SalvarFormulariosAberto;
    procedure MontarFormularioSalvo;
    procedure PostarFormulariosAberto;
  public
    { Public declarations }
    gQtRecCnx : Integer;
    gStSrvImp : Byte; // (1) conectado (2) desconectado
    gSnSalFor : Boolean;
    procedure MontarMenuSuspenso;
    function  AutenticaUsuario : Boolean;
  end;

var
  FrmLogos: TFrmLogos;

implementation

uses UDmSrvApl, UCad0001, UCad0004, UCad0003, UCai0008, UCai0002, UCai0001,
     UFrmConCli, UDmSis, UCad0000;

{$R *.dfm}

{ TFrmLogos }


procedure TFrmLogos.DicionriodeDados1Click(Sender: TObject);
begin
      Cad0001 := TCad0001.Create(Self);
      Cad0001.Show;
end;

procedure TFrmLogos.FormCloseQuery(Sender: TObject; var CanClose: Boolean);
begin
      if gSnSalFor then
      begin
            SalvarFormulariosAberto;
      end;

      try
          FrmConCli.gTcpAplCli.Disconnect;
      finally
          sleep(500);
          FrmConCli.gTcpImpCli.Disconnect;
      end;
end;

procedure TFrmLogos.FormCreate(Sender: TObject);
begin
      gSnSalFor                      := True;
      FormatSettings.ShortDateFormat := 'dd/mm/yyyy';
end;

procedure TFrmLogos.SalvarFormulariosAberto;
var lNrIndice : Integer;
    lNmForm   : String;
begin
      if CdsTelAbe.Active then
      begin
            CdsTelAbe.EmptyDataSet;
      end else
      begin
            CdsTelAbe.CreateDataSet;
      end;

      if MDIChildCount > 0 then
      begin
            if MessageDlg('Deseja salvar formulários abertos.',
                                            mtConfirmation, [mbNo, mbYes], 0) = MrYes then
            begin
                  for lNrIndice := 0 to MDIChildCount - 1 do
                  begin
                        lNmForm := UpperCase(MDIChildren[lNrIndice].Caption);

                        BrvString.Split(lNmForm, '-');

                        CdsTelAbe.Append;

                        if BrvString.BrSplitLista.Count > 2 then
                        begin
                              lNmForm := BrvString.BrSplitLista.Strings[1];
                              lNmForm := StringReplace(lNmForm, '(', '', [rfReplaceAll]);
                              lNmForm := StringReplace(lNmForm, ')', '', [rfReplaceAll]);
                              lNmForm := Trim(lNmForm);

                              CdsTelAbe.FieldByName('NrFormul').AsString := lNmForm;
                        end else
                        begin
                              CdsTelAbe.FieldByName('NmFormul').AsString :=
                                                        BrvString.BrSplitLista.Strings[0];
                        end;

                        CdsTelAbe.Post;

                  end;
            end;
      end;

      PostarFormulariosAberto;
end;

procedure TFrmLogos.StbRodapeDrawPanel(StatusBar: TStatusBar; Panel: TStatusPanel;
                                                                                 const Rect: TRect);
var lDsMsgPnl : String;
    lNrIdxImg : Integer;
begin
      lNrIdxImg := Panel.Index;

      case Panel.Index of
        0 : lDsMsgPnl := DmSrvApl.BrvDicionario.UserName;
        1 : lDsMsgPnl := DmSrvApl.gNmSerImp;
        2 : lDsMsgPnl := DmSrvApl.gNmSerApl;
        3 : lDsMsgPnl := 'Servidor de Conexão';
      end;

      if (Trim(Panel.Text) <> '') then
      begin
            lDsMsgPnl := Panel.Text;

            StbRodape.Canvas.FillRect(Rect);
            ImgListStatusBar.Draw(StbRodape.Canvas,Rect.Left + 2, Rect.Top, lNrIdxImg);
            ImgListStatusBar.Draw(StbRodape.Canvas,Rect.Left + 20, Rect.Top, 4);
            StbRodape.Canvas.TextOut(Rect.Left + 38, Rect.Top + 1, lDsMsgPnl);

      end else
      begin
            StbRodape.Canvas.FillRect(Rect);
            ImgListStatusBar.Draw(StbRodape.Canvas,Rect.Left + 2, Rect.Top, lNrIdxImg);
            StbRodape.Canvas.TextOut(Rect.Left + 25, Rect.Top + 1, lDsMsgPnl);
      end;
end;

procedure TFrmLogos.PostarFormulariosAberto;
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      lConexao  := TSDmRWClient.Create(DmSrvApl.SrvAplica.DBXConnection);
      try
          lConexao.GravarTelasAbertaUsuario(DmSrvApl.BrvDicionario.Credencial,
                                            lDsResult,
                                            CdsTelAbe.Data);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TFrmLogos.FormShow(Sender: TObject);
begin
      TmrRefresh.Enabled := True;
      SetForegroundWindow(Handle);
end;

procedure TFrmLogos.Menu1Click(Sender: TObject);
begin
      Cad0004 := TCad0004.Create(Self);
      Cad0004.Show;
end;

procedure TFrmLogos.TmrConexaTimer(Sender: TObject);
begin
      Dec(gQtRecCnx);

      StbRodape.Panels[3].Text := 'Sem sinal com servidor de conexão (Aguarde ' +
                                       IntToStr(gQtRecCnx) + 's)';

      if gQtRecCnx <= 0 then
      begin
            StbRodape.Panels[3].Text := '';

            TmrConexa.Enabled := False;

            try
                FrmConCli.IniciarContadorReconexao;
                FrmConCli.ReconectaServidorAplicacao;
                FrmConCli.SolicitarServidorImpressao(2);
            except
                FrmConCli.IniciarProcessoReconexao(30);
                raise;
            end;
      end;
end;

procedure TFrmLogos.TmrRefreshTimer(Sender: TObject);
begin
      TmrRefresh.Enabled := False;
      StbRodape.Refresh;
end;

procedure TFrmLogos.TrmCloseTimer(Sender: TObject);
begin
      Halt(0);
end;

procedure TFrmLogos.CriarTabelaTemporariaOpcoes;
begin
      CdsOpcao.Close;
      CdsOpcao.FieldDefs.Clear;

      CdsOpcao.FieldDefs.Add('NrMenu',   FtInteger,  0, False);
      CdsOpcao.FieldDefs.Add('TpMenu',   FtString,   1, False);
      CdsOpcao.FieldDefs.Add('NmFormul', FtString,  60, False);
      CdsOpcao.FieldDefs.Add('DsMenu',   FtString,  99, False);
      CdsOpcao.FieldDefs.Add('VrParMen', FtString, 100, False);
      CdsOpcao.IndexDefs.Clear;
      CdsOpcao.IndexDefs.Add('Key1', 'NrMenu', [ixPrimary,ixUnique]);
      CdsOpcao.CreateDataSet;

      CdsOpcao.IndexName := 'Key1';
      CdsOpcao.SetKey;
end;

procedure TFrmLogos.ChamarFormularioEstatico(pNmFormul : String; pDsMenu : String;
                                             pVrParMen : String);
var lDsCaptio : String;
    lNmFormul : String;
    lFrmNovo  : TForm;
    lNrFormul : Integer;
begin
      try
          if not DmSis.FormularioAberto(pNmFormul, lNrFormul) then
          begin
                lDsCaptio := pNmFormul + ' - ' + pDsMenu;

                lNmFormul := 'T' + pNmFormul;

                Application.CreateForm(TComponentClass(GetClass(lNmFormul)), lFrmNovo);

                lDsCaptio := StringReplace(lDsCaptio, '&', '',
                                                             [rfReplaceAll,rfIgnoreCase]);
                lFrmNovo.Caption  := lDsCaptio;
                TCad0000(lFrmNovo).gVrParam := pVrParMen;
          end;
      except
          raise Exception.Create('Não foi possível carregar formulário estático "' +
                                        CdsOpcao.FieldByName('NmFormul').AsString + '".');
      end;
end;

function TFrmLogos.AutenticaUsuario: Boolean;
begin
      Result := True;

      DmSrvApl.InformarEntradaCliente(True, 'A');

      if DmSrvApl.BrvDicionario.CarregarDicionario(nil, nil, DmSrvApl.gDsCreden) then
      begin
            try
                Cai0001 := TCai0001.Create(Self);

                if Cai0001.ShowModal = MrOk then
                begin
                      Application.Title := 'Logos - Cliente ' +
                                  IntToStr(DmSrvApl.BrvDicionario.VersaoCliente) +
                                  '.' +
                                  IntToStr(DmSrvApl.BrvDicionario.VersaoClienteSub);

                      if DmSrvApl.SelecionaEmpresa then
                      begin
                            DmSrvApl.BrvDicionario.VerificarUsuarioGrupoAdministrador;
                            FrmLogos.Caption := Application.Title;
                            DmSrvApl.InformarClienteAutenticado('A');
                            MontarMenuSuspenso;
                            MontarFormularioSalvo;
                      end else
                      begin
                            Result := False;
                      end;
                end else
                begin
                      Result := False;
                end;
            finally
                FreeAndNil(Cai0001);
            end;
      end else
      begin
            Result := False;
      end;
end;

procedure TFrmLogos.MontarFormularioSalvo;
var lDsParame : String;
    lNmFormul : String;
begin
      lDsParame := '<%CdUsuari%>;' + IntToStr(DmSrvApl.BrvDicionario.UserCode);
      CdsForAbe.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(63, lDsParame, Name);

      while not CdsForAbe.Eof do
      begin
            if CdsForAbe.FieldByName('NrForDin').AsInteger > 0 then
            begin
                  ChamarFormularioDinamico(CdsForAbe.FieldByName('NrForDin').AsInteger);
            end else
            begin
                  lNmFormul := CdsForAbe.FieldByName('TpFormul').AsString +
                                 FormatFloat('0000',
                                             CdsForAbe.FieldByName('NrSeqFor').AsInteger);

                  ChamarFormularioEstatico(lNmFormul,
                                           CdsForAbe.FieldByName('DsMenu').AsString,
                                           CdsForAbe.FieldByName('VrParMen').AsString);
            end;

            CdsForAbe.Next;
      end;

      if CdsTelAbe.Active then
      begin
            CdsTelAbe.EmptyDataSet;
      end else
      begin
            CdsTelAbe.CreateDataSet;
      end;

      PostarFormulariosAberto;
end;

procedure TFrmLogos.ChamarFormularioDinamico(pNrFormul : Integer);
var lFrmDinam : TCad0003;
    lNmForm   : String;
    lNrFormul : Integer;
begin
       try
           lNmForm := 'D' + IntToStr(pNrFormul);

           if not DmSis.FormularioAberto(lNmForm, lNrFormul) then
           begin
                 lFrmDinam := TCad0003.Create(Self);
                 lFrmDinam.Name := lNmForm;
                 lFrmDinam.MontarFormularioCadastro(pNrFormul, '');
           end;
       except
           MessageDlg('Não foi possível carregar formulário dinâmico "' +
                           IntToStr(pNrFormul) + '".',
                           MtError, [MbOk], 0);
           raise;
       end;

end;

procedure TFrmLogos.ClickItemMenu(Sender: TObject);
var lNrForm   : Integer;
    lNmAplica : String;
    lDtAplica : TDateTime;
begin
      if (Sender as TMenuItem).Tag < 10000 then
      begin // opções cadastradas
            if CdsOpcao.FindKey([(Sender as TMenuItem).Tag]) then
            begin
                  if CdsOpcao.FieldByName('TpMenu').AsString = 'D' then // dinamico
                  begin
                        ChamarFormularioDinamico(
                                              CdsOpcao.FieldByName('NmFormul').AsInteger);
                  end else
                  begin
                        ChamarFormularioEstatico(
                                                CdsOpcao.FieldByName('NmFormul').AsString,
                                                CdsOpcao.FieldByName('DsMenu').AsString,
                                                CdsOpcao.FieldByName('VrParMen').AsString);

                  end;
            end else
            begin
                  raise Exception.Create('Opção de menu não determinada.');
            end;
      end else
      begin // opções fixas
            case (Sender as TMenuItem).Tag of
                 10002 : begin // calculadora
                               BrvCalculadora.Executar;
                         end;
                 10003 : begin // calendário
                               BrvCalendario.Executar;
                         end;
                 10004 : begin // trocar senha
                               if DmSrvApl.UsuarioAutenticado then
                               begin
                                     Cai0002 := TCai0002.Create(Self);

                                     try
                                         Cai0002.BrvSenha.Login :=
                                                         DmSrvApl.BrvDicionario.UserLogin;

                                         Cai0002.ShowModal;
                                     finally
                                         FreeAndNil(Cai0002);
                                     end;
                               end;
                         end;
                 10005 : begin // atualizar dicionário
                               DmSrvApl.BrvDicionario.CarregarDicionario(nil, nil,
                                                                      DmSrvApl.gDsCreden);
//                               MontarMenuSuspenso;
                               MessageDlg('Dicionário de dados foi atualizado.',
                                                               mtInformation, [mbOk], 0);
                         end;
                 10006 : begin // janelas em cascata
                               FrmLogos.Cascade
                         end;
                 10007 : begin // fechar todas as janelas
                                if MDIChildCount > 0 then
                                begin
                                      if MessageDlg('Todos formulários serão fechados. ' +
                                                    #13#13 +
                                                    'Deseja continuar?', mtConfirmation,
                                                    [mbYes, mbNo], 0) = mrYes then
                                      begin
                                            for lNrForm := 0 to MDIChildCount - 1 do
                                            begin
                                                  MDIChildren[lNrForm].WindowState :=
                                                                                 wsNormal;
                                                  MDIChildren[lNrForm].Close;
                                            end;
                                      end;
                                end;
                         end;
                 10008 : begin // sobre o logos
                               lNmAplica   := Application.ExeName;
                               lDtAplica   := FileDateToDateTime(FileAge(lNmAplica));

                               try
                                   Cai0008 := TCai0008.Create(Self);
                                   Cai0008.LblDsSistem.Caption := 'Logos - Cliente';
                                   Cai0008.LblNrVersao.Caption :=
                                              IntToStr(DmSrvApl.BrvDicionario.VersaoCliente) + '.' +
                                              IntToStr(DmSrvApl.BrvDicionario.VersaoClienteSub);
                                   Cai0008.LblDtCompil.Caption :=
                                        DateToStr(FileDateToDateTime(FileAge(Application.ExeName)));

                                   Cai0008.LblDsServid.Caption := DmSrvApl.gNmSerApl;
                                   Cai0008.LblNrVerSer.Caption :=
                                             IntToStr(DmSrvApl.BrvDicionario.VersaoServidor) + '.' +
                                             IntToStr(DmSrvApl.BrvDicionario.VersaoServidorSub);
                                   Cai0008.ShowModal;
                               finally
                                   FreeAndNil(Cai0008);
                               end;
                         end;
                 10009 : begin // Atualizar Menu
                               MontarMenuSuspenso;
                         end;
            end;
      end;
end;

procedure TFrmLogos.MontarMenuSuspenso;
var lMnuItem   : TMenuItem;
    lMnuAnteri : TMenuItem;
    lLsItesRef : TStrings;
begin
      CriarTabelaTemporariaOpcoes;
      QpMenu.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(22, '', Name);
      MainMenu.Items.Clear;

      lLsItesRef := TStringList.Create();

      while not QpMenu.Eof do
      begin
            try
                lMnuItem            := TMenuItem.Create(Self);
                lMnuItem.Caption    := QpMenu.FieldByName('DsMenu').AsString;
                lMnuItem.Name       := 'M' + QpMenu.FieldByName('NrMenu').AsString;

                if QpMenu.FieldByName('NrMenPai').AsInteger = 1 then
                begin
                      lMnuItem.ImageIndex := -1;
                      MainMenu.Items.Add(lMnuItem);
                end else
                begin
                      lMnuItem.ImageIndex := 5;
                      lMnuAnteri := TMenuItem(FindComponent('M' +
                                                          QpMenu.FieldByName('NrMenPai').AsString));

                      if QpMenu.FieldByName('TpMenu').AsString = 'E' then
                      begin // formulário estático
                            lMnuItem.OnClick    := ClickItemMenu;
                            lMnuItem.Tag        := QpMenu.FieldByName('NrMenu').AsInteger;
                            lMnuItem.ImageIndex := 6;

                            CdsOpcao.Append;
                            CdsOpcao.FieldByName('NrMenu').AsInteger  :=
                                                   QpMenu.FieldByName('NrMenu').AsInteger;
                            CdsOpcao.FieldByName('TpMenu').AsString   :=
                                                   QpMenu.FieldByName('TpMenu').AsString;
                            CdsOpcao.FieldByName('DsMenu').AsString   :=
                                                   QpMenu.FieldByName('DsMenu').AsString;
                            CdsOpcao.FieldByName('VrParMen').AsString   :=
                                                   QpMenu.FieldByName('VrParMen').AsString;
                            CdsOpcao.FieldByName('NmFormul').AsString :=
                                      QpMenu.FieldByName('TpFormul').AsString +
                                      FormatFloat('0000', QpMenu.FieldByName('NrSeqFor').AsInteger);
                            CdsOpcao.Post;
                      end else
                      begin
                            if QpMenu.FieldByName('TpMenu').AsString = 'D' then
                            begin // formulário dinâmico
                                  lMnuItem.OnClick := ClickItemMenu;
                                  lMnuItem.Tag     := QpMenu.FieldByName('NrMenu').AsInteger;
                                  lMnuItem.ImageIndex := 6;

                                  CdsOpcao.Append;
                                  CdsOpcao.FieldByName('NrMenu').AsInteger  :=
                                                       QpMenu.FieldByName('NrMenu').AsInteger;
                                  CdsOpcao.FieldByName('TpMenu').AsString   :=
                                                       QpMenu.FieldByName('TpMenu').AsString;
                                  CdsOpcao.FieldByName('DsMenu').AsString   :=
                                                       QpMenu.FieldByName('DsMenu').AsString;
                                  CdsOpcao.FieldByName('NmFormul').AsString :=
                                                       QpMenu.FieldByName('NrForDin').AsString;
                                  CdsOpcao.Post;
                            end;
                      end;

                      // verificando a permissão do usuário
                      if (QpMenu.FieldByName('TpMenu').AsString = 'D') or
                         (QpMenu.FieldByName('TpMenu').AsString = 'E') then
                      begin
                            lMnuItem.Enabled := UsuarioTemPermissaoMenu(
                                                             QpMenu.FieldByName('NrMenu').AsString);
                      end;

                      TMenuItem(lMnuAnteri).Add(lMnuItem);
                end;

                QpMenu.Next;

            except
                QpMenu.Next;
            end;
      end;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=
      lMnuItem:= TMenuItem.Create(Self);
      lMnuItem.Caption := 'Sistema';
      MainMenu.Items.Add(lMnuItem);
      lMnuItem.Tag     := 10001;
      lMnuAnteri       := lMnuItem;

      lMnuItem := TMenuItem.Create(Self);
      lMnuItem.Caption := 'Calculadora';
      lMnuItem.OnClick := ClickItemMenu;
      lMnuItem.Tag     := 10002;
      TMenuItem(lMnuAnteri).Add(lMnuItem);

      lMnuItem := TMenuItem.Create(Self);
      lMnuItem.Caption := 'Calendário';
      lMnuItem.OnClick := ClickItemMenu;
      lMnuItem.Tag     := 10003;
      TMenuItem(lMnuAnteri).Add(lMnuItem);

      lMnuItem := TMenuItem.Create(Self);
      lMnuItem.Caption := '-';
      lMnuItem.OnClick := ClickItemMenu;
      TMenuItem(lMnuAnteri).Add(lMnuItem);

      lMnuItem := TMenuItem.Create(Self);
      lMnuItem.Caption := 'Trocar Senha';
      lMnuItem.OnClick := ClickItemMenu;
      lMnuItem.Tag     := 10004;
      TMenuItem(lMnuAnteri).Add(lMnuItem);

      lMnuItem := TMenuItem.Create(Self);
      lMnuItem.Caption := '-';
      lMnuItem.OnClick := ClickItemMenu;
      TMenuItem(lMnuAnteri).Add(lMnuItem);

      lMnuItem := TMenuItem.Create(Self);
      lMnuItem.Caption := 'Atualizar dicionário';
      lMnuItem.OnClick := ClickItemMenu;
      lMnuItem.Tag     := 10005;
      TMenuItem(lMnuAnteri).Add(lMnuItem);

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=
      lMnuItem:= TMenuItem.Create(Self);
      lMnuItem.Caption := 'Janelas';
      MainMenu.Items.Add(lMnuItem);
      lMnuAnteri       := lMnuItem;
      FrmLogos.WindowMenu := lMnuAnteri;

      lMnuItem := TMenuItem.Create(Self);
      lMnuItem.Caption := 'Cascata';
      lMnuItem.OnClick := ClickItemMenu;
      lMnuItem.Tag     := 10006;
      TMenuItem(lMnuAnteri).Add(lMnuItem);

      lMnuItem := TMenuItem.Create(Self);
      lMnuItem.Caption := 'Fechar Todas Janelas';
      lMnuItem.OnClick := ClickItemMenu;
      lMnuItem.Tag     := 10007;
      TMenuItem(lMnuAnteri).Add(lMnuItem);

      lMnuItem := TMenuItem.Create(Self);
      lMnuItem.Caption := 'Atualizar menu';
      lMnuItem.OnClick := ClickItemMenu;
      lMnuItem.Tag     := 10009;
      TMenuItem(lMnuAnteri).Add(lMnuItem);

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=
      lMnuItem:= TMenuItem.Create(Self);
      lMnuItem.Caption := 'Ajuda';
      MainMenu.Items.Add(lMnuItem);
      lMnuAnteri       := lMnuItem;

      lMnuItem := TMenuItem.Create(Self);
      lMnuItem.Caption := 'Sobre o Logos';
      lMnuItem.OnClick := ClickItemMenu;
      lMnuItem.Tag     := 10008;
      TMenuItem(lMnuAnteri).Add(lMnuItem);
end;

function TFrmLogos.UsuarioTemPermissaoMenu(pNrMenu : String) : Boolean;
var lDsWhere : String;
begin
      Result := True;

      if not DmSrvApl.BrvDicionario.UserGroupAdm then
      begin
            // usuário não pertence a grupo administrativo
            // verificando permissões dadas
            lDsWhere :=
                       '<%CdUsuari%>;' + IntToStr(DmSrvApl.BrvDicionario.UserCode) + #13 +
                       '<%NrMenu%>;'   + pNrMenu;
            QpPerMen.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(
                                                                      23, lDsWhere, Name);

            if QpPerMen.Eof then
            begin
                  Result := False;
            end
            else if QpPerMen.FieldByName('CdUsuari').AsInteger > 0 then
                 begin
                       Result := False;
                 end;
            ;
      end;
end;

end.

