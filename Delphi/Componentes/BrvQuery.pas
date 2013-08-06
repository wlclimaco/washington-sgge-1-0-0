unit BrvQuery;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, Db, DBTables,
  Bde, BrvDicionario, BrvString, StdCtrls;

type
  TVqType = (VqNormal, VqSearch);
  TBrvQuery = class(TQuery)
  private
    { Private declarations }
    FSnShoFie   : Boolean;
    FCdQuery    : Integer;
    FDsParams   : TStrings;
    FTpQuery    : TVqType;
    FDsDicion   : TBrvDicionario;
    FAddFields  : TDataSetNotifyEvent;
    procedure   SetParametros(Value: TStrings);
    procedure   SubstituirParametros;
    function    ExcluirExpressaoParametro(DsSql : String; DsConteu : String) : String;
    function    ExcluirExpressaoParametroParenteses(DsSql : String;
                                                 NrPosPar : Integer) : String;
    function    ExcluirExpressaoParametroWhere(DsSql : String; NrPosFim : Integer;
                                          NrPosAnt : Integer; DsAnteri : String) : String;
    procedure   TraduzirInstrucaoSQL(NmDriver : String);
    procedure   TraduzirSqlParaOracle;
    procedure   TraduzirSqlParaInterbase;
    procedure   CarregarTabelas(var StlTabela : TStrings);
    procedure   CarregarAtributos(var StlAtribu : TStrings; StlTabela : TStrings);
    procedure   ProximaPalavra(var DsFrom : String; var DsPalavr : String);
    function    AliasDoAtributo(StlAtribu : TStrings; NmAtribu : String) : String;
    function    TabelaDoAlias(StlTabela : TStrings; NmAlias : String) : String;
    procedure   VisualizarInstrucaoSQL;
    procedure   SetTipoQuery(InTpQuery : TVqType);
    procedure   TMemoFieldGetText(Sender: TField; var Text: String; DisplayText: Boolean);

  protected
    { Protected declarations }
    function    CreateHandle: HDBICur; override;
    procedure   DoAfterOpen; override;
    procedure   CreateFields; override;
  public
    { Public declarations }
    procedure   ExecuteSQL(NmDriver : String);
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
  published
    { Published declarations }
    property BrShowFieldName  : Boolean  read FSnShoFie write FSnShoFie;
    property BrQueryCode    : Integer  read FCdQuery  write FCdQuery;
    property BrParams       : TStrings read FDsParams write SetParametros;
    property BrDicionario   : TBrvDicionario
                                       read FDsDicion write FDsDicion;
    property BrOnAddFields  : TDataSetNotifyEvent read FAddFields write FAddFields;
    property BrType         : TVqType  read FTpQuery  write SetTipoQuery;

  end;

procedure Register;

implementation

uses BrvOracle, BrvInterbase;

constructor TBrvQuery.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
      FDsParams := TStringList.Create;
      FSnShoFie := False;
      FTpQuery  := VqNormal;
end;

destructor TBrvQuery.Destroy;
begin
      FDsParams.Destroy;
      inherited  destroy;
end;

procedure TBrvQuery.SetParametros(Value: TStrings);
begin
      FDsParams.Assign(Value);
end;

procedure TBrvQuery.SetTipoQuery(InTpQuery : TVqType);
begin
      FTpQuery := InTpQuery;
end;

function TBrvQuery.CreateHandle: HDBICur;
begin
      TraduzirInstrucaoSQL(Database.DriverName);

      if CachedUpdates and (UpdateObject = Nil) then
      begin
