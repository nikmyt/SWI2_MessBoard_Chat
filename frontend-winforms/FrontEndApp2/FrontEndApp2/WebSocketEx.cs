using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.WebSockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace FrontEndApp2
{
    public class WebSocketEx
    {
        ClientWebSocket clientWebSocket = new ClientWebSocket();
        public async Task ConnectWebSocket()
        {
            using (clientWebSocket)
            {
                Uri serverUri = new Uri("http://localhost:8080/ws");
                await clientWebSocket.ConnectAsync(serverUri, CancellationToken.None);
            }
        }

        public async Task DisconnectWebSocket()
        {
            using (clientWebSocket)
            {
                Uri serverUri = new Uri("http://localhost:8080/ws");
                await clientWebSocket.CloseAsync(WebSocketCloseStatus.NormalClosure, "Closed by user", CancellationToken.None);
            }
        }
    }
}
