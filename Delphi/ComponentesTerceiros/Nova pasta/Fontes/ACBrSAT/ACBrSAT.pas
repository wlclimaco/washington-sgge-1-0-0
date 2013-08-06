{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para interação com equipa- }
{ mentos de Automação Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2013 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo: André Ferreira de Moraes                        }
{                                                                              }
{  Você pode obter a última versão desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
{                                                                              }
{  Esta biblioteca é software livre; você pode redistribuí-la e/ou modificá-la }
{ sob os termos da Licença Pública Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a versão 2.1 da Licença, ou (a seu critério) }
{ qualquer versão posterior.                                                   }
{                                                                              }
{  Esta biblioteca é distribuída na expectativa de que seja útil, porém, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia implícita de COMERCIABILIDADE OU      }
{ ADEQUAÇÃO A UMA FINALIDADE ESPECÍFICA. Consulte a Licença Pública Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICENÇA.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Você deve ter recebido uma cópia da Licença Pública Geral Menor do GNU junto}
{ com esta biblioteca; se não, escreva para a Free Software Foundation, Inc.,  }
{ no endereço 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Você também pode obter uma copia da licença em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Simões de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Praça Anita Costa, 34 - Tatuí - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{$I ACBr.inc}

unit ACBrSAT;

interface

uses
  Classes, SysUtils, pcnCFe, pcnCFeR, pcnCFeCanc, pcnCFeCancR, ACBrSATClass, ACBrSATExtratoClass, synacode
  {$IFNDEF NOGUI}
    {$IFDEF FPC} ,LResources {$ENDIF}
  {$ENDIF};

type
   { TACBrSAT }

   TACBrSAT = class( TComponent )
   private
     fsCFe : TCFe ;
     fsCFeCanc : TCFeCanc ;
     fsnumeroSessao : Integer ;
     fsOnGetcodigoDeAtivacao : TACBrSATGetChave ;
     fsOnGetsignAC : TACBrSATGetChave ;
     fsOnLog : TACBrSATDoLog ;
     fsPathDLL : string ;
     fsResposta : TACBrSATResposta ;
     fsRespostaComando : String ;
     fsSATClass : TACBrSATClass ;
     fsExtrato : TACBrSATExtratoClass;

     fsArqLOG: string;
     fsComandoLog: String;
     fsInicializado : Boolean ;
     fsModelo : TACBrSATModelo ;
     fsConfig : TACBrSATConfig ;

     function GetAbout : String;
     function GetcodigoDeAtivacao : String ;
     function GetModeloStrClass : String ;
     function GetNomeDLL : String ;
     function GetsignAC : String ;
     procedure SetAbout(const Value: String);{%h-}
     procedure SetInicializado(AValue : Boolean) ;
     procedure SetModelo(AValue : TACBrSATModelo) ;
     procedure SetPathDLL(AValue : string) ;

     procedure VerificaInicializado ;
     procedure IniciaComando ;
     procedure FinalizaComando ;

     procedure GravaLog(AString : AnsiString ) ;
     procedure SetExtrato(const Value: TACBrSATExtratoClass);
   protected
     procedure Notification(AComponent: TComponent; Operation: TOperation); override;
   public
     property SAT : TACBrSATClass read fsSATClass ;

     constructor Create( AOwner : TComponent ) ; override;
     destructor Destroy ; override;

     Procedure Inicializar;
     Procedure DesInicializar;
     property Inicializado : Boolean read fsInicializado write SetInicializado ;

     Property ModeloStr : String  read GetModeloStrClass;
     Property NomeDLL   : String  read GetNomeDLL;

     property numeroSessao : Integer read fsnumeroSessao write fsnumeroSessao;
     function GerarnumeroSessao : Integer ;

     property codigoDeAtivacao : String read GetcodigoDeAtivacao ;
     property signAC           : String read GetsignAC ;

     property RespostaComando: String read fsRespostaComando ;

     property CFe : TCFe read fsCFe ;
     property CFeCanc : TCFeCanc read fsCFeCanc ;
     property Resposta : TACBrSATResposta read fsResposta;

     procedure InicializaCFe( ACFe : TCFe = nil );

     procedure DoLog(AString : AnsiString ) ;

     function AssociarAssinatura( CNPJvalue, assinaturaCNPJs : String ): String ;
     function AtivarSAT(subComando : Integer ; CNPJ : String ; cUF : Integer
       ) : String ;
     function AtualizarSoftwareSAT : String ;
     function BloquearSAT : String ;
     function CancelarUltimaVenda :String ; overload;
     function CancelarUltimaVenda( chave, dadosCancelamento : String ) :
       String ; overload;
     function ComunicarCertificadoICPBRASIL( certificado : AnsiString ) :
       String ;
     function ConfigurarInterfaceDeRede( dadosConfiguracao : AnsiString ) :
       String ;
     function ConsultarNumeroSessao( cNumeroDeSessao : Integer) : String ;
     function ConsultarSAT : String ;
     function ConsultarStatusOperacional : String ;
     function DesbloquearSAT : String ;
     function DesligarSAT : String ;
     function EnviarDadosVenda( dadosVenda : AnsiString ) : String ;
     function ExtrairLogs : String ;
     function TesteFimAFim( dadosVenda : AnsiString) : String ;
     function TrocarCodigoDeAtivacao( opcao : Integer; novoCodigo,
        confNovoCodigo : String ) : String ;

    procedure ImprimirExtrato;
    procedure ImprimirExtratoResumido;
    procedure ImprimirExtratoCancelamento;

   published
     property Modelo : TACBrSATModelo read fsModelo write SetModelo
                 default satNenhum ;

     property Extrato: TACBrSATExtratoClass read fsExtrato write SetExtrato ;

     property PathDLL: string read fsPathDLL write SetPathDLL;

     property About : String read GetAbout write SetAbout stored False ;
     property ArqLOG : String read fsArqLOG write fsArqLOG ;
     property OnLog : TACBrSATDoLog read fsOnLog write fsOnLog;

     property Config : TACBrSATConfig read fsConfig write fsConfig;

     property OnGetcodigoDeAtivacao : TACBrSATGetChave read fsOnGetcodigoDeAtivacao
        write fsOnGetcodigoDeAtivacao;
     property OnGetsignAC : TACBrSATGetChave read fsOnGetsignAC write fsOnGetsignAC;

   end;

procedure Register;

implementation

Uses ACBrUtil, ACBrSATEmuladorSP, pcnCFeW, pcnCFeCancW, ACBrSATExtratoESCPOS;

{$IFNDEF FPC}
   {$R ACBrSAT.dcr}
{$ENDIF}

procedure Register;
begin
  RegisterComponents('ACBr', [TACBrSAT,TACBrSATExtratoESCPOS]);
end;

{ TACBrSAT }

constructor TACBrSAT.Create(AOwner : TComponent) ;
begin
  inherited Create(AOwner) ;

  fsnumeroSessao    := 0;
  fsPathDLL         := '';
  fsArqLOG          := '' ;
  fsComandoLog      := '';
  fsRespostaComando := '';

  fsOnGetcodigoDeAtivacao := nil;
  fsOnGetsignAC           := nil;
  fsOnLog                 := nil;

  fsConfig  := TACBrSATConfig.Create;
  fsCFe     := TCFe.Create;
  fsCFeCanc := TCFeCanc.Create;
  fsResposta:= TACBrSATResposta.Create;
end ;

destructor TACBrSAT.Destroy ;
begin
  fsConfig.Free;
  fsCFe.Free;
  fsCFeCanc.Free;
  fsResposta.Free;

  inherited Destroy ;
end ;

procedure TACBrSAT.Inicializar ;
begin
  if fsInicializado then exit ;

  if fsModelo = satNenhum then
     raise EACBrSATErro.Create( cACBrSATModeloNaoDefinido );

  fsSATClass.Inicializar ;
  Randomize;

  DoLog( 'ACBrSAT.Inicializado');
  fsInicializado := true ;
end ;

procedure TACBrSAT.DesInicializar ;
begin
  if not fsInicializado then exit ;

  fsSATClass.DesInicializar ;

  DoLog( 'ACBrSAT.DesInicializado');
  fsInicializado := false;
end ;

procedure TACBrSAT.VerificaInicializado ;
begin
  if not Inicializado then
     raise EACBrSATErro.Create( cACBrSATNaoInicializado ) ;
end ;

procedure TACBrSAT.IniciaComando ;
var
  AStr : String ;
begin
  VerificaInicializado;
  GerarnumeroSessao;

  fsRespostaComando := '';
  AStr := '-- '+FormatDateTime('hh:nn:ss:zzz',now) +
          ' - numeroSessao: '+IntToStr(numeroSessao) ;
  if fsComandoLog <> '' then
     AStr := AStr + ' - Comando: '+fsComandoLog;

  DoLog( AStr );
end ;

procedure TACBrSAT.FinalizaComando ;
var
  AStr : String ;
begin
  fsComandoLog := '';
  AStr := '   '+FormatDateTime('hh:nn:ss:zzz',now) +
          ' - numeroSessao: '+IntToStr(numeroSessao) ;
  if fsRespostaComando <> '' then
     AStr := AStr + ' - Resposta:'+fsRespostaComando;

  Resposta.RetornoStr := fsRespostaComando;

  DoLog( AStr );
end ;

procedure TACBrSAT.DoLog(AString : AnsiString) ;
begin
  GravaLog(AString);

  if Assigned( fsOnLog ) then
    fsOnLog( AString );
end ;

procedure TACBrSAT.GravaLog(AString : AnsiString) ;
begin
  if (ArqLOG = '') then
    exit;

  WriteToTXT( ArqLOG, AString );
end ;

function TACBrSAT.GerarnumeroSessao : Integer ;
begin
  fsnumeroSessao := Random(999999);
  Result := fsnumeroSessao;
end ;

procedure TACBrSAT.InicializaCFe(ACFe : TCFe) ;
Var
  wCFe : TCFe ;
begin
  if Assigned( ACFe ) then
    wCFe := ACFe
  else
    wCFe := fsCFe;

  with wCFe do
  begin
    Clear;
    infCFe.versaoDadosEnt := fsConfig.infCFe_versaoDadosEnt;
    ide.CNPJ              := fsConfig.ide_CNPJ;
    ide.tpAmb             := fsConfig.ide_tpAmb;
    ide.numeroCaixa       := fsConfig.ide_numeroCaixa;
    ide.signAC            := signAC;
    Emit.CNPJCPF          := fsConfig.emit_CNPJ;
    Emit.IE               := fsConfig.emit_IE;
    Emit.IM               := fsConfig.emit_IM;
    Emit.cRegTribISSQN    := fsConfig.emit_cRegTribISSQN;
    Emit.indRatISSQN      := fsConfig.emit_indRatISSQN;
  end ;
end ;

function TACBrSAT.AssociarAssinatura(CNPJvalue, assinaturaCNPJs : String
  ) : String ;
begin
  fsComandoLog := 'AssociarAssinatura( '+CNPJvalue+', '+assinaturaCNPJs+' )';
  IniciaComando;
  Result := fsSATClass.AssociarAssinatura( CNPJvalue, assinaturaCNPJs );
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.AtivarSAT(subComando : Integer ; CNPJ : String ;
  cUF : Integer) : String ;
begin
  fsComandoLog := 'AtivarSAT( '+IntToStr(subComando)+', '+CNPJ+', '+IntToStr(cUF)+' )';
  IniciaComando;
  Result := fsSATClass.AtivarSAT( subComando, CNPJ, cUF );
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.AtualizarSoftwareSAT : String ;
begin
  fsComandoLog := 'AtualizarSoftwareSAT';
  IniciaComando;
  Result := fsSATClass.AtualizarSoftwareSAT ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.BloquearSAT : String ;
begin
  fsComandoLog := 'BloquearSAT';
  IniciaComando;
  Result := fsSATClass.BloquearSAT ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.CancelarUltimaVenda: String ;
var
  dadosCancelamento : string;
begin
  CFeCanc.Clear;
  CFeCanc.infCFe.chCanc   := CFe.infCFe.ID;
  CFeCanc.infCFe.dEmi     := CFe.ide.dEmi;
  CFeCanc.infCFe.hEmi     := CFe.ide.hEmi;
  CFeCanc.ide.CNPJ        := CFe.ide.CNPJ;
  CFeCanc.ide.signAC      := CFe.ide.signAC;
  CFeCanc.ide.numeroCaixa := CFe.ide.numeroCaixa;
  CFeCanc.Dest.CNPJCPF    := CFe.Dest.CNPJCPF;

  dadosCancelamento := CFeCanc.AsXMLString;

  Result := CancelarUltimaVenda(CFe.infCFe.ID, dadosCancelamento);
end ;


function TACBrSAT.CancelarUltimaVenda(chave, dadosCancelamento : String
  ) : String ;
begin
  fsComandoLog := 'CancelarUltimaVenda( '+chave+', '+dadosCancelamento+' )';
  IniciaComando;
  Result := fsSATClass.CancelarUltimaVenda(chave, dadosCancelamento) ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.ComunicarCertificadoICPBRASIL(certificado : AnsiString
  ) : String ;
begin
  fsComandoLog := 'ComunicarCertificadoICPBRASIL( '+certificado+' )';
  IniciaComando;
  Result := fsSATClass.ComunicarCertificadoICPBRASIL( certificado ) ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.ConfigurarInterfaceDeRede(dadosConfiguracao : AnsiString
  ) : String ;
begin
  fsComandoLog := 'ConfigurarInterfaceDeRede( '+dadosConfiguracao+' )';
  IniciaComando;
  Result := fsSATClass.ConfigurarInterfaceDeRede( dadosConfiguracao ) ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.ConsultarNumeroSessao(cNumeroDeSessao : Integer
  ) : String ;
begin
  fsComandoLog := 'ConsultarNumeroSessao( '+IntToStr(cNumeroDeSessao)+' )';
  IniciaComando;
  Result := fsSATClass.ConsultarNumeroSessao( cNumeroDeSessao ) ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.ConsultarSAT : String ;
begin
  fsComandoLog := 'ConsultarSAT';
  IniciaComando;
  Result := fsSATClass.ConsultarSAT ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.ConsultarStatusOperacional : String ;
begin
  fsComandoLog := 'ConsultarStatusOperacional';
  IniciaComando;
  Result := fsSATClass.ConsultarStatusOperacional ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.DesbloquearSAT : String ;
begin
  fsComandoLog := 'DesbloquearSAT';
  IniciaComando;
  Result := fsSATClass.DesbloquearSAT ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.DesligarSAT : String ;
begin
  fsComandoLog := 'DesligarSAT';
  IniciaComando;
  Result := fsSATClass.DesligarSAT ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.EnviarDadosVenda(dadosVenda : AnsiString) : String ;
var
  LocCFeR : TCFeR;
begin
  fsComandoLog := 'EnviarDadosVenda( '+dadosVenda+' )';
  IniciaComando;
  Result := fsSATClass.EnviarDadosVenda( dadosVenda ) ;
  fsRespostaComando := Result;
  FinalizaComando ;

  if fsResposta.codigoDeRetorno = 6000 then
   begin
     CFe.Clear;
     LocCFeR := TCFeR.Create(CFe);
     try
       LocCFeR.Leitor.Arquivo := DecodeBase64(fsResposta.RetornoLst[6]);
       LocCFeR.LerXml;
     finally
       LocCFeR.Free;
     end;
   end;
end ;

function TACBrSAT.ExtrairLogs : String ;
begin
  fsComandoLog := 'ExtrairLogs';
  IniciaComando;
  Result := fsSATClass.ExtrairLogs ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.TesteFimAFim(dadosVenda : AnsiString) : String ;
begin
  fsComandoLog := 'TesteFimAFim(' +dadosVenda+' )';
  IniciaComando;
  Result := fsSATClass.TesteFimAFim( dadosVenda ) ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.TrocarCodigoDeAtivacao(opcao : Integer ; novoCodigo,
  confNovoCodigo : String) : String ;
begin
  fsComandoLog := 'TrocarCodigoDeAtivacao(' +IntToStr(opcao)+', '+novoCodigo+
                  ', '+confNovoCodigo+' )';
  IniciaComando;
  Result := fsSATClass.TrocarCodigoDeAtivacao( opcao, novoCodigo, confNovoCodigo ) ;
  fsRespostaComando := Result;
  FinalizaComando ;
end ;

function TACBrSAT.GetAbout : String ;
begin
  Result := 'ACBrSAT Ver: '+CACBrSAT_Versao;
end ;

function TACBrSAT.GetcodigoDeAtivacao : String ;
var
  AcodigoDeAtivacao : String ;
begin
  AcodigoDeAtivacao := '';

  if Assigned( fsOnGetcodigoDeAtivacao ) then
     fsOnGetcodigoDeAtivacao( AcodigoDeAtivacao ) ;

  Result := AcodigoDeAtivacao;
end;

function TACBrSAT.GetModeloStrClass : String ;
begin
   Result := fsSATClass.ModeloStr;
end;

function TACBrSAT.GetNomeDLL : String ;
begin
  Result := fsSATClass.NomeDLL;
end;

function TACBrSAT.GetsignAC : String ;
var
  AsignAC : String ;
begin
  AsignAC := '';

  if Assigned( fsOnGetsignAC ) then
     fsOnGetsignAC( AsignAC ) ;

  Result := AsignAC;
end;

procedure TACBrSAT.SetAbout(const Value : String) ;
begin
  {}
end ;

procedure TACBrSAT.SetInicializado(AValue : Boolean) ;
begin
  if AValue then
    Inicializar
  else
    DesInicializar ;
end ;

procedure TACBrSAT.SetModelo(AValue : TACBrSATModelo) ;
var
  wArqLOG : String ;
begin
  if fsModelo = AValue then exit ;

  if fsInicializado then
     raise EACBrSATErro.Create( ACBrStr(cACBrSATSetModeloException));

  wArqLOG := ArqLOG ;

  FreeAndNil( fsSATClass ) ;

  { Instanciando uma nova classe de acordo com AValue }
  case AValue of
    satEmuladorSP  : fsSATClass := TACBrSATEmuladorSP.Create( Self ) ;
  else
    fsSATClass := TACBrSATClass.Create( Self ) ;
  end;

  { Passando propriedades da Classe anterior para a Nova Classe }
  ArqLOG := wArqLOG ;

  fsModelo := AValue;
end ;

procedure TACBrSAT.SetPathDLL(AValue : string) ;
begin
  if fsPathDLL = AValue then Exit ;
  fsPathDLL := PathWithDelim( Trim(AValue) ) ;
end ;

procedure TACBrSAT.SetExtrato(const Value: TACBrSATExtratoClass);
Var
  OldValue: TACBrSATExtratoClass ;
begin
  if Value <> fsExtrato then
  begin
     if Assigned(fsExtrato) then
        fsExtrato.RemoveFreeNotification(Self);

     OldValue  := fsExtrato ;   // Usa outra variavel para evitar Loop Infinito
     fsExtrato := Value;    // na remoção da associação dos componentes

     if Assigned(OldValue) then
        if Assigned(OldValue.ACBrSAT) then
           OldValue.ACBrSAT := nil ;

     if Value <> nil then
     begin
        Value.FreeNotification(self);
        Value.ACBrSAT := self ;
     end ;
  end ;
end;

procedure TACBrSAT.Notification(AComponent : TComponent ; Operation : TOperation
  ) ;
begin
  inherited Notification(AComponent, Operation) ;

  if (Operation = opRemove) and (fsExtrato <> nil) and (AComponent is TACBrSATExtratoClass) then
     fsExtrato := nil ;
end ;

procedure TACBrSAT.ImprimirExtrato;
begin
   if not Assigned(Extrato) then
      raise Exception.Create( ACBrStr('Nenhum componente "ACBrSATExtrato" associado' ) ) ;

   Extrato.ImprimirExtrato;
end;

procedure TACBrSAT.ImprimirExtratoCancelamento;
begin
   if not Assigned(Extrato) then
      raise Exception.Create( ACBrStr('Nenhum componente "ACBrSATExtrato" associado' ) ) ;

   Extrato.ImprimirExtratoCancelamento;
end;

procedure TACBrSAT.ImprimirExtratoResumido;
begin
   if not Assigned(Extrato) then
      raise Exception.Create( ACBrStr('Nenhum componente "ACBrSATExtrato" associado' ) ) ;

   Extrato.ImprimirExtratoResumido;
end;

{$IFDEF FPC}
{$IFNDEF NOGUI}
initialization
   {$I ACBrSAT.lrs}
{$ENDIF}
{$ENDIF}

end.