//            raise Exception.Create('Ocorreu erro ao tentar abrir a query "' +
//                                   UpperCase(Name) + #13 +
//                                   'Não foi encontrado Update Object...');
      end;

      try
           Result := inherited CreateHandle;
      except
           if  not (csDesigning in ComponentState) then
           begin
                 if FTpQuery = VqNormal then
                 begin
                       if MessageDlg('Erro na tentativa de abrir: ' + #13#13 +
                                     'Query: "'  + Self.Name + '"'  + #13 +
                                     'Módulo: "' + Owner.Name + '"' + #13 +
                                     'Código: "' + IntToStr(BrQueryCode) + '"' +
                                     #13#13 + 'Visualizar Instrução SQL?',
                                     MtError, [MbYes, MbNo], 0) = IdYes then
                       begin
                             VisualizarInstrucaoSQL;
                       end;
                 end else
                 begin
                       if MessageDlg('Erro na tentativa de abrir query de ' +
                                     'consulta: ' + #13#13 +
                                     'Código: "' + IntToStr(Tag) + '"' +
                                     #13#13 + 'Visualizar Instrução SQL?',
                                     MtError, [MbYes, MbNo], 0) = IdYes then
                       begin
                             VisualizarInstrucaoSQL;
                       end;
                 end;
           end;

           raise;
      end;
end;

procedure TBrvQuery.VisualizarInstrucaoSQL;
var FrmMemo     : TForm;
    DbMemo      : TMemo;
begin
      FrmMemo                 := TForm.CreateNew(Self);
      FrmMemo.Position        := poScreenCenter;
      FrmMemo.Width           := 550;
      FrmMemo.Height          := 340;
      FrmMemo.BorderStyle     := bsSizeable;
      FrmMemo.BorderIcons     := [biSystemMenu,biMaximize];
      FrmMemo.Caption         := 'Instrução SQL';

      DbMemo                  := TMemo.Create(Self);
      DbMemo.Parent           := FrmMemo;
      DbMemo.Align            := alClient;
      DbMemo.Lines.Text       := Sql.Text;
      DbMemo.ReadOnly         := True;
      DbMemo.ScrollBars       := ssBoth;
      DbMemo.TabOrder         := 0;
      DbMemo.Font.Name        := 'Courier New';

      FrmMemo.ShowModal;
      FrmMemo.Destroy;
end;

procedure TBrvQuery.CreateFields;
begin
      inherited CreateFields;

      if Assigned(FAddFields) then
         FAddFields(Self);
end;

procedure TBrvQuery.DoAfterOpen;
var NrCampo   : Integer;
    NmAlias   : String;
    NmTabela  : String;
    DsDisLab  : String;
    DsDisFor  : String;
    TpMascar  : String;
    DsMascar  : String;
    DsHelp    : String;
    DsHint    : String;
    NmField   : String;
    TpCampo   : String;
    CdPermis  : Integer;
    VrDomini  : String;
    DsDomini  : String;
    TmAtribu  : Integer;
    StlTabela : TStrings;
    StlAtribu : TStrings;
    SnKey    : Boolean;
begin
      StlTabela := TStringList.Create;
      StlAtribu := TStringList.Create;

      CarregarTabelas(StlTabela);
      CarregarAtributos(StlAtribu, StlTabela);

      for NrCampo := 0 to Fields.Count -1 do
      begin
            if FDsDicion <> nil then
            begin
                  DsDisLab := '';
                  DsDisFor := '';
                  NmField  := Fields[NrCampo].FieldName;

                  if Pos('_', NmField) > 0 then
                  begin
                        System.Delete(NmField, 1, Pos('_', NmField));
                  end;

                  NmAlias  := AliasDoAtributo(StlAtribu, NmField);
                  NmTabela := TabelaDoAlias(StlTabela, NmAlias);

                  FDsDicion.AtributoConfiguracao(NmTabela, NmField,  DsDisLab,
                                                 TpCampo,  TpMascar, DsMascar,
                                                 DsHelp,   DsHint,   TmAtribu,
                                                 CdPermis, VrDomini, DsDomini,
                                                 SnKey,    DsDisFor);
                  if not FSnShoFie then
                  begin
                        Fields[NrCampo].DisplayLabel := DsDisLab;
                  end;

                  Fields[NrCampo].EditMask     := DsMascar;

                  if (Fields[NrCampo] is TIntegerField) then
                  begin
                        TIntegerField(Fields[NrCampo]).DisplayFormat := DsDisFor;
                        Fields[NrCampo].Alignment    := taLeftJustify;
                  end else
                  if (Fields[NrCampo] is TFloatField) then
                  begin
                        TFloatField(Fields[NrCampo]).DisplayFormat := DsDisFor;
                        Fields[NrCampo].Alignment    := taLeftJustify;
                  end else
                  if (Fields[NrCampo] is TCurrencyField) then
                  begin
                        TCurrencyField(Fields[NrCampo]).DisplayFormat := DsDisFor;
                        Fields[NrCampo].Alignment    := taLeftJustify;
                  end else
                  if (Fields[NrCampo] is TBcdField) then
                  begin
                        TBcdField(Fields[NrCampo]).DisplayFormat := DsDisFor;
                        Fields[NrCampo].Alignment    := taLeftJustify;
                  end else
                  if (Fields[NrCampo] is TStringField) then
                  begin
                        case TpMascar[1] of
                             'M': Fields[NrCampo].EditMask := '>' + // maiusculo
                                     StringOfChar('c',
                                         fields[NrCampo].DisplayWidth) + ';0; ';
                             'm': Fields[NrCampo].EditMask := '<' + // minusculo
                                     StringOfChar('c',
                                         fields[NrCampo].DisplayWidth) + ';0; ';
                             'D': Fields[NrCampo].EditMask := DsMascar;
                             'N':; // assumir Display format do atributo
                        end;
                  end;
            end;

            if Fields[NrCampo] is TMemoField then
            begin
                  if  Database.DriverName = 'ORACLE' then
                  begin
                        TMemoField(Fields[NrCampo]).BlobType := ftMemo;
                  end;
                  //enio - mostrar memo na grid.
                  Fields[NrCampo].OnGetText := TMemoFieldGetText;
            end;
      end;

      StlTabela.Destroy;
      StlAtribu.Destroy;

      inherited DoAfterOpen;
