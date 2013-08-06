{$I ACBr.inc}

unit BalancaTeste1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ExtCtrls, StdCtrls, ACBrBase, ACBrBAL, ACBrDevice;

type
  TForm1 = class(TForm)
    ACBrBAL1: TACBrBAL;
    btnConectar: TButton;
    btnDesconectar: TButton;
    btnLerPeso: TButton;
    sttPeso: TStaticText;
    sttResposta: TStaticText;
    Label2: TLabel;
    Label3: TLabel;
    btnSair: TButton;
    edtTimeOut: TEdit;
    Label9: TLabel;
    chbMonitorar: TCheckBox;
    Label10: TLabel;
    Memo1: TMemo;
    Panel1: TPanel;
    Label1: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    cmbBalanca: TComboBox;
    cmbPortaSerial: TComboBox;
    cmbBaudRate: TComboBox;
    cmbDataBits: TComboBox;
    cmbHandShaking: TComboBox;
    cmbParity: TComboBox;
    Label11: TLabel;
    cmbStopBits: TComboBox;
    procedure btnConectarClick(Sender: TObject);
    procedure btnLerPesoClick(Sender: TObject);
    procedure btnSairClick(Sender: TObject);
    procedure btnDesconectarClick(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure edtTimeOutKeyPress(Sender: TObject; var Key: Char);
    procedure chbMonitorarClick(Sender: TObject);
    procedure ACBrBAL1LePeso(Peso: Double; Resposta: String);
  private
    { Private declarations }
    Function Converte( cmd : String) : String;
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.dfm}


function TForm1.Converte(cmd: String): String;
var A : Integer ;
begin
  Result := '' ;
  For A := 1 to length( cmd ) do
  begin
     if not (cmd[A] in ['A'..'Z','a'..'z','0'..'9',
                        ' ','.',',','/','?','<','>',';',':',']','[','{','}',
                        '\','|','=','+','-','_',')','(','*','&','^','%','$',
                        '#','@','!','~',']' ]) then
        Result := Result + '#' + IntToStr(ord( cmd[A] )) + ' '
     else
        Result := Result + cmd[A] + ' ';
  end ;
end;

procedure TForm1.btnConectarClick(Sender: TObject);
begin
   // se houver conec��o aberta, Fecha a conec��o
   if acbrBal1.Ativo then
      ACBrBAL1.Desativar;

   // configura porta de comunica��o
   ACBrBAL1.Modelo           := TACBrBALModelo( cmbBalanca.ItemIndex );
   ACBrBAL1.Device.HandShake := TACBrHandShake( cmbHandShaking.ItemIndex );
   ACBrBAL1.Device.Parity    := TACBrSerialParity( cmbParity.ItemIndex );
   ACBrBAL1.Device.Stop      := TACBrSerialStop( cmbStopBits.ItemIndex );
   ACBrBAL1.Device.Data      := StrToInt( cmbDataBits.text );
   ACBrBAL1.Device.Baud      := StrToInt( cmbBaudRate.Text );
   ACBrBAL1.Device.Porta     := cmbPortaSerial.Text;

   // Conecta com a balan�a
   ACBrBAL1.Ativar;

   btnConectar.Enabled    := false;
   Panel1.Enabled         := false;
   btnDesconectar.Enabled := true;
   btnLerPeso.Enabled     := true;
end;

procedure TForm1.btnLerPesoClick(Sender: TObject);
Var TimeOut : Integer ;
begin
   try
      TimeOut := StrToInt( edtTimeOut.Text ) ;
   except
      TimeOut := 2000 ;
   end ;

   ACBrBAL1.LePeso( TimeOut );
end;

procedure TForm1.btnSairClick(Sender: TObject);
begin
   close;
end;

procedure TForm1.btnDesconectarClick(Sender: TObject);
begin
  ACBrBAL1.Desativar;

  btnConectar.Enabled    := True;
  Panel1.Enabled         := True;
  btnDesconectar.Enabled := False;
  btnLerPeso.Enabled     := False;
end;

procedure TForm1.FormClose(Sender: TObject; var Action: TCloseAction);
begin
  ACBrBAL1.Desativar ;
end;

procedure TForm1.edtTimeOutKeyPress(Sender: TObject; var Key: Char);
begin
  if not (Key in ['0'..'9',#13,#8]) then
     Key := #0 ;
end;

procedure TForm1.chbMonitorarClick(Sender: TObject);
begin
   ACBrBAL1.MonitorarBalanca := chbMonitorar.Checked ;
end;

procedure TForm1.ACBrBAL1LePeso(Peso: Double; Resposta: String);
var valid : integer;
begin
   sttPeso.Caption     := formatFloat('##0.000', Peso );
   sttResposta.Caption := Converte( Resposta ) ;

   if Peso > 0 then
      Memo1.Lines.Text := 'Leitura OK !'
   else
    begin
      valid := Trunc(ACBrBAL1.UltimoPesoLido);
      case valid of
         0 : Memo1.Lines.Text := 'TimeOut !'+sLineBreak+
                                 'Coloque o produto sobre a Balan�a!' ;
        -1 : Memo1.Lines.Text := 'Peso Instavel ! ' +sLineBreak+
                                 'Tente Nova Leitura' ;
        -2 : Memo1.Lines.Text := 'Peso Negativo !' ;
       -10 : Memo1.Lines.Text := 'Sobrepeso !' ;
      end;
    end ;
end;

end.
