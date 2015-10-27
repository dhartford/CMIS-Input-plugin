package it.francescocorti.kettle.cmisinput;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import org.apache.chemistry.opencmis.client.bindings.spi.StandardAuthenticationProvider;




public class AuthenticationSelfSignedSSLProvider extends StandardAuthenticationProvider{
	
	

	@Override
	public SSLSocketFactory getSSLSocketFactory() {
				SSLSocketFactory sslFactory = null;
				try {
					SSLContext ctx = SSLContext.getInstance("TLS");
					ctx.init(null, getAcceptingTrustManager(), new SecureRandom( ));
					sslFactory = ctx.getSocketFactory();		
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (KeyManagementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return sslFactory;
	}
			
	
			

	
	private TrustManager[] getAcceptingTrustManager(){
	    TrustManager tm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
				// TODO Auto-generated method stub
				
			}
        };
        
        return new TrustManager[] { tm };
	}
			
}
