//*************************************************************************************
//*                                 Fontes Delphi XE                                  *
//* Programa para importar arquvos EDI da Bayer para conferencia de entrada do armazém*
//*************************************************************************************
//*27/03/2012 - Criado por Enio José de Sousa                                         *
//*************************************************************************************
//*                                Alterações                                         *
//*                                                                                   *
//*00/00/0000- (descrever a alteração e o motivo) por Nome do desenvolvedor           *
//*************************************************************************************

unit UMov0011;

interface

uses Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, Dialogs,
     UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvEdit,
     BrvRetCon, DB, DBClient, BrvBitBtn, Grids, DBGrids, BrvString, BrvAlertProgress,
  BrvServerProgress, BrvCustomEdit, BrvListParam, ImgList, Menus;

type
  TMov0011 = class(TMov0000)
    CTNotas: TClientDataSet;
    PnlArquiv: TPanel;
    EdtNmArquiv: TBrvEdit;
    Label1: TLabel;
    Label2: TLabel;
    EdtCdArmaze: TBrvEdit;
    EdtNmArmaze: TBrvRetCon;
    BtnImport: TBrvBitBtn;
    PnlDados: TPanel;
    DbgContLog: TDBGrid;
    Panel1: TPanel;
    Panel2: TPanel;
    DbgNotas: TDBGrid;
    CTIteNota: TClientDataSet;
    STNotas: TDataSource;
    STIteNota: TDataSource;
    BtnRetornar: TBrvBitBtn;
    BtnGravar: TBrvBitBtn;
    CTNotasNrNota: TIntegerField;
    CTNotasNrSerie: TStringField;
    CTNotasCjRemete: TStringField;
    CTNotasCdTitula: TIntegerField;
    CTNotasRsTitula: TStringField;
    CTIteNotaNrNota: TIntegerField;
    CTIteNotaQtProdut: TFloatField;
    CTIteNotaCdProdut: TIntegerField;
    CTProduto: TClientDataSet;
    CTProdutoCdProdut: TIntegerField;
    CPProduto: TClientDataSet;
    Panel4: TPanel;
    Splitter1: TSplitter;
    CTProdutoDsProdut: TStringField;
    CTTitula: TClientDataSet;
    CTTitulaCdTitula: TIntegerField;
    CTTitulaRsTitula: TStringField;
    CTTitulaCjTitula: TStringField;
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
    DbgItem: TDBGrid;
    Splitter2: TSplitter;
    CTContLog: TClientDataSet;
    IntegerField1: TIntegerField;
    FloatField1: TFloatField;
    StringField2: TStringField;
    DateField1: TDateField;
    IntegerField2: TIntegerField;
    STContLog: TDataSource;
    CTIteNotaDsProdut: TStringField;
    CTContLogCdEmbala: TStringField;
    CTIteNotaCdEmbala: TStringField;
    CTProdutoCdEmbala: TStringField;
    BrvString: TBrvString;
    CTNotasNrFornec: TStringField;
    CTNotasCjDestin: TStringField;
    CdsW007: TClientDataSet;
    CTNotassnduplic: TStringField;
    BrvAlertProgress: TBrvAlertProgress;
    BrvServerProgress: TBrvServerProgress;
    procedure BtnImportClick(Sender: TObject);
    procedure BtnRetornarClick(Sender: TObject);
    procedure BtnGravarClick(Sender: TObject);
    procedure CTNotasAfterScroll(DataSet: TDataSet);
    procedure CTIteNotaAfterScroll(DataSet: TDataSet);
    procedure DbgNotasDrawColumnCell(Sender: TObject; const Rect: TRect; DataCol: Integer;
      Column: TColumn; State: TGridDrawState);
  private
    { Private declarations }
    procedure CarregarDadosProduto(pCdEmbala : AnsiString);
    procedure CarregarDadosRemetenteNota(pCjEmiNot : AnsiString);
    function  RetornaData(pDsData : AnsiString) : TDateTime;
    function NotaExiste(pNrNota: String): String;
  public
    { Public declarations }
  end;

var
  Mov0011: TMov0011;
  gSnDuplic : Boolean;

implementation

uses UDmSrvApl, UClaSrv;

