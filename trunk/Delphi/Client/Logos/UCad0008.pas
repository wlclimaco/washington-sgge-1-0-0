unit UCad0008;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, ComCtrls, StdCtrls,
  BrvBitBtn, DBCtrls, BrvDBComboListBox, BrvDbEdit, Mask, Menus, DB, DBClient,
  BrvClientDataSet, UClaSrv, BrvDbRetCon, Grids, DBGrids, BrvGeraRelatorio, BrvString,
  BrvExport, BrvListParam, ImgList, xmldom, XMLIntf, msxmldom, XMLDoc;

type
  TCad0008 = class(TCad0000)
    PnlRodape: TPanel;
    BotExpand: TBitBtn;
    BotColaps: TBitBtn;
    PnlConta: TPanel;
    Splitter1: TSplitter;
    TrvPlano: TTreeView;
    LblClassi: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    LblPrefix: TLabel;
    Label1: TLabel;
    DBENrClassi: TDBEdit;
    DBENrConta: TDBEdit;
    DBENmConta: TDBEdit;
    BrvDbEdit1: TBrvDbEdit;
    DBCBSnRecLan: TDBCheckBox;
    DBCBSnBalPat: TDBCheckBox;
    DBCBSnConCen: TDBCheckBox;
    Label8: TLabel;
    CmbCdNatCon: TBrvDBComboListBox;
    DBRTpNatOpe: TDBRadioGroup;
    DBCBSnRatear: TDBCheckBox;
    Label5: TLabel;
    Label9: TLabel;
    BntAltRat: TBitBtn;
    BtnGravar: TBrvBitBtn;
    BtnCancel: TBrvBitBtn;
    BotExclui: TBrvBitBtn;
    MKENrClassi: TMaskEdit;
    LblSulfix: TLabel;
    CbxNumAut: TCheckBox;
    PopPlano: TPopupMenu;
    Novo1: TMenuItem;
    nOvaconta1: TMenuItem;
    N1: TMenuItem;
    Excluir1: TMenuItem;
    CCItensPlano: TBrvClientDataSet;
    DsItensPlano: TDataSource;
    QCClassifica: TBrvClientDataSet;
    QcProxConta: TBrvClientDataSet;
    BrvDBRetCon1: TBrvDBRetCon;
    TblTemp: TClientDataSet;
    TblTempCdPlaCon: TIntegerField;
    TblTempNrClassi: TStringField;
    TblTempNmConta: TStringField;
    TblTempPcRateio: TFloatField;
    PnlRateio: TPanel;
    DBGRateio: TDBGrid;
    Panel1: TPanel;
    Panel2: TPanel;
    Label6: TLabel;
    Label7: TLabel;
    SttTtRateio: TStaticText;
    BntOkRate: TBitBtn;
    QPPlaConRateio: TBrvClientDataSet;
    DsTemp: TDataSource;
    BrvDBRetCon2: TBrvDBRetCon;
    BrvBitBtn1: TBrvBitBtn;
    BrvGerRel: TBrvGeraRelatorio;
    BrvExport: TBrvExport;
    CdsImprim: TClientDataSet;
    CdsImprimDsPlano: TMemoField;
    Label2: TLabel;
    BrvDbEdit2: TBrvDbEdit;
    Label10: TLabel;
    BrvDbEdit3: TBrvDbEdit;
    BrvDBRetCon4: TBrvDBRetCon;
    BrvDBRetCon3: TBrvDBRetCon;
    procedure Novo1Click(Sender: TObject);
    procedure nOvaconta1Click(Sender: TObject);
    procedure Excluir1Click(Sender: TObject);
    procedure BotExcluiClick(Sender: TObject);
    procedure PopPlanoPopup(Sender: TObject);
    procedure BtnCancelClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure TrvPlanoChange(Sender: TObject; Node: TTreeNode);
    procedure BtnGravarClick(Sender: TObject);
    procedure DsItensPlanoStateChange(Sender: TObject);
    procedure BotExpandClick(Sender: TObject);
    procedure BotColapsClick(Sender: TObject);
    procedure TblTempAfterInsert(DataSet: TDataSet);
    procedure TblTempAfterPost(DataSet: TDataSet);
    procedure TblTempBeforeDelete(DataSet: TDataSet);
    procedure TblTempBeforeEdit(DataSet: TDataSet);
    procedure BntAltRatClick(Sender: TObject);
    procedure BntOkRateClick(Sender: TObject);
    procedure BrvBitBtn1Click(Sender: TObject);
  private
    { Private declarations }
    gSnInclui : Boolean;
    gSnAltera : Boolean;
    gNrConAnt : String;
    gNodeInc  : TTreenode;
    gSnRateio : Boolean;
    gSnAbrir  : Boolean;
    gSnIncTmp : Boolean;
    function AceitaInclusao(pNrClassi: String): Boolean;
    procedure IncluirConta(pNrClassi: string);
    procedure ReorganizarClassificacao(pNrClassi, pNrNivIni: String);
    function ExecutarSql(pDsSql: String): Integer;
    procedure AbrirItensPlano(pNrClassi: String);
    function ProximaContaContabil(pNrPlano: Integer): Integer;
    procedure CadastrarRateio;
    procedure EncontrarParentes(var pNrClassi, pNrNivPai, pNrNivAtu, pNrNivFil: String;
                                var pNrPosAtu: byte);
    function MesmoNivel(pNrClassi, pNrPai: String; pNrAtual: Byte): Boolean;
    function NivelAtual(pNrClassi: String): String;
  public
    { Public declarations }
    gVarNode   : PString;
    gNrPlano   : Integer;
    gEditMask  : String;
    procedure CarregarPlanoDeContas;
  end;

