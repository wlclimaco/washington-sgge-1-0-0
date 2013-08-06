unit UMov0020;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, DBClient, DB,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  BrvRetCon, StdCtrls, BrvLabel, BrvEdit, ComCtrls, BrvBitBtn, BrvEditNum, Mask, BrvEditDate,
  BrvAlertProgress, Grids, BrvDbGrids, BrvDbGrid, BrvClientDataSet, BrvServerProgress,
  BrvCustomMaskEdit, BrvCustomEdit;

type
  TMov0020 = class(TMov0000)
    PnlInput1: TPanel;
    PnlBottom: TPanel;
    Panel3: TPanel;
    EdtCdHistor: TBrvEdit;
    BrvLabel1: TBrvLabel;
    LblDsHistor: TBrvRetCon;
    RdgForma: TRadioGroup;
    Label1: TLabel;
    PgcCcMot: TPageControl;
    TbsManual: TTabSheet;
    TbsImportacao: TTabSheet;
    BtnPesquisa: TBrvBitBtn;
    BrvLabel2: TBrvLabel;
    EdtCdEmpres: TBrvEditNum;
    LblDsEmpres: TBrvRetCon;
    BrvLabel3: TBrvLabel;
    EdtCdMotori: TBrvEditNum;
    LblNmMotori: TBrvRetCon;
    EdtCdCarga: TBrvEditNum;
    LblDsDesCar: TBrvRetCon;
    BrvLabel4: TBrvLabel;
    EdtDtLancto: TBrvEditDate;
    BrvLabel5: TBrvLabel;
    EdtVrLancto: TBrvEditNum;
    BrvLabel6: TBrvLabel;
    MemObConCor: TMemo;
    LblTpHistor: TBrvRetCon;
    BtnExcel: TBrvBitBtn;
    BtnGravar: TBrvBitBtn;
    BtnVoltar: TBrvBitBtn;
    OpenDialog: TOpenDialog;
    BrvAlertProgress: TBrvAlertProgress;
    CAuxConta: TBrvClientDataSet;
    BrvDbGrid1: TBrvDbGrid;
    DtsAuxConta: TDataSource;
    CpMotorista: TBrvClientDataSet;
    BrvServerProgress: TBrvServerProgress;
    CpB012: TBrvClientDataSet;
    procedure BtnPesquisaClick(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure BtnGravarClick(Sender: TObject);
    procedure BtnExcelClick(Sender: TObject);
  private
    procedure ValidarEntrada;
    function CriaDataSetTemp: TClientDataSet;
    procedure LerArquivoTxt;
    procedure InserirArquivoAuxiliar(CdMotori: Integer; DtLancto: TDate; VrLancto: Real;
      ObConCor: AnsiString; NrCarSaq: Integer; NrCpfMot: AnsiString);
    function EncontraMotorista(NrCPF: String; NrCarSaq: Integer): Integer;
    procedure Gravar;
    procedure VerificaParametros;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0020: TMov0020;

implementation

uses UDmSrvApl, UClaSrv, BrvFuncoesXE, UDmCtb;

{$R *.dfm}

function TMov0020.CriaDataSetTemp: TClientDataSet;
begin
      Result := TClientDataSet.Create(Self);
      Result.FieldDefs.Clear;
      Result.FieldDefs.Add( 'CDCARGA',  ftInteger,   0);
      Result.FieldDefs.Add('CDEMPRES',  ftInteger,   0);
      Result.FieldDefs.Add('CDMOTORI',  ftInteger,   0);
      Result.FieldDefs.Add('CDUSUARI',  ftInteger,   0);
      Result.FieldDefs.Add('DTLANCTO',     ftDate,   0);
      Result.FieldDefs.Add('NRSEQCON',  ftInteger,   0);
      Result.FieldDefs.Add('OBCONCOR',   ftString, 400);
      Result.FieldDefs.Add('STCONCOR',   ftString,   1);
      Result.FieldDefs.Add('VRLANCTO',    ftFloat,   0);
      Result.CreateDataSet;
end;

procedure TMov0020.BtnExcelClick(Sender: TObject);
begin
      inherited;
      BrvFuncoesXE.ExportaBrvExcel(CAuxConta.Data);
end;

procedure TMov0020.VerificaParametros;
var
    lNrIdx    : Integer;
    lLnParame : String;
    lNrSeqPar : String;
begin
      for lNrIdx := 0 to gStlParCon.Count-1 do
      begin
            lLnParame := gStlParCon.Strings[lNrIdx];
            lNrSeqPar := lNrSeqPar + Copy(lLnParame, 1, Pos(';', lLnParame)-1) + ',';
      end;

      lNrSeqPar := Copy(lNrSeqPar, 1, length(lNrSeqPar)-1);

      CpB012.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(177,
                                  '<%TpFormul%>;' + UpperCase(copy(Self.Name, 1,3)) + Chr(13) +
                                  '<%NrSeqFor%>;' + copy(Self.Name, 4,4) + Chr(13) +
                                  '<%CdEmpres%>;' + EdtCdEmpres.Text     + Chr(13) +
                                  '<%NrSeqPar%>;' + lNrSeqPar, UpperCase(Self.Name));

      if (gStlParCon.Count <> CpB012.RecordCount) then
      begin
            raise Exception.Create('Parâmetros de contabilização não cadastrados!' + Chr(13) +
                                   'Verifique...');
      end;

      if (LblTpHistor.Text = 'D') then
      begin
            if (not CpB012.Locate('NrSeqPar', '1', [loCaseInsensitive])) then
            begin
                  raise Exception.Create('Parâmetros de contabilização não localizado!' + Chr(13) +
                                   'Verifique...');
            end;
      end else
      begin
            if (not CpB012.Locate('NrSeqPar', '2', [loCaseInsensitive])) then
            begin
                  raise Exception.Create('Parâmetros de contabilização não localizado!' + Chr(13) +
                                   'Verifique...');
            end;
      end;

end;

procedure TMov0020.Gravar;
var lCcContaMot : TClientDataSet;
    lConexao  : TSDmTraClient;
    lDsResult : String;
    lNrPlano  : Integer;
    lDsComple : TStrings;
    lNrLancto : Integer;
begin
      VerificaParametros;
      lNrPlano  := CpB012.FieldByName('NrPlano').AsInteger;
      lDsComple := TStringList.Create;
      lNrLancto := 1;

      case RdgForma.ItemIndex of
        0 : begin
                  ValidarEntrada;

                  try
                      lCcContaMot := CriaDataSetTemp;

                      lCcContaMot.Append;
                      lCcContaMot.FieldByName( 'CDCARGA').AsInteger := EdtCdCarga.BrAsInteger;
                      lCcContaMot.FieldByName('CDEMPRES').AsInteger := EdtCdEmpres.BrAsInteger;
                      lCcContaMot.FieldByName('CDMOTORI').AsInteger := EdtCdMotori.BrAsInteger;
                      lCcContaMot.FieldByName('CDUSUARI').AsInteger :=
                                                                    DmSrvApl.BrvDicionario.UserCode;
                      lCcContaMot.FieldByName('DTLANCTO').AsDateTime:= EdtDtLancto.BrAsDate;
                      lCcContaMot.FieldByName('NRSEQCON').AsInteger :=
                                                DmSrvApl.BrvDicionario.ProximoValorSequencial(0,20);
                      lCcContaMot.FieldByName('OBCONCOR').AsString  := MemObConCor.Text;
                      lCcContaMot.FieldByName('STCONCOR').AsString  := 'A';
                      lCcContaMot.FieldByName('VRLANCTO').AsFloat   := EdtVrLancto.BrAsFloat;
                      lCcContaMot.Post;

                      DmCtb.IniciarLancamentos;
                      lDsComple.Clear;

                      DmCtb.LancarContabilidade(
                                         {pNrLancto} lNrLancto,
                                         {pNrPlano}  CpB012.FieldByName('NrPlano').AsInteger,
                                         {pCdEmpres} lCcContaMot.FieldByName('CDEMPRES').AsInteger,
                                         {pDtLancto} lCcContaMot.FieldByName('DTLANCTO').AsDateTime,
                                         {pNrConDeb} CpB012.FieldByName('NrConDeb').AsInteger,
                                         {pNrConCre} CpB012.FieldByName('NrConCre').AsInteger,
                                         {pVrLancto} lCcContaMot.FieldByName('VRLANCTO').AsFloat,
                                         {pNrDocto } '0',
                                         {pCdHistor} StrToInt(EdtCdHistor.Text),
                                         {pDsHistor} lDsComple,
                                         {pNmFormul} UpperCase(Self.Name),
                                         {pSnEncerr} 'N',
                                         {pCdCeCuCr} CpB012.FieldByName('CdCeCuCr').AsInteger,
                                         {pCdCeCuDe} CpB012.FieldByName('CdCeCuDe').AsInteger,
                                         {pNrClaDeb} CpB012.FieldByName('NrClaDeb').AsString,
                                         {pNrClaCre} CpB012.FieldByName('NrClaCre').AsString);

                      lConexao  := TSDmTraClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                      lDsResult := lConexao.GravaContaCorrenteMotorista(
                                                                  DmSrvApl.BrvDicionario.Credencial,
                                                                  lCcContaMot.Data,
                                                                  DMCTB.QcLanCon.Data,
                                                                  lNrPlano,
                                                                  UpperCase(Self.Name));

                      DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

                      MessageDlg('Gravado com sucesso!', mtInformation, [mbok], 0);

                      BtnVoltarClick(BtnVoltar);
                  finally
                      FreeAndNil(lCcContaMot);
                      FreeAndNil(lConexao);
                  end;
            end;
        1 : begin
                  try
                      lCcContaMot := CriaDataSetTemp;
                      CAuxConta.DisableControls;

                      DmCtb.IniciarLancamentos;
                      lDsComple.Clear;

                      CAuxConta.First;

                      while not CAuxConta.Eof do
                      begin
                            lCcContaMot.Append;
                            lCcContaMot.FieldByName( 'CDCARGA').AsInteger := 0;
                            lCcContaMot.FieldByName('CDEMPRES').AsInteger :=
                                                        CAuxConta.FieldByName('CdEmpres').AsInteger;
                            lCcContaMot.FieldByName('CDMOTORI').AsInteger :=
                                                        CAuxConta.FieldByName('CdMotori').AsInteger;
                            lCcContaMot.FieldByName('CDUSUARI').AsInteger :=
                                                                    DmSrvApl.BrvDicionario.UserCode;
                            lCcContaMot.FieldByName('DTLANCTO').AsDateTime:=
                                                       CAuxConta.FieldByName('DtLancto').AsDateTime;
                            lCcContaMot.FieldByName('NRSEQCON').AsInteger :=
                                                DmSrvApl.BrvDicionario.ProximoValorSequencial(0,20);
                            lCcContaMot.FieldByName('OBCONCOR').AsString  :=
                                                         CAuxConta.FieldByName('ObConCor').AsString;
                            lCcContaMot.FieldByName('STCONCOR').AsString  := 'A';
                            lCcContaMot.FieldByName('VRLANCTO').AsFloat   :=
                                                         CAuxConta.FieldByName('VrLancto').AsFloat;
                            lCcContaMot.Post;

                            DmCtb.LancarContabilidade(
                                         {pNrLancto} lNrLancto,
                                         {pNrPlano}  CpB012.FieldByName('NrPlano').AsInteger,
                                         {pCdEmpres} lCcContaMot.FieldByName('CDEMPRES').AsInteger,
                                         {pDtLancto} lCcContaMot.FieldByName('DTLANCTO').AsDateTime,
                                         {pNrConDeb} CpB012.FieldByName('NrConDeb').AsInteger,
                                         {pNrConCre} CpB012.FieldByName('NrConCre').AsInteger,
                                         {pVrLancto} lCcContaMot.FieldByName('VRLANCTO').AsFloat,
                                         {pNrDocto } '0',
                                         {pCdHistor} StrToInt(EdtCdHistor.Text),
                                         {pDsHistor} lDsComple,
                                         {pNmFormul} UpperCase(Self.Name),
                                         {pSnEncerr} 'N',
                                         {pCdCeCuCr} CpB012.FieldByName('CdCeCuCr').AsInteger,
                                         {pCdCeCuDe} CpB012.FieldByName('CdCeCuDe').AsInteger,
                                         {pNrClaDeb} CpB012.FieldByName('NrClaDeb').AsString,
                                         {pNrClaCre} CpB012.FieldByName('NrClaCre').AsString);

                            CAuxConta.Next;
                      end;

                      try
                          BrvServerProgress.Start(Self.Caption, 'Gravando Lançamentos', 100, 10);
                          lConexao  := TSDmTraClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                          lDsResult := lConexao.GravaContaCorrenteMotorista(
                                                                  DmSrvApl.BrvDicionario.Credencial,
                                                                  lCcContaMot.Data,
                                                                  DMCTB.QcLanCon.Data,
                                                                  lNrPlano,
                                                                  UpperCase(Self.Name));

                      finally
                          BrvServerProgress.Stop;
                      end;

                      DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
                      MessageDlg('Gravado com sucesso!', mtInformation, [mbok], 0);
                      BtnVoltarClick(BtnVoltar);
                  finally
                      FreeAndNil(lCcContaMot);
                      FreeAndNil(lConexao);
                      CAuxConta.EnableControls;
                  end;
            end;
      end;
end;

procedure TMov0020.BtnGravarClick(Sender: TObject);
begin
      inherited;

      if (MessageDlg('Deseja realmente continuar?', mtConfirmation, [mbyes, mbno], 0) = mryes) then
      begin
            Gravar;
      end;
end;

procedure TMov0020.BtnPesquisaClick(Sender: TObject);
begin
      inherited;
      if (StrToIntDef(EdtCdHistor.Text, 0) = 0) then
      begin
            EdtCdHistor.SetFocus;
            Raise Exception.Create('Informe um Histórico!');
      end;

      if (LblTpHistor.Text <> 'D') and (LblTpHistor.Text <> 'C') then
      begin
            Raise Exception.Create('Tipo do Histórico inválido! Esperado (D)ébito (C)rédito');
      end;

      if CAuxConta.Active then
      begin
            CAuxConta.EmptyDataSet;
      end;

      PgcCcMot.Visible := True;
      TbsManual.TabVisible     := False;
      TbsImportacao.TabVisible := False;
      PgcCcMot.ActivePageIndex := RdgForma.ItemIndex;
      PnlInput1.Enabled        := False;
      BtnExcel.Visible         := (RdgForma.ItemIndex = 1);

      if (RdgForma.ItemIndex = 0) then
      begin
            EdtCdEmpres.Text := '0';
            LblDsEmpres.Text := '';
            EdtDtLancto.Text := '';
            EdtCdMotori.Text := '0';
            LblNmMotori.Text := '';
            EdtVrLancto.Text := '0,00';
            EdtCdCarga.Text  := '0';
            LblDsDesCar.Text := '';
            EdtCdEmpres.SetFocus;
            PnlBottom.Height         := 34;
      end else
      begin
            LerArquivoTxt;
      end;
end;

procedure TMov0020.InserirArquivoAuxiliar(CdMotori : Integer;  DtLancto : TDate;
                                          VrLancto : Real;     ObConCor : AnsiString;
                                          NrCarSaq : Integer;  NrCpfMot : AnsiString);
begin
      CAuxConta.Append;
      CAuxConta.FieldByName('CdMotori').AsInteger  := CdMotori;
      CAuxConta.FieldByName('CdEmpres').AsInteger  :=
                StrToIntDef(DmSrvApl.BrvDicionario.RetornaValorColunaTabela('Motorista', 'CdEmpMot',
                                                                'CdMotori', IntToStr(CdMotori)), 0);
      CAuxConta.FieldByName('NmMotori').AsString   :=
                            DmSrvApl.BrvDicionario.RetornaValorColunaTabela('Motorista', 'NmMotori',
                                                                'CdMotori', IntToStr(CdMotori));
      CAuxConta.FieldByName('DtLancto').AsDateTime := DtLancto;
      CAuxConta.FieldByName('VrLancto').AsFloat    := VrLancto;
      CAuxConta.FieldByName('NrCarSaq').AsInteger  := NrCarSaq;
      CAuxConta.FieldByName('ObConCor').AsString   := ObConCor;
      CAuxConta.FieldByName('NrCpfMot').AsString   := NrCpfMot;
      CAuxConta.Post;
end;

function TMov0020.EncontraMotorista(NrCPF : String; NrCarSaq : Integer) : Integer;
begin
      Result := 0;

      try
          CpMotorista.Close;
          CpMotorista.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro( 176,
                                  '<%DsComWhe%>; CjMotori = "' + NrCPF + '"', UpperCase(Self.Name));
          CpMotorista.Open;// 176

          case CpMotorista.RecordCount of
               0: begin
                        CpMotorista.Close;
                        CpMotorista.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro( 176,
                            '<%DsComWhe%>; NrCarSaq = ' + IntToStr(NrCarSaq), UpperCase(Self.Name));
                        CpMotorista.Open;// 176

                        if CpMotorista.RecordCount > 1 then
                        begin
                              Raise Exception.Create('Existe mais de 1 mototorista com o cartão ' +
                                                                                IntToStr(NrCarSaq));
                        end else
                        begin
                              Result := CpMotorista.FieldByName('CdMotori').AsInteger;
                        end;
                  end;

               1: begin
                        Result := CpMotorista.FieldByName('CdMotori').AsInteger;
                  end;

               else
                  begin
                        Raise Exception.Create('Existe mais de 1 mototorista com o CPF ' + NrCPF);
                  end;
          end;
      finally
          CpMotorista.Close;
      end;
end;

procedure TMov0020.LerArquivoTxt;
var NrLinha  : Integer;
    DsLinha  : String;
    CdMotori : Integer;
    NrCpfMot : AnsiString;
    DtLancto : TDateTime;
    VrLancto : Real;
    DsObserv : String;
    NrCarSaq : Integer;

    DsTexto  : TStrings;
begin
      if OpenDialog.Execute then
      begin
            CAuxConta.Close;
            CAuxConta.FieldDefs.Clear;
            CAuxConta.FieldDefs.Add('CdMotori', ftInteger,  0);
            CAuxConta.FieldDefs.Add('NmMotori', ftString , 50);
            CAuxConta.FieldDefs.Add('NrCpfMot', ftString , 11);
            CAuxConta.FieldDefs.Add('DtLancto', ftDate   ,  0);
            CAuxConta.FieldDefs.Add('VrLancto', ftFloat  ,  0);
            CAuxConta.FieldDefs.Add('ObConCor', ftMemo   ,  0);
            CAuxConta.FieldDefs.Add('NrCarSaq', ftInteger,  0);
            CAuxConta.FieldDefs.Add('CdEmpres', ftInteger,  0);
            CAuxConta.CreateDataSet;

            TFloatField(CAuxConta.FindField('VrLancto')).DisplayFormat := '#,##0.00';

            DsTexto  := TStringList.Create;
            DsTexto.LoadFromFile(OpenDialog.FileName);

            try
                CAuxConta.DisableControls;
                BrvAlertProgress.BrCaption  := Self.Caption;
                BrvAlertProgress.BrProcesso := 'Carregando lançamentos!';
                BrvAlertProgress.ShowAlert;
                BrvAlertProgress.BrMax(DsTexto.Count);

                // Ignora a primeira linha -> Registro "0" = Header
                NrLinha  := 1;
                BrvAlertProgress.BrStepIt();
                CdMotori := 0;
                NrCarSaq := 0;

                While (NrLinha < DsTexto.Count) and (Copy(DsLinha, 1, 1) <> '9') do
                begin
                      DsLinha  := DsTexto[NrLinha];
                      if Copy(DsLinha, 1, 1) = '1' then
                      begin
                            // Registro "1" = Registro identificador do portador
                            if Copy(DsLinha, 42, 1) = '0' then // Identifica o tipo de registro
                            begin
                                  // Número do CPF do portador 72, 11
                                  // Identificação do portador no Itaú 55, 6
                                  NrCarSaq := StrToIntdef(Copy(DsLinha, 55, 6), 0);
                                  NrCpfMot := Copy(DsLinha, 72, 11);
                                  CdMotori := EncontraMotorista(NrCpfMot, NrCarSaq);

                                  if CdMotori = 0 then
                                  begin
                                        MessageDlg('Motorista não encontrado. CPF: ' +
                                                    Copy(DsLinha, 72, 11)            +
                                                   ' Identificação no banco: '       +
                                                    Copy(DsLinha, 55, 6),
                                                    MtError, [MbOk], 0);
                                  end;

                                  Inc(NrLinha);
                                  BrvAlertProgress.BrStepIt();
                                  DsLinha  := DsTexto[NrLinha];
                            end;

                            // Registro "1" = Registro de transação - Movimento
                            if Copy(DsLinha, 42, 1) = '1' then  // Identifica o tipo de registro
                            begin
                                  // Valor utilizado a Débito (D) / Crédito (C)
                                  if Copy(DsLinha, 105, 1) = 'D' then
                                  begin
                                        DtLancto := StrToDateDef(
                                                             Copy(DsLinha, 182, 2) + '/' +
                                                             Copy(DsLinha, 184, 2) + '/' +
                                                             Copy(DsLinha, 186, 4), 0);

                                        VrLancto := StrToFloatDef(
                                                          Copy(DsLinha, 87, 18), 0) / 100;

                                        DsObserv := Copy(DsLinha, 50, 25);

                                        if CdMotori > 0 then
                                        begin
                                              InserirArquivoAuxiliar(CdMotori, DtLancto,
                                                                     VrLancto, DsObserv,
                                                                     NrCarSaq, NrCpfMot);
                                        end;

                                        DtLancto := 0;
                                        VrLancto := 0;
                                        DsObserv := '';
                                  end else
                                  begin
                                        MessageDlg('Encontrou um lançamento de crédito, '+
                                                   'porém será desconsiderado.',
                                                    MtError, [MbOk], 0);
                                  end;


                                  Inc(NrLinha);
                                  BrvAlertProgress.BrStepIt();
                                  DsLinha  := DsTexto[NrLinha];
                            end;

                            // Ignorar -> Registro "2" = Registro de transação - Encerramento do portador
                            if Copy(DsLinha, 42, 1) = '2' then // Identifica o tipo de registro
                            begin
                                  Inc(NrLinha);
                                  BrvAlertProgress.BrStepIt();
                                  CdMotori := 0;
                                  NrCarSaq := 0;
                            end;
                      end;

                end;
                BrvAlertProgress.BrStepIt();
                PnlBottom.Height := 34;
            finally
                FreeAndNil(DsTexto);
                CAuxConta.first;
                CAuxConta.EnableControls;
            end;
      end else
      begin
            BtnVoltarClick(BtnVoltar);
      end;
end;

procedure TMov0020.ValidarEntrada;
begin
      if (EdtCdEmpres.BrAsInteger = 0) then
      begin
            EdtCdEmpres.SetFocus;
            Raise Exception.Create('Informe a Empresa!');
      end;

      if (not EdtDtLancto.BrDataValida) then
      begin
            EdtDtLancto.SetFocus;
            Raise Exception.Create('Informe uma Data!');
      end;

      if (EdtCdMotori.BrAsInteger = 0) then
      begin
            EdtCdMotori.SetFocus;
            Raise Exception.Create('Informe um Motorista!');
      end;

      if (EdtVrLancto.BrAsFloat = 0) then
      begin
            EdtVrLancto.SetFocus;
            Raise Exception.Create('Informe o Valor!');
      end;
end;

procedure TMov0020.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      PnlBottom.Height  := 0;
      BtnExcel.Visible  := False;
      PgcCcMot.Visible  := False;
      PnlInput1.Enabled := True;
      EdtCdHistor.SelectAll;
      EdtCdHistor.SetFocus;
end;

procedure TMov0020.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0020.FormCreate(Sender: TObject);
begin
      inherited;
      PnlBottom.Height := 0;
end;

initialization
      RegisterClass(TMov0020);

finalization
      UnRegisterClass(TMov0020);

end.
