unit BrvDominiosXE;

interface

uses  SysUtils, Classes, BrvCheckListBox, StdCtrls;

procedure CarregaDominios(pCbChLsBx: TBrvCheckListBox;
                          pNmTabela: String; pNmAtribu: String;
                          pSnMarcad: Boolean=False);

procedure CarregaEmpresas(pCbChLsBx: TBrvCheckListBox; pSnMarcad: Boolean=False);

implementation

uses UDmSrvApl;

procedure CarregaDominios(pCbChLsBx: TBrvCheckListBox;
                          pNmTabela: String; pNmAtribu: String;
                          pSnMarcad: Boolean=False);
var lVrDomini : String;
    lDsDomini : String;
begin
      DmSrvApl.BrvDicionario.CarregarDominiosDoAtributo(pNmTabela, pNmAtribu, lVrDomini, lDsDomini);
      pCbChLsBx.Items.Clear;
      pCbChLsBx.Values.Clear;
      pCbChLsBx.Values.CommaText := lVrDomini;
      pCbChLsBx.Items.CommaText  := lDsDomini;
      if (pSnMarcad) then
      begin
            pCbChLsBx.CheckAll(cbChecked);
      end;
end;

procedure CarregaEmpresas(pCbChLsBx: TBrvCheckListBox; pSnMarcad: Boolean=False);
begin
      pCbChLsBx.Items.Clear;
      pCbChLsBx.Values.Clear;
      pCbChLsBx.Items.CommaText  := DmSrvApl.BrvDicionario.CorpNames.CommaText;
      pCbChLsBx.Values.CommaText := DmSrvApl.BrvDicionario.CorpCodes.CommaText;
      if (pSnMarcad) then
      begin
            pCbChLsBx.CheckAll(cbChecked);
      end;
end;

end.
