unit UCad0004;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, ComCtrls, DB,
  DBClient, BrvClientDataSet, Menus, StdCtrls, ImgList, Mask, DBCtrls, BrvDBComboListBox,
  BrvDbEdit, UClaSrv, Grids, BrvDbGrids, BrvDbGrid, BrvListParam;

type
  TCad0004 = class(TCad0000)
    SbtGravar: TBrvSpeedButton;
    SbtCancel: TBrvSpeedButton;
    PnlMenu: TPanel;
    TrvMenu: TTreeView;
    QpMenu: TBrvClientDataSet;
    PopMenu: TPopupMenu;
    PopInclui: TMenuItem;
    PopExclui: TMenuItem;
    QcMenu: TBrvClientDataSet;
    DsMenu: TDataSource;
    ImageList1: TImageList;
    Label2: TLabel;
    Label3: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    DBEdit1: TDBEdit;
    DBEdit2: TDBEdit;
    DBEdit4: TDBEdit;
    DBEdit5: TDBEdit;
    CbxTpMenu: TBrvDBComboListBox;
    Label7: TLabel;
    PgcTpFormul: TPageControl;
    TbsForEst: TTabSheet;
    TbsForDin: TTabSheet;
    Label1: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    DBEdit24: TBrvDbEdit;
    BrvDbEdit1: TBrvDbEdit;
    DBText1: TDBText;
    Label10: TLabel;
    DBText2: TDBText;
    BrvDbEdit2: TBrvDbEdit;
    Label11: TLabel;
    PnlPermis: TPanel;
    Panel1: TPanel;
    GroupBox1: TGroupBox;
    Splitter1: TSplitter;
    Splitter2: TSplitter;
    GbxPerUsu: TGroupBox;
    DbgPerGru: TBrvDbGrid;
    BrvDbGrid2: TBrvDbGrid;
    CdsTmpGru: TClientDataSet;
    DtsTmpGru: TDataSource;
    QcPerGru: TBrvClientDataSet;
    CdsTmpUsu: TClientDataSet;
    DtsTmpUsu: TDataSource;
    QcPerUsu: TBrvClientDataSet;
    Label4: TLabel;
    DBEdit3: TDBEdit;
    procedure FormCreate(Sender: TObject);
    procedure PopMenuPopup(Sender: TObject);
    procedure TrvMenuChanging(Sender: TObject; Node: TTreeNode; var AllowChange: Boolean);
    procedure PopIncluiClick(Sender: TObject);
    procedure QcMenuAfterCancel(DataSet: TDataSet);
    procedure QcMenuAfterInsert(DataSet: TDataSet);
    procedure QcMenuAfterPost(DataSet: TDataSet);
    procedure QcMenuBeforePost(DataSet: TDataSet);
    procedure SbtCancelClick(Sender: TObject);
    procedure SbtGravarClick(Sender: TObject);
    procedure CbxTpMenuClick(Sender: TObject);
    procedure TrvMenuDblClick(Sender: TObject);
    procedure PopExcluiClick(Sender: TObject);
    procedure TrvMenuMouseDown(Sender: TObject; Button: TMouseButton; Shift: TShiftState;
      X, Y: Integer);
    procedure TrvMenuDragOver(Sender, Source: TObject; X, Y: Integer; State: TDragState;
      var Accept: Boolean);
    procedure TrvMenuDragDrop(Sender, Source: TObject; X, Y: Integer);
    procedure CdsTmpGruAfterPost(DataSet: TDataSet);
    procedure CdsTmpGruBeforeInsert(DataSet: TDataSet);
    procedure CdsTmpGruAfterScroll(DataSet: TDataSet);
    procedure CdsTmpUsuAfterPost(DataSet: TDataSet);
    procedure CdsTmpGruAfterDelete(DataSet: TDataSet);
  private
    { Private declarations }
    gNdNode : PString;
    procedure MontarNivelMenu(pNrMenPai: Integer);
    procedure MostrarTipoMenu;
    procedure AtualizarOrdemOpcoes(pNrMenPai: Integer);
    procedure AtualizarOrdemMenu(pNdNode: TTreeNode);
    procedure CarregarPermissoesMenu;
    procedure CarregarPermissoesMenuGrupo;
    procedure CarregarPermissoesMenuUsuario(pSnPerUsu : Boolean);
  public
    { Public declarations }
  end;

