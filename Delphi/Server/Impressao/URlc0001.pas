unit URlc0001;

interface

uses Classes, URlc0000, DbClient, BrvDicionario, SysUtils, BrvRelASC;

type
  TRlc0001 = class(TRlc0000)
  private
    { Private declarations }
    gRelAsc  : TBrvRelAsc;
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    function GerarRelatorio(pCdsParams : TClientDataSet;
                            pBrvDicion : TBrvDicionario;
                            pNmEmpres  : String;
                            pData     : OleVariant): String; Override;
  end;

implementation

{ TRelPadrao }

constructor TRlc0001.Create(AOwner: TComponent);
begin
      inherited;
end;

destructor TRlc0001.Destroy;
begin
      inherited;
end;

function TRlc0001.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String;
                                 pData      : OleVariant) : String;
var lCdsRelato : TClientDataSet;
begin
      Result := ' ';

      try
          gRelAsc := TBrvRelAsc.Create(Self);

          try
              gRelAsc.NovoRelatorioInvisivel(pBrvDicion.UserCode, pNmEmpres, 'RLC0001',
                                                           'Teste servidor de impressao');
              lCdsRelato := TClientDataSet.Create(Self);

              lCdsRelato.Data := pBrvDicion.RetornaDadosSqlCadastro(49,
                                              ProcessarParametros(pCdsParams), 'RLC0001');

              gRelAsc.NovaLinha('Se o teste com servidor de aplicacao estiver correto,');
              gRelAsc.NovaLinha('o nome do usuario aparecera na proxima linha:');
              gRelAsc.NovaLinha(lCdsRelato.FieldByName('NmComUsu').AsString);

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
      end;
end;

Initialization
    RegisterClass(TRlc0001);

Finalization
    UnRegisterClass(TRlc0001);


end.
