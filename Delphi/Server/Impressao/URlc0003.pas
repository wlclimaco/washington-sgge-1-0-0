unit URlc0003;

interface

uses Classes, URlc0000, DbClient, SysUtils, BrvDicionario, BrvClientDataSet,
     BrvExport, ComCtrls, Forms, BrvRelAsc;


type
  TRlc0003 = class(TRlc0000)
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

procedure TRlc0003.CompletaCabecalho(Sender: TObject);
begin
      gRelAsc.NovaLinha('Classificacao     Nome da conta');
      gRelAsc.NovaLinha(StringOfChar('-', 132));
end;

constructor TRlc0003.Create(AOwner: TComponent);
begin
     inherited;
end;

destructor TRlc0003.Destroy;
begin
     inherited;
end;

function TRlc0003.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String;
                                 pData      : OleVariant) : String;
var lCdsRelato : TClientDataSet;
    lBrvExport : TBrvExport;
    lTreView   : TTreeView;
    lFrmView   : TForm;
    lNrNode    : Integer;
    lNrMargem  : Integer;
begin
      Result    := ' ';
      lNrMargem := 0;

      try
          gRelAsc := TBrvRelAsc.Create(Self);
          gRelAsc.CompletaCabecalho := CompletaCabecalho;
          gRelAsc.ColunasPorLinha   := Rel132Col;
          lBrvExport := TBrvExport.Create(Self);
          lFrmView   := TForm.Create(Self);
          lFrmView.Visible := False;

          lTreView   := TTreeView.Create(lFrmView);
          lTreView.Parent := lFrmView;

          try
              gRelAsc.NovoRelatorioInvisivel(pBrvDicion.UserCode, pNmEmpres, 'RLC0003',
                                                               'Plano de Conta Contábil');

              lCdsRelato      := TClientDataSet.Create(Self);
              lCdsRelato.Data := pData;
              lBrvExport.MontarTreeView(lCdsRelato.FieldByName('DsPlano').AsString,
                                        lTreView);

              for lNrNode := 0 to lTreView.Items.Count -1 do
              begin
                    lNrMargem := lTreView.Items[lNrNode].Level * 5;
                    gRelAsc.NovaLinha(StringOfChar(' ', lNrMargem) +
                                                            lTreView.Items[lNrNode].Text);
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
          FreeAndNil(lBrvExport);
          FreeAndNil(lFrmView);
      end;
end;

Initialization
    RegisterClass(TRlc0003);

Finalization
    UnRegisterClass(TRlc0003);


end.

