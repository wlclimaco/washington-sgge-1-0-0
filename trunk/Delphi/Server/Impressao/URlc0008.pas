unit URlc0008;

interface

uses Classes, URlc0000, DbClient, SysUtils, BrvDicionario, BrvClientDataSet,
     ComCtrls, Forms, BrvRelAsc, BrvExport;


type
  TRlc0008 = class(TRlc0000)
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

procedure TRlc0008.CompletaCabecalho(Sender: TObject);
begin
      gRelAsc.NovaLinha(
              'Classificacao.Conta                                                 ' +
              '          Saldo Anterior     Debito    Credito       Saldo Atual');
      gRelAsc.NovaLinha(StringOfChar('-', 132));
end;

constructor TRlc0008.Create(AOwner: TComponent);
begin
     inherited;
end;

destructor TRlc0008.Destroy;
begin
     inherited;
end;

function TRlc0008.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String;
                                 pData      : OleVariant) : String;
var lCdsAuxili : TClientDataSet;
    lTrvPlano  : TTreeView;
    lFrmPlano  : TForm;
    lBrvExport : TBrvExport;

    lDtMesAno  : String;
    lDsPlano   : String;
    lNrNode    : Integer;
begin
      Result    := ' ';

      try
          gRelAsc := TBrvRelAsc.Create(Self);
          gRelAsc.CompletaCabecalho := CompletaCabecalho;
          gRelAsc.ColunasPorLinha   := Rel132Col;

          lFrmPlano := TForm.Create(Self);
          lFrmPlano.Visible := False;
          lTrvPlano := TTreeView.Create(Self);
          lTrvPlano.Parent := lFrmPlano;
          lTrvPlano.Items.Clear;

          lBrvExport := TBrvExport.Create(self);

          try
              lCdsAuxili      := TClientDataSet.Create(Self);
              lCdsAuxili.Data := pData;
              gRelAsc.PaginaInicial := lCdsAuxili.FieldByName('NrPagIni').AsInteger;
              lDsPlano              := lCdsAuxili.FieldByName('DsPlano').AsString;
              lDtMesAno             := lCdsAuxili.FieldByName('DtMesAno').AsString;
          finally
              FreeAndNil(lCdsAuxili);
          end;

          try
              gRelAsc.NovoRelatorioInvisivel(pBrvDicion.UserCode, pNmEmpres, 'RLC0008',
                                                               'Balancete ' + lDtMesAno);

              lBrvExport.MontarTreeView(lDsPlano, lTrvPlano);

              for lNrNode := 0 to lTrvPlano.Items.Count -1 do
              begin
                    gRelAsc.NovaLinha(lTrvPlano.Items[lNrNode].Text);
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
          FreeAndNil(lTrvPlano);
          FreeAndNil(lFrmPlano);
          FreeAndNil(lBrvExport);
      end;
end;

function TRlc0008.NivelAtual(pNrClassi : String) : String;
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

procedure TRlc0008.ImprimeTotalDiaDiario(pVrTtDia : real;    pDtDia   : TDateTime;
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

procedure TRlc0008.ImprimeTotalMesDiario(pVrTtMes : real; pDtDiaMes : TDate);
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
    RegisterClass(TRlc0008);

Finalization
    UnRegisterClass(TRlc0008);


end.

