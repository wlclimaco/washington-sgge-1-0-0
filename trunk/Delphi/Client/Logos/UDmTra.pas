unit UDmTra;

interface

uses
  SysUtils, Classes, DBClient, UCon0005, UMov0034, UMov0033, DB, UCon0023;

type
  TDmTra = class(TDataModule)
  private
    { Private declarations }
  public
    { Public declarations }
    function  TipoTransporte(pCpCidRem : String; pCpCidDes : String; pCpCidCon : String) : String;
    procedure VisualizarConhecimento(pCpParams : TClientDataSet);
    procedure ReprogramarDataEntrega(pCdEmpres : Integer; pCdTomado : Integer;
                                     pDtEntIni : String;  pDtEntFim : String;
                                     pCdCtrc   : Integer);
    procedure VisualizarConhecimentosVencidos(pCpParams : TClientDataSet; pSpParams : TDataSource);
    procedure VisualizarRNCDetalhada(pNrRnc : AnsiString);
  end;

var
  DmTra: TDmTra;

implementation

{$R *.dfm}

function TDmTra.TipoTransporte(pCpCidRem : String; pCpCidDes : String; pCpCidCon : String) : String;
begin
      if Trim(pCpCidCon) = '' then
      begin
            pCpCidCon := pCpCidRem;
      end;

      if (pCpCidRem = pCpCidDes) and (pCpCidRem = pCpCidCon) then
      begin
           Result := 'M';
      end else
      begin
           Result := 'I';
      end;
end;

procedure TDmTra.VisualizarConhecimento(pCpParams : TClientDataSet);
var lCon0005 : TCon0005;
begin
      if (pCpParams.RecordCount > 0) then
      begin
            try
                lCon0005 := TCon0005.Create(Self);
                lCon0005.CarregarDadosConhecimento(pCpParams.FieldByName('CdCtrc').AsString,
                                                   pCpParams.FieldByName('CdEmpres').AsString,
                                                   pCpParams.FieldByName('DsModeNF').AsString,
                                                   pCpParams.FieldByName('NrSeriNF').AsString);
            finally
                FreeAndNil(lCon0005);
            end;
      end;
end;

procedure TDmTra.ReprogramarDataEntrega(pCdEmpres : Integer; pCdTomado : Integer;
                                        pDtEntIni : String;  pDtEntFim : String;
                                        pCdCtrc   : Integer);
var lMov0034 : TMov0034;
begin
      try
          lMov0034  := TMov0034.Create(Self);
          lMov0034.CarregarFormShowModal(pCdEmpres, pCdTomado, pDtEntIni, pDtEntFim, pCdCtrc);
      finally
          FreeAndNil(lMov0034);
      end;
end;

procedure TDmTra.VisualizarConhecimentosVencidos(pCpParams : TClientDataSet; pSpParams: TDataSource);
var lMov0033 : TMov0033;
begin
      if (pCpParams.RecordCount > 0) then
      begin
            try
                lMov0033 := TMov0033.Create(Self);
                lMov0033.CarregarDados(pCpParams, pSpParams);
            finally
                FreeAndNil(lMov0033);
            end;
      end;
end;

procedure TDmTra.VisualizarRNCDetalhada(pNrRnc : AnsiString);
var lCon0023 : TCon0023;
begin
      try
          lCon0023 := TCon0023.Create(Self);
          lCon0023.CarregarDadosRNC(pNrRnc);
      finally
          FreeAndNil(lCon0023);
      end;
end;

end.
