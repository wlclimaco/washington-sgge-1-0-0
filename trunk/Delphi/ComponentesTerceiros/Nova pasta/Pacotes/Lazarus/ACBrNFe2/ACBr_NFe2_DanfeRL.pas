{ This file was automatically created by Lazarus. Do not edit!
  This source is only used to compile and install the package.
 }

unit ACBr_NFe2_DanfeRL;

interface

uses
  ACBrNFeDANFeRL, ACBrNFeDANFeRLClass, ACBrNFeDANFeRLReg, 
  ACBrNFeDANFeRLRetrato, ACBrNFeDANFeRLPaisagem, ACBrNFeDANFeEventoRL, 
  ACBrNFeDANFeEventoRLRetrato, LazarusPackageIntf;

implementation

procedure Register;
begin
  RegisterUnit('ACBrNFeDANFeRLReg', @ACBrNFeDANFeRLReg.Register);
end;

initialization
  RegisterPackage('ACBr_NFe2_DanfeRL', @Register);
end.
