using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace LogosMobile
{
    public partial class SIS003 : Form
    {
        public SIS003(string nmusuario)
        {
            InitializeComponent();
            lblUsuario.Text = nmusuario;
        }

        private void btnSair_Click(object sender, EventArgs e)
        {
            this.Close();
        }        
    }
}