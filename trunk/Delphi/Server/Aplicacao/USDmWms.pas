unit USDmWms;

interface

uses
  SysUtils, Classes, DSServer, BrvProvider, BrvDataSet, DbClient, DB, BrvClientDataSet, DBXOracle,
  SqlExpr, BrvConnection, Provider, FMTBcd;

type
  TConDinam = record
      ConProvider : TBrvProvider;
      ConDataSet  : TBrvDataSet;
      ConOcupado  : Boolean;
  end;

  TSDmWms = class(TDSServerModule)
    SqlConnWMS: TBrvConnection;
    QcOpeLog: TBrvDataSet;
    DcOpeLog: TBrvProvider;
    CcOpeLog: TBrvClientDataSet;
    QExecute: TBrvDataSet;
    procedure SqlConnWMSAfterConnect(Sender: TObject);
  private
    procedure StrToClientDataSet(pDsLista: String; CcDataTmp: TClientDataSet);
    function ClientDataSetToStr(CcDataTmp: TClientDataSet): String;
    function UltimoDiaMes(Mes, Ano: Word): Word;
    { Private declarations }
  public
    { Public declarations }
    function TestaConexao(pIdTeste, pDsMensag : AnsiString): AnsiString;

    function AutenticaUsuarioColetor(pDsLogin: String; var pCdSenha, pCdUsuari: Integer;
                                                                    var pNmUsuari: String): Boolean;

    function ProximaAtividade(pCdUsuari: Integer; var pNrMapa, pDsAtivid: AnsiString): AnsiString;

    function AtividadeNaoConvocada(pCdUsuari: Integer; var pDsLista: AnsiString): AnsiString;

    function ConsultaAtividade(pNrFornec: AnsiString; pSgTipAti : AnsiString;
                           var pNrOpeLog: AnsiString): AnsiString;

    function ConferenciaEntradaIniciar(pCdUsuari: Integer; pNrMapa: AnsiString;
                                       var pNrNota, pNrFornec, pDsLista: AnsiString): AnsiString;

    function ConferenciaEntradaFinalizar(pCdUsuari: Integer; pNrMapa, pDsLista, pSnCancel: string;
                                         pQtProSId: Integer): AnsiString;

    function ConferenciaSaidaIniciar(pCdUsuari: Integer; pNrMapa: AnsiString;
                                     var pNrNota, pNrFornec, pDsLista: AnsiString): AnsiString;

    function ConferenciaSaidaFinalizar(pCdUsuari: Integer; pNrMapa,
                                       pDsLista, pCdClient, pSnCancel: string;
                                       pQtProSId: Integer): AnsiString;

    function GravarOperacaoLogistica(pDsCreden: AnsiString; pCdUsuari: Integer;
                                     pNmFormul: AnsiString; pCdArmaze: Integer;
                                     pCdTipOpe: Integer;    pCdsW005 : OleVariant;
                                     pCdsW006 : OleVariant; pCdsW007 : OleVariant): AnsiString;

    function GravaPrioridadeSituacaoOpeLog(pCdArmaze: AnsiString;
                                           PDsListOp : AnsiString): AnsiString;

    function RefazerAtividade(pCdArmaze, pNrOpeLog: AnsiString): AnsiString;

    function CancelarOpeLog(pNrOpeLog: AnsiString; pCdUsuari: AnsiString): AnsiString;

    function GravarOpeLogEndere(pDsCreden: AnsiString; pDtParam: OleVariant): AnsiString;

    function ConsultaPalletEnderecamento(pCdUsuari: Integer; pNrPallet : AnsiString;
                                         var pDsLista: AnsiString): AnsiString;

    function FechaPalletEnderecamento(pLsPallet: AnsiString): AnsiString;
  end;

implementation

uses UFrmLogos, UFrmConSrv, UDmDicion;

{$R *.dfm}

function TSDmWms.UltimoDiaMes(Mes, Ano: Word): Word;
begin
      DecodeDate(IncMonth(EncodeDate(Ano, Mes, 01), 1) - 1, Ano, Mes, Result);
end;

procedure TSDmWms.SqlConnWMSAfterConnect(Sender: TObject);
begin
      SqlConnWms.ExecuteDirect('alter session set nls_numeric_characters = ''.,''');
end;

function TSDmWms.AtividadeNaoConvocada(pCdUsuari: Integer; var pDsLista: AnsiString): AnsiString;
var lCcAtvPas : TClientDataSet;
begin
      try
          lCcAtvPas := TClientDataSet.Create(self);
          lCcAtvPas.Close;

          lCcAtvPas.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(146,
                            '<%CdUsuari%>;' + FormatFloat('0', pCdUsuari),
                            Name + '.AtividadeNaoConvocada');
          lCcAtvPas.Open;

          if (lCcAtvPas.RecordCount > 0) then
          begin
                pDsLista := ClientDataSetToStr(lCcAtvPas);
          end else
          begin
                pDsLista := '';
          end;

          Result := '';
      except
          On E: Exception Do
          Begin
                Result := E.Message;
          End;
      end;
end;

function TSDmWms.ClientDataSetToStr(CcDataTmp: TClientDataSet): String;
var nrindice : Integer;
    dstxtpro : string;
    DsLista  : TStrings;
begin
      try
          CcDataTmp.DisableControls;
          CcDataTmp.First;
          Result   := '';
          dstxtpro := '';
          DsLista  := TStringList.Create;

          for nrindice := 0 to CcDataTmp.Fields.Count -1 do
          begin
                dstxtpro := dstxtpro + UpperCase(CcDataTmp.Fields[nrindice].FieldName) + ';';
          end;

          DsLista.Add(copy(dstxtpro, 1, Length(dstxtpro)-1));

          while not CcDataTmp.Eof do
          begin
                dstxtpro := '';
                for nrindice := 0 to CcDataTmp.Fields.Count -1 do
                begin
                      dstxtpro := dstxtpro + StringReplace(UpperCase(
                                                     CcDataTmp.Fields[nrindice].AsString), ',', '.',
                                                                             [rfReplaceAll]) + ';';
                end;

                DsLista.Add(copy(dstxtpro, 1, Length(dstxtpro)-1));
                CcDataTmp.Next;
          end;

          CcDataTmp.First;
          Result := DsLista.Text;
      finally
          FreeAndNil(DsLista);
          CcDataTmp.EnableControls;
      end;
end;

procedure TSDmWms.StrToClientDataSet(pDsLista : String; CcDataTmp: TClientDataSet);
var nrindice : Integer;
    nridxcmp : Integer;
    dstxtpro : string;
    DsLista  : TStrings;
begin
      DsLista := TStringList.Create;
      DsLista.Text := pDsLista;

      if (CcDataTmp.Active) then
      begin
            CcDataTmp.EmptyDataSet;
            CcDataTmp.Close;
            CcDataTmp.Fields.Clear;
            CcDataTmp.FieldDefs.Clear;
      end;

      dstxtpro := DsLista.Strings[0] + ';';

      while (dstxtpro <> '') do
      begin
            CcDataTmp.FieldDefs.Add(copy(dstxtpro, 1, Pos(';', dstxtpro)-1), ftString, 150);
            dstxtpro := copy(dstxtpro, Pos(';', dstxtpro)+1, Length(dstxtpro));
      end;

      CcDataTmp.CreateDataSet;
      CcDataTmp.Open;

      for nrindice := 1 to DsLista.Count-1 do
      begin
            dstxtpro := DsLista.Strings[nrindice] + ';';
            nridxcmp := 0;

            CcDataTmp.Append;
            while (dstxtpro <> '') do
            begin
                  CcDataTmp.Fields[nridxcmp].AsString := StringReplace(copy(dstxtpro, 1,
                                                   Pos(';', dstxtpro)-1), '.', ',', [rfReplaceAll]);
                  dstxtpro := copy(dstxtpro, Pos(';', dstxtpro)+1, Length(dstxtpro));
                  Inc(nridxcmp);
            end;
            CcDataTmp.Post;
      end;

end;

function TSDmWms.TestaConexao(pIdTeste, pDsMensag: AnsiString): AnsiString;
var lDsArqRec : TStrings;
    lNmArquiv : String;
begin
      if (Trim(pIdTeste) = EmptyStr) then
      begin
            lNmArquiv := 'TranfColetor_' +
                         FormatDateTime('ddmmyyyyhhmmss', Now()) + '.txt';
      end else
      begin
            lNmArquiv := pIdTeste + '_' +
                         FormatDateTime('ddmmyyyyhhmmss', Now()) + '.txt';
      end;

      try
          lDsArqRec := TStringList.Create;
          lDsArqRec.Add(pDsMensag);
          lDsArqRec.SaveToFile(lNmArquiv);
      finally
          FreeAndNil(lDsArqRec);
      end;

end;

function TSDmWms.ProximaAtividade(pCdUsuari: Integer;
                                                    var pNrMapa, pDsAtivid: AnsiString): AnsiString;
var lCdsW004 : TClientDataSet;
    lCdsW005 : TClientDataSet;
    lCdsAtiv : TClientDataSet;
begin
      try
          lCdsW004 := TClientDataSet.Create(Self);
          lCdsW005 := TClientDataSet.Create(Self);
          lCdsAtiv := TClientDataSet.Create(Self);

          Result    := UFrmLogos.cDsMsgOk;
          pNrMapa   := '';
          pDsAtivid := '';

          //Consulta Atividades Pendentes
          lCdsAtiv.Close;
          lCdsAtiv.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(121,
                           '<%CdUsuari%>;' + FormatFloat('0', pCdUsuari),
                           Name + '.ProximaAtividade');
          lCdsAtiv.Open;

          if (lCdsAtiv.RecordCount = 0) then
          begin
                // Consulta Lista de Atividades de Convocação Ativa
                lCdsW004.Close;
                lCdsW004.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(116,
                                 '<%CdUsuari%>;' + FormatFloat('0', pCdUsuari),
                                 Name + '.ProximaAtividade');
                lCdsW004.Open;

                lCdsW004.First;
                pDsAtivid := '';

                if (lCdsW004.RecordCount > 0) then
                begin
                      while ((not lCdsW004.Eof) and (pDsAtivid = ''))do
                      begin
                            // Consulta Operação Logística Aguardando para ser executada
                            lCdsW005.Close;
                            lCdsW005.Data := DmDicion.BrvDicionario.
                                             RetornaDadosSqlCadastro(117,
                                             '<%CdTipAti%>;' +
                                             lCdsW004.FieldByName('CdTipAti').AsString,
                                             Name + '.ProximaAtividade');
                            lCdsW005.Open;

                            if (lCdsW005.RecordCount > 0) then
                            begin
                                  Result    :=UFrmLogos.cDsMsgOk;
                                  pNrMapa   :=lCdsW005.FieldByName('NrOpeLog').AsString;
                                  pDsAtivid :=lCdsW005.FieldByName('SgTipAti').AsString;
                            end;

                            lCdsW004.Next;
                      end;
                end else
                begin
                      Result    := UFrmLogos.cDsMsgOk;
                      pNrMapa   := '';
                      pDsAtivid := '';
                end;
          end else
          begin
                Result    := UFrmLogos.cDsMsgOk;
                pNrMapa   := lCdsAtiv.FieldByName('NrOpeLog').AsString;
                pDsAtivid := lCdsAtiv.FieldByName('sgtipati').AsString;
          end;

      finally
          FreeAndNil(lCdsW004);
          FreeAndNil(lCdsW005);
          FreeAndNil(lCdsAtiv);
      end;
