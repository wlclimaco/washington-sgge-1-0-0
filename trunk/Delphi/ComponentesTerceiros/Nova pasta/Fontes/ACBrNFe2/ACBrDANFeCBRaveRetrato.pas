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
|* 16/12/2008: Wemerson Souto
|*  - Doa��o do componente para o Projeto ACBr
|* 20/08/2009: Jo�o Paulo
|*  - Doa��o units para gera��o do Danfe via c�digo usando Rave
******************************************************************************}
{$I ACBr.inc}
unit ACBrDANFeCBRaveRetrato;

interface

uses Graphics, Forms, Windows, SysUtils, Classes,
     Variants, DBClient, Math, StdCtrls, DB, Dialogs,
     Controls, ExtCtrls, Mask, MaskUtils,
     {$IFNDEF COMPILER16} JPEG, {$ELSE} Vcl.Imaging.jpeg, {$ENDIF}
     RpDefine, RpBase, RpSystem, RpBars, RpMemo,
     RpRenderText, RpRenderRTF, RpRenderHTML, RpRender, RpRenderPDF,
     ACBrNFe, pcnConversao, ACBrDANFeCBRave;

const
      FontSizeGroup:Integer=7;
      FontSizeTitle:Integer=6;
//      FontSizeText:Integer=8;

var
   FontSizeText:double;
   ColsWidth:array[1..17] of Double;

procedure ImprimirRetrato(aRaveSystem:TDANFeRave);

implementation

uses ACBrNFeUtil, ACBrDFeUtil, StrUtils, pcnNFe;

function ImprimirCanhoto(PosX,PosY:Double):Double;
var aWidthOutros,aWidthNFe,
    aWidthData,
    aHeigth,aHeigthReceb: Double;
    vEnd: string;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     aWidthNFe:=35;
     aHeigth:=18;
     aHeigthReceb:=8;
     aWidthOutros:=FLastX-FFirstX-aWidthNFe;
     aWidthData:=40;
     if FPageNum=1 then
      begin
        if (not FormularioContinuo) then
        begin
          Box([],FLastX-aWidthNFe,PosY,aWidthNFe,aHeigth);
          Box([fsRigth],PosX,PosY,aWidthOutros,aHeigthReceb,'','',taLeftJustify,True,False,False);
          Box([fsTop],PosX,YPos,aWidthData,aHeigth-aHeigthReceb,'DATA DE RECEBIMENTO','');
          Box([fsTop,fsRigth],XPos,YPos,aWidthOutros-aWidthData,aHeigth-aHeigthReceb,'IDENTIFICA��O E ASSINATURA DO RECEBEDOR','');

          SetFont(FontNameUsed,8);
          Bold:=True;
          GotoXY(0,PosY+GetFontHeigh);
          //NewLine;
          vEnd:='Recebemos de '+Emit.XNome+' os produtos / servi�os constantes da Nota Fiscal indicada ao lado';
          if FontNameUsed = 'Courier New' then
          begin
             if Length(vEnd)>96 then
             begin
                vEnd:='Recebemos de '+Emit.XNome;
                PrintCenter(vEnd,PosX+(aWidthOutros/2));
                NewLine;
                vEnd:='os produtos / servi�os constantes da Nota Fiscal indicada ao lado';
                PrintCenter(vEnd,PosX+(aWidthOutros/2));
             end
             else
             begin
                PrintCenter(vEnd,PosX+(aWidthOutros/2));
                NewLine;
                if ExibirResumoCanhoto then
                begin
                   if DFeUtil.EstaVazio(ExibirResumoCanhoto_Texto) then
                      PrintCenter('Emiss�o: '+DFeUtil.FormatDate(DateToStr(Ide.DEmi))+'  Dest/Reme: '+Dest.XNome+'  Valor Total: '+DFeUtil.FormatFloat(Total.ICMSTot.VNF),PosX+(aWidthOutros/2))
                   else
                      PrintCenter(ExibirResumoCanhoto_Texto,PosX+(aWidthOutros/2));
                end;
             end;
          end
          else
          begin
             if Length(vEnd)>110 then
             begin
                vEnd:='Recebemos de '+Emit.XNome;
                PrintCenter(vEnd,PosX+(aWidthOutros/2));
                NewLine;
                vEnd:='os produtos / servi�os constantes da Nota Fiscal indicada ao lado';
                PrintCenter(vEnd,PosX+(aWidthOutros/2));
             end
             else
             begin
                PrintCenter(vEnd,PosX+(aWidthOutros/2));
                NewLine;
                if ExibirResumoCanhoto then
                   PrintCenter('Emiss�o: '+DFeUtil.FormatDate(DateToStr(Ide.DEmi))+'  Dest/Reme: '+Dest.XNome+'  Valor Total: '+DFeUtil.FormatFloat(Total.ICMSTot.VNF),PosX+(aWidthOutros/2));
             end;
          end;
        end;

        SetFont(FontNameUsed,8);
        Bold:=True;
        GotoXY(0,PosY+GetFontHeigh);
        NewLine;
        PrintCenter('NF-e',FLastX-aWidthNFe+(aWidthNFe/2));
        NewLine;
        PrintCenter('N�: '+FNumeroNF,FLastX-aWidthNFe+(aWidthNFe/2));
        NewLine;
        PrintCenter('S�RIE: '+FSerie,FLastX-aWidthNFe+(aWidthNFe/2));

        if (not FormularioContinuo) then
        begin
          SetPen(FColorBorders,psDot,EspessuraBorda,pmCopy);
          MoveTo(PosX,PosY+aHeigth+3);
          LineTo(FLastX,PosY+aHeigth+3);
          SetPen(FColorBorders,psSolid,EspessuraBorda,pmCopy);
        end;
      end;

     Result:=PosY+aHeigth+6;
  end;
end;

procedure ImprimirMensagensDeFundo;
var CenterX,YY:Double;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     YY:=FLastY-5;
     if Ide.TpAmb=taHomologacao then
      begin //homologa��o
        SetFont(FontNameUsed,25);
        FontColor:=clSilver;
        Bold:=True;
        Underline:=True;
        GotoXY(FFirstX+5,YY);
        FontRotation:=45;
        Print('AMBIENTE DE HOMOLOGA��O - SEM VALOR FISCAL');
      end
     else if ((procNFe.cStat in [101,151]) or (NFeCancelada)) then
      begin //NFe Cancelada
        SetFont(FontNameUsed,25);
        FontColor:=clRed;
        Bold:=True;
        Underline:=True;
        GotoXY(FFirstX+80,YY-80);
        FontRotation:=45;
        Print('NFe Cancelada');
      end
     else if (procNFe.cStat = 110) then
      begin //NFe denegada
        SetFont(FontNameUsed,25);
        FontColor:=clRed;
        Bold:=True;
        Underline:=True;
        GotoXY(FFirstX+80,YY-80);
        FontRotation:=45;
        Print('NFe Denegada');
      end
     else if ((procNFe.cStat <> 100 ) and
              (Ide.tpEmis <> teFSDA) and
              (Ide.tpEmis <> teCONTINGENCIA) and
              (Length(Trim(ProtocoloNFe)) < 15)) then
      begin //N�o autorizada
        SetFont(FontNameUsed,25);
        FontColor:=clRed;
        Bold:=True;
        Underline:=True;
        GotoXY(FFirstX+5,YY);
        FontRotation:=45;
        if procNFe.cStat <= 0 then
           Print('NFe n�o autorizada pela SEFAZ(SEM VALIDADE FISCAL)')
        else
           Print(IntToStr(procNFe.cStat) + '-' +procNFe.xMotivo);
      end;

     SetFont(FontNameUsed,22);
     FontColor:=clSilver;
     Bold:=True;
     GotoXY(FFirstX+5,YY-10);
     CenterX:=XPos+((PageWidth-MarginRight-XPos)/2);
     case Ide.tpEmis of
        teDPEC: begin
                  PrintCenter('DANFE impresso em conting�ncia - DPEC regularmente ',CenterX);
                  NewLine;
                  PrintCenter('recebida pela Receita Federal do Brasil',CenterX);
                end;
        teFSDA,
        teContingencia,
        teSCAN:
                begin
                  PrintCenter('DANFE em Conting�ncia - impresso em',CenterX);
                  NewLine;
                  PrintCenter('decorr�ncia de problemas t�cnicos',CenterX);
                end;
     end;
    FontRotation:=0;
  end;
