unit USDmCon;

interface

uses
  SysUtils, Classes, DSServer, DBXOracle, FMTBcd, Provider, DB, SqlExpr, BrvProvider,
  DBClient, BrvClientDataSet, BrvDataSet, BrvString, BrvDb, BrvConnection, DateUtils,
  Forms, ComCtrls, BrvExport, BrvGestao, BrvContabil, UXmlPlanoContas, XMLIntf,
  xmldom, msxmldom, XMLDoc;

type
  TConDinam = record
      ConProvider : TBrvProvider;
      ConDataSet  : TBrvDataSet;
      ConOcupado  : Boolean;
  end;

  TSDmCon = class(TDSServerModule)
    SqlConnCon: TBrvConnection;
    QExecute: TBrvDataSet;
    QpBem: TClientDataSet;
    CpLanBem: TBrvClientDataSet;
    DcLanBem: TBrvProvider;
    QcLanBem: TBrvDataSet;
    CpXML: TBrvClientDataSet;
    DcXML: TBrvProvider;
    QcXML: TBrvDataSet;
    QcLanPis: TBrvDataSet;
    DcLanPis: TBrvProvider;
    CcLanPis: TBrvClientDataSet;
    DCItensPlano: TBrvProvider;
    QCItensPlano: TBrvDataSet;
    DCClassifica: TBrvProvider;
    QCClassifica: TBrvDataSet;
    QCProxConta: TBrvDataSet;
    DCProxConta: TBrvProvider;
    QpPlanoContas: TBrvClientDataSet;
    BrvExport: TBrvExport;
    DcPlano: TBrvProvider;
    QcPlano: TBrvDataSet;
    DcCenCusCre: TBrvProvider;
    QcCenCusCre: TBrvDataSet;
    DcCenCusDeb: TBrvProvider;
    QcCenCusDeb: TBrvDataSet;
    QcPerCon: TBrvDataSet;
    DcPerCon: TBrvProvider;
    CcPerCon: TBrvClientDataSet;
    QcSaldoCenCus: TBrvDataSet;
    DcSaldoCenCus: TBrvProvider;
    CcSaldoCenCus: TBrvClientDataSet;
    BrvContabil: TBrvContabil;
    BrvString: TBrvString;
    CPPlaEmp: TClientDataSet;
    QcB012: TBrvDataSet;
    DpB012: TBrvProvider;
    procedure SqlConnConAfterConnect(Sender: TObject);
  private
    { Private declarations }
    function DataProximaParcela(pDtRefere : TDate; pSnPriPar : Boolean = False) : TDate;
    procedure CarregarTreeViewPlanoContas(pNrPlano: Integer; pTpConta: Byte;
                                          pTrvPlano: TTreeView; pQtNivel : Integer);
    function NivelAtual(pNrClassi: String): String;
    procedure ProximoNivel(pNrNivel : Byte;      pNrPaiNiv: string; var pVarNode: Pstring;
                           pTrvPlano: TTreeView; pTpConta: Byte; pQtNivel : Integer);
    function UltimoNivel(pNrClassi: String): Boolean;
    function ValorAbertura(pDsClass : String; pCdEmpres : String;
                           pNrPlano : Integer; pDtMes   : Integer;
                           pDtAno   : Integer) : Real;
    function SaldoBalanco(pDsClaIni : String; pDsClaFim : String;
                          pCdEmpres : String; pDtInicio : TDate;
                          pDtFinal  : TDate;  pNmColuna : String) : Real;
    procedure VerificarDebitoCredito(var pVrSaldo: Real; var pDcSaldo: String);
    procedure AtualizarSaldoContaContabil(pCdEmpres : Integer; pDtMes    : Integer;
                                          pDtAno    : Integer; pDtProMes : Integer;
                                          pDtProAno : Integer; pDtInicio : TDateTime;
                                          pDtFinal  : TDateTime; pNrPlano : Integer);
    procedure GravarSaldoContaContabil(pCdEmpres : Integer; pDtMes    : Byte;
                                       pDtAno    : Integer; pDtMesAnt : Byte;
                                       pDtAnoAnt : Integer; pNrPlano  : Integer;
                                       pNrConta  : Integer; pVrAbertu : real;
                                       pVrDebito : Real;    pVrCredit : real;
                                       pTpOperac : String;  pSnSalExi : Boolean);
    procedure AtualizarSaldoCentroCusto(pCdEmpres : Integer;   pDtMes    : Integer;
                                        pDtAno    : Integer;   pDtProMes : Integer;
                                        pDtProAno : Integer;   pDtInicio : TDateTime;
                                        pDtFinal  : TDateTime; pNrPlano : Integer;
                                        pNrConta  : Integer);
    procedure GravarSaldoCentroCusto(pCdEmpres : Integer; pDtMes    : Byte;
                                     pDtAno    : Integer; pNrConta  : Integer;
                                     pCdCenCus : Integer; pVrAbertu : Real;
                                     pVrDebito : Real;    pVrCredit : real;
                                     pTpOperac : String;  pNrPlano  : Integer);
    function  TruncVal(pVrBase : Real; pQtDecima : Integer) : Real;
    procedure GeraBalancete(XMLNode: IXMLNode; pCdEmpres : String; pNrPlano: Integer;
                            pDtInicio : TDateTime;   pDtFinal  : TDateTime);
  public
    { Public declarations }
    procedure LancamentoPatrimonial(pDsCreden: String; var pDsResult : String;
                                    pCdBem   : Integer);

    function  AbrirPlanoContas(pDsCreden: String;  var pDsResult : String;
                               pNrPlano : Integer;     pTpConta  : Integer;
                               pQtNivel : Integer;     pCdEmpres: Integer) : String;

    procedure EfetuarLancamentosContabeis(pDsCreden: String; var pDsResult : String;
                                          pNrPlano : Integer;    pNmFormul : String;
                                          pCdsLancto: OleVariant);

    function  Balancete(pDsCreden: String;   var pDsResult : String;
                        pNrPlano : Integer;      pTpConta  : Integer;
                        pQtNivel : Integer;      pCdEmpres : String;
                        pDtInicio: TDateTime;    pDtFinal  : TDateTime)   : String;

    procedure AbreFechaPeriodoContabil(pDsCreden : String;   var pDsResult : String;
                                       pDtAno    : Integer;      pDtMes    : Integer;
                                       pCdEmpres : Integer;      pTpOperac : String);

    procedure EncerrarPeriodoContabil(pDsCreden : String;   var pDsResult : String;
                                      pDtAno    : Integer;      pDtMes    : Integer;
                                      pCdEmpres : Integer;      pNrPlano  : Integer);

    function PlanoContasEmpresa(pDsCreden : String;   var pDsResult : String;
                                pCdEmpres : Integer;      pDtRefere : TDateTime) : Integer;

  end;

implementation

uses UFrmLogos, UDmDicion;