end;

function TSDmWms.ConferenciaEntradaIniciar(pCdUsuari: Integer;
                                           pNrMapa: AnsiString;
                                           var pNrNota: AnsiString;
                                           var pNrFornec: AnsiString;
                                           var pDsLista: AnsiString): AnsiString;
var lCdsW007 : TClientDataSet;
    lDsTransa : TTransactionDesc;
begin
      try
          lCdsW007 := TClientDataSet.Create(Self);

          try
              lDsTransa := DmDicion.NumeroTransactionID;
              SqlConnWMS.StartTransaction(lDsTransa);

              Result := UFrmLogos.cDsMsgOk;

              lCdsW007.Close;
              lCdsW007.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(119,
                                        '<%NrOpeLog%>;' + pNrMapa,
                                        Name + '.ConferenciaEntradaIniciar');
              lCdsW007.Open;

              pNrNota   := lCdsW007.FieldByName('nrnforig').AsString;
              pNrFornec := lCdsW007.FieldByName('NrFornec').AsString;

              lCdsW007.Close;
              lCdsW007.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(118,
                                        '<%NrOpeLog%>;' + pNrMapa,
                                        Name + '.ConferenciaEntradaIniciar');
              lCdsW007.Open;

              pDsLista := ClientDataSetToStr(lCdsW007);

              CcOpeLog.Close;
              CcOpeLog.BrParams.Clear;
              CcOpeLog.BrParams.Add('<%NrOpeLog%>;' + pNrMapa);
              CcOpeLog.Open;

              if (CcOpeLog.FieldByName('StOpeLog').AsString = 'A') then
              begin
                    CcOpeLog.Edit;
                    CcOpeLog.FieldByName('DtIniOpe').AsDateTime := Now();
                    CcOpeLog.FieldByName('CdUsuExe').AsInteger  := pCdUsuari;
                    CcOpeLog.FieldByName('StOpeLog').AsString   := 'P';
                    CcOpeLog.Post;

                    CcOpeLog.BrApplyUpdates;
              end;

              SqlConnWMS.Commit(lDsTransa);
          except
                 on E: Exception do
                 begin
                       SqlConnWMS.Rollback(lDsTransa);
                       Result := UFrmLogos.cDsMsgEr + E.Message;
                 end;
          end;
      finally
          FreeAndNil(lCdsW007);
      end;
end;

function TSDmWms.ConferenciaEntradaFinalizar(pCdUsuari: Integer;
                                             pNrMapa, pdslista, pSnCancel: string;
                                             pQtProSId: Integer): AnsiString;
var CcLisAux  : TClientDataSet;
    LCdsw007  : TClientDataSet;
    LCdsAux   : TClientDataSet;

    lDsTransa : TTransactionDesc;
    lCdsW008  : TBrvClientDataSet;
    lProW008  : TBrvProvider;
    lDtsW008  : TBrvDataSet;

    lCdsW006  : TBrvClientDataSet;
    lProW006  : TBrvProvider;
    lDtsW006  : TBrvDataSet;

    lDsLisAux : TStrings;
    lNrSeqRet : Integer;
    lDtProduc : String;
    lDtValida : String;
    lNRSEQOPE : Integer;
    lStOpeLog : String;
    lCdArmaze : String;
    lNrMes    : String;
    lNrAno    : String;
