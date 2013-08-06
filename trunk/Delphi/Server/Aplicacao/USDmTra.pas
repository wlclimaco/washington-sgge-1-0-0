unit USDmTra;

interface

uses
  SysUtils, Classes, DSServer, DBXOracle, FMTBcd, Provider, DB, SqlExpr, BrvProvider,
  DBClient, BrvClientDataSet, BrvDataSet, BrvString, BrvDb, BrvConnection, DBXCommon, DateUtils,
  BrvGestao, BrvContabil;

type
  TConDinam = record
      ConProvider : TBrvProvider;
      ConDataSet  : TBrvDataSet;
      ConOcupado  : Boolean;
  end;

  TSDmTra = class(TDSServerModule)
    QcEntMer: TBrvDataSet;
    DcEntMer: TBrvProvider;
    SqlConnTra: TBrvConnection;
    QExecute: TBrvDataSet;
    QcCarga: TBrvDataSet;
    DcCarga: TBrvProvider;
    QpCarga: TBrvClientDataSet;
    QcCargaEmp: TBrvDataSet;
    DcCargaEmp: TBrvProvider;
    QpCargaEmp: TBrvClientDataSet;
    QCMovCtrc: TBrvDataSet;
    DCMovCtrc: TBrvProvider;
    CCMovCtrc: TBrvClientDataSet;
    QcOcor: TBrvDataSet;
    DpOcor: TBrvProvider;
    CcOcor: TBrvClientDataSet;
    QpCTRC: TBrvDataSet;
    DpCTRC: TBrvProvider;
    CcCTRC: TBrvClientDataSet;
    CcT019: TBrvClientDataSet;
    DcT019: TBrvProvider;
    QcT019: TBrvDataSet;
    BrvContabil: TBrvContabil;
    QCAceCarga: TBrvDataSet;
    DCAceCarga: TBrvProvider;
    CCAceCarga: TBrvClientDataSet;
    QCConCor: TBrvDataSet;
    DCConCor: TBrvProvider;
    CCConCor: TBrvClientDataSet;
    procedure SqlConnTraAfterConnect(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    procedure SalvarCarga(pDsCreden : String;  var pDsResult : String;
                      var pCdCarga  : Integer;     pCdsCarga : OleVariant);

             { pDsCreden: String; var pDsResult : String;  var pCdCarga  : Integer;
               pDsDesCar: String;     pNrMalote : Integer;     pTpVeicul : Integer;
               pKmPreCar: Integer;    pDtPreRet : TDateTime;   pTpEscolt : Integer;
               pCdEmpEsc: Integer;    pNmFrmOri : String;      pStCarga  : String;
               pCdVeicu1: Integer;    pCdMotor1 : Integer;
               pCdVeicu2: Integer;    pCdMotor2 : Integer;
               pCdVeicu3: Integer;    pCdMotor3 : Integer;
               pCdVeicu4: Integer;    pCdMotor4 : Integer;
               pCdsCTRC : OleVariant; pCdEmpDes : Integer;
               pNrPesCom: Double);}

    function ValidarCarga(pDsCreden : AnsiString; pTpVeicul : AnsiString;
                          pCdVeicul : AnsiString; pQtPesCtr : Double;
                          pDsComWhe : AnsiString) : AnsiString;

    function CancelarCarga(pDsCreden : AnsiString;
                           pCdCarga  : Integer;
                           pNmFormul : AnsiString) : AnsiString;

    function GravarDadosCarga(pDsCreden : AnsiString; pCdsCarga : OleVariant;
                              pDsColuna : AnsiString; pNmFormul : AnsiString) : AnsiString;

    function GravarOcorrenciaMotorista(pDsCreden : AnsiString;
                                       pCdsOcorre : OleVariant) : AnsiString;

    function ProximaCarga : Integer;

    function GravaDataEntregaCTRC(pDsCreden: AnsiString; pCdsCTRC: OleVariant): AnsiString;

    function GravaContaCorrenteMotorista(pDsCreden  : AnsiString;
                                         pCdsT019   : OleVariant;
                                         pCdsLancto : OleVariant;
                                         pNrPlano   : Integer;
                                         pNmFormul  : AnsiString): AnsiString;

    function GravarAcertoCarga(pDsCreden : String; pCdsParams : String) : AnsiString;
  end;

implementation

uses UFrmLogos, UDmDicion, UDmAdm;

{$R *.dfm}

function TSDmTra.CancelarCarga(pDsCreden : AnsiString;
                               pCdCarga  : Integer;
                               pNmFormul : AnsiString) : AnsiString;
var lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
    lCdsMovAu : TClientDataSet;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := UFrmLogos.cDsMsgOk;
            CCMovCtrc.Close;
            CCMovCtrc.BrParams.Clear;
            CCMovCtrc.BrParams.Add('<%CdCarga%>;' + IntToStr(pCdCarga));
            CCMovCtrc.Open;    //151

            Result    := UFrmLogos.cDsMsgOk;

            lDsTransa := DmDicion.NumeroTransactionID;
            SqlConnTra.StartTransaction(lDsTransa);

            try
                try
                    lCdsMovAu := TClientDataSet.Create(self);
                    CCMovCtrc.BrFormName := pNmFormul;
                    CCMovCtrc.BrUserCode := lCdUsuari;

                    QExecute.BrFormName  := pNmFormul;
                    QExecute.BrUserCode  := lCdUsuari;

                    //--Mudar status carga
                    QExecute.CommandText :=
                               ' Update T003 '            +
                               ' Set    StCarga  = "C" '  +
                               ' Where  CdCarga  = '      +  IntToStr(pCdCarga);

                    QExecute.BrExecuteSQL(True);

                   //--retirar codigo da carga do conheciemnto
                    QExecute.CommandText :=
                               ' Update T002 '            +
                               ' Set    CdCarga = null '  +
                               ' Where  CdCarga  = '      +  IntToStr(pCdCarga);

                    QExecute.BrExecuteSQL(True);

                    //--baixar e Duplicar movctrc
                    lCdsMovAu.Data := CCMovCtrc.Data;

                    while not CCMovCtrc.Eof do
                    begin
                          CCMovCtrc.Edit;
                          CCMovCtrc.FieldByName('StMovime').AsString := 'F';
                          CCMovCtrc.Post;

                          CCMovCtrc.Next;
                    end;

                    lCdsMovAu.First;
                    while not lCdsMovAu.Eof do
                    begin
                          CCMovCtrc.Append;
                          CCMovCtrc.FieldByName('CdEmpres').AsInteger :=
                                                lCdsMovAu.FieldByName('CdEmpres').AsInteger;
                          CCMovCtrc.FieldByName('DsModeNf').AsString  :=
                                                lCdsMovAu.FieldByName('DsModeNf').AsString;
                          CCMovCtrc.FieldByName('NrSeriNf').AsString  :=
                                                lCdsMovAu.FieldByName('NrSeriNf').AsString;
                          CCMovCtrc.FieldByName('CdCtrc').AsInteger   :=
                                                lCdsMovAu.FieldByName('CdCtrc').AsInteger;
                          CCMovCtrc.FieldByName('NrSequen').AsInteger :=
                                                lCdsMovAu.FieldByName('NrSequen').AsInteger +
                                                lCdsMovAu.RecordCount;
                          CCMovCtrc.FieldByName('CdEmpAtu').AsInteger :=
                                                lCdsMovAu.FieldByName('CdEmpAtu').AsInteger;
                          CCMovCtrc.FieldByName('DtMovto').AsDateTime :=
                                                DmDicion.BrvDicionario.DataHoraServer;
                          CCMovCtrc.FieldByName('StMovime').AsString  :=
                                                lCdsMovAu.FieldByName('StMovime').AsString;

                          lCdsMovAu.Next;
                    end;

                    if Result = UFrmLogos.cDsMsgOk then
                    begin
                          CCMovCtrc.BrApplyUpdates;
                          SqlConnTra.Commit(lDsTransa);
                    end;
                finally
                    FreeAndNil(lCdsMovAu);
                    CCMovCtrc.BrFormName := '';
                    QExecute.BrFormName  := '';
                end;
            except
                on E: Exception do
                      begin
                            SqlConnTra.Rollback(lDsTransa);
                            Result := UFrmLogos.cDsMsgEr + ' Carga - ' +
                                      IntToStr(pCdCarga) + #13         + E.Message;
                      end;
            end;
      end;
end;

function TSDmTra.GravarDadosCarga(pDsCreden: AnsiString; pCdsCarga: OleVariant; pDsColuna,
  pNmFormul: AnsiString): AnsiString;
var lCdUsuari : Integer;
    lNmColuna : TStrings;
    lNrPos    : Integer;
    lNrColuna : Integer;
    lCdsCarga : TClientDataSet;
    lDsUpDate : AnsiString;
    lDsVirgul : AnsiString;
    lDsTransa : TTransactionDesc;
    lSnAtuSit : Boolean;
begin
      Result := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lNmColuna := TStringList.Create;
            lNmColuna.Clear;

            try
                lDsTransa := DmDicion.NumeroTransactionID;
                SqlConnTra.StartTransaction(lDsTransa);

                try
                    while Length(pDsColuna) > 0 do
                    begin
                          lNrPos := pos(';', pDsColuna);

                          if lNrPos > 0  then
                          begin
                                lNmColuna.Add(Copy(pDsColuna, 1, lNrPos - 1));
                                Delete(pDsColuna, 1, lNrPos);
                          end else
                          begin
                                lNmColuna.Add(pDsColuna);
                                pDsColuna := '';
                          end;
                    end;

                    lCdsCarga      := TClientDataSet.Create(Self);
                    lCdsCarga.Data := pCdsCarga;

                    while Not lCdsCarga.Eof do
                    begin
                          lDsUpDate := 'UpDate T003 Set ';
                          lDsVirgul := '';
                          lSnAtuSit := True;

                          for lNrColuna := 0 to lNmColuna.Count -1 do
                          begin
                                lDsUpDate := lDsUpDate +  lDsVirgul + lNmColuna[lNrColuna];

                                if lCdsCarga.FieldByName(lNmColuna[lNrColuna]).IsNull then
                                begin
                                      lDsUpDate := lDsUpDate + ' = Null';
                                      lSnAtuSit := False;
                                end else
                                begin
                                      if lCdsCarga.FieldByName(lNmColuna[lNrColuna]) is
                                                                                 TNumericField then
                                      begin
                                            lDsUpDate := lDsUpDate + ' = ' +
                                             StringReplace(
                                               lCdsCarga.FieldByName(lNmColuna[lNrColuna]).AsString,
                                                                          ',', '.', [rfReplaceAll]);
                                      end else
                                      begin
                                            lDsUpDate := lDsUpDate + ' = ' +
                                             QuotedStr(
                                              lCdsCarga.FieldByName(lNmColuna[lNrColuna]).AsString);
                                      end;
                                end;

                                lDsVirgul := ', ';
                          end;

                          if lSnAtuSit then
                          begin
                                lDsUpDate := lDsUpDate + ', StCarga = ' + QuotedStr('G');
                          end;

                          lDsUpDate := lDsUpDate + ' Where  CdCarga  = ' +
                                       lCdsCarga.FieldByName('CdCarga').AsString;

                          QExecute.BrFormName  := pNmFormul;
                          QExecute.BrUserCode  := lCdUsuari;
                          QExecute.CommandText := lDsUpDate;

                          QExecute.BrExecuteSQL(True);

                          lCdsCarga.Next;
                    end;

                    SqlConnTra.Commit(lDsTransa);
                except
                    on E: Exception do
                          begin
                                SqlConnTra.Rollback(lDsTransa);
                                Result := UFrmLogos.cDsMsgEr + E.Message;
                          end;

                    end;
            finally
                FreeAndNil(lNmColuna);
                FreeAndNil(lCdsCarga);
            end;
      end;
end;

function TSDmTra.GravaContaCorrenteMotorista(pDsCreden: AnsiString; pCdsT019: OleVariant;
                                             pCdsLancto: OleVariant; pNrPlano: Integer;
                                             pNmFormul: AnsiString): AnsiString;