{$R *.dfm}

function TSDmCon.TruncVal(pVrBase : Real; pQtDecima : Integer) : Real;
var lVrDivide : AnsiString;
begin
      lVrDivide := '1' + StringOfChar('0', pQtDecima);
      Result    := StrToInt(lVrDivide);
      Result    := Trunc(pVrBase * Result) / Result;
end;

procedure TSDmCon.LancamentoPatrimonial(
               pDsCreden: String; var pDsResult : String;  pCdBem : Integer);
var lCdUsuari : Integer;
    lDsParam  : String;

    lVlParcel : Currency;
    lVrDifere : Currency;
    lNrParcel : Integer;

    lDtLancto : TDate;
    lDtPriLan : TDate;

    lBsParPis : Currency;
    lBsParCof : Currency;
    lVrParPis : Currency;
    lVrParCof : Currency;

    lDsTransa : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            if pCdBem = 0 then
            begin
                  pDsResult := UFrmLogos.cDsMsgEr + 'Código do bem não pode ser zero';
            end else
            begin
                  lDsParam := '<%CdBem%>;' + IntToStr(pCdBem);
                  QpBem.Data :=
                       DmDicion.BrvDicionario.RetornaDadosSqlCadastro(64, lDsParam, '.');

                  if pDsResult = UFrmLogos.cDsMsgOk then
                  begin
                        lDtLancto := DataProximaParcela(
                                          QpBem.FieldByName('DtEntrad').AsDateTime, True);

                        lVlParcel := TruncVal(QpBem.FieldByName('VrBem').AsFloat /
                                              QpBem.FieldByName('QtMesDep').AsInteger, 2);

                        try
                            CpLanBem.Close;
                            CpLanBem.CommandText := 'Select * From P003 Where CdBem is null';
                            CpLanBem.Open;

                            for lNrParcel := 1 to QpBem.FieldByName('QtMesDep').AsInteger do
                            begin
                                  CpLanBem.Append;
                                  CpLanBem.FieldByName('CdBem').AsInteger     := pCdBem;
                                  CpLanBem.FieldByName('DtLancto').AsDateTime := lDtLancto;
                                  CpLanBem.FieldByName('NrParcel').AsInteger  := lNrParcel;
                                  CpLanBem.FieldByName('VrDeprec').AsFloat    := lVlParcel;
                                  CpLanBem.FieldByName('NrLancto').AsInteger :=
                                           DmDicion.BrvDicionario.ProxNumeroSequencial(
                                                                      'P003', 'NRLANCTO');
                                  CpLanBem.Post;

                                  lDtLancto := DataProximaParcela(lDtLancto);
                            end;

                            lVrDifere := QpBem.FieldByName('VrBem').AsFloat -
                                        (lVlParcel *
                                         QpBem.FieldByName('QtMesDep').AsInteger);

                            CpLanBem.First;

                            //---- Distribuir a diferença de centavos
                            while lVrDifere > 0 do
                            begin
                                  CpLanBem.Edit;
                                  CpLanBem.FieldByName('VrDeprec').AsFloat :=
                                        CpLanBem.FieldByName('VrDeprec').AsFloat + 0.01;
                                  CpLanBem.Post;
                                  CpLanBem.Next;

                                  lVrDifere := lVrDifere - 0.01;
                            end;

                            CcLanPis.Close;
                            CcLanPis.Open;

                            if QpBem.FieldByName('QtMesPis').AsInteger > 0 then
                            begin
                                  lBsParPis :=
                                       TruncVal(QpBem.FieldByName('BsImpPis').AsFloat /
                                                QpBem.FieldByName('QtMesPis').AsInteger, 2);

                                  lBsParCof :=
                                       TruncVal(QpBem.FieldByName('BsImpCof').AsFloat /
                                                QpBem.FieldByName('QtMesPis').AsInteger, 2);

                                  lVrParPis :=
                                       TruncVal(QpBem.FieldByName('VrImpPis').AsFloat /
                                                QpBem.FieldByName('QtMesPis').AsInteger, 2);

                                  lVrParCof :=
                                       TruncVal(QpBem.FieldByName('VrImpCof').AsFloat /
                                                QpBem.FieldByName('QtMesPis').AsInteger, 2);

                                  lDtLancto := DataProximaParcela(
                                          QpBem.FieldByName('DtEntrad').AsDateTime, True);

                                  for lNrParcel := 1 to
                                                QpBem.FieldByName('QtMesPis').AsInteger do
                                  begin
                                        CcLanPis.Append;
                                        CcLanPis.FieldByName('CdBem').AsInteger := pCdBem;

                                        CcLanPis.FieldByName('DtLancto').AsDateTime :=
                                                                                lDtLancto;
                                        CcLanPis.FieldByName('NrParcel').AsInteger  :=
                                                                                lNrParcel;

                                        CcLanPis.FieldByName('BsImpPis').AsFloat :=
                                                                                lBsParPis;
                                        CcLanPis.FieldByName('BsImpCof').AsFloat :=
                                                                                lBsParCof;
                                        CcLanPis.FieldByName('VrImpPis').AsFloat :=
                                                                                lVrParPis;
                                        CcLanPis.FieldByName('VrImpCof').AsFloat :=
                                                                                lVrParCof;

                                        CcLanPis.FieldByName('NrLancto').AsInteger :=
                                              DmDicion.BrvDicionario.ProxNumeroSequencial(
                                                                      'P004', 'NRLANCTO');
                                        CcLanPis.Post;

                                        lDtLancto := DataProximaParcela(lDtLancto);
                                  end;

                                  lBsParPis := QpBem.FieldByName('BsImpPis').AsFloat -
                                              (QpBem.FieldByName('QtMesPis').AsInteger *
                                               lBsParPis);

                                  lBsParCof := QpBem.FieldByName('BsImpCof').AsFloat -
                                              (QpBem.FieldByName('QtMesPis').AsInteger *
                                               lBsParCof);

                                  lVrParPis := QpBem.FieldByName('VrImpPis').AsFloat -
                                              (QpBem.FieldByName('QtMesPis').AsInteger *
                                               lVrParPis);

                                  lVrParCof := QpBem.FieldByName('VrImpCof').AsFloat -
                                              (QpBem.FieldByName('QtMesPis').AsInteger *
                                               lVrParCof);

                                  CcLanPis.First;
                                  lVrDifere := 1;

                                  while lVrDifere > 0 do
                                  begin
                                        CcLanPis.Edit;

                                        if lBsParPis > 0 then
                                        begin
                                              CcLanPis.FieldByName('BsImpPis').AsFloat :=
                                                 CcLanPis.FieldByName('BsImpPis').AsFloat + 0.01;
                                              lBsParPis := lBsParPis - 0.01;
                                        end;

                                        if lBsParCof > 0 then
                                        begin
                                              CcLanPis.FieldByName('BsImpCof').AsFloat :=
                                                 CcLanPis.FieldByName('BsImpCof').AsFloat + 0.01;
                                              lBsParCof := lBsParCof - 0.01;
                                        end;

                                        if lVrParPis > 0 then
                                        begin
                                              CcLanPis.FieldByName('VrImpPis').AsFloat :=
                                                 CcLanPis.FieldByName('VrImpPis').AsFloat + 0.01;
                                              lVrParPis := lVrParPis - 0.01;
                                        end;

                                        if lVrParCof > 0 then
                                        begin
                                              CcLanPis.FieldByName('VrImpCof').AsFloat :=
                                                 CcLanPis.FieldByName('VrImpCof').AsFloat + 0.01;
                                              lVrParCof := lVrParCof - 0.01;
                                        end;

                                        lVrDifere := lBsParPis + lBsParCof + lVrParPis + lVrParCof;

                                        CcLanPis.Post;
                                        CcLanPis.Next;
                                  end;
                            end;

                            lDsTransa := DmDicion.NumeroTransactionID;
                            SqlConnCon.StartTransaction(lDsTransa);

                            CpLanBem.BrApplyUpdates;
                            CcLanPis.BrApplyUpdates;

                            SqlConnCon.Commit(lDsTransa);
                       except
                            on E: Exception do
                                  begin
                                        if SqlConnCon.InTransaction then
                                        begin
                                              SqlConnCon.Rollback(lDsTransa);
                                        end;

                                        pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                                  end;
                       end;
                  end;
            end;
      end;
