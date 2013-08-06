unit BrvContas;

interface

uses
  SysUtils, Classes, BrvGestao, BrvContabil, BrvClientDataSet, DbClient, BrvProvider,
  BrvDataSet, Provider, Db;

type
  TBrvContas = class(TBrvGestao)
  private
    { Private declarations }
    gBrvContab : TBrvContabil;
    function  EntradaDocumentoPagarReceber(pCdHistor : Integer;   pTpLancto : String;
                                           pVrLancto : Real;      pCdForPag : Integer;
                                           pCdTitula : Integer;   pCdEmpres : Integer;
                                           pCdUsuari : Integer;   pNrDocto  : Integer;
                                           pDtEmissa : TDateTime; pNrPreCar : Integer;
                                           pNrOrdem  : Integer;   pDtVencto : TDateTime;
                                           pCdEvento : Integer;   pTpPagto  : String;
                                           pNmFormul : String;    pNrPlano  : Integer;
                                           pDsComHis : String;    pCdsN002  : TBrvClientDataSet;
                                           pCdsN003  : TBrvClientDataSet;
                                           pCdsN004  : TBrvClientDataSet) : Integer;

    function CalcularDataVencimentoFixa(pDtVenAnt : TDate;
                                        pNrDiaFix : Integer;
                                        pQtDiaVen : Integer): TDate;

    function ProximoVencimento(pNrParcel : Integer; pDtBase   : TDate;
                               pNrDiaVen : Integer; pDtDiaVen : Integer;
                               pQtDiaPar : Integer): TDate;

    procedure LocalizarDadosEvento(pCdEvento : Integer;
                               var pQtParcel : Integer;
                               var pNrDiaVen : Integer;
                               var pDtDiaVen : Integer;
                               var pQtDiaPar : Integer);

    procedure GravarNotasdaConta(pNrConta  : Integer;     pCdEmpres : Integer;
                                 pCdsN006  : OleVariant;  pNmFormul : String;
                                 pCdUsuari : Integer);

    procedure LancarContabilidade(pCdEmpres : Integer; pDtEmissa : TDate;
                                  pNrConDeb : Integer; pNrConCre : Integer;
                                  pVrParcel : Double;  pNrDocto  : Integer;
                                  pCdHisCon : Integer; pDsComHis : String;
                                  pNmFormul : String;  pSnEncerr : String;
                                  pCdCeCuCr : Integer; pCdCeCuDe : Integer;
                                  pNrPlano  : Integer; pCdUsuari : Integer);
    function ProximaSequenciaDoc(pnrconta, pnrordem: Integer): Integer;
  protected
    { Protected declarations }
  public
    { Public declarations }
    function  LancarContasPagarReceber(pNmFormul : String;     pCdHistor : Integer;
                                       pTpPagRec : String;     pVrLancto : Double;
                                       pCdForPag : Integer;    pCdTitula : Integer;
                                       pCdEmpres : Integer;    pNrDocto  : Integer;
                                       pDtEmissa : TDateTime;  pNrPreCar : Integer;
                                       pNrOrdem  : Integer;    pDtVencto : TDateTime;
                                       pQtParcel : Integer;    pTpPagto  : String;
                                       pDsComHis : String;     pNrPlano  : Integer;
                                       pCdUsuari : Integer;    pCdsN006  : OleVariant;
                                       pVrDescon : Double) : Integer;
    function CdsTempN006: OleVariant;

    procedure BaixaDocumentos(pDtN002: OleVariant; pcdusuari, pcdhistor : Integer;
                              pDtBaixa: TDateTime; pTpOperac: String; pNmFormul: String;
                              CpParCon : OleVariant);
  published
    { Published declarations }
    property BrContabilidade : TBrvContabil read gBrvContab write gBrvContab;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Gestao', [TBrvContas]);
end;

function  TBrvContas.LancarContasPagarReceber(pNmFormul : String;     pCdHistor : Integer;
                                              pTpPagRec : String;     pVrLancto : Double;
                                              pCdForPag : Integer;    pCdTitula : Integer;
                                              pCdEmpres : Integer;    pNrDocto  : Integer;
                                              pDtEmissa : TDateTime;  pNrPreCar : Integer;
                                              pNrOrdem  : Integer;    pDtVencto : TDateTime;
                                              pQtParcel : Integer;    pTpPagto  : String;
                                              pDsComHis : String;     pNrPlano  : Integer;
                                              pCdUsuari : Integer;    pCdsN006  : OleVariant;
                                              pVrDescon : Double) : Integer;
