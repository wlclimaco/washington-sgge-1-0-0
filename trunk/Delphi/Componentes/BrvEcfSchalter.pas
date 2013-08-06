unit BrvEcfSchalter;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  BrvString;

type
  TBrvEcfSchalter = class(TComponent)
  private
    { Private declarations }
    gBrString: TBrvString;
    procedure VerificarErroComunicacao(VrReturn : Integer);
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor Destroy; override;
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
function ecfAbreGaveta : integer; stdcall; external 'dll32phi.dll';
function ecfImpCab(byEst: integer): integer;   stdcall; external 'dll32phi.dll';
function ecfReducaoZ(operador: LPSTR):integer; stdcall; external 'dll32phi.dll';
function ecfLeituraX(operador: LPSTR):integer; stdcall; external 'dll32phi.dll';
function ecfCancVenda(operador: LPSTR):integer;stdcall; external 'dll32phi.dll';
function ecfCancItemDef(szItem: string; szDescr: string): integer;
                                               stdcall; external 'dll32phi.dll';
function ecfCancDoc(operador: LPSTR):integer;  stdcall; external 'dll32phi.dll';
function ecfDescItem(byTipo: integer; szDescr: string; szValor: string):integer;
                                               stdcall; external 'dll32phi.dll';
function ecfFimTrans(operador: LPSTR): integer;stdcall; external 'dll32phi.dll';
function ecfPagamento(byTipo : integer; szPosTable: string; szValor: string;
                      byLmens: integer): integer;
                                               stdcall; external 'dll32phi.dll';
function ecfVendaItem3d(szCodigo    : string;  szDescricao: string;
                        szQuantidade: string;  szValor    : string;
                        byTaxa      : integer; unidade    : string;
                        digitos     : string): Integer;
                                               stdcall; external 'dll32phi.dll';
function ecfLeitMemFisc(byTipo: integer; szDi: string; szDf: string;
                        wRi  : integer; wRf: integer; archive: string): Integer;
                                               stdcall; external 'dll32phi.dll';
function ecfImpLinha(szLinha: string): Integer;stdcall; external 'dll32phi.dll';

procedure Register;
begin
      RegisterComponents('Bravo ECF', [TBrvEcfSchalter]);
end;

constructor TBrvEcfSchalter.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
      gBrString := TBrvString.Create(Self);
end;

destructor TBrvEcfSchalter.Destroy;
begin
      gBrString.Destroy;
      inherited  destroy;
end;

procedure TBrvEcfSchalter.VerificarErroComunicacao(VrReturn : Integer);
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

function TBrvEcfSchalter.ReducaoZ: Integer;
begin
      Result := ecfReducaoZ('        ');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSchalter.LeituraX: Integer;
begin
      Result := ecfLeituraX('        ');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSchalter.MemoriaFiscalPorData(DtInicio : TDate;
                                                      DtFinal : TDate): Integer;
begin
      Result := ecfLeitMemFisc(1, FormatDateTime('ddmmyy', DtInicio),
                                  FormatDateTime('ddmmyy', DtFinal), 0, 0, '');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSchalter.MemoriaFiscalPorReducao(NrInicio : Integer;
                                                   NrFinal : Integer): Integer;
var DsInicio : String;
    DsFinal  : String;
begin
      Result := ecfLeitMemFisc(2, '', '', NrInicio, NrFinal, '');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSchalter.AjustarHorarioVerao: Integer;
begin
//      Result := Bematech_FI_ProgramaHorarioVerao;
//      VerificarErroComunicacao(Result);
end;

function TBrvEcfSchalter.CancelarUltimoCupom: Integer;
begin
      Result := ecfCancDoc('        ');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSchalter.CancelarCupomEmAndamento: Integer;
begin
      Result := ecfCancVenda('        ');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSchalter.ImprimirCabecalho: Integer;
begin
      Result := ecfImpCab(4);
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSchalter.EnviarProdutoECF(CdBarras : String;
                         DsProdut : String;  TxProdut : String;
                         QtProdut : Real;    VrUnitar : Real): Integer;
var VrAuxili : String;
    QtAuxili : String;
