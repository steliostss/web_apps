<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<script type="text/javascript" charset="utf-8" id="zm-extension"/>
 <!--
 Test with 
 java org.apache.xalan.xslt.Process -IN cars1.xml -XSL  without_xslForEach_with_Modes.xsl -OUT out.html 
-->
<xsl:template match="/">
<html>
<head>
<title>Generic xml Printout in Table Form</title>
</head>
<body>
<h1 align="center">
Table of
<xsl:value-of select="name(/*)"/>
</h1>
<table align="center" border="2">
<tr>
<xsl:apply-templates select="/*/*[1]/*|/*/*[1]/@*" mode="column_names"/>
</tr>
<xsl:apply-templates select="/*/*"/>
</table>
</body>
</html>
</xsl:template>
 <!--
   .......... .....  This template for column names .....................   
-->
<xsl:template match="/*/*[1]/*|/*/*[1]/@*" mode="column_names">
<td>
<xsl:value-of select="name()"/>
</td>
</xsl:template>
 <!--
 .................................................................................................... 
-->
 <!--
  ......................... These templates for values ........................  
-->
<xsl:template match="/*/*">
<tr>
<xsl:apply-templates select="*|@*"/>
</tr>
</xsl:template>
<xsl:template match="/*/*/*|/*/*/@*">
<td>
<xsl:value-of select="."/>
</td>
</xsl:template>
 <!--
 .................................................................................................... 
-->
</xsl:stylesheet>