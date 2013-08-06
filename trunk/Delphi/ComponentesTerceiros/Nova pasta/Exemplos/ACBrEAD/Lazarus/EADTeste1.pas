unit EADTeste1 ; 

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, Forms, Controls, Graphics, Dialogs, StdCtrls,
  Buttons, Menus, ACBrEAD ;

type

  { TForm1 }

  TForm1 = class(TForm)
     ACBrEAD1 : TACBrEAD ;
     btCalcEAD : TBitBtn ;
     btCalcMD5 : TBitBtn ;
     btAssinarArqEAD : TBitBtn ;
     btGerarXMLeECFc1 : TBitBtn ;
     btVerifArqAssinado : TBitBtn ;
     btGravarPubKey : TBitBtn ;
     btProcurarArqEntrada : TBitBtn ;
     btLerPubKey : TBitBtn ;
     btCalcPubKey : TBitBtn ;
     btGerarXMLeECFc : TBitBtn ;
     btNovoParChaves : TBitBtn ;
     btCalcModExp : TBitBtn ;
     btLerPrivKey : TBitBtn ;
     btGravarPrivKey : TBitBtn ;
     Button1 : TButton ;
     cbxDgst : TComboBox ;
     edArqPrivKey : TEdit ;
     edArqEntrada : TEdit ;
     edArqPubKey : TEdit ;
     gbPrivKey : TGroupBox ;
     gbArqEntrada : TGroupBox ;
     gbPubKey : TGroupBox ;
     GroupBox1 : TGroupBox ;
     Label1 : TLabel ;
     Label4 : TLabel ;
     Label5 : TLabel ;
     mPrivKey : TMemo ;
     mPubKey : TMemo ;
     mResp : TMemo ;
     OpenDialog1 : TOpenDialog ;
     SelectDirectoryDialog1 : TSelectDirectoryDialog ;
     procedure ACBrEAD1GetChavePrivada(var Chave : AnsiString) ;
     procedure ACBrEAD1GetChavePublica(var Chave : AnsiString) ;
     procedure btAssinarArqEADClick(Sender : TObject) ;
     procedure btCalcPubKeyClick(Sender : TObject) ;
     procedure btGerarXMLeECFc1Click(Sender : TObject) ;
     procedure btGravarPrivKeyClick(Sender : TObject) ;
     procedure btGravarPubKeyClick(Sender : TObject) ;
     procedure btLerPubKeyClick(Sender : TObject) ;
     procedure btNovoParChavesClick(Sender : TObject) ;
     procedure btLerPrivKeyClick(Sender : TObject) ;
     procedure btCalcEADClick(Sender : TObject) ;
     procedure btCalcMD5Click(Sender : TObject) ;
     procedure btCalcModExpClick(Sender : TObject) ;
     procedure btGerarXMLeECFcClick(Sender : TObject) ;
     procedure btProcurarArqEntradaClick(Sender : TObject) ;
     procedure btVerifArqAssinadoClick(Sender : TObject) ;
     procedure Button1Click(Sender : TObject) ;
     procedure FormCreate(Sender : TObject) ;
  private
    { private declarations }
  public
    { public declarations }
  end ; 

var
  Form1 : TForm1 ; 

implementation

Uses ACBrUtil ;

{$R *.lfm}

{ TForm1 }

procedure TForm1.btNovoParChavesClick(Sender : TObject) ;
Var
  ChavePublica, ChavePrivada : AnsiString ;
