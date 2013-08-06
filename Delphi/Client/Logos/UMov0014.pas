unit UMov0014;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, BrvString, DB, DBClient, Grids,
  DBGrids, StdCtrls, BrvBitBtn, BrvRetCon, BrvEdit, BrvServerProgress, BrvAlertProgress,
  BrvListParam, ImgList, Menus, BrvCustomEdit;

type
  TMov0014 = class(TMov0000)
    PnlArquiv: TPanel;
    Label1: TLabel;
    Label2: TLabel;
    EdtNmArquiv: TBrvEdit;
    EdtCdArmaze: TBrvEdit;
    EdtNmArmaze: TBrvRetCon;
    BtnImport: TBrvBitBtn;
    PnlDados: TPanel;
    Splitter1: TSplitter;
    Splitter2: TSplitter;
    DbgContLog: TDBGrid;
    Panel1: TPanel;
    BtnRetornar: TBrvBitBtn;
    BtnGravar: TBrvBitBtn;
    Panel2: TPanel;
    DbgNotas: TDBGrid;
    Panel4: TPanel;
    DbgItem: TDBGrid;
    CTTitula: TClientDataSet;
    CTTitulaCdTitula: TIntegerField;
    CTTitulaRsTitula: TStringField;
    CTTitulaCjTitula: TStringField;
    CPProduto: TClientDataSet;
    CTProduto: TClientDataSet;
    CTProdutoCdProdut: TIntegerField;
    CTProdutoDsProdut: TStringField;
    CTProdutoCdEmbala: TStringField;
    CTParams: TClientDataSet;
    CTParamsCdArmaze: TIntegerField;
    CTParamsCdTipAti: TIntegerField;
    CTParamsCdUsuGer: TIntegerField;
    CTParamsCdEmbala: TStringField;
    CTParamsQtProdut: TFloatField;
    CTParamsNrNfOrig: TIntegerField;
    CTParamsNrSeNfOr: TStringField;
    CTParamsDtValida: TDateField;
    CTParamsDsLote: TStringField;
    CTParamsQtProCon: TFloatField;
    CTNotas: TClientDataSet;
    CTNotasNrNota: TIntegerField;
    CTNotasNrSerie: TStringField;
    CTNotasCjRemete: TStringField;
    CTNotasCdTitula: TIntegerField;
    CTNotasRsTitula: TStringField;
    STNotas: TDataSource;
    CTIteNota: TClientDataSet;
    CTIteNotaNrNota: TIntegerField;
    CTIteNotaQtProdut: TFloatField;
    CTIteNotaCdProdut: TIntegerField;
    CTIteNotaDsProdut: TStringField;
    CTIteNotaCdEmbala: TStringField;
    STIteNota: TDataSource;
    CTContLog: TClientDataSet;
    IntegerField1: TIntegerField;
    FloatField1: TFloatField;
    StringField2: TStringField;
    DateField1: TDateField;
    IntegerField2: TIntegerField;
    CTContLogCdEmbala: TStringField;
    STContLog: TDataSource;
    BrvString: TBrvString;
    CTNotasNrFornec: TStringField;
    CTNotasCjDestin: TStringField;
    CTNotassnduplic: TStringField;
    CdsW007: TClientDataSet;
    BrvAlertProgress: TBrvAlertProgress;
    BrvServerProgress: TBrvServerProgress;
    procedure BtnImportClick(Sender: TObject);
    procedure BtnRetornarClick(Sender: TObject);
    procedure CTNotasAfterScroll(DataSet: TDataSet);
    procedure BtnGravarClick(Sender: TObject);
    procedure CTIteNotaAfterScroll(DataSet: TDataSet);
    procedure DbgNotasDrawColumnCell(Sender: TObject; const Rect: TRect; DataCol: Integer;
      Column: TColumn; State: TGridDrawState);
  private
    procedure CarregarDadosRemetenteNota(pCjEmiNot: AnsiString);
    procedure CarregarDadosProduto(pCdEmbala: AnsiString);
    function RetornaData(pDsData: AnsiString): TDateTime;
    function NotaExiste(pNrNota: String): String;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0014: TMov0014;
  gSnDuplic : Boolean;

implementation

uses UDmSrvApl, UClaSrv;

{$R *.dfm}