var lCdsT019 : TClientDataSet;
    lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                try
                    lDsTransa := DmDicion.NumeroTransactionID;
                    SqlConnTra.StartTransaction(lDsTransa);

                    lCdsT019      := TClientDataSet.Create(Self);
                    lCdsT019.Data := pCdsT019;
                    lCdsT019.First;

                    CcT019.Close;
                    CcT019.Open;

                    while not lCdsT019.Eof do
                    begin
                          CcT019.Append;
                          CcT019.FieldByName( 'CdCarga').Value  :=
                                                        lCdsT019.FieldByName( 'CdCarga').Value;
                          CcT019.FieldByName('CdEmpres').AsInteger  :=
                                                        lCdsT019.FieldByName('CdEmpres').AsInteger;
                          CcT019.FieldByName('CdMotori').AsInteger  :=
                                                        lCdsT019.FieldByName('CdMotori').AsInteger;
                          CcT019.FieldByName('CDUSUARI').AsInteger  :=
                                                        lCdsT019.FieldByName('CDUSUARI').AsInteger;
                          CcT019.FieldByName('DTLANCTO').AsDateTime :=
                                                        lCdsT019.FieldByName('DTLANCTO').AsDateTime;
                          CcT019.FieldByName('NRSEQCON').AsInteger  :=
                                                        lCdsT019.FieldByName('NRSEQCON').AsInteger;
                          CcT019.FieldByName('OBCONCOR').AsString   :=
                                                        lCdsT019.FieldByName('OBCONCOR').AsString;
                          CcT019.FieldByName('STCONCOR').AsString   :=
                                                        lCdsT019.FieldByName('STCONCOR').AsString;
                          CcT019.FieldByName('VRLANCTO').AsFloat    :=
                                                        lCdsT019.FieldByName('VRLANCTO').AsFloat;
                          CcT019.Post;

                          lCdsT019.Next;
                    end;

                    CcT019.BrApplyUpdates;

                    BrvContabil.LancarContabilidade(pCdsLancto, pNrPlano, lCdUsuari,  pNmFormul);

                    SqlConnTra.Commit(lDsTransa);
                    Result := UFrmLogos.cDsMsgOk;
                except
                    on E: Exception do
                    begin
                          SqlConnTra.Rollback(lDsTransa);
                          Result := UFrmLogos.cDsMsgEr + E.Message;
                    end;
                end;

            finally
                FreeAndNil(lCdsT019);
            end;
      end;
end;

