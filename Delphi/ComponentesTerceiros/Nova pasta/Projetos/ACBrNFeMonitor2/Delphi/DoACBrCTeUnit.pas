{******************************************************************************}
{ Projeto: ACBrNFeMonitor                                                      }
{  Executavel multiplataforma que faz uso do conjunto de componentes ACBr para }
{ criar uma interface de comunicação com equipamentos de automacao comercial.  }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Você pode obter a última versão desse arquivo na página do Projeto ACBr     }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
{                                                                              }
{  Este programa é software livre; você pode redistribuí-lo e/ou modificá-lo   }
{ sob os termos da Licença Pública Geral GNU, conforme publicada pela Free     }
{ Software Foundation; tanto a versão 2 da Licença como (a seu critério)       }
{ qualquer versão mais nova.                                                   }
{                                                                              }
{  Este programa é distribuído na expectativa de ser útil, mas SEM NENHUMA     }
{ GARANTIA; nem mesmo a garantia implícita de COMERCIALIZAÇÃO OU DE ADEQUAÇÃO A}
{ QUALQUER PROPÓSITO EM PARTICULAR. Consulte a Licença Pública Geral GNU para  }
{ obter mais detalhes. (Arquivo LICENCA.TXT ou LICENSE.TXT)                    }
{                                                                              }
{  Você deve ter recebido uma cópia da Licença Pública Geral GNU junto com este}
{ programa; se não, escreva para a Free Software Foundation, Inc., 59 Temple   }
{ Place, Suite 330, Boston, MA 02111-1307, USA. Você também pode obter uma     }
{ copia da licença em:  http://www.opensource.org/licenses/gpl-license.php     }
{                                                                              }
{ Daniel Simões de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Praça Anita Costa, 34 - Tatuí - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}
{$I ACBr.inc}

unit DoACBrCTeUnit ;

interface
Uses Classes, SysUtils, CmdUnitNFe, 
     
     ACBrDFeUtil;

Procedure DoACBrCTe( Cmd : TACBrNFeCTeCmd ) ;
procedure GerarIniCTe( AStr: WideString ) ;
function GerarCTeIni( XML : WideString ) : WideString;

implementation

Uses IniFiles, DateUtils,
  Windows, Forms, 
  ACBrUtil, ACBrNFeMonitor1 , ACBrCTeWebServices, 
  ACBrCTeConfiguracoes,
  pcnConversao,
  
  pcnAuxiliar,
  
  pcteCTeR, pcteRetConsReciCTe,
  ACBrCTeConhecimentos, pcteCTe, DoACBrNFeUnit;

Procedure DoACBrCTe( Cmd : TACBrNFeCTeCmd ) ;
var
  I,J : Integer;
  ArqCTe, ArqPDF, Chave : String;
  Salva,  OK : Boolean;
  SL     : TStringList;
  Alertas : AnsiString;

  Memo   : TStringList ;
  Files  : String ;
  dtFim  : TDateTime ;

  RetFind   : Integer ;
  SearchRec : TSearchRec ;

//  CTeRTXT            :  TNFeRTXT;
begin
 with frmAcbrNfeMonitor do
  begin
     try
        if Cmd.Metodo = 'statusservico' then
         begin
           if ACBrCTe1.WebServices.StatusServico.Executar then
            begin

              Cmd.Resposta := ACBrCTe1.WebServices.StatusServico.Msg+
                              '[STATUS]'+sLineBreak+
                              'Versao='+ACBrCTe1.WebServices.StatusServico.verAplic+sLineBreak+
                              'TpAmb='+TpAmbToStr(ACBrCTe1.WebServices.StatusServico.TpAmb)+sLineBreak+
                              'VerAplic='+ACBrCTe1.WebServices.StatusServico.VerAplic+sLineBreak+
                              'CStat='+IntToStr(ACBrCTe1.WebServices.StatusServico.CStat)+sLineBreak+
                              'XMotivo='+ACBrCTe1.WebServices.StatusServico.XMotivo+sLineBreak+
                              'CUF='+IntToStr(ACBrCTe1.WebServices.StatusServico.CUF)+sLineBreak+
                              'DhRecbto='+DateTimeToStr(ACBrCTe1.WebServices.StatusServico.DhRecbto)+sLineBreak+
                              'TMed='+IntToStr(ACBrCTe1.WebServices.StatusServico.TMed)+sLineBreak+
                              'DhRetorno='+DateTimeToStr(ACBrCTe1.WebServices.StatusServico.DhRetorno)+sLineBreak+
                              'XObs='+ACBrCTe1.WebServices.StatusServico.XObs+sLineBreak;
            end;
         end
        else if Cmd.Metodo = 'validarcte' then
         begin
           ACBrCTe1.Conhecimentos.Clear;
           if FileExists(Cmd.Params(0)) then
              ACBrCTe1.Conhecimentos.LoadFromFile(Cmd.Params(0))
           else
              raise Exception.Create('Arquivo '+Cmd.Params(0)+' não encontrado.');

           ACBrCTe1.Conhecimentos.Valida;
         end
        else if Cmd.Metodo = 'assinarcte' then
         begin
           ACBrCTe1.Conhecimentos.Clear;
           if FileExists(Cmd.Params(0)) then
              ACBrCTe1.Conhecimentos.LoadFromFile(Cmd.Params(0))
           else
              raise Exception.Create('Arquivo '+Cmd.Params(0)+' não encontrado.');

           Salva := ACBrCTe1.Configuracoes.Geral.Salvar;
           if not Salva then
            begin
             ForceDirectories(PathWithDelim(ExtractFilePath(Application.ExeName))+'Logs');
             ACBrCTe1.Configuracoes.Geral.PathSalvar := PathWithDelim(ExtractFilePath(Application.ExeName))+'Logs';
            end;
           ACBrCTe1.Configuracoes.Geral.Salvar := True;
           ACBrCTe1.Conhecimentos.Assinar;
           ACBrCTe1.Configuracoes.Geral.Salvar := Salva;
           if DFeUtil.NaoEstaVazio(ACBrCTe1.Conhecimentos.Items[0].NomeArq) then
              Cmd.Resposta := ACBrCTe1.Conhecimentos.Items[0].NomeArq
           else
              Cmd.Resposta := PathWithDelim(ACBrCTe1.Configuracoes.Geral.PathSalvar)+StringReplace(ACBrCTe1.Conhecimentos.Items[0].CTe.infCTe.ID, 'CTe', '', [rfIgnoreCase])+'-cte.xml';
         end
        else if Cmd.Metodo = 'consultarcte' then
         begin
           if FileExists(Cmd.Params(0)) or FileExists(PathWithDelim(ACBrCTe1.Configuracoes.Geral.PathSalvar)+Cmd.Params(0)) then
            begin
              ACBrCTe1.Conhecimentos.Clear;
              if FileExists(PathWithDelim(ACBrCTe1.Configuracoes.Geral.PathSalvar)+Cmd.Params(0)) then
                 ACBrCTe1.Conhecimentos.LoadFromFile(PathWithDelim(ACBrCTe1.Configuracoes.Geral.PathSalvar)+Cmd.Params(0))
              else
                 ACBrCTe1.Conhecimentos.LoadFromFile(Cmd.Params(0));

              ACBrCTe1.WebServices.Consulta.CTeChave := OnlyNumber(ACBrCTe1.Conhecimentos.Items[0].CTe.infCTe.ID);
            end
           else
            begin
              if not ValidarChave('CTe'+Cmd.Params(0)) then
                 raise Exception.Create('Chave '+Cmd.Params(0)+' inválida.')
              else
                 ACBrCTe1.WebServices.Consulta.CTeChave := Cmd.Params(0);
            end;
           try
              ACBrCTe1.WebServices.Consulta.Executar;

              Cmd.Resposta := ACBrCTe1.WebServices.Consulta.Msg+sLineBreak+
                              '[CONSULTA]'+sLineBreak+
                              'Versao='+ACBrCTe1.WebServices.Consulta.verAplic+sLineBreak+
                              'TpAmb='+TpAmbToStr(ACBrCTe1.WebServices.Consulta.TpAmb)+sLineBreak+
                              'VerAplic='+ACBrCTe1.WebServices.Consulta.VerAplic+sLineBreak+
                              'CStat='+IntToStr(ACBrCTe1.WebServices.Consulta.CStat)+sLineBreak+
                              'XMotivo='+ACBrCTe1.WebServices.Consulta.XMotivo+sLineBreak+
                              'CUF='+IntToStr(ACBrCTe1.WebServices.Consulta.CUF)+sLineBreak+
                              'ChCTe='+ACBrCTe1.WebServices.Consulta.CTeChave+sLineBreak+
                              'DhRecbto='+DateTimeToStr(ACBrCTe1.WebServices.Consulta.DhRecbto)+sLineBreak+
                              'NProt='+ACBrCTe1.WebServices.Consulta.Protocolo+sLineBreak+
                              'DigVal='+ACBrCTe1.WebServices.Consulta.protCTe.digVal+sLineBreak;

           except
              raise Exception.Create(ACBrCTe1.WebServices.Consulta.Msg);
           end;
         end
        else if Cmd.Metodo = 'cancelarcte' then
         begin
           if not ValidarChave('CTe'+Cmd.Params(0)) then
              raise Exception.Create('Chave '+Cmd.Params(0)+' inválida.')
           else
              ACBrCTe1.WebServices.Consulta.CTeChave := Cmd.Params(0);

           if not ACBrCTe1.WebServices.Consulta.Executar then
              raise Exception.Create(ACBrCTe1.WebServices.Consulta.Msg);

           ACBrCTe1.WebServices.Cancelamento.CTeChave      := ACBrCTe1.WebServices.Consulta.CTeChave;
           ACBrCTe1.WebServices.Cancelamento.Protocolo     := ACBrCTe1.WebServices.Consulta.Protocolo;
           ACBrCTe1.WebServices.Cancelamento.Justificativa := Cmd.Params(1);
           try
              ACBrCTe1.WebServices.Cancelamento.Executar;

              Cmd.Resposta := ACBrCTe1.WebServices.Cancelamento.Msg+sLineBreak+
                              '[CANCELAMENTO]'+sLineBreak+
                              'Versao='+ACBrCTe1.WebServices.Cancelamento.verAplic+sLineBreak+
                              'TpAmb='+TpAmbToStr(ACBrCTe1.WebServices.Cancelamento.TpAmb)+sLineBreak+
                              'VerAplic='+ACBrCTe1.WebServices.Cancelamento.VerAplic+sLineBreak+
                              'CStat='+IntToStr(ACBrCTe1.WebServices.Cancelamento.CStat)+sLineBreak+
                              'XMotivo='+ACBrCTe1.WebServices.Cancelamento.XMotivo+sLineBreak+
                              'CUF='+IntToStr(ACBrCTe1.WebServices.Cancelamento.CUF)+sLineBreak+
                              'ChCTe='+ACBrCTe1.WebServices.Cancelamento.CTeChave+sLineBreak+
                              'DhRecbto='+DateTimeToStr(ACBrCTe1.WebServices.Cancelamento.DhRecbto)+sLineBreak+
                              'NProt='+ACBrCTe1.WebServices.Cancelamento.Protocolo+sLineBreak;
           except
              raise Exception.Create(ACBrCTe1.WebServices.Cancelamento.Msg);
           end;
         end
        else if Cmd.Metodo = 'imprimirdacte' then
         begin
           if ACBrCTe1.DACTe.MostrarPreview then
            begin
              Restaurar1.Click;
              Application.BringToFront;
            end;
           ACBrCTe1.Conhecimentos.Clear;
           if FileExists(Cmd.Params(0)) or FileExists(PathWithDelim(ACBrCTe1.Configuracoes.Geral.PathSalvar)+Cmd.Params(0)) then
            begin
              if FileExists(Cmd.Params(0)) then
                 ACBrCTe1.Conhecimentos.LoadFromFile(Cmd.Params(0))
              else
                 ACBrCTe1.Conhecimentos.LoadFromFile(PathWithDelim(ACBrCTe1.Configuracoes.Geral.PathSalvar)+Cmd.Params(0));
            end
           else
              raise Exception.Create('Arquivo '+Cmd.Params(0)+' não encontrado.');

           if DFeUtil.NaoEstaVazio(Cmd.Params(1)) then
              ACBrCTe1.DACTe.Impressora := Cmd.Params(1)
           else
              ACBrCTe1.DACTe.Impressora := cbxImpressora.Text;

           if DFeUtil.NaoEstaVazio(Cmd.Params(2)) then
              ACBrCTe1.DACTe.NumCopias := StrToIntDef(Cmd.Params(2),1)
           else
              ACBrCTe1.DACTe.NumCopias := StrToIntDef(edtNumCopia.Text,1);

           if DFeUtil.NaoEstaVazio(Cmd.Params(3)) then
              ACBrCTe1.DACTe.ProtocoloCTE := Cmd.Params(3);
           ACBrCTe1.Conhecimentos.Imprimir;
           Cmd.Resposta := 'Dacte Impresso com sucesso';
           if ACBrCTe1.DACTe.MostrarPreview then
              Ocultar1.Click;
         end
        else if Cmd.Metodo = 'imprimirdactepdf' then
         begin
           ACBrCTe1.Conhecimentos.Clear;
           if FileExists(Cmd.Params(0)) then
              ACBrCTe1.Conhecimentos.LoadFromFile(Cmd.Params(0))
           else
              raise Exception.Create('Arquivo '+Cmd.Params(0)+' não encontrado.');

           if DFeUtil.NaoEstaVazio(Cmd.Params(1)) then
              ACBrCTe1.DACTe.ProtocoloCTE := Cmd.Params(1);

           try
              ACBrCTe1.Conhecimentos.ImprimirPDF;
              ArqPDF := OnlyNumber(ACBrCTe1.Conhecimentos.Items[0].CTe.infCTe.ID)+'.pdf';
              Cmd.Resposta := 'Arquivo criado em: '+ PathWithDelim(ACBrCTe1.DACTe.PathPDF) +
                              ArqPDF ;
           except
              raise Exception.Create('Erro ao criar o arquivo PDF');
           end;
         end
        else if Cmd.Metodo = 'inutilizarcte' then
         begin                            //CNPJ         //Justificat   //Ano                    //Modelo                 //Série                  //Num.Inicial            //Num.Final
           ACBrCTe1.WebServices.Inutiliza(Cmd.Params(0), Cmd.Params(1), StrToInt(Cmd.Params(2)), StrToInt(Cmd.Params(3)), StrToInt(Cmd.Params(4)), StrToInt(Cmd.Params(5)), StrToInt(Cmd.Params(6)));

           Cmd.Resposta := ACBrCTe1.WebServices.Inutilizacao.Msg+sLineBreak+
                           '[INUTILIZACAO]'+sLineBreak+
                           'Versao='+ACBrCTe1.WebServices.Inutilizacao.verAplic+sLineBreak+
                           'TpAmb='+TpAmbToStr(ACBrCTe1.WebServices.Inutilizacao.TpAmb)+sLineBreak+
                           'VerAplic='+ACBrCTe1.WebServices.Inutilizacao.VerAplic+sLineBreak+
                           'CStat='+IntToStr(ACBrCTe1.WebServices.Inutilizacao.CStat)+sLineBreak+
                           'XMotivo='+ACBrCTe1.WebServices.Inutilizacao.XMotivo+sLineBreak+
                           'CUF='+IntToStr(ACBrCTe1.WebServices.Inutilizacao.CUF)+sLineBreak+
                           'DhRecbto='+DateTimeToStr(ACBrCTe1.WebServices.Inutilizacao.DhRecbto)+sLineBreak+
                           'NProt='+ACBrCTe1.WebServices.Inutilizacao.Protocolo+sLineBreak;
         end
        else if Cmd.Metodo = 'enviarcte' then
         begin
           ACBrCTe1.Conhecimentos.Clear;
           if FileExists(Cmd.Params(0)) then
              ACBrCTe1.Conhecimentos.LoadFromFile(Cmd.Params(0))
           else
              raise Exception.Create('Arquivo '+Cmd.Params(0)+' não encontrado.');

           ACBrCTe1.Conhecimentos.GerarCTe;
           if Cmd.Params(2) <> '0' then
              ACBrCTe1.Conhecimentos.Assinar;

           ACBrCTe1.Conhecimentos.Valida;

           if not(ACBrCTe1.WebServices.StatusServico.Executar) then
            raise Exception.Create(ACBrCTe1.WebServices.StatusServico.Msg);

           if Trim(OnlyNumber(Cmd.Params(1))) = '' then
              ACBrCTe1.WebServices.Enviar.Lote := 1
           else
              ACBrCTe1.WebServices.Enviar.Lote := StrToIntDef( OnlyNumber(Cmd.Params(1)),1);

           ACBrCTe1.WebServices.Enviar.Executar;

           Cmd.Resposta :=  ACBrCTe1.WebServices.Enviar.Msg+sLineBreak+
                            '[ENVIO]'+sLineBreak+
                            'Versao='+ACBrCTe1.WebServices.Enviar.verAplic+sLineBreak+
                            'TpAmb='+TpAmbToStr(ACBrCTe1.WebServices.Enviar.TpAmb)+sLineBreak+
                            'VerAplic='+ACBrCTe1.WebServices.Enviar.VerAplic+sLineBreak+
                            'CStat='+IntToStr(ACBrCTe1.WebServices.Enviar.CStat)+sLineBreak+
                            'XMotivo='+ACBrCTe1.WebServices.Enviar.XMotivo+sLineBreak+
                            'CUF='+IntToStr(ACBrCTe1.WebServices.Enviar.CUF)+sLineBreak+
                            'NRec='+ACBrCTe1.WebServices.Enviar.Recibo+sLineBreak+
                            'DhRecbto='+DateTimeToStr( ACBrCTe1.WebServices.Enviar.dhRecbto)+sLineBreak+
                            'TMed='+IntToStr( ACBrCTe1.WebServices.Enviar.tMed)+sLineBreak;

           ACBrCTe1.WebServices.Retorno.Recibo := ACBrCTe1.WebServices.Enviar.Recibo;
           ACBrCTe1.WebServices.Retorno.Executar;

           Cmd.Resposta :=  Cmd.Resposta+
                            ACBrCTe1.WebServices.Retorno.Msg+sLineBreak+
                            '[RETORNO]'+sLineBreak+
                            'Versao='+ACBrCTe1.WebServices.Retorno.verAplic+sLineBreak+
                            'TpAmb='+TpAmbToStr(ACBrCTe1.WebServices.Retorno.TpAmb)+sLineBreak+
                            'VerAplic='+ACBrCTe1.WebServices.Retorno.VerAplic+sLineBreak+
                            'NRec='+ACBrCTe1.WebServices.Retorno.CteRetorno.nRec+sLineBreak+
                            'CStat='+IntToStr(ACBrCTe1.WebServices.Retorno.CStat)+sLineBreak+
                            'XMotivo='+ACBrCTe1.WebServices.Retorno.XMotivo+sLineBreak+
                            'CUF='+IntToStr(ACBrCTe1.WebServices.Retorno.CUF)+sLineBreak;

           for I:= 0 to ACBrCTe1.WebServices.Retorno.CteRetorno.ProtCTe.Count-1 do
            begin
              for J:= 0 to ACBrCTe1.Conhecimentos.Count-1 do
              begin
                if 'CTe'+ACBrCTe1.WebServices.Retorno.CteRetorno.ProtCTe.Items[i].chCTe = ACBrCTe1.Conhecimentos.Items[j].CTe.infCTe.Id  then
                begin
                  Cmd.Resposta := Cmd.Resposta+
                             '[CTE'+Trim(IntToStr(ACBrCTe1.Conhecimentos.Items[J].CTe.Ide.nCT))+']'+sLineBreak+
                             'Versao='+ACBrCTe1.WebServices.Retorno.CteRetorno.ProtCTe.Items[i].verAplic+sLineBreak+
                             'TpAmb='+TpAmbToStr(ACBrCTe1.WebServices.Retorno.CteRetorno.ProtCTe.Items[i].tpAmb)+sLineBreak+
                             'VerAplic='+ACBrCTe1.WebServices.Retorno.CteRetorno.ProtCTe.Items[i].verAplic+sLineBreak+
                             'CStat='+IntToStr(ACBrCTe1.WebServices.Retorno.CteRetorno.ProtCTe.Items[i].cStat)+sLineBreak+
                             'XMotivo='+ACBrCTe1.WebServices.Retorno.CteRetorno.ProtCTe.Items[i].xMotivo+sLineBreak+
                             'CUF='+IntToStr(ACBrCTe1.WebServices.Retorno.CteRetorno.cUF)+sLineBreak+
                             'ChCTe='+ACBrCTe1.WebServices.Retorno.CteRetorno.ProtCTe.Items[i].chCTe+sLineBreak+
                             'DhRecbto='+DateTimeToStr(ACBrCTe1.WebServices.Retorno.CteRetorno.ProtCTe.Items[i].dhRecbto)+sLineBreak+
                             'NProt='+ACBrCTe1.WebServices.Retorno.CteRetorno.ProtCTe.Items[i].nProt+sLineBreak+
                             'DigVal='+ACBrCTe1.WebServices.Retorno.CteRetorno.ProtCTe.Items[i].digVal+sLineBreak;
                  break;
                end;
              end;

              if DFeUtil.NaoEstaVazio(Cmd.Params(4)) then
                 ACBrCTe1.DACTe.Impressora := Cmd.Params(4)
              else
                 ACBrCTe1.DACTe.Impressora := cbxImpressora.Text;

              if ACBrCTe1.Conhecimentos.Items[i].Confirmada and (Cmd.Params(3) = '1') then
                 ACBrCTe1.Conhecimentos.Items[i].Imprimir;
            end;
         end
        else if (Cmd.Metodo = 'recibocte')then
         begin
           ACBrCTe1.WebServices.Recibo.Recibo := Cmd.Params(0);
           if not(ACBrCTe1.WebServices.Recibo.Executar) then
             raise Exception.Create(ACBrCTe1.WebServices.Recibo.xMotivo);

           Cmd.Resposta :=  Cmd.Resposta+
                            ACBrCTe1.WebServices.Recibo.Msg+sLineBreak+
                           '[RETORNO]'+sLineBreak+
                           'Versao='+ACBrCTe1.WebServices.Recibo.verAplic+sLineBreak+
                           'TpAmb='+TpAmbToStr(ACBrCTe1.WebServices.Recibo.TpAmb)+sLineBreak+
                           'VerAplic='+ACBrCTe1.WebServices.Recibo.VerAplic+sLineBreak+
                           'NRec='+ACBrCTe1.WebServices.Recibo.Recibo+sLineBreak+
                           'CStat='+IntToStr(ACBrCTe1.WebServices.Recibo.CStat)+sLineBreak+
                           'XMotivo='+ACBrCTe1.WebServices.Recibo.XMotivo+sLineBreak+
                           'CUF='+IntToStr(ACBrCTe1.WebServices.Recibo.CUF)+sLineBreak+
                           'ChCTe='+ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[0].chCTe+sLineBreak+
                           'NProt='+ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[0].nProt+sLineBreak+
                           'MotivoCTe='+ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[0].xMotivo+sLineBreak;

                           for I:= 0 to ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Count-1 do
                            begin
                              Cmd.Resposta := Cmd.Resposta+
                                '[CTE'+Trim(IntToStr(StrToInt(copy(ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[i].chCTe,26,9))))+']'+sLineBreak+
                                'Versao='+ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[i].verAplic+sLineBreak+
                                'TpAmb='+TpAmbToStr(ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[i].tpAmb)+sLineBreak+
                                'VerAplic='+ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[i].verAplic+sLineBreak+
                                'CStat='+IntToStr(ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[i].cStat)+sLineBreak+
                                'XMotivo='+ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[i].xMotivo+sLineBreak+
                                'CUF='+IntToStr(ACBrCTe1.WebServices.Recibo.CTeRetorno.cUF)+sLineBreak+
                                'ChCTe='+ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[i].chCTe+sLineBreak+
                                'DhRecbto='+DateTimeToStr(ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[i].dhRecbto)+sLineBreak+
                                'NProt='+ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[i].nProt+sLineBreak+
                                'DigVal='+ACBrCTe1.WebServices.Recibo.CTeRetorno.ProtCTe.Items[i].digVal+sLineBreak;
                            end;

           if ACBrCTe1.Configuracoes.Geral.Salvar then
            begin
              Cmd.Resposta :=  Cmd.Resposta+
              'Arquivo='+ACBrCTe1.Configuracoes.Geral.PathSalvar+Cmd.Params(0)+'-pro-rec.xml';
            end;
         end
        else if (Cmd.Metodo = 'consultacadastro')then
         begin
           ACBrCTe1.WebServices.ConsultaCadastro.UF   := Cmd.Params(0);
           if Cmd.Params(2) = '1' then
              ACBrCTe1.WebServices.ConsultaCadastro.IE := Cmd.Params(1)
           else
            begin
              if Length(Cmd.Params(1)) > 11 then
                 ACBrCTe1.WebServices.ConsultaCadastro.CNPJ := Cmd.Params(1)
              else
                 ACBrCTe1.WebServices.ConsultaCadastro.CPF := Cmd.Params(1);
            end;
            ACBrCTe1.WebServices.ConsultaCadastro.Executar;

            Cmd.Resposta :=  Cmd.Resposta+
                             ACBrCTe1.WebServices.ConsultaCadastro.Msg+sLineBreak+
                             'VerAplic='+ACBrCTe1.WebServices.ConsultaCadastro.verAplic+sLineBreak+
                             'cStat='+IntToStr(ACBrCTe1.WebServices.ConsultaCadastro.cStat)+sLineBreak+
                             'xMotivo='+ACBrCTe1.WebServices.ConsultaCadastro.xMotivo+sLineBreak+
                             'DhCons='+DateTimeToStr(ACBrCTe1.WebServices.ConsultaCadastro.DhCons)+sLineBreak+
                             'cUF='+IntToStr(ACBrCTe1.WebServices.ConsultaCadastro.cUF)+sLineBreak+
                             'IE='+ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].IE+sLineBreak+
                             'CNPJ='+ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].CNPJ+sLineBreak+
                             'CPF='+ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].CPF+sLineBreak+
                             'UF='+ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].UF+sLineBreak+
                             'cSit='+IntToStr(ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].cSit)+sLineBreak+
                             'xNome='+ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].xNome+sLineBreak+
                             'xFant='+ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].xFant+sLineBreak+
                             'xRegApur='+ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].xRegApur+sLineBreak+
                             'CNAE='+inttostr(ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].CNAE)+sLineBreak+
                             'dIniAtiv='+DFeUtil.FormatDate(DateToStr(ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].dIniAtiv))+sLineBreak+
                             'dUltSit='+DFeUtil.FormatDate(DateToStr(ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].dUltSit))+sLineBreak+
                             'dBaixa='+DFeUtil.FormatDate(DateToStr(ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].dBaixa))+sLineBreak+
                             'xLgr='+ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].xLgr+sLineBreak+
                             'nro='+ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].nro+sLineBreak+
                             'xCpl='+ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].xCpl+sLineBreak+
                             'xBairro='+ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].xBairro+sLineBreak+
                             'cMun='+inttostr(ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].cMun)+sLineBreak+
                             'xMun='+ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].xMun+sLineBreak+
                             'CEP='+inttostr(ACBrCTe1.WebServices.ConsultaCadastro.RetConsCad.InfCad.Items[0].CEP)+sLineBreak;

         end
        else if (Cmd.Metodo = 'criarcte')      or (Cmd.Metodo = 'criarenviarcte') or
                (Cmd.Metodo = 'criarctesefaz') or (Cmd.Metodo = 'criarenviarctesefaz') or
                (Cmd.Metodo = 'adicionarcte')  or (Cmd.Metodo = 'adicionarctesefaz') or
                (Cmd.Metodo = 'enviarlotecte') then
         begin
           if (Cmd.Metodo = 'criarcte') or (Cmd.Metodo = 'criarenviarcte') or
              (Cmd.Metodo = 'adicionarcte') then
              GerarIniCTe( Cmd.Params(0)  );
           {
           else
            begin
              if (Cmd.Metodo = 'criarctesefaz') or (Cmd.Metodo = 'criarenviarctesefaz') or
                 (Cmd.Metodo = 'adicionarctesefaz') then
                  begin
                    if not FileExists(Cmd.Params(0)) then
                       raise Exception.Create('Arquivo '+Cmd.Params(0)+' não encontrado.')
                    else
                     begin
                       ACBrCTe1.Conhecimentos.Clear;
                       ACBrCTe1.Conhecimentos.Add;
//                       NFeRTXT := TNFeRTXT.Create(ACBrCTe1.NotasFiscais.Items[0].NFe);
                       try
//                          NFeRTXT.CarregarArquivo(Cmd.Params(0));
//                          if not NFeRTXT.LerTxt then
//                             raise Exception.Create('Arquivo inválido!');
                       finally
//                          NFeRTXT.Free;
                       end;
                     end;
                  end;
            end;
            }
           if (Cmd.Metodo = 'adicionarcte')  or (Cmd.Metodo = 'adicionarctesefaz') then
            begin
              ForceDirectories(PathWithDelim(ExtractFilePath(Application.ExeName))+'Lotes'+PathDelim+'Lote'+trim(Cmd.Params(1)));
              ACBrCTe1.Conhecimentos.GerarCTe;
              Alertas := ACBrCTe1.Conhecimentos.Items[0].Alertas;
              ACBrCTe1.Conhecimentos.Valida;
              ArqCTe := PathWithDelim(PathWithDelim(ExtractFilePath(Application.ExeName))+'Lotes'+PathDelim+'Lote'+trim(Cmd.Params(1)))+OnlyNumber(ACBrCTe1.Conhecimentos.Items[0].CTe.infCTe.ID)+'-cte.xml';
              ACBrCTe1.Conhecimentos.SaveToFile(ExtractFilePath(ArqCTe));
              if not FileExists(ArqCTe) then
                 raise Exception.Create('Não foi possível criar o arquivo '+ArqCTe);
            end
           else if (Cmd.Metodo = 'criarcte')  or (Cmd.Metodo = 'criaresefaz') or
           (Cmd.Metodo = 'criarenviarcte') or (Cmd.Metodo = 'criarenviarctesefaz') then
            begin
              Salva := ACBrCTe1.Configuracoes.Geral.Salvar;
              if not Salva then
               begin
                ForceDirectories(PathWithDelim(ExtractFilePath(Application.ExeName))+'Logs');
                ACBrCTe1.Configuracoes.Geral.PathSalvar := PathWithDelim(ExtractFilePath(Application.ExeName))+'Logs';
               end;
              ACBrCTe1.Conhecimentos.GerarCTe;
              Alertas := ACBrCTe1.Conhecimentos.Items[0].Alertas;
              ACBrCTe1.Conhecimentos.Valida;
              ArqCTe := PathWithDelim(ACBrCTe1.Configuracoes.Geral.PathSalvar)+OnlyNumber(ACBrCTe1.Conhecimentos.Items[0].CTe.infCTe.ID)+'-cte.xml';
              ACBrCTe1.Conhecimentos.SaveToFile(ArqCTe);
              if not FileExists(ArqCTe) then
                raise Exception.Create('Não foi possível criar o arquivo '+ArqCTe);
            end;

           Cmd.Resposta := ArqCTe;
           if Alertas <> '' then
              Cmd.Resposta :=  Cmd.Resposta+sLineBreak+'Alertas:'+Alertas;
           if ((Cmd.Metodo = 'criarcte') or (Cmd.Metodo = 'criarctesefaz')) and (Cmd.Params(1) = '1') then
            begin
              SL := TStringList.Create;
              SL.LoadFromFile(ArqCTe);
              Cmd.Resposta :=  Cmd.Resposta+sLineBreak+SL.Text;
              SL.Free;
            end;

           if (Cmd.Metodo = 'criarenviarcte') or (Cmd.Metodo = 'criarenviarctesefaz') or (Cmd.Metodo = 'enviarlotecte') then
            begin
              //Carregar Notas quando enviar lote
              if (Cmd.Metodo = 'enviarlotecte')   then
               begin
                 if not DirectoryExists(PathWithDelim(ExtractFilePath(Application.ExeName))+'Lotes'+PathDelim+'Lote'+trim(Cmd.Params(0))) then
                    raise Exception.Create('Diretório não encontrado:'+PathWithDelim(ExtractFilePath(Application.ExeName))+'Lotes'+PathDelim+'Lote'+trim(Cmd.Params(0)))
                 else
                  begin
                    ACBrCTe1.Conhecimentos.Clear;
                    RetFind := SysUtils.FindFirst( PathWithDelim(ExtractFilePath(Application.ExeName))+'Lotes'+PathDelim+'Lote'+Cmd.Params(0)+PathDelim+'*-cte.xml', faAnyFile, SearchRec) ;
                    if (RetFind = 0) then
                     begin
                       while RetFind = 0 do
                        begin
                           ACBrCTe1.Conhecimentos.LoadFromFile(PathWithDelim(ExtractFilePath(Application.ExeName))+'Lotes'+PathDelim+'Lote'+Cmd.Params(0)+PathDelim+SearchRec.Name);
                           RetFind := FindNext(SearchRec);
                        end;
                        ACBrCTe1.Conhecimentos.GerarCTe;
                        ACBrCTe1.Conhecimentos.Assinar;
                        ACBrCTe1.Conhecimentos.Valida;
                     end
                    else
                       raise Exception.Create('Não foi encontrada nenhuma nota para o Lote: '+Cmd.Params(0) );
                  end;
               end;

                 if not(ACBrCTe1.WebServices.StatusServico.Executar) then
                  raise Exception.Create(ACBrCTe1.WebServices.StatusServico.Msg);

                 if (Cmd.Metodo = 'criarenviarcte') or (Cmd.Metodo = 'criarenviarctesefaz') then
                  begin
                    if Trim(OnlyNumber(Cmd.Params(1))) = '' then
                       ACBrCTe1.WebServices.Enviar.Lote := 1
                    else
                       ACBrCTe1.WebServices.Enviar.Lote := StrToIntDef( OnlyNumber(Cmd.Params(1)),1);
                  end
                 else
                  begin
                    if Trim(OnlyNumber(Cmd.Params(0))) = '' then
                       ACBrCTe1.WebServices.Enviar.Lote := 1
                    else
                       ACBrCTe1.WebServices.Enviar.Lote := StrToIntDef( OnlyNumber(Cmd.Params(0)),1);
                  end;
                 ACBrCTe1.WebServices.Enviar.Executar ;

                 Cmd.Resposta :=  ACBrCTe1.WebServices.Enviar.Msg+sLineBreak+
                                 '[ENVIO]'+sLineBreak+
                                 'Versao='+ACBrCTe1.WebServices.Enviar.verAplic+sLineBreak+
                                 'TpAmb='+TpAmbToStr(ACBrCTe1.WebServices.Enviar.TpAmb)+sLineBreak+
                                 'VerAplic='+ACBrCTe1.WebServices.Enviar.VerAplic+sLineBreak+
                                 'CStat='+IntToStr(ACBrCTe1.WebServices.Enviar.CStat)+sLineBreak+
                                 'XMotivo='+ACBrCTe1.WebServices.Enviar.XMotivo+sLineBreak+
                                 'CUF='+IntToStr(ACBrCTe1.WebServices.Enviar.CUF)+sLineBreak+
                                 'NRec='+ACBrCTe1.WebServices.Enviar.Recibo+sLineBreak+
                                 'DhRecbto='+DateTimeToStr(ACBrCTe1.WebServices.Enviar.dhRecbto)+sLineBreak+
                                 'TMed='+IntToStr(ACBrCTe1.WebServices.Enviar.TMed)+sLineBreak+
                                 'Msg='+ACBrCTe1.WebServices.Enviar.Msg+sLineBreak;

                 ACBrCTe1.WebServices.Retorno.Recibo := ACBrCTe1.WebServices.Enviar.Recibo;
                 ACBrCTe1.WebServices.Retorno.Executar;

                 Cmd.Resposta :=  Cmd.Resposta+
                                  ACBrCTe1.WebServices.Retorno.Msg+sLineBreak+
                                  '[RETORNO]'+sLineBreak+
                                  'Versao='+ACBrCTe1.WebServices.Retorno.verAplic+sLineBreak+
                                  'TpAmb='+TpAmbToStr(ACBrCTe1.WebServices.Retorno.TpAmb)+sLineBreak+
                                  'VerAplic='+ACBrCTe1.WebServices.Retorno.VerAplic+sLineBreak+
                                  'NRec='+ACBrCTe1.WebServices.Retorno.CteRetorno.nRec+sLineBreak+
                                  'CStat='+IntToStr(ACBrCTe1.WebServices.Retorno.CStat)+sLineBreak+
                                  'XMotivo='+ACBrCTe1.WebServices.Retorno.XMotivo+sLineBreak+
                                  'CUF='+IntToStr(ACBrCTe1.WebServices.Retorno.CUF)+sLineBreak;

                 for I:= 0 to ACBrCTe1.WebServices.Retorno.CTeRetorno.ProtCTe.Count-1 do
                  begin
                   for J:= 0 to ACBrCTe1.Conhecimentos.Count-1 do
                    begin
                     if 'CTe'+ACBrCTe1.WebServices.Retorno.CTeRetorno.ProtCTe.Items[i].chCTe = ACBrCTe1.Conhecimentos.Items[j].CTe.infCTe.Id  then
                      begin
                        Cmd.Resposta := Cmd.Resposta+
                                   '[CTE'+Trim(IntToStr(ACBrCTe1.Conhecimentos.Items[j].CTe.Ide.nCT))+']'+sLineBreak+
                                   'Versao='+ACBrCTe1.WebServices.Retorno.CTeRetorno.ProtCTe.Items[i].verAplic+sLineBreak+
                                   'TpAmb='+TpAmbToStr(ACBrCTe1.WebServices.Retorno.CTeRetorno.ProtCTe.Items[i].tpAmb)+sLineBreak+
                                   'VerAplic='+ACBrCTe1.WebServices.Retorno.CTeRetorno.ProtCTe.Items[i].verAplic+sLineBreak+
                                   'CStat='+IntToStr(ACBrCTe1.WebServices.Retorno.CTeRetorno.ProtCTe.Items[i].cStat)+sLineBreak+
                                   'XMotivo='+ACBrCTe1.WebServices.Retorno.CTeRetorno.ProtCTe.Items[i].xMotivo+sLineBreak+
                                   'CUF='+IntToStr(ACBrCTe1.WebServices.Retorno.CteRetorno.cUF)+sLineBreak+
                                   'ChCTe='+ACBrCTe1.WebServices.Retorno.CTeRetorno.ProtCTe.Items[i].chCTe+sLineBreak+
                                   'DhRecbto='+DateTimeToStr(ACBrCTe1.WebServices.Retorno.CTeRetorno.ProtCTe.Items[i].dhRecbto)+sLineBreak+
                                   'NProt='+ACBrCTe1.WebServices.Retorno.CTeRetorno.ProtCTe.Items[i].nProt+sLineBreak+
                                   'DigVal='+ACBrCTe1.WebServices.Retorno.CTeRetorno.ProtCTe.Items[i].digVal+sLineBreak+
                                   'Arquivo='+PathWithDelim(ACBrCTe1.Configuracoes.Geral.PathSalvar)+OnlyNumber(ACBrCTe1.Conhecimentos.Items[j].CTe.infCTe.ID)+'-CTe.xml'+sLineBreak;
                        if (Cmd.Params(2) = '1') and ACBrCTe1.DACTe.MostrarPreview then
                         begin
                           Restaurar1.Click;
                           Application.BringToFront;
                         end;
                        ACBrCTe1.DACTe.Impressora := cbxImpressora.Text;
                        if ACBrCTe1.Conhecimentos.Items[i].Confirmada and (Cmd.Params(2) = '1') then
                           ACBrCTe1.Conhecimentos.Items[i].Imprimir;
                        if (Cmd.Params(2) = '1') and ACBrCTe1.DACTe.MostrarPreview then
                           Ocultar1.Click;
                        break;
                      end;
                    end;
                  end;

            end;
         end
        else if Cmd.Metodo = 'enviaremail' then
         begin
           ACBrCTe1.Conhecimentos.Clear;
           if FileExists(Cmd.Params(1)) or FileExists(PathWithDelim(ACBrCTe1.Configuracoes.Geral.PathSalvar)+Cmd.Params(1)) then
            begin
              if FileExists(Cmd.Params(1)) then
               begin
                 ACBrCTe1.Conhecimentos.LoadFromFile(Cmd.Params(1));
                 ArqCTe := Cmd.Params(1);
               end
              else
               begin
                 ACBrCTe1.Conhecimentos.LoadFromFile(PathWithDelim(ACBrCTe1.Configuracoes.Geral.PathSalvar)+Cmd.Params(1));
                 ArqCTe := PathWithDelim(ACBrCTe1.Configuracoes.Geral.PathSalvar)+Cmd.Params(1);
               end;
            end
           else
              raise Exception.Create('Arquivo '+Cmd.Params(1)+' não encontrado.');

           if (Cmd.Params(2) = '1') then
            begin
              try
                 ACBrCTe1.Conhecimentos.ImprimirPDF;
                 ArqPDF := ACBrCTe1.Conhecimentos.Items[0].CTe.infCTe.ID ;

                 ArqPDF := OnlyNumber(ACBrCTe1.Conhecimentos.Items[0].CTe.infCTe.ID);
                 ArqPDF := PathWithDelim(ACBrCTe1.DACTe.PathPDF)+ArqPDF+'.pdf';
              except
                 raise Exception.Create('Erro ao criar o arquivo PDF');
              end;
            end;
            try
               if rgEmailTipoEnvio.ItemIndex = 0 then
                  EnviarEmail(edtSmtpHost.Text, edtSmtpPort.Text, edtSmtpUser.Text, edtSmtpPass.Text, edtSmtpUser.Text, Cmd.Params(0),DFeUtil.SeSenao(DFeUtil.NaoEstaVazio(Cmd.Params(3)),Cmd.Params(3),edtEmailAssunto.Text), ArqCTe, ArqPDF, mmEmailMsg.Lines, cbEmailSSL.Checked,Cmd.Params(4))
               else
                  EnviarEmailIndy(edtSmtpHost.Text, edtSmtpPort.Text, edtSmtpUser.Text, edtSmtpPass.Text, edtSmtpUser.Text, Cmd.Params(0),DFeUtil.SeSenao(DFeUtil.NaoEstaVazio(Cmd.Params(3)),Cmd.Params(3),edtEmailAssunto.Text), ArqCTe, ArqPDF, mmEmailMsg.Lines, cbEmailSSL.Checked,Cmd.Params(4));
               Cmd.Resposta := 'Email enviado com sucesso';
            except
               on E: Exception do
                begin
                  raise Exception.Create('Erro ao enviar email'+sLineBreak+E.Message);
                end;
            end;
         end

        else if Cmd.Metodo = 'setcertificado' then
         begin
           if (Cmd.Params(0)<>'') then
            begin
             {$IFDEF ACBrNFeOpenSSL}
                ACBrCTe1.Configuracoes.Certificados.Certificado := Cmd.Params(0);
                ACBrCTe1.Configuracoes.Certificados.Senha       := Cmd.Params(1);
                edtCaminho.Text := ACBrCTe1.Configuracoes.Certificados.Certificado;
                edtSenha.Text   := ACBrCTe1.Configuracoes.Certificados.Senha;
             {$ELSE}
                ACBrCTe1.Configuracoes.Certificados.NumeroSerie := Cmd.Params(0);
                ACBrCTe1.Configuracoes.Certificados.Senha       := Cmd.Params(1);                
                edtCaminho.Text := ACBrCTe1.Configuracoes.Certificados.NumeroSerie;
             {$ENDIF}
                frmAcbrNfeMonitor.SalvarIni;
            end
           else
              raise Exception.Create('Certificado '+Cmd.Params(0)+' Inválido.');
         end

        else if Cmd.Metodo = 'setambiente' then //1-Produção 2-Homologação
         begin
           if (StrToInt(Cmd.Params(0))>=1) and (StrToInt(Cmd.Params(0))<=2) then
            begin
              ACBrCTe1.Configuracoes.WebServices.Ambiente := StrToTpAmb(OK, Cmd.Params(0));
              rgTipoAmb.ItemIndex := ACBrCTe1.Configuracoes.WebServices.AmbienteCodigo-1;
              frmAcbrNfeMonitor.SalvarIni;
            end
           else
              raise Exception.Create('Ambiente Inválido.');
         end

        else if Cmd.Metodo = 'setformaemissao' then 
         begin
           if (StrToInt(Cmd.Params(0))>=1) and (StrToInt(Cmd.Params(0))<=5) then
            begin
              ACBrCTe1.Configuracoes.Geral.FormaEmissao := StrToTpEmis(OK, Cmd.Params(0));
              rgFormaEmissao.ItemIndex := ACBrCTe1.Configuracoes.Geral.FormaEmissaoCodigo-1;
              frmAcbrNfeMonitor.SalvarIni;
            end
           else
              raise Exception.Create('Forma de Emissão Inválida.');
         end

        else if Cmd.Metodo = 'lercte' then
         begin
           try
              Cmd.Resposta := GerarCTeIni( Cmd.Params(0)  )
           except
               on E: Exception do
                begin
                  raise Exception.Create('Erro ao gerar INI da CTe.'+sLineBreak+E.Message);
                end;
           end;
         end

        else if Cmd.Metodo = 'ctetotxt' then  //1-Arquivo XML, 2-NomeArqTXT
         begin
           ACBrCTe1.Conhecimentos.Clear;
           if FileExists(Cmd.Params(0)) then
              ACBrCTe1.Conhecimentos.LoadFromFile(Cmd.Params(0))
           else
              raise Exception.Create('Arquivo '+Cmd.Params(0)+' não encontrado.');

           ACBrCTe1.Conhecimentos.Items[0].SaveToFile(Cmd.Params(1));
           Cmd.Resposta := ChangeFileExt(ACBrCTe1.Conhecimentos.Items[0].NomeArq,'.txt');
         end

        else if Cmd.Metodo = 'savetofile' then
         begin
           Memo := TStringList.Create ;
           try
              Memo.Clear ;
              Memo.Text := ConvertStrRecived( cmd.Params(1) );
              Memo.SaveToFile( Cmd.Params(0) );
           finally
              Memo.Free ;
           end ;
         end

        else if Cmd.Metodo = 'loadfromfile' then
         begin
           Files := Cmd.Params(0) ;
           dtFim := IncSecond(now, StrToIntDef(Cmd.Params(1),1) ) ;
           while now <= dtFim do
           begin
              if FileExists( Files ) then
              begin
                 Memo  := TStringList.Create ;
                 try
                    Memo.Clear ;
                    Memo.LoadFromFile( Files ) ;
                    Cmd.Resposta := Memo.Text ;
                    Break ;
                 finally
                    Memo.Free ;
                 end ;
              end ;

              {$IFNDEF NOGUI}
               Application.ProcessMessages ;
              {$ENDIF}
              sleep(100) ;
           end ;

           if not FileExists( Cmd.Params(0) ) then
              raise Exception.Create('Arquivo '+Cmd.Params(0)+' não encontrado')
         end

        else if Cmd.Metodo = 'fileexists' then
         begin
           if not FileExists( Cmd.Params(0) ) then
              raise Exception.Create('Arquivo '+Cmd.Params(0)+' não encontrado')
         end


        else if Cmd.Metodo = 'certificadodatavencimento' then
         begin
           {$IFDEF ACBrNFeOpenSSL}
              Cmd.Resposta := 'Função disponível apenas na versão CAPICOM'
           {$ELSE}
              Cmd.Resposta := DateToStr(ACBrCTe1.Configuracoes.Certificados.DataVenc);
           {$ENDIF}
         end

        else if Cmd.Metodo = 'lerini' then // Recarrega configurações do arquivo INI
           frmAcbrNfeMonitor.LerIni

        else if Cmd.Metodo = 'gerarchave' then
         begin
           GerarChave(Chave,
                      StrToInt(Cmd.Params(0)), //codigoUF
                      StrToInt(Cmd.Params(1)), //codigoNumerico
                      StrToInt(Cmd.Params(2)), //modelo
                      StrToInt(Cmd.Params(3)), //serie
                      StrToInt(Cmd.Params(4)), //numero
                      StrToInt(Cmd.Params(5)), //tpemi
                      DFeUtil.StringToDate(Cmd.Params(6)), //emissao
                      Cmd.Params(7)); //CNPJ
           Cmd.Resposta := Chave;
         end

        else if Cmd.Metodo = 'restaurar' then
           Restaurar1Click( frmAcbrNfeMonitor )

        else if Cmd.Metodo = 'ocultar' then
           Ocultar1Click( frmAcbrNfeMonitor )

        else if Cmd.Metodo = 'encerrarmonitor' then
           Application.Terminate

        else if Cmd.Metodo = 'ativo' then
           Cmd.Resposta := 'Ativo'

        else if pos('|'+Cmd.Metodo+'|', '|exit|bye|fim|sair|') > 0 then {fecha conexao}
         begin
           Cmd.Resposta := 'Obrigado por usar o ACBrNFeMonitor' ;
           mCmd.Lines.Clear;

           if Assigned( Conexao ) then
              if Assigned( Conexao.Connection ) then
                 Conexao.Connection.Disconnect ;
         end


        else //Else Final - Se chegou ate aqui, o comando é inválido
           raise Exception.Create('Comando inválido ('+Cmd.Comando+')') ;
     finally
        { Nada a fazer aqui por enquanto... :) }
     end ;
  end;