procedure TMov0014.CarregarDadosRemetenteNota(pCjEmiNot : AnsiString);
begin
      CTTitula.Filtered := False;
      CTTitula.Filter   := 'CjTitula = ' + QuotedStr(pCjEmiNot);
      CTTitula.Filtered := True;

      if CTTitula.Eof then
      begin
            CTTitula.Append;
            CTTitula.FieldByName('CjTitula').AsString   := pCjEmiNot;
            CTTitula.FieldByName('CdTitula').AsString   :=
                     DmSrvApl.BrvDicionario.RetornaValorColunaTabela(
                                    'G011', 'CdTitula', 'CjTitula', QuotedStr(pCjEmiNot));
            CTTitula.FieldByName('RsTitula').AsString   :=
                     DmSrvApl.BrvDicionario.RetornaValorColunaTabela(
                                    'G011', 'RsTitula', 'CjTitula', QuotedStr(pCjEmiNot));
            CTTitula.Post;
      end;

      CTNotas.FieldByName('CdTitula').AsInteger :=
                                               CTTitula.FieldByName('CdTitula').AsInteger;
      CTNotas.FieldByName('RsTitula').AsString  :=
                                               CTTitula.FieldByName('RsTitula').AsString;
end;

procedure TMov0014.CTIteNotaAfterScroll(DataSet: TDataSet);
begin
      inherited;
      if CTIteNota.FieldByName('NrNota').AsInteger > 0 then
      begin
            CTContLog.Filter   :=
                         'NrNota = '        + CTIteNota.FieldByName('NrNota').AsString +
                         ' and CdEmbala = ' + QuotedStr(CTIteNota.FieldByName('CdEmbala').AsString);
            CTContLog.Filtered := True;
      end;
end;

procedure TMov0014.CTNotasAfterScroll(DataSet: TDataSet);
begin
      inherited;
      if CTNotas.FieldByName('NrNota').AsInteger > 0 then
      begin
            CTIteNota.Filter   := 'NrNota = ' + CTNotas.FieldByName('NrNota').AsString;
            CTIteNota.Filtered := True;
      end;
end;

procedure TMov0014.DbgNotasDrawColumnCell(Sender: TObject; const Rect: TRect; DataCol: Integer;
                                                            Column: TColumn; State: TGridDrawState);
begin
      inherited;
      if (Column.FieldName = 'snduplic') then
      begin
            if (Column.Field.Value = 'SIM') then
            begin
                  DbgNotas.Canvas.Font.Color := clWhite;
                  DbgNotas.Canvas.Brush.Color:= clRed;
            end else
            begin
                  DbgNotas.Canvas.Font.Color := clWhite;
                  DbgNotas.Canvas.Brush.Color:= clGreen;
            end;
      end;

      DbgNotas.Canvas.FillRect(Rect);
      DbgNotas.Canvas.TextOut(Rect.Left+2,Rect.Top,Column.Field.AsString);
end;

procedure TMov0014.CarregarDadosProduto(pCdEmbala : AnsiString);
begin
      try
          CTProduto.Filtered := False;
          CTProduto.Filter   := 'CdEmbala = ' + QuotedStr(pCdEmbala);
          CTProduto.Filtered := True;

          if CTProduto.Eof then
          begin
                CPProduto.Close;
                CPProduto.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(112,
                                                         '<%CdEmbala%>;' + pCdEmbala, Name);
                CPProduto.Open;

                if (CPProduto.RecordCount = 0) then
                begin
                      CPProduto.Close;
                      CPProduto.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(154,
                                                               '<%CdEmbala%>;' + pCdEmbala, Name);
                      CPProduto.Open;
                end;

                CTProduto.Append;
                CTProduto.FieldByName('CdEmbala').AsString  :=
                                              CPProduto.FieldByName('CdEmbala').AsString;
                CTProduto.FieldByName('CdProdut').AsInteger :=
                                              CPProduto.FieldByName('CdProdut').AsInteger;
                CTProduto.FieldByName('DsProdut').AsString  :=
                                              CPProduto.FieldByName('DsProdut').AsString;
                CTProduto.Post;

                CTIteNota.FieldByName('CdEmbala').AsString :=
                                              CPProduto.FieldByName('CdEmbala').AsString;
                CTIteNota.FieldByName('CdProdut').AsInteger :=
                                              CPProduto.FieldByName('CdProdut').AsInteger;
                CTIteNota.FieldByName('DsProdut').AsString  :=
                                              CPProduto.FieldByName('DsProdut').AsString;

          end else
          begin
                CTIteNota.FieldByName('CdProdut').AsInteger :=
                                              CTProduto.FieldByName('CdProdut').AsInteger;
                CTIteNota.FieldByName('DsProdut').AsString  :=
                                              CTProduto.FieldByName('DsProdut').AsString;
          end;

      finally
          CPProduto.Close;
      end;
end;

function TMov0014.RetornaData(pDsData: AnsiString): TDateTime;
var lDsData : String;
begin
      lDsData := Copy(pDsData, 7, 2) + '/' +
                 Copy(pDsData, 5, 2) + '/' +
                 Copy(pDsData, 1, 4);

      Result := StrToDateDef(lDsData, 0);
end;

procedure TMov0014.BtnGravarClick(Sender: TObject);
var lConexao  : TSDmWmsClient;
    lDsResult : AnsiString;