var lCdsN002  : TBrvClientDataSet;
    lCdsN003  : TBrvClientDataSet;
    lCdsN004  : TBrvClientDataSet;
    lProN002  : TBrvProvider;
    lProN003  : TBrvProvider;
    lProN004  : TBrvProvider;
    lDtsN002  : TBrvDataSet;
    lDtsN003  : TBrvDataSet;
    lDtsN004  : TBrvDataSet;
    lCdsN006  : TClientDataSet;
    lCpB012   : TClientDataSet;
    lCpN002   : TClientDataSet;
    lTpFormul : String;
    lNrSeqFor : String;
    lNrDocto  : Integer;
    lCdHisCon : Integer; // Histórico Contabil
    lNrConCre : Integer; // Conta Crédito
    lNrConDeb : Integer; // Conta Débito
    lCdCeCuCr : Integer; // Centro de Custo Crédito
    lCdCeCuDe : Integer; // Centro de Custo Débito
    procedure AbrirTabelas;
    begin
          lCdsN002 := TBrvClientDataSet.Create(Self);
          lCdsN003 := TBrvClientDataSet.Create(Self);
          lCdsN004 := TBrvClientDataSet.Create(Self);
          lCdsN006 := TClientDataSet.Create(Self);
          lCdsN006.Data := pCdsN006;

          lProN002 := TBrvProvider.Create(self);
          lProN003 := TBrvProvider.Create(self);
          lProN004 := TBrvProvider.Create(self);

          lDtsN002 := TBrvDataSet.Create(self);
          lDtsN003 := TBrvDataSet.Create(self);
          lDtsN004 := TBrvDataSet.Create(self);

          lProN002.DataSet       := lDtsN002;
          lProN002.Options       := [poAllowCommandText,poUseQuoteChar];
          lProN002.Name          := 'lProN002';

          lDtsN002.SQLConnection := gSqlConne;
          lDtsN002.BrDicionario  := gDsDicion;

          lCdsN002.ProviderName  := 'lProN002';
          lCdsN002.BrDicionario  := gDsDicion;
          lCdsN002.BrQueryCode   := 104;
          lCdsN002.BrFormName    := pNmFormul;
          lCdsN002.BrUserCode    := pCdUsuari;
          lCdsN002.Open;

          lProN003.DataSet       := lDtsN003;
          lProN003.Options       := [poAllowCommandText,poUseQuoteChar];
          lProN003.Name          := 'lProN003';
          lDtsN003.SQLConnection := gSqlConne;
          lDtsN003.BrDicionario  := gDsDicion;
          lCdsN003.ProviderName  := 'lProN003';
          lCdsN003.BrDicionario  := gDsDicion;
          lCdsN003.BrQueryCode   := 105;
          lCdsN003.BrFormName    := pNmFormul;
          lCdsN003.BrUserCode    := pCdUsuari;
          lCdsN003.Open;

          lProN004.DataSet       := lDtsN004;
          lProN004.Options       := [poAllowCommandText,poUseQuoteChar];
          lProN004.Name          := 'lProN004';
          lDtsN004.SQLConnection := gSqlConne;
          lDtsN004.BrDicionario  := gDsDicion;
          lCdsN004.ProviderName  := 'lProN004';
          lCdsN004.BrDicionario  := gDsDicion;
          lCdsN004.BrQueryCode   := 106;
          lCdsN004.BrFormName    := pNmFormul;
          lCdsN004.BrUserCode    := pCdUsuari;
          lCdsN004.Open;
    end;

begin
      try
          //Desmembrando nome do formulário
          lTpFormul := Copy(pNmFormul, 1, 3); // MOV 0011
          lNrSeqFor := Copy(pNmFormul, 4, 4); // MOV 0011

          AbrirTabelas;

          try
              lCpB012 := TClientDataSet.Create(Self);

              if pTpPagRec = 'R' then
              begin
                    //Parametro de contabilização para Descontos a Receber, nivel 2
                    lCpB012.Data := BrDicionario.RetornaDadosSqlCadastro(223,
                                                 '<%NrSeqFor%>;' + lNrSeqFor + #13 +
                                                 '<%TpFormul%>;' + lTpFormul + #13 +
                                                 '<%CdEmpres%>;' + FormatFloat('0', pCdEmpres) + #13 +
                                                 '<%NrSeqPar%>;' + '2', pNmFormul);
                    lCdHisCon := lCpB012.FieldByName('CdHistor').AsInteger;
                    lNrConCre := lCpB012.FieldByName('NrConCre').AsInteger;
                    lNrConDeb := lCpB012.FieldByName('NrConDeb').AsInteger;
                    lCdCeCuCr := lCpB012.FieldByName('CdCeCuCr').AsInteger;
                    lCdCeCuDe := lCpB012.FieldByName('CdCeCuDe').AsInteger;
              end else
              begin
                    //Parametro de contabilização para Descontos a Pagar, nivel 4
                    lCpB012.Data := BrDicionario.RetornaDadosSqlCadastro(223,
                                               '<%NrSeqFor%>;' + lNrSeqFor + #13 +
                                               '<%TpFormul%>;' + lTpFormul + #13 +
                                               '<%CdEmpres%>;' + FormatFloat('0', pCdEmpres) + #13 +
                                               '<%NrSeqPar%>;' + '4', pNmFormul);
                    lCdHisCon := lCpB012.FieldByName('CdHistor').AsInteger;
                    lNrConCre := lCpB012.FieldByName('NrConCre').AsInteger;
                    lNrConDeb := lCpB012.FieldByName('NrConDeb').AsInteger;
                    lCdCeCuCr := lCpB012.FieldByName('CdCeCuCr').AsInteger;
                    lCdCeCuDe := lCpB012.FieldByName('CdCeCuDe').AsInteger;
              end;
          finally
              FreeAndNil(lCpB012);
          end;

          if (pNrDocto = 0) then
          begin
                lNrDocto := BrDicionario.ParametroDaEmpresa(6,pCdEmpres);
          end else
          begin
                try
                    lCpN002 := TClientDataSet.Create(Self);
                    lCpN002.Data := BrDicionario.RetornaDadosSqlCadastro(225,
                                                 '<%NrDocto%>;' + FormatFloat('0', pNrDocto) + #13 +
                                                 '<%CdEmpres%>;'+ FormatFloat('0', pCdEmpres) + #13+
                                                 '<%TpConta%>;' + pTpPagRec + #13 +
                                                 '<%CdTitula%>;'+ FormatFloat('0', pCdTitula),
                                                 pNmFormul);


                    if (lCpN002.RecordCount > 0) then
                    begin
                          raise Exception.Create('Documento com faturas em aberto!!!'  + #13 +
                                                 'Docto: ' + FormatFloat('0', pNrDocto));
                    end;

                finally
                    FreeAndNil(lCpN002);
                end;
          end;

          Result := EntradaDocumentoPagarReceber(pCdHistor, pTpPagRec, pVrLancto,
                                                 pCdForPag, pCdTitula, pCdEmpres,
                                                 pCdUsuari, lNrDocto,  pDtEmissa,
                                                 pNrPreCar, pNrOrdem,  pDtVencto,
                                                 pQtParcel, pTpPagto,  pNmFormul,
                                                 pNrPlano,  pDsComHis, lCdsN002,
                                                 lCdsN003,  lCdsN004);

          if lCdsN006.RecordCount > 0 then
          begin
                GravarNotasDaConta(Result, pCdEmpres, pCdsN006, pNmFormul, pCdUsuari);
          end;

          if pVrDescon > 0 then
          begin
                LancarContabilidade(pCdEmpres, pDtEmissa, lNrConDeb, lNrConCre,
                                    pVrDescon, lNrDocto,  lCdHisCon, pDsComHis,
                                    pNmFormul, 'N',       lCdCeCuCr, lCdCeCuDe,
                                    pNrPlano,  pCdUsuari);
          end;
      finally
          FreeAndNil(lCdsN002);
          FreeAndNil(lCdsN003);
          FreeAndNil(lCdsN004);

          FreeAndNil(lProN002);
          FreeAndNil(lProN003);
          FreeAndNil(lProN004);

          FreeAndNil(lDtsN002);
          FreeAndNil(lDtsN003);
          FreeAndNil(lDtsN004);
      end;
