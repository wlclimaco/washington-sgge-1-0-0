////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//              PCN - Projeto Cooperar NFe                                    //
//                                                                            //
//   Descri��o: Classes para gera��o/leitura dos arquivos xml da NFe          //
//                                                                            //
//        site: www.projetocooperar.org/nfe                                   //
//       email: projetocooperar@zipmail.com.br                                //
//       forum: http://br.groups.yahoo.com/group/projeto_cooperar_nfe/        //
//     projeto: http://code.google.com/p/projetocooperar/                     //
//         svn: http://projetocooperar.googlecode.com/svn/trunk/              //
//                                                                            //
// Coordena��o: Paulo Casagrande                                              //
//                                                                            //
//      Equipe: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
//      Vers�o: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
//     Licen�a: GNU General Public License (GNU GPL)                          //
//                                                                            //
//              - Este programa � software livre; voc� pode redistribu�-lo    //
//              e/ou modific�-lo sob os termos da Licen�a P�blica Geral GNU,  //
//              conforme publicada pela Free Software Foundation; tanto a     //
//              vers�o 2 da Licen�a como (a seu crit�rio) qualquer vers�o     //
//              mais nova.                                                    //
//                                                                            //
//              - Este programa � distribu�do na expectativa de ser �til,     //
//              mas SEM QUALQUER GARANTIA; sem mesmo a garantia impl�cita de  //
//              COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM       //
//              PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter   //
//              mais detalhes. Voc� deve ter recebido uma c�pia da Licen�a    //
//              P�blica Geral GNU junto com este programa; se n�o, escreva    //
//              para a Free Software Foundation, Inc., 59 Temple Place,       //
//              Suite 330, Boston, MA - 02111-1307, USA ou consulte a         //
//              licen�a oficial em http://www.gnu.org/licenses/gpl.txt        //
//                                                                            //
//    Nota (1): - Esta  licen�a  n�o  concede  o  direito  de  uso  do nome   //
//              "PCN  -  Projeto  Cooperar  NFe", n�o  podendo o mesmo ser    //
//              utilizado sem previa autoriza��o.                             //
//                                                                            //
//    Nota (2): - O uso integral (ou parcial) das units do projeto esta       //
//              condicionado a manuten��o deste cabe�alho junto ao c�digo     //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//     Instru��es                                                             //
//                                                                            //
// 1 - Crie uma pasta e coloque na o programa do validador                    //
//                                                                            //
//     ...\bin\validador                                                      //
//                                                                            //
// 2 - Crie sub-pastas com os schemas                                         //
//                                                                            //
//     ...\bin\validador\PL005C                                               //
//                                                                            //
// 3 - Parametros da fun��o que chama o validador                             //
//                                                                            //
//     Mensagens: AnsiString - Variavel que recebera as mensagens de erro     //
//     PathArquivoXML: string - Caminho e o nome do arquivo xml               //
//     PathValidador: string - Caminho para pasta que esta o validador        //
//     Schema: TpcnSchema - Schema a ser utilizado                            //
//     TipoLayout: TpcnTipoLayout - Tipo do layout do arquivo                 //
//     ApagarAposValidar: Boolean - Op��o para apagar o arquivo ap�s validar  //
//                                                                            //
// 4 - Retorno                                                                //
//                                                                            //
//     Boolean - Retorna False se ocorrer algum erro                          //
//                                                                            //
// 5 - Conceito                                                               //
//                                                                            //
//     Permitir que outros validadores sejam integrados ao projeto utilizando //
//     outras tecnologias e linguagens                                        //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////

unit pcnValidador;

interface uses
  windows, SysUtils, Classes, pcnConversao;

function ValidarXML(var Mensagens: AnsiString;
  PathArquivoXML,
  PathValidador: string;
  Schema: TpcnSchema;
  TipoLayout: TpcnTipoLayout;
  ApagarAposValidar: Boolean = False): Boolean;

implementation

function ValidarXML(var Mensagens: AnsiString; // Variavel que recebera as mensagens do validador
  PathArquivoXML, // Path e nome do arquivo xml a ser validado
  PathValidador: string; // Path aonde se encontra o .exe do validador
  Schema: TpcnSchema; // Schema que sera utilizada na valida��o
  TipoLayout: TpcnTipoLayout; // Tipo do layout do arquivo que sera validado
  ApagarAposValidar: Boolean = False): Boolean; // Indica se � para apagar o xml ap�s validar
var
  i: integer;
  ArquivoRetorno: TStringList;
  NomeArquivoRetorno: string;
begin
  Result := False;
  if not FileExists(PathArquivoXML) then
  begin
    Mensagens := Mensagens + 'O Arquivo [ ' + Trim(PathArquivoXML) + ' ] n�o foi encontrado!';
    exit;
  end;
  if pos(' ', PathValidador) > 0 then
  begin
    Mensagens := Mensagens + 'N�o � permitido usar nome de diret�rios com espa�o(s) em branco [ ' + Trim(PathValidador) + ' ]';
    exit;
  end;
  if pos(' ', PathArquivoXML) > 0 then
  begin
    Mensagens := Mensagens + 'N�o � permitido usar nome de diret�rios com espa�o(s) em branco [ ' + Trim(PathArquivoXML) + ' ]';
    exit;
  end;
  Randomize;
  i := Random(999999999);
  NomeArquivoRetorno := PathValidador + 'R' + IntToStr(i) + '.TXT';
  try
    WinExec(pAnsiChar(PathValidador + 'pcnValidadorNFe.exe ' +
      PathArquivoXML + ' ' +
      PathValidador + SchemaToStr(Schema) + '\ ' +
      NomeArquivoRetorno + ' ' +
      TipoLayoutToStr(TipoLayout)),
      sw_hide);
    while not FileExists(NomeArquivoRetorno) do begin end;
    sleep(500);
    ArquivoRetorno := TStringList.Create;
    ArquivoRetorno.LoadFromFile(NomeArquivoRetorno);
    Mensagens := ArquivoRetorno.Text;
    ArquivoRetorno.Free;
    DeleteFile(NomeArquivoRetorno);
    if ApagarAposValidar then
      DeleteFile(PathArquivoXML);
    Result := Mensagens = '';
  except
    Mensagens := Mensagens + 'N�o foi possivel validar o arquivo.';
    Result := False;
  end;
end;

end.
