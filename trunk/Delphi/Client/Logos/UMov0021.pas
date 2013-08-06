unit UMov0021;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  StdCtrls, BrvBitBtn, BrvRetCon, BrvEdit, BrvLabel, BrvEditNum, Grids, BrvDbGrids, BrvDbGrid,
  ComCtrls, BrvConsulta, DB, DBClient, BrvClientDataSet, AppEvnts, BrvCustomEdit;

type
  TMov0021 = class(TMov0000)
    PnlCab: TPanel;
    BrvLabel3: TBrvLabel;
    BrvLabel1: TBrvLabel;
    EdtNrNota: TBrvEditNum;
    EdtCdTitula: TBrvEditNum;
    LblEmpres: TLabel;
    BrvLabel2: TBrvLabel;
    EdtCdEmpres: TBrvEdit;
    LblDsEmpres: TBrvRetCon;
    LblRsTitula: TBrvRetCon;
    CcF013: TBrvClientDataSet;
    CcF014: TBrvClientDataSet;
    DsF014: TDataSource;
    BtnIniciar: TBrvBitBtn;
    EdtChaNFe: TEdit;
    PnlDet: TPanel;
    PgcDet: TPageControl;
    TbsContabil: TTabSheet;
    DbgF014: TBrvDbGrid;
    TbsCtPag: TTabSheet;
    BrvDbGrid2: TBrvDbGrid;
    PnlControl: TPanel;
    BtnGravar: TBrvBitBtn;
    BtnCancel: TBrvBitBtn;
    GroupBox4: TGroupBox;
    LblTtCcPag: TLabel;
    Label41: TLabel;
    Label42: TLabel;
    LblTtContabil: TLabel;
    CcF015: TBrvClientDataSet;
    DsF015: TDataSource;
    ApplicationEvents1: TApplicationEvents;
    Splitter1: TSplitter;
    DbgF017: TBrvDbGrid;
    CcF017: TBrvClientDataSet;
    DsF017: TDataSource;
    BrvLabel4: TBrvLabel;
    LblTtCenCus: TBrvLabel;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnIniciarClick(Sender: TObject);
    procedure BtnCancelClick(Sender: TObject);
    procedure BtnGravarClick(Sender: TObject);
    procedure DbgF014Columns0BrOnAfterConsul(Sender: TObject);
    procedure DbgF014Columns2BrOnBeforeConsul(Sender: TObject);
    procedure DbgF014Columns2BrOnAfterConsul(Sender: TObject);
    procedure DbgF014Columns4BrOnAfterConsul(Sender: TObject);
    procedure CcF014BeforeInsert(DataSet: TDataSet);
    procedure CcF014BeforePost(DataSet: TDataSet);
    procedure CcF014AfterPost(DataSet: TDataSet);
    procedure CcF014AfterDelete(DataSet: TDataSet);
    procedure CcF015AfterDelete(DataSet: TDataSet);
    procedure CcF015AfterPost(DataSet: TDataSet);
    procedure ApplicationEvents1Exception(Sender: TObject; E: Exception);
    procedure CcF015BeforePost(DataSet: TDataSet);
    procedure EdtChaNFeExit(Sender: TObject);
    procedure DbgF017Columns0BrOnAfterConsul(Sender: TObject);
    procedure CcF014AfterScroll(DataSet: TDataSet);
    procedure CcF014AfterInsert(DataSet: TDataSet);
    procedure CcF017AfterInsert(DataSet: TDataSet);
    procedure CcF017AfterPost(DataSet: TDataSet);
    procedure CcF017AfterDelete(DataSet: TDataSet);
  private
    procedure ValidarEntrada;
    procedure TotalizaContabil;
    procedure TotalizaPagar;
    procedure VerificaDados;
    procedure TotalizaCentroCusto;
    { Private declarations }
  public
    { Public declarations }
    gNrLancto : Integer;
  end;

var
  Mov0021: TMov0021;

implementation

uses UDmSrvApl, UClaSrv;

{$R *.dfm}

procedure TMov0021.DbgF014Columns4BrOnAfterConsul(Sender: TObject);
begin
      inherited;
      if (CcF014.FieldByName('CdHistor').AsString = '') then
      begin
            MessageDlg('Informe um histórico válido!', mtError, [mbok], 0);
            Abort;
      end;
end;

