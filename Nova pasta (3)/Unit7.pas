unit Unit7;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Buttons, ActCurrencyEdit, ActMask, ActDateEdit, StdCtrls,
  ExtCtrls, ActCustomEdit, ActNumberEdit, Grids, DBGrids, DB,
  IBCustomDataSet, IBQuery, IBUpdateSQL, DBCtrls, ActResultEdit;

type
  TForm7 = class(TForm)
    Panel1: TPanel;
    GroupBox1: TGroupBox;
    TXTEMPRESA: TActNumberEdit;
    TXTFILIAL: TActNumberEdit;
    TXTDATPARC: TActDateEdit;
    TXTVALOR: TActCurrencyEdit;
    TXTDESC: TActCurrencyEdit;
    TXTDIAS: TActNumberEdit;
    TXTACRES: TActCurrencyEdit;
    SpeedButton1: TSpeedButton;
    QryTiTULOS: TIBQuery;
    txtparcela: TActNumberEdit;
    DataSource1: TDataSource;
    IBQuery1: TIBQuery;
    IBUpdateSQL1: TIBUpdateSQL;
    IBQuery1DCNUMERO: TIntegerField;
    IBQuery1DCSERIE: TIBStringField;
    IBQuery1DCORDEM: TIBStringField;
    IBQuery1DCTIPO: TIBStringField;
    IBQuery1PARCELA: TFloatField;
    IBQuery1DTVENCIMENTO: TDateField;
    IBQuery1DTLANCAMENTO: TDateField;
    IBQuery1STATUS: TIBStringField;
    IBQuery1TPSITUACAO: TIBStringField;
    IBQuery1VLPARCELA: TFloatField;
    IBQuery1FORNECEDOR: TFloatField;
    IBQuery1OBS: TIBStringField;
    IBQuery1DATAPAGAMENTO: TDateField;
    IBQuery1TIPO_TITULO: TIntegerField;
    IBQuery1COD_EMPRESA: TIntegerField;
    IBQuery1COD_FILIAL: TIntegerField;
    IBQuery1COD_CONTA: TIntegerField;
    IBQuery1COD_TITULO: TIntegerField;
    IBQuery1VALORPAGO: TFloatField;
    IBQuery1OPER_TITULO: TIBStringField;
    IBQuery1ID_MOV_DIARIO: TIntegerField;
    IBQuery1NOCHEQUE: TIBStringField;
    IBQuery1DESCONTO: TFloatField;
    IBQuery1JUROS: TFloatField;
    IBQuery1DATA_APROV_PAG: TDateTimeField;
    IBQuery1RESP_APROV_PAG: TIntegerField;
    IBQuery1DATA_PAG: TDateTimeField;
    IBQuery1RESP_PAG: TIntegerField;
    TXTFORNECEDOR: TActResultEdit;
    GroupBox2: TGroupBox;
    Memo1: TMemo;
    procedure SpeedButton1Click(Sender: TObject);
    procedure DBGrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
      DataCol: Integer; Column: TColumn; State: TGridDrawState);
    procedure DBGrid1EditButtonClick(Sender: TObject);
  private
    { Private declarations }
  public
   function GravarTitulo():Boolean;
  end;

var
  Form7: TForm7;

implementation

uses Unit1;

