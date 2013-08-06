using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using LogosMobile;

namespace BrMobile
{
    public partial class SIS000 : Form
    {
        public SIS000()
        {
            InitializeComponent();        
            timerIni.Enabled = true;
        }

        private void btnSair_Click(object sender, EventArgs e)
        {
            bool tmr1 = timerAtividade.Enabled;
            bool tmr2 = timerAtvNaoConv.Enabled;

            timerAtividade.Enabled  = false;
            timerAtvNaoConv.Enabled = false;

            if (Controller.MessageDlg("Deseja realmente sair?"))
            {
                this.Close();
            }
            else
            {
                timerAtividade.Enabled  = tmr1;
                timerAtvNaoConv.Enabled = tmr2;
            }
        }

        private void ProcessarAtividade(string nrmapa, string dsativid)
        {
            bool tmr1 = timerAtividade.Enabled;
            bool tmr2 = timerAtvNaoConv.Enabled;

            switch (dsativid)
            {
                case "CFEN":
                    Controller.ProcessaEntrada(nrmapa, lblUsuario.Text, false, "Conferencia de Entrada", true);
                    break;
                case "CFET":
                    Controller.ProcessaEntrada(nrmapa, lblUsuario.Text, false, "Entrada por Transf.", true);
                    break;
                case "CFSD":
                    Controller.ProcessaSaida(nrmapa, false, lblUsuario.Text, "Conferencia de Saida", true, string.Empty);
                    break;
                case "CFST":
                    Controller.ProcessaSaida(nrmapa, true, lblUsuario.Text, "Saida por Transf.", true, string.Empty);
                    break;
            }

            timerAtividade.Enabled = tmr1;
            timerAtvNaoConv.Enabled = tmr2;
        }

        private void BuscaAtividade()
        {
            pnlAtividades.Visible = true;

            LblStatus.Text = "Verificando...";
            LblStatus.Refresh();

            string NrMapa = "";
            string DsAtivid = "";

            if (Controller.WsLogos.WsPing())
            {
                if (!Controller.WsLogos.WsProximaAtividade(ref NrMapa, ref DsAtivid))
                {
                    LblStatus.Text = "Busca Manual";
                    LblStatus.Refresh();
                    DsAtivid = string.Empty;
                }

                if (DsAtivid != string.Empty)
                {
                    ProcessarAtividade(NrMapa, DsAtivid);

                    LblStatus.Text = "";
                    LblStatus.Refresh();

                    if (Controller.WsLogos.WsPing())
                    {
                        if (Controller.MessageDlg("Deseja buscar uma próxima atividade agora?"))
                        {
                            timerAtividade.Enabled = true;
                        }
                        else
                        {
                            LblStatus.Text = "Busca Manual";
                            LblStatus.Refresh();
                        }
                    }
                    else
                    {
                        LblStatus.Text = "Busca Manual";
                        LblStatus.Refresh();
                    }
                }
                else
                {
                    LblStatus.Text = "Busca Manual";
                    LblStatus.Refresh();
                }
            }
            else
            {
                LblStatus.Text = "Busca Manual";
                LblStatus.Refresh();
            }
        }

        private void timerAtividade_Tick(object sender, EventArgs e)
        {
            timerAtividade.Enabled = false;
            BuscaAtividade();
        }

        private void ImgBuscaAtividade_Click(object sender, EventArgs e)
        {
            timerAtvNaoConv.Enabled = false;
            ProcessaAtividadesNaoConvocadas();

            if (LblStatus.Text == "Busca Manual")
            {
                BuscaAtividade();
            }
            timerAtvNaoConv.Enabled = true;
        }

        private void BtnVersao_Click(object sender, EventArgs e)
        {
            SIS003 FrmAbout = new SIS003(lblUsuario.Text);
            FrmAbout.ShowDialog();
            FrmAbout.Dispose();
        }

        private void ProcessaAtividadesNaoConvocadas()
        {
            string dslista = string.Empty;
            Controller.WsLogos.WsAtividadeNaoConvocada(ref dslista);

            pnlConfSaida.Visible = false;
            pnlConfSaidaTransf.Visible = false;
            pnlConfEntTransf.Visible = false;

            if (dslista.Trim() != string.Empty)
            {
                const string dscabec = "SGTIPATI;NRATIV";
                char[] DsSepLin = { '#' };
                char[] DsSepVal = { ';' };
                string[] lines;
                string[] values;

                dslista = dslista.Replace("\r\n", "#");
                dslista = dslista.Replace("##", "#");

                lines = dslista.Split(DsSepLin);

                if (dscabec.ToUpper() != lines[0].ToUpper())
                {
                    throw new Exception(String.Format("Lista de Atividades não conforme: {0}", lines[0]));
                }

                for (int idx = 1; idx < lines.Length; idx++)
                {
                    if (lines[idx].Trim() != string.Empty)
                    {
                        values = lines[idx].Split(DsSepVal);
                        if (values[0] == "CFSD")
                        {
                            pnlConfSaida.Visible = true;
                            lblQtdeConf.Text = values[1];
                        }
                        else
                        {
                            if (values[0] == "CFST")
                            {
                                pnlConfSaidaTransf.Visible = true;
                                lblQtdeTransf.Text = values[1];
                            }
                            else
                            {
                                if (values[0] == "CFET")
                                {
                                    pnlConfEntTransf.Visible = true;
                                    lblQtdeTransfE.Text = values[1];
                                }
                            }
                        }
                    }
                }
            }
        }