end;


procedure GerarIniCTe( AStr: WideString ) ;
var
  I, J, K : Integer;
  sSecao, sFim, sCodPro, sNumeroDI, sNumeroADI, sQtdVol, sNumDup, sCampoAdic, sTipo, sDia, sDeduc : String;
  INIRec : TMemIniFile ;
  SL     : TStringList;
  OK     : boolean;
begin
 INIRec := TMemIniFile.create( 'cte.ini' ) ;
 SL := TStringList.Create;
 if FilesExists(Astr) then
    SL.LoadFromFile(AStr)
 else
    Sl.Text := ConvertStrRecived( Astr );
 INIRec.SetStrings( SL );
 SL.Free ;
 with frmAcbrNfeMonitor do
  begin
   try
      ACBrCTe1.Conhecimentos.Clear;
      with ACBrCTe1.Conhecimentos.Add.CTe do
       begin
          Ide.cCT         := INIRec.ReadInteger('ide','cCT', 0);
          Ide.CFOP        := INIRec.ReadInteger('ide','CFOP',0);
          Ide.natOp       := INIRec.ReadString('ide','natOp',EmptyStr);
          Ide.forPag      := StrTotpforPag(OK,INIRec.ReadString('ide','forPag','0'));
          Ide.modelo      := INIRec.ReadString( 'ide','mod' ,'55');
          Ide.serie       := INIRec.ReadInteger( 'ide','serie'  ,1);
          Ide.nCT         := INIRec.ReadInteger( 'ide','nCT' ,0);
          Ide.dhEmi       := DFeUtil.StringToDate(INIRec.ReadString( 'ide','dhEmi','0'));
          Ide.tpImp       := StrToTpImp(  OK, INIRec.ReadString( 'ide','tpImp',TpImpToStr(ACBrCTe1.DACTe.TipoDACTE)));
          Ide.tpEmis      := StrToTpEmis( OK,INIRec.ReadString( 'ide','tpemis',IntToStr(ACBrCTe1.Configuracoes.Geral.FormaEmissaoCodigo)));
          Ide.procEmi     := StrToProcEmi(OK,INIRec.ReadString( 'ide','procEmi','0'));
          Ide.verProc     := INIRec.ReadString(  'ide','verProc' ,'1.0.0.0' );
          Ide.dhCont      := DFeUtil.StringToDate(INIRec.ReadString( 'ide','dhCont'  ,'0'));
          Ide.xJust       := INIRec.ReadString(  'ide','xJust' ,'' );
          Ide.tpCTe       := StrTotpCTe(OK,INIRec.ReadString('ide','tpCTe','0'));
          Ide.refCTe      := INIRec.ReadString('ide','refCTe','');
          Ide.cMunEnv     := INIRec.ReadInteger('ide','cMunEnv',0);
          Ide.xMunEnv     := INIRec.ReadString('ide','xMunEnv','');
          Ide.UFEnv       := INIRec.ReadString('ide','UFEnv','');
          if Ide.cMunEnv <= 0 then
            Ide.cMunEnv := ObterCodigoMunicipio(Ide.xMunEnv,Ide.UFEnv);
          Ide.modal       := StrToTpModal(OK, INIRec.ReadString('ide','modal','01'));
          Ide.tpServ      := StrToTpServ(OK,INIRec.ReadString('ide','tpServ','0'));
          Ide.cMunIni     := INIRec.ReadInteger('ide','cMunIni',0);
          Ide.xMunIni     := INIRec.ReadString('ide','xMunIni','');
          Ide.UFIni       := INIRec.ReadString('ide','UFIni','');
          if Ide.cMunIni <= 0 then
            Ide.cMunIni := ObterCodigoMunicipio(Ide.xMunIni,Ide.UFIni);
          Ide.cMunFim     := INIRec.ReadInteger('ide','cMunFim',0);
          Ide.xMunFim     := INIRec.ReadString('ide','xMunFim','');
          Ide.UFFim       := INIRec.ReadString('ide','UFFim','');
          if Ide.cMunFim <= 0 then
            Ide.cMunFim := ObterCodigoMunicipio(Ide.xMunFim,Ide.UFFim);
          Ide.retira      := StrToTpRetira(OK,INIRec.ReadString('ide','retira','0'));
          if INIRec.ReadString('ide','xDetRetira','') <> '' then
            Ide.xDetRetira  := INIRec.ReadString('ide','xDetRetira','');

          Ide.toma03.Toma := StrToTpTomador(OK,INIRec.ReadString('toma3','toma','0'));
          {
          Ide.toma4.CNPJCPF := INIRec.ReadString('toma4','CNPJCPF','');
          Ide.toma4.IE      := INIRec.ReadString('toma4','IE','');
          Ide.toma4.xNome   := INIRec.ReadString('toma4','xNome','');
          Ide.toma4.xFant   := INIRec.ReadString('toma4','xFant','');
          Ide.toma4.fone    := INIRec.ReadString('toma4','fone','');
            with Ide.toma4.enderToma do
            begin
              xLgr    := INIRec.ReadString('toma4','xLgr','');
              nro     := INIRec.ReadString('toma4','nro','');
              xCpl    := INIRec.ReadString('toma4','xCpl','');
              xBairro := INIRec.ReadString('toma4','xBairro','');
              cMun    := INIRec.ReadInteger('toma4','cMun',0);
              xMun    := INIRec.ReadString('toma4','xMun','');
              CEP     := INIRec.ReadInteger('toma4','CEP',0);
              UF      := INIRec.ReadString('toma4','UF','');
              if cMun <= 0 then
                cMun := ObterCodigoMunicipio(xMun,UF);
              cPais   := INIRec.ReadInteger('toma4','cPais',0);
              xPais   := INIRec.ReadString('toma4','xPais','');
            end;
          Ide.toma4.email   := INIRec.ReadString('toma4','email','');
          }
          Ide.dhCont     := DFeUtil.StringToDate(INIRec.ReadString( 'ide','dhCont'  ,'0'));
          Ide.xJust      := INIRec.ReadString(  'ide','xJust' ,'' );

          compl.xEmi     := INIRec.ReadString('compl','xEmi','');
          compl.xCaracAd := INIRec.ReadString('compl','xCaracAd', '' );
          compl.xCaracSer:= INIRec.ReadString('compl','xCaracSer',''  );

          compl.Entrega.TipoData := StrToTpDataPeriodo(ok,INIRec.ReadString('compl','tpPer','0'));
          compl.Entrega.TipoHora := StrToTpHorarioIntervalo(ok,INIRec.ReadString('compl','tpHor','0'));
          {ainda tem mais dados aqui}

          {...}
          compl.origCalc  := INIRec.ReadString('compl','origCalc','');
          compl.destCalc  := INIRec.ReadString('compl','destCalc','');
          compl.xObs      := INIRec.ReadString('compl','xObx','');

          Emit.CNPJ   := INIRec.ReadString('emit','CNPJ','');
          Emit.IE     := INIRec.ReadString('emit','IE','');
          Emit.xNome  := INIRec.ReadString('emit','xNome','');
          Emit.xFant  := INIRec.ReadString('emit','xFant','');

          Emit.enderEmit.xLgr     := INIRec.ReadString('emit','xLgr','');
          Emit.enderEmit.nro      := INIRec.ReadString('emit','nro','');
          Emit.enderEmit.xCpl     := INIRec.ReadString('emit', 'xCpl','');
          Emit.enderEmit.xBairro  := INIRec.ReadString('emit','xBairro','');
          Emit.enderEmit.cMun     := INIRec.ReadInteger('emit','cMun',0);
          Emit.enderEmit.xMun     := INIRec.ReadString('emit','xMun','');
          Emit.enderEmit.CEP      := INIRec.ReadInteger('emit','CEP',0);
          Emit.enderEmit.UF       := INIRec.ReadString('emit','UF','');
          if Emit.enderEmit.cMun <= 0 then
            Emit.enderEmit.cMun := ObterCodigoMunicipio(Emit.enderEmit.xMun,Emit.enderEmit.UF);
          Emit.enderEmit.fone     := INIRec.ReadString('emit','fone','');

          ide.cUF   := INIRec.ReadInteger('ide','cUF', UFparaCodigo(Emit.enderEmit.UF));

          Rem.CNPJCPF             := INIRec.ReadString('rem','CNPJCPF','');
          Rem.IE                  := INIRec.ReadString('rem','IE','');
          Rem.xNome               := INIRec.ReadString('rem','xNome','');
          Rem.xFant               := INIRec.ReadString('rem','xFant','');
          Rem.fone                := INIRec.ReadString('rem','fone','');

          Rem.enderReme.xLgr      := INIRec.ReadString('rem','xLgr','');
          Rem.enderReme.nro       := INIRec.ReadString('rem','nro','');
          Rem.enderReme.xCpl      := INIRec.ReadString('rem','xCpl','');
          Rem.enderReme.xBairro   := INIRec.ReadString('rem','xBairro','');
          Rem.enderReme.cMun      := INIRec.ReadInteger('rem','cMun',0);
          Rem.enderReme.xMun      := INIRec.ReadString('rem','xMun','');
          Rem.enderReme.CEP       := INIRec.ReadInteger('rem','CEP',0);
          Rem.enderReme.UF        := INIRec.ReadString('rem','UF','');
          if Rem.enderReme.cMun <= 0 then
            Rem.enderReme.cMun    := ObterCodigoMunicipio(Rem.enderReme.xMun,Rem.enderReme.UF);
          Rem.enderReme.cPais      := INIRec.ReadInteger( 'rem','PaisCod'    ,1058);
          Rem.enderReme.xPais      := INIRec.ReadString(  'rem','Pais'       ,'BRASIL');
          Rem.email                := INIRec.ReadString(  'rem','Email' ,'');

           // abaixo não sei se tem ou não
          I := 1 ;
          while true do
          begin
            sSecao    := 'infNF'+IntToStrZero(I,3) ;
            sCodPro   := INIRec.ReadString(sSecao,'mod','FIM') ;
            if sCodPro = 'FIM' then
               break ;

            with Rem.infNF.Add do
            begin
