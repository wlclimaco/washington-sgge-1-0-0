unit Unit7;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Unit2, Buttons, ExtCtrls, DB, IBCustomDataSet, IBQuery,
  StdCtrls, ActMask, ActMaskEdit, ActEdit, ActCustomEdit, ActNumberEdit,UBDLFornecedores;

type
  TModeloForm7 = class(TModeloForm)
    IBQuery1: TIBQuery;
    Label1: TLabel;
    DataSource1: TDataSource;
    Label4: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    Label14: TLabel;
    Label15: TLabel;
    Label16: TLabel;
    Label17: TLabel;
    Label18: TLabel;
    Label19: TLabel;
    Label20: TLabel;
    Label21: TLabel;
    TXTCDFORNECEDOR: TActNumberEdit;
    TXTRAZAO: TActEdit;
    TXTSIGLA: TActEdit;
    TXTFANTASIA: TActEdit;
    TXTCGC: TActMaskEdit;
    TXTINCR: TActMaskEdit;
    TXTENDERECO: TActEdit;
    TXTBAIRRO: TActEdit;
    TXTMUNICIPIO: TActEdit;
    TXTUF: TActEdit;
    TXTCEP: TActMaskEdit;
    TXTFONE: TActMaskEdit;
    TXTFAX: TActMaskEdit;
    TXTFONECEL: TActMaskEdit;
    TXTCONTATO: TActEdit;
    TXTEMAIL: TActEdit;
    TXTSITE: TActEdit;
    TXTOBS: TActEdit;
    GroupBox1: TGroupBox;
    RadioButton1: TRadioButton;
    RadioButton2: TRadioButton;
    GroupBox2: TGroupBox;
    TXTREPRESENTANTE: TActEdit;
    TXTFONEREPRE: TActEdit;
    TXTINTERVALO: TActEdit;
    IBQuery2: TIBQuery;
    IBQuery3: TIBQuery;
    IBQuery4: TIBQuery;
    procedure SpeedButton7Click(Sender: TObject);
    procedure SpeedButton8Click(Sender: TObject);
    procedure SpeedButton5Click(Sender: TObject);
    procedure SpeedButton6Click(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure SpeedButton4Click(Sender: TObject);
    procedure SpeedButton9Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
   function PopularTxt():Boolean;
   function PopulaClasse():Boolean;
   function SelectInicial ():boolean;
   function GravarFornecedores ():boolean;
   function AlterarFornecedores ():boolean;
   function Excluir (CDFORNECEDOR:iNTEGER):boolean;
   function BuscarFornecedores ():boolean;
   function limparFornecedores():boolean;
   function Primeiro ():boolean;
   function Ultimo ():boolean;
   function Proximo ():boolean;
   function Anterior ():boolean;
   function Atualiza():boolean;
   function ConsultaFornecedor():boolean;
   function PopulaBanco():boolean;
  end;

var
  ModeloForm7: TModeloForm7;
  Fornecedores  : TFornecedores;
implementation

uses Unit1, Unit82;
 function TModeloForm7.PopulaBanco():boolean;
 begin
      Fornecedores := TFornecedores.Create;
      {Fornecedores.CDFORNECEDOR := IBQUERY1.fieldByName('CDFORNECEDOR').asInteger;
      Fornecedores.CEP          := IBQUERY1.fieldByName('CEP').asSTRING;
      Fornecedores.STATUS       := IBQUERY1.fieldByName('STATUS').AsString;
      Fornecedores.RAZAO        := IBQUERY1.fieldByName('RAZAO').AsString;
      Fornecedores.SIGLA        := IBQUERY1.fieldByName('SIGLA').AsString;
      Fornecedores.FANTASIA     := IBQUERY1.fieldByName('FANTASIA').AsString;
      Fornecedores.CGC          := IBQUERY1.fieldByName('CGC').AsString;
      Fornecedores.INCR         := IBQUERY1.fieldByName('INCR').AsString;
      Fornecedores.ENDe         := IBQUERY1.fieldByName('ENDERECO').AsString;
      Fornecedores.BAIRRO       := IBQUERY1.fieldByName('BAIRRO').AsString;
      Fornecedores.MUNICIPIO    := IBQUERY1.fieldByName('MUNICIPIO').AsString;
      Fornecedores.UF           := IBQUERY1.fieldByName('UF').AsString;
      Fornecedores.TELEFONE     := IBQUERY1.fieldByName('FONE').AsString;
      Fornecedores.REPRESENTANTE:= IBQUERY1.fieldByName('REPRESENTANTE').AsString;
      Fornecedores.TELREPRE     := IBQUERY1.fieldByName('TelREPRESENTANTE').AsString;
      Fornecedores.CEL          := IBQUERY1.fieldByName('FONECEL').AsString;
      Fornecedores.Fax          := IBQUERY1.fieldByName('FONEFAX').AsString;
      Fornecedores.CONTATO      := IBQUERY1.fieldByName('CONTATO').AsString;
      Fornecedores.EMAIL        := IBQUERY1.fieldByName('EMAIL').AsString;
      Fornecedores.SITE         := IBQUERY1.fieldByName('SITE').AsString;
      Fornecedores.OBS          := IBQUERY1.fieldByName('OBS').AsString;  }
 end;


   function TModeloForm7.PopularTxt():Boolean;
   Begin
    {
    TXTCDFORNECEDOR.text := IntToStr(Fornecedores.COD_PART);
    TXTCEP.Text := IntToStr(Fornecedores.CEP;
    If(Fornecedores.STATUS = 'A')then
        RadioButton1.Checked := True
    else
        RadioButton2.Checked  := True;
    TXTRAZAO.Text := Fornecedores.RAZAO;
    TXTSIGLA.Text := Fornecedores.SIGLA;
    TXTFANTASIA.Text := Fornecedores.FANTASIA;
    TXTCGC.Text := Fornecedores.CGC;
    TXTINCR.Text := Fornecedores.INCR;
    TXTENDERECO.Text := Fornecedores.ENDe;
    TXTBAIRRO.Text := Fornecedores.BAIRRO;
    TXTMUNICIPIO.Text := Fornecedores.MUNICIPIO;
    TXTUF.Text := Fornecedores.UF;
    TXTFONE.Text := Fornecedores.TELEFONE;
    TXTREPRESENTANTE.Text := Fornecedores.REPRESENTANTE;
    TXTFONEREPRE.Text := Fornecedores.TELREPRE;
    TXTFONECEL.Text :=  Fornecedores.CEL;
    TXTFAX.Text := Fornecedores.Fax;
    TXTCONTATO.Text := Fornecedores.CONTATO;
    TXTEMAIL.Text := Fornecedores.EMAIL;
    TXTSITE.Text := Fornecedores.SITE;
    TXTOBS.Text := Fornecedores.OBS;  }

   end;
   function TModeloForm7.PopulaClasse():Boolean;
   begin
     { Fornecedores := TFornecedores.Create;
      Fornecedores.CDFORNECEDOR := StrToInt(TXTCDFORNECEDOR.text);
      Fornecedores.CEP          := TXTCEP.Text;
      Fornecedores.STATUS       := 'A';
      Fornecedores.RAZAO        := TXTRAZAO.Text;
      Fornecedores.SIGLA        := TXTSIGLA.Text;
      Fornecedores.FANTASIA     := TXTFANTASIA.Text;
      Fornecedores.CGC          := TXTCGC.Text;
      Fornecedores.INCR         := TXTINCR.Text;
      Fornecedores.ENDe         := TXTENDERECO.Text;
      Fornecedores.BAIRRO       := TXTBAIRRO.Text;
      Fornecedores.MUNICIPIO    := TXTMUNICIPIO.Text;
      Fornecedores.UF           := TXTUF.Text;
      Fornecedores.TELEFONE     := TXTFONE.Text;
      Fornecedores.REPRESENTANTE:= TXTREPRESENTANTE.Text;
      Fornecedores.TELREPRE     := TXTFONEREPRE.Text;
      Fornecedores.CEL          := TXTFONECEL.Text;
      Fornecedores.Fax          := TXTFAX.Text;
      Fornecedores.CONTATO      := TXTCONTATO.Text;
      Fornecedores.EMAIL        := TXTEMAIL.Text;
      Fornecedores.SITE         := TXTSITE.Text;
      Fornecedores.DTCADASTRO   := NOW;
      Fornecedores.OBS          := TXTOBS.Text;  }
   end;
   function TModeloForm7.SelectInicial ():boolean;
   begin
      IBQuery1.Active := fALSE;
      IBQuery1.SQL.Text := 'Select *from fornecedores';
      IBQuery1.Active := True;
      PopulaBanco();
      PopularTxt();
   end;
   function TModeloForm7.GravarFornecedores ():boolean;
   begin
     Try
     { IBQuery2.Active := False;
      IBQuery2.SQL.Text := 'INSERT INTO FORNECEDORES (CDFORNECEDOR, REPRESENTANTE, STATUS, RAZAO, TELREPRESENTANTE, SIGLA, FANTASIA, CGC, INCR, ENDERECO, BAIRRO, MUNICIPIO, UF, CEP, FONE, FONEFAX, FONECEL, CONTATO, EMAIL, SITE, PRAZOVENDEDOR) VALUES('+IntToStr(Fornecedores.CDFORNECEDOR)+', '''+Fornecedores.REPRESENTANTE+''', '''+Fornecedores.STATUS+''', '''+Fornecedores.RAZAO+''', '''+Fornecedores.TELREPRE+''', '''+Fornecedores.SIGLA+''', '''+Fornecedores.FANTASIA+''', '''+Fornecedores.CGC+''', '''+Fornecedores.INCR+''', '''+Fornecedores.ENDe+''', '''+Fornecedores.BAIRRO+''', '''+Fornecedores.MUNICIPIO+''', '''+Fornecedores.UF+''', '''+Fornecedores.CEP+''', '''+Fornecedores.TELEFONE+''', '''+Fornecedores.Fax+''', '''+Fornecedores.CEL+''', '''+Fornecedores.CONTATO+''', '''+Fornecedores.EMAIL+''', '''+Fornecedores.SITE+''',  '+Fornecedores.COM+')';
      IBQuery2.ExecSQL;
      IBQuery2.Transaction.Commit;  }
      Result := true;
      Except
      Result := False;
      end;
   end;
   function TModeloForm7.AlterarFornecedores ():boolean;
   begin
    IBQuery2.SQL.Clear;
    try
    {IBQuery2.SQL.Add('UPDATE FORNECEDORES SET');
    IBQuery2.SQL.Add('REPRESENTANTE = '''+Fornecedores.REPRESENTANTE+''',');
    IBQuery2.SQL.Add('STATUS = '''+Fornecedores.STATUS+''',');
    IBQuery2.SQL.Add('RAZAO = '''+Fornecedores.RAZAO+''',');
    IBQuery2.SQL.Add('TELREPRESENTANTE = '''+Fornecedores.TELREPRE+''',');
    IBQuery2.SQL.Add('SIGLA = '''+Fornecedores.SIGLA+''',');
    IBQuery2.SQL.Add('FANTASIA = '''+Fornecedores.FANTASIA+''',');
    IBQuery2.SQL.Add('CGC = '''+Fornecedores.CGC+''',');
    IBQuery2.SQL.Add('INCR = '''+Fornecedores.INCR+''',');
    IBQuery2.SQL.Add('ENDERECO = '''+Fornecedores.ENDe+''',');
    IBQuery2.SQL.Add('BAIRRO = '''+Fornecedores.BAIRRO+''',');
    IBQuery2.SQL.Add('MUNICIPIO = '''+Fornecedores.MUNICIPIO+''',');
    IBQuery2.SQL.Add('UF = '''+Fornecedores.UF+''',');
    IBQuery2.SQL.Add('CEP = '''+Fornecedores.CEP+''',');
    IBQuery2.SQL.Add('FONE = '''+Fornecedores.TELEFONE+''',');
    IBQuery2.SQL.Add('FONEFAX = '''+Fornecedores.Fax+''',');
    IBQuery2.SQL.Add('FONECEL = '''+Fornecedores.CEL+''',');
    IBQuery2.SQL.Add('CONTATO = '''+Fornecedores.CONTATO+''',');
    IBQuery2.SQL.Add('EMAIL = '''+Fornecedores.EMAIL+''',');
    IBQuery2.SQL.Add('SITE = '''+Fornecedores.SITE+''', ');
    IBQuery2.SQL.Add('OBS = '''+Fornecedores.OBS+''', ');
    IBQuery2.SQL.Add('PRAZOVENDEDOR = '''+Fornecedores.COM+''' ');
    IBQuery2.SQL.Add('WHERE CDFORNECEDOR = '+IntToStr(Fornecedores.cdfornecedor)+ '');
    IBQuery2.ExecSQL;
    IBQuery2.Transaction.Commit;}
    Result := true;
    Except
    Result := False;
    end;


   end;
   function TModeloForm7.Excluir (CDFORNECEDOR:INTEGER):boolean;
   begin
   TRY
    IBQuery3.Active := False;
    IBQuery3.SQL.Text := 'delete from fornecedores where cdfornecedor = '+IntToStr(CDFORNECEDOR);
    IBQuery3.ExecSQL;
    IBQuery3.Transaction.Commit;
    Result := true;
    Except
    Result := False;
   end;
   end;
   function TModeloForm7.BuscarFornecedores ():boolean;
   begin
      ibquery1.Active := fALSE;
      ibquery1.SQL.Add('select *from fornecedores where cdfornecedor > 0');
      IF(TXTCDFORNECEDOR.text <> '')THEN
      ibquery1.SQL.Add(' and CDFORNECEDOR =  '+TXTCDFORNECEDOR.text);
      IF(TXTCEP.Text          <> '')THEN
      ibquery1.SQL.Add(' and CEP =  '+TXTCEP.text);
      IF(TXTRAZAO.Text        <> '')THEN
      ibquery1.SQL.Add(' and RAZAO =  '+TXTRAZAO.text);
      IF(TXTSIGLA.Text        <> '')THEN
      ibquery1.SQL.Add(' and SIGLA =  '+TXTSIGLA.text);
      IF(TXTFANTASIA.Text     <> '')THEN
      ibquery1.SQL.Add(' and FANTASIA =  '+TXTFANTASIA.text);
      IF(TXTCGC.Text          <> '')THEN
      ibquery1.SQL.Add(' and CGC =  '+TXTCGC.text);
      IF(TXTINCR.Text         <> '')THEN
      ibquery1.SQL.Add(' and INCR =  '+TXTINCR.text);
      IF(TXTENDERECO.Text     <> '')THEN
      ibquery1.SQL.Add(' and ENDERECO=  '+TXTENDERECO.text);
      IF(TXTBAIRRO.Text       <> '')THEN
      ibquery1.SQL.Add(' and BAIRRO =  '+TXTBAIRRO.text);
      IF(TXTMUNICIPIO.Text    <> '')THEN
      ibquery1.SQL.Add(' and MUNICIPIO =  '+TXTMUNICIPIO.text);
      IF(TXTUF.Text           <> '')THEN
      ibquery1.SQL.Add(' and UF =  '+TXTUF.text);
      IF(TXTFONE.Text         <> '')THEN
      ibquery1.SQL.Add(' and FONE =  '+TXTFONE.text);
      IF(TXTREPRESENTANTE.Text <> '')THEN
      ibquery1.SQL.Add(' and REPRESENTANTE =  '+TXTREPRESENTANTE.text);
      IF(TXTFONEREPRE.Text    <> '')THEN
      ibquery1.SQL.Add(' and FONEREPRE. =  '+TXTFONEREPRE.text);
      IF(TXTFONECEL.Text      <> '')THEN
      ibquery1.SQL.Add(' and CEL =  '+TXTFONECEL.text);
      IF(TXTFAX.Text          <> '')THEN
      ibquery1.SQL.Add(' and FAX =  '+TXTFAX.text);
      IF(TXTCONTATO.Text      <> '')THEN
      ibquery1.SQL.Add(' and CONTATO =  '+TXTCONTATO.text);
      IF(TXTEMAIL.Text        <> '')THEN
      ibquery1.SQL.Add(' and EMAIL =  '+TXTEMAIL.text);
      IF(TXTSITE.Text         <> '')THEN
      ibquery1.SQL.Add(' and SITE =  '+TXTSITE.text);
      IF(TXTOBS.Text          <> '')THEN
      ibquery1.SQL.Add(' and OBS =  '+TXTOBS.text);
      ibquery1.Active := true;
      PopulaBanco();
      PopularTxt();
   end;
   function TModeloForm7.limparFornecedores():boolean;
   begin
      TXTCDFORNECEDOR.text    := '';
      TXTCEP.Text             := '';
      TXTRAZAO.Text           := '';
      TXTSIGLA.Text           := '';
      TXTFANTASIA.Text        := '';
      TXTCGC.Text             := '';
      TXTINCR.Text            := '';
      TXTENDERECO.Text        := '';
      TXTBAIRRO.Text          := '';
      TXTMUNICIPIO.Text       := '';
      TXTUF.Text              := '';
      TXTFONE.Text            := '';
      TXTREPRESENTANTE.Text   := '';
      TXTFONEREPRE.Text       := '';
      TXTFONECEL.Text         := '';
      TXTFAX.Text             := '';
      TXTCONTATO.Text         := '';
      TXTEMAIL.Text           := '';
      TXTSITE.Text            := '';
      TXTOBS.Text             := '';
   end;
   function TModeloForm7.Primeiro ():boolean;
   begin
         IBQuery1.First;
         PopulaBanco();
         PopularTxt();

   end;
   function TModeloForm7.Ultimo ():boolean;
   begin
          IBQuery1.Last;
          PopulaBanco();
          PopularTxt();
   end;
   function TModeloForm7.Proximo ():boolean;
   begin
         IBQuery1.Next;
         PopulaBanco();
         PopularTxt();
   end;
   function TModeloForm7.Anterior ():boolean;
   begin
        IBQuery1.Prior;
        PopulaBanco();
        PopularTxt();
   end;
   function TModeloForm7.Atualiza():boolean;
   begin
      IBQuery1.Active := True;
      IBQuery1.SQL.Text := 'Select *from fornecedores';
      IBQuery1.Active := False;
      PopulaBanco();
      PopularTxt();
   end;
   function TModeloForm7.ConsultaFornecedor():boolean;
   begin
   
   end;



{$R *.dfm}

procedure TModeloForm7.SpeedButton7Click(Sender: TObject);
begin
 Primeiro();
 PopularTxt();
end;

procedure TModeloForm7.SpeedButton8Click(Sender: TObject);
begin
 Ultimo();
 PopularTxt();

end;

procedure TModeloForm7.SpeedButton5Click(Sender: TObject);
begin
  Proximo();
 PopularTxt();

end;

procedure TModeloForm7.SpeedButton6Click(Sender: TObject);
begin
 Anterior();
 PopularTxt();

end;

procedure TModeloForm7.SpeedButton1Click(Sender: TObject);
begin
  limparFornecedores();

end;

procedure TModeloForm7.SpeedButton2Click(Sender: TObject);
begin
  AlterarFornecedores();

end;

procedure TModeloForm7.SpeedButton3Click(Sender: TObject);
begin
  PopulaClasse();
  GravarFornecedores();

end;

procedure TModeloForm7.SpeedButton4Click(Sender: TObject);
begin
  inherited;
if MessageDlg('Tem Certeza????', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
   Begin
    IF( Excluir(StrToInt(TXTCDFORNECEDOR.Text)) = True)Then
     ShowMessage('Fornecedor EXCLUIDO com SUCESSO !!');
   end;
end;

procedure TModeloForm7.SpeedButton9Click(Sender: TObject);
begin
 BuscarFornecedores();

end;

procedure TModeloForm7.FormCreate(Sender: TObject);
begin
  inherited;
    SelectInicial();
end;

end.
