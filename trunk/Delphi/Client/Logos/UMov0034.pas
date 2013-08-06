unit UMov0034;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  Mask, BrvCustomMaskEdit, BrvEditDate, StdCtrls, BrvBitBtn, BrvRetCon, BrvCustomEdit, BrvEditNum,
  Grids, BrvDbGrids, BrvDbGrid, DB, DBClient, DBCtrls, BrvDbEdit, BrvClientDataSet,
  BrvServerProgress, BrvEdit;

type
  TMov0034 = class(TMov0000)
    PnlFiltros: TPanel;
    LblEmpres: TLabel;
    EdtCdEmpres: TBrvEditNum;
    LblDsEmpres: TBrvRetCon;
    BtnPesquisa: TBrvBitBtn;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    EdtDtVenIni: TBrvEditDate;
    EdtDtVenFin: TBrvEditDate;
    EdtCdTomado: TBrvEditNum;
    LblRsTomado: TBrvRetCon;
    DbgLsCTRC: TBrvDbGrid;
    Panel1: TPanel;
    DsT002: TDataSource;
    Label1: TLabel;
    Label6: TLabel;
    Panel2: TPanel;
    BtnGravar: TBrvBitBtn;
    BtnCancelar: TBrvBitBtn;
    DbgHistoric: TBrvDbGrid;
    CpT002: TBrvClientDataSet;
    Label7: TLabel;
    CpT021: TBrvClientDataSet;
    DsT021: TDataSource;
    EdtCdCtrc: TBrvEditNum;
    CcParams: TClientDataSet;
    CcParamsXmlQ004: TMemoField;
    CcParamsNmForm: TStringField;
    BrvServerProgress: TBrvServerProgress;
    BrvListParam: TBrvListParam;
    LblLgMotivo: TLabel;
    EdtDsMotivo: TEdit;
    EdtDtAgenda: TBrvEditDate;
    EdtDtPrecal: TBrvEditDate;
    PopCtrc: TPopupMenu;
    Detalhar1: TMenuItem;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnPesquisaClick(Sender: TObject);
    procedure BtnCancelarClick(Sender: TObject);
    procedure DbgLsCTRCDblClick(Sender: TObject);
    procedure CpT002AfterScroll(DataSet: TDataSet);
    procedure CpT002BeforeScroll(DataSet: TDataSet);
    procedure BtnGravarClick(Sender: TObject);
    procedure EdtDsMotivoChange(Sender: TObject);
    procedure EdtDtPrecalChange(Sender: TObject);
    procedure EdtDtAgendaChange(Sender: TObject);
    procedure Detalhar1Click(Sender: TObject);
  private
    function  MontarParametro : String;
    procedure LimparCampos;
    procedure AtualizarDados;
    procedure DetalharCTRC;
  public
    procedure CarregarFormShowModal(pCdEmpres : Integer; pCdTomado : Integer;
                                    pDtEntIni : String;  pDtEntFim : String;
                                    pCdCtrc   : Integer);
  end;

implementation

uses UClaSrv, UDmSrvApl, UDmTra;

var gSnShow : Boolean;

{$R *.dfm}

