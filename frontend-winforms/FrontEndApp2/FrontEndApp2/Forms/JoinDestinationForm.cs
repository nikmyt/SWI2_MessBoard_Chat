using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FrontEndApp2.Forms
{
    class JoinDestinationForm
    {
        public JoinDestinationForm(long destinationId, long userID)
        {
            this.destinationId = destinationId;
            this.userID = userID;
        }
        public long destinationId { get; set; }
        public long userID { get; set; }

        public override string ToString()
        {
            return "MessageRequestForm{" +
                "destination='" + destinationId + '\'' +
                ", userId='" + userID + '\'' +
                '}';
        }
    }
}
