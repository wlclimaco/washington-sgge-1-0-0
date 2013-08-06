unit URlc0006;

interface

uses Classes, URlc0000, DbClient, SysUtils, BrvDicionario, BrvClientDataSet,
     ComCtrls, Forms, BrvRelAsc;


type
  TRlc0006 = class(TRlc0000)
  private
    gRelAsc  : TBrvRelAsc;
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure   CompletaCabecalho(Sender : TObject);
    procedure ImprimeTotalDiaDiario(pVrTtDia: real; pDtDia: TDateTime; pSnEOF: Boolean);
    procedure ImprimeTotalMesDiario(pVrTtMes: real; pDtDiaMes : TDate);
    function NivelAtual(pNrClassi: String): String;

    { Private declarations }
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

procedure TRlc0006.CompletaCabecalho(Sender: TObject);
begin
      gRelAsc.NovaLinha('Dia    Lancamento Classificacao        Conta                 ' +
                        '                                                Debito       ' +
                        '   Credito');
      gRelAsc.NovaLinha(StringOfChar('-', 132));
end;

constructor TRlc0006.Create(AOwner: TComponent);
begin
     inherited;
end;

destructor TRlc0006.Destroy;
begin
     inherited;
end;

function TRlc0006.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String;
                                 pData      : OleVariant) : String;
var lCdsRelato : TClientDataSet;
    lCdsAuxili : TClientDataSet;
    lDsLinha   : String;
    lDsComHis  : TStrings;
    lNrLinha   : Integer;

    lVrTtDia   : Real;
    lVrTtMes   : Real;
    lDtAnteri  : TDate;
begin
      Result    := ' ';

      lVrTtDia  := 0;
      lVrTtMes  := 0;

      try
          lDsComHis := TStringList.Create;
          gRelAsc := TBrvRelAsc.Create(Self);
          gRelAsc.CompletaCabecalho := CompletaCabecalho;
          gRelAsc.ColunasPorLinha   := Rel132Col;

          try
              lCdsAuxili      := TClientDataSet.Create(Self);
              lCdsAuxili.Data := pData;
              gRelAsc.PaginaInicial := lCdsAuxili.FieldByName('NrPagIni').AsInteger;
          finally
              FreeAndNil(lCdsAuxili);
          end;

          try
              gRelAsc.NovoRelatorioInvisivel(pBrvDicion.UserCode, pNmEmpres, 'RLC0006',
                                                               'Livro Diario');

              lCdsRelato      := TClientDataSet.Create(Self);
              lCdsRelato.Data := pBrvDicion.RetornaDadosSqlCadastro(89,
                                              ProcessarParametros(pCdsParams), 'RLC0006');

              lDtAnteri := lCdsRelato.FieldByName('DtLancto').AsDateTime;

              while not lCdsRelato.Eof do
              begin
                    gRelAsc.NovaLinha(
                           gRelAsc.FormatarStringSemAcento(
                               lCdsRelato.FieldByName('DtLancto').AsString, 10)          +
                                                                                     ' ' +
                           gRelAsc.FormatarNumero(
                               lCdsRelato.FieldByName('NrLancto').AsString,  6, True)    +
                                                                                     ' ' +
                           gRelAsc.FormatarStringSemAcento(NivelAtual(
                               lCdsRelato.FieldByName('NrClaDeb').AsString), 20)         +
                                                                                     ' ' +
                           gRelAsc.FormatarNumero(
                               lCdsRelato.FieldByName('NrConDeb').AsString,  6, True)    +
                                                                                     ' ' +
                           gRelAsc.FormatarStringSemAcento(
                               lCdsRelato.FieldByName('NmConDeb').AsString, 40)          +
                                                                        '              ' +
                           gRelAsc.FormatarNumero(FormatFloat('###,###,###,##0.00',
                               lCdsRelato.FieldByName('VrLancto').AsFloat), 15, False));

                    gRelAsc.NovaLinha(
                           gRelAsc.FormatarStringSemAcento(' ', 17)                      +
                                                                                     ' ' +
                           gRelAsc.FormatarStringSemAcento(NivelAtual(
                                 lCdsRelato.FieldByName('NrClaCre').AsString), 20)       +
                                                                                     ' ' +
                           gRelAsc.FormatarNumero(
                                 lCdsRelato.FieldByName('NrConCre').AsString,  6, True)  +
                                                                                     ' ' +
                           gRelAsc.FormatarStringSemAcento(
                                 lCdsRelato.FieldByName('NmConCre').AsString, 40)        +
                                                       '                               ' +
                           gRelAsc.FormatarNumero(FormatFloat('###,###,###,##0.00',
                                 lCdsRelato.FieldByName('VrLancto').AsFloat), 15, False));

                    // =-=-=-= Linha do Histórico
                    lDsLinha := gRelAsc.FormatarStringSemAcento(' ', 21)           + ' ' +
                                gRelAsc.FormatarNumero(
                                   lCdsRelato.FieldByName('CdHistor').AsString, 3, True) +
                                ' ' +
                                gRelAsc.FormatarStringSemAcento(
                                   lCdsRelato.FieldByName('DsHistor').AsString, 40) + ' ';

                    lDsComHis.Text := lCdsRelato.FieldByName('DsComHis').AsString;

                    for lNrLinha := 0 to lDsComHis.Count -1 do
                    begin
                          lDsLinha  :=  lDsLinha + lDsComHis.Strings[lNrLinha];

                          gRelAsc.NovaLinha(lDsLinha);

                          lDsLinha   := gRelAsc.FormatarStringSemAcento(' ', 67);
                    end;

                    gRelAsc.NovaLinha(' ');

                    lVrTtDia := lVrTtDia + lCdsRelato.FieldByName('VrLancto').AsFloat;
                    lVrTtMes := lVrTtMes + lCdsRelato.FieldByName('VrLancto').AsFloat;

                    lCdsRelato.Next;

                    if  lCdsRelato.EOF then
                    begin
                          ImprimeTotalDiaDiario(lVrTtDia, lDtAnteri, True);
                          ImprimeTotalMesDiario(lVrTtMes, lDtAnteri);
                    end else
                    begin
                          if lCdsRelato.FieldByName('DtLancto').AsDateTime <>
                                                                           lDtAnteri then
                          begin
                                ImprimeTotalDiaDiario(lVrTtDia, lDtAnteri, False);
                                lVrTtDia  := 0;
                                lDtAnteri := lCdsRelato.FieldByName('DtLancto').AsDateTime;
                          end;
                    end;

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
          FreeAndNil(lCdsRelato);
          FreeAndNil(lDsComHis);
      end;
