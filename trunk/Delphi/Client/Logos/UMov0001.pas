unit UMov0001;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvEditNum,
  Mask, BrvEditDate, BrvBitBtn, BrvEdit, Grids, BrvDbGrids, BrvDbGrid, DB, DBClient,
  BrvClientDataSet, DBCtrls, BrvDbEdit, UClaSrv, ImgList, Menus, BrvListParam, BrvCustomMaskEdit,
  BrvCustomEdit, BrvRetCon, BrvDbRetCon, BrvServerProgress;

type
  TMov0001 = class(TMov0000)
    PnlCabeca: TPanel;
    PnlDados: TPanel;
    PnlSalva: TPanel;
    Label1: TLabel;
    EdtCdEmpres: TBrvEditNum;
    Label47: TLabel;
    EdtCdCtrc: TBrvEditNum;
    Label2: TLabel;
    EdtCdSerie: TBrvEdit;
    BotLocali: TBrvBitBtn;
    Label3: TLabel;
    EdtDtInicio: TBrvEditDate;
    Label4: TLabel;
    EdtDtFinal: TBrvEditDate;
    BotSalvar: TBrvBitBtn;
    BotCancel: TBrvBitBtn;
    DbgDados: TBrvDbGrid;
    DsT002: TDataSource;
    GroupBox1: TGroupBox;
    Panel1: TPanel;
    GroupBox2: TGroupBox;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label10: TLabel;
    Label9: TLabel;
    Label11: TLabel;
    Label13: TLabel;
    Label16: TLabel;
    Label17: TLabel;
    Label22: TLabel;
    Label19: TLabel;
    Label24: TLabel;
    Label12: TLabel;
    Label14: TLabel;
    Label15: TLabel;
    Label18: TLabel;
    Label20: TLabel;
    Label21: TLabel;
    MemObEntreg: TDBMemo;
    EdtDtEntreg: TBrvDbEdit;
    EdtDtEntMot: TBrvDbEdit;
    EdtCdOcorre: TBrvDbEdit;
    EdtDtPreEnt: TBrvDbEdit;
    Panel2: TPanel;
    Label23: TLabel;
    Label43: TLabel;
    Label44: TLabel;
    Label50: TLabel;
    Label48: TLabel;
    Label46: TLabel;
    Label49: TLabel;
    Label51: TLabel;
    Label26: TLabel;
    EdtCdCarga: TBrvEditNum;
    BtnApaDtE: TBrvBitBtn;
    BtnApaDtM: TBrvBitBtn;
    BtnApaDtP: TBrvBitBtn;
    QcT002: TBrvClientDataSet;
    EdtNrRps: TBrvEditNum;
    Label25: TLabel;
    LblDsEmpres: TBrvRetCon;
    BrvDBRetCon1: TBrvDBRetCon;
    BrvDBRetCon2: TBrvDBRetCon;
    BrvDBRetCon3: TBrvDBRetCon;
    BrvDBRetCon4: TBrvDBRetCon;
    BrvDBRetCon5: TBrvDBRetCon;
    BrvDBRetCon6: TBrvDBRetCon;
    BrvDBRetCon7: TBrvDBRetCon;
    BrvDBRetCon8: TBrvDBRetCon;
    BrvDBRetCon9: TBrvDBRetCon;
    BrvDBRetCon10: TBrvDBRetCon;
    BrvDBRetCon11: TBrvDBRetCon;
    BrvDBRetCon12: TBrvDBRetCon;
    BrvDBRetCon13: TBrvDBRetCon;
    BrvDBRetCon14: TBrvDBRetCon;
    BrvDBRetCon15: TBrvDBRetCon;
    BrvDBRetCon16: TBrvDBRetCon;
    BrvDBRetCon17: TBrvDBRetCon;
    BrvDBRetCon18: TBrvDBRetCon;
    BrvDBRetCon19: TBrvDBRetCon;
    BrvDBRetCon20: TBrvDBRetCon;
    BrvDBRetCon21: TBrvDBRetCon;
    BrvDBRetCon22: TBrvDBRetCon;
    BrvDBRetCon23: TBrvDBRetCon;
    Label27: TLabel;
    EdtDsModeNF: TBrvEdit;
    BrvServerProgress: TBrvServerProgress;
    procedure EdtCdCtrcBrOnBeforeConsulta(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BotLocaliClick(Sender: TObject);
    procedure DsT002StateChange(Sender: TObject);
    procedure BotCancelClick(Sender: TObject);
    procedure BotSalvarClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure QcT002BeforeInsert(DataSet: TDataSet);
    procedure BtnApaDtEClick(Sender: TObject);
    procedure BtnApaDtMClick(Sender: TObject);
    procedure BtnApaDtPClick(Sender: TObject);
    procedure ConsistirDados;
    procedure QcT002ReconcileError(DataSet: TCustomClientDataSet; E: EReconcileError;
      UpdateKind: TUpdateKind; var Action: TReconcileAction);
    procedure EdtCdCtrcBrOnAfterConsulta(Sender: TObject);
  private
    procedure HabilitarObjetos(pSnEdita: Boolean);
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0001: TMov0001;

implementation

uses UDmSrvApl, UDmTra;

{$R *.dfm}

procedure TMov0001.BotCancelClick(Sender: TObject);
begin
      QcT002.Cancel;
      HabilitarObjetos(False);

      if EdtCdCtrc.Focused then
      begin
            EdtCdCtrc.SetFocus;
      end;
end;

procedure TMov0001.BotLocaliClick(Sender: TObject);
var lDsWhere : String;
begin
      lDsWhere := '';
      PnlDados.Visible  := False;

      if EdtCdSerie.Text <> '' then
      begin
            lDsWhere := lDsWhere + ' and T002.NrSeriNf = "' + EdtCdSerie.Text + '"';
      end;

      if EdtCdCarga.BrAsInteger > 0 then
      begin
            lDsWhere := lDsWhere + ' and T002.CdCarga = ' + EdtCdCarga.Text;
      end;

      if (not EdtDtInicio.BrDataVazia) and (not EdtDtInicio.BrDataValida) then
      begin
            raise Exception.Create('Data inicial está inválida');
      end;

      if (not EdtDtFinal.BrDataVazia) and (not EdtDtFinal.BrDataValida) then
      begin
            raise Exception.Create('Data final está inválida');
      end;

      if EdtDtFinal.BrAsDate < EdtDtInicio.BrAsDate then
      begin
            raise Exception.Create('Data final deve ser maior ou igual a inicial');
      end;

      if EdtCdCtrc.BrAsInteger <> 0 then
      begin
            lDsWhere := lDsWhere + ' and T002.CdCtrc = ' + EdtCdCtrc.Text;
      end;

      if not EdtDtInicio.BrDataVazia then
      begin
            lDsWhere := lDsWhere + ' and T002.DtEmissa >= "' + EdtDtInicio.BrAsDataSQL + '"';
      end;

      if not EdtDtFinal.BrDataVazia then
      begin
            lDsWhere := lDsWhere + ' and T002.DtEmissa <= "' + EdtDtFinal.BrAsDataSQL + '"';
      end;

      if EdtNrRps.BrAsInteger > 0 then
      begin
            lDsWhere := lDsWhere + ' and F004.NrRps = ' + EdtNrRps.Text;
      end;

      if EdtDsModeNF.Text <> '' then
      begin
            lDsWhere := lDsWhere + ' and T002.DsModeNF = "' + EdtDsModeNF.Text + '"';
      end;

      if Trim(lDsWhere) = '' then
      begin
            if (MessageDlg('Não existem filtros suficientes para uma pesquisa mais efetiva!!!' +
                            Chr(13) + 'Esta operação pode levar alguns minutos...' + Chr(13) +
                           'Deseja continuar?', mtWarning, [mbyes, mbno], 0) = mrno) then
            begin
                  EdtCdEmpres.SetFocus;
                  raise Exception.Create('Informe pelo menos um filtro de pesquisa.');
            end;
      end;

      if EdtCdEmpres.BrAsInteger > 0 then
      begin
            lDsWhere := lDsWhere + ' and T002.CdEmpres = ' + EdtCdEmpres.Text;
      end else
      begin
            lDsWhere := lDsWhere + ' and T002.CdEmpres in (' +
                                     EdtCdEmpres.BrDicionario.CorpCommaCodes +')';
      end;

      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);

          QcT002.Close;
          QcT002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(33,
                                                                  '<%DsComWhe%>;' + lDsWhere, Name);
          QcT002.Open;

          TDateTimeField(QcT002.FieldByName('DtEntreg')).EditMask := '##/##/####';
          TDateTimeField(QcT002.FieldByName('DtEntMot')).EditMask := '##/##/#### ##:##:##';

          PnlDados.Visible := True;

          if EdtDtEntreg.CanFocus then
          begin
                EdtDtEntreg.SetFocus;
          end;
      finally
          BrvServerProgress.Stop;
      end;
