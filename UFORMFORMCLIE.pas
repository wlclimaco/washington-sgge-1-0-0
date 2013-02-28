unit UFORMFORMCLIE;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UFormModelo, StdCtrls, Buttons, ActButton, ActMask, ActMaskEdit,
  ActEdit, DBCtrls, ExtCtrls,UBDLFornecedores, ActCustomEdit, ActNumberEdit, ActResultEdit,
  DB, IBCustomDataSet, IBQuery, IBDatabase;

type
  TForm3 = class(TForm1)
    GroupBox1: TGroupBox;
    GroupBox2: TGroupBox;
    GroupBox3: TGroupBox;
    TXTRAZAO: TActEdit;
    TXTFANTASIA: TActEdit;
    CBFORNECEDOR: TCheckBox;
    CDbLIENTE: TCheckBox;
    CBFISICA: TCheckBox;
    CBJURIDICA: TCheckBox;
    CBOUTRA: TCheckBox;
    TXTCNPJ: TActMaskEdit;
    CBAGROPtRANS: TCheckBox;
    ActButton1: TActButton;
    TXTBANCO: TActEdit;
    TXTCONTA: TActEdit;
    CheckBox7: TCheckBox;
    CheckBox8: TCheckBox;
    TXTIE: TActMaskEdit;
    TXTUF: TActEdit;
    TXTCOD_PAIS: TActEdit;
    TXTSERIE: TActEdit;
    TXTMODELONF: TActEdit;
    TXTCODIGOMUNICIPAL: TActEdit;
    GroupBox4: TGroupBox;
    TXTTIPO: TActEdit;
    GroupBox5: TGroupBox;
    TXTIM: TActMaskEdit;
    TXTSUFRAMA: TActMaskEdit;
    TXTTIPOLOGRADOURO: TActEdit;
    TXTLOGRADOURO: TActMaskEdit;
    TXTBAIRRO: TActEdit;
    TXTCEP: TActMaskEdit;
    TXTTELEFONE: TActMaskEdit;
    TXTCONTATO: TActEdit;
    TXTTELCONTATO: TActMaskEdit;
    TXTEMAIL: TActEdit;
    TXTEMAILNFe: TActEdit;
    TXTALVARA: TActMaskEdit;
    GroupBox6: TGroupBox;
    TXTEMPRESA: TActResultEdit;
    TXTFILIAL: TActResultEdit;
    CDREPRESENTANTE: TCheckBox;
    GroupBox7: TGroupBox;
    TXTCODREPR: TActResultEdit;
    TXTAGENCIA: TActEdit;
    txtnum: TActMaskEdit;
    TXTINERVALO: TActEdit;
    Label1: TLabel;
    TXTFAX: TActMaskEdit;
    TXTSITE: TActEdit;
    IBQuery1: TIBQuery;
    IBQuery2: TIBQuery;
    procedure CDREPRESENTANTEClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    function POPULARCLASSE():Boolean;
    function POPULARTXT():Boolean;
    function POPULARCLASSE_BD():Boolean;
    Function  Proximo(): Boolean;
    Function  Anterior(): Boolean;
    Function  primeiro(): Boolean;
    Function  ultimo(): Boolean;
    function Auterar(): Boolean;
    Function  Deletar(): Boolean;
    function  limpar():boolean;
    function  SelectInicial():boolean;
    function Atualiza():boolean;

  end;

var
  Form3: TForm3;
  fornecedores   :TFornecedores;
implementation

uses Unit7, Unit8, UFormCadProServ, Unit1;

