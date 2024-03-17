
using System.Net.Http;
using System.Threading.Tasks;

namespace FrontEndApp2
{
    partial class Form1
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
            this.components = new System.ComponentModel.Container();
            this.Rooms = new System.Windows.Forms.ListBox();
            this.Chat = new System.Windows.Forms.ListBox();
            this.txtbxInput = new System.Windows.Forms.TextBox();
            this.txtbxUsername = new System.Windows.Forms.TextBox();
            this.txtbxPassword = new System.Windows.Forms.TextBox();
            this.btnLogin = new System.Windows.Forms.Button();
            this.btnLogout = new System.Windows.Forms.Button();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.tmrGetMessages = new System.Windows.Forms.Timer(this.components);
            this.txtbxCR = new System.Windows.Forms.TextBox();
            this.lblCR = new System.Windows.Forms.Label();
            this.btnCR = new System.Windows.Forms.Button();
            this.lblJR = new System.Windows.Forms.Label();
            this.txtbxJR = new System.Windows.Forms.TextBox();
            this.lstbxJR = new System.Windows.Forms.ListBox();
            this.btnJR = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // Rooms
            // 
            this.Rooms.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(55)))), ((int)(((byte)(47)))), ((int)(((byte)(70)))));
            this.Rooms.Enabled = false;
            this.Rooms.ForeColor = System.Drawing.Color.White;
            this.Rooms.FormattingEnabled = true;
            this.Rooms.ItemHeight = 16;
            this.Rooms.Location = new System.Drawing.Point(32, 189);
            this.Rooms.Name = "Rooms";
            this.Rooms.Size = new System.Drawing.Size(246, 420);
            this.Rooms.TabIndex = 1;
            this.Rooms.SelectedIndexChanged += new System.EventHandler(this.Rooms_SelectedIndexChanged);
            // 
            // Chat
            // 
            this.Chat.Enabled = false;
            this.Chat.FormattingEnabled = true;
            this.Chat.ItemHeight = 16;
            this.Chat.Location = new System.Drawing.Point(285, 189);
            this.Chat.Name = "Chat";
            this.Chat.Size = new System.Drawing.Size(546, 388);
            this.Chat.TabIndex = 2;
            // 
            // txtbxInput
            // 
            this.txtbxInput.Enabled = false;
            this.txtbxInput.Location = new System.Drawing.Point(285, 584);
            this.txtbxInput.Name = "txtbxInput";
            this.txtbxInput.Size = new System.Drawing.Size(546, 22);
            this.txtbxInput.TabIndex = 3;
            this.txtbxInput.KeyDown += new System.Windows.Forms.KeyEventHandler(this.txtbxInput_KeyDown);
            // 
            // txtbxUsername
            // 
            this.txtbxUsername.Location = new System.Drawing.Point(578, 97);
            this.txtbxUsername.Name = "txtbxUsername";
            this.txtbxUsername.Size = new System.Drawing.Size(100, 22);
            this.txtbxUsername.TabIndex = 4;
            // 
            // txtbxPassword
            // 
            this.txtbxPassword.Location = new System.Drawing.Point(685, 96);
            this.txtbxPassword.Name = "txtbxPassword";
            this.txtbxPassword.Size = new System.Drawing.Size(100, 22);
            this.txtbxPassword.TabIndex = 5;
            // 
            // btnLogin
            // 
            this.btnLogin.Location = new System.Drawing.Point(591, 62);
            this.btnLogin.Name = "btnLogin";
            this.btnLogin.Size = new System.Drawing.Size(75, 29);
            this.btnLogin.TabIndex = 6;
            this.btnLogin.Text = "Log in";
            this.btnLogin.UseVisualStyleBackColor = true;
            this.btnLogin.Click += new System.EventHandler(this.btnLogin_ClickAsync);
            // 
            // btnLogout
            // 
            this.btnLogout.Enabled = false;
            this.btnLogout.Location = new System.Drawing.Point(699, 62);
            this.btnLogout.Name = "btnLogout";
            this.btnLogout.Size = new System.Drawing.Size(75, 28);
            this.btnLogout.TabIndex = 7;
            this.btnLogout.Text = "Log out";
            this.btnLogout.UseVisualStyleBackColor = true;
            this.btnLogout.Click += new System.EventHandler(this.btnLogout_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::FrontEndApp2.Properties.Resources.messboard;
            this.pictureBox1.Location = new System.Drawing.Point(-6, 3);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(578, 180);
            this.pictureBox1.TabIndex = 8;
            this.pictureBox1.TabStop = false;
            // 
            // tmrGetMessages
            // 
            this.tmrGetMessages.Interval = 5000;
            this.tmrGetMessages.Tick += new System.EventHandler(this.tmrGetMessages_Tick);
            // 
            // txtbxCR
            // 
            this.txtbxCR.Enabled = false;
            this.txtbxCR.Location = new System.Drawing.Point(857, 209);
            this.txtbxCR.Name = "txtbxCR";
            this.txtbxCR.Size = new System.Drawing.Size(174, 22);
            this.txtbxCR.TabIndex = 9;
            this.txtbxCR.KeyDown += new System.Windows.Forms.KeyEventHandler(this.txtbxCR_KeyDown);
            // 
            // lblCR
            // 
            this.lblCR.AutoSize = true;
            this.lblCR.ForeColor = System.Drawing.Color.White;
            this.lblCR.Location = new System.Drawing.Point(898, 189);
            this.lblCR.Name = "lblCR";
            this.lblCR.Size = new System.Drawing.Size(91, 17);
            this.lblCR.TabIndex = 10;
            this.lblCR.Text = "Create Room";
            // 
            // btnCR
            // 
            this.btnCR.Enabled = false;
            this.btnCR.Location = new System.Drawing.Point(891, 237);
            this.btnCR.Name = "btnCR";
            this.btnCR.Size = new System.Drawing.Size(98, 23);
            this.btnCR.TabIndex = 11;
            this.btnCR.Text = "CREATE!";
            this.btnCR.UseVisualStyleBackColor = true;
            this.btnCR.Click += new System.EventHandler(this.btnCR_Click);
            // 
            // lblJR
            // 
            this.lblJR.AutoSize = true;
            this.lblJR.ForeColor = System.Drawing.Color.White;
            this.lblJR.Location = new System.Drawing.Point(905, 298);
            this.lblJR.Name = "lblJR";
            this.lblJR.Size = new System.Drawing.Size(75, 17);
            this.lblJR.TabIndex = 12;
            this.lblJR.Text = "Join Room";
            // 
            // txtbxJR
            // 
            this.txtbxJR.Enabled = false;
            this.txtbxJR.Location = new System.Drawing.Point(857, 318);
            this.txtbxJR.Name = "txtbxJR";
            this.txtbxJR.Size = new System.Drawing.Size(174, 22);
            this.txtbxJR.TabIndex = 13;
            this.txtbxJR.KeyDown += new System.Windows.Forms.KeyEventHandler(this.txtbxJR_KeyDown);
            // 
            // lstbxJR
            // 
            this.lstbxJR.Enabled = false;
            this.lstbxJR.FormattingEnabled = true;
            this.lstbxJR.ItemHeight = 16;
            this.lstbxJR.Location = new System.Drawing.Point(857, 347);
            this.lstbxJR.Name = "lstbxJR";
            this.lstbxJR.Size = new System.Drawing.Size(174, 180);
            this.lstbxJR.TabIndex = 14;
            // 
            // btnJR
            // 
            this.btnJR.Enabled = false;
            this.btnJR.Location = new System.Drawing.Point(902, 533);
            this.btnJR.Name = "btnJR";
            this.btnJR.Size = new System.Drawing.Size(75, 23);
            this.btnJR.TabIndex = 15;
            this.btnJR.Text = "JOIN!";
            this.btnJR.UseVisualStyleBackColor = true;
            this.btnJR.Click += new System.EventHandler(this.btnJR_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(55)))), ((int)(((byte)(47)))), ((int)(((byte)(70)))));
            this.ClientSize = new System.Drawing.Size(1088, 754);
            this.Controls.Add(this.btnJR);
            this.Controls.Add(this.lstbxJR);
            this.Controls.Add(this.txtbxJR);
            this.Controls.Add(this.lblJR);
            this.Controls.Add(this.btnCR);
            this.Controls.Add(this.lblCR);
            this.Controls.Add(this.txtbxCR);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.btnLogout);
            this.Controls.Add(this.btnLogin);
            this.Controls.Add(this.txtbxPassword);
            this.Controls.Add(this.txtbxUsername);
            this.Controls.Add(this.txtbxInput);
            this.Controls.Add(this.Chat);
            this.Controls.Add(this.Rooms);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.ListBox Rooms;
        private System.Windows.Forms.ListBox Chat;
        private System.Windows.Forms.TextBox txtbxInput;

        private async Task<object> callBackendAsync(string api, object input)
        {
            using (var httpClient = new HttpClient())
            {
                var response = await httpClient.GetAsync(api);
                if (response.IsSuccessStatusCode)
                {
                    var content = await response.Content.ReadAsStringAsync();
                    return content;
                }
            }
            return null;
        }

        private System.Windows.Forms.TextBox txtbxUsername;
        private System.Windows.Forms.TextBox txtbxPassword;
        private System.Windows.Forms.Button btnLogin;
        private System.Windows.Forms.Button btnLogout;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Timer tmrGetMessages;
        private System.Windows.Forms.TextBox txtbxCR;
        private System.Windows.Forms.Label lblCR;
        private System.Windows.Forms.Button btnCR;
        private System.Windows.Forms.Label lblJR;
        private System.Windows.Forms.TextBox txtbxJR;
        private System.Windows.Forms.ListBox lstbxJR;
        private System.Windows.Forms.Button btnJR;
    }
}

