unit CEPTeste1 ;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, FileUtil, Forms, Controls, Graphics, Dialogs, StdCtrls,
  ComCtrls, ExtCtrls, ACBrCEP, ACBrSocket, ACBrIBGE ;

type

  { TForm1 }

  TForm1 = class(TForm)
    ACBrCEP1 : TACBrCEP ;
    ACBrIBGE1 : TACBrIBGE ;
    bBuscarCEP : TButton ;
    bBuscarCEP1 : TButton ;
    bBuscarLogradouro : TButton ;
    bBuscarLogradouro1 : TButton ;
    cbxWS : TComboBox ;
    edCEP : TEdit ;
    edIBGECod : TEdit ;
    edIBGENome : TEdit ;
    edLogradouro : TEdit ;
    edCidade : TEdit ;
    edPass: TEdit;
    edUser: TEdit;
    edTipo_Logradouro : TEdit ;
    edUF : TEdit ;
    edBairro : TEdit ;
    edChaveWS : TEdit ;
    edProxyHost : TEdit ;
    edProxyPass : TEdit ;
    edProxyPort : TEdit ;
    edProxyUser : TEdit ;
    GroupBox1 : TGroupBox ;
    GroupBox2 : TGroupBox ;
    GroupBox3 : TGroupBox ;
    GroupBox4 : TGroupBox ;
    GroupBox5 : TGroupBox ;
    GroupBox6 : TGroupBox ;
    Label1 : TLabel ;
    Label10 : TLabel ;
    Label11: TLabel;
    Label12: TLabel;
    Label2 : TLabel ;
    Label3 : TLabel ;
    Label4 : TLabel ;
    Label5 : TLabel ;
    Label6 : TLabel ;
    Label7 : TLabel ;
    Label8 : TLabel ;
    Label9 : TLabel ;
    Memo1 : TMemo ;
    PageControl1 : TPageControl ;
    TabSheet1 : TTabSheet ;
    TabSheet2 : TTabSheet ;
    tsIBGE : TTabSheet ;
    procedure ACBrCEP1AntesAbrirHTTP(var AURL : String) ;
    procedure ACBrCEP1BuscaEfetuada(Sender : TObject) ;
    procedure ACBrIBGE1AntesAbrirHTTP(var AURL : String) ;
    procedure ACBrIBGE1BuscaEfetuada(Sender : TObject) ;
    procedure bBuscarCEP1Click(Sender : TObject) ;
    procedure bBuscarCEPClick(Sender : TObject) ;
    procedure bBuscarLogradouro1Click(Sender : TObject) ;
    procedure bBuscarLogradouroClick(Sender : TObject) ;
    procedure cbxWSChange(Sender : TObject) ;
  private
    procedure AjustaProxy ;
    { private declarations }
  public
    { public declarations }
  end ; 

var
  Form1 : TForm1 ; 

implementation

{$R *.lfm}

{ TForm1 }

procedure TForm1.cbxWSChange(Sender : TObject) ;
begin
  ACBrCEP1.WebService := TACBrCEPWebService( cbxWS.ItemIndex ) ;
end;

procedure TForm1.AjustaProxy ;
begin
  cbxWSChange(Self);

  ACBrCEP1.ChaveAcesso := edChaveWS.Text;
  ACBrCEP1.Usuario     := edUser.Text;
  ACBrCEP1.Senha       := edPass.Text;

  ACBrCEP1.ProxyHost := edProxyHost.Text ;
  ACBrCEP1.ProxyPort := edProxyPort.Text ;
  ACBrCEP1.ProxyUser := edProxyUser.Text ;
  ACBrCEP1.ProxyPass := edProxyPass.Text ;

  ACBrIBGE1.ProxyHost := edProxyHost.Text ;
  ACBrIBGE1.ProxyPort := edProxyPort.Text ;
  ACBrIBGE1.ProxyUser := edProxyUser.Text ;
  ACBrIBGE1.ProxyPass := edProxyPass.Text ;
end ;

procedure TForm1.ACBrCEP1BuscaEfetuada(Sender : TObject) ;
var
  I : Integer ;