end;


function TSDmCon.DataProximaParcela(pDtRefere : TDate;
                                    pSnPriPar : Boolean = False) : TDate;
begin
      Result := pDtRefere;

      if (not pSnPriPar) or (DayOf(Result) > 14) then
      begin
            Result := IncMonth(Result);
      end;

      Result := Trunc(EndOfTheMonth(Result));
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=-= procedimentos para montagem e exportação do plano de contas
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

{Função original
function TSDmCon.AbrirPlanoContas(pDsCreden: String; var pDsResult: String;
                                  pNrPlano: Integer; pTpConta: Integer;
                                  pQtNivel: Integer): String;
var lTrvPlano : TTreeView;
    lFrmPlano : TForm;
    lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            if pNrPlano = 0 then
            begin
                  pDsResult := UFrmLogos.cDsMsgEr + 'Código do plano de contas não pode ser zero';
            end else
            begin
                  if pQtNivel = 0 then
                  begin
                        pQtNivel := 1000;
                  end;

                  try
                      lFrmPlano := TForm.Create(Self);
                      lFrmPlano.Visible := False;
                      lTrvPlano := TTreeView.Create(Self);
                      lTrvPlano.Parent := lFrmPlano;
                      lTrvPlano.Items.Clear;

                      CarregarTreeViewPlanoContas(pNrPlano, pTpConta, lTrvPlano, pQtNivel);

                      Result := BrvExport.ExportarTreeView(lTrvPlano);

                  finally
                      FreeAndNil(lTrvPlano);
                      FreeAndNil(lFrmPlano);
                  end;

            end;
      end;
end;}

{Função nova gerando XML a partir do ClientDataSet}
function TSDmCon.AbrirPlanoContas(pDsCreden: String; var pDsResult: String;
                                  pNrPlano: Integer; pTpConta: Integer;
                                  pQtNivel: Integer; pCdEmpres: Integer): String;
var lTrvPlano : TTreeView;
    lFrmPlano : TForm;
    lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            if pNrPlano = 0 then
            begin
                  pDsResult := UFrmLogos.cDsMsgEr + 'Código do plano de contas não pode ser zero';
            end else
            begin
                  if (pCdEmpres > 0) then
                  begin
                        QpPlanoContas.Close;
                        QpPlanoContas.Data  := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(173,
                                                    '<%NrPlano%>;'  + IntToStr(pNrPlano) + Chr(13) +
                                                    '<%CdEmpres%>;' + IntToStr(pCdEmpres), Name);
                  end else
                  begin
                        QpPlanoContas.Close;
                        QpPlanoContas.Data  := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(72,
                                                        '<%NrPlano%>;'  + IntToStr(pNrPlano), Name);

                  end;

                  QpPlanoContas.First;
                  UXmlPlanoContas.PreparaNiveis(QpPlanoContas, pQtNivel);

                  Result := UXmlPlanoContas.AbrirPlanoContas(pTpConta, QpPlanoContas);
            end;
      end;
end;

procedure TSDmCon.CarregarTreeViewPlanoContas(pNrPlano : Integer; pTpConta : Byte;
                                              pTrvPlano : TTreeView; pQtNivel : Integer);
var lNode     : TTreeNode;
    lNrPosFim : Byte;
    lNrClassi : String;
    lNrClaPai : String;
    lNrPaiAnt : String;
    lVarNode   : PString;
begin
      lNrPaiAnt := '';

      QpPlanoContas.Close;

      QpPlanoContas.Data  := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(72,
                                          '<%NrPlano%>;'  + IntToStr(pNrPlano), Name);

      QpPlanoContas.First;

      pTrvPlano.Items.Clear;

      while not QpPlanoContas.EOF do
      begin
            lNrClassi := QpPlanoContas.FieldByName('NrClassi').AsString;

            lNrPosFim := Pos('.', lNrClassi);
            lNrClaPai := Copy(lNrClassi, 1, (lNrPosFim -1));

            if  lNrClaPai <> lNrPaiAnt then
            begin
                  lNrPaiAnt  := lNrClaPai;
                  new(lVarNode);

                  lNode := pTrvPlano.Items.AddObject(nil,
                           BrvString.FormatarStringComAcento(
                                  QpPlanoContas.FieldByName('NrClassi').AsString, 20)    + ' ' +
                           BrvString.FormatarNumero(
                                 QpPlanoContas.FieldByName('NrConta').AsString, 6, True) + ' ' +
                           BrvString.FormatarStringComAcento(
                                 QpPlanoContas.FieldByName('NmConta').AsString, 40)      + ' ',
                                     lVarNode);
                  pTrvPlano.Selected := lNode;

                  if  pTpConta = 1 then
                  begin
                        PString(lNode.Data)^ := QpPlanoContas.FieldByName('NrClassi').AsString;
                  end else
                  begin
                        PString(lNode.Data)^ := QpPlanoContas.FieldByName('NrConta').AsString;
                  end;

                  QpPlanoContas.Next;
            end else
            begin
                  if pQtNivel >= 2 then
                  begin
                        ProximoNivel(2, lNrPaiAnt, lVarNode, pTrvPlano, pTpConta, pQtNivel);
                  end else
                  begin
                        QpPlanoContas.Next;
                  end;
            end;
      end;

      pTrvPlano.FullCollapse;
      QpPlanoContas.Close;
end;