begin
      try
          try
              try
                  lDsLisAux := TStringList.Create;
                  lDsLisAux.Add('Finalização de conferência ');
                  lDsLisAux.Add('--------------------------------------------------------');
                  lDsLisAux.Add('Mapa........:' + pNrMapa);
                  lDsLisAux.Add('Usuário.....:' + FormatFloat('0', pCdUsuari));
                  lDsLisAux.Add('Cancelamento:' + pSnCancel);
                  lDsLisAux.Add('Sem Registro:' + FormatFloat('0', pQtProSid));
                  lDsLisAux.Add('Lista');
                  lDsLisAux.Add(pdslista);
                  lDsLisAux.SaveToFile('Mapa_Entrada_' + FormatFloat('00000000', StrToInt(pNrMapa)) +
                                               '_' + FormatDateTime('yymmdd_hhmm', Now()) + '.log');
              finally
                  FreeAndNil(lDsLisAux);
              end;

              lDsTransa := DmDicion.NumeroTransactionID;
              SqlConnWMS.StartTransaction(lDsTransa);

              CcLisAux  := TClientDataSet.Create(nil);
              LCdsw007  := TClientDataSet.Create(nil);
              LCdsAux   := TClientDataSet.Create(nil);

              LCdsAux.Close;
              LCdsAux.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(126,
                                                     '<%NrOpeLog%>;' + pNrMapa,
                                                     Name + '.ConferenciaEntradaFinalizar');
              LCdsAux.Open;

              lNRSEQOPE := LCdsAux.FieldByName('NRSEQOPE').AsInteger;
              Inc(lNRSEQOPE);

              LCdsAux.Close;
              LCdsAux.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(148,
                                                     '<%NrOpeLog%>;' + pNrMapa,
                                                     Name + '.ConferenciaSaidaFinalizar');
              LCdsAux.Open;

              lNrSeqRet := LCdsAux.FieldByName('NRSEQRET').AsInteger;
              lStOpeLog := 'F';

              if (pSnCancel = 'N') then
              begin
                    lCdsW008 := TBrvClientDataSet.Create(Self);
                    lProW008 := TBrvProvider.Create(self);
                    lDtsW008 := TBrvDataSet.Create(self);

                    lCdsW006 := TBrvClientDataSet.Create(Self);
                    lProW006 := TBrvProvider.Create(self);
                    lDtsW006 := TBrvDataSet.Create(self);

                    lProW008.DataSet       := lDtsW008;
                    lProW008.Options       := [poAllowCommandText,poUseQuoteChar];
                    lProW008.Name          := 'lProW008';
                    lDtsW008.SQLConnection := SqlConnWMS;
                    lDtsW008.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW008.ProviderName  := 'lProW008';
                    lCdsW008.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW008.BrQueryCode   := 123;
                    lCdsW008.BrFormName    := Name + '.ConferenciaEntradaFinalizar';
                    lCdsW008.BrUserCode    := pCdUsuari;
                    lCdsW008.Open;

                    lProW006.DataSet       := lDtsW006;
                    lProW006.Options       := [poAllowCommandText,poUseQuoteChar];
                    lProW006.Name          := 'lProW006';
                    lDtsW006.SQLConnection := SqlConnWMS;
                    lDtsW006.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW006.ProviderName  := 'lProW006';
                    lCdsW006.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW006.BrQueryCode   := 125;
                    lCdsW006.BrFormName    := Name + '.ConferenciaEntradaFinalizar';
                    lCdsW006.BrUserCode    := pCdUsuari;
                    lCdsW006.Open;

                    if (Trim(pDslista) = '') then
                    begin
                          if (CcLisAux = nil) then
                          begin
                                CcLisAux := TClientDataSet.Create(Self);
                          end;
                    end else
                    begin
                          StrToClientDataSet(pDslista, CcLisAux);
                    end;

                    if (CcLisAux.Active) then
                    begin
                          CcLisAux.First;
                          while not CcLisAux.Eof do
                          begin
                                LCdsw007.Close;
                                LCdsw007.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(122,
                                     '<%NROPELOG%>;' + pNrMapa + Chr(13) +
                                     '<%DSLOTE%>;'   + CcLisAux.FieldByName('NRLOTE').AsString +
                                     Chr(13) +
                                     '<%CDEMBALA%>;' + CcLisAux.FieldByName('NRGTIN').AsString +
                                     Chr(13) +
                                     '<%DTVALIDA%>;' + CcLisAux.FieldByName('DTVALID').AsString,
                                     Name + '.ConferenciaEntradaFinalizar');
                                LCdsw007.Open;

                                LCdsw007.First;

                                if (LCdsw007.RecordCount > 0) then
                                begin
                                      if (CcLisAux.FieldByName('STITEM').AsString <> 'C') then
                                      begin
                                            QExecute.CommandText :=
                                               'Update W006 Set STITEOPE = ' +
                                                QuotedStr(CcLisAux.FieldByName('STITEM').AsString) +
                                               'Where NROPELOG = ' + pNrMapa +
                                               ' and  CDEMBALA = ' +
                                                 QuotedStr(CcLisAux.FieldByName('NRGTIN').AsString);
                                            QExecute.ExecSQL(True);
                                      end;

                                      if (CcLisAux.FieldByName('STITEM').AsString = 'D') then
                                      begin
                                            lStOpeLog := 'D';
                                      end;

                                      lNrMes := Copy(CcLisAux.FieldByName('DTPRODUC').AsString,3,2);
                                      lNrAno := Copy(CcLisAux.FieldByName('DTPRODUC').AsString,1,2);

                                      if (Trim(lNrMes + lNrAno) <> '') then
                                      begin
                                            lDtProduc := FormatFloat('00',
                                                   UltimoDiaMes(StrToInt(lNrMes), StrToInt(lNrAno)))
                                                   + '/' + lNrMes + '/' + lNrAno;
                                      end else
                                      begin
                                            lDtProduc := '';
                                      end;

                                      lNrMes := Copy(CcLisAux.FieldByName('DTVALID').AsString,3,2);
                                      lNrAno := Copy(CcLisAux.FieldByName('DTVALID').AsString,1,2);

                                      if (Trim(lNrMes + lNrAno) <> '') then
                                      begin
                                            lDtValida := FormatFloat('00',
                                                   UltimoDiaMes(StrToInt(lNrMes), StrToInt(lNrAno)))
                                                   + '/' + lNrMes + '/' + lNrAno;
                                      end else
                                      begin
                                            lDtValida := '';
                                      end;

                                      lCdsW008.Append;
                                      lCdsW008.FieldByName('CDARMAZE').AsInteger :=
                                                         lCdsW007.FieldByName('CDARMAZE').AsInteger;
                                      lCdsW008.FieldByName('CDPROPRI').AsInteger :=
                                                         lCdsW007.FieldByName('CDPROPRI').AsInteger;
                                      lCdsW008.FieldByName('DSLOTE').AsString    :=
                                                         lCdsW007.FieldByName('DSLOTE'  ).AsString;

                                      if (Trim(lDtValida) <> EmptyStr) then
                                      begin
                                            lCdsW008.FieldByName('DTVALIDA').AsDateTime:=
                                                                               StrToDate(lDtValida);
                                      end;

                                      lCdsW008.FieldByName('NRNFORIG').AsInteger :=
                                                         lCdsW007.FieldByName('NRNFORIG').AsInteger;
                                      lCdsW008.FieldByName('NROPELOG').AsInteger :=
                                                         lCdsW007.FieldByName('NROPELOG').AsInteger;
                                      lCdsW008.FieldByName('NRSENFOR').AsString  :=
                                                         lCdsW007.FieldByName('NRSENFOR').AsString;
                                      lCdsW008.FieldByName('NRSEQCON').AsInteger :=
                                                         lCdsW007.FieldByName('NRSEQCON').AsInteger;
                                      lCdsW008.FieldByName('NRSEQOPE').AsInteger :=
                                                         lCdsW007.FieldByName('NRSEQOPE').AsInteger;
                                      lCdsW008.FieldByName('NRSEQRET').AsInteger := lNrSeqRet;
                                      lCdsW008.FieldByName('QTPRODUT').AsFloat :=
                                                         CcLisAux.FieldByName('QTPRODUT').AsFloat;

                                      if (Trim(lDtProduc) <> EmptyStr) then
                                      begin
                                            lCdsW008.FieldByName('DTPRODUC').AsDateTime:=
                                                                               StrToDate(lDtProduc);
                                      end;

                                      lCdsW008.FieldByName('NRSSCC'  ).AsString  :=
                                                        CcLisAux.FieldByName('NRSSCC').AsString;
                                      lCdsW008.FieldByName('DTCOLETA').AsDateTime:=
                                                        CcLisAux.FieldByName('DTCOLETA').AsDateTime;
                                      lCdsW008.FieldByName('STCONFCC').AsString  := 'A';
                                      lCdsW008.Post;
                                end else
                                begin
                                      LCdsw007.Close;
                                      LCdsw007.Data :=
                                                 DmDicion.BrvDicionario.RetornaDadosSqlCadastro(124,
                                                 '<%NROPELOG%>;' + pNrMapa,
                                                 Name + '.ConferenciaEntradaFinalizar');
                                      LCdsw007.Open;

                                      lNrMes := Copy(CcLisAux.FieldByName('DTPRODUC').AsString,3,2);
                                      lNrAno := Copy(CcLisAux.FieldByName('DTPRODUC').AsString,1,2);

                                      if (Trim(lNrMes + lNrAno) <> '') then
                                      begin
                                            lDtProduc := FormatFloat('00',
                                                   UltimoDiaMes(StrToInt(lNrMes), StrToInt(lNrAno)))
                                                   + '/' + lNrMes + '/' + lNrAno;
                                      end else
                                      begin
                                            lDtProduc := '';
                                      end;

                                      lNrMes := Copy(CcLisAux.FieldByName('DTVALID').AsString,3,2);
                                      lNrAno := Copy(CcLisAux.FieldByName('DTVALID').AsString,1,2);

                                      if (Trim(lNrMes + lNrAno) <> '') then
                                      begin
                                            lDtValida := FormatFloat('00',
                                                   UltimoDiaMes(StrToInt(lNrMes), StrToInt(lNrAno)))
                                                   + '/' + lNrMes + '/' + lNrAno;
                                      end else
                                      begin
                                            lDtValida := '';
                                      end;

                                      if (LCdsw006.Locate('CdEmbala',
                                                            CcLisAux.FieldByName('NRGTIN').AsString,
                                                                          [loCaseInsensitive])) then
                                      begin
                                            LCdsw006.Edit;
                                            LCdsw006.FieldByName('QTPRODUT').AsFloat :=
                                                     LCdsw006.FieldByName('QTPRODUT').AsFloat +
                                                     CcLisAux.FieldByName('QTPRODUT').AsFloat;
                                            LCdsw006.Post;
                                      end else
                                      begin
                                            lStOpeLog := 'D';

                                            LCdsw006.Append;
                                            LCdsw006.FieldByName('CDARMAZE').AsInteger :=
                                                         lCdsW007.FieldByName('CDARMAZE').AsInteger;
                                            LCdsw006.FieldByName('CDEMBALA').AsString  :=
                                                         CcLisAux.FieldByName('NRGTIN').AsString;
                                            LCdsw006.FieldByName('NROPELOG').AsInteger :=
                                                                                  StrToInt(pNrMapa);
                                            LCdsw006.FieldByName('NRSEQOPE').AsInteger := lNRSEQOPE;
                                            LCdsw006.FieldByName('QTPRODUT').AsFloat :=
                                                         CcLisAux.FieldByName('QTPRODUT').AsFloat;
                                            LCdsw006.FieldByName('STITEOPE').AsString := 'C';
                                            LCdsw006.Post;
                                            Inc(lNRSEQOPE);
                                      end;

                                      LCdsw006.BrApplyUpdates;

                                      lCdsW008.Append;
                                      lCdsW008.FieldByName('CDARMAZE').AsInteger :=
                                                         lCdsW007.FieldByName('CDARMAZE').AsInteger;
                                      lCdsW008.FieldByName('CDPROPRI').AsInteger :=
                                                         lCdsW007.FieldByName('CDPROPRI').AsInteger;
                                      lCdsW008.FieldByName('DSLOTE').AsString    :=
                                                         CcLisAux.FieldByName('NRLOTE'  ).AsString;

                                      if (Trim(lDtValida) <> '') then
                                      begin
                                            lCdsW008.FieldByName('DTVALIDA').AsDateTime:=
                                                                               StrToDate(lDtValida);
                                      end;

                                      lCdsW008.FieldByName('NRNFORIG').AsInteger :=
                                                         lCdsW007.FieldByName('NRNFORIG').AsInteger;
                                      lCdsW008.FieldByName('NROPELOG').AsInteger :=
                                                                                  StrToInt(pNrMapa);
                                      lCdsW008.FieldByName('NRSENFOR').AsString  :=
                                                          lCdsW007.FieldByName('NRSENFOR').AsString;
                                      lCdsW008.FieldByName('NRSEQCON').AsInteger := 1;
                                      lCdsW008.FieldByName('NRSEQOPE').AsInteger :=
                                                         LCdsw006.FieldByName('NRSEQOPE').AsInteger;
                                      lCdsW008.FieldByName('NRSEQRET').AsInteger := lNrSeqRet;
                                      lCdsW008.FieldByName('QTPRODUT').AsFloat :=
                                                         CcLisAux.FieldByName('QTPRODUT').AsFloat;

                                      if (Trim(lDtProduc) <> '') then
                                      begin
                                            lCdsW008.FieldByName('DTPRODUC').AsDateTime:=
                                                                               StrToDate(lDtProduc);
                                      end;

                                      lCdsW008.FieldByName('NRSSCC'  ).AsString  :=
                                                            CcLisAux.FieldByName('NRSSCC').AsString;
                                      lCdsW008.FieldByName('DTCOLETA').AsDateTime:=
                                                        CcLisAux.FieldByName('DTCOLETA').AsDateTime;
                                      lCdsW008.FieldByName('STCONFCC').AsString  := 'A';
                                      lCdsW008.Post;
                                end;

                                Inc(lNrSeqRet);
                                CcLisAux.Next;
                          end;
                    end;

                    lCdsW008.BrApplyUpdates;

                    CcOpeLog.Close;
                    CcOpeLog.BrParams.Clear;
                    CcOpeLog.BrParams.Add('<%NrOpeLog%>;' + pNrMapa);
                    CcOpeLog.Open;

                    if (pQtProSId > 0) then
                    begin
                          lStOpeLog := 'D';
                    end;

                    CcOpeLog.Edit;
                    CcOpeLog.FieldByName('DtFimOpe').AsDateTime:= Now();
                    CcOpeLog.FieldByName('StOpeLog').AsString  := lStOpeLog;
                    CcOpeLog.FieldByName('QtProSId').AsInteger := pQtProSId;
                    CcOpeLog.Post;

                    CcOpeLog.BrApplyUpdates;
              end else
              begin
                    CcOpeLog.Close;
                    CcOpeLog.BrParams.Clear;
                    CcOpeLog.BrParams.Add('<%NrOpeLog%>;' + pNrMapa);
                    CcOpeLog.Open;

                    CcOpeLog.Edit;
                    CcOpeLog.FieldByName('DtIniOpe').AsString  := '';
                    CcOpeLog.FieldByName('CdUsuExe').AsInteger := 0;
                    CcOpeLog.FieldByName('StOpeLog').AsString  := 'A';
                    CcOpeLog.FieldByName('QtProSId').AsInteger := 0;
                    CcOpeLog.Post;

                    lCdArmaze := CcOpeLog.FieldByName('CdArmaze').AsString;

                    //Excluindo controles logisticos lidos/conferidos
                    QExecute.CommandText := 'Update W008 Set StConfCc = ''E'' ' +
                                            'Where  NrOpeLog = ' + pNrMapa + ' and ' +
                                            '       CdArmaze = ' + lCdArmaze;
                    QExecute.ExecSQL(True);

                    //Excluindo itens não esperados pela atividade
                    QExecute.CommandText := 'Update w006 Set stiteope = ''E'' ' +
                                            'Where  NrOpeLog = ' + pNrMapa + ' and ' +
                                            '       CdArmaze = ' + lCdArmaze + ' and ' +
                                            '       stiteope = ''C'' ';
                    QExecute.ExecSQL(True);

                    //Removendo condição de divergencia
                    QExecute.CommandText := 'Update w006 Set stiteope = ''N'' ' +
                                            'Where  NrOpeLog = ' + pNrMapa + ' and ' +
                                            '       CdArmaze = ' + lCdArmaze + ' and ' +
                                            '       stiteope = ''D'' ';;
                    QExecute.ExecSQL(True);

                    CcOpeLog.BrApplyUpdates;
              end;

              SqlConnWMS.Commit(lDsTransa);
          except
              On E: exception do
              begin
                    SqlConnWMS.Rollback(lDsTransa);
                    Result := UFrmLogos.cDsMsgEr + E.Message;
              end;
          end;

          Result := UFrmLogos.cDsMsgOk;
      finally
          FreeAndNil(CcLisAux);
          FreeAndNil(LCdsw007);
          FreeAndNil(lCdsW008);
          FreeAndNil(lProW008);
          FreeAndNil(lDtsW008);
          FreeAndNil(lCdsW006);
          FreeAndNil(lProW006);
          FreeAndNil(lDtsW006);
      end;
end;

