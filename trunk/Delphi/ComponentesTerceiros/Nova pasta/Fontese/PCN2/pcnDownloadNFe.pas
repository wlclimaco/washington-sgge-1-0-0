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
///
unit pcnDownloadNFe;

interface uses

  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao, pcnGerador;

type
  TDownloadNFe = class ;
  TChavesCollection  = class ;
  TChavesCollectionItem = class ;

  TChavesCollection = class(TCollection)
  private
    function GetItem(Index: Integer): TChavesCollectionItem;
    procedure SetItem(Index: Integer; Value: TChavesCollectionItem);
  public
    constructor Create(AOwner: TPersistent);
    function Add: TChavesCollectionItem;
    property Items[Index: Integer]: TChavesCollectionItem read GetItem write SetItem; default;
  end;

  TChavesCollectionItem = class(TCollectionItem)
  private
    FchNFe: String;
  published
    property chNFe: String read FchNFe write FchNFe;
  end;

  TDownloadNFe = class(TPersistent)
  private
    FGerador: TGerador;
    FSchema: TpcnSchema;
    FtpAmb: TpcnTipoAmbiente;
    FCNPJ: String;
    FChaves: TChavesCollection;
    FVersao: String;
    procedure SetChaves(const Value: TChavesCollection);
  public
    constructor Create;
    destructor Destroy; override;
    function GerarXML: boolean;
    function ObterNomeArquivo: string;
  published
    property Gerador: TGerador          read FGerador write FGerador;
    property schema: TpcnSchema         read Fschema  write Fschema;
    property tpAmb: TpcnTipoAmbiente    read FtpAmb   write FtpAmb;
    property CNPJ: String               read FCNPJ    write FCNPJ;
    property Chaves: TChavesCollection  read FChaves  write SetChaves;
    property Versao: String             read FVersao  write FVersao;
  end;

implementation

{ TChavesCollection }

function TChavesCollection.Add: TChavesCollectionItem;
begin
  Result := TChavesCollectionItem(inherited Add);
end;

constructor TChavesCollection.Create(AOwner: TPersistent);
begin
  inherited Create(TChavesCollectionItem);
end;

function TChavesCollection.GetItem(Index: Integer): TChavesCollectionItem;
begin
  Result := TChavesCollectionItem(inherited GetItem(Index));
end;

procedure TChavesCollection.SetItem(Index: Integer;
  Value: TChavesCollectionItem);
begin
  inherited SetItem(Index, Value);
end;

{ TDownloadNFe }

constructor TDownloadNFe.Create;
begin
  FGerador := TGerador.Create;
  FChaves  := TChavesCollection.Create(Self);
end;

destructor TDownloadNFe.Destroy;
begin
  FGerador.Free;
  FChaves.Free;
  inherited;
end;

function TDownloadNFe.ObterNomeArquivo: string;
var
  DataHora: TDateTime;
  Year, Month, Day, Hour, Min, Sec, Milli: Word;
  AAAAMMDDTHHMMSS: string;
begin
  Datahora := now;
  DecodeTime(DataHora, Hour, Min, Sec, Milli);
  DecodeDate(DataHora, Year, Month, Day);
  AAAAMMDDTHHMMSS := IntToStrZero(Year, 4) + IntToStrZero(Month, 2) + IntToStrZero(Day, 2) +
    IntToStrZero(Hour, 2) + IntToStrZero(Min, 2) + IntToStrZero(Sec, 2);
  Result := AAAAMMDDTHHMMSS + '-ped-down-nfe.xml';
end;

procedure TDownloadNFe.SetChaves(const Value: TChavesCollection);
begin
  FChaves.Assign(Value);
end;

function TDownloadNFe.GerarXML: boolean;
var
 i : integer;
 sDoc: String;
begin
  Result := False;

//  if RetornarVersaoLayout(FSchema, tlDownloadNFe) = '2.00' then
//   begin

     Gerador.ArquivoFormatoXML := '';
//     Gerador.wGrupo('downloadNFe ' + NAME_SPACE + ' ' + V1_00);
     Gerador.wGrupo('downloadNFe ' + NAME_SPACE + ' versao="' + Versao + '"');
     Gerador.wCampo(tcStr, 'JP03', 'tpAmb', 001, 001, 1, tpAmbToStr(FtpAmb), DSC_TPAMB);
     Gerador.wCampo(tcStr, 'JP04', 'xServ', 012, 012, 1, 'DOWNLOAD NFE', DSC_XSERV);

     sDoc := SomenteNumeros( FCNPJ );
     Gerador.wCampo(tcStr, 'JP05', 'CNPJ', 014, 014, 1, sDoc , DSC_CNPJ);
     if not ValidarCNPJ( sDoc ) then Gerador.wAlerta('JP05', 'CNPJ', DSC_CNPJ, ERR_MSG_INVALIDO);

     for i := 0 to Chaves.Count - 1 do
      begin
        Gerador.wCampo(tcStr, 'JP06', 'chNFe', 044, 044,  1, Chaves.Items[i].chNFe);
      end;
     if Chaves.Count > 10 then
      Gerador.wAlerta('JP06', 'chNFe', DSC_NITEM, ERR_MSG_MAIOR_MAXIMO + '10');
     if Chaves.Count < 1 then
      Gerador.wAlerta('JP06', 'chNFe', DSC_NITEM, ERR_MSG_MENOR_MINIMO + '1');

     Gerador.wGrupo('/downloadNFe');

     Result := (Gerador.ListaDeAlertas.Count = 0);

//   end
end;

end.
