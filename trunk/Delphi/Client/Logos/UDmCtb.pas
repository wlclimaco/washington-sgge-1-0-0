unit UDmCtb;

interface

uses
  SysUtils, Classes, ComCtrls, DB, DBClient, Mask, BrvEditNum, BrvClientDataSet, Forms,
  UClaSrv;

type
  TDmCtb = class(TDataModule)
    QpConta: TBrvClientDataSet;
    QcLanCon: TBrvClientDataSet;
    QcLanConCdEmpres: TIntegerField;
    QcLanConDtLancto: TDateTimeField;
    QcLanConNrConDeb: TIntegerField;
    QcLanConNrConCre: TIntegerField;
    QcLanConVrLancto: TFloatField;
    QcLanConNrDocto: TStringField;
    QcLanConCdHistor: TIntegerField;
    QcLanConDsHistor: TMemoField;
    QcLanConNmFormul: TStringField;
    QcLanConCdCeCuDe: TIntegerField;
    QcLanConCdCeCuCr: TIntegerField;
    QcLanConNrClaDeb: TStringField;
    QcLanConNrClaCre: TStringField;
    QcLanConSnEncerr: TStringField;
    QcLanConNrLancto: TIntegerField;
    QcLanConNrPlano: TIntegerField;
  private
    { Private declarations }
  public
    { Public declarations }
    procedure ConsultarPlanoContas(pNrPlano : Integer; pTpConta : Integer;
                                   pEdtNrClassi : TMaskEdit;
                                   pEdtNrConta  : TBrvEditNum;
                                   pSnModal     : Boolean;
                                   pColNrClassi : TField;
                                   pColNrConta  : TField;
                                   pCdEmpres    : Integer);

    procedure PesquisarConta(var pNrConta : Integer; var pNrClassi : String;
                             var pNmConta : String;
                                 pNrPlano : Integer;     pNrClaNul : String);
    procedure IniciarLancamentos;
    procedure LancarContabilidade(pNrLancto : Integer;    pNrPlano  : Integer;
                                  pCdEmpres : Integer;    pDtLancto : TDateTime;
                                  pNrConDeb : Integer;    pNrConCre : Integer;
                                  pVrLancto : Real;       pNrDocto  : String;
                                  pCdHistor : Integer;    pDsHistor : TStrings;
                                  pNmFormul : String;     pSnEncerr : String;
                                  pCdCeCuCr : Integer;    pCdCeCuDe : Integer;
                                  pNrClaDeb : String;     pNrClaCre : String);
    procedure FixarContabilidade(pNrPlano : Integer; pNmFormul : String);
    procedure PesquisarPeriodoContabil(pCdEmpres : Integer;     pStAbeFec : String;
                                   var pDtAno    : Integer; var pDtMes    : Integer);
    function  PlanoContasEmpresa(pCdEmpres: Integer; pDtRefere: TDateTime): Integer;

  end;

var
  DmCtb: TDmCtb;

implementation

uses UCon0010, UDmSis, UDmSrvApl;


{$R *.dfm}


{ TDmCtb }

procedure TDmCtb.ConsultarPlanoContas(pNrPlano     : Integer; pTpConta : Integer;
                                      pEdtNrClassi : TMaskEdit;
                                      pEdtNrConta  : TBrvEditNum;
                                      pSnModal     : Boolean;
                                      pColNrClassi : TField;
                                      pColNrConta  : TField;
                                      pCdEmpres    : Integer);
var lNrFormul : Integer;
begin
      if not DmSis.FormularioAberto('CON0010', lNrFormul) then
      begin
            Con0010 := TCon0010.Create(Self);
      end;

      Con0010.gCdEmpres    := pCdEmpres;

      if (Con0010.gNrPlano <> pNrPlano) or (Con0010.gTpConta <> pTpConta) then
      begin
            Con0010.gNrPlano := pNrPlano;
            Con0010.gTpConta := pTpConta;
            Con0010.CarregarPlanoDeContas;
      end;

      Con0010.gEdtNrConta  := pEdtNrConta;
      Con0010.gEdtNrClassi := pEdtNrClassi;

      Con0010.gColNrClassi := (pColNrClassi as TStringField);
      Con0010.gColNrConta  := (pColNrConta  as TFmTBcdField);

      if pSnModal then
      begin
            Con0010.FormStyle := fsNormal;
            Con0010.Visible   := False;
            Con0010.ShowModal;
      end;
end;

procedure TDmCtb.PesquisarConta(var pNrConta  : Integer;
                                var pNrClassi : String; var pNmConta : String;
                                    pNrPlano  : Integer;    pNrClaNul : String);
