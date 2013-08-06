unit BrvConsulta;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, Provider,
  DbTables, BrvDicionario, Db, StdCtrls, Mask, BrvDataSet, SqlExpr,
  BrvClientDataSet, DBGrids, SConnect, BrvEditNum, BrvConsultaForm;

type
  TBrvConsulta = class(TComponent)
  private
    { Private declarations }
    FCdQuery   : Integer;
    FDsDicion  : TBrvDicionario;
    FDsParams  : TStrings;
    FDsConfig  : TStrings;
    FDsReturn  : TStrings;
    FDataSource: TDataSource;

    procedure SetParametros(Value : TStrings);
    procedure SetConfigurar(Value : TStrings);
    procedure SetRetorno(Value : TStrings);
    function  SubstituirInstrucaoWhere(DsWhere : String) : String;
    procedure CarregarRetornoConsulta(pDsAuxCon : TBrvClientDataSet);
    procedure ExibirRetornoConsulta;
    procedure CriarBrParams;
    procedure ExtrairDadosLinha(DsLinha : String;
                           var DsReturn : String; var DsValor : String;
                           var SnAltera : String; var SnChave : String);

    function  OcorreramMudanca : Boolean;
    procedure ChamarConsulta(DataSet : TDataSet);
    function NomeDoFormularioPai: String;

  protected
    { Protected declarations }
  public
    { Public declarations }
    FDsRetOld  : TStrings;
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure   BrExecute(DsComWhe : String);
    procedure   BrValidarEntrada;
    procedure   BrCarregarValoresAnteriores;
  published
    { Published declarations }
    property BrConfigurar    : TStrings         read FDsConfig   write SetConfigurar;
    property BrQueryCode     : Integer          read FCdQuery    write FCdQuery;
    property BrDicionario    : TBrvDicionario   read FDsDicion   write FDsDicion;
    property BrParams        : TStrings         read FDsParams   write SetParametros;
    property BrReturn        : TStrings         read FDsReturn   write SetRetorno;
    property BrDataSource    : TDataSource      read FDataSource write FDataSource;
  end;

procedure Register;

implementation

uses Variants, BrvString;

var DsBookOl : TBookmark;

procedure Register;
begin
      RegisterComponents('Bravo Consulta', [TBrvConsulta]);
end;

constructor TBrvConsulta.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);
      FDsParams := TStringList.Create;
      FDsConfig := TStringList.Create;
      FDsReturn := TStringList.Create;
      FDsRetOld := TStringList.Create;
end;

destructor TBrvConsulta.Destroy;
begin
      FreeAndNil(FDsReturn);
      FreeAndNil(FDsParams);
      FreeAndNil(FDsConfig);

//      FreeAndNil(FDsRetOld);
      inherited  destroy;
end;

procedure TBrvConsulta.SetParametros(Value: TStrings);
begin
      FDsParams.Assign(Value);
end;

procedure TBrvConsulta.SetConfigurar(Value : TStrings);
begin
      FDsConfig.Assign(Value);
end;

procedure TBrvConsulta.SetRetorno(Value : TStrings);
begin
      FDsReturn.Assign(Value);
end;