end;

function  TBrvQuery.TabelaDoAlias(StlTabela : TStrings; NmAlias : String) : String;
var NrTabela : Integer;
    NrPosPon : Integer;
    NmAliAux : String;
    NmTabela : String;
begin
      Result := '';

      if  NmAlias = '' then
      begin
            Result := Trim(Copy(StlTabela.Strings[0], 1, 15));
      end else
      begin
            NrTabela := 0;

            while NrTabela < StlTabela.Count do
            begin
                  NrPosPon := Pos(';', StlTabela.Strings[NrTabela]);
                  NmAliAux := Copy(StlTabela.Strings[NrTabela], NrPosPon + 1,
                                           Length(StlTabela.Strings[NrTabela]));
                  NmTabela := Copy(StlTabela.Strings[NrTabela], 1, NrPosPon -1);

                  if NmAliAux = NmAlias then
                  begin
                        Result   := Trim(NmTabela);
                        NrTabela := StlTabela.Count; 
                  end;

                  Inc(NrTabela);
            end;
      end;
end;

function  TBrvQuery.AliasDoAtributo(StlAtribu : TStrings; NmAtribu : String) : String;
var NrCampo  : Integer;
    NrPosPon : Integer;
    NmAlias  : String;
    NmCampo  : String;
begin
      Result  := '';
      NrCampo := 0;

      while NrCampo < StlAtribu.Count do
      begin
            NrPosPon := Pos('.', StlAtribu.Strings[NrCampo]);

            if NrPosPon > 0 then
            begin
                  NmAlias := Copy(StlAtribu.Strings[NrCampo], 1, NrPosPon -1);
                  NmCampo := Copy(StlAtribu.Strings[NrCampo], NrPosPon + 1,
                                  Length(StlAtribu.Strings[NrCampo]));
                  NmCampo := Trim(NmCampo);

                  if  NmCampo = NmAtribu then
                  begin
                        Result   := NmAlias;
                        NrCampo  := StlAtribu.Count;
                  end;
            end else
            begin
                  NrCampo  := StlAtribu.Count;
            end;

            Inc(NrCampo);
      end;
end;

procedure TBrvQuery.CarregarTabelas(var StlTabela : TStrings);
var DsFrom   : String;
    StlSelect: TStrings;
    NrSelect : Integer;
    DsSql    : String;
    NmTabela : String;
    DsPalavr : String;
    NmTabAux : String;
    NmAlias  : String;
    lBrvStr  : TBrvString;
