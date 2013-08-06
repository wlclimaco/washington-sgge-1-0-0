unit UMov0010;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, Dialogs,
  ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvComboBox, BrvRetCon,
  BrvEditNum, Grids, BrvDbGrids, BrvDbGrid, Mask, BrvEditDate, DBGrids, Menus, DB, DBClient,
  BrvClientDataSet, UClaSrv, BrvCustomMaskEdit, BrvCustomEdit, BrvListParam, ImgList, UMov0000,
  BrvBitBtn;

type
  TMov0010 = class(TMov0000)
    PnlCabeca: TPanel;
    Label1: TLabel;
    LblNrPlano: TLabel;
    EdtCdEmpres: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    LblCliFor: TLabel;
    EdtCdTitula: TBrvEditNum;
    LblRsTitula: TBrvRetCon;
    PnlRodapeTotal: TPanel;
    PnlGroup1: TPanel;
    PnlDadosFat: TPanel;
    GbxTotMarc: TGroupBox;
    Label5: TLabel;
    Label10: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    EdtCdCliFat: TBrvEditNum;
    LblRsCliFat: TBrvRetCon;
    EdtDtFatura: TBrvEditDate;
    CmbPreFat: TComboBox;
    EdtNrFatura: TBrvEditNum;
    GrdFatura: TBrvDBGrid;
    GbxTotDesmarc: TGroupBox;
    Label6: TLabel;
    Label7: TLabel;
    EdtQtDesmar: TBrvEditNum;
    EdtVrDesmar: TBrvEditNum;
    Label2: TLabel;
    EdtQtMarcar: TBrvEditNum;
    Label4: TLabel;
    EdtVrMarcar: TBrvEditNum;
    PopSnTodos: TPopupMenu;
    MarcarTodos1: TMenuItem;
    Desmarcartodos1: TMenuItem;
    LblTpFatCtr: TLabel;
    CdsFatura: TBrvClientDataSet;
    DtsFatura: TDataSource;
    Label3: TLabel;
    EdtVrDescDe: TBrvEditNum;
    EdtVrDescMa: TBrvEditNum;
    Label8: TLabel;
    CdsNotas: TBrvClientDataSet;
    CdsNotasDsModeNf: TStringField;
    CdsNotasNrSeriNf: TStringField;
    CdsNotasNrNota: TIntegerField;
    PnlOperac: TPanel;
    BtnAbrEmp: TBrvBitBtn;
    BtnFecEmp: TBrvBitBtn;
    BtnFatura: TBrvBitBtn;
    CdsNotasTemp: TClientDataSet;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure CdsFaturaBeforeInsert(DataSet: TDataSet);
    procedure MarcarTodos1Click(Sender: TObject);
    procedure Desmarcartodos1Click(Sender: TObject);
    procedure CdsFaturaAfterPost(DataSet: TDataSet);
    procedure CmbPreFatChange(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure BtnAbrEmpClick(Sender: TObject);
    procedure BtnFecEmpClick(Sender: TObject);
    procedure BtnFaturaClick(Sender: TObject);
  private
    procedure TotalizarRodape(pSnPreFat: String);
    procedure AlteraRodape;
    procedure ValidarEntradas;
    function ParametrosFaturar: OleVariant;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0010: TMov0010;

const
  cQtPreFat = 50;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TMov0010.ValidarEntradas;
begin
      if EdtVrMarcar.BrAsFloat = 0 then
      begin
            raise Exception.Create('Nenhum documento foi selecionado.');
      end;

      if LblRsCliFat.Text = '' then
      begin
            raise Exception.Create('Informe o cliente para faturamento');
      end;

      if EdtDtFatura.BrDataVazia then
      begin
            raise Exception.create('Informe a data para faturamento');
      end;
end;

function TMov0010.ParametrosFaturar: OleVariant;
var lCcParam  : TClientDataSet;
    lNrPreFat : Integer;
begin
      try
          lNrPreFat := StrToIntDef(CmbPreFat.Text, 0);
          TotalizarRodape('N');

          lCcParam  := TClientDataSet.Create(Self);

          lCcParam.FieldDefs.Clear;
          lCcParam.FieldDefs.Add('pNmFormul', ftString,  20);
          lCcParam.FieldDefs.Add('pDtFatura', ftDateTime, 0);
          lCcParam.FieldDefs.Add('pCdTitula', ftInteger,  0);
          lCcParam.FieldDefs.Add('pVrLancto', ftFloat,    0);
          lCcParam.FieldDefs.Add('pCdEmpres', ftInteger,  0);
          lCcParam.FieldDefs.Add('pNrPreCar', ftInteger,  0);
          lCcParam.FieldDefs.Add('pNrPlano' , ftInteger,  0);
          lCcParam.FieldDefs.Add('pVrDescon', ftFloat,    0);
          lCcParam.FieldDefs.Add('pCdsN006' , ftMemo,     0);
          lCcParam.CreateDataSet;

          CdsNotasTemp.Data := CdsNotas.Data;
          CdsNotasTemp.First;

          while not CdsNotasTemp.Eof do
          begin
                if (CdsNotasTemp.FieldByName('SnMarcad').AsString = 'S') then
                begin
                      CdsNotasTemp.Next;
                end else
                begin
                      CdsNotasTemp.Delete;
                end;
          end;

          lCcParam.Append;
          lCcParam.FieldByName('pNmFormul').AsString  := Self.Name;
          lCcParam.FieldByName('pDtFatura').AsDateTime:= EdtDtFatura.BrAsDate;
          lCcParam.FieldByName('pCdTitula').AsInteger := EdtCdCliFat.BrAsInteger;
          lCcParam.FieldByName('pVrLancto').AsFloat   := EdtVrMarcar.BrAsFloat;
          lCcParam.FieldByName('pCdEmpres').AsInteger := EdtCdEmpres.BrAsInteger;
          lCcParam.FieldByName('pNrPreCar').AsInteger := lNrPreFat;
          lCcParam.FieldByName('pNrPlano' ).AsInteger := StrToInt(LblNrPlano.Caption);
          lCcParam.FieldByName('pVrDescon').AsFloat   := EdtVrDescMa.BrAsFloat;
          lCcParam.FieldByName('pCdsN006' ).AsString  := CdsNotasTemp.XMLData;
          lCcParam.Post;
      finally
          Result := lCcParam.Data;
          FreeAndNil(lCcParam);
      end;
end;

procedure TMov0010.BtnFaturaClick(Sender: TObject);
var lConexao  : TSDmAdmClient;
    lCpResult : TClientDataSet;
begin
      ValidarEntradas;

      if (MessageDlg('Confirma faturamento?', mtConfirmation, [mbYes, mbNo], 0) = idYes) then
      begin
            try
                lCpResult := TClientDataSet.Create(Self);
                lConexao  := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);

 {               lCpResult.Data := lConexao.Faturar(DmSrvApl.BrvDicionario.Credencial,
                                                                                 ParametrosFaturar);
      }
                DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(
                                                        lCpResult.FieldByNAme('DsResult').AsString);

                MessageDlg('Operação realizada com sucesso!' + #13#13 +
                           'Fatura número: ' + lCpResult.FieldByNAme('NrConta').AsString,
                           mtInformation, [mbOk], 0);

                BtnFecEmpClick(nil);
            finally
                FreeAndNil(lConexao);
                FreeAndNil(lCpResult);
            end;
      end;