end;

function ImprimirEmitente(PosX,PosY:Double):Double;
   //fun��o para dividir texto - Cr�ditos: Luis
   function DivideTexto( var vTexto:string; aMax:integer ):string;
   var
    vPalavra,vRetorno:string;
   begin
    vRetorno := '';
    while length(vTexto)>aMax do
    begin
       vPalavra := RightStr(vTexto,1);
       vTexto   := Copy( vTexto, 1, Length(vTexto)-1 );
       while ( length(vTexto) > 0 ) and ( RightStr(vTexto,1) <> ' ' ) and ( RightStr(vTexto,1) <> '.' ) do
       begin
          vPalavra := RightStr(vTexto,1)+vPalavra;
          vTexto   := Copy( vTexto, 1, Length(vTexto)-1 );
       end;
       vRetorno := vPalavra + vRetorno;
    end;
    if length(vRetorno)>aMax then vRetorno := Copy( vRetorno, 1, aMax );
    Result := vRetorno;
   end;
var aHeigthLogo, aWidthLogo, aWidth, CenterX:Double;
    aWidthTexto: integer;
    stLogo:TMemoryStream;
    vEnd,vTemp:String;
    vDuasLinhas: boolean;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
    stLogo:=TMemoryStream.Create;
    try
       if LogoMarca<>nil then
          LogoMarca.SaveToStream(stLogo);
       stLogo.Position:=0;
       aWidth:=85;
       Result:=PosX+aWidth;
       if (FormularioContinuo) then
          exit;

       Box([],PosX,PosY,aWidth,30,'IDENTIFICA��O DO EMITENTE');

       if (ExpandirLogoMarca) and (Assigned(LogoMarca)) then
       begin
         PrintImageRect(PosX+1,PosY+3,PosX+aWidth-1,PosY+29,stLogo,'JPG');
       end
       else begin
         GotoXY(PosX,PosY+2);
         CenterX:=PosX+(aWidth/2);
         SetFont(FontNameUsed,FontSizeEmit_Nome);
         NewLine;
         Bold:=True;
         vEnd:=Emit.XNome;
         vDuasLinhas:=false;
         if length(vEnd)>30 then
         begin
           vtemp := DivideTexto(vEnd,30);
           vDuasLinhas:=true;
         end
         else
           vTemp:='';
         PrintCenter(vEnd,CenterX);
         if Length(Vtemp)>0 then
         begin
            NewLine;
            PrintCenter(vTemp,CenterX);
         end;
         GotoXY(PosX,YPos+2);

         aWidthLogo:=0;
         aWidthTexto:=48;
         if Assigned(LogoMarca) then
         begin
           if vDuasLinhas then
           begin
              aWidthLogo:=26-5;
              aHeigthLogo:=20-5
           end
           else
           begin
              aWidthLogo:=26;
              aHeigthLogo:=20;
           end;
           aWidthTexto:=38;
           PrintImageRect(PosX+1,YPos,PosX+aWidthLogo,YPos+aHeigthLogo,stLogo,'JPG');
         end;
         GotoXY(PosX,YPos+1.5);
         CenterX:=PosX+aWidthLogo+((aWidth-aWidthLogo)/2);
         SetFont(FontNameUsed,FontSizeEmit_Outros);
         Bold:=True;
         with Emit.EnderEmit do
          begin
           vEnd:=XLgr;
           if (Trim(Nro)>'') and (Nro<>'SN') then
              vEnd:=vEnd+' '+Nro;
           if Trim(XCpl)>'' then
              vEnd:=vEnd+', '+XCpl;
           if length(vEnd)>aWidthTexto then
              vtemp := DivideTexto(vEnd,aWidthTexto)
           else
              vTemp:='';
           PrintCenter(vEnd,CenterX);
           NewLine;
           vEnd:=vTemp+XBairro+' - '+NotaUtil.FormatarCEP(DFeUtil.Poem_Zeros(CEP,8));
           PrintCenter(vEnd,CenterX);
           NewLine;
           vEnd:=XMun+' - '+UF;
           PrintCenter(vEnd,CenterX);
           NewLine;
           vEnd:='FONE: '+NotaUtil.FormatarFone(Fone);
           if trim(FaxDoEmitente)>'' then
              vEnd:=vEnd+' / FAX: '+NotaUtil.FormatarFone(FaxDoEmitente);
           PrintCenter(vEnd,CenterX);
           NewLine;
           if vDuasLinhas then
           begin
              vEnd:=SiteDoEmitente+' - '+EmailDoEmitente;
              PrintCenter(vEnd,CenterX);
           end
           else
           begin
              vEnd:=SiteDoEmitente;
              PrintCenter(vEnd,CenterX);
              NewLine;
              vEnd:=EmailDoEmitente;
              PrintCenter(vEnd,CenterX);
           end;
           Bold:=False;
          end;
       end;
    finally
      FreeAndNil(stLogo);
    end;
  end;
end;

function ImprimirTituloDANFe(PosX, PosY: Double):Double;
var aWidth, CenterX:Double;
    VarNumPage:String;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     if FontNameUsed = 'Courier New' then
        aWidth:=37
     else
        aWidth:=36;
     Result:=PosX+aWidth;
     Box([fsLeft],PosX,PosY,aWidth,30);
     CenterX:=PosX+(aWidth/2);
     GotoXY(PosX,PosY);
     SetFont(FontNameUsed,FontSizeIdentDoc_DANFE);
     Bold:=True;
     NewLine;
     GotoXY(PosX,YPos-0.4);
     if (not FormularioContinuo) then
       PrintCenter('DANFE',CenterX);
     SetFont(FontNameUsed,FontSizeIdentDoc_TipoOperacao);
     NewLine;
     if (not FormularioContinuo) then
       PrintCenter('Documento Auxiliar da',CenterX);
     NewLine;
     if (not FormularioContinuo) then
       PrintCenter('Nota Fiscal Eletr�nica',CenterX);
     NewLine;
     if (not FormularioContinuo) then
       PrintXY(PosX+10,YPos+0.5,'0 - ENTRADA');
     NewLine;
     if (not FormularioContinuo) then
       PrintXY(PosX+10,YPos+0.5,'1 - SA�DA');
     Box([],PosX+30,PosY+14.5,4,4);
     SetFont(FontNameUsed,FontSizeIdentDoc_Outros);
     Bold:=True;
     PrintXY(PosX+31,PosY+17.8,DFeUtil.SeSenao(Ide.TpNF=tnEntrada,'0','1'));
     GotoXY(PosX,PosY+20.4);
     NewLine;
     PrintCenter('N.� '+FNumeroNF,CenterX);
     NewLine;

     VarNumPage:='PAGE'+FormatFloat('000000',FCurrentPage);

     PrintCenter('S�RIE '+IntToStr(Ide.Serie)+'-FOLHA '+PIVar(VarNumPage),CenterX);
     Bold:=False;
  end;