end;

procedure TBrvContas.GravarNotasDaConta(pNrConta : Integer;      pCdEmpres : Integer;
                                        pCdsN006  : OleVariant;  pNmFormul : String;
                                        pCdUsuari : Integer);
var lCdsN006: TBrvClientDataSet;
    lProN006: TBrvProvider;
    lDtsN006: TBrvDataSet;
    lCdsNota: TClientDataSet;
begin
      try
          lCdsN006 := TBrvClientDataSet.Create(Self);
          lCdsNota := TClientDataSet.Create(Self);
          lCdsNota.Data := pCdsN006;

          lProN006 := TBrvProvider.Create(self);
          lDtsN006 := TBrvDataSet.Create(self);

          lProN006.DataSet       := lDtsN006;
          lProN006.Options       := [poAllowCommandText,poUseQuoteChar];
          lProN006.Name          := 'lProN006';
          lDtsN006.SQLConnection := gSqlConne;
          lDtsN006.BrDicionario  := gDsDicion;
          lCdsN006.ProviderName  := 'lProN006';
          lCdsN006.BrDicionario  := gDsDicion;
          lCdsN006.BrQueryCode   := 111;
          lCdsN006.BrFormName    := pNmFormul;
          lCdsN006.BrUserCode    := pCdUsuari;


          lCdsNota.First;

          while not lCdsNota.Eof do
          begin
                lCdsN006.BrParams.Clear;
                lCdsN006.BrParams.Add('<%NrNota%>;'   + lCdsNota.FieldByName('NrNota'  ).AsString);
                lCdsN006.BrParams.Add('<%NrSeriNf%>;' + lCdsNota.FieldByName('NrSeriNf').AsString);
                lCdsN006.BrParams.Add('<%DsModeNf%>;' + lCdsNota.FieldByName('DsModeNf').AsString);
                lCdsN006.BrParams.Add('<%CdEmpres%>;' + FormatFloat('0', pCdEmpres));
                lCdsN006.Open;

                lCdsN006.Edit;
                lCdsN006.FieldByName('NrConta').AsInteger  := pNrConta;
                lCdsN006.Post;

                lCdsN006.BrApplyUpdates;

                lCdsNota.Next;
          end;

          lCdsN006.BrApplyUpdates;


      finally
          FreeAndNil(lCdsN006);
          FreeAndNil(lCdsNota);
      end;
end;

function  TBrvContas.EntradaDocumentoPagarReceber(pCdHistor : Integer;   pTpLancto : String;
                                                  pVrLancto : Real;      pCdForPag : Integer;
                                                  pCdTitula : Integer;   pCdEmpres : Integer;
                                                  pCdUsuari : Integer;   pNrDocto  : Integer;
                                                  pDtEmissa : TDateTime; pNrPreCar : Integer;
                                                  pNrOrdem  : Integer;   pDtVencto : TDateTime;
                                                  pCdEvento : Integer;   pTpPagto  : String;
                                                  pNmFormul : String;    pNrPlano  : Integer;
                                                  pDsComHis : String;
                                                  pCdsN002  : TBrvClientDataSet;
                                                  pCdsN003  : TBrvClientDataSet;
                                                  pCdsN004  : TBrvClientDataSet) : Integer;