procedure TSDmCon.ProximoNivel(pNrNivel  : Byte;      pNrPaiNiv : string;
                           var pVarNode  : Pstring;   pTrvPlano : TTreeView;
                               pTpConta  : Byte;      pQtNivel  : Integer);
var lNode     : TTreenode;
    lNodeAnt  : TTreeNode;
    lNrPosFim : Byte;
    lNrClassi : String;
    lNrClaPai : String;
    lNrPaiAnt : String;
    lNrPaiAux : string;
    lNrNivel  : Byte;
    lStRateio : String;
begin
      // =-=-=-=-=-=-= somente um espaçador
      new(pVarNode);
      lNode    := pTrvPlano.Items.AddChildObject(pTrvPlano.Selected, '', pVarNode);
      PString(lNode.Data)^ := '';


      lNrPaiAux   :=  '';
      lNrClassi   :=  QpPlanoContas.FieldByName('NrClassi').AsString;

      for lNrNivel := 1 to pNrNivel do
      begin
            lNrPosFim   :=  Pos('.', lNrClassi);

            if  lNrPosFim > 0 then
            begin
                  lNrClaPai   :=  copy(lNrClassi, 1, (lNrPosFim -1));
                  Delete(lNrClassi, 1, lNrPosFim);
            end else
            begin
                  lNrClaPai   :=  lNrClassi;
            end;

            if lNrNivel = (pNrNivel -1) then
            begin
                  lNrPaiAux := lNrClaPai
            end;
      end;

      lNrPaiAnt   :=  '';
      while (not QpPlanoContas.EOF) and (pNrPaiNiv = lNrPaiAux) do
      begin
            lStRateio   := '';
            if QpPlanoContas.FieldByName('SnRatear').AsString = 'S' then
            begin
                  lStRateio := ' * ';
            end;

            if  lNrClaPai <> lNrPaiAnt then
            begin
                  lNrPaiAnt  := lNrClaPai;
                  new(pVarNode);
                  lNodeAnt := pTrvPlano.Selected;
                  lNode    := pTrvPlano.Items.AddChildObject(
                              pTrvPlano.Selected,
                              NivelAtual(QpPlanoContas.FieldByName('NrClassi').AsString) +
                                         QpPlanoContas.FieldByName('NmConta').AsString   +
                                         lStRateio, pVarNode);

                  if  pTpConta = 1 then
                  begin
                        PString(lNode.Data)^ :=
                                           QpPlanoContas.FieldByName('NrClassi').AsString;
                  end else
                  begin
                        PString(lNode.Data)^ :=
                                            QpPlanoContas.FieldByName('NrConta').AsString;
                  end;

                  QpPlanoContas.Next;
                  pTrvPlano.Selected := lNode;
            end else
            begin
                  if pQtNivel >= (pNrNivel + 1)  then
                  begin
                        ProximoNivel(pNrNivel + 1, lNrPaiAnt,
                                                 pVarNode, pTrvPlano, pTpConta, pQtNivel);
                        pTrvPlano.Selected := lNodeAnt;
                  end else
                  begin
                        QpPlanoContas.Next;
                        pTrvPlano.Selected := lNode;
                  end;
            end;

            if not QpPlanoContas.EOF then
            begin
                  lNrClassi := QpPlanoContas.FieldByName('NrClassi').AsString;

                  for lNrNivel := 1 to pNrNivel do
                  begin
                        lNrPosFim := Pos('.', lNrClassi);

                        if  lNrPosFim > 0 then
                        begin
                              lNrClaPai   :=  copy(lNrClassi, 1, (lNrPosFim -1));
                              Delete(lNrClassi, 1, lNrPosFim);

                              if  UltimoNivel(lNrClassi) then
                              begin
                                    pTrvPlano.Selected := lNodeAnt;
                              end;
                        end else
                        begin
                              lNrClaPai          :=  lNrClassi;
                              pTrvPlano.Selected := lNodeAnt;
                        end;

                        if lNrNivel = (pNrNivel -1) then
                        begin
                              lNrPaiAux := lNrClaPai;
                        end;
                  end;
            end;
      end;

      // =-=-=-=-=-=-= somente um espaçador
      new(pVarNode);
      lNode    := pTrvPlano.Items.AddChildObject(pTrvPlano.Selected, '', pVarNode);
      PString(lNode.Data)^ := '';

end;

function TSDmCon.UltimoNivel(pNrClassi : String) : Boolean;
var lNrAuxili : LongInt;
    lNrNivel  : String;
begin
      Result := True;

      try
          if  Pos('.', pNrClassi) > 0 then
          begin
                lNrNivel := Copy(pNrClassi, 1, Pos('.', pNrClassi) - 1);

                lNrAuxili := StrToInt(lNrNIvel);

                if  lNrAuxili > 0 then
                begin
                      Result := False;
                end;
          end else
          begin
                lNrAuxili := StrToInt(pNrClassi);

                if  lNrAuxili > 0 then
                begin
                      Result := False;
                end;
          end;
      except
          Result := False;
      end;
end;

function TSDmCon.NivelAtual(pNrClassi : String) : String;
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

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TSdmCon.EfetuarLancamentosContabeis(pDsCreden: String;   var pDsResult : String;
                                              pNrPlano : Integer;      pNmFormul : String;
                                              pCdsLancto : OleVariant);
var lCdUsuari  : Integer;

    lDsTransa  : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsTransa := DmDicion.NumeroTransactionID;
            SqlConnCon.StartTransaction(lDsTransa);

            try
                BrvContabil.LancarContabilidade(pCdsLancto, pNrPlano,
                                                lCdUsuari,  pNmFormul);

                SqlConnCon.Commit(lDsTransa);
           except
                on E: Exception do
                      begin
                            SqlConnCon.Rollback(lDsTransa);
                            pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                      end;
           end;
      end;
end;

procedure TSDmCon.GeraBalancete(XMLNode: IXMLNode; pCdEmpres : String; pNrPlano: Integer;
                                pDtInicio : TDateTime;   pDtFinal  : TDateTime);
var lNodeText : string;
    lNrIdxNod : Integer;
    lDsClaIni : String;
    lDsClaFim : String;
    lDsClassi : String;
    lDtAno    : Word;
    lDtMes    : Word;
    lDtDia    : Word;
    lVrAbertu : Real;
    lVrDebito : Real;
    lVrCredit : Real;
    lVrSaldo  : Real;
    lDcAbertu : String;
    lDcSaldo  : String;
