unit BrvCheckListBox;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, CheckLst, Menus;

type
  TBrvCheckListBox = class(TCheckListBox)
  private
    { Private declarations }
    FDsValues  : TStrings;
    FMnMarDes  : TPopupMenu;
    procedure SetValues(Value: TStrings);
    procedure CreateMenu;
    procedure ClickItemMenu(Sender: TObject);
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    function    BrCheckedCount       : Integer;
    function    BrCheckedList        : String;
    function    BrCheckedListDominio : TStringList;
    procedure   BrAdd(pDsLabel : String; pDsValue : String);
  published
    { Published declarations }
    property Values         : TStrings read  FDsValues write SetValues;
  end;

procedure Register;

implementation

procedure Register;
begin
      RegisterComponents('Bravo Standard', [TBrvCheckListBox]);
end;

constructor TBrvCheckListBox.Create(AOwner: TComponent);
begin
      inherited create(AOwner);
      FDsValues       := TStringList.Create;
      CreateMenu;
end;

destructor TBrvCheckListBox.Destroy;
begin
      FDsValues.Destroy;
      inherited  destroy;
end;

procedure TBrvCheckListBox.SetValues(Value: TStrings);
begin
      FDsValues.Assign(Value);
end;

function TBrvCheckListBox.BrCheckedCount : Integer;
var lNrIndice : Integer;
begin
      Result    := 0;

      for lNrIndice := 0 to Count -1 do
      begin
            if Checked[lNrIndice] then
            begin
                  Inc(Result);
            end;
      end;
end;

procedure TBrvCheckListBox.CreateMenu;
var lMnuItem : TMenuItem;
begin
      FMnMarDes         := TPopupMenu.Create(Self);
      PopupMenu         := FMnMarDes;

      lMnuItem         := TMenuItem.Create(Self);
      lMnuItem.Caption := 'Marcar todos';
      lMnuItem.Name    := 'MniMarca';
      lMnuItem.Tag     := 0;
      lMnuItem.OnClick := ClickItemMenu;
      FMnMarDes.Items.Add(lMnuItem);

      lMnuItem         := TMenuItem.Create(Self);
      lMnuItem.Caption := 'Desmarcar todos';
      lMnuItem.Name    := 'MniDesMar';
      lMnuItem.Tag     := 1;
      lMnuItem.OnClick := ClickItemMenu;
      FMnMarDes.Items.Add(lMnuItem);
end;

procedure TBrvCheckListBox.ClickItemMenu(Sender: TObject);
begin
      if TMenuItem(Sender).Tag = 0 then
      begin
            CheckAll(cbChecked);
      end else
      begin
            CheckAll(cbUnChecked);
      end;
end;

function TBrvCheckListBox.BrCheckedList : String;
var lNrIndice : Integer;
begin
      Result    := '';

      for lNrIndice := 0 to Count -1 do
      begin
            if Checked[lNrIndice] then
            begin
                  Result := Result + ', ' + FDsValues[lNrIndice];
            end;
      end;

      Delete(Result, 1, 2);
end;

function TBrvCheckListBox.BrCheckedListDominio : TStringList;
var lNrIndice : Integer;
    lVrDomini : String;
    lDsDomini : String;
begin
      Result := TStringList.Create;

      for lNrIndice := 0 to Count -1 do
      begin
            if Checked[lNrIndice] then
            begin
                  lVrDomini := lVrDomini + ', "' + FDsValues[lNrIndice] + '"';
                  lDsDomini := lDsDomini + ', '  + Items[lNrIndice];
            end;
      end;

      Delete(lVrDomini, 1, 2);
      Delete(lDsDomini, 1, 2);

      Result.Add(lVrDomini);
      Result.Add(lDsDomini);
end;

procedure TBrvCheckListBox.BrAdd(pDsLabel : String; pDsValue : String);
begin
      Items.Add(pDsLabel);
      FDsValues.Add(pDsValue);
end;


end.