procedure  TBrvConsulta.BrExecute(DsComWhe : String);
begin
      CriarBrParams;

      FrmConsulta := TFrmConsulta.Create(Self);

      FrmConsulta.Caption                := 'Consulta - ' + FormatFloat('0000', FCdQuery);
      FrmConsulta.DsConsul.SQLConnection := FDsDicion.SQLConnection;
      FrmConsulta.DsConsul.BrQueryCode   := FCdQuery;
      FrmConsulta.DsConsul.BrDicionario  := FDsDicion;
      FrmConsulta.DsConsul.BrParams      := FDsParams;
      FrmConsulta.CsConsul.BrDicionario   := FDsDicion;
      FrmConsulta.gDsComWhe              := DsComWhe;
      FrmConsulta.gNmForPai              := NomeDoFormularioPai;
      FrmConsulta.EdtDtLocali.BrDicionario := FDsDicion;
      FrmConsulta.EdtDtLocal2.BrDicionario := FDsDicion;

      if Trim(DsComWhe) = '' then
      begin
            FrmConsulta.PnlFiltro.Visible := True;
            FrmConsulta.DbNavCop.Visible  := True;
            FrmConsulta.Height            := 415;
      end else
      begin
            FrmConsulta.PnlFiltro.Visible := False;
            FrmConsulta.DbNavCop.Visible  := False;
            FrmConsulta.Height            := 215;
      end;

      FrmConsulta.MontarConsulta;

      if FrmConsulta.ShowModal = MrOk then
      begin
            CarregarRetornoConsulta(FrmConsulta.CsConsul);
            if (BrDataSource <> nil) and
               (not (BrDataSource.State in [dsEdit, dsInsert])) then
            begin
                  BrDataSource.DataSet.Edit;
            end;

            ExibirRetornoConsulta;
      end;

      FrmConsulta.Destroy;
end;

function TBrvConsulta.NomeDoFormularioPai : String;
var lNmForm : TObject;
begin
      lNmForm := Owner;
      Result  := '';

      while Result = '' do
      begin
            if (lNmForm is TForm) then
            begin
                  Result := (lNmForm as TForm).Name;
            end else
            begin
                  lNmForm := (lNmForm as TComponent).Owner;
            end;
      end;
end;

procedure   TBrvConsulta.BrValidarEntrada;
var DsParent : TComponent;
    DsOrigem : TComponent;
    DsWhere  : String;
    DsSelect : String;
    NrLinha  : Integer;
    DsReturn : String;
    DsValor  : String;
    SnAltera : String;
    SnChave  : String;
    NrAlias  : Integer;
    CcConAux : TBrvClientDataSet;
    VrAuxili : string;