var
  Cad0004: TCad0004;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCad0004.CbxTpMenuClick(Sender: TObject);
begin
      if CbxTpMenu.values[CbxTpMenu.Itemindex] = 'S' then // separador
      begin
            QcMenu.FieldByName('DsMenu').AsString := '-';
      end else
      begin
            QcMenu.FieldByName('TpMenu').AsString := CbxTpMenu.values[CbxTpMenu.Itemindex];
      end;

      MostrarTipoMenu;
end;

procedure TCad0004.FormCreate(Sender: TObject);
var lDsDomini : TStrings;
    lVrDomini : TStrings;

    lNrAba    : Integer;
begin
      inherited;

      DmSrvApl.BrvDicionario.AtributoDominioValores('S033', 'TPMENU', lDsDomini, lVrDomini);
      CbxTpMenu.Items.Text  := lDsDomini.Text;
      CbxTpMenu.Values.Text := lVrDomini.Text;

      for lNrAba := 0 to PgcTpFormul.PageCount -1 do
      begin
            PgcTpFormul.Pages[lNrAba].TabVisible := False;
      end;

      MontarNivelMenu(0);
end;

procedure TCad0004.MontarNivelMenu(pNrMenPai : Integer);
var lNdNovo  : TTreeNode;
    lDsWhere : String;
begin
      if pNrMenPai = 0 then
      begin
            lDsWhere := 'Where NVL(NrMenPai, 0) = 0';
      end else
      begin
            lDsWhere := ' Where NrMenPai = '  + IntToStr(pNrMenPai);
      end;

      QpMenu.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(
                                                               16, '<%DsWhere%>;' + lDsWhere, Name);

      while not QpMenu.Eof do
      begin
            new(gNdNode);
            if pNrMenPai = 0 then
            begin
                  TrvMenu.Items.Clear;
                  lNdNovo := TrvMenu.Items.AddObject(nil,
                                         QpMenu.FieldByName('DsMenu').AsString, gNdNode);
                  lNdNovo.ImageIndex    := 0;
                  lNdNovo.SelectedIndex := 0;
            end else
            begin
                  lNdNovo := TrvMenu.Items.AddChildObject(TrvMenu.Selected,
                                                    QpMenu.FieldByName('DsMenu').AsString, gNdNode);

                  if ((QpMenu.FieldByName('TpMenu').AsString = 'E') or
                      (QpMenu.FieldByName('TpMenu').AsString = 'D')) then
                  begin
                        lNdNovo.ImageIndex    := 2;
                        lNdNovo.SelectedIndex := 2;
                  end else
                  begin
                        if (QpMenu.FieldByName('TpMenu').AsString = 'S') then
                        begin
                              lNdNovo.ImageIndex    := -1;
                              lNdNovo.SelectedIndex := -1;
                        end;
                  end;
            end;

            PString(lNdNovo.Data)^ := QpMenu.FieldByName('NrMenu').AsString;

            QpMenu.Next;
      end;
end;

procedure TCad0004.PopExcluiClick(Sender: TObject);
var lNdPai : TTreeNode;
begin
      if MessageDlg('Confirma a exclusão desta opção de menu?', mtConfirmation,
                                                           [mbYes, mbNo], 0) = IdYes then
      begin
            lNdPai  := TrvMenu.Selected.Parent;

            QcMenu.Delete;
            QcMenu.ApplyUpdates(0);
            TrvMenu.Selected.Delete;
            AtualizarOrdemOpcoes(StrToInt(PString(lNdPai.Data)^));
            QcMenu.Close;
            QcMenu.Open;
      end;
end;