begin
      if XMLNode.NodeType <> ntElement then
      begin
            Exit;
      end;

      if (XMLNode.AttributeNodes.Count = 2) then
      begin
            lNodeText := XMLNode.Attributes['VR1'];
            lDsClaIni := XMLNode.Attributes['VR2'];

            if lDsClaIni <> '' then
            begin
                  lDsClaFim := Copy(lNodeText, 1, Pos(' ', lNodeText) - 1) + '9';

                  lVrAbertu := ValorAbertura(lDsClaIni, pCdEmpres, pNrPlano, lDtMes,
                                             lDtAno);
                  lVrDebito := SaldoBalanco(lDsClaIni, lDsClaFim, pCdEmpres,
                                            pDtInicio, pDtFinal, 'NrConDeb');

                  lVrCredit := SaldoBalanco(lDsClaIni, lDsClaFim, pCdEmpres,
                                            pDtInicio, pDtFinal, 'NrConCre');

                  lVrSaldo  := lVrAbertu - lVrDebito + lVrCredit;

                  VerificarDebitoCredito(lVrAbertu, lDcAbertu);
                  VerificarDebitoCredito(lVrSaldo,  lDcSaldo);

                  XMLNode.Attributes['VR1'] :=
                            BrvString.FormatarStringSemAcento(lNodeText, 74)     + ' ' +
                            BrvString.FormatarNumero(
                               FormatFloat('###,###,##0.00', lVrAbertu),15, False) +
                            lDcAbertu + ' ' +
                            BrvString.FormatarNumero(
                               FormatFloat('###,###,##0.00', lVrDebito),15, False) +
                            ' ' +
                            BrvString.FormatarNumero(
                               FormatFloat('###,###,##0.00', lVrCredit),15, False) +
                            ' ' +
                            BrvString.FormatarNumero(
                               FormatFloat('###,###,##0.00', lVrSaldo),15, False)  +
                            lDcSaldo;
            end;
      end;

      if XMLNode.HasChildNodes then
      begin
            for lNrIdxNod := 0 to XMLNode.ChildNodes.Count - 1 do
            begin
                  GeraBalancete(XMLNode.ChildNodes[lNrIdxNod], pCdEmpres, pNrPlano,
                                                                               pDtInicio, pDtFinal);
            end;
      end;
end;

{Balancete Novo}
function TSDmCon.Balancete(pDsCreden : String; var pDsResult : String;
                           pNrPlano  : Integer;     pTpConta  : Integer;
                           pQtNivel  : Integer;     pCdEmpres : String;
                           pDtInicio : TDateTime;   pDtFinal  : TDateTime) :  String;
var XMLPlaCon : TXMLDocument;
begin
      Result := AbrirPlanoContas(pDsCreden, pDsResult, pNrPlano, pTpConta, pQtNivel, 0);

      if pDsResult = UFrmLogos.cDsMsgOk then
      begin
            try
                XMLPlaCon := TXMLDocument.Create(Self);
                XMLPlaCon.Active:= False;
                XMLPlaCon.XML.Text := Result;
                XMLPlaCon.Active:= True;

                GeraBalancete(XMLPlaCon.DocumentElement, pCdEmpres, pNrPlano, pDtInicio, pDtFinal);
                Result := XMLPlaCon.XML.Text;
            finally
                FreeAndNil(XMLPlaCon);
            end;
      end;
end;

//Balancete Atual
{function TSDmCon.Balancete(pDsCreden : String; var pDsResult : String;
                           pNrPlano  : Integer;     pTpConta  : Integer;
                           pQtNivel  : Integer;     pCdEmpres : String;
                           pDtInicio : TDateTime;   pDtFinal  : TDateTime) :  String;
var lTrvPlano : TTreeView;
    lFrmPlano : TForm;
    lNrNode   : Integer;
    lDsClaIni : String;
    lDsClaFim : String;
    lDsClassi : String;

    lDtAno    : Word;
    lDtMes    : Word;
    lDtDia    : Word;

    lVrAbertu : Real;
    lVrDebito : Real;
    lVrCredit : Real;
    lVrSaldo  : Real;

    lDcAbertu : String;
    lDcSaldo  : String;
begin
      Result := AbrirPlanoContas(pDsCreden, pDsResult, pNrPlano, pTpConta, pQtNivel);

      if pDsResult = UFrmLogos.cDsMsgOk then
      begin
            DecodeDate(pDtInicio, lDtAno, lDtMes, lDtDia);

            lFrmPlano := TForm.Create(Self);
            lFrmPlano.Visible := False;
            lTrvPlano := TTreeView.Create(Self);
            lTrvPlano.Parent := lFrmPlano;
            lTrvPlano.Items.Clear;

            try
                BrvExport.MontarTreeView(Result, lTrvPlano);

                for lNrNode := 0 to lTrvPlano.Items.Count -1 do
                begin
                      lDsClaIni := PString(lTrvPlano.Items[lNrNode].Data)^;

                      if lDsClaIni <> '' then
                      begin
                            lDsClaFim := Copy(lTrvPlano.Items[lNrNode].Text, 1,
                                              Pos(' ', lTrvPlano.Items[lNrNode].Text) - 1) +
                                         '9';

                            lVrAbertu := ValorAbertura(lDsClaIni, pCdEmpres, pNrPlano, lDtMes,
                                                       lDtAno);
                            lVrDebito := SaldoBalanco(lDsClaIni, lDsClaFim, pCdEmpres,
                                                      pDtInicio, pDtFinal, 'NrConDeb');

                            lVrCredit := SaldoBalanco(lDsClaIni, lDsClaFim, pCdEmpres,
                                                      pDtInicio, pDtFinal, 'NrConCre');

                            lVrSaldo  := lVrAbertu - lVrDebito + lVrCredit;

                            VerificarDebitoCredito(lVrAbertu, lDcAbertu);
                            VerificarDebitoCredito(lVrSaldo,  lDcSaldo);

                            lTrvPlano.Items[lNrNode].Text :=
                                      BrvString.FormatarStringSemAcento(
                                                lTrvPlano.Items[lNrNode].Text, 74)     + ' ' +
                                      BrvString.FormatarNumero(
                                         FormatFloat('###,###,##0.00', lVrAbertu),15, False) +
                                      lDcAbertu + ' ' +
                                      BrvString.FormatarNumero(
                                         FormatFloat('###,###,##0.00', lVrDebito),15, False) +
                                      ' ' +
                                      BrvString.FormatarNumero(
                                         FormatFloat('###,###,##0.00', lVrCredit),15, False) +
                                      ' ' +
                                      BrvString.FormatarNumero(
                                         FormatFloat('###,###,##0.00', lVrSaldo),15, False)  +
                                      lDcSaldo;
                      end;
                end;

                Result := BrvExport.ExportarTreeView(lTrvPlano);
            finally
                FreeAndNil(lTrvPlano);
                FreeAndNil(lFrmPlano);
            end;
      end;
end;}

procedure TSdmCon.VerificarDebitoCredito(var pVrSaldo : Real; var pDcSaldo : String);
begin
      pDcSaldo := ' ';
      pVrSaldo := StrToFloat(FormatFloat('#0.00', pVrSaldo));

      if pVrSaldo > 0 then
      begin
            pDcSaldo := 'C';
      end else
      begin
            if pVrSaldo < 0 then
            begin
                  pDcSaldo := 'D';
                  pVrSaldo := pVrSaldo * (-1);
            end;
      end;
