<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/messages">
        <xsl:variable name="owner" select="/messages/@owner"/>
        <html>
            <head>
                <link rel="stylesheet" href="messageStyle.css" type="text/css" />
            </head>   
        
            <body>
                <div class="header"><div id="logo" /></div>
            
                <div id="container">
                    <div class="side">
                        <div class="sideDiv">
                            <div id="ownerImg"></div>
                            <div id="ownerName"><xsl:value-of select="/messages/@owner" /></div>  
                        </div>
                    
                        <div class="sideDiv2">
                            <div id="chat">
                                <div id="chatImg"></div>
                                <div id="chatName"><xsl:value-of select="/messages/@chatName" /></div>
                            </div>   
                        </div>
                    </div>
                    <div class="messagesContainer">
                        <xsl:for-each select="message">
                            <div>
                            <xsl:choose>
                                <xsl:when test="from = $owner">
                                    <div id="myMessages">
                                        <div style="margin-bottom: 2px; margin-top: 5px;float: right; margin: 4px; color: #FFF;">
                                            <xsl:value-of select="from" />
                                        </div><br/>
                                        
                                        <div style="margin-bottom: 7px; margin-top: 5px;float: right; color: #FFF;">
                                            <xsl:value-of select="date" />
                                        </div><br/>
                                        
                                        <div style="background-color:#ffe6ff;
                                             border-bottom-left-radius: 40px; 
                                             border-top-left-radius: 40px; 
                                             border-bottom-right-radius: 10px; 
                                             border-top-right-radius: 5px; 
                                             padding:5 5 5 10; margin-left: 10px; 
                                             margin-top: 5px;">  
                                            <xsl:variable name="content" select="content"/>
                                            <xsl:for-each select="content">
                                                <xsl:variable name="color" select="@color"/>
                                                <xsl:variable name="size" select="@font-size"/>
                                                <xsl:variable name="family" select="@font-family"/>
                                                <p style=" color: {$color} ;
                                                   font-size: {$size} px;
                                                   font-family: {$family} ;
                                                   max-width: 600px;
                                                   word-wrap:break-word; ">
                                                    <xsl:value-of select="$content" />
                                                </p>
                                            </xsl:for-each>
                                        </div>
                                    </div><br/>
                                </xsl:when>
                                
                                <xsl:otherwise>
                                    <div id="otherMessages">
                                        <div style="margin-bottom: 2px; margin-top: 5px; margin-left: 7%; color: #FFF;">
                                            <xsl:value-of select="from" />
                                        </div>
                                        
                                        <div style="margin-bottom: 2px; margin-top: 5px; margin-left: 7%; color: #FFF;">
                                            <xsl:value-of select="date" />
                                        </div>
                                        
                                        <div style="background-color:#ffe6ff;
                                             border-bottom-left-radius: 10px; 
                                             border-top-left-radius: 10px; 
                                             border-bottom-right-radius: 40px; 
                                             border-top-right-radius: 40px; 
                                             padding:5 10 5 10; margin-left: 10px;
                                             max-width: 500px;
                                             word-wrap:break-word; ">  
                                            <xsl:variable name="content" select="content"/>
                                            <xsl:for-each select="content">
                                                <xsl:variable name="color" select="@color"/>
                                                <xsl:variable name="size" select="@font-size"/>
                                                <xsl:variable name="family" select="@font-family"/>
                                                <p style=" color: {$color} max-width: 500px ;
                                                   font-size: {$size} px;
                                                   font-family: {$family} ;">
                                                    <xsl:value-of select="$content" />
                                                </p>
                                            </xsl:for-each>
                                        </div>
                                    </div>
                                </xsl:otherwise>
                            </xsl:choose>
                            </div><br/><br/><br/><br/><br/><br/><br/><br/><br/>
                        </xsl:for-each>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>