begin
  if ACBrCEP1.Enderecos.Count < 1 then
     Memo1.Lines.Add( 'Nenhum Endereço encontrado' )
  else
   begin
     Memo1.Lines.Add( IntToStr(ACBrCEP1.Enderecos.Count) + ' Endereço(s) encontrado(s)');
     Memo1.Lines.Add('');

     For I := 0 to ACBrCEP1.Enderecos.Count-1 do
     begin
       with ACBrCEP1.Enderecos[I] do
       begin
          Memo1.Lines.Add('CEP: '+CEP );
          Memo1.Lines.Add('Logradouro: '+Tipo_Logradouro+ ' ' +Logradouro );
          Memo1.Lines.Add('Complemento: '+Complemento);
          Memo1.Lines.Add('Bairro: '+Bairro );
          Memo1.Lines.Add('Municipio: '+Municipio + ' - IBGE: '+IBGE_Municipio);
          edCidade.Text := Municipio;
          Memo1.Lines.Add('UF: '+UF + ' - IBGE: '+IBGE_UF);
          Memo1.Lines.Add( StringOfChar('-',20) );
       end ;
     end ;
   end ;

  Memo1.Lines.Add('');
  Memo1.Lines.Add('Resposta HTTP:');
  Memo1.Lines.AddStrings( ACBrCEP1.RespHTTP );
end;

procedure TForm1.ACBrIBGE1AntesAbrirHTTP(var AURL : String) ;
begin
  Memo1.Lines.Clear;
  Memo1.Lines.Add('Efetuando consulta HTTP em:' ) ;
  Memo1.Lines.Add( AURL );
  Memo1.Lines.Add( '' );
end;

procedure TForm1.ACBrIBGE1BuscaEfetuada(Sender : TObject) ;
var
  I : Integer ;
begin
  if ACBrIBGE1.Cidades.Count < 1 then
     Memo1.Lines.Add( 'Nenhuma Cidade encontrada' )
  else
   begin
     Memo1.Lines.Add( IntToStr(ACBrIBGE1.Cidades.Count) + ' Cidade(s) encontrada(s)');
     Memo1.Lines.Add('');

     For I := 0 to ACBrIBGE1.Cidades.Count-1 do
     begin
       with ACBrIBGE1.Cidades[I] do
       begin
          Memo1.Lines.Add('Cod UF: '+IntToStr(CodUF) );
          Memo1.Lines.Add('UF: '+UF);
          Memo1.Lines.Add('Cod.Município: '+IntToStr(CodMunicio) );
          Memo1.Lines.Add('Município: '+Municipio );
          Memo1.Lines.Add('Área: '+FormatFloat('0.00', Area) );
          Memo1.Lines.Add( StringOfChar('-',20) );
       end ;
     end ;
   end ;

  Memo1.Lines.Add('');
  Memo1.Lines.Add('Resposta HTTP:');
  Memo1.Lines.AddStrings( ACBrIBGE1.RespHTTP );
end;

procedure TForm1.bBuscarCEP1Click(Sender : TObject) ;
begin
  AjustaProxy ;

  try
     ACBrIBGE1.BuscarPorCodigo( StrToIntDef(edIBGECod.Text,0) );
  except
     On E : Exception do
     begin
        Memo1.Lines.Add(E.Message);
     end ;
  end ;
end;

procedure TForm1.ACBrCEP1AntesAbrirHTTP(var AURL : String) ;
begin
  Memo1.Lines.Clear;
  Memo1.Lines.Add('Efetuando consulta HTTP em:' ) ;

  Memo1.Lines.Add( AURL );
  Memo1.Lines.Add( '' );
end;

procedure TForm1.bBuscarCEPClick(Sender : TObject) ;
begin
  AjustaProxy ;

  try
     ACBrCEP1.BuscarPorCEP(edCEP.Text);
  except
     On E : Exception do
     begin
        Memo1.Lines.Add(E.Message);
     end ;
  end ;
end;

procedure TForm1.bBuscarLogradouro1Click(Sender : TObject) ;
begin
  AjustaProxy ;

  try
     ACBrIBGE1.BuscarPorNome( edIBGENome.Text );
  except
     On E : Exception do
     begin
        Memo1.Lines.Add(E.Message);
     end ;
  end ;
end;

procedure TForm1.bBuscarLogradouroClick(Sender : TObject) ;
begin
  AjustaProxy ;

  try
     ACBrCEP1.BuscarPorLogradouro( edCidade.Text, edTipo_Logradouro.Text,
                                   edLogradouro.Text, edUF.Text, edBairro.Text);
  except
     On E : Exception do
     begin
        Memo1.Lines.Add(E.Message);
     end ;
  end ;
end;

end.

