namespace BrMobile
{
    partial class SIS000
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SIS000));
            this.panel2 = new System.Windows.Forms.Panel();
            this.imgConfNet = new System.Windows.Forms.PictureBox();
            this.lblUsuario = new System.Windows.Forms.Label();
            this.btnSair = new System.Windows.Forms.Button();
            this.BtnVersao = new System.Windows.Forms.Button();
            this.timerAtividade = new System.Windows.Forms.Timer();
            this.timerIni = new System.Windows.Forms.Timer();
            this.pnlConfSaida = new System.Windows.Forms.Panel();
            this.lblQtdeConf = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.btnConfSaida = new System.Windows.Forms.Button();
            this.imgConfSaida = new System.Windows.Forms.PictureBox();
            this.LblTituloConf = new System.Windows.Forms.Label();
            this.pnlConfSaidaTransf = new System.Windows.Forms.Panel();
            this.imgConfSaidaTransf = new System.Windows.Forms.PictureBox();
            this.lblQtdeTransf = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.btnConfSaidaTransf = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.pnlAtividades = new System.Windows.Forms.Panel();
            this.LblStatus = new System.Windows.Forms.Button();
            this.ImgBuscaAtividade = new System.Windows.Forms.PictureBox();
            this.label1 = new System.Windows.Forms.Label();
            this.timerAtvNaoConv = new System.Windows.Forms.Timer();
            this.pnlConfEntTransf = new System.Windows.Forms.Panel();
            this.lblQtdeTransfE = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.btnEntTransf = new System.Windows.Forms.Button();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.label4 = new System.Windows.Forms.Label();
            this.panel2.SuspendLayout();
            this.pnlConfSaida.SuspendLayout();
            this.pnlConfSaidaTransf.SuspendLayout();
            this.pnlAtividades.SuspendLayout();
            this.pnlConfEntTransf.SuspendLayout();
            this.SuspendLayout();
            // 
            // panel2
            // 
            this.panel2.BackColor = System.Drawing.Color.Navy;
            this.panel2.Controls.Add(this.imgConfNet);
            this.panel2.Controls.Add(this.lblUsuario);
            this.panel2.Controls.Add(this.btnSair);
            this.panel2.Controls.Add(this.BtnVersao);
            this.panel2.Location = new System.Drawing.Point(0, 0);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(240, 27);
            // 
            // imgConfNet
            // 
            this.imgConfNet.Image = ((System.Drawing.Image)(resources.GetObject("imgConfNet.Image")));
            this.imgConfNet.Location = new System.Drawing.Point(177, 4);
            this.imgConfNet.Name = "imgConfNet";
            this.imgConfNet.Size = new System.Drawing.Size(22, 20);
            this.imgConfNet.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.imgConfNet.Click += new System.EventHandler(this.imgConfNet_Click);
            // 
            // lblUsuario
            // 
            this.lblUsuario.Font = new System.Drawing.Font("Tahoma", 9F, System.Drawing.FontStyle.Bold);
            this.lblUsuario.ForeColor = System.Drawing.Color.White;
            this.lblUsuario.Location = new System.Drawing.Point(60, 6);
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
            this.btnSair.TabIndex = 10;
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
            // timerAtividade
            // 
            this.timerAtividade.Tick += new System.EventHandler(this.timerAtividade_Tick);
            // 
            // timerIni
            // 
            this.timerIni.Tick += new System.EventHandler(this.timerIni_Tick);
            // 
            // pnlConfSaida
            // 
            this.pnlConfSaida.Controls.Add(this.lblQtdeConf);
            this.pnlConfSaida.Controls.Add(this.label2);
            this.pnlConfSaida.Controls.Add(this.btnConfSaida);
            this.pnlConfSaida.Controls.Add(this.imgConfSaida);
            this.pnlConfSaida.Controls.Add(this.LblTituloConf);
            this.pnlConfSaida.Location = new System.Drawing.Point(0, 95);
            this.pnlConfSaida.Name = "pnlConfSaida";
            this.pnlConfSaida.Size = new System.Drawing.Size(240, 62);
            this.pnlConfSaida.Visible = false;
            // 
            // lblQtdeConf
            // 
            this.lblQtdeConf.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold);
            this.lblQtdeConf.Location = new System.Drawing.Point(187, 45);
            this.lblQtdeConf.Name = "lblQtdeConf";
            this.lblQtdeConf.Size = new System.Drawing.Size(34, 14);
            this.lblQtdeConf.Text = "0";
            this.lblQtdeConf.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // label2
            // 
            this.label2.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold);
            this.label2.Location = new System.Drawing.Point(147, 25);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(83, 14);
            this.label2.Text = "Pendentes";
            // 
            // btnConfSaida
            // 
            this.btnConfSaida.BackColor = System.Drawing.Color.White;
            this.btnConfSaida.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.btnConfSaida.Location = new System.Drawing.Point(57, 26);
            this.btnConfSaida.Name = "btnConfSaida";
            this.btnConfSaida.Size = new System.Drawing.Size(84, 34);
            this.btnConfSaida.TabIndex = 18;
            this.btnConfSaida.Text = "Buscar";
            this.btnConfSaida.Click += new System.EventHandler(this.btnConfSaida_Click);
            // 
            // imgConfSaida
            // 
            this.imgConfSaida.Image = ((System.Drawing.Image)(resources.GetObject("imgConfSaida.Image")));
            this.imgConfSaida.Location = new System.Drawing.Point(6, 26);
            this.imgConfSaida.Name = "imgConfSaida";
            this.imgConfSaida.Size = new System.Drawing.Size(48, 34);
            this.imgConfSaida.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.imgConfSaida.Click += new System.EventHandler(this.btnConfSaida_Click);
            // 
            // LblTituloConf
            // 
            this.LblTituloConf.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.LblTituloConf.Location = new System.Drawing.Point(1, 1);
            this.LblTituloConf.Name = "LblTituloConf";
            this.LblTituloConf.Size = new System.Drawing.Size(238, 26);
            this.LblTituloConf.Text = "Conferência Saída";
            this.LblTituloConf.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // pnlConfSaidaTransf
            // 
            this.pnlConfSaidaTransf.Controls.Add(this.imgConfSaidaTransf);
            this.pnlConfSaidaTransf.Controls.Add(this.lblQtdeTransf);
            this.pnlConfSaidaTransf.Controls.Add(this.label5);
            this.pnlConfSaidaTransf.Controls.Add(this.btnConfSaidaTransf);
            this.pnlConfSaidaTransf.Controls.Add(this.label3);
            this.pnlConfSaidaTransf.Location = new System.Drawing.Point(0, 163);
            this.pnlConfSaidaTransf.Name = "pnlConfSaidaTransf";
            this.pnlConfSaidaTransf.Size = new System.Drawing.Size(240, 62);
            this.pnlConfSaidaTransf.Visible = false;
            // 
            // imgConfSaidaTransf
            // 
            this.imgConfSaidaTransf.Image = ((System.Drawing.Image)(resources.GetObject("imgConfSaidaTransf.Image")));
            this.imgConfSaidaTransf.Location = new System.Drawing.Point(6, 26);
            this.imgConfSaidaTransf.Name = "imgConfSaidaTransf";
            this.imgConfSaidaTransf.Size = new System.Drawing.Size(48, 31);
            this.imgConfSaidaTransf.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.imgConfSaidaTransf.Click += new System.EventHandler(this.btnConfSaidaTransf_Click);
            // 
            // lblQtdeTransf
            // 
            this.lblQtdeTransf.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold);
            this.lblQtdeTransf.Location = new System.Drawing.Point(187, 45);
            this.lblQtdeTransf.Name = "lblQtdeTransf";
            this.lblQtdeTransf.Size = new System.Drawing.Size(34, 14);
            this.lblQtdeTransf.Text = "0";
            this.lblQtdeTransf.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // label5
            // 
            this.label5.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold);
            this.label5.Location = new System.Drawing.Point(147, 25);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(83, 14);
            this.label5.Text = "Pendentes";
            // 
            // btnConfSaidaTransf
            // 
            this.btnConfSaidaTransf.BackColor = System.Drawing.Color.White;
            this.btnConfSaidaTransf.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.btnConfSaidaTransf.Location = new System.Drawing.Point(57, 26);
            this.btnConfSaidaTransf.Name = "btnConfSaidaTransf";
            this.btnConfSaidaTransf.Size = new System.Drawing.Size(84, 31);
            this.btnConfSaidaTransf.TabIndex = 23;
            this.btnConfSaidaTransf.Text = "Buscar";
            this.btnConfSaidaTransf.Click += new System.EventHandler(this.btnConfSaidaTransf_Click);
            // 
            // label3
            // 
            this.label3.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.label3.Location = new System.Drawing.Point(1, 1);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(238, 26);
            this.label3.Text = "Transf. Saída";
            this.label3.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // pnlAtividades
            // 
            this.pnlAtividades.Controls.Add(this.LblStatus);
            this.pnlAtividades.Controls.Add(this.ImgBuscaAtividade);
            this.pnlAtividades.Controls.Add(this.label1);
            this.pnlAtividades.Location = new System.Drawing.Point(0, 28);
            this.pnlAtividades.Name = "pnlAtividades";
            this.pnlAtividades.Size = new System.Drawing.Size(240, 62);
            this.pnlAtividades.Visible = false;
            // 
            // LblStatus
            // 
            this.LblStatus.BackColor = System.Drawing.Color.White;
            this.LblStatus.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.LblStatus.Location = new System.Drawing.Point(57, 26);
            this.LblStatus.Name = "LblStatus";
            this.LblStatus.Size = new System.Drawing.Size(173, 34);
            this.LblStatus.TabIndex = 15;
            this.LblStatus.Text = "Buscar";
            this.LblStatus.Click += new System.EventHandler(this.ImgBuscaAtividade_Click);
            // 
            // ImgBuscaAtividade
            // 
            this.ImgBuscaAtividade.Image = ((System.Drawing.Image)(resources.GetObject("ImgBuscaAtividade.Image")));
            this.ImgBuscaAtividade.Location = new System.Drawing.Point(6, 26);
            this.ImgBuscaAtividade.Name = "ImgBuscaAtividade";
            this.ImgBuscaAtividade.Size = new System.Drawing.Size(48, 34);
            this.ImgBuscaAtividade.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.ImgBuscaAtividade.Click += new System.EventHandler(this.ImgBuscaAtividade_Click);
            // 
            // label1
            // 
            this.label1.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.label1.Location = new System.Drawing.Point(1, 1);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(238, 26);
            this.label1.Text = "Outras Atividades";
            this.label1.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // timerAtvNaoConv
            // 
            this.timerAtvNaoConv.Interval = 10000;
            this.timerAtvNaoConv.Tick += new System.EventHandler(this.timerAtvNaoConv_Tick);
            // 
            // pnlConfEntTransf
            // 
            this.pnlConfEntTransf.Controls.Add(this.lblQtdeTransfE);
            this.pnlConfEntTransf.Controls.Add(this.label7);
            this.pnlConfEntTransf.Controls.Add(this.btnEntTransf);
            this.pnlConfEntTransf.Controls.Add(this.pictureBox1);
            this.pnlConfEntTransf.Controls.Add(this.label4);
            this.pnlConfEntTransf.Location = new System.Drawing.Point(0, 232);
            this.pnlConfEntTransf.Name = "pnlConfEntTransf";
            this.pnlConfEntTransf.Size = new System.Drawing.Size(240, 62);
            this.pnlConfEntTransf.Visible = false;
            // 
            // lblQtdeTransfE
            // 
            this.lblQtdeTransfE.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold);
            this.lblQtdeTransfE.Location = new System.Drawing.Point(187, 45);
            this.lblQtdeTransfE.Name = "lblQtdeTransfE";
            this.lblQtdeTransfE.Size = new System.Drawing.Size(34, 14);
            this.lblQtdeTransfE.Text = "0";
            this.lblQtdeTransfE.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // label7
            // 
            this.label7.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold);
            this.label7.Location = new System.Drawing.Point(147, 25);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(83, 14);
            this.label7.Text = "Pendentes";
            // 
            // btnEntTransf
            // 
            this.btnEntTransf.BackColor = System.Drawing.Color.White;
            this.btnEntTransf.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.btnEntTransf.Location = new System.Drawing.Point(57, 26);
            this.btnEntTransf.Name = "btnEntTransf";
            this.btnEntTransf.Size = new System.Drawing.Size(84, 31);
            this.btnEntTransf.TabIndex = 16;
            this.btnEntTransf.Text = "Buscar";
            this.btnEntTransf.Click += new System.EventHandler(this.btnEndIntermed_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(6, 26);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(48, 31);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            // 
            // label4
            // 
            this.label4.Font = new System.Drawing.Font("Tahoma", 16F, System.Drawing.FontStyle.Bold);
            this.label4.Location = new System.Drawing.Point(1, 1);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(238, 26);
            this.label4.Text = "Transf. Entrada";
            this.label4.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // SIS000
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 320);
            this.Controls.Add(this.pnlConfEntTransf);
            this.Controls.Add(this.pnlAtividades);
            this.Controls.Add(this.pnlConfSaidaTransf);
            this.Controls.Add(this.pnlConfSaida);
            this.Controls.Add(this.panel2);
            this.KeyPreview = true;
            this.Location = new System.Drawing.Point(0, 0);
            this.Name = "SIS000";
            this.Text = ",";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.panel2.ResumeLayout(false);
            this.pnlConfSaida.ResumeLayout(false);
            this.pnlConfSaidaTransf.ResumeLayout(false);
            this.pnlAtividades.ResumeLayout(false);
            this.pnlConfEntTransf.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Button btnSair;
        private System.Windows.Forms.Button BtnVersao;
        private System.Windows.Forms.Timer timerAtividade;
        private System.Windows.Forms.Timer timerIni;
        private System.Windows.Forms.Label lblUsuario;
        private System.Windows.Forms.PictureBox imgConfNet;
        private System.Windows.Forms.Panel pnlConfSaida;
        private System.Windows.Forms.Label LblTituloConf;
        private System.Windows.Forms.Label lblQtdeConf;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button btnConfSaida;
        private System.Windows.Forms.PictureBox imgConfSaida;
        private System.Windows.Forms.Panel pnlConfSaidaTransf;
        private System.Windows.Forms.Label lblQtdeTransf;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button btnConfSaidaTransf;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.PictureBox imgConfSaidaTransf;
        private System.Windows.Forms.Panel pnlAtividades;
        private System.Windows.Forms.Button LblStatus;
        private System.Windows.Forms.PictureBox ImgBuscaAtividade;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Timer timerAtvNaoConv;
        private System.Windows.Forms.Panel pnlConfEntTransf;
        private System.Windows.Forms.Button btnEntTransf;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label lblQtdeTransfE;
        private System.Windows.Forms.Label label7;

    }
}

