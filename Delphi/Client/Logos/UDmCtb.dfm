object DmCtb: TDmCtb
  OldCreateOrder = False
  Height = 380
  Width = 535
  object QpConta: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 8
    Top = 6
  end
  object QcLanCon: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    BrShowFieldName = False
    BrQueryCode = 0
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 72
    Top = 6
    object QcLanConNrLancto: TIntegerField
      FieldName = 'NrLancto'
    end
    object QcLanConCdEmpres: TIntegerField
      FieldName = 'CdEmpres'
    end
    object QcLanConDtLancto: TDateTimeField
      FieldName = 'DtLancto'
    end
    object QcLanConNrConDeb: TIntegerField
      FieldName = 'NrConDeb'
    end
    object QcLanConNrConCre: TIntegerField
      FieldName = 'NrConCre'
    end
    object QcLanConVrLancto: TFloatField
      FieldName = 'VrLancto'
    end
    object QcLanConNrDocto: TStringField
      FieldName = 'NrDocto'
      Size = 30
    end
    object QcLanConCdHistor: TIntegerField
      FieldName = 'CdHistor'
    end
    object QcLanConDsHistor: TMemoField
      FieldName = 'DsHistor'
      BlobType = ftMemo
    end
    object QcLanConNmFormul: TStringField
      FieldName = 'NmFormul'
    end
    object QcLanConCdCeCuDe: TIntegerField
      FieldName = 'CdCeCuDe'
    end
    object QcLanConCdCeCuCr: TIntegerField
      FieldName = 'CdCeCuCr'
    end
    object QcLanConNrClaDeb: TStringField
      FieldName = 'NrClaDeb'
      Size = 60
    end
    object QcLanConNrClaCre: TStringField
      FieldName = 'NrClaCre'
      Size = 60
    end
    object QcLanConSnEncerr: TStringField
      FieldName = 'SnEncerr'
      Size = 1
    end
    object QcLanConNrPlano: TIntegerField
      FieldName = 'NrPlano'
    end
  end
end
