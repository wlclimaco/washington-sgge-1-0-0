namespace LogosMobile
{
    partial class SIS006
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.lblTitulo = new System.Windows.Forms.Label();
            this.panel2 = new System.Windows.Forms.Panel();
            this.BtnEtiCli = new System.Windows.Forms.Button();
            this.lblUsuario = new System.Windows.Forms.Label();
            this.btnSair = new System.Windows.Forms.Button();
            this.lblEtiqueta = new System.Windows.Forms.Label();
            this.edtEtiqueta = new System.Windows.Forms.TextBox();
            this.lblMapa = new System.Windows.Forms.Label();
            this.pnlAguarde = new System.Windows.Forms.Panel();
            this.lblStatus = new System.Windows.Forms.Label();
            this.lbl2 = new System.Windows.Forms.Label();
            this.LblFornec = new System.Windows.Forms.Label();
            this.BtnFinalizar = new System.Windows.Forms.Button();
            this.panel2.SuspendLayout();
            this.pnlAguarde.SuspendLayout();
            this.SuspendLayout();
            // 
            // lblTitulo
            // 
            this.lblTitulo.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.Location = new System.Drawing.Point(0, 42);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(240, 18);
            this.lblTitulo.Text = "Conferencia de Saída";
            this.lblTitulo.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // panel2
            // 
            this.panel2.BackColor = System.Drawing.Color.Navy;
            this.panel2.Controls.Add(this.BtnEtiCli);
            this.panel2.Controls.Add(this.lblUsuario);
            this.panel2.Controls.Add(this.btnSair);
            this.panel2.Location = new System.Drawing.Point(0, 0);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(240, 27);
            // 
            // BtnEtiCli
            // 
            this.BtnEtiCli.Location = new System.Drawing.Point(10, 4);
            this.BtnEtiCli.Name = "BtnEtiCli";
            this.BtnEtiCli.Size = new System.Drawing.Size(20, 20);
            this.BtnEtiCli.TabIndex = 12;
            this.BtnEtiCli.Text = "...";
            this.BtnEtiCli.Visible = false;
            this.BtnEtiCli.Click += new System.EventHandler(this.button1_Click);
            // 
            // lblUsuario
            // 
            this.lblUsuario.Font = new System.Drawing.Font("Tahoma", 9F, System.Drawing.FontStyle.Bold);
            this.lblUsuario.ForeColor = System.Drawing.Color.White;
            this.lblUsuario.Location = new System.Drawing.Point(51, 6);
            this.lblUsuario.Name = "lblUsuario";
            this.lblUsuario.Size = new System.Drawing.Size(139, 17);
            this.lblUsuario.Text = "User";
            // 
            // btnSair
            // 
            this.btnSair.BackColor = System.Drawing.Color.White;
            this.btnSair.Location = new System.Drawing.Point(205, 4);
            this.btnSair.Name = "btnSair";
            this.btnSair.Size = new System.Drawing.Size(32, 20);
            this.btnSair.TabIndex = 11;
            this.btnSair.Text = "Sair";
            this.btnSair.Click += new System.EventHandler(this.btnSair_Click);
            // 
            // lblEtiqueta
            // 
            this.lblEtiqueta.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lblEtiqueta.Location = new System.Drawing.Point(10, 147);
            this.lblEtiqueta.Name = "lblEtiqueta";
            this.lblEtiqueta.Size = new System.Drawing.Size(219, 20);
            this.lblEtiqueta.Text = "Confirme Etiqueta Cliente";
            // 
            // edtEtiqueta
            // 
            this.edtEtiqueta.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Regular);
            this.edtEtiqueta.Location = new System.Drawing.Point(1, 170);
            this.edtEtiqueta.Name = "edtEtiqueta";
            this.edtEtiqueta.Size = new System.Drawing.Size(237, 26);
            this.edtEtiqueta.TabIndex = 42;
            // 
            // lblMapa
            // 
            this.lblMapa.Font = new System.Drawing.Font("Tahoma", 14F, System.Drawing.FontStyle.Bold);
            this.lblMapa.Location = new System.Drawing.Point(0, 67);
            this.lblMapa.Name = "lblMapa";
            this.lblMapa.Size = new System.Drawing.Size(240, 26);
            this.lblMapa.Text = "Mapa: 100325";
            this.lblMapa.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // pnlAguarde
            // 
            this.pnlAguarde.Controls.Add(this.lblStatus);
            this.pnlAguarde.Location = new System.Drawing.Point(0, 147);
            this.pnlAguarde.Name = "pnlAguarde";
            this.pnlAguarde.Size = new System.Drawing.Size(240, 103);
            this.pnlAguarde.Visible = false;
            // 
            // lblStatus
            // 
            this.lblStatus.Font = new System.Drawing.Font("Tahoma", 18F, System.Drawing.FontStyle.Bold);
            this.lblStatus.Location = new System.Drawing.Point(3, 37);
            this.lblStatus.Name = "lblStatus";
            this.lblStatus.Size = new System.Drawing.Size(235, 38);
            this.lblStatus.Text = "Aguarde...";
            this.lblStatus.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // lbl2
            // 
            this.lbl2.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.lbl2.Location = new System.Drawing.Point(0, 96);
            this.lbl2.Name = "lbl2";
            this.lbl2.Size = new System.Drawing.Size(240, 18);
            this.lbl2.Text = "Fornecimento";
            this.lbl2.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // LblFornec
            // 
            this.LblFornec.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.LblFornec.Location = new System.Drawing.Point(0, 114);
            this.LblFornec.Name = "LblFornec";
            this.LblFornec.Size = new System.Drawing.Size(240, 33);
            this.LblFornec.Text = "0800342616";
            this.LblFornec.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // BtnFinalizar
            // 
            this.BtnFinalizar.BackColor = System.Drawing.Color.White;
            this.BtnFinalizar.Location = new System.Drawing.Point(0, 213);
            this.BtnFinalizar.Name = "BtnFinalizar";
            this.BtnFinalizar.Size = new System.Drawing.Size(97, 20);
            this.BtnFinalizar.TabIndex = 43;
            this.BtnFinalizar.Text = "Finalizar";
            this.BtnFinalizar.Visible = false;
            this.BtnFinalizar.Click += new System.EventHandler(this.BtnFinalizar_Click);
            // 
            // SIS006
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 320);
            this.Controls.Add(this.pnlAguarde);
            this.Controls.Add(this.edtEtiqueta);
            this.Controls.Add(this.BtnFinalizar);
            this.Controls.Add(this.LblFornec);
            this.Controls.Add(this.lbl2);
            this.Controls.Add(this.lblMapa);
            this.Controls.Add(this.lblTitulo);
            this.Controls.Add(this.panel2);
            this.Controls.Add(this.lblEtiqueta);
            this.KeyPreview = true;
            this.Location = new System.Drawing.Point(0, 0);
            this.Name = "SIS006";
            this.Text = "SIS006";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.SIS006_KeyDown);
            this.panel2.ResumeLayout(false);
            this.pnlAguarde.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Button btnSair;
        private System.Windows.Forms.Label lblEtiqueta;
        private System.Windows.Forms.TextBox edtEtiqueta;
        private System.Windows.Forms.Label lblMapa;
        private System.Windows.Forms.Panel pnlAguarde;
        private System.Windows.Forms.Label lblStatus;
        private System.Windows.Forms.Label lbl2;
        private System.Windows.Forms.Label LblFornec;
        private System.Windows.Forms.Button BtnFinalizar;
        private System.Windows.Forms.Label lblUsuario;
        private System.Windows.Forms.Button BtnEtiCli;
    }
}