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
unit UtilUnit;

interface
Uses SysUtils, IniFiles, Classes, ACBrUtil ;

Function AcertaPath(APath : String): String;
function Converte(cmd: String): String;
Procedure GravaINICrypt(INI : TIniFile; Section, Ident, AString, Pass : String );
Function LeINICrypt(INI : TIniFile; Section, Ident, Pass : String) : String ;

implementation

{------------------------------------------------------------------------------}
function AcertaPath(APath : String): String;
begin
  Result := trim(APath) ;
  if Result <> '' then
     if pos(PathDelim, APath) = 0 then
        Result := ExtractFilePath( ParamStr(0) ) + APath ;
end;

{------------------------------------------------------------------------------}
function Converte(cmd: String): String;
var A : Integer ;
begin
  Result := '' ;
  For A := 1 to length( cmd ) do
  begin
     if not (cmd[A] in ['A'..'Z','a'..'z','0'..'9',
                        ' ','.',',','/','?','<','>',';',':',']','[','{','}',
                        '\','|','=','+','-','_',')','(','*','&','^','%','$',
                        '#','@','!','~' ]) then
        Result := Result + '#' + IntToStr(ord( cmd[A] )) + ' '
     else
        Result := Result + cmd[A] + ' ';
  end ;
end;

{------------------------------------------------------------------------------}
Function LeINICrypt(INI : TIniFile; Section, Ident, Pass : String) : String ;
  var SStream  : TStringStream ;
      CryptStr : String ;
begin
   SStream := TStringStream.Create('') ;
   try
      INI.ReadBinaryStream(Section, Ident, SStream) ;
      CryptStr := SStream.DataString  ;
      Result   := StrCrypt( CryptStr, Pass) ;
   finally
      SStream.Free ;
   end ;
end ;

{------------------------------------------------------------------------------}
Procedure GravaINICrypt(INI : TIniFile; Section, Ident, AString, Pass : String );
 var SStream  : TStringStream ;
     CryptStr : String ;
begin
  CryptStr := StrCrypt( AString, Pass) ;
  SStream := TStringStream.Create(CryptStr) ;
  try
     INI.WriteBinaryStream(Section, Ident, SStream) ;
  finally
     SStream.Free ;
  end ;
end ;



end.
