unit URlc0007;

interface

uses Classes, URlc0000, DbClient, SysUtils, BrvDicionario, BrvClientDataSet,
     ComCtrls, Forms, BrvRelAsc, DateUtils;


type
  TRlc0007 = class(TRlc0000)
  private
    { Private declarations }
    gRelAsc    : TBrvRelAsc;
    gSnEmpDat  : Boolean;
    gDtInicia  : TDate;
    gNrPlano   : String;
    gNmEmpres  : String;
    gDtMes     : String;
    gDtAno     : String;
    gCdCenCus  : Integer;
    gDsCenCus  : String;
    gCdsRelato : TClientDataSet;
    gDsMasLim  : String;
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure   Cabecalho(Sender : TObject);
    procedure   CompletaCabecalho(Sender : TObject);
    procedure ImprimirDetalhesRelatorio(pDtInicia : TDateTime; pDtFinal  : TDateTime;
                                        pCdCenCus : Integer;   pCdEmpres : String;
                                        pNrConta  : String;    pSnEmpDat : Boolean;
                                        pSnSalZer : Boolean;   pSnTotDat : Boolean);
    function SaldoContaCotabil(pCdEmpres : String;   pNrConta  : String;
                               pDtRefere : TDate) : Real;
    function SomaValorLancamentos(pCdEmpres : String; pNrConta  : String;
                                  pDtLanIni : TDate;  pDtLanFim : TDate;
                                  pDsAtribu : String) : Real;
    procedure VerificarDebitoCredito(var pVrSaldo: Real; var pDcSaldo: String);
    procedure ImprimeComplementoCabecalho;
    procedure EncontrarParentes(var NrClassi : String; var NrNivPai : String;
                                var NrNivAtu : String; var NrNivFil : String;
                                var NrPosAtu : byte);
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

procedure TRlc0007.Cabecalho(Sender: TObject);
var lDtInicio : TDate;
begin
      if gSnEmpDat then
      begin
            lDtInicio := Now;
      end else
      begin
            lDtInicio := gDtInicia;
      end;

      gRelAsc.NovaLinha(StringOfChar('-', 132));
      gRelAsc.NovaLinha(gRelAsc.FormatarStringSemAcento(gNmEmpres, 40) +
                        StringOfChar(' ', 51) + '   BRAVO   ' +
                        gRelAsc.FormatarStringSemAcento(
                                gRelAsc.AlinharDireita('RLC0007', 30), 30));

      gRelAsc.NovaLinha('R A Z A O    D E    ' +
                        gRelAsc.FormatarNumero(gDtMes, 2, True) + '/' +
                        gRelAsc.FormatarNumero(gDtAno, 4, True) +
                        StringOfChar(' ',72) +
                        gRelAsc.FormatarStringSemAcento(DateTimeToStr(lDtInicio), 23)   +
                        ' PAG. ' +
                        gRelAsc.FormatarNumero(IntToStr(gRelAsc.Pagina), 4, True));
      gRelAsc.NovaLinha(StringOfChar('-', 132));
end;

procedure TRlc0007.CompletaCabecalho(Sender: TObject);
begin
       ImprimeComplementoCabecalho;
end;

procedure TRlc0007.ImprimeComplementoCabecalho;
var lNrClassi  : String;
    lNrNivPai  : String;
    lNrNivAtu  : String;
    lNrNivFil  : String;
    lNrPosAtu  : Byte;
    lNrClaPai  : String;
    lNmConPai  : String;
    x          : byte;
    lCdsClaPai : TClientDataSet;
    lDsParam   : String;
