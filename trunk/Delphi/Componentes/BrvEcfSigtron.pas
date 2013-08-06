unit BrvEcfSigtron;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  BrvString;

type
  TBrvEcfSigtron = class(TComponent)
  private
    { Private declarations }
    gBrString     : TBrvString;
    procedure VerificarErroComunicacao(var VrReturn : Integer);
    function  MostrarErroComunicacao: Integer;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
    function    AbrePortaSerial(NrPorta : Byte): Integer;
    function    FechaPortaSerial: Integer;
    function    ReducaoZ: Integer;
    function    LeituraX: Integer;
    function    MemoriaFiscalPorData(DtInicio : TDate; DtFinal : TDate):Integer;
    function    MemoriaFiscalPorReducao(NrInicio : Integer;
                                        NrFinal  : Integer): Integer;
    function    AjustarHorarioVerao: Integer;
    function    CancelarUltimoCupom: Integer;
    function    CancelarCupomEmAndamento: Integer;
    function    ImprimirCabecalho: Integer;
    function    EnviarProdutoECF(CdProdut : Integer; DsProdut : String;
                                 TxProdut : String;  QtProdut : Real;
                                 VrUnitar : Real;    CdUnidad : String):Integer;
    function    EstornarProduto(NrItem : Integer): Integer;
    function    FinalizarImpressaoCupom(VrDescon : Real;    VrPago   : Real;
                    DsForPag : String;  DsMenRod : String;  CdEmpres : Integer;
                    NrPedido : Integer; NrCupom  : Integer; CdClient : Integer;
                    NmClient : String;  NrCpfCli : String;  NrRgClie : String;
                    DsEndCli : String;  DsBaiCli : String;  NrCepCli : String;
                    NmCidCli : String;  UfCidCli : String;  SnDadCli : Boolean;
                    TpPagto  : String): Integer;
    function    AbrirGaveta: Integer;
  published
    { Published declarations }
  end;

function DAR_AbreSerial(DsConfig: String): Integer;
                                               StdCall; External 'fs345_32.dll';
function DAR_FechaSerial(Wait: Char): Integer; Stdcall; External 'fs345_32.dll';
function DAR_Erro: Integer;                    StdCall; External 'fs345_32.dll';
function DAR_Resposta(Msg: PChar; MaxLen: Integer): Integer;
                                               Stdcall; External 'fs345_32.dll';
function DAR_LeituraX(Wait: Char): Integer;    Stdcall; External 'fs345_32.dll';
function DAR_ReducaoZ(DataHora: String; Wait: char): Integer;
                                               Stdcall; External 'fs345_32.dll';
function DAR_CancelaDoc(Wait: Char): Integer;  Stdcall; External 'fs345_32.dll';
function DAR_AbreCupomFiscal(Wait: Char): Integer;
                                               Stdcall; External 'fs345_32.dll';
function DAR_CancelaItem(NumItem: String; Wait: Char): Integer;
                                               Stdcall; External 'fs345_32.dll';
function DAR_Desc1Lin6Dig(St   : String; Cod  : String; D_a  : Char;
                          Porc : String; Preco: String; Quant: String;
                          Desc : String; Wait: Char): Integer;
                                               Stdcall; External 'fs345_32.dll';
function DAR_DescFormPag(
                    Tipo: Char; Val: String; Text: String; Wait: Char): Integer;
                                               Stdcall; External 'fs345_32.dll';
function DAR_FechaCupom(Text: String; Wait: Char): Integer;
                                               Stdcall; External 'fs345_32.dll';
function DAR_Totaliza(D_a: Char; Val: String; Wait: char): Integer;
                                               Stdcall; External 'fs345_32.dll';
function DAR_LeMF(opt: Char; Inic: String; Fim: String; Wait: Char): Integer;
                                               Stdcall; External 'fs345_32.dll';
function DAR_AbreGaveta:Integer;               Stdcall; External 'fs345_32.dll';

function DAR_sHVerao(Entra: String): Integer;  StdCall; External 'fs345_32.dll';

