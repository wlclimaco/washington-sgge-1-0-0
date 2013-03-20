unit UBanco;



interface

uses  SysUtils,DBClient, DB,StdCtrls;

function criarGerarProcedureInsert(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureUpdate(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureDelete(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureSelect(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureSelectById(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptBDInsert(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptBDTable(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptBDAtributos(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptBDValidators(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
implementation

uses BrvFuncoesXE;

function criarGerarProcedureInsert(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('CREATE OR REPLACE FUNCTION ins_'+(Txt)+'(p_name character varying, p_autogroup boolean, p_address_related boolean,  p_tenant_id integer, p_create_user character varying)');
      memo1.Lines.Add('RETURNS integer AS');
      memo1.Lines.Add('$$');
      memo1.Lines.Add('DECLARE');
      memo1.Lines.Add('		id int;');
      memo1.Lines.Add('BEGIN');
      memo1.Lines.Add('	INSERT INTO tag');
      memo1.Lines.Add('		   (name');
      memo1.Lines.Add('		   ,auto_group');
      memo1.Lines.Add('		   ,address_related');
      memo1.Lines.Add('		   ,tenant_id');
      memo1.Lines.Add('		   ,create_user)');
      memo1.Lines.Add('	VALUES');
      memo1.Lines.Add('		   (p_name');
      memo1.Lines.Add('		   ,p_autogroup');
      memo1.Lines.Add('		   ,p_address_related');
      memo1.Lines.Add('		   ,(SELECT tenant_id from tenant where tenant.tenant_id = p_tenant_id)');
      memo1.Lines.Add('		   ,p_create_user) RETURNING tag_id INTO id;');
      memo1.Lines.Add('');
      memo1.Lines.Add('	 RETURN id; ');
      memo1.Lines.Add('END');
      memo1.Lines.Add('$$');
      memo1.Lines.Add('LANGUAGE ''plpgsql'';');
      memo1.Lines.Add('');
      Result := memo1;

end;
function criarGerarProcedureUpdate(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo):Tmemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('CREATE OR REPLACE FUNCTION upd_'+Txt+'(');
      CcDataset.First;
      while not CcDataset.Eof do
      begin
            if CcDataset.FieldByName('S/N').AsString = 'S'  then
                  memo1.Lines.Add('p_'+LowerCase(CcDataset.FieldByName('Nome').AsString)+ ' '+verificadorCodeAppBanco(CcDataset.FieldByName('Tipo').AsString)+' ');
           CcDataset.Next;
      end;
      memo1.Lines.Add(')');
      memo1.Lines.Add('RETURNS void AS');
      memo1.Lines.Add('$$');
      memo1.Lines.Add('BEGIN');
      memo1.Lines.Add('	UPDATE '+Txt+' ');
      memo1.Lines.Add('	   SET ');

      CcDataset.First;
      while not CcDataset.Eof do
      begin
            if CcDataset.FieldByName('S/N').AsString = 'S'  then
                  memo1.Lines.Add(LowerCase(CcDataset.FieldByName('Nome').AsString)+ ' =  COALESCE(p_'+LowerCase(CcDataset.FieldByName('Nome').AsString)+ ','+LowerCase(CcDataset.FieldByName('Nome').AsString)+'),');
           CcDataset.Next;
      end;
      memo1.Lines.Add('	 WHERE SubstituirID = p_SubstituirID;');
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
function GerarScriptBDInsert(Txt:String;CcDataset:TClientDataSet;memo1 :Tmemo):Tmemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('	 GerarScriptBDInsert');
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
