unit BrvImgBot;

interface

uses
  SysUtils, Classes, Graphics;

type
  TBrTpBotao = (BrBtnNone,         BrBtnCancel,     BrBtnSalvar,     BrBtnOk,
                BrBtnColar,        BrBtnExcluir,    BrBtnFechar,     BrBtnImprim,
                BrBtnRecort,       BrBtnSalvarComo, BrBtnLocalizar,  BrBtnRelatorio,
                BrBtnAbir,         BrBtnAjuda,      BrBtnBuscar,     BrBtnCopiar,
                BrBtnNovo,         BrBtnLimpar,     BrBtnPesqui,     BrBtnFiltro,
                BrBtnPrimei,       BrBtnAnteri,     BrBtnProxim,     BrBtnUltimo,
                BrBtnGrade,        BrBtnOrdena,     BrBtnExport,     BrBtnImport,
                BrBtnDuplic,       BrBtnSetaAcima,  BrBtnSetaAbaixo, BrBtnSetaDireita,
                BrBtnSetaEsquerda, BrBtnExcel,      BrBtnCarga,      BrBtnApagar,
                BrBtnXML,          BrBtnAtualizar,  BrBtnCalcul,     BrBtnConfig,
                BrBtnCalendar,     BtnExecSQL,      BtnConvSQL,      BtnSubCad);

  TBrvImagem = class(TComponent)
  private
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    procedure BrImagem(pTpImagem : TBrTpBotao; var pDsHint : String;
                                               pBmpImage : TBitmap);
  published
    { Published declarations }
  end;

procedure Register;

implementation

{$R BrvImgBot.RES}

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvImagem]);
end;

{ TBrvImagem }

procedure TBrvImagem.BrImagem(pTpImagem: TBrTpBotao; var pDsHint: String;
                                                     pBmpImage: TBitmap);
var lBmpTemp : TBitMap;
begin
      case pTpImagem of
           BrBtnNone   : begin
                               lBmpTemp := TBitmap.create;

                               try
                                   pBmpImage := lBmpTemp;
                               finally
                                   FreeAndNil(lBmpTemp);
                               end;

                               pDsHint  := '';
                         end;
           BrBtnCancel : begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnCancel');
                               pDsHint  := 'Cancelar';
                         end;
           BrBtnSalvar : begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnSalvar');
                               pDsHint  := 'Salvar';
                         end;
           BrBtnBuscar : begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnBuscar');
                               pDsHint  := 'Buscar';
                         end;
           BrBtnColar  : begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnColar');
                               pDsHint  := 'Colar';
                         end;
           BrBtnCopiar : begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnCopiar');
                               pDsHint  := 'Copiar';
                         end;
           BrBtnExcluir :
                         begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnExclui');
                               pDsHint  := 'Excluir';
                         end;
           BrBtnFechar : begin
                               pBmpImage.Transparent := False;
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnFechar');
                               pDsHint  := 'Fechar';
                         end;
           BrBtnImprim : begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnImprim');
                               pDsHint  := 'Imprimir';
                         end;
           BrBtnNovo   : begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnNovo');
                               pDsHint  := 'Novo';
                         end;
           BrBtnLimpar : begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnLimpar');
                               pDsHint  := 'Limpar';
                         end;
           BrBtnRecort : begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnRecort');
                               pDsHint  := 'Recortar';
                         end;
           BrBtnSalvarComo :
                         begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnSalCom');
                               pDsHint  := 'Salvar Como';
                         end;
           BrBtnLocalizar :
                         begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnLocali');
                               pDsHint  := 'Localizar';
                         end;
           BrBtnAbir  :  begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnAbrir');
                               pDsHint  := 'Abrir';
                         end;
           BrBtnRelatorio :
                         begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnRelato');
                               pDsHint  := 'Relatório';
                         end;
           BrBtnAjuda :
                         begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnAjudar');
                               pDsHint  := 'Ajuda';
                         end;
           BrBtnOk     : begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnOk');
                               pDsHint := 'Confirmar';
                         end;
           BrBtnPesqui : begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnPesqui');
                               pDsHint := 'Pesquisar';
                         end;
           BrBtnFiltro: begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnFiltro');
                               pDsHint := 'Filtrar';
                         end;
           BrBtnPrimei: begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnPrimei');
                               pDsHint := 'Ir para o primeiro';
                         end;
           BrBtnAnteri: begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnAnteri');
                               pDsHint := 'Ir para o anterior';
                         end;
           BrBtnProxim: begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnProxim');
                               pDsHint := 'Ir para o próximo';
                         end;
           BrBtnUltimo: begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnUltimo');
                               pDsHint := 'Ir para o último';
                        end;
           BrBtnGrade : begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnGrade');
                               pDsHint := 'Alternar Grade/Formulário';
                        end;
           BrBtnOrdena: begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnOrdena');
                               pDsHint := 'Ordenar';
                        end;
           BrBtnExport: begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnExport');
                               pDsHint := 'Exportar';
                        end;
           BrBtnImport: begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnImport');
                               pDsHint := 'Importar';
                        end;

           BrBtnDuplic: begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnDuplic');
                               pDsHint := 'Duplicar';
                        end;
           BrBtnSetaAcima:
                        begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnSetAci');
                               pDsHint := '';
                        end;
           BrBtnSetaAbaixo:
                        begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnSetAba');
                               pDsHint := '';
                        end;
           BrBtnSetaEsquerda:
                        begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnSetEsq');
                               pDsHint := '';
                        end;
           BrBtnSetaDireita:
                        begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnSetDir');
                               pDsHint := '';
                        end;
           BrBtnExcel:
                        begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnExcel');
                               pDsHint := '';
                        end;
           BrBtnCarga:
                        begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnCarga');
                               pDsHint := '';
                        end;
           BrBtnApagar:
                        begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnApagar');
                               pDsHint := '';
                        end;
           BrBtnXML:    begin
                               pBmpImage.LoadFromResourceName(HInstance, 'BtnXml');
                               pDsHint := '';
                        end;
           BrBtnAtualizar:
                        begin
                              pBmpImage.LoadFromResourceName(HInstance, 'BtnAtualizar');
                              pDsHint := '';
                        end;
           BrBtnCalcul:
                        begin
                              pBmpImage.LoadFromResourceName(HInstance, 'BtnCalcul');
                              pDsHint := '';
                        end;
           BrBtnConfig:
                        begin
                              pBmpImage.LoadFromResourceName(HInstance, 'BtnConfig');
                              pDsHint := '';
                        end;
           BrBtnCalendar:
                        begin
                              pBmpImage.LoadFromResourceName(HInstance, 'BtnCalendar');
                              pDsHint := '';
                        end;

           BtnExecSQL:
                        begin
                              pBmpImage.LoadFromResourceName(HInstance, 'BtnExecSQL');
                              pDsHint := '';
                        end;
           BtnConvSQL:
                        begin
                              pBmpImage.LoadFromResourceName(HInstance, 'BtnConvSQL');
                              pDsHint := '';
                        end;
           BtnSubCad:
                        begin
                              pBmpImage.LoadFromResourceName(HInstance, 'BtnSubCad');
                              pDsHint := '';
                        end;

      end;
end;

end.
