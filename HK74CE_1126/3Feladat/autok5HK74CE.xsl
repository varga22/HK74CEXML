<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <!-- 6. Autótípusok és példányaik darabszáma példányszám szerint csökkenő sorrendben -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Autótípusok és Darabszámuk</title>
            </head>
            <body>
                <h1>Autótípusok és Darabszámuk</h1>
                <ul>
                    <xsl:for-each select="autok/auto">
                        <xsl:sort select="count(../auto[tipus=current()/tipus])" order="descending" data-type="number"/>
                        <xsl:if test="not(preceding::auto[tipus=current()/tipus])">
                            <li>
                                <xsl:value-of select="tipus"/>: <xsl:value-of select="count(../auto[tipus=current()/tipus])"/>
                            </li>
                        </xsl:if>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
