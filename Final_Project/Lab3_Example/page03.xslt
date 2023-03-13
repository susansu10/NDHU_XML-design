<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/"> 
      <html>
          <body background="images\bg06.gif" style="background-size:1530px 750px;
background-position:center 0;">
          <audio src="../music/bg06.mp3" loop="loop" autoplay="autoplay">
          </audio>
            <h2 align="center">
            <font size="6" style="color: #4C0099;
background-color:white;" face="DFKai-sb">
                高中的困擾
            </font>
            </h2>
            <ul>		
	        <li style="font-size:20px">
	   	    <xsl:for-each select="page03/page"> 
		      <dt align="left">
                 <b>
                 <a style="color:black;">
                    <xsl:attribute name="href">
                       <xsl:value-of select="name"/>
			        </xsl:attribute>                    
		            <xsl:value-of select="title"/>
                 </a>
                 </b>
		      </dt>
		    </xsl:for-each>
	       </li>
	      </ul>
              <table align="center" border="0">	
                    <center>
                      <img src="images/per5.gif" alt="Welcome~" width="200" height="150"></img>
                    </center>
                    <xsl:for-each select="page03/feature">
                      <tr>
                        <td align="center" style="color:#0000e4;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                      </tr>
                    </xsl:for-each>
                    <xsl:for-each select="page03/feature1">
                      <tr>
                        <td align="center" style="color:#ae00a6;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                      </tr>
                    </xsl:for-each>
                    <xsl:for-each select="page03/feature2">
                      <tr>
                        <td align="center" style="color:#6c2049;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                      </tr>
                    </xsl:for-each>
                    <xsl:for-each select="page03/feature3">
                       <tr>
                        <td align="center" style="color:red; background-color:white;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                       </tr>
                    </xsl:for-each>
                    <xsl:for-each select="page03/feature4">
                      <tr>
                        <td align="center" style="color:blue; background-color:white;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                      </tr>
                    </xsl:for-each>
              </table>
          </body>
      </html>
    </xsl:template>
</xsl:stylesheet>