end;

function ImprimirCodigoBarras(PosX, PosY: Double):Double;
var PosYCodBarraContigencia, aWidth, CenterX:Double;
    w1,w2,w3,aTituloChave, aChaveAcesso, aProtocolo, aChaveContigencia:String;
    wTemp_FontSizeText: double;
begin
   with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
      aWidth:=FLastX-PosX;
      Box([fsLeft],PosX,PosY,aWidth,12.27,'','',taLeftJustify,True);
      if FEspelho then
         aChaveAcesso:='SEM VALOR FISCAL (SOMENTE CONFERENCIA)'
      else
         aChaveAcesso:=NotaUtil.FormatarChaveAcesso(FChaveNFe);
      if FormularioContinuo then
         aTituloChave:=' '
        else
         aTituloChave:='CHAVE DE ACESSO';
      wtemp_FontSizeText:=FontSizeText;
      if FontNameUsed = 'Courier New' then
         FontSizeText:=6.3
      else
         FontSizeText:=8;
      Box([fsLeft,fsTop],PosX,YPos,aWidth,aHeigthPadrao,aTituloChave,aChaveAcesso,taCenter,True);
      FontSizeText:=wtemp_FontSizeText;

      if ACBrNFe.NotasFiscais.Items[FNFIndex].NFe.Ide.tpEmis in [teContingencia,teFSDA] then
         aChaveContigencia:=NotaUtil.GerarChaveContingencia(ACBrNFe.NotasFiscais.Items[FNFIndex].NFe)
      else
         aChaveContigencia:='';

      PosYCodBarraContigencia:=YPos;
      Box([fsLeft,fsTop],PosX,YPos,aWidth,12.27,'','',taLeftJustify,True);
      Result:=YPos;
      if aChaveContigencia<>'' then
         Box([fsLeft,fsTop],PosX,YPos,aWidth,aHeigthPadrao,'DADOS DA NFe',NotaUtil.FormatarChaveContigencia(aChaveContigencia),taCenter,True)
      else
      begin
         aProtocolo := ProtocoloNFe;
         if (ACBrNFe.NotasFiscais.Items[FNFIndex].NFe.Ide.tpEmis in [teNormal,teSCAN]) then
         begin
            if DFeUtil.EstaVazio(aProtocolo) then
               aProtocolo:=Trim(procNFe.nProt)+' '+DFeUtil.SeSenao(procNFe.dhRecbto<>0,DateTimeToStr(procNFe.dhRecbto),'');
            if ((NFeCancelada) or
                (ACBrNFe.NotasFiscais.Items[FNFIndex].NFe.procNFe.cStat in [101,151])) then
               Box([fsLeft,fsTop],PosX,YPos,aWidth,aHeigthPadrao,'PROTOCOLO DE HOMOLOGA��O DO CANCELAMENTO',aProtocolo,taCenter,True)
            else
            if (ACBrNFe.NotasFiscais.Items[FNFIndex].NFe.procNFe.cStat=110) then
               Box([fsLeft,fsTop],PosX,YPos,aWidth,aHeigthPadrao,'PROTOCOLO DE DENEGA��O',aProtocolo,taCenter,True)
            else
               Box([fsLeft,fsTop],PosX,YPos,aWidth,aHeigthPadrao,'PROTOCOLO DE AUTORIZA��O DE USO',aProtocolo,taCenter,True);
         end
         else if (ACBrNFe.NotasFiscais.Items[FNFIndex].NFe.Ide.tpEmis in [teDPEC]) then
            Box([fsLeft,fsTop],PosX,YPos,aWidth,aHeigthPadrao,'N�MERO DE REGISTRO DPEC',aProtocolo,taCenter,True);
      end;

      if not FEspelho then
      begin
         with TRPBarsCode128.Create(DANFeRave.BaseReport) do
         begin
            BaseReport:=DANFeRave.BaseReport;
            CodePage:=cpCodeC;
            BarCodeJustify:=pjCenter;
            UseChecksum:=false;
            BarWidth:=0.254;
            BarHeight:=10.0;
            WideFactor:=BarWidth;
            PrintReadable:=False;
            Text:=DFeUtil.LimpaNumero(FChaveNFe);
            PrintXY(PosX+(aWidth/2),PosY+1);
            Free;
         end;

         if aChaveContigencia<>'' then
         begin
            with TRPBarsCode128.Create(DANFeRave.BaseReport) do
            begin
               BaseReport:=DANFeRave.BaseReport;
               CodePage:=cpCodeC;
               BarCodeJustify:=pjCenter;
               UseChecksum:=false;
               BarWidth:=0.254;
               BarHeight:=10.0;
               WideFactor:=BarWidth;
               PrintReadable:=False;
               Text:=DFeUtil.LimpaNumero(aChaveContigencia);
               PrintXY(PosX+(aWidth/2),PosYCodBarraContigencia+1);
               Free;
            end;
         end
         else
         begin
            SetFont(FontNameUsed,DFeUtil.SeSenao(Pos('Courier',FontNameUsed)>0,8,9));
            if DFeUtil.LimpaNumero(aProtocolo)='' then
            begin
              Bold:=True;
              FontColor:=clRed;
              w1:='N F E   A I N D A   N � O   F O I';
              w2:='A U T O R I Z A D A   P E L A';
              w3:=' S E F A Z  (SEM VALIDADE FISCAL)';
            end
            else
            begin
              w1:='Consulta de autenticidade no portal nacional';
              w2:='da NF-e www.nfe.fazenda.gov.br/portal ou';
              w3:='no site da Sefaz Autorizadora';
            end;
            GotoXY(PosX,PosYCodBarraContigencia+GetFontHeigh+0.5);
            CenterX:=PosX+(aWidth/2);
            PrintCenter(w1,CenterX);
            NewLine;
            PrintCenter(w2,CenterX);
            NewLine;
            PrintCenter(w3,CenterX);
            Bold:=False;
            FontColor:=clBlack;
         end;
      end
      else
      begin
         SetFont(FontNameUsed,24);
         Bold:=True;
         Underline:=True;
         GotoXY(PosX+1,PosY);
         NewLine;
         PrintXY(PosX+5,YPos-1,'SEM VALOR FISCAL');
         Bold:=False;
         Underline:=False;
      end;
   end;
end;

function ImprimirEmitenteOutrosDados(PosX,
  PosY, WidthNaturezaOperacao: Double): Double;
var wTemp:String;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     if (not FormularioContinuo) then
        wTemp:='NATUREZA DA OPERA��O'
       else
        wTemp:=' ';
     Box([fsTop],PosX,PosY,WidthNaturezaOperacao,aHeigthPadrao,wTemp,ide.natOp,taLeftJustify,True,False,False);
     if (not FormularioContinuo) then
     begin
       Box([fsTop],PosX,YPos,82,aHeigthPadrao,'INSCRI��O ESTADUAL',Emit.IE,taLeftJustify);
       Box([fsTop,fsLeft],XPos,YPos,75,aHeigthPadrao,'INSCRI��O ESTADUAL DO SUBST. TRIBUT�RIO',Emit.IEST,taLeftJustify);
       Box([fsTop,fsLeft],XPos,YPos,87,aHeigthPadrao,'C.N.P.J.',DFeUtil.FormatarCNPJ(Emit.CNPJCPF),taLeftJustify,True);
     end
     else begin
       Box([fsTop,fsLeft],XPos,YPos,87,aHeigthPadrao,' ','',taLeftJustify,True);
     end;
     Result:=YPos;
  end;
