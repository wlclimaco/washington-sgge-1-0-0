////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//              PCN - Projeto Cooperar NFe                                    //
//                                                                            //
//   Descri��o: Classes para gera��o/leitura dos arquivos xml da NFe          //
//                                                                            //
//        site: www.projetocooperar.org                                       //
//       email: projetocooperar@zipmail.com.br                                //
//       forum: http://br.groups.yahoo.com/group/projeto_cooperar_nfe/        //
//     projeto: http://code.google.com/p/projetocooperar/                     //
//         svn: http://projetocooperar.googlecode.com/svn/trunk/              //
//                                                                            //
// Coordena��o: (c) 2009 - Paulo Casagrande                                   //
//                                                                            //
//      Equipe: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
//      Vers�o: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
//     Licen�a: GNU Lesser General Public License (GNU LGPL)                  //
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

unit pcnEnvNFe;

interface uses
  SysUtils, Classes, pcnAuxiliar, pcnConversao, pcnGerador;

type

  //////////////////////////////////////////////////////////////////////////////
  //                                                                          //
  //    E M   D E S E N V O L V I M E N T O   -   N � O   T E S T A D O       //
  //                                                                          //
  //////////////////////////////////////////////////////////////////////////////

  TenvNFe = class(TPersistent)
  private
    FGerador: TGerador;
    FSchema: TpcnSchema;
    FidLote: string;
    FListaDeArquivos: TStringlist;
  public
    constructor Create;
    destructor Destroy; override;
    function GerarXML: boolean;
    function ObterNomeArquivo: string;
    function AdicionarArquivo(Path: string): Boolean;
  published
    property Gerador: TGerador read FGerador write FGerador;
    property Schema: TpcnSchema read FSchema write FSchema;
    property idLote: string read FidLote write FidLote;
    property ListaDeArquivos: TStringList read FListaDeArquivos write FListaDeArquivos;
  end;

implementation

{ TenvNFe }

constructor TenvNFe.Create;
begin
  FGerador := TGerador.Create;
  FListaDeArquivos := TstringList.Create;
end;

destructor TenvNFe.Destroy;
begin
  FGerador.Free;
  FListaDeArquivos.Free;
  inherited;
end;

function TenvNFe.ObterNomeArquivo: string;
begin
  Result := FidLote + '-env-lot.xml';
end;

function TenvNFe.AdicionarArquivo(Path: string): Boolean;
begin
  Result := False;
  if FListaDeArquivos.Count = 50 then
    exit;
  if not FileExists(Path) then
    exit;
  FListaDeArquivos.add(Path);
  Result := True;
end;

function TenvNFe.GerarXML: boolean;
var
  i: integer;
  XMLNFE: TStringList;
begin
  Result := False;
  if RetornarVersaoLayout(FSchema, tlenvNFe) = '1.10' then
  begin
    Gerador.ArquivoFormatoXML := '';
    Gerador.wGrupo(ENCODING_UTF8_STD, '', False);
    Gerador.wGrupo('enviNFe ' + NAME_SPACE + ' ' + V1_10);
    Gerador.wCampo(tcStr, 'AP03', 'idLote', 001, 015, 1, FIdLote, DSC_IdLote);
    for i := 0 to FlistaDeArquivos.count - 1 do
    begin
      XMLNFE := TStringList.create;
      if not FileExists(FListaDeArquivos[i]) then
      begin
        Gerador.wAlerta('AP04', 'NFE', 'NFE', ERR_MSG_ARQUIVO_NAO_ENCONTRADO);
      end
      else
        XMLNFE.LoadFromFile(FListaDeArquivos[i]);
      Gerador.wTexto('<NFe xmlns' + RetornarConteudoEntre(XMLNFE.Text, '<NFe xmlns', '</NFe>') + '</NFe>');
      XMLNFE.Free;
    end;
    Gerador.wGrupo('/enviNFe');
    Result := (Gerador.ListaDeAlertas.Count = 0);
  end;
end;

end.

