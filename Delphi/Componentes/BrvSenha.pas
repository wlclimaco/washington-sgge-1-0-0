unit BrvSenha;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs;

type
  TBrvSenha = class(TComponent)
  private
    { Private declarations }
    FDsLogin  : String;
    FDsSenha  : String;
    FNrCodigo : Integer;
    procedure SetGerarCodigoLogin(Value : String);
    procedure SetGerarCodigoSenha(Value : String);
    procedure GerarCodigo;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner : TComponent); override;
    destructor  Destroy;                     override;
  published
    { Published declarations }
    property Login  : String  read FDsLogin  write SetGerarCodigoLogin;
    property Senha  : String  read FDsSenha  write SetGerarCodigoSenha;
    property Codigo : Integer read FNrCodigo write FNrCodigo;
  end;

procedure Register;

implementation

constructor TBrvSenha.Create(AOwner : TComponent);
begin
      inherited Create(AOwner);
end;

destructor TBrvSenha.Destroy;
begin
      inherited  destroy;
end;

procedure TBrvSenha.SetGerarCodigoLogin(Value : String);
begin
      FDsLogin := Value;
      GerarCodigo;
end;

procedure TBrvSenha.SetGerarCodigoSenha(Value : String);
begin
      FDsSenha := Value;
      GerarCodigo;
end;

procedure TBrvSenha.GerarCodigo;
var  NrLogin  : Integer;
     NrSenha  : Integer;
     NrCaract : Integer;
begin
      NrLogin := 0;
      NrSenha := 0;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=- Processando a descrição do login =-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      for NrCaract := 1 to Length(FDsLogin) do
      begin
            NrLogin := NrLogin + (Ord(FDsLogin[NrCaract]) * NrCaract)
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=- Processando a descrição da senha =-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      for NrCaract := 1 to Length(FDsSenha) do
      begin
            NrSenha := NrSenha + (Ord(FDsSenha[NrCaract]) * NrCaract)
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=- Retornando o valor da senha -=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      FNrCodigo := NrLogin + NrSenha;
end;

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvSenha]);
end;

end.