var lNrParcel : Integer;
    lQtParcel : Integer;
    lNrDiaVen : Integer;
    lDtDiaVen : Integer;
    lQtDiaPar : Integer;
    lVrParcel : Real;
    lVrLancto : Real;
    lNrConCre : Integer;
    lNrConDeb : Integer;
    lDtVencto : TDate;
    lCpB0012  : TClientDataSet;
    lTpFormul : String;
    lNrSeqFor : String;
    lCdHisCon : Integer;
    lCdCeCuCr : Integer;
    lCdCeCuDe : Integer;
begin
      if (pVrLancto <= 0) then
      begin
            raise Exception.Create('O valor do documento deve ser maior que zero');
      end else
      if (pCdForPag = 0) then
      begin
            raise Exception.Create('O tipo/forma de pagamento não pode ser zero');
      end else
      if (pCdTitula = 0) then
      begin
            raise Exception.Create('O Titular da conta não pode ser zero');
      end else
      if (gBrvContab = nil) then
      begin
            raise Exception.Create('Objeto ' + Name + ' contido em ' + Owner.Name +
                                   ' não possui propriedade BrContabilidade');
      end else
      begin
            //Desmembrando nome do formulário
            lTpFormul := Copy(pNmFormul, 1, 3); // MOV 0011
            lNrSeqFor := Copy(pNmFormul, 4, 4); // MOV 0011

            try
                lCpB0012 := TClientDataSet.Create(Self);

                if pTpLancto = 'R' then
                begin
                      //Parametro de contabilização para lançamentos a Receber, nivel 1
                      lCpB0012.Data := BrDicionario.RetornaDadosSqlCadastro(223,
                                                   '<%NrSeqFor%>;' + lNrSeqFor + #13 +
                                                   '<%TpFormul%>;' + lTpFormul + #13 +
                                                   '<%CdEmpres%>;' + FormatFloat('0', pCdEmpres) + #13 +
                                                   '<%NrSeqPar%>;' + '1', pNmFormul);

                      lCdHisCon := lCpB0012.FieldByName('CdHistor').AsInteger;
                      lNrConCre := lCpB0012.FieldByName('NrConCre').AsInteger;
                      lNrConDeb := lCpB0012.FieldByName('NrConDeb').AsInteger;
                      lCdCeCuCr := lCpB0012.FieldByName('CdCeCuCr').AsInteger;
                      lCdCeCuDe := lCpB0012.FieldByName('CdCeCuDe').AsInteger;

                      ValidarHistorico(pCdHistor, '"CT", "CR", "RC"');
                end else
                begin
                      //Parametro de contabilização para lançamentos a Pagar, nivel 3
                      lCpB0012.Data := BrDicionario.RetornaDadosSqlCadastro(223,
                                                   '<%NrSeqFor%>;' + lNrSeqFor + #13 +
                                                   '<%TpFormul%>;' + lTpFormul + #13 +
                                                   '<%CdEmpres%>;' + FormatFloat('0', pCdEmpres) + #13 +
                                                   '<%NrSeqPar%>;' + '3', pNmFormul);

                      lCdHisCon := lCpB0012.FieldByName('CdHistor').AsInteger;
                      lNrConCre := lCpB0012.FieldByName('NrConCre').AsInteger;
                      lNrConDeb := lCpB0012.FieldByName('NrConDeb').AsInteger;
                      lCdCeCuCr := lCpB0012.FieldByName('CdCeCuCr').AsInteger;
                      lCdCeCuDe := lCpB0012.FieldByName('CdCeCuDe').AsInteger;

                      ValidarHistorico(pCdHistor, '"CT", "CP", "PT"');
                end;
            finally
                FreeAndNil(lCpB0012);
            end;
      end;

      Result    := gDsDicion.ProximoValorSequencial(pCdEmpres, 3);

      pCdsN002.Append;
      pCdsN002.FieldByName('NrConta' ).AsInteger  := Result;
      pCdsN002.FieldByName('TpConta' ).AsString   := pTpLancto;
      pCdsN002.FieldByName('CdEmpres').AsInteger  := pCdEmpres;
      pCdsN002.FieldByName('CdTitula').AsInteger  := pCdTitula;
      pCdsN002.FieldByName('CdUsuari').AsInteger  := pCdUsuari;
      pCdsN002.FieldByName('NrDocto' ).AsInteger  := pNrDocto;
      pCdsN002.FieldByName('CdForPag').AsInteger  := pCdForPag;
      pCdsN002.FieldByName('VrOrigem').AsFloat    := pVrLancto;
      pCdsN002.FieldByName('DtEmiDoc').AsDateTime := pDtEmissa;
      pCdsN002.FieldByName('DtProces').AsDateTime := gDsDicion.DataHoraServer;
      pCdsN002.FieldByName('NrPreFat').AsInteger  := pNrPreCar;
      pCdsN002.Post;
      pCdsN002.BrApplyUpdates;

      if pCdEvento = 0 then
      begin
            lNrParcel := pNrOrdem;
            lQtParcel := pNrOrdem;
            lVrParcel := pVrLancto;
            lDtVencto := pDtVencto;
      end else
      begin
            lNrParcel := 1;
            LocalizarDadosEvento(pCdEvento, lQtParcel, lNrDiaVen, lDtDiaVen, lQtDiaPar);

            lVrParcel := StrToFloat(FormatFloat('0.00', pVrLancto / lQtParcel));
            lDtVencto := ProximoVencimento(lNrParcel, gDsDicion.DataServer, lNrDiaVen,
                                           lDtDiaVen, lQtDiaPar);
      end;

      lVrLancto := 0;

      while lNrParcel <= lQtParcel do
      begin
            lVrLancto := lVrLancto + lVrParcel;

            if lNrParcel = lQtParcel then
            begin
                  if lVrLancto > pVrLancto then
                  begin
                        lVrParcel := lVrParcel - (lVrLancto - pVrLancto);
                  end
                  else if pVrLancto > lVrLancto then
                       begin
                             lVrParcel := lVrParcel + (pVrLancto - lVrLancto);
                       end;
            end;

            pCdsN003.Append;
            pCdsN003.FieldByName('NrConta' ).AsInteger  := Result;
            pCdsN003.FieldByName('NrOrdem' ).AsInteger  := lNrParcel;
            pCdsN003.FieldByName('VrDocto' ).AsFloat    := lVrParcel;
            pCdsN003.FieldByName('DtVenDoc').AsDateTime := pDtVencto;
            pCdsN003.Post;
            pCdsN003.BrApplyUpdates;

            pCdsN004.Append;
            pCdsN004.FieldByName('NrConta' ).AsInteger  := Result;
            pCdsN004.FieldByName('NrOrdem' ).AsInteger  := lNrParcel;
            pCdsN004.FieldByName('NrSeqCon').AsInteger  := 0;
            pCdsN004.FieldByName('CdHistor').AsInteger  := pCdHistor;
            pCdsN004.FieldByName('CdUsuari').AsInteger  := pCdUsuari;
            pCdsN004.FieldByName('DtMovto' ).AsDateTime := gDsDicion.DataServer;
            pCdsN004.FieldByName('VrDocto' ).AsFloat    := lVrParcel;
            pCdsN004.FieldByName('DtVenDoc').AsDateTime := lDtVencto;
            pCdsN004.FieldByName('TpPagto' ).AsString   := pTpPagto;
            pCdsN004.Post;
            pCdsN004.BrApplyUpdates;

            inc(lNrParcel);

            LancarContabilidade(pCdEmpres, pDtEmissa, lNrConDeb, lNrConCre,
                                lVrParcel, pNrDocto,  lCdHisCon, pDsComHis,
                                pNmFormul, 'N',       lCdCeCuCr, lCdCeCuDe,
                                pNrPlano,  pCdUsuari);

            if lNrParcel <= lQtParcel then
            begin
                  lDtVencto := ProximoVencimento(lNrParcel, lDtVencto, lNrDiaVen,
                                                 lDtDiaVen, lQtDiaPar);
            end;
      end;