{$R *.dfm}


procedure TMov0011.BtnRetornarClick(Sender: TObject);
begin
      PnlDados.Visible  := False;
      PnlArquiv.Enabled := True;
end;

procedure TMov0011.BtnGravarClick(Sender: TObject);
var lConexao  : TSDmWmsClient;
    lDsResult : AnsiString;
begin
      inherited;

      if (gSnDuplic) then
      begin
            MessageDlg('AVISO' + Chr(13) + 'As notas duplicadas não serão importadas!',
                                                                          mtInformation, [mbOK], 0);
      end;

      try
            try
                BrvServerProgress.Start(Self.Caption, 'Importando...', 100, 10);
                lConexao  := TSDmWmsClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                lDsResult := lConexao.GravarOperacaoLogistica(DmSrvApl.BrvDicionario.Credencial,
                                                              DmSrvApl.BrvDicionario.UserCode,
                                                              Self.Name,
                                                              StrToInt(EdtCdArmaze.Text), 1,
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

function TMov0011.NotaExiste(pNrNota: String): String;
begin
      CdsW007.Close;
      CdsW007.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(162,
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

procedure TMov0011.BtnImportClick(Sender: TObject);
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
                                                                  Copy(lDsLinha,  2, 15);
                                      CTNotas.FieldByName('CjDestin').AsString :=
                                                                  Copy(lDsLinha,  87, 14);
                                      CTNotas.FieldByName('NrFornec').AsString :=
                                                                  Copy(lDsLinha, 1004, 10);
                                      CarregarDadosRemetenteNota(
                                                CTNotas.FieldByName('CjRemete').AsString);
                                      CTNotas.FieldByName('SnDuplic').AsString   :=
                                                                  NotaExiste(Copy(lDsLinha, 18, 9));

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

function TMov0011.RetornaData(pDsData: AnsiString): TDateTime;
var lDsData : String;
begin
      lDsData := Copy(pDsData, 7, 2) + '/' +
                 Copy(pDsData, 5, 2) + '/' +
                 Copy(pDsData, 1, 4);

      Result := StrToDateDef(lDsData, 0);
end;

procedure TMov0011.CarregarDadosProduto(pCdEmbala : AnsiString);
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
                CTProduto.Append;
                CTProduto.FieldByName('CdEmbala').AsString  :=
                                              CPProduto.FieldByName('CdEmbala').AsString;
                CTProduto.FieldByName('CdProdut').AsInteger :=
                                              CPProduto.FieldByName('CdProdut').AsInteger;
                CTProduto.FieldByName('DsProdut').AsString  :=
                                              CPProduto.FieldByName('DsProdut').AsString;
                CTProduto.Post;
          end;

          CTIteNota.FieldByName('CdProdut').AsInteger :=
                                              CTProduto.FieldByName('CdProdut').AsInteger;
          CTIteNota.FieldByName('DsProdut').AsString  :=
                                              CTProduto.FieldByName('DsProdut').AsString;
      finally
          CPProduto.Close;
      end;
end;

procedure TMov0011.CarregarDadosRemetenteNota(pCjEmiNot : AnsiString);
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

procedure TMov0011.CTIteNotaAfterScroll(DataSet: TDataSet);
begin
      if CTIteNota.FieldByName('NrNota').AsInteger > 0 then
      begin
            CTContLog.Filter   :=
                         'NrNota = '        + CTIteNota.FieldByName('NrNota').AsString +
                         ' and CdEmbala = ' + QuotedStr(CTIteNota.FieldByName('CdEmbala').AsString);
            CTContLog.Filtered := True;
      end;
end;

procedure TMov0011.CTNotasAfterScroll(DataSet: TDataSet);
begin
      if CTNotas.FieldByName('NrNota').AsInteger > 0 then
      begin
            CTIteNota.Filter   := 'NrNota = ' + CTNotas.FieldByName('NrNota').AsString;
            CTIteNota.Filtered := True;
      end;
end;

procedure TMov0011.DbgNotasDrawColumnCell(Sender: TObject; const Rect: TRect; DataCol: Integer;
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

initialization
      RegisterClass(TMov0011);

finalization
      UnRegisterClass(TMov0011);

end.
