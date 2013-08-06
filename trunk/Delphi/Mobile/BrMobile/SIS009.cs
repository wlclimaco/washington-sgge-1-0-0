using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace LogosMobile
{
    public partial class SIS009 : Form
    {
        public SIS009()
        {
            InitializeComponent();
        }

        private void ImgTeclado_Click(object sender, EventArgs e)        
        {
            IptPnlSenha.Enabled = !IptPnlSenha.Enabled;
        }        

        private void btnSair_Click(object sender, EventArgs e)
        {
            this.Close();
        }        

        private void btnTestar_Click(object sender, EventArgs e)
        {
            bool sncontinua;

            if (edtMsg.Text.Trim() == string.Empty)
            {
                if (Controller.MessageDlg("Nenhuma mensagem informada... Deseja continuar?"))
                {
                    sncontinua = true;
                }
                else
                {
                    sncontinua = false;
                }
            }
            else
            {
                sncontinua = true;
            }

            if (sncontinua)
            {
                if (Controller.WsLogos.WsTestaConexao(edtID.Text, edtMsg.Text))
                {
                    Controller.ShowMessage("Ok! Conectado!");
                }
            }
        }
    }
}