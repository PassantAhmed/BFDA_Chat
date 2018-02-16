<?xml version="1.0"?>
<!-- Simple XSLT document for intro.xml -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="messages">
        <xsl:apply-templates select="message"/>
    </xsl:template>
    <xsl:template match="message">
        <header >
        </header>
        <h1>
            <xsl:value-of select="text()"/>
        </h1>
        <br/>
    </xsl:template>
</xsl:stylesheet>