end;

procedure TMov0010.BtnAbrEmpClick(Sender: TObject);
var lDsParams : String;
begin
      NavBarra.SetFocus;

      if EdtDsEmpres.Text = '' then
      begin
           raise Exception.Create('Informe a empresa');
      end;

      if (LblNrPlano.Caption = '') or (LblNrPlano.Caption = '0') then
      begin
            raise Exception.Create('Não há plano de contas definido para essa empresa');
      end;

      if LblTpFatCtr.Caption = '2' then // Não permite faturar antes da entrega
      begin
            lDsParams :='<%DsComWhe%>; ' + ' and ' +
                        '( (T002.CdCtrc is not null and T002.DtEntreg is not null) or ' +
                        '  (T002.CdCtrc is not null and T002.DtEntMot is not null) or ' +
                        '  (T002.CdCtrc is null) ) ' + #13;
      end else
      begin // Permite faturar antes da entrega
            lDsParams := '<%DsComWhe%>; ' + #13;
      end;

      lDsParams := lDsParams + '<%CdEmpres%>;' + EdtCdEmpres.Text + #13 +
                               '<%CdClient%>;' + EdtCdTitula.Text;

      CdsFatura.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(110, lDsParams, Name);
      (CdsFatura.FieldByName('VrConNot') as TNumericField).DisplayFormat := '0.00';
      (CdsFatura.FieldByName('VrDescon') as TNumericField).DisplayFormat := '0.00';

      TotalizarRodape('S');

      EdtCdEmpres.Enabled := False;
      EdtCdTitula.Enabled := False;
      BtnAbrEmp.Enabled   := False;
      BtnFecEmp.Enabled   := True;
      BtnFatura.Enabled   := True;
      PnlFundo.Visible    := True;
