<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/"> 
  <html>
      <body background="images\bgg.gif">
        <h2 align="center">Picture Gallery</h2>
          <table>	
                <center>
                  <img src="images/pic_photo.jpg" alt="Pic01"></img>
                </center>
                <center>
                  <img src="images/pic01.jpg" alt="Pic02"></img>
                </center>
                <center>
                  <img src="images/pic02.jpg" alt="Pic03"></img>
                </center>
                <center>
                  <img src="images/img01.jpg" alt="Pic04"></img>
                </center>
                <center>
                  <img src="images/onion01.jpg" alt="Pic05"></img>
                </center>
                <center>
                  <img src="images/onion02.jpg" alt="Pic06"></img>
                </center>
                <xsl:for-each select="gallery/feature">
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