function TSDmTra.GravarAcertoCarga(pDsCreden : String; pCdsParams : String) : AnsiString;
var lCdsParams : TClientDataSet;
    lCdsLanCon : TClientDataSet;
    lCdsMovAce : TClientDataSet;
    lCdsConCor : TClientDataSet;
    lDsTransa  : TDBXTransaction;
    lNrConCor  : String;
    lVrConAux  : String;
    lCdUsuari  : Integer;
    lKmPerCar  : String;
    lCdCarga   : String;
    lSnFinali  : String;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                lCdsParams         := TClientDataSet.Create(nil);
                lCdsParams.XMLData := pCdsParams;

                lCdsLanCon         := TClientDataSet.Create(nil);
                lCdsMovAce         := TClientDataSet.Create(nil);
                lCdsConCor         := TClientDataSet.Create(nil);

                lCdsLanCon.XMLData := lCdsParams.FieldByName('CCContab').AsString;
                lCdsMovAce.XMLData := lCdsParams.FieldByName('CCAceCar').AsString;
                lCdsConCor.XMLData := lCdsParams.FieldByName('CCConCor').AsString;

                lKmPerCar := StringReplace(lCdsParams.FieldByName('KmPerCar').AsString,
                                                                          '.', '',  [rfReplaceAll]);
                lKmPerCar := StringReplace(lKmPerCar, ',', '.', [rfReplaceAll]);
                lCdCarga  := lCdsParams.FieldByName('CdCarga').AsString;
                lSnFinali := lCdsParams.FieldByName('SnFinali').AsString;


                //Atualizando movimento do acerto de carga
                CCAceCarga.Close;
                CCAceCarga.BrParams.Clear;
                CCAceCarga.BrParams.Add('<%CdCarga%>;' + lCdCarga);
                CCAceCarga.Open;

                while not CCAceCarga.Eof do
                begin
                      CCAceCarga.Delete;
                end;

                while not lCdsMovAce.Eof do
                begin
                      CCAceCarga.Append;
                      CCAceCarga.FieldByName('CdCarga').AsString :=
                                                        lCdsMovAce.FieldByName('CdCarga').AsString;
                      CCAceCarga.FieldByName('CdCenCus').AsString :=
                                                        lCdsMovAce.FieldByName('CdCenCus').AsString;
                      CCAceCarga.FieldByName('CdEmpres').AsString :=
                                                        lCdsMovAce.FieldByName('CdEmpres').AsString;
                      CCAceCarga.FieldByName('CdHistor').AsString :=
                                                        lCdsMovAce.FieldByName('CdHistor').AsString;
                      CCAceCarga.FieldByName('CdUsuari').AsString :=
                                                        lCdsMovAce.FieldByName('CdUsuari').AsString;
                      CCAceCarga.FieldByName('DtLancto').AsString :=
                                                        lCdsMovAce.FieldByName('DtLancto').AsString;
                      CCAceCarga.FieldByName('NrConta').AsString  :=
                                                        lCdsMovAce.FieldByName('NrConta').AsString;
                      CCAceCarga.FieldByName('NrPlano').AsString  :=
                                                        lCdsMovAce.FieldByName('NrPlano').AsString;

                      CCAceCarga.FieldByName('NrSeqMov').AsInteger := lCdsMovAce.RecNo;

                      CCAceCarga.FieldByName('TpLanMov').AsString :=
                                                        lCdsMovAce.FieldByName('TpLanMov').AsString;
                      CCAceCarga.FieldByName('VrLancto').AsString :=
                                                        lCdsMovAce.FieldByName('VrLancto').AsString;
                      lCdsMovAce.Next;
                      CCAceCarga.Post;
                end;
                //--

                //-- Atualizando conta corrente do motorista ---------------------------------------------
                lNrConCor := '';
                while not lCdsConCor.Eof do
                begin
                      if lCdsConCor.FieldByName('NrSeqCon').AsInteger > 0 then
                      begin
                            lNrConCor := lNrConCor + ',' +
                                         lCdsConCor.FieldByName('NrSeqCon').AsString;
                      end;

                      lCdsConCor.Next;
                end;

                Delete(lNrConCor, 1, 1);

                CCConCor.Close;
                CCConCor.BrParams.Clear;
                CCConCor.BrParams.Add('<%NrConCor%>;' + lNrConCor);
                CCConCor.Open; //208

                while not CCConCor.Eof do
                begin
                      CCConCor.Edit;
                      CCConCor.FieldByName('CdCarga').AsString  := lCdCarga;
                      CCConCor.FieldByName('StConCor').AsString := 'C';
                      CCConCor.Next;
                end;

                lCdsConCor.Filter   := 'NrSeqCon = 0';
                lCdsConCor.Filtered := True;
                lCdsConCor.First;

                while Not lCdsConCor.Eof do
                begin
                      CCConCor.Append;
                      CCConCor.FieldByName('NrSeqCon').AsInteger :=
                                                DmDicion.BrvDicionario.ProximoValorSequencial(0,20);
                      CCConCor.FieldByName('CdEmpres').AsInteger :=
                                                     lCdsConCor.FieldByName('CdEmpres').AsInteger;
                      CCConCor.FieldByName('CdMotori').AsInteger :=
                                                     lCdsConCor.FieldByName('CdMotori').AsInteger;
                      CCConCor.FieldByName('DtLancto').AsDateTime :=
                                                     lCdsConCor.FieldByName('DtLancto').AsDateTime;
                      CCConCor.FieldByName('CdHistor').AsInteger :=
                                                     lCdsConCor.FieldByName('CdHistor').AsInteger;
                      CCConCor.FieldByName('VrBasLan').AsInteger :=
                                                     lCdsConCor.FieldByName('VrBasLan').AsInteger;
                      CCConCor.FieldByName('VrLancto').AsInteger :=
                                                     lCdsConCor.FieldByName('VrLancto').AsInteger;

                      CCConCor.FieldByName('CdCarga').AsString   := lCdCarga;
                      CCConCor.FieldByName('CdUsuari').AsInteger := lCdUsuari;
                      CCConCor.FieldByName('NmFormul').AsString  :=
                                                       lCdsParams.FieldByName('NmFormul').AsString;
                      CCConCor.FieldByName('StConCor').AsString  := 'C';

                      CCConCor.Post;
                      lCdsConCor.Next;
                end;

                lCdsConCor.Close;


                QExecute.Close;
                QExecute.BrQueryCode := 0;

                lDsTransa := SqlConnTra.BeginTransaction(TDBXIsolations.ReadCommitted);

                try
                    //Fixando Movimento do acerto de carga
                    CCAceCarga.BrApplyUpdates;

                    //Fixando Conta corrente do motorista
                    CCConCor.BrApplyUpdates;

                    //-- Atualizando carga
                    QExecute.BrQueryCode := 205;
                    QExecute.BrParams.Clear;
                    QExecute.BrParams.Add('<%KmPerCar%>;' + lKmPerCar);
                    QExecute.BrParams.Add('<%CdCarga%>;'  + lCdCarga);

                    if lSnFinali = 'S' then
                    begin
                          QExecute.BrParams.Add('<%StCarga%>;, StCarga = "E"');
                    end else
                    begin
                          QExecute.BrParams.Add('<%StCarga%>;');
                    end;

                    QExecute.BrExecuteSQL(False);
                    //--

                    if lSnFinali = 'S' then
                    begin
                          //-- Atualizando carga empresa
                          QExecute.BrQueryCode := 206;
                          QExecute.BrParams.Clear;
                          QExecute.BrParams.Add('<%DtAceCar%>;' +
                                                               QExecute.BrDicionario.DataServerStr);
                          QExecute.BrParams.Add('<%CdCarga%>;'  + lCdCarga);
                          QExecute.BrExecuteSQL(False);
                          //--

                          BrvContabil.LancarContabilidade(lCdsLanCon.Data,
                                                      lCdsParams.FieldByName('NrPlano').AsInteger,
                                                      lCdUsuari,
                                                      lCdsParams.FieldByName('NmFormul').AsString);
                    end;

                    SqlConnTra.CommitFreeAndNil(lDsTransa);
                except
                    SqlConnTra.RollbackFreeAndNil(lDsTransa);
                end;
            finally
                FreeAndNil(lCdsLanCon);
                FreeAndNil(lCdsMovAce);
                FreeAndNil(lCdsConCor);
                FreeAndNil(lCdsParams);
            end;
      end;
end;

function TSDmTra.GravaDataEntregaCTRC(pDsCreden  : AnsiString; pCdsCTRC : OleVariant): AnsiString;
var lCdsCTRC  : TClientDataSet;
    lDsTransa : TTransactionDesc;
    lCdUsuari : Integer;
    lQExecute : TBrvDataSet;