end;

procedure TMov0010.TotalizarRodape(pSnPreFat : String);
var Book      : TBookmark;
    lNrPreFat : Integer;
    lDsPreFat : array [1..cQtPreFat] of Integer;
    lSnAchou  : Boolean;
begin
      try
          Book := CdsFatura.GetBookmark;

          CdsFatura.DisableControls;
          GrdFatura.Enabled       := False;

          EdtQtMarcar.BrAsInteger := 0;
          EdtVrDescMa.BrAsFloat   := 0;
          EdtVrMarcar.BrAsFloat   := 0;

          EdtQtDesmar.BrAsInteger := 0;
          EdtVrDescDe.BrAsFloat   := 0;
          EdtVrDesmar.BrAsFloat   := 0;

          // Requisição: 589
          if pSnPreFat = 'S' then // Agrupa os números de pré-fatura e atribui ao combobox
          begin
                for lNrPreFat := 1 to cQtPreFat do
                begin
                      lDsPreFat[lNrPreFat] := 0;
                end;
          end;
          // FIM - Requisição: 589

          CdsNotas.EmptyDataSet;

          CdsFatura.First;

          while not CdsFatura.Eof do
          begin
                if CdsFatura.FieldByName('SnMarcad').AsString = 'N' then
                begin
                      EdtVrDesmar.BrAsFloat   := EdtVrDesMar.BrAsFloat +
                                                        (CdsFatura.FieldByName('VrConNot').AsFloat -
                                                         CdsFatura.FieldByName('VrDescon').AsFloat);

                      EdtQtDesmar.BrAsInteger := EdtQtDesmar.BrAsInteger + 1;

                      EdtVrDescDe.BrAsFloat   := EdtVrDescDe.BrAsFloat +
                                                          CdsFatura.FieldByName('VrDescon').AsFloat;

                end else
                begin
                      EdtVrMarcar.BrAsFloat := EdtVrMarcar.BrAsFloat +
                                              (CdsFatura.FieldByName('VrConNot').AsFloat -
                                               CdsFatura.FieldByName('VrDescon').AsFloat);

                      EdtVrDescMa.BrAsFloat := EdtVrDescMa.BrAsFloat +
                                               CdsFatura.FieldByName('VrDescon').AsFloat;

                      EdtQtMarcar.BrAsInteger := EdtQtMarcar.BrAsInteger + 1;

                      CdsNotas.Append;
                      CdsNotas.FieldByName('DsModeNf').AsString :=
                                          CdsFatura.FieldByName('DsModeNf').AsString;
                      CdsNotas.FieldByName('NrSeriNf').AsString :=
                                          CdsFatura.FieldByName('NrSeriNf').AsString;
                      CdsNotas.FieldByName('NrNota').AsString   :=
                                          CdsFatura.FieldByName('NrNota').AsString;
                      CdsNotas.Post;

                end;

                // Requisição: 589
                if pSnPreFat = 'S' then // Agrupa os números de pré-fatura e atribui ao combobox
                begin
                      if CdsFatura.FieldByName('NrPreFat').AsInteger > 0 then
                      begin
                            lSnAchou := False;
                            for lNrPreFat := 1 to cQtPreFat do
                            begin
                                if lDsPreFat[lNrPreFat] =
                                         CdsFatura.FieldByName('NrPreFat').AsInteger then
                                begin
                                      lSnAchou := True;
                                end;
                            end;

                            lNrPreFat := 1;
                            while not lSnAchou  do
                            begin
                                  if lDsPreFat[lNrPreFat] = 0 then
                                  begin
                                        lDsPreFat[lNrPreFat] :=
                                            CdsFatura.FieldByName('NrPreFat').AsInteger;
                                        lSnAchou := True;
                                  end;

                                  Inc(lNrPreFat);

                                  if lNrPreFat > cQtPreFat then
                                  begin
                                        lSnAchou := True;
                                  end;
                            end;
                      end;
                end;

                CdsFatura.Next;
          end;

          // Requisição: 589
          if pSnPreFat = 'S' then// Agrupa os números de pré-fatura e atribui ao combobox
          begin
                CmbPreFat.Items.Clear;
                for lNrPreFat := 1 to cQtPreFat do
                begin
                    if lDsPreFat[lNrPreFat] <> 0 then
                    begin
                          CmbPreFat.Items.Add(IntToStr(lDsPreFat[lNrPreFat]));
                    end;
                end;
          end;

          CdsFatura.GotoBookmark(Book);
          CdsFatura.FreeBookmark(Book);
      finally
          GrdFatura.Enabled := True;
          CdsFatura.EnableControls;
      end;