begin
      VrAuxili := FormatFloat('0000000.00', VrUnitar);
      VrAuxili := StringReplace(VrAuxili, ',', '', [rfReplaceAll]);

      QtAuxili := FormatFloat('0000.00', QtProdut);

      Result := ecfVendaItem3d(CdBarras, DsProdut,
                               QtAuxili, VrAuxili,
                               StrToInt(TxProdut), 'UN',
                               '2');

      VerificarErroComunicacao(Result);
end;

function TBrvEcfSchalter.EstornarProduto(NrItem : Integer): Integer;
begin
      Result := ecfCancItemDef(FormatFloat('0000', NrItem), 'ITEM CANCELADO');
      VerificarErroComunicacao(Result);
end;

function TBrvEcfSchalter.FinalizarImpressaoCupom(VrDescon : Real;
           VrPago   : Real;    DsForPag : String;  DsMenRod : String;
           CdEmpres : Integer; NrPedido : Integer; NrCupom  : Integer;
           CdClient : Integer; NmClient : String;  NrCpfCli : String;
           NrRgClie : String;  DsEndCli : String;  DsBaiCli : String;
           NrCepCli : String;  NmCidCli : String;  UfCidCli : String;
           SnDadCli : Boolean; TpPagto  : String): Integer;
var VrAuxili : String;
    DsDadPed : TStrings;
    DsDadCli : TStrings;
begin
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-= Iniciando o fechamento do cupom e informado o valor de desconto
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      if  VrDescon > 0 then
      begin
            VrAuxili := FormatFloat('0000000.00', VrDescon);
            VrAuxili := StringReplace(VrAuxili, ',', '', [rfReplaceAll]);

            Result := ecfDescItem(0, 'DESCONTO', VrAuxili);
            VerificarErroComunicacao(Result);
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-= Informando a forma de pagamento
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      VrAuxili := FormatFloat('00000000.00', VrPago);
      VrAuxili := StringReplace(VrAuxili, ',', '', [rfReplaceAll]);

      Result := ecfPagamento(0, '00', VrAuxili, 0);
      VerificarErroComunicacao(Result);
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-= Finalizando o cupom e escrevendo mensagem promocional
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      DsDadPed := TStringList.Create;
      DsDadCli := TStringList.Create;

      DsDadPed.Add(StringOfChar('=', 48));
      DsDadPed.Add(
             'Emp.: '     + gBrString.FormatarNumero(IntToStr(CdEmpres), 3, True) +
             '  Pedido: ' + gBrString.FormatarNumero(IntToStr(NrPedido), 6, True) +
             '  Cupom: '  + gBrString.FormatarNumero(IntToStr(NrCupom) , 6, True));

      if  SnDadCli then
      begin
            DsDadCli.Add(
                 'Cli.: ' + gBrString.FormatarNumero(IntToStr(CdClient), 6, True) +
                 ' '      + gBrString.FormatarStringSemAcento(NmClient, 27));
            DsDadCli.Add(
                 'CPF.: ' + gBrString.FormatarStringSemAcento(NrCpfCli, 14)                +
                 ' RG: '  + gBrString.FormatarStringSemAcento(NrRgClie, 15));
            DsDadCli.Add(
                 'End: '  + gBrString.FormatarStringSemAcento(DsEndCli, 34)                +
                 'Bai: '  + gBrString.FormatarStringSemAcento(DsBaiCli, 20));
            DsDadCli.Add(
                 ' Cep: ' + gBrString.FormatarStringSemAcento(NrCepCli,  8)                +
                 'Cid.: ' + gBrString.FormatarStringSemAcento(NmCidCli, 27)                +
                 ' UF: '  + gBrString.FormatarStringSemAcento(UfCidCli,  2));
            DsDadCli.Add(' ');
            DsDadCli.Add(
                 'Ass.: ' + StringOfChar('_', 34));
      end else
      begin
            DsDadCli.Clear;
      end;

      DsDadCli.Add(StringOfChar('=', 40));

//      DsMenRod := DsDadPed + DsDadCli + DsMenRod;

      Result := ecfFimTrans(pAnsiChar(DsMenRod));
      VerificarErroComunicacao(Result);

      Result := ecfFimTrans('        ');
      VerificarErroComunicacao(Result);
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
end;

function TBrvEcfSchalter.AbrirGaveta: Integer;
begin
      Result := ecfAbreGaveta;
      VerificarErroComunicacao(Result);
end;

end.

