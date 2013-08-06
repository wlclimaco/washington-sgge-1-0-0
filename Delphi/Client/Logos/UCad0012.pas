unit UCad0012;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, ComCtrls, DB,
  DBClient, BrvClientDataSet, Menus, StdCtrls, ImgList, Mask, DBCtrls, BrvDBComboListBox,
  BrvDbEdit, UClaSrv, Grids, BrvDbGrids, BrvDbGrid, BrvListParam, BrvBitBtn, DBGrids, BrvString;

type
  TCad0012 = class(TCad0000)
    Panel1: TPanel;
    Splitter1: TSplitter;
    PnlMenu: TPanel;
    Label2: TLabel;
    TrvClaRnc: TTreeView;
    ImageList1: TImageList;
    BrvListParam1: TBrvListParam;
    QpClaRnc: TBrvClientDataSet;
    PopClaRnc: TPopupMenu;
    PopInclui: TMenuItem;
    PopExclui: TMenuItem;
    QcClaRNC: TBrvClientDataSet;
    DsClaRNC: TDataSource;
    ImageList2: TImageList;
    Label1: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label6: TLabel;
    PnlRodape: TPanel;
    BtnCancelar: TBrvBitBtn;
    BtnSalvar: TBrvBitBtn;
    DBENrClaRNC: TBrvDbEdit;
    DBENrClaPai: TBrvDbEdit;
    DBEDsClaRNC: TBrvDbEdit;
    DBEDsContro: TBrvDbEdit;
    DBCSnReClan: TBrvDBComboListBox;
    BrvString1: TBrvString;

    procedure FormCreate(Sender: TObject);
    procedure PopClaRncPopup(Sender: TObject);
    procedure TrvClaRncChanging(Sender: TObject; Node: TTreeNode; var AllowChange: Boolean);
    procedure PopIncluiClick(Sender: TObject);
    procedure QcClaRNCBeforePost(DataSet: TDataSet);
    procedure SbtCancelClick(Sender: TObject);
    procedure TrvClaRncDblClick(Sender: TObject);
    procedure PopExcluiClick(Sender: TObject);
    procedure BtnSalvarClick(Sender: TObject);
    procedure BtnCancelarClick(Sender: TObject);
    procedure TrvClaRncMouseDown(Sender: TObject; Button: TMouseButton; Shift: TShiftState; X,
      Y: Integer);
    procedure QcClaRNCAfterEdit(DataSet: TDataSet);
    procedure DBEDsControExit(Sender: TObject);

  private
    { Private declarations }

    gNdNode : PString;
    procedure MontarNivelMenu(lNdPai : TTreeNode;pNrMenPai : Integer);
    procedure PercorrerTreeNode(NodText:String);
    Function  SepPorCaracter(sTexto: string;sCaracter: string): string;
  public
    { Public declarations }
  end;

var
  Cad0012    : TCad0012;
  glNdNovo   : TTreeNode;


implementation

uses UDmSrvApl;

{$R *.dfm}
Function TCad0012.SepPorCaracter(sTexto: string;sCaracter: string): string;
begin
      BrvString1.Split(sTexto,sCaracter);
      Result := BrvString1.BrSplitLista[BrvString1.BrSplitLista.Count-1];
end;

procedure TCad0012.MontarNivelMenu(lNdPai : TTreeNode;pNrMenPai : Integer);
var lNdNovo  : TTreeNode;
    lQpClassRnc  : TclientDataset;
begin
      try
          lQpClassRnc      := TclientDataset.Create(self);
          lQpClassRnc.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro( 233, '<%DsWhere%>;' +
                                                                   ' Where coalesce(cdclapai,0) =  '
                                                                   + IntToStr(pNrMenPai), Name);
          while not lQpClassRnc.Eof do
          begin
                new(gNdNode);
                lNdNovo := TrvClaRnc.Items.AddChildObject(lNdPai,
                                       lQpClassRnc.FieldByName('DsContro').AsString + ' ' +
                                       lQpClassRnc.FieldByName('DsClaRNC').AsString, gNdNode);
                lNdNovo.ImageIndex     := 0;
                lNdNovo.SelectedIndex  := 0;
                PString(lNdNovo.Data)^ := lQpClassRnc.FieldByName('CdClaRNC').AsString;
                MontarNivelMenu(lNdNovo,lQpClassRnc.FieldByName('CdClaRNC').AsInteger);
                lQpClassRnc.Next;
          end;
      finally
            FreeAndNil(lQpClassRnc);
      end;
end;

