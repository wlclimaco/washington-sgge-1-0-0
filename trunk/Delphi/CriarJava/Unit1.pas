unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls,IniFiles, BrvCustomEdit, BrvEdit, ComCtrls, ExtCtrls, Buttons, BrvBitBtn, BrvSpeedButton,
  DB, DBClient, BrvClientDataSet, Grids, BrvDbGrids, BrvDbGrid;

type
  TForm1 = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    TbsConsulta: TTabSheet;
    FileOpenDialog1: TFileOpenDialog;
    GroupBox1: TGroupBox;
    TabSheet1: TTabSheet;
    TabSheet2: TTabSheet;
    TabSheet3: TTabSheet;
    TabSheet4: TTabSheet;
    TabSheet5: TTabSheet;
    Memo1: TMemo;
    CheckBox1: TCheckBox;
    CheckBox2: TCheckBox;
    CheckBox3: TCheckBox;
    CheckBox4: TCheckBox;
    CheckBox5: TCheckBox;
    CheckBox6: TCheckBox;
    CheckBox7: TCheckBox;
    CheckBox8: TCheckBox;
    CheckBox9: TCheckBox;
    CheckBox10: TCheckBox;
    CheckBox11: TCheckBox;
    Edit1: TEdit;
    Label1: TLabel;
    Label2: TLabel;
    BtnPesquisa: TBrvBitBtn;
    GroupBox2: TGroupBox;
    CheckBox13: TCheckBox;
    Memo2: TMemo;
    CheckBox14: TCheckBox;
    CheckBox15: TCheckBox;
    CheckBox16: TCheckBox;
    CheckBox17: TCheckBox;
    CheckBox18: TCheckBox;
    CheckBox19: TCheckBox;
    Memo3: TMemo;
    Memo4: TMemo;
    Memo5: TMemo;
    Memo6: TMemo;
    Memo7: TMemo;
    TabSheet6: TTabSheet;
    TabSheet7: TTabSheet;
    TabSheet8: TTabSheet;
    TabSheet9: TTabSheet;
    Memo8: TMemo;
    Memo9: TMemo;
    Memo10: TMemo;
    Memo11: TMemo;
    GroupBox3: TGroupBox;
    CheckBox12: TCheckBox;
    CheckBox20: TCheckBox;
    CheckBox21: TCheckBox;
    CheckBox23: TCheckBox;
    CheckBox24: TCheckBox;
    GroupBox4: TGroupBox;
    CheckBox27: TCheckBox;
    TabSheet10: TTabSheet;
    TabSheet11: TTabSheet;
    TabSheet12: TTabSheet;
    TabSheet13: TTabSheet;
    TabSheet14: TTabSheet;
    Memo12: TMemo;
    Memo13: TMemo;
    Memo14: TMemo;
    Memo15: TMemo;
    Memo16: TMemo;
    TabSheet15: TTabSheet;
    Memo17: TMemo;
    CheckBox28: TCheckBox;
    CheckBox29: TCheckBox;
    CheckBox30: TCheckBox;
    CheckBox31: TCheckBox;
    CheckBox32: TCheckBox;
    GroupBox6: TGroupBox;
    CheckBox40: TCheckBox;
    CheckBox41: TCheckBox;
    CheckBox42: TCheckBox;
    CheckBox43: TCheckBox;
    CheckBox44: TCheckBox;
    CheckBox45: TCheckBox;
    GroupBox7: TGroupBox;
    CheckBox46: TCheckBox;
    CheckBox47: TCheckBox;
    CheckBox48: TCheckBox;
    CheckBox49: TCheckBox;
    GroupBox8: TGroupBox;
    CheckBox50: TCheckBox;
    CheckBox51: TCheckBox;
    CheckBox52: TCheckBox;
    CheckBox53: TCheckBox;
    CheckBox54: TCheckBox;
    CheckBox22: TCheckBox;
    TabSheet16: TTabSheet;
    Panel3: TPanel;
    Panel4: TPanel;
    Panel5: TPanel;
    Panel6: TPanel;
    Panel7: TPanel;
    BrvEdit2: TBrvEdit;
    BrvEdit3: TBrvEdit;
    BrvEdit4: TBrvEdit;
    BrvEdit5: TBrvEdit;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    BrvEdit6: TBrvEdit;
    Label12: TLabel;
    BrvEdit7: TBrvEdit;
    Label13: TLabel;
    BrvEdit8: TBrvEdit;
    Label14: TLabel;
    BrvEdit9: TBrvEdit;
    Label15: TLabel;
    BrvEdit10: TBrvEdit;
    Label16: TLabel;
    BrvEdit11: TBrvEdit;
    BrvEdit12: TBrvEdit;
    Label17: TLabel;
    Label18: TLabel;
    BrvEdit13: TBrvEdit;
    BrvEdit14: TBrvEdit;
    Label19: TLabel;
    Label20: TLabel;
    BrvEdit15: TBrvEdit;
    BrvEdit16: TBrvEdit;
    FileOpenDialog2: TFileOpenDialog;
    BrvEdit17: TBrvEdit;
    Label22: TLabel;
    BrvEdit18: TBrvEdit;
    Label23: TLabel;
    Label24: TLabel;
    BrvEdit19: TBrvEdit;
    Label25: TLabel;
    BrvEdit20: TBrvEdit;
    Label26: TLabel;
    BrvEdit21: TBrvEdit;
    Label27: TLabel;
    BrvEdit22: TBrvEdit;
    Label28: TLabel;
    BrvEdit23: TBrvEdit;
    BtnFecEmp: TBrvSpeedButton;
    BtnSalvar: TBrvSpeedButton;
    SbtAjuda: TBrvSpeedButton;
    BrvSpeedButton1: TBrvSpeedButton;
    Label29: TLabel;
    TabSheet17: TTabSheet;
    GroupBox5: TGroupBox;
    TabSheet18: TTabSheet;
    BrvDbGrid1: TBrvDbGrid;
    DataSource1: TDataSource;
    EdtDsArquiv: TEdit;
    SpeedButton2: TSpeedButton;
    DlgArquiv: TOpenDialog;
    ClientDataSet1: TClientDataSet;
    procedure SpeedButton1Click(Sender: TObject);
    procedure BtnPesquisaClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
    procedure gerarModelo(Txt:String);
    function escreverCode(Txt:String):String;
    function verificadorCode(Txt:String):String;
    function escreverCodeXML(Txt:String;Op:Integer):String;
    function criarCodeInquiryResponse(Txt:String):String;
    function criarCodeInquiryRequest(Txt:String):String;
    function criarCodeResponse(Txt:String):String;
    function criarCodeRequest(Txt:String):String;
    Function Explode(Texto, Separador : String) : String;
    Function PrimeiraMaiscula(Texto: String) : String;
    Function MostrarMemo(Memo: TMemo) : String;
    function criarCodeIClasseBCF(Txt:String):String;
    function criarCodeClasseBCFImpl(Txt:String):String;
    function criarCodeIClasseBCL(Txt:String):String;
    function criarCodeIClasseBCLImpl(Txt:String):String;
    function criarCodeIClasseDAC(Txt:String):String;
    function criarCodeIClasseDACImpl(Txt:String):String;
    function criarCodeClasseXML(Txt:String):String;
    function criarCodeClasseSqlMapConfigXml(Txt:String):String;
    function criarCodeClasseAPIControler(Txt:String):String;
    function criarCodeClasseHTML(Txt:String):String;
    function criarCodeClasseMainJS(Txt:String):String;
    function criarCodeClasseInitJS(Txt:String):String;
    function criarGerarProcedureInsert(Txt:String):String;
    function criarGerarProcedureUpdate(Txt:String):String;
    function verificadorCodeAppBanco(Txt:String):String;
    procedure GravarConfiguracao;
    procedure LerConfiguracao;
  end;

var
  Form1: TForm1;
  arq: TextFile;
  F:TextFile;

implementation

uses BrvFuncoesXE;

{$R *.dfm}

procedure TForm1.LerConfiguracao;
Var IniFile  : String ;
    Ini     : TIniFile ;
    Ok : Boolean;

begin
  IniFile := ChangeFileExt( Application.ExeName, '.ini') ;

  Ini := TIniFile.Create( IniFile );
  try

      BrvEdit2.Text   := Ini.ReadString( 'BE','bcf Aplicattion'  ,'') ;
      BrvEdit3.Text   := Ini.ReadString( 'BE','bcl Aplicattion'  ,'') ;
      BrvEdit4.Text   := Ini.ReadString( 'BE','data aplication'  ,'') ;
      BrvEdit5.Text   := Ini.ReadString( 'BE','validation'  ,'') ;

      BrvEdit10.Text   := Ini.ReadString( 'Banco de dados','procedure'  ,'') ;
      BrvEdit11.Text   := Ini.ReadString( 'Banco de dados','insert'  ,'') ;
      BrvEdit12.Text   := Ini.ReadString( 'Banco de dados','table'  ,'') ;
      BrvEdit13.Text   := Ini.ReadString( 'Banco de dados','function'  ,'') ;

      BrvEdit14.Text   := Ini.ReadString( 'FE','context'  ,'') ;
      BrvEdit15.Text   := Ini.ReadString( 'FE','InternacionalizaçãoI'  ,'') ;
      BrvEdit16.Text   := Ini.ReadString( 'FE','InternacionalizaçãoII'  ,'') ;
      BrvEdit17.Text   := Ini.ReadString( 'FE','InternacionalizaçãoIII'  ,'') ;
      BrvEdit18.Text   := Ini.ReadString( 'FE','UniTest'  ,'') ;
      BrvEdit19.Text   := Ini.ReadString( 'FE','test'  ,'') ;

      BrvEdit20.Text   := Ini.ReadString( 'web','Host'  ,'') ;
      BrvEdit21.Text   := Ini.ReadString( 'web','Porta'  ,'') ;

      BrvEdit22.Text   := Ini.ReadString( 'Emitente','CNPJ'  ,'') ;
      BrvEdit23.Text   := Ini.ReadString( 'Emitente','IE'  ,'') ;


  finally
     Ini.Free ;
  end;

end;

procedure TForm1.GravarConfiguracao;
Var IniFile : String ;
    Ini     : TIniFile ;
    StreamMemo : TMemoryStream;
begin
  IniFile := ChangeFileExt( Application.ExeName, '.ini') ;

  Ini := TIniFile.Create( IniFile );
  try
      Ini.WriteString( 'BE','bcf Aplicattion',BrvEdit2.Text) ;
      Ini.WriteString( 'BE','bcl Aplicattion',BrvEdit3.Text) ;
      Ini.WriteString( 'BE','data aplication',BrvEdit4.Text) ;
      Ini.WriteString( 'BE','validation'     ,BrvEdit5.Text) ;


      Ini.WriteString( 'Banco de dados','procedure',BrvEdit10.Text) ;
      Ini.WriteString( 'Banco de dados','insert'   ,BrvEdit11.Text) ;
      Ini.WriteString( 'Banco de dados','table'    ,BrvEdit12.Text) ;
      Ini.WriteString( 'Banco de dados','function' ,BrvEdit13.Text) ;


      Ini.WriteString( 'FE','context'                ,BrvEdit14.Text) ;
      Ini.WriteString( 'FE','InternacionalizaçãoI'   ,BrvEdit15.Text) ;
      Ini.WriteString( 'FE','InternacionalizaçãoII'  ,BrvEdit16.Text) ;
      Ini.WriteString( 'FE','InternacionalizaçãoIII' ,BrvEdit17.Text) ;
      Ini.WriteString( 'FE','UniTest'                ,BrvEdit18.Text) ;
      Ini.WriteString( 'FE','test'                   ,BrvEdit19.Text) ;

      Ini.WriteString( 'web','Host'   ,BrvEdit20.Text) ;
      Ini.WriteString( 'web','Porta'  ,BrvEdit21.Text) ;


      Ini.WriteString( 'Emitente','CNPJ'       ,BrvEdit21.Text);
      Ini.WriteString( 'Emitente','IE'        ,BrvEdit22.Text);

      StreamMemo.Free;
  finally
     Ini.Free ;
  end;
end;
function TForm1.criarGerarProcedureInsert(Txt:String):String;
begin
      AssignFile(F,'c:\Ins'+PrimeiraMaiscula(Edit1.Text)+'.sql');
      Rewrite(F); //abre o arquivo para escrita
      Writeln(F,'CREATE OR REPLACE FUNCTION ins_'+(Edit1.Text)+'(p_name character varying, p_autogroup boolean, p_address_related boolean,  p_tenant_id integer, p_create_user character varying)');
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
begin
      AssignFile(F,'c:\Upd'+PrimeiraMaiscula(Edit1.Text)+'.sql');
      Rewrite(F);
      Writeln(F,'CREATE OR REPLACE FUNCTION upd_'+Edit1.Text+'(');
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
      Writeln(F,'	UPDATE '+Edit1.Text+' ');
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
function TForm1.criarCodeClasseHTML(Txt:String):String;
begin
       AssignFile(F,'c:\I'+PrimeiraMaiscula(Edit1.Text)+'DAC.java');
       Rewrite(F); //abre o arquivo para escrita
       Closefile(F);
end;
function TForm1.criarCodeClasseMainJS(Txt:String):String;
begin
       AssignFile(F,'c:\I'+PrimeiraMaiscula(Edit1.Text)+'DAC.java');
       Rewrite(F); //abre o arquivo para escrita
       Closefile(F);
end;
function TForm1.criarCodeClasseInitJS(Txt:String):String;
begin
       AssignFile(F,'c:\I'+PrimeiraMaiscula(Edit1.Text)+'DAC.java');
        Rewrite(F); //abre o arquivo para escrita
       Closefile(F);
end;