end;

procedure TMov0001.BotSalvarClick(Sender: TObject);
var lCdsParam : TClientDataSet;
    lConexao  : TSDmTraClient;
    lDsResult : String;

    lCpCidRen : String;
    lCpCidDes : String;
    lCpCidCon : String;
begin
      ConsistirDados;


      if (QcT002.FieldByName('DtPreEnt').AsDateTime  >=
          QcT002.FieldByName('DtEntreg').AsDateTime) or

          (MessageDlg('Entrega fora do prazo! Deseja realmente continuar',
                       mtConfirmation, [mbYes, mbNo], 0) = mrYes) then
      begin
            if (QcT002.FieldByName('DtEntreg').AsDateTime >
                QcT002.FieldByName('DtEmissa').AsDateTime) or

               (MessageDlg('Data de entrega menor que a data emissão!',
                       mtConfirmation, [mbYes, mbNo], 0) = mrYes) then
            begin
                  lCdsParam := TClientDataSet.Create(Self);
                  lCdsParam.FieldDefs.Clear;
                  lCdsParam.FieldDefs.Add('CdEmpres', ftInteger , 0);
                  lCdsParam.FieldDefs.Add('DsModeNF', ftString  , 3);
                  lCdsParam.FieldDefs.Add('NrSeriNF', ftString  , 6);
                  lCdsParam.FieldDefs.Add('CdCTRC'  , ftInteger , 0);
                  lCdsParam.FieldDefs.Add('DtEntreg', ftDateTime, 0);
                  lCdsParam.FieldDefs.Add('DtEntMot', ftDateTime, 0);
                  lCdsParam.FieldDefs.Add('CdOcorre', ftInteger , 0);
                  lCdsParam.FieldDefs.Add('DtPreEnt', ftDateTime, 0);
                  lCdsParam.FieldDefs.Add('ObEntreg', ftBlob    , 0);
                  lCdsParam.CreateDataSet;


                  lCdsParam.Append;
                  lCdsParam.FieldByName('CdEmpres').AsInteger :=
                                                    QcT002.FieldByName('CdEmpres').AsInteger ;
                  lCdsParam.FieldByName('DsModeNF').AsString  :=
                                                    QcT002.FieldByName('DsModeNF').AsString  ;
                  lCdsParam.FieldByName('NrSeriNF').AsString  :=
                                                    QcT002.FieldByName('NrSeriNF').AsString  ;
                  lCdsParam.FieldByName('CdCTRC'  ).AsInteger :=
                                                    QcT002.FieldByName('CdCTRC'  ).AsInteger ;
                  lCdsParam.FieldByName('DtEntreg').AsDateTime:=
                                                    QcT002.FieldByName('DtEntreg').AsDateTime;
                  lCdsParam.FieldByName('DtEntMot').AsDateTime:=
                                                    QcT002.FieldByName('DtEntMot').AsDateTime;
                  lCdsParam.FieldByName('CdOcorre').AsInteger :=
                                                    QcT002.FieldByName('CdOcorre').AsInteger ;
                  lCdsParam.FieldByName('DtPreEnt').AsDateTime:=
                                                    QcT002.FieldByName('DtPreEnt').AsDateTime;
                  lCdsParam.FieldByName('ObEntreg').Value     :=
                                                    QcT002.FieldByName('ObEntreg').Value;
                  lCdsParam.Post;

                  try
                      lConexao := TSDmTraClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                      lDsResult := lConexao.GravaDataEntregaCTRC(DmSrvApl.BrvDicionario.Credencial,
                                                                 lCdsParam.Data);

                      DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
                  finally
                      FreeAndNil(lConexao);
                      FreeAndNil(lCdsParam);
                  end;

                  QcT002.Post;
                  HabilitarObjetos(False);
                  QcT002.Next;

                  if EdtCdCtrc.CanFocus then
                  begin
                        EdtCdCtrc.SetFocus;
                  end;
            end;
      end;