end;

procedure TituloDoBloco(PosX,PosY:Double;aDescricao: string);
begin
  with DANFeRave, DANFeRave.BaseReport do begin
    SetFont(FontNameUsed,FontSizeGroup);
    Bold:=True;
    PrintXY(PosX,PosY+3,aDescricao);
    Bold:=False;
    GotoXY(PosX,YPos+0.5);
  end;
end;

function ImprimirRemetenteDestinatario(PosX,
  PosY: Double): Double;
var vEnd:String; vEntSai: string;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
      if ide.tpNF=tnEntrada then
         vEntSai:='Entrada'
      else
         vEntSai:='Saida';

     TituloDoBloco(PosX,PosY,'DESTINAT�RIO / REMETENTE');
     Box([],PosX,YPos,132,aHeigthPadrao,'Nome / Raz�o Social',Dest.XNome);
     if Length(Trim(Dest.CNPJCPF)) > 11 then
       Box([fsLeft],XPos,YPos,41,aHeigthPadrao,'CNPJ / CPF',DFeUtil.FormatarCNPJ(Dest.CNPJCPF),taCenter)
     else
       Box([fsLeft],XPos,YPos,41,aHeigthPadrao,'CNPJ / CPF',DFeUtil.FormatarCPF(Dest.CNPJCPF),taCenter);
     Box([fsLeft],XPos,YPos,21,aHeigthPadrao,'Data de Emiss�o',DFeUtil.FormatDate(DateToStr(Ide.DEmi)),taCenter,True);
     with Dest.EnderDest do
      begin
       vEnd:=XLgr;
       if (Trim(Nro)>'') and (Nro<>'SN') then
          vEnd:=vEnd+' '+Nro;
       if Trim(XCpl)>'' then
          vEnd:=vEnd+', '+XCpl;
       Box([fsTop],PosX,YPos,93,aHeigthPadrao,'Endere�o',vEnd);
       Box([fsTop,fsLeft],XPos,YPos,50,aHeigthPadrao,'Bairro',XBairro);
       Box([fsTop,fsLeft],XPos,YPos,30,aHeigthPadrao,'CEP',NotaUtil.FormatarCEP(DFeUtil.Poem_Zeros(CEP,8)),taCenter);
       Box([fsTop,fsLeft],XPos,YPos,21,aHeigthPadrao,'Data de '+vEntSai,DFeUtil.FormatDate(DateToStr(Ide.DSaiEnt)),taCenter,True);
       Box([fsTop],PosX,YPos,85,aHeigthPadrao,'Munic�pio',XMun);
       Box([fsTop,fsLeft],XPos,YPos,40,aHeigthPadrao,'Fone / Fax',NotaUtil.FormatarFone(Fone),taCenter);
       Box([fsTop,fsLeft],XPos,YPos,10,aHeigthPadrao,'Estado',UF,taCenter);
       Box([fsTop,fsLeft],XPos,YPos,38,aHeigthPadrao,'Inscri��o Estadual',Dest.IE,taCenter);

       if ide.hSaiEnt = 0 then
          Box([fsTop,fsLeft],XPos,YPos,21,aHeigthPadrao,'Hora de '+vEntSai,'',taCenter,True)
       else
          Box([fsTop,fsLeft],XPos,YPos,21,aHeigthPadrao,'Hora de '+vEntSai,TimeToStr(ide.hSaiEnt),taCenter,True);
     end;
     Result:=YPos;
  end;
end;

function ImprimirLocalRetirada(PosX,
  PosY: Double): Double;
var vEnd:String;
    wtemp_FontSizeText: double;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     //Ocultar Local se n�o for informado CNPJ
     if DFeUtil.EstaVazio(Retirada.CNPJCPF) then
      begin
        Result:=PosY;
        exit;
      end;

     TituloDoBloco(PosX,PosY,'LOCAL DE RETIRADA');
     with Retirada do
     begin
       wtemp_FontSizeText:=FontSizeText;
       if FontNameUsed = 'Courier New' then
          FontSizeText:=8;
       if Length(Trim(CNPJCPF)) > 11 then
         Box([],XPos,YPos,34,aHeigthPadrao,'CNPJ/CPF',DFeUtil.FormatarCNPJ(CNPJCPF),taCenter)
       else
         Box([],XPos,YPos,34,aHeigthPadrao,'CNPJ/CPF',DFeUtil.FormatarCPF(CNPJCPF),taCenter);
       FontSizeText:=wtemp_FontSizeText;

       vEnd:=XLgr;
       if (Trim(Nro)>'') and (Nro<>'SN') then
          vEnd:=vEnd+' '+Nro;
       if Trim(XCpl)>'' then
          vEnd:=vEnd+', '+XCpl;
       vEnd:=vEnd+' - '+xBairro+' - '+xMun+' - '+UF;
       Box([fsLeft],XPos,YPos,21,aHeigthPadrao,'Endere�o',vEnd,taLeftJustify,true);
     end;

     Result:=YPos;
  end;
end;

function ImprimirLocalEntrega(PosX,
  PosY: Double): Double;
var vEnd:String;
    wtemp_FontSizeText: double;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     //Ocultar Local se n�o for informado CNPJ
     if DFeUtil.EstaVazio(Entrega.CNPJCPF) then
      begin
        Result:=PosY;
        exit;
      end;

     TituloDoBloco(PosX,PosY,'LOCAL DE ENTREGA');
     with Entrega do
     begin
       wtemp_FontSizeText:=FontSizeText;
       if FontNameUsed = 'Courier New' then
          FontSizeText:=8;
       if Length(Trim(CNPJCPF)) > 11 then
         Box([],XPos,YPos,34,aHeigthPadrao,'CNPJ/CPF',DFeUtil.FormatarCNPJ(CNPJCPF),taCenter)
       else
         Box([],XPos,YPos,34,aHeigthPadrao,'CNPJ/CPF',DFeUtil.FormatarCPF(CNPJCPF),taCenter);
       FontSizeText:=wtemp_FontSizeText;

       vEnd:=XLgr;
       if (Trim(Nro)>'') and (Nro<>'SN') then
          vEnd:=vEnd+' '+Nro;
       if Trim(XCpl)>'' then
          vEnd:=vEnd+', '+XCpl;
       vEnd:=vEnd+' - '+xBairro+' - '+xMun+' - '+UF;
       Box([fsLeft],XPos,YPos,21,aHeigthPadrao,'Endere�o',vEnd,taLeftJustify,True);
     end;

     Result:=YPos;
  end;
end;