procedure TCad0004.AtualizarOrdemOpcoes(pNrMenPai : Integer);
var lConexao     : TSDmSisClient;
    lDsResult    : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-= Criarndo Provider no servidor de aplicação
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          lConexao.ReorganizarOrdenacaoMenu(DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                            pNrMenPai);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCad0004.PopIncluiClick(Sender: TObject);
var lNrMenPai : Integer;
    lNdNovo   : TTreeNode;
begin
      PnlPermis.Visible := False;

      lNrMenPai := StrToInt(PString(TrvMenu.Selected.Data)^);

      if not TrvMenu.Selected.HasChildren then
      begin
            MontarNivelMenu(lNrMenPai);
      end;

      New(gNdNode);
      lNdNovo := TrvMenu.Items.AddChildObject(TrvMenu.Selected, 'Novo Menu', gNdNode);

      PString(lNdNovo.Data)^ := '0';
      lNdNovo.ImageIndex     := 1;
      lNdNovo.SelectedIndex  := 1;
      TrvMenu.Selected       := lNdNovo;

      QcMenu.Append;
      QcMenu.FieldByName('NrMenu').AsInteger   := 99999;
      QcMenu.FieldByName('NrMenPai').AsInteger := lNrMenPai;
      QcMenu.FieldByName('NrOrdem').AsInteger  := TrvMenu.Selected.Index + 1;
      TrvMenu.Selected.Level
end;

procedure TCad0004.PopMenuPopup(Sender: TObject);
var pNdSelect : TTreeNode;
begin
      if StrToInt(PString(TrvMenu.Selected.Data)^) <> 1 then
      begin
            PopMenu.Tag := 0;
            if QcMenu.FieldByName('TpMenu').AsString <> 'G' then
            begin
                  PopInclui.Enabled := False;
                  PopExclui.Enabled := True;
            end else
            begin
                  if not TrvMenu.Selected.HasChildren then
                  begin
                        pNdSelect := TrvMenu.Selected;
                        MontarNivelMenu(StrToInt(PString(TrvMenu.Selected.Data)^));
                        TrvMenu.FullExpand;

                        TrvMenu.Selected := pNdSelect;
                        if not TrvMenu.Selected.HasChildren then
                        begin
                              PopInclui.Enabled := True;
                              PopExclui.Enabled := True;
                        end else
                        begin
                              PopInclui.Enabled := True;
                              PopExclui.Enabled := False;
                        end;
                  end else
                  begin
                        PopInclui.Enabled := True;
                        PopExclui.Enabled := False;
                  end;
            end;
      end else
      begin
            PopMenu.Tag       := 1;
            PopInclui.Enabled := True;
            PopExclui.Enabled := False;
      end;
end;

procedure TCad0004.QcMenuAfterCancel(DataSet: TDataSet);
begin
      if CbxTpMenu.Itemindex >= 0 then
      begin
            MostrarTipoMenu;
      end else
      begin
            TrvMenu.Selected.Delete;
      end;

      SbtCancel.Enabled := False;
      SbtGravar.Enabled := False;
end;

procedure TCad0004.QcMenuAfterInsert(DataSet: TDataSet);
begin
      SbtCancel.Enabled := True;
      SbtGravar.Enabled := True;
end;

procedure TCad0004.QcMenuAfterPost(DataSet: TDataSet);
begin
      SbtCancel.Enabled := False;
      SbtGravar.Enabled := False;

      TrvMenu.Selected.ImageIndex     := 0;
      TrvMenu.Selected.SelectedIndex  := 0;
end;

