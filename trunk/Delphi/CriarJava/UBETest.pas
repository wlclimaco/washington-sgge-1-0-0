unit UBETest;

interface

uses  SysUtils,DBClient, DB,StdCtrls;

function criarClasseBCFTest(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarClasseBCFIMock(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarClasseBCLTest(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarClasseBCLIMock(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarClasseDACTest(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarClasseDACMock(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
implementation

function criarClasseBCFTest(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('criarCodeClasseTest'+Txt+';');
end;
function criarClasseBCFIMock(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('criarClasseBCFIMock'+Txt+';');
end;
function criarClasseBCLTest(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('criarClasseBCLTest'+Txt+';');
end;
function criarClasseBCLIMock(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('criarClasseBCLIMock'+Txt+';');
end;
function criarClasseDACTest(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('criarClasseDACTest'+Txt+';');
end;
function criarClasseDACMock(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('criarClasseDACMock'+Txt+';');
end;

end.