end;

function TRlc0006.NivelAtual(pNrClassi : String) : String;
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
                        result := result + lNrClaAtu + '.';
                  end
            end else
            begin
                  lSnFim := True;

                  if  StrToInt(pNrClassi) <> 0 then
                  begin
                        result := result + pNrClassi + '.';
                  end
            end;
      end;

      Result := Result + ' ';
end;

procedure TRlc0006.ImprimeTotalDiaDiario(pVrTtDia : real;    pDtDia   : TDateTime;
                                         pSnEOF   : Boolean);
var  lNrCaract : Byte;
     lDsLinha  : String;
begin
      gRelAsc.NovaLinha('                                                   ' +
                       '                  TOTAL DO DIA ' +
      gRelAsc.FormatarStringSemAcento(DateToStr(pDtDia), 10) + ' .....: ' +
              gRelAsc.FormatarNumero(
                      FormatFloat('###,###,###,##0.00', pVrTtDia), 15, False) +
                                                                                    '  ' +
              gRelAsc.FormatarNumero(
                      FormatFloat('###,###,###,##0.00', pVrTtDia), 15, False));

      if  not pSnEOF then
      begin
            gRelAsc.NovaLinha('=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-' +
                             '=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-' +
                             '=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-');
      end else
      begin
            lDsLinha := '-----------------------------------------';

            for lNrCaract := 1 to gRelAsc.Linha do
            begin
                  lDsLinha := lDsLinha + '-';
            end;

            gRelAsc.NovaLinha(lDsLinha);

            for lNrCaract := gRelAsc.Linha to 57 do
            begin
                  gRelAsc.NovaLinha(
                              gRelAsc.FormatarStringSemAcento(' ', 41 + lNrCaract) + '\');
            end;

            gRelAsc.NovaLinha(gRelAsc.FormatarStringSemAcento(' ', 99) +
                                                     '=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=');
      end;
end;

procedure TRlc0006.ImprimeTotalMesDiario(pVrTtMes : real; pDtDiaMes : TDate);
var lDtDia : Word;
    lDtMes : Word;
    lDtAno : Word;
begin
      DecodeDate(pDtDiaMes, lDtAno, lDtMes, lDtDia);

      gRelAsc.NovaLinha('                                                '               +
                        '                    TOTAL DO PERIODO '                          +
      gRelAsc.FormatarNumero(IntToStr(lDtMes), 2, True) + DateSeparator                  +
      gRelAsc.FormatarNumero(IntToStr(lDtAno), 4, True) + ' .....: '                     +
      gRelAsc.FormatarNumero(FormatFloat('###,###,###,##0.00', pVrTtMes), 15, False)
                                                                                  + '  ' +
      gRelAsc.FormatarNumero(FormatFloat('###,###,###,##0.00', pVrTtMes), 15, False));
end;

Initialization
    RegisterClass(TRlc0006);

Finalization
    UnRegisterClass(TRlc0006);


end.

