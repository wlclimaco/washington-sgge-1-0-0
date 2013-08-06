unit BrvClientDataSet;

interface

uses SysUtils, Classes, DB, DBClient, SqlExpr, Provider, SQLConst, Dialogs,
     DSIntf, BrvDicionario, BrvDb, BrvSql;

type
  TBrvClientDataSet = class(TClientDataSet)
  private
    { Private declarations }
    FCdQuery   : Integer;
    FSnShoFie  : Boolean;
    FDsParams  : TStrings;
    FDsDicion  : TBrvDicionario;
    FAddFields : TDataSetNotifyEvent;
    FTpQuery   : TVqType;
    gBrvSql    : TBrvSql; // classe com as funcionalidades comum ClientDataSet e SqlDataSet
    gSnGraLog  : Boolean;
    gDsValLog  : TStrings; // Valor anterior do log, antes de editar
    gNmFormul  : String;
    gCdUsuari  : Integer;
    gDsAppErr  : String; // Erro do update, utilizado no ReconcileError e BrApplyUpdates;
    procedure TMemoFieldGetText(Sender: TField; var Text: String; DisplayText: Boolean);
    procedure BrSetParametros(const Value: TStrings);
    procedure SetDicionario(const Value: TBrvDicionario);
    procedure SetTipoQuery(const Value: TVqType);
    procedure GravarLogInsert(pNmTabela: String);
    function ValorChavePrimaria(pNmTabela: String): String;
    procedure GravarLogUpdate(pNmTabela: String);
    function ValorAnteriorGravacao(pNmColuna: String): String;
    procedure GravarLogDelete(pNmTabela: String);
    procedure DefaultReconcileError(DataSet: TCustomClientDataSet; E: EReconcileError;
      UpdateKind: TUpdateKind; var Action: TReconcileAction);
  protected
    procedure DoAfterOpen; override;
    procedure OpenCursor(InfoQuery: Boolean); override;
    procedure DoBeforeEdit; override;
    procedure DoBeforeDelete; override;
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure  BrApplyUpdates;
  published
    { Published declarations }
    property BrShowFieldName : Boolean             read FSnShoFie  write FSnShoFie;
    property BrQueryCode     : Integer             read FCdQuery   write FCdQuery;
    property BrParams        : TStrings            read FDsParams  write BrSetParametros;
    property BrDicionario    : TBrvDicionario      read FDsDicion  write SetDicionario;
    property BrOnAddFields   : TDataSetNotifyEvent read FAddFields write FAddFields;
    property BrType          : TVqType             read FTpQuery   write SetTipoQuery;
    property BrGravaLog      : Boolean             read gSnGraLog  write gSnGraLog;
    property BrFormName      : String              read gNmFormul  write gNmFormul;
    property BrUserCode      : Integer             read gCdUsuari  write gCdUsuari;
    property BrApplyError    : String              read gDsAppErr  write gDsAppErr;
  end;

procedure Register;

implementation

procedure Register;
begin
      RegisterComponents('Bravo Banco', [TBrvClientDataSet]);
end;

procedure TBrvClientDataSet.BrApplyUpdates;
begin
      gDsAppErr := '';

      if ApplyUpdates(0) > 0 then
      begin
            raise Exception.Create(gDsAppErr);
      end;
end;

procedure TBrvClientDataSet.DefaultReconcileError(DataSet: TCustomClientDataSet;
               E: EReconcileError; UpdateKind: TUpdateKind; var Action: TReconcileAction);
begin
      gDsAppErr := E.Message;
end;

procedure TBrvClientDataSet.BrSetParametros(const Value: TStrings);
begin
  FDsParams := Value;
end;

constructor TBrvClientDataSet.Create(AOwner: TComponent);
begin
      inherited;
      FDsParams := TStringList.Create;
      FSnShoFie := False;
      FTpQuery  := VqNormal;
      gBrvSql   := TBrvSql.Create;
      gBrvSql.LogCriarTabela(Self);

      gSnGraLog := True;
      gDsValLog := TStringList.Create;

      OnReconcileError := DefaultReconcileError;
end;