begin
      try
          lCdsCTRC := TClientDataSet.Create(Self);
          lCdsCTRC.Close;
          lCdsCTRC.Data := pCdsCTRC;
          lCdsCTRC.Open;

          if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
          begin
                Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
          end else
          begin
                try
                    lDsTransa := DmDicion.NumeroTransactionID;
                    SqlConnTra.StartTransaction(lDsTransa);

                    CCCtrc.Close;
                    CCCtrc.BrUserCode := lCdUsuari;
                    CCCtrc.BrParams.Clear;
                    CCCtrc.BrParams.Add('<%CdEmpres%>;' +lCdsCTRC.FieldByName('CdEmpres').AsString);
                    CCCtrc.BrParams.Add('<%DsModeNF%>;' +lCdsCTRC.FieldByName('DsModeNF').AsString);
                    CCCtrc.BrParams.Add('<%NrSerinf%>;' +lCdsCTRC.FieldByName('NrSerinf').AsString);
                    CCCtrc.BrParams.Add('<%CdCtrc%>;'   +lCdsCTRC.FieldByName('CdCtrc'  ).AsString);
                    CCCtrc.Open;    //169

                    if (CCCtrc.RecordCount > 0) then
                    begin
                          CCCtrc.Edit;
                          if (lCdsCTRC.FieldByName('DtEntreg').AsDateTime > 0) then
                          begin
                                CCCtrc.FieldByName('DtEntreg').AsDateTime :=
                                                        lCdsCTRC.FieldByName('DtEntreg').AsDateTime;
                          end else
                          begin
                                CCCtrc.FieldByName('DtEntreg').AsString := '';
                          end;

                          if (lCdsCTRC.FieldByName('DtEntMot').AsDateTime > 0) then
                          begin
                                CCCtrc.FieldByName('DtEntMot').AsDateTime :=
                                                        lCdsCTRC.FieldByName('DtEntMot').AsDateTime;
                          end else
                          begin
                                CCCtrc.FieldByName('DtEntMot').AsString := '';
                          end;

                          if (lCdsCTRC.FieldByName('DtPreEnt').AsDateTime > 0) then
                          begin
                                CCCtrc.FieldByName('DtPreEnt').AsDateTime :=
                                                        lCdsCTRC.FieldByName('DtPreEnt').AsDateTime;
                          end else
                          begin
                                CCCtrc.FieldByName('DtPreEnt').AsString := '';
                          end;

                          CCCtrc.FieldByName('CdOcorre').AsInteger :=
                                                         lCdsCTRC.FieldByName('CdOcorre').AsInteger;

                          CCCtrc.FieldByName('ObEntreg').Value :=
                                                         lCdsCTRC.FieldByName('ObEntreg').Value;

                          CCCtrc.Post;
                    end;

                    CCCtrc.BrApplyUpdates;

                    SqlConnTra.Commit(lDsTransa);
                    Result := UFrmLogos.cDsMsgOk;
                except
                    on E: Exception do
                    begin
                          SqlConnTra.Rollback(lDsTransa);
                          Result := UFrmLogos.cDsMsgEr + E.Message;
                    end;
                end;
          end;

      finally
          FreeAndNil(lQExecute);
          FreeAndNil(lCdsCTRC);
      end;
end;

function TSDmTra.GravarOcorrenciaMotorista(pDsCreden  : AnsiString;
                                           pCdsOcorre : OleVariant): AnsiString;
var lCdUsuari  : Integer;
    lDsTransa  : TTransactionDesc;
    lCdsOcorre : TClientDataSet;
    lCdsDSR    : TClientDataSet;
    lQtMinSal  : Integer;
    lHorIni    : TDateTime;
    lHorFin    : TDateTime;
    lHorOco    : TDateTime;