function TForm1.criarCodeClasseAPIControler(Txt:String):String;
begin
      AssignFile(F,'c:\'+PrimeiraMaiscula(Edit1.Text)+'APIControler.java');
      Rewrite(F); //abre o arquivo para escrita
      Writeln(F,'package com.sensus.mlc.wui.'+Edit1.Text+';');
      Writeln(F,'import javax.servlet.http.HttpServletRequest;');
      Writeln(F,'');
      Writeln(F,'import org.slf4j.Logger;');
      Writeln(F,'import org.slf4j.LoggerFactory;');
      Writeln(F,'import org.springframework.stereotype.Controller;');
      Writeln(F,'import org.springframework.web.bind.annotation.RequestBody;');
      Writeln(F,'import org.springframework.web.bind.annotation.RequestMapping;');
      Writeln(F,'import org.springframework.web.bind.annotation.RequestMethod;');
      Writeln(F,'import org.springframework.web.bind.annotation.ResponseBody;');
      Writeln(F,'');
      Writeln(F,'import com.sensus.common.model.response.Response;');
      Writeln(F,'import com.sensus.common.util.SensusInterfaceUtil;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.bcf.I'+PrimeiraMaiscula(Edit1.Text)+'BCF;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.'+PrimeiraMaiscula(Edit1.Text)+'Request;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.response.'+PrimeiraMaiscula(Edit1.Text)+'Response;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.response.Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response;');
      Writeln(F,'import com.sensus.mlc.wui.BaseController;');
      Writeln(F,'import com.sensus.mlc.wui.light.LightAPIController;');
      Writeln(F,'');
      Writeln(F,'/** ');
      Writeln(F,'* @author QATEmployee ');
      Writeln(F,'* ');
      Writeln(F,'*/ ');
      Writeln(F,'@Controller');
      Writeln(F,'@RequestMapping("/api/'+Edit1.Text+'") ');
      Writeln(F,'public class '+PrimeiraMaiscula(Edit1.Text)+'APIController extends BaseController  ');
      Writeln(F,'{ ');
      Writeln(F,'');
      Writeln(F,'/* ');
      Writeln(F,'* URLs Mapping   ');
      Writeln(F,'*/  ');
      Writeln(F,'/** The Constant MAP_FETCH. */ ');
      Writeln(F,'private static final String MAP_FETCH = "/fetch";');
      Writeln(F,'');
      Writeln(F,'private static final String MAP_FETCHALL = "/fetchall";');
      Writeln(F,'');
      Writeln(F,'/** The Constant MAP_DELETE. */ ');
      Writeln(F,'private static final String MAP_DELETE = "/delete";');
      Writeln(F,'');
      Writeln(F,'/** The Constant MAP_INSERT. */  ');
      Writeln(F,'private static final String MAP_INSERT = "/insert";');
      Writeln(F,'');
      Writeln(F,'/** The Constant MAP_INSERT. */  ');
      Writeln(F,'private static final String MAP_UPDATE = "/update";');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'private I'+PrimeiraMaiscula(Edit1.Text)+'BCF '+Edit1.Text+'BCF;');
      Writeln(F,'');
      Writeln(F,'/** The Constant LOG. */');
      Writeln(F,'private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);');
      Writeln(F,'');
      Writeln(F,'/** The Constant CONTROLLER_EXCEPTION_MSG. */  ');
      Writeln(F,'public static final String CONTROLLER_EXCEPTION_MSG = "'+PrimeiraMaiscula(Edit1.Text)+'APIController";');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'public I'+PrimeiraMaiscula(Edit1.Text)+'BCF get'+PrimeiraMaiscula(Edit1.Text)+'BCF() { ');
      Writeln(F,'return '+Edit1.Text+'BCF;');
      Writeln(F,'}  ');
      Writeln(F,'');
      Writeln(F,'public void set'+PrimeiraMaiscula(Edit1.Text)+'BCF(I'+PrimeiraMaiscula(Edit1.Text)+'BCF '+Edit1.Text+'BCF) {  ');
      Writeln(F,'this.'+Edit1.Text+'BCF = '+Edit1.Text+'BCF;');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST) ');
      Writeln(F,'@ResponseBody   ');
      Writeln(F,'public Response insert(@RequestBody '+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request, HttpServletRequest request) ');
      Writeln(F,'{ ');
      Writeln(F,'');
      Writeln(F,''+PrimeiraMaiscula(Edit1.Text)+'Response response = new '+PrimeiraMaiscula(Edit1.Text)+'Response();');
      Writeln(F,'try  ');
      Writeln(F,'{ ');
      Writeln(F,'setUserContext('+Edit1.Text+'Request, request);');
      Writeln(F,'');
      Writeln(F,'response = get'+PrimeiraMaiscula(Edit1.Text)+'BCF().insert'+PrimeiraMaiscula(Edit1.Text)+'('+Edit1.Text+'Request);');
      Writeln(F,'');
      Writeln(F,'}  ');
      Writeln(F,'catch (Exception e)');
      Writeln(F,'{   ');
      Writeln(F,'SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  ');
      Writeln(F,'			new String[] {CONTROLLER_EXCEPTION_MSG});');
      Writeln(F,'}  ');
      Writeln(F,'return response;');
      Writeln(F,'');
      Writeln(F,'} ');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'/**  ');
      Writeln(F,'* Update '+Edit1.Text+'.  ');
      Writeln(F,'*    ');
      Writeln(F,'* @param '+Edit1.Text+'Request the '+Edit1.Text+' request  ');
      Writeln(F,'* @return the '+Edit1.Text+' response  ');
      Writeln(F,'*/  ');
      Writeln(F,'');
      Writeln(F,'@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)  ');
      Writeln(F,'@ResponseBody  ');
      Writeln(F,'public Response update'+PrimeiraMaiscula(Edit1.Text)+'(@RequestBody '+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request, HttpServletRequest request) ');
      Writeln(F,'{ ');
      Writeln(F,'');
      Writeln(F,''+PrimeiraMaiscula(Edit1.Text)+'Response response = new '+PrimeiraMaiscula(Edit1.Text)+'Response();');
      Writeln(F,'try  ');
      Writeln(F,'{ ');
      Writeln(F,'setUserContext('+Edit1.Text+'Request, request);');
      Writeln(F,'');
      Writeln(F,'response = get'+PrimeiraMaiscula(Edit1.Text)+'BCF().update'+PrimeiraMaiscula(Edit1.Text)+'('+Edit1.Text+'Request);');
      Writeln(F,'');
      Writeln(F,'} ');
      Writeln(F,'catch (Exception e) ');
      Writeln(F,'{  ');
      Writeln(F,'SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  ');
      Writeln(F,'			new String[] {CONTROLLER_EXCEPTION_MSG});');
      Writeln(F,'}  ');
      Writeln(F,'return response;');
      Writeln(F,'');
      Writeln(F,'}  ');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'/**  ');
      Writeln(F,'* Delete filial. ');
      Writeln(F,'*      ');
      Writeln(F,'* @param filialRequest the filial request ');
      Writeln(F,'* @return the filial response    ');
      Writeln(F,'*/      ');
      Writeln(F,'');
      Writeln(F,'@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)  ');
      Writeln(F,'@ResponseBody  ');
      Writeln(F,'public Response delete'+PrimeiraMaiscula(Edit1.Text)+'(@RequestBody '+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request, HttpServletRequest request) ');
      Writeln(F,'{  ');
      Writeln(F,'');
      Writeln(F,''+PrimeiraMaiscula(Edit1.Text)+'Response response = new '+PrimeiraMaiscula(Edit1.Text)+'Response();');
      Writeln(F,'try ');
      Writeln(F,'{ ');
      Writeln(F,'setUserContext('+Edit1.Text+'Request, request);');
      Writeln(F,'');
      Writeln(F,'response = get'+PrimeiraMaiscula(Edit1.Text)+'BCF().delete'+PrimeiraMaiscula(Edit1.Text)+'('+Edit1.Text+'Request);');
      Writeln(F,'');
      Writeln(F,'} ');
      Writeln(F,'catch (Exception e) ');
      Writeln(F,'{ ');
      Writeln(F,'SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,');
      Writeln(F,'			new String[] {CONTROLLER_EXCEPTION_MSG});');
      Writeln(F,'}');
      Writeln(F,'return response;');
      Writeln(F,'');
      Writeln(F,'}  ');
      Writeln(F,'');
      Writeln(F,'/** ');
      Writeln(F,'* Fetch all filial.  ');
      Writeln(F,'*  ');
      Writeln(F,'* @param inquiryfilialRequest the inquiryfilial request  ');
      Writeln(F,'* @return the inquiry filial response  ');
      Writeln(F,'*/ ');
      Writeln(F,'');
      Writeln(F,'@RequestMapping(value = MAP_FETCHALL, method = RequestMethod.POST) ');
      Writeln(F,'@ResponseBody ');
      Writeln(F,'public Response fetch(@RequestBody Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request, HttpServletRequest request) ');
      Writeln(F,'{  ');
      Writeln(F,'');
      Writeln(F,'Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response response = new Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response();');
      Writeln(F,'try');
      Writeln(F,'{ ');
      Writeln(F,'setUserContext(inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request, request);');
      Writeln(F,'');
      Writeln(F,'response = get'+PrimeiraMaiscula(Edit1.Text)+'BCF().fetchAll'+PrimeiraMaiscula(Edit1.Text)+'(inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request);');
      Writeln(F,'');
      Writeln(F,'}');
      Writeln(F,'catch (Exception e) ');
      Writeln(F,'{   ');
      Writeln(F,'SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  ');
      Writeln(F,'			new String[] {CONTROLLER_EXCEPTION_MSG});');
      Writeln(F,'} ');
      Writeln(F,'return response;');
      Writeln(F,'');
      Writeln(F,'} ');
      Writeln(F,'');
      Writeln(F,'/**  ');
      Writeln(F,'* Fetch filial by id. ');
      Writeln(F,'* ');
      Writeln(F,'* @param filialRequest the filial request   ');
      Writeln(F,'* @return the filial response   ');
      Writeln(F,'*/    ');
      Writeln(F,'');
      Writeln(F,'@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST) ');
      Writeln(F,'@ResponseBody ');
      Writeln(F,'public Response fetch(@RequestBody '+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request, HttpServletRequest request)   ');
      Writeln(F,'{ ');
      Writeln(F,'');
      Writeln(F,''+PrimeiraMaiscula(Edit1.Text)+'Response response = new '+PrimeiraMaiscula(Edit1.Text)+'Response();');
      Writeln(F,'try   ');
      Writeln(F,'{  ');
      Writeln(F,'setUserContext('+Edit1.Text+'Request, request);');
      Writeln(F,'');
      Writeln(F,'response = get'+PrimeiraMaiscula(Edit1.Text)+'BCF().fetch'+PrimeiraMaiscula(Edit1.Text)+'ById('+Edit1.Text+'Request);');
      Writeln(F,'');
      Writeln(F,'} ');
      Writeln(F,'catch (Exception e)');
      Writeln(F,'{');
      Writeln(F,'SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,  ');
      Writeln(F,'			new String[] {CONTROLLER_EXCEPTION_MSG});');
      Writeln(F,'} ');
      Writeln(F,'return response;');
      Writeln(F,'');
      Writeln(F,'} ');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'}  ');
      Closefile(F);
end;

function TForm1.criarCodeClasseSqlMapConfigXml(Txt:String):String;
begin
      AssignFile(F,'c:\I'+PrimeiraMaiscula(Edit1.Text)+'DAC.java');
      Rewrite(F); //abre o arquivo para escrita
      Writeln(F,'<?xml version="1.0" encoding="UTF-8"?>');
      Writeln(F,'<!DOCTYPE configuration');
      Writeln(F,'PUBLIC "-//mybatis.org//DTD Config 3.0//EN"');
      Writeln(F,'"http://mybatis.org/dtd/mybatis-3-config.dtd">');

      Writeln(F,'<configuration>');

      Writeln(F,'<settings>');
      Writeln(F,'<setting name="lazyLoadingEnabled" value="false" />');
      Writeln(F,'</settings>');

      Writeln(F,'<typeAliases>');
      Writeln(F,'<!-- '+PrimeiraMaiscula(Edit1.Text)+' mapping -->');
      Writeln(F,'<typeAlias alias="Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request" 	type="com.sensus.mlc.'+Edit1.Text+'.model.request.Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request" />');
      Writeln(F,'<typeAlias alias="'+PrimeiraMaiscula(Edit1.Text)+'Request" 			type="com.sensus.mlc.'+Edit1.Text+'.model.request.'+PrimeiraMaiscula(Edit1.Text)+'Request" />');
      Writeln(F,'<typeAlias alias="FilialRequest" 		type="com.sensus.mlc.filial.model.request.FilialRequest" />');
      Writeln(F,'<typeAlias alias="EnderecoRequest" 		type="com.sensus.mlc.endereco.model.request.EnderecoRequest" />');

      Writeln(F,'<!-- '+PrimeiraMaiscula(Edit1.Text)+' and Smartpoint joint mapping -->');
      Writeln(F,'<typeAlias alias="'+PrimeiraMaiscula(Edit1.Text)+'"  Writeln(F,''type="com.sensus.mlc.'+Edit1.Text+'.model.'+PrimeiraMaiscula(Edit1.Text)+'"/>');
      Writeln(F,'<typeAlias alias="Filial" Writeln(F,''    type="com.sensus.mlc.filial.model.Filial"/>');
      Writeln(F,'<typeAlias alias="Endereco" Writeln(F,''type="com.sensus.mlc.endereco.model.Endereco"/>');

      Writeln(F,'<!-- Smartpoint mapping -->');
      Writeln(F,'<typeAlias alias="Group" Writeln(F,''		  type="com.sensus.mlc.group.model.Group" />');
      Writeln(F,'<typeAlias alias="'+Edit1.Text+'" Writeln(F,''	      type="com.sensus.mlc.tag.model.'+Edit1.Text+'" />');
      Writeln(F,'<typeAlias alias="StatusMessage" Writeln(F,''  type="com.sensus.mlc.smartpoint.model.StatusMessage" />');
      Writeln(F,'<typeAlias alias="LightParameter" Writeln(F,''  type="com.sensus.mlc.smartpoint.model.LightParameter" />');
      Writeln(F,'<typeAlias alias="PropertyValidValue" 			  type="com.sensus.mlc.smartpoint.model.PropertyValidValue" />');
      Writeln(F,'<typeAlias alias="StatusException" Writeln(F,''  type="com.sensus.mlc.smartpoint.model.StatusException" />');
      Writeln(F,'<typeAlias alias="LightRequest" Writeln(F,''  type="com.sensus.mlc.smartpoint.model.request.LightRequest" />');
      Writeln(F,'<typeAlias alias="EventSchedule" Writeln(F,''  type="com.sensus.mlc.schedule.model.EventSchedule" />');
      Writeln(F,'<typeAlias alias="OffsetSchedule" Writeln(F,''  type="com.sensus.mlc.schedule.model.OffsetSchedule" />');
      Writeln(F,'<typeAlias alias="CustomSearch" Writeln(F,''  type="com.sensus.mlc.smartpoint.model.CustomSearch" />');
      Writeln(F,'<typeAlias alias="SearchParameter" Writeln(F,''  type="com.sensus.mlc.smartpoint.model.SearchParameter" />');
      Writeln(F,'<typeAlias alias="TimeZoneInfo" Writeln(F,''  type="com.sensus.mlc.base.model.TimeZoneInfo" />');
      Writeln(F,'<typeAlias alias="SensusPartNumberConfiguration"  type="com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration" />');
      Writeln(F,'<typeAlias alias="LightHistory" Writeln(F,''  type="com.sensus.mlc.smartpoint.model.LightHistory" />');
      Writeln(F,'<typeAlias alias="Schedule" Writeln(F,''	  type="com.sensus.mlc.schedule.model.Schedule" />');
      Writeln(F,'<typeAlias alias="Column" Writeln(F,''		  type="com.sensus.mlc.smartpoint.model.Column" />');
      Writeln(F,'<typeAlias alias="GuaranteeLightExistenceRequest" type="com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest" />');
      Writeln(F,'<typeAlias alias="CurrentAlarmWarningMessage" 	  type="com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage" />');
      Writeln(F,'<typeAlias alias="CustomSearchRequest" 			  type="com.sensus.mlc.smartpoint.model.request.CustomSearchRequest" />');
      Writeln(F,'<typeAlias alias="TenantRequest" Writeln(F,''  type="com.sensus.mlc.tenant.model.request.TenantRequest" />');
      Writeln(F,'<typeAlias alias="LightStatusRequest" 			  type="com.sensus.mlc.smartpoint.model.request.LightStatusRequest" />');
      Writeln(F,'<typeAlias alias="LightingControlRequest" 		  type="com.sensus.mlc.base.model.request.LightingControlRequest" />');
      Writeln(F,'<typeAlias alias="ScheduleRequest" Writeln(F,''  type="com.sensus.mlc.schedule.model.request.ScheduleRequest" />');
      Writeln(F,'<typeAlias alias="PropertyValidValuesRequest" 	  type="com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest" />');
      Writeln(F,'<typeAlias alias="ColumnFilterRequest" 			  type="com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest" />');
      Writeln(F,'<typeAlias alias="ColumnFilterResponse" 		  type="com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse" />');

      Writeln(F,'</typeAliases>');

      Writeln(F,'<mappers>');
      Writeln(F,'<mapper resource="com/sensus/mlc/'+Edit1.Text+'/dac/mybatis/map/'+PrimeiraMaiscula(Edit1.Text)+'.xml"/>');
      Writeln(F,'<mapper resource="com/sensus/mlc/filial/dac/mybatis/map/Filial.xml" />');
      Writeln(F,'<mapper resource="com/sensus/mlc/endereco/dac/mybatis/map/Endereco.xml" />');
      Writeln(F,'</mappers>');

      Writeln(F,'</configuration>');


      Closefile(F);
