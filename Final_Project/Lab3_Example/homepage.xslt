<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
     <body background="images\bg04.gif" style="background-size:1550px 745px;
background-position:center 0;">
     <h2 align="center">
       <font size="7" color="greenyellow">Susu's Homepage
       </font>
     </h2>
     <h3 align="center">
       <font size="2" color="#CC99FF">410821204 杜昉紜 資工四
       </font>
     </h3>
     <h3 align="center">
       <font size="2" color="#CC99FF">這裡會介紹我的人生階段遇到的事情與未來夢想！
       </font>
     </h3>
     <center>
     <audio src="../music/bg01.mp3" loop="loop" autoplay="autoplay">
     </audio>
     </center>
     <center>
       <img align="center" src="images/per1.gif" width="300" height="250" alt="Welcome~"></img>
     </center>
     <center>
     <a href="https://github.com/susansu10" target="_blank" >
       <font size="5" color="#FFFF00">My Github
       </font>
     </a>
     </center>
       <ul>		
	    <li style="font-size:20px">
	   	    <xsl:for-each select="homepage/page"> 
		      <dt align="center">
                 <b>
                 <a style="color: #4C0099;
background-color:white;">
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
	</body>
  </html>
</xsl:template>
</xsl:stylesheet>