begin
      if OcorreramMudanca then
      begin
            DsParent := Owner;
            DsWhere  := '';

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =- Montando o complemento da instrução WHERE -=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            DsSelect := Trim(UpperCase(
                           FDsDicion.EncontrarInstrucaoSQLConsulta(FCdQuery)));

            Delete(DsSelect, Pos('SELECT', DsSelect), 6);
            Delete(DsSelect, Pos('FROM', DsSelect), Length(DsSelect));
            DsSelect   := Trim(DsSelect);

            for NrLinha := 0 to FDsConfig.Count - 1 do
            begin
                  ExtrairDadosLinha(FDsConfig.Strings[NrLinha], DsReturn, DsValor,
                                                                                 SnAltera, SnChave);

                  if SnChave = 'S' then
                  begin
                        if (DsSelect <> '') and
                           (DsSelect[Pos(DsValor, DsSelect) - 1] = '.') then
                        begin
                              NrAlias := Pos(DsValor, DsSelect) -1;

                              while (NrAlias > 0) and (DsSelect[NrAlias] <> ' ') and
                                                      (DsSelect[NrAlias] <> ',') do
                              begin
                                    DsValor := DsSelect[NrAlias] + DsValor;
                                    Dec(NrAlias);
                              end;
                        end else
                        begin
                              if (DsSelect <> '') and
                                 (DsSelect[Pos('*', DsSelect) - 1] = '.') then
                              begin
                                    NrAlias := Pos('*', DsSelect) -1;

                                    while (NrAlias > 0) and
                                          (DsSelect[NrAlias] <> ' ') and
                                          (DsSelect[NrAlias] <> ',') do
                                    begin
                                          DsValor := DsSelect[NrAlias] + DsValor;
                                          Dec(NrAlias);
                                    end;
                              end;
                        end;

                        if (FDataSource <> nil) and
                           (BrDataSource.DataSet.FindField(DsReturn) <> nil) then
                        begin
                              if DsWhere <> '' then
                              begin
                                    DsWhere := DsWhere + ' and ';
                              end;

                              if BrDataSource.DataSet.FieldByName(DsReturn).AsString =
                                                                                   '' then
                              begin
                                    DsWhere := DsWhere + DsValor + ' = "0"';
                              end else
                              begin
                                    DsWhere := DsWhere + DsValor + ' = "' +
                                     BrDataSource.DataSet.FieldByName(DsReturn).AsString +
                                                                                      '"';
                              end;
                        end else
                        begin
                              DsOrigem := DsParent.FindComponent(DsReturn);

                              if DsOrigem is TBrvEditNum then
                              begin
                                    if DsWhere <> '' then
                                    begin
                                          DsWhere := DsWhere + ' and ';
                                    end;

                                    if TBrvEditNum(DsOrigem).BrCasasDecimais = 0 then
                                    begin
                                          VrAuxili :=
                                              IntToStr(TBrvEditNum(DsOrigem).BrAsInteger);
                                    end else
                                    begin
                                          VrAuxili :=
                                              FloatToStr(TBrvEditNum(DsOrigem).BrAsFloat);
                                    end;

                                    DsWhere := DsWhere +
                                               DsValor + ' = "' + VrAuxili + '"';

                              end
                              else if DsOrigem is TEdit then
                                   begin
                                         if DsWhere <> '' then
                                         begin
                                               DsWhere := DsWhere + ' and ';
                                         end;
                                         DsWhere := DsWhere + DsValor + ' = "' +
                                                               TEdit(DsOrigem).Text + '"';
                                   end
                              else if DsOrigem is TMaskEdit then
                              begin
                                    if DsWhere <> '' then
                                    begin
                                          DsWhere := DsWhere + ' and ';
                                    end;
                                    DsWhere := DsWhere + DsValor + ' = "' +
                                                           TMaskEdit(DsOrigem).Text + '"';
                              end
                              else if DsOrigem is TLabel then
                              begin
                                    if DsWhere <> '' then
                                    begin
                                          DsWhere := DsWhere + ' and ';
                                    end;
                                    DsWhere := DsWhere + DsValor + ' = "' +
                                                           TLabel(DsOrigem).Caption + '"';
                              end;
                        end;
                  end;
            end;

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =- Substituindo a descrição da instrução WHERE -=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if DsWhere <> '' then
            begin
                  DsSelect := SubstituirInstrucaoWhere(DsWhere);
            end else
            begin
                  DsSelect := Trim(
                            FDsDicion.EncontrarInstrucaoSQLConsulta(FCdQuery));
            end;

            DsSelect := FDsDicion.SubstituirParametrosSQL(DsSelect, fDsParams);

            CcConAux := TBrvClientDataSet.Create(Self);
            CcConAux.BrDicionario := FDsDicion;
            try
                CcConAux.Data := FDsDicion.RetornaDadosSqlFixa(DsSelect);

                if CcConAux.RecordCount > 1 then
                begin
                      ChamarConsulta(CcConAux);
                end else
                begin
                      CarregarRetornoConsulta(CcConAux);

                      if (BrDataSource <> nil) and
                                     (not (BrDataSource.State in [dsEdit, dsInsert])) then
                      begin
                            BrDataSource.DataSet.Edit;
                      end;

                      ExibirRetornoConsulta;
                end;

            finally
                CcConAux.Close;
                FreeAndNil(CcConAux);
                FDsParams.Clear;
            end;
      end;
      BrCarregarValoresAnteriores;
end;