function DAR_Envia(buf: String; Size: Integer; Wait: Char): Integer;
                                               Stdcall; External 'fs345_32.dll';

procedure Register;

implementation

procedure Register;
begin
      RegisterComponents('Bravo ECF', [TBrvEcfSigtron]);
end;

constructor TBrvEcfSigtron.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
      gBrString := TBrvString.Create(Self);
end;

destructor TBrvEcfSigtron.Destroy;
begin
      gBrString.Destroy;
      inherited  destroy;
end;

procedure TBrvEcfSigtron.VerificarErroComunicacao(var VrReturn : Integer);
var
  DsMensag : String;
begin
      DsMensag := '';

      case VrReturn of
            0 : DsMensag :=
                 'Erro de comunica��o, a Fun��o nao conseguir enviar o comando';
           -2 : DsMensag := 'Par�metro inv�lido passado na fun��o.!';
           -3 : DsMensag := 'Al�quota n�o programada no ECF. !';
           -4 : DsMensag := 'A Chave ou Valor no Registry n�o Foi Encontado.!';
           -5 : DsMensag := 'Erro ao Abrir a Porta de Comunica��o';
           -6 : DsMensag :=
                    'Impressora desligada ou cabo de comunica��o desconectado.';
           -7 : DsMensag :='Banco n�o encontrado ou n�o cadastrado no Registry';
           -8 : DsMensag :=
                'Erro ao criar ou gravar no arquivo STATUS.TXT ou RETORNO.TXT.';
      end;

      if VrReturn = -1 then
      begin
            VrReturn := MostrarErroComunicacao();
      end;

      if DsMensag <> '' then
      begin
            ShowMessage(DsMensag);
      end;
end;

function TBrvEcfSigtron.MostrarErroComunicacao: Integer;
var
  NrRetMsg : String;
begin
    NrRetMsg := '';

    Result   := DAR_Erro();

    if Result > 1000 then
    begin
          Result := Result - 536870912;
    end;

    case Result of
         1 : NrRetMsg := 'String de configura��o da porta inv�lida.';
         2 : NrRetMsg := 'Tentativa de envio sem abertura da porta serial.';
         3 : NrRetMsg := 'Fila de entrada cheia: as respostas do ECF n�o fora' +
                         'm lidas e os buffers est�o cheios.';
         4 : NrRetMsg := 'N�o houve resposta do ECF.';
         16: NrRetMsg := 'Problema no par�metro DA (Desconto/Acr�scimo). Deve' +
                         ' ser "0" ou "1".';
         17: NrRetMsg := 'Situa��o tribut�ria inv�lida. Deve ser da forma "TA' +
                         '" ou "ta" (al�quota A a P), "F", "I" ou "N".';
         18: NrRetMsg := 'D�gito inv�lido numa string (provavelmente um carac' +
                         'ter alfa onde n�o � aceito.';
         19: NrRetMsg := 'Caracter alfanum�rico inv�lido, provavelmente tem c' +
                         'aracter de controle onde n�o � aceito.';
         20: NrRetMsg := 'Erro de formato em campo percentual, provavelmente ' +
                         'caracter n�o num�rico na string.';
         21: NrRetMsg := 'Erro de formato no campo de pre�o. Este campo de co' +
                         'nter apenas d�gitos. A v�rgula � implicita.';
         22: NrRetMsg := 'Erro no campo de quantidade, provavelmente caracter' +
                         ' alfa na string, diferente de v�rgula.';
         23: NrRetMsg := 'Erro no campo descri��o (provavelmente um caracter ' +
                         'de controle no campo).';
         24: NrRetMsg := 'Erro no campo unidade (provavelmente um caracter de' +
                         ' controlo no campo).';
         25: NrRetMsg := 'Erro no campo c�digo (provavelmente um caracter de ' +
                         'controle no campo).';
         26: NrRetMsg := 'Erro no campo c�digo (provavelmente um caracter de ' +
                         'controlo no campo).';
         27: NrRetMsg := 'Tipo de desconto inv�lido: deve ser "0" (48 ou $30)' +
                         ' a "5" (53 ou $35).';
         28: NrRetMsg := 'Erro de tipo: Em DAR_PersonaMens tipo deve ser "O" ' +
                         'ou "P". Em DAR_CriaCNF tipo deve ser "V", "+" ou "-' +
                         '". Em DAR_CriaCNF e DAR_DescFormPag, tipo deve ser ' +
                         '"A" ou "P".';
         29: NrRetMsg := 'Erro em campo de texto (provavelmente um caracter d' +
                         'e controle no campo).';
         30: NrRetMsg := 'Erro de al�quota (deve ser "A" a "P").';
         31: NrRetMsg := 'Erro de COO (provavelmente contem caracter alfa na ' +
                         'string).';
         32: NrRetMsg := 'Erro de op��o de leitura de MF (deve ser de "0" a "' +
                         '7").';
         33: NrRetMsg := 'Data inv�lida.';
         34: NrRetMsg := 'Erro de formato (n�o usado).';
         58: NrRetMsg := 'Totalizador inv�lido.';
         59: NrRetMsg := 'Meio de pagamento inv�lido.';
         60: NrRetMsg := 'N�mero de decimais inv�lido.';
    end;

    if NrRetMsg <> '' then
    begin
          Result := 0;
          ShowMessage(NrRetMsg);
    end;
