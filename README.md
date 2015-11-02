CMIS Input plugin for PDI
=================

Imagine being able to extract from your Enterprise Content Management System, all the metadata of your documents using simple queries with a query language very close to the traditional SQL. Imagine using the information extracted for statistical purposes, for creating reports and, more generally, to analyse your document archives in a way unthinkable until now with the current tools available. All this is possible within the Pentaho Suite, the Open Source Business Intelligence platform, which is useful to the extraction and analysis of structured and semi-structured data.

With this goal (the extraction and analysis of data) has been designed and developed the CMIS Input plugin for Pentaho Data Integration (Kettle) that allows querying Content Management Systems that use the CMIS interoperability standard. The data, once extracted, can be stored and analyzed and perhaps presented in customized reports be published in various formats for the end user (PDF, Excel, etc..).

The CMIS Input plugin, thanks to the use of the library Apache Chemistry OpenCMIS, is able to interoperate with ECM market leaders such as Alfresco, Microsoft SharePoint, Nuxeo, OpenText and many others.

For more informations, please refer to

http://fcorti.com/pdi-cmis-input/

================
DRH general what-to-expect
 - need to write your own cmis query (no support built-in).  
 - TIP: immediately map to another step, like text file output, and keep using that's step 'Get Fields' feature to verify your query and fields coming onto the PDI/ETL stream. 
 - a simple 'select cmis:name from cmis:document' (over HTTPS) may only run at ~40 records/sec depending on your setup.
 - CMIS query syntax itself does *not* have a limit/top option
     - TODO need to add pagination / maxRecords support via OperationContext or similar for that kind of behavior.  This should be investigated for both the 'preview' option from PDI, as well as when you want to stop the transformation half-way through and not have to wait for the CMIS query to keep running.
 - Only 1000 items (and lucene or alfresco 4.x system)?  Need to configure alfresco-global.properties for system.acl.maxPermissionChecks=1000000 (or whatever big number)...need to figure out how to get openCMIS to work correctly (paginate) for each request without exceeding that max permsision number, which should only be 1000, not that high...

2015-10-30 DRH TLS/SSL update -
 - added custom AuthenticationProvider that allows self-signed SSL certs 
     - TODO should make a checkbox option to allow any SSL Cert.
 - Research why GET calls were using HTTPS, but POST were only using HTTP...need to modify Alfresco server when using reverse proxy setups (apache, nginx, F5, etc);  For alfresco 4.2.*, 5.0.*, see http://docs.alfresco.com/5.0/tasks/configure-ssl-prod.html   (opencmis.server.* properties for alfresco-global.properties). Note that older versions have various other means to change the cmis setup.  Verify by going to alfresco/api/-default-/cmis/versions/1.0/atom and looking at the downloaded atompub xml document for app:collection and the url defined there.
     - TODO should add better check process to inform end user of difference between requested URL and URL within the atompub return document.
 - DefaultHttpInvoker verified, have not verified ApacheClientHttpInvoker yet.