end;


function TSdmCon.SaldoBalanco(pDsClaIni : String; pDsClaFim : String;
                              pCdEmpres : String; pDtInicio : TDate;
                              pDtFinal  : TDate;  pNmColuna : String) : Real;
var lCdsSalCon : TClientDataSet;
    lDsParams  : String;
begin
      Result := 0;

      try
          lDsParams := '<%CdEmpres%>;' + pCdEmpres            + #13 +
                       '<%DtInicio%>;' + DateToStr(pDtInicio) + #13 +
                       '<%DtFinal%>;'  + DateToStr(pDtFinal)  + #13 +
                       '<%NmColuna%>;' + pNmColuna            + #13 +
                       '<%NrClaIni%>;' + pDsClaIni            + #13 +
                       '<%NrClaFim%>;' + pDsClaFim            + #13;

          lCdsSalCon := TClientDataSet.Create(self);

          lCdsSalCon.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(96,
                                                  lDsParams, Name);

          Result := lCdsSalCon.FieldByName('VrLancto').AsFloat;
      finally
          FreeAndNil(lCdsSalCon);
      end;
end;

function TSdmCon.ValorAbertura(pDsClass : String; pCdEmpres : String;
                               pNrPlano : Integer; pDtMes   : Integer;
                               pDtAno   : Integer) : Real;
var lCdsSalAbe : TClientDataSet;
    lDsParams  : String;
begin
      lCdsSalAbe := TClientDataSet.Create(Self);

      try
          lDsParams := '<%CdEmpres%>;' + pCdEmpres           + #13 +
                       '<%NrPlano%>;'  + IntToStr(pNrPlano)  + #13 +
                       '<%DtMes%>;'    + IntToStr(pDtMes)    + #13 +
                       '<%DtAno%>;'    + IntToStr(pDtAno)    + #13 +
                       '<%NrClassi%>;' + pDsClass  + #13;
          lCdsSalAbe.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(95,
                                                  lDsParams, Name);

          Result := lCdsSalAbe.FieldByName('VrAbertu').AsFloat;
      finally
          FreeAndNil(lCdsSalAbe);
      end;

end;

procedure TSDmCon.AbreFechaPeriodoContabil(pDsCreden : String; var pDsResult : String;
                                           pDtAno    : Integer;    pDtMes    : Integer;
                                           pCdEmpres : Integer;    pTpOperac : String);
var lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
    lDtMesFim : Integer;
    lDtAnoFim : Integer;

const lDsAspas = #39;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsTransa := DmDicion.NumeroTransactionID;
            SqlConnCon.StartTransaction(lDsTransa);

            try
                // =-=-=-=-=-=-=-=-=
                // Atualizando período contábil
                // =-=-=-=-=-=-=-=-=
                CcPerCon.Close;
                CcPerCon.BrParams.Clear;
                CcPerCon.BrParams.Add('<%CdEmpres%>;' + IntToStr(pCdEmpres));
                CcPerCon.BrParams.Add('<%DtMes%>;'    + IntToStr(pDtMes));
                CcPerCon.BrParams.Add('<%DtAno%>;'    + IntToStr(pDtAno));
                CcPerCon.Open;

                if  CcPerCon.IsEmpty then
                begin
                      CcPerCon.Append;
                      CcPerCon.FieldByName('CdEmpres').AsInteger := pCdEmpres;
                      CcPerCon.FieldByName('DtMes').AsInteger    := pDtMes;
                      CcPerCon.FieldByName('DtAno').AsInteger    := pDtAno;
                end else
                begin
                      CcPerCon.Edit;
                end;

                CcPerCon.FieldByName('StAbeFec').AsString  := pTpOperac;

                if pTpOperac = 'A' then
                begin
                      CcPerCon.FieldByName('DtEncerr').AsString  := '';
                end else
                begin
                      CcPerCon.FieldByName('DtEncerr').AsDateTime :=
                                                    DmDicion.BrvDicionario.DataHoraServer;
                end;

                CcPerCon.Post;
                CcPerCon.BrApplyUpdates;
                CcPerCon.Close;

                if pTpOperac = 'A' then
                begin
                      lDtMesFim := pDtMes + 1;
                      lDtAnoFim := pDtAno;

                      if lDtMesFim = 13 then
                      begin
                            lDtMesFim := 01;
                            inc(lDtAnoFim);
                      end;

                      // =-=-=-=-=-=-=-=-=
                      // Excluindo lançamentos contábeis de encerramento de período
                      // =-=-=-=-=-=-=-=-=

                      QExecute.CommandText :=
                         'Delete ' +
                         'From  B004 ' +
                         'Where CdEmpres =  ' + IntToStr(pCdEmpres) +
                         ' and  SnEncerr =  ' + lDsAspas + 'S' + lDsAspas +
                         ' and  DtLancto >= ' + lDsAspas + IntToStr(pDtMes)   + '/01/' +
                                                           IntToStr(pDtAno)   + lDsAspas +
                         ' and  DtLancto <  ' + lDsAspas + IntToStr(lDtMesFim) + '/01/'  +
                                                           IntToStr(lDtAnoFim) + lDsAspas;
                      QExecute.ExecSQL(True);

                      // =-=-=-=-=-=-=-=-=
                      // Excluindo Saldo das Contas
                      // =-=-=-=-=-=-=-=-=
                      QExecute.CommandText :=
                                           'Delete From B003 ' +
                                           'Where CdEmpres = ' + IntToStr(pCdEmpres) +
                                           ' and  DtAno    = ' + IntToStr(lDtAnoFim) +
                                           ' and  DtMes    = ' + IntToStr(lDtMesFim);
                      QExecute.ExecSQL(True);
                end;

                SqlConnCon.Commit(lDsTransa);
            except
                 on E: Exception do
                       begin
                             SqlConnCon.Rollback(lDsTransa);
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;
      end;
end;

procedure TSDmCon.EncerrarPeriodoContabil(pDsCreden : String; var pDsResult : String;
                                          pDtAno    : Integer;    pDtMes    : Integer;
                                          pCdEmpres : Integer;    pNrPlano  : Integer);
