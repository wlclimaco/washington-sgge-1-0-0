unit BrvConfConsulta;

interface

uses Classes, SysUtils;
//  Windows, Messages, SysUtils, , Graphics, Controls, Forms, Dialogs, Provider,
//  DbTables, VitalDicionario, UConsulta, Db, StdCtrls, Mask, VitalDataSet, SqlExpr,
//  VitalClientDataSet;

type
  TConfig = record
       DsReturn : String;
       DsValor  : String;
       SnAltera : String;
       SnChave  : String;
       DsDadOld : String;
  end;

  TBrvConfConsulta = class(TObject)
  private
     FCount  : Byte;
     FDdConfig  : array of TConfig;

  public
     constructor Create;
     destructor  Destroy;

     function    Count : Byte;
     procedure   IniciarArrayConfiguracao(DsConfig : TStrings);
//     function    DsValor(NrLinha DsConfig : TStrings);

  end;

implementation

constructor TBrvConfConsulta.Create;
begin
      inherited Create;
end;

destructor TBrvConfConsulta.Destroy;
begin
      inherited  Destroy;
end;

function TBrvConfConsulta.Count : Byte;
begin
      Result := FCount;
end;

procedure TBrvConfConsulta.IniciarArrayConfiguracao(DsConfig : TStrings);
Var NrLinha : Byte;
    DsLinha : String;
begin
      SetLength(FDdConfig, DsConfig.Count);
      FCount := DsConfig.Count;

      For NrLinha := 0 to DsConfig.Count - 1 do
      begin
            DsLinha := UpperCase(DsConfig.Strings[NrLinha]);

            FDdConfig[NrLinha].DsReturn := Copy(DsLinha, 1, Pos(';', DsLinha) - 1);
            Delete(DsLinha, 1, Pos(';', DsLinha));

            FDdConfig[NrLinha].DsValor  := Copy(DsLinha, 1, Pos(';', DsLinha) - 1);
            Delete(DsLinha, 1, Pos(';', DsLinha));

            FDdConfig[NrLinha].SnAltera := Copy(DsLinha, 1, Pos(';', DsLinha) - 1);
            Delete(DsLinha, 1, Pos(';', DsLinha));

            FDdConfig[NrLinha].SnChave  := Copy(DsLinha, 1, Pos(';', DsLinha) - 1);
            Delete(DsLinha, 1, Pos(';', DsLinha));

            FDdConfig[NrLinha].DsDadOld := '<%VAZIO%>'
      end;
end;


end.
 