begin
      lNrClassi := gCdsRelato.FieldByName('NrClassi').AsString;
      lNrNivPai := '';
      lNrNivAtu := '';
      lNrNivFil := '';
      lNrPosAtu := 0;

      EncontrarParentes(lNrClassi, lNrNivPai, lNrNivAtu, lNrNivFil, lNrPosAtu);
      lNrClaPai := lNrClassi + lNrNivPai;

      for x := (Length(lNrClaPai) + 1) to Length(gDsMasLim) do
      begin
            if  gDsMasLim[x] = '.' then
            begin
                  lNrClaPai := lNrClaPai + '.'
            end else
            begin
                  lNrClaPai := lNrClaPai + '0'
            end;
      end;

      if lNrClaPai[Length(lNrClaPai)] = '.' then
      begin
            Delete(lNrClaPai, Length(lNrClaPai), 1);
      end;


      lCdsClaPai := TClientDataSet.Create(Self);

      try
          lDsParam := '<%CdEmpres%>;' + gNrPlano + #13 +
                      '<%NrClassi%>;' + lNrClaPai;

          lCdsClaPai.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(94,
                                                                     lDsParam, 'RLC0007');

          if  not lCdsClaPai.Eof then
          begin
                lNmConPai := lCdsClaPai.FieldByName('NmConta').AsString;
          end else
          begin
                lNmConPai := 'NAO ENCONTRADA';
          end;

      finally
           FreeAndNil(lCdsClaPai);
      end;

      gRelAsc.NovaLinha(gRelAsc.FormatarStringSemAcento(lNrClaPai,  16) +       ' ' +
                        gRelAsc.FormatarStringSemAcento(lNmConPai,  40) +       ' ' +
                        gRelAsc.FormatarStringSemAcento(
                                gCdsRelato.FieldByName('NrClassi').AsString, 20) +   ' ' +
                        gRelAsc.FormatarStringSemAcento(
                                gCdsRelato.FieldByName('NmConta').AsString,  40) +
                        ' Conta ' +
                        gRelAsc.FormatarNumero(FormatFloat('000000',
                                  gCdsRelato.FieldByName('NrConta').AsInteger), 6, True));
      gRelAsc.NovaLinha(StringOfChar('-', 132));

      if gCdCenCus <> 0 then
      begin
            gRelAsc.NovaLinha('Centro de Custo: ' +
                                                  IntToStr(gCdCenCus) + '  ' + gDsCenCus);
            gRelAsc.NovaLinha(StringOfChar('-', 132));
      end;

      gRelAsc.NovaLinha('Dt Documento  Contrapartida                Debito        '      +
                       ' Credito            Saldo Historico            Complementar');
      gRelAsc.NovaLinha(StringOfChar('-', 132));
end;

procedure TRlc0007.EncontrarParentes(var NrClassi : String; var NrNivPai : String;
                                     var NrNivAtu : String; var NrNivFil : String;
                                     var NrPosAtu : byte);
var SnPai   : Boolean;
    SnAtual : Boolean;
    x       : byte;
begin
      SnPai    := False;
      SnAtual  := False;
      NrPosAtu := 1;
      x        := Length(NrClassi);

      while (not SnPai) and (x > 0) do
      begin
            if  NrClassi[x] = '.' then
            begin
                  if  StrToInt(NrNivPai) <> 0 then
                  begin
                        if SnAtual then
                        begin
                              SnPai := True;
                        end else
                        begin
                              SnAtual    := True;
                              NrNivAtu   := NrNivPai;
                              NrNivPai   :=  '';
                              Delete(NrClassi, x, 1);
                        end;
                  end else
                  begin
                        if  not SnAtual then
                        begin
                              inc(NrPosAtu);
                              NrNivFil := NrNivPai;
                        end;

                        NrNivPai    :=  '';
                        Delete(NrClassi, x, 1);
                  end;
            end else
            begin
                  NrNivPai    := NrClassi[x] + NrNivPai;
                  Delete(NrClassi, x, 1);
            end;

            dec(x);
      end;
end;

constructor TRlc0007.Create(AOwner: TComponent);
begin
     inherited;
end;

destructor TRlc0007.Destroy;
begin
     inherited;
end;

function TRlc0007.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String;
                                 pData      : OleVariant) : String;
