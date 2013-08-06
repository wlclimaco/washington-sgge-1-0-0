inherited Con0005: TCon0005
  BorderIcons = [biSystemMenu, biMinimize]
  BorderStyle = bsSingle
  Caption = 'Con0005'
  ClientHeight = 574
  ClientWidth = 779
  FormStyle = fsNormal
  Visible = False
  ExplicitWidth = 785
  ExplicitHeight = 599
  PixelsPerInch = 96
  TextHeight = 13
  inherited NavBarra: TBrvDbNavCop
    Width = 779
    ExplicitWidth = 779
    inherited BrvSpeedButton1: TBrvSpeedButton
      Left = 752
      ExplicitLeft = 733
    end
    inherited SbtAjuda: TBrvSpeedButton
      Left = 727
      ExplicitLeft = 708
    end
  end
  inherited PnlFundo: TPanel
    Width = 779
    Height = 544
    ExplicitWidth = 779
    ExplicitHeight = 544
    object PgcPrincipal: TPageControl
      AlignWithMargins = True
      Left = 4
      Top = 4
      Width = 767
      Height = 532
      ActivePage = TbsCTRC
      Align = alClient
      TabHeight = 26
      TabOrder = 0
      object TbsCTRC: TTabSheet
        Caption = 'C.T.R.C.'
        ImageIndex = 1
        object PnlLocalizar: TPanel
          AlignWithMargins = True
          Left = 3
          Top = 3
          Width = 753
          Height = 218
          Align = alTop
          BevelKind = bkFlat
          BevelOuter = bvNone
          TabOrder = 0
          object Label2: TLabel
            Left = 7
            Top = 9
            Width = 56
            Height = 13
            Caption = 'Dt. Emiss'#227'o'
          end
          object Label48: TLabel
            Left = 437
            Top = 44
            Width = 41
            Height = 13
            Caption = 'Situa'#231#227'o'
          end
          object Label49: TLabel
            Left = 7
            Top = 44
            Width = 46
            Height = 13
            Caption = 'Cd. Fiscal'
          end
          object Label50: TLabel
            Left = 204
            Top = 9
            Width = 72
            Height = 13
            Caption = 'Frete Pago por'
          end
          object Label51: TLabel
            Left = 229
            Top = 44
            Width = 47
            Height = 13
            Caption = 'Fatura N'#186
          end
          object Label43: TLabel
            Left = 8
            Top = 114
            Width = 33
            Height = 13
            Caption = 'Ve'#237'culo'
          end
          object Label28: TLabel
            Left = 387
            Top = 9
            Width = 91
            Height = 13
            Caption = 'Tipo de Transporte'
          end
          object Label72: TLabel
            Left = 8
            Top = 184
            Width = 65
            Height = 13
            Caption = 'Calculado at'#233
          end
          object Label73: TLabel
            Left = 8
            Top = 147
            Width = 33
            Height = 13
            Caption = 'Cidade'
          end
          object Label46: TLabel
            Left = 465
            Top = 114
            Width = 13
            Height = 13
            Caption = 'UF'
          end
          object Label75: TLabel
            Left = 465
            Top = 149
            Width = 13
            Height = 13
            Caption = 'UF'
          end
          object Label76: TLabel
            Left = 7
            Top = 79
            Width = 45
            Height = 13
            Caption = 'Motorista'
          end
          object BrvDbEdit1: TBrvDbEdit
            Left = 80
            Top = 6
            Width = 86
            Height = 21
            Color = clBtnFace
            DataField = 'DtEmissa'
            DataSource = DsT013
            TabOrder = 0
            BrAlignment = taRightJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object BrvDbEdit2: TBrvDbEdit
            Left = 80
            Top = 41
            Width = 86
            Height = 21
            Color = clBtnFace
            DataField = 'CdFiscal'
            DataSource = DsT013
            TabOrder = 1
            BrAlignment = taRightJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object BrvDbEdit3: TBrvDbEdit
            Left = 282
            Top = 41
            Width = 86
            Height = 21
            Color = clBtnFace
            DataField = 'NrFatura'
            DataSource = DsT013
            TabOrder = 2
            BrAlignment = taRightJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object EdtSnFrete: TBrvDbEdit
            Left = 282
            Top = 6
            Width = 86
            Height = 21
            Color = clBtnFace
            DataField = 'SnFrete'
            DataSource = DsT013
            TabOrder = 3
            BrAlignment = taLeftJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object EdtTpTransp: TBrvDbEdit
            Left = 484
            Top = 6
            Width = 244
            Height = 21
            Color = clBtnFace
            DataField = 'TpTransp'
            DataSource = DsT013
            TabOrder = 4
            BrAlignment = taLeftJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object EdtStCtrc: TBrvDbEdit
            Left = 484
            Top = 44
            Width = 244
            Height = 21
            Color = clBtnFace
            DataField = 'StCtrc'
            DataSource = DsT013
            TabOrder = 5
            BrAlignment = taLeftJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object BrvDbEdit7: TBrvDbEdit
            Left = 80
            Top = 76
            Width = 86
            Height = 21
            Color = clBtnFace
            DataField = 'CdMotori'
            DataSource = DsT013
            TabOrder = 6
            BrAlignment = taRightJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object BrvDbEdit8: TBrvDbEdit
            Left = 172
            Top = 76
            Width = 556
            Height = 21
            Color = clBtnFace
            DataField = 'NmMotori'
            DataSource = DsT013
            TabOrder = 7
            BrAlignment = taLeftJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object BrvDbEdit9: TBrvDbEdit
            Left = 80
            Top = 111
            Width = 86
            Height = 21
            Color = clBtnFace
            DataField = 'CdVeicul'
            DataSource = DsT013
            TabOrder = 8
            BrAlignment = taRightJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object BrvDbEdit10: TBrvDbEdit
            Left = 172
            Top = 111
            Width = 269
            Height = 21
            Color = clBtnFace
            DataField = 'NrPlaVei'
            DataSource = DsT013
            TabOrder = 9
            BrAlignment = taLeftJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object BrvDbEdit11: TBrvDbEdit
            Left = 484
            Top = 111
            Width = 244
            Height = 21
            Color = clBtnFace
            DataField = 'CdEstado'
            DataSource = DsT013
            TabOrder = 10
            BrAlignment = taLeftJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object BrvDbEdit12: TBrvDbEdit
            Left = 80
            Top = 146
            Width = 86
            Height = 21
            Color = clBtnFace
            DataField = 'CpCidCal'
            DataSource = DsT013
            TabOrder = 11
            BrAlignment = taRightJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object BrvDbEdit13: TBrvDbEdit
            Left = 172
            Top = 146
            Width = 269
            Height = 21
            Color = clBtnFace
            TabOrder = 12
            BrAlignment = taLeftJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object BrvDbEdit14: TBrvDbEdit
            Left = 484
            Top = 146
            Width = 244
            Height = 21
            Color = clBtnFace
            TabOrder = 13
            BrAlignment = taLeftJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
          object BrvDbEdit15: TBrvDbEdit
            Left = 80
            Top = 181
            Width = 648
            Height = 21
            Color = clBtnFace
            DataField = 'DsCalcTr'
            DataSource = DsT013
            TabOrder = 14
            BrAlignment = taLeftJustify
            BrVisibleButton = False
            BrFunctionButton = TpDbConsulta
            BrQueryConsulta = 0
          end
        end
        object PgcDeReCo: TPageControl
          AlignWithMargins = True
          Left = 3
          Top = 227
          Width = 753
          Height = 266
          ActivePage = TbsRemete
          Align = alClient
          MultiLine = True
          TabHeight = 26
          TabOrder = 1
          object TbsRemete: TTabSheet
            Caption = 'Remetente'
            object Label3: TLabel
              Left = 3
              Top = 15
              Width = 53
              Height = 13
              Caption = 'Remetente'
            end
            object Label5: TLabel
              Left = 3
              Top = 58
              Width = 45
              Height = 13
              Caption = 'Endere'#231'o'
            end
            object Label7: TLabel
              Left = 3
              Top = 93
              Width = 28
              Height = 13
              Caption = 'Bairro'
            end
            object Label6: TLabel
              Left = 3
              Top = 128
              Width = 43
              Height = 13
              Caption = 'Munic'#237'pio'
            end
            object Label8: TLabel
              Left = 459
              Top = 128
              Width = 33
              Height = 13
              Alignment = taRightJustify
              Caption = 'Estado'
            end
            object Label10: TLabel
              Left = 3
              Top = 168
              Width = 66
              Height = 13
              Caption = 'C.N.P.J (M.F)'
            end
            object Label12: TLabel
              Left = 259
              Top = 169
              Width = 10
              Height = 13
              Caption = 'IE'
            end
            object Label85: TLabel
              Left = 575
              Top = 128
              Width = 19
              Height = 13
              Caption = 'CEP'
            end
            object BrvDbEdit17: TBrvDbEdit
              Left = 73
              Top = 12
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CdRemete'
              DataSource = DsT013
              TabOrder = 0
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object BrvDbEdit18: TBrvDbEdit
              Left = 165
              Top = 12
              Width = 559
              Height = 21
              Color = clBtnFace
              DataField = 'RsRemete'
              DataSource = DsT013
              TabOrder = 1
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object BrvDbEdit20: TBrvDbEdit
              Left = 73
              Top = 55
              Width = 651
              Height = 21
              Color = clBtnFace
              DataField = 'EdRemete'
              DataSource = DsT013
              TabOrder = 2
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object BrvDbEdit21: TBrvDbEdit
              Left = 73
              Top = 90
              Width = 651
              Height = 21
              Color = clBtnFace
              DataField = 'BiRemete'
              DataSource = DsT013
              TabOrder = 3
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object BrvDbEdit22: TBrvDbEdit
              Left = 73
              Top = 125
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CpCidRem'
              DataSource = DsT013
              TabOrder = 4
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object BrvDbEdit23: TBrvDbEdit
              Left = 165
              Top = 125
              Width = 288
              Height = 21
              Color = clBtnFace
              DataField = 'NmCidRem'
              DataSource = DsT013
              TabOrder = 5
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object BrvDbEdit19: TBrvDbEdit
              Left = 498
              Top = 125
              Width = 71
              Height = 21
              Color = clBtnFace
              DataField = 'CdEstRem'
              DataSource = DsT013
              TabOrder = 6
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object BrvDbEdit24: TBrvDbEdit
              Left = 600
              Top = 125
              Width = 124
              Height = 21
              Color = clBtnFace
              DataField = 'CpRemete'
              DataSource = DsT013
              TabOrder = 7
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object BrvDbEdit25: TBrvDbEdit
              Left = 75
              Top = 165
              Width = 176
              Height = 21
              Color = clBtnFace
              DataField = 'NrCnpjRe'
              DataSource = DsT013
              TabOrder = 8
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object BrvDbEdit26: TBrvDbEdit
              Left = 275
              Top = 165
              Width = 178
              Height = 21
              Color = clBtnFace
              DataField = 'IeRemete'
              DataSource = DsT013
              TabOrder = 9
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
          end
          object TbsDestin: TTabSheet
            Caption = 'Destinat'#225'rio'
            ImageIndex = 1
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Label9: TLabel
              Left = 3
              Top = 15
              Width = 58
              Height = 13
              Caption = 'Destinat'#225'rio'
            end
            object Label13: TLabel
              Left = 3
              Top = 58
              Width = 45
              Height = 13
              Caption = 'Endere'#231'o'
            end
            object Label16: TLabel
              Left = 3
              Top = 93
              Width = 28
              Height = 13
              Caption = 'Bairro'
            end
            object Label17: TLabel
              Left = 3
              Top = 128
              Width = 43
              Height = 13
              Caption = 'Munic'#237'pio'
            end
            object Label19: TLabel
              Left = 459
              Top = 128
              Width = 33
              Height = 13
              Alignment = taRightJustify
              Caption = 'Estado'
            end
            object Label22: TLabel
              Left = 3
              Top = 168
              Width = 66
              Height = 13
              Caption = 'C.N.P.J (M.F)'
            end
            object Label24: TLabel
              Left = 259
              Top = 169
              Width = 10
              Height = 13
              Caption = 'IE'
            end
            object Label44: TLabel
              Left = 575
              Top = 128
              Width = 19
              Height = 13
              Caption = 'CEP'
            end
            object DBEdit1: TBrvDbEdit
              Left = 73
              Top = 12
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CdDestin'
              DataSource = DsT013
              TabOrder = 0
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit2: TBrvDbEdit
              Left = 165
              Top = 12
              Width = 559
              Height = 21
              Color = clBtnFace
              DataField = 'RsDestin'
              DataSource = DsT013
              TabOrder = 1
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit3: TBrvDbEdit
              Left = 73
              Top = 55
              Width = 651
              Height = 21
              Color = clBtnFace
              DataField = 'EdDestin'
              DataSource = DsT013
              TabOrder = 2
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit4: TBrvDbEdit
              Left = 73
              Top = 90
              Width = 649
              Height = 21
              Color = clBtnFace
              DataField = 'BiDestin'
              DataSource = DsT013
              TabOrder = 3
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit5: TBrvDbEdit
              Left = 73
              Top = 125
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CpCidDes'
              DataSource = DsT013
              TabOrder = 4
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit6: TBrvDbEdit
              Left = 165
              Top = 125
              Width = 288
              Height = 21
              Color = clBtnFace
              DataField = 'NmCidDes'
              DataSource = DsT013
              TabOrder = 5
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit7: TBrvDbEdit
              Left = 498
              Top = 125
              Width = 71
              Height = 21
              Color = clBtnFace
              DataField = 'CdEstDes'
              DataSource = DsT013
              TabOrder = 6
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit8: TBrvDbEdit
              Left = 600
              Top = 125
              Width = 124
              Height = 21
              Color = clBtnFace
              DataField = 'CpDestin'
              DataSource = DsT013
              TabOrder = 7
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit9: TBrvDbEdit
              Left = 73
              Top = 165
              Width = 176
              Height = 21
              Color = clBtnFace
              DataField = 'NrCnpjDe'
              DataSource = DsT013
              TabOrder = 8
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit10: TBrvDbEdit
              Left = 275
              Top = 165
              Width = 178
              Height = 21
              Color = clBtnFace
              DataField = 'IeDestin'
              DataSource = DsT013
              TabOrder = 9
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
          end
          object TbsConsig: TTabSheet
            Caption = 'Consignat'#225'rio'
            ImageIndex = 2
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Label71: TLabel
              Left = 476
              Top = 169
              Width = 93
              Height = 13
              Caption = 'Frete consignat'#225'rio'
            end
            object Label1: TLabel
              Left = 3
              Top = 15
              Width = 66
              Height = 13
              Caption = 'Consignat'#225'rio'
            end
            object Label11: TLabel
              Left = 3
              Top = 58
              Width = 45
              Height = 13
              Caption = 'Endere'#231'o'
            end
            object Label18: TLabel
              Left = 3
              Top = 93
              Width = 28
              Height = 13
              Caption = 'Bairro'
            end
            object Label20: TLabel
              Left = 3
              Top = 128
              Width = 43
              Height = 13
              Caption = 'Munic'#237'pio'
            end
            object Label23: TLabel
              Left = 459
              Top = 128
              Width = 33
              Height = 13
              Alignment = taRightJustify
              Caption = 'Estado'
            end
            object Label27: TLabel
              Left = 3
              Top = 168
              Width = 66
              Height = 13
              Caption = 'C.N.P.J (M.F)'
            end
            object Label29: TLabel
              Left = 259
              Top = 169
              Width = 10
              Height = 13
              Caption = 'IE'
            end
            object Label86: TLabel
              Left = 575
              Top = 128
              Width = 19
              Height = 13
              Caption = 'CEP'
            end
            object DBEdit11: TBrvDbEdit
              Left = 73
              Top = 12
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CdConsig'
              DataSource = DsT013
              TabOrder = 0
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit12: TBrvDbEdit
              Left = 165
              Top = 12
              Width = 559
              Height = 21
              Color = clBtnFace
              DataField = 'RsConsig'
              DataSource = DsT013
              TabOrder = 1
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit13: TBrvDbEdit
              Left = 73
              Top = 55
              Width = 651
              Height = 21
              Color = clBtnFace
              DataField = 'EdConsig'
              DataSource = DsT013
              TabOrder = 2
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit14: TBrvDbEdit
              Left = 73
              Top = 90
              Width = 651
              Height = 21
              Color = clBtnFace
              DataField = 'BiConsig'
              DataSource = DsT013
              TabOrder = 3
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit15: TBrvDbEdit
              Left = 73
              Top = 125
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CpCidCon'
              DataSource = DsT013
              TabOrder = 4
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit16: TBrvDbEdit
              Left = 165
              Top = 125
              Width = 288
              Height = 21
              Color = clBtnFace
              DataField = 'NmCidCon'
              DataSource = DsT013
              TabOrder = 5
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit17: TBrvDbEdit
              Left = 498
              Top = 125
              Width = 71
              Height = 21
              Color = clBtnFace
              DataField = 'CdEstCon'
              DataSource = DsT013
              TabOrder = 6
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit18: TBrvDbEdit
              Left = 599
              Top = 125
              Width = 125
              Height = 21
              Color = clBtnFace
              DataField = 'CpConsig'
              DataSource = DsT013
              TabOrder = 7
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit19: TBrvDbEdit
              Left = 73
              Top = 165
              Width = 176
              Height = 21
              Color = clBtnFace
              DataField = 'NrCnpjCo'
              DataSource = DsT013
              TabOrder = 8
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit20: TBrvDbEdit
              Left = 275
              Top = 165
              Width = 178
              Height = 21
              Color = clBtnFace
              DataField = 'IeConsig'
              DataSource = DsT013
              TabOrder = 9
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
          end
          object TabRedesp: TTabSheet
            Caption = 'Redespacho'
            ImageIndex = 6
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Label47: TLabel
              Left = 3
              Top = 14
              Width = 59
              Height = 13
              Caption = 'Redespacho'
            end
            object Label87: TLabel
              Left = 3
              Top = 58
              Width = 41
              Height = 13
              Caption = 'Empresa'
            end
            object Label52: TLabel
              Left = 3
              Top = 101
              Width = 26
              Height = 13
              Caption = 'Frete'
            end
            object DBEdit21: TBrvDbEdit
              Left = 73
              Top = 12
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CdRedesp'
              DataSource = DsT013
              TabOrder = 0
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit22: TBrvDbEdit
              Left = 73
              Top = 55
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CdEmpRed'
              DataSource = DsT013
              TabOrder = 1
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit23: TBrvDbEdit
              Left = 73
              Top = 98
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'SnReFre'
              DataSource = DsT013
              TabOrder = 2
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
          end
          object TbsComFre: TTabSheet
            Caption = 'Composi'#231#227'o do frete'
            ImageIndex = 3
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Label32: TLabel
              Left = 257
              Top = 15
              Width = 53
              Height = 13
              Caption = 'Frete Valor'
            end
            object Label33: TLabel
              Left = 553
              Top = 15
              Width = 43
              Height = 13
              Caption = 'SEC/CAT'
            end
            object Label34: TLabel
              Left = 3
              Top = 42
              Width = 47
              Height = 13
              Caption = 'Despacho'
            end
            object Label35: TLabel
              Left = 257
              Top = 42
              Width = 38
              Height = 13
              Caption = 'Ped'#225'gio'
            end
            object Label37: TLabel
              Left = 257
              Top = 70
              Width = 75
              Height = 13
              Caption = 'Total Presta'#231#227'o'
            end
            object Label38: TLabel
              Left = 3
              Top = 69
              Width = 60
              Height = 13
              Caption = 'Base C'#225'lculo'
            end
            object Label40: TLabel
              Left = 556
              Top = 70
              Width = 49
              Height = 13
              Caption = 'Aliq. ICMS'
            end
            object Label42: TLabel
              Left = 3
              Top = 96
              Width = 67
              Height = 13
              Caption = 'Valor do ICMS'
            end
            object Label55: TLabel
              Left = 556
              Top = 42
              Width = 33
              Height = 13
              Caption = 'Outros'
            end
            object Label14: TLabel
              Left = 257
              Top = 96
              Width = 73
              Height = 13
              Caption = '% de desconto'
            end
            object Label56: TLabel
              Left = 556
              Top = 96
              Width = 68
              Height = 13
              Caption = 'Valor do desc.'
            end
            object Label53: TLabel
              Left = 3
              Top = 15
              Width = 80
              Height = 13
              Caption = 'Frete Peso/Volu.'
            end
            object Label36: TLabel
              Left = 3
              Top = 154
              Width = 63
              Height = 13
              Caption = 'Observa'#231#245'es'
            end
            object Label80: TLabel
              Left = 5
              Top = 123
              Width = 67
              Height = 13
              Caption = 'C'#243'd. da Faixa'
            end
            object DBEdit24: TBrvDbEdit
              Left = 89
              Top = 12
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'VrFreTep'
              DataSource = DsT013
              TabOrder = 0
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit25: TBrvDbEdit
              Left = 89
              Top = 39
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'VrDespac'
              DataSource = DsT013
              TabOrder = 1
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit26: TBrvDbEdit
              Left = 89
              Top = 66
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'VrBaseca'
              DataSource = DsT013
              TabOrder = 2
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit27: TBrvDbEdit
              Left = 89
              Top = 93
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'VrICMS'
              DataSource = DsT013
              TabOrder = 3
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit28: TBrvDbEdit
              Left = 353
              Top = 12
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'VrFreTev'
              DataSource = DsT013
              TabOrder = 4
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit29: TBrvDbEdit
              Left = 353
              Top = 39
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'VrPedagi'
              DataSource = DsT013
              TabOrder = 5
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit30: TBrvDbEdit
              Left = 353
              Top = 66
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'VrTotPre'
              DataSource = DsT013
              TabOrder = 6
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit31: TBrvDbEdit
              Left = 353
              Top = 93
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'PcDescom'
              DataSource = DsT013
              TabOrder = 7
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit32: TBrvDbEdit
              Left = 640
              Top = 12
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'VrSecCat'
              DataSource = DsT013
              TabOrder = 8
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit33: TBrvDbEdit
              Left = 640
              Top = 39
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'VrOutros'
              DataSource = DsT013
              TabOrder = 9
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit34: TBrvDbEdit
              Left = 640
              Top = 66
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'PcAlIcms'
              DataSource = DsT013
              TabOrder = 10
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit35: TBrvDbEdit
              Left = 640
              Top = 93
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'VrDescon'
              DataSource = DsT013
              TabOrder = 11
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object MEMOBCTRC: TDBMemo
              AlignWithMargins = True
              Left = 89
              Top = 154
              Width = 636
              Height = 73
              Margins.Left = 89
              Margins.Right = 20
              Align = alBottom
              Color = clBtnFace
              DataField = 'OBCTRC'
              DataSource = DsT013
              MaxLength = 160
              ReadOnly = True
              ScrollBars = ssVertical
              TabOrder = 12
            end
            object BrvDbEdit4: TBrvDbEdit
              Left = 89
              Top = 120
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CdFaixa'
              DataSource = DsT013
              TabOrder = 13
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
          end
          object TabEntreg: TTabSheet
            Caption = 'Entrega'
            ImageIndex = 7
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Label54: TLabel
              Left = 441
              Top = 14
              Width = 131
              Height = 13
              Alignment = taRightJustify
              Caption = 'Data prevista para entrega'
            end
            object Label57: TLabel
              Left = 3
              Top = 54
              Width = 79
              Height = 13
              Caption = 'Data de entrega'
            end
            object Label58: TLabel
              Left = 441
              Top = 54
              Width = 151
              Height = 13
              Alignment = taRightJustify
              Caption = 'Data da entrega  do rastreador'
            end
            object Label59: TLabel
              Left = 3
              Top = 87
              Width = 101
              Height = 13
              Caption = 'C'#243'digo da ocorr'#234'ncia'
            end
            object Label60: TLabel
              Left = 3
              Top = 120
              Width = 119
              Height = 13
              Caption = 'Observa'#231#245'es da entrega'
            end
            object Label77: TLabel
              Left = 3
              Top = 209
              Width = 54
              Height = 13
              Caption = 'Localiza'#231#227'o'
            end
            object Label78: TLabel
              Left = 3
              Top = 181
              Width = 109
              Height = 13
              Caption = 'Data do Rastreamento'
            end
            object Label79: TLabel
              Left = 441
              Top = 186
              Width = 109
              Height = 13
              Alignment = taRightJustify
              Caption = 'Hora do Rastreamento'
            end
            object Label4: TLabel
              Left = 3
              Top = 15
              Width = 116
              Height = 13
              Caption = 'Data limite para entrega'
            end
            object DBEdit36: TBrvDbEdit
              Left = 153
              Top = 11
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'DtLiment'
              DataSource = DsT013
              TabOrder = 0
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit37: TBrvDbEdit
              Left = 153
              Top = 51
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'DtEntreg'
              DataSource = DsT013
              TabOrder = 1
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit38: TBrvDbEdit
              Left = 153
              Top = 84
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CdOcorre'
              DataSource = DsT013
              TabOrder = 2
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit40: TBrvDbEdit
              Left = 153
              Top = 178
              Width = 232
              Height = 21
              Color = clBtnFace
              DataField = 'DtRastre'
              DataSource = DsT013
              TabOrder = 3
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit41: TBrvDbEdit
              Left = 153
              Top = 206
              Width = 573
              Height = 21
              Color = clBtnFace
              DataField = 'DsLocali'
              DataSource = DsT013
              TabOrder = 4
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit42: TBrvDbEdit
              Left = 599
              Top = 11
              Width = 127
              Height = 21
              Color = clBtnFace
              DataField = 'DtPreEnt'
              DataSource = DsT013
              TabOrder = 5
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit43: TBrvDbEdit
              Left = 599
              Top = 51
              Width = 127
              Height = 21
              Color = clBtnFace
              DataField = 'DtEntMot'
              DataSource = DsT013
              TabOrder = 6
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit44: TBrvDbEdit
              Left = 599
              Top = 178
              Width = 127
              Height = 21
              Color = clBtnFace
              DataField = 'HoRastre'
              DataSource = DsT013
              TabOrder = 7
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit66: TBrvDbEdit
              Left = 245
              Top = 84
              Width = 481
              Height = 21
              Color = clBtnFace
              DataField = 'DsOcorre'
              DataSource = DsT013
              TabOrder = 8
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBMemo1: TDBMemo
              Left = 153
              Top = 117
              Width = 573
              Height = 53
              Color = clBtnFace
              DataField = 'ObEntreg'
              DataSource = DsT013
              ScrollBars = ssVertical
              TabOrder = 9
            end
          end
          object TabSheet1: TTabSheet
            Caption = 'Mercad. transportadas'
            ImageIndex = 8
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Label61: TLabel
              Left = 3
              Top = 16
              Width = 89
              Height = 13
              Caption = 'Natureza da carga'
            end
            object Label62: TLabel
              Left = 3
              Top = 42
              Width = 36
              Height = 13
              Caption = 'Esp'#233'cie'
            end
            object Label63: TLabel
              Left = 3
              Top = 71
              Width = 29
              Height = 13
              Caption = 'Marca'
            end
            object Label64: TLabel
              Left = 3
              Top = 98
              Width = 31
              Height = 13
              Caption = 'Coleta'
            end
            object Label65: TLabel
              Left = 3
              Top = 125
              Width = 38
              Height = 13
              Caption = 'Entrega'
            end
            object Label66: TLabel
              Left = 3
              Top = 152
              Width = 56
              Height = 13
              Caption = 'Quantidade'
            end
            object Label67: TLabel
              Left = 289
              Top = 152
              Width = 23
              Height = 13
              Caption = 'Peso'
            end
            object Label68: TLabel
              Left = 545
              Top = 152
              Width = 61
              Height = 13
              Caption = 'Peso lota'#231#227'o'
            end
            object Label69: TLabel
              Left = 3
              Top = 179
              Width = 123
              Height = 13
              Caption = 'Quant. de metros c'#250'bicos'
            end
            object Label70: TLabel
              Left = 289
              Top = 181
              Width = 95
              Height = 13
              Caption = 'Valor da mercadoria'
            end
            object DBEdit45: TBrvDbEdit
              Left = 153
              Top = 13
              Width = 573
              Height = 21
              Color = clBtnFace
              DataField = 'DsNatCar'
              DataSource = DsT013
              TabOrder = 0
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit46: TBrvDbEdit
              Left = 153
              Top = 39
              Width = 573
              Height = 21
              Color = clBtnFace
              DataField = 'DsEspec'
              DataSource = DsT013
              TabOrder = 1
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit47: TBrvDbEdit
              Left = 153
              Top = 68
              Width = 573
              Height = 21
              Color = clBtnFace
              DataField = 'DsMarca'
              DataSource = DsT013
              TabOrder = 2
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit48: TBrvDbEdit
              Left = 153
              Top = 95
              Width = 573
              Height = 21
              Color = clBtnFace
              DataField = 'DsColet'
              DataSource = DsT013
              TabOrder = 3
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit49: TBrvDbEdit
              Left = 153
              Top = 122
              Width = 573
              Height = 21
              Color = clBtnFace
              DataField = 'DsEntreg'
              DataSource = DsT013
              TabOrder = 4
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit50: TBrvDbEdit
              Left = 153
              Top = 149
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'QtCarga'
              DataSource = DsT013
              TabOrder = 5
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit51: TBrvDbEdit
              Left = 401
              Top = 149
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'NrPeso'
              DataSource = DsT013
              TabOrder = 6
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit52: TBrvDbEdit
              Left = 401
              Top = 176
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'VrMercad'
              DataSource = DsT013
              TabOrder = 7
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit53: TBrvDbEdit
              Left = 640
              Top = 149
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'NrPesLot'
              DataSource = DsT013
              TabOrder = 8
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit67: TBrvDbEdit
              Left = 153
              Top = 176
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'QtMetCub'
              DataSource = DsT013
              TabOrder = 9
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
          end
          object TbsEDI: TTabSheet
            Caption = 'EDI'
            ImageIndex = 4
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Label15: TLabel
              Left = 12
              Top = 13
              Width = 102
              Height = 13
              Caption = 'Conhec. Embarcados'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = []
              ParentFont = False
            end
            object Label21: TLabel
              Left = 12
              Top = 69
              Width = 62
              Height = 13
              Caption = 'XML Entrega'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = []
              ParentFont = False
            end
            object Label25: TLabel
              Left = 12
              Top = 41
              Width = 81
              Height = 13
              Caption = 'Ocor. na Entrega'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = []
              ParentFont = False
            end
            object Label26: TLabel
              Left = 12
              Top = 97
              Width = 62
              Height = 13
              Caption = 'XML Rastrea'
              Font.Charset = DEFAULT_CHARSET
              Font.Color = clWindowText
              Font.Height = -11
              Font.Name = 'MS Sans Serif'
              Font.Style = []
              ParentFont = False
            end
            object DBEdit54: TBrvDbEdit
              Left = 161
              Top = 12
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'NmArqEdi'
              DataSource = DsT013
              TabOrder = 0
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit55: TBrvDbEdit
              Left = 161
              Top = 39
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'NmArqOco'
              DataSource = DsT013
              TabOrder = 1
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit56: TBrvDbEdit
              Left = 161
              Top = 66
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'NmXmlEnt'
              DataSource = DsT013
              TabOrder = 2
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit57: TBrvDbEdit
              Left = 161
              Top = 93
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'NmXmlRas'
              DataSource = DsT013
              TabOrder = 3
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
          end
          object TbsOutros: TTabSheet
            Caption = 'Outros'
            ImageIndex = 5
            ExplicitLeft = 0
            ExplicitTop = 0
            ExplicitWidth = 0
            ExplicitHeight = 0
            object Label31: TLabel
              Left = 3
              Top = 96
              Width = 125
              Height = 13
              Caption = 'Hist'#243'rico de cancelamento'
            end
            object Label39: TLabel
              Left = 3
              Top = 150
              Width = 122
              Height = 13
              Caption = 'Usu'#225'rio de Cancelamento'
            end
            object Label41: TLabel
              Left = 3
              Top = 123
              Width = 39
              Height = 13
              Caption = 'Usu'#225'rio '
            end
            object Label74: TLabel
              Left = 3
              Top = 69
              Width = 50
              Height = 13
              Caption = 'Formul'#225'rio'
            end
            object Label45: TLabel
              Left = 3
              Top = 15
              Width = 112
              Height = 13
              Caption = 'Tipo de c'#225'lculo do frete'
            end
            object Label30: TLabel
              Left = 3
              Top = 42
              Width = 107
              Height = 13
              Caption = 'Data do cancelamento'
            end
            object DBEdit59: TBrvDbEdit
              Left = 170
              Top = 66
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'NMFORMUL'
              DataSource = DsT013
              TabOrder = 0
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit60: TBrvDbEdit
              Left = 170
              Top = 39
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'DtCancel'
              DataSource = DsT013
              TabOrder = 1
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit61: TBrvDbEdit
              Left = 170
              Top = 93
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CdHisCan'
              DataSource = DsT013
              TabOrder = 2
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit62: TBrvDbEdit
              Left = 262
              Top = 120
              Width = 464
              Height = 21
              Color = clBtnFace
              DataField = 'NmUsuar1'
              DataSource = DsT013
              TabOrder = 3
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit63: TBrvDbEdit
              Left = 170
              Top = 120
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CdUsuari'
              DataSource = DsT013
              TabOrder = 4
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit64: TBrvDbEdit
              Left = 170
              Top = 147
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'CdUsuCan'
              DataSource = DsT013
              TabOrder = 5
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit39: TBrvDbEdit
              Left = 170
              Top = 12
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'TpFreCal'
              DataSource = DsT013
              TabOrder = 6
              BrAlignment = taRightJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit58: TBrvDbEdit
              Left = 262
              Top = 147
              Width = 464
              Height = 21
              Color = clBtnFace
              DataField = 'NmUsuar2'
              DataSource = DsT013
              TabOrder = 7
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object CBXSNIMBICM: TDBCheckBox
              Left = 3
              Top = 177
              Width = 105
              Height = 17
              Alignment = taLeftJustify
              Caption = 'ICMS Embutido?'
              DataField = 'SNIMBICM'
              DataSource = DsT013
              TabOrder = 8
              ValueChecked = 'Sim'
              ValueUnchecked = 'N'#195#163'o'
            end
            object CBXSNSUBSTI: TDBCheckBox
              Left = 314
              Top = 177
              Width = 102
              Height = 17
              Alignment = taLeftJustify
              Caption = 'Produto com S.T.'
              DataField = 'SNSUBSTI'
              DataSource = DsT013
              TabOrder = 9
              ValueChecked = 'Sim'
              ValueUnchecked = 'N'#195#163'o'
            end
            object DBXSNDEICFA: TDBCheckBox
              Left = 514
              Top = 177
              Width = 139
              Height = 17
              Alignment = taLeftJustify
              Caption = 'Desconta ICMS na fatura?'
              DataField = 'SNDEICFA'
              DataSource = DsT013
              TabOrder = 10
              ValueChecked = 'Sim'
              ValueUnchecked = 'N'#195#163'o'
            end
            object DBEdit65: TBrvDbEdit
              Left = 262
              Top = 93
              Width = 464
              Height = 21
              Color = clBtnFace
              TabOrder = 11
              BrAlignment = taLeftJustify
              BrVisibleButton = False
              BrFunctionButton = TpDbConsulta
              BrQueryConsulta = 0
            end
            object DBEdit68: TDBEdit
              Left = 170
              Top = 174
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'SnImbIcm'
              DataSource = DsT013
              ReadOnly = True
              TabOrder = 12
            end
            object DBEdit69: TDBEdit
              Left = 402
              Top = 174
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'SnSubsti'
              DataSource = DsT013
              ReadOnly = True
              TabOrder = 13
            end
            object DBEdit70: TDBEdit
              Left = 640
              Top = 174
              Width = 86
              Height = 21
              Color = clBtnFace
              DataField = 'SnDeIcFa'
              DataSource = DsT013
              ReadOnly = True
              TabOrder = 14
            end
          end
        end
      end
      object TbsNotas: TTabSheet
        Caption = 'Notas'
        ExplicitLeft = 0
        ExplicitTop = 0
        ExplicitWidth = 0
        ExplicitHeight = 0
        object DBGrid1: TBrvDbGrid
          Left = 0
          Top = 0
          Width = 759
          Height = 496
          BrShowMemo = True
          BrReadOnlyStyle = [fsItalic]
          BrReadOnlyColor = clMaroon
          Align = alClient
          DataSource = DsT015
          Options = [dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgAlwaysShowSelection, dgConfirmDelete, dgCancelOnExit]
          TabOrder = 0
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;')
          BrGradeZebrada = True
          Columns = <
            item
              Expanded = False
              FieldName = 'NrNota'
              Title.Caption = 'Nota'
              Width = 53
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'NrSerie'
              Title.Caption = 'S'#233'rie NF'
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DtEmissa'
              Title.Caption = 'Dt. Coleta'
              Width = 73
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'NRCgcFor'
              Title.Caption = 'CNPF/CPF Fornecedor'
              Width = 122
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'VrCtb'
              Title.Caption = 'Valor da Nota'
              Width = 87
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'NrPeso'
              Title.Caption = 'Peso KG'
              Width = 67
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'QtVolume'
              Title.Caption = 'Volumes'
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'QtMetCub'
              Title.Caption = 'Metros c'#250'bicos'
              Width = 84
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Alignment = taRightJustify
              Expanded = False
              FieldName = 'NrConRas'
              Title.Caption = 'Rastreamento'
              Width = 85
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end>
        end
      end
      object TbsMovCtrc: TTabSheet
        Caption = 'Movimento'
        ImageIndex = 2
        ExplicitLeft = 0
        ExplicitTop = 0
        ExplicitWidth = 0
        ExplicitHeight = 0
        object DBGrdRota: TBrvDbGrid
          Left = 0
          Top = 0
          Width = 759
          Height = 496
          BrShowMemo = True
          BrReadOnlyStyle = [fsItalic]
          BrReadOnlyColor = clMaroon
          Align = alClient
          DataSource = DsT014
          Options = [dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgAlwaysShowSelection, dgCancelOnExit]
          TabOrder = 0
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;')
          BrGradeZebrada = True
          Columns = <
            item
              Expanded = False
              FieldName = 'CDEMPATU'
              Title.Caption = 'Emp'
              Visible = False
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'CDEMPATU'
              Title.Caption = 'Emp'
              Width = 34
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DSEMPRES'
              Title.Caption = 'Descri'#231#227'o'
              Width = 388
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'CDCARGA'
              Title.Caption = 'Carga'
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DTMOVTO'
              Title.Caption = 'Data'
              Width = 122
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'STMOVIME'
              Title.Caption = 'Situa'#231#227'o'
              Width = 103
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end>
        end
      end
      object TabSheet2: TTabSheet
        Caption = 'Revis'#245'es de entrega'
        ImageIndex = 3
        ExplicitLeft = 0
        ExplicitTop = 0
        ExplicitWidth = 0
        ExplicitHeight = 0
        object BrvDbGrid1: TBrvDbGrid
          Left = 0
          Top = 0
          Width = 759
          Height = 496
          BrShowMemo = True
          BrReadOnlyStyle = [fsItalic]
          BrReadOnlyColor = clMaroon
          Align = alClient
          DataSource = DsT016
          Options = [dgTitles, dgIndicator, dgColumnResize, dgColLines, dgRowLines, dgTabs, dgAlwaysShowSelection, dgCancelOnExit]
          TabOrder = 0
          TitleFont.Charset = DEFAULT_CHARSET
          TitleFont.Color = clWindowText
          TitleFont.Height = -11
          TitleFont.Name = 'Tahoma'
          TitleFont.Style = []
          BrDrawColumn.Strings = (
            'N'#227'o remova essas duas linhas de formata'#231#227'o:'
            'CampoTabela;Operador;ValorComparativo;Cor;')
          BrGradeZebrada = True
          Columns = <
            item
              Expanded = False
              FieldName = 'DTAGENDA'
              Title.Caption = 'Dt agendada'
              Width = 76
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DTPRECAL'
              Title.Caption = 'Dt reprogramada'
              Width = 85
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DSMOTIVO'
              Title.Caption = 'Motivo da reprograma'#231#227'o'
              Width = 361
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'DTALTERA'
              Title.Caption = 'Dt altera'#231#227'o'
              Width = 71
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end
            item
              Expanded = False
              FieldName = 'NMCOMUSU'
              Title.Caption = 'Usu'#225'rio da altera'#231#227'o'
              Width = 117
              Visible = True
              BrConsulta = 0
              BrPermissao = []
              BrValueHalfChecked = False
              BrSaveOnClick = False
            end>
        end
      end
    end
  end
  inherited PopCfgFrm: TPopupMenu
    Left = 16
    Top = 296
  end
  inherited ImlPopFrm: TImageList
    Height = 16
    Width = 16
    Left = 88
    Top = 304
    Bitmap = {
      494C010103000500200010001000FFFFFFFFFF10FFFFFFFFFFFFFFFF424D3600
      0000000000003600000028000000400000001000000001002000000000000010
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000007F007F007F007F007F7F7F00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000007F7F7F000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000007F007F007F007F00FFFFFF00FFFFFF00BFBFBF007F7F7F000000
      000000000000000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000007F00
      7F007F007F00FFFFFF00FFFFFF000000000000000000BFBFBF00BFBFBF007F7F
      7F0000000000000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F007F007F007F00FFFF
      FF00FFFFFF0000000000000000007F007F007F007F0000000000BFBFBF00BFBF
      BF007F7F7F00000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF0000000000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F007F00
      00007F0000007F0000007F0000007F0000007F0000007F0000007F0000007F00
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F007F00FFFFFF000000
      0000000000007F007F007F007F007F007F007F007F007F007F0000000000BFBF
      BF00BFBFBF007F7F7F000000000000000000000000000000000000000000FFFF
      FF00FFFFFF0000000000000000000000000000000000FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F007F00
      0000FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF007F00
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000007F00
      7F007F007F007F007F00007F7F0000FFFF007F007F007F007F007F007F000000
      0000BFBFBF00BFBFBF007F7F7F0000000000000000000000000000000000FFFF
      FF00FFFFFF0000000000FFFFFF00000000000000000000000000FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F007F00
      00007F0000007F0000007F0000007F0000007F0000007F0000007F0000007F00
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000007F007F007F007F007F00
      7F007F007F007F007F007F007F00007F7F007F007F007F007F007F007F007F00
      7F0000000000BFBFBF000000000000000000000000000000000000000000FFFF
      FF00FFFFFF0000000000FFFFFF00FFFFFF000000000000000000FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000FFFFFF007F00
      7F007F007F007F007F007F007F007F007F0000FFFF0000FFFF007F007F007F00
      7F007F007F00000000000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF000000000000000000FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000000000000000000000000000FFFF
      FF007F007F007F007F007F007F007F007F007F007F00007F7F0000FFFF0000FF
      FF007F007F007F007F000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF0000000000FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF007F0000007F0000007F0000007F000000FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000FFFFFF007F007F007F007F007F007F00007F7F007F007F0000FFFF0000FF
      FF007F007F007F007F007F007F0000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000FFFFFF007F007F007F007F0000FFFF0000FFFF0000FFFF007F00
      7F007F007F007F007F000000000000000000000000000000000000000000FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00FFFFFF0000000000000000000000000000000000000000007F7F7F00FFFF
      FF007F0000007F0000007F0000007F0000007F000000FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000FFFFFF007F007F007F007F007F007F007F007F007F00
      7F00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000007F7F7F00FFFF
      FF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFFFF00FFFF
      FF00000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000FFFFFF007F007F007F007F00000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      00000000000000000000000000000000000000000000000000007F7F7F007F7F
      7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F7F007F7F
      7F007F7F7F000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      0000000000000000000000000000000000000000000000000000000000000000
      000000000000000000000000000000000000424D3E000000000000003E000000
      2800000040000000100000000100010000000000800000000000000000000000
      000000000000000000000000FFFFFF00FFFFFFFFFFFF0000FE3FFFFFFFFF0000
      F81FC003C0070000E00FC003C00700008007C003C00700000003C003C0070000
      0001C003C00700000000C003C00700000001C003C00700008001C003C0070000
      C001C003C0070000E000C003C0070000F000C003C0070000F803C003C0070000
      FC0FFFFFC0070000FE3FFFFFFFFF000000000000000000000000000000000000
      000000000000}
  end
  inherited LspS049: TBrvListParam
    Left = 152
    Top = 304
  end
  object DsT013: TDataSource
    DataSet = CPT013
    Left = 265
    Top = 2
  end
  object CPT013: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcEntMer'
    ReadOnly = True
    RemoteServer = DmSrvApl.PvcSDmTra
    BrShowFieldName = False
    BrQueryCode = 50
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 311
    Top = 2
  end
  object CPT014: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcEntMer'
    ReadOnly = True
    RemoteServer = DmSrvApl.PvcSDmTra
    BrShowFieldName = False
    BrQueryCode = 51
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 391
    Top = 2
  end
  object DsT014: TDataSource
    DataSet = CPT014
    Left = 353
    Top = 2
  end
  object DsT015: TDataSource
    DataSet = CPT015
    Left = 441
    Top = 2
  end
  object CPT015: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcEntMer'
    ReadOnly = True
    RemoteServer = DmSrvApl.PvcSDmTra
    BrShowFieldName = False
    BrQueryCode = 52
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 479
    Top = 2
  end
  object DsT016: TDataSource
    DataSet = CPT016
    Left = 521
    Top = 2
  end
  object CPT016: TBrvClientDataSet
    Aggregates = <>
    Params = <>
    ProviderName = 'DcEntMer'
    ReadOnly = True
    RemoteServer = DmSrvApl.PvcSDmTra
    BrShowFieldName = False
    BrQueryCode = 52
    BrDicionario = DmSrvApl.BrvDicionario
    BrType = VqNormal
    BrGravaLog = True
    BrUserCode = 0
    Left = 567
    Top = 2
  end
  object BrvServerProgress: TBrvServerProgress
    Left = 176
    Top = 1
  end
end
