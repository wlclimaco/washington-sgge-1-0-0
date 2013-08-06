unit BrvEcfBematech;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  BrvString;

type
  TBrvEcfBematech = class(TComponent)
  private
    { Private declarations }
    gBrString: TBrvString;
    procedure VerificarErroComunicacao(VrReturn : Integer);
    procedure CriarArquivoIni(NrPorta : Byte);
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor Destroy; override;
    function   AbrePortaSerial(NrPorta : Byte): Integer;
    function   FechaPortaSerial: Integer;
    function   ReducaoZ: Integer;
    function   LeituraX: Integer;
    function   MemoriaFiscalPorData(DtInicio : TDate; DtFinal : TDate): Integer;
    function   MemoriaFiscalPorReducao(NrInicio : Integer; NrFinal : Integer):
                                                                        Integer;
    function   AjustarHorarioVerao: Integer;
    function   CancelarUltimoCupom: Integer;
    function   CancelarCupomEmAndamento: Integer;
    function   ImprimirCabecalho: Integer;
    function   EnviarProdutoECF(CdBarras : String; DsProdut : String;
                                 TxProdut : String; QtProdut : Real;
                                 VrUnitar : Real): Integer;
    function   EstornarProduto(NrItem : Integer): Integer;
    function   FinalizarImpressaoCupom(VrDescon : Real;    VrPago   : Real;
                   DsForPag : String;  DsMenRod : String;  CdEmpres : Integer;
                   NrPedido : Integer; NrCupom  : Integer; CdClient : Integer;
                   NmClient : String;  NrCpfCli : String;  NrRgClie : String;
                   DsEndCli : String;  DsBaiCli : String;  NrCepCli : String;
                   NmCidCli : String;  UfCidCli : String;  SnDadCli : Boolean;
                   TpPagto  : String): Integer;
    function  AbrirGaveta: Integer;
  published
    { Published declarations }
  end;

procedure Register;

implementation

function Bematech_FI_AbrePortaSerial : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_FechaPortaSerial: Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_ReducaoZ(DtEcf : String; HoEcf : String)
                                     : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_LeituraX        : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_LeituraMemoriaFiscalData(DtInicio : String;
                                              DtFinal  : String)
                                     : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_LeituraMemoriaFiscalReducao(NrInicio : String;
                                                 NrFinal  : String)
                                     : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_DataHoraImpressora(DtEcf : String; HoEcf : String)
                                     : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_CancelaCupom    : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_AbreCupom(NrCpfCgc : String)
                                     : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_VendeItem(CdBarras : String; DsProdut : String;
                               TxProdut : String; TpQuanti : String;
                               QtProdut : String; QtCasDec : Integer;
                               VrUnitar : String; TpDescon : String;
                               VrDescon : String)
                                     : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_CancelaItemGenerico(NrItem : String)
                                     : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_IniciaFechamentoCupom(TpAcrDes : String; TpValPer : String;
                                           VrAcrDes : String)
                                     : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_EfetuaFormaPagamento(DsForPag : String; VrForPag : String)
                                     : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_TerminaFechamentoCupom(DsMensag : String)
                                     : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_AcionaGaveta    : Integer; stdcall; external 'BemaFI32.dll';
function Bematech_FI_ProgramaHorarioVerao
                                     : Integer; stdcall; external 'BemaFI32.dll';

procedure Register;
begin
      RegisterComponents('Bravo ECF', [TBrvEcfBematech]);
end;

constructor TBrvEcfBematech.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
      gBrString := TBrvString.Create(Self);
end;

destructor TBrvEcfBematech.Destroy;
begin
      gBrString.Destroy;
      inherited  destroy;
end;