var
  Cad0008: TCad0008;

implementation

uses UDmSrvApl, UDmCtb;

{$R *.dfm}

procedure TCad0008.FormCreate(Sender: TObject);
var lDsDomini : String;
    lVrDomini : String;
begin
      inherited;

      gNrConAnt := '';
      gSnInclui := False;
      gSnAltera := False;

      DmSrvApl.BrvDicionario.CarregarDominiosDoAtributo('B002', 'CdNatcon',
                                                                lVrDomini, lDsDomini);
      CmbCdNatCon.Items.CommaText   := lDsDomini;
      CmbCdNatCon.Values.CommaText  := lVrDomini;

      BtnGravar.Enabled    := False;
      BtnCancel.Enabled    := False;
      BotExclui.Enabled    := False;
      gSnInclui            := False;
      gSnAltera            := False;
      gNrConAnt            := '';

      TrvPlanoChange(nil,nil);
end;


procedure TCad0008.PopPlanoPopup(Sender: TObject);
begin
      if  (gSnInclui) or (gSnAltera) then
      begin
            BtnCancelClick(nil);
      end;

      if (TrvPlano.Items.Count > 0) then
      begin
            Novo1.Enabled := AceitaInclusao(PString(TrvPlano.Selected.Data)^);

            if  Novo1.Enabled then
            begin
                  Novo1.Enabled := not DBCBSnRatear.Checked;
            end;
      end else
      begin
            Novo1.Enabled := False;
      end;
end;

function TCad0008.AceitaInclusao(pNrClassi : String) : Boolean;
var lNrClaAux : String;
    lNrPosFim : byte;
begin
      result  :=  False;

      while (length(pNrClassi) > 0) and (not Result) do
      begin
            lNrPosFim := pos('.', pNrClassi);

            if  lNrPosFim > 0 then
            begin
                  lNrClaAux := copy(pNrClassi, 1, lNrPosFim -1);
                  Delete(pNrClassi, 1, lNrPosFim);
            end else
            begin
                  lNrClaAux := pNrClassi;
                  pNrClassi := '';
            end;

            if  StrToInt(lNrClaAux) = 0 then
            begin
                  Result := True;
            end;
      end;
end;

procedure TCad0008.TblTempAfterInsert(DataSet: TDataSet);
begin
      if  Not gSnIncTmp then
      begin
            TblTemp.Cancel;
      end;
end;

procedure TCad0008.TblTempAfterPost(DataSet: TDataSet);
begin
      SttTtRateio.caption := FormatFloat('##0.00', StrToFloat(SttTtRateio.Caption) +
                                                                 TblTempPCRATEIO.AsFloat);
end;

procedure TCad0008.TblTempBeforeDelete(DataSet: TDataSet);
begin
      Abort;
end;

