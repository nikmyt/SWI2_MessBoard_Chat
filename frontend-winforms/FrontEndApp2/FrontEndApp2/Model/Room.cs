﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FrontEndApp2.Model
{
    class Room
    {
        public long destinationId { get; set; }
        public string name { get; set; }

        public override string ToString()
        {
            return name;
        }
    }
}
