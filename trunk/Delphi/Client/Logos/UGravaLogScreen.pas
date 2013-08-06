unit UGravaLogScreen;

interface

uses
  Classes, UDmSrvApl, UClaSrv, SqlExpr, DBClient, SysUtils;

type
  TGravaLogScreen = class(TThread)
  private
    { Private declarations }
    gCdUsuari : Integer;
    gVrLogScr : Variant;
    gConexao  : TSQLConnection;
    gDsCreden : String;
    gNmFormul : String;
    gCdsLog   : Variant;
    gDsPath   : String;
  protected
  public
    procedure Execute; override;

    constructor Create(pCdUsuari : Integer;
                       pVrLogScr : Variant;
                       pNmFormul : String;
                       pDsCreden : String;
                       pDsPath   : String;
                       pConexao  : TSQLConnection);
  end;

implementation

{ GravaLogScreen }

constructor TGravaLogScreen.Create(pCdUsuari: Integer; pVrLogScr: Variant;
                                                                        pNmFormul : String;
                                                                        pDsCreden : String;
                                                                        pDsPath   : String;
                                                                        pConexao  : TSQLConnection);
begin
      gCdUsuari := pCdUsuari;
      gVrLogScr := pVrLogScr;
      gDsCreden := pDsCreden;
      gConexao  := pConexao;
      gNmFormul := pNmFormul;
      gDsPath   := pDsPath;

      FreeOnTerminate := True;
      inherited Create(True);
end;

procedure TGravaLogScreen.Execute;
var lConexao  : TSDmSisClient;
    lDsResult : String;
    lCcLogScr : TClientDataSet;
begin
      lConexao := TSDmSisClient.Create(gConexao.DBXConnection);
      lConexao.AtualizarLogScreen(gDsCreden, lDsResult, gCdUsuari, gNmFormul,
                                  FormatDateTime('yyyymmddhhmm', Now()), gVrLogScr);

      if (Copy(lDsResult, 1, 1) = '1') then
      begin
            try
                lCcLogScr := TClientDataSet.Create(nil);

                ForceDirectories(gDsPath + FormatFloat('0', gCdUsuari));

                lCcLogScr.SaveToFile(gDsPath + FormatFloat('0', gCdUsuari) + '\' +
                                               FormatFloat('0', gCdUsuari) +
                                                                           '_'+ gNmFormul + '.bin');
            finally
                FreeAndNil(lCcLogScr);
            end;
      end;

end;

end.