procedure TCad0008.TblTempBeforeEdit(DataSet: TDataSet);
begin
      SttTtRateio.caption := FormatFloat('##0.00', StrToFloat(SttTtRateio.Caption) -
                                                                 TblTempPCRATEIO.AsFloat);
end;

procedure TCad0008.TrvPlanoChange(Sender: TObject; Node: TTreeNode);
begin
      if TrvPlano.Tag = 0 then
      begin
            if  not gSnInclui then
            begin
                  DBENrConta.Enabled := False;
            end;

            if  (gSnInclui) or (gSnAltera) then
            begin
                  BtnCancelClick(nil);
            end else
            begin
                  if TrvPlano.Items.Count > 0 then
                  begin
                        if (PString(TrvPlano.Selected.Data)^ <> gNrConAnt) and
                           (not gSnAbrir) then
                        begin
                              gNodeInc     :=  TrvPlano.Selected;
                              gNrConAnt    :=  PString(TrvPlano.Selected.Data)^;

                              AbrirItensPlano(PString(TrvPlano.Selected.Data)^);

                              if  not CCItensPlano.IsEmpty then
                              begin
                                    BotExclui.Enabled := True;
                              end;

                              DBCBSnRatear.Enabled :=  not TrvPlano.Selected.HasChildren;
                        end;

                        BntAltRat.Visible    :=  DBCBSnRatear.Checked;
                        gSnRateio            :=  DBCBSnRatear.Checked;
                        PnlConta.Enabled     :=  True;
                  end else
                  begin
                        PnlConta.Enabled     := False;
                  end;
            end;
      end;
end;

procedure TCad0008.AbrirItensPlano(pNrClassi : String);
begin
      CCItensPlano.Close;
      CCItensPlano.BrParams.Clear;
      CCItensPlano.BrParams.Add('<%NrPlano%>;'  + IntToStr(gNrPlano));
      CCItensPlano.BrParams.Add('<%NrClassi%>;' + pNrClassi);
      CCItensPlano.Open; // código 73
end;

procedure TCad0008.BntAltRatClick(Sender: TObject);
begin
      CadastrarRateio;
end;

procedure TCad0008.BntOkRateClick(Sender: TObject);
begin
      if  TblTemp.State in [DsInsert, DsEdit] then
      begin
            TblTemp.Post;
      end;

      if  SttTtRateio.caption = '100,00' then
      begin
            TblTemp.First;

            while not TblTemp.Eof do
            begin
                  AbrirItensPlano(TblTemp.FieldByName('NrClassi').AsString);
                  if  not CCItensPlano.Eof then
                  begin
                        CCItensPlano.Edit;
                        CCItensPlano.FieldByName('PcRateio').AsFloat :=
                                                  TblTemp.FieldByName('PcRateio').AsFloat;
                        CCItensPlano.Post;
                        CCItensPlano.ApplyUpdates(0);
                  end;

                  TblTemp.Next;
            end;

            PnlRateio.Visible  := False;
            TrvPlano.Enabled   := True;
            PnlRodape.Enabled  := True;
            TblTemp.Close;
            gNrConAnt          := '';
            TrvPlanoChange(nil, nil);
      end else
      begin
            raise Exception.Create('Total das contas rateadas está diferente de 100,00%');
      end;
end;

procedure TCad0008.CadastrarRateio;
var lNrNivFil : String;
    lNrNivAtu : String;
    lNrClassi : String;
    lNrNivPai : String;
    lNrNivFim : String;
    lNrPosAtu : byte;
    lDsParams : String;
