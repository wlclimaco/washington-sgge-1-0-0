unit UCon0002;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, DB,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvBitBtn,
  BrvEditNum, Mask, BrvEditDate, BrvRetCon, BrvConsulta, DBCtrls, BrvDbRetCon, BrvEdit, Grids,
  BrvDbGrids, BrvDbGrid, ComCtrls, DBClient, BrvClientDataSet, BrvDbEdit, BrvServerProgress,
  BrvListParam, ImgList, Menus, BrvCustomMaskEdit, BrvCustomEdit;

type
  TCon0002 = class(TMov0000)
    pnlMestre: TPanel;
    Label2: TLabel;
    EdtCdEmpres: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    Label1: TLabel;
    EdtCdCtrc: TBrvEditNum;
    EdtCdRemete: TBrvEditNum;
    EdtRsRemete: TBrvRetCon;
    Label11: TLabel;
    Label4: TLabel;
    EdtCdDestin: TBrvEditNum;
    EdtRsDestin: TBrvRetCon;
    Label5: TLabel;
    EdtCdCarga: TBrvEditNum;
    EdtDsDescar: TBrvRetCon;
    Label6: TLabel;
    BrvEditNum1: TBrvEditNum;
    BtnLocali: TBrvBitBtn;
    DsT010: TDataSource;
    CPT010: TBrvClientDataSet;
    EdtNmMotori: TBrvRetCon;
    GroupBox1: TGroupBox;
    rbAtendimento: TRadioButton;
    rbOcorrencia: TRadioButton;
    rbAssunto: TRadioButton;
    Label3: TLabel;
    EdtCdMotori: TBrvEditNum;
    Label9: TLabel;
    EdtNrAtendi: TBrvEditNum;
    EdtTexto: TBrvEdit;
    GroupBox2: TGroupBox;
    EdtFinal: TBrvEditDate;
    Label8: TLabel;
    EdtInicio: TBrvEditDate;
    Label7: TLabel;
    rbAbertura: TRadioButton;
    rbFechamento: TRadioButton;
    Label10: TLabel;
    EdtCdUsuari: TBrvEditNum;
    EdtNmComUsu: TBrvRetCon;
    Label12: TLabel;
    EdtNrRps: TBrvEditNum;
    BrvServerProgress: TBrvServerProgress;
    bdgRegistros: TBrvDbGrid;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure EdtCdCtrcBrOnBeforeConsulta(Sender: TObject);
    procedure BtnLocaliClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure bdgRegistrossDblClick(Sender: TObject);
    procedure bdgRegistrosDblClick(Sender: TObject);
  private
    { Private declarations }
    gDsWhere      : AnsiString;
    gDsEmpresa    : AnsiString;
    gDsMotoristas : AnsiString;
  public
    { Public declarations }
  end;

var
  Con0002: TCon0002;

implementation

uses UDmSrvApl, UCon0003;

{$R *.dfm}

procedure TCon0002.bdgRegistrossDblClick(Sender: TObject);
begin
     if CPT010.RecordCount > 0  then
     begin
           try
               Con0003 := TCon0003.Create(Self);
               Con0003.CarregarDadosAtendimento(CPT010.FieldByName('NrAtendi').AsString);
           finally
               FreeAndNil(Con0003);
           end;
     end;
end;

procedure TCon0002.BtnLocaliClick(Sender: TObject);
var   lCampo  : AnsiString;
      lTexto  : AnsiString;