end;

procedure TBrvContas.LancarContabilidade(pCdEmpres : Integer; pDtEmissa : TDate;
                                         pNrConDeb : Integer; pNrConCre : Integer;
                                         pVrParcel : Double;  pNrDocto  : Integer;
                                         pCdHisCon : Integer; pDsComHis : String;
                                         pNmFormul : String;  pSnEncerr : String;
                                         pCdCeCuCr : Integer; pCdCeCuDe : Integer;
                                         pNrPlano  : Integer; pCdUsuari : Integer);
var lCdsLanCon : TClientDataSet;
    lNrClaDeb  : String;
    lNrClaCre  : String;
begin
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // Efetuando o lançamento contábil
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      lCdsLanCon := TClientDataSet.Create(Self);

      try
          gBrvContab.CriarTabelaLanctoContabil(lCdsLanCon);
          lNrClaCre := gBrvContab.NumeroClassificacao(pNrPlano, pNrConCre);
          lNrClaDeb := gBrvContab.NumeroClassificacao(pNrPlano, pNrConDeb);


          lCdsLanCon.Append;
          lCdsLanCon.FieldByName('CdEmpres').AsInteger   := pCdEmpres;
          lCdsLanCon.FieldByName('DtLancto').AsDateTime  := pDtEmissa;
          lCdsLanCon.FieldByName('NrConDeb').AsInteger   := pNrConDeb;
          lCdsLanCon.FieldByName('NrConCre').AsInteger   := pNrConCre;
          lCdsLanCon.FieldByName('VrLancto').AsFloat     := pVrParcel;
          lCdsLanCon.FieldByName('NrDocto').AsInteger    := pNrDocto;
          lCdsLanCon.FieldByName('CdHistor').AsInteger   := pCdHisCon;
          lCdsLanCon.FieldByName('DsHistor').AsString    := pDsComHis;
          lCdsLanCon.FieldByName('NmFormul').AsString    := pNmFormul;
          lCdsLanCon.FieldByName('SnEncerr').AsString    := 'N';
          lCdsLanCon.FieldByName('CdCeCuCr').AsInteger   := pCdCeCuCr;
          lCdsLanCon.FieldByName('CdCeCuDe').AsInteger   := pCdCeCuDe;
          lCdsLanCon.FieldByName('NrClaDeb').AsString    := lNrClaDeb;
          lCdsLanCon.FieldByName('NrClaCre').AsString    := lNrClaCre;
          lCdsLanCon.Post;

          gBrvContab.LancarContabilidade(lCdsLanCon.Data, pNrPlano, pCdUsuari, pNmFormul);
      finally
           FreeAndNil(lCdsLanCon);
      end;