begin
      TblTemp.Close;
      TblTemp.CreateDataSet;

      lNrClassi   := CCItensPlano.FieldByName('NrClassi').AsString;

      EncontrarParentes(lNrClassi, lNrNivPai, lNrNivAtu, lNrNivFil, lNrPosAtu);

      lNrNivFim            := IntToStr(StrToInt(lNrNivPai) + 1);
      gSnIncTmp            := True;
      SttTtRateio.caption := '0,00';


      lDsParams := '<%NrPlano%>;'  + IntToStr(gNrPlano)  + #13 +
                   '<%NrClaIni%>;' + lNrClassi + lNrNivPai + '.' + #13 +
                   '<%NrClaFim%>;' + lNrClassi + lNrNivFim + '.';

      QPPlaConRateio.Close;
      QPPlaConRateio.Data := DmSrvApl.BrvDicionario.
                                             RetornaDadosSqlCadastro(76, lDsParams, Name);

      while not QPPlaConRateio.Eof do
      begin
            if MesmoNivel(QPPlaConRateio.FieldByName('NRCLASSI').AsString,
                                                                lNrNivPai, lNrPosAtu) then
            begin
                  TblTemp.Append;
                  TblTempCdPlaCon.AsInteger := gNrPlano;
                  TblTempNRCLASSI.AsString  :=
                                  QPPlaConRateio.FieldByName('NrClassi').AsString;
                  TblTempNMCONTA.AsString   :=
                                  QPPlaConRateio.FieldByName('NmConta').AsString;
                  TblTempPCRATEIO.AsFloat   :=
                                  QPPlaConRateio.FieldByName('PcRateio').AsFloat;
                  TblTemp.Post;
            end;

            QPPlaConRateio.Next;
      end;

      QPPlaConRateio.Close;

      if  not TblTemp.IsEmpty then
      begin
            TblTemp.First;

            if  TblTemp.RecordCount = 1 then
            begin
                  TblTemp.Edit;
                  TblTempPCRATEIO.AsFloat := 100;
                  TblTemp.Post;
                  BntOkRateClick(nil);
            end else
            begin
                  PnlRateio.Align    := AlClient;
                  PnlRateio.Visible  := True;
                  TrvPlano.Enabled   := False;
                  gSnIncTmp          := False;
                  PnlRodape.Enabled  := False;
            end;
      end;
end;

function TCad0008.MesmoNivel(pNrClassi: String; pNrPai : String; pNrAtual: Byte): Boolean;
var lNrNivFil : String;
    lNrNivAtu : String;
    lNrNivPai : String;
    lNrPosAtu : byte;
begin
      EncontrarParentes(pNrClassi, lNrNivPai, lNrNivAtu, lNrNivFil, lNrPosAtu);

      if  (lNrNivPai = pNrPai) and
          (lNrPosAtu = pNrAtual) then
      begin
            Result := True;
      end else
      begin
            Result := False;
      end;
end;

procedure TCad0008.EncontrarParentes(var pNrClassi : String; var pNrNivPai : String;
                                     var pNrNivAtu : String; var pNrNivFil : String;
                                     var pNrPosAtu : Byte);
var lSnPai   : Boolean;
    lSnAtual : Boolean;
    lNoNivel : Byte;
begin
      lSnPai    := False;
      lSnAtual  := False;
      pNrPosAtu := 1;
      lNoNivel  := Length(pNrClassi);

      while (not lSnPai) and (lNoNivel > 0) do
      begin
            if  pNrClassi[lNoNivel] = '.' then
            begin
                  if  StrToInt(pNrNivPai) <> 0 then
                  begin
                        if lSnAtual then
                        begin
                              lSnPai := True;
                        end else
                        begin
                              lSnAtual    := True;
                              pNrNivAtu   := pNrNivPai;
                              pNrNivPai   :=  '';
                              Delete(pNrClassi, lNoNivel, 1);
                        end;
                  end else
                  begin
                        if  not lSnAtual then
                        begin
                              inc(pNrPosAtu);
                              pNrNivFil := pNrNivPai;
                        end;

                        pNrNivPai    :=  '';
                        Delete(pNrClassi, lNoNivel, 1);
                  end;
            end else
            begin
                  pNrNivPai    := pNrClassi[lNoNivel] + pNrNivPai;
                  Delete(pNrClassi, lNoNivel, 1);
            end;

            dec(lNoNivel);
      end;
end;

procedure TCad0008.BotColapsClick(Sender: TObject);
begin
      TrvPlano.FullCollapse;
end;

procedure TCad0008.BotExcluiClick(Sender: TObject);
var lNrClassi : String;
    lNrNivAtu : String;
    lNrNivFim : String;
    lNrNivDel : String;
    lSnPai    : Boolean;
    lTmClassi : byte;
    lDsSql    : String;