procedure TCad0012.BtnCancelarClick(Sender: TObject);
var lNdNovo : TTreeNode;
begin
      QcClaRNC.Cancel;
      if glNdNovo <> nil then
      begin
            TrvClaRnc.Items.Delete(glNdNovo);
      end;
      TrvClaRnc.Enabled  := true;
      BtnCancelar.Visible := false;
      BtnSalvar.Visible := false;
end;

procedure TCad0012.BtnSalvarClick(Sender: TObject);
begin
      if trim(QcClaRNC.FieldByName('DsClaRNC').AsString) <> '' then
      begin
            if  trim(QcClaRNC.FieldByName('DsContro').AsString) <> '' then
            begin
                  if not( QcClaRNC.State in [dsInsert]) then
                  begin
                        QcClaRNC.Edit;
                  end;
                  QcClaRNC.FieldByName('NrNivel').AsInteger     := TrvClaRnc.Selected.Level;
                  if TrvClaRnc.Selected.Level <> 1 then
                  begin
                        QcClaRNC.FieldByName('DsContro').AsString :=
                                      SepPorCaracter(QcClaRNC.FieldByName('DsContro').AsString,'.');
                  end;
                  QcClaRNC.Post;
                  QcClaRNC.BrApplyUpdates;
                  TrvClaRnc.Selected.Text := QcClaRNC.FieldByName('DsContro').AsString + ' '
                                                        + QcClaRNC.FieldByName('DsClaRNC').AsString;
                  TrvClaRnc.Enabled    := true;
                  BtnCancelar.Visible  := false;
                  BtnSalvar.Visible    := false;
            end else
            begin
                  MessageDlg('Favor informar controle da RNC!', mtWarning, [MbOk], 0);
            end;
      end else
      begin
           MessageDlg('Favor informar descrição da classificação da RNC!', mtWarning, [MbOk], 0);
      end;
end;

procedure TCad0012.PopExcluiClick(Sender: TObject);
var lNdPai : TTreeNode;
begin
      if MessageDlg('Confirma a exclusão desta opção da Classificação da RNC?', mtConfirmation,                                                          [mbYes, mbNo], 0) = IdYes then
      begin
            QcClaRNC.Delete;
            QcClaRNC.BrApplyUpdates;
            TrvClaRnc.Selected.Delete;
      end;
end;

procedure TCad0012.FormCreate(Sender: TObject);
var lDsDomini : TStrings;
    lVrDomini : TStrings;
    lNdNovo   : TTreeNode;
begin
      inherited;
      DmSrvApl.BrvDicionario.AtributoDominioValores('Q001', 'SnReClan', lDsDomini, lVrDomini);
      DBCSnReClan.Items.Text  := lDsDomini.Text;
      DBCSnReClan.Values.Text := lVrDomini.Text;
      New(gNdNode);
      lNdNovo := TrvClaRnc.Items.AddChildObject(TrvClaRnc.Selected,'Classificação', gNdNode);
      PString(lNdNovo.Data)^ := '0';
      lNdNovo.ImageIndex     := 1;
      lNdNovo.SelectedIndex  := 1;
      TrvClaRnc.Selected     := lNdNovo;
      MontarNivelMenu(lNdNovo,0);
      BtnCancelar.Visible     := false;
      BtnSalvar.Visible     := false;
end;

procedure TCad0012.PopIncluiClick(Sender: TObject);
var lNrMenPai : Integer;
    lNdNovo   : TTreeNode;
begin
      if StrToInt(PString(TrvClaRnc.Selected.Data)^) > 0 then
      begin
            lNrMenPai := StrToInt(PString(TrvClaRnc.Selected.Data)^)
      end else
      begin
            lNrMenPai :=0;
      end;
      New(gNdNode);
      lNdNovo := TrvClaRnc.Items.AddChildObject(TrvClaRnc.Selected,'Novo Class RNC', gNdNode);

      PString(lNdNovo.Data)^ := '0';
      lNdNovo.ImageIndex     := 1;
      lNdNovo.SelectedIndex  := 1;
      TrvClaRnc.Selected     := lNdNovo;
      glNdNovo               := lNdNovo;
      QcClaRNC.Append;
      QcClaRNC.FieldByName('CdClaRNC').AsInteger   := 99999;
      if lNrMenPai > 0 then
      begin
            QcClaRNC.FieldByName('CdClaPai').AsInteger   := lNrMenPai;
      end;
      TrvClaRnc.Selected.Level ;
      TrvClaRnc.Enabled    := false;
      BtnCancelar.Visible := true;
      BtnSalvar.Visible := true;
end;

