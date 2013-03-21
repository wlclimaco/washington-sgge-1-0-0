unit Unit3;


interface

uses  SysUtils,DBClient, DB,StdCtrls;

function criarGerarProcedureInsert(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureUpdate(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureDelete(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureSelect(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarGerarProcedureSelectById(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptBDInsert(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptBDTable(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptBDAtributos(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function GerarScriptBDValidators(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
implementation

uses BrvFuncoesXE;

implementation

end.