//              nRoma   := INIRec.ReadString(sSecao,'nRoma','');
//              nPed    := INIRec.ReadString(sSecao,'nPed','');
              modelo  := StrToModeloNF(OK,INIRec.ReadString(sSecao,'mod','01'));
              serie   := INIRec.ReadString(sSecao,'serie','');
              nDoc    := INIRec.ReadString(sSecao,'nDoc','');
              dEmi    := DFeUtil.StringToDate(INIRec.ReadString( sSecao,'dEmi','0'));
//              vBC     := StringToFloatDef( INIRec.ReadString(sSecao,'vBC','') ,0);
//              vICMS   := StringToFloatDef( INIRec.ReadString(sSecao,'vICMS','') ,0);
//              vBCST   := StringToFloatDef( INIRec.ReadString(sSecao,'vBCST','') ,0);
//              vST     := StringToFloatDef( INIRec.ReadString(sSecao,'vST','') ,0);
//              vProd   := StringToFloatDef( INIRec.ReadString(sSecao,'vProd','') ,0);
//              vNF     := StringToFloatDef( INIRec.ReadString(sSecao,'vNF','') ,0);
              nCFOP   := INIRec.ReadInteger(sSecao,'nCFOP',0) ;
//              nPeso   := StringToFloatDef( INIRec.ReadString(sSecao,'nPeso','') ,0);
//              PIN     := INIRec.ReadString(sSecao,'PIN','');