procedure TBrvEcfBematech.VerificarErroComunicacao(VrReturn : Integer);
var DsMensag : String;
begin
      if  VrReturn <> 1 then
      begin
            DsMensag := '';

            case VrReturn of
                 0: DsMensag := 'Erro de comunicação.';
                -1: DsMensag := 'Erro de execução da função.';
                -2: DsMensag := 'Parâmetro inválido.';
                -3: DsMensag := 'Alíquota não programada.';
                -4: DsMensag := 'Arquivo .ini não encontrado ou parâmetro ' +
                                'inválido para o nome da porta.';

                -5: DsMensag := 'Erro ao abrir a porta.';
                -6: DsMensag := 'Impressora desligada ou desconectada.';
                -7: DsMensag := 'Banco não localizado no arquivo de ' +
                                'configuração arquivo .ini';
                -8: DsMensag := 'Erro ao criar ou gravar no arquivo ' +
                                'status.txt ou retorno.txt';
                -9: DsMensag := 'Erro ao fechar a porta.';

               -18: DsMensag := 'Não foi possível abrir arquivo .001 ou .sts.';
               -19: DsMensag := 'Parâmetro do arquivo .001 diferentes.';
               -20: DsMensag := 'Operação finalizada pelo operador.';
               -21: DsMensag := 'Transação Não Ok.';
               -22: DsMensag := 'Não foi possível terminar a impressão.';
               -23: DsMensag := 'Não foi possível terminar a operação.';
              else  DsMensag := 'Erro desconhecido número: ' +
                                                             IntToStr(VrReturn);
            end;

            MessageDlg(DsMensag, mtInformation, [mbOk], 0);
      end;
end;

function TBrvEcfBematech.AbrePortaSerial(NrPorta : Byte): Integer;
begin
      CriarArquivoIni(NrPorta);
      Result := Bematech_FI_AbrePortaSerial;
      VerificarErroComunicacao(Result);
end;

procedure TBrvEcfBematech.CriarArquivoIni(NrPorta : Byte);
var DsIni  : TextFile;
    DsPath : String;
