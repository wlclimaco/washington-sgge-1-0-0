{ This file was automatically created by Lazarus. Do not edit!
  This source is only used to compile and install the package.
 }

unit ACBrTCP;

interface

uses
  ACBrSocket, ACBrCEP, ACBrTCPReg, ACBrIBGE, ACBrCNIEE, ACBrSuframa, 
  ACBrDownload, ACBrDownloadClass, ACBrNFPws, LazarusPackageIntf;

implementation

procedure Register;
begin
  RegisterUnit('ACBrTCPReg', @ACBrTCPReg.Register);
end;

initialization
  RegisterPackage('ACBrTCP', @Register);
end.