destructor TBrvClientDataSet.Destroy;
begin
      FDsParams.Destroy;
      gBrvSql.Destroy;
      gDsValLog.Destroy;
      inherited Destroy;
end;

procedure TBrvClientDataSet.SetDicionario(const Value: TBrvDicionario);
begin
      FDsDicion := Value;
end;

procedure TBrvClientDataSet.SetTipoQuery(const Value: TVqType);
begin
      FTpQuery := Value;
end;

procedure TBrvClientDataSet.DoAfterOpen;
var lNmComPai : String;
begin
      if Owner <> nil then
      begin
            lNmComPai := Owner.Name;
      end else
      begin
            lNmComPai := 'BrvClientDataSet: Componente pai não identificado';
      end;

      if fDsDicion <> nil then
      begin
            gBrvSql.DoAfterOpen(Self, FDsDicion, CommandText, Name, lNmComPai, fSnShoFie);
      end;

      inherited;
end;

procedure TBrvClientDataSet.OpenCursor(InfoQuery: Boolean);
var lNmComPai : String;
begin
      if Owner <> nil then
      begin
            lNmComPai := Owner.Name;
      end else
      begin
            lNmComPai := 'BrvClientDataSet: Componente pai não identificado';
      end;
      CommandText := gBrvSql.BrProcessarAberturaSQL(FDsDicion, fCdQuery,
                                               ComponentState, Name, lNmComPai, fTpQuery,
                                               CommandText, fDsParams);
      inherited;
end;

procedure TBrvClientDataSet.TMemoFieldGetText(Sender: TField; var Text: String;
                                                             DisplayText: Boolean);