begin
      DsPath := ExtractFileDir(Application.ExeName) + '\';

      AssignFile(DsIni, DsPath + 'BemaFI32.ini');
      Rewrite(DsIni);
      Writeln(DsIni, '[Sistema]');
      Writeln(DsIni, 'Porta=DEFAULT');
      Writeln(DsIni, 'Path=' + DsPath);
      Writeln(DsIni, 'Status=0');
      Writeln(DsIni, 'Retorno=0');
      Writeln(DsIni, 'StatusFuncao=0');
      Writeln(DsIni, 'ControlePorta=1');
      Writeln(DsIni, 'ModeloImp=BEMATECH');
      Writeln(DsIni, 'ConfigRede=0');
      Writeln(DsIni, 'ModoGaveta=0');
      Writeln(DsIni, 'Log=0');
      Writeln(DsIni, ' ');
      Writeln(DsIni, '[MFD]');
      Writeln(DsIni, 'Impressora=0');
      Writeln(DsIni, 'StatusErro=0');
      Writeln(DsIni, ' ');
      Writeln(DsIni, '[Opcional]');
      Writeln(DsIni, 'Favorecido=');
      Writeln(DsIni, 'Cidade=');
      Writeln(DsIni, ' ');
      Writeln(DsIni, '[Formato]');
      Writeln(DsIni, 'default=01,05,07,10,13,92,17,09,11,50,31,40,65');
      Writeln(DsIni, '000=51,04,01,05,06,60,65,81,01,06,08,11,14');
      Writeln(DsIni, '001=51,10,01,06,18,50,54,71,02,05,08,10,12');
      Writeln(DsIni, '003=49,08,01,05,18,52,55,72,01,05,07,09,12');
      Writeln(DsIni, '004=52,09,01,05,18,50,53,72,02,06,09,11,13');
      Writeln(DsIni, '006=56,10,01,05,15,43,48,72,01,06,08,10,13');
      Writeln(DsIni, '008=56,17,01,07,18,50,55,71,03,06,09,11,13');
      Writeln(DsIni, '021=52,12,01,04,18,49,53,71,02,07,09,11,13');
      Writeln(DsIni, '022=52,07,01,04,15,44,49,71,02,06,08,10,13');
      Writeln(DsIni, '024=51,07,01,05,18,48,52,72,01,05,07,09,12');
      Writeln(DsIni, '027=52,12,01,06,18,45,55,71,01,05,08,10,12');
      Writeln(DsIni, '028=55,06,01,05,18,50,53,71,01,05,08,10,12');
      Writeln(DsIni, '029=55,12,01,04,18,50,55,72,01,06,08,10,13');
      Writeln(DsIni, '031=55,13,01,04,18,45,49,69,01,05,08,10,12');
      Writeln(DsIni, '032=56,14,01,04,18,45,49,71,02,05,07,09,12');
      Writeln(DsIni, '033=48,17,01,06,18,46,50,71,02,06,08,11,13');
      Writeln(DsIni, '034=49,14,01,04,15,45,57,71,01,05,07,09,11');
      Writeln(DsIni, '035=59,07,01,06,18,52,56,72,02,06,08,10,13');
      Writeln(DsIni, '036=58,11,01,05,18,50,53,72,02,06,08,10,12');
      Writeln(DsIni, '037=58,08,01,05,18,51,54,72,02,06,08,10,12');
      Writeln(DsIni, '038=56,10,01,04,18,51,56,72,02,07,10,12,14');
      Writeln(DsIni, '039=49,24,01,04,18,45,56,70,01,05,07,09,11');
      Writeln(DsIni, '041=56,09,01,04,18,54,61,72,03,07,09,12,14');
      Writeln(DsIni, '047=52,08,01,05,18,47,50,72,01,05,07,10,12');
      Writeln(DsIni, '048=54,12,01,04,18,45,49,68,02,05,08,10,13');
      Writeln(DsIni, '059=50,15,01,05,18,55,59,72,01,05,07,09,11');
      Writeln(DsIni, '070=54,05,01,05,18,48,52,72,02,06,08,10,12');
      Writeln(DsIni, '104=56,13,01,04,18,48,53,72,01,04,07,10,12');
      Writeln(DsIni, '106=52,12,01,05,18,52,55,71,02,07,09,11,13');
      Writeln(DsIni, '151=54,06,01,04,18,47,52,71,01,05,07,10,12');
      Writeln(DsIni, '153=51,09,01,05,18,51,55,72,01,05,08,10,13');
      Writeln(DsIni, '168=53,05,01,05,18,54,57,71,02,06,08,11,13');
      Writeln(DsIni, '200=52,06,01,05,18,47,52,71,01,05,07,10,12');
      Writeln(DsIni, '201=52,11,01,04,18,47,51,71,01,05,07,09,11');
      Writeln(DsIni, '206=56,14,01,06,18,53,56,72,01,06,08,10,13');
      Writeln(DsIni, '207=50,04,01,05,18,48,52,71,02,06,08,11,13');
      Writeln(DsIni, '211=48,11,01,05,18,52,56,71,03,07,09,12,14');
      Writeln(DsIni, '215=55,06,01,05,18,51,54,71,02,05,08,10,13');
      Writeln(DsIni, '220=56,09,01,05,18,49,53,71,02,05,08,10,12');
      Writeln(DsIni, '230=50,12,01,05,18,54,58,71,02,05,08,10,13');
      Writeln(DsIni, '231=52,12,01,05,18,53,58,72,02,06,08,10,12');
      Writeln(DsIni, '237=50,01,01,04,18,50,54,71,02,06,09,11,14');
      Writeln(DsIni, '244=48,14,01,04,18,49,53,71,03,06,09,11,13');
      Writeln(DsIni, '254=51,09,01,05,18,53,56,71,01,05,08,11,14');
      Writeln(DsIni, '275=51,07,01,04,18,46,52,68,03,08,10,12,14');
      Writeln(DsIni, '282=56,12,01,05,18,50,54,71,02,06,08,10,13');
      Writeln(DsIni, '291=52,12,01,05,18,47,49,71,02,06,08,10,12');
      Writeln(DsIni, '294=50,05,01,05,18,54,56,71,02,05,07,10,12');
      Writeln(DsIni, '302=51,07,01,05,18,47,51,71,02,06,08,10,13');
      Writeln(DsIni, '308=57,11,01,06,18,47,50,72,02,06,08,10,12');
      Writeln(DsIni, '320=54,06,01,04,18,48,51,72,02,05,08,10,13');
      Writeln(DsIni, '334=54,06,01,04,18,54,57,71,02,06,08,10,12');
      Writeln(DsIni, '341=54,08,01,05,18,50,54,72,02,06,09,12,15');
      Writeln(DsIni, '346=54,12,01,05,18,54,57,71,02,05,08,10,12');
      Writeln(DsIni, '347=53,15,01,04,18,47,51,72,02,06,09,11,14');
      Writeln(DsIni, '351=52,14,01,05,18,55,58,72,01,05,07,10,12');
      Writeln(DsIni, '353=52,07,01,05,18,53,58,71,02,05,07,10,12');
      Writeln(DsIni, '356=52,11,01,04,18,45,49,71,01,05,07,10,12');
      Writeln(DsIni, '369=47,07,01,05,18,51,55,71,02,06,08,10,12');
      Writeln(DsIni, '370=52,06,01,05,18,47,50,71,01,05,07,10,12');
      Writeln(DsIni, '372=51,07,01,04,18,46,49,71,02,06,08,11,13');
      Writeln(DsIni, '376=54,07,01,04,18,54,58,72,02,06,08,10,12');
      Writeln(DsIni, '388=46,09,01,06,18,48,52,72,02,06,09,11,14');
      Writeln(DsIni, '389=52,06,01,05,18,53,58,72,02,07,09,12,14');
      Writeln(DsIni, '392=49,12,01,05,18,54,58,72,02,05,07,11,13');
      Writeln(DsIni, '394=51,05,01,05,18,51,55,71,01,05,07,09,13');
      Writeln(DsIni, '399=54,12,01,04,18,52,57,72,01,05,07,10,12');
      Writeln(DsIni, '409=55,12,01,04,23,52,58,71,04,07,09,11,13');
      Writeln(DsIni, '415=54,12,01,06,18,50,54,72,03,07,10,12,14');
      Writeln(DsIni, '420=54,08,01,04,18,50,54,72,02,06,08,10,13');
      Writeln(DsIni, '422=58,17,03,07,18,52,58,72,03,06,08,11,13');
      Writeln(DsIni, '424=58,12,01,04,18,50,55,71,02,06,09,11,13');
      Writeln(DsIni, '434=56,08,01,05,18,50,54,72,02,06,09,11,13');
      Writeln(DsIni, '453=54,12,01,05,18,51,56,72,03,07,10,12,14');
      Writeln(DsIni, '456=48,11,01,05,18,47,50,71,02,06,08,10,12');
      Writeln(DsIni, '464=51,16,01,05,18,56,58,72,02,06,09,11,13');
      Writeln(DsIni, '472=53,12,01,05,18,50,53,71,02,06,09,10,14');
      Writeln(DsIni, '477=55,08,01,05,18,52,57,72,03,07,09,11,14');
      Writeln(DsIni, '479=53,07,01,05,18,50,53,71,02,06,08,10,12');
      Writeln(DsIni, '483=52,08,01,05,18,47,50,71,02,05,07,09,11');
      Writeln(DsIni, '487=58,17,01,05,18,48,52,72,02,06,08,11,13');
      Writeln(DsIni, '494=51,09,01,05,18,50,53,71,02,06,08,10,13');
      Writeln(DsIni, '602=56,10,01,03,18,47,52,66,02,05,07,10,13');
      Writeln(DsIni, '603=52,05,01,02,18,51,56,72,03,08,10,13,17');
      Writeln(DsIni, '607=51,09,01,05,18,53,56,72,02,05,08,10,12');
      Writeln(DsIni, '610=55,15,01,05,18,53,58,71,01,06,08,10,12');
      Writeln(DsIni, '630=49,05,01,05,18,47,52,71,01,06,08,10,13');
      Writeln(DsIni, '718=51,07,01,05,18,48,53,71,01,06,08,10,13');
      Writeln(DsIni, '756=51,10,01,06,18,50,54,71,02,05,08,10,12');
      Writeln(DsIni, '995=44,10,01,03,32,30,30,25,20,04,06,09,10');
      Writeln(DsIni, '996=61,10,01,03,57,30,30,25,16,04,06,09,02');
      Writeln(DsIni, ' ');
      Writeln(DsIni, '[FormatoYanco]');
      Writeln(DsIni, '999=61,12,02,03,23,50,61,78,04,09,13,18,22,10');
      Writeln(DsIni, '000=38,03,03,03,00,11,35,78,12,17,22,26,31,05');
      Writeln(DsIni, '001=61,12,02,03,23,50,61,78,04,09,13,18,22,10');
      Writeln(DsIni, '003=60,14,02,04,26,54,63,78,12,22,31,44,53,05');
      Writeln(DsIni, '004=62,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '006=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '007=61,10,01,02,19,47,60,76,04,09,14,18,22,10');
      Writeln(DsIni, '008=59,17,05,06,25,53,62,78,07,11,16,20,24,10');
      Writeln(DsIni, '020=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '021=62,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '022=61,15,03,06,25,53,61,78,07,21,30,39,52,05');
      Writeln(DsIni, '024=62,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '026=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '027=61,10,00,01,20,47,60,78,04,09,15,18,22,10');
      Writeln(DsIni, '028=61,10,01,01,20,47,60,78,04,09,15,18,22,10');
      Writeln(DsIni, '029=63,13,01,03,26,53,62,78,07,25,35,44,56,05');
      Writeln(DsIni, '030=62,10,00,01,19,47,60,78,03,09,14,18,21,10');
      Writeln(DsIni, '031=61,17,03,05,22,50,60,78,13,23,37,46,54,05');
      Writeln(DsIni, '032=62,11,00,01,20,47,60,78,07,21,35,44,53,05');
      Writeln(DsIni, '033=56,15,02,04,22,49,59,78,05,11,14,18,23,10');
      Writeln(DsIni, '034=62,10,00,01,20,47,60,78,07,23,36,45,54,05');
      Writeln(DsIni, '035=62,10,00,00,22,49,60,78,04,09,15,18,22,10');
      Writeln(DsIni, '036=62,12,02,03,23,50,61,78,07,16,29,39,48,05');
      Writeln(DsIni, '037=62,10,00,01,20,47,60,78,07,22,35,44,54,05');
      Writeln(DsIni, '038=60,11,01,02,22,50,60,78,12,26,39,49,58,05');
      Writeln(DsIni, '039=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '041=60,11,02,04,26,53,63,77,07,11,16,20,24,20');
      Writeln(DsIni, '043=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '047=62,10,00,01,20,47,60,78,07,22,35,44,54,05');
      Writeln(DsIni, '048=59,10,01,02,22,49,59,78,11,26,35,44,57,05');
      Writeln(DsIni, '059=62,10,00,01,20,47,60,78,07,22,35,44,54,05');
      Writeln(DsIni, '070=62,10,00,01,20,47,60,78,07,21,35,44,53,05');
      Writeln(DsIni, '104=59,17,02,03,27,54,64,78,04,09,13,17,20,10');
      Writeln(DsIni, '106=63,13,02,03,25,53,62,78,08,26,34,49,57,05');
      Writeln(DsIni, '109=62,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '111=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '113=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '116=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '148=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '150=62,11,00,01,20,47,60,78,07,21,35,44,53,05');
      Writeln(DsIni, '151=61,12,02,02,23,50,60,78,07,21,30,39,48,05');
      Writeln(DsIni, '152=63,11,00,01,20,47,60,78,04,09,15,18,22,10');
      Writeln(DsIni, '153=64,13,02,04,25,52,62,78,04,09,15,18,22,10');
      Writeln(DsIni, '164=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '165=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '166=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '168=59,11,01,02,24,51,62,78,12,26,35,44,53,05');
      Writeln(DsIni, '171=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '175=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '184=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '200=58,12,01,02,24,52,59,78,08,25,39,52,65,05');
      Writeln(DsIni, '201=59,14,02,03,21,48,60,78,12,21,30,39,52,05');
      Writeln(DsIni, '202=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '204=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '205=61,10,00,01,18,47,60,78,08,24,38,47,55,05');
      Writeln(DsIni, '206=59,17,02,03,26,54,64,78,11,21,30,39,52,05');
      Writeln(DsIni, '207=62,11,01,02,20,47,61,78,04,09,14,18,22,10');
      Writeln(DsIni, '208=62,07,10,00,01,20,47,60,78,22,35,44,53,05');
      Writeln(DsIni, '209=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '210=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '211=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '212=61,10,01,02,20,47,60,78,04,10,15,19,22,10');
      Writeln(DsIni, '213=62,10,00,01,20,47,60,56,78,07,22,35,44,05');
      Writeln(DsIni, '214=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '215=57,16,02,04,22,49,60,78,12,26,35,44,52,05');
      Writeln(DsIni, '216=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '217=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '219=62,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '220=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '221=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '222=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '223=62,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '224=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '225=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '226=61,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '229=62,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '230=60,14,01,02,26,53,63,78,11,26,35,44,57,05');
      Writeln(DsIni, '233=62,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '237=58,13,00,02,22,50,61,78,06,11,16,20,23,10');
      Writeln(DsIni, '239=62,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '242=62,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '251=62,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '252=62,10,00,01,19,47,60,78,08,22,35,44,53,05');
      Writeln(DsIni, '254=62,10,00,01,19,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '267=62,10,00,01,20,47,60,78,03,09,15,18,21,10');
      Writeln(DsIni, '275=59,11,04,03,23,51,60,78,05,13,18,22,25,20');
      Writeln(DsIni, '291=57,16,03,04,23,49,59,78,06,11,15,18,22,10');
      Writeln(DsIni, '294=60,14,02,04,27,54,63,78,12,21,31,44,53,05');
      Writeln(DsIni, '334=61,13,02,03,24,54,64,78,04,09,13,16,20,10');
      Writeln(DsIni, '341=58,11,03,04,25,53,62,78,04,11,16,20,25,10');
      Writeln(DsIni, '347=59,14,01,03,26,53,63,78,10,25,35,44,56,05');
      Writeln(DsIni, '351=62,16,01,03,26,53,62,78,07,21,32,40,52,05');
      Writeln(DsIni, '353=70,01,01,01,36,54,63,80,02,06,10,14,17,10');
      Writeln(DsIni, '361=62,10,00,01,20,47,60,78,07,22,35,44,53,05');
      Writeln(DsIni, '366=59,15,02,02,23,50,61,78,12,25,39,48,57,05');
      Writeln(DsIni, '369=61,12,02,03,23,50,62,78,08,21,35,44,53,05');
      Writeln(DsIni, '389=61,13,04,04,26,54,63,78,13,30,40,53,61,05');
      Writeln(DsIni, '392=60,14,02,03,21,48,60,78,11,25,34,43,56,05');
      Writeln(DsIni, '399=62,21,05,06,26,53,63,78,04,09,13,18,22,10');
      Writeln(DsIni, '409=57,19,04,06,23,51,61,78,07,13,16,20,23,10');
      Writeln(DsIni, '412=62,11,00,01,20,47,60,78,09,21,34,44,54,05');
      Writeln(DsIni, '415=61,16,03,04,25,53,62,77,05,11,16,20,23,10');
      Writeln(DsIni, '434=61,14,03,09,24,51,61,78,05,09,14,18,23,10');
      Writeln(DsIni, '477=62,13,03,06,26,53,61,78,07,12,16,22,25,10');
      Writeln(DsIni, '479=61,13,01,03,22,50,60,78,04,09,13,17,22,10');
      Writeln(DsIni, '605=60,13,02,04,26,54,63,78,12,21,30,44,53,05');
      Writeln(DsIni, ' ');
      Writeln(DsIni, '[Copia]');
      Writeln(DsIni, 'Banco=');
      Writeln(DsIni, 'Valor=');
      Writeln(DsIni, 'Favorecido=');
      Writeln(DsIni, 'Cidade=');
      Writeln(DsIni, 'Data=');
      Writeln(DsIni, 'ImpressaoVerso=');
      Writeln(DsIni, 'Linhas=');
      Writeln(DsIni, 'Mensagem=');
      Writeln(DsIni, ' ');
      Writeln(DsIni, '[RelatorioTipo60]');
      Writeln(DsIni, 'COOInicial=');
      Writeln(DsIni, 'COOFinal=');
      Writeln(DsIni, 'GTInicial=');
      Writeln(DsIni, 'GTFinal=');
      Writeln(DsIni, ' ');
      Writeln(DsIni, '[TEF]');
      Writeln(DsIni, 'REQ=C:\TEF_DIAL\REQ');
      Writeln(DsIni, 'RESP=C:\TEF_DIAL\RESP');
      Writeln(DsIni, 'STATUS=0');
      Writeln(DsIni, 'LINHAS=13');
      Writeln(DsIni, 'BYTES=0');
      Writeln(DsIni, 'VIAS=1');
      Writeln(DsIni, '                                             ');
      Writeln(DsIni, '[CONFIG]');
      Writeln(DsIni, 'InicioDia=1');
      Writeln(DsIni, 'DATARED=');
      Writeln(DsIni, 'TOTSUPR=');
      Writeln(DsIni, 'MOEDASING=Real');
      Writeln(DsIni, 'MOEDAPLU=Reais');
      Writeln(DsIni, ' ');
      Writeln(DsIni, '[ReducaoYanco]');
      Writeln(DsIni, 'GT=');
      Writeln(DsIni, 'COO=');
      Writeln(DsIni, 'CANC=');
      Writeln(DsIni, 'ACRE=');
      Writeln(DsIni, 'DESC=');
      Writeln(DsIni, 'ALIQ=');
      Writeln(DsIni, 'SANG=');
      Writeln(DsIni, 'SUPR=');
      Writeln(DsIni, 'TOTPARC=');
      Writeln(DsIni, 'TOTNSUJ=');
      Writeln(DsIni, 'CONTNSU=');
      Writeln(DsIni, 'CONTOPN=');
      Writeln(DsIni, 'NUMALIQ=');
      Writeln(DsIni, 'DATAMOV=');

      Close(DsIni);                                                
end;                                                               
                                                                   
function TBrvEcfBematech.FechaPortaSerial: Integer;
begin
      Result := Bematech_FI_FechaPortaSerial;
      VerificarErroComunicacao(Result);
end;

function TBrvEcfBematech.ReducaoZ: Integer;
var DtEcf : String;
    HoEcf : String;
begin
      DtEcf := '      ';
      HoEcf := '      ';
      Result := Bematech_FI_DataHoraImpressora(DtEcf, HoEcf);
      VerificarErroComunicacao(Result);
      Result := Bematech_FI_ReducaoZ(DtEcf, HoEcf);
      VerificarErroComunicacao(Result);
end;

function TBrvEcfBematech.LeituraX: Integer;
begin
      Result := Bematech_FI_LeituraX;
      VerificarErroComunicacao(Result);
end;

function TBrvEcfBematech.MemoriaFiscalPorData(DtInicio : TDate;
                                                      DtFinal : TDate): Integer;
begin
      Result := Bematech_FI_LeituraMemoriaFiscalData(pchar(DateToStr(DtInicio)),
                                                     pchar(DateToStr(DtFinal)));
      VerificarErroComunicacao(Result);
end;

function TBrvEcfBematech.MemoriaFiscalPorReducao(NrInicio : Integer;
                                                   NrFinal : Integer): Integer;
var DsInicio : String;
    DsFinal  : String;
begin
      DsInicio := FormatFloat('0000', NrInicio);
      DsFinal  := FormatFloat('0000', NrFinal);
      Result   := Bematech_FI_LeituraMemoriaFiscalReducao(pchar(DsInicio),
                                                          pchar(DsFinal));
      VerificarErroComunicacao(Result);
end;

function TBrvEcfBematech.AjustarHorarioVerao: Integer;
begin
      Result := Bematech_FI_ProgramaHorarioVerao;
      VerificarErroComunicacao(Result);
end;

function TBrvEcfBematech.CancelarUltimoCupom: Integer;
begin
      Result := Bematech_FI_CancelaCupom;
      VerificarErroComunicacao(Result);
end;

function TBrvEcfBematech.CancelarCupomEmAndamento: Integer;
begin
      Result := Bematech_FI_CancelaCupom;
      VerificarErroComunicacao(Result);
end;

function TBrvEcfBematech.ImprimirCabecalho: Integer;
begin
      Result := Bematech_FI_AbreCupom(pchar(''));
      VerificarErroComunicacao(Result);
end;

function TBrvEcfBematech.EnviarProdutoECF(CdBarras : String;
                         DsProdut : String;  TxProdut : String;
                         QtProdut : Real;    VrUnitar : Real): Integer;
var VrAuxili : String;
    QtAuxili : String;
begin
      VrAuxili := FormatFloat('0.00', VrUnitar);
      VrAuxili := StringReplace(VrAuxili, ',', '', [rfReplaceAll]);

      QtAuxili :=  FormatFloat('0.000', QtProdut);
      QtAuxili := StringReplace(QtAuxili, ',', '', [rfReplaceAll]);

      Result := Bematech_FI_VendeItem(
                        pchar(gBrString.FormatarNumero(CdBarras, 13, True)),
                        pchar(gBrString.FormatarStringSemAcento(DsProdut, 29)),
                        pchar(gBrString.FormatarStringSemAcento(TxProdut,  2)),
                        pchar('F'),       // Quantidade Inteira
                        pchar(gBrString.FormatarNumero(QtAuxili,  7, True)),
                        2,                // casas decimais do valor unitário
                        pchar(gBrString.FormatarNumero(VrAuxili,  8, True)),
                        pchar('%'),       // Tipo de Desconto
                        pchar('0000') ); // % de desconto

      VerificarErroComunicacao(Result);
end;

function TBrvEcfBematech.EstornarProduto(NrItem : Integer): Integer;
begin
      Result := Bematech_FI_CancelaItemGenerico(
                              gBrString.FormatarNumero(IntToStr(NrItem), 3, True));

      VerificarErroComunicacao(Result);
end;

function TBrvEcfBematech.FinalizarImpressaoCupom(VrDescon : Real;
           VrPago   : Real;    DsForPag : String;  DsMenRod : String;
           CdEmpres : Integer; NrPedido : Integer; NrCupom  : Integer;
           CdClient : Integer; NmClient : String;  NrCpfCli : String;
           NrRgClie : String;  DsEndCli : String;  DsBaiCli : String;
           NrCepCli : String;  NmCidCli : String;  UfCidCli : String;
           SnDadCli : Boolean; TpPagto  : String): Integer;
var VrAuxili : String;
    DsDadPed : String;
    DsDadCli : String;
begin
      VrAuxili := FormatFloat('0.00', VrDescon);
      VrAuxili := StringReplace(VrAuxili, ',', '', [rfReplaceAll]);

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-= Iniciando o fechamento do cupom e informado o valor de desconto
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      if  VrDescon > 0 then
      begin
            Result := Bematech_FI_IniciaFechamentoCupom(
                             pchar('D'), // indica que haverá desconto
                             pchar('$'), // indica que o desconto será em valor
                             pchar(gBrString.FormatarNumero(VrAuxili, 14, True)));
            VerificarErroComunicacao(Result);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-= Informando a forma de pagamento
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      VrAuxili := FormatFloat('0.00', VrPago);
      VrAuxili := StringReplace(VrAuxili, ',', '', [rfReplaceAll]);

      Result := Bematech_FI_EfetuaFormaPagamento(
                             pchar(gBrString.FormatarStringSemAcento(DsForPag, 16)),
                             pchar(gBrString.FormatarNumero(VrAuxili, 14, True)));
      VerificarErroComunicacao(Result);
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-= Finalizando o cupom e escrevendo mensagem promocional
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      DsDadPed := StringOfChar('=', 40) +
             'Emp.: '     + gBrString.FormatarNumero(IntToStr(CdEmpres), 3, True) +
             '  Pedido: ' + gBrString.FormatarNumero(IntToStr(NrPedido), 6, True) +
             '  Cupom: '  + gBrString.FormatarNumero(IntToStr(NrCupom) , 6, True);

      if  SnDadCli then
      begin
            DsDadCli := #13 +
                 'Cli.: ' + gBrString.FormatarNumero(IntToStr(CdClient), 6, True) +
                 ' '      + gBrString.FormatarStringSemAcento(NmClient, 27)       +
                 'CPF.: ' + gBrString.FormatarStringSemAcento(NrCpfCli, 14)       +
                 ' RG: '  + gBrString.FormatarStringSemAcento(NrRgClie, 15)       +
                 'End: '  + gBrString.FormatarStringSemAcento(DsEndCli, 34)       +
                 'Bai: '  + gBrString.FormatarStringSemAcento(DsBaiCli, 20)       +
                 ' Cep: ' + gBrString.FormatarStringSemAcento(NrCepCli,  8)       +
                 'Cid.: ' + gBrString.FormatarStringSemAcento(NmCidCli, 27)       +
                 ' UF: '  + gBrString.FormatarStringSemAcento(UfCidCli,  2)       +
                 #13#13   +
                 'Ass.: ' + StringOfChar('_', 34);
      end else
      begin
            DsDadCli := '';
      end;

      DsDadCli := DsDadCli +  StringOfChar('=', 40);

      DsMenRod := DsDadPed + DsDadCli + DsMenRod;

      Result := Bematech_FI_TerminaFechamentoCupom(pchar(DsMenRod));
      VerificarErroComunicacao(Result);
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
end;

function TBrvEcfBematech.AbrirGaveta: Integer;
begin
      Result := Bematech_FI_AcionaGaveta;
      VerificarErroComunicacao(Result);
end;

end.