function ImprimirFaturas(PosX, PosY: Double): Double;
var i:Integer;
    aHeight, XX,YY,YY2:Double;
    q, f:integer;
    wtemp_FontSizeText: double;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
      //Ocultar se n�o for informado nenhuma
      if (DFeUtil.EstaVazio(Cobr.Fat.nFat)) then
      begin
         if (Cobr.Dup.Count=0) then
         begin
            //exibir somemte informa��o se n�o for OUTRAS
            if Ide.indPag=ipOutras then
            begin
              Result:=PosY;
              exit;
            end
            else
            begin
               TituloDoBloco(PosX,PosY,'FATURA/DUPLICATAS');
               if Ide.indPag=ipVista then
                  Box([],XPos,YPos,35,aHeigthPadrao,'�','PAGAMENTO � VISTA',taLeftJustify,True)
               else if Ide.indPag=ipPrazo then
                  Box([],XPos,YPos,35,aHeigthPadrao,'�','PAGAMENTO A PRAZO',taLeftJustify,True);
               Result:=PosY+aHeigthPadrao+LineHeight;
               exit;
            end;
         end;
      end;

     TituloDoBloco(PosX,PosY,'FATURA/DUPLICATAS');
     YY2:=0;
     if not (DFeUtil.EstaVazio(Cobr.Fat.nFat)) then
     begin
        Box([fsRigth],XPos,YPos,30,aHeigthPadrao,'N�mero da Fatura',Cobr.Fat.nFat,taLeftJustify);
        Box([fsLeft,fsRigth],XPos,YPos,30,aHeigthPadrao,'Valor Original',DFeUtil.FormatFloat(Cobr.Fat.vOrig),taLeftJustify);
        Box([fsLeft,fsRigth],XPos,YPos,30,aHeigthPadrao,'Valor do Desconto',DFeUtil.FormatFloat(Cobr.Fat.vDesc),taLeftJustify);
        Box([fsLeft],XPos,YPos,30,aHeigthPadrao,'Valor L�quido',DFeUtil.FormatFloat(Cobr.Fat.vLiq),taLeftJustify,true);
        YY2:=aHeigthPadrao;
     end;
     YY:=YPos;
     XX:=PosX;
     if not (Cobr.Dup.Count=0) then
     begin
        wtemp_FontSizeText:=FontSizeText;
        if FontNameUsed = 'Courier New' then
           FontSizeText:=8;

        ClearAllTabs;
        for i:=1 to 3 do
         begin
           SetTab(XX+1,pjLeft,25,0,0,0);
           SetTab(XX+22,pjCenter,19,0,0,0);
           SetTab(XX+42,pjRight,20,0,0,0);
           XX:=XX+67;
         end;
        GotoXY(XX,YY);
        NewLine;
        SetFontTitle;
        Underline:=True;
        for i:=1 to 3 do
         begin
           PrintTab('N�MERO');
           PrintTab('VENCIMENTO');
           PrintTab('VALOR');
         end;
        SetFontText;
        NewLine;
        q:=1;
        for f:=0 to Cobr.Dup.Count-1 do
         begin
          with Cobr.Dup.Items[f] do
           begin
            PrintTab(NDup);
            PrintTab(DFeUtil.FormatDate(DateToStr(DVenc)));
            PrintTab(DFeUtil.FormatFloat(VDup));
            Inc(q);
            if q>3 then
             begin
               NewLine;
               q:=1;
             end;
           end;
         end;

        aHeight:=YPos-PosY-GetFontHeigh-YY2 + 1;

        Box([],PosX,YY,67,aHeight);
        Box([fsLeft],XPos,YY,67,aHeight);
        Box([fsLeft],XPos,YY,67,aHeight,'','',taLeftJustify,True);

        if FontNameUsed = 'Courier New' then
           FontSizeText:=wtemp_FontSizeText;
     end;
     Result:=YPos;
  end;
end;

function ImprimirCalculoImposto(PosX, PosY: Double): Double;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     TituloDoBloco(PosX,PosY,'C�LCULO DO IMPOSTO');
     Box([],PosX,YPos,38,aHeigthPadrao,'Base de C�lculo do ICMS',DFeUtil.FormatFloat(Total.ICMSTot.VBC),taRightJustify);
     Box([fsLeft],XPos,YPos,38,aHeigthPadrao,'Valor do ICMS',DFeUtil.FormatFloat(Total.ICMSTot.VICMS),taRightJustify);
     Box([fsLeft],XPos,YPos,38,aHeigthPadrao,'Base C�lculo do ICMS Subst.',DFeUtil.FormatFloat(Total.ICMSTot.vBCST),taRightJustify);
     Box([fsLeft],XPos,YPos,38,aHeigthPadrao,'Valor do ICMS Subst.',DFeUtil.FormatFloat(Total.ICMSTot.vST),taRightJustify);
     Box([fsLeft],XPos,YPos,42,aHeigthPadrao,'Valor Total dos Produtos',DFeUtil.FormatFloat(Total.ICMSTot.VProd),taRightJustify,True);

     Box([fsTop],PosX,YPos,30.4,aHeigthPadrao,'Valor do Frete',DFeUtil.FormatFloat(Total.ICMSTot.VFrete),taRightJustify);
     Box([fsTop,fsLeft],XPos,YPos,30.4,aHeigthPadrao,'Valor do Seguro',DFeUtil.FormatFloat(Total.ICMSTot.VSeg),taRightJustify);
     Box([fsTop,fsLeft],XPos,YPos,30.4,aHeigthPadrao,'Desconto',DFeUtil.FormatFloat(Total.ICMSTot.VDesc),taRightJustify);
     Box([fsTop,fsLeft],XPos,YPos,30.4,aHeigthPadrao,'Outras Desp. Acess�rias',DFeUtil.FormatFloat(Total.ICMSTot.VOutro),taRightJustify);
     Box([fsTop,fsLeft],XPos,YPos,30.4,aHeigthPadrao,'Valor do IPI',DFeUtil.FormatFloat(Total.ICMSTot.VIPI),taRightJustify);
     Box([fsTop,fsLeft],XPos,YPos,42,aHeigthPadrao,'VALOR TOTAL DA NOTA FISCAL',DFeUtil.FormatFloat(Total.ICMSTot.VNF),taRightJustify,True,False);

     Result:=YPos;
  end;
end;

function ImprimirTransportadorVolumes(PosX,
  PosY: Double): Double;
