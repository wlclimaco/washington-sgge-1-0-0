unit UBrvISO;

interface

uses BrvGeraRelatorio, BrvClientDataSet, UDmSrvApl, BrvListParam, Dialogs, Controls, SysUtils;

procedure GerarRelatorioRNC(pNrRNC:String; pNmForm: String);

implementation

procedure GerarRelatorioRNC(pNrRNC:String; pNmForm: String);
var lBrGerRel : TBrvGeraRelatorio;
    lCpQ005   : TBrvClientDataSet;
    lLsParam  : TBrvListParam;
begin
      try
          lLsParam  := TBrvListParam.Create(nil);
          lLsParam.Add('NrRNC', '', '', pNrRNC);

          lCpQ005              := TBrvClientDataSet.Create(nil);
          lCpQ005.BrDicionario := DmSrvApl.BrvDicionario;
          lCpQ005.Data         := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(247,
                                                                       lLsParam.AsBrParam, pNmForm);
          lBrGerRel                  := TBrvGeraRelatorio.Create(nil);
          lBrGerRel.BrDicionario     := DmSrvApl.BrvDicionario;
          lBrGerRel.SQLConnectionImp := DmSrvApl.SrvImpres;

          lBrGerRel.IniciarRelatorio('QRL0007','G', pNmForm);
          lBrGerRel.InserirParametroSQL('NrRnc', pNrRNC);
          lBrGerRel.ImprimirRelatorio;

          Sleep(1000);

          lBrGerRel.IniciarRelatorio('QRL0006','G',pNmForm);
          lBrGerRel.InserirParametroSQL('NrRnc',pNrRNC);
          lBrGerRel.ImprimirRelatorio;
      finally
          FreeAndNil(lBrGerRel);
          FreeAndNil(lCpQ005);
          FreeAndNil(lLsParam);
      end;
end;

end.
