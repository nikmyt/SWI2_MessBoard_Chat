using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FrontEndApp2.Forms
{
    class MessageRequestForm
    {
        public MessageRequestForm(long destinationId, String timestamp, int numberOfMessages)
        {
            this.destinationId = destinationId;
            this.timestamp = timestamp;
            this.numberOfMessages = numberOfMessages;
        }
        public long destinationId { get; set; }
        public String timestamp { get; set; }
        public int numberOfMessages { get; set; }

        public override string ToString()
        {
            return "MessageRequestForm{" +
                "destinatiomId='" + destinationId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", numberOfMessages='" + numberOfMessages + '\'' +
                '}';
        }
    }
}
