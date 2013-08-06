unit URlc0000;

interface

uses Classes, DbClient, BrvDicionario, BrvRelASC, SysUtils;

type
  TRlc0000 = class(TComponent)
  protected
    { Protected declarations }
    function ProcessarParametros(pCdsParams : TClientDataSet): String;
  private
    { Private declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    function    GerarRelatorio(pCdsParams : TClientDataSet;
                               pBrvDicion : TBrvDicionario;
                               pNmEmpres  : String;
                               pData      : OleVariant): String; virtual;
  end;

implementation

{ TRelPadrao }

constructor TRlc0000.Create(AOwner: TComponent);
begin
      inherited;
end;

destructor TRlc0000.Destroy;
begin
      inherited;
end;

function TRlc0000.GerarRelatorio(pCdsParams : TClientDataSet;
                                 pBrvDicion : TBrvDicionario;
                                 pNmEmpres  : String;
                                 pData      : OleVariant) : String;
begin
      Result := 'Rel Padrao ok';
end;

function TRlc0000.ProcessarParametros(pCdsParams: TClientDataSet) : String;
begin
      Result := '';
      pCdsParams.First;
      pCdsParams.Next;
      pCdsParams.Next;
      pCdsParams.Next;
      pCdsParams.Next;

      while not pCdsParams.Eof do
      begin
            Result := Result + '<%' + pCdsParams.FieldByName('NmParam').AsString + '%>;' +
                                      pCdsParams.FieldByName('DsParam').AsString + #13;
            pCdsParams.Next;
      end;
end;

end.