begin
      inherited;

      if (gSnDuplic) then
      begin
            MessageDlg('AVISO' + Chr(13) +
                             'As notas duplicadas não serão importadas!', mtInformation, [mbOK], 0);
      end;

      try
          lConexao := TSDmWmsClient.Create(DmSrvApl.SrvAplica.DBXConnection);
          try
              BrvServerProgress.Start(Self.Caption, 'Importando...', 100, 10);
              lDsResult := lConexao.GravarOperacaoLogistica(DmSrvApl.BrvDicionario.Credencial,
                                                            DmSrvApl.BrvDicionario.UserCode,
                                                            Self.Name,
                                                            StrToInt(EdtCdArmaze.Text), 2,
                                                            CTNotas.Data, CTIteNota.Data,
                                                            CTContLog.Data);
          finally
              BrvServerProgress.Stop;
          end;

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          MessageDlg('Arquivo processado com sucesso!!!', mtInformation, [mbok], 0);
          BtnRetornarClick(BtnRetornar);
      finally
          FreeAndNil(lConexao);
      end;
end;

function TMov0014.NotaExiste(pNrNota: String): String;
begin
      CdsW007.Close;
      CdsW007.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(163,
                                                              '<%NrNfOrig%>;' +
                                                              FormatFloat('0', StrToInt(pNrNota)),
                                                              self.Name);
      CdsW007.Open;

      if (CdsW007.RecordCount > 0) then
      begin
            Result := 'SIM';
            gSnDuplic := true;
      end else
      begin
            Result := 'NAO';
      end;
end;

procedure TMov0014.BtnImportClick(Sender: TObject);
var lTxArquiv : TStrings;
    lDsLinha  : AnsiString;
    lNrIndice : Integer;
    lQtProdut : String;
    lCdBarra  : String;
    lNrNota   : String;
    lNrLote   : String;
