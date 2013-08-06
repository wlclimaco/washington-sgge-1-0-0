unit BrvOrdenarForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, ExtCtrls, Buttons, DB, BrvBitBtn, BrvSpeedButton, BrvString;

type
  TFrmOrdenar = class(TForm)
    Panel1: TPanel;
    Panel2: TPanel;
    BtnOk: TBrvBitBtn;
    BtnCancela: TBrvBitBtn;
    Panel3: TPanel;
    Panel4: TPanel;
    LbxDisponiveis1: TListBox;
    Panel5: TPanel;
    Panel6: TPanel;
    Panel7: TPanel;
    LbxOrdenar1: TListBox;
    Panel8: TPanel;
    BtnAcima: TBrvSpeedButton;
    BtnAbaixo: TBrvSpeedButton;
    BtnDireita: TBrvSpeedButton;
    BtnEsquerda: TBrvSpeedButton;
    BtnTodosDireita: TBrvSpeedButton;
    BtnTodosEsquerda: TBrvSpeedButton;
    procedure LbxDisponiveis1DblClick(Sender: TObject);
    procedure LbxOrdenar1DblClick(Sender: TObject);
    procedure BtnDireitaClick(Sender: TObject);
    procedure BtnEsquerdaClick(Sender: TObject);
    procedure BtnTodosDireitaClick(Sender: TObject);
    procedure BtnTodosEsquerdaClick(Sender: TObject);
    procedure BtnAcimaClick(Sender: TObject);
    procedure BtnAbaixoClick(Sender: TObject);
  private
    { Private declarations }
  public
    LbxOrdenar2: TStrings;
    LbxDisponiveis2: TStrings;
    procedure CarregarListBox(const DataSet1: TDataSet; pDsOrdAtu : String);
    { Public declarations }
  end;

var
  FrmOrdenar: TFrmOrdenar;
  i: Integer;

implementation

{$R *.DFM}

procedure TFrmOrdenar.LbxDisponiveis1DblClick(Sender: TObject);
begin
   if LbxDisponiveis1.ItemIndex >= 0
   then begin
      LbxOrdenar1.Items.Add(LbxDisponiveis1.Items[LbxDisponiveis1.ItemIndex]);
      LbxOrdenar2.Add(LbxDisponiveis2[LbxDisponiveis1.ItemIndex]);
      LbxDisponiveis2.Delete(LbxDisponiveis1.ItemIndex);
      LbxDisponiveis1.Items.Delete(LbxDisponiveis1.ItemIndex);
   end;
end;

procedure TFrmOrdenar.BtnDireitaClick(Sender: TObject);
begin
   if LbxDisponiveis1.ItemIndex >= 0
   then begin
      i := LbxDisponiveis1.ItemIndex;

      LbxOrdenar1.Items.Add(LbxDisponiveis1.Items[i]);
      LbxOrdenar2.Add(LbxDisponiveis2[i]);
      LbxDisponiveis2.Delete(i);
      LbxDisponiveis1.Items.Delete(i);

      if i > LbxOrdenar1.Items.Count
      then begin
         LbxDisponiveis1.ItemIndex := i - 1
      end else begin
         LbxDisponiveis1.ItemIndex := i
      end;
   end;
end;

procedure TFrmOrdenar.BtnTodosDireitaClick(Sender: TObject);
var i1: Integer;
begin
   i1 := 0;
   While i1 < LbxDisponiveis1.Items.Count   do begin
      LbxOrdenar1.Items.Add(LbxDisponiveis1.Items[i1]);
      LbxOrdenar2.Add(LbxDisponiveis2[i1]);
      inc(i1);
   end;
   LbxOrdenar1.ItemIndex := 0;

   LbxDisponiveis1.Clear;
   LbxDisponiveis2.Clear;
end;

procedure TFrmOrdenar.LbxOrdenar1DblClick(Sender: TObject);
begin
   if LbxOrdenar1.ItemIndex >= 0
   then begin
      LbxDisponiveis1.Items.Add(LbxOrdenar1.Items[LbxOrdenar1.ItemIndex]);
      LbxDisponiveis2.Add(LbxOrdenar2[LbxOrdenar1.ItemIndex]);
      LbxOrdenar2.Delete(LbxOrdenar1.ItemIndex);
      LbxOrdenar1.Items.Delete(LbxOrdenar1.ItemIndex);
   end;
