unit BrvOracle;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  DbTables;

type
  TOracleToken = (OBof, OIdenti, OEspaco, OAspas, OComent, OErro, ODelimi,
                  OOpeRel, OEof, OFinali, OSimbol);

  TBrvOracle = class(TComponent)
  private
    { Private declarations }
       DsInstru : String;
       DsNewSql : String;
       TtCaract : Integer;
       NrPosica : Integer;
       DsNexCar : Char;
       DsLexema : String;
       procedure EncontrarProximoLexema;
       procedure ProximoCaracter(SnString : Boolean = False);
       procedure ProximoLexema;
       function  PalavraReservada : Boolean;
       function  TipoVariavel : Boolean;
       function  SubstituirIdentificadores : String;
       procedure ProcessarSelect;
       procedure ProcessarSubstring;
       procedure ProcessarExtract;
       procedure TransferirIntoAntesWhere;
       procedure ProcessarFrom;
       procedure ProcessarWhere;
       procedure ProcessarGroupOrder;
       function  ProcessarLeftRightJoin : String;
       function  ProcessarJoin: String;
       procedure ProcessarJoin2(var DsTabela : String; var DsCondic : String);
       procedure ProcessarInsertUpdateDelete;
       procedure ProcessarAlter;
       procedure ProcessarAlterTable;
       procedure ProcessarDrop;
       procedure ProcessarCreate;
       procedure ProcessarCreateTable;
       procedure ProcessarCreateIndex;
       procedure ProcessarColunasCreateTable;
       procedure ProcessarTriggerProcedure;
       procedure ProcessarCabecalhoTrigger;
       procedure ProcessarCabecalhoProcedure;
       procedure ProcessarVariaveisEntradaProcedure(TpDeclar : String);
       procedure ProcessarDeclaracoesLocais;
       procedure ProcessarNomeTipoVariavel(TpDeclar : String);
       procedure TrocarTipoVariavel(TpDeclar : String);
       procedure ProcessarCorpoBeginEnd;
       procedure ProcessarIf;
       procedure ProcessarCorpoIf;
       procedure ProcessarExecute;
       function  ProcessarParametrosExecute : Boolean;
       procedure ProcessarCast;
       procedure ProcessarIdentificadorLocal;
       procedure ProcessarWhile;
       procedure ProcessarCorpoWhile;
       function  ProcessarGenerator : Boolean;
       function  NomeGenerator(NrPosGen : Integer; DsLinha : String) : String;
       function  CampoChaveGenerator(NrPosGen : Integer;
                                                     DsLinha : String) : String;
       procedure ProcessarException;
       function  EncontrarMensagemExcessao : String;
  protected
    { Protected declarations }
  public
    { Public declarations }
       constructor Create(AOwner : TComponent); override;
       destructor  Destroy;                     override;
       function    SintaxeOracle(DsSql : String) : String;
  published
    { Published declarations }
  end;

