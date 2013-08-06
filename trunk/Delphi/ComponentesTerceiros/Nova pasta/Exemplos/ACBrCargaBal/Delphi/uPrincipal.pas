unit uPrincipal;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ACBrCargaBal, ACBrBase, ComCtrls;

type
  TfrmPrincipal = class(TForm)
    ACBrCargaBal1: TACBrCargaBal;
    cbxModelo: TComboBox;
    edtDiretorio: TEdit;
    btnEscolherDiretorio: TButton;
    btnGerarArquivo: TButton;
    btnFechar: TButton;
    Label1: TLabel;
    Label2: TLabel;
    ProgressBar1: TProgressBar;
    lblStatus: TLabel;
    procedure btnFecharClick(Sender: TObject);
    procedure btnGerarArquivoClick(Sender: TObject);
    procedure btnEscolherDiretorioClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure ACBrCargaBal1Progresso(Mensagem: String; ProgressoAtual,
      ProgressoTotal: Integer);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frmPrincipal: TfrmPrincipal;

implementation

uses
  FileCtrl;

{$R *.dfm}

procedure TfrmPrincipal.FormCreate(Sender: TObject);
begin
  cbxModelo.ItemIndex := 0;
  edtDiretorio.Clear;
end;

procedure TfrmPrincipal.btnEscolherDiretorioClick(Sender: TObject);
var
  Diretorio: String;
begin
  if SelectDirectory('Selecione o diret�rio onde ser�o gerados os arquivos:', '', Diretorio) then
    edtDiretorio.Text := Diretorio;
end;

procedure TfrmPrincipal.btnFecharClick(Sender: TObject);
begin
  Close;
end;

procedure TfrmPrincipal.btnGerarArquivoClick(Sender: TObject);
var
  I: Integer;
begin
  try
    // defini��o do modelo do arquivo, utilizado para padronizar o layout
    // conforme a balan�a para a qual se vai gerar o arquivo
    ACBrCargaBal1.Modelo := TACBrCargaBalModelo(cbxModelo.ItemIndex);

    // adi��o dos itens que ser�o gerados no arquivo
    ACBrCargaBal1.Produtos.Clear;
    for I := 0 to 1000 do
    begin
      with ACBrCargaBal1.Produtos.New do
      begin
        ModeloEtiqueta  := 1;
        Tipo            := tpvPeso;
        Codigo          := I;
        Descricao       := Format('Descricao item %d', [I]);
        ValorVenda      := 1.23;
        Validade        := 15;
        Tecla           := 0;
        Receita         := Format('Receita do item %d', [I]);
//        Nutricional     := Format('Informa��o Nutricional do item %d', [I]);;

        Nutricional.Codigo:=I;
        Nutricional.Qtd :=1;
        Nutricional.UndPorcao := tpGramas;
        Nutricional.PartInteira :=1;
        Nutricional.PartDecimal:= tpPara12;
        Nutricional.MedCaseira := tpColherSopa;
        Nutricional.ValorEnergetico := 20;
        Nutricional.Carboidrato := 2;
        Nutricional.Proteina := 3;
        Nutricional.GorduraTotal:= 4;
        Nutricional.GorduraSaturada:=5;
        Nutricional.GorduraTrans := 6;
        Nutricional.Fibra := 7;
        Nutricional.Sodio :=8;

        Setor.Codigo    := 1;
        Setor.Descricao := 'GERAL';
      end;
    end;

    // gerar os arquivos para o diret�rio, informe somente o caminho do diret�rio
    ACBrCargaBal1.GerarArquivos(edtDiretorio.Text);
    ShowMessage('Arquivo gerado com sucesso!');
  except
    on E: Exception do
    begin
      // todo erro do componente levanta uma excess�o
      ShowMessage('Ocorreu o seguinte erro:' + sLineBreak + E.Message);
    end;
  end;
end;

procedure TfrmPrincipal.ACBrCargaBal1Progresso(Mensagem: String;
  ProgressoAtual, ProgressoTotal: Integer);
begin
  lblStatus.Caption     := Mensagem;
  ProgressBar1.Max      := ProgressoTotal;
  ProgressBar1.Position := ProgressoAtual;

  Application.ProcessMessages;
end;

end.
