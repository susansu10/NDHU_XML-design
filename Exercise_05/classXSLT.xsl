<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>My Class</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th style="text-align:left">ID</th>
      <th style="text-align:left">NAME</th>
      <th style="text-align:left">XML_score</th>
      <th style="text-align:left">CS_Score</th>
      <th style="text-align:left">Security_score</th>
      <th style="text-align:left">OS_Score</th>
    </tr>
    <xsl:for-each select="class/student">
    <tr>
      <td><xsl:value-of select="id"/></td>
      <td><xsl:value-of select="name"/></td>
      <td><xsl:value-of select="xml_score"/></td>
      <td><xsl:value-of select="cs_score"/></td>
      <td><xsl:value-of select="security_score"/></td>
      <td><xsl:value-of select="os_score"/></td>
    </tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>