begin
      if MessageDlg('Confirma a exclusão da conta?',
                                           mtConfirmation, [mbYes, mbNo], 0) = IdYes then
      begin
            lSnPai    := False;
            lNrClassi := CCItensPlano.FieldByName('NrClassi').AsString;
            lTmClassi := Length(lNrClassi);

            while (not lSnPai) and (lTmClassi > 0) do
            begin
                  if  lNrClassi[lTmClassi] = '.' then
                  begin
                        if  StrToInt(lNrNivAtu) <> 0 then
                        begin
                              lSnPai := True;
                        end else
                        begin
                              lNrNivAtu    :=  '';
                              Delete(lNrClassi, lTmClassi, 1);
                        end;
                  end else
                  begin
                        lNrNivAtu    := lNrClassi[lTmClassi] + lNrNivAtu;
                        Delete(lNrClassi, lTmClassi, 1);
                  end;

                  dec(lTmClassi);
            end;

            lNrNivFim := IntToStr(StrToInt(lNrNivAtu) + 1);

            if  Length(lNrNivFim) > Length(lNrNivAtu) then
            begin
                  lNrNivFim  := lNrNivAtu + '.1';
            end;

            if TrvPlano.Selected.HasChildren then
            begin
                  lNrNivDel := lNrNivFim;

                  while Length(lNrNivDel) < Length(lNrNivAtu) do
                  begin
                        lNrNivDel := '0' + lNrNivDel;
                  end;

                  lDsSql := 'Delete From B002 ' +
                            ' Where NrPlano = ' + IntToStr(gNrPlano) + '  and ' +
                            ' NrClassi >= ' + #39 + lNrClassi + lNrNivAtu + #39 + ' and ' +
                            ' NrClassi <  ' + #39 + lNrClassi + lNrNivDel + #39;
            end else
            begin
                  lDsSql := 'Delete From B002 ' +
                            ' Where NrPlano = ' + IntToStr(gNrPlano)    + ' and ' +
                            ' NrClassi = '      + #39 +
                                      CCItensPlano.FieldByName('NrClassi').AsString + #39;
            end;

            ExecutarSql(lDsSql);
            ReorganizarClassificacao(lNrClassi, lNrNivAtu);

            TrvPlano.Visible := False;
            TrvPlano.Items.Clear;

            gSnAbrir := True;

            CarregarPlanoDeContas;
            gSnAbrir := False;

            PnlConta.Enabled := False;
            TrvPlano.Visible := True;

            if TrvPlano.Items.Count <= 0 then
            begin
                  PnlConta.Enabled := False;
            end;
      end;
end;

procedure TCad0008.CarregarPlanoDeContas;
var lConexao     : TSDmConClient;
    lDsResult    : String;
    lDsPlano     : String;
begin
      lConexao := TSDmConClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lDsPlano := lConexao.AbrirPlanoContas(DmSrvApl.BrvDicionario.Credencial,
                                                lDsResult, gNrPlano, 1, 0, 0);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
          //BrvExport.MontarTreeView(lDsPlano, TrvPlano);
          BrvExport.XmlToTreeView(lDsPlano, TrvPlano);
          BotExpandClick(nil);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCad0008.BotExpandClick(Sender: TObject);
begin
      TrvPlano.FullExpand
end;

procedure TCad0008.BrvBitBtn1Click(Sender: TObject);
begin
      if not CdsImprim.Active then
      begin
            CdsImprim.CreateDataSet;
      end else
      begin
            CdsImprim.EmptyDataSet;
      end;

      CdsImprim.Append;
      CdsImprim.FieldByName('DsPlano').AsString := BrvExport.ExportarTreeView(TrvPlano);
      CdsImprim.Post;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-= Inserindo parâmetros de configuração do relatório
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      BrvGerRel.IniciarRelatorio('RLC0003', 'C', Name);

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-= Inserindo parâmetros que serão inseridos na query para geração do relatório
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      BrvGerRel.InserirParametroSQL('NrPlano', IntToStr(gNrPlano));
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      BrvGerRel.ImprimirRelatorio;
end;

function TCad0008.ExecutarSql(pDsSql : String) : Integer;
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      lConexao := TSDmRWClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          Result := lConexao.ExecutarInstrucaoSql(DmSrvApl.BrvDicionario.Credencial,
                                                  lDsResult, pDsSql);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          MessageDlg('Instrução executada com sucesso!', MtInformation, [mbOk], 0);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCad0008.ReorganizarClassificacao(pNrClassi : String; pNrNivIni : String);
