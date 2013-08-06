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

unit pcnCadEmiDFe;

interface uses
  SysUtils, Classes, pcnAuxiliar, pcnConversao, pcnGerador;

type

  //////////////////////////////////////////////////////////////////////////////
  //                                                                          //
  //    E M   D E S E N V O L V I M E N T O   -   N � O   T E S T A D O       //
  //                                                                          //
  //////////////////////////////////////////////////////////////////////////////

  TCadEmiDFe = class;
  TDFeCollectionItem = class;
  TEmissorCollection = class;
  TEmissorCollectionItem = class;

  TDFeCollection = class(TCollection)
  private
    function GetItem(Index: Integer): TDFeCollectionItem;
    procedure SetItem(Index: Integer; Value: TDFeCollectionItem);
  public
    constructor Create(AOwner: TCadEmiDFe); reintroduce;
    function Add: TDFeCollectionItem;
    property Items[Index: Integer]: TDFeCollectionItem read GetItem write
    SetItem; default;
  end;

  TDFeCollectionItem = class(TCollectionItem)
  private
    FModelo: Integer;
    FSit: TpcnTipoAmbiente;
  published
    property Modelo: Integer read FModelo write FModelo;
    property Sit: TpcnTipoAmbiente read FSit write FSit;
  end;

  TEmissorCollection = class(TCollection)
  private
    function GetItem(Index: Integer): TEmissorCollectionItem;
    procedure SetItem(Index: Integer; Value: TEmissorCollectionItem);
  public
    constructor Create(AOwner: TCadEmiDFe); reintroduce;
    function Add: TEmissorCollectionItem;
    property Items[Index: Integer]: TEmissorCollectionItem read GetItem write
    SetItem; default;
  end;

  TEmissorCollectionItem = class(TCollectionItem)
  private
    FUF: string;
    FCNPJEmissor: string;
    FCNPJMatriz: string;
    FIEEmissor: string;
    FPaisMatriz: Integer;
    FPaisEmissor: Integer;
    FDfe: TDFeCollection;
    procedure SetDFe(const Value: TDFeCollection);
  public
    constructor Create; reintroduce;
    destructor Destroy; override;
  published
    property UF: string read FUF write FUF;
    property CNPJEmissor: string read FCNPJEmissor write FCNPJEmissor;
    property CNPJMatriz: string read FCNPJMatriz write FCNPJMatriz;
    property IEEmissor: string read FIEEmissor write FIEEmissor;
    property PaisEmissor: Integer read FPaisEmissor write FPaisEmissor;
    property PaisMatriz: Integer read FPaisMatriz write FPaisMatriz;
    property DFe: TDFeCollection read FDfe write SetDFe;
  end;

  TCadEmiDFe = class(TPersistent)
  private
    FGerador: TGerador;
    FDPublic: TDateTime;
    FchNFe: string;
    FEmissor: TEmissorCollection;
    procedure SetEmissor(const Value: TEmissorCollection);
    procedure GeraDFE(const vN: Integer);
  public
    constructor Create;
    destructor Destroy; override;
    function GerarXML: boolean;
  published
    property Gerador: TGerador read FGerador write FGerador;
    property chNFe: string read FchNFe write FchNFe;
    property DPublic: TDateTime read FDPublic write FDPublic;
    property Emissor: TEmissorCollection read FEmissor write SetEmissor;

  end;

implementation

{ TCadEmiDFe }

constructor TCadEmiDFe.Create;
begin
  FGerador := TGerador.Create;
  FEmissor := TEmissorCollection.Create(Self);
end;

destructor TCadEmiDFe.Destroy;
begin
  FGerador.Free;
  Emissor.Free;
  inherited;
end;

function TCadEmiDFe.GerarXML: boolean;
var
  i: integer;