var lCdsAuxili : TClientDataSet;
    lNrClaIni  : String;
    lNrClaFim  : String;
    lNrPosPon  : Integer;
    x          : Integer;
    lDsParam   : String;

    lDtFinal   : TDate;
    lSnSalZer  : Boolean;
    lSnTotDat  : Boolean;
    lCdEmpres  : String;
begin
      Result    := ' ';

      try
          gRelAsc := TBrvRelAsc.Create(Self);
          gRelAsc.OnCabecalho       := Cabecalho;
          gRelAsc.CompletaCabecalho := CompletaCabecalho;
          gRelAsc.ColunasPorLinha   := Rel132Col;

          try
              lCdsAuxili      := TClientDataSet.Create(Self);
              lCdsAuxili.Data := pData;

              gRelAsc.PaginaInicial := lCdsAuxili.FieldByName('NrPagIni').AsInteger;
              gDtInicia := lCdsAuxili.FieldByName('DtInicia').AsDateTime;
              lDtFinal  := lCdsAuxili.FieldByName('DtFinal').AsDateTime;
              gCdCenCus := lCdsAuxili.FieldByName('CdCenCus').AsInteger;
              gDsCenCus := lCdsAuxili.FieldByName('DsCenCus').AsString;
              lCdEmpres := lCdsAuxili.FieldByName('CdEmpres').AsString;
              gSnEmpDat := lCdsAuxili.FieldByName('SnEmpDat').AsBoolean;
              lSnSalZer := lCdsAuxili.FieldByName('SnSalZer').AsBoolean;
              lSnTotDat := lCdsAuxili.FieldByName('SnTotDat').AsBoolean;
              gDsMasLim := StringReplace(lCdsAuxili.FieldByName('DsMasLim').AsString, '_',
                                                                     ' ', [rfReplaceAll]);
              gNrPlano  := lCdsAuxili.FieldByName('NrPlano').AsString;

              // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
              // Determinando classificação inicial
              // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
              if lCdsAuxili.FieldByName('NrConIni').AsInteger = 0 then
              begin
                    lNrClaIni := '';
              end else
              begin
                    lNrClaIni := ' and B002.NrClassi  >= ' + ' "' +
                                       lCdsAuxili.FieldByName('DsMasIni').AsString + '" ';
              end;

              // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
              // Determinando classificação final
              // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
              if lCdsAuxili.FieldByName('NrConFim').AsInteger = 0 then
              begin
                    lNrClaFim  :=  '';
                    lNrPosPon  :=  Pos('.', gDsMasLim);

                    for x := 1 to lNrPosPon do
                    begin
                          lNrClaFim  :=  lNrClaFim + '9';
                    end;

                    lNrClaFim  :=  lNrClaFim + '.';

                    lNrClaFim := ' and B002.NrClassi  <= ' + ' "' + lNrClaFim + '" ';
              end else
              begin
                    lNrClaFim := ' and B002.NrClassi  <= ' + ' "' +
                                       lCdsAuxili.FieldByName('DsMasFim').AsString + '" ';
              end;

          finally
              FreeAndNil(lCdsAuxili);
          end;

          try
              gNmEmpres := pNmEmpres;
              gRelAsc.NovoRelatorioInvisivel(pBrvDicion.UserCode, pNmEmpres, 'RLC0007',
                                                               'Livro Razao');

              lDsParam := ProcessarParametros(pCdsParams)   +
                          '<%NrClaIni%>;' + lNrClaIni + #13 +
                          '<%NrClaFim%>;' + lNrClaFim + #13;

              gCdsRelato      := TClientDataSet.Create(Self);
              gCdsRelato.Data := pBrvDicion.RetornaDadosSqlCadastro(90,
                                                                     lDsParam, 'RLC0007');

              while not gCdsRelato.Eof do
              begin
                    if not gCdsRelato.Bof then
                    begin
                          ImprimeComplementoCabecalho;
                    end;

                    ImprimirDetalhesRelatorio(gDtInicia, lDtFinal, gCdCenCus, lCdEmpres,
                                              gCdsRelato.FieldByName('NrConta').AsString,
                                              gSnEmpDat, lSnSalZer, lSnTotDat);

                    gCdsRelato.Next;
              end;

              Result := gRelAsc.RetornaTextoGerado;
          except
              on E : Exception do
              begin
                     Result := 'Erro ao gerar relatório: ' + #13 + E.Message;
              end;
          end;
      finally
          FreeAndNil(gRelAsc);
          FreeAndNil(gCdsRelato);
      end;
