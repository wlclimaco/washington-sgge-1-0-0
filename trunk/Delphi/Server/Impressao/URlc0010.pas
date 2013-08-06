unit URlc0010;

interface

uses Classes, URlc0000, DbClient, BrvDicionario, SysUtils, BrvRelASC;

type
  TRlc0010 = class(TRlc0000)
  private
    { Private declarations }
    gRelAsc  : TBrvRelAsc;
    function GerarPrestacaoDeContas(pDsParCar  : String; pNmEmpres : String;
                                    pBrvDicion : TBrvDicionario) : String;

  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    function    GerarRelatorio(pCdsParams : TClientDataSet;
                               pBrvDicion : TBrvDicionario;
                               pNmEmpres  : String;
                               pData     : OleVariant): String; Override;

    procedure ImprimeRecibo(pBrvDicion : TBrvDicionario; pCdCarga  : String; pNmCidade : String;
                            pVrRestit : Real;            pNmMotori : String; pDsDesCar : String;
                            pTpRecibo : String;          pNmEmpres : String; pCdEstado : String);

  end;

implementation

{ TRelPadrao }

constructor TRlc0010.Create(AOwner: TComponent);
begin
      inherited;
end;

destructor TRlc0010.Destroy;
begin
      inherited;
end;

function TRlc0010.GerarRelatorio(pCdsParams : TClientDataSet; pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String;         pData      : OleVariant) : String;
var lCdsRelato : TClientDataSet;
begin
      Result := GerarPrestacaoDeContas(ProcessarParametros(pCdsParams), pNmEmpres, pBrvDicion);
end;

function TRlc0010.GerarPrestacaoDeContas(pDsParCar  : String; pNmEmpres : String;
                                         pBrvDicion : TBrvDicionario) : String;
var lCPAceCar : TClientDataSet;
    lCdHisReb : Integer;
    lTxAuxili : TStrings;
    lTxJustif : TStrings;

    lVrReembo : Currency;
    lVrTotDes : Currency;
    lTpHisAnt : String;
begin
      try
          try
              gRelAsc    := TBrvRelAsc.Create(Self);

              lCPAceCar := TClientDataSet.Create(self);
              lCPAceCar.Data := pBrvDicion.RetornaDadosSqlCadastro(
                                                             211, pDsParCar, 'RLC0010');
              lVrTotDes := 0;
              lCdHisReb := pBrvDicion.ParametroDaEmpresa(30,0); //gerar do parametro
              lTxAuxili := TStringList.Create;

              gRelAsc.NovoRelatorioInvisivel(pBrvDicion.UserCode, pNmEmpres, 'RLC0010',
                                                               'Acerto de Carga');

              gRelAsc.LinhasFixas(2, 1, ' ');

              gRelAsc.NovaLinha(StringOfChar(' ', 18) +
                           'PRESTACAO DE CONTAS Nº ' + lCPAceCar.FieldByName('CdCarga').AsString +
                           ' - DATA '                + DateToStr(pBrvDicion.DataServer));

              gRelAsc.LinhasFixas(3, 1, ' ');

              lTxAuxili.add('Prestacao de contas correspondente ao adiantamento para ' +
                            'suprimento de viagens efetuado por '                      +
                             pNmEmpres                                + '.');

              gRelAsc.NovaLinha(gRelAsc.JustificarTexto(lTxAuxili.Text, 80));

              lCPAceCar.First;
              lTpHisAnt := 'D';

              gRelAsc.LinhasFixas(1, 1, ' ');

              while (not lCPAceCar.eof) do
              begin
                    if lCPAceCar.FieldByName('TpHistor').AsString  = 'D' then
                    begin
                          if lCPAceCar.FieldByName('CdHistor').AsInteger = lCdHisReb then
                          begin
                                lVrReembo := lVrReembo + lCPAceCar.FieldByName('VrLancto').AsFloat;
                          end;

                          gRelAsc.NovaLinha(
                                  gRelAsc.FormatarStringSemAcento(
                                         lCPAceCar.FieldByName('DsHistor').AsString, 60) + '   R$' +
                                  gRelAsc.FormatarNumero(
                                          FormatFloat('##,##0.00',
                                            lCPAceCar.FieldByName('VrLancto').AsFloat), 15, False));
                    end else
                    begin
                          gRelAsc.NovaLinha(
                                gRelAsc.FormatarStringSemAcento(
                                     lCPAceCar.FieldByName('DsHistor').AsString, 60) + '   R$' +
                                gRelAsc.FormatarNumero(
                                     FormatFloat('##,##0.00',
                                        lCPAceCar.FieldByName('VrLancto').AsFloat), 15, False));

                          lVrTotDes := lVrTotDes + lCPAceCar.FieldByName('VrLancto').AsFloat;
                    end;

                    lTpHisAnt := lCPAceCar.FieldByName('TpHistor').AsString;
                    lCPAceCar.Next;

                    if lTpHisAnt <> lCPAceCar.FieldByName('TpHistor').AsString then
                    begin
                          gRelAsc.LinhasFixas(1,  1, ' ');
                          gRelAsc.NovaLinha('Especie das despesas' +
                                             StringOfChar(' ', 55) + 'Valor');
                          gRelAsc.LinhasFixas(1,  1, ' ');
                    end;
              end;

              gRelAsc.LinhasFixas(2,  1, ' ');
              gRelAsc.NovaLinha(StringOfChar(' ', 65) + StringOfChar('-', 15));

              gRelAsc.NovaLinha(StringOfChar(' ', 44) + 'Total das despesas:  '      +
                        gRelAsc.FormatarNumero(FormatFloat('##,##0.00', lVrTotDes), 15, False));

              gRelAsc.LinhasFixas(2, 1, ' ');
              gRelAsc.NovaLinha('De acordo: ');
              gRelAsc.LinhasFixas(1, 1, ' ');
              gRelAsc.NovaLinha(StringOfChar('-', 40));
              gRelAsc.NovaLinha(lCPAceCar.FieldByName('NmMotori').AsString);
              gRelAsc.LinhasFixas(2,  1, ' ');
              gRelAsc.NovaLinha(StringOfChar('-', 40));
              gRelAsc.NovaLinha(pNmEmpres);
              gRelAsc.LinhasFixas(1, 1, ' ');
              gRelAsc.NovaLinha('Destino: ' + lCPAceCar.FieldByName('DsDesCar').AsString);

              gRelAsc.LinhasFixas(2,  1, ' ');

              if lVrReembo > 0 then
              begin
                    ImprimeRecibo(pBrvDicion,
                                  lCPAceCar.FieldByName('CdCarga').AsString,
                                  lCPAceCar.FieldByName('NmCidade').AsString,
                                  lVrReembo,
                                  lCPAceCar.FieldByName('NmMotori').AsString,
                                  lCPAceCar.FieldByName('DsDesCar').AsString, 'R',
                                  lCPAceCar.FieldByName('RsEmpres').AsString,
                                  lCPAceCar.FieldByName('CdEstado').AsString);
              end;

              Result := gRelAsc.RetornaTextoGerado;
          except
              on E : Exception do
              begin
                     Result := 'Erro ao gerar relatório: ' + #13 + E.Message;
              end;
          end;
      finally
          FreeAndNil(lCPAceCar);
          FreeAndNil(lTxAuxili);
      end;