procedure TMov0034.BtnCancelarClick(Sender: TObject);
begin
      inherited;
      if MessageDlg('Deseja realmente cancelar?', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
      begin
            LimparCampos;
      end;
end;

procedure TMov0034.BtnGravarClick(Sender: TObject);
var lSrvAdm   : TSDmAdmClient;
    lDsResult : String;
    lCpT002   : TClientDataSet;
begin
      inherited;
      try
          if(CpT002.State in [dsEdit,dsInsert]) then
          begin
                AtualizarDados;
          end;

          lCpT002 := TClientDataSet.Create(Self);
          lCpT002.FieldDefs.Clear;
          lCpT002.FieldDefs.Add('CdEmpres', ftInteger,  0);
          lCpT002.FieldDefs.Add('DsModeNF', ftString,   3);
          lCpT002.FieldDefs.Add('NrSeriNF', ftString,   6);
          lCpT002.FieldDefs.Add('CdCTRC',   ftInteger,  0);
          lCpT002.FieldDefs.Add('DtAgenda', ftDate,     0);
          lCpT002.FieldDefs.Add('DtPreCal', ftDate,     0);
          lCpT002.FieldDefs.Add('DsMotivo', ftString, 100);
          lCpT002.CreateDataSet;

          CpT002.DisableControls;
          CpT021.DisableControls;
          CpT002.First;
          while not CpT002.Eof do
          begin
                if ((CpT002.FieldByName('DtPrecal').Value <>
                     CpT002.FieldByName('DtPrecal').OldValue)  or
                    (CpT002.FieldByName('DtAgenda').Value <>
                     CpT002.FieldByName('DtAgenda').OldValue)) then
                begin
                      lCpT002.Append;
                      lCpT002.FieldByName('CdEmpres').AsInteger :=
                                                           CpT002.FieldByName('CdEmpres').AsInteger;
                      lCpT002.FieldByName('DsModeNF').AsString  :=
                                                           CpT002.FieldByName('DsModeNF').AsString;
                      lCpT002.FieldByName('NrSeriNF').AsString  :=
                                                           CpT002.FieldByName('NrSeriNF').AsString;
                      lCpT002.FieldByName('CdCTRC'  ).AsInteger :=
                                                           CpT002.FieldByName('CdCTRC'  ).AsInteger;
                      lCpT002.FieldByName('DtAgenda').AsString  :=
                                                           CpT002.FieldByName('DtAgenda').AsString;
                      lCpT002.FieldByName('DtPreCal').AsString  :=
                                                           CpT002.FieldByName('DtPreCal').AsString;
                      lCpT002.FieldByName('DsMotivo').AsString  :=
                                                           CpT002.FieldByName('DsMotivo').AsString;
                      lCpT002.Post;
                end;
                CpT002.Next;
          end;

          if (lCpT002.RecordCount > 0) then
          begin
                if (CcParams.Active) then
                begin
                      CcParams.EmptyDataSet;
                      CcParams.Close;
                end;

                CcParams.CreateDataSet;
                CcParams.Append;
                CcParams.FieldByName('XmlT002').AsString := lCpT002.XMLData;
                CcParams.FieldByName('NmForm' ).AsString := Self.Name;
                CcParams.Post;

                try
                    BrvServerProgress.Start(Self.Caption, 'Gravando informações', 100, 10);
                    lSrvAdm   := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                    lDsResult := lSrvAdm.GravarRevisoesDataEntrega(DmSrvApl.BrvDicionario.Credencial,
                                                                                     CcParams.Data);
                finally
                    BrvServerProgress.Stop;
                end;

                DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

                //EnviarEmailDestinatario;
                MessageDlg('Gravado com sucesso!!!', mtInformation, [mbok], 0);
          end else
          begin
                MessageDlg('Não houve alteração nos registros!', mtWarning, [mbok], 0);
          end;
      finally
          CpT002.EnableControls;
          CpT021.EnableControls;
          CpT002.First;
          if (lSrvAdm <> nil) then
          begin
                FreeAndNil(lSrvAdm);
                LimparCampos;
          end;
      end;
end;

procedure TMov0034.DbgLsCTRCDblClick(Sender: TObject);
begin
      inherited;
      DetalharCTRC;
end;

procedure TMov0034.Detalhar1Click(Sender: TObject);
begin
      inherited;
      DetalharCTRC;
end;

procedure TMov0034.DetalharCTRC;
begin
      inherited;
      DmTra.VisualizarConhecimento(CpT002);
end;

procedure TMov0034.BtnPesquisaClick(Sender: TObject);
begin
      if EdtCdEmpres.BrAsInteger > 0 then
      begin
            try
                BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);

                CpT002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(252, MontarParametro,
                                                                                         Self.Name);
            finally
                BrvServerProgress.Stop;
            end;

            if (CpT002.RecordCount > 0) then
            begin
                  CpT021.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(258,
                                                                    MontarParametro, Self.Name);
                  PnlFundo.Visible            := True;
                  PnlFiltros.Enabled          := False;
                  BtnPesquisa.Enabled         := False;
                  EdtCdEmpres.Color           := $00DDE2E3;
                  EdtCdEmpres.BrVisibleButton := False;
                  EdtCdTomado.Color           := $00DDE2E3;
                  EdtCdTomado.BrVisibleButton := False;
                  EdtDtVenIni.Color           := $00DDE2E3;
                  EdtDtVenFin.Color           := $00DDE2E3;
                  EdtCdCtrc.Color             := $00DDE2E3;
                  CpT002.First;
                  gSnShow := True;
            end else
            begin
                  if (Self.FormStyle = fsNormal) then
                  begin
                        gSnShow := False;
                        MessageDlg('Não é possível reprogramar este conhecimento!', mtInformation,
                                                                                         [mbok], 0);
                  end else
                  begin
                        MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
                  end;
            end;
      end else
      begin
            EdtCdEmpres.SetFocus;
            raise Exception.Create('É obrigatório informar uma Empresa!');
      end;