var
   wTemp_FontSizeText: double;
   i: integer;
   wFrete: string;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     TituloDoBloco(PosX,PosY,'TRANSPORTADOR / VOLUMES TRANSPORTADOS');

     Box([],PosX,YPos,90,aHeigthPadrao,'Nome / Raz�o Social',Transp.Transporta.XNome);

     wTemp_FontSizeText:=FontSizeText;
     FontSizeText:=8;
     case Transp.ModFrete of
        mfContaEmitente:
               begin
                  wFrete:='0-EMITENTE';
               end;
        mfContaDestinatario:
               begin
                  wFrete:='1-DEST/REM';
               end;
        mfContaTerceiros:
               begin
                  wFrete:='2-TERCEIROS';
               end;
        mfSemFrete:
               begin
                  wFrete:='9-SEM FRETE';
               end;
     end;
     Box([fsLeft],XPos,YPos,27,aHeigthPadrao,'Frete Por Conta',wFRETE,taCenter);
     FontSizeText:=wTemp_FontSizeText;

     wTemp_FontSizeText:=FontSizeText;
     FontSizeText:=TamanhoFonte_ANTT;
     Box([fsLeft],XPos,YPos,19,aHeigthPadrao,'C�digo ANTT',Transp.VeicTransp.RNTC,taCenter);
     FontSizeText:=wTemp_FontSizeText;

     Box([fsLeft],XPos,YPos,22,aHeigthPadrao,'Placa do Ve�culo',Transp.VeicTransp.Placa,taCenter);
     Box([fsLeft],XPos,YPos,8,aHeigthPadrao,'UF',Transp.VeicTransp.UF,taCenter);

     wtemp_FontSizeText:=FontSizeText;
     if FontNameUsed = 'Courier New' then
        FontSizeText:=8;
     if Length(TRim(Transp.Transporta.CNPJCPF)) > 11 then
       Box([fsLeft],XPos,YPos,30,aHeigthPadrao,'CNPJ / CPF',DFeUtil.FormatarCNPJ(Transp.Transporta.CNPJCPF),taCenter,True)
     else
       Box([fsLeft],XPos,YPos,30,aHeigthPadrao,'CNPJ / CPF',DFeUtil.FormatarCPF(Transp.Transporta.CNPJCPF),taCenter,True);
     FontSizeText:=wtemp_FontSizeText;

     Box([fsTop],PosX,YPos,90,aHeigthPadrao,'Endere�o',Transp.Transporta.XEnder);
     Box([fsTop,fsLeft],XPos,YPos,68,aHeigthPadrao,'Munic�pio',Transp.Transporta.XMun,taCenter);
     Box([fsTop,fsLeft],XPos,YPos,8,aHeigthPadrao,'UF',Transp.Transporta.UF,taCenter);
     Box([fsTop,fsLeft],XPos,YPos,30,aHeigthPadrao,'Inscri��o Estadual',Transp.Transporta.IE,taCenter,True);

     if Transp.Vol.Count > 0 then
      begin
        for I := 0 to Transp.Vol.Count - 1 do
        begin
           //if (Transp.Vol.Items[i].qVol <> 0) then
              Box([fsTop],PosX,YPos,20,aHeigthPadrao,'Quantidade',IntToStr(Transp.Vol.Items[i].qVol),taRightJustify);
           //else
           //   Box([fsTop],PosX,YPos,20,aHeigthPadrao,'Quantidade','',taRightJustify);
           Box([fsTop,fsLeft],XPos,YPos,34,aHeigthPadrao,'Esp�cie',Transp.Vol.Items[i].esp,taCenter);
           Box([fsTop,fsLeft],XPos,YPos,50,aHeigthPadrao,'Marca',Transp.Vol.Items[i].marca,taCenter);
           Box([fsTop,fsLeft],XPos,YPos,30,aHeigthPadrao,'Numero',Transp.Vol.Items[i].nVol,taCenter);
           //if (Transp.Vol.Items[i].pesoB <> 0) then
              Box([fsTop,fsLeft],XPos,YPos,30,aHeigthPadrao,'Peso Bruto',DFeUtil.FormatFloat(Transp.Vol.Items[i].pesoB,NotaUtil.PreparaCasasDecimais(3)),taRightJustify);
           //else
           //   Box([fsTop,fsLeft],XPos,YPos,30,aHeigthPadrao,'Peso Bruto','',taRightJustify);
           //if (Transp.Vol.Items[i].pesoL <> 0) then
              Box([fsTop,fsLeft],XPos,YPos,30,aHeigthPadrao,'Peso L�quido',DFeUtil.FormatFloat(Transp.Vol.Items[i].pesoL,NotaUtil.PreparaCasasDecimais(3)),taRightJustify,True);
           //else
           //   Box([fsTop,fsLeft],XPos,YPos,30,aHeigthPadrao,'Peso L�quido','',taRightJustify,True);
        end;
     end;
     
     Result:=YPos;
  end;
end;

function ImprimirCalculoISSQN(PosX, PosY: Double): Double;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     if Total.ISSQNtot.vServ>0 then
      begin
        Result:=PosY-aHeigthPadrao-4;
        TituloDoBloco(PosX,Result,'C�LCULO DO ISSQN');
        Box([],PosX,YPos,50,aHeigthPadrao,'Inscri��o Municipal',Emit.IM);
        Box([fsLeft],XPos,YPos,48,aHeigthPadrao,'Valor Total dos Servi�os',DFeUtil.FormatFloat(Total.ISSQNtot.vServ),taRightJustify);
        Box([fsLeft],XPos,YPos,48,aHeigthPadrao,'Base de C�lculo do ISSQN',DFeUtil.FormatFloat(Total.ISSQNtot.vBC),taRightJustify);
        Box([fsLeft],XPos,YPos,48,aHeigthPadrao,'Valor do ISSQN',DFeUtil.FormatFloat(Total.ISSQNtot.vISS),taRightJustify,True);
      end
     else
        Result:=PosY;
  end;
end;

function ImprimirDadosAdicionais(PosX,PosY,aHeigth: Double): Double;
var wInfCpl, YFim:Double;
    qLin : integer;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     PosY:=PosY-aHeigth-4;
     TituloDoBloco(PosX,PosY,'DADOS ADICIONAIS');
     YFim:=YPos+aHeigth;
     wInfCpl:=124;
     if FPageNum>1 then
        wInfCpl:=FLastX-XPos;
     Box([],PosX,YPos,wInfCpl,aHeigth,'Informa��es Complementares');
     if FPageNum=1 then
        Box([fsLeft],XPos,YPos,70,aHeigth,'Reservado ao Fisco','',taLeftJustify,True);
     SetFont(FontNameUsed,FontSizeInfComplementares);
     //informacoes complementares
     GotoXY(PosX,PosY+4);
     NewLine;
     NewLine;

     FMemoInfCpl.BaseReport:=BaseReport;
     FMemoInfCpl.PrintStart:=PosX+1;
     FMemoInfCpl.PrintEnd:=PosX+wInfCpl-2;
     FMemoInfCpl.NoNewLine:=True;
     qLin:=Trunc((YFim-YPos)/GetFontHeigh)-1;
     FMemoInfCpl.PrintLines(qLin,False);

     Result:=PosY;
  end;
end;

function ImprimirRodape(PosX: Double): Double;
var vEnd:String;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     SetFontTitle;
     Result:=FLastY-GetFontHeigh;
     GotoXY(PosX,Result);
     NewLine;
     vEnd:='DATA E HORA DA IMPRESS�O: '+FormatDateTime('dd/mm/yyyy hh:mm:ss',Now);
     if Trim(NomeDoUsuario)>'' then
        vEnd:=vEnd+' - '+NomeDoUsuario;
     PrintXY(PosX,YPos,vEnd);

     if Trim(NomeDoERP)>'' then
     begin
        vEnd:='Desenvolvido por '+NomeDoERP;
        PrintRight(vEnd,FLastX);
     end;
  end;
end;

