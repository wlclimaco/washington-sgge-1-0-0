unit Unit13;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Unit2, DB,Unit15, IBCustomDataSet, IBQuery, Buttons, ExtCtrls, ActMask,
  ActMaskEdit, ActEdit, StdCtrls, ActCustomEdit, ActNumberEdit, IBUpdateSQL;

type
  TModeloForm13 = class(TModeloForm)
    IBQuery1: TIBQuery;
    Label1: TLabel;
    DataSource1: TDataSource;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    Label14: TLabel;
    Label15: TLabel;
    Label16: TLabel;
    TXTCODCLI: TActNumberEdit;
    TXTNOME: TActEdit;
    TXTCPF: TActMaskEdit;
    TXTRG: TActMaskEdit;
    TXTLOGRADOURO: TActEdit;
    TXTNUMERO: TActNumberEdit;
    BAIRRO: TActEdit;
    TXTCIDADE: TActEdit;
    TXTUF: TActEdit;
    TXTCEP: TActMaskEdit;
    TXTTELEFONE: TActMaskEdit;
    TXTCEL: TActMaskEdit;
    TXTFAX: TActMaskEdit;
    TXTEMAIL: TActEdit;
    TXTEMAIL2: TActEdit;
    TXTSITE: TActEdit;
    IBQuery2: TIBQuery;
    IBQuery3: TIBQuery;
    IBQuery4: TIBQuery;
    IBQuery5: TIBQuery;
    IBQuery6: TIBQuery;
    IBQuery7: TIBQuery;
    IBUpdateSQL1: TIBUpdateSQL;
    TXTOBS: TActEdit;
    procedure SpeedButton7Click(Sender: TObject);
    procedure SpeedButton8Click(Sender: TObject);
    procedure SpeedButton5Click(Sender: TObject);
    procedure SpeedButton6Click(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure SpeedButton4Click(Sender: TObject);
    procedure SpeedButton9Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    Function PoPulaTXT():Boolean;
    Function PoPulaClasseTXT():Boolean;
    Function PoPulaClasseBD():Boolean;
    Function inserir():Boolean;
    Function alterar():Boolean;
    Function deletar():Boolean;
    Function buscar():Boolean;
    Function SelectInicial():Boolean;
    Function Primeiro():Boolean;
    Function Proximo():Boolean;
    Function Anterior():Boolean;
    Function Ultimo():Boolean;
    Function Limpar():Boolean;

  end;

var
  ModeloForm13: TModeloForm13;
  Clientes: TCLientes;
implementation




uses Unit3;

{$R *.dfm}
  Function TModeloForm13.Limpar():Boolean;
  begin
        TXTCODCLI.Text :=     '';
        TXTNOME.Text :=       '' ;
        TXTCPF.Text :=        '';
        TXTRG.Text :=         '' ;
        TXTLOGRADOURO.Text := '' ;
        TXTNUMERO.Text :=     '';
        TXTCIDADE.Text :=     '';
        TXTUF.Text :=         '';
        TXTCEP.Text :=        '' ;
        TXTTELEFONE.Text :=   ''  ;
        TXTCEL.Text :=        '' ;
        TXTFAX.Text :=        '';
        TXTEMAIL.Text :=      '' ;
        TXTEMAIL2.Text :=     '';
        TXTSITE.Text :=       '';
        TXTOBS.Text  :=       '';

  end;


     Function TModeloForm13.PoPulaTXT():Boolean;
    begin
        TXTCODCLI.Text := IntToStr(CLIENTES.CDCLIENTE);
        TXTNOME.Text :=    CLIENTES.RAZAO ;
        TXTCPF.Text :=  CLIENTES.CGC;
        TXTRG.Text :=   CLIENTES.INCR ;
        TXTLOGRADOURO.Text := CLIENTES.ENDe ;
        TXTNUMERO.Text :=IntToStr(CLIENTES.Numero);
        TXTCIDADE.Text :=  CLIENTES.MUNICIPIO;
        TXTUF.Text :=      CLIENTES.UF;
        TXTCEP.Text :=     CLIENTES.CEP ;
        TXTTELEFONE.Text :=CLIENTES.TELEFONE  ;
        TXTCEL.Text :=     CLIENTES.CEL ;
        TXTFAX.Text :=     CLIENTES.Fax;
        TXTEMAIL.Text :=   CLIENTES.EMAIL ;
        TXTEMAIL2.Text :=  CLIENTES.EMAIL;
        TXTSITE.Text :=    CLIENTES.SITE;
        TXTOBS.Text       :=    CLIENTES.OBS;
    end;
    Function TModeloForm13.PoPulaClasseTXT():Boolean;
    begin
        CLIENTES.CDCLIENTE := StrToInt(TXTCODCLI.Text);
        CLIENTES.RAZAO     := TXTNOME.Text;
        CLIENTES.CGC       := TXTCPF.Text;
        CLIENTES.INCR      := TXTRG.Text;
        CLIENTES.ENDe      := TXTLOGRADOURO.Text;
        CLIENTES.Numero    := StrToInt(TXTNUMERO.Text);
        CLIENTES.MUNICIPIO := TXTCIDADE.Text;
        CLIENTES.UF        := TXTUF.Text;
        CLIENTES.CEP       := TXTCEP.Text;
        CLIENTES.TELEFONE  := TXTTELEFONE.Text;
        CLIENTES.CEL       := TXTCEL.Text;
        CLIENTES.Fax      := TXTFAX.Text;
        CLIENTES.EMAIL     := TXTEMAIL.Text;
        CLIENTES.EMAIL     := TXTEMAIL2.Text;
        CLIENTES.SITE      := TXTSITE.Text;
        CLIENTES.OBS       := TXTOBS.Text;
    end;
    Function TModeloForm13.PoPulaClasseBD():Boolean;
    begin
        CLIENTES := TCLIENTES.Create;
        CLIENTES.CDCLIENTE := IBQuery1.FieldByName('cdcliente').AsInteger ;
        CLIENTES.RAZAO     := IBQuery1.FieldByName('NOME').AsString;
        CLIENTES.CGC       := IBQuery1.FieldByName('CGC').AsString;
        CLIENTES.INCR      := IBQuery1.FieldByName('INCR').AsString;
        CLIENTES.ENDe      := IBQuery1.FieldByName('LOGRADOURO').AsString;
        CLIENTES.Numero    := IBQuery1.FieldByName('NUMERO').AsInteger;
        CLIENTES.MUNICIPIO := IBQuery1.FieldByName('MUNICIPIO').AsString;
        CLIENTES.UF        := IBQuery1.FieldByName('UF').AsString;
        CLIENTES.CEP       := IBQuery1.FieldByName('CEP').AsString;
        CLIENTES.TELEFONE  := IBQuery1.FieldByName('FONE').AsString;
        CLIENTES.CEL       := IBQuery1.FieldByName('CEL').AsString;
        CLIENTES.Fax       := IBQuery1.FieldByName('FONEFAX').AsString;
        CLIENTES.EMAIL     := IBQuery1.FieldByName('EMAIL').AsString;
        CLIENTES.EMAIL     := IBQuery1.FieldByName('EMAIL2').AsString;
        CLIENTES.SITE      := IBQuery1.FieldByName('SITE').AsString;
        CLIENTES.OBS       := IBQuery1.FieldByName('OBS').AsString;

    end;
    Function TModeloForm13.inserir():Boolean;
    begin
      try
      IBQuery2.SQL.Text := 'INSERT INTO CLIENTES (CDCLIENTE,STATUS,NOME,FANTASIA,CGC,INCR,LOGRADOURO,NUMERO,BAIRRO,MUNICIPIO,UF,CEP,FONE,FONEFAX,CEL,EMAIL,EMAIL2,SITE,OBS) VALUES ('+IntToStr(CLIENTES.CDCLIENTE)+','''+CLIENTES.STATUS+''','''+CLIENTES.RAZAO+''','''+CLIENTES.RAZAO+''','''+CLIENTES.CGC+''','''+CLIENTES.INCR+''','''+CLIENTES.ENDe+''','+IntToStr(CLIENTES.Numero)+','''+CLIENTES.BAIRRO+''','''+CLIENTES.MUNICIPIO+''','''+CLIENTES.UF+''','''+CLIENTES.CEP+''','''+CLIENTES.Fax+''','''+CLIENTES.CEL+''','''+CLIENTES.TELEFONE+''','''+CLIENTES.EMAIL+''','''+CLIENTES.EMAIL+''','''+CLIENTES.SITE+''','''+CLIENTES.OBS+''' ) ';
      ShowMEssage(IBQuery2.SQL.Text);
      IBQuery2.ExecSQL;
      IBQuery2.Transaction.Commit;
      Result := true;
      Except
      Result := False;
      end;
    end;
    Function TModeloForm13.alterar():Boolean;
    begin
     IBQuery2.SQL.Clear;
     try
      IBQuery2.SQL.Add('update clientes');
      IBQuery2.SQL.Add('set');
      IBQuery2.SQL.Add('STATUS = '''+CLIENTES.STATUS+''',');
      IBQuery2.SQL.Add('NOME = '''+CLIENTES.RAZAO+''',');
      IBQuery2.SQL.Add('FANTASIA = '''+CLIENTES.RAZAO+''',');
      IBQuery2.SQL.Add('CGC = '''+CLIENTES.CGC+''',');
      IBQuery2.SQL.Add('INCR = '''+CLIENTES.INCR+''',');
      IBQuery2.SQL.Add('LOGRADOURO = '''+CLIENTES.ENDe+''',');
      IBQuery2.SQL.Add('NUMERO = '+IntToStr(CLIENTES.NUMERO)+',');
      IBQuery2.SQL.Add('BAIRRO = '''+CLIENTES.BAIRRO+''',');
      IBQuery2.SQL.Add('MUNICIPIO = '''+CLIENTES.MUNICIPIO+''',');
      IBQuery2.SQL.Add('UF = '''+CLIENTES.UF+''',');
      IBQuery2.SQL.Add('CEP = '''+CLIENTES.CEP+''',');
      IBQuery2.SQL.Add('FONE = '''+CLIENTES.TELEFONE+''',');
      IBQuery2.SQL.Add('FONEFAX = '''+CLIENTES.Fax+''',');
      IBQuery2.SQL.Add('CEL = '''+CLIENTES.CEL+''',');
      IBQuery2.SQL.Add('EMAIL = '''+CLIENTES.EMAIL+''',');
      IBQuery2.SQL.Add('EMAIL2 = '''+CLIENTES.EMAIL+''',');
      IBQuery2.SQL.Add('SITE = '''+CLIENTES.SITE+''',');
      IBQuery2.SQL.Add('OBS = '''+CLIENTES.OBS+'''');
      IBQuery2.SQL.Add('where');
      IBQuery2.SQL.Add('CDCLIENTE = '+IntToStr(CLIENTES.CDCLIENTE)+'');
      IBQuery2.ExecSQL;
      IBQuery2.Transaction.Commit;
      Result := true;
      Except
      Result := False;
      end;
    end;
    Function TModeloForm13.deletar():Boolean;
    begin
      try
      IBQuery2.SQL.Text := 'DELETE FROM CLIENTES WHERE CDCLIENTE = '+IntToStr(Clientes.CDCLIENTE);
      IBQuery2.ExecSQL;
      IBQuery2.Transaction.Commit;
      Result := true;
      Except
      Result := False;
      end;
    end;
    Function TModeloForm13.buscar():Boolean;
    Var Status : String;
    begin
      IBQuery2.SQL.Clear;
      IBQuery2.SQL.Add('select *from clientes where cdcliente > 0 ');
      if(status  = 'A')then
      IBQuery2.SQL.Add('and STATUS = '''+CLIENTES.STATUS+'''');
      if(TXTNOME.Text <>  '')then
      IBQuery2.SQL.Add('and NOME = '''+CLIENTES.RAZAO+'''');
      if(TXTNOME.Text <>  '')then
      IBQuery2.SQL.Add('and FANTASIA = '''+CLIENTES.RAZAO+'''');
      if(TXTCPF.Text <>  '')then
      IBQuery2.SQL.Add('and CGC = '''+CLIENTES.CGC+'''');
      if(TXTRG.Text <>  '')then
      IBQuery2.SQL.Add('and INCR = '''+CLIENTES.INCR+'''');
      if(TXTLOGRADOURO.Text <>  '')then
      IBQuery2.SQL.Add('and LOGRADOURO = '''+CLIENTES.ENDe+'''');
      if(TXTNUMERO.Text <>  '')then
      IBQuery2.SQL.Add('and NUMERO = '+IntToStr(CLIENTES.NUMERO)+'');
      if(BAIRRO.Text <>  '')then
      IBQuery2.SQL.Add('and BAIRRO = '''+CLIENTES.BAIRRO+'''');
      if(TXTCIDADE.Text <>  '')then
      IBQuery2.SQL.Add('and MUNICIPIO = '''+CLIENTES.MUNICIPIO+'''');
      if(TXTNOME.Text <>  '')then
      IBQuery2.SQL.Add('and UF = '''+CLIENTES.UF+'''');
      if(TXTCEP.Text <>  '')then
      IBQuery2.SQL.Add('and CEP = '''+CLIENTES.CEP+'''');
      if(TXTTELEFONE.Text <>  '')then
      IBQuery2.SQL.Add('and FONE = '''+CLIENTES.TELEFONE+'''');
      if(TXTFAX.Text <>  '')then
      IBQuery2.SQL.Add('and FONEFAX = '''+CLIENTES.Fax+'''');
      if(TXTCEL.Text <>  '')then
      IBQuery2.SQL.Add('and CEL = '''+CLIENTES.CEL+'''');
      if(TXTEMAIL.Text <>  '')then
      IBQuery2.SQL.Add('and EMAIL = '''+CLIENTES.EMAIL+'''');
      if(TXTEMAIL2.Text <>  '')then
      IBQuery2.SQL.Add('and EMAIL2 = '''+CLIENTES.EMAIL+'''');
      if(TXTSITE.Text <>  '')then
      IBQuery2.SQL.Add('and SITE = '''+CLIENTES.SITE+'''');
      if(TXTobs.Text <>  '')then
      IBQuery2.SQL.Add('and OBS = '''+CLIENTES.OBS+'''');
      if(TXTCODCLI.Text <>  '')then
      IBQuery2.SQL.Add('and CDCLIENTE = '+IntToStr(CLIENTES.CDCLIENTE)+'');

    end;
    Function TModeloForm13.SelectInicial():Boolean;
    begin
      IBQuery1.Active := False;
      IBQuery1.SQL.Text := 'SELECT *FROM CLIENTES';
      IBQuery1.Active := True;
      PoPulaClasseBD();
      PoPulaTXT();
    end;
    Function TModeloForm13.Primeiro():Boolean;
    begin
       IBQuery1.First;
      PoPulaClasseBD();
      PoPulaTXT();
    end;
    Function TModeloForm13.Proximo():Boolean;
    begin
       IBQuery1.Next;
      PoPulaClasseBD();
      PoPulaTXT();
    end;
    Function TModeloForm13.Anterior():Boolean;
    begin
       IBQuery1.Prior;
      PoPulaClasseBD();
      PoPulaTXT();
    end;
    Function TModeloForm13.Ultimo():Boolean;
    begin
       IBQuery1.Last;
      PoPulaClasseBD();
      PoPulaTXT();
    end;
procedure TModeloForm13.SpeedButton7Click(Sender: TObject);
begin
  Primeiro();

end;

procedure TModeloForm13.SpeedButton8Click(Sender: TObject);
begin
Ultimo();

end;

procedure TModeloForm13.SpeedButton5Click(Sender: TObject);
begin
Proximo();
end;

procedure TModeloForm13.SpeedButton6Click(Sender: TObject);
begin
Anterior();
end;

procedure TModeloForm13.SpeedButton1Click(Sender: TObject);
begin
  inherited;
Limpar();
end;

procedure TModeloForm13.SpeedButton2Click(Sender: TObject);
begin
  inherited;
 PoPulaClasseTXT();
 alterar();
end;

procedure TModeloForm13.SpeedButton3Click(Sender: TObject);
begin
  inherited;
   PoPulaClasseTXT();
   inserir();
end;

procedure TModeloForm13.SpeedButton4Click(Sender: TObject);
begin
  inherited;
     PoPulaClasseTXT();
     deletar();
end;

procedure TModeloForm13.SpeedButton9Click(Sender: TObject);
begin
  inherited;
  PoPulaClasseTXT();
  buscar();
end;

procedure TModeloForm13.FormCreate(Sender: TObject);
begin
SelectInicial();

end;

end.
