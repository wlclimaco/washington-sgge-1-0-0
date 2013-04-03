unit BrvFuncoesXE;
interface
uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls,IniFiles,DB,DBClient;


  procedure StrToClientDataSet(pDsLista: String; CcDataTmp: TClientDataSet);
  procedure TocarSpacoPosVirgula(pDsLista: String);
  function IncluirNoArq(Texto:TMemo;Linha:Integer;Arquivo:String):Boolean;
  function escreverCode(Txt:String):String;
  function verificadorCode(Txt:String):String;
  function escreverCodeXML(Txt:String;Op:Integer):String;
  Function PrimeiraMaiscula(Texto: String) : String;
  Function MostrarMemo(Memo: TMemo;Texto:String) : String;
  function verificadorCodeAppBanco(Txt:String):String;
  function verificadorCodeBanco(Txt:String):String;

implementation

function IncluirNoArq(Texto:TMemo;Linha:Integer;Arquivo:String):Boolean;
var arq        : TextFile;
    count      : Integer;
    lDsLista   : TStrings;
begin
    try
        lDsLista  := TStringList.Create;
        AssignFile(arq, Arquivo);
        lDsLista.LoadFromFile(Arquivo);
        Reset(arq);
        count := 0 ;
        linha := linha + 1;
        lDsLista.Insert(linha,Texto.Lines.Text);
        Rewrite(arq); //abre o arquivo para escrita
        {$I+}
        Writeln(arq,lDsLista.Text);
        Closefile(arq);
    finally
        FreeAndNil(lDsLista);
    end;
end;

function verificadorCodeBanco(Txt:String):String;
begin
      if Pos(UpperCase('Char'),UpperCase(Txt)) <> 0  then
      begin
          result := 'VARCHAR';
      end
      else if Pos(UpperCase('INTEGER'),UpperCase(Txt)) <> 0  then
      begin
          result := 'INTEGER'
      end
      else if Pos(UpperCase('SMALLINT'),UpperCase(Txt)) <> 0  then
      begin
            result := 'VARCHAR'
      end
      else if Pos(UpperCase('NUMERIC'),UpperCase(Txt)) <> 0  then
      begin
            result := 'DOUBLE'
      end
      else if Pos(UpperCase('TIME'),UpperCase(Txt)) <> 0  then
      begin
            result := 'DATE'
      end
      else if Pos(UpperCase('Date'),UpperCase(Txt)) <> 0  then
      begin
            result := 'DATE'
      end
      else
      begin
           result := Txt
      end
end;
function verificadorCodeAppBanco(Txt:String):String;
begin
      if Pos(UpperCase('Char'),UpperCase(Txt)) <> 0  then
      begin
          result := ' character varying'
      end
      else if Pos(UpperCase('INTEGER'),UpperCase(Txt)) <> 0  then
      begin
          result := 'integer'
      end
      else if Pos(UpperCase('SMALLINT'),UpperCase(Txt)) <> 0  then
      begin
            result := ' character varying'
      end
      else if Pos(UpperCase('NUMERIC'),UpperCase(Txt)) <> 0  then
      begin
            result := 'double'
      end
      else if Pos(UpperCase('TIME'),UpperCase(Txt)) <> 0  then
      begin
            result := 'timestamp with time zone'
      end
      else if Pos(UpperCase('Date'),UpperCase(Txt)) <> 0  then
      begin
            result := 'timestamp with time zone'
      end
      else
      begin
           result := Txt
      end
end;


Function PrimeiraMaiscula(Texto: String) : String;
begin
      Texto := lowerCase(Texto);
      If Length(Trim(Texto))>0 Then//verifica se o edit está vazio ou somente com "espaços" em branco
      Texto:= UPPERCASE(Copy(Texto, 1, 1))+ LOWERCASE(Copy(Texto, 2, Length(Texto)));
      Result := Texto;
end;
Function MostrarMemo(Memo: TMemo;Texto:String) : String;
 var   linha: string;
begin
      Memo.Text :=  Texto;
end;

function alterarCodigo(Txt:String;Tipo:String;Op:Integer):String;
var
  strLinha: String;
  Parte : TStringList;
  begin
        if Op = 1 then
        begin
              result := '<result property="'+LowerCase(Txt)+'" column="'+LowerCase(Txt)+'" />';
        end;
        if Op = 2 then
        begin
              result := ''+LowerCase(Txt);
        end;
        if Op = 3 then
        begin
              result := 'p_'+Txt+ ' '+verificadorCodeAppBanco(Tipo)+' ';
        end;
        if Op = 4 then
        begin
              result := LowerCase(Txt)+ ' =  COALESCE(p_'+LowerCase(Txt)+ ','+LowerCase(Txt)+')';
        end;