end;

procedure TRlc0010.ImprimeRecibo(
                               pBrvDicion : TBrvDicionario; pCdCarga  : String; pNmCidade : String;
                               pVrRestit : Real;            pNmMotori : String; pDsDesCar : String;
                               pTpRecibo : String;          pNmEmpres : String; pCdEstado : String);
var lDsLinAux : String;
begin
      gRelAsc.LinhasFixas(3,  1, ' ');
      gRelAsc.LinhasFixas(1,  80, '=');
      gRelAsc.NovaLinha('Numero : '           + gRelAsc.FormatarNumero(pCdCarga, 8, False)  +
                        StringOfChar(' ', 41) + 'Recibo R$ '                                +
                        gRelAsc.FormatarNumero(FormatFloat('#.00', pVrRestit), 12, False));

      gRelAsc.LinhasFixas(1, 1, ' ');

      if pTpRecibo = 'R' then
      begin
            lDsLinAux := 'Recebi da ' + pNmEmpres +
                    ', a importancia supra citada a titulo de REEMBOLSO DE DESPESAS DE VIAGEM.';
      end else
      begin
            lDsLinAux := 'Recebi da ' + pNmEmpres +
                         ', a importancia supra citada a titulo de ADIANTAMENTO SALARIAL.';
      end;

      gRelAsc.NovaLinha(gRelAsc.JustificarTexto(lDsLinAux, 80));

      gRelAsc.LinhasFixas(1,  1, ' ');
      gRelAsc.NovaLinha('Por ser verdade, firmo a presente.     ' + pNmCidade + '-' +
                        pCdEstado                          + ', ' + pBrvDicion.DataServerExtenso);
      gRelAsc.NovaLinha(StringOfChar(' ', 40));

      gRelAsc.LinhasFixas(2,  1, ' ');
      gRelAsc.NovaLinha(StringOfChar(' ', 40) + StringOfChar('_', 40));
      gRelAsc.NovaLinha(StringOfChar(' ', 40) + pNmMotori);
      gRelAsc.NovaLinha('Destino: ' + pDsDesCar);

      gRelAsc.LinhasFixas(1,  80, '=');
end;

Initialization
    RegisterClass(TRlc0010);

Finalization
    UnRegisterClass(TRlc0010);


end.
