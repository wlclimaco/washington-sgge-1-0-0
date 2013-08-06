unit URlc0004;

interface

uses Classes, URlc0000, DbClient, SysUtils, BrvRelASC, BrvDicionario, BrvClientDataSet;

type
  TRlc0004 = class(TRlc0000)
  private
    BrvRelAsc    : TBrvRelAsc;

    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;

    { Private declarations }
    procedure GerarCabecalho(DsEmpres: String; NrPagina: Integer);
    procedure CompletaCabecalho(pCdClient: Integer; pRsClient: String);
  public
    { Public declarations }
    function GerarRelatorio(pCdsParams : TClientDataSet;
                            pBrvDicion : TBrvDicionario;
                            pNmEmpres  : String;
                            pData      : OleVariant): String; Override;
  end;

implementation

uses UDmDicion;

{ TRelPadrao }

constructor TRlc0004.Create(AOwner: TComponent);
begin
  inherited;

end;

destructor TRlc0004.Destroy;
begin

  inherited;
end;

function TRlc0004.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String;
                                 pData      : OleVariant) : String;
var lCdsRelato  : TClientDataSet;
    lCdsNotas   : TBrvClientDataSet;
    NrPagina    : Integer;

    lDsWhere    : String;
    DsEmpres    : String;
    DsLinha     : String;
    DsLinha1    : String;

    DsObsEnt    : String;
    DsPreCor    : String;

    { Inteiros }
    NrNumDia    : Integer;
    RgTipImp    : Integer;

    { Chave de Nota }
    CdEmpres    : String;
    DsModeNF    : String;
    NrSeriNF    : String;
    CdCtrc      : String;

    { Dates }
    DtEmissa    : TDate;
    DtEntreg    : TDate;
    DtPreEnt    : TDate;
    DtPreCor    : TDate;

    { Counters }
    lRegsCount  : Integer;
    lRegIndice  : Integer;

    { Parametros CD }
    SlParams    : TStringList;
    NmParam     : String;
    VlParam     : String;

    NrNota      : String;
    QtEntErr    : Integer;
    VrEntErr    : Real;
    VrNotAtr    : Real;
    QtEntCer    : Integer;
    VrEntCer    : Real;
    VrNotDen    : Real;
    DsComPl1    : String;
    QtAtraza    : String;
    QtAtrazo    : Integer;
    QtNota      : Integer;
    QtNotAtr    : Integer;
    QtNotDen    : Integer;
    QtPesLot    : Integer;
    VrTtPeso    : Real;
    VrTtMerc    : Real;
    VrTtFret    : Real;
    VrTtPeLo    : Real;
    VrTtFreP    : Real;
    VrTtFreV    : Real;
    VrTtSecC    : Real;
    VrTtDesp    : Real;
    VrTtPeda    : Real;
    VrTtOutr    : Real;
    VrTtSubt    : Real;
    VrTtIcms    : Real;
    VrTtIss     : Real;
    DsLinNot    : String;
