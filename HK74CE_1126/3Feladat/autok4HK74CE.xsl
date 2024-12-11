<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <!-- 5. Miskolci tulajdonosok autóinak rendszámai -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Miskolci Autók</title>
            </head>
            <body>
                <h1>Miskolci Tulajdonosok Autóinak Rendszámai</h1>
                <ul>
                    <xsl:for-each select="autok/auto[tulaj/varos='Miskolc']">
                        <li><xsl:value-of select="@rsz"/></li>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
