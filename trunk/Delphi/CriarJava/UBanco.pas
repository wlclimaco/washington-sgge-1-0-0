unit UBanco;

interface
function criarGerarProcedureInsert(Txt:String):String;
function criarGerarProcedureUpdate(Txt:String):String;
implementation

uses BrvFuncoesXE;

function TForm1.criarGerarProcedureInsert(Txt:String):String;
var F:TextFile;
begin
      AssignFile(F,'c:\Ins'+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'.sql');
      Rewrite(F); //abre o arquivo para escrita
      Writeln(F,'CREATE OR REPLACE FUNCTION ins_'+(Txt)+'(p_name character varying, p_autogroup boolean, p_address_related boolean,  p_tenant_id integer, p_create_user character varying)');
      Writeln(F,'RETURNS integer AS');
      Writeln(F,'$$');
      Writeln(F,'DECLARE');
      Writeln(F,'		id int;');
      Writeln(F,'BEGIN');
      Writeln(F,'	INSERT INTO tag');
      Writeln(F,'		   (name');
      Writeln(F,'		   ,auto_group');
      Writeln(F,'		   ,address_related');
      Writeln(F,'		   ,tenant_id');
      Writeln(F,'		   ,create_user)');
      Writeln(F,'	VALUES');
      Writeln(F,'		   (p_name');
      Writeln(F,'		   ,p_autogroup');
      Writeln(F,'		   ,p_address_related');
      Writeln(F,'		   ,(SELECT tenant_id from tenant where tenant.tenant_id = p_tenant_id)');
      Writeln(F,'		   ,p_create_user) RETURNING tag_id INTO id;');
      Writeln(F,'');
      Writeln(F,'	 RETURN id; ');
      Writeln(F,'END');
      Writeln(F,'$$');
      Writeln(F,'LANGUAGE ''plpgsql'';');
      Writeln(F,'');
      Closefile(F);

end;
function TForm1.criarGerarProcedureUpdate(Txt:String):String;
var linha:String;
    F:TextFile;
begin
      AssignFile(F,'c:\Upd'+PrimeiraMaiscula(Txt)+'.sql');
      Rewrite(F);
      Writeln(F,'CREATE OR REPLACE FUNCTION upd_'+Txt+'(');
      AssignFile(arq, EdtDsArquiv.text);
      Reset(arq);   // [ 3 ] Abre o arquivo texto para leitura
      {$I+}
      while (not eof(arq)) do
      begin
           readln(arq, linha); // [ 6 ] Lê uma linha do arquivo
           Writeln(F,escreverCodeXML(linha,3));
      end;

      CloseFile(arq); // [ 8 ] Fecha o arquivo texto aberto
      Writeln(F,')');
      Writeln(F,'RETURNS void AS');
      Writeln(F,'$$');
      Writeln(F,'BEGIN');
      Writeln(F,'	UPDATE '+Txt+' ');
      Writeln(F,'	   SET ');
      AssignFile(arq, EdtDsArquiv.text);

      Reset(arq);   // [ 3 ] Abre o arquivo texto para leitura
      {$I+}
      while (not eof(arq)) do
      begin
           readln(arq, linha); // [ 6 ] Lê uma linha do arquivo
           Writeln(F,escreverCodeXML(linha,4));
      end;

      CloseFile(arq); // [ 8 ] Fecha o arquivo texto aberto

      Writeln(F,'	 WHERE SubstituirID = p_SubstituirID;');
      Writeln(F,'END');
      Writeln(F,'$$');
      Writeln(F,'  LANGUAGE plpgsql;');
      Closefile(F);
end;

end.
