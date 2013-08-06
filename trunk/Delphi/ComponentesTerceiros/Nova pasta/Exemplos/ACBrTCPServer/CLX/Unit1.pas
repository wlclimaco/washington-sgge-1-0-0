unit Unit1;

interface

uses
  SysUtils, Types, Classes, Variants, QTypes, QGraphics, QControls, QForms, 
  QDialogs, QStdCtrls, ACBrBase, ACBrSocket, blcksock, QExtCtrls, QCheckLst;

type
  TForm1 = class(TForm)
    ACBrTCPServer1: TACBrTCPServer;
    Panel1: TPanel;
    Panel2: TPanel;
    mOutput: TMemo;
    Panel3: TPanel;
    CheckListBox1: TCheckListBox;
    Button1: TButton;
    bEnviar: TButton;
    Panel4: TPanel;
    Label1: TLabel;
    edPorta: TEdit;
    Label4: TLabel;
    edTerm: TEdit;
    Label5: TLabel;
    edTimeOut: TEdit;
    bDesativar: TButton;
    bAtivar: TButton;
    Label3: TLabel;
    lNConexoes: TLabel;
    edEnviar: TEdit;
    Label2: TLabel;
    procedure bAtivarClick(Sender: TObject);
    procedure bDesativarClick(Sender: TObject);
    procedure ACBrTCPServer1Conecta(const TCPBlockSocket: TTCPBlockSocket;
       var Enviar: String);
    procedure ACBrTCPServer1RecebeDados(const TCPBlockSocket: TTCPBlockSocket;
       Recebido: String; var Enviar: String);
    procedure Label3Click(Sender: TObject);
    procedure ACBrTCPServer1DesConecta(
      const TCPBlockSocket: TTCPBlockSocket; Erro: Integer;
      ErroDesc: String);
    procedure bEnviarClick(Sender: TObject);
    procedure Button1Click(Sender: TObject);
  private
    procedure ExibirConexoes;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.xfm}

procedure TForm1.ExibirConexoes;
 Var I : Integer ;
begin
  CheckListBox1.Items.Clear ;
  For I := 0 to ACBrTCPServer1.ThreadList.Count - 1 do
  begin
     if ACBrTCPServer1.ThreadList[I].Active then
        CheckListBox1.Items.Add( 'IP: '+ACBrTCPServer1.ThreadList[I].TCPBlockSocket.GetRemoteSinIP +' - '+
          IntToStr( ACBrTCPServer1.ThreadList[I].TCPBlockSocket.GetRemoteSinPort ) ) ;
  end ;

  lNConexoes.Caption := IntToStr(CheckListBox1.Items.Count) ;
  Application.ProcessMessages ;
end ;

procedure TForm1.bAtivarClick(Sender: TObject);
begin
  ACBrTCPServer1.Port       := edPorta.Text ;
  ACBrTCPServer1.Terminador := edTerm.Text ;
  ACBrTCPServer1.TimeOut    := StrToInt(edTimeOut.Text) ;
  ACBrTCPServer1.Ativar ;
  mOutput.Lines.Add('Escutando a porta: '+edPorta.Text ) ;
end;

procedure TForm1.bDesativarClick(Sender: TObject);
begin
  ACBrTCPServer1.Desativar ;
  mOutput.Lines.Add('Desativado') ;
end;

procedure TForm1.ACBrTCPServer1Conecta(
  const TCPBlockSocket: TTCPBlockSocket; var Enviar: String);
begin
  mOutput.Lines.Add('Conex�o estabelecida de: ' + TCPBlockSocket.GetRemoteSinIP ) ;
  Enviar := 'Seja bem vindo' + #3 ;
  ExibirConexoes ;
end;

procedure TForm1.ACBrTCPServer1RecebeDados(const TCPBlockSocket: TTCPBlockSocket;
      Recebido: String; var Enviar: String) ;
begin
  mOutput.Lines.Add( Recebido ) ;
  Enviar := edEnviar.Text + #3 ;
end;

procedure TForm1.Label3Click(Sender: TObject);
begin
  ExibirConexoes ;
end;

procedure TForm1.ACBrTCPServer1DesConecta(
  const TCPBlockSocket: TTCPBlockSocket; Erro: Integer; ErroDesc: String);
begin
  mOutput.Lines.Add( 'Conex�o terminada. Erro:' + IntToStr(Erro) + ' - ' + ErroDesc ) ;
  ExibirConexoes ;
end;

procedure TForm1.bEnviarClick(Sender: TObject);
 Var I : Integer ;
     Selecionado : Boolean ;
begin
  Selecionado := False ;
  For I := 0 to CheckListBox1.Items.Count-1 do
  begin
     if CheckListBox1.Checked[I] then
     begin
        Selecionado := True ;
        ACBrTCPServer1.EnviarString( edEnviar.Text + #3, I );
     end ;
  end ;

  if not Selecionado then
     ACBrTCPServer1.EnviarString( edEnviar.Text + #3, -1 );  // -1 envia para Todas conexoes
end;

procedure TForm1.Button1Click(Sender: TObject);
 Var I : Integer ;
begin
  For I := 0 to CheckListBox1.Items.Count-1 do
  begin
     if CheckListBox1.Checked[I] then
        ACBrTCPServer1.ThreadList[I].Terminate ;
  end ;
end;

end.
