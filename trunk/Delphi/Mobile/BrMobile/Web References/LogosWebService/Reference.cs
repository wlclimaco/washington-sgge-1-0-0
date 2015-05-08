﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:2.0.50727.3634
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

// 
// This source code was auto-generated by Microsoft.CompactFramework.Design.Data, Version 2.0.50727.3634.
// 
namespace LogosMobile.LogosWebService {
    using System.Diagnostics;
    using System.Web.Services;
    using System.ComponentModel;
    using System.Web.Services.Protocols;
    using System;
    using System.Xml.Serialization;
    
    
    /// <remarks/>
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Web.Services.WebServiceBindingAttribute(Name="ILogosWSbinding", Namespace="http://tempuri.org/")]
    public partial class ILogosWSservice : System.Web.Services.Protocols.SoapHttpClientProtocol {
        
        /// <remarks/>
        public ILogosWSservice() {
            this.Url = "http://192.10.10.67:8080/logos/logosws.exe/soap/ILogosWS";
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapRpcMethodAttribute("urn:LogosWSIntf-ILogosWS#AutenticaUsuario", RequestNamespace="urn:LogosWSIntf-ILogosWS", ResponseNamespace="urn:LogosWSIntf-ILogosWS")]
        [return: System.Xml.Serialization.SoapElementAttribute("return")]
        public string AutenticaUsuario(string pDsLogin, string pDsSenha, ref int pCdUsuari) {
            object[] results = this.Invoke("AutenticaUsuario", new object[] {
                        pDsLogin,
                        pDsSenha,
                        pCdUsuari});
            pCdUsuari = ((int)(results[1]));
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public System.IAsyncResult BeginAutenticaUsuario(string pDsLogin, string pDsSenha, int pCdUsuari, System.AsyncCallback callback, object asyncState) {
            return this.BeginInvoke("AutenticaUsuario", new object[] {
                        pDsLogin,
                        pDsSenha,
                        pCdUsuari}, callback, asyncState);
        }
        
        /// <remarks/>
        public string EndAutenticaUsuario(System.IAsyncResult asyncResult, out int pCdUsuari) {
            object[] results = this.EndInvoke(asyncResult);
            pCdUsuari = ((int)(results[1]));
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapRpcMethodAttribute("urn:LogosWSIntf-ILogosWS#ProximaAtividade", RequestNamespace="urn:LogosWSIntf-ILogosWS", ResponseNamespace="urn:LogosWSIntf-ILogosWS")]
        [return: System.Xml.Serialization.SoapElementAttribute("return")]
        public string ProximaAtividade(int pCdUsuari, ref string pNrMapa, ref string pDsAtivid) {
            object[] results = this.Invoke("ProximaAtividade", new object[] {
                        pCdUsuari,
                        pNrMapa,
                        pDsAtivid});
            pNrMapa = ((string)(results[1]));
            pDsAtivid = ((string)(results[2]));
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public System.IAsyncResult BeginProximaAtividade(int pCdUsuari, string pNrMapa, string pDsAtivid, System.AsyncCallback callback, object asyncState) {
            return this.BeginInvoke("ProximaAtividade", new object[] {
                        pCdUsuari,
                        pNrMapa,
                        pDsAtivid}, callback, asyncState);
        }
        
        /// <remarks/>
        public string EndProximaAtividade(System.IAsyncResult asyncResult, out string pNrMapa, out string pDsAtivid) {
            object[] results = this.EndInvoke(asyncResult);
            pNrMapa = ((string)(results[1]));
            pDsAtivid = ((string)(results[2]));
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapRpcMethodAttribute("urn:LogosWSIntf-ILogosWS#ConsultaAtividade", RequestNamespace="urn:LogosWSIntf-ILogosWS", ResponseNamespace="urn:LogosWSIntf-ILogosWS")]
        [return: System.Xml.Serialization.SoapElementAttribute("return")]
        public string ConsultaAtividade(string pNrFornec, string pSgTipAti, ref string pNrOpeLog) {
            object[] results = this.Invoke("ConsultaAtividade", new object[] {
                        pNrFornec,
                        pSgTipAti,
                        pNrOpeLog});
            pNrOpeLog = ((string)(results[1]));
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public System.IAsyncResult BeginConsultaAtividade(string pNrFornec, string pSgTipAti, string pNrOpeLog, System.AsyncCallback callback, object asyncState) {
            return this.BeginInvoke("ConsultaAtividade", new object[] {
                        pNrFornec,
                        pSgTipAti,
                        pNrOpeLog}, callback, asyncState);
        }
        
        /// <remarks/>
        public string EndConsultaAtividade(System.IAsyncResult asyncResult, out string pNrOpeLog) {
            object[] results = this.EndInvoke(asyncResult);
            pNrOpeLog = ((string)(results[1]));
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapRpcMethodAttribute("urn:LogosWSIntf-ILogosWS#AtividadeNaoConvocada", RequestNamespace="urn:LogosWSIntf-ILogosWS", ResponseNamespace="urn:LogosWSIntf-ILogosWS")]
        [return: System.Xml.Serialization.SoapElementAttribute("return")]
        public string AtividadeNaoConvocada(int pCdUsuari, ref string pDsLista) {
            object[] results = this.Invoke("AtividadeNaoConvocada", new object[] {
                        pCdUsuari,
                        pDsLista});
            pDsLista = ((string)(results[1]));
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public System.IAsyncResult BeginAtividadeNaoConvocada(int pCdUsuari, string pDsLista, System.AsyncCallback callback, object asyncState) {
            return this.BeginInvoke("AtividadeNaoConvocada", new object[] {
                        pCdUsuari,
                        pDsLista}, callback, asyncState);
        }
        
        /// <remarks/>
        public string EndAtividadeNaoConvocada(System.IAsyncResult asyncResult, out string pDsLista) {
            object[] results = this.EndInvoke(asyncResult);
            pDsLista = ((string)(results[1]));
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapRpcMethodAttribute("urn:LogosWSIntf-ILogosWS#ConferenciaEntradaIniciar", RequestNamespace="urn:LogosWSIntf-ILogosWS", ResponseNamespace="urn:LogosWSIntf-ILogosWS")]
        [return: System.Xml.Serialization.SoapElementAttribute("return")]
        public string ConferenciaEntradaIniciar(int pCdUsuari, string pNrMapa, ref string pNrNota, ref string pNrFornec, ref string pDsLista) {
            object[] results = this.Invoke("ConferenciaEntradaIniciar", new object[] {
                        pCdUsuari,
                        pNrMapa,
                        pNrNota,
                        pNrFornec,
                        pDsLista});
            pNrNota = ((string)(results[1]));
            pNrFornec = ((string)(results[2]));
            pDsLista = ((string)(results[3]));
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public System.IAsyncResult BeginConferenciaEntradaIniciar(int pCdUsuari, string pNrMapa, string pNrNota, string pNrFornec, string pDsLista, System.AsyncCallback callback, object asyncState) {
            return this.BeginInvoke("ConferenciaEntradaIniciar", new object[] {
                        pCdUsuari,
                        pNrMapa,
                        pNrNota,
                        pNrFornec,
                        pDsLista}, callback, asyncState);
        }
        
        /// <remarks/>
        public string EndConferenciaEntradaIniciar(System.IAsyncResult asyncResult, out string pNrNota, out string pNrFornec, out string pDsLista) {
            object[] results = this.EndInvoke(asyncResult);
            pNrNota = ((string)(results[1]));
            pNrFornec = ((string)(results[2]));
            pDsLista = ((string)(results[3]));
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapRpcMethodAttribute("urn:LogosWSIntf-ILogosWS#ConferenciaEntradaFinalizar", RequestNamespace="urn:LogosWSIntf-ILogosWS", ResponseNamespace="urn:LogosWSIntf-ILogosWS")]
        [return: System.Xml.Serialization.SoapElementAttribute("return")]
        public string ConferenciaEntradaFinalizar(int pCdUsuari, string pNrMapa, string pdslista, string pSnCancel, int pQtProSid) {
            object[] results = this.Invoke("ConferenciaEntradaFinalizar", new object[] {
                        pCdUsuari,
                        pNrMapa,
                        pdslista,
                        pSnCancel,
                        pQtProSid});
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public System.IAsyncResult BeginConferenciaEntradaFinalizar(int pCdUsuari, string pNrMapa, string pdslista, string pSnCancel, int pQtProSid, System.AsyncCallback callback, object asyncState) {
            return this.BeginInvoke("ConferenciaEntradaFinalizar", new object[] {
                        pCdUsuari,
                        pNrMapa,
                        pdslista,
                        pSnCancel,
                        pQtProSid}, callback, asyncState);
        }
        
        /// <remarks/>
        public string EndConferenciaEntradaFinalizar(System.IAsyncResult asyncResult) {
            object[] results = this.EndInvoke(asyncResult);
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapRpcMethodAttribute("urn:LogosWSIntf-ILogosWS#ConferenciaSaidaIniciar", RequestNamespace="urn:LogosWSIntf-ILogosWS", ResponseNamespace="urn:LogosWSIntf-ILogosWS")]
        [return: System.Xml.Serialization.SoapElementAttribute("return")]
        public string ConferenciaSaidaIniciar(int pCdUsuari, string pNrMapa, ref string pNrNota, ref string pNrFornec, ref string pDsLista) {
            object[] results = this.Invoke("ConferenciaSaidaIniciar", new object[] {
                        pCdUsuari,
                        pNrMapa,
                        pNrNota,
                        pNrFornec,
                        pDsLista});
            pNrNota = ((string)(results[1]));
            pNrFornec = ((string)(results[2]));
            pDsLista = ((string)(results[3]));
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public System.IAsyncResult BeginConferenciaSaidaIniciar(int pCdUsuari, string pNrMapa, string pNrNota, string pNrFornec, string pDsLista, System.AsyncCallback callback, object asyncState) {
            return this.BeginInvoke("ConferenciaSaidaIniciar", new object[] {
                        pCdUsuari,
                        pNrMapa,
                        pNrNota,
                        pNrFornec,
                        pDsLista}, callback, asyncState);
        }
        
        /// <remarks/>
        public string EndConferenciaSaidaIniciar(System.IAsyncResult asyncResult, out string pNrNota, out string pNrFornec, out string pDsLista) {
            object[] results = this.EndInvoke(asyncResult);
            pNrNota = ((string)(results[1]));
            pNrFornec = ((string)(results[2]));
            pDsLista = ((string)(results[3]));
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapRpcMethodAttribute("urn:LogosWSIntf-ILogosWS#ConferenciaSaidaFinalizar", RequestNamespace="urn:LogosWSIntf-ILogosWS", ResponseNamespace="urn:LogosWSIntf-ILogosWS")]
        [return: System.Xml.Serialization.SoapElementAttribute("return")]
        public string ConferenciaSaidaFinalizar(int pCdUsuari, string pNrMapa, string pdslista, string pCdClient, string pSnCancel, int pQtProSid) {
            object[] results = this.Invoke("ConferenciaSaidaFinalizar", new object[] {
                        pCdUsuari,
                        pNrMapa,
                        pdslista,
                        pCdClient,
                        pSnCancel,
                        pQtProSid});
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public System.IAsyncResult BeginConferenciaSaidaFinalizar(int pCdUsuari, string pNrMapa, string pdslista, string pCdClient, string pSnCancel, int pQtProSid, System.AsyncCallback callback, object asyncState) {
            return this.BeginInvoke("ConferenciaSaidaFinalizar", new object[] {
                        pCdUsuari,
                        pNrMapa,
                        pdslista,
                        pCdClient,
                        pSnCancel,
                        pQtProSid}, callback, asyncState);
        }
        
        /// <remarks/>
        public string EndConferenciaSaidaFinalizar(System.IAsyncResult asyncResult) {
            object[] results = this.EndInvoke(asyncResult);
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapRpcMethodAttribute("urn:LogosWSIntf-ILogosWS#Ping", RequestNamespace="urn:LogosWSIntf-ILogosWS", ResponseNamespace="urn:LogosWSIntf-ILogosWS")]
        [return: System.Xml.Serialization.SoapElementAttribute("return")]
        public string Ping() {
            object[] results = this.Invoke("Ping", new object[0]);
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public System.IAsyncResult BeginPing(System.AsyncCallback callback, object asyncState) {
            return this.BeginInvoke("Ping", new object[0], callback, asyncState);
        }
        
        /// <remarks/>
        public string EndPing(System.IAsyncResult asyncResult) {
            object[] results = this.EndInvoke(asyncResult);
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapRpcMethodAttribute("urn:LogosWSIntf-ILogosWS#TestaConexao", RequestNamespace="urn:LogosWSIntf-ILogosWS", ResponseNamespace="urn:LogosWSIntf-ILogosWS")]
        [return: System.Xml.Serialization.SoapElementAttribute("return")]
        public string TestaConexao(string pIDTest, string pMsnTest) {
            object[] results = this.Invoke("TestaConexao", new object[] {
                        pIDTest,
                        pMsnTest});
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public System.IAsyncResult BeginTestaConexao(string pIDTest, string pMsnTest, System.AsyncCallback callback, object asyncState) {
            return this.BeginInvoke("TestaConexao", new object[] {
                        pIDTest,
                        pMsnTest}, callback, asyncState);
        }
        
        /// <remarks/>
        public string EndTestaConexao(System.IAsyncResult asyncResult) {
            object[] results = this.EndInvoke(asyncResult);
            return ((string)(results[0]));
        }
    }
}