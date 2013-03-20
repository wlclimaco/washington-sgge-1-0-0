unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls,IniFiles, BrvCustomEdit, BrvEdit, ComCtrls, ExtCtrls, Buttons, BrvBitBtn, BrvSpeedButton,
  DB, DBClient, BrvClientDataSet, Grids, BrvDbGrids, BrvDbGrid, Menus;

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
    PopF013: TPopupMenu;
    odos1: TMenuItem;
    Nenhum1: TMenuItem;
    procedure SpeedButton1Click(Sender: TObject);
    procedure BtnPesquisaClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure odos1Click(Sender: TObject);
    procedure Nenhum1Click(Sender: TObject);
  private
    { Private declarations }
  public
    Function Explode(Texto, Separador : String) : String;
    procedure GravarConfiguracao;
    procedure LerConfiguracao;
  end;

var
  Form1: TForm1;
  arq: TextFile;
  F:TextFile;

implementation

uses BrvFuncoesXE,UBanco,UFEpas,UConf,UBE;

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

procedure TForm1.Nenhum1Click(Sender: TObject);
begin
      ClientDataSet1.First;

          while not ClientDataSet1.eof do
          begin
                ClientDataSet1.Edit;
                ClientDataSet1.FieldByName('S/N').AsString := 'N';
                ClientDataSet1.Post;
                ClientDataSet1.Next;
          end;
          ClientDataSet1.First;
end;

procedure TForm1.odos1Click(Sender: TObject);
begin
      ClientDataSet1.First;
      while not ClientDataSet1.eof do
      begin
            ClientDataSet1.Edit;
            ClientDataSet1.FieldByName('S/N').AsString := 'S';
            ClientDataSet1.Post;
            ClientDataSet1.Next;
      end;
      ClientDataSet1.First;
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
                          lDsLista.Insert(0,'S/N Nome Tipo ab ac aa bb cc dd ee');
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