end;
function TForm1.criarCodeClasseXML(Txt:String):String;
var linha: string;
begin
      AssignFile(F,'c:\I'+PrimeiraMaiscula(Edit1.Text)+'DAC.java');
      Rewrite(F); //abre o arquivo para escrita
      Writeln(F,'<?xml version="1.0" encoding="UTF-8"?>');
      Writeln(F,'<!DOCTYPE mapper ');
      Writeln(F,'PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" ');
      Writeln(F,'"http://mybatis.org/dtd/mybatis-3-mapper.dtd">');

      Writeln(F,'<mapper namespace="'+PrimeiraMaiscula(Edit1.Text)+'">');

      Writeln(F,'<resultMap id="'+PrimeiraMaiscula(Edit1.Text)+'sResult" type="'+PrimeiraMaiscula(Edit1.Text)+'">');
      AssignFile(arq, EdtDsArquiv.text);
      Reset(arq);   // [ 3 ] Abre o arquivo texto para leitura
      {$I+}
      while (not eof(arq)) do
      begin
           readln(arq, linha); // [ 6 ] Lê uma linha do arquivo
           Writeln(F,escreverCodeXML(linha,1));
      end;

      CloseFile(arq); // [ 8 ] Fecha o arquivo texto aberto

      Writeln(F,'</resultMap>');

      Writeln(F,'<sql id="all'+PrimeiraMaiscula(Edit1.Text)+'Columms">');
      AssignFile(arq, EdtDsArquiv.text);
      Write(F,'SELECT ');
      Reset(arq);   // [ 3 ] Abre o arquivo texto para leitura
      {$I+}
      while (not eof(arq)) do
      begin
           readln(arq, linha); // [ 6 ] Lê uma linha do arquivo
           Write(F,escreverCodeXML(linha,2)+',');
      end;

      CloseFile(arq); // [ 8 ] Fecha o arquivo texto aberto
      Writeln(F,'</sql>');

      Writeln(F,'<!-- Fetch All '+Edit1.Text+'s -->');
      Writeln(F,'<select id="fetchAll'+PrimeiraMaiscula(Edit1.Text)+'s" parameterType="Map" resultMap="'+PrimeiraMaiscula(Edit1.Text)+'sResult">');

      Writeln(F,'SELECT  <include refid="all'+PrimeiraMaiscula(Edit1.Text)+'Columms"/>');
      Writeln(F,'FROM '+Edit1.Text+';   ');

      Writeln(F,'</select>');

      Writeln(F,'<select id="fetchAll'+PrimeiraMaiscula(Edit1.Text)+'ById"  parameterType="Map" resultMap="'+PrimeiraMaiscula(Edit1.Text)+'Result">');

      Writeln(F,'   select <include refid="all'+PrimeiraMaiscula(Edit1.Text)+'Columms"/>');
      Writeln(F,'   from '+Edit1.Text+' e   ');
      Writeln(F,'   where   ');
      Writeln(F,'   e.codemp = #{codemp}  ');

      Writeln(F,'</select>');

      Writeln(F,'<!-- Insert '+PrimeiraMaiscula(Edit1.Text)+' -->');
      Writeln(F,'<select id="insert'+PrimeiraMaiscula(Edit1.Text)+'" parameterType="'+PrimeiraMaiscula(Edit1.Text)+'" resultType="int">');
      Writeln(F,'SELECT ins_'+Edit1.Text+' (   ');
      Writeln(F,'#{emdemp,jdbcType=VARCHAR}, ');
      Writeln(F,'#{numemp,jdbcType=INTEGER}, ');
      Writeln(F,'#{complemp,jdbcType=VARCHAR},  ');
      Writeln(F,'#{bairemp,jdbcType=VARCHAR},  ');
      Writeln(F,'#{cepemp,jdbcType=VARCHAR},  ');
      Writeln(F,'#{cidemp,jdbcType=VARCHAR},  ');
      Writeln(F,'#{ufemp,jdbcType=VARCHAR},   ');
      Writeln(F,'#{dddemp,jdbcType=VARCHAR},  ');
      Writeln(F,'#{foneemp,jdbcType=VARCHAR}, ');
      Writeln(F,'#{faxemp,jdbcType=VARCHAR},   ');
      Writeln(F,'#{emailemp,jdbcType=VARCHAR},  ');
      Writeln(F,'#{wwwemp,jdbcType=VARCHAR},    ');
      Writeln(F,'#{nomecontemp,jdbcType=VARCHAR}, ');
      Writeln(F,'#{siglauf,jdbcType=VARCHAR},     ');
      Writeln(F,'#{codmunic,jdbcType=VARCHAR},   ');
      Writeln(F,'#{codpais,jdbcType=VARCHAR},    ');
      Writeln(F,'#{razemp,jdbcType=VARCHAR},     ');
      Writeln(F,'#{nomeemp,jdbcType=VARCHAR},    ');
      Writeln(F,'#{cnpjemp,jdbcType=VARCHAR},     ');
      Writeln(F,'#{inscemp,jdbcType=VARCHAR},      ');
      Writeln(F,'#{codeanemp,jdbcType=VARCHAR},    ');
      Writeln(F,'#{multialmoxemp,jdbcType=VARCHAR}, ');
      Writeln(F,'#{fotoemp,jdbcType=VARCHAR},      ');
      Writeln(F,'#{codmunic,jdbcType=VARCHAR},      ');
      Writeln(F,'#{percissemp,jdbcType=DOUBLE})     ');
      Writeln(F,'</select>');

      Writeln(F,'<select id="insertEndereco" parameterType="'+PrimeiraMaiscula(Edit1.Text)+'" resultType="int">');
      Writeln(F,'SELECT ins_endereco( ');
      Writeln(F,'#{emdemp,jdbcType=VARCHAR}, ');
      Writeln(F,'#{numemp,jdbcType=INTEGER},  ');
      Writeln(F,'#{complemp,jdbcType=VARCHAR},  ');
      Writeln(F,'#{bairemp,jdbcType=VARCHAR}, ');
      Writeln(F,'#{cepemp,jdbcType=VARCHAR},  ');
      Writeln(F,'#{cidemp,jdbcType=VARCHAR}, ');
      Writeln(F,'#{ufemp,jdbcType=VARCHAR}, ');
      Writeln(F,'#{dddemp,jdbcType=VARCHAR},  ');
      Writeln(F,'#{foneemp,jdbcType=VARCHAR},  ');
      Writeln(F,'#{faxemp,jdbcType=VARCHAR},   ');
      Writeln(F,'#{emailemp,jdbcType=VARCHAR},  ');
      Writeln(F,'#{wwwemp,jdbcType=VARCHAR},     ');
      Writeln(F,'#{nomecontemp,jdbcType=VARCHAR}, ');
      Writeln(F,'#{siglauf,jdbcType=VARCHAR},  ');
      Writeln(F,'#{codmunic,jdbcType=VARCHAR}, ');
      Writeln(F,'#{codpais,jdbcType=VARCHAR})  ');
      Writeln(F,'</select>');
      Writeln(F,'</mapper>');
      Closefile(F);