//              locRet.CNPJCPF  := INIRec.ReadString(sSecao,'CNPJCPF','');
//              locRet.xNome    := INIRec.ReadString(sSecao,'xNome','');
//              locRet.xLgr     := INIRec.ReadString(sSecao,'xLgr','');
//              locRet.nro      := INIRec.ReadString(sSecao,'nro','');
//              locRet.xCpl     := INIRec.ReadString(sSecao,'xCpl','');
//              locRet.xBairro  := INIRec.ReadString(sSecao,'xBairro','');
//              locRet.cMun     := INIRec.ReadInteger(sSecao,'cMun',0);
//              locRet.xMun     := INIRec.ReadString(sSecao,'xMun','');
//              locRet.uf       := INIRec.ReadString(sSecao,'UF','');
//              if locRet.cMun <= 0 then
//                locRet.cMun := ObterCodigoMunicipio(locRet.xMun,locRet.UF);
            end;
            inc(I);
          end;

          I := 1 ;
          while true do
          begin
            sSecao    := 'infNFe'+IntToStrZero(I,3) ;
            sCodPro   := INIRec.ReadString(sSecao,'Chave','FIM') ;
            if sCodPro = 'FIM' then
               break ;
            with Rem.infNFe.Add do
            begin
              chave := INIRec.ReadString(sSecao,'chave','');
              if INIRec.ReadString(sSecao,'PIN','') <> '' then
                PIN   := INIRec.ReadString(sSecao,'PIN','');
            end;
            Inc(I);
          end;

          Dest.CNPJCPF  := INIRec.ReadString('Dest','CNPJCPF','');
          Dest.IE       := INIRec.ReadString('Dest','IE','');
          Dest.xNome    := INIRec.ReadString('Dest','xNome','');
          Dest.fone     := INIRec.ReadString('Dest','fone','');

          Dest.enderDest.xLgr     := INIRec.ReadString('Dest','xLgr','');
          Dest.enderDest.nro      := INIRec.ReadString('Dest','nro','');
          if INIRec.ReadString('Dest','xCpl','') <> '' then
            Dest.enderDest.xCpl   := INIRec.ReadString('Dest','xCpl','');
          Dest.enderDest.xBairro  := INIRec.ReadString('Dest','xBairro','');
          Dest.enderDest.cMun     := INIRec.ReadInteger('Dest','cMun',0);
          Dest.enderDest.xMun     := INIRec.ReadString('Dest','xMun','');
          Dest.enderDest.CEP      := INIRec.ReadInteger('Dest', 'CEP',0);
          Dest.enderDest.UF       := INIRec.ReadString('Dest','UF','');
          if Dest.enderDest.cMun <= 0 then
            Dest.enderDest.cMun :=ObterCodigoMunicipio(Dest.enderDest.xMun,Dest.enderDest.UF);
          Dest.enderDest.cPais    := INIRec.ReadInteger('Dest','cPais',1058);
          Dest.enderDest.xPais    := INIRec.ReadString('Dest', 'xPais', 'BRASIL');

          vPrest.vTPrest := StringToFloatDef( INIRec.ReadString('vPrest','vTPrest','') ,0);
          vPrest.vRec := StringToFloatDef( INIRec.ReadString('vPrest','vRec','') ,0);

          I := 1 ;
          while true do
          begin
            sSecao    := 'Comp'+IntToStrZero(I,3) ;
            sCodPro   := INIRec.ReadString(sSecao,'xNome','FIM') ;
            if sCodPro = 'FIM' then
               break ;
            with vPrest.comp.Add do
            begin
              xNome := INIRec.ReadString(sSecao,'xNome','');
              vComp := StringToFloatDef( INIRec.ReadString(sSecao,'vComp','') ,0);
            end;
            Inc(I);
          end;

          if INIRec.ReadString('ICMS00', 'CST','') <> '' then
          begin
            Imp.ICMS.ICMS00.CST   := StrToCSTICMS(OK,INIRec.ReadString('ICMS00','CST','00'));
            Imp.ICMS.ICMS00.vBC   := StringToFloatDef( INIRec.ReadString('ICMS00','vBC','') ,0);
            Imp.ICMS.ICMS00.pICMS := StringToFloatDef( INIRec.ReadString('ICMS00','pICMS','') ,0);
            Imp.ICMS.ICMS00.vICMS := StringToFloatDef( INIRec.ReadString('ICMS00','vICMS','') ,0);
          end;

          if INIRec.ReadString('ICMS20', 'CST','') <> '' then
          begin
            Imp.ICMS.ICMS20.CST     := StrToCSTICMS(OK,INIRec.ReadString('ICMS20','CST','00'));
            Imp.ICMS.ICMS20.pRedBC  := StringToFloatDef( INIRec.ReadString('ICMS20','pRedBC','') ,0);
            Imp.ICMS.ICMS20.vBC     := StringToFloatDef( INIRec.ReadString('ICMS20','vBC','') ,0);
            Imp.ICMS.ICMS20.pICMS   := StringToFloatDef( INIRec.ReadString('ICMS20','pICMS','') ,0);
            Imp.ICMS.ICMS20.vICMS   := StringToFloatDef( INIRec.ReadString('ICMS20','vICMS','') ,0);
          end;

          if INIRec.ReadString('ICMS45','CST','') <> '' then
            Imp.ICMS.ICMS45.CST := StrToCSTICMS(OK,INIRec.ReadString('ICMS45','CST','40'));

          if INIRec.ReadString('ICMS60', 'CST','') <> '' then
          begin
            Imp.ICMS.ICMS60.CST     := StrToCSTICMS(OK,INIRec.ReadString('ICMS60','CST','60'));
            Imp.ICMS.ICMS60.vBCSTRet  := StringToFloatDef( INIRec.ReadString('ICMS60','vBCSTRet','') ,0);
            Imp.ICMS.ICMS60.vICMSSTRet     := StringToFloatDef( INIRec.ReadString('ICMS60','vICMSSTRet','') ,0);
            Imp.ICMS.ICMS60.pICMSSTRet   := StringToFloatDef( INIRec.ReadString('ICMS60','pICMSSTRet','') ,0);
            Imp.ICMS.ICMS60.vCred   := StringToFloatDef( INIRec.ReadString('ICMS60','vCred','') ,0);
          end;

          if INIRec.ReadString('ICMS90', 'CST','') <> '' then
          begin
            Imp.ICMS.ICMS90.CST     := StrToCSTICMS(OK,INIRec.ReadString('ICMS90','CST','90'));
            Imp.ICMS.ICMS90.pRedBC  := StringToFloatDef( INIRec.ReadString('ICMS90','pRedBC','') ,0);
            Imp.ICMS.ICMS90.vBC     := StringToFloatDef( INIRec.ReadString('ICMS90','vBC','') ,0);
            Imp.ICMS.ICMS90.pICMS   := StringToFloatDef( INIRec.ReadString('ICMS90','pICMS','') ,0);
            Imp.ICMS.ICMS90.vICMS   := StringToFloatDef( INIRec.ReadString('ICMS90','vICMS','') ,0);
            Imp.ICMS.ICMS90.vCred   := StringToFloatDef( INIRec.ReadString('ICMS90','vCred','') ,0);
          end;

          if INIRec.ReadString('ICMSOutraUF', 'CST','') <> '' then
          begin
            Imp.ICMS.ICMSOutraUF.CST     := StrToCSTICMS(OK,INIRec.ReadString('ICMSOutraUF','CST','90'));
            Imp.ICMS.ICMSOutraUF.pRedBCOutraUF  := StringToFloatDef( INIRec.ReadString('ICMSOutraUF','pRedBCOutraUF','') ,0);
            Imp.ICMS.ICMSOutraUF.vBCOutraUF     := StringToFloatDef( INIRec.ReadString('ICMSOutraUF','vBCOutraUF','') ,0);
            Imp.ICMS.ICMSOutraUF.pICMSOutraUF   := StringToFloatDef( INIRec.ReadString('ICMSOutraUF','pICMSOutraUF','') ,0);
            Imp.ICMS.ICMSOutraUF.vICMSOutraUF   := StringToFloatDef( INIRec.ReadString('ICMSOutraUF','vICMSOutraUF','') ,0);
          end;

          {indica se é simples}
          if INIRec.ReadInteger('ICMSSN', 'indSN',0) = 1 then
          begin
            imp.ICMS.SituTrib := cstICMSSN;
            Imp.ICMS.ICMSSN.indSN := INIRec.ReadInteger('ICMSSN', 'indSN',1);
          end;

          infCarga.vCarga   := StringToFloatDef( INIRec.ReadString('infCarga','vCarga','') ,0);
          infCarga.proPred  := INIRec.ReadString('infCarga','proPred','');
          infCarga.xOutCat  := INIRec.ReadString('infCarga','xOutCat','');

          I := 1 ;
          while true do
          begin
            sSecao    := 'infQ'+IntToStrZero(I,3) ;
            sCodPro   := INIRec.ReadString(sSecao,'cUnid','FIM') ;
            if sCodPro = 'FIM' then
               break ;
            with infCarga.infQ.Add do
            begin
              cUnid   := StrToUnidMed(OK, INIRec.ReadString(sSecao,'cUnid',''));
              tpMed   := INIRec.ReadString(sSecao,'tpMed','') ;
              qCarga  := StringToFloatDef( INIRec.ReadString(sSecao,'qCarga','') ,0);
            end;
            Inc(I);
          end;

          I := 1 ;
          while true do
          begin
            sSecao    := 'infSeg'+IntToStrZero(I,3) ;
            sCodPro   := INIRec.ReadString(sSecao,'respSeg','FIM') ;
            if sCodPro = 'FIM' then
               break ;
            with infSeg.Add do
            begin
              respSeg   := StrToTpRspSeguro(OK, INIRec.ReadString(sSecao,'respSeg',''));
              xSeg      := INIRec.ReadString(sSecao,'xSeg','') ;
              nApol     := INIRec.ReadString(sSecao,'nApol','') ;
              nAver     := INIRec.ReadString(sSecao,'nAver','');
              if INIRec.ReadString(sSecao,'vCarga','') <> '' then
                vCarga    := StringToFloatDef( INIRec.ReadString(sSecao,'vCarga','') ,0);
            end;
            Inc(I);
          end;

          if INIRec.ReadString('Rodo','RNTRC','') <> '' then
          begin
            Rodo.RNTRC := INIRec.ReadString('Rodo','RNTRC','');
            Rodo.dPrev := DFeUtil.StringToDate(INIRec.ReadString( 'Rodo','dPrev','0'));
            Rodo.Lota  := StrToTpLotacao(OK,INIRec.ReadString('Rodo','lota',''));
          end;

       end;
   finally
      INIRec.Free ;
   end;
  end;