end;

function TBrvContas.ProximoVencimento(pNrParcel : Integer; pDtBase   : TDate;
                                      pNrDiaVen : Integer; pDtDiaVen : Integer;
                                      pQtDiaPar : Integer) : TDate;
// pNrDiaVen = Tabela.Evento.NrDiaVen => Intervalo 1a. parcela
// pDtDiaVen = Tabela.Evento.DtDiaVen => Vencer todo dia ...
// pQtDiaPar = Tabela.Evento.QtDiaPar => Quantidade de dias entre parcelas
begin
      if  pNrParcel = 1 then
      begin
            if pDtDiaVen = 0 then
            begin
                  Result := pDtBase + pNrDiaVen;
            end else
            begin
                  Result := CalcularDataVencimentoFixa(pDtBase, pDtDiaVen, pNrDiaVen);
            end;
      end else
      begin
            if pDtDiaVen = 0 then
            begin
                  Result := pDtBase + pQtDiaPar;
            end else
            begin
                  Result := CalcularDataVencimentoFixa(pDtBase, pDtDiaVen, 0);
            end;
      end;
end;

function TBrvContas.CalcularDataVencimentoFixa(pDtVenAnt : TDate; pNrDiaFix: Integer;
                                        pQtDiaVen : Integer): TDate;
var lDtDia : Word;
    lDtMes : Word;
    lDtAno : Word;
begin
      DecodeDate(pDtVenAnt, lDtAno, lDtMes, lDtDia);
      Result := EncodeDate(lDtAno, lDtMes, pNrDiaFix);

      if Result <= (pDtVenAnt + pQtDiaVen) then
      begin
            Result := IncMonth(Result, 1);
      end;
end;

procedure TBrvContas.LocalizarDadosEvento(pCdEvento : Integer; var pQtParcel : Integer;
                                      var pNrDiaVen : Integer; var pDtDiaVen : Integer;
                                      var pQtDiaPar : Integer);
var lCdsEvento : TClientDataSet;
begin
      lCdsEvento := TClientDataSet.Create(Self);

      try
          lCdsEvento.Data := gDsDicion.RetornaDadosSqlCadastro(109,
                                              '<%CdEvento%>;' + IntToStr(pCdEvento),
                                              Owner.Name);

          pQtParcel := lCdsEvento.FieldByName('QtParcel').AsInteger;
          pNrDiaVen := lCdsEvento.FieldByName('NrDiaVen').AsInteger;
          pDtDiaVen := lCdsEvento.FieldByName('DtDiaVen').AsInteger;
          pQtDiaPar := lCdsEvento.FieldByName('QtDiaPar').AsInteger;
      finally
         FreeAndNil(lCdsEvento);
      end;

end;

function TBrvContas.CdsTempN006 : OleVariant;
var lCdsN006 : TClientDataSet;
begin
       try
           lCdsN006 := TClientDataSet.Create(Self);
           lCdsN006.FieldDefs.Clear;

           lCdsN006.FieldDefs.Add('DsModeNf',   FtString,  3, False);
           lCdsN006.FieldDefs.Add('NrSeriNf',   FtString,  6, False);
           lCdsN006.FieldDefs.Add('NrNota',     FtInteger, 0, False);

           lCdsN006.IndexDefs.Clear;
           lCdsN006.CreateDataSet;

           Result := lCdsN006.Data;
       finally
           FreeAndNil(lCdsN006);
       end;
end;

function TBrvContas.ProximaSequenciaDoc(pnrconta: Integer; pnrordem : Integer): Integer;
var lCtN003 : TClientDataSet;
begin
      try
          lCtN003 := TClientDataSet.Create(Self);
          lCtN003.Data := BrDicionario.RetornaDadosSqlCadastro(220,
                                             '<%NrConta%>;' + FormatFloat('0', pnrconta) + Chr(13) +
                                             '<%NrOrdem%>;' + FormatFloat('0', pnrordem),Self.Name);
          Result := lCtN003.FieldByName('NrSeqCon').AsInteger + 1;
      finally
          FreeAndNil(lCtN003);
      end;
end;

procedure TBrvContas.BaixaDocumentos(pDtN002: OleVariant; pcdusuari, pcdhistor : Integer;
                                     pDtBaixa: TDateTime; pTpOperac: String; pNmFormul: String;
                                     CpParCon : OleVariant);