var lNrNivel  : String;
    lNrPosIni : Integer;
    lNrPosNiv : Integer;
    lNrIndice : Integer;
    lNrNewCla : String;
    lNrClaAtu : String;
    lNrNivAtu : String;
    lDsZero   : String;
begin
      lNrNivel   :=  pNrNivIni;
      lNrPosIni  :=  Length(pNrClassi);

      lDsZero := '';

      for lNrIndice := 1 to Length(lNrNivel) do
      begin
            lDsZero := lDsZero + '0';
      end;

      QCClassifica.Close;
      QCClassifica.BrParams.Clear;
      QCClassifica.BrParams.Add('<%NrPlano%>;'  + IntToStr(gNrPlano));
      QCClassifica.BrParams.Add('<%NrClaIni%>;' + pNrClassi + pNrNivIni);
      QCClassifica.BrParams.Add('<%NrClaFim%>;' + pNrClassi + '9');
      QCClassifica.Open;

      while not QCClassifica.Eof do
      begin
            lNrNewCla := QCClassifica.FieldByName('NrClassi').AsString;
            lNrNivAtu := Copy(QCClassifica.FieldByName('NrClassi').AsString,
                                                        lNrPosIni + 1, Length(pNrNivIni));

            for lNrPosNiv := 1 to Length(lNrNivel) do
            begin
                  lNrNewCla[lNrPosIni + lNrPosNiv] := lNrNivel[lNrPosNiv];
            end;

            QCClassifica.Edit;
            QCClassifica.FieldByName('NrClassi').AsString := lNrNewCla;
            QCClassifica.Post;
            QCClassifica.ApplyUpdates(0);

            QCClassifica.Next;

            lNrClaAtu := Copy(QCClassifica.FieldByName('NrClassi').AsString,
                                                        1, lNrPosIni + Length(pNrNivIni));

            if lNrClaAtu <> (pNrClassi + lNrNivAtu) then
            begin
                  lNrNivel := FormatFloat(lDsZero, StrToInt(lNrNivel) + 1);
            end;
      end;
end;

procedure TCad0008.BtnCancelClick(Sender: TObject);
begin
      CCItensPlano.Cancel;

      BtnCancel.Enabled     :=  False;
      BtnGravar.Enabled     :=  False;
      BotExclui.Enabled     :=  True;
      LblPrefix.Visible     :=  False;
      LblSulfix.Visible     :=  False;
      MKENrClassi.Visible   :=  False;
      DBENrClassi.Visible   :=  True;

      if  (gSnInclui) or (gSnAltera) then
      begin
            gSnAltera := False;
            gNrConAnt := '';

            if  gSnInclui then
            begin
                  gSnInclui := False;
                  TrvPlano.Selected := gNodeInc;
                  TrvPlano.Selected.Delete;
            end else
            begin
                  TrvPlanoChange(nil, nil);
            end;
      end;

      if TrvPlano.Items.Count <= 0 then
      begin
            PnlConta.Enabled := False;
      end;
end;

function TCad0008.ProximaContaContabil(pNrPlano: Integer): Integer;
begin
      QcProxConta.Close;
      QcProxConta.BrParams.Clear;
      QcProxConta.BrParams.Add('<%NrPlano%>;' + IntToStr(pNrPlano));
      QcProxConta.Open;
      Result := QcProxConta.FieldByName('NrProCon').AsInteger;
      QcProxConta.Edit;
      QcProxConta.FieldByName('NrProCon').AsInteger := Result + 1;
      QcProxConta.Post;
      QcProxConta.ApplyUpdates(0);
end;

procedure TCad0008.BtnGravarClick(Sender: TObject);
var lSnInclui : Boolean;
    lStRateio : String;