procedure TMov0021.DbgF017Columns0BrOnAfterConsul(Sender: TObject);
begin
      inherited;
      if (CcF017.FieldByName('CdCenCus').AsString = '') then
      begin
            MessageDlg('Informe um Centro de Custo válido!', mtError, [mbok], 0);
            Abort;
      end;
end;

procedure TMov0021.DbgF014Columns0BrOnAfterConsul(Sender: TObject);
begin
      inherited;
      if (CcF014.FieldByName('NrPlano').AsString = '') then
      begin
            MessageDlg('Informe um plano válido!', mtError, [mbok], 0);
            Abort;
      end;
end;

procedure TMov0021.DbgF014Columns2BrOnAfterConsul(Sender: TObject);
begin
      inherited;
      if (CcF014.FieldByName('NrConta').AsString = '') then
      begin
            MessageDlg('Informe uma conta válida!', mtError, [mbok], 0);
            Abort;
      end;
end;

procedure TMov0021.DbgF014Columns2BrOnBeforeConsul(Sender: TObject);
begin
      inherited;
      if (CcF014.FieldByName('NrPlano').AsInteger > 0) then
      begin
            DbgF014.Columns[2].BrParams.Clear;
            DbgF014.Columns[2].BrParams.Add('<%NrPlano%>;' +CcF014.FieldByName('NrPlano').AsString);
      end else
      begin
            Raise Exception.Create('Informe um Plano de Contas válido!');
      end;
end;

procedure TMov0021.ApplicationEvents1Exception(Sender: TObject; E: Exception);
begin
      inherited;
      if Pos(UpperCase('key violation'), UpperCase(e.Message)) > 0 then
      begin
            if Pos(UpperCase('LogosApl'), UpperCase(e.Message)) = 0 then
            begin
                  ShowMessage('Verifique a numeração da parcela!');
            end else
            begin
                  MessageDlg(E.Message, mtError, [mbok], 0);
            end;
      end else
      begin
            MessageDlg(E.Message, mtError, [mbok], 0);
      end;
end;

procedure TMov0021.BtnCancelClick(Sender: TObject);
begin
      inherited;
      PgcDet.ActivePageIndex := 0;
      PnlDet.Visible := False;
      PnlCab.Enabled := True;
      EdtChaNFe.Text := '';
      EdtNrNota.Text := '0';
      EdtCdTitula.Text := '0';
      EdtCdEmpres.Text := '0';
      EdtCdEmpres.SetFocus;
end;

procedure TMov0021.VerificaDados;
begin
      if (CcF014.State in [dsEdit, dsInsert]) then
      begin
            CcF014.Post;
      end;

      if (CcF015.State in [dsEdit, dsInsert]) then
      begin
            CcF015.Post;
      end;

      if (CcF014.RecordCount = 0) then
      begin
            raise Exception.Create('Não há lançamentos contabeis!');
      end;

      if (CcF015.RecordCount = 0) then
      begin
            raise Exception.Create('Não há lançamentos de contas a pagar!');
      end;

      if (CcF013.Active) then
      begin
            CcF013.EmptyDataSet;
            CcF013.Close;
      end;

      CcF013.FieldDefs.Clear;
      CcF013.FieldDefs.Add('cdempres', ftinteger , 0);
      CcF013.FieldDefs.Add('cdtitula', ftinteger , 0);
      CcF013.FieldDefs.Add('cdusuins', ftinteger , 0);
      CcF013.FieldDefs.Add('dtinserc', ftdatetime, 0);
      CcF013.FieldDefs.Add('nrchadoc', ftstring  ,44);
      CcF013.FieldDefs.Add('nrnota'  , ftinteger , 0);
      CcF013.FieldDefs.Add('nrprelan', ftinteger , 0);
      CcF013.CreateDataSet;
end;

procedure TMov0021.BtnGravarClick(Sender: TObject);
var lConexao  : TSDmAdmClient;
    lDsResult : AnsiString;
    lNrLancto : Integer;
