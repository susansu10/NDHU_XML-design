<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/"> 
  <html>
      <body background="images\bgg.gif">
        <h2 align="center">My Husband</h2>
          <table align="center" border="0">	
                <center>
                  <img src="images/man.jpg" alt="Welcome~"></img>
                </center>
                <xsl:for-each select="introduction/feature">
                    <tr bgcolor="#32cdab">
                        <td>
                          <xsl:value-of select="."/>
                        </td>
                    </tr>
                </xsl:for-each>
        </table>
      </body>
  </html>
</xsl:template>
</xsl:stylesheet>