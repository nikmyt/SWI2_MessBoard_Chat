using FrontEndApp2.Forms;
using FrontEndApp2.Model;
using Newtonsoft.Json;
using StompNet;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Net.Sockets;
using System.Net.WebSockets;
using System.Text;
using System.Text.Json;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using JsonSerializer = System.Text.Json.JsonSerializer;

namespace FrontEndApp2
{
    public partial class Form1 : Form
    {
        private readonly string baseUrl = "http://localhost:8080";
        private TokenResponse token;
        List<Room> RoomsList = new List<Room>();
        List<ChatMessage> ChatMessagesList = new List<ChatMessage>();
        List<Room> searchRoomsList = new List<Room>();
        public Form1()
        {
            InitializeComponent();
        }

        private void usernameEmpty()
        {
            txtbxUsername.Text = "input username";
        }

        private void passwordEmpty()
        {
            txtbxPassword.Text = "input password";
        }


        private async void btnLogin_ClickAsync(object sender, EventArgs e)
        {
            String username = txtbxUsername.Text;
            if (username == string.Empty)
            {
                usernameEmpty();
                return;
            }

            String password = txtbxPassword.Text;
            if (password == string.Empty)
            {
                passwordEmpty();
                return;
            }

            Console.WriteLine(username + " " + password);
            AuthForm credentials = new AuthForm(username, password);

            using (var httpClient = new HttpClient())
            {

                var endpoint = $"{baseUrl}/login";

                var jsonCredentials = JsonConvert.SerializeObject(credentials);
                var content = new StringContent(jsonCredentials, Encoding.UTF8, "application/json");
                var response = await httpClient.PostAsync(endpoint, content);

                // Check if the request was successful
                if (response.IsSuccessStatusCode)
                {
                    var jsonString = await response.Content.ReadAsStringAsync();
                    var token = System.Text.Json.JsonSerializer.Deserialize<TokenResponse>(jsonString);

                    this.token = token;

                    btnLogin.Enabled = false;
                    btnLogout.Enabled = true;
                    txtbxUsername.Enabled = false;
                    txtbxPassword.Enabled = false;
                    txtbxPassword.Text = "";
                    Chat.Enabled = true;
                    Rooms.Enabled = true;

                    txtbxInput.Enabled = true;
                    txtbxCR.Enabled = true;
                    btnCR.Enabled = true;

                    txtbxJR.Enabled = true;
                    btnJR.Enabled = true;

                    tmrGetMessages.Enabled = true;

                    lstbxJR.Enabled = true;

                    loadRooms();
                }
                else
                {
                    var error = await response.Content.ReadAsStringAsync();
                    throw new Exception(error);
                }
            }
        }

        private void btnLogout_Click(object sender, EventArgs e)
        {
            this.token = null;
            btnLogin.Enabled = true;
            btnLogout.Enabled = false;
            txtbxUsername.Enabled = true;
            txtbxPassword.Enabled = true;
            txtbxUsername.Text = "";
            Chat.Enabled = false;
            Rooms.Enabled = false;

            txtbxInput.Enabled = false;
            txtbxCR.Enabled = false;
            btnCR.Enabled = false;

            txtbxJR.Enabled = false;
            btnJR.Enabled = false;

            tmrGetMessages.Enabled = false;

            Chat.Items.Clear();
            Rooms.Items.Clear();
            lstbxJR.Items.Clear();
            lstbxJR.Enabled = false;
        }