begin
      if  CCItensPlano.FieldByName('NmConta').AsString = '' then
      begin
            raise Exception.Create('Informe o nome da conta');
      end;

      if gSnInclui then
      begin
            CCItensPlano.FieldByName('NrPlano').AsInteger := gNrPlano;
            CCItensPlano.FieldByName('NrConta').AsInteger :=
                                                           ProximaContaContabil(gNrPlano);
            CCItensPlano.FieldByName('NrClassi').AsString := LblPrefix.Caption  +
                                                             MKENrClassi.Text  +
                                                             LblSulfix.Caption;
      end;

      lSnInclui          := gSnInclui;
      gSnInclui          := False;
      gSnAltera          := False;
      gNrConAnt          := '';
      TrvPlano.Selected := gNodeInc;

      try
          try
              if  not DBCBSnRatear.Checked then
              begin
                    CCItensPlano.FieldByName('PcRateio').AsFloat := 0;
              end;

              CCItensPlano.Post;
              CCItensPlano.ApplyUpdates(0);

              lStRateio   := '';
              if CCItensPlano.FieldByName('SnRatear').AsString = 'S' then
              begin
                    lStRateio := ' * ';
              end;

              TrvPlano.Selected.Text := NivelAtual(
                                         CCItensPlano.FieldByName('NrClassi').AsString) +
                                         CCItensPlano.FieldByName('NmConta').AsString +
                                         lStRateio;

              PString(TrvPlano.Selected.Data)^ :=
                                           CCItensPlano.FieldByName('NrClassi').AsString;

              if ((lSnInclui) and (DBCBSnRatear.Checked) ) or
                 (gSnRateio <> DBCBSnRatear.Checked ) then
              begin
                    CadastrarRateio;
              end;

              TrvPlanoChange(nil, nil);
          except
              TrvPlano.Selected.Delete;
          end;

      finally
          BtnGravar.Enabled                := False;
          BtnCancel.Enabled                := False;
          LblPrefix.Visible                := False;
          LblSulfix.Visible                := False;
          MKENrClassi.Visible              := False;
          DBENrClassi.Visible              := True;
      end;
end;

function TCad0008.NivelAtual(pNrClassi : String) : String;
var lSnFim    : Boolean;
    lNrPosFim : Byte;
    lNrClaAtu : String;
begin
      lSnFim    := False;
      Result   := '';

      while not lSnFim do
      begin
            lNrPosFim   :=  Pos('.', pNrClassi);

            if  lNrPosFim <> 0 then
            begin
                  lNrClaAtu  := copy(pNrClassi, 1, (lNrPosFim -1));
                  Delete(pNrClassi, 1, lNrPosFim);

                  if  StrToInt(lNrClaAtu) = 0 then
                  begin
                        lSnFim := True;
                  end else
                  begin
                        Result := Result + lNrClaAtu + '.';
                  end
            end else
            begin
                  lSnFim := True;

                  if  StrToInt(pNrClassi) <> 0 then
                  begin
                        Result := Result + pNrClassi + '.';
                  end
            end;
      end;

      Result := Result + ' ';
end;

procedure TCad0008.DsItensPlanoStateChange(Sender: TObject);
begin
      if CCItensPlano.State in [DsInsert, DsEdit] then
      begin
            BtnCancel.Enabled := True;
            BtnGravar.Enabled := True;

            if  CCItensPlano.State = DsEdit then
            begin
                  gSnAltera         := True;
                  BotExclui.Enabled := True;
            end;

            BntAltRat.Visible    :=  False;
      end;
end;

procedure TCad0008.Excluir1Click(Sender: TObject);
begin
      BotExcluiClick(nil);
end;

procedure TCad0008.nOvaconta1Click(Sender: TObject);
begin
      new(gVarNode);
      gNodeInc  := TrvPlano.Items.AddObject(nil, 'Nova Conta', gVarNode);
      TrvPlano.Selected := gNodeInc;
      IncluirConta('');
end;

procedure TCad0008.Novo1Click(Sender: TObject);
begin
      new(gVarNode);
      gNodeInc := TrvPlano.Items.AddChildObject(TrvPlano.Selected,
                                                          'Novo item de conta', gVarNode);
      IncluirConta(PString(TrvPlano.Selected.Data)^);
end;

procedure TCad0008.IncluirConta(pNrClassi : string);
var lNrClaAux : String;
    lNrPosFim : byte;
    lDsMascar : String;