procedure PrepararItens(PosX, FirstY, LastY: Double);
var aIncWidht,XX,YY:Double;
    i:Integer;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     if (SystemPrinter.MarginRight <> 5.1) then
        ColsWidth[2]:=ColsWidth[2]-(SystemPrinter.MarginRight-5.1);

     TituloDoBloco(PosX,FirstY,'DADOS DOS PRODUTOS / SERVI�OS');
     FirstY:=YPos;
     YY:=YPos;
     XX:=XPos;
     Box([],XPos,YPos,FLastX-XPos,LastY-FirstY);
     ClearAllTabs;
     for i:=Low(ColsWidth) to High(ColsWidth) do
      begin
        if (i=High(ColsWidth)) then
           aIncWidht:=FLastX-(XX+0.5+ColsWidth[i])
        else
           aIncWidht:=0;
        SetTab(XX+0.5,pjCenter,ColsWidth[i]-1+aIncWidht,0,0,0);
        XX:=XX+ColsWidth[i];
        if (i<High(ColsWidth)) then
         begin
           MoveTo(XX,FirstY);
           LineTo(XX,LastY);
         end;
      end;
     GotoXY(PosX,YY);
     SetFont(FontNameUsed,FontSizeItens);
     Bold:=True;
     NewLine;
     for i:=Low(ColsTitle) to High(ColsTitle) do
     begin
         if (i=4) and (emit.CRT = crtSimplesNacional)  then
            PrintTab('CSO')
         else if (i=10) and ImprimirDescPorc then
            PrintTab('DESC. %')
         else
            PrintTab(ColsTitle[i]);
     end;
     NewLine;
     for i:=Low(ColsTitleAux) to High(ColsTitleAux) do
     begin
         if (i=4) and (emit.CRT <> crtRegimeNormal)  then
            PrintTab('SN')
         else if (i=9) and ImprimirValorLiquido then
            PrintTab('L�QUIDO')
         else if (i=10) and ImprimirDescPorc then
            PrintTab('')
         else
            PrintTab(ColsTitleAux[i]);
     end;
     NewLine;
     SetFont(FontNameUsed,FontSizeItens);
     MoveTo(PosX,YPos-(LineHeight/1.2));
     LineTo(FLastX,YPos-(LineHeight/1.2));
     ClearAllTabs;
     XX:=PosX;
     for i:=Low(ColsWidth) to High(ColsWidth) do
      begin
        if (i=High(ColsWidth)) then
           aIncWidht:=FLastX-(XX+0.5+ColsWidth[i])
        else
           aIncWidht:=0;
        SetTab(XX+0.5,ColsAlingment[i],ColsWidth[i]-1+aIncWidht,0,0,0);
        XX:=XX+ColsWidth[i];
      end;
  end;
end;

function MontarPagina:Double;
var aWidthNatOper,XX,YY:Double;
begin
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
    SetPen(FColorBorders,psSolid,EspessuraBorda,pmCopy);
    Inc(FPageNum);
    Inc(FCurrentPage);
    if FPageNum=1 then
     begin
       FSerie:=IntToStr(Ide.serie);
       FNumeroNF:=FormatFloat('000000000',Ide.NNF);
       FNumeroNF:=Copy(FNumeroNF,1,3)+'.'+Copy(FNumeroNF,4,3)+'.'+Copy(FNumeroNF,7,3);
       //FEspelho:=Trim(procNFe.nProt)='';
       FEspelho:=false; //funcionalidade de espelho suspensa devido reclama��es
       FChaveNFe:=RightStr(infNFe.ID,44);
     end;

    XX:=MarginLeft;YY:=MarginTop;
    Result:=XX;
    YY:=ImprimirCanhoto(XX,YY);
    ImprimirMensagensDeFundo;
    XX:=ImprimirEmitente(XX,YY);
    XX:=ImprimirTituloDANFe(XX,YY);
    aWidthNatOper:=XX-Result;
    YY:=ImprimirCodigoBarras(XX,YY);
    YY:=ImprimirEmitenteOutrosDados(Result,YY,aWidthNatOper);

    SetPen(clBlack,psSolid,EspessuraBorda,pmCopy);

    //Imprime somente na primeira folha
    if FPageNum=1 then
     begin
       YY:=ImprimirRemetenteDestinatario(Result,YY);
       YY:=ImprimirLocalRetirada(Result,YY);
       YY:=ImprimirLocalEntrega(Result,YY);
       YY:=ImprimirFaturas(Result,YY);
       YY:=ImprimirCalculoImposto(Result,YY);
       YY:=ImprimirTransportadorVolumes(Result,YY);
       FLastItens:=ImprimirRodape(Result);
       FLastItens:=ImprimirDadosAdicionais(Result,FLastItens,32);
       FLastItens:=ImprimirCalculoISSQN(Result,FLastItens);
     end
    else begin
       FLastItens:=ImprimirRodape(Result);
       if (not IsPrintAllInfCpl) then
          if not IsPrintAllProd then
             FLastItens:=ImprimirDadosAdicionais(Result,FLastItens,32)
            else
             FLastItens:=ImprimirDadosAdicionais(Result,FLastItens,FLastItens-YY-4);
    end;

    if not IsPrintAllProd then
       PrepararItens(Result,YY,FLastItens);
  end;
end;

procedure ImprimirItens(PosX:Double);
var qPrinted,QtdeMin,j:Integer;
    aDescProduto, vEnd:String;
    Memo:TMemoBuf;
    aFontHeigth:Double;
begin

  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
   begin
     aFontHeigth:=GetFontHeigh;
     qPrinted:=0;
     while (FDetIndex<Det.Count) and ((LinhasPorPagina=0) or (qPrinted<LinhasPorPagina)) do
      begin
        Inc(qPrinted);
        with Det.Items[FDetIndex] do
         begin
          aDescProduto:=Trim(Prod.XProd);

          QtdeMin:=0;
          if ImprimirDetalhamentoEspecifico then
          begin
             if Prod.veicProd.chassi<>'' then
              begin
                with Prod.veicProd do
                 begin
                  aDescProduto:=aDescProduto+#13+
                                '  CHASSI: '+Prod.veicProd.chassi+#13+
                                '  COMBUST�VEL: '+CombDescricao+#13+
                                '  COR: '+xCor+#13+
                                '  FAB./MOD.: '+IntToStr(anoFab)+'/'+IntToStr(anoMod)+#13+