end;

function GerarCTeIni( XML : WideString ) : WideString;
var
  I, J, K : Integer;
  sSecao,
  sCodPro : String;
  INIRec : TMemIniFile ;
  OK     : Boolean;
  IniCTe : TStringList;
  LocCTeR : TCTeR;
begin
 INIRec := TMemIniFile.create( 'cte.ini' ) ;
 frmAcbrNfeMonitor.ACBrCTe1.Conhecimentos.Clear;
 if FilesExists(XML) then
    frmAcbrNfeMonitor.ACBrCTe1.Conhecimentos.LoadFromFile(XML)
 else
  begin
    LocCTeR := TCTeR.Create(frmAcbrNfeMonitor.ACBrCTe1.Conhecimentos.Add.CTe);
    try
       LocCTeR.Leitor.Arquivo := ConvertStrRecived( XML );
       LocCTeR.LerXml;
       frmAcbrNfeMonitor.ACBrCTe1.Conhecimentos.Items[0].XML := LocCTeR.Leitor.Arquivo;
       frmAcbrNfeMonitor.ACBrCTe1.Conhecimentos.GerarCTe;
    finally
       LocCTeR.Free;
    end;
  end;

 with frmAcbrNfeMonitor do
  begin
   try
      with ACBrCTe1.Conhecimentos.Items[0].CTe do
       begin
          INIRec.WriteInteger('ide','cCT', Ide.cCT);
          INIRec.WriteInteger('ide','CFOP',Ide.CFOP        );
          INIRec.WriteString('ide','natOp',Ide.natOp       );
          INIRec.WriteString('ide','forPag',tpforPagToStr( Ide.forPag)       );
          INIRec.WriteString( 'ide','mod' ,Ide.modelo       );
          INIRec.WriteInteger( 'ide','serie'  ,Ide.serie        );
          INIRec.WriteInteger( 'ide','nCT' ,Ide.nCT         );
          INIRec.WriteString( 'ide','dhEmi',DateToStr( Ide.dhEmi       ));
          INIRec.WriteString( 'ide','tpImp',TpImpToStr(Ide.tpImp ));
          INIRec.WriteString( 'ide','tpemis',TpEmisToStr(Ide.tpEmis      ));
          INIRec.WriteString( 'ide','procEmi',procEmiToStr( Ide.procEmi  )   );
          INIRec.WriteString(  'ide','verProc' ,Ide.verProc       );
          INIRec.WriteString( 'ide','dhCont'  ,DateToStr(Ide.dhCont      ));
          INIRec.WriteString(  'ide','xJust' ,Ide.xJust        );
          INIRec.WriteString('ide','tpCTe',tpCTePagToStr( Ide.tpCTe       ));
          INIRec.WriteString('ide','refCTe',Ide.refCTe       );
          INIRec.WriteInteger('ide','cMunEnv', Ide.cMunEnv     );
          INIRec.WriteString('ide','xMunEnv',Ide.xMunEnv     );
          INIRec.WriteString('ide','UFEnv',Ide.UFEnv       );
          INIRec.WriteString('ide','modal', TpModalToStr( Ide.modal )      );
          INIRec.WriteString('ide','tpServ',TpServPagToStr( Ide.tpServ      ));
          INIRec.WriteInteger('ide','cMunIni',Ide.cMunIni    );
          INIRec.WriteString('ide','xMunIni',Ide.xMunIni     );
          INIRec.WriteString('ide','UFIni',Ide.UFIni       );
          INIRec.WriteInteger('ide','cMunFim',Ide.cMunFim    );
          INIRec.WriteString('ide','xMunFim',Ide.xMunFim     );
          INIRec.WriteString('ide','UFFim',Ide.UFFim       );
          INIRec.WriteString('ide','retira',TpRetiraPagToStr( Ide.retira     ));
          INIRec.WriteString('ide','xDetRetira',Ide.xDetRetira  );

          INIRec.WriteString('toma3','toma', TpTomadorToStr( Ide.toma03.Toma ));
          {
          Ide.toma4.CNPJCPF := INIRec.ReadString('toma4','CNPJCPF','');
          Ide.toma4.IE      := INIRec.ReadString('toma4','IE','');
          Ide.toma4.xNome   := INIRec.ReadString('toma4','xNome','');
          Ide.toma4.xFant   := INIRec.ReadString('toma4','xFant','');
          Ide.toma4.fone    := INIRec.ReadString('toma4','fone','');
            with Ide.toma4.enderToma do
            begin
              xLgr    := INIRec.ReadString('toma4','xLgr','');
              nro     := INIRec.ReadString('toma4','nro','');
              xCpl    := INIRec.ReadString('toma4','xCpl','');
              xBairro := INIRec.ReadString('toma4','xBairro','');
              cMun    := INIRec.ReadInteger('toma4','cMun',0);
              xMun    := INIRec.ReadString('toma4','xMun','');
              CEP     := INIRec.ReadInteger('toma4','CEP',0);
              UF      := INIRec.ReadString('toma4','UF','');
              if cMun <= 0 then
                cMun := ObterCodigoMunicipio(xMun,UF);
              cPais   := INIRec.ReadInteger('toma4','cPais',0);
              xPais   := INIRec.ReadString('toma4','xPais','');
            end;
          Ide.toma4.email   := INIRec.ReadString('toma4','email','');
          }
          INIRec.WriteString( 'ide','dhCont'  ,DateToStr( Ide.dhCont      ));
          INIRec.WriteString(  'ide','xJust' ,Ide.xJust      );

          INIRec.WriteString('compl','xCaracAd',compl.xCaracAd  );
          INIRec.WriteString('compl','xCaracSer',compl.xCaracSer  );
          INIRec.WriteString('compl','xEmi',compl.xEmi  );

          INIRec.WriteString('compl','tpPer', TpDataPeriodoToStr(  compl.Entrega.TipoData ));
          INIRec.WriteString('compl','tpHor', TpHorarioIntervaloToStr (compl.Entrega.TipoHora ));
          {ainda tem mais dados aqui}

          {...}
          INIRec.WriteString('compl','origCalc',compl.origCalc  );
          INIRec.WriteString('compl','destCalc',compl.destCalc  );
          INIRec.WriteString('compl','xObx',compl.xObs  );

          INIRec.WriteString('emit','CNPJ',Emit.CNPJ   );
          INIRec.WriteString('emit','IE',Emit.IE      );
          INIRec.WriteString('emit','xNome',Emit.xNome  );
          INIRec.WriteString('emit','xFant',Emit.xFant );

          INIRec.WriteString('emit','xLgr',Emit.enderEmit.xLgr     );
          INIRec.WriteString('emit','nro',Emit.enderEmit.nro      );
          INIRec.WriteString('emit', 'xCpl',Emit.enderEmit.xCpl     );
          INIRec.WriteString('emit','xBairro',Emit.enderEmit.xBairro  );
          INIRec.WriteInteger('emit','cMun',Emit.enderEmit.cMun     );
          INIRec.WriteString('emit','xMun',Emit.enderEmit.xMun     );
          INIRec.WriteInteger('emit','CEP',Emit.enderEmit.CEP      );
          INIRec.WriteString('emit','UF',Emit.enderEmit.UF       );

          INIRec.WriteString('emit','fone',Emit.enderEmit.fone     );

          INIRec.WriteInteger('ide','cUF', ide.cUF  );

          INIRec.WriteString('rem','CNPJCPF',Rem.CNPJCPF             );
          INIRec.WriteString('rem','IE',Rem.IE                  );
          INIRec.WriteString('rem','xNome',Rem.xNome                );
          INIRec.WriteString('rem','xFant',Rem.xFant               );
          INIRec.WriteString('rem','fone',Rem.fone                );

          INIRec.WriteString('rem','xLgr',Rem.enderReme.xLgr      );
          INIRec.WriteString('rem','nro',Rem.enderReme.nro       );
          INIRec.WriteString('rem','xCpl',Rem.enderReme.xCpl      );
          INIRec.WriteString('rem','xBairro',Rem.enderReme.xBairro   );
          INIRec.WriteInteger('rem','cMun',Rem.enderReme.cMun      );
          INIRec.WriteString('rem','xMun',Rem.enderReme.xMun      );
          INIRec.WriteInteger('rem','CEP',Rem.enderReme.CEP       );
          INIRec.WriteString('rem','UF',Rem.enderReme.UF        );
          INIRec.WriteInteger( 'rem','PaisCod'    ,Rem.enderReme.cPais      );
          INIRec.WriteString(  'rem','Pais'       ,Rem.enderReme.xPais      );
          INIRec.WriteString(  'rem','Email' ,Rem.email                );

           // abaixo não sei se tem ou não
          I := 1 ;
          while true do
          begin
            sSecao    := 'infNF'+IntToStrZero(I,3) ;
            sCodPro   := INIRec.ReadString(sSecao,'mod','FIM') ;
            if sCodPro = 'FIM' then
               break ;

            with Rem.infNF.Add do
            begin