begin
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-  Carregando nome das tabelas   =-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      StlSelect := TStringList.Create;

      DsSql  := Trim(UpperCase(Sql.Text));

      while DsSql <> '' do
      begin
            System.Delete(DsSql, 1, Pos('FROM', DsSql) + 4);

            if Pos('FROM', DsSql) <> 0 then
            begin
                  StlSelect.Add(Copy(DsSql, 1, Pos('FROM', DsSql)));
                  System.Delete(DsSql, 1, Pos('FROM', DsSql) - 1);
            end else
            begin
                  StlSelect.Add(DsSql);
                  DsSql := '';
            end;
      end;

      for NrSelect := 0 to StlSelect.Count -1 do
      begin
            DsFrom := StlSelect.Strings[NrSelect];

            if  Pos('WHERE', DsFrom) > 0 then
            begin
                  System.Delete(DsFrom, Pos('WHERE', DsFrom), Length(DsFrom));
            end;

            if  Pos('ORDER', DsFrom) > 0 then
            begin
                  System.Delete(DsFrom, Pos('ORDER', DsFrom), Length(DsFrom));
            end;

            if  Pos('GROUP', DsFrom) > 0 then
            begin
                  System.Delete(DsFrom, Pos('GROUP', DsFrom), Length(DsFrom));
            end;

            while DsFrom <> '' do
            begin
                  NmTabela := '';
                  repeat
                         ProximaPalavra(DsFrom, DsPalavr);

                         if (DsPalavr <> 'LEFT') and (DsPalavr <> 'RIGHT') and
                            (DsPalavr <> 'JOIN') and (DsPalavr <> ',') then
                         begin
                               if  DsPalavr = 'ON' then
                               begin
                                     repeat
                                            ProximaPalavra(DsFrom, DsPalavr);
                                     until (DsPalavr = 'JOIN') or (DsFrom = '');
                               end else
                               begin
                                     if  DsPalavr = '' then
                                     begin
                                           NmTabela := NmTabela + ' ';
                                     end else
                                     begin
                                           NmTabela := NmTabela + DsPalavr;
                                     end;
                               end;
                         end;
                  until (DsPalavr = 'LEFT') or (DsPalavr = 'RIGHT') or
                        (DsPalavr = 'JOIN') or (DsPalavr = ',')     or
                        (DsFrom   = '');

                  NmTabela := Trim(NmTabela);

                  if NmTabela <> '' then
                  begin
                        if  Pos(' ', NmTabela) > 0 then
                        begin
                              NmTabAux := Copy(NmTabela, 1, Pos(' ', NmTabela) -1);
                              System.Delete(NmTabela, 1, Pos(' ', NmTabela));
                              NmAlias  := Trim(NmTabela);
                        end else
                        begin
                              NmAlias  := '';
                              NmTabAux := NmTabela;
                        end;

                        try
                             lBrvStr := TBrvString.Create(self);
                             StlTabela.Add(lBrvStr.FormatarStringSemAcento(NmTabAux, 15) +
                                                                           ';' + NmAlias);
                        finally
                             FreeAndNil(lBrvStr);
                        end;
                  end;
            end;
      end;

      StlSelect.Destroy;
end;

