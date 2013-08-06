unit BrvDb;

interface

uses Db, Classes;

type
  TBcoBravo = (BcoOracle, BcoFirebird);
  TVqType   = (VqNormal, VqSearch);
  TBrSfCharCase = (BrSfUpperCase, BrSfLowerCase, BrSfNomal);

  TBrStringField = class(TStringField)
  private
    gSfChaCas : TBrSfCharCase;
  published
    property BrCharCase : TBrSfCharCase read gSfChaCas write gSfChaCas;
  public
    constructor Create(AOwner: TComponent); override;
  end;

  TBrWideStringField = class(TWideStringField)
  private
    gSfChaCas : TBrSfCharCase;
  published
    property BrCharCase : TBrSfCharCase read gSfChaCas write gSfChaCas;
  public
    constructor Create(AOwner: TComponent); override;
  end;

  TBrMemoField = class(TMemoField)
  private
    gSfChaCas : TBrSfCharCase;
  published
    property BrCharCase : TBrSfCharCase read gSfChaCas write gSfChaCas;
  public
    constructor Create(AOwner: TComponent); override;
  end;

  implementation

{ TFzStringField }

constructor TBrStringField.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);
      gSfChaCas := BrSfNomal;
end;

{ TFzMemoField }
constructor TBrMemoField.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);
      gSfChaCas := BrSfNomal;
end;

{ TBrWideStringField }

constructor TBrWideStringField.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);
      gSfChaCas := BrSfNomal;
end;

end.