begin
  Gerador.ArquivoFormatoXML := '';
  Gerador.wGrupo(ENCODING_UTF8, '',False);
  Gerador.wGrupo('cadEmiDFe ' + NAME_SPACE + ' ' + V1_07);
  Gerador.wGrupo('infCadEmiDFe Id = "ID' + SomenteNumeros(FchNFe) + '"');
  Gerador.wCampo(tcDat, 'O05', 'dPubCad', 010, 010, 1, FDPublic, DSC_DEMI);
  for i := 0 to Emissor.Count - 1 do
  begin
    Gerador.wGrupo('emissor');
    Gerador.wCampo(tcStr, 'O07', 'UF', 002, 002, 1, Emissor[i].FUF, DSC_UF);
    if not ValidarUF(Emissor[i].FUF) then
      Gerador.wAlerta('O07', 'UF', DSC_UF, ERR_MSG_INVALIDO);
    Gerador.wCampoCNPJCPF('O08', 'CNPJ', Emissor[i].FCNPJEmissor, Emissor[i].FPaisEmissor);
    Gerador.wCampoCNPJCPF('O09', 'CNPJMatriz', Emissor[i].FCNPJMatriz, Emissor[i].FPaisMatriz);
    Gerador.wCampo(tcStr, 'O10', 'IE', 002, 014, 1, Emissor[i].IEEmissor, DSC_IE);
    GeraDFe(I);
    Gerador.wGrupo('/emissor');
  end;
  Gerador.wGrupo('/infCadEmiDFe');
  Gerador.wGrupo('Signature Id = "ID' + SomenteNumeros(FchNFe) + '"');
  Gerador.wGrupo('/Signature');
  Gerador.wGrupo('/cadEmiDFe');
  Result := (Gerador.ListaDeAlertas.Count = 0);
end;

procedure TCadEmiDFe.GeraDFE(const vN: Integer);
var
  i: Integer;
begin
  i := 0;
  for i := 0 to Emissor[i].DFe.Count - 1 do
  begin
    Gerador.wGrupo('DFe');
    Gerador.wCampo(tcInt, 'O11a', 'mod', 002, 002, 1, Emissor[vN].DFe[i].FModelo, DSC_MOD);
    Gerador.wCampo(tcStr, 'O11b', 'sit', 001, 001, 1, tpAmbToStr(Emissor[vN].DFe[i].FSit), DSC_TPAMB);
    Gerador.wGrupo('/DFe');
  end;
end;

procedure TCadEmiDFe.SetEmissor(const Value: TEmissorCollection);
begin
  FEmissor := Value;
end;

{ TDFeCollection }

function TDFeCollection.Add: TDFeCollectionItem;
begin
  Result := TDFeCollectionItem(inherited Add);
end;

constructor TDFeCollection.Create(AOwner: TCadEmiDFe);
begin
  inherited Create(TDFeCollectionItem);
end;

function TDFeCollection.GetItem(Index: Integer): TDFeCollectionItem;
begin
  Result := TDFeCollectionItem(inherited GetItem(Index));
end;

procedure TDFeCollection.SetItem(Index: Integer; Value: TDFeCollectionItem);
begin
  inherited SetItem(Index, Value);
end;

{ TEmissorCollection }

function TEmissorCollection.Add: TEmissorCollectionItem;
begin
  Result := TEmissorCollectionItem(inherited Add);
  Result.create;
end;

constructor TEmissorCollection.Create(AOwner: TCadEmiDFe);
begin
  inherited Create(TEmissorCollectionItem);
end;

function TEmissorCollection.GetItem(Index: Integer): TEmissorCollectionItem;
begin
  Result := TEmissorCollectionItem(inherited GetItem(Index));
end;

procedure TEmissorCollection.SetItem(Index: Integer;
  Value: TEmissorCollectionItem);
begin
  inherited SetItem(Index, Value);
end;

{ TEmissorCollectionItem }

constructor TEmissorCollectionItem.Create;
begin
  FDfe := TDFeCollection.Create(nil);
end;

destructor TEmissorCollectionItem.Destroy;
begin
  FDfe.Free;
  inherited;
end;

procedure TEmissorCollectionItem.SetDFe(const Value: TDFeCollection);
begin
  FDfe.Assign(Value);
end;

end.

