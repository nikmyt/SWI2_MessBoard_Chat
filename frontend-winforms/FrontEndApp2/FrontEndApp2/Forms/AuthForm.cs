﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FrontEndApp2.Forms
{
    class AuthForm
    {
        public string username { get; set; }
        public string password { get; set; }

        public AuthForm(string username, string password)
        {
            this.username = username;
            this.password = password;
        }
    }
}
