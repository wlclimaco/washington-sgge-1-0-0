unit URlc0009;

interface

uses Classes, URlc0000, DbClient, BrvDicionario, SysUtils, BrvRelASC;

type
  TRlc0009 = class(TRlc0000)
  private
    { Private declarations }
    BrvRelAsc    : TBrvRelAsc;
    procedure CompletaCabecalho(Sender: TObject);
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    function Parametros(pData : OleVariant) : String;
    function GerarRelatorio(pCdsParams : TClientDataSet;
                            pBrvDicion : TBrvDicionario;
                            pNmEmpres  : String;
                            pData     : OleVariant): String; Override;
  end;

implementation

{ TRelPadrao }

constructor TRlc0009.Create(AOwner: TComponent);
begin
      inherited;
end;

destructor TRlc0009.Destroy;
begin
      inherited;
end;

function TRlc0009.Parametros(pData : OleVariant) : String;
var lCdsParams: TClientDataSet;
begin
      Result := '';
      try
          lCdsParams := TClientDataSet.Create(Self);
          lCdsParams.Data := pData;
          lCdsParams.First;

          while not lCdsParams.Eof do
          begin
                Result := Result + '<%' + lCdsParams.FieldByName('NmParam').AsString + '%>;' +
                                          lCdsParams.FieldByName('DsParam').AsString + #13;
                lCdsParams.Next;
          end;
      finally
          FreeAndNil(lCdsParams);
      end;
end;

procedure TRlc0009.CompletaCabecalho(Sender: TObject);
begin
      BrvRelAsc.NovaLinha(
               'Cód.Bem  Produto Descrição                                          Dt. Lancto  ' +
               'Parcela  Vr. Deprec.    Vr. Parcela    A Depreciar');
      BrvRelAsc.NovaLinha(StringOfChar('-', 132));
end;

function TRlc0009.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String;
                                 pData      : OleVariant) : String;
var lCdsRelato : TClientDataSet;
    lVrTotParc : Extended;
begin
      Result := ' ';

      try
          BrvRelAsc := TBrvRelAsc.Create(Self);

          try
              BrvRelAsc.PaginaInicial := 1;
              BrvRelAsc.Pagina := 1;
              BrvRelAsc.ColunasPorLinha := Rel132Col;
              BrvRelAsc.CompletaCabecalho := CompletaCabecalho;
              BrvRelAsc.NovoRelatorioInvisivel(pBrvDicion.UserCode, pNmEmpres, 'RLC0009',
                                                           'Depreciação de um período');
              lCdsRelato := TClientDataSet.Create(Self);

              lCdsRelato.Data := pBrvDicion.RetornaDadosSqlCadastro(168,
                                              Parametros(pData), 'RLC0009');

              lVrTotParc := 0;

              lCdsRelato.First;
              while not lCdsRelato.eof do
              begin
                    BrvRelAsc.NovaLinha(
                      BrvRelAsc.FormatarNumero(
                                      lCdsRelato.FieldByName('CdBem'   ).AsString, 6, False) + ' ' +
                      BrvRelAsc.FormatarNumero(
                                      lCdsRelato.FieldByName('CdProdut').AsString, 8, False) + '  '+
                      BrvRelAsc.FormatarStringSemAcento(
                                            lCdsRelato.FieldByName('DsProdut').AsString, 50) + ' ' +
                      BrvRelAsc.FormatarStringSemAcento(
                                            lCdsRelato.FieldByName('dtlancto').AsString, 10) + ' ' +
                      BrvRelAsc.FormatarNumero(
                                      lCdsRelato.FieldByName('NrParcel').AsString, 3, False) +
                                      '    ' +
                      BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                lCdsRelato.FieldByName('VRBEM').AsFloat), 14, False) + ' ' +
                      BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                lCdsRelato.FieldByName('VrDeprec').AsFloat), 14, False) + ' ' +
                      BrvRelAsc.FormatarNumero(FormatFloat('#,###,##0.00',
                                lCdsRelato.FieldByName('VrADepre').AsFloat), 14, False));

                      lVrTotParc := lVrTotParc + lCdsRelato.FieldByName('VrDeprec').AsFloat;
                    lCdsRelato.Next;
              end;

              BrvRelAsc.NovaLinha('');
              BrvRelAsc.NovaLinha(StringOfChar('-', 132));
              BrvRelAsc.NovaLinha(StringOfChar(' ', 54) +
                                  'TOTAL DO PERIODO: ' +
                                  BrvRelAsc.FormatarNumero(
                                         FormatFloat('#,###,##0.00', lVrTotParc), 14, False));

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

Initialization
    RegisterClass(TRlc0009);

Finalization
    UnRegisterClass(TRlc0009);


end.