end;

function TBrvEcfSigtron.AbrePortaSerial(NrPorta : Byte): Integer;
begin
      Result := DAR_AbreSerial('COM' + IntToStr(NrPorta) + ':9600,n,8,1');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSigtron.FechaPortaSerial: Integer;
begin
      Result := DAR_FechaSerial('1');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSigtron.ReducaoZ: Integer;
begin
      Result := DAR_ReducaoZ(FormatDateTime('ddmmyyhhmmss', Now), '1');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSigtron.LeituraX: Integer;
begin
      Result := DAR_LeituraX('1');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSigtron.MemoriaFiscalPorData(DtInicio : TDate;
                                               DtFinal  : TDate): Integer;
var DtIniAux : String;
    DtFinAux : String;
begin
      DtIniAux := StringReplace(FormatDateTime('dd/mm/yy', DtInicio), '/', '',
                                                                [rfReplaceAll]);
      DtFinAux := StringReplace(FormatDateTime('dd/mm/yy', DtFinal), '/', '',
                                                                [rfReplaceAll]);

      Result := DAR_LeMF(Chr(54), DtIniAux, DtFinAux, '1');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSigtron.MemoriaFiscalPorReducao(NrInicio : Integer;
                                                  NrFinal : Integer): Integer;
begin
      Result := DAR_LeMF(Chr(55), IntToStr(NrInicio), IntToStr(NrFinal), '1');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSigtron.AjustarHorarioVerao: Integer;
var
  DsTipo : String;
begin
      if InputQuery('Tipo do opera��o para hor�rio de ver�o',
                                     'Op��es: 0 Entrar  -  1 Sair', DsTipo) then
      begin
            if (Trim(DsTipo) <> '0') and (Trim(DsTipo) <> '1') then
            begin
                  Result := DAR_sHVerao(DsTipo);
                  VerificarErroComunicacao(Result);
            end else
            begin
                  ShowMessage('Par�metro inv�lido.');
            end;
      end else
      begin
            ShowMessage('Opera��o cancelada.');
      end;
end;

function TBrvEcfSigtron.CancelarUltimoCupom: Integer;
begin
      Result := DAR_CancelaDoc('1');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSigtron.CancelarCupomEmAndamento: Integer;
var
  DsEnvia : String;
begin
      DsEnvia := Chr(27) + Chr(201) + '0000               ' + Chr(255);

      Result  := DAR_Envia(DsEnvia, Length(DsEnvia), '1');
      VerificarErroComunicacao(Result);

      if Result = 1 then
      begin
            Result := DAR_CancelaDoc('1');
            VerificarErroComunicacao(Result);
      end;
