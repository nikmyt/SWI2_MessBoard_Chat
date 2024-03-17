using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FrontEndApp2.Model
{
    class ChatMessage
    {
		public long id { get; set; }
		public String timestamp { get; set; }
		public long destinationId { get; set; }
		public long senderId { get; set; }
		public String text { get; set; }
		public String extra { get; set; }

		public override string ToString()
		{
			return text;
		}
	}
}