begin
      Result      := ' ';
      NrPagina    := 1;

      lRegIndice  := 0;
      lRegsCount  := 0;

      try
          BrvRelAsc := TBrvRelAsc.Create(Self);

          try
              BrvRelAsc.NovoRelatorioInvisivel(pBrvDicion.UserCode, pNmEmpres, Name, '');
              lCdsRelato  :=  TClientDataSet.Create(Self);
              lCdsNotas   :=  TBrvClientDataSet.Create(Self);
              lCdsNotas.BrDicionario  :=  pBrvDicion;

              DsEmpres    := pNmEmpres; //lCdsRelato.FieldByName('DsEmpres').AsString;
              GerarCabecalho(DsEmpres, NrPagina);

              CompletaCabecalho(0, 'nome do cliente');

              // Retornando dados da QUERY 59
              lCdsRelato.Data := pBrvDicion.RetornaDadosSqlCadastro(59,
                                              ProcessarParametros(pCdsParams), 'RLC0004');

              { Valores dos  Parametros dos CDS }
              SlParams    :=  TStringList.Create;
              SlParams    :=  pCdsParams.FieldList;   // campos de parametros
              SlParams    :=  lCdsRelato.FieldList;   // campos do query-sql

              lRegsCount  :=  pCdsParams.RecordCount;
              pCdsParams.First;
              for lRegIndice := 0 to lRegsCount-1 do
              begin
                    NmParam :=  pCdsParams.FieldByName('NmParam').AsString;
                    VlParam :=  pCdsParams.FieldByName('DsParam').AsString;

                    //  Numéro de Dias
                    if NmParam  = 'NrNumDia' then
                    begin
                          NrNumDia  :=  pCdsParams.FieldByName('DsParam').AsInteger;
                    end;

                    //  Tipo de Impressão
                    if NmParam  = 'RgTipImp' then
                    begin
                          RgTipImp  :=  pCdsParams.FieldByName('DsParam').AsInteger;
                    end;

                    pCdsParams.Next;
              end;

              { Contadores }
              lRegIndice  := 0;
              lRegsCount  := 0;

              lRegsCount      :=  lCdsRelato.RecordCount;
              lRegIndice      :=  0;

              { Varredura dos dados }
              lCdsRelato.First;
              while not lCdsRelato.Eof do
              begin
                    lRegIndice  :=  lRegIndice  + 1;

                    lCdsNotas.Close;
                    lCdsNotas.BrParams.Clear;
                    lDsWhere  :=  '';

                    { Chave de Nota }
                    CdEmpres    := lCdsRelato.FieldByName('CdEmpres').AsString;
                    DsModeNF    := lCdsRelato.FieldByName('DsModeNf').AsString;
                    NrSeriNF    := lCdsRelato.FieldByName('NrSeriNf').AsString;
                    CdCtrc      := lCdsRelato.FieldByName('CdCtrc').AsString;

                    lDsWhere  := lDsWhere + ' and NtCtrc.CdEmpres = ' + CdEmpres;
                    lDsWhere  := lDsWhere + ' and NtCtrc.DsModeNF = ' + QuotedStr(DsModeNF);
                    lDsWhere  := lDsWhere + ' and NtCtrc.NrSeriNF = ' + QuotedStr(NrSeriNF);
                    lDsWhere  := lDsWhere + ' and NtCtrc.CdCTRC   = ' + CdCtrc;

                    lCdsNotas.Data :=  pBrvDicion.RetornaDadosSqlCadastro(61,
                                               '<%DsComWhe%>;' + lDsWhere, Name);

                    // Data de Emissão
                    DtEmissa := lCdsNotas.FieldByName('DtEmissa').AsDateTime;

                    if lCdsRelato.FieldByName('DtEntreg').AsString <> '' then
                    begin
                          DtEntreg := lCdsRelato.FieldByName('DtEntreg').AsDateTime;
                          DsLinha1 := BrvRelAsc.FormatarStringSemAcento(
                                                         DateToStr(DtEntreg), 10) + '  ';
                    end else
                    begin
                          if lCdsRelato.FieldByName('DtEntMot').AsString <> '' then
                          begin
                                DtEntreg := lCdsRelato.FieldByName('DtEntMot').AsDateTime;
                                DsLinha1 := BrvRelAsc.FormatarStringSemAcento(
                                                           DateToStr(DtEntreg),10) + 'R ';
                          end else
                          begin
                                DtEntreg := lCdsRelato.FieldByName('DtEmissa').AsDateTime;
                                DsLinha1 := StringOfChar(' ', 12);
                          end;
                    end;

                  //  Data de Entrega
                  DtEntreg := StrToDate(FormatDateTime('dd/mm/yyyy', DtEntreg));

                  if (NrNumDia = 0) or
                     (lCdsRelato.FieldByName('QtDiaEnt').AsInteger >= NrNumDia) then
                  begin


                        DsLinha  :=
                        BrvRelAsc.FormatarNumero(
                        lCdsRelato.FieldByName('CdEmpres').AsString, 3, True) + ' ' +

                        BrvRelAsc.FormatarNumero(
                        lCdsRelato.FieldByName('CdCtrc').AsString, 6, True)   + ' ' +

                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('SnFrete').AsString, 1)        + ' ' +

                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('TpTransp').AsString, 1)       + ' ' +
                        FormatDateTime('dd/mm/yyyy',  DtEmissa)               + ' ' +

                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('DtEmissa').AsString, 10)      + ' ' +

                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('DtPreEnt').AsString, 10)      + ' '

                        ;

                        DsLinha := DsLinha + DsLinha1;

                       //  Data Corrigida
                        if lCdsRelato.FieldByName('DtPreCor').AsString <> '' then
                        begin
                              DtPreCor := StrToDate(FormatDateTime('dd/mm/yyyy',
                                          lCdsRelato.FieldByName('DtPreCor').AsDateTime));

                              DsPreCor := DateToStr(DtPreCor) + '      ';
                        end else
                        begin
                              DsPreCor := '99/99/9999      ';
                              DsPreCor := StringOfChar(' ', 16);
                        end;

                        DsLinha := DsLinha +  DsPreCor;

                        // Marcador para indicar se a observação da entrega está preenchido
                        DsObsEnt := '';
                        if Length(Trim(lCdsRelato.FieldByName('ObEntreg').AsString))
                                                                                  > 0 then
                        begin
                              DsLinha  := DsLinha + 'X  ';

                              DsObsEnt := lCdsRelato.FieldByName('ObEntreg').AsString;

                              DsObsEnt := StringReplace(DsObsEnt, #10, ' ', [rfReplaceAll]);
                              DsObsEnt := StringReplace(DsObsEnt, #13, ' ', [rfReplaceAll]);
                        end else
                        begin
                              DsLinha := DsLinha + '   ';
                        end;

                        DsLinha := DsLinha + BrvRelAsc.FormatarNumero(
                                      FormatFloat('#0',
                                         lCdsRelato.FieldByName('QtDiaEnt').AsInteger),
                                                                         2, False) + '  ';

                        DtPreEnt := StrToDate(FormatDateTime('dd/mm/yyyy',
                                    lCdsRelato.FieldByName('DtPreEnt').AsDateTime));

                        {QtAtraza := IntToStr(DMCTR.CalculaDiasUteis(
                                           DMCTR.CcAnaliTransp.FieldByName('EsDestin').AsString,
                                           DMCTR.CcAnaliTransp.FieldByName('CpCidDes').AsString,
                                                                          DtPreEnt, DtEntreg));}

                        if (DtPreEnt < 0) or (DtEntreg < 0) then
                        begin
                              QtAtrazo := -1;
                        end else
                        begin
                              if (DtPreEnt = DtEntreg) or (DtPreEnt > DtEntreg) or
                                                          (DtPreEnt = 0)        then
                              begin
                                    QtAtrazo := 0;
                              end else
                              begin
                                    QtAtrazo := StrToInt(
                                                   FormatFloat('#0',DtEntreg - DtPreEnt));
                              end;
                        end;

                        QtAtraza := IntToStr(QtAtrazo);
                        if QtAtraza = '0' then
                        begin
                              QtAtraza := '';
                        end;
                        DsLinha := DsLinha +
                                   BrvRelAsc.FormatarNumero(QtAtraza, 2, False) + ' ';

                        DsLinha := DsLinha + BrvRelAsc.FormatarNumero(FormatFloat('##0',
                         lCdsRelato.FieldByName('QtDiaEnt').AsInteger), 3, False)+ ' ';

                        if RgTipImp = 0 then
                        begin
                              DsLinNot := DsLinha;
                        end;

                        if RgTipImp = 1 then // Requisição 1104
                        begin
                              DsLinha := DsLinha +
                                BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                lCdsRelato.FieldByName('NrPeso').AsFloat), 14, False) +
                                ' ';
                        end else
                        begin
                              DsLinha := DsLinha +
                                BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                lCdsRelato.FieldByName('NrPeso').AsFloat), 14, False) +
                                ' ';
                        end;

                        if lCdsRelato.FieldByName('NrPesLot').AsFloat <> 0 then
                        begin
                              DsLinha := DsLinha +
                               BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                lCdsRelato.FieldByName('NrPesLot').AsFloat), 14, False) +
                                                                                      ' ';
                              VrTtPeLo := VrTtPeLo +
                                          lCdsRelato.FieldByName('NrPesLot').AsFloat;

                              Inc(QtPesLot);
                        end else
                        begin
                              DsLinha := DsLinha   + '               ';

                              VrTtPeLo := VrTtPeLo +
                                          lCdsRelato.FieldByName('NrPeso').AsFloat;
                        end;

                        if RgTipImp = 1 then // Requisição 1104
                        begin
                              DsLinha := DsLinha +
                                      BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                      lCdsRelato.FieldByName('VrMercad').AsFloat),
                                      14, False) + ' ';
                        end else
                        begin
                              DsLinha := DsLinha +
                                      BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                      lCdsNotas.FieldByName('VrCtb').AsFloat),
                                      14, False) + ' ';
                        end;

