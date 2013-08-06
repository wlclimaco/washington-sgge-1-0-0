unit URELTR0001;

interface

uses
  Classes, Printers, SysUtils;

type
  RELTR0001 = class(TThread)
  private
    { Private declarations }
  public
    NrCopias : Integer;
    NmImpres : String;
    NrImpres : Integer;
    TxRelato : TStrings;
    SnConden : Boolean;
    constructor Create(CreateSuspended: Boolean); virtual;
    destructor Destroy; override;
  protected
    procedure Execute; override;
  end;

implementation

{ RELTR0001 }

constructor RELTR0001.Create(CreateSuspended: Boolean);
begin
      inherited Create(CreateSuspended);
      
      TxRelato := TStringList.Create;
end;

destructor RELTR0001.Destroy;
begin
      TxRelato.Destroy;
      
      inherited  destroy;
end;

procedure RELTR0001.Execute;
var NrCopia  : Integer;
    DsConfig : String;
begin
      for NrCopia := 1 to NrCopias do
      begin
            DsConfig := '';

            if SnConden then
            begin
                  if Pos('XEROX', UpperCase(NmImpres)) > 0 then
                  begin
                        DsConfig := '(s18H';
                  end else
                  begin
                        DsConfig := #27 + #120 + '0' + #27 + #15;//transforma em draft e compacta
                  end;
            end;

            if Trim(DsConfig) <> '' then
            begin
                  TxRelato.Insert(0, DsConfig);
            end;

            TxRelato.SaveToFile(NmImpres);

//            AssignFile(DsTexto, NmImpres);
//            Rewrite(DsTexto);
//            WriteLn(DsTexto, DsConfig + TxRelato.Text);
//            CloseFile(DsTexto);
      end;
end;

end.