begin
      inherited;
      if (MessageDlg('Deseja continuar?', mtConfirmation, [mbYes, mbNo], 0) = MrYes) then
      begin
            VerificaDados;

            CcF013.Append;
            CcF013.FieldByName('cdempres').AsInteger := StrToInt(EdtCdEmpres.Text);
            CcF013.FieldByName('cdtitula').AsInteger := StrToInt(EdtCdTitula.Text);
            CcF013.FieldByName('cdusuins').AsInteger := DmSrvApl.BrvDicionario.UserCode;
            CcF013.FieldByName('dtinserc').AsDateTime:= Now();
            CcF013.FieldByName('nrchadoc').AsString  := EdtChaNFe.Text;
            CcF013.FieldByName('nrnota'  ).AsInteger := StrToInt(EdtNrNota.Text);
            CcF013.FieldByName('nrprelan').AsInteger :=
                                    DmSrvApl.BrvDicionario.ProxNumeroSequencial('F013', 'NRPRELAN');
            CcF013.Post;

            try
                CcF014.First;
                CcF014.DisableControls;
                CcF014.AfterScroll := nil;

                CcF015.First;
                CcF015.DisableControls;

                CcF017.First;
                CcF017.DisableControls;

                while not CcF014.Eof do
                begin
                      lNrLancto := CcF014.FieldByName('NrLancto').AsInteger;

                      CcF014.Edit;
                      CcF014.FieldByName('NrLancto').AsInteger :=
                             DmSrvApl.BrvDicionario.ProxNumeroSequencial('F014', 'NRLANCTO');
                      CcF014.FieldByName('nrprelan').AsInteger :=
                                              CcF013.FieldByName('nrprelan').AsInteger;
                      CcF014.Post;

                      CcF017.Filtered := False;
                      CcF017.Filter   := 'NrLancto = ' + IntToStr(lNrLancto);
                      CcF017.Filtered := True;

                      CcF017.First;

                      while not CcF017.Eof do
                      begin
                            CcF017.Edit;
                            CcF017.FieldByName('NrLancCc').AsInteger :=
                                    DmSrvApl.BrvDicionario.ProxNumeroSequencial('F017', 'NRLANCCC');
                            CcF017.FieldByName('NrLancto').AsInteger :=
                                                           CcF014.FieldByName('NrLancto').AsInteger;
                            CcF017.FieldByName('nrprelan').AsInteger :=
                                                           CcF013.FieldByName('nrprelan').AsInteger;
                            CcF017.Post;
                            CcF017.Next;
                      end;

                      CcF014.Next;
                end;

                while not CcF015.Eof do
                begin
                      CcF015.Edit;
                      CcF015.FieldByName('nrprelan').AsInteger :=
                                                     CcF013.FieldByName('nrprelan').AsInteger;
                      CcF015.Post;

                      CcF015.Next;
                end;

                lConexao := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                lDsResult := lConexao.GravarPreLancamentoNFEntrada(
                                                                  DmSrvApl.BrvDicionario.Credencial,
                                                                  CcF013.Data, CcF014.Data,
                                                                  CcF015.Data, CcF017.Data);

                DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

                MessageDlg('Dados gravados com sucesso!', mtInformation, [mbok], 0);
                BtnCancelClick(BtnCancel);
            finally
                CcF014.First;
                CcF014.EnableControls;
                CcF014.AfterScroll := CcF014AfterScroll;

                CcF015.First;
                CcF015.EnableControls;

                CcF017.First;
                CcF017.EnableControls;
                FreeAndNil(lConexao);
            end;
      end;
end;

procedure TMov0021.ValidarEntrada;
begin
      if (StrToIntDef(EdtCdEmpres.Text, 0) =  0) then
      begin
            Raise Exception.Create('Informe a Empresa!');
      end;

      if (StrToIntDef(EdtCdTitula.Text, 0) = 0) then
      begin
            Raise Exception.Create('Informe o Fornecedor!');
      end;

      if (StrToIntDef(EdtNrNota.Text, 0) = 0)  then
      begin
            Raise Exception.Create('Informe a Nota!');
      end;
end;

