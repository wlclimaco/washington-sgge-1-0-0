namespace LogosMobile
{
    partial class SIS001
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SIS001));
            this.EdtDsSenha = new System.Windows.Forms.TextBox();
            this.ImgBackground = new System.Windows.Forms.PictureBox();
            this.BtnConfirma = new System.Windows.Forms.Button();
            this.LblCaption = new System.Windows.Forms.Label();
            this.LblDsSenha = new System.Windows.Forms.Label();
            this.LblCdUsuari = new System.Windows.Forms.Label();
            this.EdtCdUsuari = new System.Windows.Forms.TextBox();
            this.panel2 = new System.Windows.Forms.Panel();
            this.imgConfNet = new System.Windows.Forms.PictureBox();
            this.ImgTeclado = new System.Windows.Forms.PictureBox();
            this.btnSair = new System.Windows.Forms.Button();
            this.BtnVersao = new System.Windows.Forms.Button();
            this.IptPnlSenha = new Microsoft.WindowsCE.Forms.InputPanel();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.panel2.SuspendLayout();
            this.SuspendLayout();
            // 
            // EdtDsSenha
            // 
            this.EdtDsSenha.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.EdtDsSenha.Location = new System.Drawing.Point(13, 141);
            this.EdtDsSenha.MaxLength = 20;
            this.EdtDsSenha.Name = "EdtDsSenha";
            this.EdtDsSenha.PasswordChar = '*';
            this.EdtDsSenha.Size = new System.Drawing.Size(211, 26);
            this.EdtDsSenha.TabIndex = 18;
            this.EdtDsSenha.TabStop = false;
            this.EdtDsSenha.WordWrap = false;
            // 
            // ImgBackground
            // 
            this.ImgBackground.Location = new System.Drawing.Point(0, 0);
            this.ImgBackground.Name = "ImgBackground";
            this.ImgBackground.Size = new System.Drawing.Size(240, 320);
            this.ImgBackground.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            // 
            // BtnConfirma
            // 
            this.BtnConfirma.BackColor = System.Drawing.Color.White;
            this.BtnConfirma.Location = new System.Drawing.Point(152, 188);
            this.BtnConfirma.Name = "BtnConfirma";
            this.BtnConfirma.Size = new System.Drawing.Size(72, 20);
            this.BtnConfirma.TabIndex = 19;
            this.BtnConfirma.Text = "Confirma";
            this.BtnConfirma.Click += new System.EventHandler(this.BtnConfirma_Click);
            // 
            // LblCaption
            // 
            this.LblCaption.Font = new System.Drawing.Font("Tahoma", 14F, System.Drawing.FontStyle.Bold);
            this.LblCaption.Location = new System.Drawing.Point(13, 37);
            this.LblCaption.Name = "LblCaption";
            this.LblCaption.Size = new System.Drawing.Size(159, 20);
            this.LblCaption.Text = "BRAVO MOBILE";
            // 
            // LblDsSenha
            // 
            this.LblDsSenha.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.LblDsSenha.Location = new System.Drawing.Point(13, 118);
            this.LblDsSenha.Name = "LblDsSenha";
            this.LblDsSenha.Size = new System.Drawing.Size(57, 20);
            this.LblDsSenha.Text = "Senha";
            // 
            // LblCdUsuari
            // 
            this.LblCdUsuari.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.LblCdUsuari.Location = new System.Drawing.Point(13, 66);
            this.LblCdUsuari.Name = "LblCdUsuari";
            this.LblCdUsuari.Size = new System.Drawing.Size(57, 20);
            this.LblCdUsuari.Text = "Login";
            // 
            // EdtCdUsuari
            // 
            this.EdtCdUsuari.Font = new System.Drawing.Font("Tahoma", 12F, System.Drawing.FontStyle.Bold);
            this.EdtCdUsuari.Location = new System.Drawing.Point(13, 89);
            this.EdtCdUsuari.Name = "EdtCdUsuari";
            this.EdtCdUsuari.Size = new System.Drawing.Size(211, 26);
            this.EdtCdUsuari.TabIndex = 17;
            // 
            // panel2
            // 
            this.panel2.BackColor = System.Drawing.Color.Navy;
            this.panel2.Controls.Add(this.pictureBox1);
            this.panel2.Controls.Add(this.imgConfNet);
            this.panel2.Controls.Add(this.ImgTeclado);
            this.panel2.Controls.Add(this.btnSair);
            this.panel2.Controls.Add(this.BtnVersao);
            this.panel2.Location = new System.Drawing.Point(0, 0);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(240, 27);
            // 
            // imgConfNet
            // 
            this.imgConfNet.Image = ((System.Drawing.Image)(resources.GetObject("imgConfNet.Image")));
            this.imgConfNet.Location = new System.Drawing.Point(132, 4);
            this.imgConfNet.Name = "imgConfNet";
            this.imgConfNet.Size = new System.Drawing.Size(22, 20);
            this.imgConfNet.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.imgConfNet.Click += new System.EventHandler(this.imgConfNet_Click);
            // 
            // ImgTeclado
            // 
            this.ImgTeclado.Image = ((System.Drawing.Image)(resources.GetObject("ImgTeclado.Image")));
            this.ImgTeclado.Location = new System.Drawing.Point(160, 4);
            this.ImgTeclado.Name = "ImgTeclado";
            this.ImgTeclado.Size = new System.Drawing.Size(41, 20);
            this.ImgTeclado.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.ImgTeclado.Click += new System.EventHandler(this.ImgTeclado_Click);
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
            // BtnVersao
            // 
            this.BtnVersao.BackColor = System.Drawing.Color.White;
            this.BtnVersao.Location = new System.Drawing.Point(3, 4);
            this.BtnVersao.Name = "BtnVersao";
            this.BtnVersao.Size = new System.Drawing.Size(51, 20);
            this.BtnVersao.TabIndex = 9;
            this.BtnVersao.Text = "Versão";
            this.BtnVersao.Click += new System.EventHandler(this.BtnVersao_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackColor = System.Drawing.Color.Navy;
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(104, 4);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(22, 20);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.Click += new System.EventHandler(this.pictureBox1_Click);
            // 
            // SIS001
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoValidate = System.Windows.Forms.AutoValidate.Disable;
            this.ClientSize = new System.Drawing.Size(240, 320);
            this.ControlBox = false;
            this.Controls.Add(this.panel2);
            this.Controls.Add(this.BtnConfirma);
            this.Controls.Add(this.LblCaption);
            this.Controls.Add(this.LblDsSenha);
            this.Controls.Add(this.EdtDsSenha);
            this.Controls.Add(this.LblCdUsuari);
            this.Controls.Add(this.EdtCdUsuari);
            this.Controls.Add(this.ImgBackground);
            this.KeyPreview = true;
            this.Location = new System.Drawing.Point(0, 0);
            this.MinimizeBox = false;
            this.Name = "SIS001";
            this.Text = "Acesso ao Sistema";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.SIS000_KeyDown);
            this.panel2.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.PictureBox ImgBackground;
        private System.Windows.Forms.Button BtnConfirma;
        private System.Windows.Forms.Label LblCaption;
        private System.Windows.Forms.Label LblDsSenha;
        private System.Windows.Forms.Label LblCdUsuari;
        private System.Windows.Forms.TextBox EdtCdUsuari;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Button BtnVersao;
        private Microsoft.WindowsCE.Forms.InputPanel IptPnlSenha;
        private System.Windows.Forms.PictureBox ImgTeclado;
        private System.Windows.Forms.Button btnSair;
        private System.Windows.Forms.TextBox EdtDsSenha;
        private System.Windows.Forms.PictureBox imgConfNet;
        private System.Windows.Forms.PictureBox pictureBox1;
    }
}