        private async void loadRooms()
        {
            using (var httpClient = new HttpClient())
            {

                var endpoint = $"{baseUrl}/getUserRooms";
                var jsonCredentials = JsonConvert.SerializeObject(token.userId);
                var content = new StringContent(jsonCredentials, Encoding.UTF8, "application/json");
                var response = await httpClient.PostAsync(endpoint, content);

                // Check if the request was successful
                if (response.IsSuccessStatusCode)
                {
                    var jsonString = await response.Content.ReadAsStringAsync();

                    this.RoomsList = System.Text.Json.JsonSerializer.Deserialize<List<Room>>(jsonString);

                    Room roomGlobal = new Room();
                    roomGlobal.destinationId = 0;
                    roomGlobal.name = "Global Chat";
                    this.RoomsList.Insert(0, roomGlobal);

                    assignRoomsListToUI();
                }
                else
                {
                    var error = await response.Content.ReadAsStringAsync();
                    throw new Exception(error);
                }
            }
        }
        private void assignRoomsListToUI()
        {
            Rooms.Items.Clear();
            foreach (Room room in RoomsList)
            {
                Rooms.Items.Add(room);
            }

            Rooms.SelectedIndex = 0;

        }

        private void Rooms_SelectedIndexChanged(object sender, EventArgs e)
        {
            getMessages();
        }

        private void assignChatMessagesListToUI()
        {
            if(Chat.Items.Count == ChatMessagesList.Count)
            {
                if (Chat.Items.Count > 0)
                {
                    ChatMessage lastMessage = (ChatMessage)Chat.Items[Chat.Items.Count - 1];
                    String timestamp = lastMessage.timestamp;
                    if(ChatMessagesList[ChatMessagesList.Count-1].timestamp==timestamp)
                    {
                        return;
                    }
                }
            }
            Chat.Items.Clear();
            foreach (ChatMessage message in ChatMessagesList)
            {
                Chat.Items.Add(message);
            }
            Chat.TopIndex = Chat.Items.Count - 1;
        }

        private void tmrGetMessages_Tick(object sender, EventArgs e)
        {
            getMessages();
        }

        private async void getMessages()
        {
            using (var httpClient = new HttpClient())
            {

                var endpoint = $"{baseUrl}/getMessages";
                String timestamp = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds().ToString();
                MessageRequestForm mrf = new MessageRequestForm(RoomsList[Rooms.SelectedIndex].destinationId, timestamp, 256);
                Console.WriteLine("mrf: " + mrf.ToString());
                var jsonCredentials = JsonConvert.SerializeObject(mrf);
                Console.WriteLine("jsonCredentials: " + jsonCredentials);
                var content = new StringContent(jsonCredentials, Encoding.UTF8, "application/json");
                var response = await httpClient.PostAsync(endpoint, content);

                // Check if the request was successful
                if (response.IsSuccessStatusCode)
                {
                    var jsonString = await response.Content.ReadAsStringAsync();

                    this.ChatMessagesList = System.Text.Json.JsonSerializer.Deserialize<List<ChatMessage>>(jsonString);
                    Console.WriteLine("Length of ChatMessagesList: " + this.ChatMessagesList.Count);
                    assignChatMessagesListToUI();
                }
                else
                {
                    var error = await response.Content.ReadAsStringAsync();
                    throw new Exception(error);
                }
            }
        }