end;



function escreverCodeXML(Txt:String;Op:Integer):String;
var
  strLinha: String;
  Parte : TStringList;
  begin
        strLinha := 'ab|c|d e|f';

        Parte := TStringList.Create;
        try
        Parte.Clear;
        ExtractStrings([' '],[], PChar(Txt), Parte);
        if('CREATE' <> Parte[0])  then
        begin
              if Op = 1 then
              begin
                    result := '<result property="'+LowerCase(Parte[0])+'" column="'+LowerCase(Parte[0])+'" />';
              end;
              if Op = 2 then
              begin
                    result := ''+LowerCase(Parte[0]);
              end;
              if Op = 3 then
              begin
                    result := 'p_'+LowerCase(Parte[0])+ ' '+verificadorCodeAppBanco(Parte[1])+' ';
              end;
              if Op = 4 then
              begin
                    result := LowerCase(Parte[0])+ ' =  COALESCE(p_'+LowerCase(Parte[0])+ ','+LowerCase(Parte[0])+')';
              end;
        end;
  finally
    Parte.Free;
  end;
end;

function verificadorCode(Txt:String):String;
begin
      if Pos(UpperCase('Char'),UpperCase(Txt)) <> 0  then
      begin
          result := 'String'
      end
      else if Pos(UpperCase('INTEGER'),UpperCase(Txt)) <> 0  then
      begin
          result := 'Integer'
      end
      else if Pos(UpperCase('SMALLINT'),UpperCase(Txt)) <> 0  then
      begin
            result := 'String'
      end
      else if Pos(UpperCase('NUMERIC'),UpperCase(Txt)) <> 0  then
      begin
            result := 'Double'
      end
      else if Pos(UpperCase('TIME'),UpperCase(Txt)) <> 0  then
      begin
            result := 'Date'
      end
      else
      begin
           result := Txt
      end

end;

function escreverCode(Txt:String):String;
var
  strLinha: String;
  Parte : TStringList;
    begin
          strLinha := 'ab|c|d e|f';

          Parte := TStringList.Create;
          try
          Parte.Clear;
          ExtractStrings([' '],[], PChar(Txt), Parte);
          if('CREATE' <> Parte[0])  then
          result := verificadorCode(Parte[1])+' '+ LowerCase(Parte[0]) +';';
    finally
      Parte.Free;
    end;
end;


procedure StrToClientDataSet(pDsLista : String; CcDataTmp: TClientDataSet);
var nrindice : Integer;
    nridxcmp : Integer;
    dstxtpro : string;
    DsLista  : TStrings;
begin
      DsLista := TStringList.Create;
      DsLista.Text := pDsLista;

      if (CcDataTmp.Active) then
      begin
            CcDataTmp.EmptyDataSet;
            CcDataTmp.Close;
            CcDataTmp.Fields.Clear;
            CcDataTmp.FieldDefs.Clear;
      end;

      dstxtpro := DsLista.Strings[0] + ' ';

      while (dstxtpro <> '') do
      begin
            CcDataTmp.FieldDefs.Add(copy(dstxtpro, 1, Pos(' ', dstxtpro)-1), ftString, 150);
            dstxtpro := copy(dstxtpro, Pos(' ', dstxtpro)+1, Length(dstxtpro));
      end;

      CcDataTmp.CreateDataSet;
      CcDataTmp.Open;

      for nrindice := 1 to DsLista.Count -1 do
      begin
            dstxtpro := DsLista.Strings[nrindice] + ' ';
            CcDataTmp.Edit;
            CcDataTmp.Fields[0].AsString := 'S';
            nridxcmp := 1;

            CcDataTmp.Append;
            while ((dstxtpro <> '')) do
            begin
                  if trim(StringReplace(copy(dstxtpro, 1,Pos(' ', dstxtpro)-1), '.', ' ', [rfReplaceAll])) <> '' then
                  begin
                        CcDataTmp.Fields[nridxcmp].AsString := StringReplace(copy(dstxtpro, 1,Pos(' ', dstxtpro)-1), '.', ' ', [rfReplaceAll]);
                        Inc(nridxcmp);
                  end;
                  dstxtpro := copy(dstxtpro, Pos(' ', dstxtpro)+1, Length(dstxtpro));
            end;
            CcDataTmp.Post;
      end;

end;
procedure TocarSpacoPosVirgula(pDsLista: String);
var I,X:integer;
    a,b:String;
begin
     showMessage(StringReplace(copy(pDsLista, 1,Pos(' ', pDsLista)-1), '.', ';', [rfReplaceAll]));
     StringReplace(copy(pDsLista, 1,Pos(' ', pDsLista)-1), '.', ';', [rfReplaceAll]);
end;
end.