procedure TMov0021.BtnIniciarClick(Sender: TObject);
begin
      inherited;

      gNrLancto := -1;

      ValidarEntrada;

      if (CcF014.Active) then
      begin
            CcF014.EmptyDataSet;
            CcF014.Close;
      end;

      if (CcF015.Active) then
      begin
            CcF015.EmptyDataSet;
            CcF015.Close;
      end;

      if (CcF017.Active) then
      begin
            CcF017.EmptyDataSet;
            CcF017.Close;
      end;

      CcF014.FieldDefs.Clear;
      CcF014.FieldDefs.Add('nrplano' , ftinteger , 0);
      CcF014.FieldDefs.Add('dsplano' , ftstring  ,50);
      CcF014.FieldDefs.Add('nrconta' , ftinteger , 0);
      CcF014.FieldDefs.Add('nmconta' , ftstring  ,50);
      CcF014.FieldDefs.Add('cdhistor', ftinteger , 0);
      CcF014.FieldDefs.Add('dshistor', ftString  ,50);
      CcF014.FieldDefs.Add('nrlancto', ftinteger , 0);
      CcF014.FieldDefs.Add('nrprelan', ftinteger , 0);
      CcF014.FieldDefs.Add('vrlancto', ftFloat   , 0);
      CcF014.CreateDataSet;

      TFloatField(CcF014.FieldByName('vrlancto')).DisplayFormat := '#0.00';

      CcF015.FieldDefs.Clear;
      CcF015.FieldDefs.Add('NrPreLan', ftinteger , 0);
      CcF015.FieldDefs.Add('NrParcel', ftinteger , 0);
      CcF015.FieldDefs.Add('DtVencto', ftDate    , 0);
      CcF015.FieldDefs.Add('VrDocto' , ftFloat   , 0);
      CcF015.CreateDataSet;

      CcF015.IndexDefs.Clear;
      CcF015.IndexDefs.Add('IdxParcel', 'NrParcel', [ixUnique]);
      CcF015.IndexName := 'IdxParcel';

      TDateField(CcF015.FieldByName('DtVencto')).EditMask := '!99/99/0000;1;_';
      TFloatField(CcF015.FieldByName('VrDocto')).DisplayFormat := '#0.00';

      CcF017.FieldDefs.Clear;
      CcF017.FieldDefs.Add('nrprelan', ftinteger , 0);
      CcF017.FieldDefs.Add('nrlancto', ftinteger , 0);
      CcF017.FieldDefs.Add('nrlancCC', ftinteger , 0);
      CcF017.FieldDefs.Add('cdcencus', ftinteger , 0);
      CcF017.FieldDefs.Add('dscencus', ftString  ,50);
      CcF017.FieldDefs.Add('vrlancto', ftFloat   , 0);
      CcF017.CreateDataSet;

      TFloatField(CcF017.FieldByName('vrlancto')).DisplayFormat := '#0.00';

      PgcDet.ActivePageIndex := 0;
      PnlDet.Visible := True;
      PnlCab.Enabled := False;
      DbgF014.SetFocus;
      DbgF014.SelectedField := CcF014.FindField('NrPlano');
end;

procedure TMov0021.CcF014AfterDelete(DataSet: TDataSet);
begin
      inherited;
      TotalizaContabil;
end;

procedure TMov0021.CcF014AfterInsert(DataSet: TDataSet);
begin
      inherited;
      CcF014.FieldByName('nrlancto').AsInteger := gNrLancto;
      inc(gNrLancto, -1);
end;

procedure TMov0021.CcF014AfterPost(DataSet: TDataSet);

begin
      inherited;
      TotalizaContabil;
end;

procedure TMov0021.CcF014AfterScroll(DataSet: TDataSet);
begin
      inherited;
      if (CcF014.RecordCount > 0) then
      begin
            CcF017.Filtered := False;
            CcF017.Filter   := 'NrLancto = ' + CcF014.FieldByName('NrLancto').AsString;
            CcF017.Filtered := True;
      end else
      begin
            CcF017.Filtered := False;
      end;
end;

procedure TMov0021.TotalizaContabil;
var lVrContabil : Extended;
    lNrRecno : Integer;
begin
      try
          lNrRecno := CcF014.RecNo;
          CcF014.DisableControls;
          CcF014.First;
          lVrContabil := 0;

          while not CcF014.Eof do
          begin
                lVrContabil := lVrContabil + CcF014.FieldByName('VrLancto').AsFloat;
                CcF014.Next;
          end;

          LblTtContabil.Caption := FormatFloat('0.00', lVrContabil);

      finally
          CcF014.RecNo := lNrRecno;
          CcF014.EnableControls;
      end;
end;

procedure TMov0021.TotalizaPagar;
var lVrPagar : Extended;
    lNrRecno : Integer;
begin
      try
          lNrRecno := CcF015.RecNo;
          CcF015.DisableControls;
          CcF015.First;
          lVrPagar := 0;

          while not CcF015.Eof do
          begin
                lVrPagar := lVrPagar + CcF015.FieldByName('VrDocto').AsFloat;
                CcF015.Next;
          end;

          LblTtCcPag.Caption := FormatFloat('0.00', lVrPagar);

      finally
          CcF015.RecNo := lNrRecno;
          CcF015.EnableControls;
      end;
