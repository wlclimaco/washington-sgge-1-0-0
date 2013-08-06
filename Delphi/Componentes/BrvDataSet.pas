unit BrvDataSet;

interface

uses
  Windows, SysUtils, Classes, DB, SqlExpr, BrvDicionario, Forms, StdCtrls, Controls,
  BrvString, Dialogs, BrvDb, BrvSql;

type
  TBrvDataSet = class(TSQLDataSet)
  private
    { Private declarations }
    FSnShoFie   : Boolean;
    FCdQuery    : Integer;
    FDsParams   : TStrings;
    FTpQuery    : TVqType;
    FDsDicion   : TBrvDicionario;
    FAddFields  : TDataSetNotifyEvent;
    gBrvSql     : TBrvSql;
    gNmFormul  : String;
    gCdUsuari  : Integer;
    procedure   SetTipoQuery(InTpQuery : TVqType);
    procedure   SetDicionario(Value: TBrvDicionario);
    procedure   BrProcessarAberturaSQL;
  protected
    { Protected declarations }
    procedure   OpenCursor(InfoQuery: Boolean); override;
    procedure   DoAfterOpen; override;
    procedure   CreateFields; override;
    procedure   BrSetParametros(Value: TStrings);
  public
    { Public declarations }
    procedure   BrExecuteSQL(pSnGraLog : Boolean);
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
  published
    { Published declarations }
    property BrShowFieldName : Boolean             read FSnShoFie  write FSnShoFie;
    property BrQueryCode     : Integer             read FCdQuery   write FCdQuery;
    property BrParams        : TStrings            read FDsParams  write BrSetParametros;
    property BrDicionario    : TBrvDicionario      read FDsDicion  write SetDicionario;
    property BrOnAddFields   : TDataSetNotifyEvent read FAddFields write FAddFields;
    property BrType          : TVqType             read FTpQuery   write SetTipoQuery;
    property BrFormName      : String              read gNmFormul  write gNmFormul;
    property BrUserCode      : Integer             read gCdUsuari  write gCdUsuari;
  end;

procedure Register;

implementation

uses BrvOracle, BrvInterbase, BrvInformix;

constructor TBrvDataSet.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
      FDsParams := TStringList.Create;
      FSnShoFie := False;
      FTpQuery  := VqNormal;
      gBrvSql   := TBrvSql.Create;
      gBrvSql.LogCriarTabela(Self);
      GetMetadata := False;
end;

destructor TBrvDataSet.Destroy;
begin
      FDsParams.Destroy;
      gBrvSql.Destroy;
      inherited  destroy;
end;

procedure TBrvDataSet.BrSetParametros(Value: TStrings);
begin
      FDsParams.Assign(Value);
end;

procedure TBrvDataSet.SetDicionario(Value: TBrvDicionario);
begin
      FDsDicion := Value;
end;

procedure TBrvDataSet. SetTipoQuery(InTpQuery : TVqType);
begin
      FTpQuery := InTpQuery;
end;

procedure TBrvDataSet.OpenCursor(InfoQuery: Boolean);
begin
      BrProcessarAberturaSQL;
      inherited;
end;

procedure TBrvDataSet.BrProcessarAberturaSQL;
begin
      CommandText := gBrvSql.BrProcessarAberturaSQL(FDsDicion, fCdQuery,
                                               ComponentState, Name, Owner.Name, fTpQuery,
                                               CommandText, fDsParams);
end;

procedure TBrvDataSet.CreateFields;
begin
      inherited CreateFields;

      if Assigned(FAddFields) then
      begin
            FAddFields(Self);
      end;
end;

procedure TBrvDataSet.DoAfterOpen;
var lNmComPai : String;
begin
      if Owner <> nil then
      begin
            lNmComPai := Owner.Name;
      end else
      begin
            lNmComPai := 'BrvDataSet: Componente pai não identificado';
      end;

      if BrDicionario.Camada = server then
      begin
            gBrvSql.DoAfterOpen(Self, FDsDicion, CommandText, Name, Owner.Name, fSnShoFie);
      end;

      inherited DoAfterOpen;
end;

procedure TBrvDataSet.BrExecuteSQL(pSnGraLog : Boolean);
var lNmComPai : String;
    lNmTabela : String;
    lTpOperac : String;
    lDsSql    : String;
begin
      if Owner <> nil then
      begin
            lNmComPai := Owner.Name;
      end else
      begin
            lNmComPai := 'BrvDataSet: Componente pai não identificado';
      end;

      CommandText := gBrvSql.BrTraduzirInstrucaoSQL(FDsDicion, fCdQuery,
                             ComponentState, Name, lnmComPai, fTpQuery,
                             CommandText, fDsParams);

      if pSnGraLog then
      begin
            gBrvSql.NomeDaTabelaSQL(lNmTabela, lTpOperac, CommandText);
            lDsSql := CommandText;
      end;

      ExecSql;

      if pSnGraLog then
      begin
            gBrvSql.LogProcessarInstrucaoSql(lDsSql, lTpOperac, Self, lNmTabela,
                                             FDsDicion, gNmFormul, gCdUsuari);
      end;
end;

procedure Register;
begin
  RegisterComponents('Bravo Banco', [TBrvDataSet]);
end;

end.

