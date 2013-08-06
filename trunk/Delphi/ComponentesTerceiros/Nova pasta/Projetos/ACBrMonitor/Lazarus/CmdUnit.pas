{******************************************************************************}
{ Projeto: ACBr Monitor                                                        }
{  Executavel multiplataforma que faz uso do conjunto de componentes ACBr para }
{ criar uma interface de comunicação com equipamentos de automacao comercial.  }
{                                                                              }
{ Direitos Autorais Reservados (c) 2010 Daniel Simões de Almeida               }
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
{       Rua Coronel Aureliano de Camargo, 973 - Tatuí - SP - 18270-170         }
{                                                                              }
{******************************************************************************}
unit CmdUnit;

{$mode objfpc}{$H+}

interface
Uses SysUtils, Classes, Math ;

Const
   Objetos = '"ECF","CHQ","GAV","DIS","LCB","ACBR","BAL","ETQ","BOLETO","CEP","IBGE"' ;

type
TACBrCmd = class
  private
    fsParams : TStringList ;
    fsComando: AnsiString;
    fsObjeto : AnsiString;
    fsMetodo : AnsiString;
    fsResposta: AnsiString;
    procedure SetComando(const Value: AnsiString);

  public
    constructor Create;
    destructor Destroy; override ;

    function Params( Index : Integer) : AnsiString ;

    property Comando : AnsiString read fsComando write SetComando ;
    property Objeto  : AnsiString read fsObjeto ;
    property Metodo  : AnsiString read fsMetodo ;
    property Resposta: AnsiString read fsResposta write fsResposta ;
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

function TACBrCmd.Params(Index: Integer): AnsiString;
begin
  if Index > fsParams.Count-1 then
     Result := ''
  else
     Result := fsParams[Index] ;
end;

procedure TACBrCmd.SetComando(const Value: AnsiString);
Var P,PaI,PaF,Pv : Integer ;
    wComando, wParam, wProxChar : AnsiString ;
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
     raise Exception.Create('Objeto inválido: '+fsObjeto+sLineBreak+
                            ' Permitidos: '+Objetos ) ;

  wComando := copy(wComando, P+1, Length(wComando) ) ;

  { Achando o Método }
  P := pos('(',wComando) ;
  if P = 0 then
  begin
     { Verificando se é uma atribuição a propriedade Ex: "Ativo := true"
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
     raise Exception.Create('Metodo não informado') ;

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
       { Verificando se a aspas é seguida de ',' ou ')' ou fim do comando
          Se não for, então a aspas não é um delimitador de String e sim
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
           raise Exception.Create('Parametro Inválido. String não terminada');

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
 