//              nRoma   := INIRec.ReadString(sSecao,'nRoma','');
//              nPed    := INIRec.ReadString(sSecao,'nPed','');
              modelo  := StrToModeloNF(OK,INIRec.ReadString(sSecao,'mod','01'));
              serie   := INIRec.ReadString(sSecao,'serie','');
              nDoc    := INIRec.ReadString(sSecao,'nDoc','');
              dEmi    := DFeUtil.StringToDate(INIRec.ReadString( sSecao,'dEmi','0'));
//              vBC     := StringToFloatDef( INIRec.ReadString(sSecao,'vBC','') ,0);
//              vICMS   := StringToFloatDef( INIRec.ReadString(sSecao,'vICMS','') ,0);
//              vBCST   := StringToFloatDef( INIRec.ReadString(sSecao,'vBCST','') ,0);
//              vST     := StringToFloatDef( INIRec.ReadString(sSecao,'vST','') ,0);
//              vProd   := StringToFloatDef( INIRec.ReadString(sSecao,'vProd','') ,0);
//              vNF     := StringToFloatDef( INIRec.ReadString(sSecao,'vNF','') ,0);
              nCFOP   := INIRec.ReadInteger(sSecao,'nCFOP',0) ;
//              nPeso   := StringToFloatDef( INIRec.ReadString(sSecao,'nPeso','') ,0);
//              PIN     := INIRec.ReadString(sSecao,'PIN','');