end;

procedure TMov0034.CarregarFormShowModal(pCdEmpres : Integer; pCdTomado : Integer;
                                         pDtEntIni : String;  pDtEntFim : String;
                                         pCdCtrc   : Integer);
begin
      FormStyle := fsNormal;
      Visible   := False;
      Position  := poMainFormCenter;
      EdtCdEmpres.BrAsInteger := pCdEmpres;
      EdtCdCtrc.BrAsInteger   := pCdCtrc;

      if Trim(pDtEntIni) <> '' then
      begin
            EdtDtVenIni.BrAsDate := StrToDate(pDtEntIni);
      end;

      if Trim(pDtEntFim) <> '' then
      begin
            EdtDtVenFin.BrAsDate := StrToDate(pDtEntFim);
      end;

      if pCdTomado > 0 then
      begin
            EdtCdTomado.BrAsInteger := pCdTomado;
      end;

      gSnShow             := False;
      BtnCancelar.Visible := False;
      BorderIcons         := [biSystemMenu];
      BtnPesquisaClick(nil);
      if (gSnShow) then
      begin
            ShowModal;
            if (CpT002.Active) then
            begin
                  CpT002.EmptyDataSet;
                  CpT002.Close;
            end;
            if (CpT021.Active) then
            begin
                  CpT021.EmptyDataSet;
                  CpT021.Close;
            end;
            if (CcParams.Active) then
            begin
                  CcParams.EmptyDataSet;
                  CcParams.Close;
            end;
      end;
end;