function TBrvConsulta.SubstituirInstrucaoWhere(DsWhere : String) : String;
var DsSql : String;
begin
      DsSql := Trim(UpperCase(FDsDicion.EncontrarInstrucaoSQLConsulta(FCdQuery)));

      if Pos('WHERE', DsSql) > 0 then
      begin
            DsWhere := 'AND ' + DsWhere;
      end else
      begin
            DsWhere := 'WHERE ' + DsWhere;
      end;

      if Pos('GROUP BY', DsSql) > 0 then
      begin
            DsWhere := DsWhere + ' GROUP BY';
            DsSql   := StringReplace(DsSql, 'GROUP BY', DsWhere , []);
      end else
      begin
            if Pos('ORDER BY', DsSql) > 0 then
            begin
                  DsWhere := DsWhere + ' ORDER BY';
                  DsSql   := StringReplace(DsSql, 'ORDER BY', DsWhere, []);
            end else
            begin
                  DsSql   := DsSql + ' ' + DsWhere;
            end;
      end;

      Result := DsSql;
end;
procedure TBrvConsulta.CarregarRetornoConsulta(pDsAuxCon : TBrvClientDataSet);
var lNrLinha   : Integer;
    lNrCampos  : Integer;
    lNmColuna  : String;
    lDsRetorn  : String;
    lBrvStrCam : TBrvString;
    lBrvStrCol : TBrvString;
    lDsSepara  : String;
begin
      try
           lBrvStrCam := TBrvString.Create(Self);
           lBrvStrCol := TBrvString.Create(Self);
           FDsReturn.Clear;

           for lNrLinha := 0 to FDsConfig.Count - 1 do
           begin
                 lNmColuna := FDsConfig.Strings[lNrLinha];
                 Delete(lNmColuna, 1, Pos(';', lNmColuna));
                 Delete(lNmColuna, Pos(';', lNmColuna), Length(lNmColuna));
                 lNmColuna := Trim(lNmColuna);

                 if Pos('@', lNmColuna) > 0 then
                 begin
                       lDsRetorn := '';
                       lBrvStrCam.Split(lNmColuna, '#');

                       for lNrCampos := 0 to lBrvStrCam.BrSplitLista.Count -1 do
                       begin
                             lBrvStrCol.Split(
                                         lBrvStrCam.BrSplitLista.Strings[lNrCampos], '@');

                             lNmColuna := lBrvStrCol.BrSplitLista.Strings[0];

                             if lDsRetorn <> '' then
                             begin
                                   lDsSepara := lBrvStrCol.BrSplitLista.Strings[1];
                                   lDsSepara := StringReplace(lDsSepara, '{', '',
                                                                         [rfReplaceAll]);
                                   lDsSepara := StringReplace(lDsSepara, '}', '',
                                                                         [rfReplaceAll]);
                                   lDsRetorn := lDsRetorn + lDsSepara;
                             end;

                             if pDsAuxCon.FindField(lNmColuna) <> nil then
                             begin
                                   lDsRetorn := lDsRetorn +
                                                pDsAuxCon.FieldByName(lNmColuna).AsString;
                             end else
                             begin
                                   lDsRetorn := lDsRetorn + lNmColuna;
                             end;
                       end;

                       FDsReturn.Add(lDsRetorn);
                 end else
                 begin
                       if pDsAuxCon.FindField(lNmColuna) <> nil then
                       begin
                             FDsReturn.Add(pDsAuxCon.FieldByName(lNmColuna).AsString);
                       end else
                       begin
                             FDsReturn.Add('Erro: ' + lNmColuna);
                       end;
                 end;
           end;
      finally
           FreeAndNil(lBrvStrCam);
           FreeAndNil(lBrvStrCol);
      end;
end;

procedure TBrvConsulta.ExibirRetornoConsulta;
var DsParent : TComponent;
    DsOrigem : TComponent;
    NrLinha  : Integer;
    DsReturn : String;
    DsValor  : String;
    SnAltera : String;
    SnChave  : String;