begin
      try
          lCdsOcorre      := TClientDataSet.Create(nil);
          lCdsDSR         := TClientDataSet.Create(nil);
          lCdsOcorre.Data := pCdsOcorre;

          if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
          begin
                Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
          end else
          begin
                try
                    lDsTransa := DmDicion.NumeroTransactionID;
                    SqlConnTra.StartTransaction(lDsTransa);

                    CcOcor.BrUserCode := lCdUsuari;
                    CcOcor.BrFormName := lCdsOcorre.FieldByName('NmFrmOri').AsString;
                    CcOcor.Close;
                    CcOcor.CommandText := 'Select * From T018 Where CdCarga is null';
                    CcOcor.Open;

                    CcOcor.Append;
                    CcOcor.FieldByName('NrSeqMov').AsInteger  :=
                                    DmDicion.BrvDicionario.ProxNumeroSequencial('T018', 'NRSEQMOV');

                    CcOcor.FieldByName('CdEmpres').AsInteger  :=
                                    lCdsOcorre.FieldByName('CdEmpres' ).AsInteger;

                    if (lCdsOcorre.FieldByName('CdCarga' ).AsInteger > 0) then
                    begin
                          CcOcor.FieldByName('CdCarga' ).AsInteger  :=
                                    lCdsOcorre.FieldByName('CdCarga' ).AsInteger;
                    end;

                    CcOcor.FieldByName('CdMotori').AsInteger  :=
                                    lCdsOcorre.FieldByName('CdMotori').AsInteger;
                    CcOcor.FieldByName('CdTipOco').AsInteger  :=
                                    lCdsOcorre.FieldByName('CdTipOco').AsInteger;

                    if lCdsOcorre.FieldByName('CdVeicul').AsInteger > 0 then
                    begin
                          CcOcor.FieldByName('CdVeicul').AsInteger  :=
                                    lCdsOcorre.FieldByName('CdVeicul').AsInteger;
                    end;

                    CcOcor.FieldByName('DtOcorre').AsDateTime :=
                                    lCdsOcorre.FieldByName('DtOcorre').AsDateTime;
                    CcOcor.FieldByName('DtProces').AsDateTime :=
                                    lCdsOcorre.FieldByName('DtProces').AsDateTime;

                    if (lCdsOcorre.FieldByName('TpTipOco').AsString = '3') then
                    begin
                          CcOcor.FieldByName('QtMinSal' ).AsInteger := MinutesBetween(
                                    lCdsOcorre.FieldByName('DtOcorre').AsDateTime,
                                    lCdsOcorre.FieldByName('DtUltOco').AsDateTime);
                    end else
                    begin
                          if (lCdsOcorre.FieldByName('TpTipOco').AsString = '1') then
                          begin
                                CcOcor.FieldByName('VrFatDSR').AsFloat :=
                                                        lCdsOcorre.FieldByName('VrFatDSR').AsFloat;
                                CcOcor.FieldByName('HrIniJor').AsString :=
                                                        lCdsOcorre.FieldByName('HrIniJor').AsString;
                                CcOcor.FieldByName('QtHorJor').AsFloat :=
                                                        lCdsOcorre.FieldByName('QtHorJor').AsFloat;

                                //Ocorrencia 21/08/2012 00:30:00
                                lHorIni := StrToDateTime(FormatDateTime('dd/mm/yyyy',
                                           lCdsOcorre.FieldByName('DtOcorre').AsDateTime) + ' ' +
                                           lCdsOcorre.FieldByName('HrIniJor').AsString);

                                //Param: 05:00:00
                                //Ex: 21/08/2012 05:00:00

                                lHorFin := IncHour(lHorIni,
                                                      lCdsOcorre.FieldByName('QtHorJor').AsInteger);
                                //Param: 13
                                //Ex: 21/08/2012 18:00:00

                                lHorOco := lCdsOcorre.FieldByName('DtOcorre').AsDateTime;

                                if (lHorOco < lHorIni) then
                                begin
                                      CcOcor.FieldByName('QtSalJor').AsInteger :=
                                                                   MinutesBetween(lHorIni, lHorOco);
                                end else
                                begin
                                      if (lHorOco > lHorFin)  then
                                      begin
                                            lHorIni := IncHour(lHorIni, 24);
                                            CcOcor.FieldByName('QtSalJor').AsInteger :=
                                                                   MinutesBetween(lHorIni, lHorOco);
                                      end;
                                end;

                                lCdsDSR.Close;
                                lCdsDSR.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(157,
                                                        '<%CdMotori%>;' +
                                                        lCdsOcorre.FieldByName('CdMotori').AsString,
                                                        Name);
                                lCdsDSR.Open;

                                if (lCdsDSR.RecordCount > 0) then
                                begin
                                      lCdsDSR.Close;
                                      lCdsDSR.Data :=
                                                DmDicion.BrvDicionario.RetornaDadosSqlCadastro(158,
                                                        '<%CdMotori%>;' +
                                                        lCdsOcorre.FieldByName('CdMotori').AsString,
                                                        Name);
                                      lCdsDSR.Open;

                                      lQtMinSal := StrToInt(
                                              DmDicion.BrvDicionario.ParametroDaEmpresa(14,0)) * 60;

                                      CcOcor.FieldByName('QtMinSal').AsInteger :=
                                                          lQtMinSal -
                                                          lCdsDSR.FieldByName('QtMinSal').AsInteger;
                                end else
                                begin
                                      lCdsDSR.Close;
                                      lCdsDSR.Data :=
                                                DmDicion.BrvDicionario.RetornaDadosSqlCadastro(159,
                                                        '<%CdMotori%>;' +
                                                        lCdsOcorre.FieldByName('CdMotori').AsString,
                                                        Name);
                                      lCdsDSR.Open;

                                      lQtMinSal := StrToInt(
                                              DmDicion.BrvDicionario.ParametroDaEmpresa(14,0)) * 60;

                                      CcOcor.FieldByName('QtMinSal').AsInteger :=
                                                          lQtMinSal -
                                                          lCdsDSR.FieldByName('QtMinSal').AsInteger;
                                end;

                          end;
                    end;

                    CcOcor.Post;

                    CcOcor.BrApplyUpdates;

                    SqlConnTra.Commit(lDsTransa);
                    Result := UFrmLogos.cDsMsgOk;
                except
                    on E: Exception do
                    begin
                          SqlConnTra.Rollback(lDsTransa);
                          Result := UFrmLogos.cDsMsgEr + E.Message;
                    end;
                end;
          end;

          lCdsOcorre.Close;
      finally
          FreeAndNil(lCdsOcorre);
          FreeAndNil(lCdsDSR);
      end;
end;

function TSDmTra.ProximaCarga: Integer;
var DsSql : AnsiString;
    CPParSis : TClientDataSet;
begin
      //função temporária para compatibilizar os dois sistemas
      try
          CPParSis      := TClientDataSet.Create(nil);
          CPParSis.Data := DmDicion.BrvDicionario.RetornaDadosSqlFixa(
                                                             'Select CdCarga From Parsistema');

          QExecute.CommandText := 'UpDate ParSistema Set CdCarga = CdCarga + 1';

          Result := CPParSis.Fields[0].AsInteger + 1;


          CPParSis.Close;
      finally
          FreeAndNil(CPParSis);
      end;
end;

procedure TSDmTra.SalvarCarga(pDsCreden : String;  var pDsResult : String;
                          var pCdCarga  : Integer;     pCdsCarga : OleVariant);
