<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <!-- 4. Dokumentum elemeinek száma -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Dokumentum Elem Számláló</title>
            </head>
            <body>
                <h1>Dokumentum Elem Számláló</h1>
                <p>
                    Az elemek száma: <xsl:value-of select="count(//*)"/>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
