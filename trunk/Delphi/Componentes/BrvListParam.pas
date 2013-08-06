unit BrvListParam;

interface

  uses Classes, Dialogs, Grids, SysUtils;

  Type
    // Classe dos dados do parametro
    TBrParam = class
    private
      FNmParam  : String;  // Nome do parametro (interno\desenvolvimento)
      FDsParam  : String;  // Descrição do parametro
      FVrInterf : String;  // Valor exibido na interface
      FVrParSQL : String;  // Valor do parametro
      FSnQuote  : Boolean; // Indica se o parametro terá aspas
      { private declarations }
    protected
      { protected declarations }
    public
      { public declarations }
      property NmParam     : String  read FNmParam  write FNmParam;
      property DsParam     : String  read FDsParam  write FDsParam;
      property VrInterface : String  read FVrInterf write FVrInterf;
      property VrParam     : String  read FVrParSQL write FVrParSQL;
      property SnQuote     : Boolean read FSnQuote write FSnQuote;
    published
      { published declarations }
    end;

    // Lista de parametros
    TBrvListParam = class(TComponent)
    private
      { private declarations }
      FListParam : TList;
      function CountDisplay: Integer;
    protected
      { protected declarations }
    public
      { public declarations }
      constructor Create(AOwner: TComponent); override;
      //Adiciona novo parametro a lista
      procedure Add(pNmParam, pDsParam, pVrParInt, pVrParam: String; pSnQuote: Boolean = False);
      //Remove parametro da lista pelo indice
      procedure Rem(Index: Integer);
      //Conta parametros inseridos
      function  Count: Integer;
      //Retorna os parametros no formato padrão do sistema Logos
      function  AsBrParam : String;
      //Move os paramtros visiveis para uma stringgrid
      procedure SetStgParam(pStgParam: TStringGrid);
      //Limpa a lista
      procedure Clear;

      procedure CopyListParam(pListParam : TBrvListParam);
    published
      { published declarations }
    end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvListParam]);
end;

{ TBrListParam }

constructor TBrvListParam.Create;
begin
      inherited Create(AOwner);
      FListParam := TList.Create;
end;

procedure TBrvListParam.Add(pNmParam, pDsParam, pVrParInt, pVrParam: String;
                                                                         pSnQuote: Boolean = False);
var lParam : TBrParam;
begin
      lParam           := TBrParam.Create;
      lParam.NmParam   := pNmParam;
      lParam.DsParam   := pDsParam;
      lParam.FVrInterf := pVrParInt;
      lParam.VrParam   := pVrParam;
      lParam.SnQuote   := pSnQuote;

      FListParam.Add(lParam);
end;

procedure TBrvListParam.CopyListParam(pListParam: TBrvListParam);
var lnrlist: Integer;
begin
      pListParam.Clear;

      for lnrlist := 0 to FListParam.Count -1 do
      begin
            pListParam.Add(TBrParam(FListParam.Items[lnrlist]).FNmParam,
                           TBrParam(FListParam.Items[lnrlist]).FDsParam,
                           TBrParam(FListParam.Items[lnrlist]).FVrInterf,
                           TBrParam(FListParam.Items[lnrlist]).FVrParSQL,
                           TBrParam(FListParam.Items[lnrlist]).FSnQuote);
      end;
end;

function TBrvListParam.Count: Integer;
begin
      Result := FListParam.Count;
end;

function TBrvListParam.CountDisplay: Integer;
var lNrIdx : Integer;
begin
      Result := 0;

      for lNrIdx := 0 to FListParam.Count-1 do
      begin
            if (Trim(TBrParam(FListParam.Items[lNrIdx]).FDsParam) <> '') then
            begin
                  Result := Result + 1;
            end;
      end;
end;

procedure TBrvListParam.Rem(Index: Integer);
begin
      if Index < Count then
      begin
            FListParam.Delete(Index)
      end else
      begin
            ShowMessage('Item não encontrado!');
      end;
end;

function TBrvListParam.AsBrParam: String;
var lNrIdx : Integer;
begin
      Result := '';
      if (FListParam.Count > 0) then
      begin
            for lNrIdx := 0 to FListParam.Count-1 do
            begin
                  Result := Result + '<%' + TBrParam(FListParam.Items[lNrIdx]).FNmParam + '%>;';

                  if (TBrParam(FListParam.Items[lNrIdx]).SnQuote) then
                  begin
                        Result := Result + QuotedStr(TBrParam(FListParam.Items[lNrIdx]).FVrParSQL) +
                                                                                            Chr(13);
                  end else
                  begin
                        Result := Result + TBrParam(FListParam.Items[lNrIdx]).FVrParSQL + Chr(13);
                  end;
            end;

            Result := Copy(Result, 1, length(Result)-1);
      end else
      begin
            ShowMessage('Lista vazia!');
      end;
end;

procedure TBrvListParam.Clear;
begin
      //Limpando lista
      FListParam.Clear;
end;

procedure TBrvListParam.SetStgParam(pStgParam: TStringGrid);
var lNrIdx : Integer;
begin
      if (CountDisplay > 0) then
      begin
            //Definindo 2 linhas (minimo necessário - cabeçalho e linha de registro)
            pStgParam.RowCount := 2;
            //Fixando a primeira linha que será o cabeçalho
            pStgParam.FixedRows:= 1;
            //Definindo 2 colunas (Descrição e valor do parametro)
            pStgParam.ColCount := 2;
            //Nenhuma coluna fixa
            pStgParam.FixedCols:= 0;

            //Altura da linha
            pStgParam.DefaultRowHeight := 15;

            //Movendo descrições ao cabeçalho
            pStgParam.Cells[0,0] := 'Filtros';
            pStgParam.Cells[1,0] := 'Valores';

            for lNrIdx := 0 to FListParam.Count-1 do
            begin
                  //Só inserir parametros que possuam descrição
                  if (Trim(TBrParam(FListParam.Items[lNrIdx]).DsParam) <> '') then
                  begin
                       //Evitando duplicidade de criação de linha porque no rowcount já inicia com 2
                        if (lNrIdx > 0) then
                        begin
                              pStgParam.RowCount := pStgParam.RowCount + 1;
                        end;

                        //Movendo descrições e valor do parametro
                        pStgParam.Cells[0,pStgParam.RowCount-1] :=
                                                         TBrParam(FListParam.Items[lNrIdx]).DsParam;
                        pStgParam.Cells[1,pStgParam.RowCount-1] :=
                                                       TBrParam(FListParam.Items[lNrIdx]).FVrInterf;
                  end;
            end;
      end else
      begin
            ShowMessage('Lista vazia!');
      end;
end;

end.



