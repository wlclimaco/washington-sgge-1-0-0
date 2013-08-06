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
unit CmdUnit;

interface
Uses SysUtils, Classes, Math ;

Const
   Objetos = '"ECF","CHQ","GAV","DIS","LCB","ACBR","BAL"' ;

type
TACBrCmd = class
  private
    fsParams : TStringList ;
    fsComando: String;
    fsObjeto : String;
    fsMetodo : String;
    fsResposta: String;
    procedure SetComando(const Value: String);

  public
    constructor Create;
    destructor Destroy; override ;

    function Params( Index : Integer) : String ;

    property Comando : String      read fsComando write SetComando ;
    property Objeto  : String      read fsObjeto ;
    property Metodo  : String      read fsMetodo ;
    property Resposta: String      read fsResposta write fsResposta ;
  end;

implementation

uses StrUtils;

{----------------------------------- TACBrCmd ---------------------------------}
constructor TACBrCmd.Create;
begin
  fsParams := TStringList.Create ;
end;

destructor TACBrCmd.Destroy;
begin
  fsParams.Free ;
  inherited Destroy ;
end;

function TACBrCmd.Params(Index: Integer): String;
begin
  if Index > fsParams.Count-1 then
     Result := ''
  else
     Result := fsParams[Index] ;
end;

procedure TACBrCmd.SetComando(const Value: String);
Var P,PaI,PaF,Pv : Integer ;
    wComando, wParam, wProxChar : String ;
begin
  fsMetodo   := '' ;
  fsObjeto   := '' ;
  fsResposta := '' ;
  fsParams.Clear ;

  fsComando := Value ;
  wComando  := Value ;

  { Achando o Objeto }
  P := pos('.',wComando) ;
  if P = 0 then
     raise Exception.Create('Objeto nao definido') ;

  fsObjeto := UpperCase( Trim(copy(fsComando,1,P-1)) ) ;
  if pos('"'+fsObjeto+'"', Objetos) = 0 then
     raise Exception.Create('Objeto inv�lido: '+fsObjeto+sLineBreak+
                            ' Permitidos: '+Objetos ) ;

  wComando := copy(wComando, P+1, Length(wComando) ) ;

  { Achando o M�todo }
  P := pos('(',wComando) ;
  if P = 0 then
  begin
     { Verificando se � uma atribui��o a propriedade Ex: "Ativo := true"
       Se for, transforma em "SetAtivo(True)"  }
     P := pos(':=',wComando) ;
     if P > 0 then
      begin
         wComando := 'Set'+Trim(copy(wComando,1,P-1))+'('+
                           Trim(copy(wComando,1,P+2))+')' ;
         P := pos('(',wComando) ;
      end
     else
        P := Length( wComando ) + 1 ;
  end ;

  fsMetodo := LowerCase( Trim(copy(wComando,1,P-1)) ) ;
  if fsMetodo = '' then
     raise Exception.Create('Metodo n�o informado') ;

  { Tem Parameteros ? }
  wComando := copy(wComando, P+1, Length(wComando) ) ;
  while Length(wComando) > 0 do
  begin
     PaI := pos('"',wComando) ;
     Pv  := pos(',',wComando) ;  { Procurando o Fim do parametro }
     if Pv = 0 then
        Pv := pos(')',wComando) ;
     if Pv = 0 then
        Pv := Length( wComando ) + 1;

     if (PaI <> 0) and (PaI < Pv) then  { Tem aspas no Inicio do Comando }
      begin                             { Entao procure a proxima Aspas }
       { Verificando se a aspas � seguida de ',' ou ')' ou fim do comando
          Se n�o for, ent�o a aspas n�o � um delimitador de String e sim
          faz parte do texto da String }
        PaF := PaI ;
        wProxChar := ' ' ;
        while (PaF <> 0) and
              (wProxChar <> '') and
              (pos(wProxChar, ',)') = 0) do
        begin
           if wProxChar = '"' then
              Inc( PaF ) ;
           PaF := PaF + max(Pos('"',copy(wComando, PaF+1, Length(Wcomando)) ),1) ;
           wProxChar := copy(Trim(copy(wComando, PaF+1 , Length(Wcomando))),1,1) ;
        end ;

        if PaF = 0 then
           raise Exception.Create('Parametro Inv�lido. String n�o terminada');

        wParam := copy(wComando, PaI+1 , PaF-PaI-1 ) ;
        Pv := PosEx(',', wComando, PaF+1 ) ;
        if Pv = 0 then
           Pv := Length( wComando ) + 1;
      end
     else
        wParam := copy(wComando, 1, Pv-1 ) ;

     { convertendo as aspas duplas "", para simples " }
     wParam := StringReplace(wParam,'""','"',[rfReplaceAll]) ;

     fsParams.Add( wParam ) ;
     wComando := copy(wComando, Pv+1, Length( wComando ) ) ;
  end ;
end;

end.
 