procedure TCad0012.PopClaRncPopup(Sender: TObject);
var pNdSelect : TTreeNode;
begin
      if StrToInt(PString(TrvClaRnc.Selected.Data)^) <> 1 then
      begin
            PopClaRnc.Tag := 0;
            if not TrvClaRnc.Selected.HasChildren then
            begin
                  pNdSelect := TrvClaRnc.Selected;
                  MontarNivelMenu(nil,StrToInt(PString(TrvClaRnc.Selected.Data)^));
                  TrvClaRnc.Selected        := pNdSelect;
                  if not TrvClaRnc.Selected.HasChildren then
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
      end else
      begin
            PopClaRnc.Tag     := 1;
            PopInclui.Enabled := True;
            PopExclui.Enabled := False;
      end;
end;


procedure TCad0012.QcClaRNCAfterEdit(DataSet: TDataSet);
begin
      TrvClaRnc.Enabled   := false;
      BtnCancelar.Visible := true;
      BtnSalvar.Visible   := true;
end;

procedure TCad0012.QcClaRNCBeforePost(DataSet: TDataSet);
begin

      if QcClaRNC.State in [dsInsert]  then
      begin
            QcClaRNC.FieldByName('CdClaRNC').AsInteger :=
                              (DmSrvApl.BrvDicionario.ProxNumeroSequencial('Q001', 'CDCLARNC'));
      end;
      PString(TrvClaRnc.Selected.Data)^ := QcClaRNC.FieldByName('CdClaRNC').AsString;
end;

procedure TCad0012.SbtCancelClick(Sender: TObject);
begin
      QcClaRNC.Cancel;
end;

procedure TCad0012.TrvClaRncChanging(Sender: TObject; Node: TTreeNode;
  var AllowChange: Boolean);
begin
      if Node.AbsoluteIndex > 0 then
      begin
            if QcClaRNC.State in [dsInsert, dsEdit] then
            begin
                  QcClaRNC.Cancel;
            end;
            QcClaRNC.Close;
            QcClaRNC.BrParams.Clear;
            QcClaRNC.BrParams.Add('<%CdClaRNC%>;' + PString(Node.Data)^);
            QcClaRNC.Open;
            PnlMenu.Visible := True;
      end else
      begin
            PnlMenu.Visible := False;
      end;
end;

procedure TCad0012.DBEDsControExit(Sender: TObject);
begin
      PercorrerTreeNode(DBEDsContro.Text);
end;

procedure TCad0012.PercorrerTreeNode(NodText:String);
var SnodText: string;
    TTreeNodeAux : TTreeNode;
begin
      SnodText := '';
      if (TrvClaRnc.Selected <> nil) and (TrvClaRnc.Selected.Text <> '') then
      begin
            TTreeNodeAux := TrvClaRnc.Selected;
      end else
      begin
            TTreeNodeAux := nil;
      end;
      if NodText = '' then
      begin
            while TTreeNodeAux <> nil do
            begin
                  if(TrvClaRnc.Selected.Text <> 'Classificação') and (TTreeNodeAux.Text <>
                                                                               'Classificação') then
                  begin
                        BrvString1.Split(TTreeNodeAux.Text,' ');
                        SnodText := BrvString1.BrSplitLista[0] + '.' + SnodText;
                        TTreeNodeAux := TTreeNodeAux.Parent;
                  end else
                  begin
                        TTreeNodeAux := TTreeNodeAux.Parent;
                  end;
            end;
            delete(SnodText,length(SnodText),1);
            DBEDsContro.Text := SnodText;
      end else
      begin
            while TTreeNodeAux <> nil do
            begin
                  TTreeNodeAux := TTreeNodeAux.Parent;
                  if(TrvClaRnc.Selected.Text <> 'Classificação') and (TTreeNodeAux.Text <>
                                                                               'Classificação') then
                  begin
                        BrvString1.Split(TTreeNodeAux.Text,' ');
                        SnodText := BrvString1.BrSplitLista[0] + '.' + SnodText;
                  end else
                  begin
                        TTreeNodeAux := TTreeNodeAux.Parent;
                  end;
            end;
            DBEDsContro.Text := SnodText +''+ NodText;
      end;
end;


procedure TCad0012.TrvClaRncDblClick(Sender: TObject);
begin
      if not TrvClaRnc.Selected.HasChildren then
      begin
            MontarNivelMenu(nil,StrToInt(PString(TrvClaRnc.Selected.Data)^));
      end;
end;

procedure TCad0012.TrvClaRncMouseDown(Sender: TObject; Button: TMouseButton; Shift: TShiftState; X,
  Y: Integer);
begin
      inherited;
      PercorrerTreeNode('');
end;

initialization
      RegisterClass(TCad0012);

finalization
      UnRegisterClass(TCad0012);

end.