var
  OToken    : TOracleToken;
  ODsDigito : set of char = ['0'..'9', 'a'..'z', 'A'..'Z', '.', '*',
                             '+', '-', '/', '%', ':', '_', '|', '$', '&', '#',
                             'À', 'Ã', 'Á', 'Ä', 'Â',
                             'à', 'ã', 'á', 'ä', 'â',
                             'È', 'É', 'Ë', 'Ê',
                             'è', 'é', 'ë', 'ê',
                             'Ì', 'Í', 'Ï', 'Î',
                             'ì', 'í', 'ï', 'î',
                             'Ò', 'Õ', 'Ó', 'Ö', 'Ô',
                             'ò', 'õ', 'ó', 'ö', 'ô',
                             'Ù', 'Ú', 'Ü', 'Û',
                             'ù', 'ú', 'ü', 'û',
                             'ç', 'Ç'];

  ODsApostr : set of char = [#39];
  ODsEspaco : set of char = [' ', #13, #10, #9];
  ODsDelimi : set of char = ['(', ')'];
  ODsOpeRel : set of char = ['<', '>', '='];
  ODsOpeFin : set of char = [';'];
  ODsSimbol : set of char = [','];
  DsReconh  : set of char = ['0'..'9', 'a'..'z', 'A'..'Z', '.', '*', '+', '-', '/', '%',
                             ':', '_', '|', '$', '&', '(', ')', '<', '>', ' ', #39];

procedure Register;

implementation
  const DsFinali = ';'; // caracter finalizador
  const DsApostr = #39;

constructor TBrvOracle.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
end;

destructor TBrvOracle.Destroy;
begin
      inherited  destroy;
end;

procedure Register;
begin
  RegisterComponents('Bravo Banco', [TBrvOracle]);
end;

function TBrvOracle.SintaxeOracle(DsSql : String) : String;
begin
      DsInstru := DsSql;
   // Substituindo as aspas =-=-=-=-=-=-=-=-=-=-=-=-=-=
      while Pos('"', DsInstru) > 0 do
      begin
            NrPosica := Pos('"', DsInstru);

            DsInstru[NrPosica] := #39;
      end;

      TtCaract := Length(DsSql);
      NrPosica := 0;
      OToken   := OBof;

      repeat
             EncontrarProximoLexema;
      until (PalavraReservada) or (OToken = OEof) or (DsLexema = '$ORACLE');

      if  DsLexema = '$ORACLE' then
      begin
            DsNewSql := '';

            repeat
                   EncontrarProximoLexema;

                   if OToken <> OEof then
                   begin
                         DsNewSql := DsNewSql + DsLexema;
                   end;
            until (OToken = OEof);
      end else
      begin
            if  DsLexema = 'SELECT' then
            begin
                  DsNewSql := DsLexema;
                  ProcessarSelect;
            end else
            begin
                  if  DsLexema = 'CREATE' then
                  begin
                        DsNewSql := DsLexema;
                        ProcessarCreate;
                  end else
                  begin
                        if  (DsLexema = 'INSERT') or (DsLexema = 'UPDATE') or
                            (DsLexema = 'DELETE') then
                        begin
                              DsNewSql := DsLexema;
                              ProcessarInsertUpdateDelete;
                        end else
                        begin
                              if  (DsLexema = 'ALTER') then
                              begin
                                    ProcessarAlter;
                              end else
                              begin
                                    if  (DsLexema = 'DROP') then
                                    begin
                                          DsNewSql := DsLexema;
                                          ProcessarDrop;
                                    end;
                              end;
                        end;
                  end;
            end;
      end;

      DsNewSql := StringReplace(DsNewSql, '<$hh', 'TO_DATE(',[rfReplaceAll,rfIgnoreCase]);
      DsNewSql := StringReplace(DsNewSql, 'hh$>', ',' + #39 + 'DD/MM/RRRR HH24:MI:SS' +
                                                  #39 + ')', [rfReplaceAll,rfIgnoreCase]);

      Result   :=  DsNewSql;
end;

procedure TBrvOracle.EncontrarProximoLexema;
var TokAnt : TOracleToken;
begin
      TokAnt := OToken;

      repeat
             ProximoCaracter;
      until (OToken <> TokAnt) or (OToken = OEof);

      if  OToken <> OEof then
      begin
            if  OToken = OAspas then
            begin
                  DsLexema := DsNexCar;

                  repeat
                         ProximoCaracter(True);
                         DsLexema := DsLexema + DsNexCar;
                  until OToken in [OAspas, OEof];

                  if OToken = OEof then
                  begin
                        raise Exception.Create('String não finalizada.');
                  end;
            end else
            begin
                  ProximoLexema;
                  DsLexema := UpperCase(DsLexema);
            end;
      end;
end;

procedure TBrvOracle.ProximoCaracter(SnString : Boolean = False);
begin
      Inc(NrPosica);

      if NrPosica > TtCaract then
      begin
            OToken := OEof;
      end else
      begin
            if DsInstru[NrPosica] <> '' then
            begin
                  DsNexCar := DsInstru[NrPosica]
            end else
            begin
                  DsNexCar := ' ';
            end;

            if  DsNexCar in ODsApostr then
            begin
                  OToken   := OAspas;
            end else
            begin
                  if (DsNexCar in ODsDigito) or (SnString) then
                  begin
                        OToken := OIdenti;
                  end else
                  begin
                        if  DsNexCar in ODsEspaco then
                        begin
                              OToken := OEspaco;
                        end else
                        begin
                              if  DsNexCar in ODsDelimi then
                              begin
                                    OToken := ODelimi;
                              end else
                              begin
                                    if  DsNexCar in ODsOpeRel then
                                    begin
                                          OToken   := OOpeRel;
                                    end else
                                    begin
                                          if  DsNexCar in ODsOpeFin then
                                          begin
                                                OToken   := OFinali;
                                          end else
                                          begin
                                                if  DsNexCar in ODsSimbol then
                                                begin
                                                      OToken   := OSimbol;
                                                end else
                                                begin
                                                      OToken   := OErro;
                                                end;
                                          end;
                                    end;
                              end;
                        end;
                  end;
            end;
      end;

      if  OToken = OErro then
      begin
            raise Exception.Create('Caracter "' + DsNexCar + '" na posiçao ' +
                                   IntToStr(NrPosica) + ' não foi reconhecido');
      end;
end;

procedure TBrvOracle.ProximoLexema;
var TokAnt  : TOracleToken;
begin
      TokAnt   :=  OToken;
      DsLexema := '';

      while OToken = TokAnt do
      begin
            DsLexema := DsLexema + DsNexCar;
            ProximoCaracter;
      end;

      if  DsLexema = '/*' then
      begin
            Dec(NrPosica);

            repeat
                   EncontrarProximoLexema;
            until (DsLexema = '*/') or (OToken = OEof);

            EncontrarProximoLexema;
            Dec(NrPosica);
      end else
      begin
            Dec(NrPosica);
      end;

      OToken := TokAnt;
end;

function TBrvOracle.PalavraReservada : Boolean;
begin
      if
         (DsLexema = 'ALTER')      or (DsLexema = 'AS')                 or
         (DsLexema = 'AFTER')      or (DsLexema = 'ADD')                or
         (DsLexema = 'BLOB')       or (DsLexema = 'BEGIN')              or
         (DsLexema = 'BEFORE')     or
         {(DsLexema = 'OR')       or} (DsLexema = 'REPLACE')            or
         (DsLexema = 'CREATE')     or (DsLexema = 'CAST')               or
         (DsLexema = 'CONSTRAINT') or (DsLexema = 'COLLUMN')            or
         (DsLexema = 'DELETE')     or (DsLexema = 'DECLARE')            or
         (DsLexema = 'DATE')       or (DsLexema = 'DROP')               or
         (DsLexema = 'DOUBLE')     or (DsLexema = 'DO')                 or
         (DsLexema = 'END')        or (DsLexema = 'ELSE')               or
         (DsLexema = 'EXECUTE')    or (DsLexema = 'EXCEPTION')          or
         (DsLexema = 'FROM')       or (DsLexema = 'FOR')                or
         (DsLexema = 'FLOAT')      or (DsLexema = 'VIEW')               or
         (DsLexema = 'GROUP')      or
         (DsLexema = 'INSERT')     or (DsLexema = 'INTEGER')            or
         (DsLexema = 'IF')         or (DsLexema = 'INDEX')              or
         (DsLexema = 'JOIN')       or
         (DsLexema = 'LEFT')       or
         (DsLexema = 'ORDER')      or
         (DsLexema = 'PRECISION')  or (DsLexema = 'PROCEDURE')          or
         (DsLexema = 'RIGHT')      or (DsLexema = 'RETURNING_VALUES')   or
         (DsLexema = 'RETURNS')    or
         (DsLexema = 'SELECT')     or (DsLexema = 'SMALLINT')           or
         (DsLexema = 'SYSTEM')     or
//         (DsLexema = 'SUSPEND')    or
         (DsLexema = 'TRIGGER')    or (DsLexema = 'THEN')               or
         (DsLexema = 'TABLE')      or
         (DsLexema = 'UPDATE')     or
         (DsLexema = 'VARIABLE')   or (DsLexema = 'VARCHAR')            or
         (DsLexema = 'WHERE')      or (DsLexema = 'WHILE')              then
      begin
            Result := True;
      end else
      begin
            Result := False;
      end;
end;

function TBrvOracle.TipoVariavel : Boolean;
begin
      if (DsLexema = 'INTEGER')  or (DsLexema = 'VARCHAR')  or
         (DsLexema = 'FLOAT')    or (DsLexema = 'DOUBLE')   or
         (DsLexema = 'DATE')     or (DsLexema = 'BLOB')     or
         (DsLexema = 'SMALLINT') or (DsLexema = 'NUMERIC')  or
         (DsLexema = 'TIMESTAMP') then
      begin
            Result := True;
      end else
      begin
            Result := False;
      end;
end;

function TBrvOracle.SubstituirIdentificadores : String;
begin
      Result := DsLexema;

      if  (Pos('NEW.', DsLexema) <> 0) or (Pos('OLD.', DsLexema) <> 0) then
      begin
            Result := ':' + DsLexema;
      end else
      begin
            if  (DsLexema = DsApostr + 'NOW'   + DsApostr) or
                (DsLexema = DsApostr + 'TODAY' + DsApostr) then
            begin
                  Result := 'SYSDATE';
            end else
            begin
                  if  DsLexema = 'SUSPEND' then
                  begin
                        Result := 'RETURN';
                  end else
                  begin
                        if  DsLexema = 'COALESCE' then
                        begin
                              Result := 'NVL';
                        end else
                        begin
                              if  DsLexema = 'EXTRACT' then
                              begin
                                    Result := 'TO_CHAR';
                              end;
                        end;
                  end;
            end;
      end;
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER A SELECT                                *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarSelect;
begin
      TransferirIntoAntesWhere;

      repeat
             EncontrarProximoLexema;

             if  (OToken <> OEof) and (DsLexema <> 'FROM') and
                                                            (DsLexema <> 'SUBSTRING') then
             begin
                  DsNewSql := DsNewSql + SubstituirIdentificadores;
             end;

             if  DsLexema = 'SELECT' then
             begin
                   ProcessarSelect;
             end else
             begin
                   if  DsLexema = 'SUBSTRING' then
                   begin
                         ProcessarSubstring;
                   end else
                   begin
                         if  DsLexema = 'EXTRACT' then
                         begin
                               ProcessarExtract;
                         end;
                   end;
             end;
      until (OToken in [OEof, OFinali]) or (DsLexema = 'FROM');

      if  (OToken = OEof) or (DsLexema <> 'FROM') then
      begin
            raise Exception.Create('Operador "FROM" não encontrado.');
      end;

      if  DsLexema = 'FROM' then
      begin
            DsNewSql := DsNewSql + DsLexema;
            ProcessarFrom;
      end;

      if  OToken <> OEof then
      begin
            if  DsLexema = 'WHERE' then
            begin
                  ProcessarWhere;
            end;
      end;

      if  OToken <> OEof then
      begin
            if  (DsLexema = 'GROUP') or (DsLexema = 'ORDER') then
            begin
                  ProcessarGroupOrder;

                  if  (DsLexema = 'GROUP') or (DsLexema = 'ORDER') then
                  begin
                        ProcessarGroupOrder;
                  end;

            end;
      end;
end;

procedure TBrvOracle.ProcessarSubstring;
begin
      DsNewSql := DsNewSql + 'SUBSTR';

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=  Encontrando Operador FROM do SUBSTRING =-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      repeat
             EncontrarProximoLexema;

             if  (OToken <> OEof) and (DsLexema <> 'FROM') then
             begin
                   DsNewSql := DsNewSql + SubstituirIdentificadores;
             end;
      until (OToken in [OEof, OFinali]) or (DsLexema = 'FROM');

      if  (OToken = OEof) or (DsLexema <> 'FROM') then
      begin
            raise Exception.Create('Operador "FROM" do "SUBSTRING" não encontrado.');
      end;

      DsNewSql := DsNewSql + ',';
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=  Encontrando Operador FROM do SUBSTRING =-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      repeat
             EncontrarProximoLexema;

             if  (OToken <> OEof) and (DsLexema <> 'FOR') then
             begin
                   DsNewSql := DsNewSql + SubstituirIdentificadores;
             end;
      until (OToken in [OEof, OFinali]) or (DsLexema = 'FOR');

      if  (OToken = OEof) or (DsLexema <> 'FOR') then
      begin
            raise Exception.Create('Operador "FOR" do "SUBSTRING" não encontrado.');
      end;

      DsNewSql := DsNewSql + ',';
end;

procedure TBrvOracle.ProcessarExtract;
var DsMascar : String;
begin
      EncontrarProximoLexema;

      if  (OToken = OEof) or (DsLexema <> '(') then
      begin
            raise Exception.Create('"(" não encontrado.');
      end;

      DsNewSql := DsNewSql + DsLexema;

      repeat
             EncontrarProximoLexema;

             if (OToken <> OEof) and (DsLexema <> 'FROM') then
             begin
                   if DsLexema = 'MONTH' then
                      DsMascar := #39 + 'MM' + #39
                   else
                        if DsLexema = 'YEAR' then
                           DsMascar := #39 + 'YYYY' + #39
                        else
                             if DsLexema = 'DAY' then
                                DsMascar := #39 + 'DD' + #39
                             else
                                  if DsLexema = 'HOUR' then
                                     DsMascar := #39 + 'HH24' + #39
                                  else
                                     DsNewSql := DsNewSql + SubstituirIdentificadores;
             end;

      until (OToken in [OEof, OFinali]) or (DsLexema = 'FROM');

      if  (OToken = OEof) or (DsLexema <> 'FROM') then
      begin
            raise Exception.Create('Operador "FROM" do "EXTRACT" não encontrado.');
      end;

      repeat
             EncontrarProximoLexema;
      until (Trim(DsLexema) <> '');

      DsNewSql := DsNewSql + DsLexema + ',' + DsMascar;
end;

procedure TBrvOracle.TransferirIntoAntesWhere;
var DsSelect : String;
    DsInto   : String;
    NrNewPos : Integer;
    NrPosInt : Integer;
    NrPosFro : Integer;
begin
      NrNewPos := NrPosica;
      DsSelect := '';

      while (DsInstru[NrNewPos] <> ';') and (NrNewPos <= TtCaract) do
      begin
            DsSelect := DsSelect + DsInstru[NrNewPos];
            inc(NrNewPos);
      end;

      NrPosInt :=  Pos('into', LowerCase(DsSelect));

      if  (NrPosInt <> 0)                                 and
          (DsSelect[NrPosInt + 4] in [' ', #10, #13, #0]) and
          (DsSelect[NrPosInt - 1] in [' ', #10, #13, #0]) then
      begin
            DsInto := Copy(DsSelect, NrPosInt, Length(DsSelect));
            Delete(DsSelect, NrPosInt, Length(DsSelect));

            NrPosFro := Pos('from', LowerCase(DsSelect));
            Insert(' ' + DsInto + ' ', DsSelect, NrPosFro);

            Delete(DsInstru, NrPosica, NrNewPos - NrPosica);
            Insert(DsSelect, DsInstru, NrPosica);

            TtCaract := Length(DsInstru);
      end;
end;

procedure TBrvOracle.ProcessarFrom;
begin
      repeat
             EncontrarProximoLexema;

             if  OToken <> OEof then
             begin
                   if  DsLexema = 'WHERE' then
                   begin
                         DsNewSql := DsNewSql + DsLexema;
                   end else
                   begin
                         if (DsLexema = 'LEFT') or (DsLexema = 'RIGHT') or
                            (DsLexema = 'JOIN') then
                         begin
                               DsNewSql := DsNewSql + ' ' + ProcessarLeftRightJoin;
                         end else
                         begin
                               if  (DsLexema <> 'FROM')  and (DsLexema <> 'GROUP') and
                                   (DsLexema <> 'ORDER') then
                               begin
                                     DsNewSql := DsNewSql + SubstituirIdentificadores;
                               end;
                         end;
                   end
             end;
      until (OToken   in [OEof, OFinali]) or
            (DsLexema = 'WHERE') or (DsLexema = 'ORDER') or
            (DsLexema = 'GROUP') or (DsLexema = 'FROM');
end;

procedure TBrvOracle.ProcessarWhere;
begin
      repeat
             EncontrarProximoLexema;

             if  DsLexema = 'SUBSTRING' then
             begin
                   ProcessarSubstring;
                   DsLexema := ' '; //"FOR" é palavra reservada e neste caso não pode encerrar o loop
             end else
             begin
                   if  DsLexema = 'EXTRACT' then
                   begin
                         DsNewSql := DsNewSql + SubstituirIdentificadores;
                         ProcessarExtract;
                   end else
                   begin
                         if  (OToken <> OEof) and (not PalavraReservada) then
                         begin
                               if  DsLexema = '=' then
                               begin
                                     DsNewSql := DsNewSql + DsLexema;
                               end else
                               begin
                                     DsNewSql := DsNewSql + SubstituirIdentificadores;
                               end;
                         end;
                   end;
             end;

             if  DsLexema = 'SELECT' then
             begin
                   DsNewSql := DsNewSql + DsLexema;
                   ProcessarSelect;
             end else
             begin
                   if  DsLexema = 'AS' then
                   begin
                         DsNewSql := DsNewSql + DsLexema;

                         repeat
                                EncontrarProximoLexema;

                                if  (OToken <> OEof) and (not PalavraReservada) then
                                begin
                                      DsNewSql := DsNewSql + SubstituirIdentificadores;
                                end;
                         until (OToken in [OEof, OSimbol]) or (PalavraReservada);
                   end;
             end;
      until (OToken in [OEof, OFinali]) or (PalavraReservada);
end;

procedure TBrvOracle.ProcessarGroupOrder;
begin
      DsNewSql := DsNewSql + DsLexema;

      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until OToken in [OEof, OIdenti];

      if  DsLexema <> 'BY' then
      begin
            raise Exception.Create('Operador "BY" não encontrado.');
      end;

      DsNewSql := DsNewSql + DsLexema;

      repeat
             EncontrarProximoLexema;

             if  DsLexema = 'EXTRACT' then
             begin
                   DsNewSql := DsNewSql + SubstituirIdentificadores;
                   ProcessarExtract;
             end else
             begin
                   if (OToken <> OEof) and (not PalavraReservada) then
                   begin
                         DsNewSql := DsNewSql + SubstituirIdentificadores;
                   end;
             end;
      until (OToken = OEof) or (PalavraReservada);
end;

function  TBrvOracle.ProcessarLeftRightJoin : String;
var DsTabela : String;
    DsCondic : String;
begin
      Result := ProcessarJoin;
{
      DsTabela := '';

      ProcessarJoin2(DsTabela, DsCondic);

      Result := DsTabela + #13#10 + 'WHERE ' + DsCondic;
}
end;

function TBrvOracle.ProcessarJoin : String;
var TpJoin   : String;
begin
      Result := DsLexema;
      TpJoin := DsLexema;

      if  TpJoin <> 'JOIN' then
      begin
        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // =-=-= Encontrando identificador "JOIN"  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
           repeat
                  EncontrarProximoLexema;
                  Result := Result + DsLexema;
           until OToken in [OEof, OIdenti];

           if  (OToken = OEof) then
           begin
                 raise Exception.Create(TpJoin + ' JOIN não finalizado.');
           end;

           if  (DsLexema <> 'JOIN') then
           begin
                 raise Exception.Create('Operador "JOIN" não encontrado.');
           end;
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-= Encontrando o nome da tabela do join -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
             Result := Result + DsLexema;
      until OToken in [OEof, OIdenti];

      if  OToken = OEof then
      begin
            raise Exception.Create('Tabela do JOIN não foi encontrada.');
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-= Encontrando o identificador do Alias da tabela do join -=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
             Result := Result + DsLexema;
      until OToken in [OEof, OIdenti];

      if  OToken = OEof then
      begin
            raise Exception.Create('Alias da tabela do JOIN não encontrada.');
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-= Encontrando o identificador ON do Join -=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
             Result := Result + DsLexema;
      until OToken in [OEof, OIdenti];

      if  (OToken = OEof) or (DsLexema <> 'ON') then
      begin
            raise Exception.Create('Operador "ON" não encontrado.');
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken <> OEof then
             begin
                   if  DsLexema = 'SELECT' then
                   begin
                         raise Exception.Create(
                                         'Select em Joins, não suportado pelo tradutor.');
                   end else
                   begin
                         //if (DsLexema <> 'LEFT')  and (DsLexema <> 'RIGHT') then - Alterado por Enio
                         if (DsLexema <> 'LEFT')  and (DsLexema <> 'RIGHT') and
                            (DsLexema <> 'GROUP') and (DsLexema <> 'ORDER') then
                         begin
                               Result := Result + DsLexema;
                         end;
                   end;
             end;
      until (DsLexema = 'WHERE') or (DsLexema = 'LEFT')  or
            (DsLexema = 'RIGHT') or (DsLexema = 'ORDER') or
            (DsLexema = 'GROUP') or (DsLexema = 'JOIN')  or (OToken = OEof);

      if  OToken <> OEof then
      begin
            if  (DsLexema = 'RIGHT') or (DsLexema = 'LEFT') or (DsLexema = 'JOIN') then
            begin
                  Result := Result + ProcessarJoin;
            end;
      end;
end;

procedure TBrvOracle.ProcessarJoin2(var DsTabela : String;
                                     var DsCondic : String);
var DsAlias  : String;
    DsOldSql : String;
    TpJoin   : String;
begin
      TpJoin := DsLexema;

      if  TpJoin <> 'JOIN' then
      begin
        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // =-=-= Encontrando identificador "JOIN"  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
           repeat
                  EncontrarProximoLexema;
           until OToken in [OEof, OIdenti];

           if  (OToken = OEof) then
           begin
                 raise Exception.Create(TpJoin + ' JOIN não finalizado.');
           end;

           if  (DsLexema <> 'JOIN') then
           begin
                 raise Exception.Create('Operador "JOIN" não encontrado.');
           end;
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-= Encontrando o nome da tabela do join -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
      until OToken in [OEof, OIdenti];

      if  OToken = OEof then
      begin
            raise Exception.Create('Tabela do JOIN não foi encontrada.');
      end;

      DsTabela := DsTabela + ', ' + DsLexema;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-= Encontrando o identificador do Alias da tabela do join -=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
      until OToken in [OEof, OIdenti];

      if  OToken = OEof then
      begin
            raise Exception.Create('Alias da tabela do JOIN não encontrada.');
      end;

      DsAlias  := DsLexema + '.';
      DsTabela := DsTabela + ' ' + DsLexema;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-= Encontrando o identificador ON do Join -=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
      until OToken in [OEof, OIdenti];

      if  (OToken = OEof) or (DsLexema <> 'ON') then
      begin
            raise Exception.Create('Operador "ON" não encontrado.');
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken <> OEof then
             begin
                   if  DsLexema = 'SELECT' then
                   begin
                         DsOldSql := DsNewSql;
                         DsNewSql := DsLexema;
                         ProcessarSelect;
                         DsCondic := DsCondic + DsNewSql;
                         DSNewSql := DsOldSql;
                   end else
                   begin
                         if (DsLexema <> 'WHERE') and (DsLexema <> 'LEFT')  and
                            (DsLexema <> 'RIGHT') and (DsLexema <> 'ORDER') and
                            (DsLexema <> 'GROUP') and (DsLexema <> 'JOIN') then
                         begin
                               DsCondic := DsCondic + SubstituirIdentificadores;

                               if  TpJoin = 'LEFT' then
                               begin
                                     if  (OToken <> ODelimi) and (OToken <> OOpeRel)  then
                                     begin
                                           if (Pos(DsAlias, DsLexema) <> 0) and
                                              (Length(DsAlias) = Pos('.', DsLexema)) then
                                           begin
                                                 DsCondic := DsCondic + ' (+) ';
                                           end;
                                     end;
                               end else
                               begin
                                     if  TpJoin = 'RIGHT' then
                                     begin
                                           if  OToken = OIdenti then
                                           begin
                                                 if (Pos(DsAlias, DsLexema) = 0) and
                                                    (Length(DsAlias) =
                                                                  Pos('.', DsLexema)) then
                                                 begin
                                                       DsCondic := DsCondic + ' (+) ';
                                                 end;
                                           end;
                                     end;
                               end;
                         end;
                   end;
             end;
      until (DsLexema = 'WHERE') or (DsLexema = 'LEFT')  or
            (DsLexema = 'RIGHT') or (DsLexema = 'ORDER') or
            (DsLexema = 'GROUP') or (DsLexema = 'JOIN')  or (OToken = OEof);

      if  OToken <> OEof then
      begin
            if  (DsLexema <> 'GROUP') and (DsLexema <> 'ORDER') then
            begin
                  DsCondic := DsCondic + ' AND ';
            end;

            if  (DsLexema = 'RIGHT') or (DsLexema = 'LEFT') or (DsLexema = 'JOIN') then
            begin
                  ProcessarJoin2(DsTabela, DsCondic);
            end;
      end;
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER O ALTER                                 *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarAlter;
var DsSqlAux : String;
begin
      DsSqlAux := '';
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-= Encontrando o identificador TABLE =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsSqlAux := DsSqlAux + DsLexema;
             end;
      until (OToken = OEof) or (PalavraReservada);

      if  OToken = OEof then
      begin
            raise Exception.Create('Instrução "ALTER" não finalizada');
      end;

      if  (DsLexema <> 'TABLE')   and (DsLexema <> 'PROCEDURE') and
          (DsLexema <> 'TRIGGER') and (DsLexema <> 'SYSTEM') then
      begin
            raise Exception.Create('Operador "TABLE", "SYSTEM", "PROCEDURE" ' +
                                   'ou "TRIGGER" não encontrado');
      end;

      if DsLexema = 'TABLE' then
      begin
            DsNewSql := 'ALTER' + DsSqlAux;
            ProcessarAlterTable;
      end
      else if (DsLexema = 'PROCEDURE') or (DsLexema = 'TRIGGER') then
           begin
                 DsNewSql := 'CREATE OR REPLACE ' + DsLexema;
                 ProcessarTriggerProcedure;
           end
      else if DsLexema = 'SYSTEM' then
           begin
                 DsNewSql := 'ALTER SYSTEM' + DsSqlAux;

                 repeat
                        EncontrarProximoLexema;
                        DsNewSql := DsNewSql + DsLexema;
                 until OToken = OEof;
           end;
end;

procedure TBrvOracle.ProcessarAlterTable;
var lTpDeclar : String;
begin
      lTpDeclar := 'ALTER TABLE';
      DsNewSql := DsNewSql + DsLexema;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-= Encontrando o nome da tabela -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until OToken in [OEof, OIdenti];

      if  OToken = OEof then
      begin
            raise Exception.Create('Nome da tabela não encontrado');
      end;

      DsNewSql := DsNewSql + DsLexema;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-= Encontrando o identificador ADD ou DROP =-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until (OToken = OEof) or (PalavraReservada);

      if  OToken = OEof then
      begin
            raise Exception.Create('Operador "ADD" ou "DROP" não encontrado');
      end;

      if  (DsLexema <> 'ADD') and (DsLexema <> 'DROP') then
      begin
            raise Exception.Create('Operador "ADD" ou "DROP" não encontrado');
      end;

      if DsLexema = 'DROP'  then
      begin
            lTpDeclar := lTpDeclar + ' DROP';
      end;

      DsNewSql := DsNewSql + DsLexema;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-= Encontrando o identificador CONSTRAINT OU COLLUMN -=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until (OToken = OEof) or (PalavraReservada);

      if  OToken = OEof then
      begin
            raise Exception.Create('Operador "CONSTRAINT" ou "COLLUMN" não encontrado');
      end;

      if  (DsLexema <> 'CONSTRAINT') and (DsLexema <> 'COLLUMN') then
      begin
            raise Exception.Create('Operador "CONSTRAINT" ou "COLLUMN" não encontrado');
      end;

      if  DsLexema = 'CONSTRAINT' then
      begin
            DsNewSql := DsNewSql + DsLexema;

            repeat
                   EncontrarProximoLexema;
                   if  OToken <> OEof then
                   begin
                         DsNewSql := DsNewSql + SubstituirIdentificadores;
                   end;
            until OToken in [OEof, OFinali];
      end else
      begin
            if lTpDeclar = 'ALTER TABLE' then
            begin
                  ProcessarNomeTipoVariavel('CREATE TABLE');
            end else
            begin
                  DsNewSql := DsNewSql + 'COLUMN ';

                  ProcessarDrop;
            end;
      end;
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER O DROP                                  *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarDrop;
begin
      repeat
             EncontrarProximoLexema;

             if  OToken <> OEof then
             begin
                   DsNewSql := DsNewSql + SubstituirIdentificadores;
             end;
      until OToken in [OEof, OFinali];
end;
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER O INSERT, UPDATE E DELETE               *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarInsertUpdateDelete;
begin
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end else
             begin
                   if  PalavraReservada then
                   begin
                         if  DsLexema = 'SELECT' then
                         begin
                               DsNewSql := DsNewSql + DsLexema;
                               ProcessarSelect;

                               if not (OToken in [OEof, OFinali]) then
                               begin
                                     DsNewSql := DsNewSql + DsLexema;
                               end;
                         end else
                         begin
                               DsNewSql := DsNewSql + SubstituirIdentificadores;
                         end;
                   end else
                   begin
                         if  not (OToken in [OEof, OFinali]) then
                         begin
                               DsNewSql := DsNewSql + SubstituirIdentificadores;
                         end else
                         begin
                               if  OToken = OFinali then
                               begin
                                     DsNewSql := DsNewSql + SubstituirIdentificadores;
                               end;
                         end;
                   end;
             end;
      until OToken in [OEof, OFinali];

      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end else
      until OToken in [OEof, OIdenti];
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER O CREATE                                *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarCreate;
begin
      repeat
             EncontrarProximoLexema;
      until (OToken = OEof) or (PalavraReservada) or (DsLexema = 'OR');

      // Processar OR REPLACE
      if DsLexema = 'OR' then
      begin
            DsNewSql := DsNewSql + ' ' + DsLexema;

            repeat
                   EncontrarProximoLexema;
            until (OToken = OEof) or (PalavraReservada);

            if  (OToken = OEof) or (DsLexema <> 'REPLACE') then
            begin
                  raise Exception.Create('Operador "REPLACE" não encontrado.');
            end;

      end;

      DsNewSql := DsNewSql + ' ' + DsLexema;

      repeat
             EncontrarProximoLexema;
      until (OToken = OEof) or (PalavraReservada);

      if  (OToken = OEof) or
          ((DsLexema <> 'TRIGGER') and (DsLexema <> 'PROCEDURE') and
           (DsLexema <> 'TABLE')   and (DsLexema <> 'INDEX')     and
           (DsLexema <> 'VIEW'))   then
      begin
            raise Exception.Create('Operador "TRIGGER", "PROCEDURE", "INDEX", "VIEW" ou ' +
                                                     '"TABLE" não encontrado.');
      end;

      DsNewSql := DsNewSql + ' ' + DsLexema;


      if  (DsLexema = 'TRIGGER') or (DsLexema = 'PROCEDURE') then
      begin
            ProcessarTriggerProcedure;
      end else
      begin
            if  DsLexema = 'TABLE' then
            begin
                  ProcessarCreateTable;
            end else
            begin
                  ProcessarCreateIndex; // INDEX  e VIEW
            end;
      end;
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER CREATE INDEX                            *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarCreateIndex;
begin
      repeat
             EncontrarProximoLexema;
             DsNewSql := DsNewSql + SubstituirIdentificadores;
      until OToken in [OEof, Ofinali];

      Delete(DsNewSql, Length(DsNewSql), 1);
      // Tradutor estava gerando um parentese a mais no fim da string. corrigir depois.
end;
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER A TABLE                                 *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarCreateTable;
begin
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O NOME DA TABELA  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until OToken in [OEof, OIdenti];

      if  OToken = OEof then
      begin
            raise Exception.Create('Nome da tabela não foi encontrado.');
      end;

      DsNewSql := DsNewSql + DsLexema;

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO A DECLARAÇÃO DAS COLUNAS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken in [OEspaco, ODelimi] then
             begin
                   DsNewSql := DsNewSql + SubstituirIdentificadores;
             end;
      until OToken in [OEof, ODelimi];

      if OToken = OEof then
      begin
            raise Exception.Create('Create Table não finalizado.');
      end;

      ProcessarColunasCreateTable;
end;

procedure TBrvOracle.ProcessarColunasCreateTable;
begin
      ProcessarNomeTipoVariavel('CREATE TABLE');

      if  (OToken <> OEof) then
      begin
            if  (OToken <> OSimbol) and (OToken <> ODelimi) then
            begin
                  repeat
                         EncontrarProximoLexema;

                         if  OToken in [OEspaco, OIdenti, OSimbol, ODelimi] then
                         begin
                               DsNewSql := DsNewSql + SubstituirIdentificadores;
                         end;
                  until OToken in [OEof, OSimbol, ODelimi];
            end;

            if  OToken = OSimbol then
            begin
                  ProcessarColunasCreateTable;
            end else
            begin
                  repeat
                         EncontrarProximoLexema;

                         if  OToken in [OEspaco, OFinali] then
                         begin
                               DsNewSql := DsNewSql + SubstituirIdentificadores;
                         end;
                  until OToken in [OEof, OIdenti];
            end;
      end
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER A TRIGGER E O PROCEDIMENTO              *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarTriggerProcedure;
var SnDeclar : Boolean;
begin
   // Retirando os dois pontos ':' -=-=-=-=-=-=-=-=-=-=
      while pos(':', DsInstru) <> 0 do
      begin
            Delete(DsInstru, pos(':', DsInstru), 1);
      end;
      TtCaract := Length(DsInstru);
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      if  DsLexema = 'TRIGGER' then
      begin
            ProcessarCabecalhoTrigger;
            SnDeclar := True;
      end else
      begin
            ProcessarCabecalhoProcedure;
            SnDeclar := False;
      end;

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO A DECLARAÇÃO DAS VARIÁVEIS E O BEGIN =-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until OToken in [OEof, OIdenti];

      if  OToken = OEof then
      begin
            raise Exception.Create('Operador "BEGIN" não encontrado.');
      end;

      if  DsLexema = 'DECLARE' then
      begin
            if  SnDeclar then
            begin
                  DsNewSql := DsNewSql + SubstituirIdentificadores + #13#10;
            end;

            ProcessarDeclaracoesLocais;
      end;

      if  DsLexema <> 'BEGIN' then
      begin
            raise Exception.Create('Operador "BEGIN" não encontrado.');
      end;

      DsNewSql := DsNewSql + DsLexema;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  PROCESSANDO O CORPO -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until OToken in [OEof, OIdenti];

      ProcessarCorpoBeginEnd;

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  FINALIZANDO -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      if  DsLexema <> 'END' then
      begin
            raise Exception.Create('Operador "END" não encontrado para finalizar.');
      end;

      DsNewSql := DsNewSql + DsLexema + DsFinali;
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER A TRIGGER                               *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarCabecalhoTrigger;
var NmTabela : String;
    TpBefAft : String;
    TpInsUpd : String;
begin
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O NOME DA TRIGGER  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until OToken in [OEof, OIdenti];

      if  (OToken = OEof) then
      begin
            raise Exception.Create('Trigger não finalizada.');
      end;

      if  (PalavraReservada) then
      begin
            raise Exception.Create('Nome da trigger não encontrado');
      end;

      DsNewSql := DsNewSql + DsLexema;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O OPERADOR FOR =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
      until (OToken = OEof) or (PalavraReservada);

      if  (OToken = OEof) then
      begin
            raise Exception.Create('Trigger não finalizada.');
      end;

      if  (DsLexema <> 'FOR') then
      begin
            raise Exception.Create('Operador "FOR" não encontrado.');
      end;

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O NOME DA TABELA =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
      until OToken in [OEof, OIdenti];

      if  (OToken = OEof) then
      begin
            raise Exception.Create('Trigger não finalizada.');
      end;

      if  PalavraReservada then
      begin
            raise Exception.Create('Nome da tabela não encontrado.');
      end;

      NmTabela := DsLexema;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O TIPO DA TRIGGER -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
      until (OToken = OEof) or (PalavraReservada);

      if  (OToken = OEof) then
      begin
            raise Exception.Create('Trigger não finalizada.');
      end;

      if  (DsLexema <> 'AFTER') and (DsLexema <> 'BEFORE') then
      begin
            raise Exception.Create('Operador "AFTER" ou "BEFORE" não foi encontrado');
      end;

      TpBefAft := DsLexema;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O TIPO DA TRIGGER -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
      until (OToken = OEof) or (PalavraReservada);

      if  (OToken = OEof) then
      begin
            raise Exception.Create('Trigger não finalizada.');
      end;

      if  (DsLexema <> 'INSERT') and (DsLexema <> 'UPDATE') and
          (DsLexema <> 'DELETE') then
      begin
            raise Exception.Create('Operador "INSERT", "UPDATE" ou "DELETE" ' +
                                                                    'não foi encontrado');
      end;

      TpInsUpd := DsLexema;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O FINAL DO CABECALHO  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
      until (OToken = OEof) or (DsLexema = 'AS');

      if  (OToken = OEof) then
      begin
            raise Exception.Create('Trigger não finalizada.');
      end;

      if  DsLexema <> 'AS' then
      begin
            raise Exception.Create('Cabeçalho não finalizado. Operador "AS" ' +
                                                                        'não encontrado');
      end;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  MONTANDO O CABEÇALHO  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      DsNewSql := DsNewSql + ' ' + TpBefAft + ' ' + TpInsUpd + ' ON ' +
                  NmTabela + ' FOR EACH ROW';
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER A PROCEDURE                             *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarCabecalhoProcedure;
var SnFecPar : Boolean;
begin
      SnFecPar := False;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O NOME DO PROCEDIMENTO =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until OToken in [OEof, OIdenti];

      if  (OToken = OEof) then
      begin
            raise Exception.Create('Procedure não finalizada.');
      end;

      if  (PalavraReservada) then
      begin
            raise Exception.Create('Nome do procedimento não encontrado');
      end;

      DsNewSql := DsNewSql + DsLexema;

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO DECLARAÇÕES DE ENTRADA E SAÍDA =-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;

      until (OToken in [OEof, ODelimi]) or (PalavraReservada);

      if  (OToken = OEof) then
      begin
            raise Exception.Create('Procedure não finalizada.');
      end;

      if  DsLexema = '(' then
      begin
            SnFecPar := True;

            DsNewSql := DsNewSql + DsLexema;

            ProcessarVariaveisEntradaProcedure(' IN');

            if  DsLexema <> ')' then
            begin
                  raise Exception.Create('Finalizador ")" não encontrado.');
            end;

            repeat
                   EncontrarProximoLexema;
            until (OToken = OEof) or (PalavraReservada);
      end;

      if DsLexema = 'RETURNS' then
      begin
            Delete(DsNewSql, Length(DsNewSql), 1);
            if  not SnFecPar then
            begin
                  DsNewSql := DsNewSql + '(';
            end else
            begin
                  DsNewSql := DsNewSql + ', ';
            end;

            repeat
                   EncontrarProximoLexema;

                   if OToken = OEspaco then
                   begin
                         DsNewSql := DsNewSql + DsLexema;
                   end;
             until OToken in [OEof, ODelimi];

            if  (OToken = OEof) or (DsLexema <> '(') then
            begin
                  raise Exception.Create('"(" não encontrado.');
            end;

            ProcessarVariaveisEntradaProcedure(' OUT');

            if  DsLexema <> ')' then
            begin
                 raise Exception.Create('Finalizador ")" não encontrado.');
            end;

            repeat
                   EncontrarProximoLexema;
            until (OToken = OEof) or (PalavraReservada);
      end;

      if DsLexema <> 'AS' then
      begin
            raise Exception.Create('Operador "AS" não encontrado.');
      end;

      DsNewSql := DsNewSql + ' IS';
end;

procedure TBrvOracle.ProcessarVariaveisEntradaProcedure(TpDeclar : String);
begin
      ProcessarNomeTipoVariavel(TpDeclar);

      if  DsLexema <> ')' then
      begin
            ProcessarVariaveisEntradaProcedure(TpDeclar);
      end
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA DECLARAÇÃO DE VARIÁVEIS LOCAIS                    *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarDeclaracoesLocais;
begin
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O OPERADOR VARIABLE -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until (OToken = OEof) or (PalavraReservada);

      if  (OToken = OEof) then
      begin
            raise Exception.Create('Trigger não finalizada.');
      end;

      if  DsLexema <> 'VARIABLE' then
      begin
            raise Exception.Create('Operador "VARIABLE" não foi encontrado');
      end;

      ProcessarNomeTipoVariavel('VAR LOCAL');

      if  OToken <> OFinali then
      begin
            raise Exception.Create('Operador finalizador não foi encontrado');
      end;

      DsNewSql := DsNewSql + DsFinali;

      if  OToken = OEof then
      begin
            raise Exception.Create('Trigger não finalizada.');
      end;

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until OToken in [OEof, OIdenti];

      if  DsLexema = 'DECLARE' then
      begin
            ProcessarDeclaracoesLocais;
      end;
end;

procedure TBrvOracle.ProcessarNomeTipoVariavel(TpDeclar : String);
begin
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO A VARIAVEL =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until OToken in [OEof, OIdenti];

      if  (OToken = OEof) then
      begin
            raise Exception.Create('Declaração da variável não finalizada.');
      end;

      if  PalavraReservada then
      begin
            raise Exception.Create('Nome da varável não encontrada');
      end;

      if  (TpDeclar <> 'CREATE TABLE') and (TpDeclar <> 'VAR LOCAL') then
      begin
            DsNewSql := DsNewSql + DsLexema + TpDeclar;
      end else
      begin
            DsNewSql := DsNewSql + DsLexema;
      end;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O TIPO DA VARIÁVEL =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until (OToken = OEof) or (OToken = OIdenti);

      if  (OToken = OEof) then
      begin
            raise Exception.Create('Tipo de variável não encontrado.');
      end;

      if  not TipoVariavel then
      begin
            raise Exception.Create('"' + DsLexema +
                                         '" não é um tipo de variável válido.');
      end;

      TrocarTipoVariavel(TpDeclar);
end;

procedure TBrvOracle.TrocarTipoVariavel(TpDeclar : String);
var SnProLex : Boolean;
begin
      SnProLex := True;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=  VarChar -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      if  DsLexema = 'VARCHAR' then
      begin
            DsNewSql := DsNewSql + 'VARCHAR2';

            repeat
                   EncontrarProximoLexema;

                   if  OToken <> OFinali then
                   begin
                         if (TpDeclar = 'CREATE TABLE') or (TpDeclar = 'VAR LOCAL') then
                         begin
                               DsNewSql := DsNewSql + SubstituirIdentificadores;
                         end;
                   end;
            until (OToken in [OEof, OFinali, OSimbol]) or
                  (DsLexema = '))') or (DsLexema = ')');

            if  Dslexema = '))' then
            begin
                  DsLexema := ')';
                  if (TpDeclar = ' OUT') or (TpDeclar = ' IN') then
                  begin
                        DsNewSql := DsNewSql + DsLexema;
                  end;
            end else
            begin
                  if  DsLexema = ')' then
                  begin
                        repeat
                               EncontrarProximoLexema;

                               if  (OToken <> OFinali) and (OToken <> OEof) then
                               begin
                                     DsNewSql := DsNewSql + SubstituirIdentificadores;
                               end;
                        until (OToken in [OEof, OFinali, OSimbol]) or (DsLexema = ')');
                  end;
            end;

            SnProLex := False;
      end
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=  Float and Double  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      else if  (DsLexema = 'FLOAT') or (DsLexema = 'DOUBLE') then
           begin
                 DsNewSql := DsNewSql + 'NUMBER';

                 if  DsLexema = 'DOUBLE' then
                 begin
                       repeat
                              EncontrarProximoLexema;
                       until (OToken = OEof) or (DsLexema = 'PRECISION');
                 end;
           end
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=  TIMESTAMP -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      else if  DsLexema = 'TIMESTAMP' then
           begin
                 DsNewSql := DsNewSql + 'DATE';
           end
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=  SmallInt  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      else if  DsLexema = 'SMALLINT' then
           begin
                 DsNewSql := DsNewSql + 'INTEGER';
           end
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=  Blob  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      else if  DsLexema = 'BLOB' then
           begin
                 repeat
                        EncontrarProximoLexema;
                 until (OToken in [OEof, OFinali, OIdenti, OSimbol]) or (DsLexema = ')');

                 if  DsLexema = 'SUB_TYPE' then
                 begin
                       DsNewSql := DsNewSql + 'CLOB';

                       repeat
                              EncontrarProximoLexema;
                       until (OToken in [OEof, OFinali, OIdenti, OSimbol]) or
                                                                         (DsLexema = ')');
                 end else
                 begin
                       DsNewSql := DsNewSql + 'BLOB';

                       if OToken in [OSimbol, OIdenti, ODelimi] then
                       begin
                             if  OToken <> OSimbol then
                             begin
                                   DsNewSql := DsNewSql + ' ' + DsLexema;
                             end else
                             begin
                                   DsNewSql := DsNewSql + DsLexema;
                             end;
                       end;

                       SnProLex := False;
                 end;
           end
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=  Integer  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      else if DsLexema = 'INTEGER' then
           begin
                 if TpDeclar = 'CREATE TABLE' then
                 begin
                       DsNewSql := DsNewSql + 'NUMBER(10)';
                 end else
                 begin
                       DsNewSql := DsNewSql + DsLexema;
                 end
           end
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=  Numeric  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      else if DsLexema = 'NUMERIC' then
           begin
                 DsNewSql := DsNewSql + 'NUMBER';

                 repeat
                        EncontrarProximoLexema;

                        if  OToken <> OFinali then
                        begin
                              if (TpDeclar = 'CREATE TABLE') or
                                                             (TpDeclar = 'VAR LOCAL') then
                              begin
                                    DsNewSql := DsNewSql + DsLexema;
                              end;
                        end;
                 until (OToken in [OEof, OFinali]) or (DsLexema = '))') or (DsLexema=')');

                 if  Dslexema = '))' then
                 begin
                       DsLexema := ')';
                 end else
                 begin
                       if  DsLexema = ')' then
                       begin
                             repeat
                                    EncontrarProximoLexema;

                                    if  OToken <> OFinali then
                                    begin
                                          DsNewSql := DsNewSql + DsLexema;
                                    end;
                             until (OToken in [OEof, OFinali, OSimbol]) or (DsLexema=')');
                       end;
                 end;

                 SnProLex := False;
           end
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      else begin
                 DsNewSql := DsNewSql + DsLexema;
           end;

      if  SnProLex then
      begin
            repeat
                   EncontrarProximoLexema;

                   if  (OToken <> OFinali) and (OToken <> OEof) then
                   begin
                         DsNewSql := DsNewSql + DsLexema;
                   end;
            until (OToken in [OEof, OFinali, OIdenti, OSimbol]) or (DsLexema = ')');
      end;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER O CORPO DO BEGIN ... END                *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarCorpoBeginEnd;
begin
      if  (DsLexema = 'INSERT') or (DsLexema = 'UPDATE') or (DsLexema = 'DELETE') then
      begin
            DsNewSql := DsNewSql + DsLexema;
            ProcessarInsertUpdateDelete;
            ProcessarCorpoBeginEnd;
      end
      else if  DsLexema = 'SELECT' then
           begin
                 DsNewSql := DsNewSql + DsLexema;
                 ProcessarSelect;
                 ProcessarCorpoBeginEnd;
      end
      else if  DsLexema = 'IF' then
           begin
                 DsNewSql := DsNewSql + DsLexema;
                 ProcessarIf;
                 ProcessarCorpoBeginEnd;
      end
      else if  DsLexema = 'EXECUTE' then
           begin
                 ProcessarExecute;
                 ProcessarCorpoBeginEnd;
      end
      else if  DsLexema = 'CREATE' then
           begin
                 DsNewSql := DsNewSql + DsLexema;
                 ProcessarCreate;
                 ProcessarCorpoBeginEnd;
      end
      else if DsLexema = 'CAST' then
           begin
                 DsNewSql := DsNewSql + DsLexema;
                 ProcessarCast;
                 ProcessarCorpoBeginEnd;
      end
      else if  DsLexema = 'ALTER' then
           begin
                 DsNewSql := DsNewSql + DsLexema;
                 ProcessarAlter;
                 ProcessarCorpoBeginEnd;
      end
      else if  DsLexema = 'DROP' then
           begin
                 DsNewSql := DsNewSql + DsLexema;
                 ProcessarDrop;
                 ProcessarCorpoBeginEnd;
      end
      else if  DsLexema = 'EXCEPTION' then
           begin
                 ProcessarException;
                 ProcessarCorpoBeginEnd;
      end
      else if  DsLexema = 'WHILE' then
           begin
                 DsNewSql := DsNewSql + DsLexema;
                 ProcessarWhile;
                 ProcessarCorpoBeginEnd;
      end
      else if  DsLexema = 'SUBSTRING' then
           begin
                 ProcessarSubstring;
      end
      else if (not PalavraReservada) and (OToken <> OFinali) and (OToken <> OEof) then
           begin
                 ProcessarIdentificadorLocal;

                 if  OToken <> OFinali then
                 begin
                       ProcessarCorpoBeginEnd;
                 end;
      end;

      if  (OToken <> OEof) and (OToken <> OIdenti) then
      begin
            repeat
                   EncontrarProximoLexema;

                   if  OToken = OEspaco then
                   begin
                         DsNewSql := DsNewSql + DsLexema;
                   end;
            until OToken in [OEof, OIdenti];

            ProcessarCorpoBeginEnd;
      end;
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER O IF                                    *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarIf;
var DsSql : String;
begin
      DsSql := '';
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O OPERADOR THEN  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if (OToken <> OEof) and (not PalavraReservada) then
             begin
                    DsNewSql := DsNewSql + SubstituirIdentificadores;
             end;
      until (OToken = OEof) or (PalavraReservada);

      if OToken = OEof then
      begin
            raise Exception.Create('Instrução "IF" não finalizada.');
      end;

      if DsLexema <> 'THEN' then
      begin
            raise Exception.Create('Operador "THEN" não encontrado');
      end;

      DsNewSql := DsNewSql + DsLexema;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  PROCESSANDO O CORPO DO IF -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      ProcessarCorpoIf;

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  FINALIZANDO CORPO DO IF  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      if DsLexema <> 'END' then
      begin
            raise Exception.Create('Operador finalizador "END" da instrução ' +
                                                        '"IF" não encontrado.');
      end;

      repeat
             EncontrarProximoLexema;

             if OToken = OEspaco then
             begin
                   DsSql := DsSql + DsLexema;
             end;
      until OToken in [OEof, OIdenti];

      if  DsLexema = 'ELSE' then
      begin
            DsNewSql := DsNewSql + DsLexema;

            ProcessarCorpoIf;

            if DsLexema <> 'END' then
            begin
                  raise Exception.Create('Operador finalizador "END" da ' +
                                            'instrução "ELSE" não encontrado.');
            end;

            DsNewSql := DsNewSql + DsLexema + ' IF' + DsFinali;

            repeat
                   EncontrarProximoLexema;

                   if OToken = OEspaco then
                   begin
                          DsNewSql := DsNewSql + DsLexema;
                   end;
            until OToken in [OEof, OIdenti];
      end else
      begin
            DsNewSql := DsNewSql + 'END IF' + DsFinali + DsSql;
      end;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
end;

procedure TBrvOracle.ProcessarCorpoIf;
var TpInstru : String;
begin
      TpInstru := DsLexema;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O OPERADOR BEGIN  IF =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
      until (OToken = OEof) or (PalavraReservada);

      if OToken = OEof then
      begin
            raise Exception.Create('Instrução "' + TpInstru + '" não finalizada.');
      end;

      if DsLexema <> 'BEGIN' then
      begin
            raise Exception.Create('Operador "BEGIN" da instrução "' + TpInstru +
                                                                     '" não encontrado.');
      end;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken <> OEspaco then
             begin
                   ProcessarCorpoBeginEnd;
             end else
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until (OToken = OEof) or (PalavraReservada);
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER O EXECUTE                               *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarExecute;
var SnEnvPar : Boolean;
begin
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O OPERADOR PROCEDURE =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
      until (OToken = OEof) or (PalavraReservada);

      if OToken = OEof then
      begin
            raise Exception.Create('Instrução "EXECUTE" não finalizada.');
      end;

      if DsLexema <> 'PROCEDURE' then
      begin
            raise Exception.Create('Operador "PROCEDURE" não encontrado');
      end;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O NOME DO PROCEDIMENTO =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
      until OToken in [OEof, OIdenti];

      if OToken = OEof then
      begin
            raise Exception.Create('Instrução "EXECUTE" não finalizada.');
      end;

      if  PalavraReservada then
      begin
            raise Exception.Create('Nome do procedimento não finalizado.');
      end;

      DsNewSql := DsNewSql + DsLexema + '(';

      SnEnvPar := ProcessarParametrosExecute;

      if OToken = OEof then
      begin
            raise Exception.Create('Instrução "EXECUTE" não finalizada.');
      end;

      if  (OToken <> OFinali) then
      begin
            if  DsLexema = 'RETURNING_VALUES' then
            begin
                  if SnEnvPar then
                  begin
                        DsNewSql := DsNewSql + ',';
                  end;
                  ProcessarParametrosExecute;
            end;
      end;

      DsNewSql := DsNewSql + ');';
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
end;

function TBrvOracle.ProcessarParametrosExecute : Boolean;
begin
      Result := False;
      repeat
             EncontrarProximoLexema;

             if OToken <> OEspaco then
             begin
                   if (OToken <> OEof) and (OToken <> OFinali) and
                      (not PalavraReservada) then
                   begin
                         Result := True;
                         DsNewSql := DsNewSql + SubstituirIdentificadores;
                   end;
             end;
      until (OToken in [OEof, OFinali]) or (PalavraReservada);
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER O CAST                                  *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarCast;
var TpVariav : String;
begin
      repeat
             EncontrarProximoLexema;

             if (OToken <> OEof) and (not TipoVariavel) then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until (OToken = OEof) or (TipoVariavel);

      if  OToken = OEof then
      begin
            raise Exception.Create('Instrução "CAST" não finalizada.');
      end;

      TpVariav := DsLexema;

      TrocarTipoVariavel('CAST');

      if  TpVariav = 'VARCHAR' then
      begin
            if  DsLexema <> ')' then
            begin
                  raise Exception.Create('Instrução "CAST" não finalizada.');
            end;

            DsNewSql := DsNewSql + DsLexema;
      end;

      EncontrarProximoLexema;

      if  OToken <> OFinali then
      begin
            raise Exception.Create('Finalizador da instrução "CAST" não encontrada.');
      end;
      DsNewSql := DsNewSql + DsLexema;
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER O EXCEPTION                             *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarException;
begin
      DsNewSql := DsNewSql + 'RAISE_APPLICATION_ERROR(-20101, ' + DsApostr;

      repeat
             EncontrarProximoLexema;

             if (OToken <> OEof) and (OToken <> OFinali) and (OToken <> OEspaco) then
             begin
                   try
                       DsNewSql := DsNewSql + EncontrarMensagemExcessao;
                   except
                       DsNewSql := DsNewSql + 'Erro ao tendar encontrar excessão.';
                   end;
             end;
      until OToken in [OEof, OFinali];

      if  OToken = OEof then
      begin
            raise Exception.Create('Excessão não finalizada.');
      end;

      if  OToken <> OFinali then
      begin
            raise Exception.Create('Excessão não finalizada.');
      end;

      DsNewSql := DsNewSql + DsApostr + ')' + DsLexema;
end;

function TBrvOracle.EncontrarMensagemExcessao : String;
var QryExcess : TQuery;
begin
      try
          QryExcess               := TQuery.Create(Self);
          QryExcess.DataBaseName  := 'BDPadrao';
          QryExcess.SQL.Text      := 'SELECT TXEXCESS '  +
                                     'FROM EXCESSAO '    +
                                     'WHERE NMEXCESS = ' + #39 + DsLexema + #39;
          QryExcess.Open;

          if  not QryExcess.IsEmpty then
          begin
                Result := QryExcess.FieldByName('TXEXCESS').AsString;
          end else
          begin
                Result := 'Excessão não encontrada.';
          end;
      finally
          QryExcess.Close;
          FreeAndNil(QryExcess);
      end;
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER O WHILE                                 *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarWhile;
begin
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O OPERADOR DO  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if (OToken <> OEof) and (not PalavraReservada) then
             begin
                   DsNewSql := DsNewSql + SubstituirIdentificadores;
             end;
      until (OToken = OEof) or (PalavraReservada);

      if OToken = OEof then
      begin
            raise Exception.Create('Instrução "WHILE" não finalizada.');
      end;

      if DsLexema <> 'DO' then
      begin
            raise Exception.Create('Operador "DO" não encontrado');
      end;

      DsNewSql := DsNewSql + 'LOOP';
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  PROCESSANDO O CORPO DO WHILE =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      ProcessarCorpoWhile;

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  FINALIZANDO CORPO DO WHILE =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      if DsLexema <> 'END' then
      begin
            raise Exception.Create('Operador finalizador "END" da instrução ' +
                                                               '"WHILE" não encontrado.');
      end;

      DsNewSql := DsNewSql + 'END LOOP' + DsFinali;

      repeat
             EncontrarProximoLexema;

             if OToken = OEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until OToken in [OEof, OIdenti];
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
end;

procedure TBrvOracle.ProcessarCorpoWhile;
begin
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=  ENCONTRANDO O OPERADOR BEGIN =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;
      until (OToken = OEof) or (PalavraReservada);

      if OToken = OEof then
      begin
            raise Exception.Create('Instrução "WHILE" não finalizada.');
      end;

      if DsLexema <> 'BEGIN' then
      begin
            raise Exception.Create(
                                 'Operador "BEGIN" da instrução "WHILE" não encontrado.');
      end;
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  OToken <> OEspaco then
             begin
                   ProcessarCorpoBeginEnd;
             end else
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until (OToken = OEof) or (PalavraReservada);
end;
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvOracle.ProcessarIdentificadorLocal;
begin
      if  not ProcessarGenerator then
      begin
            DsNewSql := DsNewSql + SubstituirIdentificadores;

            repeat
                   EncontrarProximoLexema;

                   if  not (PalavraReservada) then
                   begin
                         if  DsLexema = '=' then
                         begin
                               DsNewSql := DsNewSql + ':=';
                         end else
                         begin
                               DsNewSql := DsNewSql + SubstituirIdentificadores;
                         end;
                   end;
             until (OToken in [OEof, OFinali]) or (PalavraReservada);
      end;
end;

function TBrvOracle.ProcessarGenerator : Boolean;
var DsLinha  : String;
    NrNewPos : Integer;
    NrPosGen : Integer;
begin
      NrNewPos := NrPosica - Length(DsLexema) + 1;
      DsLinha  := '';

      while (DsInstru[NrNewPos] <> ';') and (NrNewPos <= TtCaract) do
      begin
            DsLinha := DsLinha + DsInstru[NrNewPos];
            inc(NrNewPos);
      end;

      NrPosGen :=  Pos('gen_id', LowerCase(DsLinha));

      if  NrPosGen <> 0 then
      begin
            DsNewSql := DsNewSql  +
                        'SELECT ' + NomeGenerator(NrPosGen, DsLinha) +
                        '.NEXTVAL INTO ' + CampoChaveGenerator(NrPosGen, DsLinha) +
                        ' FROM DUAL;';

            NrPosica := NrNewPos;

            repeat
                   EncontrarProximoLexema;

                   if OToken in [OEspaco, OFinali]  then
                   begin
                         DsNewSql := DsNewSql + DsLexema;
                   end;
            until  OToken in [OEof, OIdenti];

            Result   := True;
      end else
      begin
            Result   := False;
      end;
end;

function TBrvOracle.NomeGenerator(NrPosGen : Integer; DsLinha : String) : String;
begin
      Result := '';

      while (DsLinha[NrPosGen] <> '(') and (NrPosGen <= Length(DsLinha)) do
      begin
            inc(NrPosGen);
      end;

      inc(NrPosGen);

      while (DsLinha[NrPosGen] = ' ') and (NrPosGen <= Length(DsLinha)) do
      begin
            inc(NrPosGen);
      end;

      if  NrPosGen > Length(DsLinha) then
      begin
            raise Exception.Create('Generator não finalizado corretamente');
      end;

      while (DsLinha[NrPosGen] <> ')') and (DsLinha[NrPosGen] <> ',') and
            (DsLinha[NrPosGen] <> ' ') and (NrPosGen <= Length(DsLinha)) do
      begin
            Result := Result + DsLinha[NrPosGen];
            inc(NrPosGen);
      end;

      Result := UpperCase(Result);
end;

function TBrvOracle.CampoChaveGenerator(NrPosGen : Integer; DsLinha : String) : String;
var NrInicio : Integer;
begin
      Result := '';
      NrInicio := 1;

      while (NrInicio <= NrPosGen) and (DsLinha[NrInicio] <> ' ') do
      begin
            Result := Result + DsLinha[NrInicio];
            inc(NrInicio);
      end;

      DsLexema := UpperCase(Result);
      Result   := SubstituirIdentificadores;
end;

end.


