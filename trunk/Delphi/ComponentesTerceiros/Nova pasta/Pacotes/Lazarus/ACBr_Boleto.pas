{ This file was automatically created by Lazarus. Do not edit!
  This source is only used to compile and install the package.
 }

unit ACBr_Boleto;

interface

uses
  ACBrBoleto, ACBrBancoBradesco, ACBrBancoBrasil, ACBrBancoItau, 
  ACBrBancoSicredi, ACBrBancoMercantil, ACBrCaixaEconomica, ACBrBancoBanrisul, 
  ACBrBancoSantander, ACBrBancoob, ACBrCaixaEconomicaSICOB, ACBrBancoHSBC, 
  ACBrBanestes, ACBrBancoNordeste, ACBrBancoBRB, LazarusPackageIntf;

implementation

procedure Register;
begin
  RegisterUnit('ACBrBoleto', @ACBrBoleto.Register);
end;

initialization
  RegisterPackage('ACBr_Boleto', @Register);
end.
