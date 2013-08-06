inherited Mov0006: TMov0006
  Left = 476
  Caption = 'Mov0006 - Manuten'#231#227'o em lan'#231'amentos cont'#225'beis'
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    inherited BtnFecCon: TBrvSpeedButton
      Left = 33
      ExplicitLeft = 33
    end
    inherited SbtLocali: TBrvSpeedButton
      Left = 61
      Top = 4
      ExplicitLeft = 61
      ExplicitTop = 4
    end
  end
  inherited PnlFundo: TPanel
    inherited DBGLancto: TBrvDbGrid
      OnDblClick = DBGLanctoDblClick
    end
  end
end
