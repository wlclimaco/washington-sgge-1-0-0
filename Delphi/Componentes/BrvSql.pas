unit BrvSql;

interface

uses Classes, Dialogs, BrvDicionario, BrvDb, SysUtils, Db, BrvString, Windows, Forms,
     StdCtrls, Controls, DBClient;

type
  TBrvSql = Class
  private
   gCdsLog    : TClientDataSet;
   function ExcluirExpressaoParametro(pDsSql : String; pDsConteu: String): String;
   function ExcluirExpressaoParametroParenteses(pDsSql: String;
                                                pNrPosPar: Integer): String;
    function ExcluirExpressaoParametroWhere(pDsSql: String;     pNrPosFim : Integer;
                                            pNrPosAnt: Integer; pDsAnteri : String): String;
    procedure BrCarregarTabelas(var pStlTabela : TStrings; pDsSql : String);
    procedure ProximaPalavra(var pDsFrom : String; var pDsPalavr: String);
    procedure BrCarregarAtributos(var pStlAtribu : TStrings; pStlTabela: TStrings;
                                      pDsSql    : String;    pBrvDicion : TBrvDicionario;
                                      pNmCompon : String;    pNmComPai : String);
    function  BrAliasDoAtributo(pStlAtribu: TStrings; pNmAtribu: String): String;
    procedure BrTabelaDoAlias(pStlTabela: TStrings;  pNmAlias: String;
                          var pNmTabela: String; var pNrIndTab: Integer);
    procedure VisualizarInstrucaoSQL(pDsSql : String);
    function LogValorChavePrimaria(pNmTabela: String; pDsDicion: TBrvDicionario;
                                   pStlNmCam : TStrings; pStlVrCam : TStrings) : String;
    procedure LogProcessarInstrucaoSqlUpdate(pDsSql    : String;
                                             pDsParent : TComponent;
                                             pNmTabela : String;
                                             pDsDicion : TBrvDicionario;
                                             pNmFormul : String; pCdUsuari : Integer);

    function RemoveFuncaoBanco(DsSql : String; DsFunc : String): String;

  public
    function BrTraduzirInstrucaoSQL(pBrvDicion : TBrvDicionario; pNrQuery : Integer;
                                    pStCompon : TComponentState; pNmCompon : String;
                                    pNmComPai : String;          pTpQuery  : TVqType;
                                    pDsSql    : String;          pDsParams : TStrings):
                                    String;
    function BrProcessarAberturaSQL(pBrvDicion : TBrvDicionario; pNrQuery  : Integer;
                                    pStCompon : TComponentState; pNmCompon : String;
                                    pNmComPai : String;          pTpQuery  : TVqType;
                                    pDsSql    : String;          pDsParams : TStrings)
                                    : String;
    procedure DoAfterOpen(pDataSet : TDataSet; pBrvDicion: TBrvDicionario;
                          pDsSql   : String;   pNmCompon : String;
                          pNmComPai: String;   pSnShoFie  : Boolean);
    procedure NomeDaTabelaSQL(var pNmTabela, pTpOperac: String;
                                  pDsSql    : String);
    procedure LogCriarTabela(pDsParent : TComponent);
    procedure LogLimparDataSet;
    procedure LogGravarDataSet(pNmAtribu, pVrAtual: String);
    function  LogGetData : OleVariant;
    procedure LogProcessarInstrucaoSql(pDsSql, pTpOperac: String;
                                       pDsParent : TComponent;
                                       pNmTabela : String;
                                       pDsDicion : TBrvDicionario;
                                       pNmFormul : String; pCdUsuari : Integer);
    procedure LogProcessarInstrucaoSqlInsert(pDsSql    : String;
                                             pDsParent : TComponent;
                                             pNmTabela : String;
                                             pDsDicion : TBrvDicionario;
                                             pNmFormul : String; pCdUsuari : Integer);
    procedure LogAtualizarBanco(pNmTabela : String;     pDsChaPri : String;
                                pTpOperac : Integer;    pDsDicion : TBrvDicionario;
                                pDsParent : TComponent; pNmFormul : String;
                                pCdUsuari : Integer;    pTxSql    : String);
  end;

implementation

{ TBrvSql }