var CtN002 : TClientDataSet;
    CpN006 : TClientDataSet;
    lCpParCon : TClientDataSet;
    CpN006NotaConta : TClientDataSet;

    CcN003BaixaDoc: TBrvClientDataSet;
    QcN003BaixaDoc: TBrvDataSet;
    DpN003BaixaDoc: TBrvProvider;

    CcN004BaixaDoc: TBrvClientDataSet;
    QcN004BaixaDoc: TBrvDataSet;
    DcN004BaixaDoc: TBrvProvider;

    CcN006CanNfCc: TBrvClientDataSet;
    QcN006CanNfCc: TBrvDataSet;
    DcN006CanNfCc: TBrvProvider;

    procedure AbreN006CanNfCc;
    begin
          QcN006CanNfCc := TBrvDataSet.Create(Self);
          QcN006CanNfCc.SQLConnection := gSqlConne;
          QcN006CanNfCc.BrDicionario  := gDsDicion;

          DcN006CanNfCc := TBrvProvider.Create(Self);
          DcN006CanNfCc.DataSet  := QcN006CanNfCc;
          DcN006CanNfCc.Options  := [poAllowCommandText,poUseQuoteChar];
          DcN006CanNfCc.Name     := 'DcN006CanNfCc';

          CcN006CanNfCc := TBrvClientDataSet.Create(Self);
          CcN006CanNfCc.BrDicionario := gDsDicion;
          CcN006CanNfCc.CommandText  := 'Select * From N006 Where 1=2';
          CcN006CanNfCc.ProviderName := 'DcN006CanNfCc';
          CcN006CanNfCc.BrFormName   := pNmFormul;
          CcN006CanNfCc.BrUserCode   := pcdusuari;

          CcN006CanNfCc.Close;
          CcN006CanNfCc.Open;
    end;

    procedure AbreN003BaixaDoc(pNrOrdem, pNrconta: String);
    begin
          QcN003BaixaDoc := TBrvDataSet.Create(Self);
          QcN003BaixaDoc.SQLConnection := gSqlConne;
          QcN003BaixaDoc.BrDicionario  := gDsDicion;

          DpN003BaixaDoc := TBrvProvider.Create(Self);
          DpN003BaixaDoc.DataSet  := QcN003BaixaDoc;
          DpN003BaixaDoc.Options  := [poAllowCommandText,poUseQuoteChar];
          DpN003BaixaDoc.Name     := 'DpN003BaixaDoc';

          CcN003BaixaDoc := TBrvClientDataSet.Create(Self);
          CcN003BaixaDoc.BrDicionario := gDsDicion;
          CcN003BaixaDoc.BrQueryCode  := 219;
          CcN003BaixaDoc.ProviderName := 'DpN003BaixaDoc';
          CcN003BaixaDoc.BrFormName   := pNmFormul;
          CcN003BaixaDoc.BrUserCode   := pcdusuari;

          CcN003BaixaDoc.Close;
          CcN003BaixaDoc.BrParams.Clear;
          CcN003BaixaDoc.BrParams.Add('<%NrOrdem%>;' + pNrOrdem);
          CcN003BaixaDoc.BrParams.Add('<%NrConta%>;' + pNrConta);
          CcN003BaixaDoc.Open;
    end;

    procedure AbreN004BaixaDoc;
    begin
          QcN004BaixaDoc := TBrvDataSet.Create(Self);
          QcN004BaixaDoc.SQLConnection := gSqlConne;
          QcN004BaixaDoc.BrDicionario  := gDsDicion;

          DcN004BaixaDoc := TBrvProvider.Create(Self);
          DcN004BaixaDoc.DataSet  := QcN004BaixaDoc;
          DcN004BaixaDoc.Options  := [poAllowCommandText,poUseQuoteChar];
          DcN004BaixaDoc.Name     := 'DcN004BaixaDoc';

          CcN004BaixaDoc := TBrvClientDataSet.Create(Self);
          CcN004BaixaDoc.BrDicionario := gDsDicion;
          CcN004BaixaDoc.CommandText  := 'Select N004.* From N004 Where 1=2';
          CcN004BaixaDoc.ProviderName := 'DcN004BaixaDoc';
          CcN004BaixaDoc.BrFormName   := pNmFormul;
          CcN004BaixaDoc.BrUserCode   := pcdusuari;

          CcN004BaixaDoc.Close;
          CcN004BaixaDoc.Open;
    end;