        private async void txtbxInput_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                using (var httpClient = new HttpClient())
                {

                    var endpoint = $"{baseUrl}/send";
                    String timestamp = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds().ToString();
                    MessageForm mf = new MessageForm(RoomsList[Rooms.SelectedIndex].destinationId, timestamp, token.userId, txtbxInput.Text, "");
                    txtbxInput.Text = "";
                    Console.WriteLine("mf: " + mf.ToString());
                    var jsonCredentials = JsonConvert.SerializeObject(mf);
                    Console.WriteLine("jsonCredentials: " + jsonCredentials);
                    var content = new StringContent(jsonCredentials, Encoding.UTF8, "application/json");
                    var response = await httpClient.PostAsync(endpoint, content);

                    // Check if the request was successful
                    if (response.IsSuccessStatusCode)
                    {
                        Console.WriteLine("Message sent.");
                        getMessages();
                    }
                    else
                    {
                        var error = await response.Content.ReadAsStringAsync();
                        throw new Exception(error);
                    }
                }
            }
        }

        private void txtbxCR_KeyDown(object sender, KeyEventArgs e)
        {
            if(e.KeyCode == Keys.Enter)
            {
                CreateRoom();
            }
        }

        private void btnCR_Click(object sender, EventArgs e)
        {
            CreateRoom();
        }
        private async void CreateRoom()
        {
            if(!String.IsNullOrEmpty(txtbxCR.Text))
            {
                using (var httpClient = new HttpClient())
                {

                    var endpoint = $"{baseUrl}/saveRoom";
                    DestinationForm df = new DestinationForm(txtbxCR.Text, token.userId);
                    txtbxCR.Text = "";
                    Console.WriteLine("df: " + df.ToString());
                    var jsonCredentials = JsonConvert.SerializeObject(df);
                    Console.WriteLine("jsonCredentials: " + jsonCredentials);
                    var content = new StringContent(jsonCredentials, Encoding.UTF8, "application/json");
                    var response = await httpClient.PostAsync(endpoint, content);

                    // Check if the request was successful
                    if (response.IsSuccessStatusCode)
                    {
                        var jsonString = await response.Content.ReadAsStringAsync();

                        Console.WriteLine(jsonString);
                        loadRooms();
                    }
                    else
                    {
                        var error = await response.Content.ReadAsStringAsync();
                        throw new Exception(error);
                    }
                }
            }
            else
            {
                txtbxCR.Text = "Input name";
            }
        }

        private async void txtbxJR_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                if(!String.IsNullOrEmpty(txtbxJR.Text))
                {
                    using (var httpClient = new HttpClient())
                    {

                        var endpoint = $"{baseUrl}/searchRooms";
                        var jsonCredentials = JsonConvert.SerializeObject(txtbxJR.Text);
                        txtbxJR.Text = "";
                        Console.WriteLine("jsonCredentials: " + jsonCredentials);
                        var content = new StringContent(jsonCredentials, Encoding.UTF8, "application/json");
                        var response = await httpClient.PostAsync(endpoint, content);

                        // Check if the request was successful
                        if (response.IsSuccessStatusCode)
                        {
                            var jsonString = await response.Content.ReadAsStringAsync();

                            this.searchRoomsList = System.Text.Json.JsonSerializer.Deserialize<List<Room>>(jsonString);

                            assignSearchRoomsListToUI();
                        }
                        else
                        {
                            var error = await response.Content.ReadAsStringAsync();
                            throw new Exception(error);
                        }
                    }
                }
                else
                {
                    txtbxJR.Text = "Input name";
                }
            }
        }
        private void assignSearchRoomsListToUI()
        {
            lstbxJR.Items.Clear();
            foreach (Room room in searchRoomsList)
            {
                lstbxJR.Items.Add(room);
            }
        }

        private async void btnJR_Click(object sender, EventArgs e)
        {
            if(lstbxJR.Items.Count>0)
            {
                if(lstbxJR.SelectedIndex!=-1)
                {
                    using (var httpClient = new HttpClient())
                    {

                        var endpoint = $"{baseUrl}/AddUserToRoom";
                        JoinDestinationForm jdf = new JoinDestinationForm(searchRoomsList[lstbxJR.SelectedIndex].destinationId, token.userId);
                        var jsonCredentials = JsonConvert.SerializeObject(jdf);
                        lstbxJR.Items.Clear();
                        Console.WriteLine("jsonCredentials: " + jsonCredentials);
                        var content = new StringContent(jsonCredentials, Encoding.UTF8, "application/json");
                        var response = await httpClient.PostAsync(endpoint, content);

                        // Check if the request was successful
                        if (response.IsSuccessStatusCode)
                        {
                            var jsonString = await response.Content.ReadAsStringAsync();

                            Console.WriteLine(jsonString);

                            loadRooms();
                        }
                        else
                        {
                            var error = await response.Content.ReadAsStringAsync();
                            throw new Exception(error);
                        }
                    }
                }
                lstbxJR.SelectedIndex = -1;
            }
        }
    }
}
