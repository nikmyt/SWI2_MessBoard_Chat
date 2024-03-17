using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FrontEndApp2.Forms
{
    class MessageForm
    {

        public long destinationId { get; set; }
        public String timestamp { get; set; }
        public long senderId { get; set; }
        public String text { get; set; }
        public String extra { get; set; }

        public MessageForm(long destinationId, String timestamp, long senderId, String text, String extra)
        {
            this.destinationId = destinationId;
            this.timestamp = timestamp;
            this.senderId = senderId;
            this.text = text;
            this.extra = extra;
        }

        public override string ToString()
        {
            return "MessageRequestForm{" +
                "destinatiomId='" + destinationId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", senderId='" + senderId + '\'' +
                ", text='" + text + '\'' +
                ", extra='" + extra + '\'' +
                '}';
        }
    }
}