begin
      gSnInclui := True;
      gSnRateio := False;

      AbrirItensPlano(pNrClassi);

      CCItensPlano.Append;
      CCItensPlano.FieldByName('SnRecLan').AsString := 'S';
      CCItensPlano.FieldByName('SnRatear').AsString := 'N';
      CCItensPlano.FieldByName('SnBalPat').AsString := 'S';
      CCItensPlano.FieldByName('SNConCen').AsString := 'N';
      CCItensPlano.FieldByName('TpNatOpe').AsString := 'C';
      CCItensPlano.FieldByName('NrClassi').EditMask := gEditMask;
      DBENrConta.Enabled := not CbxNumAut.Checked;

      BtnCancel.Enabled    := True;
      BtnGravar.Enabled    := True;
      BotExclui.Enabled    := False;
      DBCBSnRatear.Enabled := True;
      LblPrefix.Caption    := '';
      LblSulfix.Caption    := '';
      lDsMascar             := CCItensPlano.FieldByName('NrClassi').EditMask;

      if  pNrClassi <> '' then
      begin
            // definindo nova máscara
            lDsMascar := CCItensPlano.FieldByName('NrClassi').EditMask;

            While (pos('.', pNrClassi) > 0) do
            begin
                  lNrPosFim := pos('.', pNrClassi);

                  if  lNrPosFim > 0 then
                  begin
                        lNrClaAux := copy(pNrClassi, 1, lNrPosFim -1);
                        Delete(pNrClassi, 1, lNrPosFim);

                        if  StrToInt(lNrClaAux) <> 0 then
                        begin
                              Delete(lDsMascar, 1, lNrPosFim);

                              if  LblPrefix.Caption = '' then
                              begin
                                    LblPrefix.caption := lNrClaAux + '.';
                                    LblPrefix.Visible := True;
                              end else
                              begin
                                    LblPrefix.caption := LblPrefix.caption +
                                                                 lNrClaAux + '.';
                              end;
                        end else
                        begin
                              if  LblSulfix.Caption = '' then
                              begin
                                    LblSulfix.visible    := True;
                                    LblSulfix.caption    := '.' + pNrClassi;
                                    Delete(lDsMascar, pos('.', lDsMascar),
                                                      Length(lDsMascar));
                              end;
                        end;
                  end;
            end;

            LblPrefix.top        :=  LblClassi.Top;
            LblPrefix.Left       :=  DBENrConta.Left;

            Delete(lDsMascar, pos(';', lDsMascar), Length(lDsMascar));
            MKENrClassi.EditMask :=  lDsMascar + ';1;_';
            MKENrClassi.Width    :=  (Length(lDsMascar) * 8) + 5;
            MKENrClassi.Left     :=  LblPrefix.Left + LblPrefix.Width;
            MKENrClassi.Top      :=  DBENrClassi.Top;
            MKENrClassi.Text     :=  FormatFloat(lDsMascar, gNodeInc.Index + 1);
            MKENrClassi.Visible  :=  True;

            LblSulfix.top        :=  LblClassi.Top;
            LblSulfix.Left       :=  MKENrClassi.Left + MKENrClassi.Width + 5;
      end else
      begin
            lNrPosFim := pos('.', lDsMascar);

            if  lNrPosFim > 0 then
            begin
                  lNrClaaux := copy(lDsMascar, 1, lNrPosFim -1);
                  Delete(lDsMascar, 1, lNrPosFim -1);
                  Delete(lDsMascar, pos(';', lDsMascar), Length(lDsMascar));

                  if  lDsMascar <> '' then
                  begin
                        LblSulfix.visible    := True;
                        LblSulfix.caption    := lDsMascar;
                  end;

                  lDsMascar                 := lNrClaAux;
            end else
            begin
                  Delete(lDsMascar, pos(';', lDsMascar), Length(lDsMascar));
            end;

            MKENrClassi.EditMask := lDsMascar + ';1;_';
            MKENrClassi.Width    :=  (Length(lDsMascar) * 8) + 5;
            MKENrClassi.Left     :=  DBENrClassi.Left;
            MKENrClassi.Top      :=  DBENrClassi.Top;
            MKENrClassi.Text     :=  FormatFloat(lDsMascar, gNodeInc.Index + 1);
            MKENrClassi.Visible  :=  True;

            LblSulfix.top        :=  LblClassi.Top;
            LblSulfix.Left       :=  MKENrClassi.Left + MKENrClassi.Width + 5;
      end;

      TrvPlano.Selected.Expand(true);
      DBENrClassi.Visible  := False;

      DBENmConta.SetFocus;
      PnlConta.Enabled     := True;
end;

end.
