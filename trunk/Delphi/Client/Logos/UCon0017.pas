unit UCon0017;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, BrvDbGrids, BrvDbGrid,
  StdCtrls, Grids, DBGrids, CheckLst, BrvCheckListBox, BrvEdit, Mask, BrvEditDate, BrvBitBtn,
  BrvRetCon, BrvEditNum, ComCtrls, DB, DBClient, BrvProgressBar, BrvExcel, BrvMesAno,
  BrvListParam, BrvServerProgress, ImgList, Menus, BrvCustomMaskEdit, BrvCustomEdit, BrvXml,UClaSrv,
  BrvImport, ACBrNFe, ACBrNFeDANFEClass, ACBrNFeDANFERave,BrvClientDataSet;

type
  TCon0017 = class(TMov0000)
    Panel1: TPanel;
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    Panel4: TPanel;
    Panel2: TPanel;
    BtnPesquisa: TBrvBitBtn;
    TbsConsulta: TTabSheet;
    Panel3: TPanel;
    Splitter2: TSplitter;
    Panel5: TPanel;
    LblQtReg: TLabel;
    BtnExcel: TBrvBitBtn;
    BtnVoltar: TBrvBitBtn;
    Panel6: TPanel;
    StgFiltros: TStringGrid;
    PopupMenu1: TPopupMenu;
    MenuItem1: TMenuItem;
    MenuItem2: TMenuItem;
    MenuItem3: TMenuItem;
    ImageList1: TImageList;
    BrvListParam1: TBrvListParam;
    CcP002: TClientDataSet;
    DtsP002: TDataSource;
    BrvExcel: TBrvExcel;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    BrvXMLNFE: TBrvXML;
    Label1: TLabel;
    CcP003: TClientDataSet;
    CcP004: TClientDataSet;
    BrvDbGrid1: TBrvDbGrid;
    BrvDbGrid2: TBrvDbGrid;
    BrvImport1: TBrvImport;
    Splitter1: TSplitter;
    DbgResult: TBrvDbGrid;
    BrvBitBtn1: TBrvBitBtn;
    CCP005: TClientDataSet;
    popMarcar: TPopupMenu;
    MarcarTodos1: TMenuItem;
    DesmarcarTodos1: TMenuItem;
    Panel7: TPanel;
    LblCdEmpres: TLabel;
    CblCdEmpres: TBrvCheckListBox;
    LblDtIni: TLabel;
    LblDtFin: TLabel;
    EdtDtIni: TBrvEditDate;
    EdtDtFin: TBrvEditDate;
    procedure FormCreate(Sender: TObject);
    procedure BtnPesquisaClick(Sender: TObject);
    procedure DbgResultDblClick(Sender: TObject);
    procedure BrvBitBtn1Click(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure BtnExcelClick(Sender: TObject);
    procedure MarcarTodos1Click(Sender: TObject);
    procedure DesmarcarTodos1Click(Sender: TObject);
  private
    procedure ValidaEntradaDados;
  public
    { Public declarations }
  end;

var
  Con0017: TCon0017;

implementation

uses UDmSrvApl, BrvDominiosXE;
{$R *.dfm}

procedure TCon0017.BrvBitBtn1Click(Sender: TObject);
var  lCcP002Aux : TClientDataSet;
      pCpXML: TBrvClientDataSet ;
      CPEmpresa:TBrvClientDataSet;
     lConexao  : TSDmAdmClient;
     lDsResult : AnsiString;

begin
      try
          lCcP002Aux := TClientDataSet.Create(Self);
          lCcP002Aux.Data := CcP003.Data;
          lCcP002Aux.Filter   := 'SnMarcad = ' + QuotedStr('S');
          lCcP002Aux.Filtered := True;

          while not lCcP002Aux.Eof do
          begin
                if (lCcP002Aux.FieldByName('NrCertif').AsString <> '') and
                                         (lCcP002Aux.FieldByName('NrSenCer').AsString <> '') then
                begin
                      lConexao  := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                      lDsResult := lConexao.BuscarNFeManifesto(DmSrvApl.BrvDicionario.Credencial,
                                              210210,
                                              lCcP002Aux.FieldByName('NRCHADOC').AsString,
                                              lCcP002Aux.FieldByName('CjEmpres').AsString,
                                              lCcP002Aux.FieldByName('NrCertif').AsString,
                                              lCcP002Aux.FieldByName('NrSenCer').AsString,
                                              lCcP002Aux.FieldByName('CdEstado').AsString,
                                              lCcP002Aux.FieldByName('CdEmpres').AsString);

                      DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
                end else
                begin
                      ShowMessage('Nota não pode mudar status pois não existe certificado digital'
                                    +' para empresa '+lCcP002Aux.FieldByName('CdEmpres').AsString);
                end;
                lCcP002Aux.Next;
          end;

      finally

      end;
end;

procedure TCon0017.DbgResultDblClick(Sender: TObject);
begin
      BrvListParam.Clear;
      BrvListParam.Add('NrChaDoc', 'NrChaDoc',
                                      DbgResult.Fields[7].asString,DbgResult.Fields[7].asString);
      CcP002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(224,
                                                                BrvListParam.AsBrParam, Self.Name);
end;

procedure TCon0017.DesmarcarTodos1Click(Sender: TObject);
begin
      inherited;
      DbgResult.SetarTodasColunas('SnMarcad', 'N');
end;

procedure TCon0017.BtnExcelClick(Sender: TObject);
var lColunas  : TStringList;
    lCampo    : String;
    lClientDS : TClientDataSet;
    lPonteiro : TBookmark;
    lNrIndice : Integer;
begin
      lColunas  :=  TStringList.Create;

      for lNrIndice := 1 to DbgResult.Columns.Count - 1 do
      begin
            lColunas.Add(DbgResult.Columns[lNrIndice].FieldName);
      end;
      try
          Screen.Cursor := crHourGlass;
          lClientDS := TClientDataSet.Create(Self);
          lClientDS.Data := CcP003.Data;
          lClientDS.First;
          BrvExcel.BrDataSet :=  lClientDS;
          BrvExcel.Execute(lColunas, '', nil);
      finally
          FreeAndNil(lColunas);
          Screen.Cursor := crDefault;
      end;
end;

procedure TCon0017.ValidaEntradaDados;
var lDsEmp : String;
begin
      BrvListParam.Clear;

      if (CblCdEmpres.BrCheckedCount = 0) then
      begin
            CblCdEmpres.SetFocus;
            raise Exception.Create('Selecione a(s) ' + LblCdEmpres.Caption + '!');
      end else
      begin
            lDsEmp := CblCdEmpres.BrCheckedList;
            BrvListParam.Add('CdEmpres', LblCdEmpres.Caption, lDsEmp, lDsEmp);
      end;
      if (EdtDtIni.BrDataVazia) then
      begin
            EdtDtIni.SetFocus;
            raise Exception.Create('Verifique a ' + LblDtIni.Caption + ' informada!');
      end else
      begin
            BrvListParam.Add('dtinilan', LblDtIni.Caption, EdtDtIni.Text, EdtDtIni.Text);
      end;
      if (EdtDtFin.BrDataVazia) then
      begin
            EdtDtFin.SetFocus;
            raise Exception.Create('Verifique a ' + LblDtFin.Caption + ' informada!');
      end else
      begin
            BrvListParam.Add('dtfinlan', LblDtFin.Caption, EdtDtFin.Text, EdtDtFin.Text);
      end;
end;

procedure TCon0017.BtnPesquisaClick(Sender: TObject);
Var lCcP002Aux : TClientDataSet;
    lVrCount   : Integer;
begin
      try
          lCcP002Aux := TClientDataSet.Create(Self);

          ValidaEntradaDados;

          try
              BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);

              lCcP002Aux.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(230,
                                                                BrvListParam.AsBrParam, Self.Name);
          finally
              BrvServerProgress.Stop;
          end;

          LblQtReg.Caption := FormatFloat('0', lCcP002Aux.RecordCount) + ' Registro(s)';
          BrvListParam.SetStgParam(StgFiltros);
          lVrCount := 1;
          if (lCcP002Aux.RecordCount > 0) then
          begin
                PgcFundo.ActivePage := TbsConsulta;
                if (CcP003.Active) then
                begin
                      CcP003.EmptyDataSet;
                      CcP003.Close;
                end;
                CcP003.FieldDefs.Clear;
                CcP003.FieldDefs.Add('SnMarcad', ftstring , 1);
                CcP003.FieldDefs.Add('CdEmpres', ftinteger , 0);
                CcP003.FieldDefs.Add('RazSocEmp', ftstring ,255);
                CcP003.FieldDefs.Add('NrNota', ftinteger , 0);
                CcP003.FieldDefs.Add('CjFornec', ftstring, 20);
                CcP003.FieldDefs.Add('RazSocFor', ftstring  ,44);
                CcP003.FieldDefs.Add('VrNota'  , ftFloat , 0);
                CcP003.FieldDefs.Add('NRCHADOC', ftstring , 255);
                CcP003.FieldDefs.Add('STMANIFE', ftstring , 255);
                CcP003.FieldDefs.Add('CjEmpres', ftstring , 255);
                CcP003.FieldDefs.Add('NrCertif', ftstring , 255);
                CcP003.FieldDefs.Add('NrSenCer', ftstring , 255);
                CcP003.FieldDefs.Add('CdEstado', ftstring , 255);
                CcP003.CreateDataSet;
                while not  lCcP002Aux.Eof do
                begin
                      BrvXMLNFE.BrXMLOriginal.Text := lCcP002Aux.FieldByName('TxXML').AsString;
                      BrvXMLNFE.ProcessarXml;

                      CcP003.Append;
                      CcP003.FieldByName('SnMarcad').Value    := 'N';
                      CcP003.FieldByName('CdEmpres').Value
                                                        := lCcP002Aux.FieldByName('CDEmpres').Value;
                      CcP003.FieldByName('RazSocEmp').Value
                                                        := CcP002.FieldByName('dest_xNome').Value;
                      CcP003.FieldByName('NrNota').Value
                                                        := CcP002.FieldByName('ide_nNF').Value;
                      CcP003.FieldByName('CjFornec').Value
                                                        := CcP002.FieldByName('emit_CNPJ').Value;
                      CcP003.FieldByName('RazSocFor').Value
                                                        := CcP002.FieldByName('emit_xNome').Value;
                      CcP003.FieldByName('VrNota').Value
                                                        := CcP002.FieldByName('ICMSTot_vNF').Value;
                      CcP003.FieldByName('NRCHADOC').Value
                                                        := lCcP002Aux.FieldByName('NrChaDoc').Value;
                      CcP003.FieldByName('CjEmpres').AsString
                                                        := lCcP002Aux.FieldByName('CjEmpres').Value;
                      if lCcP002Aux.FieldByName('NrCertif').Value <> null then
                      begin
                            CcP003.FieldByName('NrCertif').AsString
                                                        := lCcP002Aux.FieldByName('NrCertif').Value;
                      end;
                      if lCcP002Aux.FieldByName('NrSenCer').Value <> null then
                      begin
                            CcP003.FieldByName('NrSenCer').AsString
                                                        := lCcP002Aux.FieldByName('NrSenCer').Value;
                      end;
                      CcP003.FieldByName('CdEstado').AsString
                                                        := lCcP002Aux.FieldByName('CdEstado').Value;
                      case lCcP002Aux.FieldByName('StManife').AsInteger of
                        0 :  CcP003.FieldByName('STMANIFE').Value := 'Manifesto não informado';
                        1 :  CcP003.FieldByName('STMANIFE').Value
                                                            := 'Confirmacao de Operacao registrada';
                        2 :  CcP003.FieldByName('STMANIFE').Value
                                                                := 'Ciencia da Operacao registrada';
                        3 :  CcP003.FieldByName('STMANIFE').Value
                                                        := 'Desconhecimento da Operacao registrada';
                        4 :  CcP003.FieldByName('STMANIFE').Value
                                                             := 'Operacao nao Realizada registrada';
                        9 :  CcP003.FieldByName('STMANIFE').Value
                                                                 := 'Nota não precisa de manifesto';
                      end;
                      CcP003.Post;
                      lVrCount := lVrCount + 1;
                      lCcP002Aux.Next;
                end;


          end else
          begin
                MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
          end;

      finally
          FreeAndNil(lCcP002Aux);
      end;
end;

procedure TCon0017.BtnVoltarClick(Sender: TObject);
begin
  inherited;
      PgcFundo.ActivePage := TbsFiltro;
end;

procedure TCon0017.FormCreate(Sender: TObject);
var lnridx : Integer;
begin
      inherited;
      TbsFiltro.TabVisible := False;
      TbsConsulta.TabVisible := False;
      PgcFundo.ActivePage := TbsFiltro;

      CarregaEmpresas(CblCdEmpres, True);

      EdtDtIni.Text := FormatDateTime('DD/mm/yyyy', Now());
      EdtDtFin.Text := FormatDateTime('DD/mm/yyyy', Now());
end;

procedure TCon0017.MarcarTodos1Click(Sender: TObject);
begin
      inherited;
      DbgResult.SetarTodasColunas('SnMarcad', 'S');
end;

initialization
      RegisterClass(TCon0017);

finalization
      UnRegisterClass(TCon0017);

end.
