unit UBanco;



interface

uses  SysUtils,DBClient, DB,StdCtrls,BrvString;

function criarGerarProcedureInsert(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureUpdate(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureDelete(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureSelect(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureSelectById(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptBDInsert(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo;qntReq:Integer;LinhaInsert:Integer):Tmemo;
function GerarScriptBDTable(Txt:String;DsTab:String;CcDataset:TClientDataSet;memo1 :Tmemo;banco:String):Tmemo;
function GerarScriptBDAtributos(Txt:String;dSTABE:String;CcDataset:TClientDataSet;memo1 :Tmemo):TMemo;
function GerarScriptBDValidators(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptCabecalho(CcDataset:TClientDataSet):AnsiString;
function GerarScriptBDTradutorBanco(Txt:String;Banco:String):String;
implementation


uses BrvFuncoesXE;
var BrvString: TBrvString;

function GerarScriptBDTradutorBanco(Txt:String;Banco:String):String;
begin
      if LowerCase(Banco) = 'postgres' then // postgres
      begin
             if Pos(UpperCase('Char'),UpperCase(Txt)) <> 0  then
                result := 'character varying'
             else if True then
                result := Txt;
      end;
end;

function GerarScriptCabecalho(CcDataset:TClientDataSet):AnsiString;
var return:AnsiString;
begin
return:='';
      CcDataset.First;
      while not CcDataset.Eof do
      begin
            if CcDataset.FieldByName('S/N').AsString = 'S'  then
                  return := return + 'p_'+CcDataset.FieldByName('Nome').AsString+' '+verificadorCodeAppBanco(CcDataset.FieldByName('Nome').AsString+',');
           CcDataset.Next;
      end;
      delete(return,length(return),1);
      result := return
end;


function criarGerarProcedureInsert(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('CREATE OR REPLACE FUNCTION ins_'+(Txt)+' ('+GerarScriptCabecalho(CcDataset)+')');
      memo1.Lines.Add('RETURNS integer AS');
      memo1.Lines.Add('$$');
      memo1.Lines.Add('DECLARE');
      memo1.Lines.Add('		id int;');
      memo1.Lines.Add('BEGIN');
      memo1.Lines.Add('	INSERT INTO '+Txt+' ');
      memo1.Lines.Add('(');
      CcDataset.First;
      while not CcDataset.Eof do
      begin
            if CcDataset.FieldByName('S/N').AsString = 'S'  then
                  memo1.Lines.Add('		   '+CcDataset.FieldByName('Nome').AsString+', ');
           CcDataset.Next;
      end;
      memo1.Lines.Add('		   ,tenant_id');
      memo1.Lines.Add('		   ,create_user)');
      memo1.Lines.Add('	VALUES');
      memo1.Lines.Add('( ');
      CcDataset.First;
      while not CcDataset.Eof do
      begin
            if CcDataset.FieldByName('S/N').AsString = 'S'  then
                  memo1.Lines.Add('	p_'+CcDataset.FieldByName('Nome').AsString+', ');
           CcDataset.Next;
      end;
      memo1.Lines.Add(',(SELECT tenant_id from tenant where tenant.tenant_id = p_tenant_id)');
      memo1.Lines.Add(',p_create_user) RETURNING tag_id INTO id;');
      memo1.Lines.Add('');
      memo1.Lines.Add('RETURN id; ');
      memo1.Lines.Add('END');
      memo1.Lines.Add('$$');
      memo1.Lines.Add('LANGUAGE ''plpgsql'';');
      memo1.Lines.Add('');
      Result := memo1;

end;
function criarGerarProcedureUpdate(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo):Tmemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('CREATE OR REPLACE FUNCTION upd_'+Txt+' ('+GerarScriptCabecalho(CcDataset)+')');
      memo1.Lines.Add('RETURNS void AS');
      memo1.Lines.Add('$$');
      memo1.Lines.Add('BEGIN');
      memo1.Lines.Add('UPDATE '+Txt+' ');
      memo1.Lines.Add('SET ');
      CcDataset.First;
      while not CcDataset.Eof do
      begin
            if CcDataset.FieldByName('S/N').AsString = 'S'  then
                  memo1.Lines.Add(LowerCase(CcDataset.FieldByName('Nome').AsString)+ ' =  COALESCE(p_'+LowerCase(CcDataset.FieldByName('Nome').AsString)+ ','+LowerCase(CcDataset.FieldByName('Nome').AsString)+'),');
           CcDataset.Next;
      end;
      memo1.Lines.Add('WHERE SubstituirID = p_SubstituirID;');
      memo1.Lines.Add('END');
      memo1.Lines.Add('$$');
      memo1.Lines.Add('  LANGUAGE plpgsql;');
      Result := memo1;

end;

function criarGerarProcedureDelete(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo):Tmemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('	 criarGerarProcedureDelete');
end;
function criarGerarProcedureSelect(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo):Tmemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('	 criarGerarProcedureSelect');
end;
function criarGerarProcedureSelectById(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo):Tmemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('	 criarGerarProcedureSelectById');
end;
function GerarScriptBDInsert(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo;qntReq:Integer;LinhaInsert:Integer):Tmemo;
var Cab,Value:String;
i:Integer;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('INSERT INTO '+Txt+'(') ;
      CcDataset.First;
      while not CcDataset.Eof do
      begin
            if CcDataset.FieldByName('S/N').AsString = 'S'  then
                  Cab:= Cab + LowerCase(CcDataset.FieldByName('Nome').AsString)+ ',';
           CcDataset.Next;
      end;
       delete(Cab,length(Cab),1);
       memo1.Lines.Add(Cab);
       memo1.Lines.Add('	VALUES ');
      for i := 0 to qntReq do
      begin
            CcDataset.First;
            while not CcDataset.Eof do
            begin
                  if CcDataset.FieldByName('S/N').AsString = 'S'  then
                  begin
                        if (LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'char') or(LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'varchar') or (UpperCase(CcDataset.FieldByName('Tipo').AsString) = 'SMALLINT') then
                            Value:= Value +''''+CcDataset.FieldByName('Tipo').AsString+'-'+IntToStr(i)+''',';
                        if (LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'integer') or(LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'double') or (LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'float') then
                            Value:= Value + IntToStr(i)+',';
                        if (LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'boolean')  then
                            Value:= Value + 'True'+',';
                        if (LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'date') or (LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'time') then
                            Value:= Value + DateToStr(now())+',';
                  end;
                 CcDataset.Next;
            end;
            delete(Value,length(Value),1);
            if i <> qntReq then
            memo1.Lines.Add('('+Value+'),')
            else
            memo1.Lines.Add('('+Value+');');
            Value := '';
      end;
      memo1.Lines.Add(Value);
end;
function GerarScriptBDTable(Txt:String;DsTab:String;CcDataset:TClientDataSet;memo1 :Tmemo;banco:String):Tmemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('create table '+Txt+' (');
      CcDataset.First;
      while not CcDataset.Eof do
      begin
            if CcDataset.FieldByName('S/N').AsString = 'S'  then
            begin
                  if (LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'integer')or(LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'serial')or(LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'double') then
                  begin
                        if CcDataset.FieldByName('Obrigatorio').AsString = 'S' then
                           memo1.Lines.Add('     '+CcDataset.FieldByName('Nome').AsString+'     '+GerarScriptBDTradutorBanco(CcDataset.FieldByName('Tipo').AsString,banco)+'  NOT NULL ,')
                        else
                           memo1.Lines.Add('     '+CcDataset.FieldByName('Nome').AsString+'     '+GerarScriptBDTradutorBanco(CcDataset.FieldByName('Tipo').AsString,banco)+'  NULL ,');

                  end;
                   if (LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'char') or(LowerCase(CcDataset.FieldByName('Tipo').AsString) = 'varchar') or (UpperCase(CcDataset.FieldByName('Tipo').AsString) = 'SMALLINT') then
                   begin
                         if CcDataset.FieldByName('Obrigatorio').AsString = 'S' then
                            memo1.Lines.Add('     '+CcDataset.FieldByName('Nome').AsString+'     '+GerarScriptBDTradutorBanco(CcDataset.FieldByName('Tipo').AsString,banco)+'('+CcDataset.FieldByName('tamanho').AsString+')   NOT NULL   ,')
                         else
                            memo1.Lines.Add('     '+CcDataset.FieldByName('Nome').AsString+'     '+GerarScriptBDTradutorBanco(CcDataset.FieldByName('Tipo').AsString,banco)+'('+CcDataset.FieldByName('tamanho').AsString+')   NULL   ,')

                   end;
            end;
            CcDataset.Next;
      end;
      memo1.Lines.Add(')');
      CcDataset.First;
      while not CcDataset.Eof do
      begin
            if LowerCase(CcDataset.FieldByName('Chave').AsString) = 'pk' then
            begin
                  memo1.Lines.Add('constraint PK_'+Txt+' primary key ('+CcDataset.FieldByName('Nome').AsString+') ' );
            end;
            CcDataset.Next;
      end;
          memo1.Lines.Add('COMMENT ON TABLE '+Txt+' '''+DsTab+'''');

end;
function GerarScriptBDAtributos(Txt:String;dSTABE:String;CcDataset:TClientDataSet;memo1 :Tmemo):Tmemo;
var Input : String;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('INSERT INTO S008 (NMTABELA,DSTABELA)VALUES('''+Txt+''','''+dSTABE+''')');
      memo1.Lines.Add('INSERT INTO S009 (nmtabela,nmatribu,dsatribu,tpatribu,tpmascar,Dsmascar,dshelp,tmatribu');
      memo1.Lines.Add(')value ');
      CcDataset.First;
      while not CcDataset.Eof do
      begin
            if CcDataset.FieldByName('S/N').AsString = 'S'  then
            begin
                  memo1.Lines.Add('  '''+Txt+''','''+CcDataset.FieldByName('Nome').AsString+''','''+CcDataset.FieldByName('Apelido').AsString+''','''+CcDataset.FieldByName('Tipo').AsString+''','' '' ,'''+CcDataset.FieldByName('Mascara').AsString+''','''+CcDataset.FieldByName('Help').AsString+''','''+CcDataset.FieldByName('Tamanho').AsString+''',');
            end;
            CcDataset.Next;
      end;
      memo1.Lines.Add(';');
      CcDataset.First;
      while not CcDataset.Eof do
      begin
            if CcDataset.FieldByName('S/N').AsString = 'S'  then
            begin
                  if not(CcDataset.FieldByName('Dominio').AsString <> '') then
                  begin
                        memo1.Lines.Add('INSERT INTO S010 (NMTABELA,NMATRIBU,DSDOMINI,VRDOMINI)VALUES('+Txt+','+CcDataset.FieldByName('NOME').AsString+','+CcDataset.FieldByName('Dominio').AsString+','+CcDataset.FieldByName('Dominio').AsString+');');
                  end;
                   if LowerCase(CcDataset.FieldByName('Chave').AsString) <> 'pk' then
                  begin
                        memo1.Lines.Add('INSERT INTO S011 (NMTABELA,NMATRIBU)VALUES('+Txt+','+CcDataset.FieldByName('NOME').AsString+');');
                  end;
                   if LowerCase(CcDataset.FieldByName('Chave').AsString) <> 'fk' then
                  begin
                        memo1.Lines.Add('INSERT INTO S012 (NMCHAEST,NMTABELA,NMTABEST,TPCHAEST)VALUES(FK_'+Txt+'_'+CcDataset.FieldByName('NomeFK').AsString+','+Txt+','+CcDataset.FieldByName('NomeFK').AsString+',''C'');');
                  end;
            end;
            CcDataset.Next;
      end;

end;
function GerarScriptBDValidators(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo):Tmemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('	 GerarScriptBDValidators');
end;

end.