end;
function TForm1.criarCodeIClasseDACImpl(Txt:String):String;
begin
      AssignFile(F,'c:\I'+PrimeiraMaiscula(Edit1.Text)+'DAC.java');
      Rewrite(F); //abre o arquivo para escrita
      Writeln(F,'package com.sensus.mlc.'+Edit1.Text+'.dac.mybatis;');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'import java.util.Date;');
      Writeln(F,'import java.util.HashMap;');
      Writeln(F,'import java.util.List;');
      Writeln(F,'import java.util.Map;');
      Writeln(F,'');
      Writeln(F,'import org.mybatis.spring.support.SqlSessionDaoSupport;');
      Writeln(F,'import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;');
      Writeln(F,'import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;');
      Writeln(F,'import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;');
      Writeln(F,'import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;');
      Writeln(F,'import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;');
      Writeln(F,'import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;');
      Writeln(F,'import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getParametersToFetchAllLights;');
      Writeln(F,'');
      Writeln(F,'import com.sensus.common.model.request.Request;');
      Writeln(F,'import com.sensus.common.model.response.InternalResponse;');
      Writeln(F,'import com.sensus.common.model.response.InternalResultsResponse;');
      Writeln(F,'import com.sensus.common.validation.ValidationUtil;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.dac.I'+PrimeiraMaiscula(Edit1.Text)+'DAC;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.'+PrimeiraMaiscula(Edit1.Text)+';');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.'+PrimeiraMaiscula(Edit1.Text)+'Request;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.response.'+PrimeiraMaiscula(Edit1.Text)+'Response;');
      Writeln(F,'');
      Writeln(F,'/**');
      Writeln(F,'* The Class ActionDACImpl.');
      Writeln(F,'*');
      Writeln(F,'* @author - QAT Brazil.');
      Writeln(F,'*');
      Writeln(F,'*/');
      Writeln(F,'public class '+PrimeiraMaiscula(Edit1.Text)+'DACImpl extends SqlSessionDaoSupport implements I'+PrimeiraMaiscula(Edit1.Text)+'DAC');
      Writeln(F,'{');
      Writeln(F,'');
      Writeln(F,'/** The Constant PARAMSIZE7. */');
      Writeln(F,'');
      Writeln(F,'/** The Constant PARAMSIZE1. */');
      Writeln(F,'');
      Writeln(F,'/** The Constant TAG_NAMESPACE. */');
      Writeln(F,'private static final String EMPRESA_NAMESPACE = "'+PrimeiraMaiscula(Edit1.Text)+'.";');
      Writeln(F,'');
      Writeln(F,'/** The Constant FETCH_ALL_TAGS. */');
      Writeln(F,'private static final String FETCH_ALL_EMPRESAS = EMPRESA_NAMESPACE + "fetchAll'+PrimeiraMaiscula(Edit1.Text)+'s";');
      Writeln(F,'');
      Writeln(F,'private static final Integer PARAMSIZE1 = 1;');
      Writeln(F,'');
      Writeln(F,'/** The Constant PARAMSIZE6. */');
      Writeln(F,'private static final Integer PARAMSIZE6 = 6;');
      Writeln(F,'');
      Writeln(F,'/** The Constant PARAMSIZE6. */');
      Writeln(F,'private static final Integer PARAMSIZE32 = 32;');
      Writeln(F,'');
      Writeln(F,'/** The Constant PARAMSIZE7. */');
      Writeln(F,'private static final Integer PARAMSIZE7 = 7;');
      Writeln(F,'');
      Writeln(F,'/** The Constant TOTAL_COLUMN. */');
      Writeln(F,'private static final Integer TOTAL_COLUMN = 5;');
      Writeln(F,'');
      Writeln(F,'/** The Constant COLUMN_0. */');
      Writeln(F,'private static final Integer COLUMN_0 = 0;');
      Writeln(F,'');
      Writeln(F,'/** The Constant COLUMN_1. */');
      Writeln(F,'private static final Integer COLUMN_1 = 1;');
      Writeln(F,'');
      Writeln(F,'/** The Constant COLUMN_2. */');
      Writeln(F,'private static final Integer COLUMN_2 = 2;');
      Writeln(F,'');
      Writeln(F,'/** The Constant COLUMN_3. */');
      Writeln(F,'private static final Integer COLUMN_3 = 3;');
      Writeln(F,'');
      Writeln(F,'/** The Constant COLUMN_4. */');
      Writeln(F,'private static final Integer COLUMN_4 = 4;');
      Writeln(F,'');
      Writeln(F,'/** The Constant ACTION_NAME_HEADER. */');
      Writeln(F,'private static final String ACTION_NAME_HEADER = "Action Name";');
      Writeln(F,'');
      Writeln(F,'/** The Constant PAGINATION_TOTAL_ROWS. */');
      Writeln(F,'private static final String PAGINATION_TOTAL_ROWS = EMPRESA_NAMESPACE + "PaginationTotalRows";');
      Writeln(F,'');
      Writeln(F,'/** The Constant ORDERBY. */');
      Writeln(F,'private static final String ORDERBY = "orderBy";');
      Writeln(F,'');
      Writeln(F,'/** The Constant GROUPID. */');
      Writeln(F,'private static final String EMPRESA_ID = "codEmp";');
      Writeln(F,'');
      Writeln(F,'/** The Constant START_PAGE. */');
      Writeln(F,'private static final String START_PAGE = "startPage";');
      Writeln(F,'');
      Writeln(F,'/** The Constant ACTION_TYPE_HEADER. */');
      Writeln(F,'private static final String ACTION_TYPE_HEADER = "Action Type";');
      Writeln(F,'');
      Writeln(F,'/** The Constant MODIFIED_BY_HEADER. */');
      Writeln(F,'private static final String MODIFIED_BY_HEADER = "Modified By";');
      Writeln(F,'');
      Writeln(F,'/** The Constant DATE_MODIFIED_HEADER. */');
      Writeln(F,'private static final String DATE_MODIFIED_HEADER = "Date Modified";');
      Writeln(F,'');
      Writeln(F,'/** The Constant LOCKED_HEADER. */');
      Writeln(F,'private static final String LOCKED_HEADER = "Locked";');
      Writeln(F,'');
      Writeln(F,'private static final String TENANT_ID = "tenantId";');
      Writeln(F,'');
      Writeln(F,'/** The Constant ORDER_BY. */');
      Writeln(F,'private static final String ORDER_BY = "order_by";');
      Writeln(F,'');
      Writeln(F,'/** The Constant SORT_BY. */');
      Writeln(F,'private static final String SORT_BY = "sort_by";');
      Writeln(F,'');
      Writeln(F,'/** The Constant PAGE_SIZE. */');
      Writeln(F,'private static final String PAGE_SIZE = "page_size";');
      Writeln(F,'');
      Writeln(F,'/** The Constant START_ROW. */');
      Writeln(F,'private static final String START_ROW = "start_row";');
      Writeln(F,'');
      Writeln(F,'/** The Constant ACTION_NAME. */');
      Writeln(F,'private static final String ACTION_NAME = "action_name";');
      Writeln(F,'');
      Writeln(F,'/** The Constant ACTION_TYPES. */');
      Writeln(F,'private static final String ACTION_TYPES = "action_types";');
      Writeln(F,'');
      Writeln(F,'/** The Constant USERS. */');
      Writeln(F,'private static final String USERS = "users";');
      Writeln(F,'');
      Writeln(F,'/** The Constant ACTION_ID. */');
      Writeln(F,'private static final String ACTION_ID = "action_id";');
      Writeln(F,'');
      Writeln(F,'/** The Constant ACTION_TYPE. */');
      Writeln(F,'private static final String ACTION_TYPE = "action_type";');
      Writeln(F,'');
      Writeln(F,'/** The Constant ACTION_DESCRIPTION. */');
      Writeln(F,'private static final String ACTION_DESCRIPTION = "action_description";');
      Writeln(F,'');
      Writeln(F,'/** The Constant START_STEP. */');
      Writeln(F,'private static final String START_STEP = "start_step";');
      Writeln(F,'');
      Writeln(F,'/** The Constant CREATE_USER. */');
      Writeln(F,'private static final String CREATE_USER = "create_user";');
      Writeln(F,'');
      Writeln(F,'/** The Constant STEP_LIST. */');
      Writeln(F,'private static final String STEP_LIST = "step_list";');
      Writeln(F,'');
      Writeln(F,'/** The Constant DEMAND_RESET_STEP_LIST. */');
      Writeln(F,'private static final String DEMAND_RESET_STEP_LIST = "1,2,3,4";');
      Writeln(F,'');
      Writeln(F,'/** The Constant UNSELECTION_PAGINATION_IDS. */');
      Writeln(F,'private static final String UNSELECTION_PAGINATION_IDS = "unselectionPaginationIds";');
      Writeln(F,'');
      Writeln(F,'/** The Constant INSERT_SMART_POINT_TO_TAG. */');
      Writeln(F,'private static final String INSERT_SMART_POINT_TO_TAG = EMPRESA_NAMESPACE + "insertSmartPointTo'+Edit1.Text+'";');
      Writeln(F,'');
      Writeln(F,'/** The Constant INSERT_TAG. */');
      Writeln(F,'private static final String INSERT_EMPRESA = EMPRESA_NAMESPACE + "insert'+PrimeiraMaiscula(Edit1.Text)+'";');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'/** The Constant SENSUS_EPM_ACTIONVALIDATOR_ACTION_IS_SCHEDULED. */');
      Writeln(F,'private static final String SENSUS_EPM_ACTIONVALIDATOR_ACTION_IS_SCHEDULED =');
      Writeln(F,'"sensus.epm.actionvalidator.action.is_scheduled";');
      Writeln(F,'');
      Writeln(F,'/** The Constant ADD_ACTION_FAILED. */');
      Writeln(F,'private static final String ADD_ACTION_FAILED = "sensus.epm.actionbclimpl.add.action.failed";');
      Writeln(F,'');
      Writeln(F,'/**');
      Writeln(F,'* Prepare data to write file.');
      Writeln(F,'*');
      Writeln(F,'* @param actionList the action list');
      Writeln(F,'* @return the string[][]');
      Writeln(F,'*/');
      Writeln(F,'private String[][] preapreDataToWriteFile(List<'+PrimeiraMaiscula(Edit1.Text)+'> actionList)');
      Writeln(F,'{');
      Writeln(F,'String[][] excelData = new String[actionList.size() + 1][TOTAL_COLUMN];');
      Writeln(F,'');
      Writeln(F,'// HEADER');
      Writeln(F,'excelData[0][COLUMN_0] = ACTION_NAME_HEADER;');
      Writeln(F,'excelData[0][COLUMN_1] = ACTION_TYPE_HEADER;');
      Writeln(F,'excelData[0][COLUMN_2] = MODIFIED_BY_HEADER;');
      Writeln(F,'excelData[0][COLUMN_3] = DATE_MODIFIED_HEADER;');
      Writeln(F,'excelData[0][COLUMN_4] = LOCKED_HEADER;');
      Writeln(F,'');
      Writeln(F,'for (int i = 1; i <= actionList.size(); i++)');
      Writeln(F,'{ ');
      Writeln(F,''+PrimeiraMaiscula(Edit1.Text)+' '+Edit1.Text+' = actionList.get(i - 1);');
      Writeln(F,'');
      Writeln(F,'excelData[i][COLUMN_0] = '+Edit1.Text+'.getCodemp().toString();');
      Writeln(F,'');
      Writeln(F,'excelData[i][COLUMN_1] = null;');
      Writeln(F,'if (!ValidationUtil.isNull('+Edit1.Text+'.getNomeemp()))');
      Writeln(F,'{');
      Writeln(F,'excelData[i][COLUMN_1] = '+Edit1.Text+'.getRazemp();');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'excelData[i][COLUMN_2] = '+Edit1.Text+'.getCnpjemp();');
      Writeln(F,'');
      Writeln(F,'excelData[i][COLUMN_3] = null;');
      Writeln(F,'if (!ValidationUtil.isNull('+Edit1.Text+'.getInscemp()))');
      Writeln(F,'{');
      Writeln(F,'excelData[i][COLUMN_3] = '+Edit1.Text+'.getInscemp();');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'excelData[i][COLUMN_4] = null;');
      Writeln(F,'if (!ValidationUtil.isNull('+Edit1.Text+'.getCodmunic()))');
      Writeln(F,'{');
      Writeln(F,'excelData[i][COLUMN_4] = '+Edit1.Text+'.getWwwemp();');
      Writeln(F,'}');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'return excelData;');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'@Override');
      Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> insert'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request)');
      Writeln(F,'{');
      Writeln(F,'');
      Writeln(F,'// Define user from context.');
      Writeln(F,''+Edit1.Text+'Request.get'+PrimeiraMaiscula(Edit1.Text)+'().setCreateUser('+Edit1.Text+'Request.getUserContext().getUserId());');
      Writeln(F,'');
      Writeln(F,'paramMap.put(UNSELECTION_PAGINATION_IDS, null);');
      Writeln(F,'');
      Writeln(F,'if (!ValidationUtil.isNullOrEmpty('+Edit1.Text+'Request.getUnselectionPaginationIds()))');
      Writeln(F,'{');
      Writeln(F,'paramMap.put(UNSELECTION_PAGINATION_IDS, '+Edit1.Text+'Request.getUnselectionPaginationIds());');
      Writeln(F,'}');
      Writeln(F,''+Edit1.Text+'.setCodemp((Integer)doQueryForObject(getSqlSession(), "insertEndereco", '+Edit1.Text+'Request.get'+PrimeiraMaiscula(Edit1.Text)+'()));');
      Writeln(F,'');
      Writeln(F,'InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> response = new InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'>();');
      Writeln(F,'response.addResult('+Edit1.Text+');');
      Writeln(F,'return response;');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'@SuppressWarnings("unchecked")');
      Writeln(F,'@Override');
      Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> fetchAll'+PrimeiraMaiscula(Edit1.Text)+'(Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request inquiry'+Edit1.Text+'Request)');
      Writeln(F,'{');
      Writeln(F,'InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> response = new InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'>();');
      Writeln(F,'HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);');
      Writeln(F,'paramMap.put(TENANT_ID, inquiry'+Edit1.Text+'Request.getTenant().getId());');
      Writeln(F,'paramMap.put(PAGE_SIZE, inquiry'+Edit1.Text+'Request.getPageSize());');
      Writeln(F,'paramMap.put(START_ROW, inquiry'+Edit1.Text+'Request.getStartRow());');
      Writeln(F,'paramMap.put(START_PAGE, inquiry'+Edit1.Text+'Request.getStartPage());');
      Writeln(F,'paramMap.put(ORDERBY, '+Edit1.Text+'OrderByEnum.NAME_COLUMN.getValue());');
      Writeln(F,'');
      Writeln(F,'if (!ValidationUtil.isNullOrEmpty(inquiry'+Edit1.Text+'Request.getSortExpressions()))');
      Writeln(F,'{');
      Writeln(F,'paramMap.put(ORDERBY, inquiry'+Edit1.Text+'Request.getSortExpressions().get(0));');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'if (!ValidationUtil.isNull(inquiry'+Edit1.Text+'Request.get'+PrimeiraMaiscula(Edit1.Text)+'s()))');
      Writeln(F,'{');
      Writeln(F,'paramMap.put(EMPRESA_ID, inquiry'+Edit1.Text+'Request.get'+PrimeiraMaiscula(Edit1.Text)+'s().get(0).getCodemp());');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'doQueryForList(getSqlSession(), FETCH_ALL_EMPRESAS, paramMap, response);');
      Writeln(F,'');
      Writeln(F,'Integer totalRows = (Integer)doQueryForObject(getSqlSession(),');
      Writeln(F,'PAGINATION_TOTAL_ROWS, paramMap);');
      Writeln(F,'');
      Writeln(F,'response.getResultsSetInfo().setTotalRowsAvailable(totalRows);');
      Writeln(F,'return response;');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'@Override');
      Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> fetch'+PrimeiraMaiscula(Edit1.Text)+'ById('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request)');
      Writeln(F,'{');
      Writeln(F,'InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> response = new InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'>();');
      Writeln(F,'');
      Writeln(F,'response.getResultsList().addAll(');
      Writeln(F,'getSqlSession().selectList("'+PrimeiraMaiscula(Edit1.Text)+'Map.fetch'+PrimeiraMaiscula(Edit1.Text)+'ById", '+Edit1.Text+'Request.get'+PrimeiraMaiscula(Edit1.Text)+'()));');
      Writeln(F,'');
      Writeln(F,'return response;');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'@Override');
      Writeln(F,'public InternalResponse generateFileCSV(Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request)');
      Writeln(F,'{');
      Writeln(F,'InternalResponse response = new InternalResponse();');
      Writeln(F,'');
      Writeln(F,'preapreDataToWriteFile(inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request.get'+PrimeiraMaiscula(Edit1.Text)+'s());');
      Writeln(F,'');
      Writeln(F,'if (GenerateFileCSV.generateCSVFile(inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request.getFileName(), excelData))');
      Writeln(F,'{');
      Writeln(F,'response.setStatus(Status.OperationSuccess);');
      Writeln(F,'}');
      Writeln(F,'else');
      Writeln(F,'{');
      Writeln(F,'response.setStatus(Status.ExceptionError);');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'return response;');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'@Override');
      Writeln(F,'public '+PrimeiraMaiscula(Edit1.Text)+'Response fetchAll'+PrimeiraMaiscula(Edit1.Text)+'Types(Request request)');
      Writeln(F,'{');
      Writeln(F,'// TODO Auto-generated method stub');
      Writeln(F,'return null;');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'@Override');
      Writeln(F,'public '+PrimeiraMaiscula(Edit1.Text)+'Response fetchAll'+PrimeiraMaiscula(Edit1.Text)+'Filial(Request request)');
      Writeln(F,'{');
      Writeln(F,'// TODO Auto-generated method stub');
      Writeln(F,'return null;');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'@Override');
      Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> update'+PrimeiraMaiscula(Edit1.Text)+'(');
      Writeln(F,''+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request) {');
      Writeln(F,'// TODO Auto-generated method stub');
      Writeln(F,'return null;');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'@Override');
      Writeln(F,'public InternalResponse delete'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request) {');
      Writeln(F,'// TODO Auto-generated method stub');
      Writeln(F,'return null;');
      Writeln(F,'}');
      Writeln(F,'');
      Writeln(F,'}');
      Writeln(F,'');
      Closefile(F);
end;

 function TForm1.criarCodeIClasseDAC(Txt:String):String;
 begin
        AssignFile(F,'c:\I'+PrimeiraMaiscula(Edit1.Text)+'DAC.java');
        Rewrite(F); //abre o arquivo para escrita
        Writeln(F,'package com.sensus.mlc.'+Edit1.Text+'.dac');
        Writeln(F,'');
        Writeln(F,'');
        Writeln(F,'import com.sensus.common.model.request.Request');
        Writeln(F,'import com.sensus.common.model.response.InternalResponse');
        Writeln(F,'import com.sensus.common.model.response.InternalResultsResponse');
        Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.'+PrimeiraMaiscula(Edit1.Text)+'');
        Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.'+PrimeiraMaiscula(Edit1.Text)+'Request');
        Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request');
        Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.response.'+PrimeiraMaiscula(Edit1.Text)+'Response');
        Writeln(F,'');
        Writeln(F,'/**');
        Writeln(F,'* The Interface IActionDAC.');
        Writeln(F,'*');
        Writeln(F,'* @author - QAT Brazil.');
        Writeln(F,'*');
        Writeln(F,'*/');
        Writeln(F,'public interface I'+PrimeiraMaiscula(Edit1.Text)+'DAC');
        Writeln(F,'{');
        Writeln(F,'');
        Writeln(F,'/**');
        Writeln(F,'* Update '+Edit1.Text+'.');
        Writeln(F,'*');
        Writeln(F,'* @param '+Edit1.Text+'Request the '+Edit1.Text+' request');
        Writeln(F,'* @return the '+Edit1.Text+' response');
        Writeln(F,'*/');
        Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> update'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request)');
        Writeln(F,'');
        Writeln(F,'/**');
        Writeln(F,'* Delete '+Edit1.Text+'.');
        Writeln(F,'*');
        Writeln(F,'* @param '+Edit1.Text+'Request the '+Edit1.Text+' request');
        Writeln(F,'* @return the '+Edit1.Text+' response');
        Writeln(F,'*/');
        Writeln(F,'public InternalResponse delete'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request)');
        Writeln(F,'');
        Writeln(F,'/**');
        Writeln(F,'* Fetch all '+Edit1.Text+'.');
        Writeln(F,'*');
        Writeln(F,'* @param inquiry'+Edit1.Text+'Request the inquiry'+Edit1.Text+' request');
        Writeln(F,'* @return the inquiry '+Edit1.Text+' response');
        Writeln(F,'*/');
        Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> fetchAll'+PrimeiraMaiscula(Edit1.Text)+'(Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request inquiry'+Edit1.Text+'Request)');
        Writeln(F,'');
        Writeln(F,'/**');
        Writeln(F,'* Fetch '+Edit1.Text+' by id.');
        Writeln(F,'*');
        Writeln(F,'* @param inquiry'+Edit1.Text+'Request the inquiry'+Edit1.Text+' request');
        Writeln(F,'* @return the internal results response');
        Writeln(F,'*/');
        Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> fetch'+PrimeiraMaiscula(Edit1.Text)+'ById('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request)');
        Writeln(F,'');
        Writeln(F,'/**');
        Writeln(F,'* Generate file csv.');
        Writeln(F,'*');
        Writeln(F,'* @param inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request the inquiry '+Edit1.Text+' request');
        Writeln(F,'* @return the inquiry '+Edit1.Text+' response');
        Writeln(F,'*/');
        Writeln(F,'public InternalResponse generateFileCSV(Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request)');
        Writeln(F,'');
        Writeln(F,'/**');
        Writeln(F,'* Fetch all '+Edit1.Text+' types.');
        Writeln(F,'*');
        Writeln(F,'* @param request the request');
        Writeln(F,'* @return the '+Edit1.Text+' response');
        Writeln(F,'*/');
        Writeln(F,'public '+PrimeiraMaiscula(Edit1.Text)+'Response fetchAll'+PrimeiraMaiscula(Edit1.Text)+'Types(Request request)');
        Writeln(F,'');
        Writeln(F,'/**');
        Writeln(F,'* Fetch all '+Edit1.Text+' filial.');
        Writeln(F,'*');
        Writeln(F,'* @param request the request');
        Writeln(F,'* @return the '+Edit1.Text+' response');
        Writeln(F,'*/');
        Writeln(F,'public '+PrimeiraMaiscula(Edit1.Text)+'Response fetchAll'+PrimeiraMaiscula(Edit1.Text)+'Filial(Request request)');
        Writeln(F,'');
        Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> insert'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request)');
        Writeln(F,'}');
        Writeln(F,'');
        Writeln(F,'');
        Closefile(F);
 end;

