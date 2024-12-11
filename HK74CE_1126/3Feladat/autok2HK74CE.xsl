<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <!-- 3. Autók száma, amelyek drágábbak 30000-nél -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Autók Darabszáma</title>
            </head>
            <body>
                <h1>Autók, amelyek drágábbak 30000-nél</h1>
                <p>
                    Az ilyen autók száma: <xsl:value-of select="count(autok/auto[ar &gt; 30000])"/>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>