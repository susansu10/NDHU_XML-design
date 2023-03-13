<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
	<body background="images\error.jpg" style="background-size:1500px 745px;
background-position:center 0;">
  	  <table align="center" border="0">
        <h1>You need to find the key to unlock the page.</h1>
        <h2 id="demo">How to find the key to unlock the 404 page ?</h2>
        <button type="button"
        onclick="document.getElementById('demo').innerHTML = 'The key is in the middle of the whole page!'">Click Me!
        </button>		
	    <tr>
	   	    <xsl:for-each select="user/page"> 
		      <td align="center">
                 <a style="color: #3366B3;">
                    <xsl:attribute name="href">
                       <xsl:value-of select="name"/>
			        </xsl:attribute>                    
		            <xsl:value-of select="title"/>
                 </a>
		      </td>
		    </xsl:for-each>
	    </tr>
	  </table>
	</body>
  </html>
</xsl:template>
</xsl:stylesheet>