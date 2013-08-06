unit UMov0003;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvBitBtn, BrvEditNum,
  BrvEdit, Mask, BrvEditDate, ComCtrls, Grids, BrvDbGrids, BrvDbGrid, DB, DBClient, BrvClientDataSet,
  DBCtrls, BrvDbEdit, BrvLabel, BrvRetCon, BrvConsulta, BrvDbRetCon, Menus, BrvRelAsc, BrvString,
  ImgList, BrvListParam, BrvCustomEdit;

type
  TMov0003 = class(TMov0000)
    pnlHeader: TPanel;
    pnlBody: TPanel;
    lblAtendimento: TLabel;
    BtnProsse: TBrvBitBtn;
    pnlAtend: TPanel;
    lblDescricao: TLabel;
    EdtDsAtendi: TBrvDbEdit;
    lblnicio: TLabel;
    Label1: TLabel;
    Label2: TLabel;
    EdtDtAbertu: TBrvDbEdit;
    EdtDtFecham: TBrvDbEdit;
    EdtCdEmpres: TBrvDbEdit;
    pnlFooter: TPanel;
    BtnVoltar: TBrvBitBtn;
    BtnGravar: TBrvBitBtn;
    BtnEmail: TBrvBitBtn;
    BrvLabel1: TBrvLabel;
    DsT010: TDataSource;
    QcT010: TBrvClientDataSet;
    DsT011: TDataSource;
    QcT011: TBrvClientDataSet;
    QcT012: TBrvClientDataSet;
    DsT012: TDataSource;
    RedTxAtendi: TDBRichEdit;
    lblTxt: TLabel;
    PnlOcorrencias: TPanel;
    BdgConhec: TBrvDbGrid;
    EdtNotas: TBrvDbEdit;
    BdgOcorrencias: TBrvDbGrid;
    EdtDsEmpr: TBrvDBRetCon;
    Label3: TLabel;
    EdtCdTipAte: TBrvDbEdit;
    EdtDsTipAte: TBrvDBRetCon;
    bbb1: TBrvBitBtn;
    PopConcluir: TPopupMenu;
    ConcluirOcorrncia1: TMenuItem;
    EdtNrAtendi: TBrvEditNum;
    Label4: TLabel;
    EdtCdCarga: TBrvDbEdit;
    EdtDsDescar: TBrvDBRetCon;
    RelAsc: TBrvRelAsc;
    DbEdtCdVeicul: TBrvDBRetCon;
    Label5: TLabel;
    DbEdtNmMotori: TBrvDBRetCon;
    DbEdtCdMotori: TBrvDBRetCon;
    DbEdtdsVeicul: TBrvDBRetCon;
    Label6: TLabel;
    PopLog: TPopupMenu;
    VerLog1: TMenuItem;
    Label7: TLabel;
    DbEdtCdUsuari: TBrvDbEdit;
    DbEdtNmUsuari: TBrvDBRetCon;
    BtnCancelar: TBrvBitBtn;
    Label8: TLabel;
    BrvDBRetCon1: TBrvDBRetCon;
    ReprogramarDatadeEntrega1: TMenuItem;
    procedure FormCreate(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure DsT010DataChange(Sender: TObject; Field: TField);
    procedure BtnProsseClick(Sender: TObject);
    procedure BtnGravarClick(Sender: TObject);
    procedure QcT010NewRecord(DataSet: TDataSet);
    procedure QcT011NewRecord(DataSet: TDataSet);
    procedure QcT012NewRecord(DataSet: TDataSet);
    procedure BtnVoltarClick(Sender: TObject);
    procedure BdgConhecColumns0BrOnBeforeConsul(Sender: TObject);
    procedure bbb1Click(Sender: TObject);
    procedure ConcluirOcorrncia1Click(Sender: TObject);
    procedure FormKeyDown(Sender: TObject; var Key: Word; Shift: TShiftState);
    procedure BtnEmailClick(Sender: TObject);
    procedure BdgConhecColumns1BrOnBeforeConsul(Sender: TObject);
    procedure QcT011ReconcileError(DataSet: TCustomClientDataSet; E: EReconcileError;
      UpdateKind: TUpdateKind; var Action: TReconcileAction);
    procedure VerLog1Click(Sender: TObject);
    procedure BtnCancelarClick(Sender: TObject);
    procedure ReprogramarDatadeEntrega1Click(Sender: TObject);
  private
    procedure DesabilitarControles;
    procedure HabilitarControles;
    procedure MontaRelatorio;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0003: TMov0003;

implementation

uses BrvDb, UDmSrvApl, UDmTra;

{$R *.dfm}

procedure TMov0003.DesabilitarControles();
begin
      QcT010.DisableControls;
      QcT011.DisableControls;
      QcT012.DisableControls;
end;

procedure TMov0003.HabilitarControles();
begin
      QcT010.EnableControls;
      QcT011.EnableControls;
      QcT012.EnableControls;
end;


procedure TMov0003.bbb1Click(Sender: TObject);
var lMensagem : String;
    lRetorno  : Integer;
    NrIndice  : Integer;

begin
      inherited;

      lMensagem :=  'Deseja encerrar todas Ocorrências e finalizar o Atendimento ?';
      lRetorno  :=  MessageDlg(lMensagem, mtWarning, [mbYes,mbNo], 0);

      if lRetorno = 6 then
      begin
            QcT012.First;
            while Not QcT012.Eof do
            begin
                  if QcT012.FieldByName('STOCORRE').AsString = 'A' then
                  begin
                        QcT012.Edit;
                        QcT012.FieldByName('STOCORRE').AsString     :=  'F';
                        QcT012.FieldByName('DTCONCLU').AsDateTime   :=
                                                              DmSrvApl.BrvDicionario.DataHoraServer;
                  end;
                  QcT012.Next;
            end;

            QcT010.Edit;
            QcT010.FieldByName('DTFECHAM').AsDateTime := DmSrvApl.BrvDicionario.DataHoraServer;
            QcT010.FieldByName('StAtendi').AsString   := 'F';
            BtnGravar.Click;
            BtnCancelar.Click;
      end;

end;

procedure TMov0003.BdgConhecColumns0BrOnBeforeConsul(Sender: TObject);
begin
      inherited;
      (Sender as TColumn).BrParams.Clear;
      (Sender as TColumn).BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0003.BdgConhecColumns1BrOnBeforeConsul(Sender: TObject);
begin
      inherited;

      if TColumn(Sender).BrConsulta = 22 then
      begin
            TColumn(Sender).BrParams.Clear;

            if QcT010.FieldByName('CdCarga').AsInteger > 0 then
            begin
                  TColumn(Sender).BrParams.Add('<%CdCarga%>; and T008.CdCarga = ' +
                                                   QcT010.FieldByName('CdCarga').AsString);
            end else
            begin
                  TColumn(Sender).BrParams.Add('<%CdCarga%>;');
            end;
      end;
end;

procedure TMov0003.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      PnlBody.Visible :=  False;

//      BtnGravar.Click;

      QcT010.Close;
      QcT011.Close;
      QcT012.Close;

      PnlHeader.Enabled :=  True;

      EdtNrAtendi.BrAsInteger :=  0;
      EdtNrAtendi.SetFocus;
      EdtNrAtendi.SelectAll;

end;

procedure TMov0003.BtnCancelarClick(Sender: TObject);
var lMensagem : String;
    lRetorno  : Integer;
    NrIndice  : Integer;
begin
      inherited;

      if (QcT010.FieldByName('StAtendi').AsString = 'A') then
      begin
            lMensagem :=  'ATENÇÃO!!!' + Chr(13) + 'Deseja Cancelar o Atendimento ?';
            lRetorno  :=  MessageDlg(lMensagem, mtWarning, [mbYes,mbNo], 0);

            if lRetorno = 6 then
            begin
                  QcT012.First;
                  while Not QcT012.Eof do
                  begin
                        if QcT012.FieldByName('STOCORRE').AsString = 'A' then
                        begin
                              QcT012.Edit;
                              QcT012.FieldByName('STOCORRE').AsString     :=  'C';
                              QcT012.FieldByName('DTCONCLU').AsDateTime   :=
                                                              DmSrvApl.BrvDicionario.DataHoraServer;
                        end;
                        QcT012.Next;
                  end;

                  QcT010.Edit;
                  QcT010.FieldByName('DTFECHAM').AsDateTime :=
                                                              DmSrvApl.BrvDicionario.DataHoraServer;
                  QcT010.FieldByName('StAtendi').AsString   := 'C';
                  BtnGravar.Click;
                  BtnVoltar.Click;
            end;
      end else
      begin
            MessageDlg('Atendimento não esta aberto!', mtWarning, [mbok], 0);
      end;
end;

procedure TMov0003.BtnEmailClick(Sender: TObject);
begin
      inherited;

      MontaRelatorio;
end;

procedure TMov0003.MontaRelatorio();
var   lEmpresa, lCtrc, lDtEmissao, lRemetente, lNmRemetente, lDestinatario, lNmDestinatario : WideString;
      lNrOcorr, lSitOcorr, lDtOcorr, lDtConclu  : WideString;
      lTxAtendimento, lTxOcorr : WideString;
      lIndiceOCorr  : Integer;
begin
      RelAsc.NovoRelatorio(DmSrvApl.BrvDicionario.UserCode, EdtDsEmpr.Text,
                                                            Self.Name , Self.Caption);
      RelAsc.NovaLinha('Nr. ATENDIMENTO : ' + EdtNrAtendi.Text + ' - ' + EdtDsAtendi.Text);
      RelAsc.LinhasFixas(1,132,'-');
      RelAsc.NovaLinha('Empresa         : ' + EdtDsEmpr.Text);
      RelAsc.NovaLinha('Tipo            : ' + EdtDsTipAte.Text);
      RelAsc.NovaLinha('Carga           : ' + EdtDsDescar.Text);

      lTxAtendimento    :=  RedTxAtendi.Text;
      lTxAtendimento    :=  RelAsc.JustificarTexto(lTxAtendimento,80);
      RelAsc.NovaLinha('Detalhes        : '  );
      RelAsc.NovaLinha(lTxAtendimento);

      // Conhecimentos que estão neste atendimento
      RelAsc.LinhasFixas(1,132,'-');
      RelAsc.NovaLinha('CONHECIMENTOS DO ATENDIMENTO          ');
      RelAsc.LinhasFixas(1,132,'-');
      QcT011.First;
      RelAsc.NovaLinha('Emp     CTRC Dt. Emissão  ' +
                       'Remetente                                         ' +
                       'Destinatario                                      ' );
      RelAsc.LinhasFixas(1,132,'-');
      while Not QcT011.Eof do
      begin
            lEmpresa          :=  QcT011.FieldByName('CdEmpres').AsString;
            lCtrc             :=  QcT011.FieldByName('CdCTRC').AsString;
            lDtEmissao        :=  QcT011.FieldByName('DtEmissa').AsString;

            lRemetente        :=  QcT011.FieldByName('CdRemete').AsString;
            lNmRemetente      :=  QcT011.FieldByName('NmRemete').AsString;

            lDestinatario     :=  QcT011.FieldByName('CdDestin').AsString;
            lNmDestinatario   :=  QcT011.FieldByName('NmDestin').AsString;

            lEmpresa          :=  RelAsc.FormatarNumero(lEmpresa,         03, True);
            lCtrc             :=  RelAsc.FormatarNumero(lCtrc,            08, False);
            lDtEmissao        :=  RelAsc.FormatarNumero(lDtEmissao,       10, False);
            lRemetente        :=  RelAsc.FormatarNumero(lRemetente,       08, False);
            lNmRemetente      :=  RelAsc.FormatarStringComAcento(lNmRemetente,     40);
            lDestinatario     :=  RelAsc.FormatarNumero(lDestinatario,    08, False);
            lNmDestinatario   :=  RelAsc.FormatarStringComAcento(lNmDestinatario,  40);

            RelAsc.NovaLinha(lEmpresa       + ' ' + lCtrc           + ' ' + lDtEmissao + ' ' +
                             lRemetente     + ' ' + lNmRemetente    + ' ' +
                             lDestinatario  + ' ' + lNmDestinatario);
            QcT011.Next;
      end;


      // Ocorrências que estão neste atendimento
      RelAsc.LinhasFixas(1,132,'-');
      RelAsc.NovaLinha('OCORRÊNCIAS DO ATENDIMENTO          ');
      RelAsc.LinhasFixas(1,132,'-');
      QcT012.First;
      RelAsc.NovaLinha(' Nº Situação     Dt. Ocorrência      Dt. Conclusão  ' );
      RelAsc.LinhasFixas(1,132,'-');
      lIndiceOCorr  :=  0;
      while Not QcT012.Eof do
      begin
            lIndiceOCorr      :=  lIndiceOCorr  + 1;

            if lIndiceOCorr > 1 then
            begin
                  RelAsc.LinhasFixas(1,132,'.');
            end;

            lNrOcorr          :=  IntToStr(lIndiceOCorr);
            lSitOcorr         :=  QcT012.FieldByName('StOcorre').AsString;
            lDtOcorr          :=  QcT012.FieldByName('DtOcorre').AsString;
            lDtConclu         :=  QcT012.FieldByName('DtConclu').AsString;
            lTxOcorr          :=  QcT012.FieldByName('TxOcorre').AsString;

            lNrOcorr          :=  RelAsc.FormatarNumero(lNrOcorr,         03, True);
            lDtOcorr          :=  RelAsc.FormatarNumero(lDtOcorr,         19, False);
            lDtConclu         :=  RelAsc.FormatarNumero(lDtConclu,        19, False);
            lTxOcorr          :=  RelAsc.JustificarTexto(lTxOcorr, 80);


            if lSitOcorr = 'A' then
            begin
                  lSitOcorr :=  'Aberto';
            end else
            begin
                  lSitOcorr :=  'Fechado';
            end;

            lSitOcorr         := RelAsc.FormatarStringComAcento(lSitOcorr,12);

            RelAsc.NovaLinha(lNrOcorr       + ' ' + lSitOcorr + ' ' + lDtOcorr  + ' ' + lDtConclu );
            RelAsc.NovaLinha(lTxOcorr);

            QcT012.Next;
      end;

end;


procedure TMov0003.BtnGravarClick(Sender: TObject);
begin
      if (QcT010.FieldByName('CdEmpres').AsInteger = 0) then
      begin
            raise Exception.Create('Informe a empresa');
      end;

      if (QcT010.FieldByName('CdTipAte').AsInteger = 0) then
      begin
            raise Exception.Create('Informe o tipo');
      end;

      if QcT010.State in [dsEdit, dsInsert] then
      begin
            QcT010.Post;
      end;
      if QcT010.ChangeCount > 0 then
      begin
            QcT010.ApplyUpdates(0);
      end;
      //-------------------------------------------------------------------------
      if QcT011.State in [dsEdit, dsInsert] then
      begin
            QcT011.Post;
      end;
      if QcT011.ChangeCount > 0 then
      begin
            QcT011.ApplyUpdates(0);
      end;
      //-------------------------------------------------------------------------
      if QcT012.State in [dsEdit, dsInsert] then
      begin
            QcT012.Post;
      end;
      if QcT012.ChangeCount > 0 then
      begin
            QcT012.ApplyUpdates(0);
      end;

      ShowMessage('Registros gravados com SUCESSO');
end;

procedure TMov0003.BtnProsseClick(Sender: TObject);
var
  lDsWhere  : String;
begin
      try
          DesabilitarControles;
          lDsWhere  :=  lDsWhere + ' and T010.NrAtendi = ' + EdtNrAtendi.Text;
          QcT010.Close;
          QcT010.BrParams.Clear;
          QcT010.BrParams.Add('<%DsComWhe%>;' + lDsWhere);
          QcT010.Open;

          if ( (EdtNrAtendi.BrAsInteger = 0) or (EdtNrAtendi.Text = '') ) then
          begin
                QcT010.Insert;
                EdtNrAtendi.BrAsInteger :=
                          DmSrvApl.BrvDicionario.ProxNumeroSequencial('T010', 'NRATENDI');
                QcT010.FieldByName('NrAtendi').AsInteger := EdtNrAtendi.BrAsInteger;
                QcT010.FieldByName('CdUsuari').AsInteger := DmSrvApl.BrvDicionario.UserCode;
                QcT010.FieldByName('NmComUsu').AsString  := DmSrvApl.BrvDicionario.UserName;
                QcT010.FieldByName('StAtendi').AsString  := 'A';
          end;

          // Abrindo Conhecimentos
          QcT011.Close;
          lDsWhere :=  '';
          lDsWhere := lDsWhere + ' and T011.NrAtendi = ' + EdtNrAtendi.Text;
          QcT011.BrParams.Clear;
          QcT011.BrParams.Add('<%DsComWhe%>;' + lDsWhere);
          QcT011.Open;

          // Abrindo Ocorrencias
          QcT012.Close;
          lDsWhere :=  '';
          lDsWhere := lDsWhere + ' and T012.NrAtendi = ' + EdtNrAtendi.Text;
          QcT012.BrParams.Clear;
          QcT012.BrParams.Add('<%DsComWhe%>;' + lDsWhere);
          QcT012.Open;
      finally
          PnlBody.Visible :=  True;
          HabilitarControles;
          pnlHeader.Enabled :=  False;
          EdtCdEmpres.SetFocus;
      end;
end;

procedure TMov0003.ConcluirOcorrncia1Click(Sender: TObject);
begin
      inherited;
      QcT012.Edit;
      QcT012.FieldByName('STOCORRE').AsString     :=  'F';
      QcT012.FieldByName('DTCONCLU').AsDateTime   :=  DmSrvApl.BrvDicionario.DataHoraServer;
      BtnGravar.Click;
end;

procedure TMov0003.DsT010DataChange(Sender: TObject; Field: TField);
begin
      if  QcT010.Active then
      begin
            if QcT010.State = dsInactive then
            begin
                  BrvLabel1.Caption :=  'Inativo'
            end;
            if QcT010.State = dsInsert then
            begin
                  BrvLabel1.Caption :=  'Inserindo'
            end;
            if QcT010.State = dsEdit then
            begin
                  BrvLabel1.Caption :=  'Editando '
            end;
      end else
      begin
            BrvLabel1.Caption :=  'ClientDataSet fechado'
      end;
end;

procedure TMov0003.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0003.FormCreate(Sender: TObject);
var lDsValues : String;
    lDsItems  : String;
begin
      inherited;
      EdtDsAtendi.Text          :=  '';

      Top     :=  0;
      Height  :=  640;
      Width   :=  800;

      DmSrvApl.BrvDicionario.CarregarDominiosDoAtributo(
                                                 'T012', 'STOCORRE', lDsValues, lDsItems);


      BdgOcorrencias.Columns.Items[1].PickList.CommaText      :=  lDsItems;
      BdgOcorrencias.Columns.Items[1].BrPickValue.CommaText   :=  lDsValues;

      EdtNrAtendi.BrAsInteger :=  0;

end;

procedure TMov0003.FormKeyDown(Sender: TObject; var Key: Word; Shift: TShiftState);
begin
      inherited;
      if Key = VK_F5 then
      begin
            BdgConhec.SetFocus;
      end;

      if Key = VK_F6 then
      begin
            BdgOcorrencias.SetFocus;
      end;
end;

procedure TMov0003.QcT010NewRecord(DataSet: TDataSet);
begin
      inherited;
      DataSet.FieldByName('DTABERTU').AsDateTime  :=  DmSrvApl.BrvDicionario.DataHoraServer;
      DataSet.FieldByName('DTABERTU').AsDateTime  :=  DmSrvApl.BrvDicionario.DataHoraServer;
end;

procedure TMov0003.QcT011NewRecord(DataSet: TDataSet);
begin
      inherited;
      DataSet.FieldByName('NRATENDI').AsInteger  := EdtNrAtendi.BrAsInteger;
end;

procedure TMov0003.QcT011ReconcileError(DataSet: TCustomClientDataSet; E: EReconcileError;
  UpdateKind: TUpdateKind; var Action: TReconcileAction);
begin
      inherited;
      raise Exception.Create(E.Message);
end;

procedure TMov0003.QcT012NewRecord(DataSet: TDataSet);
begin
      inherited;
      DataSet.FieldByName('NRATENDI').AsInteger     :=  EdtNrAtendi.BrAsInteger;
      DataSet.FieldByName('NROCORRE').AsInteger     :=
                                DmSrvApl.BrvDicionario.ProxNumeroSequencial('T012', 'NROCORRE');
      DataSet.FieldByName('STOCORRE').AsString      :=  'A';
      DataSet.FieldByName('DTOCORRE').AsDateTime    :=  DmSrvApl.BrvDicionario.DataHoraServer;

      QcT012.FieldByName('TXOCORRE').FocusControl;
end;

procedure TMov0003.ReprogramarDatadeEntrega1Click(Sender: TObject);
begin
     inherited;
     DmTra.ReprogramarDataEntrega(QcT011.FieldByName('CdEmpres').AsInteger,
                                  0,
                                  QcT011.FieldByName('DtEmissa').AsString,
                                  QcT011.FieldByName('DtEmissa').AsString,
                                  QcT011.FieldByName('CdCtrc'  ).AsInteger);
end;

procedure TMov0003.VerLog1Click(Sender: TObject);
begin
      VerLog(QcT010, 'T010');
end;

initialization
      RegisterClass(TMov0003);

finalization
      UnRegisterClass(TMov0003);


end.

