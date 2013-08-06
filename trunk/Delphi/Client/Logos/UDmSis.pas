unit UDmSis;

interface

uses
  SysUtils, Classes;

type
  TDmSis = class(TDataModule)
  private
    { Private declarations }
  public
    { Public declarations }
    function TipoColuna(pTpColuna, pTmColuna: String): String;
    function FormularioAberto(pNmFormul: String; var pNrFormul: Integer): Boolean;
  end;

var
  DmSis: TDmSis;

implementation

uses ULogos;

{$R *.dfm}

function TDmSis.TipoColuna(pTpColuna : String; pTmColuna : String) : String;
begin
      if pTpColuna = 'D' then
      begin
            Result := ' TimeStamp ';
      end
      else if pTpColuna  = 'I' then
           begin
                 Result := ' Integer';
           end
      else if pTpColuna  = 'B' then
           begin
                 Result := ' Blob Sub_Type 1 ';
           end
      else if pTpColuna  = 'N' then
           begin
                 Result := ' Numeric(15,' +  pTmColuna  + ') ';
           end
      else if pTpColuna  = 'V' then
           begin
                 Result := ' VarChar(' + pTmColuna + ')';
           end
      else if pTpColuna  = 'L' then
           begin
                 Result := ' Blob ';
           end
      ;
end;

function TDmSis.FormularioAberto(pNmFormul : String; var pNrFormul : Integer) : Boolean;
begin
      Result := False;
      pNrFormul := 0;

      while (pNrFormul < FrmLogos.MDIChildCount) and (not Result) do
      begin
            if (UpperCase(FrmLogos.MDIChildren[pNrFormul].Name) =
                                                               UpperCase(pNmFormul)) then
            begin
                  FrmLogos.MDIChildren[pNrFormul].Show;
                  Result := True;
            end else
            begin
                  inc(pNrFormul);
            end;
      end;
end;

end.
