unit BrvDadosTabela;

interface

uses SysUtils, Classes, BrvConnection, BrvClientDataSet, BrvDataSet,
     Provider, BrvDicionario, SqlExpr;

type
  TBrvDadosTabela = class(TComponent)
  private
    { Private declarations }
     FDsChaAtr : TStrings;
     FDsChaVal : TStrings;
     FDsNmAtRe : TStrings;
     FDsVrAtRe : TStrings;
     FDsConnec : TBrvConnection;
     FDsDicion : TBrvDicionario;
     FNmTabela : String;

     FQpRetorn : TBrvDataSet;
     FDpRetorn : TDataSetProvider;
     FCcRetorn : TBrvClientDataSet;

     FSnParame : Boolean;

     Procedure SetFDsChaAtr(Value : TStrings);
     Procedure SetFDsChaVal(Value : TStrings);
     Procedure SetFDsNmAtRe(Value : TStrings);
     Procedure SetFDsVrAtRe(Value : TStrings);
     Function  MontarSentecaSQL : String;

  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure   Executar;
    property    ValorAtributoRetorno : TStrings      read FDsVrAtRe write SetFDsVrAtRe;
  published
    { Published declarations }
    property BrNomeAtributosChave   : TStrings          read FDsChaAtr write SetFDsChaAtr;
    property BrValorAtributosChave  : TStrings          read FDsChaVal write SetFDsChaVal;
    property BrNomeAtributoRetorno  : TStrings          read FDsNmAtRe write SetFDsNmAtRe;

    property BrConnection           : TBrvConnection    read FDsConnec write FDsConnec;
    property BrTabela               : String            read FNmTabela write FNmTabela;
    property BrTabelaParametro      : Boolean           read FSnParame write FSnParame;
    property BrDicionario           : TBrvDicionario    read FDsDicion write FDsDicion;

  end;

procedure Register;

implementation

uses DB, DBClient;

procedure Register;
begin
      RegisterComponents('Bravo Banco', [TBrvDadosTabela]);
end;

constructor TBrvDadosTabela.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);
      FDsChaAtr := TStringList.Create;
      FDsChaVal := TStringList.Create;
      FDsNmAtRe := TStringList.Create;
      FDsVrAtRe := TStringList.Create;
end;

destructor TBrvDadosTabela.Destroy;
begin
      FDsChaAtr.Destroy;
      FDsChaVal.Destroy;
      FDsNmAtRe.Destroy;
      FDsVrAtRe.Destroy;

      inherited  destroy;
end;

Procedure TBrvDadosTabela.SetFDsChaAtr(Value : TStrings);
begin
      FDsChaAtr.Assign(Value);
end;

Procedure TBrvDadosTabela.SetFDsChaVal(Value : TStrings);
begin
      FDsChaVal.Assign(Value);
end;

Procedure TBrvDadosTabela.SetFDsNmAtRe(Value : TStrings);
begin
      FDsNmAtRe.Assign(Value);
end;

Procedure TBrvDadosTabela.SetFDsVrAtRe(Value : TStrings);
begin
      FDsVrAtRe.Assign(Value);
end;

Function TBrvDadosTabela.MontarSentecaSQL : String;
Var NrIndice : Byte;
    DsSelect : String;
    DsWhere  : String;
begin
      DsSelect := '';
      DsWhere  := '';

      for NrIndice := 0 to FDsNmAtRe.Count - 1 do
      begin
            DsSelect := DsSelect  + ', ' + FDsNmAtRe[NrIndice];
      end;
      Delete(DsSelect, 1, 1);
      DsSelect := 'Select ' + DsSelect;

      for NrIndice := 0 to FDsChaAtr.Count - 1 do
      begin
            DsWhere := DsWhere  + ' and ' + FDsChaAtr[NrIndice] + ' = ' +
                                                                      FDsChaVal[NrIndice];
      end;
      Delete(DsWhere, 1, 4);
      DsWhere := ' Where ' + DsWhere;

      Result := DsSelect + ' From  ' + FNmTabela + ' ' + DsWhere;

      if FSnParame = True then
      begin
            Result := Result + ' and DtUltAlt = ' +
                               ' (Select Max(DtUltAlt) From ' + FNmTabela + DsWhere + ')';
      end;
end;

procedure TBrvDadosTabela.Executar;
Var NrIndice : Byte;
begin
      try
          FQpRetorn                  := TBrvDataSet.Create(Self);
          FQpRetorn.SQLConnection    := FDsConnec;
          FQpRetorn.BrDicionario     := FDsDicion;
          FQpRetorn.GetMetadata      := False;
          FQpRetorn.NumericMapping   := True;
          FQpRetorn.CommandType      := ctQuery;
          FQpRetorn.CommandText      := MontarSentecaSQL;

          FDpRetorn                  := TDataSetProvider.Create(Self);
          FDpRetorn.DataSet          := FQpRetorn;
          FDpRetorn.Name             := 'FDpRetorn';

          FCcRetorn                  := TBrvClientDataSet.Create(Self);
          FCcRetorn.ProviderName     := FDpRetorn.Name;
          FCcRetorn.BrDicionario     := FDsDicion;
          FCcRetorn.ReadOnly         := True;

          FCcRetorn.Close;
          FCcRetorn.Open;

          FDsVrAtRe.Clear;

          if not FCcRetorn.Eof then
          begin
                for NrIndice := 0 to FCcRetorn.FieldCount - 1 do
                begin
                      FDsVrAtRe.Add(FCcRetorn.Fields[NrIndice].AsString);
                end;
          end;

      finally
          FCcRetorn.Close;
          FreeAndNil(FQpRetorn);
          FreeAndNil(FDpRetorn);
          FreeAndNil(FCcRetorn);
      end
end;

end.