begin
      FDsRetOld := FDsReturn;
      DsParent  := Owner;

      for NrLinha := 0 to FDsConfig.Count - 1 do
      begin
            ExtrairDadosLinha(FDsConfig.Strings[NrLinha], DsReturn, DsValor,
                                                                       SnAltera, SnChave);

            if (BrDataSource <> nil) and
               (BrDataSource.DataSet.FindField(DsReturn) <> nil) then
            begin
                  if ((BrDataSource.DataSet.Fields.FieldByName(DsReturn).Text = '') or

                      (SnAltera = 'S')) and
                     (not BrDataSource.DataSet.Fields.FieldByName(DsReturn).ReadOnly) then
                  begin
                        BrDataSource.DataSet.Fields.FieldByName(DsReturn).AsString :=
                                                               FDsReturn.Strings[NrLinha];
                  end;
            end else
            begin
                  DsOrigem := DsParent.FindComponent(DsReturn);

                  if DsOrigem is TEdit then
                  begin
                       if (TEdit(DsOrigem).Text = '') or (SnAltera = 'S') then
                       begin
                             TEdit(DsOrigem).Text := FDsReturn.Strings[NrLinha];
                       end
                  end
                  else if DsOrigem is TLabel then
                       begin
                             if (TLabel(DsOrigem).Caption = '') or (SnAltera = 'S') then
                             begin
                                   TLabel(DsOrigem).Caption := FDsReturn.Strings[NrLinha];
                             end;
                       end
                  else if DsOrigem is TMaskEdit then
                       begin
                             if (TMaskEdit(DsOrigem).Text = '') or (SnAltera = 'S') then
                             begin
                                   TMaskEdit(DsOrigem).Text := FDsReturn.Strings[NrLinha];
                              end;
                       end
                  else if DsOrigem is TMemo then
                       begin
                             if (TMemo(DsOrigem).Text = '') or (SnAltera = 'S') then
                             begin
                                   TMemo(DsOrigem).Lines.Text := FDsReturn.Strings[NrLinha];
                             end;
                       end;
            end;
      end;
end;

procedure TBrvConsulta.CriarBrParams;
var DsSql    : String;
    NmField  : String;
    NrPosIni : Integer;
    NrPosFim : Integer;
begin
      if FDataSource <> nil then
      begin
//            FDsParams.Clear;
            DsSql := FDsDicion.EncontrarInstrucaoSQLConsulta(FCdQuery);

            NrPosIni := Pos('<%', DsSql);
            NrPosFim := Pos('%>', DsSql);

            while NrPosIni > 0 do
            begin
                  NmField := Copy(DsSql, NrPosIni + 2, NrPosFim - NrPosIni - 2);
                  Delete(DsSql, 1, NrPosFim + 1);

                  if FDataSource.DataSet.FindField(NmField) <> nil then
                  begin
                        FDsParams.Add('<%' + NmField + '%>;"' +
                             FDataSource.DataSet.FieldByName(NmField).AsString + '"');
                  end;

                  NrPosIni := Pos('<%', DsSql);
                  NrPosFim := Pos('%>', DsSql);
            end;
      end;
end;

function  TBrvConsulta.OcorreramMudanca : Boolean;
var DsOrigem : TComponent;
    DsParent : TComponent;
    NrLinha  : Integer;

    DsReturn : String;
    DsValor  : String;
    SnAltera : String;
    SnChave  : String;

    DsBookMa : TBookmark;
begin
      Result := False;

      if FDsRetOld.Count = 0 then
      begin
            Result := True;
      end else
      begin
            if (not Result) and (FDataSource <> nil) and (FDataSource.DataSet <> nil) then
            begin
                  DsBookMa := FDataSource.DataSet.GetBookmark;

                  if {(FDataSource.DataSet.CompareBookmarks(DsBookOl, DsBookMa) <> 0) or}
                     (DsBookOl = nil)                                                then
                  begin