//                        DsLinha := DsLinha + '****************';

                        DsLinha := DsLinha +
                        BrvRelAsc.FormatarNumero(FormatFloat('#,##0.00',
                        lCdsRelato.FieldByName('VrFreteP').AsFloat), 12, False) + ' ' +

                        BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                        lCdsRelato.FieldByName('VrTotPre').AsFloat), 13, False)  + ' ' +

                        BrvRelAsc.FormatarNumero(FormatFloat('#,##0.00',
                        lCdsRelato.FieldByName('VrFreteV').AsFloat), 12, False) + ' ' +

                        BrvRelAsc.FormatarNumero(FormatFloat('#,##0.00',
                        lCdsRelato.FieldByName('VrSecCat').AsFloat), 12, False) + ' ' +
                        BrvRelAsc.FormatarNumero(FormatFloat('#,##0.00',
                        lCdsRelato.FieldByName('VrDespac').AsFloat), 12, False) + ' ' +
                        BrvRelAsc.FormatarNumero(FormatFloat('#,##0.00',
                        lCdsRelato.FieldByName('VrPedagi').AsFloat), 12, False) + ' ' +
                        BrvRelAsc.FormatarNumero(FormatFloat('#,##0.00',
                        lCdsRelato.FieldByName('VrOutros').AsFloat), 12, False) + ' ' +
                        BrvRelAsc.FormatarNumero(FormatFloat('#,##0.00',
                        lCdsRelato.FieldByName('VrSubTri').AsFloat), 12, False) + ' ' +
                        BrvRelAsc.FormatarNumero(FormatFloat('#,##0.00',
                        lCdsRelato.FieldByName('VrIcms').AsFloat), 12, False)   + ' ' +
                        BrvRelAsc.FormatarNumero(FormatFloat('#,##0.00',
                        lCdsRelato.FieldByName('VrIssQn').AsFloat), 12, False)  + ' ' +
                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('RsRemete').AsString, 14)        + ' ' +
                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('CpRemete').AsString, 15)        + ' ' +
                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('EsRemete').AsString, 2)         + ' ' +
                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('RsDestin').AsString, 15)        + ' ' +
                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('CpDestin').AsString, 15)        + ' ' +
                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('EsDestin').AsString, 2)         + ' ' +
                        BrvRelAsc.FormatarNumero(
                        lCdsRelato.FieldByName('NrFatura').AsString, 6, True)   + ' ' +
                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('CdConsig').AsString, 7)         + ' ' +
                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('RsCliCon').AsString, 23)        + ' ' +
                        BrvRelAsc.FormatarStringSemAcento(
                        lCdsRelato.FieldByName('CdEmpFro').AsString, 10);

                        VrTtPeso := VrTtPeso + lCdsRelato.FieldByName('NrPeso').AsFloat;
                        VrTtMerc := VrTtMerc + lCdsRelato.FieldByName('VrMercad').AsFloat;
                        VrTtFret := VrTtFret + lCdsRelato.FieldByName('VrTotPre').AsFloat;
                        VrTtFreP := VrTtFreP + lCdsRelato.FieldByName('VrFreteP').AsFloat;
                        VrTtFreV := VrTtFreV + lCdsRelato.FieldByName('VrFreteV').AsFloat;
                        VrTtSecC := VrTtSecC + lCdsRelato.FieldByName('VrSecCat').AsFloat;
                        VrTtDesp := VrTtDesp + lCdsRelato.FieldByName('VrDespac').AsFloat;
                        VrTtPeda := VrTtPeda + lCdsRelato.FieldByName('VrPedagi').AsFloat;
                        VrTtOutr := VrTtOutr + lCdsRelato.FieldByName('VrOutros').AsFloat;
                        VrTtSubt := VrTtSubt + lCdsRelato.FieldByName('VrSubTri').AsFloat;
                        VrTtIcms := VrTtIcms + lCdsRelato.FieldByName('VrIcms').AsFloat;
                        VrTtIss := VrTtIss   + lCdsRelato.FieldByName('VrIssQn').AsFloat;

                        // Notas fiscais dos CTRC
                        QtNota := 0;

                        NrNota   := '';

                        lCdsNotas.First;
                        while not lCdsNotas.Eof do
                        begin
                              if RgTipImp = 1 then
                              begin
                                    NrNota := NrNota + BrvRelAsc.FormatarNumero(
                                            lCdsNotas.FieldByName('NrNota').AsString,
                                            6, True) + ' ' +
                                            BrvRelAsc.FormatarNumero(
                                            lCdsNotas.FieldByName('NrConRas').AsString,
                                            8, True) + ' ';

                              end else
                              begin
                                    if QtNota = 0 then
                                    begin
                                          BrvRelAsc.NovaLinha(DsLinha + ' ' +
                                            BrvRelAsc.FormatarNumero(
                                            lCdsNotas.FieldByName('NrNota').AsString,
                                            6, True) + ' ' +
                                            BrvRelAsc.FormatarNumero(
                                            lCdsNotas.FieldByName('NrConRas').AsString,
                                            8, True) + ' ' + DsObsEnt);
                                    end else
                                    begin
                                          BrvRelAsc.NovaLinha(
                                            DsLinNot +
                                            BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                            lCdsNotas.FieldByName('NrPeso').AsFloat),
                                            14, False) + StringOfChar(' ', 16) +
                                            BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                            lCdsNotas.FieldByName('VrCtb').AsFloat),
                                            14, False) + StringOfChar(' ', 132) +
                                            BrvRelAsc.FormatarStringSemAcento(
                                            lCdsRelato.FieldByName('RsRemete').AsString
                                            , 14)   + ' '+
                                            BrvRelAsc.FormatarStringSemAcento(
                                            lCdsRelato.FieldByName('CpRemete').AsString
                                            , 15)   + ' '+
                                            BrvRelAsc.FormatarStringSemAcento(
                                            lCdsRelato.FieldByName('EsRemete').AsString
                                            , 2)    + ' '+
                                            BrvRelAsc.FormatarStringSemAcento(
                                            lCdsRelato.FieldByName('RsDestin').AsString
                                            , 15)   + ' '+
                                            BrvRelAsc.FormatarStringSemAcento(
                                            lCdsRelato.FieldByName('CpDestin').AsString
                                            , 15)   + ' '+
                                            BrvRelAsc.FormatarStringSemAcento(
                                            lCdsRelato.FieldByName('EsDestin').AsString
                                            , 2)    + ' '+
                                            BrvRelAsc.FormatarNumero(
                                            lCdsRelato.FieldByName('NrFatura').AsString
                                            , 6, True) + ' ' +
                                            BrvRelAsc.FormatarStringSemAcento(
                                               lCdsRelato.FieldByName('CdConsig').AsString, 7) +
                                                                                     ' ' +
                                            BrvRelAsc.FormatarStringSemAcento(
                                               lCdsRelato.FieldByName('RsCliCon').AsString, 23) +
                                                                                     ' ' +
                                            BrvRelAsc.FormatarStringSemAcento(
                                              lCdsRelato.FieldByName('CdEmpFro').AsString, 10)
                                            + ' ' +

                                            BrvRelAsc.FormatarNumero(
                                            lCdsNotas.FieldByName('NrNota').AsString,
                                            6, True) + ' ' +
                                            BrvRelAsc.FormatarNumero(
                                            lCdsNotas.FieldByName('NrConRas').AsString,
                                            8, True) );
                                    end;
                              end;
                              Inc(QtNota);
                              lCdsNotas.Next;
                        end;
                        lCdsNotas.Close;

                        if QtAtraza <> '' then
                        begin
                              Inc(QtEntErr);
                              QtNotAtr := QtNotAtr + QtNota;
                        end else
                        begin
                              Inc(QtEntCer);
                              QtNotDen := QtNotDen + QtNota;
                        end;
                        //
                        if RgTipImp = 1 then
                        begin
                              Delete(NrNota, Length(NrNota), 1);
                              BrvRelAsc.NovaLinha(DsLinha + ' ' + NrNota + ' ' + DsObsEnt);
                        end;

                  end;

                  // Próximo Registro
                  lCdsRelato.Next;;
              end;

              if QtEntErr = 0 then
              begin
                    VrEntErr := 0;
                    VrNotAtr := 0;
              end else
              begin
                    VrEntErr := (QtEntErr / (QtEntErr + QtEntCer));
                    VrNotAtr := (QtNotAtr / (QtNotAtr + QtNotDen));
              end;

              if QtEntCer = 0 then
              begin
                    VrEntCer := 0;
                    VrNotDen := 0;
              end else
              begin
                    VrEntCer := (QtEntCer / (QtEntErr + QtEntCer));
                    VrNotDen := (QtNotDen / (QtNotAtr + QtNotDen));
              end;

              if (QtEntErr > 0) or (QtEntCer > 0) then
              begin
                    BrvRelAsc.NovaLinha(StringOfChar('-', 384));
                    BrvRelAsc.NovaLinha('Totais .................: ' + StringOfChar(' ', 64)        +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtPeso), 14, False)
                                                                                           + ' ' +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtPeLo), 14, False)
                                                                                           + ' ' +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtMerc), 14, False)
                                                                                           + ' ' +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtFreP), 12, False)
                                                                                           + ' ' +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtFret), 13, False)
                                                                                           + ' ' +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtFreV), 12, False)
                                                                                           + ' ' +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtSecC), 12, False)
                                                                                           + ' ' +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtDesp), 12, False)
                                                                                           + ' ' +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtPeda), 12, False)
                                                                                           + ' ' +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtOutr), 12, False)
                                                                                           + ' ' +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtSubt), 12, False)
                                                                                           + ' ' +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtIcms), 12, False)
                                                                                           + ' ' +
                       BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', VrTtIss), 12, False)
                                                                                           + ' ');


                    BrvRelAsc.NovaLinha(StringOfChar('-', 384));
                    BrvRelAsc.NovaLinha('Conhecimentos                             Notas');
                    BrvRelAsc.NovaLinha('Entregas .............: ' + BrvRelAsc.FormatarNumero(
                                               FormatFloat('#0', QtEntErr + QtEntCer), 5, False) +
                                           '             ' + BrvRelAsc.FormatarNumero(
                                               FormatFloat('#0', QtNotAtr + QtNotDen), 5, False));

                    BrvRelAsc.NovaLinha('Dentro do prazo ......: ' +
                                    BrvRelAsc.FormatarNumero(FormatFloat('#0', QtEntCer), 5, False) +
                                                                                             ' ' +
                                    BrvRelAsc.FormatarNumero(FormatFloat('#0.00', VrEntCer * 100), 7,
                                                                                False) + '%    ' +
                                    BrvRelAsc.FormatarNumero(FormatFloat('#0', QtNotDen), 5, False) +
                                                                                             ' ' +
                                    BrvRelAsc.FormatarNumero(FormatFloat('#0.00', VrNotDen * 100), 7,
                                                                                    False) + '%');
                    BrvRelAsc.NovaLinha('Fora do prazo ........: ' +
                                    BrvRelAsc.FormatarNumero(FormatFloat('#0', QtEntErr), 5, False) +
                                                                                             ' ' +
                                    BrvRelAsc.FormatarNumero(FormatFloat('#0.00', VrEntErr * 100), 7,
                                                                                False) + '%    ' +
                                    BrvRelAsc.FormatarNumero(FormatFloat('#0', QtNotAtr), 5, False) +
                                                                                             ' ' +
                                    BrvRelAsc.FormatarNumero(FormatFloat('#0.00', VrNotAtr * 100), 7,
                                                                                    False) + '%');
                    BrvRelAsc.NovaLinha('Entregas por lotacao .: ' +
                                    BrvRelAsc.FormatarNumero(FormatFloat('#0', QtPesLot), 5, False));

                    BrvRelAsc.NovaLinha(StringOfChar('-', 384));
              end;

              { FINAL }
              { Retorna o relatório para a app client }
              Result := BrvRelAsc.RetornaTextoGerado;
          except
              on E : Exception do
              begin
                     Result := 'Erro ao gerar relatório: ' + #13 + E.Message;
              end;
          end;
      finally
          FreeAndNil(BrvRelAsc);
          FreeAndNil(lCdsRelato);
      end;