{$R *.dfm}

    Function  TForm3.Proximo(): Boolean;
    begin

    end;
    Function  TForm3.Anterior(): Boolean;
    begin

    end;
    Function  TForm3.primeiro(): Boolean;
    begin

    end;
    Function  TForm3.ultimo(): Boolean;
    begin

    end;
    function  TForm3.Auterar(): Boolean;
    begin

    end;
    Function  TForm3.Deletar(): Boolean;
    begin

    end;
    function  TForm3.limpar():boolean;
    begin

    end;
    function  TForm3.SelectInicial():boolean;
    begin

      {
      IBQuery1.Active := False;
     // IBQuery1.SQL.Clear;
      IBQuery1.SQL.Text := 'Select *from CLIENTES_FORNECEDOR' ;
      IBQuery1.Active := True;   }
    end;
    function  TForm3.Atualiza():boolean;
    begin

    end;


    function TForm3.POPULARCLASSE():Boolean;
    BEGIN
     fornecedores.COD_PART := StrToInt(ActResultEdit1.TEXT);
     fornecedores.NOME := TXTRAZAO.TEXT;
     fornecedores.COD_PAIS := StrToInt(TXTCOD_PAIS.TEXT);

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
     fornecedores.CPF := TXTCNPJ.TEXT
     else
     begin
       if (CBJURIDICA.Checked = True) then
          fornecedores.CNPJ := TXTCNPJ.TEXT
       else
       ShowMessage('Favor marque uma das opções ||FISICA||JURIDICA||');
     end;


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
    function TForm3.POPULARTXT():Boolean;
    BEGIN
       ActResultEdit1.TEXT := IntToStr(fornecedores.COD_PART);
       TXTRAZAO.TEXT       := fornecedores.NOME ;
       TXTCOD_PAIS.TEXT    := IntToStr(fornecedores.COD_PAIS);
       TXTIE.TEXT          := fornecedores.IE ;
       TXTCODIGOMUNICIPAL.TEXT:= IntToStr(fornecedores.COD_MUN);
       TXTSUFRAMA.TEXT := fornecedores.SUFRAMA ;
       TXTLOGRADOURO.TEXT := fornecedores.ENDE;
       TXTCEP.TEXT := IntToStr(fornecedores.Cep);
       txtnum.TEXT := IntToStr(fornecedores.Num);
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

     if (CBFISICA.Checked = True )then
     fornecedores.CPF := TXTCNPJ.TEXT
     else
     begin
       if (CBJURIDICA.Checked = True) then
          fornecedores.CNPJ := TXTCNPJ.TEXT
       else
       ShowMessage('Favor marque uma das opções ||FISICA||JURIDICA||');
     end;


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
    end;
    function TForm3.POPULARCLASSE_BD():Boolean;
    BEGIN


     fornecedores.COD_PART := IbQuery1.FieldByName('COD_PART').AsInteger ;
     fornecedores.NOME     := IbQuery1.FieldByName('NOME').AsString ;
     fornecedores.COD_PAIS := IbQuery1.FieldByName('COD_PAIS').AsInteger ;
     fornecedores.IE       := IbQuery1.FieldByName('IE').AsString ;
     fornecedores.COD_MUN  := IbQuery1.FieldByName('COD_MUN').AsInteger ;
     fornecedores.SUFRAMA  := IbQuery1.FieldByName('SUFRAMA').AsString ;
     fornecedores.ENDE     := IbQuery1.FieldByName('ENDE').AsString ;
     fornecedores.Cep      := IbQuery1.FieldByName('CEP').AsInteger ;
     fornecedores.Num      := IbQuery1.FieldByName('NUM').AsInteger ;
     fornecedores.COMPL    := IbQuery1.FieldByName('COMPL').AsString ;
     fornecedores.BAIRRO   := IbQuery1.FieldByName('BAIRRO').AsString ;
     fornecedores.CLIENTE  := IbQuery1.FieldByName('CLIENTE').AsString ;
     fornecedores.FORNECEDOR  := IbQuery1.FieldByName('FORNECEDOR').AsString ;
     fornecedores.representante := IbQuery1.FieldByName('REPRESENTANTE').AsString ;
     fornecedores.transportador := IbQuery1.FieldByName('TRANSPORTADOR').AsString ;
    // fornecedores.
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

     if (CBFISICA.Checked = True )then
     fornecedores.CPF := TXTCNPJ.TEXT
     else
     begin
       if (CBJURIDICA.Checked = True) then
          fornecedores.CNPJ := TXTCNPJ.TEXT
       else
       ShowMessage('Favor marque uma das opções ||FISICA||JURIDICA||');
     end;


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
    end;


procedure TForm3.CDREPRESENTANTEClick(Sender: TObject);
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

procedure TForm3.FormCreate(Sender: TObject);
begin
  inherited;
  {
fornecedores := Tfornecedores.Create;
SelectInicial();
POPULARCLASSE_BD();
POPULARTXT();   }

end;

end.
