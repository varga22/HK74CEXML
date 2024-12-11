<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <!-- 2. Autók rendszámát és árát ár szerinti sorrendben -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Autók Rendszáma és Ára</title>
            </head>
            <body>
                <h1>Autók Rendszáma és Ára (Ár Sorrendben)</h1>
                <ul>
                    <xsl:for-each select="autok/auto">
                        <xsl:sort select="ar" order="ascending"/>
                        <li>
                            <xsl:value-of select="@rsz"/> - <xsl:value-of select="ar"/>
                        </li>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
