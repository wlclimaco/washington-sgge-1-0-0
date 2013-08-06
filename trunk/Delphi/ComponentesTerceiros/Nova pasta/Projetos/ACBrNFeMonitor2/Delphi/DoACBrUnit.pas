{******************************************************************************}
{ Projeto: ACBr Monitor                                                        }
{  Executavel multiplataforma que faz uso do conjunto de componentes ACBr para }
{ criar uma interface de comunica��o com equipamentos de automacao comercial.  }
{                                                                              }
{ Direitos Autorais Reservados (c) 2006 Daniel Simoes de Almeida               }
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

unit DoACBrUnit ;

interface
Uses Classes, TypInfo, SysUtils, CmdUnit, Types ;
{$IFDEF MSWINDOWS}
  function BlockInput (fBlockInput: boolean): dword; stdcall; external 'user32.dll';
{$ENDIF}

Procedure DoACBr( Cmd : TACBrCmd ) ;
Procedure VerificaPermiteComandosRemoto ;
Function ConvertStrRecived( AStr: String ) : String ;

implementation
Uses ACBrUtil, StrUtils, DateUtils,
  {$IFDEF MSWINDOWS}sndkey32, Windows,{$ENDIF}
  {$IFNDEF NOGUI}QForms, ACBrMonitor1 {$ELSE}ACBrMonitorConsoleDM {$ENDIF} ;

Procedure DoACBr( Cmd : TACBrCmd ) ;
Var AltTab : Boolean ;
    Memo   : TStringList ;
    dtFim  : TDateTime ;
    nWait  : Integer ;
    Files  : String ;
begin
  with {$IFNDEF NOGUI}FrmACBrMonitor{$ELSE}dm{$ENDIF} do
  begin
     try
        if Cmd.Metodo = 'run' then
         begin
           VerificaPermiteComandosRemoto ;

           if Cmd.Params(0) = '' then
              raise Exception.Create('Linha de comando n�o informada');

           AltTab := StrToBoolDef(Cmd.Params(4),False) ; { Envia ALT-TAB quando Terminar ?}
           RunCommand( Cmd.Params(0),                       { Linha de comando }
                       Cmd.Params(1),                       { Parametros adicionais }
                       StrToBoolDef(Cmd.Params(2),False), { Aguarda termino execu�ao ? }
                       StrToIntDef(Cmd.Params(3),1));
           {$IFDEF MSWINDOWS}
           if AltTab then
              SendKeys(pchar('%{TAB}'), False);
           {$ENDIF}
         end

        {$IFNDEF NOGUI}
          else if Cmd.Metodo = 'restaurar' then
             Restaurar1Click( FrmACBrMonitor )

          else if Cmd.Metodo = 'ocultar' then
             Ocultar1Click( FrmACBrMonitor )

          else if Cmd.Metodo = 'encerrarmonitor' then
             Application.Terminate
        {$ENDIF}

        {$IFDEF MSWINDOWS}
         else if Cmd.Metodo = 'sendkeys' then
            SendKeys( PChar(Cmd.Params(0)),                  { Teclas a Enviar }
                      StrToBoolDef(Cmd.Params(1),False) )           { Espera ? }
            
         else if Cmd.Metodo = 'appactivate' then
          begin
            nWait := StrToIntDef( Cmd.Params(1), 0 ) ;
            if nWait > 0 then
               Sleep(nWait);
            AppActivate( PChar(Cmd.Params(0)) ) ;
          end

         else if Cmd.Metodo = 'appexists' then
            Cmd.Resposta := BoolToStr( AppExists( PChar(Cmd.Params(0)) ), true )

         else if Cmd.Metodo = 'blockinput' then
           BlockInput( StrToBool(Cmd.Params(0)) )
        {$ENDIF}

        else if Cmd.Metodo = 'savetofile' then
         begin
           VerificaPermiteComandosRemoto ;

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
           VerificaPermiteComandosRemoto ;

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
              raise Exception.Create('Arquivo '+Cmd.Params(0)+' n�o encontrado')
         end

        else if Cmd.Metodo = 'filesexists' then
         begin
           VerificaPermiteComandosRemoto ;

           Files := Cmd.Params(0) ;
           dtFim := IncSecond(now, StrToIntDef(Cmd.Params(1),0) ) ;
           while (now <= dtFim) and ( not FileExists( Files ) ) do
           begin
              {$IFNDEF NOGUI}
               Application.ProcessMessages ;
              {$ENDIF}
              sleep(100) ;
           end ;

           Cmd.Resposta := BoolToStr(FilesExists( Files ), True) ;
         end

        else if Cmd.Metodo = 'deletefiles' then
         begin
           VerificaPermiteComandosRemoto ;

           Files := Trim(Cmd.Params(0)) ;
           if (Files = '') or (Files = '*') or (Files = '*.*') then
              raise Exception.Create( 'Mascara inv�lida: ['+Files+']') ;

           DeleteFiles( Files ) ;
           if FilesExists( Files ) then
              raise Exception.Create('Arquivo(s) ['+Files+'] ainda existe(m)') ;
         end

        else if pos('|'+Cmd.Metodo+'|', '|exit|bye|fim|sair|') > 0 then {fecha conexao}
         begin
           Cmd.Resposta := 'Obrigado por usar o ACBrMonitor' ;
           {$IFNDEF NOGUI}
             mCmd.Lines.Clear;
           {$ELSE}
             WriteLn( 'Obrigado por usar o ACBrMonitorConsole' ) ;
           {$ENDIF}

           if Assigned( Conexao ) then
              if Assigned( Conexao.Connection ) then
                 Conexao.Connection.Disconnect ;
         end

        ELSE
           raise Exception.Create('Comando inv�lido ('+ copy(Cmd.Comando,6,length(Cmd.Comando))+')') ;

     finally
        { Nada a fazer aqui por enquanto... :) }
     end ;
  end ;
end ;

Procedure VerificaPermiteComandosRemoto ;
begin
  with {$IFNDEF NOGUI}FrmACBrMonitor{$ELSE}dm{$ENDIF} do
  begin
     {$IFNDEF NOGUI}
       if not cbComandos.Checked then
     {$ELSE}
       if not PermiteComandos then
     {$ENDIF}
          raise Exception.Create('Comandos Remotos n�o s�o permitidos');
  end ;
end ;

Function ConvertStrRecived( AStr: String ) : String ;
 Var P   : Integer ;
     Hex : String ;
     CharHex : Char ;
begin
  { Verificando por codigos em Hexa }
  Result := AStr ;

  P := pos('\x',Result) ;
  while P > 0 do
  begin
     Hex := copy(Result,P+2,2) ;

     try
        CharHex := Chr(StrToInt('$'+Hex)) ;
     except
        CharHex := ' ' ;
     end ;

     Result := StringReplace(Result,'\x'+Hex,CharHex,[rfReplaceAll]) ;
     P      := pos('\x',Result) ;
  end ;
end ;

end.