//                        CarregarValoresAnteriores;
                        Result := True;
                  end;
            end;

            for NrLinha := 0 to FDsConfig.Count - 1 do
            begin
                  ExtrairDadosLinha(FDsConfig.Strings[NrLinha], DsReturn, DsValor,
                                                                       SnAltera, SnChave);

                  if SnChave = 'S' then
                  begin
                        if (BrDataSource <> nil) and
                           (BrDataSource.DataSet.FindField(DsReturn) <> nil) then
                        begin
                              if BrDataSource.DataSet.FindField(DsReturn).AsString <>
                                                           FDsRetOld.Strings[NrLinha] then
                              begin
                                    Result := True;
                              end;
                        end else
                        begin
                              DsParent := Owner;
                              DsOrigem := DsParent.FindComponent(DsReturn);

                              if (DsOrigem is TEdit)                                   and
                                 (TEdit(DsOrigem).Text <> FDsRetOld.Strings[NrLinha]) then
                              begin
                                    Result := True;
                              end else
                              begin
                                    if (DsOrigem is TLabel)                            and
                                       (TLabel(DsOrigem).Caption <>
                                                          FDsRetOld.Strings[NrLinha]) then
                                     begin
                                           Result := True;
                                     end else
                                     begin
                                           if (DsOrigem is TMaskEdit) and
                                              (TMaskEdit(DsOrigem).Text <>
                                                          FDsRetOld.Strings[NrLinha]) then
                                           begin
                                                 Result := True;
                                           end;
                                     end;
                              end;
                        end;
                  end;
            end;

