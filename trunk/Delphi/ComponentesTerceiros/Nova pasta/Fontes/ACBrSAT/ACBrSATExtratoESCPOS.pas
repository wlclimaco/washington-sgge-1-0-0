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
{ http://www.opensource.org/licenses/gpl-license.php                           }
{                                                                              }
{ Daniel Simões de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Praça Anita Costa, 34 - Tatuí - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 04/04/2013:  André Ferreira de Moraes
|*   Inicio do desenvolvimento
******************************************************************************}
unit ACBrSATExtratoESCPOS;

interface

uses Classes, SysUtils,
     ACBrSATExtratoClass, ACBrDevice, ACBrUtil,
     pcnCFe, pcnCFeCanc, pcnConversao, ACBrDFeUtil;

const
      cCmdImpZera = #27+'@';
      cCmdEspacoLinha = #27+'3'+#14;
      cCmdPagCod = #27+'t'+#39;
      cCmdImpNegrito = #27+'E1';
      cCmdImpFimNegrito = #27+'E2';
      cCmdImpExpandido = #29+'!'+#16;
      cCmdImpFimExpandido = #29+'!'+#0;
      cCmdFonteNormal = #27+'M0';
      cCmdFontePequena = #27+'M1';
      cCmdAlinhadoEsquerda = #27+'a0';
      cCmdAlinhadoCentro = #27+'a1';
      cCmdAlinhadoDireita = #27+'a2';
      cCmdCortaPapel = #29+'V1';      

type
  TACBrSATExtratoESCPOS = class( TACBrSATExtratoClass )
  private
    FDevice : TACBrDevice ;
    FLinhaCmd : String;
    FBuffer : TStringList;

    procedure ImprimePorta( AString : AnsiString ) ;
  protected
    procedure GerarCabecalho;
    procedure GerarItens;
    procedure GerarTotais(Resumido : Boolean = False);
    procedure GerarPagamentos(Resumido : Boolean = False );
    procedure GerarObsFisco;
    procedure GerarDadosEntrega;
    procedure GerarObsContribuinte(Resumido : Boolean = False );
    procedure GerarRodape(CortaPapel: Boolean = True; Cancelamento: Boolean = False);
    procedure GerarDadosCancelamento;
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure ImprimirExtrato(CFe : TCFe = nil); override;
    procedure ImprimirExtratoResumido(CFe : TCFe = nil); override;
    procedure ImprimirExtratoCancelamento(CFe : TCFe = nil; CFeCanc: TCFeCanc = nil); override;
  published
    property Device : TACBrDevice read FDevice ;
  end ;

implementation

uses ACBrSAT;

function Int2TB(AInteger: Integer): AnsiString;
var
   AHexStr: String;
begin
  AHexStr := IntToHex(AInteger,4);
  Result  := AnsiChar(chr( StrToInt('$'+copy(AHexStr,3,2) ) )) +
             AnsiChar(chr( StrToInt('$'+copy(AHexStr,1,2) ) )) ;
  AHexStr := Result;
end;

{ TACBrSATExtratoESCPOS }

constructor TACBrSATExtratoESCPOS.Create(AOwner: TComponent);
begin
  inherited create( AOwner );

  { Instanciando SubComponente TACBrDevice }
  FDevice := TACBrDevice.Create( self ) ;  { O dono é o proprio componente }
  FDevice.Name := 'ACBrDevice' ;      { Apenas para aparecer no Object Inspector}
  {$IFDEF COMPILER6_UP}
  FDevice.SetSubComponent( true );{ para gravar no DFM/XFM }
  {$ENDIF}
  FDevice.SetDefaultValues ;
  FDevice.Porta := 'COM1';

  FBuffer := TStringList.Create;
end;

destructor TACBrSATExtratoESCPOS.Destroy;
begin
  FBuffer.Free;

  FreeAndNil( FDevice ) ;
  
  inherited Destroy ;
end;

procedure TACBrSATExtratoESCPOS.GerarCabecalho;
begin
  FLinhaCmd := cCmdImpZera+cCmdEspacoLinha+cCmdPagCod+cCmdFonteNormal+cCmdAlinhadoCentro;
  FBuffer.clear;
  FBuffer.Add(FLinhaCmd+chr(29)+'(L'+chr(6)+chr(0)+'0E  '+chr(1)+chr(1)); // Imprimindo logo já gravado na memória

  FLinhaCmd := cCmdAlinhadoCentro+cCmdImpNegrito+FpCFe.Emit.xFant+cCmdImpFimNegrito;
  FBuffer.Add(FLinhaCmd);
  FBuffer.Add(cCmdFontePequena+FpCFe.Emit.xNome);
  FBuffer.Add(cCmdFontePequena+Trim(FpCFe.Emit.EnderEmit.xLgr)+' '+
              Trim(FpCFe.Emit.EnderEmit.nro)+' '+
              Trim(FpCFe.Emit.EnderEmit.xCpl)+' '+
              Trim(FpCFe.Emit.EnderEmit.xBairro)+'-'+
              Trim(FpCFe.Emit.EnderEmit.xMun)+'-'+
              DFeUtil.FormatarCEP(IntToStr(FpCFe.Emit.EnderEmit.CEP)));

  FLinhaCmd := cCmdAlinhadoEsquerda+cCmdFontePequena+
              'CNPJ:'+DFeUtil.FormatarCNPJ(FpCFe.Emit.CNPJCPF)+
              ' IE:'+Trim(FpCFe.Emit.IE)+
              ' IM:'+Trim(FpCFe.Emit.IM);
  FBuffer.Add(FLinhaCmd);
  FBuffer.Add(cCmdAlinhadoEsquerda+cCmdFonteNormal+'------------------------------------------------');


  if FpCFe.ide.tpAmb = taHomologacao then
   begin
     FLinhaCmd := cCmdFonteNormal+cCmdAlinhadoCentro+cCmdImpNegrito+
                 'Extrato No. 000000';
     FBuffer.Add(FLinhaCmd);
     FLinhaCmd := 'CUPOM FISCAL ELETRÔNICO - SAT'+cCmdImpFimNegrito;
     FBuffer.Add(FLinhaCmd);
     FBuffer.Add(' ');
     FBuffer.Add(padC(' = T E S T E =',48));
     FBuffer.Add(' ');
     FBuffer.Add(padC('>',48,'>'));
     FBuffer.Add(padC('>',48,'>'));
     FBuffer.Add(padC('>',48,'>'));
   end
  else
   begin
     FLinhaCmd := cCmdFonteNormal+cCmdAlinhadoCentro+cCmdImpNegrito+
                 'Extrato No. '+IntToStrZero(FpCFe.ide.nCFe,6);
     FBuffer.Add(FLinhaCmd);
     FLinhaCmd := 'CUPOM FISCAL ELETRÔNICO - SAT'+cCmdImpFimNegrito;
     FBuffer.Add(FLinhaCmd);
   end;
  FBuffer.Add('------------------------------------------------');
  FBuffer.Add(cCmdAlinhadoEsquerda+cCmdFontePequena+'CPF/CNPJ do Consumidor: '+DFeUtil.FormatarCNPJ(FpCFe.Dest.CNPJCPF));
end;

procedure TACBrSATExtratoESCPOS.GerarItens;
var
  i : integer;
begin
  FBuffer.Add(cCmdFonteNormal+'------------------------------------------------');
  FBuffer.Add('#|COD|DESC|QTD|UN|VL UN R$|(VLTR R$)*|VL ITEM R$');
  FBuffer.Add('------------------------------------------------');

  for i:=0 to FpCFe.Det.Count - 1 do
   begin
     FLinhaCmd := IntToStrZero(FpCFe.Det.Items[i].nItem,3)+' '+
                  Trim(FpCFe.Det.Items[i].Prod.cProd)+' '+
                  Trim(FpCFe.Det.Items[i].Prod.xProd)+' '+
                  DFeUtil.FormatFloat(FpCFe.Det.Items[i].Prod.qCom,'0.0000')+' '+
                  Trim(FpCFe.Det.Items[i].Prod.uCom)+' X '+
                  DFeUtil.FormatFloat(FpCFe.Det.Items[i].Prod.vUnCom,'0.000')+' ';
     if FpCFe.Det.Items[i].Imposto.vItem12741 > 0 then
        FLinhaCmd := FLinhaCmd + DFeUtil.FormatFloat(FpCFe.Det.Items[i].Imposto.vItem12741,'0.00')+'* ';

     FLinhaCmd := FLinhaCmd + '|' + FormatFloat('#,###,##0.00',FpCFe.Det.Items[i].Prod.vProd)+' ';

     FLinhaCmd := padS(FLinhaCmd,64, '|');

     FBuffer.Add(cCmdAlinhadoEsquerda+cCmdFontePequena+FLinhaCmd);

     if FpCFe.Det.Items[i].Prod.vDesc > 0 then
      begin
        FBuffer.Add(padS('desconto|'+FormatFloat('-#,###,##0.00',FpCFe.Det.Items[i].Prod.vDesc),64, '|'));
        FBuffer.Add(padS('valor líquido|'+FormatFloat('#,###,##0.00',(FpCFe.Det.Items[i].Prod.qCom*FpCFe.Det.Items[i].Prod.vUnCom)-FpCFe.Det.Items[i].Prod.vDesc),64, '|'));
      end;

     if FpCFe.Det.Items[i].Prod.vOutro > 0 then
      begin
        FBuffer.Add(padS('desconto|'+FormatFloat('-#,###,##0.00',FpCFe.Det.Items[i].Prod.vOutro),64, '|'));
        FBuffer.Add(padS('valor líquido|'+FormatFloat('#,###,##0.00',(FpCFe.Det.Items[i].Prod.qCom*FpCFe.Det.Items[i].Prod.vUnCom)+FpCFe.Det.Items[i].Prod.vOutro),64, '|'));
      end;

     if FpCFe.Det.Items[i].Imposto.ISSQN.vDeducISSQN > 0 then
      begin
        FBuffer.Add(padS('dedução para ISSQN|'+FormatFloat('-#,###,##0.00',FpCFe.Det.Items[i].Imposto.ISSQN.vDeducISSQN),64, '|'));
        FBuffer.Add(padS('base de cálculo ISSQN|'+FormatFloat('#,###,##0.00',FpCFe.Det.Items[i].Imposto.ISSQN.vBC),64, '|'));
      end;
   end;
  FBuffer.Add(cCmdAlinhadoEsquerda+cCmdFonteNormal);
end;

procedure TACBrSATExtratoESCPOS.GerarTotais(Resumido: Boolean);
begin
  if not Resumido then
   begin
     if (FpCFe.Total.ICMSTot.vDesc > 0) or (FpCFe.Total.ICMSTot.vOutro > 0) then
        FBuffer.Add(cCmdFontePequena+padS('Subtotal|'+FormatFloat('#,###,##0.00',FpCFe.Total.ICMSTot.vProd),64, '|'));
     if FpCFe.Total.ICMSTot.vDesc > 0 then
        FBuffer.Add(cCmdFontePequena+padS('Descontos|'+FormatFloat('-#,###,##0.00',FpCFe.Total.ICMSTot.vDesc),64, '|'));
     if FpCFe.Total.ICMSTot.vOutro > 0 then
        FBuffer.Add(cCmdFontePequena+padS('Acréscimos|'+FormatFloat('+#,###,##0.00',FpCFe.Total.ICMSTot.vOutro),64, '|'));
   end;

  FLinhaCmd := cCmdAlinhadoEsquerda+cCmdImpExpandido+
               padS('TOTAL R$|'+FormatFloat('#,###,##0.00',FpCFe.Total.vCFe),32, '|')+
               cCmdImpFimExpandido;
  FBuffer.Add(FLinhaCmd);
end;

procedure TACBrSATExtratoESCPOS.GerarPagamentos(Resumido : Boolean = False );
var
  i : integer;
begin
  if not Resumido then
    FBuffer.Add('');
  for i:=0 to FpCFe.Pagto.Count - 1 do
   begin
     FBuffer.Add(cCmdFontePequena+padS(CodigoMPToDescricao(FpCFe.Pagto.Items[i].cMP)+'|'+FormatFloat('#,###,##0.00',FpCFe.Pagto.Items[i].vMP),64, '|'));
   end;
  if FpCFe.Pagto.vTroco > 0 then
     FBuffer.Add(cCmdFontePequena+padS('Troco R$|'+FormatFloat('#,###,##0.00',FpCFe.Pagto.vTroco),64, '|'));
end;

procedure TACBrSATExtratoESCPOS.GerarObsFisco;
var
  i : integer;
begin
  if (FpCFe.InfAdic.obsFisco.Count > 0) or
     (FpCFe.Emit.cRegTrib = RTSimplesNacional) then
     FBuffer.Add('');

  if FpCFe.Emit.cRegTrib = RTSimplesNacional then
     FBuffer.Add(cCmdFontePequena+'ICMS a ser recolhido conforme LC 123/2006 - Simples Nacional');


  for i:=0 to FpCFe.InfAdic.obsFisco.Count - 1 do
   begin
      FBuffer.Add(cCmdFontePequena+FpCFe.InfAdic.obsFisco.Items[i].xCampo+'-'+FpCFe.InfAdic.obsFisco.Items[i].xTexto);
   end;
end;

procedure TACBrSATExtratoESCPOS.GerarDadosEntrega;
begin
  if Trim(FpCFe.Entrega.xLgr)+
     Trim(FpCFe.Entrega.nro)+
     Trim(FpCFe.Entrega.xCpl)+
     Trim(FpCFe.Entrega.xBairro)+
     Trim(FpCFe.Entrega.xMun) <> '' then
   begin
     FBuffer.Add(cCmdFonteNormal+'------------------------------------------------');
     FBuffer.Add('DADOS PARA ENTREGA');
     FBuffer.Add(cCmdFontePequena+Trim(FpCFe.Entrega.xLgr)+' '+
                 Trim(FpCFe.Entrega.nro)+' '+
                 Trim(FpCFe.Entrega.xCpl)+' '+
                 Trim(FpCFe.Entrega.xBairro)+' '+
                 Trim(FpCFe.Entrega.xMun));
     FBuffer.Add(FpCFe.Dest.xNome);
   end;
end;

procedure TACBrSATExtratoESCPOS.GerarObsContribuinte(Resumido : Boolean = False );
begin
  if Trim(FpCFe.InfAdic.infCpl) <> '' then
   begin
     FBuffer.Add(cCmdFonteNormal+'------------------------------------------------');
     FBuffer.Add('OBSERVAÇÕES DO CONTRIBUINTE');
     FBuffer.Add(cCmdFontePequena+StringReplace(Trim(FpCFe.InfAdic.infCpl),';',sLineBreak,[rfReplaceAll]));
   end;

  if FpCFe.Total.vCFeLei12741 > 0 then
   begin
     if Trim(FpCFe.InfAdic.infCpl) = '' then
      begin
       FBuffer.Add(cCmdFonteNormal+'------------------------------------------------');
       FBuffer.Add('OBSERVAÇÕES DO CONTRIBUINTE');
      end
     else
       FBuffer.Add(' ');

     FBuffer.Add(cCmdFontePequena+padS('Valor aproximado dos tributos do deste cupom R$ |'+cCmdImpNegrito+FormatFloat('#,###,##0.00',FpCFe.Total.vCFeLei12741),66, '|'));
     FBuffer.Add(cCmdImpFimNegrito+'(conforme Lei Fed. 12.741/2012)');
     if not Resumido then
      begin
        FBuffer.Add(' ');
        FBuffer.Add('*Valor aproximado dos tributos do item');
      end;
   end;
end;

procedure TACBrSATExtratoESCPOS.GerarRodape(CortaPapel: Boolean = True; Cancelamento: Boolean = False);
var
  qrcode : string;
begin
  FBuffer.Add(cCmdFonteNormal+'------------------------------------------------');
  if Cancelamento then
     FBuffer.Add(cCmdImpNegrito+'DADOS DO CUPOM FISCAL ELETRÔNICO CANCELADO'+cCmdImpFimNegrito);
  FLinhaCmd := cCmdAlinhadoCentro+'SAT No. '+
               cCmdImpNegrito+IntToStr(FpCFe.ide.nserieSAT)+cCmdImpFimNegrito;
  FBuffer.Add(FLinhaCmd);
  FBuffer.Add(DFeUtil.FormatDate(DateToStr(FpCFe.ide.dEmi))+' '+TimeToStr(FpCFe.ide.hEmi));
  FBuffer.Add(' ');
  FLinhaCmd :=  cCmdFontePequena+DFeUtil.FormatarChaveAcesso((FpCFe.infCFe.ID))+cCmdFonteNormal;
  FBuffer.Add(FLinhaCmd);
  FBuffer.Add(' ');

  FLinhaCmd := chr(29)+'h'+chr(50)+
               chr(29)+'w'+chr(2)+
               chr(29)+'H0'+
               chr(29)+'kI'+chr(24)+'{C'+AscToBcd(FpCFe.infCFe.ID,22);
  FBuffer.Add(FLinhaCmd);
  FBuffer.Add(' ');

  if ImprimeQRCode then
   begin
     qrcode := FpCFe.infCFe.ID + '|';
     qrcode := qrcode + FormatDateTime('yyyymmddhhmmss',FpCFe.ide.dEmi+FpCFe.ide.hEmi) + '|';
     qrcode := qrcode + DFeUtil.FormatFloat(FpCFe.Total.vCFe,'0.00') + '|';
     qrcode := qrcode + Trim(FpCFe.Dest.CNPJCPF) + '|';
     qrcode := qrcode + FpCFe.ide.assinaturaQRCODE;
     FLinhaCmd := chr(29)+'(k'+chr(4)+chr(0)+'1A2'+chr(0)+
                  chr(29)+'(k'+chr(3)+chr(0)+'1C'+chr(4)+
                  chr(29)+'(k'+chr(3)+chr(0)+'1E0'+
                  chr(29)+'(k'+Int2TB(length(qrcode)+3)+'1P0'+qrcode+
                  chr(29)+'(k'+chr(3)+chr(0)+'1Q0';
     FBuffer.Add(FLinhaCmd);
   end;
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');

  if CortaPapel then
     FBuffer.Add(cCmdCortaPapel);
end;

procedure TACBrSATExtratoESCPOS.GerarDadosCancelamento;
var
  qrcode : string;
begin
  FBuffer.Add(cCmdFonteNormal+'------------------------------------------------');
  FBuffer.Add(cCmdImpNegrito+'DADOS DO CUPOM FISCAL ELETRÔNICO DE CANCELAMENTO'+cCmdImpFimNegrito);
  FLinhaCmd := cCmdAlinhadoCentro+'SAT No. '+
               cCmdImpNegrito+IntToStr(FpCFe.ide.nserieSAT)+cCmdImpFimNegrito;
  FBuffer.Add(FLinhaCmd);
  FBuffer.Add(DFeUtil.FormatDate(DateToStr(FpCFeCanc.ide.dEmi))+' '+TimeToStr(FpCFeCanc.ide.hEmi));
  FBuffer.Add('');
  FLinhaCmd :=  cCmdFontePequena+DFeUtil.FormatarChaveAcesso((FpCFeCanc.infCFe.ID))+cCmdFonteNormal;
  FBuffer.Add(FLinhaCmd);
  FBuffer.Add(' ');

  FLinhaCmd := chr(29)+'h'+chr(100)+
               chr(29)+'w'+chr(2)+
               chr(29)+'H0'+
               chr(29)+'kI'+chr(24)+'{C'+AscToBcd(FpCFeCanc.infCFe.ID,22);
  FBuffer.Add(FLinhaCmd);
  FBuffer.Add(' ');

  if ImprimeQRCode then
   begin
     qrcode := FpCFe.infCFe.ID + '|';
     qrcode := qrcode + FormatDateTime('yyyymmddhhmmss',FpCFeCanc.ide.dEmi+FpCFeCanc.ide.hEmi) + '|';
     qrcode := qrcode + DFeUtil.FormatFloat(FpCFeCanc.Total.vCFe,'0.00') + '|';
     qrcode := qrcode + Trim(FpCFeCanc.Dest.CNPJCPF) + '|';
     qrcode := qrcode + FpCFeCanc.ide.assinaturaQRCODE;
     FLinhaCmd := chr(29)+'(k'+chr(4)+chr(0)+'1A2'+chr(0)+
                  chr(29)+'(k'+chr(3)+chr(0)+'1C'+chr(6)+
                  chr(29)+'(k'+chr(3)+chr(0)+'1E0'+
                  chr(29)+'(k'+Int2TB(length(qrcode)+3)+'1P0'+qrcode+
                  chr(29)+'(k'+chr(3)+chr(0)+'1Q0';
     FBuffer.Add(FLinhaCmd);
   end;
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');
  FBuffer.Add('');

  FBuffer.Add(cCmdCortaPapel);
end;


procedure TACBrSATExtratoESCPOS.ImprimePorta(AString: AnsiString);
begin
   FDevice.EnviaString( AString );
end;

procedure TACBrSATExtratoESCPOS.ImprimirExtrato(CFe: TCFe);
begin
  if CFe = nil then
   begin
     if not Assigned(ACBrSAT) then
        raise Exception.Create('Componente ACBrSAT não atribuído');

     FpCFe := TACBrSAT(ACBrSAT).CFe;
   end
  else
    FpCFe := CFe; 

  GerarCabecalho;
  GerarItens;
  GerarTotais;
  GerarPagamentos;
  GerarObsFisco;
  GerarDadosEntrega;
  GerarObsContribuinte;
  GerarRodape;

  ImprimePorta(FBuffer.Text);
end;

procedure TACBrSATExtratoESCPOS.ImprimirExtratoCancelamento(CFe: TCFe; CFeCanc: TCFeCanc);
begin
  if CFe = nil then
   begin
     if not Assigned(ACBrSAT) then
        raise Exception.Create('Componente ACBrSAT não atribuído');

     FpCFe := TACBrSAT(ACBrSAT).CFe;
     FpCFeCanc := TACBrSAT(ACBrSAT).CFeCanc;
   end
  else
   begin
     FpCFe := CFe;
     FpCFeCanc := CFeCanc;
   end;

  GerarCabecalho;
  GerarTotais(True);
  GerarRodape(False);
  GerarDadosCancelamento;
end;

procedure TACBrSATExtratoESCPOS.ImprimirExtratoResumido(CFe: TCFe);
begin
  if CFe = nil then
   begin
     if not Assigned(ACBrSAT) then
        raise Exception.Create('Componente ACBrSAT não atribuído');

     FpCFe := TACBrSAT(ACBrSAT).CFe;
   end
  else
    FpCFe := CFe;

  GerarCabecalho;
  GerarTotais(True);
  GerarPagamentos(True);
  GerarObsFisco;
  GerarDadosEntrega;
  GerarObsContribuinte(True);
  GerarRodape;

  ImprimePorta(FBuffer.Text);
end;

end.