var lCdUsuari : Integer;
    lCdsCtrc  : TClientDataSet;
    lCdsCarga : TClientDataSet;
    lVrCarga  : Real;
    lQtEntCar : Integer;
    lPsCarga  : Real;
    lCdEmpCar : Integer;
    lDsTransa : TTransactionDesc;
    lCpParams : TClientDataSet;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                lDsTransa := DmDicion.NumeroTransactionID;
                SqlConnTra.StartTransaction(lDsTransa);

                lCdsCarga        := TClientDataSet.Create(Self);
                lCdsCtrc         := TClientDataSet.Create(Self);

                lCdsCarga.Data   := pCdsCarga;
                lCdsCtrc.XMLData := lCdsCarga.FieldByName('CdsCTRC').AsString;

                //pCdCarga :=
                //          DmDicion.BrvDicionario.ProxNumeroSequencial('T003', 'CDCARGA');
                //pCdCarga := ProximaCarga;

                pCdCarga := DmDicion.BrvDicionario.ProximoValorSequencial(0, 19);

                //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                //=-=-=-=-= Gravando a carga
                //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                QpCarga.BrUserCode := lCdUsuari;
                QpCarga.BrFormName := lCdsCarga.FieldByName('NmFrmOri').AsString;
                QpCarga.Close;
                QpCarga.CommandText := 'Select * From T003 Where CdCarga is null';
                QpCarga.Open;
                QpCarga.Append;
                QpCarga.FieldByName('CdCarga').AsInteger   := pCdCarga;
                                                   //    lCdsCarga.FieldByName('CdCarga').AsInteger;
                QpCarga.FieldByName('StCarga').AsString    :=
                                                       lCdsCarga.FieldByName('StCarga').AsString;
                //QpCarga.FieldByName('TpVeicul').Value      :=
                //                                       lCdsCarga.FieldByName('TpVeicul').Value;
                QpCarga.FieldByName('TpEscolt').AsInteger  :=
                                                       lCdsCarga.FieldByName('TpEscolt').AsInteger;
                QpCarga.FieldByName('DsDesCar').AsString   :=
                                                       lCdsCarga.FieldByName('DsDesCar').AsString;
                QpCarga.FieldByName('NrMalote').AsInteger  :=
                                                       lCdsCarga.FieldByName('NrMalote').AsInteger;
                QpCarga.FieldByName('KmPreCar').AsInteger  :=
                                                       lCdsCarga.FieldByName('KmPreCar').AsInteger;
                QpCarga.FieldByName('DtPreRet').AsDateTime :=
                                                       lCdsCarga.FieldByName('DtPreRet').AsDateTime;
                QpCarga.FieldByName('NrPesCom').AsFloat    :=
                                                       lCdsCarga.FieldByName('NrPesCom').AsFloat;

                if lCdsCarga.FieldByName('CdEmpDes').AsInteger <> 0 then
                begin
                      QpCarga.FieldByName('CdEmpDes').AsInteger :=
                                                       lCdsCarga.FieldByName('CdEmpDes').AsInteger;
                end;

                if lCdsCarga.FieldByName('CdEmpEsc').AsInteger <> 0 then
                begin
                      QpCarga.FieldByName('CdEmpEsc').AsInteger :=
                                                      lCdsCarga.FieldByName('CdEmpEsc').AsInteger;
                end;

                // =-=-=-= Motoristas
                if lCdsCarga.FieldByName('CdMotor1').AsInteger <> 0 then
                begin
                      QpCarga.FieldByName('CdMotori').AsInteger :=
                                                      lCdsCarga.FieldByName('CdMotor1').AsInteger;
                end;

                if lCdsCarga.FieldByName('CdMotor2').AsInteger <> 0 then
                begin
                      QpCarga.FieldByName('CdMotor1').AsInteger :=
                                                      lCdsCarga.FieldByName('CdMotor2').AsInteger;
                end;

                if lCdsCarga.FieldByName('CdMotor3').AsInteger <> 0 then
                begin
                      QpCarga.FieldByName('CdMotor2').AsInteger :=
                                                      lCdsCarga.FieldByName('CdMotor3').AsInteger;
                end;

                if lCdsCarga.FieldByName('CdMotor4').AsInteger <> 0 then
                begin
                      QpCarga.FieldByName('CdMotor3').AsInteger :=
                                                      lCdsCarga.FieldByName('CdMotor4').AsInteger;
                end;

                // =-=-=-= Veículos
                if lCdsCarga.FieldByName('CdVeicu1').AsInteger <> 0 then
                begin
                      QpCarga.FieldByName('CdVeicul').AsInteger :=
                                                      lCdsCarga.FieldByName('CdVeicu1').AsInteger;
                end;

                if lCdsCarga.FieldByName('CdVeicu2').AsInteger <> 0 then
                begin
                      QpCarga.FieldByName('CdVeicu1').AsInteger :=
                                                      lCdsCarga.FieldByName('CdVeicu2').AsInteger;
                end;

                if lCdsCarga.FieldByName('CdVeicu3').AsInteger <> 0 then
                begin
                      QpCarga.FieldByName('CdVeicu2').AsInteger :=
                                                      lCdsCarga.FieldByName('CdVeicu3').AsInteger;
                end;

                if lCdsCarga.FieldByName('CdVeicu4').AsInteger <> 0 then
                begin
                      QpCarga.FieldByName('CdVeicu3').AsInteger :=
                                                      lCdsCarga.FieldByName('CdVeicu4').AsInteger;
                end;

                //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                //=-=-=-=-= Movimento do CTRC
                //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                QpCargaEmp.BrUserCode := lCdUsuari;
                QpCargaEmp.BrFormName := lCdsCarga.FieldByName('NmFrmOri').AsString;

                QpCargaEmp.Close;
                QpCargaEmp.CommandText := 'Select * From T014 Where CdCarga is null';
                QpCargaEmp.Open;


                lCdsCtrc.IndexDefs.Add('IdxEmpres', 'CDEMPRES', [ixCaseInsensitive,ixDescending]);
                lCdsCtrc.IndexName := 'IdxEmpres';
                lCdsCtrc.First;

                lVrCarga  := 0;
                lQtEntCar := 0;
                lPsCarga  := 0;

                while (not lCdsCTRC.Eof) and (pDsResult = UFrmLogos.cDsMsgOk) do
                begin
                      // =-=-=-=-=-=-=-=-=
                      // Atualizando movimento CTRC
                      // =-=-=-=-=-=-=-=-=
                      QExecute.CommandText :=
                               ' Update T008 set ' +
                               ' CdCarga  = ' + IntToStr(pCdCarga)  + ', ' +
                               ' NrOrdCar = ' +
                                       IntToStr(lCdsCTRC.FieldByName('NrOrdCar').AsInteger) +
                               ' Where CdEmpres = ' + #39 +
                                       lCdsCTRC.FieldByName('CdEmpres').AsString + #39 +
                               ' and   DsModeNF = ' + #39 +
                                       lCdsCTRC.FieldByName('DsModeNf').AsString + #39 +
                               ' and   NrSeriNf = ' + #39 +
                                       lCdsCTRC.FieldByName('NrSeriNF').AsString + #39 +
                               ' and   CdCtrc   = ' + #39 +
                                       lCdsCTRC.FieldByName('CdCTRC').AsString   + #39 +
                               ' and   NrSequen = ' + #39 +
                                       lCdsCTRC.FieldByName('NrSequen').AsString + #39;

                      try
                          QExecute.ExecSQL(True);
                      except
                           on E: Exception do
                           begin
                                 pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                           end;
                      end;

                      // =-=-=-=-=-=-=-=-=
                      // Atualizando conhecimento (CTRC)
                      // =-=-=-=-=-=-=-=-=
                      QExecute.CommandText :=
                               ' Update T002 set '                               +
                             //  ' StCtrc   = "C", '                               +
                               ' CdCarga  = '      + IntToStr(pCdCarga)          +
                               ' Where CdEmpres = ' + #39 +
                                       lCdsCTRC.FieldByName('CdEmpres').AsString + #39 +
                               ' and   DsModeNF = ' + #39 +
                                       lCdsCTRC.FieldByName('DsModeNf').AsString + #39 +
                               ' and   NrSeriNf = ' + #39 +
                                       lCdsCTRC.FieldByName('NrSeriNF').AsString + #39 +
                               ' and   CdCtrc   = ' + #39 +
                                       lCdsCTRC.FieldByName('CdCTRC').AsString   + #39;

                      try
                          QExecute.ExecSQL(True);
                      except
                           on E: Exception do
                                 begin
                                       pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                                 end;
                      end;
                      // =-=-=-=-=-=-=-=-=

                      Inc(lQtEntCar);
                      lVrCarga  := lVrCarga + lCdsCTRC.FieldByName('VrTotPre').AsFloat;
                      lPsCarga  := lPsCarga + lCdsCTRC.FieldByName('NrPeso').AsFloat;


                      lCdsCTRC.Next;
                end;
                if (lCdsCTRC.Eof) or
                   (lCdEmpCar <> lCdsCTRC.FieldByName('CdEmpres').AsInteger) then
                begin
                      //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                      //=-=-=-=-= Gravando a carga Empresa
                      //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                      QpCargaEmp.Append;
                      QpCargaEmp.FieldByName('CdCarga').AsInteger   := pCdCarga;
                      QpCargaEmp.FieldByName('CdEmpres').AsInteger  :=
                                                        lCdsCarga.FieldByName('CdEmpres').AsInteger;
                      QpCargaEmp.FieldByName('VrCarga').AsFloat     := lVrCarga;
                      QpCargaEmp.FieldByName('QtEntCar').AsInteger  := lQtEntCar;
                      QpCargaEmp.FieldByName('PsCarga').AsFloat     := lPsCarga;
                      QpCargaEmp.Post;
                end;
                if (lCdsCarga.FieldByName('NrLibera').AsString <> '') then
                begin
                      lCpParams := TClientDataSet.Create(Self);
                      lCpParams.FieldDefs.Clear;
                      lCpParams.FieldDefs.Add('NrLibera', ftInteger,  0);
                      lCpParams.FieldDefs.Add('CdUsuUti', ftInteger,  0);
                      lCpParams.FieldDefs.Add('TxDadMov', ftBlob,     0);
                      lCpParams.FieldDefs.Add('NmForm',   ftString,  20);
                      lCpParams.CreateDataSet;
                      lCpParams.Append;
                      lCpParams.FieldByName('NrLibera').AsInteger :=
                                                        lCdsCarga.FieldByName('NrLibera').AsInteger;
                      lCpParams.FieldByName('CdUsuUti').AsInteger := lCdUsuari;
                      lCpParams.FieldByName('TxDadMov').AsString  :=
                                                        'CDCARGA' + #13 + IntToStr(pCdCarga);
                      lCpParams.FieldByName('NmForm'  ).AsString  :=
                                                        lCdsCarga.FieldByName('NmFrmOri').AsString;
                      lCpParams.Post;
                      DmAdm.AtualizarChaveLiberacao(lCpParams.Data, lCdUsuari);
                end;
            finally
                 FreeAndNil(lCdsCtrc);
                 FreeAndNil(lCdsCarga);
                 FreeAndNil(lCpParams);
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QpCarga.BrApplyUpdates;
                      QpCargaEmp.BrApplyUpdates;
                      SqlConnTra.Commit(lDsTransa);
                  except
                       on E: Exception do
                             begin
                                 SqlConnTra.Rollback(lDsTransa);
                                 pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end else
            begin
                  SqlConnTra.Rollback(lDsTransa);
            end;
      end;