end;

procedure TFrmOrdenar.BtnEsquerdaClick(Sender: TObject);
begin
   if LbxOrdenar1.ItemIndex >= 0
   then begin
      i := LbxOrdenar1.ItemIndex;

      LbxDisponiveis1.Items.Add(LbxOrdenar1.Items[LbxOrdenar1.ItemIndex]);
      LbxDisponiveis2.Add(LbxOrdenar2[LbxOrdenar1.ItemIndex]);
      LbxOrdenar2.Delete(LbxOrdenar1.ItemIndex);
      LbxOrdenar1.Items.Delete(LbxOrdenar1.ItemIndex);

      if i > LbxOrdenar1.Items.Count
      then begin
         LbxOrdenar1.ItemIndex := i - 1
      end else begin
         LbxOrdenar1.ItemIndex := i
      end;
   end;
end;

procedure TFrmOrdenar.BtnTodosEsquerdaClick(Sender: TObject);
var i1: Integer;
begin
   i1 := 0;
   While i1 < LbxOrdenar1.Items.Count   do begin
      LbxDisponiveis1.Items.Add(LbxOrdenar1.Items[i1]);
      LbxDisponiveis2.Add(LbxOrdenar2[i1]);
      inc(i1);
   end;
   LbxDisponiveis1.ItemIndex := 0;

   LbxOrdenar1.Clear;
   LbxOrdenar2.Clear;
end;

procedure TFrmOrdenar.BtnAcimaClick(Sender: TObject);
begin
   if (LbxOrdenar1.ItemIndex > 0)
   then begin
      i := LbxOrdenar1.ItemIndex;

      LbxOrdenar2.Move(i, i - 1);
      LbxOrdenar1.Items.Move(i, i - 1);
      LbxOrdenar1.ItemIndex := i - 1;
   end;
end;

procedure TFrmOrdenar.BtnAbaixoClick(Sender: TObject);
begin
      if (LbxOrdenar1.ItemIndex < (LbxOrdenar1.Items.Count - 1)) and
         (LbxOrdenar1.ItemIndex > -1) then
      begin
            i := LbxOrdenar1.ItemIndex;

            LbxOrdenar2.Move(i, i + 1);
            LbxOrdenar1.Items.Move(i, i + 1);
            LbxOrdenar1.ItemIndex := i + 1;
      end;
end;

procedure TFrmOrdenar.CarregarListBox(const DataSet1: TDataSet; pDsOrdAtu : String);
var lNrIndice : Integer;
    lBrString : TBrvString;
    lNmField  : String;
begin
      LbxDisponiveis2 := TStringList.Create;
      LbxOrdenar2     := TStringList.Create;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-= Carregando os campos já em ordenação
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      lBrString := TBrvString.Create(Self);
      lBrString.Split(pDsOrdAtu, ';');
      for lNrIndice := 0 to lBrString.BrSplitLista.Count - 1 do
      begin
            lNmField := lBrString.BrSplitLista.Strings[lNrIndice];

            if lNmField <> '' then
            begin
                  LbxOrdenar1.Items.Add(DataSet1.FieldByName(lNmField).DisplayName);
                  LbxOrdenar2.Add(lNmField);
            end;
      end;

      for lNrIndice := 0 to DataSet1.FieldCount-1 do
      begin
            if (DataSet1.Fields[lNrIndice].Visible)            and
               (DataSet1.Fields[lNrIndice].FieldKind = fkData) then
            begin
                  if Pos(DataSet1.Fields[lNrIndice].FieldName, pDsOrdAtu) = 0 then
                  begin
                        LbxDisponiveis1.Items.Add(DataSet1.Fields[lNrIndice].DisplayName);
                        LbxDisponiveis2.Add(DataSet1.Fields[lNrIndice].FieldName);
                  end;
            end;
     end;
end;

end.