var lDtInicio : TDateTime;
    lDtFinal  : TDateTime;
    lDtProMes : Byte;
    lDtProAno : Integer;
    lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDtInicio := EncodeDate(pDtAno, pDtMes, 01);

            if pDtMes = 12 then
            begin
                  lDtProMes := 01;
                  lDtProAno := pDtAno + 1;
            end else
            begin
                  lDtProMes := pDtMes + 1;
                  lDtProAno := pDtAno;
            end;

            lDtFinal  := EncodeDate(lDtProAno, lDtProMes, 01);

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
         // =-=-=-=- Primeiramente encerra mês atual e abre próximo
         // =-=-=-=- para que não haja possibilidade de erro nos saldos
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
            AbreFechaPeriodoContabil(pDsCreden, pDsResult, pDtAno, pDtMes, pCdEmpres, 'F');

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  AbreFechaPeriodoContabil(pDsCreden, pDsResult, lDtProAno,
                                           lDtProMes, pCdEmpres, 'A');
            end;

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  lDsTransa := DmDicion.NumeroTransactionID;
                  SqlConnCon.StartTransaction(lDsTransa);

                  try
                       AtualizarSaldoContaContabil(pCdEmpres, pDtMes,    pDtAno, lDtProMes,
                                                   lDtProAno, lDtInicio, lDtFinal,
                                                   pNrPlano);

                       //AtualizarSaldoCentroCusto(pCdEmpres, pDtMes,    pDtAno, lDtProMes,
                       //                          lDtProAno, lDtInicio, lDtFinal,
                       //                          pNrPlano);

                       SqlConnCon.Commit(lDsTransa);
                  except
                       on E: Exception do
                             begin
                                   SqlConnCon.Rollback(lDsTransa);
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;
      end;
end;

procedure TSDmCon.AtualizarSaldoContaContabil(pCdEmpres : Integer;  pDtMes    : Integer;
                                              pDtAno    : Integer;  pDtProMes : Integer;
                                              pDtProAno : Integer;  pDtInicio : TDateTime;
                                              pDtFinal  : TDateTime; pNrPlano : Integer);
var lCdsLanEnc : TClientDataSet;
    lDsParam   : String;
begin
      lCdsLanEnc := TClientDataSet.Create(Self);

      try
          lDsParam := '<%CdEmpres%>;' + IntToStr(pCdEmpres) + #13 +
                      '<%DtMes%>;'    + IntToStr(pDtMes)    + #13 +
                      '<%DtAno%>;'    + IntToStr(pDtAno)    + #13 +
                      '<%NrPlano%>;'  + IntToStr(pNrPlano)  + #13 +
                      '<%DtInicio%>;' + FormaTDateTime(
                                        DmDicion.BrvDicionario.DateSql, pDtInicio) + #13 +
                      '<%DtFinal%>;'  + FormaTDateTime(
                                                DmDicion.BrvDicionario.DateSql, pDtFinal);

          lCdsLanEnc.Data :=
                       DmDicion.BrvDicionario.RetornaDadosSqlCadastro(101, lDsParam, '.');

          while not lCdsLanEnc.EOF do
          begin
                if (lCdsLanEnc.FieldByName('VrAbertu').AsFloat <> 0) or
                   (lCdsLanEnc.FieldByName('VrEncDeb').AsFloat <> 0) or
                   (lCdsLanEnc.FieldByName('VrEncCre').AsFloat <> 0) then
                begin
                      GravarSaldoContaContabil(
                                      pCdEmpres, pDtProMes, pDtProAno, pDtMes, pDtAno,
                                      pNrPlano,
                                      lCdsLanEnc.FieldByName('NrConta').AsInteger,

                                     (lCdsLanEnc.FieldByName('VrAbertu').AsFloat -
                                      lCdsLanEnc.FieldByName('VrEncDeb').AsFloat +
                                      lCdsLanEnc.FieldByName('VrEncCre').AsFloat),

                                      lCdsLanEnc.FieldByName('VrEncDeb').AsFloat,
                                      lCdsLanEnc.FieldByName('VrEncCre').AsFloat,
                                      'T',
                                      not(lCdsLanEnc.FieldByName('DtMes').IsNull));

                       //AtualizarSaldoCentroCusto(pCdEmpres, pDtMes,    pDtAno,   pDtProMes,
                       //                          pDtProAno, pDtInicio, pDtFinal, pNrPlano,
                       //                          lCdsLanEnc.FieldByName('NrConta').AsInteger);


                end;

                lCdsLanEnc.Next;
          end;

      finally
          FreeAndNil(lCdsLanEnc);
      end;
end;

procedure TSDmCon.GravarSaldoContaContabil(pCdEmpres : Integer; pDtMes    : Byte;
                                           pDtAno    : Integer; pDtMesAnt : Byte;
                                           pDtAnoAnt : Integer; pNrPlano  : Integer;
                                           pNrConta  : Integer; pVrAbertu : real;
                                           pVrDebito : Real;    pVrCredit : real;
                                           pTpOperac : String;  pSnSalExi : Boolean);
var DsSql    : String;
    VrAbeStr : String;
    VrDebStr : String;
    VrCreStr : String;
begin
      VrAbeStr := FormatFloat('#0.00', pVrAbertu);
      VrAbeStr := StringReplace(VrAbeStr, ',', '.', [rfReplaceAll]);

      VrDebStr := FormatFloat('#0.00', pVrDebito);
      VrDebStr := StringReplace(VrDebStr, ',', '.', [rfReplaceAll]);

      VrCreStr := FormatFloat('#0.00', pVrCredit);
      VrCreStr := StringReplace(VrCreStr, ',', '.', [rfReplaceAll]);



      QExecute.CommandText := 'Delete From B003 '  +
                              'Where CdEmpres = ' + IntToStr(pCdEmpres) + ' and' +
                              '      DtMes    = ' + IntToStr(pDtMes)    + ' and' +
                              '      DtAno    = ' + IntToStr(pDtAno)    + ' and' +
                              '      NrConta  = ' + IntToStr(pNrConta);
      QExecute.ExecSQL(True);


      QExecute.CommandText :=
           'Insert Into B003 '                                             +
           ' (CdEmpres, DtAno, DtMes, NrPlano, NrConta, VrAbertu, VrEncDeb, VrEncCre) ' +
           'Values ('                                                            +
                     IntToStr(pCdEmpres) + ', ' +
                     IntToStr(pDtAno)    + ', ' +
                     IntToStr(pDtMes)    + ', ' +
                     IntToStr(pNrPlano)  + ', ' +
                     IntToStr(pNrConta)  + ', ' +
                     VrAbeStr           + ', 0, 0)';
      QExecute.ExecSQL(True);

      if not pSnSalExi then
      begin
             QExecute.CommandText :=
                      'Insert Into B003  (CdEmpres, DtAno, DtMes, NrPlano, NrConta, ' +
                                          'VrAbertu, VrEncDeb, VrEncCre) '    +
               'Values ('                                                          +
                          IntToStr(pCdEmpres) + ', ' +
                          IntToStr(pDtAnoAnt) + ', ' +
                          IntToStr(pDtMesAnt) + ', ' +
                          IntToStr(pNrPlano)  + ', ' +
                          IntToStr(pNrConta)  + ', 0, ' +
                          VrDebStr + ',' + VrCreStr + ')';
      end else
      begin
             QExecute.CommandText :=
                     'Update B003 Set '                             +
                     '   VrEncDeb = '     + VrDebStr            +    ', ' +
                     '   VrEncCre = '     + VrCreStr                      +
                     ' Where CdEmpres = ' + IntToStr(pCdEmpres) + ' and ' +
                            'DtAno    = ' + IntToStr(pDtAnoAnt) + ' and ' +
                            'DtMes    = ' + IntToStr(pDtMesAnt) + ' and ' +
                            'NrPlano  = ' + IntToStr(pNrPlano)  + ' and ' +
                            'NrConta  = ' + IntToStr(pNrConta);
      end;

      QExecute.ExecSQL(True);