function TForm1.criarCodeIClasseBCLImpl(Txt:String):String;
begin
      AssignFile(F,'c:\'+PrimeiraMaiscula(Edit1.Text)+'BCLImpl.java');
      Rewrite(F); //abre o arquivo para escrita
      Writeln(F,'package com.sensus.mlc.'+Edit1.Text+'.bcl.impl; ');
      Writeln(F,' ');
      Writeln(F,'import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure; ');
      Writeln(F,'import static com.sensus.mlc.base.util.LCHelp.createProcessRequest; ');
      Writeln(F,'  ');
      Writeln(F,'import java.util.ArrayList; ');
      Writeln(F,'import java.util.List; ');
      Writeln(F,' ');
      Writeln(F,'import org.apache.commons.logging.Log; ');
      Writeln(F,'import org.apache.commons.logging.LogFactory; ');
      Writeln(F,'  ');
      Writeln(F,'import com.sensus.common.model.response.InternalResponse; ');
      Writeln(F,'import com.sensus.common.model.response.InternalResultsResponse; ');
      Writeln(F,'import com.sensus.common.util.SensusAppContext; ');
      Writeln(F,'import com.sensus.common.validation.ValidationUtil; ');
      Writeln(F,'import com.sensus.mlc.base.util.LCDateUtil;   ');
      Writeln(F,'import com.sensus.mlc.base.util.LCHelp;        ');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.bcl.I'+PrimeiraMaiscula(Edit1.Text)+'BCL; ');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.dac.I'+PrimeiraMaiscula(Edit1.Text)+'DAC;  ');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.'+PrimeiraMaiscula(Edit1.Text)+';    ');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.'+PrimeiraMaiscula(Edit1.Text)+'Request; ');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request;  ');
      Writeln(F,'import com.sensus.mlc.process.bcl.IProcessBCL;    ');
      Writeln(F,'import com.sensus.mlc.process.model.LCAction;  ');
      Writeln(F,'import com.sensus.mlc.process.model.LCActionParameter;  ');
      Writeln(F,'import com.sensus.mlc.process.model.LCActionTypeEnum;  ');
      Writeln(F,'import com.sensus.mlc.process.model.Process;   ');
      Writeln(F,'import com.sensus.mlc.process.model.ProcessItemStatusEnum; ');
      Writeln(F,'import com.sensus.mlc.process.model.ProcessStatusReasonEnum; ');
      Writeln(F,'import com.sensus.mlc.process.model.request.ProcessRequest; ');
      Writeln(F,'import com.sensus.mlc.schedule.bcl.IScheduleBCL;  ');
      Writeln(F,'import com.sensus.mlc.smartpoint.model.Light; ');
      Writeln(F,'import com.sensus.mlc.smartpoint.model.PropertyEnum; ');
      Writeln(F,'import com.sensus.mlc.smartpoint.model.SearchParameter;');
      Writeln(F,'  ');
      Writeln(F,'/** ');
      Writeln(F,' * Action BCL implementation class. ');
      Writeln(F,' * ');
      Writeln(F,' * @author QAT. ');
      Writeln(F,' */');
      Writeln(F,'public class '+PrimeiraMaiscula(Edit1.Text)+'BCLImpl implements I'+PrimeiraMaiscula(Edit1.Text)+'BCL ');
      Writeln(F,'package com.sensus.mlc.'+Edit1.Text+'.bcl.impl');
      Writeln(F,' ');
      Writeln(F,'import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure');
      Writeln(F,'import static com.sensus.mlc.base.util.LCHelp.createProcessRequest');
      Writeln(F,' ');
      Writeln(F,'import java.util.ArrayList');
      Writeln(F,'import java.util.List');
      Writeln(F,' ');
      Writeln(F,'import org.apache.commons.logging.Log');
      Writeln(F,'import org.apache.commons.logging.LogFactory');
      Writeln(F,' ');
      Writeln(F,'import com.sensus.common.model.response.InternalResponse');
      Writeln(F,'import com.sensus.common.model.response.InternalResultsResponse');
      Writeln(F,'import com.sensus.common.util.SensusAppContext');
      Writeln(F,'import com.sensus.common.validation.ValidationUtil');
      Writeln(F,'import com.sensus.mlc.base.util.LCDateUtil');
      Writeln(F,'import com.sensus.mlc.base.util.LCHelp');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.bcl.I'+PrimeiraMaiscula(Edit1.Text)+'BCL');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.dac.I'+PrimeiraMaiscula(Edit1.Text)+'DAC');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.'+PrimeiraMaiscula(Edit1.Text)+'');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.'+PrimeiraMaiscula(Edit1.Text)+'Request');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request');
      Writeln(F,'import com.sensus.mlc.process.bcl.IProcessBCL');
      Writeln(F,'import com.sensus.mlc.process.model.LCAction');
      Writeln(F,'import com.sensus.mlc.process.model.LCActionParameter');
      Writeln(F,'import com.sensus.mlc.process.model.LCActionTypeEnum');
      Writeln(F,'import com.sensus.mlc.process.model.Process');
      Writeln(F,'import com.sensus.mlc.process.model.ProcessItemStatusEnum');
      Writeln(F,'import com.sensus.mlc.process.model.ProcessStatusReasonEnum');
      Writeln(F,'import com.sensus.mlc.process.model.request.ProcessRequest');
      Writeln(F,'import com.sensus.mlc.schedule.bcl.IScheduleBCL');
      Writeln(F,'import com.sensus.mlc.smartpoint.model.Light');
      Writeln(F,'import com.sensus.mlc.smartpoint.model.PropertyEnum');
      Writeln(F,'import com.sensus.mlc.smartpoint.model.SearchParameter');
      Writeln(F,' ');
      Writeln(F,'/**');
      Writeln(F,'* Action BCL implementation class. ');
      Writeln(F,'*');
      Writeln(F,'* @author QAT. ');
      Writeln(F,'*/');
      Writeln(F,'public class '+PrimeiraMaiscula(Edit1.Text)+'BCLImpl implements I'+PrimeiraMaiscula(Edit1.Text)+'BCL ');
      Writeln(F,'{ ');
      Writeln(F,' ');
      Writeln(F,'/** The LOG. */  ');
      Writeln(F,'private static transient Log LOG = LogFactory.getLog('+PrimeiraMaiscula(Edit1.Text)+'BCLImpl.class)');
      Writeln(F,' ');
      Writeln(F,'/** The '+Edit1.Text+' dac. */ ');
      Writeln(F,'private I'+PrimeiraMaiscula(Edit1.Text)+'DAC '+Edit1.Text+'DAC // injected by Spring through setter ');
      Writeln(F,' ');
      Writeln(F,'/** The Constant PROCESS_BCL_BEAN. */ ');
      Writeln(F,'private static final String PROCESS_BCL_BEAN = "processBCLTarget"');
      Writeln(F,' ');
      Writeln(F,'/** The Constant SCHEDULE_BCL_BEAN. */ ');
      Writeln(F,'private static final String SCHEDULE_BCL_BEAN = "scheduleBCLTarget"');
      Writeln(F,' ');
      Writeln(F,'/** The Constant ACTION_PROVIDER_TYPE. */ ');
      Writeln(F,'private static final String ACTION_PROVIDER_TYPE = "EPM.TASK"');
      Writeln(F,' ');
      Writeln(F,'/** The Constant ADD_PROPERTY_TO_ACTION_FAILED. */ ');
      Writeln(F,'private static final String ADD_PROPERTY_TO_ACTION_FAILED =  ');
      Writeln(F,'"sensus.epm.actionbclimpl.add.property.to.action.failed"');
      Writeln(F,' ');
      Writeln(F,'/** The Constant UPDATE_PROPERTY_TO_ACTION_FAILED. */  ');
      Writeln(F,'private static final String UPDATE_PROPERTY_TO_ACTION_FAILED = ');
      Writeln(F,'"sensus.epm.actionbclimpl.update.property.to.action.failed"');
      Writeln(F,' ');
      Writeln(F,'/** The Constant DELETE_PROPERTY_FROM_ACTION_FAILED. */   ');
      Writeln(F,'private static final String DELETE_PROPERTY_FROM_ACTION_FAILED = ');
      Writeln(F,'"sensus.epm.actionbclimpl.delete.property.from.action.failed"');
      Writeln(F,' ');
      Writeln(F,'/** The Constant PROPERTY_DAC_BEAN. */ ');
      Writeln(F,'private static final String PROPERTY_DAC_BEAN = "propertyDACTarget"');
      Writeln(F,' ');
      Writeln(F,'/** The Constant GET_ACTION_TO_CLONE_FAILED. */     ');
      Writeln(F,'private static final String GET_ACTION_TO_CLONE_FAILED = "sensus.epm.actionbclimpl.get.action.to.clone.failed"');
      Writeln(F,' ');
      Writeln(F,'/** The Constant APPLY_ACTION_FAILED. */   ');
      Writeln(F,'private static final String APPLY_ACTION_FAILED =  ');
      Writeln(F,'"sensus.epm.actionbclimpl.apply.action.failed"');
      Writeln(F,' ');
      Writeln(F,'/** The Constant DATE_RAND_DIVISION. */  ');
      Writeln(F,'private static final Integer DATE_RAND_DIVISION = 0xFFFFF');
      Writeln(F,' ');
      Writeln(F,'/**  ');
      Writeln(F,'* Gets the process bcl. ');
      Writeln(F,'*   ');
      Writeln(F,'* @return the process bcl ');
      Writeln(F,'*/');
      Writeln(F,'public IProcessBCL getProcessBCL() ');
      Writeln(F,'{  ');
      Writeln(F,'return (IProcessBCL)SensusAppContext.getApplicationContext().getBean(PROCESS_BCL_BEAN)');
      Writeln(F,'} ');
      Writeln(F,' ');
      Writeln(F,'/**');
      Writeln(F,'* Gets the schedule bcl. ');
      Writeln(F,'* ');
      Writeln(F,'* @return the schedule bcl ');
      Writeln(F,'*/');
      Writeln(F,'public IScheduleBCL getScheduleBCL()');
      Writeln(F,'{  ');
      Writeln(F,'return (IScheduleBCL)SensusAppContext.getApplicationContext().getBean(SCHEDULE_BCL_BEAN)');
      Writeln(F,'} ');
      Writeln(F,' ');
      Writeln(F,'@Override ');
      Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> insert'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request)  ');
      Writeln(F,'{  ');
      Writeln(F,'InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> response = get'+PrimeiraMaiscula(Edit1.Text)+'DAC().insert'+PrimeiraMaiscula(Edit1.Text)+'('+Edit1.Text+'Request)');
      Writeln(F,' ');
      Writeln(F,'if (!response.isInError())  ');
      Writeln(F,'{    ');
      Writeln(F,''+PrimeiraMaiscula(Edit1.Text)+' '+Edit1.Text+' = response.getFirstResult()');
      Writeln(F,''+Edit1.Text+'Request.set'+PrimeiraMaiscula(Edit1.Text)+'('+Edit1.Text+')');
      Writeln(F,' ');
      Writeln(F,'SearchParameter '+Edit1.Text+'Parameter =  ');
      Writeln(F,'new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf('+Edit1.Text+'.getCodemp()))');
      Writeln(F,''+Edit1.Text+'Request.getSearchLight().addSearchParameter('+Edit1.Text+'Parameter)');
      Writeln(F,'InternalResultsResponse<Process> processResponse =   ');
      Writeln(F,'insertProcess('+Edit1.Text+'Request, LCActionTypeEnum.INSERT_EMPRESA, null)');
      Writeln(F,''+Edit1.Text+'Request.getSearchLight().getSearchParameters().remove('+Edit1.Text+'Parameter)');
      Writeln(F,' ');
      Writeln(F,'response.setStatus(processResponse.getStatus())');
      Writeln(F,'response.addMessages(processResponse.getMessageInfoList())');
      Writeln(F,'}  ');
      Writeln(F,'return response');
      Writeln(F,' ');
      Writeln(F,'}  ');
      Writeln(F,' ');
      Writeln(F,'@Override ');
      Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> update'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request)');
      Writeln(F,'{ ');
      Writeln(F,'InternalResponse groupResponse = new InternalResponse()');
      Writeln(F,' ');
      Writeln(F,'InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> internalResultsResponse = get'+PrimeiraMaiscula(Edit1.Text)+'DAC().update'+PrimeiraMaiscula(Edit1.Text)+'('+Edit1.Text+'Request)');
      Writeln(F,' ');
      Writeln(F,'if (!ValidationUtil.isNull(groupResponse))  ');
      Writeln(F,'{  ');
      Writeln(F,'internalResultsResponse.addMessages(groupResponse.getMessageInfoList())');
      Writeln(F,'} ');
      Writeln(F,' ');
      Writeln(F,'return internalResultsResponse');
      Writeln(F,'} ');
      Writeln(F,' ');
      Writeln(F,'@Override  ');
      Writeln(F,'public InternalResponse delete'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request)  ');
      Writeln(F,'{ ');
      Writeln(F,' ');
      Writeln(F,'InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> tagResponse =  ');
      Writeln(F,'(InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'>)get'+PrimeiraMaiscula(Edit1.Text)+'DAC().delete'+PrimeiraMaiscula(Edit1.Text)+'('+Edit1.Text+'Request)');
      Writeln(F,'InternalResponse response = new InternalResponse()');
      Writeln(F,' ');
      Writeln(F,'if (tagResponse.isInError())  ');
      Writeln(F,'{   ');
      Writeln(F,'return response');
      Writeln(F,'}  ');
      Writeln(F,' ');
      Writeln(F,'response = get'+PrimeiraMaiscula(Edit1.Text)+'DAC().delete'+PrimeiraMaiscula(Edit1.Text)+'('+Edit1.Text+'Request)');
      Writeln(F,' ');
      Writeln(F,'if (tagResponse.isInError()) ');
      Writeln(F,'{      ');
      Writeln(F,'return response');
      Writeln(F,'}   ');
      Writeln(F,' ');
      Writeln(F,''+PrimeiraMaiscula(Edit1.Text)+' '+Edit1.Text+' = tagResponse.getFirstResult()');
      Writeln(F,''+Edit1.Text+'Request.set'+PrimeiraMaiscula(Edit1.Text)+'('+Edit1.Text+')');
      Writeln(F,' ');
      Writeln(F,'SearchParameter tagParameter =  ');
      Writeln(F,'new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf('+Edit1.Text+'.getCodemp()))');
      Writeln(F,''+Edit1.Text+'Request.getSearchLight().addSearchParameter(tagParameter)');
      Writeln(F,' ');
      Writeln(F,'InternalResultsResponse<Process> processResponse = ');
      Writeln(F,'insertProcess('+Edit1.Text+'Request, LCActionTypeEnum.DELETE_TAG, null)');
      Writeln(F,''+Edit1.Text+'Request.getSearchLight().getSearchParameters().remove(tagParameter)');
      Writeln(F,' ');
      Writeln(F,'if (processResponse.isInError())  ');
      Writeln(F,'{       ');
      Writeln(F,'response.setStatus(processResponse.getStatus())');
      Writeln(F,'response.addMessages(processResponse.getMessageInfoList())');
      Writeln(F,'}');
      Writeln(F,' ');
      Writeln(F,'return response');
      Writeln(F,'} ');
      Writeln(F,' ');
      Writeln(F,'@Override ');
      Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> fetchAll'+PrimeiraMaiscula(Edit1.Text)+'(Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request inquiry'+Edit1.Text+'Request) ');
      Writeln(F,'{   ');
      Writeln(F,'return get'+PrimeiraMaiscula(Edit1.Text)+'DAC().fetchAll'+PrimeiraMaiscula(Edit1.Text)+'(inquiry'+Edit1.Text+'Request)');
      Writeln(F,'}  ');
      Writeln(F,' ');
      Writeln(F,'@Override  ');
      Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> fetch'+PrimeiraMaiscula(Edit1.Text)+'ById('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request) ');
      Writeln(F,'{   ');
      Writeln(F,'return get'+PrimeiraMaiscula(Edit1.Text)+'DAC().fetch'+PrimeiraMaiscula(Edit1.Text)+'ById('+Edit1.Text+'Request)');
      Writeln(F,'} ');
      Writeln(F,' ');
      Writeln(F,'public I'+PrimeiraMaiscula(Edit1.Text)+'DAC get'+PrimeiraMaiscula(Edit1.Text)+'DAC() ');
      Writeln(F,'{');
      Writeln(F,'return '+Edit1.Text+'DAC');
      Writeln(F,'} ');
      Writeln(F,' ');
      Writeln(F,'public void set'+PrimeiraMaiscula(Edit1.Text)+'DAC(I'+PrimeiraMaiscula(Edit1.Text)+'DAC '+Edit1.Text+'DAC) ');
      Writeln(F,'{ ');
      Writeln(F,'this.'+Edit1.Text+'DAC = '+Edit1.Text+'DAC');
      Writeln(F,'}  ');
      Writeln(F,' ');
      Writeln(F,'@Override  ');
      Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> fetchAll'+PrimeiraMaiscula(Edit1.Text)+'Types(Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request inquiry'+Edit1.Text+'Request)  ');
      Writeln(F,'{ ');
      Writeln(F,'// TODO Auto-generated method stub  ');
      Writeln(F,'return null');
      Writeln(F,'}         ');
      Writeln(F,' ');
      Writeln(F,'private InternalResultsResponse<Process> insertProcess('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request,  ');
      Writeln(F,'LCActionTypeEnum lcActionType, ');
      Writeln(F,'String processDescription)   ');
      Writeln(F,'{ ');
      Writeln(F,'return insertProcess('+Edit1.Text+'Request, lcActionType, processDescription, null)');
      Writeln(F,'} ');
      Writeln(F,' ');
      Writeln(F,'/**   ');
      Writeln(F,'* Insert process. ');
      Writeln(F,'*   ');
      Writeln(F,'* @param tagRequest the tag request ');
      Writeln(F,'* @param lcActionType the lc action type   ');
      Writeln(F,'* @param processDescription the process description   ');
      Writeln(F,'* @return the internal results response  ');
      Writeln(F,'*/         ');
      Writeln(F,'private InternalResultsResponse<Process> insertProcess('+PrimeiraMaiscula(Edit1.Text)+'Request tagRequest, LCActionTypeEnum lcActionType,  ');
      Writeln(F,'String processDescription, List<Light> deactivatedLights) ');
      Writeln(F,'{');
      Writeln(F,''+PrimeiraMaiscula(Edit1.Text)+' '+Edit1.Text+' = tagRequest.get'+PrimeiraMaiscula(Edit1.Text)+'()');
      Writeln(F,' ');
      Writeln(F,'List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>()');
      Writeln(F,'actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf('+Edit1.Text+'.getCodemp())))');
      Writeln(F,'actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, '+Edit1.Text+'.getCnpjemp()))');
      Writeln(F,' ');
      Writeln(F,'LCAction action = new LCAction(lcActionType)');
      Writeln(F,'action.setActionParameters(actionParameters)');
      Writeln(F,'action.setDescription("INSERT EMPRESA")');
      Writeln(F,'Process process = LCHelp.generateProcess(false, action, 0)');
      Writeln(F,'process.setIsProcessComplete(true)');
      Writeln(F,'process.setIsSubmitted(true)');
      Writeln(F,'process.setDescription("insert '+PrimeiraMaiscula(Edit1.Text)+'")');
      Writeln(F,'process.setRniCorrelationId("")');
      Writeln(F,' ');
      Writeln(F,' ');
      Writeln(F,'process.setEndTime(LCDateUtil.getNewDateUTC())');
      Writeln(F,' ');
      Writeln(F,'if (!ValidationUtil.isNullOrEmpty(processDescription))  ');
      Writeln(F,'{ ');
      Writeln(F,'process.setDescription(processDescription)');
      Writeln(F,'} ');
      Writeln(F,' ');
      Writeln(F,'ProcessRequest processRequest = createProcessRequest(tagRequest, process)');
      Writeln(F,'processRequest.setProcessItemFailureList( ');
      Writeln(F,'createProcessItemWithFailure(  ');
      Writeln(F,'	deactivatedLights,     ');
      Writeln(F,'	ProcessItemStatusEnum.MLCFAILURE,  ');
      Writeln(F,'	ProcessStatusReasonEnum.LIGHT_DEACTIVATED))');
      Writeln(F,' ');
      Writeln(F,'return getProcessBCL().insertProcess(processRequest)');
      Writeln(F,'} ');
      Writeln(F,' ');
      Writeln(F,'@Override  ');
      Writeln(F,'public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> fetchAll'+PrimeiraMaiscula(Edit1.Text)+'Filial('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request) ');
      Writeln(F,'{  ');
      Writeln(F,'// TODO Auto-generated method stub  ');
      Writeln(F,'return null');
      Writeln(F,'} ');
      Writeln(F,'}  ');
      Writeln(F,' ');
      Closefile(F);
end;


function TForm1.criarCodeIClasseBCL(Txt:String):String;
begin
      AssignFile(F,'c:\'+PrimeiraMaiscula(Edit1.Text)+'BCL.java');
      Rewrite(F); //abre o arquivo para escrita
      Writeln(F,'package com.sensus.mlc.'+Edit1.Text+'.bcl;');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'import com.sensus.common.model.response.InternalResponse; ');
      Writeln(F,'import com.sensus.common.model.response.InternalResultsResponse;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.'+PrimeiraMaiscula(Edit1.Text)+'; ');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.'+PrimeiraMaiscula(Edit1.Text)+'Request; ');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request; ');
      Writeln(F,' ');
      Writeln(F,'// TODO: Auto-generated Javadoc ');
      Writeln(F,'/**');
      Writeln(F,' * The Interface IActionBCL.');
      Writeln(F,' * ');
      Writeln(F,' * @author - QAT Brazil. ');
      Writeln(F,' * ');
      Writeln(F,' */ ');
      Writeln(F,'public interface I'+PrimeiraMaiscula(Edit1.Text)+'BCL ');
      Writeln(F,'{   ');
      Writeln(F,'    ');
      Writeln(F,'	/**  ');
      Writeln(F,'	 * Insert '+Edit1.Text+'. ');
      Writeln(F,'	 * ');
      Writeln(F,'	 * @param '+Edit1.Text+'Request the '+Edit1.Text+' request  ');
      Writeln(F,'	 * @return the '+Edit1.Text+' response  ');
      Writeln(F,'	 */ ');
      Writeln(F,'	public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> insert'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request);');
      Writeln(F,'  ');
      Writeln(F,'	/** ');
      Writeln(F,'	 * Update '+Edit1.Text+'. ');
      Writeln(F,'	 *  ');
      Writeln(F,'	 * @param '+Edit1.Text+'Request the '+Edit1.Text+' request ');
      Writeln(F,'	 * @return the '+Edit1.Text+' response ');
      Writeln(F,'	 */  ');
      Writeln(F,'	public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> update'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request); ');
      Writeln(F,'   ');
      Writeln(F,'	/** ');
      Writeln(F,'	 * Delete '+Edit1.Text+'. ');
      Writeln(F,'	 *   ');
      Writeln(F,'	 * @param '+Edit1.Text+'Request the '+Edit1.Text+' request ');
      Writeln(F,'	 * @return the '+Edit1.Text+' response  ');
      Writeln(F,'	 */ ');
      Writeln(F,'	public InternalResponse delete'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request); ');
      Writeln(F,'  ');
      Writeln(F,'	/** ');
      Writeln(F,'	 * Fetch all '+Edit1.Text+'.');
      Writeln(F,'	 *   ');
      Writeln(F,'	 * @param inquiry'+Edit1.Text+'Request the inquiry'+Edit1.Text+' request ');
      Writeln(F,'	 * @return the inquiry '+Edit1.Text+' response ');
      Writeln(F,'	 */  ');
      Writeln(F,'	public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> fetchAll'+PrimeiraMaiscula(Edit1.Text)+'(Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request inquiry'+Edit1.Text+'Request);');
      Writeln(F,'  ');
      Writeln(F,'	/** ');
      Writeln(F,'	 * Fetch '+Edit1.Text+' by id. ');
      Writeln(F,'	 * ');
      Writeln(F,'	 * @param inquiry'+Edit1.Text+'Request the inquiry'+Edit1.Text+' request');
      Writeln(F,'	 * @return the internal results response   ');
      Writeln(F,'	 */ ');
      Writeln(F,'	public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> fetch'+PrimeiraMaiscula(Edit1.Text)+'ById('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request); ');
      Writeln(F,' ');
      Writeln(F,'	/** ');
      Writeln(F,'	 * Fetch all '+Edit1.Text+' types. ');
      Writeln(F,'	 * ');
      Writeln(F,'	 * @param request the request ');
      Writeln(F,'	 * @return the '+Edit1.Text+' response ');
      Writeln(F,'	 */ ');
      Writeln(F,'	public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> fetchAll'+PrimeiraMaiscula(Edit1.Text)+'Types(Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request inquiry'+Edit1.Text+'Request);  ');
      Writeln(F,'  ');
      Writeln(F,'	/** ');
      Writeln(F,'	 * Fetch all '+Edit1.Text+' filial. ');
      Writeln(F,'	 *  ');
      Writeln(F,'	 * @param request the request ');
      Writeln(F,'	 * @return the '+Edit1.Text+' response ');
      Writeln(F,'	 */ ');
      Writeln(F,'	public InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> fetchAll'+PrimeiraMaiscula(Edit1.Text)+'Filial('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request);');
      Writeln(F,' ');
      Writeln(F,'} ');
      Closefile(F);