        private void timerIni_Tick(object sender, EventArgs e)
        {
            timerIni.Enabled = false;

            LogosMobile.SIS001 FrmSIS001 = new LogosMobile.SIS001();
            FrmSIS001.ShowDialog();

            if (FrmSIS001.DialogResult == DialogResult.OK)
            {
                lblUsuario.Text = FrmSIS001.NmUsuario;
                this.Show();                
                this.Refresh();

                ProcessaAtividadesNaoConvocadas();

                string nrip = string.Empty;
                string lastuser = string.Empty;
                string snpreetiq = string.Empty;
                string nrtmpatv = string.Empty;

                Controller.GetConfig(ref nrip, ref lastuser, ref snpreetiq, ref nrtmpatv);

                if (nrtmpatv.Trim() == string.Empty)
                {
                    nrtmpatv = "10000";
                }

                timerAtvNaoConv.Interval = Convert.ToInt32(nrtmpatv);
                timerAtvNaoConv.Enabled  = true;

                timerAtividade.Enabled   = true;
            }
            else
            {
                this.Close();
            }
        }

        private void imgConfNet_Click(object sender, EventArgs e)
        {
            string nrip = string.Empty;
            string lastuser = string.Empty;
            string snpreetiq = string.Empty;
            string nrtmpatv = string.Empty;

            Controller.GetConfig(ref nrip, ref lastuser, ref snpreetiq, ref nrtmpatv);

            SIS004 FrmIP = new SIS004(nrip, lblUsuario.Text, snpreetiq, false, false, true, nrtmpatv);
            FrmIP.ShowDialog();

            if (FrmIP.DialogResult == DialogResult.OK)
            {
                Controller.SetConfig(FrmIP.Nrip, FrmIP.LastUser, FrmIP.SnPreEtiq, FrmIP.TmpAtv);

                timerAtvNaoConv.Interval = Convert.ToInt32(FrmIP.TmpAtv);
                timerAtvNaoConv.Enabled = true;
            }

            FrmIP.Dispose();
        }

        private void btnConfSaida_Click(object sender, EventArgs e)
        {
            timerAtvNaoConv.Enabled = false;
            SIS008 FrmRecebeEtiquetaCliente = new SIS008(lblUsuario.Text);
            FrmRecebeEtiquetaCliente.ShowDialog();

            if (FrmRecebeEtiquetaCliente.DialogResult == DialogResult.OK)
            { 
                string nropelog = string.Empty;

                if (Controller.WsLogos.ConsultaAtividade(FrmRecebeEtiquetaCliente.NrFornec, "CFSD", ref nropelog))
                {
                    Controller.ProcessaSaida(nropelog, false, lblUsuario.Text, "Conferencia de Saida", false, FrmRecebeEtiquetaCliente.NrClient);
                }
            }
            FrmRecebeEtiquetaCliente.Dispose();
            ProcessaAtividadesNaoConvocadas();
            timerAtvNaoConv.Enabled = true;

        }

        private void btnConfSaidaTransf_Click(object sender, EventArgs e)
        {
            timerAtvNaoConv.Enabled = false;
            SIS008 FrmRecebeEtiquetaCliente = new SIS008(lblUsuario.Text);
            FrmRecebeEtiquetaCliente.ShowDialog();

            if (FrmRecebeEtiquetaCliente.DialogResult == DialogResult.OK)
            {
                string nropelog = string.Empty;

                if (Controller.WsLogos.ConsultaAtividade(FrmRecebeEtiquetaCliente.NrFornec, "CFST", ref nropelog))
                {
                    Controller.ProcessaSaida(nropelog, true, lblUsuario.Text, "Saida por Transf.", false, FrmRecebeEtiquetaCliente.NrClient);
                }
            }
            FrmRecebeEtiquetaCliente.Dispose();
            ProcessaAtividadesNaoConvocadas();
            timerAtvNaoConv.Enabled = true;
        }

        private void timerAtvNaoConv_Tick(object sender, EventArgs e)
        {
            ProcessaAtividadesNaoConvocadas();
        }

        private void btnEndIntermed_Click(object sender, EventArgs e)
        {
            timerAtvNaoConv.Enabled = false;
            SIS008 FrmRecebeEtiquetaCliente = new SIS008(lblUsuario.Text);
            FrmRecebeEtiquetaCliente.ShowDialog();

            if (FrmRecebeEtiquetaCliente.DialogResult == DialogResult.OK)
            {
                string nropelog = string.Empty;

                if (Controller.WsLogos.ConsultaAtividade(FrmRecebeEtiquetaCliente.NrFornec, "CFET", ref nropelog))
                {
                    Controller.ProcessaEntrada(nropelog, lblUsuario.Text, false, "Entrada por Transf.", false);
                }
            }
            FrmRecebeEtiquetaCliente.Dispose();
            ProcessaAtividadesNaoConvocadas();
            timerAtvNaoConv.Enabled = true;
        }                      
    }
}