//                                '  RENAVAM: '+RENAVAM+#13+
                                '  N� DO MOTOR: '+nMotor;
                 end;
                QtdeMin:=QtdeMin+6;
              end;

             if Prod.med.Count > 0 then
              begin
                for j:=0 to Prod.med.Count-1 do
                 begin
                   with Prod.med.Items[j] do
                    begin
                     aDescProduto:=aDescProduto+
                                   ' - LOTE: '+nLote+
   //                                ' QTDADE: '+DFeUtil.FormatFloat(qLote)+#13+
                                   ' FABR.: '+DFeUtil.FormatDate(DateToStr(dFab))+
                                   ' VAL.: '+DFeUtil.FormatDate(DateToStr(dVal))+
                                   DFeUtil.SeSenao(vPMC>0,' PMC: '+DFeUtil.FormatFloat(vPMC),'');
                    end;
                   QtdeMin:=QtdeMin+1;
                 end;
              end;
          end;

          if Trim(infAdProd)>'' then
           begin
             aDescProduto:=aDescProduto+#13+StringReplace(infAdProd,';',#13,[rfReplaceAll]);
             Inc(QtdeMin);
             for j:=1 to Length(infAdProd) do
                if infAdProd[j]=';' then
                   Inc(QtdeMin);
           end;

          //Testa se a quantidade de linhas a ser impressa
          //ultrapassar� o final do quadro dos itens,
          //e caso aconte�a, cria uma nova p�gina
          if (YPos+(aFontHeigth*QtdeMin))>FLastItens then
           begin
             Break;
           end
           else
            if FDetIndex>0 then
             begin
               MoveTo(PosX,YPos+0.1-aFontHeigth);
               LineTo(FLastX,YPos+0.1-aFontHeigth);
             end;

          PrintTab(Prod.CProd);
          PrintTab('');

          vEnd:=Prod.NCM;
          if Trim(Prod.EXTIPI)>'' then
             vEnd:=vEnd+'/'+Prod.EXTIPI;
          PrintTab(vEnd);

          case Emit.CRT of

	          crtSimplesNacional                         : PrintTab(OrigToStr(Imposto.ICMS.orig)+CSOSNIcmsToStr(Imposto.ICMS.CSOSN));
            crtRegimeNormal , crtSimplesExcessoReceita : PrintTab(OrigToStr(Imposto.ICMS.orig)+CSTICMSToStr(Imposto.ICMS.CST));
          end;

          PrintTab(Prod.CFOP);
          PrintTab(Prod.UCom);

          PrintTab(DFeUtil.FormatFloat(Prod.QCom,DFeUtil.SeSenao(Mask_qCom='',NotaUtil.PreparaCasasDecimais(CasasDecimais_qCom),Mask_qCom)));
          PrintTab(DFeUtil.FormatFloat(Prod.VUnCom,DFeUtil.SeSenao(Mask_vUnCom='',NotaUtil.PreparaCasasDecimais(CasasDecimais_vUnCom),Mask_vUnCom)));

          if ImprimirValorLiquido then
             PrintTab(DFeUtil.FormatFloat(Prod.VProd-Prod.VDesc))
          else
             PrintTab(DFeUtil.FormatFloat(Prod.VProd));

          if ImprimirDescPorc then
          begin
            if Prod.vDesc > 0 then
               PrintTab(DFeUtil.FormatFloat(RoundTo(100-((((Prod.VUnCom*Prod.QCom)-Prod.vDesc)/(Prod.VUnCom*Prod.QCom))*100),-1))+'%')
            else
               PrintTab(DFeUtil.FormatFloat(Prod.vDesc));
          end
          else
            PrintTab(DFeUtil.FormatFloat(Prod.vDesc));

          PrintTab(DFeUtil.FormatFloat(Imposto.ICMS.vBC));
          PrintTab(DFeUtil.FormatFloat(Imposto.ICMS.vBCST));
          PrintTab(DFeUtil.FormatFloat(Imposto.ICMS.vICMSST));
          PrintTab(DFeUtil.FormatFloat(Imposto.ICMS.vICMS));
          PrintTab(DFeUtil.FormatFloat(Imposto.IPI.vIPI));
          PrintTab(DFeUtil.FormatFloat(Imposto.ICMS.pICMS));
          PrintTab(DFeUtil.FormatFloat(Imposto.IPI.pIPI));
          Memo:=TMemoBuf.Create;
          try
            Memo.PrintStart:=PosX+ColsWidth[1]+0.5;
            Memo.PrintEnd:=Memo.PrintStart+ColsWidth[2]-0.5;
            Memo.NoNewLine:=true;
            memo.text:=aDescProduto;
            PrintMemo(Memo,0,false);
          finally
            Memo.Free;
          end;

          // Beretta
          If Prod.UCom <> Prod.uTrib Then
             Begin
             NewLine;
             PrintTab('');  // 1
             PrintTab('');  // 2
             PrintTab('');  // 3
             PrintTab('');  // 4
             PrintTab('');  // 5
             PrintTab(Prod.uTrib);  // 6
             PrintTab(DFeUtil.FormatFloat(Prod.qTrib,'0.0000'));
             PrintTab(DFeUtil.FormatFloat(Prod.vUnTrib,'0.0000'));
             End ;

          Inc(FDetIndex);
          NewLine;
        end;
     end;
  end;
end;

procedure ImprimirRetrato(aRaveSystem:TDANFeRave);
var wtemp: double;
    wInfcpl,wInfFisco:String;
    i:Integer;
    bInicio:boolean;
begin
  //tamanho padrao das colunas
  ColsWidth[1]:=15;
  ColsWidth[2]:=52;
  ColsWidth[3]:=13;
  ColsWidth[4]:=7;
  ColsWidth[5]:=7;
  ColsWidth[6]:=7;
  ColsWidth[7]:=12;
  ColsWidth[8]:=13;
  ColsWidth[9]:=15;
  ColsWidth[10]:=11;
  ColsWidth[11]:=12;
  ColsWidth[12]:=0;
  ColsWidth[13]:=0;
  ColsWidth[14]:=10;
  ColsWidth[15]:=10;
  ColsWidth[16]:=8;
  ColsWidth[17]:=8;

  FontSizeText:=10;

  DANFeRave:=aRaveSystem;

  //ajusta tamanho da coluna codigo
  with DANFeRave, DANFeRave.ACBrNFe.NotasFiscais.Items[DANFeRave.FNFIndex].NFe, DANFeRave.BaseReport do
  begin
    FontSizeText:=TamanhoFonte_DemaisCampos;

    if TamanhoCampoCodigo <> 0 then
    begin
      wtemp:=ColsWidth[1]-TamanhoCampoCodigo;
      ColsWidth[1]:=ColsWidth[1]-wtemp;
      ColsWidth[2]:=ColsWidth[2]+wtemp;
    end;

    FDetIndex:=0;

    wInfFisco:='';
    for i:=0  to InfAdic.obsFisco.Count-1 do
    begin
      with InfAdic.obsFisco.Items[i] do
        wInfFisco:=wInfFisco+XCampo+': '+XTexto;
      wInfFisco:=wInfFisco+';';
    end;
    FMemoInfCpl.Text:=wInfFisco;
    if Trim(FMemoInfCpl.Text)>'' then
       FMemoInfCpl.Text:=FMemoInfCpl.Text+InfAdic.infAdFisco
    else
       FMemoInfCpl.Text:=InfAdic.infAdFisco;

    wInfcpl:='';
    for i:=0  to InfAdic.ObsCont.Count-1 do
    begin
      with InfAdic.ObsCont.Items[i] do
        wInfcpl:=wInfcpl+XCampo+': '+XTexto;
      wInfcpl:=wInfcpl+';';
    end;
    if Trim(FMemoInfCpl.Text)>'' then
      FMemoInfCpl.Text:=FMemoInfCpl.Text+';'+wInfCpl+InfAdic.infCpl
    else
      FMemoInfCpl.Text:=wInfCpl+InfAdic.infCpl;

     if Ide.tpEmis <> teNORMAL then
     begin
        case Ide.tpEmis of
           teDPEC: begin
                     wInfCpl:='DANFE impresso em conting�ncia - DPEC regularmente recebida pela Receita Federal do Brasil';
                   end;
           teFSDA,
           teContingencia,
           teSCAN:
                   begin
                     wInfCpl:='DANFE em Conting�ncia - impresso em decorr�ncia de problemas t�cnicos';
                   end;
        end;
        wInfCpl:=wInfCpl+';'+
        'DATA/HORA IN�CIO: '+DFeUtil.SeSenao(ide.dhCont = 0,' ',DateTimeToStr(ide.dhCont))+';'+
        'MOTIVO CONTING�NCIA: '+DFeUtil.SeSenao(DFeUtil.EstaVazio(ide.xJust),' ',ide.xJust);

         FMemoInfCpl.Text:=FMemoInfCpl.Text+';;'+wInfCpl;
     end;

    FMemoInfCpl.Text:=StringReplace(FMemoInfCpl.Text,';',#13,[rfReplaceAll]);

    bInicio:=True;
    while not ((IsPrintAllProd) and (IsPrintAllInfCpl)) do begin
      if not bInicio then
         NewPage;
      ImprimirItens(MontarPagina);
      bInicio:=False;
    end;
  end;
end;

end.
