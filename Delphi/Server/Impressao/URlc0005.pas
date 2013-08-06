unit URlc0005;

interface

uses Classes, URlc0000, DbClient, SysUtils, BrvDicionario, BrvClientDataSet,
     ComCtrls, Forms, BrvRelAsc;


type
  TRlc0005 = class(TRlc0000)
  private
    gRelAsc  : TBrvRelAsc;
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure   CompletaCabecalho(Sender : TObject);

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

procedure TRlc0005.CompletaCabecalho(Sender: TObject);
begin
      gRelAsc.NovaLinha('Data       Lancto Conta de Debito                              '+
                       '   Conta de Credito                               '              +
                       'Valor do Lancamento');
      gRelAsc.NovaLinha('           Historico                                           '+
                       '   Complemento');
      gRelAsc.NovaLinha(StringOfChar('-', 132));
end;

constructor TRlc0005.Create(AOwner: TComponent);
begin
     inherited;
end;

destructor TRlc0005.Destroy;
begin
     inherited;
end;

function TRlc0005.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String;
                                 pData      : OleVariant) : String;
var lCdsRelato : TClientDataSet;
    lDsLinha   : String;
    lDsComHis  : TStrings;
    lNrLinha   : Integer;
begin
      Result    := ' ';

      try
          lDsComHis := TStringList.Create;
          gRelAsc := TBrvRelAsc.Create(Self);
          gRelAsc.CompletaCabecalho := CompletaCabecalho;
          gRelAsc.ColunasPorLinha   := Rel132Col;

          try
              gRelAsc.NovoRelatorioInvisivel(pBrvDicion.UserCode, pNmEmpres, 'RLC0005',
                                                               'Lancamentos contabeis');

              lCdsRelato      := TClientDataSet.Create(Self);
              lCdsRelato.Data := pBrvDicion.RetornaDadosSqlCadastro(88,
                                              ProcessarParametros(pCdsParams), 'RLC0005');

              while not lCdsRelato.Eof do
              begin
                    gRelAsc.NovaLinha(
                            gRelAsc.FormatarStringSemAcento(
                                  lCdsRelato.FieldByName('DtLancto').AsString, 10)       +
                            ' ' +
                            gRelAsc.FormatarNumero(
                                  lCdsRelato.FieldByName('NrLancto').AsString,  6, True) +
                            ' ' +
                            gRelAsc.FormatarNumero(
                                  lCdsRelato.FieldByName('NrConDeb').AsString,  6, True) +
                            ' ' +
                            gRelAsc.FormatarStringSemAcento(
                                  lCdsRelato.FieldByName('NmConDeb').AsString, 40)       +
                            ' ' +
                            gRelAsc.FormatarNumero(
                                  lCdsRelato.FieldByName('NrConCre').AsString,  6, True) +
                            ' ' +
                            gRelAsc.FormatarStringSemAcento(
                                  lCdsRelato.FieldByName('NmConCre').AsString, 40)       +
                            ' ' +
                            gRelAsc.FormatarNumero(FormatFloat('###,###,###,##0.00',
                                 lCdsRelato.FieldByName('VrLancto').AsFloat), 18, False));

                    // =-=-=-= Linha do Histórico
                    lDsLinha := gRelAsc.FormatarStringSemAcento(' ', 10)            + ' ' +
                                gRelAsc.FormatarNumero(
                                   lCdsRelato.FieldByName('CdHistor').AsString, 3, True) +
                                ' ' +
                                gRelAsc.FormatarStringSemAcento(
                                   lCdsRelato.FieldByName('DsHistor').AsString, 50) + ' ';

                    lDsComHis.Text := lCdsRelato.FieldByName('DsComHis').AsString;

                    for lNrLinha := 0 to lDsComHis.Count -1 do
                    begin
                          lDsLinha  :=  lDsLinha + lDsComHis.Strings[lNrLinha];

                          gRelAsc.NovaLinha(lDsLinha);

                          lDsLinha   := gRelAsc.FormatarStringSemAcento(' ', 66);
                    end;

                    gRelAsc.NovaLinha(' ');

                    lCdsRelato.Next;
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

Initialization
    RegisterClass(TRlc0005);

Finalization
    UnRegisterClass(TRlc0005);


end.