end;

function TBrvEcfSigtron.ImprimirCabecalho: Integer;
begin
      Result := DAR_AbreCupomFiscal('1');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSigtron.EnviarProdutoECF(CdProdut : Integer;
                        DsProdut : String; TxProdut : String;
                        QtProdut : Real;   VrUnitar : Real;
                        CdUnidad : String): Integer;
begin
      Result :=
             DAR_Desc1Lin6Dig(TxProdut,
                              FormatFloat('000000',CdProdut),
                              '0',
                              '0000',
                              StringReplace(FormatFloat('0000000.00', VrUnitar),
                                                       ',', '', [rfReplaceAll]),
                              FormatFloat('00.00', QtProdut),
                              gBrString.FormatarStringSemAcento(DsProdut, 14),
                              '1');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSigtron.EstornarProduto(NrItem : Integer): Integer;
begin
      Result := DAR_CancelaItem(FormatFloat('000', NrItem), '1');
      VerificarErroComunicacao(Result);
end;

function  TBrvEcfSigtron.FinalizarImpressaoCupom(VrDescon : Real;
           VrPago   : Real;    DsForPag : String;  DsMenRod : String;
           CdEmpres : Integer; NrPedido : Integer; NrCupom  : Integer;
           CdClient : Integer; NmClient : String;  NrCpfCli : String;
           NrRgClie : String;  DsEndCli : String;  DsBaiCli : String;
           NrCepCli : String;  NmCidCli : String;  UfCidCli : String;
           SnDadCli : Boolean; TpPagto  : String): Integer;
var
  DsLinha  : TStrings;
  DsDescon : String;
  DsEnvia  : String;
begin
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-= Finalizando o cupom e escrevendo mensagem promocional
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      try
          DsLinha := TStringList.Create;

          DsLinha.Add(
             'Emp.: '     + gBrString.FormatarNumero(IntToStr(CdEmpres), 3, True) +
             '  Pedido: ' + gBrString.FormatarNumero(IntToStr(NrPedido), 6, True) +
             '  Cupom: '  + gBrString.FormatarNumero(IntToStr(NrCupom) , 6, True));

          DsDescon := StringReplace(FormatFloat('00.00', VrDescon),
                                                       ',', '', [rfReplaceAll]);

          DsEnvia  := Chr(27) + Chr(201) + DsDescon + '              ';

          if  SnDadCli then
          begin
                DsLinha.Add(
                   'Cli.: ' + gBrString.FormatarNumero(IntToStr(CdClient), 6,
                             True) + ' ' + gBrString.FormatarStringSemAcento(NmClient, 27));

//                DsLinha.Add(
//                   'CPF.: ' + gBrString.FormatarString(NrCpfCli, 14) +
//                   ' RG: '  + gBrString.FormatarString(NrRgClie, 15));

                DsLinha.Add(
                   'End: '  + gBrString.FormatarStringSemAcento(DsEndCli, 35));

//                DsLinha.Add(
//                   'Bai: '  + gBrString.FormatarString(DsBaiCli, 21) +
//                   ' Cep: ' + gBrString.FormatarString(NrCepCli,  8));

//                DsLinha.Add(
//                   'Cid.: ' + gBrString.FormatarString(NmCidCli, 27) +
//                   ' UF: '  + gBrString.FormatarString(UfCidCli,  2));

                DsLinha.Add(' ');
                DsLinha.Add(' ');
                DsLinha.Add('Ass.:__________________________________');
                DsLinha.Add(' ');
          end;

          DsEnvia := DsEnvia + DsLinha.Text + Chr(255);

          Result := DAR_Envia(DsEnvia, Length(DsEnvia), '1');
          VerificarErroComunicacao(Result);
      finally
          DsLinha := Nil;
      end;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
end;

function TBrvEcfSigtron.AbrirGaveta: Integer;
begin
      Result := DAR_AbreGaveta();
      VerificarErroComunicacao(Result);
end;

end.