{$R *.dfm}

    function TForm7.GravarTitulo():Boolean;
    VAR PARCELA   : Integer;
        VALORpAR  :dOUBLE;
        DATA : tDATETIME;
    begin
      PARCELA := 1;
      data := StrToDateTime(TXTDATPARC.Text);
      VALORPAR := (StrToFloat(TXTVALOR.Text)/StrToInt(TXTPARCELA.Text));
      WHILE (PARCELA <= StrToInt(TXTPARCELA.Text) )DO
      BEGIN
      QryTiTULOS.Active := False;
      QryTiTULOS.SQL.Clear;
      QryTiTULOS.SQL.Add('      insert into titulospagar2 ');
      QryTiTULOS.SQL.Add('  ( PARCELA, DTVENCIMENTO, DTLANCAMENTO, ');
      QryTiTULOS.SQL.Add('   STATUS, TPSITUACAO, VLPARCELA, FORNECEDOR, OBS, TIPO_TITULO, ');
      QryTiTULOS.SQL.Add('   COD_EMPRESA, COD_FILIAL, COD_TITULO,DESCONTO,JUROS,OPER_TITULO)');
      QryTiTULOS.SQL.Add('values');
      QryTiTULOS.SQL.Add('  (:PARCELA, :DTVENCIMENTO, :DTLANCAMENTO, ');
      QryTiTULOS.SQL.Add('   :STATUS, :TPSITUACAO, :VLPARCELA, :FORNECEDOR, :OBS, ');
      QryTiTULOS.SQL.Add('   :TIPO_TITULO, :COD_EMPRESA, :COD_FILIAL, :COD_TITULO,:DESCONTO,:JUROS,:OPER_TITULO');
      QryTiTULOS.SQL.Add('   )');
      QryTiTULOS.ParamByName('STATUS').AsString         :=   'D';
      QryTiTULOS.ParamByName('TPSITUACAO').AsString     :=   'F';
      QryTiTULOS.ParamByName('Cod_empresa').asInteger   :=   StrToInt(TXTEMPRESA.Text);
      QryTiTULOS.ParamByName('Cod_filial').asInteger    :=   StrToInt(TXTFILIAL.Text);
      QryTiTULOS.ParamByName('parcela').asFloat       :=   parcela;
      QryTiTULOS.ParamByName('fornecedor').asInteger    :=   StrToInt(TXTFORNECEDOR.Text);
      QryTiTULOS.ParamByName('dtlancamento').asdATETIME :=   Now;
      QryTiTULOS.ParamByName('vlparcela').asfloat       :=   VALORpAR;
      QryTiTULOS.ParamByName('dtvencimento').asdATETIME :=   data;
      QryTiTULOS.ParamByName('tipo_titulo').asInteger   :=   10;

      QryTiTULOS.ParamByName('COD_TITULO').asInteger   :=    parcela;
      QryTiTULOS.ParamByName('DESCONTO').asfLOAT        :=   StrToFLOAT(TXTDESC.Text);
      QryTiTULOS.ParamByName('JUROS').asfLOAT           :=   StrToFLOAT(TXTACRES.Text);
      QryTiTULOS.ParamByName('OBS').AsString            :=   MEMO1.Lines.Text;
      QryTiTULOS.ParamByName('OPER_TITULO').AsString    :=   'P';
     try
      ShowMessage(QryTiTULOS.SQL.Text);
     QryTiTULOS.ExecSQL;
     QryTiTULOS.Transaction.Commit;
     result := True;
     Except
     result := False;
     end;
     DATA := DATA + StrToInt(TXTDIAS.Text);
     PARCELA := PARCELA + 1;
     END;
    end;

procedure TForm7.SpeedButton1Click(Sender: TObject);
begin
GravarTitulo;
end;

procedure TForm7.DBGrid1DrawColumnCell(Sender: TObject; const Rect: TRect;
  DataCol: Integer; Column: TColumn; State: TGridDrawState);
{var
  BUTTON: Integer;
  R: TRect;
  SCapt : string;
begin
  if Column.FieldName = 'DCNUMERO' then
  begin
    DBGrid1.Canvas.FillRect(Rect);
    BUTTON := 0;
    R:=Rect;
    SCapt := '...';
    InflateRect(R,-40,0); //Diminue o tamanho do Botão
    DrawFrameControl(DBGrid1.Canvas.Handle,R,BUTTON, BUTTON or BUTTON);
    with DBGrid1.Canvas do
    begin
      Brush.Style := bsClear;
      Font.Color := clBtnText;
      TextRect(Rect, (Rect.Left + Rect.Right - TextWidth(SCapt)) div 2, (Rect.Top + Rect.Bottom - TextHeight(SCapt)) div 2, SCapt);
    end;
  end; }
const IsChecked : array[Boolean] of Integer =

      (DFCS_BUTTONCHECK, DFCS_BUTTONCHECK or DFCS_CHECKED);

//var

//  DrawState: Integer;

//  DrawRect: TRect;

begin

 { if (gdFocused in State) then

  begin

    if (Column.Field.FieldName = DBCheckBox1.DataField) then

    begin

     DBCheckBox1.Left := Rect.Left + DBGrid1.Left + 2;

     DBCheckBox1.Top := Rect.Top + DBGrid1.top + 2;

     DBCheckBox1.Width := Rect.Right - Rect.Left;

     DBCheckBox1.Height := Rect.Bottom - Rect.Top;

     DBCheckBox1.Visible := True;

    end

  end

  else

  begin

    if (Column.Field.FieldName = DBCheckBox1.DataField) then

    begin

      DrawRect:=Rect;

      InflateRect(DrawRect,-1,-1);

      DrawState := ISChecked[Column.Field.AsBoolean];

      DBGrid1.Canvas.FillRect(Rect);

      DrawFrameControl(DBGrid1.Canvas.Handle, DrawRect,

        DFC_BUTTON, DrawState);

    end;

  end;   }
end;
procedure TForm7.DBGrid1EditButtonClick(Sender: TObject);

{   Var Key: Char; }

   begin
    {  inherited;
      if UpperCase(dbgrid1.Columns[dbgrid1.SelectedIndex].FieldName) = 'dcnumero' then
         begin
            Key := #13;

            Showmessage('teste')

           // ConsProduto.Execute(Key, '');

         end; }

   end;


end.
