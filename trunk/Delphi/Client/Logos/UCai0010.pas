unit UCai0010;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCai0000, StdCtrls, Buttons, BrvBitBtn, ExtCtrls, Grids, BrvDbGrids, BrvDbGrid,
  DB, DBClient, BrvClientDataSet, BrvComboBox, BrvSpeedButton;

type
  TCai0010 = class(TCai0000)
    Panel3: TPanel;
    BrvDbGrid1: TBrvDbGrid;
    QpLog: TBrvClientDataSet;
    DpLog: TDataSource;
    Label1: TLabel;
    EdtNmTabela: TEdit;
    SbtPosici: TBrvSpeedButton;
    Label2: TLabel;
    CbxAtribu: TBrvComboBox;
    procedure SbtPosiciClick(Sender: TObject);
  private
    { Private declarations }
    gVrChave : String;
  public
    { Public declarations }
    procedure  CarregarAtributos(pNmTabela : String; pVrChave : String);
    procedure MostrarLog(pNmAtribu : String);
  end;

var
  Cai0010: TCai0010;

implementation

uses UDmSrvApl;

{$R *.dfm}

{ TCai0010 }

procedure TCai0010.CarregarAtributos(pNmTabela: String; pVrChave : String);
var lStlAtribu : TStrings;
begin
      EdtNmTabela.Text := pNmTabela;
      gVrChave         := pVrChave;

      try
          lStlAtribu := TStringList.Create;
          DmSrvApl.BrvDicionario.AtributosDaTabela(pNmtabela, lStlAtribu);
          CbxAtribu.Items.Text  := lStlAtribu.Text;
          CbxAtribu.Values.Text := lStlAtribu.Text;
      finally
          FreeAndNil(lStlAtribu);
      end;
end;

procedure TCai0010.MostrarLog(pNmAtribu: String);
var lDsParam   : String;
begin
      if pNmAtribu = ''  then
      begin
            lDsParam := '<%NmTabela%>;' + EdtNmTabela.Text + #13 +
                        '<%VrChave%>;'  + gVrChave  + #13 +
                        '<%NmAtribu%>; ';
      end else
      begin
            lDsParam := '<%NmTabela%>;' + EdtNmTabela.Text + #13 +
                        '<%VrChave%>;'  + gVrChave  + #13 +
                        '<%NmAtribu%>; and S039.NmAtribu = ' + #39 + pNmAtribu + #39;
      end;

      Cai0010.QpLog.Data :=
                       DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(32, lDsParam, Name);

end;

procedure TCai0010.SbtPosiciClick(Sender: TObject);
begin
      MostrarLog(CbxAtribu.Values.Strings[CbxAtribu.ItemIndex]);
end;

end.