procedure TBrvSql.ProximaPalavra(var pDsFrom : String; var pDsPalavr : String);
begin
      pDsPalavr := '';

      case pDsFrom[1] of
           ' ': begin
                      while (pDsFrom <> '') and (pDsFrom[1] = ' ') do
                      begin
                            pDsPalavr := pDsPalavr + pDsFrom[1];
                            System.Delete(pDsFrom, 1, 1);
                      end;
                end;
           ',': begin
                      pDsPalavr := pDsFrom[1];
                      System.Delete(pDsFrom, 1, 1);
                end;
       #10,#13: begin
                      while (pDsFrom    <> '')  and
                            ((pDsFrom[1] = #10)  or (pDsFrom[1] = #13)) do
                      begin
                            pDsPalavr := pDsPalavr + pDsFrom[1];
                            System.Delete(pDsFrom, 1, 1);
                      end;
                end;
           else begin
                      while (pDsFrom    <> '')  and (pDsFrom[1] <> ' ') and
                            (pDsFrom[1] <> ',') and (pDsFrom[1] <> #10) and
                            (pDsFrom[1] <> #13) do
                      begin
                            pDsPalavr := pDsPalavr + pDsFrom[1];
                            System.Delete(pDsFrom, 1, 1);
                      end;
                end;
      end;
end;

function TBrvSql.RemoveFuncaoBanco(DsSql, DsFunc: String): String;

var lauxstr  : String;
    lauxstr1 : String;
    lauxstr2 : String;
begin
      lauxstr := DsSql;

      while pos(DsFunc, lauxstr) > 0 do
      begin
            lauxstr1 := Copy(lauxstr, 1, pos(DsFunc, lauxstr)-1);
            lauxstr2 := Copy(lauxstr, pos(DsFunc, lauxstr)+ (Length(DsFunc)+1), Length(lauxstr));
            lauxstr1 := lauxstr1 + Copy(lauxstr2, 1, pos(',', lauxstr2) - 1);
            lauxstr2 := Copy(lauxstr2, pos(')', lauxstr2) + 1, Length(lauxstr));
            lauxstr  := lauxstr1 + lauxstr2
      end;

      result := lauxstr;
end;

procedure TBrvSql.BrCarregarTabelas(var pStlTabela : TStrings; pDsSql : String);
var lDsFrom   : String;
    lStlSelect: TStrings;
    pNrSelect : Integer;
    lDsSql    : String;
    lNmTabela : String;
    lDsPalavr : String;
    lNmTabAux : String;
    lNmAlias  : String;
    lBrString : TBrvString;
begin
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-  Carregando nome das tabelas   =-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      try
          lStlSelect := TStringList.Create;

          lDsSql  := Trim(UpperCase(pDsSql));

          while lDsSql <> '' do
          begin
                System.Delete(lDsSql, 1, Pos('FROM', lDsSql) + 4);

                if Pos('FROM', lDsSql) <> 0 then
                begin
                      lStlSelect.Add(Copy(lDsSql, 1, Pos('FROM', lDsSql)));
                      System.Delete(lDsSql, 1, Pos('FROM', lDsSql) - 1);
                end else
                begin
                      lStlSelect.Add(lDsSql);
                      lDsSql := '';
                end;
          end;

          for pNrSelect := 0 to lStlSelect.Count -1 do
          begin
                lDsFrom := lStlSelect.Strings[pNrSelect];

                if  Pos('WHERE', lDsFrom) > 0 then
                begin
                      System.Delete(lDsFrom, Pos('WHERE', lDsFrom), Length(lDsFrom));
                end;

                if  Pos('ORDER', lDsFrom) > 0 then
                begin
                      System.Delete(lDsFrom, Pos('ORDER', lDsFrom), Length(lDsFrom));
                end;

                if  Pos('GROUP', lDsFrom) > 0 then
                begin
                      System.Delete(lDsFrom, Pos('GROUP', lDsFrom), Length(lDsFrom));
                end;

                while lDsFrom <> '' do
                begin
                      lNmTabela := '';
                      repeat
                             ProximaPalavra(lDsFrom, lDsPalavr);

                             if (lDsPalavr <> 'LEFT') and (lDsPalavr <> 'RIGHT') and
                                (lDsPalavr <> 'JOIN') and (lDsPalavr <> ',') then
                             begin
                                   if  lDsPalavr = 'ON' then
                                   begin
                                         repeat
                                                ProximaPalavra(lDsFrom, lDsPalavr);
                                         until (lDsPalavr = 'JOIN') or (lDsFrom = '');
                                   end else
                                   begin
                                         if  lDsPalavr = '' then
                                         begin
                                               lNmTabela := lNmTabela + ' ';
                                         end else
                                         begin
                                               lNmTabela := lNmTabela + lDsPalavr;
                                         end;
                                   end;
                             end;
                      until (lDsPalavr = 'LEFT') or (lDsPalavr = 'RIGHT') or
                            (lDsPalavr = 'JOIN') or (lDsPalavr = ',')     or
                            (lDsFrom   = '');

                      lNmTabela := Trim(lNmTabela);

                      if lNmTabela <> '' then
                      begin
                            if  Pos(' ', lNmTabela) > 0 then
                            begin
                                  lNmTabAux := Copy(lNmTabela, 1, Pos(' ', lNmTabela) -1);
                                  System.Delete(lNmTabela, 1, Pos(' ', lNmTabela));
                                  lNmAlias  := Trim(lNmTabela);
                            end else
                            begin
                                  lNmAlias  := '';
                                  lNmTabAux := lNmTabela;
                            end;

                            pStlTabela.Add(
                                        lBrString.FormatarStringSemAcento(lNmTabAux, 15) +
                                                                           ';'+ lNmAlias);
                      end;
                end;
          end;
      finally
          FreeAndNil(lStlSelect);
      end;
end;

procedure TBrvSql.BrCarregarAtributos(var pStlAtribu : TStrings;
                                          pStlTabela : TStrings;       pDsSql    : String;
                                          pBrvDicion : TBrvDicionario; pNmCompon : String;
                                          pNmComPai : String);
var lDsSelect : String;
    lNrPosVir : Integer;
    lNrPosEsp : Integer;
    lNrPosAst : Integer;
    lNrTabela : Integer;
    lNrColuna : Integer;
    lNmAtribu : String;
    lNmTabela : String;
    lNmAlias  : String;
    lNmAliAux : String;
    lStlColAux: TStrings;
begin
      pStlAtribu.Clear;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=  Carregando os atributos da select -=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      lDsSelect := Trim(UpperCase(pDsSql));
      System.Delete(lDsSelect, Pos('SELECT', lDsSelect), 6);
      System.Delete(lDsSelect, Pos('FROM', lDsSelect), Length(lDsSelect));

      while lDsSelect <> '' do
      begin
            lNrPosVir := Pos(',', lDsSelect);

            if lNrPosVir > 0 then
            begin
                  lNmAtribu := Copy(lDsSelect, 1, lNrPosVir -1);
                  System.Delete(lDsSelect, 1, lNrPosVir);
            end else
            begin
                  lNmAtribu := lDsSelect;
                  lDsSelect := '';
            end;

            lNmAtribu := Trim(lNmAtribu);

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=  Verificanco a existencia do AS =-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            lNrPosEsp := Pos(' ', lNmAtribu);

            if lNrPosEsp > 0 then
            begin
                  lNmAtribu := Trim(Copy(lNmAtribu, 1, lNrPosEsp));
            end;

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=  Verificanco a existencia do * -=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            lNrPosAst := Pos('*', lNmAtribu);

            if lNrPosAst > 0 then
            begin
                  lNrPosVir := Pos('.', lNmAtribu);

                  if lNrPosVir > 0 then
                  begin
                        lNmAlias  := Copy(lNmAtribu, 1, lNrPosVir -1);
                        lNrTabela := 0;

                        while lNrTabela < pStlTabela.Count do
                        begin
                              lNmAliAux := Copy(pStlTabela.Strings[lNrTabela], 17,
                                           Length(pStlTabela.Strings[lNrTabela]));

                              if  lNmAliAux = lNmAlias then
                              begin
                                    lNmTabela := Copy(pStlTabela.Strings[lNrTabela], 1, 15);
                                    lNrTabela := pStlTabela.Count;
                              end;

                              Inc(lNrTabela);
                        end;
                  end else
                  begin
                        lNmAlias  := '';
                        lNmTabela := Copy(pStlTabela.Strings[0], 1, 15);
                  end;

                  lNmTabela  := Trim(lNmTabela);
                  lStlColAux := TStringList.Create;
                  lStlColAux.Clear;

                  if pBrvDicion = nil then
                  begin
                        MessageDlg('Não foi possível determinar o dicionário '  +
                                   'de dados.' + #13#13 + 'Query: ' + pNmCompon +
                                   #13 + 'Módulo: ' + pNmComPai, MtError, [MbOk], 0);
                  end else
                  begin
                        pBrvDicion.AtributosDaTabela(lNmTabela, lStlColAux);
                  end;

                  for lNrColuna := 0 to lStlColAux.Count -1 do
                  begin
                        if lNmAlias <> '' then
                        begin
                              pStlAtribu.Add(lNmAlias + '.' + lStlColAux.Strings[lNrColuna]);
                        end else
                        begin
                              pStlAtribu.Add(lStlColAux.Strings[lNrColuna]);
                        end;
                  end;
            end else
            begin
                  pStlAtribu.Add(lNmAtribu);
            end;
      end;
end;

function  TBrvSql.BrAliasDoAtributo(pStlAtribu : TStrings; pNmAtribu : String) : String;
var lNrCampo  : Integer;
    lNrPosPon : Integer;
    lNmAlias  : String;
    lNmCampo  : String;
begin
      Result  := '';
      lNrCampo := 0;

      while lNrCampo < pStlAtribu.Count do
      begin
            lNrPosPon := Pos('.', pStlAtribu.Strings[lNrCampo]);

            if lNrPosPon > 0 then
            begin
                  lNmAlias  := Copy(pStlAtribu.Strings[lNrCampo], 1, lNrPosPon - 1);
                  lNmCampo  := Copy(pStlAtribu.Strings[lNrCampo], lNrPosPon + 1,
                                    Length(pStlAtribu.Strings[lNrCampo]));

                  lNmCampo  := Trim(lNmCampo);

                  if  lNmCampo = pNmAtribu then
                  begin
                        Result   := lNmAlias;

                        pStlAtribu.Delete(lNrCampo);

                        lNrCampo  := pStlAtribu.Count;
                  end;
            end else
            begin
                  lNrCampo  := pStlAtribu.Count;
            end;

            Inc(lNrCampo);
      end;
end;

procedure TBrvSql.BrTabelaDoAlias(pStlTabela : TStrings;   pNmAlias  : String;
                                  var pNmTabela  : String; var pNrIndTab : Integer);
var lNrTabela : Integer;
    lNrPosPon : Integer;
    lNmAliAux : String;
    lNmTabAux : String;
begin
      pNmTabela := '';

      if  pNmAlias = '' then
      begin
            pNmTabela := Trim(Copy(pStlTabela.Strings[0], 1, 15));
            pNrIndTab := 0;
      end else
      begin
            lNrTabela := 0;

            while lNrTabela < pStlTabela.Count do
            begin
                  lNrPosPon := Pos(';', pStlTabela.Strings[lNrTabela]);
                  lNmAliAux := Copy(pStlTabela.Strings[lNrTabela], lNrPosPon + 1,
                                                   Length(pStlTabela.Strings[lNrTabela]));
                  lNmTabAux := Copy(pStlTabela.Strings[lNrTabela], 1, lNrPosPon -1);
                  pNrIndTab := lNrTabela;

                  if lNmAliAux = pNmAlias then
                  begin
                        pNmTabela := Trim(lNmTabAux);
                        lNrTabela := pStlTabela.Count;
                  end;

                  Inc(lNrTabela);
            end;
      end;
end;

procedure TBrvSql.DoAfterOpen(pDataSet  : TDataSet; pBrvDicion : TBrvDicionario;
                              pDsSql    : String;   pNmCompon  : String;
                              pNmComPai : String;   pSnShoFie  : Boolean);
var lNrCampo   : Integer;
    lNmAlias   : String;
    lNmTabela  : String;
    lDsDisLab  : String;
    lDsDisFor  : String;
    lDsMascar  : String;
    lTpMascar  : String;
    lDsHelp    : String;
    lDsHint    : String;
    lTmAtribu  : Integer;
    lNmField   : String;
    lTpCampo   : String;
    //lDsCampo   : String;
    lCdPermis  : Integer;
    lVrDomini  : String;
    lDsDomini  : String;
    lStlTabela : TStrings;
    lStrAtribu : TStrings;
    lSnKey     : Boolean;
    lNrIndTab  : Integer;
begin
      if pDsSql <> '' then
      begin
            try
                lStlTabela := TStringList.Create;
                lStrAtribu := TStringList.Create;

                BrCarregarTabelas(lStlTabela, pDsSql);
                BrCarregarAtributos(lStrAtribu, lStlTabela, pDsSql, pBrvDicion,
                                    pNmCompon,  pNmComPai);

                if pBrvDicion <> nil then
                begin
                      for lNrCampo := 0 to pDataSet.Fields.Count -1 do
                      begin
                            lDsDisLab := '';
                            lDsDisFor := '';
                            lNmField  := pDataSet.Fields[lNrCampo].FieldName;

                            lNmAlias  := BrAliasDoAtributo(lStrAtribu, lNmField);

                            BrTabelaDoAlias(lStlTabela, lNmAlias, lNmTabela, lNrIndTab);

                            pBrvDicion.AtributoConfiguracao(
                                       lNmTabela, lNmField,  lDsDisLab,
                                       lTpCampo,  lTpMascar, lDsMascar,
                                       lDsHelp,   lDsHint,   lTmAtribu,
                                       lCdPermis, lVrDomini, lDsDomini,
                                       lSnKey,    lDsDisFor);

                            if (lNrIndTab = 0) and
                               (pBrvDicion.AtributoExisteTabela(lNmTabela, lNmField)) then
                            begin
                                  if lSnKey then
                                  begin
                                        pDataSet.Fields[lNrCampo].ProviderFlags :=
                                                       [pfInUpdate, pfInWhere];
                                  end else
                                  begin
                                        pDataSet.Fields[lNrCampo].ProviderFlags :=
                                                                             [pfInUpdate];
                                  end;
                            end else
                            begin
                                  pDataSet.Fields[lNrCampo].ProviderFlags := [];
                            end;

                            if not pSnShoFie then
                            begin
                                  pDataSet.Fields[lNrCampo].DisplayLabel := lDsDisLab;
                            end;

                            pDataSet.Fields[lNrCampo].EditMask := lDsMascar;

                            if (pDataSet.Fields[lNrCampo] is TIntegerField) then
                            begin
                                  TIntegerField(pDataSet.Fields[lNrCampo]).DisplayFormat :=
                                                                           lDsDisFor;
                                  pDataSet.Fields[lNrCampo].Alignment := taLeftJustify;
                            end else
                            if (pDataSet.Fields[lNrCampo] is TFloatField) then
                            begin
                                  TFloatField(pDataSet.Fields[lNrCampo]).DisplayFormat  := lDsDisFor;
                                  pDataSet.Fields[lNrCampo].Alignment := taLeftJustify;
                            end else
                            if (pDataSet.Fields[lNrCampo] is TCurrencyField) then
                            begin
                                  TCurrencyField(pDataSet.Fields[lNrCampo]).DisplayFormat :=
                                                                                      lDsDisFor;
                                  pDataSet.Fields[lNrCampo].Alignment := taLeftJustify;
                            end else
                            if (pDataSet.Fields[lNrCampo] is TBcdField) then
                            begin
                                  TBcdField(pDataSet.Fields[lNrCampo]).DisplayFormat := lDsDisFor;
                                  pDataSet.Fields[lNrCampo].Alignment := taLeftJustify;
                            end else
                            if (pDataSet.Fields[lNrCampo] is TFMTBcdField) then
                            begin
                                  TBcdField(pDataSet.Fields[lNrCampo]).DisplayFormat := lDsDisFor;
                                  pDataSet.Fields[lNrCampo].Alignment := taLeftJustify;
                            end else
                            if (pDataSet.Fields[lNrCampo] is TStringField) then
                            begin
                                  case lTpMascar[1] of
                                       'M': pDataSet.Fields[lNrCampo].EditMask := '>' + // maiusculo
                                               StringOfChar('c',
                                                pDataSet.fields[lNrCampo].DisplayWidth) + ';0; ';
                                       'm': pDataSet.Fields[lNrCampo].EditMask := '<' + // minusculo
                                               StringOfChar('c',
                                                pDataSet.fields[lNrCampo].DisplayWidth) + ';0; ';
                                       'D': pDataSet.Fields[lNrCampo].EditMask := lDsMascar;
                                       'N':; // assumir Display format do atributo
                                  end;
                            end;

                            if pBrvDicion.TipoBancoDados = BcoOracle then
                            begin
                                  if pDataSet.Fields[lNrCampo] is TMemoField then
                                  begin
                                        TMemoField(pDataSet.Fields[lNrCampo]).BlobType := ftOraClob;
                                  end;
                            end;
                      end;
                end;
            finally
                lStlTabela.Destroy;
                lStrAtribu.Destroy;
            end;
      end;
end;

function TBrvSql.BrProcessarAberturaSQL(pBrvDicion : TBrvDicionario; pNrQuery  : Integer;
                                        pStCompon : TComponentState; pNmCompon : String;
                                        pNmComPai : String;          pTpQuery  : TVqType;
                                        pDsSql    : String;          pDsParams : TStrings)
                                        : String;
begin
      try
          Result := BrTraduzirInstrucaoSQL(pBrvDicion, pNrQuery, pStCompon, pNmCompon,
                                           pNmComPai,  pTpQuery, pDsSql,    pDsParams);


          inherited;
      except
          if  not (csDesigning in pStCompon) then
          begin
                if pBrvDicion.Camada = client then
                begin
                      if pTpQuery = VqNormal then
                      begin
                            if MessageDlg('Erro na tentativa de abrir: ' + #13#13 +
                                          'Query: "'  + pNmCompon + '"'  + #13 +
                                          'Módulo: "' + pNmComPai + '"' + #13 +
                                          'Código: "' + IntToStr(pNrQuery) + '"' +
                                          #13#13 + 'Visualizar Instrução SQL?',
                                          MtError, [MbYes, MbNo], 0) = IdYes then
                            begin
                                  VisualizarInstrucaoSQL(pDsSql);
                            end;
                      end else
                      begin
                            if MessageDlg('Erro na tentativa de abrir query de ' +
                                          'consulta: ' + #13#13 +
                                          'Código: "' + IntToStr(pNrQuery) + '"' +
                                          #13#13 + 'Visualizar Instrução SQL?',
                                          MtError, [MbYes, MbNo], 0) = IdYes then
                            begin
                                  VisualizarInstrucaoSQL(pDsSql);
                            end;
                      end;
                end;
          end;

          raise;
      end;
end;

procedure TBrvSql.VisualizarInstrucaoSQL(pDsSql : String);
var lFrmMemo : TForm;
    lDbMemo  : TMemo;
begin
      try
          lFrmMemo             := TForm.CreateNew(Application);
          lFrmMemo.Position    := poScreenCenter;
          lFrmMemo.Width       := 550;
          lFrmMemo.Height      := 340;
          lFrmMemo.BorderStyle := bsSizeable;
          lFrmMemo.BorderIcons := [biSystemMenu,biMaximize];
          lFrmMemo.Caption     := 'Instrução SQL';

          lDbMemo              := TMemo.Create(lFrmMemo);
          lDbMemo.Parent       := lFrmMemo;
          lDbMemo.Align        := alClient;
          lDbMemo.Lines.Text   := pDsSql;
          lDbMemo.ReadOnly     := True;
          lDbMemo.ScrollBars   := ssBoth;
          lDbMemo.TabOrder     := 0;
          lDbMemo.Font.Name    := 'Courier New';

          lFrmMemo.ShowModal;
      finally
          FreeAndNil(lFrmMemo);
          FreeAndNil(lDbMemo);
      end;
end;


function TBrvSql.BrTraduzirInstrucaoSQL(pBrvDicion : TBrvDicionario; pNrQuery  : Integer;
                                        pStCompon : TComponentState; pNmCompon : String;
                                        pNmComPai : String;          pTpQuery  : TVqType;
                                        pDsSql    : String;          pDsParams : TStrings):
                                        String;
begin
      if pBrvDicion = nil then
      begin
            MessageDlg('Não foi possível determinar o dicionário '  +
                       'de dados.' + #13#13 +
                       'Origem: TBrvSql'     + #13 +
                       'Query: ' + pNmCompon + #13 +
                       'Módulo: ' + pNmComPai, MtError, [MbOk], 0);
            Result := pDsSql;
      end else
      begin
           // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
           // =-=-=-=-=-=- Pesquisando no banco a select para o código -=-=-=-=-=-=-=-=-
           // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
              if  (pNrQuery <> 0) and (not (csDesigning in pStCompon)) then
              begin
                    if pTpQuery  = VqNormal then
                    begin
                          Result := Trim(pBrvDicion.EncontrarInstrucaoSQL(pNrQuery,
                                                                              pNmComPai));
                    end else
                    begin
                          Result :=
                                Trim(pBrvDicion.EncontrarInstrucaoSQLConsulta(pNrQuery));
                    end;
              end else
              begin
                    Result := pDsSql;
              end;

           // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
           // =-=-=-=-=-=- Substituindo os parâmetros =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
           // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
              Result := pBrvDicion.SubstituirParametrosSQL(Result, pDsParams);

           // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
           // =-=-=-=-=-=- Traduzindo a select para o banco atual =-=-=-=-=-=-=-=-=-=-=
           // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
              Result := pBrvDicion.TraduzirInstrucaoSQL(Result);
           // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      end;
end;

function TBrvSql.ExcluirExpressaoParametro(pDsSql: String; pDsConteu: String): String;
var lNrPosFim : Integer;
    lNrPosAnt : Integer;
    lDsAnteri : String;
begin
      lNrPosFim := Pos(pDsConteu, pDsSql);
   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo parâmetro
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      System.Delete(pDsSql, lNrPosFim, Length(pDsConteu));

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo espaco antes do parâmetro
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      if  (pDsSql[lNrPosFim] = ')') or (pDsSql[lNrPosFim] = '') then
      begin
           Dec(lNrPosFim);
      end;

      while ((pDsSql[lNrPosFim] = ' ') or (pDsSql[lNrPosFim] = #13) or
             (pDsSql[lNrPosFim] = #10)) and (pDsSql <> '') do
      begin
            System.Delete(pDsSql, lNrPosFim, 1);
            Dec(lNrPosFim);
      end;
   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo operador lógico
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while (pDsSql[lNrPosFim] <> ' ') and (pDsSql[lNrPosFim] <> #13) and
            (pDsSql[lNrPosFim] <> #10) and (pDsSql <> '') do
      begin
            System.Delete(pDsSql, lNrPosFim, 1);
            Dec(lNrPosFim);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo espaco antes do operador lógico
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while ((pDsSql[lNrPosFim] = ' ') or (pDsSql[lNrPosFim] = #13) or
             (pDsSql[lNrPosFim] = #10)) and (pDsSql <> '') do
      begin
            System.Delete(pDsSql, lNrPosFim, 1);
            Dec(lNrPosFim);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo coluna de comparação
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while (pDsSql[lNrPosFim] <> ' ') and (pDsSql[lNrPosFim] <> #13) and
            (pDsSql[lNrPosFim] <> #10) and (pDsSql[lNrPosFim] <> '(')  and
            (pDsSql <> '') do
      begin
            System.Delete(pDsSql, lNrPosFim, 1);
            Dec(lNrPosFim);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Excluindo espaco antes da coluna de comparação
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while ((pDsSql[lNrPosFim] = ' ') or (pDsSql[lNrPosFim] = #13) or
             (pDsSql[lNrPosFim] = #10)) and (pDsSql <> '') do
      begin
            System.Delete(pDsSql, lNrPosFim, 1);
            Dec(lNrPosFim);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Econtrando operador antes da coluna de comparação
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      lNrPosAnt := lNrPosFim;
      lDsAnteri := '';

      while (pDsSql[lNrPosAnt] <> ' ') and (pDsSql[lNrPosAnt] <> #13) and
            (pDsSql[lNrPosAnt] <> #10) and
            (pDsSql <> '') do
      begin
            lDsAnteri := pDsSql[lNrPosAnt] + lDsAnteri;
            Dec(lNrPosAnt);
      end;

      if  UpperCase(lDsAnteri) <> 'WHERE' then
      begin
            if  Pos('(', lDsAnteri) > 0 then
            begin
                  pDsSql := ExcluirExpressaoParametroParenteses(pDsSql, lNrPosAnt);
            end else
            begin
               // =-=-=-=-=-=-=-=-=-=-=-=-=
               // Excluindo operador antes da coluna de comparação
               // =-=-=-=-=-=-=-=-=-=-=-=-=
                  System.Delete(pDsSql, lNrPosAnt + 1, Length(lDsAnteri));
            end;
      end else
      begin
            pDsSql := ExcluirExpressaoParametroWhere(pDsSql, lNrPosFim,
                                                     lNrPosAnt, lDsAnteri);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=

      Result := pDsSql;
end;

function TBrvSql.ExcluirExpressaoParametroParenteses(pDsSql    : String;
                                                     pNrPosPar : Integer) : String;
var lNrPosAtu : Integer;
    lDsParent : String;
    lDsAnteri : String;
    lqtparent : Integer;
    lnrposini : Integer;
begin
      lNrPosAtu := pNrPosPar + 2;
      lnrposini := lNrPosAtu;
      lDsParent := '';
      lqtparent := 1;

      while lqtparent > 0 do
      begin
            if pDsSql[lNrPosAtu] = '(' then
            begin
                  Inc(lqtparent);
                  lDsParent := lDsParent + pDsSql[lNrPosAtu];
            end else
            begin
                  if pDsSql[lNrPosAtu] = ')' then
                  begin
                        Dec(lqtparent);

                        if lqtparent > 0 then
                        begin
                              lDsParent := lDsParent + pDsSql[lNrPosAtu];
                        end;
                  end else
                  begin
                        lDsParent := lDsParent + pDsSql[lNrPosAtu];
                  end;
            end;
            Inc(lNrPosAtu);
      end;

      lDsParent := Trim(lDsParent);

      if  lDsParent <> '' then
      begin
            System.Delete(pDsSql, lnrposini, lNrPosAtu - lnrposini - 1);

            while (lDsParent[1] <> ' ') and (lDsParent[1] <> #13) and
                  (lDsParent[1] <> #10) and (lDsParent <> '') do
            begin
                  System.Delete(lDsParent, 1, 1);
            end;

            System.Insert(lDsParent, pDsSql, lnrposini);
      end else
      begin
            System.Delete(pDsSql, lnrposini - 1, lNrPosAtu - lnrposini + 1);
            lnrposini := lnrposini - 1 - (lNrPosAtu - lnrposini);

            while ((pDsSql[lnrposini] = ' ') or (pDsSql[lnrposini] = #13) or
                   (pDsSql[lnrposini] = #10)) and (lnrposini > 0) do
            begin
                  System.Delete(pDsSql, lnrposini, 1);
                  Dec(lnrposini);
            end;

            if  pDsSql[lnrposini] = '(' then
            begin
                  Inc(lnrposini);
                  while ((pDsSql[lnrposini] = ' ') or (pDsSql[lnrposini] = #13) or
                         (pDsSql[lnrposini] = #10)) and (lnrposini > 0) do
                  begin
                        System.Delete(pDsSql, lnrposini, 1);
                  end;

                  while (pDsSql[lnrposini] <> ' ') and (pDsSql[lnrposini] <> #13) and
                        (pDsSql[lnrposini] <> #10) and (pDsSql[lnrposini] <> ')') and
                        (lnrposini > 0) do
                  begin
                        System.Delete(pDsSql, lnrposini, 1);
                  end;
            end else
            begin
               // =-=-=-=-=-=-=-=-=-=-=-=-=
               // Econtrando operador antes dos parenteses
               // =-=-=-=-=-=-=-=-=-=-=-=-=
                  lDsAnteri := '';
                  lNrPosAtu := lnrposini;

                  while (pDsSql[lNrPosAtu] <> ' ') and (pDsSql[lNrPosAtu] <> #13) and
                        (pDsSql[lNrPosAtu] <> #10) and (pDsSql[lNrPosAtu] <> '(') and
                        (lNrPosAtu > 0) do
                  begin
                        lDsAnteri := Copy(pDsSql, lNrPosAtu, 1) + lDsAnteri;
                        Dec(lNrPosAtu);
                  end;

                  lDsAnteri := Trim(lDsAnteri);

                  if  UpperCase(lDsAnteri) <> 'WHERE' then
                  begin
                        while (pDsSql[lnrposini] <> ' ') and
                              (pDsSql[lnrposini] <> #13) and
                              (pDsSql[lnrposini] <> #10) and
                              (pDsSql[lnrposini] <> '(') and
                              (lnrposini > 0) do
                        begin
                              System.Delete(pDsSql, lnrposini, 1);
                              Dec(lnrposini);
                        end;
                  end else
                  begin
                        pDsSql := ExcluirExpressaoParametroWhere(pDsSql,
                                              lnrposini + 1, lNrPosAtu, lDsAnteri);
                  end;
            end;
      end;

      Result := pDsSql;
end;

function TBrvSql.ExcluirExpressaoParametroWhere(pDsSql    : String;
                                                pNrPosFim : Integer;
                                                pNrPosAnt : Integer;
                                                pDsAnteri : String) : String;
var lNrPosPos : Integer;
    lDsPoster : String;
begin
      lNrPosPos := pNrPosFim + 1;
      lDsPoster := '';

      if  (pDsSql[lNrPosPos] = ' ') or (pDsSql[lNrPosPos] = #10) or
          (pDsSql[lNrPosPos] = #13) then
      begin
         // =-=-=-=-=-=-=-=-=-=-=-=-=
         // Ignorando espaços após a coluna de comparação
         // =-=-=-=-=-=-=-=-=-=-=-=-=

            while ((pDsSql[lNrPosPos] = ' ') or (pDsSql[lNrPosPos] = #10) or
                   (pDsSql[lNrPosPos] = #13))                          and
                  ((lNrPosPos <= Length(pDsSql))) do
            begin
                  Inc(lNrPosPos);
            end;
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=
   // Encontrando operador depois da coluna de comparação
   // =-=-=-=-=-=-=-=-=-=-=-=-=
      while (pDsSql[lNrPosPos] <> ' ') and (pDsSql[lNrPosPos] <> #13) and
            (pDsSql[lNrPosPos] <> #10) and (lNrPosPos <= Length(pDsSql)) do
      begin
            lDsPoster := lDsPoster + pDsSql[lNrPosPos];
            Inc(lNrPosPos);
      end;

      lDsPoster := Trim(lDsPoster);

      if  lDsPoster = ''  then
      begin
            System.Delete(pDsSql, pNrPosAnt + 1, Length(pDsAnteri));
      end else
      begin
            System.Delete(pDsSql, pNrPosFim + 1, lNrPosPos - pNrPosFim - 1);
      end;

      Result := pDsSql;
end;

procedure TBrvSql.NomeDaTabelaSQL(var pNmTabela : String; var pTpOperac : String;
                                      pDsSql    : String);
begin
      pNmTabela := Trim(UpperCase(pDsSql));
      pTpOperac := Copy(pNmTabela, 1, Pos(' ', pNmTabela));
      pTpOperac := Trim(pTpOperac);

      if pTpOperac = 'SELECT' then
      begin
            System.Delete(pNmTabela, 1, Pos('FROM', pNmTabela) + 4);
            pNmTabela := Trim(pNmTabela);
            System.Delete(pNmTabela, Pos(' ', pNmTabela), 10000);
      end
      else if pTpOperac = 'DELETE' then
           begin
                 if pos('FROM', pNmTabela) > 0 then
                 begin
                       System.Delete(pNmTabela, 1, Pos('FROM', pNmTabela) + 4);
                 end else
                 begin
                       System.Delete(pNmTabela, 1, Pos('DELETE', pNmTabela) + 6);
                 end;

                 pNmTabela := Trim(pNmTabela);
                 System.Delete(pNmTabela, Pos(' ', pNmTabela), 10000);
           end
      else if pTpOperac = 'UPDATE' then
           begin
                 System.Delete(pNmTabela, 1, Pos(' ', pNmTabela));
                 pNmTabela := Trim(pNmTabela);
                 System.Delete(pNmTabela, Pos(' ', pNmTabela), 10000);
           end
      else if pTpOperac = 'INSERT' then
           begin
                 System.Delete(pNmTabela, 1, Pos('INTO', pNmTabela) + 4);
                 pNmTabela := Trim(pNmTabela);
                 System.Delete(pNmTabela, Pos(' ', pNmTabela), 10000);
           end;

      pNmTabela := Trim(pNmTabela);
end;

procedure TBrvSql.LogCriarTabela(pDsParent : TComponent);
begin
      gCdsLog   := TClientDataSet.Create(pDsParent);
      gCdsLog.FieldDefs.Clear;

      gCdsLog.FieldDefs.Add('NmAtribu', FtString,  8, False);
      gCdsLog.FieldDefs.Add('VrAtual',  FtMemo,    0, False);
      gCdsLog.CreateDataSet;
end;

procedure TBrvSql.LogLimparDataSet;
begin
      gCdsLog.EmptyDataSet;
end;

function TBrvSql.LogGetData: OleVariant;
begin
      Result := gCdsLog.Data;
end;

procedure TBrvSql.LogGravarDataSet(pNmAtribu : String; pVrAtual : String);
begin
      gCdsLog.Append;
      gCdsLog.FieldByName('NmAtribu').AsString := pNmAtribu;
      gCdsLog.FieldByName('VrAtual').AsString  := pVrAtual;
      gCdsLog.Post;
end;

procedure TBrvSql.LogProcessarInstrucaoSql(pDsSql    : String; pTpOperac : String;
                                           pDsParent : TComponent;
                                           pNmTabela : String;
                                           pDsDicion : TBrvDicionario;
                                           pNmFormul : String; pCdUsuari : Integer);
var lDsSql : String;
begin
      LogLimparDataSet;
      pDsSql := UpperCase(pDsSql);

      //Removendo comandos do Banco
      lDsSql := pDsSql;
      lDsSql := RemoveFuncaoBanco(lDsSql, 'TO_DATE');
      lDsSql := RemoveFuncaoBanco(lDsSql, 'NVL');
      lDsSql := RemoveFuncaoBanco(lDsSql, 'SUBSTR');

      if pTpOperac = 'INSERT' then
      begin
            LogProcessarInstrucaoSqlInsert(lDsSql,    pDsParent, pNmTabela,
                                           pDsDicion, pNmFormul, pCdUsuari);
      end
      else if pTpOperac = 'UPDATE' then
           begin
                 LogProcessarInstrucaoSqlUpdate(lDsSql,    pDsParent, pNmTabela,
                                                pDsDicion, pNmFormul, pCdUsuari);
           end
      else if pTpOperac = 'DELETE' then
           begin
                 LogLimparDataSet;
                 LogAtualizarBanco(pNmTabela, 'SQL DELETE', 3, pDsDicion, pDsParent,
                                   pNmFormul, pCdUsuari, lDsSql);
           end
      ;
end;

procedure TBrvSql.LogProcessarInstrucaoSqlUpdate(pDsSql    : String;
                                                 pDsParent : TComponent;
                                                 pNmTabela : String;
                                                 pDsDicion : TBrvDicionario;
                                                 pNmFormul : String; pCdUsuari : Integer);
var lBrString : TBrvString;
    lDsCampos : String;
    //NmCampos : String;
    lDsWhere  : String;
    lVrCampos : String;
    lStlNmCam : TStrings;
    lStlNmCmU : TStrings;
    lStlVrCam : TStrings;
    lNrCampo  : Integer;
begin
      lDsWhere  := '';

      try
          lBrString := TBrvString.Create(pDsParent);

          lBrString.Split(pDsSql, 'SET');
          lDsCampos := lBrString.BrSplitLista.Strings[1];
          lBrString.Split(lDsCampos, 'WHERE');
          lDsCampos := lBrString.BrSplitLista.Strings[0];

          if lBrString.BrSplitLista.Count > 1 then
          begin
                lDsWhere := lBrString.BrSplitLista.Strings[1];
          end;

          try
              lStlNmCam := TStringList.Create;
              lStlVrCam := TStringList.Create;
              lStlNmCmU := TStringList.Create;

              // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
              // =-=-= Processando campos e valores do SET
              // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
              lStlVrCam.Clear;
              lBrString.Split(lDsCampos, ',');
              lStlNmCam.Text := lBrString.BrSplitLista.Text;

              lStlNmCmU.Clear;

              for lNrCampo := 0 to lStlNmCam.Count -1 do
              begin
                    if (Trim(lStlNmCam.Strings[lNrCampo]) <> '') then
                    begin
                          lBrString.Split(lStlNmCam.Strings[lNrCampo], '=');
                          lStlNmCam.Strings[lNrCampo] := Trim(lBrString.BrSplitLista.Strings[0]);
                          //Ajuste
                          lStlNmCmU.Add(lStlNmCam.Strings[lNrCampo]);

                          lVrCampos := Trim(lBrString.BrSplitLista.Strings[1]);
                          lVrCampos := StringReplace(lVrCampos, '"', '',
                                                                   [rfReplaceAll,rfIgnoreCase]);
                          lVrCampos := StringReplace(lVrCampos, #39, '',
                                                                   [rfReplaceAll,rfIgnoreCase]);
                          lStlVrCam.Add(lVrCampos);
                    end;
              end;

              for lNrCampo := 0 to lStlNmCmU.Count -1 do
              begin
                    LogGravarDataSet(lStlNmCmU.Strings[lNrCampo],
                                     lStlVrCam.Strings[lNrCampo]);

              end;

              LogAtualizarBanco(pNmTabela, 'SQL UPDATE', 2, pDsDicion, pDsParent,
                                pNmFormul, pCdUsuari, pDsSql);

          finally
              FreeAndNil(lStlNmCam);
              FreeAndNil(lStlVrCam);
              FreeAndNil(lStlNmCmU);
          end;
      finally
          FreeAndNil(lBrString);
      end;
end;

procedure TBrvSql.LogProcessarInstrucaoSqlInsert(pDsSql    : String;
                                                 pDsParent : TComponent;
                                                 pNmTabela : String;
                                                 pDsDicion : TBrvDicionario;
                                                 pNmFormul : String; pCdUsuari : Integer);
var lBrString : TBrvString;
    lNmCampos : String;
    lVrCampos : String;
    lStlNmCam : TStrings;
    lStlVrCam : TStrings;
    lNrCampo  : Integer;
    lDsChaPri : String;
begin;
      try
          lBrString := TBrvString.Create(pDsParent);
          lBrString.Split(pDsSql, '(');
          lNmCampos := lBrString.BrSplitLista.Strings[1];
          lVrCampos := lBrString.BrSplitLista.Strings[2];

          lBrString.Split(lNmCampos, ')');
          lNmCampos := lBrString.BrSplitLista.Strings[0];

          lBrString.Split(lVrCampos, ')');
          lVrCampos := lBrString.BrSplitLista.Strings[0];

          try
              lStlNmCam := TStringList.Create;
              lStlVrCam := TStringList.Create;

              lVrCampos := StringReplace(lVrCampos, '"', '', [rfReplaceAll,rfIgnoreCase]);
              lVrCampos := StringReplace(lVrCampos, #39, '', [rfReplaceAll,rfIgnoreCase]);

              lStlNmCam.CommaText := lNmCampos;
              lStlVrCam.CommaText := lVrCampos;

              for lNrCampo := 0 to lStlNmCam.Count -1 do
              begin
                    LogGravarDataSet(lStlNmCam.Strings[lNrCampo],
                                     lStlVrCam.Strings[lNrCampo]);

              end;

              lDsChaPri := LogValorChavePrimaria(pNmTabela, pDsDicion,
                                                 lStlNmCam, lStlVrCam);

              LogAtualizarBanco(pNmTabela, lDsChaPri, 1, pDsDicion, pDsParent,
                                pNmFormul, pCdUsuari, 'SQL INSERT' + #13#13 + pDsSql);
          finally
              FreeAndNil(lStlNmCam);
              FreeAndNil(lStlVrCam);
          end;
      finally
          FreeAndNil(lBrString);
      end;
end;

function TBrvSql.LogValorChavePrimaria(pNmTabela : String;   pDsDicion : TBrvDicionario;
                                       pStlNmCam : TStrings; pStlVrCam : TStrings): String;
var  lStlChave : TStrings;
     lNrChave  : Integer;
     lNrCampo  : Integer;
begin
      try
          lStlChave   := TStringList.Create;
          pDsDicion.ChavePrimaria(pNmTabela, lStlChave);
          Result      := '';

          for lNrChave := 0 to lStlChave.Count - 1 do
          begin
                lNrCampo := 0;

                while lNrCampo < pStlNmCam.Count -1 do
                begin
                      if pStlNmCam.Strings[lNrCampo] = lStlChave.Strings[lNrChave] then
                      begin
                            Result := Result + pStlVrCam.Strings[lNrCampo] + '@';
                            lNrCampo := pStlNmCam.Count + 5;
                      end;

                      inc(lNrCampo);
                end;
          end;
      finally
          FreeAndNil(lStlChave);
      end;
end;

procedure TBrvSql.LogAtualizarBanco(pNmTabela : String;     pDsChaPri : String;
                                    pTpOperac : Integer;    pDsDicion : TBrvDicionario;
                                    pDsParent : TComponent; pNmFormul : String;
                                    pCdUsuari : Integer;    pTxSql    : String);
var lNmFormul : String;
begin
      if pDsDicion.Camada = Client then
      begin
            if pDsParent.Owner <> nil then
            begin
                  lNmFormul := pDsParent.Owner.Name;
            end else
            begin
                  lNmFormul := pDsParent.Name;
            end;

            pDsDicion.AtualizarLog(pNmTabela, pDsChaPri, pTpOperac, LogGetData,
                                   lNmFormul, pDsDicion.UserCode,   pTxSql);
      end else
      begin
            if pNmFormul = '' then
            begin
                  raise Exception.Create('Não é possível gravar log do objeto ' +
                                         pDsParent.Owner.Name + '.' +
                                         pDsParent.Name + '.' + #13#13 +
                                         'Nome do formulário não definido.');
            end;

            if pCdUsuari = 0 then
            begin
                  raise Exception.Create('Não é possível gravar log do objeto ' +
                                         pDsParent.Owner.Name + '.' +
                                         pDsParent.Name + '.' + #13#13 +
                                         'Código do usuáriio não definido.');
            end;

            pDsDicion.AtualizarLog(pNmTabela, pDsChaPri, pTpOperac, LogGetData,
                                   pNmFormul, pCdUsuari, pTxSql);
      end;
end;

end.
