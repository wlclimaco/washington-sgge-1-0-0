{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para interação com equipa- }
{ mentos de Automação Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
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

{******************************************************************************
|* Historico
|*
|* 21/11/2009: Daniel Simoes de Almeida
|*  - Primeira Versao: Criaçao e Distribuiçao da Primeira Versao
******************************************************************************}

{$I ACBr.inc}

unit ACBrSATClass ;

interface

uses
  Classes, SysUtils, pcnCFe, pcnConversao ;

const
  cACBrSAT_Versao      = '0.1.0' ;
  cLIBSAT              = 'c:\SAT\SAT.DLL';
  cversaoDadosEnt      = 0.03;

  cACBrSATClassCreateException = 'Essa Classe deve ser instanciada por TACBrSAT' ;
  cACBrSATSetModeloException   = 'Não é possível mudar o Modelo com o SAT Inicializado' ;
  cACBrSATModeloNaoDefinido    = 'Modelo de SAT não definido' ;
  cACBrSATNaoInicializado      = 'ACBrSAT não foi inicializado corretamente' ;
  cACBrSATFuncaoNaoEncontrada  = 'Erro ao carregar a função: %s na Biblioteca: %s' ;
  cACBrSATCMDInvalidoException = 'Procedure: %s '+ sLineBreak +
                                 ' não implementada para o SAT: %s'+sLineBreak + sLineBreak +
                                 'Ajude no desenvolvimento do ACBrSAT. '+ sLineBreak+
                                 'Acesse nosso Forum em: http://projetoacbr.com.br/' ;
type

  TACBrSATModelo = ( satNenhum, satEmuladorSP ) ;

  { EACBrSATErro }

  EACBrSATErro = class(Exception)
  public
    constructor Create(const msg : string);
  end ;

  { Eventos do componente }
  TACBrSATGetChave = procedure(var Chave: String) of object ;
  TACBrSATDoLog = procedure(const AString: String) of object ;

  { TACBrSATConfig }

  TACBrSATConfig = Class(TPersistent)
  private
    fsemit_CNPJ : String ;
    fsemit_cRegTrib : TpcnRegTrib ;
    fsemit_cRegTribISSQN : TpcnRegTribISSQN ;
    fsemit_IE : String ;
    fsemit_IM : String ;
    fsemit_indRatISSQN : TpcnindRatISSQN ;
    fside_CNPJ : String ;
    fside_numeroCaixa : Integer ;
    fsinfCFe_versaoDadosEnt : Real ;
    fside_tpAmb : TpcnTipoAmbiente ;
  public
    constructor Create;
    destructor Destroy; override;
    procedure Clear;
  published
    property infCFe_versaoDadosEnt : Real read fsinfCFe_versaoDadosEnt
       write fsinfCFe_versaoDadosEnt ;

    property ide_CNPJ : String  read fside_CNPJ write fside_CNPJ;
    property ide_numeroCaixa : Integer read fside_numeroCaixa
       write fside_numeroCaixa ;
    property ide_tpAmb : TpcnTipoAmbiente read fside_tpAmb write fside_tpAmb;

    property emit_CNPJ : String read fsemit_CNPJ write fsemit_CNPJ;
    property emit_IE   : String read fsemit_IE   write fsemit_IE;
    property emit_IM   : String read fsemit_IM   write fsemit_IM;
    property emit_cRegTrib: TpcnRegTrib read fsemit_cRegTrib
       write fsemit_cRegTrib ;
    property emit_cRegTribISSQN: TpcnRegTribISSQN read fsemit_cRegTribISSQN
       write fsemit_cRegTribISSQN ;
    property emit_indRatISSQN: TpcnindRatISSQN read fsemit_indRatISSQN
       write fsemit_indRatISSQN;
  end;

  { TACBrSATRespostaClass }

  TACBrSATResposta = class
  private
    fcodigoDeRetorno : Integer ;
    fnumeroSessao : Integer ;
    fRetornoLst : TStringList ;
    fRetornoStr : String ;
    procedure SetRetornoStr(AValue : String) ;
  public
    constructor Create ;
    Destructor Destroy ;
    procedure Clear ;

    property numeroSessao : Integer read fnumeroSessao ;
    property codigoDeRetorno : Integer read  fcodigoDeRetorno;
    property RetornoLst : TStringList read fRetornoLst ;
    property RetornoStr : String read fRetornoStr write SetRetornoStr ;
  end ;

  { TACBrSATClass }

   TACBrSATClass = class( TComponent )
   private
     function GetcodigoDeAtivacao : String ;
     function GetnumeroSessao : Integer ;
     function GetPathDLL : string ;

     procedure ErroAbstract( NomeProcedure : String ) ;
     function GetsignAC : String ;
   protected
     fpOwner : TComponent ;   { Componente ACBrSAT }
     fpModeloStr: String;
     fpNomeDLL  : String;

     function GetModeloStr: String; virtual ;
     function GetNomeDLL : String ; virtual ;

     property PathDLL: String read GetPathDLL ;

     property codigoDeAtivacao : String read GetcodigoDeAtivacao ;
     property signAC : String read GetsignAC ;
     property numeroSessao : Integer read GetnumeroSessao ;

     procedure LoadDLLFunctions ; virtual ;
     procedure UnLoadDLLFunctions ; virtual ;
     procedure FunctionDetectLibSAT(FuncName : String ;
       var LibPointer : Pointer) ; virtual ;
   public
     constructor Create( AOwner : TComponent ) ; override;
     destructor Destroy ; override;

     procedure Inicializar; virtual ;
     procedure DesInicializar ; virtual ;

     Property ModeloStr: String read GetModeloStr ;
     property NomeDLL  : String read GetNomeDLL ;

     function AssociarAssinatura( CNPJvalue, assinaturaCNPJs : String ):
       String ; virtual;
     function AtivarSAT( subComando : Integer; CNPJ: String; cUF : Integer )
       : String ; virtual;
     function AtualizarSoftwareSAT : String ; virtual;
     function BloquearSAT : String ; virtual;
     function CancelarUltimaVenda( chave, dadosCancelamento : String ) :
       String ; virtual;
     function ComunicarCertificadoICPBRASIL( certificado : AnsiString ) :
       String ; virtual;
     function ConfigurarInterfaceDeRede( dadosConfiguracao : AnsiString ) :
       String ; virtual;
     function ConsultarNumeroSessao( cNumeroDeSessao : Integer) : String ;
       virtual;
     function ConsultarSAT : String ; virtual;
     function ConsultarStatusOperacional : String ; virtual;
     function DesbloquearSAT : String ; virtual;
     function DesligarSAT : String ; virtual;
     function EnviarDadosVenda( dadosVenda : AnsiString ) : String ; virtual;
     function ExtrairLogs : String ; virtual;
     function TesteFimAFim( dadosVenda : AnsiString) : String ; virtual;
     function TrocarCodigoDeAtivacao( opcao : Integer; novoCodigo,
        confNovoCodigo : String ) : String ; virtual;

   end;

implementation

Uses ACBrSAT, ACBrUtil ;

{ TACBrSATRespostaClass }

procedure TACBrSATResposta.SetRetornoStr(AValue : String) ;
begin
  Clear;
  fRetornoLst.Text := StringReplace( AValue, '|', sLineBreak, [rfReplaceAll] );
  fRetornoStr      := AValue;

  if fRetornoLst.Count > 1 then
  begin
    fnumeroSessao    := StrToIntDef( fRetornoLst[0], 0);
    fcodigoDeRetorno := StrToIntDef( fRetornoLst[1], 0);
  end ;
end;

constructor TACBrSATResposta.Create ;
begin
  inherited Create;
  fRetornoLst := TStringList.Create;
  Clear;
end ;

destructor TACBrSATResposta.Destroy ;
begin
  fRetornoLst.Free;
  inherited Destroy;
end ;

procedure TACBrSATResposta.Clear ;
begin
  fRetornoLst.Clear;
  fRetornoStr      := '';
  fnumeroSessao    := 0;
  fcodigoDeRetorno := 0;
end ;

{ TACBrSATConfig }

constructor TACBrSATConfig.Create ;
begin
  inherited Create;
  Clear;
end ;

destructor TACBrSATConfig.Destroy ;
begin
  inherited Destroy ;
end ;

procedure TACBrSATConfig.Clear ;
begin
  fsemit_CNPJ             := '' ;
  fsemit_cRegTrib         := RTSimplesNacional;
  fsemit_cRegTribISSQN    := RTISSMicroempresaMunicipal;
  fsemit_IE               := '' ;
  fsemit_IM               := '' ;
  fsemit_indRatISSQN      := irSim ;
  fside_CNPJ              := '' ;
  fside_numeroCaixa       := 0 ;
  fside_tpAmb             := taHomologacao ;
  fsinfCFe_versaoDadosEnt := cversaoDadosEnt ;
end ;

{ EACBrSATErro }

constructor EACBrSATErro.Create(const msg : string) ;
begin
  inherited Create( ACBrStr(msg) );
end ;

{ TACBrSATClass }

constructor TACBrSATClass.Create(AOwner : TComponent) ;
begin
  if not (AOwner is TACBrSAT) then
     raise EACBrSATErro.create( ACBrStr(cACBrSATClassCreateException) );

  inherited Create(AOwner) ;

  fpOwner := AOwner ;
  fpModeloStr := 'Não Definido' ;
  fpNomeDLL   := cLIBSAT;
end ;

destructor TACBrSATClass.Destroy ;
begin
  inherited Destroy ;
end ;

procedure TACBrSATClass.Inicializar ;
begin
  LoadDLLFunctions;
end ;

procedure TACBrSATClass.DesInicializar ;
begin
  UnLoadDLLFunctions;
end ;

function TACBrSATClass.GetPathDLL : string ;
begin
  Result := TACBrSAT(fpOwner).PathDLL;
end;

procedure TACBrSATClass.ErroAbstract(NomeProcedure : String) ;
begin
  raise EACBrSATErro.create( Format( cACBrSATCMDInvalidoException,
                                     [NomeProcedure, ModeloStr] )) ;
end ;

function TACBrSATClass.GetsignAC : String ;
begin
  Result := TACBrSAT(fpOwner).signAC;
end;

function TACBrSATClass.GetcodigoDeAtivacao : String ;
begin
  Result := TACBrSAT(fpOwner).codigoDeAtivacao;
end;

function TACBrSATClass.GetNomeDLL : String ;
begin
  Result := fpNomeDLL;;
end;

function TACBrSATClass.GetnumeroSessao : Integer ;
begin
  Result := TACBrSAT(fpOwner).numeroSessao;
end;

procedure TACBrSATClass.LoadDLLFunctions;
begin
  ErroAbstract('LoadDLLFunctions');
end;

procedure TACBrSATClass.UnLoadDLLFunctions ;
begin
  {}
end ;

function TACBrSATClass.AssociarAssinatura(CNPJvalue,
  assinaturaCNPJs : String) : String ;
begin
  ErroAbstract('AssociarAssinatura');
  Result := '';
end ;

function TACBrSATClass.AtivarSAT(subComando : Integer ; CNPJ : String ;
  cUF : Integer) : String ;
begin
  ErroAbstract('AtivarSAT');
  Result := '';
end ;

function TACBrSATClass.AtualizarSoftwareSAT : String ;
begin
  ErroAbstract('AtualizarSoftwareSAT');
  Result := '';
end ;

function TACBrSATClass.BloquearSAT : String ;
begin
  ErroAbstract('BloquearSAT');
  Result := '';
end ;

function TACBrSATClass.CancelarUltimaVenda(chave, dadosCancelamento : String
  ) : String ;
begin
  ErroAbstract('CancelarUltimaVenda');
  Result := '';
end ;

function TACBrSATClass.ComunicarCertificadoICPBRASIL(certificado : AnsiString
  ) : String ;
begin
  ErroAbstract('ComunicarCertificadoICPBRASIL');
  Result := '';
end ;

function TACBrSATClass.ConfigurarInterfaceDeRede(dadosConfiguracao : AnsiString
  ) : String ;
begin
  ErroAbstract('ConfigurarInterfaceDeRede');
  Result := '';
end ;

function TACBrSATClass.ConsultarNumeroSessao(cNumeroDeSessao : Integer
  ) : String ;
begin
  ErroAbstract('ConsultarNumeroSessao');
  Result := '';
end ;

function TACBrSATClass.ConsultarSAT : String ;
begin
  ErroAbstract('ConsultarSAT');
  Result := '';
end ;

function TACBrSATClass.ConsultarStatusOperacional : String ;
begin
  ErroAbstract('ConsultarStatusOperacional');
  Result := '';
end ;

function TACBrSATClass.DesbloquearSAT : String ;
begin
  ErroAbstract('DesbloquearSAT');
  Result := '';
end ;

function TACBrSATClass.DesligarSAT : String ;
begin
  ErroAbstract('DesligarSAT');
  Result := '';
end ;

function TACBrSATClass.EnviarDadosVenda(dadosVenda : AnsiString) : String ;
begin
  ErroAbstract('EnviarDadosVenda');
  Result := '';
end ;

function TACBrSATClass.ExtrairLogs : String ;
begin
  ErroAbstract('ExtrairLogs');
  Result := '';
end ;

function TACBrSATClass.TesteFimAFim(dadosVenda : AnsiString) : String ;
begin
  ErroAbstract('TesteFimAFim');
  Result := '';
end ;

function TACBrSATClass.TrocarCodigoDeAtivacao(opcao : Integer ; novoCodigo,
  confNovoCodigo : String) : String ;
begin
  ErroAbstract('TrocarCodigoDeAtivacao');
  Result := '';
end ;

function TACBrSATClass.GetModeloStr : String ;
begin
  Result := fpModeloStr;
end ;

procedure TACBrSATClass.FunctionDetectLibSAT(FuncName : String ;
  var LibPointer : Pointer) ;
var
  sLibName : String ;
begin
  if not Assigned( LibPointer )  then
  begin
    sLibName := NomeDLL;

    if not FunctionDetect( sLibName, FuncName, LibPointer) then
    begin
       LibPointer := NIL ;
       raise EACBrSATErro.Create( Format(cACBrSATFuncaoNaoEncontrada, [FuncName,sLibName]) ) ;
    end ;
  end ;
end ;

end.