end;

procedure TMov0001.BtnApaDtEClick(Sender: TObject);
begin
      if QcT002.State <> dsEdit then
      begin
            QcT002.Edit;
      end;

      QcT002.FieldByName('DtEntreg').AsString := '';
end;

procedure TMov0001.BtnApaDtMClick(Sender: TObject);
begin
      inherited;
      if not (QcT002.State = dsEdit) then
      begin
            QcT002.Edit;
      end;

      QcT002.FieldByName('DtEntMot').AsString := '';
end;

procedure TMov0001.BtnApaDtPClick(Sender: TObject);
begin
      inherited;
      if not (QcT002.State = dsEdit) then
      begin
            QcT002.Edit;
      end;

      QcT002.FieldByName('DtPreEnt').AsString := '';
end;

procedure TMov0001.DsT002StateChange(Sender: TObject);
begin
      if (Sender as TDataSource).DataSet.State in [dsEdit] then
      begin
            HabilitarObjetos(True);
      end;
end;

procedure TMov0001.HabilitarObjetos(pSnEdita : Boolean);
begin
      DbgDados.Enabled    := not pSnEdita;
      BotLocali.Enabled   := not pSnEdita;
      EdtCdEmpres.Enabled := not pSnEdita;
      EdtCdSerie.Enabled  := not pSnEdita;
      EdtCdCtrc.Enabled   := not pSnEdita;
      EdtDtInicio.Enabled := not pSnEdita;
      EdtDtFinal.Enabled  := not pSnEdita;
      BotSalvar.Enabled   := pSnEdita;
      BotCancel.Enabled   := pSnEdita;