end;

procedure TRlc0007.ImprimirDetalhesRelatorio(pDtInicia : TDateTime; pDtFinal  : TDateTime;
                                             pCdCenCus : Integer;   pCdEmpres : String;
                                             pNrConta  : String;    pSnEmpDat : Boolean;
                                             pSnSalZer : Boolean;   pSnTotDat : Boolean);
var  lSdDebito  : Real;
     lSdCredit  : Real;
     lVrCredit  : Real;
     lVrDebito  : Real;
     lDcAnteri  : String;
     lDsCentro  : String;

     lDsComple  : TStrings;

     lDsParam   : String;
     lCdsDetalh : TClientDataSet;

     lSdAnteri  : Real;
     lSdAuxili  : Real;

     lDsLinha   : String;

     lNrIndice  : Integer;
     lCdEmpLan  : Integer;
     lVrCreDat  : Real;
     lVrDebDat  : Real;
     lDtAnteri  : TDate;

     lDtDia     : String;
begin
      lSdDebito := 0;
      lSdCredit := 0;
      lVrCredit := 0;
      lVrDebito := 0;
      lDcAnteri := ' ';
      lDsCentro := '';

      if pCdCenCus <> 0 then
      begin
            lDsCentro :=
                    ' and B004.NrLancto in ( ' +
                    '                 Select B008.NrLancto ' +
                    '                 From B008 B008 ' +
                    '                 Where B008.CdEmpres = B004.CdEmpres and ' +
                    '                       B008.DtLancto = B004.DtLancto and ' +
                    '                       B008.NrLancto = B004.NrLancto and ' +
                    '                       B008.CdCenCus = ' + IntToStr(pCdCenCus) + ')';
      end;
       
      try
          lDsComple  := TStringList.Create;

          gDtMes     := IntToStr(MonthOf(pDtInicia));
          gDtAno     := IntToStr(YearOf(pDtInicia));

          lDsParam   := '<%DtMes%>;'    + gDtMes    + #13 +
                        '<%DtAno%>;'    + gDtAno    + #13 +
                        '<%NrConta%>;'  + pNrConta  + #13 +
                        '<%CdEmpres%>;' + pCdEmpres + #13 +
                        '<%DtLanIni%>;' + FormatDateTime(
                                        DmDicion.BrvDicionario.DateSql, pDtInicia) + #13 +
                        '<%DtLanFim%>;' + FormatDateTime(
                                        DmDicion.BrvDicionario.DateSql, pDtFinal)  + #13 +
                        '<%CdCenCus%>;' + lDsCentro + #13;

          if pSnEmpDat then
          begin
                lDsParam := lDsParam +
                       '<%DsOrder%>;Order By B004.CdEmpres, B004.DtLancto, B004.NrLancto';
          end else
          begin
                lDsParam := lDsParam +
                       '<%DsOrder%>;Order By B004.DtLancto, B004.CdEmpres, B004.NrLancto';
          end;

          lCdsDetalh := TClientDataSet.Create(Self);
          lCdsDetalh.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(91,
                                                                     lDsParam, 'RLC0007');

          if (not lCdsDetalh.IsEmpty) or (pSnSalZer) then
          begin
                lSdAnteri := SaldoContaCotabil(pCdEmpres, pNrConta, pDtInicia);
                lSdAuxili := lSdAnteri;
                VerificarDebitoCredito(lSdAuxili, lDcAnteri);

                lDsLinha := '  S A L D O    A N T E R I O R ...........:     '          +
                            '                 '                                         +
                           gRelAsc.FormatarNumero(FormatFloat('#,###,###,##0.00',
                                                                    lSdAuxili),16, False);

                gRelAsc.NovaLinha(lDsLinha  + lDcAnteri);
                gRelAsc.NovaLinha(' ');

                lCdEmpLan := 0;
                lVrCreDat := 0;
                lVrDebDat := 0;
                lDtAnteri := lCdsDetalh.FieldByName('DtLancto').AsDateTime;

               {=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                =-=-=-=-=-=- PROCESSANDO LANÇAMENTOS -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=}
                while not lCdsDetalh.EOF do
                begin
                      lDtDia := IntToStr(DayOf(lCdsDetalh.FieldByName('DtLancto').AsDateTime));

                      if pSnEmpDat then
                      begin
                            if lCdEmpLan <>
                               lCdsDetalh.FieldByName('CdEmpres').AsInteger then
                            begin
                                  if lCdEmpLan <> 0 then
                                  begin
                                        gRelAsc.NovaLinha('         Sub-total empresa ' +
                                                gRelAsc.FormatarNumero(
                                                           IntToStr(lCdEmpLan), 3, False)+
                                                StringOfChar(' ', 5) +
                                                gRelAsc.FormatarNumero(
                                                FormatFloat('#,###,##0.00',
                                                                  lVrDebito), 14, False) +
                                                '  ' +
                                                gRelAsc.FormatarNumero(
                                                FormatFloat('#,###,##0.00', lVrCredit),
                                                                              14, False));
                                        gRelAsc.NovaLinha(' ');

                                        lVrCredit := 0;
                                        lVrDebito := 0;
                                  end;

                                  gRelAsc.NovaLinha('         Data lançamento ' +
                                          lCdsDetalh.FieldByName('DtLancto').AsString +
                                          '   Empresa ' +
                                          lCdsDetalh.FieldByName('CdEmpres').AsString +
                                          ' - ' +
                                          lCdsDetalh.FieldByName('RsEmpres').AsString);
                                  lCdEmpLan :=
                                             lCdsDetalh.FieldByName('CdEmpres').AsInteger;
                            end;
                      end;

                      if  lCdsDetalh.FieldByName('NrConDeb').AsString = pNrConta then
                      begin
                            lDsLinha := gRelAsc.FormatarStringSemAcento(lDtDia,  2)      +
                               ' ' +
                               gRelAsc.FormatarStringSemAcento(
                                  lCdsDetalh.FieldByName('NrDocLan').AsString, 10) + ' ' +
                               gRelAsc.FormatarStringSemAcento(
                                  lCdsDetalh.FieldByName('NrClaCre').AsString,  20) +
                                                                                    ' '  +
                               gRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                     lCdsDetalh.FieldByName('VrLancto').AsFloat), 14, False)
                                                                                   + ' ' +
                               gRelAsc.FormatarStringSemAcento(' ',  15);

                            lSdAnteri := lSdAnteri -
                                         lCdsDetalh.FieldByName('VrLancto').AsFloat;

                            lSdDebito := lSdDebito +
                                         lCdsDetalh.FieldByName('VrLancto').AsFloat;

                            lVrDebito := lVrDebito +
                                         lCdsDetalh.FieldByName('VrLancto').AsFloat;

                            lVrDebDat := lVrDebDat +
                                         lCdsDetalh.FieldByName('VrLancto').AsFloat;
                      end else
                      begin
                            lDsLinha :=
                                gRelAsc.FormatarStringSemAcento(lDtDia,  2) + ' ' +
                                gRelAsc.FormatarStringSemAcento(
                                    lCdsDetalh.FieldByName('NrDocLan').AsString, 10) +
                                                                                     ' ' +
                                gRelAsc.FormatarStringSemAcento(
                                    lCdsDetalh.FieldByName('NrClaDeb').AsString,  20)+ ' ' +
                                gRelAsc.FormatarStringSemAcento(' ',  15)            + ' ' +
                                gRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                    lCdsDetalh.FieldByName('VrLancto').AsFloat), 14, False);

                            lSdAnteri := lSdAnteri  +
                                         lCdsDetalh.FieldByName('VrLancto').AsFloat;

                            lSdCredit := lSdCredit  +
                                         lCdsDetalh.FieldByName('VrLancto').AsFloat;

                            lVrCredit := lVrCredit +
                                         lCdsDetalh.FieldByName('VrLancto').AsFloat;

                            lVrCreDat := lVrCreDat +
                                         lCdsDetalh.FieldByName('VrLancto').AsFloat;
                      end;

                      lSdAuxili := lSdAnteri;
                      VerificarDebitoCredito(lSdAuxili, lDcAnteri);

                      lDsLinha  := lDsLinha +
                             gRelAsc.FormatarNumero(
                                  FormatFloat('#,###,###,##0.00', lSdAuxili), 16, False) +
                                                                         lDcAnteri + ' ' +
                             gRelAsc.FormatarStringSemAcento(
                                  lCdsDetalh.FieldByName('DsHistor').AsString, 20)+ ' ';

                      lDsComple.Text := gRelAsc.Justificar(
                                         lCdsDetalh.FieldByName('DsComHis').AsString, 28);

                      if lDsComple.Count > 1 then
                      begin
                            for lNrIndice := 0 to lDsComple.Count -1 do
                            begin
                                  gRelAsc.NovaLinha(lDsLinha + lDsComple[lNrIndice]);
                                  lDsLinha := StringOfChar(' ', 104);
                            end;
                      end else
                      begin
                            if lDsComple.Count = 1 then
                            begin
                                  gRelAsc.NovaLinha(lDsLinha + lDsComple[0]);
                            end else
                            begin
                                  gRelAsc.NovaLinha(lDsLinha);
                            end;
                      end;

                      lCdsDetalh.Next;

                      //----   Totalizar por dia

                      if (pSnTotDat) and
                        ((lCdsDetalh.Eof) or
                         (lDtAnteri <> lCdsDetalh.FieldByName('DtLancto').AsDateTime)) then
                      begin
                            gRelAsc.NovaLinha(
                                    '         Sub-Total ' + DateToStr(lDtAnteri) +
                                    StringOfChar(' ', 6) +
                                    gRelAsc.FormatarNumero(
                                    FormatFloat('#,###,##0.00', lVrDebDat), 14, False) +
                                    '  ' +
                                    gRelAsc.FormatarNumero(
                                    FormatFloat('#,###,##0.00', lVrCreDat), 14, False));
                            gRelAsc.NovaLinha(' ');

                            lVrCreDat := 0;
                            lVrDebDat := 0;
                            lDtAnteri := lCdsDetalh.FieldByName('DtLancto').AsDateTime;
                      end;
                end;

                if (pSnEmpDat) and (lCdEmpLan <> 0) then
                begin
                      gRelAsc.NovaLinha('         Sub-total empresa ' +
                               gRelAsc.FormatarNumero(IntToStr(lCdEmpLan), 3, False) +
                               StringOfChar(' ', 4) +
                               gRelAsc.FormatarNumero(
                                      FormatFloat('#,###,##0.00', lVrDebito), 14, False) +
                               '  ' +
                               gRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                                                  lVrCredit), 14, False));
                end;

               {=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                =-=-=-=-=-=- IMPRIMINDO SALDO ATUAL =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=}
                lSdAuxili :=  lSdAnteri;
                VerificarDebitoCredito(lSdAuxili, lDcAnteri);

                gRelAsc.NovaLinha(StringOfChar('-', 132));

                lDsLinha := 'S A L D O    A T U A L ..........:' +
                      gRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', lSdDebito), 15,
                                                                            False) + ' ' +
                      gRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', lSdCredit), 15,
                                                                            False) + ' ' +
                      gRelAsc.FormatarNumero(FormatFloat('#,###,##0.00', lSdAuxili), 15,
                                                                                  False) +
                      lDcAnteri;

                gRelAsc.NovaLinha(' ');
                gRelAsc.NovaLinha(lDsLinha);
                gRelAsc.NovaLinha(' ');
                gRelAsc.NovaLinha(StringOfChar('-', 132));
          end;
      finally
          FreeAndNil(lDsComple);
          FreeAndNil(lCdsDetalh);
      end;