{                        Result   := True;

            CompareBookmarks

{            if (not Result)                 and (FDataSource <> nil) and
               (FDataSource.DataSet <> nil) and (FDataSource.DataSet.State = dsInsert) then
            begin
                  Result   := True;
            end;}
      end;
end;

procedure TBrvConsulta.ExtrairDadosLinha(DsLinha : String;
                                      var DsReturn : String; var DsValor : String;
                                      var SnAltera : String; var SnChave : String);
begin
      DsLinha := UpperCase(DsLinha);

      DsReturn := Copy(DsLinha, 1, Pos(';', DsLinha) - 1);
      Delete(DsLinha, 1, Pos(';', DsLinha));

      DsValor  := Copy(DsLinha, 1, Pos(';', DsLinha) - 1);
      Delete(DsLinha, 1, Pos(';', DsLinha));

      SnAltera := Copy(DsLinha, 1, Pos(';', DsLinha) - 1);
      Delete(DsLinha, 1, Pos(';', DsLinha));

      SnChave  := Copy(DsLinha, 1, Pos(';', DsLinha) - 1);
      Delete(DsLinha, 1, Pos(';', DsLinha));

      DsReturn := Trim(DsReturn);
      SnAltera := Trim(SnAltera);
      DsValor  := Trim(DsValor);
      SnChave  := Trim(SnChave);
end;

procedure TBrvConsulta.ChamarConsulta(DataSet : TDataSet);
var DsReturn : String;
    DsValor  : String;
    SnAltera : String;
    SnChave  : String;

    DsSelect : String;
    DsAlias  : String;
    DsComWhe : String;

    NrLinha  : Byte;
    NrAlias  : Byte;
begin
      DsSelect := Trim(
                 UpperCase(FDsDicion.EncontrarInstrucaoSQLConsulta(FCdQuery)));

      Delete(DsSelect, Pos('SELECT', DsSelect), 6);
      Delete(DsSelect, Pos('FROM', DsSelect), Length(DsSelect));
      DsSelect   := Trim(DsSelect);

      DsComWhe := '';

      for NrLinha := 0 to FDsConfig.Count - 1 do
      begin
            ExtrairDadosLinha(FDsConfig.Strings[NrLinha], DsReturn, DsValor,
                                                          SnAltera, SnChave);
            DsAlias := '';

            if SnChave = 'S' then
            begin
                  if (DsSelect <> '') and
                     (DsSelect[Pos(DsValor, DsSelect) - 1] = '.') then
                  begin
                        NrAlias := Pos(DsValor, DsSelect) -1;

                        while (NrAlias > 0) and
                              (DsSelect[NrAlias] <> ' ') and
                              (DsSelect[NrAlias] <> ',') do
                        begin
                              DsAlias := DsSelect[NrAlias] + DsAlias;
                              Dec(NrAlias);
                        end;
                  end else
                  begin
                        if (DsSelect <> '') and
                           (DsSelect[Pos('*', DsSelect) - 1] = '.') then
                        begin
                              NrAlias := Pos('*', DsSelect) -1;

                              while (NrAlias > 0) and
                                    (DsSelect[NrAlias] <> ' ') and
                                    (DsSelect[NrAlias] <> ',') do
                              begin
                                    DsAlias := DsSelect[NrAlias] + DsAlias;
                                    Dec(NrAlias);
                              end;
                        end;
                  end;

                  if DataSet.FieldByName(DsValor) is TIntegerField then
                  begin
                        if Trim(DsComWhe) = '' then
                        begin
                              DsComWhe := DsComWhe + DsAlias + DsValor + ' = ' +
                                         IntToStr(DataSet.FieldByName(DsValor).AsInteger);
                        end else
                        begin
                              DsComWhe := DsComWhe + ' and ' + DsAlias + DsValor + ' = ' +
                                         IntToStr(DataSet.FieldByName(DsValor).AsInteger);
                        end;
                  end else
                  begin
                        if (DataSet.FieldByName(DsValor) is TDateField)      or
                           (DataSet.FieldByName(DsValor) is TDateTimeField) then
                        begin
                              if Trim(DsComWhe) = '' then
                              begin
                                    DsComWhe := DsComWhe + DsAlias + DsValor + '= "' +
                                           FormatDateTime(BrDicionario.DateSql,
                                           DataSet.FieldByName(DsValor).AsDateTime) + '"';
                              end else
                              begin
                                    DsComWhe := DsComWhe + ' and ' + DsAlias +
                                           DsValor + '= "' +
                                           FormatDateTime(BrDicionario.DateSql,
                                           DataSet.FieldByName(DsValor).AsDateTime) + '"';
                              end;
                        end else
                        begin
                              if Trim(DsComWhe) = '' then
                              begin
                                    DsComWhe := DsComWhe + DsAlias + DsValor   + '= "' +
                                         DataSet.FieldByName(DsValor).AsString + '"';
                              end else
                              begin
                                    DsComWhe := DsComWhe + ' and ' + DsAlias + DsValor +
                                      '= "' + DataSet.FieldByName(DsValor).AsString + '"';
                              end;
                        end;
                  end;
            end;
      end;

      BrExecute(DsComWhe);
end;

procedure TBrvConsulta.BrCarregarValoresAnteriores;
var NmColuna : String;
    SnVarAux : String;
    SnChave  : String;
    NrLinha  : Byte;

    DsValAux : Variant;
begin
      if (BrDataSource <> nil) and (BrDataSource.DataSet <> nil) then
      begin
            FDsRetOld.Clear;

            for NrLinha := 0 to FDsConfig.Count - 1 do
            begin
                  ExtrairDadosLinha(FDsConfig.Strings[NrLinha], NmColuna, SnVarAux,
                                                                SnVarAux, SnChave);

                  if (SnChave = 'S')                                     and
                     (BrDataSource.DataSet.FindField(NmColuna)   <> nil)   then
                  begin
                        DsValAux := BrDataSource.DataSet.FieldByName(NmColuna).Value;

                        if not VarIsNull(DsValAux) then
                        begin
                              if (BrDataSource.DataSet.FieldByName(NmColuna) is
                                                                       TNumericField) then
                              begin
                                    FDsRetOld.Add(FloatToStr(DsValAux));
                              end else
                              begin
                                    if BrDataSource.DataSet.FieldByName(NmColuna) is
                                                                       TDateTimeField then
                                    begin
                                          FDsRetOld.Add(DateToStr(DsValAux));
                                    end else
                                    begin
                                          FDsRetOld.Add(DsValAux);
                                    end;
                              end;
                        end else
                        begin
                              FDsRetOld.Add('0');
                        end;
                  end else
                  begin
                        FDsRetOld.Add('0');
                  end;
            end;
      end;
end;

end.

