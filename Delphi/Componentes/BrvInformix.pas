unit BrvInformix;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs;

type
  TInformixToken = (IBof, IIdenti, IEspaco, IAspas, IComent, IErro, IDelimi,
                     IOpeRel, IEof, IFinali, ISimbol);

  TBrvInformix = class(TComponent)
  private
    { Private declarations }
       DsInstru : String;
       NrPosica : Integer;
       DsLexema : String;
       DsNewSql : String;
       DsNexCar : Char;
       TtCaract : Integer;
       procedure EncontrarProximoLexema;
       procedure ProximoCaracter;
       function  PalavraReservada : Boolean;
       procedure ProcessarAlter;
       procedure ProcessarAlterTrigger;
       procedure ProximoLexema;
       function  SubstituirIdentificadores : String;
       procedure ProcessarSelect;
    procedure ProcessarSubstring;
  protected
    { Protected declarations }
  public
    { Public declarations }
       constructor Create(AOwner : TComponent); override;
       destructor  Destroy;                     override;
       function    SintaxeInformix(DsSql : String) : String;
  published
    { Published declarations }
  end;

var
  IToken    : TInformixToken;
  IDsDigito : set of char = ['0'..'9', 'a'..'z', 'A'..'Z', '.', '*',
                             '+', '-', '/', '%', ':', '_', '|', '$', '&',
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

  IDsAspas  : set of char = [#39];
  IDsEspaco : set of char = [' ', #13, #10, #9];
  IDsDelimi : set of char = ['(', ')'];
  IDsOpeRel : set of char = ['<', '>', '='];
  IDsOpeFin : set of char = [';'];
  IDsSimbol : set of char = [','];
procedure Register;

implementation
  const DsAspas  = #39;

procedure Register;
begin
  RegisterComponents('Bravo Banco', [TBrvInformix]);
end;

constructor TBrvInformix.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
end;

destructor TBrvInformix.Destroy;
begin
      inherited  destroy;
end;

function TBrvInformix.SintaxeInformix(DsSql : String) : String;
begin
      DsInstru := DsSql;
   // Substituindo as aspas =-=-=-=-=-=-=-=-=-=-=-=-=-=
      DsInstru := StringReplace(DsInstru, '"', DsAspas, [rfReplaceAll]);

      NrPosica := 0;
      TtCaract := Length(DsSql);
      IToken   := IBof;

      repeat
             EncontrarProximoLexema;
      until (PalavraReservada) or (IToken = IEof);

      if  DsLexema = 'SELECT' then
      begin
            DsNewSql := DsLexema;
            ProcessarSelect;
      end else
      begin
            if  (DsLexema = 'ALTER') then
            begin
                  DsNewSql := DsLexema;
                  ProcessarAlter;
            end else
            begin
                  DsNewSql := DsInstru;
            end;
      end;

      DsNewSql := StringReplace(DsNewSql, '<$hh', '', [rfReplaceAll,rfIgnoreCase]);
      DsNewSql := StringReplace(DsNewSql, 'hh$>', '', [rfReplaceAll,rfIgnoreCase]);
      Result   :=  DsNewSql;
end;

procedure TBrvInformix.EncontrarProximoLexema;
var TokAnt : TInformixToken;
begin
      TokAnt := IToken;

      repeat
             ProximoCaracter;
      until (IToken <> TokAnt) or (IToken = IEof);

      if  IToken <> IEof then
      begin
            if  IToken = IAspas then
            begin
                  DsLexema := DsNexCar;

                  repeat
                         ProximoCaracter;
                         DsLexema := DsLexema + DsNexCar;
                  until IToken in [IAspas, IEof];

                  if IToken = IEof then
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

procedure TBrvInformix.ProximoLexema;
var TokAnt  : TInformixToken;
begin
      TokAnt   :=  IToken;
      DsLexema := '';

      while IToken = TokAnt do
      begin
            DsLexema := DsLexema + DsNexCar;
            ProximoCaracter;
      end;

      if  DsLexema = '/*' then
      begin
            Dec(NrPosica);

            repeat
                   EncontrarProximoLexema;
            until (DsLexema = '*/') or (IToken = IEof);

            EncontrarProximoLexema;
            Dec(NrPosica);
      end else
      begin
            Dec(NrPosica);
      end;

      IToken := TokAnt;
end;

procedure TBrvInformix.ProximoCaracter;
begin
      Inc(NrPosica);

      if  NrPosica > TtCaract then
      begin
            IToken := IEof;
      end else
      begin
            DsNexCar := DsInstru[NrPosica];

            if DsNexCar in IDsDigito then
            begin
                  IToken := IIdenti;
            end
            else if  DsNexCar in IDsEspaco then
                 begin
                       IToken := IEspaco;
                 end
            else if  DsNexCar in IDsAspas then
                 begin
                       IToken   := IAspas;
                 end
            else if  DsNexCar in IDsDelimi then
                 begin
                       IToken := IDelimi;
                 end
            else if  DsNexCar in IDsOpeRel then
                 begin
                       IToken   := IOpeRel;
                 end
            else if  DsNexCar in IDsOpeFin then
                 begin
                       IToken   := IFinali;
                 end
            else if  DsNexCar in IDsSimbol then
                 begin
                       IToken   := ISimbol;
                 end
            else IToken   := IErro;
      end;

      if  IToken = IErro then
      begin
            raise Exception.Create('Caracter "' + DsNexCar + '" não foi reconhecido');
      end;
end;

function TBrvInformix.PalavraReservada : Boolean;
begin
      if
         (DsLexema = 'ALTER')      or (DsLexema = 'AS')                 or
         (DsLexema = 'AFTER')      or (DsLexema = 'ADD')                or
         (DsLexema = 'BLOB')       or (DsLexema = 'BEGIN')              or
         (DsLexema = 'BEFORE')     or
         (DsLexema = 'CREATE')     or (DsLexema = 'CAST')               or
         (DsLexema = 'CONSTRAINT') or (DsLexema = 'COLLUMN')            or
         (DsLexema = 'DELETE')     or (DsLexema = 'DECLARE')            or
         (DsLexema = 'DATE')       or (DsLexema = 'DROP')               or
         (DsLexema = 'DOUBLE')     or
         (DsLexema = 'END')        or (DsLexema = 'ELSE')               or
         (DsLexema = 'EXECUTE')    or (DsLexema = 'EXCEPTION')          or
         (DsLexema = 'FROM')       or (DsLexema = 'FOR')                or
         (DsLexema = 'FLOAT')      or
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
//         (DsLexema = 'SUSPEND')    or
         (DsLexema = 'TRIGGER')    or (DsLexema = 'THEN')               or
         (DsLexema = 'TABLE')      or
         (DsLexema = 'UPDATE')     or
         (DsLexema = 'VARIABLE')   or (DsLexema = 'VARCHAR')            or
         (DsLexema = 'WHERE')      then
      begin
            Result := True;
      end else
      begin
            Result := False;
      end;
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER O ALTER                                 *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvInformix.ProcessarAlter;
begin
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-= Encontrando o identificador TABLE =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      repeat
             EncontrarProximoLexema;

             if  IToken = IEspaco then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;
      until (IToken = IEof) or (PalavraReservada);

      if  IToken = IEof then
      begin
            raise Exception.Create('Instrução "ALTER" não finalizada');
      end;

      if  (DsLexema <> 'TABLE')   and (DsLexema <> 'PROCEDURE') and
          (DsLexema <> 'TRIGGER') and (DsLexema <> 'EXCEPTION') then
      begin
            raise Exception.Create('Operador "TABLE", "PROCEDURE", "TRIGGER" ' +
                                   'ou "EXCEPTION" não encontrado');
      end;

      DsNewSql := DsNewSql + DsLexema;

      if  DsLexema = 'TRIGGER' then
      begin
            ProcessarAlterTrigger;
      end else
      begin
             repeat
                    EncontrarProximoLexema;
                    if IToken <> IEof  then
                    begin
                          DsNewSql := DsNewSql + DsLexema;
                    end;
                    
             until (IToken = IEof);
      end;
end;

procedure TBrvInformix.ProcessarAlterTrigger;
begin
      repeat
             EncontrarProximoLexema;

             if  DsLexema <> 'FOR' then
             begin
                   DsNewSql := DsNewSql + DsLexema;
             end;

      until (IToken = IEof) or (DsLexema = 'FOR');

      if  IToken = IEof then
      begin
            raise Exception.Create('Instrução "ALTER TRIGGER" não finalizada');
      end;

      repeat
             EncontrarProximoLexema;
      until (IToken = IEof) or (IToken = IIdenti);
      
      if  IToken = IEof then
      begin
            raise Exception.Create('Instrução "ALTER TRIGGER" não finalizada');
      end;

      repeat
             EncontrarProximoLexema;
             DsNewSql := DsNewSql + DsLexema;
      until (IToken = IEof);
end;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//*      PROCEDIMENTOS PARA CONVERTER A SELECT                                *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
procedure TBrvInformix.ProcessarSelect;
begin
      repeat
             EncontrarProximoLexema;

             if (IToken <> IEof) and (DsLexema <> 'SUBSTRING') then
             begin
                   DsNewSql := DsNewSql + SubstituirIdentificadores;
             end;

             if  DsLexema = 'SELECT' then
             begin
                   ProcessarSelect;
             end
             else if  DsLexema = 'SUBSTRING' then
                  begin
                        ProcessarSubstring;
                  end;
                  
      until (IToken in [IEof, IFinali]);
end;

procedure TBrvInformix.ProcessarSubstring;
begin
      DsNewSql := DsNewSql + 'SUBSTR';

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=  Encontrando Operador FROM do SUBSTRING =-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      repeat
             EncontrarProximoLexema;

             if  (IToken <> IEof) and (DsLexema <> 'FROM') then
             begin
                   DsNewSql := DsNewSql + SubstituirIdentificadores;
             end;
      until (IToken in [IEof, IFinali]) or (DsLexema = 'FROM');

      if  (IToken = IEof) or (DsLexema <> 'FROM') then
      begin
            raise Exception.Create('Operador "FROM" do "SUBSTRING" não encontrado.');
      end;

      DsNewSql := DsNewSql + ',';
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=  Encontrando Operador FROM do SUBSTRING =-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      repeat
             EncontrarProximoLexema;

             if  (IToken <> IEof) and (DsLexema <> 'FOR') then
             begin
                   DsNewSql := DsNewSql + SubstituirIdentificadores;
             end;
      until (IToken in [IEof, IFinali]) or (DsLexema = 'FOR');

      if  (IToken = IEof) or (DsLexema <> 'FOR') then
      begin
            raise Exception.Create('Operador "FOR" do "SUBSTRING" não encontrado.');
      end;

      DsNewSql := DsNewSql + ',';
end;

function TBrvInformix.SubstituirIdentificadores : String;
begin
      Result := DsLexema;

      if  (Pos('NEW.', DsLexema) <> 0) or (Pos('OLD.', DsLexema) <> 0) then
      begin
            Result := ':' + DsLexema;
      end else
      begin
            if  (DsLexema = DsAspas + 'NOW'   + DsAspas) or
                (DsLexema = DsAspas + 'TODAY' + DsAspas) then
            begin
                  Result := 'CURRENT';
            end else
            begin
                  if  DsLexema = 'COALESCE' then
                  begin
                        Result := 'NVL';
                  end;
            end;
      end;
end;

end.
