unit BrvGestao;

interface

uses
  SysUtils, Classes, BrvDicionario, BrvConnection, DbClient;

type
  TBrvGestao = class(TComponent)
  private
    { Private declarations }
  protected
    { Protected declarations }
    gDsDicion  : TBrvDicionario;
    gSqlConne  : TBrvConnection;
    procedure ValidarObjetos(pNmPaiObj : String; pNmObjeto : String);
    procedure ValidarHistorico(pCdHistor: Integer; pDsClasse: String);
  public
    { Public declarations }
  published
    { Published declarations }
    property BrDicionario    : TBrvDicionario      read gDsDicion  write gDsDicion;
    property SQLConnection   : TBrvConnection      read gSqlConne  write gSqlConne;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Gestao', [TBrvGestao]);
end;

{ TBrvGestao }

{ TBrvGestao }

procedure TBrvGestao.ValidarObjetos(pNmPaiObj : String; pNmObjeto : String);
begin
      if gSqlConne = nil then
      begin
            raise Exception.Create('Objeto ' + pNmObjeto + ' contido em ' + pNmPaiObj +
                                   ' não possui propriedade SQLConnection');
      end;

      if gDsDicion = nil then
      begin
            raise Exception.Create('Objeto ' + pNmObjeto + ' contido em ' + pNmPaiObj +
                                   ' não possui propriedade BrDicionario');
      end;

end;

procedure TBrvGestao.ValidarHistorico(pCdHistor : Integer; pDsClasse : String);
var lCdsHistor : TClientDataSet;
    lSnHisCon  : Boolean;
begin
      if  pCdHistor = 0 then
      begin
            raise Exception.Create('Histórico não informado');
      end;

      try
          lCdsHistor      := TClientDataSet.Create(Self);
          lCdsHistor.Data := gDsDicion.RetornaDadosSqlCadastro(84,
                                                      '<%CdHistor%>;' + IntToStr(pCdHistor) + #13 +
                                                      '<%CdClaHis%>;' + pDsClasse, Name);

          if lCdsHistor.Eof then
          begin
                raise Exception.Create('Histórico ' + IntToStr(pCdHistor) +
                                       ' não pertence as seguintes classes: ' + pDsClasse + #13 +
                                       'Não é possível utiliza-lo neste lançamento.');
          end;
          lCdsHistor.Close;
      finally
         FreeAndNil(lCdsHistor);
      end;
end;

end.