//              locRet.CNPJCPF  := INIRec.ReadString(sSecao,'CNPJCPF','');
//              locRet.xNome    := INIRec.ReadString(sSecao,'xNome','');
//              locRet.xLgr     := INIRec.ReadString(sSecao,'xLgr','');
//              locRet.nro      := INIRec.ReadString(sSecao,'nro','');
//              locRet.xCpl     := INIRec.ReadString(sSecao,'xCpl','');
//              locRet.xBairro  := INIRec.ReadString(sSecao,'xBairro','');
//              locRet.cMun     := INIRec.ReadInteger(sSecao,'cMun',0);
//              locRet.xMun     := INIRec.ReadString(sSecao,'xMun','');
//              locRet.uf       := INIRec.ReadString(sSecao,'UF','');
//              if locRet.cMun <= 0 then
//                locRet.cMun := ObterCodigoMunicipio(locRet.xMun,locRet.UF);
            end;
            Inc(I);
          end;

          for i := 0 to Rem.infNFe.Count -1 do
          begin
            sSecao    := 'infNFe'+IntToStrZero(I+1,3) ;
            with Rem.infNFe.Items[i] do
            begin
              INIRec.WriteString(sSecao,'chave',chave );
              INIRec.WriteString(sSecao,'PIN',PIN   );
            end;
          end;

          INIRec.WriteString('Dest','CNPJCPF',Dest.CNPJCPF   );
          INIRec.WriteString('Dest','IE',Dest.IE       );
          INIRec.WriteString('Dest','xNome',Dest.xNome    );
          INIRec.WriteString('Dest','fone',Dest.fone     );

          INIRec.WriteString('Dest','xLgr',Dest.enderDest.xLgr     );
          INIRec.WriteString('Dest','nro',Dest.enderDest.nro      );
          INIRec.WriteString('Dest','xCpl',Dest.enderDest.xCpl   );
          INIRec.WriteString('Dest','xBairro',Dest.enderDest.xBairro );
          INIRec.WriteInteger('Dest','cMun',Dest.enderDest.cMun     );
          INIRec.WriteString('Dest','xMun',Dest.enderDest.xMun     );
          INIRec.WriteInteger('Dest', 'CEP',Dest.enderDest.CEP      );
          INIRec.WriteString('Dest','UF',Dest.enderDest.UF       );

          INIRec.WriteInteger('Dest','cPais',Dest.enderDest.cPais    );
          INIRec.WriteString('Dest', 'xPais', Dest.enderDest.xPais    );

          INIRec.WriteString('vPrest','vTPrest', CurrToStr( vPrest.vTPrest ));
          INIRec.WriteString('vPrest','vRec',CurrToStr( vPrest.vRec ));

          for i := 0 to vPrest.comp.Count - 1 do
          begin
            sSecao    := 'Comp'+IntToStrZero(I+1,3) ;
            with vPrest.comp.Items[i] do
            begin
              INIRec.WriteString(sSecao,'xNome',xNome );
              INIRec.WriteString(sSecao,'vComp',CurrToStr( vComp ));
            end;
          end;


          if Imp.ICMS.ICMS00.CST <> cst00 then
          begin
            INIRec.WriteString('ICMS00','CST',CSTICMSToStr(Imp.ICMS.ICMS00.CST));
            INIRec.WriteString('ICMS00','vBC',CurrToStr( Imp.ICMS.ICMS00.vBC ));
            INIRec.WriteString('ICMS00','pICMS',CurrToStr(Imp.ICMS.ICMS00.pICMS  ));
            INIRec.WriteString('ICMS00','vICMS',CurrToStr(Imp.ICMS.ICMS00.vICMS ));
          end;

          if Imp.ICMS.ICMS20.CST <> cst00 then
          begin
            INIRec.WriteString('ICMS20','CST',CSTICMSToStr(Imp.ICMS.ICMS20.CST )    );
            INIRec.WriteString('ICMS20','pRedBC',CurrToStr(Imp.ICMS.ICMS20.pRedBC  ));
            INIRec.WriteString('ICMS20','vBC',CurrToStr(Imp.ICMS.ICMS20.vBC     ));
            INIRec.WriteString('ICMS20','pICMS',CurrToStr(Imp.ICMS.ICMS20.pICMS   ));
            INIRec.WriteString('ICMS20','vICMS',CurrToStr(Imp.ICMS.ICMS20.vICMS   ));
          end;

          if Imp.ICMS.ICMS45.CST <> cst00 then
            INIRec.WriteString('ICMS45','CST',CSTICMSToStr(Imp.ICMS.ICMS45.CST ));

          if Imp.ICMS.ICMS60.CST <> cst00 then
          begin
            INIRec.WriteString('ICMS60','CST',CSTICMSToStr(Imp.ICMS.ICMS60.CST     ));
            INIRec.WriteString('ICMS60','vBCSTRet',CurrToStr(Imp.ICMS.ICMS60.vBCSTRet ));
            INIRec.WriteString('ICMS60','vICMSSTRet',CurrToStr(Imp.ICMS.ICMS60.vICMSSTRet ));
            INIRec.WriteString('ICMS60','pICMSSTRet',CurrToStr(Imp.ICMS.ICMS60.pICMSSTRet ));
            INIRec.WriteString('ICMS60','vCred',CurrToStr(Imp.ICMS.ICMS60.vCred ));
          end;

          if Imp.ICMS.ICMS90.CST <> cst00 then
          begin
            INIRec.WriteString('ICMS90','CST',CSTICMSToStr(Imp.ICMS.ICMS90.CST     ));
            INIRec.WriteString('ICMS90','pRedBC',CurrToStr(Imp.ICMS.ICMS90.pRedBC));
            INIRec.WriteString('ICMS90','vBC',CurrToStr(Imp.ICMS.ICMS90.vBC ));
            INIRec.WriteString('ICMS90','pICMS',CurrToStr(Imp.ICMS.ICMS90.pICMS ));
            INIRec.WriteString('ICMS90','vICMS',CurrToStr(Imp.ICMS.ICMS90.vICMS ));
            INIRec.WriteString('ICMS90','vCred',CurrToStr(Imp.ICMS.ICMS90.vCred ) );
          end;

          if Imp.ICMS.ICMSOutraUF.CST <> cst00 then
          begin
            INIRec.WriteString('ICMSOutraUF','CST',CSTICMSToStr(Imp.ICMS.ICMSOutraUF.CST     ));
            INIRec.WriteString('ICMSOutraUF','pRedBCOutraUF',CurrToStr(Imp.ICMS.ICMSOutraUF.pRedBCOutraUF));
            INIRec.WriteString('ICMSOutraUF','vBCOutraUF',CurrToStr(Imp.ICMS.ICMSOutraUF.vBCOutraUF ));
            INIRec.WriteString('ICMSOutraUF','pICMSOutraUF',CurrToStr(Imp.ICMS.ICMSOutraUF.pICMSOutraUF ));
            INIRec.WriteString('ICMSOutraUF','vICMSOutraUF',CurrToStr(Imp.ICMS.ICMSOutraUF.vICMSOutraUF ));
          end;

          {indica se é simples}
          if (Imp.ICMS.ICMSSN.indSN = 1) and (Imp.ICMS.SituTrib = cstICMSSN) then
            INIRec.WriteInteger('ICMSSN', 'indSN',Imp.ICMS.ICMSSN.indSN );

          INIRec.WriteString('infCarga','vCarga',CurrToStr(infCarga.vCarga  ));
          INIRec.WriteString('infCarga','proPred',infCarga.proPred  );
          INIRec.WriteString('infCarga','xOutCat',infCarga.xOutCat  );

          for i := 0 to infCarga.infQ.Count -1 do
          begin
            sSecao    := 'infQ'+IntToStrZero(I+1,3) ;

            with infCarga.infQ.Items[i] do
            begin
              INIRec.WriteString(sSecao,'cUnid', UnidMedToStr( cUnid  ));
              INIRec.WriteString(sSecao,'tpMed',tpMed   ) ;
              INIRec.WriteString(sSecao,'qCarga',CurrToStr(qCarga  ));
            end;
          end;

          for i:= 0 to infSeg.Count - 1 do
          begin
            sSecao    := 'infSeg'+IntToStrZero(I+1,3) ;

            with infSeg.Items[i] do
            begin
              INIRec.WriteString(sSecao,'respSeg',TpRspSeguroToStr( respSeg   ));
              INIRec.WriteString(sSecao,'xSeg',xSeg      ) ;
              INIRec.WriteString(sSecao,'nApol',nApol     ) ;
              INIRec.WriteString(sSecao,'nAver',nAver     );

              INIRec.WriteString(sSecao,'vCarga',CurrToStr(vCarga ));
            end;
          end;

          if Rodo.RNTRC <> '' then
          begin
            INIRec.WriteString('Rodo','RNTRC',Rodo.RNTRC );
            INIRec.WriteString( 'Rodo','dPrev',DateToStr(Rodo.dPrev ));
            INIRec.WriteString('Rodo','lota',TpLotacaoToStr( Rodo.Lota ));
          end;
       end;
   finally
      IniCTe := TStringList.Create;
      INIRec.GetStrings(IniCTe);
      INIRec.Free ;
      Result := StringReplace(IniCTe.Text,sLineBreak+sLineBreak,sLineBreak,[rfReplaceAll]);
      IniCTe.Free;
   end;

  end;

end;

end.

