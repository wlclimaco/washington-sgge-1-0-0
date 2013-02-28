unit Unit2;

interface

uses
  SysUtils, Classes, DB, DBTables, IBDatabase;

type
  TDataModule2 = class(TDataModule)
    Database1: TDatabase;
    tblprodutos: TTable;
    tblClientes: TTable;
    tblEMPREGAD: TTable;
    tblENDERECO: TTable;
    tblLOJAS: TTable;
    tblClientesCODCLI: TFloatField;
    tblClientesSITUACAO: TStringField;
    tblClientesSTATUS: TStringField;
    tblClientesRAZAO: TStringField;
    tblClientesCOM: TStringField;
    tblClientesSIGLA: TStringField;
    tblClientesFANTASIA: TStringField;
    tblClientesCGC: TStringField;
    tblClientesINCR: TStringField;
    tblClientesENDERECO: TStringField;
    tblClientesBAIRRO: TStringField;
    tblClientesMUNICIPIO: TStringField;
    tblClientesUF: TStringField;
    tblClientesCEP: TStringField;
    tblClientesFONE: TStringField;
    tblClientesFONEFAX: TStringField;
    tblClientesFONECEL: TStringField;
    tblClientesCONTATO: TStringField;
    tblClientesEMAIL: TStringField;
    tblClientesSITE: TStringField;
    tblClientesOBS: TMemoField;
    tblEMPREGADCODEMP: TFloatField;
    tblEMPREGADNOME: TStringField;
    tblEMPREGADCARGO: TStringField;
    tblEMPREGADSIGLA: TStringField;
    tblEMPREGADCOMISSAO: TFloatField;
    tblENDERECOCODCLI: TFloatField;
    tblENDERECOCODIGO: TFloatField;
    tblENDERECOSITUACAO: TStringField;
    tblENDERECORAZAO: TStringField;
    tblENDERECOENDERECO: TStringField;
    tblENDERECOBAIRRO: TStringField;
    tblENDERECOMUNICIPIO: TStringField;
    tblENDERECOUF: TStringField;
    tblENDERECOCEP: TStringField;
    tblENDERECOFONE: TStringField;
    tblENDERECOFONEFAX: TStringField;
    tblENDERECOFLAG_EXCL: TStringField;
    tblLOJASCODLOJ: TFloatField;
    tblLOJASLOJA: TStringField;
    tblLOJASSIGLA: TStringField;
    tblprodutosCODPRO: TFloatField;
    tblprodutosPRODUTO: TStringField;
    tblprodutosREF: TStringField;
    tblprodutosCLASS: TStringField;
    tblprodutosUNIDADE: TStringField;
    tblprodutosLOCAL: TStringField;
    tblprodutosCODIND: TFloatField;
    tblprodutosINDICE: TStringField;
    tblprodutosVALOR: TFloatField;
    tblprodutosCODCLI: TFloatField;
    tblprodutosRAZAO: TStringField;
    tblprodutosAQUIS: TFloatField;
    tblprodutosICMS: TFloatField;
    tblprodutosIPI: TFloatField;
    tblprodutosFINAN: TFloatField;
    tblprodutosFRETE: TFloatField;
    tblprodutosCUSTO: TFloatField;
    tblprodutosPRAZO: TSmallintField;
    dsCLIENTES: TDataSource;
    dsEMPREGAD: TDataSource;
    dsENDERECO: TDataSource;
    dsLOJAS: TDataSource;
    dsPRODUTOS: TDataSource;
    QryProdutos: TQuery;
    qryprodutos1: TQuery;
    qrypreco: TQuery;
    qryclientes: TQuery;
    qryvendas: TQuery;
    qryenderecos: TQuery;
    Query6: TQuery;
    Query7: TQuery;
    DsQryProd: TDataSource;
    Query1: TQuery;
    tblnfentradas: TTable;
    Dsnfentradas: TDataSource;
    tblnfentradasDcnumero: TFloatField;
    tblnfentradasDcserie: TStringField;
    tblnfentradasDcordem: TStringField;
    tblnfentradasDctipo: TStringField;
    tblnfentradasVlnota: TCurrencyField;
    tblnfentradasVlicms: TCurrencyField;
    tblnfentradasVlipi: TCurrencyField;
    tblnfentradasVlfrete: TCurrencyField;
    tblnfentradasCdfornecedor: TFloatField;
    tblnfentradasCfop: TFloatField;
    tblnfentradasDtentrada: TDateField;
    tblnfentradasDtemissao: TDateField;
    tblnfentradasTransportado: TFloatField;
    tblnfentradasCdpedido: TFloatField;
    tblnfentradasNatureza: TFloatField;
    tblnfentradasIdnfentradas: TIntegerField;
    tblnfentradasVlst: TCurrencyField;
    tblnfentradasTpsituacao: TStringField;
    Tbtitulospagar: TTable;
    dstitulospagar: TDataSource;
    qryMovEstoque: TQuery;
    qrymovestoque2: TQuery;
    QYBAIXAS: TQuery;
    Query2: TQuery;
    DsQryCliente: TDataSource;
    DataSource2: TDataSource;
    Query3: TQuery;
    Query4: TQuery;
    Query5: TQuery;
    Query8: TQuery;
    tblprodutosPRECOVENDA: TFloatField;
    tblprodutosSTATUS: TStringField;
    tblprodutosESTOQUE: TFloatField;
    tblprodutosESTOQUEANT: TFloatField;
    tblnatureza: TTable;
    dsNatureza: TDataSource;
    tblnaturezaNatureza: TFloatField;
    tblnaturezaDescricao: TStringField;
    Query9: TQuery;
    tbletiques: TTable;
    dsetiquetas: TDataSource;
    tbletiquesCodpro: TFloatField;
    tbletiquesProduto: TStringField;
    tbletiquesPreco: TCurrencyField;
    tbletiquesQuantidade: TFloatField;
    tbletiquesImprime: TStringField;
    qrytitulospagar: TQuery;
    dsQrytitulospagar: TDataSource;
    dsQUERY9: TDataSource;
    TbtitulospagarDcnumero: TFloatField;
    TbtitulospagarDcserie: TStringField;
    TbtitulospagarDcordem: TStringField;
    TbtitulospagarDctipo: TStringField;
    TbtitulospagarParcela: TFloatField;
    TbtitulospagarStatus: TStringField;
    TbtitulospagarTpsituacao: TStringField;
    TbtitulospagarVlparcela: TFloatField;
    TbtitulospagarFornecedor: TFloatField;
    TbtitulospagarObs: TStringField;
    TbtitulospagarDATAPAGAMENTO: TDateField;
    tbletiquesCodcli: TStringField;
    TbtitulospagarDtlancamento: TDateField;
    IBDatabase1: TIBDatabase;
    IBTransaction1: TIBTransaction;

  private
    { Private declarations }
  public
  function baixarestoque(const cdproduto : integer;
                                    custo : String;
                               quantidade: real ): boolean;
 function aumentarestoque(const cdproduto : integer;
                                    custo :String;
                               quantidade: real ): boolean;

 function ajustadata(const strdata : String): String;
 function ajustaVirgula(const frase : String): String;

  end;

