{******************************************************************************}
{ Projeto: Componente ACBrNFe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - NFe - http://www.nfe.fazenda.gov.br                          }
{                                                                              }
{ Direitos Autorais Reservados (c) 2008 Wemerson Souto                         }
{                                       Daniel Simoes de Almeida               }
{                                       Andr� Ferreira de Moraes               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do Projeto ACBr     }
{ Componentes localizado em http://www.sourceforge.net/projects/acbr           }
{                                                                              }
{                                                                              }
{  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la }
{ sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) }
{ qualquer vers�o posterior.                                                   }
{                                                                              }
{  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      }
{ ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto}
{ com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  }
{ no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Voc� tamb�m pode obter uma copia da licen�a em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 04/10/2012: Andr� Ferreira de Moraes
|*  - Impress�o de Eventos da NFe baseado em exemplo disponibilizado no f�rum
******************************************************************************}
{$I ACBr.inc}
unit ACBrDANFeEventoRaveRetrato;

interface

uses Graphics, Forms, Windows, SysUtils, Classes,
     Variants, DBClient, Math, StdCtrls, DB, Dialogs,
     Controls, ExtCtrls, Mask, MaskUtils,
     {$IFNDEF COMPILER16} JPEG, {$ELSE} Vcl.Imaging.jpeg, {$ENDIF}
     RpDefine, RpBase, RpSystem, RpBars, RpMemo,
     RpRenderText, RpRenderRTF, RpRenderHTML, RpRender, RpRenderPDF,
     ACBrNFe, pcnConversao, ACBrDANFeCBRave, ACBrNFeUtil, ACBrDFeUtil;

const aHeigthPadrao:Double=5.8;

procedure ImprimirEventoRetrato(aRaveSystem:TEventoRave);

implementation

uses pcnEnvEventoNFe;

procedure ImprimirEventoRetrato(aRaveSystem:TEventoRave);
var
 PosX, PosY, PosBarra : Double;
 aWidth, CenterX, YY : Double;
 FMemo : TMemoBuf;
begin
  EventoRave:=aRaveSystem;

  // marca d'agua de homologa��o ***********************************************
  with EventoRave, EventoRave.BaseReport, EventoRave.ACBrNFe.EventoNFe.Evento.Items[EventoRave.FNFIndex] do
  begin
    if ( InfEvento.tpAmb = taHomologacao ) then
     begin
        YY :=FLastY-15;
        SetFont(FontNameUsed,25);
        FontColor:=clSilver;
        Bold:=True;
        Underline:=True;
        if InfEvento.tpEvento <> teCCe then
         begin
           GotoXY(FFirstX+5,YY);
           FontRotation:=30;
         end
        else
         begin
           GotoXY(FFirstX+10,YY);
           FontRotation:=45;
         end;
        Print('AMBIENTE DE HOMOLOGA��O - SEM VALOR FISCAL');
     end;

     PosX := 10;
     PosY := 10;
     aWidth := 190;

     Box([],PosX,PosY,aWidth,18,'');
     SetFont(FontNameUsed,12);
     CenterX:=PosX+(aWidth/2);
     NewLine;
     Bold:=True;
     PrintCenter(InfEvento.DescricaoTipoEvento(InfEvento.tpEvento),CenterX);
     SetFont(FontNameUsed,10);
     NewLine;
     Bold:=False;
     PrintCenter('N�o possui valor fiscal, simples representa��o do evento indicado abaixo.',CenterX);
     NewLine;
     PrintCenter('CONSULTE A AUTENTICIDADE NO SITE DA SEFAZ AUTORIZADORA.',CenterX);
     NewLine;
     NewLine;
     NewLine;
     SetFont(FontNameUsed,12);
     Bold:=True;
     PrintLeft('NOTA FISCAL ELETR�NICA - NF-e',PosX);
     PosY := YPos + 2;
     PosX := 10;
     Bold:=False;
     SetFont(FontNameUsed,10);
     Box([],PosX,PosY, 12,aHeigthPadrao,'MODELO', copy(InfEvento.chNFe,21,2),taRightJustify);
     Box([],XPos,YPos, 12,aHeigthPadrao,'S�RIE', copy(InfEvento.chNFe,23,3),taRightJustify);
     Box([],XPos,YPos, 43,aHeigthPadrao,'N�MERO',copy(InfEvento.chNFe,26,9),taRightJustify);
     Box([],XPos,YPos, 43,aHeigthPadrao,'M�S/ANO DA EMISS�O',copy(InfEvento.chNFe,05,2)+'/'+copy(InfEvento.chNFe,03,2),taRightJustify);

     PosBarra := XPos+41;
     Box([],XPos,PosY, 80,aHeigthPadrao*2) ;
     with TRPBarsCode128.Create(EventoRave.BaseReport) do
      begin
        BaseReport:=EventoRave.BaseReport;
        CodePage:=cpCodeC;
        BarCodeJustify:=pjCenter;
        UseChecksum:=false;
        BarWidth:=0.254;
        BarHeight:=10.0;
        WideFactor:=BarWidth;
        PrintReadable:=False;
        Text:=DFeUtil.LimpaNumero(InfEvento.chNFe);
        PrintXY(PosBarra,PosY+1);
        Free;
     end;

     PosY := YPos + aHeigthPadrao;
     PosX := 10;
     if EventoRave.FontNameUsed = 'Courier New' then
        Box([],PosX,PosY,109.8,aHeigthPadrao,'CHAVE DE ACESSO',InfEvento.chNFe,taRightJustify)
     else
     Box([],PosX,PosY,109.8,aHeigthPadrao,'CHAVE DE ACESSO',NotaUtil.FormatarChaveAcesso(InfEvento.chNFe),taRightJustify);
     NewLine;
     NewLine;
     NewLine;
     PosX := 10;
     SetFont(FontNameUsed,12);
     Bold:=True;
     PrintLeft(InfEvento.DescricaoTipoEvento(InfEvento.tpEvento),PosX);
     PosY := YPos + 2;
     SetFont(FontNameUsed,10);
     Bold:=False;
     Box([],PosX,PosY, 12,aHeigthPadrao,'ORG�O', IntToStr(InfEvento.cOrgao),taLeftJustify);
     if (InfEvento.tpAmb = taProducao) then
        Box([],XPos,YPos, 132,aHeigthPadrao,'AMBIENTE', 'PRODU��O',taLeftJustify)
     else
        Box([],XPos,YPos, 132,aHeigthPadrao,'AMBIENTE', 'HOMOLOGA��O - SEM VALOR FISCAL',taLeftJustify);
     Box([],XPos,YPos, 46,aHeigthPadrao,'DATA E HOR�RIO DO EVENTO',DateTimeToStr(InfEvento.dhEvento),taRightJustify);
     PosY := YPos + aHeigthPadrao;
     Box([],PosX,PosY, 18,aHeigthPadrao,'EVENTO', InfEvento.TipoEvento,taRightJustify);
     Box([],XPos,YPos,110,aHeigthPadrao,'DESCRI��O DO EVENTO', InfEvento.DescEvento,taLeftJustify);
     Box([],XPos,YPos, 32,aHeigthPadrao,'SEQU�NCIA DO EVENTO', IntToStr(InfEvento.nSeqEvento),taRightJustify);
     Box([],XPos,YPos, 30,aHeigthPadrao,'VERS�O DO EVENTO', InfEvento.versaoEvento,taRightJustify);
     PosY := YPos + aHeigthPadrao;
     Box([],PosX,PosY,110,aHeigthPadrao,'STATUS', IntToStr(RetInfEvento.cStat)+' - '+RetInfEvento.xMotivo,taLeftJustify);
     Box([],XPos,YPos, 38,aHeigthPadrao,'PROTOCOLO', RetInfEvento.nProt,taLeftJustify);
     Box([],XPos,YPos, 42,aHeigthPadrao,'DATA E HOR�RIO DO REGISTRO',DateTimeToStr(RetInfEvento.dhRegEvento),taRightJustify);

     if InfEvento.tpEvento <> teCCe then
      begin
        PosY := YPos + aHeigthPadrao;
        Box([],PosX,PosY,190,aHeigthPadrao,'JUSTIFICATIVA', InfEvento.detEvento.xJust,taLeftJustify);
      end;

     if EventoRave.ACBrNFe.NotasFiscais.Count > 0 then
      begin
        if EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Emit.CNPJCPF = InfEvento.CNPJ then
         begin
           NewLine;
           NewLine;
           NewLine;
           PosX := 10;
           SetFont(FontNameUsed,12);
           Bold:=True;
           PrintLeft('EMITENTE',PosX);
           PosY := YPos + 2;
           SetFont(FontNameUsed,10);
           Bold:=False;
           Box([],PosX,PosY,148,aHeigthPadrao,'NOME / RAZ�O SOCIAL', EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Emit.xNome,taLeftJustify);
           Box([],XPos,YPos, 42,aHeigthPadrao,'CNPJ / CPF', DFeUtil.FormatarCNPJ(EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Emit.CNPJCPF),taRightJustify);
           PosY := YPos + aHeigthPadrao;
           Box([],PosX,PosY, 98,aHeigthPadrao,'ENDERE�O', EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Emit.EnderEmit.xLgr,taLeftJustify);
           Box([],XPos,YPos, 70,aHeigthPadrao,'BAIRRO', EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Emit.EnderEmit.xBairro,taLeftJustify);
           Box([],XPos,YPos, 22,aHeigthPadrao,'CEP', NotaUtil.FormatarCEP(DFeUtil.Poem_Zeros(EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Emit.EnderEmit.CEP,8)),taRightJustify);
           PosY := YPos + aHeigthPadrao;
           Box([],PosX,PosY, 88,aHeigthPadrao,'MUNIC�PIO', EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Emit.EnderEmit.xMun,taLeftJustify);
           Box([],XPos,YPos, 49,aHeigthPadrao,'FONE/FAX', DFeUtil.FormatarFone(EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Emit.EnderEmit.fone),taLeftJustify);
           Box([],XPos,YPos, 11,aHeigthPadrao,'ESTADO', EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Emit.EnderEmit.UF,taLeftJustify);
           Box([],XPos,YPos, 42,aHeigthPadrao,'INSCRI��O ESTADUAL', EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Emit.IE,taLeftJustify);
         end;

        if (EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.infNFe.ID = InfEvento.chNFe) or
           (StringReplace(UpperCase(EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.infNFe.ID),'NFE', '', [rfReplaceAll]) = InfEvento.chNFe) then
         begin
           NewLine;
           NewLine;
           NewLine;
           PosX := 10;
           SetFont(FontNameUsed,12);
           Bold:=True;
           PrintLeft('DESTINAT�RIO / REMETENTE',PosX);
           PosY := YPos + 2;
           SetFont(FontNameUsed,10);
           Bold:=False;
           Box([],PosX,PosY,148,aHeigthPadrao,'NOME / RAZ�O SOCIAL', EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Dest.xNome,taLeftJustify);
           if Length(Trim(EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Dest.CNPJCPF)) > 11 then
              Box([],XPos,YPos, 42,aHeigthPadrao,'CNPJ / CPF', DFeUtil.FormatarCNPJ(EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Dest.CNPJCPF),taRightJustify)
           else
              Box([],XPos,YPos, 42,aHeigthPadrao,'CNPJ / CPF', DFeUtil.FormatarCPF(EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Dest.CNPJCPF),taRightJustify);
           PosY := YPos + aHeigthPadrao;
           Box([],PosX,PosY, 98,aHeigthPadrao,'ENDERE�O', EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Dest.EnderDest.xLgr,taLeftJustify);
           Box([],XPos,YPos, 70,aHeigthPadrao,'BAIRRO', EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Dest.EnderDest.xBairro,taLeftJustify);
           Box([],XPos,YPos, 22,aHeigthPadrao,'CEP', NotaUtil.FormatarCEP(DFeUtil.Poem_Zeros(EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Dest.EnderDest.CEP,8)),taRightJustify);
           PosY := YPos + aHeigthPadrao;
           Box([],PosX,PosY, 88,aHeigthPadrao,'MUNIC�PIO', EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Dest.EnderDest.xMun,taLeftJustify);
           Box([],XPos,YPos, 49,aHeigthPadrao,'FONE/FAX', DFeUtil.FormatarFone(EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Dest.EnderDest.fone),taLeftJustify);
           Box([],XPos,YPos, 11,aHeigthPadrao,'ESTADO', EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Dest.EnderDest.UF,taLeftJustify);
           Box([],XPos,YPos, 42,aHeigthPadrao,'INSCRI��O ESTADUAL', EventoRave.ACBrNFe.NotasFiscais.Items[0].NFe.Dest.IE,taLeftJustify);
         end;
      end;

     if InfEvento.tpEvento = teCCe then
      begin
        NewLine;
        NewLine;
        NewLine;
        PosX := 10;
        SetFont(FontNameUsed,12);
        Bold:=True;
        PrintLeft('CONDI��ES DE USO',PosX);
        PosY := YPos + 2;
        SetFont(FontNameUsed,10);
        Bold:=False;
        NewLine;
        NewLine;
        Box([],PosX,PosY,aWidth,40,'');
        NewLine;
        FMemo := TMemoBuf.Create;
        FMemo.Text := StringReplace(InfEvento.detEvento.xCondUso,'com: I','com:'+#13+' I',[rfReplaceAll]);
        FMemo.Text := StringReplace(FMemo.Text,';',';'+#13,[rfReplaceAll]);
        FMemo.BaseReport:=BaseReport;
        FMemo.PrintStart:=12;
        FMemo.PrintEnd:=188;
        FMemo.NoNewLine:=True;
        FMemo.PrintLines(8,False);
        FMemo.Free;

        NewLine;
        NewLine;
        NewLine;
        PosX := 10;
        SetFont(FontNameUsed,12);
        Bold:=True;
        PrintLeft('CORRE��O',PosX);
        PosY := YPos + 2;
        SetFont(FontNameUsed,10);
        Bold:=False;
        NewLine;
        NewLine;
        Box([],PosX,PosY,aWidth,(FLastY - YPos),'');
        NewLine;
        FMemo := TMemoBuf.Create;
        FMemo.Text := StringReplace(InfEvento.detEvento.xCorrecao,';',#13,[rfReplaceAll]);
        FMemo.BaseReport:=BaseReport;
        FMemo.PrintStart:=12;
        FMemo.PrintEnd:=188;
        FMemo.NoNewLine:=True;
        FMemo.PrintLines(10,False);
        FMemo.Free;
      end;
      
     SetFontTitle;
     PrintXY(PosX,FLastY,'DATA E HORA DA IMPRESS�O: '+FormatDateTime('dd/mm/yyyy hh:mm:ss',Now)+DFeUtil.SeSenao((Trim(NomeDoUsuario)<>''),' - '+NomeDoUsuario,''));
     if Trim(NomeDoERP)>'' then
      begin
        PrintRight('Desenvolvido por '+NomeDoERP,FLastX-5);
      end;
  end;

end;


end.