begin
  ChavePrivada := '' ;
  ChavePublica := '' ;

  ACBrEAD1.GerarChaves( ChavePublica, ChavePrivada );

  mPrivKey.Lines.Text := StringReplace( ChavePrivada, #10, sLineBreak, [rfReplaceAll] );
  mPubKey.Lines.Text  := StringReplace( ChavePublica, #10, sLineBreak, [rfReplaceAll] );

  if MessageDlg('Deseja gravar as chaves geradas ?',mtConfirmation,mbYesNoCancel,0) = mrYes then
  begin
     btGravarPrivKey.Click;
     btGravarPubKey.Click;
  end ;
end;

procedure TForm1.btLerPrivKeyClick(Sender : TObject) ;
begin
  OpenDialog1.FileName := edArqPrivKey.Text;
  if OpenDialog1.Execute then
  begin
     edArqPrivKey.Text := OpenDialog1.FileName;
     mPrivKey.Lines.LoadFromFile( edArqPrivKey.Text );
  end ;
end;

procedure TForm1.btCalcEADClick(Sender : TObject) ;
begin
   mResp.Lines.Add('Calculando o EAD do arquivo: '+edArqEntrada.Text );
   mResp.Lines.Add('EAD = '+ ACBrEAD1.CalcularEADArquivo( edArqEntrada.Text ) );
   mResp.Lines.Add('------------------------------');
end;

procedure TForm1.btCalcMD5Click(Sender : TObject) ;
begin
   mResp.Lines.Add('Calculando o HASH - "'+cbxDgst.Text+'" do arquivo: '+edArqEntrada.Text );
   mResp.Lines.Add(UpperCase(cbxDgst.Text)+' = '+  ACBrEAD1.CalcularHashArquivo( edArqEntrada.Text, TACBrEADDgst( cbxDgst.ItemIndex ) ) );
   mResp.Lines.Add('------------------------------');
end;

procedure TForm1.btCalcModExpClick(Sender : TObject) ;
Var
  Modulo, Expoente : AnsiString ;
begin
   Modulo   := '';
   Expoente := '';
   ACBrEAD1.CalcularModuloeExpoente( Modulo, Expoente );

   mResp.Lines.Add('Modulo');
   mResp.Lines.Add( Modulo );
   mResp.Lines.Add('');

   mResp.Lines.Add('Expoente');
   mResp.Lines.Add( Expoente );
   mResp.Lines.Add('------------------------------');
end;

procedure TForm1.btGerarXMLeECFcClick(Sender : TObject) ;
var
   NomeSH, ArqXML : String ;
begin
  NomeSH := 'Sua SoftwareHouse' ;
  if not InputQuery('Sw.House','Entre com o nome da Sw.House', NomeSH ) then
     exit ;

  if SelectDirectoryDialog1.Execute then
  begin
     ArqXML := PathWithDelim(SelectDirectoryDialog1.FileName) + NomeSH + '.xml' ;
     if FileExists( ArqXML )  then
        if MessageDlg( 'Arquivo já existe, sobrescrever ?',
                       mtConfirmation, mbYesNoCancel, 0) <> mrYes then
           exit ;

     if ACBrEAD1.GerarXMLeECFc( NomeSH, SelectDirectoryDialog1.FileName ) then
        MessageDlg('Arquivo: '+ArqXML+' criado', mtInformation, [mbOK], 0 );
  end ;
end;

procedure TForm1.btProcurarArqEntradaClick(Sender : TObject) ;
begin
   OpenDialog1.FileName := edArqEntrada.Text;
   if OpenDialog1.Execute then
      edArqEntrada.Text := OpenDialog1.FileName;
end;

procedure TForm1.btVerifArqAssinadoClick(Sender : TObject) ;
begin
   mResp.Lines.Add('Verificando a Assinatura do arquivo: '+edArqEntrada.Text+' usando a Chave Pública' );
   if ACBrEAD1.VerificarEADArquivo( edArqEntrada.Text ) then
      mResp.Lines.Add( 'Verificação OK'  )
   else
    begin
      mResp.Lines.Add( 'Falha na Verificação... Verifique:' ) ;
      mResp.Lines.Add( '- A Chave Pública é par da Chave Privada do responsável pela Assinatura do Arquivo ?' ) ;
      mResp.Lines.Add( '- O EAD foi calculado corretamente (por outro programa) ? ' ) ;
    end ;

   mResp.Lines.Add('------------------------------');
end;

procedure TForm1.Button1Click(Sender : TObject) ;
begin
   mResp.Lines.Add('Versão de SSLeay');
   mResp.Lines.Add( ACBrEAD1.OpenSSL_Version );
   mResp.Lines.Add('------------------------------');
end;

procedure TForm1.FormCreate(Sender : TObject) ;
Var
  Dir : String ;
begin
   Dir := ExtractFilePath( Application.ExeName ) ;

   edArqPrivKey.Text := Dir + 'priv_key.pem' ;
   edArqPubKey.Text  := Dir + 'pub_key.pem' ;

   if FileExists( edArqPrivKey.Text ) then
      mPrivKey.Lines.LoadFromFile( edArqPrivKey.Text );

   if FileExists( edArqPubKey.Text ) then
      mPubKey.Lines.LoadFromFile( edArqPubKey.Text );
end;

procedure TForm1.ACBrEAD1GetChavePrivada(var Chave : AnsiString) ;
begin
  mResp.Lines.Add('  Lendo Chave Privada');
  Chave := mPrivKey.Lines.Text;
end;

procedure TForm1.ACBrEAD1GetChavePublica(var Chave : AnsiString) ;
begin
  mResp.Lines.Add('  Lendo Chave Pública');
  Chave := mPubKey.Lines.Text;
end;

procedure TForm1.btAssinarArqEADClick(Sender : TObject) ;
var
   EAD : String ;
begin
   mResp.Lines.Add('Assinando o arquivo: '+edArqEntrada.Text+' com o registro EAD' );
   EAD :=  ACBrEAD1.AssinarArquivoComEAD( edArqEntrada.Text, True ) ;
   mResp.Lines.Add('Arquivo alterado. Adicionado no final do arquivo a linha: ' );
   mResp.Lines.Add('EAD'+EAD);
   mResp.Lines.Add('------------------------------');
end;

procedure TForm1.btCalcPubKeyClick(Sender : TObject) ;
var
   Chave : AnsiString ;
begin
   mResp.Lines.Add('Calculando Chave Pública através da Chave Privada');
   Chave := ACBrEAD1.CalcularChavePublica;
   Chave := StringReplace( Chave, #10, sLineBreak, [rfReplaceAll] );;
   mResp.Lines.Add('Chave Pública Calculada:');
   mResp.Lines.Add(Chave);
   mResp.Lines.Add('------------------------------');

   mPubKey.Lines.Text := Chave;
end;

procedure TForm1.btGerarXMLeECFc1Click(Sender : TObject) ;
var
  ChavePub : String ;
begin
  OpenDialog1.Filter := 'xml|*.xml';
  if OpenDialog1.Execute then
  begin
     ChavePub := ACBrEAD1.ConverteXMLeECFcParaOpenSSL( OpenDialog1.FileName ) ;
     ChavePub := StringReplace( ChavePub, #10, sLineBreak, [rfReplaceAll] );;

     mResp.Lines.Add('Convertento a Chave Publica do arquivo: '+OpenDialog1.FileName );
     mResp.Lines.Add( ChavePub );
     mResp.Lines.Add('------------------------------');
  end ;
  OpenDialog1.Filter := '';
end;

procedure TForm1.btGravarPrivKeyClick(Sender : TObject) ;
begin
   if (edArqPrivKey.Text <> '') and (mPrivKey.Lines.Count > 0) then
   begin
      if FileExists( edArqPrivKey.Text )  then
         if MessageDlg( 'Arquivo já existe, sobrescrever ?',
                        mtConfirmation, mbYesNoCancel, 0) <> mrYes then
            exit ;

      mPrivKey.Lines.SaveToFile( edArqPrivKey.Text );
      MessageDlg( 'Chave Privada salva como:'+sLineBreak+edArqPrivKey.Text,
                  mtInformation, [mbOK], 0 );
   end ;
end;

procedure TForm1.btGravarPubKeyClick(Sender : TObject) ;
begin
   if (edArqPubKey.Text <> '') and (mPubKey.Lines.Count > 0) then
   begin
      if FileExists( edArqPubKey.Text )  then
         if MessageDlg( 'Arquivo já existe, sobrescrever ?',
                        mtConfirmation, mbYesNoCancel, 0) <> mrYes then
            exit ;

      mPubKey.Lines.SaveToFile( edArqPubKey.Text );
      MessageDlg('Chave Pública salva como:'+sLineBreak+edArqPubKey.Text,
                  mtInformation, [mbOK], 0 );
   end ;
end;

procedure TForm1.btLerPubKeyClick(Sender : TObject) ;
begin
   OpenDialog1.FileName := edArqPubKey.Text;
   if OpenDialog1.Execute then
   begin
      edArqPubKey.Text := OpenDialog1.FileName;
      mPubKey.Lines.LoadFromFile( edArqPubKey.Text );
   end ;
end;

end.