end;

procedure TMov0010.BtnFecEmpClick(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.Enabled  := True;
      EdtCdTitula.Enabled  := True;
      BtnAbrEmp.Enabled    := True;
      BtnFecEmp.Enabled    := False;
      BtnFatura.Enabled    := False;
      PnlFundo.Visible     := False;
end;

procedure TMov0010.CdsFaturaAfterPost(DataSet: TDataSet);
begin
      AlteraRodape;
end;

procedure TMov0010.CdsFaturaBeforeInsert(DataSet: TDataSet);
begin
      Abort;
end;

procedure TMov0010.CmbPreFatChange(Sender: TObject);
var lNrPreFat : Integer;
begin
      CdsFatura.DisableControls;

      try
          lNrPreFat := StrToInt(CmbPreFat.text);
          CdsFatura.First;

          while not CdsFatura.Eof do
          begin
                CdsFatura.Edit;

                CdsFatura.FieldByName('SnMarcad').AsString := 'N';

                if CdsFatura.FieldByName('NrPreFat').AsInteger = lNrPreFat then
                begin
                      CdsFatura.FieldByName('SnMarcad').AsString := 'S';
                end;

                CdsFatura.Next;
          end;

      finally
          CdsFatura.First;
          TotalizarRodape('N');
          CdsFatura.EnableControls;
      end;
end;

procedure TMov0010.Desmarcartodos1Click(Sender: TObject);
begin
      GrdFatura.SetarTodasColunas('SnMarcad', 'N');
      TotalizarRodape('N');
end;

procedure TMov0010.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0010.FormCreate(Sender: TObject);
begin
      Inherited;
      CdsNotas.CreateDataSet;
end;

procedure TMov0010.MarcarTodos1Click(Sender: TObject);
begin
      GrdFatura.SetarTodasColunas('SnMarcad', 'S');
      TotalizarRodape('N');
end;

procedure TMov0010.AlteraRodape;
begin
      if CdsFatura.FieldByName('SnMarcad').AsString = 'N' then
      begin
            EdtVrMarcar.BrAsFloat   := EdtVrMarcar.BrAsFloat -
                                                        (CdsFatura.FieldByName('VrConNot').AsFloat -
                                                         CdsFatura.FieldByName('VrDescon').AsFloat);
            EdtQtMarcar.BrAsInteger := EdtQtMarcar.BrAsInteger - 1;
            EdtVrDescMa.BrAsFloat   := EdtVrDescMa.BrAsFloat -
                                                          CdsFatura.FieldByName('VrDescon').AsFloat;
            EdtVrDesmar.BrAsFloat   := EdtVrDesMar.BrAsFloat +
                                                        (CdsFatura.FieldByName('VrConNot').AsFloat -
                                                         CdsFatura.FieldByName('VrDescon').AsFloat);
            EdtQtDesmar.BrAsInteger := EdtQtDesmar.BrAsInteger + 1;
            EdtVrDescDe.BrAsFloat   := EdtVrDescDe.BrAsFloat +
                                                          CdsFatura.FieldByName('VrDescon').AsFloat;
      end else
      begin
            EdtVrMarcar.BrAsFloat   := EdtVrMarcar.BrAsFloat +
                                                        (CdsFatura.FieldByName('VrConNot').AsFloat -
                                                         CdsFatura.FieldByName('VrDescon').AsFloat);
            EdtVrDescMa.BrAsFloat   := EdtVrDescMa.BrAsFloat +
                                                          CdsFatura.FieldByName('VrDescon').AsFloat;
            EdtQtMarcar.BrAsInteger := EdtQtMarcar.BrAsInteger + 1;
            EdtVrDesMar.BrAsFloat   := EdtVrDesMar.BrAsFloat -
                                                        (CdsFatura.FieldByName('VrConNot').AsFloat -
                                                         CdsFatura.FieldByName('VrDescon').AsFloat);
            EdtQtDesMar.BrAsInteger := EdtQtDesMar.BrAsInteger - 1;
            EdtVrDescDe.BrAsFloat   := EdtVrDescDe.BrAsFloat -
                                                          CdsFatura.FieldByName('VrDescon').AsFloat;
      end;
end;

initialization
      RegisterClass(TMov0010);

finalization
      UnRegisterClass(TMov0010);
end.