procedure TMov0034.CpT002AfterScroll(DataSet: TDataSet);
begin
      inherited;
      EdtDtAgenda.Text := CpT002.FieldByName('DtAgenda').AsString;
      EdtDtPrecal.Text := CpT002.FieldByName('DtPrecal').AsString;

      if ((CpT002.FieldByName('DtAgenda').AsString <> '')    and
          (CpT002.FieldByName('DtAgenda').OldValue <> Null)) then
      begin
            EdtDtAgenda.Color   := $00DDE2E3;
            EdtDtAgenda.Enabled := False;
      end else
      begin
            EdtDtAgenda.Color   := clWhite;
            EdtDtAgenda.Enabled := True;
      end;

      EdtDsMotivo.Text := CpT002.FieldByName('DsMotivo').Text;

      CpT021.Filtered := False;
      CpT021.Filter   := ' CdEmpres = '   + CpT002.FieldByName('CdEmpres').AsString +   ' and' +
                         ' DsModeNF = ''' + CpT002.FieldByName('DsModeNF').AsString + ''' and' +
                         ' NrSeriNF = ''' + CpT002.FieldByName('NrSeriNF').AsString + ''' and' +
                         ' CdCTRC = '     + CpT002.FieldByName('CdCTRC'  ).AsString;
      CpT021.Filtered := True;
end;

procedure TMov0034.CpT002BeforeScroll(DataSet: TDataSet);
begin
      inherited;
      AtualizarDados;
end;

procedure TMov0034.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0034.EdtDsMotivoChange(Sender: TObject);
begin
      inherited;
      LblLgMotivo.Caption := IntToStr(Length(EdtDsMotivo.Text)) + '/100';

      if not (CpT002.State in [dsEdit,dsInsert]) then
      begin
            CpT002.ReadOnly := False;
            CpT002.Edit;
      end;
end;

procedure TMov0034.EdtDtAgendaChange(Sender: TObject);
begin
      inherited;
      if (CpT002.FieldByName('DtAgenda').AsString <> EdtDtAgenda.BrAsDataSQL) then
      begin
            if ((EdtDtPrecal.BrDataVazia) or (EdtDtPrecal.BrDataValida) or
                (EdtDtPrecal.BrAsDate < EdtDtAgenda.BrAsDate)) then
            begin
                  EdtDtPrecal.Text := EdtDtAgenda.Text;
            end;
      end;

      if ((EdtDtAgenda.BrDataValida) and
          (CpT002.FieldByName('DtAgenda').AsString <> EdtDtAgenda.BrAsDataSQL)) then
      begin
            if not (CpT002.State in [dsEdit,dsInsert]) then
            begin
                  CpT002.ReadOnly := False;
                  CpT002.Edit;
            end;
      end;
end;

procedure TMov0034.EdtDtPrecalChange(Sender: TObject);
begin
      inherited;
      if ((EdtDtPrecal.BrDataValida) and (EdtDtAgenda.BrDataValida) and
          (EdtDtPrecal.BrAsDate < EdtDtAgenda.BrAsDate)) then
      begin
            EdtDtPrecal.Clear;
            EdtDtPrecal.SetFocus;
            raise Exception.Create('A data reprogramada deve ser maior ou igual à data agendada!');
      end;

      if ((EdtDtPrecal.BrDataValida) and
          (CpT002.FieldByName('DtPrecal').AsString <> EdtDtPrecal.BrAsDataSQL)) then
      begin
            if not (CpT002.State in [dsEdit,dsInsert]) then
            begin
                  CpT002.ReadOnly := False;
                  CpT002.Edit;
            end;
      end;
end;

function TMov0034.MontarParametro : String;
begin
      Result := '<%CdEmpres%>;' + EdtCdEmpres.Text + #13 + '<%DsComWhe%>;';

      if EdtCdTomado.BrAsInteger > 0 then
      begin
            Result := Result + ' and CdTomado = ' + EdtCdTomado.Text;
      end;

      if not EdtDtVenIni.BrDataVazia then
      begin
            Result := Result + ' and Coalesce(DtAgenda, Coalesce(DtPreCal, DtPreEnt)) >= <$hh"' +
                                                                      EdtDtVenIni.Text + '"hh$>';
      end;

      if not EdtDtVenFin.BrDataVazia then
      begin
            Result := Result + ' and Coalesce(DtAgenda, Coalesce(DtPreCal, DtPreEnt)) <= <$hh"' +
                                                                      EdtDtVenFin.Text + '"hh$>';
      end;

      if EdtCdCtrc.BrAsInteger > 0 then
      begin
            Result := Result + ' and CdCtrc = ' + EdtCdCtrc.Text;
      end;
end;

procedure TMov0034.LimparCampos;
begin
      PnlFundo.Visible            := False;
      PnlFiltros.Enabled          := True;
      BtnPesquisa.Enabled         := True;
      EdtCdEmpres.BrAsInteger     := 0;
      EdtCdEmpres.BrValidarEntrada;
      EdtCdEmpres.Color           := clWhite;
      EdtCdEmpres.BrVisibleButton := True;
      EdtCdTomado.Color           := clWhite;
      EdtCdTomado.BrVisibleButton := True;
      EdtCdTomado.BrAsInteger     := 0;
      EdtCdTomado.BrValidarEntrada;
      EdtDtVenIni.Color           := clWhite;
      EdtDtVenIni.Text            := '';
      EdtDtVenFin.Color           := clWhite;
      EdtDtVenFin.Text            := '';
      EdtCdCtrc.Color             := clWhite;
      EdtCdCtrc.Text              := '';
      EdtCdEmpres.SetFocus;
end;

procedure TMov0034.AtualizarDados;
begin
      if ((CpT002.FieldByName('DtPrecal').AsString <> EdtDtPrecal.BrAsDataSQL) or
          (CpT002.FieldByName('DtAgenda').AsString <> EdtDtAgenda.BrAsDataSQL) or
          (CpT002.FieldByName('DsMotivo').AsString <> EdtDsMotivo.Text)) then
      begin
            if (EdtDsMotivo.Text <> '') then
            begin
                  if not (CpT002.State in [dsEdit,dsInsert]) then
                  begin
                        CpT002.ReadOnly := False;
                        CpT002.Edit;
                  end;

                  if ((EdtDtPrecal.BrDataValida) and (EdtDtAgenda.BrDataValida) and
                      (EdtDtPrecal.BrAsDate < EdtDtAgenda.BrAsDate)) then
                  begin
                        EdtDtPrecal.Clear;
                        EdtDtPrecal.SetFocus;
                        raise Exception.Create(
                                    'A data reprogramada deve ser maior ou igual à data agendada!');
                  end;

                  if ((not EdtDtAgenda.BrDataVazia) and (EdtDtAgenda.BrDataValida)) then
                  begin
                        CpT002.FieldByName('DtAgenda').AsString := EdtDtAgenda.BrAsDataSQL;
                  end else
                  begin
                        if ((not EdtDtAgenda.BrDataVazia) and (not EdtDtAgenda.BrDataValida)) then
                        begin
                              EdtDtAgenda.SetFocus;
                              raise Exception.Create('Verifique a data agendada informada!');
                        end;
                  end;

                  if ((not EdtDtPrecal.BrDataVazia) and (EdtDtPrecal.BrDataValida)) then
                  begin
                        CpT002.FieldByName('DtPrecal').AsString := EdtDtPrecal.BrAsDataSQL;
                  end else
                  begin
                        if ((not EdtDtPrecal.BrDataVazia) and (not EdtDtPrecal.BrDataValida)) then
                        begin
                              EdtDtAgenda.SetFocus;
                              raise Exception.Create('Verifique a data reprogramada informada!');
                        end;
                  end;

                  if ((EdtDtPrecal.BrDataVazia) and (EdtDtAgenda.BrDataVazia)) then
                  begin
                        EdtDtAgenda.SetFocus;
                        raise Exception.Create(
                               'O motivo só pode ser informado se uma das datas houver alteração!');
                  end else
                  begin
                        CpT002.FieldByName('DsMotivo').AsString := EdtDsMotivo.Text;
                  end;

                  CpT002.Post;
                  CpT002.ReadOnly := True;

            end else
            begin
                  if ((CpT002.FieldByName('DtPrecal').AsString <> EdtDtPrecal.BrAsDataSQL)  or
                      (CpT002.FieldByName('DtAgenda').AsString <> EdtDtAgenda.BrAsDataSQL)) then
                  begin
                        EdtDsMotivo.SetFocus;
                        raise Exception.Create('É obrigatório informar o Motivo da Alteração!');
                  end;
            end;
      end;
end;

initialization
      RegisterClass(TMov0034);

finalization
      UnRegisterClass(TMov0034);

end.
