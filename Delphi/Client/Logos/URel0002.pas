unit URel0002;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, URel0000, BrvGeraRelatorio, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  BrvRetCon, StdCtrls, BrvEditNum, Mask, BrvMesAno, BrvCustomEdit, BrvCustomMaskEdit, BrvListParam,
  ImgList, Menus, BrvBitBtn;

type
  TRel0002 = class(TRel0000)
    Panel1: TPanel;
    Label2: TLabel;
    EdtCdEmpres: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    Label7: TLabel;
    Label1: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    EdtDtMesAno: TBrvMesAno;
    EdtDtDiaIni: TBrvEditNum;
    EdtDtDiaFim: TBrvEditNum;
    CbxTpImpres: TComboBox;
    BtnFecCon: TBrvBitBtn;
    BtnAbrCon: TBrvBitBtn;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnGeraRelClick(Sender: TObject);
    procedure BrvBitBtn2Click(Sender: TObject);
    procedure BtnAbrConClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Rel0002: TRel0002;

implementation

uses UDmCtb, UDmSrvApl;

{$R *.dfm}

procedure TRel0002.BtnAbrConClick(Sender: TObject);
var lDtAno : Integer;
    lDtMes : Integer;
begin
      NavBarra.SetFocus;

      if EdtDsEmpres.Text = '' then
      begin
            raise Exception.Create('Empresa deve ser informada');
      end;

      DmCtb.PesquisarPeriodoContabil(EdtCdEmpres.BrAsInteger, 'A', lDtAno, lDtMes);
      EdtDtMesAno.AsMes := lDtMes;
      EdtDtMesAno.AsAno := lDtAno;

      EdtDtDiaIni.BrAsInteger := 1;
      EdtDtDiaFim.BrAsInteger := EdtDtMesAno.AsDiaFinal;

      EdtCdEmpres.Enabled     := False;
      BtnAbrCon.Enabled       := False;
      BtnGeraRel.Enabled      := True;
      BtnFecCon.Enabled       := True;
      PnlFundo.Visible        := True;
      EdtDtMesAno.SetFocus;
end;

procedure TRel0002.BrvBitBtn2Click(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.Enabled     := True;
      BtnAbrCon.Enabled       := True;
      BtnGeraRel.Enabled      := False;
      BtnFecCon.Enabled       := False;
      PnlFundo.Visible        := False;
end;

procedure TRel0002.BtnGeraRelClick(Sender: TObject);
var lDtInicio : TDate;
    lDtFinal  : TDate;
begin
      inherited;

      if  not EdtDtMesAno.DataValida then
      begin
            raise Exception.Create('Período inválido');
      end;

      if  (EdtDtDiaIni.BrAsInteger <= 0) or
          (EdtDtDiaIni.BrAsInteger > EdtDtMesAno.AsDiaFinal) then
      begin
            raise Exception.Create('Dia inicial inválido');
      end;

      if  (EdtDtDiaFim.BrAsInteger <= 0) or
          (EdtDtDiaFim.BrAsInteger > EdtDtMesAno.AsDiaFinal) then
      begin
            raise Exception.Create('Dia final inválido');
      end;

      if  EdtDtDiaIni.BrAsInteger > EdtDtDiaFim.BrAsInteger then
      begin
            raise Exception.Create('Dia inicial não pode ser maior que final');
      end;

      if CbxTpImpres.ItemIndex < 0 then
      begin
            raise Exception.Create('Informe o tipo de impressão');
      end;

      lDtInicio := EncodeDate(EdtDtMesAno.AsAno, EdtDtMesAno.AsMes,
                                                 EdtDtDiaIni.BrAsInteger);
      lDtFinal  := EncodeDate(EdtDtMesAno.AsAno, EdtDtMesAno.AsMes,
                                                    EdtDtDiaFim.BrAsInteger);

      if CbxTpImpres.ItemIndex = 0 then
      begin
            BrvGerRel.IniciarRelatorio('RLC0005', 'C', Name);
      end else
      begin
            BrvGerRel.IniciarRelatorio('QRL0004', 'G', Name);
      end;

      BrvGerRel.InserirParametroSQL('CdEmpres', EdtCdEmpres.Text);
      BrvGerRel.InserirParametroSQL('DtInicio', DateToStr(lDtInicio));
      BrvGerRel.InserirParametroSQL('DtFinal',  DateToStr(lDtFinal));

      BrvGerRel.ImprimirRelatorio;
end;

procedure TRel0002.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

initialization
      RegisterClass(TRel0002);

finalization
      UnRegisterClass(TRel0002);
end.