end;

procedure TRlc0007.VerificarDebitoCredito(var pVrSaldo : Real; var pDcSaldo : String);
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

function TRlc0007.SaldoContaCotabil(pCdEmpres : String;   pNrConta  : String;
                                    pDtRefere : TDate) : Real;
var lDtDia    : Word;
    lDtMes    : Word;
    lDtAno    : Word;
    lDtSaldo  : TDate;
    lVrLanCre : Real;
    lVrLanDeb : Real;
    lDsParAux : AnsiString;

    lCdsSaldo : TClientDataSet;
    lDsParam  : String;
begin
      lDtSaldo  := 1;
      Result    := 0;
      DecodeDate(pDtRefere, lDtAno, lDtMes, lDtDia);

      lDsParAux := StringReplace(FloatToStr(lDtAno + lDtMes * 0.01),
                                                                ',', '.', [rfReplaceAll]);
      lCdsSaldo := TClientDataSet.Create(Self);

      try
          lDsParam := '<%CdEmpres%>;' + pCdEmpres + #13 +
                      '<%NrConta%>;'  + pNrConta  + #13 +
                      '<%DtMes%>;'    + lDsParAux;

          lCdsSaldo.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(92,
                                                                     lDsParam, 'RLC0007');

          if not lCdsSaldo.Eof then
          begin
                Result := lCdsSaldo.FieldByName('VrAbertu').AsFloat;
                lDtSaldo := EncodeDate(lCdsSaldo.FieldByName('DtAno').AsInteger,
                                       lCdsSaldo.FieldByName('DtMes').AsInteger, 1);
          end;

          lVrLanCre := SomaValorLancamentos(pCdEmpres, pNrConta,
                                                       lDtSaldo, pDtRefere, 'NrConCre');
          lVrLanDeb := SomaValorLancamentos(pCdEmpres, pNrConta,
                                                       lDtSaldo, pDtRefere, 'NrConDeb');

          Result   := Result + lVrLanCre - lVrLanDeb;

      finally
          FreeAndNil(lCdsSaldo);
      end;