end;

procedure TRlc0004.GerarCabecalho(DsEmpres : String; NrPagina : Integer);
begin
      BrvRelAsc.NovaLinha(StringOfChar(' ', 384));
      BrvRelAsc.NovaLinha(StringOfChar('-', 384));
      BrvRelAsc.NovaLinha(BrvRelAsc.FormatarStringComAcento(DsEmpres,  145)                       + ' ' +
                       'BRAVO' + StringOfChar(' ', 131)                         + ' ' +
                       'RCL0004');
      BrvRelAsc.NovaLinha('A N A L I S E   D E   P E R F O R M A N C E   D E   T R A N S '  +
                       'P O R  T E ' + StringOfChar(' ', 194) +
                        BrvRelAsc.FormatarStringComAcento('01/01/2011', 19) + 'PAG. ' +
                        BrvRelAsc.FormatarNumero(IntToStr(NrPagina), 4, True));
      BrvRelAsc.NovaLinha(StringOfChar('-', 384));

      BrvRelAsc.Linha := 5;
end;

procedure TRlc0004.CompletaCabecalho(pCdClient : Integer; pRsClient : String);
begin
      if pCdClient > 0 then
      begin
            BrvRelAsc.NovaLinha(BrvRelAsc.FormatarNumero(IntToStr(pCdClient), 6, True)      + ' ' +
                                BrvRelAsc.FormatarStringComAcento(pRsClient, 40));
      end else
      begin
            BrvRelAsc.NovaLinha('TODOS CLIENTES');
      end;

      BrvRelAsc.NovaLinha(StringOfChar('-', 394));
      BrvRelAsc.NovaLinha('Emp Ctrc   F T Coleta     Emissao    Prevista   ' +
                       'Entrega     Prev.Corrigida  Ob ' +
                       'Pz  At  PC           Peso   Peso Lotacao   Vr Mercadori   Frete '+
                       'Peso      Vr Frete  Frete Valor      SEC/CAT     Despacho      ' +
                       'Pedagio       Outros Vr Sub.Trib.   Valor ICMS    Valor ISS '    +
                       'Remetente      Cidade          UF '         +
                       'Destinatario    Cidade          UF Fatura Consignatário        ' +
                       '           Orig/Frota   NF   SHIPMENT');
      BrvRelAsc.NovaLinha(StringOfChar('-', 384));
end;

Initialization
    RegisterClass(TRlc0004);

Finalization
    UnRegisterClass(TRlc0004);

end.

