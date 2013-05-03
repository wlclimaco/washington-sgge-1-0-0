unit Unit10;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DB, IBCustomDataSet, IBQuery, StdCtrls, Buttons, ActButton,
  ActMask, ActMaskEdit, ActEdit,UBDLFornecedores, ExtCtrls, ActCustomEdit, ActNumberEdit,
  ActResultEdit, IBUpdateSQL, Grids, DBGrids, Menus;

type
  TForm10 = class(TForm)
    Panel1: TPanel;
    ActResultEdit1: TActResultEdit;
    Panel2: TPanel;
    SpeedButton1: TSpeedButton;
    SpeedButton2: TSpeedButton;
    SpeedButton3: TSpeedButton;
    SpeedButton4: TSpeedButton;
    SpeedButton5: TSpeedButton;
    SpeedButton6: TSpeedButton;
    SpeedButton7: TSpeedButton;
    SpeedButton8: TSpeedButton;
    SpeedButton9: TSpeedButton;
    SpeedButton10: TSpeedButton;
    SpeedButton11: TSpeedButton;
    ScrollBox1: TScrollBox;
    GroupBox1: TGroupBox;
    TXTRAZAO: TActEdit;
    TXTFANTASIA: TActEdit;
    GroupBox2: TGroupBox;
    Label1: TLabel;
    CBFORNECEDOR: TCheckBox;
    CDbLIENTE: TCheckBox;
    CBFISICA: TCheckBox;
    CBJURIDICA: TCheckBox;
    CBOUTRA: TCheckBox;
    TXTCNPJ: TActMaskEdit;
    CBAGROPtRANS: TCheckBox;
    ActButton1: TActButton;
    CDREPRESENTANTE: TCheckBox;
    TXTINERVALO: TActEdit;
    GroupBox3: TGroupBox;
    TXTBANCO: TActEdit;
    TXTCONTA: TActEdit;
    CheckBox7: TCheckBox;
    CheckBox8: TCheckBox;
    TXTIE: TActMaskEdit;
    TXTSERIE: TActEdit;
    TXTMODELONF: TActEdit;
    TXTCODIGOMUNICIPAL: TActEdit;
    TXTAGENCIA: TActEdit;
    GroupBox4: TGroupBox;
    TXTTIPO: TActEdit;
    GroupBox5: TGroupBox;
    TXTIM: TActMaskEdit;
    TXTSUFRAMA: TActMaskEdit;
    TXTTIPOLOGRADOURO: TActEdit;
    TXTLOGRADOURO: TActMaskEdit;
    TXTBAIRRO: TActEdit;
    TXTTELEFONE: TActMaskEdit;
    TXTCONTATO: TActEdit;
    TXTTELCONTATO: TActMaskEdit;
    TXTEMAIL: TActEdit;
    TXTEMAILNFe: TActEdit;
    TXTALVARA: TActMaskEdit;
    txtnum: TActMaskEdit;
    TXTFAX: TActMaskEdit;
    TXTSITE: TActEdit;
    GroupBox6: TGroupBox;
    TXTEMPRESA: TActResultEdit;
    TXTFILIAL: TActResultEdit;
    GroupBox7: TGroupBox;
    TXTCODREPR: TActResultEdit;
    IBQuery1: TIBQuery;
    IBQuery2: TIBQuery;
    TXTCIDADE: TActEdit;
    TXTCEP: TActResultEdit;
    IBQuery3: TIBQuery;
    IBQuery4: TIBQuery;
    IBUpdateSQL1: TIBUpdateSQL;
    GroupBox8: TGroupBox;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    QryForn: TIBQuery;
    QryEmpr: TIBQuery;
    QryFilial: TIBQuery;
    TXTUF: TActEdit;
    TXTCOD_PAIS: TActEdit;
    Label6: TLabel;
    Label7: TLabel;
    TXTRAMO: TActResultEdit;
    TXTIND_FORN: TActResultEdit;
    GroupBox9: TGroupBox;
    DBGrid1: TDBGrid;
    IBQuery5: TIBQuery;
    IBUpdateSQL2: TIBUpdateSQL;
    DataSource1: TDataSource;
    IBQuery5COD_MARCA: TIntegerField;
    IBQuery5MARCA: TIBStringField;
    IBQuery5COD_FORNECEDOR: TIntegerField;
    PopupMenu1: TPopupMenu;
    INSERIR1: TMenuItem;
    ALTERAR1: TMenuItem;
    EXCLUIR1: TMenuItem;
    IBQuery6: TIBQuery;
    SpeedButton12: TSpeedButton;
    procedure CDREPRESENTANTEClick(Sender: TObject);
    procedure SpeedButton11Click(Sender: TObject);
    procedure SpeedButton10Click(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure CBFISICAClick(Sender: TObject);
    procedure CBJURIDICAClick(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure SpeedButton4Click(Sender: TObject);
    procedure SpeedButton5Click(Sender: TObject);
    procedure ActResultEdit1Change(Sender: TObject);
    procedure TXTEMPRESAChange(Sender: TObject);
    procedure TXTFILIALChange(Sender: TObject);
    procedure TXTCODREPRChange(Sender: TObject);
    procedure TXTRAMOButtonClick(Sender: TObject);
    procedure TXTRAMOChange(Sender: TObject);
    procedure TXTIND_FORNButtonClick(Sender: TObject);
    procedure TXTIND_FORNChange(Sender: TObject);
    procedure TXTCEPButtonClick(Sender: TObject);
    procedure INSERIR1Click(Sender: TObject);
    procedure DBGrid1KeyPress(Sender: TObject; var Key: Char);
    procedure SpeedButton12Click(Sender: TObject);
    procedure SpeedButton6Click(Sender: TObject);
    procedure TXTCEPExit(Sender: TObject);
    procedure TXTEMPRESAButtonClick(Sender: TObject);
    procedure TXTFILIALButtonClick(Sender: TObject);
    procedure FormActivate(Sender: TObject);
    procedure ActResultEdit1ButtonClick(Sender: TObject);
  private
    { Private declarations }
  public
        function POPULARCLASSE():Boolean;
    function POPULARTXT():Boolean;
    function POPULARCLASSE_BD():Boolean;
    Function  Proximo(): Boolean;
    Function  Anterior(): Boolean;
    Function  primeiro(): Boolean;
    Function  ultimo(): Boolean;
    function Auterar(): Boolean;
    Function  Deletar(): Boolean;
    Function  Gravar(): Boolean;
    function  limpar():boolean;
    function  SelectInicial():boolean;
    function Atualiza():boolean;
  end;

var
  Form10: TForm10;
  fornecedores   :TFornecedores;
implementation

uses UFormCadProServ, UConsRamoAtiv, UConsIndForne, UConsCep, UConsEmpr,
  UConsFilial, UConsForn;

{$R *.dfm}

  Function  TForm10.Proximo(): Boolean;
    begin
      IBQuery1.Next;
    end;
    Function  TForm10.Anterior(): Boolean;
    begin
      IBQuery1.Prior;
    end;
    Function  TForm10.primeiro(): Boolean;
    begin
      IBQuery1.First;
    end;
    Function  TForm10.ultimo(): Boolean;
    begin
     IBQuery1.Last;
    end;
    function  TForm10.Auterar(): Boolean;
    begin
    IBQuery3.SQL.Clear;
    IBQuery3.SQL.Add('  update CLIENTES_FORNECEDOR ');
    IBQuery3.SQL.Add('set ');
    IBQuery3.SQL.Add('NOME = :NOME, ');
    IBQuery3.SQL.Add('COD_PAIS = :COD_PAIS, ');
    IBQuery3.SQL.Add('CNPJ = :CNPJ, ');
    IBQuery3.SQL.Add('CPF = :CPF, ');
    IBQuery3.SQL.Add('IE = :IE, ');
    IBQuery3.SQL.Add('COD_MUN = :COD_MUN, ');
    IBQuery3.SQL.Add('SUFRAMA = :SUFRAMA, ');
    IBQuery3.SQL.Add('ENDE = :ENDE, ');
    IBQuery3.SQL.Add(' NUM = :NUM, ');
    IBQuery3.SQL.Add('COMPL = :COMPL, ');
    IBQuery3.SQL.Add('BAIRRO = :BAIRRO, ');
    IBQuery3.SQL.Add('CLIENTE = :CLIENTE, ');
    IBQuery3.SQL.Add('FORNECEDOR = :FORNECEDOR, ');
    IBQuery3.SQL.Add('CONTA = :CONTA, ');
    IBQuery3.SQL.Add('BANCO = :BANCO, ');
    IBQuery3.SQL.Add('EMAIL = :EMAIL, ');
    IBQuery3.SQL.Add('EMAIL_NFE = :EMAIL_NFE, ');
    IBQuery3.SQL.Add('REPRESENTANTE = :REPRESENTANTE, ');
    IBQuery3.SQL.Add('INTERVALO_REPR = :INTERVALO_REPR, ');
    IBQuery3.SQL.Add('SITE = :SITE, ');
    IBQuery3.SQL.Add('DATA_CADASTRO = :DATA_CADASTRO, ');
    IBQuery3.SQL.Add('TELEFONE = :TELEFONE, ');
    IBQuery3.SQL.Add('FAX = :FAX, ');
    IBQuery3.SQL.Add('AGENCIA = :AGENCIA, ');
    IBQuery3.SQL.Add('TRANSPORTADOR = :TRANSPORTADOR, ');
    IBQuery3.SQL.Add('CEP = :CEP, ');
    IBQuery3.SQL.Add('EMPRESA = :EMPRESA, ');
    IBQuery3.SQL.Add('FILIAL = :FILIAL, ');
    IBQuery3.SQL.Add('TELCONTATO = :TELCONTATO, ');
    IBQuery3.SQL.Add('CONTATO = :CONTATO, ');
    IBQuery3.SQL.Add('RAZAO = :RAZAO, ');
    IBQuery3.SQL.Add('FANTASIA = :FANTASIA, ');
    IBQuery3.SQL.Add('STATUS = :STATUS, ');
    IBQuery3.SQL.Add('TPPESSOA = :TPPESSOA, ');
    IBQuery3.SQL.Add('UF = :UF , ');
    IBQuery3.SQL.Add('DIASREPRE = :DIASREPRE, ');
    IBQuery3.SQL.Add('RAMO_ATIVIDADE = :RAMO_ATIVIDADE ,');
    IBQuery3.SQL.Add('IND_FORNECEDOR = :IND_FORNECEDOR ');
    IBQuery3.SQL.Add('where  ');
    IBQuery3.SQL.Add('COD_PART = :OLD_COD_PART  ');

    IBQuery3.ParamByName('NOME').AsString := fornecedores.NOME;
    IBQuery3.ParamByName('COD_PAIS').AsInteger := fornecedores.COD_PAIS;
    IBQuery3.ParamByName('CNPJ').AsString := fornecedores.CNPJ;
    IBQuery3.ParamByName('CPF').AsString := fornecedores.CPF;
    IBQuery3.ParamByName('IE').AsString := fornecedores.IE;
    IBQuery3.ParamByName('COD_MUN').AsInteger := fornecedores.COD_MUN;
    IBQuery3.ParamByName('SUFRAMA').AsString := fornecedores.SUFRAMA;
    IBQuery3.ParamByName('ENDE').AsString := fornecedores.ENDE;
    IBQuery3.ParamByName('NUM').AsInteger := fornecedores.Num;
    IBQuery3.ParamByName('COMPL').AsString := fornecedores.COMPL;
    IBQuery3.ParamByName('BAIRRO').AsString := fornecedores.BAIRRO;
    IBQuery3.ParamByName('CLIENTE').AsString := fornecedores.CLIENTE;
    IBQuery3.ParamByName('FORNECEDOR').AsString := fornecedores.FORNECEDOR;
    IBQuery3.ParamByName('CONTA').AsString := fornecedores.CONTA;
    IBQuery3.ParamByName('BANCO').AsString := fornecedores.BANCO;
    IBQuery3.ParamByName('EMAIL').AsString := fornecedores.EMAIL;
    IBQuery3.ParamByName('EMAIL_NFE').AsString := fornecedores.EMAIL_NFE;
    IBQuery3.ParamByName('REPRESENTANTE').AsString := fornecedores.representante;
    IBQuery3.ParamByName('INTERVALO_REPR').AsString := fornecedores.INTERVALO_REPR;
    IBQuery3.ParamByName('SITE').AsString := fornecedores.SITE;
    IBQuery3.ParamByName('DATA_CADASTRO').AsDateTime := Now;
    IBQuery3.ParamByName('TELEFONE').AsString := fornecedores.TELEFONE;
    IBQuery3.ParamByName('FAX').AsString := fornecedores.FAX;
    IBQuery3.ParamByName('AGENCIA').AsString := fornecedores.AGENCIA;
    IBQuery3.ParamByName('TRANSPORTADOR').AsString := fornecedores.transportador;
    IBQuery3.ParamByName('CEP').AsInteger := fornecedores.Cep;
    IBQuery3.ParamByName('EMPRESA').AsInteger := fornecedores.COD_EMPRESA;
    IBQuery3.ParamByName('FILIAL').AsInteger := fornecedores.COD_FILIAL;
    IBQuery3.ParamByName('TELCONTATO').AsString := fornecedores.telcontato;
    IBQuery3.ParamByName('CONTATO').AsString := fornecedores.contato;
    IBQuery3.ParamByName('RAZAO').AsString := fornecedores.razao;
    IBQuery3.ParamByName('FANTASIA').AsString := fornecedores.Fantasia;
    IBQuery3.ParamByName('STATUS').AsString := fornecedores.status;
    IBQuery3.ParamByName('TPPESSOA').AsString := fornecedores.TPPESSOA;
    IBQuery3.ParamByName('UF').AsString := fornecedores.UF;
    IBQuery3.ParamByName('OLD_COD_PART').AsInteger := fornecedores.COD_PART;
    IBQuery3.ParamByName('DIASREPRE').AsInteger := fornecedores.COD_REPRESENTANTE ;
    IBQuery3.ParamByName('RAMO_ATIVIDADE').AsInteger := fornecedores.RAMO ;
    IBQuery3.ParamByName('IND_FORNECEDOR').AsInteger := fornecedores.INDFORN ;
    IBQuery3.ExecSQL;
    IBQuery3.Transaction.Commit;
    end;
    Function  TForm10.Deletar(): Boolean;
    begin
      IBQuery3.SQL.Clear;
    IBQuery3.SQL.Add('delete from CLIENTES_FORNECEDOR');
    IBQuery3.SQL.Add('where ');
    IBQuery3.SQL.Add('COD_PART = :OLD_COD_PART ');
    IBQuery3.ParamByName('OLD_COD_PART').AsInteger := fornecedores.COD_PART;
    IBQuery3.ExecSQL;
    IBQuery3.Transaction.Commit;
    end;
    Function  TForm10.Gravar(): Boolean;
    begin
    IBQuery3.SQL.Clear;
    IBQuery3.SQL.Add(' insert into CLIENTES_FORNECEDOR ');
    IBQuery3.SQL.Add(' (COD_PART, NOME, COD_PAIS, CNPJ, CPF, IE, COD_MUN, SUFRAMA, ENDE, NUM, COMPL, BAIRRO, ');
    IBQuery3.SQL.Add(' CLIENTE, FORNECEDOR, CONTA, BANCO, EMAIL, EMAIL_NFE, REPRESENTANTE, ');
    IBQuery3.SQL.Add(' INTERVALO_REPR, SITE, DATA_CADASTRO, TELEFONE, FAX, AGENCIA, TRANSPORTADOR, ');
    IBQuery3.SQL.Add(' CEP, EMPRESA, FILIAL, TELCONTATO, CONTATO, RAZAO, FANTASIA, STATUS,UF, TPPESSOA,DIASREPRE,RAMO_ATIVIDADE,IND_FORNECEDOR ');
    IBQuery3.SQL.Add(' ) ');
    IBQuery3.SQL.Add(' values ');
    IBQuery3.SQL.Add(' (:COD_PART,:NOME, :COD_PAIS, :CNPJ, :CPF, :IE, :COD_MUN, :SUFRAMA, :ENDE, :NUM, ');
    IBQuery3.SQL.Add(' :COMPL, :BAIRRO, :CLIENTE, :FORNECEDOR, :CONTA, :BANCO, :EMAIL, :EMAIL_NFE, ');
    IBQuery3.SQL.Add(' :REPRESENTANTE, :INTERVALO_REPR, :SITE, :DATA_CADASTRO, :TELEFONE, :FAX, ');
    IBQuery3.SQL.Add(' :AGENCIA, :TRANSPORTADOR, :CEP, :EMPRESA, :FILIAL, :TELCONTATO, :CONTATO, ');
    IBQuery3.SQL.Add(' :RAZAO, :FANTASIA, :STATUS, :UF,:TPPESSOA,:DIASREPRE,:RAMO_ATIVIDADE,:IND_FORNECEDOR) ');
    IBQuery3.ParamByName('NOME').AsString := fornecedores.NOME;
    IBQuery3.ParamByName('COD_PAIS').AsInteger := fornecedores.COD_PAIS;
    IBQuery3.ParamByName('CNPJ').AsString := fornecedores.CNPJ;
    IBQuery3.ParamByName('CPF').AsString := fornecedores.CPF;
    IBQuery3.ParamByName('IE').AsString := fornecedores.IE;
    IBQuery3.ParamByName('COD_MUN').AsInteger := fornecedores.COD_MUN;
    IBQuery3.ParamByName('SUFRAMA').AsString := fornecedores.SUFRAMA;
    IBQuery3.ParamByName('ENDE').AsString := fornecedores.ENDE;
    IBQuery3.ParamByName('NUM').AsInteger := fornecedores.Num;
    IBQuery3.ParamByName('COMPL').AsString := fornecedores.COMPL;
    IBQuery3.ParamByName('BAIRRO').AsString := fornecedores.BAIRRO;
    IBQuery3.ParamByName('CLIENTE').AsString := fornecedores.CLIENTE;
    IBQuery3.ParamByName('FORNECEDOR').AsString := fornecedores.FORNECEDOR;
    IBQuery3.ParamByName('CONTA').AsString := fornecedores.CONTA;
    IBQuery3.ParamByName('BANCO').AsString := fornecedores.BANCO;
    IBQuery3.ParamByName('EMAIL').AsString := fornecedores.EMAIL;
    IBQuery3.ParamByName('EMAIL_NFE').AsString := fornecedores.EMAIL_NFE;
    IBQuery3.ParamByName('REPRESENTANTE').AsString := fornecedores.representante;
    IBQuery3.ParamByName('INTERVALO_REPR').AsString := fornecedores.INTERVALO_REPR;
    IBQuery3.ParamByName('SITE').AsString := fornecedores.SITE;
    IBQuery3.ParamByName('DATA_CADASTRO').AsDateTime := Now;
    IBQuery3.ParamByName('TELEFONE').AsString := fornecedores.TELEFONE;
    IBQuery3.ParamByName('FAX').AsString := fornecedores.FAX;
    IBQuery3.ParamByName('AGENCIA').AsString := fornecedores.AGENCIA;
    IBQuery3.ParamByName('TRANSPORTADOR').AsString := fornecedores.transportador;
    IBQuery3.ParamByName('CEP').AsInteger := fornecedores.Cep;
    IBQuery3.ParamByName('EMPRESA').AsInteger := fornecedores.COD_EMPRESA;
    IBQuery3.ParamByName('FILIAL').AsInteger := fornecedores.COD_FILIAL;
    IBQuery3.ParamByName('TELCONTATO').AsString := fornecedores.telcontato;
    IBQuery3.ParamByName('CONTATO').AsString := fornecedores.contato;
    IBQuery3.ParamByName('RAZAO').AsString := fornecedores.razao;
    IBQuery3.ParamByName('FANTASIA').AsString := fornecedores.Fantasia;
    IBQuery3.ParamByName('STATUS').AsString := fornecedores.status;
    IBQuery3.ParamByName('UF').AsString := fornecedores.UF;
    IBQuery3.ParamByName('TPPESSOA').AsString := fornecedores.TPPESSOA;
    IBQuery3.ParamByName('COD_PART').AsInteger := fornecedores.COD_PART;
    IBQuery3.ParamByName('DIASREPRE').AsInteger := fornecedores.COD_REPRESENTANTE ;
    IBQuery3.ParamByName('RAMO_ATIVIDADE').AsInteger := fornecedores.RAMO ;
    IBQuery3.ParamByName('IND_FORNECEDOR').AsInteger := fornecedores.INDFORN ;
    IBQuery3.ExecSQL;
    IBQuery3.Transaction.Commit;
    end;
    function  TForm10.limpar():boolean;
    begin
     fornecedores.COD_EMPRESA := 0 ;
     fornecedores.COD_FILIAL := 0 ;
     fornecedores.COD_PART := 0 ;
     fornecedores.NOME     := '' ;
     fornecedores.COD_PAIS := 0 ;
     fornecedores.IE       := '' ;
     fornecedores.COD_MUN  := 0 ;
     fornecedores.COD_REPRESENTANTE  := 0 ;
     fornecedores.RAMO  := 0 ;
     fornecedores.INDFORN  := 0 ;
     fornecedores.SUFRAMA  := '' ;
     fornecedores.ENDE     := '' ;
     fornecedores.Cep      := 0 ;
     fornecedores.Num      := 0 ;
     fornecedores.COMPL    := '' ;
     fornecedores.BAIRRO   := '' ;
     fornecedores.CLIENTE  := '' ;
     fornecedores.Fantasia  := '' ;
     fornecedores.FORNECEDOR  := '' ;
     fornecedores.representante := '' ;
     fornecedores.transportador := '' ;
     fornecedores.CONTA := '' ;
     fornecedores.BANCO := '' ;
     fornecedores.EMAIL_NFE := '' ;
     fornecedores.INTERVALO_REPR := '' ;
     fornecedores.EMAIL := '' ;
     fornecedores.SITE := '' ;
     fornecedores.DATA_CADASTRO := NOW ;
     fornecedores.TELEFONE := '' ;
     fornecedores.FAX := '' ;
     fornecedores.AGENCIA := '' ;
     fornecedores.status  := '' ;
     fornecedores.cnpj := '';
     fornecedores.cpf  := '';
    end;
    function  TForm10.SelectInicial():boolean;
    begin


      IBQuery1.Active := False;
      IBQuery1.SQL.Clear;
      IBQuery1.SQL.Text := 'Select *from CLIENTES_FORNECEDOR' ;
      IBQuery1.Active := True;
    end;
    function  TForm10.Atualiza():boolean;
    begin

    end;


    function TForm10.POPULARCLASSE():Boolean;
    BEGIN
     fornecedores.COD_EMPRESA :=  StrToInt(TXTEMPRESA.TEXT);
     fornecedores.COD_FILIAL  :=  StrToInt(TXTFILIAL.TEXT);
     fornecedores.COD_PART := StrToInt(ActResultEdit1.TEXT);
     fornecedores.NOME := TXTRAZAO.TEXT;
     fornecedores.COD_PAIS := StrToInt(TXTCOD_PAIS.TEXT);
     fornecedores.Fantasia := TXTFANTASIA.Text;
     fornecedores.IE := TXTIE.TEXT;
     fornecedores.COD_MUN := StrToInt(TXTCODIGOMUNICIPAL.TEXT);
     fornecedores.SUFRAMA := TXTSUFRAMA.TEXT;
     fornecedores.ENDE := TXTLOGRADOURO.TEXT;
     fornecedores.Cep := StrToInt(TXTCEP.TEXT);
     fornecedores.Num := StrToInt(txtnum.TEXT);
     fornecedores.COMPL := TXTTIPOLOGRADOURO.TEXT;
     fornecedores.BAIRRO := TXTBAIRRO.TEXT;
     if (CDbLIENTE.Checked = True )then
           fornecedores.CLIENTE := 'S'
     else
           fornecedores.CLIENTE := 'N';
     if (CBFORNECEDOR.Checked = True )then
          fornecedores.FORNECEDOR := 'S'
     else
       fornecedores.FORNECEDOR := 'N';
     if (CDREPRESENTANTE.Checked = True )then
       fornecedores.representante := 'S'
     else
       fornecedores.representante := 'N';
     if (CBAGROPtRANS.Checked = True )then
         fornecedores.transportador := 'S'
     else
     fornecedores.transportador := 'N' ;
     if (CBFISICA.Checked = True )then
     begin
     fornecedores.TPPESSOA := 'F';
     fornecedores.CPF := TXTCNPJ.TEXT ;
     end;
     if (CBJURIDICA.Checked = True) then
     begin
          fornecedores.TPPESSOA := 'J';
          fornecedores.CNPJ := TXTCNPJ.TEXT
     end;
     if(RadioButton1.Checked = True)then
        fornecedores.status := 'A'
     else
        fornecedores.status := 'I';

     fornecedores.RAMO              := StrToInt(TXTRAMO.Text);
     fornecedores.INDFORN           := StrToInt(TXTIND_FORN.Text);
     fornecedores.COD_REPRESENTANTE := StrToInt(TXTCODREPR.Text);
     fornecedores.CONTA := TXTCONTA.TEXT;
     fornecedores.BANCO := TXTBANCO.TEXT;
     fornecedores.EMAIL_NFE := TXTEMAILNFe.TEXT;
     fornecedores.INTERVALO_REPR := TXTINERVALO.Text;
     fornecedores.EMAIL := TXTEMAIL.TEXT;
     fornecedores.SITE := TXTSITE.TEXT;
     fornecedores.DATA_CADASTRO := Now;
     fornecedores.TELEFONE := TXTTELEFONE.TEXT;
     fornecedores.FAX := TXTFAX.TEXT;
     fornecedores.AGENCIA := TXTAGENCIA.TEXT;
    end;
    function TForm10.POPULARTXT():Boolean;
    BEGIN
       TXTEMPRESA.Text := IntToStr(fornecedores.COD_EMPRESA);
       TXTFILIAL.Text  := IntToStr(fornecedores.COD_FILIAL);
       ActResultEdit1.TEXT := IntToStr(fornecedores.COD_PART);
       TXTRAZAO.TEXT       := fornecedores.NOME ;
       TXTCOD_PAIS.TEXT    := IntToStr(fornecedores.COD_PAIS);
       TXTIE.TEXT          := fornecedores.IE ;
       TXTCODIGOMUNICIPAL.TEXT:= IntToStr(fornecedores.COD_MUN);
       TXTSUFRAMA.TEXT := fornecedores.SUFRAMA ;
       TXTLOGRADOURO.TEXT := fornecedores.ENDE;
       TXTCEP.TEXT := IntToStr(fornecedores.Cep);
       txtnum.TEXT := IntToStr(fornecedores.Num);
       TXTFANTASIA.Text := fornecedores.fantasia;
       TXTCODREPR.Text :=    IntToStr(fornecedores.COD_REPRESENTANTE);
       TXTTIPOLOGRADOURO.Text :=  fornecedores.COMPL ;
       TXTBAIRRO.TEXT := fornecedores.BAIRRO ;
     if (fornecedores.CLIENTE = 'S' )then
         CDbLIENTE.Checked := True
     else
         CDbLIENTE.Checked := fALSE;

     if (fornecedores.FORNECEDOR = 'S' )then
         CBFORNECEDOR.Checked := True
     else
        CBFORNECEDOR.Checked := False;

     if (fornecedores.representante = 'S' )then
          CDREPRESENTANTE.Checked := True
     else
       CDREPRESENTANTE.Checked := False;

     if (fornecedores.transportador = 'S' )then
         CBAGROPtRANS.Checked := True
     else
         CBAGROPtRANS.Checked := False;

     if (fornecedores.TPPESSOA = 'J' )then
     begin
         CBFISICA.Checked := False;
         TXTCNPJ.TEXT := fornecedores.CPF;
     end
     else
     begin
         CBJURIDICA.Checked := True ;
         TXTCNPJ.TEXT :=  fornecedores.Cpf; 
     end;
     if(fornecedores.status = 'A')then
      RadioButton1.Checked := True
     else
      RadioButton2.Checked := True;

     TXTCONTA.TEXT     := fornecedores.CONTA;
     TXTBANCO.TEXT     := fornecedores.BANCO;
     TXTEMAILNFe.TEXT  := fornecedores.EMAIL_NFE;
     TXTINERVALO.Text  := fornecedores.INTERVALO_REPR;
     TXTEMAIL.TEXT     := fornecedores.EMAIL;
     TXTSITE.TEXT      := fornecedores.SITE;
     //fornecedores.DATA_CADASTRO := Now;
     TXTTELEFONE.TEXT  := fornecedores.TELEFONE;
     TXTFAX.TEXT       := fornecedores.FAX;
     TXTAGENCIA.TEXT   := fornecedores.AGENCIA;
     TXTRAMO.Text :=    IntToStr(fornecedores.RAMO);
     TXTIND_FORN.Text :=    IntToStr(fornecedores.INDFORN);
     if(fornecedores.status = 'A') then
      RadioButton1.Checked := True
     else
      RadioButton2.Checked := True;
    end;
    function TForm10.POPULARCLASSE_BD():Boolean;
    BEGIN
     fornecedores.COD_EMPRESA := IbQuery1.FieldByName('empresa').AsInteger ;
     fornecedores.COD_FILIAL := IbQuery1.FieldByName('filial').AsInteger ;
     fornecedores.COD_PART := IbQuery1.FieldByName('COD_PART').AsInteger ;
     fornecedores.NOME     := IbQuery1.FieldByName('NOME').AsString ;
     fornecedores.COD_PAIS := IbQuery1.FieldByName('COD_PAIS').AsInteger ;
     fornecedores.IE       := IbQuery1.FieldByName('IE').AsString ;
     fornecedores.COD_MUN  := IbQuery1.FieldByName('COD_MUN').AsInteger ;
     fornecedores.COD_REPRESENTANTE  := IbQuery1.FieldByName('diasrepre').AsInteger ;
     fornecedores.RAMO  := IbQuery1.FieldByName('RAMO_ATIVIDADE').AsInteger ;
     fornecedores.INDFORN  := IbQuery1.FieldByName('IND_FORNECEDOR').AsInteger ;
     fornecedores.SUFRAMA  := IbQuery1.FieldByName('SUFRAMA').AsString ;
     fornecedores.ENDE     := IbQuery1.FieldByName('ENDE').AsString ;
     fornecedores.Cep      := IbQuery1.FieldByName('CEP').AsInteger ;
     fornecedores.Num      := IbQuery1.FieldByName('NUM').AsInteger ;
     fornecedores.COMPL    := IbQuery1.FieldByName('COMPL').AsString ;
     fornecedores.BAIRRO   := IbQuery1.FieldByName('BAIRRO').AsString ;
     fornecedores.CLIENTE  := IbQuery1.FieldByName('CLIENTE').AsString ;
     fornecedores.Fantasia  := IbQuery1.FieldByName('Fantasia').AsString ;
     fornecedores.FORNECEDOR  := IbQuery1.FieldByName('FORNECEDOR').AsString ;
     fornecedores.representante := IbQuery1.FieldByName('REPRESENTANTE').AsString ;
     fornecedores.transportador := IbQuery1.FieldByName('TRANSPORTADOR').AsString ;
     fornecedores.CONTA := IbQuery1.FieldByName('CONTA').AsString ;
     fornecedores.BANCO := IbQuery1.FieldByName('BANCO').AsString ;
     fornecedores.EMAIL_NFE := IbQuery1.FieldByName('EMAIL_NFE').AsString ;
     fornecedores.INTERVALO_REPR := IbQuery1.FieldByName('INTERVALO_REPR').AsString ;
     fornecedores.EMAIL := IbQuery1.FieldByName('EMAIL').AsString ;
     fornecedores.SITE := IbQuery1.FieldByName('SITE').AsString ;
     fornecedores.DATA_CADASTRO := IbQuery1.FieldByName('DATA_CADASTRO').AsDateTime ;
     fornecedores.TELEFONE := IbQuery1.FieldByName('TELEFONE').AsString ;
     fornecedores.FAX := IbQuery1.FieldByName('FAX').AsString ;
     fornecedores.AGENCIA := IbQuery1.FieldByName('AGENCIA').AsString ;
     fornecedores.status  := IbQuery1.FieldByName('STATUS').AsString ;
     if (IbQuery1.FieldByName('TRANSPORTADOR').AsString = 'J' )then
          fornecedores.cnpj := IbQuery1.FieldByName('cnpj').AsString
     else
          fornecedores.cpf  := IbQuery1.FieldByName('CPF').AsString;

    end;



procedure TForm10.CDREPRESENTANTEClick(Sender: TObject);
begin
  inherited;
if (CDREPRESENTANTE.Checked = tRUE)THEN
BEGIN
  TXTINERVALO.Visible := True;
  Label1.Visible      := True;
END
else
BEGIN
  TXTINERVALO.Visible := FALSE;
  Label1.Visible      := FALSE;
end;
end;

procedure TForm10.SpeedButton11Click(Sender: TObject);
begin
primeiro();
POPULARCLASSE_BD();
POPULARTXT();
end;

procedure TForm10.SpeedButton10Click(Sender: TObject);
begin
Proximo();
POPULARCLASSE_BD();
POPULARTXT();
end;

procedure TForm10.SpeedButton1Click(Sender: TObject);
begin
Anterior();
POPULARCLASSE_BD();
POPULARTXT();
end;

procedure TForm10.SpeedButton2Click(Sender: TObject);
begin
Ultimo();
POPULARCLASSE_BD();
POPULARTXT();
end;

procedure TForm10.CBFISICAClick(Sender: TObject);
begin
CBJURIDICA.Checked := False;
end;

procedure TForm10.CBJURIDICAClick(Sender: TObject);
begin
CBFISICA.Checked := False;
end;

procedure TForm10.SpeedButton3Click(Sender: TObject);
begin
POPULARCLASSE;
IF(Gravar())THEN
  ShowMEssage('FORNECEDOR INSERIDO COM SUCESSO !!');
SelectInicial();
POPULARCLASSE_BD();
POPULARTXT();
end;

procedure TForm10.SpeedButton4Click(Sender: TObject);
begin
POPULARCLASSE;
IF (Auterar()) THEN
    ShowMEssage('FORNECEDOR ALTERADO COM SUCESSO !!');
SelectInicial();
POPULARCLASSE_BD();
POPULARTXT();
end;

procedure TForm10.SpeedButton5Click(Sender: TObject);
begin
POPULARCLASSE;
if MessageDlg('Tem Certeza????', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
Deletar();
SelectInicial();
POPULARCLASSE_BD();
POPULARTXT();
end;

procedure TForm10.ActResultEdit1Change(Sender: TObject);
begin
  if (ActResultEdit1.Text <> '')then
  begin

  QryForn.Active := False;
  QryForn.SQL.Clear;
  QryForn.SQL.Add('SELECT *FROM clientes_fornecedor WHERE cod_part = '+ActResultEdit1.Text+'');
  QryForn.Active := True;
  if(not QryForn.Eof)then
     Label2.Caption := ''+QryForn.fieldbyName('nome').AsString+'  -  '+QryForn.fieldbyName('CNPJ').AsString
  else
     Label2.Caption := '';
  end;
  IBQuery5.Active := False;
  IBQuery5.SQL.Clear;
  IBQuery5.SQL.Add('select *from marca where cod_fornecedor = '+ActResultEdit1.Text);
  IBQuery5.Active := True;
end;

procedure TForm10.TXTEMPRESAChange(Sender: TObject);
begin
  if (TXTEMPRESA.Text <> '')then
  begin

  FRMCADPROD.QryEmpresa.Active := False;
  //FRMCADPROD.QryEmpresa.SQL.Clear;
  //FRMCADPROD.QryEmpresa.SQL.Add('SELECT *FROM clientes_fornecedor WHERE cod_part = '+ActResultEdit1.Text+'');
  FRMCADPROD.QryEmpresa.ParamByName('Cod_Empresa').AsInteger := StrToInt(txtempresa.Text);
  FRMCADPROD.QryEmpresa.Active := True;
  if(not FRMCADPROD.QryEmpresa.Eof)then
     Label3.Caption := ''+FRMCADPROD.QryEmpresa.fieldbyName('nome').AsString
  else
     Label3.Caption := '';
  end;
end;

procedure TForm10.TXTFILIALChange(Sender: TObject);
begin
  if (txtfilial.Text <> '')then
  begin
  FRMCADPROD.Qryfilial.Active := False;
  FRMCADPROD.Qryfilial.ParamByName('CdFilial').AsInteger := StrToInt(txtFilial.Text);
  FRMCADPROD.Qryfilial.ParamByName('Cod_Empresa').AsInteger := StrToInt(txtempresa.Text);
  FRMCADPROD.Qryfilial.Active := True;
  if(not FRMCADPROD.Qryfilial.Eof)then
     Label4.Caption := FRMCADPROD.Qryfilial.fieldbyName('NOME').AsString
  else
     Label4.Caption := '';
  end;
end;

procedure TForm10.TXTCODREPRChange(Sender: TObject);
begin
  if (TXTCODREPR.Text <> '')then
  begin
  QryEmpr.Active := False;
  QryEmpr.SQL.Clear;
  QryEmpr.SQL.Add('SELECT *FROM clientes_fornecedor WHERE cod_part = '+ActResultEdit1.Text+' and representante = ''S'' ');
  QryEmpr.Active := True;
  if(not QryEmpr.Eof)then
     Label5.Caption := ''+QryEmpr.fieldbyName('nome').AsString
  else
     Label5.Caption := '';
  end;
end;

procedure TForm10.TXTRAMOButtonClick(Sender: TObject);
begin
FRMCONSRAMO.Show;
end;

procedure TForm10.TXTRAMOChange(Sender: TObject);
begin
 if (TXTRAMO.Text <> '')then
  begin
  QryFilial.Active := False;
  QryFilial.ParamByName('RAMO').AsInteger := StrToInt(TXTRAMO.Text);
  QryFilial.Active := True;
  if(not QryFilial.Eof)then
     Label6.Caption := QryFilial.fieldbyName('DESCRICAO').AsString
  else
     Label6.Caption := '';
end;
 END;
procedure TForm10.TXTIND_FORNButtonClick(Sender: TObject);
begin
  Form17.Show;
end;

procedure TForm10.TXTIND_FORNChange(Sender: TObject);
begin
 if (TXTIND_FORN.Text <> '')then
  begin
  IBQuery4.Active := False;
  IBQuery4.ParamByName('CODIGO').AsInteger := StrToInt(TXTIND_FORN.Text);
  IBQuery4.Active := True;
  if(not IBQuery4.Eof)then
     Label7.Caption := IBQuery4.fieldbyName('VALOR').AsString
  else
     Label7.Caption := '';
end;
end;

procedure TForm10.TXTCEPButtonClick(Sender: TObject);
begin
FRMCONSCEP.Show;
end;

procedure TForm10.INSERIR1Click(Sender: TObject);
begin
IBQuery5.Close;
IBQuery5.OPEN;
IBQuery5.Insert;
end;

procedure TForm10.DBGrid1KeyPress(Sender: TObject; var Key: Char);
begin
  if(dbGrid1.Fields[0].IsNull )THEN
  begin
    IBQuery6.Active := False;
    IBQuery6.SQL.Text := 'SELECT MAX(cod_MARCA) FROM MARCA ';
    IBQuery6.Active := True;
    dbGrid1.Fields[0].AsInteger := IBQuery6.FieldByName('max').asInteger + 1;
    dbGrid1.Fields[2].AsInteger := StrToInt(ActResultEdit1.Text);


  end

end;



procedure TForm10.SpeedButton12Click(Sender: TObject);
begin
       IBQuery5.Post;
       IBQuery5.Transaction.Commit;
end;

procedure TForm10.SpeedButton6Click(Sender: TObject);
begin
limpar();
POPULARTXT();
end;

procedure TForm10.TXTCEPExit(Sender: TObject);
begin
IBQuery2.Active := False ;
IBQuery2.SQL.Clear;
IBQuery2.SQL.Text := 'select *from cep where nocep = '+TXTCEP.Text;
IBQuery2.Active := True;
if (not IBQuery2.Eof) then
begin
TXTCODIGOMUNICIPAL.Text := IBQuery2.FieldByName('codigoibge').AsString;
TXTUF.Text              := IBQuery2.FieldByName('dsestado').AsString;
TXTLOGRADOURO.Text      := IBQuery2.FieldByName('dslogradouro').AsString;
TXTBAIRRO.Text          := IBQuery2.FieldByName('dsbairro').AsString;
TXTCIDADE.Text          := IBQuery2.FieldByName('dsCIDADE').AsString;
end
ELSE
IF MessageDlg('CEP n�o encontrado deseja cadastrar !!', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
end;

procedure TForm10.TXTEMPRESAButtonClick(Sender: TObject);
begin

Form11.Show;
end;

procedure TForm10.TXTFILIALButtonClick(Sender: TObject);
begin

Form12.Show;
end;

procedure TForm10.FormActivate(Sender: TObject);
begin
IBQuery5.Close;
IBQuery5.Open;
IBQuery5.Insert;
fornecedores := Tfornecedores.Create;
SelectInicial();
POPULARCLASSE_BD();
POPULARTXT();  

end;

procedure TForm10.ActResultEdit1ButtonClick(Sender: TObject);
begin
ConsForn.InsertCodRet(1);
ConsForn.show;
end;

end.