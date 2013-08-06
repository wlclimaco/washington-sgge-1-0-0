unit BrvDialogoImpressao;

interface

uses Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs;

type
  TBrvDialogoImpressao = class(TComponent)
  private
    { Private declarations }
    FNmImpres   :  String;
    FSnImpCon   :  Boolean;
    FNrCopias   :  Byte;
    FNrImpres   :  Integer;
    procedure SetImprimeCondensado(InValor : Boolean);
    procedure SetNumeroCopias(InValor : Byte);
    procedure EncontrarNomeImpressora;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    function    Execute : Boolean;
  published
    { Published declarations }
    property Impressora        : String  read FNmImpres write FNmImpres;
    property ImprimeCondensado : Boolean read FSnImpCon write SetImprimeCondensado;
    property NumeroCopias      : Byte    read FNrCopias write SetNumeroCopias;
    property ImpressoraNumero  : Integer read FNrImpres write FNrImpres;
  end;

procedure Register;

implementation

uses  BrvDialogoImpressaoForm;

procedure Register;
begin
      RegisterComponents('Bravo Relatorio', [TBrvDialogoImpressao]);
end;

constructor TBrvDialogoImpressao.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);
      FNrCopias   :=  1;
end;

destructor TBrvDialogoImpressao.Destroy;
begin
      inherited  destroy;
end;

procedure TBrvDialogoImpressao.SetImprimeCondensado(InValor : Boolean);
begin
      FSnImpCon := inValor;
end;

procedure TBrvDialogoImpressao.SetNumeroCopias(InValor : Byte);
begin
      FNrCopias := InValor;
end;

function TBrvDialogoImpressao.Execute : Boolean;
begin
      Result := False;

      BrvDialogoImpressaoForm.FrmDlgImpres  :=
                                       BrvDialogoImpressaoForm.TFrmDlgImpres.Create(Self);

      BrvDialogoImpressaoForm.FrmDlgImpres.CbxConden.Checked  := FSnImpCon;
      BrvDialogoImpressaoForm.FrmDlgImpres.SdtNrCopias.Value  := FNrCopias;

      if  BrvDialogoImpressaoForm.FrmDlgImpres.CbxImpres.Text = '' then
      begin
            MessageDlg('Nenhuma impressora foi encontrada em seu computador',
                        MtInformation, [MbOk], 0);
      end else
      begin
            if  BrvDialogoImpressaoForm.FrmDlgImpres.ShowModal = MrOk then
            begin
                  Result  :=  True;
                  
                  SetImprimeCondensado(
                                  BrvDialogoImpressaoForm.FrmDlgImpres.CbxConden.Checked);
                              
                  SetNumeroCopias(BrvDialogoImpressaoForm.FrmDlgImpres.SdtNrCopias.Value);
                              
                  EncontrarNomeImpressora;
            end else
            begin
                  Result  :=  False;
            end;
      end;

      BrvDialogoImpressaoForm.FrmDlgImpres.Destroy;
end;

procedure TBrvDialogoImpressao.EncontrarNomeImpressora;
var NmImpres  : String;
begin
       NmImpres    :=  BrvDialogoImpressaoForm.FrmDlgImpres.CbxImpres.Text;

       if (pos(' on ',NmImpres) <> 0) or (pos(' em ', NmImpres) <> 0) then
       begin
             if  pos(' on ',NmImpres) <> 0 then
             begin
                   Delete(NmImpres, 1, pos(' on ', NmImpres) + 3);
             end else
             begin
                   Delete(NmImpres, 1, pos(' em ', NmImpres) + 3);
             end;
       end else
       begin
             if  pos('\\', NmImpres) = 0 then
             begin
                   NmImpres := 'lpt1:';
             end;
       end;

       FNmImpres := NmImpres;
       FNrImpres := BrvDialogoImpressaoForm.FrmDlgImpres.CbxImpres.ItemIndex;
end;

end.