procedure TCad0004.QcMenuBeforePost(DataSet: TDataSet);
begin
      if QcMenu.FieldByName('DsMenu').AsString = '' then
      begin
            raise Exception.Create('Informe a descrição do menu');
      end;

      if QcMenu.FieldByName('TpMenu').AsString = '' then
      begin
            raise Exception.Create('Informe o tipo de menu');
      end;

      if QcMenu.FieldByName('TpMenu').AsString = 'P' then
      begin
            raise Exception.Create(
                                'Esta opção só pode ser utilizada para o menu principal');
      end;

      if PgcTpFormul.Visible then
      begin
            if PgcTpFormul.ActivePage = TbsForEst then
            begin
                  if QcMenu.FieldByName('TpFormul').AsString = '' then
                  begin
                        raise Exception.Create('Formulário estático deve ser informado');
                  end;

                  QcMenu.FieldByName('NrForDin').AsString := '';
            end else
            begin
                  if QcMenu.FieldByName('NrForDin').AsInteger = 0 then
                  begin
                        raise Exception.Create('Formulário dinâmico deve ser informado');
                  end;

                  QcMenu.FieldByName('TpFormul').AsString := '';
                  QcMenu.FieldByName('NrSeqFor').AsString := '';
            end;
      end;

      if QcMenu.State in [dsInsert] then
      begin
            QcMenu.FieldByName('NrMenu').AsInteger :=
                            DmSrvApl.BrvDicionario.ProxNumeroSequencial('S033', 'NRMENU');

            PString(TrvMenu.Selected.Data)^ := QcMenu.FieldByName('NrMenu').AsString;
      end;
end;

procedure TCad0004.SbtCancelClick(Sender: TObject);
begin
      QcMenu.Cancel;
end;

procedure TCad0004.SbtGravarClick(Sender: TObject);
begin
      QcMenu.FieldByName('NrNivel').AsInteger := TrvMenu.Selected.Level;

      QcMenu.Post;
      QcMenu.ApplyUpdates(0);
      TrvMenu.Selected.Text := QcMenu.FieldByName('DsMenu').AsString;
end;

procedure TCad0004.TrvMenuChanging(Sender: TObject; Node: TTreeNode;
  var AllowChange: Boolean);
begin
      if QcMenu.State in [dsInsert, dsEdit] then
      begin
            QcMenu.Cancel;
      end;

      QcMenu.Close;
      QcMenu.BrParams.Clear;
      QcMenu.BrParams.Add('<%NrMenu%>;' + PString(Node.Data)^);
      QcMenu.Open;

      MostrarTipoMenu;

      if Node.AbsoluteIndex > 0 then
      begin
            PnlMenu.Visible   := True;
            PnlPermis.Visible := False;

            if Pos(QcMenu.FieldByName('TpMenu').AsString, 'ED') > 0  then
            begin
                  CarregarPermissoesMenu;
                  PnlPermis.Visible := True;
            end;
      end else
      begin
            PnlMenu.Visible := False;
      end;
end;

procedure TCad0004.CarregarPermissoesMenu;
begin
      CarregarPermissoesMenuGrupo;
end;

procedure TCad0004.CarregarPermissoesMenuGrupo;
begin
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-=-= Abrindo a Query que efetivará as alterações no banco
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      QcPerGru.Close;
      QcPerGru.BrParams.Clear;
      QcPerGru.BrParams.Add('<%NrMenu%>;' + QcMenu.FieldByName('NrMenu').AsString);
      QcPerGru.Open;
      if QcPerGru.Tag = 0 then
      begin
            QcPerGru.Tag := 1;
            QcPerGru.IndexDefs.Add('Key_Grupo', 'CDGRUPO', [ixCaseInsensitive,ixDescending]);
            QcPerGru.IndexName := 'Key_Grupo';
      end;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-=-= Abrindo a Query para controle visual
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      CdsTmpGru.Tag := 1;
      CdsTmpGru.DisableControls;
      CdsTmpGru.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(
                                 18,
                                 '<%NrMenu%>;' + QcMenu.FieldByName('NrMenu').AsString,
                                 Name);

      while not CdsTmpGru.Eof do
      begin
            if CdsTmpGru.FieldByName('CdPerGru').AsInteger > 0 then
            begin
                  CdsTmpGru.Edit;
                  CdsTmpGru.FieldByName('SnPermis').AsString := 'S';
                  CdsTmpGru.Post;
            end;

            CdsTmpGru.Next;
      end;

      CdsTmpGru.EnableControls;
      CdsTmpGru.Tag := 0;
      CdsTmpGru.First;
end;

