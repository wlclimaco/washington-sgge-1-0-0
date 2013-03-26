unit UBanco;



interface

uses  SysUtils,DBClient, DB,StdCtrls;

function criarGerarProcedureInsert(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureUpdate(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureDelete(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureSelect(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureSelectById(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptBDInsert(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo;qntReq:Integer;LinhaInsert:Integer):Tmemo;
function GerarScriptBDTable(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptBDAtributos(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptBDValidators(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptCabecalho(CcDataset:TClientDataSet):AnsiString;
implementation

uses BrvFuncoesXE;

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
function GerarScriptBDTable(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo):Tmemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('	 GerarScriptBDTable');
end;
function GerarScriptBDAtributos(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo):Tmemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('	 GerarScriptBDAtributos');
end;
function GerarScriptBDValidators(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo):Tmemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('	 GerarScriptBDValidators');
end;

end.