begin
      try
          gSnDuplic := false;

          if FileExists(EdtNmArquiv.Text) then
          begin
                CTNotas.Close;
                CTNotas.CreateDataSet;

                CTIteNota.Close;
                CTIteNota.CreateDataSet;

                CTContLog.Close;
                CTContLog.CreateDataSet;

                CTProduto.Close;
                CTProduto.CreateDataSet;

                CTTitula.Close;
                CTTitula.CreateDataSet;

                lTxArquiv := TStringList.Create;
                lTxArquiv.LoadFromFile(EdtNmArquiv.Text);

                BrvAlertProgress.BrCaption  := self.Caption;
                BrvAlertProgress.BrProcesso := 'Processando arquivo...';
                BrvAlertProgress.ShowAlert;
                BrvAlertProgress.BrMax(lTxArquiv.Count);

                for lNrIndice := 0 to lTxArquiv.Count -1 do
                begin
                      lDsLinha := lTxArquiv[lNrIndice];

                      case lDsLinha[1] of
                           '1': begin
                                      CTNotas.Append;
                                      CTNotas.FieldByName('NrNota').AsString   :=
                                                                  Copy(lDsLinha, 18, 9);
                                      CTNotas.FieldByName('NrSerie').AsString  :=
                                                                  Copy(lDsLinha, 27, 3);
                                      CTNotas.FieldByName('CjRemete').AsString :=
                                                                  Copy(lDsLinha,  2, 14);
                                      CTNotas.FieldByName('CjDestin').AsString :=
                                                                  Copy(lDsLinha,  87, 14);
                                      CTNotas.FieldByName('NrFornec').AsString :=
                                                                  Copy(lDsLinha, 1004, 10);
                                      CTNotas.FieldByName('SnDuplic').AsString :=
                                                                  NotaExiste(Copy(lDsLinha, 18, 9));

                                      CarregarDadosRemetenteNota(
                                                CTNotas.FieldByName('CjRemete').AsString);
                                      CTNotas.Post;
                                end;
                           '2': begin
                                      lCdBarra :=
                                             BrvString.RemoverZerosEsquerda(Copy(lDsLinha, 34, 18));

                                      CPProduto.Close;
                                      CPProduto.Data := DmSrvApl.BrvDicionario.
                                                RetornaDadosSqlCadastro(154, '<%CdEmbala%>;' +
                                                                                    lCdBarra, Name);
                                      CPProduto.Open;

                                      if (CPProduto.RecordCount > 0) then
                                      begin
                                            lCdBarra := CPProduto.FieldByName('CdEmbala').AsString;
                                      end;

                                      if (not CTIteNota.Locate('NrNota;CdEmbala',
                                              VarArrayOf([Copy(lDsLinha, 18, 9), lCdBarra]),
                                                                 [loCaseInsensitive])) then
                                      begin
                                            CTIteNota.Append;
                                            CTIteNota.FieldByName('NrNota').AsString   :=
                                                                              Copy(lDsLinha, 18, 9);
                                            CTIteNota.FieldByName('CdEmbala').AsString := lCdBarra;

                                            CTIteNota.FieldByName('CdProdut').AsInteger :=
                                                        CPProduto.FieldByName('CdProdut').AsInteger;
                                            CTIteNota.FieldByName('DsProdut').AsString  :=
                                                        CPProduto.FieldByName('DsProdut').AsString;

                                            lQtProdut := FormatFloat('0',
                                                           StrToInt(Copy(lDsLinha, 110, 12)));
                                            lQtProdut := Copy(lQtProdut, 1, Length(lQtProdut)-3)
                                                    + ',' + Copy(lQtProdut, Length(lQtProdut)-2, 3);

                                            CTIteNota.FieldByName('QtProdut').AsString := lQtProdut;

                                            CTIteNota.Post;
                                      end else
                                      begin
                                            CTIteNota.Edit;
                                            lQtProdut := FormatFloat('0',
                                                                 StrToInt(Copy(lDsLinha, 110, 12)));
                                            lQtProdut := Copy(lQtProdut, 1, Length(lQtProdut)-3)
                                                   + ',' +
                                                  Copy(lQtProdut, Length(lQtProdut)-2, 3);

                                            CTIteNota.FieldByName('QtProdut').AsFloat :=
                                                   CTIteNota.FieldByName('QtProdut').AsFloat +
                                                                              StrToFloat(lQtProdut);
                                            CTIteNota.Post;
                                      end;
                                end;
                           '3': begin
                                      lCdBarra :=
                                             BrvString.RemoverZerosEsquerda(Copy(lDsLinha, 34, 18));

                                      CPProduto.Close;
                                      CPProduto.Data := DmSrvApl.BrvDicionario.
                                                RetornaDadosSqlCadastro(154, '<%CdEmbala%>;' +
                                                                                    lCdBarra, Name);
                                      CPProduto.Open;

                                      if (CPProduto.RecordCount > 0) then
                                      begin
                                            lCdBarra := CPProduto.FieldByName('CdEmbala').AsString;
                                      end;

                                      lNrNota := BrvString.RemoverZerosEsquerda(
                                                                             Copy(lDsLinha, 18, 9));
                                      lNrLote := Trim(Copy(lDsLinha, 52, 10));

                                      if (not CTContLog.Locate('NrNota;CdEmbala;DsLote',
                                              VarArrayOf([lNrNota, lCdBarra, lNrLote]),
                                                                          [loCaseInsensitive])) then
                                      begin
                                            lQtProdut := FormatFloat('0',
                                                              StrToInt(Copy(lDsLinha, 62, 12)));
                                            lQtProdut := Copy(lQtProdut, 1, Length(lQtProdut)-3)
                                                         + ',' +
                                                        Copy(lQtProdut, Length(lQtProdut)-2, 3);

                                            if StrToFloat(lQtProdut) > 0 then
                                            begin
                                                  CTContLog.Append;
                                                  CTContLog.FieldByName('NrNota'  ).AsString :=
                                                                                          lNrNota;
                                                  CTContLog.FieldByName('CdEmbala').AsString :=
                                                                                          lCdBarra;
                                                  CTContLog.FieldByName('DsLote'  ).AsString :=
                                                                                          lNrLote;
                                                  CTContLog.FieldByName('QtProdut').AsString :=
                                                                                          lQtProdut;
                                                  CTContLog.FieldByName('DtValida').AsDateTime :=
                                                                RetornaData(Copy(lDsLinha, 74,  8));

                                                  CTContLog.Post;
                                            end;
                                      end else
                                      begin
                                            CTContLog.Edit;
                                            lQtProdut := FormatFloat('0',
                                                                 StrToInt(Copy(lDsLinha, 62, 12)));
                                            lQtProdut := Copy(lQtProdut, 1, Length(lQtProdut)-3)
                                                    + ',' + Copy(lQtProdut, Length(lQtProdut)-2, 3);

                                            CTContLog.FieldByName('QtProdut').AsFloat :=
                                                   CTContLog.FieldByName('QtProdut').AsFloat +
                                                                              StrToFloat(lQtProdut);
                                            CTContLog.Post;
                                      end;
                                end;
                      end;
                      BrvAlertProgress.BrStepIt();
                end;

                CTNotasAfterScroll(nil);
                PnlDados.Visible  := True;
                PnlArquiv.Enabled := False;
          end else
          begin
                raise Exception.Create('Arquivo inválido!');
          end;
      finally
          FreeAndNil(lTxArquiv);
      end;
end;

procedure TMov0014.BtnRetornarClick(Sender: TObject);
begin
      inherited;
      PnlDados.Visible  := False;
      PnlArquiv.Enabled := True;
end;

initialization
      RegisterClass(TMov0014);

finalization
      UnRegisterClass(TMov0014);

end.
