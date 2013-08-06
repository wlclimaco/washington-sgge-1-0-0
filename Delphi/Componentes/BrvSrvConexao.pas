unit BrvSrvConexao;

interface

uses
  SysUtils, Classes, IdHTTP, BrvString;

type
  TBrvSrvConexao = class(TComponent)
  private
    { Private declarations }
    gNoIpInter : String;
    gNoIpExter : String;
    gNoPorta   : Integer;
    gDsEndSit  : String;
  protected
    { Protected declarations }
  public
    { Public declarations }
    procedure Execute;
    function Post(pDsParams: TStrings): AnsiString;
  published
    { Published declarations }
    property BrEnderecoSite : String  read gDsEndSit  write gDsEndSit;
    property BrIpInterno    : String  read gNoIpInter write gNoIpInter;
    property BrIpExterno    : String  read gNoIpExter write gNoIpExter;
    property BrNumeroPorta  : Integer read gNoPorta   write gNoPorta;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo rede', [TBrvSrvConexao]);
end;

{ TBrvSrvConexao }

procedure TBrvSrvConexao.Execute;
var WwwConexa : TIdHttp;
    lDsResult : String;
    BrvString : TBrvString;
begin
      try
          if gDsEndSit = '' then
          begin
                raise Exception.Create('Endereço do site não foi informado.');
          end;

          WwwConexa := TIdHttp.Create(Self);
          BrvString := TBrvString.Create(Self);

          lDsResult := WwwConexa.Post(gDsEndSit + 'GetIp.php', BrvString.BrSplitLista);
          WwwConexa.Disconnect;
          BrvString.Split(lDsResult,'<BR>');
          gNoIpInter := BrvString.BrSplitLista.Strings[0];

          if BrvString.BrSplitLista.count > 1 then
          begin
                gNoIpExter := BrvString.BrSplitLista.Strings[1];
                gNoPorta   := StrToInt(BrvString.BrSplitLista.Strings[2]);
          end;
      finally
          FreeAndNil(WwwConexa);
          FreeAndNil(BrvString);
      end;
end;

function TBrvSrvConexao.Post(pDsParams: TStrings): AnsiString;
var lWwwConexa : TIdHttp;
begin
      try
          if gDsEndSit = '' then
          begin
                raise Exception.Create('Endereço do site não foi informado.');
          end;

          lWwwConexa := TIdHttp.Create(Self);
          Result  := lWwwConexa.Post(gDsEndSit + 'PutIp.php', pDsParams);
      finally
          FreeAndNil(lWwwConexa);
      end;
end;

end.