end;

procedure TSDmCon.AtualizarSaldoCentroCusto(pCdEmpres : Integer;   pDtMes    : Integer;
                                            pDtAno    : Integer;   pDtProMes : Integer;
                                            pDtProAno : Integer;   pDtInicio : TDateTime;
                                            pDtFinal  : TDateTime; pNrPlano : Integer;
                                            pNrConta  : Integer);
var lCdsLanEnc : TClientDataSet;
    lDsParam   : String;
begin
      lCdsLanEnc := TClientDataSet.Create(Self);

      try
          lDsParam := '<%CdEmpres%>;' + IntToStr(pCdEmpres) + #13 +
                      '<%DtMes%>;'    + IntToStr(pDtMes)    + #13 +
                      '<%NrPlano%>;'  + IntToStr(pNrPlano)  + #13 +
                      '<%DtAno%>;'    + IntToStr(pDtAno)    + #13 +
                      '<%DtInicio%>;' + FormaTDateTime(
                                        DmDicion.BrvDicionario.DateSql, pDtInicio) + #13 +
                      '<%DtFinal%>;'  + FormaTDateTime(
                                                DmDicion.BrvDicionario.DateSql, pDtFinal);

          lCdsLanEnc.Data :=
                       DmDicion.BrvDicionario.RetornaDadosSqlCadastro(102, lDsParam, '.');

          while not lCdsLanEnc.EOF do
          begin
                if (lCdsLanEnc.FieldByName('VrAbertu').AsFloat <> 0) or
                   (lCdsLanEnc.FieldByName('VrEncDeb').AsFloat <> 0) or
                   (lCdsLanEnc.FieldByName('VrEncCre').AsFloat <> 0) then
                begin
                      GravarSaldoCentroCusto(pCdEmpres, pDtProMes, pDtProAno,
                                             lCdsLanEnc.FieldByName('NrConta').AsInteger,
                                             lCdsLanEnc.FieldByName('CdCenCus').AsInteger,
                                             lCdsLanEnc.FieldByName('VrAbertu').AsFloat,
                                             lCdsLanEnc.FieldByName('VrEncDeb').AsFloat,
                                             lCdsLanEnc.FieldByName('VrEncCre').AsFloat,
                                             'T', pNrPlano);
                end;

                lCdsLanEnc.Next;
          end;
      finally
          FreeAndNil(lCdsLanEnc);
      end;
end;

procedure TSDmCon.GravarSaldoCentroCusto(pCdEmpres : Integer; pDtMes    : Byte;
                                         pDtAno    : Integer; pNrConta  : Integer;
                                         pCdCenCus : Integer; pVrAbertu : Real;
                                         pVrDebito : Real;    pVrCredit : real;
                                         pTpOperac : String;  pNrPlano  : Integer);
begin
      QCSaldoCenCus.Close;
      QCSaldoCenCus.BrParams.Clear;
      QCSaldoCenCus.BrParams.Add('<%CdEmpres%>;' + IntToStr(pCdEmpres));
      QCSaldoCenCus.BrParams.Add('<%DtMes%>;'    + IntToStr(pDtMes));
      QCSaldoCenCus.BrParams.Add('<%DtAno%>;'    + IntToStr(pDtAno));
      QCSaldoCenCus.BrParams.Add('<%NrPlano%>;'  + IntToStr(pNrPlano));
      QCSaldoCenCus.BrParams.Add('<%NrConta%>;'  + IntToStr(pNrConta));
      QCSaldoCenCus.BrParams.Add('<%CdCenCus%>;' + IntToStr(pCdCenCus));
      QCSaldoCenCus.Open;

      if  QCSaldoCenCus.IsEmpty then
      begin
            QCSaldoCenCus.Append;
            QCSaldoCenCus.FieldByName('CdEmpres').AsInteger := pCdEmpres;
            QCSaldoCenCus.FieldByName('DtMes').AsInteger    := pDtMes;
            QCSaldoCenCus.FieldByName('DtAno').AsInteger    := pDtAno;
            QCSaldoCenCus.FieldByName('NrConta').AsInteger  := pNrConta;
            QCSaldoCenCus.FieldByName('CdCenCus').AsInteger := pCdCenCus;
      end else
      begin
            QCSaldoCenCus.Edit;
      end;

      case pTpOperac[1] of
           'A': QCSaldoCenCus.FieldByName('VrAbertu').AsFloat :=
                              pVrAbertu - pVrDebito + pVrCredit;
           'E': begin
                      QCSaldoCenCus.FieldByName('VrEncDeb').AsFloat := pVrDebito;
                      QCSaldoCenCus.FieldByName('VrEncCre').AsFloat := pVrCredit;
                end;
           'T': begin
                      QCSaldoCenCus.FieldByName('VrAbertu').AsFloat :=
                                                        pVrAbertu - pVrDebito + pVrCredit;
                      QCSaldoCenCus.FieldByName('VrEncDeb').AsFloat := pVrDebito;
                      QCSaldoCenCus.FieldByName('VrEncCre').AsFloat := pVrCredit;
                end;
      end;

      QCSaldoCenCus.Post;
end;

function TSDmCon.PlanoContasEmpresa(pDsCreden : String;   var pDsResult : String;
                                    pCdEmpres : Integer;      pDtRefere : TDateTime) : Integer;
var lDsParams : String;
    lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                lDsParams := FormatDateTime(DmDicion.BrvDicionario.DateSql, pDtRefere);

                lDsParams := '<%CdEmpres%>;' + IntToStr(pCdEmpres) + #13 +
                             '<%DtIniVig%>;' + lDsParams           + #13 +
                             '<%DtFimVig%>;' + lDsParams;

                CPPlaEmp.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(172, lDsParams, '');

                if CPPlaEmp.RecordCount = 0 then
                begin
                      pDsResult := UFrmLogos.cDsMsgEr + 'Empresa não possui plano de contas';
                end else
                begin
                      pDsResult := UFrmLogos.cDsMsgOk;
                      Result    := CPPlaEmp.Fields[0].AsInteger;
                end;
            finally
                CPPlaEmp.Close;
            end;
      end;
end;

procedure TSDmCon.SqlConnConAfterConnect(Sender: TObject);
begin
      SqlConnCon.ExecuteDirect('alter session set nls_numeric_characters = ''.,''');
end;

end.

