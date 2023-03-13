<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/"> 
  <html>
      <body background="images\bg05.gif" style="background-size:1550px 750px;
background-position:center 0;">
     <audio src="../music/bg02.mp3" loop="loop" autoplay="autoplay">
     </audio>
        <h2 align="center">
            <font size="6" color="#3333FF" face="DFKai-sb">
                我的小學有趣日常
            </font>
        </h2>
        <ul>		
	    <li style="font-size:20px">
	   	    <xsl:for-each select="page01/page"> 
		      <dt align="left">
                 <b>
                 <a>
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
                  <img src="images/page01.gif" alt="Welcome~"></img>
                </center>
                <xsl:for-each select="page01/feature">
                    <tr>
                        <td align="center">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                    </tr>
                </xsl:for-each>
                <xsl:for-each select="page01/esc1">
                    <tr>
                        <td align="center" style="color:blue;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                    </tr>
                </xsl:for-each>
                <xsl:for-each select="page01/feature1">
                    <tr>
                        <td align="center">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                    </tr>
                </xsl:for-each>
                <xsl:for-each select="page01/esc2">
                    <tr>
                        <td align="center" style="color:blue;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                    </tr>
                </xsl:for-each>
                <xsl:for-each select="page01/hightlight1">
                    <tr>
                        <td align="center" style="color:red;">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                    </tr>
                </xsl:for-each>
                <xsl:for-each select="page01/feature2">
                    <tr>
                        <td align="center">
                        <font size="5" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                    </tr>
                </xsl:for-each>
                <xsl:for-each select="page01/hightlight2">
                    <tr>
                        <td align="center" style="color:#33ef4c; background-color:white;">
                        <font size="6" face="DFKai-sb">
                          <xsl:value-of select="."/>
                        </font>
                        </td>
                    </tr>
                </xsl:for-each>
                <xsl:for-each select="page01/feature3">
                    <tr>
                        <td align="center">
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