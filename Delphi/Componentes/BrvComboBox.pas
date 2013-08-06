unit BrvComboBox;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls;

type
  TBrvComboBox = class(TComboBox)
  private
    { Private declarations }
    FDsValues     :  TStrings;
    procedure SetValues(Value: TStrings);
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    function IndexValue(DsValue : AnsiString) : Integer;
    function IndexItem(DsItem   : AnsiString) : Integer;
    function BrvPosicionarValor(pVrValor : AnsiString) : Boolean;
  published
    { Published declarations }
    property OnMouseDown;
    property Values : TStrings read  FDsValues write SetValues;
  end;

procedure Register;

implementation

constructor TBrvComboBox.Create(AOwner: TComponent);
begin
      inherited create(AOwner);
      FDsValues       := TStringList.Create;
end;

destructor TBrvComboBox.Destroy;
begin
      FDsValues.Destroy;

      inherited  destroy;
end;

procedure TBrvComboBox.SetValues(Value: TStrings);
begin
      FDsValues.Assign(Value);
end;

function TBrvComboBox.IndexItem(DsItem : AnsiString) : Integer;
begin

      Result := 0;

      while (Result < Items.Count) and (Items[Result] <> DsItem) do
      begin
            Inc(Result);
      end;

      if Result = Items.Count then
      begin
            Result := -1;
      end;
end;

function TBrvComboBox.IndexValue(DsValue : AnsiString) : Integer;
begin
      Result := 0;

      while (Result < Values.Count) and (Values[Result] <> DsValue) do
      begin
            Inc(Result);
      end;

      if Result = Values.Count then
      begin
            Result := -1;
      end;
end;

function TBrvComboBox.BrvPosicionarValor(pVrValor: AnsiString): Boolean;
var lNrLinha : Integer;
begin
      lNrLinha := 0;
      Result   := False;

      while lNrLinha < Values.Count do
      begin
            if Values.Strings[lNrLinha] = pVrValor then
            begin
                  ItemIndex := lNrLinha;
                  Result    := True;
                  lNrLinha  := Values.Count + 1;
            end;

            inc(lNrLinha);
      end;
end;

procedure Register;
begin
  RegisterComponents('Bravo Standard', [TBrvComboBox]);
end;

end.