begin
      Text := Copy(Sender.AsString, 1, 40);

      StringReplace(Text, #10, ' ', [rfReplaceAll]);
      StringReplace(Text, #13, ' ', [rfReplaceAll]);

      Sender.Alignment := taLeftJustify;
end;

procedure TBrvClientDataSet.DoBeforeDelete;
var lNmTabela : String;
    lTpOperac : String;
begin
      inherited;

      if (Tag = 0) and (gSnGraLog) then
      begin
            if FDsDicion = nil then
            begin
                  raise Exception.Create(Owner.Name + '.' + Name +
                                                   ': dicioinário de dados não definido');
            end;

            gBrvSql.NomeDaTabelaSQL(lNmTabela, lTpOperac, CommandText);

            if lNmTabela <> '' then
            begin
                  gBrvSql.LogLimparDataSet;
                  GravarLogDelete(lNmTabela);
            end;
      end;
end;

procedure TBrvClientDataSet.GravarLogDelete(pNmTabela : String);
var lDsChaPri   : String;
    lStlColuna  : TStrings;
    lNrAtribu   : Integer;
    lNmAtribu   : String;
begin
      lDsChaPri := ValorChavePrimaria(pNmTabela);

      try
          lStlColuna := TStringList.Create;
          FDsDicion.AtributosDaTabela(pNmTabela, lStlColuna);

          for lNrAtribu := 0 to lStlColuna.Count -1 do
          begin
                lNmAtribu := lStlColuna.Strings[lNrAtribu];
                if Trim(FieldByName(lNmAtribu).AsString) <> '' then
                begin
                      gBrvSql.LogGravarDataSet(lNmAtribu, '<% Exclusão do registro! %>');
                end;
          end;

          gBrvSql.LogAtualizarBanco(pNmTabela, lDsChaPri, 3, FDsDicion, Self,
                                    gNmFormul, gCdUsuari, '');
      finally
          FreeAndNil(lStlColuna);
      end;
end;

procedure TBrvClientDataSet.DoBeforeEdit;
var lNratribu : Integer;
begin
      gDsValLog.Clear;

      if gSnGraLog then
      begin
            for lNratribu := 0 to FieldCount -1 do
            begin
                  if Trim(Fields.Fields[lNratribu].AsString) <> '' then
                  begin
                        gDsValLog.Add(Fields.Fields[lNratribu].FieldName + '=' +
                                      Fields.Fields[lNratribu].AsString);
                  end else
                  begin
                        gDsValLog.Add(Fields.Fields[lNratribu].FieldName + '=');
                  end;
            end;
      end;

      inherited;
end;



procedure TBrvClientDataSet.GravarLogInsert(pNmTabela : String);
var lDsChaPri   : String;
    lStlColuna  : TStrings;
    lNrAtribu   : Integer;
    lNmAtribu   : String;
begin
      lDsChaPri := ValorChavePrimaria(pNmTabela);

      try
          lStlColuna := TStringList.Create;
          FDsDicion.AtributosDaTabela(pNmTabela, lStlColuna);

          for lNrAtribu := 0 to lStlColuna.Count -1 do
          begin
                lNmAtribu := lStlColuna.Strings[lNrAtribu];

                if Trim(FieldByName(lNmAtribu).AsString) <> '' then
                begin
                      gBrvSql.LogGravarDataSet(lNmAtribu, FieldByName(lNmAtribu).AsString);
                end;
          end;

          gBrvSql.LogAtualizarBanco(pNmTabela, lDsChaPri, 1, FDsDicion, Self,
                                    gNmFormul, gCdUsuari, '');
      finally
          FreeAndNil(lStlColuna);
      end;
end;

procedure TBrvClientDataSet.GravarLogUpdate(pNmTabela : String);
var lDsChaPri   : String;
    lStlColuna  : TStrings;
    lNrAtribu   : Integer;
    lNmAtribu   : String;
    lVrAnteri   : String;
begin
      lDsChaPri := ValorChavePrimaria(pNmTabela);

      try
          lStlColuna := TStringList.Create;
          FDsDicion.AtributosDaTabela(pNmTabela, lStlColuna);

          for lNrAtribu := 0 to lStlColuna.Count -1 do
          begin
                lNmAtribu := lStlColuna.Strings[lNrAtribu];
                lVrAnteri := ValorAnteriorGravacao(lNmAtribu);

                if lVrAnteri <> '!@#$% --> ATRIBUTO INDEFINIDO <-- %$#@!' then
                begin
                      if Trim(FieldByName(lNmAtribu).AsString) <> Trim(lVrAnteri) then
                      begin
                            gBrvSql.LogGravarDataSet(lNmAtribu,
                                                     FieldByName(lNmAtribu).AsString);
                      end;
                end;
          end;
          gBrvSql.LogAtualizarBanco(pNmTabela, lDsChaPri, 2, FDsDicion, Self,
                                    gNmFormul, gCdUsuari, '');
      finally
          FreeAndNil(lStlColuna);
      end;
end;

function TBrvClientDataSet.ValorAnteriorGravacao(pNmColuna : String) : String;
var lNrLinha  : Integer;
    lNmColuna : String;
    lVrAtribu : String;
    lSnAchou  : Boolean;
begin
      lNrLinha := 0;
      lSnAchou := False;
      Result   := '';

      while (lNrLinha < gDsValLog.Count) do
      begin
            lVrAtribu := gDsValLog.Strings[lNrLinha];
            lNmColuna := Copy(lVrAtribu, 1, Pos('=', lVrAtribu) -1);

            if pNmColuna = lNmColuna then
            begin
                  System.Delete(lVrAtribu, 1, Pos('=', lVrAtribu));
                  result   := lVrAtribu;
                  lSnAchou := True;

                  lNrLinha := gDsValLog.Count + 1 // saindo do loop
            end;

            inc(lNrLinha);
      end;

      if not lSnAchou then
      begin
            Result := '!@#$% --> ATRIBUTO INDEFINIDO <-- %$#@!';
      end;
end;

function TBrvClientDataSet.ValorChavePrimaria(pNmTabela : String) : String;
var  lStlChave : TStrings;
     lNrChave  : Integer;
begin
      try
          lStlChave   := TStringList.Create;
          FDsDicion.ChavePrimaria(pNmTabela, lStlChave);
          Result      := '';

          for lNrChave := 0 to lStlChave.Count - 1 do
          begin
                Result := Result + FieldByName(lStlChave.Strings[lNrChave]).AsString + '@';
          end;
      finally
          FreeAndNil(lStlChave);
      end;
end;

end.