end;

procedure TMov0001.QcT002BeforeInsert(DataSet: TDataSet);
begin
      Abort;
end;

procedure TMov0001.QcT002ReconcileError(DataSet: TCustomClientDataSet; E: EReconcileError;
  UpdateKind: TUpdateKind; var Action: TReconcileAction);
begin
      inherited;
      raise Exception.Create('E.Message');
end;

procedure TMov0001.EdtCdCtrcBrOnAfterConsulta(Sender: TObject);
begin
      inherited;
      if (EdtCdCtrc.Tag = 1) then
      begin
            EdtCdCtrc.Tag := 0;

            if (StrToIntDef(EdtCdCtrc.Text, 0) = 0) then
            begin
                  MessageDlg('Conhecimento cancelado\inutilizado ou inexistente!!!',
                                                                                mtError, [mbOK], 0);
            end;
      end;
end;

procedure TMov0001.EdtCdCtrcBrOnBeforeConsulta(Sender: TObject);
begin
      if (StrToIntDef(EdtCdCtrc.Text, 0) > 0) then
      begin
            EdtCdCtrc.Tag := 1;
      end;

      EdtCdCtrc.BrParams.Clear;
      if EdtCdEmpres.BrAsInteger > 0 then
      begin
            EdtCdCtrc.BrParams.Add('<%CdEmpres%>; and S004.CdEmpres = ' +
                                                                        EdtCdEmpres.Text);
      end else
      begin
            EdtCdCtrc.BrParams.Add('<%CdEmpres%>;');
      end;
end;

procedure TMov0001.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0001.FormCreate(Sender: TObject);
begin
      inherited;

      EdtDtEntreg.Enabled :=
                  DmSrvApl.BrvDicionario.PermissaoUsuarioAtributo('T002', 'DTENTREG') < 4;

      EdtCdOcorre.Enabled :=
                  DmSrvApl.BrvDicionario.PermissaoUsuarioAtributo('T002', 'CDOCORRE') < 4;

      EdtDtEntMot.Enabled :=
                  DmSrvApl.BrvDicionario.PermissaoUsuarioAtributo('T002', 'DTENTMOT') < 4;

      EdtDtPreEnt.Enabled :=
                  DmSrvApl.BrvDicionario.PermissaoUsuarioAtributo('T002', 'DTPREENT') < 4;

      MemObEntreg.Enabled :=
                  DmSrvApl.BrvDicionario.PermissaoUsuarioAtributo('T002', 'OBENTREG') < 4;
end;

procedure TMov0001.ConsistirDados;
var lDtHoje : TDateTime;
begin
      if ((QcT002.FieldByName('DtEntreg').AsDateTime > 0)  or
          (QcT002.FieldByName('DtEntMot').AsDateTime > 0)) and
          (QcT002.FieldByName('CdOcorre').AsInteger = 0)   then
      begin
            Raise Exception.Create('Informe a ocorrência da entrega!');
      end;

      if (QcT002.FieldByName('DtEntreg').AsDateTime = 0) and
         (QcT002.FieldByName('DtEntMot').AsDateTime = 0) and
         (QcT002.FieldByName('CdOcorre').AsInteger  > 0) then
      begin
            Raise Exception.Create('Informe a da Informe a data de entrega ou do rastreador!');
      end;

      if (QcT002.FieldByName('DtEntreg').Asfloat > 0) then
      begin
            if (DmTra.TipoTransporte(QcT002.FieldByName('CpCidRem').AsString,
                                     QcT002.FieldByName('CpCidDes').AsString,
                                     QcT002.FieldByName('CpCidCon').AsString) = 'I') and
               (QcT002.FieldByName('DtEntreg').AsDateTime <
                QcT002.FieldByName('DtEmissa').AsDateTime) then
            begin
                  Raise Exception.Create(
                        'Data de entrega menor que a data emissão!' + Chr(13) +
                        'Operação não permitida para operações Intermunicipais' + Chr(13));
            end;
      end;

      if QcT002.FieldByName('DtEntreg').AsString <> '' then
      begin
            if (QcT002.FieldByName('DtEntreg').AsDateTime -
                QcT002.FieldByName('DtEmissa').AsDateTime) >= 15 then
            begin
                  ShowMessage('Data de entrega não permitida, ultrapassou o limite de 15 dias!');
            end;

            lDtHoje := DmSrvApl.BrvDicionario.DataServer;

            if QcT002.FieldByName('DtEntreg').AsDateTime > lDtHoje then
            begin
                  Raise Exception.Create('Data de entrega não pode ser maior que a data atual!');
            end;
      end;
end;

initialization
      RegisterClass(TMov0001);

finalization
      UnRegisterClass(TMov0001);

end.