function TSDmWms.RefazerAtividade(pCdArmaze, pNrOpeLog: AnsiString): AnsiString;
var lDsTransa : TTransactionDesc;
begin
      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnWMS.StartTransaction(lDsTransa);

          CcOpeLog.Close;
          CcOpeLog.BrParams.Clear;
          CcOpeLog.BrParams.Add('<%NrOpeLog%>;' + pNrOpeLog);
          CcOpeLog.Open;

          //Passando atividade para status de Processando
          CcOpeLog.Edit;
          CcOpeLog.FieldByName('DtFimOpe').AsString := '';
          CcOpeLog.FieldByName('StOpeLog').AsString := 'P';
          CcOpeLog.FieldByName('QtProSid').AsInteger:= 0;
          CcOpeLog.Post;

          //Excluindo controles logisticos lidos/conferidos
          QExecute.CommandText := 'Update W008 Set StConfCc = ''E'' ' +
                                  'Where  NrOpeLog = ' + pNrOpeLog + ' and ' +
                                  '       CdArmaze = ' + pCdArmaze;
          QExecute.ExecSQL(True);

          //Excluindo itens não esperados pela atividade
          QExecute.CommandText := 'Update w006 Set stiteope = ''E'' ' +
                                  'Where  NrOpeLog = ' + pNrOpeLog + ' and ' +
                                  '       CdArmaze = ' + pCdArmaze + ' and ' +
                                  '       stiteope = ''C'' ';
          QExecute.ExecSQL(True);

          //Removendo condição de divergencia
          QExecute.CommandText := 'Update w006 Set stiteope = ''N'' ' +
                                  'Where  NrOpeLog = ' + pNrOpeLog + ' and ' +
                                  '       CdArmaze = ' + pCdArmaze + ' and ' +
                                  '       stiteope = ''D'' ';;
          QExecute.ExecSQL(True);

          CcOpeLog.BrApplyUpdates;

          SqlConnWMS.Commit(lDsTransa);
      except
           on E: Exception do
           begin
                 SqlConnWMS.Rollback(lDsTransa);
                 Result := UFrmLogos.cDsMsgEr + E.Message;
           end;
      end;
end;

function TSDmWms.CancelarOpeLog(pNrOpeLog: AnsiString; pCdUsuari: AnsiString): AnsiString;
var lDsTransa : TTransactionDesc;
begin
      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnWMS.StartTransaction(lDsTransa);

          CcOpeLog.Close;
          CcOpeLog.BrParams.Clear;
          CcOpeLog.BrParams.Add('<%NrOpeLog%>;' + pNrOpeLog);
          CcOpeLog.Open;

          CcOpeLog.Edit;
          CcOpeLog.FieldByName('CDUSUEXE').AsString   := pCdUsuari;
          CcOpeLog.FieldByName('DTINIOPE').AsDateTime := Now();
          CcOpeLog.FieldByName('DTFIMOPE').AsDateTime := Now();
          CcOpeLog.FieldByName('StOpeLog').AsString   := 'C';
          CcOpeLog.Post;

          CcOpeLog.BrApplyUpdates;

          SqlConnWMS.Commit(lDsTransa);
      except
           on E: Exception do
           begin
                 SqlConnWMS.Rollback(lDsTransa);
                 Result := UFrmLogos.cDsMsgEr + E.Message;
           end;
      end;
end;

function TSDmWms.GravaPrioridadeSituacaoOpeLog(pCdArmaze: AnsiString;
                                               PDsListOp: AnsiString): AnsiString;
var lDsTransa : TTransactionDesc;
    lLsOperac : TStrings;
    lNrIdx    : Integer;
    lNrOpeLog : String;
    lNrPriori : String;
    lStOpeLog : String;
    lDsLinha  : String;
begin
      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnWMS.StartTransaction(lDsTransa);

          lLsOperac := TStringList.Create;
          lLsOperac.Text := PDsListOp;

          for lNrIdx := 0 to lLsOperac.Count-1 do
          begin
                lDsLinha  := lLsOperac.Strings[lNrIdx];
                lNrOpeLog := Copy(lDsLinha, 1, Pos(',', lDsLinha)-1);
                lDsLinha  := Copy(lDsLinha, Pos(',', lDsLinha)+ 1, length(lDsLinha));
                lNrPriori := Copy(lDsLinha, 1, Pos(',', lDsLinha)-1);
                lStOpeLog := Copy(lDsLinha, Pos(',', lDsLinha)+ 1, length(lDsLinha));

                CcOpeLog.Close;
                CcOpeLog.BrParams.Clear;
                CcOpeLog.BrParams.Add('<%NrOpeLog%>;' + lNrOpeLog);
                CcOpeLog.Open;

                CcOpeLog.Edit;
                CcOpeLog.FieldByName('NrPriori').AsInteger := StrToInt(lNrPriori);
                CcOpeLog.FieldByName('StOpeLog').AsString  := lStOpeLog;
                CcOpeLog.Post;

                CcOpeLog.BrApplyUpdates;
          end;

          SqlConnWMS.Commit(lDsTransa);
      except
           on E: Exception do
           begin
                 SqlConnWMS.Rollback(lDsTransa);
                 Result := UFrmLogos.cDsMsgEr + E.Message;
           end;
      end;
end;

function TSDmWms.GravarOpeLogEndere(pDsCreden: AnsiString; pDtParam: OleVariant): AnsiString;
var lCpW005   : TClientDataSet;
    lCcW005   : TBrvClientDataSet;
    lDcW005   : TBrvProvider;
    lQcW005   : TBrvDataSet;
    lCpParam  : TClientDataSet;
    lDsTransa : TTransactionDesc;
    lNrOpeLog : Integer;
    lNrSeqOpe : Integer;
    lNrSeqCon : Integer;
    lNrMes    : Integer;
    lNrAno    : Integer;
    lDtValida : String;
    lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial Invalida!';
      end else
      begin
            try
                try
                    lDsTransa := DmDicion.NumeroTransactionID;
                    SqlConnWMS.StartTransaction(lDsTransa);

                    lCcW005 := TBrvClientDataSet.Create(Self);
                    lDcW005 := TBrvProvider.Create(self);
                    lQcW005 := TBrvDataSet.Create(self);

                    lCpParam := TClientDataSet.Create(Self);
                    lCpW005  := TClientDataSet.Create(Self);

                    lCpParam.XMLData := pDtParam;
                    lCpW005.XMLData  := lCpParam.FieldByName('pXmlW005').AsString;

                    //Provider
                    lDcW005.DataSet       := lQcW005;
                    lDcW005.Options       := [poAllowCommandText, poUseQuoteChar];
                    lDcW005.Name          := 'lDcW005';

                    //DataSet
                    lQcW005.SQLConnection := SqlConnWMS;
                    lQcW005.BrDicionario  := DmDicion.BrvDicionario;

                    //Client Data Set
                    lCcW005.ProviderName  := 'lDcW005';
                    lCcW005.BrDicionario  := DmDicion.BrvDicionario;
                    lCcW005.BrQueryCode   := 260;
                    lCcW005.BrFormName    := lCpParam.FieldByName('pNmFormul').AsString;
                    lccW005.BrUserCode    := lCdUsuari;
                    lCcW005.Open;

                    lCpW005.First;

                    while not lCpW005.Eof do
                    begin
                          lNrOpeLog := DmDicion.BrvDicionario.ProximoValorSequencial(0,13);

                          lCcW005.Append;
                          lCcW005.FieldByName('CDARMAZE').AsInteger  :=
                                                        lCpParam.FieldByName('pCdArmaze').AsInteger;
                          lCcW005.FieldByName('NROPELOG').AsInteger  := lNrOpeLog;
                          lCcW005.FieldByName('CDTIPATI').AsInteger  := 5;
                          lCcW005.FieldByName('CDUSUGER').AsInteger  := lCdUsuari;
                          lCcW005.FieldByName('DTGERACA').AsDateTime := Now();
                          lCcW005.FieldByName('CDUSUEXE').AsInteger  := 0;
                          lCcW005.FieldByName('STOPELOG').AsString   := 'A';
                          lCcW005.FieldByName('NRPRIORI').AsInteger  := 9;
                          lCcW005.FieldByName('NRDOCTO' ).AsString   :=
                                                          lCpW005.FieldByName('NRDOCTO' ).AsString;
                          lCcW005.FieldByName('NRORDEM' ).AsString   :=
                                                          lCpW005.FieldByName('NRORDEM' ).AsString;

                          lCcW005.FieldByName('SQENDERE').AsInteger  :=
                                                  lCpW005.FieldByName('SQENDDES').AsInteger;

                          lCcW005.FieldByName('DSENDERE').AsString :=
                                                  FormatFloat('00',
                                                  lCpW005.FieldByName('NRRUADES').AsInteger) + '.' +
                                                  FormatFloat('000',
                                                  lCpW005.FieldByName('NRPREDES').AsInteger) + '.' +
                                                  FormatFloat('00',
                                                  lCpW005.FieldByName('NRNIVDES').AsInteger) +
                                                  lCpW005.FieldByName('DSBLODES').AsString;

                          lCcW005.FieldByName('SQENDMAS').AsInteger  := StrToIntDef(
                                                lCpW005.FieldByName('SQENDMAS').AsString, 0);

                          lCcW005.FieldByName('DSENDMAS').AsString :=
                                               FormatFloat('00',  StrToIntDef(
                                               lCpW005.FieldByName('NRRUAMAS').AsString, 0)) + '.' +
                                               FormatFloat('000', StrToIntDef(
                                               lCpW005.FieldByName('NRPREMAS').AsString, 0)) + '.' +
                                               FormatFloat('00', StrToIntDef(
                                               lCpW005.FieldByName('NRNIVMAS').AsString, 0)) +
                                               lCpW005.FieldByName('DSBLOMAS').AsString;
                          lCcW005.Post;

                          lCpW005.Next;
                    end;

                    lCcW005.BrApplyUpdates;

                    SqlConnWMS.Commit(lDsTransa);
                except
                     on E: Exception do
                     begin
                           SqlConnWMS.Rollback(lDsTransa);
                           Result := UFrmLogos.cDsMsgEr + E.Message;
                     end;
                end;

            finally
                FreeAndNil(lCcW005);
                FreeAndNil(lDcW005);
                FreeAndNil(lQcW005);
                FreeAndNil(lCpParam);
                FreeAndNil(lCpW005);
            end;
      end;
end;

function TSDmWms.GravarOperacaoLogistica(pDsCreden: AnsiString; pCdUsuari: Integer;
                                         pNmFormul: AnsiString; pCdArmaze: Integer;
                                         pCdTipOpe: Integer;
                                         pCdsW005, pCdsW006, pCdsW007: OleVariant): AnsiString;
var lCdsW005p : TClientDataSet;
    lCdsW006p : TClientDataSet;
    lCdsW007p : TClientDataSet;

    lCdsW005  : TBrvClientDataSet;
    lCdsW006  : TBrvClientDataSet;
    lCdsW007  : TBrvClientDataSet;

    lProW005  : TBrvProvider;
    lProW006  : TBrvProvider;
    lProW007  : TBrvProvider;

    lDtsW005  : TBrvDataSet;
    lDtsW006  : TBrvDataSet;
    lDtsW007  : TBrvDataSet;

    lNrOpeLog : Integer;
    lNrSeqOpe : Integer;
    lNrSeqCon : Integer;
    lDsTransa : TTransactionDesc;
    lNrMes : Integer;
    lNrAno : Integer;
    lDtValida : String;