end;

procedure TMov0021.TotalizaCentroCusto;
var lVrCenCus : Extended;
    lNrRecno : Integer;
begin
      try
          lNrRecno := CcF017.RecNo;
          CcF017.DisableControls;
          CcF017.First;
          lVrCenCus := 0;

          while not CcF017.Eof do
          begin
                lVrCenCus := lVrCenCus + CcF017.FieldByName('VrLancto').AsFloat;
                CcF017.Next;
          end;

          LblTtCenCus.Caption := FormatFloat('0.00', lVrCenCus);

      finally
          CcF017.RecNo := lNrRecno;;
          CcF017.EnableControls;
      end;
end;

procedure TMov0021.CcF014BeforeInsert(DataSet: TDataSet);
begin
      inherited;
      DbgF014.SelectedField := CcF014.FindField('NrPlano');
end;

procedure TMov0021.CcF014BeforePost(DataSet: TDataSet);
begin
      inherited;
      if (CcF014.FieldByName('vrlancto').AsFloat = 0) then
      begin
            raise Exception.Create('Informe um valor!');
      end;
end;

procedure TMov0021.CcF015AfterDelete(DataSet: TDataSet);
begin
      inherited;
      TotalizaPagar;
end;

procedure TMov0021.CcF015AfterPost(DataSet: TDataSet);
begin
      inherited;
      TotalizaPagar;
end;

procedure TMov0021.CcF015BeforePost(DataSet: TDataSet);
begin
      inherited;
      if (CcF015.FieldByName('DtVencto').IsNull) then
      begin
            raise Exception.Create('Informe um vencimento!');
      end;

      if (CcF015.FieldByName('VrDocto').IsNull) then
      begin
            raise Exception.Create('Informe um valor!');
      end;
end;

procedure TMov0021.CcF017AfterDelete(DataSet: TDataSet);
begin
      inherited;
      TotalizaCentroCusto;
end;

procedure TMov0021.CcF017AfterInsert(DataSet: TDataSet);
begin
      inherited;
      if (CcF014.State in [dsEdit, dsInsert]) then
      begin
            CcF014.Post;
      end;

      if (CcF014.RecordCount > 0) then
      begin
            CcF017.FieldByName('NrLancto').AsInteger := CcF014.FieldByName('NrLancto').AsInteger;
      end else
      begin
            raise Exception.Create('Não existem lançamentos contabeis!');
      end;
end;

procedure TMov0021.CcF017AfterPost(DataSet: TDataSet);
begin
      inherited;
      TotalizaCentroCusto;
end;

procedure TMov0021.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0021.EdtChaNFeExit(Sender: TObject);
begin
      inherited;
      if (Length(Trim(EdtChaNFe.Text)) = 44) then
      begin
            if (StrToIntDef(Trim(EdtNrNota.Text), 0) = 0) then
            begin
                  EdtNrNota.Text := FormatFloat('0', StrToInt(Copy(EdtChaNFe.Text, 26, 9)));
                  BtnIniciar.SetFocus;
            end else
            begin
                  if (StrToIntDef(Trim(EdtNrNota.Text), 0) <>
                      StrToIntDef(Trim(Copy(EdtChaNFe.Text, 26, 9)), 0)) then
                  begin
                        if (MessageDlg('Nota informada não é a mesma da chave informada!' + Chr(13)+
                                       'Deseja substituir nota informada?', mtConfirmation,
                                       [mbYes, mbNo], 0) = mrYes) then
                        begin
                              EdtNrNota.Text := FormatFloat('0', StrToInt(
                                                                      Copy(EdtChaNFe.Text, 26, 9)));
                        end else
                        begin
                              EdtChaNFe.SetFocus;
                              raise Exception.Create(
                                                'Nota informada não é a mesma da chave informada!');
                        end;
                  end;
            end;
      end else
      begin
            if (Trim(EdtChaNFe.Text) <> '') then
            begin
                  EdtChaNFe.SetFocus;
                  raise Exception.Create('Chave inválida!');
            end;
      end;
end;

initialization
      RegisterClass(TMov0021);

finalization
      UnRegisterClass(TMov0021);

end.
