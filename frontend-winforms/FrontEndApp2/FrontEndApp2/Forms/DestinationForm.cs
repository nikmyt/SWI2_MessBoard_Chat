using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FrontEndApp2.Forms
{
    class DestinationForm
    {
        public DestinationForm(String destination, long userID)
        {
            this.destination = destination;
            this.userID = userID;
        }
        public String destination { get; set; }
        public long userID { get; set; }

        public override string ToString()
        {
            return "MessageRequestForm{" +
                "destination='" + destination + '\'' +
                ", userId='" + userID + '\'' +
                '}';
        }
    }
}