var
  DataModule2: TDataModule2;

implementation

//uses Unit2;

{$R *.dfm}
function  TDataModule2.baixarestoque(const cdproduto : integer;
                                       custo : String;
                                  quantidade: real ): boolean;
 var
  estqatual,estoque : double;
 begin
    try
    DataModule2.qryprodutos1.Active := false;
    DataModule2.qryMovEstoque.SQL.Text := 'select *from produtos where codpro = '+IntToStr(cdproduto);
    DataModule2.qryMovEstoque.Active := true;
    estqatual := DataModule2.qryMovEstoque.FieldbyName('estoque').AsInteger;
    estoque := estqatual - quantidade;
    DataModule2.qryMovEstoque.Active := false;
    DataModule2.qryMovEstoque.SQL.Text := 'update produtos set estoque = '+FloatToStr(estoque)+',estoqueant = '+FloatToStr(estqatual)+', custo = '+custo+' where codpro = '+IntToStr(cdproduto) ;
    DataModule2.qryMovEstoque.ExecSQL;
   // DataModule2.qryMovEstoque.Active := false;
   // DataModule2.qryMovEstoque.SQL.Text := 'update produtos set estoque = '+FloatToStr(estoque)+',estoqueant = '+FloatToStr(estqatual)+', custo = '+custo+' where codpro = '+IntToStr(cdproduto) ;
   // DataModule2.qryMovEstoque.ExecSQL;
   finally
    Result := false;

  end;
 end;

function  TDataModule2.aumentarestoque(const cdproduto : integer;
                                       custo : String;
                                  quantidade: real ): boolean;
var
  estqatual,estoque : double;
 begin
    DataModule2.qryprodutos1.Active := false;
    DataModule2.qryMovEstoque.SQL.Text := 'select *from produtos where codpro = '+IntToStr(cdproduto);
    DataModule2.qryMovEstoque.Active := true;
    estqatual := DataModule2.qryMovEstoque.FieldbyName('estoque').AsInteger;
    estoque := estqatual + quantidade;
    DataModule2.qryMovEstoque.Active := false;
    DataModule2.qryMovEstoque.SQL.Text := 'update produtos set estoque = '+FloatToStr(estoque)+',estoqueant = '+FloatToStr(estqatual)+', custo = '+DataModule2.ajustaVirgula(custo)+' where codpro = '+IntToStr(cdproduto) ;
    DataModule2.qryMovEstoque.ExecSQL;
    //DataModule2.qryMovEstoque.Active := True;
   Result := true;
  end;

  function  TDataModule2.ajustadata(const strdata : String): String;
  var x: integer;
    ano,mes,dia,aux: String;
  begin
    ano := Copy(strdata,0,4);
    mes := Copy(strdata,5,2);
    dia := Copy(strdata,7,2);
    aux := ''+dia+'/'+mes+'/'+ano ;
    result := aux;
  end;
  function  TDataModule2.ajustaVirgula(const frase : String): String;
  var x: integer;
     teste : string;
  begin
    x:= 0;
    teste := frase;
    while (x < 10  )do
    begin
      if(teste[x] = ',') then
        teste[x] := '.';
        x := x + 1;
    end;
    Result := teste;

  end;
end.