end;
function TForm1.criarCodeClasseBCFImpl(Txt:String):String;
begin
      AssignFile(F,'c:\I'+PrimeiraMaiscula(Edit1.Text)+'BCF.java');
      Rewrite(F); //abre o arquivo para escrita
      Writeln(F,'');
      Writeln(F,'package com.sensus.mlc.'+Edit1.Text+'.bcf.impl;');
      Writeln(F,'');
      Writeln(F,'import org.slf4j.Logger;');
      Writeln(F,'import org.slf4j.LoggerFactory;');
      Writeln(F,'');
      Writeln(F,'import com.sensus.common.model.request.Request;');
      Writeln(F,'import com.sensus.common.model.response.InternalResponse;');
      Writeln(F,'import com.sensus.common.model.response.InternalResultsResponse;');
      Writeln(F,'import com.sensus.common.util.SensusInterfaceUtil;');
      Writeln(F,'import com.sensus.mlc.base.model.MLCPersistanceActionEnum;');
      Writeln(F,'import com.sensus.mlc.base.util.LCHelp;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.bcf.I'+PrimeiraMaiscula(Edit1.Text)+'BCF;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.bcl.I'+PrimeiraMaiscula(Edit1.Text)+'BCL;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.'+PrimeiraMaiscula(Edit1.Text)+';');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.'+PrimeiraMaiscula(Edit1.Text)+'Request;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.response.'+PrimeiraMaiscula(Edit1.Text)+'Response;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.response.Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response;');
      Writeln(F,'import com.sensus.mlc.process.model.response.ProcessResponse;');
      Writeln(F,'import com.sensus.mlc.tag.bcf.impl.'+Edit1.Text+'BCFImpl;');
      Writeln(F,'');
      Writeln(F,'public class '+PrimeiraMaiscula(Edit1.Text)+'BCFImpl implements I'+PrimeiraMaiscula(Edit1.Text)+'BCF');
      Writeln(F,'{');
      Writeln(F,'');
      Writeln(F,'	/** The Constant NAME_LENGTH. */');
      Writeln(F,'	private static final Integer NAME_LENGTH = 255;');
      Writeln(F,' ');
      Writeln(F,'	/** The Constant SENSUS_EPM_ACTIONVALIDATOR_NAME_INVALID. */ ');
      Writeln(F,'	private static final String SENSUS_EPM_ACTIONVALIDATOR_NAME_INVALID = ');
      Writeln(F,'			"sensus.epm.actionvalidator.name.invalid"; ');
      Writeln(F,'');
      Writeln(F,'	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */ ');
      Writeln(F,'	private static final String DEFAULT_EMPRESA_BCF_EXCEPTION_MSG = "sensus.mlc.groupbcfimpl.defaultexception"; ');
      Writeln(F,' ');
      Writeln(F,'	/** The Constant DEFAULT_GROUP_BCL_EXCEPTION_MSG. */ ');
      Writeln(F,'	private static final String DEFAULT_EMPRESA_BCL_EXCEPTION_MSG = "sensus.mlc.groupbclimpl.defaultexception";');
      Writeln(F,' ');
      Writeln(F,'	/** The Constant LOG. */');
      Writeln(F,'	private static final Logger LOG = LoggerFactory.getLogger('+Edit1.Text+'BCFImpl.class); ');
      Writeln(F,' ');
      Writeln(F,'	private I'+PrimeiraMaiscula(Edit1.Text)+'BCL '+Edit1.Text+'BCL; // injected by Spring through setter');
      Writeln(F,' ');
      Writeln(F,'	@Override ');
      Writeln(F,'	public '+PrimeiraMaiscula(Edit1.Text)+'Response insert'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request)');
      Writeln(F,'	{');
      Writeln(F,'		'+PrimeiraMaiscula(Edit1.Text)+'Response response = new '+PrimeiraMaiscula(Edit1.Text)+'Response();');
      Writeln(F,' ');
      Writeln(F,'		try ');
      Writeln(F,'		{ ');
      Writeln(F,'     ');
      Writeln(F,'			if (LCHelp.checkValidation(response, '+Edit1.Text+'Request, new Object[] {MLCPersistanceActionEnum.INSERT}))');
      Writeln(F,'			{');
      Writeln(F,'				InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> internalResponse = get'+PrimeiraMaiscula(Edit1.Text)+'BCL().insert'+PrimeiraMaiscula(Edit1.Text)+'('+Edit1.Text+'Request); ');
      Writeln(F,'				response.set'+PrimeiraMaiscula(Edit1.Text)+'(internalResponse.getResultsList()); ');
      Writeln(F,'				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); ');
      Writeln(F,'			}');
      Writeln(F,'		} ');
      Writeln(F,'		catch (Exception ex) ');
      Writeln(F,'		{ ');
      Writeln(F,'			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);');
      Writeln(F,'		}');
      Writeln(F,'');
      Writeln(F,'		return response;');
      Writeln(F,'	}');
      Writeln(F,'');
      Writeln(F,'	@Override');
      Writeln(F,'	public '+PrimeiraMaiscula(Edit1.Text)+'Response update'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request)');
      Writeln(F,'	{');
      Writeln(F,'		'+PrimeiraMaiscula(Edit1.Text)+'Response response = new '+PrimeiraMaiscula(Edit1.Text)+'Response();');
      Writeln(F,'		try ');
      Writeln(F,'		{ ');
      Writeln(F,'			if (LCHelp.checkValidation(response, '+Edit1.Text+'Request, MLCPersistanceActionEnum.UPDATE)) ');
      Writeln(F,'			{  ');
      Writeln(F,'				InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> internalResponse = get'+PrimeiraMaiscula(Edit1.Text)+'BCL().update'+PrimeiraMaiscula(Edit1.Text)+'('+Edit1.Text+'Request);');
      Writeln(F,'				response.set'+PrimeiraMaiscula(Edit1.Text)+'(internalResponse.getResultsList());');
      Writeln(F,'				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCL_EXCEPTION_MSG);');
      Writeln(F,'			}');
      Writeln(F,'		} ');
      Writeln(F,'		catch (Exception ex) ');
      Writeln(F,'		{ ');
      Writeln(F,'			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);');
      Writeln(F,'		} ');
      Writeln(F,'		return response;  ');
      Writeln(F,'	} ');
      Writeln(F,'   ');
      Writeln(F,'	@Override  ');
      Writeln(F,'	public '+PrimeiraMaiscula(Edit1.Text)+'Response delete'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request) ');
      Writeln(F,'	{  ');
      Writeln(F,'		'+PrimeiraMaiscula(Edit1.Text)+'Response response = new '+PrimeiraMaiscula(Edit1.Text)+'Response();');
      Writeln(F,'		try  ');
      Writeln(F,'		{  ');
      Writeln(F,'			if (LCHelp.checkValidation(response, '+Edit1.Text+'Request, MLCPersistanceActionEnum.DELETE)) ');
      Writeln(F,'			{  ');
      Writeln(F,'				InternalResponse internalResponse = get'+PrimeiraMaiscula(Edit1.Text)+'BCL().delete'+PrimeiraMaiscula(Edit1.Text)+'('+Edit1.Text+'Request); ');
      Writeln(F,'				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); ');
      Writeln(F,'			}  ');
      Writeln(F,'		}  ');
      Writeln(F,'		catch (Exception ex)');
      Writeln(F,'		{ ');
      Writeln(F,'			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); ');
      Writeln(F,'		} ');
      Writeln(F,'		return response; ');
      Writeln(F,'	} ');
      Writeln(F,'  ');
      Writeln(F,'	@Override ');
      Writeln(F,'	public Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response fetchAll'+PrimeiraMaiscula(Edit1.Text)+'(Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request inquiry'+Edit1.Text+'Request) ');
      Writeln(F,'	{ ');
      Writeln(F,'		Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response response = new Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response(); ');
      Writeln(F,'		try ');
      Writeln(F,'		{ ');
      Writeln(F,'    ');
      Writeln(F,'				InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> internalResponse =  ');
      Writeln(F,'						get'+PrimeiraMaiscula(Edit1.Text)+'BCL().fetchAll'+PrimeiraMaiscula(Edit1.Text)+'(inquiry'+Edit1.Text+'Request); ');
      Writeln(F,'				response.set'+PrimeiraMaiscula(Edit1.Text)+'(internalResponse.getResultsList());');
      Writeln(F,'				response.setResultsSetInfo(internalResponse.getResultsSetInfo());');
      Writeln(F,'				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); ');
      Writeln(F,'   ');
      Writeln(F,'		}');
      Writeln(F,'		catch (Exception ex)');
      Writeln(F,'		{ ');
      Writeln(F,'			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); ');
      Writeln(F,'		}  ');
      Writeln(F,'		return response;');
      Writeln(F,'	} ');
      Writeln(F,'   ');
      Writeln(F,'	@Override  ');
      Writeln(F,'	public '+PrimeiraMaiscula(Edit1.Text)+'Response fetch'+PrimeiraMaiscula(Edit1.Text)+'ById('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request) ');
      Writeln(F,'	{ ');
      Writeln(F,'		'+PrimeiraMaiscula(Edit1.Text)+'Response response = new '+PrimeiraMaiscula(Edit1.Text)+'Response(); ');
      Writeln(F,'		try ');
      Writeln(F,'		{  ');
      Writeln(F,'			if (LCHelp.checkValidation(response, '+Edit1.Text+'Request, MLCPersistanceActionEnum.FETCH_BY_ID)) ');
      Writeln(F,'			{ ');
      Writeln(F,'				InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> internalResponse = get'+PrimeiraMaiscula(Edit1.Text)+'BCL().fetch'+PrimeiraMaiscula(Edit1.Text)+'ById('+Edit1.Text+'Request); ');
      Writeln(F,'				response.set'+PrimeiraMaiscula(Edit1.Text)+'(internalResponse.getResultsList());');
      Writeln(F,'				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); ');
      Writeln(F,'			} ');
      Writeln(F,'		}  ');
      Writeln(F,'		catch (Exception ex)  ');
      Writeln(F,'		{      ');
      Writeln(F,'			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);  ');
      Writeln(F,'		}');
      Writeln(F,'		return response;  ');
      Writeln(F,'	}');
      Writeln(F,'  ');
      Writeln(F,'	@Override ');
      Writeln(F,'	public '+PrimeiraMaiscula(Edit1.Text)+'Response fetchAll'+PrimeiraMaiscula(Edit1.Text)+'Filial('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request) { ');
      Writeln(F,'		'+PrimeiraMaiscula(Edit1.Text)+'Response response = new '+PrimeiraMaiscula(Edit1.Text)+'Response(); ');
      Writeln(F,'		try ');
      Writeln(F,'		{    ');
      Writeln(F,'			if (LCHelp.checkValidation(response, inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request, MLCPersistanceActionEnum.FETCH)) ');
      Writeln(F,'			{ ');
      Writeln(F,'				InternalResultsResponse<'+PrimeiraMaiscula(Edit1.Text)+'> internalResponse =  ');
      Writeln(F,'						get'+PrimeiraMaiscula(Edit1.Text)+'BCL().fetchAll'+PrimeiraMaiscula(Edit1.Text)+'Filial('+Edit1.Text+'Request);  ');
      Writeln(F,'				response.set'+PrimeiraMaiscula(Edit1.Text)+'(internalResponse.getResultsList());   ');
      Writeln(F,'				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); ');
      Writeln(F,'			}  ');
      Writeln(F,'		} ');
      Writeln(F,'		catch (Exception ex) ');
      Writeln(F,'		{  ');
      Writeln(F,'			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); ');
      Writeln(F,'		} ');
      Writeln(F,'		return response; ');
      Writeln(F,'	} ');
      Writeln(F,'   ');
      Writeln(F,'   ');
      Writeln(F,'	public I'+PrimeiraMaiscula(Edit1.Text)+'BCL get'+PrimeiraMaiscula(Edit1.Text)+'BCL() ');
      Writeln(F,'	{       ');
      Writeln(F,'		return '+Edit1.Text+'BCL; ');
      Writeln(F,'	}  ');
      Writeln(F,'    ');
      Writeln(F,'	public void set'+PrimeiraMaiscula(Edit1.Text)+'BCL(I'+PrimeiraMaiscula(Edit1.Text)+'BCL '+Edit1.Text+'BCL) ');
      Writeln(F,'	{ ');
      Writeln(F,'		this.'+Edit1.Text+'BCL = '+Edit1.Text+'BCL; ');
      Writeln(F,'	} ');
      Writeln(F,'}  ');
      Closefile(F);
end;

function TForm1.criarCodeIClasseBCF(Txt:String):String;
begin
      AssignFile(F,'c:\I'+PrimeiraMaiscula(Edit1.Text)+'BCF.java');
      Rewrite(F); //abre o arquivo para escrita
      Writeln(F,'package com.sensus.mlc.'+Edit1.Text+'.bcf;');
      Writeln(F,'');
      Writeln(F,'import com.sensus.common.model.request.Request;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.'+PrimeiraMaiscula(Edit1.Text)+'Request;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.request.Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.response.'+PrimeiraMaiscula(Edit1.Text)+'Response;');
      Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.response.Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response;');
      Writeln(F,'import com.sensus.mlc.process.model.response.ProcessResponse;');
      Writeln(F,'');
      Writeln(F,'');
      Writeln(F,'/** ');
      Writeln(F,' * The Interface I'+PrimeiraMaiscula(Edit1.Text)+'BCF.');
      Writeln(F,' *');
      Writeln(F,' * @author Washington.Costa');
      Writeln(F,' */');
      Writeln(F,'public interface I'+PrimeiraMaiscula(Edit1.Text)+'BCF ');
      Writeln(F,'{');
      Writeln(F,'');
      Writeln(F,'	/** ');
      Writeln(F,'	 * Insert '+Edit1.Text+'.');
      Writeln(F,'	 *');
      Writeln(F,'	 * @param '+Edit1.Text+'Request the '+Edit1.Text+' request');
      Writeln(F,'	 * @return the '+Edit1.Text+' response ');
      Writeln(F,'	 */');
      Writeln(F,'	public '+PrimeiraMaiscula(Edit1.Text)+'Response insert'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request);');
      Writeln(F,'  ');
      Writeln(F,'	/**');
      Writeln(F,'	 * Update '+Edit1.Text+'.');
      Writeln(F,'	 *');
      Writeln(F,'	 * @param '+Edit1.Text+'Request the '+Edit1.Text+' request');
      Writeln(F,'	 * @return the '+Edit1.Text+' response');
      Writeln(F,'	 */');
      Writeln(F,'	public '+PrimeiraMaiscula(Edit1.Text)+'Response update'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request);');
      Writeln(F,'  ');
      Writeln(F,'	/**  ');
      Writeln(F,'	 * Delete '+Edit1.Text+'.  ');
      Writeln(F,'	 *     ');
      Writeln(F,'	 * @param '+Edit1.Text+'Request the '+Edit1.Text+' request ');
      Writeln(F,'	 * @return the '+Edit1.Text+' response');
      Writeln(F,'	 */');
      Writeln(F,'	public '+PrimeiraMaiscula(Edit1.Text)+'Response delete'+PrimeiraMaiscula(Edit1.Text)+'('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request); ');
      Writeln(F,'   ');
      Writeln(F,'	/**  ');
      Writeln(F,'	 * Fetch all '+Edit1.Text+'. ');
      Writeln(F,'	 *  ');
      Writeln(F,'	 * @param inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request the inquiry'+PrimeiraMaiscula(Edit1.Text)+' request   ');
      Writeln(F,'	 * @return the inquiry '+Edit1.Text+' response ');
      Writeln(F,'	 */ ');
      Writeln(F,'	public Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response fetchAll'+PrimeiraMaiscula(Edit1.Text)+'(Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request); ');
      Writeln(F,'   ');
      Writeln(F,'	/** ');
      Writeln(F,'	 * Fetch '+Edit1.Text+' by id. ');
      Writeln(F,'	 *   ');
      Writeln(F,'	 * @param '+PrimeiraMaiscula(Edit1.Text)+'Request the '+Edit1.Text+' request ');
      Writeln(F,'	 * @return the '+Edit1.Text+' response  ');
      Writeln(F,'	 */ ');
      Writeln(F,'	public '+PrimeiraMaiscula(Edit1.Text)+'Response fetch'+PrimeiraMaiscula(Edit1.Text)+'ById('+PrimeiraMaiscula(Edit1.Text)+'Request '+Edit1.Text+'Request); ');
      Writeln(F,' ');
      Writeln(F,'} ');
      Closefile(F);
end;
function TForm1.criarCodeResponse(Txt:String):String;
begin
       AssignFile(F,'c:\'+PrimeiraMaiscula(Edit1.Text)+'response.java');
       Rewrite(F); //abre o arquivo para escrita
       Writeln(F,'package com.sensus.mlc.'+Edit1.Text+'.model.response;');
       Writeln(F,'import java.util.List;');
       Writeln(F,'import com.sensus.common.model.response.Response;');
       Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.'+PrimeiraMaiscula(Edit1.Text)+';');
       Writeln(F,'');
       Writeln(F,'');
       Writeln(F,'public class '+PrimeiraMaiscula(Edit1.Text)+'Response extends Response');
       Writeln(F,'');
       Writeln(F,'    ');
       Writeln(F,'    private Integer parentRetry;');
       Writeln(F,'    ');
       Writeln(F,'    private List<'+PrimeiraMaiscula(Edit1.Text)+'> '+LowerCase(Edit1.Text)+'s ;');
       Writeln(F,'}');
       Closefile(F);
end;
function TForm1.criarCodeRequest(Txt:String):String;
begin
       AssignFile(F,'c:\'+PrimeiraMaiscula(Edit1.Text)+'request.java');
       Rewrite(F); //abre o arquivo para escrita
       Writeln(F,'package com.sensus.mlc.'+Edit1.Text+'.model.request;');
       Writeln(F,'import java.util.List;');
       Writeln(F,'import com.sensus.mlc.base.model.request.LightSelectionRequest;');
       Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.'+PrimeiraMaiscula(Edit1.Text)+';');
       Writeln(F,'');
       Writeln(F,'');
       Writeln(F,'public class '+PrimeiraMaiscula(Edit1.Text)+'Request extends LightSelectionRequest');
       Writeln(F,'');
       Writeln(F,'    ');
       Writeln(F,'    private Integer parentRetry;');
       Writeln(F,'    ');
       Writeln(F,'    private '+PrimeiraMaiscula(Edit1.Text)+'  '+LowerCase(Edit1.Text)+';');
       Writeln(F,'}');
       Closefile(F);
end;

Function TForm1.MostrarMemo(Memo: TMemo) : String;
 var   linha: string;
begin
      Memo.Lines.Clear;
      Reset(F);   // [ 3 ] Abre o arquivo texto para leitura
      {$I+}         // ativa a diretiva de Input

      if (IOResult <> 0) then
          Memo.Lines.Add('Erro na abertura do arquivo !!!')
      else begin
         while (not eof(F)) do
         begin
           readln(F, linha); // [ 6 ] Lê uma linha do arquivo
           Memo.Lines.Add(linha);
         end;

         CloseFile(F); // [ 8 ] Fecha o arquivo texto aberto
       end;
end;

Function TForm1.PrimeiraMaiscula(Texto: String) : String;
begin
      Texto := lowerCase(Texto);
      If Length(Trim(Texto))>0 Then//verifica se o edit está vazio ou somente com "espaços" em branco
      Texto:= UPPERCASE(Copy(Texto, 1, 1))+ LOWERCASE(Copy(Texto, 2, Length(Texto)));
      Result := Texto;
end;
function TForm1.criarCodeInquiryRequest(Txt:String):String;
begin

       AssignFile(F,'c:\Inquiry'+PrimeiraMaiscula(Edit1.Text)+'request.java');
       Rewrite(F); //abre o arquivo para escrita
       Writeln(F,'package com.sensus.mlc.'+Edit1.Text+'.model.request;');
       Writeln(F,'import java.util.List;');
       Writeln(F,'import com.sensus.mlc.base.model.BaseSearch;');
       Writeln(F,'import com.sensus.mlc.base.model.request.InquiryPaginationRequest;');
       Writeln(F,'import com.sensus.mlc.'+Edit1.Text+'.model.'+PrimeiraMaiscula(Edit1.Text)+';');
       Writeln(F,'');
       Writeln(F,'');
       Writeln(F,'public class Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request extends InquiryPaginationRequest');
       Writeln(F,'');
       Writeln(F,'    private BaseSearch baseSearch;');
       Writeln(F,'    ');
       Writeln(F,'    private String fileName;');
       Writeln(F,'    ');
       Writeln(F,'    Integer processId;');
       Writeln(F,'    ');
       Writeln(F,'    private List<'+PrimeiraMaiscula(Edit1.Text)+'> '+LowerCase(Edit1.Text)+';');
       Writeln(F,'}');
       Closefile(F);
end;



function TForm1.criarCodeInquiryResponse(Txt:String):String;
begin
       AssignFile(F,'c:\Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response.java');
       Rewrite(F); //abre o arquivo para escrita
       Writeln(F,'import java.util.List;');
       Writeln(F,'import com.sensus.common.model.response.InquiryResponse');
       Writeln(F,'');
       Writeln(F,'');
       Writeln(F,'public class Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response extends InquiryResponse');
       Writeln(F,'');
       Writeln(F,'    private List<'+PrimeiraMaiscula(Edit1.Text)+'> '+lowerCase(Edit1.Text)+' ;');
       Writeln(F,'');
       Writeln(F,'');
	     Writeln(F,'    public List<'+PrimeiraMaiscula(Edit1.Text)+'> get'+PrimeiraMaiscula(Edit1.Text)+'() {');
       Writeln(F,'      return '+Edit1.Text+';');
       Writeln(F,'    }');
       Writeln(F,'');
       Writeln(F,'');
       Writeln(F,'    public void set'+PrimeiraMaiscula(Edit1.Text)+'(List<'+PrimeiraMaiscula(Edit1.Text)+'> '+Edit1.Text+') {');
       Writeln(F,'        this.'+Edit1.Text+' = '+Edit1.Text+';');
       Writeln(F,     '}');
       Writeln(F,'');
       Writeln(F,'');
       Writeln(F,'');
       Writeln(F,'    public String toString() {');
//       Writeln(F,'                                                                                                                                                       ');
       Writeln(F,'    return "Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response ['+Edit1.Text+'=" + '+Edit1.Text);
       Writeln(F,'      + ", get'+PrimeiraMaiscula(Edit1.Text)+'()=" + get'+PrimeiraMaiscula(Edit1.Text)+'()');
       Writeln(F,'    + "]";');
       Writeln(F,'');
       Writeln(F,'');
       Writeln(F,'}');
       Closefile(F);
end;

function TForm1.verificadorCodeAppBanco(Txt:String):String;
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



function TForm1.verificadorCode(Txt:String):String;
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
Function TForm1.Explode(Texto, Separador : String) : String;
var
    strItem       : String;
    ListaAuxUTILS : TStrings;
    NumCaracteres,
    TamanhoSeparador,
    I : Integer;
Begin
    ListaAuxUTILS    := TStringList.Create;
    strItem          := '';
    NumCaracteres    := Length(Texto);
    TamanhoSeparador := Length(Separador);
    I                := 1;
    While I <= NumCaracteres Do
      Begin
        If (Copy(Texto,I,TamanhoSeparador) = Separador) or (I = NumCaracteres) Then
          Begin
            if (I = NumCaracteres) then strItem := strItem + Texto[I];
            ListaAuxUTILS.Add(trim(strItem));
            strItem := '';
            I := I + (TamanhoSeparador-1);
          end
        Else
            strItem := strItem + Texto[I];

        I := I + 1;
      End;
    Explode := ListaAuxUTILS.Text;
end;

procedure TForm1.FormCreate(Sender: TObject);
begin
      TabSheet1.TabVisible := false;
      TabSheet2.TabVisible := false;
      TabSheet3.TabVisible := false;
      TabSheet4.TabVisible := false;
      TabSheet5.TabVisible := false;
      TabSheet6.TabVisible := false;
      TabSheet7.TabVisible := false;
      TabSheet8.TabVisible := false;
      TabSheet9.TabVisible := false;
      TabSheet10.TabVisible := false;
      TabSheet11.TabVisible := false;
      TabSheet12.TabVisible := false;
      TabSheet13.TabVisible := false;
      TabSheet14.TabVisible := false;
      TabSheet15.TabVisible := false;
      TbsConsulta.TabVisible := false;
      LerConfiguracao;
end;

function TForm1.escreverCodeXML(Txt:String;Op:Integer):String;
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

function TForm1.escreverCode(Txt:String):String;
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
procedure TForm1.gerarModelo(Txt:String);
var linha: string;
BEGIN
     AssignFile(F,'c:\'+Edit1.Text+'.java');
     Rewrite(F); //abre o arquivo para escrita
     Writeln(F,'public class '+Txt+' extends SensusModel'); //escreve no arquivo e desce uma linha
     Writeln(F,'{');
     AssignFile(arq, EdtDsArquiv.text);

     Reset(arq);   // [ 3 ] Abre o arquivo texto para leitura
     {$I+}
     while (not eof(arq)) do
     begin
           readln(arq, linha); // [ 6 ] Lê uma linha do arquivo
           Writeln(F,'    private '+escreverCode(linha));
      end;

      CloseFile(arq); // [ 8 ] Fecha o arquivo texto aberto


      Writeln(F,'}');
      Closefile(F); //fecha o handle de arquivo

END;

procedure TForm1.BtnPesquisaClick(Sender: TObject);
begin
     GravarConfiguracao;
     if CheckBox1.Checked = true then
     begin
           criarCodeIClasseBCF(Edit1.Text);
           MostrarMemo(Memo3);
           TabSheet1.TabVisible := true;
           TabSheet1.Caption := 'IClasseBCF';
     end;
     if CheckBox2.Checked = true then
     begin
           criarCodeClasseBCFImpl(Edit1.Text);
           MostrarMemo(Memo4);
           TabSheet2.TabVisible := true;
           TabSheet2.Caption := ''+PrimeiraMaiscula(Edit1.Text)+'BCFImpl';
     end;
     if CheckBox3.Checked = true then
     begin
           criarCodeIClasseBCL(Edit1.Text);
           MostrarMemo(Memo5);
           TabSheet3.TabVisible := true;
           TabSheet3.Caption := 'I'+PrimeiraMaiscula(Edit1.Text)+'BCL';
     end;
     if CheckBox4.Checked = true then
     begin
           criarCodeIClasseBCLImpl(Edit1.Text);
           MostrarMemo(Memo6);
           TabSheet4.TabVisible := true;
           TabSheet4.Caption := ''+PrimeiraMaiscula(Edit1.Text)+'BCLImpl';
     end;
     if CheckBox5.Checked = true then
     begin
           criarCodeIClasseBCL(Edit1.Text);
           MostrarMemo(Memo7);
           TabSheet5.TabVisible := true;
           TabSheet5.Caption := 'I'+PrimeiraMaiscula(Edit1.Text)+'BCL';
     end;
     if CheckBox6.Checked = true then
     begin
           criarCodeIClasseDAC(Edit1.Text);
           MostrarMemo(Memo8);
           TabSheet6.TabVisible := true;
           TabSheet6.Caption := 'I'+PrimeiraMaiscula(Edit1.Text)+'DAC';
     end;
     if CheckBox7.Checked = true then
     begin
           criarCodeIClasseDACImpl(Edit1.Text);
           MostrarMemo(Memo9);
           TabSheet7.TabVisible := true;
           TabSheet7.Caption := ''+PrimeiraMaiscula(Edit1.Text)+'DACImpl';
     end;
     if CheckBox8.Checked = true then
     begin
           criarCodeClasseXML(Edit1.Text);
           MostrarMemo(Memo10);
           TabSheet8.TabVisible := true;
           TabSheet8.Caption := ''+PrimeiraMaiscula(Edit1.Text)+'.XML';
     end;
     if CheckBox27.Checked = true then
     begin
           criarCodeClasseSqlMapConfigXml(Edit1.Text);
           MostrarMemo(Memo11);
           TabSheet9.TabVisible := true;
           TabSheet9.Caption := ''+PrimeiraMaiscula(Edit1.Text)+'.Sql.Map.Config.Xml';
     end;
     if CheckBox9.Checked = true then
     begin
           gerarModelo(Edit1.Text);
           MostrarMemo(Memo12);
           TabSheet10.TabVisible := true;
           TabSheet10.Caption := ''+PrimeiraMaiscula(Edit1.Text)+'Modelo';
     end;
     if CheckBox10.Checked = true then
     begin
           criarCodeRequest(Edit1.Text);
           MostrarMemo(Memo13);
           TabSheet11.TabVisible := true;
           TabSheet11.Caption := ''+PrimeiraMaiscula(Edit1.Text)+'Request';
     end;
     if CheckBox11.Checked = true then
     begin
           criarCodeResponse(Edit1.Text);
           MostrarMemo(Memo14);
           TabSheet12.TabVisible := true;
           TabSheet12.Caption := ''+PrimeiraMaiscula(Edit1.Text)+'Response';
     end;
     if CheckBox18.Checked = true then
     begin
           criarCodeInquiryResponse(Edit1.Text);
           MostrarMemo(Memo15);
           TabSheet13.TabVisible := true;
           TabSheet13.Caption := 'Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Response';
     end;
     if CheckBox19.Checked = true then
     begin
           criarCodeInquiryRequest(Edit1.Text);
           MostrarMemo(Memo16);
           TabSheet14.TabVisible := true;
           TabSheet14.Caption := 'Inquiry'+PrimeiraMaiscula(Edit1.Text)+'Request';
     end;
     if CheckBox11.Checked = true then
     begin
           criarCodeClasseAPIControler(Edit1.Text);
           MostrarMemo(Memo17);
           TabSheet15.TabVisible := true;
           TabSheet15.Caption := ''+PrimeiraMaiscula(Edit1.Text)+'APIControler';
     end;
end;

procedure TForm1.SpeedButton1Click(Sender: TObject);
var   arq: TextFile; { declarando a variável "arq" do tipo arquivo texto }
    linha: string;
     b:tcheckbox;
     x,y:Integer;
     lDsLista  : TStrings;
begin

      Memo1.Clear;
      try
          lDsLista  := TStringList.Create;
      if DlgArquiv.Execute then
      begin
            AssignFile(arq, EdtDsArquiv.text);

            Reset(arq);   // [ 3 ] Abre o arquivo texto para leitura
            {$I+}         // ativa a diretiva de Input
            x := 10;
            y := 15;
            if (IOResult <> 0) // verifica o resultado da operação de abertura
               then Memo1.Lines.Add('Erro na abertura do arquivo !!!')
            else begin
                   lDsLista.LoadFromFile(DlgArquiv.FileName);
                   Reset(arq);
                          {$I+}
                          lDsLista.Insert(0,'NrCodigo NrRua NrPredio NrNivel DsBloco');
                          BrvFuncoesXE.StrToClientDataSet(lDsLista.Text,ClientDataSet1);
                   while (not eof(arq)) do
                   begin
                          readln(arq, linha); // [ 6 ] Lê uma linha do arquivo
                          b:=Tcheckbox.create(self);
                          b.visible:=false;
                          b.parent:=GroupBox5;
                          b.left:=x;
                          b.top:=y;
                          b.Width := 190;
                          b.name:= escreverCodeXML(linha,2);
                          b.Caption:= escreverCodeXML(linha,3);
                          b.visible:=true;
                          y := y + 20;
                          if y > 420 then
                          begin
                              x:= x + 200;
                              y := 15;
                          end;

                     Memo1.Lines.Add(linha);
                   end;

                   CloseFile(arq); // [ 8 ] Fecha o arquivo texto aberto
                 end;
             TbsConsulta.TabVisible := true;
          end;
      finally
              FreeAndNil(lDsLista);
      end;
end;
end.