begin
      try
          CtN002 := TClientDataSet.Create(Self);
          CpN006 := TClientDataSet.Create(Self);
          CpN006NotaConta := TClientDataSet.Create(Self);

          CpN006.FieldDefs.Clear;
          CpN006.FieldDefs.Add('NrConta', ftInteger, 0);
          CpN006.CreateDataSet;

          CtN002.Data := pDtN002;

          while not CtN002.eof do
          begin
                if (not CpN006.Locate('NrConta', CtN002.FieldByName('NrConta').AsString,
                                                                          [loCaseInsensitive])) then
                begin
                      CpN006.Append;
                      CpN006.FieldByName('Nrconta').AsInteger :=
                                                            CtN002.FieldByName('NrConta').AsInteger;
                      CpN006.Post;
                end;

                AbreN003BaixaDoc(CtN002.FieldByName('NrOrdem').AsString,
                                 CtN002.FieldByName('NrConta').AsString);

                AbreN004BaixaDoc;

                CcN004BaixaDoc.Append;
                CcN004BaixaDoc.FieldByName('cdhistor').AsInteger := pcdhistor;
                CcN004BaixaDoc.FieldByName('cdusuari').AsInteger := pcdusuari;
                CcN004BaixaDoc.FieldByName('dtmovto' ).AsDateTime:= pDtBaixa;
                CcN004BaixaDoc.FieldByName('dtvendoc').AsDateTime:=
                                                      CtN002.FieldByName('dtvendoc').AsDateTime;
                CcN004BaixaDoc.FieldByName('nrconta' ).AsInteger  :=
                                                      CtN002.FieldByName('nrconta').AsInteger;
                CcN004BaixaDoc.FieldByName('nrordem' ).AsInteger  :=
                                                      CtN002.FieldByName('nrordem').AsInteger;
                CcN004BaixaDoc.FieldByName('nrseqcon').AsInteger  :=
                                      ProximaSequenciaDoc(CtN002.FieldByName('nrconta').AsInteger,
                                                          CtN002.FieldByName('nrordem').AsInteger);
                CcN004BaixaDoc.FieldByName('tppagto' ).AsString   :=
                                                      CtN002.FieldByName('tppagto').AsString;
                CcN004BaixaDoc.FieldByName('vrdocto' ).AsFloat    :=
                                                      CtN002.FieldByName('VrDocto').AsFloat;
                CcN004BaixaDoc.Post;
                CcN004BaixaDoc.BrApplyUpdates;

                if CtN002.FieldByName('VrJuros').AsFloat > 0 then
                begin
                      CcN004BaixaDoc.Append;
                      CcN004BaixaDoc.FieldByName('cdhistor').AsInteger := pcdhistor;
                      CcN004BaixaDoc.FieldByName('cdusuari').AsInteger := pcdusuari;
                      CcN004BaixaDoc.FieldByName('dtmovto' ).AsDateTime:= pDtBaixa;
                      CcN004BaixaDoc.FieldByName('dtvendoc').AsDateTime:=
                                                          CtN002.FieldByName('dtvendoc').AsDateTime;
                      CcN004BaixaDoc.FieldByName('nrconta' ).AsInteger  :=
                                                          CtN002.FieldByName('nrconta').AsInteger;
                      CcN004BaixaDoc.FieldByName('nrordem' ).AsInteger  :=
                                                          CtN002.FieldByName('nrordem').AsInteger;
                      CcN004BaixaDoc.FieldByName('nrseqcon').AsInteger  :=
                                      ProximaSequenciaDoc(CtN002.FieldByName('nrconta').AsInteger,
                                                          CtN002.FieldByName('nrordem').AsInteger);
                      CcN004BaixaDoc.FieldByName('tppagto' ).AsString   :=
                                                          CtN002.FieldByName('tppagto').AsString;
                      CcN004BaixaDoc.FieldByName('vrdocto' ).AsFloat    :=
                                                          CtN002.FieldByName('VrJuros').AsFloat;
                      CcN004BaixaDoc.Post;
                      CcN004BaixaDoc.BrApplyUpdates;
                end;


                if (CcN003BaixaDoc.FieldByName('VrDocto').AsFloat >
                                                          CtN002.FieldByName('VrPago').AsFloat) then
                begin
                      CcN003BaixaDoc.Edit;
                      CcN003BaixaDoc.FieldByName('VrDocto').AsFloat :=
                                                     CcN003BaixaDoc.FieldByName('VrDocto').AsFloat -
                                                               CtN002.FieldByName('VrPago').AsFloat;
                      CcN003BaixaDoc.Post;
                end else
                begin
                      CcN003BaixaDoc.Delete;
                end;

                CtN002.Next;

                CcN003BaixaDoc.BrApplyUpdates;
          end;

          if (pTpOperac = 'C') then
          begin
                AbreN006CanNfCc;

                //Gerando novas linhas da tabela N006 para novo faturamento
                CpN006.First;
                while not CpN006.Eof do
                begin
                      CpN006NotaConta.Data := BrDicionario.RetornaDadosSqlCadastro(221,
                                       '<%NrConta%>;' + CpN006.FieldByName('NrConta').AsString, '');

                      while not CpN006NotaConta.Eof do
                      begin
                            CcN006CanNfCc.Append;
                            CcN006CanNfCc.FieldByName('nrnotcon').AsInteger :=
                                              BrDicionario.ProxNumeroSequencial('N006', 'NrNotCon');
                            CcN006CanNfCc.FieldByName('cdempres').AsInteger :=
                                                  CpN006NotaConta.FieldByName('cdempres').AsInteger;
                            CcN006CanNfCc.FieldByName('dsmodenf').AsString  :=
                                                  CpN006NotaConta.FieldByName('dsmodenf').AsString;
                            CcN006CanNfCc.FieldByName('nrnota' ).AsInteger :=
                                                  CpN006NotaConta.FieldByName('nrnota' ).AsInteger;
                            CcN006CanNfCc.FieldByName('nrserinf').AsString  :=
                                                  CpN006NotaConta.FieldByName('nrserinf').AsString;
                            CcN006CanNfCc.Post;

                            CpN006NotaConta.Next;
                      end;

                      CpN006.Next;
                end;

                CcN006CanNfCc.BrApplyUpdates;
          end;

          lCpParCon := TClientDataSet.Create(Self);
          lCpParCon.Data := CpParCon;
          BrContabilidade.LancarContabilidadeXML(lCpParCon.XMLData); //B004 - B008
      finally
          FreeAndNil(CtN002);
          FreeAndNil(CpN006);
      end;
end;

end.