procedure TCad0004.CdsTmpGruAfterDelete(DataSet: TDataSet);
begin
      CarregarPermissoesMenuUsuario(DataSet.Tag = 0);
end;

procedure TCad0004.CdsTmpGruAfterPost(DataSet: TDataSet);
begin
      if DataSet.Tag = 0 then
      begin
            if DataSet.FieldByName('SnPermis').AsString = 'S' then
            begin
                  QcPerGru.Append;
                  QcPerGru.FieldByName('NrMenu').AsInteger  :=
                           QcMenu.FieldByName('NrMenu').AsInteger;
                  QcPerGru.FieldByName('CdGrupo').AsInteger :=
                           CdsTmpGru.FieldByName('CdGrupo').AsInteger;
                  QcPerGru.Post;
            end else
            begin
                  if QcPerGru.FindKey([CdsTmpGru.FieldByName('CdGrupo').AsInteger]) then
                  begin
                        QcPerUsu.First;
                        while not QcPerUsu.EOF do
                        begin
                              QcPerUsu.Delete;
                        end;
                        QcPerUsu.ApplyUpdates(0);

                        QcPerGru.Delete;
                  end;
            end;

            QcPerGru.ApplyUpdates(0);
            CarregarPermissoesMenuUsuario(DataSet.Tag = 0);
      end;
end;

procedure TCad0004.CdsTmpGruAfterScroll(DataSet: TDataSet);
begin
      CarregarPermissoesMenuUsuario(DataSet.Tag = 0);
end;

procedure TCad0004.CdsTmpGruBeforeInsert(DataSet: TDataSet);
begin
      Abort;
end;

procedure TCad0004.CarregarPermissoesMenuUsuario(pSnPerUsu : Boolean);
begin
      GbxPerUsu.Visible := False;

      if (CdsTmpGru.FieldByName('SnPermis').AsString = 'S') and (pSnPerUsu) then
      begin
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-=-=-=-= Abrindo a Query que efetivará as alterações no banco
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            QcPerUsu.Close;
            QcPerUsu.BrParams.Clear;
            QcPerUsu.BrParams.Add('<%NrMenu%>;'  + QcMenu.FieldByName('NrMenu').AsString);
            QcPerUsu.BrParams.Add('<%CdGrupo%>;' + CdsTmpGru.FieldByName('CdGrupo').AsString);
            QcPerUsu.Open;
            if QcPerUsu.Tag = 0 then
            begin
                  QcPerUsu.Tag := 1;
                  QcPerUsu.IndexDefs.Add('Key_Usu', 'CdUsuari', [ixCaseInsensitive,ixDescending]);
                  QcPerUsu.IndexName := 'Key_Usu';
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-=-=-=-= Abrindo a Query para controle visual
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            CdsTmpUsu.Tag := 1;
            CdsTmpUsu.DisableControls;
            CdsTmpUsu.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(
                               20,
                               '<%NrMenu%>;' + QcMenu.FieldByName('NrMenu').AsString +
                               #13 +
                               '<%CdGrupo%>;' + CdsTmpGru.FieldByName('CdGrupo').AsString,
                               Name);

            while not CdsTmpUsu.Eof do
            begin
                  if CdsTmpUsu.FieldByName('CdPerUsu').AsInteger > 0 then
                  begin
                        CdsTmpUsu.Edit;
                        CdsTmpUsu.FieldByName('SnPermis').AsString := 'S';
                        CdsTmpUsu.Post;
                  end;

                  CdsTmpUsu.Next;
            end;

            CdsTmpUsu.First;
            CdsTmpUsu.EnableControls;
            CdsTmpUsu.Tag := 0;

            GbxPerUsu.Visible := True;
      end;
end;

