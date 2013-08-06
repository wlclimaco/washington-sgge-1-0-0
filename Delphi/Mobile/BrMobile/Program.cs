using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace BrMobile
{
    //SIS000 - Principal
    //SIS001 - Login
    //SIS002 - Conferencia de Entrada
    //SIS003 - Versão
    //SIS004 - Configuração de IP
    //SIS005 - Conferencia de Saida
    //SIS006 - Valida Etiqueta de Cliente
    //SIS007 - Recebe Etiqueta de produto e SSCC (Provisório)

    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [MTAThread]
        static void Main()
        {
            Application.Run(new SIS000());
        }
    }
}