end;

procedure TSDmTra.SqlConnTraAfterConnect(Sender: TObject);
begin
      SqlConnTra.ExecuteDirect('alter session set nls_numeric_characters = ''.,''');
end;

function TSDmTra.ValidarCarga(pDsCreden : AnsiString; pTpVeicul : AnsiString;
                              pCdVeicul : AnsiString; pQtPesCtr : Double;
                              pDsComWhe : AnsiString) : AnsiString;
var lCdsCtrc   : TClientDataSet;
    lCpPesCar  : TClientDataSet;

    lCdUsuari  : Integer;

    lQtPesVei  : Real;
begin
      try
          if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
          begin
                Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
          end else
          begin
                Result     := UFrmLogos.cDsMsgOk;

                lCdsCtrc   := TClientDataSet.Create(Self);
                lCpPesCar  := TClientDataSet.Create(Self);

                if pDsComWhe <> '' then
                begin
                      lCpPesCar.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(149,
                                                                 '<%DsComWhe%>;' + pDsComWhe, Name);
                end;

                if (lCpPesCar.Active) and (not lCpPesCar.eof) then
                begin
                      Result := UFrmLogos.cDsMsgEr +
                               ' Estorou o valor limite para carga do Cliente '        +
                                          lCpPesCar.FieldByName('CdTomado').AsString   + #13 +
                               ' Valor Máximo: ' + FormatFloat('#,##0.00',
                                          lCpPesCar.FieldByName('VrMaxCar').AsFloat )  + #13 +
                               ' Valor Carga: '  + FormatFloat('#,##0.00',
                                          lCpPesCar.FieldByName('VrTotCar').AsFloat );

                end else
                begin
                      if pCdVeicul <> '' then
                      begin
                            lCpPesCar.Data :=
                               DmDicion.BrvDicionario.RetornaDadosSqlCadastro(150,
                                                              '<%CdVeicul%>;' + pCdVeicul, Name);
                            lQtPesVei := lCpPesCar.FieldByName('QtCapPes').AsFloat;
                            if lQtPesVei < pQtPesCtr then
                            begin
                                  Result := UFrmLogos.cDsMsgEr +
                                                           ' Estorou o limite peso para o veículo';
                            end;
                      end;
                end;

                lCdsCtrc.Close;
                lCpPesCar.Close;
          end;
      finally
          FreeAndNil(lCdsCtrc);
          FreeAndNil(lCpPesCar);
      end;
end;

end.