procedure TCad0004.CdsTmpUsuAfterPost(DataSet: TDataSet);
begin
      if DataSet.Tag = 0 then
      begin
            if DataSet.FieldByName('SnPermis').AsString = 'S' then
            begin
                  QcPerUsu.Append;
                  QcPerUsu.FieldByName('NrMenu').AsInteger  :=
                           QcMenu.FieldByName('NrMenu').AsInteger;
                  QcPerUsu.FieldByName('CdGrupo').AsInteger :=
                           CdsTmpGru.FieldByName('CdGrupo').AsInteger;
                  QcPerUsu.FieldByName('CdUsuari').AsInteger :=
                           CdsTmpUsu.FieldByName('CdUsuari').AsInteger;
                  QcPerUsu.Post;
            end else
            begin
                  if QcPerUsu.FindKey([CdsTmpUsu.FieldByName('CdUsuari').AsInteger]) then
                  begin
                        QcPerUsu.Delete;
                  end;
            end;

            QcPerUsu.ApplyUpdates(0);
      end;
end;

procedure TCad0004.TrvMenuDblClick(Sender: TObject);
begin
      if not TrvMenu.Selected.HasChildren then
      begin
            MontarNivelMenu(StrToInt(PString(TrvMenu.Selected.Data)^));
            TrvMenu.FullExpand;
      end;
end;

procedure TCad0004.TrvMenuDragDrop(Sender, Source: TObject; X, Y: Integer);
var lNdNode: TTreeNode;
begin
      lNdNode := TrvMenu.GetNodeAt(X, Y);

      if lNdNode <> nil then
      begin
            TrvMenu.Selected.MoveTo(lNdNode, naInsert);
            AtualizarOrdemMenu(lNdNode)
      end;
end;

procedure TCad0004.AtualizarOrdemMenu(pNdNode : TTreeNode);
var lNrOldOrd : Integer;
    lNrNewOrd : Integer;
    lConexao  : TSDmSisClient;
    lDsResult : String;
begin
      lNrOldOrd := QcMenu.FieldByName('NrOrdem').AsInteger;
      lNrNewOrd := pNdNode.Index;

      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-= Criarndo Provider no servidor de aplicação
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          lConexao.MoverMenu(DmSrvApl.BrvDicionario.Credencial, lDsResult,
                             QcMenu.FieldByName('NrMenPai').AsInteger,
                             lNrOldOrd, lNrNewOrd);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;

      QcMenu.Close;
      QcMenu.Open;
end;

procedure TCad0004.TrvMenuDragOver(Sender, Source: TObject; X, Y: Integer;
  State: TDragState; var Accept: Boolean);
var lNdNode : TTreeNode;
begin
      lNdNode := TrvMenu.GetNodeAt(X, Y);
      Accept  := (Source = Sender) and (lNdNode <> nil)   and
                 (lNdNode.Level = TrvMenu.Selected.Level) and
                 (lNdNode <> TrvMenu.Selected);
end;

procedure TCad0004.TrvMenuMouseDown(Sender: TObject; Button: TMouseButton;
  Shift: TShiftState; X, Y: Integer);
begin
      if (Button = mbLeft) and (ssCtrl in Shift) and (TrvMenu.Selected.Level <> 0) then
      begin
            TrvMenu.BeginDrag(False);
      end;
end;

procedure TCad0004.MostrarTipoMenu;
begin
      if CbxTpMenu.Itemindex >= 0 then
      begin
            if CbxTpMenu.values[CbxTpMenu.Itemindex] = 'G' then // grupo
            begin
                  PgcTpFormul.Visible     := False;
            end
            else if CbxTpMenu.values[CbxTpMenu.Itemindex] = 'E' then // estático
                 begin
                       PgcTpFormul.Visible     := True;
                       PgcTpFormul.ActivePage  := TbsForEst;
                 end
            else if CbxTpMenu.values[CbxTpMenu.Itemindex] = 'D' then // dinâmico
                 begin
                       PgcTpFormul.Visible     := True;
                       PgcTpFormul.ActivePage  := TbsForDin;
                 end
            else if CbxTpMenu.values[CbxTpMenu.Itemindex] = 'S' then // separador
                 begin
                       PgcTpFormul.Visible     := False;
                 end;
      end;
end;

initialization
      RegisterClass(TCad0004);

finalization
      UnRegisterClass(TCad0004);

end.
