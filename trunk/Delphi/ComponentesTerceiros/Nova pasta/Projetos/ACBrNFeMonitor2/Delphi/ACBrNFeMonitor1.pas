{******************************************************************************}
{ Projeto: ACBrNFeMonitor                                                        }
{  Executavel multiplataforma que faz uso do conjunto de componentes ACBr para }
{ criar uma interface de comunica��o com equipamentos de automacao comercial.  }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na p�gina do Projeto ACBr     }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
{                                                                              }
{  Este programa � software livre; voc� pode redistribu�-lo e/ou modific�-lo   }
{ sob os termos da Licen�a P�blica Geral GNU, conforme publicada pela Free     }
{ Software Foundation; tanto a vers�o 2 da Licen�a como (a seu crit�rio)       }
{ qualquer vers�o mais nova.                                                   }
{                                                                              }
{  Este programa � distribu�do na expectativa de ser �til, mas SEM NENHUMA     }
{ GARANTIA; nem mesmo a garantia impl�cita de COMERCIALIZA��O OU DE ADEQUA��O A}
{ QUALQUER PROP�SITO EM PARTICULAR. Consulte a Licen�a P�blica Geral GNU para  }
{ obter mais detalhes. (Arquivo LICENCA.TXT ou LICENSE.TXT)                    }
{                                                                              }
{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto com este}
{ programa; se n�o, escreva para a Free Software Foundation, Inc., 59 Temple   }
{ Place, Suite 330, Boston, MA 02111-1307, USA. Voc� tamb�m pode obter uma     }
{ copia da licen�a em:  http://www.opensource.org/licenses/gpl-license.php     }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}
{$I ACBr.inc}

unit ACBrNFeMonitor1;

interface

uses IniFiles, CmdUnitNFe, FileCtrl, Printers,
  IdBaseComponent, IdComponent, IdTCPServer,
  ShellAPI,                                { Unit para criar icone no Systray }
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ComCtrls, ExtCtrls, Buttons, Spin, Menus, ImgList,
  ACBrNFe, ACBrNFeDANFEClass, ACBrNFeDANFERave, pcnConversao, OleCtrls,
  SHDocVw, ACBrNFeUtil, ACBrDFeUtil, ACBrNFeDANFERaveCB, ACBrGIF,
  ACBrCTeDACTEClass, ACBrCTeDACTeQRClass, ACBrCTe;

const
   BufferMemoResposta = 10000 ;              { Maximo de Linhas no MemoResposta }
   _C = 'tYk*5W@' ;
   wm_IconMessage = wm_User;

{$I versao.txt}
type
  TfrmAcbrNfeMonitor = class(TForm)
    pPrincipal: TPanel;
    pBotoes: TPanel;
    pCmd: TPanel;
    pResp: TPanel;
    Splitter1: TSplitter;
    pTopCmd: TPanel;
    pTodResp: TPanel;
    sbProcessando: TStatusBar;
    mCmd: TMemo;
    mResp: TMemo;
    StatusBar1: TStatusBar;
    pmTray: TPopupMenu;
    Restaurar1: TMenuItem;
    Ocultar1: TMenuItem;
    N1: TMenuItem;
    EncerrarMonitor1: TMenuItem;
    Timer1: TTimer;
    TCPServer: TIdTCPServer;
    pConfig: TPanel;
    PageControl1: TPageControl;
    Monitor: TTabSheet;
    gbTCP: TGroupBox;
    Label12: TLabel;
    Label13: TLabel;
    edPortaTCP: TEdit;
    sedConexoesTCP: TSpinEdit;
    gbTXT: TGroupBox;
    Label14: TLabel;
    Label15: TLabel;
    Label16: TLabel;
    edEntTXT: TEdit;
    edSaiTXT: TEdit;
    sedIntervalo: TSpinEdit;
    gbSenha: TGroupBox;
    Label17: TLabel;
    edSenha: TEdit;
    gbLog: TGroupBox;
    Label18: TLabel;
    Label19: TLabel;
    sbLog: TSpeedButton;
    edLogArq: TEdit;
    sedLogLinhas: TSpinEdit;
    rbTCP: TRadioButton;
    cbSenha: TCheckBox;
    rbTXT: TRadioButton;
    cbLog: TCheckBox;
    Certificado: TTabSheet;
    gbxCertificado: TGroupBox;
    lblCaminho: TLabel;
    lblSenha: TLabel;
    sbArquivoCert: TSpeedButton;
    edtCaminho: TEdit;
    edtSenha: TEdit;
    WebService: TTabSheet;
    GroupBox4: TGroupBox;
    Label6: TLabel;
    sbPathSalvar: TSpeedButton;
    cbUF: TComboBox;
    ckSalvar: TCheckBox;
    edtPathLogs: TEdit;
    gbxProxy: TGroupBox;
    Label8: TLabel;
    Label9: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    edtProxyHost: TEdit;
    edtProxyPorta: TEdit;
    edtProxyUser: TEdit;
    edtProxySenha: TEdit;
    ACBrNFe1: TACBrNFe;
    btMinimizar: TBitBtn;
    btConfig: TBitBtn;
    btCancelar: TBitBtn;
    Image1: TImage;
    Email: TTabSheet;
    GroupBox1: TGroupBox;
    edtSmtpHost: TEdit;
    edtSmtpPort: TEdit;
    edtSmtpUser: TEdit;
    edtSmtpPass: TEdit;
    edtEmailAssunto: TEdit;
    Label20: TLabel;
    Label21: TLabel;
    Label22: TLabel;
    Label23: TLabel;
    Label24: TLabel;
    cbEmailSSL: TCheckBox;
    mmEmailMsg: TMemo;
    Label25: TLabel;
    OpenDialog1: TOpenDialog;
    ImageList1: TImageList;
    rgTipoAmb: TRadioGroup;
    DANFE: TTabSheet;
    Label27: TLabel;
    edtSoftwareHouse: TEdit;
    rgFormaEmissao: TRadioGroup;
    Testes: TTabSheet;
    Panel2: TPanel;
    WBResposta: TWebBrowser;
    Panel3: TPanel;
    ACBrNFeDANFERave1: TACBrNFeDANFERave;
    ACBrNFeDANFERaveCB1: TACBrNFeDANFERaveCB;
    Diretorios: TTabSheet;
    cbxSalvarArqs: TCheckBox;
    cbxPastaMensal: TCheckBox;
    cbxAdicionaLiteral: TCheckBox;
    edtPathNFe: TEdit;
    sbPathNFe: TSpeedButton;
    Label32: TLabel;
    Label33: TLabel;
    edtPathCan: TEdit;
    sbPathCan: TSpeedButton;
    Label34: TLabel;
    edtPathInu: TEdit;
    sbPathInu: TSpeedButton;
    Label35: TLabel;
    edtPathDPEC: TEdit;
    sbPathDPEC: TSpeedButton;
    Label28: TLabel;
    edtPathPDF: TEdit;
    sbPathPDF: TSpeedButton;
    PageControl2: TPageControl;
    TabSheet1: TTabSheet;
    TabSheet2: TTabSheet;
    Label5: TLabel;
    Label1: TLabel;
    rgTipoDanfe: TRadioGroup;
    cbxImpressora: TComboBox;
    cbxImpDescPorc: TCheckBox;
    cbxMostrarPreview: TCheckBox;
    edtNumCopia: TEdit;
    gbxMargem: TGroupBox;
    Label2: TLabel;
    Label29: TLabel;
    Label30: TLabel;
    Label31: TLabel;
    edtMargemInf: TEdit;
    edtMargemSup: TEdit;
    edtMargemDir: TEdit;
    edtMargemEsq: TEdit;
    rgModeloDanfe: TRadioGroup;
    cbxExibeResumo: TCheckBox;
    cbxImpValLiq: TCheckBox;
    Label3: TLabel;
    Label4: TLabel;
    Label26: TLabel;
    edtSiteEmpresa: TEdit;
    edtEmailEmpresa: TEdit;
    edtFaxEmpresa: TEdit;
    Label7: TLabel;
    edtLogoMarca: TEdit;
    sbLogoMarca: TSpeedButton;
    cbxFormCont: TCheckBox;
    rgTipoFonte: TRadioGroup;
    gbxRetornoEnvio: TGroupBox;
    cbxAjustarAut: TCheckBox;
    edtTentativas: TEdit;
    edtIntervalo: TEdit;
    edtAguardar: TEdit;
    Label36: TLabel;
    Label37: TLabel;
    Label38: TLabel;
    cbxEmissaoPathNFe: TCheckBox;
    cbxMostraStatus: TCheckBox;
    cbxExpandirLogo: TCheckBox;
    Label39: TLabel;
    edtProdPag: TEdit;
    gbLogComp: TGroupBox;
    Label40: TLabel;
    Label41: TLabel;
    SpeedButton1: TSpeedButton;
    edLogComp: TEdit;
    sedLogLinhasComp: TSpinEdit;
    cbLogComp: TCheckBox;
    edtEspBorda: TEdit;
    Label42: TLabel;
    edtFonteRazao: TEdit;
    Label43: TLabel;
    edtFonteCampos: TEdit;
    Label44: TLabel;
    cbUmaInstancia: TCheckBox;
    rgEmailTipoEnvio: TRadioGroup;
    btnDoar: TBitBtn;
    rgCasasDecimaisQtd: TRadioGroup;
    spedtDecimaisVUnit: TSpinEdit;
    Label45: TLabel;
    ACBrGIF1: TACBrGIF;
    Label46: TLabel;
    sbPathCCe: TSpeedButton;
    Label47: TLabel;
    sbPathEvento: TSpeedButton;
    edtPathCCe: TEdit;
    edtPathEvento: TEdit;
    cbxSalvaCCeCancelamentoPathEvento: TCheckBox;
    rgTipoCancelamento: TRadioGroup;
    ACBrCTe1: TACBrCTe;
    ACBrCTeDACTeQR1: TACBrCTeDACTeQR;
    Panel1: TPanel;
    PageControl3: TPageControl;
    TabSheet3: TTabSheet;
    btnStatusServ: TButton;
    btnValidarXML: TButton;
    btnImprimir: TButton;
    btnInutilizar: TButton;
    btnConsultar: TButton;
    btnCancNF: TButton;
    btnEnviar: TButton;
    btnEnviarEmail: TButton;
    TabSheet4: TTabSheet;
    btnStatusServCTe: TButton;
    btnValidarXMLCTe: TButton;
    btnImprimirCTe: TButton;
    btnInutilizarCTe: TButton;
    btnConsultarCTe: TButton;
    btnCancelarCTe: TButton;
    btnEnviarCTe: TButton;
    procedure DoACBrTimer(Sender: TObject);
    procedure edOnlyNumbers(Sender: TObject; var Key: Char);
    procedure FormCreate(Sender: TObject);
    procedure Restaurar1Click(Sender: TObject);
    procedure Ocultar1Click(Sender: TObject);
    procedure FormDestroy(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure FormCloseQuery(Sender: TObject; var CanClose: Boolean);
    procedure btConfigClick(Sender: TObject);
    procedure btMinimizarClick(Sender: TObject);
    procedure btCancelarClick(Sender: TObject);
    procedure rbTCPClick(Sender: TObject);
    procedure cbSenhaClick(Sender: TObject);
    procedure cbLogClick(Sender: TObject);
    procedure TCPServerConnect(AThread: TIdPeerThread);
    procedure TCPServerDisconnect(AThread: TIdPeerThread);
    procedure TCPServerExecute(AThread: TIdPeerThread);
    procedure sbArquivoCertClick(Sender: TObject);
    procedure sbLogoMarcaClick(Sender: TObject);
    procedure PathClick(Sender: TObject);
    procedure btnStatusServClick(Sender: TObject);
    procedure btnConsultarClick(Sender: TObject);
    procedure btnCancNFClick(Sender: TObject);
    procedure btnValidarXMLClick(Sender: TObject);
    procedure btnImprimirClick(Sender: TObject);
    procedure btnInutilizarClick(Sender: TObject);
    procedure btnEnviarClick(Sender: TObject);
    procedure edtNumCopiaKeyPress(Sender: TObject; var Key: Char);
    procedure edtMargemInfKeyPress(Sender: TObject; var Key: Char);
    procedure sbPathPDFClick(Sender: TObject);
    procedure rgModeloDanfeClick(Sender: TObject);
    procedure EncerrarMonitor1Click(Sender: TObject);
    procedure btnEnviarEmailClick(Sender: TObject);
    procedure sbPathNFeClick(Sender: TObject);
    procedure sbPathSalvarClick(Sender: TObject);
    procedure sbPathCanClick(Sender: TObject);
    procedure sbPathInuClick(Sender: TObject);
    procedure sbPathDPECClick(Sender: TObject);
    procedure cbxAjustarAutClick(Sender: TObject);
    procedure cbxPastaMensalClick(Sender: TObject);
    procedure ACBrNFe1GerarLog(const Mensagem: String);
    procedure Image1Click(Sender: TObject);
    procedure btnDoarClick(Sender: TObject);
    procedure cbxSalvarArqsClick(Sender: TObject);
    procedure ACBrGIF1Click(Sender: TObject);
    procedure sbPathCCeClick(Sender: TObject);
    procedure sbPathEventoClick(Sender: TObject);
    procedure ACBrCTe1GerarLog(const Mensagem: String);
    procedure btnStatusServCTeClick(Sender: TObject);
    procedure btnEnviarCTeClick(Sender: TObject);
    procedure btnConsultarCTeClick(Sender: TObject);
    procedure btnCancelarCTeClick(Sender: TObject);
    procedure btnValidarXMLCTeClick(Sender: TObject);
    procedure btnImprimirCTeClick(Sender: TObject);
    procedure btnInutilizarCTeClick(Sender: TObject);
  private
    { Private declarations }
    ACBrNFeMonitorINI : string;
    Inicio  : Boolean ;
    ArqSaiTXT, ArqSaiTMP, ArqEntTXT, ArqLogTXT, ArqLogCompTXT, ArqEntOrig, ArqSaiOrig : String ;
    NewLines : String ;
    fsHashSenha:Integer;
    Cmd : TACBrNFeCTeCmd ;

    procedure ExibeResp( Resposta : AnsiString );
    procedure Inicializar ;
    procedure EscondeConfig ;
    procedure ExibeConfig ;
    procedure AjustaLinhasLog ;

    procedure Resposta(Comando, Resposta : string);

    procedure TrataErros(Sender: TObject; E: Exception);

    procedure IconTray (var Msg: TMessage);
    message wm_IconMessage;

  public
    { Public declarations }
    nid: TNotifyIconData;
    Conexao : TIdPeerThread ;

    procedure Processar ;
    procedure LerIni ;
    procedure SalvarIni ;
    procedure VerificaDiretorios ;
  end;

var
  frmAcbrNfeMonitor: TfrmAcbrNfeMonitor;

implementation

uses ACBrUtil, IdStack, UtilUnit, DoACBrNFeUnit, DoACBrCTeUnit, Math;

const
  SELDIRHELP = 1000;

{$R *.dfm}
function PasswordInputBox(const ACaption, APrompt:string): string;
var
  Form: TForm;
  Prompt: TLabel;
  Edit: TEdit;
  DialogUnits: TPoint;
  ButtonTop, ButtonWidth, ButtonHeight: Integer;
  Value: string;
  I: Integer;
  Buffer: array[0..51] of Char;
begin
  Result := '';
  Form := TForm.Create(Application);
  with Form do
  try
    Canvas.Font := Font;
    for I := 0 to 25 do Buffer[I] := Chr(I + Ord('A'));
    for I := 0 to 25 do Buffer[I + 26] := Chr(I + Ord('a'));
    GetTextExtentPoint(Canvas.Handle, Buffer, 52, TSize(DialogUnits));
    DialogUnits.X := 260;
    DialogUnits.Y := 16;
    DialogUnits.X := DialogUnits.X div 52;
    BorderStyle := bsDialog;
    Caption := ACaption;
    ClientWidth := MulDiv(180, DialogUnits.X, 4);
    ClientHeight := MulDiv(63, DialogUnits.Y, 8);
    Position := poScreenCenter;
    Prompt := TLabel.Create(Form);
    with Prompt do
    begin
      Parent := Form;
      AutoSize := True;
      Left := MulDiv(8, DialogUnits.X, 4);
      Top := MulDiv(8, DialogUnits.Y, 8);
      Caption := APrompt;
    end;
    Edit := TEdit.Create(Form);
    with Edit do
    begin
      Parent := Form;
      Left := Prompt.Left;
      Top := MulDiv(19, DialogUnits.Y, 8);
      Width := MulDiv(164, DialogUnits.X, 4);
      MaxLength := 255;
      PasswordChar := '*';
      SelectAll;
    end;
    ButtonTop := MulDiv(41, DialogUnits.Y, 8);
    ButtonWidth := MulDiv(50, DialogUnits.X, 4);
    ButtonHeight := MulDiv(14, DialogUnits.Y, 8);
    with TButton.Create(Form) do
    begin
      Parent := Form;
      Caption := 'OK';
      ModalResult := mrOk;
      Default := True;
      SetBounds(MulDiv(38, DialogUnits.X, 4),ButtonTop, ButtonWidth,ButtonHeight);
    end;
    with TButton.Create(Form) do
    begin
      Parent := Form;
      Caption := 'Cancel';
      ModalResult := mrCancel;
      Cancel := True;
      SetBounds(MulDiv(92, DialogUnits.X, 4),ButtonTop, ButtonWidth,ButtonHeight);
    end;
    if ShowModal = mrOk then
    begin
      Value := Edit.Text;
      Result := Value;
    end;
  finally
    Form.Free;
  end;
end;

procedure TfrmAcbrNfeMonitor.ExibeResp( Resposta : AnsiString );
var
 StrLstResposta : TStringList;
begin
 StrLstResposta := TStringList.Create;
 StrLstResposta.Text := UTF8Encode(Resposta);
 StrLstResposta.SaveToFile(PathWithDelim(ExtractFileDir(application.ExeName))+'temp.xml');
 WBResposta.Navigate(PathWithDelim(ExtractFileDir(application.ExeName))+'temp.xml');
 StrLstResposta.Free;
end;

procedure TfrmAcbrNfeMonitor.Inicializar;
Var
  Txt    : String ;
  Erro   : String ;
  A : Integer ;
begin
  cbxImpressora.Items.Assign(Printer.Printers);
  Timer1.Enabled := false ;
  Inicio         := false ;
  Erro           := '' ;
  ACBrNFeMonitorINI := ExtractFilePath(Application.ExeName)+ 'ACBrNFeMonitor.ini';

  if not FileExists( ACBrNFeMonitorINI ) then //verifica se o arq. de config existe
  begin                                    //se nao existir vai para as configs
    MessageDlg('Bem vindo ao ACBrNFeMonitor,'+#13#10#13#10+
               'Por favor configure o ACBrNFeMonitor, '+
               'informando o M�todo de Monitoramento, e a configura��o '+
               'do certificado utilizado para assinar e efetuar a conex�o segura com os WebServices.'+#13#10#13#10+
               'IMPORTANTE: Ap�s configurar o '+
               'M�todo de Monitoramento o ACBrNFeMonitor'+
               ' deve ser reiniciado.',mtInformation,[mbok],0) ;
    {$IFDEF ACBrNFeOpenSSL}
       lblCaminho.Caption := 'Arquivo PFX';
    {$ELSE}
       lblCaminho.Caption := 'N�mero de S�rie';
    {$ENDIF}
    btConfig.Click ;
    exit ;
  end;

  while not Visible do
  begin
     Application.ProcessMessages ;
     sleep(200) ;
  end ;

  try
     LerIni;
     cbxEmissaoPathNFe.Enabled := cbxPastaMensal.Checked;

     Application.Minimize ;
  except
     on E : Exception do
        Erro := Erro + sLineBreak + E.Message ;
  end ;

  EscondeConfig ;

  try
     AjustaLinhasLog ;  { Diminui / Ajusta o numero de linhas do Log }
  except
     on E : Exception do
        Erro := Erro + sLineBreak +E.Message ;
  end ;

  try
     btConfig.Enabled  := true ;
     Timer1.Interval  := sedIntervalo.Value ;
     Timer1.Enabled   := rbTXT.Checked ;
     TcpServer.Active := rbTCP.Checked ;

     mResp.Lines.Add('ACBrNFeMonitor2 Ver.'+Versao+ {$IFDEF ACBrNFeOpenSSL}' - OpenSSL'{$ELSE}' - CAPICOM'{$ENDIF});
     mResp.Lines.Add('Aguardando comandos');
  except
     on E : Exception do
        Erro := Erro + sLineBreak + E.Message ;
  end ;

  try
     if rbTCP.Checked then
      begin
        if TcpServer.Active then
        begin
           try
              Txt := 'Endere�o: '+TcpServer.LocalName ;
              For A := 0 to IdStack.GStack.LocalAddresses.Count-1 do
                 Txt := Txt + '   ' + IdStack.GStack.LocalAddresses[A] ;
           except
           end ;
           mResp.Lines.Add(Txt) ;
           mResp.Lines.Add( 'Porta: ['+IntToStr(TcpServer.DefaultPort)+']') ;
        end ;
      end
     else
      begin
        mResp.Lines.Add('Monitorando Comandos TXT em: '+ArqEntTXT);
        mResp.Lines.Add('Respostas gravadas em: '+ArqSaiTXT);
      end ;

     if cbLog.Checked then
        mResp.Lines.Add('Log de comandos ser� gravado em: '+ArqLogTXT) ;

     if cbLogComp.Checked then
        mResp.Lines.Add('Log de mensagens do componente ser� gravado em: '+ArqLogCompTXT) ;

{     mResp.Lines.Add('');
     mResp.Lines.Add('   * Voc� gosta do ACBrNFeMonitor ?');
     mResp.Lines.Add('');
     mResp.Lines.Add('   * Incentive a equipe de desenvolvimento do ACBrNFeMonitor') ;
     mResp.Lines.Add('     a continuar com esse trabalho.');
     mResp.Lines.Add('');
     mResp.Lines.Add('   * EFETUE AINDA HOJE A SUA DOA��O. Acesse:') ;
     mResp.Lines.Add('     http://acbr.sourceforge.net/drupal/?q=node/14') ;
     mResp.Lines.Add('');
     mResp.Lines.Add('     http://www.djsystem.com.br/acbr/forum/') ;    }
  except
     on E : Exception do
        Erro := Erro + sLineBreak + E.Message ;
  end ;

  if Erro <> '' then
     Raise Exception.Create( Erro );
end;

{------------------------------------------------------------------------------}
procedure TfrmAcbrNfeMonitor.AjustaLinhasLog;
  Procedure AjustaLogFile( AFile : String; LinhasMax : Integer ) ;
    Var LogNew, LogOld : TStringList ;
     I : Integer ;
  begin
     if not FileExists( AFile ) then
        exit ;

     LogOld := TStringList.Create ;
     try
        LogOld.LoadFromFile( AFile );
        if LogOld.Count > LinhasMax then
        begin
           mResp.Lines.Add('Ajustando o tamanho do arquivo: '+AFile) ;
           mResp.Lines.Add('Numero de Linhas atual: '+IntToStr(LogOld.Count) ) ;
           mResp.Lines.Add('Reduzindo para: '+IntToStr(LinhasMax)+ ' linhas' );

           { Se for muito grande � mais r�pido copiar para outra lista do que Deletar }
           if (LogOld.Count - LinhasMax) > LinhasMax then
            begin
              LogNew := TStringList.Create ;
              try
                 LogNew.Clear ;

                 For I := LinhasMax downto 1 do
                    LogNew.Add(LogOld[ LogOld.Count - I ] ) ;

                 LogNew.SaveToFile( AFile );
              finally
                 LogNew.Free ;
              end ;
            end
           else
            begin
              { Existe alguma maneira mais r�pida de fazer isso ??? }
              LogOld.BeginUpdate ;
              while LogOld.Count > LinhasMax do
                 LogOld.Delete(0) ;
              LogOld.EndUpdate ;
              LogOld.SaveToFile( AFile );
            end ;
        end ;
     finally
        LogOld.Free ;
     end ;
  end ;
begin
  if (sedLogLinhas.Value > 0) and (cbLog.Checked) then
     AjustaLogFile( ArqLogTXT, sedLogLinhas.Value );

  if (sedLogLinhasComp.Value > 0) and (cbLogComp.Checked) then
     AjustaLogFile( ArqLogCompTXT, sedLogLinhasComp.Value );

end;

procedure TfrmAcbrNfeMonitor.EscondeConfig;
begin
  pConfig.Visible := False ;

  btConfig.Caption     := '&Configurar' ;
  btConfig.Glyph       := nil ;
  ImageList1.GetBitmap(5,btConfig.Glyph);
  btCancelar.Visible   := false ;
  btMinimizar.Visible := true ;
  Application.ProcessMessages ;
end;

procedure TfrmAcbrNfeMonitor.ExibeConfig;
Var Senha     : String ;
    SenhaOk   : Boolean ;
    HashSenha : Integer ;
begin
  SenhaOk := (fsHashSenha < 1)  ;
  if not SenhaOk then
  begin
     Senha := PasswordInputBox('Configura��o','Digite a Senha de Configura��o') ;
//     if InputQuery('Configura��o','Digite a Senha de Configura��o',Senha) then
//     begin
        Senha     := Trim(Senha) ;
        HashSenha := StringCrc16( Senha ) ;
        SenhaOk   := (HashSenha = fsHashSenha) ;
//     end ;
  end ;

  if not SenhaOk then
     raise Exception.Create('Senha ['+Senha+'] inv�lida');

  pConfig.Visible := true ;

  btConfig.Caption     := '&Salvar' ;
  btConfig.Glyph       := nil ;
  ImageList1.GetBitmap(6,btConfig.Glyph);
  btCancelar.Visible   := true ;
  btMinimizar.Visible := false ;
  Application.ProcessMessages ;
end;

procedure TfrmAcbrNfeMonitor.LerIni ;
var
  Ini : TIniFile;
  Senha :String ;
  StreamMemo : TMemoryStream;
  OK : Boolean;
begin
  Ini := TIniFile.Create( ACBrNFeMonitorINI ) ;

  try
     { Lendo Senha }
     fsHashSenha := StrToIntDef( LeINICrypt(INI,'ACBrNFeMonitor','HashSenha', _C), -1) ;

     if fsHashSenha < 1 then  { INI antigo n�o tinha essa chave }
     begin
        Senha := Ini.ReadString('ACBrNFeMonitor','Senha','');
        if Senha <> '' then
           fsHashSenha := StringCrc16(Senha) ;
     end ;

     if fsHashSenha > 0 then
     begin
        cbSenha.Checked := True ;
        edSenha.Text    := 'NADAAQUI' ;
     end ;

     { Parametros do Monitor }
     rbTCP.Checked        := Ini.ReadBool('ACBrNFeMonitor','Modo_TCP',false);
     rbTXT.Checked        := Ini.ReadBool('ACBrNFeMonitor','Modo_TXT',false);
     edPortaTCP.Text      := IntToStr(Ini.ReadInteger('ACBrNFeMonitor','TCP_Porta',3434));
     sedConexoesTCP.Value := Ini.ReadInteger('ACBrNFeMonitor','Conexoes_Simultaneas',1);
     edEntTXT.Text        := Ini.ReadString('ACBrNFeMonitor','TXT_Entrada','ENT.TXT');
     edSaiTXT.Text        := Ini.ReadString('ACBrNFeMonitor','TXT_Saida','SAI.TXT');
     sedIntervalo.Value   := Ini.ReadInteger('ACBrNFeMonitor','Intervalo',50);
     edLogArq.Text        := Ini.ReadString('ACBrNFeMonitor','Arquivo_Log','LOG.TXT');
     cbLog.Checked        := Ini.ReadBool('ACBrNFeMonitor','Gravar_Log',false) and
                           ( edLogArq.Text <> '' ) ;
     sedLogLinhas.Value   := Ini.ReadInteger('ACBrNFeMonitor','Linhas_Log',0);

     edLogComp.Text        := Ini.ReadString('ACBrNFeMonitor','Arquivo_Log_Comp','LOG_COMP.TXT');
     cbLogComp.Checked        := Ini.ReadBool('ACBrNFeMonitor','Gravar_Log_Comp',false) and
                           ( edLogComp.Text <> '' ) ;
     sedLogLinhasComp.Value   := Ini.ReadInteger('ACBrNFeMonitor','Linhas_Log_Comp',0);
     cbUmaInstancia.Checked  := Ini.ReadBool('ACBrNFeMonitor','Uma_Instancia',false);

     ArqEntTXT := AcertaPath( edEntTXT.Text ) ;
     ArqEntOrig := ArqEntTXT;
     ArqSaiTXT := AcertaPath( edSaiTXT.Text ) ;
     ArqSaiOrig := ArqSaiTXT;     
     ArqSaiTMP := ChangeFileExt( ArqSaiTXT, '.tmp' ) ;
     ArqLogTXT := AcertaPath( edLogArq.Text ) ;
     ArqLogCompTXT := AcertaPath( edLogComp.Text ) ;     

     TcpServer.DefaultPort    := StrToIntDef( edPortaTCP.Text, 3436 ) ;
     TcpServer.MaxConnections := sedConexoesTCP.Value ;
     TcpServer.MaxConnectionReply.Text.Add( 'Pedido de conex�o negado.') ;
     TcpServer.MaxConnectionReply.Text.Add(
        'N�mero m�ximo de conex�es ('+sedConexoesTCP.Text+') j� atingido' ) ;

     rgFormaEmissao.ItemIndex := Ini.ReadInteger( 'Geral','FormaEmissao',0) ;
     ckSalvar.Checked    := Ini.ReadBool(   'Geral','Salvar'      ,True) ;
     edtPathLogs.Text    := Ini.ReadString( 'Geral','PathSalvar'  ,     PathWithDelim(ExtractFilePath(Application.ExeName))+'Logs') ;
     cbxImpressora.ItemIndex   := cbxImpressora.Items.IndexOf(Ini.ReadString( 'Geral','Impressora','0')) ;

     ACBrNFe1.Configuracoes.Geral.FormaEmissao := StrToTpEmis(OK,IntToStr(rgFormaEmissao.ItemIndex+1));
     ACBrNFe1.Configuracoes.Geral.Salvar       := ckSalvar.Checked;
     ACBrNFe1.Configuracoes.Geral.PathSalvar   := edtPathLogs.Text;

     ACBrCTe1.Configuracoes.Geral.FormaEmissao := StrToTpEmis(OK,IntToStr(rgFormaEmissao.ItemIndex+1));
     ACBrCTe1.Configuracoes.Geral.Salvar       := ckSalvar.Checked;
     ACBrCTe1.Configuracoes.Geral.PathSalvar   := edtPathLogs.Text;

     cbxAjustarAut.Checked  := Ini.ReadBool(   'WebService','AjustarAut' ,False) ;
     edtAguardar.Text       := Ini.ReadString( 'WebService','Aguardar'  ,'0') ;
     edtTentativas.Text     := Ini.ReadString( 'WebService','Tentativas','5') ;
     edtIntervalo.Text      := Ini.ReadString( 'WebService','Intervalo' ,'0') ;


     ACBrNFe1.Configuracoes.WebServices.AjustaAguardaConsultaRet := cbxAjustarAut.Checked;
     ACBrCTe1.Configuracoes.WebServices.AjustaAguardaConsultaRet := cbxAjustarAut.Checked;
     if DFeUtil.NaoEstaVazio(edtAguardar.Text)then
      begin
        ACBrNFe1.Configuracoes.WebServices.AguardarConsultaRet := DFeUtil.SeSenao(StrToInt(edtAguardar.Text)<1000,StrToInt(edtAguardar.Text)*1000,StrToInt(edtAguardar.Text));
        ACBrCTe1.Configuracoes.WebServices.AguardarConsultaRet := DFeUtil.SeSenao(StrToInt(edtAguardar.Text)<1000,StrToInt(edtAguardar.Text)*1000,StrToInt(edtAguardar.Text))
      end
     else
      begin
        edtAguardar.Text := IntToStr(ACBrNFe1.Configuracoes.WebServices.AguardarConsultaRet);
        edtAguardar.Text := IntToStr(ACBrCTe1.Configuracoes.WebServices.AguardarConsultaRet);
      end;
     if DFeUtil.NaoEstaVazio(edtTentativas.Text) then
      begin
        ACBrNFe1.Configuracoes.WebServices.Tentativas          := StrToInt(edtTentativas.Text);
        ACBrCTe1.Configuracoes.WebServices.Tentativas          := StrToInt(edtTentativas.Text);
      end
     else
      begin
        edtTentativas.Text := IntToStr(ACBrNFe1.Configuracoes.WebServices.Tentativas);
        edtTentativas.Text := IntToStr(ACBrCTe1.Configuracoes.WebServices.Tentativas);
      end;
     if DFeUtil.NaoEstaVazio(edtIntervalo.Text) then
      begin
        ACBrNFe1.Configuracoes.WebServices.IntervaloTentativas := DFeUtil.SeSenao(StrToInt(edtIntervalo.Text)<1000,StrToInt(edtIntervalo.Text)*1000,StrToInt(edtIntervalo.Text));
        ACBrCTe1.Configuracoes.WebServices.IntervaloTentativas := DFeUtil.SeSenao(StrToInt(edtIntervalo.Text)<1000,StrToInt(edtIntervalo.Text)*1000,StrToInt(edtIntervalo.Text))
      end
     else
      begin
        edtIntervalo.Text := IntToStr(ACBrNFe1.Configuracoes.WebServices.IntervaloTentativas);
        edtIntervalo.Text := IntToStr(ACBrCTe1.Configuracoes.WebServices.IntervaloTentativas);
      end;


     cbUF.ItemIndex       := cbUF.Items.IndexOf(Ini.ReadString( 'WebService','UF','SP')) ;
     rgTipoAmb.ItemIndex  := Ini.ReadInteger( 'WebService','Ambiente'  ,0) ;
     ACBrNFe1.Configuracoes.WebServices.UF         := cbUF.Text;
     ACBrNFe1.Configuracoes.WebServices.Ambiente   := StrToTpAmb(Ok,IntToStr(rgTipoAmb.ItemIndex+1));

     ACBrCTe1.Configuracoes.WebServices.UF         := cbUF.Text;
     ACBrCTe1.Configuracoes.WebServices.Ambiente   := StrToTpAmb(Ok,IntToStr(rgTipoAmb.ItemIndex+1));

     rgTipoCancelamento.ItemIndex := Ini.ReadInteger( 'WebService','TipoCancelamento',IfThen((pos('|'+UpperCase(ACBrNFe1.Configuracoes.WebServices.UF)+'|', '|PR|ES|MA|PA|PI|RN|') <= 0),0,1)) ;

     {$IFDEF ACBrNFeOpenSSL}
     lblCaminho.Caption := 'Arquivo PFX';
     edtCaminho.Text  := Ini.ReadString( 'Certificado','Caminho' ,'') ;
     ACBrNFe1.Configuracoes.Certificados.Certificado  := edtCaminho.Text;
     ACBrCTe1.Configuracoes.Certificados.Certificado  := edtCaminho.Text;
     {$ELSE}
     lblCaminho.Caption := 'N�mero de S�rie';
     edtCaminho.Text  := Ini.ReadString( 'Certificado','Caminho' ,'') ;
     ACBrNFe1.Configuracoes.Certificados.NumeroSerie := edtCaminho.Text;
     edtCaminho.Text := ACBrNFe1.Configuracoes.Certificados.NumeroSerie;

     ACBrCTe1.Configuracoes.Certificados.NumeroSerie := edtCaminho.Text;
     edtCaminho.Text := ACBrCTe1.Configuracoes.Certificados.NumeroSerie;
     {$ENDIF}
     edtSenha.Text    := LeINICrypt(INI,'Certificado','Senha', _C) ;
     ACBrNFe1.Configuracoes.Certificados.Senha        := edtSenha.Text;
     ACBrCTe1.Configuracoes.Certificados.Senha        := edtSenha.Text;

     edtProxyHost.Text  := Ini.ReadString( 'Proxy','Host'   ,'') ;
     edtProxyPorta.Text := Ini.ReadString( 'Proxy','Porta'  ,'') ;
     edtProxyUser.Text  := Ini.ReadString( 'Proxy','User'   ,'') ;
     edtProxySenha.Text := LeINICrypt(INI, 'Proxy','Pass', _C) ;
     ACBrNFe1.Configuracoes.WebServices.ProxyHost := edtProxyHost.Text;
     ACBrNFe1.Configuracoes.WebServices.ProxyPort := edtProxyPorta.Text;
     ACBrNFe1.Configuracoes.WebServices.ProxyUser := edtProxyUser.Text;
     ACBrNFe1.Configuracoes.WebServices.ProxyPass := edtProxySenha.Text;

     ACBrCTe1.Configuracoes.WebServices.ProxyHost := edtProxyHost.Text;
     ACBrCTe1.Configuracoes.WebServices.ProxyPort := edtProxyPorta.Text;
     ACBrCTe1.Configuracoes.WebServices.ProxyUser := edtProxyUser.Text;
     ACBrCTe1.Configuracoes.WebServices.ProxyPass := edtProxySenha.Text;

     rgTipoDanfe.ItemIndex     := Ini.ReadInteger( 'Geral','DANFE'   ,0) ;
     edtLogoMarca.Text         := Ini.ReadString( 'Geral','LogoMarca','') ;
     rgModeloDanfe.ItemIndex   := Ini.ReadInteger('DANFE','Modelo'   ,0) ;
     edtSoftwareHouse.Text     := Ini.ReadString( 'DANFE','SoftwareHouse'   ,'') ;
     edtSiteEmpresa.Text       := Ini.ReadString( 'DANFE','Site'  ,'') ;
     edtEmailEmpresa.Text      := Ini.ReadString( 'DANFE','Email' ,'') ;
     edtFaxEmpresa.Text        := Ini.ReadString( 'DANFE','Fax'   ,'') ;
     cbxImpDescPorc.Checked    := Ini.ReadBool(   'DANFE','ImpDescPorc',False) ;
     cbxMostrarPreview.Checked := Ini.ReadBool(   'DANFE','MostrarPreview',False) ;
     edtNumCopia.Text          := Ini.ReadString( 'DANFE','Copias','1') ;
     edtProdPag.Text           := Ini.ReadString( 'DANFE','ProdutosPagina','0') ;
     edtEspBorda.Text          := Ini.ReadString( 'DANFE','EspessuraBorda','1') ;
     edtFonteRazao.Text        := Ini.ReadString( 'DANFE','FonteRazao','12') ;
     edtFonteCampos.Text       := Ini.ReadString( 'DANFE','FonteCampos','10') ;
     edtMargemInf.Text         := Ini.ReadString( 'DANFE','Margem','0,8') ;
     edtMargemSup.Text         := Ini.ReadString( 'DANFE','MargemSup','0,8') ;
     edtMargemDir.Text         := Ini.ReadString( 'DANFE','MargemDir','0,51') ;
     edtMargemEsq.Text         := Ini.ReadString( 'DANFE','MargemEsq','0,6') ;
     edtPathPDF.Text           := PathWithDelim(Ini.ReadString( 'DANFE','PathPDF',ExtractFilePath(Application.ExeName))) ;
     rgCasasDecimaisQtd.ItemIndex   := Ini.ReadInteger('DANFE','DecimaisQTD',2) ;
     spedtDecimaisVUnit.Value  := Ini.ReadInteger('DANFE','DecimaisValor',2) ;
     cbxExibeResumo.Checked    := Ini.ReadBool(   'DANFE','ExibeResumo',False) ;
     cbxImpValLiq.Checked      := Ini.ReadBool(   'DANFE','ImprimirValLiq',False) ;
     cbxFormCont.Checked       := Ini.ReadBool(   'DANFE','PreImpresso',False) ;
     cbxMostraStatus.Checked   := Ini.ReadBool(   'DANFE','MostrarStatus',True) ;
     cbxExpandirLogo.Checked   := Ini.ReadBool(   'DANFE','ExpandirLogo',False) ;
     rgTipoFonte.ItemIndex     := Ini.ReadInteger( 'DANFE','Fonte'   ,0) ;

     if rgModeloDanfe.ItemIndex = 0 then
        ACBrNFe1.DANFE := ACBrNFeDANFERave1
     else
        ACBrNFe1.DANFE := ACBrNFeDANFERaveCB1;

     if ACBrNFe1.DANFE <> nil then
      begin
        ACBrNFe1.DANFE.TipoDANFE  := StrToTpImp(OK,IntToStr(rgTipoDanfe.ItemIndex+1));
        ACBrNFe1.DANFE.Logo       := edtLogoMarca.Text;
        ACBrNFe1.DANFE.Sistema := edtSoftwareHouse.Text;
        ACBrNFe1.DANFE.Site    := edtSiteEmpresa.Text;
        ACBrNFe1.DANFE.Email   := edtEmailEmpresa.Text;
        ACBrNFe1.DANFE.Fax     := edtFaxEmpresa.Text;
        ACBrNFe1.DANFE.ImprimirDescPorc  := cbxImpDescPorc.Checked;
        ACBrNFe1.DANFE.MostrarPreview    := cbxMostrarPreview.Checked;
        ACBrNFe1.DANFE.Impressora := cbxImpressora.Text;
        ACBrNFe1.DANFE.NumCopias  := StrToIntDef(edtNumCopia.Text, 1);
        ACBrNFe1.DANFE.ProdutosPorPagina := StrToIntDef(edtProdPag.Text, 0);
        ACBrNFe1.DANFE.MargemInferior  := StrToFloatDef(edtMargemInf.Text,0.8);
        ACBrNFe1.DANFE.MargemSuperior  := StrToFloatDef(edtMargemSup.Text,0.8);
        ACBrNFe1.DANFE.MargemDireita   := StrToFloatDef(edtMargemDir.Text,0.51);
        ACBrNFe1.DANFE.MargemEsquerda  := StrToFloatDef(edtMargemEsq.Text,0.6);
        ACBrNFe1.DANFE.PathPDF    := edtPathPDF.Text;
        ACBrNFe1.DANFE.CasasDecimais._qCom   := rgCasasDecimaisQtd.ItemIndex+2;
        ACBrNFe1.DANFE.CasasDecimais._vUnCom := spedtDecimaisVUnit.Value;
        ACBrNFe1.DANFE.ExibirResumoCanhoto  :=  cbxExibeResumo.Checked;
        ACBrNFe1.DANFE.ImprimirTotalLiquido := cbxImpValLiq.Checked;
        ACBrNFe1.DANFE.FormularioContinuo   := cbxFormCont.Checked;
        ACBrNFe1.DANFE.MostrarStatus        := cbxMostraStatus.Checked;
        ACBrNFe1.DANFE.ExpandirLogoMarca    := cbxExpandirLogo.Checked;
        ACBrNFe1.DANFE.TamanhoFonte_DemaisCampos := StrToIntDef(edtFonteCampos.Text, 10);

        if ACBrNFe1.DANFE = ACBrNFeDANFERave1 then
         begin
           ACBrNFeDANFERave1.EspessuraBorda := StrToIntDef(edtEspBorda.Text, 1);
           ACBrNFeDANFERave1.TamanhoFonte_RazaoSocial := StrToIntDef(edtFonteRazao.Text, 12);
           ACBrNFeDANFERave1.RavFile := PathWithDelim(ExtractFilePath(Application.ExeName))+'Report\DANFE_Rave513.rav'; //NotaFiscalEletronica
         end
        else
         begin
           ACBrNFeDANFERaveCB1.EspessuraBorda := StrToIntDef(edtEspBorda.Text, 1);
           ACBrNFeDANFERaveCB1.Fonte  := DFeUtil.SeSenao(rgTipoFonte.ItemIndex=0,ftTimes,ftCourier);
         end;
      end;

     ACBrCTe1.DACTe := ACBrCTeDACTeQR1;
     ACBrCTe1.DACTe.TipoDACTE  := StrToTpImp(OK,IntToStr(rgTipoDanfe.ItemIndex+1));
     ACBrCTe1.DACTe.Logo       := edtLogoMarca.Text;
     ACBrCTe1.DACTe.Sistema := edtSoftwareHouse.Text;
     ACBrCTe1.DACTe.Site    := edtSiteEmpresa.Text;
     ACBrCTe1.DACTe.Email   := edtEmailEmpresa.Text;
     ACBrCTe1.DACTe.Fax     := edtFaxEmpresa.Text;
     ACBrCTe1.DACTe.ImprimirDescPorc  := cbxImpDescPorc.Checked;
     ACBrCTe1.DACTe.MostrarPreview    := cbxMostrarPreview.Checked;
     ACBrCTe1.DACTe.Impressora := cbxImpressora.Text;
     ACBrCTe1.DACTe.NumCopias  := StrToIntDef(edtNumCopia.Text, 1);
//     ACBrCTe1.DACTe.ProdutosPorPagina := StrToIntDef(edtProdPag.Text, 0);
     ACBrCTe1.DACTe.MargemInferior  := StrToFloatDef(edtMargemInf.Text,0.8);
     ACBrCTe1.DACTe.MargemSuperior  := StrToFloatDef(edtMargemSup.Text,0.8);
     ACBrCTe1.DACTe.MargemDireita   := StrToFloatDef(edtMargemDir.Text,0.51);
     ACBrCTe1.DACTe.MargemEsquerda  := StrToFloatDef(edtMargemEsq.Text,0.6);
     ACBrCTe1.DACTe.PathPDF     := edtPathPDF.Text;
     ACBrCTe1.DACTe.MostrarStatus        := cbxMostraStatus.Checked;
     ACBrCTe1.DACTe.ExpandirLogoMarca    := cbxExpandirLogo.Checked;

     edtSmtpHost.Text      := Ini.ReadString( 'Email','Host'   ,'') ;
     edtSmtpPort.Text      := Ini.ReadString( 'Email','Port'   ,'') ;
     edtSmtpUser.Text      := Ini.ReadString( 'Email','User'   ,'') ;
     edtSmtpPass.Text      := LeINICrypt( INI, 'Email','Pass' ,_C) ;
     edtEmailAssunto.Text  := Ini.ReadString( 'Email','Assunto','') ;
     cbEmailSSL.Checked    := Ini.ReadBool(   'Email','SSL'    ,False) ;
     rgEmailTipoEnvio.ItemIndex := Ini.ReadInteger( 'Email','Tipo'   ,0) ;
     StreamMemo := TMemoryStream.Create;
     Ini.ReadBinaryStream( 'Email','Mensagem',StreamMemo) ;
     mmEmailMsg.Lines.LoadFromStream(StreamMemo);
     StreamMemo.Free;

     cbxSalvarArqs.Checked      := Ini.ReadBool(   'Arquivos','Salvar'     ,false);
     VerificaDiretorios;
     cbxPastaMensal.Checked     := Ini.ReadBool(   'Arquivos','PastaMensal',false);
     cbxAdicionaLiteral.Checked := Ini.ReadBool(   'Arquivos','AddLiteral' ,false);
     cbxEmissaoPathNFe.Checked  := Ini.ReadBool(   'Arquivos','EmissaoPathNFe',false);
     cbxSalvaCCeCancelamentoPathEvento.Checked  := Ini.ReadBool(   'Arquivos','SalvarCCeCanPathEvento',false);
     edtPathNFe.Text            := Ini.ReadString( 'Arquivos','PathNFe'    ,'') ;
     edtPathCan.Text            := Ini.ReadString( 'Arquivos','PathCan'    ,'') ;
     edtPathInu.Text            := Ini.ReadString( 'Arquivos','PathInu'    ,'') ;
     edtPathDPEC.Text           := Ini.ReadString( 'Arquivos','PathDPEC'   ,'') ;
     edtPathCCe.Text            := Ini.ReadString( 'Arquivos','PathCCe'   ,'') ;
     edtPathEvento.Text         := Ini.ReadString( 'Arquivos','PathEvento','') ;

     ACBrNFe1.Configuracoes.Arquivos.Salvar           := cbxSalvarArqs.Checked;
     ACBrNFe1.Configuracoes.Arquivos.PastaMensal      := cbxPastaMensal.Checked;
     ACBrNFe1.Configuracoes.Arquivos.AdicionarLiteral := cbxAdicionaLiteral.Checked;
     ACBrNFe1.Configuracoes.Arquivos.EmissaoPathNFe   := cbxEmissaoPathNFe.Checked;
     ACBrNFe1.Configuracoes.Arquivos.SalvarCCeCanEvento := cbxSalvaCCeCancelamentoPathEvento.Checked;
     ACBrNFe1.Configuracoes.Arquivos.PathNFe  := edtPathNFe.Text;
     ACBrNFe1.Configuracoes.Arquivos.PathCan  := edtPathCan.Text;
     ACBrNFe1.Configuracoes.Arquivos.PathInu  := edtPathInu.Text;
     ACBrNFe1.Configuracoes.Arquivos.PathDPEC := edtPathDPEC.Text;
     ACBrNFe1.Configuracoes.Arquivos.PathCCe  := edtPathCCe.Text;
     ACBrNFe1.Configuracoes.Arquivos.PathEvento := edtPathEvento.Text;
     ACBrCTe1.Configuracoes.Arquivos.Salvar           := cbxSalvarArqs.Checked;
     ACBrCTe1.Configuracoes.Arquivos.PastaMensal      := cbxPastaMensal.Checked;
     ACBrCTe1.Configuracoes.Arquivos.AdicionarLiteral := cbxAdicionaLiteral.Checked;
     ACBrCTe1.Configuracoes.Arquivos.EmissaoPathCTe   := cbxEmissaoPathNFe.Checked;
     ACBrCTe1.Configuracoes.Arquivos.PathCTe  := edtPathNFe.Text;
     ACBrCTe1.Configuracoes.Arquivos.PathCan  := edtPathCan.Text;
     ACBrCTe1.Configuracoes.Arquivos.PathInu  := edtPathInu.Text;
     ACBrCTe1.Configuracoes.Arquivos.PathDPEC := edtPathDPEC.Text;
  finally
     Ini.Free ;
  end ;

end;

procedure TfrmAcbrNfeMonitor.SalvarIni;
var
  Ini : TIniFile;
  StreamMemo : TMemoryStream;
  OldMonitoraTXT, OldMonitoraTCP : Boolean ;
begin
  if cbSenha.Checked and (edSenha.Text <> 'NADAAQUI') and (edSenha.Text <> '') then
     fsHashSenha := StringCrc16(edSenha.Text) ;

  Ini := TIniFile.Create( ACBrNFeMonitorINI ) ;
  try
     // Verificando se modificou o Modo de Monitoramento //
     OldMonitoraTCP := Ini.ReadBool('ACBrNFeMonitor','Modo_TCP',false) ;
     OldMonitoraTXT := Ini.ReadBool('ACBrNFeMonitor','Modo_TXT',false) ;

     // Parametros do Monitor //
     Ini.WriteBool('ACBrNFeMonitor','Modo_TCP',rbTCP.Checked);
     Ini.WriteBool('ACBrNFeMonitor','Modo_TXT',rbTXT.Checked);
     Ini.WriteInteger('ACBrNFeMonitor','TCP_Porta',StrToIntDef(edPortaTCP.Text,3434));
     Ini.WriteInteger('ACBrNFeMonitor','Conexoes_Simultaneas',sedConexoesTCP.Value);
     Ini.WriteString('ACBrNFeMonitor','TXT_Entrada',edEntTXT.Text);
     Ini.WriteString('ACBrNFeMonitor','TXT_Saida',edSaiTXT.Text);
     Ini.WriteInteger('ACBrNFeMonitor','Intervalo',sedIntervalo.Value);
     GravaINICrypt(INI,'ACBrNFeMonitor','HashSenha', IntToStrZero(fsHashSenha,8), _C) ;

     Ini.WriteBool('ACBrNFeMonitor','Gravar_Log',cbLog.Checked);
     Ini.WriteString('ACBrNFeMonitor','Arquivo_Log',edLogArq.Text);
     Ini.WriteInteger('ACBrNFeMonitor','Linhas_Log',sedLogLinhas.Value);

     Ini.WriteBool('ACBrNFeMonitor','Gravar_Log_Comp',cbLogComp.Checked);
     Ini.WriteString('ACBrNFeMonitor','Arquivo_Log_Comp',edLogComp.Text);
     Ini.WriteInteger('ACBrNFeMonitor','Linhas_Log_Comp',sedLogLinhasComp.Value);

     Ini.WriteBool('ACBrNFeMonitor','Uma_Instancia',cbUmaInstancia.Checked);

     Ini.WriteString( 'Certificado','Caminho' ,edtCaminho.Text) ;
     GravaINICrypt(INI,'Certificado','Senha', edtSenha.Text, _C) ;

     Ini.WriteInteger('Geral','DANFE'       ,rgTipoDanfe.ItemIndex) ;
     Ini.WriteInteger('Geral','FormaEmissao',rgFormaEmissao.ItemIndex) ;
     Ini.WriteString( 'Geral','LogoMarca'   ,edtLogoMarca.Text) ;
     Ini.WriteBool(   'Geral','Salvar'      ,ckSalvar.Checked) ;
     Ini.WriteString( 'Geral','PathSalvar'  ,edtPathLogs.Text) ;
     Ini.WriteString( 'Geral','Impressora'  ,cbxImpressora.Text) ;

     Ini.WriteString( 'WebService','UF'         ,cbUF.Text) ;
     Ini.WriteInteger('WebService','Ambiente'   ,rgTipoAmb.ItemIndex) ;
     Ini.WriteBool(   'WebService','AjustarAut' ,cbxAjustarAut.Checked) ;
     Ini.WriteString( 'WebService','Aguardar'   ,edtAguardar.Text) ;
     Ini.WriteString( 'WebService','Tentativas' ,edtTentativas.Text) ;
     Ini.WriteString( 'WebService','Intervalo'  ,edtIntervalo.Text) ;
     Ini.WriteInteger('WebService','TipoCancelamento',rgTipoCancelamento.ItemIndex) ;

     Ini.WriteString( 'Proxy','Host'   ,edtProxyHost.Text) ;
     Ini.WriteString( 'Proxy','Porta'  ,edtProxyPorta.Text) ;
     Ini.WriteString( 'Proxy','User'   ,edtProxyUser.Text) ;
     GravaINICrypt( INI, 'Proxy','Pass',edtProxySenha.Text, _C) ;

     Ini.WriteString( 'Email','Host'    ,edtSmtpHost.Text) ;
     Ini.WriteString( 'Email','Port'    ,edtSmtpPort.Text) ;
     Ini.WriteString( 'Email','User'    ,edtSmtpUser.Text) ;
     GravaINICrypt(INI, 'Email','Pass'  ,edtSmtpPass.Text, _C) ;
     Ini.WriteString( 'Email','Assunto' ,edtEmailAssunto.Text) ;
     Ini.WriteBool(   'Email','SSL'     ,cbEmailSSL.Checked ) ;
     Ini.WriteInteger('Email','Tipo'    ,rgEmailTipoEnvio.ItemIndex) ;
     StreamMemo := TMemoryStream.Create;
     mmEmailMsg.Lines.SaveToStream(StreamMemo);
     StreamMemo.Seek(0,soFromBeginning);
     Ini.WriteBinaryStream( 'Email','Mensagem',StreamMemo) ;
     StreamMemo.Free;

     Ini.WriteInteger('DANFE','Modelo'       ,rgModeloDanfe.ItemIndex) ;
     Ini.WriteString( 'DANFE','SoftwareHouse',edtSoftwareHouse.Text) ;
     Ini.WriteString( 'DANFE','Site' ,edtSiteEmpresa.Text) ;
     Ini.WriteString( 'DANFE','Email',edtEmailEmpresa.Text) ;
     Ini.WriteString( 'DANFE','Fax'  ,edtFaxEmpresa.Text) ;
     Ini.WriteBool(   'DANFE','ImpDescPorc'   ,cbxImpDescPorc.Checked);
     Ini.WriteBool(   'DANFE','MostrarPreview',cbxMostrarPreview.Checked);
     Ini.WriteString( 'DANFE','Copias'        ,edtNumCopia.Text) ;
     Ini.WriteString( 'DANFE','ProdutosPagina',edtProdPag.Text) ;
     Ini.WriteString( 'DANFE','EspessuraBorda',edtEspBorda.Text) ;
     Ini.WriteString( 'DANFE','FonteRazao'    ,edtFonteRazao.Text) ;
     Ini.WriteString( 'DANFE','FonteCampos'   ,edtFonteCampos.Text) ;
     Ini.WriteString( 'DANFE','Margem'   ,edtMargemInf.Text) ;
     Ini.WriteString( 'DANFE','MargemSup',edtMargemSup.Text) ;
     Ini.WriteString( 'DANFE','MargemDir',edtMargemDir.Text) ;
     Ini.WriteString( 'DANFE','MargemEsq',edtMargemEsq.Text) ;
     Ini.WriteString( 'DANFE','PathPDF'  ,edtPathPDF.Text) ;
     Ini.WriteInteger('DANFE','DecimaisQTD'   ,rgCasasDecimaisQtd.ItemIndex  );
     Ini.WriteInteger('DANFE','DecimaisValor' ,spedtDecimaisVUnit.Value);
     Ini.WriteBool(   'DANFE','ExibeResumo'   ,cbxExibeResumo.Checked) ;
     Ini.WriteBool(   'DANFE','ImprimirValLiq',cbxImpValLiq.Checked) ;
     Ini.WriteBool(   'DANFE','PreImpresso'   ,cbxFormCont.Checked) ;
     Ini.WriteBool(   'DANFE','MostrarStatus' ,cbxMostraStatus.Checked) ;
     Ini.WriteBool(   'DANFE','ExpandirLogo'  ,cbxExpandirLogo.Checked) ;
     Ini.WriteInteger('DANFE','Fonte'         ,rgTipoFonte.ItemIndex) ;

     Ini.WriteBool(   'Arquivos','Salvar'     ,cbxSalvarArqs.Checked);
     Ini.WriteBool(   'Arquivos','PastaMensal',cbxPastaMensal.Checked);
     Ini.WriteBool(   'Arquivos','AddLiteral' ,cbxAdicionaLiteral.Checked);
     Ini.WriteBool(   'Arquivos','EmissaoPathNFe',cbxEmissaoPathNFe.Checked);
     Ini.WriteBool(   'Arquivos','SalvarCCeCanPathEvento',cbxSalvaCCeCancelamentoPathEvento.Checked);
     Ini.WriteString( 'Arquivos','PathNFe'    ,edtPathNFe.Text) ;
     Ini.WriteString( 'Arquivos','PathCan'    ,edtPathCan.Text) ;
     Ini.WriteString( 'Arquivos','PathInu'    ,edtPathInu.Text) ;
     Ini.WriteString( 'Arquivos','PathDPEC'   ,edtPathDPEC.Text) ;
     Ini.WriteString( 'Arquivos','PathCCe'    ,edtPathCCe.Text) ;
     Ini.WriteString( 'Arquivos','PathEvento' ,edtPathEvento.Text) ;
  finally
     Ini.Free ;
  end ;

  if (OldMonitoraTXT <> rbTXT.Checked) or (OldMonitoraTCP <> rbTCP.Checked) then
  begin
     MessageDlg('O M�todo de Monitoramento do ACBrNFeMonitor foi modificado'+sLineBreak+
                'Ser� necess�rio reinicar o ACBrNFeMonitor.',
                mtInformation, [mbOk],0) ;
     Application.Terminate ;
  end ;
end;

procedure TfrmAcbrNfeMonitor.Resposta(Comando, Resposta : string );
begin
  if rbTCP.Checked then
  begin
     if Assigned( Conexao ) then
     begin
        if Assigned( Conexao.Connection ) then
        begin
           Conexao.Connection.WriteLn(Resposta) ;
           Conexao.Connection.Write(#3) ;
        end;
     end ;
  end ;

  if rbTXT.Checked then
  begin
     { Primeiro salva em Tempor�rio para que a grava��o de todos os Bytes ocorra
       antes que a aplica��o controladora do ACBrMonitor tente ler o arquivo de
       Resposta incompleto }
       TryDeleteFile( ArqSaiTMP, 1000 ) ; // Tenta apagar por at� 1 segundo

     if FileExists(ArqSaiTXT) then
        RenameFile(ArqSaiTXT, ArqSaiTMP) ; { GravaArqResp faz append se arq. existir }
        WriteToTXT(ArqSaiTMP, Resposta);
        RenameFile(ArqSaiTMP, ArqSaiTXT) ;

  end ;

  mResp.Lines.BeginUpdate ;
  mResp.Lines.Add( Comando + sLineBreak + Resposta ) ;
//  while mResp.Lines.Count > BufferMemoResposta do
//     mResp.Lines.Delete(0) ;
  mResp.Lines.EndUpdate ;

  if cbLog.Checked then
     WriteToTXT(ArqLogTXT, DateTimeToStr(now)+' - '+ Comando + sLineBreak + Resposta);
end;

procedure TfrmAcbrNfeMonitor.Processar;
var
  Linha : WideString;
begin
  if NewLines <> '' then
     mCmd.Lines.Add( NewLines ) ;

  NewLines := '' ;

  while mCmd.Lines.Count > 0 do
  begin
     Application.ProcessMessages ;

//     Linha := Trim( mCmd.Lines[0] );
//     mCmd.Lines.Delete(0);

     Linha := Trim( mCmd.Lines.Text );
     mCmd.Lines.Text := '';
     mResp.Lines.Text := '';

     if Linha <> '' then
     begin
        sbProcessando.Panels[1].Text := Linha ;

        try
           if pos('.',Linha) = 0 then              { Comandos do ACBrMonitor }
              Linha := 'NFE.'+Linha ;

           Cmd.Comando := Linha ;

           if Cmd.Objeto = 'NFE' then
              DoACBrNFe( Cmd )
           else if Cmd.Objeto = 'CTE' then
              DoACBrCTe( Cmd );

           Resposta(Linha, 'OK: '+Cmd.Resposta );
           
        except
           on E : Exception do
              Resposta(Linha, 'ERRO: '+E.Message );
        end ;

        sbProcessando.Panels[1].Text := '' ;
     end ;
  end ;
end;

procedure TfrmAcbrNfeMonitor.TrataErros(Sender: TObject; E: Exception);
begin
  mResp.Lines.Add( E.Message );
  if cbLog.Checked then
     WriteToTXT(ArqLogTXT, 'Exception: ' + E.Message);

  StatusBar1.Panels[0].Text := 'Exception' ;
end ;

procedure TfrmAcbrNfeMonitor.IconTray (var Msg: TMessage);
var
  Pt: TPoint;
begin
 if Msg.lParam = WM_RBUTTONDOWN then
  begin
     GetCursorPos (Pt);
     pmTray.Popup (Pt.x, Pt.y);
  end
 else if Msg.lParam = WM_LBUTTONDBLCLK then
  if Visible then
    Ocultar1.Click
  else
   Restaurar1.Click;
end;

procedure TfrmAcbrNfeMonitor.DoACBrTimer(Sender: TObject);
var
   SL    : TStringList ;
   RetFind   : Integer ;
   SearchRec : TSearchRec ;
   NomeArqEnt, NomeArqSai : String;
begin
  Timer1.Enabled := false;

  if Inicio then
  begin
     Inicializar ;
     exit;
  end ;

  try
     try
        NomeArqEnt := PathWithDelim(ExtractFileDir(ArqEntOrig)) + StringReplace(ExtractFileName(ArqEntOrig),ExtractFileExt(ArqEntOrig),'',[rfReplaceAll]) +'*'+ExtractFileExt(ArqEntOrig);
        RetFind := SysUtils.FindFirst( NomeArqEnt, faAnyFile, SearchRec) ;
        if (RetFind = 0) then
         begin
           NomeArqEnt := StringReplace(ExtractFileName(ArqEntOrig),ExtractFileExt(ArqEntOrig),'',[rfReplaceAll]);
           NomeArqSai := StringReplace(ExtractFileName(ArqSaiOrig),ExtractFileExt(ArqSaiOrig),'',[rfReplaceAll]);
           ArqEntTXT  := PathWithDelim(ExtractFileDir(ArqEntOrig)) + SearchRec.Name ;  { Arquivo de Requisicao }
           ArqSaiTXT  := PathWithDelim(ExtractFilePath(ArqSaiOrig))+StringReplace( ExtractFileName(LowerCase(ArqEntTXT)),LowerCase(NomeArqEnt),LowerCase(NomeArqSai),[rfReplaceAll]) ;
           ArqSaiTMP  := ChangeFileExt(ArqSaiTXT,'.tmp');
         end;  
     finally
        SysUtils.FindClose(SearchRec) ;
     end ;

     if FileExists( ArqEntTXT ) then  { Existe arquivo para ler ? }
     begin
       { Lendo em StringList tempor�rio para nao apagar comandos nao processados }
       SL := TStringList.Create ;
       try
          SL.LoadFromFile( ArqEntTXT );
          TryDeleteFile( ArqEntTXT, 1000 ) ; // Tenta apagar por at� 1 segundo

          mCmd.Lines.AddStrings( SL );
       finally
          SL.Free ;
       end ;
     end;

     Processar ;
  finally
     Timer1.Enabled := True;
  end ;
end;

procedure TfrmAcbrNfeMonitor.edOnlyNumbers(Sender: TObject;
  var Key: Char);
begin
  if not ( Key in ['0'..'9',#13,#8] ) then
     Key := #0 ;
end;

procedure TfrmAcbrNfeMonitor.FormCreate(Sender: TObject);
begin
  mResp.Clear ;
  mCmd.Clear ;
  PageControl1.ActivePage := Monitor;

  if FileExists( PathWithDelim(ExtractFilePath(Application.ExeName)) + 'banner_acbrmonitor.gif' ) then
   begin
     ACBrGIF1.LoadFromFile( PathWithDelim(ExtractFilePath(Application.ExeName)) + 'banner_acbrmonitor.gif' );
     ACBrGIF1.Transparent := True;
     ACBrGIF1.Start;
   end
  else
     ACBrGIF1.Visible := False;

  Cmd       := TACBrNFeCTeCmd.Create ;

  Inicio    := true ;
  ArqSaiTXT := '' ;
  ArqSaiTMP := '' ;
  ArqEntTXT := '' ;
  ArqLogTXT := '' ;
  ArqLogCompTXT := '';
  Conexao   := nil ;
  NewLines  := '' ;

  { Criando TrayIcon, e copiando Icone do Form }
  Icon.Handle := LoadIcon (HInstance, 'MAINICON');
  nid.cbSize := sizeof (nid);
  nid.wnd := Handle;
  nid.uID := 1; // Identificador do �cone
  nid.uCallBAckMessage := wm_IconMessage;
  nid.hIcon := Icon.Handle;
  nid.szTip := 'ACBrNFeMonitor';
  nid.uFlags := nif_Message or
  nif_Icon or nif_Tip;
  Shell_NotifyIcon (NIM_ADD, @nid);

  Caption := 'ACBrNFeMonitor2 '+ Versao + {$IFDEF ACBrNFeOpenSSL}' - OpenSSL'{$ELSE}' - CAPICOM'{$ENDIF};
  PageControl1.ActivePageIndex := 0 ;

  Application.OnException        := TrataErros ;
  Application.OnMinimize         := Ocultar1Click ;
  Application.OnRestore          := Restaurar1Click ;
  Application.Title              := Caption ;
  Application.HintHidePause      := 5000 ;

  Timer1.Enabled := True ;
end;

procedure TfrmAcbrNfeMonitor.Restaurar1Click(Sender: TObject);
begin
  Application.ShowMainForm := true ;
  Visible := true ;
  Application.Restore ;
  Application.BringToFront ;
  Application.ProcessMessages ;
end;

procedure TfrmAcbrNfeMonitor.Ocultar1Click(Sender: TObject);
begin
  Visible := false ;
  Application.ShowMainForm := false ;

  Application.ProcessMessages ;
end;

procedure TfrmAcbrNfeMonitor.FormDestroy(Sender: TObject);
begin
  Cmd.Free ;
  nid.uFlags := 0;
  Shell_NotifyIcon (NIM_DELETE, @nid);
  Timer1.Enabled := false ;
end;

procedure TfrmAcbrNfeMonitor.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin
  Timer1.Enabled := False ;

  TCPServer.OnDisconnect := nil ;
  TCPServer.Active       := False ;    { Desliga TCP }
end;

procedure TfrmAcbrNfeMonitor.FormCloseQuery(Sender: TObject;
  var CanClose: Boolean);
begin
  CanClose := True ;

  if pConfig.Visible then
  begin
     MessageDlg('Por favor Salve ou Cancele as configura��es '+#13+#10+
                'efetuadas antes de fechar o programa',
                 mtWarning,[mbOk],0 ) ;
     CanClose := false ;
     exit ;
  end ;

end;

procedure TfrmAcbrNfeMonitor.btConfigClick(Sender: TObject);
begin
  if pConfig.Visible then
   begin
     SalvarIni ;
     EscondeConfig ;
     LerIni ; { Para as altera�oes fazerem efeito }
   end
  else
     ExibeConfig ;
end;

procedure TfrmAcbrNfeMonitor.btMinimizarClick(Sender: TObject);
begin
  Ocultar1Click( Sender );
end;

procedure TfrmAcbrNfeMonitor.btCancelarClick(Sender: TObject);
begin
  EscondeConfig ;
  LerIni ;
end;

procedure TfrmAcbrNfeMonitor.rbTCPClick(Sender: TObject);
begin
  gbTCP.Enabled := rbTCP.Checked ;
  gbTXT.Enabled := rbTXT.Checked ;

  if rbTXT.Checked then
   begin
     if edENTTXT.Text = '' then
        edENTTXT.Text := 'ENTNFE.TXT' ;

     if edSAITXT.Text = '' then
        edSAITXT.Text := 'SAINFE.TXT' ;
   end
  else
   begin
     if edPortaTCP.Text = '' then
        edPortaTCP.Text := '3434' ;

     if sedConexoesTCP.Value <= 0 then
        sedConexoesTCP.Value := 1 ;
   end ;
end;

procedure TfrmAcbrNfeMonitor.cbSenhaClick(Sender: TObject);
begin
  gbSenha.Enabled := cbSenha.Checked ;
  if not cbSenha.Checked then
  begin
     fsHashSenha  := -1 ;
     edSenha.Text :=  '' ;
  end ;
end;

procedure TfrmAcbrNfeMonitor.cbLogClick(Sender: TObject);
begin
  gbLog.Enabled := cbLog.Checked ;

  if cbLog.Checked and (edLogArq.Text = '') then
     edLogArq.Text := 'LOG.TXT' ;
end;

procedure TfrmAcbrNfeMonitor.TCPServerConnect(AThread: TIdPeerThread);
begin
  sleep(100);
  Conexao := AThread;
  mCmd.Lines.Clear ;
  Resposta('','ACBrNFeMonitor2 Ver. '+ Versao + {$IFDEF ACBrNFeOpenSSL}' - OpenSSL'{$ELSE}' - CAPICOM'{$ENDIF} +sLineBreak +
              'Conectado em: '+FormatDateTime('dd/mm/yy hh:nn:ss', now )+sLineBreak+
              'M�quina: '+AThread.Connection.Socket.Binding.PeerIP+sLineBreak+
              'Esperando por comandos.');
end;

procedure TfrmAcbrNfeMonitor.TCPServerDisconnect(AThread: TIdPeerThread);
begin
  mResp.Lines.Add('ALERTA: Fim da Conex�o com: '+
                   AThread.Connection.Socket.Binding.PeerIP+
                  ' em: '+FormatDateTime('dd/mm/yy hh:nn:ss', now ) )
end;

procedure TfrmAcbrNfeMonitor.TCPServerExecute(AThread: TIdPeerThread);
Var
  SL : TStringList;
begin
  { Le o que foi enviado atravez da conexao TCP }
  SL := TStringList.Create ;
  try
     AThread.Connection.Capture( SL );
     NewLines:= SL.Text;
     Conexao := AThread ;
     AThread.Synchronize( Processar );
  finally
     SL.Free ;
  end ;
end;

procedure TfrmAcbrNfeMonitor.sbArquivoCertClick(Sender: TObject);
begin
 {$IFDEF ACBrNFeOpenSSL}
  OpenDialog1.Title := 'Selecione o Certificado';
  OpenDialog1.DefaultExt := '*.pfx';
  OpenDialog1.Filter := 'Arquivos PFX (*.pfx)|*.pfx|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ExtractFileDir(application.ExeName);
  if OpenDialog1.Execute then
   begin
    edtCaminho.Text := OpenDialog1.FileName;
   end;
  {$ELSE}
   edtCaminho.Text := ACBrNFe1.Configuracoes.Certificados.SelecionarCertificado;
  {$ENDIF}
end;

procedure TfrmAcbrNfeMonitor.sbLogoMarcaClick(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione o Logo';
  if rgModeloDanfe.ItemIndex = 0 then
   begin
     OpenDialog1.DefaultExt := '*.bmp';
     OpenDialog1.Filter := 'Arquivos BMP (*.bmp)|*.bmp|Todos os Arquivos (*.*)|*.*';
   end
  else
   begin
     OpenDialog1.DefaultExt := '*.jpg';
     OpenDialog1.Filter := 'Arquivos JPG (*.jpg)|*.jpg|Todos os Arquivos (*.*)|*.*';
   end;
  OpenDialog1.InitialDir := ExtractFileDir(application.ExeName);
  if OpenDialog1.Execute then
  begin
    edtLogoMarca.Text := OpenDialog1.FileName;
  end;

 if Length(Trim(edtLogoMarca.Text)) > 0 then
  begin
    if rgModeloDanfe.ItemIndex = 0 then
     begin
       if ExtractFileExt(edtLogoMarca.Text) <> '.bmp' then
        begin
          MessageDlg('O arquivo de logo deve ser no formato BMP.',mtError,[mbOk],0);
          edtLogoMarca.SetFocus;
        end;
     end
    else
     begin
       if ExtractFileExt(edtLogoMarca.Text) <> '.jpg' then
        begin
          MessageDlg('O arquivo de logo deve ser no formato JPG.',mtError,[mbOk],0);
          edtLogoMarca.SetFocus;
        end;
     end;
  end;
  
end;

procedure TfrmAcbrNfeMonitor.PathClick(Sender: TObject);
var
  Dir: string;
begin
  if Length(TEdit(Sender).Text) <= 0 then
     Dir := ExtractFileDir(application.ExeName)
  else
     Dir := TEdit(Sender).Text;

  if SelectDirectory(Dir, [sdAllowCreate, sdPerformCreate, sdPrompt],SELDIRHELP) then
    TEdit(Sender).Text := Dir;
end;

procedure TfrmAcbrNfeMonitor.btnStatusServClick(Sender: TObject);
begin
 ACBrNFe1.WebServices.StatusServico.Executar;
 ExibeResp(ACBrNFe1.WebServices.StatusServico.RetWS);
end;

procedure TfrmAcbrNfeMonitor.btnConsultarClick(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
    ACBrNFe1.Consultar;
    ExibeResp(ACBrNFe1.WebServices.Consulta.RetWS);
  end;
end;

procedure TfrmAcbrNfeMonitor.btnCancNFClick(Sender: TObject);
var
  idLote,vAux : String;
begin
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
    idLote := '1';
    if rgTipoCancelamento.ItemIndex = 0 then
       if not(InputQuery('WebServices Eventos: Cancelamento', 'Identificador de controle do Lote de envio do Evento', idLote)) then
          exit;

    if not(InputQuery('WebServices Cancelamento', 'Justificativa', vAux)) then
       exit;

     if rgTipoCancelamento.ItemIndex = 0 then
      begin
        ACBrNFe1.EventoNFe.Evento.Clear;
        ACBrNFe1.EventoNFe.idLote := StrToInt(idLote) ;
        with ACBrNFe1.EventoNFe.Evento.Add do
         begin
           infEvento.dhEvento := now;
           infEvento.tpEvento := teCancelamento;
           infEvento.detEvento.xJust := vAux;
        end;
        ACBrNFe1.EnviarEventoNFe(StrToInt(idLote));
        ExibeResp(ACBrNFe1.WebServices.EnvEvento.RetWS);
      end
     else
      begin
        ACBrNFe1.Cancelamento(vAux);
        ExibeResp(ACBrNFe1.WebServices.Cancelamento.RetWS);
      end;
  end;
end;

procedure TfrmAcbrNfeMonitor.btnValidarXMLClick(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
    ACBrNFe1.NotasFiscais.Valida;
    showmessage('Nota Fiscal Eletr�nica Valida');
  end;
end;

procedure TfrmAcbrNfeMonitor.btnImprimirClick(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin

    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
    NotaUtil.ConfAmbiente;
    ACBrNFe1.NotasFiscais.Imprimir;
  end;
end;

procedure TfrmAcbrNfeMonitor.btnInutilizarClick(Sender: TObject);
var
 CNPJ, Modelo, Serie, Ano, NumeroInicial, NumeroFinal, Justificativa : String;
begin
 if not(InputQuery('WebServices Inutiliza��o ', 'CNPJ',   CNPJ)) then
    exit;
 if not(InputQuery('WebServices Inutiliza��o ', 'Ano',    Ano)) then
    exit;
 if not(InputQuery('WebServices Inutiliza��o ', 'Modelo', Modelo)) then
    exit;
 if not(InputQuery('WebServices Inutiliza��o ', 'Serie',  Serie)) then
    exit;
 if not(InputQuery('WebServices Inutiliza��o ', 'N�mero Inicial', NumeroInicial)) then
    exit;
 if not(InputQuery('WebServices Inutiliza��o ', 'N�mero Final', NumeroFinal)) then
    exit;
 if not(InputQuery('WebServices Inutiliza��o ', 'Justificativa', Justificativa)) then
    exit;
    
  ACBrNFe1.WebServices.Inutiliza(CNPJ, Justificativa, StrToInt(Ano), StrToInt(Modelo), StrToInt(Serie), StrToInt(NumeroInicial), StrToInt(NumeroFinal));
  ExibeResp(ACBrNFe1.WebServices.Inutilizacao.RetWS);
end;

procedure TfrmAcbrNfeMonitor.btnEnviarClick(Sender: TObject);
var
 vAux : String;
begin
  if not(InputQuery('WebServices Enviar', 'Numero do Lote', vAux)) then
    exit;
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
    ACBrNFe1.Enviar(StrToInt(vAux));
    ExibeResp(ACBrNFe1.WebServices.Retorno.RetWS);
  end;
end;

procedure TfrmAcbrNfeMonitor.edtNumCopiaKeyPress(Sender: TObject;
  var Key: Char);
begin
  if ((Key in ['0'..'9'] = False) and (Word(Key) <> VK_BACK)) then
    Key := #0;
end;

procedure TfrmAcbrNfeMonitor.edtMargemInfKeyPress(Sender: TObject;
  var Key: Char);
begin
  if (((Key in ['0'..'9'] = False) and (Key in ['.',','] = false)) and (Word(Key) <> VK_BACK)) then
    Key := #0
  else
    if Key = '.' then
       Key := ',';  
end;

procedure TfrmAcbrNfeMonitor.sbPathPDFClick(Sender: TObject);
begin
 PathClick(edtPathPDF);
end;

procedure TfrmAcbrNfeMonitor.rgModeloDanfeClick(Sender: TObject);
begin
 if Length(Trim(edtLogoMarca.Text)) > 0 then
  begin
    if rgModeloDanfe.ItemIndex = 0 then
     begin
       if ExtractFileExt(edtLogoMarca.Text) <> '.bmp' then
        begin
          MessageDlg('O arquivo de logo deve ser no formato BMP.',mtError,[mbOk],0);
          edtLogoMarca.SetFocus;
        end;
     end
    else
     begin
       if ExtractFileExt(edtLogoMarca.Text) <> '.jpg' then
        begin
          MessageDlg('O arquivo de logo deve ser no formato JPG.',mtError,[mbOk],0);
          edtLogoMarca.SetFocus;
        end;
     end;
  end;
end;

procedure TfrmAcbrNfeMonitor.EncerrarMonitor1Click(Sender: TObject);
begin
 Close;
end;

procedure TfrmAcbrNfeMonitor.btnEnviarEmailClick(Sender: TObject);
var
  ArqPDF, vPara : String;
begin
  OpenDialog1.Title := 'Selecione a NFE';
  OpenDialog1.DefaultExt := '*-nfe.XML';
  OpenDialog1.Filter := 'Arquivos NFE (*-nfe.XML)|*-nfe.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrNFe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrNFe1.NotasFiscais.Clear;
    ACBrNFe1.NotasFiscais.LoadFromFile(OpenDialog1.FileName);
    ACBrNFe1.NotasFiscais.ImprimirPDF;
    ArqPDF := ACBrNFe1.NotasFiscais.Items[0].NFe.infNFe.ID ;

    ArqPDF := StringReplace(ACBrNFe1.NotasFiscais.Items[0].NFe.infNFe.ID,'NFe', '', [rfIgnoreCase]);
    ArqPDF :=PathWithDelim(ACBrNFe1.DANFE.PathPDF)+ ArqPDF+'.pdf';

    if not(InputQuery('Enviar Email', 'Email de Destino', vPara)) then
       exit;
    try
       if rgEmailTipoEnvio.ItemIndex = 0 then
          EnviarEmail(edtSmtpHost.Text, edtSmtpPort.Text, edtSmtpUser.Text, edtSmtpPass.Text, edtSmtpUser.Text, vPara, edtEmailAssunto.Text, OpenDialog1.FileName, ArqPDF, mmEmailMsg.Lines, cbEmailSSL.Checked)
       else
          EnviarEmailIndy(edtSmtpHost.Text, edtSmtpPort.Text, edtSmtpUser.Text, edtSmtpPass.Text, edtSmtpUser.Text, vPara, edtEmailAssunto.Text, OpenDialog1.FileName, ArqPDF, mmEmailMsg.Lines, cbEmailSSL.Checked);
    except
       on E: Exception do
        begin
          raise Exception.Create('Erro ao enviar email'+sLineBreak+E.Message);
          exit;
        end;
    end;
    ShowMessage('Email enviado com sucesso!');
  end;
end;

procedure TfrmAcbrNfeMonitor.sbPathNFeClick(Sender: TObject);
begin
 PathClick(edtPathNFe);
end;

procedure TfrmAcbrNfeMonitor.sbPathSalvarClick(Sender: TObject);
begin
 PathClick(edtPathLogs);
end;

procedure TfrmAcbrNfeMonitor.sbPathCanClick(Sender: TObject);
begin
 PathClick(edtPathCan);
end;

procedure TfrmAcbrNfeMonitor.sbPathInuClick(Sender: TObject);
begin
 PathClick(edtPathInu);
end;

procedure TfrmAcbrNfeMonitor.sbPathDPECClick(Sender: TObject);
begin
 PathClick(edtPathDPEC);
end;

procedure TfrmAcbrNfeMonitor.cbxAjustarAutClick(Sender: TObject);
begin
 edtAguardar.Enabled := not cbxAjustarAut.Checked;
end;

procedure TfrmAcbrNfeMonitor.cbxPastaMensalClick(Sender: TObject);
begin
 cbxEmissaoPathNFe.Enabled := cbxPastaMensal.Checked;
end;

procedure TfrmAcbrNfeMonitor.ACBrNFe1GerarLog(const Mensagem: String);
begin
  if cbLogComp.Checked then
     WriteToTXT(ArqLogCompTXT, Mensagem + sLineBreak );
end;

procedure TfrmAcbrNfeMonitor.Image1Click(Sender: TObject);
begin
 OpenURL('http://acbr.sf.net');
end;

procedure TfrmAcbrNfeMonitor.btnDoarClick(Sender: TObject);
begin
 OpenURL('http://acbr.sourceforge.net/drupal/?q=node/14');
end;

procedure TfrmAcbrNfeMonitor.VerificaDiretorios;
begin
  if cbxSalvarArqs.Checked then
   begin
     cbxPastaMensal.Enabled     := True;
     cbxAdicionaLiteral.Enabled := True;
     cbxEmissaoPathNFe.Enabled  := True;
     cbxSalvaCCeCancelamentoPathEvento.Enabled  := True;
     edtPathNFe.Enabled   := True;
     edtPathCan.Enabled   := True;
     edtPathInu.Enabled   := True;
     edtPathDPEC.Enabled  := True;
     edtPathCCe.Enabled   := True;
     edtPathEvento.Enabled := True;
     sbPathNFe.Enabled   := True;
     sbPathCan.Enabled   := True;
     sbPathInu.Enabled   := True;
     sbPathDPEC.Enabled  := True;
     sbPathCCe.Enabled   := True;
     sbPathEvento.Enabled := True;
   end
  else
   begin
     cbxPastaMensal.Enabled     := False;
     cbxAdicionaLiteral.Enabled := False;
     cbxEmissaoPathNFe.Enabled  := False;
     cbxSalvaCCeCancelamentoPathEvento.Enabled  := True;
     edtPathNFe.Enabled   := False;
     edtPathCan.Enabled   := False;
     edtPathInu.Enabled   := False;
     edtPathDPEC.Enabled  := False;
     edtPathCCe.Enabled   := False;
     edtPathEvento.Enabled := False;
     sbPathNFe.Enabled   := False;
     sbPathCan.Enabled   := False;
     sbPathInu.Enabled   := False;
     sbPathDPEC.Enabled  := False;
     sbPathCCe.Enabled   := False;
     sbPathEvento.Enabled := False;
   end
end;

procedure TfrmAcbrNfeMonitor.cbxSalvarArqsClick(Sender: TObject);
begin
  VerificaDiretorios;
end;

procedure TfrmAcbrNfeMonitor.ACBrGIF1Click(Sender: TObject);
begin
   OpenURL('http://www.djsystem.com.br/acbr/sac/')
end;

procedure TfrmAcbrNfeMonitor.sbPathCCeClick(Sender: TObject);
begin
 PathClick(edtPathCCe);
end;

procedure TfrmAcbrNfeMonitor.sbPathEventoClick(Sender: TObject);
begin
 PathClick(edtPathEvento);
end;

procedure TfrmAcbrNfeMonitor.ACBrCTe1GerarLog(const Mensagem: String);
begin
  if cbLogComp.Checked then
     WriteToTXT(ArqLogCompTXT, Mensagem + sLineBreak );
end;

procedure TfrmAcbrNfeMonitor.btnStatusServCTeClick(Sender: TObject);
begin
 ACBrCTe1.WebServices.StatusServico.Executar;
 ExibeResp(ACBrCTe1.WebServices.StatusServico.RetWS);
end;

procedure TfrmAcbrNfeMonitor.btnEnviarCTeClick(Sender: TObject);
var
 vAux : String;
begin
  if not(InputQuery('WebServices Enviar', 'Numero do Lote', vAux)) then
    exit;
  OpenDialog1.Title := 'Selecione a CTe';
  OpenDialog1.DefaultExt := '*-cte.XML';
  OpenDialog1.Filter := 'Arquivos CTe (*-cte.XML)|*-cte.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrCTe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrCTe1.Conhecimentos.Clear;
    ACBrCTe1.Conhecimentos.LoadFromFile(OpenDialog1.FileName);
    ACBrNFe1.Enviar(StrToInt(vAux));
    ExibeResp(ACBrCTe1.WebServices.Retorno.RetWS);
  end;
end;

procedure TfrmAcbrNfeMonitor.btnConsultarCTeClick(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione a CTE';
  OpenDialog1.DefaultExt := '*-cte.XML';
  OpenDialog1.Filter := 'Arquivos CTE (*-cte.XML)|*-cte.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrCTe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrCTe1.Conhecimentos.Clear;
    ACBrCTe1.Conhecimentos.LoadFromFile(OpenDialog1.FileName);
    ACBrCTe1.Consultar;
    ExibeResp(ACBrCTe1.WebServices.Consulta.RetWS);
  end;
end;

procedure TfrmAcbrNfeMonitor.btnCancelarCTeClick(Sender: TObject);
var
  vAux : String;
begin
  OpenDialog1.Title := 'Selecione a CTE';
  OpenDialog1.DefaultExt := '*-cte.XML';
  OpenDialog1.Filter := 'Arquivos CTE (*-cte.XML)|*-cte.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrCTe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrCTe1.Conhecimentos.Clear;
    ACBrCTe1.Conhecimentos.LoadFromFile(OpenDialog1.FileName);
    if not(InputQuery('WebServices Cancelamento', 'Justificativa', vAux)) then
       exit;
     ACBrCTe1.Cancelamento(vAux);
     ExibeResp(ACBrCTe1.WebServices.Cancelamento.RetWS);
  end;
end;

procedure TfrmAcbrNfeMonitor.btnValidarXMLCTeClick(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione a CTE';
  OpenDialog1.DefaultExt := '*-cte.XML';
  OpenDialog1.Filter := 'Arquivos CTE (*-cte.XML)|*-cte.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrCTe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrCTe1.Conhecimentos.Clear;
    ACBrCTe1.Conhecimentos.LoadFromFile(OpenDialog1.FileName);
    ACBrCTe1.Conhecimentos.Valida;
    ShowMessage('Conhecimento de Transporte Eletr�nico Valido');
  end;
end;

procedure TfrmAcbrNfeMonitor.btnImprimirCTeClick(Sender: TObject);
begin
  OpenDialog1.Title := 'Selecione a CTE';
  OpenDialog1.DefaultExt := '*-cte.XML';
  OpenDialog1.Filter := 'Arquivos CTE (*-cte.XML)|*-cte.XML|Arquivos XML (*.XML)|*.XML|Todos os Arquivos (*.*)|*.*';
  OpenDialog1.InitialDir := ACBrCTe1.Configuracoes.Geral.PathSalvar;
  if OpenDialog1.Execute then
  begin
    ACBrCTe1.Conhecimentos.Clear;
    ACBrCTe1.Conhecimentos.LoadFromFile(OpenDialog1.FileName);
    ACBrCTe1.Conhecimentos.Imprimir;
  end;
end;

procedure TfrmAcbrNfeMonitor.btnInutilizarCTeClick(Sender: TObject);
var
 CNPJ, Modelo, Serie, Ano, NumeroInicial, NumeroFinal, Justificativa : String;
begin
 if not(InputQuery('WebServices Inutiliza��o ', 'CNPJ',   CNPJ)) then
    exit;
 if not(InputQuery('WebServices Inutiliza��o ', 'Ano',    Ano)) then
    exit;
 if not(InputQuery('WebServices Inutiliza��o ', 'Modelo', Modelo)) then
    exit;
 if not(InputQuery('WebServices Inutiliza��o ', 'Serie',  Serie)) then
    exit;
 if not(InputQuery('WebServices Inutiliza��o ', 'N�mero Inicial', NumeroInicial)) then
    exit;
 if not(InputQuery('WebServices Inutiliza��o ', 'N�mero Final', NumeroFinal)) then
    exit;
 if not(InputQuery('WebServices Inutiliza��o ', 'Justificativa', Justificativa)) then
    exit;

  ACBrCTe1.WebServices.Inutiliza(CNPJ, Justificativa, StrToInt(Ano), StrToInt(Modelo), StrToInt(Serie), StrToInt(NumeroInicial), StrToInt(NumeroFinal));
  ExibeResp(ACBrCTe1.WebServices.Inutilizacao.RetWS);
end;

end.