begin
      inherited;
      gDsWhere    :=  '';
      gDsEmpresa  :=  '';
      //PnlDados.Visible := False;

      // Empresa
      if EdtCdEmpres.BrAsInteger > 0 then
      begin
            gDsWhere := gDsWhere + ' and T002.CdEmpres = ' + EdtCdEmpres.Text;
      end else
      begin
            gDsEmpresa := gDsEmpresa + ' and T002.CdEmpres in (' +
                                     EdtCdEmpres.BrDicionario.CorpCommaCodes +')';
      end;

      // Remetente
      if EdtCdRemete.BrAsInteger > 0 then
      begin
            gDsWhere := gDsWhere + ' and T002.CdRemete = ' + EdtCdRemete.Text;
      end;

      // Destinatario
      if EdtCdDestin.BrAsInteger > 0 then
      begin
            gDsWhere := gDsWhere + ' and T002.CdDestin = ' + EdtCdDestin.Text;
      end;

      // Carga
      if EdtCdCarga.BrAsInteger > 0 then
      begin
            gDsWhere := gDsWhere + ' and T010.CdCarga = ' + EdtCdCarga.Text;
      end;

      // Motorista
      if EdtCdMotori.BrAsInteger > 0 then
      begin
            gDsWhere := gDsWhere + ' and T009.CdMotori = ' + EdtCdMotori.Text;
      end;

      // Campo texto a pesquisar
      lTexto  :=  EdtTexto.Text;

      if ( (rbAtendimento.Checked) and (lTexto <> '') ) then
      begin
            gDsWhere := gDsWhere + ' and T010.TxAtendi Like ' + QuotedStr('%' + lTexto + '%');
      end;

      if ( (rbAssunto.Checked) and (lTexto <> '') ) then
      begin
            gDsWhere := gDsWhere + ' and T010.DsAtendi Like ' + QuotedStr('%' + lTexto + '%');
      end;

      if ( (rbOcorrencia.Checked) and (lTexto <> '') ) then
      begin
            gDsWhere := gDsWhere + ' and T012.TxOcorre Like ' + QuotedStr('%' + lTexto + '%')
      end;

      if ( (Not EdtInicio.BrDataVazia ) or (Not EdtFinal.BrDataVazia ) ) then
      begin
            if rbAbertura.Checked then
            begin
                  lCampo  := 'DtAbertu';
            end;

            if rbFechamento.Checked then
            begin
                  lCampo  := 'DtFecham';
            end;

            if not EdtInicio.BrDataVazia then
            begin
                  gDsWhere := gDsWhere + ' and T010.' + lCampo + ' >= "' + EdtInicio.BrAsDataSQL + '"';
            end;

            if not EdtFinal.BrDataVazia then
            begin
                  gDsWhere := gDsWhere + ' and T010.' + lCampo + ' <= "' + EdtFinal.BrAsDataSQL + '"';
            end;
      end;

      if EdtCdUsuari.BrAsInteger > 0 then
      begin
            gDsWhere := gDsWhere + ' and T010.CdUsuari = ' + EdtCdUsuari.Text;
      end;

      if EdtNrRps.BrAsInteger > 0 then
      begin
            gDsWhere := gDsWhere + ' and F004.NrRps = ' + EdtNrRps.Text;
      end;

      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);

          CpT010.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(44,
                                                                  '<%DsComWhe%>;' + gDsWhere, Name);
      finally
          BrvServerProgress.Stop;
      end;
end;

procedure TCon0002.bdgRegistrosDblClick(Sender: TObject);
begin
      if CPT010.RecordCount > 0  then
      begin
            try
                Con0003 := TCon0003.Create(Self);
                Con0003.CarregarDadosAtendimento(CPT010.FieldByName('NrAtendi').AsString);
            finally
                FreeAndNil(Con0003);
            end;
      end;
end;

procedure TCon0002.EdtCdCtrcBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdCtrc.BrParams.Clear;
      if EdtCdEmpres.BrAsInteger > 0 then
      begin
            EdtCdCtrc.BrParams.Add('<%CdEmpres%>;' + ' and S004.CdEmpres = ' +
                                                                        EdtCdEmpres.Text);
      end else
      begin
            EdtCdCtrc.BrParams.Add('<%CdEmpres%>;');
      end;
end;

procedure TCon0002.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TCon0002.FormCreate(Sender: TObject);
begin
      inherited;
      Height  :=  600;
      Width   :=  800;
      Top     :=  -20;

end;

initialization
      RegisterClass(TCon0002);

finalization
      UnRegisterClass(TCon0002);

end.
