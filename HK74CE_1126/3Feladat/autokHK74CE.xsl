<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <!-- 1. Autók rendszámait visszaadó lista -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Autók Rendszámai</title>
            </head>
            <body>
                <h1>Autók Rendszámai</h1>
                <ul>
                    <xsl:for-each select="autok/auto">
                        <li><xsl:value-of select="@rsz"/></li>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
