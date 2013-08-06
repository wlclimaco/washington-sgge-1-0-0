unit URel0001;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, URel0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  StdCtrls, BrvComboBox, BrvGeraRelatorio, BrvListParam, ImgList, Menus, BrvBitBtn;

type
  TRel0001 = class(TRel0000)
    Label1: TLabel;
    CbxTpTeste: TBrvComboBox;
    procedure FormCreate(Sender: TObject);
    procedure BtnGeraRelClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Rel0001: TRel0001;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TRel0001.BtnGeraRelClick(Sender: TObject);
begin
      inherited;
      // =-= Inserindo parâmetros de configuração do relatório

      if CbxTpTeste.Values.Strings[CbxTpTeste.ItemIndex] = 'C' then
      begin
            BrvGerRel.IniciarRelatorio('RLC0001',
                                             CbxTpTeste.Values.Strings[CbxTpTeste.ItemIndex], Name);
      end else
      begin
            BrvGerRel.IniciarRelatorio('QRL0001',
                                             CbxTpTeste.Values.Strings[CbxTpTeste.ItemIndex], Name);
      end;

      // =-= Inserindo parâmetros que serão inseridos na query para geração do relatório
      BrvGerRel.InserirParametroSQL('CdUsuari', IntToStr(DmSrvApl.BrvDicionario.UserCode));
      BrvGerRel.ImprimirRelatorio;
end;

procedure TRel0001.FormCreate(Sender: TObject);
begin
      inherited;
      CbxTpTeste.ItemIndex := 0;
end;

end.
