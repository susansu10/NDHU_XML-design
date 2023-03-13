<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/"> 
      <html>
          <body background="images\page02bg.jpg" style="background-size:1550px 750px;
background-position:center 0;">
          <audio src="../music/bg04.mp3" loop="loop" autoplay="autoplay">
          </audio>
            <h2 align="center">
            <font size="6" style="color: #4C0099;
background-color:white;" face="DFKai-sb">
                國中不好的回憶
            </font>
            </h2>
            <ul>		
	        <li style="font-size:20px">
	   	    <xsl:for-each select="page02/page"> 
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
                      <img src="images/per4.gif" alt="Welcome~" width="200" height="250"></img>
                    </center>
                    <xsl:for-each select="page02/feature">
                      <tr>
                        <td align="center" style="color:#ffffb1;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                      </tr>
                    </xsl:for-each>
                    <xsl:for-each select="page02/feature1">
                      <tr>
                        <td align="center" style="color:#ffbfb1;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                      </tr>
                    </xsl:for-each>
                    <xsl:for-each select="page02/feature2">
                      <tr>
                        <td align="center" style="color:#ffffb1;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                      </tr>
                    </xsl:for-each>
                    <xsl:for-each select="page02/hightlight">
                       <tr>
                        <td align="center" style="color:red; background-color:white;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                       </tr>
                    </xsl:for-each>
                    <xsl:for-each select="page02/feature3">
                      <tr>
                        <td align="center" style="color:#ffffb1;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                      </tr>
                    </xsl:for-each>
                    <xsl:for-each select="page02/feature4">
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