begin
      if not FrmLogos.CredencialValida(pDsCreden, pCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial Invalida!';
      end else
      begin
            try
                try
                    lDsTransa := DmDicion.NumeroTransactionID;
                    SqlConnWMS.StartTransaction(lDsTransa);

                    lCdsW005 := TBrvClientDataSet.Create(Self);
                    lCdsW006 := TBrvClientDataSet.Create(Self);
                    lCdsW007 := TBrvClientDataSet.Create(Self);

                    lProW005 := TBrvProvider.Create(self);
                    lProW006 := TBrvProvider.Create(self);
                    lProW007 := TBrvProvider.Create(self);

                    lDtsW005 := TBrvDataSet.Create(self);
                    lDtsW006 := TBrvDataSet.Create(self);
                    lDtsW007 := TBrvDataSet.Create(self);

                    lProW005.DataSet       := lDtsW005;
                    lProW005.Options       := [poAllowCommandText,poUseQuoteChar];
                    lProW005.Name          := 'lProW005';

                    lDtsW005.SQLConnection := SqlConnWMS;
                    lDtsW005.BrDicionario  := DmDicion.BrvDicionario;

                    lCdsW005.ProviderName  := 'lProW005';
                    lCdsW005.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW005.BrQueryCode   := 113;
                    lCdsW005.BrFormName    := pNmFormul;
                    lCdsW005.BrUserCode    := pCdUsuari;
                    lCdsW005.Open;

                    lProW006.DataSet       := lDtsW006;
                    lProW006.Options       := [poAllowCommandText,poUseQuoteChar];
                    lProW006.Name          := 'lProW006';
                    lDtsW006.SQLConnection := SqlConnWMS;
                    lDtsW006.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW006.ProviderName  := 'lProW006';
                    lCdsW006.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW006.BrQueryCode   := 114;
                    lCdsW006.BrFormName    := pNmFormul;
                    lCdsW006.BrUserCode    := pCdUsuari;
                    lCdsW006.Open;

                    lProW007.DataSet       := lDtsW007;
                    lProW007.Options       := [poAllowCommandText,poUseQuoteChar];
                    lProW007.Name          := 'lProW007';
                    lDtsW007.SQLConnection := SqlConnWMS;
                    lDtsW007.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW007.ProviderName  := 'lProW007';
                    lCdsW007.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW007.BrQueryCode   := 115;
                    lCdsW007.BrFormName    := pNmFormul;
                    lCdsW007.BrUserCode    := pCdUsuari;
                    lCdsW007.Open;

                    lCdsW005p := TClientDataSet.Create(Self);
                    lCdsW006p := TClientDataSet.Create(Self);
                    lCdsW007p := TClientDataSet.Create(Self);

                    lCdsW005p.Close;
                    lCdsW005p.Data := pCdsW005;
                    lCdsW005p.Open;

                    lCdsW006p.Close;
                    lCdsW006p.Data := pCdsW006;
                    lCdsW006p.Open;

                    lCdsW007p.Close;
                    lCdsW007p.Data := pCdsW007;
                    lCdsW007p.Open;

                    lCdsW005p.Filtered := false;
                    lCdsW005p.Filter   := 'snduplic = ' + QuotedStr('NAO');
                    lCdsW005p.Filtered := true;

                    lCdsW005p.First;

                    while not lCdsW005p.Eof do
                    begin
                          lNrOpeLog := DmDicion.BrvDicionario.ProximoValorSequencial(0,13);

                          lCdsW005.Append;
                          lCdsW005.FieldByName('CDARMAZE').AsInteger  := pCdArmaze;
                          lCdsW005.FieldByName('NROPELOG').AsInteger  := lNrOpeLog;
                          lCdsW005.FieldByName('CDTIPATI').AsInteger  := pCdTipOpe;
                          lCdsW005.FieldByName('CDUSUGER').AsInteger  := pCdUsuari;
                          lCdsW005.FieldByName('DTGERACA').AsDateTime := Now();
                          lCdsW005.FieldByName('CDUSUEXE').AsInteger  := 0;
                          lCdsW005.FieldByName('STOPELOG').AsString   := 'A';
                          lCdsW005.FieldByName('NRPRIORI').AsInteger  := 9;

                          if (pCdTipOpe = 2) then
                          begin
                                if (Copy(lCdsW005p.FieldByName('CjRemete').AsString,1,8) =
                                    Copy(lCdsW005p.FieldByName('CjDestin').AsString,1,8)) then
                                begin
                                      lCdsW005.FieldByName('CDTIPATI').AsInteger  := 3;
                                end;
                          end else
                          begin
                                if (pCdTipOpe = 1) then
                                begin
                                      if (Copy(lCdsW005p.FieldByName('CjRemete').AsString,1,8) =
                                          Copy(lCdsW005p.FieldByName('CjDestin').AsString,1,8)) then
                                      begin
                                            lCdsW005.FieldByName('CDTIPATI').AsInteger  := 4;

                                      end;
                                end;
                          end;

                          lCdsW005.Post;

                          lCdsW006p.Filtered := False;
                          lCdsW006p.Filter   := 'NrNota = ' +
                                                           lCdsW005p.FieldByName('NrNota').AsString;
                          lCdsW006p.Filtered := True;

                          lCdsW006p.First;
                          lNrSeqOpe := 1;


                          while not lCdsW006p.Eof do
                          begin

                                lCdsW006.Append;
                                lCdsW006.FieldByName('CDARMAZE').AsInteger := pCdArmaze;
                                lCdsW006.FieldByName('CDEMBALA').AsString  :=
                                                         lCdsW006p.FieldByName('CDEMBALA').AsString;
                                lCdsW006.FieldByName('NROPELOG').AsInteger := lNrOpeLog;
                                lCdsW006.FieldByName('NRSEQOPE').AsInteger := lNrSeqOpe;
                                lCdsW006.FieldByName('QTPRODUT').AsFloat   :=
                                                         lCdsW006p.FieldByName('QTPRODUT').AsFloat;
                                lCdsW006.FieldByName('STITEOPE').AsString  := 'N';
                                lCdsW006.Post;


                                lCdsW007p.Filtered := False;
                                lCdsW007p.Filter   :=
                                              'NrNota = ' +
                                              lCdsW005p.FieldByName('NrNota').AsString +
                                              ' and CdEmbala = ' +
                                              QuotedStr(lCdsW006p.FieldByName('CDEMBALA').AsString);
                                lCdsW007p.Filtered := True;
                                lCdsW007p.First;

                                lNrSeqCon := 1;

                                if (lCdsW007p.RecordCount > 0) then
                                begin
                                      while not lCdsW007p.Eof do
                                      begin
                                            lCdsW007.Append;
                                            lCdsW007.FieldByName('CDARMAZE').AsInteger := pCdArmaze;
                                            lCdsW007.FieldByName('NROPELOG').AsInteger := lNrOpeLog;
                                            lCdsW007.FieldByName('CDPROPRI').AsInteger :=
                                                       lCdsW005p.FieldByName('CdTitula').AsInteger;
                                            lCdsW007.FieldByName('DSLOTE'  ).AsString  :=
                                                       lCdsW007p.FieldByName('DSLOTE').AsString;

                                            lNrMes := StrToInt(FormatDateTime('mm',
                                                     lCdsW007p.FieldByName('DTVALIDA').AsDateTime));
                                            lNrAno := StrToInt(FormatDateTime('yyyy',
                                                     lCdsW007p.FieldByName('DTVALIDA').AsDateTime));

                                            lDtValida := IntToStr(UltimoDiaMes(lNrMes, lNrAno)) +
                                                      '/' + FormatDateTime('mm/yyyy',
                                                      lCdsW007p.FieldByName('DTVALIDA').AsDateTime);

                                            lCdsW007.FieldByName('DTVALIDA').AsDateTime:=
                                                                               StrToDate(lDtValida);

                                            lCdsW007.FieldByName('NRNFORIG').AsInteger :=
                                                       lCdsW005p.FieldByName('NrNota').AsInteger;
                                            lCdsW007.FieldByName('NRSENFOR').AsString  :=
                                                       lCdsW005p.FieldByName('NrSerie').AsString;
                                            lCdsW007.FieldByName('NRSEQCON').AsInteger := lNrSeqCon;
                                            lCdsW007.FieldByName('NRSEQOPE').AsInteger := lNrSeqOpe;
                                            lCdsW007.FieldByName('QTPRODUT').AsFloat   :=
                                                       lCdsW007p.FieldByName('QTPRODUT').AsFloat;
                                            lCdsW007.FieldByName('NrFornec').AsString  :=
                                                       lCdsW005p.FieldByName('NrFornec').AsString;
                                            lCdsW007.Post;

                                            inc(lNrSeqCon);

                                            lCdsW007p.Next;
                                      end;
                                end else
                                begin
                                      lCdsW007.Append;
                                      lCdsW007.FieldByName('CDARMAZE').AsInteger  := pCdArmaze;
                                      lCdsW007.FieldByName('NROPELOG').AsInteger  := lNrOpeLog;
                                      lCdsW007.FieldByName('CDPROPRI').AsInteger  :=
                                                       lCdsW005p.FieldByName('CdTitula').AsInteger;
                                      lCdsW007.FieldByName('DSLOTE'  ).AsString   := '';
                                      lCdsW007.FieldByName('NRNFORIG').AsInteger  :=
                                                       lCdsW005p.FieldByName('NrNota').AsInteger;
                                      lCdsW007.FieldByName('NRSENFOR').AsString   :=
                                                       lCdsW005p.FieldByName('NrSerie').AsString;
                                      lCdsW007.FieldByName('NRSEQCON').AsInteger := lNrSeqCon;
                                      lCdsW007.FieldByName('NRSEQOPE').AsInteger := lNrSeqOpe;
                                      lCdsW007.FieldByName('QTPRODUT').AsFloat   :=
                                                       lCdsW006p.FieldByName('QTPRODUT').AsFloat;
                                      lCdsW007.FieldByName('NrFornec').AsString  :=
                                                       lCdsW005p.FieldByName('NrFornec').AsString;
                                      lCdsW007.Post;

                                      inc(lNrSeqCon);
                                end;

                                Inc(lNrSeqOpe);

                                lCdsW006p.Next;
                          end;
                          lCdsW005p.Next;
                    end;

                    lCdsW005.BrApplyUpdates;
                    lCdsW006.BrApplyUpdates;
                    lCdsW007.BrApplyUpdates;

                    SqlConnWMS.Commit(lDsTransa);
                except
                     on E: Exception do
                     begin
                           SqlConnWMS.Rollback(lDsTransa);
                           Result := UFrmLogos.cDsMsgEr + E.Message;
                     end;
                end;

            finally
                FreeAndNil(lCdsW005p);
                FreeAndNil(lCdsW006p);
                FreeAndNil(lCdsW007p);
                FreeAndNil(lCdsW005);
                FreeAndNil(lCdsW006);
                FreeAndNil(lCdsW007);
                FreeAndNil(lProW005);
                FreeAndNil(lProW006);
                FreeAndNil(lProW007);
                FreeAndNil(lDtsW005);
                FreeAndNil(lDtsW006);
                FreeAndNil(lDtsW007);
            end;
      end;
end;

function TSDmWms.ConferenciaSaidaIniciar(pCdUsuari: Integer;
                                pNrMapa: AnsiString;
                                var pNrNota, pNrFornec, pDsLista: AnsiString): AnsiString;
var lCdsW007  : TClientDataSet;
    lCdsW007C : TClientDataSet;
    lDsTransa : TTransactionDesc;
begin
      try
          lCdsW007 := TClientDataSet.Create(Self);
          lCdsW007C:= TClientDataSet.Create(Self);

          try
              lDsTransa := DmDicion.NumeroTransactionID;
              SqlConnWMS.StartTransaction(lDsTransa);

              Result := UFrmLogos.cDsMsgOk;

              lCdsW007.Close;
              lCdsW007.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(128,
                                        '<%NrOpeLog%>;' + pNrMapa,
                                        Name + '.ConferenciaEntradaIniciar');
              lCdsW007.Open;

              pNrNota   := lCdsW007.FieldByName('nrnforig').AsString;
              pNrFornec := lCdsW007.FieldByName('NrFornec').AsString;

              lCdsW007.Close;
              lCdsW007.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(127,
                                        '<%NrOpeLog%>;' + pNrMapa,
                                        Name + '.ConferenciaEntradaIniciar');
              lCdsW007.Open;

              lCdsW007C := TClientDataSet.Create(Self);
              lCdsW007C.FieldDefs.Clear;
              lCdsW007C.FieldDefs.Add('NRGTIN'  , ftString, 20);
              lCdsW007C.FieldDefs.Add('DSPRODUT', ftString, 50);
              lCdsW007C.FieldDefs.Add('QTPRODUT', ftFloat ,  0);
              lCdsW007C.FieldDefs.Add('NRLOTE'  , ftString, 20);
              lCdsW007C.FieldDefs.Add('DTVALID' , ftString,  6);
              lCdsW007C.CreateDataSet;

              lCdsW007.First;

              while not lCdsW007.Eof do
              begin
                    lCdsW007C.Append;
                    lCdsW007C.FieldByName('NRGTIN'  ).AsString :=
                                                          lCdsW007.FieldByName('NRGTIN'  ).AsString;
                    lCdsW007C.FieldByName('DSPRODUT').AsString :=
                                                          lCdsW007.FieldByName('DSPRODUT').AsString;
                    lCdsW007C.FieldByName('QTPRODUT').AsFloat  :=
                                                          lCdsW007.FieldByName('QTPRODUT').AsFloat;
                    lCdsW007C.FieldByName('NRLOTE'  ).AsString :=
                                                          lCdsW007.FieldByName('NRLOTE'  ).AsString;
                    lCdsW007C.FieldByName('DTVALID' ).AsString := FormatDateTime('YYMM00',
                                                       lCdsW007.FieldByName('DTVALID' ).AsDateTime);
                    lCdsW007C.Post;

                    lCdsW007.Next;
              end;

              pDsLista := ClientDataSetToStr(lCdsW007C);

              CcOpeLog.Close;
              CcOpeLog.BrParams.Clear;
              CcOpeLog.BrParams.Add('<%NrOpeLog%>;' + pNrMapa);
              CcOpeLog.Open;

              if (CcOpeLog.FieldByName('StOpeLog').AsString = 'A') then
              begin
                    CcOpeLog.Edit;
                    CcOpeLog.FieldByName('DtIniOpe').AsDateTime := Now();
                    CcOpeLog.FieldByName('CdUsuExe').AsInteger  := pCdUsuari;
                    CcOpeLog.FieldByName('StOpeLog').AsString   := 'P';
                    CcOpeLog.Post;

                    CcOpeLog.BrApplyUpdates;
              end;

              SqlConnWMS.Commit(lDsTransa);
          except
                 on E: Exception do
                 begin
                       SqlConnWMS.Rollback(lDsTransa);
                       Result := UFrmLogos.cDsMsgEr + E.Message;
                 end;
          end;
      finally
          FreeAndNil(lCdsW007);
      end;
end;

function TSDmWms.ConsultaAtividade(pNrFornec, pSgTipAti: AnsiString;
                               var pNrOpeLog: AnsiString): AnsiString;
var CcAtvFor : TClientDataSet;
begin
      try
          CcAtvFor := TClientDataSet.Create(self);
          CcAtvFor.Close;
          CcAtvFor.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(147,
                            '<%SgTipAti%>;' + pSgTipAti + Chr(13) +
                            '<%NrFornec%>;' + pNrFornec,
                            Name + '.AtividadeNaoConvocada');
          CcAtvFor.Open;

          if (CcAtvFor.RecordCount > 0) then
          begin
                pNrOpeLog := CcAtvFor.FieldByName('NrOpeLog').AsString;
                Result := '';
          end else
          begin
                pNrOpeLog := '';
                Result := 'Nao Encontrado!!!';
          end;

      except
          On E: Exception Do
          Begin
                Result := E.Message;
          End;
      end;
end;

function TSDmWms.FechaPalletEnderecamento(pLsPallet: AnsiString): AnsiString;
var lCpPallet : TClientDataSet;
    lDsTransa : TTransactionDesc;
begin
      try
          lCpPallet := TClientDataSet.Create(Self);
          StrToClientDataSet(pLsPallet, lCpPallet);

          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnWMS.StartTransaction(lDsTransa);

          QExecute.BrQueryCode := 268;

          lCpPallet.First;

          while not lCpPallet.eof do
          begin
                QExecute.BrParams.Clear;
                QExecute.BrParams.Add('<%NrOrdem%>;'  + lCpPallet.FieldByName('NrOrdem').AsString);
                QExecute.BrParams.Add('<%DtIniOpe%>;' + lCpPallet.FieldByName('DtIniOpe').AsString);
                QExecute.BrParams.Add('<%DtFinOpe%>;' + lCpPallet.FieldByName('DtFimOpe').AsString);
                QExecute.BrExecuteSQL(False);

                lCpPallet.Next;
          end;

          SqlConnWMS.Commit(lDsTransa);
          Result := UFrmLogos.cDsMsgOk;
      except
          on E : Exception do
          begin
                SqlConnWMS.Rollback(lDsTransa);
                Result := UFrmLogos.cDsMsgEr + E.Message;
          end;
      end;
end;

function TSDmWms.ConsultaPalletEnderecamento(pCdUsuari: Integer; pNrPallet: AnsiString;
                                             var pDsLista: AnsiString): AnsiString;
var lCpW005 : TClientDataSet;
begin
      try
          try
              lCpW005 := TClientDataSet.Create(Self);
              lCpW005.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(263,
                                                             '<%NrOrdem%>;' + pNrPallet, 'Coletor');

              if (lCpW005.RecordCount = 0) then
              begin
                    Result := UFrmLogos.cDsMsgEr + 'Pallet nao localizado!';
              end else
              begin
                    if (lCpW005.FieldByName('QtEnder').AsInteger = 0) then
                    begin
                          Result := UFrmLogos.cDsMsgEr + 'Enderecamento Finalizado!';
                    end else
                    begin
                          if (lCpW005.FieldByName('CdUsuExe').AsInteger = 0) then
                          begin
                                lCpW005.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(267,
                                         '<%CdUsuExe%>;' + FormatFloat('0', pCdUsuari) , 'Coletor');

                                if (lCpW005.FieldByName('QtReserva').AsInteger > 0) then
                                begin
                                      lCpW005.Data :=
                                                 DmDicion.BrvDicionario.RetornaDadosSqlCadastro(266,
                                                '<%NrOrdem%>;' + pNrPallet + #13 +
                                                '<%CdUsuExe%>;' + FormatFloat('0', pCdUsuari) ,
                                                'Coletor');

                                      if (lCpW005.RecordCount > 0) then
                                      begin
                                            // Reserva, monta e devolve lista
                                            QExecute.BrQueryCode := 265;
                                            QExecute.BrParams.Clear;
                                            QExecute.BrParams.Add('<%NrOrdem%>;' + pNrPallet);
                                            QExecute.BrParams.Add('<%CdUsuExe%>;' +
                                                                       FormatFloat('0', pCdUsuari));
                                            QExecute.BrExecuteSQL(False);

                                            lCpW005.Data :=
                                                     DmDicion.BrvDicionario.RetornaDadosSqlCadastro(
                                                        264, '<%NrOrdem%>;' + pNrPallet, 'Coletor');

                                            pDsLista := ClientDataSetToStr(lCpW005);
                                            result := UFrmLogos.cDsMsgOk;
                                      end else
                                      begin
                                            Result := UFrmLogos.cDsMsgEr +
                                                              'Operacao nao permitida! ' +
                                                              'Voce possui enderecamento pendente!';
                                      end;
                                end else
                                begin
                                      // Reserva, monta e devolve lista
                                      QExecute.BrQueryCode := 265;
                                      QExecute.BrParams.Clear;
                                      QExecute.BrParams.Add('<%NrOrdem%>;' + pNrPallet);
                                      QExecute.BrParams.Add('<%CdUsuExe%>;' +
                                                                       FormatFloat('0', pCdUsuari));
                                      QExecute.BrExecuteSQL(False);

                                      lCpW005.Data :=
                                                 DmDicion.BrvDicionario.RetornaDadosSqlCadastro(264,
                                                             '<%NrOrdem%>;' + pNrPallet, 'Coletor');

                                      pDsLista := ClientDataSetToStr(lCpW005);
                                      result := UFrmLogos.cDsMsgOk;
                                end;
                          end else
                          begin
                                if (lCpW005.FieldByName('CdUsuExe').AsInteger = pCdUsuari) then
                                begin
                                      // Monta e devolve lista
                                      lCpW005.Data :=
                                                 DmDicion.BrvDicionario.RetornaDadosSqlCadastro(264,
                                                 '<%NrOrdem%>;' + pNrPallet, 'Coletor');

                                      pDsLista := ClientDataSetToStr(lCpW005);
                                      result := UFrmLogos.cDsMsgOk;
                                end else
                                begin
                                      Result := UFrmLogos.cDsMsgEr +
                                                      'Enderecamento reservado para outro usuario!';
                                end;
                          end;
                    end;
              end;
          except
              on E: Exception do
              begin
                    result := UFrmLogos.cDsMsgEr + 'ConsultaPalletEnderecamento:' + E.Message;
              end;
          end;
      finally
          FreeAndNil(lCpW005);
      end;
end;

function TSDmWms.AutenticaUsuarioColetor(pDsLogin  : String;  var pCdSenha : Integer;
                                         var pCdUsuari : Integer; var pNmUsuari : String): Boolean;
var lDsSql   : String;
    lCdsTemp : TClientDataSet;
begin
      result := true;
      lDsSql := 'Select S001.NmComUsu, S001.CdUsuari, S001.CdSenha ' +
                'From   S001 S001 '    +
                'Where  S001.DsLogin =  ' + #39 + pDsLogin + #39;

      try
          lCdsTemp := TClientDataSet.Create(nil);
          try
              lCdsTemp.Data :=  DmDicion.BrvDicionario.RetornaDadosSqlFixa(lDsSql);

              if lCdsTemp.Eof then
              begin
                    result := false;
              end else
              begin
                    if FrmLogos.TblUser.RecordCount > 0 then
                    begin
                          if not FrmLogos.TblUser.FindKey([pDsLogin]) then
                          begin
                                result := false;;

                          end;
                    end;

                    if (Result) then
                    begin
                          pCdSenha  := lCdsTemp.FieldByName('CdSenha').AsInteger;
                          pCdUsuari := lCdsTemp.FieldByName('CdUsuari').AsInteger;
                          pNmUsuari := lCdsTemp.FieldByName('NmComUsu').AsString;
                    end;
              end;
          except
              pCdSenha  := 0;
              pCdUsuari := 0;
              pNmUsuari := '';
          end;
      finally
          FreeAndnIl(lCdsTemp);
      end;
end;

function TSDmWms.ConferenciaSaidaFinalizar(pCdUsuari: Integer; pNrMapa,
                                           pDsLista, pCdClient, pSnCancel: string;
                                           pQtProSId: Integer): AnsiString;
var CcLisAux  : TClientDataSet;
    LCdsw007  : TClientDataSet;
    LCdsAux   : TClientDataSet;

    lDsTransa : TTransactionDesc;
    lCdsW008  : TBrvClientDataSet;
    lProW008  : TBrvProvider;
    lDtsW008  : TBrvDataSet;

    lCdsW006  : TBrvClientDataSet;
    lProW006  : TBrvProvider;
    lDtsW006  : TBrvDataSet;

    lDsLisAux : TStrings;
    lNrSeqRet : Integer;
    lDtProduc : String;
    lDtValida : String;
    lNRSEQOPE : Integer;
    lStOpeLog : String;
    lNrMes    : String;
    lNrAno    : String;
    lCdArmaze : String;
begin

      try
          Result := UFrmLogos.cDsMsgOk;

          try
              try
                  lDsLisAux := TStringList.Create;
                  lDsLisAux.Add('Finalização de conferência ');
                  lDsLisAux.Add('--------------------------------------------------------');
                  lDsLisAux.Add('Mapa........:' + pNrMapa);
                  lDsLisAux.Add('Usuário.....:' + FormatFloat('0', pCdUsuari));
                  lDsLisAux.Add('Cancelamento:' + pSnCancel);
                  lDsLisAux.Add('Cliente.....:' + pCdClient);
                  lDsLisAux.Add('Sem Registro:' + FormatFloat('0', pQtProSid));
                  lDsLisAux.Add('Lista');
                  lDsLisAux.Add(pdslista);
                  lDsLisAux.SaveToFile('Mapa_Saida_' + FormatFloat('00000000', StrToInt(pNrMapa))
                                           + '_' + FormatDateTime('yymmdd_hhmm', Now()) + '.log');
              finally
                  FreeAndNil(lDsLisAux);
              end;

              lDsTransa := DmDicion.NumeroTransactionID;
              SqlConnWMS.StartTransaction(lDsTransa);

              CcLisAux  := TClientDataSet.Create(nil);
              LCdsw007  := TClientDataSet.Create(nil);
              LCdsAux   := TClientDataSet.Create(nil);

              LCdsAux.Close;
              LCdsAux.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(129,
                                                     '<%NrOpeLog%>;' + pNrMapa,
                                                     Name + '.ConferenciaSaidaFinalizar');
              LCdsAux.Open;

              lNRSEQOPE := LCdsAux.FieldByName('NRSEQOPE').AsInteger;
              Inc(lNRSEQOPE);

              LCdsAux.Close;
              LCdsAux.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(148,
                                                     '<%NrOpeLog%>;' + pNrMapa,
                                                     Name + '.ConferenciaSaidaFinalizar');
              LCdsAux.Open;

              lNrSeqRet := LCdsAux.FieldByName('NRSEQRET').AsInteger;
              lStOpeLog := 'F';

              if (pSnCancel = 'N') then
              begin
                    lCdsW008 := TBrvClientDataSet.Create(Self);
                    lProW008 := TBrvProvider.Create(self);
                    lDtsW008 := TBrvDataSet.Create(self);

                    lCdsW006 := TBrvClientDataSet.Create(Self);
                    lProW006 := TBrvProvider.Create(self);
                    lDtsW006 := TBrvDataSet.Create(self);

                    lProW008.DataSet       := lDtsW008;
                    lProW008.Options       := [poAllowCommandText,poUseQuoteChar];
                    lProW008.Name          := 'lProW008';
                    lDtsW008.SQLConnection := SqlConnWMS;
                    lDtsW008.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW008.ProviderName  := 'lProW008';
                    lCdsW008.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW008.BrQueryCode   := 130;
                    lCdsW008.BrFormName    := Name + '.ConferenciaSaidaFinalizar';
                    lCdsW008.BrUserCode    := pCdUsuari;
                    lCdsW008.Open;

                    lProW006.DataSet       := lDtsW006;
                    lProW006.Options       := [poAllowCommandText,poUseQuoteChar];
                    lProW006.Name          := 'lProW006';
                    lDtsW006.SQLConnection := SqlConnWMS;
                    lDtsW006.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW006.ProviderName  := 'lProW006';
                    lCdsW006.BrDicionario  := DmDicion.BrvDicionario;
                    lCdsW006.BrQueryCode   := 131;
                    lCdsW006.BrFormName    := Name + '.ConferenciaSaidaFinalizar';
                    lCdsW006.BrUserCode    := pCdUsuari;
                    lCdsW006.Open;

                    if (Trim(pDslista) = '') then
                    begin
                          StrToClientDataSet(
                            'NRMAPA;NRGTIN;NRLOTE;DTVALID;NRSSCC;DTPRODUC;DTCOLETA;QTPRODUT;STITEM',
                                                                                          CcLisAux);
                    end else
                    begin
                          StrToClientDataSet(pDsLista, CcLisAux);
                    end;

                    CcLisAux.First;
                    while not CcLisAux.Eof do
                    begin
                          if (CcLisAux.FieldByName('QTPRODUT').AsFloat = 0) then
                          begin
                                CcLisAux.Delete;
                          end else
                          begin
                                CcLisAux.Next;
                          end;
                    end;

                    CcLisAux.First;

                    while not CcLisAux.Eof do
                    begin
                          //Localiza produto na base importada a partir de uma linha bipada do coletor
                          LCdsw007.Close;
                          LCdsw007.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(132,
                               '<%NROPELOG%>;' + pNrMapa + Chr(13) +
                               '<%DSLOTE%>;'   + CcLisAux.FieldByName('NRLOTE').AsString +
                               Chr(13) +
                               '<%CDEMBALA%>;' + CcLisAux.FieldByName('NRGTIN').AsString +
                               Chr(13) +
                               '<%DTVALIDA%>;' + CcLisAux.FieldByName('DTVALID').AsString,
                               Name + '.ConferenciaSaidaFinalizar');
                          LCdsw007.Open;

                          LCdsw007.First;

                          if (LCdsw007.RecordCount > 0) then
                          begin
                                //Se o item do coletor for diferente de "C" consolidado atualiza
                                // com o status que viver do coletor.
                                if (CcLisAux.FieldByName('STITEM').AsString <> 'C') then
                                begin
                                      QExecute.CommandText :=
                                       'Update W006 Set STITEOPE = ' +
                                       QuotedStr(CcLisAux.FieldByName('STITEM'  ).AsString) +
                                       'Where NROPELOG = ' + pNrMapa +
                                       ' and  CDEMBALA = ' +
                                        QuotedStr(CcLisAux.FieldByName('NRGTIN').AsString);
                                        QExecute.ExecSQL(True);
                                end;

                                //Se um item apenas tiver o status de Divergente, toda a operação
                                // será divergente
                                if (CcLisAux.FieldByName('STITEM').AsString = 'D') then
                                begin
                                      lStOpeLog := 'D';
                                end;

                                //Extraindo informações bipadas para inserção de registro bipado w008

                                lNrMes := Copy(CcLisAux.FieldByName('DTPRODUC').AsString,3,2);
                                lNrAno := Copy(CcLisAux.FieldByName('DTPRODUC').AsString,1,2);
                                lDtProduc := FormatFloat('00',
                                             UltimoDiaMes(StrToInt(lNrMes), StrToInt(lNrAno)))
                                             + '/' + lNrMes + '/' + lNrAno;

                                lNrMes := Copy(CcLisAux.FieldByName('DTVALID').AsString,3,2);
                                lNrAno := Copy(CcLisAux.FieldByName('DTVALID').AsString,1,2);
                                lDtValida := FormatFloat('00',
                                             UltimoDiaMes(StrToInt(lNrMes), StrToInt(lNrAno)))
                                             + '/' + lNrMes + '/' + lNrAno;

                                lCdsW008.Append;
                                lCdsW008.FieldByName('CDARMAZE').AsInteger :=
                                                   lCdsW007.FieldByName('CDARMAZE').AsInteger;
                                lCdsW008.FieldByName('CDPROPRI').AsInteger :=
                                                   lCdsW007.FieldByName('CDPROPRI').AsInteger;
                                lCdsW008.FieldByName('DSLOTE').AsString    :=
                                                   lCdsW007.FieldByName('DSLOTE'  ).AsString;
                                lCdsW008.FieldByName('DTVALIDA').AsDateTime:=
                                                                         StrToDate(lDtValida);
                                lCdsW008.FieldByName('NRNFORIG').AsInteger :=
                                                   lCdsW007.FieldByName('NRNFORIG').AsInteger;
                                lCdsW008.FieldByName('NROPELOG').AsInteger :=
                                                   lCdsW007.FieldByName('NROPELOG').AsInteger;
                                lCdsW008.FieldByName('NRSENFOR').AsString  :=
                                                   lCdsW007.FieldByName('NRSENFOR').AsString;
                                lCdsW008.FieldByName('NRSEQCON').AsInteger :=
                                                   lCdsW007.FieldByName('NRSEQCON').AsInteger;
                                lCdsW008.FieldByName('NRSEQOPE').AsInteger :=
                                                   lCdsW007.FieldByName('NRSEQOPE').AsInteger;
                                lCdsW008.FieldByName('NRSEQRET').AsInteger := lNrSeqRet;
                                lCdsW008.FieldByName('QTPRODUT').AsFloat :=
                                                   CcLisAux.FieldByName('QTPRODUT').AsFloat;
                                lCdsW008.FieldByName('DTPRODUC').AsDateTime:=
                                                                         StrToDate(lDtProduc);
                                lCdsW008.FieldByName('NRSSCC'  ).AsString  :=
                                                  CcLisAux.FieldByName('NRSSCC').AsString;
                                lCdsW008.FieldByName('DTCOLETA').AsDateTime:=
                                                  CcLisAux.FieldByName('DTCOLETA').AsDateTime;
                                lCdsW008.FieldByName('CDCLIENT').AsInteger :=
                                                                          StrToInt(pCdClient);
                                lCdsW008.FieldByName('STCONFCC').AsString  := 'A';
                                lCdsW008.Post;
                          end else
                          begin
                                //Não localizou item na base de importados do arquivo.
                                //Busca itens importados para inserir informações de nota, cliente, etc.
                                LCdsw007.Close;
                                LCdsw007.Data :=
                                           DmDicion.BrvDicionario.RetornaDadosSqlCadastro(133,
                                           '<%NROPELOG%>;' + pNrMapa,
                                           Name + '.ConferenciaSaidaFinalizar');
                                LCdsw007.Open;

                                lNrMes := Copy(CcLisAux.FieldByName('DTPRODUC').AsString,3,2);
                                lNrAno := Copy(CcLisAux.FieldByName('DTPRODUC').AsString,1,2);

                                if (Trim(lNrMes + lNrAno) <> '') then
                                begin
                                      lDtProduc := FormatFloat('00',
                                             UltimoDiaMes(StrToInt(lNrMes), StrToInt(lNrAno)))
                                             + '/' + lNrMes + '/' + lNrAno;
                                end else
                                begin
                                      lDtProduc := '';
                                end;

                                lNrMes := Copy(CcLisAux.FieldByName('DTVALID').AsString,3,2);
                                lNrAno := Copy(CcLisAux.FieldByName('DTVALID').AsString,1,2);

                                if (Trim(lNrMes + lNrAno) <> '') then
                                begin
                                      lDtValida := FormatFloat('00',
                                             UltimoDiaMes(StrToInt(lNrMes), StrToInt(lNrAno)))
                                             + '/' + lNrMes + '/' + lNrAno;
                                end else
                                begin
                                      lDtValida := '';
                                end;

                                if (LCdsw006.Locate('CdEmbala',
                                                      CcLisAux.FieldByName('NRGTIN').AsString,
                                                                    [loCaseInsensitive])) then
                                begin
                                      LCdsw006.Edit;
                                      LCdsw006.FieldByName('QTPRODUT').AsFloat :=
                                               LCdsw006.FieldByName('QTPRODUT').AsFloat +
                                               CcLisAux.FieldByName('QTPRODUT').AsFloat;
                                      LCdsw006.Post;
                                end else
                                begin
                                      lStOpeLog := 'D';

                                      LCdsw006.Append;
                                      LCdsw006.FieldByName('CDARMAZE').AsInteger :=
                                                   lCdsW007.FieldByName('CDARMAZE').AsInteger;
                                      LCdsw006.FieldByName('CDEMBALA').AsString  :=
                                                   CcLisAux.FieldByName('NRGTIN').AsString;
                                      LCdsw006.FieldByName('NROPELOG').AsInteger :=
                                                                            StrToInt(pNrMapa);
                                      LCdsw006.FieldByName('NRSEQOPE').AsInteger := lNRSEQOPE;
                                      LCdsw006.FieldByName('QTPRODUT').AsFloat :=
                                                   CcLisAux.FieldByName('QTPRODUT').AsFloat;
                                      LCdsw006.FieldByName('STITEOPE').AsString := 'C';
                                      LCdsw006.Post;

                                      Inc(lNRSEQOPE);
                                end;

                                LCdsw006.BrApplyUpdates;

                                lCdsW008.Append;
                                lCdsW008.FieldByName('CDARMAZE').AsInteger :=
                                                   lCdsW007.FieldByName('CDARMAZE').AsInteger;
                                lCdsW008.FieldByName('CDPROPRI').AsInteger :=
                                                   lCdsW007.FieldByName('CDPROPRI').AsInteger;
                                lCdsW008.FieldByName('DSLOTE').AsString    :=
                                                   CcLisAux.FieldByName('NRLOTE'  ).AsString;

                                if (Trim(lDtValida) <> '') then
                                begin
                                      lCdsW008.FieldByName('DTVALIDA').AsDateTime:=
                                                                         StrToDate(lDtValida);
                                end;

                                lCdsW008.FieldByName('NRNFORIG').AsInteger :=
                                                   lCdsW007.FieldByName('NRNFORIG').AsInteger;
                                lCdsW008.FieldByName('NROPELOG').AsInteger :=
                                                                            StrToInt(pNrMapa);
                                lCdsW008.FieldByName('NRSENFOR').AsString  :=
                                                    lCdsW007.FieldByName('NRSENFOR').AsString;
                                lCdsW008.FieldByName('NRSEQCON').AsInteger := 1;
                                lCdsW008.FieldByName('NRSEQOPE').AsInteger :=
                                                   LCdsw006.FieldByName('NRSEQOPE').AsInteger;
                                lCdsW008.FieldByName('NRSEQRET').AsInteger := lNrSeqRet;
                                lCdsW008.FieldByName('QTPRODUT').AsFloat :=
                                                   CcLisAux.FieldByName('QTPRODUT').AsFloat;

                                if (Trim(lDtProduc) <> '') then
                                begin
                                      lCdsW008.FieldByName('DTPRODUC').AsDateTime:=
                                                                         StrToDate(lDtProduc);
                                end;

                                lCdsW008.FieldByName('NRSSCC'  ).AsString  :=
                                                      CcLisAux.FieldByName('NRSSCC').AsString;
                                lCdsW008.FieldByName('DTCOLETA').AsDateTime:=
                                                  CcLisAux.FieldByName('DTCOLETA').AsDateTime;
                                lCdsW008.FieldByName('CDCLIENT').AsInteger :=
                                                                          StrToInt(pCdClient);
                                lCdsW008.FieldByName('STCONFCC').AsString  := 'A';
                                lCdsW008.Post;
                          end;

                          Inc(lNrSeqRet);
                          CcLisAux.Next;
                    end;

                    lCdsW008.BrApplyUpdates;

                    CcOpeLog.Close;
                    CcOpeLog.BrParams.Clear;
                    CcOpeLog.BrParams.Add('<%NrOpeLog%>;' + pNrMapa);
                    CcOpeLog.Open;

                    if (pQtProSId > 0) then
                    begin
                          lStOpeLog := 'D';
                    end;

                    CcOpeLog.Edit;
                    CcOpeLog.FieldByName('DtFimOpe').AsDateTime:= Now();
                    CcOpeLog.FieldByName('StOpeLog').AsString  := lStOpeLog;
                    CcOpeLog.FieldByName('QtProSId').AsInteger := pQtProSId;
                    CcOpeLog.FieldByName('CdClient').AsInteger := StrToInt(pCdClient);
                    CcOpeLog.Post;

                    CcOpeLog.BrApplyUpdates;
              end else
              begin

                    CcOpeLog.Close;
                    CcOpeLog.BrParams.Clear;
                    CcOpeLog.BrParams.Add('<%NrOpeLog%>;' + pNrMapa);
                    CcOpeLog.Open;

                    CcOpeLog.Edit;
                    CcOpeLog.FieldByName('DtIniOpe').AsString  := '';
                    CcOpeLog.FieldByName('CdUsuExe').AsInteger := 0;
                    CcOpeLog.FieldByName('StOpeLog').AsString  := 'A';
                    CcOpeLog.FieldByName('QtProSId').AsInteger := 0;
                    CcOpeLog.Post;

                    lCdArmaze := CcOpeLog.FieldByName('CdArmaze').AsString;

                    //Excluindo controles logisticos lidos/conferidos
                    QExecute.CommandText := 'Update W008 Set StConfCc = ''E'' ' +
                                            'Where  NrOpeLog = ' + pNrMapa + ' and ' +
                                            '       CdArmaze = ' + lCdArmaze;
                    QExecute.ExecSQL(True);

                    //Excluindo itens não esperados pela atividade
                    QExecute.CommandText := 'Update w006 Set stiteope = ''E'' ' +
                                            'Where  NrOpeLog = ' + pNrMapa + ' and ' +
                                            '       CdArmaze = ' + lCdArmaze + ' and ' +
                                            '       stiteope = ''C'' ';
                    QExecute.ExecSQL(True);

                    //Removendo condição de divergencia
                    QExecute.CommandText := 'Update w006 Set stiteope = ''N'' ' +
                                            'Where  NrOpeLog = ' + pNrMapa + ' and ' +
                                            '       CdArmaze = ' + lCdArmaze + ' and ' +
                                            '       stiteope = ''D'' ';;
                    QExecute.ExecSQL(True);

                    CcOpeLog.BrApplyUpdates;
              end;

              SqlConnWMS.Commit(lDsTransa);
          except
              On E: exception do
              begin
                    SqlConnWMS.Rollback(lDsTransa);
                    Result := UFrmLogos.cDsMsgEr + E.Message;
              end;
          end;

      finally
          FreeAndNil(CcLisAux);
          FreeAndNil(LCdsw007);
          FreeAndNil(lDsLisAux);
          FreeAndNil(LCdsAux);
          FreeAndNil(lCdsW008);
          FreeAndNil(lProW008);
          FreeAndNil(lDtsW008);
          FreeAndNil(lCdsW006);
          FreeAndNil(lProW006);
          FreeAndNil(lDtsW006);
      end;
end;

end.


function TSDmWms.GravarOperacaoLogistica(pDsCreden: AnsiString; pCdUsuari: Integer;
                                         pNmFormul: AnsiString; pCdArmaze: Integer;
                                         pCdTipOpe: Integer;
                                         pCdsW005, pCdsW006: OleVariant): AnsiString;
begin
      if not FrmLogos.CredencialValida(pDsCreden, pCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial Invalida!' + Chr(13) +
                                                'Favor verificar com o administrador do sistema...';
      end else
      begin
            try
                try
                    lDsTransa := DmDicion.NumeroTransactionID;
                    SqlConnWMS.StartTransaction(lDsTransa);

                    lCdsW005p.Data := pCdsW005;
                    lCdsW006p.Data := pCdsW006;
                    lCdsW007p.Data := pCdsW007;

                    while not lCdsW005p.Eof do
                    begin
                          lNrOpeLog := DmDicion.BrvDicionario.ProximoValorSequencial(0,13);

                          lCdsW005.Append;
                          lCdsW005.FieldByName('CDARMAZE').AsInteger  := pCdArmaze;
                          lCdsW005.FieldByName('NROPELOG').AsInteger  := lNrOpeLog;
                          lCdsW005.FieldByName('CDTIPATI').AsInteger  := pCdTipOpe;
                          lCdsW005.Post;

                          lCdsW006p.Filtered := False;
                          lCdsW006p.Filter   := 'NrNota = ' +
                                                           lCdsW005p.FieldByName('NrNota').AsString;
                          lCdsW006p.Filtered := True;

                          while not lCdsW006p.Eof do
                          begin
                                lCdsW006.Append;
                                lCdsW006.FieldByName('CDARMAZE').AsInteger  := pCdArmaze;
                                lCdsW006.FieldByName('CDEMBALA').AsString   :=
                                                         lCdsW006p.FieldByName('CDEMBALA').AsString;
                                lCdsW006.FieldByName('DTFECHA' ).AsDatetime :=
                                                         DmDicion.BrvDicionario.DataHoraServer;
                                lCdsW006.Post;
                                lCdsW006p.Next;
                          end;

                          lCdsW005p.Next;
                    end;
                except
                    on E: Exception do
                    begin
                          SqlConnWMS.Rollback(lDsTransa);
                          Result := UFrmLogos.cDsMsgEr + E.Message;
                    end;
                end;
            finally
                lCdsW005p.Close;
                lCdsW006p.Close;
                lCdsW007p.Close;
            end;
      end;
end;