var lDsParams : String;
begin
      pNmConta  := '';
      lDsParams := '<%NrPlano%>;' + IntToStr(pNrPlano) + #13;

      if pNrConta <> 0 then
      begin
            lDsParams := lDsParams + '<%NmColuna%>;NrConta = ' + IntToStr(pNrConta);
      end
      else if pNrClassi <> pNrClaNul then
           begin
                 lDsParams := lDsParams + '<%NmColuna%>;NrClassi= ' +
                                                                   #39 + pNrClassi + #39;
           end
      ;

      QpConta.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(78, lDsParams, Name);

      if QpConta.Eof then
      begin
            raise Exception.Create('Conta não encontrada.');
      end;

      if QpConta.FieldByName('SnrecLan').AsString <> 'S' then
      begin
            pNmConta  := '';
            pNrClassi := '';
            pNrConta  := 0;
            raise Exception.Create('Conta não pode receber lançamento');
      end;

      pNrClassi := QpConta.FieldByName('NrClassi').AsString;
      pNmConta  := QpConta.FieldByName('NmConta').AsString;
      pNrConta  := QpConta.FieldByName('NrConta').AsInteger;
end;

procedure TDmCtb.PesquisarPeriodoContabil(pCdEmpres : Integer;     pStAbeFec : String;
                                      var pDtAno    : Integer; var pDtMes    : Integer);
var lDsParams  : String;
    lCdsPerCon : TClientDataSet;
begin
      lDsParams := '<%CdEmpres%>;' + IntToStr(pCdEmpres) + #13 +
                   '<%StAbeFec%>;' + pStAbeFec           + #13;

      lCdsPerCon := TClientDataSet.Create(Self);
      try
          lCdsPerCon.Data :=
                      DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(87, lDsParams, Name);

          pDtAno := lCdsPerCon.FieldByName('DtAno').AsInteger;
          pDtMes := lCdsPerCon.FieldByName('DtMes').AsInteger;
      finally
          FreeAndNil(lCdsPerCon);
      end;
end;

procedure TDmCtb.FixarContabilidade(pNrPlano : Integer; pNmFormul : String);
var lConexao     : TSDmConClient;
    lDsResult    : String;
begin
      lConexao := TSDmConClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lConexao.EfetuarLancamentosContabeis(DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                               pNrPlano, pNmFormul, QcLanCon.Data);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TDmCtb.IniciarLancamentos;
begin
      if QcLanCon.Active then
      begin
            QcLanCon.EmptyDataSet;
      end else
      begin
            QcLanCon.CreateDataSet;
      end;
end;

procedure TDMCTB.LancarContabilidade(pNrLancto : Integer;    pNrPlano  : Integer;
                                     pCdEmpres : Integer;    pDtLancto : TDateTime;
                                     pNrConDeb : Integer;    pNrConCre : Integer;
                                     pVrLancto : Real;       pNrDocto  : String;
                                     pCdHistor : Integer;    pDsHistor : TStrings;
                                     pNmFormul : String;     pSnEncerr : String;
                                     pCdCeCuCr : Integer;    pCdCeCuDe : Integer;
                                     pNrClaDeb : String;     pNrClaCre : String);
begin
      QcLanCon.Append;
      QcLanCon.FieldByName('NrLancto').AsInteger   := pNrLancto;
      QcLanCon.FieldByName('NrPlano' ).AsInteger   := pNrPlano;
      QcLanCon.FieldByName('CdEmpres').AsInteger   := pCdEmpres;
      QcLanCon.FieldByName('DtLancto').AsDateTime  := pDtLancto;
      QcLanCon.FieldByName('NrConCre').AsInteger   := pNrConCre;
      QcLanCon.FieldByName('NrConDeb').AsInteger   := pNrConDeb;
      QcLanCon.FieldByName('VrLancto').AsFloat     := pVrLancto;
      QcLanCon.FieldByName('NrDocto' ).AsString    := pNrDocto;
      QcLanCon.FieldByName('CdHistor').AsInteger   := pCdHistor;
      QcLanCon.FieldByName('DsHistor').AsString    := pDsHistor.Text;
      QcLanCon.FieldByName('NmFormul').AsString    := pNmFormul;
      QcLanCon.FieldByName('SnEncerr').AsString    := pSnEncerr;
      QcLanCon.FieldByName('NrClaDeb').AsString    := pNrClaDeb;
      QcLanCon.FieldByName('NrClaCre').AsString    := pNrClaCre;
      QcLanCon.FieldByName('CdCeCuCr').AsInteger   := pCdCeCuCr;
      QcLanCon.FieldByName('CdCeCuDe').AsInteger   := pCdCeCuDe;
      QcLanCon.Post;
end;

function TDMCTB.PlanoContasEmpresa(pCdEmpres: Integer; pDtRefere: TDateTime): Integer;
var lConexao     : TSDmConClient;
    lDsResult    : String;
begin
      lConexao := TSDmConClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          Result := lConexao.PlanoContasEmpresa(DmSrvApl.BrvDicionario.Credencial,
                                                lDsResult, pCdEmpres, pDtRefere);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

end.
//