end;

function TRlc0007.SomaValorLancamentos(pCdEmpres : String; pNrConta  : String;
                                       pDtLanIni : TDate;  pDtLanFim : TDate;
                                       pDsAtribu : String) : Real;
var lCdsLancto : TClientDataSet;
    lDsParam   : String;
begin
      Result := 0;

      try
          lCdsLancto := TClientDataSet.Create(Self);

          lDsParam   := '<%CdEmpres%>;' + pCdEmpres + #13 +
                        '<%NrConta%>;'  + pNrConta  + #13 +
                        '<%DsAtrib%>;'  + pDsAtribu  + #13 +
                        '<%DtLanIni%>;' +
                         FormatDateTime(DmDicion.BrvDicionario.DateSql, pDtLanIni) + #13 +
                        '<%DtLanFim%>;' +
                         FormatDateTime(DmDicion.BrvDicionario.DateSql, pDtLanFim);

          lCdsLancto.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(93,
                                                                     lDsParam, 'RLC0007');

          if not lCdsLancto.Eof then
          begin
                Result := lCdsLancto.FieldByName('VrLancto').AsFloat;
          end;
      finally
          FreeAndNil(lCdsLancto);
      end;
end;

Initialization
    RegisterClass(TRlc0007);

Finalization
    UnRegisterClass(TRlc0007);


end.

