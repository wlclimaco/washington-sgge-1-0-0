unit UConf;

interface

uses  SysUtils,DBClient, DB,StdCtrls;

function criarBcfApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarBclApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarDataAcessApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarValidationApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
implementation

function criarBcfApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('criarCodeClasseTest'+Txt+';');
end;
function criarBclApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('criarCodeClasseTest'+Txt+';');
end;
function criarDataAcessApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('criarCodeClasseTest'+Txt+';');
end;
function criarValidationApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('criarCodeClasseTest'+Txt+';');
end;
end.