procedure TBrvQuery.ProximaPalavra(var DsFrom : String; var DsPalavr : String);
begin
      DsPalavr := '';

      case DsFrom[1] of
           ' ': begin
                      while (DsFrom <> '') and (DsFrom[1] = ' ') do
                      begin
                            DsPalavr := DsPalavr + DsFrom[1];
                            System.Delete(DsFrom, 1, 1);
                      end;
                end;
           ',': begin
                      DsPalavr := DsFrom[1];
                      System.Delete(DsFrom, 1, 1);
                end;
       #10,#13: begin
                      while (DsFrom    <> '')  and
                            ((DsFrom[1] = #10)  or (DsFrom[1] = #13)) do
                      begin
                            DsPalavr := DsPalavr + DsFrom[1];
                            System.Delete(DsFrom, 1, 1);
                      end;
                end;
           else begin
                      while (DsFrom    <> '')  and (DsFrom[1] <> ' ') and
                            (DsFrom[1] <> ',') and (DsFrom[1] <> #10) and
                            (DsFrom[1] <> #13) do
                      begin
                           DsPalavr := DsPalavr + DsFrom[1];
                           System.Delete(DsFrom, 1, 1);
                      end;
                end;
      end;
end;

procedure TBrvQuery.CarregarAtributos(var StlAtribu : TStrings; StlTabela : TStrings);
var DsSelect : String;
    NrPosVir : Integer;
    NrPosEsp : Integer;
    NrPosAst : Integer;
    NrTabela : Integer;
    NrColuna : Integer;
    NmAtribu : String;
    NmTabela : String;
    NmAlias  : String;
    NmAliAux : String;
    StlColAux: TStrings;
begin
      StlAtribu.Clear;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=  Carregando os atributos da select -=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      DsSelect := Trim(UpperCase(Sql.Text));
      System.Delete(DsSelect, Pos('SELECT', DsSelect), 6);
      System.Delete(DsSelect, Pos('FROM', DsSelect), Length(DsSelect));

      while DsSelect <> '' do
      begin
            NrPosVir := Pos(',', DsSelect);

            if NrPosVir > 0 then
            begin
                  NmAtribu := Copy(DsSelect, 1, NrPosVir -1);
                  System.Delete(DsSelect, 1, NrPosVir);
            end else
            begin
                  NmAtribu := DsSelect;
                  DsSelect := '';
            end;

            NmAtribu := Trim(NmAtribu);

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=  Verificanco a existencia do AS =-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            NrPosEsp := Pos(' ', NmAtribu);

            if NrPosEsp > 0 then
            begin
                  NmAtribu := Trim(Copy(NmAtribu, 1, NrPosEsp));
            end;

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=  Verificanco a existencia do * -=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            NrPosAst := Pos('*', NmAtribu);

            if NrPosAst > 0 then
            begin
                  NrPosVir := Pos('.', NmAtribu);

                  if NrPosVir > 0 then
                  begin
                        NmAlias  := Copy(NmAtribu, 1, NrPosVir -1);
                        NrTabela := 0;

                        while NrTabela < StlTabela.Count do
                        begin
                              NmAliAux := Copy(StlTabela.Strings[NrTabela], 17,
                                                     Length(StlTabela.Strings[NrTabela]));

                              if  NmAliAux = NmAlias then
                              begin
                                    NmTabela := Copy(StlTabela.Strings[NrTabela], 1, 15);
                                    NrTabela := StlTabela.Count;
                              end;

                              Inc(NrTabela);
                        end;
                  end else
                  begin
                        NmAlias  := '';
                        NmTabela := Copy(StlTabela.Strings[0], 1, 15);
                  end;

                  NmTabela  := Trim(NmTabela);
                  StlColAux := TStringList.Create;
                  StlColAux.Clear;

                  if FDsDicion = nil then
                  begin
                        MessageDlg('Não foi possível determinar o dicionário ' +
                                   'de dados.' + #13#13 + 'Query: ' + Name     +
                                   #13 + 'Módulo: ' + Owner.Name,
                                   MtError, [MbOk], 0);
                  end else
                  begin
                        FDsDicion.AtributosDaTabela(NmTabela, StlColAux);
                  end;

                  for NrColuna := 0 to StlColAux.Count -1 do
                  begin
                        if NmAlias <> '' then
                        begin
                              StlAtribu.Add(NmAlias + '.' + StlColAux.Strings[NrColuna]);
                        end else
                        begin
                              StlAtribu.Add(StlColAux.Strings[NrColuna]);
                        end;
                  end;
            end else
            begin
                  StlAtribu.Add(NmAtribu);       
            end;
      end;
end;

procedure TBrvQuery.ExecuteSQL(NmDriver : String);
begin
      TraduzirInstrucaoSQL(NmDriver);
      ExecSql;
end;

procedure TBrvQuery.TraduzirInstrucaoSQL(NmDriver : String);
var lNmComPai : String;
begin
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=- Pesquisando no banco a select para o código -=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      if  (FCdQuery <> 0) and (not (csDesigning in ComponentState)) then
      begin
            if FDsDicion = nil then
            begin
                  MessageDlg('Não foi possível determinar o dicionário ' +
                             'de dados.' + #13#13 + 'Query: ' + Name     +
                             #13 + 'Módulo: ' + Owner.Name, MtError, [MbOk], 0);
            end else
            begin
                   if Owner <> nil then
                   begin
                         lNmComPai := Owner.Name;
                   end else
                   begin
                         lNmComPai := 'BrvQuery: pai não identificado';
                   end;


                  if FTpQuery  = VqNormal then
                  begin
                        Sql.Text := FDsDicion.EncontrarInstrucaoSQL(FCdQuery, lNmComPai);
                  end else
                  begin
                        Sql.Text :=
                            FDsDicion.EncontrarInstrucaoSQLConsulta(FCdQuery)
                  end;
            end;

      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=- Substituindo os parâmetros =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      SubstituirParametros;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=- Traduzindo a select para o banco atual =-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      if  NmDriver = 'ORACLE' then
      begin
            TraduzirSqlParaOracle;
      end else
      begin
            TraduzirSqlParaInterbase;
      end;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
end;

procedure TBrvQuery.SubstituirParametros;
var NrLinha  : Integer ;
    DsParam  : String ;
    DsConteu : String ;
    NrPosPon : Integer ;
begin
      if Pos('%>', Sql.Text) <> 0 then
      begin
            Sql.Text := StringReplace(Sql.Text, '<%EMPRESAS%>' ,
                                      FDsDicion.CorpCommaCodes, [rfReplaceAll]);
                                      
            for NrLinha := 0 to FDsParams.Count -1 do
            begin
                  NrPosPon := Pos(';', FDsParams.Strings[NrLinha]);
                  DsParam  := Copy(FDsParams.Strings[NrLinha], 1 , NrPosPon -1);
                  DsConteu := Copy(FDsParams.Strings[NrLinha], NrPosPon + 1,
                                            Length(FDsParams.Strings[NrLinha]));

                  if  DsParam = DsConteu then
                  begin
                        Sql.Text := ExcluirExpressaoParametro(Sql.Text, DsConteu);
                  end else
                  begin
                        Sql.Text := StringReplace(Sql.Text, DsParam, DsConteu,
                                                             [rfReplaceAll,rfIgnoreCase]);
                  end;
            end;
      end;
end;

function TBrvQuery.ExcluirExpressaoParametro(DsSql: String; DsConteu: String) : String;
var NrPosFim : Integer;
    NrPosAnt : Integer;
    DsAnteri : String;
begin
      NrPosFim := Pos(DsConteu, DsSql);
   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo parâmetro
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      System.Delete(DsSql, NrPosFim, Length(DsConteu));

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo espaco antes do parâmetro
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      if  (DsSql[NrPosFim] = ')') or (DsSql[NrPosFim] = '') then
      begin
           Dec(NrPosFim);
      end;

      while ((DsSql[NrPosFim] = ' ') or (DsSql[NrPosFim] = #13) or
             (DsSql[NrPosFim] = #10)) and (DsSql <> '') do
      begin
            System.Delete(DsSql, NrPosFim, 1);
            Dec(NrPosFim);
      end;
   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo operador lógico
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while (DsSql[NrPosFim] <> ' ') and (DsSql[NrPosFim] <> #13) and
            (DsSql[NrPosFim] <> #10) and (DsSql <> '') do
      begin
            System.Delete(DsSql, NrPosFim, 1);
            Dec(NrPosFim);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo espaco antes do operador lógico
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while ((DsSql[NrPosFim] = ' ') or (DsSql[NrPosFim] = #13) or
             (DsSql[NrPosFim] = #10)) and (DsSql <> '') do
      begin
            System.Delete(DsSql, NrPosFim, 1);
            Dec(NrPosFim);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo coluna de comparação
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while (DsSql[NrPosFim] <> ' ') and (DsSql[NrPosFim] <> #13) and
            (DsSql[NrPosFim] <> #10) and (DsSql[NrPosFim] <> '(')  and
            (DsSql <> '') do
      begin
            System.Delete(DsSql, NrPosFim, 1);
            Dec(NrPosFim);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo espaco antes da coluna de comparação
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while ((DsSql[NrPosFim] = ' ') or (DsSql[NrPosFim] = #13) or
             (DsSql[NrPosFim] = #10)) and (DsSql <> '') do
      begin
            System.Delete(DsSql, NrPosFim, 1);
            Dec(NrPosFim);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Econtrando operador antes da coluna de comparação
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      NrPosAnt := NrPosFim;
      DsAnteri := '';

      while (DsSql[NrPosAnt] <> ' ') and (DsSql[NrPosAnt] <> #13) and
            (DsSql[NrPosAnt] <> #10) and (DsSql <> '') do
      begin
            DsAnteri := DsSql[NrPosAnt] + DsAnteri;
            Dec(NrPosAnt);
      end;

      if  UpperCase(DsAnteri) <> 'WHERE' then
      begin
            if  Pos('(', DsAnteri) > 0 then
            begin
                  DsSql := ExcluirExpressaoParametroParenteses(DsSql, NrPosAnt);
            end else
            begin
               // =-=-=-=-=-=-=-=-=-=-=-=-=
               // Excluindo operador antes da coluna de comparação
               // =-=-=-=-=-=-=-=-=-=-=-=-=
                  System.Delete(DsSql, NrPosAnt + 1, Length(DsAnteri));
            end;
      end else
      begin
            DsSql := ExcluirExpressaoParametroWhere(DsSql, NrPosFim, NrPosAnt, DsAnteri);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=

      Result := DsSql;
end;

function TBrvQuery.ExcluirExpressaoParametroParenteses(DsSql    : String;
                                                       NrPosPar : Integer) : String;
var NrPosAtu : Integer;
    DsParent : String;
    DsAnteri : String;
    QtParent : Integer;
    NrPosIni : Integer;
begin
      NrPosAtu := NrPosPar + 2;
      NrPosIni := NrPosAtu;
      DsParent := '';
      QtParent := 1;

      while QtParent > 0 do
      begin
            if DsSql[NrPosAtu] = '(' then
            begin
                  Inc(QtParent);
                  DsParent := DsParent + DsSql[NrPosAtu];
            end else
            begin
                  if DsSql[NrPosAtu] = ')' then
                  begin
                        Dec(QtParent);

                        if QtParent > 0 then
                        begin
                              DsParent := DsParent + DsSql[NrPosAtu];
                        end;
                  end else
                  begin
                        DsParent := DsParent + DsSql[NrPosAtu];
                  end;
            end;
            Inc(NrPosAtu);
      end;

      DsParent := Trim(DsParent);

      if  DsParent <> '' then
      begin
            System.Delete(DsSql, NrPosIni, NrPosAtu - NrPosIni - 1);

            while (DsParent[1] <> ' ') and (DsParent[1] <> #13) and
                  (DsParent[1] <> #10) and (DsParent <> '') do
            begin
                  System.Delete(DsParent, 1, 1);
            end;

            System.Insert(DsParent, DsSql, NrPosIni);
      end else
      begin
            System.Delete(DsSql, NrPosIni - 1, NrPosAtu - NrPosIni + 1);
            NrPosIni := NrPosIni - 1 - (NrPosAtu - NrPosIni);

            while ((DsSql[NrPosIni] = ' ') or (DsSql[NrPosIni] = #13) or
                   (DsSql[NrPosIni] = #10)) and (NrPosIni > 0) do
            begin
                  System.Delete(DsSql, NrPosIni, 1);
                  Dec(NrPosIni);
            end;

            if  DsSql[NrPosIni] = '(' then
            begin
                  Inc(NrPosIni);
                  while ((DsSql[NrPosIni] = ' ') or (DsSql[NrPosIni] = #13) or
                         (DsSql[NrPosIni] = #10)) and (NrPosIni > 0) do
                  begin
                        System.Delete(DsSql, NrPosIni, 1);
                  end;

                  while (DsSql[NrPosIni] <> ' ') and (DsSql[NrPosIni] <> #13) and
                        (DsSql[NrPosIni] <> #10) and (DsSql[NrPosIni] <> ')') and
                        (NrPosIni > 0) do
                  begin
                        System.Delete(DsSql, NrPosIni, 1);
                  end;
            end else
            begin
               // =-=-=-=-=-=-=-=-=-=-=-=-=
               // Econtrando operador antes dos parenteses
               // =-=-=-=-=-=-=-=-=-=-=-=-=
                  DsAnteri := '';
                  NrPosAtu := NrPosIni;

                  while (DsSql[NrPosAtu] <> ' ') and (DsSql[NrPosAtu] <> #13) and
                        (DsSql[NrPosAtu] <> #10) and (DsSql[NrPosAtu] <> '(') and
                        (NrPosAtu > 0) do
                  begin
                        DsAnteri := Copy(DsSql, NrPosAtu, 1) + DsAnteri;
                        Dec(NrPosAtu);
                  end;

                  DsAnteri := Trim(DsAnteri);

                  if  UpperCase(DsAnteri) <> 'WHERE' then
                  begin
                        while (DsSql[NrPosIni] <> ' ') and (DsSql[NrPosIni] <> #13) and
                              (DsSql[NrPosIni] <> #10) and (DsSql[NrPosIni] <> '(') and
                              (NrPosIni > 0) do
                        begin
                              System.Delete(DsSql, NrPosIni, 1);
                              Dec(NrPosIni);
                        end;
                  end else
                  begin
                        DsSql := ExcluirExpressaoParametroWhere(DsSql,    NrPosIni + 1,
                                                                NrPosAtu, DsAnteri);
                  end;
            end;
      end;

      Result := DsSql;
end;

function TBrvQuery.ExcluirExpressaoParametroWhere(DsSql    : String; NrPosFim : Integer;
                                                    NrPosAnt : Integer;
                                                    DsAnteri : String) : String;
var NrPosPos : Integer;
    DsPoster : String;
begin
      NrPosPos := NrPosFim + 1;
      DsPoster := '';

      if  (DsSql[NrPosPos] = ' ') or (DsSql[NrPosPos] = #10) or
          (DsSql[NrPosPos] = #13) then
      begin
         // =-=-=-=-=-=-=-=-=-=-=-=-=
         // Ignorando espaços após a coluna de comparação
         // =-=-=-=-=-=-=-=-=-=-=-=-=

            while ((DsSql[NrPosPos] = ' ')  or  (DsSql[NrPosPos] = #10) or
                   (DsSql[NrPosPos] = #13)) and ((NrPosPos <= Length(DsSql))) do
            begin
                  Inc(NrPosPos);
            end;
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Encontrando operador depois da coluna de comparação
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while (DsSql[NrPosPos] <> ' ') and (DsSql[NrPosPos] <> #13) and
            (DsSql[NrPosPos] <> #10) and (NrPosPos <= Length(DsSql)) do
      begin
            DsPoster := DsPoster + DsSql[NrPosPos];
            Inc(NrPosPos);
      end;

      DsPoster := Trim(DsPoster);

      if  DsPoster = ''  then
      begin
            System.Delete(DsSql, NrPosAnt + 1, Length(DsAnteri));
      end else
      begin
            System.Delete(DsSql, NrPosFim + 1, NrPosPos - NrPosFim - 1);
      end;

      Result := DsSql;
end;

procedure TBrvQuery.TraduzirSqlParaOracle;
var DsTradut : TBrvOracle;
begin
      DsTradut := TBrvOracle.Create(Self);
      Sql.Text := DsTradut.SintaxeOracle(Sql.Text);
      DsTradut.Destroy;
end;

procedure TBrvQuery.TraduzirSqlParaInterbase;
var DsTradut : TBrvInterbase;
begin
      DsTradut := TBrvInterbase.Create(Self);
      Sql.Text := DsTradut.SintaxeInterbase(Sql.Text);
      DsTradut.Destroy;
end;

procedure TBrvQuery.TMemoFieldGetText(Sender: TField; var Text: String;
                                                     DisplayText: Boolean);
begin
      try
          Text := Copy(Sender.AsString, 1, 40);
          Text := StringReplace(Text, #10, ' ', [rfReplaceAll]);
          Text := StringReplace(Text, #13, ' ', [rfReplaceAll]);
          Text := StringReplace(Text, #0,  ' ', [rfReplaceAll]);

          Sender.Alignment := taLeftJustify;
      except
          Text := '(MEMO)'
      end;
end;

procedure Register;
begin
      RegisterComponents('Bravo Banco', [TBrvQuery]);
end;

end.
